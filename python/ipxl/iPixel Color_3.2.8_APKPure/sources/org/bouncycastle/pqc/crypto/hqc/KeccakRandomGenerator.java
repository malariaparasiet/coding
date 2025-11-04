package org.bouncycastle.pqc.crypto.hqc;

import kotlin.UByte;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
class KeccakRandomGenerator {
    private static long[] KeccakRoundConstants = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
    protected int bitsInQueue;
    protected byte[] dataQueue;
    protected int fixedOutputLength;
    protected int rate;
    protected long[] state;

    public KeccakRandomGenerator() {
        this(288);
    }

    public KeccakRandomGenerator(int i) {
        this.state = new long[26];
        this.dataQueue = new byte[192];
        init(i);
    }

    private void init(int i) {
        if (i != 128 && i != 224 && i != 256 && i != 288 && i != 384 && i != 512) {
            throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
        }
        initSponge(1600 - (i << 1));
    }

    private void initSponge(int i) {
        if (i <= 0 || i >= 1600 || i % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        }
        this.rate = i;
        Arrays.fill(this.state, 0L);
        Arrays.fill(this.dataQueue, (byte) 0);
        this.bitsInQueue = 0;
        this.fixedOutputLength = (1600 - i) / 2;
    }

    private void keccakIncAbsorb(byte[] bArr, int i) {
        long j;
        long[] jArr;
        long j2;
        int i2 = this.rate >> 3;
        int i3 = i;
        int i4 = 0;
        while (true) {
            j = i3;
            long j3 = i2;
            if (this.state[25] + j < j3) {
                break;
            }
            int i5 = 0;
            while (true) {
                long j4 = i5;
                jArr = this.state;
                j2 = jArr[25];
                if (j4 < j3 - j2) {
                    int i6 = i5;
                    int i7 = ((int) (j2 + j4)) >> 3;
                    jArr[i7] = jArr[i7] ^ (toUnsignedLong(bArr[i6 + i4] & UByte.MAX_VALUE) << ((int) (((this.state[25] + j4) & 7) * 8)));
                    i5 = i6 + 1;
                }
            }
            i3 = (int) (j - (j3 - j2));
            i4 = (int) (i4 + (j3 - j2));
            jArr[25] = 0;
            keccakPermutation(jArr);
        }
        int i8 = 0;
        while (true) {
            long[] jArr2 = this.state;
            if (i8 >= i3) {
                jArr2[25] = jArr2[25] + j;
                return;
            }
            long j5 = i8;
            int i9 = ((int) (jArr2[25] + j5)) >> 3;
            jArr2[i9] = (toUnsignedLong(bArr[i8 + i4] & UByte.MAX_VALUE) << ((int) (((this.state[25] + j5) & 7) * 8))) ^ jArr2[i9];
            i8++;
        }
    }

    private void keccakIncFinalize(int i) {
        int i2 = this.rate >> 3;
        long[] jArr = this.state;
        int i3 = ((int) jArr[25]) >> 3;
        long j = jArr[i3];
        long unsignedLong = toUnsignedLong(i);
        long[] jArr2 = this.state;
        jArr[i3] = j ^ (unsignedLong << ((int) ((jArr2[25] & 7) * 8)));
        int i4 = i2 - 1;
        int i5 = i4 >> 3;
        jArr2[i5] = jArr2[i5] ^ (toUnsignedLong(128) << ((i4 & 7) * 8));
        this.state[25] = 0;
    }

    private void keccakIncSqueeze(byte[] bArr, int i) {
        int i2 = this.rate >> 3;
        int i3 = 0;
        while (i3 < i) {
            if (i3 >= this.state[25]) {
                break;
            }
            long j = i2;
            bArr[i3] = (byte) (r9[(int) (((j - r10) + r7) >> 3)] >> ((int) ((7 & ((j - r10) + r7)) * 8)));
            i3++;
        }
        int i4 = i - i3;
        long[] jArr = this.state;
        jArr[25] = jArr[25] - i3;
        while (i4 > 0) {
            keccakPermutation(this.state);
            int i5 = 0;
            while (i5 < i4 && i5 < i2) {
                bArr[i3 + i5] = (byte) (this.state[i5 >> 3] >> ((i5 & 7) * 8));
                i5++;
            }
            i3 += i5;
            i4 -= i5;
            this.state[25] = i2 - i5;
        }
    }

