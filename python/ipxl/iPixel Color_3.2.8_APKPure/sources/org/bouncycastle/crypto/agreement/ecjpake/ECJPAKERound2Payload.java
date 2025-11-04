package org.bouncycastle.crypto.agreement.ecjpake;

import org.bouncycastle.math.ec.ECPoint;

/* loaded from: classes3.dex */
public class ECJPAKERound2Payload {
    private final ECPoint a;
    private final ECSchnorrZKP knowledgeProofForX2s;
    private final String participantId;

    public ECJPAKERound2Payload(String str, ECPoint eCPoint, ECSchnorrZKP eCSchnorrZKP) {
        ECJPAKEUtil.validateNotNull(str, "participantId");
        ECJPAKEUtil.validateNotNull(eCPoint, "a");
        ECJPAKEUtil.validateNotNull(eCSchnorrZKP, "knowledgeProofForX2s");
        this.participantId = str;
        this.a = eCPoint;
        this.knowledgeProofForX2s = eCSchnorrZKP;
    }

    public ECPoint getA() {
        return this.a;
    }

    public ECSchnorrZKP getKnowledgeProofForX2s() {
        return this.knowledgeProofForX2s;
    }

    public String getParticipantId() {
        return this.participantId;
    }
}
