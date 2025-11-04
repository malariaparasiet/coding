package com.wifiled.baselib.manager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wifiled.baselib.FileProvider7;
import com.wifiled.baselib.LogInterceptor;
import com.wifiled.baselib.R;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.bean.UpdateVO;
import com.wifiled.baselib.manager.UpdateManager;
import com.wifiled.baselib.utils.AppUtils;
import com.wifiled.baselib.utils.FileUtils;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.TimeUtils;
import com.wifiled.baselib.utils.ToastUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes2.dex */
public class UpdateManager {
    private static final int DOWN_BEFORE = 4;
    private static final int DOWN_FAIL = 3;
    private static final int DOWN_OVER = 2;
    private static final int DOWN_UPDATE = 1;
    private static final String TAG = "UpdateManager";
    public static boolean isNeedUpDateApp = false;
    private Activity mContext;
    private AlertDialog mDownloadDialog;
    private DownloadListener mDownloadListener;
    private DownloadThread mDownloadThread;
    private ProgressBar mProgress;
    private boolean mShowDialog;
    private float mSize;
    private TextView mTvTotal;
    private boolean interceptFlag = false;
    private boolean mApk = true;
    private MessageHandler mHandler = new MessageHandler();

    public interface DownloadListener {
        void onDownloadComplete();

        void onDownloadFailed();

        void onDownloading(int i);

        void onInstall(File file);

        void onPreDownload(String str);
    }

