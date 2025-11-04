package org.bouncycastle.pqc.crypto.ntru;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class NTRUKEMExtractor implements EncapsulatedSecretExtractor {
    private final NTRUPrivateKeyParameters ntruPrivateKey;

    public NTRUKEMExtractor(NTRUPrivateKeyParameters nTRUPrivateKeyParameters) {
        if (nTRUPrivateKeyParameters == null) {
            throw new NullPointerException("'ntruPrivateKey' cannot be null");
        }
        this.ntruPrivateKey = nTRUPrivateKeyParameters;
    }

    private void cmov(byte[] bArr, byte[] bArr2, byte b) {
        byte b2 = (byte) ((~b) + 1);
        for (int i = 0; i < bArr.length; i++) {
            byte b3 = bArr[i];
            bArr[i] = (byte) (b3 ^ ((bArr2[i] ^ b3) & b2));
        }
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        NTRUParameterSet parameterSet = this.ntruPrivateKey.getParameters().getParameterSet();
        if (bArr == null) {
            throw new NullPointerException("'encapsulation' cannot be null");
        }
        if (bArr.length != parameterSet.ntruCiphertextBytes()) {
            throw new IllegalArgumentException("encapsulation");
        }
        byte[] bArr2 = this.ntruPrivateKey.privateKey;
        OWCPADecryptResult decrypt = new NTRUOWCPA(parameterSet).decrypt(bArr, bArr2);
        byte[] bArr3 = decrypt.rm;
        int i = decrypt.fail;
        SHA3Digest sHA3Digest = new SHA3Digest(256);
        byte[] bArr4 = new byte[sHA3Digest.getDigestSize()];
        sHA3Digest.update(bArr3, 0, bArr3.length);
        sHA3Digest.doFinal(bArr4, 0);
        sHA3Digest.update(bArr2, parameterSet.owcpaSecretKeyBytes(), parameterSet.prfKeyBytes());
        sHA3Digest.update(bArr, 0, bArr.length);
        sHA3Digest.doFinal(bArr3, 0);
        cmov(bArr4, bArr3, (byte) i);
        byte[] copyOfRange = Arrays.copyOfRange(bArr4, 0, parameterSet.sharedKeyBytes());
        Arrays.clear(bArr4);
        return copyOfRange;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return this.ntruPrivateKey.getParameters().getParameterSet().ntruCiphertextBytes();
    }
}
