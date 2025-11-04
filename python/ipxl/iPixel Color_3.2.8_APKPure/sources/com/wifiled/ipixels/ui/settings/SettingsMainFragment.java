package com.wifiled.ipixels.ui.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.cache.ACache;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.manager.UpdateManager;
import com.wifiled.baselib.utils.AppUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.baselib.widget.TextDrawable;
import com.wifiled.blelibrary.ble.utils.ByteUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.BuildConfig;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.BaseSend;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import com.wifiled.ipixels.utils.TimeHelper;
import com.wifiled.ipixels.view.customview.CustomImageView;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: SettingsMainFragment.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010%\u001a\u00020&H\u0014J\b\u0010'\u001a\u00020&H\u0015J\b\u0010(\u001a\u00020&H\u0016J\u0010\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020&H\u0016J\b\u00101\u001a\u00020&H\u0015R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u001f\u0010-\u001a\u0013\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020&0.¢\u0006\u0002\b0X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/wifiled/ipixels/ui/settings/SettingsMainFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "devArray", "", "getDevArray", "()[B", "setDevArray", "([B)V", "clickCount", "", "layoutId", "arraw_1", "Landroidx/appcompat/widget/AppCompatImageView;", "arraw_2", "arraw_3", "check_up", "Landroidx/constraintlayout/widget/ConstraintLayout;", "check_up_image", "Landroid/widget/ImageView;", "rotate_0", "Landroidx/appcompat/widget/AppCompatButton;", "rotate_180", "rotate_270", "rotate_90", "rotate_ll", "Landroidx/appcompat/widget/LinearLayoutCompat;", "sb_settings_alpha", "Landroid/widget/SeekBar;", "tv_clear_data", "tv_devinfo", "tv_devpwd", "tv_language", "tv_upside_down", "tv_ver", "Landroid/widget/TextView;", "initView", "", "bindData", "onDestroyView", "onHiddenChanged", "hidden", "", "onDetach", "callback", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "bindListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsMainFragment extends BaseFragment {
    private AppCompatImageView arraw_1;
    private AppCompatImageView arraw_2;
    private AppCompatImageView arraw_3;
    private ConstraintLayout check_up;
    private ImageView check_up_image;
    private int clickCount;
    private AppCompatButton rotate_0;
    private AppCompatButton rotate_180;
    private AppCompatButton rotate_270;
    private AppCompatButton rotate_90;
    private LinearLayoutCompat rotate_ll;
    private SeekBar sb_settings_alpha;
    private ConstraintLayout tv_clear_data;
    private ConstraintLayout tv_devinfo;
    private ConstraintLayout tv_devpwd;
    private ConstraintLayout tv_language;
    private ConstraintLayout tv_upside_down;
    private TextView tv_ver;
    private byte[] devArray = new byte[0];
    private Function1<? super SendCore.CallbackBuilder, Unit> callback = new Function1() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit callback$lambda$9;
            callback$lambda$9 = SettingsMainFragment.callback$lambda$9(SettingsMainFragment.this, (SendCore.CallbackBuilder) obj);
            return callback$lambda$9;
        }
    };

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_settings_main;
    }

    public final byte[] getDevArray() {
        return this.devArray;
    }

    public final void setDevArray(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.devArray = bArr;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.arraw_1);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.arraw_1 = (AppCompatImageView) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.arraw_2);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.arraw_2 = (AppCompatImageView) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.arraw_3);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.arraw_3 = (AppCompatImageView) findViewById3;
        View findViewById4 = this.mRootView.findViewById(R.id.check_up);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.check_up = (ConstraintLayout) findViewById4;
        View findViewById5 = this.mRootView.findViewById(R.id.check_up_image);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.check_up_image = (ImageView) findViewById5;
        View findViewById6 = this.mRootView.findViewById(R.id.rotate_0);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.rotate_0 = (AppCompatButton) findViewById6;
        View findViewById7 = this.mRootView.findViewById(R.id.rotate_180);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.rotate_180 = (AppCompatButton) findViewById7;
        View findViewById8 = this.mRootView.findViewById(R.id.rotate_270);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.rotate_270 = (AppCompatButton) findViewById8;
        View findViewById9 = this.mRootView.findViewById(R.id.rotate_90);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.rotate_90 = (AppCompatButton) findViewById9;
        View findViewById10 = this.mRootView.findViewById(R.id.rotate_ll);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.rotate_ll = (LinearLayoutCompat) findViewById10;
        View findViewById11 = this.mRootView.findViewById(R.id.sb_settings_alpha);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.sb_settings_alpha = (SeekBar) findViewById11;
        View findViewById12 = this.mRootView.findViewById(R.id.tv_clear_data);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.tv_clear_data = (ConstraintLayout) findViewById12;
        View findViewById13 = this.mRootView.findViewById(R.id.tv_devinfo);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.tv_devinfo = (ConstraintLayout) findViewById13;
        View findViewById14 = this.mRootView.findViewById(R.id.tv_devpwd);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.tv_devpwd = (ConstraintLayout) findViewById14;
        View findViewById15 = this.mRootView.findViewById(R.id.tv_language);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.tv_language = (ConstraintLayout) findViewById15;
        View findViewById16 = this.mRootView.findViewById(R.id.tv_upside_down);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.tv_upside_down = (ConstraintLayout) findViewById16;
        View findViewById17 = this.mRootView.findViewById(R.id.tv_ver);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.tv_ver = (TextView) findViewById17;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        String appMetaData = AppUtils.getAppMetaData(this.mActivity, "HEATON_CHANNEL");
        Intrinsics.checkNotNull(appMetaData);
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        LinearLayoutCompat linearLayoutCompat = null;
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            AppCompatImageView appCompatImageView = this.arraw_1;
            if (appCompatImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arraw_1");
                appCompatImageView = null;
            }
            appCompatImageView.setImageResource(R.mipmap.icon_arraw_ar);
            AppCompatImageView appCompatImageView2 = this.arraw_2;
            if (appCompatImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arraw_2");
                appCompatImageView2 = null;
            }
            appCompatImageView2.setImageResource(R.mipmap.icon_arraw_ar);
            AppCompatImageView appCompatImageView3 = this.arraw_3;
            if (appCompatImageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arraw_3");
                appCompatImageView3 = null;
            }
            appCompatImageView3.setImageResource(R.mipmap.icon_arraw_ar);
        }
        LogUtils.vTag("ruis", "channel-----" + appMetaData);
        if (!Intrinsics.areEqual(BuildConfig.FLAVOR, appMetaData)) {
            TextView textView = this.tv_ver;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_ver");
                textView = null;
            }
            textView.setText("S-V" + AppUtils.getVersionName(getActivity()));
            if (UpdateManager.isNeedUpDateApp) {
                ImageView imageView = this.check_up_image;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("check_up_image");
                    imageView = null;
                }
                imageView.setVisibility(0);
            } else {
                ImageView imageView2 = this.check_up_image;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("check_up_image");
                    imageView2 = null;
                }
                imageView2.setVisibility(8);
            }
        } else {
            TextView textView2 = this.tv_ver;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_ver");
                textView2 = null;
            }
            textView2.setText("G-V" + AppUtils.getVersionName(getActivity()));
            ConstraintLayout constraintLayout = this.check_up;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("check_up");
                constraintLayout = null;
            }
            constraintLayout.setVisibility(8);
        }
        try {
            if (ACache.get(getActivity()).getAsString(Constants.VAL_GLOBAL_LIGHT) != null) {
                String asString = ACache.get(getActivity()).getAsString(Constants.VAL_GLOBAL_LIGHT);
                Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
                SeekBar seekBar = this.sb_settings_alpha;
                if (seekBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sb_settings_alpha");
                    seekBar = null;
                }
                seekBar.setProgress(Integer.parseInt(asString));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (AppConfig.INSTANCE.getLedType() == 2 || AppConfig.INSTANCE.getLedType() == 0) {
            return;
        }
        AppCompatButton appCompatButton = this.rotate_90;
        if (appCompatButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rotate_90");
            appCompatButton = null;
        }
        appCompatButton.setVisibility(8);
        AppCompatButton appCompatButton2 = this.rotate_270;
        if (appCompatButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rotate_270");
            appCompatButton2 = null;
        }
        appCompatButton2.setVisibility(8);
        LinearLayoutCompat linearLayoutCompat2 = this.rotate_ll;
        if (linearLayoutCompat2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rotate_ll");
            linearLayoutCompat2 = null;
        }
        linearLayoutCompat2.setGravity(16);
        ConstraintLayout constraintLayout2 = this.tv_devpwd;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_devpwd");
            constraintLayout2 = null;
        }
        constraintLayout2.setVisibility(0);
        LinearLayoutCompat linearLayoutCompat3 = this.rotate_ll;
        if (linearLayoutCompat3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rotate_ll");
        } else {
            linearLayoutCompat = linearLayoutCompat3;
        }
        linearLayoutCompat.setPadding(UtilsExtensionKt.toDp(50), 0, 0, 0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.callback = new Function1() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onDestroyView$lambda$0;
                onDestroyView$lambda$0 = SettingsMainFragment.onDestroyView$lambda$0((SendCore.CallbackBuilder) obj);
                return onDestroyView$lambda$0;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onDestroyView$lambda$0(SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        return Unit.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            return;
        }
        try {
            if (ACache.get(getActivity()).getAsString(Constants.VAL_GLOBAL_LIGHT) != null) {
                String asString = ACache.get(getActivity()).getAsString(Constants.VAL_GLOBAL_LIGHT);
                Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
                SeekBar seekBar = this.sb_settings_alpha;
                if (seekBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sb_settings_alpha");
                    seekBar = null;
                }
                seekBar.setProgress(Integer.parseInt(asString));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mActivity = null;
        ToastUtil.cancelToast();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$9(final SettingsMainFragment settingsMainFragment, SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        callbackBuilder.onError(new Function1() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$9$lambda$2;
                callback$lambda$9$lambda$2 = SettingsMainFragment.callback$lambda$9$lambda$2(SettingsMainFragment.this, ((Integer) obj).intValue());
                return callback$lambda$9$lambda$2;
            }
        });
        callbackBuilder.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$9$lambda$4;
                callback$lambda$9$lambda$4 = SettingsMainFragment.callback$lambda$9$lambda$4(SettingsMainFragment.this);
                return callback$lambda$9$lambda$4;
            }
        });
        callbackBuilder.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$9$lambda$8;
                callback$lambda$9$lambda$8 = SettingsMainFragment.callback$lambda$9$lambda$8(SettingsMainFragment.this, (byte[]) obj);
                return callback$lambda$9$lambda$8;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$9$lambda$2(final SettingsMainFragment settingsMainFragment, final int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$9$lambda$2$lambda$1;
                callback$lambda$9$lambda$2$lambda$1 = SettingsMainFragment.callback$lambda$9$lambda$2$lambda$1(SettingsMainFragment.this, i);
                return callback$lambda$9$lambda$2$lambda$1;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$9$lambda$2$lambda$1(SettingsMainFragment settingsMainFragment, int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, (String) null, false, 6, (Object) null);
        if (settingsMainFragment.isAdded()) {
            ToastUtil.show(settingsMainFragment.getString(R.string.msg_search_hd_info_fail) + "(" + i + ")");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$9$lambda$4(final SettingsMainFragment settingsMainFragment) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$9$lambda$4$lambda$3;
                callback$lambda$9$lambda$4$lambda$3 = SettingsMainFragment.callback$lambda$9$lambda$4$lambda$3(SettingsMainFragment.this);
                return callback$lambda$9$lambda$4$lambda$3;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$9$lambda$4$lambda$3(SettingsMainFragment settingsMainFragment) {
        String string = settingsMainFragment.getString(R.string.msg_search_hd_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, string, true, 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$9$lambda$8(final SettingsMainFragment settingsMainFragment, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.length == 8) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$9$lambda$8$lambda$5;
                    callback$lambda$9$lambda$8$lambda$5 = SettingsMainFragment.callback$lambda$9$lambda$8$lambda$5(SettingsMainFragment.this);
                    return callback$lambda$9$lambda$8$lambda$5;
                }
            });
            settingsMainFragment.devArray = it;
            EventBus.getDefault().post(new SettingsPage(2));
        } else {
            String byteUtils = ByteUtils.toString(it);
            Intrinsics.checkNotNullExpressionValue(byteUtils, "toString(...)");
            if (StringsKt.contains$default((CharSequence) byteUtils, (CharSequence) "dev disconnect", false, 2, (Object) null)) {
                if (settingsMainFragment.isAdded()) {
                    UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda20
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit callback$lambda$9$lambda$8$lambda$6;
                            callback$lambda$9$lambda$8$lambda$6 = SettingsMainFragment.callback$lambda$9$lambda$8$lambda$6(SettingsMainFragment.this);
                            return callback$lambda$9$lambda$8$lambda$6;
                        }
                    });
                }
                UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit callback$lambda$9$lambda$8$lambda$7;
                        callback$lambda$9$lambda$8$lambda$7 = SettingsMainFragment.callback$lambda$9$lambda$8$lambda$7(SettingsMainFragment.this);
                        return callback$lambda$9$lambda$8$lambda$7;
                    }
                }, 0L, 2, null);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$9$lambda$8$lambda$5(SettingsMainFragment settingsMainFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$9$lambda$8$lambda$6(SettingsMainFragment settingsMainFragment) {
        ToastUtil.show(settingsMainFragment.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$9$lambda$8$lambda$7(SettingsMainFragment settingsMainFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        ConstraintLayout constraintLayout = this.tv_clear_data;
        AppCompatButton appCompatButton = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_clear_data");
            constraintLayout = null;
        }
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$14(SettingsMainFragment.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        ConstraintLayout constraintLayout2 = this.tv_clear_data;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_clear_data");
            constraintLayout2 = null;
        }
        companion.attachViewOnTouchListener(constraintLayout2);
        ConstraintLayout constraintLayout3 = this.tv_devinfo;
        if (constraintLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_devinfo");
            constraintLayout3 = null;
        }
        constraintLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$17(SettingsMainFragment.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        ConstraintLayout constraintLayout4 = this.tv_devinfo;
        if (constraintLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_devinfo");
            constraintLayout4 = null;
        }
        companion2.attachViewOnTouchListener(constraintLayout4);
        ConstraintLayout constraintLayout5 = this.tv_language;
        if (constraintLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_language");
            constraintLayout5 = null;
        }
        constraintLayout5.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$18(view);
            }
        });
        CustomImageView.Companion companion3 = CustomImageView.INSTANCE;
        ConstraintLayout constraintLayout6 = this.tv_language;
        if (constraintLayout6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_language");
            constraintLayout6 = null;
        }
        companion3.attachViewOnTouchListener(constraintLayout6);
        ConstraintLayout constraintLayout7 = this.tv_devpwd;
        if (constraintLayout7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_devpwd");
            constraintLayout7 = null;
        }
        constraintLayout7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$20(SettingsMainFragment.this, view);
            }
        });
        CustomImageView.Companion companion4 = CustomImageView.INSTANCE;
        ConstraintLayout constraintLayout8 = this.tv_devpwd;
        if (constraintLayout8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_devpwd");
            constraintLayout8 = null;
        }
        companion4.attachViewOnTouchListener(constraintLayout8);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        ConstraintLayout constraintLayout9 = this.tv_upside_down;
        if (constraintLayout9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_upside_down");
            constraintLayout9 = null;
        }
        constraintLayout9.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$22(Ref.BooleanRef.this, view);
            }
        });
        CustomImageView.Companion companion5 = CustomImageView.INSTANCE;
        ConstraintLayout constraintLayout10 = this.tv_upside_down;
        if (constraintLayout10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_upside_down");
            constraintLayout10 = null;
        }
        companion5.attachViewOnTouchListener(constraintLayout10);
        ConstraintLayout constraintLayout11 = this.check_up;
        if (constraintLayout11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("check_up");
            constraintLayout11 = null;
        }
        constraintLayout11.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$23(SettingsMainFragment.this, view);
            }
        });
        CustomImageView.Companion companion6 = CustomImageView.INSTANCE;
        ConstraintLayout constraintLayout12 = this.check_up;
        if (constraintLayout12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("check_up");
            constraintLayout12 = null;
        }
        companion6.attachViewOnTouchListener(constraintLayout12);
        SeekBar seekBar = this.sb_settings_alpha;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb_settings_alpha");
            seekBar = null;
        }
        UtilsExtensionKt.setOnSeekBarStopChangeListener(seekBar, new Function1() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit bindListener$lambda$24;
                bindListener$lambda$24 = SettingsMainFragment.bindListener$lambda$24(SettingsMainFragment.this, (SeekBar) obj);
                return bindListener$lambda$24;
            }
        });
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                SettingsMainFragment.this.clickCount = 0;
            }
        };
        TextView textView = this.tv_ver;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_ver");
            textView = null;
        }
        final long j = CoroutineLiveDataKt.DEFAULT_TIMEOUT;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$26(SettingsMainFragment.this, handler, runnable, j, view);
            }
        });
        AppCompatButton appCompatButton2 = this.rotate_0;
        if (appCompatButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rotate_0");
            appCompatButton2 = null;
        }
        appCompatButton2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$27(view);
            }
        });
        AppCompatButton appCompatButton3 = this.rotate_90;
        if (appCompatButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rotate_90");
            appCompatButton3 = null;
        }
        appCompatButton3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$28(view);
            }
        });
        AppCompatButton appCompatButton4 = this.rotate_180;
        if (appCompatButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rotate_180");
            appCompatButton4 = null;
        }
        appCompatButton4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$29(view);
            }
        });
        AppCompatButton appCompatButton5 = this.rotate_270;
        if (appCompatButton5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rotate_270");
        } else {
            appCompatButton = appCompatButton5;
        }
        appCompatButton.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsMainFragment.bindListener$lambda$30(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$14(final SettingsMainFragment settingsMainFragment, View view) {
        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$14$lambda$10;
                    bindListener$lambda$14$lambda$10 = SettingsMainFragment.bindListener$lambda$14$lambda$10(SettingsMainFragment.this);
                    return bindListener$lambda$14$lambda$10;
                }
            });
            UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$14$lambda$11;
                    bindListener$lambda$14$lambda$11 = SettingsMainFragment.bindListener$lambda$14$lambda$11(SettingsMainFragment.this);
                    return bindListener$lambda$14$lambda$11;
                }
            }, 0L, 2, null);
            return;
        }
        Object obj = new WeakReference(settingsMainFragment.mActivity).get();
        Intrinsics.checkNotNull(obj);
        CustomDialog.Builder builder = new CustomDialog.Builder((Context) obj);
        builder.setTitle(settingsMainFragment.getString(R.string.gps_tip));
        builder.setMessage(settingsMainFragment.getString(R.string.tip));
        builder.setPositiveButton(settingsMainFragment.getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SettingsMainFragment.bindListener$lambda$14$lambda$12(SettingsMainFragment.this, dialogInterface, i);
            }
        });
        builder.setNegativeButton(settingsMainFragment.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda12
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$14$lambda$10(SettingsMainFragment settingsMainFragment) {
        String string = settingsMainFragment.getString(R.string.msg_dev_connect_null);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, string, false, 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$14$lambda$11(SettingsMainFragment settingsMainFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$14$lambda$12(SettingsMainFragment settingsMainFragment, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        boolean contains$default = StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "zh", false, 2, (Object) null);
        SendCore.INSTANCE.deleteAllData(new SettingsMainFragment$bindListener$1$3$1(settingsMainFragment, contains$default ? (byte) 1 : (byte) 0));
        SendCore.INSTANCE.deleteAllData2(new SettingsMainFragment$bindListener$1$3$2(settingsMainFragment, contains$default ? (byte) 1 : (byte) 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$17(final SettingsMainFragment settingsMainFragment, View view) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindListener$lambda$17$lambda$15;
                bindListener$lambda$17$lambda$15 = SettingsMainFragment.bindListener$lambda$17$lambda$15(SettingsMainFragment.this);
                return bindListener$lambda$17$lambda$15;
            }
        });
        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
            if (settingsMainFragment.isAdded()) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit bindListener$lambda$17$lambda$16;
                        bindListener$lambda$17$lambda$16 = SettingsMainFragment.bindListener$lambda$17$lambda$16(SettingsMainFragment.this);
                        return bindListener$lambda$17$lambda$16;
                    }
                });
                return;
            }
            return;
        }
        SendCore.getHwInfo(settingsMainFragment.callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$17$lambda$15(SettingsMainFragment settingsMainFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$17$lambda$16(SettingsMainFragment settingsMainFragment) {
        ToastUtil.show(settingsMainFragment.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$18(View view) {
        EventBus.getDefault().post(new SettingsPage(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$20(SettingsMainFragment settingsMainFragment, View view) {
        FragmentActivity activity = settingsMainFragment.getActivity();
        if (activity != null) {
            if (AppConfig.INSTANCE.getPwdFlag() == 0) {
                SetPwdActivity.INSTANCE.start(activity, 3);
            } else {
                SetPwdActivity.INSTANCE.start(activity, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$22(final Ref.BooleanRef booleanRef, final View view) {
        if (AppConfig.INSTANCE.getConnectType() != -1) {
            booleanRef.element = !booleanRef.element;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$22$lambda$21;
                    bindListener$lambda$22$lambda$21 = SettingsMainFragment.bindListener$lambda$22$lambda$21(view, booleanRef);
                    return bindListener$lambda$22$lambda$21;
                }
            });
        }
        BaseSend.setUpsideDown$default(SendCore.INSTANCE, booleanRef.element ? 1 : 0, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$22$lambda$21(View view, Ref.BooleanRef booleanRef) {
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.wifiled.baselib.widget.TextDrawable");
        ((TextDrawable) view).setDrawableRight(booleanRef.element ? R.drawable.set_select : R.drawable.set_default);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$23(SettingsMainFragment settingsMainFragment, View view) {
        if (UpdateManager.isNeedUpDateApp) {
            try {
                new UpdateManager(settingsMainFragment.mActivity).versionUpdate(LanguageUtil.getSaveLanguage(settingsMainFragment.mActivity));
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ToastUtil.show(settingsMainFragment.getString(R.string.is_new_version));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$24(final SettingsMainFragment settingsMainFragment, SeekBar it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$bindListener$7$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(getClass().getSimpleName(), "#setOnSeekBarChangeListener# prog:" + (seekBar != null ? Integer.valueOf(seekBar.getProgress()) : null));
                if (TimeHelper.INSTANCE.allowSend(100)) {
                    if (progress <= 10) {
                        progress = 10;
                    }
                    BaseSend.setLedLight$default(SendCore.INSTANCE, progress, null, 2, null);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(getClass().getSimpleName(), "#onStartTrackingTouch# prog:" + (seekBar != null ? Integer.valueOf(seekBar.getProgress()) : null));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(getClass().getSimpleName(), "#onStopTrackingTouch# prog:" + (seekBar != null ? Integer.valueOf(seekBar.getProgress()) : null));
                ACache.get(SettingsMainFragment.this.getActivity()).put(Constants.VAL_GLOBAL_LIGHT, String.valueOf(seekBar != null ? Integer.valueOf(seekBar.getProgress()) : null));
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$26(SettingsMainFragment settingsMainFragment, Handler handler, Runnable runnable, long j, View view) {
        settingsMainFragment.clickCount++;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, j);
        if (settingsMainFragment.clickCount >= 10) {
            List<File> logFiles = LogUtils.getLogFiles();
            Intrinsics.checkNotNullExpressionValue(logFiles, "getLogFiles(...)");
            if (CollectionsKt.last((List) logFiles) != null) {
                List<File> logFiles2 = LogUtils.getLogFiles();
                Intrinsics.checkNotNullExpressionValue(logFiles2, "getLogFiles(...)");
                Uri uriForFile = FileProvider.getUriForFile(settingsMainFragment.requireContext(), settingsMainFragment.requireContext().getPackageName() + ".android7.fileprovider", (File) CollectionsKt.last((List) logFiles2));
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.STREAM", uriForFile);
                intent.addFlags(1);
                settingsMainFragment.startActivity(Intent.createChooser(intent, "Share text file"));
                settingsMainFragment.clickCount = 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$27(View view) {
        BaseSend.setUpsideDown$default(SendCore.INSTANCE, 0, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$28(View view) {
        BaseSend.setUpsideDown$default(SendCore.INSTANCE, 1, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$29(View view) {
        BaseSend.setUpsideDown$default(SendCore.INSTANCE, 2, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$30(View view) {
        BaseSend.setUpsideDown$default(SendCore.INSTANCE, 3, null, 2, null);
    }
}
