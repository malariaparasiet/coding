package org.bouncycastle.pqc.crypto.sphincs;

import kotlin.UByte;
import okhttp3.internal.ws.RealWebSocket;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.sphincs.Tree;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
public class SPHINCS256Signer implements MessageSigner {
    private final HashFunctions hashFunctions;
    private byte[] keyData;

    public SPHINCS256Signer(Digest digest, Digest digest2) {
        if (digest.getDigestSize() != 32) {
            throw new IllegalArgumentException("n-digest needs to produce 32 bytes of output");
        }
        if (digest2.getDigestSize() != 64) {
            throw new IllegalArgumentException("2n-digest needs to produce 64 bytes of output");
        }
        this.hashFunctions = new HashFunctions(digest, digest2);
    }

    static void compute_authpath_wots(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int i, Tree.leafaddr leafaddrVar, byte[] bArr3, byte[] bArr4, int i2) {
        Tree.leafaddr leafaddrVar2 = new Tree.leafaddr(leafaddrVar);
        byte[] bArr5 = new byte[2048];
        byte[] bArr6 = new byte[1024];
        byte[] bArr7 = new byte[68608];
        long j = 0;
        leafaddrVar2.subleaf = 0L;
        while (leafaddrVar2.subleaf < 32) {
            Seed.get_seed(hashFunctions, bArr6, (int) (leafaddrVar2.subleaf * 32), bArr3, leafaddrVar2);
            leafaddrVar2.subleaf++;
        }
        HashFunctions hashFunctions2 = hashFunctions;
        Wots wots = new Wots();
        leafaddrVar2.subleaf = 0L;
        while (leafaddrVar2.subleaf < 32) {
            HashFunctions hashFunctions3 = hashFunctions2;
            byte[] bArr8 = bArr7;
            wots.wots_pkgen(hashFunctions3, bArr8, (int) (leafaddrVar2.subleaf * 2144), bArr6, (int) (leafaddrVar2.subleaf * 32), bArr4, 0);
            bArr7 = bArr8;
            leafaddrVar2.subleaf++;
            hashFunctions2 = hashFunctions;
        }
        while (true) {
            leafaddrVar2.subleaf = j;
            if (leafaddrVar2.subleaf >= 32) {
                break;
            }
            Tree.l_tree(hashFunctions, bArr5, (int) ((leafaddrVar2.subleaf * 32) + RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE), bArr7, (int) (leafaddrVar2.subleaf * 2144), bArr4, 0);
            j = leafaddrVar2.subleaf + 1;
        }
        int i3 = 0;
        for (int i4 = 32; i4 > 0; i4 >>>= 1) {
            for (int i5 = 0; i5 < i4; i5 += 2) {
                hashFunctions.hash_2n_n_mask(bArr5, ((i5 >>> 1) * 32) + ((i4 >>> 1) * 32), bArr5, (i4 * 32) + (i5 * 32), bArr4, (i3 + 7) * 64);
            }
            i3++;
        }
        int i6 = (int) leafaddrVar.subleaf;
        for (int i7 = 0; i7 < i2; i7++) {
            System.arraycopy(bArr5, ((32 >>> i7) * 32) + (((i6 >>> i7) ^ 1) * 32), bArr2, i + (i7 * 32), 32);
        }
        System.arraycopy(bArr5, 32, bArr, 0, 32);
    }

    static void validate_authpath(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if ((i & 1) != 0) {
            for (int i4 = 0; i4 < 32; i4++) {
                bArr5[i4 + 32] = bArr2[i4];
            }
            for (int i5 = 0; i5 < 32; i5++) {
                bArr5[i5] = bArr3[i2 + i5];
            }
        } else {
            for (int i6 = 0; i6 < 32; i6++) {
                bArr5[i6] = bArr2[i6];
            }
            for (int i7 = 0; i7 < 32; i7++) {
                bArr5[i7 + 32] = bArr3[i2 + i7];
            }
        }
        int i8 = i2 + 32;
        int i9 = i;
        int i10 = 0;
        while (i10 < i3 - 1) {
            int i11 = i9 >>> 1;
            if ((i11 & 1) != 0) {
                hashFunctions.hash_2n_n_mask(bArr5, 32, bArr5, 0, bArr4, (i10 + 7) * 64);
                for (int i12 = 0; i12 < 32; i12++) {
                    bArr5[i12] = bArr3[i8 + i12];
                }
            } else {
                hashFunctions.hash_2n_n_mask(bArr5, 0, bArr5, 0, bArr4, (i10 + 7) * 64);
                for (int i13 = 0; i13 < 32; i13++) {
                    bArr5[i13 + 32] = bArr3[i8 + i13];
                }
            }
            i8 += 32;
            i10++;
            i9 = i11;
        }
        hashFunctions.hash_2n_n_mask(bArr, 0, bArr5, 0, bArr4, (i3 + 6) * 64);
    }

