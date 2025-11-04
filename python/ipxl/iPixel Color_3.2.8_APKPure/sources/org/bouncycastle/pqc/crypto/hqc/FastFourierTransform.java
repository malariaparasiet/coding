package org.bouncycastle.pqc.crypto.hqc;

/* loaded from: classes4.dex */
class FastFourierTransform {
    FastFourierTransform() {
    }

    static void computeFFTBetas(int[] iArr, int i) {
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i2 >= i3) {
                return;
            }
            iArr[i2] = 1 << (i3 - i2);
            i2++;
        }
    }

    static void computeFFTRec(int[] iArr, int[] iArr2, int i, int i2, int i3, int[] iArr3, int i4, int i5) {
        int i6;
        int i7;
        int[] iArr4;
        int i8 = 1;
        int i9 = 1 << (i4 - 2);
        int i10 = i5 - 2;
        int i11 = 1 << i10;
        int[] iArr5 = new int[i9];
        int[] iArr6 = new int[i9];
        int[] iArr7 = new int[i10];
        int[] iArr8 = new int[i10];
        int[] iArr9 = new int[i11];
        int[] iArr10 = new int[i11];
        int[] iArr11 = new int[i11];
        int[] iArr12 = new int[(i5 - i4) + 1];
        if (i3 == 1) {
            for (int i12 = 0; i12 < i2; i12++) {
                iArr12[i12] = GFCalculator.mult(iArr3[i12], iArr2[1]);
            }
            iArr[0] = iArr2[0];
            for (int i13 = 0; i13 < i2; i13++) {
                for (int i14 = 0; i14 < i8; i14++) {
                    iArr[i8 + i14] = iArr[i14] ^ iArr12[i13];
                }
                i8 <<= 1;
            }
            return;
        }
        int i15 = i2 - 1;
        if (iArr3[i15] != 1) {
            int i16 = 1 << i3;
            i6 = 1;
            i7 = 0;
            int i17 = 1;
            while (true) {
                iArr4 = iArr8;
                if (i8 >= i16) {
                    break;
                }
                i17 = GFCalculator.mult(i17, iArr3[i15]);
                iArr2[i8] = GFCalculator.mult(i17, iArr2[i8]);
                i8++;
                iArr8 = iArr4;
            }
        } else {
            i6 = 1;
            i7 = 0;
            iArr4 = iArr8;
        }
        computeRadix(iArr5, iArr6, iArr2, i3, i4);
        for (int i18 = i7; i18 < i15; i18++) {
            int mult = GFCalculator.mult(iArr3[i18], GFCalculator.inverse(iArr3[i15]));
            iArr7[i18] = mult;
            iArr4[i18] = GFCalculator.mult(mult, mult) ^ iArr7[i18];
        }
        computeSubsetSum(iArr9, iArr7, i15);
        int i19 = i3 - 1;
        int[] iArr13 = iArr4;
        computeFFTRec(iArr10, iArr5, (i + 1) / 2, i15, i19, iArr13, i4, i5);
        int i20 = i6 << (i15 & 15);
        if (i <= 3) {
            iArr[i7] = iArr10[i7];
            iArr[i20] = iArr10[i7] ^ iArr6[i7];
            for (int i21 = i6; i21 < i20; i21++) {
                int mult2 = iArr10[i21] ^ GFCalculator.mult(iArr9[i21], iArr6[i7]);
                iArr[i21] = mult2;
                iArr[i20 + i21] = mult2 ^ iArr6[i7];
            }
            return;
        }
        computeFFTRec(iArr11, iArr6, i / 2, i15, i19, iArr13, i4, i5);
        int i22 = i7;
        System.arraycopy(iArr11, i22, iArr, i20, i20);
        iArr[i22] = iArr10[i22];
        iArr[i20] = iArr10[i22] ^ iArr[i20];
        for (int i23 = i6; i23 < i20; i23++) {
            int mult3 = iArr10[i23] ^ GFCalculator.mult(iArr9[i23], iArr11[i23]);
            iArr[i23] = mult3;
            int i24 = i20 + i23;
            iArr[i24] = mult3 ^ iArr[i24];
        }
    }

    static void computeRadix(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2) {
        if (i == 1) {
            iArr[0] = iArr3[0];
            iArr2[0] = iArr3[1];
            return;
        }
        if (i == 2) {
            iArr[0] = iArr3[0];
            int i3 = iArr3[2];
            int i4 = iArr3[3];
            int i5 = i3 ^ i4;
            iArr[1] = i5;
            iArr2[0] = i5 ^ iArr3[1];
            iArr2[1] = i4;
            return;
        }
        if (i == 3) {
            iArr[0] = iArr3[0];
            int i6 = iArr3[4];
            int i7 = iArr3[6];
            iArr[2] = i6 ^ i7;
            int i8 = iArr3[7];
            iArr[3] = i7 ^ i8;
            int i9 = iArr3[3];
            int i10 = iArr3[5];
            int i11 = (i9 ^ i10) ^ i8;
            iArr2[1] = i11;
            iArr2[2] = i10 ^ i7;
            iArr2[3] = i8;
            int i12 = (iArr3[2] ^ iArr[2]) ^ i11;
            iArr[1] = i12;
            iArr2[0] = i12 ^ iArr3[1];
            return;
        }
        if (i != 4) {
            computeRadixBig(iArr, iArr2, iArr3, i, i2);
            return;
        }
        int i13 = iArr3[8];
        int i14 = iArr3[12];
        iArr[4] = i13 ^ i14;
        int i15 = iArr3[14];
        iArr[6] = i14 ^ i15;
        int i16 = iArr3[15];
        iArr[7] = i15 ^ i16;
        int i17 = iArr3[11];
        int i18 = iArr3[13];
        int i19 = i17 ^ i18;
        iArr2[5] = i19;
        iArr2[6] = i18 ^ i15;
        iArr2[7] = i16;
        int i20 = iArr3[10];
        int i21 = (i14 ^ i20) ^ i19;
        iArr[5] = i21;
        int i22 = iArr3[9];
        iArr2[4] = i21 ^ (i22 ^ i18);
        iArr[0] = iArr3[0];
        int i23 = (iArr3[7] ^ i17) ^ i16;
        iArr2[3] = i23;
        int i24 = (i15 ^ (iArr3[6] ^ i20)) ^ i23;
        iArr[3] = i24;
        int i25 = (iArr3[4] ^ iArr[4]) ^ i24;
        int i26 = iArr2[3];
        iArr[2] = i25 ^ i26;
        int i27 = iArr3[3];
        int i28 = (((iArr3[5] ^ i27) ^ i22) ^ i18) ^ i26;
        iArr2[1] = i28;
        iArr2[2] = i24 ^ (i27 ^ i28);
        int i29 = (iArr3[2] ^ iArr[2]) ^ i28;
        iArr[1] = i29;
        iArr2[0] = i29 ^ iArr3[1];
    }

    static void computeRadixBig(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2) {
        int i3 = 1 << (i - 2);
        int i4 = 1 << (i2 - 2);
        int i5 = (i4 * 2) + 1;
        int[] iArr4 = new int[i5];
        int[] iArr5 = new int[i5];
        int[] iArr6 = new int[i4];
        int[] iArr7 = new int[i4];
        int[] iArr8 = new int[i4];
        int[] iArr9 = new int[i4];
        int i6 = i3 * 3;
        int i7 = i3 * 2;
        Utils.copyBytes(iArr3, i6, iArr4, 0, i7);
        Utils.copyBytes(iArr3, i6, iArr4, i3, i7);
        Utils.copyBytes(iArr3, 0, iArr5, 0, i3 * 4);
        for (int i8 = 0; i8 < i3; i8++) {
            int i9 = iArr4[i8] ^ iArr3[i7 + i8];
            iArr4[i8] = i9;
            int i10 = i3 + i8;
            iArr5[i10] = iArr5[i10] ^ i9;
        }
        int i11 = i - 1;
        computeRadix(iArr6, iArr7, iArr4, i11, i2);
        computeRadix(iArr8, iArr9, iArr5, i11, i2);
        Utils.copyBytes(iArr8, 0, iArr, 0, i7);
        Utils.copyBytes(iArr6, 0, iArr, i3, i7);
        Utils.copyBytes(iArr9, 0, iArr2, 0, i7);
        Utils.copyBytes(iArr7, 0, iArr2, i3, i7);
    }

    static void computeSubsetSum(int[] iArr, int[] iArr2, int i) {
        iArr[0] = 0;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = 0;
            while (true) {
                int i4 = 1 << i2;
                if (i3 < i4) {
                    iArr[i4 + i3] = iArr2[i2] ^ iArr[i3];
                    i3++;
                }
            }
        }
    }

    static void fastFourierTransform(int[] iArr, int[] iArr2, int i, int i2) {
        int i3 = 1 << i2;
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        int[] iArr5 = new int[7];
        int[] iArr6 = new int[128];
        int[] iArr7 = new int[128];
        int[] iArr8 = new int[7];
        int[] iArr9 = new int[128];
        computeFFTBetas(iArr8, 8);
        computeSubsetSum(iArr9, iArr8, 7);
        computeRadix(iArr3, iArr4, iArr2, i2, i2);
        for (int i4 = 0; i4 < 7; i4++) {
            int i5 = iArr8[i4];
            iArr5[i4] = GFCalculator.mult(i5, i5) ^ iArr8[i4];
        }
        int i6 = i2 - 1;
        computeFFTRec(iArr6, iArr3, (i + 1) / 2, 7, i6, iArr5, i2, 8);
        computeFFTRec(iArr7, iArr4, i / 2, 7, i6, iArr5, i2, 8);
        System.arraycopy(iArr7, 0, iArr, 128, 128);
        iArr[0] = iArr6[0];
        iArr[128] = iArr[128] ^ iArr6[0];
        for (int i7 = 1; i7 < 128; i7++) {
            int mult = iArr6[i7] ^ GFCalculator.mult(iArr9[i7], iArr7[i7]);
            iArr[i7] = mult;
            int i8 = 128 + i7;
            iArr[i8] = mult ^ iArr[i8];
        }
    }

    static void fastFourierTransformGetError(byte[] bArr, int[] iArr, int i, int[] iArr2) {
        int[] iArr3 = new int[7];
        int[] iArr4 = new int[i];
        computeFFTBetas(iArr3, 8);
        computeSubsetSum(iArr4, iArr3, 7);
        byte unsigned16Bits = (byte) (bArr[0] ^ (Utils.toUnsigned16Bits((-iArr[0]) >> 15) ^ 1));
        bArr[0] = unsigned16Bits;
        bArr[0] = (byte) (unsigned16Bits ^ (Utils.toUnsigned16Bits((-iArr[i]) >> 15) ^ 1));
        for (int i2 = 1; i2 < i; i2++) {
            int i3 = 255 - iArr2[iArr4[i2]];
            bArr[i3] = (byte) (bArr[i3] ^ (Math.abs((-iArr[i2]) >> 15) ^ 1));
            int i4 = 255 - iArr2[iArr4[i2] ^ 1];
            bArr[i4] = (byte) (bArr[i4] ^ (Math.abs((-iArr[i + i2]) >> 15) ^ 1));
        }
    }
}
