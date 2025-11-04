package org.bouncycastle.pqc.crypto.falcon;

/* loaded from: classes4.dex */
class FalconFFT {
    FalconFFT() {
    }

    static void FFT(double[] dArr, int i, int i2) {
        int i3 = (1 << i2) >> 1;
        int i4 = 2;
        int i5 = 1;
        int i6 = i3;
        while (i5 < i2) {
            int i7 = i6 >> 1;
            int i8 = i4 >> 1;
            int i9 = 0;
            int i10 = 0;
            while (i9 < i8) {
                int i11 = i10 + i7 + i;
                int i12 = (i4 + i9) << 1;
                double d = FPREngine.fpr_gm_tab[i12];
                double d2 = FPREngine.fpr_gm_tab[i12 + 1];
                int i13 = i + i10;
                int i14 = i13 + i3;
                int i15 = i13 + i7;
                int i16 = i15 + i3;
                while (i13 < i11) {
                    double d3 = dArr[i13];
                    double d4 = dArr[i14];
                    double d5 = dArr[i15];
                    double d6 = dArr[i16];
                    double d7 = (d5 * d) - (d6 * d2);
                    double d8 = (d5 * d2) + (d6 * d);
                    dArr[i13] = d3 + d7;
                    dArr[i14] = d4 + d8;
                    dArr[i15] = d3 - d7;
                    dArr[i16] = d4 - d8;
                    i13++;
                    i14++;
                    i15++;
                    i16++;
                }
                i9++;
                i10 += i6;
            }
            i5++;
            i4 <<= 1;
            i6 = i7;
        }
    }

    static void iFFT(double[] dArr, int i, int i2) {
        int i3;
        int i4 = 1 << i2;
        int i5 = i4 >> 1;
        int i6 = i2;
        int i7 = 1;
        int i8 = i4;
        while (true) {
            i3 = 0;
            if (i6 <= 1) {
                break;
            }
            i8 >>= 1;
            int i9 = i7 << 1;
            int i10 = 0;
            while (i3 < i5) {
                int i11 = i3 + i7 + i;
                int i12 = (i8 + i10) << 1;
                double d = FPREngine.fpr_gm_tab[i12];
                double d2 = -FPREngine.fpr_gm_tab[i12 + 1];
                int i13 = i + i3;
                int i14 = i13 + i5;
                int i15 = i13 + i7;
                int i16 = i15 + i5;
                while (i13 < i11) {
                    double d3 = dArr[i13];
                    double d4 = dArr[i14];
                    double d5 = dArr[i15];
                    double d6 = dArr[i16];
                    dArr[i13] = d3 + d5;
                    dArr[i14] = d4 + d6;
                    double d7 = d3 - d5;
                    double d8 = d4 - d6;
                    dArr[i15] = (d7 * d) - (d8 * d2);
                    dArr[i16] = (d7 * d2) + (d8 * d);
                    i13++;
                    i14++;
                    i15++;
                    i16++;
                }
                i10++;
                i3 += i9;
            }
            i6--;
            i7 = i9;
        }
        if (i2 > 0) {
            double d9 = FPREngine.fpr_p2_tab[i2];
            while (i3 < i4) {
                int i17 = i + i3;
                dArr[i17] = dArr[i17] * d9;
                i3++;
            }
        }
    }

    static void poly_LDL_fft(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3, int i4) {
        int i5 = (1 << i4) >> 1;
        int i6 = i5;
        int i7 = 0;
        int i8 = i2 + i5;
        int i9 = i2;
        while (i7 < i5) {
            double d = dArr[i + i7];
            double d2 = dArr[i + i6];
            double d3 = dArr2[i9];
            double d4 = dArr2[i8];
            double d5 = 1.0d / ((d * d) + (d2 * d2));
            double d6 = d * d5;
            double d7 = d5 * (-d2);
            double d8 = (d3 * d6) - (d4 * d7);
            double d9 = (d7 * d3) + (d6 * d4);
            double d10 = (d8 * d3) + (d9 * d4);
            double d11 = ((-d4) * d8) + (d3 * d9);
            int i10 = i3 + i7;
            dArr3[i10] = dArr3[i10] - d10;
            int i11 = i3 + i6;
            dArr3[i11] = dArr3[i11] - d11;
            dArr2[i9] = d8;
            dArr2[i8] = -d9;
            i7++;
            i6++;
            i9++;
            i8++;
        }
    }

