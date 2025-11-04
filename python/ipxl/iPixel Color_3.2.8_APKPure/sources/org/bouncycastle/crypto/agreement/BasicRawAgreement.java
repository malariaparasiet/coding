package org.bouncycastle.crypto.agreement;

import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.RawAgreement;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public final class BasicRawAgreement implements RawAgreement {
    public final BasicAgreement basicAgreement;

    public BasicRawAgreement(BasicAgreement basicAgreement) {
        if (basicAgreement == null) {
            throw new NullPointerException("'basicAgreement' cannot be null");
        }
        this.basicAgreement = basicAgreement;
    }

    @Override // org.bouncycastle.crypto.RawAgreement
    public void calculateAgreement(CipherParameters cipherParameters, byte[] bArr, int i) {
        BigIntegers.asUnsignedByteArray(this.basicAgreement.calculateAgreement(cipherParameters), bArr, i, getAgreementSize());
    }

    @Override // org.bouncycastle.crypto.RawAgreement
    public int getAgreementSize() {
        return this.basicAgreement.getFieldSize();
    }

    @Override // org.bouncycastle.crypto.RawAgreement
    public void init(CipherParameters cipherParameters) {
        this.basicAgreement.init(cipherParameters);
    }
}
