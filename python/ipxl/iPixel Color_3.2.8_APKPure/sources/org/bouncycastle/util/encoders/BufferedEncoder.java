package org.bouncycastle.util.encoders;

/* loaded from: classes4.dex */
public class BufferedEncoder {
    protected byte[] buf;
    protected int bufOff;
    protected Translator translator;

    public BufferedEncoder(Translator translator, int i) {
        this.translator = translator;
        if (i % translator.getEncodedBlockSize() != 0) {
            throw new IllegalArgumentException("buffer size not multiple of input block size");
        }
        this.buf = new byte[i];
        this.bufOff = 0;
    }

    public int processByte(byte b, byte[] bArr, int i) {
        byte[] bArr2 = this.buf;
        int i2 = this.bufOff;
        int i3 = i2 + 1;
        this.bufOff = i3;
        bArr2[i2] = b;
        if (i3 != bArr2.length) {
            return 0;
        }
        int encode = this.translator.encode(bArr2, 0, bArr2.length, bArr, i);
        this.bufOff = 0;
        return encode;
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        byte[] bArr3;
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        byte[] bArr4 = this.buf;
        int length = bArr4.length;
        int i4 = this.bufOff;
        int i5 = length - i4;
        int i6 = 0;
        if (i2 > i5) {
            System.arraycopy(bArr, i, bArr4, i4, i5);
            Translator translator = this.translator;
            byte[] bArr5 = this.buf;
            int encode = translator.encode(bArr5, 0, bArr5.length, bArr2, i3);
            this.bufOff = 0;
            int i7 = i2 - i5;
            int i8 = i + i5;
            int length2 = i7 - (i7 % this.buf.length);
            bArr3 = bArr;
            i6 = encode + this.translator.encode(bArr3, i8, length2, bArr2, i3 + encode);
            i2 = i7 - length2;
            i = i8 + length2;
        } else {
            bArr3 = bArr;
        }
        if (i2 != 0) {
            System.arraycopy(bArr3, i, this.buf, this.bufOff, i2);
            this.bufOff += i2;
        }
        return i6;
    }
}
