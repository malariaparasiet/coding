package org.bouncycastle.crypto.digests;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Memoable;

/* loaded from: classes3.dex */
public class MD2Digest implements ExtendedDigest, Memoable {
    private static final int DIGEST_LENGTH = 16;
    private static final byte[] S = {41, 46, 67, -55, -94, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_STR_UTF16LE, 1, Base64.padSymbol, 54, 84, -95, -20, JSONB.Constants.BC_INT32_NUM_MIN, 6, 19, 98, JSONB.Constants.BC_LOCAL_TIME, 5, -13, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_INT64_SHORT_MAX, 115, -116, -104, JSONB.Constants.BC_REFERENCE, 43, -39, -68, 76, -126, -54, 30, -101, 87, 60, -3, -44, -32, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 103, 66, 111, 24, -118, 23, -27, 18, JSONB.Constants.BC_INT64, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_INT64_SHORT_ZERO, -42, -38, -98, -34, 73, -96, -5, -11, -114, JSONB.Constants.BC_BIGINT, JSONB.Constants.BC_INT32_NUM_MAX, -18, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_LOCAL_DATE, 104, JSONB.Constants.BC_STR_ASCII, JSONB.Constants.BC_BINARY, 21, JSONB.Constants.BC_DOUBLE_NUM_0, 7, 63, -108, -62, JSONB.Constants.BC_INT32_NUM_16, -119, 11, 34, 95, 33, ByteCompanionObject.MIN_VALUE, Byte.MAX_VALUE, 93, -102, 90, JSONB.Constants.BC_CHAR, 50, 39, 53, 62, -52, -25, JSONB.Constants.BC_INT64_INT, -9, -105, 3, -1, 25, JSONB.Constants.BC_INT32_BYTE_MIN, JSONB.Constants.BC_DOUBLE_NUM_1, JSONB.Constants.BC_INT32, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_DOUBLE, -47, JSONB.Constants.BC_INT64_BYTE_MAX, 94, JSONB.Constants.BC_TYPED_ANY, 42, JSONB.Constants.BC_TIMESTAMP_SECONDS, 86, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -58, 79, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_INT32_BYTE_ZERO, -46, -106, JSONB.Constants.BC_ARRAY, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_FLOAT_INT, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, 45, JSONB.Constants.BC_LOCAL_DATETIME, 2, 27, 96, 37, JSONB.Constants.BC_TIMESTAMP_MINUTES, JSONB.Constants.BC_TIMESTAMP, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_DECIMAL, -10, 28, 70, 97, JSONB.Constants.BC_STR_ASCII_FIX_32, 52, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_STR_GB18030, 15, 85, JSONB.Constants.BC_INT32_SHORT_MAX, JSONB.Constants.BC_ARRAY_FIX_MAX, 35, -35, 81, JSONB.Constants.BC_NULL, 58, -61, 92, -7, -50, -70, -59, -22, 38, 44, 83, 13, 110, -123, 40, -124, 9, -45, -33, -51, -12, 65, -127, JSONB.Constants.BC_STR_ASCII_FIX_4, 82, 106, JL_Constant.PREFIX_FLAG_SECOND, 55, JSONB.Constants.BC_INT64_BYTE_MIN, 108, -63, JSONB.Constants.BC_TIMESTAMP_MILLIS, -6, 36, -31, JSONB.Constants.BC_STR_UTF16, 8, 12, JSONB.Constants.BC_INT8, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_STR_ASCII_FIX_1, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -120, -107, -117, -29, 99, -24, JSONB.Constants.BC_STR_ASCII_FIX_36, -23, -53, -43, -2, 59, 0, 29, 57, -14, -17, JSONB.Constants.BC_FLOAT, 14, 102, 88, JSONB.Constants.BC_INT64_BYTE_ZERO, -28, JSONB.Constants.BC_OBJECT, 119, 114, -8, -21, 117, 75, 10, 49, JSONB.Constants.BC_INT32_SHORT_ZERO, 80, JSONB.Constants.BC_DOUBLE_LONG, -113, -19, 31, 26, -37, -103, -115, 51, -97, 17, -125, 20};
    private byte[] C;
    private int COff;
    private byte[] M;
    private byte[] X;
    private int mOff;
    private final CryptoServicePurpose purpose;
    private int xOff;

    public MD2Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public MD2Digest(CryptoServicePurpose cryptoServicePurpose) {
        this.X = new byte[48];
        this.M = new byte[16];
        this.C = new byte[16];
        this.purpose = cryptoServicePurpose;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 64, cryptoServicePurpose));
        reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        this.X = new byte[48];
        this.M = new byte[16];
        this.C = new byte[16];
        CryptoServicePurpose cryptoServicePurpose = mD2Digest.purpose;
        this.purpose = cryptoServicePurpose;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 64, cryptoServicePurpose));
        copyIn(mD2Digest);
    }

    private void copyIn(MD2Digest mD2Digest) {
        byte[] bArr = mD2Digest.X;
        System.arraycopy(bArr, 0, this.X, 0, bArr.length);
        this.xOff = mD2Digest.xOff;
        byte[] bArr2 = mD2Digest.M;
        System.arraycopy(bArr2, 0, this.M, 0, bArr2.length);
        this.mOff = mD2Digest.mOff;
        byte[] bArr3 = mD2Digest.C;
        System.arraycopy(bArr3, 0, this.C, 0, bArr3.length);
        this.COff = mD2Digest.COff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new MD2Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int length = this.M.length;
        int i2 = this.mOff;
        byte b = (byte) (length - i2);
        while (true) {
            byte[] bArr2 = this.M;
            if (i2 >= bArr2.length) {
                processCheckSum(bArr2);
                processBlock(this.M);
                processBlock(this.C);
                System.arraycopy(this.X, this.xOff, bArr, i, 16);
                reset();
                return 16;
            }
            bArr2[i2] = b;
            i2++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "MD2";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    protected void processBlock(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.X;
            bArr2[i + 16] = bArr[i];
            bArr2[i + 32] = (byte) (bArr[i] ^ bArr2[i]);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 18; i3++) {
            for (int i4 = 0; i4 < 48; i4++) {
                byte[] bArr3 = this.X;
                byte b = (byte) (S[i2] ^ bArr3[i4]);
                bArr3[i4] = b;
                i2 = b & UByte.MAX_VALUE;
            }
            i2 = (i2 + i3) % 256;
        }
    }

    protected void processCheckSum(byte[] bArr) {
        byte b = this.C[15];
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.C;
            b = (byte) (S[(b ^ bArr[i]) & 255] ^ bArr2[i]);
            bArr2[i] = b;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.xOff = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.X;
            if (i == bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.mOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.M;
            if (i2 == bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        this.COff = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.C;
            if (i3 == bArr3.length) {
                return;
            }
            bArr3[i3] = 0;
            i3++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD2Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.M;
        int i = this.mOff;
        int i2 = i + 1;
        this.mOff = i2;
        bArr[i] = b;
        if (i2 == 16) {
            processCheckSum(bArr);
            processBlock(this.M);
            this.mOff = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.mOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 >= 16) {
            System.arraycopy(bArr, i, this.M, 0, 16);
            processCheckSum(this.M);
            processBlock(this.M);
            i2 -= 16;
            i += 16;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
