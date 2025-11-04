package org.bouncycastle.crypto.engines;

import androidx.core.view.InputDeviceCompat;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public final class TwofishEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int INPUT_WHITEN = 0;
    private static final int MAX_KEY_BITS = 256;
    private static final int MAX_ROUNDS = 16;
    private static final int OUTPUT_WHITEN = 4;
    private static final byte[][] P = {new byte[]{JSONB.Constants.BC_LOCAL_DATE, 103, JSONB.Constants.BC_DOUBLE_NUM_1, -24, 4, -3, JSONB.Constants.BC_ARRAY_FIX_MAX, 118, -102, JSONB.Constants.BC_TYPED_ANY, ByteCompanionObject.MIN_VALUE, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -28, -35, -47, JSONB.Constants.BC_INT32_BYTE_ZERO, 13, -58, 53, -104, 24, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, JSONB.Constants.BC_INT32, -14, JSONB.Constants.BC_INT64_BYTE_ZERO, -117, JSONB.Constants.BC_INT32_BYTE_MIN, -124, 84, -33, 35, 25, 91, Base64.padSymbol, 89, -13, JSONB.Constants.BC_TIMESTAMP, -94, -126, 99, 1, -125, 46, -39, 81, -101, JSONB.Constants.BC_STR_UTF16LE, JSONB.Constants.BC_OBJECT, -21, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_INT64, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 12, -29, 97, JSONB.Constants.BC_INT64_SHORT_MIN, -116, 58, -11, 115, 44, 37, 11, JSONB.Constants.BC_BIGINT, JSONB.Constants.BC_STR_ASCII_FIX_5, -119, 107, 83, 106, JSONB.Constants.BC_DOUBLE_LONG, -15, -31, -26, JSONB.Constants.BC_INT8, 69, -30, -12, JSONB.Constants.BC_FLOAT_INT, 102, -52, -107, 3, 86, -44, 28, 30, JSONB.Constants.BC_INT64_BYTE_MAX, -5, -61, -114, JSONB.Constants.BC_DOUBLE, -23, -49, JSONB.Constants.BC_INT64_INT, -70, -22, 119, 57, JSONB.Constants.BC_NULL, 51, -55, 98, 113, -127, JSONB.Constants.BC_STR_ASCII, 9, JSONB.Constants.BC_TIMESTAMP_MINUTES, 36, -51, -7, JSONB.Constants.BC_INT64_NUM_MIN, -27, -59, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_INT32_SHORT_ZERO, 8, -122, -25, -95, 29, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -19, 6, 112, JSONB.Constants.BC_DOUBLE_NUM_0, -46, 65, JSONB.Constants.BC_STR_UTF16, -96, 17, 49, -62, 39, JSONB.Constants.BC_CHAR, 32, -10, 96, -1, -106, 92, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_TIMESTAMP_MILLIS, -98, -100, 82, 27, 95, JSONB.Constants.BC_REFERENCE, 10, -17, JSONB.Constants.BC_BINARY, -123, 73, -18, 45, 79, -113, 59, JSONB.Constants.BC_INT32_SHORT_MAX, -121, JSONB.Constants.BC_STR_ASCII_FIX_36, 70, -42, 62, JSONB.Constants.BC_STR_ASCII_FIX_32, 100, 42, -50, -53, JSONB.Constants.BC_INT32_NUM_MAX, -4, -105, 5, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_TIMESTAMP_SECONDS, Byte.MAX_VALUE, -43, 26, 75, 14, JSONB.Constants.BC_LOCAL_TIME, 90, 40, 20, 63, 41, -120, 60, 76, 2, JSONB.Constants.BC_DECIMAL_LONG, -38, JSONB.Constants.BC_FALSE, 23, 85, 31, -118, JSONB.Constants.BC_STR_UTF16BE, 87, JSONB.Constants.BC_INT64_SHORT_MAX, -115, 116, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_INT64_SHORT_ZERO, -97, 114, JSONB.Constants.BC_STR_GB18030, 21, 34, 18, 88, 7, -103, 52, 110, 80, -34, 104, 101, -68, -37, -8, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_LOCAL_DATETIME, 43, JSONB.Constants.BC_INT32_SHORT_MIN, JL_Constant.PREFIX_FLAG_SECOND, -2, 50, JSONB.Constants.BC_ARRAY, -54, JSONB.Constants.BC_INT32_NUM_16, 33, JSONB.Constants.BC_INT32_NUM_MIN, -45, 93, 15, 0, 111, -99, 54, 66, JSONB.Constants.BC_STR_ASCII_FIX_1, 94, -63, -32}, new byte[]{117, -13, -58, -12, -37, JSONB.Constants.BC_STR_UTF16, -5, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_STR_ASCII_FIX_1, -45, -26, 107, 69, JSONB.Constants.BC_STR_UTF16BE, -24, 75, -42, 50, JSONB.Constants.BC_INT64_NUM_MIN, -3, 55, 113, -15, -31, JSONB.Constants.BC_INT32_BYTE_MIN, 15, -8, 27, -121, -6, 6, 63, 94, -70, JSONB.Constants.BC_TIMESTAMP, 91, -118, 0, -68, -99, JSONB.Constants.BC_STR_ASCII_FIX_36, -63, JSONB.Constants.BC_TRUE, 14, ByteCompanionObject.MIN_VALUE, 93, -46, -43, -96, -124, 7, 20, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_CHAR, 44, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_DOUBLE_NUM_0, 115, 76, 84, JSONB.Constants.BC_TYPED_ANY, 116, 54, 81, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_INT8, 90, -4, 96, 98, -106, 108, 66, -9, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_STR_UTF16LE, 40, 39, -116, 19, -107, -100, JSONB.Constants.BC_INT64_SHORT_MAX, 36, 70, 59, 112, -54, -29, -123, -53, 17, JSONB.Constants.BC_INT64_BYTE_ZERO, JSONB.Constants.BC_REFERENCE, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_OBJECT, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_INT32_SHORT_MIN, -25, 43, -30, JSONB.Constants.BC_STR_ASCII, 12, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -126, 65, 58, -22, JSONB.Constants.BC_DECIMAL, -28, -102, JSONB.Constants.BC_ARRAY, -105, JSONB.Constants.BC_STR_GB18030, -38, JSONB.Constants.BC_STR_UTF8, 23, 102, -108, -95, 29, Base64.padSymbol, JSONB.Constants.BC_INT32_NUM_MIN, -34, JSONB.Constants.BC_DOUBLE_NUM_1, 11, 114, JSONB.Constants.BC_LOCAL_TIME, 28, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 73, -127, -120, -18, 33, JSONB.Constants.BC_INT64_SHORT_ZERO, 26, -21, -39, -59, 57, -103, -51, JSONB.Constants.BC_TIMESTAMP_MINUTES, 49, -117, 1, 24, 35, -35, 31, JSONB.Constants.BC_STR_ASCII_FIX_5, 45, -7, JSONB.Constants.BC_INT32, 79, -14, 101, -114, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 92, 88, 25, -115, -27, -104, 87, 103, Byte.MAX_VALUE, 5, 100, JSONB.Constants.BC_NULL, 99, JSONB.Constants.BC_FLOAT_INT, -2, -11, JSONB.Constants.BC_FLOAT, 60, JSONB.Constants.BC_OBJECT_END, -50, -23, 104, JSONB.Constants.BC_INT32_SHORT_ZERO, -32, JSONB.Constants.BC_STR_ASCII_FIX_4, 67, JSONB.Constants.BC_STR_ASCII_FIX_32, 41, 46, JSONB.Constants.BC_TIMESTAMP_SECONDS, 21, 89, JSONB.Constants.BC_LOCAL_DATETIME, 10, -98, 110, JSONB.Constants.BC_INT32_SHORT_MAX, -33, 52, 53, 106, -49, JL_Constant.PREFIX_FLAG_SECOND, 34, -55, JSONB.Constants.BC_INT64_SHORT_MIN, -101, -119, -44, -19, JSONB.Constants.BC_TIMESTAMP_MILLIS, 18, -94, 13, 82, JSONB.Constants.BC_BIGINT, 2, JSONB.Constants.BC_INT32_NUM_MAX, JSONB.Constants.BC_LOCAL_DATE, JSONB.Constants.BC_INT64_BYTE_MAX, 97, 30, JSONB.Constants.BC_DOUBLE_LONG, 80, 4, -10, -62, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 37, -122, 86, 85, 9, JSONB.Constants.BC_INT64, JSONB.Constants.BC_BINARY}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int ROUNDS = 16;
    private static final int ROUND_SUBKEYS = 8;
    private static final int RS_GF_FDBK = 333;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final int SK_STEP = 33686018;
    private static final int TOTAL_SUBKEYS = 40;
    private int[] gSBox;
    private int[] gSubKeys;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[256];
    private int[] gMDS1 = new int[256];
    private int[] gMDS2 = new int[256];
    private int[] gMDS3 = new int[256];
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256));
        for (int i = 0; i < 256; i++) {
            byte[][] bArr = P;
            int i2 = bArr[0][i] & UByte.MAX_VALUE;
            int Mx_X = Mx_X(i2) & 255;
            int Mx_Y = Mx_Y(i2) & 255;
            int i3 = bArr[1][i] & 255;
            int[] iArr = {i2, i3};
            int[] iArr2 = {Mx_X, Mx_X(i3) & 255};
            int[] iArr3 = {Mx_Y, Mx_Y(i3) & 255};
            int[] iArr4 = this.gMDS0;
            int i4 = iArr[1] | (iArr2[1] << 8);
            int i5 = iArr3[1];
            iArr4[i] = i4 | (i5 << 16) | (i5 << 24);
            int[] iArr5 = this.gMDS1;
            int i6 = iArr3[0];
            iArr5[i] = i6 | (i6 << 8) | (iArr2[0] << 16) | (iArr[0] << 24);
            int[] iArr6 = this.gMDS2;
            int i7 = iArr2[1];
            int i8 = iArr3[1];
            iArr6[i] = (iArr[1] << 16) | i7 | (i8 << 8) | (i8 << 24);
            int[] iArr7 = this.gMDS3;
            int i9 = iArr2[0];
            iArr7[i] = (iArr3[0] << 16) | (iArr[0] << 8) | i9 | (i9 << 24);
        }
    }

    private int F32(int i, int[] iArr) {
        int i2;
        int i3;
        int b0 = b0(i);
        int b1 = b1(i);
        int b2 = b2(i);
        int b3 = b3(i);
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        int i8 = this.k64Cnt & 3;
        if (i8 != 0) {
            if (i8 == 1) {
                int[] iArr2 = this.gMDS0;
                byte[][] bArr = P;
                i2 = (iArr2[(bArr[0][b0] & UByte.MAX_VALUE) ^ b0(i4)] ^ this.gMDS1[(bArr[0][b1] & UByte.MAX_VALUE) ^ b1(i4)]) ^ this.gMDS2[(bArr[1][b2] & UByte.MAX_VALUE) ^ b2(i4)];
                i3 = this.gMDS3[(bArr[1][b3] & UByte.MAX_VALUE) ^ b3(i4)];
                return i3 ^ i2;
            }
            if (i8 != 2) {
                if (i8 != 3) {
                    return 0;
                }
            }
            int[] iArr3 = this.gMDS0;
            byte[][] bArr2 = P;
            byte[] bArr3 = bArr2[0];
            i2 = (iArr3[(bArr3[(bArr3[b0] & UByte.MAX_VALUE) ^ b0(i5)] & UByte.MAX_VALUE) ^ b0(i4)] ^ this.gMDS1[(bArr2[0][(bArr2[1][b1] & UByte.MAX_VALUE) ^ b1(i5)] & UByte.MAX_VALUE) ^ b1(i4)]) ^ this.gMDS2[(bArr2[1][(bArr2[0][b2] & UByte.MAX_VALUE) ^ b2(i5)] & UByte.MAX_VALUE) ^ b2(i4)];
            int[] iArr4 = this.gMDS3;
            byte[] bArr4 = bArr2[1];
            i3 = iArr4[(bArr4[(bArr4[b3] & UByte.MAX_VALUE) ^ b3(i5)] & UByte.MAX_VALUE) ^ b3(i4)];
            return i3 ^ i2;
        }
        byte[][] bArr5 = P;
        b0 = (bArr5[1][b0] & UByte.MAX_VALUE) ^ b0(i7);
        b1 = (bArr5[0][b1] & UByte.MAX_VALUE) ^ b1(i7);
        b2 = (bArr5[0][b2] & UByte.MAX_VALUE) ^ b2(i7);
        b3 = (bArr5[1][b3] & UByte.MAX_VALUE) ^ b3(i7);
        byte[][] bArr6 = P;
        b0 = (bArr6[1][b0] & UByte.MAX_VALUE) ^ b0(i6);
        b1 = (bArr6[1][b1] & UByte.MAX_VALUE) ^ b1(i6);
        b2 = (bArr6[0][b2] & UByte.MAX_VALUE) ^ b2(i6);
        b3 = (bArr6[0][b3] & UByte.MAX_VALUE) ^ b3(i6);
        int[] iArr32 = this.gMDS0;
        byte[][] bArr22 = P;
        byte[] bArr32 = bArr22[0];
        i2 = (iArr32[(bArr32[(bArr32[b0] & UByte.MAX_VALUE) ^ b0(i5)] & UByte.MAX_VALUE) ^ b0(i4)] ^ this.gMDS1[(bArr22[0][(bArr22[1][b1] & UByte.MAX_VALUE) ^ b1(i5)] & UByte.MAX_VALUE) ^ b1(i4)]) ^ this.gMDS2[(bArr22[1][(bArr22[0][b2] & UByte.MAX_VALUE) ^ b2(i5)] & UByte.MAX_VALUE) ^ b2(i4)];
        int[] iArr42 = this.gMDS3;
        byte[] bArr42 = bArr22[1];
        i3 = iArr42[(bArr42[(bArr42[b3] & UByte.MAX_VALUE) ^ b3(i5)] & UByte.MAX_VALUE) ^ b3(i4)];
        return i3 ^ i2;
    }

    private int Fe32_0(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 24) & 255) * 2) + InputDeviceCompat.SOURCE_DPAD] ^ ((iArr[(i & 255) * 2] ^ iArr[(((i >>> 8) & 255) * 2) + 1]) ^ iArr[(((i >>> 16) & 255) * 2) + 512]);
    }

    private int Fe32_3(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 16) & 255) * 2) + InputDeviceCompat.SOURCE_DPAD] ^ ((iArr[((i >>> 24) & 255) * 2] ^ iArr[((i & 255) * 2) + 1]) ^ iArr[(((i >>> 8) & 255) * 2) + 512]);
    }

    private int LFSR1(int i) {
        return ((i & 1) != 0 ? 180 : 0) ^ (i >> 1);
    }

    private int LFSR2(int i) {
        return ((i >> 2) ^ ((i & 2) != 0 ? 180 : 0)) ^ ((i & 1) != 0 ? 90 : 0);
    }

    private int Mx_X(int i) {
        return i ^ LFSR2(i);
    }

    private int Mx_Y(int i) {
        return LFSR2(i) ^ (LFSR1(i) ^ i);
    }

    private int RS_MDS_Encode(int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            i2 = RS_rem(i2);
        }
        int i4 = i ^ i2;
        for (int i5 = 0; i5 < 4; i5++) {
            i4 = RS_rem(i4);
        }
        return i4;
    }

    private int RS_rem(int i) {
        int i2 = i >>> 24;
        int i3 = i2 & 255;
        int i4 = ((i3 << 1) ^ ((i2 & 128) != 0 ? RS_GF_FDBK : 0)) & 255;
        int i5 = ((i3 >>> 1) ^ ((i2 & 1) != 0 ? Opcodes.IF_ACMPNE : 0)) ^ i4;
        return ((((i << 8) ^ (i5 << 24)) ^ (i4 << 16)) ^ (i5 << 8)) ^ i3;
    }

    private int b0(int i) {
        return i & 255;
    }

    private int b1(int i) {
        return (i >>> 8) & 255;
    }

    private int b2(int i) {
        return (i >>> 16) & 255;
    }

    private int b3(int i) {
        return (i >>> 24) & 255;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i) ^ this.gSubKeys[4];
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4) ^ this.gSubKeys[5];
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8) ^ this.gSubKeys[6];
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12) ^ this.gSubKeys[7];
        int i3 = 39;
        for (int i4 = 0; i4 < 16; i4 += 2) {
            int Fe32_0 = Fe32_0(littleEndianToInt);
            int Fe32_3 = Fe32_3(littleEndianToInt2);
            int i5 = littleEndianToInt4 ^ (((Fe32_3 * 2) + Fe32_0) + this.gSubKeys[i3]);
            littleEndianToInt3 = Integers.rotateLeft(littleEndianToInt3, 1) ^ ((Fe32_0 + Fe32_3) + this.gSubKeys[i3 - 1]);
            littleEndianToInt4 = Integers.rotateRight(i5, 1);
            int Fe32_02 = Fe32_0(littleEndianToInt3);
            int Fe32_32 = Fe32_3(littleEndianToInt4);
            int i6 = i3 - 3;
            int i7 = littleEndianToInt2 ^ (((Fe32_32 * 2) + Fe32_02) + this.gSubKeys[i3 - 2]);
            i3 -= 4;
            littleEndianToInt = Integers.rotateLeft(littleEndianToInt, 1) ^ ((Fe32_02 + Fe32_32) + this.gSubKeys[i6]);
            littleEndianToInt2 = Integers.rotateRight(i7, 1);
        }
        Pack.intToLittleEndian(littleEndianToInt3 ^ this.gSubKeys[0], bArr2, i2);
        Pack.intToLittleEndian(littleEndianToInt4 ^ this.gSubKeys[1], bArr2, i2 + 4);
        Pack.intToLittleEndian(this.gSubKeys[2] ^ littleEndianToInt, bArr2, i2 + 8);
        Pack.intToLittleEndian(this.gSubKeys[3] ^ littleEndianToInt2, bArr2, i2 + 12);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i) ^ this.gSubKeys[0];
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4) ^ this.gSubKeys[1];
        int i4 = 2;
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8) ^ this.gSubKeys[2];
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12) ^ this.gSubKeys[3];
        int i5 = 8;
        while (i3 < 16) {
            int Fe32_0 = Fe32_0(littleEndianToInt);
            int Fe32_3 = Fe32_3(littleEndianToInt2);
            littleEndianToInt3 = Integers.rotateRight(littleEndianToInt3 ^ ((Fe32_0 + Fe32_3) + this.gSubKeys[i5]), 1);
            littleEndianToInt4 = Integers.rotateLeft(littleEndianToInt4, 1) ^ ((Fe32_0 + (Fe32_3 * i4)) + this.gSubKeys[i5 + 1]);
            int Fe32_02 = Fe32_0(littleEndianToInt3);
            int Fe32_32 = Fe32_3(littleEndianToInt4);
            int i6 = i4;
            int i7 = i5 + 3;
            littleEndianToInt = Integers.rotateRight(littleEndianToInt ^ ((Fe32_02 + Fe32_32) + this.gSubKeys[i5 + 2]), 1);
            i5 += 4;
            littleEndianToInt2 = Integers.rotateLeft(littleEndianToInt2, 1) ^ ((Fe32_02 + (Fe32_32 * 2)) + this.gSubKeys[i7]);
            i3 += 2;
            i4 = i6;
        }
        Pack.intToLittleEndian(this.gSubKeys[4] ^ littleEndianToInt3, bArr2, i2);
        Pack.intToLittleEndian(littleEndianToInt4 ^ this.gSubKeys[5], bArr2, i2 + 4);
        Pack.intToLittleEndian(this.gSubKeys[6] ^ littleEndianToInt, bArr2, i2 + 8);
        Pack.intToLittleEndian(this.gSubKeys[7] ^ littleEndianToInt2, bArr2, i2 + 12);
    }

    private void setKey(byte[] bArr) {
        int b0;
        int b1;
        int b2;
        int b3;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        this.gSubKeys = new int[40];
        for (int i5 = 0; i5 < this.k64Cnt; i5++) {
            int i6 = i5 * 8;
            iArr[i5] = Pack.littleEndianToInt(bArr, i6);
            int littleEndianToInt = Pack.littleEndianToInt(bArr, i6 + 4);
            iArr2[i5] = littleEndianToInt;
            iArr3[(this.k64Cnt - 1) - i5] = RS_MDS_Encode(iArr[i5], littleEndianToInt);
        }
        for (int i7 = 0; i7 < 20; i7++) {
            int i8 = SK_STEP * i7;
            int F32 = F32(i8, iArr);
            int rotateLeft = Integers.rotateLeft(F32(i8 + 16843009, iArr2), 8);
            int i9 = F32 + rotateLeft;
            int[] iArr4 = this.gSubKeys;
            int i10 = i7 * 2;
            iArr4[i10] = i9;
            int i11 = i9 + rotateLeft;
            iArr4[i10 + 1] = (i11 << 9) | (i11 >>> 23);
        }
        int i12 = iArr3[0];
        int i13 = iArr3[1];
        int i14 = 2;
        int i15 = iArr3[2];
        int i16 = iArr3[3];
        this.gSBox = new int[1024];
        int i17 = 0;
        while (i17 < 256) {
            int i18 = this.k64Cnt & 3;
            if (i18 != 0) {
                if (i18 == 1) {
                    int[] iArr5 = this.gSBox;
                    int i19 = i17 * 2;
                    int[] iArr6 = this.gMDS0;
                    byte[][] bArr2 = P;
                    iArr5[i19] = iArr6[(bArr2[0][i17] & UByte.MAX_VALUE) ^ b0(i12)];
                    this.gSBox[i19 + 1] = this.gMDS1[(bArr2[0][i17] & UByte.MAX_VALUE) ^ b1(i12)];
                    this.gSBox[i19 + 512] = this.gMDS2[(bArr2[1][i17] & UByte.MAX_VALUE) ^ b2(i12)];
                    this.gSBox[i19 + InputDeviceCompat.SOURCE_DPAD] = this.gMDS3[(bArr2[1][i17] & UByte.MAX_VALUE) ^ b3(i12)];
                } else if (i18 == i14) {
                    i4 = i17;
                    i3 = i4;
                    i2 = i3;
                    i = i2;
                    int[] iArr7 = this.gSBox;
                    int i20 = i17 * 2;
                    int[] iArr8 = this.gMDS0;
                    byte[][] bArr3 = P;
                    byte[] bArr4 = bArr3[0];
                    iArr7[i20] = iArr8[(bArr4[(bArr4[i3] & UByte.MAX_VALUE) ^ b0(i13)] & UByte.MAX_VALUE) ^ b0(i12)];
                    this.gSBox[i20 + 1] = this.gMDS1[(bArr3[0][(bArr3[1][i2] & UByte.MAX_VALUE) ^ b1(i13)] & UByte.MAX_VALUE) ^ b1(i12)];
                    this.gSBox[i20 + 512] = this.gMDS2[(bArr3[1][(bArr3[0][i] & UByte.MAX_VALUE) ^ b2(i13)] & UByte.MAX_VALUE) ^ b2(i12)];
                    int[] iArr9 = this.gSBox;
                    int i21 = i20 + InputDeviceCompat.SOURCE_DPAD;
                    int[] iArr10 = this.gMDS3;
                    byte[] bArr5 = bArr3[1];
                    iArr9[i21] = iArr10[(bArr5[(bArr5[i4] & UByte.MAX_VALUE) ^ b3(i13)] & UByte.MAX_VALUE) ^ b3(i12)];
                } else if (i18 == 3) {
                    b3 = i17;
                    b0 = b3;
                    b1 = b0;
                    b2 = b1;
                }
                i17++;
                i14 = 2;
            } else {
                byte[][] bArr6 = P;
                b0 = (bArr6[1][i17] & UByte.MAX_VALUE) ^ b0(i16);
                b1 = (bArr6[0][i17] & UByte.MAX_VALUE) ^ b1(i16);
                b2 = (bArr6[0][i17] & UByte.MAX_VALUE) ^ b2(i16);
                b3 = (bArr6[1][i17] & UByte.MAX_VALUE) ^ b3(i16);
            }
            byte[][] bArr7 = P;
            i3 = (bArr7[1][b0] & UByte.MAX_VALUE) ^ b0(i15);
            i2 = (bArr7[1][b1] & UByte.MAX_VALUE) ^ b1(i15);
            i = (bArr7[0][b2] & UByte.MAX_VALUE) ^ b2(i15);
            i4 = (bArr7[0][b3] & UByte.MAX_VALUE) ^ b3(i15);
            int[] iArr72 = this.gSBox;
            int i202 = i17 * 2;
            int[] iArr82 = this.gMDS0;
            byte[][] bArr32 = P;
            byte[] bArr42 = bArr32[0];
            iArr72[i202] = iArr82[(bArr42[(bArr42[i3] & UByte.MAX_VALUE) ^ b0(i13)] & UByte.MAX_VALUE) ^ b0(i12)];
            this.gSBox[i202 + 1] = this.gMDS1[(bArr32[0][(bArr32[1][i2] & UByte.MAX_VALUE) ^ b1(i13)] & UByte.MAX_VALUE) ^ b1(i12)];
            this.gSBox[i202 + 512] = this.gMDS2[(bArr32[1][(bArr32[0][i] & UByte.MAX_VALUE) ^ b2(i13)] & UByte.MAX_VALUE) ^ b2(i12)];
            int[] iArr92 = this.gSBox;
            int i212 = i202 + InputDeviceCompat.SOURCE_DPAD;
            int[] iArr102 = this.gMDS3;
            byte[] bArr52 = bArr32[1];
            iArr92[i212] = iArr102[(bArr52[(bArr52[i4] & UByte.MAX_VALUE) ^ b3(i13)] & UByte.MAX_VALUE) ^ b3(i12)];
            i17++;
            i14 = 2;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Twofish";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
        }
        this.encrypting = z;
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.workingKey = key;
        int length = key.length * 8;
        if (length != 128 && length != 192 && length != 256) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), length, cipherParameters, Utils.getPurpose(z)));
        byte[] bArr = this.workingKey;
        this.k64Cnt = bArr.length / 8;
        setKey(bArr);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Twofish not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            encryptBlock(bArr, i, bArr2, i2);
            return 16;
        }
        decryptBlock(bArr, i, bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.workingKey;
        if (bArr != null) {
            setKey(bArr);
        }
    }
}