    static void poly_add(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        int i4 = 1 << i3;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            dArr[i6] = dArr[i6] + dArr2[i2 + i5];
        }
    }

    static void poly_add_muladj_fft(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5, int i) {
        int i2 = (1 << i) >> 1;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 + i2;
            double d = dArr2[i3];
            double d2 = dArr2[i4];
            double d3 = dArr3[i3];
            double d4 = dArr3[i4];
            double d5 = dArr4[i3];
            double d6 = dArr4[i4];
            double d7 = dArr5[i3];
            double d8 = dArr5[i4];
            double d9 = (d * d5) + (d2 * d6);
            double d10 = (d2 * d5) - (d * d6);
            dArr[i3] = d9 + (d3 * d7) + (d4 * d8);
            dArr[i4] = d10 + ((d4 * d7) - (d3 * d8));
        }
    }

    static void poly_adj_fft(double[] dArr, int i, int i2) {
        int i3 = 1 << i2;
        for (int i4 = i3 >> 1; i4 < i3; i4++) {
            int i5 = i + i4;
            dArr[i5] = -dArr[i5];
        }
    }

    static void poly_div_autoadj_fft(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        int i4 = (1 << i3) >> 1;
        for (int i5 = 0; i5 < i4; i5++) {
            double d = 1.0d / dArr2[i2 + i5];
            int i6 = i + i5;
            dArr[i6] = dArr[i6] * d;
            int i7 = i6 + i4;
            dArr[i7] = dArr[i7] * d;
        }
    }

    static void poly_invnorm2_fft(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3, int i4) {
        int i5 = (1 << i4) >> 1;
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = i2 + i6;
            double d = dArr2[i7];
            double d2 = dArr2[i7 + i5];
            int i8 = i3 + i6;
            double d3 = dArr3[i8];
            double d4 = dArr3[i8 + i5];
            dArr[i + i6] = 1.0d / ((((d * d) + (d2 * d2)) + (d3 * d3)) + (d4 * d4));
        }
    }

    static void poly_merge_fft(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3, int i4) {
        int i5 = 1 << i4;
        int i6 = i5 >> 1;
        int i7 = i5 >> 2;
        dArr[i] = dArr2[i2];
        dArr[i + i6] = dArr3[i3];
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i3 + i8;
            double d = dArr3[i9];
            double d2 = dArr3[i9 + i7];
            int i10 = (i8 + i6) << 1;
            double d3 = FPREngine.fpr_gm_tab[i10];
            double d4 = FPREngine.fpr_gm_tab[i10 + 1];
            double d5 = (d * d3) - (d2 * d4);
            double d6 = (d * d4) + (d2 * d3);
            int i11 = i2 + i8;
            double d7 = dArr2[i11];
            double d8 = dArr2[i11 + i7];
            int i12 = i + (i8 << 1);
            dArr[i12] = d7 + d5;
            int i13 = i12 + 1;
            dArr[i12 + i6] = d8 + d6;
            dArr[i13] = d7 - d5;
            dArr[i13 + i6] = d8 - d6;
        }
    }

    static void poly_mul_autoadj_fft(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        int i4 = (1 << i3) >> 1;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            int i7 = i2 + i5;
            dArr[i6] = dArr[i6] * dArr2[i7];
            int i8 = i6 + i4;
            dArr[i8] = dArr[i8] * dArr2[i7];
        }
    }

    static void poly_mul_fft(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        int i4 = (1 << i3) >> 1;
        int i5 = i + i4;
        int i6 = 0;
        int i7 = i;
        int i8 = i2;
        while (i6 < i4) {
            double d = dArr[i7];
            double d2 = dArr[i5];
            double d3 = dArr2[i8];
            double d4 = dArr2[i8 + i4];
            dArr[i7] = (d * d3) - (d2 * d4);
            dArr[i5] = (d * d4) + (d2 * d3);
            i6++;
            i7++;
            i8++;
            i5++;
        }
    }

    static void poly_muladj_fft(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        int i4 = (1 << i3) >> 1;
        int i5 = 0;
        int i6 = i;
        while (i5 < i4) {
            double d = dArr[i6];
            int i7 = i6 + i4;
            double d2 = dArr[i7];
            int i8 = i2 + i5;
            double d3 = dArr2[i8];
            double d4 = dArr2[i8 + i4];
            dArr[i6] = (d * d3) + (d2 * d4);
            dArr[i7] = (d2 * d3) - (d * d4);
            i5++;
            i6++;
        }
    }

    static void poly_mulconst(double[] dArr, int i, double d, int i2) {
        int i3 = 1 << i2;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            dArr[i5] = dArr[i5] * d;
        }
    }

    static void poly_mulselfadj_fft(double[] dArr, int i, int i2) {
        int i3 = (1 << i2) >> 1;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            double d = dArr[i5];
            int i6 = i5 + i3;
            double d2 = dArr[i6];
            dArr[i5] = (d * d) + (d2 * d2);
            dArr[i6] = 0.0d;
        }
    }

    static void poly_neg(double[] dArr, int i, int i2) {
        int i3 = 1 << i2;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            dArr[i5] = -dArr[i5];
        }
    }

    static void poly_split_fft(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3, int i4) {
        int i5 = 1 << i4;
        int i6 = i5 >> 1;
        int i7 = i5 >> 2;
        dArr[i] = dArr3[i3];
        dArr2[i2] = dArr3[i3 + i6];
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i3 + (i8 << 1);
            double d = dArr3[i9];
            int i10 = i9 + 1;
            double d2 = dArr3[i9 + i6];
            double d3 = dArr3[i10];
            double d4 = dArr3[i10 + i6];
            int i11 = i + i8;
            dArr[i11] = (d + d3) * 0.5d;
            dArr[i11 + i7] = (d2 + d4) * 0.5d;
            double d5 = d - d3;
            double d6 = d2 - d4;
            int i12 = (i8 + i6) << 1;
            double d7 = FPREngine.fpr_gm_tab[i12];
            double d8 = -FPREngine.fpr_gm_tab[i12 + 1];
            int i13 = i2 + i8;
            dArr2[i13] = ((d5 * d7) - (d6 * d8)) * 0.5d;
            dArr2[i13 + i7] = ((d5 * d8) + (d6 * d7)) * 0.5d;
        }
    }

    static void poly_sub(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        int i4 = 1 << i3;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            dArr[i6] = dArr[i6] - dArr2[i2 + i5];
        }
    }
}
