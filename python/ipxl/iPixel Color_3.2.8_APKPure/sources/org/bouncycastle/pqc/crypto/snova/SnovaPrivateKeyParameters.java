package org.bouncycastle.pqc.crypto.snova;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class SnovaPrivateKeyParameters extends AsymmetricKeyParameter {
    private final SnovaParameters parameters;
    private final byte[] privateKey;

    public SnovaPrivateKeyParameters(SnovaParameters snovaParameters, byte[] bArr) {
        super(true);
        this.privateKey = Arrays.clone(bArr);
        this.parameters = snovaParameters;
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.privateKey);
    }

    public SnovaParameters getParameters() {
        return this.parameters;
    }

    public byte[] getPrivateKey() {
        return Arrays.clone(this.privateKey);
    }
}
