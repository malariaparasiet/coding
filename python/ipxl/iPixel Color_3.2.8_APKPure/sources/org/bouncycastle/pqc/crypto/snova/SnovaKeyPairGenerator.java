package org.bouncycastle.pqc.crypto.snova;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class SnovaKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    static final int privateSeedLength = 32;
    static final int publicSeedLength = 16;
    private static final int seedLength = 48;
    private SnovaEngine engine;
    private boolean initialized;
    private SnovaParameters params;
    private SecureRandom random;

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        if (!this.initialized) {
            throw new IllegalStateException("SNOVA key pair generator not initialized");
        }
        byte[] bArr = new byte[48];
        this.random.nextBytes(bArr);
        byte[] bArr2 = new byte[this.params.getPublicKeyLength()];
        int privateKeyLength = this.params.getPrivateKeyLength();
        byte[] bArr3 = new byte[privateKeyLength];
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 16);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 16, 48);
        SnovaKeyElements snovaKeyElements = new SnovaKeyElements(this.params);
        System.arraycopy(copyOfRange, 0, bArr2, 0, copyOfRange.length);
        this.engine.genMap1T12Map2(snovaKeyElements, copyOfRange, copyOfRange2);
        this.engine.genP22(bArr2, copyOfRange.length, snovaKeyElements.T12, snovaKeyElements.map1.p21, snovaKeyElements.map2.f12);
        System.arraycopy(copyOfRange, 0, bArr2, 0, copyOfRange.length);
        if (!this.params.isSkIsSeed()) {
            int o = this.params.getO();
            int lsq = this.params.getLsq();
            int v = this.params.getV();
            int i = v * o;
            int alpha = (this.params.getAlpha() * o * lsq * 4) + (i * lsq) + (((i * v) + (i * o) + (o * o * v)) * lsq);
            byte[] bArr4 = new byte[alpha];
            SnovaKeyElements.copy4d(snovaKeyElements.map2.f21, bArr4, SnovaKeyElements.copy4d(snovaKeyElements.map2.f12, bArr4, SnovaKeyElements.copy4d(snovaKeyElements.map2.f11, bArr4, SnovaKeyElements.copy3d(snovaKeyElements.T12, bArr4, SnovaKeyElements.copy3d(snovaKeyElements.map1.qAlpha2, bArr4, SnovaKeyElements.copy3d(snovaKeyElements.map1.qAlpha1, bArr4, SnovaKeyElements.copy3d(snovaKeyElements.map1.bAlpha, bArr4, SnovaKeyElements.copy3d(snovaKeyElements.map1.aAlpha, bArr4, 0))))))));
            GF16Utils.encodeMergeInHalf(bArr4, alpha, bArr3);
            System.arraycopy(bArr, 0, bArr3, privateKeyLength - 48, 48);
            bArr = bArr3;
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new SnovaPublicKeyParameters(this.params, bArr2), (AsymmetricKeyParameter) new SnovaPrivateKeyParameters(this.params, bArr));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        SnovaKeyGenerationParameters snovaKeyGenerationParameters = (SnovaKeyGenerationParameters) keyGenerationParameters;
        this.params = snovaKeyGenerationParameters.getParameters();
        this.random = snovaKeyGenerationParameters.getRandom();
        this.initialized = true;
        this.engine = new SnovaEngine(this.params);
    }
}
