package org.bouncycastle.crypto;

import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public abstract class DefaultMultiBlockCipher implements MultiBlockCipher {
    protected DefaultMultiBlockCipher() {
    }

    @Override // org.bouncycastle.crypto.MultiBlockCipher
    public int getMultiBlockSize() {
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.MultiBlockCipher
    public int processBlocks(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        int multiBlockSize = getMultiBlockSize();
        int i4 = i2 * multiBlockSize;
        if (bArr == bArr2 && Arrays.segmentsOverlap(i, i4, i3, i4)) {
            bArr = new byte[i4];
            System.arraycopy(bArr2, i, bArr, 0, i4);
            i = 0;
        }
        int i5 = 0;
        for (int i6 = 0; i6 != i2; i6++) {
            i5 += processBlock(bArr, i, bArr2, i3 + i5);
            i += multiBlockSize;
        }
        return i5;
    }
}
