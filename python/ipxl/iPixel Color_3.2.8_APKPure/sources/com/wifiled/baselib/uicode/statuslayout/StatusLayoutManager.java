package com.wifiled.baselib.uicode.statuslayout;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.lottie.LottieAnimationView;
import com.wifiled.baselib.R;

/* loaded from: classes2.dex */
public class StatusLayoutManager {
    private View contentLayout;
    private int defaultBackgroundColor;
    private int defaultBackgroundResource;
    private int defaultStatusTextColor;
    private int defaultThemeColor;
    private LayoutInflater inflater;
    private int loadEmptyImgID;
    private View loadEmptyLayout;
    private int loadEmptyLayoutID;
    private String loadEmptyText;
    private int loadErrorImgID;
    private View loadErrorLayout;
    private int loadErrorLayoutID;
    private String loadErrorText;
    private View loadingLayout;
    private int loadingLayoutID;
    private String loadingLottiePath;
    private int netDisconnectImgID;
    private View netDisconnectLayout;
    private int netDisconnectLayoutID;
    private String netDisconnectText;
    private OnStatusRetryClickListener onStatusRetryClickListener;
    private ReplaceLayoutHelper replaceLayoutHelper;

    private StatusLayoutManager(Builder builder) {
        this.contentLayout = builder.contentLayout;
        this.loadingLayoutID = builder.loadingLayoutID;
        this.loadingLayout = builder.loadingLayout;
        this.loadingLottiePath = builder.loadingLottiePath;
        this.loadEmptyLayoutID = builder.loadEmptyLayoutID;
        this.loadEmptyLayout = builder.loadEmptyLayout;
        this.loadEmptyText = builder.loadEmptyText;
        this.loadEmptyImgID = builder.loadEmptyImgID;
        this.loadErrorLayoutID = builder.loadErrorLayoutID;
        this.loadErrorLayout = builder.loadErrorLayout;
        this.loadErrorText = builder.loadErrorText;
        this.loadErrorImgID = builder.loadErrorImgID;
        this.netDisconnectLayoutID = builder.netDisconnectLayoutID;
        this.netDisconnectLayout = builder.netDisconnectLayout;
        this.netDisconnectText = builder.netDisconnectText;
        this.netDisconnectImgID = builder.netDisconnectImgID;
        this.defaultThemeColor = builder.defaultThemeColor;
        this.defaultStatusTextColor = builder.defaultStatusTextColor;
        this.defaultBackgroundColor = builder.defaultBackgroundColor;
        this.defaultBackgroundResource = builder.defaultBackgroundResource;
        this.onStatusRetryClickListener = builder.onStatusRetryClickListener;
        this.replaceLayoutHelper = new ReplaceLayoutHelper(this.contentLayout);
    }

