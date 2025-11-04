package com.wifiled.baselib.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.wifiled.baselib.utils.HandlerUtils;
import com.wifiled.baselib.utils.ToastUtil;

/* loaded from: classes2.dex */
public abstract class BaseDialog extends Dialog {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected final String TAG;
    public Activity mActivity;
    protected int mLayoutId;

    protected abstract void bindData();

    protected void bindListener() {
    }

    protected abstract void initView();

    protected int layoutId() {
        return 0;
    }

    public BaseDialog(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.mActivity = (Activity) context;
    }

    public BaseDialog(Context context, int i) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.mLayoutId = i;
        this.mActivity = (Activity) context;
    }

    public BaseDialog(Context context, int i, int i2) {
        super(context, i2);
        this.TAG = getClass().getSimpleName();
        this.mActivity = (Activity) context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int layoutId = layoutId();
        this.mLayoutId = layoutId;
        setContentView(View.inflate(this.mActivity, layoutId, null));
        Window window = getWindow();
        window.setLayout(-1, -2);
        window.setBackgroundDrawable(new ColorDrawable(0));
        initView();
        bindData();
        bindListener();
    }

    public void toast(int i) {
        ToastUtil.show(i);
    }

    public void toast(String str) {
        ToastUtil.show(str);
    }

    public void setTimeout(long j, Runnable runnable) {
        HandlerUtils.setTimeout(2, j, runnable);
    }

    public void removeTimeout() {
        HandlerUtils.removeTimeout(2);
    }
}
