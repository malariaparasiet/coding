package org.bouncycastle.pqc.crypto.mayo;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes4.dex */
public class MayoKeyParameters extends AsymmetricKeyParameter {
    private final MayoParameters params;

    public MayoKeyParameters(boolean z, MayoParameters mayoParameters) {
        super(z);
        this.params = mayoParameters;
    }

    public MayoParameters getParameters() {
        return this.params;
    }
}
