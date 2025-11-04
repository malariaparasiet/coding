package org.bouncycastle.crypto.digests;

import com.alibaba.fastjson2.JSONB;
import org.bouncycastle.crypto.digests.BufferBaseDigest;
import org.bouncycastle.crypto.engines.PhotonBeetleEngine;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;

/* loaded from: classes3.dex */
public class PhotonBeetleDigest extends BufferBaseDigest {
    private static final int D = 8;
    private static final int SQUEEZE_RATE_INBYTES = 16;
    private int blockCount;
    private final byte[] state;

    public static class Friend {
        private static final Friend INSTANCE = new Friend();

        private Friend() {
        }
    }

    public PhotonBeetleDigest() {
        super(BufferBaseDigest.ProcessingBufferType.Buffered, 4);
        this.DigestSize = 32;
        this.state = new byte[this.DigestSize];
        this.algorithmName = "Photon-Beetle Hash";
        this.blockCount = 0;
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest
    protected void finish(byte[] bArr, int i) {
        if (this.m_bufPos == 0 && this.blockCount == 0) {
            byte[] bArr2 = this.state;
            int i2 = this.DigestSize - 1;
            bArr2[i2] = (byte) (bArr2[i2] ^ 32);
        } else {
            int i3 = this.blockCount;
            if (i3 < 4) {
                System.arraycopy(this.m_buf, 0, this.state, this.blockCount << 2, this.m_bufPos);
                byte[] bArr3 = this.state;
                int i4 = (this.blockCount << 2) + this.m_bufPos;
                bArr3[i4] = (byte) (bArr3[i4] ^ 1);
                byte[] bArr4 = this.state;
                int i5 = this.DigestSize - 1;
                bArr4[i5] = (byte) (bArr4[i5] ^ 32);
            } else if (i3 == 4 && this.m_bufPos == 0) {
                byte[] bArr5 = this.state;
                int i6 = this.DigestSize - 1;
                bArr5[i6] = (byte) (bArr5[i6] ^ JSONB.Constants.BC_INT32_SHORT_MIN);
            } else {
                PhotonBeetleEngine.photonPermutation(Friend.INSTANCE, this.state);
                Bytes.xorTo(this.m_bufPos, this.m_buf, this.state);
                if (this.m_bufPos < this.BlockSize) {
                    byte[] bArr6 = this.state;
                    int i7 = this.m_bufPos;
                    bArr6[i7] = (byte) (bArr6[i7] ^ 1);
                }
                byte[] bArr7 = this.state;
                int i8 = this.DigestSize - 1;
                bArr7[i8] = (byte) (((this.m_bufPos % this.BlockSize != 0 ? 2 : 1) << 5) ^ bArr7[i8]);
            }
        }
        PhotonBeetleEngine.photonPermutation(Friend.INSTANCE, this.state);
        System.arraycopy(this.state, 0, bArr, i, 16);
        PhotonBeetleEngine.photonPermutation(Friend.INSTANCE, this.state);
        System.arraycopy(this.state, 0, bArr, i + 16, 16);
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
        int i2 = this.blockCount;
        if (i2 < 4) {
            System.arraycopy(bArr, i, this.state, i2 << 2, this.BlockSize);
        } else {
            PhotonBeetleEngine.photonPermutation(Friend.INSTANCE, this.state);
            Bytes.xorTo(this.BlockSize, bArr, i, this.state);
        }
        this.blockCount++;
    }

    @Override // org.bouncycastle.crypto.digests.BufferBaseDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        Arrays.fill(this.state, (byte) 0);
        this.blockCount = 0;
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
