package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AEADBaseEngine;
import org.bouncycastle.crypto.engines.AsconPermutationFriend;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class AsconAEAD128 extends AsconBaseEngine {
    public AsconAEAD128() {
        this.BlockSize = 16;
        this.AADBufferSize = 16;
        this.MAC_SIZE = 16;
        this.IV_SIZE = 16;
        this.KEY_SIZE = 16;
        this.ASCON_IV = 17594342703105L;
        this.algorithmName = "Ascon-AEAD128";
        this.nr = 8;
        this.dsep = Long.MIN_VALUE;
        setInnerMembers(AEADBaseEngine.ProcessingBufferType.Immediate, AEADBaseEngine.AADOperatorType.Default, AEADBaseEngine.DataOperatorType.Default);
    }

    private void finishData(AEADBaseEngine.State state) {
        this.p.x2 ^= this.K0;
        this.p.x3 ^= this.K1;
        this.p.p(12);
        this.p.x3 ^= this.K0;
        this.p.x4 ^= this.K1;
        this.m_state = state;
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected void ascon_aeadinit() {
        this.p.set(this.ASCON_IV, this.K0, this.K1, this.N0, this.N1);
        this.p.p(12);
        this.p.x3 ^= this.K0;
        this.p.x4 ^= this.K1;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ String getAlgorithmName() {
        return super.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    public String getAlgorithmVersion() {
        return "v1.3";
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
        this.K0 = Pack.littleEndianToLong(bArr, 0);
        this.K1 = Pack.littleEndianToLong(bArr, 8);
        this.N0 = Pack.littleEndianToLong(bArr2, 0);
        this.N1 = Pack.littleEndianToLong(bArr2, 8);
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected long loadBytes(byte[] bArr, int i) {
        return Pack.littleEndianToLong(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected long pad(int i) {
        return 1 << (i << 3);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADByte(byte b) {
        super.processAADByte(b);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADBytes(byte[] bArr, int i, int i2) {
        super.processAADBytes(bArr, i, i2);
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
        if (this.m_aadPos == this.BlockSize) {
            this.p.x0 ^= loadBytes(this.m_aad, 0);
            this.p.x1 ^= loadBytes(this.m_aad, 8);
            this.m_aadPos -= this.BlockSize;
            this.p.p(this.nr);
        }
        Arrays.fill(this.m_aad, this.m_aadPos, this.AADBufferSize, (byte) 0);
        if (this.m_aadPos < 8) {
            this.p.x0 ^= Pack.littleEndianToLong(this.m_aad, 0) ^ pad(this.m_aadPos);
            return;
        }
        this.p.x0 ^= Pack.littleEndianToLong(this.m_aad, 0);
        AsconPermutationFriend.AsconPermutation asconPermutation = this.p;
        asconPermutation.x1 = (Pack.littleEndianToLong(this.m_aad, 8) ^ pad(this.m_aadPos)) ^ asconPermutation.x1;
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected void processFinalDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (i >= 8) {
            long littleEndianToLong = Pack.littleEndianToLong(bArr, 0);
            int i3 = i - 8;
            long littleEndianToLong2 = Pack.littleEndianToLong(bArr, 8, i3);
            Pack.longToLittleEndian(this.p.x0 ^ littleEndianToLong, bArr2, i2);
            Pack.longToLittleEndian(this.p.x1 ^ littleEndianToLong2, bArr2, i2 + 8, i3);
            this.p.x0 = littleEndianToLong;
            this.p.x1 &= -(1 << (i3 << 3));
            this.p.x1 |= littleEndianToLong2;
            this.p.x1 ^= pad(i3);
        } else {
            if (i != 0) {
                long littleEndianToLong3 = Pack.littleEndianToLong(bArr, 0, i);
                Pack.longToLittleEndian(this.p.x0 ^ littleEndianToLong3, bArr2, i2, i);
                this.p.x0 &= -(1 << (i << 3));
                this.p.x0 |= littleEndianToLong3;
            }
            this.p.x0 ^= pad(i);
        }
        finishData(AEADBaseEngine.State.DecFinal);
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected void processFinalEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (i >= 8) {
            this.p.x0 ^= Pack.littleEndianToLong(bArr, 0);
            int i3 = i - 8;
            this.p.x1 ^= Pack.littleEndianToLong(bArr, 8, i3);
            Pack.longToLittleEndian(this.p.x0, bArr2, i2);
            Pack.longToLittleEndian(this.p.x1, bArr2, i2 + 8);
            this.p.x1 ^= pad(i3);
        } else {
            if (i != 0) {
                this.p.x0 ^= Pack.littleEndianToLong(bArr, 0, i);
                Pack.longToLittleEndian(this.p.x0, bArr2, i2, i);
            }
            this.p.x0 ^= pad(i);
        }
        finishData(AEADBaseEngine.State.EncFinal);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected void setBytes(long j, byte[] bArr, int i) {
        Pack.longToLittleEndian(j, bArr, i);
    }
}
