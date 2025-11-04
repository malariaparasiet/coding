package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import java.lang.reflect.Array;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.RomulusDigest;
import org.bouncycastle.crypto.engines.AEADBaseEngine;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;

/* loaded from: classes3.dex */
public class RomulusEngine extends AEADBaseEngine {
    private static final int AD_BLK_LEN_HALF = 16;
    private final byte[] CNT;
    private Instance instance;
    private byte[] k;
    private byte[] npub;
    private static final byte[] sbox_8 = {101, 76, 106, 66, 75, 99, 67, 107, 85, 117, 90, JSONB.Constants.BC_STR_UTF8, 83, 115, 91, JSONB.Constants.BC_STR_UTF16, 53, -116, 58, -127, -119, 51, ByteCompanionObject.MIN_VALUE, 59, -107, 37, -104, 42, JSONB.Constants.BC_CHAR, 35, -103, 43, -27, -52, -24, -63, -55, -32, JSONB.Constants.BC_INT64_SHORT_MIN, -23, -43, -11, JSONB.Constants.BC_INT64_NUM_MIN, -8, JSONB.Constants.BC_INT64_BYTE_ZERO, JSONB.Constants.BC_INT32_NUM_MIN, -39, -7, JSONB.Constants.BC_OBJECT_END, 28, JSONB.Constants.BC_LOCAL_DATETIME, 18, 27, -96, 19, JSONB.Constants.BC_LOCAL_DATE, 5, JSONB.Constants.BC_DOUBLE, 10, JSONB.Constants.BC_DECIMAL_LONG, 3, JSONB.Constants.BC_FALSE, 11, JSONB.Constants.BC_DECIMAL, 50, -120, 60, -123, -115, 52, -124, Base64.padSymbol, JSONB.Constants.BC_BINARY, 34, -100, 44, -108, 36, -99, 45, 98, JSONB.Constants.BC_STR_ASCII_FIX_1, 108, 69, JSONB.Constants.BC_STR_ASCII_FIX_4, 100, JSONB.Constants.BC_INT32_SHORT_ZERO, JSONB.Constants.BC_STR_ASCII_FIX_36, 82, 114, 92, JSONB.Constants.BC_STR_UTF16LE, 84, 116, 93, JSONB.Constants.BC_STR_UTF16BE, -95, 26, JSONB.Constants.BC_TIMESTAMP_SECONDS, 21, 29, JSONB.Constants.BC_ARRAY, 20, JSONB.Constants.BC_TIMESTAMP_MINUTES, 2, JSONB.Constants.BC_TRUE, 12, -68, 4, JSONB.Constants.BC_DOUBLE_LONG, 13, JSONB.Constants.BC_INT8, -31, JSONB.Constants.BC_INT64_BYTE_MIN, -20, -59, -51, -28, JSONB.Constants.BC_INT64_SHORT_ZERO, -19, -47, -15, JL_Constant.PREFIX_FLAG_SECOND, -4, -44, -12, -35, -3, 54, -114, JSONB.Constants.BC_INT32_BYTE_ZERO, -126, -117, JSONB.Constants.BC_INT32_BYTE_MIN, -125, 57, -106, 38, -102, 40, JSONB.Constants.BC_REFERENCE, 32, -101, 41, 102, JSONB.Constants.BC_STR_ASCII_FIX_5, 104, 65, 73, 96, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_STR_ASCII_FIX_32, 86, 118, 88, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 80, 112, 89, JSONB.Constants.BC_STR_ASCII, JSONB.Constants.BC_OBJECT, 30, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 17, 25, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_TIMESTAMP_MILLIS, 6, JSONB.Constants.BC_FLOAT_INT, 8, -70, 0, JSONB.Constants.BC_DOUBLE_NUM_1, 9, JSONB.Constants.BC_BIGINT, -26, -50, -22, -62, -53, -29, -61, -21, -42, -10, -38, -6, -45, -13, -37, -5, 49, -118, 62, -122, -113, 55, -121, 63, JSONB.Constants.BC_TYPED_ANY, 33, -98, 46, -105, 39, -97, JSONB.Constants.BC_INT32_NUM_MAX, 97, JSONB.Constants.BC_INT32, 110, 70, 79, 103, JSONB.Constants.BC_INT32_SHORT_MAX, 111, 81, 113, 94, JSONB.Constants.BC_STR_GB18030, 87, 119, 95, Byte.MAX_VALUE, -94, 24, JSONB.Constants.BC_TIMESTAMP, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 31, JSONB.Constants.BC_LOCAL_TIME, 23, JSONB.Constants.BC_NULL, 1, JSONB.Constants.BC_DOUBLE_NUM_0, 14, JSONB.Constants.BC_INT64, 7, JSONB.Constants.BC_FLOAT, 15, JSONB.Constants.BC_INT64_INT, -30, -54, -18, -58, -49, -25, JSONB.Constants.BC_INT64_SHORT_MAX, -17, -46, -14, -34, -2, JSONB.Constants.BC_INT64_BYTE_MAX, -9, -33, -1};
    private static final byte[] TWEAKEY_P = {9, 15, 8, 13, 10, 14, 12, 11, 0, 1, 2, 3, 4, 5, 6, 7};
    private static final byte[] RC = {1, 3, 7, 15, 31, 62, Base64.padSymbol, 59, 55, JSONB.Constants.BC_INT32_NUM_MAX, 30, 60, 57, 51, 39, 14, 29, 58, 53, 43, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 44, 24, JSONB.Constants.BC_INT32_BYTE_MIN, 33, 2, 5, 11, 23, 46, 28, JSONB.Constants.BC_INT32_BYTE_ZERO, 49, 35, 6, 13, 27, 54, 45, 26};

