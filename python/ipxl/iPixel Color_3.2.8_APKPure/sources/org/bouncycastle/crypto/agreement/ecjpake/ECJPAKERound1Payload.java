package org.bouncycastle.crypto.agreement.ecjpake;

import org.bouncycastle.math.ec.ECPoint;

/* loaded from: classes3.dex */
public class ECJPAKERound1Payload {
    private final ECPoint gx1;
    private final ECPoint gx2;
    private final ECSchnorrZKP knowledgeProofForX1;
    private final ECSchnorrZKP knowledgeProofForX2;
    private final String participantId;

    public ECJPAKERound1Payload(String str, ECPoint eCPoint, ECPoint eCPoint2, ECSchnorrZKP eCSchnorrZKP, ECSchnorrZKP eCSchnorrZKP2) {
        ECJPAKEUtil.validateNotNull(str, "participantId");
        ECJPAKEUtil.validateNotNull(eCPoint, "gx1");
        ECJPAKEUtil.validateNotNull(eCPoint2, "gx2");
        ECJPAKEUtil.validateNotNull(eCSchnorrZKP, "knowledgeProofForX1");
        ECJPAKEUtil.validateNotNull(eCSchnorrZKP2, "knowledgeProofForX2");
        this.participantId = str;
        this.gx1 = eCPoint;
        this.gx2 = eCPoint2;
        this.knowledgeProofForX1 = eCSchnorrZKP;
        this.knowledgeProofForX2 = eCSchnorrZKP2;
    }

    public ECPoint getGx1() {
        return this.gx1;
    }

    public ECPoint getGx2() {
        return this.gx2;
    }

    public ECSchnorrZKP getKnowledgeProofForX1() {
        return this.knowledgeProofForX1;
    }

    public ECSchnorrZKP getKnowledgeProofForX2() {
        return this.knowledgeProofForX2;
    }

    public String getParticipantId() {
        return this.participantId;
    }
}
