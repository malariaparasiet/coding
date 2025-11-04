package org.bouncycastle.crypto.signers;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.ECCSIPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECCSIPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class ECCSISigner implements Signer {
    private final ECPoint G;
    private final int N;
    private ECPoint Y;
    private final Digest digest;
    private boolean forSigning;
    private final byte[] id;
    private BigInteger j;
    private final ECPoint kpak;
    private CipherParameters param;
    private final BigInteger q;
    private BigInteger r;
    private ByteArrayOutputStream stream;

    public ECCSISigner(ECPoint eCPoint, X9ECParameters x9ECParameters, Digest digest, byte[] bArr) {
        this.kpak = eCPoint;
        this.id = bArr;
        this.q = x9ECParameters.getCurve().getOrder();
        this.G = x9ECParameters.getG();
        this.digest = digest;
        digest.reset();
        this.N = (x9ECParameters.getCurve().getOrder().bitLength() + 7) >> 3;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException, DataLengthException {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        ECCSIPrivateKeyParameters eCCSIPrivateKeyParameters = (ECCSIPrivateKeyParameters) ((ParametersWithRandom) this.param).getParameters();
        BigInteger mod = new BigInteger(1, bArr).add(this.r.multiply(eCCSIPrivateKeyParameters.getSSK())).mod(this.q);
        if (mod.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Invalid j, retry");
        }
        return Arrays.concatenate(BigIntegers.asUnsignedByteArray(this.N, this.r), BigIntegers.asUnsignedByteArray(this.N, mod.modInverse(this.q).multiply(this.j).mod(this.q)), eCCSIPrivateKeyParameters.getPublicKeyParameters().getPVT().getEncoded(false));
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forSigning = z;
        this.param = cipherParameters;
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        SecureRandom secureRandom;
        ECPoint eCPoint;
        ECPoint eCPoint2;
        this.digest.reset();
        CipherParameters cipherParameters = this.param;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            secureRandom = parametersWithRandom.getRandom();
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            secureRandom = null;
        }
        if (this.forSigning) {
            ECCSIPrivateKeyParameters eCCSIPrivateKeyParameters = (ECCSIPrivateKeyParameters) cipherParameters;
            eCPoint = eCCSIPrivateKeyParameters.getPublicKeyParameters().getPVT();
            BigInteger createRandomBigInteger = BigIntegers.createRandomBigInteger(this.q.bitLength(), secureRandom);
            this.j = createRandomBigInteger;
            this.r = this.G.multiply(createRandomBigInteger).normalize().getAffineXCoord().toBigInteger().mod(this.q);
            eCPoint2 = this.G.multiply(eCCSIPrivateKeyParameters.getSSK());
        } else {
            ECPoint pvt = ((ECCSIPublicKeyParameters) cipherParameters).getPVT();
            this.stream = new ByteArrayOutputStream();
            eCPoint = pvt;
            eCPoint2 = null;
        }
        byte[] encoded = this.G.getEncoded(false);
        this.digest.update(encoded, 0, encoded.length);
        byte[] encoded2 = this.kpak.getEncoded(false);
        this.digest.update(encoded2, 0, encoded2.length);
        Digest digest = this.digest;
        byte[] bArr = this.id;
        digest.update(bArr, 0, bArr.length);
        byte[] encoded3 = eCPoint.getEncoded(false);
        this.digest.update(encoded3, 0, encoded3.length);
        int digestSize = this.digest.getDigestSize();
        byte[] bArr2 = new byte[digestSize];
        this.digest.doFinal(bArr2, 0);
        BigInteger mod = new BigInteger(1, bArr2).mod(this.q);
        this.digest.update(bArr2, 0, digestSize);
        if (!this.forSigning) {
            this.Y = eCPoint.multiply(mod).add(this.kpak).normalize();
        } else {
            if (!eCPoint2.subtract(eCPoint.multiply(mod)).normalize().equals(this.kpak)) {
                throw new IllegalArgumentException("Invalid KPAK");
            }
            byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.N, this.r);
            this.digest.update(asUnsignedByteArray, 0, asUnsignedByteArray.length);
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        if (this.forSigning) {
            this.digest.update(b);
        } else {
            this.stream.write(b);
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        if (this.forSigning) {
            this.digest.update(bArr, i, i2);
        } else {
            this.stream.write(bArr, i, i2);
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, this.N);
        int i = this.N;
        BigInteger bigInteger = new BigInteger(1, Arrays.copyOfRange(bArr, i, i << 1));
        this.r = new BigInteger(1, copyOf).mod(this.q);
        this.digest.update(copyOf, 0, this.N);
        byte[] byteArray = this.stream.toByteArray();
        this.digest.update(byteArray, 0, byteArray.length);
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        return this.G.multiply(new BigInteger(1, bArr2).mod(this.q)).normalize().add(this.Y.multiply(this.r).normalize()).normalize().multiply(bigInteger).normalize().getAffineXCoord().toBigInteger().mod(this.q).equals(this.r.mod(this.q));
    }
}