    private static void keccakPermutation(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        char c = 2;
        long j3 = jArr[2];
        char c2 = 3;
        long j4 = jArr[3];
        char c3 = 4;
        long j5 = jArr[4];
        char c4 = 5;
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = jArr[8];
        long j10 = jArr[9];
        long j11 = jArr[10];
        long j12 = jArr[11];
        long j13 = jArr[12];
        long j14 = jArr[13];
        long j15 = jArr[14];
        long j16 = jArr[15];
        long j17 = jArr[16];
        long j18 = jArr[17];
        long j19 = jArr[18];
        long j20 = jArr[19];
        long j21 = jArr[20];
        long j22 = jArr[21];
        long j23 = jArr[22];
        long j24 = jArr[23];
        int i = 24;
        long j25 = jArr[24];
        int i2 = 0;
        while (i2 < i) {
            long j26 = (((j ^ j6) ^ j11) ^ j16) ^ j21;
            long j27 = (((j2 ^ j7) ^ j12) ^ j17) ^ j22;
            long j28 = (((j3 ^ j8) ^ j13) ^ j18) ^ j23;
            long j29 = (((j4 ^ j9) ^ j14) ^ j19) ^ j24;
            long j30 = (((j5 ^ j10) ^ j15) ^ j20) ^ j25;
            long j31 = ((j27 << 1) | (j27 >>> (-1))) ^ j30;
            long j32 = ((j28 << 1) | (j28 >>> (-1))) ^ j26;
            long j33 = ((j29 << 1) | (j29 >>> (-1))) ^ j27;
            long j34 = ((j30 << 1) | (j30 >>> (-1))) ^ j28;
            long j35 = ((j26 << 1) | (j26 >>> (-1))) ^ j29;
            long j36 = j ^ j31;
            long j37 = j6 ^ j31;
            long j38 = j11 ^ j31;
            long j39 = j16 ^ j31;
            long j40 = j21 ^ j31;
            long j41 = j2 ^ j32;
            long j42 = j7 ^ j32;
            long j43 = j12 ^ j32;
            long j44 = j17 ^ j32;
            long j45 = j22 ^ j32;
            long j46 = j3 ^ j33;
            long j47 = j8 ^ j33;
            long j48 = j13 ^ j33;
            long j49 = j18 ^ j33;
            long j50 = j23 ^ j33;
            long j51 = j4 ^ j34;
            long j52 = j9 ^ j34;
            long j53 = j14 ^ j34;
            long j54 = j19 ^ j34;
            long j55 = j24 ^ j34;
            long j56 = j5 ^ j35;
            long j57 = j10 ^ j35;
            long j58 = j15 ^ j35;
            long j59 = j20 ^ j35;
            long j60 = j25 ^ j35;
            long j61 = (j41 << 1) | (j41 >>> 63);
            char c5 = c;
            long j62 = (j42 << 44) | (j42 >>> 20);
            char c6 = c2;
            long j63 = (j57 << 20) | (j57 >>> 44);
            char c7 = c3;
            long j64 = (j50 << 61) | (j50 >>> c6);
            int i3 = i;
            long j65 = (j58 << 39) | (j58 >>> 25);
            int i4 = i2;
            long j66 = (j40 << 18) | (j40 >>> 46);
            long j67 = (j46 << 62) | (j46 >>> c5);
            long j68 = (j48 << 43) | (j48 >>> 21);
            long j69 = (j53 << 25) | (j53 >>> 39);
            long j70 = (j59 << 8) | (j59 >>> 56);
            long j71 = (j55 << 56) | (j55 >>> 8);
            long j72 = (j39 << 41) | (j39 >>> 23);
            long j73 = (j56 << 27) | (j56 >>> 37);
            long j74 = (j60 << 14) | (j60 >>> 50);
            long j75 = (j45 << c5) | (j45 >>> 62);
            long j76 = (j52 << 55) | (j52 >>> 9);
            long j77 = (j44 << 45) | (j44 >>> 19);
            long j78 = (j37 << 36) | (j37 >>> 28);
            long j79 = (j51 << 28) | (j51 >>> 36);
            long j80 = (j54 << 21) | (j54 >>> 43);
            long j81 = (j49 << 15) | (j49 >>> 49);
            long j82 = (j43 << 10) | (j43 >>> 54);
            long j83 = (j47 << 6) | (j47 >>> 58);
            long j84 = (j38 << c6) | (j38 >>> 61);
            long j85 = j36 ^ ((~j62) & j68);
            long j86 = ((~j68) & j80) ^ j62;
            long j87 = ((~j80) & j74) ^ j68;
            long j88 = j80 ^ ((~j74) & j36);
            j5 = j74 ^ (j62 & (~j36));
            long j89 = j79 ^ ((~j63) & j84);
            long j90 = ((~j84) & j77) ^ j63;
            long j91 = ((~j77) & j64) ^ j84;
            long j92 = j77 ^ ((~j64) & j79);
            long j93 = ((~j79) & j63) ^ j64;
            j11 = j61 ^ ((~j83) & j69);
            long j94 = ((~j69) & j70) ^ j83;
            long j95 = ((~j70) & j66) ^ j69;
            long j96 = j70 ^ ((~j66) & j61);
            j15 = j66 ^ ((~j61) & j83);
            long j97 = j73 ^ ((~j78) & j82);
            long j98 = j78 ^ ((~j82) & j81);
            long j99 = ((~j81) & j71) ^ j82;
            long j100 = j81 ^ ((~j71) & j73);
            long j101 = ((~j73) & j78) ^ j71;
            long j102 = j67 ^ ((~j76) & j65);
            long j103 = ((~j65) & j72) ^ j76;
            long j104 = j65 ^ ((~j72) & j75);
            j24 = j72 ^ ((~j75) & j67);
            long j105 = j75 ^ ((~j67) & j76);
            long j106 = j85 ^ KeccakRoundConstants[i4];
            j17 = j98;
            j8 = j91;
            j22 = j103;
            j21 = j102;
            j23 = j104;
            j10 = j93;
            j9 = j92;
            j18 = j99;
            j14 = j96;
            j20 = j101;
            j7 = j90;
            j6 = j89;
            j13 = j95;
            j12 = j94;
            c3 = c7;
            c = c5;
            c4 = c4;
            j19 = j100;
            j16 = j97;
            c2 = c6;
            j3 = j87;
            j2 = j86;
            j25 = j105;
            j = j106;
            i2 = i4 + 1;
            j4 = j88;
            i = i3;
        }
        jArr[0] = j;
        jArr[1] = j2;
        jArr[c] = j3;
        jArr[c2] = j4;
        jArr[c3] = j5;
        jArr[c4] = j6;
        jArr[6] = j7;
        jArr[7] = j8;
        jArr[8] = j9;
        jArr[9] = j10;
        jArr[10] = j11;
        jArr[11] = j12;
        jArr[12] = j13;
        jArr[13] = j14;
        jArr[14] = j15;
        jArr[15] = j16;
        jArr[16] = j17;
        jArr[17] = j18;
        jArr[18] = j19;
        jArr[19] = j20;
        jArr[20] = j21;
        jArr[21] = j22;
        jArr[22] = j23;
        jArr[23] = j24;
        jArr[i] = j25;
    }

