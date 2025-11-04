package org.bouncycastle.pqc.math.ntru;

import kotlin.UByte;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet;

/* loaded from: classes4.dex */
public class HPSPolynomial extends Polynomial {
    public HPSPolynomial(NTRUHPSParameterSet nTRUHPSParameterSet) {
        super(nTRUHPSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void lift(Polynomial polynomial) {
        System.arraycopy(polynomial.coeffs, 0, this.coeffs, 0, this.coeffs.length);
        z3ToZq();
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int length = this.coeffs.length;
        int i = 0;
        while (i < this.params.packDegree() / 8) {
            int i2 = i * 8;
            int i3 = i * 11;
            int i4 = i3 + 1;
            this.coeffs[i2] = (short) ((bArr[i3] & UByte.MAX_VALUE) | ((((short) (bArr[i4] & UByte.MAX_VALUE)) & 7) << 8));
            int i5 = i3 + 2;
            this.coeffs[i2 + 1] = (short) (((bArr[i4] & UByte.MAX_VALUE) >>> 3) | ((((short) (bArr[i5] & UByte.MAX_VALUE)) & 63) << 5));
            int i6 = ((bArr[i5] & UByte.MAX_VALUE) >>> 6) | ((((short) (bArr[i3 + 3] & UByte.MAX_VALUE)) & 255) << 2);
            int i7 = i3 + 4;
            this.coeffs[i2 + 2] = (short) (i6 | ((((short) (bArr[i7] & UByte.MAX_VALUE)) & 1) << 10));
            int i8 = (bArr[i7] & UByte.MAX_VALUE) >>> 1;
            int i9 = i3 + 5;
            this.coeffs[i2 + 3] = (short) (i8 | ((((short) (bArr[i9] & UByte.MAX_VALUE)) & 15) << 7));
            int i10 = (bArr[i9] & UByte.MAX_VALUE) >>> 4;
            int i11 = i3 + 6;
            this.coeffs[i2 + 4] = (short) (((((short) (bArr[i11] & UByte.MAX_VALUE)) & 127) << 4) | i10);
            int i12 = i3 + 8;
            this.coeffs[i2 + 5] = (short) (((bArr[i11] & UByte.MAX_VALUE) >>> 7) | ((((short) (bArr[i3 + 7] & UByte.MAX_VALUE)) & 255) << 1) | ((((short) (bArr[i12] & UByte.MAX_VALUE)) & 3) << 9));
            int i13 = i3 + 9;
            this.coeffs[i2 + 6] = (short) (((bArr[i12] & UByte.MAX_VALUE) >>> 2) | ((((short) (bArr[i13] & UByte.MAX_VALUE)) & 31) << 6));
            this.coeffs[i2 + 7] = (short) (((bArr[i13] & UByte.MAX_VALUE) >>> 5) | ((((short) (bArr[i3 + 10] & UByte.MAX_VALUE)) & 255) << 3));
            i++;
        }
        int packDegree = this.params.packDegree() & 7;
        if (packDegree == 2) {
            int i14 = i * 8;
            int i15 = i * 11;
            int i16 = i15 + 1;
            this.coeffs[i14] = (short) ((bArr[i15] & UByte.MAX_VALUE) | ((((short) (bArr[i16] & UByte.MAX_VALUE)) & 7) << 8));
            this.coeffs[i14 + 1] = (short) (((((short) (bArr[i15 + 2] & UByte.MAX_VALUE)) & 63) << 5) | ((bArr[i16] & UByte.MAX_VALUE) >>> 3));
        } else if (packDegree == 4) {
            int i17 = i * 8;
            int i18 = i * 11;
            int i19 = i18 + 1;
            this.coeffs[i17] = (short) ((bArr[i18] & UByte.MAX_VALUE) | ((((short) (bArr[i19] & UByte.MAX_VALUE)) & 7) << 8));
            int i20 = i18 + 2;
            this.coeffs[i17 + 1] = (short) (((bArr[i19] & UByte.MAX_VALUE) >>> 3) | ((((short) (bArr[i20] & UByte.MAX_VALUE)) & 63) << 5));
            int i21 = i18 + 4;
            this.coeffs[i17 + 2] = (short) (((((short) (bArr[i18 + 3] & UByte.MAX_VALUE)) & 255) << 2) | ((bArr[i20] & UByte.MAX_VALUE) >>> 6) | ((((short) (bArr[i21] & UByte.MAX_VALUE)) & 1) << 10));
            this.coeffs[i17 + 3] = (short) (((((short) (bArr[i18 + 5] & UByte.MAX_VALUE)) & 15) << 7) | ((bArr[i21] & UByte.MAX_VALUE) >>> 1));
        }
        this.coeffs[length - 1] = 0;
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        short[] sArr = new short[8];
        int i2 = 0;
        while (i2 < this.params.packDegree() / 8) {
            for (int i3 = 0; i3 < 8; i3++) {
                sArr[i3] = (short) modQ(this.coeffs[(i2 * 8) + i3] & 65535, this.params.q());
            }
            int i4 = i2 * 11;
            short s = sArr[0];
            bArr[i4] = (byte) (s & 255);
            short s2 = sArr[1];
            bArr[i4 + 1] = (byte) ((s >>> 8) | ((s2 & 31) << 3));
            int i5 = s2 >>> 5;
            short s3 = sArr[2];
            bArr[i4 + 2] = (byte) (i5 | ((s3 & 3) << 6));
            bArr[i4 + 3] = (byte) ((s3 >>> 2) & 255);
            int i6 = s3 >>> 10;
            short s4 = sArr[3];
            bArr[i4 + 4] = (byte) (i6 | ((s4 & 127) << 1));
            short s5 = sArr[4];
            bArr[i4 + 5] = (byte) ((s4 >>> 7) | ((s5 & 15) << 4));
            short s6 = sArr[5];
            bArr[i4 + 6] = (byte) ((s5 >>> 4) | ((s6 & 1) << 7));
            bArr[i4 + 7] = (byte) ((s6 >>> 1) & 255);
            int i7 = s6 >>> 9;
            short s7 = sArr[6];
            bArr[i4 + 8] = (byte) (i7 | ((s7 & 63) << 2));
            short s8 = sArr[7];
            bArr[i4 + 9] = (byte) ((s7 >>> 6) | ((s8 & 7) << 5));
            bArr[i4 + 10] = (byte) (s8 >>> 3);
            i2++;
        }
        int i8 = 0;
        while (true) {
            int i9 = i2 * 8;
            if (i8 >= this.params.packDegree() - i9) {
                break;
            }
            sArr[i8] = (short) modQ(this.coeffs[i9 + i8] & 65535, this.params.q());
            i8++;
        }
        while (i8 < 8) {
            sArr[i8] = 0;
            i8++;
        }
        int packDegree = this.params.packDegree() & 7;
        if (packDegree == 2) {
            int i10 = i2 * 11;
            short s9 = sArr[0];
            bArr[i10] = (byte) (s9 & 255);
            int i11 = s9 >>> 8;
            short s10 = sArr[1];
            bArr[i10 + 1] = (byte) (i11 | ((s10 & 31) << 3));
            bArr[i10 + 2] = (byte) ((s10 >>> 5) | ((sArr[2] & 3) << 6));
            return bArr;
        }
        if (packDegree != 4) {
            return bArr;
        }
        int i12 = i2 * 11;
        short s11 = sArr[0];
        bArr[i12] = (byte) (s11 & 255);
        int i13 = s11 >>> 8;
        short s12 = sArr[1];
        bArr[i12 + 1] = (byte) (i13 | ((s12 & 31) << 3));
        short s13 = sArr[2];
        bArr[i12 + 2] = (byte) ((s12 >>> 5) | ((s13 & 3) << 6));
        bArr[i12 + 3] = (byte) ((s13 >>> 2) & 255);
        int i14 = s13 >>> 10;
        short s14 = sArr[3];
        bArr[i12 + 4] = (byte) (i14 | ((s14 & 127) << 1));
        bArr[i12 + 5] = (byte) ((s14 >>> 7) | ((sArr[4] & 15) << 4));
        return bArr;
    }
}
