package org.bouncycastle.jcajce.provider.asymmetric.mldsa;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.spec.MLDSAPrivateKeySpec;
import org.bouncycastle.jcajce.spec.MLDSAPublicKeySpec;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.BaseKeyFactorySpi;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class MLDSAKeyFactorySpi extends BaseKeyFactorySpi {
    private static final Set<ASN1ObjectIdentifier> hashKeyOids;
    private static final Set<ASN1ObjectIdentifier> pureKeyOids;
    private final boolean isHashOnly;

    public static class Hash extends MLDSAKeyFactorySpi {
        public Hash() {
            super((Set<ASN1ObjectIdentifier>) MLDSAKeyFactorySpi.hashKeyOids);
        }
    }

    public static class HashMLDSA44 extends MLDSAKeyFactorySpi {
        public HashMLDSA44() {
            super(NISTObjectIdentifiers.id_hash_ml_dsa_44_with_sha512);
        }
    }

    public static class HashMLDSA65 extends MLDSAKeyFactorySpi {
        public HashMLDSA65() {
            super(NISTObjectIdentifiers.id_hash_ml_dsa_65_with_sha512);
        }
    }

    public static class HashMLDSA87 extends MLDSAKeyFactorySpi {
        public HashMLDSA87() {
            super(NISTObjectIdentifiers.id_hash_ml_dsa_87_with_sha512);
        }
    }

    public static class MLDSA44 extends MLDSAKeyFactorySpi {
        public MLDSA44() {
            super(NISTObjectIdentifiers.id_ml_dsa_44);
        }
    }

    public static class MLDSA65 extends MLDSAKeyFactorySpi {
        public MLDSA65() {
            super(NISTObjectIdentifiers.id_ml_dsa_65);
        }
    }

    public static class MLDSA87 extends MLDSAKeyFactorySpi {
        public MLDSA87() {
            super(NISTObjectIdentifiers.id_ml_dsa_87);
        }
    }

    public static class Pure extends MLDSAKeyFactorySpi {
        public Pure() {
            super((Set<ASN1ObjectIdentifier>) MLDSAKeyFactorySpi.pureKeyOids);
        }
    }

    static {
        HashSet hashSet = new HashSet();
        pureKeyOids = hashSet;
        HashSet hashSet2 = new HashSet();
        hashKeyOids = hashSet2;
        hashSet.add(NISTObjectIdentifiers.id_ml_dsa_44);
        hashSet.add(NISTObjectIdentifiers.id_ml_dsa_65);
        hashSet.add(NISTObjectIdentifiers.id_ml_dsa_87);
        hashSet2.add(NISTObjectIdentifiers.id_ml_dsa_44);
        hashSet2.add(NISTObjectIdentifiers.id_ml_dsa_65);
        hashSet2.add(NISTObjectIdentifiers.id_ml_dsa_87);
        hashSet2.add(NISTObjectIdentifiers.id_hash_ml_dsa_44_with_sha512);
        hashSet2.add(NISTObjectIdentifiers.id_hash_ml_dsa_65_with_sha512);
        hashSet2.add(NISTObjectIdentifiers.id_hash_ml_dsa_87_with_sha512);
    }

    public MLDSAKeyFactorySpi(Set<ASN1ObjectIdentifier> set) {
        super(set);
        this.isHashOnly = false;
    }

    public MLDSAKeyFactorySpi(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        super(aSN1ObjectIdentifier);
        this.isHashOnly = aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_hash_ml_dsa_44_with_sha512) || aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_hash_ml_dsa_65_with_sha512) || aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_hash_ml_dsa_87_with_sha512);
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.BaseKeyFactorySpi, java.security.KeyFactorySpi
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        MLDSAPrivateKeyParameters mLDSAPrivateKeyParameters;
        if (!(keySpec instanceof MLDSAPrivateKeySpec)) {
            return super.engineGeneratePrivate(keySpec);
        }
        MLDSAPrivateKeySpec mLDSAPrivateKeySpec = (MLDSAPrivateKeySpec) keySpec;
        MLDSAParameters parameters = Utils.getParameters(mLDSAPrivateKeySpec.getParameterSpec().getName());
        if (mLDSAPrivateKeySpec.isSeed()) {
            mLDSAPrivateKeyParameters = new MLDSAPrivateKeyParameters(parameters, mLDSAPrivateKeySpec.getSeed());
        } else {
            mLDSAPrivateKeyParameters = new MLDSAPrivateKeyParameters(parameters, mLDSAPrivateKeySpec.getPrivateData(), null);
            byte[] publicData = mLDSAPrivateKeySpec.getPublicData();
            if (publicData != null && !Arrays.constantTimeAreEqual(publicData, mLDSAPrivateKeyParameters.getPublicKey())) {
                throw new InvalidKeySpecException("public key data does not match private key data");
            }
        }
        return new BCMLDSAPrivateKey(mLDSAPrivateKeyParameters);
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.BaseKeyFactorySpi, java.security.KeyFactorySpi
    public PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (!(keySpec instanceof MLDSAPublicKeySpec)) {
            return super.engineGeneratePublic(keySpec);
        }
        MLDSAPublicKeySpec mLDSAPublicKeySpec = (MLDSAPublicKeySpec) keySpec;
        return new BCMLDSAPublicKey(new MLDSAPublicKeyParameters(Utils.getParameters(mLDSAPublicKeySpec.getParameterSpec().getName()), mLDSAPublicKeySpec.getPublicData()));
    }

    @Override // java.security.KeyFactorySpi
    public final KeySpec engineGetKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        if (key instanceof BCMLDSAPrivateKey) {
            if (PKCS8EncodedKeySpec.class.isAssignableFrom(cls)) {
                return new PKCS8EncodedKeySpec(key.getEncoded());
            }
            if (MLDSAPrivateKeySpec.class.isAssignableFrom(cls)) {
                BCMLDSAPrivateKey bCMLDSAPrivateKey = (BCMLDSAPrivateKey) key;
                byte[] seed = bCMLDSAPrivateKey.getSeed();
                return seed != null ? new MLDSAPrivateKeySpec(bCMLDSAPrivateKey.getParameterSpec(), seed) : new MLDSAPrivateKeySpec(bCMLDSAPrivateKey.getParameterSpec(), bCMLDSAPrivateKey.getPrivateData(), bCMLDSAPrivateKey.getPublicKey().getPublicData());
            }
            if (MLDSAPublicKeySpec.class.isAssignableFrom(cls)) {
                BCMLDSAPrivateKey bCMLDSAPrivateKey2 = (BCMLDSAPrivateKey) key;
                return new MLDSAPublicKeySpec(bCMLDSAPrivateKey2.getParameterSpec(), bCMLDSAPrivateKey2.getPublicKey().getPublicData());
            }
        } else {
            if (!(key instanceof BCMLDSAPublicKey)) {
                throw new InvalidKeySpecException("unsupported key type: " + key.getClass() + ".");
            }
            if (X509EncodedKeySpec.class.isAssignableFrom(cls)) {
                return new X509EncodedKeySpec(key.getEncoded());
            }
            if (MLDSAPublicKeySpec.class.isAssignableFrom(cls)) {
                BCMLDSAPublicKey bCMLDSAPublicKey = (BCMLDSAPublicKey) key;
                return new MLDSAPublicKeySpec(bCMLDSAPublicKey.getParameterSpec(), bCMLDSAPublicKey.getPublicData());
            }
        }
        throw new InvalidKeySpecException("unknown key specification: " + cls + ".");
    }

    @Override // java.security.KeyFactorySpi
    public final Key engineTranslateKey(Key key) throws InvalidKeyException {
        if ((key instanceof BCMLDSAPrivateKey) || (key instanceof BCMLDSAPublicKey)) {
            return key;
        }
        throw new InvalidKeyException("unsupported key type");
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PrivateKey generatePrivate(PrivateKeyInfo privateKeyInfo) throws IOException {
        MLDSAParameters mLDSAParameters;
        BCMLDSAPrivateKey bCMLDSAPrivateKey = new BCMLDSAPrivateKey(privateKeyInfo);
        if (!this.isHashOnly || bCMLDSAPrivateKey.getAlgorithm().indexOf("WITH") > 0) {
            return bCMLDSAPrivateKey;
        }
        MLDSAPrivateKeyParameters keyParams = bCMLDSAPrivateKey.getKeyParams();
        if (keyParams.getParameters().equals(MLDSAParameters.ml_dsa_44)) {
            mLDSAParameters = MLDSAParameters.ml_dsa_44_with_sha512;
        } else if (keyParams.getParameters().equals(MLDSAParameters.ml_dsa_65)) {
            mLDSAParameters = MLDSAParameters.ml_dsa_65_with_sha512;
        } else {
            if (!keyParams.getParameters().equals(MLDSAParameters.ml_dsa_87)) {
                throw new IllegalStateException("unknown ML-DSA parameters");
            }
            mLDSAParameters = MLDSAParameters.ml_dsa_87_with_sha512;
        }
        return new BCMLDSAPrivateKey(new MLDSAPrivateKeyParameters(mLDSAParameters, keyParams.getRho(), keyParams.getK(), keyParams.getTr(), keyParams.getS1(), keyParams.getS2(), keyParams.getT0(), keyParams.getT1(), keyParams.getSeed()));
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PublicKey generatePublic(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        return new BCMLDSAPublicKey(subjectPublicKeyInfo);
    }
}
