package org.bouncycastle.pqc.crypto.mayo;

import java.security.SecureRandom;
import kotlin.UByte;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;
import org.bouncycastle.util.GF16;
import org.bouncycastle.util.Longs;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
public class MayoSigner implements MessageSigner {
    private static final long EVEN_2BYTES = 281470681808895L;
    private static final long EVEN_BYTES = 71777214294589695L;
    private static final int F_TAIL_LEN = 4;
    private MayoParameters params;
    private MayoPrivateKeyParameters privKey;
    private MayoPublicKeyParameters pubKey;
    private SecureRandom random;

    private static long ctCompare64(int i, int i2) {
        return (-(i ^ i2)) >> 63;
    }

    private static void mVecMultiplyBins(int i, int i2, long[] jArr, long[] jArr2) {
        int i3 = i + i;
        int i4 = i3 + i;
        int i5 = i4 + i;
        int i6 = i5 + i;
        int i7 = i6 + i;
        int i8 = i7 + i;
        int i9 = i8 + i;
        int i10 = i9 + i;
        int i11 = i10 + i;
        int i12 = i11 + i;
        int i13 = i12 + i;
        int i14 = i13 + i;
        int i15 = i14 + i;
        int i16 = i15 + i;
        int i17 = 0;
        int i18 = 0;
        while (i17 < i2) {
            int i19 = i17;
            int i20 = i18;
            int i21 = 0;
            while (i21 < i) {
                long j = jArr[i20 + i6];
                long j2 = j & 1229782938247303441L;
                long j3 = (jArr[i20 + i11] ^ ((j & (-1229782938247303442L)) >>> 1)) ^ ((j2 << 3) + j2);
                long j4 = jArr[i20 + i12];
                long j5 = (j4 & (-8608480567731124088L)) >>> 3;
                long j6 = (jArr[i20 + i13] ^ ((j4 & 8608480567731124087L) << 1)) ^ ((j5 << 1) + j5);
                long j7 = j3 & 1229782938247303441L;
                long j8 = (jArr[i20 + i8] ^ ((j3 & (-1229782938247303442L)) >>> 1)) ^ ((j7 << 3) + j7);
                long j9 = (j6 & (-8608480567731124088L)) >>> 3;
                long j10 = (jArr[i20 + i7] ^ ((j6 & 8608480567731124087L) << 1)) ^ ((j9 << 1) + j9);
                long j11 = j8 & 1229782938247303441L;
                long j12 = (jArr[i20 + i15] ^ ((j8 & (-1229782938247303442L)) >>> 1)) ^ ((j11 << 3) + j11);
                long j13 = (j10 & (-8608480567731124088L)) >>> 3;
                long j14 = (jArr[i20 + i4] ^ ((j10 & 8608480567731124087L) << 1)) ^ ((j13 << 1) + j13);
                long j15 = j12 & 1229782938247303441L;
                long j16 = (jArr[i20 + i16] ^ ((j12 & (-1229782938247303442L)) >>> 1)) ^ ((j15 << 3) + j15);
                long j17 = (j14 & (-8608480567731124088L)) >>> 3;
                long j18 = (jArr[i20 + i9] ^ ((j14 & 8608480567731124087L) << 1)) ^ ((j17 << 1) + j17);
                long j19 = j16 & 1229782938247303441L;
                long j20 = (jArr[i20 + i14] ^ ((j16 & (-1229782938247303442L)) >>> 1)) ^ ((j19 << 3) + j19);
                long j21 = (j18 & (-8608480567731124088L)) >>> 3;
                long j22 = (jArr[i20 + i5] ^ ((j18 & 8608480567731124087L) << 1)) ^ ((j21 << 1) + j21);
                long j23 = j20 & 1229782938247303441L;
                long j24 = (jArr[i20 + i10] ^ ((j20 & (-1229782938247303442L)) >>> 1)) ^ ((j23 << 3) + j23);
                long j25 = (j22 & (-8608480567731124088L)) >>> 3;
                long j26 = (jArr[i20 + i3] ^ ((j22 & 8608480567731124087L) << 1)) ^ ((j25 << 1) + j25);
                long j27 = j24 & 1229782938247303441L;
                long j28 = (jArr[i20 + i] ^ ((j24 & (-1229782938247303442L)) >>> 1)) ^ ((j27 << 3) + j27);
                long j29 = (j26 & (-8608480567731124088L)) >>> 3;
                jArr2[(i18 >> 4) + i21] = (j28 ^ ((j26 & 8608480567731124087L) << 1)) ^ ((j29 << 1) + j29);
                i21++;
                i20++;
            }
            i17 = i19 + 1;
            i18 += i << 4;
        }
    }