    private interface Instance {
        void processBufferAAD(byte[] bArr, int i);

        void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2);

        void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2);

        void processFinalAAD();

        void processFinalBlock(byte[] bArr, int i);

        void reset();
    }

    private class RomulusM implements Instance {
        private int offset;
        private final byte[] mac_s = new byte[16];
        private final byte[] mac_CNT = new byte[7];
        private final byte[] s = new byte[16];
        private boolean twist = true;

        public RomulusM() {
        }

        int ad_encryption(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4) {
            byte[] bArr5 = new byte[16];
            byte[] bArr6 = new byte[16];
            int min = Math.min(i2, 16);
            int i3 = i2 - min;
            RomulusEngine.this.pad(bArr, i, bArr6, 16, min);
            Bytes.xorTo(16, bArr6, bArr2);
            int i4 = i + min;
            this.offset = i4;
            RomulusEngine.this.lfsr_gf56(bArr4);
            if (i3 == 0) {
                return i3;
            }
            int min2 = Math.min(i3, 16);
            int i5 = i3 - min2;
            RomulusEngine.this.pad(bArr, i4, bArr5, 16, min2);
            this.offset = i4 + min2;
            RomulusEngine.this.block_cipher(bArr2, bArr3, bArr5, 0, bArr4, (byte) 44);
            RomulusEngine.this.lfsr_gf56(bArr4);
            return i5;
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processBufferAAD(byte[] bArr, int i) {
            if (this.twist) {
                Bytes.xorTo(RomulusEngine.this.MAC_SIZE, bArr, i, this.mac_s);
            } else {
                RomulusEngine romulusEngine = RomulusEngine.this;
                romulusEngine.block_cipher(this.mac_s, romulusEngine.k, bArr, i, this.mac_CNT, (byte) 40);
            }
            this.twist = !this.twist;
            RomulusEngine.this.lfsr_gf56(this.mac_CNT);
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processFinalAAD() {
            if (RomulusEngine.this.aadOperator.getLen() == 0) {
                RomulusEngine.this.lfsr_gf56(this.mac_CNT);
            } else if (RomulusEngine.this.m_aadPos != 0) {
                Arrays.fill(RomulusEngine.this.m_aad, RomulusEngine.this.m_aadPos, RomulusEngine.this.BlockSize - 1, (byte) 0);
                RomulusEngine.this.m_aad[RomulusEngine.this.BlockSize - 1] = (byte) (RomulusEngine.this.m_aadPos & 15);
                if (this.twist) {
                    Bytes.xorTo(RomulusEngine.this.BlockSize, RomulusEngine.this.m_aad, this.mac_s);
                } else {
                    RomulusEngine romulusEngine = RomulusEngine.this;
                    romulusEngine.block_cipher(this.mac_s, romulusEngine.k, RomulusEngine.this.m_aad, 0, this.mac_CNT, (byte) 40);
                }
                RomulusEngine.this.lfsr_gf56(this.mac_CNT);
            }
            RomulusEngine.this.m_aadPos = 0;
            RomulusEngine romulusEngine2 = RomulusEngine.this;
            romulusEngine2.m_bufPos = romulusEngine2.dataOperator.getLen();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0060  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00fe  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x016c  */
        /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0165  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x00de  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x004e  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0052  */
        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void processFinalBlock(byte[] r31, int r32) {
            /*
                Method dump skipped, instructions count: 519
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.RomulusEngine.RomulusM.processFinalBlock(byte[], int):void");
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void reset() {
            Arrays.clear(this.s);
            Arrays.clear(this.mac_s);
            RomulusEngine.this.reset_lfsr_gf56(this.mac_CNT);
            RomulusEngine romulusEngine = RomulusEngine.this;
            romulusEngine.reset_lfsr_gf56(romulusEngine.CNT);
            this.twist = true;
        }
    }

    private class RomulusN implements Instance {
        private final byte[] s = new byte[16];
        boolean twist;

        public RomulusN() {
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processBufferAAD(byte[] bArr, int i) {
            if (this.twist) {
                Bytes.xorTo(16, bArr, i, this.s);
            } else {
                RomulusEngine romulusEngine = RomulusEngine.this;
                romulusEngine.block_cipher(this.s, romulusEngine.k, bArr, i, RomulusEngine.this.CNT, (byte) 8);
            }
            RomulusEngine romulusEngine2 = RomulusEngine.this;
            romulusEngine2.lfsr_gf56(romulusEngine2.CNT);
            this.twist = !this.twist;
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
            RomulusEngine.this.g8A(this.s, bArr2, i2);
            for (int i3 = 0; i3 < 16; i3++) {
                int i4 = i3 + i2;
                byte b = (byte) (bArr2[i4] ^ bArr[i3 + i]);
                bArr2[i4] = b;
                byte[] bArr3 = this.s;
                bArr3[i3] = (byte) (b ^ bArr3[i3]);
            }
            RomulusEngine romulusEngine = RomulusEngine.this;
            romulusEngine.lfsr_gf56(romulusEngine.CNT);
            RomulusEngine romulusEngine2 = RomulusEngine.this;
            romulusEngine2.block_cipher(this.s, romulusEngine2.k, RomulusEngine.this.npub, 0, RomulusEngine.this.CNT, (byte) 4);
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
            RomulusEngine.this.g8A(this.s, bArr2, i2);
            for (int i3 = 0; i3 < 16; i3++) {
                byte[] bArr3 = this.s;
                int i4 = i3 + i;
                bArr3[i3] = (byte) (bArr3[i3] ^ bArr[i4]);
                int i5 = i3 + i2;
                bArr2[i5] = (byte) (bArr2[i5] ^ bArr[i4]);
            }
            RomulusEngine romulusEngine = RomulusEngine.this;
            romulusEngine.lfsr_gf56(romulusEngine.CNT);
            RomulusEngine romulusEngine2 = RomulusEngine.this;
            romulusEngine2.block_cipher(this.s, romulusEngine2.k, RomulusEngine.this.npub, 0, RomulusEngine.this.CNT, (byte) 4);
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processFinalAAD() {
            if (RomulusEngine.this.m_aadPos != 0) {
                byte[] bArr = new byte[16];
                int min = Math.min(RomulusEngine.this.m_aadPos, 16);
                RomulusEngine romulusEngine = RomulusEngine.this;
                romulusEngine.pad(romulusEngine.m_aad, 0, bArr, 16, min);
                if (this.twist) {
                    Bytes.xorTo(16, bArr, this.s);
                } else {
                    RomulusEngine romulusEngine2 = RomulusEngine.this;
                    romulusEngine2.block_cipher(this.s, romulusEngine2.k, bArr, 0, RomulusEngine.this.CNT, (byte) 8);
                }
                RomulusEngine romulusEngine3 = RomulusEngine.this;
                romulusEngine3.lfsr_gf56(romulusEngine3.CNT);
            }
            if (RomulusEngine.this.aadOperator.getLen() == 0) {
                RomulusEngine romulusEngine4 = RomulusEngine.this;
                romulusEngine4.lfsr_gf56(romulusEngine4.CNT);
                RomulusEngine romulusEngine5 = RomulusEngine.this;
                romulusEngine5.block_cipher(this.s, romulusEngine5.k, RomulusEngine.this.npub, 0, RomulusEngine.this.CNT, (byte) 26);
            } else if ((RomulusEngine.this.m_aadPos & 15) != 0) {
                RomulusEngine romulusEngine6 = RomulusEngine.this;
                romulusEngine6.block_cipher(this.s, romulusEngine6.k, RomulusEngine.this.npub, 0, RomulusEngine.this.CNT, (byte) 26);
            } else {
                RomulusEngine romulusEngine7 = RomulusEngine.this;
                romulusEngine7.block_cipher(this.s, romulusEngine7.k, RomulusEngine.this.npub, 0, RomulusEngine.this.CNT, (byte) 24);
            }
            RomulusEngine romulusEngine8 = RomulusEngine.this;
            romulusEngine8.reset_lfsr_gf56(romulusEngine8.CNT);
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processFinalBlock(byte[] bArr, int i) {
            if (RomulusEngine.this.dataOperator.getLen() - (RomulusEngine.this.forEncryption ? 0 : RomulusEngine.this.MAC_SIZE) == 0) {
                RomulusEngine romulusEngine = RomulusEngine.this;
                romulusEngine.lfsr_gf56(romulusEngine.CNT);
                RomulusEngine romulusEngine2 = RomulusEngine.this;
                romulusEngine2.block_cipher(this.s, romulusEngine2.k, RomulusEngine.this.npub, 0, RomulusEngine.this.CNT, (byte) 21);
            } else if (RomulusEngine.this.m_bufPos != 0) {
                int min = Math.min(RomulusEngine.this.m_bufPos, 16);
                RomulusEngine romulusEngine3 = RomulusEngine.this;
                romulusEngine3.rho(romulusEngine3.m_buf, 0, bArr, i, this.s, min);
                RomulusEngine romulusEngine4 = RomulusEngine.this;
                romulusEngine4.lfsr_gf56(romulusEngine4.CNT);
                RomulusEngine romulusEngine5 = RomulusEngine.this;
                romulusEngine5.block_cipher(this.s, romulusEngine5.k, RomulusEngine.this.npub, 0, RomulusEngine.this.CNT, RomulusEngine.this.m_bufPos == 16 ? (byte) 20 : (byte) 21);
            }
            RomulusEngine romulusEngine6 = RomulusEngine.this;
            romulusEngine6.g8A(this.s, romulusEngine6.mac, 0);
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void reset() {
            Arrays.clear(this.s);
            RomulusEngine romulusEngine = RomulusEngine.this;
            romulusEngine.reset_lfsr_gf56(romulusEngine.CNT);
            this.twist = true;
        }
    }

    public static class RomulusParameters {
        public static final int ROMULUS_M = 0;
        public static final int ROMULUS_N = 1;
        public static final int ROMULUS_T = 2;
        public static final RomulusParameters RomulusM = new RomulusParameters(0);
        public static final RomulusParameters RomulusN = new RomulusParameters(1);
        public static final RomulusParameters RomulusT = new RomulusParameters(2);
        private final int ord;

        RomulusParameters(int i) {
            this.ord = i;
        }
    }

    private class RomulusT implements Instance {
        byte[] CNT_Z;
        byte[] LR;
        byte[] S;
        byte[] T;
        byte[] Z;
        private final byte[] g;
        private final byte[] h;

        private RomulusT() {
            this.h = new byte[16];
            this.g = new byte[16];
            this.Z = new byte[16];
            this.CNT_Z = new byte[7];
            this.LR = new byte[32];
            this.T = new byte[16];
            this.S = new byte[16];
        }

        private void processAfterAbsorbCiphertext() {
            if (RomulusEngine.this.m_aadPos == RomulusEngine.this.BlockSize) {
                RomulusEngine.hirose_128_128_256(this.h, this.g, RomulusEngine.this.m_aad, 0);
                RomulusEngine.this.m_aadPos = 0;
            } else {
                RomulusEngine romulusEngine = RomulusEngine.this;
                romulusEngine.m_aadPos = romulusEngine.BlockSize;
            }
            RomulusEngine.this.lfsr_gf56(this.CNT_Z);
        }

        private void processBuffer(byte[] bArr, int i, byte[] bArr2, int i2) {
            System.arraycopy(RomulusEngine.this.npub, 0, this.S, 0, 16);
            RomulusEngine romulusEngine = RomulusEngine.this;
            romulusEngine.block_cipher(this.S, this.Z, this.T, 0, romulusEngine.CNT, JSONB.Constants.BC_INT32_SHORT_MIN);
            Bytes.xor(16, this.S, bArr, i, bArr2, i2);
            System.arraycopy(RomulusEngine.this.npub, 0, this.S, 0, 16);
            RomulusEngine romulusEngine2 = RomulusEngine.this;
            romulusEngine2.block_cipher(this.S, this.Z, this.T, 0, romulusEngine2.CNT, (byte) 65);
            System.arraycopy(this.S, 0, this.Z, 0, 16);
            RomulusEngine romulusEngine3 = RomulusEngine.this;
            romulusEngine3.lfsr_gf56(romulusEngine3.CNT);
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processBufferAAD(byte[] bArr, int i) {
            RomulusEngine.hirose_128_128_256(this.h, this.g, bArr, i);
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
            processBuffer(bArr, i, bArr2, i2);
            System.arraycopy(bArr, i, RomulusEngine.this.m_aad, RomulusEngine.this.m_aadPos, RomulusEngine.this.BlockSize);
            processAfterAbsorbCiphertext();
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
            processBuffer(bArr, i, bArr2, i2);
            System.arraycopy(bArr2, i2, RomulusEngine.this.m_aad, RomulusEngine.this.m_aadPos, RomulusEngine.this.BlockSize);
            processAfterAbsorbCiphertext();
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processFinalAAD() {
            Arrays.fill(RomulusEngine.this.m_aad, RomulusEngine.this.m_aadPos, RomulusEngine.this.AADBufferSize - 1, (byte) 0);
            if (RomulusEngine.this.m_aadPos >= 16) {
                RomulusEngine.this.m_aad[RomulusEngine.this.AADBufferSize - 1] = (byte) (RomulusEngine.this.m_aadPos & 15);
                RomulusEngine.hirose_128_128_256(this.h, this.g, RomulusEngine.this.m_aad, 0);
                RomulusEngine.this.m_aadPos = 0;
            } else {
                if (RomulusEngine.this.m_aadPos < 0 || RomulusEngine.this.aadOperator.getLen() == 0) {
                    return;
                }
                RomulusEngine.this.m_aad[RomulusEngine.this.BlockSize - 1] = (byte) (RomulusEngine.this.m_aadPos & 15);
                RomulusEngine romulusEngine = RomulusEngine.this;
                romulusEngine.m_aadPos = romulusEngine.BlockSize;
            }
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void processFinalBlock(byte[] bArr, int i) {
            char c;
            int len = RomulusEngine.this.dataOperator.getLen() - (RomulusEngine.this.forEncryption ? 0 : RomulusEngine.this.MAC_SIZE);
            if (RomulusEngine.this.m_bufPos != 0) {
                int min = Math.min(RomulusEngine.this.m_bufPos, 16);
                System.arraycopy(RomulusEngine.this.npub, 0, this.S, 0, 16);
                RomulusEngine romulusEngine = RomulusEngine.this;
                romulusEngine.block_cipher(this.S, this.Z, this.T, 0, romulusEngine.CNT, JSONB.Constants.BC_INT32_SHORT_MIN);
                Bytes.xor(min, RomulusEngine.this.m_buf, this.S, bArr, i);
                System.arraycopy(RomulusEngine.this.npub, 0, this.S, 0, 16);
                RomulusEngine romulusEngine2 = RomulusEngine.this;
                romulusEngine2.lfsr_gf56(romulusEngine2.CNT);
                if (!RomulusEngine.this.forEncryption) {
                    bArr = RomulusEngine.this.m_buf;
                    i = 0;
                }
                System.arraycopy(bArr, i, RomulusEngine.this.m_aad, RomulusEngine.this.m_aadPos, RomulusEngine.this.m_bufPos);
                Arrays.fill(RomulusEngine.this.m_aad, RomulusEngine.this.m_aadPos + RomulusEngine.this.m_bufPos, RomulusEngine.this.AADBufferSize - 1, (byte) 0);
                RomulusEngine.this.m_aad[(RomulusEngine.this.m_aadPos + RomulusEngine.this.BlockSize) - 1] = (byte) (RomulusEngine.this.m_bufPos & 15);
                if (RomulusEngine.this.m_aadPos == 0) {
                    System.arraycopy(RomulusEngine.this.npub, 0, RomulusEngine.this.m_aad, RomulusEngine.this.BlockSize, RomulusEngine.this.BlockSize);
                    c = 0;
                } else {
                    c = 16;
                }
                RomulusEngine.hirose_128_128_256(this.h, this.g, RomulusEngine.this.m_aad, 0);
                RomulusEngine.this.lfsr_gf56(this.CNT_Z);
            } else if (RomulusEngine.this.m_aadPos != 0) {
                RomulusEngine romulusEngine3 = RomulusEngine.this;
                if (len > 0) {
                    Arrays.fill(romulusEngine3.m_aad, RomulusEngine.this.BlockSize, RomulusEngine.this.AADBufferSize, (byte) 0);
                } else if (romulusEngine3.aadOperator.getLen() != 0) {
                    System.arraycopy(RomulusEngine.this.npub, 0, RomulusEngine.this.m_aad, RomulusEngine.this.m_aadPos, 16);
                    RomulusEngine.this.m_aadPos = 0;
                    c = 0;
                    RomulusEngine.hirose_128_128_256(this.h, this.g, RomulusEngine.this.m_aad, 0);
                }
                c = 16;
                RomulusEngine.hirose_128_128_256(this.h, this.g, RomulusEngine.this.m_aad, 0);
            } else if (len > 0) {
                Arrays.fill(RomulusEngine.this.m_aad, 0, RomulusEngine.this.BlockSize, (byte) 0);
                System.arraycopy(RomulusEngine.this.npub, 0, RomulusEngine.this.m_aad, RomulusEngine.this.BlockSize, RomulusEngine.this.BlockSize);
                RomulusEngine.hirose_128_128_256(this.h, this.g, RomulusEngine.this.m_aad, 0);
                c = 0;
            } else {
                c = 16;
            }
            if (c == 16) {
                System.arraycopy(RomulusEngine.this.npub, 0, RomulusEngine.this.m_aad, 0, 16);
                System.arraycopy(RomulusEngine.this.CNT, 0, RomulusEngine.this.m_aad, 16, 7);
                Arrays.fill(RomulusEngine.this.m_aad, 23, 31, (byte) 0);
                RomulusEngine.this.m_aad[31] = 23;
            } else {
                System.arraycopy(this.CNT_Z, 0, RomulusEngine.this.m_aad, 0, 7);
                Arrays.fill(RomulusEngine.this.m_aad, 7, 31, (byte) 0);
                RomulusEngine.this.m_aad[31] = 7;
            }
            byte[] bArr2 = this.h;
            bArr2[0] = (byte) (bArr2[0] ^ 2);
            RomulusEngine.hirose_128_128_256(bArr2, this.g, RomulusEngine.this.m_aad, 0);
            System.arraycopy(this.h, 0, this.LR, 0, 16);
            System.arraycopy(this.g, 0, this.LR, 16, 16);
            Arrays.clear(this.CNT_Z);
            RomulusEngine romulusEngine4 = RomulusEngine.this;
            romulusEngine4.block_cipher(this.LR, romulusEngine4.k, this.LR, 16, this.CNT_Z, JSONB.Constants.BC_INT32_SHORT_ZERO);
            System.arraycopy(this.LR, 0, RomulusEngine.this.mac, 0, RomulusEngine.this.MAC_SIZE);
        }

        @Override // org.bouncycastle.crypto.engines.RomulusEngine.Instance
        public void reset() {
            Arrays.clear(this.h);
            Arrays.clear(this.g);
            Arrays.clear(this.LR);
            Arrays.clear(this.T);
            Arrays.clear(this.S);
            Arrays.clear(this.CNT_Z);
            RomulusEngine romulusEngine = RomulusEngine.this;
            romulusEngine.reset_lfsr_gf56(romulusEngine.CNT);
            System.arraycopy(RomulusEngine.this.npub, 0, this.Z, 0, RomulusEngine.this.IV_SIZE);
            RomulusEngine romulusEngine2 = RomulusEngine.this;
            romulusEngine2.block_cipher(this.Z, romulusEngine2.k, this.T, 0, this.CNT_Z, (byte) 66);
            RomulusEngine.this.reset_lfsr_gf56(this.CNT_Z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RomulusEngine(org.bouncycastle.crypto.engines.RomulusEngine.RomulusParameters r4) {
        /*
            r3 = this;
            r3.<init>()
            r0 = 16
            r3.AADBufferSize = r0
            r3.BlockSize = r0
            r3.MAC_SIZE = r0
            r3.IV_SIZE = r0
            r3.KEY_SIZE = r0
            r0 = 7
            byte[] r0 = new byte[r0]
            r3.CNT = r0
            int r0 = org.bouncycastle.crypto.engines.RomulusEngine.RomulusParameters.access$000(r4)
            if (r0 == 0) goto L3a
            r1 = 1
            if (r0 == r1) goto L30
            r1 = 2
            if (r0 == r1) goto L21
            goto L45
        L21:
            java.lang.String r0 = "Romulus-T"
            r3.algorithmName = r0
            r0 = 32
            r3.AADBufferSize = r0
            org.bouncycastle.crypto.engines.RomulusEngine$RomulusT r0 = new org.bouncycastle.crypto.engines.RomulusEngine$RomulusT
            r1 = 0
            r0.<init>()
            goto L43
        L30:
            java.lang.String r0 = "Romulus-N"
            r3.algorithmName = r0
            org.bouncycastle.crypto.engines.RomulusEngine$RomulusN r0 = new org.bouncycastle.crypto.engines.RomulusEngine$RomulusN
            r0.<init>()
            goto L43
        L3a:
            java.lang.String r0 = "Romulus-M"
            r3.algorithmName = r0
            org.bouncycastle.crypto.engines.RomulusEngine$RomulusM r0 = new org.bouncycastle.crypto.engines.RomulusEngine$RomulusM
            r0.<init>()
        L43:
            r3.instance = r0
        L45:
            org.bouncycastle.crypto.engines.RomulusEngine$RomulusParameters r0 = org.bouncycastle.crypto.engines.RomulusEngine.RomulusParameters.RomulusN
            if (r4 != r0) goto L4c
            org.bouncycastle.crypto.engines.AEADBaseEngine$ProcessingBufferType r0 = org.bouncycastle.crypto.engines.AEADBaseEngine.ProcessingBufferType.Buffered
            goto L4e
        L4c:
            org.bouncycastle.crypto.engines.AEADBaseEngine$ProcessingBufferType r0 = org.bouncycastle.crypto.engines.AEADBaseEngine.ProcessingBufferType.Immediate
        L4e:
            org.bouncycastle.crypto.engines.AEADBaseEngine$AADOperatorType r1 = org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperatorType.Counter
            org.bouncycastle.crypto.engines.RomulusEngine$RomulusParameters r2 = org.bouncycastle.crypto.engines.RomulusEngine.RomulusParameters.RomulusM
            if (r4 != r2) goto L57
            org.bouncycastle.crypto.engines.AEADBaseEngine$DataOperatorType r4 = org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperatorType.Stream
            goto L59
        L57:
            org.bouncycastle.crypto.engines.AEADBaseEngine$DataOperatorType r4 = org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperatorType.Counter
        L59:
            r3.setInnerMembers(r0, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.RomulusEngine.<init>(org.bouncycastle.crypto.engines.RomulusEngine$RomulusParameters):void");
    }

    public static void hirose_128_128_256(RomulusDigest.Friend friend, byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by RomulusDigest");
        }
        hirose_128_128_256(bArr, bArr2, bArr3, i);
    }

    static void hirose_128_128_256(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        byte[] bArr4 = new byte[48];
        byte[] bArr5 = new byte[16];
        System.arraycopy(bArr2, 0, bArr4, 0, 16);
        System.arraycopy(bArr, 0, bArr2, 0, 16);
        System.arraycopy(bArr, 0, bArr5, 0, 16);
        bArr2[0] = (byte) (bArr2[0] ^ 1);
        System.arraycopy(bArr3, i, bArr4, 16, 32);
        skinny_128_384_plus_enc(bArr, bArr4);
        skinny_128_384_plus_enc(bArr2, bArr4);
        for (int i2 = 0; i2 < 16; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr5[i2]);
            bArr2[i2] = (byte) (bArr2[i2] ^ bArr5[i2]);
        }
        bArr2[0] = (byte) (bArr2[0] ^ 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset_lfsr_gf56(byte[] bArr) {
        bArr[0] = 1;
        Arrays.fill(bArr, 1, 7, (byte) 0);
    }

    private static void skinny_128_384_plus_enc(byte[] bArr, byte[] bArr2) {
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 4, 4);
        byte[][][] bArr4 = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, 3, 4, 4);
        byte[][][] bArr5 = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, 3, 4, 4);
        for (int i = 0; i < 4; i++) {
            int i2 = i << 2;
            System.arraycopy(bArr, i2, bArr3[i], 0, 4);
            System.arraycopy(bArr2, i2, bArr4[0][i], 0, 4);
            System.arraycopy(bArr2, i2 + 16, bArr4[1][i], 0, 4);
            System.arraycopy(bArr2, i2 + 32, bArr4[2][i], 0, 4);
        }
        for (int i3 = 0; i3 < 40; i3++) {
            for (int i4 = 0; i4 < 4; i4++) {
                for (int i5 = 0; i5 < 4; i5++) {
                    byte[] bArr6 = bArr3[i4];
                    bArr6[i5] = sbox_8[bArr6[i5] & UByte.MAX_VALUE];
                }
            }
            byte[] bArr7 = bArr3[0];
            byte b = bArr7[0];
            byte[] bArr8 = RC;
            bArr7[0] = (byte) (b ^ (bArr8[i3] & 15));
            byte[] bArr9 = bArr3[1];
            bArr9[0] = (byte) (bArr9[0] ^ ((bArr8[i3] >>> 4) & 3));
            byte[] bArr10 = bArr3[2];
            bArr10[0] = (byte) (bArr10[0] ^ 2);
            for (int i6 = 0; i6 <= 1; i6++) {
                for (int i7 = 0; i7 < 4; i7++) {
                    byte[] bArr11 = bArr3[i6];
                    bArr11[i7] = (byte) (bArr11[i7] ^ ((bArr4[0][i6][i7] ^ bArr4[1][i6][i7]) ^ bArr4[2][i6][i7]));
                }
            }
            for (int i8 = 0; i8 < 4; i8++) {
                for (int i9 = 0; i9 < 4; i9++) {
                    byte b2 = TWEAKEY_P[(i8 << 2) + i9];
                    int i10 = b2 >>> 2;
                    int i11 = b2 & 3;
                    bArr5[0][i8][i9] = bArr4[0][i10][i11];
                    bArr5[1][i8][i9] = bArr4[1][i10][i11];
                    bArr5[2][i8][i9] = bArr4[2][i10][i11];
                }
            }
            int i12 = 0;
            while (i12 <= 1) {
                for (int i13 = 0; i13 < 4; i13++) {
                    bArr4[0][i12][i13] = bArr5[0][i12][i13];
                    byte b3 = bArr5[1][i12][i13];
                    bArr4[1][i12][i13] = (byte) (((b3 >>> 5) & 1) ^ (((b3 << 1) & 254) ^ ((b3 >>> 7) & 1)));
                    byte b4 = bArr5[2][i12][i13];
                    bArr4[2][i12][i13] = (byte) (((b4 << 1) & 128) ^ (((b4 >>> 1) & 127) ^ ((b4 << 7) & 128)));
                }
                i12++;
            }
            while (i12 < 4) {
                for (int i14 = 0; i14 < 4; i14++) {
                    bArr4[0][i12][i14] = bArr5[0][i12][i14];
                    bArr4[1][i12][i14] = bArr5[1][i12][i14];
                    bArr4[2][i12][i14] = bArr5[2][i12][i14];
                }
                i12++;
            }
            byte[] bArr12 = bArr3[1];
            byte b5 = bArr12[3];
            bArr12[3] = bArr12[2];
            bArr12[2] = bArr12[1];
            bArr12[1] = bArr12[0];
            bArr12[0] = b5;
            byte[] bArr13 = bArr3[2];
            byte b6 = bArr13[0];
            bArr13[0] = bArr13[2];
            bArr13[2] = b6;
            byte b7 = bArr13[1];
            bArr13[1] = bArr13[3];
            bArr13[3] = b7;
            byte[] bArr14 = bArr3[3];
            byte b8 = bArr14[0];
            bArr14[0] = bArr14[1];
            bArr14[1] = bArr14[2];
            bArr14[2] = bArr14[3];
            bArr14[3] = b8;
            for (int i15 = 0; i15 < 4; i15++) {
                byte[] bArr15 = bArr3[1];
                byte b9 = bArr15[i15];
                byte[] bArr16 = bArr3[2];
                bArr15[i15] = (byte) (b9 ^ bArr16[i15]);
                byte b10 = bArr16[i15];
                byte[] bArr17 = bArr3[0];
                byte b11 = (byte) (b10 ^ bArr17[i15]);
                bArr16[i15] = b11;
                byte[] bArr18 = bArr3[3];
                byte b12 = (byte) (bArr18[i15] ^ b11);
                bArr18[i15] = b12;
                bArr18[i15] = bArr16[i15];
                bArr16[i15] = bArr15[i15];
                bArr15[i15] = bArr17[i15];
                bArr17[i15] = b12;
            }
        }
        for (int i16 = 0; i16 < 16; i16++) {
            bArr[i16] = (byte) (bArr3[i16 >>> 2][i16 & 3] & UByte.MAX_VALUE);
        }
    }

    void block_cipher(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b) {
        byte[] bArr5 = new byte[48];
        System.arraycopy(bArr4, 0, bArr5, 0, 7);
        bArr5[7] = b;
        System.arraycopy(bArr3, i, bArr5, 16, 16);
        System.arraycopy(bArr2, 0, bArr5, 32, 16);
        skinny_128_384_plus_enc(bArr, bArr5);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void finishAAD(AEADBaseEngine.State state, boolean z) {
        finishAAD1(state);
    }

    void g8A(byte[] bArr, byte[] bArr2, int i) {
        int min = Math.min(bArr2.length - i, 16);
        for (int i2 = 0; i2 < min; i2++) {
            byte b = bArr[i2];
            bArr2[i2 + i] = (byte) (((b & 1) << 7) ^ (((b & UByte.MAX_VALUE) >>> 1) ^ (b & ByteCompanionObject.MIN_VALUE)));
        }
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
    }

    void lfsr_gf56(byte[] bArr) {
        byte b = bArr[6];
        byte b2 = (byte) ((b & UByte.MAX_VALUE) >>> 7);
        int i = (b & UByte.MAX_VALUE) << 1;
        byte b3 = bArr[5];
        bArr[6] = (byte) (i | ((b3 & UByte.MAX_VALUE) >>> 7));
        int i2 = (b3 & UByte.MAX_VALUE) << 1;
        byte b4 = bArr[4];
        bArr[5] = (byte) (i2 | ((b4 & UByte.MAX_VALUE) >>> 7));
        int i3 = (b4 & UByte.MAX_VALUE) << 1;
        byte b5 = bArr[3];
        bArr[4] = (byte) (i3 | ((b5 & UByte.MAX_VALUE) >>> 7));
        int i4 = (b5 & UByte.MAX_VALUE) << 1;
        byte b6 = bArr[2];
        bArr[3] = (byte) (i4 | ((b6 & UByte.MAX_VALUE) >>> 7));
        int i5 = (b6 & UByte.MAX_VALUE) << 1;
        byte b7 = bArr[1];
        bArr[2] = (byte) (i5 | ((b7 & UByte.MAX_VALUE) >>> 7));
        int i6 = (b7 & UByte.MAX_VALUE) << 1;
        byte b8 = bArr[0];
        bArr[1] = (byte) (i6 | ((b8 & UByte.MAX_VALUE) >>> 7));
        int i7 = (b8 & UByte.MAX_VALUE) << 1;
        if (b2 == 1) {
            bArr[0] = (byte) (i7 ^ Opcodes.FCMPL);
        } else {
            bArr[0] = (byte) i7;
        }
    }

    void pad(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        bArr2[i2 - 1] = (byte) (i3 & 15);
        System.arraycopy(bArr, i, bArr2, 0, i3);
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
        this.instance.processBufferAAD(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.instance.processBufferDecrypt(bArr, i, bArr2, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.instance.processBufferEncrypt(bArr, i, bArr2, i2);
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
        this.instance.processFinalAAD();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        this.instance.processFinalBlock(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        super.reset(z);
        this.instance.reset();
    }

    void rho(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        int i4;
        byte[] bArr4 = new byte[16];
        pad(bArr, i, bArr4, 16, i3);
        g8A(bArr3, bArr2, i2);
        if (this.forEncryption) {
            for (int i5 = 0; i5 < 16; i5++) {
                bArr3[i5] = (byte) (bArr3[i5] ^ bArr4[i5]);
                int i6 = i5 + i2;
                if (i5 < i3) {
                    bArr2[i6] = (byte) (bArr2[i6] ^ bArr4[i5]);
                } else {
                    bArr2[i6] = 0;
                }
            }
            return;
        }
        for (int i7 = 0; i7 < 16; i7++) {
            byte b = (byte) (bArr3[i7] ^ bArr4[i7]);
            bArr3[i7] = b;
            if (i7 < i3 && (i4 = i7 + i2) < bArr2.length) {
                bArr3[i7] = (byte) (b ^ bArr2[i4]);
                bArr2[i4] = (byte) (bArr2[i4] ^ bArr4[i7]);
            }
        }
    }
}
