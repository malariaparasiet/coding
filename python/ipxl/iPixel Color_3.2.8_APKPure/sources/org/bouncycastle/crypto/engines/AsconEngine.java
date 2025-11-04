package org.bouncycastle.crypto.engines;

import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AEADBaseEngine;
import org.bouncycastle.crypto.engines.AsconPermutationFriend;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class AsconEngine extends AsconBaseEngine {
    private long K2;
    private final AsconParameters asconParameters;

    public enum AsconParameters {
        ascon80pq,
        ascon128a,
        ascon128
    }

    public AsconEngine(AsconParameters asconParameters) {
        String str;
        this.asconParameters = asconParameters;
        this.MAC_SIZE = 16;
        this.IV_SIZE = 16;
        int ordinal = asconParameters.ordinal();
        if (ordinal == 0) {
            this.KEY_SIZE = 20;
            this.BlockSize = 8;
            this.ASCON_IV = -6899501409222262784L;
            str = "Ascon-80pq AEAD";
        } else if (ordinal == 1) {
            this.KEY_SIZE = 16;
            this.BlockSize = 16;
            this.ASCON_IV = -9187330011336540160L;
            str = "Ascon-128a AEAD";
        } else {
            if (ordinal != 2) {
                throw new IllegalArgumentException("invalid parameter setting for ASCON AEAD");
            }
            this.KEY_SIZE = 16;
            this.BlockSize = 8;
            this.ASCON_IV = -9205344418435956736L;
            str = "Ascon-128 AEAD";
        }
        this.algorithmName = str;
        this.nr = this.BlockSize == 8 ? 6 : 8;
        this.AADBufferSize = this.BlockSize;
        this.dsep = 1L;
        setInnerMembers(AEADBaseEngine.ProcessingBufferType.Immediate, AEADBaseEngine.AADOperatorType.Default, AEADBaseEngine.DataOperatorType.Default);
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected void ascon_aeadinit() {
        this.p.set(this.ASCON_IV, this.K1, this.K2, this.N0, this.N1);
        if (this.KEY_SIZE == 20) {
            this.p.x0 ^= this.K0;
        }
        this.p.p(12);
        if (this.KEY_SIZE == 20) {
            this.p.x2 ^= this.K0;
        }
        this.p.x3 ^= this.K1;
        this.p.x4 ^= this.K2;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        return super.doFinal(bArr, i);
    }

    protected void finishData(AEADBaseEngine.State state) {
        AsconPermutationFriend.AsconPermutation asconPermutation;
        long j;
        long j2;
        int ordinal = this.asconParameters.ordinal();
        if (ordinal == 0) {
            this.p.x1 ^= (this.K0 << 32) | (this.K1 >> 32);
            this.p.x2 ^= (this.K1 << 32) | (this.K2 >> 32);
            asconPermutation = this.p;
            j = asconPermutation.x3;
            j2 = this.K2 << 32;
        } else {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    throw new IllegalStateException();
                }
                this.p.x1 ^= this.K1;
                this.p.x2 ^= this.K2;
                this.p.p(12);
                this.p.x3 ^= this.K1;
                this.p.x4 ^= this.K2;
                this.m_state = state;
            }
            this.p.x2 ^= this.K1;
            asconPermutation = this.p;
            j = asconPermutation.x3;
            j2 = this.K2;
        }
        asconPermutation.x3 = j ^ j2;
        this.p.p(12);
        this.p.x3 ^= this.K1;
        this.p.x4 ^= this.K2;
        this.m_state = state;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ String getAlgorithmName() {
        return super.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    public String getAlgorithmVersion() {
        return "v1.2";
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
        long bigEndianToLong;
        this.N0 = Pack.bigEndianToLong(bArr2, 0);
        this.N1 = Pack.bigEndianToLong(bArr2, 8);
        if (this.KEY_SIZE == 16) {
            this.K1 = Pack.bigEndianToLong(bArr, 0);
            bigEndianToLong = Pack.bigEndianToLong(bArr, 8);
        } else {
            if (this.KEY_SIZE != 20) {
                throw new IllegalStateException();
            }
            this.K0 = Pack.bigEndianToInt(bArr, 0);
            this.K1 = Pack.bigEndianToLong(bArr, 4);
            bigEndianToLong = Pack.bigEndianToLong(bArr, 12);
        }
        this.K2 = bigEndianToLong;
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected long loadBytes(byte[] bArr, int i) {
        return Pack.bigEndianToLong(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected long pad(int i) {
        return 128 << (56 - (i << 3));
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
        this.m_aad[this.m_aadPos] = ByteCompanionObject.MIN_VALUE;
        if (this.m_aadPos < 8) {
            AsconPermutationFriend.AsconPermutation asconPermutation = this.p;
            asconPermutation.x0 = (((-1) << (56 - (this.m_aadPos << 3))) & Pack.bigEndianToLong(this.m_aad, 0)) ^ asconPermutation.x0;
            return;
        }
        this.p.x0 ^= Pack.bigEndianToLong(this.m_aad, 0);
        AsconPermutationFriend.AsconPermutation asconPermutation2 = this.p;
        asconPermutation2.x1 = (((-1) << (56 - ((this.m_aadPos - 8) << 3))) & Pack.bigEndianToLong(this.m_aad, 8)) ^ asconPermutation2.x1;
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected void processFinalDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (i >= 8) {
            long bigEndianToLong = Pack.bigEndianToLong(bArr, 0);
            this.p.x0 ^= bigEndianToLong;
            Pack.longToBigEndian(this.p.x0, bArr2, i2);
            this.p.x0 = bigEndianToLong;
            int i3 = i2 + 8;
            int i4 = i - 8;
            this.p.x1 ^= pad(i4);
            if (i4 != 0) {
                long littleEndianToLong_High = Pack.littleEndianToLong_High(bArr, 8, i4);
                this.p.x1 ^= littleEndianToLong_High;
                Pack.longToLittleEndian_High(this.p.x1, bArr2, i3, i4);
                this.p.x1 &= (-1) >>> (i4 << 3);
                this.p.x1 ^= littleEndianToLong_High;
            }
        } else {
            this.p.x0 ^= pad(i);
            if (i != 0) {
                long littleEndianToLong_High2 = Pack.littleEndianToLong_High(bArr, 0, i);
                this.p.x0 ^= littleEndianToLong_High2;
                Pack.longToLittleEndian_High(this.p.x0, bArr2, i2, i);
                this.p.x0 &= (-1) >>> (i << 3);
                this.p.x0 ^= littleEndianToLong_High2;
            }
        }
        finishData(AEADBaseEngine.State.DecFinal);
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected void processFinalEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        long j;
        if (i >= 8) {
            this.p.x0 ^= Pack.bigEndianToLong(bArr, 0);
            Pack.longToBigEndian(this.p.x0, bArr2, i2);
            i2 += 8;
            i -= 8;
            this.p.x1 ^= pad(i);
            if (i != 0) {
                this.p.x1 ^= Pack.littleEndianToLong_High(bArr, 8, i);
                j = this.p.x1;
                Pack.longToLittleEndian_High(j, bArr2, i2, i);
            }
        } else {
            this.p.x0 ^= pad(i);
            if (i != 0) {
                this.p.x0 ^= Pack.littleEndianToLong_High(bArr, 0, i);
                j = this.p.x0;
                Pack.longToLittleEndian_High(j, bArr2, i2, i);
            }
        }
        finishData(AEADBaseEngine.State.EncFinal);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AsconBaseEngine
    protected void setBytes(long j, byte[] bArr, int i) {
        Pack.longToBigEndian(j, bArr, i);
    }
}
