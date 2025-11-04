package org.bouncycastle.crypto.digests;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Bytes;

/* loaded from: classes3.dex */
public abstract class HarakaBase implements Digest {
    protected static final int DIGEST_SIZE = 32;
    static final byte[][] RC = {new byte[]{-99, JSONB.Constants.BC_STR_UTF16, -127, 117, JSONB.Constants.BC_INT32_NUM_MIN, -2, -59, JSONB.Constants.BC_DOUBLE_NUM_0, 10, JSONB.Constants.BC_INT64_SHORT_MIN, 32, -26, 76, 112, -124, 6}, new byte[]{23, -9, 8, JSONB.Constants.BC_INT32_NUM_MAX, JSONB.Constants.BC_ARRAY, 107, 15, 100, 107, -96, -13, -120, -31, JSONB.Constants.BC_DOUBLE_LONG, 102, -117}, new byte[]{20, JSONB.Constants.BC_BINARY, 2, -97, 96, -99, 2, -49, -104, -124, -14, 83, 45, -34, 2, 52}, new byte[]{JSONB.Constants.BC_STR_ASCII, 79, 91, -3, JSONB.Constants.BC_NULL, -68, -13, JSONB.Constants.BC_BIGINT, 8, 79, JSONB.Constants.BC_STR_UTF16, 46, -26, -22, -42, 14}, new byte[]{JSONB.Constants.BC_INT32_SHORT_ZERO, 112, 57, JSONB.Constants.BC_INT64, 28, -51, -18, JSONB.Constants.BC_STR_ASCII, -117, JSONB.Constants.BC_INT32_SHORT_ZERO, 114, JSONB.Constants.BC_INT32, -53, JSONB.Constants.BC_FALSE, -49, -53}, new byte[]{JSONB.Constants.BC_STR_UTF16, 5, -118, 43, -19, 53, 83, -115, JSONB.Constants.BC_FLOAT, 50, JSONB.Constants.BC_CHAR, 110, -18, -51, -22, JSONB.Constants.BC_STR_GB18030}, new byte[]{27, -17, 79, -38, 97, 39, 65, -30, JSONB.Constants.BC_INT64_BYTE_ZERO, JSONB.Constants.BC_STR_UTF16LE, 46, 94, 67, -113, -62, 103}, new byte[]{59, 11, JSONB.Constants.BC_INT64_SHORT_MAX, 31, -30, -3, 95, 103, 7, -52, -54, JSONB.Constants.BC_NULL, JSONB.Constants.BC_FALSE, -39, 36, 41}, new byte[]{-18, 101, -44, JSONB.Constants.BC_DECIMAL, -54, -113, -37, -20, -23, Byte.MAX_VALUE, -122, -26, -15, 99, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_TIMESTAMP_MILLIS}, new byte[]{51, JSONB.Constants.BC_STR_GB18030, 3, JSONB.Constants.BC_TIMESTAMP_MINUTES, 79, JSONB.Constants.BC_INT32_SHORT_MIN, 42, 91, 100, -51, JSONB.Constants.BC_FLOAT, -44, -124, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_INT32_BYTE_MIN, 28}, new byte[]{0, -104, -10, -115, 46, -117, 2, JSONB.Constants.BC_STR_ASCII_FIX_32, JSONB.Constants.BC_INT64_INT, 35, 23, -108, JSONB.Constants.BC_DECIMAL, 11, -52, JSONB.Constants.BC_DOUBLE_NUM_0}, new byte[]{-118, 45, -99, 92, JSONB.Constants.BC_INT64_BYTE_MIN, -98, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, JSONB.Constants.BC_STR_ASCII_FIX_1, 114, 85, 111, -34, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 4, -6}, new byte[]{-44, -97, 18, 41, 46, 79, -6, 14, 18, 42, 119, 107, 43, -97, JSONB.Constants.BC_DOUBLE_LONG, -33}, new byte[]{-18, 18, 106, JSONB.Constants.BC_BIGINT, JSONB.Constants.BC_TIMESTAMP, 17, -42, 50, 54, -94, 73, -12, JSONB.Constants.BC_INT32_SHORT_ZERO, 3, -95, 30}, new byte[]{JSONB.Constants.BC_OBJECT, -20, JSONB.Constants.BC_LOCAL_DATETIME, -100, -55, 0, -106, 95, -124, 0, 5, 75, -120, 73, 4, JSONB.Constants.BC_NULL}, new byte[]{-20, JSONB.Constants.BC_REFERENCE, -27, 39, -29, JSONB.Constants.BC_INT64_SHORT_MAX, -94, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 79, -100, 25, -99, JSONB.Constants.BC_INT64_NUM_MIN, 94, 2, 33}, new byte[]{115, 1, -44, -126, -51, 46, 40, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_FLOAT, -55, 89, JSONB.Constants.BC_LOCAL_TIME, -8, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 58, JSONB.Constants.BC_INT64_INT}, new byte[]{107, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_INT32_BYTE_MIN, JSONB.Constants.BC_INT32_NUM_16, -39, -17, -14, 55, 23, JSONB.Constants.BC_FALSE, -122, 97, 13, 112, 96, 98}, new byte[]{-58, -102, -4, -10, 83, JSONB.Constants.BC_BINARY, -62, -127, 67, 4, JSONB.Constants.BC_INT32_BYTE_MIN, 33, -62, 69, -54, 90}, new byte[]{58, -108, -47, 54, -24, JSONB.Constants.BC_TYPED_ANY, JSONB.Constants.BC_NULL, 44, JSONB.Constants.BC_BIGINT, 104, 107, 34, 60, -105, 35, JSONB.Constants.BC_TYPED_ANY}, new byte[]{JSONB.Constants.BC_DOUBLE_LONG, 113, JSONB.Constants.BC_INT32_NUM_16, -27, 88, JSONB.Constants.BC_DECIMAL, -70, 108, -21, -122, 88, 34, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_TYPED_ANY, JSONB.Constants.BC_INT64_INT, -45}, new byte[]{-115, 18, -31, 36, -35, -3, Base64.padSymbol, JSONB.Constants.BC_REFERENCE, 119, -58, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_TIMESTAMP, -27, 60, -122, -37}, new byte[]{JSONB.Constants.BC_TRUE, 18, 34, -53, -29, -115, -28, -125, -100, -96, -21, -1, 104, 98, 96, JSONB.Constants.BC_BIGINT}, new byte[]{JSONB.Constants.BC_STR_UTF16BE, -9, 43, JSONB.Constants.BC_INT64_SHORT_MAX, JSONB.Constants.BC_STR_ASCII_FIX_5, 26, JSONB.Constants.BC_DECIMAL, 45, -100, -47, -28, -30, JL_Constant.PREFIX_FLAG_SECOND, -45, 75, 115}, new byte[]{JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_TYPED_ANY, JSONB.Constants.BC_DOUBLE_NUM_1, 44, JSONB.Constants.BC_INT64_SHORT_ZERO, 21, 20, 75, 67, 27, JSONB.Constants.BC_INT32_BYTE_MIN, 97, -61, JSONB.Constants.BC_INT32_SHORT_MAX, JSONB.Constants.BC_BIGINT, 67}, new byte[]{-103, 104, -21, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -35, 49, JSONB.Constants.BC_DOUBLE_NUM_0, 3, -10, -17, 7, -25, JSONB.Constants.BC_LOCAL_DATETIME, 117, JSONB.Constants.BC_LOCAL_TIME, -37}, new byte[]{44, JSONB.Constants.BC_INT32_SHORT_MAX, -54, JSONB.Constants.BC_STR_GB18030, 2, 35, 94, -114, 119, 89, 117, 60, 75, 97, -13, JSONB.Constants.BC_STR_ASCII_FIX_36}, new byte[]{-7, 23, -122, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_DECIMAL, -27, 27, JSONB.Constants.BC_STR_ASCII_FIX_36, 119, JSONB.Constants.BC_STR_UTF16BE, -34, -42, 23, 90, JSONB.Constants.BC_LOCAL_TIME, -51}, new byte[]{93, -18, 70, JSONB.Constants.BC_LOCAL_DATE, -99, 6, 108, -99, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -23, JSONB.Constants.BC_LOCAL_DATETIME, 107, JSONB.Constants.BC_INT32_NUM_MIN, 67, 107, -20}, new byte[]{-63, 39, -13, 59, 89, 17, 83, -94, 43, 51, 87, -7, 80, JSONB.Constants.BC_STR_ASCII_FIX_32, 30, -53}, new byte[]{-39, JSONB.Constants.BC_INT64_BYTE_ZERO, 14, 96, 83, 3, -19, -28, -100, 97, -38, 0, 117, 12, -18, 44}, new byte[]{80, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_ARRAY, 99, -68, -70, JSONB.Constants.BC_BIGINT, ByteCompanionObject.MIN_VALUE, JSONB.Constants.BC_TIMESTAMP_MILLIS, 12, -23, -106, -95, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_INT32_NUM_MIN}, new byte[]{57, -54, -115, JSONB.Constants.BC_REFERENCE, JSONB.Constants.BC_INT32_BYTE_MIN, -34, 13, JSONB.Constants.BC_TIMESTAMP_MILLIS, -120, 41, -106, 94, 2, JSONB.Constants.BC_TRUE, Base64.padSymbol, JSONB.Constants.BC_TIMESTAMP}, new byte[]{66, JSONB.Constants.BC_DOUBLE_LONG, 117, 46, JSONB.Constants.BC_LOCAL_DATETIME, -13, 20, -120, 11, JSONB.Constants.BC_ARRAY, 84, -43, JSONB.Constants.BC_INT32_BYTE_ZERO, -113, JSONB.Constants.BC_BIGINT, 23}, new byte[]{-10, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 10, 54, JSONB.Constants.BC_STR_ASCII, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_FLOAT_INT, JSONB.Constants.BC_TIMESTAMP, JSONB.Constants.BC_INT64_BYTE_MAX, Byte.MAX_VALUE, 66, 95, 91, -118, JSONB.Constants.BC_BIGINT, 52}, new byte[]{-34, JSONB.Constants.BC_NULL, -70, -1, 24, 89, -50, 67, JSONB.Constants.BC_INT32_BYTE_ZERO, 84, -27, -53, 65, 82, -10, 38}, new byte[]{JSONB.Constants.BC_STR_ASCII_FIX_MAX, -55, -98, -125, -9, -100, -54, -94, 106, 2, -13, JSONB.Constants.BC_DECIMAL, 84, -102, -23, 76}, new byte[]{53, 18, JSONB.Constants.BC_CHAR, 34, 40, 110, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_INT64, -9, -33, 27, 26, JSONB.Constants.BC_OBJECT_END, 81, JSONB.Constants.BC_TIMESTAMP}, new byte[]{-49, 89, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_INT32, 15, -68, 115, -63, 43, -46, JSONB.Constants.BC_STR_GB18030, -70, 60, 97, -63, -96}, new byte[]{-95, -99, -59, -23, -3, JSONB.Constants.BC_INT8, -42, JSONB.Constants.BC_STR_ASCII_FIX_1, -120, -126, 40, 2, 3, -52, 106, 117}};
    private static final byte[][] S = {new byte[]{99, JSONB.Constants.BC_STR_UTF16LE, 119, JSONB.Constants.BC_STR_UTF16, -14, 107, 111, -59, JSONB.Constants.BC_INT32_BYTE_MIN, 1, 103, 43, -2, JSONB.Constants.BC_INT64_BYTE_MAX, JSONB.Constants.BC_TIMESTAMP_MILLIS, 118}, new byte[]{-54, -126, -55, JSONB.Constants.BC_STR_UTF16BE, -6, 89, JSONB.Constants.BC_INT32_SHORT_MAX, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MINUTES, -44, -94, JSONB.Constants.BC_NULL, -100, JSONB.Constants.BC_ARRAY, 114, JSONB.Constants.BC_INT64_SHORT_MIN}, new byte[]{JSONB.Constants.BC_FLOAT, -3, JSONB.Constants.BC_REFERENCE, 38, 54, 63, -9, -52, 52, JSONB.Constants.BC_OBJECT_END, -27, -15, 113, JSONB.Constants.BC_INT64_NUM_MIN, 49, 21}, new byte[]{4, JSONB.Constants.BC_INT64_SHORT_MAX, 35, -61, 24, -106, 5, -102, 7, 18, ByteCompanionObject.MIN_VALUE, -30, -21, 39, JSONB.Constants.BC_DOUBLE_NUM_0, 117}, new byte[]{9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, JSONB.Constants.BC_DOUBLE_NUM_1, 41, -29, JSONB.Constants.BC_INT32_NUM_MAX, -124}, new byte[]{83, -47, 0, -19, 32, -4, JSONB.Constants.BC_TRUE, 91, 106, -53, JSONB.Constants.BC_INT64, 57, JSONB.Constants.BC_STR_ASCII_FIX_1, 76, 88, -49}, new byte[]{JSONB.Constants.BC_INT64_BYTE_ZERO, -17, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -5, 67, JSONB.Constants.BC_STR_ASCII_FIX_4, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, JSONB.Constants.BC_LOCAL_DATETIME}, new byte[]{81, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_INT32_SHORT_MIN, -113, JSONB.Constants.BC_TYPED_ANY, -99, JSONB.Constants.BC_INT32_BYTE_ZERO, -11, -68, JSONB.Constants.BC_FLOAT_INT, -38, 33, JSONB.Constants.BC_INT32_NUM_16, -1, -13, -46}, new byte[]{-51, 12, 19, -20, 95, -105, JSONB.Constants.BC_INT32_SHORT_ZERO, 23, JSONB.Constants.BC_INT64_SHORT_ZERO, JSONB.Constants.BC_LOCAL_TIME, JSONB.Constants.BC_STR_GB18030, Base64.padSymbol, 100, 93, 25, 115}, new byte[]{96, -127, 79, JL_Constant.PREFIX_FLAG_SECOND, 34, 42, JSONB.Constants.BC_CHAR, -120, 70, -18, JSONB.Constants.BC_DECIMAL_LONG, 20, -34, 94, 11, -37}, new byte[]{-32, 50, 58, 10, 73, 6, 36, 92, -62, -45, JSONB.Constants.BC_TIMESTAMP_SECONDS, 98, JSONB.Constants.BC_BINARY, -107, -28, JSONB.Constants.BC_STR_ASCII}, new byte[]{-25, JSONB.Constants.BC_INT64_BYTE_MIN, 55, JSONB.Constants.BC_STR_ASCII_FIX_36, -115, -43, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_LOCAL_DATE, 108, 86, -12, -22, 101, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_TIMESTAMP, 8}, new byte[]{-70, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 37, 46, 28, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_DOUBLE_LONG, -58, -24, -35, 116, 31, 75, JSONB.Constants.BC_INT8, -117, -118}, new byte[]{112, 62, JSONB.Constants.BC_DOUBLE, 102, JSONB.Constants.BC_INT32, 3, -10, 14, 97, 53, 87, JSONB.Constants.BC_DECIMAL, -122, -63, 29, -98}, new byte[]{-31, -8, -104, 17, JSONB.Constants.BC_STR_ASCII_FIX_32, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33}, new byte[]{-116, -95, -119, 13, JSONB.Constants.BC_INT64_INT, -26, 66, 104, 65, -103, 45, 15, JSONB.Constants.BC_FALSE, 84, JSONB.Constants.BC_BIGINT, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER}};

