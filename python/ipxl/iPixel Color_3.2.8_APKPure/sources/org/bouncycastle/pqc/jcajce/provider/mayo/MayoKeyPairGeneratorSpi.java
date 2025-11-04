package org.bouncycastle.pqc.jcajce.provider.mayo;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.mayo.MayoKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.mayo.MayoKeyPairGenerator;
import org.bouncycastle.pqc.crypto.mayo.MayoParameters;
import org.bouncycastle.pqc.crypto.mayo.MayoPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mayo.MayoPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.MayoParameterSpec;
import org.bouncycastle.util.Strings;

/* loaded from: classes4.dex */
public class MayoKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters;
    MayoKeyPairGenerator engine;
    boolean initialised;
    private MayoParameters mayoParameters;
    MayoKeyGenerationParameters param;
    SecureRandom random;

    public static class Mayo1 extends MayoKeyPairGeneratorSpi {
        public Mayo1() {
            super(MayoParameters.mayo1);
        }
    }

    public static class Mayo2 extends MayoKeyPairGeneratorSpi {
        public Mayo2() {
            super(MayoParameters.mayo2);
        }
    }

    public static class Mayo3 extends MayoKeyPairGeneratorSpi {
        public Mayo3() {
            super(MayoParameters.mayo3);
        }
    }

    public static class Mayo5 extends MayoKeyPairGeneratorSpi {
        public Mayo5() {
            super(MayoParameters.mayo5);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put("MAYO_1", MayoParameters.mayo1);
        parameters.put("MAYO_2", MayoParameters.mayo2);
        parameters.put("MAYO_3", MayoParameters.mayo3);
        parameters.put("MAYO_5", MayoParameters.mayo5);
        parameters.put(MayoParameterSpec.mayo1.getName(), MayoParameters.mayo1);
        parameters.put(MayoParameterSpec.mayo2.getName(), MayoParameters.mayo2);
        parameters.put(MayoParameterSpec.mayo3.getName(), MayoParameters.mayo3);
        parameters.put(MayoParameterSpec.mayo5.getName(), MayoParameters.mayo5);
    }

    public MayoKeyPairGeneratorSpi() {
        super("Mayo");
        this.engine = new MayoKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
    }

    protected MayoKeyPairGeneratorSpi(MayoParameters mayoParameters) {
        super(mayoParameters.getName());
        this.engine = new MayoKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
        this.mayoParameters = mayoParameters;
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) {
        return algorithmParameterSpec instanceof MayoParameterSpec ? ((MayoParameterSpec) algorithmParameterSpec).getName() : Strings.toLowerCase(SpecUtil.getNameFrom(algorithmParameterSpec));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            MayoKeyGenerationParameters mayoKeyGenerationParameters = new MayoKeyGenerationParameters(this.random, MayoParameters.mayo1);
            this.param = mayoKeyGenerationParameters;
            this.engine.init(mayoKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCMayoPublicKey((MayoPublicKeyParameters) generateKeyPair.getPublic()), new BCMayoPrivateKey((MayoPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        String nameFromParams = getNameFromParams(algorithmParameterSpec);
        if (nameFromParams == null) {
            throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + algorithmParameterSpec);
        }
        MayoKeyGenerationParameters mayoKeyGenerationParameters = new MayoKeyGenerationParameters(secureRandom, (MayoParameters) parameters.get(nameFromParams));
        this.param = mayoKeyGenerationParameters;
        this.engine.init(mayoKeyGenerationParameters);
        this.initialised = true;
    }
}
