package org.bouncycastle.pqc.crypto.snova;

import java.lang.reflect.Array;
import org.bouncycastle.util.GF16;

/* loaded from: classes4.dex */
class MapGroup1 {
    public final byte[][][] aAlpha;
    public final byte[][][] bAlpha;
    public final byte[][][][] p11;
    public final byte[][][][] p12;
    public final byte[][][][] p21;
    public final byte[][][] qAlpha1;
    public final byte[][][] qAlpha2;

    public MapGroup1(SnovaParameters snovaParameters) {
        int m = snovaParameters.getM();
        int v = snovaParameters.getV();
        int o = snovaParameters.getO();
        int alpha = snovaParameters.getAlpha();
        int lsq = snovaParameters.getLsq();
        this.p11 = (byte[][][][]) Array.newInstance((Class<?>) Byte.TYPE, m, v, v, lsq);
        this.p12 = (byte[][][][]) Array.newInstance((Class<?>) Byte.TYPE, m, v, o, lsq);
        this.p21 = (byte[][][][]) Array.newInstance((Class<?>) Byte.TYPE, m, o, v, lsq);
        this.aAlpha = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, m, alpha, lsq);
        this.bAlpha = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, m, alpha, lsq);
        this.qAlpha1 = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, m, alpha, lsq);
        this.qAlpha2 = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, m, alpha, lsq);
    }

    private static int decodeAlpha(byte[] bArr, int i, byte[][][] bArr2, int i2) {
        int i3 = 0;
        for (byte[][] bArr3 : bArr2) {
            i3 += decodeArray(bArr, i + i3, bArr3, i2 - i3);
        }
        return i3;
    }

    static int decodeArray(byte[] bArr, int i, byte[][] bArr2, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < bArr2.length; i4++) {
            int min = Math.min(bArr2[i4].length, i2 << 1);
            GF16.decode(bArr, i + i3, bArr2[i4], 0, min);
            int i5 = (min + 1) >> 1;
            i3 += i5;
            i2 -= i5;
        }
        return i3;
    }

    static int decodeP(byte[] bArr, int i, byte[][][][] bArr2, int i2) {
        int i3 = 0;
        for (byte[][][] bArr3 : bArr2) {
            i3 += decodeAlpha(bArr, i + i3, bArr3, i2);
        }
        return i3;
    }

    static int fillAlpha(byte[] bArr, int i, byte[][][] bArr2, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < bArr2.length; i4++) {
            int i5 = 0;
            while (true) {
                byte[][] bArr3 = bArr2[i4];
                if (i5 < bArr3.length) {
                    int min = Math.min(bArr3[i5].length, i2 - i3);
                    System.arraycopy(bArr, i + i3, bArr2[i4][i5], 0, min);
                    i3 += min;
                    i5++;
                }
            }
        }
        return i3;
    }

    static int fillP(byte[] bArr, int i, byte[][][][] bArr2, int i2) {
        int i3 = 0;
        for (byte[][][] bArr3 : bArr2) {
            i3 += fillAlpha(bArr, i + i3, bArr3, i2 - i3);
        }
        return i3;
    }

    void decode(byte[] bArr, int i, boolean z) {
        int decodeP = decodeP(bArr, 0, this.p11, i);
        int decodeP2 = decodeP + decodeP(bArr, decodeP, this.p12, i - decodeP);
        int decodeP3 = decodeP2 + decodeP(bArr, decodeP2, this.p21, i - decodeP2);
        if (z) {
            int decodeAlpha = decodeP3 + decodeAlpha(bArr, decodeP3, this.aAlpha, i - decodeP3);
            int decodeAlpha2 = decodeAlpha + decodeAlpha(bArr, decodeAlpha, this.bAlpha, i - decodeAlpha);
            int decodeAlpha3 = decodeAlpha2 + decodeAlpha(bArr, decodeAlpha2, this.qAlpha1, i - decodeAlpha2);
            decodeAlpha(bArr, decodeAlpha3, this.qAlpha2, i - decodeAlpha3);
        }
    }

    void fill(byte[] bArr, boolean z) {
        int fillP = fillP(bArr, 0, this.p11, bArr.length);
        int fillP2 = fillP + fillP(bArr, fillP, this.p12, bArr.length - fillP);
        int fillP3 = fillP2 + fillP(bArr, fillP2, this.p21, bArr.length - fillP2);
        if (z) {
            int fillAlpha = fillP3 + fillAlpha(bArr, fillP3, this.aAlpha, bArr.length - fillP3);
            int fillAlpha2 = fillAlpha + fillAlpha(bArr, fillAlpha, this.bAlpha, bArr.length - fillAlpha);
            int fillAlpha3 = fillAlpha2 + fillAlpha(bArr, fillAlpha2, this.qAlpha1, bArr.length - fillAlpha2);
            fillAlpha(bArr, fillAlpha3, this.qAlpha2, bArr.length - fillAlpha3);
        }
    }
}
