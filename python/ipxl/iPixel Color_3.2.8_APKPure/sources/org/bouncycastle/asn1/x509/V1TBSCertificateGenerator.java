package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;

/* loaded from: classes3.dex */
public class V1TBSCertificateGenerator {
    Time endDate;
    X500Name issuer;
    ASN1Integer serialNumber;
    AlgorithmIdentifier signature;
    Time startDate;
    X500Name subject;
    SubjectPublicKeyInfo subjectPublicKeyInfo;
    Validity validity;
    DERTaggedObject version = new DERTaggedObject(true, 0, (ASN1Encodable) new ASN1Integer(0));

    public TBSCertificate generateTBSCertificate() {
        if (this.serialNumber == null || this.signature == null || this.issuer == null || ((this.validity == null && (this.startDate == null || this.endDate == null)) || this.subject == null || this.subjectPublicKeyInfo == null)) {
            throw new IllegalStateException("not all mandatory fields set in V1 TBScertificate generator");
        }
        ASN1Integer aSN1Integer = new ASN1Integer(0L);
        ASN1Integer aSN1Integer2 = this.serialNumber;
        AlgorithmIdentifier algorithmIdentifier = this.signature;
        X500Name x500Name = this.issuer;
        Validity validity = this.validity;
        if (validity == null) {
            validity = new Validity(this.startDate, this.endDate);
        }
        return new TBSCertificate(aSN1Integer, aSN1Integer2, algorithmIdentifier, x500Name, validity, this.subject, this.subjectPublicKeyInfo, null, null, null);
    }

    public void setEndDate(ASN1UTCTime aSN1UTCTime) {
        setEndDate(new Time(aSN1UTCTime));
    }

    public void setEndDate(Time time) {
        this.validity = null;
        this.endDate = time;
    }

    public void setIssuer(X500Name x500Name) {
        this.issuer = x500Name;
    }

    public void setIssuer(X509Name x509Name) {
        this.issuer = X500Name.getInstance(x509Name.toASN1Primitive());
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

    public void setValidity(Validity validity) {
        this.validity = validity;
        this.startDate = null;
        this.endDate = null;
    }
}
