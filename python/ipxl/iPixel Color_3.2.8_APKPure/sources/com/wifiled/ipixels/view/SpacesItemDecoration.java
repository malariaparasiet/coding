package com.wifiled.ipixels.view;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes3.dex */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int thumbnailsCount;

    public SpacesItemDecoration(int space, int thumbnailsCount) {
        this.space = space;
        this.thumbnailsCount = thumbnailsCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition == 0) {
            outRect.left = this.space;
            outRect.right = 0;
            return;
        }
        int i = this.thumbnailsCount;
        if (i > 10 && childAdapterPosition == i - 1) {
            outRect.left = 0;
            outRect.right = this.space;
        } else {
            outRect.left = 0;
            outRect.right = 0;
        }
    }
}
