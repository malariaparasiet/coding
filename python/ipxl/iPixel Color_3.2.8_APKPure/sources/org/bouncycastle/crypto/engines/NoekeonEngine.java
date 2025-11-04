package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.JSONB;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class NoekeonEngine implements BlockCipher {
    private static final int SIZE = 16;
    private static final byte[] roundConstants = {ByteCompanionObject.MIN_VALUE, 27, 54, 108, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MILLIS, JSONB.Constants.BC_STR_ASCII_FIX_4, -102, JSONB.Constants.BC_INT32_NUM_MAX, 94, -68, 99, -58, -105, 53, 106, -44};
    private boolean _forEncryption;
    private final int[] k = new int[4];
    private boolean _initialised = false;

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, i);
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, i + 4);
        int bigEndianToInt3 = Pack.bigEndianToInt(bArr, i + 8);
        int bigEndianToInt4 = Pack.bigEndianToInt(bArr, i + 12);
        int[] iArr = this.k;
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = 16;
        while (true) {
            int i8 = bigEndianToInt ^ bigEndianToInt3;
            int rotateLeft = i8 ^ (Integers.rotateLeft(i8, 8) ^ Integers.rotateLeft(i8, 24));
            int i9 = bigEndianToInt2 ^ i4;
            int i10 = bigEndianToInt4 ^ i6;
            int i11 = i9 ^ i10;
            int rotateLeft2 = i11 ^ (Integers.rotateLeft(i11, 24) ^ Integers.rotateLeft(i11, 8));
            int i12 = i9 ^ rotateLeft;
            int i13 = (bigEndianToInt3 ^ i5) ^ rotateLeft2;
            int i14 = i10 ^ rotateLeft;
            int i15 = ((bigEndianToInt ^ i3) ^ rotateLeft2) ^ (roundConstants[i7] & UByte.MAX_VALUE);
            i7--;
            if (i7 < 0) {
                Pack.intToBigEndian(i15, bArr2, i2);
                Pack.intToBigEndian(i12, bArr2, i2 + 4);
                Pack.intToBigEndian(i13, bArr2, i2 + 8);
                Pack.intToBigEndian(i14, bArr2, i2 + 12);
                return 16;
            }
            int rotateLeft3 = Integers.rotateLeft(i12, 1);
            int rotateLeft4 = Integers.rotateLeft(i13, 5);
            int rotateLeft5 = Integers.rotateLeft(i14, 2);
            int i16 = rotateLeft3 ^ (rotateLeft5 | rotateLeft4);
            int i17 = ~i16;
            int i18 = i15 ^ (rotateLeft4 & i17);
            int i19 = (rotateLeft4 ^ (i17 ^ rotateLeft5)) ^ i18;
            int i20 = i16 ^ (i18 | i19);
            int i21 = rotateLeft5 ^ (i19 & i20);
            bigEndianToInt2 = Integers.rotateLeft(i20, 31);
            bigEndianToInt3 = Integers.rotateLeft(i19, 27);
            int rotateLeft6 = Integers.rotateLeft(i18, 30);
            bigEndianToInt = i21;
            bigEndianToInt4 = rotateLeft6;
        }
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, i);
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, i + 4);
        int bigEndianToInt3 = Pack.bigEndianToInt(bArr, i + 8);
        int bigEndianToInt4 = Pack.bigEndianToInt(bArr, i + 12);
        int[] iArr = this.k;
        int i3 = 0;
        int i4 = iArr[0];
        int i5 = 1;
        int i6 = iArr[1];
        int i7 = iArr[2];
        int i8 = iArr[3];
        while (true) {
            int i9 = bigEndianToInt ^ (roundConstants[i3] & UByte.MAX_VALUE);
            int i10 = i9 ^ bigEndianToInt3;
            int rotateLeft = i10 ^ (Integers.rotateLeft(i10, 8) ^ Integers.rotateLeft(i10, 24));
            int i11 = bigEndianToInt2 ^ i6;
            int i12 = bigEndianToInt4 ^ i8;
            int i13 = i5;
            int i14 = i11 ^ i12;
            int rotateLeft2 = i14 ^ (Integers.rotateLeft(i14, 24) ^ Integers.rotateLeft(i14, 8));
            int i15 = (i9 ^ i4) ^ rotateLeft2;
            int i16 = i11 ^ rotateLeft;
            int i17 = (bigEndianToInt3 ^ i7) ^ rotateLeft2;
            int i18 = i12 ^ rotateLeft;
            i3++;
            if (i3 > 16) {
                Pack.intToBigEndian(i15, bArr2, i2);
                Pack.intToBigEndian(i16, bArr2, i2 + 4);
                Pack.intToBigEndian(i17, bArr2, i2 + 8);
                Pack.intToBigEndian(i18, bArr2, i2 + 12);
                return 16;
            }
            i5 = i13;
            int rotateLeft3 = Integers.rotateLeft(i16, i5);
            int rotateLeft4 = Integers.rotateLeft(i17, 5);
            int rotateLeft5 = Integers.rotateLeft(i18, 2);
            int i19 = rotateLeft3 ^ (rotateLeft5 | rotateLeft4);
            int i20 = ~i19;
            int i21 = i15 ^ (rotateLeft4 & i20);
            int i22 = (rotateLeft4 ^ (i20 ^ rotateLeft5)) ^ i21;
            int i23 = i19 ^ (i21 | i22);
            int i24 = rotateLeft5 ^ (i22 & i23);
            bigEndianToInt2 = Integers.rotateLeft(i23, 31);
            bigEndianToInt3 = Integers.rotateLeft(i22, 27);
            int rotateLeft6 = Integers.rotateLeft(i21, 30);
            bigEndianToInt = i24;
            bigEndianToInt4 = rotateLeft6;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Noekeon";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Noekeon init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("Key length not 128 bits.");
        }
        Pack.bigEndianToInt(key, 0, this.k, 0, 4);
        if (!z) {
            int[] iArr = this.k;
            int i = iArr[0];
            int i2 = iArr[1];
            int i3 = iArr[2];
            int i4 = iArr[3];
            int i5 = i ^ i3;
            int rotateLeft = i5 ^ (Integers.rotateLeft(i5, 8) ^ Integers.rotateLeft(i5, 24));
            int i6 = i2 ^ i4;
            int rotateLeft2 = (Integers.rotateLeft(i6, 8) ^ Integers.rotateLeft(i6, 24)) ^ i6;
            int i7 = i2 ^ rotateLeft;
            int i8 = i4 ^ rotateLeft;
            int[] iArr2 = this.k;
            iArr2[0] = i ^ rotateLeft2;
            iArr2[1] = i7;
            iArr2[2] = i3 ^ rotateLeft2;
            iArr2[3] = i8;
        }
        this._forEncryption = z;
        this._initialised = true;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this._initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 <= bArr2.length - 16) {
            return this._forEncryption ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
