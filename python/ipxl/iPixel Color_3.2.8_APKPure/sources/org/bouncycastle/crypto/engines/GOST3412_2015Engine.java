package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class GOST3412_2015Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    private static final byte[] PI = {-4, -18, -35, 17, -49, 110, 49, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -5, JSONB.Constants.BC_INT64_SHORT_ZERO, -6, -38, 35, -59, 4, JSONB.Constants.BC_STR_ASCII_FIX_4, -23, 119, JSONB.Constants.BC_INT32_NUM_MIN, -37, JSONB.Constants.BC_REFERENCE, 46, -103, -70, 23, 54, -15, JSONB.Constants.BC_BIGINT, 20, -51, 95, -63, -7, 24, 101, 90, -30, 92, -17, 33, -127, 28, 60, 66, -117, 1, -114, 79, 5, -124, 2, JSONB.Constants.BC_TIMESTAMP, -29, 106, -113, -96, 6, 11, -19, -104, Byte.MAX_VALUE, -44, -45, 31, -21, 52, 44, 81, -22, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_INT32, JSONB.Constants.BC_TIMESTAMP_MILLIS, -14, 42, 104, -94, -3, 58, -50, -52, JSONB.Constants.BC_DOUBLE, 112, 14, 86, 8, 12, 118, 18, JSONB.Constants.BC_INT64_INT, 114, 19, JSONB.Constants.BC_INT32_SHORT_MAX, -100, JSONB.Constants.BC_FLOAT, 93, -121, 21, -95, -106, 41, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_STR_UTF16, -102, JSONB.Constants.BC_INT64_SHORT_MAX, -13, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 111, -99, -98, JSONB.Constants.BC_DOUBLE_NUM_0, JSONB.Constants.BC_TRUE, 50, 117, 25, Base64.padSymbol, -1, 53, -118, JSONB.Constants.BC_STR_GB18030, JSONB.Constants.BC_STR_ASCII_FIX_36, 84, -58, ByteCompanionObject.MIN_VALUE, -61, JSONB.Constants.BC_INT8, 13, 87, -33, -11, 36, JSONB.Constants.BC_LOCAL_DATE, 62, JSONB.Constants.BC_LOCAL_DATETIME, 67, -55, JSONB.Constants.BC_INT64_BYTE_MAX, JSONB.Constants.BC_STR_ASCII, -42, -10, JSONB.Constants.BC_STR_UTF16LE, 34, JSONB.Constants.BC_DECIMAL, 3, -32, 15, -20, -34, JSONB.Constants.BC_STR_UTF8, -108, JSONB.Constants.BC_FALSE, -68, JL_Constant.PREFIX_FLAG_SECOND, -24, 40, 80, JSONB.Constants.BC_STR_ASCII_FIX_5, 51, 10, JSONB.Constants.BC_STR_ASCII_FIX_1, JSONB.Constants.BC_LOCAL_TIME, -105, 96, 115, 30, 0, 98, JSONB.Constants.BC_INT32_SHORT_ZERO, 26, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_INT32_BYTE_ZERO, -126, 100, -97, 38, 65, JSONB.Constants.BC_TIMESTAMP_MINUTES, 69, 70, JSONB.Constants.BC_TYPED_ANY, 39, 94, 85, JSONB.Constants.BC_INT32_NUM_MAX, -116, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_STR_ASCII_FIX_32, -43, -107, 59, 7, 88, JSONB.Constants.BC_DOUBLE_NUM_1, JSONB.Constants.BC_INT32_SHORT_MIN, -122, JSONB.Constants.BC_TIMESTAMP_SECONDS, 29, -9, JSONB.Constants.BC_INT32_BYTE_MIN, 55, 107, -28, -120, -39, -25, -119, -31, 27, -125, 73, 76, 63, -8, -2, -115, 83, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, JSONB.Constants.BC_CHAR, -54, JSONB.Constants.BC_INT64_NUM_MIN, -123, 97, 32, 113, 103, JSONB.Constants.BC_ARRAY, 45, 43, 9, 91, -53, -101, 37, JSONB.Constants.BC_INT64_BYTE_ZERO, JSONB.Constants.BC_INT64, -27, 108, 82, 89, JSONB.Constants.BC_OBJECT, 116, -46, -26, -12, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_INT64_SHORT_MIN, -47, 102, JSONB.Constants.BC_NULL, -62, 57, 75, 99, JSONB.Constants.BC_FLOAT_INT};
    private static final byte[] inversePI = {JSONB.Constants.BC_OBJECT_END, 45, 50, -113, 14, JSONB.Constants.BC_INT32_BYTE_MIN, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_INT64_SHORT_MIN, 84, -26, -98, 57, 85, JSONB.Constants.BC_STR_GB18030, 82, JSONB.Constants.BC_BINARY, 100, 3, 87, 90, 28, 96, 7, 24, 33, 114, JSONB.Constants.BC_LOCAL_DATETIME, -47, 41, -58, JSONB.Constants.BC_ARRAY, 63, -32, 39, -115, 12, -126, -22, JSONB.Constants.BC_TIMESTAMP, JSONB.Constants.BC_DOUBLE_LONG, -102, 99, 73, -27, 66, -28, 21, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_INT64_BYTE_MIN, 6, 112, -99, 65, 117, 25, -55, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -4, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_INT64_INT, 42, 115, -124, -43, -61, JSONB.Constants.BC_NULL, 43, -122, JSONB.Constants.BC_LOCAL_TIME, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_DOUBLE_NUM_0, 91, 70, -45, -97, -3, -44, 15, -100, JSONB.Constants.BC_INT32_NUM_MAX, -101, 67, -17, -39, JSONB.Constants.BC_STR_ASCII, JSONB.Constants.BC_FLOAT_INT, 83, Byte.MAX_VALUE, -63, JSONB.Constants.BC_INT32_NUM_MIN, 35, -25, 37, 94, JSONB.Constants.BC_DOUBLE, 30, -94, -33, JSONB.Constants.BC_OBJECT, -2, JSONB.Constants.BC_TIMESTAMP_SECONDS, 34, -7, -30, JSONB.Constants.BC_STR_ASCII_FIX_1, -68, 53, -54, -18, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 5, 107, 81, -31, 89, JSONB.Constants.BC_ARRAY_FIX_MAX, -14, 113, 86, 17, 106, -119, -108, 101, -116, JSONB.Constants.BC_BIGINT, 119, 60, JSONB.Constants.BC_STR_UTF16, 40, JSONB.Constants.BC_TIMESTAMP_MILLIS, -46, 49, -34, JSONB.Constants.BC_INT64_SHORT_ZERO, 95, -52, -49, 118, 44, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_INT64_NUM_MIN, 46, 54, -37, JSONB.Constants.BC_STR_ASCII_FIX_32, JSONB.Constants.BC_DOUBLE_NUM_1, 20, -107, JSONB.Constants.BC_INT64, 98, -95, 59, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 102, -23, 92, 108, JSONB.Constants.BC_STR_ASCII_FIX_36, JSONB.Constants.BC_TIMESTAMP_MINUTES, 55, 97, 75, JSONB.Constants.BC_DECIMAL, -29, -70, -15, -96, -123, -125, -38, JSONB.Constants.BC_INT32_SHORT_MAX, -59, JSONB.Constants.BC_FALSE, 51, -6, -106, 111, 110, -62, -10, 80, -1, 93, JSONB.Constants.BC_LOCAL_DATE, -114, 23, 27, -105, JSONB.Constants.BC_STR_UTF16BE, -20, 88, -9, 31, -5, JSONB.Constants.BC_STR_UTF16LE, 9, 13, JSONB.Constants.BC_STR_UTF8, 103, 69, -121, JL_Constant.PREFIX_FLAG_SECOND, -24, 79, 29, JSONB.Constants.BC_STR_ASCII_FIX_5, 4, -21, -8, -13, 62, Base64.padSymbol, JSONB.Constants.BC_INT8, -118, -120, -35, -51, 11, 19, -104, 2, JSONB.Constants.BC_REFERENCE, ByteCompanionObject.MIN_VALUE, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_INT64_BYTE_ZERO, 36, 52, -53, -19, -12, -50, -103, JSONB.Constants.BC_INT32_NUM_16, JSONB.Constants.BC_INT32_SHORT_ZERO, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_TYPED_ANY, 58, 1, 38, 18, 26, JSONB.Constants.BC_INT32, 104, -11, -127, -117, JSONB.Constants.BC_INT64_SHORT_MAX, -42, 32, 10, 8, 0, 76, JSONB.Constants.BC_INT64_BYTE_MAX, 116};
    private boolean forEncryption;
    private final byte[] lFactors = {-108, 32, -123, JSONB.Constants.BC_INT32_NUM_16, -62, JSONB.Constants.BC_INT64_SHORT_MIN, 1, -5, 1, JSONB.Constants.BC_INT64_SHORT_MIN, -62, JSONB.Constants.BC_INT32_NUM_16, -123, 32, -108, 1};
    private int KEY_LENGTH = 32;
    private int SUB_LENGTH = 32 / 2;
    private byte[][] subKeys = null;
    private byte[][] _gf_mul = init_gf256_mul_table();

    private void C(byte[] bArr, int i) {
        Arrays.clear(bArr);
        bArr[15] = (byte) i;
        L(bArr);
    }

    private void F(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] LSX = LSX(bArr, bArr2);
        X(LSX, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.SUB_LENGTH);
        System.arraycopy(LSX, 0, bArr2, 0, this.SUB_LENGTH);
    }

    private void GOST3412_2015Func(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[][] bArr3;
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, i, bArr4, 0, 16);
        int i3 = 9;
        if (this.forEncryption) {
            for (int i4 = 0; i4 < 9; i4++) {
                bArr4 = Arrays.copyOf(LSX(this.subKeys[i4], bArr4), 16);
            }
            X(bArr4, this.subKeys[9]);
        } else {
            while (true) {
                bArr3 = this.subKeys;
                if (i3 <= 0) {
                    break;
                }
                bArr4 = Arrays.copyOf(XSL(bArr3[i3], bArr4), 16);
                i3--;
            }
            X(bArr4, bArr3[0]);
        }
        System.arraycopy(bArr4, 0, bArr2, i2, 16);
    }

    private void L(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            R(bArr);
        }
    }

    private byte[] LSX(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        X(copyOf, bArr2);
        S(copyOf);
        L(copyOf);
        return copyOf;
    }

    private void R(byte[] bArr) {
        byte l = l(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = l;
    }

    private void S(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = PI[unsignedByte(bArr[i])];
        }
    }

    private void X(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    private byte[] XSL(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        X(copyOf, bArr2);
        inverseL(copyOf);
        inverseS(copyOf);
        return copyOf;
    }

    private void generateSubKeys(byte[] bArr) {
        int i;
        if (bArr.length != this.KEY_LENGTH) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        this.subKeys = new byte[10][];
        for (int i2 = 0; i2 < 10; i2++) {
            this.subKeys[i2] = new byte[this.SUB_LENGTH];
        }
        int i3 = this.SUB_LENGTH;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[i3];
        int i4 = 0;
        while (true) {
            i = this.SUB_LENGTH;
            if (i4 >= i) {
                break;
            }
            byte[][] bArr4 = this.subKeys;
            byte[] bArr5 = bArr4[0];
            byte b = bArr[i4];
            bArr2[i4] = b;
            bArr5[i4] = b;
            byte[] bArr6 = bArr4[1];
            byte b2 = bArr[i + i4];
            bArr3[i4] = b2;
            bArr6[i4] = b2;
            i4++;
        }
        byte[] bArr7 = new byte[i];
        for (int i5 = 1; i5 < 5; i5++) {
            for (int i6 = 1; i6 <= 8; i6++) {
                C(bArr7, ((i5 - 1) * 8) + i6);
                F(bArr7, bArr2, bArr3);
            }
            int i7 = i5 * 2;
            System.arraycopy(bArr2, 0, this.subKeys[i7], 0, this.SUB_LENGTH);
            System.arraycopy(bArr3, 0, this.subKeys[i7 + 1], 0, this.SUB_LENGTH);
        }
    }

    private static byte[][] init_gf256_mul_table() {
        byte[][] bArr = new byte[256][];
        for (int i = 0; i < 256; i++) {
            bArr[i] = new byte[256];
            for (int i2 = 0; i2 < 256; i2++) {
                bArr[i][i2] = kuz_mul_gf256_slow((byte) i, (byte) i2);
            }
        }
        return bArr;
    }

    private void inverseL(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            inverseR(bArr);
        }
    }

    private void inverseR(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte l = l(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = l;
    }

    private void inverseS(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = inversePI[unsignedByte(bArr[i])];
        }
    }

    private static byte kuz_mul_gf256_slow(byte b, byte b2) {
        byte b3 = 0;
        for (byte b4 = 0; b4 < 8 && b != 0 && b2 != 0; b4 = (byte) (b4 + 1)) {
            if ((b2 & 1) != 0) {
                b3 = (byte) (b3 ^ b);
            }
            byte b5 = (byte) (b & ByteCompanionObject.MIN_VALUE);
            b = (byte) (b << 1);
            if (b5 != 0) {
                b = (byte) (b ^ 195);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    private byte l(byte[] bArr) {
        byte b = bArr[15];
        for (int i = 14; i >= 0; i--) {
            b = (byte) (b ^ this._gf_mul[unsignedByte(bArr[i])][unsignedByte(this.lFactors[i])]);
        }
        return b;
    }

    private int unsignedByte(byte b) {
        return b & UByte.MAX_VALUE;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            generateSubKeys(((KeyParameter) cipherParameters).getKey());
        } else if (cipherParameters != null) {
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.subKeys == null) {
            throw new IllegalStateException("GOST3412_2015 engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        GOST3412_2015Func(bArr, i, bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
