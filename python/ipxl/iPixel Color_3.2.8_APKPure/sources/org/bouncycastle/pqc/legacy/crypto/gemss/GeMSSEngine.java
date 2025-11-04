package org.bouncycastle.pqc.legacy.crypto.gemss;

import java.math.BigInteger;
import java.security.SecureRandom;
import kotlin.UByte;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.legacy.crypto.gemss.SecretKeyHFE;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
class GeMSSEngine {
    final int ACCESS_last_equations8;
    Pointer Buffer_NB_WORD_GFqn;
    Pointer Buffer_NB_WORD_MUL;
    final boolean ENABLED_REMOVE_ODD_DEGREE;
    final int HFEDELTA;
    final int HFEDeg;
    final int HFEDegI;
    final int HFEDegJ;
    final int HFENr8;
    final int HFENr8c;
    int HFE_odd_degree;
    final int HFEm;
    final int HFEmq;
    final int HFEmq8;
    final int HFEmr;
    final int HFEmr8;
    final int HFEn;
    int HFEn1h_rightmost;
    int HFEn_1rightmost;
    final int HFEnq;
    final int HFEnr;
    final int HFEnv;
    final int HFEnvq;
    final int HFEnvr;
    final int HFEnvr8;
    final int HFEv;
    final int HFEvq;
    final int HFEvr;
    int II;
    int KP;
    int KX;
    final int LOST_BITS;
    int LTRIANGULAR_NV_SIZE;
    final int LTRIANGULAR_N_SIZE;
    final long MASK_GF2m;
    final long MASK_GF2n;
    final int MATRIXn_SIZE;
    final int MATRIXnv_SIZE;
    final int MLv_GFqn_SIZE;
    int MQv_GFqn_SIZE;
    final int NB_BYTES_EQUATION;
    final int NB_BYTES_GFqm;
    final int NB_BYTES_GFqn;
    final int NB_BYTES_GFqnv;
    int NB_COEFS_HFEPOLY;
    final int NB_ITE;
    int NB_MONOMIAL_PK;
    int NB_MONOMIAL_VINEGAR;
    int NB_UINT_HFEVPOLY;
    int NB_WORD_GF2m;
    int NB_WORD_GF2nv;
    final int NB_WORD_GF2nvm;
    int NB_WORD_GFqn;
    final int NB_WORD_GFqv;
    int NB_WORD_MMUL;
    final int NB_WORD_MUL;
    final int NB_WORD_UNCOMP_EQ;
    int POW_II;
    final int SIZE_DIGEST;
    final int SIZE_DIGEST_UINT;
    final int SIZE_ROW;
    final int SIZE_SEED_SK;
    final int SIZE_SIGN_UNCOMPRESSED;
    final int Sha3BitStrength;
    final int ShakeBitStrength;
    final int VAL_BITS_M;
    private int buffer;
    Mul_GF2x mul;
    private SecureRandom random;
    Rem_GF2n rem;
    SHA3Digest sha3Digest;
    final int NB_BITS_UINT = 64;
    final int LEN_UNROLLED_64 = 4;

