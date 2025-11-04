package org.bouncycastle.pqc.crypto.xwing;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.agreement.X25519Agreement;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMGenerator;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPublicKeyParameters;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes4.dex */
public class XWingKEMGenerator implements EncapsulatedSecretGenerator {
    private static final byte[] XWING_LABEL = Strings.toByteArray("\\.//^\\");
    private final SecureRandom random;

    public XWingKEMGenerator(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    static byte[] computeSSX(X25519PublicKeyParameters x25519PublicKeyParameters, X25519PrivateKeyParameters x25519PrivateKeyParameters) {
        X25519Agreement x25519Agreement = new X25519Agreement();
        x25519Agreement.init(x25519PrivateKeyParameters);
        byte[] bArr = new byte[x25519Agreement.getAgreementSize()];
        x25519Agreement.calculateAgreement(x25519PublicKeyParameters, bArr, 0);
        return bArr;
    }

    static byte[] computeSharedSecret(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        SHA3Digest sHA3Digest = new SHA3Digest(256);
        sHA3Digest.update(bArr2, 0, bArr2.length);
        sHA3Digest.update(bArr4, 0, bArr4.length);
        sHA3Digest.update(bArr3, 0, bArr3.length);
        sHA3Digest.update(bArr, 0, bArr.length);
        byte[] bArr5 = XWING_LABEL;
        sHA3Digest.update(bArr5, 0, bArr5.length);
        byte[] bArr6 = new byte[32];
        sHA3Digest.doFinal(bArr6, 0);
        return bArr6;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        XWingPublicKeyParameters xWingPublicKeyParameters = (XWingPublicKeyParameters) asymmetricKeyParameter;
        MLKEMPublicKeyParameters kyberPublicKey = xWingPublicKeyParameters.getKyberPublicKey();
        X25519PublicKeyParameters xDHPublicKey = xWingPublicKeyParameters.getXDHPublicKey();
        byte[] encoded = xDHPublicKey.getEncoded();
        SecretWithEncapsulation generateEncapsulated = new MLKEMGenerator(this.random).generateEncapsulated(kyberPublicKey);
        byte[] encapsulation = generateEncapsulated.getEncapsulation();
        X25519KeyPairGenerator x25519KeyPairGenerator = new X25519KeyPairGenerator();
        x25519KeyPairGenerator.init(new X25519KeyGenerationParameters(this.random));
        AsymmetricCipherKeyPair generateKeyPair = x25519KeyPairGenerator.generateKeyPair();
        byte[] encoded2 = ((X25519PublicKeyParameters) generateKeyPair.getPublic()).getEncoded();
        byte[] computeSSX = computeSSX(xDHPublicKey, (X25519PrivateKeyParameters) generateKeyPair.getPrivate());
        byte[] computeSharedSecret = computeSharedSecret(encoded, generateEncapsulated.getSecret(), encoded2, computeSSX);
        Arrays.clear(computeSSX);
        return new SecretWithEncapsulationImpl(computeSharedSecret, Arrays.concatenate(encapsulation, encoded2));
    }
}
