package org.bouncycastle.pqc.crypto.snova;

import com.alibaba.fastjson2.internal.asm.Opcodes;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CTRModeCipher;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.GF16;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
class SnovaEngine {
    private static final Map<Integer, byte[]> fixedAbqSet = new HashMap();
    private static final Map<Integer, byte[][]> sSet = new HashMap();
    private static final Map<Integer, int[][]> xSSet = new HashMap();
    final byte[][] S;
    private final int alpha;
    private final int l;
    private final int lsq;
    private final int m;
    private final int n;
    private final int o;
    private final SnovaParameters params;
    private final int v;
    final int[][] xS;

    public SnovaEngine(SnovaParameters snovaParameters) {
        int i;
        this.params = snovaParameters;
        int l = snovaParameters.getL();
        this.l = l;
        int lsq = snovaParameters.getLsq();
        this.lsq = lsq;
        this.m = snovaParameters.getM();
        this.v = snovaParameters.getV();
        this.o = snovaParameters.getO();
        this.alpha = snovaParameters.getAlpha();
        this.n = snovaParameters.getN();
        int i2 = 0;
        if (!xSSet.containsKey(Integers.valueOf(l))) {
            int i3 = 2;
            byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, l, lsq);
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, l, lsq);
            be_aI(bArr[0], 0, (byte) 1);
            beTheS(bArr[1]);
            while (true) {
                int i4 = this.l;
                if (i3 >= i4) {
                    break;
                }
                GF16Utils.gf16mMul(bArr[i3 - 1], bArr[1], bArr[i3], i4);
                i3++;
            }
            int i5 = 0;
            while (true) {
                i = this.l;
                if (i5 >= i) {
                    break;
                }
                for (int i6 = 0; i6 < this.lsq; i6++) {
                    iArr[i5][i6] = GF16Utils.gf16FromNibble(bArr[i5][i6]);
                }
                i5++;
            }
            sSet.put(Integers.valueOf(i), bArr);
            xSSet.put(Integers.valueOf(this.l), iArr);
        }
        this.S = sSet.get(Integers.valueOf(this.l));
        this.xS = xSSet.get(Integers.valueOf(this.l));
        if (this.l >= 4 || fixedAbqSet.containsKey(Integers.valueOf(this.o))) {
            return;
        }
        int i7 = this.alpha;
        int i8 = this.l;
        int i9 = i7 * i8;
        int i10 = i8 * i9;
        int i11 = this.o;
        int i12 = i11 * i9;
        int i13 = i11 * i10;
        byte[] bArr2 = new byte[i13 << 2];
        int i14 = i13 + i12;
        byte[] bArr3 = new byte[i14];
        byte[] bArr4 = new byte[i12 << 2];
        byte[] bytes = "SNOVA_ABQ".getBytes();
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bytes, 0, bytes.length);
        sHAKEDigest.doFinal(bArr3, 0, i14);
        int i15 = i13 << 1;
        GF16.decode(bArr3, bArr2, i15);
        GF16.decode(bArr3, i10, bArr4, 0, i12 << 1);
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        while (true) {
            int i19 = this.o;
            if (i16 >= i19) {
                fixedAbqSet.put(Integers.valueOf(i19), bArr2);
                return;
            }
            int i20 = i2;
            int i21 = i17;
            int i22 = i18;
            while (i20 < this.alpha) {
                makeInvertibleByAddingAS(bArr2, i22);
                makeInvertibleByAddingAS(bArr2, i13 + i22);
                genAFqS(bArr4, i21, bArr2, i15 + i22);
                genAFqS(bArr4, i12 + i21, bArr2, i15 + i13 + i22);
                i20++;
                i21 += this.l;
                i22 += this.lsq;
            }
            i16++;
            i18 += i10;
            i17 += i9;
            i2 = 0;
        }
    }

    private void beTheS(byte[] bArr) {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = this.l;
            if (i3 >= i) {
                break;
            }
            int i5 = 0;
            while (true) {
                i2 = this.l;
                if (i5 < i2) {
                    bArr[i4 + i5] = (byte) ((8 - (i3 + i5)) & 15);
                    i5++;
                }
            }
            i3++;
            i4 += i2;
        }
        if (i == 5) {
            bArr[24] = 9;
        }
    }

    private void be_aI(byte[] bArr, int i, byte b) {
        int i2 = this.l + 1;
        int i3 = 0;
        while (i3 < this.l) {
            bArr[i] = b;
            i3++;
            i += i2;
        }
    }

    private static void copy4DMatrix(byte[][][][] bArr, byte[][][][] bArr2, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < i; i5++) {
            for (int i6 = 0; i6 < i2; i6++) {
                for (int i7 = 0; i7 < i3; i7++) {
                    System.arraycopy(bArr[i5][i6][i7], 0, bArr2[i5][i6][i7], 0, i4);
                }
            }
        }
    }

    private byte determinant2x2(byte[] bArr, int i) {
        return (byte) (GF16.mul(bArr[i + 1], bArr[i + 2]) ^ GF16.mul(bArr[i], bArr[i + 3]));
    }

    private byte determinant3x3(byte[] bArr, int i) {
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        byte b5 = bArr[i + 4];
        byte b6 = bArr[i + 5];
        byte b7 = bArr[i + 6];
        byte b8 = bArr[i + 7];
        byte b9 = bArr[i + 8];
        return (byte) ((GF16.mul(b2, GF16.mul(b4, b9) ^ GF16.mul(b6, b7)) ^ GF16.mul(b, GF16.mul(b5, b9) ^ GF16.mul(b6, b8))) ^ GF16.mul(b3, GF16.mul(b4, b8) ^ GF16.mul(b5, b7)));
    }

    private byte determinant4x4(byte[] bArr, int i) {
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        byte b5 = bArr[i + 4];
        byte b6 = bArr[i + 5];
        byte b7 = bArr[i + 6];
        byte b8 = bArr[i + 7];
        byte b9 = bArr[i + 8];
        byte b10 = bArr[i + 9];
        byte b11 = bArr[i + 10];
        byte b12 = bArr[i + 11];
        byte b13 = bArr[i + 12];
        byte b14 = bArr[i + 13];
        byte b15 = bArr[i + 14];
        byte b16 = bArr[i + 15];
        byte mul = (byte) (GF16.mul(b11, b16) ^ GF16.mul(b12, b15));
        byte mul2 = (byte) (GF16.mul(b10, b16) ^ GF16.mul(b12, b14));
        byte mul3 = (byte) (GF16.mul(b10, b15) ^ GF16.mul(b11, b14));
        byte mul4 = (byte) (GF16.mul(b12, b13) ^ GF16.mul(b9, b16));
        byte mul5 = (byte) (GF16.mul(b11, b13) ^ GF16.mul(b9, b15));
        byte mul6 = (byte) (GF16.mul(b9, b14) ^ GF16.mul(b10, b13));
        return (byte) (GF16.mul(b4, (GF16.mul(b5, mul3) ^ GF16.mul(b6, mul5)) ^ GF16.mul(b7, mul6)) ^ ((GF16.mul(b, (GF16.mul(b6, mul) ^ GF16.mul(b7, mul2)) ^ GF16.mul(b8, mul3)) ^ GF16.mul(b2, (GF16.mul(b5, mul) ^ GF16.mul(b7, mul4)) ^ GF16.mul(b8, mul5))) ^ GF16.mul(b3, (GF16.mul(b5, mul2) ^ GF16.mul(b6, mul4)) ^ GF16.mul(b8, mul6))));
    }

    private byte determinant5x5(byte[] bArr, int i) {
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        byte b5 = bArr[i + 4];
        byte b6 = bArr[i + 5];
        byte b7 = bArr[i + 6];
        byte b8 = bArr[i + 7];
        byte b9 = bArr[i + 8];
        byte b10 = bArr[i + 9];
        byte b11 = bArr[i + 10];
        byte b12 = bArr[i + 11];
        byte b13 = bArr[i + 12];
        byte b14 = bArr[i + 13];
        byte b15 = bArr[i + 14];
        byte b16 = bArr[i + 15];
        byte b17 = bArr[i + 16];
        byte b18 = bArr[i + 17];
        byte b19 = bArr[i + 18];
        byte b20 = bArr[i + 19];
        byte b21 = bArr[i + 20];
        byte b22 = bArr[i + 21];
        byte b23 = bArr[i + 22];
        byte b24 = bArr[i + 23];
        byte b25 = bArr[i + 24];
        byte mul = (byte) (GF16.mul(b6, b12) ^ GF16.mul(b7, b11));
        byte mul2 = (byte) (GF16.mul(b6, b13) ^ GF16.mul(b8, b11));
        byte mul3 = (byte) (GF16.mul(b6, b14) ^ GF16.mul(b9, b11));
        byte mul4 = (byte) (GF16.mul(b6, b15) ^ GF16.mul(b10, b11));
        byte mul5 = (byte) (GF16.mul(b7, b13) ^ GF16.mul(b8, b12));
        byte mul6 = (byte) (GF16.mul(b7, b14) ^ GF16.mul(b9, b12));
        byte mul7 = (byte) (GF16.mul(b7, b15) ^ GF16.mul(b10, b12));
        byte mul8 = (byte) (GF16.mul(b8, b14) ^ GF16.mul(b9, b13));
        byte mul9 = (byte) (GF16.mul(b8, b15) ^ GF16.mul(b10, b13));
        byte mul10 = (byte) (GF16.mul(b9, b15) ^ GF16.mul(b10, b14));
        return (byte) (((byte) (GF16.mul((GF16.mul(b4, mul7) ^ GF16.mul(b2, mul10)) ^ GF16.mul(b5, mul6), GF16.mul(b16, b23) ^ GF16.mul(b18, b21)) ^ ((byte) (((byte) (((byte) (GF16.mul((GF16.mul(b, mul10) ^ GF16.mul(b4, mul4)) ^ GF16.mul(b5, mul3), GF16.mul(b17, b23) ^ GF16.mul(b18, b22)) ^ ((byte) (((byte) (((byte) (((byte) (((byte) GF16.mul((GF16.mul(b, mul5) ^ GF16.mul(b2, mul2)) ^ GF16.mul(b3, mul), GF16.mul(b19, b25) ^ GF16.mul(b20, b24))) ^ GF16.mul((GF16.mul(b, mul6) ^ GF16.mul(b2, mul3)) ^ GF16.mul(b4, mul), GF16.mul(b18, b25) ^ GF16.mul(b20, b23)))) ^ GF16.mul((GF16.mul(b, mul7) ^ GF16.mul(b2, mul4)) ^ GF16.mul(b5, mul), GF16.mul(b18, b24) ^ GF16.mul(b19, b23)))) ^ GF16.mul((GF16.mul(b, mul8) ^ GF16.mul(b3, mul3)) ^ GF16.mul(b4, mul2), GF16.mul(b17, b25) ^ GF16.mul(b20, b22)))) ^ GF16.mul((GF16.mul(b, mul9) ^ GF16.mul(b3, mul4)) ^ GF16.mul(b5, mul2), GF16.mul(b17, b24) ^ GF16.mul(b19, b22)))))) ^ GF16.mul((GF16.mul(b2, mul8) ^ GF16.mul(b3, mul6)) ^ GF16.mul(b4, mul5), GF16.mul(b16, b25) ^ GF16.mul(b20, b21)))) ^ GF16.mul(GF16.mul(b5, mul5) ^ (GF16.mul(b2, mul9) ^ GF16.mul(b3, mul7)), GF16.mul(b16, b24) ^ GF16.mul(b19, b21)))))) ^ GF16.mul((GF16.mul(b3, mul10) ^ GF16.mul(b4, mul9)) ^ GF16.mul(b5, mul8), GF16.mul(b16, b22) ^ GF16.mul(b17, b21)));
    }

    private void genAFqS(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3;
        int i4;
        be_aI(bArr2, i2, bArr[i]);
        int i5 = 1;
        while (true) {
            i3 = this.l;
            if (i5 >= i3 - 1) {
                break;
            }
            gf16mScaleTo(this.S[i5], bArr[i + i5], bArr2, i2);
            i5++;
        }
        if (bArr[(i + i3) - 1] != 0) {
            i4 = bArr[(i + i3) - 1];
        } else {
            int i6 = bArr[i];
            i4 = 16 - (i6 + (i6 == 0 ? 1 : 0));
        }
        gf16mScaleTo(this.S[i3 - 1], (byte) i4, bArr2, i2);
    }

    private void genAFqSCT(byte[] bArr, int i, byte[] bArr2) {
        int i2;
        int[] iArr = new int[this.lsq];
        int i3 = this.l + 1;
        int gf16FromNibble = GF16Utils.gf16FromNibble(bArr[i]);
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.l) {
            iArr[i5] = gf16FromNibble;
            i4++;
            i5 += i3;
        }
        int i6 = 1;
        while (true) {
            i2 = this.l;
            if (i6 >= i2 - 1) {
                break;
            }
            int gf16FromNibble2 = GF16Utils.gf16FromNibble(bArr[i + i6]);
            for (int i7 = 0; i7 < this.lsq; i7++) {
                iArr[i7] = iArr[i7] ^ (this.xS[i6][i7] * gf16FromNibble2);
            }
            i6++;
        }
        int ctGF16IsNotZero = GF16Utils.ctGF16IsNotZero(bArr[(i2 + i) - 1]);
        int gf16FromNibble3 = GF16Utils.gf16FromNibble((byte) ((bArr[(this.l + i) - 1] * ctGF16IsNotZero) + ((1 - ctGF16IsNotZero) * ((GF16Utils.ctGF16IsNotZero(bArr[i]) + 15) - bArr[i]))));
        for (int i8 = 0; i8 < this.lsq; i8++) {
            int i9 = iArr[i8] ^ (this.xS[this.l - 1][i8] * gf16FromNibble3);
            iArr[i8] = i9;
            bArr2[i8] = GF16Utils.gf16ToNibble(i9);
        }
        Arrays.fill(iArr, 0);
    }

    private void genF(MapGroup2 mapGroup2, MapGroup1 mapGroup1, byte[][][] bArr) {
        byte[][][][] bArr2 = mapGroup1.p11;
        byte[][][][] bArr3 = mapGroup2.f11;
        int i = this.m;
        int i2 = this.v;
        copy4DMatrix(bArr2, bArr3, i, i2, i2, this.lsq);
        copy4DMatrix(mapGroup1.p12, mapGroup2.f12, this.m, this.v, this.o, this.lsq);
        copy4DMatrix(mapGroup1.p21, mapGroup2.f21, this.m, this.o, this.v, this.lsq);
        for (int i3 = 0; i3 < this.m; i3++) {
            for (int i4 = 0; i4 < this.v; i4++) {
                for (int i5 = 0; i5 < this.o; i5++) {
                    for (int i6 = 0; i6 < this.v; i6++) {
                        GF16Utils.gf16mMulToTo(mapGroup1.p11[i3][i4][i6], bArr[i6][i5], mapGroup1.p11[i3][i6][i4], mapGroup2.f12[i3][i4][i5], mapGroup2.f21[i3][i5][i4], this.l);
                    }
                }
            }
        }
    }

    private void genSeedsAndT12(byte[][][] bArr, byte[] bArr2) {
        int i = this.v * this.o * this.l;
        int i2 = (i + 1) >>> 1;
        byte[] bArr3 = new byte[i2];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr2, 0, bArr2.length);
        sHAKEDigest.doFinal(bArr3, 0, i2);
        byte[] bArr4 = new byte[i];
        GF16.decode(bArr3, bArr4, i);
        int i3 = 0;
        for (int i4 = 0; i4 < this.v; i4++) {
            for (int i5 = 0; i5 < this.o; i5++) {
                genAFqSCT(bArr4, i3, bArr[i4][i5]);
                i3 += this.l;
            }
        }
    }

    private void generateASMatrixTo(byte[] bArr, int i, byte b) {
        int i2;
        int i3 = 0;
        while (i3 < this.l) {
            int i4 = 0;
            while (true) {
                i2 = this.l;
                if (i4 < i2) {
                    byte b2 = (byte) (8 - (i3 + i4));
                    if (i2 == 5 && i3 == 4 && i4 == 4) {
                        b2 = 9;
                    }
                    int i5 = i + i4;
                    bArr[i5] = (byte) (GF16.mul(b2, b) ^ bArr[i5]);
                    i4++;
                }
            }
            i3++;
            i += i2;
        }
    }

    private byte gf16Determinant(byte[] bArr, int i) {
        int i2 = this.l;
        if (i2 == 2) {
            return determinant2x2(bArr, i);
        }
        if (i2 == 3) {
            return determinant3x3(bArr, i);
        }
        if (i2 == 4) {
            return determinant4x4(bArr, i);
        }
        if (i2 == 5) {
            return determinant5x5(bArr, i);
        }
        throw new IllegalStateException();
    }

    private void gf16mScaleTo(byte[] bArr, byte b, byte[] bArr2, int i) {
        int i2;
        int i3 = 0;
        int i4 = 0;
        while (i3 < this.l) {
            int i5 = 0;
            while (true) {
                i2 = this.l;
                if (i5 < i2) {
                    int i6 = i4 + i5;
                    int i7 = i6 + i;
                    bArr2[i7] = (byte) (GF16.mul(bArr[i6], b) ^ bArr2[i7]);
                    i5++;
                }
            }
            i3++;
            i4 += i2;
        }
    }

    private void makeInvertibleByAddingAS(byte[] bArr, int i) {
        if (gf16Determinant(bArr, i) != 0) {
            return;
        }
        for (int i2 = 1; i2 < 16; i2++) {
            generateASMatrixTo(bArr, i, (byte) i2);
            if (gf16Determinant(bArr, i) != 0) {
                return;
            }
        }
    }

    public void genABQP(MapGroup1 mapGroup1, byte[] bArr) {
        int i = this.lsq;
        int i2 = this.m;
        int i3 = this.alpha;
        int i4 = this.n;
        int i5 = this.l;
        int i6 = (i * ((i2 * 2 * i3) + (((i4 * i4) - (i2 * i2)) * i2))) + (i5 * 2 * i2 * i3);
        int i7 = ((i2 * i3) * i5) << 1;
        byte[] bArr2 = new byte[i7];
        int i8 = (i6 + 1) >> 1;
        byte[] bArr3 = new byte[i8];
        if (this.params.isPkExpandShake()) {
            byte[] bArr4 = new byte[8];
            SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
            long j = 0;
            int i9 = 0;
            while (i8 > 0) {
                sHAKEDigest.update(bArr, 0, bArr.length);
                Pack.longToLittleEndian(j, bArr4, 0);
                sHAKEDigest.update(bArr4, 0, 8);
                int min = Math.min(i8, Opcodes.JSR);
                sHAKEDigest.doFinal(bArr3, i9, min);
                i9 += min;
                i8 -= min;
                j++;
            }
        } else {
            CTRModeCipher newInstance = SICBlockCipher.newInstance(AESEngine.newInstance());
            newInstance.init(true, new ParametersWithIV(new KeyParameter(bArr), new byte[16]));
            int blockSize = newInstance.getBlockSize();
            byte[] bArr5 = new byte[blockSize];
            int i10 = 0;
            while (true) {
                int i11 = i10 + blockSize;
                if (i11 > i8) {
                    break;
                }
                newInstance.processBlock(bArr5, 0, bArr3, i10);
                i10 = i11;
            }
            if (i10 < i8) {
                newInstance.processBlock(bArr5, 0, bArr5, 0);
                System.arraycopy(bArr5, 0, bArr3, i10, i8 - i10);
            }
        }
        if ((this.lsq & 1) == 0) {
            mapGroup1.decode(bArr3, (i6 - i7) >> 1, this.l >= 4);
        } else {
            int i12 = i6 - i7;
            byte[] bArr6 = new byte[i12];
            GF16.decode(bArr3, bArr6, i12);
            mapGroup1.fill(bArr6, this.l >= 4);
        }
        if (this.l < 4) {
            int i13 = this.o;
            int i14 = this.alpha * i13 * this.lsq;
            byte[] bArr7 = fixedAbqSet.get(Integers.valueOf(i13));
            MapGroup1.fillAlpha(bArr7, 0, mapGroup1.aAlpha, this.m * i14);
            MapGroup1.fillAlpha(bArr7, i14, mapGroup1.bAlpha, (this.m - 1) * i14);
            MapGroup1.fillAlpha(bArr7, i14 * 2, mapGroup1.qAlpha1, (this.m - 2) * i14);
            MapGroup1.fillAlpha(bArr7, i14 * 3, mapGroup1.qAlpha2, (this.m - 3) * i14);
            return;
        }
        GF16.decode(bArr3, (i6 - i7) >> 1, bArr2, 0, i7);
        int i15 = this.m * this.alpha * this.l;
        int i16 = 0;
        for (int i17 = 0; i17 < this.m; i17++) {
            for (int i18 = 0; i18 < this.alpha; i18++) {
                makeInvertibleByAddingAS(mapGroup1.aAlpha[i17][i18], 0);
                makeInvertibleByAddingAS(mapGroup1.bAlpha[i17][i18], 0);
                genAFqS(bArr2, i16, mapGroup1.qAlpha1[i17][i18], 0);
                genAFqS(bArr2, i15, mapGroup1.qAlpha2[i17][i18], 0);
                int i19 = this.l;
                i16 += i19;
                i15 += i19;
            }
        }
    }

    public void genMap1T12Map2(SnovaKeyElements snovaKeyElements, byte[] bArr, byte[] bArr2) {
        genSeedsAndT12(snovaKeyElements.T12, bArr2);
        genABQP(snovaKeyElements.map1, bArr);
        genF(snovaKeyElements.map2, snovaKeyElements.map1, snovaKeyElements.T12);
    }

    public void genP22(byte[] bArr, int i, byte[][][] bArr2, byte[][][][] bArr3, byte[][][][] bArr4) {
        int i2 = this.o;
        int i3 = this.lsq * i2;
        int i4 = i2 * i3;
        int i5 = this.m * i4;
        byte[] bArr5 = new byte[i5];
        int i6 = 0;
        int i7 = 0;
        while (i6 < this.m) {
            int i8 = 0;
            int i9 = i7;
            while (i8 < this.o) {
                int i10 = 0;
                int i11 = i9;
                while (i10 < this.o) {
                    int i12 = 0;
                    while (i12 < this.v) {
                        byte[][] bArr6 = bArr2[i12];
                        int i13 = i10;
                        GF16Utils.gf16mMulTo(bArr6[i8], bArr4[i6][i12][i13], bArr3[i6][i8][i12], bArr6[i13], bArr5, i11, this.l);
                        i12++;
                        i10 = i13;
                    }
                    i10++;
                    i11 += this.lsq;
                }
                i8++;
                i9 += i3;
            }
            i6++;
            i7 += i4;
        }
        GF16.encode(bArr5, bArr, i, i5);
    }
}
