package org.bouncycastle.crypto.hpke;

import com.alibaba.fastjson2.internal.asm.Opcodes;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.RawAgreement;
import org.bouncycastle.crypto.agreement.BasicRawAgreement;
import org.bouncycastle.crypto.agreement.ECDHCBasicAgreement;
import org.bouncycastle.crypto.agreement.X25519Agreement;
import org.bouncycastle.crypto.agreement.X448Agreement;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.generators.X448KeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.crypto.params.X448KeyGenerationParameters;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PublicKeyParameters;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.math.ec.rfc7748.X25519;
import org.bouncycastle.math.ec.rfc7748.X448;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;

/* loaded from: classes3.dex */
class DHKEM extends KEM {
    private int Nenc;
    private int Nsecret;
    private int Nsk;
    private byte bitmask;
    ECDomainParameters domainParams;
    private HKDF hkdf;
    private final short kemId;
    private AsymmetricCipherKeyPairGenerator kpGen;
    private RawAgreement rawAgreement;

    protected DHKEM(short s) {
        AsymmetricCipherKeyPairGenerator x25519KeyPairGenerator;
        KeyGenerationParameters x25519KeyGenerationParameters;
        this.kemId = s;
        if (s == 32) {
            this.hkdf = new HKDF((short) 1);
            this.rawAgreement = new X25519Agreement();
            this.Nsecret = 32;
            this.Nsk = 32;
            this.Nenc = 32;
            x25519KeyPairGenerator = new X25519KeyPairGenerator();
            this.kpGen = x25519KeyPairGenerator;
            x25519KeyGenerationParameters = new X25519KeyGenerationParameters(getSecureRandom());
        } else if (s != 33) {
            switch (s) {
                case 16:
                    this.hkdf = new HKDF((short) 1);
                    this.domainParams = getDomainParameters("P-256");
                    this.rawAgreement = new BasicRawAgreement(new ECDHCBasicAgreement());
                    this.bitmask = (byte) -1;
                    this.Nsk = 32;
                    this.Nsecret = 32;
                    this.Nenc = 65;
                    x25519KeyPairGenerator = new ECKeyPairGenerator();
                    this.kpGen = x25519KeyPairGenerator;
                    x25519KeyGenerationParameters = new ECKeyGenerationParameters(this.domainParams, getSecureRandom());
                    break;
                case 17:
                    this.hkdf = new HKDF((short) 2);
                    this.domainParams = getDomainParameters("P-384");
                    this.rawAgreement = new BasicRawAgreement(new ECDHCBasicAgreement());
                    this.bitmask = (byte) -1;
                    this.Nsk = 48;
                    this.Nsecret = 48;
                    this.Nenc = 97;
                    x25519KeyPairGenerator = new ECKeyPairGenerator();
                    this.kpGen = x25519KeyPairGenerator;
                    x25519KeyGenerationParameters = new ECKeyGenerationParameters(this.domainParams, getSecureRandom());
                    break;
                case 18:
                    this.hkdf = new HKDF((short) 3);
                    this.domainParams = getDomainParameters("P-521");
                    this.rawAgreement = new BasicRawAgreement(new ECDHCBasicAgreement());
                    this.bitmask = (byte) 1;
                    this.Nsk = 66;
                    this.Nsecret = 64;
                    this.Nenc = Opcodes.I2L;
                    x25519KeyPairGenerator = new ECKeyPairGenerator();
                    this.kpGen = x25519KeyPairGenerator;
                    x25519KeyGenerationParameters = new ECKeyGenerationParameters(this.domainParams, getSecureRandom());
                    break;
                default:
                    throw new IllegalArgumentException("invalid kem id");
            }
        } else {
            this.hkdf = new HKDF((short) 3);
            this.rawAgreement = new X448Agreement();
            this.Nsecret = 64;
            this.Nsk = 56;
            this.Nenc = 56;
            x25519KeyPairGenerator = new X448KeyPairGenerator();
            this.kpGen = x25519KeyPairGenerator;
            x25519KeyGenerationParameters = new X448KeyGenerationParameters(getSecureRandom());
        }
        x25519KeyPairGenerator.init(x25519KeyGenerationParameters);
    }

    private byte[] ExtractAndExpand(byte[] bArr, byte[] bArr2) {
        byte[] concatenate = Arrays.concatenate(Strings.toByteArray("KEM"), Pack.shortToBigEndian(this.kemId));
        return this.hkdf.LabeledExpand(this.hkdf.LabeledExtract(null, concatenate, "eae_prk", bArr), concatenate, "shared_secret", bArr2, this.Nsecret);
    }

    private static byte[] calculateRawAgreement(RawAgreement rawAgreement, AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricKeyParameter asymmetricKeyParameter2) {
        rawAgreement.init(asymmetricKeyParameter);
        byte[] bArr = new byte[rawAgreement.getAgreementSize()];
        rawAgreement.calculateAgreement(asymmetricKeyParameter2, bArr, 0);
        return bArr;
    }