    enum FunctionParams {
        NV,
        V,
        N,
        M
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x03cd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03b7 A[LOOP:0: B:92:0x03b1->B:94:0x03b7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x03bc A[EDGE_INSN: B:95:0x03bc->B:96:0x03bc BREAK  A[LOOP:0: B:92:0x03b1->B:94:0x03b7], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x03ca A[LOOP:1: B:97:0x03c2->B:99:0x03ca, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GeMSSEngine(int r39, int r40, int r41, int r42, int r43, int r44, int r45, int r46) {
        /*
            Method dump skipped, instructions count: 978
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.legacy.crypto.gemss.GeMSSEngine.<init>(int, int, int, int, int, int, int, int):void");
    }

    private void CMP_AND_SWAP_CST_TIME(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (int i = this.NB_WORD_GFqn - 1; i > 0; i--) {
            j2 |= GeMSSUtils.ORBITS_UINT(pointer2.get(i) ^ pointer.get(i));
            j3 += j2;
        }
        int i2 = 0;
        while (true) {
            int i3 = this.NB_WORD_GFqn;
            if (i2 >= i3) {
                pointer3.setRangeFromXorAndMask_xor(pointer, pointer2, -j, i3);
                return;
            } else {
                j |= (-GeMSSUtils.NORBITS_UINT(i2 ^ j3)) & GeMSSUtils.CMP_LT_UINT(pointer2.get(i2), pointer.get(i2));
                i2++;
            }
        }
    }

    private void LOOPIR(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2, int i3, int i4, boolean z) {
        for (int i5 = 0; i5 < i; i5++) {
            Pointer pointer4 = new Pointer(pointer3);
            int i6 = 1;
            while (i6 <= i2) {
                LOOPJR(pointer, pointer2, pointer4, 64, i4, i6);
                i6++;
            }
            if (z) {
                LOOPJR(pointer, pointer2, pointer4, i3, i4, i6);
            }
            pointer2.move(i4);
        }
    }

    private void LOOPIR_INIT(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, int i, int i2) {
        while (i < i2) {
            pointer.setRangeClear(0, this.NB_WORD_GFqn);
            pointer2.changeIndex(pointer3);
            Pointer pointer5 = pointer;
            LOOPK_COMPLETE(pointer5, pointer4, pointer2, 0, this.HFEnvq);
            pointer4.move(this.NB_WORD_GF2nv);
            i++;
            pointer = pointer5;
        }
    }

    private void LOOPIR_LOOPK_COMPLETE(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        while (i < i2) {
            LOOPK_COMPLETE(pointer, pointer2, pointer3, 0, this.HFEnvq);
            i++;
        }
    }

    private void LOOPJR(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2, int i3) {
        int min = Math.min(i2, i3);
        pointer.set(0L);
        for (int i4 = 0; i4 < i; i4++) {
            pointer.setXor(GeMSSUtils.XORBITS_UINT(pointer2.getDotProduct(0, pointer3, 0, min)) << i4);
            pointer3.move(i3);
        }
        pointer.moveIncremental();
    }

    private long LOOPJR_NOCST_64(Pointer pointer, PointerUnion pointerUnion, int i, int i2, long j, int i3, int i4) {
        while (i < i2) {
            if ((1 & j) != 0) {
                pointer.setXorRange(0, pointerUnion, 0, i4);
            }
            pointerUnion.moveNextBytes(i3);
            j >>>= 1;
            i++;
        }
        return j;
    }

    private void LOOPJR_UNROLLED_64(Pointer pointer, PointerUnion pointerUnion, int i, int i2, long j, int i3, int i4) {
        int i5 = i;
        long j2 = j;
        while (i5 < i2 - 3) {
            j2 = LOOPJR_NOCST_64(pointer, pointerUnion, 0, 4, j2, i3, i4);
            i5 += 4;
        }
        LOOPJR_NOCST_64(pointer, pointerUnion, i5, i2, j2, i3, i4);
    }

    private void LOOPKR(Pointer pointer, Pointer pointer2, long j, int i, int i2) {
        while (i < i2) {
            pointer2.setXorRangeAndMaskMove(pointer, this.NB_WORD_GFqn, -(1 & j));
            j >>>= 1;
            i++;
        }
    }

    private void LOOPK_COMPLETE(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            LOOPKR(pointer3, pointer, pointer2.get(i3), 0, 64);
        }
        if (this.HFEnvr != 0) {
            LOOPKR(pointer3, pointer, pointer2.get(i2), 0, this.HFEnvr);
        }
        pointer.move(this.NB_WORD_GFqn);
    }

    private int chooseRootHFE_gf2nx(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        GeMSSEngine geMSSEngine;
        SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar2;
        Pointer pointer3 = new Pointer(this.SIZE_DIGEST_UINT);
        Pointer pointer4 = new Pointer(((this.HFEDeg << 1) - 1) * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer((this.HFEDeg + 1) * this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer(this.NB_WORD_GFqn);
        pointer6.setRangeFromXor(complete_sparse_monic_gf2nxVar.poly, pointer2, this.NB_WORD_GFqn);
        int i = this.HFEDeg;
        if (i <= 34 || (this.HFEn > 196 && i < 256)) {
            geMSSEngine = this;
            complete_sparse_monic_gf2nxVar2 = complete_sparse_monic_gf2nxVar;
            frobeniusMap_multisqr_HFE_gf2nx(pointer4, complete_sparse_monic_gf2nxVar2, pointer6);
        } else {
            int i2 = 2 << this.HFEDegI;
            pointer4.set(this.NB_WORD_GFqn * i2, 1L);
            geMSSEngine = this;
            geMSSEngine.divsqr_r_HFE_cstdeg_gf2nx(pointer4, i2, i2, this.HFEDeg, complete_sparse_monic_gf2nxVar, pointer6);
            complete_sparse_monic_gf2nxVar2 = complete_sparse_monic_gf2nxVar;
            geMSSEngine.for_sqr_divsqr(pointer4, geMSSEngine.HFEDegI + 1, geMSSEngine.HFEn, complete_sparse_monic_gf2nxVar2, pointer6);
        }
        pointer4.setXor(geMSSEngine.NB_WORD_GFqn, 1L);
        int index = pointer5.getIndex();
        pointer5.copyFrom(complete_sparse_monic_gf2nxVar2.poly, geMSSEngine.NB_WORD_GFqn);
        for_copy_move(pointer5, complete_sparse_monic_gf2nxVar2);
        pointer5.changeIndex(index);
        pointer5.set(geMSSEngine.HFEDeg * geMSSEngine.NB_WORD_GFqn, 1L);
        pointer5.setXorRange(pointer2, geMSSEngine.NB_WORD_GFqn);
        int gcd_gf2nx = gcd_gf2nx(pointer5, geMSSEngine.HFEDeg, pointer4, pointer4.getD_for_not0_or_plus(geMSSEngine.NB_WORD_GFqn, geMSSEngine.HFEDeg - 1));
        if (geMSSEngine.buffer != 0) {
            pointer4.swap(pointer5);
        }
        if (pointer4.is0_gf2n(0, geMSSEngine.NB_WORD_GFqn) == 0) {
            return 0;
        }
        convMonic_gf2nx(pointer5, gcd_gf2nx);
        Pointer pointer7 = new Pointer(geMSSEngine.NB_WORD_GFqn * gcd_gf2nx);
        findRootsSplit_gf2nx(pointer7, pointer5, gcd_gf2nx);
        if (gcd_gf2nx == 1) {
            pointer.copyFrom(pointer7, geMSSEngine.NB_WORD_GFqn);
            return gcd_gf2nx;
        }
        fast_sort_gf2n(pointer7, gcd_gf2nx);
        getSHA3Hash(pointer3, 0, geMSSEngine.Sha3BitStrength >>> 3, pointer2.toBytes(geMSSEngine.NB_BYTES_GFqn), 0, geMSSEngine.NB_BYTES_GFqn, new byte[geMSSEngine.Sha3BitStrength >>> 3]);
        int remainderUnsigned = (int) remainderUnsigned(pointer3.get(), gcd_gf2nx);
        int i3 = this.NB_WORD_GFqn;
        pointer.copyFrom(0, pointer7, remainderUnsigned * i3, i3);
        return gcd_gf2nx;
    }

    private void choose_LOOPJR(Pointer pointer, PointerUnion pointerUnion, int i, long j, int i2, int i3) {
        int i4 = this.HFEnvr;
        if (i4 < 8) {
            LOOPJR_NOCST_64(pointer, pointerUnion, i, i4, j, i2, i3);
        } else {
            LOOPJR_UNROLLED_64(pointer, pointerUnion, i, i4, j, i2, i3);
        }
    }

    private long convMQ_last_uncompressL_gf2(Pointer pointer, PointerUnion pointerUnion) {
        GeMSSEngine geMSSEngine;
        Pointer pointer2;
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int i = this.HFEnv - 1;
        int i2 = i >>> 6;
        int i3 = i & 63;
        int for_setpk2_end_move_plus = for_setpk2_end_move_plus(pointer, pointerUnion2, i2);
        if (i3 != 0) {
            int i4 = i3 + 1;
            geMSSEngine = this;
            pointer2 = pointer;
            for_setpk2_end_move_plus = geMSSEngine.setPk2Value(pointer2, pointerUnion2, for_setpk2_end_move_plus, i2, i4);
        } else {
            geMSSEngine = this;
            pointer2 = pointer;
        }
        int i5 = geMSSEngine.HFEnv;
        int i6 = geMSSEngine.LOST_BITS;
        int i7 = i5 - i6;
        int i8 = i7 >>> 6;
        int i9 = i7 & 63;
        if (i9 != 0) {
            int i10 = for_setpk2_end_move_plus & 63;
            if (i10 != 0) {
                int i11 = geMSSEngine.NB_MONOMIAL_PK;
                if (((((i11 - i6) + 7) >>> 3) & 7) != 0) {
                    int i12 = (i5 - ((64 - (((i11 - i6) - geMSSEngine.HFEnvr) & 63)) & 63)) >>> 6;
                    pointer2.setRangePointerUnion_Check(pointerUnion2, i12, for_setpk2_end_move_plus);
                    pointer2.set(i12, pointerUnion2.getWithCheck(i12) >>> i10);
                    if (i12 < i8) {
                        int i13 = i12 + 1;
                        long withCheck = pointerUnion2.getWithCheck(i13);
                        pointer2.setXor(i12, withCheck << (64 - i10));
                        pointer2.set(i13, withCheck >>> i10);
                    } else if (i9 + i10 > 64) {
                        pointer2.setXor(i12, pointerUnion2.getWithCheck(i12 + 1) << (64 - i10));
                    }
                } else {
                    pointer2.setRangePointerUnion(pointerUnion2, i8, i10);
                    pointer2.set(i8, pointerUnion2.get(i8) >>> i10);
                    if (i9 + i10 > 64) {
                        pointer2.setXor(i8, pointerUnion2.get(i8 + 1) << (64 - i10));
                    }
                }
            } else if (((((geMSSEngine.NB_MONOMIAL_PK - i6) + 7) >>> 3) & 7) != 0) {
                pointer2.setRangePointerUnion(pointerUnion2, i8);
                pointer2.set(i8, pointerUnion2.getWithCheck(i8));
            } else {
                i8++;
                pointer2.setRangePointerUnion(pointerUnion2, i8);
            }
        } else if (i8 != 0) {
            int i14 = for_setpk2_end_move_plus & 63;
            if (i14 != 0) {
                if (((((geMSSEngine.NB_MONOMIAL_PK - i6) + 7) >>> 3) & 7) != 0) {
                    int i15 = i8 - 1;
                    pointer2.setRangePointerUnion(pointerUnion2, i15, i14);
                    pointer2.set(i15, pointerUnion2.get(i15) >>> i14);
                    pointer2.setXor(i15, pointerUnion2.getWithCheck(i8) << (64 - i14));
                } else {
                    pointer2.setRangePointerUnion(pointerUnion2, i8, i14);
                }
            }
            pointer2.setRangePointerUnion(pointerUnion2, i8);
        }
        return pointerUnion.get() & 1;
    }

    private long convMQ_uncompressL_gf2(Pointer pointer, PointerUnion pointerUnion) {
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int for_setpk2_end_move_plus = for_setpk2_end_move_plus(pointer, pointerUnion2, this.HFEnvq);
        int i = this.HFEnvr;
        if (i != 0) {
            setPk2Value(pointer, pointerUnion2, for_setpk2_end_move_plus, this.HFEnvq, i + 1);
        }
        return pointerUnion.get() & 1;
    }

    private void convMonic_gf2nx(Pointer pointer, int i) {
        Pointer pointer2 = new Pointer(this.NB_WORD_GFqn);
        int index = pointer.getIndex();
        pointer.move(this.NB_WORD_GFqn * i);
        inv_gf2n(pointer2, pointer, 0);
        pointer.set1_gf2n(0, this.NB_WORD_GFqn);
        while (true) {
            i--;
            if (i == -1) {
                pointer.changeIndex(index);
                return;
            } else {
                pointer.move(-this.NB_WORD_GFqn);
                mul_gf2n(pointer, pointer, pointer2);
            }
        }
    }

    private void copy_for_casct(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, Pointer pointer5, int i, int i2) {
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        while (i > 1) {
            pointer4.changeIndex(pointer3, (i2 + i) * this.NB_WORD_GFqn);
            CMP_AND_SWAP_CST_TIME(pointer, pointer4, pointer5);
            i >>>= 1;
        }
    }

    private void copy_move_matrix_move(Pointer pointer, Pointer pointer2, int i) {
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer2.move(this.NB_WORD_GFqn);
        pointer.setXorMatrix(pointer2, this.NB_WORD_GFqn, i);
        pointer2.move(this.NB_WORD_GFqn * (this.HFEv + 1));
    }

    private void div_q_monic_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer();
        while (i >= i2) {
            int searchDegree = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (searchDegree < i2) {
                return;
            }
            pointer3.changeIndex(pointer, this.NB_WORD_GFqn * searchDegree);
            int max = Math.max(0, (i2 << 1) - searchDegree);
            pointer4.changeIndex(pointer, ((searchDegree - i2) + max) * this.NB_WORD_GFqn);
            for_mul_rem_xor_move(pointer4, pointer3, pointer2, max, i2);
            i = searchDegree - 1;
        }
    }

    private void div_r_monic_cst_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        int index = pointer.getIndex();
        pointer.move(this.NB_WORD_GFqn * i);
        while (i >= i2) {
            pointer3.changeIndex(pointer, (-i2) * this.NB_WORD_GFqn);
            Pointer pointer4 = pointer;
            for_mul_rem_xor_move(pointer3, pointer4, pointer2, 0, i2);
            pointer4.move(-this.NB_WORD_GFqn);
            i--;
            pointer = pointer4;
        }
        pointer.changeIndex(index);
    }

    private int div_r_monic_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer();
        while (i >= i2) {
            i = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (i < i2) {
                break;
            }
            pointer3.changeIndex(pointer, this.NB_WORD_GFqn * i);
            pointer4.changeIndex(pointer3, (-i2) * this.NB_WORD_GFqn);
            for_mul_rem_xor_move(pointer4, pointer3, pointer2, 0, i2);
            i--;
        }
        if (i == -1) {
            i++;
        }
        return pointer.searchDegree(i, 1, this.NB_WORD_GFqn);
    }

    private void divsqr_r_HFE_cstdeg_gf2nx(Pointer pointer, int i, int i2, int i3, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        Pointer pointer3 = new Pointer(pointer, i * this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer();
        while (i2 >= i3) {
            pointer4.changeIndex(pointer3, (-this.HFEDeg) * this.NB_WORD_GFqn);
            mul_rem_xorrange(pointer4, pointer3, pointer2);
            for (int i4 = 1; i4 < this.NB_COEFS_HFEPOLY; i4++) {
                pointer4.move(complete_sparse_monic_gf2nxVar.L[i4]);
                mul_rem_xorrange(pointer4, pointer3, complete_sparse_monic_gf2nxVar.poly, this.NB_WORD_GFqn * i4);
            }
            pointer3.move(-this.NB_WORD_GFqn);
            i2--;
        }
    }

    private void dotProduct_gf2n(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        Pointer pointer4 = new Pointer(this.NB_WORD_MUL);
        int index = pointer2.getIndex();
        int index2 = pointer3.getIndex();
        mul_move(pointer4, pointer2, pointer3);
        for_mul_xorrange_move(pointer4, pointer2, pointer3, i - 1);
        rem_gf2n(pointer, 0, pointer4);
        pointer2.changeIndex(index);
        pointer3.changeIndex(index2);
    }

    private void dotproduct_move_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        dotProduct_gf2n(pointer, pointer3, pointer2, i);
        pointer.move(this.NB_WORD_GFqn);
        pointer2.move((i + this.HFEv + 1) * this.NB_WORD_GFqn);
    }

    private void evalMQShybrid8_uncomp_nocst_gf2_m(Pointer pointer, Pointer pointer2, PointerUnion pointerUnion, PointerUnion pointerUnion2) {
        PointerUnion pointerUnion3 = new PointerUnion(pointerUnion2);
        evalMQSnocst8_quo_gf2(pointer, pointer2, pointerUnion);
        if (this.HFEmr < 8) {
            pointer.set(this.HFEmq, 0L);
        }
        for (int i = this.HFEmr - this.HFEmr8; i < this.HFEmr; i++) {
            pointer.setXor(this.HFEmq, evalMQnocst_unrolled_no_simd_gf2(pointer2, pointerUnion3) << i);
            pointerUnion3.move(this.NB_WORD_UNCOMP_EQ);
        }
    }

    private void evalMQSnocst8_quo_gf2(Pointer pointer, Pointer pointer2, PointerUnion pointerUnion) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        GeMSSEngine geMSSEngine = this;
        Pointer pointer3 = pointer;
        int i6 = geMSSEngine.HFEnv;
        int i7 = geMSSEngine.HFEm;
        if ((i7 >>> 3) != 0) {
            i7 = (i7 >>> 3) << 3;
        }
        int i8 = i7;
        int i9 = (i8 & 7) != 0 ? (i8 >>> 3) + 1 : i8 >>> 3;
        int i10 = (i9 >>> 3) + ((i9 & 7) != 0 ? 1 : 0);
        int i11 = i6;
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        System.arraycopy(pointerUnion2.getArray(), 0, pointer3.getArray(), pointer3.getIndex(), i10);
        pointerUnion2.moveNextBytes(i9);
        int i12 = 0;
        while (true) {
            i = geMSSEngine.HFEnvq;
            if (i12 >= i) {
                break;
            }
            int i13 = i11;
            long j = pointer2.get(i12);
            int i14 = 0;
            while (i14 < 64) {
                if ((j & 1) != 0) {
                    pointer3.setXorRange(0, pointerUnion2, 0, i10);
                    pointerUnion2.moveNextBytes(i9);
                    i3 = i10;
                    i4 = i9;
                    int i15 = i14;
                    i2 = i15;
                    geMSSEngine.LOOPJR_UNROLLED_64(pointer3, pointerUnion2, i15 + 1, 64, j >>> 1, i4, i3);
                    int i16 = i12 + 1;
                    while (true) {
                        i5 = geMSSEngine.HFEnvq;
                        if (i16 >= i5) {
                            break;
                        }
                        geMSSEngine.LOOPJR_UNROLLED_64(pointer, pointerUnion2, 0, 64, pointer2.get(i16), i4, i3);
                        i16++;
                    }
                    if (geMSSEngine.HFEnvr != 0) {
                        pointer3 = pointer;
                        geMSSEngine.choose_LOOPJR(pointer3, pointerUnion2, 0, pointer2.get(i5), i4, i3);
                        i4 = i4;
                    } else {
                        pointer3 = pointer;
                    }
                } else {
                    i2 = i14;
                    i3 = i10;
                    i4 = i9;
                    pointerUnion2.moveNextBytes(i13 * i4);
                }
                j >>>= 1;
                i14 = i2 + 1;
                i13--;
                i9 = i4;
                i10 = i3;
            }
            i12++;
            i10 = i10;
            i11 = i13;
        }
        int i17 = i10;
        int i18 = i9;
        if (geMSSEngine.HFEnvr != 0) {
            int i19 = i11;
            long j2 = pointer2.get(i);
            int i20 = 0;
            while (i20 < geMSSEngine.HFEnvr) {
                if ((j2 & 1) != 0) {
                    pointer3.setXorRange(0, pointerUnion2, 0, i17);
                    pointerUnion2.moveNextBytes(i18);
                    int i21 = i18;
                    geMSSEngine.choose_LOOPJR(pointer3, pointerUnion2, i20 + 1, j2 >>> 1, i21, i17);
                    i18 = i21;
                } else {
                    pointerUnion2.moveNextBytes(i19 * i18);
                }
                j2 >>>= 1;
                i20++;
                i19--;
                geMSSEngine = this;
            }
        }
        int i22 = i8 & 63;
        if (i22 != 0) {
            pointer3.setAnd(i17 - 1, (1 << i22) - 1);
        }
    }

    private long evalMQnocst_unrolled_no_simd_gf2(Pointer pointer, PointerUnion pointerUnion) {
        int i;
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        long j = pointer.get();
        long j2 = 0;
        for (int i2 = 0; i2 < 64; i2++) {
            if ((1 & (j >>> i2)) != 0) {
                j2 ^= pointerUnion2.get(i2) & j;
            }
        }
        pointerUnion2.move(64);
        int i3 = 1;
        while (true) {
            int i4 = this.NB_WORD_GF2nv;
            if (i3 >= i4) {
                return GeMSSUtils.XORBITS_UINT(j2);
            }
            int i5 = i3 + 1;
            if (i4 != i5 || (i = this.HFEnvr) == 0) {
                i = 64;
            }
            long j3 = pointer.get(i3);
            for (int i6 = 0; i6 < i; i6++) {
                if (((j3 >>> i6) & 1) != 0) {
                    j2 ^= pointerUnion2.getDotProduct(0, pointer, 0, i5);
                }
                pointerUnion2.move(i5);
            }
            i3 = i5;
        }
    }

    private void findRootsSplit_gf2nx(Pointer pointer, Pointer pointer2, int i) {
        int i2;
        int gcd_gf2nx;
        int i3;
        if (i == 1) {
            pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
            return;
        }
        if ((this.HFEn & 1) != 0 && i == 2) {
            findRootsSplit2_HT_gf2nx(pointer, pointer2);
            return;
        }
        int i4 = (i << 1) - 1;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn * i4);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn * i);
        int i5 = i + 1;
        Pointer pointer5 = new Pointer(this.NB_WORD_GFqn * i5);
        Pointer pointer6 = new Pointer(this.NB_WORD_GFqn);
        while (true) {
            pointer3.setRangeClear(0, this.NB_WORD_GFqn * i4);
            pointer4.setRangeClear(0, this.NB_WORD_GFqn * i);
            do {
                pointer4.fillRandom(this.NB_WORD_GFqn, this.random, this.NB_BYTES_GFqn);
                pointer4.setAnd((this.NB_WORD_GFqn << 1) - 1, this.MASK_GF2n);
                i2 = this.NB_WORD_GFqn;
            } while (pointer4.is0_gf2n(i2, i2) != 0);
            pointer5.copyFrom(pointer2, this.NB_WORD_GFqn * i5);
            traceMap_gf2nx(pointer4, pointer3, pointer5, i);
            gcd_gf2nx = gcd_gf2nx(pointer5, i, pointer4, pointer4.searchDegree(i - 1, 1, this.NB_WORD_GFqn));
            i3 = this.buffer;
            if (gcd_gf2nx != 0 && gcd_gf2nx != i) {
                break;
            }
        }
        if (i3 != 0) {
            pointer4.swap(pointer5);
        }
        inv_gf2n(pointer6, pointer5, this.NB_WORD_GFqn * gcd_gf2nx);
        int i6 = this.NB_WORD_GFqn;
        pointer5.set1_gf2n(gcd_gf2nx * i6, i6);
        for_mul(pointer5, pointer6, gcd_gf2nx - 1);
        div_q_monic_gf2nx(pointer2, i, pointer5, gcd_gf2nx);
        findRootsSplit_gf2nx(pointer, pointer5, gcd_gf2nx);
        findRootsSplit_gf2nx(new Pointer(pointer, this.NB_WORD_GFqn * gcd_gf2nx), new Pointer(pointer2, this.NB_WORD_GFqn * gcd_gf2nx), i - gcd_gf2nx);
    }

    private void for_and_xor_shift_incre_move(Pointer pointer, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            pointer.setAnd(j);
            pointer.setXor(1 << i3);
            j = (j << 1) + 1;
            pointer.move(i);
        }
    }

    private void for_casct_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        int i3 = this.NB_WORD_GFqn * i2;
        int i4 = 0;
        while (i4 < i) {
            CMP_AND_SWAP_CST_TIME(pointer, pointer2, pointer3);
            pointer.move(i3);
            pointer2.move(i3);
            i4 += i2;
        }
    }

    private void for_copy_move(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar) {
        for (int i = 1; i < this.NB_COEFS_HFEPOLY; i++) {
            pointer.move(complete_sparse_monic_gf2nxVar.L[i]);
            Pointer pointer2 = complete_sparse_monic_gf2nxVar.poly;
            int i2 = this.NB_WORD_GFqn;
            pointer.copyFrom(0, pointer2, i * i2, i2);
        }
    }

    private void for_mul(Pointer pointer, Pointer pointer2, int i) {
        Pointer pointer3 = new Pointer(pointer, this.NB_WORD_GFqn * i);
        while (i != -1) {
            mul_gf2n(pointer3, pointer3, pointer2);
            pointer3.move(-this.NB_WORD_GFqn);
            i--;
        }
    }

    private void for_mul_rem_xor_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        int i3 = this.NB_WORD_GFqn * i;
        while (i < i2) {
            mul_rem_xorrange(pointer, pointer2, pointer3, i3);
            pointer.move(this.NB_WORD_GFqn);
            i++;
            i3 += this.NB_WORD_GFqn;
        }
    }

    private int for_setPK(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        bArr[i] = (byte) (bArr2[i2] & 3);
        int i4 = 2;
        for (int i5 = 2; i5 < i3; i5++) {
            int i6 = this.HFEnv;
            i4 = setPK(bArr, bArr2, i5, i, i2, i4, i6 - 1, i6 - i5);
        }
        return i4;
    }

    private int for_setpk2_end_move_plus(Pointer pointer, PointerUnion pointerUnion, int i) {
        int i2 = 1;
        int i3 = 0;
        while (i3 < i) {
            Pointer pointer2 = pointer;
            PointerUnion pointerUnion2 = pointerUnion;
            int pk2Value = setPk2Value(pointer2, pointerUnion2, i2, i3, 64);
            setPk2_endValue(pointer2, pointerUnion2, pk2Value, i3);
            i3++;
            pointerUnion2.move(i3);
            pointer2.move(i3);
            i2 = pk2Value + (i3 << 6);
            pointer = pointer2;
            pointerUnion = pointerUnion2;
        }
        return i2;
    }

    private void for_sqr_divsqr(Pointer pointer, int i, int i2, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        while (i < i2) {
            sqr_gf2nx(pointer, this.HFEDeg - 1);
            int i3 = this.HFEDeg;
            divsqr_r_HFE_cstdeg_gf2nx(pointer, (i3 - 1) << 1, (i3 - 1) << 1, i3, complete_sparse_monic_gf2nxVar, pointer2);
            i++;
        }
    }

    private void frobeniusMap_multisqr_HFE_gf2nx(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer(this.HFEDeg * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer();
        Pointer pointer6 = new Pointer(((this.KX * this.HFEDeg) + this.POW_II) * this.NB_WORD_GFqn);
        int i = (this.POW_II * this.KP) - this.HFEDeg;
        Pointer pointer7 = new Pointer(pointer6, this.NB_WORD_GFqn * i);
        pointer7.copyFrom(pointer2, this.NB_WORD_GFqn);
        for_copy_move(pointer7, complete_sparse_monic_gf2nxVar);
        int i2 = i - 1;
        divsqr_r_HFE_cstdeg_gf2nx(pointer6, this.HFEDeg + i2, i2, 0, complete_sparse_monic_gf2nxVar, pointer2);
        int i3 = this.KP + 1;
        while (true) {
            int i4 = this.HFEDeg;
            if (i3 >= i4) {
                break;
            }
            pointer7.changeIndex(pointer6, i4 * this.NB_WORD_GFqn);
            pointer7.setRangeClear(0, this.POW_II * this.NB_WORD_GFqn);
            int i5 = this.POW_II;
            int i6 = this.NB_WORD_GFqn;
            pointer7.copyFrom(i5 * i6, pointer6, 0, this.HFEDeg * i6);
            pointer6.changeIndex(pointer7);
            int i7 = this.POW_II;
            divsqr_r_HFE_cstdeg_gf2nx(pointer6, (i7 - 1) + this.HFEDeg, i7 - 1, 0, complete_sparse_monic_gf2nxVar, pointer2);
            i3++;
        }
        pointer6.indexReset();
        int i8 = (1 << this.HFEDegI) - this.KP;
        int i9 = this.HFEDeg;
        int i10 = this.NB_WORD_GFqn;
        pointer.copyFrom(0, pointer6, i8 * i9 * i10, i9 * i10);
        int i11 = 0;
        while (true) {
            int i12 = this.HFEn;
            int i13 = this.HFEDegI;
            int i14 = this.II;
            if (i11 >= ((i12 - i13) - i14) / i14) {
                for_sqr_divsqr(pointer, 0, (i12 - i13) % i14, complete_sparse_monic_gf2nxVar, pointer2);
                return;
            }
            loop_sqr(pointer4, pointer);
            for (int i15 = 1; i15 < this.II; i15++) {
                loop_sqr(pointer4, pointer4);
            }
            pointer5.changeIndex(pointer4, this.KP * this.NB_WORD_GFqn);
            pointer7.changeIndex(pointer6);
            pointer3.changeIndex(pointer);
            for (int i16 = 0; i16 < this.HFEDeg; i16++) {
                mul_gf2n(pointer3, pointer7, pointer5);
                pointer3.move(this.NB_WORD_GFqn);
                pointer7.move(this.NB_WORD_GFqn);
            }
            for (int i17 = this.KP + 1; i17 < this.HFEDeg; i17++) {
                pointer5.move(this.NB_WORD_GFqn);
                pointer3.changeIndex(pointer);
                for (int i18 = 0; i18 < this.HFEDeg; i18++) {
                    mul_rem_xorrange(pointer3, pointer7, pointer5);
                    pointer3.move(this.NB_WORD_GFqn);
                    pointer7.move(this.NB_WORD_GFqn);
                }
            }
            for (int i19 = 0; i19 < this.KP; i19++) {
                int i20 = this.POW_II * i19;
                int i21 = this.NB_WORD_GFqn;
                pointer.setXorRange(i20 * i21, pointer4, i19 * i21, i21);
            }
            i11++;
        }
    }

    private int gcd_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        int div_r_monic_gf2nx;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        this.buffer = 0;
        int i3 = i;
        Pointer pointer4 = pointer;
        Pointer pointer5 = pointer2;
        while (true) {
            int i4 = i3;
            if (i2 == 0) {
                return i4;
            }
            if ((i2 << 1) > i4) {
                div_r_monic_gf2nx = div_r_gf2nx(pointer4, i4, pointer5, i2);
            } else {
                inv_gf2n(pointer3, pointer5, this.NB_WORD_GFqn * i2);
                int i5 = this.NB_WORD_GFqn;
                pointer5.set1_gf2n(i2 * i5, i5);
                for_mul(pointer5, pointer3, i2 - 1);
                div_r_monic_gf2nx = div_r_monic_gf2nx(pointer4, i4, pointer5, i2);
            }
            this.buffer = 1 - this.buffer;
            Pointer pointer6 = pointer4;
            pointer4 = pointer5;
            pointer5 = pointer6;
            i3 = i2;
            i2 = div_r_monic_gf2nx;
        }
    }

    private void getSHA3Hash(Pointer pointer, int i, int i2, byte[] bArr, int i3, int i4, byte[] bArr2) {
        this.sha3Digest.update(bArr, i3, i4);
        this.sha3Digest.doFinal(bArr2, 0);
        pointer.fill(i, bArr2, 0, i2);
    }

    private void initListDifferences_gf2nx(int[] iArr) {
        iArr[1] = this.NB_WORD_GFqn;
        int i = 2;
        int i2 = 0;
        while (i2 < this.HFEDegI) {
            if (!this.ENABLED_REMOVE_ODD_DEGREE || (1 << i2) + 1 <= this.HFE_odd_degree) {
                iArr[i] = this.NB_WORD_GFqn;
                i = setArrayL(iArr, i + 1, 0, i2);
            } else {
                if (i2 != 0) {
                    iArr[i] = this.NB_WORD_GFqn << 1;
                    i++;
                }
                i = setArrayL(iArr, i, 1, i2);
            }
            i2++;
        }
        int i3 = this.HFEDegJ;
        if (i3 != 0) {
            if (!this.ENABLED_REMOVE_ODD_DEGREE || (1 << i2) + 1 <= this.HFE_odd_degree) {
                iArr[i] = this.NB_WORD_GFqn;
                setArrayL(iArr, i + 1, 0, i3 - 1);
            } else {
                iArr[i] = this.NB_WORD_GFqn << 1;
                setArrayL(iArr, i + 1, 1, i3 - 1);
            }
        }
    }

    private void inv_gf2n(Pointer pointer, Pointer pointer2, int i) {
        int index = pointer2.getIndex();
        pointer2.move(i);
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        for (int i2 = this.HFEn_1rightmost - 1; i2 != -1; i2--) {
            int i3 = (this.HFEn - 1) >>> (i2 + 1);
            sqr_gf2n(pointer3, pointer);
            for (int i4 = 1; i4 < i3; i4++) {
                sqr_gf2n(pointer3, pointer3);
            }
            mul_gf2n(pointer, pointer, pointer3);
            if ((((this.HFEn - 1) >>> i2) & 1) != 0) {
                sqr_gf2n(pointer3, pointer);
                mul_gf2n(pointer, pointer2, pointer3);
            }
        }
        sqr_gf2n(pointer, pointer);
        pointer2.changeIndex(index);
    }

    private void loop_sqr(Pointer pointer, Pointer pointer2) {
        for (int i = 0; i < this.HFEDeg; i++) {
            int i2 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer, i * i2, pointer2, i2 * i);
        }
    }

    private int loop_xor_loop_move_xorandmask_move(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        int i7 = i;
        while (i6 < i3) {
            pointer.setXor(i2, 1 << i6);
            pointer2.changeIndex(pointer);
            pointer3.changeIndex(pointer4);
            for (int i8 = i7; i8 < i4; i8++) {
                pointer2.move(i5);
                pointer3.move((i8 >>> 6) + 1);
                pointer2.setXorRangeAndMask(pointer, i2 + 1, -((pointer3.get() >>> i6) & 1));
            }
            pointer.move(i5);
            pointer4.move(i2 + 1);
            i6++;
            i7++;
        }
        return i7;
    }

    private void mulMatricesLU_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3, FunctionParams functionParams) {
        int i;
        int i2;
        int i3;
        boolean z;
        int index = pointer.getIndex();
        int ordinal = functionParams.ordinal();
        if (ordinal == 0) {
            int i4 = this.HFEnvq;
            i = this.HFEnvr;
            i2 = i4;
            i3 = 1;
            z = i != 0;
        } else {
            if (ordinal != 2) {
                throw new IllegalArgumentException("Invalid parameter for MULMATRICESLU_GF2");
            }
            int i5 = this.HFEnq;
            i = this.HFEnr;
            i2 = i5;
            i3 = 1;
            z = true;
        }
        int i6 = i;
        Pointer pointer4 = new Pointer(pointer2);
        int i7 = i3;
        while (true) {
            int i8 = i6;
            if (i7 > i2) {
                LOOPIR(pointer, pointer4, pointer3, i6, i2, i8, i7, z);
                pointer.changeIndex(index);
                return;
            } else {
                LOOPIR(pointer, pointer4, pointer3, 64, i2, i8, i7, z);
                i7++;
                i6 = i8;
            }
        }
    }

    private void precSignHFE(SecretKeyHFE secretKeyHFE, Pointer[] pointerArr, byte[] bArr) {
        precSignHFESeed(secretKeyHFE, bArr);
        initListDifferences_gf2nx(secretKeyHFE.F_struct.L);
        Pointer pointer = new Pointer(secretKeyHFE.F_HFEv);
        Pointer pointer2 = new Pointer(this.NB_COEFS_HFEPOLY * this.NB_WORD_GFqn);
        Pointer pointer3 = new Pointer(pointer, this.MQv_GFqn_SIZE);
        pointerArr[0] = pointer3;
        pointer.changeIndex(pointer3, this.MLv_GFqn_SIZE);
        Pointer pointer4 = new Pointer(pointer2, this.NB_WORD_GFqn * 2);
        int i = 0;
        while (true) {
            int i2 = 1;
            if (i >= this.HFEDegI) {
                break;
            }
            if ((1 << i) + 1 <= this.HFE_odd_degree || !this.ENABLED_REMOVE_ODD_DEGREE) {
                i2 = 0;
            }
            int i3 = i - i2;
            pointer4.copyFrom(pointer, this.NB_WORD_GFqn * i3);
            pointer.move(this.NB_WORD_GFqn * i3);
            pointer4.move(i3 * this.NB_WORD_GFqn);
            i++;
            pointerArr[i] = new Pointer(pointer);
            pointer.move(this.MLv_GFqn_SIZE);
            pointer4.move(this.NB_WORD_GFqn);
        }
        int i4 = this.HFEDegJ;
        if (i4 != 0) {
            pointer4.copyFrom(pointer, (i4 - ((1 << i) + 1 > this.HFE_odd_degree ? 1 : 0)) * this.NB_WORD_GFqn);
        }
        secretKeyHFE.F_struct.poly = new Pointer(pointer2);
    }

    private void precSignHFESeed(SecretKeyHFE secretKeyHFE, byte[] bArr) {
        int i = this.NB_UINT_HFEVPOLY + ((this.LTRIANGULAR_NV_SIZE + this.LTRIANGULAR_N_SIZE) << 1);
        secretKeyHFE.sk_uncomp = new Pointer(this.MATRIXnv_SIZE + i + this.MATRIXn_SIZE);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(this.ShakeBitStrength);
        sHAKEDigest.update(bArr, 0, this.SIZE_SEED_SK);
        int i2 = i << 3;
        byte[] bArr2 = new byte[i2];
        sHAKEDigest.doFinal(bArr2, 0, i2);
        secretKeyHFE.sk_uncomp.fill(0, bArr2, 0, i2);
        secretKeyHFE.S = new Pointer(secretKeyHFE.sk_uncomp, i);
        secretKeyHFE.T = new Pointer(secretKeyHFE.S, this.MATRIXnv_SIZE);
        secretKeyHFE.F_HFEv = new Pointer(secretKeyHFE.sk_uncomp);
        cleanMonicHFEv_gf2nx(secretKeyHFE.F_HFEv);
        Pointer pointer = new Pointer(secretKeyHFE.sk_uncomp, this.NB_UINT_HFEVPOLY);
        Pointer pointer2 = new Pointer(pointer, this.LTRIANGULAR_NV_SIZE);
        cleanLowerMatrix(pointer, FunctionParams.NV);
        cleanLowerMatrix(pointer2, FunctionParams.NV);
        mulMatricesLU_gf2(secretKeyHFE.S, pointer, pointer2, FunctionParams.NV);
        pointer.move(this.LTRIANGULAR_NV_SIZE << 1);
        pointer2.changeIndex(pointer, this.LTRIANGULAR_N_SIZE);
        cleanLowerMatrix(pointer, FunctionParams.N);
        cleanLowerMatrix(pointer2, FunctionParams.N);
        mulMatricesLU_gf2(secretKeyHFE.T, pointer, pointer2, FunctionParams.N);
    }

    private void rem_gf2n(Pointer pointer, int i, Pointer pointer2) {
        this.rem.rem_gf2n(pointer.array, i + pointer.getIndex(), pointer2.array);
    }

    private static long remainderUnsigned(long j, long j2) {
        return (j <= 0 || j2 <= 0) ? new BigInteger(1, Pack.longToBigEndian(j)).mod(new BigInteger(1, Pack.longToBigEndian(j2))).longValue() : j % j2;
    }

    private int setArrayL(int[] iArr, int i, int i2, int i3) {
        while (i2 < i3) {
            iArr[i] = this.NB_WORD_GFqn << i2;
            i2++;
            i++;
        }
        return i;
    }

    private int setPK(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6) {
        while (i5 >= i6) {
            int i7 = (i4 >>> 3) + i2;
            bArr[i7] = (byte) (bArr[i7] ^ (((bArr2[(i >>> 3) + i3] >>> (i & 7)) & 1) << (i4 & 7)));
            i += i5;
            i5--;
            i4++;
        }
        this.buffer = i;
        return i4;
    }

    private int setPk2Value(Pointer pointer, PointerUnion pointerUnion, int i, int i2, int i3) {
        for (int i4 = 1; i4 < i3; i4++) {
            int i5 = i & 63;
            if (i5 != 0) {
                pointer.setRangePointerUnion(pointerUnion, i2, i5);
                pointer.set(i2, pointerUnion.get(i2) >>> i5);
                int i6 = i5 + i4;
                if (i6 > 64) {
                    pointer.setXor(i2, pointerUnion.get(i2 + 1) << (64 - i5));
                }
                if (i6 >= 64) {
                    pointerUnion.moveIncremental();
                }
            } else {
                pointer.setRangePointerUnion(pointerUnion, i2 + 1);
            }
            pointerUnion.move(i2);
            pointer.setAnd(i2, (1 << i4) - 1);
            pointer.move(i2 + 1);
            i += (i2 << 6) + i4;
        }
        return i;
    }

    private void setPk2_endValue(Pointer pointer, PointerUnion pointerUnion, int i, int i2) {
        int i3 = i & 63;
        int i4 = i2 + 1;
        if (i3 != 0) {
            pointer.setRangePointerUnion(pointerUnion, i4, i3);
        } else {
            pointer.setRangePointerUnion(pointerUnion, i4);
        }
    }

    private void special_buffer(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        int i;
        int index = pointer2.getIndex();
        pointer2.move((this.NB_WORD_GFqn * (this.HFEv + 1)) << 1);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer.move(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(pointer2, this.NB_WORD_GFqn * (this.HFEv + 2));
        int i2 = 2;
        while (i2 < this.SIZE_ROW - 1) {
            copy_move_matrix_move(pointer, pointer4, i2 - 1);
            i2++;
        }
        if (this.ENABLED_REMOVE_ODD_DEGREE) {
            while (i2 < this.SIZE_ROW - 1) {
                copy_move_matrix_move(pointer, pointer4, i2 - 2);
                i2++;
            }
        }
        pointer.set1_gf2n(0, this.NB_WORD_GFqn);
        pointer.setXorMatrix(pointer4, this.NB_WORD_GFqn, this.HFEDegJ);
        for (int i3 = 0; i3 < this.HFEn - 1; i3++) {
            mul_gf2n(pointer, pointer3, pointer2);
            pointer.move(this.NB_WORD_GFqn);
            pointer4.changeIndex(pointer2, this.NB_WORD_GFqn * (this.HFEv + 2));
            int i4 = 2;
            while (i4 < this.HFEDegI) {
                dotproduct_move_move(pointer, pointer4, pointer3, i4);
                i4++;
            }
            if (this.ENABLED_REMOVE_ODD_DEGREE) {
                pointer3.move(this.NB_WORD_GFqn);
                while (i4 < this.SIZE_ROW - 1) {
                    dotproduct_move_move(pointer, pointer4, pointer3, i4 - 1);
                    i4++;
                }
                pointer3.move(-this.NB_WORD_GFqn);
            }
            int i5 = this.HFEDegJ;
            if (i5 == 0) {
                pointer.copyFrom(pointer3, this.NB_WORD_GFqn);
                pointer.move(this.NB_WORD_GFqn);
                i = this.SIZE_ROW;
            } else {
                dotProduct_gf2n(pointer, pointer3, pointer4, i5);
                pointer3.move(this.HFEDegJ * this.NB_WORD_GFqn);
                pointer.setXorRange_SelfMove(pointer3, this.NB_WORD_GFqn);
                i = this.SIZE_ROW - this.HFEDegJ;
            }
            pointer3.move(i * this.NB_WORD_GFqn);
        }
        pointer.indexReset();
        pointer2.changeIndex(index);
        pointer3.indexReset();
    }

    private void sqr_gf2n(Pointer pointer, int i, Pointer pointer2, int i2) {
        this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, pointer2.array, i2 + pointer2.cp);
        rem_gf2n(pointer, i, this.Buffer_NB_WORD_MUL);
    }

    private void sqr_gf2n(Pointer pointer, Pointer pointer2) {
        this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, pointer2.array, pointer2.cp);
        this.rem.rem_gf2n(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
    }

    private void sqr_gf2nx(Pointer pointer, int i) {
        int i2 = this.NB_WORD_GFqn * i;
        int index = pointer.getIndex();
        pointer.move(i2);
        Pointer pointer2 = new Pointer(pointer, i2);
        for (int i3 = 0; i3 < i; i3++) {
            sqr_gf2n(pointer2, pointer);
            pointer.move(-this.NB_WORD_GFqn);
            pointer2.move(-this.NB_WORD_GFqn);
            pointer2.setRangeClear(0, this.NB_WORD_GFqn);
            pointer2.move(-this.NB_WORD_GFqn);
        }
        sqr_gf2n(pointer, pointer);
        pointer.changeIndex(index);
    }

    private void traceMap_gf2nx(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        int i2;
        int i3 = 1;
        while (true) {
            i2 = 1 << i3;
            if (i2 >= i) {
                break;
            }
            int i4 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer, i4 << i3, pointer, i4 << (i3 - 1));
            i3++;
        }
        if (i3 < this.HFEn) {
            int i5 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer2, i5 << i3, pointer, i5 << (i3 - 1));
            div_r_monic_cst_gf2nx(pointer2, i2, pointer3, i);
            pointer.setXorRange(pointer2, this.NB_WORD_GFqn * i);
            for (int i6 = i3 + 1; i6 < this.HFEn; i6++) {
                int i7 = i - 1;
                sqr_gf2nx(pointer2, i7);
                div_r_monic_cst_gf2nx(pointer2, i7 << 1, pointer3, i);
                pointer.setXorRange(pointer2, this.NB_WORD_GFqn * i);
            }
        }
    }

    private void uncompress_signHFE(Pointer pointer, byte[] bArr) {
        PointerUnion pointerUnion = new PointerUnion(pointer);
        int i = (1 << this.HFEnvr8) - 1;
        pointerUnion.fillBytes(0, bArr, 0, this.NB_BYTES_GFqnv);
        if (this.HFEnvr8 != 0) {
            pointerUnion.setAndByte(this.NB_BYTES_GFqnv - 1, i);
        }
        int i2 = this.HFEnv;
        pointerUnion.moveNextBytes((this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7));
        for (int i3 = 1; i3 < this.NB_ITE; i3++) {
            int i4 = i2 & 7;
            int min = Math.min(this.HFEDELTA + this.HFEv, (8 - i4) & 7);
            if (i4 != 0) {
                pointerUnion.setXorByte(((bArr[i2 >>> 3] & UByte.MAX_VALUE) >>> i4) << this.HFEmr8);
                int i5 = min - this.VAL_BITS_M;
                if (i5 >= 0) {
                    pointerUnion.moveNextByte();
                }
                if (i5 > 0) {
                    int i6 = i2 + this.VAL_BITS_M;
                    pointerUnion.setXorByte((bArr[i6 >>> 3] & UByte.MAX_VALUE) >>> (i6 & 7));
                    i2 = i6 + i5;
                } else {
                    i2 += min;
                }
            }
            int i7 = (this.HFEDELTA + this.HFEv) - min;
            int i8 = (this.HFEm + min) & 7;
            if (i8 != 0) {
                for (int i9 = 0; i9 < ((i7 - 1) >>> 3); i9++) {
                    int i10 = i2 >>> 3;
                    pointerUnion.setXorByte((bArr[i10] & UByte.MAX_VALUE) << i8);
                    pointerUnion.moveNextByte();
                    pointerUnion.setXorByte((bArr[i10] & UByte.MAX_VALUE) >>> (8 - i8));
                    i2 += 8;
                }
                int i11 = i2 >>> 3;
                pointerUnion.setXorByte((bArr[i11] & UByte.MAX_VALUE) << i8);
                pointerUnion.moveNextByte();
                int i12 = ((i7 + 7) & 7) + 1;
                int i13 = 8 - i8;
                if (i12 > i13) {
                    pointerUnion.setByte((bArr[i11] & UByte.MAX_VALUE) >>> i13);
                    pointerUnion.moveNextByte();
                }
                i2 += i12;
            } else {
                for (int i14 = 0; i14 < ((i7 + 7) >>> 3); i14++) {
                    pointerUnion.setByte(bArr[i2 >>> 3]);
                    i2 += 8;
                    pointerUnion.moveNextByte();
                }
                i2 -= (8 - (i7 & 7)) & 7;
            }
            if (this.HFEnvr8 != 0) {
                pointerUnion.setAndByte(-1, i);
            }
            pointerUnion.moveNextBytes(((8 - (this.NB_BYTES_GFqnv & 7)) & 7) + (this.HFEmq8 & 7));
        }
    }

    private void vmpv_xorrange_move(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        vecMatProduct(pointer, pointer2, new Pointer(pointer3, this.NB_WORD_GFqn), FunctionParams.V);
        pointer.setXorRange(pointer3, this.NB_WORD_GFqn);
        pointer3.move(this.MLv_GFqn_SIZE);
    }

    void changeVariablesMQS64_gf2(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer();
        int i = this.HFEnv;
        Pointer pointer4 = new Pointer(i * i * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer, this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer(pointer4);
        Pointer pointer7 = new Pointer(pointer2);
        int i2 = 0;
        for (int i3 = 0; i3 < this.HFEnv; i3++) {
            pointer3.changeIndex(pointer5);
            for (int i4 = 0; i4 < this.HFEnvq; i4++) {
                for (int i5 = 0; i5 < 64; i5++) {
                    Pointer pointer8 = pointer6;
                    Pointer pointer9 = pointer3;
                    LOOPKR(pointer9, pointer8, pointer7.get() >>> i5, i5, 64);
                    pointer3 = pointer9;
                    pointer6 = pointer8;
                    LOOPK_COMPLETE(pointer6, pointer7, pointer3, 1, this.HFEnvq - i4);
                }
                pointer7.moveIncremental();
            }
            if (this.HFEnvr != 0) {
                for (int i6 = 0; i6 < this.HFEnvr; i6++) {
                    Pointer pointer10 = pointer6;
                    Pointer pointer11 = pointer3;
                    LOOPKR(pointer11, pointer10, pointer7.get() >>> i6, i6, this.HFEnvr);
                    pointer3 = pointer11;
                    pointer6 = pointer10;
                    pointer6.move(this.NB_WORD_GFqn);
                }
                pointer7.moveIncremental();
            }
        }
        pointer5.changeIndex(pointer4);
        pointer6.changeIndex(pointer, this.NB_WORD_GFqn);
        Pointer pointer12 = new Pointer(pointer2);
        int i7 = 0;
        while (i7 < this.HFEnvq) {
            int i8 = 0;
            while (i8 < 64) {
                pointer7.changeIndex(pointer12);
                Pointer pointer13 = pointer3;
                Pointer pointer14 = pointer5;
                Pointer pointer15 = pointer7;
                LOOPIR_INIT(pointer6, pointer13, pointer14, pointer15, i8, 64);
                Pointer pointer16 = pointer6;
                Pointer pointer17 = pointer14;
                pointer3 = pointer13;
                int i9 = i8;
                for (int i10 = i7 + 1; i10 < this.HFEnvq; i10++) {
                    Pointer pointer18 = pointer3;
                    Pointer pointer19 = pointer17;
                    Pointer pointer20 = pointer16;
                    LOOPIR_INIT(pointer20, pointer18, pointer19, pointer15, 0, 64);
                    pointer16 = pointer20;
                    pointer17 = pointer19;
                    pointer3 = pointer18;
                }
                int i11 = this.HFEnvr;
                if (i11 != 0) {
                    Pointer pointer21 = pointer3;
                    Pointer pointer22 = pointer17;
                    Pointer pointer23 = pointer16;
                    LOOPIR_INIT(pointer23, pointer21, pointer22, pointer15, 0, i11);
                    pointer16 = pointer23;
                    pointer17 = pointer22;
                    pointer3 = pointer21;
                }
                pointer17.changeIndex(pointer3);
                pointer12.move(this.NB_WORD_GF2nv);
                i8 = i9 + 1;
                pointer5 = pointer17;
                pointer6 = pointer16;
                pointer7 = pointer15;
            }
            i7++;
            pointer6 = pointer6;
        }
        Pointer pointer24 = pointer6;
        Pointer pointer25 = pointer5;
        Pointer pointer26 = pointer7;
        if (this.HFEnvr != 0) {
            int i12 = 0;
            while (i12 < this.HFEnvr) {
                pointer26.changeIndex(pointer12);
                pointer3.changeIndex(pointer25);
                Pointer pointer27 = pointer3;
                Pointer pointer28 = pointer25;
                Pointer pointer29 = pointer24;
                LOOPIR_INIT(pointer29, pointer27, pointer28, pointer26, i12, this.HFEnvr);
                pointer3 = pointer27;
                pointer28.changeIndex(pointer3);
                pointer12.move(this.NB_WORD_GF2nv);
                i12++;
                pointer24 = pointer29;
                pointer25 = pointer28;
            }
        }
        Pointer pointer30 = pointer25;
        Pointer pointer31 = pointer24;
        pointer30.changeIndex(pointer4);
        pointer31.changeIndex(pointer, this.NB_WORD_GFqn);
        pointer26.changeIndex(pointer2);
        for (int i13 = 0; i13 < this.HFEnvq; i13++) {
            int i14 = 0;
            while (i14 < 64) {
                pointer31.move(this.NB_WORD_GFqn);
                pointer30.move(this.HFEnv * this.NB_WORD_GFqn);
                pointer3.changeIndex(pointer30);
                int i15 = i14 + 1;
                Pointer pointer32 = pointer26;
                LOOPIR_LOOPK_COMPLETE(pointer31, pointer32, pointer3, i15, 64);
                pointer26 = pointer32;
                for (int i16 = i13 + 1; i16 < this.HFEnvq; i16++) {
                    Pointer pointer33 = pointer26;
                    LOOPIR_LOOPK_COMPLETE(pointer31, pointer33, pointer3, 0, 64);
                    pointer26 = pointer33;
                }
                int i17 = this.HFEnvr;
                if (i17 != 0) {
                    Pointer pointer34 = pointer26;
                    LOOPIR_LOOPK_COMPLETE(pointer31, pointer34, pointer3, 0, i17);
                    pointer26 = pointer34;
                }
                pointer26.move(this.NB_WORD_GF2nv);
                i14 = i15;
            }
        }
        if (this.HFEnvr != 0) {
            while (i2 < this.HFEnvr - 1) {
                pointer31.move(this.NB_WORD_GFqn);
                pointer30.move(this.HFEnv * this.NB_WORD_GFqn);
                pointer3.changeIndex(pointer30);
                i2++;
                Pointer pointer35 = pointer26;
                LOOPIR_LOOPK_COMPLETE(pointer31, pointer35, pointer3, i2, this.HFEnvr);
                pointer35.move(this.NB_WORD_GF2nv);
                pointer26 = pointer35;
            }
        }
        pointer.indexReset();
        pointer2.indexReset();
    }

    void cleanLowerMatrix(Pointer pointer, FunctionParams functionParams) {
        int i;
        int i2;
        int ordinal = functionParams.ordinal();
        if (ordinal == 0) {
            i = this.HFEnvq;
            i2 = this.HFEnvr;
        } else {
            if (ordinal != 2) {
                throw new IllegalArgumentException("");
            }
            i = this.HFEnq;
            i2 = this.HFEnr;
        }
        Pointer pointer2 = new Pointer(pointer);
        int i3 = 1;
        while (i3 <= i) {
            for_and_xor_shift_incre_move(pointer2, i3, 64);
            pointer2.moveIncremental();
            i3++;
        }
        for_and_xor_shift_incre_move(pointer2, i3, i2);
    }

    void cleanMonicHFEv_gf2nx(Pointer pointer) {
        int i = this.NB_WORD_GFqn - 1;
        while (i < this.NB_UINT_HFEVPOLY) {
            pointer.setAnd(i, this.MASK_GF2n);
            i += this.NB_WORD_GFqn;
        }
    }

    public void compress_signHFE(byte[] bArr, Pointer pointer) {
        int i;
        byte[] bytes = pointer.toBytes(pointer.getLength() << 3);
        System.arraycopy(bytes, 0, bArr, 0, this.NB_BYTES_GFqnv);
        int i2 = this.HFEnv;
        int i3 = (this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7);
        for (int i4 = 1; i4 < this.NB_ITE; i4++) {
            int i5 = i2 & 7;
            int min = Math.min(this.HFEDELTA + this.HFEv, (8 - i5) & 7);
            if (i5 != 0) {
                int i6 = this.HFEmr8;
                if (i6 != 0) {
                    int i7 = i2 >>> 3;
                    bArr[i7] = (byte) ((((bytes[i3] & UByte.MAX_VALUE) >>> i6) << i5) ^ bArr[i7]);
                    int i8 = this.VAL_BITS_M;
                    int i9 = min - i8;
                    if (i9 >= 0) {
                        i3++;
                    }
                    if (i9 > 0) {
                        int i10 = i2 + i8;
                        int i11 = i10 >>> 3;
                        bArr[i11] = (byte) (bArr[i11] ^ ((bytes[i3] & UByte.MAX_VALUE) << (i10 & 7)));
                        i2 = i10 + i9;
                    }
                } else {
                    int i12 = i2 >>> 3;
                    bArr[i12] = (byte) (((bytes[i3] & UByte.MAX_VALUE) << i5) ^ bArr[i12]);
                }
                i2 += min;
            }
            int i13 = (this.HFEDELTA + this.HFEv) - min;
            int i14 = (this.HFEm + min) & 7;
            if (i14 != 0) {
                for (int i15 = 0; i15 < ((i13 - 1) >>> 3); i15++) {
                    int i16 = (bytes[i3] & UByte.MAX_VALUE) >>> i14;
                    i3++;
                    bArr[i2 >>> 3] = (byte) (i16 ^ ((bytes[i3] & UByte.MAX_VALUE) << (8 - i14)));
                    i2 += 8;
                }
                int i17 = i2 >>> 3;
                i = i3 + 1;
                byte b = (byte) ((bytes[i3] & UByte.MAX_VALUE) >>> i14);
                bArr[i17] = b;
                int i18 = ((i13 + 7) & 7) + 1;
                int i19 = 8 - i14;
                if (i18 > i19) {
                    bArr[i17] = (byte) (((byte) ((bytes[i] & UByte.MAX_VALUE) << i19)) ^ b);
                    i = i3 + 2;
                }
                i2 += i18;
            } else {
                int i20 = 0;
                while (i20 < ((i13 + 7) >>> 3)) {
                    bArr[i2 >>> 3] = bytes[i3];
                    i2 += 8;
                    i20++;
                    i3++;
                }
                i2 -= (8 - (i13 & 7)) & 7;
                i = i3;
            }
            i3 = ((8 - (this.NB_BYTES_GFqnv & 7)) & 7) + (this.HFEmq8 & 7) + i;
        }
    }

    void convMQS_one_eq_to_hybrid_rep8_comp_gf2(byte[] bArr, PointerUnion pointerUnion, byte[] bArr2) {
        convMQ_UL_gf2(bArr, bArr2, this.HFEmr8);
        int i = 0;
        for (int i2 = 0; i2 < this.NB_MONOMIAL_PK; i2++) {
            i = pointerUnion.toBytesMove(bArr, i, this.HFEmq8);
            if (this.HFEmr8 != 0) {
                pointerUnion.moveNextByte();
            }
        }
    }

    void convMQS_one_eq_to_hybrid_rep8_uncomp_gf2(byte[] bArr, PointerUnion pointerUnion, byte[] bArr2) {
        int i = this.HFEmr8 - 1;
        convMQ_UL_gf2(bArr, bArr2, i);
        int i2 = this.ACCESS_last_equations8;
        int i3 = this.NB_BYTES_EQUATION;
        int i4 = i2 + (i * i3);
        int i5 = i3 * i;
        int for_setPK = for_setPK(bArr, bArr2, i4, i5, this.HFEnv);
        int i6 = this.HFEnv;
        setPK(bArr, bArr2, i6, i4, i5, for_setPK, i6 - 1, this.LOST_BITS);
        int i7 = this.buffer;
        long j = 0;
        for (int i8 = this.LOST_BITS - 1; i8 >= 0; i8--) {
            j ^= ((bArr2[(i7 >>> 3) + i5] >>> (i7 & 7)) & 1) << ((this.LOST_BITS - 1) - i8);
            i7 += i8;
        }
        int i9 = this.ACCESS_last_equations8 - 1;
        for (int i10 = 0; i10 < this.HFEmr8 - 1; i10++) {
            i9 += this.NB_BYTES_EQUATION;
            bArr[i9] = (byte) (bArr[i9] ^ (((byte) (j >>> (this.HFENr8c * i10))) << this.HFENr8));
        }
        pointerUnion.indexReset();
        int i11 = 0;
        for (int i12 = 0; i12 < this.NB_MONOMIAL_PK; i12++) {
            i11 = pointerUnion.toBytesMove(bArr, i11, this.HFEmq8);
            pointerUnion.moveNextByte();
        }
    }

    void convMQS_one_to_last_mr8_equations_gf2(byte[] bArr, PointerUnion pointerUnion) {
        int i;
        pointerUnion.moveNextBytes(this.HFEmq8);
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int i2 = this.NB_MONOMIAL_PK >>> 3;
        int i3 = 0;
        for (int i4 = 0; i4 < this.HFEmr8; i4++) {
            pointerUnion2.changeIndex(pointerUnion);
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    break;
                }
                int i6 = (pointerUnion2.getByte() >>> i4) & 1;
                pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                for (int i7 = 1; i7 < 8; i7++) {
                    i6 ^= ((pointerUnion2.getByte() >>> i4) & 1) << i7;
                    pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                }
                bArr[i3] = (byte) i6;
                i5++;
                i3++;
            }
            if (this.HFENr8 != 0) {
                long withCheck = (pointerUnion2.getWithCheck() >>> i4) & 1;
                pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                for (i = 1; i < this.HFENr8; i++) {
                    withCheck ^= ((pointerUnion2.getWithCheck() >>> i4) & 1) << i;
                    pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                }
                bArr[i3] = (byte) withCheck;
                i3++;
            }
        }
    }

    void convMQ_UL_gf2(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.ACCESS_last_equations8;
            int i4 = this.NB_BYTES_EQUATION;
            for_setPK(bArr, bArr2, i3 + (i2 * i4), i2 * i4, this.HFEnv + 1);
        }
    }

    public int crypto_sign_open(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long j;
        int i;
        long j2;
        PointerUnion pointerUnion = new PointerUnion(bArr);
        int i2 = 0;
        long j3 = 0;
        if (this.HFENr8 == 0 || this.HFEmr8 <= 1) {
            j = 0;
        } else {
            PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
            pointerUnion2.moveNextBytes(this.ACCESS_last_equations8 - 1);
            j = 0;
            for (int i3 = 0; i3 < this.HFEmr8 - 1; i3++) {
                pointerUnion2.moveNextBytes(this.NB_BYTES_EQUATION);
                j ^= ((pointerUnion2.getByte() & 255) >>> this.HFENr8) << (this.HFENr8c * i3);
            }
        }
        if (this.HFEmr8 == 0) {
            Pointer pointer = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
            Pointer pointer2 = new Pointer(new Pointer(this.NB_WORD_GF2nv));
            Pointer pointer3 = new Pointer(this.SIZE_DIGEST_UINT);
            pointer.fill(0, bArr3, 0, this.NB_BYTES_GFqnv);
            getSHA3Hash(pointer3, 0, 64, bArr2, 0, bArr2.length, new byte[64]);
            evalMQSnocst8_quo_gf2(pointer2, pointer, pointerUnion);
            return pointer2.isEqual_nocst_gf2(pointer3, this.NB_WORD_GF2m);
        }
        Pointer pointer4 = new Pointer((this.NB_WORD_UNCOMP_EQ * this.HFEmr8) + 1);
        PointerUnion pointerUnion3 = new PointerUnion(pointerUnion);
        while (i2 < this.HFEmr8 - 1) {
            pointerUnion3.setByteIndex(this.ACCESS_last_equations8 + (this.NB_BYTES_EQUATION * i2));
            j3 ^= convMQ_uncompressL_gf2(new Pointer(pointer4, (this.NB_WORD_UNCOMP_EQ * i2) + 1), pointerUnion3) << i2;
            i2++;
        }
        pointerUnion3.setByteIndex(this.ACCESS_last_equations8 + (this.NB_BYTES_EQUATION * i2));
        long convMQ_last_uncompressL_gf2 = j3 ^ (convMQ_last_uncompressL_gf2(new Pointer(pointer4, (this.NB_WORD_UNCOMP_EQ * i2) + 1), pointerUnion3) << i2);
        if (this.HFENr8 != 0) {
            int i4 = this.HFEnvr;
            if (i4 == 0) {
                i = (i2 + 1) * this.NB_WORD_UNCOMP_EQ;
                j2 = j << (64 - this.LOST_BITS);
            } else {
                int i5 = this.LOST_BITS;
                int i6 = i2 + 1;
                if (i4 > i5) {
                    i = i6 * this.NB_WORD_UNCOMP_EQ;
                    j2 = j << (i4 - i5);
                } else if (i4 == i5) {
                    pointer4.set(i6 * this.NB_WORD_UNCOMP_EQ, j);
                } else {
                    pointer4.setXor((this.NB_WORD_UNCOMP_EQ * i6) - 1, j << (64 - (i5 - i4)));
                    pointer4.set(i6 * this.NB_WORD_UNCOMP_EQ, j >>> (this.LOST_BITS - this.HFEnvr));
                }
            }
            pointer4.setXor(i, j2);
        }
        pointer4.set(convMQ_last_uncompressL_gf2 << (this.HFEmr - this.HFEmr8));
        return sign_openHFE_huncomp_pk(bArr2, bArr2.length, bArr3, pointerUnion, new PointerUnion(pointer4));
    }

    int div_r_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer);
        inv_gf2n(pointer4, pointer2, this.NB_WORD_GFqn * i2);
        while (i >= i2) {
            i = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (i < i2) {
                break;
            }
            pointer5.changeIndex((i - i2) * this.NB_WORD_GFqn);
            mul_gf2n(pointer3, pointer, this.NB_WORD_GFqn * i, pointer4);
            for_mul_rem_xor_move(pointer5, pointer3, pointer2, 0, i2);
            i--;
        }
        return pointer.searchDegree(i, 1, this.NB_WORD_GFqn);
    }

    void evalHFEv_gf2nx(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = new Pointer(this.NB_WORD_MUL);
        Pointer pointer5 = new Pointer(this.NB_WORD_MUL);
        int i = 1;
        Pointer pointer6 = new Pointer((this.HFEDegI + 1) * this.NB_WORD_GFqn);
        Pointer pointer7 = new Pointer();
        int index = pointer2.getIndex();
        Pointer pointer8 = new Pointer(this.NB_WORD_GFqv);
        Pointer pointer9 = new Pointer(pointer6, this.NB_WORD_GFqn);
        pointer6.copyFrom(pointer3, this.NB_WORD_GFqn);
        pointer6.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);
        for (int i2 = 1; i2 <= this.HFEDegI; i2++) {
            sqr_gf2n(pointer9, 0, pointer9, -this.NB_WORD_GFqn);
            pointer9.move(this.NB_WORD_GFqn);
        }
        int i3 = this.NB_WORD_GFqn;
        int i4 = this.NB_WORD_GFqv;
        if (i3 + i4 != this.NB_WORD_GF2nv) {
            i4--;
        }
        int i5 = i4;
        pointer8.setRangeRotate(0, pointer3, i3 - 1, i5, 64 - this.HFEnr);
        int i6 = this.NB_WORD_GFqn;
        if (this.NB_WORD_GFqv + i6 != this.NB_WORD_GF2nv) {
            pointer8.set(i5, pointer3.get((i6 - 1) + i5) >>> this.HFEnr);
        }
        evalMQSv_unrolled_gf2(pointer4, pointer8, pointer2);
        pointer2.move(this.MQv_GFqn_SIZE);
        vmpv_xorrange_move(pointer5, pointer8, pointer2);
        pointer9.changeIndex(pointer6);
        mul_xorrange(pointer4, pointer9, pointer5);
        while (true) {
            int i7 = this.HFEDegI;
            vmpv_xorrange_move(pointer5, pointer8, pointer2);
            if (i >= i7) {
                break;
            }
            int i8 = this.NB_WORD_GFqn;
            pointer5.setRangeClear(i8, this.NB_WORD_MMUL - i8);
            pointer7.changeIndex(pointer9);
            for_mul_xorrange_move(pointer5, pointer2, pointer7, i);
            rem_gf2n(pointer5, 0, pointer5);
            mul_xorrange(pointer4, pointer7, pointer5);
            i++;
        }
        pointer7.changeIndex(pointer9);
        if (this.HFEDegJ != 0) {
            int i9 = this.NB_WORD_GFqn;
            pointer5.setRangeClear(i9, this.NB_WORD_MMUL - i9);
            for_mul_xorrange_move(pointer5, pointer2, pointer7, this.HFEDegJ);
            pointer5.setXorRange(pointer7, this.NB_WORD_GFqn);
            rem_gf2n(pointer5, 0, pointer5);
        } else {
            pointer5.setRangeFromXor(pointer5, pointer7, this.NB_WORD_GFqn);
        }
        pointer9.move(this.HFEDegI * this.NB_WORD_GFqn);
        mul_xorrange(pointer4, pointer9, pointer5);
        rem_gf2n(pointer, 0, pointer4);
        pointer2.changeIndex(index);
    }

    void evalMQSv_unrolled_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = new Pointer(this.HFEv);
        int i = this.HFEv;
        int i2 = i >>> 6;
        int i3 = i & 63;
        int i4 = this.HFEn;
        int i5 = 0;
        int i6 = (i4 >>> 6) + ((i4 & 63) != 0 ? 1 : 0);
        int index = pointer3.getIndex();
        Pointer pointer5 = new Pointer(i6);
        int i7 = 0;
        int i8 = 0;
        while (i7 < i2) {
            i8 = pointer4.setRange_xi(pointer2.get(i7), i8, 64);
            i7++;
        }
        if (i3 != 0) {
            pointer4.setRange_xi(pointer2.get(i7), i8, i3);
        }
        pointer.copyFrom(pointer3, i6);
        pointer3.move(i6);
        while (i5 < this.HFEv) {
            pointer5.copyFrom(pointer3, i6);
            pointer3.move(i6);
            int i9 = i5 + 1;
            int i10 = i9;
            while (i10 < this.HFEv - 3) {
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10 + 1));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10 + 2));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10 + 3));
                i10 += 4;
            }
            while (i10 < this.HFEv) {
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i10));
                i10++;
            }
            pointer.setXorRangeAndMask(pointer5, i6, pointer4.get(i5));
            i5 = i9;
        }
        pointer3.changeIndex(index);
    }

    void fast_sort_gf2n(Pointer pointer, int i) {
        int i2;
        Pointer pointer2;
        int i3;
        Pointer pointer3 = pointer;
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer();
        Pointer pointer7 = new Pointer();
        int i4 = i - 1;
        int Highest_One = GeMSSUtils.Highest_One(i4);
        int i5 = Highest_One;
        while (true) {
            i2 = 0;
            if (i5 <= 1) {
                break;
            }
            int i6 = i5 << 1;
            int i7 = i / i6;
            int max = Math.max(0, (i - (i6 * i7)) - i5);
            pointer6.changeIndex(pointer3);
            pointer7.changeIndex(pointer3, this.NB_WORD_GFqn * i5);
            int i8 = 0;
            while (i8 < i7) {
                for_casct_move(pointer6, pointer7, pointer5, i5, 1);
                int i9 = i5;
                pointer6.move(this.NB_WORD_GFqn * i9);
                pointer7.move(this.NB_WORD_GFqn * i9);
                i8++;
                i5 = i9;
            }
            int i10 = i5;
            for_casct_move(pointer6, pointer7, pointer5, max, 1);
            int i11 = Highest_One;
            while (i11 > i10) {
                while (i2 < i - i11) {
                    if ((i2 & i10) == 0) {
                        pointer7.changeIndex(pointer3, (i2 + i10) * this.NB_WORD_GFqn);
                        Pointer pointer8 = pointer5;
                        Pointer pointer9 = pointer3;
                        int i12 = i11;
                        Pointer pointer10 = pointer6;
                        Pointer pointer11 = pointer4;
                        i3 = i2;
                        copy_for_casct(pointer11, pointer7, pointer9, pointer10, pointer8, i12, i3);
                        pointer2 = pointer11;
                        pointer6 = pointer10;
                        i11 = i12;
                        pointer3 = pointer9;
                        pointer5 = pointer8;
                        pointer7.copyFrom(pointer2, this.NB_WORD_GFqn);
                    } else {
                        pointer2 = pointer4;
                        i3 = i2;
                    }
                    i2 = i3 + 1;
                    pointer4 = pointer2;
                }
                i11 >>>= 1;
                pointer4 = pointer4;
            }
            i5 = i10 >>> 1;
        }
        Pointer pointer12 = pointer4;
        pointer6.changeIndex(pointer3);
        pointer7.changeIndex(pointer3, this.NB_WORD_GFqn);
        for_casct_move(pointer6, pointer7, pointer5, i4, 2);
        pointer7.changeIndex(pointer3, this.NB_WORD_GFqn);
        while (Highest_One > 1) {
            int i13 = i2;
            while (i13 < i - Highest_One) {
                Pointer pointer13 = pointer6;
                Pointer pointer14 = pointer5;
                copy_for_casct(pointer12, pointer7, pointer3, pointer13, pointer14, Highest_One, i13);
                pointer5 = pointer14;
                pointer7.copyFrom(pointer12, this.NB_WORD_GFqn);
                pointer7.move(this.NB_WORD_GFqn << 1);
                i13 += 2;
                pointer6 = pointer13;
                pointer3 = pointer;
            }
            Highest_One >>>= 1;
            pointer3 = pointer;
            pointer6 = pointer6;
            i2 = i13;
        }
    }

    void findRootsSplit2_HT_gf2nx(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        int index = pointer2.getIndex();
        sqr_gf2n(pointer3, 0, pointer2, this.NB_WORD_GFqn);
        inv_gf2n(pointer, pointer3, 0);
        mul_gf2n(pointer3, pointer2, pointer);
        findRootsSplit_x2_x_c_HT_gf2nx(pointer4, pointer3);
        pointer2.move(this.NB_WORD_GFqn);
        mul_gf2n(pointer, pointer4, pointer2);
        int i = this.NB_WORD_GFqn;
        pointer.setRangeFromXor(i, pointer, 0, pointer2, 0, i);
        pointer2.changeIndex(index);
    }

    void findRootsSplit_x2_x_c_HT_gf2nx(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        int i = (this.HFEn + 1) >>> 1;
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        int i2 = 1;
        for (int i3 = this.HFEn1h_rightmost; i3 != -1; i3--) {
            int i4 = i2 << 1;
            sqr_gf2n(pointer3, pointer);
            for (int i5 = 1; i5 < i4; i5++) {
                sqr_gf2n(pointer3, pointer3);
            }
            pointer.setXorRange(pointer3, this.NB_WORD_GFqn);
            i2 = i >>> i3;
            if ((i2 & 1) != 0) {
                sqr_gf2n(pointer3, pointer);
                sqr_gf2n(pointer, pointer3);
                pointer.setXorRange(pointer2, this.NB_WORD_GFqn);
            }
        }
    }

    void for_mul_xorrange_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.mul.mul_gf2x_xor(pointer, pointer2, pointer3);
            pointer2.move(this.NB_WORD_GFqn);
            pointer3.move(this.NB_WORD_GFqn);
        }
    }

    void genSecretMQS_gf2_opt(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        int i = 1;
        Pointer pointer4 = new Pointer((this.HFEDegI + 1) * (this.HFEv + 1) * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer2, this.MQv_GFqn_SIZE);
        for (int i2 = 0; i2 <= this.HFEDegI; i2++) {
            for (int i3 = 0; i3 <= this.HFEv; i3++) {
                int i4 = ((this.HFEDegI + 1) * i3) + i2;
                int i5 = this.NB_WORD_GFqn;
                pointer4.copyFrom(i4 * i5, pointer5, 0, i5);
                pointer5.move(this.NB_WORD_GFqn);
            }
            pointer5.move(this.NB_WORD_GFqn * i2);
        }
        Pointer pointer6 = new Pointer(this.SIZE_ROW * (this.HFEn - 1) * this.NB_WORD_GFqn);
        for (int i6 = 1; i6 < this.HFEn; i6++) {
            pointer6.set(i6 >>> 6, 1 << (i6 & 63));
            for (int i7 = 0; i7 < this.HFEDegI; i7++) {
                sqr_gf2n(pointer6, this.NB_WORD_GFqn, pointer6, 0);
                pointer6.move(this.NB_WORD_GFqn);
            }
            pointer6.move(this.NB_WORD_GFqn);
        }
        pointer6.indexReset();
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer2.move(this.MQv_GFqn_SIZE);
        pointer.move(this.NB_WORD_GFqn);
        Pointer pointer7 = new Pointer(this.HFEDegI * this.HFEn * this.NB_WORD_GFqn);
        special_buffer(pointer7, pointer2, pointer6);
        Pointer pointer8 = new Pointer(pointer7);
        Pointer pointer9 = new Pointer(pointer7);
        pointer.copyFrom(pointer9, this.NB_WORD_GFqn);
        pointer9.move(this.NB_WORD_GFqn);
        pointer.setXorMatrix_NoMove(pointer9, this.NB_WORD_GFqn, this.HFEDegI - 1);
        pointer5.changeIndex(pointer4);
        pointer.setXorMatrix(pointer5, this.NB_WORD_GFqn, this.HFEDegI + 1);
        Pointer pointer10 = new Pointer(pointer6, this.NB_WORD_GFqn);
        int i8 = 1;
        while (i8 < this.HFEn) {
            dotProduct_gf2n(pointer, pointer10, pointer8, this.HFEDegI);
            pointer10.move(this.SIZE_ROW * this.NB_WORD_GFqn);
            pointer.setXorMatrix(pointer9, this.NB_WORD_GFqn, this.HFEDegI);
            i8++;
        }
        while (i8 < this.HFEnv) {
            pointer.copyFrom(pointer5, this.NB_WORD_GFqn);
            pointer5.move(this.NB_WORD_GFqn);
            pointer.setXorMatrix(pointer5, this.NB_WORD_GFqn, this.HFEDegI);
            i8++;
        }
        Pointer pointer11 = new Pointer(pointer6, this.NB_WORD_GFqn);
        Pointer pointer12 = new Pointer(this.NB_WORD_MUL);
        int i9 = 1;
        while (i9 < this.HFEn) {
            pointer8.move(this.HFEDegI * this.NB_WORD_GFqn);
            pointer10.changeIndex(pointer11);
            pointer9.changeIndex(pointer8);
            int i10 = i;
            this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer4, new Pointer(pointer10, -this.NB_WORD_GFqn));
            int i11 = i10;
            while (i11 <= this.HFEDegI) {
                int i12 = this.NB_WORD_GFqn;
                int i13 = i9;
                Pointer pointer13 = pointer9;
                pointer3.setRangeFromXor(0, pointer13, 0, pointer4, i11 * i12, i12);
                mul_xorrange(this.Buffer_NB_WORD_MUL, pointer3, pointer10);
                pointer13.move(this.NB_WORD_GFqn);
                pointer10.move(this.NB_WORD_GFqn);
                i11++;
                pointer9 = pointer13;
                pointer12 = pointer12;
                i9 = i13;
            }
            Pointer pointer14 = pointer12;
            int i14 = i9;
            Pointer pointer15 = pointer9;
            pointer10.move(this.NB_WORD_GFqn);
            rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
            pointer.move(this.NB_WORD_GFqn);
            int i15 = i14 + 1;
            int i16 = i15;
            while (i16 < this.HFEn) {
                int index = pointer10.getIndex();
                int index2 = pointer8.getIndex();
                int index3 = pointer11.getIndex();
                int index4 = pointer15.getIndex();
                mul_move(pointer14, pointer10, pointer8);
                for_mul_xorrange_move(pointer14, pointer10, pointer8, this.HFEDegI - 1);
                for_mul_xorrange_move(pointer14, pointer11, pointer15, this.HFEDegI);
                rem_gf2n(pointer, 0, pointer14);
                pointer10.changeIndex(index + (this.NB_WORD_GFqn * this.SIZE_ROW));
                pointer8.changeIndex(index2);
                pointer11.changeIndex(index3);
                pointer15.changeIndex(index4 + (this.HFEDegI * this.NB_WORD_GFqn));
                pointer.move(this.NB_WORD_GFqn);
                i16++;
                pointer3 = pointer3;
            }
            Pointer pointer16 = pointer3;
            pointer5.changeIndex(pointer4);
            pointer11.move(-this.NB_WORD_GFqn);
            while (i16 < this.HFEnv) {
                pointer5.move((this.HFEDegI + 1) * this.NB_WORD_GFqn);
                dotProduct_gf2n(pointer, pointer11, pointer5, this.HFEDegI + 1);
                pointer.move(this.NB_WORD_GFqn);
                i16++;
            }
            int i17 = this.NB_WORD_GFqn;
            pointer11.move(i17 + (this.SIZE_ROW * i17));
            pointer9 = pointer15;
            i = i10;
            pointer3 = pointer16;
            i9 = i15;
            pointer12 = pointer14;
        }
        pointer2.move(this.NB_WORD_GFqn - this.MQv_GFqn_SIZE);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn * (this.NB_MONOMIAL_VINEGAR - 1));
        pointer.indexReset();
        pointer2.indexReset();
    }

    int interpolateHFE_FS_ref(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer5 = new Pointer();
        Pointer pointer6 = new Pointer();
        Pointer pointer7 = new Pointer(this.HFEnv * this.NB_WORD_GFqn);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        Pointer pointer8 = new Pointer(pointer3);
        Pointer pointer9 = new Pointer(pointer7);
        for (int i = 0; i < this.HFEnv; i++) {
            evalHFEv_gf2nx(pointer9, pointer2, pointer8);
            pointer9.move(this.NB_WORD_GFqn);
            pointer8.move(this.NB_WORD_GF2nv);
        }
        pointer8.changeIndex(pointer3);
        pointer9.changeIndex(pointer7);
        int i2 = 0;
        while (i2 < this.HFEnv) {
            pointer.move(this.NB_WORD_GFqn);
            pointer9.setXorRange(pointer2, this.NB_WORD_GFqn);
            pointer.copyFrom(pointer9, this.NB_WORD_GFqn);
            pointer5.changeIndex(pointer9);
            pointer6.changeIndex(pointer8);
            i2++;
            for (int i3 = i2; i3 < this.HFEnv; i3++) {
                pointer.move(this.NB_WORD_GFqn);
                pointer5.move(this.NB_WORD_GFqn);
                pointer6.move(this.NB_WORD_GF2nv);
                pointer4.setRangeFromXor(pointer8, pointer6, this.NB_WORD_GF2nv);
                evalHFEv_gf2nx(pointer, pointer2, pointer4);
                pointer.setXorRangeXor(0, pointer9, 0, pointer5, 0, this.NB_WORD_GFqn);
            }
            pointer9.move(this.NB_WORD_GFqn);
            pointer8.move(this.NB_WORD_GF2nv);
            pointer = pointer;
        }
        pointer.indexReset();
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void invMatrixLU_gf2(org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r18, org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r19, org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r20, org.bouncycastle.pqc.legacy.crypto.gemss.GeMSSEngine.FunctionParams r21) {
        /*
            r17 = this;
            r0 = r17
            r10 = r18
            r1 = r19
            org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r3 = new org.bouncycastle.pqc.legacy.crypto.gemss.Pointer
            r3.<init>(r1)
            org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r4 = new org.bouncycastle.pqc.legacy.crypto.gemss.Pointer
            r4.<init>(r1)
            org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r11 = new org.bouncycastle.pqc.legacy.crypto.gemss.Pointer
            r1 = r20
            r11.<init>(r1)
            int r1 = r21.ordinal()
            r12 = 0
            r13 = 1
            if (r1 == 0) goto L3b
            r2 = 2
            if (r1 != r2) goto L33
            int r1 = r0.MATRIXn_SIZE
            r10.setRangeClear(r12, r1)
            int r1 = r0.HFEnq
            int r2 = r0.HFEn
            int r2 = r2 - r13
            int r5 = r0.NB_WORD_GFqn
            int r6 = r0.HFEnr
            int r7 = r0.LTRIANGULAR_N_SIZE
            goto L46
        L33:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Invalid Input"
            r1.<init>(r2)
            throw r1
        L3b:
            int r1 = r0.HFEnvq
            int r2 = r0.HFEnv
            int r2 = r2 - r13
            int r5 = r0.NB_WORD_GF2nv
            int r6 = r0.HFEnvr
            int r7 = r0.LTRIANGULAR_NV_SIZE
        L46:
            r14 = r1
            r8 = r2
            r9 = r5
            r15 = r6
            org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r1 = new org.bouncycastle.pqc.legacy.crypto.gemss.Pointer
            r1.<init>(r10)
            org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r2 = new org.bouncycastle.pqc.legacy.crypto.gemss.Pointer
            r2.<init>(r10)
            r5 = r12
            r6 = r5
        L56:
            if (r6 >= r14) goto L6c
            r16 = r7
            r7 = 64
            r12 = r16
            int r5 = r0.loop_xor_loop_move_xorandmask_move(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r4.moveIncremental()
            int r6 = r6 + 1
            r0 = r17
            r7 = r12
            r12 = 0
            goto L56
        L6c:
            r12 = r7
            r16 = r11
            r10 = 1
            if (r15 <= r13) goto L83
            int r7 = r15 + (-1)
            r0 = r17
            r0.loop_xor_loop_move_xorandmask_move(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            long r3 = r10 << r7
            r1.setXor(r6, r3)
        L7f:
            r1.move(r9)
            goto L89
        L83:
            if (r15 != r13) goto L89
            r1.set(r6, r10)
            goto L7f
        L89:
            r0 = r16
            r0.move(r12)
        L8e:
            if (r8 <= 0) goto Lba
            int r3 = r8 >>> 6
            int r3 = (-1) - r3
            r0.move(r3)
            int r3 = -r9
            r1.move(r3)
            r3 = r18
            r2.changeIndex(r3)
            r4 = 0
        La1:
            if (r4 >= r8) goto Lb7
            int r5 = r4 >>> 6
            long r5 = r0.get(r5)
            r7 = r4 & 63
            long r5 = r5 >>> r7
            long r5 = r5 & r10
            long r5 = -r5
            r2.setXorRangeAndMask(r1, r9, r5)
            r2.move(r9)
            int r4 = r4 + 1
            goto La1
        Lb7:
            int r8 = r8 + (-1)
            goto L8e
        Lba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.legacy.crypto.gemss.GeMSSEngine.invMatrixLU_gf2(org.bouncycastle.pqc.legacy.crypto.gemss.Pointer, org.bouncycastle.pqc.legacy.crypto.gemss.Pointer, org.bouncycastle.pqc.legacy.crypto.gemss.Pointer, org.bouncycastle.pqc.legacy.crypto.gemss.GeMSSEngine$FunctionParams):void");
    }

    void mul_gf2n(Pointer pointer, Pointer pointer2, int i, Pointer pointer3) {
        int index = pointer2.getIndex();
        pointer2.move(i);
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        pointer2.changeIndex(index);
        rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
    }

    void mul_gf2n(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
    }

    void mul_move(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(pointer, pointer2, pointer3);
        pointer2.move(this.NB_WORD_GFqn);
        pointer3.move(this.NB_WORD_GFqn);
    }

    public void mul_rem_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        this.rem.rem_gf2n_xor(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
    }

    public void mul_rem_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        int index = pointer3.getIndex();
        pointer3.move(i);
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        this.rem.rem_gf2n_xor(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
        pointer3.changeIndex(index);
    }

    public void mul_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x_xor(pointer, pointer2, pointer3);
    }

    public void signHFE_FeistelPatarin(SecureRandom secureRandom, byte[] bArr, byte[] bArr2, int i, int i2, byte[] bArr3) {
        int i3;
        int i4;
        long j;
        long j2;
        long j3;
        PointerUnion pointerUnion;
        Pointer pointer;
        int i5;
        Pointer pointer2;
        Pointer pointer3;
        Pointer pointer4;
        SecureRandom secureRandom2 = secureRandom;
        this.random = secureRandom2;
        Pointer pointer5 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer(this.SIZE_DIGEST_UINT);
        Pointer pointer7 = new Pointer(new Pointer(this.SIZE_DIGEST_UINT));
        int i6 = this.HFEv;
        int i7 = i6 & 7;
        int i8 = (i6 >>> 3) + (i7 != 0 ? 1 : 0);
        long maskUINT = GeMSSUtils.maskUINT(this.HFEvr);
        SecretKeyHFE secretKeyHFE = new SecretKeyHFE(this);
        Pointer pointer8 = new Pointer(this.NB_WORD_GFqv);
        Pointer[] pointerArr = new Pointer[this.HFEDegI + 1];
        precSignHFE(secretKeyHFE, pointerArr, bArr3);
        Pointer pointer9 = new Pointer(secretKeyHFE.F_struct.poly);
        Pointer pointer10 = new Pointer(pointer6);
        int i9 = this.Sha3BitStrength >>> 3;
        byte[] bArr4 = new byte[i9];
        Pointer pointer11 = pointer7;
        Pointer pointer12 = pointer10;
        Pointer pointer13 = pointer9;
        long j4 = maskUINT;
        getSHA3Hash(pointer12, 0, i9, bArr2, i, i2, bArr4);
        Pointer pointer14 = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
        Pointer pointer15 = new Pointer(this.NB_WORD_GF2nv);
        PointerUnion pointerUnion2 = new PointerUnion(pointer15);
        long j5 = 0;
        int i10 = 1;
        while (true) {
            i3 = this.NB_ITE;
            if (i10 > i3) {
                break;
            }
            pointer15.setRangeFromXor(pointer14, pointer12, this.NB_WORD_GF2m);
            if (this.HFEmr8 != 0) {
                i4 = i7;
                pointer15.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
                j5 = pointerUnion2.getByte(this.HFEmq8);
            } else {
                i4 = i7;
            }
            long j6 = j5;
            while (true) {
                if (this.HFEmr8 != 0) {
                    pointerUnion2.fillRandomBytes(this.HFEmq8, secureRandom2, (this.NB_BYTES_GFqn - this.NB_BYTES_GFqm) + 1);
                    pointerUnion2.setAndThenXorByte(this.HFEmq8, -(1 << this.HFEmr8), j6);
                    j = j6;
                } else {
                    j = j6;
                    int i11 = this.NB_BYTES_GFqm;
                    pointerUnion2.fillRandomBytes(i11, secureRandom2, this.NB_BYTES_GFqn - i11);
                }
                if ((this.HFEn & 7) != 0) {
                    j2 = j;
                    pointer15.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);
                } else {
                    j2 = j;
                }
                vecMatProduct(pointer5, pointer15, secretKeyHFE.T, FunctionParams.N);
                pointer8.fillRandom(0, secureRandom2, i8);
                if (i4 != 0) {
                    j3 = j4;
                    pointer8.setAnd(this.NB_WORD_GFqv - 1, j3);
                } else {
                    j3 = j4;
                }
                Pointer pointer16 = pointer13;
                evalMQSv_unrolled_gf2(pointer16, pointer8, secretKeyHFE.F_HFEv);
                pointerUnion = pointerUnion2;
                int i12 = 0;
                while (i12 <= this.HFEDegI) {
                    int i13 = i12;
                    Pointer pointer17 = pointer16;
                    vecMatProduct(this.Buffer_NB_WORD_GFqn, pointer8, new Pointer(pointerArr[i13], this.NB_WORD_GFqn), FunctionParams.V);
                    int i14 = this.NB_WORD_GFqn;
                    i12 = i13 + 1;
                    pointer17.setRangeFromXor(i14 * (((i13 * i12) >>> 1) + 1), pointerArr[i13], 0, this.Buffer_NB_WORD_GFqn, 0, i14);
                    pointer16 = pointer17;
                }
                pointer13 = pointer16;
                if (chooseRootHFE_gf2nx(pointer15, secretKeyHFE.F_struct, pointer5) != 0) {
                    break;
                }
                secureRandom2 = secureRandom;
                j6 = j2;
                pointerUnion2 = pointerUnion;
                j4 = j3;
            }
            pointer15.setXor(this.NB_WORD_GFqn - 1, pointer8.get() << this.HFEnr);
            Pointer pointer18 = pointer15;
            pointer18.setRangeRotate(this.NB_WORD_GFqn, pointer8, 0, this.NB_WORD_GFqv - 1, 64 - this.HFEnr);
            int i15 = this.NB_WORD_GFqn;
            int i16 = this.NB_WORD_GFqv;
            if (i15 + i16 == this.NB_WORD_GF2nv) {
                pointer18.set((i15 + i16) - 1, pointer8.get(i16 - 1) >>> (64 - this.HFEnr));
            }
            vecMatProduct(pointer14, pointer18, secretKeyHFE.S, FunctionParams.NV);
            int i17 = this.NB_ITE;
            if (i10 != i17) {
                int i18 = this.NB_WORD_GF2nv;
                int i19 = this.NB_WORD_GF2nvm;
                int i20 = (((i17 - 1) - i10) * i19) + i18;
                pointer14.copyFrom(i20, pointer14, i18 - i19, i19);
                if (this.HFEmr != 0) {
                    pointer14.setAnd(i20, ~this.MASK_GF2m);
                }
                byte[] bytes = pointer12.toBytes(this.SIZE_DIGEST);
                pointer = pointer14;
                i5 = i10;
                pointer2 = pointer18;
                pointer3 = pointer12;
                pointer4 = pointer11;
                getSHA3Hash(pointer4, 0, this.SIZE_DIGEST, bytes, 0, bytes.length, bytes);
                pointer4.swap(pointer3);
            } else {
                pointer = pointer14;
                i5 = i10;
                pointer2 = pointer18;
                pointer3 = pointer12;
                pointer4 = pointer11;
            }
            i10 = i5 + 1;
            j5 = j2;
            pointer11 = pointer4;
            pointer12 = pointer3;
            pointer14 = pointer;
            pointer15 = pointer2;
            pointerUnion2 = pointerUnion;
            secureRandom2 = secureRandom;
            j4 = j3;
            i7 = i4;
        }
        Pointer pointer19 = pointer14;
        if (i3 == 1) {
            System.arraycopy(pointer19.toBytes(pointer19.getLength() << 3), 0, bArr, 0, this.NB_BYTES_GFqnv);
        } else {
            compress_signHFE(bArr, pointer19);
        }
    }

    public int sign_openHFE_huncomp_pk(byte[] bArr, int i, byte[] bArr2, PointerUnion pointerUnion, PointerUnion pointerUnion2) {
        Pointer pointer = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
        Pointer pointer2 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer3 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer4 = new Pointer(pointer2);
        Pointer pointer5 = new Pointer(pointer3);
        byte[] bArr3 = new byte[64];
        Pointer pointer6 = new Pointer(this.NB_ITE * this.SIZE_DIGEST_UINT);
        long j = pointerUnion2.get();
        pointerUnion2.move(1);
        uncompress_signHFE(pointer, bArr2);
        getSHA3Hash(pointer6, 0, 64, bArr, 0, i, bArr3);
        int i2 = 1;
        while (i2 < this.NB_ITE) {
            int i3 = i2;
            getSHA3Hash(pointer6, this.SIZE_DIGEST_UINT * i2, 64, bArr3, 0, this.SIZE_DIGEST, bArr3);
            pointer6.setAnd(((this.SIZE_DIGEST_UINT * (i3 - 1)) + this.NB_WORD_GF2m) - 1, this.MASK_GF2m);
            i2 = i3 + 1;
        }
        pointer6.setAnd(((this.SIZE_DIGEST_UINT * (i2 - 1)) + this.NB_WORD_GF2m) - 1, this.MASK_GF2m);
        evalMQShybrid8_uncomp_nocst_gf2_m(pointer4, pointer, pointerUnion, pointerUnion2);
        pointer4.setXor(this.HFEmq, j);
        for (int i4 = this.NB_ITE - 1; i4 > 0; i4--) {
            pointer4.setXorRange(pointer6, this.SIZE_DIGEST_UINT * i4, this.NB_WORD_GF2m);
            int i5 = this.NB_WORD_GF2nv + (((this.NB_ITE - 1) - i4) * this.NB_WORD_GF2nvm);
            pointer4.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
            pointer4.setXor(this.NB_WORD_GF2m - 1, pointer.get(i5));
            int i6 = this.NB_WORD_GF2nvm;
            if (i6 != 1) {
                pointer4.copyFrom(this.NB_WORD_GF2m, pointer, i5 + 1, i6 - 1);
            }
            evalMQShybrid8_uncomp_nocst_gf2_m(pointer5, pointer4, pointerUnion, pointerUnion2);
            pointer5.setXor(this.HFEmq, j);
            pointer5.swap(pointer4);
        }
        return pointer4.isEqual_nocst_gf2(pointer6, this.NB_WORD_GF2m);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ab A[LOOP:2: B:26:0x00a9->B:27:0x00ab, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void vecMatProduct(org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r20, org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r21, org.bouncycastle.pqc.legacy.crypto.gemss.Pointer r22, org.bouncycastle.pqc.legacy.crypto.gemss.GeMSSEngine.FunctionParams r23) {
        /*
            Method dump skipped, instructions count: 203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.legacy.crypto.gemss.GeMSSEngine.vecMatProduct(org.bouncycastle.pqc.legacy.crypto.gemss.Pointer, org.bouncycastle.pqc.legacy.crypto.gemss.Pointer, org.bouncycastle.pqc.legacy.crypto.gemss.Pointer, org.bouncycastle.pqc.legacy.crypto.gemss.GeMSSEngine$FunctionParams):void");
    }
}
