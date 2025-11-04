package org.bouncycastle.crypto.hpke;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.modes.ChaCha20Poly1305;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class AEAD {
    private final short aeadId;
    private final byte[] baseNonce;
    private AEADCipher cipher;
    private final byte[] key;
    private long seq = 0;

    public AEAD(short s, byte[] bArr, byte[] bArr2) {
        AEADCipher newInstance;
        this.key = bArr;
        this.baseNonce = bArr2;
        this.aeadId = s;
        if (s == 1 || s == 2) {
            newInstance = GCMBlockCipher.newInstance(AESEngine.newInstance());
        } else if (s != 3) {
            return;
        } else {
            newInstance = new ChaCha20Poly1305();
        }
        this.cipher = newInstance;
    }

    private byte[] computeNonce() {
        long j = this.seq;
        this.seq = 1 + j;
        byte[] longToBigEndian = Pack.longToBigEndian(j);
        byte[] clone = Arrays.clone(this.baseNonce);
        Bytes.xorTo(8, longToBigEndian, 0, clone, clone.length - 8);
        return clone;
    }

    private byte[] process(boolean z, byte[] bArr, byte[] bArr2, int i, int i2) throws InvalidCipherTextException {
        short s = this.aeadId;
        if (s != 1 && s != 2 && s != 3) {
            throw new IllegalStateException("Export only mode, cannot be used to seal/open");
        }
        this.cipher.init(z, new ParametersWithIV(new KeyParameter(this.key), computeNonce()));
        this.cipher.processAADBytes(bArr, 0, bArr.length);
        int outputSize = this.cipher.getOutputSize(i2);
        byte[] bArr3 = new byte[outputSize];
        int processBytes = this.cipher.processBytes(bArr2, i, i2, bArr3, 0);
        if (processBytes + this.cipher.doFinal(bArr3, processBytes) == outputSize) {
            return bArr3;
        }
        throw new IllegalStateException();
    }

    public byte[] open(byte[] bArr, byte[] bArr2) throws InvalidCipherTextException {
        return process(false, bArr, bArr2, 0, bArr2.length);
    }

    public byte[] open(byte[] bArr, byte[] bArr2, int i, int i2) throws InvalidCipherTextException {
        Arrays.validateSegment(bArr2, i, i2);
        return process(false, bArr, bArr2, i, i2);
    }

    public byte[] seal(byte[] bArr, byte[] bArr2) throws InvalidCipherTextException {
        return process(true, bArr, bArr2, 0, bArr2.length);
    }

    public byte[] seal(byte[] bArr, byte[] bArr2, int i, int i2) throws InvalidCipherTextException {
        Arrays.validateSegment(bArr2, i, i2);
        return process(true, bArr, bArr2, i, i2);
    }
}
