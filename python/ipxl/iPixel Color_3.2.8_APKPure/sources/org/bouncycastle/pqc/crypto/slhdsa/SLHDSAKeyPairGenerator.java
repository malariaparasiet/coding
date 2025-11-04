package org.bouncycastle.pqc.crypto.slhdsa;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes4.dex */
public class SLHDSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private SLHDSAParameters parameters;
    private SecureRandom random;

    private AsymmetricCipherKeyPair implGenerateKeyPair(SLHDSAEngine sLHDSAEngine, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        SK sk = new SK(bArr, bArr2);
        sLHDSAEngine.init(bArr3);
        PK pk = new PK(bArr3, new HT(sLHDSAEngine, sk.seed, bArr3).htPubKey);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new SLHDSAPublicKeyParameters(this.parameters, pk), (AsymmetricKeyParameter) new SLHDSAPrivateKeyParameters(this.parameters, sk, pk));
    }

    private byte[] sec_rand(int i) {
        byte[] bArr = new byte[i];
        this.random.nextBytes(bArr);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        SLHDSAEngine engine = this.parameters.getEngine();
        return implGenerateKeyPair(engine, sec_rand(engine.N), sec_rand(engine.N), sec_rand(engine.N));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
        this.parameters = ((SLHDSAKeyGenerationParameters) keyGenerationParameters).getParameters();
    }

    public AsymmetricCipherKeyPair internalGenerateKeyPair(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return implGenerateKeyPair(this.parameters.getEngine(), bArr, bArr2, bArr3);
    }
}
