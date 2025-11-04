package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyFactorySpi;

/* loaded from: classes4.dex */
public class Mayo {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.mayo.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.Mayo", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyFactorySpi");
            addKeyFactoryAlgorithm(configurableProvider, "MAYO_1", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyFactorySpi$Mayo1", BCObjectIdentifiers.mayo1, new MayoKeyFactorySpi.Mayo1());
            addKeyFactoryAlgorithm(configurableProvider, "MAYO_2", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyFactorySpi$Mayo2", BCObjectIdentifiers.mayo2, new MayoKeyFactorySpi.Mayo2());
            addKeyFactoryAlgorithm(configurableProvider, "MAYO_3", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyFactorySpi$Mayo3", BCObjectIdentifiers.mayo3, new MayoKeyFactorySpi.Mayo3());
            addKeyFactoryAlgorithm(configurableProvider, "MAYO_5", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyFactorySpi$Mayo5", BCObjectIdentifiers.mayo5, new MayoKeyFactorySpi.Mayo5());
            configurableProvider.addAlgorithm("KeyPairGenerator.Mayo", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyPairGeneratorSpi");
            addKeyPairGeneratorAlgorithm(configurableProvider, "MAYO_1", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyPairGeneratorSpi$Mayo1", BCObjectIdentifiers.mayo1);
            addKeyPairGeneratorAlgorithm(configurableProvider, "MAYO_2", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyPairGeneratorSpi$Mayo2", BCObjectIdentifiers.mayo2);
            addKeyPairGeneratorAlgorithm(configurableProvider, "MAYO_3", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyPairGeneratorSpi$Mayo3", BCObjectIdentifiers.mayo3);
            addKeyPairGeneratorAlgorithm(configurableProvider, "MAYO_5", "org.bouncycastle.pqc.jcajce.provider.mayo.MayoKeyPairGeneratorSpi$Mayo5", BCObjectIdentifiers.mayo5);
            addSignatureAlgorithm(configurableProvider, "Mayo", "org.bouncycastle.pqc.jcajce.provider.mayo.SignatureSpi$Base", BCObjectIdentifiers.mayo);
            addSignatureAlgorithm(configurableProvider, "MAYO_1", "org.bouncycastle.pqc.jcajce.provider.mayo.SignatureSpi$Mayo1", BCObjectIdentifiers.mayo1);
            addSignatureAlgorithm(configurableProvider, "MAYO_2", "org.bouncycastle.pqc.jcajce.provider.mayo.SignatureSpi$Mayo2", BCObjectIdentifiers.mayo2);
            addSignatureAlgorithm(configurableProvider, "MAYO_3", "org.bouncycastle.pqc.jcajce.provider.mayo.SignatureSpi$Mayo3", BCObjectIdentifiers.mayo3);
            addSignatureAlgorithm(configurableProvider, "MAYO_5", "org.bouncycastle.pqc.jcajce.provider.mayo.SignatureSpi$Mayo5", BCObjectIdentifiers.mayo5);
        }
    }
}