    private class MessageHandler extends Handler {
        private MessageHandler() {
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                if (UpdateManager.this.mProgress != null) {
                    UpdateManager.this.mProgress.setProgress(message.arg1);
                }
                if (UpdateManager.this.mDownloadListener != null) {
                    UpdateManager.this.mDownloadListener.onDownloading(message.arg1);
                    return;
                }
                return;
            }
            if (i == 2) {
                if (UpdateManager.this.mDownloadListener != null) {
                    UpdateManager.this.mDownloadListener.onDownloadComplete();
                }
                UpdateManager.this.install((File) message.obj);
                return;
            }
            if (i != 3) {
                if (i == 4) {
                    if (UpdateManager.this.mDownloadListener != null) {
                        UpdateManager.this.mDownloadListener.onPreDownload((String) message.obj);
                        return;
                    }
                    return;
                }
                super.dispatchMessage(message);
                return;
            }
            if (UpdateManager.this.mDownloadDialog != null) {
                UpdateManager.this.mDownloadDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateManager.this.mContext);
                builder.setCancelable(false);
                builder.setMessage(R.string.down_fail);
                final String str = (String) message.obj;
                final float f = UpdateManager.this.mSize;
                builder.setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.manager.UpdateManager.MessageHandler.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        dialogInterface.dismiss();
                        UpdateManager.this.downloadFile(str, f, UpdateManager.this.mShowDialog);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.manager.UpdateManager.MessageHandler.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
            if (UpdateManager.this.mDownloadListener != null) {
                UpdateManager.this.mDownloadListener.onDownloadFailed();
            }
        }
    }

    public UpdateManager(Activity activity) {
        this.mContext = activity;
    }

    public void versionUpdate(String str) {
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(new LogInterceptor()).build();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("app_id", AppUtils.getPackageName(this.mContext));
        builder.add("platform", Constance.APP.PLATFORM);
        build.newCall(new Request.Builder().url("http://api.e-toys.cn/api/app/lastUpdate").post(builder.build()).build()).enqueue(new AnonymousClass1(str));
    }

    /* renamed from: com.wifiled.baselib.manager.UpdateManager$1, reason: invalid class name */
    class AnonymousClass1 implements Callback {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final /* synthetic */ String val$language;

        AnonymousClass1(String str) {
            this.val$language = str;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wifiled.baselib.manager.UpdateManager$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ToastUtil.show(R.string.down_fail);
                }
            });
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) throws IOException {
            final UpdateVO updateVO;
            String string = response.body().string();
            if (string == null || string.isEmpty()) {
                Log.e("JSON", "json string is null or empty");
                return;
            }
            try {
                JSONObject parseObject = JSON.parseObject(string);
                if (parseObject == null || parseObject.getInteger(NotificationCompat.CATEGORY_STATUS).intValue() != 0) {
                    return;
                }
                String string2 = parseObject.getString("data");
                if (TextUtils.isEmpty(string2) || (updateVO = (UpdateVO) JSONObject.parseObject(string2, UpdateVO.class)) == null) {
                    return;
                }
                int versionCode = AppUtils.getVersionCode(UpdateManager.this.mContext);
                LogUtils.logd("当前版本号：" + versionCode, new Object[0]);
                if (TextUtils.isEmpty(updateVO.app_url) || versionCode < 0 || updateVO.app_version_number <= versionCode) {
                    return;
                }
                UpdateManager.isNeedUpDateApp = true;
                Log.i(UpdateManager.TAG, "onResponse: 检测到新版本:" + updateVO.toString());
                JSONObject parseObject2 = JSON.parseObject(updateVO.app_update);
                final String str = UpdateManager.this.mContext.getResources().getString(R.string.find_new_version) + ":" + updateVO.app_version + "\n\n" + UpdateManager.this.mContext.getResources().getString(R.string.size) + ":" + (((int) ((updateVO.app_size / 1024.0f) * 100.0f)) / 100) + "MB\n\n" + (this.val$language.contains("zh") ? parseObject2.getString("cn") : parseObject2.getString("en"));
                if (ActivityUtils.isActivityAlive(UpdateManager.this.mContext)) {
                    UpdateManager.this.mHandler.post(new Runnable() { // from class: com.wifiled.baselib.manager.UpdateManager$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            UpdateManager.AnonymousClass1.this.lambda$onResponse$1(str, updateVO);
                        }
                    });
                } else {
                    UpdateManager.this.mHandler.removeCallbacksAndMessages(null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onResponse$1(String str, UpdateVO updateVO) {
            UpdateManager.this.showNoticeDialog(str, updateVO.app_url, updateVO.app_size);
        }
    }

    public void showNoticeDialog(String str, String str2, float f) {
        showNoticeDialog(str, str2, f, true);
    }

    public void showNoticeDialog(String str, final String str2, final float f, final boolean z) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setCancelable(false);
        builder.setTitle(R.string.update_title);
        builder.setMessage(str);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.manager.UpdateManager.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                UpdateManager.this.downloadFile(str2, f, z);
            }
        });
        builder.setNegativeButton(R.string.remind_later, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.manager.UpdateManager.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void setDownloadDialog(AlertDialog alertDialog) {
        if (alertDialog != this.mDownloadDialog) {
            this.mDownloadDialog = alertDialog;
        }
    }

    public void downloadFile(String str, float f, boolean z) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this.mContext, R.string.download_url_error, 1).show();
            return;
        }
        File externalFilePath = FileUtils.getExternalFilePath(this.mContext, "apk");
        Log.e("TAG", "UpdateManager>>>[downloadFile]: " + externalFilePath);
        if (externalFilePath.exists() || !externalFilePath.mkdir()) {
            String substring = str.substring(str.lastIndexOf("/"));
            File file = new File(externalFilePath, substring);
            if (file.exists()) {
                install(file);
                return;
            }
            try {
                if (this.mDownloadDialog == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
                    builder.setCancelable(false);
                    builder.setTitle(R.string.update_title);
                    builder.setView(R.layout.update_layout);
                    this.mDownloadDialog = builder.create();
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.manager.UpdateManager.4
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            UpdateManager.this.mDownloadThread.interrupt();
                            UpdateManager.this.interceptFlag = true;
                        }
                    });
                }
                this.mSize = f;
                this.mDownloadDialog.show();
                View findViewById = this.mDownloadDialog.findViewById(R.id.progress);
                if (findViewById != null && (findViewById instanceof ProgressBar)) {
                    this.mProgress = (ProgressBar) findViewById;
                }
                View findViewById2 = this.mDownloadDialog.findViewById(R.id.tv_total);
                if (findViewById2 != null && (findViewById2 instanceof TextView)) {
                    TextView textView = (TextView) findViewById2;
                    this.mTvTotal = textView;
                    textView.setText(this.mContext.getString(R.string.total_size, new Object[]{Float.valueOf(this.mSize / 1024.0f)}));
                }
                this.mShowDialog = z;
                downloadApk(new File(externalFilePath, substring + ".tmp"), str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showDownloadDialog(String str, float f) {
        downloadFile(str, f, true);
    }

    private class DownloadThread extends Thread {
        String downloadUrl;
        File saveFile;

        DownloadThread(File file, String str) {
            this.saveFile = file;
            this.downloadUrl = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.downloadUrl).openConnection();
                httpURLConnection.connect();
                int contentLength = httpURLConnection.getContentLength();
                InputStream inputStream = httpURLConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(this.saveFile);
                UpdateManager.this.mHandler.obtainMessage(4, this.downloadUrl).sendToTarget();
                byte[] bArr = new byte[1024];
                int i = 0;
                while (true) {
                    int read = inputStream.read(bArr);
                    i += read;
                    UpdateManager.this.mHandler.obtainMessage(1, (int) ((i / contentLength) * 100.0f), 0).sendToTarget();
                    if (read <= 0) {
                        File file = new File(this.saveFile.getCanonicalPath().replace(".tmp", ""));
                        this.saveFile.renameTo(file);
                        UpdateManager.this.mHandler.obtainMessage(2, file).sendToTarget();
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, read);
                        if (UpdateManager.this.interceptFlag) {
                            break;
                        }
                    }
                }
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                UpdateManager.this.mHandler.obtainMessage(3, this.downloadUrl).sendToTarget();
            }
        }
    }

    public DownloadListener getDownloadListener() {
        return this.mDownloadListener;
    }

    public void setDownloadListener(DownloadListener downloadListener) {
        this.mDownloadListener = downloadListener;
    }

    public boolean isApk() {
        return this.mApk;
    }

    public void setApk(boolean z) {
        this.mApk = z;
    }

    private void downloadApk(File file, String str) {
        DownloadThread downloadThread = this.mDownloadThread;
        if (downloadThread == null || !downloadThread.isAlive()) {
            DownloadThread downloadThread2 = new DownloadThread(file, str);
            this.mDownloadThread = downloadThread2;
            downloadThread2.start();
        }
    }

    public static void uploadInstallInfo(final Context context, String str) {
        if (((Boolean) SPUtils.get(context, Constance.SP.FIRST_INSTALL, true)).booleanValue()) {
            OkHttpClient build = new OkHttpClient.Builder().addInterceptor(new LogInterceptor()).build();
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("app_package", AppUtils.getPackageName(context));
            builder.add("app_channel", AppUtils.getAppMetaData(context, "HEATON_CHANNEL"));
            builder.add("phone_system", Constance.APP.PLATFORM);
            builder.add("phone_brands", AppUtils.getDeviceBrand());
            builder.add("phone_model", AppUtils.getSystemModel());
            builder.add("phone_system_version", AppUtils.getSystemVersion());
            builder.add("run_time", TimeUtils.getNowTime());
            builder.add("return_symbolize", str + "");
            build.newCall(new Request.Builder().url("http://api.e-toys.cn/api/app/count").post(builder.build()).build()).enqueue(new Callback() { // from class: com.wifiled.baselib.manager.UpdateManager.5
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        if (JSON.parseObject(response.body().string()).getInteger(NotificationCompat.CATEGORY_STATUS).intValue() == 0) {
                            Log.i(UpdateManager.TAG, "onResponse: 上传安装信息成功");
                            SPUtils.put(context, Constance.SP.FIRST_INSTALL, false);
                        } else {
                            Log.e(UpdateManager.TAG, "onResponse: 上传安装信息失败");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void install(File file) {
        try {
            if (file.exists()) {
                if (this.mDownloadDialog != null && ActivityUtils.isActivityAlive(this.mContext)) {
                    this.mDownloadDialog.dismiss();
                }
                DownloadListener downloadListener = this.mDownloadListener;
                if (downloadListener != null) {
                    downloadListener.onInstall(file);
                }
                if (this.mApk) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(268435456);
                    FileProvider7.setIntentDataAndType(this.mContext, intent, "application/vnd.android.package-archive", file, true);
                    this.mContext.startActivity(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void grantUriPermission(Context context, Uri uri, Intent intent) {
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (it.hasNext()) {
            context.grantUriPermission(it.next().activityInfo.packageName, uri, 3);
        }
    }
}
