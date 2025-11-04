package com.wifiled.baselib.uicode.inner;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* compiled from: ICoreView.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0012\u0010\r\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0012\u0010\n\u001a\u00020\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH&J\u0012\u0010\r\u001a\u00020\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH&J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0012\u0010\u0011\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0012\u0010\u0010\u001a\u00020\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH&J\u0012\u0010\u0011\u001a\u00020\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0012\u001a\u00020\u0003H&¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/uicode/inner/ICoreView;", "", "showProgressDialog", "", "hideProgressDialog", "showEmptyLayout", "showLoadingLayout", "showLoadErrorLayout", "showNetDisconnectLayout", "hideStatusLayout", "showToast", NotificationCompat.CATEGORY_MESSAGE, "", "showLongToast", "resId", "", "showCenterToast", "showCenterLongToast", "finishAc", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ICoreView {
    void finishAc();

    void hideProgressDialog();

    void hideStatusLayout();

    void showCenterLongToast(int resId);

    void showCenterLongToast(String msg);

    void showCenterToast(int resId);

    void showCenterToast(String msg);

    void showEmptyLayout();

    void showLoadErrorLayout();

    void showLoadingLayout();

    void showLongToast(int resId);

    void showLongToast(String msg);

    void showNetDisconnectLayout();

    void showProgressDialog();

    void showToast(int resId);

    void showToast(String msg);
}
