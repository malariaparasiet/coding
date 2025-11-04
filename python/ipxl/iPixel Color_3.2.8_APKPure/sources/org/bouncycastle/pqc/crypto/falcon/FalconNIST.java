package org.bouncycastle.pqc.crypto.falcon;

import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
class FalconNIST {
    final int CRYPTO_BYTES;
    private final int CRYPTO_PUBLICKEYBYTES;
    private final int CRYPTO_SECRETKEYBYTES;
    final int LOGN;
    private final int N;
    final int NONCELEN;
    private final SecureRandom rand;

    FalconNIST(int i, int i2, SecureRandom secureRandom) {
        this.rand = secureRandom;
        this.LOGN = i;
        this.NONCELEN = i2;
        int i3 = 1 << i;
        this.N = i3;
        this.CRYPTO_PUBLICKEYBYTES = ((i3 * 14) / 8) + 1;
        if (i == 10) {
            this.CRYPTO_SECRETKEYBYTES = 2305;
            this.CRYPTO_BYTES = 1330;
            return;
        }
        if (i == 9 || i == 8) {
            this.CRYPTO_SECRETKEYBYTES = ((i3 * 12) / 8) + 1 + i3;
            this.CRYPTO_BYTES = 690;
        } else if (i == 7 || i == 6) {
            this.CRYPTO_SECRETKEYBYTES = ((i3 * 14) / 8) + 1 + i3;
            this.CRYPTO_BYTES = 690;
        } else {
            this.CRYPTO_SECRETKEYBYTES = (i3 * 2) + 1 + i3;
            this.CRYPTO_BYTES = 690;
        }
    }

    byte[] crypto_sign(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        int i2 = this.N;
        byte[] bArr4 = new byte[i2];
        byte[] bArr5 = new byte[i2];
        byte[] bArr6 = new byte[i2];
        byte[] bArr7 = new byte[i2];
        short[] sArr = new short[i2];
        short[] sArr2 = new short[i2];
        byte[] bArr8 = new byte[48];
        byte[] bArr9 = new byte[this.NONCELEN];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        FalconSign falconSign = new FalconSign();
        int trim_i8_decode = FalconCodec.trim_i8_decode(bArr4, this.LOGN, FalconCodec.max_fg_bits[this.LOGN], bArr3, 0, this.CRYPTO_SECRETKEYBYTES);
        if (trim_i8_decode == 0) {
            throw new IllegalStateException("f decode failed");
        }
        int trim_i8_decode2 = FalconCodec.trim_i8_decode(bArr5, this.LOGN, FalconCodec.max_fg_bits[this.LOGN], bArr3, trim_i8_decode, this.CRYPTO_SECRETKEYBYTES - trim_i8_decode);
        if (trim_i8_decode2 == 0) {
            throw new IllegalStateException("g decode failed");
        }
        int i3 = trim_i8_decode + trim_i8_decode2;
        int trim_i8_decode3 = FalconCodec.trim_i8_decode(bArr6, this.LOGN, FalconCodec.max_FG_bits[this.LOGN], bArr3, i3, this.CRYPTO_SECRETKEYBYTES - i3);
        if (trim_i8_decode3 == 0) {
            throw new IllegalArgumentException("F decode failed");
        }
        if (trim_i8_decode3 + i3 != this.CRYPTO_SECRETKEYBYTES - 1) {
            throw new IllegalStateException("full key not used");
        }
        if (!FalconVrfy.complete_private(bArr7, bArr4, bArr5, bArr6, this.LOGN, new short[this.N * 2])) {
            throw new IllegalStateException("complete_private failed");
        }
        this.rand.nextBytes(bArr9);
        sHAKEDigest.update(bArr9, 0, this.NONCELEN);
        sHAKEDigest.update(bArr2, 0, i);
        FalconCommon.hash_to_point_vartime(sHAKEDigest, sArr2, this.LOGN);
        this.rand.nextBytes(bArr8);
        sHAKEDigest.reset();
        sHAKEDigest.update(bArr8, 0, 48);
        falconSign.sign_dyn(sArr, sHAKEDigest, bArr4, bArr5, bArr6, bArr7, sArr2, this.LOGN, new double[this.N * 10]);
        int i4 = (this.CRYPTO_BYTES - 2) - this.NONCELEN;
        byte[] bArr10 = new byte[i4];
        int comp_encode = FalconCodec.comp_encode(bArr10, i4, sArr, this.LOGN);
        if (comp_encode == 0) {
            throw new IllegalStateException("signature failed to generate");
        }
        bArr[0] = (byte) (this.LOGN + 48);
        System.arraycopy(bArr9, 0, bArr, 1, this.NONCELEN);
        System.arraycopy(bArr10, 0, bArr, this.NONCELEN + 1, comp_encode);
        return Arrays.copyOfRange(bArr, 0, this.NONCELEN + 1 + comp_encode);
    }

