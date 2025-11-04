package org.bouncycastle.pqc.crypto.mayo;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.GF16;
import org.bouncycastle.util.Longs;

/* loaded from: classes4.dex */
public class MayoKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private MayoParameters p;
    private SecureRandom random;

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        int i;
        int mVecLimbs = this.p.getMVecLimbs();
        int m = this.p.getM();
        int v = this.p.getV();
        int o = this.p.getO();
        int oBytes = this.p.getOBytes();
        int p1Limbs = this.p.getP1Limbs();
        int p3Limbs = this.p.getP3Limbs();
        int pkSeedBytes = this.p.getPkSeedBytes();
        int skSeedBytes = this.p.getSkSeedBytes();
        byte[] bArr = new byte[this.p.getCpkBytes()];
        byte[] bArr2 = new byte[this.p.getCskBytes()];
        int i2 = oBytes + pkSeedBytes;
        byte[] bArr3 = new byte[i2];
        long[] jArr = new long[this.p.getP2Limbs() + p1Limbs];
        long[] jArr2 = new long[o * o * mVecLimbs];
        int i3 = v * o;
        byte[] bArr4 = new byte[i3];
        this.random.nextBytes(bArr2);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr2, 0, skSeedBytes);
        sHAKEDigest.doFinal(bArr3, 0, i2);
        GF16.decode(bArr3, pkSeedBytes, bArr4, 0, i3);
        Utils.expandP1P2(this.p, jArr, bArr3);
        int i4 = 0;
        GF16Utils.mulAddMUpperTriangularMatXMat(mVecLimbs, jArr, bArr4, jArr, p1Limbs, v, o);
        byte[] bArr5 = bArr4;
        GF16Utils.mulAddMatTransXMMat(mVecLimbs, bArr5, jArr, p1Limbs, jArr2, v, o);
        System.arraycopy(bArr3, 0, bArr, 0, pkSeedBytes);
        long[] jArr3 = new long[p3Limbs];
        int i5 = o * mVecLimbs;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            byte[] bArr6 = bArr5;
            if (i6 >= o) {
                Utils.packMVecs(jArr3, bArr, pkSeedBytes, p3Limbs / mVecLimbs, m);
                Arrays.clear(bArr6);
                Arrays.clear(jArr2);
                return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new MayoPublicKeyParameters(this.p, bArr), (AsymmetricKeyParameter) new MayoPrivateKeyParameters(this.p, bArr2));
            }
            int i9 = i6;
            int i10 = i7;
            int i11 = i4;
            while (true) {
                i = i5;
                if (i9 < o) {
                    System.arraycopy(jArr2, i7 + i11, jArr3, i8, mVecLimbs);
                    if (i6 != i9) {
                        Longs.xorTo(mVecLimbs, jArr2, i10 + i4, jArr3, i8);
                    }
                    i8 += mVecLimbs;
                    i9++;
                    i11 += mVecLimbs;
                    i10 += i;
                    i5 = i;
                }
            }
            i6++;
            i7 += i;
            i4 += mVecLimbs;
            bArr5 = bArr6;
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.p = ((MayoKeyGenerationParameters) keyGenerationParameters).getParameters();
        this.random = keyGenerationParameters.getRandom();
    }
}
