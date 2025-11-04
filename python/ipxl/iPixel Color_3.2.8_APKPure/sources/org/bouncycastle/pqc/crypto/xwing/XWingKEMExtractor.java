package org.bouncycastle.pqc.crypto.xwing;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMExtractor;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class XWingKEMExtractor implements EncapsulatedSecretExtractor {
    private static final int MLKEM_CIPHERTEXT_SIZE = 1088;
    private final XWingPrivateKeyParameters key;
    private final MLKEMExtractor mlkemExtractor;

    public XWingKEMExtractor(XWingPrivateKeyParameters xWingPrivateKeyParameters) {
        this.key = xWingPrivateKeyParameters;
        this.mlkemExtractor = new MLKEMExtractor(xWingPrivateKeyParameters.getKyberPrivateKey());
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, MLKEM_CIPHERTEXT_SIZE);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, MLKEM_CIPHERTEXT_SIZE, bArr.length);
        byte[] computeSSX = XWingKEMGenerator.computeSSX(new X25519PublicKeyParameters(copyOfRange2, 0), this.key.getXDHPrivateKey());
        byte[] computeSharedSecret = XWingKEMGenerator.computeSharedSecret(this.key.getXDHPublicKey().getEncoded(), this.mlkemExtractor.extractSecret(copyOfRange), copyOfRange2, computeSSX);
        Arrays.clear(computeSSX);
        return computeSharedSecret;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return this.mlkemExtractor.getEncapsulationLength() + 32;
    }
}
