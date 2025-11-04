package org.bouncycastle.pqc.crypto.mldsa;

import java.io.IOException;
import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.params.ParametersWithContext;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.DigestUtils;

/* loaded from: classes4.dex */
public class HashMLDSASigner implements Signer {
    private static final byte[] EMPTY_CONTEXT = new byte[0];
    private Digest digest;
    private MLDSAEngine engine;
    private MLDSAPrivateKeyParameters privKey;
    private MLDSAPublicKeyParameters pubKey;
    private SecureRandom random;

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException, DataLengthException {
        byte[] bArr = new byte[32];
        SecureRandom secureRandom = this.random;
        if (secureRandom != null) {
            secureRandom.nextBytes(bArr);
        }
        MLDSAEngine mLDSAEngine = this.engine;
        byte[] generateMu = mLDSAEngine.generateMu(mLDSAEngine.shake256Digest);
        MLDSAEngine mLDSAEngine2 = this.engine;
        return mLDSAEngine2.generateSignature(generateMu, mLDSAEngine2.getShake256Digest(), this.privKey.rho, this.privKey.k, this.privKey.t0, this.privKey.s1, this.privKey.s2, bArr);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] bArr = EMPTY_CONTEXT;
        if (cipherParameters instanceof ParametersWithContext) {
            ParametersWithContext parametersWithContext = (ParametersWithContext) cipherParameters;
            bArr = parametersWithContext.getContext();
            cipherParameters = parametersWithContext.getParameters();
            if (bArr.length > 255) {
                throw new IllegalArgumentException("context too long");
            }
        }
        if (z) {
            this.pubKey = null;
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.privKey = (MLDSAPrivateKeyParameters) parametersWithRandom.getParameters();
                this.random = parametersWithRandom.getRandom();
            } else {
                this.privKey = (MLDSAPrivateKeyParameters) cipherParameters;
                this.random = null;
            }
            MLDSAEngine engine = this.privKey.getParameters().getEngine(this.random);
            this.engine = engine;
            engine.initSign(this.privKey.tr, true, bArr);
        } else {
            MLDSAPublicKeyParameters mLDSAPublicKeyParameters = (MLDSAPublicKeyParameters) cipherParameters;
            this.pubKey = mLDSAPublicKeyParameters;
            this.privKey = null;
            this.random = null;
            MLDSAEngine engine2 = mLDSAPublicKeyParameters.getParameters().getEngine(null);
            this.engine = engine2;
            engine2.initVerify(this.pubKey.rho, this.pubKey.t1, true, bArr);
        }
        SHAKEDigest sHAKEDigest = this.engine.shake256Digest;
        this.digest = sHAKEDigest;
        try {
            byte[] encoded = DigestUtils.getDigestOid(sHAKEDigest.getAlgorithmName()).getEncoded(ASN1Encoding.DER);
            this.digest.update(encoded, 0, encoded.length);
        } catch (IOException e) {
            throw new IllegalStateException("oid encoding failed: " + e.getMessage());
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.digest.reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.digest.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        MLDSAEngine mLDSAEngine = this.engine;
        byte[] generateMu = mLDSAEngine.generateMu(mLDSAEngine.shake256Digest);
        MLDSAEngine mLDSAEngine2 = this.engine;
        return mLDSAEngine2.verifyInternalMuSignature(generateMu, bArr, bArr.length, mLDSAEngine2.getShake256Digest(), this.pubKey.rho, this.pubKey.t1);
    }
}
