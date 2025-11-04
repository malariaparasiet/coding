package com.wifiled.ipixels.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.wifiled.baselib.app.language.Constance;
import com.wifiled.baselib.app.language.LanguageUtil;

/* loaded from: classes3.dex */
public class VerticalSeekBar extends AppCompatSeekBar {
    private OnSeekBarListener mOnSeekBarStopListener;
    private Drawable mThumb;

    public interface OnSeekBarListener {
        void onStopTrackingTouch(SeekBar seekBar);
    }

    void onStartTrackingTouch() {
    }

    public VerticalSeekBar(Context context) {
        super(context);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnSeekBarStopListener(OnSeekBarListener l) {
        this.mOnSeekBarStopListener = l;
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onDraw(Canvas c) {
        if (LanguageUtil.getSaveLanguage(getContext()).equals(Constance.SP.INSTANCE.getLANGUAGE_AR())) {
            Log.v("ruis", " if (LanguageUtil.getSaveLanguage(getContext()).equals(Constance.SP.Companion.getLANGUAGE_AR())){");
            c.rotate(90.0f);
            c.translate(getHeight() - getWidth(), -getWidth());
        } else {
            c.rotate(-90.0f);
            c.translate(-getHeight(), 0.0f);
        }
        Log.i("getHeight()", getHeight() + " width: " + getWidth());
        super.onDraw(c);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0015, code lost:
    
        if (r0 != 3) goto L16;
     */
    @Override // android.widget.AbsSeekBar, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.isEnabled()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            int r0 = r5.getAction()
            r2 = 1
            if (r0 == 0) goto L3c
            if (r0 == r2) goto L35
            r3 = 2
            if (r0 == r3) goto L18
            r5 = 3
            if (r0 == r5) goto L35
            goto L42
        L18:
            r4.attemptClaimDrag()
            int r0 = r4.getMax()
            int r1 = r4.getMax()
            float r1 = (float) r1
            float r5 = r5.getY()
            float r1 = r1 * r5
            int r5 = r4.getHeight()
            float r5 = (float) r5
            float r1 = r1 / r5
            int r5 = (int) r1
            int r0 = r0 - r5
            r4.setProgress(r0)
            goto L42
        L35:
            r4.onStopTrackingTouch()
            r4.setPressed(r1)
            goto L42
        L3c:
            r4.setPressed(r2)
            r4.onStartTrackingTouch()
        L42:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.view.VerticalSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        onProgressRefresh(getProgress() / getMax(), true);
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int progress, boolean animate) {
        super.setProgress(progress, animate);
        onProgressRefresh(getProgress() / getMax(), true);
    }

    void onProgressRefresh(float scale, boolean fromUser) {
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            setThumbPos(getHeight(), drawable, scale, Integer.MIN_VALUE);
            invalidate();
        }
    }

    private void setThumbPos(int w, Drawable thumb, float scale, int gap) {
        int i;
        int paddingLeft = (w - getPaddingLeft()) - getPaddingRight();
        int intrinsicWidth = thumb.getIntrinsicWidth();
        int intrinsicHeight = thumb.getIntrinsicHeight();
        int i2 = (int) ((scale * paddingLeft) + 0.5f);
        if (gap == Integer.MIN_VALUE) {
            Rect bounds = thumb.getBounds();
            gap = bounds.top;
            i = bounds.bottom;
        } else {
            i = gap + intrinsicHeight;
        }
        thumb.setBounds(i2, gap, intrinsicWidth + i2, i);
    }

    @Override // android.widget.AbsSeekBar
    public void setThumb(Drawable thumb) {
        this.mThumb = thumb;
        super.setThumb(thumb);
    }

    void onStopTrackingTouch() {
        OnSeekBarListener onSeekBarListener = this.mOnSeekBarStopListener;
        if (onSeekBarListener != null) {
            onSeekBarListener.onStopTrackingTouch(this);
        }
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }
}
