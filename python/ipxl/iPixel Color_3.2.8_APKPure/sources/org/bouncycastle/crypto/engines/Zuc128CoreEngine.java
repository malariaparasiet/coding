package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Memoable;

/* loaded from: classes3.dex */
public class Zuc128CoreEngine implements StreamCipher, Memoable {
    private final int[] BRC;
    private final int[] F;
    private final int[] LFSR;
    private final byte[] keyStream;
    private int theIndex;
    private int theIterations;
    private Zuc128CoreEngine theResetState;
    private static final byte[] S0 = {62, 114, 91, JSONB.Constants.BC_INT32_SHORT_MAX, -54, -32, 0, 51, 4, -47, 84, -104, 9, JSONB.Constants.BC_DECIMAL, JSONB.Constants.BC_STR_ASCII_FIX_36, -53, JSONB.Constants.BC_STR_UTF16, 27, -7, 50, JSONB.Constants.BC_NULL, -99, 106, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_DECIMAL_LONG, 45, -4, 29, 8, 83, 3, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_STR_ASCII_FIX_5, -124, -103, -28, -50, -39, JSONB.Constants.BC_BINARY, -35, JSONB.Constants.BC_FLOAT_INT, -123, JSONB.Constants.BC_INT32, -117, 41, 110, JSONB.Constants.BC_TIMESTAMP_SECONDS, -51, -63, -8, 30, 115, 67, JSONB.Constants.BC_STR_ASCII_FIX_32, -58, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_INT8, -3, 57, 99, 32, -44, JSONB.Constants.BC_INT32_BYTE_ZERO, 118, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_DOUBLE_NUM_0, JSONB.Constants.BC_LOCAL_TIME, -49, -19, 87, -59, -13, 44, JSONB.Constants.BC_BIGINT, 20, 33, 6, 85, -101, -29, -17, 94, 49, 79, Byte.MAX_VALUE, 90, JSONB.Constants.BC_ARRAY, 13, -126, 81, 73, 95, -70, 88, 28, JSONB.Constants.BC_STR_ASCII_FIX_1, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -43, 23, JSONB.Constants.BC_LOCAL_DATETIME, JSONB.Constants.BC_TYPED_ANY, 36, 31, -116, -1, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_TIMESTAMP, 46, 1, -45, JSONB.Constants.BC_TIMESTAMP_MINUTES, 59, 75, -38, 70, -21, -55, -34, -102, -113, -121, JSONB.Constants.BC_INT64_BYTE_MAX, 58, ByteCompanionObject.MIN_VALUE, 111, JSONB.Constants.BC_INT32_NUM_MAX, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_DOUBLE_LONG, 55, -9, 10, 34, 19, 40, JSONB.Constants.BC_STR_UTF16LE, -52, 60, -119, JSONB.Constants.BC_INT64_SHORT_MAX, -61, -106, 86, 7, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_STR_GB18030, JSONB.Constants.BC_INT32_NUM_MIN, 11, 43, -105, 82, 53, 65, JSONB.Constants.BC_STR_ASCII, 97, JSONB.Constants.BC_OBJECT, 76, JSONB.Constants.BC_INT32_NUM_16, -2, -68, 38, -107, -120, -118, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_ARRAY_FIX_MAX, -5, JSONB.Constants.BC_INT64_SHORT_MIN, 24, -108, -14, -31, -27, -23, 93, JSONB.Constants.BC_INT64_BYTE_ZERO, JL_Constant.PREFIX_FLAG_SECOND, 17, 102, 100, 92, -20, 89, 66, 117, 18, -11, 116, -100, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 35, 14, -122, JSONB.Constants.BC_TIMESTAMP_MILLIS, JSONB.Constants.BC_INT64, 42, 2, -25, 103, -26, JSONB.Constants.BC_INT32_SHORT_ZERO, -94, 108, -62, JSONB.Constants.BC_REFERENCE, -97, -15, -10, -6, 54, -46, 80, 104, -98, 98, 113, 21, Base64.padSymbol, -42, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_INT64_SHORT_ZERO, -30, 15, -114, -125, 119, 107, 37, 5, 63, 12, JSONB.Constants.BC_INT32_BYTE_MIN, -22, 112, JSONB.Constants.BC_FLOAT, -95, -24, JSONB.Constants.BC_LOCAL_DATE, 101, -115, 39, 26, -37, -127, JSONB.Constants.BC_DOUBLE_NUM_1, -96, -12, 69, JSONB.Constants.BC_STR_UTF8, 25, -33, -18, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 52, 96};
    private static final byte[] S1 = {85, -62, 99, 113, 59, JSONB.Constants.BC_INT64_BYTE_MIN, JSONB.Constants.BC_INT32_SHORT_MAX, -122, -97, 60, -38, 91, 41, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -3, 119, -116, -59, -108, 12, JSONB.Constants.BC_OBJECT, 26, 19, 0, -29, JSONB.Constants.BC_LOCAL_DATETIME, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 114, JSONB.Constants.BC_INT32_SHORT_MIN, -7, -8, 66, JSONB.Constants.BC_INT32_SHORT_ZERO, 38, 104, -106, -127, -39, 69, 62, JSONB.Constants.BC_INT32_NUM_16, 118, -58, JSONB.Constants.BC_LOCAL_TIME, -117, 57, 67, -31, 58, JSONB.Constants.BC_DOUBLE, 86, 42, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_STR_ASCII_FIX_36, JSONB.Constants.BC_DOUBLE_NUM_1, 5, 34, 102, JSONB.Constants.BC_INT64_INT, JL_Constant.PREFIX_FLAG_SECOND, 11, -6, 98, JSONB.Constants.BC_INT32, -35, 32, 17, 6, 54, -55, -63, -49, -10, 39, 82, JSONB.Constants.BC_BIGINT, JSONB.Constants.BC_STR_ASCII_FIX_32, -11, -44, -121, Byte.MAX_VALUE, -124, 76, -46, -100, 87, JSONB.Constants.BC_ARRAY, -68, 79, -102, -33, -2, -42, -115, JSONB.Constants.BC_STR_UTF8, -21, 43, 83, JSONB.Constants.BC_INT64_NUM_MIN, 92, -95, 20, 23, -5, 35, -43, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_INT32_BYTE_MIN, 103, 115, 8, 9, -18, JSONB.Constants.BC_FLOAT, 112, 63, 97, JSONB.Constants.BC_DOUBLE_NUM_0, 25, -114, JSONB.Constants.BC_STR_ASCII_FIX_5, -27, 75, JSONB.Constants.BC_REFERENCE, -113, 93, -37, JSONB.Constants.BC_LOCAL_DATE, JSONB.Constants.BC_TIMESTAMP_MINUTES, -15, JSONB.Constants.BC_TIMESTAMP, 46, -53, 13, -4, -12, 45, 70, 110, 29, -105, -24, -47, -23, JSONB.Constants.BC_STR_ASCII_FIX_4, 55, JSONB.Constants.BC_OBJECT_END, 117, 94, -125, -98, JSONB.Constants.BC_TIMESTAMP_MILLIS, -126, -99, JSONB.Constants.BC_DECIMAL, 28, -32, -51, 73, -119, 1, JSONB.Constants.BC_FLOAT_INT, JSONB.Constants.BC_INT8, 88, 36, -94, 95, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -103, 21, JSONB.Constants.BC_CHAR, 80, JSONB.Constants.BC_DECIMAL_LONG, -107, -28, JSONB.Constants.BC_INT64_BYTE_ZERO, JSONB.Constants.BC_BINARY, JSONB.Constants.BC_INT64_SHORT_MAX, -50, -19, 15, JSONB.Constants.BC_DOUBLE_LONG, 111, -96, -52, JSONB.Constants.BC_INT32_NUM_MIN, 2, JSONB.Constants.BC_STR_ASCII_FIX_1, JSONB.Constants.BC_STR_ASCII, -61, -34, JSONB.Constants.BC_ARRAY_FIX_MAX, -17, -22, 81, -26, 107, 24, -20, 27, 44, ByteCompanionObject.MIN_VALUE, -9, 116, -25, -1, 33, 90, 106, 84, 30, 65, 49, JSONB.Constants.BC_TYPED_ANY, 53, JSONB.Constants.BC_INT64_SHORT_ZERO, 51, 7, 10, -70, JSONB.Constants.BC_STR_GB18030, 14, 52, -120, JSONB.Constants.BC_TRUE, -104, JSONB.Constants.BC_STR_UTF16LE, -13, Base64.padSymbol, 96, 108, JSONB.Constants.BC_STR_UTF16, -54, -45, 31, 50, 101, 4, 40, 100, JSONB.Constants.BC_INT64, -123, -101, JSONB.Constants.BC_INT32_NUM_MAX, 89, -118, JSONB.Constants.BC_INT64_BYTE_MAX, JSONB.Constants.BC_FALSE, 37, JSONB.Constants.BC_TIMESTAMP_SECONDS, JSONB.Constants.BC_NULL, 18, 3, -30, -14};
    private static final short[] EK_d = {17623, 9916, 25195, 4958, 22409, 13794, 28981, 2479, 19832, 12051, 27588, 6897, 24102, 15437, 30874, 18348};

