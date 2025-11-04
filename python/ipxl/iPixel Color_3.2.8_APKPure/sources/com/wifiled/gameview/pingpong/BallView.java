package com.wifiled.gameview.pingpong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.gameview.GameAction;
import com.wifiled.gameview.GameCallback;
import com.wifiled.gameview.R;
import com.wifiled.gameview.pingpong.model.Movable;
import com.wifiled.gameview.pingpong.model.PongGround;
import com.wifiled.gameview.utils.TextAgreement;
import com.wifiled.gameview.utils.ThreadPoolUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BallView.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b.\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 ¯\u00012\u00020\u00012\u00020\u0002:\u0002¯\u0001B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\tB#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0007\u0010\fJ\n\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0002J\b\u0010\u0083\u0001\u001a\u00030\u0082\u0001J\n\u0010\u0084\u0001\u001a\u00030\u0082\u0001H\u0002J.\u0010\u0085\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u0086\u0001\u001a\u00020\u000b2\u0007\u0010\u0087\u0001\u001a\u00020\u000b2\u0007\u0010\u0088\u0001\u001a\u00020\u000b2\u0007\u0010\u0089\u0001\u001a\u00020\u000bH\u0014J\u001c\u0010\u008a\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u008b\u0001\u001a\u00020\u000b2\u0007\u0010\u008c\u0001\u001a\u00020\u000bH\u0014J\u0013\u0010\u008d\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u008e\u0001\u001a\u00020\u001eH\u0015J\t\u0010\u008f\u0001\u001a\u00020 H\u0002J\u0013\u0010\u0090\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u008e\u0001\u001a\u00020\u001eH\u0002J\n\u0010\u0091\u0001\u001a\u00030\u0082\u0001H\u0002J\u001a\u0010\u0092\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u0093\u0001\u001a\u00020\u000b2\u0007\u0010\u0094\u0001\u001a\u00020\u000bJ\u0013\u0010\u0095\u0001\u001a\u00020\u001a2\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001H\u0017J\u0012\u0010\u0098\u0001\u001a\u00020\u000b2\u0007\u0010\u0099\u0001\u001a\u00020\u0018H\u0002J\u0011\u0010j\u001a\u00030\u0082\u00012\u0006\u0010j\u001a\u00020kH\u0016J\n\u0010\u009a\u0001\u001a\u00030\u0082\u0001H\u0016J\n\u0010\u009b\u0001\u001a\u00030\u0082\u0001H\u0016J\n\u0010\u009c\u0001\u001a\u00030\u0082\u0001H\u0016J\n\u0010\u009d\u0001\u001a\u00030\u0082\u0001H\u0016J\b\u0010\u009e\u0001\u001a\u00030\u0082\u0001J\n\u0010\u009f\u0001\u001a\u00030\u0082\u0001H\u0016J\t\u0010 \u0001\u001a\u00020\u001aH\u0002J\u0013\u0010¡\u0001\u001a\u00030\u0082\u00012\u0007\u0010¡\u0001\u001a\u00020kH\u0016J\n\u0010¢\u0001\u001a\u00030\u0082\u0001H\u0016J\u0016\u0010£\u0001\u001a\u00030\u0082\u00012\n\u0010¤\u0001\u001a\u0005\u0018\u00010\u0080\u0001H\u0016J\b\u0010¥\u0001\u001a\u00030\u0082\u0001J@\u0010¦\u0001\u001a\u00030\u0082\u00012\b\u0010§\u0001\u001a\u00030¨\u00012\u0007\u0010©\u0001\u001a\u00020\u00182\u0007\u0010ª\u0001\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00102\u0007\u0010«\u0001\u001a\u00020 2\t\b\u0002\u0010¬\u0001\u001a\u00020\u000bJ\b\u0010\u00ad\u0001\u001a\u00030\u0082\u0001J\u0013\u0010®\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u008e\u0001\u001a\u00020\u001eH\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R\u001a\u0010*\u001a\u00020+X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R\u001a\u00109\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010$\"\u0004\b@\u0010&R\u001a\u0010A\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010$\"\u0004\bC\u0010&R\u001a\u0010D\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010$\"\u0004\bF\u0010&R\u001a\u0010G\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010$\"\u0004\bI\u0010&R\u001a\u0010J\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010$\"\u0004\bL\u0010&R\u001a\u0010M\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010$\"\u0004\bO\u0010&R\u001a\u0010P\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010$\"\u0004\bR\u0010&R\u001a\u0010S\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010$\"\u0004\bU\u0010&R\u001a\u0010V\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010$\"\u0004\bX\u0010&R\u001a\u0010Y\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010$\"\u0004\b[\u0010&R\u001a\u0010\\\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010$\"\u0004\b^\u0010&R\u001a\u0010_\u001a\u00020`X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010a\"\u0004\bb\u0010cR\u001a\u0010d\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u001a\u0010h\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010e\"\u0004\bi\u0010gR\u000e\u0010j\u001a\u00020kX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020kX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010m\u001a\b\u0012\u0004\u0012\u00020o0nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR\u0011\u0010t\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\bu\u0010vR\u0011\u0010w\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\bx\u0010vR\u000e\u0010y\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010{\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010v\"\u0004\b}\u0010~R\u0011\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006°\u0001"}, d2 = {"Lcom/wifiled/gameview/pingpong/BallView;", "Landroid/view/View;", "Lcom/wifiled/gameview/GameAction;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "ground", "Lcom/wifiled/gameview/pingpong/model/PongGround;", "paint", "Landroid/graphics/Paint;", "scorePaint", "gamePaint", "itemWidth", "itemHeight", "viewWidth", "viewHeight", "gameFontSize", "", "bFirstShowWifiScreen", "", "mPaint", "mIsDraw", "mCanvas", "Landroid/graphics/Canvas;", "mBg", "Landroid/graphics/Bitmap;", "bBallFresh", "width", "getWidth", "()F", "setWidth", "(F)V", "height", "getHeight", "setHeight", "movable", "Lcom/wifiled/gameview/pingpong/model/Movable;", "getMovable", "()Lcom/wifiled/gameview/pingpong/model/Movable;", "setMovable", "(Lcom/wifiled/gameview/pingpong/model/Movable;)V", "current", "", "getCurrent", "()D", "setCurrent", "(D)V", "gravityAcceleration", "getGravityAcceleration", "setGravityAcceleration", "mScore", "getMScore", "()I", "setMScore", "(I)V", "boardWdith", "getBoardWdith", "setBoardWdith", "boardHeight", "getBoardHeight", "setBoardHeight", "board2Top", "getBoard2Top", "setBoard2Top", "board2Left", "getBoard2Left", "setBoard2Left", "ballRadius", "getBallRadius", "setBallRadius", "ballX", "getBallX", "setBallX", "ballY", "getBallY", "setBallY", "vx", "getVx", "setVx", "vy", "getVy", "setVy", "brickWidth", "getBrickWidth", "setBrickWidth", "brickHeight", "getBrickHeight", "setBrickHeight", "isOver", "", "()B", "setOver", "(B)V", "isOverDrawn", "()Z", "setOverDrawn", "(Z)V", "isNoticeDraw", "setNoticeDraw", "frameInterval", "", "lastFrameTime", "brickList", "", "Lcom/wifiled/gameview/pingpong/Brick;", "getBrickList", "()Ljava/util/List;", "setBrickList", "(Ljava/util/List;)V", "ballPaint", "getBallPaint", "()Landroid/graphics/Paint;", "boardPaint", "getBoardPaint", "paintLine", "canvasData", "paintData", "getPaintData", "setPaintData", "(Landroid/graphics/Paint;)V", "gameCallback", "Lcom/wifiled/gameview/GameCallback;", "initView", "", "onResume", "resetBall", "onSizeChanged", "w", "h", "oldw", "oldh", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onDraw", "canvas", "paintCanvasData", "resetAllPathsBeforeDraw", "initMoveBall", "createBricks", "row", "col", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "dip", "dipValue", "stopGame", "restartGame", "moveRight", "destroy", "freshBall", "startGame", "isExpired", "level", "moveLeft", "setGameCallback", "callback", "onStop", "customBitmap", "info", "", "left", "top", "bitmap", "iOffset", "notifyInvalid", "drawOverInfo", "Companion", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BallView extends View implements GameAction {
    public static final byte Bit0 = 0;
    public static final byte Bit1 = 1;
    public static final byte Bit2 = 2;
    public static final byte Bit3 = 3;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DOWN_ZERO = 60;
    private static int GROUND_BOTTOM = 0;
    public static final String TAG = "BallView";
    public static final int UP_ZERO = 25;
    public static final int V_MAX = 55;
    public static final int V_MIN = 30;
    public static final int WOODEDGE = 240;
    private static final int fontVal = 105;
    private boolean bBallFresh;
    private boolean bFirstShowWifiScreen;
    private final Paint ballPaint;
    private float ballRadius;
    private float ballX;
    private float ballY;
    private float board2Left;
    private float board2Top;
    private float boardHeight;
    private final Paint boardPaint;
    private float boardWdith;
    private float brickHeight;
    private List<Brick> brickList;
    private float brickWidth;
    private Canvas canvasData;
    private double current;
    private long frameInterval;
    private GameCallback gameCallback;
    private float gameFontSize;
    private final Paint gamePaint;
    private float gravityAcceleration;
    private final PongGround ground;
    private float height;
    private boolean isNoticeDraw;
    private byte isOver;
    private boolean isOverDrawn;
    private int itemHeight;
    private int itemWidth;
    private long lastFrameTime;
    private Bitmap mBg;
    private Canvas mCanvas;
    private boolean mIsDraw;
    private Paint mPaint;
    private int mScore;
    public Movable movable;
    private final Paint paint;
    private Paint paintData;
    private final Paint paintLine;
    private final Paint scorePaint;
    private int viewHeight;
    private int viewWidth;
    private float vx;
    private float vy;
    private float width;

    private final void resetAllPathsBeforeDraw(Canvas canvas) {
    }

    @Override // com.wifiled.gameview.IGame
    public void level(long level) {
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveLeft() {
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveRight() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BallView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.ground = new PongGround();
        this.paint = new Paint();
        this.scorePaint = new Paint();
        this.gamePaint = new Paint();
        this.gameFontSize = 105.0f;
        this.bFirstShowWifiScreen = true;
        this.gravityAcceleration = 1000.0f;
        this.vx = 20.0f;
        this.vy = -20.0f;
        this.frameInterval = 50L;
        this.brickList = new ArrayList();
        this.ballPaint = new Paint();
        this.boardPaint = new Paint();
        this.paintLine = new Paint();
        this.paintData = new Paint();
    }

    /* compiled from: BallView.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/wifiled/gameview/pingpong/BallView$Companion;", "", "<init>", "()V", "TAG", "", "Bit0", "", "Bit1", "Bit2", "Bit3", "V_MAX", "", "V_MIN", "WOODEDGE", "GROUND_BOTTOM", "getGROUND_BOTTOM", "()I", "setGROUND_BOTTOM", "(I)V", "UP_ZERO", "DOWN_ZERO", "fontVal", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getGROUND_BOTTOM() {
            return BallView.GROUND_BOTTOM;
        }

        public final void setGROUND_BOTTOM(int i) {
            BallView.GROUND_BOTTOM = i;
        }
    }

    @Override // android.view.View
    public final float getWidth() {
        return this.width;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    @Override // android.view.View
    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    public final Movable getMovable() {
        Movable movable = this.movable;
        if (movable != null) {
            return movable;
        }
        Intrinsics.throwUninitializedPropertyAccessException("movable");
        return null;
    }

    public final void setMovable(Movable movable) {
        Intrinsics.checkNotNullParameter(movable, "<set-?>");
        this.movable = movable;
    }

    public final double getCurrent() {
        return this.current;
    }

    public final void setCurrent(double d) {
        this.current = d;
    }

    public final float getGravityAcceleration() {
        return this.gravityAcceleration;
    }

    public final void setGravityAcceleration(float f) {
        this.gravityAcceleration = f;
    }

    public final int getMScore() {
        return this.mScore;
    }

    public final void setMScore(int i) {
        this.mScore = i;
    }

    public final float getBoardWdith() {
        return this.boardWdith;
    }

    public final void setBoardWdith(float f) {
        this.boardWdith = f;
    }

    public final float getBoardHeight() {
        return this.boardHeight;
    }

    public final void setBoardHeight(float f) {
        this.boardHeight = f;
    }

    public final float getBoard2Top() {
        return this.board2Top;
    }

    public final void setBoard2Top(float f) {
        this.board2Top = f;
    }

    public final float getBoard2Left() {
        return this.board2Left;
    }

    public final void setBoard2Left(float f) {
        this.board2Left = f;
    }

    public final float getBallRadius() {
        return this.ballRadius;
    }

    public final void setBallRadius(float f) {
        this.ballRadius = f;
    }

    public final float getBallX() {
        return this.ballX;
    }

    public final void setBallX(float f) {
        this.ballX = f;
    }

    public final float getBallY() {
        return this.ballY;
    }

    public final void setBallY(float f) {
        this.ballY = f;
    }

    public final float getVx() {
        return this.vx;
    }

    public final void setVx(float f) {
        this.vx = f;
    }

    public final float getVy() {
        return this.vy;
    }

    public final void setVy(float f) {
        this.vy = f;
    }

    public final float getBrickWidth() {
        return this.brickWidth;
    }

    public final void setBrickWidth(float f) {
        this.brickWidth = f;
    }

    public final float getBrickHeight() {
        return this.brickHeight;
    }

    public final void setBrickHeight(float f) {
        this.brickHeight = f;
    }

    /* renamed from: isOver, reason: from getter */
    public final byte getIsOver() {
        return this.isOver;
    }

    public final void setOver(byte b) {
        this.isOver = b;
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

    public final List<Brick> getBrickList() {
        return this.brickList;
    }

    public final void setBrickList(List<Brick> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.brickList = list;
    }

    public final Paint getBallPaint() {
        return this.ballPaint;
    }

    public final Paint getBoardPaint() {
        return this.boardPaint;
    }

    public final Paint getPaintData() {
        return this.paintData;
    }

    public final void setPaintData(Paint paint) {
        Intrinsics.checkNotNullParameter(paint, "<set-?>");
        this.paintData = paint;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BallView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.ground = new PongGround();
        this.paint = new Paint();
        this.scorePaint = new Paint();
        this.gamePaint = new Paint();
        this.gameFontSize = 105.0f;
        this.bFirstShowWifiScreen = true;
        this.gravityAcceleration = 1000.0f;
        this.vx = 20.0f;
        this.vy = -20.0f;
        this.frameInterval = 50L;
        this.brickList = new ArrayList();
        this.ballPaint = new Paint();
        this.boardPaint = new Paint();
        this.paintLine = new Paint();
        this.paintData = new Paint();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BallView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.ground = new PongGround();
        this.paint = new Paint();
        this.scorePaint = new Paint();
        this.gamePaint = new Paint();
        this.gameFontSize = 105.0f;
        this.bFirstShowWifiScreen = true;
        this.gravityAcceleration = 1000.0f;
        this.vx = 20.0f;
        this.vy = -20.0f;
        this.frameInterval = 50L;
        this.brickList = new ArrayList();
        this.ballPaint = new Paint();
        this.boardPaint = new Paint();
        this.paintLine = new Paint();
        this.paintData = new Paint();
    }

    private final void initView() {
        float f = this.viewWidth;
        this.width = f;
        float f2 = this.viewHeight;
        this.height = f2;
        float f3 = f / 4;
        this.boardWdith = f3;
        this.board2Top = f2 - ((f2 / 64) * 6);
        float f4 = 2;
        this.board2Left = (f / f4) - (f3 / f4);
        int i = this.itemHeight;
        this.ballRadius = i * 2.0f;
        this.ballX = f / f4;
        this.ballY = i * 5.0f;
        this.ballPaint.setStyle(Paint.Style.FILL);
        this.ballPaint.setAntiAlias(true);
        this.ballPaint.setColor(InputDeviceCompat.SOURCE_ANY);
        this.boardPaint.setStyle(Paint.Style.FILL);
        this.boardPaint.setAntiAlias(true);
        this.boardPaint.setStrokeWidth(dip(10.0f));
        this.boardPaint.setColor(-16776961);
        this.mScore = 0;
        this.bFirstShowWifiScreen = true;
        postInvalidate();
    }

    public final void onResume() {
        this.bFirstShowWifiScreen = true;
        this.isOver = (byte) 0;
    }

    private final void resetBall() {
        initView();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
        float f = this.width / 5;
        this.brickWidth = f;
        this.brickHeight = f / 2;
        this.paintLine.setColor(ContextHolder.getContext().getColor(R.color.white));
        this.paintLine.setStrokeWidth(dip(1.0f));
        this.paintLine.setAntiAlias(true);
        this.paintLine.setTextSize(dip(this.width / 50));
        this.paintLine.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.viewWidth = 960;
        this.viewHeight = 960;
        GROUND_BOTTOM = 960 - this.itemHeight;
        this.itemHeight = 960 / 64;
        this.itemWidth = 960 / 64;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        this.ground.drawMe(canvas, this.paint, this.itemWidth, this.itemHeight);
        if (this.isOver == 2) {
            GameCallback gameCallback = this.gameCallback;
            if (gameCallback != null) {
                gameCallback.onGameOver();
            }
            if (this.isNoticeDraw) {
                drawOverInfo(canvas);
            }
            Log.d(getClass().getSimpleName(), "#1.0.1# run onDraw--> onGameFrame " + this.isNoticeDraw);
            return;
        }
        float f = this.board2Left;
        float f2 = this.board2Top;
        canvas.drawRoundRect(new RectF(f, f2, this.boardWdith + f, 70 + f2), this.itemWidth * 3.0f, this.itemHeight * 3.0f, this.boardPaint);
        if (this.isOver == 1 || this.bBallFresh) {
            this.bBallFresh = false;
            canvas.drawCircle(this.ballX, this.ballY, this.ballRadius, this.ballPaint);
        }
        boolean z = this.bFirstShowWifiScreen;
        if (z && this.isOver == 0) {
            this.bFirstShowWifiScreen = !z;
        }
        GameCallback gameCallback2 = this.gameCallback;
        if (gameCallback2 != null) {
            gameCallback2.onGameFrame(paintCanvasData());
        }
    }

    private final Bitmap paintCanvasData() {
        Canvas canvas;
        Bitmap createBitmap = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        this.canvasData = new Canvas(createBitmap);
        this.paintData.reset();
        this.paintData.setAntiAlias(true);
        this.paintData.setColor(-1);
        this.paintData.setStyle(Paint.Style.STROKE);
        this.paintData.setStrokeWidth(1.0f);
        Canvas canvas2 = this.canvasData;
        Canvas canvas3 = null;
        if (canvas2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canvasData");
            canvas = null;
        } else {
            canvas = canvas2;
        }
        canvas.drawRect(0.0f, 0.0f, 64.0f, 64.0f, this.paintData);
        this.paintData.setColor(InputDeviceCompat.SOURCE_ANY);
        this.paintData.setStyle(Paint.Style.FILL);
        Canvas canvas4 = this.canvasData;
        if (canvas4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canvasData");
            canvas4 = null;
        }
        float f = 960;
        float f2 = 64;
        canvas4.drawCircle((this.ballX / f) * f2, (this.ballY / f) * f2, 2.0f, this.paintData);
        this.paintData.setColor(-16776961);
        float f3 = this.board2Left;
        float f4 = this.board2Top;
        RectF rectF = new RectF((f3 / f) * f2, (f4 / f) * f2, ((f3 / f) * f2) + 18, ((f4 / f) * f2) + 5);
        Canvas canvas5 = this.canvasData;
        if (canvas5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canvasData");
        } else {
            canvas3 = canvas5;
        }
        canvas3.drawRoundRect(rectF, 3.0f, 3.0f, this.paintData);
        return createBitmap;
    }

    private final void initMoveBall() {
        setMovable(new Movable((int) this.ballX, (int) this.ballY, this.ballRadius, this.ballPaint));
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.gameview.pingpong.BallView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BallView.initMoveBall$lambda$0(BallView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initMoveBall$lambda$0(BallView ballView) {
        byte b;
        do {
            b = ballView.isOver;
            if (b == 0) {
                return;
            }
        } while (b != 2);
        Log.d(ballView.getClass().getSimpleName(), "#1.0# run async execute--> onGameFrame: " + ballView.isOverDrawn);
        if (ballView.isOverDrawn) {
            Thread.sleep(500L);
        }
    }

    public final void createBricks(int row, int col) {
        Brick brick = new Brick();
        RectF rectF = new RectF();
        rectF.left = this.brickWidth * col;
        rectF.top = this.brickHeight * row;
        rectF.right = this.brickWidth * (col + 1);
        rectF.bottom = this.brickHeight * (row + 1);
        brick.setRectF(rectF);
        brick.setColor("#" + Integer.toHexString((int) (ViewCompat.MEASURED_STATE_MASK * Math.random())));
        this.brickList.add(brick);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() != 2) {
            return true;
        }
        float f = 2;
        this.board2Left = event.getX() - (this.boardWdith / f);
        float x = event.getX();
        int i = this.viewWidth;
        int i2 = this.itemWidth;
        float f2 = this.boardWdith;
        if (x >= (i - i2) - (f2 / f)) {
            this.board2Left = (i - i2) - f2;
        } else if (this.board2Left < i2) {
            this.board2Left = i2 * 1.0f;
        }
        postInvalidate();
        return true;
    }

    private final int dip(float dipValue) {
        return (int) ((dipValue * getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // com.wifiled.gameview.IGame
    public void frameInterval(long frameInterval) {
        this.frameInterval = frameInterval;
    }

    @Override // com.wifiled.gameview.GameAction
    public void stopGame() {
        this.isOver = (byte) 0;
        GameCallback gameCallback = this.gameCallback;
        if (gameCallback != null) {
            gameCallback.onGameStop();
        }
    }

    @Override // com.wifiled.gameview.GameAction
    public void restartGame() {
        initView();
        startGame();
    }

    @Override // com.wifiled.gameview.GameAction
    public void destroy() {
        this.gameCallback = null;
        this.isOver = (byte) 0;
    }

    public final void freshBall() {
        this.bBallFresh = true;
        postInvalidate();
    }

    @Override // com.wifiled.gameview.GameAction
    public void startGame() {
        this.isOver = (byte) 1;
        invalidate();
        this.isNoticeDraw = false;
        GameCallback gameCallback = this.gameCallback;
        if (gameCallback != null) {
            gameCallback.onGameStart();
        }
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.gameview.pingpong.BallView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BallView.startGame$lambda$1(BallView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x022f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void startGame$lambda$1(com.wifiled.gameview.pingpong.BallView r18) {
        /*
            Method dump skipped, instructions count: 582
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.gameview.pingpong.BallView.startGame$lambda$1(com.wifiled.gameview.pingpong.BallView):void");
    }

    private final boolean isExpired() {
        long j = 1000;
        return ((System.nanoTime() - this.lastFrameTime) / j) / j > this.frameInterval;
    }

    @Override // com.wifiled.gameview.GameAction
    public void setGameCallback(GameCallback callback) {
        this.gameCallback = callback;
    }

    public final void onStop() {
        stopGame();
        initView();
    }

    public static /* synthetic */ void customBitmap$default(BallView ballView, String str, float f, float f2, Paint paint, Bitmap bitmap, int i, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            i = 0;
        }
        ballView.customBitmap(str, f, f2, paint, bitmap, i);
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

    public final void notifyInvalid() {
        this.isNoticeDraw = true;
        postInvalidate();
    }

    private final void drawOverInfo(Canvas canvas) {
        Bitmap createBitmap = Bitmap.createBitmap(this.viewWidth, this.viewHeight, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        this.gamePaint.setTextSize(105.0f);
        this.gamePaint.setColor(SupportMenu.CATEGORY_MASK);
        Rect rect = new Rect();
        this.gamePaint.getTextBounds("GAME OVER!", 0, "GAME OVER!".length(), rect);
        customBitmap$default(this, "GAME OVER!", ((rect.width() / "GAME OVER!".length()) * 1.0f) + (this.itemWidth * 2), (this.viewHeight / 4) * 1.5f, this.gamePaint, createBitmap, 0, 32, null);
        String str = "SCORE:" + this.mScore;
        this.scorePaint.setColor(-1);
        this.scorePaint.setTextSize(105.0f);
        this.scorePaint.getTextBounds(str, 0, str.length(), rect);
        customBitmap$default(this, str, ((rect.width() / str.length()) * 1.0f) + (this.itemWidth * 2), (this.viewHeight / 2) * 1.0f, this.scorePaint, createBitmap, 0, 32, null);
        canvas.drawBitmap(createBitmap, this.itemWidth * 3.0f, 0.0f, this.gamePaint);
        this.isOverDrawn = true;
    }
}
