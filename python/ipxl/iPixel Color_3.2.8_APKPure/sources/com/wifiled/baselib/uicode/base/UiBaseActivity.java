package com.wifiled.baselib.uicode.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wifiled.baselib.R;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.uicode.inner.IBaseView;
import com.wifiled.baselib.uicode.statuslayout.DefaultStatusLayout;
import com.wifiled.baselib.uicode.statuslayout.OnStatusCustomClickListener;
import com.wifiled.baselib.uicode.statuslayout.OnStatusRetryClickListener;
import com.wifiled.baselib.uicode.statuslayout.StatusLayoutManager;
import com.wifiled.baselib.uicode.statuslayout.StatusLayoutType;
import com.wifiled.baselib.uicode.utils.FragmentStarter;
import com.wifiled.baselib.uicode.utils.OSUtils;
import com.wifiled.baselib.uicode.utils.UICoreConfig;
import com.wifiled.baselib.uicode.view.CommonViewDelegate;
import com.wifiled.baselib.uicode.viewmodel.BaseViewModel;
import com.wifiled.baselib.utils.SPUtils;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiBaseActivity.kt */
@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0003\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u00052\u00020\u0006B\u0007¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\u0017\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0018J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\u0012\u0010#\u001a\u00020 2\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\nH\u0002J\b\u0010)\u001a\u00020 H\u0016J\b\u0010*\u001a\u00020 H\u0002J\b\u0010+\u001a\u00020 H\u0016J\b\u0010,\u001a\u00020\u0013H\u0002J\b\u0010-\u001a\u00020 H\u0002J\b\u0010.\u001a\u00020 H\u0016J\b\u0010/\u001a\u00020 H\u0016J\b\u00100\u001a\u00020 H\u0014J\b\u00101\u001a\u00020 H\u0014J\b\u00102\u001a\u00020 H\u0014J\b\u00103\u001a\u00020\u0015H\u0016J\r\u00104\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000eJ\b\u00105\u001a\u000206H\u0014J\b\u00107\u001a\u00020 H\u0002J\b\u00108\u001a\u000206H\u0016J\b\u00109\u001a\u00020\u0015H\u0016J\u0012\u0010:\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010=\u001a\u00020\u0015H\u0014J\b\u0010>\u001a\u00020 H\u0016J\u0006\u0010?\u001a\u00020 J\b\u0010@\u001a\u00020 H\u0002J\u0012\u0010A\u001a\u00020 2\b\u0010B\u001a\u0004\u0018\u00010\nH\u0014J\u0012\u0010C\u001a\u00020 2\b\u0010D\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010E\u001a\u00020 2\b\u0010D\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010C\u001a\u00020 2\u0006\u0010F\u001a\u000206H\u0016J\u0010\u0010E\u001a\u00020 2\u0006\u0010F\u001a\u000206H\u0016J\u0012\u0010G\u001a\u00020 2\b\u0010D\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010H\u001a\u00020 2\b\u0010D\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010G\u001a\u00020 2\u0006\u0010F\u001a\u000206H\u0016J\u0010\u0010H\u001a\u00020 2\u0006\u0010F\u001a\u000206H\u0016J\b\u0010I\u001a\u00020 H\u0016J\u0010\u0010J\u001a\u00020 2\u0006\u0010K\u001a\u00020LH\u0002J\u0006\u0010M\u001a\u00020\u0015J\b\u0010N\u001a\u00020\u0015H\u0016J\b\u0010O\u001a\u000206H\u0016J\u0018\u0010P\u001a\u00020 2\u0006\u0010Q\u001a\u0002062\u0006\u0010N\u001a\u00020\u0015H\u0016J\u0018\u0010R\u001a\u00020 2\u0006\u0010Q\u001a\u0002062\u0006\u0010N\u001a\u00020\u0015H\u0016J0\u0010S\u001a\u00020 2\u0006\u0010Q\u001a\u0002062\u0006\u0010T\u001a\u00020\u00152\u0006\u0010U\u001a\u0002062\u0006\u0010V\u001a\u0002062\u0006\u0010N\u001a\u00020\u0015H\u0002J\u0010\u0010W\u001a\u00020 2\u0006\u0010N\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\b\u0010X\u001a\u00020 H\u0016J\u0018\u0010Y\u001a\u00020 2\u0006\u0010Z\u001a\u00020\u00152\u0006\u0010[\u001a\u00020\u0015H\u0016J\b\u0010\\\u001a\u00020 H\u0016J\u0010\u0010]\u001a\u00020 2\u0006\u0010^\u001a\u00020_H\u0002J\u0010\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020aH\u0016J\u0010\u0010c\u001a\u00020 2\u0006\u0010d\u001a\u00020_H\u0016J\u0018\u0010c\u001a\u00020 2\u0006\u0010d\u001a\u00020_2\u0006\u0010e\u001a\u00020fH\u0016J\b\u0010g\u001a\u00020 H\u0016J\b\u0010h\u001a\u00020 H\u0016J\b\u0010i\u001a\u00020 H\u0016J\b\u0010j\u001a\u00020 H\u0016J\n\u0010k\u001a\u0004\u0018\u00010lH\u0016J\n\u0010m\u001a\u0004\u0018\u00010lH\u0016J\b\u0010n\u001a\u00020 H\u0016J(\u0010o\u001a\u00020 2\b\b\u0001\u0010p\u001a\u0002062\u0006\u0010q\u001a\u00020r2\f\b\u0001\u0010s\u001a\u00020t\"\u000206H\u0016J\u0012\u0010w\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010vH\u0016J6\u0010y\u001a\u00020 2\b\b\u0001\u0010z\u001a\u0002062\u0018\u0010{\u001a\u0014\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030v\u0018\u00010|2\b\u0010}\u001a\u0004\u0018\u00010~H\u0016J,\u0010y\u001a\u00020 2\u0018\u0010{\u001a\u0014\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030v\u0018\u00010|2\b\u0010}\u001a\u0004\u0018\u00010~H\u0016J\u0012\u0010\u007f\u001a\u00020 2\b\u0010}\u001a\u0004\u0018\u00010~H\u0016J\t\u0010\u0080\u0001\u001a\u00020\u0015H\u0002R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00028\u00018DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001a\u0010\u0018R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010u\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010vX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0081\u0001"}, d2 = {"Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "VM", "Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;", "VB", "Landroidx/viewbinding/ViewBinding;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/wifiled/baselib/uicode/inner/IBaseView;", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "viewModel", "getViewModel", "()Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;", "setViewModel", "(Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;)V", "Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;", "componentView", "Lcom/wifiled/baselib/uicode/view/CommonViewDelegate;", "isViewDestroy", "", "isActivityVisible", "getViewBinding", "()Landroidx/viewbinding/ViewBinding;", "binding", "getBinding", "binding$delegate", "Lkotlin/Lazy;", "getResources", "Landroid/content/res/Resources;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "attachBaseContext", "newBase", "Landroid/content/Context;", "getLocale", "Ljava/util/Locale;", Constance.SP.LANGUAGE, "setTranslucentStatus", "attachViewModelAndLifecycle", "initLazyData", "getViewDelegate", "initUIChangeLiveDataCallBack", "registorUIChangeLiveDataCallBack", "initData", "onResume", "onPause", "onDestroy", "isViewDestroyed", "initViewModel", "getFragmentContainerId", "", "initContentView", "getCoverStatusLayoutResId", "enabledDefaultBack", "initView", "closeToast", "Landroid/widget/Toast;", "processBackPressed", "onBackPressed", "back", "doReturnBack", "log", "args", "showToast", NotificationCompat.CATEGORY_MESSAGE, "showLongToast", "resId", "showCenterToast", "showCenterLongToast", "finishAc", "catchThrowable", "e", "", "enabledImmersion", "enbaleFixImmersionAndEditBug", "getToolBarLayoutResId", "setWhiteFakeStatus", "contentParentViewId", "setTransparentStatus", "setFakeStatus", "isLightMode", "alpha", "statuBgResource", "fixImmersionAndEditBug", "showProgressDialog", "setProgressDialogCancel", "onTouchOutside", "backCancel", "hideProgressDialog", "buildStatusView", "contentView", "Landroid/view/View;", "buildCustomStatusLayoutView", "Lcom/wifiled/baselib/uicode/statuslayout/StatusLayoutManager$Builder;", "builder", "statusLayoutRetry", "view", NotificationCompat.CATEGORY_STATUS, "Lcom/wifiled/baselib/uicode/statuslayout/StatusLayoutType;", "showEmptyLayout", "showLoadingLayout", "showLoadErrorLayout", "showNetDisconnectLayout", "getDefaultEmptyLayout", "Lcom/wifiled/baselib/uicode/statuslayout/DefaultStatusLayout;", "getDefaultLoadErrorLayout", "hideStatusLayout", "showCustomLayout", "customLayoutID", "onStatusCustomClickListener", "Lcom/wifiled/baselib/uicode/statuslayout/OnStatusCustomClickListener;", "clickViewID", "", "mCurrentFragment", "Lcom/wifiled/baselib/uicode/base/UiBaseFragment;", "getCurrentFragment", "mCloseWarned", "pushFragmentToBackStack", "containerId", "cls", "Ljava/lang/Class;", "data", "", "popTopFragment", "tryToUpdateCurrentAfterPop", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class UiBaseActivity<VM extends BaseViewModel, VB extends ViewBinding> extends AppCompatActivity implements IBaseView {
    private Toast closeToast;
    private CommonViewDelegate componentView;
    private boolean isViewDestroy;
    private boolean mCloseWarned;
    private UiBaseFragment<?, ?> mCurrentFragment;
    public VM viewModel;
    private final String TAG = getClass().getSimpleName();
    private boolean isActivityVisible = true;

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding = LazyKt.lazy(new Function0() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ViewBinding viewBinding;
            viewBinding = UiBaseActivity.this.getViewBinding();
            return viewBinding;
        }
    });

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public StatusLayoutManager.Builder buildCustomStatusLayoutView(StatusLayoutManager.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return builder;
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public boolean enabledDefaultBack() {
        return true;
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public final boolean enabledImmersion() {
        return true;
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public boolean enbaleFixImmersionAndEditBug() {
        return false;
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public int getCoverStatusLayoutResId() {
        return 0;
    }

    protected int getFragmentContainerId() {
        return 0;
    }

    public abstract VB getViewBinding();

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

    protected boolean processBackPressed() {
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

    /* JADX INFO: Access modifiers changed from: protected */
    public final VB getBinding() {
        return (VB) this.binding.getValue();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        Intrinsics.checkNotNull(resources);
        return resources;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable$default(this, null, null, 3, null);
        super.onCreate(savedInstanceState);
        try {
            attachViewModelAndLifecycle();
            initContentView();
            setTranslucentStatus();
            initView(savedInstanceState);
            initUIChangeLiveDataCallBack();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        if (newBase == null) {
            super.attachBaseContext(newBase);
            return;
        }
        String string = newBase.getSharedPreferences(SPUtils.FILE_NAME, 0).getString(Constance.SP.LANGUAGE, Locale.getDefault().getLanguage());
        Locale locale = string != null ? getLocale(string) : null;
        final Configuration configuration = newBase.getResources().getConfiguration();
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        final Context createConfigurationContext = newBase.createConfigurationContext(configuration);
        final int i = R.style.Theme_AppCompat_Empty;
        super.attachBaseContext(new ContextThemeWrapper(createConfigurationContext, i) { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$attachBaseContext$wrappedContext$1
            @Override // androidx.appcompat.view.ContextThemeWrapper
            public void applyOverrideConfiguration(Configuration overrideConfiguration) {
                Intrinsics.checkNotNullParameter(overrideConfiguration, "overrideConfiguration");
                overrideConfiguration.setTo(configuration);
                super.applyOverrideConfiguration(overrideConfiguration);
            }
        });
    }

    private final Locale getLocale(String language) {
        Locale locale;
        Locale.getDefault();
        int hashCode = language.hashCode();
        if (hashCode == 3201) {
            if (language.equals("de")) {
                locale = Locale.GERMANY;
            }
            locale = new Locale(language);
        } else if (hashCode == 3241) {
            if (language.equals("en")) {
                locale = Locale.ENGLISH;
            }
            locale = new Locale(language);
        } else if (hashCode == 3276) {
            if (language.equals("fr")) {
                locale = Locale.FRENCH;
            }
            locale = new Locale(language);
        } else if (hashCode == 3371) {
            if (language.equals("it")) {
                locale = Locale.ITALY;
            }
            locale = new Locale(language);
        } else if (hashCode == 3383) {
            if (language.equals("ja")) {
                locale = Locale.JAPAN;
            }
            locale = new Locale(language);
        } else if (hashCode == 3428) {
            if (language.equals("ko")) {
                locale = Locale.KOREA;
            }
            locale = new Locale(language);
        } else if (hashCode == 3886) {
            if (language.equals("zh")) {
                locale = Locale.SIMPLIFIED_CHINESE;
            }
            locale = new Locale(language);
        } else if (hashCode != 106935481) {
            if (hashCode == 115861812 && language.equals("zh_TW")) {
                locale = Locale.TRADITIONAL_CHINESE;
            }
            locale = new Locale(language);
        } else {
            if (language.equals("pt-BR")) {
                locale = new Locale("pt", "BR");
            }
            locale = new Locale(language);
        }
        Intrinsics.checkNotNull(locale);
        return locale;
    }

    public void setTranslucentStatus() {
        Window window = getWindow();
        window.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        window.getDecorView().setSystemUiVisibility(1040);
        window.addFlags(Integer.MIN_VALUE);
    }

    private final void attachViewModelAndLifecycle() {
        setViewModel(initViewModel());
        getLifecycle().addObserver(getViewModel());
    }

    private final CommonViewDelegate getViewDelegate() {
        if (this.componentView == null) {
            this.componentView = new CommonViewDelegate(this);
        }
        CommonViewDelegate commonViewDelegate = this.componentView;
        Intrinsics.checkNotNull(commonViewDelegate);
        return commonViewDelegate;
    }

    private final void initUIChangeLiveDataCallBack() {
        UiBaseActivity<VM, VB> uiBaseActivity = this;
        getViewModel().getShowProgressDialogEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.showProgressDialog();
            }
        });
        getViewModel().getHideProgressDialogEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda16
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.hideProgressDialog();
            }
        });
        getViewModel().getShowEmptyLayoutEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda17
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.showEmptyLayout();
            }
        });
        getViewModel().getShowLoadingLayoutEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.showLoadingLayout();
            }
        });
        getViewModel().getShowNetDisconnectLayoutEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.showNetDisconnectLayout();
            }
        });
        getViewModel().getShowLoadErrorLayoutEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.showLoadErrorLayout();
            }
        });
        getViewModel().getHideStatusLayoutEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.hideStatusLayout();
            }
        });
        getViewModel().getShowToastStrEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.showToast((String) obj);
            }
        });
        getViewModel().getShowLongToastStrEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.showLongToast((String) obj);
            }
        });
        getViewModel().getShowToastResEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.initUIChangeLiveDataCallBack$lambda$11(UiBaseActivity.this, (Integer) obj);
            }
        });
        getViewModel().getShowLongToastResEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda10
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.initUIChangeLiveDataCallBack$lambda$12(UiBaseActivity.this, (Integer) obj);
            }
        });
        getViewModel().getShowCenterToastStrEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda11
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.showCenterToast((String) obj);
            }
        });
        getViewModel().getShowCenterLongToastStrEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda12
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.showCenterLongToast((String) obj);
            }
        });
        getViewModel().getShowCenterToastResEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda13
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.initUIChangeLiveDataCallBack$lambda$15(UiBaseActivity.this, (Integer) obj);
            }
        });
        getViewModel().getShowCenterLongToastResEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda14
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.initUIChangeLiveDataCallBack$lambda$16(UiBaseActivity.this, (Integer) obj);
            }
        });
        getViewModel().getFinishAcEvent().observe(uiBaseActivity, new Observer() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda15
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UiBaseActivity.this.finishAc();
            }
        });
        registorUIChangeLiveDataCallBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initUIChangeLiveDataCallBack$lambda$11(UiBaseActivity uiBaseActivity, Integer num) {
        Intrinsics.checkNotNull(num);
        uiBaseActivity.showToast(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initUIChangeLiveDataCallBack$lambda$12(UiBaseActivity uiBaseActivity, Integer num) {
        Intrinsics.checkNotNull(num);
        uiBaseActivity.showLongToast(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initUIChangeLiveDataCallBack$lambda$15(UiBaseActivity uiBaseActivity, Integer num) {
        Intrinsics.checkNotNull(num);
        uiBaseActivity.showCenterToast(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initUIChangeLiveDataCallBack$lambda$16(UiBaseActivity uiBaseActivity, Integer num) {
        Intrinsics.checkNotNull(num);
        uiBaseActivity.showCenterLongToast(num.intValue());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.isActivityVisible = true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isActivityVisible = false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        try {
            hideProgressDialog();
            this.isViewDestroy = true;
            this.isActivityVisible = false;
            super.onDestroy();
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    /* renamed from: isViewDestroyed, reason: from getter */
    public boolean getIsViewDestroy() {
        return this.isViewDestroy;
    }

    private final void initContentView() {
        setContentView(getBinding().getRoot());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        if (processBackPressed()) {
            return;
        }
        back();
    }

    public final void back() {
        boolean z;
        UiBaseFragment<?, ?> uiBaseFragment = this.mCurrentFragment;
        if (uiBaseFragment != null) {
            Intrinsics.checkNotNull(uiBaseFragment);
            z = !uiBaseFragment.processBackPressed();
        } else {
            z = true;
        }
        log(" getBackStackEntryCount  " + getSupportFragmentManager().getBackStackEntryCount() + "   " + z + ";mCurrentFragment:" + this.mCurrentFragment);
        if (z) {
            if (getSupportFragmentManager().getBackStackEntryCount() <= 1 && isTaskRoot()) {
                if (!this.mCloseWarned) {
                    if (!TextUtils.isEmpty(r0)) {
                        Toast makeText = Toast.makeText(getApplicationContext(), r0, 0);
                        this.closeToast = makeText;
                        Intrinsics.checkNotNull(makeText);
                        makeText.show();
                        this.mCloseWarned = true;
                        new Handler().postDelayed(new Runnable() { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$$ExternalSyntheticLambda8
                            @Override // java.lang.Runnable
                            public final void run() {
                                UiBaseActivity.this.mCloseWarned = false;
                            }
                        }, 1500L);
                        return;
                    }
                }
                Toast toast = this.closeToast;
                if (toast != null) {
                    Intrinsics.checkNotNull(toast);
                    toast.cancel();
                }
                doReturnBack();
                return;
            }
            this.mCloseWarned = false;
            doReturnBack();
        }
    }

    private final void doReturnBack() {
        UiBaseFragment<?, ?> uiBaseFragment;
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
            return;
        }
        getSupportFragmentManager().popBackStackImmediate();
        if (!tryToUpdateCurrentAfterPop() || (uiBaseFragment = this.mCurrentFragment) == null) {
            return;
        }
        Intrinsics.checkNotNull(uiBaseFragment);
        uiBaseFragment.onBackWithData(null);
    }

    protected void log(String args) {
        LogUtils.i(this.TAG, args);
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
            ToastUtils.make().setGravity(17, 0, 0).show(msg, new Object[0]);
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

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void finishAc() {
        finish();
    }

    private final void catchThrowable(Throwable e) {
        UICoreConfig.INSTANCE.throwable(e);
    }

    @Override // com.wifiled.baselib.uicode.inner.IBaseView
    public int getToolBarLayoutResId() {
        return R.layout.include_common_white_toolbar;
    }

    public void setWhiteFakeStatus(int contentParentViewId, boolean enbaleFixImmersionAndEditBug) {
        setFakeStatus(contentParentViewId, true, 0, R.color.common_white_color, enbaleFixImmersionAndEditBug);
        OSUtils.INSTANCE.fixWhiteStatusbarBug(this);
    }

    public void setTransparentStatus(int contentParentViewId, boolean enbaleFixImmersionAndEditBug) {
        View findViewById = findViewById(contentParentViewId);
        if (findViewById != null) {
            BarUtils.subtractMarginTopEqualStatusBarHeight(findViewById);
            BarUtils.setStatusBarLightMode((Activity) this, true);
        }
        fixImmersionAndEditBug(enbaleFixImmersionAndEditBug);
    }

    private final void setFakeStatus(int contentParentViewId, boolean isLightMode, int alpha, int statuBgResource, boolean enbaleFixImmersionAndEditBug) {
        View findViewById = findViewById(contentParentViewId);
        if (findViewById != null) {
            BarUtils.addMarginTopEqualStatusBarHeight(findViewById);
            BarUtils.setStatusBarLightMode(this, isLightMode);
        }
        fixImmersionAndEditBug(enbaleFixImmersionAndEditBug);
    }

    private final void fixImmersionAndEditBug(boolean enbaleFixImmersionAndEditBug) {
        if (enbaleFixImmersionAndEditBug) {
            KeyboardUtils.fixAndroidBug5497(this);
        }
    }

    /* renamed from: isActivityVisible, reason: from getter */
    private final boolean getIsActivityVisible() {
        return this.isActivityVisible;
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showProgressDialog() {
        if (getIsViewDestroy() || !getIsActivityVisible()) {
            return;
        }
        getViewDelegate().showProgressDialog();
    }

    public void setProgressDialogCancel(boolean onTouchOutside, boolean backCancel) {
        if (getIsViewDestroy()) {
            return;
        }
        getViewDelegate().setProgressDialogCanceled(onTouchOutside, backCancel);
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
            getViewDelegate().setDefaultStatusListener(new OnStatusRetryClickListener(this) { // from class: com.wifiled.baselib.uicode.base.UiBaseActivity$buildStatusView$1
                final /* synthetic */ UiBaseActivity<VM, VB> this$0;

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

    public DefaultStatusLayout getDefaultEmptyLayout() {
        return getViewDelegate().getEmptyLayout();
    }

    public DefaultStatusLayout getDefaultLoadErrorLayout() {
        return getViewDelegate().getLoadErrorLayout();
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

    public UiBaseFragment<?, ?> getCurrentFragment() {
        return this.mCurrentFragment;
    }

    public void pushFragmentToBackStack(int containerId, Class<? extends UiBaseFragment<?, ?>> cls, Object data) {
        try {
            this.mCurrentFragment = FragmentStarter.pushFragmentToBackStack(this, containerId, cls, data, true);
            this.mCloseWarned = false;
        } catch (Throwable th) {
            catchThrowable(th);
        }
    }

    public void pushFragmentToBackStack(Class<? extends UiBaseFragment<?, ?>> cls, Object data) {
        pushFragmentToBackStack(getFragmentContainerId(), cls, data);
    }

    public void popTopFragment(Object data) {
        UiBaseFragment<?, ?> uiBaseFragment;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        supportFragmentManager.popBackStackImmediate();
        if (!tryToUpdateCurrentAfterPop() || (uiBaseFragment = this.mCurrentFragment) == null) {
            return;
        }
        Intrinsics.checkNotNull(uiBaseFragment);
        uiBaseFragment.onBackWithData(data);
    }

    private final boolean tryToUpdateCurrentAfterPop() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        int backStackEntryCount = supportFragmentManager.getBackStackEntryCount();
        if (backStackEntryCount <= 0) {
            return false;
        }
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(supportFragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName());
        if (findFragmentByTag != null && (findFragmentByTag instanceof UiBaseFragment)) {
            this.mCurrentFragment = (UiBaseFragment) findFragmentByTag;
        }
        return true;
    }
}
