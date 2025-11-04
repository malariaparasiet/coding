package com.wifiled.gameview.snake.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import com.wifiled.gameview.GameAction;
import com.wifiled.gameview.GameCallback;
import com.wifiled.gameview.snake.model.Food;
import com.wifiled.gameview.snake.model.Ground;
import com.wifiled.gameview.snake.model.Snake;
import com.wifiled.gameview.snake.utils.LogUtil;
import com.wifiled.gameview.snake.utils.SnakeUnitUtil;
import com.wifiled.gameview.snake.utils.SyncObject;
import com.wifiled.gameview.utils.TextAgreement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StageView.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 S2\u00020\u00012\u00020\u0002:\u0001SB\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020+J\u0006\u0010-\u001a\u00020+J\u0006\u0010.\u001a\u00020+J\u0018\u0010/\u001a\u00020+2\u0006\u00100\u001a\u00020\n2\u0006\u00101\u001a\u00020\nH\u0014J\u0010\u00104\u001a\u00020#2\u0006\u00105\u001a\u000206H\u0016J\u0010\u00107\u001a\u00020+2\u0006\u00108\u001a\u000209H\u0014J\u0010\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020<H\u0016J\u0006\u0010=\u001a\u00020+J\b\u0010>\u001a\u00020+H\u0016J\b\u0010?\u001a\u00020+H\u0016J\b\u0010@\u001a\u00020+H\u0016J\b\u0010A\u001a\u00020+H\u0016J\b\u0010B\u001a\u00020+H\u0016J\b\u0010C\u001a\u00020+H\u0016J\b\u0010D\u001a\u00020+H\u0016J\b\u0010E\u001a\u00020+H\u0016J\u0010\u0010F\u001a\u00020+2\u0006\u0010F\u001a\u00020GH\u0016J\u0010\u0010\u001f\u001a\u00020+2\u0006\u0010\u001f\u001a\u00020GH\u0016J\u0006\u0010H\u001a\u00020+J\u0010\u0010I\u001a\u00020+2\u0006\u00108\u001a\u000209H\u0002J8\u0010J\u001a\u00020+2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020N2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010P\u001a\u00020Q2\b\b\u0002\u0010R\u001a\u00020\nR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001b\"\u0004\b!\u0010\u001dR\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R\u000e\u00102\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lcom/wifiled/gameview/snake/view/StageView;", "Landroid/view/View;", "Lcom/wifiled/gameview/GameAction;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "SCORE_LEVEL", "", "paint", "Landroid/graphics/Paint;", "ground", "Lcom/wifiled/gameview/snake/model/Ground;", "food", "Lcom/wifiled/gameview/snake/model/Food;", "scorePaint", "gamePaint", "snakeIns", "Lcom/wifiled/gameview/snake/model/Snake;", "getSnakeIns", "()Lcom/wifiled/gameview/snake/model/Snake;", "setSnakeIns", "(Lcom/wifiled/gameview/snake/model/Snake;)V", "score", "getScore", "()I", "setScore", "(I)V", "prevScore", "level", "getLevel", "setLevel", "isOverDrawn", "", "()Z", "setOverDrawn", "(Z)V", "bWifiScreenShowFirst", "getBWifiScreenShowFirst", "setBWifiScreenShowFirst", "init", "", "addScore", "addLevel", "reset", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "downX", "downY", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onDraw", "canvas", "Landroid/graphics/Canvas;", "setGameCallback", "callback", "Lcom/wifiled/gameview/GameCallback;", "onMove", "moveForward", "moveBack", "moveLeft", "moveRight", "startGame", "stopGame", "restartGame", "destroy", "frameInterval", "", "notifyInvalid", "drawOverInfo", "customBitmap", "info", "", "left", "", "top", "bitmap", "Landroid/graphics/Bitmap;", "iOffset", "Companion", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StageView extends View implements GameAction {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int fontVal = 105;
    private static int itemHeight;
    private static int itemWidth;
    private static int viewHeight;
    private static int viewWidth;
    private final int SCORE_LEVEL;
    private boolean bWifiScreenShowFirst;
    private int downX;
    private int downY;
    private Food food;
    private final Paint gamePaint;
    private Ground ground;
    private boolean isOverDrawn;
    private int level;
    private Paint paint;
    private int prevScore;
    private int score;
    private final Paint scorePaint;
    private Snake snakeIns;

    public StageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.SCORE_LEVEL = 100;
        this.scorePaint = new Paint();
        this.gamePaint = new Paint();
        this.snakeIns = new Snake();
        this.bWifiScreenShowFirst = true;
        this.ground = new Ground();
        this.food = new Food();
        Paint paint = new Paint();
        this.paint = paint;
        Intrinsics.checkNotNull(paint);
        paint.setAntiAlias(true);
        init();
    }

    public final Snake getSnakeIns() {
        return this.snakeIns;
    }

    public final void setSnakeIns(Snake snake) {
        Intrinsics.checkNotNullParameter(snake, "<set-?>");
        this.snakeIns = snake;
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

    /* compiled from: StageView.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/wifiled/gameview/snake/view/StageView$Companion;", "", "<init>", "()V", "itemWidth", "", "getItemWidth", "()I", "setItemWidth", "(I)V", "itemHeight", "getItemHeight", "setItemHeight", "viewWidth", "getViewWidth", "setViewWidth", "viewHeight", "getViewHeight", "setViewHeight", "fontVal", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getItemWidth() {
            return StageView.itemWidth;
        }

        public final void setItemWidth(int i) {
            StageView.itemWidth = i;
        }

        public final int getItemHeight() {
            return StageView.itemHeight;
        }

        public final void setItemHeight(int i) {
            StageView.itemHeight = i;
        }

        public final int getViewWidth() {
            return StageView.viewWidth;
        }

        public final void setViewWidth(int i) {
            StageView.viewWidth = i;
        }

        public final int getViewHeight() {
            return StageView.viewHeight;
        }

        public final void setViewHeight(int i) {
            StageView.viewHeight = i;
        }
    }

    public final boolean getBWifiScreenShowFirst() {
        return this.bWifiScreenShowFirst;
    }

    public final void setBWifiScreenShowFirst(boolean z) {
        this.bWifiScreenShowFirst = z;
    }

    public final void init() {
        this.snakeIns.init(this);
        SnakeUnitUtil.createOneFood(this.snakeIns, this.food);
        this.score = 0;
        this.level = 1;
        this.snakeIns.level(1);
        this.snakeIns.Score(this.score);
        this.bWifiScreenShowFirst = true;
        Ground ground = this.ground;
        Intrinsics.checkNotNull(ground);
        ground.setChessboard(true);
    }

    public final void addScore() {
        int i = this.score + 5;
        this.score = i;
        this.snakeIns.Score(i);
    }

    public final void addLevel() {
        int i = this.level + 1;
        this.level = i;
        this.snakeIns.level(i);
    }

    public final void reset() {
        this.level = 1;
        this.score = 0;
        this.snakeIns.frameInterval(150L);
        this.snakeIns.level(this.level);
        this.snakeIns.Score(this.score);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = 960;
        viewHeight = 960;
        itemHeight = 960 / 64;
        itemWidth = 960 / 64;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        int action = event.getAction();
        if (action == 0) {
            LogUtil.d(getClass(), NotificationCompat.CATEGORY_EVENT, "down " + rawX + " " + rawY);
            this.downX = rawX;
            this.downY = rawY;
        } else if (action == 1) {
            LogUtil.d(getClass(), NotificationCompat.CATEGORY_EVENT, "up " + rawX + " " + rawY);
            int i = rawX - this.downX;
            int i2 = rawY - this.downY;
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            if (abs > 50 || abs2 > 50) {
                if (abs > abs2) {
                    if (i > 0) {
                        moveRight();
                    } else {
                        moveLeft();
                    }
                } else if (i2 > 0) {
                    moveBack();
                } else {
                    moveForward();
                }
            }
        }
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        long currentTimeMillis = System.currentTimeMillis();
        Ground ground = this.ground;
        Intrinsics.checkNotNull(ground);
        ground.drawMe(canvas, this.paint, itemWidth, itemHeight);
        Paint paint = this.paint;
        Intrinsics.checkNotNull(paint);
        paint.setColor(Color.parseColor("#2e2f32"));
        for (int i = 0; i < 65; i++) {
            int i2 = itemHeight;
            Paint paint2 = this.paint;
            Intrinsics.checkNotNull(paint2);
            canvas.drawLine(0.0f, i * i2, viewWidth, i2 * i, paint2);
        }
        for (int i3 = 0; i3 < 65; i3++) {
            int i4 = itemWidth;
            float f = viewHeight;
            Paint paint3 = this.paint;
            Intrinsics.checkNotNull(paint3);
            canvas.drawLine(i3 * i4, 0.0f, i4 * i3, f, paint3);
        }
        if (!this.snakeIns.isAlive()) {
            Ground ground2 = this.ground;
            Intrinsics.checkNotNull(ground2);
            ground2.setChessboard(false);
            if (this.isOverDrawn) {
                drawOverInfo(canvas);
            }
        } else {
            this.snakeIns.drawMe(canvas, this.paint, itemWidth, itemHeight);
            Food food = this.food;
            Intrinsics.checkNotNull(food);
            food.drawMe(canvas, this.paint, itemWidth, itemHeight);
            if (this.bWifiScreenShowFirst) {
                this.bWifiScreenShowFirst = false;
                this.snakeIns.onGameFrame(this);
            }
        }
        Log.d(getClass().getSimpleName(), "draw end: " + (System.currentTimeMillis() - currentTimeMillis));
        if (SyncObject.INSTANCE.get().isLocked()) {
            SyncObject.INSTANCE.get().unlock();
        }
    }

    @Override // com.wifiled.gameview.GameAction
    public void setGameCallback(GameCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.setGameCallback(callback);
    }

    public final void onMove() {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        if (snake.isGetFood(this.food)) {
            this.snakeIns.eatFood();
            addScore();
            SnakeUnitUtil.createOneFood(this.snakeIns, this.food);
            int i = this.prevScore;
            int i2 = this.score;
            if (i != i2) {
                this.prevScore = i2;
                int i3 = this.SCORE_LEVEL;
                if ((i2 - ((i2 / i3) * i3)) % i3 == 0) {
                    addLevel();
                    this.snakeIns.decreaseFrameInterval();
                }
            }
        }
        if (this.snakeIns.checkDie(this.ground)) {
            this.snakeIns.gameOver();
            SyncObject.INSTANCE.get().setDelayTime(0L);
        }
        invalidate();
        SyncObject.INSTANCE.get().tryLock();
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveForward() {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.changeDirection(1);
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveBack() {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.changeDirection(-1);
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveLeft() {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.changeDirection(-2);
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveRight() {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.changeDirection(2);
    }

    @Override // com.wifiled.gameview.GameAction
    public void startGame() {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.start();
        Ground ground = this.ground;
        Intrinsics.checkNotNull(ground);
        ground.setChessboard(true);
        this.isOverDrawn = false;
        invalidate();
    }

    @Override // com.wifiled.gameview.GameAction
    public void stopGame() {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.stop();
        invalidate();
    }

    @Override // com.wifiled.gameview.GameAction
    public void restartGame() {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.init(this);
        reset();
        startGame();
    }

    @Override // com.wifiled.gameview.GameAction
    public void destroy() {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.destroy();
    }

    @Override // com.wifiled.gameview.IGame
    public void frameInterval(long frameInterval) {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.frameInterval(frameInterval);
    }

    @Override // com.wifiled.gameview.IGame
    public void level(long level) {
        Snake snake = this.snakeIns;
        Intrinsics.checkNotNull(snake);
        snake.level(level);
    }

    public final void notifyInvalid() {
        this.isOverDrawn = true;
        postInvalidate();
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

    public static /* synthetic */ void customBitmap$default(StageView stageView, String str, float f, float f2, Paint paint, Bitmap bitmap, int i, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            i = 0;
        }
        stageView.customBitmap(str, f, f2, paint, bitmap, i);
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
}
