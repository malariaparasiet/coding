package com.wifiled.ipixels.ui.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.os.HandlerCompat;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.utils.BitmapUtil;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.gameview.GameCallback;
import com.wifiled.gameview.pingpong.BallView;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.text.TextAgreement;
import com.wifiled.ipixels.utils.BitmapAndRgbByteUtil;
import com.wifiled.ipixels.utils.BitmapUtils;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.TimeHelper;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.view.customview.CustomImageViewPlus;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PongFragment.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\r\u001a\u00020\u0006H\u0014J\b\u0010\u001d\u001a\u00020\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u001eH\u0014J\b\u0010 \u001a\u00020\u001eH\u0016J\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0015H\u0016J\b\u0010#\u001a\u00020\u001eH\u0016J\b\u0010$\u001a\u00020\u001eH\u0016J\u0012\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010%\u001a\u00020\u001e2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u001eH\u0016J\b\u0010+\u001a\u00020\u001eH\u0016J\b\u0010,\u001a\u00020\u001eH\u0016J@\u0010-\u001a\u00020)2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00122\u0006\u00105\u001a\u00020)2\b\b\u0002\u00106\u001a\u00020\u0006J\b\u00107\u001a\u00020\u001eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0013\u0010\u000e\u001a\u00070\u000f¢\u0006\u0002\b\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/wifiled/ipixels/ui/game/PongFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "Lcom/wifiled/gameview/GameCallback;", "<init>", "()V", "fontVal", "", "mGameState", "", "getMGameState", "()B", "setMGameState", "(B)V", "layoutId", "mHandler", "Landroid/os/Handler;", "Lorg/jspecify/annotations/NonNull;", "gamePaint", "Landroid/graphics/Paint;", "scorePaint", "bFirstShow", "", "view_pong", "Lcom/wifiled/gameview/pingpong/BallView;", "iv_pong_start", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "tv_ping_score", "Landroid/widget/TextView;", "textView", "bindData", "", "bindListener", "onResume", "onHiddenChanged", "hidden", "onStop", "onGameStart", "onGameFrame", "view", "Landroid/view/View;", "ledBitmap", "Landroid/graphics/Bitmap;", "onGameStop", "onGameOver", "onGameExit", "customBitmap", "info", "", "left", "", "top", "typeface", "paint", "bitmap", "iOffset", "showLoadAnimNum", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PongFragment extends BaseFragment implements GameCallback {
    private boolean bFirstShow;
    private final int fontVal = 12;
    private final Paint gamePaint;
    private CustomImageView iv_pong_start;
    private byte mGameState;
    private final Handler mHandler;
    private final Paint scorePaint;
    private TextView textView;
    private TextView tv_ping_score;
    private BallView view_pong;

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_game_pong;
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameExit() {
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameStop() {
    }

    public PongFragment() {
        Handler createAsync = HandlerCompat.createAsync(Looper.getMainLooper());
        Intrinsics.checkNotNullExpressionValue(createAsync, "createAsync(...)");
        this.mHandler = createAsync;
        this.gamePaint = new Paint();
        this.scorePaint = new Paint();
        this.bFirstShow = true;
    }

    public final byte getMGameState() {
        return this.mGameState;
    }

    public final void setMGameState(byte b) {
        this.mGameState = b;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        View findViewById = this.mRootView.findViewById(R.id.view_pong);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.view_pong = (BallView) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.iv_pong_start);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_pong_start = (CustomImageView) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.tv_ping_score);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tv_ping_score = (TextView) findViewById3;
        View findViewById4 = this.mRootView.findViewById(R.id.textView);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.textView = (TextView) findViewById4;
        BallView ballView = this.view_pong;
        if (ballView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            ballView = null;
        }
        ballView.setGameCallback(this);
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        CustomImageView customImageView = this.iv_pong_start;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_pong_start");
            customImageView = null;
        }
        customImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.game.PongFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PongFragment.bindListener$lambda$0(PongFragment.this, view);
            }
        });
        CustomImageViewPlus.Companion companion = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView3 = this.iv_pong_start;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_pong_start");
        } else {
            customImageView2 = customImageView3;
        }
        companion.attachViewOnTouchListener(customImageView2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$0(PongFragment pongFragment, View view) {
        byte b = pongFragment.mGameState;
        byte b2 = (byte) (b & 2);
        BallView ballView = null;
        if (b2 == 2) {
            BallView ballView2 = pongFragment.view_pong;
            if (ballView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_pong");
                ballView2 = null;
            }
            ballView2.restartGame();
            BallView ballView3 = pongFragment.view_pong;
            if (ballView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            } else {
                ballView = ballView3;
            }
            ballView.stopGame();
            view.setEnabled(false);
            pongFragment.showLoadAnimNum();
            return;
        }
        if (b2 == 0) {
            byte b3 = (byte) (b & 1);
            if (b3 == 1) {
                BallView ballView4 = pongFragment.view_pong;
                if (ballView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_pong");
                } else {
                    ballView = ballView4;
                }
                ballView.stopGame();
                pongFragment.mGameState = (byte) 0;
                return;
            }
            if (b3 == 0) {
                boolean z = pongFragment.bFirstShow;
                if (z) {
                    view.setEnabled(false);
                    pongFragment.showLoadAnimNum();
                    pongFragment.bFirstShow = false;
                } else {
                    if (z) {
                        throw new NoWhenBranchMatchedException();
                    }
                    BallView ballView5 = pongFragment.view_pong;
                    if (ballView5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view_pong");
                    } else {
                        ballView = ballView5;
                    }
                    ballView.startGame();
                    pongFragment.mGameState = (byte) 1;
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), "run onResume:");
        BallView ballView = this.view_pong;
        BallView ballView2 = null;
        if (ballView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            ballView = null;
        }
        UtilsExtensionKt.show(ballView);
        BallView ballView3 = this.view_pong;
        if (ballView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
        } else {
            ballView2 = ballView3;
        }
        ballView2.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(getClass().getSimpleName(), "run onHiddenChanged:" + hidden);
        TextView textView = null;
        if (hidden) {
            BallView ballView = this.view_pong;
            if (ballView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_pong");
                ballView = null;
            }
            UtilsExtensionKt.hide(ballView);
            BallView ballView2 = this.view_pong;
            if (ballView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_pong");
                ballView2 = null;
            }
            ballView2.onStop();
            SendCore.INSTANCE.sendExitCmd(null);
            return;
        }
        BallView ballView3 = this.view_pong;
        if (ballView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            ballView3 = null;
        }
        UtilsExtensionKt.show(ballView3);
        BallView ballView4 = this.view_pong;
        if (ballView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            ballView4 = null;
        }
        ballView4.onResume();
        this.bFirstShow = true;
        this.mGameState = (byte) 0;
        TextView textView2 = this.tv_ping_score;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_ping_score");
        } else {
            textView = textView2;
        }
        UtilsExtensionKt.show(textView);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        BallView ballView = this.view_pong;
        BallView ballView2 = null;
        if (ballView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            ballView = null;
        }
        UtilsExtensionKt.hide(ballView);
        BallView ballView3 = this.view_pong;
        if (ballView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
        } else {
            ballView2 = ballView3;
        }
        ballView2.onStop();
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameStart() {
        this.mGameState = (byte) 0;
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameFrame(View view) {
        BallView ballView = null;
        Integer valueOf = view != null ? Integer.valueOf(view.getWidth()) : null;
        Intrinsics.checkNotNull(valueOf);
        Bitmap convertViewToBitmap = BitmapUtil.convertViewToBitmap(view, valueOf.intValue(), view.getHeight());
        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
        Intrinsics.checkNotNull(convertViewToBitmap);
        Bitmap sizeCompres = bitmapUtils.sizeCompres(convertViewToBitmap, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue());
        if (((byte) (this.mGameState & 2)) == 2) {
            this.gamePaint.setTextSize(this.fontVal * 1.0f);
            this.gamePaint.setColor(SupportMenu.CATEGORY_MASK);
            Rect rect = new Rect();
            this.gamePaint.getTextBounds("GAME OVER!", 0, "GAME OVER!".length(), rect);
            float width = (rect.width() / "GAME OVER!".length()) * 1.0f;
            Intrinsics.checkNotNull(sizeCompres != null ? Integer.valueOf(sizeCompres.getHeight()) : null);
            Bitmap customBitmap$default = customBitmap$default(this, "GAME OVER!", width, ((r4.intValue() - 14) / 2) * 1.0f, 16, this.gamePaint, sizeCompres, 0, 64, null);
            BallView ballView2 = this.view_pong;
            if (ballView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            } else {
                ballView = ballView2;
            }
            String str = "SCORE:" + ballView.getMScore();
            this.scorePaint.setColor(-1);
            this.scorePaint.setTextSize(this.fontVal * 1.0f);
            this.scorePaint.getTextBounds(str, 0, str.length(), rect);
            sizeCompres = customBitmap$default(this, str, (rect.width() / str.length()) * 1.0f, ((customBitmap$default.getHeight() - 14) / 4) * 3 * 1.0f, 16, this.scorePaint, customBitmap$default, 0, 64, null);
        }
        byte[] bitmap2RGBData = BitmapAndRgbByteUtil.bitmap2RGBData(sizeCompres);
        SendCore sendCore = SendCore.INSTANCE;
        Intrinsics.checkNotNull(bitmap2RGBData);
        sendCore.sendCameraData(bitmap2RGBData);
        this.mHandler.post(new Runnable() { // from class: com.wifiled.ipixels.ui.game.PongFragment$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PongFragment.onGameFrame$lambda$1(PongFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onGameFrame$lambda$1(PongFragment pongFragment) {
        TextView textView = null;
        BallView ballView = null;
        if (((byte) (pongFragment.mGameState & 2)) == 2) {
            TextView textView2 = pongFragment.tv_ping_score;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_ping_score");
                textView2 = null;
            }
            UtilsExtensionKt.hide(textView2);
            ThreadUtils.delay(200L);
            BallView ballView2 = pongFragment.view_pong;
            if (ballView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            } else {
                ballView = ballView2;
            }
            ballView.notifyInvalid();
            return;
        }
        TextView textView3 = pongFragment.tv_ping_score;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_ping_score");
            textView3 = null;
        }
        UtilsExtensionKt.show(textView3);
        String string = pongFragment.getString(R.string.score);
        BallView ballView3 = pongFragment.view_pong;
        if (ballView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            ballView3 = null;
        }
        String str = string + ":" + Integer.valueOf(ballView3.getMScore());
        TextView textView4 = pongFragment.tv_ping_score;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_ping_score");
        } else {
            textView = textView4;
        }
        textView.setText(str);
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameFrame(Bitmap ledBitmap) {
        Bitmap bitmap;
        if (TimeHelper.INSTANCE.allowSend(120)) {
            if (((byte) (this.mGameState & 2)) == 2) {
                this.gamePaint.setTextSize(this.fontVal * 1.0f);
                this.gamePaint.setColor(SupportMenu.CATEGORY_MASK);
                Rect rect = new Rect();
                this.gamePaint.getTextBounds("GAME OVER!", 0, "GAME OVER!".length(), rect);
                float f = 1;
                float width = ((rect.width() / "GAME OVER!".length()) * 1.0f) + f;
                BallView ballView = null;
                Intrinsics.checkNotNull(ledBitmap != null ? Integer.valueOf(ledBitmap.getHeight()) : null);
                customBitmap$default(this, "GAME OVER!", width, ((r3.intValue() - 14) / 2) * 1.0f, 16, this.gamePaint, ledBitmap, 0, 64, null);
                BallView ballView2 = this.view_pong;
                if (ballView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_pong");
                } else {
                    ballView = ballView2;
                }
                String str = "SCORE:" + ballView.getMScore();
                this.scorePaint.setColor(-1);
                this.scorePaint.setTextSize(this.fontVal * 1.0f);
                this.scorePaint.getTextBounds(str, 0, str.length(), rect);
                bitmap = ledBitmap;
                customBitmap$default(this, str, ((rect.width() / str.length()) * 1.0f) + f, ((ledBitmap.getHeight() - 14) / 4) * 3 * 1.0f, 16, this.scorePaint, bitmap, 0, 64, null);
            } else {
                bitmap = ledBitmap;
            }
            String str2 = ContextHolder.getContext().getFilesDir().getAbsolutePath() + File.separator + "image_test.jpg";
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                if (bitmap != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception unused) {
            }
            SendCore sendCore = SendCore.INSTANCE;
            byte[] readFileBytes = FileUtil.readFileBytes(str2);
            Intrinsics.checkNotNullExpressionValue(readFileBytes, "readFileBytes(...)");
            sendCore.sendCameraData(readFileBytes);
            this.mHandler.post(new Runnable() { // from class: com.wifiled.ipixels.ui.game.PongFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PongFragment.onGameFrame$lambda$2(PongFragment.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onGameFrame$lambda$2(PongFragment pongFragment) {
        TextView textView = null;
        BallView ballView = null;
        if (((byte) (pongFragment.mGameState & 2)) == 2) {
            TextView textView2 = pongFragment.tv_ping_score;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_ping_score");
                textView2 = null;
            }
            UtilsExtensionKt.hide(textView2);
            ThreadUtils.delay(200L);
            BallView ballView2 = pongFragment.view_pong;
            if (ballView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            } else {
                ballView = ballView2;
            }
            ballView.notifyInvalid();
            return;
        }
        TextView textView3 = pongFragment.tv_ping_score;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_ping_score");
            textView3 = null;
        }
        UtilsExtensionKt.show(textView3);
        String string = pongFragment.getString(R.string.score);
        BallView ballView3 = pongFragment.view_pong;
        if (ballView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            ballView3 = null;
        }
        String str = string + ":" + Integer.valueOf(ballView3.getMScore());
        TextView textView4 = pongFragment.tv_ping_score;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_ping_score");
        } else {
            textView = textView4;
        }
        textView.setText(str);
    }

    @Override // com.wifiled.gameview.GameCallback
    public void onGameOver() {
        this.mGameState = (byte) 2;
    }

    public static /* synthetic */ Bitmap customBitmap$default(PongFragment pongFragment, String str, float f, float f2, int i, Paint paint, Bitmap bitmap, int i2, int i3, Object obj) {
        return pongFragment.customBitmap(str, f, f2, i, paint, bitmap, (i3 & 64) != 0 ? 0 : i2);
    }

    public final Bitmap customBitmap(String info, float left, float top, int typeface, Paint paint, Bitmap bitmap, int iOffset) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(copy);
        char[] charArray = info.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            int i2 = this.fontVal;
            canvas.drawBitmap(TextAgreement.getCharBitmap(c, i2, i2, typeface, paint.getColor(), false), (i + iOffset) * left, top, paint);
        }
        Intrinsics.checkNotNull(copy);
        return copy;
    }

    private final void showLoadAnimNum() {
        final Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anticipate);
        loadAnimation.setInterpolator(new LinearInterpolator());
        loadAnimation.setFillAfter(true);
        BallView ballView = this.view_pong;
        if (ballView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view_pong");
            ballView = null;
        }
        ballView.freshBall();
        new CountDownTimer() { // from class: com.wifiled.ipixels.ui.game.PongFragment$showLoadAnimNum$timer$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                TextView textView;
                TextView textView2;
                textView = PongFragment.this.textView;
                TextView textView3 = null;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textView");
                    textView = null;
                }
                textView.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                textView2 = PongFragment.this.textView;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textView");
                } else {
                    textView3 = textView2;
                }
                textView3.startAnimation(loadAnimation);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                TextView textView;
                BallView ballView2;
                CustomImageView customImageView;
                textView = PongFragment.this.textView;
                CustomImageView customImageView2 = null;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textView");
                    textView = null;
                }
                textView.setText("");
                ballView2 = PongFragment.this.view_pong;
                if (ballView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view_pong");
                    ballView2 = null;
                }
                ballView2.startGame();
                PongFragment.this.setMGameState((byte) 1);
                customImageView = PongFragment.this.iv_pong_start;
                if (customImageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("iv_pong_start");
                } else {
                    customImageView2 = customImageView;
                }
                customImageView2.setEnabled(true);
            }
        }.start();
    }
}
