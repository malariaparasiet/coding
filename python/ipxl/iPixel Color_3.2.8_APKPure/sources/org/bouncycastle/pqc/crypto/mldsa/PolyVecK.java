package org.bouncycastle.pqc.crypto.mldsa;

/* loaded from: classes4.dex */
class PolyVecK {
    private final Poly[] vec;

    PolyVecK(MLDSAEngine mLDSAEngine) {
        int dilithiumK = mLDSAEngine.getDilithiumK();
        this.vec = new Poly[dilithiumK];
        for (int i = 0; i < dilithiumK; i++) {
            this.vec[i] = new Poly(mLDSAEngine);
        }
    }

    public void addPolyVecK(PolyVecK polyVecK) {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).addPoly(polyVecK.getVectorIndex(i));
        }
    }

    public boolean checkNorm(int i) {
        for (int i2 = 0; i2 < this.vec.length; i2++) {
            if (getVectorIndex(i2).checkNorm(i)) {
                return true;
            }
        }
        return false;
    }

    public void conditionalAddQ() {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).conditionalAddQ();
        }
    }

    public void decompose(PolyVecK polyVecK) {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).decompose(polyVecK.getVectorIndex(i));
        }
    }

    Poly getVectorIndex(int i) {
        return this.vec[i];
    }

    public void invNttToMont() {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).invNttToMont();
        }
    }

    public int makeHint(PolyVecK polyVecK, PolyVecK polyVecK2) {
        int i = 0;
        for (int i2 = 0; i2 < this.vec.length; i2++) {
            i += getVectorIndex(i2).polyMakeHint(polyVecK.getVectorIndex(i2), polyVecK2.getVectorIndex(i2));
        }
        return i;
    }

    public void packW1(MLDSAEngine mLDSAEngine, byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.vec.length; i2++) {
            getVectorIndex(i2).packW1(bArr, (mLDSAEngine.getDilithiumPolyW1PackedBytes() * i2) + i);
        }
    }

    public void pointwisePolyMontgomery(Poly poly, PolyVecK polyVecK) {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).pointwiseMontgomery(poly, polyVecK.getVectorIndex(i));
        }
    }

    public void polyVecNtt() {
        int i = 0;
        while (true) {
            Poly[] polyArr = this.vec;
            if (i >= polyArr.length) {
                return;
            }
            polyArr[i].polyNtt();
            i++;
        }
    }

    public void power2Round(PolyVecK polyVecK) {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).power2Round(polyVecK.getVectorIndex(i));
        }
    }

    public void reduce() {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).reduce();
        }
    }

    void setVectorIndex(int i, Poly poly) {
        this.vec[i] = poly;
    }

    public void shiftLeft() {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).shiftLeft();
        }
    }

    public void subtract(PolyVecK polyVecK) {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).subtract(polyVecK.getVectorIndex(i));
        }
    }

    public String toString() {
        String str = "[";
        for (int i = 0; i < this.vec.length; i++) {
            str = str + i + " " + getVectorIndex(i).toString();
            if (i != this.vec.length - 1) {
                str = str + ",\n";
            }
        }
        return str + "]";
    }

    public String toString(String str) {
        return str + ": " + toString();
    }

    public void uniformEta(byte[] bArr, short s) {
        int i = 0;
        while (true) {
            Poly[] polyArr = this.vec;
            if (i >= polyArr.length) {
                return;
            }
            polyArr[i].uniformEta(bArr, s);
            i++;
            s = (short) (s + 1);
        }
    }

    public void useHint(PolyVecK polyVecK, PolyVecK polyVecK2) {
        for (int i = 0; i < this.vec.length; i++) {
            getVectorIndex(i).polyUseHint(polyVecK.getVectorIndex(i), polyVecK2.getVectorIndex(i));
        }
    }
}
