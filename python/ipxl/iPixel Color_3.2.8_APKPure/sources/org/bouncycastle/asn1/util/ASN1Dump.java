package org.bouncycastle.asn1.util;

import org.bouncycastle.asn1.ASN1BMPString;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1External;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1GraphicString;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1NumericString;
import org.bouncycastle.asn1.ASN1ObjectDescriptor;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1PrintableString;
import org.bouncycastle.asn1.ASN1RelativeOID;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1T61String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.ASN1UTF8String;
import org.bouncycastle.asn1.ASN1Util;
import org.bouncycastle.asn1.ASN1VideotexString;
import org.bouncycastle.asn1.ASN1VisibleString;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DLBitString;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class ASN1Dump {
    private static final int SAMPLE_SIZE = 32;
    private static final String TAB = "    ";

    static void _dumpAsString(String str, boolean z, ASN1Primitive aSN1Primitive, StringBuffer stringBuffer) {
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append(str);
        if (aSN1Primitive instanceof ASN1Null) {
            stringBuffer.append("NULL");
            stringBuffer.append(lineSeparator);
            return;
        }
        int i = 0;
        if (aSN1Primitive instanceof ASN1Sequence) {
            stringBuffer.append(aSN1Primitive instanceof BERSequence ? "BER Sequence" : aSN1Primitive instanceof DERSequence ? "DER Sequence" : "Sequence");
            stringBuffer.append(lineSeparator);
            ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1Primitive;
            String str2 = str + TAB;
            int size = aSN1Sequence.size();
            while (i < size) {
                _dumpAsString(str2, z, aSN1Sequence.getObjectAt(i).toASN1Primitive(), stringBuffer);
                i++;
            }
            return;
        }
        if (aSN1Primitive instanceof ASN1Set) {
            stringBuffer.append(aSN1Primitive instanceof BERSet ? "BER Set" : aSN1Primitive instanceof DERSet ? "DER Set" : "Set");
            stringBuffer.append(lineSeparator);
            ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
            String str3 = str + TAB;
            int size2 = aSN1Set.size();
            while (i < size2) {
                _dumpAsString(str3, z, aSN1Set.getObjectAt(i).toASN1Primitive(), stringBuffer);
                i++;
            }
            return;
        }
        if (aSN1Primitive instanceof ASN1TaggedObject) {
            stringBuffer.append(aSN1Primitive instanceof BERTaggedObject ? "BER Tagged " : aSN1Primitive instanceof DERTaggedObject ? "DER Tagged " : "Tagged ");
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
            stringBuffer.append(ASN1Util.getTagText(aSN1TaggedObject));
            if (!aSN1TaggedObject.isExplicit()) {
                stringBuffer.append(" IMPLICIT");
            }
            stringBuffer.append(lineSeparator);
            _dumpAsString(str + TAB, z, aSN1TaggedObject.getBaseObject().toASN1Primitive(), stringBuffer);
            return;
        }
        if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
            stringBuffer.append("ObjectIdentifier(" + ((ASN1ObjectIdentifier) aSN1Primitive).getId() + ")" + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1RelativeOID) {
            stringBuffer.append("RelativeOID(" + ((ASN1RelativeOID) aSN1Primitive).getId() + ")" + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1Boolean) {
            stringBuffer.append("Boolean(" + ((ASN1Boolean) aSN1Primitive).isTrue() + ")" + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1Integer) {
            stringBuffer.append("Integer(" + ((ASN1Integer) aSN1Primitive).getValue() + ")" + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1OctetString) {
            ASN1OctetString aSN1OctetString = (ASN1OctetString) aSN1Primitive;
            stringBuffer.append(aSN1Primitive instanceof BEROctetString ? "BER Constructed Octet String[" : "DER Octet String[");
            stringBuffer.append(aSN1OctetString.getOctetsLength() + "]" + lineSeparator);
            if (z) {
                dumpBinaryDataAsString(stringBuffer, str, aSN1OctetString.getOctets());
                return;
            }
            return;
        }
        if (aSN1Primitive instanceof ASN1BitString) {
            ASN1BitString aSN1BitString = (ASN1BitString) aSN1Primitive;
            stringBuffer.append(aSN1BitString instanceof DERBitString ? "DER Bit String[" : aSN1BitString instanceof DLBitString ? "DL Bit String[" : "BER Bit String[");
            stringBuffer.append(aSN1BitString.getBytesLength() + ", " + aSN1BitString.getPadBits() + "]" + lineSeparator);
            if (z) {
                dumpBinaryDataAsString(stringBuffer, str, aSN1BitString.getBytes());
                return;
            }
            return;
        }
        if (aSN1Primitive instanceof ASN1IA5String) {
            stringBuffer.append("IA5String(" + ((ASN1IA5String) aSN1Primitive).getString() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1UTF8String) {
            stringBuffer.append("UTF8String(" + ((ASN1UTF8String) aSN1Primitive).getString() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1NumericString) {
            stringBuffer.append("NumericString(" + ((ASN1NumericString) aSN1Primitive).getString() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1PrintableString) {
            stringBuffer.append("PrintableString(" + ((ASN1PrintableString) aSN1Primitive).getString() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1VisibleString) {
            stringBuffer.append("VisibleString(" + ((ASN1VisibleString) aSN1Primitive).getString() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1BMPString) {
            stringBuffer.append("BMPString(" + ((ASN1BMPString) aSN1Primitive).getString() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1T61String) {
            stringBuffer.append("T61String(" + ((ASN1T61String) aSN1Primitive).getString() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1GraphicString) {
            stringBuffer.append("GraphicString(" + ((ASN1GraphicString) aSN1Primitive).getString() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1VideotexString) {
            stringBuffer.append("VideotexString(" + ((ASN1VideotexString) aSN1Primitive).getString() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1UTCTime) {
            stringBuffer.append("UTCTime(" + ((ASN1UTCTime) aSN1Primitive).getTime() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1GeneralizedTime) {
            stringBuffer.append("GeneralizedTime(" + ((ASN1GeneralizedTime) aSN1Primitive).getTime() + ") " + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1Enumerated) {
            stringBuffer.append("DER Enumerated(" + ((ASN1Enumerated) aSN1Primitive).getValue() + ")" + lineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1ObjectDescriptor) {
            stringBuffer.append("ObjectDescriptor(" + ((ASN1ObjectDescriptor) aSN1Primitive).getBaseGraphicString().getString() + ") " + lineSeparator);
            return;
        }
        if (!(aSN1Primitive instanceof ASN1External)) {
            stringBuffer.append(aSN1Primitive.toString() + lineSeparator);
            return;
        }
        ASN1External aSN1External = (ASN1External) aSN1Primitive;
        stringBuffer.append("External " + lineSeparator);
        String str4 = str + TAB;
        if (aSN1External.getDirectReference() != null) {
            stringBuffer.append(str4 + "Direct Reference: " + aSN1External.getDirectReference().getId() + lineSeparator);
        }
        if (aSN1External.getIndirectReference() != null) {
            stringBuffer.append(str4 + "Indirect Reference: " + aSN1External.getIndirectReference().toString() + lineSeparator);
        }
        if (aSN1External.getDataValueDescriptor() != null) {
            _dumpAsString(str4, z, aSN1External.getDataValueDescriptor(), stringBuffer);
        }
        stringBuffer.append(str4 + "Encoding: " + aSN1External.getEncoding() + lineSeparator);
        _dumpAsString(str4, z, aSN1External.getExternalContent(), stringBuffer);
    }

    private static void appendAscString(StringBuffer stringBuffer, byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 != i + i2; i3++) {
            byte b = bArr[i3];
            if (b >= 32 && b <= 126) {
                stringBuffer.append((char) b);
            }
        }
    }

    public static String dumpAsString(Object obj) {
        return dumpAsString(obj, false);
    }

    public static String dumpAsString(Object obj, boolean z) {
        ASN1Primitive aSN1Primitive;
        if (obj instanceof ASN1Primitive) {
            aSN1Primitive = (ASN1Primitive) obj;
        } else {
            if (!(obj instanceof ASN1Encodable)) {
                return "unknown object type " + obj.toString();
            }
            aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
        }
        StringBuffer stringBuffer = new StringBuffer();
        _dumpAsString("", z, aSN1Primitive, stringBuffer);
        return stringBuffer.toString();
    }

    private static void dumpBinaryDataAsString(StringBuffer stringBuffer, String str, byte[] bArr) {
        if (bArr.length < 1) {
            return;
        }
        String lineSeparator = Strings.lineSeparator();
        String str2 = str + TAB;
        for (int i = 0; i < bArr.length; i += 32) {
            int min = Math.min(bArr.length - i, 32);
            stringBuffer.append(str2);
            stringBuffer.append(Hex.toHexString(bArr, i, min));
            for (int i2 = min; i2 < 32; i2++) {
                stringBuffer.append("  ");
            }
            stringBuffer.append(TAB);
            appendAscString(stringBuffer, bArr, i, min);
            stringBuffer.append(lineSeparator);
        }
    }
}
