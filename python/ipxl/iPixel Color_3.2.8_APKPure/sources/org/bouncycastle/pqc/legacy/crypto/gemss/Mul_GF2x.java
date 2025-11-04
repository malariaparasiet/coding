package org.bouncycastle.pqc.legacy.crypto.gemss;

/* loaded from: classes4.dex */
abstract class Mul_GF2x {

    public static class Mul12 extends Mul_GF2x {
        private long[] Buffer = new long[12];

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            Mul_GF2x.mul384_no_simd_gf2x(pointer.array, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp, this.Buffer);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x_xor(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            Mul_GF2x.mul384_no_simd_gf2x_xor(pointer.array, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp, this.Buffer);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void sqr_gf2x(long[] jArr, long[] jArr2, int i) {
            Mul_GF2x.SQR128_NO_SIMD_GF2X(jArr, 8, jArr2, i + 4);
            Mul_GF2x.SQR256_NO_SIMD_GF2X(jArr, 0, jArr2, i);
        }
    }

    public static class Mul13 extends Mul_GF2x {
        private long[] Buffer = new long[13];
        private long[] Buffer2 = new long[4];

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            Mul_GF2x.mul416_no_simd_gf2x(pointer.array, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp, this.Buffer);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x_xor(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            Mul_GF2x.mul416_no_simd_gf2x_xor(pointer.array, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp, this.Buffer, this.Buffer2);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void sqr_gf2x(long[] jArr, long[] jArr2, int i) {
            jArr[12] = Mul_GF2x.SQR32_NO_SIMD_GF2X(jArr2[i + 6]);
            Mul_GF2x.SQR128_NO_SIMD_GF2X(jArr, 8, jArr2, i + 4);
            Mul_GF2x.SQR256_NO_SIMD_GF2X(jArr, 0, jArr2, i);
        }
    }

    public static class Mul17 extends Mul_GF2x {
        private long[] AA = new long[5];
        private long[] BB = new long[5];
        private long[] Buffer1 = new long[17];
        private long[] Buffer2 = new long[4];

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            Mul_GF2x.mul544_no_simd_gf2x(pointer.array, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp, this.AA, this.BB, this.Buffer1);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x_xor(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            Mul_GF2x.mul544_no_simd_gf2x_xor(pointer.array, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp, this.AA, this.BB, this.Buffer1, this.Buffer2);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void sqr_gf2x(long[] jArr, long[] jArr2, int i) {
            jArr[16] = Mul_GF2x.SQR32_NO_SIMD_GF2X(jArr2[i + 8]);
            Mul_GF2x.SQR256_NO_SIMD_GF2X(jArr, 8, jArr2, i + 4);
            Mul_GF2x.SQR256_NO_SIMD_GF2X(jArr, 0, jArr2, i);
        }
    }

