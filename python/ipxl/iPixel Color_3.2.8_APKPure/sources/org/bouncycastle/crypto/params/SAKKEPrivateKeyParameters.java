package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class SAKKEPrivateKeyParameters extends AsymmetricKeyParameter {
    private static final BigInteger qMinOne = SAKKEPublicKeyParameters.q.subtract(BigInteger.ONE);
    private final SAKKEPublicKeyParameters publicParams;
    private final BigInteger z;

    public SAKKEPrivateKeyParameters(BigInteger bigInteger, SAKKEPublicKeyParameters sAKKEPublicKeyParameters) {
        super(true);
        this.z = bigInteger;
        this.publicParams = sAKKEPublicKeyParameters;
        if (!sAKKEPublicKeyParameters.getPoint().multiply(bigInteger).normalize().equals(sAKKEPublicKeyParameters.getZ())) {
            throw new IllegalStateException("public key and private key of SAKKE do not match");
        }
    }

    public SAKKEPrivateKeyParameters(SecureRandom secureRandom) {
        super(true);
        BigInteger bigInteger = BigIntegers.TWO;
        BigInteger bigInteger2 = qMinOne;
        BigInteger createRandomInRange = BigIntegers.createRandomInRange(bigInteger, bigInteger2, secureRandom);
        this.z = createRandomInRange;
        this.publicParams = new SAKKEPublicKeyParameters(BigIntegers.createRandomInRange(BigIntegers.TWO, bigInteger2, secureRandom), SAKKEPublicKeyParameters.P.multiply(createRandomInRange).normalize());
    }

    public BigInteger getMasterSecret() {
        return this.z;
    }

    public SAKKEPublicKeyParameters getPublicParams() {
        return this.publicParams;
    }
}
