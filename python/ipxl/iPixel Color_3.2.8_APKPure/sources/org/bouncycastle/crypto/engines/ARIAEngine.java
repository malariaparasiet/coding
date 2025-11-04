package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import java.lang.reflect.Array;
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
import org.bouncycastle.util.Bytes;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class ARIAEngine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    private static final byte[][] C = {Hex.decodeStrict("517cc1b727220a94fe13abe8fa9a6ee0"), Hex.decodeStrict("6db14acc9e21c820ff28b1d5ef5de2b0"), Hex.decodeStrict("db92371d2126e9700324977504e8c90e")};
    private static final byte[] SB1_sbox = {99, JSONB.Constants.BC_STR_UTF16LE, 119, JSONB.Constants.BC_STR_UTF16, -14, 107, 111, -59, JSONB.Constants.BC_INT32_BYTE_MIN, 1, 103, 43, -2, JSONB.Constants.BC_INT64_BYTE_MAX, JSONB.Constants.BC_TIMESTAMP_MILLIS, 118, -54, -126, -55, JSONB.Constants.BC_STR_UTF16BE, -6, 89, JSONB.Constants.BC_INT32_SHORT_MAX, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MINUTES, -44, -94, JSONB.Constants.BC_NULL, -100, JSONB.Constants.BC_ARRAY, 114, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_FLOAT, -3, JSONB.Constants.BC_REFERENCE, 38, 54, 63, -9, -52, 52, JSONB.Constants.BC_OBJECT_END, -27, -15, 113, JSONB.Constants.BC_INT64_NUM_MIN, 49, 21, 4, JSONB.Constants.BC_INT64_SHORT_MAX, 35, -61, 24, -106, 5, -102, 7, 18, ByteCompanionObject.MIN_VALUE, -30, -21, 39, JSONB.Constants.BC_DOUBLE_NUM_0, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, JSONB.Constants.BC_DOUBLE_NUM_1, 41, -29, JSONB.Constants.BC_INT32_NUM_MAX, -124, 83, -47, 0, -19, 32, -4, JSONB.Constants.BC_TRUE, 91, 106, -53, JSONB.Constants.BC_INT64, 57, JSONB.Constants.BC_STR_ASCII_FIX_1, 76, 88, -49, JSONB.Constants.BC_INT64_BYTE_ZERO, -17, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -5, 67, JSONB.Constants.BC_STR_ASCII_FIX_4, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, JSONB.Constants.BC_LOCAL_DATETIME, 81, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_INT32_SHORT_MIN, -113, JSONB.Constants.BC_TYPED_ANY, -99, JSONB.Constants.BC_INT32_BYTE_ZERO, -11, -68, JSONB.Constants.BC_FLOAT_INT, -38, 33, JSONB.Constants.BC_INT32_NUM_16, -1, -13, -46, -51, 12, 19, -20, 95, -105, JSONB.Constants.BC_INT32_SHORT_ZERO, 23, JSONB.Constants.BC_INT64_SHORT_ZERO, JSONB.Constants.BC_LOCAL_TIME, JSONB.Constants.BC_STR_GB18030, Base64.padSymbol, 100, 93, 25, 115, 96, -127, 79, JL_Constant.PREFIX_FLAG_SECOND, 34, 42, JSONB.Constants.BC_CHAR, -120, 70, -18, JSONB.Constants.BC_DECIMAL_LONG, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, JSONB.Constants.BC_TIMESTAMP_SECONDS, 98, JSONB.Constants.BC_BINARY, -107, -28, JSONB.Constants.BC_STR_ASCII, -25, JSONB.Constants.BC_INT64_BYTE_MIN, 55, JSONB.Constants.BC_STR_ASCII_FIX_36, -115, -43, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_LOCAL_DATE, 108, 86, -12, -22, 101, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_TIMESTAMP, 8, -70, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 37, 46, 28, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_DOUBLE_LONG, -58, -24, -35, 116, 31, 75, JSONB.Constants.BC_INT8, -117, -118, 112, 62, JSONB.Constants.BC_DOUBLE, 102, JSONB.Constants.BC_INT32, 3, -10, 14, 97, 53, 87, JSONB.Constants.BC_DECIMAL, -122, -63, 29, -98, -31, -8, -104, 17, JSONB.Constants.BC_STR_ASCII_FIX_32, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, JSONB.Constants.BC_INT64_INT, -26, 66, 104, 65, -103, 45, 15, JSONB.Constants.BC_FALSE, 84, JSONB.Constants.BC_BIGINT, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER};
    private static final byte[] SB2_sbox = {-30, JSONB.Constants.BC_STR_ASCII_FIX_5, 84, -4, -108, -62, JSONB.Constants.BC_STR_ASCII_FIX_1, -52, 98, 13, 106, 70, 60, JSONB.Constants.BC_STR_ASCII_FIX_4, -117, -47, 94, -6, 100, -53, JSONB.Constants.BC_DOUBLE_LONG, -105, JSONB.Constants.BC_INT64, 43, -68, 119, 46, 3, -45, 25, 89, -63, 29, 6, 65, 107, 85, JSONB.Constants.BC_INT32_NUM_MIN, -103, JSONB.Constants.BC_STR_ASCII_FIX_32, -22, -100, 24, JSONB.Constants.BC_TIMESTAMP, 99, -33, -25, JSONB.Constants.BC_BIGINT, 0, 115, 102, -5, -106, 76, -123, -28, 58, 9, 69, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 15, -18, JSONB.Constants.BC_INT32_NUM_16, -21, 45, Byte.MAX_VALUE, -12, 41, JSONB.Constants.BC_TIMESTAMP_SECONDS, -49, JSONB.Constants.BC_TIMESTAMP_MINUTES, JSONB.Constants.BC_BINARY, -115, JSONB.Constants.BC_STR_ASCII_FIX_MAX, JSONB.Constants.BC_INT64_BYTE_MIN, -107, -7, JSONB.Constants.BC_INT32_NUM_MAX, -50, -51, 8, JSONB.Constants.BC_STR_UTF8, -120, JSONB.Constants.BC_INT32_BYTE_ZERO, 92, -125, 42, 40, JSONB.Constants.BC_INT32_SHORT_MAX, -37, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_INT64_SHORT_MAX, JSONB.Constants.BC_REFERENCE, JSONB.Constants.BC_ARRAY, 18, 83, -1, -121, 14, 49, 54, 33, 88, JSONB.Constants.BC_INT32, 1, -114, 55, 116, 50, -54, -23, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_TIMESTAMP_MILLIS, 12, JSONB.Constants.BC_INT64_BYTE_MAX, JSONB.Constants.BC_INT64_SHORT_ZERO, 86, 66, 38, 7, -104, 96, -39, JSONB.Constants.BC_FLOAT_INT, JSONB.Constants.BC_DECIMAL, 17, JSONB.Constants.BC_INT32_SHORT_MIN, -20, 32, -116, JSONB.Constants.BC_INT8, -96, -55, -124, 4, 73, 35, -15, 79, 80, 31, 19, JL_Constant.PREFIX_FLAG_SECOND, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_INT64_SHORT_MIN, -98, 87, -29, -61, JSONB.Constants.BC_STR_UTF16, 101, 59, 2, -113, 62, -24, 37, JSONB.Constants.BC_TYPED_ANY, -27, 21, -35, -3, 23, JSONB.Constants.BC_LOCAL_DATE, JSONB.Constants.BC_INT64_INT, -44, -102, JSONB.Constants.BC_STR_GB18030, -59, 57, 103, -2, 118, -99, 67, JSONB.Constants.BC_LOCAL_TIME, -31, JSONB.Constants.BC_INT64_BYTE_ZERO, -11, 104, -14, 27, 52, 112, 5, JSONB.Constants.BC_ARRAY_FIX_MAX, -118, -43, JSONB.Constants.BC_STR_ASCII, -122, JSONB.Constants.BC_LOCAL_DATETIME, JSONB.Constants.BC_INT32_BYTE_MIN, -58, 81, 75, 30, JSONB.Constants.BC_OBJECT, 39, -10, 53, -46, 110, 36, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -126, 95, -38, -26, 117, -94, -17, 44, JSONB.Constants.BC_DOUBLE_NUM_0, 28, -97, 93, 111, ByteCompanionObject.MIN_VALUE, 10, 114, JSONB.Constants.BC_INT32_SHORT_ZERO, -101, 108, JSONB.Constants.BC_CHAR, 11, 91, 51, JSONB.Constants.BC_STR_UTF16BE, 90, 82, -13, 97, -95, -9, JSONB.Constants.BC_FALSE, -42, 63, JSONB.Constants.BC_STR_UTF16LE, JSONB.Constants.BC_STR_ASCII_FIX_36, -19, 20, -32, JSONB.Constants.BC_OBJECT_END, Base64.padSymbol, 34, JSONB.Constants.BC_DOUBLE_NUM_1, -8, -119, -34, 113, 26, JSONB.Constants.BC_NULL, -70, JSONB.Constants.BC_DOUBLE, -127};
    private static final byte[] SB3_sbox = {82, 9, 106, -43, JSONB.Constants.BC_INT32_BYTE_MIN, 54, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_ARRAY_FIX_MAX, -98, -127, -13, JSONB.Constants.BC_INT64_BYTE_MAX, -5, JSONB.Constants.BC_STR_UTF16LE, -29, 57, -126, -101, JSONB.Constants.BC_INT32_NUM_MAX, -1, -121, 52, -114, 67, JSONB.Constants.BC_INT32_SHORT_ZERO, JSONB.Constants.BC_INT64_SHORT_ZERO, -34, -23, -53, 84, JSONB.Constants.BC_STR_UTF16, -108, 50, JSONB.Constants.BC_OBJECT, -62, 35, Base64.padSymbol, -18, 76, -107, 11, 66, -6, -61, JSONB.Constants.BC_STR_ASCII_FIX_5, 8, 46, -95, 102, 40, -39, 36, JSONB.Constants.BC_DOUBLE_NUM_0, 118, 91, -94, 73, JSONB.Constants.BC_STR_ASCII_FIX_36, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -44, JSONB.Constants.BC_ARRAY, 92, -52, 93, 101, JSONB.Constants.BC_FLOAT_INT, JSONB.Constants.BC_TYPED_ANY, 108, 112, JSONB.Constants.BC_INT32, 80, -3, -19, JSONB.Constants.BC_DECIMAL, -38, 94, 21, 70, 87, JSONB.Constants.BC_LOCAL_TIME, -115, -99, -124, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MILLIS, 0, -116, -68, -45, 10, -9, -28, 88, 5, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_DOUBLE_NUM_1, 69, 6, JSONB.Constants.BC_INT64_BYTE_ZERO, 44, 30, -113, -54, 63, 15, 2, -63, JSONB.Constants.BC_NULL, JSONB.Constants.BC_INT8, 3, 1, 19, -118, 107, 58, JSONB.Constants.BC_BINARY, 17, 65, 79, 103, JL_Constant.PREFIX_FLAG_SECOND, -22, -105, -14, -49, -50, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_DOUBLE_LONG, -26, 115, -106, JSONB.Constants.BC_TIMESTAMP_SECONDS, 116, 34, -25, JSONB.Constants.BC_TIMESTAMP_MINUTES, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, JSONB.Constants.BC_INT32_SHORT_MAX, -15, 26, 113, 29, 41, -59, -119, 111, JSONB.Constants.BC_FLOAT, 98, 14, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 24, JSONB.Constants.BC_INT64, 27, -4, 86, 62, 75, -58, -46, JSONB.Constants.BC_STR_ASCII, 32, -102, -37, JSONB.Constants.BC_INT64_SHORT_MIN, -2, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -51, 90, -12, 31, -35, JSONB.Constants.BC_LOCAL_DATETIME, 51, -120, 7, JSONB.Constants.BC_INT64_SHORT_MAX, 49, JSONB.Constants.BC_TRUE, 18, JSONB.Constants.BC_INT32_NUM_16, 89, 39, ByteCompanionObject.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, JSONB.Constants.BC_LOCAL_DATE, 25, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_STR_ASCII_FIX_1, 13, 45, -27, JSONB.Constants.BC_STR_UTF8, -97, JSONB.Constants.BC_REFERENCE, -55, -100, -17, -96, -32, 59, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_TIMESTAMP, 42, -11, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_INT64_BYTE_MIN, -21, JSONB.Constants.BC_BIGINT, 60, -125, 83, -103, 97, 23, 43, 4, JSONB.Constants.BC_STR_GB18030, -70, 119, -42, 38, -31, JSONB.Constants.BC_STR_ASCII_FIX_32, 20, 99, 85, 33, 12, JSONB.Constants.BC_STR_UTF16BE};
    private static final byte[] SB4_sbox = {JSONB.Constants.BC_INT32_BYTE_MIN, 104, -103, 27, -121, JSONB.Constants.BC_DECIMAL, 33, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 80, 57, -37, -31, 114, 9, 98, 60, 62, JSONB.Constants.BC_STR_GB18030, 94, -114, -15, -96, -52, JSONB.Constants.BC_ARRAY_FIX_MAX, 42, 29, -5, JSONB.Constants.BC_FLOAT_INT, -42, 32, JSONB.Constants.BC_INT64_SHORT_ZERO, -115, -127, 101, -11, -119, -53, -99, 119, -58, 87, 67, 86, 23, -44, JSONB.Constants.BC_INT32_SHORT_MIN, 26, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_INT64_SHORT_MIN, 99, 108, -29, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_INT64_BYTE_MIN, 100, 106, 83, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, JSONB.Constants.BC_INT32_BYTE_ZERO, -104, 12, -12, -101, -19, Byte.MAX_VALUE, 34, 118, JSONB.Constants.BC_NULL, -35, 58, 11, 88, 103, -120, 6, -61, 53, 13, 1, -117, -116, -62, -26, 95, 2, 36, 117, JSONB.Constants.BC_REFERENCE, 102, 30, -27, -30, 84, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_INT32_NUM_16, -50, JSONB.Constants.BC_STR_UTF8, -24, 8, 44, 18, -105, 50, JSONB.Constants.BC_TIMESTAMP_MILLIS, JSONB.Constants.BC_DOUBLE_LONG, 39, 10, 35, -33, -17, -54, -39, JSONB.Constants.BC_DECIMAL_LONG, -6, JL_Constant.PREFIX_FLAG_SECOND, 49, 107, -47, JSONB.Constants.BC_TIMESTAMP_MINUTES, 25, 73, JSONB.Constants.BC_INT8, 81, -106, -18, -28, JSONB.Constants.BC_LOCAL_DATETIME, 65, -38, -1, -51, 85, -122, 54, JSONB.Constants.BC_INT64, 97, 82, -8, JSONB.Constants.BC_BIGINT, 14, -126, JSONB.Constants.BC_INT32, JSONB.Constants.BC_STR_ASCII_FIX_32, -102, -32, JSONB.Constants.BC_INT32_SHORT_MAX, -98, 92, 4, 75, 52, 21, JSONB.Constants.BC_STR_ASCII, 38, JSONB.Constants.BC_LOCAL_TIME, -34, 41, JSONB.Constants.BC_TIMESTAMP, JSONB.Constants.BC_TYPED_ANY, JSONB.Constants.BC_INT64_BYTE_MAX, -124, -23, -46, -70, 93, -13, -59, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_ARRAY, 59, 113, JSONB.Constants.BC_INT32_SHORT_ZERO, 70, 43, -4, -21, 111, -43, -10, 20, -2, JSONB.Constants.BC_STR_UTF16LE, 112, 90, JSONB.Constants.BC_STR_UTF16BE, -3, JSONB.Constants.BC_INT32_NUM_MAX, 24, -125, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_BINARY, 31, 5, -107, 116, JSONB.Constants.BC_LOCAL_DATE, -63, 91, JSONB.Constants.BC_STR_ASCII_FIX_1, -123, JSONB.Constants.BC_STR_ASCII_FIX_36, 19, 7, 79, JSONB.Constants.BC_STR_ASCII_FIX_5, 69, JSONB.Constants.BC_DOUBLE_NUM_0, 15, -55, 28, JSONB.Constants.BC_OBJECT, -68, -20, 115, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_STR_UTF16, -49, 89, -113, -95, -7, 45, -14, JSONB.Constants.BC_TRUE, 0, -108, 55, -97, JSONB.Constants.BC_INT64_BYTE_ZERO, 46, -100, 110, 40, 63, ByteCompanionObject.MIN_VALUE, JSONB.Constants.BC_INT32_NUM_MIN, Base64.padSymbol, -45, 37, -118, JSONB.Constants.BC_DOUBLE, -25, 66, JSONB.Constants.BC_DOUBLE_NUM_1, JSONB.Constants.BC_INT64_SHORT_MAX, -22, -9, 76, 17, 51, 3, -94, JSONB.Constants.BC_TIMESTAMP_SECONDS, 96};
    boolean forEncryption;
    private byte[][] roundKeys;

    public ARIAEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256));
    }

    protected static void A(byte[] bArr) {
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        byte b5 = bArr[4];
        byte b6 = bArr[5];
        byte b7 = bArr[6];
        byte b8 = bArr[7];
        byte b9 = bArr[8];
        byte b10 = bArr[9];
        byte b11 = bArr[10];
        byte b12 = bArr[11];
        byte b13 = bArr[12];
        byte b14 = bArr[13];
        byte b15 = bArr[14];
        byte b16 = bArr[15];
        bArr[0] = (byte) ((((((b4 ^ b5) ^ b7) ^ b9) ^ b10) ^ b14) ^ b15);
        bArr[1] = (byte) ((((((b3 ^ b6) ^ b8) ^ b9) ^ b10) ^ b13) ^ b16);
        bArr[2] = (byte) ((((((b2 ^ b5) ^ b7) ^ b11) ^ b12) ^ b13) ^ b16);
        bArr[3] = (byte) ((((((b ^ b6) ^ b8) ^ b11) ^ b12) ^ b14) ^ b15);
        int i = b ^ b3;
        bArr[4] = (byte) (((((i ^ b6) ^ b9) ^ b12) ^ b15) ^ b16);
        int i2 = b2 ^ b4;
        bArr[5] = (byte) (((((i2 ^ b5) ^ b10) ^ b11) ^ b15) ^ b16);
        bArr[6] = (byte) (((((i ^ b8) ^ b10) ^ b11) ^ b13) ^ b14);
        bArr[7] = (byte) (((((i2 ^ b7) ^ b9) ^ b12) ^ b13) ^ b14);
        int i3 = b ^ b2;
        bArr[8] = (byte) (((((i3 ^ b5) ^ b8) ^ b11) ^ b14) ^ b16);
        bArr[9] = (byte) (((((i3 ^ b6) ^ b7) ^ b12) ^ b13) ^ b15);
        int i4 = b3 ^ b4;
        bArr[10] = (byte) (((((i4 ^ b6) ^ b7) ^ b9) ^ b14) ^ b16);
        bArr[11] = (byte) (((((i4 ^ b5) ^ b8) ^ b10) ^ b13) ^ b15);
        int i5 = b2 ^ b3;
        bArr[12] = (byte) (((((i5 ^ b7) ^ b8) ^ b10) ^ b12) ^ b13);
        int i6 = b ^ b4;
        bArr[13] = (byte) (((((i6 ^ b7) ^ b8) ^ b9) ^ b11) ^ b14);
        bArr[14] = (byte) (((((i6 ^ b5) ^ b6) ^ b10) ^ b12) ^ b15);
        bArr[15] = (byte) (((((i5 ^ b5) ^ b6) ^ b9) ^ b11) ^ b16);
    }

    protected static void FE(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL2(bArr);
        A(bArr);
    }

    protected static void FO(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL1(bArr);
        A(bArr);
    }

    protected static byte SB1(byte b) {
        return SB1_sbox[b & UByte.MAX_VALUE];
    }

    protected static byte SB2(byte b) {
        return SB2_sbox[b & UByte.MAX_VALUE];
    }

    protected static byte SB3(byte b) {
        return SB3_sbox[b & UByte.MAX_VALUE];
    }

    protected static byte SB4(byte b) {
        return SB4_sbox[b & UByte.MAX_VALUE];
    }

    protected static void SL1(byte[] bArr) {
        bArr[0] = SB1(bArr[0]);
        bArr[1] = SB2(bArr[1]);
        bArr[2] = SB3(bArr[2]);
        bArr[3] = SB4(bArr[3]);
        bArr[4] = SB1(bArr[4]);
        bArr[5] = SB2(bArr[5]);
        bArr[6] = SB3(bArr[6]);
        bArr[7] = SB4(bArr[7]);
        bArr[8] = SB1(bArr[8]);
        bArr[9] = SB2(bArr[9]);
        bArr[10] = SB3(bArr[10]);
        bArr[11] = SB4(bArr[11]);
        bArr[12] = SB1(bArr[12]);
        bArr[13] = SB2(bArr[13]);
        bArr[14] = SB3(bArr[14]);
        bArr[15] = SB4(bArr[15]);
    }

    protected static void SL2(byte[] bArr) {
        bArr[0] = SB3(bArr[0]);
        bArr[1] = SB4(bArr[1]);
        bArr[2] = SB1(bArr[2]);
        bArr[3] = SB2(bArr[3]);
        bArr[4] = SB3(bArr[4]);
        bArr[5] = SB4(bArr[5]);
        bArr[6] = SB1(bArr[6]);
        bArr[7] = SB2(bArr[7]);
        bArr[8] = SB3(bArr[8]);
        bArr[9] = SB4(bArr[9]);
        bArr[10] = SB1(bArr[10]);
        bArr[11] = SB2(bArr[11]);
        bArr[12] = SB3(bArr[12]);
        bArr[13] = SB4(bArr[13]);
        bArr[14] = SB1(bArr[14]);
        bArr[15] = SB2(bArr[15]);
    }

    private int bitsOfSecurity() {
        byte[][] bArr = this.roundKeys;
        if (bArr.length > 13) {
            return bArr.length > 15 ? 256 : 192;
        }
        return 128;
    }

    protected static byte[][] keySchedule(boolean z, byte[] bArr) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 3;
        int i2 = i - 2;
        byte[][] bArr2 = C;
        byte[] bArr3 = bArr2[i2];
        byte[] bArr4 = bArr2[(i - 1) % 3];
        byte[] bArr5 = bArr2[i % 3];
        byte[] bArr6 = new byte[16];
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr, 0, bArr6, 0, 16);
        System.arraycopy(bArr, 16, bArr7, 0, length - 16);
        byte[] bArr8 = new byte[16];
        byte[] bArr9 = new byte[16];
        byte[] bArr10 = new byte[16];
        byte[] bArr11 = new byte[16];
        System.arraycopy(bArr6, 0, bArr8, 0, 16);
        System.arraycopy(bArr8, 0, bArr9, 0, 16);
        FO(bArr9, bArr3);
        xor(bArr9, bArr7);
        System.arraycopy(bArr9, 0, bArr10, 0, 16);
        FE(bArr10, bArr4);
        xor(bArr10, bArr8);
        System.arraycopy(bArr10, 0, bArr11, 0, 16);
        FO(bArr11, bArr5);
        xor(bArr11, bArr9);
        int i3 = i2 * 2;
        int i4 = i3 + 12;
        byte[][] bArr12 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i3 + 13, 16);
        keyScheduleRound(bArr12[0], bArr8, bArr9, 19);
        keyScheduleRound(bArr12[1], bArr9, bArr10, 19);
        keyScheduleRound(bArr12[2], bArr10, bArr11, 19);
        keyScheduleRound(bArr12[3], bArr11, bArr8, 19);
        keyScheduleRound(bArr12[4], bArr8, bArr9, 31);
        keyScheduleRound(bArr12[5], bArr9, bArr10, 31);
        keyScheduleRound(bArr12[6], bArr10, bArr11, 31);
        keyScheduleRound(bArr12[7], bArr11, bArr8, 31);
        keyScheduleRound(bArr12[8], bArr8, bArr9, 67);
        keyScheduleRound(bArr12[9], bArr9, bArr10, 67);
        keyScheduleRound(bArr12[10], bArr10, bArr11, 67);
        keyScheduleRound(bArr12[11], bArr11, bArr8, 67);
        keyScheduleRound(bArr12[12], bArr8, bArr9, 97);
        if (i4 > 12) {
            keyScheduleRound(bArr12[13], bArr9, bArr10, 97);
            keyScheduleRound(bArr12[14], bArr10, bArr11, 97);
            if (i4 > 14) {
                keyScheduleRound(bArr12[15], bArr11, bArr8, 97);
                keyScheduleRound(bArr12[16], bArr8, bArr9, 109);
            }
        }
        if (!z) {
            reverseKeys(bArr12);
            for (int i5 = 1; i5 < i4; i5++) {
                A(bArr12[i5]);
            }
        }
        return bArr12;
    }

    protected static void keyScheduleRound(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        int i4 = 8 - i3;
        int i5 = bArr3[15 - i2] & 255;
        int i6 = 0;
        while (i6 < 16) {
            int i7 = bArr3[(i6 - i2) & 15] & 255;
            bArr[i6] = (byte) (((i5 << i4) | (i7 >>> i3)) ^ (bArr2[i6] & 255));
            i6++;
            i5 = i7;
        }
    }

    protected static void reverseKeys(byte[][] bArr) {
        int length = bArr.length;
        int i = length / 2;
        int i2 = length - 1;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            int i4 = i2 - i3;
            bArr[i3] = bArr[i4];
            bArr[i4] = bArr2;
        }
    }

    protected static void xor(byte[] bArr, byte[] bArr2) {
        Bytes.xorTo(16, bArr2, bArr);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "ARIA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to ARIA init - " + cipherParameters.getClass().getName());
        }
        this.forEncryption = z;
        this.roundKeys = keySchedule(z, ((KeyParameter) cipherParameters).getKey());
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.roundKeys == null) {
            throw new IllegalStateException("ARIA engine not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 > bArr2.length - 16) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, i, bArr3, 0, 16);
        int length = this.roundKeys.length - 3;
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            FO(bArr3, this.roundKeys[i3]);
            i3 += 2;
            FE(bArr3, this.roundKeys[i4]);
        }
        FO(bArr3, this.roundKeys[i3]);
        xor(bArr3, this.roundKeys[i3 + 1]);
        SL2(bArr3);
        xor(bArr3, this.roundKeys[i3 + 2]);
        System.arraycopy(bArr3, 0, bArr2, i2, 16);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
