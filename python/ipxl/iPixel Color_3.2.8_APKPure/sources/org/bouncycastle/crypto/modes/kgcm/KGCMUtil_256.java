package org.bouncycastle.crypto.modes.kgcm;

import kotlin.text.Typography;
import org.bouncycastle.math.raw.Interleave;

/* loaded from: classes3.dex */
public class KGCMUtil_256 {
    public static final int SIZE = 4;

    public static void add(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr2[3] ^ jArr[3];
    }

    public static void copy(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    public static boolean equal(long[] jArr, long[] jArr2) {
        return ((jArr2[3] ^ jArr[3]) | (((jArr[0] ^ jArr2[0]) | (jArr[1] ^ jArr2[1])) | (jArr[2] ^ jArr2[2]))) == 0;
    }

    public static void multiply(long[] jArr, long[] jArr2, long[] jArr3) {
        boolean z;
        char c;
        long j;
        int i = 0;
        long j2 = jArr[0];
        boolean z2 = true;
        long j3 = jArr[1];
        char c2 = 2;
        long j4 = jArr[2];
        char c3 = 3;
        long j5 = jArr[3];
        long j6 = jArr2[0];
        long j7 = jArr2[1];
        long j8 = jArr2[2];
        long j9 = jArr2[3];
        long j10 = 0;
        long j11 = 0;
        long j12 = 0;
        long j13 = 0;
        long j14 = 0;
        while (true) {
            z = z2;
            c = c2;
            j = j4;
            if (i >= 64) {
                break;
            }
            long j15 = -(j2 & 1);
            j2 >>>= z ? 1L : 0L;
            j10 ^= j6 & j15;
            char c4 = c3;
            long j16 = -(j3 & 1);
            j3 >>>= z ? 1L : 0L;
            j11 = (j11 ^ (j7 & j15)) ^ (j6 & j16);
            j12 = (j12 ^ (j8 & j15)) ^ (j7 & j16);
            j13 = (j13 ^ (j9 & j15)) ^ (j8 & j16);
            j14 ^= j9 & j16;
            long j17 = j9 >> 63;
            j9 = (j9 << (z ? 1L : 0L)) | (j8 >>> 63);
            j8 = (j8 << (z ? 1L : 0L)) | (j7 >>> 63);
            j7 = (j6 >>> 63) | (j7 << (z ? 1L : 0L));
            j6 = (j6 << (z ? 1L : 0L)) ^ (j17 & 1061);
            i++;
            c3 = c4;
            z2 = z ? 1 : 0;
            c2 = c;
            j4 = j;
            j5 = j5;
        }
        char c5 = c3;
        long j18 = j5;
        char c6 = Typography.greater;
        long j19 = (((j9 >>> 62) ^ j6) ^ (j9 >>> 59)) ^ (j9 >>> 54);
        long j20 = ((j9 ^ (j9 << c)) ^ (j9 << 5)) ^ (j9 << 10);
        int i2 = 0;
        while (i2 < 64) {
            long j21 = -(j & 1);
            j >>>= z ? 1L : 0L;
            j10 ^= j20 & j21;
            char c7 = c6;
            long j22 = j19;
            long j23 = -(j18 & 1);
            j18 >>>= z ? 1L : 0L;
            long j24 = (j11 ^ (j19 & j21)) ^ (j20 & j23);
            j12 = (j12 ^ (j7 & j21)) ^ (j22 & j23);
            j13 = (j13 ^ (j8 & j21)) ^ (j7 & j23);
            j14 ^= j8 & j23;
            long j25 = j8 >> 63;
            j8 = (j8 << (z ? 1L : 0L)) | (j7 >>> 63);
            j7 = (j22 >>> 63) | (j7 << (z ? 1L : 0L));
            long j26 = (j22 << (z ? 1L : 0L)) | (j20 >>> 63);
            j20 = (j20 << (z ? 1L : 0L)) ^ (j25 & 1061);
            i2++;
            c6 = c7;
            j19 = j26;
            j11 = j24;
        }
        jArr3[0] = j10 ^ (((j14 ^ (j14 << c)) ^ (j14 << 5)) ^ (j14 << 10));
        jArr3[z ? 1 : 0] = j11 ^ (((j14 >>> c6) ^ (j14 >>> 59)) ^ (j14 >>> 54));
        jArr3[c] = j12;
        jArr3[c5] = j13;
    }

    public static void multiplyX(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        jArr2[0] = ((j4 >> 63) & 1061) ^ (j << 1);
        jArr2[1] = (j >>> 63) | (j2 << 1);
        jArr2[2] = (j3 << 1) | (j2 >>> 63);
        jArr2[3] = (j4 << 1) | (j3 >>> 63);
    }

    public static void multiplyX8(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = j4 >>> 56;
        jArr2[0] = ((((j << 8) ^ j5) ^ (j5 << 2)) ^ (j5 << 5)) ^ (j5 << 10);
        jArr2[1] = (j >>> 56) | (j2 << 8);
        jArr2[2] = (j3 << 8) | (j2 >>> 56);
        jArr2[3] = (j4 << 8) | (j3 >>> 56);
    }

    public static void one(long[] jArr) {
        jArr[0] = 1;
        jArr[1] = 0;
        jArr[2] = 0;
        jArr[3] = 0;
    }

    public static void square(long[] jArr, long[] jArr2) {
        int i = 8;
        long[] jArr3 = new long[8];
        for (int i2 = 0; i2 < 4; i2++) {
            Interleave.expand64To128(jArr[i2], jArr3, i2 << 1);
        }
        while (true) {
            int i3 = i - 1;
            if (i3 < 4) {
                copy(jArr3, jArr2);
                return;
            }
            long j = jArr3[i3];
            int i4 = i - 5;
            jArr3[i4] = jArr3[i4] ^ ((((j << 2) ^ j) ^ (j << 5)) ^ (j << 10));
            int i5 = i - 4;
            jArr3[i5] = ((j >>> 54) ^ ((j >>> 62) ^ (j >>> 59))) ^ jArr3[i5];
            i = i3;
        }
    }

    public static void x(long[] jArr) {
        jArr[0] = 2;
        jArr[1] = 0;
        jArr[2] = 0;
        jArr[3] = 0;
    }

    public static void zero(long[] jArr) {
        jArr[0] = 0;
        jArr[1] = 0;
        jArr[2] = 0;
        jArr[3] = 0;
    }
}
