package org.bouncycastle.crypto.agreement.ecjpake;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class ECJPAKERound3Payload {
    private final BigInteger macTag;
    private final String participantId;

    public ECJPAKERound3Payload(String str, BigInteger bigInteger) {
        this.participantId = str;
        this.macTag = bigInteger;
    }

    public BigInteger getMacTag() {
        return this.macTag;
    }

    public String getParticipantId() {
        return this.participantId;
    }
}
