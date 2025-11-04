package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class DSTU7624Engine implements BlockCipher {
    private static final int ROUNDS_128 = 10;
    private static final int ROUNDS_256 = 14;
    private static final int ROUNDS_512 = 18;
    private static final byte[] S0 = {JSONB.Constants.BC_LOCAL_DATETIME, 67, 95, 6, 107, 117, 108, 89, 113, -33, -121, -107, 23, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_INT64_NUM_MIN, 9, JSONB.Constants.BC_STR_ASCII_FIX_36, -13, 29, -53, -55, JSONB.Constants.BC_STR_ASCII_FIX_4, 44, JSONB.Constants.BC_NULL, JSONB.Constants.BC_STR_ASCII, -32, -105, -3, 111, 75, 69, 57, 62, -35, JSONB.Constants.BC_ARRAY_FIX_MAX, 79, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_FLOAT_INT, -102, 14, 31, JSONB.Constants.BC_INT64_INT, 21, -31, 73, -46, JSONB.Constants.BC_REFERENCE, -58, JSONB.Constants.BC_TYPED_ANY, 114, -98, 97, -47, 99, -6, -18, -12, 25, -43, JSONB.Constants.BC_TIMESTAMP_MINUTES, 88, JSONB.Constants.BC_ARRAY, JSONB.Constants.BC_BIGINT, -95, JL_Constant.PREFIX_FLAG_SECOND, -14, -125, 55, 66, -28, JSONB.Constants.BC_STR_UTF8, 50, -100, -52, JSONB.Constants.BC_TIMESTAMP_MILLIS, JSONB.Constants.BC_STR_ASCII_FIX_1, -113, 110, 4, 39, 46, -25, -30, 90, -106, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 35, 43, -62, 101, 102, 15, -68, JSONB.Constants.BC_LOCAL_DATE, JSONB.Constants.BC_INT32_SHORT_MAX, 65, 52, JSONB.Constants.BC_INT32, -4, JSONB.Constants.BC_FLOAT, 106, -120, JSONB.Constants.BC_OBJECT_END, 83, -122, -7, 91, -37, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_STR_UTF16, -61, 30, 34, 51, 36, 40, 54, JSONB.Constants.BC_INT64_SHORT_MAX, JSONB.Constants.BC_DOUBLE_NUM_0, 59, -114, 119, -70, -11, 20, -97, 8, 85, -101, 76, -2, 96, 92, -38, 24, 70, -51, JSONB.Constants.BC_STR_UTF16BE, 33, JSONB.Constants.BC_FALSE, 63, 27, -119, -1, -21, -124, JSONB.Constants.BC_STR_ASCII_FIX_32, 58, -99, JSONB.Constants.BC_INT64_BYTE_MAX, -45, 112, 103, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_DOUBLE, -34, 93, JSONB.Constants.BC_INT32_BYTE_MIN, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 17, 1, -27, 0, 104, -104, -96, -59, 2, JSONB.Constants.BC_OBJECT, 116, 45, 11, -94, 118, JSONB.Constants.BC_DOUBLE_NUM_1, JSONB.Constants.BC_INT64, -50, JSONB.Constants.BC_INT8, JSONB.Constants.BC_TIMESTAMP, -23, -118, 49, 28, -20, -15, -103, -108, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -10, 38, JSONB.Constants.BC_INT32_NUM_MAX, -17, -24, -116, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, 94, JSONB.Constants.BC_CHAR, 32, Base64.padSymbol, -126, -9, -22, 10, 13, JSONB.Constants.BC_STR_GB18030, -8, 80, 26, JSONB.Constants.BC_INT64_SHORT_ZERO, 7, 87, JSONB.Constants.BC_DECIMAL_LONG, 60, 98, -29, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_TIMESTAMP_SECONDS, 82, 100, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_INT64_BYTE_ZERO, -39, 19, 12, 18, 41, 81, JSONB.Constants.BC_DECIMAL, -49, -42, 115, -115, -127, 84, JSONB.Constants.BC_INT64_SHORT_MIN, -19, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_INT32_SHORT_ZERO, JSONB.Constants.BC_LOCAL_TIME, 42, -123, 37, -26, -54, JSONB.Constants.BC_STR_UTF16LE, -117, 86, ByteCompanionObject.MIN_VALUE};
    private static final byte[] S1 = {-50, JSONB.Constants.BC_BIGINT, -21, JSONB.Constants.BC_TYPED_ANY, -22, -53, 19, -63, -23, 58, -42, JSONB.Constants.BC_DOUBLE_NUM_0, -46, JSONB.Constants.BC_CHAR, 23, -8, 66, 21, 86, JSONB.Constants.BC_DOUBLE_LONG, 101, 28, -120, 67, -59, 92, 54, -70, -11, 87, 103, -115, 49, -10, 100, 88, -98, -12, 34, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 117, 15, 2, JSONB.Constants.BC_TRUE, -33, JSONB.Constants.BC_STR_ASCII_FIX_36, 115, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_STR_UTF16LE, 38, 46, -9, 8, 93, JSONB.Constants.BC_INT32_SHORT_ZERO, 62, -97, 20, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_TIMESTAMP, 84, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_INT64_NUM_MIN, -68, 26, 107, JSONB.Constants.BC_STR_ASCII_FIX_32, -13, JSONB.Constants.BC_INT8, 51, JSONB.Constants.BC_TIMESTAMP_MILLIS, -6, -47, -101, 104, JSONB.Constants.BC_STR_ASCII_FIX_5, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -107, JSONB.Constants.BC_BINARY, -18, 76, 99, -114, 91, -52, 60, 25, -95, -127, 73, JSONB.Constants.BC_STR_UTF16, -39, 111, 55, 96, -54, -25, 43, JSONB.Constants.BC_INT32, -3, -106, 69, -4, 65, 18, 13, JSONB.Constants.BC_STR_ASCII, -27, -119, -116, -29, 32, JSONB.Constants.BC_INT32_BYTE_MIN, JL_Constant.PREFIX_FLAG_SECOND, JSONB.Constants.BC_FLOAT, 108, JSONB.Constants.BC_STR_ASCII_FIX_1, JSONB.Constants.BC_DOUBLE, 63, -105, -44, 98, 45, 6, JSONB.Constants.BC_ARRAY, JSONB.Constants.BC_OBJECT_END, -125, 95, 42, -38, -55, 0, JSONB.Constants.BC_STR_GB18030, -94, 85, JSONB.Constants.BC_INT64_INT, 17, -43, -100, -49, 14, 10, Base64.padSymbol, 81, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_REFERENCE, 27, -2, JSONB.Constants.BC_INT64_SHORT_ZERO, JSONB.Constants.BC_INT32_SHORT_MAX, 9, -122, 11, -113, -99, 106, 7, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_FALSE, -104, 24, 50, 113, 75, -17, 59, 112, -96, -28, JSONB.Constants.BC_INT32_SHORT_MIN, -1, -61, JSONB.Constants.BC_LOCAL_DATE, -26, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -7, -117, 70, ByteCompanionObject.MIN_VALUE, 30, JSONB.Constants.BC_INT32_BYTE_ZERO, -31, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_LOCAL_DATETIME, -32, 12, 35, 118, 29, 37, 36, 5, -15, 110, -108, 40, -102, -124, -24, JSONB.Constants.BC_ARRAY_FIX_MAX, 79, 119, -45, -123, -30, 82, -14, -126, 80, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_INT32_NUM_MAX, 116, 83, JSONB.Constants.BC_DOUBLE_NUM_1, 97, JSONB.Constants.BC_NULL, 57, 53, -34, -51, 31, -103, JSONB.Constants.BC_TIMESTAMP_SECONDS, JSONB.Constants.BC_TIMESTAMP_MINUTES, 114, 44, -35, JSONB.Constants.BC_INT64_BYTE_ZERO, -121, JSONB.Constants.BC_INT64, 94, JSONB.Constants.BC_OBJECT, -20, 4, -58, 3, 52, -5, -37, 89, JSONB.Constants.BC_FLOAT_INT, -62, 1, JSONB.Constants.BC_INT32_NUM_MIN, 90, -19, JSONB.Constants.BC_LOCAL_TIME, 102, 33, Byte.MAX_VALUE, -118, 39, JSONB.Constants.BC_INT64_SHORT_MAX, JSONB.Constants.BC_INT64_SHORT_MIN, 41, JSONB.Constants.BC_INT64_BYTE_MAX};
    private static final byte[] S2 = {JSONB.Constants.BC_REFERENCE, -39, -102, JSONB.Constants.BC_DOUBLE, -104, 34, 69, -4, -70, 106, -33, 2, -97, JL_Constant.PREFIX_FLAG_SECOND, 81, 89, JSONB.Constants.BC_STR_ASCII_FIX_1, 23, 43, -62, -108, -12, JSONB.Constants.BC_BIGINT, JSONB.Constants.BC_ARRAY_FIX_MAX, 98, -28, 113, -44, -51, 112, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -31, 73, 60, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_INT64_NUM_MIN, 92, -101, JSONB.Constants.BC_TIMESTAMP_MINUTES, -123, 83, -95, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_INT64_BYTE_MIN, 45, -32, -47, 114, JSONB.Constants.BC_OBJECT, 44, JSONB.Constants.BC_INT64_SHORT_ZERO, -29, 118, JSONB.Constants.BC_STR_ASCII_FIX_MAX, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_DOUBLE_LONG, 9, 59, 14, 65, 76, -34, JSONB.Constants.BC_DOUBLE_NUM_0, JSONB.Constants.BC_CHAR, 37, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_INT64_BYTE_MAX, 3, 17, 0, -61, 46, JSONB.Constants.BC_TYPED_ANY, -17, JSONB.Constants.BC_STR_ASCII_FIX_5, 18, -99, JSONB.Constants.BC_STR_UTF16BE, -53, 53, JSONB.Constants.BC_INT32_NUM_16, -43, 79, -98, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_LOCAL_DATE, 85, -58, JSONB.Constants.BC_INT64_BYTE_ZERO, JSONB.Constants.BC_STR_UTF16, 24, -105, -45, 54, -26, JSONB.Constants.BC_INT32, 86, -127, -113, 119, -52, -100, JSONB.Constants.BC_DECIMAL, -30, JSONB.Constants.BC_TIMESTAMP_SECONDS, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_INT32_NUM_MAX, 21, JSONB.Constants.BC_ARRAY, JSONB.Constants.BC_STR_UTF16LE, -38, JSONB.Constants.BC_INT32_BYTE_ZERO, 30, 11, 5, -42, 20, 110, 108, JSONB.Constants.BC_STR_GB18030, 102, -3, JSONB.Constants.BC_TRUE, -27, 96, JSONB.Constants.BC_NULL, 94, 51, -121, -55, JSONB.Constants.BC_INT32_NUM_MIN, 93, JSONB.Constants.BC_STR_ASCII_FIX_36, 63, -120, -115, JSONB.Constants.BC_INT64_SHORT_MAX, -9, 29, -23, -20, -19, ByteCompanionObject.MIN_VALUE, 41, 39, -49, -103, JSONB.Constants.BC_LOCAL_DATETIME, 80, 15, 55, 36, 40, JSONB.Constants.BC_INT32_BYTE_MIN, -107, -46, 62, 91, JSONB.Constants.BC_INT32_SHORT_MIN, -125, JSONB.Constants.BC_DOUBLE_NUM_1, JSONB.Constants.BC_STR_ASCII_FIX_32, 87, 31, 7, 28, -118, -68, 32, -21, -50, -114, JSONB.Constants.BC_TIMESTAMP_MILLIS, -18, 49, -94, 115, -7, -54, 58, 26, -5, 13, -63, -2, -6, -14, 111, JSONB.Constants.BC_INT8, -106, -35, 67, 82, JSONB.Constants.BC_FLOAT_INT, 8, -13, JSONB.Constants.BC_TIMESTAMP, JSONB.Constants.BC_INT64, 25, -119, 50, 38, JSONB.Constants.BC_FALSE, -22, 75, 100, -124, -126, 107, -11, JSONB.Constants.BC_STR_ASCII, JSONB.Constants.BC_INT64_INT, 1, 95, 117, 99, 27, 35, Base64.padSymbol, 104, 42, 101, -24, JSONB.Constants.BC_BINARY, -10, -1, 19, 88, -15, JSONB.Constants.BC_INT32_SHORT_MAX, 10, Byte.MAX_VALUE, -59, JSONB.Constants.BC_LOCAL_TIME, -25, 97, 90, 6, 70, JSONB.Constants.BC_INT32_SHORT_ZERO, 66, 4, -96, -37, 57, -122, 84, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -116, 52, 33, -117, -8, 12, 116, 103};
    private static final byte[] S3 = {104, -115, -54, JSONB.Constants.BC_STR_ASCII_FIX_4, 115, 75, JSONB.Constants.BC_STR_ASCII_FIX_5, 42, -44, 82, 38, JSONB.Constants.BC_DOUBLE_NUM_1, 84, 30, 25, 31, 34, 3, 70, Base64.padSymbol, 45, JSONB.Constants.BC_STR_ASCII_FIX_1, 83, -125, 19, -118, JSONB.Constants.BC_FLOAT, -43, 37, JSONB.Constants.BC_STR_ASCII, -11, JSONB.Constants.BC_INT8, 88, JSONB.Constants.BC_INT32_NUM_MAX, 13, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 60, 102, 112, 93, -13, 69, JSONB.Constants.BC_INT32_SHORT_MIN, -52, -24, -108, 86, 8, -50, 26, 58, -46, -31, -33, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_INT32_BYTE_ZERO, 110, 14, -27, -12, -7, -122, -23, 79, -42, -123, 35, -49, 50, -103, 49, 20, JSONB.Constants.BC_TIMESTAMP, -18, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_INT32, -45, JSONB.Constants.BC_INT32_BYTE_MIN, -95, JSONB.Constants.BC_TYPED_ANY, 65, JSONB.Constants.BC_TRUE, 24, JSONB.Constants.BC_INT64_SHORT_ZERO, 44, 113, 114, JSONB.Constants.BC_INT32_SHORT_ZERO, 21, -3, 55, JSONB.Constants.BC_INT64, 95, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -101, -120, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MILLIS, -119, -100, -6, 96, -22, -68, 98, 12, 36, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_LOCAL_DATETIME, -20, 103, 32, -37, JSONB.Constants.BC_STR_UTF16LE, 40, -35, JSONB.Constants.BC_TIMESTAMP_SECONDS, 91, 52, JSONB.Constants.BC_STR_GB18030, JSONB.Constants.BC_INT32_NUM_16, -15, JSONB.Constants.BC_STR_UTF16, -113, 99, -96, 5, -102, 67, 119, 33, JSONB.Constants.BC_INT64_INT, 39, 9, -61, -97, JSONB.Constants.BC_FLOAT_INT, JSONB.Constants.BC_INT64_BYTE_MAX, 41, -62, -21, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_ARRAY, -117, -116, 29, -5, -1, -63, JSONB.Constants.BC_DOUBLE_NUM_0, -105, 46, -8, 101, -10, 117, 7, 4, 73, 51, -28, -39, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_INT64_BYTE_ZERO, 66, JSONB.Constants.BC_INT64_SHORT_MAX, 108, JSONB.Constants.BC_CHAR, 0, -114, 111, 80, 1, -59, -38, JSONB.Constants.BC_INT32_SHORT_MAX, 63, -51, JSONB.Constants.BC_STR_ASCII_FIX_32, -94, -30, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_LOCAL_TIME, -58, JSONB.Constants.BC_REFERENCE, 15, 10, 6, -26, 43, -106, JSONB.Constants.BC_ARRAY_FIX_MAX, 28, JSONB.Constants.BC_NULL, 106, 18, -124, 57, -25, JSONB.Constants.BC_FALSE, -126, -9, -2, -99, -121, 92, -127, 53, -34, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_OBJECT_END, -4, ByteCompanionObject.MIN_VALUE, -17, -53, JSONB.Constants.BC_BIGINT, 107, 118, -70, 90, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 11, -107, -29, JSONB.Constants.BC_TIMESTAMP_MINUTES, 116, -104, 59, 54, 100, JSONB.Constants.BC_STR_ASCII_FIX_36, JL_Constant.PREFIX_FLAG_SECOND, JSONB.Constants.BC_INT32_NUM_MIN, 89, JSONB.Constants.BC_LOCAL_DATE, 76, 23, Byte.MAX_VALUE, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_DECIMAL_LONG, -55, 87, 27, -32, 97};
    private static final byte[] T0 = {JSONB.Constants.BC_ARRAY, -94, JSONB.Constants.BC_LOCAL_DATE, -59, JSONB.Constants.BC_STR_ASCII_FIX_5, -55, 3, -39, JSONB.Constants.BC_STR_GB18030, 15, -46, JSONB.Constants.BC_TIMESTAMP_MINUTES, -25, -45, 39, 91, -29, -95, -24, -26, JSONB.Constants.BC_STR_UTF16LE, 42, 85, 12, -122, 57, JSONB.Constants.BC_INT64_BYTE_MAX, -115, JSONB.Constants.BC_DECIMAL_LONG, 18, 111, 40, -51, -118, 112, 86, 114, -7, JSONB.Constants.BC_INT64_INT, 79, 115, -23, -9, 87, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, JSONB.Constants.BC_TIMESTAMP_SECONDS, 80, JSONB.Constants.BC_INT64_SHORT_MIN, -99, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_INT32_SHORT_MAX, 113, 96, JSONB.Constants.BC_INT64_SHORT_ZERO, 116, 67, 108, 31, JSONB.Constants.BC_REFERENCE, 119, JL_Constant.PREFIX_FLAG_SECOND, -50, 32, -116, -103, 95, JSONB.Constants.BC_INT32_SHORT_ZERO, 1, -11, 30, -121, 94, 97, 44, 75, 29, -127, 21, -12, 35, -42, -22, -31, 103, -15, Byte.MAX_VALUE, -2, -38, 60, 7, 83, 106, -124, -100, -53, 2, -125, 51, -35, 53, -30, 89, 90, -104, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_TYPED_ANY, 100, 4, 6, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_STR_ASCII_FIX_4, 28, -105, 8, 49, -18, JSONB.Constants.BC_TIMESTAMP_MILLIS, 5, JSONB.Constants.BC_NULL, JSONB.Constants.BC_STR_ASCII, -96, 24, 70, JSONB.Constants.BC_STR_ASCII_FIX_36, -4, -119, -44, JSONB.Constants.BC_INT64_SHORT_MAX, -1, JSONB.Constants.BC_INT32_NUM_MIN, -49, 66, JSONB.Constants.BC_BINARY, -8, 104, 10, 101, -114, JSONB.Constants.BC_FLOAT_INT, -3, -61, -17, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 76, -52, -98, JSONB.Constants.BC_INT32_BYTE_MIN, 46, -68, 11, 84, 26, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_BIGINT, 38, ByteCompanionObject.MIN_VALUE, JSONB.Constants.BC_INT32, -108, 50, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_LOCAL_TIME, 63, JSONB.Constants.BC_TIMESTAMP, 34, Base64.padSymbol, 102, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -10, 0, 93, JSONB.Constants.BC_INT8, JSONB.Constants.BC_STR_ASCII_FIX_1, -32, 59, JSONB.Constants.BC_DOUBLE_LONG, 23, -117, -97, 118, JSONB.Constants.BC_FALSE, 36, -102, 37, 99, -37, -21, JSONB.Constants.BC_STR_UTF8, 62, 92, JSONB.Constants.BC_DOUBLE_NUM_1, JSONB.Constants.BC_TRUE, 41, -14, -54, 88, 110, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_LOCAL_DATETIME, JSONB.Constants.BC_INT32_NUM_MAX, 117, -33, 20, -5, 19, 73, -120, JSONB.Constants.BC_DOUBLE_NUM_0, -20, -28, 52, 45, -106, -58, 58, -19, -107, 14, -27, -123, 107, JSONB.Constants.BC_INT32_SHORT_MIN, 33, -101, 9, 25, 43, 82, -34, 69, JSONB.Constants.BC_ARRAY_FIX_MAX, -6, 81, -62, JSONB.Constants.BC_DOUBLE, -47, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_DECIMAL, -13, 55, -63, 13, -70, 65, 17, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_STR_UTF16, JSONB.Constants.BC_INT64, JSONB.Constants.BC_INT64_BYTE_ZERO, -43, JSONB.Constants.BC_STR_ASCII_FIX_32, 54, JSONB.Constants.BC_INT64_BYTE_MIN, 98, 27, -126, -113};
    private static final byte[] T1 = {-125, -14, 42, -21, -23, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_STR_UTF16, -100, 52, -106, -115, -104, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_STR_ASCII_FIX_32, -116, 41, Base64.padSymbol, -120, 104, 6, 57, 17, 76, 14, -96, 86, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_TYPED_ANY, 21, -68, JSONB.Constants.BC_DOUBLE_NUM_1, JL_Constant.PREFIX_FLAG_SECOND, 111, -8, 38, -70, JSONB.Constants.BC_INT64, JSONB.Constants.BC_INT8, 49, -5, -61, -2, ByteCompanionObject.MIN_VALUE, 97, -31, JSONB.Constants.BC_STR_UTF8, 50, -46, 112, 32, -95, 69, -20, -39, 26, 93, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_INT64_NUM_MIN, 9, JSONB.Constants.BC_OBJECT_END, 85, -114, 55, 118, JSONB.Constants.BC_LOCAL_DATE, 103, JSONB.Constants.BC_INT32_NUM_16, 23, 54, 101, JSONB.Constants.BC_TRUE, -107, 98, 89, 116, JSONB.Constants.BC_ARRAY_FIX_MAX, 80, JSONB.Constants.BC_INT32_NUM_MAX, 75, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_INT64_BYTE_ZERO, -113, -51, -44, 60, -122, 18, 29, 35, -17, -12, 83, 25, 53, -26, Byte.MAX_VALUE, 94, -42, JSONB.Constants.BC_STR_ASCII, 81, 34, 20, -9, 30, JSONB.Constants.BC_STR_ASCII_FIX_1, 66, -101, 65, 115, 45, -63, 92, JSONB.Constants.BC_OBJECT, -94, -32, 46, -45, 40, JSONB.Constants.BC_BIGINT, -55, JSONB.Constants.BC_TIMESTAMP, 106, -47, 90, JSONB.Constants.BC_INT32_BYTE_MIN, JSONB.Constants.BC_CHAR, -124, -7, JSONB.Constants.BC_DOUBLE_NUM_0, 88, -49, JSONB.Constants.BC_STR_GB18030, -59, -53, -105, -28, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 108, -6, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_STR_ASCII_FIX_36, 31, 82, -103, 13, JSONB.Constants.BC_STR_ASCII_FIX_5, 3, JSONB.Constants.BC_BINARY, -62, JSONB.Constants.BC_STR_ASCII_FIX_4, 100, 119, -97, -35, JSONB.Constants.BC_INT64_SHORT_ZERO, 73, -118, -102, 36, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_LOCAL_TIME, 87, -123, JSONB.Constants.BC_INT64_SHORT_MAX, JSONB.Constants.BC_STR_UTF16LE, JSONB.Constants.BC_STR_UTF16BE, -25, -10, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_TIMESTAMP_SECONDS, 39, 70, -34, -33, 59, JSONB.Constants.BC_INT64_BYTE_MAX, -98, 43, 11, -43, 19, 117, JSONB.Constants.BC_INT32_NUM_MIN, 114, JSONB.Constants.BC_FLOAT_INT, -99, 27, 1, 63, JSONB.Constants.BC_INT32_SHORT_ZERO, -27, -121, -3, 7, -15, JSONB.Constants.BC_TIMESTAMP_MILLIS, -108, 24, -22, -4, 58, -126, 95, 5, 84, -37, 0, -117, -29, JSONB.Constants.BC_INT32, 12, -54, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -119, 10, -1, 62, 91, -127, -18, 113, -30, -38, 44, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_DOUBLE, -52, 110, JSONB.Constants.BC_LOCAL_DATETIME, 107, JSONB.Constants.BC_TIMESTAMP_MINUTES, 96, -58, 8, 4, 2, -24, -11, 79, JSONB.Constants.BC_ARRAY, -13, JSONB.Constants.BC_INT64_SHORT_MIN, -50, 67, 37, 28, 33, 51, 15, JSONB.Constants.BC_NULL, JSONB.Constants.BC_INT32_SHORT_MAX, -19, 102, 99, JSONB.Constants.BC_REFERENCE, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE};
    private static final byte[] T2 = {69, -44, 11, 67, -15, 114, -19, JSONB.Constants.BC_ARRAY, -62, JSONB.Constants.BC_INT32_BYTE_ZERO, -26, 113, -3, JSONB.Constants.BC_FLOAT_INT, 58, -107, 80, JSONB.Constants.BC_INT32_SHORT_ZERO, 75, -30, 116, 107, 30, 17, 90, -58, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_OBJECT_END, -118, 112, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_LOCAL_DATETIME, -6, 5, -39, -105, JSONB.Constants.BC_INT32_SHORT_MIN, -55, JSONB.Constants.BC_CHAR, -104, -113, JL_Constant.PREFIX_FLAG_SECOND, 18, 49, 44, JSONB.Constants.BC_INT32_SHORT_MAX, 106, -103, JSONB.Constants.BC_TIMESTAMP, JSONB.Constants.BC_INT64_BYTE_MIN, Byte.MAX_VALUE, -7, 79, 93, -106, 111, -12, JSONB.Constants.BC_DOUBLE_NUM_1, 57, 33, -38, -100, -123, -98, 59, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_INT64_INT, -17, 6, -18, -27, 95, 32, JSONB.Constants.BC_INT32_NUM_16, -52, 60, 84, JSONB.Constants.BC_STR_ASCII_FIX_1, 82, -108, 14, JSONB.Constants.BC_INT64_SHORT_MIN, 40, -10, 86, 96, -94, -29, 15, -20, -99, 36, -125, JSONB.Constants.BC_STR_GB18030, -43, JSONB.Constants.BC_STR_UTF16LE, -21, 24, JSONB.Constants.BC_INT64_BYTE_MAX, -51, -35, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -1, -37, -95, 9, JSONB.Constants.BC_INT64_BYTE_ZERO, 118, -124, 117, JSONB.Constants.BC_BIGINT, 29, 26, JSONB.Constants.BC_INT32_NUM_MAX, JSONB.Constants.BC_FALSE, -2, -42, 52, 99, 53, -46, 42, 89, JSONB.Constants.BC_STR_ASCII_FIX_36, JSONB.Constants.BC_STR_ASCII_FIX_4, 119, -25, -114, 97, -49, -97, -50, 39, -11, ByteCompanionObject.MIN_VALUE, -122, JSONB.Constants.BC_INT64_SHORT_MAX, JSONB.Constants.BC_OBJECT, -5, -8, -121, JSONB.Constants.BC_TIMESTAMP_MILLIS, 98, 63, -33, JSONB.Constants.BC_INT32, 0, 20, -102, JSONB.Constants.BC_INT8, 91, 4, JSONB.Constants.BC_TYPED_ANY, 2, 37, 101, 76, 83, 12, -14, 41, JSONB.Constants.BC_NULL, 23, 108, 65, JSONB.Constants.BC_INT32_BYTE_MIN, -23, JSONB.Constants.BC_REFERENCE, 85, -9, JSONB.Constants.BC_TIMESTAMP_SECONDS, 104, 38, JSONB.Constants.BC_INT64_SHORT_ZERO, JSONB.Constants.BC_STR_UTF16BE, -54, JSONB.Constants.BC_STR_UTF8, 62, -96, 55, 3, -63, 54, JSONB.Constants.BC_STR_ASCII_FIX_32, 102, 8, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, JSONB.Constants.BC_LOCAL_TIME, -68, -59, -45, 34, JSONB.Constants.BC_FLOAT, 19, 70, 50, -24, 87, -120, 43, -127, JSONB.Constants.BC_DOUBLE_NUM_0, JSONB.Constants.BC_STR_ASCII_FIX_5, 100, 28, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, JSONB.Constants.BC_BINARY, 88, 46, -101, 92, 27, 81, 115, 66, 35, 1, 110, -13, 13, JSONB.Constants.BC_INT64, Base64.padSymbol, 10, 45, 31, 103, 51, 25, JSONB.Constants.BC_STR_UTF16, 94, -22, -34, -117, -53, JSONB.Constants.BC_LOCAL_DATE, -116, -115, JSONB.Constants.BC_TIMESTAMP_MINUTES, 73, -126, -28, -70, -61, 21, -47, -32, -119, -4, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_DOUBLE, 7, JSONB.Constants.BC_STR_ASCII, JSONB.Constants.BC_DECIMAL_LONG, -31};
    private static final byte[] T3 = {JSONB.Constants.BC_DOUBLE_NUM_0, JSONB.Constants.BC_FLOAT_INT, 35, 17, JSONB.Constants.BC_LOCAL_TIME, -120, -59, JSONB.Constants.BC_OBJECT, 57, -113, JSONB.Constants.BC_INT64_SHORT_ZERO, -24, 115, 34, 67, -61, -126, 39, -51, 24, 81, 98, 45, -9, 92, 14, 59, -3, -54, -101, 13, 15, JSONB.Constants.BC_STR_ASCII, -116, JSONB.Constants.BC_INT32_NUM_16, 76, 116, 28, 10, -114, JSONB.Constants.BC_STR_UTF16LE, -108, 7, JSONB.Constants.BC_INT64_SHORT_MAX, 94, 20, -95, 33, 87, 80, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_LOCAL_DATE, ByteCompanionObject.MIN_VALUE, -39, -17, 100, 65, -49, 60, -18, 46, 19, 41, -70, 52, 90, JSONB.Constants.BC_TIMESTAMP, -118, 97, 51, 18, JSONB.Constants.BC_DECIMAL, 85, JSONB.Constants.BC_LOCAL_DATETIME, 21, 5, -10, 3, 6, 73, JSONB.Constants.BC_DOUBLE, 37, 9, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 12, 42, JSONB.Constants.BC_INT32_BYTE_ZERO, -4, 32, -12, -27, Byte.MAX_VALUE, JSONB.Constants.BC_INT64_BYTE_MAX, 49, 43, 102, 111, -1, 114, -122, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_INT32_NUM_MAX, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 0, -68, -52, -30, JSONB.Constants.BC_FALSE, -15, 66, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_INT32_BYTE_MIN, 95, 96, 4, -20, JSONB.Constants.BC_OBJECT_END, -29, -117, -25, 29, JSONB.Constants.BC_INT64_INT, -124, JSONB.Constants.BC_STR_UTF16, -26, -127, -8, -34, JSONB.Constants.BC_INT64_NUM_MIN, -46, 23, -50, 75, JSONB.Constants.BC_INT32_SHORT_MAX, -42, JSONB.Constants.BC_STR_ASCII_FIX_32, 108, 25, -103, -102, 1, JSONB.Constants.BC_DOUBLE_NUM_1, -123, JSONB.Constants.BC_TRUE, -7, 89, -62, 55, -23, JSONB.Constants.BC_INT64_BYTE_MIN, -96, -19, 79, -119, 104, JSONB.Constants.BC_STR_ASCII_FIX_36, -43, 38, JSONB.Constants.BC_BINARY, -121, 88, JSONB.Constants.BC_INT8, -55, -104, JL_Constant.PREFIX_FLAG_SECOND, 117, JSONB.Constants.BC_INT64_SHORT_MIN, 118, -11, 103, 107, JSONB.Constants.BC_STR_GB18030, -21, 82, -53, -47, 91, -97, 11, -37, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_TYPED_ANY, 26, -6, JSONB.Constants.BC_TIMESTAMP_SECONDS, -28, -31, 113, 31, 101, -115, -105, -98, -107, JSONB.Constants.BC_CHAR, 93, JSONB.Constants.BC_FLOAT, -63, JSONB.Constants.BC_NULL, 84, -5, 2, -32, 53, JSONB.Constants.BC_BIGINT, 58, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_TIMESTAMP_MINUTES, 44, Base64.padSymbol, 86, 8, 27, JSONB.Constants.BC_STR_ASCII_FIX_1, JSONB.Constants.BC_REFERENCE, 106, JSONB.Constants.BC_TIMESTAMP_MILLIS, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_STR_UTF8, -14, JSONB.Constants.BC_STR_UTF16BE, -38, 63, -2, 62, JSONB.Constants.BC_INT64, -22, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, JSONB.Constants.BC_INT32_SHORT_ZERO, -58, JSONB.Constants.BC_INT64_BYTE_ZERO, 54, JSONB.Constants.BC_INT32, 112, -106, 119, 36, 83, -33, -13, -125, 40, 50, 69, 30, JSONB.Constants.BC_ARRAY, -45, -94, 70, 110, -100, -35, 99, -44, -99};
    private boolean forEncryption;
    private long[] internalState;
    private long[][] roundKeys;
    private int roundsAmount;
    private int wordsInBlock;
    private int wordsInKey;
    private long[] workingKey;

    public DSTU7624Engine(int i) throws IllegalArgumentException {
        if (i != 128 && i != 256 && i != 512) {
            throw new IllegalArgumentException("unsupported block length: only 128/256/512 are allowed");
        }
        int i2 = i >>> 6;
        this.wordsInBlock = i2;
        this.internalState = new long[i2];
    }

    private void addRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] + jArr[i2];
        }
    }

    private void decryptBlock_128(byte[] bArr, int i, byte[] bArr2, int i2) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, i);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, i + 8);
        long[][] jArr = this.roundKeys;
        int i3 = this.roundsAmount;
        long[] jArr2 = jArr[i3];
        boolean z = false;
        long j = littleEndianToLong - jArr2[0];
        boolean z2 = true;
        long j2 = littleEndianToLong2 - jArr2[1];
        while (true) {
            long mixColumnInv = mixColumnInv(j);
            long mixColumnInv2 = mixColumnInv(j2);
            int i4 = (int) mixColumnInv;
            int i5 = (int) (mixColumnInv >>> 32);
            int i6 = (int) mixColumnInv2;
            int i7 = (int) (mixColumnInv2 >>> 32);
            byte[] bArr3 = T0;
            byte b = bArr3[i4 & 255];
            byte[] bArr4 = T1;
            byte b2 = bArr4[(i4 >>> 8) & 255];
            byte[] bArr5 = T2;
            boolean z3 = z;
            byte b3 = bArr5[(i4 >>> 16) & 255];
            byte[] bArr6 = T3;
            int i8 = (bArr6[i4 >>> 24] << 24) | ((b3 & UByte.MAX_VALUE) << 16) | (b & UByte.MAX_VALUE) | ((b2 & UByte.MAX_VALUE) << 8);
            byte b4 = bArr3[i7 & 255];
            byte b5 = bArr4[(i7 >>> 8) & 255];
            byte b6 = bArr5[(i7 >>> 16) & 255];
            boolean z4 = z2;
            long j3 = (i8 & 4294967295L) | (((bArr6[i7 >>> 24] << 24) | (((b4 & UByte.MAX_VALUE) | ((b5 & UByte.MAX_VALUE) << 8)) | ((b6 & UByte.MAX_VALUE) << 16))) << 32);
            int i9 = (bArr6[i6 >>> 24] << 24) | (bArr3[i6 & 255] & UByte.MAX_VALUE) | ((bArr4[(i6 >>> 8) & 255] & UByte.MAX_VALUE) << 8) | ((bArr5[(i6 >>> 16) & 255] & UByte.MAX_VALUE) << 16);
            byte b7 = bArr3[i5 & 255];
            byte b8 = bArr4[(i5 >>> 8) & 255];
            byte b9 = bArr5[(i5 >>> 16) & 255];
            long j4 = (i9 & 4294967295L) | (((bArr6[i5 >>> 24] << 24) | (((b7 & UByte.MAX_VALUE) | ((b8 & UByte.MAX_VALUE) << 8)) | ((b9 & UByte.MAX_VALUE) << 16))) << 32);
            i3--;
            long[][] jArr3 = this.roundKeys;
            if (i3 == 0) {
                long[] jArr4 = jArr3[z3 ? 1 : 0];
                long j5 = j3 - jArr4[z3 ? 1 : 0];
                long j6 = j4 - jArr4[z4 ? 1 : 0];
                Pack.longToLittleEndian(j5, bArr2, i2);
                Pack.longToLittleEndian(j6, bArr2, i2 + 8);
                return;
            }
            long[] jArr5 = jArr3[i3];
            long j7 = jArr5[z3 ? 1 : 0] ^ j3;
            j2 = j4 ^ jArr5[z4 ? 1 : 0];
            j = j7;
            z = z3 ? 1 : 0;
            z2 = z4 ? 1 : 0;
        }
    }

    private void encryptBlock_128(byte[] bArr, int i, byte[] bArr2, int i2) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, i);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, i + 8);
        boolean z = false;
        long[] jArr = this.roundKeys[0];
        long j = littleEndianToLong + jArr[0];
        boolean z2 = true;
        long j2 = littleEndianToLong2 + jArr[1];
        int i3 = 0;
        while (true) {
            int i4 = (int) j;
            int i5 = (int) (j >>> 32);
            int i6 = (int) j2;
            int i7 = (int) (j2 >>> 32);
            byte[] bArr3 = S0;
            byte b = bArr3[i4 & 255];
            byte[] bArr4 = S1;
            byte b2 = bArr4[(i4 >>> 8) & 255];
            byte[] bArr5 = S2;
            boolean z3 = z;
            byte b3 = bArr5[(i4 >>> 16) & 255];
            byte[] bArr6 = S3;
            int i8 = ((b3 & UByte.MAX_VALUE) << 16) | (b & UByte.MAX_VALUE) | ((b2 & UByte.MAX_VALUE) << 8) | (bArr6[i4 >>> 24] << 24);
            byte b4 = bArr3[i7 & 255];
            byte b5 = bArr4[(i7 >>> 8) & 255];
            byte b6 = bArr5[(i7 >>> 16) & 255];
            boolean z4 = z2;
            long j3 = (i8 & 4294967295L) | (((bArr6[i7 >>> 24] << 24) | (((b4 & UByte.MAX_VALUE) | ((b5 & UByte.MAX_VALUE) << 8)) | ((b6 & UByte.MAX_VALUE) << 16))) << 32);
            int i9 = (bArr6[i6 >>> 24] << 24) | (bArr3[i6 & 255] & UByte.MAX_VALUE) | ((bArr4[(i6 >>> 8) & 255] & UByte.MAX_VALUE) << 8) | ((bArr5[(i6 >>> 16) & 255] & UByte.MAX_VALUE) << 16);
            byte b7 = bArr3[i5 & 255];
            byte b8 = bArr4[(i5 >>> 8) & 255];
            byte b9 = bArr5[(i5 >>> 16) & 255];
            long j4 = (i9 & 4294967295L) | (((bArr6[i5 >>> 24] << 24) | (((b7 & UByte.MAX_VALUE) | ((b8 & UByte.MAX_VALUE) << 8)) | ((b9 & UByte.MAX_VALUE) << 16))) << 32);
            long mixColumn = mixColumn(j3);
            long mixColumn2 = mixColumn(j4);
            i3++;
            int i10 = this.roundsAmount;
            if (i3 == i10) {
                long[] jArr2 = this.roundKeys[i10];
                long j5 = mixColumn + jArr2[z3 ? 1 : 0];
                long j6 = mixColumn2 + jArr2[z4 ? 1 : 0];
                Pack.longToLittleEndian(j5, bArr2, i2);
                Pack.longToLittleEndian(j6, bArr2, i2 + 8);
                return;
            }
            long[] jArr3 = this.roundKeys[i3];
            long j7 = mixColumn ^ jArr3[z3 ? 1 : 0];
            j2 = mixColumn2 ^ jArr3[z4 ? 1 : 0];
            j = j7;
            z = z3 ? 1 : 0;
            z2 = z4 ? 1 : 0;
        }
    }

    private void invShiftRows() {
        int i = this.wordsInBlock;
        if (i == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
            return;
        }
        if (i == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j5) & (-281470681808896L);
            long j9 = j4 ^ j8;
            long j10 = j5 ^ j8;
            long j11 = (j6 ^ j7) & (-281470681808896L);
            long j12 = j6 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-4294967296L);
            long j15 = j9 ^ j14;
            long j16 = (j10 ^ j13) & 281474976645120L;
            jArr2[0] = j15;
            jArr2[1] = j10 ^ j16;
            jArr2[2] = j12 ^ j14;
            jArr2[3] = j16 ^ j13;
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long[] jArr3 = this.internalState;
        long j17 = jArr3[0];
        long j18 = jArr3[1];
        long j19 = jArr3[2];
        long j20 = jArr3[3];
        long j21 = jArr3[4];
        long j22 = jArr3[5];
        long j23 = jArr3[6];
        long j24 = jArr3[7];
        long j25 = (j17 ^ j18) & (-71777214294589696L);
        long j26 = j17 ^ j25;
        long j27 = j18 ^ j25;
        long j28 = (j19 ^ j20) & (-71777214294589696L);
        long j29 = j19 ^ j28;
        long j30 = j20 ^ j28;
        long j31 = (j21 ^ j22) & (-71777214294589696L);
        long j32 = j21 ^ j31;
        long j33 = j22 ^ j31;
        long j34 = (j23 ^ j24) & (-71777214294589696L);
        long j35 = j23 ^ j34;
        long j36 = j24 ^ j34;
        long j37 = (j26 ^ j29) & (-281470681808896L);
        long j38 = j26 ^ j37;
        long j39 = j29 ^ j37;
        long j40 = (j27 ^ j30) & 72056494543077120L;
        long j41 = j27 ^ j40;
        long j42 = j30 ^ j40;
        long j43 = (j32 ^ j35) & (-281470681808896L);
        long j44 = j32 ^ j43;
        long j45 = j35 ^ j43;
        long j46 = (j33 ^ j36) & 72056494543077120L;
        long j47 = j33 ^ j46;
        long j48 = j36 ^ j46;
        long j49 = (j38 ^ j44) & (-4294967296L);
        long j50 = j38 ^ j49;
        long j51 = j44 ^ j49;
        long j52 = (j41 ^ j47) & 72057594021150720L;
        long j53 = j41 ^ j52;
        long j54 = (j39 ^ j45) & 281474976645120L;
        long j55 = j39 ^ j54;
        long j56 = j54 ^ j45;
        long j57 = (j42 ^ j48) & 1099511627520L;
        jArr3[0] = j50;
        jArr3[1] = j53;
        jArr3[2] = j55;
        jArr3[3] = j42 ^ j57;
        jArr3[4] = j51;
        jArr3[5] = j47 ^ j52;
        jArr3[6] = j56;
        jArr3[7] = j48 ^ j57;
    }

    private void invSubBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = T0;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = T1;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = T2;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            int i4 = (T3[i2 >>> 24] << 24) | (b & UByte.MAX_VALUE) | ((b2 & UByte.MAX_VALUE) << 8) | ((b3 & UByte.MAX_VALUE) << 16);
            byte b4 = bArr[i3 & 255];
            byte b5 = bArr2[(i3 >>> 8) & 255];
            byte b6 = bArr3[(i3 >>> 16) & 255];
            jArr[i] = (i4 & 4294967295L) | (((r11[i3 >>> 24] << 24) | (((b4 & UByte.MAX_VALUE) | ((b5 & UByte.MAX_VALUE) << 8)) | ((b6 & UByte.MAX_VALUE) << 16))) << 32);
        }
    }

    private static long mixColumn(long j) {
        long mulX = mulX(j);
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(16, rotate)) ^ rotate(48, j);
        return ((rotate(32, mulX2((j ^ rotate2) ^ mulX)) ^ rotate2) ^ rotate(40, mulX)) ^ rotate(48, mulX);
    }

    private static long mixColumnInv(long j) {
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(32, rotate)) ^ rotate(48, j);
        long j2 = rotate2 ^ j;
        long rotate3 = rotate(48, j);
        long rotate4 = rotate(56, j);
        long mulX = mulX(j2 ^ rotate4) ^ rotate(56, j2);
        long mulX2 = mulX(rotate(40, mulX(mulX) ^ j) ^ (rotate(16, j2) ^ j)) ^ (j2 ^ rotate3);
        return mulX(rotate(40, ((j ^ rotate(32, j2)) ^ rotate4) ^ mulX(((rotate3 ^ (rotate(24, j) ^ j2)) ^ rotate4) ^ mulX(mulX(mulX2) ^ rotate(16, rotate2))))) ^ rotate2;
    }

    private void mixColumns() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            jArr[i] = mixColumn(jArr[i]);
        }
    }

    private void mixColumnsInv() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            jArr[i] = mixColumnInv(jArr[i]);
        }
    }

    private static long mulX(long j) {
        return (((j & (-9187201950435737472L)) >>> 7) * 29) ^ ((9187201950435737471L & j) << 1);
    }

    private static long mulX2(long j) {
        return (((j & 4629771061636907072L) >>> 6) * 29) ^ (((4557430888798830399L & j) << 2) ^ ((((-9187201950435737472L) & j) >>> 6) * 29));
    }

    private static long rotate(int i, long j) {
        return (j << (-i)) | (j >>> i);
    }

    private void rotateLeft(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        if (i == 2) {
            long j = jArr[0];
            long j2 = jArr[1];
            jArr2[0] = (j >>> 56) | (j2 << 8);
            jArr2[1] = (j << 8) | (j2 >>> 56);
            return;
        }
        if (i == 4) {
            long j3 = jArr[0];
            long j4 = jArr[1];
            long j5 = jArr[2];
            long j6 = jArr[3];
            jArr2[0] = (j4 >>> 24) | (j5 << 40);
            jArr2[1] = (j5 >>> 24) | (j6 << 40);
            jArr2[2] = (j6 >>> 24) | (j3 << 40);
            jArr2[3] = (j3 >>> 24) | (j4 << 40);
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long j7 = jArr[0];
        long j8 = jArr[1];
        long j9 = jArr[2];
        long j10 = jArr[3];
        long j11 = jArr[4];
        long j12 = jArr[5];
        long j13 = jArr[6];
        long j14 = jArr[7];
        jArr2[0] = (j9 >>> 24) | (j10 << 40);
        jArr2[1] = (j10 >>> 24) | (j11 << 40);
        jArr2[2] = (j11 >>> 24) | (j12 << 40);
        jArr2[3] = (j12 >>> 24) | (j13 << 40);
        jArr2[4] = (j13 >>> 24) | (j14 << 40);
        jArr2[5] = (j14 >>> 24) | (j7 << 40);
        jArr2[6] = (j7 >>> 24) | (j8 << 40);
        jArr2[7] = (j8 >>> 24) | (j9 << 40);
    }

    private void shiftRows() {
        int i = this.wordsInBlock;
        if (i == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
            return;
        }
        if (i == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j6) & (-4294967296L);
            long j9 = j4 ^ j8;
            long j10 = j6 ^ j8;
            long j11 = (j5 ^ j7) & 281474976645120L;
            long j12 = j5 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-281470681808896L);
            long j15 = (j10 ^ j13) & (-281470681808896L);
            jArr2[0] = j9 ^ j14;
            jArr2[1] = j12 ^ j14;
            jArr2[2] = j10 ^ j15;
            jArr2[3] = j13 ^ j15;
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long[] jArr3 = this.internalState;
        long j16 = jArr3[0];
        long j17 = jArr3[1];
        long j18 = jArr3[2];
        long j19 = jArr3[3];
        long j20 = jArr3[4];
        long j21 = jArr3[5];
        long j22 = jArr3[6];
        long j23 = jArr3[7];
        long j24 = (j16 ^ j20) & (-4294967296L);
        long j25 = j16 ^ j24;
        long j26 = j20 ^ j24;
        long j27 = (j17 ^ j21) & 72057594021150720L;
        long j28 = j17 ^ j27;
        long j29 = j21 ^ j27;
        long j30 = (j18 ^ j22) & 281474976645120L;
        long j31 = j18 ^ j30;
        long j32 = j22 ^ j30;
        long j33 = (j19 ^ j23) & 1099511627520L;
        long j34 = j19 ^ j33;
        long j35 = j23 ^ j33;
        long j36 = (j25 ^ j31) & (-281470681808896L);
        long j37 = j25 ^ j36;
        long j38 = j31 ^ j36;
        long j39 = (j28 ^ j34) & 72056494543077120L;
        long j40 = j28 ^ j39;
        long j41 = j34 ^ j39;
        long j42 = (j26 ^ j32) & (-281470681808896L);
        long j43 = j26 ^ j42;
        long j44 = j32 ^ j42;
        long j45 = (j29 ^ j35) & 72056494543077120L;
        long j46 = j29 ^ j45;
        long j47 = j35 ^ j45;
        long j48 = (j37 ^ j40) & (-71777214294589696L);
        long j49 = j37 ^ j48;
        long j50 = j40 ^ j48;
        long j51 = (j38 ^ j41) & (-71777214294589696L);
        long j52 = j38 ^ j51;
        long j53 = j41 ^ j51;
        long j54 = (j43 ^ j46) & (-71777214294589696L);
        long j55 = j43 ^ j54;
        long j56 = j46 ^ j54;
        long j57 = (j44 ^ j47) & (-71777214294589696L);
        jArr3[0] = j49;
        jArr3[1] = j50;
        jArr3[2] = j52;
        jArr3[3] = j53;
        jArr3[4] = j55;
        jArr3[5] = j56;
        jArr3[6] = j44 ^ j57;
        jArr3[7] = j47 ^ j57;
    }

    private void subBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = S0;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = S1;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = S2;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            int i4 = (S3[i2 >>> 24] << 24) | (b & UByte.MAX_VALUE) | ((b2 & UByte.MAX_VALUE) << 8) | ((b3 & UByte.MAX_VALUE) << 16);
            byte b4 = bArr[i3 & 255];
            byte b5 = bArr2[(i3 >>> 8) & 255];
            byte b6 = bArr3[(i3 >>> 16) & 255];
            jArr[i] = (i4 & 4294967295L) | (((r11[i3 >>> 24] << 24) | (((b4 & UByte.MAX_VALUE) | ((b5 & UByte.MAX_VALUE) << 8)) | ((b6 & UByte.MAX_VALUE) << 16))) << 32);
        }
    }

    private void subRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] - jArr[i2];
        }
    }

    private void workingKeyExpandEven(long[] jArr, long[] jArr2) {
        int i;
        int i2;
        int i3 = this.wordsInKey;
        long[] jArr3 = new long[i3];
        long[] jArr4 = new long[this.wordsInBlock];
        System.arraycopy(jArr, 0, jArr3, 0, i3);
        long j = 281479271743489L;
        int i4 = 0;
        while (true) {
            for (int i5 = 0; i5 < this.wordsInBlock; i5++) {
                jArr4[i5] = jArr2[i5] + j;
            }
            for (int i6 = 0; i6 < this.wordsInBlock; i6++) {
                this.internalState[i6] = jArr3[i6] + jArr4[i6];
            }
            subBytes();
            shiftRows();
            mixColumns();
            for (int i7 = 0; i7 < this.wordsInBlock; i7++) {
                long[] jArr5 = this.internalState;
                jArr5[i7] = jArr5[i7] ^ jArr4[i7];
            }
            subBytes();
            shiftRows();
            mixColumns();
            int i8 = 0;
            while (true) {
                i = this.wordsInBlock;
                if (i8 >= i) {
                    break;
                }
                long[] jArr6 = this.internalState;
                jArr6[i8] = jArr6[i8] + jArr4[i8];
                i8++;
            }
            System.arraycopy(this.internalState, 0, this.roundKeys[i4], 0, i);
            if (this.roundsAmount == i4) {
                return;
            }
            if (this.wordsInBlock != this.wordsInKey) {
                i4 += 2;
                j <<= 1;
                for (int i9 = 0; i9 < this.wordsInBlock; i9++) {
                    jArr4[i9] = jArr2[i9] + j;
                }
                int i10 = 0;
                while (true) {
                    int i11 = this.wordsInBlock;
                    if (i10 >= i11) {
                        break;
                    }
                    this.internalState[i10] = jArr3[i11 + i10] + jArr4[i10];
                    i10++;
                }
                subBytes();
                shiftRows();
                mixColumns();
                for (int i12 = 0; i12 < this.wordsInBlock; i12++) {
                    long[] jArr7 = this.internalState;
                    jArr7[i12] = jArr7[i12] ^ jArr4[i12];
                }
                subBytes();
                shiftRows();
                mixColumns();
                int i13 = 0;
                while (true) {
                    i2 = this.wordsInBlock;
                    if (i13 >= i2) {
                        break;
                    }
                    long[] jArr8 = this.internalState;
                    jArr8[i13] = jArr8[i13] + jArr4[i13];
                    i13++;
                }
                System.arraycopy(this.internalState, 0, this.roundKeys[i4], 0, i2);
                if (this.roundsAmount == i4) {
                    return;
                }
            }
            i4 += 2;
            j <<= 1;
            long j2 = jArr3[0];
            for (int i14 = 1; i14 < i3; i14++) {
                jArr3[i14 - 1] = jArr3[i14];
            }
            jArr3[i3 - 1] = j2;
        }
    }

    private void workingKeyExpandKT(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        long[] jArr3 = new long[i];
        long[] jArr4 = new long[i];
        long[] jArr5 = new long[i];
        this.internalState = jArr5;
        long j = jArr5[0];
        int i2 = this.wordsInKey;
        jArr5[0] = j + i + i2 + 1;
        System.arraycopy(jArr, 0, jArr3, 0, i);
        if (i == i2) {
            System.arraycopy(jArr, 0, jArr4, 0, i);
        } else {
            int i3 = this.wordsInBlock;
            System.arraycopy(jArr, i3, jArr4, 0, i3);
        }
        int i4 = 0;
        while (true) {
            long[] jArr6 = this.internalState;
            if (i4 >= jArr6.length) {
                break;
            }
            jArr6[i4] = jArr6[i4] + jArr3[i4];
            i4++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int i5 = 0;
        while (true) {
            long[] jArr7 = this.internalState;
            if (i5 >= jArr7.length) {
                break;
            }
            jArr7[i5] = jArr7[i5] ^ jArr4[i5];
            i5++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int i6 = 0;
        while (true) {
            long[] jArr8 = this.internalState;
            if (i6 >= jArr8.length) {
                subBytes();
                shiftRows();
                mixColumns();
                System.arraycopy(this.internalState, 0, jArr2, 0, this.wordsInBlock);
                return;
            }
            jArr8[i6] = jArr8[i6] + jArr3[i6];
            i6++;
        }
    }

    private void workingKeyExpandOdd() {
        for (int i = 1; i < this.roundsAmount; i += 2) {
            long[][] jArr = this.roundKeys;
            rotateLeft(jArr[i - 1], jArr[i]);
        }
    }

    private void xorRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] ^ jArr[i2];
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DSTU7624";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.wordsInBlock << 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008b A[LOOP:0: B:21:0x0086->B:23:0x008b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0094 A[EDGE_INSN: B:24:0x0094->B:25:0x0094 BREAK  A[LOOP:0: B:21:0x0086->B:23:0x008b], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b4  */
    @Override // org.bouncycastle.crypto.BlockCipher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(boolean r7, org.bouncycastle.crypto.CipherParameters r8) throws java.lang.IllegalArgumentException {
        /*
            r6 = this;
            boolean r0 = r8 instanceof org.bouncycastle.crypto.params.KeyParameter
            if (r0 == 0) goto Lbc
            r6.forEncryption = r7
            r0 = r8
            org.bouncycastle.crypto.params.KeyParameter r0 = (org.bouncycastle.crypto.params.KeyParameter) r0
            byte[] r0 = r0.getKey()
            int r1 = r0.length
            int r1 = r1 << 3
            int r2 = r6.wordsInBlock
            int r2 = r2 << 6
            r3 = 512(0x200, float:7.17E-43)
            r4 = 128(0x80, float:1.8E-43)
            r5 = 256(0x100, float:3.59E-43)
            if (r1 == r4) goto L2a
            if (r1 == r5) goto L2a
            if (r1 != r3) goto L21
            goto L2a
        L21:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "unsupported key length: only 128/256/512 are allowed"
            r7.<init>(r8)
            throw r7
        L2a:
            if (r1 == r2) goto L39
            int r2 = r2 * 2
            if (r1 != r2) goto L31
            goto L39
        L31:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Unsupported key length"
            r7.<init>(r8)
            throw r7
        L39:
            if (r1 == r4) goto L64
            if (r1 == r5) goto L52
            if (r1 == r3) goto L40
            goto L78
        L40:
            r2 = 18
            r6.roundsAmount = r2
            org.bouncycastle.crypto.constraints.DefaultServiceProperties r2 = new org.bouncycastle.crypto.constraints.DefaultServiceProperties
            java.lang.String r3 = r6.getAlgorithmName()
            org.bouncycastle.crypto.CryptoServicePurpose r7 = org.bouncycastle.crypto.engines.Utils.getPurpose(r7)
            r2.<init>(r3, r5, r8, r7)
            goto L75
        L52:
            r2 = 14
            r6.roundsAmount = r2
            org.bouncycastle.crypto.constraints.DefaultServiceProperties r2 = new org.bouncycastle.crypto.constraints.DefaultServiceProperties
            java.lang.String r3 = r6.getAlgorithmName()
            org.bouncycastle.crypto.CryptoServicePurpose r7 = org.bouncycastle.crypto.engines.Utils.getPurpose(r7)
            r2.<init>(r3, r5, r8, r7)
            goto L75
        L64:
            r2 = 10
            r6.roundsAmount = r2
            org.bouncycastle.crypto.constraints.DefaultServiceProperties r2 = new org.bouncycastle.crypto.constraints.DefaultServiceProperties
            java.lang.String r3 = r6.getAlgorithmName()
            org.bouncycastle.crypto.CryptoServicePurpose r7 = org.bouncycastle.crypto.engines.Utils.getPurpose(r7)
            r2.<init>(r3, r4, r8, r7)
        L75:
            org.bouncycastle.crypto.CryptoServicesRegistrar.checkConstraints(r2)
        L78:
            int r7 = r1 >>> 6
            r6.wordsInKey = r7
            int r7 = r6.roundsAmount
            int r7 = r7 + 1
            long[][] r7 = new long[r7][]
            r6.roundKeys = r7
            r7 = 0
            r8 = r7
        L86:
            long[][] r2 = r6.roundKeys
            int r3 = r2.length
            if (r8 >= r3) goto L94
            int r3 = r6.wordsInBlock
            long[] r3 = new long[r3]
            r2[r8] = r3
            int r8 = r8 + 1
            goto L86
        L94:
            int r8 = r6.wordsInKey
            long[] r8 = new long[r8]
            r6.workingKey = r8
            int r2 = r0.length
            int r1 = r1 >>> 3
            if (r2 != r1) goto Lb4
            org.bouncycastle.util.Pack.littleEndianToLong(r0, r7, r8)
            int r7 = r6.wordsInBlock
            long[] r7 = new long[r7]
            long[] r8 = r6.workingKey
            r6.workingKeyExpandKT(r8, r7)
            long[] r8 = r6.workingKey
            r6.workingKeyExpandEven(r8, r7)
            r6.workingKeyExpandOdd()
            return
        Lb4:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Invalid key parameter passed to DSTU7624Engine init"
            r7.<init>(r8)
            throw r7
        Lbc:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Invalid parameter passed to DSTU7624Engine init"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.DSTU7624Engine.init(boolean, org.bouncycastle.crypto.CipherParameters):void");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        if (this.workingKey == null) {
            throw new IllegalStateException("DSTU7624Engine not initialised");
        }
        if (getBlockSize() + i > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (getBlockSize() + i2 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i4 = 0;
        if (this.forEncryption) {
            if (this.wordsInBlock != 2) {
                Pack.littleEndianToLong(bArr, i, this.internalState);
                addRoundKey(0);
                while (true) {
                    subBytes();
                    shiftRows();
                    mixColumns();
                    i4++;
                    i3 = this.roundsAmount;
                    if (i4 == i3) {
                        break;
                    }
                    xorRoundKey(i4);
                }
                addRoundKey(i3);
                Pack.longToLittleEndian(this.internalState, bArr2, i2);
            } else {
                encryptBlock_128(bArr, i, bArr2, i2);
            }
        } else if (this.wordsInBlock != 2) {
            Pack.littleEndianToLong(bArr, i, this.internalState);
            subRoundKey(this.roundsAmount);
            int i5 = this.roundsAmount;
            while (true) {
                mixColumnsInv();
                invShiftRows();
                invSubBytes();
                i5--;
                if (i5 == 0) {
                    break;
                }
                xorRoundKey(i5);
            }
            subRoundKey(0);
            Pack.longToLittleEndian(this.internalState, bArr2, i2);
        } else {
            decryptBlock_128(bArr, i, bArr2, i2);
        }
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.internalState, 0L);
    }
}
