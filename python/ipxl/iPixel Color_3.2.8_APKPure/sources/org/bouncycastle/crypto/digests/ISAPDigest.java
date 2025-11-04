package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.AsconBaseDigest;
import org.bouncycastle.crypto.digests.BufferBaseDigest;
import org.bouncycastle.crypto.engines.AsconPermutationFriend;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class ISAPDigest extends BufferBaseDigest {
    private final AsconPermutationFriend.AsconPermutation p;

    public static class Friend {
        private static final Friend INSTANCE = new Friend();

        private Friend() {
        }

        static Friend getFriend(AsconBaseDigest.Friend friend) {
            if (friend != null) {
                return INSTANCE;
            }
            throw new NullPointerException("This method is only for use by AsconBaseDigest");
        }
    }

    public ISAPDigest() {
        super(BufferBaseDigest.ProcessingBufferType.Immediate, 8);
        this.p = AsconPermutationFriend.getAsconPermutation(Friend.INSTANCE);
        this.DigestSize = 32;
        this.algorithmName = "ISAP Hash";
        reset();
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest
    protected void finish(byte[] bArr, int i) {
        AsconPermutationFriend.AsconPermutation asconPermutation = this.p;
        long j = asconPermutation.x0;
        long j2 = 128 << ((7 - this.m_bufPos) << 3);
        while (true) {
            asconPermutation.x0 = j ^ j2;
            if (this.m_bufPos <= 0) {
                break;
            }
            asconPermutation = this.p;
            j = asconPermutation.x0;
            byte[] bArr2 = this.m_buf;
            this.m_bufPos = this.m_bufPos - 1;
            j2 = (bArr2[r4] & 255) << ((7 - this.m_bufPos) << 3);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.p.p(12);
            Pack.longToBigEndian(this.p.x0, bArr, i);
            i += 8;
        }
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public /* bridge */ /* synthetic */ String getAlgorithmName() {
        return super.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.ExtendedDigest
    public /* bridge */ /* synthetic */ int getByteLength() {
        return super.getByteLength();
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public /* bridge */ /* synthetic */ int getDigestSize() {
        return super.getDigestSize();
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest
    protected void processBytes(byte[] bArr, int i) {
        AsconPermutationFriend.AsconPermutation asconPermutation = this.p;
        asconPermutation.x0 = Pack.bigEndianToLong(bArr, i) ^ asconPermutation.x0;
        this.p.p(12);
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.p.set(-1255492011513352131L, -8380609354527731710L, -5437372128236807582L, 4834782570098516968L, 3787428097924915520L);
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public /* bridge */ /* synthetic */ void update(byte b) {
        super.update(b);
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public /* bridge */ /* synthetic */ void update(byte[] bArr, int i, int i2) {
        super.update(bArr, i, i2);
    }
}
