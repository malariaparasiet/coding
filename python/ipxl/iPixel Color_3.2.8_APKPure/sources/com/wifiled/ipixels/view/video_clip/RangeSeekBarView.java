package com.wifiled.ipixels.view.video_clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.camera.video.AudioStats;
import com.google.android.material.card.MaterialCardViewHelper;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.utils.DateUtil;
import java.text.DecimalFormat;

/* loaded from: classes3.dex */
public class RangeSeekBarView extends View {
    public static final int ACTION_POINTER_INDEX_MASK = 65280;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    public static final int INVALID_POINTER_ID = 255;
    private static final String TAG = "RangeSeekBarView";
    private static final int TextPositionY = ScreenUtil.dip2px(8.0f);
    private static final int paddingTop = ScreenUtil.dip2px(10.0f);
    private double absoluteMaxValuePrim;
    private double absoluteMinValuePrim;
    private int borderColorRes;
    private boolean isMin;
    private boolean isTouchDown;
    private int mActivePointerId;
    private float mDownMotionX;
    private long mEndPosition;
    private boolean mIsDragging;
    private long mMinShootTime;
    private OnRangeSeekBarChangeListener mRangeSeekBarChangeListener;
    private int mScaledTouchSlop;
    private final Paint mShadow;
    private long mStartPosition;
    private final Paint mVideoTrimTimePaintL;
    private final Paint mVideoTrimTimePaintR;
    private double min_width;
    private double normalizedMaxValue;
    private double normalizedMaxValueTime;
    private double normalizedMinValue;
    private double normalizedMinValueTime;
    private boolean notifyWhileDragging;
    private final float padding;
    private Paint paint;
    private Thumb pressedThumb;
    private Paint rectPaint;
    private float thumbHalfWidth;
    private Bitmap thumbImageLeft;
    private Bitmap thumbImageRight;
    private float thumbPaddingTop;
    private Bitmap thumbPressedImage;
    private int thumbWidth;
    private int whiteColorRes;

    public interface OnRangeSeekBarChangeListener {
        void onRangeSeekBarValuesChanged(RangeSeekBarView bar, long minValue, long maxValue, int action, boolean isMin, Thumb pressedThumb);
    }

    public enum Thumb {
        MIN,
        MAX
    }

    public RangeSeekBarView(Context context) {
        super(context);
        this.mActivePointerId = 255;
        this.mMinShootTime = 1000L;
        this.normalizedMinValue = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.normalizedMaxValue = 1.0d;
        this.normalizedMinValueTime = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.normalizedMaxValueTime = 1.0d;
        this.mVideoTrimTimePaintL = new Paint();
        this.mVideoTrimTimePaintR = new Paint();
        this.mShadow = new Paint();
        this.padding = 0.0f;
        this.mStartPosition = 0L;
        this.mEndPosition = 0L;
        this.thumbPaddingTop = 0.0f;
        this.min_width = 1.0d;
        this.notifyWhileDragging = false;
        this.borderColorRes = Color.parseColor("#ffcc00");
        this.whiteColorRes = -1;
    }

