package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.JSONB;
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
import org.bouncycastle.crypto.params.RC2Parameters;

/* loaded from: classes3.dex */
public class RC2Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 8;
    private static byte[] piTable = {-39, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -7, JSONB.Constants.BC_INT64_SHORT_ZERO, 25, -35, JSONB.Constants.BC_DOUBLE, -19, 40, -23, -3, JSONB.Constants.BC_STR_ASCII, JSONB.Constants.BC_STR_ASCII_FIX_1, -96, JSONB.Constants.BC_INT64_NUM_MIN, -99, -58, JSONB.Constants.BC_STR_GB18030, 55, -125, 43, 118, 83, -114, 98, 76, 100, -120, JSONB.Constants.BC_INT32_SHORT_ZERO, -117, -5, -94, 23, -102, 89, -11, -121, JSONB.Constants.BC_DOUBLE_NUM_1, 79, 19, 97, 69, JSONB.Constants.BC_STR_ASCII_FIX_36, -115, 9, -127, JSONB.Constants.BC_STR_UTF16BE, 50, JSONB.Constants.BC_INT8, -113, JSONB.Constants.BC_INT32_SHORT_MIN, -21, -122, JSONB.Constants.BC_FLOAT, JSONB.Constants.BC_STR_UTF16, 11, JSONB.Constants.BC_INT32_NUM_MIN, -107, 33, 34, 92, 107, JSONB.Constants.BC_STR_ASCII_FIX_5, -126, 84, -42, 101, JSONB.Constants.BC_REFERENCE, -50, 96, JSONB.Constants.BC_DOUBLE_NUM_0, 28, 115, 86, JSONB.Constants.BC_INT64_SHORT_MIN, 20, JSONB.Constants.BC_LOCAL_TIME, -116, -15, JL_Constant.PREFIX_FLAG_SECOND, 18, 117, -54, 31, 59, JSONB.Constants.BC_INT64, -28, -47, 66, Base64.padSymbol, -44, JSONB.Constants.BC_INT32_BYTE_MIN, JSONB.Constants.BC_ARRAY_FIX_MAX, 60, JSONB.Constants.BC_FLOAT_INT, 38, 111, JSONB.Constants.BC_INT64_INT, 14, -38, 70, JSONB.Constants.BC_STR_ASCII_FIX_32, 7, 87, 39, -14, 29, -101, -68, -108, 67, 3, -8, 17, JSONB.Constants.BC_INT64_SHORT_MAX, -10, JSONB.Constants.BC_CHAR, -17, 62, -25, 6, -61, -43, JSONB.Constants.BC_INT32_NUM_MAX, JSONB.Constants.BC_INT64_BYTE_MIN, 102, 30, JSONB.Constants.BC_INT64_BYTE_MAX, 8, -24, -22, -34, ByteCompanionObject.MIN_VALUE, 82, -18, -9, -124, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 114, JSONB.Constants.BC_TIMESTAMP_SECONDS, 53, JSONB.Constants.BC_STR_ASCII_FIX_4, 106, 42, -106, 26, -46, 113, 90, 21, 73, 116, 75, -97, JSONB.Constants.BC_INT64_BYTE_ZERO, 94, 4, 24, JSONB.Constants.BC_ARRAY, -20, -62, -32, 65, 110, 15, 81, -53, -52, 36, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_NULL, 80, -95, -12, 112, 57, -103, JSONB.Constants.BC_STR_UTF16LE, 58, -123, 35, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_STR_UTF8, -4, 2, 54, 91, 37, 85, -105, 49, 45, 93, -6, -104, -29, -118, JSONB.Constants.BC_TYPED_ANY, JSONB.Constants.BC_TIMESTAMP, 5, -33, 41, JSONB.Constants.BC_INT32_NUM_16, 103, 108, -70, -55, -45, 0, -26, -49, -31, -98, JSONB.Constants.BC_LOCAL_DATETIME, 44, 99, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 1, 63, 88, -30, -119, JSONB.Constants.BC_LOCAL_DATE, 13, JSONB.Constants.BC_INT32_BYTE_ZERO, 52, 27, JSONB.Constants.BC_TIMESTAMP_MILLIS, 51, -1, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_BIGINT, JSONB.Constants.BC_INT32, 12, 95, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_TRUE, -51, 46, -59, -13, -37, JSONB.Constants.BC_INT32_SHORT_MAX, -27, JSONB.Constants.BC_OBJECT_END, -100, 119, 10, JSONB.Constants.BC_OBJECT, 32, 104, -2, Byte.MAX_VALUE, -63, JSONB.Constants.BC_TIMESTAMP_MINUTES};
    private boolean encrypting;
    private int[] workingKey;

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = ((bArr[i + 7] & UByte.MAX_VALUE) << 8) + (bArr[i + 6] & UByte.MAX_VALUE);
        int i4 = ((bArr[i + 5] & UByte.MAX_VALUE) << 8) + (bArr[i + 4] & UByte.MAX_VALUE);
        int i5 = ((bArr[i + 3] & UByte.MAX_VALUE) << 8) + (bArr[i + 2] & UByte.MAX_VALUE);
        int i6 = ((bArr[i + 1] & UByte.MAX_VALUE) << 8) + (bArr[i] & UByte.MAX_VALUE);
        for (int i7 = 60; i7 >= 44; i7 -= 4) {
            i3 = rotateWordLeft(i3, 11) - ((((~i4) & i6) + (i5 & i4)) + this.workingKey[i7 + 3]);
            i4 = rotateWordLeft(i4, 13) - ((((~i5) & i3) + (i6 & i5)) + this.workingKey[i7 + 2]);
            i5 = rotateWordLeft(i5, 14) - ((((~i6) & i4) + (i3 & i6)) + this.workingKey[i7 + 1]);
            i6 = rotateWordLeft(i6, 15) - ((((~i3) & i5) + (i4 & i3)) + this.workingKey[i7]);
        }
        int[] iArr = this.workingKey;
        int i8 = i3 - iArr[i4 & 63];
        int i9 = i4 - iArr[i5 & 63];
        int i10 = i5 - iArr[i6 & 63];
        int i11 = i6 - iArr[i8 & 63];
        for (int i12 = 40; i12 >= 20; i12 -= 4) {
            i8 = rotateWordLeft(i8, 11) - ((((~i9) & i11) + (i10 & i9)) + this.workingKey[i12 + 3]);
            i9 = rotateWordLeft(i9, 13) - ((((~i10) & i8) + (i11 & i10)) + this.workingKey[i12 + 2]);
            i10 = rotateWordLeft(i10, 14) - ((((~i11) & i9) + (i8 & i11)) + this.workingKey[i12 + 1]);
            i11 = rotateWordLeft(i11, 15) - ((((~i8) & i10) + (i9 & i8)) + this.workingKey[i12]);
        }
        int[] iArr2 = this.workingKey;
        int i13 = i8 - iArr2[i9 & 63];
        int i14 = i9 - iArr2[i10 & 63];
        int i15 = i10 - iArr2[i11 & 63];
        int i16 = i11 - iArr2[i13 & 63];
        for (int i17 = 16; i17 >= 0; i17 -= 4) {
            i13 = rotateWordLeft(i13, 11) - ((((~i14) & i16) + (i15 & i14)) + this.workingKey[i17 + 3]);
            i14 = rotateWordLeft(i14, 13) - ((((~i15) & i13) + (i16 & i15)) + this.workingKey[i17 + 2]);
            i15 = rotateWordLeft(i15, 14) - ((((~i16) & i14) + (i13 & i16)) + this.workingKey[i17 + 1]);
            i16 = rotateWordLeft(i16, 15) - ((((~i13) & i15) + (i14 & i13)) + this.workingKey[i17]);
        }
        bArr2[i2] = (byte) i16;
        bArr2[i2 + 1] = (byte) (i16 >> 8);
        bArr2[i2 + 2] = (byte) i15;
        bArr2[i2 + 3] = (byte) (i15 >> 8);
        bArr2[i2 + 4] = (byte) i14;
        bArr2[i2 + 5] = (byte) (i14 >> 8);
        bArr2[i2 + 6] = (byte) i13;
        bArr2[i2 + 7] = (byte) (i13 >> 8);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = ((bArr[i + 7] & UByte.MAX_VALUE) << 8) + (bArr[i + 6] & UByte.MAX_VALUE);
        int i4 = ((bArr[i + 5] & UByte.MAX_VALUE) << 8) + (bArr[i + 4] & UByte.MAX_VALUE);
        int i5 = ((bArr[i + 3] & UByte.MAX_VALUE) << 8) + (bArr[i + 2] & UByte.MAX_VALUE);
        int i6 = ((bArr[i + 1] & UByte.MAX_VALUE) << 8) + (bArr[i] & UByte.MAX_VALUE);
        for (int i7 = 0; i7 <= 16; i7 += 4) {
            i6 = rotateWordLeft(i6 + ((~i3) & i5) + (i4 & i3) + this.workingKey[i7], 1);
            i5 = rotateWordLeft(i5 + ((~i6) & i4) + (i3 & i6) + this.workingKey[i7 + 1], 2);
            i4 = rotateWordLeft(i4 + ((~i5) & i3) + (i6 & i5) + this.workingKey[i7 + 2], 3);
            i3 = rotateWordLeft(i3 + ((~i4) & i6) + (i5 & i4) + this.workingKey[i7 + 3], 5);
        }
        int[] iArr = this.workingKey;
        int i8 = i6 + iArr[i3 & 63];
        int i9 = i5 + iArr[i8 & 63];
        int i10 = i4 + iArr[i9 & 63];
        int i11 = i3 + iArr[i10 & 63];
        for (int i12 = 20; i12 <= 40; i12 += 4) {
            i8 = rotateWordLeft(i8 + ((~i11) & i9) + (i10 & i11) + this.workingKey[i12], 1);
            i9 = rotateWordLeft(i9 + ((~i8) & i10) + (i11 & i8) + this.workingKey[i12 + 1], 2);
            i10 = rotateWordLeft(i10 + ((~i9) & i11) + (i8 & i9) + this.workingKey[i12 + 2], 3);
            i11 = rotateWordLeft(i11 + ((~i10) & i8) + (i9 & i10) + this.workingKey[i12 + 3], 5);
        }
        int[] iArr2 = this.workingKey;
        int i13 = i8 + iArr2[i11 & 63];
        int i14 = i9 + iArr2[i13 & 63];
        int i15 = i10 + iArr2[i14 & 63];
        int i16 = i11 + iArr2[i15 & 63];
        for (int i17 = 44; i17 < 64; i17 += 4) {
            i13 = rotateWordLeft(i13 + ((~i16) & i14) + (i15 & i16) + this.workingKey[i17], 1);
            i14 = rotateWordLeft(i14 + ((~i13) & i15) + (i16 & i13) + this.workingKey[i17 + 1], 2);
            i15 = rotateWordLeft(i15 + ((~i14) & i16) + (i13 & i14) + this.workingKey[i17 + 2], 3);
            i16 = rotateWordLeft(i16 + ((~i15) & i13) + (i14 & i15) + this.workingKey[i17 + 3], 5);
        }
        bArr2[i2] = (byte) i13;
        bArr2[i2 + 1] = (byte) (i13 >> 8);
        bArr2[i2 + 2] = (byte) i14;
        bArr2[i2 + 3] = (byte) (i14 >> 8);
        bArr2[i2 + 4] = (byte) i15;
        bArr2[i2 + 5] = (byte) (i15 >> 8);
        bArr2[i2 + 6] = (byte) i16;
        bArr2[i2 + 7] = (byte) (i16 >> 8);
    }

    private int[] generateWorkingKey(byte[] bArr, int i) {
        int[] iArr = new int[128];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        int length = bArr.length;
        if (length < 128) {
            int i3 = iArr[length - 1];
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                i3 = piTable[(i3 + iArr[i4]) & 255] & UByte.MAX_VALUE;
                int i6 = length + 1;
                iArr[length] = i3;
                if (i6 >= 128) {
                    break;
                }
                length = i6;
                i4 = i5;
            }
        }
        int i7 = (i + 7) >> 3;
        int i8 = 128 - i7;
        int i9 = piTable[(255 >> ((-i) & 7)) & iArr[i8]] & UByte.MAX_VALUE;
        iArr[i8] = i9;
        for (int i10 = 127 - i7; i10 >= 0; i10--) {
            i9 = piTable[i9 ^ iArr[i10 + i7]] & UByte.MAX_VALUE;
            iArr[i10] = i9;
        }
        int[] iArr2 = new int[64];
        for (int i11 = 0; i11 != 64; i11++) {
            int i12 = i11 * 2;
            iArr2[i11] = iArr[i12] + (iArr[i12 + 1] << 8);
        }
        return iArr2;
    }

    private int rotateWordLeft(int i, int i2) {
        int i3 = i & 65535;
        return (i3 >> (16 - i2)) | (i3 << i2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] key;
        this.encrypting = z;
        if (cipherParameters instanceof RC2Parameters) {
            RC2Parameters rC2Parameters = (RC2Parameters) cipherParameters;
            this.workingKey = generateWorkingKey(rC2Parameters.getKey(), rC2Parameters.getEffectiveKeyBits());
            key = rC2Parameters.getKey();
        } else {
            if (!(cipherParameters instanceof KeyParameter)) {
                throw new IllegalArgumentException("invalid parameter passed to RC2 init - " + cipherParameters.getClass().getName());
            }
            key = ((KeyParameter) cipherParameters).getKey();
            this.workingKey = generateWorkingKey(key, key.length * 8);
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length * 8, cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("RC2 engine not initialised");
        }
        if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            encryptBlock(bArr, i, bArr2, i2);
            return 8;
        }
        decryptBlock(bArr, i, bArr2, i2);
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
