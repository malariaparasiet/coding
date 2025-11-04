package org.bouncycastle.crypto;

/* loaded from: classes3.dex */
public interface EncapsulatedSecretExtractor {
    byte[] extractSecret(byte[] bArr);

    int getEncapsulationLength();
}