    byte[][] crypto_sign_keypair(byte[] bArr, byte[] bArr2) {
        int i = this.N;
        byte[] bArr3 = new byte[i];
        byte[] bArr4 = new byte[i];
        byte[] bArr5 = new byte[i];
        short[] sArr = new short[i];
        byte[] bArr6 = new byte[48];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        this.rand.nextBytes(bArr6);
        sHAKEDigest.update(bArr6, 0, 48);
        FalconKeyGen.keygen(sHAKEDigest, bArr3, bArr4, bArr5, sArr, this.LOGN);
        int i2 = this.LOGN;
        bArr2[0] = (byte) (i2 + 80);
        int trim_i8_encode = FalconCodec.trim_i8_encode(bArr2, 1, this.CRYPTO_SECRETKEYBYTES - 1, bArr3, i2, FalconCodec.max_fg_bits[this.LOGN]);
        if (trim_i8_encode == 0) {
            throw new IllegalStateException("f encode failed");
        }
        int i3 = trim_i8_encode + 1;
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, 1, i3);
        int trim_i8_encode2 = FalconCodec.trim_i8_encode(bArr2, i3, this.CRYPTO_SECRETKEYBYTES - i3, bArr4, this.LOGN, FalconCodec.max_fg_bits[this.LOGN]);
        if (trim_i8_encode2 == 0) {
            throw new IllegalStateException("g encode failed");
        }
        int i4 = i3 + trim_i8_encode2;
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr2, i3, i4);
        int trim_i8_encode3 = FalconCodec.trim_i8_encode(bArr2, i4, this.CRYPTO_SECRETKEYBYTES - i4, bArr5, this.LOGN, FalconCodec.max_FG_bits[this.LOGN]);
        if (trim_i8_encode3 == 0) {
            throw new IllegalStateException("F encode failed");
        }
        int i5 = trim_i8_encode3 + i4;
        byte[] copyOfRange3 = Arrays.copyOfRange(bArr2, i4, i5);
        if (i5 != this.CRYPTO_SECRETKEYBYTES) {
            throw new IllegalStateException("secret key encoding failed");
        }
        int i6 = this.LOGN;
        bArr[0] = (byte) i6;
        if (FalconCodec.modq_encode(bArr, this.CRYPTO_PUBLICKEYBYTES - 1, sArr, i6) == this.CRYPTO_PUBLICKEYBYTES - 1) {
            return new byte[][]{Arrays.copyOfRange(bArr, 1, bArr.length), copyOfRange, copyOfRange2, copyOfRange3};
        }
        throw new IllegalStateException("public key encoding failed");
    }

    int crypto_sign_open(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int i = this.N;
        short[] sArr = new short[i];
        short[] sArr2 = new short[i];
        short[] sArr3 = new short[i];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        if (FalconCodec.modq_decode(sArr, this.LOGN, bArr4, this.CRYPTO_PUBLICKEYBYTES - 1) != this.CRYPTO_PUBLICKEYBYTES - 1) {
            return -1;
        }
        FalconVrfy.to_ntt_monty(sArr, this.LOGN);
        int length = bArr.length;
        int length2 = bArr3.length;
        if (length < 1 || FalconCodec.comp_decode(sArr3, this.LOGN, bArr, length) != length) {
            return -1;
        }
        sHAKEDigest.update(bArr2, 0, this.NONCELEN);
        sHAKEDigest.update(bArr3, 0, length2);
        FalconCommon.hash_to_point_vartime(sHAKEDigest, sArr2, this.LOGN);
        return FalconVrfy.verify_raw(sArr2, sArr3, sArr, this.LOGN, new short[this.N]) == 0 ? -1 : 0;
    }
}
