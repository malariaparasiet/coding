package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class ECCSIKeyGenerationParameters extends KeyGenerationParameters {
    private final ECPoint G;
    private final Digest digest;
    private final byte[] id;
    private final ECPoint kpak;
    private final BigInteger ksak;
    private final int n;
    private final BigInteger q;

    public ECCSIKeyGenerationParameters(SecureRandom secureRandom, X9ECParameters x9ECParameters, Digest digest, byte[] bArr) {
        super(secureRandom, x9ECParameters.getCurve().getA().bitLength());
        BigInteger order = x9ECParameters.getCurve().getOrder();
        this.q = order;
        ECPoint g = x9ECParameters.getG();
        this.G = g;
        this.digest = digest;
        this.id = Arrays.clone(bArr);
        int bitLength = x9ECParameters.getCurve().getA().bitLength();
        this.n = bitLength;
        BigInteger mod = BigIntegers.createRandomBigInteger(bitLength, secureRandom).mod(order);
        this.ksak = mod;
        this.kpak = g.multiply(mod).normalize();
    }

    public BigInteger computeSSK(BigInteger bigInteger) {
        return this.ksak.add(bigInteger).mod(this.q);
    }

    public Digest getDigest() {
        return this.digest;
    }

    public ECPoint getG() {
        return this.G;
    }

    public byte[] getId() {
        return Arrays.clone(this.id);
    }

    public ECPoint getKPAK() {
        return this.kpak;
    }

    public int getN() {
        return this.n;
    }

    public BigInteger getQ() {
        return this.q;
    }
}
