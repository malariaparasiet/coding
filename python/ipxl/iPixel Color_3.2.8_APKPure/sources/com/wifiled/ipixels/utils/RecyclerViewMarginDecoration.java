package com.wifiled.ipixels.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wifiled.baselib.utils.ScreenUtil;

/* loaded from: classes3.dex */
public class RecyclerViewMarginDecoration extends RecyclerView.ItemDecoration {
    private boolean isPullUp;
    private int mMargin;
    private int mOrderPostion;
    int spanCount;
    int topPostion = -1;
    int childLayoutOuPosition = 0;
    int childLayoutJiPosition = 0;

    public RecyclerViewMarginDecoration(Context context, int spanCount, int margin) {
        this.mMargin = ScreenUtil.dp2px((Activity) context, margin);
        this.spanCount = spanCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if ((layoutManager instanceof GridLayoutManager) && ((GridLayoutManager) layoutManager).getSpanSizeLookup().getSpanSize(parent.getChildAdapterPosition(view)) == 1) {
            if (parent.getChildLayoutPosition(view) % this.spanCount == 0 && this.topPostion == -1) {
                this.topPostion = parent.getChildLayoutPosition(view);
            } else if (parent.getChildLayoutPosition(view) % this.spanCount != 0 && this.topPostion == -1) {
                this.topPostion = parent.getChildLayoutPosition(view);
            }
            this.isPullUp = parent.getChildLayoutPosition(view) > this.mOrderPostion;
            this.mOrderPostion = parent.getChildLayoutPosition(view);
            if (this.topPostion % this.spanCount == 0) {
                int childLayoutPosition = parent.getChildLayoutPosition(view);
                int i = this.spanCount;
                if (childLayoutPosition % i == 0) {
                    int i2 = this.mMargin;
                    outRect.set(i2, 0, i2 / 2, 0);
                    this.childLayoutOuPosition = parent.getChildLayoutPosition(view);
                    return;
                } else {
                    if (parent.getChildLayoutPosition(view) == (this.isPullUp ? (this.childLayoutOuPosition + i) - 1 : parent.getChildLayoutPosition(view))) {
                        int i3 = this.mMargin;
                        outRect.set(i3 / 2, 0, i3, 0);
                        return;
                    } else {
                        int i4 = this.mMargin;
                        outRect.set(i4 / 2, 0, i4 / 2, 0);
                        return;
                    }
                }
            }
            int childLayoutPosition2 = parent.getChildLayoutPosition(view);
            int i5 = this.spanCount;
            if (childLayoutPosition2 % i5 != 0) {
                int i6 = this.mMargin;
                outRect.set(i6, 0, i6 / 2, 0);
                this.childLayoutJiPosition = parent.getChildLayoutPosition(view);
            } else {
                if (parent.getChildLayoutPosition(view) == (this.isPullUp ? (this.childLayoutJiPosition + i5) - 1 : parent.getChildLayoutPosition(view))) {
                    int i7 = this.mMargin;
                    outRect.set(i7 / 2, 0, i7, 0);
                } else {
                    int i8 = this.mMargin;
                    outRect.set(i8 / 2, 0, i8 / 2, 0);
                }
            }
        }
    }
}
