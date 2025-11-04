package org.bouncycastle.pqc.crypto.xwing;

import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPublicKeyParameters;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class XWingPrivateKeyParameters extends XWingKeyParameters {
    private final transient MLKEMPrivateKeyParameters kyberPrivateKey;
    private final transient MLKEMPublicKeyParameters kyberPublicKey;
    private final transient byte[] seed;
    private final transient X25519PrivateKeyParameters xdhPrivateKey;
    private final transient X25519PublicKeyParameters xdhPublicKey;

    public XWingPrivateKeyParameters(byte[] bArr) {
        super(true);
        XWingPrivateKeyParameters xWingPrivateKeyParameters = (XWingPrivateKeyParameters) XWingKeyPairGenerator.genKeyPair(bArr).getPrivate();
        this.seed = xWingPrivateKeyParameters.seed;
        this.kyberPrivateKey = xWingPrivateKeyParameters.kyberPrivateKey;
        this.xdhPrivateKey = xWingPrivateKeyParameters.xdhPrivateKey;
        this.kyberPublicKey = xWingPrivateKeyParameters.kyberPublicKey;
        this.xdhPublicKey = xWingPrivateKeyParameters.xdhPublicKey;
    }

    public XWingPrivateKeyParameters(byte[] bArr, MLKEMPrivateKeyParameters mLKEMPrivateKeyParameters, X25519PrivateKeyParameters x25519PrivateKeyParameters, MLKEMPublicKeyParameters mLKEMPublicKeyParameters, X25519PublicKeyParameters x25519PublicKeyParameters) {
        super(true);
        this.seed = Arrays.clone(bArr);
        this.kyberPrivateKey = mLKEMPrivateKeyParameters;
        this.xdhPrivateKey = x25519PrivateKeyParameters;
        this.kyberPublicKey = mLKEMPublicKeyParameters;
        this.xdhPublicKey = x25519PublicKeyParameters;
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.seed);
    }

    MLKEMPrivateKeyParameters getKyberPrivateKey() {
        return this.kyberPrivateKey;
    }

    MLKEMPublicKeyParameters getKyberPublicKey() {
        return this.kyberPublicKey;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    X25519PrivateKeyParameters getXDHPrivateKey() {
        return this.xdhPrivateKey;
    }

    X25519PublicKeyParameters getXDHPublicKey() {
        return this.xdhPublicKey;
    }
}
