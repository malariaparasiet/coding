package com.wifiled.baselib.uicode.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.wifiled.baselib.uicode.inner.ICommonViewDelegate;
import com.wifiled.baselib.uicode.statuslayout.DefaultStatusLayout;
import com.wifiled.baselib.uicode.statuslayout.OnStatusCustomClickListener;
import com.wifiled.baselib.uicode.statuslayout.OnStatusRetryClickListener;
import com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager;
import com.wifiled.baselib.uicode.utils.UICoreConfig;

/* loaded from: classes2.dex */
public class CommonViewDelegate implements ICommonViewDelegate {
    private StatusLayoutManager.Builder builder;
    private LoadingProgressDialog mProgressDialog;
    private StatusLayoutManager statusLayoutManager;

    public CommonViewDelegate(Context context) {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new LoadingProgressDialog(context);
            setProgressDialogCanceled(true, true);
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICommonViewDelegate
    public void showProgressDialog() {
        this.mProgressDialog.show();
    }

    public void setProgressDialogCanceled(boolean z, boolean z2) {
        this.mProgressDialog.setCanceled(z, z2);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICommonViewDelegate
    public void hideProgressDialog() {
        if (this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    public StatusLayoutManager getStatusLayoutManager() {
        return this.statusLayoutManager;
    }

    public StatusLayoutManager.Builder getStatusLayoutManagerBuilder() {
        return this.builder;
    }

    public void initStatusLayout(View view) {
        StatusLayoutManager.Builder builder = new StatusLayoutManager.Builder(view);
        this.builder = builder;
        builder.setDefaultThemeColor(UICoreConfig.INSTANCE.getDefaultThemeColor());
        this.builder.setDefaultEmptyImg(UICoreConfig.INSTANCE.getDefaultEmptyIcon());
        this.builder.setDefaultLoadErrorImg(UICoreConfig.INSTANCE.getLoadErrorIcon());
        this.builder.setDefaultNetDisconnectImg(UICoreConfig.INSTANCE.getNetDisconnectIcon());
        this.builder.setDefaultLoadingLottiePath(UICoreConfig.INSTANCE.getLoadingLottie());
    }

    public void setDefaultStatusListener(OnStatusRetryClickListener onStatusRetryClickListener) {
        this.builder.setOnStatusRetryClickListener(onStatusRetryClickListener);
    }

    public void build() {
        this.statusLayoutManager = this.builder.build();
        Log.d("status layout", "status layout builder.build statusLayoutManager " + this.statusLayoutManager);
    }

    public void build(StatusLayoutManager.Builder builder) {
        if (builder == null) {
            Log.e("status layout", "status layout builder is null !!!");
        } else {
            this.statusLayoutManager = builder.build();
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICommonViewDelegate
    public void showEmptyLayout() {
        StatusLayoutManager statusLayoutManager = this.statusLayoutManager;
        if (statusLayoutManager == null) {
            Log.e("status layout", "statusLayoutManager is null");
        } else {
            statusLayoutManager.showEmptyLayout();
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICommonViewDelegate
    public void showLoadingLayout() {
        StatusLayoutManager statusLayoutManager = this.statusLayoutManager;
        if (statusLayoutManager == null) {
            Log.e("status layout", "statusLayoutManager is null");
            return;
        }
        try {
            statusLayoutManager.showLoadingLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICommonViewDelegate
    public void showLoadErrorLayout() {
        StatusLayoutManager statusLayoutManager = this.statusLayoutManager;
        if (statusLayoutManager == null) {
            Log.e("status layout", "statusLayoutManager is null");
        } else {
            statusLayoutManager.showLoadErrorLayout();
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICommonViewDelegate
    public void showNetDisconnectLayout() {
        StatusLayoutManager statusLayoutManager = this.statusLayoutManager;
        if (statusLayoutManager == null) {
            Log.e("status layout", "statusLayoutManager is null");
        } else {
            statusLayoutManager.showNetDisconnectLayout();
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICommonViewDelegate
    public void hideStatusLayout() {
        StatusLayoutManager statusLayoutManager = this.statusLayoutManager;
        if (statusLayoutManager == null) {
            Log.e("status layout", "statusLayoutManager is null");
        } else {
            statusLayoutManager.hideStatusLayout();
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICommonViewDelegate
    public void showCustomLayout(int i, OnStatusCustomClickListener onStatusCustomClickListener, int... iArr) {
        StatusLayoutManager statusLayoutManager = this.statusLayoutManager;
        if (statusLayoutManager == null) {
            Log.e("status layout", "statusLayoutManager is null");
        } else {
            statusLayoutManager.showCustomLayout(i, onStatusCustomClickListener, iArr);
        }
    }

    public DefaultStatusLayout getEmptyLayout() {
        if (this.statusLayoutManager == null) {
            Log.e("status layout", "statusLayoutManager is null");
            StatusLayoutManager.Builder builder = this.builder;
            if (builder != null) {
                this.statusLayoutManager = builder.build();
            }
        }
        StatusLayoutManager statusLayoutManager = this.statusLayoutManager;
        if (statusLayoutManager == null) {
            return null;
        }
        return (DefaultStatusLayout) statusLayoutManager.getEmptyLayout();
    }

    public DefaultStatusLayout getLoadErrorLayout() {
        if (this.statusLayoutManager == null) {
            Log.e("status layout", "statusLayoutManager is null");
            StatusLayoutManager.Builder builder = this.builder;
            if (builder != null) {
                this.statusLayoutManager = builder.build();
            }
        }
        StatusLayoutManager statusLayoutManager = this.statusLayoutManager;
        if (statusLayoutManager == null) {
            return null;
        }
        return (DefaultStatusLayout) statusLayoutManager.getLoadErrorLayout();
    }
}
