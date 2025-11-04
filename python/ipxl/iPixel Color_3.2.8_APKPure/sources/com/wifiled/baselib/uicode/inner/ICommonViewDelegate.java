package com.wifiled.baselib.uicode.inner;

import com.wifiled.baselib.uicode.statuslayout.OnStatusCustomClickListener;
import kotlin.Metadata;

/* compiled from: ICommonViewDelegate.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J*\u0010\n\u001a\u00020\u00032\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\b\u0001\u0010\u000f\u001a\u00020\u0010\"\u00020\fH&¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/uicode/inner/ICommonViewDelegate;", "", "showProgressDialog", "", "hideProgressDialog", "showEmptyLayout", "showLoadingLayout", "showLoadErrorLayout", "showNetDisconnectLayout", "hideStatusLayout", "showCustomLayout", "customLayoutID", "", "onStatusCustomClickListener", "Lcom/wifiled/baselib/uicode/statuslayout/OnStatusCustomClickListener;", "clickViewID", "", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ICommonViewDelegate {
    void hideProgressDialog();

    void hideStatusLayout();

    void showCustomLayout(int customLayoutID, OnStatusCustomClickListener onStatusCustomClickListener, int... clickViewID);

    void showEmptyLayout();

    void showLoadErrorLayout();

    void showLoadingLayout();

    void showNetDisconnectLayout();

    void showProgressDialog();
}