    private View inflate(int i) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(this.contentLayout.getContext());
        }
        return this.inflater.inflate(i, (ViewGroup) null);
    }

    public void hideStatusLayout() {
        this.replaceLayoutHelper.restoreLayout();
    }

    private void createLoadingLayout() {
        if (this.loadingLayout == null) {
            View inflate = inflate(this.loadingLayoutID);
            this.loadingLayout = inflate;
            ((LottieAnimationView) inflate.findViewById(R.id.lottie_loading)).setAnimation(this.loadingLottiePath);
        }
        int i = this.defaultBackgroundColor;
        if (i > 0) {
            this.loadingLayout.setBackgroundColor(i);
            return;
        }
        int i2 = this.defaultBackgroundResource;
        if (i2 > 0) {
            this.loadingLayout.setBackgroundResource(i2);
        }
    }

    public View getLoadingLayout() {
        createLoadingLayout();
        return this.loadingLayout;
    }

    public void showLoadingLayout() {
        createLoadingLayout();
        Log.d("status layout", "status layout showLoadingLayout:" + this.loadingLayout);
        this.replaceLayoutHelper.showStatusLayout(this.loadingLayout);
    }

    private void createLoadEmptyLayout() {
        if (this.loadEmptyLayout == null) {
            this.loadEmptyLayout = inflate(this.loadEmptyLayoutID);
        }
        Log.d("status layout", "status layout create showLoadingLayout !isDefaultLayout :" + (!isDefaultLayout(this.loadEmptyLayout)) + ";loadEmptyText:" + this.loadEmptyText + ";loadEmptyImgID:" + this.loadEmptyImgID);
        if (!isDefaultLayout(this.loadEmptyLayout)) {
            this.loadEmptyLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    StatusLayoutManager statusLayoutManager = StatusLayoutManager.this;
                    statusLayoutManager.setRetryClick(statusLayoutManager.loadEmptyLayout);
                }
            });
        } else {
            setDefaultCommonView(this.loadEmptyLayout, this.loadEmptyText, this.loadEmptyImgID, this.defaultThemeColor, this.defaultStatusTextColor, true);
        }
    }

    public View getEmptyLayout() {
        createLoadEmptyLayout();
        return this.loadEmptyLayout;
    }

    public void showEmptyLayout() {
        createLoadEmptyLayout();
        Log.d("status layout", "status layout showEmptyLayout:" + this.loadEmptyLayout);
        this.replaceLayoutHelper.showStatusLayout(this.loadEmptyLayout);
    }

    private void createLoadErrorLayout() {
        if (this.loadErrorLayout == null) {
            this.loadErrorLayout = inflate(this.loadErrorLayoutID);
        }
        if (!isDefaultLayout(this.loadErrorLayout)) {
            this.loadErrorLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    StatusLayoutManager statusLayoutManager = StatusLayoutManager.this;
                    statusLayoutManager.setRetryClick(statusLayoutManager.loadErrorLayout);
                }
            });
        } else {
            setDefaultCommonView(this.loadErrorLayout, this.loadErrorText, this.loadErrorImgID, this.defaultThemeColor, this.defaultStatusTextColor, true);
        }
    }

    public View getLoadErrorLayout() {
        createLoadErrorLayout();
        return this.loadErrorLayout;
    }

    public void showLoadErrorLayout() {
        createLoadErrorLayout();
        Log.d("status layout", "status layout showLoadErrorLayout:" + this.loadErrorLayout);
        this.replaceLayoutHelper.showStatusLayout(this.loadErrorLayout);
    }

    private void createNetDisconnectLayout() {
        if (this.netDisconnectLayout == null) {
            this.netDisconnectLayout = inflate(this.netDisconnectLayoutID);
        }
        if (isDefaultLayout(this.netDisconnectLayout)) {
            setDefaultCommonView(this.netDisconnectLayout, this.netDisconnectText, this.netDisconnectImgID, this.defaultThemeColor, this.defaultStatusTextColor, true);
        }
    }

    public View getNetDisconnectLayout() {
        createNetDisconnectLayout();
        return this.netDisconnectLayout;
    }

    public void showNetDisconnectLayout() {
        createNetDisconnectLayout();
        this.replaceLayoutHelper.showStatusLayout(this.netDisconnectLayout);
    }

    private boolean isDefaultLayout(View view) {
        return view instanceof DefaultStatusLayout;
    }

    private View setDefaultCommonView(final View view, String str, int i, int i2, int i3, boolean z) {
        int i4 = this.defaultBackgroundColor;
        if (i4 > 0) {
            ((DefaultStatusLayout) view).setDefaultBackgroundColor(i4);
        } else {
            int i5 = this.defaultBackgroundResource;
            if (i5 > 0) {
                ((DefaultStatusLayout) view).setDefaultBackgroundResource(i5);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            ((DefaultStatusLayout) view).setTipText(str);
            Log.d("status layout", "status layout setDefaultCommonView setTipText:" + str);
        }
        if (i > 0) {
            ((DefaultStatusLayout) view).setImage(i);
            Log.d("status layout", "status layout setDefaultCommonView setImage:" + i);
        }
        if (i2 > 0) {
            ((DefaultStatusLayout) view).setRefreshBackgroundColor(i2);
            Log.d("status layout", "status layout setDefaultCommonView setRefreshBackgroundColor:" + i2);
        }
        if (i3 > 0) {
            ((DefaultStatusLayout) view).setTipTextColor(i3);
            Log.d("status layout", "status layout setDefaultCommonView setDefaultStatusTextColor:" + i3);
        }
        if (view == this.loadErrorLayout) {
            DefaultStatusLayout defaultStatusLayout = (DefaultStatusLayout) view;
            defaultStatusLayout.setVisibleRefresh(true);
            if (z) {
                defaultStatusLayout.setErrorClickListener(new View.OnClickListener() { // from class: com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        StatusLayoutManager.this.setRetryClick(view);
                    }
                });
                return view;
            }
        } else if (view == this.loadEmptyLayout) {
            DefaultStatusLayout defaultStatusLayout2 = (DefaultStatusLayout) view;
            defaultStatusLayout2.setVisibleRefresh(false);
            if (z) {
                defaultStatusLayout2.setEmptyClickListener(new View.OnClickListener() { // from class: com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        StatusLayoutManager.this.setRetryClick(view);
                    }
                });
                return view;
            }
        } else if (view == this.netDisconnectLayout) {
            DefaultStatusLayout defaultStatusLayout3 = (DefaultStatusLayout) view;
            defaultStatusLayout3.setVisibleRefresh(true);
            if (z) {
                defaultStatusLayout3.setErrorClickListener(new View.OnClickListener() { // from class: com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        StatusLayoutManager.this.setRetryClick(view);
                    }
                });
            }
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRetryClick(View view) {
        if (this.onStatusRetryClickListener != null) {
            StatusLayoutType statusLayoutType = StatusLayoutType.STATUS_DEFAULT;
            if (view == this.loadErrorLayout) {
                statusLayoutType = StatusLayoutType.STATUS_LOAD_ERROR;
            } else if (view == this.loadEmptyLayout) {
                statusLayoutType = StatusLayoutType.STATUS_EMPTY;
            } else if (view == this.netDisconnectLayout) {
                statusLayoutType = StatusLayoutType.STATUS_NET_DISCONNECT_ERROR;
            }
            this.onStatusRetryClickListener.onClickRetry(view, statusLayoutType);
        }
    }

    public void showCustomLayout(View view) {
        this.replaceLayoutHelper.showStatusLayout(view);
    }

    public View showCustomLayout(int i) {
        View inflate = inflate(i);
        showCustomLayout(inflate);
        return inflate;
    }

    public void showCustomLayout(View view, final OnStatusCustomClickListener onStatusCustomClickListener, int... iArr) {
        this.replaceLayoutHelper.showStatusLayout(view);
        if (onStatusCustomClickListener == null) {
            return;
        }
        for (int i : iArr) {
            View findViewById = view.findViewById(i);
            if (findViewById == null) {
                return;
            }
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    onStatusCustomClickListener.onCustomClick(view2);
                }
            });
        }
    }

    public View showCustomLayout(int i, OnStatusCustomClickListener onStatusCustomClickListener, int... iArr) {
        View inflate = inflate(i);
        showCustomLayout(inflate, onStatusCustomClickListener, iArr);
        return inflate;
    }

    public static final class Builder {
        private View contentLayout;
        private int defaultBackgroundColor;
        private int defaultBackgroundResource;
        private int defaultStatusTextColor;
        private int defaultThemeColor;
        private int loadEmptyImgID;
        private View loadEmptyLayout;
        private String loadEmptyText;
        private int loadErrorImgID;
        private View loadErrorLayout;
        private String loadErrorText;
        private View loadingLayout;
        private String loadingLottiePath;
        private final Resources mResources;
        private int netDisconnectImgID;
        private View netDisconnectLayout;
        private String netDisconnectText;
        private OnStatusRetryClickListener onStatusRetryClickListener;
        private int loadingLayoutID = R.layout.layout_status_layout_manager_loading;
        private int loadEmptyLayoutID = R.layout.status_layout_default_load_empty;
        private int loadErrorLayoutID = R.layout.status_layout_default_load_error;
        private int netDisconnectLayoutID = R.layout.status_layout_default_net_disconnect;

        public OnStatusRetryClickListener getOnStatusRetryClickListener() {
            return this.onStatusRetryClickListener;
        }

        public Builder(View view) {
            this.contentLayout = view;
            this.mResources = view.getContext().getResources();
        }

        public Builder setDefaultLayoutsBackgroundColor(int i) {
            this.defaultBackgroundColor = i;
            return this;
        }

        public Builder setDefaultLayoutsBackgroundResource(int i) {
            this.defaultBackgroundResource = i;
            return this;
        }

        public Builder setOnStatusRetryClickListener(OnStatusRetryClickListener onStatusRetryClickListener) {
            this.onStatusRetryClickListener = onStatusRetryClickListener;
            return this;
        }

        public Builder setLoadingLayout(int i) {
            this.loadingLayoutID = i;
            return this;
        }

        public Builder setLoadingLayout(View view) {
            this.loadingLayout = view;
            return this;
        }

        public Builder setDefaultLoadingLottiePath(String str) {
            this.loadingLottiePath = str;
            return this;
        }

        public Builder setEmptyLayout(int i) {
            this.loadEmptyLayoutID = i;
            return this;
        }

        public Builder setEmptyLayout(View view) {
            this.loadEmptyLayout = view;
            return this;
        }

        public Builder setDefaultEmptyImg(int i) {
            this.loadEmptyImgID = i;
            return this;
        }

        public Builder setDefaultEmptyText(String str) {
            this.loadEmptyText = str;
            return this;
        }

        public Builder setDefaultEmptyText(int i) {
            this.loadEmptyText = this.mResources.getString(i);
            return this;
        }

        public Builder setLoadErrorLayout(int i) {
            this.loadErrorLayoutID = i;
            return this;
        }

        public Builder setLoadErrorLayout(View view) {
            this.loadErrorLayout = view;
            return this;
        }

        public Builder setDefaultLoadErrorText(String str) {
            this.loadErrorText = str;
            return this;
        }

        public Builder setDefaultLoadErrorText(int i) {
            this.loadErrorText = this.mResources.getString(i);
            return this;
        }

        public Builder setDefaultLoadErrorImg(int i) {
            this.loadErrorImgID = i;
            return this;
        }

        public Builder setNetDisconnectLayout(int i) {
            this.netDisconnectLayoutID = i;
            return this;
        }

        public Builder setNetDisconnectLayout(View view) {
            this.netDisconnectLayout = view;
            return this;
        }

        public Builder setDefaultNetDisconnectText(String str) {
            this.netDisconnectText = str;
            return this;
        }

        public Builder setDefaultNetDisconnectText(int i) {
            this.netDisconnectText = this.mResources.getString(i);
            return this;
        }

        public Builder setDefaultNetDisconnectImg(int i) {
            this.netDisconnectImgID = i;
            return this;
        }

        public Builder setDefaultThemeColor(int i) {
            this.defaultThemeColor = i;
            return this;
        }

        public Builder setDefaultStatusTextColor(int i) {
            this.defaultStatusTextColor = i;
            return this;
        }

        public StatusLayoutManager build() {
            return new StatusLayoutManager(this);
        }
    }
}
