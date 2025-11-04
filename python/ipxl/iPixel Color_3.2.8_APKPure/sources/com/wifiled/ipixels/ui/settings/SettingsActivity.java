package com.wifiled.ipixels.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.Navigation;
import com.wifiled.baselib.app.language.LanguageManager;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseNavActivity;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.ui.ChooseActivity;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: SettingsActivity.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 (2\u00020\u0001:\u0001(B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u000bH\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u000bH\u0014J\b\u0010\u001c\u001a\u00020\u0017H\u0014J\b\u0010\u001d\u001a\u00020\u0017H\u0014J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 H\u0007J\u0006\u0010!\u001a\u00020\u0017J\b\u0010\"\u001a\u00020\u0017H\u0002J\u001e\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0002\b\u0003\u0018\u00010'H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/wifiled/ipixels/ui/settings/SettingsActivity;", "Lcom/wifiled/baselib/base/BaseNavActivity;", "<init>", "()V", "devInfoFragment", "Lcom/wifiled/ipixels/ui/settings/SettingsDevFragment;", "lanFragment", "Lcom/wifiled/ipixels/ui/settings/SettingsLanFragment;", "mainFragment", "Lcom/wifiled/ipixels/ui/settings/SettingsMainFragment;", "SET_PAGE_MAIN", "", "SET_PAGE_LAN", "SET_PAGE_DEV", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "mPageIndex", "mLanguage", "", "initView", "", "layoutId", PlayerFinal.PLAYER_MODE, "Lcom/wifiled/baselib/app/Navigation$MODE;", "containerViewId", "bindData", "onDestroy", "onSettingsPage", "instance", "Lcom/wifiled/ipixels/ui/settings/SettingsPage;", "initToolBar", "setLanguage", "restartApp", "activity", "Landroid/app/Activity;", "homeClass", "Ljava/lang/Class;", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsActivity extends BaseNavActivity {
    private static boolean mIsLanChange;
    private SettingsDevFragment devInfoFragment;
    private CustomImageView iv_back;
    private CustomImageView iv_right;
    private SettingsLanFragment lanFragment;
    private SettingsMainFragment mainFragment;
    private TextView tv_title;
    private final int SET_PAGE_LAN = 1;
    private final int SET_PAGE_DEV = 2;
    private final int SET_PAGE_MAIN;
    private int mPageIndex = this.SET_PAGE_MAIN;
    private String mLanguage = "en";

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setLanguage$lambda$8() {
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById;
        View findViewById2 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById2;
        View findViewById3 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tv_title = (TextView) findViewById3;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_settings;
    }

    @Override // com.wifiled.baselib.base.BaseNavActivity
    protected Navigation.MODE mode() {
        return Navigation.MODE.SHOW;
    }

    @Override // com.wifiled.baselib.base.BaseNavActivity
    protected int containerViewId() {
        return R.id.set_navigation_container;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        LogUtils.file("SettingsActivity   bindData");
        this.devInfoFragment = new SettingsDevFragment();
        this.lanFragment = new SettingsLanFragment();
        this.mainFragment = new SettingsMainFragment();
        initToolBar();
        Navigation.get().navigate(this.mainFragment);
        EventBus.getDefault().register(this);
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        Navigation.get().pop();
        this.devInfoFragment = null;
        this.lanFragment = null;
        this.mainFragment = null;
        CustomImageView customImageView = this.iv_back;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView = null;
        }
        customImageView.setOnTouchListener(null);
        CustomImageView customImageView2 = this.iv_right;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView2 = null;
        }
        customImageView2.setOnTouchListener(null);
        Navigation.get().clear();
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public final void onSettingsPage(SettingsPage instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        int index = instance.getIndex();
        this.mPageIndex = index;
        if (index == this.SET_PAGE_LAN) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsActivity$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onSettingsPage$lambda$2;
                    onSettingsPage$lambda$2 = SettingsActivity.onSettingsPage$lambda$2(SettingsActivity.this);
                    return onSettingsPage$lambda$2;
                }
            });
            Navigation.get().navigate(this.lanFragment);
        } else if (index == this.SET_PAGE_DEV) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsActivity$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onSettingsPage$lambda$3;
                    onSettingsPage$lambda$3 = SettingsActivity.onSettingsPage$lambda$3(SettingsActivity.this);
                    return onSettingsPage$lambda$3;
                }
            });
            SettingsDevFragment settingsDevFragment = this.devInfoFragment;
            if (settingsDevFragment != null) {
                SettingsMainFragment settingsMainFragment = this.mainFragment;
                settingsDevFragment.setArray(settingsMainFragment != null ? settingsMainFragment.getDevArray() : null);
            }
            Navigation.get().navigate(this.devInfoFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onSettingsPage$lambda$2(SettingsActivity settingsActivity) {
        CustomImageView customImageView = settingsActivity.iv_right;
        TextView textView = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView = null;
        }
        UtilsExtensionKt.show(customImageView);
        TextView textView2 = settingsActivity.tv_title;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
        } else {
            textView = textView2;
        }
        textView.setText(settingsActivity.getString(R.string.title_language));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onSettingsPage$lambda$3(SettingsActivity settingsActivity) {
        CustomImageView customImageView = settingsActivity.iv_right;
        TextView textView = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView = null;
        }
        UtilsExtensionKt.hide(customImageView);
        TextView textView2 = settingsActivity.tv_title;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
        } else {
            textView = textView2;
        }
        textView.setText(settingsActivity.getString(R.string.set_devinfo));
        return Unit.INSTANCE;
    }

    public final void initToolBar() {
        TextView textView = this.tv_title;
        CustomImageView customImageView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getString(R.string.title_settings));
        CustomImageView customImageView2 = this.iv_right;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView2 = null;
        }
        UtilsExtensionKt.hide(customImageView2);
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            CustomImageView customImageView3 = this.iv_back;
            if (customImageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView3 = null;
            }
            customImageView3.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            CustomImageView customImageView4 = this.iv_back;
            if (customImageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView4 = null;
            }
            customImageView4.setBackgroundResource(R.mipmap.icon_back);
        }
        CustomImageView customImageView5 = this.iv_back;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView5 = null;
        }
        customImageView5.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsActivity.initToolBar$lambda$5(SettingsActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView6 = this.iv_back;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView6 = null;
        }
        companion.attachViewOnTouchListener(customImageView6);
        CustomImageView customImageView7 = this.iv_right;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView7 = null;
        }
        customImageView7.setBackgroundResource(R.mipmap.icon_setting_ok);
        CustomImageView customImageView8 = this.iv_right;
        if (customImageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView8 = null;
        }
        customImageView8.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsActivity.initToolBar$lambda$6(SettingsActivity.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        CustomImageView customImageView9 = this.iv_right;
        if (customImageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView = customImageView9;
        }
        companion2.attachViewOnTouchListener(customImageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolBar$lambda$5(final SettingsActivity settingsActivity, View view) {
        LogUtils.file("SettingsActivity   iv_back.setOnClickListener");
        TextView textView = settingsActivity.tv_title;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(settingsActivity.getString(R.string.title_settings));
        int i = settingsActivity.mPageIndex;
        if (i == settingsActivity.SET_PAGE_LAN) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsActivity$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit initToolBar$lambda$5$lambda$4;
                    initToolBar$lambda$5$lambda$4 = SettingsActivity.initToolBar$lambda$5$lambda$4(SettingsActivity.this);
                    return initToolBar$lambda$5$lambda$4;
                }
            });
            settingsActivity.mPageIndex = settingsActivity.SET_PAGE_MAIN;
            Navigation.get().navigate(settingsActivity.mainFragment);
        } else if (i == settingsActivity.SET_PAGE_DEV) {
            settingsActivity.mPageIndex = settingsActivity.SET_PAGE_MAIN;
            Navigation.get().navigate(settingsActivity.mainFragment);
        } else if (i == settingsActivity.SET_PAGE_MAIN) {
            if (mIsLanChange) {
                mIsLanChange = false;
                settingsActivity.toActivity(ChooseActivity.class);
            }
            settingsActivity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initToolBar$lambda$5$lambda$4(SettingsActivity settingsActivity) {
        CustomImageView customImageView = settingsActivity.iv_right;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView = null;
        }
        UtilsExtensionKt.hide(customImageView);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolBar$lambda$6(SettingsActivity settingsActivity, View view) {
        LogUtils.file("SettingsActivity   iv_right.setOnClickListener ");
        settingsActivity.setLanguage();
    }

    private final void setLanguage() {
        LogUtils.file("SettingsActivity   setLanguage");
        SettingsLanFragment settingsLanFragment = this.lanFragment;
        String valueOf = String.valueOf(settingsLanFragment != null ? settingsLanFragment.getMLanguage() : null);
        this.mLanguage = valueOf;
        SendCore.INSTANCE.getLedType(StringsKt.contains$default((CharSequence) valueOf, (CharSequence) "zh", false, 2, (Object) null) ? (byte) 1 : (byte) 0, new Function1() { // from class: com.wifiled.ipixels.ui.settings.SettingsActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit language$lambda$7;
                language$lambda$7 = SettingsActivity.setLanguage$lambda$7((SendCore.CallbackBuilder) obj);
                return language$lambda$7;
            }
        });
        LanguageManager.setSaveLanguage(getApplication(), this.mLanguage);
        new Handler().postDelayed(new Runnable() { // from class: com.wifiled.ipixels.ui.settings.SettingsActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SettingsActivity.setLanguage$lambda$8();
            }
        }, 500L);
        dismissProgressDialog();
        restartApp(this, ChooseActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setLanguage$lambda$7(SendCore.CallbackBuilder getLedType) {
        Intrinsics.checkNotNullParameter(getLedType, "$this$getLedType");
        return Unit.INSTANCE;
    }

    private final void restartApp(Activity activity, Class<?> homeClass) {
        Intent intent = new Intent(activity, homeClass);
        intent.setFlags(268468224);
        activity.startActivity(intent);
        mIsLanChange = true;
    }
}
