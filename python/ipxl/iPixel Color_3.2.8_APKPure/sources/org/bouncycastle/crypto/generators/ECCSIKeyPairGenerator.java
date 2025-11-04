package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECCSIKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECCSIPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECCSIPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class ECCSIKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private ECPoint G;
    private Digest digest;
    private ECCSIKeyGenerationParameters parameters;
    private BigInteger q;

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        SecureRandom random = this.parameters.getRandom();
        this.digest.reset();
        byte[] id = this.parameters.getId();
        ECPoint kpak = this.parameters.getKPAK();
        BigInteger mod = BigIntegers.createRandomBigInteger(256, random).mod(this.q);
        ECPoint normalize = this.G.multiply(mod).normalize();
        byte[] encoded = this.G.getEncoded(false);
        this.digest.update(encoded, 0, encoded.length);
        byte[] encoded2 = kpak.getEncoded(false);
        this.digest.update(encoded2, 0, encoded2.length);
        this.digest.update(id, 0, id.length);
        byte[] encoded3 = normalize.getEncoded(false);
        this.digest.update(encoded3, 0, encoded3.length);
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new ECCSIPublicKeyParameters(normalize), (AsymmetricKeyParameter) new ECCSIPrivateKeyParameters(this.parameters.computeSSK(new BigInteger(1, bArr).mod(this.q).multiply(mod)), new ECCSIPublicKeyParameters(normalize)));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        ECCSIKeyGenerationParameters eCCSIKeyGenerationParameters = (ECCSIKeyGenerationParameters) keyGenerationParameters;
        this.parameters = eCCSIKeyGenerationParameters;
        this.q = eCCSIKeyGenerationParameters.getQ();
        this.G = this.parameters.getG();
        this.digest = this.parameters.getDigest();
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("ECCSI", this.parameters.getN(), null, CryptoServicePurpose.KEYGEN));
    }
}
