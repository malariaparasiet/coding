package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.carousel.CarouselStrategy;
import com.google.android.material.carousel.KeylineState;

/* loaded from: classes2.dex */
public final class UncontainedCarouselStrategy extends CarouselStrategy {
    private static final float MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD = 0.85f;

    @Override // com.google.android.material.carousel.CarouselStrategy
    public KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view) {
        int containerWidth = carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight();
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f = layoutParams.topMargin + layoutParams.bottomMargin;
        float measuredHeight = view.getMeasuredHeight();
        if (carousel.isHorizontal()) {
            f = layoutParams.leftMargin + layoutParams.rightMargin;
            measuredHeight = view.getMeasuredWidth();
        }
        float f2 = measuredHeight;
        float f3 = f;
        float f4 = f2 + f3;
        float extraSmallSize = CarouselStrategyHelper.getExtraSmallSize(view.getContext()) + f3;
        float extraSmallSize2 = CarouselStrategyHelper.getExtraSmallSize(view.getContext()) + f3;
        int max = Math.max(1, (int) Math.floor(r1 / f4));
        float f5 = containerWidth - (max * f4);
        if (carousel.getCarouselAlignment() == 1) {
            float f6 = f5 / 2.0f;
            return createCenterAlignedKeylineState(containerWidth, f3, f4, max, Math.max(Math.min(3.0f * f6, f4), getSmallItemSizeMin() + f3), extraSmallSize2, f6);
        }
        int i = 1;
        if (f5 <= 0.0f) {
            i = 0;
        }
        return createLeftAlignedKeylineState(view.getContext(), f3, containerWidth, f4, max, calculateMediumChildSize(extraSmallSize, f4, f5), i, extraSmallSize2);
    }

    private float calculateMediumChildSize(float f, float f2, float f3) {
        float max = Math.max(1.5f * f3, f);
        float f4 = MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD * f2;
        if (max > f4) {
            max = Math.max(f4, f3 * 1.2f);
        }
        return Math.min(f2, max);
    }

    private KeylineState createCenterAlignedKeylineState(int i, float f, float f2, int i2, float f3, float f4, float f5) {
        float min = Math.min(f4, f2);
        float childMaskPercentage = getChildMaskPercentage(min, f2, f);
        float childMaskPercentage2 = getChildMaskPercentage(f3, f2, f);
        float f6 = f3 / 2.0f;
        float f7 = (f5 + 0.0f) - f6;
        float f8 = f7 + f6;
        float f9 = min / 2.0f;
        float f10 = (i2 * f2) + f8;
        KeylineState.Builder addKeylineRange = new KeylineState.Builder(f2, i).addAnchorKeyline((f7 - f6) - f9, childMaskPercentage, min).addKeyline(f7, childMaskPercentage2, f3, false).addKeylineRange((f2 / 2.0f) + f8, 0.0f, f2, i2, true);
        addKeylineRange.addKeyline(f6 + f10, childMaskPercentage2, f3, false);
        addKeylineRange.addAnchorKeyline(f10 + f3 + f9, childMaskPercentage, min);
        return addKeylineRange.build();
    }

    private KeylineState createLeftAlignedKeylineState(Context context, float f, int i, float f2, int i2, float f3, int i3, float f4) {
        float min = Math.min(f4, f2);
        float max = Math.max(min, 0.5f * f3);
        float childMaskPercentage = getChildMaskPercentage(max, f2, f);
        float childMaskPercentage2 = getChildMaskPercentage(min, f2, f);
        float childMaskPercentage3 = getChildMaskPercentage(f3, f2, f);
        float f5 = (i2 * f2) + 0.0f;
        KeylineState.Builder addKeylineRange = new KeylineState.Builder(f2, i).addAnchorKeyline(0.0f - (max / 2.0f), childMaskPercentage, max).addKeylineRange(f2 / 2.0f, 0.0f, f2, i2, true);
        if (i3 > 0) {
            float f6 = (f3 / 2.0f) + f5;
            f5 += f3;
            addKeylineRange.addKeyline(f6, childMaskPercentage3, f3, false);
        }
        addKeylineRange.addAnchorKeyline(f5 + (CarouselStrategyHelper.getExtraSmallSize(context) / 2.0f), childMaskPercentage2, min);
        return addKeylineRange.build();
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    CarouselStrategy.StrategyType getStrategyType() {
        return CarouselStrategy.StrategyType.UNCONTAINED;
    }
}