    public RangeSeekBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mActivePointerId = 255;
        this.mMinShootTime = 1000L;
        this.normalizedMinValue = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.normalizedMaxValue = 1.0d;
        this.normalizedMinValueTime = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.normalizedMaxValueTime = 1.0d;
        this.mVideoTrimTimePaintL = new Paint();
        this.mVideoTrimTimePaintR = new Paint();
        this.mShadow = new Paint();
        this.padding = 0.0f;
        this.mStartPosition = 0L;
        this.mEndPosition = 0L;
        this.thumbPaddingTop = 0.0f;
        this.min_width = 1.0d;
        this.notifyWhileDragging = false;
        this.borderColorRes = Color.parseColor("#ffcc00");
        this.whiteColorRes = -1;
    }

    public RangeSeekBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mActivePointerId = 255;
        this.mMinShootTime = 1000L;
        this.normalizedMinValue = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.normalizedMaxValue = 1.0d;
        this.normalizedMinValueTime = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.normalizedMaxValueTime = 1.0d;
        this.mVideoTrimTimePaintL = new Paint();
        this.mVideoTrimTimePaintR = new Paint();
        this.mShadow = new Paint();
        this.padding = 0.0f;
        this.mStartPosition = 0L;
        this.mEndPosition = 0L;
        this.thumbPaddingTop = 0.0f;
        this.min_width = 1.0d;
        this.notifyWhileDragging = false;
        this.borderColorRes = Color.parseColor("#ffcc00");
        this.whiteColorRes = -1;
    }

    public RangeSeekBarView(Context context, long absoluteMinValuePrim, long absoluteMaxValuePrim) {
        super(context);
        this.mActivePointerId = 255;
        this.mMinShootTime = 1000L;
        this.normalizedMinValue = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.normalizedMaxValue = 1.0d;
        this.normalizedMinValueTime = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.normalizedMaxValueTime = 1.0d;
        this.mVideoTrimTimePaintL = new Paint();
        this.mVideoTrimTimePaintR = new Paint();
        this.mShadow = new Paint();
        this.padding = 0.0f;
        this.mStartPosition = 0L;
        this.mEndPosition = 0L;
        this.thumbPaddingTop = 0.0f;
        this.min_width = 1.0d;
        this.notifyWhileDragging = false;
        this.borderColorRes = Color.parseColor("#ffcc00");
        this.whiteColorRes = -1;
        this.absoluteMinValuePrim = absoluteMinValuePrim;
        this.absoluteMaxValuePrim = absoluteMaxValuePrim;
        setFocusable(true);
        setFocusableInTouchMode(true);
        init();
    }

    private void init() {
        this.mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.thumbImageLeft = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_video_thumb_handle);
        this.thumbImageRight = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_video_thumb_handle02);
        int width = this.thumbImageLeft.getWidth();
        int height = this.thumbImageLeft.getHeight();
        int dip2px = ScreenUtil.dip2px(20.0f);
        Matrix matrix = new Matrix();
        matrix.postScale((dip2px * 1.0f) / width, (ScreenUtil.dip2px(50.0f) * 1.0f) / height);
        this.thumbImageLeft = Bitmap.createBitmap(this.thumbImageLeft, 0, 0, width, height, matrix, true);
        this.thumbImageRight = Bitmap.createBitmap(this.thumbImageRight, 0, 0, width, height, matrix, true);
        this.thumbPressedImage = this.thumbImageLeft;
        this.thumbWidth = dip2px;
        this.thumbHalfWidth = dip2px / 2;
        int color = getContext().getResources().getColor(R.color.shadow_color);
        this.mShadow.setAntiAlias(true);
        this.mShadow.setColor(color);
        this.paint = new Paint(1);
        Paint paint = new Paint(1);
        this.rectPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.rectPaint.setColor(this.borderColorRes);
        this.mVideoTrimTimePaintL.setStrokeWidth(3.0f);
        this.mVideoTrimTimePaintL.setARGB(255, 51, 51, 51);
        this.mVideoTrimTimePaintL.setTextSize(28.0f);
        this.mVideoTrimTimePaintL.setAntiAlias(true);
        this.mVideoTrimTimePaintL.setColor(this.whiteColorRes);
        this.mVideoTrimTimePaintL.setTextAlign(Paint.Align.LEFT);
        this.mVideoTrimTimePaintR.setStrokeWidth(3.0f);
        this.mVideoTrimTimePaintR.setARGB(255, 51, 51, 51);
        this.mVideoTrimTimePaintR.setTextSize(28.0f);
        this.mVideoTrimTimePaintR.setAntiAlias(true);
        this.mVideoTrimTimePaintR.setColor(this.whiteColorRes);
        this.mVideoTrimTimePaintR.setTextAlign(Paint.Align.RIGHT);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(View.MeasureSpec.getMode(widthMeasureSpec) != 0 ? View.MeasureSpec.getSize(widthMeasureSpec) : MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, View.MeasureSpec.getMode(heightMeasureSpec) != 0 ? View.MeasureSpec.getSize(heightMeasureSpec) : 120);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth() - getPaddingRight();
        float normalizedToScreen = normalizedToScreen(this.normalizedMinValue);
        float normalizedToScreen2 = normalizedToScreen(this.normalizedMaxValue);
        Rect rect = new Rect((int) 0.0f, getHeight(), (int) normalizedToScreen, 0);
        Rect rect2 = new Rect((int) normalizedToScreen2, getHeight(), (int) width, 0);
        canvas.drawRect(rect, this.mShadow);
        canvas.drawRect(rect2, this.mShadow);
        float f = normalizedToScreen + 10.0f;
        float f2 = this.thumbPaddingTop;
        int i = paddingTop;
        float f3 = normalizedToScreen2 - 10.0f;
        canvas.drawRect(f, f2 + i, f3, f2 + ScreenUtil.dip2px(1.0f) + i, this.rectPaint);
        canvas.drawRect(f, getHeight() - ScreenUtil.dip2px(1.0f), f3, getHeight(), this.rectPaint);
        drawThumb(normalizedToScreen(this.normalizedMinValue), false, canvas, true);
        drawThumb(normalizedToScreen(this.normalizedMaxValue), false, canvas, false);
        drawVideoTrimTimeText(canvas);
    }

    private void drawThumb(float screenCoord, boolean pressed, Canvas canvas, boolean isLeft) {
        Bitmap bitmap;
        if (pressed) {
            bitmap = this.thumbPressedImage;
        } else {
            bitmap = isLeft ? this.thumbImageLeft : this.thumbImageRight;
        }
        canvas.drawBitmap(bitmap, screenCoord - (isLeft ? 0 : this.thumbWidth), paddingTop, this.paint);
    }

    private void drawVideoTrimTimeText(Canvas canvas) {
        String convertSecondsToTime = DateUtil.convertSecondsToTime(this.mStartPosition);
        String convertSecondsToTime2 = DateUtil.convertSecondsToTime(this.mEndPosition);
        float normalizedToScreen = normalizedToScreen(this.normalizedMinValue);
        int i = TextPositionY;
        canvas.drawText(convertSecondsToTime, normalizedToScreen, i, this.mVideoTrimTimePaintL);
        canvas.drawText(convertSecondsToTime2, normalizedToScreen(this.normalizedMaxValue), i, this.mVideoTrimTimePaintR);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        OnRangeSeekBarChangeListener onRangeSeekBarChangeListener;
        if (this.isTouchDown) {
            return super.onTouchEvent(event);
        }
        if (event.getPointerCount() > 1) {
            return super.onTouchEvent(event);
        }
        if (!isEnabled()) {
            return false;
        }
        if (this.absoluteMaxValuePrim <= this.mMinShootTime) {
            return super.onTouchEvent(event);
        }
        int action = event.getAction() & 255;
        if (action == 0) {
            int pointerId = event.getPointerId(event.getPointerCount() - 1);
            this.mActivePointerId = pointerId;
            float x = event.getX(event.findPointerIndex(pointerId));
            this.mDownMotionX = x;
            Thumb evalPressedThumb = evalPressedThumb(x);
            this.pressedThumb = evalPressedThumb;
            if (evalPressedThumb == null) {
                return super.onTouchEvent(event);
            }
            setPressed(true);
            onStartTrackingTouch();
            trackTouchEvent(event);
            attemptClaimDrag();
            OnRangeSeekBarChangeListener onRangeSeekBarChangeListener2 = this.mRangeSeekBarChangeListener;
            if (onRangeSeekBarChangeListener2 != null) {
                onRangeSeekBarChangeListener2.onRangeSeekBarValuesChanged(this, getSelectedMinValue(), getSelectedMaxValue(), 0, this.isMin, this.pressedThumb);
            }
        } else if (action == 1) {
            if (this.mIsDragging) {
                trackTouchEvent(event);
                onStopTrackingTouch();
                setPressed(false);
            } else {
                onStartTrackingTouch();
                trackTouchEvent(event);
                onStopTrackingTouch();
            }
            invalidate();
            OnRangeSeekBarChangeListener onRangeSeekBarChangeListener3 = this.mRangeSeekBarChangeListener;
            if (onRangeSeekBarChangeListener3 != null) {
                onRangeSeekBarChangeListener3.onRangeSeekBarValuesChanged(this, getSelectedMinValue(), getSelectedMaxValue(), 1, this.isMin, this.pressedThumb);
            }
            this.pressedThumb = null;
        } else if (action != 2) {
            if (action == 3) {
                if (this.mIsDragging) {
                    onStopTrackingTouch();
                    setPressed(false);
                }
                invalidate();
            } else if (action == 5) {
                int pointerCount = event.getPointerCount() - 1;
                this.mDownMotionX = event.getX(pointerCount);
                this.mActivePointerId = event.getPointerId(pointerCount);
                invalidate();
            } else if (action == 6) {
                onSecondaryPointerUp(event);
                invalidate();
            }
        } else if (this.pressedThumb != null) {
            if (this.mIsDragging) {
                trackTouchEvent(event);
            } else if (Math.abs(event.getX(event.findPointerIndex(this.mActivePointerId)) - this.mDownMotionX) > this.mScaledTouchSlop) {
                setPressed(true);
                Log.e(TAG, "没有拖住最大最小值");
                invalidate();
                onStartTrackingTouch();
                trackTouchEvent(event);
                attemptClaimDrag();
            }
            if (this.notifyWhileDragging && (onRangeSeekBarChangeListener = this.mRangeSeekBarChangeListener) != null) {
                onRangeSeekBarChangeListener.onRangeSeekBarValuesChanged(this, getSelectedMinValue(), getSelectedMaxValue(), 2, this.isMin, this.pressedThumb);
            }
        }
        return true;
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        int action = (ev.getAction() & 65280) >> 8;
        if (ev.getPointerId(action) == this.mActivePointerId) {
            int i = action == 0 ? 1 : 0;
            this.mDownMotionX = ev.getX(i);
            this.mActivePointerId = ev.getPointerId(i);
        }
    }

    private void trackTouchEvent(MotionEvent event) {
        if (event.getPointerCount() > 1) {
            return;
        }
        Log.e(TAG, "trackTouchEvent: " + event.getAction() + " x: " + event.getX());
        try {
            float x = event.getX(event.findPointerIndex(this.mActivePointerId));
            if (Thumb.MIN.equals(this.pressedThumb)) {
                setNormalizedMinValue(screenToNormalized(x, 0));
            } else if (Thumb.MAX.equals(this.pressedThumb)) {
                setNormalizedMaxValue(screenToNormalized(x, 1));
            }
        } catch (Exception unused) {
        }
    }

    private double screenToNormalized(float screenCoord, int position) {
        double d;
        if (getWidth() <= 0.0f) {
            return AudioStats.AUDIO_AMPLITUDE_NONE;
        }
        this.isMin = false;
        double d2 = screenCoord;
        float normalizedToScreen = normalizedToScreen(this.normalizedMinValue);
        float normalizedToScreen2 = normalizedToScreen(this.normalizedMaxValue);
        double d3 = this.mMinShootTime;
        double d4 = this.absoluteMaxValuePrim;
        double d5 = (d3 / (d4 - this.absoluteMinValuePrim)) * (r6 - (this.thumbWidth * 2));
        if (d4 > 300000.0d) {
            this.min_width = Double.parseDouble(new DecimalFormat("0.0000").format(d5));
        } else {
            this.min_width = Math.round(d5 + 0.5d);
        }
        if (position == 0) {
            if (isInThumbRangeLeft(screenCoord, this.normalizedMinValue, 0.5d)) {
                return this.normalizedMinValue;
            }
            double valueLength = getValueLength() - ((((float) getWidth()) - normalizedToScreen2 >= 0.0f ? getWidth() - normalizedToScreen2 : 0.0f) + this.min_width);
            double d6 = normalizedToScreen;
            if (d2 > d6) {
                d2 = (d2 - d6) + d6;
            } else if (d2 <= d6) {
                d2 = d6 - (d6 - d2);
            }
            if (d2 > valueLength) {
                this.isMin = true;
            } else {
                valueLength = d2;
            }
            int i = this.thumbWidth;
            if (valueLength < (i * 2) / 3) {
                valueLength = 0.0d;
            }
            double d7 = valueLength - AudioStats.AUDIO_AMPLITUDE_NONE;
            this.normalizedMinValueTime = Math.min(1.0d, Math.max(AudioStats.AUDIO_AMPLITUDE_NONE, d7 / (r6 - (i * 2))));
            return Math.min(1.0d, Math.max(AudioStats.AUDIO_AMPLITUDE_NONE, d7 / (r7 - 0.0f)));
        }
        if (isInThumbRange(screenCoord, this.normalizedMaxValue, 0.5d)) {
            return this.normalizedMaxValue;
        }
        double valueLength2 = getValueLength() - (normalizedToScreen + this.min_width);
        double d8 = normalizedToScreen2;
        if (d2 > d8) {
            d2 = (d2 - d8) + d8;
        } else if (d2 <= d8) {
            d2 = d8 - (d8 - d2);
        }
        double width = getWidth() - d2;
        if (width > valueLength2) {
            this.isMin = true;
            d2 = getWidth() - valueLength2;
            d = valueLength2;
        } else {
            d = width;
        }
        if (d < (this.thumbWidth * 2) / 3) {
            d2 = getWidth();
            d = AudioStats.AUDIO_AMPLITUDE_NONE;
        }
        this.normalizedMaxValueTime = Math.min(1.0d, Math.max(AudioStats.AUDIO_AMPLITUDE_NONE, 1.0d - ((d - AudioStats.AUDIO_AMPLITUDE_NONE) / (r6 - (this.thumbWidth * 2)))));
        return Math.min(1.0d, Math.max(AudioStats.AUDIO_AMPLITUDE_NONE, (d2 - AudioStats.AUDIO_AMPLITUDE_NONE) / (r7 - 0.0f)));
    }

    private int getValueLength() {
        return getWidth() - (this.thumbWidth * 2);
    }

    private Thumb evalPressedThumb(float touchX) {
        boolean isInThumbRange = isInThumbRange(touchX, this.normalizedMinValue, 2.0d);
        boolean isInThumbRange2 = isInThumbRange(touchX, this.normalizedMaxValue, 2.0d);
        if (isInThumbRange && isInThumbRange2) {
            return touchX / ((float) getWidth()) > 0.5f ? Thumb.MIN : Thumb.MAX;
        }
        if (isInThumbRange) {
            return Thumb.MIN;
        }
        if (isInThumbRange2) {
            return Thumb.MAX;
        }
        return null;
    }

    private boolean isInThumbRange(float touchX, double normalizedThumbValue, double scale) {
        return ((double) Math.abs(touchX - normalizedToScreen(normalizedThumbValue))) <= ((double) this.thumbHalfWidth) * scale;
    }

    private boolean isInThumbRangeLeft(float touchX, double normalizedThumbValue, double scale) {
        return ((double) Math.abs((touchX - normalizedToScreen(normalizedThumbValue)) - ((float) this.thumbWidth))) <= ((double) this.thumbHalfWidth) * scale;
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    void onStartTrackingTouch() {
        this.mIsDragging = true;
    }

    void onStopTrackingTouch() {
        this.mIsDragging = false;
    }

    public void setMinShootTime(long min_cut_time) {
        this.mMinShootTime = min_cut_time;
    }

    private float normalizedToScreen(double normalizedCoord) {
        return (float) (getPaddingLeft() + (normalizedCoord * ((getWidth() - getPaddingLeft()) - getPaddingRight())));
    }

    private double valueToNormalized(long value) {
        double d = this.absoluteMaxValuePrim;
        double d2 = this.absoluteMinValuePrim;
        return AudioStats.AUDIO_AMPLITUDE_NONE == d - d2 ? AudioStats.AUDIO_AMPLITUDE_NONE : (value - d2) / (d - d2);
    }

    public void setStartEndTime(long start, long end) {
        this.mStartPosition = start / 1000;
        this.mEndPosition = end / 1000;
    }

    public void setSelectedMinValue(long value) {
        if (AudioStats.AUDIO_AMPLITUDE_NONE == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            setNormalizedMinValue(AudioStats.AUDIO_AMPLITUDE_NONE);
        } else {
            setNormalizedMinValue(valueToNormalized(value));
        }
    }

    public void setSelectedMaxValue(long value) {
        if (AudioStats.AUDIO_AMPLITUDE_NONE == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            setNormalizedMaxValue(1.0d);
        } else {
            setNormalizedMaxValue(valueToNormalized(value));
        }
    }

    public void setNormalizedMinValue(double value) {
        this.normalizedMinValue = Math.max(AudioStats.AUDIO_AMPLITUDE_NONE, Math.min(1.0d, Math.min(value, this.normalizedMaxValue)));
        invalidate();
    }

    public void setNormalizedMaxValue(double value) {
        this.normalizedMaxValue = Math.max(AudioStats.AUDIO_AMPLITUDE_NONE, Math.min(1.0d, Math.max(value, this.normalizedMinValue)));
        invalidate();
    }

    public long getSelectedMinValue() {
        return normalizedToValue(this.normalizedMinValueTime);
    }

    public long getSelectedMaxValue() {
        return normalizedToValue(this.normalizedMaxValueTime);
    }

    private long normalizedToValue(double normalized) {
        double d = this.absoluteMinValuePrim;
        return (long) (d + (normalized * (this.absoluteMaxValuePrim - d)));
    }

    public boolean isNotifyWhileDragging() {
        return this.notifyWhileDragging;
    }

    public void setNotifyWhileDragging(boolean flag) {
        this.notifyWhileDragging = flag;
    }

    public void setTouchDown(boolean touchDown) {
        this.isTouchDown = touchDown;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUPER", super.onSaveInstanceState());
        bundle.putDouble("MIN", this.normalizedMinValue);
        bundle.putDouble("MAX", this.normalizedMaxValue);
        bundle.putDouble("MIN_TIME", this.normalizedMinValueTime);
        bundle.putDouble("MAX_TIME", this.normalizedMaxValueTime);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcel) {
        Bundle bundle = (Bundle) parcel;
        super.onRestoreInstanceState(bundle.getParcelable("SUPER"));
        this.normalizedMinValue = bundle.getDouble("MIN");
        this.normalizedMaxValue = bundle.getDouble("MAX");
        this.normalizedMinValueTime = bundle.getDouble("MIN_TIME");
        this.normalizedMaxValueTime = bundle.getDouble("MAX_TIME");
    }

    public void setOnRangeSeekBarChangeListener(OnRangeSeekBarChangeListener listener) {
        this.mRangeSeekBarChangeListener = listener;
    }
}
