package com.wifiled.ipixels.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes3.dex */
public class ViewPagerAllResponse extends ViewPager {
    private static final float DISTANCE = 0.0f;
    private int currentIndex;
    private float downX;
    private float downY;
    private boolean isSlide;
    private int mMaxNumber;
    private int startCurrentIndex;
    private float upX;
    private float upY;

    private void initData() {
    }

    public ViewPagerAllResponse(Context context) {
        this(context, null);
        initData();
    }

    public ViewPagerAllResponse(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.currentIndex = 0;
        this.startCurrentIndex = 2000;
        this.isSlide = false;
        initData();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.downX = ev.getX();
            this.downY = ev.getY();
        } else if (ev.getAction() == 1) {
            this.upX = ev.getX();
            this.upY = ev.getY();
            if (Math.abs(this.upX - this.downX) > 0.0f || Math.abs(this.upY - this.downY) > 0.0f) {
                return super.dispatchTouchEvent(ev);
            }
            View clickPageOnScreen = clickPageOnScreen(ev);
            if (clickPageOnScreen != null) {
                int intValue = ((Integer) clickPageOnScreen.getTag()).intValue();
                if (getCurrentItem() != intValue) {
                    setCurrentItem(intValue);
                }
            }
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    private View clickPageOnScreen(MotionEvent ev) {
        int childCount = getChildCount();
        int currentItem = getCurrentItem();
        int[] iArr = new int[2];
        float rawX = ev.getRawX();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int intValue = ((Integer) childAt.getTag()).intValue();
            childAt.getLocationOnScreen(iArr);
            int i2 = iArr[0];
            int width = childAt.getWidth() + i2;
            if (intValue < currentItem) {
                width = (int) (width - ((childAt.getWidth() * 0.3f) * 0.5d));
                i2 = (int) (i2 - ((childAt.getWidth() * 0.3f) * 0.5d));
            }
            if (rawX > i2 && rawX < width) {
                return childAt;
            }
        }
        return null;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.downY = ev.getY();
        } else if (action == 1) {
            float y = ev.getY();
            this.upY = y;
            if (Math.abs(y - this.downY) > 0.0f) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.downY = ev.getY();
        } else if (action == 1) {
            float y = ev.getY();
            this.upY = y;
            if (Math.abs(y - this.downY) > 0.0f) {
                return super.onTouchEvent(ev);
            }
        }
        return super.onTouchEvent(ev);
    }
}
