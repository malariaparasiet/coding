package com.wifiled.ipixels.ui.game;

import android.content.res.Configuration;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.wifiled.baselib.app.Navigation;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.base.BaseNavActivity;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: GameActivity.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 02\u00020\u0001:\u00010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u001aH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0014J\u001a\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001a2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u001fH\u0014J\b\u0010&\u001a\u00020\u001fH\u0014J\u0010\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)H\u0007J\u0010\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/wifiled/ipixels/ui/game/GameActivity;", "Lcom/wifiled/baselib/base/BaseNavActivity;", "<init>", "()V", "mainFragment", "Lcom/wifiled/ipixels/ui/game/GameMainFragment;", "snakeLandFragment", "Lcom/wifiled/ipixels/ui/game/SnakeLandFragment;", "tetrisLandFragment", "Lcom/wifiled/ipixels/ui/game/TetrisLandFragment;", "snakeFragment", "Lcom/wifiled/ipixels/ui/game/SnakeFragment;", "tetrisFragment", "Lcom/wifiled/ipixels/ui/game/TetrisFragment;", "pongFragment", "Lcom/wifiled/ipixels/ui/game/PongFragment;", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "il_game", "Landroidx/constraintlayout/widget/ConstraintLayout;", "mCurFragment", "Lcom/wifiled/baselib/base/BaseFragment;", "layoutId", "", "containerViewId", PlayerFinal.PLAYER_MODE, "Lcom/wifiled/baselib/app/Navigation$MODE;", "bindData", "", "onKeyDown", "", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onStop", "onDestroy", "onEvent", NotificationCompat.CATEGORY_MESSAGE, "Lcom/wifiled/ipixels/ui/game/LandScreenClickMsg;", "callbackToolBarData", "dataChange", "Lcom/wifiled/ipixels/ui/game/ToolbarDataChange;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GameActivity extends BaseNavActivity {
    public static final int PONG = 0;
    public static final int SNAKE = 2;
    public static final int TERTRIS = 1;
    private ConstraintLayout il_game;
    private CustomImageView iv_back;
    private CustomImageView iv_right;
    private BaseFragment mCurFragment;
    private TextView tv_title;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int configuration = 1;
    private final GameMainFragment mainFragment = new GameMainFragment();
    private final SnakeLandFragment snakeLandFragment = new SnakeLandFragment();
    private final TetrisLandFragment tetrisLandFragment = new TetrisLandFragment();
    private final SnakeFragment snakeFragment = new SnakeFragment();
    private final TetrisFragment tetrisFragment = new TetrisFragment();
    private final PongFragment pongFragment = new PongFragment();

    @Override // com.wifiled.baselib.base.BaseNavActivity
    protected int containerViewId() {
        return R.id.navigation_container;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_game;
    }

    /* compiled from: GameActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/wifiled/ipixels/ui/game/GameActivity$Companion;", "", "<init>", "()V", "configuration", "", "getConfiguration", "()I", "setConfiguration", "(I)V", "PONG", "TERTRIS", "SNAKE", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getConfiguration() {
            return GameActivity.configuration;
        }

        public final void setConfiguration(int i) {
            GameActivity.configuration = i;
        }
    }

    @Override // com.wifiled.baselib.base.BaseNavActivity
    protected Navigation.MODE mode() {
        return Navigation.MODE.SHOW;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        View findViewById = findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById;
        View findViewById2 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById2;
        View findViewById3 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tv_title = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.il_game);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.il_game = (ConstraintLayout) findViewById4;
        EventBus.getDefault().register(this);
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
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.GameActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameActivity.bindData$lambda$0(GameActivity.this, view);
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
        textView.setText(getString(R.string.title_game_center));
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
        customImageView7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.GameActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameActivity.bindData$lambda$1(GameActivity.this, view);
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
        Navigation.get().navigate(this.mainFragment);
        this.mCurFragment = this.mainFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$0(GameActivity gameActivity, View view) {
        BaseFragment baseFragment = gameActivity.mCurFragment;
        BaseFragment baseFragment2 = null;
        if (baseFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
            baseFragment = null;
        }
        boolean z = baseFragment instanceof GameMainFragment;
        if (z) {
            EventBus.getDefault().unregister(gameActivity);
            gameActivity.finish();
            return;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        SendCore.INSTANCE.sendExitCmd(null);
        gameActivity.tetrisFragment.setSwitchLandScreen(false);
        gameActivity.snakeFragment.setSwitchLandScreen(false);
        BaseFragment baseFragment3 = gameActivity.mCurFragment;
        if (baseFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
            baseFragment3 = null;
        }
        if ((baseFragment3 instanceof SnakeLandFragment) || (baseFragment3 instanceof TetrisLandFragment)) {
            gameActivity.setRequestedOrientation(1);
            BaseFragment baseFragment4 = gameActivity.mCurFragment;
            if (baseFragment4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment4 = null;
            }
            if (baseFragment4 instanceof TetrisLandFragment) {
                gameActivity.tetrisFragment.onStop();
            } else {
                gameActivity.snakeFragment.onStop();
            }
            gameActivity.mCurFragment = gameActivity.mainFragment;
            Navigation navigation = Navigation.get();
            BaseFragment baseFragment5 = gameActivity.mCurFragment;
            if (baseFragment5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
            } else {
                baseFragment2 = baseFragment5;
            }
            navigation.navigate(baseFragment2);
        } else if ((baseFragment3 instanceof PongFragment) || (baseFragment3 instanceof SnakeFragment) || (baseFragment3 instanceof TetrisFragment)) {
            gameActivity.mCurFragment = gameActivity.mainFragment;
            Navigation navigation2 = Navigation.get();
            BaseFragment baseFragment6 = gameActivity.mCurFragment;
            if (baseFragment6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
            } else {
                baseFragment2 = baseFragment6;
            }
            navigation2.navigate(baseFragment2);
        }
        ToolbarDataChange toolbarDataChange = new ToolbarDataChange();
        String string = gameActivity.getString(R.string.title_game_center);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        toolbarDataChange.setStrTitle(string);
        toolbarDataChange.setShow(false);
        EventBus.getDefault().post(toolbarDataChange);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$1(GameActivity gameActivity, View view) {
        BaseFragment baseFragment = gameActivity.mCurFragment;
        BaseFragment baseFragment2 = null;
        if (baseFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
            baseFragment = null;
        }
        if (!(baseFragment instanceof SnakeFragment)) {
            BaseFragment baseFragment3 = gameActivity.mCurFragment;
            if (baseFragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment3 = null;
            }
            if (!(baseFragment3 instanceof SnakeLandFragment)) {
                BaseFragment baseFragment4 = gameActivity.mCurFragment;
                if (baseFragment4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    baseFragment4 = null;
                }
                if (!(baseFragment4 instanceof TetrisFragment)) {
                    BaseFragment baseFragment5 = gameActivity.mCurFragment;
                    if (baseFragment5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    } else {
                        baseFragment2 = baseFragment5;
                    }
                    if (!(baseFragment2 instanceof TetrisLandFragment)) {
                        return;
                    }
                }
            }
        }
        int i = configuration;
        if (i == 1) {
            gameActivity.setRequestedOrientation(0);
        } else {
            if (i != 2) {
                return;
            }
            gameActivity.setRequestedOrientation(1);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(getClass().getSimpleName(), "run onKeyDown:" + keyCode);
        if (keyCode == 4) {
            BaseFragment baseFragment = this.mCurFragment;
            BaseFragment baseFragment2 = null;
            if (baseFragment == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment = null;
            }
            boolean z = baseFragment instanceof GameMainFragment;
            if (z) {
                EventBus.getDefault().unregister(this);
                finish();
            } else {
                if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                CustomImageView customImageView = this.iv_right;
                if (customImageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("iv_right");
                    customImageView = null;
                }
                UtilsExtensionKt.hide(customImageView);
                BaseFragment baseFragment3 = this.mCurFragment;
                if (baseFragment3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    baseFragment3 = null;
                }
                if ((baseFragment3 instanceof SnakeLandFragment) || (baseFragment3 instanceof TetrisLandFragment)) {
                    setRequestedOrientation(1);
                    this.mCurFragment = this.mainFragment;
                    Navigation navigation = Navigation.get();
                    BaseFragment baseFragment4 = this.mCurFragment;
                    if (baseFragment4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    } else {
                        baseFragment2 = baseFragment4;
                    }
                    navigation.navigate(baseFragment2);
                    ToolbarDataChange toolbarDataChange = new ToolbarDataChange();
                    String string = getString(R.string.title_game_center);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    toolbarDataChange.setStrTitle(string);
                    toolbarDataChange.setShow(false);
                    EventBus.getDefault().post(toolbarDataChange);
                } else if ((baseFragment3 instanceof PongFragment) || (baseFragment3 instanceof SnakeFragment) || (baseFragment3 instanceof TetrisFragment)) {
                    this.mCurFragment = this.mainFragment;
                    Navigation navigation2 = Navigation.get();
                    BaseFragment baseFragment5 = this.mCurFragment;
                    if (baseFragment5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    } else {
                        baseFragment2 = baseFragment5;
                    }
                    navigation2.navigate(baseFragment2);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Navigation.get().pop();
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Navigation.get().clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(LandScreenClickMsg msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        int direction = msg.getDirection();
        BaseFragment baseFragment = null;
        if (direction == Constants.DIRECTION.BACK.ordinal()) {
            BaseFragment baseFragment2 = this.mCurFragment;
            if (baseFragment2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment2 = null;
            }
            boolean z = baseFragment2 instanceof GameMainFragment;
            if (z) {
                EventBus.getDefault().unregister(this);
                finish();
                return;
            }
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            this.tetrisFragment.setSwitchLandScreen(false);
            this.snakeFragment.setSwitchLandScreen(false);
            BaseFragment baseFragment3 = this.mCurFragment;
            if (baseFragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment3 = null;
            }
            if ((baseFragment3 instanceof SnakeLandFragment) || (baseFragment3 instanceof TetrisLandFragment)) {
                setRequestedOrientation(1);
                BaseFragment baseFragment4 = this.mCurFragment;
                if (baseFragment4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    baseFragment4 = null;
                }
                if (baseFragment4 instanceof TetrisLandFragment) {
                    this.tetrisFragment.onStop();
                } else {
                    this.snakeFragment.onStop();
                }
                this.mCurFragment = this.mainFragment;
                Navigation navigation = Navigation.get();
                BaseFragment baseFragment5 = this.mCurFragment;
                if (baseFragment5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                } else {
                    baseFragment = baseFragment5;
                }
                navigation.navigate(baseFragment);
            } else if ((baseFragment3 instanceof PongFragment) || (baseFragment3 instanceof SnakeFragment) || (baseFragment3 instanceof TetrisFragment)) {
                this.mCurFragment = this.mainFragment;
                Navigation navigation2 = Navigation.get();
                BaseFragment baseFragment6 = this.mCurFragment;
                if (baseFragment6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                } else {
                    baseFragment = baseFragment6;
                }
                navigation2.navigate(baseFragment);
            }
            ToolbarDataChange toolbarDataChange = new ToolbarDataChange();
            String string = getString(R.string.title_game_center);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            toolbarDataChange.setStrTitle(string);
            toolbarDataChange.setShow(false);
            EventBus.getDefault().post(toolbarDataChange);
            return;
        }
        if (direction == Constants.DIRECTION.SCREEN_SWITCH.ordinal()) {
            BaseFragment baseFragment7 = this.mCurFragment;
            if (baseFragment7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment7 = null;
            }
            if (!(baseFragment7 instanceof SnakeFragment)) {
                BaseFragment baseFragment8 = this.mCurFragment;
                if (baseFragment8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    baseFragment8 = null;
                }
                if (!(baseFragment8 instanceof SnakeLandFragment)) {
                    BaseFragment baseFragment9 = this.mCurFragment;
                    if (baseFragment9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                        baseFragment9 = null;
                    }
                    if (!(baseFragment9 instanceof TetrisFragment)) {
                        BaseFragment baseFragment10 = this.mCurFragment;
                        if (baseFragment10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                        } else {
                            baseFragment = baseFragment10;
                        }
                        if (!(baseFragment instanceof TetrisLandFragment)) {
                            return;
                        }
                    }
                }
            }
            int i = configuration;
            if (i == 1) {
                setRequestedOrientation(0);
            } else {
                if (i != 2) {
                    return;
                }
                setRequestedOrientation(1);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void callbackToolBarData(ToolbarDataChange dataChange) {
        Intrinsics.checkNotNullParameter(dataChange, "dataChange");
        TextView textView = this.tv_title;
        CustomImageView customImageView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(dataChange.getStrTitle());
        int index = dataChange.getIndex();
        if (index == 0) {
            this.mCurFragment = this.pongFragment;
            Navigation navigation = Navigation.get();
            BaseFragment baseFragment = this.mCurFragment;
            if (baseFragment == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment = null;
            }
            navigation.navigate(baseFragment);
        } else if (index == 1) {
            this.mCurFragment = this.tetrisFragment;
            Navigation navigation2 = Navigation.get();
            BaseFragment baseFragment2 = this.mCurFragment;
            if (baseFragment2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment2 = null;
            }
            navigation2.navigate(baseFragment2);
        } else if (index == 2) {
            this.mCurFragment = this.snakeFragment;
            Navigation navigation3 = Navigation.get();
            BaseFragment baseFragment3 = this.mCurFragment;
            if (baseFragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment3 = null;
            }
            navigation3.navigate(baseFragment3);
        }
        boolean isShow = dataChange.getIsShow();
        if (isShow) {
            CustomImageView customImageView2 = this.iv_right;
            if (customImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            } else {
                customImageView = customImageView2;
            }
            UtilsExtensionKt.show(customImageView);
            return;
        }
        if (isShow) {
            throw new NoWhenBranchMatchedException();
        }
        CustomImageView customImageView3 = this.iv_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView = customImageView3;
        }
        UtilsExtensionKt.hide(customImageView);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (configuration != newConfig.orientation) {
            configuration = newConfig.orientation;
            int i = newConfig.orientation;
            BaseFragment baseFragment = null;
            if (i == 1) {
                getWindow().clearFlags(1024);
                ConstraintLayout constraintLayout = this.il_game;
                if (constraintLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("il_game");
                    constraintLayout = null;
                }
                UtilsExtensionKt.show(constraintLayout);
                BaseFragment baseFragment2 = this.mCurFragment;
                if (baseFragment2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    baseFragment2 = null;
                }
                if (baseFragment2 instanceof SnakeLandFragment) {
                    this.mCurFragment = this.snakeFragment;
                    Navigation navigation = Navigation.get();
                    BaseFragment baseFragment3 = this.mCurFragment;
                    if (baseFragment3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    } else {
                        baseFragment = baseFragment3;
                    }
                    navigation.navigate(baseFragment);
                    return;
                }
                BaseFragment baseFragment4 = this.mCurFragment;
                if (baseFragment4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    baseFragment4 = null;
                }
                if (baseFragment4 instanceof TetrisLandFragment) {
                    this.mCurFragment = this.tetrisFragment;
                    Navigation navigation2 = Navigation.get();
                    BaseFragment baseFragment5 = this.mCurFragment;
                    if (baseFragment5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    } else {
                        baseFragment = baseFragment5;
                    }
                    navigation2.navigate(baseFragment);
                    return;
                }
                return;
            }
            if (i != 2) {
                return;
            }
            getWindow().addFlags(1024);
            ConstraintLayout constraintLayout2 = this.il_game;
            if (constraintLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("il_game");
                constraintLayout2 = null;
            }
            UtilsExtensionKt.hide(constraintLayout2);
            BaseFragment baseFragment6 = this.mCurFragment;
            if (baseFragment6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment6 = null;
            }
            if (baseFragment6 instanceof SnakeFragment) {
                BaseFragment baseFragment7 = this.mCurFragment;
                if (baseFragment7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                } else {
                    baseFragment = baseFragment7;
                }
                ((SnakeFragment) baseFragment).setSwitchLandScreen(true);
                this.mCurFragment = this.snakeLandFragment;
                Navigation.get().navigate(this.snakeLandFragment);
                return;
            }
            BaseFragment baseFragment8 = this.mCurFragment;
            if (baseFragment8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                baseFragment8 = null;
            }
            if (baseFragment8 instanceof TetrisFragment) {
                BaseFragment baseFragment9 = this.mCurFragment;
                if (baseFragment9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                } else {
                    baseFragment = baseFragment9;
                }
                ((TetrisFragment) baseFragment).setSwitchLandScreen(true);
                this.mCurFragment = this.tetrisLandFragment;
                Navigation.get().navigate(this.tetrisLandFragment);
            }
        }
    }
}
