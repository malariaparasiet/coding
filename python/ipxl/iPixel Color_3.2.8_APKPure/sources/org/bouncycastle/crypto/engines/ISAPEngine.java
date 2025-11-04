package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.internal.asm.Opcodes;
import kotlin.UByte;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AEADBaseEngine;
import org.bouncycastle.crypto.engines.AsconPermutationFriend;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class ISAPEngine extends AEADBaseEngine {
    private static final int ISAP_STATE_SZ = 40;
    private final ISAP_AEAD ISAPAEAD;
    private int ISAP_rH;
    private byte[] k;
    private byte[] npub;

    private abstract class ISAPAEAD_A implements ISAP_AEAD {
        protected long ISAP_IV1_64;
        protected long ISAP_IV2_64;
        protected long ISAP_IV3_64;
        protected long[] k64;
        AsconPermutationFriend.AsconPermutation mac;
        protected long[] npub64;
        AsconPermutationFriend.AsconPermutation p;

        public ISAPAEAD_A() {
            ISAPEngine.this.ISAP_rH = 64;
            ISAPEngine.this.BlockSize = (ISAPEngine.this.ISAP_rH + 7) >> 3;
            this.p = new AsconPermutationFriend.AsconPermutation();
            this.mac = new AsconPermutationFriend.AsconPermutation();
        }

        private int getLongSize(int i) {
            return (i + 7) >>> 3;
        }

        private void isap_rk(AsconPermutationFriend.AsconPermutation asconPermutation, long j, byte[] bArr, int i) {
            long[] jArr = this.k64;
            asconPermutation.set(jArr[0], jArr[1], j, 0L, 0L);
            asconPermutation.p(12);
            for (int i2 = 0; i2 < (i << 3) - 1; i2++) {
                asconPermutation.x0 ^= ((((bArr[i2 >>> 3] >>> (7 - (i2 & 7))) & 1) << 7) & 255) << 56;
                PX2(asconPermutation);
            }
            asconPermutation.x0 ^= (bArr[i - 1] & 1) << 63;
            asconPermutation.p(12);
        }

        protected abstract void PX1(AsconPermutationFriend.AsconPermutation asconPermutation);

        protected abstract void PX2(AsconPermutationFriend.AsconPermutation asconPermutation);

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void absorbFinalAADBlock() {
            for (int i = 0; i < ISAPEngine.this.m_aadPos; i++) {
                this.mac.x0 ^= (ISAPEngine.this.m_aad[i] & 255) << ((7 - i) << 3);
            }
            this.mac.x0 ^= 128 << ((7 - ISAPEngine.this.m_aadPos) << 3);
            this.mac.p(12);
            this.mac.x4 ^= 1;
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void absorbMacBlock(byte[] bArr, int i) {
            AsconPermutationFriend.AsconPermutation asconPermutation = this.mac;
            asconPermutation.x0 = Pack.bigEndianToLong(bArr, i) ^ asconPermutation.x0;
            this.mac.p(12);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void init() {
            this.npub64 = new long[getLongSize(ISAPEngine.this.npub.length)];
            this.k64 = new long[getLongSize(ISAPEngine.this.k.length)];
            Pack.bigEndianToLong(ISAPEngine.this.npub, 0, this.npub64);
            Pack.bigEndianToLong(ISAPEngine.this.k, 0, this.k64);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processEncBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
            Pack.longToBigEndian(Pack.bigEndianToLong(bArr, i) ^ this.p.x0, bArr2, i2);
            PX1(this.p);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processEncFinalBlock(byte[] bArr, int i) {
            Bytes.xor(ISAPEngine.this.m_bufPos, Pack.longToLittleEndian(this.p.x0), ISAPEngine.this.BlockSize - ISAPEngine.this.m_bufPos, ISAPEngine.this.m_buf, 0, bArr, i);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processMACFinal(byte[] bArr, int i, int i2, byte[] bArr2) {
            int i3 = i;
            int i4 = 0;
            while (i4 < i2) {
                this.mac.x0 ^= (bArr[i3] & 255) << ((7 - i4) << 3);
                i4++;
                i3++;
            }
            this.mac.x0 ^= 128 << ((7 - i2) << 3);
            this.mac.p(12);
            Pack.longToBigEndian(this.mac.x0, bArr2, 0);
            Pack.longToBigEndian(this.mac.x1, bArr2, 8);
            long j = this.mac.x2;
            long j2 = this.mac.x3;
            long j3 = this.mac.x4;
            isap_rk(this.mac, this.ISAP_IV2_64, bArr2, ISAPEngine.this.KEY_SIZE);
            this.mac.x2 = j;
            this.mac.x3 = j2;
            this.mac.x4 = j3;
            this.mac.p(12);
            Pack.longToBigEndian(this.mac.x0, bArr2, 0);
            Pack.longToBigEndian(this.mac.x1, bArr2, 8);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void reset() {
            isap_rk(this.p, this.ISAP_IV3_64, ISAPEngine.this.npub, ISAPEngine.this.IV_SIZE);
            this.p.x3 = this.npub64[0];
            this.p.x4 = this.npub64[1];
            PX1(this.p);
            AsconPermutationFriend.AsconPermutation asconPermutation = this.mac;
            long[] jArr = this.npub64;
            asconPermutation.set(jArr[0], jArr[1], this.ISAP_IV1_64, 0L, 0L);
            this.mac.p(12);
        }
    }

    private class ISAPAEAD_A_128 extends ISAPAEAD_A {
        public ISAPAEAD_A_128() {
            super();
            this.ISAP_IV1_64 = 108156764298152972L;
            this.ISAP_IV2_64 = 180214358336080908L;
            this.ISAP_IV3_64 = 252271952374008844L;
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_A
        protected void PX1(AsconPermutationFriend.AsconPermutation asconPermutation) {
            asconPermutation.p(12);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_A
        protected void PX2(AsconPermutationFriend.AsconPermutation asconPermutation) {
            asconPermutation.p(12);
        }
    }

    private class ISAPAEAD_A_128A extends ISAPAEAD_A {
        public ISAPAEAD_A_128A() {
            super();
            this.ISAP_IV1_64 = 108156764297430540L;
            this.ISAP_IV2_64 = 180214358335358476L;
            this.ISAP_IV3_64 = 252271952373286412L;
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_A
        protected void PX1(AsconPermutationFriend.AsconPermutation asconPermutation) {
            asconPermutation.p(6);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_A
        protected void PX2(AsconPermutationFriend.AsconPermutation asconPermutation) {
            asconPermutation.round(75L);
        }
    }

    private abstract class ISAPAEAD_K implements ISAP_AEAD {
        protected short[] ISAP_IV1_16;
        protected short[] ISAP_IV2_16;
        protected short[] ISAP_IV3_16;
        protected final int ISAP_STATE_SZ_CRYPTO_NPUBBYTES;
        protected short[] iv16;
        protected short[] k16;
        private final int[] KeccakF400RoundConstants = {1, 32898, 32906, 32768, 32907, 1, 32897, 32777, 138, Opcodes.L2I, 32777, 10, 32907, Opcodes.F2I, 32905, 32771, 32770, 128, 32778, 10};
        protected short[] SX = new short[25];
        protected short[] macSX = new short[25];
        protected short[] E = new short[25];
        protected short[] C = new short[5];
        protected short[] macE = new short[25];
        protected short[] macC = new short[5];

        public ISAPAEAD_K() {
            this.ISAP_STATE_SZ_CRYPTO_NPUBBYTES = 40 - ISAPEngine.this.IV_SIZE;
            ISAPEngine.this.ISAP_rH = 144;
            ISAPEngine.this.BlockSize = (ISAPEngine.this.ISAP_rH + 7) >> 3;
        }

        private short ROL16(short s, int i) {
            int i2 = s & 65535;
            return (short) ((i2 >>> (16 - i)) ^ (i2 << i));
        }

        private void byteToShortXor(byte[] bArr, int i, short[] sArr, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                sArr[i3] = (short) (sArr[i3] ^ Pack.littleEndianToShort(bArr, (i3 << 1) + i));
            }
        }

        protected abstract void PermuteRoundsBX(short[] sArr, short[] sArr2, short[] sArr3);

        protected abstract void PermuteRoundsHX(short[] sArr, short[] sArr2, short[] sArr3);

        protected abstract void PermuteRoundsKX(short[] sArr, short[] sArr2, short[] sArr3);

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void absorbFinalAADBlock() {
            for (int i = 0; i < ISAPEngine.this.m_aadPos; i++) {
                short[] sArr = this.macSX;
                int i2 = i >> 1;
                sArr[i2] = (short) (sArr[i2] ^ ((ISAPEngine.this.m_aad[i] & UByte.MAX_VALUE) << ((i & 1) << 3)));
            }
            short[] sArr2 = this.macSX;
            int i3 = ISAPEngine.this.m_aadPos >> 1;
            sArr2[i3] = (short) (sArr2[i3] ^ (128 << ((ISAPEngine.this.m_aadPos & 1) << 3)));
            PermuteRoundsHX(this.macSX, this.macE, this.macC);
            short[] sArr3 = this.macSX;
            sArr3[24] = (short) (sArr3[24] ^ 256);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void absorbMacBlock(byte[] bArr, int i) {
            byteToShortXor(bArr, i, this.macSX, ISAPEngine.this.BlockSize >> 1);
            PermuteRoundsHX(this.macSX, this.macE, this.macC);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void init() {
            this.k16 = new short[ISAPEngine.this.k.length >> 1];
            byte[] bArr = ISAPEngine.this.k;
            short[] sArr = this.k16;
            Pack.littleEndianToShort(bArr, 0, sArr, 0, sArr.length);
            this.iv16 = new short[ISAPEngine.this.npub.length >> 1];
            byte[] bArr2 = ISAPEngine.this.npub;
            short[] sArr2 = this.iv16;
            Pack.littleEndianToShort(bArr2, 0, sArr2, 0, sArr2.length);
        }

        public void isap_rk(short[] sArr, byte[] bArr, int i, short[] sArr2, int i2, short[] sArr3) {
            short[] sArr4 = new short[25];
            short[] sArr5 = new short[25];
            System.arraycopy(this.k16, 0, sArr4, 0, 8);
            System.arraycopy(sArr, 0, sArr4, 8, 4);
            PermuteRoundsKX(sArr4, sArr5, sArr3);
            for (int i3 = 0; i3 < (i << 3) - 1; i3++) {
                sArr4[0] = (short) (sArr4[0] ^ (((bArr[i3 >> 3] >>> (7 - (i3 & 7))) & 1) << 7));
                PermuteRoundsBX(sArr4, sArr5, sArr3);
            }
            sArr4[0] = (short) (sArr4[0] ^ ((bArr[i - 1] & 1) << 7));
            PermuteRoundsKX(sArr4, sArr5, sArr3);
            System.arraycopy(sArr4, 0, sArr2, 0, i2 == this.ISAP_STATE_SZ_CRYPTO_NPUBBYTES ? 17 : 8);
        }

        protected void prepareThetaX(short[] sArr, short[] sArr2) {
            sArr2[0] = (short) ((((sArr[0] ^ sArr[5]) ^ sArr[10]) ^ sArr[15]) ^ sArr[20]);
            sArr2[1] = (short) ((((sArr[1] ^ sArr[6]) ^ sArr[11]) ^ sArr[16]) ^ sArr[21]);
            sArr2[2] = (short) ((((sArr[2] ^ sArr[7]) ^ sArr[12]) ^ sArr[17]) ^ sArr[22]);
            sArr2[3] = (short) ((((sArr[3] ^ sArr[8]) ^ sArr[13]) ^ sArr[18]) ^ sArr[23]);
            sArr2[4] = (short) (sArr[24] ^ (((sArr[4] ^ sArr[9]) ^ sArr[14]) ^ sArr[19]));
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processEncBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
            int i3 = 0;
            while (i3 < ISAPEngine.this.BlockSize) {
                bArr2[i2] = (byte) (bArr[i] ^ (this.SX[i3 >> 1] >>> ((i3 & 1) << 3)));
                i3++;
                i2++;
                i++;
            }
            PermuteRoundsKX(this.SX, this.E, this.C);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processEncFinalBlock(byte[] bArr, int i) {
            int i2 = 0;
            while (i2 < ISAPEngine.this.m_bufPos) {
                bArr[i] = (byte) ((this.SX[i2 >> 1] >>> ((i2 & 1) << 3)) ^ ISAPEngine.this.m_buf[i2]);
                i2++;
                i++;
            }
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processMACFinal(byte[] bArr, int i, int i2, byte[] bArr2) {
            int i3 = 0;
            while (i3 < i2) {
                short[] sArr = this.macSX;
                int i4 = i3 >> 1;
                sArr[i4] = (short) (((bArr[i] & 255) << ((i3 & 1) << 3)) ^ sArr[i4]);
                i3++;
                i++;
            }
            short[] sArr2 = this.macSX;
            int i5 = i2 >> 1;
            sArr2[i5] = (short) ((128 << ((i2 & 1) << 3)) ^ sArr2[i5]);
            PermuteRoundsHX(sArr2, this.macE, this.macC);
            Pack.shortToLittleEndian(this.macSX, 0, 8, bArr2, 0);
            isap_rk(this.ISAP_IV2_16, bArr2, ISAPEngine.this.KEY_SIZE, this.macSX, ISAPEngine.this.KEY_SIZE, this.macC);
            PermuteRoundsHX(this.macSX, this.macE, this.macC);
            Pack.shortToLittleEndian(this.macSX, 0, 8, bArr2, 0);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void reset() {
            Arrays.fill(this.SX, (short) 0);
            isap_rk(this.ISAP_IV3_16, ISAPEngine.this.npub, ISAPEngine.this.IV_SIZE, this.SX, this.ISAP_STATE_SZ_CRYPTO_NPUBBYTES, this.C);
            System.arraycopy(this.iv16, 0, this.SX, 17, 8);
            PermuteRoundsKX(this.SX, this.E, this.C);
            Arrays.fill(this.macSX, 12, 25, (short) 0);
            System.arraycopy(this.iv16, 0, this.macSX, 0, 8);
            System.arraycopy(this.ISAP_IV1_16, 0, this.macSX, 8, 4);
            PermuteRoundsHX(this.macSX, this.macE, this.macC);
        }

        protected void rounds12X(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            rounds_8_18(sArr, sArr2, sArr3);
        }

        protected void rounds_12_18(short[] sArr, short[] sArr2, short[] sArr3) {
            thetaRhoPiChiIotaPrepareTheta(12, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(13, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(14, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(15, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(16, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(17, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(18, sArr, sArr2, sArr3);
            thetaRhoPiChiIota(sArr2, sArr, sArr3);
        }

        protected void rounds_4_18(short[] sArr, short[] sArr2, short[] sArr3) {
            thetaRhoPiChiIotaPrepareTheta(4, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(5, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(6, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(7, sArr2, sArr, sArr3);
            rounds_8_18(sArr, sArr2, sArr3);
        }

        protected void rounds_8_18(short[] sArr, short[] sArr2, short[] sArr3) {
            thetaRhoPiChiIotaPrepareTheta(8, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(9, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(10, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(11, sArr2, sArr, sArr3);
            rounds_12_18(sArr, sArr2, sArr3);
        }

        protected void thetaRhoPiChiIota(short[] sArr, short[] sArr2, short[] sArr3) {
            short ROL16 = (short) (sArr3[4] ^ ROL16(sArr3[1], 1));
            short ROL162 = (short) (sArr3[0] ^ ROL16(sArr3[2], 1));
            short ROL163 = (short) (sArr3[1] ^ ROL16(sArr3[3], 1));
            short ROL164 = (short) (sArr3[2] ^ ROL16(sArr3[4], 1));
            short ROL165 = (short) (sArr3[3] ^ ROL16(sArr3[0], 1));
            short s = (short) (sArr[0] ^ ROL16);
            sArr[0] = s;
            short s2 = (short) (sArr[6] ^ ROL162);
            sArr[6] = s2;
            short ROL166 = ROL16(s2, 12);
            short s3 = (short) (sArr[12] ^ ROL163);
            sArr[12] = s3;
            short ROL167 = ROL16(s3, 11);
            short s4 = (short) (sArr[18] ^ ROL164);
            sArr[18] = s4;
            short ROL168 = ROL16(s4, 5);
            short s5 = (short) (sArr[24] ^ ROL165);
            sArr[24] = s5;
            short ROL169 = ROL16(s5, 14);
            sArr2[0] = (short) (this.KeccakF400RoundConstants[19] ^ (((~ROL166) & ROL167) ^ s));
            sArr2[1] = (short) (((~ROL167) & ROL168) ^ ROL166);
            sArr2[2] = (short) (((~ROL168) & ROL169) ^ ROL167);
            sArr2[3] = (short) (((~ROL169) & s) ^ ROL168);
            sArr2[4] = (short) (((~s) & ROL166) ^ ROL169);
            short s6 = (short) (sArr[3] ^ ROL164);
            sArr[3] = s6;
            short ROL1610 = ROL16(s6, 12);
            short s7 = (short) (sArr[9] ^ ROL165);
            sArr[9] = s7;
            short ROL1611 = ROL16(s7, 4);
            short s8 = (short) (sArr[10] ^ ROL16);
            sArr[10] = s8;
            short ROL1612 = ROL16(s8, 3);
            short s9 = (short) (sArr[16] ^ ROL162);
            sArr[16] = s9;
            short ROL1613 = ROL16(s9, 13);
            short s10 = (short) (sArr[22] ^ ROL163);
            sArr[22] = s10;
            short ROL1614 = ROL16(s10, 13);
            sArr2[5] = (short) (((~ROL1611) & ROL1612) ^ ROL1610);
            sArr2[6] = (short) (((~ROL1612) & ROL1613) ^ ROL1611);
            sArr2[7] = (short) (ROL1612 ^ ((~ROL1613) & ROL1614));
            sArr2[8] = (short) (((~ROL1614) & ROL1610) ^ ROL1613);
            sArr2[9] = (short) (((~ROL1610) & ROL1611) ^ ROL1614);
            short s11 = (short) (sArr[1] ^ ROL162);
            sArr[1] = s11;
            short ROL1615 = ROL16(s11, 1);
            short s12 = (short) (sArr[7] ^ ROL163);
            sArr[7] = s12;
            short ROL1616 = ROL16(s12, 6);
            short s13 = (short) (sArr[13] ^ ROL164);
            sArr[13] = s13;
            short ROL1617 = ROL16(s13, 9);
            short s14 = (short) (sArr[19] ^ ROL165);
            sArr[19] = s14;
            short ROL1618 = ROL16(s14, 8);
            short s15 = (short) (sArr[20] ^ ROL16);
            sArr[20] = s15;
            short ROL1619 = ROL16(s15, 2);
            sArr2[10] = (short) (((~ROL1616) & ROL1617) ^ ROL1615);
            sArr2[11] = (short) (((~ROL1617) & ROL1618) ^ ROL1616);
            sArr2[12] = (short) (((~ROL1618) & ROL1619) ^ ROL1617);
            sArr2[13] = (short) (((~ROL1619) & ROL1615) ^ ROL1618);
            sArr2[14] = (short) (((~ROL1615) & ROL1616) ^ ROL1619);
            short s16 = (short) (sArr[4] ^ ROL165);
            sArr[4] = s16;
            short ROL1620 = ROL16(s16, 11);
            short s17 = (short) (sArr[5] ^ ROL16);
            sArr[5] = s17;
            short ROL1621 = ROL16(s17, 4);
            short s18 = (short) (sArr[11] ^ ROL162);
            sArr[11] = s18;
            short ROL1622 = ROL16(s18, 10);
            short s19 = (short) (sArr[17] ^ ROL163);
            sArr[17] = s19;
            short ROL1623 = ROL16(s19, 15);
            short s20 = (short) (sArr[23] ^ ROL164);
            sArr[23] = s20;
            short ROL1624 = ROL16(s20, 8);
            sArr2[15] = (short) (((~ROL1621) & ROL1622) ^ ROL1620);
            sArr2[16] = (short) (((~ROL1622) & ROL1623) ^ ROL1621);
            sArr2[17] = (short) (ROL1622 ^ ((~ROL1623) & ROL1624));
            sArr2[18] = (short) (((~ROL1624) & ROL1620) ^ ROL1623);
            sArr2[19] = (short) ((ROL1621 & (~ROL1620)) ^ ROL1624);
            short s21 = (short) (sArr[2] ^ ROL163);
            sArr[2] = s21;
            short ROL1625 = ROL16(s21, 14);
            short s22 = (short) (sArr[8] ^ ROL164);
            sArr[8] = s22;
            short ROL1626 = ROL16(s22, 7);
            short s23 = (short) (sArr[14] ^ ROL165);
            sArr[14] = s23;
            short ROL1627 = ROL16(s23, 7);
            short s24 = (short) (ROL16 ^ sArr[15]);
            sArr[15] = s24;
            short ROL1628 = ROL16(s24, 9);
            short s25 = (short) (ROL162 ^ sArr[21]);
            sArr[21] = s25;
            short ROL1629 = ROL16(s25, 2);
            sArr2[20] = (short) (((~ROL1626) & ROL1627) ^ ROL1625);
            sArr2[21] = (short) (((~ROL1627) & ROL1628) ^ ROL1626);
            sArr2[22] = (short) (ROL1627 ^ ((~ROL1628) & ROL1629));
            sArr2[23] = (short) (ROL1628 ^ ((~ROL1629) & ROL1625));
            sArr2[24] = (short) (((~ROL1625) & ROL1626) ^ ROL1629);
        }

        protected void thetaRhoPiChiIotaPrepareTheta(int i, short[] sArr, short[] sArr2, short[] sArr3) {
            short ROL16 = (short) (sArr3[4] ^ ROL16(sArr3[1], 1));
            short ROL162 = (short) (sArr3[0] ^ ROL16(sArr3[2], 1));
            short ROL163 = (short) (sArr3[1] ^ ROL16(sArr3[3], 1));
            short ROL164 = (short) (sArr3[2] ^ ROL16(sArr3[4], 1));
            short ROL165 = (short) (sArr3[3] ^ ROL16(sArr3[0], 1));
            short s = (short) (sArr[0] ^ ROL16);
            sArr[0] = s;
            short s2 = (short) (sArr[6] ^ ROL162);
            sArr[6] = s2;
            short ROL166 = ROL16(s2, 12);
            short s3 = (short) (sArr[12] ^ ROL163);
            sArr[12] = s3;
            short ROL167 = ROL16(s3, 11);
            short s4 = (short) (sArr[18] ^ ROL164);
            sArr[18] = s4;
            short ROL168 = ROL16(s4, 5);
            short s5 = (short) (sArr[24] ^ ROL165);
            sArr[24] = s5;
            short ROL169 = ROL16(s5, 14);
            short s6 = (short) (this.KeccakF400RoundConstants[i] ^ (((~ROL166) & ROL167) ^ s));
            sArr2[0] = s6;
            sArr3[0] = s6;
            short s7 = (short) (((~ROL167) & ROL168) ^ ROL166);
            sArr2[1] = s7;
            sArr3[1] = s7;
            short s8 = (short) (((~ROL168) & ROL169) ^ ROL167);
            sArr2[2] = s8;
            sArr3[2] = s8;
            short s9 = (short) (((~ROL169) & s) ^ ROL168);
            sArr2[3] = s9;
            sArr3[3] = s9;
            short s10 = (short) (((~s) & ROL166) ^ ROL169);
            sArr2[4] = s10;
            sArr3[4] = s10;
            short s11 = (short) (sArr[3] ^ ROL164);
            sArr[3] = s11;
            short ROL1610 = ROL16(s11, 12);
            short s12 = (short) (sArr[9] ^ ROL165);
            sArr[9] = s12;
            short ROL1611 = ROL16(s12, 4);
            short s13 = (short) (sArr[10] ^ ROL16);
            sArr[10] = s13;
            short ROL1612 = ROL16(s13, 3);
            short s14 = (short) (sArr[16] ^ ROL162);
            sArr[16] = s14;
            short ROL1613 = ROL16(s14, 13);
            short s15 = (short) (sArr[22] ^ ROL163);
            sArr[22] = s15;
            short ROL1614 = ROL16(s15, 13);
            short s16 = (short) (((~ROL1611) & ROL1612) ^ ROL1610);
            sArr2[5] = s16;
            sArr3[0] = (short) (sArr3[0] ^ s16);
            short s17 = (short) (((~ROL1612) & ROL1613) ^ ROL1611);
            sArr2[6] = s17;
            sArr3[1] = (short) (sArr3[1] ^ s17);
            short s18 = (short) (((~ROL1613) & ROL1614) ^ ROL1612);
            sArr2[7] = s18;
            sArr3[2] = (short) (sArr3[2] ^ s18);
            short s19 = (short) (((~ROL1614) & ROL1610) ^ ROL1613);
            sArr2[8] = s19;
            sArr3[3] = (short) (sArr3[3] ^ s19);
            short s20 = (short) (((~ROL1610) & ROL1611) ^ ROL1614);
            sArr2[9] = s20;
            sArr3[4] = (short) (s20 ^ sArr3[4]);
            short s21 = (short) (sArr[1] ^ ROL162);
            sArr[1] = s21;
            short ROL1615 = ROL16(s21, 1);
            short s22 = (short) (sArr[7] ^ ROL163);
            sArr[7] = s22;
            short ROL1616 = ROL16(s22, 6);
            short s23 = (short) (sArr[13] ^ ROL164);
            sArr[13] = s23;
            short ROL1617 = ROL16(s23, 9);
            short s24 = (short) (sArr[19] ^ ROL165);
            sArr[19] = s24;
            short ROL1618 = ROL16(s24, 8);
            short s25 = (short) (sArr[20] ^ ROL16);
            sArr[20] = s25;
            short ROL1619 = ROL16(s25, 2);
            short s26 = (short) (((~ROL1616) & ROL1617) ^ ROL1615);
            sArr2[10] = s26;
            sArr3[0] = (short) (sArr3[0] ^ s26);
            short s27 = (short) (((~ROL1617) & ROL1618) ^ ROL1616);
            sArr2[11] = s27;
            sArr3[1] = (short) (sArr3[1] ^ s27);
            short s28 = (short) (((~ROL1618) & ROL1619) ^ ROL1617);
            sArr2[12] = s28;
            sArr3[2] = (short) (s28 ^ sArr3[2]);
            short s29 = (short) (((~ROL1619) & ROL1615) ^ ROL1618);
            sArr2[13] = s29;
            sArr3[3] = (short) (s29 ^ sArr3[3]);
            short s30 = (short) (((~ROL1615) & ROL1616) ^ ROL1619);
            sArr2[14] = s30;
            sArr3[4] = (short) (s30 ^ sArr3[4]);
            short s31 = (short) (sArr[4] ^ ROL165);
            sArr[4] = s31;
            short ROL1620 = ROL16(s31, 11);
            short s32 = (short) (sArr[5] ^ ROL16);
            sArr[5] = s32;
            short ROL1621 = ROL16(s32, 4);
            short s33 = (short) (sArr[11] ^ ROL162);
            sArr[11] = s33;
            short ROL1622 = ROL16(s33, 10);
            short s34 = (short) (sArr[17] ^ ROL163);
            sArr[17] = s34;
            short ROL1623 = ROL16(s34, 15);
            short s35 = (short) (sArr[23] ^ ROL164);
            sArr[23] = s35;
            short ROL1624 = ROL16(s35, 8);
            short s36 = (short) (((~ROL1621) & ROL1622) ^ ROL1620);
            sArr2[15] = s36;
            sArr3[0] = (short) (sArr3[0] ^ s36);
            short s37 = (short) (((~ROL1622) & ROL1623) ^ ROL1621);
            sArr2[16] = s37;
            sArr3[1] = (short) (sArr3[1] ^ s37);
            short s38 = (short) (ROL1622 ^ ((~ROL1623) & ROL1624));
            sArr2[17] = s38;
            sArr3[2] = (short) (s38 ^ sArr3[2]);
            short s39 = (short) (((~ROL1624) & ROL1620) ^ ROL1623);
            sArr2[18] = s39;
            sArr3[3] = (short) (s39 ^ sArr3[3]);
            short s40 = (short) (((~ROL1620) & ROL1621) ^ ROL1624);
            sArr2[19] = s40;
            sArr3[4] = (short) (s40 ^ sArr3[4]);
            short s41 = (short) (sArr[2] ^ ROL163);
            sArr[2] = s41;
            short ROL1625 = ROL16(s41, 14);
            short s42 = (short) (sArr[8] ^ ROL164);
            sArr[8] = s42;
            short ROL1626 = ROL16(s42, 7);
            short s43 = (short) (sArr[14] ^ ROL165);
            sArr[14] = s43;
            short ROL1627 = ROL16(s43, 7);
            short s44 = (short) (ROL16 ^ sArr[15]);
            sArr[15] = s44;
            short ROL1628 = ROL16(s44, 9);
            short s45 = (short) (ROL162 ^ sArr[21]);
            sArr[21] = s45;
            short ROL1629 = ROL16(s45, 2);
            short s46 = (short) (((~ROL1626) & ROL1627) ^ ROL1625);
            sArr2[20] = s46;
            sArr3[0] = (short) (s46 ^ sArr3[0]);
            short s47 = (short) (((~ROL1627) & ROL1628) ^ ROL1626);
            sArr2[21] = s47;
            sArr3[1] = (short) (s47 ^ sArr3[1]);
            short s48 = (short) (ROL1627 ^ ((~ROL1628) & ROL1629));
            sArr2[22] = s48;
            sArr3[2] = (short) (s48 ^ sArr3[2]);
            short s49 = (short) (ROL1628 ^ ((~ROL1629) & ROL1625));
            sArr2[23] = s49;
            sArr3[3] = (short) (s49 ^ sArr3[3]);
            short s50 = (short) (((~ROL1625) & ROL1626) ^ ROL1629);
            sArr2[24] = s50;
            sArr3[4] = (short) (s50 ^ sArr3[4]);
        }
    }

    private class ISAPAEAD_K_128 extends ISAPAEAD_K {
        public ISAPAEAD_K_128() {
            super();
            this.ISAP_IV1_16 = new short[]{-32767, 400, 3092, 3084};
            this.ISAP_IV2_16 = new short[]{-32766, 400, 3092, 3084};
            this.ISAP_IV3_16 = new short[]{-32765, 400, 3092, 3084};
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsBX(short[] sArr, short[] sArr2, short[] sArr3) {
            rounds12X(sArr, sArr2, sArr3);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsHX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(0, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(1, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(2, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(3, sArr2, sArr, sArr3);
            rounds_4_18(sArr, sArr2, sArr3);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsKX(short[] sArr, short[] sArr2, short[] sArr3) {
            rounds12X(sArr, sArr2, sArr3);
        }
    }

    private class ISAPAEAD_K_128A extends ISAPAEAD_K {
        public ISAPAEAD_K_128A() {
            super();
            this.ISAP_IV1_16 = new short[]{-32767, 400, 272, 2056};
            this.ISAP_IV2_16 = new short[]{-32766, 400, 272, 2056};
            this.ISAP_IV3_16 = new short[]{-32765, 400, 272, 2056};
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsBX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(19, sArr, sArr2, sArr3);
            System.arraycopy(sArr2, 0, sArr, 0, sArr2.length);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsHX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            rounds_4_18(sArr, sArr2, sArr3);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsKX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            rounds_12_18(sArr, sArr2, sArr3);
        }
    }

    private interface ISAP_AEAD {
        void absorbFinalAADBlock();

        void absorbMacBlock(byte[] bArr, int i);

        void init();

        void processEncBlock(byte[] bArr, int i, byte[] bArr2, int i2);

        void processEncFinalBlock(byte[] bArr, int i);

        void processMACFinal(byte[] bArr, int i, int i2, byte[] bArr2);

        void reset();
    }

    public enum IsapType {
        ISAP_A_128A,
        ISAP_K_128A,
        ISAP_A_128,
        ISAP_K_128
    }

    public ISAPEngine(IsapType isapType) {
        String str;
        this.MAC_SIZE = 16;
        this.IV_SIZE = 16;
        this.KEY_SIZE = 16;
        int ordinal = isapType.ordinal();
        if (ordinal == 0) {
            this.ISAPAEAD = new ISAPAEAD_A_128A();
            str = "ISAP-A-128A AEAD";
        } else if (ordinal == 1) {
            this.ISAPAEAD = new ISAPAEAD_K_128A();
            str = "ISAP-K-128A AEAD";
        } else if (ordinal == 2) {
            this.ISAPAEAD = new ISAPAEAD_A_128();
            str = "ISAP-A-128 AEAD";
        } else {
            if (ordinal != 3) {
                throw new IllegalArgumentException("Incorrect ISAP parameter");
            }
            this.ISAPAEAD = new ISAPAEAD_K_128();
            str = "ISAP-K-128 AEAD";
        }
        this.algorithmName = str;
        this.AADBufferSize = this.BlockSize;
        setInnerMembers(AEADBaseEngine.ProcessingBufferType.Immediate, AEADBaseEngine.AADOperatorType.Default, AEADBaseEngine.DataOperatorType.Counter);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void finishAAD(AEADBaseEngine.State state, boolean z) {
        finishAAD3(state, z);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ String getAlgorithmName() {
        return super.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    public /* bridge */ /* synthetic */ int getIVBytesSize() {
        return super.getIVBytesSize();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    public /* bridge */ /* synthetic */ int getKeyBytesSize() {
        return super.getKeyBytesSize();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ byte[] getMac() {
        return super.getMac();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int getOutputSize(int i) {
        return super.getOutputSize(i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int getUpdateOutputSize(int i) {
        return super.getUpdateOutputSize(i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void init(boolean z, CipherParameters cipherParameters) {
        super.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void init(byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        this.npub = bArr2;
        this.k = bArr;
        this.ISAPAEAD.init();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADByte(byte b) {
        super.processAADByte(b);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADBytes(byte[] bArr, int i, int i2) {
        super.processAADBytes(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferAAD(byte[] bArr, int i) {
        this.ISAPAEAD.absorbMacBlock(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.ISAPAEAD.processEncBlock(bArr, i, bArr2, i2);
        this.ISAPAEAD.absorbMacBlock(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.ISAPAEAD.processEncBlock(bArr, i, bArr2, i2);
        this.ISAPAEAD.absorbMacBlock(bArr2, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return super.processByte(b, bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        return super.processBytes(bArr, i, i2, bArr2, i3);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalAAD() {
        this.ISAPAEAD.absorbFinalAADBlock();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        this.ISAPAEAD.processEncFinalBlock(bArr, i);
        if (this.forEncryption) {
            this.ISAPAEAD.processMACFinal(bArr, i, this.m_bufPos, this.mac);
        } else {
            this.ISAPAEAD.processMACFinal(this.m_buf, 0, this.m_bufPos, this.mac);
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        super.reset(z);
        this.ISAPAEAD.reset();
    }
}
