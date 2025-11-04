package com.wifiled.ipixels.ui;

import android.os.Handler;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.utils.HandlerUtil;
import kotlin.Metadata;

/* compiled from: SplashActivity.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"Lcom/wifiled/ipixels/ui/SplashActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "<init>", "()V", "layoutId", "", "bindData", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SplashActivity extends BaseActivity {
    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_splash;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        Handler handler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler().get();
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.wifiled.ipixels.ui.SplashActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SplashActivity.bindData$lambda$0(SplashActivity.this);
                }
            }, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$0(SplashActivity splashActivity) {
        splashActivity.toActivity(ChooseActivity.class);
        splashActivity.finish();
    }
}
