package com.wifiled.ipixels.ui.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.utils.BitmapUtil;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.gameview.GameCallback;
import com.wifiled.gameview.snake.model.Snake;
import com.wifiled.gameview.snake.view.StageView;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.text.TextAgreement;
import com.wifiled.ipixels.utils.BitmapUtils;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.HandlerUtil;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.view.customview.CustomImageViewPlus;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: SnakeFragment.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010(\u001a\u00020\tH\u0014J\b\u0010)\u001a\u00020*H\u0014J\u0010\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u00020\u000eH\u0016J\b\u00100\u001a\u00020*H\u0016J\b\u00101\u001a\u00020*H\u0016J\b\u00102\u001a\u00020*H\u0016J\b\u00103\u001a\u00020*H\u0014J\u0010\u00104\u001a\u00020*2\u0006\u00105\u001a\u000206H\u0007J\b\u00107\u001a\u00020*H\u0014J\b\u00108\u001a\u00020*H\u0016J\u0012\u00109\u001a\u00020*2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u0012\u00109\u001a\u00020*2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\b\u0010>\u001a\u00020*H\u0016J\b\u0010?\u001a\u00020*H\u0016J\b\u0010@\u001a\u00020*H\u0016JJ\u0010A\u001a\u00020*2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020\t2\u0006\u0010H\u001a\u00020\u00062\u0006\u0010<\u001a\u00020=2\b\b\u0002\u0010I\u001a\u00020\t2\b\b\u0002\u0010J\u001a\u00020\tJ6\u0010A\u001a\u00020*2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020\t2\u0006\u0010H\u001a\u00020\u00062\u0006\u0010<\u001a\u00020=J\u0006\u0010K\u001a\u00020*J\u0006\u0010L\u001a\u00020*R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006M"}, d2 = {"Lcom/wifiled/ipixels/ui/game/SnakeFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "Lcom/wifiled/gameview/GameCallback;", "<init>", "()V", "gamePaint", "Landroid/graphics/Paint;", "scorePaint", "fontVal", "", "mHandler", "Ljava/lang/ref/WeakReference;", "Landroid/os/Handler;", "isSwitchLandScreen", "", "()Z", "setSwitchLandScreen", "(Z)V", "mRecIsDetaced", "getMRecIsDetaced", "setMRecIsDetaced", "cl_portait_snake", "Landroidx/constraintlayout/widget/ConstraintLayout;", "td_down", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "td_left", "td_right", "td_start", "td_up", "tv_snake_level", "Landroid/widget/TextView;", "tv_snake_point", "view_snake", "Lcom/wifiled/gameview/snake/view/StageView;", "mSnakeGameState", "", "getMSnakeGameState", "()B", "setMSnakeGameState", "(B)V", "layoutId", "initView", "", "onAttach", "context", "Landroid/content/Context;", "onHiddenChanged", "hidden", "onStop", "onDetach", "onDestroy", "bindData", "onEvent", NotificationCompat.CATEGORY_MESSAGE, "Lcom/wifiled/ipixels/ui/game/LandScreenClickMsg;", "bindListener", "onGameStart", "onGameFrame", "view", "Landroid/view/View;", "bitmap", "Landroid/graphics/Bitmap;", "onGameStop", "onGameOver", "onGameExit", "customBitmap", "info", "", "left", "", "top", "typeface", "paint", "changeColor", "iOffset", "hide", "show", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SnakeFragment extends BaseFragment implements GameCallback {
    private ConstraintLayout cl_portait_snake;
    private boolean isSwitchLandScreen;
    private boolean mRecIsDetaced;
    private byte mSnakeGameState;
    private CustomImageView td_down;
    private CustomImageView td_left;
    private CustomImageView td_right;
    private CustomImageView td_start;
    private CustomImageView td_up;
    private TextView tv_snake_level;
    private TextView tv_snake_point;
    private StageView view_snake;
    private final Paint gamePaint = new Paint();
    private final Paint scorePaint = new Paint();
    private final int fontVal = 12;
    private WeakReference<Handler> mHandler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler();

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_game_snake;
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameExit() {
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameFrame(Bitmap bitmap) {
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameStart() {
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameStop() {
    }

    /* renamed from: isSwitchLandScreen, reason: from getter */
    public final boolean getIsSwitchLandScreen() {
        return this.isSwitchLandScreen;
    }

    public final void setSwitchLandScreen(boolean z) {
        this.isSwitchLandScreen = z;
    }

    public final boolean getMRecIsDetaced() {
        return this.mRecIsDetaced;
    }

    public final void setMRecIsDetaced(boolean z) {
        this.mRecIsDetaced = z;
    }

    public final byte getMSnakeGameState() {
        return this.mSnakeGameState;
    }

    public final void setMSnakeGameState(byte b) {
        this.mSnakeGameState = b;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.cl_portait_snake);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.cl_portait_snake = (ConstraintLayout) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.td_down);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.td_down = (CustomImageView) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.td_left);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.td_left = (CustomImageView) findViewById3;
        View findViewById4 = this.mRootView.findViewById(R.id.td_right);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.td_right = (CustomImageView) findViewById4;
        View findViewById5 = this.mRootView.findViewById(R.id.td_start);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.td_start = (CustomImageView) findViewById5;
        View findViewById6 = this.mRootView.findViewById(R.id.td_up);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.td_up = (CustomImageView) findViewById6;
        View findViewById7 = this.mRootView.findViewById(R.id.tv_snake_level);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.tv_snake_level = (TextView) findViewById7;
        View findViewById8 = this.mRootView.findViewById(R.id.tv_snake_point);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.tv_snake_point = (TextView) findViewById8;
        View findViewById9 = this.mRootView.findViewById(R.id.view_snake);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.view_snake = (StageView) findViewById9;
    }

    @Override // com.wifiled.baselib.base.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        Log.d(getClass().getSimpleName(), "run attach");
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(getClass().getSimpleName(), "run onHiddenChanged:" + hidden);
        if (this.isSwitchLandScreen) {
            return;
        }
        StageView stageView = null;
        if (hidden) {
            StageView stageView2 = this.view_snake;
            if (stageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
                stageView2 = null;
            }
            stageView2.getSnakeIns().chanageWorkState();
            this.mSnakeGameState = (byte) 0;
            SendCore.INSTANCE.sendExitCmd(null);
            EventBus.getDefault().unregister(this);
            return;
        }
        StageView stageView3 = this.view_snake;
        if (stageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
        } else {
            stageView = stageView3;
        }
        stageView.init();
        EventBus.getDefault().register(this);
        this.mRecIsDetaced = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), "run onStop");
        this.mSnakeGameState = (byte) 0;
        EventBus.getDefault().unregister(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Log.d(getClass().getSimpleName(), "run onDetach");
        this.mRecIsDetaced = true;
        this.mActivity = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Log.d(getClass().getSimpleName(), "run onDestroy");
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        StageView stageView = this.view_snake;
        TextView textView = null;
        if (stageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView = null;
        }
        stageView.init();
        StageView stageView2 = this.view_snake;
        if (stageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView2 = null;
        }
        stageView2.getSnakeIns().setGameCallback(this);
        EventBus.getDefault().register(this);
        TextView textView2 = this.tv_snake_point;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_snake_point");
            textView2 = null;
        }
        UtilsExtensionKt.show(textView2);
        TextView textView3 = this.tv_snake_level;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_snake_level");
            textView3 = null;
        }
        UtilsExtensionKt.show(textView3);
        String string = getString(R.string.score);
        StageView stageView3 = this.view_snake;
        if (stageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView3 = null;
        }
        String str = string + ": " + Integer.valueOf(stageView3.getScore());
        TextView textView4 = this.tv_snake_point;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_snake_point");
            textView4 = null;
        }
        textView4.setText(str);
        String string2 = getString(R.string.level);
        StageView stageView4 = this.view_snake;
        if (stageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView4 = null;
        }
        String str2 = string2 + ": " + Integer.valueOf(stageView4.getLevel());
        TextView textView5 = this.tv_snake_level;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_snake_level");
        } else {
            textView = textView5;
        }
        textView.setText(str2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(LandScreenClickMsg msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        int direction = msg.getDirection();
        StageView stageView = null;
        if (direction == Constants.DIRECTION.UP.ordinal()) {
            StageView stageView2 = this.view_snake;
            if (stageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            } else {
                stageView = stageView2;
            }
            stageView.moveForward();
            return;
        }
        if (direction == Constants.DIRECTION.DOWN.ordinal()) {
            StageView stageView3 = this.view_snake;
            if (stageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            } else {
                stageView = stageView3;
            }
            stageView.moveBack();
            return;
        }
        if (direction == Constants.DIRECTION.LEFT.ordinal()) {
            StageView stageView4 = this.view_snake;
            if (stageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            } else {
                stageView = stageView4;
            }
            stageView.moveLeft();
            return;
        }
        if (direction == Constants.DIRECTION.RIGHT.ordinal()) {
            StageView stageView5 = this.view_snake;
            if (stageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            } else {
                stageView = stageView5;
            }
            stageView.moveRight();
            return;
        }
        if (direction == Constants.DIRECTION.START_PAUSE.ordinal()) {
            byte b = this.mSnakeGameState;
            byte b2 = (byte) (b & 2);
            if (b2 == 2) {
                StageView stageView6 = this.view_snake;
                if (stageView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_snake");
                } else {
                    stageView = stageView6;
                }
                stageView.restartGame();
                this.mSnakeGameState = (byte) 1;
                return;
            }
            if (b2 == 0) {
                byte b3 = (byte) (b & 1);
                if (b3 == 1) {
                    StageView stageView7 = this.view_snake;
                    if (stageView7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view_snake");
                    } else {
                        stageView = stageView7;
                    }
                    stageView.getSnakeIns().stop();
                    this.mSnakeGameState = (byte) 0;
                    return;
                }
                if (b3 == 0) {
                    StageView stageView8 = this.view_snake;
                    if (stageView8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view_snake");
                    } else {
                        stageView = stageView8;
                    }
                    stageView.getSnakeIns().start();
                    this.mSnakeGameState = (byte) 1;
                }
            }
        }
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        CustomImageView customImageView = this.td_right;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_right");
            customImageView = null;
        }
        customImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeFragment.bindListener$lambda$0(SnakeFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView3 = this.td_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_right");
            customImageView3 = null;
        }
        companion.attachViewOnTouchListener(customImageView3);
        CustomImageView customImageView4 = this.td_up;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_up");
            customImageView4 = null;
        }
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeFragment.bindListener$lambda$1(SnakeFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion2 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView5 = this.td_up;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_up");
            customImageView5 = null;
        }
        companion2.attachViewOnTouchListener(customImageView5);
        CustomImageView customImageView6 = this.td_down;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_down");
            customImageView6 = null;
        }
        customImageView6.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeFragment.bindListener$lambda$2(SnakeFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion3 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView7 = this.td_down;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_down");
            customImageView7 = null;
        }
        companion3.attachViewOnTouchListener(customImageView7);
        CustomImageView customImageView8 = this.td_left;
        if (customImageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_left");
            customImageView8 = null;
        }
        customImageView8.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeFragment.bindListener$lambda$3(SnakeFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion4 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView9 = this.td_left;
        if (customImageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_left");
            customImageView9 = null;
        }
        companion4.attachViewOnTouchListener(customImageView9);
        CustomImageView customImageView10 = this.td_start;
        if (customImageView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_start");
            customImageView10 = null;
        }
        customImageView10.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.SnakeFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnakeFragment.bindListener$lambda$4(SnakeFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion5 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView11 = this.td_start;
        if (customImageView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("td_start");
        } else {
            customImageView2 = customImageView11;
        }
        companion5.attachViewOnTouchListener(customImageView2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$0(SnakeFragment snakeFragment, View view) {
        StageView stageView = snakeFragment.view_snake;
        if (stageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView = null;
        }
        stageView.moveRight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$1(SnakeFragment snakeFragment, View view) {
        StageView stageView = snakeFragment.view_snake;
        if (stageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView = null;
        }
        stageView.moveForward();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$2(SnakeFragment snakeFragment, View view) {
        StageView stageView = snakeFragment.view_snake;
        if (stageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView = null;
        }
        stageView.moveBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$3(SnakeFragment snakeFragment, View view) {
        StageView stageView = snakeFragment.view_snake;
        if (stageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView = null;
        }
        stageView.moveLeft();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$4(SnakeFragment snakeFragment, View view) {
        byte b = snakeFragment.mSnakeGameState;
        byte b2 = (byte) (b & 2);
        StageView stageView = null;
        if (b2 == 2) {
            StageView stageView2 = snakeFragment.view_snake;
            if (stageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            } else {
                stageView = stageView2;
            }
            stageView.restartGame();
            snakeFragment.mSnakeGameState = (byte) 1;
            return;
        }
        if (b2 == 0) {
            byte b3 = (byte) (b & 1);
            if (b3 == 1) {
                StageView stageView3 = snakeFragment.view_snake;
                if (stageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_snake");
                } else {
                    stageView = stageView3;
                }
                stageView.getSnakeIns().stop();
                snakeFragment.mSnakeGameState = (byte) 0;
                return;
            }
            if (b3 == 0) {
                StageView stageView4 = snakeFragment.view_snake;
                if (stageView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_snake");
                } else {
                    stageView = stageView4;
                }
                stageView.getSnakeIns().start();
                snakeFragment.mSnakeGameState = (byte) 1;
            }
        }
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameFrame(View view) {
        Bitmap bitmap;
        if (this.mRecIsDetaced) {
            return;
        }
        StageView stageView = null;
        Bitmap convertViewToBitmap = view != null ? BitmapUtil.convertViewToBitmap(view, view.getWidth(), view.getHeight()) : null;
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.getTextBounds("", 0, "".length(), rect);
        Bitmap zoomImage = BitmapUtils.INSTANCE.zoomImage(convertViewToBitmap, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue());
        byte b = this.mSnakeGameState;
        if (b == 2) {
            this.gamePaint.setTextSize(this.fontVal * 1.0f);
            this.gamePaint.setColor(SupportMenu.CATEGORY_MASK);
            Rect rect2 = new Rect();
            bitmap = zoomImage;
            this.gamePaint.getTextBounds("GAME OVER!", 0, "GAME OVER!".length(), rect2);
            float width = (rect2.width() / "GAME OVER!".length()) * 1.0f;
            Intrinsics.checkNotNull(bitmap != null ? Integer.valueOf(bitmap.getHeight()) : null);
            customBitmap("GAME OVER!", width, ((r4.intValue() - 14) / 2) * 1.0f, 16, this.gamePaint, bitmap);
            StageView stageView2 = this.view_snake;
            if (stageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            } else {
                stageView = stageView2;
            }
            String str = "SCORE:" + stageView.getSnakeIns().getScore();
            this.scorePaint.setColor(-1);
            this.scorePaint.setTextSize(this.fontVal * 1.0f);
            this.scorePaint.getTextBounds(str, 0, str.length(), rect2);
            customBitmap(str, (rect2.width() / str.length()) * 1.0f, ((bitmap.getHeight() - 14) / 4) * 3 * 1.0f, 16, this.scorePaint, bitmap);
        } else if (b != 1 && b != 0) {
            bitmap = zoomImage;
        } else {
            if (isDetached()) {
                return;
            }
            StageView stageView3 = this.view_snake;
            if (stageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
                stageView3 = null;
            }
            Snake snakeIns = stageView3.getSnakeIns();
            String str2 = "S:" + (snakeIns != null ? Integer.valueOf(snakeIns.getScore()) : null);
            paint.setColor(-16711936);
            paint.getTextBounds(str2, 0, str2.length(), rect);
            bitmap = zoomImage;
            customBitmap$default(this, str2, (rect.width() / str2.length()) * 1.0f, 53.0f, 16, paint, bitmap, -16711936, 0, 128, null);
            StageView stageView4 = this.view_snake;
            if (stageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
                stageView4 = null;
            }
            Snake snakeIns2 = stageView4.getSnakeIns();
            String str3 = "L:" + (snakeIns2 != null ? Long.valueOf(snakeIns2.getLevel()) : null);
            paint.setColor(InputDeviceCompat.SOURCE_ANY);
            paint.getTextBounds(str3, 0, str3.length(), rect);
            customBitmap(str3, (rect.width() / str3.length()) * 1.0f, 53.0f, 16, paint, bitmap, InputDeviceCompat.SOURCE_ANY, 11);
        }
        String str4 = ContextHolder.getContext().getFilesDir().getAbsolutePath() + File.separator + "image_test.png";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str4);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception unused) {
        }
        SendCore sendCore = SendCore.INSTANCE;
        byte[] readFileBytes = FileUtil.readFileBytes(str4);
        Intrinsics.checkNotNullExpressionValue(readFileBytes, "readFileBytes(...)");
        sendCore.sendCameraData(readFileBytes);
        if (this.mHandler.get() == null) {
            this.mHandler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler();
        }
        Handler handler = this.mHandler.get();
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.wifiled.ipixels.ui.game.SnakeFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SnakeFragment.onGameFrame$lambda$6(SnakeFragment.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onGameFrame$lambda$6(SnakeFragment snakeFragment) {
        TextView textView = null;
        StageView stageView = null;
        if (((byte) (snakeFragment.mSnakeGameState & 2)) == 2) {
            TextView textView2 = snakeFragment.tv_snake_point;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_snake_point");
                textView2 = null;
            }
            UtilsExtensionKt.hide(textView2);
            TextView textView3 = snakeFragment.tv_snake_level;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_snake_level");
                textView3 = null;
            }
            UtilsExtensionKt.hide(textView3);
            ThreadUtils.delay(200L);
            StageView stageView2 = snakeFragment.view_snake;
            if (stageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            } else {
                stageView = stageView2;
            }
            stageView.notifyInvalid();
            return;
        }
        TextView textView4 = snakeFragment.tv_snake_point;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_snake_point");
            textView4 = null;
        }
        UtilsExtensionKt.show(textView4);
        TextView textView5 = snakeFragment.tv_snake_level;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_snake_level");
            textView5 = null;
        }
        UtilsExtensionKt.show(textView5);
        if (snakeFragment.mRecIsDetaced) {
            return;
        }
        String string = snakeFragment.getString(R.string.score);
        StageView stageView3 = snakeFragment.view_snake;
        if (stageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView3 = null;
        }
        String str = string + ": " + Integer.valueOf(stageView3.getScore());
        TextView textView6 = snakeFragment.tv_snake_point;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_snake_point");
            textView6 = null;
        }
        textView6.setText(str);
        String string2 = snakeFragment.getString(R.string.level);
        StageView stageView4 = snakeFragment.view_snake;
        if (stageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_snake");
            stageView4 = null;
        }
        String str2 = string2 + ": " + Integer.valueOf(stageView4.getLevel());
        TextView textView7 = snakeFragment.tv_snake_level;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_snake_level");
        } else {
            textView = textView7;
        }
        textView.setText(str2);
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameOver() {
        this.mSnakeGameState = (byte) 2;
    }

    public static /* synthetic */ void customBitmap$default(SnakeFragment snakeFragment, String str, float f, float f2, int i, Paint paint, Bitmap bitmap, int i2, int i3, int i4, Object obj) {
        if ((i4 & 64) != 0) {
            i2 = 0;
        }
        if ((i4 & 128) != 0) {
            i3 = 0;
        }
        snakeFragment.customBitmap(str, f, f2, i, paint, bitmap, i2, i3);
    }

    public final void customBitmap(String info, float left, float top, int typeface, Paint paint, Bitmap bitmap, int changeColor, int iOffset) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Canvas canvas = new Canvas(bitmap);
        char[] charArray = info.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            int indexOf$default = StringsKt.indexOf$default((CharSequence) info, ":", 0, false, 6, (Object) null);
            if (i > indexOf$default && indexOf$default != -1) {
                paint.setColor(changeColor);
            }
            int i2 = this.fontVal;
            canvas.drawBitmap(TextAgreement.getCharBitmap(c, i2, i2, typeface, paint.getColor(), false), (i + iOffset) * left, top, paint);
        }
    }

    public final void customBitmap(String info, float left, float top, int typeface, Paint paint, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Canvas canvas = new Canvas(bitmap);
        char[] charArray = info.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        int length = charArray.length;
        int i = 0;
        while (i < length) {
            char c = charArray[i];
            int i2 = this.fontVal;
            int i3 = typeface;
            canvas.drawBitmap(TextAgreement.getCharBitmap(c, i2, i2, i3, paint.getColor(), false), i * left, top, paint);
            i++;
            typeface = i3;
        }
    }

    public final void hide() {
        ConstraintLayout constraintLayout = this.cl_portait_snake;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_portait_snake");
            constraintLayout = null;
        }
        UtilsExtensionKt.hide(constraintLayout);
    }

    public final void show() {
        ConstraintLayout constraintLayout = this.cl_portait_snake;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_portait_snake");
            constraintLayout = null;
        }
        UtilsExtensionKt.show(constraintLayout);
    }
}
