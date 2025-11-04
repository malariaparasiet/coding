package org.bouncycastle.jcajce.spec;

import java.security.spec.KeySpec;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class MLDSAPrivateKeySpec implements KeySpec {
    private final byte[] data;
    private final boolean isSeed;
    private final MLDSAParameterSpec params;
    private final byte[] publicData;

    public MLDSAPrivateKeySpec(MLDSAParameterSpec mLDSAParameterSpec, byte[] bArr) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("incorrect length for seed");
        }
        this.isSeed = true;
        this.params = mLDSAParameterSpec;
        this.data = Arrays.clone(bArr);
        this.publicData = null;
    }

    public MLDSAPrivateKeySpec(MLDSAParameterSpec mLDSAParameterSpec, byte[] bArr, byte[] bArr2) {
        this.isSeed = false;
        this.params = mLDSAParameterSpec;
        this.data = Arrays.clone(bArr);
        this.publicData = Arrays.clone(bArr2);
    }

    public MLDSAParameterSpec getParameterSpec() {
        return this.params;
    }

    public byte[] getPrivateData() {
        if (isSeed()) {
            throw new IllegalStateException("KeySpec represents seed");
        }
        return Arrays.clone(this.data);
    }

    public byte[] getPublicData() {
        if (isSeed()) {
            throw new IllegalStateException("KeySpec represents long form");
        }
        return Arrays.clone(this.publicData);
    }

    public byte[] getSeed() {
        if (isSeed()) {
            return Arrays.clone(this.data);
        }
        throw new IllegalStateException("KeySpec represents long form");
    }

    public boolean isSeed() {
        return this.isSeed;
    }
}
