package org.bouncycastle.crypto.threshold;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface SplitSecret {
    byte[] getSecret() throws IOException;

    SecretShare[] getSecretShares();
}
