package org.bouncycastle.pqc.crypto.mayo;

import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class MayoPrivateKeyParameters extends MayoKeyParameters {
    private final byte[] seed_sk;

    public MayoPrivateKeyParameters(MayoParameters mayoParameters, byte[] bArr) {
        super(true, mayoParameters);
        this.seed_sk = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.seed_sk);
    }

    public byte[] getSeedSk() {
        return Arrays.clone(this.seed_sk);
    }
}
