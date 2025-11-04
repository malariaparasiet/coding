package org.bouncycastle.pqc.legacy.crypto.ntru;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.legacy.crypto.ntru.NTRUSigningPrivateKeyParameters;
import org.bouncycastle.pqc.legacy.math.ntru.euclid.BigIntEuclidean;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.BigDecimalPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.BigIntPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.DenseTernaryPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Polynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.ProductFormPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Resultant;

/* loaded from: classes4.dex */
public class NTRUSigningKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUSigningKeyGenerationParameters params;

    private class BasisGenerationTask implements Callable<NTRUSigningPrivateKeyParameters.Basis> {
        private BasisGenerationTask() {
        }

        @Override // java.util.concurrent.Callable
        public NTRUSigningPrivateKeyParameters.Basis call() throws Exception {
            return NTRUSigningKeyPairGenerator.this.generateBoundedBasis();
        }
    }

    public static class FGBasis extends NTRUSigningPrivateKeyParameters.Basis {
        public IntegerPolynomial F;
        public IntegerPolynomial G;

        FGBasis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            super(polynomial, polynomial2, integerPolynomial, nTRUSigningKeyGenerationParameters);
            this.F = integerPolynomial2;
            this.G = integerPolynomial3;
        }

        boolean isNormOk() {
            double d = this.params.keyNormBoundSq;
            int i = this.params.q;
            return ((double) this.F.centeredNormSq(i)) < d && ((double) this.G.centeredNormSq(i)) < d;
        }
    }

    private FGBasis generateBasis() {
        Polynomial generateRandom;
        IntegerPolynomial integerPolynomial;
        IntegerPolynomial invertFq;
        int i;
        Resultant resultant;
        Polynomial generateRandom2;
        IntegerPolynomial integerPolynomial2;
        int i2;
        Resultant resultant2;
        BigIntEuclidean calculate;
        BigIntPolynomial round;
        IntegerPolynomial mult;
        Polynomial polynomial;
        int i3 = this.params.N;
        int i4 = this.params.q;
        int i5 = this.params.d;
        int i6 = this.params.d1;
        int i7 = this.params.d2;
        int i8 = this.params.d3;
        int i9 = this.params.basisType;
        int i10 = 1;
        int i11 = (i3 * 2) + 1;
        boolean z = this.params.primeCheck;
        while (true) {
            generateRandom = this.params.polyType == 0 ? DenseTernaryPolynomial.generateRandom(i3, i5 + 1, i5, CryptoServicesRegistrar.getSecureRandom()) : ProductFormPolynomial.generateRandom(i3, i6, i7, i8 + 1, i8, CryptoServicesRegistrar.getSecureRandom());
            integerPolynomial = generateRandom.toIntegerPolynomial();
            if (!z || !integerPolynomial.resultant(i11).res.equals(BigInteger.ZERO)) {
                invertFq = integerPolynomial.invertFq(i4);
                if (invertFq != null) {
                    break;
                }
            }
        }
        Resultant resultant3 = integerPolynomial.resultant();
        while (true) {
            if (this.params.polyType == 0) {
                i = i10;
                generateRandom2 = DenseTernaryPolynomial.generateRandom(i3, i5 + 1, i5, CryptoServicesRegistrar.getSecureRandom());
                resultant = resultant3;
            } else {
                i = i10;
                resultant = resultant3;
                generateRandom2 = ProductFormPolynomial.generateRandom(i3, i6, i7, i8 + 1, i8, CryptoServicesRegistrar.getSecureRandom());
            }
            integerPolynomial2 = generateRandom2.toIntegerPolynomial();
            int i12 = i5;
            if (z) {
                i2 = i6;
                if (integerPolynomial2.resultant(i11).res.equals(BigInteger.ZERO)) {
                    resultant3 = resultant;
                    i10 = i;
                    i5 = i12;
                    i6 = i2;
                }
            } else {
                i2 = i6;
            }
            if (integerPolynomial2.invertFq(i4) != null) {
                resultant2 = integerPolynomial2.resultant();
                int i13 = i7;
                calculate = BigIntEuclidean.calculate(resultant.res, resultant2.res);
                int i14 = i8;
                if (calculate.gcd.equals(BigInteger.ONE)) {
                    break;
                }
                resultant3 = resultant;
                i10 = i;
                i5 = i12;
                i6 = i2;
                i7 = i13;
                i8 = i14;
            } else {
                resultant3 = resultant;
                i10 = i;
                i5 = i12;
                i6 = i2;
            }
        }
        BigIntPolynomial bigIntPolynomial = (BigIntPolynomial) resultant.rho.clone();
        bigIntPolynomial.mult(calculate.x.multiply(BigInteger.valueOf(i4)));
        BigIntPolynomial bigIntPolynomial2 = (BigIntPolynomial) resultant2.rho.clone();
        bigIntPolynomial2.mult(calculate.y.multiply(BigInteger.valueOf(-i4)));
        int i15 = 0;
        if (this.params.keyGenAlg == 0) {
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            iArr[0] = integerPolynomial.coeffs[0];
            iArr2[0] = integerPolynomial2.coeffs[0];
            for (int i16 = i; i16 < i3; i16++) {
                int i17 = i3 - i16;
                iArr[i16] = integerPolynomial.coeffs[i17];
                iArr2[i16] = integerPolynomial2.coeffs[i17];
            }
            IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(iArr);
            IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(iArr2);
            IntegerPolynomial mult2 = generateRandom.mult(integerPolynomial3);
            mult2.add(generateRandom2.mult(integerPolynomial4));
            Resultant resultant4 = mult2.resultant();
            BigIntPolynomial mult3 = integerPolynomial3.mult(bigIntPolynomial2);
            mult3.add(integerPolynomial4.mult(bigIntPolynomial));
            round = mult3.mult(resultant4.rho);
            round.div(resultant4.res);
        } else {
            for (int i18 = i; i18 < i3; i18 *= 10) {
                i15++;
            }
            BigDecimalPolynomial div = resultant.rho.div(new BigDecimal(resultant.res), bigIntPolynomial2.getMaxCoeffLength() + 1 + i15);
            BigDecimalPolynomial div2 = resultant2.rho.div(new BigDecimal(resultant2.res), bigIntPolynomial.getMaxCoeffLength() + 1 + i15);
            BigDecimalPolynomial mult4 = div.mult(bigIntPolynomial2);
            mult4.add(div2.mult(bigIntPolynomial));
            mult4.halve();
            round = mult4.round();
        }
        BigIntPolynomial bigIntPolynomial3 = (BigIntPolynomial) bigIntPolynomial2.clone();
        bigIntPolynomial3.sub(generateRandom.mult(round));
        BigIntPolynomial bigIntPolynomial4 = (BigIntPolynomial) bigIntPolynomial.clone();
        bigIntPolynomial4.sub(generateRandom2.mult(round));
        IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(bigIntPolynomial3);
        IntegerPolynomial integerPolynomial6 = new IntegerPolynomial(bigIntPolynomial4);
        minimizeFG(integerPolynomial, integerPolynomial2, integerPolynomial5, integerPolynomial6, i3);
        if (i9 == 0) {
            mult = generateRandom2.mult(invertFq, i4);
            polynomial = integerPolynomial5;
        } else {
            mult = integerPolynomial5.mult(invertFq, i4);
            polynomial = generateRandom2;
        }
        mult.modPositive(i4);
        return new FGBasis(generateRandom, polynomial, mult, integerPolynomial5, integerPolynomial6, this.params);
    }

    private void minimizeFG(IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, IntegerPolynomial integerPolynomial4, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += i * 2 * ((integerPolynomial.coeffs[i3] * integerPolynomial.coeffs[i3]) + (integerPolynomial2.coeffs[i3] * integerPolynomial2.coeffs[i3]));
        }
        int i4 = i2 - 4;
        IntegerPolynomial integerPolynomial5 = (IntegerPolynomial) integerPolynomial.clone();
        IntegerPolynomial integerPolynomial6 = (IntegerPolynomial) integerPolynomial2.clone();
        int i5 = 0;
        int i6 = 0;
        while (i5 < i && i6 < i) {
            int i7 = 0;
            for (int i8 = 0; i8 < i; i8++) {
                i7 += i * 4 * ((integerPolynomial3.coeffs[i8] * integerPolynomial.coeffs[i8]) + (integerPolynomial4.coeffs[i8] * integerPolynomial2.coeffs[i8]));
            }
            int sumCoeffs = i7 - ((integerPolynomial3.sumCoeffs() + integerPolynomial4.sumCoeffs()) * 4);
            if (sumCoeffs > i4) {
                integerPolynomial3.sub(integerPolynomial5);
                integerPolynomial4.sub(integerPolynomial6);
            } else if (sumCoeffs < (-i4)) {
                integerPolynomial3.add(integerPolynomial5);
                integerPolynomial4.add(integerPolynomial6);
            } else {
                i6++;
                integerPolynomial5.rotate1();
                integerPolynomial6.rotate1();
            }
            i5++;
            i6 = 0;
            i6++;
            integerPolynomial5.rotate1();
            integerPolynomial6.rotate1();
        }
    }

    public NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis() {
        FGBasis generateBasis;
        do {
            generateBasis = generateBasis();
        } while (!generateBasis.isNormOk());
        return generateBasis;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        int i = this.params.B;
        while (true) {
            nTRUSigningPublicKeyParameters = null;
            Object[] objArr = 0;
            if (i < 0) {
                break;
            }
            arrayList.add(newCachedThreadPool.submit(new BasisGenerationTask()));
            i--;
        }
        newCachedThreadPool.shutdown();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = this.params.B; i2 >= 0; i2--) {
            Future future = (Future) arrayList.get(i2);
            try {
                arrayList2.add((NTRUSigningPrivateKeyParameters.Basis) future.get());
                if (i2 == this.params.B) {
                    nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(((NTRUSigningPrivateKeyParameters.Basis) future.get()).h, this.params.getSigningParameters());
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList2, nTRUSigningPublicKeyParameters));
    }

    public AsymmetricCipherKeyPair generateKeyPairSingleThread() {
        ArrayList arrayList = new ArrayList();
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = null;
        for (int i = this.params.B; i >= 0; i--) {
            NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis = generateBoundedBasis();
            arrayList.add(generateBoundedBasis);
            if (i == 0) {
                nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(generateBoundedBasis.h, this.params.getSigningParameters());
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList, nTRUSigningPublicKeyParameters));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.params = (NTRUSigningKeyGenerationParameters) keyGenerationParameters;
    }
}
