package org.bouncycastle.jcajce.provider.asymmetric.mlkem;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.jcajce.spec.MLKEMParameterSpec;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMKeyPairGenerator;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.util.Strings;

/* loaded from: classes4.dex */
public class MLKEMKeyPairGeneratorSpi extends KeyPairGenerator {
    MLKEMKeyPairGenerator engine;
    boolean initialised;
    private MLKEMParameters mlkemParameters;
    MLKEMKeyGenerationParameters param;
    SecureRandom random;

    public static class MLKEM1024 extends MLKEMKeyPairGeneratorSpi {
        public MLKEM1024() {
            super(MLKEMParameterSpec.ml_kem_1024);
        }
    }

    public static class MLKEM512 extends MLKEMKeyPairGeneratorSpi {
        public MLKEM512() {
            super(MLKEMParameterSpec.ml_kem_512);
        }
    }

    public static class MLKEM768 extends MLKEMKeyPairGeneratorSpi {
        public MLKEM768() {
            super(MLKEMParameterSpec.ml_kem_768);
        }
    }

    public MLKEMKeyPairGeneratorSpi() {
        super("ML-KEM");
        this.engine = new MLKEMKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
    }

    protected MLKEMKeyPairGeneratorSpi(MLKEMParameterSpec mLKEMParameterSpec) {
        super(Strings.toUpperCase(mLKEMParameterSpec.getName()));
        this.engine = new MLKEMKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
        this.mlkemParameters = Utils.getParameters(mLKEMParameterSpec.getName());
        if (this.param == null) {
            this.param = new MLKEMKeyGenerationParameters(this.random, this.mlkemParameters);
        }
        this.engine.init(this.param);
        this.initialised = true;
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) {
        return algorithmParameterSpec instanceof MLKEMParameterSpec ? ((MLKEMParameterSpec) algorithmParameterSpec).getName() : Strings.toUpperCase(SpecUtil.getNameFrom(algorithmParameterSpec));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            MLKEMKeyGenerationParameters mLKEMKeyGenerationParameters = new MLKEMKeyGenerationParameters(this.random, MLKEMParameters.ml_kem_768);
            this.param = mLKEMKeyGenerationParameters;
            this.engine.init(mLKEMKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCMLKEMPublicKey((MLKEMPublicKeyParameters) generateKeyPair.getPublic()), new BCMLKEMPrivateKey((MLKEMPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidAlgorithmParameterException {
        try {
            initialize(algorithmParameterSpec, new BCJcaJceHelper().createSecureRandom("DEFAULT"));
        } catch (NoSuchAlgorithmException unused) {
            throw new IllegalStateException("unable to find DEFAULT DRBG");
        }
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        String nameFromParams = getNameFromParams(algorithmParameterSpec);
        if (nameFromParams == null) {
            throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + algorithmParameterSpec);
        }
        MLKEMParameters parameters = Utils.getParameters(nameFromParams);
        if (parameters == null) {
            throw new InvalidAlgorithmParameterException("unknown parameter set name: " + nameFromParams);
        }
        if (this.mlkemParameters != null && !parameters.getName().equals(this.mlkemParameters.getName())) {
            throw new InvalidAlgorithmParameterException("key pair generator locked to " + getAlgorithm());
        }
        MLKEMKeyGenerationParameters mLKEMKeyGenerationParameters = new MLKEMKeyGenerationParameters(secureRandom, parameters);
        this.param = mLKEMKeyGenerationParameters;
        this.engine.init(mLKEMKeyGenerationParameters);
        this.initialised = true;
    }
}
