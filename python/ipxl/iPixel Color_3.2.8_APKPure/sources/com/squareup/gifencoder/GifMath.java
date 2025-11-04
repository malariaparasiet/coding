package com.squareup.gifencoder;

/* loaded from: classes2.dex */
final class GifMath {
    static int roundUpToPowerOfTwo(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 1);
        int i4 = i3 | (i3 >> 2);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 8);
        return (i6 | (i6 >> 16)) + 1;
    }

    private GifMath() {
    }

    static boolean isPowerOfTwo(int i) {
        return Integer.bitCount(i) == 1;
    }
}
