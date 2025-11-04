package com.wifiled.baselib.uicode.inner;

import android.os.Bundle;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.wifiled.baselib.uicode.statuslayout.OnStatusCustomClickListener;
import com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager;
import com.wifiled.baselib.uicode.statuslayout.StatusLayoutType;
import kotlin.Metadata;

/* compiled from: IBaseView.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0007H'J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\b\u0010\f\u001a\u00020\tH&J\b\u0010\r\u001a\u00020\tH&J\b\u0010\u000e\u001a\u00020\tH&J\b\u0010\u000f\u001a\u00020\u0007H'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H&J(\u0010\u0013\u001a\u00020\t2\b\b\u0001\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162\f\b\u0001\u0010\u0017\u001a\u00020\u0018\"\u00020\u0007H&J\u0010\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0018\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH&¨\u0006\u001eÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/uicode/inner/IBaseView;", "Lcom/wifiled/baselib/uicode/inner/ICoreView;", "enabledDefaultBack", "", "enabledImmersion", "enbaleFixImmersionAndEditBug", "getToolBarLayoutResId", "", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "initData", "initLazyData", "onBackPressed", "getCoverStatusLayoutResId", "buildCustomStatusLayoutView", "Lcom/wifiled/baselib/uicode/statuslayout/StatusLayoutManager$Builder;", "builder", "showCustomLayout", "customLayoutID", "onStatusCustomClickListener", "Lcom/wifiled/baselib/uicode/statuslayout/OnStatusCustomClickListener;", "clickViewID", "", "statusLayoutRetry", "view", "Landroid/view/View;", NotificationCompat.CATEGORY_STATUS, "Lcom/wifiled/baselib/uicode/statuslayout/StatusLayoutType;", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface IBaseView extends ICoreView {
    StatusLayoutManager.Builder buildCustomStatusLayoutView(StatusLayoutManager.Builder builder);

    boolean enabledDefaultBack();

    boolean enabledImmersion();

    boolean enbaleFixImmersionAndEditBug();

    int getCoverStatusLayoutResId();

    int getToolBarLayoutResId();

    void initData();

    void initLazyData();

    void initView(Bundle savedInstanceState);

    void onBackPressed();

    void showCustomLayout(int customLayoutID, OnStatusCustomClickListener onStatusCustomClickListener, int... clickViewID);

    void statusLayoutRetry(View view);

    void statusLayoutRetry(View view, StatusLayoutType status);
}
