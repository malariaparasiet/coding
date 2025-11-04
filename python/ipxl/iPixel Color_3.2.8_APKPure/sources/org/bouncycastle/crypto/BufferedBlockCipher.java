package org.bouncycastle.crypto;

import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class BufferedBlockCipher {
    protected byte[] buf;
    protected int bufOff;
    protected BlockCipher cipher;
    protected boolean forEncryption;
    protected MultiBlockCipher mbCipher;
    protected boolean partialBlockOkay;
    protected boolean pgpCFB;

    BufferedBlockCipher() {
    }

    public BufferedBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        if (blockCipher instanceof MultiBlockCipher) {
            MultiBlockCipher multiBlockCipher = (MultiBlockCipher) blockCipher;
            this.mbCipher = multiBlockCipher;
            this.buf = new byte[multiBlockCipher.getMultiBlockSize()];
        } else {
            this.mbCipher = null;
            this.buf = new byte[blockCipher.getBlockSize()];
        }
        boolean z = false;
        this.bufOff = 0;
        String algorithmName = blockCipher.getAlgorithmName();
        int indexOf = algorithmName.indexOf(47) + 1;
        boolean z2 = indexOf > 0 && algorithmName.startsWith("PGP", indexOf);
        this.pgpCFB = z2;
        if (z2 || (blockCipher instanceof StreamCipher)) {
            this.partialBlockOkay = true;
            return;
        }
        if (indexOf > 0 && algorithmName.startsWith("OpenPGP", indexOf)) {
            z = true;
        }
        this.partialBlockOkay = z;
    }

    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
        try {
            int i2 = this.bufOff;
            if (i + i2 > bArr.length) {
                throw new OutputLengthException("output buffer too short for doFinal()");
            }
            int i3 = 0;
            if (i2 != 0) {
                if (!this.partialBlockOkay) {
                    throw new DataLengthException("data not block size aligned");
                }
                BlockCipher blockCipher = this.cipher;
                byte[] bArr2 = this.buf;
                blockCipher.processBlock(bArr2, 0, bArr2, 0);
                int i4 = this.bufOff;
                this.bufOff = 0;
                System.arraycopy(this.buf, 0, bArr, i, i4);
                i3 = i4;
            }
            return i3;
        } finally {
            reset();
        }
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int getOutputSize(int i) {
        int i2;
        if (this.pgpCFB && this.forEncryption) {
            i += this.bufOff;
            i2 = this.cipher.getBlockSize() + 2;
        } else {
            i2 = this.bufOff;
        }
        return i + i2;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public int getUpdateOutputSize(int i) {
        int length;
        int i2;
        int i3 = i + this.bufOff;
        if (!this.pgpCFB) {
            length = this.buf.length;
        } else {
            if (this.forEncryption) {
                i2 = (i3 % this.buf.length) - (this.cipher.getBlockSize() + 2);
                return i3 - i2;
            }
            length = this.buf.length;
        }
        i2 = i3 % length;
        return i3 - i2;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = z;
        reset();
        this.cipher.init(z, cipherParameters);
    }

    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        byte[] bArr2 = this.buf;
        int i2 = this.bufOff;
        int i3 = i2 + 1;
        this.bufOff = i3;
        bArr2[i2] = b;
        if (i3 != bArr2.length) {
            return 0;
        }
        int processBlock = this.cipher.processBlock(bArr2, 0, bArr, i);
        this.bufOff = 0;
        return processBlock;
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        byte[] bArr3;
        int i4;
        int i5;
        int i6;
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int blockSize = getBlockSize();
        int updateOutputSize = getUpdateOutputSize(i2);
        if (updateOutputSize > 0 && i3 + updateOutputSize > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr4 = this.buf;
        int length = bArr4.length;
        int i7 = this.bufOff;
        int i8 = length - i7;
        if (i2 > i8) {
            System.arraycopy(bArr, i, bArr4, i7, i8);
            int i9 = i + i8;
            i5 = i2 - i8;
            if (bArr == bArr2 && Arrays.segmentsOverlap(i9, i5, i3, updateOutputSize)) {
                byte[] bArr5 = new byte[i5];
                System.arraycopy(bArr2, i9, bArr5, 0, i5);
                bArr3 = bArr5;
                i4 = 0;
            } else {
                i4 = i9;
                bArr3 = bArr;
            }
            i6 = this.cipher.processBlock(this.buf, 0, bArr2, i3);
            this.bufOff = 0;
            MultiBlockCipher multiBlockCipher = this.mbCipher;
            if (multiBlockCipher != null) {
                int multiBlockSize = i5 / multiBlockCipher.getMultiBlockSize();
                if (multiBlockSize > 0) {
                    i6 += this.mbCipher.processBlocks(bArr3, i4, multiBlockSize, bArr2, i3 + i6);
                    int multiBlockSize2 = multiBlockSize * this.mbCipher.getMultiBlockSize();
                    i5 -= multiBlockSize2;
                    i4 += multiBlockSize2;
                }
            } else {
                while (i5 > this.buf.length) {
                    i6 += this.cipher.processBlock(bArr3, i4, bArr2, i3 + i6);
                    i5 -= blockSize;
                    i4 += blockSize;
                }
            }
        } else {
            bArr3 = bArr;
            i4 = i;
            i5 = i2;
            i6 = 0;
        }
        System.arraycopy(bArr3, i4, this.buf, this.bufOff, i5);
        int i10 = this.bufOff + i5;
        this.bufOff = i10;
        byte[] bArr6 = this.buf;
        if (i10 != bArr6.length) {
            return i6;
        }
        int processBlock = i6 + this.cipher.processBlock(bArr6, 0, bArr2, i3 + i6);
        this.bufOff = 0;
        return processBlock;
    }

    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i >= bArr.length) {
                this.bufOff = 0;
                this.cipher.reset();
                return;
            } else {
                bArr[i] = 0;
                i++;
            }
        }
    }
}
