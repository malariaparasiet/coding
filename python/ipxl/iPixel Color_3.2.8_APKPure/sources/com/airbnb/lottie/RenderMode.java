package com.airbnb.lottie;

/* loaded from: classes2.dex */
public enum RenderMode {
    AUTOMATIC,
    HARDWARE,
    SOFTWARE;

    public boolean useSoftwareRendering(int i, boolean z, int i2) {
        int ordinal = ordinal();
        if (ordinal == 1) {
            return false;
        }
        if (ordinal != 2) {
            return (z && i < 28) || i2 > 4 || i <= 25;
        }
        return true;
    }
}
