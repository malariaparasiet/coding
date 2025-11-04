package org.bouncycastle.crypto.agreement.ecjpake;

import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.math.ec.ECCurve;

/* loaded from: classes3.dex */
public class ECJPAKECurves {
    public static final ECJPAKECurve NIST_P256 = getCurve("P-256");
    public static final ECJPAKECurve NIST_P384 = getCurve("P-384");
    public static final ECJPAKECurve NIST_P521 = getCurve("P-521");

    private static ECJPAKECurve getCurve(String str) {
        X9ECParameters byName = CustomNamedCurves.getByName(str);
        return new ECJPAKECurve((ECCurve.AbstractFp) byName.getCurve(), byName.getG());
    }
}
