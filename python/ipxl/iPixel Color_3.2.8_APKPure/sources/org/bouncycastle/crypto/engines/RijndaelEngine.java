package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.Command;
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

/* loaded from: classes3.dex */
public class RijndaelEngine implements BlockCipher {
    private static final int MAXKC = 64;
    private static final int MAXROUNDS = 14;
    private long A0;
    private long A1;
    private long A2;
    private long A3;
    private int BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;
    private long[][] workingKey;
    private static final byte[] logtable = {0, 0, 25, 1, 50, 2, 26, -58, 75, JSONB.Constants.BC_INT64_SHORT_MAX, 27, 104, 51, -18, -33, 3, 100, 4, -32, 14, 52, -115, -127, -17, 76, 113, 8, JSONB.Constants.BC_INT64_BYTE_MIN, -8, JSONB.Constants.BC_STR_ASCII_FIX_32, 28, -63, JSONB.Constants.BC_STR_UTF16BE, -62, 29, JSONB.Constants.BC_DOUBLE, -7, JSONB.Constants.BC_DECIMAL, 39, 106, JSONB.Constants.BC_STR_ASCII_FIX_4, -28, JSONB.Constants.BC_OBJECT, 114, -102, -55, 9, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 101, JSONB.Constants.BC_INT32_NUM_MAX, -118, 5, 33, 15, -31, 36, 18, JSONB.Constants.BC_INT32_NUM_MIN, -126, 69, 53, JSONB.Constants.BC_REFERENCE, -38, -114, -106, -113, -37, JSONB.Constants.BC_INT8, 54, JSONB.Constants.BC_INT64_BYTE_ZERO, -50, -108, 19, 92, -46, -15, JSONB.Constants.BC_INT32_SHORT_MIN, 70, -125, JSONB.Constants.BC_INT32_BYTE_ZERO, 102, -35, -3, JSONB.Constants.BC_INT32_BYTE_MIN, JSONB.Constants.BC_INT64_INT, 6, -117, 98, JSONB.Constants.BC_DOUBLE_NUM_1, 37, -30, -104, 34, -120, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_STR_GB18030, 110, JSONB.Constants.BC_INT32, -61, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_FLOAT_INT, 30, 66, 58, 107, 40, 84, -6, -123, Base64.padSymbol, -70, 43, JSONB.Constants.BC_STR_ASCII, 10, 21, -101, -97, 94, -54, JSONB.Constants.BC_STR_ASCII_FIX_5, -44, JSONB.Constants.BC_TIMESTAMP_SECONDS, -27, -13, 115, JSONB.Constants.BC_LOCAL_TIME, 87, JSONB.Constants.BC_NULL, 88, JSONB.Constants.BC_LOCAL_DATETIME, 80, -12, -22, -42, 116, 79, JSONB.Constants.BC_TIMESTAMP, -23, -43, -25, -26, JSONB.Constants.BC_TIMESTAMP_MINUTES, -24, 44, JSONB.Constants.BC_INT64_BYTE_MAX, 117, JSONB.Constants.BC_STR_UTF8, -21, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 11, -11, 89, -53, 95, JSONB.Constants.BC_FALSE, -100, JSONB.Constants.BC_LOCAL_DATE, 81, -96, Byte.MAX_VALUE, 12, -10, 111, 23, JSONB.Constants.BC_INT64_SHORT_ZERO, 73, -20, JSONB.Constants.BC_INT64_NUM_MIN, 67, 31, 45, JSONB.Constants.BC_ARRAY, 118, JSONB.Constants.BC_STR_UTF16, JSONB.Constants.BC_FLOAT, -52, JSONB.Constants.BC_BIGINT, 62, 90, -5, 96, JSONB.Constants.BC_TRUE, -122, 59, 82, -95, 108, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 85, 41, -99, -105, JSONB.Constants.BC_DOUBLE_NUM_0, -121, JSONB.Constants.BC_CHAR, 97, JSONB.Constants.BC_INT64, JL_Constant.PREFIX_FLAG_SECOND, -4, -68, -107, -49, -51, 55, 63, 91, -47, 83, 57, -124, 60, 65, -94, JSONB.Constants.BC_STR_ASCII_FIX_36, JSONB.Constants.BC_INT32_SHORT_MAX, 20, 42, -98, 93, 86, -14, -45, JSONB.Constants.BC_TIMESTAMP_MILLIS, JSONB.Constants.BC_INT32_SHORT_ZERO, 17, JSONB.Constants.BC_TYPED_ANY, -39, 35, 32, 46, -119, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_STR_UTF16LE, JSONB.Constants.BC_DECIMAL_LONG, 38, 119, -103, -29, JSONB.Constants.BC_OBJECT_END, 103, JSONB.Constants.BC_STR_ASCII_FIX_1, -19, -34, -59, 49, -2, 24, 13, 99, -116, ByteCompanionObject.MIN_VALUE, JSONB.Constants.BC_INT64_SHORT_MIN, -9, 112, 7};
    private static final byte[] aLogtable = {0, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, -106, -95, -8, 19, 53, 95, -31, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_INT32, JSONB.Constants.BC_INT64_NUM_MIN, 115, -107, JSONB.Constants.BC_ARRAY, -9, 2, 6, 10, 30, 34, 102, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -27, 52, 92, -28, 55, 89, -21, 38, 106, JSONB.Constants.BC_INT64, -39, 112, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_TIMESTAMP_MILLIS, -26, 49, 83, -11, 4, 12, 20, 60, JSONB.Constants.BC_INT32_SHORT_ZERO, -52, 79, -47, 104, JSONB.Constants.BC_DECIMAL_LONG, -45, 110, JSONB.Constants.BC_DOUBLE_NUM_0, -51, 76, -44, 103, JSONB.Constants.BC_LOCAL_DATE, -32, 59, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_INT64_BYTE_MAX, 98, JSONB.Constants.BC_OBJECT, -15, 8, 24, 40, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -120, -125, -98, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_INT64_BYTE_ZERO, 107, JSONB.Constants.BC_INT8, JL_Constant.PREFIX_FLAG_SECOND, Byte.MAX_VALUE, -127, -104, JSONB.Constants.BC_DOUBLE_NUM_1, -50, 73, -37, 118, -102, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_INT64_SHORT_ZERO, 87, -7, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_INT32_BYTE_MIN, 80, JSONB.Constants.BC_INT32_NUM_MIN, 11, 29, 39, JSONB.Constants.BC_STR_ASCII_FIX_32, JSONB.Constants.BC_BIGINT, -42, 97, JSONB.Constants.BC_ARRAY_FIX_MAX, -2, 25, 43, JSONB.Constants.BC_STR_UTF16BE, -121, JSONB.Constants.BC_TYPED_ANY, JSONB.Constants.BC_TIMESTAMP_MINUTES, -20, JSONB.Constants.BC_INT32_NUM_MAX, 113, JSONB.Constants.BC_REFERENCE, JSONB.Constants.BC_TIMESTAMP, -23, 32, 96, -96, -5, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 58, JSONB.Constants.BC_STR_ASCII_FIX_5, -46, JSONB.Constants.BC_STR_ASCII_FIX_36, JSONB.Constants.BC_FLOAT, -62, 93, -25, 50, 86, -6, 21, 63, 65, -61, 94, -30, Base64.padSymbol, JSONB.Constants.BC_INT32_SHORT_MAX, -55, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_INT64_SHORT_MIN, 91, -19, 44, 116, -100, JSONB.Constants.BC_INT64_INT, -38, 117, -97, -70, -43, 100, JSONB.Constants.BC_TIMESTAMP_SECONDS, -17, 42, JSONB.Constants.BC_STR_GB18030, -126, -99, -68, -33, JSONB.Constants.BC_STR_UTF8, -114, -119, ByteCompanionObject.MIN_VALUE, -101, JSONB.Constants.BC_FLOAT_INT, -63, 88, -24, 35, 101, JSONB.Constants.BC_NULL, -22, 37, 111, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_INT64_BYTE_MIN, 67, -59, 84, -4, 31, 33, 99, JSONB.Constants.BC_OBJECT_END, -12, 7, 9, 27, 45, 119, -103, JSONB.Constants.BC_FALSE, -53, 70, -54, 69, -49, JSONB.Constants.BC_STR_ASCII_FIX_1, -34, JSONB.Constants.BC_STR_ASCII, -117, -122, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_LOCAL_DATETIME, -29, 62, 66, -58, 81, -13, 14, 18, 54, 90, -18, 41, JSONB.Constants.BC_STR_UTF16, -115, -116, -113, -118, -123, -108, JSONB.Constants.BC_LOCAL_TIME, -14, 13, 23, 57, 75, -35, JSONB.Constants.BC_STR_UTF16LE, -124, -105, -94, -3, 28, 36, 108, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_INT64_SHORT_MAX, 82, -10, 1, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, -106, -95, -8, 19, 53, 95, -31, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_INT32, JSONB.Constants.BC_INT64_NUM_MIN, 115, -107, JSONB.Constants.BC_ARRAY, -9, 2, 6, 10, 30, 34, 102, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -27, 52, 92, -28, 55, 89, -21, 38, 106, JSONB.Constants.BC_INT64, -39, 112, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_TIMESTAMP_MILLIS, -26, 49, 83, -11, 4, 12, 20, 60, JSONB.Constants.BC_INT32_SHORT_ZERO, -52, 79, -47, 104, JSONB.Constants.BC_DECIMAL_LONG, -45, 110, JSONB.Constants.BC_DOUBLE_NUM_0, -51, 76, -44, 103, JSONB.Constants.BC_LOCAL_DATE, -32, 59, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_INT64_BYTE_MAX, 98, JSONB.Constants.BC_OBJECT, -15, 8, 24, 40, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -120, -125, -98, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_INT64_BYTE_ZERO, 107, JSONB.Constants.BC_INT8, JL_Constant.PREFIX_FLAG_SECOND, Byte.MAX_VALUE, -127, -104, JSONB.Constants.BC_DOUBLE_NUM_1, -50, 73, -37, 118, -102, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_INT64_SHORT_ZERO, 87, -7, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_INT32_BYTE_MIN, 80, JSONB.Constants.BC_INT32_NUM_MIN, 11, 29, 39, JSONB.Constants.BC_STR_ASCII_FIX_32, JSONB.Constants.BC_BIGINT, -42, 97, JSONB.Constants.BC_ARRAY_FIX_MAX, -2, 25, 43, JSONB.Constants.BC_STR_UTF16BE, -121, JSONB.Constants.BC_TYPED_ANY, JSONB.Constants.BC_TIMESTAMP_MINUTES, -20, JSONB.Constants.BC_INT32_NUM_MAX, 113, JSONB.Constants.BC_REFERENCE, JSONB.Constants.BC_TIMESTAMP, -23, 32, 96, -96, -5, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 58, JSONB.Constants.BC_STR_ASCII_FIX_5, -46, JSONB.Constants.BC_STR_ASCII_FIX_36, JSONB.Constants.BC_FLOAT, -62, 93, -25, 50, 86, -6, 21, 63, 65, -61, 94, -30, Base64.padSymbol, JSONB.Constants.BC_INT32_SHORT_MAX, -55, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_INT64_SHORT_MIN, 91, -19, 44, 116, -100, JSONB.Constants.BC_INT64_INT, -38, 117, -97, -70, -43, 100, JSONB.Constants.BC_TIMESTAMP_SECONDS, -17, 42, JSONB.Constants.BC_STR_GB18030, -126, -99, -68, -33, JSONB.Constants.BC_STR_UTF8, -114, -119, ByteCompanionObject.MIN_VALUE, -101, JSONB.Constants.BC_FLOAT_INT, -63, 88, -24, 35, 101, JSONB.Constants.BC_NULL, -22, 37, 111, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_INT64_BYTE_MIN, 67, -59, 84, -4, 31, 33, 99, JSONB.Constants.BC_OBJECT_END, -12, 7, 9, 27, 45, 119, -103, JSONB.Constants.BC_FALSE, -53, 70, -54, 69, -49, JSONB.Constants.BC_STR_ASCII_FIX_1, -34, JSONB.Constants.BC_STR_ASCII, -117, -122, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_LOCAL_DATETIME, -29, 62, 66, -58, 81, -13, 14, 18, 54, 90, -18, 41, JSONB.Constants.BC_STR_UTF16, -115, -116, -113, -118, -123, -108, JSONB.Constants.BC_LOCAL_TIME, -14, 13, 23, 57, 75, -35, JSONB.Constants.BC_STR_UTF16LE, -124, -105, -94, -3, 28, 36, 108, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_INT64_SHORT_MAX, 82, -10, 1};
    private static final byte[] S = {99, JSONB.Constants.BC_STR_UTF16LE, 119, JSONB.Constants.BC_STR_UTF16, -14, 107, 111, -59, JSONB.Constants.BC_INT32_BYTE_MIN, 1, 103, 43, -2, JSONB.Constants.BC_INT64_BYTE_MAX, JSONB.Constants.BC_TIMESTAMP_MILLIS, 118, -54, -126, -55, JSONB.Constants.BC_STR_UTF16BE, -6, 89, JSONB.Constants.BC_INT32_SHORT_MAX, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MINUTES, -44, -94, JSONB.Constants.BC_NULL, -100, JSONB.Constants.BC_ARRAY, 114, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_FLOAT, -3, JSONB.Constants.BC_REFERENCE, 38, 54, 63, -9, -52, 52, JSONB.Constants.BC_OBJECT_END, -27, -15, 113, JSONB.Constants.BC_INT64_NUM_MIN, 49, 21, 4, JSONB.Constants.BC_INT64_SHORT_MAX, 35, -61, 24, -106, 5, -102, 7, 18, ByteCompanionObject.MIN_VALUE, -30, -21, 39, JSONB.Constants.BC_DOUBLE_NUM_0, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, JSONB.Constants.BC_DOUBLE_NUM_1, 41, -29, JSONB.Constants.BC_INT32_NUM_MAX, -124, 83, -47, 0, -19, 32, -4, JSONB.Constants.BC_TRUE, 91, 106, -53, JSONB.Constants.BC_INT64, 57, JSONB.Constants.BC_STR_ASCII_FIX_1, 76, 88, -49, JSONB.Constants.BC_INT64_BYTE_ZERO, -17, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -5, 67, JSONB.Constants.BC_STR_ASCII_FIX_4, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, JSONB.Constants.BC_LOCAL_DATETIME, 81, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_INT32_SHORT_MIN, -113, JSONB.Constants.BC_TYPED_ANY, -99, JSONB.Constants.BC_INT32_BYTE_ZERO, -11, -68, JSONB.Constants.BC_FLOAT_INT, -38, 33, JSONB.Constants.BC_INT32_NUM_16, -1, -13, -46, -51, 12, 19, -20, 95, -105, JSONB.Constants.BC_INT32_SHORT_ZERO, 23, JSONB.Constants.BC_INT64_SHORT_ZERO, JSONB.Constants.BC_LOCAL_TIME, JSONB.Constants.BC_STR_GB18030, Base64.padSymbol, 100, 93, 25, 115, 96, -127, 79, JL_Constant.PREFIX_FLAG_SECOND, 34, 42, JSONB.Constants.BC_CHAR, -120, 70, -18, JSONB.Constants.BC_DECIMAL_LONG, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, JSONB.Constants.BC_TIMESTAMP_SECONDS, 98, JSONB.Constants.BC_BINARY, -107, -28, JSONB.Constants.BC_STR_ASCII, -25, JSONB.Constants.BC_INT64_BYTE_MIN, 55, JSONB.Constants.BC_STR_ASCII_FIX_36, -115, -43, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_LOCAL_DATE, 108, 86, -12, -22, 101, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_TIMESTAMP, 8, -70, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 37, 46, 28, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_DOUBLE_LONG, -58, -24, -35, 116, 31, 75, JSONB.Constants.BC_INT8, -117, -118, 112, 62, JSONB.Constants.BC_DOUBLE, 102, JSONB.Constants.BC_INT32, 3, -10, 14, 97, 53, 87, JSONB.Constants.BC_DECIMAL, -122, -63, 29, -98, -31, -8, -104, 17, JSONB.Constants.BC_STR_ASCII_FIX_32, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, JSONB.Constants.BC_INT64_INT, -26, 66, 104, 65, -103, 45, 15, JSONB.Constants.BC_FALSE, 84, JSONB.Constants.BC_BIGINT, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER};
    private static final byte[] Si = {82, 9, 106, -43, JSONB.Constants.BC_INT32_BYTE_MIN, 54, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_ARRAY_FIX_MAX, -98, -127, -13, JSONB.Constants.BC_INT64_BYTE_MAX, -5, JSONB.Constants.BC_STR_UTF16LE, -29, 57, -126, -101, JSONB.Constants.BC_INT32_NUM_MAX, -1, -121, 52, -114, 67, JSONB.Constants.BC_INT32_SHORT_ZERO, JSONB.Constants.BC_INT64_SHORT_ZERO, -34, -23, -53, 84, JSONB.Constants.BC_STR_UTF16, -108, 50, JSONB.Constants.BC_OBJECT, -62, 35, Base64.padSymbol, -18, 76, -107, 11, 66, -6, -61, JSONB.Constants.BC_STR_ASCII_FIX_5, 8, 46, -95, 102, 40, -39, 36, JSONB.Constants.BC_DOUBLE_NUM_0, 118, 91, -94, 73, JSONB.Constants.BC_STR_ASCII_FIX_36, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -44, JSONB.Constants.BC_ARRAY, 92, -52, 93, 101, JSONB.Constants.BC_FLOAT_INT, JSONB.Constants.BC_TYPED_ANY, 108, 112, JSONB.Constants.BC_INT32, 80, -3, -19, JSONB.Constants.BC_DECIMAL, -38, 94, 21, 70, 87, JSONB.Constants.BC_LOCAL_TIME, -115, -99, -124, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MILLIS, 0, -116, -68, -45, 10, -9, -28, 88, 5, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_DOUBLE_NUM_1, 69, 6, JSONB.Constants.BC_INT64_BYTE_ZERO, 44, 30, -113, -54, 63, 15, 2, -63, JSONB.Constants.BC_NULL, JSONB.Constants.BC_INT8, 3, 1, 19, -118, 107, 58, JSONB.Constants.BC_BINARY, 17, 65, 79, 103, JL_Constant.PREFIX_FLAG_SECOND, -22, -105, -14, -49, -50, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_DOUBLE_LONG, -26, 115, -106, JSONB.Constants.BC_TIMESTAMP_SECONDS, 116, 34, -25, JSONB.Constants.BC_TIMESTAMP_MINUTES, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, JSONB.Constants.BC_INT32_SHORT_MAX, -15, 26, 113, 29, 41, -59, -119, 111, JSONB.Constants.BC_FLOAT, 98, 14, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 24, JSONB.Constants.BC_INT64, 27, -4, 86, 62, 75, -58, -46, JSONB.Constants.BC_STR_ASCII, 32, -102, -37, JSONB.Constants.BC_INT64_SHORT_MIN, -2, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -51, 90, -12, 31, -35, JSONB.Constants.BC_LOCAL_DATETIME, 51, -120, 7, JSONB.Constants.BC_INT64_SHORT_MAX, 49, JSONB.Constants.BC_TRUE, 18, JSONB.Constants.BC_INT32_NUM_16, 89, 39, ByteCompanionObject.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, JSONB.Constants.BC_LOCAL_DATE, 25, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_STR_ASCII_FIX_1, 13, 45, -27, JSONB.Constants.BC_STR_UTF8, -97, JSONB.Constants.BC_REFERENCE, -55, -100, -17, -96, -32, 59, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_TIMESTAMP, 42, -11, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_INT64_BYTE_MIN, -21, JSONB.Constants.BC_BIGINT, 60, -125, 83, -103, 97, 23, 43, 4, JSONB.Constants.BC_STR_GB18030, -70, 119, -42, 38, -31, JSONB.Constants.BC_STR_ASCII_FIX_32, 20, 99, 85, 33, 12, JSONB.Constants.BC_STR_UTF16BE};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, Opcodes.LOOKUPSWITCH, 77, Opcodes.IFNE, 47, 94, 188, 99, Opcodes.IFNULL, Opcodes.DCMPL, 53, 106, Command.CMD_GET_DEV_MD5, Opcodes.PUTSTATIC, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, Opcodes.I2B};
    static byte[][] shifts0 = {new byte[]{0, 8, JSONB.Constants.BC_INT32_NUM_16, 24}, new byte[]{0, 8, JSONB.Constants.BC_INT32_NUM_16, 24}, new byte[]{0, 8, JSONB.Constants.BC_INT32_NUM_16, 24}, new byte[]{0, 8, JSONB.Constants.BC_INT32_NUM_16, 32}, new byte[]{0, 8, 24, 32}};
    static byte[][] shifts1 = {new byte[]{0, 24, JSONB.Constants.BC_INT32_NUM_16, 8}, new byte[]{0, 32, 24, JSONB.Constants.BC_INT32_NUM_16}, new byte[]{0, 40, 32, 24}, new byte[]{0, JSONB.Constants.BC_INT32_BYTE_MIN, 40, 24}, new byte[]{0, JSONB.Constants.BC_INT32_BYTE_ZERO, 40, 32}};

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int i) {
        if (i == 128) {
            this.BC = 32;
            this.BC_MASK = 4294967295L;
            this.shifts0SC = shifts0[0];
            this.shifts1SC = shifts1[0];
        } else if (i == 160) {
            this.BC = 40;
            this.BC_MASK = 1099511627775L;
            this.shifts0SC = shifts0[1];
            this.shifts1SC = shifts1[1];
        } else if (i == 192) {
            this.BC = 48;
            this.BC_MASK = 281474976710655L;
            this.shifts0SC = shifts0[2];
            this.shifts1SC = shifts1[2];
        } else if (i == 224) {
            this.BC = 56;
            this.BC_MASK = 72057594037927935L;
            this.shifts0SC = shifts0[3];
            this.shifts1SC = shifts1[3];
        } else {
            if (i != 256) {
                throw new IllegalArgumentException("unknown blocksize to Rijndael");
            }
            this.BC = 64;
            this.BC_MASK = -1L;
            this.shifts0SC = shifts0[4];
            this.shifts1SC = shifts1[4];
        }
        this.blockBits = i;
    }

    private void InvMixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            int i2 = (int) ((this.A0 >> i) & 255);
            int i3 = (int) ((this.A1 >> i) & 255);
            int i4 = (int) ((this.A2 >> i) & 255);
            int i5 = (int) ((this.A3 >> i) & 255);
            int i6 = -1;
            int i7 = i2 != 0 ? logtable[i2 & 255] & UByte.MAX_VALUE : -1;
            int i8 = i3 != 0 ? logtable[i3 & 255] & UByte.MAX_VALUE : -1;
            int i9 = i4 != 0 ? logtable[i4 & 255] & UByte.MAX_VALUE : -1;
            if (i5 != 0) {
                i6 = logtable[i5 & 255] & UByte.MAX_VALUE;
            }
            j |= ((((mul0xe(i7) ^ mul0xb(i8)) ^ mul0xd(i9)) ^ mul0x9(i6)) & 255) << i;
            j4 |= ((((mul0xe(i8) ^ mul0xb(i9)) ^ mul0xd(i6)) ^ mul0x9(i7)) & 255) << i;
            j2 |= ((((mul0xe(i9) ^ mul0xb(i6)) ^ mul0xd(i7)) ^ mul0x9(i8)) & 255) << i;
            j3 = (((((mul0xe(i6) ^ mul0xb(i7)) ^ mul0xd(i8)) ^ mul0x9(i9)) & 255) << i) | j3;
        }
        this.A0 = j;
        this.A1 = j4;
        this.A2 = j2;
        this.A3 = j3;
    }

    private void KeyAddition(long[] jArr) {
        this.A0 ^= jArr[0];
        this.A1 ^= jArr[1];
        this.A2 ^= jArr[2];
        this.A3 ^= jArr[3];
    }

    private void MixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            int i2 = (int) ((this.A0 >> i) & 255);
            int i3 = (int) ((this.A1 >> i) & 255);
            int i4 = (int) ((this.A2 >> i) & 255);
            int i5 = (int) ((this.A3 >> i) & 255);
            j |= ((((mul0x2(i2) ^ mul0x3(i3)) ^ i4) ^ i5) & 255) << i;
            j4 |= ((((mul0x2(i3) ^ mul0x3(i4)) ^ i5) ^ i2) & 255) << i;
            j2 |= ((((mul0x2(i4) ^ mul0x3(i5)) ^ i2) ^ i3) & 255) << i;
            j3 = (((((mul0x2(i5) ^ mul0x3(i2)) ^ i3) ^ i4) & 255) << i) | j3;
        }
        this.A0 = j;
        this.A1 = j4;
        this.A2 = j2;
        this.A3 = j3;
    }

    private void ShiftRow(byte[] bArr) {
        this.A1 = shift(this.A1, bArr[1]);
        this.A2 = shift(this.A2, bArr[2]);
        this.A3 = shift(this.A3, bArr[3]);
    }

    private void Substitution(byte[] bArr) {
        this.A0 = applyS(this.A0, bArr);
        this.A1 = applyS(this.A1, bArr);
        this.A2 = applyS(this.A2, bArr);
        this.A3 = applyS(this.A3, bArr);
    }

    private long applyS(long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            j2 |= (bArr[(int) ((j >> i) & 255)] & UByte.MAX_VALUE) << i;
        }
        return j2;
    }

    private void decryptBlock(long[][] jArr) {
        KeyAddition(jArr[this.ROUNDS]);
        Substitution(Si);
        ShiftRow(this.shifts1SC);
        for (int i = this.ROUNDS - 1; i > 0; i--) {
            KeyAddition(jArr[i]);
            InvMixColumn();
            Substitution(Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(jArr[0]);
    }

    private void encryptBlock(long[][] jArr) {
        KeyAddition(jArr[0]);
        for (int i = 1; i < this.ROUNDS; i++) {
            Substitution(S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(jArr[i]);
        }
        Substitution(S);
        ShiftRow(this.shifts0SC);
        KeyAddition(jArr[this.ROUNDS]);
    }

    private long[][] generateWorkingKey(byte[] bArr) {
        int i;
        int i2 = 8;
        int length = bArr.length * 8;
        int i3 = 1;
        int i4 = 0;
        int i5 = 4;
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance((Class<?>) Long.TYPE, 15, 4);
        if (length == 128) {
            i = 4;
        } else if (length == 160) {
            i = 5;
        } else if (length == 192) {
            i = 6;
        } else if (length == 224) {
            i = 7;
        } else {
            if (length != 256) {
                throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
            }
            i = 8;
        }
        this.ROUNDS = length >= this.blockBits ? i + 6 : (this.BC / 8) + 6;
        int i6 = 0;
        int i7 = 0;
        while (i6 < bArr.length) {
            bArr2[i6 % 4][i6 / 4] = bArr[i7];
            i6++;
            i7++;
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < i && i9 < (this.ROUNDS + i3) * (this.BC / i2)) {
            int i10 = i4;
            while (i10 < 4) {
                int i11 = this.BC;
                long[] jArr2 = jArr[i9 / (i11 / 8)];
                jArr2[i10] = ((bArr2[i10][i8] & UByte.MAX_VALUE) << ((i9 * 8) % i11)) | jArr2[i10];
                i10++;
                i3 = i3;
                i2 = i2;
                i4 = i4;
            }
            i8++;
            i9++;
        }
        int i12 = i3;
        int i13 = i4;
        int i14 = i13;
        while (i9 < (this.ROUNDS + 1) * (this.BC / 8)) {
            int i15 = i13;
            while (i15 < i5) {
                byte[] bArr3 = bArr2[i15];
                i15++;
                bArr3[i13] = (byte) (bArr3[i13] ^ S[bArr2[i15 % 4][i - 1] & UByte.MAX_VALUE]);
            }
            byte[] bArr4 = bArr2[i13];
            int i16 = i14 + 1;
            bArr4[i13] = (byte) (rcon[i14] ^ bArr4[i13]);
            int i17 = i12;
            if (i <= 6) {
                while (i17 < i) {
                    for (int i18 = i13; i18 < i5; i18++) {
                        byte[] bArr5 = bArr2[i18];
                        bArr5[i17] = (byte) (bArr5[i17] ^ bArr5[i17 - 1]);
                    }
                    i17++;
                }
            } else {
                while (i17 < i5) {
                    for (int i19 = i13; i19 < i5; i19++) {
                        byte[] bArr6 = bArr2[i19];
                        bArr6[i17] = (byte) (bArr6[i17] ^ bArr6[i17 - 1]);
                    }
                    i17++;
                }
                for (int i20 = i13; i20 < i5; i20++) {
                    byte[] bArr7 = bArr2[i20];
                    bArr7[i5] = (byte) (bArr7[i5] ^ S[bArr7[3] & UByte.MAX_VALUE]);
                }
                for (int i21 = 5; i21 < i; i21++) {
                    for (int i22 = i13; i22 < i5; i22++) {
                        byte[] bArr8 = bArr2[i22];
                        bArr8[i21] = (byte) (bArr8[i21] ^ bArr8[i21 - 1]);
                    }
                }
            }
            int i23 = i13;
            while (i23 < i && i9 < (this.ROUNDS + 1) * (this.BC / 8)) {
                int i24 = i13;
                while (i24 < i5) {
                    int i25 = this.BC;
                    long[] jArr3 = jArr[i9 / (i25 / 8)];
                    jArr3[i24] = ((bArr2[i24][i23] & UByte.MAX_VALUE) << ((i9 * 8) % i25)) | jArr3[i24];
                    i24++;
                    i = i;
                    i5 = 4;
                }
                i23++;
                i9++;
                i5 = 4;
            }
            i14 = i16;
            i = i;
            i5 = 4;
        }
        return jArr;
    }

    private byte mul0x2(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & UByte.MAX_VALUE) + 25];
        }
        return (byte) 0;
    }

    private byte mul0x3(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & UByte.MAX_VALUE) + 1];
        }
        return (byte) 0;
    }

    private byte mul0x9(int i) {
        if (i >= 0) {
            return aLogtable[i + Opcodes.IFNONNULL];
        }
        return (byte) 0;
    }

    private byte mul0xb(int i) {
        if (i >= 0) {
            return aLogtable[i + 104];
        }
        return (byte) 0;
    }

    private byte mul0xd(int i) {
        if (i >= 0) {
            return aLogtable[i + 238];
        }
        return (byte) 0;
    }

    private byte mul0xe(int i) {
        if (i >= 0) {
            return aLogtable[i + 223];
        }
        return (byte) 0;
    }

    private void packBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 != this.BC; i2 += 8) {
            bArr[i] = (byte) (this.A0 >> i2);
            bArr[i + 1] = (byte) (this.A1 >> i2);
            int i3 = i + 3;
            bArr[i + 2] = (byte) (this.A2 >> i2);
            i += 4;
            bArr[i3] = (byte) (this.A3 >> i2);
        }
    }

    private long shift(long j, int i) {
        return ((j << (this.BC - i)) | (j >>> i)) & this.BC_MASK;
    }

    private void unpackBlock(byte[] bArr, int i) {
        this.A0 = bArr[i] & UByte.MAX_VALUE;
        this.A1 = bArr[i + 1] & UByte.MAX_VALUE;
        int i2 = i + 3;
        this.A2 = bArr[i + 2] & UByte.MAX_VALUE;
        int i3 = i + 4;
        this.A3 = bArr[i2] & UByte.MAX_VALUE;
        for (int i4 = 8; i4 != this.BC; i4 += 8) {
            this.A0 |= (bArr[i3] & UByte.MAX_VALUE) << i4;
            this.A1 |= (bArr[i3 + 1] & UByte.MAX_VALUE) << i4;
            int i5 = i3 + 3;
            this.A2 |= (bArr[i3 + 2] & UByte.MAX_VALUE) << i4;
            i3 += 4;
            this.A3 |= (bArr[i5] & UByte.MAX_VALUE) << i4;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Rijndael";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.workingKey = generateWorkingKey(key);
        this.forEncryption = z;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length * 8, cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Rijndael engine not initialised");
        }
        int i3 = this.BC;
        if ((i3 / 2) + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if ((i3 / 2) + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.forEncryption;
        unpackBlock(bArr, i);
        long[][] jArr = this.workingKey;
        if (z) {
            encryptBlock(jArr);
        } else {
            decryptBlock(jArr);
        }
        packBlock(bArr2, i2);
        return this.BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
