package org.bouncycastle.pqc.crypto.xwing;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.crypto.prng.FixedSecureRandom;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMKeyPairGenerator;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPublicKeyParameters;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class XWingKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private SecureRandom random;

    static AsymmetricCipherKeyPair genKeyPair(byte[] bArr) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[96];
        sHAKEDigest.doOutput(bArr2, 0, 96);
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, 0, 64);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr2, 64, 96);
        FixedSecureRandom fixedSecureRandom = new FixedSecureRandom(copyOfRange);
        MLKEMKeyPairGenerator mLKEMKeyPairGenerator = new MLKEMKeyPairGenerator();
        mLKEMKeyPairGenerator.init(new MLKEMKeyGenerationParameters(fixedSecureRandom, MLKEMParameters.ml_kem_768));
        AsymmetricCipherKeyPair generateKeyPair = mLKEMKeyPairGenerator.generateKeyPair();
        MLKEMPublicKeyParameters mLKEMPublicKeyParameters = (MLKEMPublicKeyParameters) generateKeyPair.getPublic();
        MLKEMPrivateKeyParameters mLKEMPrivateKeyParameters = (MLKEMPrivateKeyParameters) generateKeyPair.getPrivate();
        FixedSecureRandom fixedSecureRandom2 = new FixedSecureRandom(copyOfRange2);
        X25519KeyPairGenerator x25519KeyPairGenerator = new X25519KeyPairGenerator();
        x25519KeyPairGenerator.init(new X25519KeyGenerationParameters(fixedSecureRandom2));
        AsymmetricCipherKeyPair generateKeyPair2 = x25519KeyPairGenerator.generateKeyPair();
        X25519PublicKeyParameters x25519PublicKeyParameters = (X25519PublicKeyParameters) generateKeyPair2.getPublic();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new XWingPublicKeyParameters(mLKEMPublicKeyParameters, x25519PublicKeyParameters), (AsymmetricKeyParameter) new XWingPrivateKeyParameters(bArr, mLKEMPrivateKeyParameters, (X25519PrivateKeyParameters) generateKeyPair2.getPrivate(), mLKEMPublicKeyParameters, x25519PublicKeyParameters));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        byte[] bArr = new byte[32];
        this.random.nextBytes(bArr);
        return genKeyPair(bArr);
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
