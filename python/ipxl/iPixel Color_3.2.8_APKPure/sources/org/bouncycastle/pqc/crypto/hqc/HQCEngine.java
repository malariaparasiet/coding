package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
class HQCEngine {
    private int K_BYTE;
    private int K_BYTE_64;
    private int N1N2_BYTE;
    private int N1N2_BYTE_64;
    private int N1_BYTE;
    private int N1_BYTE_64;
    private int N_BYTE;
    private int N_BYTE_64;
    private long RED_MASK;
    private int delta;
    private int fft;
    private int g;
    private int[] generatorPoly;
    private GF2PolynomialCalculator gfCalculator;
    private int k;
    private int mulParam;
    private int n;
    private int n1;
    private int n1n2;
    private int n2;
    private int rejectionThreshold;
    private int w;
    private int we;
    private int wr;
    private int SEED_SIZE = 40;
    private byte G_FCT_DOMAIN = 3;
    private byte K_FCT_DOMAIN = 4;
    private int GF_POLY_WT = 5;
    private int GF_POLY_M2 = 4;
    private int SALT_SIZE_BYTES = 16;
    private int SALT_SIZE_64 = 2;
    private int SHA512_BYTES = 64;

    public HQCEngine(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        this.n = i;
        this.k = i4;
        this.delta = i6;
        this.w = i7;
        this.wr = i8;
        this.we = i9;
        this.n1 = i2;
        this.n2 = i3;
        int i12 = i2 * i3;
        this.n1n2 = i12;
        this.generatorPoly = iArr;
        this.g = i5;
        this.rejectionThreshold = i10;
        this.fft = i11;
        this.mulParam = (int) Math.ceil(i3 / 128);
        this.N_BYTE = Utils.getByteSizeFromBitSize(i);
        this.K_BYTE = i4;
        this.N_BYTE_64 = Utils.getByte64SizeFromBitSize(i);
        this.K_BYTE_64 = Utils.getByteSizeFromBitSize(i4);
        this.N1_BYTE_64 = Utils.getByteSizeFromBitSize(i2);
        this.N1N2_BYTE_64 = Utils.getByte64SizeFromBitSize(i12);
        this.N1N2_BYTE = Utils.getByteSizeFromBitSize(i12);
        this.N1_BYTE = Utils.getByteSizeFromBitSize(i2);
        this.RED_MASK = (1 << ((int) (i % 64))) - 1;
        this.gfCalculator = new GF2PolynomialCalculator(this.N_BYTE_64, i, this.RED_MASK);
    }

