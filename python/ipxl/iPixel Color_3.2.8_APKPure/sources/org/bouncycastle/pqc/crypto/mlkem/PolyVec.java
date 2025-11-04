package org.bouncycastle.pqc.crypto.mlkem;

import kotlin.UByte;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
class PolyVec {
    private MLKEMEngine engine;
    private int kyberK;
    private int polyVecBytes;
    Poly[] vec;

    public PolyVec() throws Exception {
        throw new Exception("Requires Parameter");
    }

    public PolyVec(MLKEMEngine mLKEMEngine) {
        this.engine = mLKEMEngine;
        this.kyberK = mLKEMEngine.getKyberK();
        this.polyVecBytes = mLKEMEngine.getKyberPolyVecBytes();
        this.vec = new Poly[this.kyberK];
        for (int i = 0; i < this.kyberK; i++) {
            this.vec[i] = new Poly(mLKEMEngine);
        }
    }

    public static void pointwiseAccountMontgomery(Poly poly, PolyVec polyVec, PolyVec polyVec2, MLKEMEngine mLKEMEngine) {
        Poly poly2 = new Poly(mLKEMEngine);
        Poly.baseMultMontgomery(poly, polyVec.getVectorIndex(0), polyVec2.getVectorIndex(0));
        for (int i = 1; i < mLKEMEngine.getKyberK(); i++) {
            Poly.baseMultMontgomery(poly2, polyVec.getVectorIndex(i), polyVec2.getVectorIndex(i));
            poly.addCoeffs(poly2);
        }
        poly.reduce();
    }

