package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.Xof;

/* loaded from: classes3.dex */
abstract class AsconXofBase extends AsconBaseDigest implements Xof {
    private final byte[] buffer = new byte[this.BlockSize];
    private int bytesInBuffer;
    private boolean m_squeezing;

    AsconXofBase() {
    }

    private void ensureNoAbsorbWhileSqueezing(boolean z) {
        if (z) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
    }

    public int doFinal(byte[] bArr, int i, int i2) {
        int doOutput = doOutput(bArr, i, i2);
        reset();
        return doOutput;
    }

    public int doOutput(byte[] bArr, int i, int i2) {
        int i3;
        ensureSufficientOutputBuffer(bArr, i, i2);
        if (this.bytesInBuffer != 0) {
            int i4 = this.BlockSize;
            int i5 = this.bytesInBuffer;
            int i6 = i4 - i5;
            i3 = Math.min(i2, i5);
            System.arraycopy(this.buffer, i6, bArr, i, i3);
            this.bytesInBuffer -= i3;
        } else {
            i3 = 0;
        }
        int i7 = i2 - i3;
        if (i7 >= this.BlockSize) {
            i3 += hash(bArr, i + i3, i7 - (i7 % this.BlockSize));
        }
        if (i3 >= i2) {
            return i3;
        }
        hash(this.buffer, 0, this.BlockSize);
        int i8 = i2 - i3;
        System.arraycopy(this.buffer, 0, bArr, i + i3, i8);
        this.bytesInBuffer = this.buffer.length - i8;
        return i3 + i8;
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest
    protected void padAndAbsorb() {
        if (this.m_squeezing) {
            this.p.p(this.ASCON_PB_ROUNDS);
        } else {
            this.m_squeezing = true;
            super.padAndAbsorb();
        }
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        this.m_squeezing = false;
        this.bytesInBuffer = 0;
        super.reset();
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public void update(byte b) {
        ensureNoAbsorbWhileSqueezing(this.m_squeezing);
        super.update(b);
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        ensureNoAbsorbWhileSqueezing(this.m_squeezing);
        super.update(bArr, i, i2);
    }
}
