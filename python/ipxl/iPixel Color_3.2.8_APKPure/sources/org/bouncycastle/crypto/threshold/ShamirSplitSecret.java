package org.bouncycastle.crypto.threshold;

import java.io.IOException;
import java.lang.reflect.Array;
import kotlin.UByte;
import org.bouncycastle.crypto.threshold.ShamirSecretSplitter;

/* loaded from: classes3.dex */
public class ShamirSplitSecret implements SplitSecret {
    private final Polynomial poly;
    private final ShamirSplitSecretShare[] secretShares;

    ShamirSplitSecret(Polynomial polynomial, ShamirSplitSecretShare[] shamirSplitSecretShareArr) {
        this.secretShares = shamirSplitSecretShareArr;
        this.poly = polynomial;
    }

    public ShamirSplitSecret(ShamirSecretSplitter.Algorithm algorithm, ShamirSecretSplitter.Mode mode, ShamirSplitSecretShare[] shamirSplitSecretShareArr) {
        this.secretShares = shamirSplitSecretShareArr;
        this.poly = Polynomial.newInstance(algorithm, mode);
    }

    public ShamirSplitSecret divide(int i) throws IOException {
        int i2 = 0;
        while (true) {
            ShamirSplitSecretShare[] shamirSplitSecretShareArr = this.secretShares;
            if (i2 >= shamirSplitSecretShareArr.length) {
                return this;
            }
            byte[] encoded = shamirSplitSecretShareArr[i2].getEncoded();
            for (int i3 = 0; i3 < encoded.length; i3++) {
                encoded[i3] = this.poly.gfDiv(encoded[i3] & UByte.MAX_VALUE, i);
            }
            int i4 = i2 + 1;
            this.secretShares[i2] = new ShamirSplitSecretShare(encoded, i4);
            i2 = i4;
        }
    }

    @Override // org.bouncycastle.crypto.threshold.SplitSecret
    public byte[] getSecret() throws IOException {
        ShamirSplitSecretShare[] shamirSplitSecretShareArr = this.secretShares;
        int length = shamirSplitSecretShareArr.length;
        byte[] bArr = new byte[length];
        int i = length - 1;
        byte[] bArr2 = new byte[i];
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, length, shamirSplitSecretShareArr[0].getEncoded().length);
        for (int i2 = 0; i2 < length; i2++) {
            bArr3[i2] = this.secretShares[i2].getEncoded();
            byte b = 0;
            for (int i3 = 0; i3 < length; i3++) {
                if (i3 != i2) {
                    bArr2[b] = this.poly.gfDiv(this.secretShares[i3].r, this.secretShares[i2].r ^ this.secretShares[i3].r);
                    b = (byte) (b + 1);
                }
            }
            byte b2 = 1;
            for (int i4 = 0; i4 != i; i4++) {
                b2 = this.poly.gfMul(b2 & UByte.MAX_VALUE, bArr2[i4] & UByte.MAX_VALUE);
            }
            bArr[i2] = b2;
        }
        return this.poly.gfVecMul(bArr, bArr3);
    }

    @Override // org.bouncycastle.crypto.threshold.SplitSecret
    public ShamirSplitSecretShare[] getSecretShares() {
        return this.secretShares;
    }

    public ShamirSplitSecret multiple(int i) throws IOException {
        int i2 = 0;
        while (true) {
            ShamirSplitSecretShare[] shamirSplitSecretShareArr = this.secretShares;
            if (i2 >= shamirSplitSecretShareArr.length) {
                return this;
            }
            byte[] encoded = shamirSplitSecretShareArr[i2].getEncoded();
            for (int i3 = 0; i3 < encoded.length; i3++) {
                encoded[i3] = this.poly.gfMul(encoded[i3] & UByte.MAX_VALUE, i);
            }
            int i4 = i2 + 1;
            this.secretShares[i2] = new ShamirSplitSecretShare(encoded, i4);
            i2 = i4;
        }
    }
}
