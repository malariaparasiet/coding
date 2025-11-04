package com.wifiled.ipixels.ui.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import com.example.admin.balatetris.TetrisView;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.utils.BitmapUtil;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.gameview.GameCallback;
import com.wifiled.gameview.utils.GridLineView;
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
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: TetrisFragment.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010%\u001a\u00020\u0012H\u0014J\b\u0010&\u001a\u00020'H\u0014J\b\u0010(\u001a\u00020'H\u0014J\u0010\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020'2\u0006\u0010-\u001a\u00020\u0014H\u0016J\b\u0010.\u001a\u00020'H\u0016J\b\u0010/\u001a\u00020'H\u0016J\b\u00100\u001a\u00020'H\u0016J\b\u00101\u001a\u00020'H\u0016J\b\u00102\u001a\u00020'H\u0016J\u0016\u00103\u001a\u00020'2\f\u00104\u001a\b\u0012\u0004\u0012\u00020605H\u0003J\b\u00107\u001a\u00020'H\u0015J\b\u00108\u001a\u00020'H\u0016J\u0012\u00109\u001a\u00020'2\b\u0010:\u001a\u0004\u0018\u000106H\u0016J\u0012\u00109\u001a\u00020'2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010=\u001a\u00020'H\u0016J\b\u0010>\u001a\u00020'H\u0016J\b\u0010?\u001a\u00020'H\u0016J\u0010\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020BH\u0007J@\u0010C\u001a\u00020'2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020\u00122\u0006\u0010J\u001a\u00020\u000f2\u0006\u0010;\u001a\u00020<2\b\b\u0002\u0010K\u001a\u00020\u0012R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/wifiled/ipixels/ui/game/TetrisFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "Lcom/wifiled/gameview/GameCallback;", "<init>", "()V", "mGameState", "", "getMGameState", "()B", "setMGameState", "(B)V", "mHandler", "Ljava/lang/ref/WeakReference;", "Landroid/os/Handler;", "gamePaint", "Landroid/graphics/Paint;", "scorePaint", "fontVal", "", "isSwitchLandScreen", "", "()Z", "setSwitchLandScreen", "(Z)V", "iv_tetris_down", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_tetris_left", "iv_tetris_right", "iv_tetris_rotate", "iv_tetris_start", "tv_tetris_level", "Landroid/widget/TextView;", "tv_tetris_score", "view_tetris", "Lcom/example/admin/balatetris/TetrisView;", "view_tetris_gridline", "Lcom/wifiled/gameview/utils/GridLineView;", "layoutId", "initView", "", "bindData", "onSaveInstanceState", "outState", "Landroid/os/Bundle;", "onHiddenChanged", "hidden", "onStop", "onDetach", "onDestroy", "onDestroyView", "onResume", "vSimple", "views", "", "Landroid/view/View;", "bindListener", "onGameStart", "onGameFrame", "view", "bitmap", "Landroid/graphics/Bitmap;", "onGameStop", "onGameOver", "onGameExit", "onEvent", NotificationCompat.CATEGORY_MESSAGE, "Lcom/wifiled/ipixels/ui/game/LandScreenClickMsg;", "customBitmap", "info", "", "left", "", "top", "typeface", "paint", "iOffset", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TetrisFragment extends BaseFragment implements GameCallback {
    private boolean isSwitchLandScreen;
    private CustomImageView iv_tetris_down;
    private CustomImageView iv_tetris_left;
    private CustomImageView iv_tetris_right;
    private CustomImageView iv_tetris_rotate;
    private CustomImageView iv_tetris_start;
    private byte mGameState;
    private TextView tv_tetris_level;
    private TextView tv_tetris_score;
    private TetrisView view_tetris;
    private GridLineView view_tetris_gridline;
    private WeakReference<Handler> mHandler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler();
    private final Paint gamePaint = new Paint();
    private final Paint scorePaint = new Paint();
    private final int fontVal = 12;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindListener$lambda$2(View view, MotionEvent motionEvent) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean vSimple$lambda$1$lambda$0(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_game_tetris;
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

    public final byte getMGameState() {
        return this.mGameState;
    }

    public final void setMGameState(byte b) {
        this.mGameState = b;
    }

    /* renamed from: isSwitchLandScreen, reason: from getter */
    public final boolean getIsSwitchLandScreen() {
        return this.isSwitchLandScreen;
    }

    public final void setSwitchLandScreen(boolean z) {
        this.isSwitchLandScreen = z;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.iv_tetris_down);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_tetris_down = (CustomImageView) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.iv_tetris_left);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_tetris_left = (CustomImageView) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.iv_tetris_right);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.iv_tetris_right = (CustomImageView) findViewById3;
        View findViewById4 = this.mRootView.findViewById(R.id.iv_tetris_rotate);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.iv_tetris_rotate = (CustomImageView) findViewById4;
        View findViewById5 = this.mRootView.findViewById(R.id.iv_tetris_start);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.iv_tetris_start = (CustomImageView) findViewById5;
        View findViewById6 = this.mRootView.findViewById(R.id.tv_tetris_level);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.tv_tetris_level = (TextView) findViewById6;
        View findViewById7 = this.mRootView.findViewById(R.id.tv_tetris_score);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.tv_tetris_score = (TextView) findViewById7;
        View findViewById8 = this.mRootView.findViewById(R.id.view_tetris);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.view_tetris = (TetrisView) findViewById8;
        View findViewById9 = this.mRootView.findViewById(R.id.view_tetris_gridline);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.view_tetris_gridline = (GridLineView) findViewById9;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        GridLineView gridLineView = this.view_tetris_gridline;
        TetrisView tetrisView = null;
        if (gridLineView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris_gridline");
            gridLineView = null;
        }
        gridLineView.render();
        TetrisView tetrisView2 = this.view_tetris;
        if (tetrisView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
        } else {
            tetrisView = tetrisView2;
        }
        tetrisView.setGameCallback(this);
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(getClass().getSimpleName(), "run onHiddenChanged:" + hidden);
        if (this.isSwitchLandScreen) {
            return;
        }
        TetrisView tetrisView = null;
        if (hidden) {
            TetrisView tetrisView2 = this.view_tetris;
            if (tetrisView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                tetrisView2 = null;
            }
            tetrisView2.stopGame();
            this.mGameState = (byte) 0;
            SendCore.INSTANCE.sendExitCmd(null);
            EventBus.getDefault().unregister(this);
            return;
        }
        TextView textView = this.tv_tetris_level;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_level");
            textView = null;
        }
        UtilsExtensionKt.show(textView);
        TextView textView2 = this.tv_tetris_score;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_score");
            textView2 = null;
        }
        UtilsExtensionKt.show(textView2);
        String string = getString(R.string.score);
        TetrisView tetrisView3 = this.view_tetris;
        if (tetrisView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView3 = null;
        }
        String str = string + ":\n \n" + tetrisView3.getScore();
        TextView textView3 = this.tv_tetris_score;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_score");
            textView3 = null;
        }
        textView3.setText(str);
        String string2 = getString(R.string.level);
        TetrisView tetrisView4 = this.view_tetris;
        if (tetrisView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView4 = null;
        }
        String str2 = string2 + ":\n \n" + tetrisView4.getLevel();
        TextView textView4 = this.tv_tetris_level;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_level");
            textView4 = null;
        }
        textView4.setText(str2);
        TetrisView tetrisView5 = this.view_tetris;
        if (tetrisView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
        } else {
            tetrisView = tetrisView5;
        }
        tetrisView.initInvalid();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), "run onStop");
        TetrisView tetrisView = this.view_tetris;
        if (tetrisView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView = null;
        }
        tetrisView.stopGame();
        this.mGameState = (byte) 0;
        EventBus.getDefault().unregister(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Log.d(getClass().getSimpleName(), "run onDetach");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Log.d(getClass().getSimpleName(), "run onDestroy");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(getClass().getSimpleName(), "run onDestroyView");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        TextView textView = this.tv_tetris_level;
        TetrisView tetrisView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_level");
            textView = null;
        }
        UtilsExtensionKt.show(textView);
        TextView textView2 = this.tv_tetris_score;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_score");
            textView2 = null;
        }
        UtilsExtensionKt.show(textView2);
        String string = getString(R.string.score);
        TetrisView tetrisView2 = this.view_tetris;
        if (tetrisView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView2 = null;
        }
        String str = string + ":\n \n" + tetrisView2.getScore();
        TextView textView3 = this.tv_tetris_score;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_score");
            textView3 = null;
        }
        textView3.setText(str);
        String string2 = getString(R.string.level);
        TetrisView tetrisView3 = this.view_tetris;
        if (tetrisView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView3 = null;
        }
        String str2 = string2 + ":\n \n" + tetrisView3.getLevel();
        TextView textView4 = this.tv_tetris_level;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_level");
            textView4 = null;
        }
        textView4.setText(str2);
        TetrisView tetrisView4 = this.view_tetris;
        if (tetrisView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
        } else {
            tetrisView = tetrisView4;
        }
        tetrisView.initInvalid();
    }

    private final void vSimple(List<View> views) {
        Iterator<T> it = views.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setOnTouchListener(new View.OnTouchListener() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean vSimple$lambda$1$lambda$0;
                    vSimple$lambda$1$lambda$0 = TetrisFragment.vSimple$lambda$1$lambda$0(view, motionEvent);
                    return vSimple$lambda$1$lambda$0;
                }
            });
        }
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        CustomImageView customImageView = this.iv_tetris_right;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_right");
            customImageView = null;
        }
        customImageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean bindListener$lambda$2;
                bindListener$lambda$2 = TetrisFragment.bindListener$lambda$2(view, motionEvent);
                return bindListener$lambda$2;
            }
        });
        CustomImageView customImageView3 = this.iv_tetris_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_right");
            customImageView3 = null;
        }
        customImageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TetrisFragment.bindListener$lambda$3(TetrisFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView4 = this.iv_tetris_right;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_right");
            customImageView4 = null;
        }
        companion.attachViewOnTouchListener(customImageView4);
        CustomImageView customImageView5 = this.iv_tetris_rotate;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_rotate");
            customImageView5 = null;
        }
        customImageView5.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TetrisFragment.bindListener$lambda$4(TetrisFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion2 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView6 = this.iv_tetris_rotate;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_rotate");
            customImageView6 = null;
        }
        companion2.attachViewOnTouchListener(customImageView6);
        CustomImageView customImageView7 = this.iv_tetris_down;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_down");
            customImageView7 = null;
        }
        customImageView7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TetrisFragment.bindListener$lambda$5(TetrisFragment.this, view);
            }
        });
        CustomImageView customImageView8 = this.iv_tetris_down;
        if (customImageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_down");
            customImageView8 = null;
        }
        customImageView8.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean bindListener$lambda$6;
                bindListener$lambda$6 = TetrisFragment.bindListener$lambda$6(TetrisFragment.this, view);
                return bindListener$lambda$6;
            }
        });
        CustomImageViewPlus.Companion companion3 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView9 = this.iv_tetris_down;
        if (customImageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_down");
            customImageView9 = null;
        }
        companion3.attachViewOnTouchListener(customImageView9);
        CustomImageView customImageView10 = this.iv_tetris_left;
        if (customImageView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_left");
            customImageView10 = null;
        }
        customImageView10.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TetrisFragment.bindListener$lambda$7(TetrisFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion4 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView11 = this.iv_tetris_left;
        if (customImageView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_left");
            customImageView11 = null;
        }
        companion4.attachViewOnTouchListener(customImageView11);
        CustomImageView customImageView12 = this.iv_tetris_start;
        if (customImageView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_start");
            customImageView12 = null;
        }
        customImageView12.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TetrisFragment.bindListener$lambda$8(TetrisFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion5 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView13 = this.iv_tetris_start;
        if (customImageView13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_tetris_start");
        } else {
            customImageView2 = customImageView13;
        }
        companion5.attachViewOnTouchListener(customImageView2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$3(TetrisFragment tetrisFragment, View view) {
        TetrisView tetrisView = tetrisFragment.view_tetris;
        if (tetrisView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView = null;
        }
        tetrisView.moveRight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$4(TetrisFragment tetrisFragment, View view) {
        TetrisView tetrisView = tetrisFragment.view_tetris;
        if (tetrisView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView = null;
        }
        tetrisView.rotateRightAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$5(TetrisFragment tetrisFragment, View view) {
        TetrisView tetrisView = tetrisFragment.view_tetris;
        if (tetrisView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView = null;
        }
        tetrisView.softDropAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindListener$lambda$6(TetrisFragment tetrisFragment, View view) {
        TetrisView tetrisView = tetrisFragment.view_tetris;
        if (tetrisView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView = null;
        }
        tetrisView.hardDropAction();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$7(TetrisFragment tetrisFragment, View view) {
        TetrisView tetrisView = tetrisFragment.view_tetris;
        if (tetrisView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView = null;
        }
        tetrisView.moveLeft();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$8(TetrisFragment tetrisFragment, View view) {
        byte b = tetrisFragment.mGameState;
        byte b2 = (byte) (b & 2);
        TetrisView tetrisView = null;
        GridLineView gridLineView = null;
        TetrisView tetrisView2 = null;
        if (b2 == 2) {
            TetrisView tetrisView3 = tetrisFragment.view_tetris;
            if (tetrisView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                tetrisView3 = null;
            }
            tetrisView3.restartGame();
            tetrisFragment.mGameState = (byte) 1;
            GridLineView gridLineView2 = tetrisFragment.view_tetris_gridline;
            if (gridLineView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris_gridline");
            } else {
                gridLineView = gridLineView2;
            }
            gridLineView.render();
            return;
        }
        if (b2 == 0) {
            byte b3 = (byte) (b & 1);
            if (b3 == 1) {
                TetrisView tetrisView4 = tetrisFragment.view_tetris;
                if (tetrisView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                } else {
                    tetrisView2 = tetrisView4;
                }
                tetrisView2.pauseGame();
                tetrisFragment.mGameState = (byte) 0;
                return;
            }
            if (b3 == 0) {
                TetrisView tetrisView5 = tetrisFragment.view_tetris;
                if (tetrisView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                } else {
                    tetrisView = tetrisView5;
                }
                tetrisView.startGame();
                tetrisFragment.mGameState = (byte) 1;
            }
        }
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameFrame(View view) {
        final TetrisFragment tetrisFragment = this;
        TetrisView tetrisView = null;
        Bitmap convertViewToBitmap = view != null ? BitmapUtil.convertViewToBitmap(view, view.getWidth(), view.getHeight()) : null;
        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
        Intrinsics.checkNotNull(convertViewToBitmap);
        Bitmap scaleBitmapCanvas = bitmapUtils.scaleBitmapCanvas(convertViewToBitmap, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue());
        byte b = tetrisFragment.mGameState;
        byte b2 = (byte) (b & 2);
        if (b2 == 0) {
            byte b3 = (byte) (b & 1);
            if (b3 == 1 || b3 == 0) {
                Paint paint = new Paint();
                Rect rect = new Rect();
                paint.setColor(-16711936);
                paint.getTextBounds("S:", 0, "S:".length(), rect);
                Intrinsics.checkNotNull(scaleBitmapCanvas);
                tetrisFragment.customBitmap("S:", (rect.width() / "S:".length()) * 1.0f, 24.0f, 0, paint, scaleBitmapCanvas, 45);
                int height = rect.height();
                TetrisView tetrisView2 = tetrisFragment.view_tetris;
                if (tetrisView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                    tetrisView2 = null;
                }
                String valueOf = String.valueOf(Integer.valueOf(tetrisView2.getScore()));
                paint.getTextBounds(valueOf, 0, valueOf.length(), rect);
                tetrisFragment.customBitmap(valueOf, (rect.width() / valueOf.length()) * 1.0f, height + 23.0f, 16, paint, scaleBitmapCanvas, 45);
                paint.setColor(Color.parseColor("#FFA500"));
                paint.getTextBounds("L:", 0, "L:".length(), rect);
                tetrisFragment = this;
                tetrisFragment.customBitmap("L:", 1 + ((rect.width() / "L:".length()) * 1.0f), 44.0f, 0, paint, scaleBitmapCanvas, 45);
                int height2 = rect.height();
                TetrisView tetrisView3 = tetrisFragment.view_tetris;
                if (tetrisView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                } else {
                    tetrisView = tetrisView3;
                }
                String valueOf2 = String.valueOf(Integer.valueOf(tetrisView.getLevel()));
                paint.getTextBounds(valueOf2, 0, valueOf2.length(), rect);
                tetrisFragment.customBitmap(valueOf2, (rect.width() / valueOf2.length()) * 1.0f, height2 + 44.0f, 0, paint, scaleBitmapCanvas, 45);
            }
        } else if (b2 == 2) {
            if (tetrisFragment.mHandler.get() == null) {
                tetrisFragment.mHandler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler();
            }
            Handler handler = tetrisFragment.mHandler.get();
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        TetrisFragment.onGameFrame$lambda$10(TetrisFragment.this);
                    }
                });
            }
            tetrisFragment.gamePaint.setTextSize(tetrisFragment.fontVal * 1.0f);
            tetrisFragment.gamePaint.setColor(SupportMenu.CATEGORY_MASK);
            Rect rect2 = new Rect();
            tetrisFragment.gamePaint.getTextBounds("GAME OVER!", 0, "GAME OVER!".length(), rect2);
            Intrinsics.checkNotNull(scaleBitmapCanvas);
            customBitmap$default(tetrisFragment, "GAME OVER!", (rect2.width() / "GAME OVER!".length()) * 1.0f, ((scaleBitmapCanvas.getHeight() - 14) / 2) * 1.0f, 0, tetrisFragment.gamePaint, scaleBitmapCanvas, 0, 64, null);
            TetrisView tetrisView4 = tetrisFragment.view_tetris;
            if (tetrisView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            } else {
                tetrisView = tetrisView4;
            }
            String str = "SCORE:" + tetrisView.getScore();
            tetrisFragment.scorePaint.setColor(-1);
            tetrisFragment.scorePaint.setTextSize(tetrisFragment.fontVal * 1.0f);
            tetrisFragment.scorePaint.getTextBounds(str, 0, str.length(), rect2);
            customBitmap$default(tetrisFragment, str, (rect2.width() / str.length()) * 1.0f, ((scaleBitmapCanvas.getHeight() - 14) / 4) * 3 * 1.0f, 0, tetrisFragment.scorePaint, scaleBitmapCanvas, 0, 64, null);
        }
        String str2 = ContextHolder.getContext().getFilesDir().getAbsolutePath() + File.separator + "image_test.png";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            if (scaleBitmapCanvas != null) {
                scaleBitmapCanvas.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception unused) {
        }
        SendCore sendCore = SendCore.INSTANCE;
        byte[] readFileBytes = FileUtil.readFileBytes(str2);
        Intrinsics.checkNotNullExpressionValue(readFileBytes, "readFileBytes(...)");
        sendCore.sendCameraData(readFileBytes);
        if (tetrisFragment.mHandler.get() == null) {
            tetrisFragment.mHandler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler();
        }
        Handler handler2 = tetrisFragment.mHandler.get();
        if (handler2 != null) {
            handler2.post(new Runnable() { // from class: com.wifiled.ipixels.ui.game.TetrisFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    TetrisFragment.onGameFrame$lambda$11(TetrisFragment.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onGameFrame$lambda$10(TetrisFragment tetrisFragment) {
        TextView textView = tetrisFragment.tv_tetris_score;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_score");
            textView = null;
        }
        UtilsExtensionKt.hide(textView);
        TextView textView3 = tetrisFragment.tv_tetris_level;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_level");
        } else {
            textView2 = textView3;
        }
        UtilsExtensionKt.hide(textView2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onGameFrame$lambda$11(TetrisFragment tetrisFragment) {
        TextView textView = null;
        GridLineView gridLineView = null;
        if (((byte) (tetrisFragment.mGameState & 2)) == 2) {
            ThreadUtils.delay(400L);
            Log.d(tetrisFragment.getClass().getSimpleName(), "#2.0# run onGameFrame--> notifyInvalid");
            TetrisView tetrisView = tetrisFragment.view_tetris;
            if (tetrisView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                tetrisView = null;
            }
            tetrisView.notifyInvalid();
            GridLineView gridLineView2 = tetrisFragment.view_tetris_gridline;
            if (gridLineView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris_gridline");
            } else {
                gridLineView = gridLineView2;
            }
            gridLineView.render();
            return;
        }
        TextView textView2 = tetrisFragment.tv_tetris_score;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_score");
            textView2 = null;
        }
        UtilsExtensionKt.show(textView2);
        TextView textView3 = tetrisFragment.tv_tetris_level;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_level");
            textView3 = null;
        }
        UtilsExtensionKt.show(textView3);
        String string = tetrisFragment.getString(R.string.score);
        TetrisView tetrisView2 = tetrisFragment.view_tetris;
        if (tetrisView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView2 = null;
        }
        String str = string + "\n \n" + Integer.valueOf(tetrisView2.getScore());
        TextView textView4 = tetrisFragment.tv_tetris_score;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_score");
            textView4 = null;
        }
        textView4.setText(str);
        String string2 = tetrisFragment.getString(R.string.level);
        TetrisView tetrisView3 = tetrisFragment.view_tetris;
        if (tetrisView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            tetrisView3 = null;
        }
        String str2 = string2 + "\n \n" + Integer.valueOf(tetrisView3.getLevel());
        TextView textView5 = tetrisFragment.tv_tetris_level;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_tetris_level");
        } else {
            textView = textView5;
        }
        textView.setText(str2);
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameOver() {
        this.mGameState = (byte) 2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(LandScreenClickMsg msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        int direction = msg.getDirection();
        TetrisView tetrisView = null;
        if (direction == Constants.DIRECTION.DOWN.ordinal()) {
            TetrisView tetrisView2 = this.view_tetris;
            if (tetrisView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            } else {
                tetrisView = tetrisView2;
            }
            tetrisView.moveBack();
            return;
        }
        if (direction == Constants.DIRECTION.DOWN_LONG_CLICK.ordinal()) {
            TetrisView tetrisView3 = this.view_tetris;
            if (tetrisView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            } else {
                tetrisView = tetrisView3;
            }
            tetrisView.hardDropAction();
            return;
        }
        if (direction == Constants.DIRECTION.LEFT.ordinal()) {
            TetrisView tetrisView4 = this.view_tetris;
            if (tetrisView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            } else {
                tetrisView = tetrisView4;
            }
            tetrisView.moveLeft();
            return;
        }
        if (direction == Constants.DIRECTION.RIGHT.ordinal()) {
            TetrisView tetrisView5 = this.view_tetris;
            if (tetrisView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            } else {
                tetrisView = tetrisView5;
            }
            tetrisView.moveRight();
            return;
        }
        if (direction == Constants.DIRECTION.START_PAUSE.ordinal()) {
            byte b = this.mGameState;
            byte b2 = (byte) (b & 2);
            if (b2 == 2) {
                TetrisView tetrisView6 = this.view_tetris;
                if (tetrisView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                } else {
                    tetrisView = tetrisView6;
                }
                tetrisView.restartGame();
                this.mGameState = (byte) 1;
                return;
            }
            if (b2 == 0) {
                byte b3 = (byte) (b & 1);
                if (b3 == 1) {
                    TetrisView tetrisView7 = this.view_tetris;
                    if (tetrisView7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                    } else {
                        tetrisView = tetrisView7;
                    }
                    tetrisView.pauseGame();
                    this.mGameState = (byte) 0;
                    return;
                }
                if (b3 == 0) {
                    TetrisView tetrisView8 = this.view_tetris;
                    if (tetrisView8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
                    } else {
                        tetrisView = tetrisView8;
                    }
                    tetrisView.startGame();
                    this.mGameState = (byte) 1;
                    return;
                }
                return;
            }
            return;
        }
        if (direction == Constants.DIRECTION.DEFORMATION.ordinal()) {
            TetrisView tetrisView9 = this.view_tetris;
            if (tetrisView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_tetris");
            } else {
                tetrisView = tetrisView9;
            }
            tetrisView.rotateRightAction();
        }
    }

    public static /* synthetic */ void customBitmap$default(TetrisFragment tetrisFragment, String str, float f, float f2, int i, Paint paint, Bitmap bitmap, int i2, int i3, Object obj) {
        tetrisFragment.customBitmap(str, f, f2, i, paint, bitmap, (i3 & 64) != 0 ? 0 : i2);
    }

    public final void customBitmap(String info, float left, float top, int typeface, Paint paint, Bitmap bitmap, int iOffset) {
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
            canvas.drawBitmap(TextAgreement.getCharBitmap(c, i2, i2, i3, paint.getColor(), false), iOffset + (i * left), top, paint);
            i++;
            typeface = i3;
        }
    }
}
