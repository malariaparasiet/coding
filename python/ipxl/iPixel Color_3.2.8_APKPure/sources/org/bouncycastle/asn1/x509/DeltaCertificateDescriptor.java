package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;

/* loaded from: classes3.dex */
public class DeltaCertificateDescriptor extends ASN1Object {
    private final Extensions extensions;
    private final X500Name issuer;
    private final ASN1Integer serialNumber;
    private final AlgorithmIdentifier signature;
    private final ASN1BitString signatureValue;
    private final X500Name subject;
    private final SubjectPublicKeyInfo subjectPublicKeyInfo;
    private final Validity validity;

    public DeltaCertificateDescriptor(ASN1Integer aSN1Integer, AlgorithmIdentifier algorithmIdentifier, X500Name x500Name, Validity validity, X500Name x500Name2, SubjectPublicKeyInfo subjectPublicKeyInfo, Extensions extensions, ASN1BitString aSN1BitString) {
        if (aSN1Integer == null) {
            throw new NullPointerException("'serialNumber' cannot be null");
        }
        if (subjectPublicKeyInfo == null) {
            throw new NullPointerException("'subjectPublicKeyInfo' cannot be null");
        }
        if (aSN1BitString == null) {
            throw new NullPointerException("'signatureValue' cannot be null");
        }
        this.serialNumber = aSN1Integer;
        this.signature = algorithmIdentifier;
        this.issuer = x500Name;
        this.validity = validity;
        this.subject = x500Name2;
        this.subjectPublicKeyInfo = subjectPublicKeyInfo;
        this.extensions = extensions;
        this.signatureValue = aSN1BitString;
    }

    private DeltaCertificateDescriptor(ASN1Sequence aSN1Sequence) {
        ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(1);
        Extensions extensions = null;
        int i = 2;
        AlgorithmIdentifier algorithmIdentifier = null;
        X500Name x500Name = null;
        Validity validity = null;
        X500Name x500Name2 = null;
        while (objectAt instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objectAt);
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                algorithmIdentifier = AlgorithmIdentifier.getInstance(aSN1TaggedObject, true);
            } else if (tagNo == 1) {
                x500Name = X500Name.getInstance(aSN1TaggedObject, true);
            } else if (tagNo == 2) {
                validity = Validity.getInstance(aSN1TaggedObject, true);
            } else if (tagNo == 3) {
                x500Name2 = X500Name.getInstance(aSN1TaggedObject, true);
            }
            int i2 = i + 1;
            ASN1Encodable objectAt2 = aSN1Sequence.getObjectAt(i);
            i = i2;
            objectAt = objectAt2;
        }
        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(objectAt);
        ASN1Encodable objectAt3 = aSN1Sequence.getObjectAt(i);
        while (objectAt3 instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject2 = ASN1TaggedObject.getInstance(objectAt3);
            if (aSN1TaggedObject2.getTagNo() == 4) {
                extensions = Extensions.getInstance(aSN1TaggedObject2, true);
            }
            int i3 = i + 1;
            ASN1Encodable objectAt4 = aSN1Sequence.getObjectAt(i);
            i = i3;
            objectAt3 = objectAt4;
        }
        ASN1BitString aSN1BitString = ASN1BitString.getInstance(objectAt3);
        this.serialNumber = aSN1Integer;
        this.signature = algorithmIdentifier;
        this.issuer = x500Name;
        this.validity = validity;
        this.subject = x500Name2;
        this.subjectPublicKeyInfo = subjectPublicKeyInfo;
        this.extensions = extensions;
        this.signatureValue = aSN1BitString;
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, int i, boolean z, ASN1Object aSN1Object) {
        if (aSN1Object != null) {
            aSN1EncodableVector.add(new DERTaggedObject(z, i, aSN1Object));
        }
    }

    public static DeltaCertificateDescriptor fromExtensions(Extensions extensions) {
        return getInstance(Extensions.getExtensionParsedValue(extensions, Extension.deltaCertificateDescriptor));
    }

    public static DeltaCertificateDescriptor getInstance(Object obj) {
        if (obj instanceof DeltaCertificateDescriptor) {
            return (DeltaCertificateDescriptor) obj;
        }
        if (obj != null) {
            return new DeltaCertificateDescriptor(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private static DeltaCertificateDescriptor trimDeltaCertificateDescriptor(DeltaCertificateDescriptor deltaCertificateDescriptor, TBSCertificate tBSCertificate, Extensions extensions) {
        Extensions extensions2;
        Extension extension;
        ASN1Integer serialNumber = deltaCertificateDescriptor.getSerialNumber();
        AlgorithmIdentifier signature = deltaCertificateDescriptor.getSignature();
        if (signature != null && signature.equals(tBSCertificate.getSignature())) {
            signature = null;
        }
        X500Name issuer = deltaCertificateDescriptor.getIssuer();
        if (issuer != null && issuer.equals(tBSCertificate.getIssuer())) {
            issuer = null;
        }
        Validity validityObject = deltaCertificateDescriptor.getValidityObject();
        if (validityObject != null && validityObject.equals(tBSCertificate.getValidity())) {
            validityObject = null;
        }
        X500Name subject = deltaCertificateDescriptor.getSubject();
        if (subject != null && subject.equals(tBSCertificate.getSubject())) {
            subject = null;
        }
        SubjectPublicKeyInfo subjectPublicKeyInfo = deltaCertificateDescriptor.getSubjectPublicKeyInfo();
        Extensions extensions3 = deltaCertificateDescriptor.getExtensions();
        if (extensions3 != null) {
            ExtensionsGenerator extensionsGenerator = new ExtensionsGenerator();
            Enumeration oids = extensions.oids();
            while (oids.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
                if (!Extension.deltaCertificateDescriptor.equals((ASN1Primitive) aSN1ObjectIdentifier) && (extension = extensions3.getExtension(aSN1ObjectIdentifier)) != null && !extension.equals(extensions.getExtension(aSN1ObjectIdentifier))) {
                    extensionsGenerator.addExtension(extension);
                }
            }
            extensions2 = extensionsGenerator.isEmpty() ? null : extensionsGenerator.generate();
        } else {
            extensions2 = extensions3;
        }
        return new DeltaCertificateDescriptor(serialNumber, signature, issuer, validityObject, subject, subjectPublicKeyInfo, extensions2, deltaCertificateDescriptor.getSignatureValue());
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public ASN1BitString getSignatureValue() {
        return this.signatureValue;
    }

    public X500Name getSubject() {
        return this.subject;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.subjectPublicKeyInfo;
    }

    public ASN1Sequence getValidity() {
        return (ASN1Sequence) this.validity.toASN1Primitive();
    }

    public Validity getValidityObject() {
        return this.validity;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(8);
        aSN1EncodableVector.add(this.serialNumber);
        addOptional(aSN1EncodableVector, 0, true, this.signature);
        addOptional(aSN1EncodableVector, 1, true, this.issuer);
        addOptional(aSN1EncodableVector, 2, true, this.validity);
        addOptional(aSN1EncodableVector, 3, true, this.subject);
        aSN1EncodableVector.add(this.subjectPublicKeyInfo);
        addOptional(aSN1EncodableVector, 4, true, this.extensions);
        aSN1EncodableVector.add(this.signatureValue);
        return new DERSequence(aSN1EncodableVector);
    }

    public DeltaCertificateDescriptor trimTo(TBSCertificate tBSCertificate, Extensions extensions) {
        return trimDeltaCertificateDescriptor(this, tBSCertificate, extensions);
    }
}
