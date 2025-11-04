package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;

/* loaded from: classes3.dex */
public class V3TBSCertificateGenerator {
    private static final DERTaggedObject VERSION = new DERTaggedObject(true, 0, (ASN1Encodable) new ASN1Integer(2));
    private boolean altNamePresentAndCritical;
    Time endDate;
    Extensions extensions;
    X500Name issuer;
    private DERBitString issuerUniqueID;
    ASN1Integer serialNumber;
    AlgorithmIdentifier signature;
    Time startDate;
    X500Name subject;
    SubjectPublicKeyInfo subjectPublicKeyInfo;
    private DERBitString subjectUniqueID;
    Validity validity;

    public ASN1Sequence generatePreTBSCertificate() {
        if (this.signature != null) {
            throw new IllegalStateException("signature field should not be set in PreTBSCertificate");
        }
        if (this.serialNumber == null || this.issuer == null || ((this.validity == null && (this.startDate == null || this.endDate == null)) || ((this.subject == null && !this.altNamePresentAndCritical) || this.subjectPublicKeyInfo == null))) {
            throw new IllegalStateException("not all mandatory fields set in V3 TBScertificate generator");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(9);
        aSN1EncodableVector.add(VERSION);
        aSN1EncodableVector.add(this.serialNumber);
        aSN1EncodableVector.add(this.issuer);
        Validity validity = this.validity;
        if (validity == null) {
            validity = new Validity(this.startDate, this.endDate);
        }
        aSN1EncodableVector.add(validity);
        X500Name x500Name = this.subject;
        if (x500Name == null) {
            x500Name = X500Name.getInstance(new DERSequence());
        }
        aSN1EncodableVector.add(x500Name);
        aSN1EncodableVector.add(this.subjectPublicKeyInfo);
        if (this.issuerUniqueID != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) this.issuerUniqueID));
        }
        if (this.subjectUniqueID != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, (ASN1Encodable) this.subjectUniqueID));
        }
        if (this.extensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 3, (ASN1Encodable) this.extensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public TBSCertificate generateTBSCertificate() {
        if (this.serialNumber == null || this.signature == null || this.issuer == null || ((this.validity == null && (this.startDate == null || this.endDate == null)) || ((this.subject == null && !this.altNamePresentAndCritical) || this.subjectPublicKeyInfo == null))) {
            throw new IllegalStateException("not all mandatory fields set in V3 TBScertificate generator");
        }
        ASN1Integer aSN1Integer = new ASN1Integer(2L);
        ASN1Integer aSN1Integer2 = this.serialNumber;
        AlgorithmIdentifier algorithmIdentifier = this.signature;
        X500Name x500Name = this.issuer;
        Validity validity = this.validity;
        if (validity == null) {
            validity = new Validity(this.startDate, this.endDate);
        }
        Validity validity2 = validity;
        X500Name x500Name2 = this.subject;
        if (x500Name2 == null) {
            x500Name2 = X500Name.getInstance(new DERSequence());
        }
        return new TBSCertificate(aSN1Integer, aSN1Integer2, algorithmIdentifier, x500Name, validity2, x500Name2, this.subjectPublicKeyInfo, this.issuerUniqueID, this.subjectUniqueID, this.extensions);
    }

    public void setEndDate(ASN1UTCTime aSN1UTCTime) {
        setEndDate(new Time(aSN1UTCTime));
    }

    public void setEndDate(Time time) {
        this.validity = null;
        this.endDate = time;
    }

    public void setExtensions(Extensions extensions) {
        Extension extension;
        this.extensions = extensions;
        if (extensions == null || (extension = extensions.getExtension(Extension.subjectAlternativeName)) == null || !extension.isCritical()) {
            return;
        }
        this.altNamePresentAndCritical = true;
    }

    public void setExtensions(X509Extensions x509Extensions) {
        setExtensions(Extensions.getInstance(x509Extensions));
    }

    public void setIssuer(X500Name x500Name) {
        this.issuer = x500Name;
    }

    public void setIssuer(X509Name x509Name) {
        this.issuer = X500Name.getInstance(x509Name);
    }

    public void setIssuerUniqueID(DERBitString dERBitString) {
        this.issuerUniqueID = dERBitString;
    }

    public void setSerialNumber(ASN1Integer aSN1Integer) {
        this.serialNumber = aSN1Integer;
    }

    public void setSignature(AlgorithmIdentifier algorithmIdentifier) {
        this.signature = algorithmIdentifier;
    }

    public void setStartDate(ASN1UTCTime aSN1UTCTime) {
        setStartDate(new Time(aSN1UTCTime));
    }

    public void setStartDate(Time time) {
        this.validity = null;
        this.startDate = time;
    }

    public void setSubject(X500Name x500Name) {
        this.subject = x500Name;
    }

    public void setSubject(X509Name x509Name) {
        this.subject = X500Name.getInstance(x509Name.toASN1Primitive());
    }

    public void setSubjectPublicKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.subjectPublicKeyInfo = subjectPublicKeyInfo;
    }

    public void setSubjectUniqueID(DERBitString dERBitString) {
        this.subjectUniqueID = dERBitString;
    }

    public void setValidity(Validity validity) {
        this.validity = validity;
        this.startDate = null;
        this.endDate = null;
    }
}