    private int decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, long[] jArr) {
        long[] jArr2 = new long[this.N_BYTE_64];
        Utils.fromByteArrayToLongArray(jArr2, bArr4);
        int i = this.N1N2_BYTE_64;
        long[] jArr3 = new long[i];
        Utils.fromByteArrayToLongArray(jArr3, bArr5);
        long[] jArr4 = new long[this.N_BYTE_64];
        System.arraycopy(jArr3, 0, jArr4, 0, i);
        long[] jArr5 = new long[this.N_BYTE_64];
        this.gfCalculator.multLongs(jArr5, jArr, jArr2);
        GF2PolynomialCalculator.addLongs(jArr5, jArr5, jArr4);
        int i2 = this.n1;
        byte[] bArr6 = new byte[i2];
        ReedMuller.decode(bArr6, jArr5, i2, this.mulParam);
        ReedSolomon.decode(bArr2, bArr6, this.n1, this.fft, this.delta, this.k, this.g);
        System.arraycopy(bArr2, 0, bArr, 0, bArr.length);
        return 0;
    }

    private void encrypt(byte[] bArr, long[] jArr, long[] jArr2, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        KeccakRandomGenerator keccakRandomGenerator = new KeccakRandomGenerator(256);
        keccakRandomGenerator.seedExpanderInit(bArr4, this.SEED_SIZE);
        int i = this.N_BYTE_64;
        long[] jArr3 = new long[i];
        long[] jArr4 = new long[i];
        long[] jArr5 = new long[i];
        generateRandomFixedWeight(jArr5, keccakRandomGenerator, this.wr);
        generateRandomFixedWeight(jArr3, keccakRandomGenerator, this.we);
        generateRandomFixedWeight(jArr4, keccakRandomGenerator, this.wr);
        long[] jArr6 = new long[this.N_BYTE_64];
        this.gfCalculator.multLongs(jArr6, jArr5, jArr2);
        GF2PolynomialCalculator.addLongs(jArr6, jArr6, jArr4);
        Utils.fromLongArrayToByteArray(bArr, jArr6);
        int i2 = this.n1;
        byte[] bArr5 = new byte[i2];
        int i3 = this.N1N2_BYTE_64;
        long[] jArr7 = new long[i3];
        long[] jArr8 = new long[this.N_BYTE_64];
        ReedSolomon.encode(bArr5, bArr3, this.K_BYTE * 8, i2, this.k, this.g, this.generatorPoly);
        ReedMuller.encode(jArr7, bArr5, this.n1, this.mulParam);
        System.arraycopy(jArr7, 0, jArr8, 0, i3);
        long[] jArr9 = new long[this.N_BYTE_64];
        Utils.fromByteArrayToLongArray(jArr9, bArr2);
        long[] jArr10 = new long[this.N_BYTE_64];
        this.gfCalculator.multLongs(jArr10, jArr5, jArr9);
        GF2PolynomialCalculator.addLongs(jArr10, jArr10, jArr8);
        GF2PolynomialCalculator.addLongs(jArr10, jArr10, jArr3);
        int i4 = this.n1n2;
        int i5 = this.n;
        int i6 = this.N1N2_BYTE_64;
        Utils.resizeArray(jArr, i4, jArr10, i5, i6, i6);
    }

    private void extractCiphertexts(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        System.arraycopy(bArr4, 0, bArr, 0, bArr.length);
        System.arraycopy(bArr4, bArr.length, bArr2, 0, bArr2.length);
        System.arraycopy(bArr4, bArr.length + bArr2.length, bArr3, 0, bArr3.length);
    }

    private void extractKeysFromSecretKeys(long[] jArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.SEED_SIZE;
        byte[] bArr4 = new byte[i];
        System.arraycopy(bArr3, 0, bArr4, 0, i);
        System.arraycopy(bArr3, this.SEED_SIZE, bArr, 0, this.K_BYTE);
        KeccakRandomGenerator keccakRandomGenerator = new KeccakRandomGenerator(256);
        keccakRandomGenerator.seedExpanderInit(bArr4, i);
        generateRandomFixedWeight(jArr, keccakRandomGenerator, this.w);
        System.arraycopy(bArr3, this.SEED_SIZE + this.K_BYTE, bArr2, 0, bArr2.length);
    }

    private void extractPublicKeys(long[] jArr, byte[] bArr, byte[] bArr2) {
        int i = this.SEED_SIZE;
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr2, 0, bArr3, 0, i);
        KeccakRandomGenerator keccakRandomGenerator = new KeccakRandomGenerator(256);
        keccakRandomGenerator.seedExpanderInit(bArr3, i);
        long[] jArr2 = new long[this.N_BYTE_64];
        generatePublicKeyH(jArr2, keccakRandomGenerator);
        System.arraycopy(jArr2, 0, jArr, 0, jArr.length);
        System.arraycopy(bArr2, 40, bArr, 0, bArr.length);
    }

    private void generateRandomFixedWeight(long[] jArr, KeccakRandomGenerator keccakRandomGenerator, int i) {
        int i2 = this.wr;
        byte[] bArr = new byte[i2 * 4];
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        long[] jArr2 = new long[i2];
        keccakRandomGenerator.expandSeed(bArr, i * 4);
        Pack.littleEndianToInt(bArr, 0, new int[i2], 0, i2);
        for (int i3 = 0; i3 < i; i3++) {
            iArr[i3] = (int) (i3 + ((r1[i3] & 4294967295L) % (this.n - i3)));
        }
        for (int i4 = i - 1; i4 >= 0; i4--) {
            int i5 = 0;
            for (int i6 = i4 + 1; i6 < i; i6++) {
                if (iArr[i6] == iArr[i4]) {
                    i5 = 1;
                }
            }
            int i7 = -i5;
            iArr[i4] = ((~i7) & iArr[i4]) ^ (i7 & i4);
        }
        for (int i8 = 0; i8 < i; i8++) {
            iArr2[i8] = iArr[i8] >>> 6;
            jArr2[i8] = 1 << (iArr[i8] & 63);
        }
        for (int i9 = 0; i9 < this.N_BYTE_64; i9++) {
            long j = 0;
            for (int i10 = 0; i10 < i; i10++) {
                int i11 = i9 - iArr2[i10];
                j |= (-(((i11 | (-i11)) >>> 31) ^ 1)) & jArr2[i10];
            }
            jArr[i9] = j | jArr[i9];
        }
    }

    public int decaps(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long[] jArr = new long[this.N_BYTE_64];
        byte[] bArr4 = new byte[this.N_BYTE + 40];
        byte[] bArr5 = new byte[this.K_BYTE];
        extractKeysFromSecretKeys(jArr, bArr5, bArr4, bArr3);
        byte[] bArr6 = new byte[this.N_BYTE];
        byte[] bArr7 = new byte[this.N1N2_BYTE];
        byte[] bArr8 = new byte[this.SALT_SIZE_BYTES];
        extractCiphertexts(bArr6, bArr7, bArr8, bArr2);
        int i = this.k;
        byte[] bArr9 = new byte[i];
        int decrypt = decrypt(bArr9, bArr9, bArr5, bArr6, bArr7, jArr);
        byte[] bArr10 = new byte[this.SHA512_BYTES];
        int i2 = this.K_BYTE;
        int i3 = this.SALT_SIZE_BYTES;
        int i4 = i2 + (i3 * 2) + i3;
        byte[] bArr11 = new byte[i4];
        System.arraycopy(bArr9, 0, bArr11, 0, i);
        System.arraycopy(bArr4, 0, bArr11, this.K_BYTE, this.SALT_SIZE_BYTES * 2);
        int i5 = this.K_BYTE;
        int i6 = this.SALT_SIZE_BYTES;
        System.arraycopy(bArr8, 0, bArr11, i5 + (i6 * 2), i6);
        KeccakRandomGenerator keccakRandomGenerator = new KeccakRandomGenerator(256);
        keccakRandomGenerator.SHAKE256_512_ds(bArr10, bArr11, i4, new byte[]{this.G_FCT_DOMAIN});
        long[] jArr2 = new long[this.N_BYTE_64];
        byte[] bArr12 = new byte[this.N_BYTE];
        extractPublicKeys(jArr2, bArr12, bArr4);
        byte[] bArr13 = new byte[this.N_BYTE];
        byte[] bArr14 = new byte[this.N1N2_BYTE];
        long[] jArr3 = new long[this.N1N2_BYTE_64];
        encrypt(bArr13, jArr3, jArr2, bArr12, bArr9, bArr10);
        Utils.fromLongArrayToByteArray(bArr14, jArr3);
        int i7 = this.K_BYTE + this.N_BYTE + this.N1N2_BYTE;
        byte[] bArr15 = new byte[i7];
        if (!Arrays.constantTimeAreEqual(bArr6, bArr13)) {
            decrypt = 1;
        }
        if (!Arrays.constantTimeAreEqual(bArr7, bArr14)) {
            decrypt = 1;
        }
        int i8 = decrypt - 1;
        int i9 = 0;
        while (true) {
            int i10 = this.K_BYTE;
            if (i9 >= i10) {
                System.arraycopy(bArr6, 0, bArr15, i10, this.N_BYTE);
                System.arraycopy(bArr7, 0, bArr15, this.K_BYTE + this.N_BYTE, this.N1N2_BYTE);
                keccakRandomGenerator.SHAKE256_512_ds(bArr, bArr15, i7, new byte[]{this.K_FCT_DOMAIN});
                return -i8;
            }
            bArr15[i9] = (byte) (((bArr9[i9] & i8) ^ (bArr5[i9] & (~i8))) & 255);
            i9++;
        }
    }

    public void encaps(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        int i = this.K_BYTE;
        byte[] bArr7 = new byte[i];
        byte[] bArr8 = new byte[this.SEED_SIZE];
        KeccakRandomGenerator keccakRandomGenerator = new KeccakRandomGenerator(256);
        keccakRandomGenerator.randomGeneratorInit(bArr5, null, bArr5.length, 0);
        keccakRandomGenerator.squeeze(bArr8, 40);
        int i2 = this.K_BYTE;
        keccakRandomGenerator.squeeze(new byte[i2], i2);
        keccakRandomGenerator.squeeze(new byte[this.SEED_SIZE], 40);
        keccakRandomGenerator.squeeze(bArr7, this.K_BYTE);
        byte[] bArr9 = new byte[this.SHA512_BYTES];
        int i3 = this.K_BYTE;
        int i4 = this.SALT_SIZE_BYTES;
        int i5 = i3 + (i4 * 2) + i4;
        byte[] bArr10 = new byte[i5];
        keccakRandomGenerator.squeeze(bArr6, i4);
        System.arraycopy(bArr7, 0, bArr10, 0, i);
        System.arraycopy(bArr4, 0, bArr10, this.K_BYTE, this.SALT_SIZE_BYTES * 2);
        int i6 = this.K_BYTE;
        int i7 = this.SALT_SIZE_BYTES;
        System.arraycopy(bArr6, 0, bArr10, i6 + (i7 * 2), i7);
        KeccakRandomGenerator keccakRandomGenerator2 = new KeccakRandomGenerator(256);
        keccakRandomGenerator2.SHAKE256_512_ds(bArr9, bArr10, i5, new byte[]{this.G_FCT_DOMAIN});
        long[] jArr = new long[this.N_BYTE_64];
        byte[] bArr11 = new byte[this.N_BYTE];
        extractPublicKeys(jArr, bArr11, bArr4);
        long[] jArr2 = new long[this.N1N2_BYTE_64];
        encrypt(bArr, jArr2, jArr, bArr11, bArr7, bArr9);
        Utils.fromLongArrayToByteArray(bArr2, jArr2);
        byte[] concatenate = Arrays.concatenate(bArr7, bArr, bArr2);
        keccakRandomGenerator2.SHAKE256_512_ds(bArr3, concatenate, concatenate.length, new byte[]{this.K_FCT_DOMAIN});
    }

    public void genKeyPair(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.SEED_SIZE;
        byte[] bArr4 = new byte[i];
        byte[] bArr5 = new byte[this.K_BYTE];
        KeccakRandomGenerator keccakRandomGenerator = new KeccakRandomGenerator(256);
        keccakRandomGenerator.randomGeneratorInit(bArr3, null, bArr3.length, 0);
        keccakRandomGenerator.squeeze(bArr4, 40);
        keccakRandomGenerator.squeeze(bArr5, this.K_BYTE);
        KeccakRandomGenerator keccakRandomGenerator2 = new KeccakRandomGenerator(256);
        keccakRandomGenerator2.seedExpanderInit(bArr4, i);
        int i2 = this.N_BYTE_64;
        long[] jArr = new long[i2];
        long[] jArr2 = new long[i2];
        generateRandomFixedWeight(jArr2, keccakRandomGenerator2, this.w);
        generateRandomFixedWeight(jArr, keccakRandomGenerator2, this.w);
        int i3 = this.SEED_SIZE;
        byte[] bArr6 = new byte[i3];
        keccakRandomGenerator.squeeze(bArr6, 40);
        KeccakRandomGenerator keccakRandomGenerator3 = new KeccakRandomGenerator(256);
        keccakRandomGenerator3.seedExpanderInit(bArr6, i3);
        long[] jArr3 = new long[this.N_BYTE_64];
        generatePublicKeyH(jArr3, keccakRandomGenerator3);
        long[] jArr4 = new long[this.N_BYTE_64];
        this.gfCalculator.multLongs(jArr4, jArr2, jArr3);
        GF2PolynomialCalculator.addLongs(jArr4, jArr4, jArr);
        byte[] bArr7 = new byte[this.N_BYTE];
        Utils.fromLongArrayToByteArray(bArr7, jArr4);
        byte[] concatenate = Arrays.concatenate(bArr6, bArr7);
        byte[] concatenate2 = Arrays.concatenate(bArr4, bArr5, concatenate);
        System.arraycopy(concatenate, 0, bArr, 0, concatenate.length);
        System.arraycopy(concatenate2, 0, bArr2, 0, concatenate2.length);
    }

    void generatePublicKeyH(long[] jArr, KeccakRandomGenerator keccakRandomGenerator) {
        int i = this.N_BYTE;
        byte[] bArr = new byte[i];
        keccakRandomGenerator.expandSeed(bArr, i);
        long[] jArr2 = new long[this.N_BYTE_64];
        Utils.fromByteArrayToLongArray(jArr2, bArr);
        int i2 = this.N_BYTE_64 - 1;
        jArr2[i2] = jArr2[i2] & Utils.bitMask(this.n, 64L);
        System.arraycopy(jArr2, 0, jArr, 0, jArr.length);
    }

    int getSessionKeySize() {
        return this.SHA512_BYTES;
    }
}
