package com.wifiled.ipixels.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.wifiled.ipixels.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class FlowLayout extends ViewGroup {
    public static final int GRAVITY_BOTTOM = 5;
    public static final int GRAVITY_CENTER = 2;
    public static final int GRAVITY_LEFT = 1;
    public static final int GRAVITY_NONE = -1;
    public static final int GRAVITY_RIGHT = 3;
    public static final int GRAVITY_TOP = 4;
    public static final int ORIENTATION_HORIZONTAL = 2;
    public static final int ORIENTATION_VERTICAL = 3;
    private static final int SPACING_NONE = -1;
    private int availableHeight;
    private int availableWidth;
    private List<Integer> currentLineChildIndex;
    private int currentLineHeight;
    private int currentLineWidth;
    private List<Integer> currentRowChildIndex;
    private int currentRowHeight;
    private int currentRowWidth;
    private boolean efficientMode;
    private int gravity;
    public int horizontalSpacing;
    private List<List<Integer>> lineChildIndex;
    private List<Integer> lineHeightList;
    private int lineNum;
    private List<Integer> lineWidthList;
    private int maxHeight;
    private int maxWidth;
    private int orientation;
    private List<List<Integer>> rowChildIndex;
    private List<Integer> rowHeightList;
    private List<Integer> rowWidthList;
    private float totalWeight;
    public int verticalSpacing;
    private List<Integer> weightChildList;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        this.currentLineWidth = 0;
        this.currentLineHeight = 0;
        this.maxWidth = 0;
        this.currentRowWidth = 0;
        this.currentRowHeight = 0;
        this.maxHeight = 0;
        this.totalWeight = 0.0f;
        this.orientation = 2;
        this.gravity = -1;
        this.lineNum = Integer.MIN_VALUE;
        this.horizontalSpacing = 0;
        this.verticalSpacing = 0;
        this.efficientMode = false;
        initAttr(context, attributeSet, defStyle);
    }

    private void initAttr(Context context, AttributeSet attributeSet, int defStyle) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlowLayout, defStyle, 0);
        try {
            this.orientation = obtainStyledAttributes.getInt(8, 2);
            this.gravity = obtainStyledAttributes.getInt(3, -1);
            this.verticalSpacing = obtainStyledAttributes.getDimensionPixelSize(9, -1);
            this.horizontalSpacing = obtainStyledAttributes.getDimensionPixelSize(5, -1);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int i = this.orientation;
        if (i == 2) {
            measureHorizontally(widthMeasureSpec, heightMeasureSpec);
        } else {
            if (i != 3) {
                return;
            }
            measureVertically(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private void measureHorizontally(int widthMeasureSpec, int heightMeasureSpec) {
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        this.availableWidth = (size - getPaddingLeft()) - getPaddingRight();
        this.lineChildIndex = new ArrayList();
        this.lineHeightList = new ArrayList();
        this.lineWidthList = new ArrayList();
        this.weightChildList = new ArrayList();
        int i = 0;
        this.maxWidth = 0;
        this.lineNum = Integer.MIN_VALUE;
        newLine();
        int i2 = 0;
        while (i2 < getChildCount()) {
            View childAt = getChildAt(i2);
            LayoutParam layoutParam = (LayoutParam) childAt.getLayoutParams();
            int i3 = layoutParam.width;
            int i4 = layoutParam.height;
            int childHorizontalSpacing = getChildHorizontalSpacing(childAt);
            int childVerticalSpacing = getChildVerticalSpacing(childAt);
            int i5 = (mode2 == 0 && i4 == 0) ? i : 1073741824;
            if (layoutParam.lineNum != this.lineNum) {
                endLine(this.availableWidth - this.currentLineWidth);
                newLine();
                this.lineNum = layoutParam.lineNum;
            }
            if (layoutParam.width == -1) {
                int i6 = this.currentLineWidth + layoutParam.leftMargin + layoutParam.rightMargin + childHorizontalSpacing;
                int i7 = this.availableWidth;
                if (i6 <= i7) {
                    int i8 = (((i7 - this.currentLineWidth) - layoutParam.leftMargin) - layoutParam.rightMargin) - childHorizontalSpacing;
                    this.currentLineChildIndex.add(Integer.valueOf(i2));
                    this.currentLineWidth = size - getPaddingRight();
                    if (layoutParam.width != -2) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(i4, i5));
                        childAt.getMeasuredWidth();
                        i4 = childAt.getMeasuredHeight();
                    }
                    this.currentLineHeight = Math.max(this.currentLineHeight, i4 + layoutParam.topMargin + layoutParam.bottomMargin + childVerticalSpacing);
                    endLine(0);
                    newLine();
                } else {
                    endLine(0);
                    newLine();
                    int i9 = ((this.availableWidth - layoutParam.leftMargin) - layoutParam.rightMargin) - childHorizontalSpacing;
                    this.currentLineChildIndex.add(Integer.valueOf(i2));
                    this.currentLineWidth = size - getPaddingRight();
                    if (layoutParam.width != -2) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i9, 1073741824), View.MeasureSpec.makeMeasureSpec(i4, i5));
                        childAt.getMeasuredWidth();
                        i4 = childAt.getMeasuredHeight();
                    }
                    this.currentLineHeight = Math.max(this.currentLineHeight, i4 + layoutParam.topMargin + layoutParam.bottomMargin + childVerticalSpacing);
                    endLine(0);
                    newLine();
                }
            } else if (layoutParam.width == 0 && layoutParam.weight != 0.0f) {
                this.totalWeight += layoutParam.weight;
                this.weightChildList.add(Integer.valueOf(i2));
                this.currentLineChildIndex.add(Integer.valueOf(i2));
            } else {
                if (i3 == -2) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(i4, i5));
                    i3 = childAt.getMeasuredWidth();
                    i4 = childAt.getMeasuredHeight();
                }
                int i10 = this.currentLineWidth + layoutParam.leftMargin + i3 + layoutParam.rightMargin + childHorizontalSpacing;
                int i11 = this.availableWidth;
                if (i10 <= i11) {
                    this.currentLineChildIndex.add(Integer.valueOf(i2));
                    this.currentLineWidth += layoutParam.leftMargin + i3 + layoutParam.rightMargin + childHorizontalSpacing;
                    if (layoutParam.width != -2) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(i4, i5));
                        i3 = childAt.getMeasuredWidth();
                        i4 = childAt.getMeasuredHeight();
                    }
                    if (i3 + layoutParam.leftMargin + layoutParam.rightMargin != 0) {
                        this.currentLineHeight = Math.max(this.currentLineHeight, i4 + layoutParam.topMargin + layoutParam.bottomMargin + childVerticalSpacing);
                    }
                } else {
                    endLine(i11 - this.currentLineWidth);
                    newLine();
                    this.currentLineChildIndex.add(Integer.valueOf(i2));
                    this.currentLineWidth = layoutParam.leftMargin + i3 + layoutParam.rightMargin + childHorizontalSpacing;
                    if (layoutParam.width != -2) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(i4, i5));
                        childAt.getMeasuredWidth();
                        i4 = childAt.getMeasuredHeight();
                    }
                    this.currentLineHeight = Math.max(this.currentLineHeight, i4 + layoutParam.topMargin + layoutParam.bottomMargin + childVerticalSpacing);
                }
            }
            i2++;
            i = 0;
        }
        endLine(this.availableWidth - this.currentLineWidth);
        int paddingTop = getPaddingTop();
        for (int i12 = 0; i12 < this.lineChildIndex.size(); i12++) {
            List<Integer> list = this.lineChildIndex.get(i12);
            this.currentLineHeight = this.lineHeightList.get(i12).intValue();
            int intValue = this.lineWidthList.get(i12).intValue();
            int i13 = this.gravity;
            if (i13 == 2) {
                this.currentLineWidth = getPaddingLeft() + ((this.availableWidth - intValue) / 2);
            } else if (i13 == 3 || i13 == 5) {
                this.currentLineWidth = getPaddingLeft() + (this.availableWidth - intValue);
            } else {
                this.currentLineWidth = getPaddingLeft();
            }
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                View childAt2 = getChildAt(it.next().intValue());
                LayoutParam layoutParam2 = (LayoutParam) childAt2.getLayoutParams();
                int measuredWidth = childAt2.getMeasuredWidth();
                layoutParam2.top = layoutParam2.topMargin + paddingTop + (getChildVerticalSpacing(childAt2) / 2);
                layoutParam2.left = this.currentLineWidth + layoutParam2.leftMargin + (getChildHorizontalSpacing(childAt2) / 2);
                this.currentLineWidth += layoutParam2.leftMargin + measuredWidth + layoutParam2.rightMargin;
            }
            paddingTop += this.currentLineHeight;
        }
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            size = this.maxWidth + getPaddingRight() + getPaddingLeft();
        }
        if (mode2 == 0 || mode2 == Integer.MIN_VALUE) {
            size2 = getPaddingBottom() + paddingTop;
        }
        setMeasuredDimension(size, size2);
    }

    private void endLine(int extraSpacing) {
        measureWeightChildHorizontal(this.weightChildList, extraSpacing, this.totalWeight);
        Iterator<Integer> it = this.currentLineChildIndex.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += getChildAt(it.next().intValue()).getMeasuredWidth();
        }
        this.lineWidthList.add(Integer.valueOf(i));
        this.lineChildIndex.add(this.currentLineChildIndex);
        this.lineHeightList.add(Integer.valueOf(this.currentLineHeight));
        this.maxWidth = Math.max(this.currentLineWidth, this.maxWidth);
    }

    private void newLine() {
        this.currentLineChildIndex = new ArrayList();
        this.currentLineHeight = 0;
        this.currentLineWidth = getPaddingLeft();
        this.totalWeight = 0.0f;
    }

    private void measureWeightChildHorizontal(List<Integer> weightChildList, int extraSpacing, float totalWeight) {
        while (weightChildList.size() > 0) {
            View childAt = getChildAt(weightChildList.get(0).intValue());
            LayoutParam layoutParam = (LayoutParam) childAt.getLayoutParams();
            int i = (int) ((layoutParam.weight / totalWeight) * extraSpacing);
            int i2 = layoutParam.height;
            if (layoutParam.leftMargin + i + layoutParam.rightMargin != 0) {
                this.currentLineHeight = Math.max(this.currentLineHeight, layoutParam.topMargin + i2 + layoutParam.bottomMargin);
            }
            childAt.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(i2, 1073741824));
            weightChildList.remove(0);
        }
    }

    private void measureVertically(int widthMeasureSpec, int heightMeasureSpec) {
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        this.availableHeight = (size2 - getPaddingTop()) - getPaddingBottom();
        this.weightChildList = new ArrayList();
        this.rowChildIndex = new ArrayList();
        this.rowWidthList = new ArrayList();
        this.rowHeightList = new ArrayList();
        int i = 0;
        this.maxHeight = 0;
        newRow();
        int i2 = 0;
        while (i2 < getChildCount()) {
            View childAt = getChildAt(i2);
            LayoutParam layoutParam = (LayoutParam) childAt.getLayoutParams();
            int i3 = layoutParam.width;
            int i4 = layoutParam.height;
            int childHorizontalSpacing = getChildHorizontalSpacing(childAt);
            int childVerticalSpacing = getChildVerticalSpacing(childAt);
            int i5 = (mode == 0 && i3 == 0) ? i : 1073741824;
            if (layoutParam.lineNum != this.lineNum) {
                endRow(this.availableHeight - this.currentRowHeight);
                newRow();
                this.lineNum = layoutParam.lineNum;
            }
            if (layoutParam.height == -1) {
                int i6 = this.currentRowHeight + layoutParam.topMargin + layoutParam.height + layoutParam.bottomMargin + childVerticalSpacing;
                int i7 = this.availableHeight;
                if (i6 <= i7) {
                    int i8 = (((i7 - this.currentRowHeight) - layoutParam.topMargin) - layoutParam.bottomMargin) - childVerticalSpacing;
                    this.currentRowChildIndex.add(Integer.valueOf(i2));
                    this.currentRowHeight = size2 - getPaddingBottom();
                    if (layoutParam.height != -2) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, i5), View.MeasureSpec.makeMeasureSpec(i8, 1073741824));
                        i3 = childAt.getMeasuredWidth();
                        childAt.getMeasuredHeight();
                    }
                    this.currentRowWidth = Math.max(this.currentRowWidth, i3 + layoutParam.leftMargin + layoutParam.rightMargin + childHorizontalSpacing);
                    endRow(0);
                    newRow();
                } else {
                    endRow(0);
                    newRow();
                    int i9 = ((this.availableHeight - layoutParam.topMargin) - layoutParam.bottomMargin) - childVerticalSpacing;
                    this.currentRowChildIndex.add(Integer.valueOf(i2));
                    this.currentRowHeight = size2 - getPaddingBottom();
                    if (layoutParam.height != -2) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, i5), View.MeasureSpec.makeMeasureSpec(i9, 1073741824));
                        i3 = childAt.getMeasuredWidth();
                        childAt.getMeasuredHeight();
                    }
                    this.currentRowWidth = Math.max(this.currentRowWidth, i3 + layoutParam.leftMargin + layoutParam.rightMargin + childHorizontalSpacing);
                    endRow(0);
                    newRow();
                }
            } else if (layoutParam.height == 0 && layoutParam.weight != 0.0f) {
                this.currentRowChildIndex.add(Integer.valueOf(i2));
                this.totalWeight += layoutParam.weight;
                this.weightChildList.add(Integer.valueOf(i2));
            } else {
                if (i4 == -2) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, i5), View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
                    i3 = childAt.getMeasuredWidth();
                    i4 = childAt.getMeasuredHeight();
                }
                int i10 = this.currentRowHeight + layoutParam.topMargin + i4 + layoutParam.bottomMargin + childVerticalSpacing;
                int i11 = this.availableHeight;
                if (i10 <= i11) {
                    this.currentRowChildIndex.add(Integer.valueOf(i2));
                    this.currentRowHeight += layoutParam.topMargin + i4 + layoutParam.bottomMargin + childVerticalSpacing;
                    if (layoutParam.height != -2) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, i5), View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
                        i3 = childAt.getMeasuredWidth();
                        i4 = childAt.getMeasuredHeight();
                    }
                    if (i4 + layoutParam.topMargin + layoutParam.bottomMargin != 0) {
                        this.currentRowWidth = Math.max(this.currentRowWidth, i3 + layoutParam.leftMargin + layoutParam.rightMargin + childHorizontalSpacing);
                    }
                } else {
                    endRow(i11 - this.currentRowHeight);
                    newRow();
                    this.currentRowChildIndex.add(Integer.valueOf(i2));
                    this.currentRowHeight = layoutParam.topMargin + i4 + layoutParam.bottomMargin + childVerticalSpacing;
                    if (layoutParam.height != -2) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, i5), View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
                        i3 = childAt.getMeasuredWidth();
                        childAt.getMeasuredHeight();
                    }
                    this.currentRowWidth = Math.max(this.currentRowWidth, i3 + layoutParam.leftMargin + layoutParam.rightMargin + childHorizontalSpacing);
                }
            }
            i2++;
            i = 0;
        }
        endRow(this.availableHeight - this.currentRowHeight);
        int paddingLeft = getPaddingLeft();
        for (int i12 = 0; i12 < this.rowChildIndex.size(); i12++) {
            List<Integer> list = this.rowChildIndex.get(i12);
            this.currentRowWidth = this.rowWidthList.get(i12).intValue();
            int intValue = this.rowHeightList.get(i12).intValue();
            int i13 = this.gravity;
            if (i13 == 2) {
                this.currentRowHeight = getPaddingTop() + ((this.availableHeight - intValue) / 2);
            } else if (i13 == 3 || i13 == 5) {
                this.currentRowHeight = getPaddingTop() + (this.availableHeight - intValue);
            } else {
                this.currentRowHeight = getPaddingTop();
            }
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                View childAt2 = getChildAt(it.next().intValue());
                LayoutParam layoutParam2 = (LayoutParam) childAt2.getLayoutParams();
                int measuredHeight = childAt2.getMeasuredHeight();
                layoutParam2.top = this.currentRowHeight + layoutParam2.topMargin + (getChildVerticalSpacing(childAt2) / 2);
                layoutParam2.left = layoutParam2.leftMargin + paddingLeft + (getChildHorizontalSpacing(childAt2) / 2);
                this.currentRowHeight += layoutParam2.topMargin + measuredHeight + layoutParam2.bottomMargin;
            }
            paddingLeft += this.currentRowWidth;
        }
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            size = getPaddingRight() + paddingLeft;
        }
        if (mode2 == 0 || mode2 == Integer.MIN_VALUE) {
            size2 = this.maxHeight + getPaddingLeft() + getPaddingBottom();
        }
        setMeasuredDimension(size, size2);
    }

    private void endRow(int extraSpacing) {
        measureWeightChildVertically(this.weightChildList, extraSpacing, this.totalWeight);
        Iterator<Integer> it = this.currentRowChildIndex.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += getChildAt(it.next().intValue()).getMeasuredHeight();
        }
        this.rowHeightList.add(Integer.valueOf(i));
        this.rowChildIndex.add(this.currentRowChildIndex);
        this.rowWidthList.add(Integer.valueOf(this.currentRowWidth));
        this.maxHeight = Math.max(this.currentRowHeight, this.maxHeight);
    }

    private void newRow() {
        this.currentRowChildIndex = new ArrayList();
        this.currentRowWidth = 0;
        this.currentRowHeight = getPaddingTop();
        this.totalWeight = 0.0f;
    }

    private void measureWeightChildVertically(List<Integer> weightChildList, int extraSpacing, float totalWeight) {
        while (weightChildList.size() > 0) {
            View childAt = getChildAt(weightChildList.get(0).intValue());
            LayoutParam layoutParam = (LayoutParam) childAt.getLayoutParams();
            int i = (int) ((layoutParam.weight / totalWeight) * extraSpacing);
            int i2 = layoutParam.width;
            if (layoutParam.topMargin + i + layoutParam.bottomMargin != 0) {
                this.currentRowWidth = Math.max(this.currentRowWidth, layoutParam.leftMargin + i2 + layoutParam.rightMargin);
            }
            childAt.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(i, 1073741824));
            weightChildList.remove(0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutChild(changed, l, t, r, b);
    }

    private void layoutChild(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                LayoutParam layoutParam = (LayoutParam) childAt.getLayoutParams();
                int i2 = layoutParam.left;
                int i3 = layoutParam.top;
                childAt.layout(i2, i3, childAt.getMeasuredWidth() + i2, childAt.getMeasuredHeight() + i3);
            }
        }
    }

    public void setGravity(int gravity) {
        boolean z = gravity == this.gravity;
        this.gravity = gravity;
        if (z) {
            requestLayout();
        }
    }

    private int getChildHorizontalSpacing(View child) {
        LayoutParam layoutParam = (LayoutParam) child.getLayoutParams();
        int i = this.horizontalSpacing;
        if (i == -1) {
            i = 0;
        }
        return layoutParam.horizontalSpacing == -1 ? i : layoutParam.horizontalSpacing;
    }

    private int getChildVerticalSpacing(View child) {
        LayoutParam layoutParam = (LayoutParam) child.getLayoutParams();
        int i = this.verticalSpacing;
        if (i == -1) {
            i = 0;
        }
        return layoutParam.verticalSpacing == -1 ? i : layoutParam.verticalSpacing;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParam generateDefaultLayoutParams() {
        return new LayoutParam(-1, -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParam generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParam(p);
    }

    @Override // android.view.ViewGroup
    public LayoutParam generateLayoutParams(AttributeSet attrs) {
        return new LayoutParam(getContext(), attrs);
    }

    public static class LayoutParam extends ViewGroup.MarginLayoutParams {
        public static final int LINE_NUM_INVALID = Integer.MIN_VALUE;
        public int horizontalSpacing;
        public int left;
        public int lineNum;
        public int top;
        public int verticalSpacing;
        public float weight;

        public LayoutParam(int width, int height) {
            super(width, height);
            this.weight = -1.0f;
            this.lineNum = Integer.MIN_VALUE;
            this.horizontalSpacing = 0;
            this.verticalSpacing = 0;
            this.left = -1;
            this.top = -1;
        }

        public LayoutParam(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.weight = -1.0f;
            this.lineNum = Integer.MIN_VALUE;
            this.horizontalSpacing = 0;
            this.verticalSpacing = 0;
            this.left = -1;
            this.top = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlowLayout, 0, 0);
            try {
                this.weight = obtainStyledAttributes.getInt(10, 0);
                this.lineNum = obtainStyledAttributes.getInt(6, Integer.MIN_VALUE);
                this.horizontalSpacing = obtainStyledAttributes.getDimensionPixelSize(0, -1);
                this.verticalSpacing = obtainStyledAttributes.getDimensionPixelSize(1, -1);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }

        public LayoutParam(ViewGroup.LayoutParams source) {
            super(source);
            this.weight = -1.0f;
            this.lineNum = Integer.MIN_VALUE;
            this.horizontalSpacing = 0;
            this.verticalSpacing = 0;
            this.left = -1;
            this.top = -1;
        }
    }
}
