package org.bouncycastle.pqc.crypto.mldsa;

import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class MLDSAPrivateKeyParameters extends MLDSAKeyParameters {
    public static final int BOTH = 0;
    public static final int EXPANDED_KEY = 2;
    public static final int SEED_ONLY = 1;
    final byte[] k;
    private final int prefFormat;
    final byte[] rho;
    final byte[] s1;
    final byte[] s2;
    private final byte[] seed;
    final byte[] t0;
    private final byte[] t1;
    final byte[] tr;

    public MLDSAPrivateKeyParameters(MLDSAParameters mLDSAParameters, byte[] bArr) {
        this(mLDSAParameters, bArr, null);
    }

    public MLDSAPrivateKeyParameters(MLDSAParameters mLDSAParameters, byte[] bArr, MLDSAPublicKeyParameters mLDSAPublicKeyParameters) {
        super(true, mLDSAParameters);
        MLDSAEngine engine = mLDSAParameters.getEngine(null);
        if (bArr.length == 32) {
            byte[][] generateKeyPairInternal = engine.generateKeyPairInternal(bArr);
            this.rho = generateKeyPairInternal[0];
            this.k = generateKeyPairInternal[1];
            this.tr = generateKeyPairInternal[2];
            this.s1 = generateKeyPairInternal[3];
            this.s2 = generateKeyPairInternal[4];
            this.t0 = generateKeyPairInternal[5];
            this.t1 = generateKeyPairInternal[6];
            this.seed = generateKeyPairInternal[7];
        } else {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 32);
            this.rho = copyOfRange;
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 32, 64);
            this.k = copyOfRange2;
            byte[] copyOfRange3 = Arrays.copyOfRange(bArr, 64, 128);
            this.tr = copyOfRange3;
            int dilithiumL = (engine.getDilithiumL() * engine.getDilithiumPolyEtaPackedBytes()) + 128;
            byte[] copyOfRange4 = Arrays.copyOfRange(bArr, 128, dilithiumL);
            this.s1 = copyOfRange4;
            int dilithiumK = (engine.getDilithiumK() * engine.getDilithiumPolyEtaPackedBytes()) + dilithiumL;
            byte[] copyOfRange5 = Arrays.copyOfRange(bArr, dilithiumL, dilithiumK);
            this.s2 = copyOfRange5;
            byte[] copyOfRange6 = Arrays.copyOfRange(bArr, dilithiumK, (engine.getDilithiumK() * 416) + dilithiumK);
            this.t0 = copyOfRange6;
            this.t1 = engine.deriveT1(copyOfRange, copyOfRange2, copyOfRange3, copyOfRange4, copyOfRange5, copyOfRange6);
            this.seed = null;
        }
        if (mLDSAPublicKeyParameters != null && !Arrays.constantTimeAreEqual(this.t1, mLDSAPublicKeyParameters.getT1())) {
            throw new IllegalArgumentException("passed in public key does not match private values");
        }
        this.prefFormat = this.seed != null ? 0 : 2;
    }

    public MLDSAPrivateKeyParameters(MLDSAParameters mLDSAParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
        this(mLDSAParameters, bArr, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7, null);
    }

    public MLDSAPrivateKeyParameters(MLDSAParameters mLDSAParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8) {
        super(true, mLDSAParameters);
        this.rho = Arrays.clone(bArr);
        this.k = Arrays.clone(bArr2);
        this.tr = Arrays.clone(bArr3);
        this.s1 = Arrays.clone(bArr4);
        this.s2 = Arrays.clone(bArr5);
        this.t0 = Arrays.clone(bArr6);
        this.t1 = Arrays.clone(bArr7);
        this.seed = Arrays.clone(bArr8);
        this.prefFormat = bArr8 != null ? 0 : 2;
    }

    private MLDSAPrivateKeyParameters(MLDSAPrivateKeyParameters mLDSAPrivateKeyParameters, int i) {
        super(true, mLDSAPrivateKeyParameters.getParameters());
        this.rho = mLDSAPrivateKeyParameters.rho;
        this.k = mLDSAPrivateKeyParameters.k;
        this.tr = mLDSAPrivateKeyParameters.tr;
        this.s1 = mLDSAPrivateKeyParameters.s1;
        this.s2 = mLDSAPrivateKeyParameters.s2;
        this.t0 = mLDSAPrivateKeyParameters.t0;
        this.t1 = mLDSAPrivateKeyParameters.t1;
        this.seed = mLDSAPrivateKeyParameters.seed;
        this.prefFormat = i;
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(new byte[][]{this.rho, this.k, this.tr, this.s1, this.s2, this.t0});
    }

    public byte[] getK() {
        return Arrays.clone(this.k);
    }

    public MLDSAPrivateKeyParameters getParametersWithFormat(int i) {
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
        return new MLDSAPrivateKeyParameters(this, i);
    }

    public int getPreferredFormat() {
        return this.prefFormat;
    }

    public byte[] getPrivateKey() {
        return getEncoded();
    }

    public byte[] getPublicKey() {
        return MLDSAPublicKeyParameters.getEncoded(this.rho, this.t1);
    }

    public MLDSAPublicKeyParameters getPublicKeyParameters() {
        if (this.t1 == null) {
            return null;
        }
        return new MLDSAPublicKeyParameters(getParameters(), this.rho, this.t1);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getS1() {
        return Arrays.clone(this.s1);
    }

    public byte[] getS2() {
        return Arrays.clone(this.s2);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public byte[] getT0() {
        return Arrays.clone(this.t0);
    }

    public byte[] getT1() {
        return Arrays.clone(this.t1);
    }

    public byte[] getTr() {
        return Arrays.clone(this.tr);
    }
}
