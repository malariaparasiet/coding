package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.engines.AEADBaseEngine;
import org.bouncycastle.crypto.engines.AsconPermutationFriend;

/* loaded from: classes3.dex */
abstract class AsconBaseEngine extends AEADBaseEngine {
    protected long ASCON_IV;
    protected long K0;
    protected long K1;
    protected long N0;
    protected long N1;
    protected long dsep;
    protected int nr;
    AsconPermutationFriend.AsconPermutation p = new AsconPermutationFriend.AsconPermutation();

    AsconBaseEngine() {
    }

    protected abstract void ascon_aeadinit();

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void finishAAD(AEADBaseEngine.State state, boolean z) {
        int i = this.m_state.ord;
        if (i == 2 || i == 6) {
            processFinalAAD();
            this.p.p(this.nr);
        }
        this.p.x4 ^= this.dsep;
        this.m_aadPos = 0;
        this.m_state = state;
    }

    public abstract String getAlgorithmVersion();

    protected abstract long loadBytes(byte[] bArr, int i);

    protected abstract long pad(int i);

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferAAD(byte[] bArr, int i) {
        this.p.x0 ^= loadBytes(bArr, i);
        if (this.BlockSize == 16) {
            AsconPermutationFriend.AsconPermutation asconPermutation = this.p;
            asconPermutation.x1 = loadBytes(bArr, i + 8) ^ asconPermutation.x1;
        }
        this.p.p(this.nr);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        long loadBytes = loadBytes(bArr, i);
        setBytes(this.p.x0 ^ loadBytes, bArr2, i2);
        this.p.x0 = loadBytes;
        if (this.BlockSize == 16) {
            long loadBytes2 = loadBytes(bArr, i + 8);
            setBytes(this.p.x1 ^ loadBytes2, bArr2, i2 + 8);
            this.p.x1 = loadBytes2;
        }
        this.p.p(this.nr);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.p.x0 ^= loadBytes(bArr, i);
        setBytes(this.p.x0, bArr2, i2);
        if (this.BlockSize == 16) {
            AsconPermutationFriend.AsconPermutation asconPermutation = this.p;
            asconPermutation.x1 = loadBytes(bArr, i + 8) ^ asconPermutation.x1;
            setBytes(this.p.x1, bArr2, i2 + 8);
        }
        this.p.p(this.nr);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        if (this.forEncryption) {
            processFinalEncrypt(this.m_buf, this.m_bufPos, bArr, i);
        } else {
            processFinalDecrypt(this.m_buf, this.m_bufPos, bArr, i);
        }
        setBytes(this.p.x3, this.mac, 0);
        setBytes(this.p.x4, this.mac, 8);
    }

    protected abstract void processFinalDecrypt(byte[] bArr, int i, byte[] bArr2, int i2);

    protected abstract void processFinalEncrypt(byte[] bArr, int i, byte[] bArr2, int i2);

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        super.reset(z);
        ascon_aeadinit();
    }

    protected abstract void setBytes(long j, byte[] bArr, int i);
}
