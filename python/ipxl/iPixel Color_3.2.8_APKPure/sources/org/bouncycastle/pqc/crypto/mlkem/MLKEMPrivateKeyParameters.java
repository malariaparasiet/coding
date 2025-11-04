package org.bouncycastle.pqc.crypto.mlkem;

import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class MLKEMPrivateKeyParameters extends MLKEMKeyParameters {
    public static final int BOTH = 0;
    public static final int EXPANDED_KEY = 2;
    public static final int SEED_ONLY = 1;
    final byte[] hpk;
    final byte[] nonce;
    private final int prefFormat;
    final byte[] rho;
    final byte[] s;
    final byte[] seed;
    final byte[] t;

    public MLKEMPrivateKeyParameters(MLKEMParameters mLKEMParameters, byte[] bArr) {
        this(mLKEMParameters, bArr, null);
    }

    public MLKEMPrivateKeyParameters(MLKEMParameters mLKEMParameters, byte[] bArr, MLKEMPublicKeyParameters mLKEMPublicKeyParameters) {
        super(true, mLKEMParameters);
        MLKEMEngine engine = mLKEMParameters.getEngine();
        if (bArr.length == 64) {
            byte[][] generateKemKeyPairInternal = engine.generateKemKeyPairInternal(Arrays.copyOfRange(bArr, 0, 32), Arrays.copyOfRange(bArr, 32, bArr.length));
            this.s = generateKemKeyPairInternal[2];
            this.hpk = generateKemKeyPairInternal[3];
            this.nonce = generateKemKeyPairInternal[4];
            this.t = generateKemKeyPairInternal[0];
            this.rho = generateKemKeyPairInternal[1];
            this.seed = generateKemKeyPairInternal[5];
        } else {
            this.s = Arrays.copyOfRange(bArr, 0, engine.getKyberIndCpaSecretKeyBytes());
            int kyberIndCpaSecretKeyBytes = engine.getKyberIndCpaSecretKeyBytes();
            this.t = Arrays.copyOfRange(bArr, kyberIndCpaSecretKeyBytes, (engine.getKyberIndCpaPublicKeyBytes() + kyberIndCpaSecretKeyBytes) - 32);
            int kyberIndCpaPublicKeyBytes = kyberIndCpaSecretKeyBytes + (engine.getKyberIndCpaPublicKeyBytes() - 32);
            int i = kyberIndCpaPublicKeyBytes + 32;
            this.rho = Arrays.copyOfRange(bArr, kyberIndCpaPublicKeyBytes, i);
            int i2 = kyberIndCpaPublicKeyBytes + 64;
            this.hpk = Arrays.copyOfRange(bArr, i, i2);
            this.nonce = Arrays.copyOfRange(bArr, i2, kyberIndCpaPublicKeyBytes + 96);
            this.seed = null;
        }
        if (mLKEMPublicKeyParameters != null && (!Arrays.constantTimeAreEqual(this.t, mLKEMPublicKeyParameters.t) || !Arrays.constantTimeAreEqual(this.rho, mLKEMPublicKeyParameters.rho))) {
            throw new IllegalArgumentException("passed in public key does not match private values");
        }
        this.prefFormat = this.seed != null ? 0 : 2;
    }

    public MLKEMPrivateKeyParameters(MLKEMParameters mLKEMParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        this(mLKEMParameters, bArr, bArr2, bArr3, bArr4, bArr5, null);
    }

    public MLKEMPrivateKeyParameters(MLKEMParameters mLKEMParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        super(true, mLKEMParameters);
        this.s = Arrays.clone(bArr);
        this.hpk = Arrays.clone(bArr2);
        this.nonce = Arrays.clone(bArr3);
        this.t = Arrays.clone(bArr4);
        this.rho = Arrays.clone(bArr5);
        this.seed = Arrays.clone(bArr6);
        this.prefFormat = 0;
    }

    private MLKEMPrivateKeyParameters(MLKEMPrivateKeyParameters mLKEMPrivateKeyParameters, int i) {
        super(true, mLKEMPrivateKeyParameters.getParameters());
        this.s = mLKEMPrivateKeyParameters.s;
        this.t = mLKEMPrivateKeyParameters.t;
        this.rho = mLKEMPrivateKeyParameters.rho;
        this.hpk = mLKEMPrivateKeyParameters.hpk;
        this.nonce = mLKEMPrivateKeyParameters.nonce;
        this.seed = mLKEMPrivateKeyParameters.seed;
        this.prefFormat = i;
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(new byte[][]{this.s, this.t, this.rho, this.hpk, this.nonce});
    }

    public byte[] getHPK() {
        return Arrays.clone(this.hpk);
    }

    public byte[] getNonce() {
        return Arrays.clone(this.nonce);
    }

    public MLKEMPrivateKeyParameters getParametersWithFormat(int i) {
        if (this.prefFormat == i) {
            return this;
        }
        if (i == 0 || i == 1) {
            if (this.seed == null) {
                throw new IllegalStateException("no seed available");
            }
        } else if (i != 2) {
            throw new IllegalArgumentException("unknown format");
        }
        return new MLKEMPrivateKeyParameters(this, i);
    }

    public int getPreferredFormat() {
        return this.prefFormat;
    }

    public byte[] getPublicKey() {
        return MLKEMPublicKeyParameters.getEncoded(this.t, this.rho);
    }

    public MLKEMPublicKeyParameters getPublicKeyParameters() {
        return new MLKEMPublicKeyParameters(getParameters(), this.t, this.rho);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getS() {
        return Arrays.clone(this.s);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public byte[] getT() {
        return Arrays.clone(this.t);
    }
}
