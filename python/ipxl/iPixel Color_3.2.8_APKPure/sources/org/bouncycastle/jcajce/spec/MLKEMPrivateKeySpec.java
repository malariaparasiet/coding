package org.bouncycastle.jcajce.spec;

import java.security.spec.KeySpec;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class MLKEMPrivateKeySpec implements KeySpec {
    private final byte[] data;
    private final boolean isSeed;
    private final MLKEMParameterSpec params;
    private final byte[] publicData;

    public MLKEMPrivateKeySpec(MLKEMParameterSpec mLKEMParameterSpec, byte[] bArr) {
        if (bArr.length != 64) {
            throw new IllegalArgumentException("incorrect length for seed");
        }
        this.isSeed = true;
        this.params = mLKEMParameterSpec;
        this.data = Arrays.clone(bArr);
        this.publicData = null;
    }

    public MLKEMPrivateKeySpec(MLKEMParameterSpec mLKEMParameterSpec, byte[] bArr, byte[] bArr2) {
        this.isSeed = false;
        this.params = mLKEMParameterSpec;
        this.data = Arrays.clone(bArr);
        this.publicData = Arrays.clone(bArr2);
    }

    public MLKEMParameterSpec getParameterSpec() {
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
