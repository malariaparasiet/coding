package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class ECCSIPrivateKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: pub, reason: collision with root package name */
    private final ECCSIPublicKeyParameters f15pub;
    private final BigInteger ssk;

    public ECCSIPrivateKeyParameters(BigInteger bigInteger, ECCSIPublicKeyParameters eCCSIPublicKeyParameters) {
        super(true);
        this.ssk = bigInteger;
        this.f15pub = eCCSIPublicKeyParameters;
    }

    public ECCSIPublicKeyParameters getPublicKeyParameters() {
        return this.f15pub;
    }

    public BigInteger getSSK() {
        return this.ssk;
    }
}
