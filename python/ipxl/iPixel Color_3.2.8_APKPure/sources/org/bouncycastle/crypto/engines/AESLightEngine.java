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
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;
    private static final byte[] S = {99, JSONB.Constants.BC_STR_UTF16LE, 119, JSONB.Constants.BC_STR_UTF16, -14, 107, 111, -59, JSONB.Constants.BC_INT32_BYTE_MIN, 1, 103, 43, -2, JSONB.Constants.BC_INT64_BYTE_MAX, JSONB.Constants.BC_TIMESTAMP_MILLIS, 118, -54, -126, -55, JSONB.Constants.BC_STR_UTF16BE, -6, 89, JSONB.Constants.BC_INT32_SHORT_MAX, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MINUTES, -44, -94, JSONB.Constants.BC_NULL, -100, JSONB.Constants.BC_ARRAY, 114, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_FLOAT, -3, JSONB.Constants.BC_REFERENCE, 38, 54, 63, -9, -52, 52, JSONB.Constants.BC_OBJECT_END, -27, -15, 113, JSONB.Constants.BC_INT64_NUM_MIN, 49, 21, 4, JSONB.Constants.BC_INT64_SHORT_MAX, 35, -61, 24, -106, 5, -102, 7, 18, ByteCompanionObject.MIN_VALUE, -30, -21, 39, JSONB.Constants.BC_DOUBLE_NUM_0, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, JSONB.Constants.BC_DOUBLE_NUM_1, 41, -29, JSONB.Constants.BC_INT32_NUM_MAX, -124, 83, -47, 0, -19, 32, -4, JSONB.Constants.BC_TRUE, 91, 106, -53, JSONB.Constants.BC_INT64, 57, JSONB.Constants.BC_STR_ASCII_FIX_1, 76, 88, -49, JSONB.Constants.BC_INT64_BYTE_ZERO, -17, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -5, 67, JSONB.Constants.BC_STR_ASCII_FIX_4, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, JSONB.Constants.BC_LOCAL_DATETIME, 81, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_INT32_SHORT_MIN, -113, JSONB.Constants.BC_TYPED_ANY, -99, JSONB.Constants.BC_INT32_BYTE_ZERO, -11, -68, JSONB.Constants.BC_FLOAT_INT, -38, 33, JSONB.Constants.BC_INT32_NUM_16, -1, -13, -46, -51, 12, 19, -20, 95, -105, JSONB.Constants.BC_INT32_SHORT_ZERO, 23, JSONB.Constants.BC_INT64_SHORT_ZERO, JSONB.Constants.BC_LOCAL_TIME, JSONB.Constants.BC_STR_GB18030, Base64.padSymbol, 100, 93, 25, 115, 96, -127, 79, JL_Constant.PREFIX_FLAG_SECOND, 34, 42, JSONB.Constants.BC_CHAR, -120, 70, -18, JSONB.Constants.BC_DECIMAL_LONG, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, JSONB.Constants.BC_TIMESTAMP_SECONDS, 98, JSONB.Constants.BC_BINARY, -107, -28, JSONB.Constants.BC_STR_ASCII, -25, JSONB.Constants.BC_INT64_BYTE_MIN, 55, JSONB.Constants.BC_STR_ASCII_FIX_36, -115, -43, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_LOCAL_DATE, 108, 86, -12, -22, 101, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_TIMESTAMP, 8, -70, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 37, 46, 28, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_DOUBLE_LONG, -58, -24, -35, 116, 31, 75, JSONB.Constants.BC_INT8, -117, -118, 112, 62, JSONB.Constants.BC_DOUBLE, 102, JSONB.Constants.BC_INT32, 3, -10, 14, 97, 53, 87, JSONB.Constants.BC_DECIMAL, -122, -63, 29, -98, -31, -8, -104, 17, JSONB.Constants.BC_STR_ASCII_FIX_32, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, JSONB.Constants.BC_INT64_INT, -26, 66, 104, 65, -103, 45, 15, JSONB.Constants.BC_FALSE, 84, JSONB.Constants.BC_BIGINT, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER};
    private static final byte[] Si = {82, 9, 106, -43, JSONB.Constants.BC_INT32_BYTE_MIN, 54, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_ARRAY_FIX_MAX, -98, -127, -13, JSONB.Constants.BC_INT64_BYTE_MAX, -5, JSONB.Constants.BC_STR_UTF16LE, -29, 57, -126, -101, JSONB.Constants.BC_INT32_NUM_MAX, -1, -121, 52, -114, 67, JSONB.Constants.BC_INT32_SHORT_ZERO, JSONB.Constants.BC_INT64_SHORT_ZERO, -34, -23, -53, 84, JSONB.Constants.BC_STR_UTF16, -108, 50, JSONB.Constants.BC_OBJECT, -62, 35, Base64.padSymbol, -18, 76, -107, 11, 66, -6, -61, JSONB.Constants.BC_STR_ASCII_FIX_5, 8, 46, -95, 102, 40, -39, 36, JSONB.Constants.BC_DOUBLE_NUM_0, 118, 91, -94, 73, JSONB.Constants.BC_STR_ASCII_FIX_36, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -44, JSONB.Constants.BC_ARRAY, 92, -52, 93, 101, JSONB.Constants.BC_FLOAT_INT, JSONB.Constants.BC_TYPED_ANY, 108, 112, JSONB.Constants.BC_INT32, 80, -3, -19, JSONB.Constants.BC_DECIMAL, -38, 94, 21, 70, 87, JSONB.Constants.BC_LOCAL_TIME, -115, -99, -124, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MILLIS, 0, -116, -68, -45, 10, -9, -28, 88, 5, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_DOUBLE_NUM_1, 69, 6, JSONB.Constants.BC_INT64_BYTE_ZERO, 44, 30, -113, -54, 63, 15, 2, -63, JSONB.Constants.BC_NULL, JSONB.Constants.BC_INT8, 3, 1, 19, -118, 107, 58, JSONB.Constants.BC_BINARY, 17, 65, 79, 103, JL_Constant.PREFIX_FLAG_SECOND, -22, -105, -14, -49, -50, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_DOUBLE_LONG, -26, 115, -106, JSONB.Constants.BC_TIMESTAMP_SECONDS, 116, 34, -25, JSONB.Constants.BC_TIMESTAMP_MINUTES, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, JSONB.Constants.BC_INT32_SHORT_MAX, -15, 26, 113, 29, 41, -59, -119, 111, JSONB.Constants.BC_FLOAT, 98, 14, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 24, JSONB.Constants.BC_INT64, 27, -4, 86, 62, 75, -58, -46, JSONB.Constants.BC_STR_ASCII, 32, -102, -37, JSONB.Constants.BC_INT64_SHORT_MIN, -2, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -51, 90, -12, 31, -35, JSONB.Constants.BC_LOCAL_DATETIME, 51, -120, 7, JSONB.Constants.BC_INT64_SHORT_MAX, 49, JSONB.Constants.BC_TRUE, 18, JSONB.Constants.BC_INT32_NUM_16, 89, 39, ByteCompanionObject.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, JSONB.Constants.BC_LOCAL_DATE, 25, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_STR_ASCII_FIX_1, 13, 45, -27, JSONB.Constants.BC_STR_UTF8, -97, JSONB.Constants.BC_REFERENCE, -55, -100, -17, -96, -32, 59, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_TIMESTAMP, 42, -11, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_INT64_BYTE_MIN, -21, JSONB.Constants.BC_BIGINT, 60, -125, 83, -103, 97, 23, 43, 4, JSONB.Constants.BC_STR_GB18030, -70, 119, -42, 38, -31, JSONB.Constants.BC_STR_ASCII_FIX_32, 20, 99, 85, 33, 12, JSONB.Constants.BC_STR_UTF16BE};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, Opcodes.LOOKUPSWITCH, 77, Opcodes.IFNE, 47, 94, 188, 99, Opcodes.IFNULL, Opcodes.DCMPL, 53, 106, Command.CMD_GET_DEV_MD5, Opcodes.PUTSTATIC, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, Opcodes.I2B};

    public AESLightEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity()));
    }

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = (m5 & i) << 2;
        int i3 = i & m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private int bitsOfSecurity() {
        if (this.WorkingKey == null) {
            return 256;
        }
        return (r0.length - 7) << 5;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int i3 = this.ROUNDS;
        int[] iArr2 = iArr[i3];
        char c = 0;
        int i4 = littleEndianToInt ^ iArr2[0];
        int i5 = 1;
        int i6 = littleEndianToInt2 ^ iArr2[1];
        int i7 = littleEndianToInt3 ^ iArr2[2];
        int i8 = i3 - 1;
        int i9 = littleEndianToInt4 ^ iArr2[3];
        while (i8 > i5) {
            byte[] bArr3 = Si;
            int inv_mcol = inv_mcol((((bArr3[i4 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i9 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i7 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i6 >> 24) & 255] << 24)) ^ iArr[i8][c];
            int inv_mcol2 = inv_mcol((((bArr3[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i9 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i7 >> 24) & 255] << 24)) ^ iArr[i8][i5];
            char c2 = c;
            int inv_mcol3 = inv_mcol(((((bArr3[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8) ^ (bArr3[i7 & 255] & UByte.MAX_VALUE)) ^ ((bArr3[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i9 >> 24) & 255] << 24)) ^ iArr[i8][2];
            int inv_mcol4 = inv_mcol((((bArr3[i9 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i7 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i4 >> 24) & 255] << 24));
            int i10 = i8 - 1;
            int i11 = inv_mcol4 ^ iArr[i8][3];
            int inv_mcol5 = inv_mcol((((bArr3[inv_mcol & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i11 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol2 >> 24) & 255] << 24)) ^ iArr[i10][c2];
            int inv_mcol6 = inv_mcol((((bArr3[inv_mcol2 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i11 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol3 >> 24) & 255] << 24)) ^ iArr[i10][i5];
            int i12 = i5;
            int inv_mcol7 = inv_mcol(((((bArr3[(inv_mcol2 >> 8) & 255] & UByte.MAX_VALUE) << 8) ^ (bArr3[inv_mcol3 & 255] & UByte.MAX_VALUE)) ^ ((bArr3[(inv_mcol >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i11 >> 24) & 255] << 24)) ^ iArr[i10][2];
            i8 -= 2;
            i9 = inv_mcol((((bArr3[i11 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(inv_mcol3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(inv_mcol2 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(inv_mcol >> 24) & 255] << 24)) ^ iArr[i10][3];
            c = c2;
            i4 = inv_mcol5;
            i6 = inv_mcol6;
            i7 = inv_mcol7;
            i5 = i12;
        }
        char c3 = c;
        int i13 = i5;
        byte[] bArr4 = Si;
        int inv_mcol8 = inv_mcol((((bArr4[i4 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i9 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i7 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i6 >> 24) & 255] << 24)) ^ iArr[i8][c3];
        int inv_mcol9 = inv_mcol((((bArr4[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i4 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i9 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i7 >> 24) & 255] << 24)) ^ iArr[i8][i13];
        int inv_mcol10 = inv_mcol((((bArr4[i7 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i4 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i9 >> 24) & 255] << 24)) ^ iArr[i8][2];
        int inv_mcol11 = inv_mcol((((bArr4[i9 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i7 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i4 >> 24) & 255] << 24)) ^ iArr[i8][3];
        int i14 = (((bArr4[inv_mcol8 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(inv_mcol11 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(inv_mcol10 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(inv_mcol9 >> 24) & 255] << 24);
        int[] iArr3 = iArr[c3];
        int i15 = i14 ^ iArr3[c3];
        int i16 = ((((bArr4[inv_mcol9 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(inv_mcol8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(inv_mcol11 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(inv_mcol10 >> 24) & 255] << 24)) ^ iArr3[i13];
        int i17 = ((((bArr4[inv_mcol10 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(inv_mcol9 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(inv_mcol8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(inv_mcol11 >> 24) & 255] << 24)) ^ iArr3[2];
        int i18 = ((((bArr4[inv_mcol11 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(inv_mcol10 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(inv_mcol9 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(inv_mcol8 >> 24) & 255] << 24)) ^ iArr3[3];
        Pack.intToLittleEndian(i15, bArr2, i2);
        Pack.intToLittleEndian(i16, bArr2, i2 + 4);
        Pack.intToLittleEndian(i17, bArr2, i2 + 8);
        Pack.intToLittleEndian(i18, bArr2, i2 + 12);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        char c = 0;
        int[] iArr2 = iArr[0];
        int i3 = littleEndianToInt ^ iArr2[0];
        int i4 = 1;
        int i5 = littleEndianToInt2 ^ iArr2[1];
        int i6 = littleEndianToInt3 ^ iArr2[2];
        int i7 = littleEndianToInt4 ^ iArr2[3];
        int i8 = 1;
        while (i8 < this.ROUNDS - i4) {
            byte[] bArr3 = S;
            int mcol = mcol((((bArr3[i3 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i7 >> 24) & 255] << 24)) ^ iArr[i8][c];
            int mcol2 = mcol((((bArr3[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i7 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i3 >> 24) & 255] << 24)) ^ iArr[i8][i4];
            char c2 = c;
            int mcol3 = mcol(((((bArr3[(i7 >> 8) & 255] & UByte.MAX_VALUE) << 8) ^ (bArr3[i6 & 255] & UByte.MAX_VALUE)) ^ ((bArr3[(i3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i5 >> 24) & 255] << 24)) ^ iArr[i8][2];
            int mcol4 = mcol((((bArr3[i7 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(i3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i6 >> 24) & 255] << 24));
            int i9 = i8 + 1;
            int i10 = mcol4 ^ iArr[i8][3];
            int mcol5 = mcol((((bArr3[mcol & 255] & UByte.MAX_VALUE) ^ ((bArr3[(mcol2 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(mcol3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(i10 >> 24) & 255] << 24)) ^ iArr[i9][c2];
            int mcol6 = mcol((((bArr3[mcol2 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(mcol3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(i10 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(mcol >> 24) & 255] << 24)) ^ iArr[i9][i4];
            int i11 = i4;
            int mcol7 = mcol(((((bArr3[(i10 >> 8) & 255] & UByte.MAX_VALUE) << 8) ^ (bArr3[mcol3 & 255] & UByte.MAX_VALUE)) ^ ((bArr3[(mcol >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(mcol2 >> 24) & 255] << 24)) ^ iArr[i9][2];
            i8 += 2;
            i7 = mcol((((bArr3[i10 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(mcol >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(mcol2 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(mcol3 >> 24) & 255] << 24)) ^ iArr[i9][3];
            c = c2;
            i3 = mcol5;
            i5 = mcol6;
            i6 = mcol7;
            i4 = i11;
        }
        char c3 = c;
        int i12 = i4;
        byte[] bArr4 = S;
        int mcol8 = mcol((((bArr4[i3 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i5 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i6 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i7 >> 24) & 255] << 24)) ^ iArr[i8][c3];
        int mcol9 = mcol((((bArr4[i5 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i6 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i7 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i3 >> 24) & 255] << 24)) ^ iArr[i8][i12];
        int mcol10 = mcol((((bArr4[i6 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i7 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i3 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i5 >> 24) & 255] << 24)) ^ iArr[i8][2];
        int mcol11 = mcol((((bArr4[i7 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(i3 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(i5 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(i6 >> 24) & 255] << 24)) ^ iArr[i8][3];
        int i13 = (((bArr4[mcol8 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(mcol9 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(mcol10 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(mcol11 >> 24) & 255] << 24);
        int[] iArr3 = iArr[i8 + 1];
        int i14 = i13 ^ iArr3[c3];
        int i15 = ((((bArr4[mcol9 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(mcol10 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(mcol11 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(mcol8 >> 24) & 255] << 24)) ^ iArr3[i12];
        int i16 = iArr3[2] ^ ((((bArr4[mcol10 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(mcol11 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(mcol8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(mcol9 >> 24) & 255] << 24));
        int i17 = ((((bArr4[mcol11 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(mcol8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(mcol9 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(mcol10 >> 24) & 255] << 24)) ^ iArr3[3];
        Pack.intToLittleEndian(i14, bArr2, i2);
        Pack.intToLittleEndian(i15, bArr2, i2 + 4);
        Pack.intToLittleEndian(i16, bArr2, i2 + 8);
        Pack.intToLittleEndian(i17, bArr2, i2 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int i;
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i2 = length >>> 2;
        this.ROUNDS = i2 + 6;
        int i3 = 1;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2 + 7, 4);
        char c = 3;
        if (i2 == 4) {
            i = 1;
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            for (int i4 = 1; i4 <= 10; i4++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[i4 - 1];
                int[] iArr2 = iArr[i4];
                iArr2[0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr2[1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr2[2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr2[3] = littleEndianToInt4;
            }
        } else if (i2 == 6) {
            i = 1;
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int i5 = 1;
            int i6 = 1;
            while (true) {
                int[] iArr3 = iArr[i5];
                iArr3[0] = littleEndianToInt9;
                iArr3[1] = littleEndianToInt10;
                int subWord = littleEndianToInt5 ^ (subWord(shift(littleEndianToInt10, 8)) ^ i6);
                int[] iArr4 = iArr[i5];
                iArr4[2] = subWord;
                int i7 = littleEndianToInt6 ^ subWord;
                iArr4[3] = i7;
                int i8 = littleEndianToInt7 ^ i7;
                int[] iArr5 = iArr[i5 + 1];
                iArr5[0] = i8;
                int i9 = littleEndianToInt8 ^ i8;
                iArr5[1] = i9;
                int i10 = littleEndianToInt9 ^ i9;
                iArr5[2] = i10;
                int i11 = littleEndianToInt10 ^ i10;
                iArr5[3] = i11;
                int subWord2 = subWord(shift(i11, 8)) ^ (i6 << 1);
                i6 <<= 2;
                littleEndianToInt5 = subWord ^ subWord2;
                int[] iArr6 = iArr[i5 + 2];
                iArr6[0] = littleEndianToInt5;
                littleEndianToInt6 = i7 ^ littleEndianToInt5;
                iArr6[1] = littleEndianToInt6;
                littleEndianToInt7 = i8 ^ littleEndianToInt6;
                iArr6[2] = littleEndianToInt7;
                littleEndianToInt8 = i9 ^ littleEndianToInt7;
                iArr6[3] = littleEndianToInt8;
                i5 += 3;
                if (i5 >= 13) {
                    break;
                }
                littleEndianToInt9 = i10 ^ littleEndianToInt8;
                littleEndianToInt10 = i11 ^ littleEndianToInt9;
            }
        } else {
            if (i2 != 8) {
                throw new IllegalStateException("Should never get here");
            }
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i12 = 1;
            int i13 = 2;
            while (true) {
                int subWord3 = subWord(shift(littleEndianToInt18, 8)) ^ i12;
                i12 <<= i3;
                littleEndianToInt11 ^= subWord3;
                int[] iArr7 = iArr[i13];
                iArr7[0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr7[i3] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr7[2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr7[c] = littleEndianToInt14;
                i = i3;
                int i14 = i13 + 1;
                char c2 = c;
                if (i14 >= 15) {
                    break;
                }
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                int[] iArr8 = iArr[i14];
                iArr8[0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr8[i] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr8[2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr8[c2] = littleEndianToInt18;
                i13 += 2;
                i3 = i;
                c = c2;
            }
        }
        if (!z) {
            for (int i15 = i; i15 < this.ROUNDS; i15++) {
                for (int i16 = 0; i16 < 4; i16++) {
                    int[] iArr9 = iArr[i15];
                    iArr9[i16] = inv_mcol(iArr9[i16]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int mcol(int i) {
        int shift = shift(i, 8);
        int i2 = i ^ shift;
        return FFmulX(i2) ^ (shift ^ shift(i2, 16));
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & UByte.MAX_VALUE) | ((bArr[(i >> 8) & 255] & UByte.MAX_VALUE) << 8) | ((bArr[(i >> 16) & 255] & UByte.MAX_VALUE) << 16);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
        this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
        this.forEncryption = z;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 > bArr2.length - 16) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            encryptBlock(bArr, i, bArr2, i2, iArr);
        } else {
            decryptBlock(bArr, i, bArr2, i2, iArr);
        }
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
