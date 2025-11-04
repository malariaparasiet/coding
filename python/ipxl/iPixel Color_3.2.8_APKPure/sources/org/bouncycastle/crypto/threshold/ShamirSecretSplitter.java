package org.bouncycastle.crypto.threshold;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class ShamirSecretSplitter implements SecretSplitter {
    protected int l;
    private final Polynomial poly;
    protected SecureRandom random;

    public enum Algorithm {
        AES,
        RSA
    }

    public enum Mode {
        Native,
        Table
    }

    public ShamirSecretSplitter(Algorithm algorithm, Mode mode, int i, SecureRandom secureRandom) {
        if (i < 0 || i > 65534) {
            throw new IllegalArgumentException("Invalid input: l ranges from 0 to 65534 (2^16-2) bytes.");
        }
        this.poly = Polynomial.newInstance(algorithm, mode);
        this.l = i;
        this.random = secureRandom;
    }

    private byte[][] initP(int i, int i2) {
        if (i < 1 || i > 255) {
            throw new IllegalArgumentException("Invalid input: m must be less than 256 and positive.");
        }
        if (i2 < i || i2 > 255) {
            throw new IllegalArgumentException("Invalid input: n must be less than 256 and greater than or equal to n.");
        }
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i2, i);
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                bArr[i3][i4] = this.poly.gfPow((byte) (i3 + 1), (byte) i4);
            }
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.threshold.SecretSplitter
    public ShamirSplitSecret resplit(byte[] bArr, int i, int i2) {
        byte[][] initP = initP(i, i2);
        int i3 = 0;
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i, this.l);
        ShamirSplitSecretShare[] shamirSplitSecretShareArr = new ShamirSplitSecretShare[this.l];
        bArr2[0] = Arrays.clone(bArr);
        for (int i4 = 1; i4 < i; i4++) {
            this.random.nextBytes(bArr2[i4]);
        }
        while (i3 < initP.length) {
            int i5 = i3 + 1;
            shamirSplitSecretShareArr[i3] = new ShamirSplitSecretShare(this.poly.gfVecMul(initP[i3], bArr2), i5);
            i3 = i5;
        }
        return new ShamirSplitSecret(this.poly, shamirSplitSecretShareArr);
    }

    @Override // org.bouncycastle.crypto.threshold.SecretSplitter
    public ShamirSplitSecret split(int i, int i2) {
        byte[][] initP = initP(i, i2);
        int i3 = 0;
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i, this.l);
        ShamirSplitSecretShare[] shamirSplitSecretShareArr = new ShamirSplitSecretShare[this.l];
        for (int i4 = 0; i4 < i; i4++) {
            this.random.nextBytes(bArr[i4]);
        }
        while (i3 < initP.length) {
            int i5 = i3 + 1;
            shamirSplitSecretShareArr[i3] = new ShamirSplitSecretShare(this.poly.gfVecMul(initP[i3], bArr), i5);
            i3 = i5;
        }
        return new ShamirSplitSecret(this.poly, shamirSplitSecretShareArr);
    }

    @Override // org.bouncycastle.crypto.threshold.SecretSplitter
    public ShamirSplitSecret splitAround(SecretShare secretShare, int i, int i2) throws IOException {
        byte[][] initP = initP(i, i2);
        int i3 = 1;
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i, this.l);
        ShamirSplitSecretShare[] shamirSplitSecretShareArr = new ShamirSplitSecretShare[this.l];
        byte[] encoded = secretShare.getEncoded();
        shamirSplitSecretShareArr[0] = new ShamirSplitSecretShare(encoded, 1);
        for (int i4 = 0; i4 < i; i4++) {
            this.random.nextBytes(bArr[i4]);
        }
        for (int i5 = 0; i5 < this.l; i5++) {
            byte b = bArr[1][i5];
            for (int i6 = 2; i6 < i; i6++) {
                b = (byte) (b ^ bArr[i6][i5]);
            }
            bArr[0][i5] = (byte) (b ^ encoded[i5]);
        }
        while (i3 < initP.length) {
            int i7 = i3 + 1;
            shamirSplitSecretShareArr[i3] = new ShamirSplitSecretShare(this.poly.gfVecMul(initP[i3], bArr), i7);
            i3 = i7;
        }
        return new ShamirSplitSecret(this.poly, shamirSplitSecretShareArr);
    }
}
