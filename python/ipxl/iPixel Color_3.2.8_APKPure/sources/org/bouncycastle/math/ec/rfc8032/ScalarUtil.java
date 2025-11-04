package org.bouncycastle.math.ec.rfc8032;

import org.bouncycastle.util.Integers;

/* loaded from: classes4.dex */
abstract class ScalarUtil {
    private static final long M = 4294967295L;

    ScalarUtil() {
    }

    static void addShifted_NP(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i3 = i;
        int[] iArr5 = iArr3;
        char c = ' ';
        int i4 = 0;
        long j = M;
        long j2 = 0;
        if (i2 == 0) {
            long j3 = 0;
            while (i4 <= i3) {
                int i5 = iArr5[i4];
                long j4 = j2 + (iArr[i4] & M);
                long j5 = i5 & M;
                long j6 = j3 + j5 + (iArr2[i4] & M);
                int i6 = (int) j6;
                j3 = j6 >>> 32;
                iArr5[i4] = i6;
                long j7 = j4 + j5 + (i6 & M);
                iArr[i4] = (int) j7;
                j2 = j7 >>> 32;
                i4++;
            }
            return;
        }
        if (i2 < 32) {
            int i7 = 0;
            long j8 = 0;
            long j9 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i4 <= i3) {
                int i10 = iArr5[i4];
                char c2 = c;
                int i11 = -i2;
                long j10 = j;
                long j11 = j8 + (iArr[i4] & j10) + (((i7 >>> i11) | (i10 << i2)) & j10);
                int i12 = iArr2[i4];
                long j12 = j9 + (i10 & j10) + (((i12 << i2) | (i8 >>> i11)) & j10);
                int i13 = (int) j12;
                j9 = j12 >>> c2;
                iArr5[i4] = i13;
                long j13 = j11 + (((i9 >>> i11) | (i13 << i2)) & j10);
                iArr[i4] = (int) j13;
                j8 = j13 >>> c2;
                i4++;
                i8 = i12;
                i9 = i13;
                i7 = i10;
                c = c2;
                j = j10;
            }
            return;
        }
        System.arraycopy(iArr5, 0, iArr4, 0, i3);
        int i14 = i2 >>> 5;
        int i15 = i2 & 31;
        if (i15 == 0) {
            long j14 = 0;
            for (int i16 = i14; i16 <= i3; i16++) {
                int i17 = i16 - i14;
                long j15 = j2 + (iArr[i16] & M) + (iArr4[i17] & M);
                long j16 = j14 + (iArr5[i16] & M) + (iArr2[i17] & M);
                iArr5[i16] = (int) j16;
                j14 = j16 >>> 32;
                long j17 = j15 + (iArr5[i17] & M);
                iArr[i16] = (int) j17;
                j2 = j17 >>> 32;
            }
            return;
        }
        int i18 = i14;
        int i19 = 0;
        int i20 = 0;
        long j18 = 0;
        while (i18 <= i3) {
            int i21 = i18 - i14;
            int i22 = iArr4[i21];
            int i23 = -i15;
            long j19 = j2 + (iArr[i18] & M) + (((i4 >>> i23) | (i22 << i15)) & M);
            int i24 = iArr2[i21];
            long j20 = j18 + (iArr5[i18] & M) + (((i24 << r21) | (i19 >>> i23)) & M);
            iArr3[i18] = (int) j20;
            j18 = j20 >>> 32;
            int i25 = iArr3[i21];
            long j21 = j19 + (((i25 << r21) | (i20 >>> i23)) & M);
            iArr[i18] = (int) j21;
            j2 = j21 >>> 32;
            i18++;
            i15 = i15;
            iArr5 = iArr3;
            i20 = i25;
            i19 = i24;
            i4 = i22;
            i3 = i;
        }
    }

    static void addShifted_UV(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i3 = i2 >>> 5;
        int i4 = i2 & 31;
        char c = ' ';
        long j = M;
        long j2 = 0;
        if (i4 == 0) {
            long j3 = 0;
            for (int i5 = i3; i5 <= i; i5++) {
                long j4 = j2 + (iArr[i5] & M);
                long j5 = j3 + (iArr2[i5] & M);
                int i6 = i5 - i3;
                long j6 = j4 + (iArr3[i6] & M);
                long j7 = j5 + (iArr4[i6] & M);
                iArr[i5] = (int) j6;
                j2 = j6 >>> 32;
                iArr2[i5] = (int) j7;
                j3 = j7 >>> 32;
            }
            return;
        }
        int i7 = i3;
        int i8 = 0;
        int i9 = 0;
        long j8 = 0;
        while (i7 <= i) {
            int i10 = i7 - i3;
            int i11 = iArr3[i10];
            int i12 = iArr4[i10];
            char c2 = c;
            long j9 = j;
            long j10 = j2 + (iArr[i7] & j9);
            long j11 = j10 + (((i8 >>> (-i4)) | (i11 << i4)) & j9);
            long j12 = j8 + (iArr2[i7] & j9) + (((i9 >>> r3) | (i12 << i4)) & j9);
            iArr[i7] = (int) j11;
            j2 = j11 >>> c2;
            iArr2[i7] = (int) j12;
            j8 = j12 >>> c2;
            i7++;
            c = c2;
            i9 = i12;
            i8 = i11;
            j = j9;
        }
    }

    static int getBitLength(int i, int[] iArr) {
        int i2 = iArr[i] >> 31;
        while (i > 0 && iArr[i] == i2) {
            i--;
        }
        return ((i * 32) + 32) - Integers.numberOfLeadingZeros(iArr[i] ^ i2);
    }

    static int getBitLengthPositive(int i, int[] iArr) {
        while (i > 0 && iArr[i] == 0) {
            i--;
        }
        return ((i * 32) + 32) - Integers.numberOfLeadingZeros(iArr[i]);
    }

    static boolean lessThan(int i, int[] iArr, int[] iArr2) {
        do {
            int i2 = iArr[i] - 2147483648;
            int i3 = iArr2[i] - 2147483648;
            if (i2 < i3) {
                return true;
            }
            if (i2 > i3) {
                return false;
            }
            i--;
        } while (i >= 0);
        return false;
    }

    static void subShifted_NP(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i3 = i;
        int[] iArr5 = iArr3;
        char c = ' ';
        int i4 = 0;
        long j = M;
        long j2 = 0;
        if (i2 == 0) {
            long j3 = 0;
            while (i4 <= i3) {
                int i5 = iArr5[i4];
                long j4 = j2 + (iArr[i4] & M);
                long j5 = i5 & M;
                long j6 = (j3 + j5) - (iArr2[i4] & M);
                int i6 = (int) j6;
                j3 = j6 >> 32;
                iArr5[i4] = i6;
                long j7 = (j4 - j5) - (i6 & M);
                iArr[i4] = (int) j7;
                j2 = j7 >> 32;
                i4++;
            }
            return;
        }
        if (i2 < 32) {
            int i7 = 0;
            long j8 = 0;
            long j9 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i4 <= i3) {
                int i10 = iArr5[i4];
                char c2 = c;
                int i11 = -i2;
                long j10 = j;
                long j11 = (j8 + (iArr[i4] & j10)) - (((i7 >>> i11) | (i10 << i2)) & j10);
                int i12 = iArr2[i4];
                long j12 = (j9 + (i10 & j10)) - (((i12 << i2) | (i8 >>> i11)) & j10);
                int i13 = (int) j12;
                j9 = j12 >> c2;
                iArr5[i4] = i13;
                long j13 = j11 - (((i9 >>> i11) | (i13 << i2)) & j10);
                iArr[i4] = (int) j13;
                j8 = j13 >> c2;
                i4++;
                i8 = i12;
                i9 = i13;
                i7 = i10;
                c = c2;
                j = j10;
            }
            return;
        }
        System.arraycopy(iArr5, 0, iArr4, 0, i3);
        int i14 = i2 >>> 5;
        int i15 = i2 & 31;
        if (i15 == 0) {
            long j14 = 0;
            for (int i16 = i14; i16 <= i3; i16++) {
                int i17 = i16 - i14;
                long j15 = (j2 + (iArr[i16] & M)) - (iArr4[i17] & M);
                long j16 = (j14 + (iArr5[i16] & M)) - (iArr2[i17] & M);
                iArr5[i16] = (int) j16;
                j14 = j16 >> 32;
                long j17 = j15 - (iArr5[i17] & M);
                iArr[i16] = (int) j17;
                j2 = j17 >> 32;
            }
            return;
        }
        int i18 = i14;
        int i19 = 0;
        int i20 = 0;
        long j18 = 0;
        while (i18 <= i3) {
            int i21 = i18 - i14;
            int i22 = iArr4[i21];
            int i23 = -i15;
            long j19 = (j2 + (iArr[i18] & M)) - (((i4 >>> i23) | (i22 << i15)) & M);
            int i24 = iArr2[i21];
            long j20 = (j18 + (iArr5[i18] & M)) - (((i24 << r21) | (i19 >>> i23)) & M);
            iArr3[i18] = (int) j20;
            j18 = j20 >> 32;
            int i25 = iArr3[i21];
            long j21 = j19 - (((i25 << r21) | (i20 >>> i23)) & M);
            iArr[i18] = (int) j21;
            j2 = j21 >> 32;
            i18++;
            i15 = i15;
            iArr5 = iArr3;
            i20 = i25;
            i19 = i24;
            i4 = i22;
            i3 = i;
        }
    }

    static void subShifted_UV(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i3 = i2 >>> 5;
        int i4 = i2 & 31;
        char c = ' ';
        long j = M;
        long j2 = 0;
        if (i4 == 0) {
            long j3 = 0;
            for (int i5 = i3; i5 <= i; i5++) {
                long j4 = j2 + (iArr[i5] & M);
                long j5 = j3 + (iArr2[i5] & M);
                int i6 = i5 - i3;
                long j6 = j4 - (iArr3[i6] & M);
                long j7 = j5 - (iArr4[i6] & M);
                iArr[i5] = (int) j6;
                j2 = j6 >> 32;
                iArr2[i5] = (int) j7;
                j3 = j7 >> 32;
            }
            return;
        }
        int i7 = i3;
        int i8 = 0;
        int i9 = 0;
        long j8 = 0;
        while (i7 <= i) {
            int i10 = i7 - i3;
            int i11 = iArr3[i10];
            int i12 = iArr4[i10];
            char c2 = c;
            long j9 = j;
            long j10 = j2 + (iArr[i7] & j9);
            long j11 = j10 - (((i8 >>> (-i4)) | (i11 << i4)) & j9);
            long j12 = (j8 + (iArr2[i7] & j9)) - (((i9 >>> r3) | (i12 << i4)) & j9);
            iArr[i7] = (int) j11;
            j2 = j11 >> c2;
            iArr2[i7] = (int) j12;
            j8 = j12 >> c2;
            i7++;
            c = c2;
            i9 = i12;
            i8 = i11;
            j = j9;
        }
    }
}
