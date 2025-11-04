package org.bouncycastle.pqc.crypto.falcon;

import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
class FalconRNG {
    byte[] bd = new byte[512];
    int ptr = 0;
    byte[] sd = new byte[256];

    FalconRNG() {
    }

    private void QROUND(int i, int i2, int i3, int i4, int[] iArr) {
        int i5 = iArr[i] + iArr[i2];
        iArr[i] = i5;
        int i6 = i5 ^ iArr[i4];
        iArr[i4] = i6;
        int i7 = (i6 >>> 16) | (i6 << 16);
        iArr[i4] = i7;
        int i8 = iArr[i3] + i7;
        iArr[i3] = i8;
        int i9 = iArr[i2] ^ i8;
        iArr[i2] = i9;
        int i10 = (i9 >>> 20) | (i9 << 12);
        iArr[i2] = i10;
        int i11 = iArr[i] + i10;
        iArr[i] = i11;
        int i12 = iArr[i4] ^ i11;
        iArr[i4] = i12;
        int i13 = (i12 >>> 24) | (i12 << 8);
        iArr[i4] = i13;
        int i14 = iArr[i3] + i13;
        iArr[i3] = i14;
        int i15 = iArr[i2] ^ i14;
        iArr[i2] = i15;
        iArr[i2] = (i15 >>> 25) | (i15 << 7);
    }

    long prng_get_u64() {
        int i = this.ptr;
        if (i >= this.bd.length - 9) {
            prng_refill();
            i = 0;
        }
        this.ptr = i + 8;
        return Pack.littleEndianToLong(this.bd, i);
    }

    byte prng_get_u8() {
        byte[] bArr = this.bd;
        int i = this.ptr;
        int i2 = i + 1;
        this.ptr = i2;
        byte b = bArr[i];
        if (i2 == bArr.length) {
            prng_refill();
        }
        return b;
    }

    void prng_init(SHAKEDigest sHAKEDigest) {
        sHAKEDigest.doOutput(this.sd, 0, 56);
        prng_refill();
    }

    void prng_refill() {
        FalconRNG falconRNG = this;
        int[] iArr = {1634760805, 857760878, 2036477234, 1797285236};
        int[] iArr2 = new int[16];
        long littleEndianToLong = Pack.littleEndianToLong(falconRNG.sd, 48);
        for (int i = 0; i < 8; i++) {
            System.arraycopy(iArr, 0, iArr2, 0, 4);
            Pack.littleEndianToInt(falconRNG.sd, 0, iArr2, 4, 12);
            int i2 = (int) littleEndianToLong;
            iArr2[14] = iArr2[14] ^ i2;
            int i3 = (int) (littleEndianToLong >>> 32);
            iArr2[15] = iArr2[15] ^ i3;
            int i4 = 0;
            while (i4 < 10) {
                falconRNG.QROUND(0, 4, 8, 12, iArr2);
                falconRNG = this;
                falconRNG.QROUND(1, 5, 9, 13, iArr2);
                falconRNG.QROUND(2, 6, 10, 14, iArr2);
                falconRNG.QROUND(3, 7, 11, 15, iArr2);
                falconRNG.QROUND(0, 5, 10, 15, iArr2);
                falconRNG.QROUND(1, 6, 11, 12, iArr2);
                falconRNG.QROUND(2, 7, 8, 13, iArr2);
                falconRNG.QROUND(3, 4, 9, 14, iArr2);
                i4++;
                i3 = i3;
            }
            int i5 = i3;
            for (int i6 = 0; i6 < 4; i6++) {
                iArr2[i6] = iArr2[i6] + iArr[i6];
            }
            for (int i7 = 4; i7 < 14; i7++) {
                iArr2[i7] = iArr2[i7] + Pack.littleEndianToInt(falconRNG.sd, (i7 * 4) - 16);
            }
            iArr2[14] = iArr2[14] + (Pack.littleEndianToInt(falconRNG.sd, 40) ^ i2);
            iArr2[15] = iArr2[15] + (Pack.littleEndianToInt(falconRNG.sd, 44) ^ i5);
            littleEndianToLong++;
            for (int i8 = 0; i8 < 16; i8++) {
                Pack.intToLittleEndian(iArr2[i8], falconRNG.bd, (i << 2) + (i8 << 5));
            }
        }
        Pack.longToLittleEndian(littleEndianToLong, falconRNG.sd, 48);
        falconRNG.ptr = 0;
    }
}
