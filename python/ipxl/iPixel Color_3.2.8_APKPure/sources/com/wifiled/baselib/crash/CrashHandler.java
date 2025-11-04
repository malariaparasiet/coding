package com.wifiled.baselib.crash;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import com.wifiled.baselib.callback.CallBack;
import com.wifiled.baselib.retrofit.NetWorkManager;
import com.wifiled.baselib.retrofit.entity.CloudRes;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.baselib.utils.ThreadUtils;
import java.io.File;
import java.lang.Thread;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    private Context context;
    private CrashCollect crashCollect;
    private CrashUploader crashUploader;
    long curTime;
    private Thread.UncaughtExceptionHandler defaultHandler;
    private Class targetClass;

    public interface CrashUploader {
        void crashMessage(CrashInfo crashInfo);
    }

    private CrashHandler(Builder builder) {
        this.crashUploader = builder.crashUploader;
        this.targetClass = builder.targetClass;
    }

    public void init(Context context) {
        this.context = context;
        this.crashCollect = new CrashCollect(context);
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        File crashLogFile = CrashCollect.getCrashLogFile();
        LogUtils.logd("====file:" + crashLogFile.exists(), new Object[0]);
        if (crashLogFile.exists()) {
            CrashInfo collectCrashInfo = this.crashCollect.collectCrashInfo(null);
            LogUtils.loge("上传上次的错误信息", new Object[0]);
            uploadCrashInfo(collectCrashInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadCrashInfo(CrashInfo crashInfo) {
        NetWorkManager.INSTANCE.getRequest().uploadCrash(crashInfo.getAppPackage(), crashInfo.getAppChannel(), crashInfo.getPhoneSystem(), crashInfo.getPhoneBrands(), crashInfo.getPhoneModel(), crashInfo.getPhoneSystemVersion(), crashInfo.getAppVersionName(), crashInfo.getAppVersionCode(), crashInfo.getExceptionInfo()).enqueue(new Callback<CloudRes>() { // from class: com.wifiled.baselib.crash.CrashHandler.1
            @Override // retrofit2.Callback
            public void onFailure(Call<CloudRes> call, Throwable th) {
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<CloudRes> call, Response<CloudRes> response) {
            }
        });
    }

    public static class Builder {
        private CrashUploader crashUploader;
        private Class targetClass;

        public Builder crashUploader(CrashUploader crashUploader) {
            this.crashUploader = crashUploader;
            return this;
        }

        public Builder targetClass(Class cls) {
            this.targetClass = cls;
            return this;
        }

        public CrashHandler build() {
            return new CrashHandler(this);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(final Thread thread, final Throwable th) {
        try {
            LogUtils.loge("=====uncaughtException=============1", new Object[0]);
            if (!catchCrashException(th) && this.defaultHandler != null) {
                LogUtils.loge("===如果用户没有处理则让系统默认的异常处理器来处理", new Object[0]);
                this.defaultHandler.uncaughtException(thread, th);
            } else {
                ThreadUtils.asyncDelay(4000L, new CallBack() { // from class: com.wifiled.baselib.crash.CrashHandler.2
                    @Override // com.wifiled.baselib.callback.CallBack
                    public void execute() {
                        Log.e(CrashHandler.TAG, "====uncaughtException: 终止退出程序");
                        if (CrashHandler.this.targetClass == null) {
                            CrashHandler.this.defaultHandler.uncaughtException(thread, th);
                        }
                        System.exit(0);
                        Process.killProcess(Process.myPid());
                    }
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean catchCrashException(final Throwable th) throws InterruptedException {
        Log.e(TAG, "========catchCrashException..." + th);
        if (th == null) {
            return false;
        }
        toTargetActivity();
        ThreadUtils.async(new CallBack() { // from class: com.wifiled.baselib.crash.CrashHandler.3
            @Override // com.wifiled.baselib.callback.CallBack
            public void execute() {
                Log.e(CrashHandler.TAG, "===正在上传崩溃信息到服务器..." + CrashHandler.this.crashCollect);
                CrashInfo collectCrashInfo = CrashHandler.this.crashCollect.collectCrashInfo(th);
                LogUtils.loge("crashInfo.getExceptionInfo():" + collectCrashInfo.getExceptionInfo(), new Object[0]);
                CrashHandler.this.crashCollect.saveCrashInfoToFile(collectCrashInfo.getExceptionInfo());
                ThreadUtils.delay(500L);
                LogUtils.loge("crashUploader:" + CrashHandler.this.crashUploader, new Object[0]);
                if (CrashHandler.this.crashUploader != null) {
                    CrashHandler.this.crashUploader.crashMessage(collectCrashInfo);
                    LogUtils.loge("===上传崩溃信息到服务器1", new Object[0]);
                    CrashHandler.this.uploadCrashInfo(collectCrashInfo);
                } else {
                    LogUtils.loge("===上传崩溃信息到服务器", new Object[0]);
                    CrashHandler.this.uploadCrashInfo(collectCrashInfo);
                }
            }
        });
        Thread.sleep(1000L);
        return true;
    }

    private void toTargetActivity() {
        if (this.targetClass != null) {
            Intent intent = new Intent();
            intent.setClass(this.context, this.targetClass);
            intent.setFlags(268468224);
            this.context.startActivity(intent);
        }
    }
}
