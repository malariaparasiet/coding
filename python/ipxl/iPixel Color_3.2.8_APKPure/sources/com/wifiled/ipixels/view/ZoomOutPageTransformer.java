package com.wifiled.ipixels.view;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes3.dex */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    public static final float MAX_SCALE = 1.0f;
    public static float MIN_ALPHA = 1.0f;
    public static final float MIN_SCALE = 0.7f;

    public ZoomOutPageTransformer() {
    }

    public ZoomOutPageTransformer(float MIN_ALPHA2) {
        MIN_ALPHA = MIN_ALPHA2;
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float position) {
        if (position < -1.0f) {
            view.setScaleX(0.7f);
            view.setScaleY(0.7f);
            view.setAlpha(MIN_ALPHA);
        } else {
            if (position < 1.0f) {
                float abs = ((1.0f - Math.abs(position)) * 0.3f) + 0.7f;
                if (position > 0.0f) {
                    view.setTranslationX(-abs);
                } else if (position < 0.0f) {
                    view.setTranslationX(abs);
                }
                view.setScaleY(abs);
                view.setScaleX(abs);
                float f = MIN_ALPHA;
                view.setAlpha(f + ((1.0f - f) * (1.0f - Math.abs(position))));
                return;
            }
            view.setScaleX(0.7f);
            view.setScaleY(0.7f);
            view.setAlpha(MIN_ALPHA);
        }
    }
}