    private static long toUnsignedLong(int i) {
        return i & 4294967295L;
    }

    public void SHAKE256_512_ds(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        Arrays.fill(this.state, 0L);
        keccakIncAbsorb(bArr2, i);
        keccakIncAbsorb(bArr3, bArr3.length);
        keccakIncFinalize(31);
        keccakIncSqueeze(bArr, 64);
    }

    public void expandSeed(byte[] bArr, int i) {
        int i2 = i & 7;
        int i3 = i - i2;
        keccakIncSqueeze(bArr, i3);
        if (i2 != 0) {
            byte[] bArr2 = new byte[8];
            keccakIncSqueeze(bArr2, 8);
            System.arraycopy(bArr2, 0, bArr, i3, i2);
        }
    }

    public void randomGeneratorInit(byte[] bArr, byte[] bArr2, int i, int i2) {
        keccakIncAbsorb(bArr, i);
        keccakIncAbsorb(bArr2, i2);
        keccakIncAbsorb(new byte[]{1}, 1);
        keccakIncFinalize(31);
    }

    public void seedExpanderInit(byte[] bArr, int i) {
        keccakIncAbsorb(bArr, i);
        keccakIncAbsorb(new byte[]{2}, 1);
        keccakIncFinalize(31);
    }

    public void squeeze(byte[] bArr, int i) {
        keccakIncSqueeze(bArr, i);
    }
}
