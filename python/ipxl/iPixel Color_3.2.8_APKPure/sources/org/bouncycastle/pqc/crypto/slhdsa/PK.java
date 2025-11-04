package org.bouncycastle.pqc.crypto.slhdsa;

/* loaded from: classes4.dex */
class PK {
    final byte[] root;
    final byte[] seed;

    PK(byte[] bArr, byte[] bArr2) {
        this.seed = bArr;
        this.root = bArr2;
    }
}
