package org.bouncycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.SAKKEPublicKeyParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class SAKKEKEMSGenerator implements EncapsulatedSecretGenerator {
    private final SecureRandom random;

    public SAKKEKEMSGenerator(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    static BigInteger hashToIntegerRange(byte[] bArr, BigInteger bigInteger, Digest digest) {
        byte[] bArr2 = new byte[digest.getDigestSize()];
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, 0);
        byte[] clone = Arrays.clone(bArr2);
        int digestSize = digest.getDigestSize();
        byte[] bArr3 = new byte[digestSize];
        int bitLength = bigInteger.bitLength() >> 8;
        BigInteger bigInteger2 = BigInteger.ZERO;
        for (int i = 0; i <= bitLength; i++) {
            digest.update(bArr3, 0, digestSize);
            digest.doFinal(bArr3, 0);
            digest.update(bArr3, 0, digestSize);
            digest.update(clone, 0, clone.length);
            int digestSize2 = digest.getDigestSize();
            byte[] bArr4 = new byte[digestSize2];
            digest.doFinal(bArr4, 0);
            bigInteger2 = bigInteger2.shiftLeft(digestSize2 * 8).add(new BigInteger(1, bArr4));
        }
        return bigInteger2.mod(bigInteger);
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        SAKKEPublicKeyParameters sAKKEPublicKeyParameters = (SAKKEPublicKeyParameters) asymmetricKeyParameter;
        ECPoint z = sAKKEPublicKeyParameters.getZ();
        BigInteger identifier = sAKKEPublicKeyParameters.getIdentifier();
        BigInteger prime = sAKKEPublicKeyParameters.getPrime();
        BigInteger q = sAKKEPublicKeyParameters.getQ();
        BigInteger g = sAKKEPublicKeyParameters.getG();
        int n = sAKKEPublicKeyParameters.getN();
        ECCurve curve = sAKKEPublicKeyParameters.getCurve();
        ECPoint point = sAKKEPublicKeyParameters.getPoint();
        Digest digest = sAKKEPublicKeyParameters.getDigest();
        BigInteger createRandomBigInteger = BigIntegers.createRandomBigInteger(n, this.random);
        BigInteger hashToIntegerRange = hashToIntegerRange(Arrays.concatenate(createRandomBigInteger.toByteArray(), identifier.toByteArray()), q, digest);
        ECPoint normalize = point.multiply(identifier).normalize().add(z).multiply(hashToIntegerRange).normalize();
        BigInteger bigInteger = BigInteger.ONE;
        BigInteger[] bigIntegerArr = new BigInteger[2];
        BigInteger bigInteger2 = BigInteger.ONE;
        ECPoint createPoint = curve.createPoint(bigInteger2, g);
        BigInteger bigInteger3 = g;
        for (int bitLength = hashToIntegerRange.bitLength() - 2; bitLength >= 0; bitLength--) {
            BigInteger[] fp2PointSquare = SAKKEKEMExtractor.fp2PointSquare(bigInteger2, bigInteger3, prime);
            createPoint = createPoint.timesPow2(2);
            BigInteger bigInteger4 = fp2PointSquare[0];
            BigInteger bigInteger5 = fp2PointSquare[1];
            if (hashToIntegerRange.testBit(bitLength)) {
                BigInteger[] fp2Multiply = SAKKEKEMExtractor.fp2Multiply(bigInteger4, bigInteger5, bigInteger, g, prime);
                bigInteger4 = fp2Multiply[0];
                bigInteger5 = fp2Multiply[1];
            }
            BigInteger bigInteger6 = bigInteger4;
            bigInteger3 = bigInteger5;
            bigInteger2 = bigInteger6;
        }
        return new SecretWithEncapsulationImpl(BigIntegers.asUnsignedByteArray(n / 8, createRandomBigInteger), Arrays.concatenate(normalize.getEncoded(false), BigIntegers.asUnsignedByteArray(16, createRandomBigInteger.xor(hashToIntegerRange(bigInteger3.multiply(bigInteger2.modInverse(prime)).mod(prime).toByteArray(), BigInteger.ONE.shiftLeft(n), digest)))));
    }
}
