package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.digests.BufferBaseDigest;
import org.bouncycastle.crypto.digests.ISAPDigest;
import org.bouncycastle.crypto.engines.AsconPermutationFriend;

/* loaded from: classes3.dex */
abstract class AsconBaseDigest extends BufferBaseDigest {
    protected int ASCON_PB_ROUNDS;
    AsconPermutationFriend.AsconPermutation p;

    public static class Friend {
        private static final Friend INSTANCE = new Friend();

        private Friend() {
        }
    }

    protected AsconBaseDigest() {
        super(BufferBaseDigest.ProcessingBufferType.Immediate, 8);
        this.ASCON_PB_ROUNDS = 12;
        this.p = AsconPermutationFriend.getAsconPermutation(ISAPDigest.Friend.getFriend(Friend.INSTANCE));
        this.DigestSize = 32;
    }

    protected void ensureSufficientOutputBuffer(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new OutputLengthException("output buffer is too short");
        }
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest
    protected void finish(byte[] bArr, int i) {
        padAndAbsorb();
        squeeze(bArr, i, this.DigestSize);
    }

    protected int hash(byte[] bArr, int i, int i2) {
        ensureSufficientOutputBuffer(bArr, i, i2);
        padAndAbsorb();
        squeeze(bArr, i, i2);
        return i2;
    }

    protected abstract long loadBytes(byte[] bArr, int i);

    protected abstract long loadBytes(byte[] bArr, int i, int i2);

    protected abstract long pad(int i);

    protected void padAndAbsorb() {
        this.p.x0 ^= loadBytes(this.m_buf, 0, this.m_bufPos) ^ pad(this.m_bufPos);
        this.p.p(12);
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest
    protected void processBytes(byte[] bArr, int i) {
        AsconPermutationFriend.AsconPermutation asconPermutation = this.p;
        asconPermutation.x0 = loadBytes(bArr, i) ^ asconPermutation.x0;
        this.p.p(this.ASCON_PB_ROUNDS);
    }

    protected abstract void setBytes(long j, byte[] bArr, int i);

    protected abstract void setBytes(long j, byte[] bArr, int i, int i2);

    protected void squeeze(byte[] bArr, int i, int i2) {
        int i3 = i;
        int i4 = i2;
        while (i4 > this.BlockSize) {
            setBytes(this.p.x0, bArr, i3);
            this.p.p(this.ASCON_PB_ROUNDS);
            i3 += this.BlockSize;
            i4 -= this.BlockSize;
        }
        setBytes(this.p.x0, bArr, i3, i4);
    }
}
