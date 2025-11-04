package org.bouncycastle.util;

/* loaded from: classes4.dex */
public class Longs {
    public static final int BYTES = 8;
    public static final int SIZE = 64;

    public static long highestOneBit(long j) {
        return Long.highestOneBit(j);
    }

    public static long lowestOneBit(long j) {
        return Long.lowestOneBit(j);
    }

    public static int numberOfLeadingZeros(long j) {
        return Long.numberOfLeadingZeros(j);
    }

    public static int numberOfTrailingZeros(long j) {
        return Long.numberOfTrailingZeros(j);
    }

    public static long reverse(long j) {
        return Long.reverse(j);
    }

    public static long reverseBytes(long j) {
        return Long.reverseBytes(j);
    }

    public static long rotateLeft(long j, int i) {
        return Long.rotateLeft(j, i);
    }

    public static long rotateRight(long j, int i) {
        return Long.rotateRight(j, i);
    }

    public static Long valueOf(long j) {
        return Long.valueOf(j);
    }

    public static void xorTo(int i, long[] jArr, int i2, long[] jArr2, int i3) {
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i3 + i4;
            jArr2[i5] = jArr2[i5] ^ jArr[i2 + i4];
        }
    }
}
