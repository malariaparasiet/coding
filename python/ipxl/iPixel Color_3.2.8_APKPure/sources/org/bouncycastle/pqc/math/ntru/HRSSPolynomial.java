package org.bouncycastle.pqc.math.ntru;

import kotlin.UByte;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHRSSParameterSet;

/* loaded from: classes4.dex */
public class HRSSPolynomial extends Polynomial {
    public HRSSPolynomial(NTRUHRSSParameterSet nTRUHRSSParameterSet) {
        super(nTRUHRSSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void lift(Polynomial polynomial) {
        int length = this.coeffs.length;
        Polynomial createPolynomial = this.params.createPolynomial();
        short s = (short) (3 - (length % 3));
        short[] sArr = createPolynomial.coeffs;
        int i = 0;
        int i2 = 2 - s;
        int i3 = polynomial.coeffs[0] * i2;
        short s2 = polynomial.coeffs[1];
        sArr[0] = (short) (i3 + (polynomial.coeffs[2] * s));
        short[] sArr2 = createPolynomial.coeffs;
        int i4 = polynomial.coeffs[1] * i2;
        short s3 = polynomial.coeffs[2];
        sArr2[1] = (short) i4;
        createPolynomial.coeffs[2] = (short) (polynomial.coeffs[2] * i2);
        short s4 = 0;
        for (int i5 = 3; i5 < length; i5++) {
            short[] sArr3 = createPolynomial.coeffs;
            sArr3[0] = (short) (sArr3[0] + (polynomial.coeffs[i5] * ((s * 2) + s4)));
            short[] sArr4 = createPolynomial.coeffs;
            int i6 = s4 + s;
            sArr4[1] = (short) (sArr4[1] + (polynomial.coeffs[i5] * i6));
            short[] sArr5 = createPolynomial.coeffs;
            sArr5[2] = (short) (sArr5[2] + (polynomial.coeffs[i5] * s4));
            s4 = (short) (i6 % 3);
        }
        short[] sArr6 = createPolynomial.coeffs;
        int i7 = s + s4;
        sArr6[1] = (short) (sArr6[1] + (polynomial.coeffs[0] * i7));
        short[] sArr7 = createPolynomial.coeffs;
        sArr7[2] = (short) (sArr7[2] + (polynomial.coeffs[0] * s4));
        short[] sArr8 = createPolynomial.coeffs;
        sArr8[2] = (short) (sArr8[2] + (polynomial.coeffs[1] * i7));
        for (int i8 = 3; i8 < length; i8++) {
            createPolynomial.coeffs[i8] = (short) (createPolynomial.coeffs[i8 - 3] + ((polynomial.coeffs[i8] + polynomial.coeffs[i8 - 1] + polynomial.coeffs[i8 - 2]) * 2));
        }
        createPolynomial.mod3PhiN();
        createPolynomial.z3ToZq();
        this.coeffs[0] = (short) (-createPolynomial.coeffs[0]);
        while (i < length - 1) {
            int i9 = i + 1;
            this.coeffs[i9] = (short) (createPolynomial.coeffs[i] - createPolynomial.coeffs[i9]);
            i = i9;
        }
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.params.packDegree() / 8) {
            int i2 = i * 8;
            int i3 = i * 13;
            int i4 = i3 + 1;
            this.coeffs[i2] = (short) ((bArr[i3] & UByte.MAX_VALUE) | ((((short) (bArr[i4] & UByte.MAX_VALUE)) & 31) << 8));
            int i5 = i3 + 3;
            this.coeffs[i2 + 1] = (short) (((bArr[i4] & UByte.MAX_VALUE) >>> 5) | (((short) (bArr[i3 + 2] & UByte.MAX_VALUE)) << 3) | ((((short) (bArr[i5] & UByte.MAX_VALUE)) & 3) << 11));
            int i6 = (bArr[i5] & UByte.MAX_VALUE) >>> 2;
            int i7 = i3 + 4;
            this.coeffs[i2 + 2] = (short) (i6 | ((((short) (bArr[i7] & UByte.MAX_VALUE)) & 127) << 6));
            int i8 = ((bArr[i7] & UByte.MAX_VALUE) >>> 7) | (((short) (bArr[i3 + 5] & UByte.MAX_VALUE)) << 1);
            int i9 = i3 + 6;
            this.coeffs[i2 + 3] = (short) (i8 | ((((short) (bArr[i9] & UByte.MAX_VALUE)) & 15) << 9));
            int i10 = i3 + 8;
            this.coeffs[i2 + 4] = (short) ((((short) (bArr[i3 + 7] & UByte.MAX_VALUE)) << 4) | ((bArr[i9] & UByte.MAX_VALUE) >>> 4) | ((((short) (bArr[i10] & UByte.MAX_VALUE)) & 1) << 12));
            int i11 = (bArr[i10] & UByte.MAX_VALUE) >>> 1;
            int i12 = i3 + 9;
            this.coeffs[i2 + 5] = (short) (i11 | ((((short) (bArr[i12] & UByte.MAX_VALUE)) & 63) << 7));
            int i13 = i3 + 11;
            this.coeffs[i2 + 6] = (short) ((((short) (bArr[i3 + 10] & UByte.MAX_VALUE)) << 2) | ((bArr[i12] & UByte.MAX_VALUE) >>> 6) | ((((short) (bArr[i13] & UByte.MAX_VALUE)) & 7) << 10));
            this.coeffs[i2 + 7] = (short) (((bArr[i13] & UByte.MAX_VALUE) >>> 3) | (((short) (bArr[i3 + 12] & UByte.MAX_VALUE)) << 5));
            i++;
        }
        int packDegree = this.params.packDegree() & 7;
        if (packDegree == 2) {
            int i14 = i * 8;
            int i15 = i * 13;
            int i16 = i15 + 1;
            this.coeffs[i14] = (short) ((bArr[i15] & UByte.MAX_VALUE) | ((((short) (bArr[i16] & UByte.MAX_VALUE)) & 31) << 8));
            this.coeffs[i14 + 1] = (short) (((((short) (bArr[i15 + 3] & UByte.MAX_VALUE)) & 3) << 11) | ((bArr[i16] & UByte.MAX_VALUE) >>> 5) | (((short) (bArr[i15 + 2] & UByte.MAX_VALUE)) << 3));
        } else if (packDegree == 4) {
            int i17 = i * 8;
            int i18 = i * 13;
            int i19 = i18 + 1;
            this.coeffs[i17] = (short) ((bArr[i18] & UByte.MAX_VALUE) | ((((short) (bArr[i19] & UByte.MAX_VALUE)) & 31) << 8));
            int i20 = i18 + 3;
            this.coeffs[i17 + 1] = (short) (((bArr[i19] & UByte.MAX_VALUE) >>> 5) | (((short) (bArr[i18 + 2] & UByte.MAX_VALUE)) << 3) | ((((short) (bArr[i20] & UByte.MAX_VALUE)) & 3) << 11));
            int i21 = i18 + 4;
            this.coeffs[i17 + 2] = (short) (((bArr[i20] & UByte.MAX_VALUE) >>> 2) | ((((short) (bArr[i21] & UByte.MAX_VALUE)) & 127) << 6));
            this.coeffs[i17 + 3] = (short) (((((short) (bArr[i18 + 6] & UByte.MAX_VALUE)) & 15) << 9) | ((bArr[i21] & UByte.MAX_VALUE) >>> 7) | (((short) (bArr[i18 + 5] & UByte.MAX_VALUE)) << 1));
        }
        this.coeffs[this.params.n() - 1] = 0;
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
            int i4 = i2 * 13;
            short s = sArr[0];
            bArr[i4] = (byte) (s & 255);
            short s2 = sArr[1];
            bArr[i4 + 1] = (byte) ((s >>> 8) | ((s2 & 7) << 5));
            bArr[i4 + 2] = (byte) ((s2 >>> 3) & 255);
            int i5 = s2 >>> 11;
            short s3 = sArr[2];
            bArr[i4 + 3] = (byte) (i5 | ((s3 & 63) << 2));
            short s4 = sArr[3];
            bArr[i4 + 4] = (byte) ((s3 >>> 6) | ((s4 & 1) << 7));
            bArr[i4 + 5] = (byte) ((s4 >>> 1) & 255);
            int i6 = s4 >>> 9;
            short s5 = sArr[4];
            bArr[i4 + 6] = (byte) (i6 | ((s5 & 15) << 4));
            bArr[i4 + 7] = (byte) ((s5 >>> 4) & 255);
            short s6 = sArr[5];
            bArr[i4 + 8] = (byte) ((s5 >>> 12) | ((s6 & 127) << 1));
            int i7 = s6 >>> 7;
            short s7 = sArr[6];
            bArr[i4 + 9] = (byte) (i7 | ((s7 & 3) << 6));
            bArr[i4 + 10] = (byte) ((s7 >>> 2) & 255);
            short s8 = sArr[7];
            bArr[i4 + 11] = (byte) ((s7 >>> 10) | ((s8 & 31) << 3));
            bArr[i4 + 12] = (byte) (s8 >>> 5);
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
        int packDegree = this.params.packDegree() - ((this.params.packDegree() / 8) * 8);
        if (packDegree != 2) {
            if (packDegree != 4) {
                return bArr;
            }
            int i10 = i2 * 13;
            short s9 = sArr[0];
            bArr[i10] = (byte) (s9 & 255);
            short s10 = sArr[1];
            bArr[i10 + 1] = (byte) ((s9 >>> 8) | ((s10 & 7) << 5));
            bArr[i10 + 2] = (byte) ((s10 >>> 3) & 255);
            int i11 = s10 >>> 11;
            short s11 = sArr[2];
            bArr[i10 + 3] = (byte) (i11 | ((s11 & 63) << 2));
            int i12 = s11 >>> 6;
            short s12 = sArr[3];
            bArr[i10 + 4] = (byte) (i12 | ((s12 & 1) << 7));
            bArr[i10 + 5] = (byte) ((s12 >>> 1) & 255);
            bArr[i10 + 6] = (byte) ((s12 >>> 9) | ((sArr[4] & 15) << 4));
        }
        int i13 = i2 * 13;
        short s13 = sArr[0];
        bArr[i13] = (byte) (s13 & 255);
        int i14 = s13 >>> 8;
        short s14 = sArr[1];
        bArr[i13 + 1] = (byte) (i14 | ((s14 & 7) << 5));
        bArr[i13 + 2] = (byte) ((s14 >>> 3) & 255);
        bArr[i13 + 3] = (byte) ((s14 >>> 11) | ((sArr[2] & 63) << 2));
        return bArr;
    }
}