    public void addPoly(PolyVec polyVec) {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).addCoeffs(polyVec.getVectorIndex(i));
        }
    }

    public byte[] compressPolyVec() {
        conditionalSubQ();
        byte[] bArr = new byte[this.engine.getKyberPolyVecCompressedBytes()];
        int i = 32;
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * 320) {
            short[] sArr = new short[4];
            int i2 = 0;
            for (int i3 = 0; i3 < this.kyberK; i3++) {
                for (int i4 = 0; i4 < 64; i4++) {
                    for (int i5 = 0; i5 < 4; i5++) {
                        sArr[i5] = (short) (((((getVectorIndex(i3).getCoeffIndex((i4 * 4) + i5) << 10) + 1665) * 1290167) >> 32) & 1023);
                    }
                    short s = sArr[0];
                    bArr[i2] = (byte) s;
                    short s2 = sArr[1];
                    bArr[i2 + 1] = (byte) ((s >> 8) | (s2 << 2));
                    int i6 = s2 >> 6;
                    short s3 = sArr[2];
                    bArr[i2 + 2] = (byte) (i6 | (s3 << 4));
                    int i7 = s3 >> 4;
                    short s4 = sArr[3];
                    bArr[i2 + 3] = (byte) (i7 | (s4 << 6));
                    bArr[i2 + 4] = (byte) (s4 >> 2);
                    i2 += 5;
                }
            }
        } else {
            if (this.engine.getKyberPolyVecCompressedBytes() != this.kyberK * 352) {
                throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
            }
            short[] sArr2 = new short[8];
            int i8 = 0;
            int i9 = 0;
            while (i8 < this.kyberK) {
                int i10 = 0;
                while (i10 < i) {
                    for (int i11 = 0; i11 < 8; i11++) {
                        sArr2[i11] = (short) (((((getVectorIndex(i8).getCoeffIndex((i10 * 8) + i11) << 11) + 1664) * 645084) >> 31) & 2047);
                    }
                    short s5 = sArr2[0];
                    bArr[i9] = (byte) s5;
                    short s6 = sArr2[1];
                    bArr[i9 + 1] = (byte) ((s5 >> 8) | (s6 << 3));
                    short s7 = sArr2[2];
                    bArr[i9 + 2] = (byte) ((s6 >> 5) | (s7 << 6));
                    bArr[i9 + 3] = (byte) (s7 >> 2);
                    int i12 = s7 >> 10;
                    short s8 = sArr2[3];
                    bArr[i9 + 4] = (byte) (i12 | (s8 << 1));
                    short s9 = sArr2[4];
                    bArr[i9 + 5] = (byte) ((s8 >> 7) | (s9 << 4));
                    short s10 = sArr2[5];
                    bArr[i9 + 6] = (byte) ((s9 >> 4) | (s10 << 7));
                    bArr[i9 + 7] = (byte) (s10 >> 1);
                    int i13 = s10 >> 9;
                    short s11 = sArr2[6];
                    bArr[i9 + 8] = (byte) (i13 | (s11 << 2));
                    int i14 = s11 >> 6;
                    short s12 = sArr2[7];
                    bArr[i9 + 9] = (byte) (i14 | (s12 << 5));
                    bArr[i9 + 10] = (byte) (s12 >> 3);
                    i9 += 11;
                    i10++;
                    i = 32;
                }
                i8++;
                i = 32;
            }
        }
        return bArr;
    }

    public void conditionalSubQ() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).conditionalSubQ();
        }
    }

    public void decompressPolyVec(byte[] bArr) {
        int i = 3;
        int i2 = 6;
        short s = 2;
        short s2 = 4;
        short s3 = 1;
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * 320) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.kyberK; i4++) {
                for (int i5 = 0; i5 < 64; i5++) {
                    int i6 = bArr[i3] & UByte.MAX_VALUE;
                    byte b = bArr[i3 + 1];
                    short s4 = (short) (i6 | ((short) ((b & UByte.MAX_VALUE) << 8)));
                    int i7 = (b & UByte.MAX_VALUE) >> 2;
                    byte b2 = bArr[i3 + 2];
                    short s5 = (short) (i7 | ((short) ((b2 & UByte.MAX_VALUE) << 6)));
                    int i8 = (b2 & UByte.MAX_VALUE) >> 4;
                    byte b3 = bArr[i3 + 3];
                    short[] sArr = {s4, s5, (short) (i8 | ((short) ((b3 & UByte.MAX_VALUE) << 4))), (short) (((b3 & UByte.MAX_VALUE) >> 6) | ((short) ((bArr[i3 + 4] & UByte.MAX_VALUE) << 2)))};
                    i3 += 5;
                    for (int i9 = 0; i9 < 4; i9++) {
                        this.vec[i4].setCoeffIndex((i5 * 4) + i9, (short) ((((sArr[i9] & 1023) * MLKEMEngine.KyberQ) + 512) >> 10));
                    }
                }
            }
            return;
        }
        if (this.engine.getKyberPolyVecCompressedBytes() != this.kyberK * 352) {
            throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
        }
        int i10 = 0;
        for (int i11 = 0; i11 < this.kyberK; i11++) {
            int i12 = 0;
            while (i12 < 32) {
                int i13 = bArr[i10] & UByte.MAX_VALUE;
                byte b4 = bArr[i10 + 1];
                short s6 = (short) (i13 | (((short) (b4 & UByte.MAX_VALUE)) << 8));
                int i14 = (b4 & UByte.MAX_VALUE) >> i;
                byte b5 = bArr[i10 + 2];
                short s7 = (short) (i14 | (((short) (b5 & UByte.MAX_VALUE)) << 5));
                int i15 = ((b5 & UByte.MAX_VALUE) >> i2) | (((short) (bArr[i10 + 3] & UByte.MAX_VALUE)) << s);
                byte b6 = bArr[i10 + 4];
                int i16 = i;
                short s8 = (short) (((short) ((b6 & UByte.MAX_VALUE) << 10)) | i15);
                int i17 = (b6 & UByte.MAX_VALUE) >> s3;
                byte b7 = bArr[i10 + 5];
                int i18 = i2;
                short s9 = (short) ((((short) (b7 & UByte.MAX_VALUE)) << 7) | i17);
                int i19 = (b7 & UByte.MAX_VALUE) >> s2;
                byte b8 = bArr[i10 + 6];
                short s10 = s;
                short s11 = (short) ((((short) (b8 & UByte.MAX_VALUE)) << s2) | i19);
                int i20 = ((b8 & UByte.MAX_VALUE) >> 7) | (((short) (bArr[i10 + 7] & UByte.MAX_VALUE)) << s3);
                byte b9 = bArr[i10 + 8];
                short s12 = s2;
                short s13 = (short) (((short) ((b9 & UByte.MAX_VALUE) << 9)) | i20);
                int i21 = (b9 & UByte.MAX_VALUE) >> 2;
                byte b10 = bArr[i10 + 9];
                short s14 = s3;
                short s15 = (short) ((((short) (b10 & UByte.MAX_VALUE)) << 6) | i21);
                short s16 = (short) (((b10 & UByte.MAX_VALUE) >> 5) | (((short) (bArr[i10 + 10] & UByte.MAX_VALUE)) << 3));
                short[] sArr2 = new short[8];
                sArr2[0] = s6;
                sArr2[s14] = s7;
                sArr2[s10] = s8;
                sArr2[i16] = s9;
                sArr2[s12] = s11;
                sArr2[5] = s13;
                sArr2[i18] = s15;
                sArr2[7] = s16;
                i10 += 11;
                for (int i22 = 0; i22 < 8; i22++) {
                    this.vec[i11].setCoeffIndex((i12 * 8) + i22, (short) ((((sArr2[i22] & 2047) * MLKEMEngine.KyberQ) + 1024) >> 11));
                }
                i12++;
                i = i16;
                i2 = i18;
                s = s10;
                s2 = s12;
                s3 = s14;
            }
        }
    }

    public void fromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.kyberK) {
            Poly vectorIndex = getVectorIndex(i);
            int i2 = i * MLKEMEngine.KyberPolyBytes;
            i++;
            vectorIndex.fromBytes(Arrays.copyOfRange(bArr, i2, i * MLKEMEngine.KyberPolyBytes));
        }
    }

    public Poly getVectorIndex(int i) {
        return this.vec[i];
    }

    public void polyVecInverseNttToMont() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyInverseNttToMont();
        }
    }

    public void polyVecNtt() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyNtt();
        }
    }

    public void reducePoly() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).reduce();
        }
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[this.polyVecBytes];
        for (int i = 0; i < this.kyberK; i++) {
            System.arraycopy(this.vec[i].toBytes(), 0, bArr, i * MLKEMEngine.KyberPolyBytes, MLKEMEngine.KyberPolyBytes);
        }
        return bArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        for (int i = 0; i < this.kyberK; i++) {
            stringBuffer.append(this.vec[i].toString());
            if (i != this.kyberK - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
