package org.bouncycastle.crypto.threshold;

import java.io.IOException;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class ShamirSplitSecretShare implements SecretShare {
    final int r;
    private final byte[] secretShare;

    public ShamirSplitSecretShare(byte[] bArr) {
        this.secretShare = Arrays.clone(bArr);
        this.r = 1;
    }

    public ShamirSplitSecretShare(byte[] bArr, int i) {
        this.secretShare = Arrays.clone(bArr);
        this.r = i;
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return Arrays.clone(this.secretShare);
    }
}
