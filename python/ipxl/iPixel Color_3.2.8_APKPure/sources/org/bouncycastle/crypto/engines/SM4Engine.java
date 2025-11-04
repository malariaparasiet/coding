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
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class SM4Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private final int[] X = new int[4];
    private int[] rk;
    private static final byte[] Sbox = {-42, JSONB.Constants.BC_CHAR, -23, -2, -52, -31, Base64.padSymbol, JSONB.Constants.BC_FLOAT, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, JSONB.Constants.BC_FLOAT_INT, 20, -62, 40, -5, 44, 5, 43, 103, -102, 118, 42, JSONB.Constants.BC_INT64, 4, -61, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, JSONB.Constants.BC_INT32_SHORT_ZERO, 19, 38, 73, -122, 6, -103, -100, 66, 80, -12, JSONB.Constants.BC_BINARY, -17, -104, JSONB.Constants.BC_STR_UTF8, 51, 84, 11, 67, -19, -49, JSONB.Constants.BC_TIMESTAMP_SECONDS, 98, -28, JSONB.Constants.BC_DOUBLE_NUM_1, 28, JSONB.Constants.BC_LOCAL_DATE, -55, 8, -24, -107, ByteCompanionObject.MIN_VALUE, -33, -108, -6, 117, -113, 63, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_INT32_SHORT_MAX, 7, JSONB.Constants.BC_LOCAL_TIME, -4, -13, 115, 23, -70, -125, 89, 60, 25, -26, -123, 79, JSONB.Constants.BC_LOCAL_DATETIME, 104, 107, -127, JSONB.Constants.BC_DOUBLE_NUM_0, 113, 100, -38, -117, -8, -21, 15, 75, 112, 86, -99, 53, 30, 36, 14, 94, 99, 88, -47, -94, 37, 34, JSONB.Constants.BC_STR_UTF16LE, 59, 1, 33, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -121, -44, 0, 70, 87, -97, -45, 39, 82, 76, 54, 2, -25, -96, JSONB.Constants.BC_INT64_SHORT_ZERO, JSONB.Constants.BC_INT64_BYTE_MIN, -98, -22, JSONB.Constants.BC_INT64_INT, -118, -46, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_INT64_SHORT_MAX, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_ARRAY_FIX_MAX, -9, -14, -50, -7, 97, 21, -95, -32, JSONB.Constants.BC_TIMESTAMP, 93, JSONB.Constants.BC_ARRAY, -101, 52, 26, 85, JSONB.Constants.BC_TIMESTAMP_MINUTES, JSONB.Constants.BC_REFERENCE, 50, JSONB.Constants.BC_INT32_BYTE_MIN, -11, -116, JSONB.Constants.BC_TRUE, -29, 29, -10, -30, 46, -126, 102, -54, 96, JSONB.Constants.BC_INT64_SHORT_MIN, 41, 35, JSONB.Constants.BC_TIMESTAMP_MILLIS, 13, 83, JSONB.Constants.BC_STR_ASCII_FIX_5, 111, -43, -37, 55, 69, -34, -3, -114, JSONB.Constants.BC_INT32_NUM_MAX, 3, -1, 106, 114, JSONB.Constants.BC_STR_ASCII_FIX_36, 108, 91, 81, -115, 27, JSONB.Constants.BC_NULL, JSONB.Constants.BC_TYPED_ANY, JSONB.Constants.BC_BIGINT, -35, -68, Byte.MAX_VALUE, 17, -39, 92, 65, 31, JSONB.Constants.BC_INT32_NUM_16, 90, JSONB.Constants.BC_INT64_NUM_MIN, 10, -63, 49, -120, JSONB.Constants.BC_OBJECT_END, -51, JSONB.Constants.BC_STR_UTF16, JSONB.Constants.BC_INT8, 45, 116, JSONB.Constants.BC_INT64_BYTE_ZERO, 18, JSONB.Constants.BC_DECIMAL_LONG, -27, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_FALSE, -119, JSONB.Constants.BC_STR_ASCII_FIX_32, -105, JSONB.Constants.BC_STR_ASCII_FIX_1, 12, -106, 119, JSONB.Constants.BC_STR_GB18030, 101, JSONB.Constants.BC_DECIMAL, -15, 9, -59, 110, -58, -124, 24, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_STR_UTF16BE, -20, 58, JL_Constant.PREFIX_FLAG_SECOND, JSONB.Constants.BC_STR_ASCII_FIX_4, 32, JSONB.Constants.BC_STR_ASCII, -18, 95, 62, JSONB.Constants.BC_INT64_BYTE_MAX, -53, 57, JSONB.Constants.BC_INT32};
    private static final int[] CK = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};
    private static final int[] FK = {-1548633402, 1453994832, 1736282519, -1301273892};

    private int F0(int[] iArr, int i) {
        return T((iArr[3] ^ (iArr[1] ^ iArr[2])) ^ i) ^ iArr[0];
    }

    private int F1(int[] iArr, int i) {
        return T((iArr[0] ^ (iArr[2] ^ iArr[3])) ^ i) ^ iArr[1];
    }

    private int F2(int[] iArr, int i) {
        return T((iArr[1] ^ (iArr[3] ^ iArr[0])) ^ i) ^ iArr[2];
    }

    private int F3(int[] iArr, int i) {
        return T((iArr[2] ^ (iArr[0] ^ iArr[1])) ^ i) ^ iArr[3];
    }

    private int L(int i) {
        return rotateLeft(i, 24) ^ (((rotateLeft(i, 2) ^ i) ^ rotateLeft(i, 10)) ^ rotateLeft(i, 18));
    }

    private int L_ap(int i) {
        return rotateLeft(i, 23) ^ (rotateLeft(i, 13) ^ i);
    }

    private int T(int i) {
        return L(tau(i));
    }

    private int T_ap(int i) {
        return L_ap(tau(i));
    }

    private int[] expandKey(boolean z, byte[] bArr) {
        int[] iArr = new int[32];
        int[] iArr2 = {Pack.bigEndianToInt(bArr, 0), Pack.bigEndianToInt(bArr, 4), Pack.bigEndianToInt(bArr, 8), Pack.bigEndianToInt(bArr, 12)};
        int i = iArr2[0];
        int[] iArr3 = FK;
        int[] iArr4 = {i ^ iArr3[0], iArr2[1] ^ iArr3[1], iArr2[2] ^ iArr3[2], iArr2[3] ^ iArr3[3]};
        if (z) {
            int i2 = iArr4[0];
            int i3 = (iArr4[1] ^ iArr4[2]) ^ iArr4[3];
            int[] iArr5 = CK;
            int T_ap = i2 ^ T_ap(i3 ^ iArr5[0]);
            iArr[0] = T_ap;
            int T_ap2 = T_ap((T_ap ^ (iArr4[2] ^ iArr4[3])) ^ iArr5[1]) ^ iArr4[1];
            iArr[1] = T_ap2;
            int T_ap3 = T_ap((T_ap2 ^ (iArr4[3] ^ iArr[0])) ^ iArr5[2]) ^ iArr4[2];
            iArr[2] = T_ap3;
            iArr[3] = T_ap((T_ap3 ^ (iArr[0] ^ iArr[1])) ^ iArr5[3]) ^ iArr4[3];
            for (int i4 = 4; i4 < 32; i4++) {
                iArr[i4] = iArr[i4 - 4] ^ T_ap(((iArr[i4 - 3] ^ iArr[i4 - 2]) ^ iArr[i4 - 1]) ^ CK[i4]);
            }
        } else {
            int i5 = iArr4[0];
            int i6 = (iArr4[1] ^ iArr4[2]) ^ iArr4[3];
            int[] iArr6 = CK;
            int T_ap4 = i5 ^ T_ap(i6 ^ iArr6[0]);
            iArr[31] = T_ap4;
            int T_ap5 = T_ap((T_ap4 ^ (iArr4[2] ^ iArr4[3])) ^ iArr6[1]) ^ iArr4[1];
            iArr[30] = T_ap5;
            int T_ap6 = T_ap((T_ap5 ^ (iArr4[3] ^ iArr[31])) ^ iArr6[2]) ^ iArr4[2];
            iArr[29] = T_ap6;
            iArr[28] = T_ap((T_ap6 ^ (iArr[31] ^ iArr[30])) ^ iArr6[3]) ^ iArr4[3];
            for (int i7 = 27; i7 >= 0; i7--) {
                iArr[i7] = iArr[i7 + 4] ^ T_ap(((iArr[i7 + 3] ^ iArr[i7 + 2]) ^ iArr[i7 + 1]) ^ CK[31 - i7]);
            }
        }
        return iArr;
    }

    private int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private int tau(int i) {
        byte[] bArr = Sbox;
        return (bArr[i & 255] & UByte.MAX_VALUE) | ((bArr[(i >> 24) & 255] & UByte.MAX_VALUE) << 24) | ((bArr[(i >> 16) & 255] & UByte.MAX_VALUE) << 16) | ((bArr[(i >> 8) & 255] & UByte.MAX_VALUE) << 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "SM4";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("SM4 requires a 128 bit key");
        }
        this.rk = expandKey(z, key);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.rk == null) {
            throw new IllegalStateException("SM4 not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.X[0] = Pack.bigEndianToInt(bArr, i);
        this.X[1] = Pack.bigEndianToInt(bArr, i + 4);
        this.X[2] = Pack.bigEndianToInt(bArr, i + 8);
        this.X[3] = Pack.bigEndianToInt(bArr, i + 12);
        for (int i3 = 0; i3 < 32; i3 += 4) {
            int[] iArr = this.X;
            iArr[0] = F0(iArr, this.rk[i3]);
            int[] iArr2 = this.X;
            iArr2[1] = F1(iArr2, this.rk[i3 + 1]);
            int[] iArr3 = this.X;
            iArr3[2] = F2(iArr3, this.rk[i3 + 2]);
            int[] iArr4 = this.X;
            iArr4[3] = F3(iArr4, this.rk[i3 + 3]);
        }
        Pack.intToBigEndian(this.X[3], bArr2, i2);
        Pack.intToBigEndian(this.X[2], bArr2, i2 + 4);
        Pack.intToBigEndian(this.X[1], bArr2, i2 + 8);
        Pack.intToBigEndian(this.X[0], bArr2, i2 + 12);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