    private void zerobytes(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 != i2; i3++) {
            bArr[i + i3] = 0;
        }
    }

    byte[] crypto_sign(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[41000];
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[64];
        long[] jArr = new long[8];
        byte[] bArr6 = new byte[32];
        byte[] bArr7 = new byte[32];
        byte[] bArr8 = new byte[1024];
        byte[] bArr9 = new byte[1088];
        for (int i = 0; i < 1088; i++) {
            bArr9[i] = bArr2[i];
        }
        System.arraycopy(bArr9, 1056, bArr3, 40968, 32);
        Digest messageHash = hashFunctions.getMessageHash();
        byte[] bArr10 = new byte[messageHash.getDigestSize()];
        messageHash.update(bArr3, 40968, 32);
        messageHash.update(bArr, 0, bArr.length);
        messageHash.doFinal(bArr10, 0);
        zerobytes(bArr3, 40968, 32);
        for (int i2 = 0; i2 != 8; i2++) {
            jArr[i2] = Pack.littleEndianToLong(bArr10, i2 * 8);
        }
        long j = jArr[0] & 1152921504606846975L;
        System.arraycopy(bArr10, 16, bArr4, 0, 32);
        System.arraycopy(bArr4, 0, bArr3, 39912, 32);
        Tree.leafaddr leafaddrVar = new Tree.leafaddr();
        leafaddrVar.level = 11;
        leafaddrVar.subtree = 0L;
        leafaddrVar.subleaf = 0L;
        System.arraycopy(bArr9, 32, bArr3, 39944, 1024);
        HashFunctions hashFunctions2 = hashFunctions;
        Tree.treehash(hashFunctions2, bArr3, 40968, 5, bArr9, leafaddrVar, bArr3, 39944);
        byte[] bArr11 = bArr9;
        Digest messageHash2 = hashFunctions2.getMessageHash();
        messageHash2.update(bArr3, 39912, 1088);
        messageHash2.update(bArr, 0, bArr.length);
        messageHash2.doFinal(bArr5, 0);
        Tree.leafaddr leafaddrVar2 = new Tree.leafaddr();
        leafaddrVar2.level = 12;
        leafaddrVar2.subleaf = (int) (r19 & 31);
        leafaddrVar2.subtree = j >>> 5;
        int i3 = 32;
        for (int i4 = 0; i4 < 32; i4++) {
            bArr3[i4] = bArr4[i4];
        }
        System.arraycopy(bArr11, 32, bArr8, 0, 1024);
        int i5 = 0;
        while (i5 < 8) {
            bArr3[i3 + i5] = (byte) ((j >>> (i5 * 8)) & 255);
            i5++;
            i3 = 32;
        }
        Seed.get_seed(hashFunctions2, bArr7, 0, bArr11, leafaddrVar2);
        new Horst();
        byte[] bArr12 = bArr8;
        byte[] bArr13 = bArr6;
        byte[] bArr14 = bArr7;
        int i6 = 0;
        int horst_sign = 40 + Horst.horst_sign(hashFunctions2, bArr3, 40, bArr13, bArr14, bArr12, bArr5);
        Wots wots = new Wots();
        int i7 = 0;
        while (i7 < 12) {
            leafaddrVar2.level = i7;
            Seed.get_seed(hashFunctions2, bArr14, i6, bArr11, leafaddrVar2);
            byte[] bArr15 = bArr3;
            HashFunctions hashFunctions3 = hashFunctions2;
            Wots wots2 = wots;
            byte[] bArr16 = bArr12;
            byte[] bArr17 = bArr14;
            byte[] bArr18 = bArr13;
            int i8 = horst_sign;
            wots2.wots_sign(hashFunctions3, bArr15, i8, bArr18, bArr17, bArr16);
            byte[] bArr19 = bArr11;
            Tree.leafaddr leafaddrVar3 = leafaddrVar2;
            compute_authpath_wots(hashFunctions, bArr18, bArr15, i8 + 2144, leafaddrVar3, bArr19, bArr16, 5);
            bArr13 = bArr18;
            bArr3 = bArr15;
            horst_sign = i8 + 2304;
            leafaddrVar3.subleaf = (int) (leafaddrVar3.subtree & 31);
            leafaddrVar3.subtree >>>= 5;
            i7++;
            leafaddrVar2 = leafaddrVar3;
            bArr11 = bArr19;
            bArr12 = bArr16;
            bArr14 = bArr17;
            wots = wots2;
            i6 = 0;
            hashFunctions2 = hashFunctions;
        }
        zerobytes(bArr11, i6, 1088);
        return bArr3;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        return crypto_sign(this.hashFunctions, bArr, this.keyData);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.keyData = ((SPHINCSPublicKeyParameters) cipherParameters).getKeyData();
        } else if (cipherParameters instanceof ParametersWithRandom) {
            this.keyData = ((SPHINCSPrivateKeyParameters) ((ParametersWithRandom) cipherParameters).getParameters()).getKeyData();
        } else {
            this.keyData = ((SPHINCSPrivateKeyParameters) cipherParameters).getKeyData();
        }
    }

    boolean verify(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[2144];
        byte[] bArr5 = new byte[32];
        byte[] bArr6 = new byte[32];
        byte[] bArr7 = new byte[41000];
        byte[] bArr8 = new byte[1056];
        if (bArr2.length != 41000) {
            throw new IllegalArgumentException("signature wrong size");
        }
        byte[] bArr9 = new byte[64];
        for (int i = 0; i < 1056; i++) {
            bArr8[i] = bArr3[i];
        }
        byte[] bArr10 = new byte[32];
        for (int i2 = 0; i2 < 32; i2++) {
            bArr10[i2] = bArr2[i2];
        }
        System.arraycopy(bArr2, 0, bArr7, 0, 41000);
        Digest messageHash = hashFunctions.getMessageHash();
        messageHash.update(bArr10, 0, 32);
        messageHash.update(bArr8, 0, 1056);
        messageHash.update(bArr, 0, bArr.length);
        messageHash.doFinal(bArr9, 0);
        long j = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            j ^= (bArr7[32 + i3] & UByte.MAX_VALUE) << (i3 * 8);
        }
        new Horst();
        Horst.horst_verify(hashFunctions, bArr6, bArr7, 40, bArr8, bArr9);
        Wots wots = new Wots();
        int i4 = 13352;
        int i5 = 0;
        while (i5 < 12) {
            wots.wots_verify(hashFunctions, bArr4, bArr7, i4, bArr6, bArr8);
            int i6 = i4;
            byte[] bArr11 = bArr4;
            byte[] bArr12 = bArr5;
            Tree.l_tree(hashFunctions, bArr12, 0, bArr11, 0, bArr8, 0);
            bArr4 = bArr11;
            byte[] bArr13 = bArr7;
            byte[] bArr14 = bArr6;
            validate_authpath(hashFunctions, bArr14, bArr12, (int) (31 & j), bArr13, i6 + 2144, bArr8, 5);
            bArr7 = bArr13;
            bArr6 = bArr14;
            j >>= 5;
            i4 = i6 + 2304;
            i5++;
            bArr5 = bArr12;
            wots = wots;
        }
        boolean z = true;
        for (int i7 = 0; i7 < 32; i7++) {
            z &= bArr6[i7] == bArr8[i7 + 1024];
        }
        return z;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        return verify(this.hashFunctions, bArr, bArr2, this.keyData);
    }
}
