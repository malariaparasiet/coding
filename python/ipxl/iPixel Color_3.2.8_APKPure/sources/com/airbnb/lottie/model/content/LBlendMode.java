package com.airbnb.lottie.model.content;

import androidx.core.graphics.BlendModeCompat;

/* loaded from: classes2.dex */
public enum LBlendMode {
    NORMAL,
    MULTIPLY,
    SCREEN,
    OVERLAY,
    DARKEN,
    LIGHTEN,
    COLOR_DODGE,
    COLOR_BURN,
    HARD_LIGHT,
    SOFT_LIGHT,
    DIFFERENCE,
    EXCLUSION,
    HUE,
    SATURATION,
    COLOR,
    LUMINOSITY,
    ADD,
    HARD_MIX;

    public BlendModeCompat toNativeBlendMode() {
        int ordinal = ordinal();
        if (ordinal == 1) {
            return BlendModeCompat.MODULATE;
        }
        if (ordinal == 2) {
            return BlendModeCompat.SCREEN;
        }
        if (ordinal == 3) {
            return BlendModeCompat.OVERLAY;
        }
        if (ordinal == 4) {
            return BlendModeCompat.DARKEN;
        }
        if (ordinal == 5) {
            return BlendModeCompat.LIGHTEN;
        }
        if (ordinal != 16) {
            return null;
        }
        return BlendModeCompat.PLUS;
    }
}
