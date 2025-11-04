package com.wifiled.baselib.uicode.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import com.airbnb.lottie.LottieAnimationView;
import com.wifiled.baselib.R;
import com.wifiled.baselib.uicode.utils.UICoreConfig;

/* loaded from: classes2.dex */
public class LoadingProgressDialog extends ProgressDialog {
    private boolean backCanceled;
    private boolean onTouchOutsideCanceled;

    public LoadingProgressDialog(Context context) {
        this(context, R.style.CustomDialog);
    }

    public LoadingProgressDialog(Context context, int i) {
        super(context, i);
    }

    @Override // android.app.ProgressDialog, android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
    }

    private void init() {
        setCancel();
        setContentView(R.layout.dialog_common_progress);
        ((LottieAnimationView) findViewById(R.id.lottie_progress)).setAnimation(UICoreConfig.INSTANCE.getProgressLottie());
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -2;
        attributes.height = -2;
        getWindow().setAttributes(attributes);
    }

    private void setCancel() {
        setCancelable(this.backCanceled);
        setCanceledOnTouchOutside(this.onTouchOutsideCanceled);
    }

    public void setCanceled(boolean z, boolean z2) {
        this.onTouchOutsideCanceled = z;
        this.backCanceled = z2;
    }

    @Override // android.app.Dialog
    public void show() {
        setCancel();
        super.show();
    }
}
