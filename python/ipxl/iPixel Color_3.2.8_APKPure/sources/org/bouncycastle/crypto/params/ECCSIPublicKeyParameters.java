package org.bouncycastle.crypto.params;

import org.bouncycastle.math.ec.ECPoint;

/* loaded from: classes3.dex */
public class ECCSIPublicKeyParameters extends AsymmetricKeyParameter {
    private final ECPoint pvt;

    public ECCSIPublicKeyParameters(ECPoint eCPoint) {
        super(false);
        this.pvt = eCPoint;
    }

    public final ECPoint getPVT() {
        return this.pvt;
    }
}
