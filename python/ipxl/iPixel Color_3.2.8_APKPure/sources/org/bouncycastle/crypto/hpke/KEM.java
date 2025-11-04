package org.bouncycastle.crypto.hpke;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes3.dex */
public abstract class KEM {
    abstract byte[] AuthDecap(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair, AsymmetricKeyParameter asymmetricKeyParameter);

    abstract byte[][] AuthEncap(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricCipherKeyPair asymmetricCipherKeyPair);

    abstract byte[] Decap(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair);

    abstract AsymmetricCipherKeyPair DeriveKeyPair(byte[] bArr);

    abstract AsymmetricCipherKeyPair DeserializePrivateKey(byte[] bArr, byte[] bArr2);

    abstract AsymmetricKeyParameter DeserializePublicKey(byte[] bArr);

    abstract byte[][] Encap(AsymmetricKeyParameter asymmetricKeyParameter);

    abstract byte[][] Encap(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricCipherKeyPair asymmetricCipherKeyPair);

    abstract AsymmetricCipherKeyPair GeneratePrivateKey();

    abstract byte[] SerializePrivateKey(AsymmetricKeyParameter asymmetricKeyParameter);

    abstract byte[] SerializePublicKey(AsymmetricKeyParameter asymmetricKeyParameter);

    abstract int getEncryptionSize();
}
