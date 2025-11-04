package com.wifiled.baselib.widget.loopview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.camera.video.AudioStats;
import com.wifiled.baselib.R;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class LoopView extends View {
    private static final float DEFAULT_LINE_SPACE = 2.0f;
    private static final int DEFAULT_TEXT_SIZE = (int) (Resources.getSystem().getDisplayMetrics().density * 15.0f);
    private static final int DEFAULT_VISIBIE_ITEMS = 9;
    int centerTextColor;
    int change;
    private Context context;
    int dividerColor;
    String[] drawingStrings;
    int firstLineY;
    private GestureDetector flingGestureDetector;
    int halfCircumference;
    Handler handler;
    int initPosition;
    boolean isLoop;
    List<String> items;
    int itemsVisibleCount;
    float lineSpacingMultiplier;
    ScheduledExecutorService mExecutor;
    private ScheduledFuture<?> mFuture;
    private int mOffset;
    int maxTextHeight;
    int measuredHeight;
    int measuredWidth;
    OnItemSelectedListener onItemSelectedListener;
    int outerTextColor;
    private int paddingLeft;
    private int paddingRight;
    private Paint paintCenterText;
    private Paint paintIndicator;
    private Paint paintOuterText;
    int preCurrentIndex;
    private float previousY;
    int radius;
    private float scaleX;
    int secondLineY;
    private int selectedItem;
    long startTime;
    private Rect tempRect;
    int textSize;
    int totalScrollY;

    public enum ACTION {
        CLICK,
        FLING,
        DAGGLE
    }

    public void setLineSpacingMultiplier(float f) {
        if (f > 1.0f) {
            this.lineSpacingMultiplier = f;
        }
    }

    public void setCenterTextColor(int i) {
        this.centerTextColor = i;
        this.paintCenterText.setColor(i);
    }

    public void setOuterTextColor(int i) {
        this.outerTextColor = i;
        this.paintOuterText.setColor(i);
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
        this.paintIndicator.setColor(i);
    }

    public LoopView(Context context) {
        super(context);
        this.scaleX = 1.05f;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mOffset = 0;
        this.startTime = 0L;
        this.tempRect = new Rect();
        initLoopView(context, null);
    }

    public LoopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scaleX = 1.05f;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mOffset = 0;
        this.startTime = 0L;
        this.tempRect = new Rect();
        initLoopView(context, attributeSet);
    }

    public LoopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scaleX = 1.05f;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mOffset = 0;
        this.startTime = 0L;
        this.tempRect = new Rect();
        initLoopView(context, attributeSet);
    }

    private void initLoopView(Context context, AttributeSet attributeSet) {
        this.context = context;
        this.handler = new MessageHandler(this);
        GestureDetector gestureDetector = new GestureDetector(context, new LoopViewGestureListener(this));
        this.flingGestureDetector = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.androidWheelView);
        this.textSize = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_textsize, DEFAULT_TEXT_SIZE);
        this.textSize = (int) (Resources.getSystem().getDisplayMetrics().density * this.textSize);
        this.lineSpacingMultiplier = obtainStyledAttributes.getFloat(R.styleable.androidWheelView_awv_lineSpace, DEFAULT_LINE_SPACE);
        this.centerTextColor = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_centerTextColor, -1282278);
        this.outerTextColor = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_outerTextColor, -5263441);
        this.dividerColor = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_dividerTextColor, -3815995);
        int integer = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_itemsVisibleCount, 9);
        this.itemsVisibleCount = integer;
        if (integer % 2 == 0) {
            this.itemsVisibleCount = 9;
        }
        this.isLoop = obtainStyledAttributes.getBoolean(R.styleable.androidWheelView_awv_isLoop, true);
        obtainStyledAttributes.recycle();
        this.drawingStrings = new String[this.itemsVisibleCount];
        this.totalScrollY = 0;
        this.initPosition = -1;
        initPaints();
    }

    public void setItemsVisibleCount(int i) {
        if (i % 2 == 0 || i == this.itemsVisibleCount) {
            return;
        }
        this.itemsVisibleCount = i;
        this.drawingStrings = new String[i];
    }

    private void initPaints() {
        Paint paint = new Paint();
        this.paintOuterText = paint;
        paint.setColor(this.outerTextColor);
        this.paintOuterText.setAntiAlias(true);
        this.paintOuterText.setTypeface(Typeface.MONOSPACE);
        this.paintOuterText.setTextSize(this.textSize);
        Paint paint2 = new Paint();
        this.paintCenterText = paint2;
        paint2.setColor(this.centerTextColor);
        this.paintCenterText.setAntiAlias(true);
        this.paintCenterText.setTextScaleX(this.scaleX);
        this.paintCenterText.setTypeface(Typeface.MONOSPACE);
        this.paintCenterText.setTextSize(this.textSize);
        Paint paint3 = new Paint();
        this.paintIndicator = paint3;
        paint3.setColor(this.dividerColor);
        this.paintIndicator.setAntiAlias(true);
    }

    private void remeasure() {
        List<String> list = this.items;
        if (list != null || list.size() <= 0) {
            this.measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            this.measuredHeight = measuredHeight;
            if (this.measuredWidth == 0 || measuredHeight == 0) {
                return;
            }
            this.paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            this.paddingRight = paddingRight;
            this.measuredWidth -= paddingRight;
            this.paintCenterText.getTextBounds("星期", 0, 2, this.tempRect);
            this.tempRect.height();
            int i = this.measuredHeight;
            int i2 = (int) ((i * 3.141592653589793d) / 2.0d);
            this.halfCircumference = i2;
            float f = this.lineSpacingMultiplier;
            int i3 = (int) (i2 / ((this.itemsVisibleCount - 1) * f));
            this.maxTextHeight = i3;
            this.radius = i / 2;
            this.firstLineY = (int) ((i - (i3 * f)) / DEFAULT_LINE_SPACE);
            this.secondLineY = (int) ((i + (f * i3)) / DEFAULT_LINE_SPACE);
            if (this.initPosition == -1) {
                if (this.isLoop) {
                    this.initPosition = (this.items.size() + 1) / 2;
                } else {
                    this.initPosition = 0;
                }
            }
            this.preCurrentIndex = this.initPosition;
        }
    }

    void smoothScroll(ACTION action) {
        cancelFuture();
        if (action == ACTION.FLING || action == ACTION.DAGGLE) {
            float f = this.lineSpacingMultiplier * this.maxTextHeight;
            int i = (int) (((this.totalScrollY % f) + f) % f);
            this.mOffset = i;
            if (i > f / DEFAULT_LINE_SPACE) {
                this.mOffset = (int) (f - i);
            } else {
                this.mOffset = -i;
            }
        }
        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new SmoothScrollTimerTask(this, this.mOffset), 0L, 10L, TimeUnit.MILLISECONDS);
    }

    protected final void scrollBy(float f) {
        cancelFuture();
        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new InertiaTimerTask(this, f), 0L, 10, TimeUnit.MILLISECONDS);
    }

    public void cancelFuture() {
        ScheduledFuture<?> scheduledFuture = this.mFuture;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            return;
        }
        this.mFuture.cancel(true);
        this.mFuture = null;
    }

    public void setNotLoop() {
        this.isLoop = false;
    }

    public final void setTextSize(float f) {
        if (f > 0.0f) {
            int i = (int) (this.context.getResources().getDisplayMetrics().density * f);
            this.textSize = i;
            this.paintOuterText.setTextSize(i);
            this.paintCenterText.setTextSize(this.textSize);
        }
    }

    public final void setInitPosition(int i) {
        if (i < 0) {
            this.initPosition = 0;
            return;
        }
        List<String> list = this.items;
        if (list == null || list.size() <= i) {
            return;
        }
        this.initPosition = i;
    }

    public final void setListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public final void setItems(List<String> list) {
        this.items = list;
        remeasure();
        invalidate();
    }

    public final int getSelectedItem() {
        return this.selectedItem;
    }

    protected final void onItemSelected() {
        if (this.onItemSelectedListener != null) {
            postDelayed(new OnItemSelectedRunnable(this), 200L);
        }
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.scaleX = f;
    }

    public void setCurrentPosition(int i) {
        List<String> list = this.items;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.items.size();
        if (i < 0 || i >= size || i == this.selectedItem) {
            return;
        }
        this.initPosition = i;
        this.totalScrollY = 0;
        this.mOffset = 0;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        List<String> list = this.items;
        if (list == null) {
            return;
        }
        int i = (int) (this.totalScrollY / (this.lineSpacingMultiplier * this.maxTextHeight));
        this.change = i;
        int size = this.initPosition + (i % list.size());
        this.preCurrentIndex = size;
        if (!this.isLoop) {
            if (size < 0) {
                this.preCurrentIndex = 0;
            }
            if (this.preCurrentIndex > this.items.size() - 1) {
                this.preCurrentIndex = this.items.size() - 1;
            }
        } else {
            if (size < 0) {
                this.preCurrentIndex = this.items.size() + this.preCurrentIndex;
            }
            if (this.preCurrentIndex > this.items.size() - 1) {
                this.preCurrentIndex -= this.items.size();
            }
        }
        int i2 = (int) (this.totalScrollY % (this.lineSpacingMultiplier * this.maxTextHeight));
        int i3 = 0;
        while (true) {
            int i4 = this.itemsVisibleCount;
            if (i3 >= i4) {
                break;
            }
            int i5 = this.preCurrentIndex - ((i4 / 2) - i3);
            if (this.isLoop) {
                while (i5 < 0) {
                    i5 += this.items.size();
                }
                while (i5 > this.items.size() - 1) {
                    i5 -= this.items.size();
                }
                this.drawingStrings[i3] = this.items.get(i5);
            } else if (i5 < 0) {
                this.drawingStrings[i3] = "";
            } else if (i5 > this.items.size() - 1) {
                this.drawingStrings[i3] = "";
            } else {
                this.drawingStrings[i3] = this.items.get(i5);
            }
            i3++;
        }
        float f = this.paddingLeft;
        int i6 = this.firstLineY;
        canvas.drawLine(f, i6, this.measuredWidth, i6, this.paintIndicator);
        float f2 = this.paddingLeft;
        int i7 = this.secondLineY;
        canvas.drawLine(f2, i7, this.measuredWidth, i7, this.paintIndicator);
        for (int i8 = 0; i8 < this.itemsVisibleCount; i8++) {
            canvas.save();
            float f3 = this.maxTextHeight * this.lineSpacingMultiplier;
            double d = (((i8 * f3) - i2) * 3.141592653589793d) / this.halfCircumference;
            if (d >= 3.141592653589793d || d <= AudioStats.AUDIO_AMPLITUDE_NONE) {
                canvas.restore();
            } else {
                int cos = (int) ((this.radius - (Math.cos(d) * this.radius)) - ((Math.sin(d) * this.maxTextHeight) / 2.0d));
                canvas.translate(0.0f, cos);
                canvas.scale(1.0f, (float) Math.sin(d));
                int i9 = this.firstLineY;
                if (cos <= i9 && this.maxTextHeight + cos >= i9) {
                    canvas.save();
                    canvas.clipRect(0, 0, this.measuredWidth, this.firstLineY - cos);
                    canvas.drawText(this.drawingStrings[i8], getTextX(r3, this.paintOuterText, this.tempRect), this.maxTextHeight, this.paintOuterText);
                    canvas.restore();
                    canvas.save();
                    canvas.clipRect(0, this.firstLineY - cos, this.measuredWidth, (int) f3);
                    canvas.drawText(this.drawingStrings[i8], getTextX(r2, this.paintCenterText, this.tempRect), this.maxTextHeight, this.paintCenterText);
                    canvas.restore();
                } else {
                    int i10 = this.secondLineY;
                    if (cos <= i10 && this.maxTextHeight + cos >= i10) {
                        canvas.save();
                        canvas.clipRect(0, 0, this.measuredWidth, this.secondLineY - cos);
                        canvas.drawText(this.drawingStrings[i8], getTextX(r3, this.paintCenterText, this.tempRect), this.maxTextHeight, this.paintCenterText);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0, this.secondLineY - cos, this.measuredWidth, (int) f3);
                        canvas.drawText(this.drawingStrings[i8], getTextX(r2, this.paintOuterText, this.tempRect), this.maxTextHeight, this.paintOuterText);
                        canvas.restore();
                    } else if (cos >= i9 && this.maxTextHeight + cos <= i10) {
                        canvas.clipRect(0, 0, this.measuredWidth, (int) f3);
                        canvas.drawText(this.drawingStrings[i8], getTextX(r2, this.paintCenterText, this.tempRect), this.maxTextHeight, this.paintCenterText);
                        this.selectedItem = this.items.indexOf(this.drawingStrings[i8]);
                    } else {
                        canvas.clipRect(0, 0, this.measuredWidth, (int) f3);
                        canvas.drawText(this.drawingStrings[i8], getTextX(r2, this.paintOuterText, this.tempRect), this.maxTextHeight, this.paintOuterText);
                    }
                }
                canvas.restore();
            }
        }
    }

    private int getTextX(String str, Paint paint, Rect rect) {
        paint.getTextBounds(str, 0, str.length(), rect);
        int width = (int) (rect.width() * this.scaleX);
        int i = this.measuredWidth;
        int i2 = this.paddingLeft;
        return (((i - i2) - width) / 2) + i2;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        remeasure();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = this.flingGestureDetector.onTouchEvent(motionEvent);
        float f = this.lineSpacingMultiplier * this.maxTextHeight;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startTime = System.currentTimeMillis();
            cancelFuture();
            this.previousY = motionEvent.getRawY();
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (action == 2) {
            float rawY = this.previousY - motionEvent.getRawY();
            this.previousY = motionEvent.getRawY();
            this.totalScrollY = (int) (this.totalScrollY + rawY);
            if (!this.isLoop) {
                float f2 = (-this.initPosition) * f;
                float size = ((this.items.size() - 1) - this.initPosition) * f;
                int i = this.totalScrollY;
                if (i < f2) {
                    this.totalScrollY = (int) f2;
                } else if (i > size) {
                    this.totalScrollY = (int) size;
                }
            }
        } else {
            if (!onTouchEvent) {
                float y = motionEvent.getY();
                int i2 = this.radius;
                int acos = (int) (((Math.acos((i2 - y) / i2) * this.radius) + (f / DEFAULT_LINE_SPACE)) / f);
                this.mOffset = (int) (((acos - (this.itemsVisibleCount / 2)) * f) - (((this.totalScrollY % f) + f) % f));
                if (System.currentTimeMillis() - this.startTime > 120) {
                    smoothScroll(ACTION.DAGGLE);
                } else {
                    smoothScroll(ACTION.CLICK);
                }
            }
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        invalidate();
        return true;
    }
}
