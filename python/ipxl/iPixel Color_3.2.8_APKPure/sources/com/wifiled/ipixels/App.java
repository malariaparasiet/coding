package com.wifiled.ipixels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import com.wifiled.baselib.BaseLibApi;
import com.wifiled.baselib.CoreBase;
import com.wifiled.baselib.Options;
import com.wifiled.baselib.uicode.application.Core;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.ipixels.core.AppLifeObserver;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.SocketManager;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.view.customview.CustomImageViewPlus;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: App.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\t"}, d2 = {"Lcom/wifiled/ipixels/App;", "Lcom/wifiled/baselib/uicode/application/Core;", "<init>", "()V", "onCreate", "", "getViewModelStore", "Landroidx/lifecycle/ViewModelStore;", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class App extends Core {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static ViewModelStore appViewModelStore;
    public static Context context;
    public static App instace;

    /* compiled from: App.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/wifiled/ipixels/App$Companion;", "", "<init>", "()V", "instace", "Lcom/wifiled/ipixels/App;", "getInstace", "()Lcom/wifiled/ipixels/App;", "setInstace", "(Lcom/wifiled/ipixels/App;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "appViewModelStore", "Landroidx/lifecycle/ViewModelStore;", "getAppViewModelStore", "()Landroidx/lifecycle/ViewModelStore;", "setAppViewModelStore", "(Landroidx/lifecycle/ViewModelStore;)V", "gotoAppDetailIntent", "", "activity", "Landroid/app/Activity;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final App getInstace() {
            App app = App.instace;
            if (app != null) {
                return app;
            }
            Intrinsics.throwUninitializedPropertyAccessException("instace");
            return null;
        }

        public final void setInstace(App app) {
            Intrinsics.checkNotNullParameter(app, "<set-?>");
            App.instace = app;
        }

        public final Context getContext() {
            Context context = App.context;
            if (context != null) {
                return context;
            }
            Intrinsics.throwUninitializedPropertyAccessException("context");
            return null;
        }

        public final void setContext(Context context) {
            Intrinsics.checkNotNullParameter(context, "<set-?>");
            App.context = context;
        }

        public final ViewModelStore getAppViewModelStore() {
            return App.appViewModelStore;
        }

        public final void setAppViewModelStore(ViewModelStore viewModelStore) {
            App.appViewModelStore = viewModelStore;
        }

        public final void gotoAppDetailIntent(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
            activity.startActivity(intent);
        }
    }

    @Override // com.wifiled.baselib.uicode.application.Core, android.app.Application
    public void onCreate() {
        super.onCreate();
        Companion companion = INSTANCE;
        companion.setInstace(this);
        App app = this;
        BaseLibApi.init(app, new Options(false));
        ContextHolder.init(app);
        CoreBase.init(app);
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        companion.setContext(applicationContext);
        SocketManager.INSTANCE.initSocket();
        BleManager.INSTANCE.get().init(app);
        BleManager2.INSTANCE.get().init(app);
        ProcessLifecycleOwner.INSTANCE.get().getLifecycle().addObserver(new AppLifeObserver());
        PreferenceSettings.INSTANCE.init(app);
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.wifiled.ipixels.App$onCreate$1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Intrinsics.checkNotNullParameter(outState, "outState");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                CustomImageView.Companion.detachView();
                CustomImageViewPlus.Companion.detachView();
            }
        });
    }

    public final ViewModelStore getViewModelStore() {
        if (appViewModelStore == null) {
            appViewModelStore = new ViewModelStore();
        }
        ViewModelStore viewModelStore = appViewModelStore;
        Intrinsics.checkNotNull(viewModelStore);
        return viewModelStore;
    }
}
