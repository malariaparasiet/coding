package com.wifiled.gameview.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.example.admin.balatetris.TetrisView;
import com.wifiled.gameview.balatetris.model.TetrisGround;
import com.wifiled.gameview.snake.view.StageView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GridLineView.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\bB#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0004\u0010\u000bJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J\u0012\u0010\u001a\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010\u001b\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\u0006\u0010\u001c\u001a\u00020\u0017R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/wifiled/gameview/utils/GridLineView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "curActivityName", "", "getCurActivityName", "()Ljava/lang/String;", "setCurActivityName", "(Ljava/lang/String;)V", "mPaint", "Landroid/graphics/Paint;", "ground", "Lcom/wifiled/gameview/balatetris/model/TetrisGround;", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "drawGridLine", "drawSnakeGridLine", "render", "Companion", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GridLineView extends View {
    public static final String GAME_SNAKE = "snake";
    public static final String GAME_TETRIS = "tetris";
    private static int ledType;
    private static float pointAllLength;
    private static float pointAllLength2;
    private static float pointLength;
    private String curActivityName;
    private final TetrisGround ground;
    private final Paint mPaint;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int pointMargin = 1;

    public final String getCurActivityName() {
        return this.curActivityName;
    }

    public final void setCurActivityName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.curActivityName = str;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GridLineView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.curActivityName = "";
        this.mPaint = new Paint();
        this.ground = new TetrisGround(0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GridLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.curActivityName = "";
        this.mPaint = new Paint();
        this.ground = new TetrisGround(0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GridLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.curActivityName = "";
        this.mPaint = new Paint();
        this.ground = new TetrisGround(0);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        this.ground.drawMe(canvas, this.mPaint, TetrisView.INSTANCE.getItemWidth(), TetrisView.INSTANCE.getItemHeight(), TetrisView.INSTANCE.getState());
        if (Intrinsics.areEqual(getTag().toString(), GAME_SNAKE)) {
            drawSnakeGridLine(canvas);
        } else {
            drawGridLine(canvas);
        }
    }

    private final void drawGridLine(Canvas canvas) {
        this.mPaint.setColor(0);
        if (Intrinsics.areEqual(getTag().toString(), GAME_TETRIS)) {
            this.mPaint.setColor(Color.parseColor("#2e2f32"));
        }
        for (int i = 0; i < 62; i++) {
            if (canvas != null) {
                canvas.drawLine(0.0f, TetrisView.INSTANCE.getItemHeight() * i, TetrisView.INSTANCE.getViewWidth(), TetrisView.INSTANCE.getItemHeight() * i, this.mPaint);
            }
        }
        for (int i2 = 0; i2 < 62; i2++) {
            if (canvas != null) {
                canvas.drawLine(TetrisView.INSTANCE.getItemWidth() * i2, 0.0f, TetrisView.INSTANCE.getItemWidth() * i2, TetrisView.INSTANCE.getViewHeight(), this.mPaint);
            }
        }
    }

    private final void drawSnakeGridLine(Canvas canvas) {
        this.mPaint.setColor(0);
        for (int i = 0; i < 65; i++) {
            Intrinsics.checkNotNull(canvas);
            canvas.drawLine(0.0f, StageView.INSTANCE.getItemHeight() * i, StageView.INSTANCE.getViewWidth(), StageView.INSTANCE.getItemHeight() * i, this.mPaint);
        }
        for (int i2 = 0; i2 < 65; i2++) {
            Intrinsics.checkNotNull(canvas);
            canvas.drawLine(StageView.INSTANCE.getItemWidth() * i2, 0.0f, StageView.INSTANCE.getItemWidth() * i2, StageView.INSTANCE.getViewHeight(), this.mPaint);
        }
    }

    public final void render() {
        invalidate();
    }

    /* compiled from: GridLineView.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\bJ,\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\bJ\"\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\bJ,\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\bJ,\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/wifiled/gameview/utils/GridLineView$Companion;", "", "<init>", "()V", "GAME_SNAKE", "", "GAME_TETRIS", "pointAllLength", "", "pointAllLength2", "pointMargin", "", "pointLength", "ledType", "getLedType", "()I", "setLedType", "(I)V", "drawTextGridLine", "", "paint", "Landroid/graphics/Paint;", "canvas", "Landroid/graphics/Canvas;", "widthSize", "ledWidthSize", "drawTextGridLine1696", "drawTextGridLine2064", "drawTextGridLine3296", "drawTextGridLine2448", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getLedType() {
            return GridLineView.ledType;
        }

        public final void setLedType(int i) {
            GridLineView.ledType = i;
        }

        public static /* synthetic */ void drawTextGridLine$default(Companion companion, Paint paint, Canvas canvas, float f, float f2, int i, Object obj) {
            if ((i & 4) != 0) {
                f = 64.0f;
            }
            if ((i & 8) != 0) {
                f2 = 64.0f;
            }
            companion.drawTextGridLine(paint, canvas, f, f2);
        }

        public final void drawTextGridLine(Paint paint, Canvas canvas, float widthSize, float ledWidthSize) {
            Intrinsics.checkNotNullParameter(paint, "paint");
            paint.setColor(-1);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(1.0f);
            float f = 64;
            GridLineView.pointAllLength = (widthSize / f) * 1.0f;
            GridLineView.pointLength = GridLineView.pointAllLength - (GridLineView.pointMargin * 2);
            float f2 = GridLineView.pointAllLength * 64.0f;
            Intrinsics.checkNotNull(canvas);
            canvas.drawLine(1.0f, 1.0f, 1.0f, f2, paint);
            float f3 = 2;
            canvas.drawLine(((GridLineView.pointAllLength * f) / f3) - paint.getStrokeWidth(), 1.0f, (GridLineView.pointAllLength * f) / f3, GridLineView.pointAllLength * 64.0f, paint);
            float f4 = 4;
            float f5 = 3;
            canvas.drawLine((((GridLineView.pointAllLength * f) / f4) * f5) - paint.getStrokeWidth(), 1.0f, ((GridLineView.pointAllLength * f) / f4) * f5, GridLineView.pointAllLength * 64.0f, paint);
            float f6 = 1;
            canvas.drawLine((GridLineView.pointAllLength * 64.0f) - f6, 1.0f, (GridLineView.pointAllLength * 64.0f) - f6, (GridLineView.pointAllLength * 64.0f) - f6, paint);
            canvas.drawLine(1.0f, 1.0f, GridLineView.pointAllLength * 64.0f, 1.0f, paint);
            canvas.drawLine(((GridLineView.pointAllLength * f) / f4) - paint.getStrokeWidth(), 1.0f, ((GridLineView.pointAllLength * f) / f4) - paint.getStrokeWidth(), GridLineView.pointAllLength * 64.0f, paint);
            canvas.drawLine(1.0f, (GridLineView.pointAllLength * f) / f3, GridLineView.pointAllLength * 64.0f, (GridLineView.pointAllLength * f) / f3, paint);
            canvas.drawLine(1.0f, GridLineView.pointAllLength * 64.0f, GridLineView.pointAllLength * 64.0f, GridLineView.pointAllLength * f, paint);
        }

        public static /* synthetic */ void drawTextGridLine1696$default(Companion companion, Paint paint, Canvas canvas, float f, float f2, int i, Object obj) {
            if ((i & 4) != 0) {
                f = 96.0f;
            }
            if ((i & 8) != 0) {
                f2 = 16.0f;
            }
            companion.drawTextGridLine1696(paint, canvas, f, f2);
        }

        public final void drawTextGridLine1696(Paint paint, Canvas canvas, float widthSize, float ledWidthSize) {
            Intrinsics.checkNotNullParameter(paint, "paint");
            paint.setColor(-1);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(1.0f);
            GridLineView.pointAllLength = widthSize / ledWidthSize;
            GridLineView.pointLength = GridLineView.pointAllLength - (GridLineView.pointMargin * 2);
            float f = GridLineView.pointAllLength * 16.0f;
            Intrinsics.checkNotNull(canvas);
            canvas.drawLine(1.0f, 1.0f, 1.0f, f, paint);
            float f2 = 2;
            canvas.drawLine(((GridLineView.pointAllLength * ledWidthSize) / f2) - paint.getStrokeWidth(), 1.0f, (GridLineView.pointAllLength * ledWidthSize) / f2, GridLineView.pointAllLength * 16.0f, paint);
            float f3 = 1;
            canvas.drawLine((GridLineView.pointAllLength * ledWidthSize) - f3, 1.0f, (GridLineView.pointAllLength * ledWidthSize) - f3, (GridLineView.pointAllLength * 16.0f) - f3, paint);
            canvas.drawLine(1.0f, 1.0f, GridLineView.pointAllLength * ledWidthSize, 1.0f, paint);
            canvas.drawLine(1.0f, GridLineView.pointAllLength * 16.0f, GridLineView.pointAllLength * ledWidthSize, (GridLineView.pointAllLength * 16.0f) - f3, paint);
        }

        public static /* synthetic */ void drawTextGridLine2064$default(Companion companion, Paint paint, Canvas canvas, float f, int i, Object obj) {
            if ((i & 4) != 0) {
                f = 96.0f;
            }
            companion.drawTextGridLine2064(paint, canvas, f);
        }

        public final void drawTextGridLine2064(Paint paint, Canvas canvas, float widthSize) {
            Intrinsics.checkNotNullParameter(paint, "paint");
            paint.setColor(-1);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(1.0f);
            float f = 64;
            GridLineView.pointAllLength = widthSize / f;
            GridLineView.pointLength = GridLineView.pointAllLength - (GridLineView.pointMargin * 2);
            float f2 = GridLineView.pointAllLength * 20.0f;
            Intrinsics.checkNotNull(canvas);
            canvas.drawLine(1.0f, 1.0f, 1.0f, f2, paint);
            float f3 = 2;
            canvas.drawLine(((GridLineView.pointAllLength * f) / f3) - paint.getStrokeWidth(), 1.0f, (GridLineView.pointAllLength * f) / f3, GridLineView.pointAllLength * 20.0f, paint);
            float f4 = 1;
            canvas.drawLine((GridLineView.pointAllLength * 64.0f) - f4, 1.0f, (GridLineView.pointAllLength * 64.0f) - f4, (GridLineView.pointAllLength * 20.0f) - f4, paint);
            canvas.drawLine(1.0f, 1.0f, GridLineView.pointAllLength * 64.0f, 1.0f, paint);
            canvas.drawLine(1.0f, GridLineView.pointAllLength * 20.0f, GridLineView.pointAllLength * 64.0f, (GridLineView.pointAllLength * 20.0f) - f4, paint);
        }

        public static /* synthetic */ void drawTextGridLine3296$default(Companion companion, Paint paint, Canvas canvas, float f, float f2, int i, Object obj) {
            if ((i & 4) != 0) {
                f = 96.0f;
            }
            if ((i & 8) != 0) {
                f2 = 16.0f;
            }
            companion.drawTextGridLine3296(paint, canvas, f, f2);
        }

        public final void drawTextGridLine3296(Paint paint, Canvas canvas, float widthSize, float ledWidthSize) {
            Intrinsics.checkNotNullParameter(paint, "paint");
            paint.setColor(-1);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(1.0f);
            GridLineView.pointAllLength = widthSize / ledWidthSize;
            GridLineView.pointLength = GridLineView.pointAllLength - (GridLineView.pointMargin * 2);
            float f = GridLineView.pointAllLength * 32.0f;
            Intrinsics.checkNotNull(canvas);
            canvas.drawLine(1.0f, 1.0f, 1.0f, f, paint);
            float f2 = 2;
            canvas.drawLine(((GridLineView.pointAllLength * ledWidthSize) / f2) - paint.getStrokeWidth(), 1.0f, (GridLineView.pointAllLength * ledWidthSize) / f2, GridLineView.pointAllLength * 32.0f, paint);
            float f3 = 1;
            canvas.drawLine((GridLineView.pointAllLength * ledWidthSize) - f3, 1.0f, (GridLineView.pointAllLength * ledWidthSize) - f3, (GridLineView.pointAllLength * 32.0f) - f3, paint);
            canvas.drawLine(1.0f, 1.0f, GridLineView.pointAllLength * ledWidthSize, 1.0f, paint);
            canvas.drawLine(1.0f, GridLineView.pointAllLength * 32.0f, GridLineView.pointAllLength * ledWidthSize, (GridLineView.pointAllLength * 32.0f) - f3, paint);
        }

        public static /* synthetic */ void drawTextGridLine2448$default(Companion companion, Paint paint, Canvas canvas, float f, float f2, int i, Object obj) {
            if ((i & 4) != 0) {
                f = 96.0f;
            }
            if ((i & 8) != 0) {
                f2 = 16.0f;
            }
            companion.drawTextGridLine2448(paint, canvas, f, f2);
        }

        public final void drawTextGridLine2448(Paint paint, Canvas canvas, float widthSize, float ledWidthSize) {
            Intrinsics.checkNotNullParameter(paint, "paint");
            paint.setColor(-1);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(1.0f);
            GridLineView.pointAllLength = widthSize / ledWidthSize;
            GridLineView.pointLength = GridLineView.pointAllLength - (GridLineView.pointMargin * 2);
            float f = GridLineView.pointAllLength * 24.0f;
            Intrinsics.checkNotNull(canvas);
            canvas.drawLine(1.0f, 1.0f, 1.0f, f, paint);
            float f2 = 2;
            canvas.drawLine(((GridLineView.pointAllLength * ledWidthSize) / f2) - paint.getStrokeWidth(), 1.0f, (GridLineView.pointAllLength * ledWidthSize) / f2, GridLineView.pointAllLength * 24.0f, paint);
            float f3 = 1;
            canvas.drawLine((GridLineView.pointAllLength * ledWidthSize) - f3, 1.0f, (GridLineView.pointAllLength * ledWidthSize) - f3, (GridLineView.pointAllLength * 24.0f) - f3, paint);
            canvas.drawLine(1.0f, 1.0f, GridLineView.pointAllLength * ledWidthSize, 1.0f, paint);
            canvas.drawLine(1.0f, GridLineView.pointAllLength * 24.0f, GridLineView.pointAllLength * ledWidthSize, (GridLineView.pointAllLength * 24.0f) - f3, paint);
        }
    }
}
