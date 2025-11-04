package com.wifiled.musiclib.utils;

import csh.tiro.cc.fft.int16FFT;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes3.dex */
public class FftConvertUtils {
    public static short NUM_FFT = 128;
    private short _maxLevel;
    private short _minLevel;
    private float mMaxLevel;
    private float mMinLevel;
    private short maxvalue;
    private short[] pcmbuffer;

    private static class FftConvertHolder {
        private static FftConvertUtils fftConvertUtils = new FftConvertUtils();

        private FftConvertHolder() {
        }
    }

    public static FftConvertUtils getInstance() {
        return FftConvertHolder.fftConvertUtils;
    }

    private FftConvertUtils() {
        this.maxvalue = (short) 0;
        this.pcmbuffer = null;
        this._maxLevel = (short) 0;
        this._minLevel = ShortCompanionObject.MAX_VALUE;
    }

    public short getLevelByWaveData(short[] sArr) {
        if (sArr.length <= 128) {
            return (short) -1;
        }
        short[] sArr2 = new short[128];
        short[] sArr3 = new short[128];
        System.arraycopy(sArr, 0, sArr2, 0, 128);
        this.maxvalue = (short) 0;
        for (int i = 0; i < 64; i++) {
            short s = sArr2[i];
            if (s > this.maxvalue) {
                this.maxvalue = s;
            }
        }
        int16FFT.WindowCalc(sArr2, (char) 0);
        int16FFT.BitReverse(sArr2);
        int16FFT.IntFFT(sArr2, sArr3);
        return computeLevel(this.maxvalue, ShortCompanionObject.MAX_VALUE);
    }

    public short getLevelByRecordDate(short[] sArr) {
        short[] sArr2;
        short[] sArr3 = this.pcmbuffer;
        if (sArr3 == null || sArr3.length != sArr.length) {
            this.pcmbuffer = new short[sArr.length];
        }
        System.arraycopy(sArr, 0, this.pcmbuffer, 0, sArr.length);
        this.maxvalue = (short) 0;
        int i = 0;
        while (true) {
            sArr2 = this.pcmbuffer;
            if (i >= sArr2.length) {
                break;
            }
            short s = sArr2[i];
            if (s > this.maxvalue) {
                this.maxvalue = s;
            }
            i++;
        }
        if (sArr2.length > 128) {
            short[] sArr4 = new short[128];
            System.arraycopy(sArr2, 0, sArr4, 0, 128);
            int16FFT.WindowCalc(sArr4, (char) 0);
            int16FFT.BitReverse(sArr4);
            int16FFT.IntFFT(sArr4, new short[128]);
        }
        return computeLevel(this.maxvalue, ShortCompanionObject.MAX_VALUE);
    }

    private short computeLevel(short s, short s2) {
        float f;
        float f2 = this.mMaxLevel;
        if (f2 > 0.0f) {
            this.mMaxLevel = f2 - (f2 * 0.1f);
        }
        float f3 = this.mMinLevel;
        if (f3 > 0.0f) {
            this.mMinLevel = f3 + (0.08f * f3);
        }
        float f4 = this.mMaxLevel;
        if (f4 == 0.0f || f4 <= s) {
            this.mMaxLevel = s;
        }
        float f5 = this.mMinLevel;
        if (f5 == 0.0f || f5 > s) {
            this.mMinLevel = s;
        }
        float f6 = s2;
        float f7 = 0.2f * f6;
        if (this.mMaxLevel < f7) {
            this.mMaxLevel = f7;
        }
        float f8 = 0.85f * f6;
        if (this.mMinLevel > f8) {
            this.mMinLevel = f8;
        }
        float f9 = s;
        float f10 = 0.1f * f9;
        float f11 = this.mMaxLevel;
        if (f11 != 0.0f) {
            float f12 = this.mMinLevel;
            f = (((f9 - f12) / (f11 - f12)) * (f6 - f10)) + f10;
        } else {
            f = s / s2;
        }
        short s3 = (short) ((f / f6) * 255.0f);
        if (s3 < 2) {
            return (short) 2;
        }
        return s3;
    }

    public byte[] getFftData(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int i = 0; i < length; i++) {
            bArr2[i] = (byte) (bArr2[i] - ByteCompanionObject.MIN_VALUE);
        }
        short[] sArr = new short[128];
        short[] sArr2 = new short[128];
        for (int i2 = 0; i2 < NUM_FFT && i2 < length; i2++) {
            sArr[i2] = (short) (bArr2[i2] * 256);
        }
        int16FFT.WindowCalc(sArr, (char) 0);
        int16FFT.BitReverse(sArr);
        int16FFT.IntFFT(sArr, sArr2);
        short[] sArr3 = new short[34];
        for (int i3 = 3; i3 < NUM_FFT / 2 && i3 < 37; i3++) {
            short s = sArr[i3];
            double d = s * s;
            short s2 = sArr2[i3];
            short sqrt = (short) Math.sqrt(d + (s2 * s2));
            sArr3[i3 - 3] = sqrt;
            if (sqrt > this._maxLevel) {
                this._maxLevel = sqrt;
            }
            if (sqrt < this._minLevel) {
                this._minLevel = sqrt;
            }
        }
        short s3 = (short) (this._maxLevel * 0.98d);
        this._maxLevel = s3;
        float abs = Math.abs(s3 - this._minLevel) / 12;
        if (abs < 1.0d) {
            abs = 1.0f;
        }
        for (int i4 = 0; i4 < 34; i4++) {
            short s4 = sArr3[i4];
            if (s4 / abs < 0.3d) {
                sArr3[i4] = 0;
            } else {
                sArr3[i4] = (short) Math.ceil(s4 / abs);
            }
            if (sArr3[i4] >= 12) {
                sArr3[i4] = 12;
            }
        }
        byte[] bArr3 = new byte[17];
        for (int i5 = 0; i5 < 17; i5++) {
            int i6 = i5 * 2;
            bArr3[i5] = (byte) (sArr3[i6 + 1] | (sArr3[i6] << 4));
        }
        byte[] bArr4 = new byte[16];
        bArr4[0] = 18;
        System.arraycopy(bArr3, 0, bArr4, 1, 14);
        return bArr4;
    }
}
