package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import kotlin.UByte;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.qualified.MonetaryValue;
import org.bouncycastle.asn1.x509.qualified.QCStatement;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.bouncycastle.util.Integers;

/* loaded from: classes4.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final int NAME_CHECK_MAX = 1024;
    private static final String RESOURCE_NAME = "org.bouncycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected Date currentDate;
    protected List[] errors;
    private boolean initialized;
    protected int n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public PKIXCertPathReviewer() {
    }

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & UByte.MAX_VALUE));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        Iterator<PKIXCertPathChecker> it = certPathCheckers.iterator();
        while (it.hasNext()) {
            try {
                try {
                    it.next().init(false);
                } catch (CertPathValidatorException e) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
                }
            } catch (CertPathReviewerException e2) {
                addError(e2.getErrorMessage(), e2.getIndex());
                return;
            }
        }
        for (int size = this.certs.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(KEY_USAGE);
                criticalExtensionOIDs.remove(CERTIFICATE_POLICIES);
                criticalExtensionOIDs.remove(POLICY_MAPPINGS);
                criticalExtensionOIDs.remove(INHIBIT_ANY_POLICY);
                criticalExtensionOIDs.remove(ISSUING_DISTRIBUTION_POINT);
                criticalExtensionOIDs.remove(DELTA_CRL_INDICATOR);
                criticalExtensionOIDs.remove(POLICY_CONSTRAINTS);
                criticalExtensionOIDs.remove(BASIC_CONSTRAINTS);
                criticalExtensionOIDs.remove(SUBJECT_ALTERNATIVE_NAME);
                criticalExtensionOIDs.remove(NAME_CONSTRAINTS);
                if (size == 0) {
                    criticalExtensionOIDs.remove(Extension.extendedKeyUsage.getId());
                }
                String str = QC_STATEMENT;
                if (criticalExtensionOIDs.contains(str) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(str);
                }
                Iterator<PKIXCertPathChecker> it2 = certPathCheckers.iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e3) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e3.getMessage(), e3, e3.getClass().getName()}), e3.getCause(), this.certPath, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it3 = criticalExtensionOIDs.iterator();
                    while (it3.hasNext()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it3.next())}), size);
                    }
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        if (aSN1Sequence2.size() > 1024) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), this.certPath, size);
                                        }
                                        for (int i = 0; i < aSN1Sequence2.size(); i++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e2, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e3) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e4) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                        }
                    } catch (IOException e5) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e5, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) getExtensionValue(x509Certificate, NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i2 = 0; i2 != excludedSubtrees.length; i2++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i2]);
                            }
                        }
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e6, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        ASN1Integer pathLenConstraintInteger;
        int i = this.n;
        int i2 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                if (i <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i--;
                i2++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && basicConstraints.isCA() && (pathLenConstraintInteger = basicConstraints.getPathLenConstraintInteger()) != null) {
                i = Math.min(i, pathLenConstraintInteger.intPositiveValueExact());
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i2)}));
    }

    /* JADX WARN: Code restructure failed: missing block: B:230:0x0144, code lost:
    
        r22 = getQualifierSet(r10.getPolicyQualifiers());
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x014e, code lost:
    
        r9 = r5[r14 - 1];
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x0155, code lost:
    
        if (r10 >= r9.size()) goto L380;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x0157, code lost:
    
        r21 = (org.bouncycastle.jce.provider.PKIXPolicyNode) r9.get(r10);
        r11 = r21.getExpectedPolicies().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x016b, code lost:
    
        if (r11.hasNext() == false) goto L381;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x016d, code lost:
    
        r30 = r9;
        r9 = r11.next();
        r31 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x0177, code lost:
    
        if ((r9 instanceof java.lang.String) == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x0179, code lost:
    
        r9 = (java.lang.String) r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x0186, code lost:
    
        r10 = r21.getChildren();
        r17 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x0190, code lost:
    
        if (r10.hasNext() == false) goto L385;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x0192, code lost:
    
        r19 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x01a2, code lost:
    
        if (r9.equals(((org.bouncycastle.jce.provider.PKIXPolicyNode) r10.next()).getValidPolicy()) == false) goto L387;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x01a4, code lost:
    
        r17 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x01a6, code lost:
    
        r10 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x01a9, code lost:
    
        if (r17 != false) goto L384;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x01ab, code lost:
    
        r10 = new java.util.HashSet();
        r10.add(r9);
        r17 = new org.bouncycastle.jce.provider.PKIXPolicyNode(new java.util.ArrayList(), r14, r10, r21, r22, r9, false);
        r9 = r21;
        r9.addChild(r17);
        r21 = r9;
        r5[r14].add(r17);
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x01d3, code lost:
    
        r9 = r30;
        r10 = r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x017e, code lost:
    
        if ((r9 instanceof org.bouncycastle.asn1.ASN1ObjectIdentifier) == false) goto L383;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0180, code lost:
    
        r9 = ((org.bouncycastle.asn1.ASN1ObjectIdentifier) r9).getId();
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x01d8, code lost:
    
        r10 = r10 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:227:0x012e A[Catch: CertPathReviewerException -> 0x05e3, TRY_LEAVE, TryCatch #6 {CertPathReviewerException -> 0x05e3, blocks: (B:15:0x0068, B:19:0x0077, B:22:0x0084, B:26:0x0094, B:27:0x009f, B:29:0x00a5, B:32:0x00c6, B:33:0x00ce, B:35:0x00d4, B:41:0x00d9, B:42:0x00e5, B:48:0x00f1, B:51:0x00f8, B:52:0x0101, B:54:0x0107, B:57:0x0111, B:64:0x011a, B:66:0x011e, B:68:0x01ee, B:70:0x01f2, B:71:0x01fb, B:73:0x0201, B:75:0x020d, B:82:0x0214, B:80:0x0217, B:86:0x021e, B:88:0x0226, B:89:0x022f, B:91:0x0235, B:100:0x0253, B:101:0x025f, B:102:0x0260, B:108:0x0264, B:110:0x026c, B:111:0x0272, B:113:0x0278, B:116:0x029c, B:118:0x02a6, B:120:0x02ab, B:121:0x02b7, B:123:0x02b8, B:124:0x02c4, B:127:0x02c9, B:128:0x02dc, B:130:0x02e2, B:132:0x0308, B:134:0x0320, B:135:0x0317, B:138:0x0325, B:139:0x032b, B:141:0x0331, B:144:0x0339, B:157:0x035b, B:149:0x033e, B:150:0x034a, B:152:0x034c, B:153:0x0358, B:162:0x0367, B:171:0x0389, B:173:0x0393, B:174:0x0397, B:176:0x039d, B:190:0x03ad, B:179:0x03ba, B:200:0x03c7, B:202:0x03d1, B:106:0x040f, B:209:0x03d9, B:210:0x03e7, B:212:0x03e8, B:213:0x03f4, B:222:0x03f6, B:223:0x0404, B:224:0x0124, B:225:0x0128, B:227:0x012e, B:230:0x0144, B:232:0x014e, B:233:0x0151, B:235:0x0157, B:236:0x0167, B:238:0x016d, B:240:0x0179, B:241:0x0186, B:242:0x018c, B:244:0x0192, B:252:0x01ab, B:256:0x017c, B:258:0x0180, B:261:0x01d8, B:265:0x01e1, B:266:0x01ed, B:273:0x041d, B:274:0x0429, B:276:0x042a, B:281:0x043d, B:283:0x0447, B:284:0x044c, B:286:0x0452, B:289:0x0460, B:304:0x0473, B:311:0x05c8, B:312:0x05d4, B:314:0x047e, B:315:0x048a, B:316:0x048b, B:318:0x0491, B:320:0x0499, B:322:0x049f, B:324:0x04a7, B:325:0x04aa, B:327:0x04b0, B:329:0x04c0, B:330:0x04c4, B:332:0x04ca, B:334:0x04d2, B:337:0x04d5, B:339:0x04d8, B:340:0x04dc, B:342:0x04e2, B:345:0x04f2, B:347:0x04fc, B:348:0x04ff, B:350:0x0505, B:352:0x0511, B:354:0x0515, B:357:0x0518, B:360:0x051e, B:361:0x052a, B:363:0x052f, B:365:0x0537, B:366:0x053a, B:368:0x0540, B:370:0x0550, B:371:0x0554, B:373:0x055a, B:376:0x056a, B:381:0x056e, B:384:0x0571, B:386:0x0574, B:387:0x057a, B:389:0x0580, B:391:0x0592, B:397:0x059c, B:399:0x05a4, B:400:0x05a7, B:402:0x05ad, B:404:0x05b9, B:406:0x05bd, B:409:0x05c0, B:411:0x05d6, B:412:0x05e2), top: B:14:0x0068, inners: #2, #3, #4, #5, #7, #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:268:0x01ee A[EDGE_INSN: B:268:0x01ee->B:68:0x01ee BREAK  A[LOOP:11: B:225:0x0128->B:267:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01f2 A[Catch: CertPathReviewerException -> 0x05e3, TryCatch #6 {CertPathReviewerException -> 0x05e3, blocks: (B:15:0x0068, B:19:0x0077, B:22:0x0084, B:26:0x0094, B:27:0x009f, B:29:0x00a5, B:32:0x00c6, B:33:0x00ce, B:35:0x00d4, B:41:0x00d9, B:42:0x00e5, B:48:0x00f1, B:51:0x00f8, B:52:0x0101, B:54:0x0107, B:57:0x0111, B:64:0x011a, B:66:0x011e, B:68:0x01ee, B:70:0x01f2, B:71:0x01fb, B:73:0x0201, B:75:0x020d, B:82:0x0214, B:80:0x0217, B:86:0x021e, B:88:0x0226, B:89:0x022f, B:91:0x0235, B:100:0x0253, B:101:0x025f, B:102:0x0260, B:108:0x0264, B:110:0x026c, B:111:0x0272, B:113:0x0278, B:116:0x029c, B:118:0x02a6, B:120:0x02ab, B:121:0x02b7, B:123:0x02b8, B:124:0x02c4, B:127:0x02c9, B:128:0x02dc, B:130:0x02e2, B:132:0x0308, B:134:0x0320, B:135:0x0317, B:138:0x0325, B:139:0x032b, B:141:0x0331, B:144:0x0339, B:157:0x035b, B:149:0x033e, B:150:0x034a, B:152:0x034c, B:153:0x0358, B:162:0x0367, B:171:0x0389, B:173:0x0393, B:174:0x0397, B:176:0x039d, B:190:0x03ad, B:179:0x03ba, B:200:0x03c7, B:202:0x03d1, B:106:0x040f, B:209:0x03d9, B:210:0x03e7, B:212:0x03e8, B:213:0x03f4, B:222:0x03f6, B:223:0x0404, B:224:0x0124, B:225:0x0128, B:227:0x012e, B:230:0x0144, B:232:0x014e, B:233:0x0151, B:235:0x0157, B:236:0x0167, B:238:0x016d, B:240:0x0179, B:241:0x0186, B:242:0x018c, B:244:0x0192, B:252:0x01ab, B:256:0x017c, B:258:0x0180, B:261:0x01d8, B:265:0x01e1, B:266:0x01ed, B:273:0x041d, B:274:0x0429, B:276:0x042a, B:281:0x043d, B:283:0x0447, B:284:0x044c, B:286:0x0452, B:289:0x0460, B:304:0x0473, B:311:0x05c8, B:312:0x05d4, B:314:0x047e, B:315:0x048a, B:316:0x048b, B:318:0x0491, B:320:0x0499, B:322:0x049f, B:324:0x04a7, B:325:0x04aa, B:327:0x04b0, B:329:0x04c0, B:330:0x04c4, B:332:0x04ca, B:334:0x04d2, B:337:0x04d5, B:339:0x04d8, B:340:0x04dc, B:342:0x04e2, B:345:0x04f2, B:347:0x04fc, B:348:0x04ff, B:350:0x0505, B:352:0x0511, B:354:0x0515, B:357:0x0518, B:360:0x051e, B:361:0x052a, B:363:0x052f, B:365:0x0537, B:366:0x053a, B:368:0x0540, B:370:0x0550, B:371:0x0554, B:373:0x055a, B:376:0x056a, B:381:0x056e, B:384:0x0571, B:386:0x0574, B:387:0x057a, B:389:0x0580, B:391:0x0592, B:397:0x059c, B:399:0x05a4, B:400:0x05a7, B:402:0x05ad, B:404:0x05b9, B:406:0x05bd, B:409:0x05c0, B:411:0x05d6, B:412:0x05e2), top: B:14:0x0068, inners: #2, #3, #4, #5, #7, #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0226 A[Catch: CertPathReviewerException -> 0x05e3, TryCatch #6 {CertPathReviewerException -> 0x05e3, blocks: (B:15:0x0068, B:19:0x0077, B:22:0x0084, B:26:0x0094, B:27:0x009f, B:29:0x00a5, B:32:0x00c6, B:33:0x00ce, B:35:0x00d4, B:41:0x00d9, B:42:0x00e5, B:48:0x00f1, B:51:0x00f8, B:52:0x0101, B:54:0x0107, B:57:0x0111, B:64:0x011a, B:66:0x011e, B:68:0x01ee, B:70:0x01f2, B:71:0x01fb, B:73:0x0201, B:75:0x020d, B:82:0x0214, B:80:0x0217, B:86:0x021e, B:88:0x0226, B:89:0x022f, B:91:0x0235, B:100:0x0253, B:101:0x025f, B:102:0x0260, B:108:0x0264, B:110:0x026c, B:111:0x0272, B:113:0x0278, B:116:0x029c, B:118:0x02a6, B:120:0x02ab, B:121:0x02b7, B:123:0x02b8, B:124:0x02c4, B:127:0x02c9, B:128:0x02dc, B:130:0x02e2, B:132:0x0308, B:134:0x0320, B:135:0x0317, B:138:0x0325, B:139:0x032b, B:141:0x0331, B:144:0x0339, B:157:0x035b, B:149:0x033e, B:150:0x034a, B:152:0x034c, B:153:0x0358, B:162:0x0367, B:171:0x0389, B:173:0x0393, B:174:0x0397, B:176:0x039d, B:190:0x03ad, B:179:0x03ba, B:200:0x03c7, B:202:0x03d1, B:106:0x040f, B:209:0x03d9, B:210:0x03e7, B:212:0x03e8, B:213:0x03f4, B:222:0x03f6, B:223:0x0404, B:224:0x0124, B:225:0x0128, B:227:0x012e, B:230:0x0144, B:232:0x014e, B:233:0x0151, B:235:0x0157, B:236:0x0167, B:238:0x016d, B:240:0x0179, B:241:0x0186, B:242:0x018c, B:244:0x0192, B:252:0x01ab, B:256:0x017c, B:258:0x0180, B:261:0x01d8, B:265:0x01e1, B:266:0x01ed, B:273:0x041d, B:274:0x0429, B:276:0x042a, B:281:0x043d, B:283:0x0447, B:284:0x044c, B:286:0x0452, B:289:0x0460, B:304:0x0473, B:311:0x05c8, B:312:0x05d4, B:314:0x047e, B:315:0x048a, B:316:0x048b, B:318:0x0491, B:320:0x0499, B:322:0x049f, B:324:0x04a7, B:325:0x04aa, B:327:0x04b0, B:329:0x04c0, B:330:0x04c4, B:332:0x04ca, B:334:0x04d2, B:337:0x04d5, B:339:0x04d8, B:340:0x04dc, B:342:0x04e2, B:345:0x04f2, B:347:0x04fc, B:348:0x04ff, B:350:0x0505, B:352:0x0511, B:354:0x0515, B:357:0x0518, B:360:0x051e, B:361:0x052a, B:363:0x052f, B:365:0x0537, B:366:0x053a, B:368:0x0540, B:370:0x0550, B:371:0x0554, B:373:0x055a, B:376:0x056a, B:381:0x056e, B:384:0x0571, B:386:0x0574, B:387:0x057a, B:389:0x0580, B:391:0x0592, B:397:0x059c, B:399:0x05a4, B:400:0x05a7, B:402:0x05ad, B:404:0x05b9, B:406:0x05bd, B:409:0x05c0, B:411:0x05d6, B:412:0x05e2), top: B:14:0x0068, inners: #2, #3, #4, #5, #7, #8, #9, #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void checkPolicy() {
        /*
            Method dump skipped, instructions count: 1520
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkPolicy():void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:30|(2:127|128)(2:32|(2:121|122)(3:34|(2:38|(1:40))|41))|(2:42|43)|44|(18:83|84|(15:86|87|88|(11:90|91|(2:94|92)|95|96|(2:99|97)|100|101|102|103|104)|111|91|(1:92)|95|96|(1:97)|100|101|102|103|104)|114|87|88|(0)|111|91|(1:92)|95|96|(1:97)|100|101|102|103|104)(1:46)|(1:50)|51|(7:53|(1:57)|58|59|(2:61|(1:63))(1:79)|64|(7:66|(1:78)|70|71|72|74|75))|82|70|71|72|74|75) */
    /* JADX WARN: Can't wrap try/catch for region: R(15:(2:83|84)|(3:(15:86|87|88|(11:90|91|(2:94|92)|95|96|(2:99|97)|100|101|102|103|104)|111|91|(1:92)|95|96|(1:97)|100|101|102|103|104)|103|104)|114|87|88|(0)|111|91|(1:92)|95|96|(1:97)|100|101|102) */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02ee, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02ef, code lost:
    
        r12 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x028a, code lost:
    
        addError(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.crlAuthInfoAccError"), r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0394, code lost:
    
        addError(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.pubKeyError"), r9);
     */
    /* JADX WARN: Removed duplicated region for block: B:139:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0265 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0285 A[Catch: AnnotatedException -> 0x028a, TRY_LEAVE, TryCatch #0 {AnnotatedException -> 0x028a, blocks: (B:88:0x027d, B:90:0x0285), top: B:87:0x027d }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02a7 A[LOOP:1: B:92:0x02a1->B:94:0x02a7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02ca A[LOOP:2: B:97:0x02c4->B:99:0x02ca, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void checkSignatures() {
        /*
            Method dump skipped, instructions count: 937
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkSignatures():void");
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate, QC_STATEMENT);
            boolean z = false;
            for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i2));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals((ASN1Primitive) qCStatement.getStatementId())) {
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance");
                } else {
                    if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals((ASN1Primitive) qCStatement.getStatementId())) {
                        if (QCStatement.id_etsi_qcs_QcSSCD.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD");
                        } else if (QCStatement.id_etsi_qcs_LimiteValue.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                            monetaryValue.getCurrency();
                            double doubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                            addNotification(monetaryValue.getCurrency().isAlphabetic() ? new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{monetaryValue.getCurrency().getAlphabetic(), new TrustedInput(new Double(doubleValue)), monetaryValue}) : new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), monetaryValue}), i);
                        } else {
                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i);
                            z = true;
                        }
                    }
                }
                addNotification(errorBundle, i);
            }
            return !z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i);
            return false;
        }
    }

    protected void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    protected void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x023a  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void checkCRLs(java.security.cert.PKIXParameters r23, java.security.cert.X509Certificate r24, java.util.Date r25, java.security.cert.X509Certificate r26, java.security.PublicKey r27, java.util.Vector r28, int r29) throws org.bouncycastle.x509.CertPathReviewerException {
        /*
            Method dump skipped, instructions count: 1028
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkCRLs(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    protected void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    protected void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
        if (this.notifications != null) {
            return;
        }
        int i = this.n;
        this.notifications = new List[i + 1];
        this.errors = new List[i + 1];
        int i2 = 0;
        while (true) {
            List[] listArr = this.notifications;
            if (i2 >= listArr.length) {
                checkSignatures();
                checkNameConstraints();
                checkPathLength();
                checkPolicy();
                checkCriticalExtensions();
                return;
            }
            listArr[i2] = new ArrayList();
            this.errors[i2] = new ArrayList();
            i2++;
        }
    }

    protected Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((ASN1IA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.n;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    protected Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals((ASN1Primitive) AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((ASN1IA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    protected Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                if (authorityKeyIdentifier.getAuthorityCertSerialNumber() != null) {
                    x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                } else {
                    byte[] keyIdentifier = authorityKeyIdentifier.getKeyIdentifier();
                    if (keyIdentifier != null) {
                        x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                    }
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }

    public void init(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        if (certPath == null) {
            throw new NullPointerException("certPath was null");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        if (certificates.size() != 1) {
            HashSet hashSet = new HashSet();
            Iterator<TrustAnchor> it = pKIXParameters.getTrustAnchors().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().getTrustedCert());
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i != certificates.size(); i++) {
                if (!hashSet.contains(certificates.get(i))) {
                    arrayList.add(certificates.get(i));
                }
            }
            try {
                this.certPath = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCertPath(arrayList);
                this.certs = arrayList;
            } catch (GeneralSecurityException unused) {
                throw new IllegalStateException("unable to rebuild certpath");
            }
        } else {
            this.certPath = certPath;
            this.certs = certPath.getCertificates();
        }
        this.n = this.certs.size();
        if (this.certs.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
        }
        this.pkixParams = (PKIXParameters) pKIXParameters.clone();
        Date date = new Date();
        this.currentDate = date;
        this.validDate = getValidityDate(this.pkixParams, date);
        this.notifications = null;
        this.errors = null;
        this.trustAnchor = null;
        this.subjectPublicKey = null;
        this.policyTree = null;
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i >= listArr.length) {
                return true;
            }
            if (!listArr[i].isEmpty()) {
                return false;
            }
            i++;
        }
    }
}
