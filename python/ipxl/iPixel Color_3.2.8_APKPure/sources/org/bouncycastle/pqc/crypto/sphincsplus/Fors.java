package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.LinkedList;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
class Fors {
    SPHINCSPlusEngine engine;

    public Fors(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
    }

    static int[] message_to_idxs(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            iArr[i4] = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                iArr[i4] = iArr[i4] ^ (((bArr[i3 >> 3] >> (i3 & 7)) & 1) << i5);
                i3++;
            }
        }
        return iArr;
    }

    public byte[] pkFromSig(SIG_FORS[] sig_forsArr, byte[] bArr, byte[] bArr2, ADRS adrs) {
        int i;
        int i2 = 2;
        byte[][] bArr3 = new byte[2][];
        byte[][] bArr4 = new byte[this.engine.K][];
        int i3 = this.engine.T;
        int[] message_to_idxs = message_to_idxs(bArr, this.engine.K, this.engine.A);
        int i4 = 0;
        while (i4 < this.engine.K) {
            int i5 = message_to_idxs[i4];
            byte[] sk = sig_forsArr[i4].getSK();
            adrs.setTreeHeight(0);
            int i6 = (i4 * i3) + i5;
            adrs.setTreeIndex(i6);
            bArr3[0] = this.engine.F(bArr2, adrs, sk);
            byte[][] authPath = sig_forsArr[i4].getAuthPath();
            adrs.setTreeIndex(i6);
            int i7 = 0;
            while (i7 < this.engine.A) {
                int i8 = i7 + 1;
                adrs.setTreeHeight(i8);
                if ((i5 / (1 << i7)) % i2 == 0) {
                    adrs.setTreeIndex(adrs.getTreeIndex() / i2);
                    i = i2;
                    bArr3[1] = this.engine.H(bArr2, adrs, bArr3[0], authPath[i7]);
                } else {
                    i = i2;
                    adrs.setTreeIndex((adrs.getTreeIndex() - 1) / 2);
                    bArr3[1] = this.engine.H(bArr2, adrs, authPath[i7], bArr3[0]);
                }
                bArr3[0] = bArr3[1];
                i7 = i8;
                i2 = i;
            }
            bArr4[i4] = bArr3[0];
            i4++;
            i2 = i2;
        }
        ADRS adrs2 = new ADRS(adrs);
        adrs2.setTypeAndClear(4);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr4));
    }

    public SIG_FORS[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        Fors fors = this;
        ADRS adrs2 = new ADRS(adrs);
        int[] message_to_idxs = message_to_idxs(bArr, fors.engine.K, fors.engine.A);
        SIG_FORS[] sig_forsArr = new SIG_FORS[fors.engine.K];
        int i = fors.engine.T;
        int i2 = 0;
        while (i2 < fors.engine.K) {
            int i3 = message_to_idxs[i2];
            adrs2.setTypeAndClear(6);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setTreeHeight(0);
            int i4 = i2 * i;
            adrs2.setTreeIndex(i4 + i3);
            byte[] bArr4 = bArr2;
            byte[] bArr5 = bArr3;
            byte[] PRF = fors.engine.PRF(bArr5, bArr4, adrs2);
            adrs2.changeType(3);
            byte[][] bArr6 = new byte[fors.engine.A][];
            int i5 = 0;
            while (i5 < fors.engine.A) {
                int i6 = 1 << i5;
                bArr6[i5] = fors.treehash(bArr4, (((i3 / i6) ^ 1) * i6) + i4, i5, bArr5, adrs2);
                i5++;
                fors = this;
                bArr4 = bArr2;
                bArr5 = bArr3;
            }
            sig_forsArr[i2] = new SIG_FORS(PRF, bArr6);
            i2++;
            fors = this;
        }
        return sig_forsArr;
    }

    byte[] treehash(byte[] bArr, int i, int i2, byte[] bArr2, ADRS adrs) {
        if (((i >>> i2) << i2) != i) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        ADRS adrs2 = new ADRS(adrs);
        for (int i3 = 0; i3 < (1 << i2); i3++) {
            adrs2.setTypeAndClear(6);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setTreeHeight(0);
            int i4 = i + i3;
            adrs2.setTreeIndex(i4);
            byte[] PRF = this.engine.PRF(bArr2, bArr, adrs2);
            adrs2.changeType(3);
            byte[] F = this.engine.F(bArr2, adrs2, PRF);
            adrs2.setTreeHeight(1);
            int i5 = 1;
            while (!linkedList.isEmpty() && ((NodeEntry) linkedList.get(0)).nodeHeight == i5) {
                i4 = (i4 - 1) / 2;
                adrs2.setTreeIndex(i4);
                F = this.engine.H(bArr2, adrs2, ((NodeEntry) linkedList.remove(0)).nodeValue, F);
                i5++;
                adrs2.setTreeHeight(i5);
            }
            linkedList.add(0, new NodeEntry(F, i5));
        }
        return ((NodeEntry) linkedList.get(0)).nodeValue;
    }
}
