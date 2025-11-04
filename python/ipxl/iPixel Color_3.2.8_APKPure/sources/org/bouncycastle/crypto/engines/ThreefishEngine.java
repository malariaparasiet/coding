package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.TweakableBlockCipherParameters;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class ThreefishEngine implements BlockCipher {
    public static final int BLOCKSIZE_1024 = 1024;
    public static final int BLOCKSIZE_256 = 256;
    public static final int BLOCKSIZE_512 = 512;
    private static final long C_240 = 2004413935125273122L;
    private static final int MAX_ROUNDS = 80;
    private static int[] MOD17 = null;
    private static int[] MOD3 = null;
    private static int[] MOD5 = null;
    private static int[] MOD9 = null;
    private static final int ROUNDS_1024 = 80;
    private static final int ROUNDS_256 = 72;
    private static final int ROUNDS_512 = 72;
    private static final int TWEAK_SIZE_BYTES = 16;
    private static final int TWEAK_SIZE_WORDS = 2;
    private int blocksizeBytes;
    private int blocksizeWords;
    private ThreefishCipher cipher;
    private long[] currentBlock;
    private boolean forEncryption;
    private long[] kw;
    private long[] t = new long[5];

    private static final class Threefish1024Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 24;
        private static final int ROTATION_0_1 = 13;
        private static final int ROTATION_0_2 = 8;
        private static final int ROTATION_0_3 = 47;
        private static final int ROTATION_0_4 = 8;
        private static final int ROTATION_0_5 = 17;
        private static final int ROTATION_0_6 = 22;
        private static final int ROTATION_0_7 = 37;
        private static final int ROTATION_1_0 = 38;
        private static final int ROTATION_1_1 = 19;
        private static final int ROTATION_1_2 = 10;
        private static final int ROTATION_1_3 = 55;
        private static final int ROTATION_1_4 = 49;
        private static final int ROTATION_1_5 = 18;
        private static final int ROTATION_1_6 = 23;
        private static final int ROTATION_1_7 = 52;
        private static final int ROTATION_2_0 = 33;
        private static final int ROTATION_2_1 = 4;
        private static final int ROTATION_2_2 = 51;
        private static final int ROTATION_2_3 = 13;
        private static final int ROTATION_2_4 = 34;
        private static final int ROTATION_2_5 = 41;
        private static final int ROTATION_2_6 = 59;
        private static final int ROTATION_2_7 = 17;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 20;
        private static final int ROTATION_3_2 = 48;
        private static final int ROTATION_3_3 = 41;
        private static final int ROTATION_3_4 = 47;
        private static final int ROTATION_3_5 = 28;
        private static final int ROTATION_3_6 = 16;
        private static final int ROTATION_3_7 = 25;
        private static final int ROTATION_4_0 = 41;
        private static final int ROTATION_4_1 = 9;
        private static final int ROTATION_4_2 = 37;
        private static final int ROTATION_4_3 = 31;
        private static final int ROTATION_4_4 = 12;
        private static final int ROTATION_4_5 = 47;
        private static final int ROTATION_4_6 = 44;
        private static final int ROTATION_4_7 = 30;
        private static final int ROTATION_5_0 = 16;
        private static final int ROTATION_5_1 = 34;
        private static final int ROTATION_5_2 = 56;
        private static final int ROTATION_5_3 = 51;
        private static final int ROTATION_5_4 = 4;
        private static final int ROTATION_5_5 = 53;
        private static final int ROTATION_5_6 = 42;
        private static final int ROTATION_5_7 = 41;
        private static final int ROTATION_6_0 = 31;
        private static final int ROTATION_6_1 = 44;
        private static final int ROTATION_6_2 = 47;
        private static final int ROTATION_6_3 = 46;
        private static final int ROTATION_6_4 = 19;
        private static final int ROTATION_6_5 = 42;
        private static final int ROTATION_6_6 = 44;
        private static final int ROTATION_6_7 = 25;
        private static final int ROTATION_7_0 = 9;
        private static final int ROTATION_7_1 = 48;
        private static final int ROTATION_7_2 = 35;
        private static final int ROTATION_7_3 = 52;
        private static final int ROTATION_7_4 = 23;
        private static final int ROTATION_7_5 = 31;
        private static final int ROTATION_7_6 = 37;
        private static final int ROTATION_7_7 = 20;

        public Threefish1024Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            int i2 = 9;
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long j14 = jArr[13];
            long j15 = jArr[14];
            long j16 = jArr[15];
            int i3 = 19;
            while (i3 >= i) {
                int i4 = iArr[i3];
                int i5 = iArr2[i3];
                int i6 = i4 + 1;
                long j17 = j - jArr3[i6];
                int i7 = i4 + 2;
                long j18 = j2 - jArr3[i7];
                int i8 = i4 + 3;
                long j19 = j3 - jArr3[i8];
                int i9 = i4 + 4;
                long j20 = j4 - jArr3[i9];
                int i10 = i4 + 5;
                int i11 = i;
                long j21 = j5 - jArr3[i10];
                int i12 = i4 + 6;
                long[] jArr5 = jArr3;
                long j22 = j6 - jArr3[i12];
                int i13 = i4 + 7;
                long[] jArr6 = jArr4;
                long j23 = j7 - jArr5[i13];
                int i14 = i4 + 8;
                int[] iArr3 = iArr2;
                long j24 = j8 - jArr5[i14];
                int i15 = i4 + 9;
                long j25 = j9 - jArr5[i15];
                int i16 = i4 + 10;
                long j26 = j10 - jArr5[i16];
                int i17 = i4 + 11;
                long j27 = j11 - jArr5[i17];
                int i18 = i4 + 12;
                long j28 = j12 - jArr5[i18];
                int i19 = i4 + 13;
                long j29 = j13 - jArr5[i19];
                int i20 = i4 + 14;
                int i21 = i5 + 1;
                long j30 = j14 - (jArr5[i20] + jArr6[i21]);
                int i22 = i4 + 15;
                long j31 = j15 - (jArr5[i22] + jArr6[i5 + 2]);
                long j32 = i3;
                long xorRotr = ThreefishEngine.xorRotr(j16 - ((jArr5[i4 + 16] + j32) + 1), i2, j17);
                long j33 = j17 - xorRotr;
                long xorRotr2 = ThreefishEngine.xorRotr(j28, 48, j19);
                long j34 = j19 - xorRotr2;
                long xorRotr3 = ThreefishEngine.xorRotr(j30, 35, j23);
                long j35 = j23 - xorRotr3;
                long xorRotr4 = ThreefishEngine.xorRotr(j26, 52, j21);
                long j36 = j21 - xorRotr4;
                long xorRotr5 = ThreefishEngine.xorRotr(j18, 23, j31);
                long j37 = j31 - xorRotr5;
                long xorRotr6 = ThreefishEngine.xorRotr(j22, 31, j25);
                long j38 = j25 - xorRotr6;
                long xorRotr7 = ThreefishEngine.xorRotr(j20, 37, j27);
                long j39 = j27 - xorRotr7;
                long xorRotr8 = ThreefishEngine.xorRotr(j24, 20, j29);
                long j40 = j29 - xorRotr8;
                long xorRotr9 = ThreefishEngine.xorRotr(xorRotr8, 31, j33);
                long j41 = j33 - xorRotr9;
                long xorRotr10 = ThreefishEngine.xorRotr(xorRotr6, 44, j34);
                long j42 = j34 - xorRotr10;
                long xorRotr11 = ThreefishEngine.xorRotr(xorRotr7, 47, j36);
                long j43 = j36 - xorRotr11;
                long xorRotr12 = ThreefishEngine.xorRotr(xorRotr5, 46, j35);
                long j44 = j35 - xorRotr12;
                long xorRotr13 = ThreefishEngine.xorRotr(xorRotr, 19, j40);
                long j45 = j40 - xorRotr13;
                long xorRotr14 = ThreefishEngine.xorRotr(xorRotr3, 42, j37);
                long j46 = j37 - xorRotr14;
                long xorRotr15 = ThreefishEngine.xorRotr(xorRotr2, 44, j38);
                long j47 = j38 - xorRotr15;
                long xorRotr16 = ThreefishEngine.xorRotr(xorRotr4, 25, j39);
                long j48 = j39 - xorRotr16;
                long xorRotr17 = ThreefishEngine.xorRotr(xorRotr16, 16, j41);
                long j49 = j41 - xorRotr17;
                long xorRotr18 = ThreefishEngine.xorRotr(xorRotr14, 34, j42);
                long j50 = j42 - xorRotr18;
                long xorRotr19 = ThreefishEngine.xorRotr(xorRotr15, 56, j44);
                long j51 = j44 - xorRotr19;
                long xorRotr20 = ThreefishEngine.xorRotr(xorRotr13, 51, j43);
                long j52 = j43 - xorRotr20;
                long xorRotr21 = ThreefishEngine.xorRotr(xorRotr9, 4, j48);
                long j53 = j48 - xorRotr21;
                long xorRotr22 = ThreefishEngine.xorRotr(xorRotr11, 53, j45);
                long j54 = j45 - xorRotr22;
                long xorRotr23 = ThreefishEngine.xorRotr(xorRotr10, 42, j46);
                long j55 = j46 - xorRotr23;
                long xorRotr24 = ThreefishEngine.xorRotr(xorRotr12, 41, j47);
                long j56 = j47 - xorRotr24;
                long xorRotr25 = ThreefishEngine.xorRotr(xorRotr24, 41, j49);
                long j57 = j49 - xorRotr25;
                long xorRotr26 = ThreefishEngine.xorRotr(xorRotr22, 9, j50);
                long xorRotr27 = ThreefishEngine.xorRotr(xorRotr23, 37, j52);
                long j58 = j52 - xorRotr27;
                long xorRotr28 = ThreefishEngine.xorRotr(xorRotr21, 31, j51);
                long j59 = j51 - xorRotr28;
                long xorRotr29 = ThreefishEngine.xorRotr(xorRotr17, 12, j56);
                long j60 = j56 - xorRotr29;
                long xorRotr30 = ThreefishEngine.xorRotr(xorRotr19, 47, j53);
                long j61 = j53 - xorRotr30;
                long xorRotr31 = ThreefishEngine.xorRotr(xorRotr18, 44, j54);
                long j62 = j54 - xorRotr31;
                long xorRotr32 = ThreefishEngine.xorRotr(xorRotr20, 30, j55);
                long j63 = j55 - xorRotr32;
                long j64 = j57 - jArr5[i4];
                long j65 = xorRotr25 - jArr5[i6];
                long j66 = (j50 - xorRotr26) - jArr5[i7];
                long j67 = xorRotr26 - jArr5[i8];
                long j68 = j58 - jArr5[i9];
                long j69 = xorRotr27 - jArr5[i10];
                long j70 = j59 - jArr5[i12];
                long j71 = xorRotr28 - jArr5[i13];
                long j72 = j60 - jArr5[i14];
                long j73 = xorRotr29 - jArr5[i15];
                long j74 = j61 - jArr5[i16];
                long j75 = xorRotr30 - jArr5[i17];
                long j76 = j62 - jArr5[i18];
                long j77 = xorRotr31 - (jArr5[i19] + jArr6[i5]);
                long j78 = j63 - (jArr5[i20] + jArr6[i21]);
                long xorRotr33 = ThreefishEngine.xorRotr(xorRotr32 - (jArr5[i22] + j32), 5, j64);
                long j79 = j64 - xorRotr33;
                long xorRotr34 = ThreefishEngine.xorRotr(j75, 20, j66);
                long j80 = j66 - xorRotr34;
                long xorRotr35 = ThreefishEngine.xorRotr(j77, 48, j70);
                long j81 = j70 - xorRotr35;
                long xorRotr36 = ThreefishEngine.xorRotr(j73, 41, j68);
                long j82 = j68 - xorRotr36;
                long xorRotr37 = ThreefishEngine.xorRotr(j65, 47, j78);
                long j83 = j78 - xorRotr37;
                long xorRotr38 = ThreefishEngine.xorRotr(j69, 28, j72);
                long j84 = j72 - xorRotr38;
                long xorRotr39 = ThreefishEngine.xorRotr(j67, 16, j74);
                long j85 = j74 - xorRotr39;
                long xorRotr40 = ThreefishEngine.xorRotr(j71, 25, j76);
                long j86 = j76 - xorRotr40;
                long xorRotr41 = ThreefishEngine.xorRotr(xorRotr40, 33, j79);
                long j87 = j79 - xorRotr41;
                long xorRotr42 = ThreefishEngine.xorRotr(xorRotr38, 4, j80);
                long j88 = j80 - xorRotr42;
                long xorRotr43 = ThreefishEngine.xorRotr(xorRotr39, 51, j82);
                long j89 = j82 - xorRotr43;
                long xorRotr44 = ThreefishEngine.xorRotr(xorRotr37, 13, j81);
                long j90 = j81 - xorRotr44;
                long xorRotr45 = ThreefishEngine.xorRotr(xorRotr33, 34, j86);
                long j91 = j86 - xorRotr45;
                long xorRotr46 = ThreefishEngine.xorRotr(xorRotr35, 41, j83);
                long j92 = j83 - xorRotr46;
                long xorRotr47 = ThreefishEngine.xorRotr(xorRotr34, 59, j84);
                long j93 = j84 - xorRotr47;
                long xorRotr48 = ThreefishEngine.xorRotr(xorRotr36, 17, j85);
                long j94 = j85 - xorRotr48;
                long xorRotr49 = ThreefishEngine.xorRotr(xorRotr48, 38, j87);
                long j95 = j87 - xorRotr49;
                long xorRotr50 = ThreefishEngine.xorRotr(xorRotr46, 19, j88);
                long j96 = j88 - xorRotr50;
                long xorRotr51 = ThreefishEngine.xorRotr(xorRotr47, 10, j90);
                long j97 = j90 - xorRotr51;
                long xorRotr52 = ThreefishEngine.xorRotr(xorRotr45, 55, j89);
                long j98 = j89 - xorRotr52;
                long xorRotr53 = ThreefishEngine.xorRotr(xorRotr41, 49, j94);
                long j99 = j94 - xorRotr53;
                long xorRotr54 = ThreefishEngine.xorRotr(xorRotr43, 18, j91);
                long j100 = j91 - xorRotr54;
                long xorRotr55 = ThreefishEngine.xorRotr(xorRotr42, 23, j92);
                long j101 = j92 - xorRotr55;
                long xorRotr56 = ThreefishEngine.xorRotr(xorRotr44, 52, j93);
                long j102 = j93 - xorRotr56;
                long xorRotr57 = ThreefishEngine.xorRotr(xorRotr56, 24, j95);
                long j103 = j95 - xorRotr57;
                long xorRotr58 = ThreefishEngine.xorRotr(xorRotr54, 13, j96);
                j3 = j96 - xorRotr58;
                long xorRotr59 = ThreefishEngine.xorRotr(xorRotr55, 8, j98);
                long j104 = j98 - xorRotr59;
                long xorRotr60 = ThreefishEngine.xorRotr(xorRotr53, 47, j97);
                long j105 = j97 - xorRotr60;
                long xorRotr61 = ThreefishEngine.xorRotr(xorRotr49, 8, j102);
                long j106 = j102 - xorRotr61;
                long xorRotr62 = ThreefishEngine.xorRotr(xorRotr51, 17, j99);
                long j107 = j99 - xorRotr62;
                long xorRotr63 = ThreefishEngine.xorRotr(xorRotr50, 22, j100);
                j13 = j100 - xorRotr63;
                j16 = ThreefishEngine.xorRotr(xorRotr52, 37, j101);
                j15 = j101 - j16;
                j12 = xorRotr62;
                j11 = j107;
                jArr3 = jArr5;
                jArr4 = jArr6;
                iArr = iArr;
                j7 = j105;
                j6 = xorRotr59;
                j = j103;
                i2 = 9;
                j4 = xorRotr58;
                j14 = xorRotr63;
                j8 = xorRotr60;
                i3 -= 2;
                j10 = xorRotr61;
                j5 = j104;
                j2 = xorRotr57;
                i = i11;
                j9 = j106;
                iArr2 = iArr3;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            int i23 = i;
            long j108 = j - jArr7[0];
            long j109 = j2 - jArr7[i23];
            long j110 = j3 - jArr7[2];
            long j111 = j4 - jArr7[3];
            long j112 = j5 - jArr7[4];
            long j113 = j6 - jArr7[5];
            long j114 = j7 - jArr7[6];
            long j115 = j8 - jArr7[7];
            long j116 = j9 - jArr7[8];
            long j117 = j10 - jArr7[9];
            long j118 = j11 - jArr7[10];
            long j119 = j12 - jArr7[11];
            long j120 = j13 - jArr7[12];
            long j121 = j14 - (jArr7[13] + jArr8[0]);
            long j122 = j15 - (jArr7[14] + jArr8[i23]);
            long j123 = j16 - jArr7[15];
            jArr2[0] = j108;
            jArr2[i23] = j109;
            jArr2[2] = j110;
            jArr2[3] = j111;
            jArr2[4] = j112;
            jArr2[5] = j113;
            jArr2[6] = j114;
            jArr2[7] = j115;
            jArr2[8] = j116;
            jArr2[9] = j117;
            jArr2[10] = j118;
            jArr2[11] = j119;
            jArr2[12] = j120;
            jArr2[13] = j121;
            jArr2[14] = j122;
            jArr2[15] = j123;
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            int i = 9;
            long j13 = jArr[12];
            int i2 = 12;
            int i3 = 13;
            long j14 = jArr[13];
            long j15 = jArr[14];
            long j16 = jArr[15];
            long j17 = j + jArr3[0];
            long j18 = j2 + jArr3[1];
            long j19 = j3 + jArr3[2];
            long j20 = j4 + jArr3[3];
            long j21 = j5 + jArr3[4];
            long j22 = j6 + jArr3[5];
            long j23 = j7 + jArr3[6];
            long j24 = j8 + jArr3[7];
            long j25 = j9 + jArr3[8];
            long j26 = j10 + jArr3[9];
            long j27 = j11 + jArr3[10];
            long j28 = j12 + jArr3[11];
            long j29 = j13 + jArr3[12];
            long j30 = j14 + jArr3[13] + jArr4[0];
            long j31 = j15 + jArr3[14] + jArr4[1];
            long j32 = j22;
            long j33 = j24;
            long j34 = j26;
            long j35 = j28;
            long j36 = j30;
            long j37 = j16 + jArr3[15];
            long j38 = j21;
            long j39 = j17;
            long j40 = j20;
            int i4 = 1;
            while (i4 < 20) {
                int i5 = iArr[i4];
                int i6 = iArr2[i4];
                long j41 = j40;
                long j42 = j39 + j18;
                long rotlXor = ThreefishEngine.rotlXor(j18, 24, j42);
                long j43 = j19 + j41;
                long rotlXor2 = ThreefishEngine.rotlXor(j41, i3, j43);
                int i7 = i4;
                long j44 = j32;
                long j45 = j38 + j44;
                long rotlXor3 = ThreefishEngine.rotlXor(j44, 8, j45);
                long[] jArr5 = jArr3;
                long j46 = j33;
                long j47 = j23 + j46;
                long[] jArr6 = jArr4;
                long rotlXor4 = ThreefishEngine.rotlXor(j46, 47, j47);
                long j48 = j34;
                long j49 = j25 + j48;
                int[] iArr3 = iArr2;
                long rotlXor5 = ThreefishEngine.rotlXor(j48, 8, j49);
                long j50 = j35;
                long j51 = j27 + j50;
                long rotlXor6 = ThreefishEngine.rotlXor(j50, 17, j51);
                long j52 = j36;
                long j53 = j29 + j52;
                long rotlXor7 = ThreefishEngine.rotlXor(j52, 22, j53);
                long j54 = j37;
                long j55 = j31 + j54;
                long rotlXor8 = ThreefishEngine.rotlXor(j54, 37, j55);
                long j56 = j42 + rotlXor5;
                long rotlXor9 = ThreefishEngine.rotlXor(rotlXor5, 38, j56);
                long j57 = j43 + rotlXor7;
                long rotlXor10 = ThreefishEngine.rotlXor(rotlXor7, 19, j57);
                long j58 = j47 + rotlXor6;
                long rotlXor11 = ThreefishEngine.rotlXor(rotlXor6, 10, j58);
                long j59 = j45 + rotlXor8;
                long rotlXor12 = ThreefishEngine.rotlXor(rotlXor8, 55, j59);
                long j60 = j51 + rotlXor4;
                long rotlXor13 = ThreefishEngine.rotlXor(rotlXor4, 49, j60);
                long j61 = j53 + rotlXor2;
                long rotlXor14 = ThreefishEngine.rotlXor(rotlXor2, 18, j61);
                long j62 = j55 + rotlXor3;
                long rotlXor15 = ThreefishEngine.rotlXor(rotlXor3, 23, j62);
                long j63 = j49 + rotlXor;
                long rotlXor16 = ThreefishEngine.rotlXor(rotlXor, 52, j63);
                long j64 = j56 + rotlXor13;
                long rotlXor17 = ThreefishEngine.rotlXor(rotlXor13, 33, j64);
                long j65 = j57 + rotlXor15;
                long rotlXor18 = ThreefishEngine.rotlXor(rotlXor15, 4, j65);
                long j66 = j59 + rotlXor14;
                long rotlXor19 = ThreefishEngine.rotlXor(rotlXor14, 51, j66);
                long j67 = j58 + rotlXor16;
                long rotlXor20 = ThreefishEngine.rotlXor(rotlXor16, 13, j67);
                long j68 = j61 + rotlXor12;
                long rotlXor21 = ThreefishEngine.rotlXor(rotlXor12, 34, j68);
                long j69 = j62 + rotlXor10;
                long rotlXor22 = ThreefishEngine.rotlXor(rotlXor10, 41, j69);
                long j70 = j63 + rotlXor11;
                long rotlXor23 = ThreefishEngine.rotlXor(rotlXor11, 59, j70);
                long j71 = j60 + rotlXor9;
                long rotlXor24 = ThreefishEngine.rotlXor(rotlXor9, 17, j71);
                long j72 = j64 + rotlXor21;
                long rotlXor25 = ThreefishEngine.rotlXor(rotlXor21, 5, j72);
                long j73 = j65 + rotlXor23;
                long rotlXor26 = ThreefishEngine.rotlXor(rotlXor23, 20, j73);
                long j74 = j67 + rotlXor22;
                long rotlXor27 = ThreefishEngine.rotlXor(rotlXor22, 48, j74);
                long j75 = j66 + rotlXor24;
                long rotlXor28 = ThreefishEngine.rotlXor(rotlXor24, 41, j75);
                long j76 = j69 + rotlXor20;
                long rotlXor29 = ThreefishEngine.rotlXor(rotlXor20, 47, j76);
                long j77 = j70 + rotlXor18;
                long rotlXor30 = ThreefishEngine.rotlXor(rotlXor18, 28, j77);
                long j78 = j71 + rotlXor19;
                long rotlXor31 = ThreefishEngine.rotlXor(rotlXor19, 16, j78);
                long j79 = j68 + rotlXor17;
                long rotlXor32 = ThreefishEngine.rotlXor(rotlXor17, 25, j79);
                long j80 = j72 + jArr5[i5];
                int i8 = i5 + 1;
                long j81 = rotlXor29 + jArr5[i8];
                int i9 = i5 + 2;
                long j82 = j73 + jArr5[i9];
                int i10 = i5 + 3;
                long j83 = rotlXor31 + jArr5[i10];
                int i11 = i5 + 4;
                long j84 = j75 + jArr5[i11];
                int i12 = i5 + 5;
                long j85 = rotlXor30 + jArr5[i12];
                int i13 = i5 + 6;
                long j86 = j74 + jArr5[i13];
                int i14 = i5 + 7;
                long j87 = rotlXor32 + jArr5[i14];
                int i15 = i5 + 8;
                long j88 = j77 + jArr5[i15];
                int i16 = i5 + 9;
                long j89 = rotlXor28 + jArr5[i16];
                int i17 = i5 + 10;
                long j90 = j78 + jArr5[i17];
                int i18 = i5 + 11;
                long j91 = rotlXor26 + jArr5[i18];
                int i19 = i5 + 12;
                long j92 = j79 + jArr5[i19];
                int i20 = i5 + 13;
                long j93 = rotlXor27 + jArr5[i20] + jArr6[i6];
                int i21 = i5 + 14;
                int i22 = i6 + 1;
                long j94 = j76 + jArr5[i21] + jArr6[i22];
                int i23 = i5 + 15;
                long j95 = i7;
                long j96 = rotlXor25 + jArr5[i23] + j95;
                long j97 = j80 + j81;
                long rotlXor33 = ThreefishEngine.rotlXor(j81, 41, j97);
                long j98 = j82 + j83;
                long rotlXor34 = ThreefishEngine.rotlXor(j83, i, j98);
                long j99 = j84 + j85;
                long rotlXor35 = ThreefishEngine.rotlXor(j85, 37, j99);
                long j100 = j86 + j87;
                long rotlXor36 = ThreefishEngine.rotlXor(j87, 31, j100);
                long j101 = j88 + j89;
                long rotlXor37 = ThreefishEngine.rotlXor(j89, i2, j101);
                long j102 = j90 + j91;
                long rotlXor38 = ThreefishEngine.rotlXor(j91, 47, j102);
                long j103 = j92 + j93;
                long rotlXor39 = ThreefishEngine.rotlXor(j93, 44, j103);
                long j104 = j94 + j96;
                long rotlXor40 = ThreefishEngine.rotlXor(j96, 30, j104);
                long j105 = j97 + rotlXor37;
                long rotlXor41 = ThreefishEngine.rotlXor(rotlXor37, 16, j105);
                long j106 = j98 + rotlXor39;
                long rotlXor42 = ThreefishEngine.rotlXor(rotlXor39, 34, j106);
                long j107 = j100 + rotlXor38;
                long rotlXor43 = ThreefishEngine.rotlXor(rotlXor38, 56, j107);
                long j108 = j99 + rotlXor40;
                long rotlXor44 = ThreefishEngine.rotlXor(rotlXor40, 51, j108);
                long j109 = j102 + rotlXor36;
                long rotlXor45 = ThreefishEngine.rotlXor(rotlXor36, 4, j109);
                long j110 = j103 + rotlXor34;
                long rotlXor46 = ThreefishEngine.rotlXor(rotlXor34, 53, j110);
                long j111 = j104 + rotlXor35;
                long rotlXor47 = ThreefishEngine.rotlXor(rotlXor35, 42, j111);
                long j112 = j101 + rotlXor33;
                long rotlXor48 = ThreefishEngine.rotlXor(rotlXor33, 41, j112);
                long j113 = j105 + rotlXor45;
                long rotlXor49 = ThreefishEngine.rotlXor(rotlXor45, 31, j113);
                long j114 = j106 + rotlXor47;
                long rotlXor50 = ThreefishEngine.rotlXor(rotlXor47, 44, j114);
                long j115 = j108 + rotlXor46;
                long rotlXor51 = ThreefishEngine.rotlXor(rotlXor46, 47, j115);
                long j116 = j107 + rotlXor48;
                long rotlXor52 = ThreefishEngine.rotlXor(rotlXor48, 46, j116);
                long j117 = j110 + rotlXor44;
                long rotlXor53 = ThreefishEngine.rotlXor(rotlXor44, 19, j117);
                long j118 = j111 + rotlXor42;
                long rotlXor54 = ThreefishEngine.rotlXor(rotlXor42, 42, j118);
                long j119 = j112 + rotlXor43;
                long rotlXor55 = ThreefishEngine.rotlXor(rotlXor43, 44, j119);
                long j120 = j109 + rotlXor41;
                long rotlXor56 = ThreefishEngine.rotlXor(rotlXor41, 25, j120);
                long j121 = j113 + rotlXor53;
                long rotlXor57 = ThreefishEngine.rotlXor(rotlXor53, 9, j121);
                long j122 = j114 + rotlXor55;
                long rotlXor58 = ThreefishEngine.rotlXor(rotlXor55, 48, j122);
                long j123 = j116 + rotlXor54;
                long rotlXor59 = ThreefishEngine.rotlXor(rotlXor54, 35, j123);
                long j124 = j115 + rotlXor56;
                long rotlXor60 = ThreefishEngine.rotlXor(rotlXor56, 52, j124);
                long j125 = j118 + rotlXor52;
                long rotlXor61 = ThreefishEngine.rotlXor(rotlXor52, 23, j125);
                long j126 = j119 + rotlXor50;
                long rotlXor62 = ThreefishEngine.rotlXor(rotlXor50, 31, j126);
                long j127 = j120 + rotlXor51;
                long rotlXor63 = ThreefishEngine.rotlXor(rotlXor51, 37, j127);
                long j128 = j117 + rotlXor49;
                long rotlXor64 = ThreefishEngine.rotlXor(rotlXor49, 20, j128);
                long j129 = jArr5[i8] + j121;
                long j130 = rotlXor61 + jArr5[i9];
                long j131 = j122 + jArr5[i10];
                long j132 = rotlXor63 + jArr5[i11];
                long j133 = j124 + jArr5[i12];
                long j134 = rotlXor62 + jArr5[i13];
                long j135 = j123 + jArr5[i14];
                long j136 = rotlXor64 + jArr5[i15];
                long j137 = j126 + jArr5[i16];
                j34 = rotlXor60 + jArr5[i17];
                long j138 = j127 + jArr5[i18];
                long j139 = rotlXor58 + jArr5[i19];
                j29 = j128 + jArr5[i20];
                j36 = rotlXor59 + jArr5[i21] + jArr6[i22];
                long j140 = j125 + jArr5[i23] + jArr6[i6 + 2];
                j37 = rotlXor57 + jArr5[i5 + 16] + j95 + 1;
                j23 = j135;
                j25 = j137;
                j38 = j133;
                j18 = j130;
                j19 = j131;
                i4 = i7 + 2;
                j40 = j132;
                j27 = j138;
                j35 = j139;
                j32 = j134;
                i3 = 13;
                i = 9;
                i2 = 12;
                j33 = j136;
                j39 = j129;
                j31 = j140;
                jArr3 = jArr5;
                jArr4 = jArr6;
                iArr = iArr;
                iArr2 = iArr3;
            }
            jArr2[0] = j39;
            jArr2[1] = j18;
            jArr2[2] = j19;
            jArr2[3] = j40;
            jArr2[4] = j38;
            jArr2[5] = j32;
            jArr2[6] = j23;
            jArr2[7] = j33;
            jArr2[8] = j25;
            jArr2[9] = j34;
            jArr2[10] = j27;
            jArr2[11] = j35;
            jArr2[12] = j29;
            jArr2[13] = j36;
            jArr2[14] = j31;
            jArr2[15] = j37;
        }
    }

    private static final class Threefish256Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 14;
        private static final int ROTATION_0_1 = 16;
        private static final int ROTATION_1_0 = 52;
        private static final int ROTATION_1_1 = 57;
        private static final int ROTATION_2_0 = 23;
        private static final int ROTATION_2_1 = 40;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 37;
        private static final int ROTATION_4_0 = 25;
        private static final int ROTATION_4_1 = 33;
        private static final int ROTATION_5_0 = 46;
        private static final int ROTATION_5_1 = 12;
        private static final int ROTATION_6_0 = 58;
        private static final int ROTATION_6_1 = 22;
        private static final int ROTATION_7_0 = 32;
        private static final int ROTATION_7_1 = 32;

        public Threefish256Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            char c = 2;
            long j3 = jArr[2];
            long j4 = jArr[3];
            int i2 = 17;
            while (i2 >= i) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                int i5 = i3 + 1;
                long j5 = j - jArr3[i5];
                int i6 = i3 + 2;
                int i7 = i4 + 1;
                long j6 = j2 - (jArr3[i6] + jArr4[i7]);
                int i8 = i3 + 3;
                long j7 = j3 - (jArr3[i8] + jArr4[i4 + 2]);
                int i9 = i;
                long j8 = i2;
                char c2 = c;
                long xorRotr = ThreefishEngine.xorRotr(j4 - ((jArr3[i3 + 4] + j8) + 1), 32, j5);
                long j9 = j5 - xorRotr;
                long[] jArr5 = jArr3;
                long xorRotr2 = ThreefishEngine.xorRotr(j6, 32, j7);
                long j10 = j7 - xorRotr2;
                long[] jArr6 = jArr4;
                long xorRotr3 = ThreefishEngine.xorRotr(xorRotr2, 58, j9);
                long j11 = j9 - xorRotr3;
                long xorRotr4 = ThreefishEngine.xorRotr(xorRotr, 22, j10);
                long j12 = j10 - xorRotr4;
                long xorRotr5 = ThreefishEngine.xorRotr(xorRotr4, 46, j11);
                long j13 = j11 - xorRotr5;
                long xorRotr6 = ThreefishEngine.xorRotr(xorRotr3, 12, j12);
                long j14 = j12 - xorRotr6;
                long xorRotr7 = ThreefishEngine.xorRotr(xorRotr6, 25, j13);
                long xorRotr8 = ThreefishEngine.xorRotr(xorRotr5, 33, j14);
                long j15 = (j13 - xorRotr7) - jArr5[i3];
                long j16 = xorRotr7 - (jArr5[i5] + jArr6[i4]);
                long j17 = (j14 - xorRotr8) - (jArr5[i6] + jArr6[i7]);
                long xorRotr9 = ThreefishEngine.xorRotr(xorRotr8 - (jArr5[i8] + j8), 5, j15);
                long j18 = j15 - xorRotr9;
                long xorRotr10 = ThreefishEngine.xorRotr(j16, 37, j17);
                long j19 = j17 - xorRotr10;
                long xorRotr11 = ThreefishEngine.xorRotr(xorRotr10, 23, j18);
                long j20 = j18 - xorRotr11;
                long xorRotr12 = ThreefishEngine.xorRotr(xorRotr9, 40, j19);
                long j21 = j19 - xorRotr12;
                long xorRotr13 = ThreefishEngine.xorRotr(xorRotr12, 52, j20);
                long j22 = j20 - xorRotr13;
                long xorRotr14 = ThreefishEngine.xorRotr(xorRotr11, 57, j21);
                long j23 = j21 - xorRotr14;
                long xorRotr15 = ThreefishEngine.xorRotr(xorRotr14, 14, j22);
                j4 = ThreefishEngine.xorRotr(xorRotr13, 16, j23);
                j3 = j23 - j4;
                i2 -= 2;
                j2 = xorRotr15;
                i = i9;
                jArr3 = jArr5;
                c = c2;
                j = j22 - xorRotr15;
                jArr4 = jArr6;
                iArr = iArr;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            int i10 = i;
            char c3 = c;
            long j24 = j - jArr7[0];
            long j25 = j2 - (jArr7[i10] + jArr8[0]);
            long j26 = j3 - (jArr7[c3] + jArr8[i10]);
            long j27 = j4 - jArr7[3];
            jArr2[0] = j24;
            jArr2[i10] = j25;
            jArr2[c3] = j26;
            jArr2[3] = j27;
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            boolean z = true;
            long j2 = jArr[1];
            long j3 = jArr[2];
            char c = 3;
            long j4 = jArr[3];
            long j5 = j + jArr3[0];
            long j6 = j2 + jArr3[1] + jArr4[0];
            int i = 1;
            long j7 = j3 + jArr3[2] + jArr4[1];
            long j8 = j4 + jArr3[3];
            while (i < 18) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j9 = j5 + j6;
                boolean z2 = z;
                long rotlXor = ThreefishEngine.rotlXor(j6, 14, j9);
                long j10 = j7 + j8;
                long rotlXor2 = ThreefishEngine.rotlXor(j8, 16, j10);
                long[] jArr5 = jArr3;
                long j11 = j9 + rotlXor2;
                long rotlXor3 = ThreefishEngine.rotlXor(rotlXor2, 52, j11);
                long j12 = j10 + rotlXor;
                long rotlXor4 = ThreefishEngine.rotlXor(rotlXor, 57, j12);
                long j13 = j11 + rotlXor4;
                long rotlXor5 = ThreefishEngine.rotlXor(rotlXor4, 23, j13);
                long j14 = j12 + rotlXor3;
                long rotlXor6 = ThreefishEngine.rotlXor(rotlXor3, 40, j14);
                long j15 = j13 + rotlXor6;
                long rotlXor7 = ThreefishEngine.rotlXor(rotlXor6, 5, j15);
                long j16 = j14 + rotlXor5;
                long rotlXor8 = ThreefishEngine.rotlXor(rotlXor5, 37, j16);
                long j17 = j15 + jArr5[i2];
                int i4 = i2 + 1;
                long j18 = rotlXor8 + jArr5[i4] + jArr4[i3];
                int i5 = i2 + 2;
                int i6 = i3 + 1;
                long j19 = j16 + jArr5[i5] + jArr4[i6];
                int i7 = i2 + 3;
                long j20 = i;
                long j21 = rotlXor7 + jArr5[i7] + j20;
                long j22 = j17 + j18;
                long rotlXor9 = ThreefishEngine.rotlXor(j18, 25, j22);
                long j23 = j19 + j21;
                long rotlXor10 = ThreefishEngine.rotlXor(j21, 33, j23);
                long j24 = j22 + rotlXor10;
                long rotlXor11 = ThreefishEngine.rotlXor(rotlXor10, 46, j24);
                long j25 = j23 + rotlXor9;
                long rotlXor12 = ThreefishEngine.rotlXor(rotlXor9, 12, j25);
                long j26 = j24 + rotlXor12;
                long rotlXor13 = ThreefishEngine.rotlXor(rotlXor12, 58, j26);
                long j27 = j25 + rotlXor11;
                long rotlXor14 = ThreefishEngine.rotlXor(rotlXor11, 22, j27);
                long j28 = j26 + rotlXor14;
                long rotlXor15 = ThreefishEngine.rotlXor(rotlXor14, 32, j28);
                long j29 = j27 + rotlXor13;
                long rotlXor16 = ThreefishEngine.rotlXor(rotlXor13, 32, j29);
                long j30 = j28 + jArr5[i4];
                long j31 = rotlXor16 + jArr5[i5] + jArr4[i6];
                j7 = j29 + jArr5[i7] + jArr4[i3 + 2];
                j8 = rotlXor15 + jArr5[i2 + 4] + j20 + 1;
                i += 2;
                j6 = j31;
                z = z2;
                c = c;
                j5 = j30;
                jArr3 = jArr5;
            }
            jArr2[0] = j5;
            jArr2[z ? 1 : 0] = j6;
            jArr2[2] = j7;
            jArr2[c] = j8;
        }
    }

    private static final class Threefish512Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 46;
        private static final int ROTATION_0_1 = 36;
        private static final int ROTATION_0_2 = 19;
        private static final int ROTATION_0_3 = 37;
        private static final int ROTATION_1_0 = 33;
        private static final int ROTATION_1_1 = 27;
        private static final int ROTATION_1_2 = 14;
        private static final int ROTATION_1_3 = 42;
        private static final int ROTATION_2_0 = 17;
        private static final int ROTATION_2_1 = 49;
        private static final int ROTATION_2_2 = 36;
        private static final int ROTATION_2_3 = 39;
        private static final int ROTATION_3_0 = 44;
        private static final int ROTATION_3_1 = 9;
        private static final int ROTATION_3_2 = 54;
        private static final int ROTATION_3_3 = 56;
        private static final int ROTATION_4_0 = 39;
        private static final int ROTATION_4_1 = 30;
        private static final int ROTATION_4_2 = 34;
        private static final int ROTATION_4_3 = 24;
        private static final int ROTATION_5_0 = 13;
        private static final int ROTATION_5_1 = 50;
        private static final int ROTATION_5_2 = 10;
        private static final int ROTATION_5_3 = 17;
        private static final int ROTATION_6_0 = 25;
        private static final int ROTATION_6_1 = 29;
        private static final int ROTATION_6_2 = 39;
        private static final int ROTATION_6_3 = 43;
        private static final int ROTATION_7_0 = 8;
        private static final int ROTATION_7_1 = 35;
        private static final int ROTATION_7_2 = 56;
        private static final int ROTATION_7_3 = 22;

        protected Threefish512Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            char c = 5;
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            char c2 = 2;
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            int i2 = 17;
            while (i2 >= i) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                int i5 = i3 + 1;
                long j9 = j - jArr3[i5];
                int i6 = i3 + 2;
                long j10 = j2 - jArr3[i6];
                int i7 = i3 + 3;
                long j11 = j3 - jArr3[i7];
                int i8 = i3 + 4;
                long j12 = j4 - jArr3[i8];
                int i9 = i3 + 5;
                char c3 = c2;
                long j13 = j5 - jArr3[i9];
                int i10 = i3 + 6;
                int i11 = i4 + 1;
                int i12 = i;
                long j14 = j6 - (jArr3[i10] + jArr4[i11]);
                int i13 = i3 + 7;
                long[] jArr5 = jArr3;
                long j15 = j7 - (jArr3[i13] + jArr4[i4 + 2]);
                long[] jArr6 = jArr4;
                long j16 = i2;
                long j17 = j8 - ((jArr5[i3 + 8] + j16) + 1);
                int[] iArr3 = iArr2;
                long xorRotr = ThreefishEngine.xorRotr(j10, 8, j15);
                long j18 = j15 - xorRotr;
                long xorRotr2 = ThreefishEngine.xorRotr(j17, 35, j9);
                long j19 = j9 - xorRotr2;
                long xorRotr3 = ThreefishEngine.xorRotr(j14, 56, j11);
                long j20 = j11 - xorRotr3;
                long xorRotr4 = ThreefishEngine.xorRotr(j12, 22, j13);
                long j21 = j13 - xorRotr4;
                long xorRotr5 = ThreefishEngine.xorRotr(xorRotr, 25, j21);
                long j22 = j21 - xorRotr5;
                long xorRotr6 = ThreefishEngine.xorRotr(xorRotr4, 29, j18);
                long j23 = j18 - xorRotr6;
                long xorRotr7 = ThreefishEngine.xorRotr(xorRotr3, 39, j19);
                long j24 = j19 - xorRotr7;
                long xorRotr8 = ThreefishEngine.xorRotr(xorRotr2, 43, j20);
                long j25 = j20 - xorRotr8;
                long xorRotr9 = ThreefishEngine.xorRotr(xorRotr5, 13, j25);
                long j26 = j25 - xorRotr9;
                long xorRotr10 = ThreefishEngine.xorRotr(xorRotr8, 50, j22);
                long j27 = j22 - xorRotr10;
                long xorRotr11 = ThreefishEngine.xorRotr(xorRotr7, 10, j23);
                long j28 = j23 - xorRotr11;
                long xorRotr12 = ThreefishEngine.xorRotr(xorRotr6, 17, j24);
                long j29 = j24 - xorRotr12;
                long xorRotr13 = ThreefishEngine.xorRotr(xorRotr9, 39, j29);
                long j30 = j29 - xorRotr13;
                long xorRotr14 = ThreefishEngine.xorRotr(xorRotr12, 30, j26);
                long xorRotr15 = ThreefishEngine.xorRotr(xorRotr11, 34, j27);
                long xorRotr16 = ThreefishEngine.xorRotr(xorRotr10, 24, j28);
                long j31 = j28 - xorRotr16;
                long j32 = j30 - jArr5[i3];
                long j33 = xorRotr13 - jArr5[i5];
                long j34 = (j26 - xorRotr14) - jArr5[i6];
                long j35 = xorRotr14 - jArr5[i7];
                long j36 = (j27 - xorRotr15) - jArr5[i8];
                long j37 = xorRotr15 - (jArr5[i9] + jArr6[i4]);
                long j38 = j31 - (jArr5[i10] + jArr6[i11]);
                long j39 = xorRotr16 - (jArr5[i13] + j16);
                long xorRotr17 = ThreefishEngine.xorRotr(j33, 44, j38);
                long j40 = j38 - xorRotr17;
                long xorRotr18 = ThreefishEngine.xorRotr(j39, 9, j32);
                long j41 = j32 - xorRotr18;
                long xorRotr19 = ThreefishEngine.xorRotr(j37, 54, j34);
                long j42 = j34 - xorRotr19;
                long xorRotr20 = ThreefishEngine.xorRotr(j35, 56, j36);
                long j43 = j36 - xorRotr20;
                long xorRotr21 = ThreefishEngine.xorRotr(xorRotr17, 17, j43);
                long j44 = j43 - xorRotr21;
                long xorRotr22 = ThreefishEngine.xorRotr(xorRotr20, 49, j40);
                long j45 = j40 - xorRotr22;
                long xorRotr23 = ThreefishEngine.xorRotr(xorRotr19, 36, j41);
                long j46 = j41 - xorRotr23;
                long xorRotr24 = ThreefishEngine.xorRotr(xorRotr18, 39, j42);
                long j47 = j42 - xorRotr24;
                long xorRotr25 = ThreefishEngine.xorRotr(xorRotr21, 33, j47);
                long j48 = j47 - xorRotr25;
                long xorRotr26 = ThreefishEngine.xorRotr(xorRotr24, 27, j44);
                long j49 = j44 - xorRotr26;
                long xorRotr27 = ThreefishEngine.xorRotr(xorRotr23, 14, j45);
                long j50 = j45 - xorRotr27;
                long xorRotr28 = ThreefishEngine.xorRotr(xorRotr22, 42, j46);
                long j51 = j46 - xorRotr28;
                long xorRotr29 = ThreefishEngine.xorRotr(xorRotr25, 46, j51);
                j4 = ThreefishEngine.xorRotr(xorRotr28, 36, j48);
                j6 = ThreefishEngine.xorRotr(xorRotr27, 19, j49);
                long j52 = j49 - j6;
                j8 = ThreefishEngine.xorRotr(xorRotr26, 37, j50);
                j7 = j50 - j8;
                j3 = j48 - j4;
                j2 = xorRotr29;
                i2 -= 2;
                j = j51 - xorRotr29;
                i = i12;
                jArr3 = jArr5;
                c = c;
                c2 = c3;
                iArr2 = iArr3;
                j5 = j52;
                jArr4 = jArr6;
                iArr = iArr;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            char c4 = c;
            int i14 = i;
            char c5 = c2;
            long j53 = j - jArr7[0];
            long j54 = j2 - jArr7[i14];
            long j55 = j3 - jArr7[c5];
            long j56 = j4 - jArr7[3];
            long j57 = j5 - jArr7[4];
            long j58 = j6 - (jArr7[c4] + jArr8[0]);
            long j59 = j7 - (jArr7[6] + jArr8[i14]);
            long j60 = j8 - jArr7[7];
            jArr2[0] = j53;
            jArr2[i14] = j54;
            jArr2[c5] = j55;
            jArr2[3] = j56;
            jArr2[4] = j57;
            jArr2[c4] = j58;
            jArr2[6] = j59;
            jArr2[7] = j60;
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = j + jArr3[0];
            long j10 = j2 + jArr3[1];
            long j11 = j3 + jArr3[2];
            long j12 = j4 + jArr3[3];
            long j13 = j5 + jArr3[4];
            long j14 = j6 + jArr3[5] + jArr4[0];
            long j15 = j7 + jArr3[6] + jArr4[1];
            int i = 1;
            long j16 = j9;
            long j17 = j12;
            long j18 = j8 + jArr3[7];
            long j19 = j15;
            long j20 = j11;
            long j21 = j14;
            long j22 = j13;
            while (i < 18) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j23 = j17;
                long j24 = j16 + j10;
                long rotlXor = ThreefishEngine.rotlXor(j10, 46, j24);
                long[] jArr5 = jArr3;
                long j25 = j20 + j23;
                long[] jArr6 = jArr4;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long rotlXor2 = ThreefishEngine.rotlXor(j23, 36, j25);
                long j26 = j22 + j21;
                long rotlXor3 = ThreefishEngine.rotlXor(j21, 19, j26);
                long j27 = j19 + j18;
                long rotlXor4 = ThreefishEngine.rotlXor(j18, 37, j27);
                long j28 = j25 + rotlXor;
                long rotlXor5 = ThreefishEngine.rotlXor(rotlXor, 33, j28);
                long j29 = j26 + rotlXor4;
                long rotlXor6 = ThreefishEngine.rotlXor(rotlXor4, 27, j29);
                long j30 = j27 + rotlXor3;
                long rotlXor7 = ThreefishEngine.rotlXor(rotlXor3, 14, j30);
                long j31 = j24 + rotlXor2;
                long rotlXor8 = ThreefishEngine.rotlXor(rotlXor2, 42, j31);
                long j32 = j29 + rotlXor5;
                long rotlXor9 = ThreefishEngine.rotlXor(rotlXor5, 17, j32);
                long j33 = j30 + rotlXor8;
                long rotlXor10 = ThreefishEngine.rotlXor(rotlXor8, 49, j33);
                long j34 = j31 + rotlXor7;
                long rotlXor11 = ThreefishEngine.rotlXor(rotlXor7, 36, j34);
                long j35 = j28 + rotlXor6;
                long rotlXor12 = ThreefishEngine.rotlXor(rotlXor6, 39, j35);
                int i4 = i;
                long j36 = j33 + rotlXor9;
                long rotlXor13 = ThreefishEngine.rotlXor(rotlXor9, 44, j36);
                long j37 = j34 + rotlXor12;
                long rotlXor14 = ThreefishEngine.rotlXor(rotlXor12, 9, j37);
                long j38 = j35 + rotlXor11;
                long rotlXor15 = ThreefishEngine.rotlXor(rotlXor11, 54, j38);
                long j39 = j32 + rotlXor10;
                long rotlXor16 = ThreefishEngine.rotlXor(rotlXor10, 56, j39);
                long j40 = j37 + jArr5[i2];
                int i5 = i2 + 1;
                long j41 = rotlXor13 + jArr5[i5];
                int i6 = i2 + 2;
                long j42 = j38 + jArr5[i6];
                int i7 = i2 + 3;
                long j43 = rotlXor16 + jArr5[i7];
                int i8 = i2 + 4;
                long j44 = j39 + jArr5[i8];
                int i9 = i2 + 5;
                long j45 = rotlXor15 + jArr5[i9] + jArr6[i3];
                int i10 = i2 + 6;
                int i11 = i3 + 1;
                long j46 = j36 + jArr5[i10] + jArr6[i11];
                int i12 = i2 + 7;
                long j47 = i4;
                long j48 = rotlXor14 + jArr5[i12] + j47;
                long j49 = j40 + j41;
                long rotlXor17 = ThreefishEngine.rotlXor(j41, 39, j49);
                long j50 = j42 + j43;
                long rotlXor18 = ThreefishEngine.rotlXor(j43, 30, j50);
                long j51 = j44 + j45;
                long rotlXor19 = ThreefishEngine.rotlXor(j45, 34, j51);
                long j52 = j46 + j48;
                long rotlXor20 = ThreefishEngine.rotlXor(j48, 24, j52);
                long j53 = j50 + rotlXor17;
                long rotlXor21 = ThreefishEngine.rotlXor(rotlXor17, 13, j53);
                long j54 = j51 + rotlXor20;
                long rotlXor22 = ThreefishEngine.rotlXor(rotlXor20, 50, j54);
                long j55 = j52 + rotlXor19;
                long rotlXor23 = ThreefishEngine.rotlXor(rotlXor19, 10, j55);
                long j56 = j49 + rotlXor18;
                long rotlXor24 = ThreefishEngine.rotlXor(rotlXor18, 17, j56);
                long j57 = j54 + rotlXor21;
                long rotlXor25 = ThreefishEngine.rotlXor(rotlXor21, 25, j57);
                long j58 = j55 + rotlXor24;
                long rotlXor26 = ThreefishEngine.rotlXor(rotlXor24, 29, j58);
                long j59 = j56 + rotlXor23;
                long rotlXor27 = ThreefishEngine.rotlXor(rotlXor23, 39, j59);
                long j60 = j53 + rotlXor22;
                long rotlXor28 = ThreefishEngine.rotlXor(rotlXor22, 43, j60);
                long j61 = j58 + rotlXor25;
                long rotlXor29 = ThreefishEngine.rotlXor(rotlXor25, 8, j61);
                long j62 = j59 + rotlXor28;
                long rotlXor30 = ThreefishEngine.rotlXor(rotlXor28, 35, j62);
                long j63 = j60 + rotlXor27;
                long rotlXor31 = ThreefishEngine.rotlXor(rotlXor27, 56, j63);
                long j64 = j57 + rotlXor26;
                long rotlXor32 = ThreefishEngine.rotlXor(rotlXor26, 22, j64);
                long j65 = j62 + jArr5[i5];
                long j66 = rotlXor29 + jArr5[i6];
                long j67 = j63 + jArr5[i7];
                long j68 = rotlXor32 + jArr5[i8];
                long j69 = j64 + jArr5[i9];
                long j70 = rotlXor31 + jArr5[i10] + jArr6[i11];
                j19 = j61 + jArr5[i12] + jArr6[i3 + 2];
                j20 = j67;
                j16 = j65;
                j22 = j69;
                jArr4 = jArr6;
                iArr = iArr3;
                j10 = j66;
                j21 = j70;
                j18 = jArr5[i2 + 8] + j47 + 1 + rotlXor30;
                i = i4 + 2;
                iArr2 = iArr4;
                j17 = j68;
                jArr3 = jArr5;
            }
            jArr2[0] = j16;
            jArr2[1] = j10;
            jArr2[2] = j20;
            jArr2[3] = j17;
            jArr2[4] = j22;
            jArr2[5] = j21;
            jArr2[6] = j19;
            jArr2[7] = j18;
        }
    }

    private static abstract class ThreefishCipher {
        protected final long[] kw;
        protected final long[] t;

        protected ThreefishCipher(long[] jArr, long[] jArr2) {
            this.kw = jArr;
            this.t = jArr2;
        }

        abstract void decryptBlock(long[] jArr, long[] jArr2);

        abstract void encryptBlock(long[] jArr, long[] jArr2);
    }

    static {
        int[] iArr = new int[80];
        MOD9 = iArr;
        MOD17 = new int[iArr.length];
        MOD5 = new int[iArr.length];
        MOD3 = new int[iArr.length];
        int i = 0;
        while (true) {
            int[] iArr2 = MOD9;
            if (i >= iArr2.length) {
                return;
            }
            MOD17[i] = i % 17;
            iArr2[i] = i % 9;
            MOD5[i] = i % 5;
            MOD3[i] = i % 3;
            i++;
        }
    }

    public ThreefishEngine(int i) {
        ThreefishCipher threefish256Cipher;
        int i2 = i / 8;
        this.blocksizeBytes = i2;
        int i3 = i2 / 8;
        this.blocksizeWords = i3;
        this.currentBlock = new long[i3];
        this.kw = new long[(i3 * 2) + 1];
        if (i == 256) {
            threefish256Cipher = new Threefish256Cipher(this.kw, this.t);
        } else if (i == 512) {
            threefish256Cipher = new Threefish512Cipher(this.kw, this.t);
        } else {
            if (i != 1024) {
                throw new IllegalArgumentException("Invalid blocksize - Threefish is defined with block size of 256, 512, or 1024 bits");
            }
            threefish256Cipher = new Threefish1024Cipher(this.kw, this.t);
        }
        this.cipher = threefish256Cipher;
    }

    public static long bytesToWord(byte[] bArr, int i) {
        return Pack.littleEndianToLong(bArr, i);
    }

    static long rotlXor(long j, int i, long j2) {
        return ((j >>> (-i)) | (j << i)) ^ j2;
    }

    private void setKey(long[] jArr) {
        if (jArr.length != this.blocksizeWords) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeWords + " words)");
        }
        long j = C_240;
        int i = 0;
        while (true) {
            int i2 = this.blocksizeWords;
            if (i >= i2) {
                long[] jArr2 = this.kw;
                jArr2[i2] = j;
                System.arraycopy(jArr2, 0, jArr2, i2 + 1, i2);
                return;
            } else {
                long[] jArr3 = this.kw;
                long j2 = jArr[i];
                jArr3[i] = j2;
                j ^= j2;
                i++;
            }
        }
    }

    private void setTweak(long[] jArr) {
        if (jArr.length != 2) {
            throw new IllegalArgumentException("Tweak must be 2 words.");
        }
        long[] jArr2 = this.t;
        long j = jArr[0];
        jArr2[0] = j;
        long j2 = jArr[1];
        jArr2[1] = j2;
        jArr2[2] = j ^ j2;
        jArr2[3] = j;
        jArr2[4] = j2;
    }

    public static void wordToBytes(long j, byte[] bArr, int i) {
        Pack.longToLittleEndian(j, bArr, i);
    }

    static long xorRotr(long j, int i, long j2) {
        long j3 = j ^ j2;
        return (j3 << (-i)) | (j3 >>> i);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Threefish-" + (this.blocksizeBytes * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blocksizeBytes;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] key;
        byte[] bArr;
        long[] jArr;
        long[] jArr2 = null;
        if (cipherParameters instanceof TweakableBlockCipherParameters) {
            TweakableBlockCipherParameters tweakableBlockCipherParameters = (TweakableBlockCipherParameters) cipherParameters;
            key = tweakableBlockCipherParameters.getKey().getKey();
            bArr = tweakableBlockCipherParameters.getTweak();
        } else {
            if (!(cipherParameters instanceof KeyParameter)) {
                throw new IllegalArgumentException("Invalid parameter passed to Threefish init - " + cipherParameters.getClass().getName());
            }
            key = ((KeyParameter) cipherParameters).getKey();
            bArr = null;
        }
        if (key == null) {
            jArr = null;
        } else {
            if (key.length != this.blocksizeBytes) {
                throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeBytes + " bytes)");
            }
            jArr = new long[this.blocksizeWords];
            Pack.littleEndianToLong(key, 0, jArr);
        }
        if (bArr != null) {
            if (bArr.length != 16) {
                throw new IllegalArgumentException("Threefish tweak must be 16 bytes");
            }
            jArr2 = new long[2];
            Pack.littleEndianToLong(bArr, 0, jArr2);
        }
        init(z, jArr, jArr2);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256, cipherParameters, Utils.getPurpose(z)));
    }

    public void init(boolean z, long[] jArr, long[] jArr2) {
        this.forEncryption = z;
        if (jArr != null) {
            setKey(jArr);
        }
        if (jArr2 != null) {
            setTweak(jArr2);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3 = this.blocksizeBytes;
        if (i + i3 > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        Pack.littleEndianToLong(bArr, i, this.currentBlock);
        long[] jArr = this.currentBlock;
        processBlock(jArr, jArr);
        Pack.longToLittleEndian(this.currentBlock, bArr2, i2);
        return this.blocksizeBytes;
    }

    public int processBlock(long[] jArr, long[] jArr2) throws DataLengthException, IllegalStateException {
        long[] jArr3 = this.kw;
        int i = this.blocksizeWords;
        if (jArr3[i] == 0) {
            throw new IllegalStateException("Threefish engine not initialised");
        }
        if (jArr.length != i) {
            throw new DataLengthException("Input buffer too short");
        }
        if (jArr2.length != i) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.forEncryption) {
            this.cipher.encryptBlock(jArr, jArr2);
        } else {
            this.cipher.decryptBlock(jArr, jArr2);
        }
        return this.blocksizeWords;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
