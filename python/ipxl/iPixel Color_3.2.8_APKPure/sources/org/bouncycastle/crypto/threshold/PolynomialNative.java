package org.bouncycastle.crypto.threshold;

import kotlin.UByte;
import org.bouncycastle.crypto.threshold.ShamirSecretSplitter;

/* loaded from: classes3.dex */
class PolynomialNative extends Polynomial {
    private final int IRREDUCIBLE;

    /* renamed from: org.bouncycastle.crypto.threshold.PolynomialNative$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$threshold$ShamirSecretSplitter$Algorithm;

        static {
            int[] iArr = new int[ShamirSecretSplitter.Algorithm.values().length];
            $SwitchMap$org$bouncycastle$crypto$threshold$ShamirSecretSplitter$Algorithm = iArr;
            try {
                iArr[ShamirSecretSplitter.Algorithm.AES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$threshold$ShamirSecretSplitter$Algorithm[ShamirSecretSplitter.Algorithm.RSA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public PolynomialNative(ShamirSecretSplitter.Algorithm algorithm) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$threshold$ShamirSecretSplitter$Algorithm[algorithm.ordinal()];
        if (i2 == 1) {
            i = 283;
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException("The algorithm is not correct");
            }
            i = 285;
        }
        this.IRREDUCIBLE = i;
    }

    @Override // org.bouncycastle.crypto.threshold.Polynomial
    protected byte gfDiv(int i, int i2) {
        return gfMul(i, gfPow((byte) i2, (byte) -2) & UByte.MAX_VALUE);
    }

    @Override // org.bouncycastle.crypto.threshold.Polynomial
    protected byte gfMul(int i, int i2) {
        int i3 = 0;
        while (i2 > 0) {
            if ((i2 & 1) != 0) {
                i3 ^= i;
            }
            i <<= 1;
            if ((i & 256) != 0) {
                i ^= this.IRREDUCIBLE;
            }
            i2 >>= 1;
        }
        while (i3 >= 256) {
            if ((i3 & 256) != 0) {
                i3 ^= this.IRREDUCIBLE;
            }
            i3 <<= 1;
        }
        return (byte) (i3 & 255);
    }
}
