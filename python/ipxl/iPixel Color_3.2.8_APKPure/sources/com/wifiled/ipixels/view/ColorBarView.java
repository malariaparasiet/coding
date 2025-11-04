package com.wifiled.ipixels.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public class ColorBarView extends View {
    private static int[] colors;
    private float barHeight;
    private Paint barPaint;
    private float barStartX;
    private float barStartY;
    private float barWidth;
    private int currentColor;
    private float currentThumbOffset;
    private float height;
    private OnColorChangeListener onColorChangeListener;
    float prevOffset;
    private Bitmap thumbBitmap;
    private int thumbDrawable;
    private float thumbHeight;
    private Paint thumbPaint;
    private float thumbWidth;
    private float width;

    public interface OnColorChangeListener {
        void onColorChange(int color);
    }

    public ColorBarView(Context context) {
        this(context, null);
    }

    public ColorBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.prevOffset = 0.0f;
        init();
        initCustomAttrs(context, attrs);
        initView();
    }

    private void initView() {
        this.thumbBitmap = BitmapFactory.decodeResource(getResources(), this.thumbDrawable);
    }

    private void init() {
        initColors();
        Paint paint = new Paint();
        this.barPaint = paint;
        paint.setAntiAlias(true);
        this.barPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.thumbPaint = paint2;
        paint2.setAntiAlias(true);
        this.thumbPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initColors() {
        colors = new int[13];
        float[] fArr = {0.0f, 1.0f, 1.0f};
        int i = 0;
        while (true) {
            int[] iArr = colors;
            if (i < iArr.length - 1) {
                fArr[0] = (i * 30) % 360;
                iArr[i] = Color.HSVToColor(fArr);
                i++;
            } else {
                iArr[iArr.length - 1] = Color.HSVToColor(new float[]{0.0f, 0.0f, 1.0f});
                return;
            }
        }
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ColorBarView);
        this.thumbDrawable = obtainStyledAttributes.getResourceId(1, R.mipmap.icon_diy_thunb);
        this.barHeight = obtainStyledAttributes.getDimension(0, 90.0f);
        this.thumbHeight = obtainStyledAttributes.getDimension(2, 103.0f);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode == 1073741824) {
            return Math.max(Math.max((int) this.thumbHeight, (int) this.barHeight), size);
        }
        return mode == Integer.MIN_VALUE ? Math.max((int) this.thumbHeight, ((int) this.barHeight) + getPaddingTop() + getPaddingBottom()) : size;
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        return mode == 1073741824 ? View.MeasureSpec.getSize(widthMeasureSpec) : mode == Integer.MIN_VALUE ? 200 : 0;
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width = w;
        this.height = h;
        float width = this.thumbBitmap.getWidth();
        this.thumbWidth = width;
        float f = this.width - width;
        this.barWidth = f;
        this.currentThumbOffset = f + (width / 2.0f);
        this.barStartX = width / 2.0f;
        this.barStartY = (this.height / 2.0f) - (this.barHeight / 2.0f);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Log.v("ruis", "event.getAction()-----------" + event.getAction());
        int action = event.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        return super.onTouchEvent(event);
                    }
                }
            }
            Log.v("ruis", "MotionEvent.ACTION_CANCEL");
            OnColorChangeListener onColorChangeListener = this.onColorChangeListener;
            if (onColorChangeListener != null) {
                onColorChangeListener.onColorChange(this.currentColor);
            }
            return true;
        }
        Log.v("ruis", "MotionEvent.ACTION_MOVE");
        float x = (int) event.getX();
        this.currentThumbOffset = x;
        float f = this.thumbWidth;
        if (x <= f / 2.0f) {
            this.currentThumbOffset = (f / 2.0f) + 1.0f;
        }
        float f2 = this.currentThumbOffset;
        float f3 = this.barWidth;
        if (f2 >= (f / 2.0f) + f3) {
            this.currentThumbOffset = f3 + (f / 2.0f);
        }
        changColor();
        invalidate();
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawBar(canvas);
        drawThumb(canvas);
        super.onDraw(canvas);
    }

    private void changColor() {
        float f = this.currentThumbOffset - (this.thumbWidth / 2.0f);
        float f2 = this.barWidth;
        float f3 = (f / f2) * 360.0f;
        if (Float.compare(f, f2) >= 0) {
            this.currentColor = Color.HSVToColor(new float[]{0.0f, 0.0f, 1.0f});
        } else {
            this.currentColor = Color.HSVToColor(new float[]{f3, 1.0f, 1.0f});
        }
    }

    private int getCurrentColor() {
        return this.currentColor;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
        OnColorChangeListener onColorChangeListener = this.onColorChangeListener;
        if (onColorChangeListener != null) {
            onColorChangeListener.onColorChange(currentColor);
        }
        invalidate();
    }

    private void drawBar(Canvas canvas) {
        Paint paint = this.barPaint;
        float f = this.barStartX;
        float f2 = this.barStartY;
        float f3 = this.barHeight;
        paint.setShader(new LinearGradient(f, (f3 / 2.0f) + f2, this.barWidth + f, f2 + (f3 / 2.0f), colors, (float[]) null, Shader.TileMode.CLAMP));
        float f4 = this.barStartX;
        float f5 = this.barStartY;
        RectF rectF = new RectF(f4, f5, this.barWidth + f4, this.barHeight + f5);
        float f6 = this.barHeight;
        canvas.drawRoundRect(rectF, f6 / 9.0f, f6 / 9.0f, this.barPaint);
    }

    private void drawThumb(Canvas canvas) {
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(this.currentColor), Color.green(this.currentColor), Color.blue(this.currentColor), fArr);
        float f = this.currentThumbOffset;
        this.prevOffset = f;
        float f2 = this.barWidth;
        this.currentThumbOffset = f > f2 ? f2 + (this.thumbWidth / 2.0f) : (f > f2 ? f2 : (fArr[0] * f2) / 360.0f) + (this.thumbWidth / 2.0f);
        canvas.drawBitmap(this.thumbBitmap, (Rect) null, getThumbRect(), this.thumbPaint);
    }

    private RectF getThumbRect() {
        float f = this.currentThumbOffset;
        float f2 = this.thumbWidth;
        float f3 = this.height;
        float f4 = this.thumbHeight;
        return new RectF(f - (f2 / 2.0f), (f3 / 2.0f) - (f4 / 2.0f), f + (f2 / 2.0f), (f3 / 2.0f) + (f4 / 2.0f));
    }

    public void setOnColorChangerListener(OnColorChangeListener onColorChangerListener) {
        this.onColorChangeListener = onColorChangerListener;
    }
}
