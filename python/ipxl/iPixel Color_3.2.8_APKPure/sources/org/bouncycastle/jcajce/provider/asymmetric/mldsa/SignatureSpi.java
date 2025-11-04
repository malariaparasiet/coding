package org.bouncycastle.jcajce.provider.asymmetric.mldsa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.jcajce.MLDSAProxyPrivateKey;
import org.bouncycastle.jcajce.interfaces.MLDSAPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseDeterministicOrRandomSignature;
import org.bouncycastle.jcajce.spec.MLDSAParameterSpec;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSASigner;
import org.bouncycastle.pqc.crypto.util.PublicKeyFactory;

/* loaded from: classes4.dex */
public class SignatureSpi extends BaseDeterministicOrRandomSignature {
    protected MLDSAParameters parameters;
    protected MLDSASigner signer;

    public static class MLDSA extends SignatureSpi {
        public MLDSA() {
            super(new MLDSASigner());
        }
    }

    public static class MLDSA44 extends SignatureSpi {
        public MLDSA44() {
            super(new MLDSASigner(), MLDSAParameters.ml_dsa_44);
        }
    }

    public static class MLDSA65 extends SignatureSpi {
        public MLDSA65() {
            super(new MLDSASigner(), MLDSAParameters.ml_dsa_65);
        }
    }

    public static class MLDSA87 extends SignatureSpi {
        public MLDSA87() throws NoSuchAlgorithmException {
            super(new MLDSASigner(), MLDSAParameters.ml_dsa_87);
        }
    }

    public static class MLDSACalcMu extends SignatureSpi {
        public MLDSACalcMu() {
            super(new MLDSASigner());
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.mldsa.SignatureSpi, java.security.SignatureSpi
        protected byte[] engineSign() throws SignatureException {
            try {
                return this.signer.generateMu();
            } catch (Exception e) {
                throw new SignatureException(e.toString());
            }
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.mldsa.SignatureSpi, java.security.SignatureSpi
        protected boolean engineVerify(byte[] bArr) throws SignatureException {
            return this.signer.verifyMu(bArr);
        }
    }

    public static class MLDSAExtMu extends SignatureSpi {
        private ByteArrayOutputStream bOut;

        public MLDSAExtMu() {
            super(new MLDSASigner());
            this.bOut = new ByteArrayOutputStream(64);
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.mldsa.SignatureSpi, java.security.SignatureSpi
        protected byte[] engineSign() throws SignatureException {
            try {
                byte[] byteArray = this.bOut.toByteArray();
                this.bOut.reset();
                return this.signer.generateMuSignature(byteArray);
            } catch (DataLengthException e) {
                throw new SignatureException(e.getMessage());
            } catch (Exception e2) {
                throw new SignatureException(e2.toString());
            }
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.mldsa.SignatureSpi, java.security.SignatureSpi
        protected boolean engineVerify(byte[] bArr) throws SignatureException {
            byte[] byteArray = this.bOut.toByteArray();
            this.bOut.reset();
            try {
                return this.signer.verifyMuSignature(byteArray, bArr);
            } catch (DataLengthException e) {
                throw new SignatureException(e.getMessage());
            }
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.mldsa.SignatureSpi, org.bouncycastle.jcajce.provider.asymmetric.util.BaseDeterministicOrRandomSignature
        protected void updateEngine(byte b) throws SignatureException {
            this.bOut.write(b);
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.mldsa.SignatureSpi, org.bouncycastle.jcajce.provider.asymmetric.util.BaseDeterministicOrRandomSignature
        protected void updateEngine(byte[] bArr, int i, int i2) throws SignatureException {
            this.bOut.write(bArr, i, i2);
        }
    }

    protected SignatureSpi(MLDSASigner mLDSASigner) {
        super("MLDSA");
        this.signer = mLDSASigner;
        this.parameters = null;
    }

    protected SignatureSpi(MLDSASigner mLDSASigner, MLDSAParameters mLDSAParameters) {
        super(MLDSAParameterSpec.fromName(mLDSAParameters.getName()).getName());
        this.signer = mLDSASigner;
        this.parameters = mLDSAParameters;
    }

    @Override // java.security.SignatureSpi
    protected byte[] engineSign() throws SignatureException {
        try {
            return this.signer.generateSignature();
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) throws SignatureException {
        return this.signer.verifySignature(bArr);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseDeterministicOrRandomSignature
    protected void reInitialize(boolean z, CipherParameters cipherParameters) {
        this.signer.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseDeterministicOrRandomSignature
    protected void signInit(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.appRandom = secureRandom;
        if (privateKey instanceof BCMLDSAPrivateKey) {
            BCMLDSAPrivateKey bCMLDSAPrivateKey = (BCMLDSAPrivateKey) privateKey;
            this.keyParams = bCMLDSAPrivateKey.getKeyParams();
            MLDSAParameters mLDSAParameters = this.parameters;
            if (mLDSAParameters != null) {
                String name = MLDSAParameterSpec.fromName(mLDSAParameters.getName()).getName();
                if (!name.equals(bCMLDSAPrivateKey.getAlgorithm())) {
                    throw new InvalidKeyException("signature configured for " + name);
                }
                return;
            }
            return;
        }
        if (!(privateKey instanceof MLDSAProxyPrivateKey) || !(this instanceof MLDSACalcMu)) {
            throw new InvalidKeyException("unknown private key passed to ML-DSA");
        }
        MLDSAPublicKey publicKey = ((MLDSAProxyPrivateKey) privateKey).getPublicKey();
        try {
            this.keyParams = PublicKeyFactory.createKey(publicKey.getEncoded());
            MLDSAParameters mLDSAParameters2 = this.parameters;
            if (mLDSAParameters2 != null) {
                String name2 = MLDSAParameterSpec.fromName(mLDSAParameters2.getName()).getName();
                if (!name2.equals(publicKey.getAlgorithm())) {
                    throw new InvalidKeyException("signature configured for " + name2);
                }
            }
        } catch (IOException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseDeterministicOrRandomSignature
    protected void updateEngine(byte b) throws SignatureException {
        this.signer.update(b);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseDeterministicOrRandomSignature
    protected void updateEngine(byte[] bArr, int i, int i2) throws SignatureException {
        this.signer.update(bArr, i, i2);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseDeterministicOrRandomSignature
    protected void verifyInit(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof BCMLDSAPublicKey)) {
            throw new InvalidKeyException("unknown public key passed to ML-DSA");
        }
        BCMLDSAPublicKey bCMLDSAPublicKey = (BCMLDSAPublicKey) publicKey;
        this.keyParams = bCMLDSAPublicKey.getKeyParams();
        MLDSAParameters mLDSAParameters = this.parameters;
        if (mLDSAParameters != null) {
            String name = MLDSAParameterSpec.fromName(mLDSAParameters.getName()).getName();
            if (!name.equals(bCMLDSAPublicKey.getAlgorithm())) {
                throw new InvalidKeyException("signature configured for " + name);
            }
        }
    }
}
