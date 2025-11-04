package com.wifiled.baselib.uicode.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wifiled.baselib.uicode.inner.IBaseView;
import com.wifiled.baselib.uicode.statuslayout.DefaultStatusLayout;
import com.wifiled.baselib.uicode.statuslayout.OnStatusCustomClickListener;
import com.wifiled.baselib.uicode.statuslayout.OnStatusRetryClickListener;
import com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager;
import com.wifiled.baselib.uicode.statuslayout.StatusLayoutType;
import com.wifiled.baselib.uicode.utils.FragmentStarter;
import com.wifiled.baselib.uicode.utils.UICoreConfig;
import com.wifiled.baselib.uicode.view.CommonViewDelegate;
import com.wifiled.baselib.uicode.viewmodel.BaseViewModel;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiBaseFragment.kt */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u00052\u00020\u0006B\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000f\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&¢\u0006\u0002\u0010\u0014J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020!H\u0016J\u0012\u0010*\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0002J\b\u0010+\u001a\u00020&H\u0002J&\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00132\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\u001a\u00101\u001a\u00020&2\u0006\u00102\u001a\u00020-2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\r\u00103\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000bJ\u0012\u00104\u001a\u00020&2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\b\u00105\u001a\u00020&H\u0002J\b\u00106\u001a\u00020&H\u0016J\b\u00107\u001a\u00020&H\u0016J\b\u00108\u001a\u000209H\u0016J\b\u0010:\u001a\u00020&H\u0016J\b\u0010;\u001a\u00020!H\u0002J\b\u0010<\u001a\u00020&H\u0016J\u0010\u0010=\u001a\u00020&2\u0006\u0010>\u001a\u00020!H\u0016J\u0010\u0010?\u001a\u00020&2\u0006\u0010@\u001a\u00020!H\u0016J\b\u0010A\u001a\u00020&H\u0016J\u0010\u0010B\u001a\u00020&2\u0006\u0010C\u001a\u00020!H\u0002J\b\u0010D\u001a\u00020&H\u0016J\b\u0010E\u001a\u00020&H\u0002J\b\u0010F\u001a\u00020&H\u0002J\b\u0010G\u001a\u00020&H\u0014J\b\u0010H\u001a\u00020&H\u0014J\b\u0010I\u001a\u00020&H\u0002J\b\u0010J\u001a\u00020&H\u0016J\b\u0010K\u001a\u00020!H\u0016J\b\u0010L\u001a\u00020&H\u0016J\b\u0010M\u001a\u00020&H\u0016J\b\u0010N\u001a\u00020!H\u0016J\b\u0010O\u001a\u00020&H\u0002J\u0010\u0010P\u001a\u00020&2\u0006\u0010Q\u001a\u00020RH\u0002J\b\u0010S\u001a\u00020\u001fH\u0002J\n\u0010T\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010U\u001a\u00020&2\b\u0010V\u001a\u0004\u0018\u00010RH\u0016J\u0012\u0010W\u001a\u00020&2\b\u0010V\u001a\u0004\u0018\u00010RH\u0016J\u0010\u0010U\u001a\u00020&2\u0006\u0010X\u001a\u000209H\u0016J\u0010\u0010W\u001a\u00020&2\u0006\u0010X\u001a\u000209H\u0016J\u0012\u0010Y\u001a\u00020&2\b\u0010V\u001a\u0004\u0018\u00010RH\u0016J\u0012\u0010Z\u001a\u00020&2\b\u0010V\u001a\u0004\u0018\u00010RH\u0016J\u0010\u0010Y\u001a\u00020&2\u0006\u0010X\u001a\u000209H\u0016J\u0010\u0010Z\u001a\u00020&2\u0006\u0010X\u001a\u000209H\u0016J\u0010\u0010[\u001a\u00020&2\u0006\u0010\\\u001a\u00020]H\u0002J\u0018\u0010^\u001a\u00020&2\u0006\u0010_\u001a\u00020!2\u0006\u0010`\u001a\u00020!H\u0016J\b\u0010a\u001a\u00020&H\u0016J\b\u0010b\u001a\u00020&H\u0016J\u0010\u0010c\u001a\u00020&2\u0006\u0010d\u001a\u00020-H\u0002J\u0010\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020fH\u0016J\u0010\u0010h\u001a\u00020&2\u0006\u00102\u001a\u00020-H\u0016J\u0018\u0010h\u001a\u00020&2\u0006\u00102\u001a\u00020-2\u0006\u0010i\u001a\u00020jH\u0016J\b\u0010k\u001a\u00020&H\u0016J\b\u0010l\u001a\u00020&H\u0016J\b\u0010m\u001a\u00020&H\u0016J\b\u0010n\u001a\u00020&H\u0016J\b\u0010o\u001a\u00020&H\u0016J(\u0010p\u001a\u00020&2\b\b\u0001\u0010q\u001a\u0002092\u0006\u0010r\u001a\u00020s2\f\b\u0001\u0010t\u001a\u00020u\"\u000209H\u0016J\n\u0010v\u001a\u0004\u0018\u00010wH\u0016J\n\u0010x\u001a\u0004\u0018\u00010wH\u0016J\n\u0010{\u001a\u0004\u0018\u00010zH\u0016J\u0010\u0010|\u001a\u00020&2\b\u0010}\u001a\u0004\u0018\u00010zJ\u0012\u0010~\u001a\u00020&2\b\u0010}\u001a\u0004\u0018\u00010zH\u0016J\u0013\u0010\u007f\u001a\u00020&2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0005H\u0016J\u001e\u0010\u007f\u001a\u00020&2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00052\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010RH\u0016R\u001c\u0010\t\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0015\u001a\u00028\u0001X\u0084.¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010y\u001a\u0004\u0018\u00010zX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0082\u0001"}, d2 = {"Lcom/wifiled/baselib/uicode/base/UiBaseFragment;", "VM", "Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;", "VB", "Landroidx/viewbinding/ViewBinding;", "Landroidx/fragment/app/Fragment;", "Lcom/wifiled/baselib/uicode/inner/IBaseView;", "<init>", "()V", "viewModel", "getViewModel", "()Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;", "setViewModel", "(Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;)V", "Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;)Landroidx/viewbinding/ViewBinding;", "binding", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "setBinding", "(Landroidx/viewbinding/ViewBinding;)V", "Landroidx/viewbinding/ViewBinding;", "mViewRef", "Ljava/lang/ref/Reference;", "Landroid/app/Activity;", "mComponentView", "Lcom/wifiled/baselib/uicode/view/CommonViewDelegate;", "isViewDestroy", "", "isFVisible", "isInitLazyData", "isAttachViewModelOk", "onAttach", "", "context", "Landroid/content/Context;", "processBackPressed", "onAttachToContext", "attachViewModelAndLifecycle", "onCreateView", "Landroid/view/View;", "viewGroup", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "initViewModel", "initView", "initUIChangeLiveDataCallBack", "registorUIChangeLiveDataCallBack", "initData", "getCoverStatusLayoutResId", "", "finishAc", "isActivityDestroyed", "onResume", "setUserVisibleHint", "isVisibleToUser", "onHiddenChanged", "hidden", "onPause", "setUserVisible", "isUserVisible", "onBackPressed", "onUserVisible", "onUserVisibleHint", "onInVisible", "onVisible", "doInitLazyData", "initLazyData", "isViewDestroyed", "onDestroyView", "onDestroy", "enabledDefaultBack", "initBaseCommonComponentView", "log", "args", "", "getViewDelegate", "getBaseActivity", "showToast", NotificationCompat.CATEGORY_MESSAGE, "showLongToast", "resId", "showCenterToast", "showCenterLongToast", "catchThrowable", "e", "", "setProgressDialogCancel", "onTouchOutside", "backCancel", "showProgressDialog", "hideProgressDialog", "buildStatusView", "contentView", "buildCustomStatusLayoutView", "Lcom/wifiled/baselib/uicode/statuslayout/StatusLayoutManager$Builder;", "builder", "statusLayoutRetry", NotificationCompat.CATEGORY_STATUS, "Lcom/wifiled/baselib/uicode/statuslayout/StatusLayoutType;", "showEmptyLayout", "showLoadingLayout", "showLoadErrorLayout", "showNetDisconnectLayout", "hideStatusLayout", "showCustomLayout", "customLayoutID", "onStatusCustomClickListener", "Lcom/wifiled/baselib/uicode/statuslayout/OnStatusCustomClickListener;", "clickViewID", "", "getDefaultEmptyLayout", "Lcom/wifiled/baselib/uicode/statuslayout/DefaultStatusLayout;", "getDefaultLoadErrorLayout", "mDataIn", "", "getDataIn", "onEnterWithData", "data", "onBackWithData", "startFragment", "toFragment", "tag", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class UiBaseFragment<VM extends BaseViewModel, VB extends ViewBinding> extends Fragment implements IBaseView {
    protected VB binding;
    private boolean isAttachViewModelOk;
    private boolean isFVisible;
    private boolean isInitLazyData;
    private boolean isViewDestroy;
    private CommonViewDelegate mComponentView;
    private Object mDataIn;
    private Reference<Activity> mViewRef;
    public VM viewModel;

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public StatusLayoutManager.Builder buildCustomStatusLayoutView(StatusLayoutManager.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return builder;
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public boolean enabledDefaultBack() {
        return false;
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public int getCoverStatusLayoutResId() {
        return 0;
    }

    public abstract VB getViewBinding(LayoutInflater inflater, ViewGroup container);

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public void initData() {
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public void initLazyData() {
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public void initView(Bundle savedInstanceState) {
    }

    public abstract VM initViewModel();

    public void onBackWithData(Object data) {
    }

    protected void onInVisible() {
    }

    protected void onVisible() {
    }

    public boolean processBackPressed() {
        return false;
    }

    public void registorUIChangeLiveDataCallBack() {
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public void statusLayoutRetry(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public void statusLayoutRetry(View view, StatusLayoutType status) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(status, "status");
    }

    public final VM getViewModel() {
        VM vm = this.viewModel;
        if (vm != null) {
            return vm;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(VM vm) {
        Intrinsics.checkNotNullParameter(vm, "<set-?>");
        this.viewModel = vm;
    }

    protected final VB getBinding() {
        VB vb = this.binding;
        if (vb != null) {
            return vb;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    protected final void setBinding(VB vb) {
        Intrinsics.checkNotNullParameter(vb, "<set-?>");
        this.binding = vb;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        onAttachToContext(context);
    }

    private final void onAttachToContext(Context context) {
        try {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            this.mViewRef = new WeakReference((Activity) context);
            attachViewModelAndLifecycle();
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    private final void attachViewModelAndLifecycle() {
        setViewModel(initViewModel());
        getLifecycle().addObserver(getViewModel());
        this.isAttachViewModelOk = true;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        initBaseCommonComponentView();
        setBinding(getViewBinding(inflater, viewGroup));
        return getBinding().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        try {
            if (isActivityDestroyed()) {
                log("onViewCreated fragment刚创建，Activity就destroy了！");
                return;
            }
            initView(savedInstanceState);
            initUIChangeLiveDataCallBack();
            initData();
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    private final void initUIChangeLiveDataCallBack() {
        getViewModel().getShowProgressDialogEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.showProgressDialog();
            }
        });
        getViewModel().getHideProgressDialogEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda13
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.hideProgressDialog();
            }
        });
        getViewModel().getShowEmptyLayoutEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda14
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.showEmptyLayout();
            }
        });
        getViewModel().getShowLoadingLayoutEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda15
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.showLoadingLayout();
            }
        });
        getViewModel().getShowLoadErrorLayoutEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.showLoadErrorLayout();
            }
        });
        getViewModel().getShowNetDisconnectLayoutEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.showNetDisconnectLayout();
            }
        });
        getViewModel().getHideStatusLayoutEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.hideStatusLayout();
            }
        });
        getViewModel().getShowToastStrEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.showToast((String) obj);
            }
        });
        getViewModel().getShowLongToastStrEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.showLongToast((String) obj);
            }
        });
        getViewModel().getShowToastResEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.initUIChangeLiveDataCallBack$lambda$9(UiBaseFragment.this, (Integer) obj);
            }
        });
        getViewModel().getShowLongToastResEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.initUIChangeLiveDataCallBack$lambda$10(UiBaseFragment.this, (Integer) obj);
            }
        });
        getViewModel().getShowCenterToastStrEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.showCenterToast((String) obj);
            }
        });
        getViewModel().getShowCenterLongToastStrEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.showCenterLongToast((String) obj);
            }
        });
        getViewModel().getShowCenterToastResEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda10
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.initUIChangeLiveDataCallBack$lambda$13(UiBaseFragment.this, (Integer) obj);
            }
        });
        getViewModel().getShowCenterLongToastResEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda11
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.initUIChangeLiveDataCallBack$lambda$14(UiBaseFragment.this, (Integer) obj);
            }
        });
        getViewModel().getFinishAcEvent().observe(getViewLifecycleOwner(), new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$$ExternalSyntheticLambda12
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseFragment.this.finishAc();
            }
        });
        registorUIChangeLiveDataCallBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initUIChangeLiveDataCallBack$lambda$9(UiBaseFragment uiBaseFragment, Integer num) {
        Intrinsics.checkNotNull(num);
        uiBaseFragment.showToast(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initUIChangeLiveDataCallBack$lambda$10(UiBaseFragment uiBaseFragment, Integer num) {
        Intrinsics.checkNotNull(num);
        uiBaseFragment.showLongToast(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initUIChangeLiveDataCallBack$lambda$13(UiBaseFragment uiBaseFragment, Integer num) {
        Intrinsics.checkNotNull(num);
        uiBaseFragment.showCenterToast(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initUIChangeLiveDataCallBack$lambda$14(UiBaseFragment uiBaseFragment, Integer num) {
        Intrinsics.checkNotNull(num);
        uiBaseFragment.showCenterLongToast(num.intValue());
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void finishAc() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    private final boolean isActivityDestroyed() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity.isDestroyed();
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        try {
            log("fragment onResume:" + super.isResumed() + ";isVisible:" + super.isVisible() + ";isHidden:" + super.isHidden() + ";getUserVisibleHint:" + super.getUserVisibleHint() + ";isAdded:" + super.isAdded());
            setUserVisible(getUserVisibleHint());
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            log("fragment visible setUserVisibleHint:" + super.isResumed() + ";" + super.isVisible() + ";" + super.isHidden() + ";" + super.getUserVisibleHint() + ";" + super.isAdded());
            setUserVisible(isVisibleToUser);
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        try {
            setUserVisible(!hidden);
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        try {
            log("fragment visible onPause:" + super.isResumed() + ";" + super.isVisible() + ";" + super.isHidden() + ";" + super.getUserVisibleHint() + ";" + super.isAdded());
            setUserVisible(false);
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    private final synchronized void setUserVisible(boolean isUserVisible) {
        if (this.isAttachViewModelOk) {
            log("[setUserVisible]isUserVisible:[" + isUserVisible + "]");
            if (isUserVisible) {
                if (!this.isFVisible) {
                    this.isFVisible = true;
                    onUserVisible();
                }
            } else if (this.isFVisible) {
                this.isFVisible = false;
                onUserVisibleHint();
            }
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public void onBackPressed() {
        try {
            if (getBaseActivity() == null || !(getBaseActivity() instanceof UiBaseActivity)) {
                return;
            }
            Activity baseActivity = getBaseActivity();
            Intrinsics.checkNotNull(baseActivity);
            baseActivity.onBackPressed();
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    private final void onUserVisible() {
        log("[onUserVisible]");
        onVisible();
        doInitLazyData();
    }

    private final void onUserVisibleHint() {
        log("[onUserVisibleHint]");
        onInVisible();
    }

    private final void doInitLazyData() {
        if (this.isInitLazyData) {
            return;
        }
        this.isInitLazyData = true;
    }

    /* renamed from: isViewDestroyed, reason: from getter */
    public boolean getIsViewDestroy() {
        return this.isViewDestroy;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.isFVisible = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.isViewDestroy = true;
        super.onDestroy();
    }

    private final void initBaseCommonComponentView() {
        this.mComponentView = new CommonViewDelegate(getContext());
    }

    private final void log(String args) {
        LogUtils.i("BaseFragment", getClass().getSimpleName() + " " + args);
    }

    private final CommonViewDelegate getViewDelegate() {
        if (this.mComponentView == null) {
            this.mComponentView = new CommonViewDelegate(getContext());
        }
        CommonViewDelegate commonViewDelegate = this.mComponentView;
        Intrinsics.checkNotNull(commonViewDelegate);
        return commonViewDelegate;
    }

    public Activity getBaseActivity() {
        Reference<Activity> reference = this.mViewRef;
        if (reference == null) {
            return null;
        }
        Intrinsics.checkNotNull(reference);
        return reference.get();
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showToast(String msg) {
        if (msg != null) {
            ToastUtils.showShort(msg, new Object[0]);
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showLongToast(String msg) {
        if (msg != null) {
            ToastUtils.showLong(msg, new Object[0]);
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showToast(int resId) {
        ToastUtils.showShort(resId);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showLongToast(int resId) {
        ToastUtils.showLong(resId);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showCenterToast(String msg) {
        if (msg != null) {
            ToastUtils.make().setGravity(17, 0, 0);
            ToastUtils.showShort(msg, new Object[0]);
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showCenterLongToast(String msg) {
        if (msg != null) {
            ToastUtils.make().setGravity(17, 0, 0);
            ToastUtils.showLong(msg, new Object[0]);
        }
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showCenterToast(int resId) {
        ToastUtils.make().setGravity(17, 0, 0);
        ToastUtils.showShort(resId);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showCenterLongToast(int resId) {
        ToastUtils.make().setGravity(17, 0, 0);
        ToastUtils.showLong(resId);
    }

    private final void catchThrowable(Throwable e) {
        UICoreConfig.INSTANCE.throwable(e);
    }

    public void setProgressDialogCancel(boolean onTouchOutside, boolean backCancel) {
        if (getIsViewDestroy()) {
            return;
        }
        getViewDelegate().setProgressDialogCanceled(onTouchOutside, backCancel);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showProgressDialog() {
        if (getIsViewDestroy()) {
            return;
        }
        getViewDelegate().showProgressDialog();
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void hideProgressDialog() {
        if (getIsViewDestroy()) {
            return;
        }
        getViewDelegate().hideProgressDialog();
    }

    private final void buildStatusView(View contentView) {
        log("[buildStatusView]contentView：" + contentView);
        getViewDelegate().initStatusLayout(contentView);
        StatusLayoutManager.Builder statusLayoutManagerBuilder = getViewDelegate().getStatusLayoutManagerBuilder();
        Intrinsics.checkNotNull(statusLayoutManagerBuilder);
        StatusLayoutManager.Builder buildCustomStatusLayoutView = buildCustomStatusLayoutView(statusLayoutManagerBuilder);
        if (buildCustomStatusLayoutView.getOnStatusRetryClickListener() == null) {
            getViewDelegate().setDefaultStatusListener(new OnStatusRetryClickListener(this) { // from class: com.wifiled.baselib.uicode.base.UiBaseFragment$buildStatusView$1
                final /* synthetic */ UiBaseFragment<VM, VB> this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.wifiled.baselib.uicode.statuslayout.OnStatusRetryClickListener
                public void onClickRetry(View view, StatusLayoutType status) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(status, "status");
                    this.this$0.statusLayoutRetry(view, status);
                }
            });
        }
        getViewDelegate().build(buildCustomStatusLayoutView);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showEmptyLayout() {
        getViewDelegate().showEmptyLayout();
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showLoadingLayout() {
        getViewDelegate().showLoadingLayout();
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showLoadErrorLayout() {
        getViewDelegate().showLoadErrorLayout();
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showNetDisconnectLayout() {
        getViewDelegate().showNetDisconnectLayout();
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void hideStatusLayout() {
        getViewDelegate().hideStatusLayout();
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public void showCustomLayout(int customLayoutID, OnStatusCustomClickListener onStatusCustomClickListener, int... clickViewID) {
        Intrinsics.checkNotNullParameter(onStatusCustomClickListener, "onStatusCustomClickListener");
        Intrinsics.checkNotNullParameter(clickViewID, "clickViewID");
        getViewDelegate().showCustomLayout(customLayoutID, onStatusCustomClickListener, Arrays.copyOf(clickViewID, clickViewID.length));
    }

    public DefaultStatusLayout getDefaultEmptyLayout() {
        return getViewDelegate().getEmptyLayout();
    }

    public DefaultStatusLayout getDefaultLoadErrorLayout() {
        return getViewDelegate().getLoadErrorLayout();
    }

    /* renamed from: getDataIn, reason: from getter */
    public Object getMDataIn() {
        return this.mDataIn;
    }

    public final void onEnterWithData(Object data) {
        this.mDataIn = data;
    }

    public void startFragment(Fragment toFragment) {
        try {
            startFragment(toFragment, null);
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    public void startFragment(Fragment toFragment, String tag) {
        FragmentStarter.startFragment(this, toFragment, tag);
    }
}