    private static void mayoGenericMCalculatePS(MayoParameters mayoParameters, long[] jArr, int i, int i2, byte[] bArr, int i3, int i4, int i5, long[] jArr2) {
        int i6 = i3;
        int i7 = i4;
        int i8 = i7 + i6;
        int mVecLimbs = mayoParameters.getMVecLimbs();
        long[] jArr3 = new long[(((mayoParameters.getK() * mVecLimbs) * mayoParameters.getN()) * mVecLimbs) << 4];
        int i9 = i7 * mVecLimbs;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i10 < i6) {
            for (int i14 = i10; i14 < i6; i14++) {
                int i15 = 0;
                int i16 = 0;
                while (i15 < i5) {
                    Longs.xorTo(mVecLimbs, jArr, i12, jArr3, (((i13 + i15) << 4) + (bArr[i16 + i14] & 255)) * mVecLimbs);
                    i15++;
                    i16 += i8;
                }
                i12 += mVecLimbs;
            }
            int i17 = i11;
            int i18 = 0;
            while (i18 < i7) {
                int i19 = 0;
                int i20 = 0;
                while (i19 < i5) {
                    Longs.xorTo(mVecLimbs, jArr, i + i17, jArr3, (((i13 + i19) << 4) + (bArr[i20 + i18 + i3] & 255)) * mVecLimbs);
                    i19++;
                    i20 += i8;
                }
                i18++;
                i17 += mVecLimbs;
                i7 = i4;
            }
            i10++;
            i13 += i5;
            i11 += i9;
            i6 = i3;
            i7 = i4;
        }
        int i21 = i3 * i5;
        int i22 = 0;
        int i23 = i3;
        while (i23 < i8) {
            for (int i24 = i23; i24 < i8; i24++) {
                int i25 = 0;
                int i26 = 0;
                while (i25 < i5) {
                    Longs.xorTo(mVecLimbs, jArr, i2 + i22, jArr3, (((i21 + i25) << 4) + (bArr[i26 + i24] & 255)) * mVecLimbs);
                    i25++;
                    i26 += i8;
                }
                i22 += mVecLimbs;
            }
            i23++;
            i21 += i5;
        }
        mVecMultiplyBins(mVecLimbs, i8 * i5, jArr3, jArr2);
    }

    private static void mayoGenericMCalculateSPS(long[] jArr, byte[] bArr, int i, int i2, int i3, long[] jArr2) {
        int i4 = i2;
        int i5 = i4 * i4;
        long[] jArr3 = new long[(i * i5) << 4];
        int i6 = i4 * i;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < i4) {
            int i10 = 0;
            int i11 = 0;
            while (i10 < i3) {
                int i12 = ((bArr[i8 + i10] & 255) * i) + i9;
                int i13 = 0;
                int i14 = 0;
                while (i13 < i4) {
                    Longs.xorTo(i, jArr, i11 + i14, jArr3, i12 + (i14 << 4));
                    i13++;
                    i14 += i;
                    i4 = i2;
                }
                i10++;
                i11 += i6;
                i4 = i2;
            }
            i7++;
            i8 += i3;
            i9 += i6 << 4;
            i4 = i2;
        }
        mVecMultiplyBins(i, i5, jArr3, jArr2);
    }

    private static int mulTable(int i) {
        int i2 = i * 134480385;
        int i3 = (-252645136) & i2;
        return (i2 ^ (i3 >>> 4)) ^ (i3 >>> 3);
    }

    private static void transpose16x16Nibbles(long[] jArr, int i) {
        for (int i2 = 0; i2 < 16; i2 += 2) {
            int i3 = i + i2;
            int i4 = i3 + 1;
            long j = jArr[i3];
            long j2 = ((j >>> 4) ^ jArr[i4]) & 1085102592571150095L;
            jArr[i3] = j ^ (j2 << 4);
            jArr[i4] = jArr[i4] ^ j2;
        }
        int i5 = i;
        for (int i6 = 0; i6 < 16; i6 += 4) {
            long j3 = jArr[i5];
            long j4 = ((j3 >>> 8) ^ jArr[i5 + 2]) & EVEN_BYTES;
            int i7 = i5 + 1;
            long j5 = EVEN_BYTES & ((jArr[i7] >>> 8) ^ jArr[i5 + 3]);
            jArr[i5] = j3 ^ (j4 << 8);
            int i8 = i5 + 2;
            jArr[i7] = jArr[i7] ^ (j5 << 8);
            int i9 = i5 + 3;
            jArr[i8] = jArr[i8] ^ j4;
            i5 += 4;
            jArr[i9] = jArr[i9] ^ j5;
        }
        for (int i10 = 0; i10 < 4; i10++) {
            int i11 = i + i10;
            long j6 = jArr[i11];
            int i12 = i11 + 4;
            long j7 = ((j6 >>> 16) ^ jArr[i12]) & EVEN_2BYTES;
            int i13 = i11 + 8;
            int i14 = i11 + 12;
            long j8 = EVEN_2BYTES & ((jArr[i13] >>> 16) ^ jArr[i14]);
            jArr[i11] = j6 ^ (j7 << 16);
            jArr[i13] = jArr[i13] ^ (j8 << 16);
            jArr[i12] = jArr[i12] ^ j7;
            jArr[i14] = jArr[i14] ^ j8;
        }
        for (int i15 = 0; i15 < 8; i15++) {
            int i16 = i + i15;
            long j9 = jArr[i16];
            int i17 = i16 + 8;
            long j10 = ((j9 >>> 32) ^ jArr[i17]) & 4294967295L;
            jArr[i16] = j9 ^ (j10 << 32);
            jArr[i17] = jArr[i17] ^ j10;
        }
    }

    private static void vecMulAddU64(int i, long[] jArr, byte b, long[] jArr2) {
        int mulTable = mulTable(b & UByte.MAX_VALUE);
        for (int i2 = 0; i2 < i; i2++) {
            long j = jArr[i2];
            jArr2[i2] = ((((j >>> 3) & 1229782938247303441L) * ((mulTable >>> 24) & 15)) ^ ((((j & 1229782938247303441L) * (mulTable & 255)) ^ (((j >>> 1) & 1229782938247303441L) * ((mulTable >>> 8) & 15))) ^ (((j >>> 2) & 1229782938247303441L) * ((mulTable >>> 16) & 15)))) ^ jArr2[i2];
        }
    }

    private static void vecMulAddU64(int i, long[] jArr, byte b, long[] jArr2, int i2) {
        int mulTable = mulTable(b & UByte.MAX_VALUE);
        for (int i3 = 0; i3 < i; i3++) {
            long j = jArr[i3];
            int i4 = i2 + i3;
            jArr2[i4] = ((((j >>> 3) & 1229782938247303441L) * ((mulTable >>> 24) & 15)) ^ ((((j & 1229782938247303441L) * (mulTable & 255)) ^ (((j >>> 1) & 1229782938247303441L) * ((mulTable >>> 8) & 15))) ^ (((j >>> 2) & 1229782938247303441L) * ((mulTable >>> 16) & 15)))) ^ jArr2[i4];
        }
    }

    void computeA(long[] jArr, byte[] bArr) {
        char c;
        int i;
        char c2;
        int i2;
        int k = this.params.getK();
        int o = this.params.getO();
        int m = this.params.getM();
        int mVecLimbs = this.params.getMVecLimbs();
        int aCols = this.params.getACols();
        int[] fTail = this.params.getFTail();
        int i3 = o * k;
        int i4 = o * mVecLimbs;
        int i5 = 4;
        int i6 = ((i3 + 15) >> 4) << 4;
        long[] jArr2 = new long[(((m + 7) >>> 3) * i6) << 4];
        int i7 = m & 15;
        if (i7 != 0) {
            long j = (1 << (i7 << 2)) - 1;
            int i8 = mVecLimbs - 1;
            c = 3;
            int i9 = 0;
            while (i9 < i3) {
                jArr[i8] = jArr[i8] & j;
                i9++;
                i8 += mVecLimbs;
            }
        } else {
            c = 3;
        }
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i10 < k) {
            int i15 = k - 1;
            int i16 = i15 * i4;
            int i17 = i15 * o;
            int i18 = i15;
            while (i18 >= i10) {
                int i19 = 0;
                int i20 = 0;
                while (true) {
                    i2 = i5;
                    if (i19 >= o) {
                        break;
                    }
                    int i21 = 0;
                    int i22 = 0;
                    while (i21 < mVecLimbs) {
                        long j2 = jArr[i16 + i21 + i20];
                        int i23 = i11 + i19 + i14 + i22;
                        jArr2[i23] = jArr2[i23] ^ (j2 << i13);
                        if (i13 > 0) {
                            int i24 = i23 + i6;
                            jArr2[i24] = jArr2[i24] ^ (j2 >>> (64 - i13));
                        }
                        i21++;
                        i22 += i6;
                    }
                    i19++;
                    i20 += mVecLimbs;
                    i5 = i2;
                }
                if (i10 != i18) {
                    int i25 = 0;
                    int i26 = 0;
                    while (i25 < o) {
                        int i27 = 0;
                        int i28 = 0;
                        while (i27 < mVecLimbs) {
                            long j3 = jArr[i12 + i27 + i26];
                            int i29 = i17 + i25 + i14 + i28;
                            jArr2[i29] = jArr2[i29] ^ (j3 << i13);
                            if (i13 > 0) {
                                int i30 = i29 + i6;
                                jArr2[i30] = jArr2[i30] ^ (j3 >>> (64 - i13));
                            }
                            i27++;
                            i28 += i6;
                        }
                        i25++;
                        i26 += mVecLimbs;
                    }
                }
                int i31 = i13 + 4;
                if (i31 == 64) {
                    i14 += i6;
                    i13 = 0;
                } else {
                    i13 = i31;
                }
                i18--;
                i16 -= i4;
                i17 -= o;
                i5 = i2;
            }
            i10++;
            i11 += o;
            i12 += i4;
        }
        int i32 = i5;
        int i33 = 0;
        while (true) {
            i = (k + 1) * k;
            if (i33 >= ((((i >> 1) + m) + 15) >>> 4) * i6) {
                break;
            }
            transpose16x16Nibbles(jArr2, i33);
            i33 += 16;
        }
        byte[] bArr2 = new byte[16];
        int i34 = 0;
        int i35 = 0;
        while (true) {
            c2 = 1;
            if (i34 >= i32) {
                break;
            }
            int i36 = fTail[i34];
            bArr2[i35] = (byte) GF16.mul(i36, 1);
            bArr2[i35 + 1] = (byte) GF16.mul(i36, 2);
            int i37 = i35 + 3;
            bArr2[i35 + 2] = (byte) GF16.mul(i36, 4);
            i35 += 4;
            bArr2[i37] = (byte) GF16.mul(i36, 8);
            i34++;
            i32 = 4;
        }
        int i38 = 0;
        while (i38 < i6) {
            int i39 = m;
            while (i39 < (i >>> 1) + m) {
                long j4 = jArr2[((i39 >>> 4) * i6) + i38 + (i39 & 15)];
                long j5 = j4 & 1229782938247303441L;
                long j6 = (j4 >>> c2) & 1229782938247303441L;
                long j7 = (j4 >>> 2) & 1229782938247303441L;
                long j8 = (j4 >>> c) & 1229782938247303441L;
                int i40 = 0;
                int i41 = 0;
                while (i40 < 4) {
                    int i42 = (i39 + i40) - m;
                    int i43 = ((i42 >> 4) * i6) + i38 + (i42 & 15);
                    jArr2[i43] = jArr2[i43] ^ ((((bArr2[r23 + 1] * j6) ^ (bArr2[i41] * j5)) ^ (r30[r23 + 2] * j7)) ^ (r30[r23 + 3] * j8));
                    i40++;
                    i41 += 4;
                    bArr2 = bArr2;
                }
                i39++;
                c2 = 1;
            }
            i38 += 16;
            c2 = 1;
        }
        byte[] longToLittleEndian = Pack.longToLittleEndian(jArr2);
        for (int i44 = 0; i44 < m; i44 += 16) {
            int i45 = 0;
            while (true) {
                int i46 = aCols - 1;
                if (i45 < i46) {
                    int i47 = 0;
                    while (true) {
                        int i48 = i47 + i44;
                        if (i48 < m) {
                            GF16.decode(longToLittleEndian, ((((i44 * i6) >> 4) + i45) + i47) << 3, bArr, (i48 * aCols) + i45, Math.min(16, i46 - i45));
                            i47++;
                        }
                    }
                    i45 += 16;
                }
            }
        }
    }

    void computeRHS(long[] jArr, byte[] bArr, byte[] bArr2) {
        int i;
        int[] iArr;
        int m = this.params.getM();
        int mVecLimbs = this.params.getMVecLimbs();
        int k = this.params.getK();
        int[] fTail = this.params.getFTail();
        int i2 = ((m - 1) & 15) << 2;
        int i3 = m & 15;
        int i4 = 0;
        if (i3 != 0) {
            long j = (1 << (i3 << 2)) - 1;
            int i5 = k * k;
            int i6 = mVecLimbs - 1;
            int i7 = 0;
            while (i7 < i5) {
                jArr[i6] = jArr[i6] & j;
                i7++;
                i6 += mVecLimbs;
            }
        }
        long[] jArr2 = new long[mVecLimbs];
        byte[] bArr3 = new byte[mVecLimbs << 3];
        int i8 = k * mVecLimbs;
        int i9 = k - 1;
        int i10 = i9 * mVecLimbs;
        int i11 = i10 * k;
        while (i9 >= 0) {
            int i12 = i9;
            int i13 = i10;
            int i14 = i11;
            while (i12 < k) {
                int i15 = mVecLimbs - 1;
                long j2 = jArr2[i15];
                int i16 = i12;
                int i17 = (int) ((j2 >>> i2) & 15);
                jArr2[i15] = j2 << 4;
                for (int i18 = mVecLimbs - 2; i18 >= 0; i18--) {
                    int i19 = i18 + 1;
                    jArr2[i19] = jArr2[i19] ^ (jArr2[i18] >>> 60);
                    jArr2[i18] = jArr2[i18] << 4;
                }
                Pack.longToLittleEndian(jArr2, bArr3, i4);
                int i20 = i4;
                for (int i21 = 4; i20 < i21; i21 = 4) {
                    int i22 = fTail[i20];
                    if (i22 == 0) {
                        i = k;
                        iArr = fTail;
                    } else {
                        i = k;
                        iArr = fTail;
                        long mul = GF16.mul(i17, i22);
                        if ((i20 & 1) == 0) {
                            int i23 = i20 >> 1;
                            bArr3[i23] = (byte) (bArr3[i23] ^ ((byte) (mul & 15)));
                        } else {
                            int i24 = i20 >> 1;
                            bArr3[i24] = (byte) (bArr3[i24] ^ ((byte) ((mul & 15) << 4)));
                        }
                    }
                    i20++;
                    k = i;
                    fTail = iArr;
                }
                int i25 = k;
                int[] iArr2 = fTail;
                Pack.littleEndianToLong(bArr3, 0, jArr2);
                int i26 = i11 + i13;
                int i27 = i14 + i10;
                boolean z = i9 == i16;
                for (int i28 = 0; i28 < mVecLimbs; i28++) {
                    long j3 = jArr[i26 + i28];
                    if (!z) {
                        j3 ^= jArr[i27 + i28];
                    }
                    jArr2[i28] = jArr2[i28] ^ j3;
                }
                i12 = i16 + 1;
                i13 += mVecLimbs;
                i14 += i8;
                k = i25;
                fTail = iArr2;
                i4 = 0;
            }
            i9--;
            i10 -= mVecLimbs;
            i11 -= i8;
            i4 = 0;
        }
        Pack.longToLittleEndian(jArr2, bArr3, i4);
        while (i4 < m) {
            int i29 = i4 >> 1;
            bArr2[i4] = (byte) (bArr[i4] ^ (bArr3[i29] & 15));
            int i30 = i4 + 1;
            bArr2[i30] = (byte) (((bArr3[i29] >>> 4) & 15) ^ bArr[i30]);
            i4 += 2;
        }
    }

    void ef(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5 = (i2 + 15) >> 4;
        long[] jArr = new long[i5];
        long[] jArr2 = new long[i5];
        long[] jArr3 = new long[i * i5];
        int i6 = 16;
        int o = (this.params.getO() * this.params.getK()) + 16;
        byte[] bArr2 = new byte[o >> 1];
        int i7 = o >> 4;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 < i) {
            int i11 = 0;
            while (i11 < i5) {
                long j = 0;
                int i12 = 0;
                while (i12 < i6) {
                    if ((i11 << 4) + i12 < i2) {
                        i3 = i12;
                        i4 = i8;
                        j |= (bArr[r9 + i9] & 15) << (i3 << 2);
                    } else {
                        i3 = i12;
                        i4 = i8;
                    }
                    i12 = i3 + 1;
                    i8 = i4;
                    i6 = 16;
                }
                jArr3[i11 + i10] = j;
                i11++;
                i6 = 16;
            }
            i8++;
            i9 += i2;
            i10 += i5;
            i6 = 16;
        }
        int i13 = 0;
        int i14 = 0;
        while (i13 < i2) {
            int max = Math.max(0, (i13 + i) - i2);
            int i15 = i - 1;
            int min = Math.min(i15, i13);
            Arrays.clear(jArr);
            Arrays.clear(jArr2);
            int min2 = Math.min(i15, min + 32);
            int i16 = max * i5;
            int i17 = i16;
            int i18 = 0;
            int i19 = i7;
            int i20 = i13;
            long j2 = -1;
            while (max <= min2) {
                long j3 = ~ctCompare64(max, i14);
                long j4 = (i14 - max) >> 63;
                for (int i21 = 0; i21 < i5; i21++) {
                    jArr[i21] = jArr[i21] ^ ((j3 | (j4 & j2)) & jArr3[i17 + i21]);
                }
                i18 = (int) ((jArr[i20 >>> 4] >>> ((i20 & 15) << 2)) & 15);
                j2 = ~((-i18) >> 63);
                max++;
                i17 += i5;
            }
            vecMulAddU64(i5, jArr, GF16.inv((byte) i18), jArr2);
            int i22 = i16;
            int i23 = max;
            while (i23 <= min) {
                int i24 = i22;
                int i25 = i18;
                int i26 = i23;
                long j5 = (~j2) & (~ctCompare64(i23, i14));
                long j6 = ~j5;
                int i27 = i24;
                int i28 = 0;
                while (i28 < i5) {
                    jArr3[i27] = (j6 & jArr3[i27]) | (j5 & jArr2[i28]);
                    i28++;
                    i27++;
                }
                i23 = i26 + 1;
                i22 = i24 + i5;
                i18 = i25;
            }
            int i29 = i18;
            int i30 = max;
            while (i30 < i) {
                vecMulAddU64(i5, jArr2, (byte) (((int) ((jArr3[(i20 >>> 4) + i16] >>> ((i20 & 15) << 2)) & 15)) & (i30 > i14 ? -1 : 0)), jArr3, i16);
                i30++;
                i16 += i5;
            }
            if (i29 != 0) {
                i14++;
            }
            i13 = i20 + 1;
            i7 = i19;
        }
        int i31 = i7;
        int i32 = 0;
        int i33 = 0;
        int i34 = 0;
        while (i34 < i) {
            Pack.longToLittleEndian(jArr3, i32, i31, bArr2, 0);
            GF16.decode(bArr2, 0, bArr, i33, i2);
            i33 += i2;
            i34++;
            i32 += i5;
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        byte[] bArr5;
        byte[] bArr6;
        byte[] bArr7;
        byte[] bArr8;
        byte[] bArr9;
        byte[] bArr10;
        byte[] bArr11;
        byte[] bArr12;
        long[] jArr;
        int i;
        byte[] bArr13;
        int i2;
        byte[] bArr14;
        int i3;
        int i4;
        byte[] bArr15;
        byte[] bArr16;
        byte[] bArr17;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        long[] jArr2;
        int i10;
        int k = this.params.getK();
        int v = this.params.getV();
        int o = this.params.getO();
        int n = this.params.getN();
        int m = this.params.getM();
        int vBytes = this.params.getVBytes();
        int oBytes = this.params.getOBytes();
        int saltBytes = this.params.getSaltBytes();
        int mVecLimbs = this.params.getMVecLimbs();
        int p1Limbs = this.params.getP1Limbs();
        int pkSeedBytes = this.params.getPkSeedBytes();
        int digestBytes = this.params.getDigestBytes();
        int skSeedBytes = this.params.getSkSeedBytes();
        byte[] bArr18 = new byte[this.params.getMBytes()];
        byte[] bArr19 = new byte[m];
        byte[] bArr20 = new byte[saltBytes];
        byte[] bArr21 = new byte[m];
        int i11 = k * vBytes;
        int rBytes = this.params.getRBytes() + i11;
        int i12 = i11;
        int i13 = v * k;
        int i14 = k;
        byte[] bArr22 = new byte[i13];
        int i15 = i14 * o;
        int i16 = i14 * n;
        byte[] bArr23 = bArr22;
        int i17 = i15 + 1;
        byte[] bArr24 = new byte[rBytes];
        byte[] bArr25 = new byte[((m + 7) / 8) * 8 * i17];
        byte[] bArr26 = new byte[i16];
        byte[] bArr27 = new byte[i16];
        int i18 = digestBytes + saltBytes;
        int i19 = i16;
        int i20 = i18 + skSeedBytes;
        byte[] bArr28 = new byte[i17];
        int i21 = i20 + 1;
        int i22 = rBytes;
        byte[] bArr29 = new byte[i21];
        int i23 = i21;
        int sigBytes = this.params.getSigBytes();
        byte[] bArr30 = new byte[sigBytes];
        long[] jArr3 = new long[p1Limbs + this.params.getP2Limbs()];
        int i24 = v * o;
        byte[] bArr31 = new byte[i24];
        byte[] bArr32 = bArr18;
        long[] jArr4 = new long[i15 * mVecLimbs];
        long[] jArr5 = new long[i14 * i14 * mVecLimbs];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        try {
            byte[] seedSk = this.privKey.getSeedSk();
            int i25 = pkSeedBytes + oBytes;
            byte[] bArr33 = new byte[i25];
            byte[] bArr34 = bArr20;
            try {
                sHAKEDigest.update(seedSk, 0, seedSk.length);
                sHAKEDigest.doFinal(bArr33, 0, i25);
                GF16.decode(bArr33, pkSeedBytes, bArr31, 0, i24);
                Utils.expandP1P2(this.params, jArr3, bArr33);
                int i26 = o * mVecLimbs;
                int i27 = 0;
                int i28 = 0;
                int i29 = 0;
                int i30 = 0;
                while (i27 < v) {
                    int i31 = i28;
                    int i32 = i29;
                    int i33 = i30;
                    int i34 = i27;
                    while (i34 < v) {
                        if (i34 == i27) {
                            i33 += mVecLimbs;
                            i9 = i26;
                            jArr2 = jArr3;
                            i10 = mVecLimbs;
                        } else {
                            i9 = i26;
                            int i35 = p1Limbs;
                            int i36 = 0;
                            while (i36 < o) {
                                long[] jArr6 = jArr3;
                                GF16Utils.mVecMulAdd(mVecLimbs, jArr6, i33, bArr31[i31 + i36], jArr3, i29 + i35);
                                int i37 = mVecLimbs;
                                GF16Utils.mVecMulAdd(i37, jArr6, i33, bArr31[i28 + i36], jArr6, i32 + i35);
                                i36++;
                                i35 += i37;
                                mVecLimbs = i37;
                                jArr3 = jArr6;
                            }
                            jArr2 = jArr3;
                            i10 = mVecLimbs;
                            i33 += i10;
                        }
                        i34++;
                        i31 += o;
                        i32 += i9;
                        mVecLimbs = i10;
                        jArr3 = jArr2;
                        i26 = i9;
                    }
                    i27++;
                    i28 += o;
                    i29 += i26;
                    jArr3 = jArr3;
                    i30 = i33;
                }
                long[] jArr7 = jArr3;
                int i38 = mVecLimbs;
                Arrays.fill(bArr33, (byte) 0);
                sHAKEDigest.update(bArr, 0, bArr.length);
                sHAKEDigest.doFinal(bArr29, 0, digestBytes);
                try {
                    this.random.nextBytes(bArr34);
                    int i39 = saltBytes;
                    System.arraycopy(bArr34, 0, bArr29, digestBytes, i39);
                    System.arraycopy(seedSk, 0, bArr29, i18, skSeedBytes);
                    int i40 = i20;
                    sHAKEDigest.update(bArr29, 0, i40);
                    sHAKEDigest.doFinal(bArr34, 0, i39);
                    System.arraycopy(bArr34, 0, bArr29, digestBytes, i39);
                    sHAKEDigest.update(bArr29, 0, i18);
                    byte[] bArr35 = bArr32;
                    try {
                        sHAKEDigest.doFinal(bArr35, 0, this.params.getMBytes());
                        try {
                            GF16.decode(bArr35, bArr19, m);
                            jArr = new long[i13 * i38];
                            bArr34 = bArr34;
                        } catch (Throwable th) {
                            th = th;
                            bArr2 = bArr29;
                            bArr11 = bArr19;
                            bArr3 = bArr34;
                            bArr12 = bArr35;
                            bArr4 = bArr21;
                            bArr5 = bArr23;
                            bArr6 = bArr24;
                            bArr7 = bArr25;
                            bArr8 = bArr26;
                            bArr9 = bArr27;
                            bArr10 = bArr28;
                        }
                        try {
                            byte[] bArr36 = new byte[v];
                            int i41 = 0;
                            while (true) {
                                if (i41 > 255) {
                                    i = i39;
                                    bArr13 = bArr29;
                                    i2 = v;
                                    bArr14 = bArr36;
                                    bArr32 = bArr35;
                                    bArr4 = bArr21;
                                    i3 = i14;
                                    bArr6 = bArr24;
                                    bArr7 = bArr25;
                                    i4 = i19;
                                    bArr15 = bArr28;
                                    bArr16 = bArr30;
                                    break;
                                }
                                try {
                                    bArr29[i40] = (byte) i41;
                                    i5 = i38;
                                    int i42 = i23;
                                    i6 = 0;
                                    sHAKEDigest.update(bArr29, 0, i42);
                                    i23 = i42;
                                    bArr32 = bArr35;
                                    bArr6 = bArr24;
                                    i7 = i22;
                                } catch (Throwable th2) {
                                    th = th2;
                                    bArr13 = bArr29;
                                    bArr32 = bArr35;
                                    bArr4 = bArr21;
                                    bArr6 = bArr24;
                                }
                                try {
                                    sHAKEDigest.doFinal(bArr6, 0, i7);
                                    i22 = i7;
                                    while (true) {
                                        i8 = i14;
                                        if (i6 >= i8) {
                                            break;
                                        }
                                        i14 = i8;
                                        byte[] bArr37 = bArr29;
                                        int i43 = i40;
                                        byte[] bArr38 = bArr23;
                                        try {
                                            GF16.decode(bArr6, i6 * vBytes, bArr38, i6 * v, v);
                                            i6++;
                                            bArr23 = bArr38;
                                            bArr29 = bArr37;
                                            i40 = i43;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            bArr5 = bArr38;
                                            bArr11 = bArr19;
                                            bArr2 = bArr37;
                                            bArr4 = bArr21;
                                            bArr7 = bArr25;
                                            bArr8 = bArr26;
                                            bArr9 = bArr27;
                                            bArr10 = bArr28;
                                            bArr12 = bArr32;
                                            bArr3 = bArr34;
                                            Arrays.fill(bArr12, (byte) 0);
                                            Arrays.fill(bArr11, (byte) 0);
                                            Arrays.fill(bArr4, (byte) 0);
                                            Arrays.fill(bArr3, (byte) 0);
                                            Arrays.fill(bArr6, (byte) 0);
                                            Arrays.fill(bArr5, (byte) 0);
                                            Arrays.fill(bArr7, (byte) 0);
                                            Arrays.fill(bArr8, (byte) 0);
                                            Arrays.fill(bArr10, (byte) 0);
                                            Arrays.fill(bArr9, (byte) 0);
                                            Arrays.fill(bArr2, (byte) 0);
                                            throw th;
                                        }
                                    }
                                    i = i39;
                                    bArr13 = bArr29;
                                    int i44 = i40;
                                    long[] jArr8 = jArr;
                                    int i45 = i41;
                                    long[] jArr9 = jArr7;
                                    byte[] bArr39 = bArr23;
                                    i4 = i19;
                                    bArr16 = bArr30;
                                    long[] jArr10 = jArr4;
                                    bArr14 = bArr36;
                                    SHAKEDigest sHAKEDigest2 = sHAKEDigest;
                                    int i46 = i15;
                                    bArr15 = bArr28;
                                    try {
                                        GF16Utils.mulAddMatXMMat(i5, bArr39, jArr9, p1Limbs, jArr10, i8, v, o);
                                        int i47 = v;
                                        try {
                                            GF16Utils.mulAddMUpperTriangularMatXMatTrans(i5, jArr9, bArr39, jArr8, i47, i8);
                                            long[] jArr11 = jArr5;
                                            GF16Utils.mulAddMatXMMat(i5, bArr39, jArr8, jArr11, i8, i47);
                                            i3 = i8;
                                            i2 = i47;
                                            bArr4 = bArr21;
                                            try {
                                                computeRHS(jArr11, bArr19, bArr4);
                                                bArr7 = bArr25;
                                                try {
                                                    computeA(jArr10, bArr7);
                                                    bArr23 = bArr39;
                                                    int i48 = i12;
                                                    try {
                                                        GF16.decode(bArr6, i48, bArr15, 0, i46);
                                                        byte[] bArr40 = bArr26;
                                                        try {
                                                            if (sampleSolution(bArr7, bArr4, bArr15, bArr40)) {
                                                                bArr26 = bArr40;
                                                                break;
                                                            }
                                                            i12 = i48;
                                                            bArr26 = bArr40;
                                                            Arrays.fill(jArr10, 0L);
                                                            Arrays.fill(jArr11, 0L);
                                                            bArr21 = bArr4;
                                                            bArr25 = bArr7;
                                                            jArr5 = jArr11;
                                                            i14 = i3;
                                                            jArr4 = jArr10;
                                                            i15 = i46;
                                                            bArr28 = bArr15;
                                                            bArr24 = bArr6;
                                                            bArr36 = bArr14;
                                                            sHAKEDigest = sHAKEDigest2;
                                                            jArr7 = jArr9;
                                                            i38 = i5;
                                                            jArr = jArr8;
                                                            bArr35 = bArr32;
                                                            i40 = i44;
                                                            i39 = i;
                                                            i19 = i4;
                                                            bArr30 = bArr16;
                                                            v = i2;
                                                            i41 = i45 + 1;
                                                            bArr29 = bArr13;
                                                        } catch (Throwable th4) {
                                                            th = th4;
                                                            bArr26 = bArr40;
                                                            bArr11 = bArr19;
                                                            bArr10 = bArr15;
                                                            bArr5 = bArr23;
                                                            bArr8 = bArr26;
                                                            bArr9 = bArr27;
                                                            bArr12 = bArr32;
                                                            bArr3 = bArr34;
                                                            bArr2 = bArr13;
                                                            Arrays.fill(bArr12, (byte) 0);
                                                            Arrays.fill(bArr11, (byte) 0);
                                                            Arrays.fill(bArr4, (byte) 0);
                                                            Arrays.fill(bArr3, (byte) 0);
                                                            Arrays.fill(bArr6, (byte) 0);
                                                            Arrays.fill(bArr5, (byte) 0);
                                                            Arrays.fill(bArr7, (byte) 0);
                                                            Arrays.fill(bArr8, (byte) 0);
                                                            Arrays.fill(bArr10, (byte) 0);
                                                            Arrays.fill(bArr9, (byte) 0);
                                                            Arrays.fill(bArr2, (byte) 0);
                                                            throw th;
                                                        }
                                                    } catch (Throwable th5) {
                                                        th = th5;
                                                    }
                                                } catch (Throwable th6) {
                                                    th = th6;
                                                    bArr23 = bArr39;
                                                }
                                            } catch (Throwable th7) {
                                                th = th7;
                                                bArr23 = bArr39;
                                                bArr7 = bArr25;
                                                bArr11 = bArr19;
                                                bArr10 = bArr15;
                                                bArr5 = bArr23;
                                                bArr8 = bArr26;
                                                bArr9 = bArr27;
                                                bArr12 = bArr32;
                                                bArr3 = bArr34;
                                                bArr2 = bArr13;
                                                Arrays.fill(bArr12, (byte) 0);
                                                Arrays.fill(bArr11, (byte) 0);
                                                Arrays.fill(bArr4, (byte) 0);
                                                Arrays.fill(bArr3, (byte) 0);
                                                Arrays.fill(bArr6, (byte) 0);
                                                Arrays.fill(bArr5, (byte) 0);
                                                Arrays.fill(bArr7, (byte) 0);
                                                Arrays.fill(bArr8, (byte) 0);
                                                Arrays.fill(bArr10, (byte) 0);
                                                Arrays.fill(bArr9, (byte) 0);
                                                Arrays.fill(bArr2, (byte) 0);
                                                throw th;
                                            }
                                        } catch (Throwable th8) {
                                            th = th8;
                                            bArr23 = bArr39;
                                            bArr4 = bArr21;
                                            bArr7 = bArr25;
                                            bArr11 = bArr19;
                                            bArr10 = bArr15;
                                            bArr5 = bArr23;
                                            bArr8 = bArr26;
                                            bArr9 = bArr27;
                                            bArr12 = bArr32;
                                            bArr3 = bArr34;
                                            bArr2 = bArr13;
                                            Arrays.fill(bArr12, (byte) 0);
                                            Arrays.fill(bArr11, (byte) 0);
                                            Arrays.fill(bArr4, (byte) 0);
                                            Arrays.fill(bArr3, (byte) 0);
                                            Arrays.fill(bArr6, (byte) 0);
                                            Arrays.fill(bArr5, (byte) 0);
                                            Arrays.fill(bArr7, (byte) 0);
                                            Arrays.fill(bArr8, (byte) 0);
                                            Arrays.fill(bArr10, (byte) 0);
                                            Arrays.fill(bArr9, (byte) 0);
                                            Arrays.fill(bArr2, (byte) 0);
                                            throw th;
                                        }
                                    } catch (Throwable th9) {
                                        th = th9;
                                        bArr23 = bArr39;
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                    bArr13 = bArr29;
                                    bArr4 = bArr21;
                                    bArr7 = bArr25;
                                    bArr15 = bArr28;
                                    bArr11 = bArr19;
                                    bArr10 = bArr15;
                                    bArr5 = bArr23;
                                    bArr8 = bArr26;
                                    bArr9 = bArr27;
                                    bArr12 = bArr32;
                                    bArr3 = bArr34;
                                    bArr2 = bArr13;
                                    Arrays.fill(bArr12, (byte) 0);
                                    Arrays.fill(bArr11, (byte) 0);
                                    Arrays.fill(bArr4, (byte) 0);
                                    Arrays.fill(bArr3, (byte) 0);
                                    Arrays.fill(bArr6, (byte) 0);
                                    Arrays.fill(bArr5, (byte) 0);
                                    Arrays.fill(bArr7, (byte) 0);
                                    Arrays.fill(bArr8, (byte) 0);
                                    Arrays.fill(bArr10, (byte) 0);
                                    Arrays.fill(bArr9, (byte) 0);
                                    Arrays.fill(bArr2, (byte) 0);
                                    throw th;
                                }
                            }
                            byte[] bArr41 = bArr19;
                            int i49 = 0;
                            int i50 = 0;
                            int i51 = 0;
                            int i52 = 0;
                            while (i51 < i3) {
                                int i53 = i50;
                                bArr10 = bArr15;
                                byte[] bArr42 = bArr31;
                                byte[] bArr43 = bArr14;
                                bArr11 = bArr41;
                                int i54 = i49;
                                int i55 = i2;
                                int i56 = o;
                                byte[] bArr44 = bArr26;
                                bArr12 = bArr32;
                                bArr3 = bArr34;
                                try {
                                    GF16Utils.matMul(bArr42, bArr44, i52, bArr43, i56, i55);
                                    bArr17 = bArr10;
                                    int i57 = i51;
                                    int i58 = i3;
                                    int i59 = i52;
                                    bArr5 = bArr23;
                                    bArr8 = bArr26;
                                    bArr9 = bArr27;
                                    try {
                                        Bytes.xor(i55, bArr5, i54, bArr43, bArr9, i53);
                                        System.arraycopy(bArr8, i59, bArr9, i53 + i55, i56);
                                        int i60 = i59 + i56;
                                        bArr41 = bArr11;
                                        bArr34 = bArr3;
                                        bArr32 = bArr12;
                                        bArr26 = bArr8;
                                        i2 = i55;
                                        bArr23 = bArr5;
                                        bArr27 = bArr9;
                                        i49 = i54 + i55;
                                        i3 = i58;
                                        bArr15 = bArr17;
                                        o = i56;
                                        i51 = i57 + 1;
                                        bArr14 = bArr43;
                                        i52 = i60;
                                        i50 = i53 + n;
                                        bArr31 = bArr42;
                                    } catch (Throwable th11) {
                                        th = th11;
                                        bArr10 = bArr17;
                                        bArr2 = bArr13;
                                        Arrays.fill(bArr12, (byte) 0);
                                        Arrays.fill(bArr11, (byte) 0);
                                        Arrays.fill(bArr4, (byte) 0);
                                        Arrays.fill(bArr3, (byte) 0);
                                        Arrays.fill(bArr6, (byte) 0);
                                        Arrays.fill(bArr5, (byte) 0);
                                        Arrays.fill(bArr7, (byte) 0);
                                        Arrays.fill(bArr8, (byte) 0);
                                        Arrays.fill(bArr10, (byte) 0);
                                        Arrays.fill(bArr9, (byte) 0);
                                        Arrays.fill(bArr2, (byte) 0);
                                        throw th;
                                    }
                                } catch (Throwable th12) {
                                    th = th12;
                                    bArr8 = bArr44;
                                    bArr5 = bArr23;
                                    bArr9 = bArr27;
                                    bArr2 = bArr13;
                                    Arrays.fill(bArr12, (byte) 0);
                                    Arrays.fill(bArr11, (byte) 0);
                                    Arrays.fill(bArr4, (byte) 0);
                                    Arrays.fill(bArr3, (byte) 0);
                                    Arrays.fill(bArr6, (byte) 0);
                                    Arrays.fill(bArr5, (byte) 0);
                                    Arrays.fill(bArr7, (byte) 0);
                                    Arrays.fill(bArr8, (byte) 0);
                                    Arrays.fill(bArr10, (byte) 0);
                                    Arrays.fill(bArr9, (byte) 0);
                                    Arrays.fill(bArr2, (byte) 0);
                                    throw th;
                                }
                            }
                            bArr17 = bArr15;
                            bArr5 = bArr23;
                            bArr8 = bArr26;
                            bArr9 = bArr27;
                            bArr11 = bArr41;
                            bArr12 = bArr32;
                            bArr3 = bArr34;
                            byte[] bArr45 = bArr16;
                            GF16.encode(bArr9, bArr45, i4);
                            System.arraycopy(bArr3, 0, bArr45, sigBytes - i, i);
                            byte[] concatenate = Arrays.concatenate(bArr45, bArr);
                            Arrays.fill(bArr12, (byte) 0);
                            Arrays.fill(bArr11, (byte) 0);
                            Arrays.fill(bArr4, (byte) 0);
                            Arrays.fill(bArr3, (byte) 0);
                            Arrays.fill(bArr6, (byte) 0);
                            Arrays.fill(bArr5, (byte) 0);
                            Arrays.fill(bArr7, (byte) 0);
                            Arrays.fill(bArr8, (byte) 0);
                            Arrays.fill(bArr17, (byte) 0);
                            Arrays.fill(bArr9, (byte) 0);
                            Arrays.fill(bArr13, (byte) 0);
                            return concatenate;
                        } catch (Throwable th13) {
                            th = th13;
                            bArr2 = bArr29;
                            bArr11 = bArr19;
                            bArr12 = bArr35;
                            bArr4 = bArr21;
                            bArr5 = bArr23;
                            bArr6 = bArr24;
                            bArr7 = bArr25;
                            bArr8 = bArr26;
                            bArr9 = bArr27;
                            bArr10 = bArr28;
                            bArr3 = bArr34;
                            Arrays.fill(bArr12, (byte) 0);
                            Arrays.fill(bArr11, (byte) 0);
                            Arrays.fill(bArr4, (byte) 0);
                            Arrays.fill(bArr3, (byte) 0);
                            Arrays.fill(bArr6, (byte) 0);
                            Arrays.fill(bArr5, (byte) 0);
                            Arrays.fill(bArr7, (byte) 0);
                            Arrays.fill(bArr8, (byte) 0);
                            Arrays.fill(bArr10, (byte) 0);
                            Arrays.fill(bArr9, (byte) 0);
                            Arrays.fill(bArr2, (byte) 0);
                            throw th;
                        }
                    } catch (Throwable th14) {
                        th = th14;
                        bArr2 = bArr29;
                        bArr3 = bArr34;
                        bArr12 = bArr35;
                        bArr4 = bArr21;
                        bArr5 = bArr23;
                        bArr6 = bArr24;
                        bArr7 = bArr25;
                        bArr8 = bArr26;
                        bArr9 = bArr27;
                        bArr10 = bArr28;
                        bArr11 = bArr19;
                    }
                } catch (Throwable th15) {
                    th = th15;
                    bArr2 = bArr29;
                    bArr3 = bArr34;
                    bArr4 = bArr21;
                    bArr5 = bArr23;
                    bArr6 = bArr24;
                    bArr7 = bArr25;
                    bArr8 = bArr26;
                    bArr9 = bArr27;
                    bArr10 = bArr28;
                    bArr11 = bArr19;
                    bArr12 = bArr32;
                    Arrays.fill(bArr12, (byte) 0);
                    Arrays.fill(bArr11, (byte) 0);
                    Arrays.fill(bArr4, (byte) 0);
                    Arrays.fill(bArr3, (byte) 0);
                    Arrays.fill(bArr6, (byte) 0);
                    Arrays.fill(bArr5, (byte) 0);
                    Arrays.fill(bArr7, (byte) 0);
                    Arrays.fill(bArr8, (byte) 0);
                    Arrays.fill(bArr10, (byte) 0);
                    Arrays.fill(bArr9, (byte) 0);
                    Arrays.fill(bArr2, (byte) 0);
                    throw th;
                }
            } catch (Throwable th16) {
                th = th16;
                bArr2 = bArr29;
                bArr4 = bArr21;
                bArr5 = bArr23;
                bArr6 = bArr24;
                bArr7 = bArr25;
                bArr8 = bArr26;
                bArr9 = bArr27;
                bArr10 = bArr28;
                bArr11 = bArr19;
            }
        } catch (Throwable th17) {
            th = th17;
            bArr2 = bArr29;
            bArr3 = bArr20;
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (!z) {
            MayoPublicKeyParameters mayoPublicKeyParameters = (MayoPublicKeyParameters) cipherParameters;
            this.pubKey = mayoPublicKeyParameters;
            this.params = mayoPublicKeyParameters.getParameters();
            this.privKey = null;
            this.random = null;
            return;
        }
        this.pubKey = null;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.privKey = (MayoPrivateKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
        } else {
            this.privKey = (MayoPrivateKeyParameters) cipherParameters;
            secureRandom = CryptoServicesRegistrar.getSecureRandom();
        }
        this.random = secureRandom;
        this.params = this.privKey.getParameters();
    }

    boolean sampleSolution(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int i;
        boolean z;
        int i2;
        int k = this.params.getK();
        int o = this.params.getO();
        int m = this.params.getM();
        int aCols = this.params.getACols();
        int i3 = k * o;
        byte b = 0;
        System.arraycopy(bArr3, 0, bArr4, 0, i3);
        byte[] bArr5 = new byte[m];
        int i4 = i3 + 1;
        byte[] bArr6 = bArr;
        GF16Utils.matMul(bArr6, bArr3, 0, bArr5, i4, m);
        int i5 = i3;
        int i6 = 0;
        while (i6 < m) {
            bArr6[i5] = (byte) (bArr2[i6] ^ bArr5[i6]);
            i6++;
            i5 += i4;
        }
        ef(bArr6, m, aCols);
        int i7 = m - 1;
        int i8 = i7 * aCols;
        int i9 = i8;
        int i10 = 0;
        boolean z2 = false;
        while (true) {
            i = aCols - 1;
            z = true;
            if (i10 >= i) {
                break;
            }
            if (bArr6[i9] == 0) {
                z = false;
            }
            z2 |= z;
            i10++;
            i9++;
        }
        if (!z2) {
            return false;
        }
        while (i7 >= 0) {
            int min = Math.min((32 / (m - i7)) + i7, i3);
            int i11 = i7;
            byte b2 = b;
            while (i11 <= min) {
                byte b3 = (byte) ((-(bArr6[i8 + i11] & UByte.MAX_VALUE)) >> 31);
                byte b4 = (byte) ((~b2) & b3 & bArr6[(i8 + aCols) - 1]);
                bArr4[i11] = (byte) (bArr4[i11] ^ b4);
                int i12 = i11;
                int i13 = i;
                int i14 = 0;
                while (i14 < i7) {
                    boolean z3 = z;
                    byte b5 = b3;
                    long j = 0;
                    int i15 = 0;
                    int i16 = 0;
                    while (true) {
                        if (i15 >= 8) {
                            break;
                        }
                        int i17 = i15;
                        j ^= (bArr[i12 + i16] & UByte.MAX_VALUE) << (i17 << 3);
                        i15 = i17 + 1;
                        i16 += aCols;
                    }
                    long mulFx8 = GF16Utils.mulFx8(b4, j);
                    int i18 = 0;
                    int i19 = 0;
                    for (i2 = 8; i18 < i2; i2 = 8) {
                        int i20 = i13 + i19;
                        bArr[i20] = (byte) (bArr[i20] ^ ((byte) ((mulFx8 >> (i18 << 3)) & 15)));
                        i18++;
                        i19 += aCols;
                        i7 = i7;
                    }
                    i14 += 8;
                    int i21 = aCols << 3;
                    i12 += i21;
                    i13 += i21;
                    z = z3;
                    b3 = b5;
                }
                b2 = (byte) (b2 | b3);
                i11++;
                bArr6 = bArr;
            }
            i7--;
            i8 -= aCols;
            bArr6 = bArr;
            b = 0;
        }
        return z;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        int m = this.params.getM();
        int n = this.params.getN();
        int k = this.params.getK();
        int i = k * n;
        int p1Limbs = this.params.getP1Limbs();
        int p2Limbs = this.params.getP2Limbs();
        int p3Limbs = this.params.getP3Limbs();
        int mBytes = this.params.getMBytes();
        int sigBytes = this.params.getSigBytes();
        int digestBytes = this.params.getDigestBytes();
        int saltBytes = this.params.getSaltBytes();
        int mVecLimbs = this.params.getMVecLimbs();
        byte[] bArr3 = new byte[mBytes];
        byte[] bArr4 = new byte[m];
        byte[] bArr5 = new byte[m << 1];
        byte[] bArr6 = new byte[i];
        int i2 = p1Limbs + p2Limbs;
        long[] jArr = new long[i2 + p3Limbs];
        byte[] bArr7 = new byte[digestBytes + saltBytes];
        byte[] encoded = this.pubKey.getEncoded();
        Utils.expandP1P2(this.params, jArr, encoded);
        Utils.unpackMVecs(encoded, this.params.getPkSeedBytes(), jArr, i2, p3Limbs / mVecLimbs, m);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr, 0, bArr.length);
        sHAKEDigest.doFinal(bArr7, 0, digestBytes);
        sHAKEDigest.update(bArr7, 0, digestBytes);
        sHAKEDigest.update(bArr2, sigBytes - saltBytes, saltBytes);
        sHAKEDigest.doFinal(bArr3, 0, mBytes);
        GF16.decode(bArr3, bArr4, m);
        GF16.decode(bArr2, bArr6, i);
        long[] jArr2 = new long[k * k * mVecLimbs];
        long[] jArr3 = new long[i * mVecLimbs];
        MayoParameters mayoParameters = this.params;
        mayoGenericMCalculatePS(mayoParameters, jArr, p1Limbs, i2, bArr6, mayoParameters.getV(), this.params.getO(), k, jArr3);
        mayoGenericMCalculateSPS(jArr3, bArr6, mVecLimbs, k, n, jArr2);
        computeRHS(jArr2, new byte[m], bArr5);
        return Arrays.constantTimeAreEqual(m, bArr5, 0, bArr4, 0);
    }
}
