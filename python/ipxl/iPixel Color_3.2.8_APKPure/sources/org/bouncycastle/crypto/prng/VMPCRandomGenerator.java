package org.bouncycastle.crypto.prng;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class VMPCRandomGenerator implements RandomGenerator {
    private byte n = 0;
    private byte[] P = {JSONB.Constants.BC_BIGINT, 44, 98, Byte.MAX_VALUE, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -44, 13, -127, -2, JSONB.Constants.BC_DOUBLE_NUM_0, -126, -53, -96, -95, 8, 24, 113, 86, -24, 73, 2, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_INT64_SHORT_ZERO, -34, 53, JSONB.Constants.BC_OBJECT_END, -20, ByteCompanionObject.MIN_VALUE, 18, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_STR_ASCII_FIX_32, -38, JSONB.Constants.BC_INT32_NUM_MAX, 117, -52, -94, 9, 54, 3, 97, 45, -3, -32, -35, 5, 67, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_TIMESTAMP_MINUTES, JSONB.Constants.BC_INT64_BYTE_MIN, -31, JSONB.Constants.BC_NULL, 87, -101, 76, JSONB.Constants.BC_INT64_NUM_MIN, 81, JSONB.Constants.BC_TIMESTAMP, 80, -123, 60, 10, -28, -13, -100, 38, 35, 83, -55, -125, -105, 70, JSONB.Constants.BC_TRUE, -103, 100, 49, 119, -43, 29, -42, JSONB.Constants.BC_STR_ASCII_FIX_MAX, JSONB.Constants.BC_INT8, 94, JSONB.Constants.BC_FALSE, -118, 34, JSONB.Constants.BC_INT32_BYTE_ZERO, -8, 104, 43, 42, -59, -45, -9, -68, 111, -33, 4, -27, -107, 62, 37, -122, JSONB.Constants.BC_OBJECT, 11, -113, -15, 36, 14, JSONB.Constants.BC_INT64_BYTE_MAX, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_DOUBLE_NUM_1, -49, JSONB.Constants.BC_STR_GB18030, 6, 21, -102, JSONB.Constants.BC_STR_ASCII_FIX_4, 28, JSONB.Constants.BC_ARRAY_FIX_MAX, -37, 50, JSONB.Constants.BC_TYPED_ANY, 88, 17, 39, -12, 89, JSONB.Constants.BC_INT64_BYTE_ZERO, JSONB.Constants.BC_STR_ASCII_FIX_5, 106, 23, 91, JSONB.Constants.BC_TIMESTAMP_SECONDS, -1, 7, JSONB.Constants.BC_INT64_SHORT_MIN, 101, JSONB.Constants.BC_STR_ASCII, -4, JSONB.Constants.BC_INT64_SHORT_MAX, -51, 118, 66, 93, -25, 58, 52, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_INT32_BYTE_MIN, 40, 15, 115, 1, -7, -47, -46, 25, -23, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_DECIMAL, 90, -19, 65, JSONB.Constants.BC_STR_ASCII_FIX_36, JSONB.Constants.BC_DOUBLE_LONG, -61, -98, JSONB.Constants.BC_INT64_INT, 99, -6, 31, 51, 96, JSONB.Constants.BC_INT32_SHORT_MAX, -119, JSONB.Constants.BC_INT32_NUM_MIN, -106, 26, 95, JSONB.Constants.BC_REFERENCE, Base64.padSymbol, 55, 75, -39, JSONB.Constants.BC_LOCAL_DATETIME, -63, 27, -10, 57, -117, JSONB.Constants.BC_FLOAT, 12, 32, -50, -120, 110, JSONB.Constants.BC_FLOAT_INT, 116, -114, -115, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 41, -14, -121, -11, -21, 112, -29, -5, 85, -97, -58, JSONB.Constants.BC_INT32_SHORT_ZERO, JSONB.Constants.BC_STR_ASCII_FIX_1, 69, JSONB.Constants.BC_STR_UTF16BE, -30, 107, 92, 108, 102, JSONB.Constants.BC_LOCAL_DATE, -116, -18, -124, 19, JSONB.Constants.BC_LOCAL_TIME, 30, -99, JL_Constant.PREFIX_FLAG_SECOND, 103, JSONB.Constants.BC_INT32, -70, 46, -26, JSONB.Constants.BC_ARRAY, JSONB.Constants.BC_TIMESTAMP_MILLIS, JSONB.Constants.BC_STR_UTF16LE, -108, 0, 33, -17, -22, JSONB.Constants.BC_INT64, -54, 114, 79, 82, -104, 63, -62, 20, JSONB.Constants.BC_STR_UTF16, 59, 84};
    private byte s = JSONB.Constants.BC_INT64;

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j) {
        addSeedMaterial(Pack.longToBigEndian(j));
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        for (byte b : bArr) {
            byte[] bArr2 = this.P;
            byte b2 = this.s;
            byte b3 = this.n;
            byte b4 = bArr2[(b2 + bArr2[b3 & UByte.MAX_VALUE] + b) & 255];
            this.s = b4;
            byte b5 = bArr2[b3 & UByte.MAX_VALUE];
            bArr2[b3 & UByte.MAX_VALUE] = bArr2[b4 & UByte.MAX_VALUE];
            bArr2[b4 & UByte.MAX_VALUE] = b5;
            this.n = (byte) ((b3 + 1) & 255);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int i, int i2) {
        synchronized (this.P) {
            int i3 = i2 + i;
            while (i != i3) {
                byte[] bArr2 = this.P;
                byte b = this.s;
                byte b2 = this.n;
                byte b3 = bArr2[(b + bArr2[b2 & UByte.MAX_VALUE]) & 255];
                this.s = b3;
                bArr[i] = bArr2[(bArr2[bArr2[b3 & UByte.MAX_VALUE] & UByte.MAX_VALUE] + 1) & 255];
                byte b4 = bArr2[b2 & UByte.MAX_VALUE];
                bArr2[b2 & UByte.MAX_VALUE] = bArr2[b3 & UByte.MAX_VALUE];
                bArr2[b3 & UByte.MAX_VALUE] = b4;
                this.n = (byte) ((b2 + 1) & 255);
                i++;
            }
        }
    }
}
