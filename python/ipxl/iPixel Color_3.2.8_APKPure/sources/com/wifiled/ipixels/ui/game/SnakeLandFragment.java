package com.wifiled.ipixels.ui.game;

import android.view.View;
import android.widget.TextView;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.view.customview.CustomImageViewPlus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: SnakeLandFragment.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0011H\u0014J\b\u0010\u0013\u001a\u00020\u0011H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/wifiled/ipixels/ui/game/SnakeLandFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "iv_land_down", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_land_left", "iv_land_right", "iv_land_start", "iv_land_up", "iv_right", "iv_back", "tv_title", "Landroid/widget/TextView;", "layoutId", "", "initView", "", "bindData", "bindListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SnakeLandFragment extends BaseFragment {
    private CustomImageView iv_back;
    private CustomImageView iv_land_down;
    private CustomImageView iv_land_left;
    private CustomImageView iv_land_right;
    private CustomImageView iv_land_start;
    private CustomImageView iv_land_up;
    private CustomImageView iv_right;
    private TextView tv_title;

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_land_game_snake;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tv_title = (TextView) findViewById3;
        View findViewById4 = this.mRootView.findViewById(R.id.iv_land_down);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.iv_land_down = (CustomImageView) findViewById4;
        View findViewById5 = this.mRootView.findViewById(R.id.iv_land_left);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.iv_land_left = (CustomImageView) findViewById5;
        View findViewById6 = this.mRootView.findViewById(R.id.iv_land_right);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.iv_land_right = (CustomImageView) findViewById6;
        View findViewById7 = this.mRootView.findViewById(R.id.iv_land_start);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.iv_land_start = (CustomImageView) findViewById7;
        View findViewById8 = this.mRootView.findViewById(R.id.iv_land_up);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.iv_land_up = (CustomImageView) findViewById8;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        CustomImageView customImageView = null;
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            CustomImageView customImageView2 = this.iv_back;
            if (customImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView2 = null;
            }
            customImageView2.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            CustomImageView customImageView3 = this.iv_back;
            if (customImageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView3 = null;
            }
            customImageView3.setBackgroundResource(R.mipmap.icon_back);
        }
        CustomImageView customImageView4 = this.iv_back;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView4 = null;
        }
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeLandFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeLandFragment.bindData$lambda$0(view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView5 = this.iv_back;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView5 = null;
        }
        companion.attachViewOnTouchListener(customImageView5);
        TextView textView = this.tv_title;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getString(R.string.title_game_tetris));
        CustomImageView customImageView6 = this.iv_right;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView6 = null;
        }
        customImageView6.setBackgroundResource(R.drawable.screen_icon_default_b);
        CustomImageView customImageView7 = this.iv_right;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView7 = null;
        }
        customImageView7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeLandFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeLandFragment.bindData$lambda$1(view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        CustomImageView customImageView8 = this.iv_right;
        if (customImageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView = customImageView8;
        }
        companion2.attachViewOnTouchListener(customImageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$0(View view) {
        EventBus.getDefault().post(new LandScreenClickMsg(Constants.DIRECTION.BACK.ordinal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$1(View view) {
        EventBus.getDefault().post(new LandScreenClickMsg(Constants.DIRECTION.SCREEN_SWITCH.ordinal()));
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        CustomImageView customImageView = this.iv_land_right;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_right");
            customImageView = null;
        }
        customImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeLandFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeLandFragment.bindListener$lambda$2(view);
            }
        });
        CustomImageViewPlus.Companion companion = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView3 = this.iv_land_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_right");
            customImageView3 = null;
        }
        companion.attachViewOnTouchListener(customImageView3);
        CustomImageView customImageView4 = this.iv_land_up;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_up");
            customImageView4 = null;
        }
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeLandFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeLandFragment.bindListener$lambda$3(view);
            }
        });
        CustomImageViewPlus.Companion companion2 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView5 = this.iv_land_up;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_up");
            customImageView5 = null;
        }
        companion2.attachViewOnTouchListener(customImageView5);
        CustomImageView customImageView6 = this.iv_land_down;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_down");
            customImageView6 = null;
        }
        customImageView6.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeLandFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeLandFragment.bindListener$lambda$4(view);
            }
        });
        CustomImageViewPlus.Companion companion3 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView7 = this.iv_land_down;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_down");
            customImageView7 = null;
        }
        companion3.attachViewOnTouchListener(customImageView7);
        CustomImageView customImageView8 = this.iv_land_left;
        if (customImageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_left");
            customImageView8 = null;
        }
        customImageView8.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeLandFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeLandFragment.bindListener$lambda$5(view);
            }
        });
        CustomImageViewPlus.Companion companion4 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView9 = this.iv_land_left;
        if (customImageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_left");
            customImageView9 = null;
        }
        companion4.attachViewOnTouchListener(customImageView9);
        CustomImageView customImageView10 = this.iv_land_start;
        if (customImageView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_start");
            customImageView10 = null;
        }
        customImageView10.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeLandFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeLandFragment.bindListener$lambda$6(view);
            }
        });
        CustomImageViewPlus.Companion companion5 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView11 = this.iv_land_start;
        if (customImageView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_land_start");
        } else {
            customImageView2 = customImageView11;
        }
        companion5.attachViewOnTouchListener(customImageView2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$2(View view) {
        EventBus.getDefault().post(new LandScreenClickMsg(Constants.DIRECTION.RIGHT.ordinal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$3(View view) {
        EventBus.getDefault().post(new LandScreenClickMsg(Constants.DIRECTION.UP.ordinal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$4(View view) {
        EventBus.getDefault().post(new LandScreenClickMsg(Constants.DIRECTION.DOWN.ordinal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$5(View view) {
        EventBus.getDefault().post(new LandScreenClickMsg(Constants.DIRECTION.LEFT.ordinal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$6(View view) {
        EventBus.getDefault().post(new LandScreenClickMsg(Constants.DIRECTION.START_PAUSE.ordinal()));
    }
}
