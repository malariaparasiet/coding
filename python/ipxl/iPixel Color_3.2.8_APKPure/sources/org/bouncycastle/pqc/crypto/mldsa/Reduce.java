package org.bouncycastle.pqc.crypto.mldsa;

/* loaded from: classes4.dex */
class Reduce {
    Reduce() {
    }

    static int conditionalAddQ(int i) {
        return i + ((i >> 31) & 8380417);
    }

    static int montgomeryReduce(long j) {
        return (int) ((j - (((int) (58728449 * j)) * 8380417)) >>> 32);
    }

    static int reduce32(int i) {
        return i - (((4194304 + i) >> 23) * 8380417);
    }
}
