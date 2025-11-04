package org.bouncycastle.pqc.crypto.mayo;

import kotlin.UByte;
import org.bouncycastle.util.GF16;

/* loaded from: classes4.dex */
class GF16Utils {
    static final long MASK_LSB = 1229782938247303441L;
    static final long MASK_MSB = -8608480567731124088L;
    static final long NIBBLE_MASK_LSB = -1229782938247303442L;
    static final long NIBBLE_MASK_MSB = 8608480567731124087L;

    GF16Utils() {
    }

    static void mVecMulAdd(int i, long[] jArr, int i2, int i3, long[] jArr2, int i4) {
        long j = i3;
        long j2 = 4294967295L & j;
        long j3 = j & 1;
        char c = 1;
        long j4 = (j2 >>> 1) & 1;
        long j5 = (j2 >>> 2) & 1;
        char c2 = 3;
        long j6 = (j2 >>> 3) & 1;
        int i5 = i4;
        int i6 = 0;
        int i7 = i2;
        while (i6 < i) {
            long j7 = jArr[i7];
            char c3 = c2;
            long j8 = (-j3) & j7;
            long j9 = (j7 & MASK_MSB) >>> c3;
            long j10 = ((j7 & NIBBLE_MASK_MSB) << c) ^ (j9 + (j9 << c));
            long j11 = j3;
            long j12 = ((-j4) & j10) ^ j8;
            long j13 = (j10 & MASK_MSB) >>> c3;
            long j14 = (j13 + (j13 << c)) ^ ((j10 & NIBBLE_MASK_MSB) << c);
            char c4 = c;
            long j15 = (j14 & MASK_MSB) >>> c3;
            jArr2[i5] = ((j12 ^ ((-j5) & j14)) ^ ((-j6) & ((j15 + (j15 << c4)) ^ ((j14 & NIBBLE_MASK_MSB) << c4)))) ^ jArr2[i5];
            i6++;
            c2 = c3;
            i5++;
            i7++;
            c = c4;
            j3 = j11;
            j4 = j4;
        }
    }

    static void matMul(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i3) {
            int i7 = 0;
            byte b = 0;
            while (i7 < i2) {
                b = (byte) (GF16.mul(bArr[i5], bArr2[i + i7]) ^ b);
                i7++;
                i5++;
            }
            bArr3[i6] = b;
            i4++;
            i6++;
        }
    }

    static void mulAddMUpperTriangularMatXMat(int i, long[] jArr, byte[] bArr, long[] jArr2, int i2, int i3, int i4) {
        int i5 = i4 * i;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i6 < i3) {
            int i10 = i7;
            int i11 = i9;
            int i12 = i6;
            while (i12 < i3) {
                int i13 = 0;
                int i14 = 0;
                while (i13 < i4) {
                    mVecMulAdd(i, jArr, i11, bArr[i10 + i13], jArr2, i2 + i8 + i14);
                    i13++;
                    i14 += i;
                }
                i11 += i;
                i12++;
                i10 += i4;
            }
            i6++;
            i7 += i4;
            i8 += i5;
            i9 = i11;
        }
    }

    static void mulAddMUpperTriangularMatXMatTrans(int i, long[] jArr, byte[] bArr, long[] jArr2, int i2, int i3) {
        int i4 = i * i3;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i2) {
            int i8 = i7;
            for (int i9 = i5; i9 < i2; i9++) {
                int i10 = 0;
                int i11 = 0;
                int i12 = 0;
                while (i10 < i3) {
                    mVecMulAdd(i, jArr, i8, bArr[i11 + i9], jArr2, i6 + i12);
                    i10++;
                    i11 += i2;
                    i12 += i;
                }
                i8 += i;
            }
            i5++;
            i6 += i4;
            i7 = i8;
        }
    }

    static void mulAddMatTransXMMat(int i, byte[] bArr, long[] jArr, int i2, long[] jArr2, int i3, int i4) {
        int i5 = i4 * i;
        int i6 = 0;
        int i7 = 0;
        while (i6 < i4) {
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i8 < i3) {
                byte b = bArr[i9 + i6];
                int i11 = 0;
                int i12 = 0;
                while (i11 < i4) {
                    mVecMulAdd(i, jArr, i2 + i10 + i12, b, jArr2, i7 + i12);
                    i11++;
                    i12 += i;
                }
                i8++;
                i9 += i4;
                i10 += i5;
            }
            i6++;
            i7 += i5;
        }
    }

    static void mulAddMatXMMat(int i, byte[] bArr, long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        int i6 = i * i5;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < i3) {
            int i10 = 0;
            int i11 = 0;
            while (i10 < i4) {
                byte b = bArr[i9 + i10];
                int i12 = 0;
                int i13 = 0;
                while (i12 < i5) {
                    mVecMulAdd(i, jArr, i11 + i13 + i2, b, jArr2, i8 + i13);
                    i12++;
                    i13 += i;
                }
                i10++;
                i11 += i6;
            }
            i7++;
            i8 += i6;
            i9 += i4;
        }
    }

    static void mulAddMatXMMat(int i, byte[] bArr, long[] jArr, long[] jArr2, int i2, int i3) {
        int i4 = i * i2;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i2) {
            int i8 = 0;
            int i9 = 0;
            while (i8 < i3) {
                byte b = bArr[i6 + i8];
                int i10 = 0;
                int i11 = 0;
                while (i10 < i2) {
                    mVecMulAdd(i, jArr, i9 + i11, b, jArr2, i7 + i11);
                    i10++;
                    i11 += i;
                }
                i8++;
                i9 += i4;
            }
            i5++;
            i6 += i3;
            i7 += i4;
        }
    }

    static long mulFx8(byte b, long j) {
        int i = b & UByte.MAX_VALUE;
        long j2 = ((j << 3) & (-((i >> 3) & 1))) ^ ((((-(b & 1)) & j) ^ ((-((i >> 1) & 1)) & (j << 1))) ^ ((-((i >> 2) & 1)) & (j << 2)));
        long j3 = (-1085102592571150096L) & j2;
        return ((j2 ^ (j3 >>> 4)) ^ (j3 >>> 3)) & 1085102592571150095L;
    }
}
