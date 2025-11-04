package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.IssuerAndSerialNumber;

/* loaded from: classes3.dex */
public class PrivateKeyStatement extends ASN1Object {
    private final Certificate cert;
    private final IssuerAndSerialNumber signer;

    private PrivateKeyStatement(ASN1Sequence aSN1Sequence) {
        Certificate certificate;
        if (aSN1Sequence.size() == 1) {
            this.signer = IssuerAndSerialNumber.getInstance(aSN1Sequence.getObjectAt(0));
            certificate = null;
        } else {
            if (aSN1Sequence.size() != 2) {
                throw new IllegalArgumentException("unknown sequence in PrivateKeyStatement");
            }
            this.signer = IssuerAndSerialNumber.getInstance(aSN1Sequence.getObjectAt(0));
            certificate = Certificate.getInstance(aSN1Sequence.getObjectAt(1));
        }
        this.cert = certificate;
    }

    public PrivateKeyStatement(IssuerAndSerialNumber issuerAndSerialNumber) {
        this.signer = issuerAndSerialNumber;
        this.cert = null;
    }

    public PrivateKeyStatement(Certificate certificate) {
        this.signer = new IssuerAndSerialNumber(certificate.getIssuer(), certificate.getSerialNumber().getValue());
        this.cert = certificate;
    }

    public static PrivateKeyStatement getInstance(Object obj) {
        if (obj instanceof PrivateKeyStatement) {
            return (PrivateKeyStatement) obj;
        }
        if (obj != null) {
            return new PrivateKeyStatement(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public Certificate getCert() {
        return this.cert;
    }

    public IssuerAndSerialNumber getSigner() {
        return this.signer;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.signer);
        Certificate certificate = this.cert;
        if (certificate != null) {
            aSN1EncodableVector.add(certificate);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
