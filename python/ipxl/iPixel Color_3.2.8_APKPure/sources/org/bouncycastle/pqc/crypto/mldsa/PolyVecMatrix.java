package org.bouncycastle.pqc.crypto.mldsa;

/* loaded from: classes4.dex */
class PolyVecMatrix {
    private final PolyVecL[] matrix;

    PolyVecMatrix(MLDSAEngine mLDSAEngine) {
        int dilithiumK = mLDSAEngine.getDilithiumK();
        this.matrix = new PolyVecL[dilithiumK];
        for (int i = 0; i < dilithiumK; i++) {
            this.matrix[i] = new PolyVecL(mLDSAEngine);
        }
    }

    private String addString() {
        String str = "[";
        int i = 0;
        while (i < this.matrix.length) {
            String str2 = (str + "Outer Matrix " + i + " [") + this.matrix[i].toString();
            str = (i == this.matrix.length + (-1) ? new StringBuilder().append(str2).append("]\n") : new StringBuilder().append(str2).append("],\n")).toString();
            i++;
        }
        return str + "]\n";
    }

    public void expandMatrix(byte[] bArr) {
        int i = 0;
        while (true) {
            PolyVecL[] polyVecLArr = this.matrix;
            if (i >= polyVecLArr.length) {
                return;
            }
            polyVecLArr[i].uniformBlocks(bArr, i << 8);
            i++;
        }
    }

    public void pointwiseMontgomery(PolyVecK polyVecK, PolyVecL polyVecL) {
        for (int i = 0; i < this.matrix.length; i++) {
            polyVecK.getVectorIndex(i).pointwiseAccountMontgomery(this.matrix[i], polyVecL);
        }
    }

    public String toString(String str) {
        return str.concat(": \n" + addString());
    }
}