    private static ECDomainParameters getDomainParameters(String str) {
        return new ECDomainParameters(CustomNamedCurves.getByName(str));
    }

    private static SecureRandom getSecureRandom() {
        return CryptoServicesRegistrar.getSecureRandom();
    }

    private boolean validateSk(BigInteger bigInteger) {
        BigInteger n = this.domainParams.getN();
        return bigInteger.compareTo(BigInteger.valueOf(1L)) >= 0 && bigInteger.compareTo(n) < 0 && WNafUtil.getNafWeight(bigInteger) >= (n.bitLength() >>> 2);
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    protected byte[] AuthDecap(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair, AsymmetricKeyParameter asymmetricKeyParameter) {
        AsymmetricKeyParameter DeserializePublicKey = DeserializePublicKey(bArr);
        this.rawAgreement.init(asymmetricCipherKeyPair.getPrivate());
        int agreementSize = this.rawAgreement.getAgreementSize();
        byte[] bArr2 = new byte[agreementSize * 2];
        this.rawAgreement.calculateAgreement(DeserializePublicKey, bArr2, 0);
        this.rawAgreement.calculateAgreement(asymmetricKeyParameter, bArr2, agreementSize);
        return ExtractAndExpand(bArr2, Arrays.concatenate(bArr, SerializePublicKey(asymmetricCipherKeyPair.getPublic()), SerializePublicKey(asymmetricKeyParameter)));
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    protected byte[][] AuthEncap(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        AsymmetricCipherKeyPair generateKeyPair = this.kpGen.generateKeyPair();
        this.rawAgreement.init(generateKeyPair.getPrivate());
        int agreementSize = this.rawAgreement.getAgreementSize();
        byte[] bArr = new byte[agreementSize * 2];
        this.rawAgreement.calculateAgreement(asymmetricKeyParameter, bArr, 0);
        this.rawAgreement.init(asymmetricCipherKeyPair.getPrivate());
        if (agreementSize != this.rawAgreement.getAgreementSize()) {
            throw new IllegalStateException();
        }
        this.rawAgreement.calculateAgreement(asymmetricKeyParameter, bArr, agreementSize);
        byte[] SerializePublicKey = SerializePublicKey(generateKeyPair.getPublic());
        return new byte[][]{ExtractAndExpand(bArr, Arrays.concatenate(SerializePublicKey, SerializePublicKey(asymmetricKeyParameter), SerializePublicKey(asymmetricCipherKeyPair.getPublic()))), SerializePublicKey};
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    protected byte[] Decap(byte[] bArr, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        return ExtractAndExpand(calculateRawAgreement(this.rawAgreement, asymmetricCipherKeyPair.getPrivate(), DeserializePublicKey(bArr)), Arrays.concatenate(bArr, SerializePublicKey(asymmetricCipherKeyPair.getPublic())));
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    public AsymmetricCipherKeyPair DeriveKeyPair(byte[] bArr) {
        byte[] concatenate = Arrays.concatenate(Strings.toByteArray("KEM"), Pack.shortToBigEndian(this.kemId));
        short s = this.kemId;
        if (s == 32) {
            X25519PrivateKeyParameters x25519PrivateKeyParameters = new X25519PrivateKeyParameters(this.hkdf.LabeledExpand(this.hkdf.LabeledExtract(null, concatenate, "dkp_prk", bArr), concatenate, "sk", null, this.Nsk));
            return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) x25519PrivateKeyParameters.generatePublicKey(), (AsymmetricKeyParameter) x25519PrivateKeyParameters);
        }
        if (s == 33) {
            X448PrivateKeyParameters x448PrivateKeyParameters = new X448PrivateKeyParameters(this.hkdf.LabeledExpand(this.hkdf.LabeledExtract(null, concatenate, "dkp_prk", bArr), concatenate, "sk", null, this.Nsk));
            return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) x448PrivateKeyParameters.generatePublicKey(), (AsymmetricKeyParameter) x448PrivateKeyParameters);
        }
        switch (s) {
            case 16:
            case 17:
            case 18:
                byte[] LabeledExtract = this.hkdf.LabeledExtract(null, concatenate, "dkp_prk", bArr);
                for (int i = 0; i < 256; i++) {
                    byte[] LabeledExpand = this.hkdf.LabeledExpand(LabeledExtract, concatenate, "candidate", new byte[]{(byte) i}, this.Nsk);
                    LabeledExpand[0] = (byte) (LabeledExpand[0] & this.bitmask);
                    BigInteger bigInteger = new BigInteger(1, LabeledExpand);
                    if (validateSk(bigInteger)) {
                        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new ECPublicKeyParameters(new FixedPointCombMultiplier().multiply(this.domainParams.getG(), bigInteger), this.domainParams), (AsymmetricKeyParameter) new ECPrivateKeyParameters(bigInteger, this.domainParams));
                    }
                }
                throw new IllegalStateException("DeriveKeyPairError");
            default:
                throw new IllegalStateException("invalid kem id");
        }
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    public AsymmetricCipherKeyPair DeserializePrivateKey(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            throw new NullPointerException("'skEncoded' cannot be null");
        }
        if (bArr.length != this.Nsk) {
            throw new IllegalArgumentException("'skEncoded' has invalid length");
        }
        AsymmetricKeyParameter DeserializePublicKey = bArr2 != null ? DeserializePublicKey(bArr2) : null;
        short s = this.kemId;
        if (s == 32) {
            X25519PrivateKeyParameters x25519PrivateKeyParameters = new X25519PrivateKeyParameters(bArr);
            if (DeserializePublicKey == null) {
                DeserializePublicKey = x25519PrivateKeyParameters.generatePublicKey();
            }
            return new AsymmetricCipherKeyPair(DeserializePublicKey, (AsymmetricKeyParameter) x25519PrivateKeyParameters);
        }
        if (s == 33) {
            X448PrivateKeyParameters x448PrivateKeyParameters = new X448PrivateKeyParameters(bArr);
            if (DeserializePublicKey == null) {
                DeserializePublicKey = x448PrivateKeyParameters.generatePublicKey();
            }
            return new AsymmetricCipherKeyPair(DeserializePublicKey, (AsymmetricKeyParameter) x448PrivateKeyParameters);
        }
        switch (s) {
            case 16:
            case 17:
            case 18:
                ECPrivateKeyParameters eCPrivateKeyParameters = new ECPrivateKeyParameters(new BigInteger(1, bArr), this.domainParams);
                if (DeserializePublicKey == null) {
                    DeserializePublicKey = new ECPublicKeyParameters(new FixedPointCombMultiplier().multiply(this.domainParams.getG(), eCPrivateKeyParameters.getD()), this.domainParams);
                }
                return new AsymmetricCipherKeyPair(DeserializePublicKey, (AsymmetricKeyParameter) eCPrivateKeyParameters);
            default:
                throw new IllegalStateException("invalid kem id");
        }
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    public AsymmetricKeyParameter DeserializePublicKey(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("'pkEncoded' cannot be null");
        }
        if (bArr.length != this.Nenc) {
            throw new IllegalArgumentException("'pkEncoded' has invalid length");
        }
        short s = this.kemId;
        if (s == 32) {
            return new X25519PublicKeyParameters(bArr);
        }
        if (s == 33) {
            return new X448PublicKeyParameters(bArr);
        }
        switch (s) {
            case 16:
            case 17:
            case 18:
                if (bArr[0] == 4) {
                    return new ECPublicKeyParameters(this.domainParams.getCurve().decodePoint(bArr), this.domainParams);
                }
                throw new IllegalArgumentException("'pkEncoded' has invalid format");
            default:
                throw new IllegalStateException("invalid kem id");
        }
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    protected byte[][] Encap(AsymmetricKeyParameter asymmetricKeyParameter) {
        return Encap(asymmetricKeyParameter, this.kpGen.generateKeyPair());
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    protected byte[][] Encap(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricCipherKeyPair asymmetricCipherKeyPair) {
        byte[] calculateRawAgreement = calculateRawAgreement(this.rawAgreement, asymmetricCipherKeyPair.getPrivate(), asymmetricKeyParameter);
        byte[] SerializePublicKey = SerializePublicKey(asymmetricCipherKeyPair.getPublic());
        return new byte[][]{ExtractAndExpand(calculateRawAgreement, Arrays.concatenate(SerializePublicKey, SerializePublicKey(asymmetricKeyParameter))), SerializePublicKey};
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    public AsymmetricCipherKeyPair GeneratePrivateKey() {
        return this.kpGen.generateKeyPair();
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    public byte[] SerializePrivateKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        short s = this.kemId;
        if (s == 32) {
            byte[] encoded = ((X25519PrivateKeyParameters) asymmetricKeyParameter).getEncoded();
            X25519.clampPrivateKey(encoded);
            return encoded;
        }
        if (s != 33) {
            switch (s) {
                case 16:
                case 17:
                case 18:
                    return BigIntegers.asUnsignedByteArray(this.Nsk, ((ECPrivateKeyParameters) asymmetricKeyParameter).getD());
                default:
                    throw new IllegalStateException("invalid kem id");
            }
        }
        byte[] encoded2 = ((X448PrivateKeyParameters) asymmetricKeyParameter).getEncoded();
        X448.clampPrivateKey(encoded2);
        return encoded2;
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    public byte[] SerializePublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        short s = this.kemId;
        if (s == 32) {
            return ((X25519PublicKeyParameters) asymmetricKeyParameter).getEncoded();
        }
        if (s == 33) {
            return ((X448PublicKeyParameters) asymmetricKeyParameter).getEncoded();
        }
        switch (s) {
            case 16:
            case 17:
            case 18:
                return ((ECPublicKeyParameters) asymmetricKeyParameter).getQ().getEncoded(false);
            default:
                throw new IllegalStateException("invalid kem id");
        }
    }

    @Override // org.bouncycastle.crypto.hpke.KEM
    int getEncryptionSize() {
        return this.Nenc;
    }
}
