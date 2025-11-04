package org.bouncycastle.pqc.jcajce.provider.mayo;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.mayo.MayoParameters;
import org.bouncycastle.pqc.crypto.mayo.MayoPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mayo.MayoSigner;
import org.bouncycastle.util.Strings;

/* loaded from: classes4.dex */
public class SignatureSpi extends Signature {
    private final ByteArrayOutputStream bOut;
    private final MayoParameters parameters;
    private SecureRandom random;
    private final MayoSigner signer;

    public static class Base extends SignatureSpi {
        public Base() {
            super(new MayoSigner());
        }
    }

    public static class Mayo1 extends SignatureSpi {
        public Mayo1() {
            super(new MayoSigner(), MayoParameters.mayo1);
        }
    }

    public static class Mayo2 extends SignatureSpi {
        public Mayo2() {
            super(new MayoSigner(), MayoParameters.mayo2);
        }
    }

    public static class Mayo3 extends SignatureSpi {
        public Mayo3() {
            super(new MayoSigner(), MayoParameters.mayo3);
        }
    }

    public static class Mayo5 extends SignatureSpi {
        public Mayo5() {
            super(new MayoSigner(), MayoParameters.mayo5);
        }
    }

    protected SignatureSpi(MayoSigner mayoSigner) {
        super("Mayo");
        this.bOut = new ByteArrayOutputStream();
        this.signer = mayoSigner;
        this.parameters = null;
    }

    protected SignatureSpi(MayoSigner mayoSigner, MayoParameters mayoParameters) {
        super(Strings.toUpperCase(mayoParameters.getName()));
        this.parameters = mayoParameters;
        this.bOut = new ByteArrayOutputStream();
        this.signer = mayoSigner;
    }

    @Override // java.security.SignatureSpi
    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (!(privateKey instanceof BCMayoPrivateKey)) {
            throw new InvalidKeyException("unknown private key passed to Mayo");
        }
        BCMayoPrivateKey bCMayoPrivateKey = (BCMayoPrivateKey) privateKey;
        MayoPrivateKeyParameters keyParams = bCMayoPrivateKey.getKeyParams();
        MayoParameters mayoParameters = this.parameters;
        if (mayoParameters != null) {
            String upperCase = Strings.toUpperCase(mayoParameters.getName());
            if (!upperCase.equals(bCMayoPrivateKey.getAlgorithm())) {
                throw new InvalidKeyException("signature configured for " + upperCase);
            }
        }
        if (this.random != null) {
            this.signer.init(true, new ParametersWithRandom(keyParams, this.random));
        } else {
            this.signer.init(true, keyParams);
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        engineInitSign(privateKey);
    }

    @Override // java.security.SignatureSpi
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof BCMayoPublicKey)) {
            try {
                publicKey = new BCMayoPublicKey(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
            } catch (Exception e) {
                throw new InvalidKeyException("unknown public key passed to Mayo: " + e.getMessage());
            }
        }
        BCMayoPublicKey bCMayoPublicKey = (BCMayoPublicKey) publicKey;
        MayoParameters mayoParameters = this.parameters;
        if (mayoParameters != null) {
            String upperCase = Strings.toUpperCase(mayoParameters.getName());
            if (!upperCase.equals(bCMayoPublicKey.getAlgorithm())) {
                throw new InvalidKeyException("signature configured for " + upperCase);
            }
        }
        this.signer.init(false, bCMayoPublicKey.getKeyParams());
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected byte[] engineSign() throws SignatureException {
        try {
            byte[] byteArray = this.bOut.toByteArray();
            this.bOut.reset();
            return this.signer.generateSignature(byteArray);
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b) throws SignatureException {
        this.bOut.write(b);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) throws SignatureException {
        this.bOut.write(bArr, i, i2);
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) throws SignatureException {
        byte[] byteArray = this.bOut.toByteArray();
        this.bOut.reset();
        return this.signer.verifySignature(byteArray, bArr);
    }
}