    protected Zuc128CoreEngine() {
        this.LFSR = new int[16];
        this.F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
    }

    protected Zuc128CoreEngine(Zuc128CoreEngine zuc128CoreEngine) {
        this.LFSR = new int[16];
        this.F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
        reset(zuc128CoreEngine);
    }

    private int AddM(int i, int i2) {
        int i3 = i + i2;
        return (Integer.MAX_VALUE & i3) + (i3 >>> 31);
    }

    private void BitReorganization() {
        int[] iArr = this.BRC;
        int[] iArr2 = this.LFSR;
        iArr[0] = ((iArr2[15] & 2147450880) << 1) | (iArr2[14] & 65535);
        iArr[1] = ((iArr2[11] & 65535) << 16) | (iArr2[9] >>> 15);
        iArr[2] = ((iArr2[7] & 65535) << 16) | (iArr2[5] >>> 15);
        iArr[3] = (iArr2[0] >>> 15) | ((iArr2[2] & 65535) << 16);
    }

    private static int L1(int i) {
        return ROT(i, 24) ^ (((ROT(i, 2) ^ i) ^ ROT(i, 10)) ^ ROT(i, 18));
    }

    private static int L2(int i) {
        return ROT(i, 30) ^ (((ROT(i, 8) ^ i) ^ ROT(i, 14)) ^ ROT(i, 22));
    }

