package org.bouncycastle.jcajce.provider.asymmetric;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

/* loaded from: classes4.dex */
public class NoSig {
    private static final String PREFIX = "org.bouncycastle.jcajce.provider.asymmetric.NoSig$";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("Signature." + X509ObjectIdentifiers.id_alg_noSignature, "org.bouncycastle.jcajce.provider.asymmetric.NoSig$SigSpi");
        }
    }

    public static class SigSpi extends SignatureSpi {
        @Override // java.security.SignatureSpi
        protected Object engineGetParameter(String str) throws InvalidParameterException {
            return null;
        }

        @Override // java.security.SignatureSpi
        protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
            throw new InvalidKeyException("attempt to pass private key to NoSig");
        }

        @Override // java.security.SignatureSpi
        protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
            throw new InvalidKeyException("attempt to pass public key to NoSig");
        }

        @Override // java.security.SignatureSpi
        protected void engineSetParameter(String str, Object obj) throws InvalidParameterException {
        }

        @Override // java.security.SignatureSpi
        protected byte[] engineSign() throws SignatureException {
            return new byte[0];
        }

        @Override // java.security.SignatureSpi
        protected void engineUpdate(byte b) throws SignatureException {
        }

        @Override // java.security.SignatureSpi
        protected void engineUpdate(byte[] bArr, int i, int i2) throws SignatureException {
        }

        @Override // java.security.SignatureSpi
        protected boolean engineVerify(byte[] bArr) throws SignatureException {
            return false;
        }
    }
}