    public static class Mul6 extends Mul_GF2x {
        private long[] Buffer = new long[6];

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            mul192_no_simd_gf2x(pointer.array, 0, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x_xor(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            mul192_no_simd_gf2x_xor(pointer.array, pointer.cp, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp, this.Buffer);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void sqr_gf2x(long[] jArr, long[] jArr2, int i) {
            Mul_GF2x.SQR64_NO_SIMD_GF2X(jArr, 4, jArr2[i + 2]);
            Mul_GF2x.SQR128_NO_SIMD_GF2X(jArr, 0, jArr2, i);
        }
    }

    public static class Mul9 extends Mul_GF2x {
        private long[] Buffer = new long[9];

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            Mul_GF2x.mul288_no_simd_gf2x(pointer.array, 0, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp, this.Buffer);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void mul_gf2x_xor(Pointer pointer, Pointer pointer2, Pointer pointer3) {
            Mul_GF2x.mul288_no_simd_gf2x_xor(pointer.array, pointer.cp, pointer2.array, pointer2.cp, pointer3.array, pointer3.cp, this.Buffer);
        }

        @Override // org.bouncycastle.pqc.legacy.crypto.gemss.Mul_GF2x
        public void sqr_gf2x(long[] jArr, long[] jArr2, int i) {
            jArr[8] = Mul_GF2x.SQR32_NO_SIMD_GF2X(jArr2[i + 4]);
            Mul_GF2x.SQR256_NO_SIMD_GF2X(jArr, 0, jArr2, i);
        }
    }

    Mul_GF2x() {
    }

    private static long MUL32_NO_SIMD_GF2X(long j, long j2) {
        return ((j & (-((j2 >>> 31) & 1))) << 31) ^ ((((((((((((((((((((((((((((((((-(j2 & 1)) & j) ^ (((-((j2 >>> 1) & 1)) & j) << 1)) ^ (((-((j2 >>> 2) & 1)) & j) << 2)) ^ (((-((j2 >>> 3) & 1)) & j) << 3)) ^ (((-((j2 >>> 4) & 1)) & j) << 4)) ^ (((-((j2 >>> 5) & 1)) & j) << 5)) ^ (((-((j2 >>> 6) & 1)) & j) << 6)) ^ (((-((j2 >>> 7) & 1)) & j) << 7)) ^ (((-((j2 >>> 8) & 1)) & j) << 8)) ^ (((-((j2 >>> 9) & 1)) & j) << 9)) ^ (((-((j2 >>> 10) & 1)) & j) << 10)) ^ (((-((j2 >>> 11) & 1)) & j) << 11)) ^ (((-((j2 >>> 12) & 1)) & j) << 12)) ^ (((-((j2 >>> 13) & 1)) & j) << 13)) ^ (((-((j2 >>> 14) & 1)) & j) << 14)) ^ (((-((j2 >>> 15) & 1)) & j) << 15)) ^ (((-((j2 >>> 16) & 1)) & j) << 16)) ^ (((-((j2 >>> 17) & 1)) & j) << 17)) ^ (((-((j2 >>> 18) & 1)) & j) << 18)) ^ (((-((j2 >>> 19) & 1)) & j) << 19)) ^ (((-((j2 >>> 20) & 1)) & j) << 20)) ^ (((-((j2 >>> 21) & 1)) & j) << 21)) ^ (((-((j2 >>> 22) & 1)) & j) << 22)) ^ (((-((j2 >>> 23) & 1)) & j) << 23)) ^ (((-((j2 >>> 24) & 1)) & j) << 24)) ^ (((-((j2 >>> 25) & 1)) & j) << 25)) ^ (((-((j2 >>> 26) & 1)) & j) << 26)) ^ (((-((j2 >>> 27) & 1)) & j) << 27)) ^ (((-((j2 >>> 28) & 1)) & j) << 28)) ^ (((-((j2 >>> 29) & 1)) & j) << 29)) ^ (((-((j2 >>> 30) & 1)) & j) << 30));
    }

    private static void MUL64_NO_SIMD_GF2X(long[] jArr, int i, long j, long j2) {
        long j3 = (-(j2 >>> 63)) & j;
        long j4 = (-((j2 >>> 1) & 1)) & j;
        long j5 = (((-(j2 & 1)) & j) ^ (j3 << 63)) ^ (j4 << 1);
        long j6 = (j3 >>> 1) ^ (j4 >>> 63);
        long j7 = (-((j2 >>> 2) & 1)) & j;
        long j8 = (-((j2 >>> 3) & 1)) & j;
        long j9 = (-((j2 >>> 4) & 1)) & j;
        long j10 = (-((j2 >>> 5) & 1)) & j;
        long j11 = (((j5 ^ (j7 << 2)) ^ (j8 << 3)) ^ (j9 << 4)) ^ (j10 << 5);
        long j12 = (j10 >>> 59) ^ (((j6 ^ (j7 >>> 62)) ^ (j8 >>> 61)) ^ (j9 >>> 60));
        long j13 = (-((j2 >>> 6) & 1)) & j;
        long j14 = j11 ^ (j13 << 6);
        long j15 = j12 ^ (j13 >>> 58);
        long j16 = (-((j2 >>> 7) & 1)) & j;
        long j17 = j14 ^ (j16 << 7);
        long j18 = j15 ^ (j16 >>> 57);
        long j19 = (-((j2 >>> 8) & 1)) & j;
        long j20 = j17 ^ (j19 << 8);
        long j21 = j18 ^ (j19 >>> 56);
        long j22 = (-((j2 >>> 9) & 1)) & j;
        long j23 = j20 ^ (j22 << 9);
        long j24 = j21 ^ (j22 >>> 55);
        long j25 = (-((j2 >>> 10) & 1)) & j;
        long j26 = j23 ^ (j25 << 10);
        long j27 = j24 ^ (j25 >>> 54);
        long j28 = (-((j2 >>> 11) & 1)) & j;
        long j29 = j26 ^ (j28 << 11);
        long j30 = j27 ^ (j28 >>> 53);
        long j31 = (-((j2 >>> 12) & 1)) & j;
        long j32 = j29 ^ (j31 << 12);
        long j33 = j30 ^ (j31 >>> 52);
        long j34 = (-((j2 >>> 13) & 1)) & j;
        long j35 = j32 ^ (j34 << 13);
        long j36 = j33 ^ (j34 >>> 51);
        long j37 = (-((j2 >>> 14) & 1)) & j;
        long j38 = j35 ^ (j37 << 14);
        long j39 = j36 ^ (j37 >>> 50);
        long j40 = (-((j2 >>> 15) & 1)) & j;
        long j41 = j38 ^ (j40 << 15);
        long j42 = j39 ^ (j40 >>> 49);
        long j43 = (-((j2 >>> 16) & 1)) & j;
        long j44 = j41 ^ (j43 << 16);
        long j45 = j42 ^ (j43 >>> 48);
        long j46 = (-((j2 >>> 17) & 1)) & j;
        long j47 = j44 ^ (j46 << 17);
        long j48 = j45 ^ (j46 >>> 47);
        long j49 = (-((j2 >>> 18) & 1)) & j;
        long j50 = j47 ^ (j49 << 18);
        long j51 = j48 ^ (j49 >>> 46);
        long j52 = (-((j2 >>> 19) & 1)) & j;
        long j53 = j50 ^ (j52 << 19);
        long j54 = j51 ^ (j52 >>> 45);
        long j55 = (-((j2 >>> 20) & 1)) & j;
        long j56 = j53 ^ (j55 << 20);
        long j57 = j54 ^ (j55 >>> 44);
        long j58 = (-((j2 >>> 21) & 1)) & j;
        long j59 = j56 ^ (j58 << 21);
        long j60 = j57 ^ (j58 >>> 43);
        long j61 = (-((j2 >>> 22) & 1)) & j;
        long j62 = j59 ^ (j61 << 22);
        long j63 = j60 ^ (j61 >>> 42);
        long j64 = (-((j2 >>> 23) & 1)) & j;
        long j65 = j62 ^ (j64 << 23);
        long j66 = j63 ^ (j64 >>> 41);
        long j67 = (-((j2 >>> 24) & 1)) & j;
        long j68 = j65 ^ (j67 << 24);
        long j69 = j66 ^ (j67 >>> 40);
        long j70 = (-((j2 >>> 25) & 1)) & j;
        long j71 = j68 ^ (j70 << 25);
        long j72 = j69 ^ (j70 >>> 39);
        long j73 = (-((j2 >>> 26) & 1)) & j;
        long j74 = j71 ^ (j73 << 26);
        long j75 = j72 ^ (j73 >>> 38);
        long j76 = (-((j2 >>> 27) & 1)) & j;
        long j77 = j74 ^ (j76 << 27);
        long j78 = j75 ^ (j76 >>> 37);
        long j79 = (-((j2 >>> 28) & 1)) & j;
        long j80 = j77 ^ (j79 << 28);
        long j81 = j78 ^ (j79 >>> 36);
        long j82 = (-((j2 >>> 29) & 1)) & j;
        long j83 = j80 ^ (j82 << 29);
        long j84 = j81 ^ (j82 >>> 35);
        long j85 = (-((j2 >>> 30) & 1)) & j;
        long j86 = j83 ^ (j85 << 30);
        long j87 = j84 ^ (j85 >>> 34);
        long j88 = (-((j2 >>> 31) & 1)) & j;
        long j89 = j86 ^ (j88 << 31);
        long j90 = j87 ^ (j88 >>> 33);
        long j91 = (-((j2 >>> 32) & 1)) & j;
        long j92 = j89 ^ (j91 << 32);
        long j93 = j90 ^ (j91 >>> 32);
        long j94 = (-((j2 >>> 33) & 1)) & j;
        long j95 = j92 ^ (j94 << 33);
        long j96 = j93 ^ (j94 >>> 31);
        long j97 = (-((j2 >>> 34) & 1)) & j;
        long j98 = j95 ^ (j97 << 34);
        long j99 = j96 ^ (j97 >>> 30);
        long j100 = (-((j2 >>> 35) & 1)) & j;
        long j101 = j98 ^ (j100 << 35);
        long j102 = j99 ^ (j100 >>> 29);
        long j103 = (-((j2 >>> 36) & 1)) & j;
        long j104 = j101 ^ (j103 << 36);
        long j105 = j102 ^ (j103 >>> 28);
        long j106 = (-((j2 >>> 37) & 1)) & j;
        long j107 = j104 ^ (j106 << 37);
        long j108 = j105 ^ (j106 >>> 27);
        long j109 = (-((j2 >>> 38) & 1)) & j;
        long j110 = j107 ^ (j109 << 38);
        long j111 = j108 ^ (j109 >>> 26);
        long j112 = (-((j2 >>> 39) & 1)) & j;
        long j113 = j110 ^ (j112 << 39);
        long j114 = j111 ^ (j112 >>> 25);
        long j115 = (-((j2 >>> 40) & 1)) & j;
        long j116 = j113 ^ (j115 << 40);
        long j117 = j114 ^ (j115 >>> 24);
        long j118 = (-((j2 >>> 41) & 1)) & j;
        long j119 = j116 ^ (j118 << 41);
        long j120 = j117 ^ (j118 >>> 23);
        long j121 = (-((j2 >>> 42) & 1)) & j;
        long j122 = j119 ^ (j121 << 42);
        long j123 = j120 ^ (j121 >>> 22);
        long j124 = (-((j2 >>> 43) & 1)) & j;
        long j125 = j122 ^ (j124 << 43);
        long j126 = j123 ^ (j124 >>> 21);
        long j127 = (-((j2 >>> 44) & 1)) & j;
        long j128 = j125 ^ (j127 << 44);
        long j129 = j126 ^ (j127 >>> 20);
        long j130 = (-((j2 >>> 45) & 1)) & j;
        long j131 = j128 ^ (j130 << 45);
        long j132 = j129 ^ (j130 >>> 19);
        long j133 = (-((j2 >>> 46) & 1)) & j;
        long j134 = j131 ^ (j133 << 46);
        long j135 = j132 ^ (j133 >>> 18);
        long j136 = (-((j2 >>> 47) & 1)) & j;
        long j137 = j134 ^ (j136 << 47);
        long j138 = j135 ^ (j136 >>> 17);
        long j139 = (-((j2 >>> 48) & 1)) & j;
        long j140 = j137 ^ (j139 << 48);
        long j141 = j138 ^ (j139 >>> 16);
        long j142 = (-((j2 >>> 49) & 1)) & j;
        long j143 = j140 ^ (j142 << 49);
        long j144 = j141 ^ (j142 >>> 15);
        long j145 = (-((j2 >>> 50) & 1)) & j;
        long j146 = j143 ^ (j145 << 50);
        long j147 = j144 ^ (j145 >>> 14);
        long j148 = (-((j2 >>> 51) & 1)) & j;
        long j149 = j146 ^ (j148 << 51);
        long j150 = j147 ^ (j148 >>> 13);
        long j151 = (-((j2 >>> 52) & 1)) & j;
        long j152 = j149 ^ (j151 << 52);
        long j153 = j150 ^ (j151 >>> 12);
        long j154 = (-((j2 >>> 53) & 1)) & j;
        long j155 = j152 ^ (j154 << 53);
        long j156 = j153 ^ (j154 >>> 11);
        long j157 = (-((j2 >>> 54) & 1)) & j;
        long j158 = j155 ^ (j157 << 54);
        long j159 = j156 ^ (j157 >>> 10);
        long j160 = (-((j2 >>> 55) & 1)) & j;
        long j161 = j158 ^ (j160 << 55);
        long j162 = j159 ^ (j160 >>> 9);
        long j163 = (-((j2 >>> 56) & 1)) & j;
        long j164 = j161 ^ (j163 << 56);
        long j165 = j162 ^ (j163 >>> 8);
        long j166 = (-((j2 >>> 57) & 1)) & j;
        long j167 = j164 ^ (j166 << 57);
        long j168 = j165 ^ (j166 >>> 7);
        long j169 = (-((j2 >>> 58) & 1)) & j;
        long j170 = j167 ^ (j169 << 58);
        long j171 = j168 ^ (j169 >>> 6);
        long j172 = (-((j2 >>> 59) & 1)) & j;
        long j173 = j170 ^ (j172 << 59);
        long j174 = j171 ^ (j172 >>> 5);
        long j175 = (-((j2 >>> 60) & 1)) & j;
        long j176 = j173 ^ (j175 << 60);
        long j177 = j174 ^ (j175 >>> 4);
        long j178 = (-((j2 >>> 61) & 1)) & j;
        long j179 = j176 ^ (j178 << 61);
        long j180 = j177 ^ (j178 >>> 3);
        long j181 = (-((j2 >>> 62) & 1)) & j;
        jArr[i] = j179 ^ (j181 << 62);
        jArr[i + 1] = j180 ^ (j181 >>> 2);
    }

    private static void MUL64_NO_SIMD_GF2X_XOR(long[] jArr, int i, long j, long j2) {
        long j3 = (-(j2 >>> 63)) & j;
        long j4 = (-((j2 >>> 1) & 1)) & j;
        long j5 = (((-(j2 & 1)) & j) ^ (j3 << 63)) ^ (j4 << 1);
        long j6 = (j3 >>> 1) ^ (j4 >>> 63);
        long j7 = (-((j2 >>> 2) & 1)) & j;
        long j8 = (-((j2 >>> 3) & 1)) & j;
        long j9 = (-((j2 >>> 4) & 1)) & j;
        long j10 = (-((j2 >>> 5) & 1)) & j;
        long j11 = (((j5 ^ (j7 << 2)) ^ (j8 << 3)) ^ (j9 << 4)) ^ (j10 << 5);
        long j12 = (j10 >>> 59) ^ (((j6 ^ (j7 >>> 62)) ^ (j8 >>> 61)) ^ (j9 >>> 60));
        long j13 = (-((j2 >>> 6) & 1)) & j;
        long j14 = j11 ^ (j13 << 6);
        long j15 = j12 ^ (j13 >>> 58);
        long j16 = (-((j2 >>> 7) & 1)) & j;
        long j17 = j14 ^ (j16 << 7);
        long j18 = j15 ^ (j16 >>> 57);
        long j19 = (-((j2 >>> 8) & 1)) & j;
        long j20 = j17 ^ (j19 << 8);
        long j21 = j18 ^ (j19 >>> 56);
        long j22 = (-((j2 >>> 9) & 1)) & j;
        long j23 = j20 ^ (j22 << 9);
        long j24 = j21 ^ (j22 >>> 55);
        long j25 = (-((j2 >>> 10) & 1)) & j;
        long j26 = j23 ^ (j25 << 10);
        long j27 = j24 ^ (j25 >>> 54);
        long j28 = (-((j2 >>> 11) & 1)) & j;
        long j29 = j26 ^ (j28 << 11);
        long j30 = j27 ^ (j28 >>> 53);
        long j31 = (-((j2 >>> 12) & 1)) & j;
        long j32 = j29 ^ (j31 << 12);
        long j33 = j30 ^ (j31 >>> 52);
        long j34 = (-((j2 >>> 13) & 1)) & j;
        long j35 = j32 ^ (j34 << 13);
        long j36 = j33 ^ (j34 >>> 51);
        long j37 = (-((j2 >>> 14) & 1)) & j;
        long j38 = j35 ^ (j37 << 14);
        long j39 = j36 ^ (j37 >>> 50);
        long j40 = (-((j2 >>> 15) & 1)) & j;
        long j41 = j38 ^ (j40 << 15);
        long j42 = j39 ^ (j40 >>> 49);
        long j43 = (-((j2 >>> 16) & 1)) & j;
        long j44 = j41 ^ (j43 << 16);
        long j45 = j42 ^ (j43 >>> 48);
        long j46 = (-((j2 >>> 17) & 1)) & j;
        long j47 = j44 ^ (j46 << 17);
        long j48 = j45 ^ (j46 >>> 47);
        long j49 = (-((j2 >>> 18) & 1)) & j;
        long j50 = j47 ^ (j49 << 18);
        long j51 = j48 ^ (j49 >>> 46);
        long j52 = (-((j2 >>> 19) & 1)) & j;
        long j53 = j50 ^ (j52 << 19);
        long j54 = j51 ^ (j52 >>> 45);
        long j55 = (-((j2 >>> 20) & 1)) & j;
        long j56 = j53 ^ (j55 << 20);
        long j57 = j54 ^ (j55 >>> 44);
        long j58 = (-((j2 >>> 21) & 1)) & j;
        long j59 = j56 ^ (j58 << 21);
        long j60 = j57 ^ (j58 >>> 43);
        long j61 = (-((j2 >>> 22) & 1)) & j;
        long j62 = j59 ^ (j61 << 22);
        long j63 = j60 ^ (j61 >>> 42);
        long j64 = (-((j2 >>> 23) & 1)) & j;
        long j65 = j62 ^ (j64 << 23);
        long j66 = j63 ^ (j64 >>> 41);
        long j67 = (-((j2 >>> 24) & 1)) & j;
        long j68 = j65 ^ (j67 << 24);
        long j69 = j66 ^ (j67 >>> 40);
        long j70 = (-((j2 >>> 25) & 1)) & j;
        long j71 = j68 ^ (j70 << 25);
        long j72 = j69 ^ (j70 >>> 39);
        long j73 = (-((j2 >>> 26) & 1)) & j;
        long j74 = j71 ^ (j73 << 26);
        long j75 = j72 ^ (j73 >>> 38);
        long j76 = (-((j2 >>> 27) & 1)) & j;
        long j77 = j74 ^ (j76 << 27);
        long j78 = j75 ^ (j76 >>> 37);
        long j79 = (-((j2 >>> 28) & 1)) & j;
        long j80 = j77 ^ (j79 << 28);
        long j81 = j78 ^ (j79 >>> 36);
        long j82 = (-((j2 >>> 29) & 1)) & j;
        long j83 = j80 ^ (j82 << 29);
        long j84 = j81 ^ (j82 >>> 35);
        long j85 = (-((j2 >>> 30) & 1)) & j;
        long j86 = j83 ^ (j85 << 30);
        long j87 = j84 ^ (j85 >>> 34);
        long j88 = (-((j2 >>> 31) & 1)) & j;
        long j89 = j86 ^ (j88 << 31);
        long j90 = j87 ^ (j88 >>> 33);
        long j91 = (-((j2 >>> 32) & 1)) & j;
        long j92 = j89 ^ (j91 << 32);
        long j93 = j90 ^ (j91 >>> 32);
        long j94 = (-((j2 >>> 33) & 1)) & j;
        long j95 = j92 ^ (j94 << 33);
        long j96 = j93 ^ (j94 >>> 31);
        long j97 = (-((j2 >>> 34) & 1)) & j;
        long j98 = j95 ^ (j97 << 34);
        long j99 = j96 ^ (j97 >>> 30);
        long j100 = (-((j2 >>> 35) & 1)) & j;
        long j101 = j98 ^ (j100 << 35);
        long j102 = j99 ^ (j100 >>> 29);
        long j103 = (-((j2 >>> 36) & 1)) & j;
        long j104 = j101 ^ (j103 << 36);
        long j105 = j102 ^ (j103 >>> 28);
        long j106 = (-((j2 >>> 37) & 1)) & j;
        long j107 = j104 ^ (j106 << 37);
        long j108 = j105 ^ (j106 >>> 27);
        long j109 = (-((j2 >>> 38) & 1)) & j;
        long j110 = j107 ^ (j109 << 38);
        long j111 = j108 ^ (j109 >>> 26);
        long j112 = (-((j2 >>> 39) & 1)) & j;
        long j113 = j110 ^ (j112 << 39);
        long j114 = j111 ^ (j112 >>> 25);
        long j115 = (-((j2 >>> 40) & 1)) & j;
        long j116 = j113 ^ (j115 << 40);
        long j117 = j114 ^ (j115 >>> 24);
        long j118 = (-((j2 >>> 41) & 1)) & j;
        long j119 = j116 ^ (j118 << 41);
        long j120 = j117 ^ (j118 >>> 23);
        long j121 = (-((j2 >>> 42) & 1)) & j;
        long j122 = j119 ^ (j121 << 42);
        long j123 = j120 ^ (j121 >>> 22);
        long j124 = (-((j2 >>> 43) & 1)) & j;
        long j125 = j122 ^ (j124 << 43);
        long j126 = j123 ^ (j124 >>> 21);
        long j127 = (-((j2 >>> 44) & 1)) & j;
        long j128 = j125 ^ (j127 << 44);
        long j129 = j126 ^ (j127 >>> 20);
        long j130 = (-((j2 >>> 45) & 1)) & j;
        long j131 = j128 ^ (j130 << 45);
        long j132 = j129 ^ (j130 >>> 19);
        long j133 = (-((j2 >>> 46) & 1)) & j;
        long j134 = j131 ^ (j133 << 46);
        long j135 = j132 ^ (j133 >>> 18);
        long j136 = (-((j2 >>> 47) & 1)) & j;
        long j137 = j134 ^ (j136 << 47);
        long j138 = j135 ^ (j136 >>> 17);
        long j139 = (-((j2 >>> 48) & 1)) & j;
        long j140 = j137 ^ (j139 << 48);
        long j141 = j138 ^ (j139 >>> 16);
        long j142 = (-((j2 >>> 49) & 1)) & j;
        long j143 = j140 ^ (j142 << 49);
        long j144 = j141 ^ (j142 >>> 15);
        long j145 = (-((j2 >>> 50) & 1)) & j;
        long j146 = j143 ^ (j145 << 50);
        long j147 = j144 ^ (j145 >>> 14);
        long j148 = (-((j2 >>> 51) & 1)) & j;
        long j149 = j146 ^ (j148 << 51);
        long j150 = j147 ^ (j148 >>> 13);
        long j151 = (-((j2 >>> 52) & 1)) & j;
        long j152 = j149 ^ (j151 << 52);
        long j153 = j150 ^ (j151 >>> 12);
        long j154 = (-((j2 >>> 53) & 1)) & j;
        long j155 = j152 ^ (j154 << 53);
        long j156 = j153 ^ (j154 >>> 11);
        long j157 = (-((j2 >>> 54) & 1)) & j;
        long j158 = j155 ^ (j157 << 54);
        long j159 = j156 ^ (j157 >>> 10);
        long j160 = (-((j2 >>> 55) & 1)) & j;
        long j161 = j158 ^ (j160 << 55);
        long j162 = j159 ^ (j160 >>> 9);
        long j163 = (-((j2 >>> 56) & 1)) & j;
        long j164 = j161 ^ (j163 << 56);
        long j165 = j162 ^ (j163 >>> 8);
        long j166 = (-((j2 >>> 57) & 1)) & j;
        long j167 = j164 ^ (j166 << 57);
        long j168 = j165 ^ (j166 >>> 7);
        long j169 = (-((j2 >>> 58) & 1)) & j;
        long j170 = j167 ^ (j169 << 58);
        long j171 = j168 ^ (j169 >>> 6);
        long j172 = (-((j2 >>> 59) & 1)) & j;
        long j173 = j170 ^ (j172 << 59);
        long j174 = j171 ^ (j172 >>> 5);
        long j175 = (-((j2 >>> 60) & 1)) & j;
        long j176 = j173 ^ (j175 << 60);
        long j177 = j174 ^ (j175 >>> 4);
        long j178 = (-((j2 >>> 61) & 1)) & j;
        long j179 = j176 ^ (j178 << 61);
        long j180 = j177 ^ (j178 >>> 3);
        long j181 = (-((j2 >>> 62) & 1)) & j;
        jArr[i] = (j179 ^ (j181 << 62)) ^ jArr[i];
        int i2 = i + 1;
        jArr[i2] = (j180 ^ (j181 >>> 2)) ^ jArr[i2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void SQR128_NO_SIMD_GF2X(long[] jArr, int i, long[] jArr2, int i2) {
        SQR64_NO_SIMD_GF2X(jArr, i + 2, jArr2[i2 + 1]);
        SQR64_NO_SIMD_GF2X(jArr, i, jArr2[i2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void SQR256_NO_SIMD_GF2X(long[] jArr, int i, long[] jArr2, int i2) {
        SQR128_NO_SIMD_GF2X(jArr, i + 4, jArr2, i2 + 2);
        SQR128_NO_SIMD_GF2X(jArr, i, jArr2, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long SQR32_NO_SIMD_GF2X(long j) {
        long j2 = (j ^ (j << 16)) & 281470681808895L;
        long j3 = (j2 ^ (j2 << 8)) & 71777214294589695L;
        long j4 = (j3 ^ (j3 << 4)) & 1085102592571150095L;
        long j5 = (j4 ^ (j4 << 2)) & 3689348814741910323L;
        return (j5 ^ (j5 << 1)) & 6148914691236517205L;
    }

    private static long SQR64LOW_NO_SIMD_GF2X(long j) {
        long j2 = ((j << 16) ^ (4294967295L & j)) & 281470681808895L;
        long j3 = (j2 ^ (j2 << 8)) & 71777214294589695L;
        long j4 = (j3 ^ (j3 << 4)) & 1085102592571150095L;
        long j5 = (j4 ^ (j4 << 2)) & 3689348814741910323L;
        return (j5 ^ (j5 << 1)) & 6148914691236517205L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void SQR64_NO_SIMD_GF2X(long[] jArr, int i, long j) {
        jArr[i + 1] = SQR32_NO_SIMD_GF2X(j >>> 32);
        jArr[i] = SQR64LOW_NO_SIMD_GF2X(j);
    }

    private static void mul128_no_simd_gf2x(long[] jArr, int i, long j, long j2, long j3, long j4) {
        MUL64_NO_SIMD_GF2X(jArr, i, j, j3);
        int i2 = i + 2;
        MUL64_NO_SIMD_GF2X(jArr, i2, j2, j4);
        int i3 = i + 1;
        long j5 = jArr[i2] ^ jArr[i3];
        jArr[i2] = j5;
        jArr[i3] = j5 ^ jArr[i];
        jArr[i2] = jArr[i2] ^ jArr[i + 3];
        MUL64_NO_SIMD_GF2X_XOR(jArr, i3, j ^ j2, j3 ^ j4);
    }

    private static void mul128_no_simd_gf2x(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3) {
        MUL64_NO_SIMD_GF2X(jArr, i, jArr2[i2], jArr3[i3]);
        int i4 = i + 2;
        int i5 = i2 + 1;
        int i6 = i3 + 1;
        MUL64_NO_SIMD_GF2X(jArr, i4, jArr2[i5], jArr3[i6]);
        long j = jArr[i4];
        int i7 = i + 1;
        long j2 = j ^ jArr[i7];
        jArr[i4] = j2;
        jArr[i7] = j2 ^ jArr[i];
        jArr[i4] = jArr[i4] ^ jArr[i + 3];
        MUL64_NO_SIMD_GF2X_XOR(jArr, i7, jArr2[i2] ^ jArr2[i5], jArr3[i3] ^ jArr3[i6]);
    }

    private static void mul128_no_simd_gf2x_xor(long[] jArr, int i, long j, long j2, long j3, long j4, long[] jArr2) {
        MUL64_NO_SIMD_GF2X(jArr2, 0, j, j3);
        MUL64_NO_SIMD_GF2X(jArr2, 2, j2, j4);
        jArr[i] = jArr[i] ^ jArr2[0];
        long j5 = jArr2[2] ^ jArr2[1];
        jArr2[2] = j5;
        int i2 = i + 1;
        jArr[i2] = (jArr2[0] ^ j5) ^ jArr[i2];
        int i3 = i + 2;
        jArr[i3] = jArr[i3] ^ (jArr2[2] ^ jArr2[3]);
        int i4 = i + 3;
        jArr[i4] = jArr[i4] ^ jArr2[3];
        MUL64_NO_SIMD_GF2X_XOR(jArr, i2, j ^ j2, j3 ^ j4);
    }

    public static void mul192_no_simd_gf2x(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3) {
        MUL64_NO_SIMD_GF2X(jArr, i, jArr2[i2], jArr3[i3]);
        int i4 = i + 4;
        int i5 = i2 + 2;
        int i6 = i3 + 2;
        MUL64_NO_SIMD_GF2X(jArr, i4, jArr2[i5], jArr3[i6]);
        int i7 = i + 2;
        int i8 = i2 + 1;
        int i9 = i3 + 1;
        MUL64_NO_SIMD_GF2X(jArr, i7, jArr2[i8], jArr3[i9]);
        int i10 = i + 1;
        jArr[i10] = jArr[i10] ^ jArr[i7];
        int i11 = i + 3;
        long j = jArr[i11] ^ jArr[i4];
        jArr[i11] = j;
        jArr[i4] = j ^ jArr[i + 5];
        jArr[i7] = (jArr[i11] ^ jArr[i10]) ^ jArr[i];
        jArr[i11] = jArr[i10] ^ jArr[i4];
        jArr[i10] = jArr[i10] ^ jArr[i];
        MUL64_NO_SIMD_GF2X_XOR(jArr, i10, jArr2[i2] ^ jArr2[i8], jArr3[i3] ^ jArr3[i9]);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i11, jArr2[i8] ^ jArr2[i5], jArr3[i9] ^ jArr3[i6]);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i7, jArr2[i2] ^ jArr2[i5], jArr3[i3] ^ jArr3[i6]);
    }

    public static void mul192_no_simd_gf2x_xor(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, long[] jArr4) {
        MUL64_NO_SIMD_GF2X(jArr4, 0, jArr2[i2], jArr3[i3]);
        int i4 = i2 + 2;
        int i5 = i3 + 2;
        MUL64_NO_SIMD_GF2X(jArr4, 4, jArr2[i4], jArr3[i5]);
        int i6 = i2 + 1;
        int i7 = i3 + 1;
        MUL64_NO_SIMD_GF2X(jArr4, 2, jArr2[i6], jArr3[i7]);
        jArr[i] = jArr[i] ^ jArr4[0];
        long j = jArr4[1] ^ jArr4[2];
        jArr4[1] = j;
        long j2 = jArr4[3] ^ jArr4[4];
        jArr4[3] = j2;
        jArr4[4] = j2 ^ jArr4[5];
        long j3 = j ^ jArr4[0];
        jArr4[0] = j3;
        int i8 = i + 1;
        jArr[i8] = j3 ^ jArr[i8];
        int i9 = i + 2;
        jArr[i9] = (jArr4[0] ^ jArr4[3]) ^ jArr[i9];
        int i10 = i + 3;
        jArr[i10] = jArr[i10] ^ (jArr4[1] ^ jArr4[4]);
        int i11 = i + 4;
        jArr[i11] = jArr[i11] ^ jArr4[4];
        int i12 = i + 5;
        jArr[i12] = jArr[i12] ^ jArr4[5];
        MUL64_NO_SIMD_GF2X_XOR(jArr, i8, jArr2[i2] ^ jArr2[i6], jArr3[i3] ^ jArr3[i7]);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i10, jArr2[i6] ^ jArr2[i4], jArr3[i7] ^ jArr3[i5]);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i9, jArr2[i2] ^ jArr2[i4], jArr3[i5] ^ jArr3[i3]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mul288_no_simd_gf2x(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, long[] jArr4) {
        int i4 = i2 + 1;
        int i5 = i3 + 1;
        mul128_no_simd_gf2x(jArr, i, jArr2[i2], jArr2[i4], jArr3[i3], jArr3[i5]);
        int i6 = i + 4;
        int i7 = i2 + 2;
        int i8 = i3 + 2;
        MUL64_NO_SIMD_GF2X(jArr, i6, jArr2[i7], jArr3[i8]);
        int i9 = i + 7;
        int i10 = i2 + 3;
        int i11 = i3 + 3;
        MUL64_NO_SIMD_GF2X(jArr, i9, jArr2[i10], jArr3[i11]);
        long j = jArr[i9];
        int i12 = i + 5;
        jArr[i9] = j ^ jArr[i12];
        int i13 = i + 8;
        int i14 = i2 + 4;
        int i15 = i3 + 4;
        jArr[i13] = MUL32_NO_SIMD_GF2X(jArr2[i14], jArr3[i15]) ^ jArr[i13];
        jArr[i12] = jArr[i9] ^ jArr[i6];
        long j2 = jArr[i9] ^ jArr[i13];
        jArr[i9] = j2;
        int i16 = i + 6;
        jArr[i16] = j2 ^ jArr[i6];
        MUL64_NO_SIMD_GF2X_XOR(jArr, i12, jArr2[i10] ^ jArr2[i7], jArr3[i8] ^ jArr3[i11]);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i9, jArr2[i14] ^ jArr2[i10], jArr3[i11] ^ jArr3[i15]);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i16, jArr2[i14] ^ jArr2[i7], jArr3[i8] ^ jArr3[i15]);
        int i17 = i + 2;
        jArr[i6] = jArr[i6] ^ jArr[i17];
        long j3 = jArr[i12];
        int i18 = i + 3;
        jArr[i12] = j3 ^ jArr[i18];
        long j4 = jArr2[i2] ^ jArr2[i7];
        long j5 = jArr2[i4] ^ jArr2[i10];
        long j6 = jArr3[i3] ^ jArr3[i8];
        long j7 = jArr3[i5] ^ jArr3[i11];
        MUL64_NO_SIMD_GF2X(jArr4, 0, j4, j6);
        MUL64_NO_SIMD_GF2X(jArr4, 2, j5, j7);
        jArr4[2] = jArr4[2] ^ jArr4[1];
        jArr4[3] = MUL32_NO_SIMD_GF2X(jArr2[i14], jArr3[i15]) ^ jArr4[3];
        jArr[i17] = (jArr[i6] ^ jArr[i]) ^ jArr4[0];
        jArr[i18] = ((jArr[i12] ^ jArr[i + 1]) ^ jArr4[2]) ^ jArr4[0];
        long j8 = jArr4[2] ^ jArr4[3];
        jArr4[2] = j8;
        jArr[i6] = ((jArr[i16] ^ j8) ^ jArr4[0]) ^ jArr[i6];
        jArr[i12] = jArr[i12] ^ (jArr[i9] ^ jArr4[2]);
        jArr[i16] = (jArr[i13] ^ jArr4[3]) ^ jArr[i16];
        MUL64_NO_SIMD_GF2X_XOR(jArr, i18, j4 ^ j5, j6 ^ j7);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i12, j5 ^ jArr2[i14], j7 ^ jArr3[i15]);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i6, j4 ^ jArr2[i14], j6 ^ jArr3[i15]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mul288_no_simd_gf2x_xor(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, long[] jArr4) {
        int i4 = i2 + 1;
        int i5 = i3 + 1;
        mul128_no_simd_gf2x(jArr4, 0, jArr2[i2], jArr2[i4], jArr3[i3], jArr3[i5]);
        int i6 = i2 + 2;
        int i7 = i3 + 2;
        MUL64_NO_SIMD_GF2X(jArr4, 4, jArr2[i6], jArr3[i7]);
        int i8 = i2 + 3;
        int i9 = i3 + 3;
        MUL64_NO_SIMD_GF2X(jArr4, 7, jArr2[i8], jArr3[i9]);
        jArr4[7] = jArr4[7] ^ jArr4[5];
        int i10 = i2 + 4;
        int i11 = i3 + 4;
        long MUL32_NO_SIMD_GF2X = jArr4[8] ^ MUL32_NO_SIMD_GF2X(jArr2[i10], jArr3[i11]);
        jArr4[8] = MUL32_NO_SIMD_GF2X;
        long j = jArr4[7];
        long j2 = jArr4[4];
        long j3 = j ^ j2;
        jArr4[5] = j3;
        long j4 = MUL32_NO_SIMD_GF2X ^ j;
        jArr4[7] = j4;
        jArr4[6] = j4 ^ j2;
        jArr4[4] = jArr4[2] ^ j2;
        jArr4[5] = j3 ^ jArr4[3];
        jArr[i] = jArr[i] ^ jArr4[0];
        int i12 = i + 1;
        jArr[i12] = jArr[i12] ^ jArr4[1];
        int i13 = i + 2;
        jArr[i13] = jArr[i13] ^ (jArr4[4] ^ jArr4[0]);
        MUL64_NO_SIMD_GF2X_XOR(jArr4, 5, jArr2[i8] ^ jArr2[i6], jArr3[i7] ^ jArr3[i9]);
        MUL64_NO_SIMD_GF2X_XOR(jArr4, 7, jArr2[i10] ^ jArr2[i8], jArr3[i9] ^ jArr3[i11]);
        MUL64_NO_SIMD_GF2X_XOR(jArr4, 6, jArr2[i10] ^ jArr2[i6], jArr3[i7] ^ jArr3[i11]);
        int i14 = i + 3;
        jArr[i14] = jArr[i14] ^ (jArr4[5] ^ jArr4[1]);
        int i15 = i + 4;
        jArr[i15] = jArr[i15] ^ (jArr4[4] ^ jArr4[6]);
        int i16 = i + 5;
        jArr[i16] = jArr[i16] ^ (jArr4[5] ^ jArr4[7]);
        int i17 = i + 6;
        jArr[i17] = jArr[i17] ^ (jArr4[6] ^ jArr4[8]);
        int i18 = i + 7;
        jArr[i18] = jArr[i18] ^ jArr4[7];
        int i19 = i + 8;
        jArr[i19] = jArr[i19] ^ jArr4[8];
        long j5 = jArr2[i2] ^ jArr2[i6];
        long j6 = jArr2[i4] ^ jArr2[i8];
        long j7 = jArr3[i3] ^ jArr3[i7];
        long j8 = jArr3[i5] ^ jArr3[i9];
        MUL64_NO_SIMD_GF2X(jArr4, 0, j5, j7);
        MUL64_NO_SIMD_GF2X(jArr4, 2, j6, j8);
        jArr4[2] = jArr4[2] ^ jArr4[1];
        jArr4[3] = MUL32_NO_SIMD_GF2X(jArr2[i10], jArr3[i11]) ^ jArr4[3];
        jArr[i13] = jArr[i13] ^ jArr4[0];
        jArr[i14] = jArr[i14] ^ (jArr4[2] ^ jArr4[0]);
        long j9 = jArr4[2] ^ jArr4[3];
        jArr4[2] = j9;
        jArr[i15] = (j9 ^ jArr4[0]) ^ jArr[i15];
        jArr[i16] = jArr[i16] ^ jArr4[2];
        jArr[i17] = jArr[i17] ^ jArr4[3];
        MUL64_NO_SIMD_GF2X_XOR(jArr, i14, j5 ^ j6, j7 ^ j8);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i16, j6 ^ jArr2[i10], j8 ^ jArr3[i11]);
        MUL64_NO_SIMD_GF2X_XOR(jArr, i15, jArr2[i10] ^ j5, jArr3[i11] ^ j7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mul384_no_simd_gf2x(long[] jArr, long[] jArr2, int i, long[] jArr3, int i2, long[] jArr4) {
        mul192_no_simd_gf2x(jArr, 0, jArr2, i, jArr3, i2);
        int i3 = i + 3;
        int i4 = i2 + 3;
        mul192_no_simd_gf2x(jArr, 6, jArr2, i3, jArr3, i4);
        long j = jArr2[i] ^ jArr2[i3];
        long j2 = jArr2[i + 1] ^ jArr2[i + 4];
        long j3 = jArr2[i + 2] ^ jArr2[i + 5];
        long j4 = jArr3[i2] ^ jArr3[i4];
        long j5 = jArr3[i2 + 1] ^ jArr3[i2 + 4];
        long j6 = jArr3[i2 + 2] ^ jArr3[i2 + 5];
        jArr[6] = jArr[6] ^ jArr[3];
        jArr[7] = jArr[7] ^ jArr[4];
        jArr[8] = jArr[8] ^ jArr[5];
        MUL64_NO_SIMD_GF2X(jArr4, 0, j, j4);
        MUL64_NO_SIMD_GF2X(jArr4, 4, j3, j6);
        MUL64_NO_SIMD_GF2X(jArr4, 2, j2, j5);
        long j7 = jArr[6];
        long j8 = j7 ^ jArr[0];
        long j9 = jArr4[0];
        jArr[3] = j8 ^ j9;
        long j10 = jArr4[1] ^ jArr4[2];
        jArr4[1] = j10;
        long j11 = j9 ^ j10;
        jArr4[0] = j11;
        long j12 = jArr4[3] ^ jArr4[4];
        jArr4[3] = j12;
        long j13 = j12 ^ jArr4[5];
        jArr4[4] = j13;
        long j14 = jArr[8];
        jArr[5] = ((j14 ^ jArr[2]) ^ j12) ^ j11;
        jArr[6] = j7 ^ ((jArr[9] ^ j10) ^ j13);
        long j15 = jArr[7];
        jArr[4] = (jArr[1] ^ j15) ^ j11;
        jArr[7] = j15 ^ (jArr4[4] ^ jArr[10]);
        jArr[8] = j14 ^ (jArr[11] ^ jArr4[5]);
        MUL64_NO_SIMD_GF2X_XOR(jArr, 4, j ^ j2, j4 ^ j5);
        MUL64_NO_SIMD_GF2X_XOR(jArr, 6, j2 ^ j3, j5 ^ j6);
        MUL64_NO_SIMD_GF2X_XOR(jArr, 5, j ^ j3, j4 ^ j6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mul384_no_simd_gf2x_xor(long[] jArr, long[] jArr2, int i, long[] jArr3, int i2, long[] jArr4) {
        mul192_no_simd_gf2x(jArr4, 0, jArr2, i, jArr3, i2);
        int i3 = i + 3;
        int i4 = i2 + 3;
        mul192_no_simd_gf2x(jArr4, 6, jArr2, i3, jArr3, i4);
        long j = jArr2[i3] ^ jArr2[i];
        long j2 = jArr2[i + 1] ^ jArr2[i + 4];
        long j3 = jArr2[i + 2] ^ jArr2[i + 5];
        long j4 = jArr3[i4] ^ jArr3[i2];
        long j5 = jArr3[i2 + 4] ^ jArr3[i2 + 1];
        long j6 = jArr3[i2 + 5] ^ jArr3[i2 + 2];
        long j7 = jArr4[6] ^ jArr4[3];
        jArr4[6] = j7;
        long j8 = jArr4[7] ^ jArr4[4];
        jArr4[7] = j8;
        long j9 = jArr4[8] ^ jArr4[5];
        jArr4[8] = j9;
        jArr[0] = jArr[0] ^ jArr4[0];
        jArr[1] = jArr[1] ^ jArr4[1];
        jArr[2] = jArr[2] ^ jArr4[2];
        jArr[3] = jArr[3] ^ (j7 ^ jArr4[0]);
        jArr[5] = jArr[5] ^ (j9 ^ jArr4[2]);
        long j10 = jArr[6];
        long j11 = jArr4[9];
        jArr[6] = j10 ^ (j7 ^ j11);
        jArr[4] = jArr[4] ^ (j8 ^ jArr4[1]);
        long j12 = jArr[7];
        long j13 = jArr4[10];
        jArr[7] = j12 ^ (j8 ^ j13);
        long j14 = jArr[8];
        long j15 = jArr4[11];
        jArr[8] = j14 ^ (j9 ^ j15);
        jArr[9] = jArr[9] ^ j11;
        jArr[10] = jArr[10] ^ j13;
        jArr[11] = jArr[11] ^ j15;
        MUL64_NO_SIMD_GF2X(jArr4, 0, j, j4);
        MUL64_NO_SIMD_GF2X(jArr4, 4, j3, j6);
        MUL64_NO_SIMD_GF2X(jArr4, 2, j2, j5);
        long j16 = jArr[3];
        long j17 = jArr4[0];
        jArr[3] = j16 ^ j17;
        long j18 = jArr4[1] ^ jArr4[2];
        jArr4[1] = j18;
        long j19 = j17 ^ j18;
        jArr4[0] = j19;
        long j20 = jArr4[3] ^ jArr4[4];
        jArr4[3] = j20;
        long j21 = j20 ^ jArr4[5];
        jArr4[4] = j21;
        jArr[5] = jArr[5] ^ (j20 ^ j19);
        jArr[6] = (j18 ^ j21) ^ jArr[6];
        jArr[4] = jArr[4] ^ j19;
        jArr[7] = jArr[7] ^ jArr4[4];
        jArr[8] = jArr[8] ^ jArr4[5];
        MUL64_NO_SIMD_GF2X_XOR(jArr, 4, j ^ j2, j4 ^ j5);
        MUL64_NO_SIMD_GF2X_XOR(jArr, 6, j2 ^ j3, j5 ^ j6);
        MUL64_NO_SIMD_GF2X_XOR(jArr, 5, j ^ j3, j4 ^ j6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mul416_no_simd_gf2x(long[] jArr, long[] jArr2, int i, long[] jArr3, int i2, long[] jArr4) {
        mul192_no_simd_gf2x(jArr, 0, jArr2, i, jArr3, i2);
        int i3 = i + 3;
        int i4 = i + 4;
        int i5 = i2 + 3;
        int i6 = i2 + 4;
        mul128_no_simd_gf2x(jArr, 6, jArr2[i3], jArr2[i4], jArr3[i5], jArr3[i6]);
        int i7 = i + 5;
        int i8 = i2 + 5;
        MUL64_NO_SIMD_GF2X(jArr, 10, jArr2[i7], jArr3[i8]);
        int i9 = i + 6;
        int i10 = i2 + 6;
        long MUL32_NO_SIMD_GF2X = MUL32_NO_SIMD_GF2X(jArr2[i9], jArr3[i10]) ^ jArr[11];
        jArr[12] = MUL32_NO_SIMD_GF2X;
        jArr[11] = MUL32_NO_SIMD_GF2X ^ jArr[10];
        MUL64_NO_SIMD_GF2X_XOR(jArr, 11, jArr2[i9] ^ jArr2[i7], jArr3[i10] ^ jArr3[i8]);
        long j = jArr[8] ^ jArr[10];
        jArr[8] = j;
        long j2 = jArr[11] ^ jArr[9];
        jArr[11] = j2;
        jArr[10] = jArr[12] ^ j;
        jArr[8] = j ^ jArr[6];
        jArr[9] = jArr[7] ^ j2;
        mul128_no_simd_gf2x_xor(jArr, 8, jArr2[i7] ^ jArr2[i3], jArr2[i9] ^ jArr2[i4], jArr3[i8] ^ jArr3[i5], jArr3[i10] ^ jArr3[i6], jArr4);
        long j3 = jArr2[i3] ^ jArr2[i];
        long j4 = jArr2[i + 1] ^ jArr2[i4];
        long j5 = jArr2[i + 2] ^ jArr2[i7];
        long j6 = jArr2[i9];
        long j7 = jArr3[i2] ^ jArr3[i5];
        long j8 = jArr3[i2 + 1] ^ jArr3[i6];
        long j9 = jArr3[i2 + 2] ^ jArr3[i8];
        long j10 = jArr3[i10];
        jArr[6] = jArr[6] ^ jArr[3];
        jArr[7] = jArr[7] ^ jArr[4];
        jArr[8] = jArr[8] ^ jArr[5];
        mul128_no_simd_gf2x(jArr4, 0, j3, j4, j7, j8);
        MUL64_NO_SIMD_GF2X(jArr4, 4, j5, j9);
        long MUL32_NO_SIMD_GF2X2 = MUL32_NO_SIMD_GF2X(j6, j10) ^ jArr4[5];
        jArr4[6] = MUL32_NO_SIMD_GF2X2;
        jArr4[5] = MUL32_NO_SIMD_GF2X2 ^ jArr4[4];
        MUL64_NO_SIMD_GF2X_XOR(jArr4, 5, j5 ^ j6, j9 ^ j10);
        long j11 = jArr[6];
        long j12 = jArr[0] ^ j11;
        long j13 = jArr4[0];
        jArr[3] = j12 ^ j13;
        long j14 = jArr[7];
        long j15 = j14 ^ jArr[1];
        long j16 = jArr4[1];
        jArr[4] = j15 ^ j16;
        long j17 = jArr4[2] ^ jArr4[4];
        jArr4[2] = j17;
        long j18 = jArr4[3] ^ jArr4[5];
        jArr4[3] = j18;
        long j19 = jArr[8];
        jArr[5] = ((j19 ^ jArr[2]) ^ j17) ^ j13;
        long j20 = jArr[9];
        jArr[6] = j11 ^ ((j20 ^ j18) ^ j16);
        long j21 = jArr[10] ^ j17;
        long j22 = jArr4[6];
        jArr[7] = (j21 ^ j22) ^ j14;
        jArr[8] = j19 ^ (jArr[11] ^ j18);
        jArr[9] = j20 ^ (jArr[12] ^ j22);
        mul128_no_simd_gf2x_xor(jArr, 5, j3 ^ j5, j4 ^ j6, j7 ^ j9, j8 ^ j10, jArr4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mul416_no_simd_gf2x_xor(long[] jArr, long[] jArr2, int i, long[] jArr3, int i2, long[] jArr4, long[] jArr5) {
        mul192_no_simd_gf2x(jArr4, 0, jArr2, i, jArr3, i2);
        int i3 = i + 3;
        int i4 = i + 4;
        int i5 = i2 + 3;
        int i6 = i2 + 4;
        mul128_no_simd_gf2x(jArr4, 6, jArr2[i3], jArr2[i4], jArr3[i5], jArr3[i6]);
        int i7 = i + 5;
        int i8 = i2 + 5;
        MUL64_NO_SIMD_GF2X(jArr4, 10, jArr2[i7], jArr3[i8]);
        int i9 = i + 6;
        int i10 = i2 + 6;
        long MUL32_NO_SIMD_GF2X = MUL32_NO_SIMD_GF2X(jArr2[i9], jArr3[i10]) ^ jArr4[11];
        jArr4[12] = MUL32_NO_SIMD_GF2X;
        jArr4[11] = MUL32_NO_SIMD_GF2X ^ jArr4[10];
        MUL64_NO_SIMD_GF2X_XOR(jArr4, 11, jArr2[i9] ^ jArr2[i7], jArr3[i10] ^ jArr3[i8]);
        long j = jArr4[8] ^ jArr4[10];
        jArr4[8] = j;
        long j2 = jArr4[11] ^ jArr4[9];
        jArr4[11] = j2;
        jArr4[10] = jArr4[12] ^ j;
        long j3 = jArr4[6];
        long j4 = j ^ j3;
        jArr4[8] = j4;
        long j5 = jArr4[7];
        jArr4[9] = j2 ^ j5;
        jArr4[6] = jArr4[3] ^ j3;
        jArr4[7] = jArr4[4] ^ j5;
        jArr4[8] = j4 ^ jArr4[5];
        mul128_no_simd_gf2x_xor(jArr4, 8, jArr2[i7] ^ jArr2[i3], jArr2[i9] ^ jArr2[i4], jArr3[i8] ^ jArr3[i5], jArr3[i10] ^ jArr3[i6], jArr5);
        jArr[0] = jArr[0] ^ jArr4[0];
        jArr[1] = jArr[1] ^ jArr4[1];
        jArr[2] = jArr[2] ^ jArr4[2];
        long j6 = jArr[3];
        long j7 = jArr4[6];
        jArr[3] = j6 ^ (jArr4[0] ^ j7);
        long j8 = jArr[4];
        long j9 = jArr4[7];
        jArr[4] = j8 ^ (jArr4[1] ^ j9);
        long j10 = jArr[5];
        long j11 = jArr4[8];
        jArr[5] = j10 ^ (jArr4[2] ^ j11);
        long j12 = jArr[6];
        long j13 = jArr4[9];
        jArr[6] = j12 ^ (j7 ^ j13);
        long j14 = jArr[7];
        long j15 = jArr4[10];
        jArr[7] = j14 ^ (j9 ^ j15);
        long j16 = jArr[8];
        long j17 = jArr4[11];
        jArr[8] = j16 ^ (j11 ^ j17);
        long j18 = jArr[9];
        long j19 = jArr4[12];
        jArr[9] = j18 ^ (j13 ^ j19);
        jArr[10] = jArr[10] ^ j15;
        jArr[11] = jArr[11] ^ j17;
        jArr[12] = jArr[12] ^ j19;
        long j20 = jArr2[i3] ^ jArr2[i];
        long j21 = jArr2[i4] ^ jArr2[i + 1];
        long j22 = jArr2[i + 2] ^ jArr2[i7];
        long j23 = jArr2[i9];
        long j24 = jArr3[i2] ^ jArr3[i5];
        long j25 = jArr3[i2 + 1] ^ jArr3[i6];
        long j26 = jArr3[i2 + 2] ^ jArr3[i8];
        long j27 = jArr3[i10];
        mul128_no_simd_gf2x(jArr4, 0, j20, j21, j24, j25);
        MUL64_NO_SIMD_GF2X(jArr4, 4, j22, j26);
        long MUL32_NO_SIMD_GF2X2 = MUL32_NO_SIMD_GF2X(j23, j27) ^ jArr4[5];
        jArr4[6] = MUL32_NO_SIMD_GF2X2;
        jArr4[5] = MUL32_NO_SIMD_GF2X2 ^ jArr4[4];
        MUL64_NO_SIMD_GF2X_XOR(jArr4, 5, j22 ^ j23, j26 ^ j27);
        long j28 = jArr[3];
        long j29 = jArr4[0];
        jArr[3] = j28 ^ j29;
        long j30 = jArr[4];
        long j31 = jArr4[1];
        jArr[4] = j30 ^ j31;
        long j32 = jArr4[2] ^ jArr4[4];
        jArr4[2] = j32;
        long j33 = jArr4[3] ^ jArr4[5];
        jArr4[3] = j33;
        jArr[5] = jArr[5] ^ (j29 ^ j32);
        jArr[6] = jArr[6] ^ (j33 ^ j31);
        long j34 = jArr[7];
        long j35 = jArr4[6];
        jArr[7] = (j32 ^ j35) ^ j34;
        jArr[8] = jArr[8] ^ j33;
        jArr[9] = jArr[9] ^ j35;
        mul128_no_simd_gf2x_xor(jArr, 5, j20 ^ j22, j21 ^ j23, j24 ^ j26, j25 ^ j27, jArr4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mul544_no_simd_gf2x(long[] jArr, long[] jArr2, int i, long[] jArr3, int i2, long[] jArr4, long[] jArr5, long[] jArr6) {
        int i3 = i + 1;
        int i4 = i2 + 1;
        mul128_no_simd_gf2x(jArr, 0, jArr2[i], jArr2[i3], jArr3[i2], jArr3[i4]);
        int i5 = i + 2;
        int i6 = i + 3;
        int i7 = i2 + 2;
        int i8 = i2 + 3;
        mul128_no_simd_gf2x(jArr, 4, jArr2[i5], jArr2[i6], jArr3[i7], jArr3[i8]);
        long j = jArr[4] ^ jArr[2];
        jArr[4] = j;
        long j2 = jArr[5] ^ jArr[3];
        jArr[5] = j2;
        jArr[2] = jArr[0] ^ j;
        jArr[3] = jArr[1] ^ j2;
        jArr[4] = j ^ jArr[6];
        jArr[5] = j2 ^ jArr[7];
        mul128_no_simd_gf2x_xor(jArr, 2, jArr2[i5] ^ jArr2[i], jArr2[i3] ^ jArr2[i6], jArr3[i2] ^ jArr3[i7], jArr3[i4] ^ jArr3[i8], jArr6);
        int i9 = i + 4;
        int i10 = i2 + 4;
        mul288_no_simd_gf2x(jArr, 8, jArr2, i9, jArr3, i10, jArr6);
        long j3 = jArr[8] ^ jArr[4];
        jArr[8] = j3;
        long j4 = jArr[9] ^ jArr[5];
        jArr[9] = j4;
        long j5 = jArr[10] ^ jArr[6];
        jArr[10] = j5;
        long j6 = jArr[11] ^ jArr[7];
        jArr[11] = j6;
        jArr[4] = j3 ^ jArr[0];
        jArr[5] = j4 ^ jArr[1];
        jArr[6] = j5 ^ jArr[2];
        jArr[7] = j6 ^ jArr[3];
        long j7 = jArr[12];
        jArr[8] = j3 ^ j7;
        jArr[9] = j4 ^ jArr[13];
        jArr[10] = j5 ^ jArr[14];
        jArr[11] = j6 ^ jArr[15];
        jArr[12] = j7 ^ jArr[16];
        jArr4[0] = jArr2[i] ^ jArr2[i9];
        jArr4[1] = jArr2[i3] ^ jArr2[i + 5];
        jArr4[2] = jArr2[i5] ^ jArr2[i + 6];
        jArr4[3] = jArr2[i6] ^ jArr2[i + 7];
        jArr4[4] = jArr2[i + 8];
        jArr5[0] = jArr3[i2] ^ jArr3[i10];
        jArr5[1] = jArr3[i4] ^ jArr3[i2 + 5];
        jArr5[2] = jArr3[i7] ^ jArr3[i2 + 6];
        jArr5[3] = jArr3[i8] ^ jArr3[i2 + 7];
        jArr5[4] = jArr3[i2 + 8];
        mul288_no_simd_gf2x_xor(jArr, 4, jArr4, 0, jArr5, 0, jArr6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mul544_no_simd_gf2x_xor(long[] jArr, long[] jArr2, int i, long[] jArr3, int i2, long[] jArr4, long[] jArr5, long[] jArr6, long[] jArr7) {
        int i3 = i + 1;
        int i4 = i2 + 1;
        mul128_no_simd_gf2x(jArr6, 0, jArr2[i], jArr2[i3], jArr3[i2], jArr3[i4]);
        int i5 = i + 2;
        int i6 = i + 3;
        int i7 = i2 + 2;
        int i8 = i2 + 3;
        mul128_no_simd_gf2x(jArr6, 4, jArr2[i5], jArr2[i6], jArr3[i7], jArr3[i8]);
        long j = jArr6[4] ^ jArr6[2];
        jArr6[4] = j;
        long j2 = jArr6[5] ^ jArr6[3];
        jArr6[5] = j2;
        jArr6[2] = jArr6[0] ^ j;
        jArr6[3] = jArr6[1] ^ j2;
        jArr6[4] = j ^ jArr6[6];
        jArr6[5] = j2 ^ jArr6[7];
        mul128_no_simd_gf2x_xor(jArr6, 2, jArr2[i5] ^ jArr2[i], jArr2[i3] ^ jArr2[i6], jArr3[i2] ^ jArr3[i7], jArr3[i4] ^ jArr3[i8], jArr7);
        int i9 = i + 4;
        int i10 = i2 + 4;
        mul288_no_simd_gf2x(jArr6, 8, jArr2, i9, jArr3, i10, jArr7);
        long j3 = jArr6[8] ^ jArr6[4];
        jArr6[8] = j3;
        long j4 = jArr6[9] ^ jArr6[5];
        jArr6[9] = j4;
        long j5 = jArr6[10] ^ jArr6[6];
        jArr6[10] = j5;
        long j6 = jArr6[11] ^ jArr6[7];
        jArr6[11] = j6;
        jArr[0] = jArr[0] ^ jArr6[0];
        jArr[1] = jArr[1] ^ jArr6[1];
        jArr[2] = jArr[2] ^ jArr6[2];
        jArr[3] = jArr[3] ^ jArr6[3];
        jArr[4] = jArr[4] ^ (j3 ^ jArr6[0]);
        jArr[5] = jArr[5] ^ (j4 ^ jArr6[1]);
        jArr[6] = jArr[6] ^ (j5 ^ jArr6[2]);
        jArr[7] = jArr[7] ^ (j6 ^ jArr6[3]);
        long j7 = jArr[8];
        long j8 = jArr6[12];
        jArr[8] = j7 ^ (j3 ^ j8);
        long j9 = jArr[9];
        long j10 = jArr6[13];
        jArr[9] = j9 ^ (j4 ^ j10);
        long j11 = jArr[10];
        long j12 = jArr6[14];
        jArr[10] = j11 ^ (j5 ^ j12);
        long j13 = jArr[11];
        long j14 = jArr6[15];
        jArr[11] = j13 ^ (j6 ^ j14);
        long j15 = jArr[12];
        long j16 = jArr6[16];
        jArr[12] = j15 ^ (j8 ^ j16);
        jArr[13] = jArr[13] ^ j10;
        jArr[14] = jArr[14] ^ j12;
        jArr[15] = jArr[15] ^ j14;
        jArr[16] = jArr[16] ^ j16;
        jArr4[0] = jArr2[i] ^ jArr2[i9];
        jArr4[1] = jArr2[i3] ^ jArr2[i + 5];
        jArr4[2] = jArr2[i5] ^ jArr2[i + 6];
        jArr4[3] = jArr2[i6] ^ jArr2[i + 7];
        jArr4[4] = jArr2[i + 8];
        jArr5[0] = jArr3[i2] ^ jArr3[i10];
        jArr5[1] = jArr3[i4] ^ jArr3[i2 + 5];
        jArr5[2] = jArr3[i7] ^ jArr3[i2 + 6];
        jArr5[3] = jArr3[i8] ^ jArr3[i2 + 7];
        jArr5[4] = jArr3[i2 + 8];
        mul288_no_simd_gf2x_xor(jArr, 4, jArr4, 0, jArr5, 0, jArr6);
    }

    public abstract void mul_gf2x(Pointer pointer, Pointer pointer2, Pointer pointer3);

    public abstract void mul_gf2x_xor(Pointer pointer, Pointer pointer2, Pointer pointer3);

    public abstract void sqr_gf2x(long[] jArr, long[] jArr2, int i);
}
