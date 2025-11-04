package org.bouncycastle.jcajce.provider.asymmetric.compositesignatures;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.internal.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.internal.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.jcajce.CompositePrivateKey;
import org.bouncycastle.jcajce.CompositePublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Exceptions;

/* loaded from: classes4.dex */
public class KeyFactorySpi extends BaseKeyFactorySpi implements AsymmetricKeyInfoConverter {
    private static Map<ASN1ObjectIdentifier, int[]> componentKeySizes;
    private static final AlgorithmIdentifier ecDsaBrainpoolP256r1;
    private static final AlgorithmIdentifier ecDsaBrainpoolP384r1;
    private static final AlgorithmIdentifier ecDsaP256;
    private static final AlgorithmIdentifier ecDsaP384;
    private static final AlgorithmIdentifier ed25519;
    private static final AlgorithmIdentifier ed448;
    private static final AlgorithmIdentifier falcon512Identifier;
    private static final AlgorithmIdentifier mlDsa44;
    private static final AlgorithmIdentifier mlDsa65;
    private static final AlgorithmIdentifier mlDsa87;
    private static Map<ASN1ObjectIdentifier, AlgorithmIdentifier[]> pairings;
    private static final AlgorithmIdentifier rsa;
    private JcaJceHelper helper;

    static {
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(NISTObjectIdentifiers.id_ml_dsa_44);
        mlDsa44 = algorithmIdentifier;
        AlgorithmIdentifier algorithmIdentifier2 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_ml_dsa_65);
        mlDsa65 = algorithmIdentifier2;
        AlgorithmIdentifier algorithmIdentifier3 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_ml_dsa_87);
        mlDsa87 = algorithmIdentifier3;
        falcon512Identifier = new AlgorithmIdentifier(BCObjectIdentifiers.falcon_512);
        AlgorithmIdentifier algorithmIdentifier4 = new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519);
        ed25519 = algorithmIdentifier4;
        AlgorithmIdentifier algorithmIdentifier5 = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(SECObjectIdentifiers.secp256r1));
        ecDsaP256 = algorithmIdentifier5;
        AlgorithmIdentifier algorithmIdentifier6 = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(TeleTrusTObjectIdentifiers.brainpoolP256r1));
        ecDsaBrainpoolP256r1 = algorithmIdentifier6;
        AlgorithmIdentifier algorithmIdentifier7 = new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption);
        rsa = algorithmIdentifier7;
        AlgorithmIdentifier algorithmIdentifier8 = new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed448);
        ed448 = algorithmIdentifier8;
        AlgorithmIdentifier algorithmIdentifier9 = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(SECObjectIdentifiers.secp384r1));
        ecDsaP384 = algorithmIdentifier9;
        AlgorithmIdentifier algorithmIdentifier10 = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(TeleTrusTObjectIdentifiers.brainpoolP384r1));
        ecDsaBrainpoolP384r1 = algorithmIdentifier10;
        pairings = new HashMap();
        componentKeySizes = new HashMap();
        pairings.put(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PSS_SHA256, new AlgorithmIdentifier[]{algorithmIdentifier, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PKCS15_SHA256, new AlgorithmIdentifier[]{algorithmIdentifier, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_MLDSA44_Ed25519_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier, algorithmIdentifier4});
        pairings.put(MiscObjectIdentifiers.id_MLDSA44_ECDSA_P256_SHA256, new AlgorithmIdentifier[]{algorithmIdentifier, algorithmIdentifier5});
        pairings.put(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PSS_SHA256, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PKCS15_SHA256, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PSS_SHA384, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PKCS15_SHA384, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_MLDSA65_ECDSA_P384_SHA384, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier9});
        pairings.put(MiscObjectIdentifiers.id_MLDSA65_ECDSA_brainpoolP256r1_SHA256, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier6});
        pairings.put(MiscObjectIdentifiers.id_MLDSA65_Ed25519_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier4});
        pairings.put(MiscObjectIdentifiers.id_MLDSA87_ECDSA_P384_SHA384, new AlgorithmIdentifier[]{algorithmIdentifier3, algorithmIdentifier9});
        pairings.put(MiscObjectIdentifiers.id_MLDSA87_ECDSA_brainpoolP384r1_SHA384, new AlgorithmIdentifier[]{algorithmIdentifier3, algorithmIdentifier10});
        pairings.put(MiscObjectIdentifiers.id_MLDSA87_Ed448_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier3, algorithmIdentifier8});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PSS_SHA256, new AlgorithmIdentifier[]{algorithmIdentifier, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PKCS15_SHA256, new AlgorithmIdentifier[]{algorithmIdentifier, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA44_Ed25519_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier, algorithmIdentifier4});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA44_ECDSA_P256_SHA256, new AlgorithmIdentifier[]{algorithmIdentifier, algorithmIdentifier5});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PSS_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PKCS15_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PSS_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PKCS15_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier7});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_P384_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier9});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_brainpoolP256r1_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier6});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_Ed25519_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier2, algorithmIdentifier4});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_P384_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier3, algorithmIdentifier9});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_brainpoolP384r1_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier3, algorithmIdentifier10});
        pairings.put(MiscObjectIdentifiers.id_HashMLDSA87_Ed448_SHA512, new AlgorithmIdentifier[]{algorithmIdentifier3, algorithmIdentifier8});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PSS_SHA256, new int[]{1328, 268});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PKCS15_SHA256, new int[]{1312, 284});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA44_Ed25519_SHA512, new int[]{1312, 32});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA44_ECDSA_P256_SHA256, new int[]{1312, 76});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PSS_SHA256, new int[]{1952, 256});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PKCS15_SHA256, new int[]{1952, 256});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PSS_SHA384, new int[]{1952, 542});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PKCS15_SHA384, new int[]{1952, 542});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_ECDSA_P384_SHA384, new int[]{1952, 87});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_ECDSA_brainpoolP256r1_SHA256, new int[]{1952, 76});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_Ed25519_SHA512, new int[]{1952, 32});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA87_ECDSA_P384_SHA384, new int[]{2592, 87});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA87_ECDSA_brainpoolP384r1_SHA384, new int[]{2592, 87});
        componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA87_Ed448_SHA512, new int[]{2592, 57});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PSS_SHA256, new int[]{1328, 268});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PKCS15_SHA256, new int[]{1312, 284});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA44_Ed25519_SHA512, new int[]{1312, 32});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA44_ECDSA_P256_SHA256, new int[]{1312, 76});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PSS_SHA512, new int[]{1952, 256});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PKCS15_SHA512, new int[]{1952, 256});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PSS_SHA512, new int[]{1952, 542});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PKCS15_SHA512, new int[]{1952, 542});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_P384_SHA512, new int[]{1952, 87});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_brainpoolP256r1_SHA512, new int[]{1952, 76});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_Ed25519_SHA512, new int[]{1952, 32});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_P384_SHA512, new int[]{2592, 87});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_brainpoolP384r1_SHA512, new int[]{2592, 87});
        componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA87_Ed448_SHA512, new int[]{2592, 57});
    }

    public KeyFactorySpi() {
        this(null);
    }

    public KeyFactorySpi(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    private List<KeyFactory> getKeyFactoriesFromIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws NoSuchAlgorithmException, NoSuchProviderException {
        ArrayList arrayList = new ArrayList();
        new ArrayList();
        String[] pairing = CompositeIndex.getPairing(aSN1ObjectIdentifier);
        if (pairing == null) {
            throw new NoSuchAlgorithmException("Cannot create KeyFactories. Unsupported algorithm identifier.");
        }
        arrayList.add(this.helper.createKeyFactory(CompositeIndex.getBaseName(pairing[0])));
        arrayList.add(this.helper.createKeyFactory(CompositeIndex.getBaseName(pairing[1])));
        return Collections.unmodifiableList(arrayList);
    }

    private X509EncodedKeySpec[] getKeysSpecs(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1BitString[] aSN1BitStringArr) throws IOException {
        X509EncodedKeySpec[] x509EncodedKeySpecArr = new X509EncodedKeySpec[aSN1BitStringArr.length];
        SubjectPublicKeyInfo[] subjectPublicKeyInfoArr = new SubjectPublicKeyInfo[aSN1BitStringArr.length];
        AlgorithmIdentifier[] algorithmIdentifierArr = pairings.get(aSN1ObjectIdentifier);
        if (algorithmIdentifierArr == null) {
            throw new IOException("Cannot create key specs. Unsupported algorithm identifier.");
        }
        subjectPublicKeyInfoArr[0] = new SubjectPublicKeyInfo(algorithmIdentifierArr[0], aSN1BitStringArr[0]);
        subjectPublicKeyInfoArr[1] = new SubjectPublicKeyInfo(algorithmIdentifierArr[1], aSN1BitStringArr[1]);
        x509EncodedKeySpecArr[0] = new X509EncodedKeySpec(subjectPublicKeyInfoArr[0].getEncoded());
        x509EncodedKeySpecArr[1] = new X509EncodedKeySpec(subjectPublicKeyInfoArr[1].getEncoded());
        return x509EncodedKeySpecArr;
    }

    @Override // java.security.KeyFactorySpi
    protected Key engineTranslateKey(Key key) throws InvalidKeyException {
        if (this.helper == null) {
            this.helper = new BCJcaJceHelper();
        }
        try {
            if (key instanceof PrivateKey) {
                return generatePrivate(PrivateKeyInfo.getInstance(key.getEncoded()));
            }
            if (key instanceof PublicKey) {
                return generatePublic(SubjectPublicKeyInfo.getInstance(key.getEncoded()));
            }
            throw new InvalidKeyException("Key not recognized");
        } catch (IOException e) {
            throw new InvalidKeyException("Key could not be parsed: " + e.getMessage());
        }
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PrivateKey generatePrivate(PrivateKeyInfo privateKeyInfo) throws IOException {
        ASN1Sequence dERSequence;
        if (this.helper == null) {
            this.helper = new BCJcaJceHelper();
        }
        ASN1ObjectIdentifier algorithm = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        int i = 0;
        if (MiscObjectIdentifiers.id_alg_composite.equals((ASN1Primitive) algorithm) || MiscObjectIdentifiers.id_composite_key.equals((ASN1Primitive) algorithm)) {
            ASN1Sequence dERSequence2 = DERSequence.getInstance(privateKeyInfo.parsePrivateKey());
            PrivateKey[] privateKeyArr = new PrivateKey[dERSequence2.size()];
            while (i != dERSequence2.size()) {
                PrivateKeyInfo privateKeyInfo2 = PrivateKeyInfo.getInstance(ASN1Sequence.getInstance(dERSequence2.getObjectAt(i)));
                try {
                    privateKeyArr[i] = this.helper.createKeyFactory(privateKeyInfo2.getPrivateKeyAlgorithm().getAlgorithm().getId()).generatePrivate(new PKCS8EncodedKeySpec(privateKeyInfo2.getEncoded()));
                    i++;
                } catch (Exception e) {
                    throw new IOException("cannot decode generic composite: " + e.getMessage(), e);
                }
            }
            return new CompositePrivateKey(privateKeyArr);
        }
        try {
            try {
                Object parsePrivateKey = privateKeyInfo.parsePrivateKey();
                if (parsePrivateKey instanceof ASN1OctetString) {
                    parsePrivateKey = ASN1OctetString.getInstance(parsePrivateKey).getOctets();
                }
                dERSequence = DERSequence.getInstance(parsePrivateKey);
            } catch (Exception unused) {
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                byte[] octets = privateKeyInfo.getPrivateKey().getOctets();
                aSN1EncodableVector.add(new DEROctetString(Arrays.copyOfRange(octets, 0, 32)));
                aSN1EncodableVector.add(new DEROctetString(Arrays.copyOfRange(octets, 32, octets.length)));
                dERSequence = new DERSequence(aSN1EncodableVector);
            }
            List<KeyFactory> keyFactoriesFromIdentifier = getKeyFactoriesFromIdentifier(algorithm);
            PrivateKey[] privateKeyArr2 = new PrivateKey[dERSequence.size()];
            AlgorithmIdentifier[] algorithmIdentifierArr = pairings.get(algorithm);
            while (i < dERSequence.size()) {
                if (dERSequence.getObjectAt(i) instanceof ASN1OctetString) {
                    ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(3);
                    aSN1EncodableVector2.add(privateKeyInfo.getVersion());
                    aSN1EncodableVector2.add(algorithmIdentifierArr[i]);
                    aSN1EncodableVector2.add(dERSequence.getObjectAt(i));
                    privateKeyArr2[i] = keyFactoriesFromIdentifier.get(i).generatePrivate(new PKCS8EncodedKeySpec(PrivateKeyInfo.getInstance(new DERSequence(aSN1EncodableVector2)).getEncoded()));
                } else {
                    privateKeyArr2[i] = keyFactoriesFromIdentifier.get(i).generatePrivate(new PKCS8EncodedKeySpec(PrivateKeyInfo.getInstance(ASN1Sequence.getInstance(dERSequence.getObjectAt(i))).getEncoded()));
                }
                i++;
            }
            return new CompositePrivateKey(algorithm, privateKeyArr2);
        } catch (GeneralSecurityException e2) {
            throw Exceptions.ioException(e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PublicKey generatePublic(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        ASN1Sequence aSN1Sequence;
        if (this.helper == null) {
            this.helper = new BCJcaJceHelper();
        }
        ASN1ObjectIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm().getAlgorithm();
        byte[][] bArr = new byte[2][];
        try {
            aSN1Sequence = DERSequence.getInstance(subjectPublicKeyInfo.getPublicKeyData().getBytes());
        } catch (Exception unused) {
            bArr = split(algorithm, subjectPublicKeyInfo.getPublicKeyData());
            aSN1Sequence = null;
        }
        int i = 0;
        if (MiscObjectIdentifiers.id_alg_composite.equals((ASN1Primitive) algorithm) || MiscObjectIdentifiers.id_composite_key.equals((ASN1Primitive) algorithm)) {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(subjectPublicKeyInfo.getPublicKeyData().getBytes());
            PublicKey[] publicKeyArr = new PublicKey[aSN1Sequence2.size()];
            while (i != aSN1Sequence2.size()) {
                SubjectPublicKeyInfo subjectPublicKeyInfo2 = SubjectPublicKeyInfo.getInstance(aSN1Sequence2.getObjectAt(i));
                try {
                    publicKeyArr[i] = this.helper.createKeyFactory(subjectPublicKeyInfo2.getAlgorithm().getAlgorithm().getId()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo2.getEncoded()));
                    i++;
                } catch (Exception e) {
                    throw new IOException("cannot decode generic composite: " + e.getMessage(), e);
                }
            }
            return new CompositePublicKey(publicKeyArr);
        }
        try {
            int length = aSN1Sequence == null ? bArr.length : aSN1Sequence.size();
            List<KeyFactory> keyFactoriesFromIdentifier = getKeyFactoriesFromIdentifier(algorithm);
            ASN1BitString[] aSN1BitStringArr = new ASN1BitString[length];
            for (int i2 = 0; i2 < length; i2++) {
                if (aSN1Sequence == null) {
                    aSN1BitStringArr[i2] = new DERBitString(bArr[i2]);
                } else if (aSN1Sequence.getObjectAt(i2) instanceof DEROctetString) {
                    aSN1BitStringArr[i2] = new DERBitString(((DEROctetString) aSN1Sequence.getObjectAt(i2)).getOctets());
                } else {
                    aSN1BitStringArr[i2] = (DERBitString) aSN1Sequence.getObjectAt(i2);
                }
            }
            X509EncodedKeySpec[] keysSpecs = getKeysSpecs(algorithm, aSN1BitStringArr);
            PublicKey[] publicKeyArr2 = new PublicKey[length];
            while (i < length) {
                publicKeyArr2[i] = keyFactoriesFromIdentifier.get(i).generatePublic(keysSpecs[i]);
                i++;
            }
            return new CompositePublicKey(algorithm, publicKeyArr2);
        } catch (GeneralSecurityException e2) {
            throw Exceptions.ioException(e2.getMessage(), e2);
        }
    }

    byte[][] split(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1BitString aSN1BitString) {
        int[] iArr = componentKeySizes.get(aSN1ObjectIdentifier);
        aSN1BitString.getOctets();
        return new byte[][]{new byte[iArr[0]], new byte[iArr[1]]};
    }
}
