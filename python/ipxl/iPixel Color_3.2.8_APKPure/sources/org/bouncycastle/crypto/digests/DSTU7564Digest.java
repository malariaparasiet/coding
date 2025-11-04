package org.bouncycastle.crypto.digests;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class DSTU7564Digest implements ExtendedDigest, Memoable {
    private static final int NB_1024 = 16;
    private static final int NB_512 = 8;
    private static final int NR_1024 = 14;
    private static final int NR_512 = 10;
    private static final byte[] S0 = {JSONB.Constants.BC_LOCAL_DATETIME, 67, 95, 6, 107, 117, 108, 89, 113, -33, -121, -107, 23, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_INT64_NUM_MIN, 9, JSONB.Constants.BC_STR_ASCII_FIX_36, -13, 29, -53, -55, JSONB.Constants.BC_STR_ASCII_FIX_4, 44, JSONB.Constants.BC_NULL, JSONB.Constants.BC_STR_ASCII, -32, -105, -3, 111, 75, 69, 57, 62, -35, JSONB.Constants.BC_ARRAY_FIX_MAX, 79, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_FLOAT_INT, -102, 14, 31, JSONB.Constants.BC_INT64_INT, 21, -31, 73, -46, JSONB.Constants.BC_REFERENCE, -58, JSONB.Constants.BC_TYPED_ANY, 114, -98, 97, -47, 99, -6, -18, -12, 25, -43, JSONB.Constants.BC_TIMESTAMP_MINUTES, 88, JSONB.Constants.BC_ARRAY, JSONB.Constants.BC_BIGINT, -95, JL_Constant.PREFIX_FLAG_SECOND, -14, -125, 55, 66, -28, JSONB.Constants.BC_STR_UTF8, 50, -100, -52, JSONB.Constants.BC_TIMESTAMP_MILLIS, JSONB.Constants.BC_STR_ASCII_FIX_1, -113, 110, 4, 39, 46, -25, -30, 90, -106, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 35, 43, -62, 101, 102, 15, -68, JSONB.Constants.BC_LOCAL_DATE, JSONB.Constants.BC_INT32_SHORT_MAX, 65, 52, JSONB.Constants.BC_INT32, -4, JSONB.Constants.BC_FLOAT, 106, -120, JSONB.Constants.BC_OBJECT_END, 83, -122, -7, 91, -37, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_STR_UTF16, -61, 30, 34, 51, 36, 40, 54, JSONB.Constants.BC_INT64_SHORT_MAX, JSONB.Constants.BC_DOUBLE_NUM_0, 59, -114, 119, -70, -11, 20, -97, 8, 85, -101, 76, -2, 96, 92, -38, 24, 70, -51, JSONB.Constants.BC_STR_UTF16BE, 33, JSONB.Constants.BC_FALSE, 63, 27, -119, -1, -21, -124, JSONB.Constants.BC_STR_ASCII_FIX_32, 58, -99, JSONB.Constants.BC_INT64_BYTE_MAX, -45, 112, 103, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_DOUBLE, -34, 93, JSONB.Constants.BC_INT32_BYTE_MIN, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 17, 1, -27, 0, 104, -104, -96, -59, 2, JSONB.Constants.BC_OBJECT, 116, 45, 11, -94, 118, JSONB.Constants.BC_DOUBLE_NUM_1, JSONB.Constants.BC_INT64, -50, JSONB.Constants.BC_INT8, JSONB.Constants.BC_TIMESTAMP, -23, -118, 49, 28, -20, -15, -103, -108, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -10, 38, JSONB.Constants.BC_INT32_NUM_MAX, -17, -24, -116, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, 94, JSONB.Constants.BC_CHAR, 32, Base64.padSymbol, -126, -9, -22, 10, 13, JSONB.Constants.BC_STR_GB18030, -8, 80, 26, JSONB.Constants.BC_INT64_SHORT_ZERO, 7, 87, JSONB.Constants.BC_DECIMAL_LONG, 60, 98, -29, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_TIMESTAMP_SECONDS, 82, 100, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_INT64_BYTE_ZERO, -39, 19, 12, 18, 41, 81, JSONB.Constants.BC_DECIMAL, -49, -42, 115, -115, -127, 84, JSONB.Constants.BC_INT64_SHORT_MIN, -19, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_INT32_SHORT_ZERO, JSONB.Constants.BC_LOCAL_TIME, 42, -123, 37, -26, -54, JSONB.Constants.BC_STR_UTF16LE, -117, 86, ByteCompanionObject.MIN_VALUE};
    private static final byte[] S1 = {-50, JSONB.Constants.BC_BIGINT, -21, JSONB.Constants.BC_TYPED_ANY, -22, -53, 19, -63, -23, 58, -42, JSONB.Constants.BC_DOUBLE_NUM_0, -46, JSONB.Constants.BC_CHAR, 23, -8, 66, 21, 86, JSONB.Constants.BC_DOUBLE_LONG, 101, 28, -120, 67, -59, 92, 54, -70, -11, 87, 103, -115, 49, -10, 100, 88, -98, -12, 34, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 117, 15, 2, JSONB.Constants.BC_TRUE, -33, JSONB.Constants.BC_STR_ASCII_FIX_36, 115, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_STR_UTF16LE, 38, 46, -9, 8, 93, JSONB.Constants.BC_INT32_SHORT_ZERO, 62, -97, 20, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_TIMESTAMP, 84, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_INT64_NUM_MIN, -68, 26, 107, JSONB.Constants.BC_STR_ASCII_FIX_32, -13, JSONB.Constants.BC_INT8, 51, JSONB.Constants.BC_TIMESTAMP_MILLIS, -6, -47, -101, 104, JSONB.Constants.BC_STR_ASCII_FIX_5, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -107, JSONB.Constants.BC_BINARY, -18, 76, 99, -114, 91, -52, 60, 25, -95, -127, 73, JSONB.Constants.BC_STR_UTF16, -39, 111, 55, 96, -54, -25, 43, JSONB.Constants.BC_INT32, -3, -106, 69, -4, 65, 18, 13, JSONB.Constants.BC_STR_ASCII, -27, -119, -116, -29, 32, JSONB.Constants.BC_INT32_BYTE_MIN, JL_Constant.PREFIX_FLAG_SECOND, JSONB.Constants.BC_FLOAT, 108, JSONB.Constants.BC_STR_ASCII_FIX_1, JSONB.Constants.BC_DOUBLE, 63, -105, -44, 98, 45, 6, JSONB.Constants.BC_ARRAY, JSONB.Constants.BC_OBJECT_END, -125, 95, 42, -38, -55, 0, JSONB.Constants.BC_STR_GB18030, -94, 85, JSONB.Constants.BC_INT64_INT, 17, -43, -100, -49, 14, 10, Base64.padSymbol, 81, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_REFERENCE, 27, -2, JSONB.Constants.BC_INT64_SHORT_ZERO, JSONB.Constants.BC_INT32_SHORT_MAX, 9, -122, 11, -113, -99, 106, 7, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_FALSE, -104, 24, 50, 113, 75, -17, 59, 112, -96, -28, JSONB.Constants.BC_INT32_SHORT_MIN, -1, -61, JSONB.Constants.BC_LOCAL_DATE, -26, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -7, -117, 70, ByteCompanionObject.MIN_VALUE, 30, JSONB.Constants.BC_INT32_BYTE_ZERO, -31, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_LOCAL_DATETIME, -32, 12, 35, 118, 29, 37, 36, 5, -15, 110, -108, 40, -102, -124, -24, JSONB.Constants.BC_ARRAY_FIX_MAX, 79, 119, -45, -123, -30, 82, -14, -126, 80, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_INT32_NUM_MAX, 116, 83, JSONB.Constants.BC_DOUBLE_NUM_1, 97, JSONB.Constants.BC_NULL, 57, 53, -34, -51, 31, -103, JSONB.Constants.BC_TIMESTAMP_SECONDS, JSONB.Constants.BC_TIMESTAMP_MINUTES, 114, 44, -35, JSONB.Constants.BC_INT64_BYTE_ZERO, -121, JSONB.Constants.BC_INT64, 94, JSONB.Constants.BC_OBJECT, -20, 4, -58, 3, 52, -5, -37, 89, JSONB.Constants.BC_FLOAT_INT, -62, 1, JSONB.Constants.BC_INT32_NUM_MIN, 90, -19, JSONB.Constants.BC_LOCAL_TIME, 102, 33, Byte.MAX_VALUE, -118, 39, JSONB.Constants.BC_INT64_SHORT_MAX, JSONB.Constants.BC_INT64_SHORT_MIN, 41, JSONB.Constants.BC_INT64_BYTE_MAX};
    private static final byte[] S2 = {JSONB.Constants.BC_REFERENCE, -39, -102, JSONB.Constants.BC_DOUBLE, -104, 34, 69, -4, -70, 106, -33, 2, -97, JL_Constant.PREFIX_FLAG_SECOND, 81, 89, JSONB.Constants.BC_STR_ASCII_FIX_1, 23, 43, -62, -108, -12, JSONB.Constants.BC_BIGINT, JSONB.Constants.BC_ARRAY_FIX_MAX, 98, -28, 113, -44, -51, 112, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -31, 73, 60, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_INT64_NUM_MIN, 92, -101, JSONB.Constants.BC_TIMESTAMP_MINUTES, -123, 83, -95, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_INT64_BYTE_MIN, 45, -32, -47, 114, JSONB.Constants.BC_OBJECT, 44, JSONB.Constants.BC_INT64_SHORT_ZERO, -29, 118, JSONB.Constants.BC_STR_ASCII_FIX_MAX, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_DOUBLE_LONG, 9, 59, 14, 65, 76, -34, JSONB.Constants.BC_DOUBLE_NUM_0, JSONB.Constants.BC_CHAR, 37, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_INT64_BYTE_MAX, 3, 17, 0, -61, 46, JSONB.Constants.BC_TYPED_ANY, -17, JSONB.Constants.BC_STR_ASCII_FIX_5, 18, -99, JSONB.Constants.BC_STR_UTF16BE, -53, 53, JSONB.Constants.BC_INT32_NUM_16, -43, 79, -98, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_LOCAL_DATE, 85, -58, JSONB.Constants.BC_INT64_BYTE_ZERO, JSONB.Constants.BC_STR_UTF16, 24, -105, -45, 54, -26, JSONB.Constants.BC_INT32, 86, -127, -113, 119, -52, -100, JSONB.Constants.BC_DECIMAL, -30, JSONB.Constants.BC_TIMESTAMP_SECONDS, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_INT32_NUM_MAX, 21, JSONB.Constants.BC_ARRAY, JSONB.Constants.BC_STR_UTF16LE, -38, JSONB.Constants.BC_INT32_BYTE_ZERO, 30, 11, 5, -42, 20, 110, 108, JSONB.Constants.BC_STR_GB18030, 102, -3, JSONB.Constants.BC_TRUE, -27, 96, JSONB.Constants.BC_NULL, 94, 51, -121, -55, JSONB.Constants.BC_INT32_NUM_MIN, 93, JSONB.Constants.BC_STR_ASCII_FIX_36, 63, -120, -115, JSONB.Constants.BC_INT64_SHORT_MAX, -9, 29, -23, -20, -19, ByteCompanionObject.MIN_VALUE, 41, 39, -49, -103, JSONB.Constants.BC_LOCAL_DATETIME, 80, 15, 55, 36, 40, JSONB.Constants.BC_INT32_BYTE_MIN, -107, -46, 62, 91, JSONB.Constants.BC_INT32_SHORT_MIN, -125, JSONB.Constants.BC_DOUBLE_NUM_1, JSONB.Constants.BC_STR_ASCII_FIX_32, 87, 31, 7, 28, -118, -68, 32, -21, -50, -114, JSONB.Constants.BC_TIMESTAMP_MILLIS, -18, 49, -94, 115, -7, -54, 58, 26, -5, 13, -63, -2, -6, -14, 111, JSONB.Constants.BC_INT8, -106, -35, 67, 82, JSONB.Constants.BC_FLOAT_INT, 8, -13, JSONB.Constants.BC_TIMESTAMP, JSONB.Constants.BC_INT64, 25, -119, 50, 38, JSONB.Constants.BC_FALSE, -22, 75, 100, -124, -126, 107, -11, JSONB.Constants.BC_STR_ASCII, JSONB.Constants.BC_INT64_INT, 1, 95, 117, 99, 27, 35, Base64.padSymbol, 104, 42, 101, -24, JSONB.Constants.BC_BINARY, -10, -1, 19, 88, -15, JSONB.Constants.BC_INT32_SHORT_MAX, 10, Byte.MAX_VALUE, -59, JSONB.Constants.BC_LOCAL_TIME, -25, 97, 90, 6, 70, JSONB.Constants.BC_INT32_SHORT_ZERO, 66, 4, -96, -37, 57, -122, 84, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -116, 52, 33, -117, -8, 12, 116, 103};
    private static final byte[] S3 = {104, -115, -54, JSONB.Constants.BC_STR_ASCII_FIX_4, 115, 75, JSONB.Constants.BC_STR_ASCII_FIX_5, 42, -44, 82, 38, JSONB.Constants.BC_DOUBLE_NUM_1, 84, 30, 25, 31, 34, 3, 70, Base64.padSymbol, 45, JSONB.Constants.BC_STR_ASCII_FIX_1, 83, -125, 19, -118, JSONB.Constants.BC_FLOAT, -43, 37, JSONB.Constants.BC_STR_ASCII, -11, JSONB.Constants.BC_INT8, 88, JSONB.Constants.BC_INT32_NUM_MAX, 13, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 60, 102, 112, 93, -13, 69, JSONB.Constants.BC_INT32_SHORT_MIN, -52, -24, -108, 86, 8, -50, 26, 58, -46, -31, -33, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_INT32_BYTE_ZERO, 110, 14, -27, -12, -7, -122, -23, 79, -42, -123, 35, -49, 50, -103, 49, 20, JSONB.Constants.BC_TIMESTAMP, -18, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_INT32, -45, JSONB.Constants.BC_INT32_BYTE_MIN, -95, JSONB.Constants.BC_TYPED_ANY, 65, JSONB.Constants.BC_TRUE, 24, JSONB.Constants.BC_INT64_SHORT_ZERO, 44, 113, 114, JSONB.Constants.BC_INT32_SHORT_ZERO, 21, -3, 55, JSONB.Constants.BC_INT64, 95, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -101, -120, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MILLIS, -119, -100, -6, 96, -22, -68, 98, 12, 36, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_LOCAL_DATETIME, -20, 103, 32, -37, JSONB.Constants.BC_STR_UTF16LE, 40, -35, JSONB.Constants.BC_TIMESTAMP_SECONDS, 91, 52, JSONB.Constants.BC_STR_GB18030, JSONB.Constants.BC_INT32_NUM_16, -15, JSONB.Constants.BC_STR_UTF16, -113, 99, -96, 5, -102, 67, 119, 33, JSONB.Constants.BC_INT64_INT, 39, 9, -61, -97, JSONB.Constants.BC_FLOAT_INT, JSONB.Constants.BC_INT64_BYTE_MAX, 41, -62, -21, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_ARRAY, -117, -116, 29, -5, -1, -63, JSONB.Constants.BC_DOUBLE_NUM_0, -105, 46, -8, 101, -10, 117, 7, 4, 73, 51, -28, -39, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_INT64_BYTE_ZERO, 66, JSONB.Constants.BC_INT64_SHORT_MAX, 108, JSONB.Constants.BC_CHAR, 0, -114, 111, 80, 1, -59, -38, JSONB.Constants.BC_INT32_SHORT_MAX, 63, -51, JSONB.Constants.BC_STR_ASCII_FIX_32, -94, -30, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_LOCAL_TIME, -58, JSONB.Constants.BC_REFERENCE, 15, 10, 6, -26, 43, -106, JSONB.Constants.BC_ARRAY_FIX_MAX, 28, JSONB.Constants.BC_NULL, 106, 18, -124, 57, -25, JSONB.Constants.BC_FALSE, -126, -9, -2, -99, -121, 92, -127, 53, -34, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_OBJECT_END, -4, ByteCompanionObject.MIN_VALUE, -17, -53, JSONB.Constants.BC_BIGINT, 107, 118, -70, 90, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 11, -107, -29, JSONB.Constants.BC_TIMESTAMP_MINUTES, 116, -104, 59, 54, 100, JSONB.Constants.BC_STR_ASCII_FIX_36, JL_Constant.PREFIX_FLAG_SECOND, JSONB.Constants.BC_INT32_NUM_MIN, 89, JSONB.Constants.BC_LOCAL_DATE, 76, 23, Byte.MAX_VALUE, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_DECIMAL_LONG, -55, 87, 27, -32, 97};
    private int blockSize;
    private byte[] buf;
    private int bufOff;
    private int columns;
    private int hashSize;
    private long inputBlocks;
    private final CryptoServicePurpose purpose;
    private int rounds;
    private long[] state;
    private long[] tempState1;
    private long[] tempState2;

    public DSTU7564Digest(int i) {
        this(i, CryptoServicePurpose.ANY);
    }

    public DSTU7564Digest(int i, CryptoServicePurpose cryptoServicePurpose) {
        int i2;
        this.purpose = cryptoServicePurpose;
        if (i != 256 && i != 384 && i != 512) {
            throw new IllegalArgumentException("Hash size is not recommended. Use 256/384/512 instead");
        }
        this.hashSize = i >>> 3;
        if (i > 256) {
            this.columns = 16;
            i2 = 14;
        } else {
            this.columns = 8;
            i2 = 10;
        }
        this.rounds = i2;
        int i3 = this.columns;
        int i4 = i3 << 3;
        this.blockSize = i4;
        long[] jArr = new long[i3];
        this.state = jArr;
        jArr[0] = i4;
        this.tempState1 = new long[i3];
        this.tempState2 = new long[i3];
        this.buf = new byte[i4];
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
    }

    public DSTU7564Digest(DSTU7564Digest dSTU7564Digest) {
        this.purpose = dSTU7564Digest.purpose;
        copyIn(dSTU7564Digest);
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
    }

    private void P(long[] jArr) {
        for (int i = 0; i < this.rounds; i++) {
            long j = i;
            for (int i2 = 0; i2 < this.columns; i2++) {
                jArr[i2] = jArr[i2] ^ j;
                j += 16;
            }
            shiftRows(jArr);
            subBytes(jArr);
            mixColumns(jArr);
        }
    }

    private void Q(long[] jArr) {
        for (int i = 0; i < this.rounds; i++) {
            long j = ((((this.columns - 1) << 4) ^ i) << 56) | 67818912035696883L;
            for (int i2 = 0; i2 < this.columns; i2++) {
                jArr[i2] = jArr[i2] + j;
                j -= 1152921504606846976L;
            }
            shiftRows(jArr);
            subBytes(jArr);
            mixColumns(jArr);
        }
    }

    private void copyIn(DSTU7564Digest dSTU7564Digest) {
        this.hashSize = dSTU7564Digest.hashSize;
        this.blockSize = dSTU7564Digest.blockSize;
        this.rounds = dSTU7564Digest.rounds;
        int i = this.columns;
        if (i <= 0 || i != dSTU7564Digest.columns) {
            this.columns = dSTU7564Digest.columns;
            this.state = Arrays.clone(dSTU7564Digest.state);
            int i2 = this.columns;
            this.tempState1 = new long[i2];
            this.tempState2 = new long[i2];
            this.buf = Arrays.clone(dSTU7564Digest.buf);
        } else {
            System.arraycopy(dSTU7564Digest.state, 0, this.state, 0, i);
            System.arraycopy(dSTU7564Digest.buf, 0, this.buf, 0, this.blockSize);
        }
        this.inputBlocks = dSTU7564Digest.inputBlocks;
        this.bufOff = dSTU7564Digest.bufOff;
    }

    private static long mixColumn(long j) {
        long j2 = ((9187201950435737471L & j) << 1) ^ (((j & (-9187201950435737472L)) >>> 7) * 29);
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(16, rotate)) ^ rotate(48, j);
        long j3 = (j ^ rotate2) ^ j2;
        return ((rotate(32, (((j3 & 4629771061636907072L) >>> 6) * 29) ^ (((((-9187201950435737472L) & j3) >>> 6) * 29) ^ ((4557430888798830399L & j3) << 2))) ^ rotate2) ^ rotate(40, j2)) ^ rotate(48, j2);
    }

    private void mixColumns(long[] jArr) {
        for (int i = 0; i < this.columns; i++) {
            jArr[i] = mixColumn(jArr[i]);
        }
    }

    private void processBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.columns; i2++) {
            long littleEndianToLong = Pack.littleEndianToLong(bArr, i);
            i += 8;
            this.tempState1[i2] = this.state[i2] ^ littleEndianToLong;
            this.tempState2[i2] = littleEndianToLong;
        }
        P(this.tempState1);
        Q(this.tempState2);
        for (int i3 = 0; i3 < this.columns; i3++) {
            long[] jArr = this.state;
            jArr[i3] = jArr[i3] ^ (this.tempState1[i3] ^ this.tempState2[i3]);
        }
    }

    private static long rotate(int i, long j) {
        return (j << (-i)) | (j >>> i);
    }

    private void shiftRows(long[] jArr) {
        int i = this.columns;
        if (i == 8) {
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = (j ^ j5) & (-4294967296L);
            long j10 = j ^ j9;
            long j11 = j5 ^ j9;
            long j12 = (j2 ^ j6) & 72057594021150720L;
            long j13 = j2 ^ j12;
            long j14 = j6 ^ j12;
            long j15 = (j3 ^ j7) & 281474976645120L;
            long j16 = j3 ^ j15;
            long j17 = j7 ^ j15;
            long j18 = (j4 ^ j8) & 1099511627520L;
            long j19 = j4 ^ j18;
            long j20 = j8 ^ j18;
            long j21 = (j10 ^ j16) & (-281470681808896L);
            long j22 = j10 ^ j21;
            long j23 = j16 ^ j21;
            long j24 = (j13 ^ j19) & 72056494543077120L;
            long j25 = j13 ^ j24;
            long j26 = j19 ^ j24;
            long j27 = (j11 ^ j17) & (-281470681808896L);
            long j28 = j11 ^ j27;
            long j29 = j17 ^ j27;
            long j30 = (j14 ^ j20) & 72056494543077120L;
            long j31 = j14 ^ j30;
            long j32 = j20 ^ j30;
            long j33 = (j22 ^ j25) & (-71777214294589696L);
            long j34 = j22 ^ j33;
            long j35 = j25 ^ j33;
            long j36 = (j23 ^ j26) & (-71777214294589696L);
            long j37 = j23 ^ j36;
            long j38 = j26 ^ j36;
            long j39 = (j28 ^ j31) & (-71777214294589696L);
            long j40 = (j29 ^ j32) & (-71777214294589696L);
            jArr[0] = j34;
            jArr[1] = j35;
            jArr[2] = j37;
            jArr[3] = j38;
            jArr[4] = j28 ^ j39;
            jArr[5] = j31 ^ j39;
            jArr[6] = j29 ^ j40;
            jArr[7] = j32 ^ j40;
            return;
        }
        if (i != 16) {
            throw new IllegalStateException("unsupported state size: only 512/1024 are allowed");
        }
        long j41 = jArr[0];
        long j42 = jArr[1];
        long j43 = jArr[2];
        long j44 = jArr[3];
        long j45 = jArr[4];
        long j46 = jArr[5];
        long j47 = jArr[6];
        long j48 = jArr[7];
        long j49 = jArr[8];
        long j50 = jArr[9];
        long j51 = jArr[10];
        long j52 = jArr[11];
        long j53 = jArr[12];
        long j54 = jArr[13];
        long j55 = jArr[14];
        long j56 = jArr[15];
        long j57 = (j41 ^ j49) & (-72057594037927936L);
        long j58 = j41 ^ j57;
        long j59 = j49 ^ j57;
        long j60 = (j42 ^ j50) & (-72057594037927936L);
        long j61 = j42 ^ j60;
        long j62 = j50 ^ j60;
        long j63 = (j43 ^ j51) & (-281474976710656L);
        long j64 = j43 ^ j63;
        long j65 = j51 ^ j63;
        long j66 = (j44 ^ j52) & (-1099511627776L);
        long j67 = j44 ^ j66;
        long j68 = j52 ^ j66;
        long j69 = (j45 ^ j53) & (-4294967296L);
        long j70 = j45 ^ j69;
        long j71 = j53 ^ j69;
        long j72 = (j46 ^ j54) & 72057594021150720L;
        long j73 = j46 ^ j72;
        long j74 = j54 ^ j72;
        long j75 = (j47 ^ j55) & 72057594037862400L;
        long j76 = j47 ^ j75;
        long j77 = j55 ^ j75;
        long j78 = (j48 ^ j56) & 72057594037927680L;
        long j79 = j48 ^ j78;
        long j80 = j56 ^ j78;
        long j81 = (j58 ^ j70) & 72057589742960640L;
        long j82 = j58 ^ j81;
        long j83 = j70 ^ j81;
        long j84 = (j61 ^ j73) & (-16777216);
        long j85 = j61 ^ j84;
        long j86 = j73 ^ j84;
        long j87 = (j64 ^ j76) & (-71776119061282816L);
        long j88 = j64 ^ j87;
        long j89 = j76 ^ j87;
        long j90 = (j67 ^ j79) & (-72056494526300416L);
        long j91 = j67 ^ j90;
        long j92 = j79 ^ j90;
        long j93 = (j59 ^ j71) & 72057589742960640L;
        long j94 = j59 ^ j93;
        long j95 = j71 ^ j93;
        long j96 = (j62 ^ j74) & (-16777216);
        long j97 = j62 ^ j96;
        long j98 = j74 ^ j96;
        long j99 = (j65 ^ j77) & (-71776119061282816L);
        long j100 = j65 ^ j99;
        long j101 = j77 ^ j99;
        long j102 = (j68 ^ j80) & (-72056494526300416L);
        long j103 = j68 ^ j102;
        long j104 = j80 ^ j102;
        long j105 = (j82 ^ j88) & (-281470681808896L);
        long j106 = j82 ^ j105;
        long j107 = j88 ^ j105;
        long j108 = (j85 ^ j91) & 72056494543077120L;
        long j109 = j85 ^ j108;
        long j110 = j91 ^ j108;
        long j111 = (j83 ^ j89) & (-281470681808896L);
        long j112 = j83 ^ j111;
        long j113 = j89 ^ j111;
        long j114 = (j86 ^ j92) & 72056494543077120L;
        long j115 = j86 ^ j114;
        long j116 = j92 ^ j114;
        long j117 = (j94 ^ j100) & (-281470681808896L);
        long j118 = j94 ^ j117;
        long j119 = j100 ^ j117;
        long j120 = (j97 ^ j103) & 72056494543077120L;
        long j121 = j97 ^ j120;
        long j122 = j103 ^ j120;
        long j123 = (j95 ^ j101) & (-281470681808896L);
        long j124 = j95 ^ j123;
        long j125 = j101 ^ j123;
        long j126 = (j98 ^ j104) & 72056494543077120L;
        long j127 = j98 ^ j126;
        long j128 = j104 ^ j126;
        long j129 = (j106 ^ j109) & (-71777214294589696L);
        long j130 = j106 ^ j129;
        long j131 = j109 ^ j129;
        long j132 = (j107 ^ j110) & (-71777214294589696L);
        long j133 = j107 ^ j132;
        long j134 = j110 ^ j132;
        long j135 = (j112 ^ j115) & (-71777214294589696L);
        long j136 = j112 ^ j135;
        long j137 = j115 ^ j135;
        long j138 = (j113 ^ j116) & (-71777214294589696L);
        long j139 = j113 ^ j138;
        long j140 = j116 ^ j138;
        long j141 = (j118 ^ j121) & (-71777214294589696L);
        long j142 = j118 ^ j141;
        long j143 = j121 ^ j141;
        long j144 = (j119 ^ j122) & (-71777214294589696L);
        long j145 = j119 ^ j144;
        long j146 = j122 ^ j144;
        long j147 = (j124 ^ j127) & (-71777214294589696L);
        long j148 = (j125 ^ j128) & (-71777214294589696L);
        jArr[0] = j130;
        jArr[1] = j131;
        jArr[2] = j133;
        jArr[3] = j134;
        jArr[4] = j136;
        jArr[5] = j137;
        jArr[6] = j139;
        jArr[7] = j140;
        jArr[8] = j142;
        jArr[9] = j143;
        jArr[10] = j145;
        jArr[11] = j146;
        jArr[12] = j124 ^ j147;
        jArr[13] = j127 ^ j147;
        jArr[14] = j125 ^ j148;
        jArr[15] = j128 ^ j148;
    }

    private void subBytes(long[] jArr) {
        for (int i = 0; i < this.columns; i++) {
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
            jArr[i] = (i4 & 4294967295L) | (((r10[i3 >>> 24] << 24) | (((b4 & UByte.MAX_VALUE) | ((b5 & UByte.MAX_VALUE) << 8)) | ((b6 & UByte.MAX_VALUE) << 16))) << 32);
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new DSTU7564Digest(this);
    }

    protected CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, 256, this.purpose);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4 = this.bufOff;
        byte[] bArr2 = this.buf;
        int i5 = i4 + 1;
        this.bufOff = i5;
        bArr2[i4] = ByteCompanionObject.MIN_VALUE;
        int i6 = this.blockSize - 12;
        int i7 = 0;
        if (i5 > i6) {
            while (true) {
                int i8 = this.bufOff;
                if (i8 >= this.blockSize) {
                    break;
                }
                byte[] bArr3 = this.buf;
                this.bufOff = i8 + 1;
                bArr3[i8] = 0;
            }
            this.bufOff = 0;
            processBlock(this.buf, 0);
        }
        while (true) {
            i2 = this.bufOff;
            if (i2 >= i6) {
                break;
            }
            byte[] bArr4 = this.buf;
            this.bufOff = i2 + 1;
            bArr4[i2] = 0;
        }
        long j = (((this.inputBlocks & 4294967295L) * this.blockSize) + i4) << 3;
        Pack.intToLittleEndian((int) j, this.buf, i2);
        int i9 = this.bufOff + 4;
        this.bufOff = i9;
        Pack.longToLittleEndian((j >>> 32) + (((this.inputBlocks >>> 32) * this.blockSize) << 3), this.buf, i9);
        processBlock(this.buf, 0);
        System.arraycopy(this.state, 0, this.tempState1, 0, this.columns);
        P(this.tempState1);
        while (true) {
            i3 = this.columns;
            if (i7 >= i3) {
                break;
            }
            long[] jArr = this.state;
            jArr[i7] = jArr[i7] ^ this.tempState1[i7];
            i7++;
        }
        for (int i10 = i3 - (this.hashSize >>> 3); i10 < this.columns; i10++) {
            Pack.longToLittleEndian(this.state[i10], bArr, i);
            i += 8;
        }
        reset();
        return this.hashSize;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "DSTU7564";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.hashSize;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        Arrays.fill(this.state, 0L);
        this.state[0] = this.blockSize;
        this.inputBlocks = 0L;
        this.bufOff = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((DSTU7564Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.buf;
        int i = this.bufOff;
        int i2 = i + 1;
        this.bufOff = i2;
        bArr[i] = b;
        if (i2 == this.blockSize) {
            processBlock(bArr, 0);
            this.bufOff = 0;
            this.inputBlocks++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.bufOff != 0 && i2 > 0) {
            update(bArr[i]);
            i2--;
            i++;
        }
        if (i2 > 0) {
            while (i2 >= this.blockSize) {
                processBlock(bArr, i);
                int i3 = this.blockSize;
                i += i3;
                i2 -= i3;
                this.inputBlocks++;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i2--;
                i++;
            }
        }
    }
}
