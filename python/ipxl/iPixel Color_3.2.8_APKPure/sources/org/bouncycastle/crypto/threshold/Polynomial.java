package org.bouncycastle.crypto.threshold;

import kotlin.UByte;
import org.bouncycastle.crypto.threshold.ShamirSecretSplitter;

/* loaded from: classes3.dex */
abstract class Polynomial {
    Polynomial() {
    }

    public static Polynomial newInstance(ShamirSecretSplitter.Algorithm algorithm, ShamirSecretSplitter.Mode mode) {
        return mode == ShamirSecretSplitter.Mode.Native ? new PolynomialNative(algorithm) : new PolynomialTable(algorithm);
    }

    protected abstract byte gfDiv(int i, int i2);

    protected abstract byte gfMul(int i, int i2);

    protected byte gfPow(int i, byte b) {
        byte b2 = 1;
        for (int i2 = 0; i2 < 8; i2++) {
            if (((1 << i2) & b) != 0) {
                b2 = gfMul(b2 & UByte.MAX_VALUE, i & 255);
            }
            int i3 = i & 255;
            i = gfMul(i3, i3);
        }
        return b2;
    }

    public byte[] gfVecMul(byte[] bArr, byte[][] bArr2) {
        byte[] bArr3 = new byte[bArr2[0].length];
        for (int i = 0; i < bArr2[0].length; i++) {
            int i2 = 0;
            for (int i3 = 0; i3 < bArr.length; i3++) {
                i2 ^= gfMul(bArr[i3] & UByte.MAX_VALUE, bArr2[i3][i] & UByte.MAX_VALUE);
            }
            bArr3[i] = (byte) i2;
        }
        return bArr3;
    }
}
