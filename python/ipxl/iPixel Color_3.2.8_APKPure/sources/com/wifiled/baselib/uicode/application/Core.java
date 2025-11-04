package com.wifiled.baselib.uicode.application;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import androidx.multidex.MultiDexApplication;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.Utils;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.uicode.net.NetLongLogger;
import com.wifiled.baselib.uicode.net.NetManager;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/* compiled from: Core.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b&\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\t\u001a\u00020\u0007H\u0002J\b\u0010\n\u001a\u00020\u0007H\u0002¨\u0006\f"}, d2 = {"Lcom/wifiled/baselib/uicode/application/Core;", "Landroidx/multidex/MultiDexApplication;", "<init>", "()V", "getResources", "Landroid/content/res/Resources;", "onCreate", "", "initCrash", "configNet", "initBlankj", "Companion", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class Core extends MultiDexApplication {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static Context context;
    private static Handler handler;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initBlankj$lambda$1(String str, String str2) {
    }

    private final void initCrash() {
    }

    /* compiled from: Core.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/wifiled/baselib/uicode/application/Core$Companion;", "", "<init>", "()V", "handler", "Landroid/os/Handler;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getHanlder", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Context getContext() {
            Context context = Core.context;
            if (context != null) {
                return context;
            }
            Intrinsics.throwUninitializedPropertyAccessException("context");
            return null;
        }

        public final void setContext(Context context) {
            Intrinsics.checkNotNullParameter(context, "<set-?>");
            Core.context = context;
        }

        public final Handler getHanlder() {
            Handler handler = Core.handler;
            if (handler != null) {
                return handler;
            }
            Intrinsics.throwUninitializedPropertyAccessException("handler");
            return null;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        Intrinsics.checkNotNull(resources);
        return resources;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Companion companion = INSTANCE;
        handler = new Handler(Looper.getMainLooper());
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        companion.setContext(applicationContext);
        initBlankj();
        CrashUtils.init(new CrashUtils.OnCrashListener() { // from class: com.wifiled.baselib.uicode.application.Core$$ExternalSyntheticLambda0
            @Override // com.blankj.utilcode.util.CrashUtils.OnCrashListener
            public final void onCrash(CrashUtils.CrashInfo crashInfo) {
                Core.onCreate$lambda$0(crashInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(CrashUtils.CrashInfo crashInfo) {
        LogUtils.file(crashInfo.toString());
    }

    private final void configNet() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new NetLongLogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        NetManager.Builer addInterceptor = new NetManager.Builer(Constance.API.BASE_URL2, 15L).addInterceptor(httpLoggingInterceptor);
        RxJava2CallAdapterFactory create = RxJava2CallAdapterFactory.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        addInterceptor.addCallAdapterFactory(create).build();
    }

    private final void initBlankj() {
        Utils.init(this);
        LogUtils.getConfig().setLogSwitch(true);
        LogUtils.getConfig().setLog2FileSwitch(true);
        LogUtils.getConfig().setSingleTagSwitch(true);
        LogUtils.getConfig().setGlobalTag("ruis");
        LogUtils.getConfig().setDir(new File(PathUtils.getExternalAppFilesPath(), "log"));
        LogUtils.getConfig().setFilePrefix("iPixel");
        LogUtils.getConfig().setBorderSwitch(true);
        LogUtils.getConfig().setSaveDays(1);
        LogUtils.getConfig().setOnFileOutputListener(new LogUtils.OnFileOutputListener() { // from class: com.wifiled.baselib.uicode.application.Core$$ExternalSyntheticLambda1
            @Override // com.blankj.utilcode.util.LogUtils.OnFileOutputListener
            public final void onFileOutput(String str, String str2) {
                Core.initBlankj$lambda$1(str, str2);
            }
        });
    }
}
