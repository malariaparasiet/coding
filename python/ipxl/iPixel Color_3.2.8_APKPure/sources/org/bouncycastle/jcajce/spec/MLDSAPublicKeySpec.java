package org.bouncycastle.jcajce.spec;

import java.security.spec.KeySpec;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class MLDSAPublicKeySpec implements KeySpec {
    private final MLDSAParameterSpec params;
    private final byte[] publicData;

    public MLDSAPublicKeySpec(MLDSAParameterSpec mLDSAParameterSpec, byte[] bArr) {
        this.params = mLDSAParameterSpec;
        this.publicData = Arrays.clone(bArr);
    }

    public MLDSAParameterSpec getParameterSpec() {
        return this.params;
    }

    public byte[] getPublicData() {
        return Arrays.clone(this.publicData);
    }
}
