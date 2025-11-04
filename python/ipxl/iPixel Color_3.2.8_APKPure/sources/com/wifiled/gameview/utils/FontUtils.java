package com.wifiled.gameview.utils;

import android.graphics.Typeface;
import com.wifiled.baselib.utils.ContextHolder;

/* loaded from: classes3.dex */
public class FontUtils {
    public static Typeface getTypeface() {
        return Typeface.createFromAsset(ContextHolder.getContext().getAssets(), "fonts/HeatonLED.ttf");
    }
}
