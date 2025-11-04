package com.easysocket.callback;

import android.app.Dialog;
import android.content.DialogInterface;
import com.easysocket.entity.exception.RequestCancelException;
import com.easysocket.interfaces.callback.IProgressDialog;
import com.easysocket.interfaces.callback.ProgressCancelListener;

/* loaded from: classes2.dex */
public abstract class ProgressDialogCallBack<T> extends SuperCallBack<T> implements ProgressCancelListener {
    private boolean isShowProgress;
    private Dialog mDialog;
    private IProgressDialog progressDialog;

    @Override // com.easysocket.callback.SuperCallBack
    public abstract void onResponse(T t);

    public ProgressDialogCallBack(IProgressDialog iProgressDialog, String str) {
        super(str);
        this.isShowProgress = true;
        this.progressDialog = iProgressDialog;
        init(false);
        onStart();
    }

    public ProgressDialogCallBack(IProgressDialog iProgressDialog, boolean z, boolean z2, String str) {
        super(str);
        this.progressDialog = iProgressDialog;
        this.isShowProgress = z;
        init(z2);
        onStart();
    }

    private void init(boolean z) {
        IProgressDialog iProgressDialog = this.progressDialog;
        if (iProgressDialog == null) {
            return;
        }
        Dialog dialog = iProgressDialog.getDialog();
        this.mDialog = dialog;
        if (dialog == null) {
            return;
        }
        dialog.setCancelable(z);
        if (z) {
            this.mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.easysocket.callback.ProgressDialogCallBack.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    ProgressDialogCallBack.this.onCancelProgress();
                }
            });
        }
    }

    private void showProgress() {
        Dialog dialog;
        if (!this.isShowProgress || (dialog = this.mDialog) == null || dialog.isShowing()) {
            return;
        }
        this.mDialog.show();
    }

    private void dismissProgress() {
        Dialog dialog;
        if (this.isShowProgress && (dialog = this.mDialog) != null && dialog.isShowing()) {
            this.mDialog.dismiss();
        }
    }

    @Override // com.easysocket.callback.SuperCallBack
    public void onStart() {
        showProgress();
    }

    @Override // com.easysocket.callback.SuperCallBack
    public void onCompleted() {
        dismissProgress();
    }

    @Override // com.easysocket.callback.SuperCallBack
    public void onError(Exception exc) {
        onCompleted();
    }

    @Override // com.easysocket.interfaces.callback.ProgressCancelListener
    public void onCancelProgress() {
        onCompleted();
        onError(new RequestCancelException("网络请求被取消"));
    }
}
