package com.wifiled.ipixels.utils;

import csh.tiro.cc.fft.int16FFT;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes3.dex */
public class FftUtils {
    public static final int NUM_FFT = 128;

    public static byte[] getLevelByWaveData(short[] wave) {
        short[] sArr = new short[128];
        short[] sArr2 = new short[128];
        if (wave.length > 128) {
            System.arraycopy(wave, 0, sArr, 0, 128);
            int16FFT.WindowCalc(sArr, (char) 0);
            int16FFT.BitReverse(sArr);
            int16FFT.IntFFT(sArr, sArr2);
        }
        short[] sArr3 = new short[28];
        short s = ShortCompanionObject.MAX_VALUE;
        short s2 = 0;
        for (int i = 3; i < 64 && i < 31; i++) {
            short s3 = sArr[i];
            double d = s3 * s3;
            short s4 = sArr2[i];
            short sqrt = (short) Math.sqrt(d + (s4 * s4));
            sArr3[i - 3] = sqrt;
            if (sqrt > s2) {
                s2 = sqrt;
            }
            if (sqrt < s) {
                s = sqrt;
            }
        }
        float abs = Math.abs(((short) (s2 * 0.98d)) - s) / 15;
        if (abs < 1.0d) {
            abs = 1.0f;
        }
        for (int i2 = 0; i2 < 28; i2++) {
            if (i2 == 0 && sArr3[i2] / abs < 0.5d) {
                sArr3[i2] = 0;
            } else {
                sArr3[i2] = (short) Math.ceil(sArr3[i2] / abs);
            }
            if (sArr3[i2] >= 15) {
                sArr3[i2] = 15;
            }
        }
        byte[] bArr = new byte[14];
        for (int i3 = 0; i3 < 14; i3++) {
            int i4 = i3 * 2;
            bArr[i3] = (byte) (sArr3[i4 + 1] | (sArr3[i4] << 4));
        }
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 0, bArr2, 0, 14);
        return bArr2;
    }

    public static byte[] getWaveFormData(short[] waveform, int rhythmType) {
        short s = 0;
        for (short s2 : waveform) {
            if (s2 > s) {
                s = s2;
            }
        }
        short[] sArr = new short[128];
        short[] sArr2 = new short[128];
        if (waveform.length > 128) {
            System.arraycopy(waveform, 0, sArr, 0, 128);
            int16FFT.WindowCalc(sArr, (char) 0);
            int16FFT.BitReverse(sArr);
            int16FFT.IntFFT(sArr, sArr2);
        }
        short[] sArr3 = new short[28];
        short s3 = ShortCompanionObject.MAX_VALUE;
        short s4 = 0;
        for (int i = 3; i < 64 && i < 31; i++) {
            short s5 = sArr[i];
            double d = s5 * s5;
            short s6 = sArr2[i];
            short sqrt = (short) Math.sqrt(d + (s6 * s6));
            sArr3[i - 3] = sqrt;
            if (sqrt > s4) {
                s4 = sqrt;
            }
            if (sqrt < s3) {
                s3 = sqrt;
            }
        }
        float abs = Math.abs(((short) (s4 * 0.98d)) - s3) / 16;
        if (abs < 1.0d) {
            abs = 1.0f;
        }
        for (int i2 = 0; i2 < 28; i2++) {
            if (i2 == 0 && sArr3[i2] / abs < 0.5d) {
                sArr3[i2] = 0;
            } else {
                sArr3[i2] = (short) Math.ceil(sArr3[i2] / abs);
            }
            if (sArr3[i2] >= 16) {
                sArr3[i2] = 16;
            }
        }
        byte[] bArr = new byte[14];
        for (int i3 = 0; i3 < 14; i3++) {
            int i4 = i3 * 2;
            bArr[i3] = (byte) (sArr3[i4 + 1] | (sArr3[i4] << 4));
        }
        byte[] bArr2 = new byte[16];
        bArr2[0] = 15;
        bArr2[1] = (byte) rhythmType;
        System.arraycopy(bArr, 0, bArr2, 2, 14);
        return bArr2;
    }
}
