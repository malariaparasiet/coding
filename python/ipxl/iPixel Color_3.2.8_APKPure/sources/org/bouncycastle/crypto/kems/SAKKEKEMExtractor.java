package org.bouncycastle.crypto.kems;

import java.math.BigInteger;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.params.SAKKEPrivateKeyParameters;
import org.bouncycastle.crypto.params.SAKKEPublicKeyParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class SAKKEKEMExtractor implements EncapsulatedSecretExtractor {
    private final ECPoint K_bs;
    private final ECPoint P;
    private final ECPoint Z_S;
    private final ECCurve curve;
    private final Digest digest;
    private final BigInteger identifier;
    private final int n;
    private final BigInteger p;
    private final BigInteger q;

    public SAKKEKEMExtractor(SAKKEPrivateKeyParameters sAKKEPrivateKeyParameters) {
        SAKKEPublicKeyParameters publicParams = sAKKEPrivateKeyParameters.getPublicParams();
        this.curve = publicParams.getCurve();
        BigInteger q = publicParams.getQ();
        this.q = q;
        ECPoint point = publicParams.getPoint();
        this.P = point;
        this.p = publicParams.getPrime();
        this.Z_S = publicParams.getZ();
        BigInteger identifier = publicParams.getIdentifier();
        this.identifier = identifier;
        this.K_bs = point.multiply(identifier.add(sAKKEPrivateKeyParameters.getMasterSecret()).modInverse(q)).normalize();
        this.n = publicParams.getN();
        this.digest = publicParams.getDigest();
    }

    static BigInteger computePairing(ECPoint eCPoint, ECPoint eCPoint2, BigInteger bigInteger, BigInteger bigInteger2) {
        boolean z = false;
        BigInteger[] bigIntegerArr = {BigInteger.ONE, BigInteger.ZERO};
        BigInteger subtract = bigInteger2.subtract(BigInteger.ONE);
        int bitLength = subtract.bitLength();
        BigInteger bigInteger3 = eCPoint2.getAffineXCoord().toBigInteger();
        BigInteger bigInteger4 = eCPoint2.getAffineYCoord().toBigInteger();
        BigInteger bigInteger5 = eCPoint.getAffineXCoord().toBigInteger();
        BigInteger bigInteger6 = eCPoint.getAffineYCoord().toBigInteger();
        BigInteger valueOf = BigInteger.valueOf(3L);
        BigInteger valueOf2 = BigInteger.valueOf(2L);
        int i = bitLength - 2;
        ECPoint eCPoint3 = eCPoint;
        while (i >= 0) {
            BigInteger bigInteger7 = eCPoint3.getAffineXCoord().toBigInteger();
            BigInteger bigInteger8 = eCPoint3.getAffineYCoord().toBigInteger();
            boolean z2 = z;
            BigInteger mod = valueOf.multiply(bigInteger7.multiply(bigInteger7).subtract(BigInteger.ONE)).multiply(bigInteger8.multiply(valueOf2).modInverse(bigInteger)).mod(bigInteger);
            BigInteger[] fp2PointSquare = fp2PointSquare(bigIntegerArr[z2 ? 1 : 0], bigIntegerArr[1], bigInteger);
            bigIntegerArr = fp2Multiply(fp2PointSquare[z2 ? 1 : 0], fp2PointSquare[1], mod.multiply(bigInteger3.add(bigInteger7)).subtract(bigInteger8), bigInteger4, bigInteger);
            eCPoint3 = eCPoint3.twice().normalize();
            if (subtract.testBit(i)) {
                BigInteger bigInteger9 = eCPoint3.getAffineXCoord().toBigInteger();
                BigInteger bigInteger10 = eCPoint3.getAffineYCoord().toBigInteger();
                bigIntegerArr = fp2Multiply(bigIntegerArr[z2 ? 1 : 0], bigIntegerArr[1], bigInteger10.subtract(bigInteger6).multiply(bigInteger9.subtract(bigInteger5).modInverse(bigInteger)).mod(bigInteger).multiply(bigInteger3.add(bigInteger9)).subtract(bigInteger10), bigInteger4, bigInteger);
                eCPoint3 = eCPoint3.add(eCPoint).normalize();
            }
            i--;
            z = z2 ? 1 : 0;
        }
        boolean z3 = z;
        BigInteger[] fp2PointSquare2 = fp2PointSquare(bigIntegerArr[z3 ? 1 : 0], bigIntegerArr[1], bigInteger);
        BigInteger[] fp2PointSquare3 = fp2PointSquare(fp2PointSquare2[z3 ? 1 : 0], fp2PointSquare2[1], bigInteger);
        return fp2PointSquare3[1].multiply(fp2PointSquare3[z3 ? 1 : 0].modInverse(bigInteger)).mod(bigInteger);
    }

    static BigInteger[] fp2Multiply(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
        return new BigInteger[]{bigInteger.multiply(bigInteger3).subtract(bigInteger2.multiply(bigInteger4)).mod(bigInteger5), bigInteger.multiply(bigInteger4).add(bigInteger2.multiply(bigInteger3)).mod(bigInteger5)};
    }

    static BigInteger[] fp2PointSquare(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return new BigInteger[]{bigInteger.add(bigInteger2).mod(bigInteger3).multiply(bigInteger.subtract(bigInteger2).mod(bigInteger3)).mod(bigInteger3), bigInteger.multiply(bigInteger2).multiply(BigInteger.valueOf(2L)).mod(bigInteger3)};
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        ECPoint decodePoint = this.curve.decodePoint(Arrays.copyOfRange(bArr, 0, 257));
        BigInteger fromUnsignedByteArray = BigIntegers.fromUnsignedByteArray(bArr, 257, 16);
        BigInteger computePairing = computePairing(decodePoint, this.K_bs, this.p, this.q);
        BigInteger mod = fromUnsignedByteArray.xor(SAKKEKEMSGenerator.hashToIntegerRange(computePairing.toByteArray(), BigInteger.ONE.shiftLeft(this.n), this.digest)).mod(this.p);
        BigInteger bigInteger = this.identifier;
        if (decodePoint.equals(this.P.multiply(bigInteger).normalize().add(this.Z_S).multiply(SAKKEKEMSGenerator.hashToIntegerRange(Arrays.concatenate(mod.toByteArray(), bigInteger.toByteArray()), this.q, this.digest)).normalize())) {
            return BigIntegers.asUnsignedByteArray(this.n / 8, mod);
        }
        throw new IllegalStateException("Validation of R_bS failed");
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return 273;
    }
}
