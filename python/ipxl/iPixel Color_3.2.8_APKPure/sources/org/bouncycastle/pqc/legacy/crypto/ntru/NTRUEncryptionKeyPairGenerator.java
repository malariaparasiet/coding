package org.bouncycastle.pqc.legacy.crypto.ntru;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.DenseTernaryPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Polynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.ProductFormPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.util.Util;

/* loaded from: classes4.dex */
public class NTRUEncryptionKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUEncryptionKeyGenerationParameters params;

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        Polynomial generateRandomTernary;
        IntegerPolynomial integerPolynomial;
        IntegerPolynomial invertFq;
        DenseTernaryPolynomial generateRandom;
        int i = this.params.N;
        int i2 = this.params.q;
        int i3 = this.params.df;
        int i4 = this.params.df1;
        int i5 = this.params.df2;
        int i6 = this.params.df3;
        int i7 = this.params.dg;
        boolean z = this.params.fastFp;
        boolean z2 = this.params.sparse;
        IntegerPolynomial integerPolynomial2 = null;
        while (true) {
            int i8 = this.params.polyType;
            if (z) {
                generateRandomTernary = i8 == 0 ? Util.generateRandomTernary(i, i3, i3, z2, this.params.getRandom()) : ProductFormPolynomial.generateRandom(i, i4, i5, i6, i6, this.params.getRandom());
                integerPolynomial = generateRandomTernary.toIntegerPolynomial();
                integerPolynomial.mult(3);
                int[] iArr = integerPolynomial.coeffs;
                iArr[0] = iArr[0] + 1;
            } else {
                generateRandomTernary = i8 == 0 ? Util.generateRandomTernary(i, i3, i3 - 1, z2, this.params.getRandom()) : ProductFormPolynomial.generateRandom(i, i4, i5, i6, i6 - 1, this.params.getRandom());
                integerPolynomial = generateRandomTernary.toIntegerPolynomial();
                integerPolynomial2 = integerPolynomial.invertF3();
                if (integerPolynomial2 == null) {
                    continue;
                }
            }
            invertFq = integerPolynomial.invertFq(i2);
            if (invertFq != null) {
                break;
            }
        }
        if (z) {
            integerPolynomial2 = new IntegerPolynomial(i);
            integerPolynomial2.coeffs[0] = 1;
        }
        do {
            generateRandom = DenseTernaryPolynomial.generateRandom(i, i7, i7 - 1, this.params.getRandom());
        } while (generateRandom.invertFq(i2) == null);
        IntegerPolynomial mult = generateRandom.mult(invertFq, i2);
        mult.mult3(i2);
        mult.ensurePositive(i2);
        generateRandom.clear();
        invertFq.clear();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NTRUEncryptionPublicKeyParameters(mult, this.params.getEncryptionParameters()), (AsymmetricKeyParameter) new NTRUEncryptionPrivateKeyParameters(mult, generateRandomTernary, integerPolynomial2, this.params.getEncryptionParameters()));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.params = (NTRUEncryptionKeyGenerationParameters) keyGenerationParameters;
    }
}