    static byte[] aesEnc(byte[] bArr, byte[] bArr2) {
        byte[] mixColumns = mixColumns(shiftRows(subBytes(bArr)));
        Bytes.xorTo(16, bArr2, mixColumns);
        return mixColumns;
    }

    private static byte[] mixColumns(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i2 * 4;
            int i4 = i3 + 1;
            int i5 = i3 + 2;
            int i6 = i3 + 3;
            bArr2[i] = (byte) ((((mulX(bArr[i3]) ^ mulX(bArr[i4])) ^ bArr[i4]) ^ bArr[i5]) ^ bArr[i6]);
            bArr2[i + 1] = (byte) ((((bArr[i3] ^ mulX(bArr[i4])) ^ mulX(bArr[i5])) ^ bArr[i5]) ^ bArr[i6]);
            int i7 = i + 3;
            bArr2[i + 2] = (byte) ((((bArr[i3] ^ bArr[i4]) ^ mulX(bArr[i5])) ^ mulX(bArr[i6])) ^ bArr[i6]);
            i += 4;
            bArr2[i7] = (byte) ((((mulX(bArr[i3]) ^ bArr[i3]) ^ bArr[i4]) ^ bArr[i5]) ^ mulX(bArr[i6]));
        }
        return bArr2;
    }

    static byte mulX(byte b) {
        return (byte) ((((b & ByteCompanionObject.MIN_VALUE) >> 7) * 27) ^ ((b & Byte.MAX_VALUE) << 1));
    }

    static byte sBox(byte b) {
        return S[(b & UByte.MAX_VALUE) >>> 4][b & 15];
    }

    static byte[] shiftRows(byte[] bArr) {
        return new byte[]{bArr[0], bArr[5], bArr[10], bArr[15], bArr[4], bArr[9], bArr[14], bArr[3], bArr[8], bArr[13], bArr[2], bArr[7], bArr[12], bArr[1], bArr[6], bArr[11]};
    }

    static byte[] subBytes(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        bArr2[0] = sBox(bArr[0]);
        bArr2[1] = sBox(bArr[1]);
        bArr2[2] = sBox(bArr[2]);
        bArr2[3] = sBox(bArr[3]);
        bArr2[4] = sBox(bArr[4]);
        bArr2[5] = sBox(bArr[5]);
        bArr2[6] = sBox(bArr[6]);
        bArr2[7] = sBox(bArr[7]);
        bArr2[8] = sBox(bArr[8]);
        bArr2[9] = sBox(bArr[9]);
        bArr2[10] = sBox(bArr[10]);
        bArr2[11] = sBox(bArr[11]);
        bArr2[12] = sBox(bArr[12]);
        bArr2[13] = sBox(bArr[13]);
        bArr2[14] = sBox(bArr[14]);
        bArr2[15] = sBox(bArr[15]);
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }
}
