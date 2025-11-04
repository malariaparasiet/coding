package org.bouncycastle.pqc.crypto.snova;

import java.lang.reflect.Array;

/* loaded from: classes4.dex */
class SnovaKeyElements {
    public final byte[][][] T12;
    public final MapGroup1 map1;
    public final MapGroup2 map2;

    public SnovaKeyElements(SnovaParameters snovaParameters) {
        int o = snovaParameters.getO();
        int v = snovaParameters.getV();
        int lsq = snovaParameters.getLsq();
        this.map1 = new MapGroup1(snovaParameters);
        this.T12 = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, v, o, lsq);
        this.map2 = new MapGroup2(snovaParameters);
    }

    static int copy3d(byte[] bArr, int i, byte[][][] bArr2) {
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            int i3 = 0;
            while (true) {
                byte[][] bArr3 = bArr2[i2];
                if (i3 < bArr3.length) {
                    byte[] bArr4 = bArr3[i3];
                    System.arraycopy(bArr, i, bArr4, 0, bArr4.length);
                    i += bArr2[i2][i3].length;
                    i3++;
                }
            }
        }
        return i;
    }

    static int copy3d(byte[][][] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = 0;
            while (true) {
                byte[][] bArr3 = bArr[i2];
                if (i3 < bArr3.length) {
                    byte[] bArr4 = bArr3[i3];
                    System.arraycopy(bArr4, 0, bArr2, i, bArr4.length);
                    i += bArr[i2][i3].length;
                    i3++;
                }
            }
        }
        return i;
    }

    static int copy4d(byte[] bArr, int i, byte[][][][] bArr2) {
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            for (int i3 = 0; i3 < bArr2[i2].length; i3++) {
                int i4 = 0;
                while (true) {
                    byte[][] bArr3 = bArr2[i2][i3];
                    if (i4 < bArr3.length) {
                        byte[] bArr4 = bArr3[i4];
                        System.arraycopy(bArr, i, bArr4, 0, bArr4.length);
                        i += bArr2[i2][i3][i4].length;
                        i4++;
                    }
                }
            }
        }
        return i;
    }

    static int copy4d(byte[][][][] bArr, byte[] bArr2, int i) {
        for (byte[][][] bArr3 : bArr) {
            i = copy3d(bArr3, bArr2, i);
        }
        return i;
    }
}
