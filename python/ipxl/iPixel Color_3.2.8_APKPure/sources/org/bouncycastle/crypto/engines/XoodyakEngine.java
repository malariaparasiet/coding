package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.XoodyakDigest;
import org.bouncycastle.crypto.engines.AEADBaseEngine;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class XoodyakEngine extends AEADBaseEngine {
    private static final int ModeHash = 1;
    private static final int ModeKeyed = 0;
    private static final int PhaseDown = 1;
    private static final int PhaseUp = 2;
    private static final int[] RC = {88, 56, 960, 208, 288, 20, 96, 44, 896, 240, 416, 18};
    private static final int f_bPrime_1 = 47;
    private byte[] K;
    private byte aadcd;
    private boolean encrypted;
    private byte[] iv;
    private int mode;
    private int phase;
    private final byte[] state;

    public XoodyakEngine() {
        this.algorithmName = "Xoodyak AEAD";
        this.MAC_SIZE = 16;
        this.IV_SIZE = 16;
        this.KEY_SIZE = 16;
        this.BlockSize = 24;
        this.AADBufferSize = 44;
        this.state = new byte[48];
        setInnerMembers(AEADBaseEngine.ProcessingBufferType.Immediate, AEADBaseEngine.AADOperatorType.Default, AEADBaseEngine.DataOperatorType.Counter);
    }

    private void AbsorbAny(byte[] bArr, int i, int i2, int i3) {
        if (this.phase != 2) {
            up(this.mode, this.state, 0);
        }
        int i4 = i;
        int i5 = i3;
        while (true) {
            int min = Math.min(i2, this.AADBufferSize);
            byte[] bArr2 = bArr;
            down(this.mode, this.state, bArr2, i4, min, i5);
            this.phase = 1;
            i4 += min;
            i2 -= min;
            if (i2 == 0) {
                return;
            }
            i5 = 0;
            bArr = bArr2;
        }
    }

    private static void down(int i, byte[] bArr, byte[] bArr2, int i2, int i3, int i4) {
        Bytes.xorTo(i3, bArr2, i2, bArr);
        bArr[i3] = (byte) (bArr[i3] ^ 1);
        int i5 = bArr[47];
        if (i == 1) {
            i4 &= 1;
        }
        bArr[47] = (byte) (i5 ^ i4);
    }

    public static void down(XoodyakDigest.Friend friend, int i, byte[] bArr, byte[] bArr2, int i2, int i3, int i4) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by XoodyakDigest");
        }
        down(i, bArr, bArr2, i2, i3, i4);
    }

    private static void up(int i, byte[] bArr, int i2) {
        if (i != 1) {
            bArr[47] = (byte) (bArr[47] ^ i2);
        }
        int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
        int i3 = 12;
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
        int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 16);
        int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 20);
        int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 24);
        int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 28);
        int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 32);
        int i4 = 0;
        int i5 = littleEndianToInt8;
        int i6 = littleEndianToInt9;
        int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 36);
        int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 40);
        int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 44);
        while (i4 < i3) {
            int i7 = (littleEndianToInt ^ littleEndianToInt5) ^ i6;
            int i8 = (littleEndianToInt2 ^ littleEndianToInt6) ^ littleEndianToInt10;
            int i9 = i4;
            int i10 = (littleEndianToInt3 ^ littleEndianToInt7) ^ littleEndianToInt11;
            int i11 = littleEndianToInt12;
            int i12 = (littleEndianToInt4 ^ i5) ^ i11;
            int i13 = littleEndianToInt11;
            int rotateLeft = Integers.rotateLeft(i12, 5) ^ Integers.rotateLeft(i12, 14);
            int rotateLeft2 = Integers.rotateLeft(i7, 5) ^ Integers.rotateLeft(i7, 14);
            int rotateLeft3 = Integers.rotateLeft(i8, 5) ^ Integers.rotateLeft(i8, 14);
            int rotateLeft4 = Integers.rotateLeft(i10, 5) ^ Integers.rotateLeft(i10, 14);
            int i14 = littleEndianToInt ^ rotateLeft;
            int i15 = littleEndianToInt5 ^ rotateLeft;
            int i16 = i6 ^ rotateLeft;
            int i17 = littleEndianToInt2 ^ rotateLeft2;
            int i18 = littleEndianToInt6 ^ rotateLeft2;
            int i19 = rotateLeft2 ^ littleEndianToInt10;
            int i20 = littleEndianToInt3 ^ rotateLeft3;
            int i21 = littleEndianToInt7 ^ rotateLeft3;
            int i22 = littleEndianToInt4 ^ rotateLeft4;
            int i23 = i5 ^ rotateLeft4;
            int rotateLeft5 = Integers.rotateLeft(i16, 11);
            int rotateLeft6 = Integers.rotateLeft(i19, 11);
            int rotateLeft7 = Integers.rotateLeft(i13 ^ rotateLeft3, 11);
            int rotateLeft8 = Integers.rotateLeft(i11 ^ rotateLeft4, 11);
            int i24 = i14 ^ RC[i9];
            int i25 = ((~i23) & rotateLeft5) ^ i24;
            int i26 = ((~i15) & rotateLeft6) ^ i17;
            int i27 = ((~i18) & rotateLeft7) ^ i20;
            int i28 = ((~i21) & rotateLeft8) ^ i22;
            int i29 = ((~rotateLeft5) & i24) ^ i23;
            int i30 = i15 ^ ((~rotateLeft6) & i17);
            int i31 = ((~rotateLeft7) & i20) ^ i18;
            int i32 = ((~rotateLeft8) & i22) ^ i21;
            int i33 = rotateLeft5 ^ ((~i24) & i23);
            int i34 = rotateLeft6 ^ ((~i17) & i15);
            int i35 = rotateLeft7 ^ ((~i20) & i18);
            int i36 = rotateLeft8 ^ ((~i22) & i21);
            littleEndianToInt5 = Integers.rotateLeft(i29, 1);
            int rotateLeft9 = Integers.rotateLeft(i30, 1);
            int rotateLeft10 = Integers.rotateLeft(i31, 1);
            int rotateLeft11 = Integers.rotateLeft(i32, 1);
            i6 = Integers.rotateLeft(i35, 8);
            littleEndianToInt10 = Integers.rotateLeft(i36, 8);
            littleEndianToInt11 = Integers.rotateLeft(i33, 8);
            littleEndianToInt12 = Integers.rotateLeft(i34, 8);
            littleEndianToInt = i25;
            littleEndianToInt6 = rotateLeft9;
            littleEndianToInt3 = i27;
            i3 = 12;
            littleEndianToInt7 = rotateLeft10;
            i4 = i9 + 1;
            littleEndianToInt2 = i26;
            i5 = rotateLeft11;
            littleEndianToInt4 = i28;
        }
        Pack.intToLittleEndian(littleEndianToInt, bArr, 0);
        Pack.intToLittleEndian(littleEndianToInt2, bArr, 4);
        Pack.intToLittleEndian(littleEndianToInt3, bArr, 8);
        Pack.intToLittleEndian(littleEndianToInt4, bArr, 12);
        Pack.intToLittleEndian(littleEndianToInt5, bArr, 16);
        Pack.intToLittleEndian(littleEndianToInt6, bArr, 20);
        Pack.intToLittleEndian(littleEndianToInt7, bArr, 24);
        Pack.intToLittleEndian(i5, bArr, 28);
        Pack.intToLittleEndian(i6, bArr, 32);
        Pack.intToLittleEndian(littleEndianToInt10, bArr, 36);
        Pack.intToLittleEndian(littleEndianToInt11, bArr, 40);
        Pack.intToLittleEndian(littleEndianToInt12, bArr, 44);
    }

    public static void up(XoodyakDigest.Friend friend, int i, byte[] bArr, int i2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by XoodyakDigest");
        }
        up(i, bArr, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void finishAAD(AEADBaseEngine.State state, boolean z) {
        finishAAD3(state, z);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ String getAlgorithmName() {
        return super.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    public /* bridge */ /* synthetic */ int getIVBytesSize() {
        return super.getIVBytesSize();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    public /* bridge */ /* synthetic */ int getKeyBytesSize() {
        return super.getKeyBytesSize();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ byte[] getMac() {
        return super.getMac();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int getOutputSize(int i) {
        return super.getOutputSize(i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int getUpdateOutputSize(int i) {
        return super.getUpdateOutputSize(i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void init(boolean z, CipherParameters cipherParameters) {
        super.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void init(byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        this.K = bArr;
        this.iv = bArr2;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADByte(byte b) {
        super.processAADByte(b);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADBytes(byte[] bArr, int i, int i2) {
        super.processAADBytes(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferAAD(byte[] bArr, int i) {
        AbsorbAny(bArr, i, this.AADBufferSize, this.aadcd);
        this.aadcd = (byte) 0;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        up(this.mode, this.state, this.encrypted ? 0 : 128);
        Bytes.xor(this.BlockSize, this.state, bArr, i, bArr2, i2);
        down(this.mode, this.state, bArr2, i2, this.BlockSize, 0);
        this.phase = 1;
        this.encrypted = true;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        up(this.mode, this.state, this.encrypted ? 0 : 128);
        Bytes.xor(this.BlockSize, this.state, bArr, i, bArr2, i2);
        down(this.mode, this.state, bArr, i, this.BlockSize, 0);
        this.phase = 1;
        this.encrypted = true;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return super.processByte(b, bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        return super.processBytes(bArr, i, i2, bArr2, i3);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalAAD() {
        AbsorbAny(this.m_aad, 0, this.m_aadPos, this.aadcd);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        if (this.m_bufPos != 0 || !this.encrypted) {
            up(this.mode, this.state, this.encrypted ? 0 : 128);
            Bytes.xor(this.m_bufPos, this.state, this.m_buf, 0, bArr, i);
            if (this.forEncryption) {
                down(this.mode, this.state, this.m_buf, 0, this.m_bufPos, 0);
            } else {
                down(this.mode, this.state, bArr, i, this.m_bufPos, 0);
            }
            this.phase = 1;
        }
        up(this.mode, this.state, 64);
        System.arraycopy(this.state, 0, this.mac, 0, this.MAC_SIZE);
        this.phase = 2;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        super.reset(z);
        Arrays.fill(this.state, (byte) 0);
        this.encrypted = false;
        this.phase = 2;
        this.aadcd = (byte) 3;
        int length = this.K.length;
        int length2 = this.iv.length;
        byte[] bArr = new byte[this.AADBufferSize];
        this.mode = 0;
        System.arraycopy(this.K, 0, bArr, 0, length);
        System.arraycopy(this.iv, 0, bArr, length, length2);
        int i = length + length2;
        bArr[i] = (byte) length2;
        AbsorbAny(bArr, 0, i + 1, 2);
    }
}