    private void LFSRWithInitialisationMode(int i) {
        int i2 = this.LFSR[0];
        int AddM = AddM(AddM(AddM(AddM(AddM(AddM(i2, MulByPow2(i2, 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15)), i);
        int[] iArr = this.LFSR;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = iArr[5];
        iArr[5] = iArr[6];
        iArr[6] = iArr[7];
        iArr[7] = iArr[8];
        iArr[8] = iArr[9];
        iArr[9] = iArr[10];
        iArr[10] = iArr[11];
        iArr[11] = iArr[12];
        iArr[12] = iArr[13];
        iArr[13] = iArr[14];
        iArr[14] = iArr[15];
        iArr[15] = AddM;
    }

    private void LFSRWithWorkMode() {
        int i = this.LFSR[0];
        int AddM = AddM(AddM(AddM(AddM(AddM(i, MulByPow2(i, 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15));
        int[] iArr = this.LFSR;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = iArr[5];
        iArr[5] = iArr[6];
        iArr[6] = iArr[7];
        iArr[7] = iArr[8];
        iArr[8] = iArr[9];
        iArr[9] = iArr[10];
        iArr[10] = iArr[11];
        iArr[11] = iArr[12];
        iArr[12] = iArr[13];
        iArr[13] = iArr[14];
        iArr[14] = iArr[15];
        iArr[15] = AddM;
    }

    private static int MAKEU31(byte b, short s, byte b2) {
        return ((b & UByte.MAX_VALUE) << 23) | ((s & 65535) << 8) | (b2 & UByte.MAX_VALUE);
    }

    private static int MAKEU32(byte b, byte b2, byte b3, byte b4) {
        return ((b & UByte.MAX_VALUE) << 24) | ((b2 & UByte.MAX_VALUE) << 16) | ((b3 & UByte.MAX_VALUE) << 8) | (b4 & UByte.MAX_VALUE);
    }

    private static int MulByPow2(int i, int i2) {
        return ((i >>> (31 - i2)) | (i << i2)) & Integer.MAX_VALUE;
    }

    static int ROT(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static void encode32be(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >> 24);
        bArr[i2 + 1] = (byte) (i >> 16);
        bArr[i2 + 2] = (byte) (i >> 8);
        bArr[i2 + 3] = (byte) i;
    }

    private void makeKeyStream() {
        encode32be(makeKeyStreamWord(), this.keyStream, 0);
    }

    private void setKeyAndIV(byte[] bArr, byte[] bArr2) {
        setKeyAndIV(this.LFSR, bArr, bArr2);
        int[] iArr = this.F;
        iArr[0] = 0;
        iArr[1] = 0;
        int i = 32;
        while (true) {
            BitReorganization();
            if (i <= 0) {
                F();
                LFSRWithWorkMode();
                return;
            } else {
                LFSRWithInitialisationMode(F() >>> 1);
                i--;
            }
        }
    }

    int F() {
        int[] iArr = this.BRC;
        int i = iArr[0];
        int[] iArr2 = this.F;
        int i2 = iArr2[0];
        int i3 = iArr2[1];
        int i4 = (i ^ i2) + i3;
        int i5 = i2 + iArr[1];
        int i6 = iArr[2] ^ i3;
        int L1 = L1((i5 << 16) | (i6 >>> 16));
        int L2 = L2((i6 << 16) | (i5 >>> 16));
        int[] iArr3 = this.F;
        byte[] bArr = S0;
        byte b = bArr[L1 >>> 24];
        byte[] bArr2 = S1;
        iArr3[0] = MAKEU32(b, bArr2[(L1 >>> 16) & 255], bArr[(L1 >>> 8) & 255], bArr2[L1 & 255]);
        this.F[1] = MAKEU32(bArr[L2 >>> 24], bArr2[(L2 >>> 16) & 255], bArr[(L2 >>> 8) & 255], bArr2[L2 & 255]);
        return i4;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new Zuc128CoreEngine(this);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Zuc-128";
    }

    protected int getMaxIterations() {
        return 2047;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        byte[] bArr;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters2 = parametersWithIV.getParameters();
        } else {
            cipherParameters2 = cipherParameters;
            bArr = null;
        }
        byte[] key = cipherParameters2 instanceof KeyParameter ? ((KeyParameter) cipherParameters2).getKey() : null;
        this.theIndex = 0;
        this.theIterations = 0;
        setKeyAndIV(key, bArr);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length * 8, cipherParameters, z ? CryptoServicePurpose.ENCRYPTION : CryptoServicePurpose.DECRYPTION));
        this.theResetState = (Zuc128CoreEngine) copy();
    }

    protected int makeKeyStreamWord() {
        int i = this.theIterations;
        this.theIterations = i + 1;
        if (i >= getMaxIterations()) {
            throw new IllegalStateException("Too much data processed by singleKey/IV");
        }
        BitReorganization();
        int F = F() ^ this.BRC[3];
        LFSRWithWorkMode();
        return F;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (this.theResetState == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            bArr2[i4 + i3] = returnByte(bArr[i4 + i]);
        }
        return i2;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        Zuc128CoreEngine zuc128CoreEngine = this.theResetState;
        if (zuc128CoreEngine != null) {
            reset(zuc128CoreEngine);
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        Zuc128CoreEngine zuc128CoreEngine = (Zuc128CoreEngine) memoable;
        int[] iArr = zuc128CoreEngine.LFSR;
        int[] iArr2 = this.LFSR;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = zuc128CoreEngine.F;
        int[] iArr4 = this.F;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        int[] iArr5 = zuc128CoreEngine.BRC;
        int[] iArr6 = this.BRC;
        System.arraycopy(iArr5, 0, iArr6, 0, iArr6.length);
        byte[] bArr = zuc128CoreEngine.keyStream;
        byte[] bArr2 = this.keyStream;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.theIndex = zuc128CoreEngine.theIndex;
        this.theIterations = zuc128CoreEngine.theIterations;
        this.theResetState = zuc128CoreEngine;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.theIndex == 0) {
            makeKeyStream();
        }
        byte[] bArr = this.keyStream;
        int i = this.theIndex;
        byte b2 = (byte) (b ^ bArr[i]);
        this.theIndex = (i + 1) % 4;
        return b2;
    }

    protected void setKeyAndIV(int[] iArr, byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("A key of 16 bytes is needed");
        }
        if (bArr2 == null || bArr2.length != 16) {
            throw new IllegalArgumentException("An IV of 16 bytes is needed");
        }
        int[] iArr2 = this.LFSR;
        byte b = bArr[0];
        short[] sArr = EK_d;
        iArr2[0] = MAKEU31(b, sArr[0], bArr2[0]);
        this.LFSR[1] = MAKEU31(bArr[1], sArr[1], bArr2[1]);
        this.LFSR[2] = MAKEU31(bArr[2], sArr[2], bArr2[2]);
        this.LFSR[3] = MAKEU31(bArr[3], sArr[3], bArr2[3]);
        this.LFSR[4] = MAKEU31(bArr[4], sArr[4], bArr2[4]);
        this.LFSR[5] = MAKEU31(bArr[5], sArr[5], bArr2[5]);
        this.LFSR[6] = MAKEU31(bArr[6], sArr[6], bArr2[6]);
        this.LFSR[7] = MAKEU31(bArr[7], sArr[7], bArr2[7]);
        this.LFSR[8] = MAKEU31(bArr[8], sArr[8], bArr2[8]);
        this.LFSR[9] = MAKEU31(bArr[9], sArr[9], bArr2[9]);
        this.LFSR[10] = MAKEU31(bArr[10], sArr[10], bArr2[10]);
        this.LFSR[11] = MAKEU31(bArr[11], sArr[11], bArr2[11]);
        this.LFSR[12] = MAKEU31(bArr[12], sArr[12], bArr2[12]);
        this.LFSR[13] = MAKEU31(bArr[13], sArr[13], bArr2[13]);
        this.LFSR[14] = MAKEU31(bArr[14], sArr[14], bArr2[14]);
        this.LFSR[15] = MAKEU31(bArr[15], sArr[15], bArr2[15]);
    }
}
