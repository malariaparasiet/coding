package com.example.admin.balatetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.baselib.CoreBase;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.gameview.GameAction;
import com.wifiled.gameview.GameCallback;
import com.wifiled.gameview.balatetris.model.Cell;
import com.wifiled.gameview.balatetris.model.TetrisGround;
import com.wifiled.gameview.snake.utils.SyncObject;
import com.wifiled.gameview.utils.TextAgreement;
import com.wifiled.gameview.utils.ThreadPoolUtil;
import com.wifiled.gameview.utils.TimeHelper;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;

/* compiled from: TetrisView.kt */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 \u0087\u00012\u00020\u00012\u00020\u0002:\u0004\u0087\u0001\u0088\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0005\u0010\tB#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0005\u0010\fJ(\u0010H\u001a\u00020 2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000b2\u0006\u0010L\u001a\u00020\u000bH\u0014J\u0018\u0010M\u001a\u00020 2\u0006\u0010N\u001a\u00020\u000b2\u0006\u0010O\u001a\u00020\u000bH\u0014J\u0010\u0010;\u001a\u00020 2\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u00103\u001a\u00020 2\u0006\u00103\u001a\u00020<H\u0016J\u0012\u0010P\u001a\u00020 2\b\u0010Q\u001a\u0004\u0018\u00010GH\u0016J\b\u0010R\u001a\u00020 H\u0016J\b\u0010S\u001a\u00020 H\u0016J\b\u0010T\u001a\u00020 H\u0016J\b\u0010U\u001a\u00020 H\u0016J\u0006\u0010V\u001a\u00020 J\b\u0010W\u001a\u00020 H\u0016J\b\u0010X\u001a\u00020 H\u0016J\b\u0010Y\u001a\u00020 H\u0016J\u0010\u0010Z\u001a\u00020 2\u0006\u0010[\u001a\u00020\u0018H\u0014J\b\u0010\\\u001a\u00020 H\u0002J\u0010\u0010]\u001a\u00020 2\u0006\u0010[\u001a\u00020\u0018H\u0002J\u0010\u0010^\u001a\u00020 2\u0006\u0010[\u001a\u00020\u0018H\u0002J\u0010\u0010_\u001a\u00020 2\u0006\u0010[\u001a\u00020\u0018H\u0002J\u0010\u0010`\u001a\u00020 2\u0006\u0010[\u001a\u00020\u0018H\u0002J\u0010\u0010a\u001a\u00020 2\u0006\u0010[\u001a\u00020\u0018H\u0002J\b\u0010d\u001a\u00020 H\u0002J\b\u0010e\u001a\u00020 H\u0002J\u0006\u0010h\u001a\u00020 J\b\u0010i\u001a\u00020\u001aH\u0002J\b\u0010j\u001a\u00020 H\u0002J\b\u0010k\u001a\u00020 H\u0002J\b\u0010l\u001a\u00020\u000bH\u0002J\u0010\u0010m\u001a\u00020\u001a2\u0006\u0010n\u001a\u00020\u000bH\u0002J\u0010\u0010o\u001a\u00020 2\u0006\u0010n\u001a\u00020\u000bH\u0002J\b\u0010p\u001a\u00020\u001aH\u0002J\u000e\u0010s\u001a\u00020 2\u0006\u0010t\u001a\u00020rJ\b\u0010u\u001a\u00020 H\u0002J\b\u0010v\u001a\u00020\u001aH\u0002J\b\u0010w\u001a\u00020\u001aH\u0002J\u0006\u0010x\u001a\u00020 J\u0006\u0010y\u001a\u00020 J\u0006\u0010z\u001a\u00020 J\u0006\u0010{\u001a\u00020 J\u0006\u0010|\u001a\u00020 J\u0006\u0010}\u001a\u00020 J>\u0010~\u001a\u00020 2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\u0007\u0010\u0081\u0001\u001a\u00020c2\u0007\u0010\u0082\u0001\u001a\u00020c2\u0007\u0010\u0083\u0001\u001a\u00020\u00122\u0007\u0010\u0084\u0001\u001a\u00020\u000e2\t\b\u0002\u0010\u0085\u0001\u001a\u00020\u000bJ\u0011\u0010\u0086\u0001\u001a\u00020 2\u0006\u0010[\u001a\u00020\u0018H\u0002R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u001b\u0010%\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010$\u001a\u0004\b&\u0010\"R\u000e\u0010(\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00100\"\u0004\b5\u00102R\u000e\u00106\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00107\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001c\"\u0004\b8\u0010\u001eR\u001a\u00109\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001c\"\u0004\b:\u0010\u001eR\u000e\u0010;\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010=\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u00100\"\u0004\b?\u00102R\u000e\u0010@\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010B\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010D0C0CX\u0082\u000e¢\u0006\u0004\n\u0002\u0010ER\u0010\u0010F\u001a\u0004\u0018\u00010GX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020gX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010q\u001a\u0004\u0018\u00010rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0089\u0001"}, d2 = {"Lcom/example/admin/balatetris/TetrisView;", "Landroid/view/View;", "Lcom/wifiled/gameview/GameAction;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mBg", "Landroid/graphics/Bitmap;", "ground", "Lcom/wifiled/gameview/balatetris/model/TetrisGround;", "mPaint", "Landroid/graphics/Paint;", "scorePaint", "gamePaint", "mPrevState", "", "mCanvas", "Landroid/graphics/Canvas;", "bWifiScreenShowFirst", "", "getBWifiScreenShowFirst", "()Z", "setBWifiScreenShowFirst", "(Z)V", "mGameOver", "", "getMGameOver", "()Lkotlin/Unit;", "mGameOver$delegate", "Lkotlin/Lazy;", "mPause", "getMPause", "mPause$delegate", "CELL_SIZE", "ROWS", "COLS", "tetromino", "Lcom/example/admin/balatetris/Tetromino;", "nextOne", "score", "getScore", "()I", "setScore", "(I)V", "level", "getLevel", "setLevel", "lines", "isOverDrawn", "setOverDrawn", "isNoticeDraw", "setNoticeDraw", "frameInterval", "", "index", "getIndex", "setIndex", "speed", "moveDirection", "mWall", "", "Lcom/wifiled/gameview/balatetris/model/Cell;", "[[Lcom/wifiled/gameview/balatetris/model/Cell;", "gameCallback", "Lcom/wifiled/gameview/GameCallback;", "onSizeChanged", "w", "h", "oldw", "oldh", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "setGameCallback", "callback", "moveLeft", "moveRight", "moveBack", "startGame", "pauseGame", "stopGame", "restartGame", "destroy", "onDraw", "canvas", "refreshFrame", "initTetris", "drawTetris", "paintWall", "paintTetromino", "paintNextOne", "mTvSize", "", "paintScore", "paintState", "scoreTable", "", "softDropAction", "canDrop", "landIntoWall", "resetLines", "destroyLines", "isFullCells", "row", "deleteRow", "isGameOver", "mTetrisListener", "Lcom/example/admin/balatetris/TetrisView$TetrisListener;", "setTetrisListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "moveLeftAction", "outOfBounds", "coincide", "rotateRightAction", "moveRightAction", "hardDropAction", "onceAgain", "initInvalid", "notifyInvalid", "customBitmap", "info", "", "left", "top", "paint", "bitmap", "iOffset", "drawOverInfo", "Companion", "TetrisListener", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TetrisView extends View implements GameAction {
    public static final byte Bit0 = 0;
    public static final byte Bit1 = 1;
    public static final byte Bit2 = 2;
    public static final byte Bit3 = 3;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final byte GAME_OVER = 2;
    public static final byte INIT = 3;
    public static final byte PAUSE = 0;
    public static final byte RUNNING = 1;
    public static final String TAG = "TetrisView";
    private static final int fontVal = 105;
    private static int itemHeight;
    private static int itemWidth;
    private static byte state;
    private static int viewHeight;
    private static int viewWidth;
    private int CELL_SIZE;
    private int COLS;
    private int ROWS;
    private boolean bWifiScreenShowFirst;
    private long frameInterval;
    private GameCallback gameCallback;
    private final Paint gamePaint;
    private final TetrisGround ground;
    private int index;
    private boolean isNoticeDraw;
    private boolean isOverDrawn;
    private int level;
    private int lines;
    private Bitmap mBg;
    private Canvas mCanvas;

    /* renamed from: mGameOver$delegate, reason: from kotlin metadata */
    private final Lazy mGameOver;
    private final Paint mPaint;

    /* renamed from: mPause$delegate, reason: from kotlin metadata */
    private final Lazy mPause;
    private byte mPrevState;
    private TetrisListener mTetrisListener;
    private final float mTvSize;
    private Cell[][] mWall;
    private int moveDirection;
    private Tetromino nextOne;
    private int score;
    private final Paint scorePaint;
    private final int[] scoreTable;
    private int speed;
    private Tetromino tetromino;

    /* compiled from: TetrisView.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/example/admin/balatetris/TetrisView$TetrisListener;", "", "gameOver", "", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface TetrisListener {
        void gameOver();
    }

    @Override // com.wifiled.gameview.IGame
    public void frameInterval(long frameInterval) {
    }

    @Override // com.wifiled.gameview.IGame
    public void level(long level) {
    }

    public final boolean getBWifiScreenShowFirst() {
        return this.bWifiScreenShowFirst;
    }

    public final void setBWifiScreenShowFirst(boolean z) {
        this.bWifiScreenShowFirst = z;
    }

    private final Unit getMGameOver() {
        this.mGameOver.getValue();
        return Unit.INSTANCE;
    }

    private final Unit getMPause() {
        this.mPause.getValue();
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TetrisView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.ground = new TetrisGround(1);
        this.mPaint = new Paint();
        this.scorePaint = new Paint();
        this.gamePaint = new Paint();
        this.bWifiScreenShowFirst = true;
        this.mGameOver = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.TetrisView$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        this.mPause = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.TetrisView$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        this.level = 1;
        this.frameInterval = 30L;
        this.moveDirection = -1;
        int i = this.ROWS;
        Cell[][] cellArr = new Cell[i][];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.COLS;
            Cell[] cellArr2 = new Cell[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                cellArr2[i4] = null;
            }
            cellArr[i2] = cellArr2;
        }
        this.mWall = cellArr;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        this.mTvSize = ScreenUtil.px2dp(CoreBase.getContext(), 70.0f) * 1.0f;
        this.scoreTable = new int[]{0, 1, 10, 100, 500};
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TetrisView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.ground = new TetrisGround(1);
        this.mPaint = new Paint();
        this.scorePaint = new Paint();
        this.gamePaint = new Paint();
        this.bWifiScreenShowFirst = true;
        this.mGameOver = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.TetrisView$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        this.mPause = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.TetrisView$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        this.level = 1;
        this.frameInterval = 30L;
        this.moveDirection = -1;
        int i = this.ROWS;
        Cell[][] cellArr = new Cell[i][];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.COLS;
            Cell[] cellArr2 = new Cell[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                cellArr2[i4] = null;
            }
            cellArr[i2] = cellArr2;
        }
        this.mWall = cellArr;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        this.mTvSize = ScreenUtil.px2dp(CoreBase.getContext(), 70.0f) * 1.0f;
        this.scoreTable = new int[]{0, 1, 10, 100, 500};
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TetrisView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.ground = new TetrisGround(1);
        this.mPaint = new Paint();
        this.scorePaint = new Paint();
        this.gamePaint = new Paint();
        this.bWifiScreenShowFirst = true;
        this.mGameOver = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.TetrisView$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        this.mPause = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.TetrisView$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        this.level = 1;
        this.frameInterval = 30L;
        this.moveDirection = -1;
        int i2 = this.ROWS;
        Cell[][] cellArr = new Cell[i2][];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.COLS;
            Cell[] cellArr2 = new Cell[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                cellArr2[i5] = null;
            }
            cellArr[i3] = cellArr2;
        }
        this.mWall = cellArr;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        this.mTvSize = ScreenUtil.px2dp(CoreBase.getContext(), 70.0f) * 1.0f;
        this.scoreTable = new int[]{0, 1, 10, 100, 500};
    }

    /* compiled from: TetrisView.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\u001b\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R\u000e\u0010\u001e\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lcom/example/admin/balatetris/TetrisView$Companion;", "", "<init>", "()V", "TAG", "", "Bit0", "", "Bit1", "Bit2", "Bit3", "PAUSE", DebugCoroutineInfoImplKt.RUNNING, "GAME_OVER", "INIT", "itemWidth", "", "getItemWidth", "()I", "setItemWidth", "(I)V", "itemHeight", "getItemHeight", "setItemHeight", "viewWidth", "getViewWidth", "setViewWidth", "viewHeight", "getViewHeight", "setViewHeight", "fontVal", PlayerFinal.STATE, "getState", "()B", "setState", "(B)V", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getItemWidth() {
            return TetrisView.itemWidth;
        }

        public final void setItemWidth(int i) {
            TetrisView.itemWidth = i;
        }

        public final int getItemHeight() {
            return TetrisView.itemHeight;
        }

        public final void setItemHeight(int i) {
            TetrisView.itemHeight = i;
        }

        public final int getViewWidth() {
            return TetrisView.viewWidth;
        }

        public final void setViewWidth(int i) {
            TetrisView.viewWidth = i;
        }

        public final int getViewHeight() {
            return TetrisView.viewHeight;
        }

        public final void setViewHeight(int i) {
            TetrisView.viewHeight = i;
        }

        public final byte getState() {
            return TetrisView.state;
        }

        public final void setState(byte b) {
            TetrisView.state = b;
        }
    }

    public final int getScore() {
        return this.score;
    }

    public final void setScore(int i) {
        this.score = i;
    }

    public final int getLevel() {
        return this.level;
    }

    public final void setLevel(int i) {
        this.level = i;
    }

    /* renamed from: isOverDrawn, reason: from getter */
    public final boolean getIsOverDrawn() {
        return this.isOverDrawn;
    }

    public final void setOverDrawn(boolean z) {
        this.isOverDrawn = z;
    }

    /* renamed from: isNoticeDraw, reason: from getter */
    public final boolean getIsNoticeDraw() {
        return this.isNoticeDraw;
    }

    public final void setNoticeDraw(boolean z) {
        this.isNoticeDraw = z;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        refreshFrame();
        int i = this.ROWS;
        Cell[][] cellArr = new Cell[i][];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.COLS;
            Cell[] cellArr2 = new Cell[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                cellArr2[i4] = null;
            }
            cellArr[i2] = cellArr2;
        }
        this.mWall = cellArr;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = 930;
        viewHeight = 930;
        itemHeight = 930 / this.ground.getHEIGHT_COUNT();
        int width_count = viewWidth / this.ground.getWIDTH_COUNT();
        itemWidth = width_count;
        this.CELL_SIZE = width_count;
        this.ROWS = (viewHeight - (itemHeight * 2)) / width_count;
        this.COLS = (viewWidth - (width_count * 20)) / width_count;
    }

    @Override // com.wifiled.gameview.GameAction
    public void setGameCallback(GameCallback callback) {
        this.gameCallback = callback;
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveLeft() {
        moveLeftAction();
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveRight() {
        moveRightAction();
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveBack() {
        softDropAction();
    }

    @Override // com.wifiled.gameview.GameAction
    public void startGame() {
        if (state == 0) {
            state = (byte) 1;
        } else {
            state = (byte) 1;
            this.isOverDrawn = false;
            this.moveDirection = -1;
            this.isNoticeDraw = false;
            this.tetromino = Tetromino.INSTANCE.randomOne();
            this.nextOne = Tetromino.INSTANCE.randomOne();
        }
        refreshFrame();
    }

    public final void pauseGame() {
        state = (byte) 0;
    }

    @Override // com.wifiled.gameview.GameAction
    public void stopGame() {
        state = (byte) 0;
        resetLines();
    }

    @Override // com.wifiled.gameview.GameAction
    public void restartGame() {
        onceAgain();
    }

    @Override // com.wifiled.gameview.GameAction
    public void destroy() {
        this.mPrevState = (byte) 2;
        this.tetromino = null;
        this.nextOne = null;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        this.ground.drawMe(canvas, this.mPaint, itemWidth, itemHeight, state);
        byte b = state;
        if (b == 2) {
            GameCallback gameCallback = this.gameCallback;
            if (gameCallback != null) {
                gameCallback.onGameOver();
            }
            this.isOverDrawn = true;
            if (this.isNoticeDraw) {
                drawOverInfo(canvas);
                resetLines();
            }
            Log.d(getClass().getSimpleName(), "#2.0.1# run onDraw--> isNoticeDraw:" + this.isNoticeDraw);
            return;
        }
        if (b == 3) {
            if (this.bWifiScreenShowFirst) {
                this.bWifiScreenShowFirst = false;
                initTetris(canvas);
                GameCallback gameCallback2 = this.gameCallback;
                if (gameCallback2 != null) {
                    gameCallback2.onGameFrame(this);
                    return;
                }
                return;
            }
            return;
        }
        drawTetris(canvas);
    }

    private final void refreshFrame() {
        ThreadPoolUtil.execute(new Runnable() { // from class: com.example.admin.balatetris.TetrisView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TetrisView.refreshFrame$lambda$2(TetrisView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshFrame$lambda$2(TetrisView tetrisView) {
        GameCallback gameCallback;
        if (TimeHelper.INSTANCE.allowSend(50)) {
            byte b = state;
            if (b == 3) {
                if (!SyncObject.INSTANCE.get().getBRet() || (gameCallback = tetrisView.gameCallback) == null) {
                    return;
                }
                gameCallback.onGameFrame(tetrisView);
                return;
            }
            if (b != 1) {
                if (b == 2) {
                    while (!tetrisView.isOverDrawn) {
                    }
                    Thread.sleep(tetrisView.frameInterval);
                    GameCallback gameCallback2 = tetrisView.gameCallback;
                    if (gameCallback2 != null) {
                        gameCallback2.onGameFrame(tetrisView);
                    }
                    Log.d(tetrisView.getClass().getSimpleName(), "#1.0# run async execute--> onGameFrame");
                    return;
                }
                return;
            }
            while (state == 1) {
                int i = 20 - (tetrisView.lines / 10);
                tetrisView.speed = i;
                if (i < 1) {
                    i = 1;
                }
                tetrisView.speed = i;
                int i2 = 21 - i;
                tetrisView.level = i2;
                Log.d(TAG, "level:[" + i2 + "] speed:[" + i + "]");
                if (state == 1 && tetrisView.index % tetrisView.speed == 0) {
                    tetrisView.softDropAction();
                    GameCallback gameCallback3 = tetrisView.gameCallback;
                    if (gameCallback3 != null) {
                        gameCallback3.onGameFrame(tetrisView);
                    }
                }
                tetrisView.index++;
                tetrisView.postInvalidate();
                Thread.sleep(tetrisView.speed);
            }
        }
    }

    private final void initTetris(Canvas canvas) {
        try {
            Bitmap bitmap = this.mBg;
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mPaint);
            }
            canvas.translate(itemWidth, itemHeight);
            paintWall(canvas);
            paintScore();
            paintState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void drawTetris(Canvas canvas) {
        try {
            Bitmap bitmap = this.mBg;
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mPaint);
            }
            canvas.translate(itemWidth, itemHeight);
            paintWall(canvas);
            paintTetromino(canvas);
            paintNextOne(canvas);
            paintScore();
            paintState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void paintWall(Canvas canvas) {
        Bitmap image;
        this.mPaint.setColor(Color.parseColor("#2e2f32"));
        Cell[][] cellArr = this.mWall;
        int length = cellArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            Cell[] cellArr2 = cellArr[i];
            int length2 = cellArr2.length;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length2) {
                Cell cell = cellArr2[i4];
                int i6 = i5 + 1;
                int i7 = this.CELL_SIZE;
                int i8 = i5 * i7;
                int i9 = i7 * i2;
                Cell cell2 = this.mWall[i2][i5];
                if (cell2 != null && (image = cell2.getImage()) != null) {
                    canvas.drawBitmap(image, i8, i9, this.mPaint);
                }
                i4++;
                i5 = i6;
            }
            i++;
            i2 = i3;
        }
    }

    private final void paintTetromino(Canvas canvas) {
        Tetromino tetromino = this.tetromino;
        if (tetromino == null) {
            return;
        }
        Cell[] cells = tetromino != null ? tetromino.getCells() : null;
        if (cells != null) {
            for (Cell cell : cells) {
                if (cell != null) {
                    int col = cell.getCol() * this.CELL_SIZE;
                    int row = cell.getRow() * this.CELL_SIZE;
                    Bitmap image = cell.getImage();
                    if (image != null) {
                        canvas.drawBitmap(image, col, row, this.mPaint);
                    }
                }
            }
        }
    }

    private final void paintNextOne(Canvas canvas) {
        Tetromino tetromino = this.nextOne;
        if (tetromino == null) {
            return;
        }
        Cell[] cells = tetromino != null ? tetromino.getCells() : null;
        if (cells != null) {
            for (Cell cell : cells) {
                if (cell != null) {
                    int col = (cell.getCol() + (this.COLS - 10)) * this.CELL_SIZE;
                    int row = (cell.getRow() + 6) * this.CELL_SIZE;
                    Bitmap image = cell.getImage();
                    if (image != null) {
                        canvas.drawBitmap(image, col, row, this.mPaint);
                    }
                }
            }
        }
    }

    private final void paintScore() {
        int i = viewHeight;
        int i2 = i / 10;
        int i3 = this.COLS / 3;
        int i4 = i / 5;
        if (this.mCanvas != null) {
            Paint paint = this.mPaint;
            paint.setColor(SupportMenu.CATEGORY_MASK);
            paint.setTextSize(this.mTvSize);
        }
    }

    private final void paintState() {
        GameCallback gameCallback;
        byte b = state;
        if (b == 0) {
            GameCallback gameCallback2 = this.gameCallback;
            if (gameCallback2 != null) {
                gameCallback2.onGameStop();
                return;
            }
            return;
        }
        if (b != 2 || (gameCallback = this.gameCallback) == null) {
            return;
        }
        gameCallback.onGameOver();
    }

    public final void softDropAction() {
        if (state != 1) {
            return;
        }
        if (canDrop()) {
            Tetromino tetromino = this.tetromino;
            if (tetromino != null) {
                tetromino.softDrop();
                return;
            }
            return;
        }
        landIntoWall();
        int destroyLines = destroyLines();
        this.lines += destroyLines;
        if (destroyLines > 0) {
            this.score += (destroyLines / Cell.INSTANCE.getMIN_UNIT_SIZE()) * 5;
        }
        if (isGameOver()) {
            state = (byte) 2;
            return;
        }
        this.score += 5;
        this.tetromino = this.nextOne;
        this.nextOne = Tetromino.INSTANCE.randomOne();
    }

    private final boolean canDrop() {
        Tetromino tetromino = this.tetromino;
        Cell[] cells = tetromino != null ? tetromino.getCells() : null;
        if (cells != null) {
            for (Cell cell : cells) {
                if (cell != null) {
                    if (cell.getRow() + Cell.INSTANCE.getMIN_UNIT_SIZE() >= this.ROWS - 1) {
                        return false;
                    }
                    if (cell.getRow() < 0 || cell.getCol() < 0) {
                        return true;
                    }
                    if (this.mWall[cell.getRow() + Cell.INSTANCE.getMIN_UNIT_SIZE()][cell.getCol()] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private final void landIntoWall() {
        Tetromino tetromino = this.tetromino;
        Cell[] cells = tetromino != null ? tetromino.getCells() : null;
        if (cells != null) {
            int length = cells.length;
            for (int i = 0; i < length; i++) {
                Cell cell = cells[i];
                Integer valueOf = cell != null ? Integer.valueOf(cell.getRow()) : null;
                Integer valueOf2 = cell != null ? Integer.valueOf(cell.getCol()) : null;
                if (valueOf != null) {
                    valueOf.intValue();
                    if (valueOf2 != null) {
                        valueOf2.intValue();
                        try {
                            this.mWall[valueOf.intValue()][valueOf2.intValue()] = cell;
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
    }

    private final void resetLines() {
        int i = this.ROWS;
        for (int i2 = 0; i2 < i; i2++) {
            Arrays.fill(this.mWall[i2], (Object) null);
        }
        Log.d(TAG, "destroyLines:[" + this.lines + "]");
    }

    private final int destroyLines() {
        int i = this.ROWS;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (isFullCells(i3)) {
                deleteRow(i3);
                i2 += Cell.INSTANCE.getMIN_UNIT_SIZE();
            }
        }
        Log.d(TAG, "destroyLines:[" + i2 + "]");
        return i2;
    }

    private final boolean isFullCells(int row) {
        Cell[] cellArr = this.mWall[row];
        int length = cellArr.length - Cell.INSTANCE.getMIN_UNIT_SIZE();
        int min_unit_size = Cell.INSTANCE.getMIN_UNIT_SIZE();
        if (min_unit_size <= 0) {
            throw new IllegalArgumentException("Step must be positive, was: " + min_unit_size + ".");
        }
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, length, min_unit_size);
        if (progressionLastElement < 0) {
            return true;
        }
        for (int i = 0; cellArr[i] != null; i += min_unit_size) {
            if (i == progressionLastElement) {
                return true;
            }
        }
        return false;
    }

    private final void deleteRow(int row) {
        int min_unit_size = Cell.INSTANCE.getMIN_UNIT_SIZE();
        if (min_unit_size <= row) {
            while (true) {
                System.arraycopy(this.mWall[row - Cell.INSTANCE.getMIN_UNIT_SIZE()], 0, this.mWall[row], 0, this.COLS);
                if (row == min_unit_size) {
                    break;
                } else {
                    row--;
                }
            }
        }
        Arrays.fill(this.mWall[0], (Object) null);
    }

    private final boolean isGameOver() {
        Tetromino tetromino = this.nextOne;
        Cell[] cells = tetromino != null ? tetromino.getCells() : null;
        if (cells != null) {
            int length = cells.length;
            for (int i = 0; i < length; i++) {
                Cell cell = cells[i];
                Integer valueOf = cell != null ? Integer.valueOf(cell.getRow()) : null;
                Integer valueOf2 = cell != null ? Integer.valueOf(cell.getCol()) : null;
                try {
                    Cell[][] cellArr = this.mWall;
                    if (cellArr != null) {
                        Intrinsics.checkNotNull(valueOf);
                        if (cellArr[valueOf.intValue()] != null) {
                            Cell[][] cellArr2 = this.mWall;
                            Intrinsics.checkNotNull(valueOf);
                            Cell[] cellArr3 = cellArr2[valueOf.intValue()];
                            Intrinsics.checkNotNull(valueOf2);
                            if (cellArr3[valueOf2.intValue()] != null) {
                                TetrisListener tetrisListener = this.mTetrisListener;
                                if (tetrisListener != null && tetrisListener != null) {
                                    tetrisListener.gameOver();
                                }
                                state = (byte) 2;
                                GameCallback gameCallback = this.gameCallback;
                                if (gameCallback == null) {
                                    return true;
                                }
                                gameCallback.onGameOver();
                                return true;
                            }
                            continue;
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } catch (Exception e) {
                    Log.d(getClass().getSimpleName(), "error: " + e);
                }
            }
        }
        return false;
    }

    public final void setTetrisListener(TetrisListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mTetrisListener = listener;
    }

    private final void moveLeftAction() {
        Tetromino tetromino;
        if (state != 1) {
            return;
        }
        this.moveDirection = 1;
        Tetromino tetromino2 = this.tetromino;
        if (tetromino2 != null) {
            tetromino2.moveLeft();
        }
        if ((outOfBounds() || coincide()) && (tetromino = this.tetromino) != null) {
            tetromino.moveRight();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean outOfBounds() {
        int min_unit_size;
        boolean z;
        Tetromino tetromino = this.tetromino;
        Cell[] cells = tetromino != null ? tetromino.getCells() : null;
        ArrayList arrayList = new ArrayList();
        Intrinsics.checkNotNull(cells);
        for (Cell cell : cells) {
            Intrinsics.checkNotNull(cell);
            arrayList.add(cell);
        }
        int i = 1;
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.example.admin.balatetris.TetrisView$outOfBounds$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((Cell) t).getCol()), Integer.valueOf(((Cell) t2).getCol()));
                }
            });
        }
        int i2 = 0;
        for (Object obj : arrayList) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Cell cell2 = (Cell) obj;
            int row = cell2.getRow();
            int col = cell2.getCol();
            Log.d(arrayList.getClass().getSimpleName(), "outOfBounds prev index[" + i2 + "] size：" + cells.length + " moveDirection[" + this.moveDirection + "] row[" + row + "] -- col[" + col + "]");
            int i4 = this.moveDirection;
            if (i4 != 0) {
                if (i4 == i && i2 == cells.length - i) {
                    min_unit_size = Cell.INSTANCE.getMIN_UNIT_SIZE();
                    col += min_unit_size;
                }
                z = i;
                Cell[] cellArr = cells;
                ArrayList arrayList2 = arrayList;
                Log.d(arrayList.getClass().getSimpleName(), "outOfBounds after index [" + i2 + "] size：" + cells.length + " moveDirection[" + this.moveDirection + "] row[" + row + "] -- col[" + col + "]");
                if (row >= 0 || row >= this.ROWS || col < 0 || col >= this.COLS) {
                    return z;
                }
                i2 = i3;
                i = z ? 1 : 0;
                cells = cellArr;
                arrayList = arrayList2;
            } else {
                if (i2 == cells.length - i) {
                    min_unit_size = Cell.INSTANCE.getMIN_UNIT_SIZE();
                    col += min_unit_size;
                }
                z = i;
                Cell[] cellArr2 = cells;
                ArrayList arrayList22 = arrayList;
                Log.d(arrayList.getClass().getSimpleName(), "outOfBounds after index [" + i2 + "] size：" + cells.length + " moveDirection[" + this.moveDirection + "] row[" + row + "] -- col[" + col + "]");
                if (row >= 0) {
                }
                return z;
            }
        }
        return false;
    }

    private final boolean coincide() {
        Tetromino tetromino = this.tetromino;
        Cell[] cells = tetromino != null ? tetromino.getCells() : null;
        if (cells != null) {
            for (Cell cell : cells) {
                if (cell != null) {
                    try {
                        if (this.mWall[cell.getRow()][cell.getCol()] != null) {
                            return true;
                        }
                    } catch (Exception unused) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public final void rotateRightAction() {
        Tetromino tetromino;
        if (state != 1) {
            return;
        }
        Tetromino tetromino2 = this.tetromino;
        if (tetromino2 != null) {
            tetromino2.rotateRight();
        }
        if ((outOfBounds() || coincide()) && (tetromino = this.tetromino) != null) {
            tetromino.rotateLeft();
        }
    }

    public final void moveRightAction() {
        Tetromino tetromino;
        if (state != 1) {
            return;
        }
        this.moveDirection = 0;
        Tetromino tetromino2 = this.tetromino;
        if (tetromino2 != null) {
            tetromino2.moveRight();
        }
        if ((outOfBounds() || coincide()) && (tetromino = this.tetromino) != null) {
            tetromino.moveLeft();
        }
    }

    public final void hardDropAction() {
        if (state != 1) {
            return;
        }
        while (canDrop()) {
            Tetromino tetromino = this.tetromino;
            if (tetromino != null) {
                tetromino.softDrop();
            }
        }
        landIntoWall();
        int destroyLines = destroyLines();
        if (this.lines != destroyLines && destroyLines > 0) {
            this.lines = destroyLines;
            this.score += (destroyLines / Cell.INSTANCE.getMIN_UNIT_SIZE()) * 5;
            GameCallback gameCallback = this.gameCallback;
            if (gameCallback != null) {
                gameCallback.onGameFrame(this);
            }
        } else {
            this.score += 5;
            GameCallback gameCallback2 = this.gameCallback;
            if (gameCallback2 != null) {
                gameCallback2.onGameFrame(this);
            }
        }
        if (isGameOver()) {
            state = (byte) 2;
        } else {
            this.tetromino = this.nextOne;
            this.nextOne = Tetromino.INSTANCE.randomOne();
        }
    }

    public final void onceAgain() {
        this.lines = 0;
        this.score = 0;
        this.level = 1;
        this.moveDirection = -1;
        this.isOverDrawn = false;
        state = (byte) 1;
        this.isNoticeDraw = false;
        int i = this.ROWS;
        Cell[][] cellArr = new Cell[i][];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.COLS;
            Cell[] cellArr2 = new Cell[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                cellArr2[i4] = null;
            }
            cellArr[i2] = cellArr2;
        }
        this.mWall = cellArr;
        this.tetromino = Tetromino.INSTANCE.randomOne();
        this.nextOne = Tetromino.INSTANCE.randomOne();
        this.index = 0;
        refreshFrame();
    }

    public final void initInvalid() {
        state = (byte) 3;
        this.bWifiScreenShowFirst = true;
        this.score = 0;
        this.level = 1;
        postInvalidate();
    }

    public final void notifyInvalid() {
        this.isNoticeDraw = true;
        postInvalidate();
    }

    public static /* synthetic */ void customBitmap$default(TetrisView tetrisView, String str, float f, float f2, Paint paint, Bitmap bitmap, int i, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            i = 0;
        }
        tetrisView.customBitmap(str, f, f2, paint, bitmap, i);
    }

    public final void customBitmap(String info, float left, float top, Paint paint, Bitmap bitmap, int iOffset) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Canvas canvas = new Canvas(bitmap);
        char[] charArray = info.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            canvas.drawBitmap(TextAgreement.getCharBitmap(charArray[i], 105, 105, paint.getColor()), (i + iOffset) * left, top, paint);
        }
    }

    private final void drawOverInfo(Canvas canvas) {
        Bitmap createBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        this.gamePaint.setTextSize(105.0f);
        this.gamePaint.setColor(SupportMenu.CATEGORY_MASK);
        Rect rect = new Rect();
        this.gamePaint.getTextBounds("GAME OVER!", 0, "GAME OVER!".length(), rect);
        customBitmap$default(this, "GAME OVER!", ((rect.width() / "GAME OVER!".length()) * 1.0f) + (itemWidth * 2), (viewHeight / 4) * 1.5f, this.gamePaint, createBitmap, 0, 32, null);
        String str = "SCORE:" + this.score;
        this.scorePaint.setColor(-1);
        this.scorePaint.setTextSize(105.0f);
        this.scorePaint.getTextBounds(str, 0, str.length(), rect);
        customBitmap$default(this, str, ((rect.width() / str.length()) * 1.0f) + (itemWidth * 2), (viewHeight / 2) * 1.0f, this.scorePaint, createBitmap, 0, 32, null);
        canvas.drawBitmap(createBitmap, itemWidth * 3.0f, 0.0f, this.gamePaint);
    }
}
