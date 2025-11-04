package org.bouncycastle.pqc.crypto.falcon;

import org.bouncycastle.crypto.digests.SHAKEDigest;

/* loaded from: classes4.dex */
class FalconSign {
    FalconSign() {
    }

    int do_sign_dyn(SamplerCtx samplerCtx, short[] sArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, short[] sArr2, int i, double[] dArr, int i2) {
        int i3 = 1 << i;
        int i4 = i2 + i3;
        int i5 = i4 + i3;
        int i6 = i5 + i3;
        smallints_to_fpr(dArr, i4, bArr, i);
        smallints_to_fpr(dArr, i2, bArr2, i);
        smallints_to_fpr(dArr, i6, bArr3, i);
        smallints_to_fpr(dArr, i5, bArr4, i);
        FalconFFT.FFT(dArr, i4, i);
        FalconFFT.FFT(dArr, i2, i);
        FalconFFT.FFT(dArr, i6, i);
        FalconFFT.FFT(dArr, i5, i);
        FalconFFT.poly_neg(dArr, i4, i);
        FalconFFT.poly_neg(dArr, i6, i);
        int i7 = i6 + i3;
        int i8 = i7 + i3;
        System.arraycopy(dArr, i4, dArr, i7, i3);
        FalconFFT.poly_mulselfadj_fft(dArr, i7, i);
        System.arraycopy(dArr, i2, dArr, i8, i3);
        FalconFFT.poly_muladj_fft(dArr, i8, dArr, i5, i);
        FalconFFT.poly_mulselfadj_fft(dArr, i2, i);
        FalconFFT.poly_add(dArr, i2, dArr, i7, i);
        System.arraycopy(dArr, i4, dArr, i7, i3);
        FalconFFT.poly_muladj_fft(dArr, i4, dArr, i6, i);
        FalconFFT.poly_add(dArr, i4, dArr, i8, i);
        FalconFFT.poly_mulselfadj_fft(dArr, i5, i);
        System.arraycopy(dArr, i6, dArr, i8, i3);
        FalconFFT.poly_mulselfadj_fft(dArr, i8, i);
        FalconFFT.poly_add(dArr, i5, dArr, i8, i);
        int i9 = i8 + i3;
        for (int i10 = 0; i10 < i3; i10++) {
            dArr[i8 + i10] = sArr2[i10];
        }
        FalconFFT.FFT(dArr, i8, i);
        System.arraycopy(dArr, i8, dArr, i9, i3);
        FalconFFT.poly_mul_fft(dArr, i9, dArr, i7, i);
        FalconFFT.poly_mulconst(dArr, i9, -8.137358613394092E-5d, i);
        FalconFFT.poly_mul_fft(dArr, i8, dArr, i6, i);
        FalconFFT.poly_mulconst(dArr, i8, 8.137358613394092E-5d, i);
        int i11 = i3 * 2;
        System.arraycopy(dArr, i8, dArr, i6, i11);
        ffSampling_fft_dyntree(samplerCtx, dArr, i6, dArr, i7, dArr, i2, dArr, i4, dArr, i5, i, i, dArr, i8);
        System.arraycopy(dArr, i6, dArr, i7, i11);
        smallints_to_fpr(dArr, i4, bArr, i);
        smallints_to_fpr(dArr, i2, bArr2, i);
        smallints_to_fpr(dArr, i6, bArr3, i);
        smallints_to_fpr(dArr, i5, bArr4, i);
        FalconFFT.FFT(dArr, i4, i);
        FalconFFT.FFT(dArr, i2, i);
        FalconFFT.FFT(dArr, i6, i);
        FalconFFT.FFT(dArr, i5, i);
        FalconFFT.poly_neg(dArr, i4, i);
        FalconFFT.poly_neg(dArr, i6, i);
        int i12 = i9 + i3;
        System.arraycopy(dArr, i7, dArr, i9, i3);
        System.arraycopy(dArr, i8, dArr, i12, i3);
        FalconFFT.poly_mul_fft(dArr, i9, dArr, i2, i);
        FalconFFT.poly_mul_fft(dArr, i12, dArr, i5, i);
        FalconFFT.poly_add(dArr, i9, dArr, i12, i);
        System.arraycopy(dArr, i7, dArr, i12, i3);
        FalconFFT.poly_mul_fft(dArr, i12, dArr, i4, i);
        System.arraycopy(dArr, i9, dArr, i7, i3);
        FalconFFT.poly_mul_fft(dArr, i8, dArr, i6, i);
        FalconFFT.poly_add(dArr, i8, dArr, i12, i);
        FalconFFT.iFFT(dArr, i7, i);
        FalconFFT.iFFT(dArr, i8, i);
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < i3; i15++) {
            int fpr_rint = (sArr2[i15] & 65535) - ((int) FPREngine.fpr_rint(dArr[i7 + i15]));
            i13 += fpr_rint * fpr_rint;
            i14 |= i13;
        }
        int i16 = i13 | (-(i14 >>> 31));
        short[] sArr3 = new short[i3];
        for (int i17 = 0; i17 < i3; i17++) {
            sArr3[i17] = (short) (-FPREngine.fpr_rint(dArr[i8 + i17]));
        }
        if (FalconCommon.is_short_half(i16, sArr3, i) == 0) {
            return 0;
        }
        System.arraycopy(sArr3, 0, sArr, 0, i3);
        return 1;
    }

    void ffSampling_fft_dyntree(SamplerCtx samplerCtx, double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3, double[] dArr4, int i4, double[] dArr5, int i5, int i6, int i7, double[] dArr6, int i8) {
        if (i7 == 0) {
            double sqrt = Math.sqrt(dArr3[i3]) * FPREngine.fpr_inv_sigma[i6];
            dArr[i] = SamplerZ.sample(samplerCtx, dArr[i], sqrt);
            dArr2[i2] = SamplerZ.sample(samplerCtx, dArr2[i2], sqrt);
            return;
        }
        int i9 = 1 << i7;
        int i10 = i9 >> 1;
        FalconFFT.poly_LDL_fft(dArr3, i3, dArr4, i4, dArr5, i5, i7);
        int i11 = i8 + i10;
        FalconFFT.poly_split_fft(dArr6, i8, dArr6, i11, dArr3, i3, i7);
        System.arraycopy(dArr6, i8, dArr3, i3, i9);
        FalconFFT.poly_split_fft(dArr6, i8, dArr6, i11, dArr5, i5, i7);
        System.arraycopy(dArr6, i8, dArr5, i5, i9);
        System.arraycopy(dArr4, i4, dArr6, i8, i9);
        System.arraycopy(dArr3, i3, dArr4, i4, i10);
        int i12 = i4 + i10;
        System.arraycopy(dArr5, i5, dArr4, i12, i10);
        int i13 = i8 + i9;
        int i14 = i13 + i10;
        FalconFFT.poly_split_fft(dArr6, i13, dArr6, i14, dArr2, i2, i7);
        int i15 = i7 - 1;
        ffSampling_fft_dyntree(samplerCtx, dArr6, i13, dArr6, i14, dArr5, i5, dArr5, i5 + i10, dArr4, i12, i6, i15, dArr6, i13 + i9);
        int i16 = i8 + (i9 << 1);
        FalconFFT.poly_merge_fft(dArr6, i16, dArr6, i13, dArr6, i14, i7);
        System.arraycopy(dArr2, i2, dArr6, i13, i9);
        FalconFFT.poly_sub(dArr6, i13, dArr6, i16, i7);
        System.arraycopy(dArr6, i16, dArr2, i2, i9);
        FalconFFT.poly_mul_fft(dArr6, i8, dArr6, i13, i7);
        FalconFFT.poly_add(dArr, i, dArr6, i8, i7);
        FalconFFT.poly_split_fft(dArr6, i8, dArr6, i11, dArr, i, i7);
        ffSampling_fft_dyntree(samplerCtx, dArr6, i8, dArr6, i11, dArr3, i3, dArr3, i3 + i10, dArr4, i4, i6, i15, dArr6, i13);
        FalconFFT.poly_merge_fft(dArr, i, dArr6, i8, dArr6, i11, i7);
    }

    void sign_dyn(short[] sArr, SHAKEDigest sHAKEDigest, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, short[] sArr2, int i, double[] dArr) {
        SamplerCtx samplerCtx;
        do {
            samplerCtx = new SamplerCtx();
            samplerCtx.sigma_min = FPREngine.fpr_sigma_min[i];
            samplerCtx.p.prng_init(sHAKEDigest);
        } while (do_sign_dyn(samplerCtx, sArr, bArr, bArr2, bArr3, bArr4, sArr2, i, dArr, 0) == 0);
    }

    void smallints_to_fpr(double[] dArr, int i, byte[] bArr, int i2) {
        int i3 = 1 << i2;
        for (int i4 = 0; i4 < i3; i4++) {
            dArr[i + i4] = bArr[i4];
        }
    }
}
