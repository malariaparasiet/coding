package org.bouncycastle.pqc.crypto.cmce;

import androidx.collection.ScatterMapKt;
import androidx.core.internal.view.SupportMenu;
import com.alibaba.fastjson2.JSONB;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import kotlin.UByte;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
class CMCEEngine {
    private int COND_BYTES;
    private int GFBITS;
    private int GFMASK;
    private int IRR_BYTES;
    private int PK_NCOLS;
    private int PK_NROWS;
    private int PK_ROW_BYTES;
    private int SYND_BYTES;
    private int SYS_N;
    private int SYS_T;
    private BENES benes;
    private boolean countErrorIndices;
    private final int defaultKeySize;
    private GF gf;
    private int[] poly;
    private boolean usePadding;
    private boolean usePivots;

    public CMCEEngine(int i, int i2, int i3, int[] iArr, boolean z, int i4) {
        BENES benes13;
        this.usePivots = z;
        this.SYS_N = i2;
        this.SYS_T = i3;
        this.GFBITS = i;
        this.poly = iArr;
        this.defaultKeySize = i4;
        this.IRR_BYTES = i3 * 2;
        this.COND_BYTES = (1 << (i - 4)) * ((i * 2) - 1);
        int i5 = i3 * i;
        this.PK_NROWS = i5;
        int i6 = i2 - i5;
        this.PK_NCOLS = i6;
        this.PK_ROW_BYTES = (i6 + 7) / 8;
        this.SYND_BYTES = (i5 + 7) / 8;
        this.GFMASK = (1 << i) - 1;
        if (i == 12) {
            this.gf = new GF12();
            benes13 = new BENES12(this.SYS_N, this.SYS_T, this.GFBITS);
        } else {
            this.gf = new GF13();
            benes13 = new BENES13(this.SYS_N, this.SYS_T, this.GFBITS);
        }
        this.benes = benes13;
        this.usePadding = this.SYS_T % 8 != 0;
        this.countErrorIndices = (1 << this.GFBITS) > this.SYS_N;
    }

    private void bm(short[] sArr, short[] sArr2) {
        int i;
        int i2 = this.SYS_T;
        short[] sArr3 = new short[i2 + 1];
        short[] sArr4 = new short[i2 + 1];
        short s = 1;
        short[] sArr5 = new short[i2 + 1];
        int i3 = 0;
        for (int i4 = 0; i4 < this.SYS_T + 1; i4++) {
            sArr5[i4] = 0;
            sArr4[i4] = 0;
        }
        sArr4[0] = 1;
        sArr5[1] = 1;
        short s2 = 1;
        short s3 = 0;
        short s4 = 0;
        while (s3 < this.SYS_T * 2) {
            int i5 = 0;
            for (int i6 = 0; i6 <= min(s3, this.SYS_T); i6++) {
                i5 ^= this.gf.gf_mul_ext(sArr4[i6], sArr2[s3 - i6]);
            }
            short gf_reduce = this.gf.gf_reduce(i5);
            short s5 = (short) (((short) (((short) (((short) (gf_reduce - 1)) >> 15)) & s)) - s);
            short s6 = (short) (((short) (((short) (((short) (((short) (s3 - (s4 * 2))) >> 15)) & s)) - s)) & s5);
            for (int i7 = 0; i7 <= this.SYS_T; i7++) {
                sArr3[i7] = sArr4[i7];
            }
            short gf_frac = this.gf.gf_frac(s2, gf_reduce);
            int i8 = 0;
            while (true) {
                i = this.SYS_T;
                if (i8 > i) {
                    break;
                }
                sArr4[i8] = (short) ((this.gf.gf_mul(gf_frac, sArr5[i8]) & s5) ^ sArr4[i8]);
                i8++;
            }
            int i9 = ~s6;
            int i10 = s3 + 1;
            s4 = (short) (((i10 - s4) & s6) | (s4 & i9));
            for (int i11 = i - 1; i11 >= 0; i11--) {
                sArr5[i11 + 1] = (short) ((sArr5[i11] & i9) | (sArr3[i11] & s6));
            }
            sArr5[0] = 0;
            s2 = (short) ((i9 & s2) | (gf_reduce & s6));
            s3 = (short) i10;
            s = 1;
        }
        while (true) {
            int i12 = this.SYS_T;
            if (i3 > i12) {
                return;
            }
            sArr[i3] = sArr4[i12 - i3];
            i3++;
        }
    }

    static void cbrecursion(byte[] bArr, long j, long j2, short[] sArr, int i, long j3, long j4, int[] iArr) {
        long j5;
        int i2;
        int i3;
        int i4;
        char c;
        long j6;
        long j7;
        long j8;
        long j9 = j4;
        long j10 = 1;
        long j11 = 7;
        char c2 = 3;
        if (j3 == 1) {
            int i5 = (int) (j >> 3);
            bArr[i5] = (byte) ((get_q_short(iArr, i) << ((int) (j & 7))) ^ bArr[i5]);
            return;
        }
        if (sArr != null) {
            long j12 = 0;
            while (j12 < j9) {
                int i6 = (int) j12;
                long j13 = j10;
                iArr[i6] = sArr[(int) (j12 ^ j13)] | ((sArr[i6] ^ 1) << 16);
                j12 += j13;
                j10 = j13;
            }
            j5 = j10;
        } else {
            j5 = 1;
            long j14 = 0;
            while (j14 < j9) {
                long j15 = i;
                iArr[(int) j14] = ((get_q_short(iArr, (int) (j15 + j14)) ^ 1) << 16) | get_q_short(iArr, (int) (j15 + (j14 ^ 1)));
                j14++;
                j11 = j11;
            }
        }
        long j16 = j11;
        int i7 = (int) j9;
        sort32(iArr, 0, i7);
        long j17 = 0;
        while (true) {
            i2 = 65535;
            if (j17 >= j9) {
                break;
            }
            int i8 = (int) j17;
            int i9 = 65535 & iArr[i8];
            if (j17 >= i9) {
                i8 = i9;
            }
            iArr[(int) (j9 + j17)] = i8 | (i9 << 16);
            j17 += j5;
        }
        for (long j18 = 0; j18 < j9; j18 += j5) {
            iArr[(int) j18] = (int) ((iArr[r7] << 16) | j18);
        }
        sort32(iArr, 0, i7);
        long j19 = 0;
        while (j19 < j9) {
            int i10 = (int) j19;
            iArr[i10] = (iArr[i10] << 16) + (iArr[(int) (j9 + j19)] >> 16);
            j19 += j5;
            c2 = c2;
        }
        char c3 = c2;
        sort32(iArr, 0, i7);
        if (j3 <= 10) {
            for (long j20 = 0; j20 < j9; j20 += j5) {
                int i11 = (int) (j9 + j20);
                iArr[i11] = ((iArr[(int) j20] & 65535) << 10) | (iArr[i11] & 1023);
            }
            long j21 = j5;
            while (j21 < j3 - j5) {
                long j22 = 0;
                while (j22 < j9) {
                    iArr[(int) j22] = (int) (((iArr[(int) (j9 + j22)] & (-1024)) << 6) | j22);
                    j22 += j5;
                    j21 = j21;
                }
                long j23 = j21;
                sort32(iArr, 0, i7);
                for (long j24 = 0; j24 < j9; j24 += j5) {
                    int i12 = (int) j24;
                    iArr[i12] = (iArr[i12] << 20) | iArr[(int) (j9 + j24)];
                }
                sort32(iArr, 0, i7);
                for (long j25 = 0; j25 < j9; j25 += j5) {
                    int i13 = iArr[(int) j25];
                    int i14 = 1048575 & i13;
                    int i15 = (int) (j9 + j25);
                    int i16 = (i13 & 1047552) | (iArr[i15] & 1023);
                    if (i14 >= i16) {
                        i14 = i16;
                    }
                    iArr[i15] = i14;
                }
                j21 = j23 + j5;
            }
            for (long j26 = 0; j26 < j9; j26 += j5) {
                int i17 = (int) (j9 + j26);
                iArr[i17] = iArr[i17] & 1023;
            }
            i3 = 65535;
            i4 = -65536;
            c = c3;
            j6 = j5;
        } else {
            int i18 = SupportMenu.CATEGORY_MASK;
            for (long j27 = 0; j27 < j9; j27 += j5) {
                int i19 = (int) (j9 + j27);
                iArr[i19] = (iArr[(int) j27] << 16) | (iArr[i19] & 65535);
            }
            long j28 = j5;
            while (j28 < j3 - j5) {
                long j29 = 0;
                while (j29 < j9) {
                    iArr[(int) j29] = (int) ((iArr[(int) (j9 + j29)] & r23) | j29);
                    j29 += j5;
                    i2 = i2;
                    i18 = i18;
                }
                int i20 = i2;
                int i21 = i18;
                sort32(iArr, 0, i7);
                long j30 = 0;
                while (j30 < j9) {
                    int i22 = (int) j30;
                    long j31 = j5;
                    iArr[i22] = (iArr[i22] << 16) | (iArr[(int) (j9 + j30)] & i20);
                    j30 += j31;
                    c3 = c3;
                    j5 = j31;
                }
                char c4 = c3;
                long j32 = j5;
                if (j28 < j3 - 2) {
                    for (long j33 = 0; j33 < j9; j33 += j32) {
                        int i23 = (int) (j9 + j33);
                        iArr[i23] = (iArr[(int) j33] & i21) | (iArr[i23] >> 16);
                    }
                    sort32(iArr, i7, (int) (j9 * 2));
                    for (long j34 = 0; j34 < j9; j34 += j32) {
                        int i24 = (int) (j9 + j34);
                        iArr[i24] = (iArr[i24] << 16) | (iArr[(int) j34] & i20);
                    }
                }
                sort32(iArr, 0, i7);
                for (long j35 = 0; j35 < j9; j35 += j32) {
                    int i25 = (int) (j9 + j35);
                    int i26 = iArr[i25];
                    int i27 = (i26 & i21) | (iArr[(int) j35] & i20);
                    if (i27 < i26) {
                        iArr[i25] = i27;
                    }
                }
                j28 += j32;
                i2 = i20;
                c3 = c4;
                i18 = i21;
                j5 = j32;
            }
            i3 = i2;
            i4 = i18;
            c = c3;
            j6 = j5;
            for (long j36 = 0; j36 < j9; j36 += j6) {
                int i28 = (int) (j9 + j36);
                iArr[i28] = iArr[i28] & i3;
            }
        }
        long j37 = 0;
        if (sArr != null) {
            while (j37 < j9) {
                iArr[(int) j37] = (int) ((sArr[r0] << 16) + j37);
                j37 += j6;
            }
        } else {
            while (j37 < j9) {
                iArr[(int) j37] = (int) ((get_q_short(iArr, (int) (i + j37)) << 16) + j37);
                j37 += j6;
            }
        }
        sort32(iArr, 0, i7);
        long j38 = j;
        int i29 = i3;
        long j39 = 0;
        while (true) {
            j7 = j9 / 2;
            if (j39 >= j7) {
                break;
            }
            long j40 = j39 * 2;
            long j41 = j9 + j40;
            int i30 = (int) j41;
            int i31 = i29;
            int i32 = iArr[i30] & 1;
            char c5 = c;
            int i33 = (int) (i32 + j40);
            long j42 = j38;
            int i34 = (int) (j42 >> c5);
            bArr[i34] = (byte) ((i32 << ((int) (j42 & j16))) ^ bArr[i34]);
            j38 = j42 + j2;
            iArr[i30] = (iArr[(int) j40] << 16) | i33;
            iArr[(int) (j41 + j6)] = (iArr[(int) (j40 + j6)] << 16) | (i33 ^ 1);
            j39 += j6;
            i29 = i31;
            j9 = j4;
            c = c5;
        }
        int i35 = i29;
        char c6 = c;
        long j43 = j4 * 2;
        sort32(iArr, i7, (int) j43);
        long j44 = j3 * 2;
        long j45 = j38 + ((j44 - 3) * j2 * j7);
        long j46 = 0;
        while (true) {
            j8 = j43;
            if (j46 >= j7) {
                break;
            }
            long j47 = j46 * 2;
            long j48 = j44;
            long j49 = j4 + j47;
            int i36 = iArr[(int) j49];
            int i37 = i36 & 1;
            int i38 = (int) (i37 + j47);
            int i39 = i38 ^ 1;
            int i40 = (int) (j45 >> c6);
            bArr[i40] = (byte) (bArr[i40] ^ (i37 << ((int) (j45 & j16))));
            j45 += j2;
            iArr[(int) j47] = (i36 & i35) | (i38 << 16);
            iArr[(int) (j47 + j6)] = (i39 << 16) | (iArr[(int) (j49 + j6)] & i35);
            j46 += j6;
            j43 = j8;
            j44 = j48;
        }
        sort32(iArr, 0, i7);
        long j50 = j45 - (((j44 - 2) * j2) * j7);
        short[] sArr2 = new short[i7 * 4];
        for (long j51 = 0; j51 < j8; j51 += j6) {
            long j52 = j51 * 2;
            int i41 = iArr[(int) j51];
            sArr2[(int) j52] = (short) i41;
            sArr2[(int) (j52 + j6)] = (short) ((i41 & i4) >> 16);
        }
        for (long j53 = 0; j53 < j7; j53 += j6) {
            long j54 = j53 * 2;
            sArr2[(int) j53] = (short) ((iArr[(int) j54] & i35) >>> 1);
            sArr2[(int) (j53 + j7)] = (short) ((iArr[(int) (j54 + j6)] & i35) >>> 1);
        }
        for (long j55 = 0; j55 < j7; j55 += j6) {
            long j56 = j55 * 2;
            iArr[(int) (j4 + (j4 / 4) + j55)] = sArr2[(int) j56] | (sArr2[(int) (j56 + j6)] << 16);
        }
        long j57 = j2 * 2;
        long j58 = j4 + (j4 / 4);
        long j59 = j3 - j6;
        cbrecursion(bArr, j50, j57, null, ((int) j58) * 2, j59, j7, iArr);
        cbrecursion(bArr, j50 + j2, j57, null, (int) ((j58 * 2) + j7), j59, j7, iArr);
    }

    private static void controlbitsfrompermutation(byte[] bArr, short[] sArr, long j, long j2) {
        long j3 = j2;
        int[] iArr = new int[(int) (j3 * 2)];
        int i = (int) j3;
        short[] sArr2 = new short[i];
        while (true) {
            short s = 0;
            for (int i2 = 0; i2 < (((((j * 2) - 1) * j3) / 2) + 7) / 8; i2++) {
                bArr[i2] = 0;
            }
            cbrecursion(bArr, 0L, 1L, sArr, 0, j, j3, iArr);
            for (int i3 = 0; i3 < j2; i3++) {
                sArr2[i3] = (short) i3;
            }
            int i4 = 0;
            for (int i5 = 0; i5 < j; i5++) {
                layer(sArr2, bArr, i4, i5, i);
                i4 = (int) (i4 + (j2 >> 4));
            }
            for (int i6 = (int) (j - 2); i6 >= 0; i6--) {
                layer(sArr2, bArr, i4, i6, i);
                i4 = (int) (i4 + (j2 >> 4));
            }
            int i7 = 0;
            while (i7 < j2) {
                short s2 = (short) (s | (sArr[i7] ^ sArr2[i7]));
                i7++;
                s = s2;
            }
            if (s == 0) {
                return;
            } else {
                j3 = j2;
            }
        }
    }

    private static int ctz(long j) {
        long j2 = ~j;
        long j3 = ScatterMapKt.BitmaskLsb;
        long j4 = 0;
        for (int i = 0; i < 8; i++) {
            j3 &= j2 >>> i;
            j4 += j3;
        }
        long j5 = 578721382704613384L & j4;
        long j6 = j5 | (j5 >>> 1);
        long j7 = j6 | (j6 >>> 2);
        long j8 = j4 >>> 8;
        long j9 = j4 + (j8 & j7);
        for (int i2 = 2; i2 < 8; i2++) {
            j7 &= j7 >>> 8;
            j8 >>>= 8;
            j9 += j8 & j7;
        }
        return ((int) j9) & 255;
    }

    private int decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i;
        int i2;
        int i3 = this.SYS_T;
        short[] sArr = new short[i3 + 1];
        int i4 = this.SYS_N;
        short[] sArr2 = new short[i4];
        short[] sArr3 = new short[i3 * 2];
        short[] sArr4 = new short[i3 * 2];
        short[] sArr5 = new short[i3 + 1];
        short[] sArr6 = new short[i4];
        byte[] bArr4 = new byte[i4 / 8];
        int i5 = 0;
        while (true) {
            i = this.SYND_BYTES;
            if (i5 >= i) {
                break;
            }
            bArr4[i5] = bArr3[i5];
            i5++;
        }
        while (i < this.SYS_N / 8) {
            bArr4[i] = 0;
            i++;
        }
        int i6 = 0;
        while (true) {
            i2 = this.SYS_T;
            if (i6 >= i2) {
                break;
            }
            sArr[i6] = Utils.load_gf(bArr2, (i6 * 2) + 40, this.GFMASK);
            i6++;
        }
        sArr[i2] = 1;
        this.benes.support_gen(sArr2, bArr2);
        synd(sArr3, sArr, sArr2, bArr4);
        bm(sArr5, sArr3);
        root(sArr6, sArr5, sArr2);
        for (int i7 = 0; i7 < this.SYS_N / 8; i7++) {
            bArr[i7] = 0;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < this.SYS_N; i9++) {
            short gf_iszero = (short) (this.gf.gf_iszero(sArr6[i9]) & 1);
            int i10 = i9 / 8;
            bArr[i10] = (byte) (bArr[i10] | (gf_iszero << (i9 % 8)));
            i8 += gf_iszero;
        }
        synd(sArr4, sArr, sArr2, bArr);
        int i11 = this.SYS_T ^ i8;
        for (int i12 = 0; i12 < this.SYS_T * 2; i12++) {
            i11 |= sArr3[i12] ^ sArr4[i12];
        }
        return (((i11 - 1) >> 15) & 1) ^ 1;
    }

    private void encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        generate_error_vector(bArr3, secureRandom);
        syndrome(bArr, bArr2, bArr3);
    }

    private short eval(short[] sArr, short s) {
        int i = this.SYS_T;
        short s2 = sArr[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            s2 = (short) (this.gf.gf_mul(s2, s) ^ sArr[i2]);
        }
        return s2;
    }

    private void generate_error_vector(byte[] bArr, SecureRandom secureRandom) {
        int i;
        int i2 = this.SYS_T;
        short[] sArr = new short[i2 * 2];
        short[] sArr2 = new short[i2];
        byte[] bArr2 = new byte[i2];
        while (true) {
            if (this.countErrorIndices) {
                byte[] bArr3 = new byte[this.SYS_T * 4];
                secureRandom.nextBytes(bArr3);
                for (int i3 = 0; i3 < this.SYS_T * 2; i3++) {
                    sArr[i3] = Utils.load_gf(bArr3, i3 * 2, this.GFMASK);
                }
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    i = this.SYS_T;
                    if (i4 >= i * 2 || i5 >= i) {
                        break;
                    }
                    short s = sArr[i4];
                    if (s < this.SYS_N) {
                        sArr2[i5] = s;
                        i5++;
                    }
                    i4++;
                }
                if (i5 < i) {
                    continue;
                }
            } else {
                byte[] bArr4 = new byte[this.SYS_T * 2];
                secureRandom.nextBytes(bArr4);
                for (int i6 = 0; i6 < this.SYS_T; i6++) {
                    sArr2[i6] = Utils.load_gf(bArr4, i6 * 2, this.GFMASK);
                }
            }
            boolean z = false;
            for (int i7 = 1; i7 < this.SYS_T && !z; i7++) {
                int i8 = 0;
                while (true) {
                    if (i8 >= i7) {
                        break;
                    }
                    if (sArr2[i7] == sArr2[i8]) {
                        z = true;
                        break;
                    }
                    i8++;
                }
            }
            if (!z) {
                break;
            }
        }
        for (int i9 = 0; i9 < this.SYS_T; i9++) {
            bArr2[i9] = (byte) (1 << (sArr2[i9] & 7));
        }
        for (short s2 = 0; s2 < this.SYS_N / 8; s2 = (short) (s2 + 1)) {
            bArr[s2] = 0;
            for (int i10 = 0; i10 < this.SYS_T; i10++) {
                bArr[s2] = (byte) ((((short) (same_mask32(s2, (short) (sArr2[i10] >> 3)) & 255)) & bArr2[i10]) | bArr[s2]);
            }
        }
    }

    private int generate_irr_poly(short[] sArr) {
        int i;
        int i2 = this.SYS_T;
        int i3 = 2;
        short[][] sArr2 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i2 + 1, i2);
        sArr2[0][0] = 1;
        System.arraycopy(sArr, 0, sArr2[1], 0, this.SYS_T);
        int[] iArr = new int[(this.SYS_T * 2) - 1];
        while (true) {
            i = this.SYS_T;
            if (i3 >= i) {
                break;
            }
            this.gf.gf_sqr_poly(i, this.poly, sArr2[i3], sArr2[i3 >>> 1], iArr);
            int[] iArr2 = iArr;
            this.gf.gf_mul_poly(this.SYS_T, this.poly, sArr2[i3 + 1], sArr2[i3], sArr, iArr2);
            iArr = iArr2;
            i3 += 2;
        }
        if (i3 == i) {
            this.gf.gf_sqr_poly(i, this.poly, sArr2[i3], sArr2[i3 >>> 1], iArr);
        }
        int i4 = 0;
        while (true) {
            int i5 = this.SYS_T;
            if (i4 >= i5) {
                System.arraycopy(sArr2[i5], 0, sArr, 0, i5);
                return 0;
            }
            int i6 = i4 + 1;
            for (int i7 = i6; i7 < this.SYS_T; i7++) {
                short gf_iszero = this.gf.gf_iszero(sArr2[i4][i4]);
                for (int i8 = i4; i8 < this.SYS_T + 1; i8++) {
                    short[] sArr3 = sArr2[i8];
                    sArr3[i4] = (short) (sArr3[i4] ^ ((short) (sArr3[i7] & gf_iszero)));
                }
            }
            short s = sArr2[i4][i4];
            if (s == 0) {
                return -1;
            }
            short gf_inv = this.gf.gf_inv(s);
            for (int i9 = i4; i9 < this.SYS_T + 1; i9++) {
                short[] sArr4 = sArr2[i9];
                sArr4[i4] = this.gf.gf_mul(sArr4[i4], gf_inv);
            }
            for (int i10 = 0; i10 < this.SYS_T; i10++) {
                if (i10 != i4) {
                    short s2 = sArr2[i4][i10];
                    for (int i11 = i4; i11 <= this.SYS_T; i11++) {
                        short[] sArr5 = sArr2[i11];
                        sArr5[i10] = (short) (sArr5[i10] ^ this.gf.gf_mul(sArr5[i4], s2));
                    }
                }
            }
            i4 = i6;
        }
    }

    static short get_q_short(int[] iArr, int i) {
        int i2 = i / 2;
        return (short) (i % 2 == 0 ? iArr[i2] : (iArr[i2] & SupportMenu.CATEGORY_MASK) >> 16);
    }

    private static void layer(short[] sArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = 1 << i2;
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6 += i4 * 2) {
            for (int i7 = 0; i7 < i4; i7++) {
                int i8 = i6 + i7;
                short s = sArr[i8];
                int i9 = i8 + i4;
                int i10 = (sArr[i9] ^ s) & (-((bArr[(i5 >> 3) + i] >> (i5 & 7)) & 1));
                sArr[i8] = (short) (s ^ i10);
                sArr[i9] = (short) (sArr[i9] ^ i10);
                i5++;
            }
        }
    }

    private static int min(short s, int i) {
        return s < i ? s : i;
    }

    private int mov_columns(byte[][] bArr, short[] sArr, long[] jArr) {
        long load8;
        int i = 64;
        long[] jArr2 = new long[64];
        int i2 = 32;
        long[] jArr3 = new long[32];
        byte[] bArr2 = new byte[9];
        int i3 = this.PK_NROWS - 32;
        int i4 = i3 / 8;
        int i5 = i3 % 8;
        boolean z = false;
        if (this.usePadding) {
            for (int i6 = 0; i6 < 32; i6++) {
                for (int i7 = 0; i7 < 9; i7++) {
                    bArr2[i7] = bArr[i3 + i6][i4 + i7];
                }
                int i8 = 0;
                while (i8 < 8) {
                    int i9 = i8 + 1;
                    bArr2[i8] = (byte) (((bArr2[i8] & UByte.MAX_VALUE) >> i5) | (bArr2[i9] << (8 - i5)));
                    i8 = i9;
                }
                jArr2[i6] = Utils.load8(bArr2, 0);
            }
        } else {
            for (int i10 = 0; i10 < 32; i10++) {
                jArr2[i10] = Utils.load8(bArr[i3 + i10], i4);
            }
        }
        long j = 0;
        jArr[0] = 0;
        int i11 = 0;
        while (true) {
            long j2 = 1;
            if (i11 >= 32) {
                int i12 = 0;
                while (i12 < i2) {
                    int i13 = i12 + 1;
                    int i14 = i13;
                    while (i14 < i) {
                        long same_mask64 = (sArr[r11] ^ sArr[r15]) & same_mask64((short) i14, (short) r19[i12]);
                        sArr[i3 + i12] = (short) (sArr[r11] ^ same_mask64);
                        sArr[i3 + i14] = (short) (same_mask64 ^ sArr[r15]);
                        i14++;
                        i13 = i13;
                        jArr3 = jArr3;
                        i = 64;
                        i2 = 32;
                    }
                    i12 = i13;
                }
                long[] jArr4 = jArr3;
                for (int i15 = 0; i15 < this.PK_NROWS; i15++) {
                    if (this.usePadding) {
                        for (int i16 = 0; i16 < 9; i16++) {
                            bArr2[i16] = bArr[i15][i4 + i16];
                        }
                        int i17 = 0;
                        while (i17 < 8) {
                            int i18 = i17 + 1;
                            bArr2[i17] = (byte) (((bArr2[i17] & UByte.MAX_VALUE) >> i5) | (bArr2[i18] << (8 - i5)));
                            i17 = i18;
                        }
                        load8 = Utils.load8(bArr2, 0);
                    } else {
                        load8 = Utils.load8(bArr[i15], i4);
                    }
                    for (int i19 = 0; i19 < 32; i19++) {
                        long j3 = jArr4[i19];
                        long j4 = ((load8 >> i19) ^ (load8 >> ((int) j3))) & 1;
                        load8 = (load8 ^ (j4 << ((int) j3))) ^ (j4 << i19);
                    }
                    if (this.usePadding) {
                        Utils.store8(bArr2, 0, load8);
                        byte[] bArr3 = bArr[i15];
                        int i20 = i4 + 8;
                        int i21 = 8 - i5;
                        bArr3[i20] = (byte) ((((bArr3[i20] & UByte.MAX_VALUE) >>> i5) << i5) | ((bArr2[7] & UByte.MAX_VALUE) >>> i21));
                        bArr3[i4] = (byte) (((bArr2[0] & UByte.MAX_VALUE) << i5) | (((bArr3[i4] & UByte.MAX_VALUE) << i21) >>> i21));
                        for (int i22 = 7; i22 >= 1; i22--) {
                            bArr[i15][i4 + i22] = (byte) (((bArr2[i22] & UByte.MAX_VALUE) << i5) | ((bArr2[i22 - 1] & UByte.MAX_VALUE) >>> i21));
                        }
                    } else {
                        Utils.store8(bArr[i15], i4, load8);
                    }
                }
                return 0;
            }
            long j5 = jArr2[i11];
            int i23 = i11 + 1;
            long j6 = j;
            for (int i24 = i23; i24 < 32; i24++) {
                j5 |= jArr2[i24];
            }
            if (j5 == j6) {
                return -1;
            }
            int ctz = ctz(j5);
            boolean z2 = z;
            long j7 = ctz;
            jArr3[i11] = j7;
            jArr[z2 ? 1 : 0] = jArr[z2 ? 1 : 0] | (1 << ((int) j7));
            for (int i25 = i23; i25 < 32; i25++) {
                long j8 = jArr2[i11];
                jArr2[i11] = j8 ^ (jArr2[i25] & (((j8 >> ctz) & 1) - 1));
            }
            int i26 = i23;
            while (i26 < 32) {
                long j9 = jArr2[i26];
                long j10 = j2;
                jArr2[i26] = j9 ^ (jArr2[i11] & (-((j9 >> ctz) & j10)));
                i26++;
                j2 = j10;
                z2 = false;
            }
            z = z2;
            i11 = i23;
            j = j6;
        }
    }

    private int pk_gen(byte[] bArr, byte[] bArr2, int[] iArr, short[] sArr, long[] jArr) {
        int i;
        int i2;
        int i3 = this.SYS_T;
        short[] sArr2 = new short[i3 + 1];
        byte b = 1;
        sArr2[i3] = 1;
        for (int i4 = 0; i4 < this.SYS_T; i4++) {
            sArr2[i4] = Utils.load_gf(bArr2, (i4 * 2) + 40, this.GFMASK);
        }
        int i5 = 1 << this.GFBITS;
        long[] jArr2 = new long[i5];
        for (int i6 = 0; i6 < (1 << this.GFBITS); i6++) {
            long j = iArr[i6];
            jArr2[i6] = j;
            long j2 = j << 31;
            jArr2[i6] = j2;
            long j3 = j2 | i6;
            jArr2[i6] = j3;
            jArr2[i6] = j3 & Long.MAX_VALUE;
        }
        sort64(jArr2, 0, i5);
        for (int i7 = 1; i7 < (1 << this.GFBITS); i7++) {
            if ((jArr2[i7 - 1] >> 31) == (jArr2[i7] >> 31)) {
                return -1;
            }
        }
        short[] sArr3 = new short[this.SYS_N];
        for (int i8 = 0; i8 < (1 << this.GFBITS); i8++) {
            sArr[i8] = (short) (jArr2[i8] & this.GFMASK);
        }
        int i9 = 0;
        while (true) {
            i = this.SYS_N;
            if (i9 >= i) {
                break;
            }
            sArr3[i9] = Utils.bitrev(sArr[i9], this.GFBITS);
            i9++;
        }
        short[] sArr4 = new short[i];
        root(sArr4, sArr2, sArr3);
        int i10 = 0;
        while (true) {
            i2 = this.SYS_N;
            if (i10 >= i2) {
                break;
            }
            sArr4[i10] = this.gf.gf_inv(sArr4[i10]);
            i10++;
        }
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, this.PK_NROWS, i2 / 8);
        for (int i11 = 0; i11 < this.PK_NROWS; i11++) {
            for (int i12 = 0; i12 < this.SYS_N / 8; i12++) {
                bArr3[i11][i12] = 0;
            }
        }
        int i13 = 0;
        while (i13 < this.SYS_T) {
            for (int i14 = 0; i14 < this.SYS_N; i14 += 8) {
                int i15 = 0;
                while (true) {
                    int i16 = this.GFBITS;
                    if (i15 < i16) {
                        bArr3[(i16 * i13) + i15][i14 / 8] = (byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) ((sArr4[i14 + 7] >>> i15) & 1)) << 1)) | ((sArr4[i14 + 6] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 5] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 4] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 3] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 2] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 1] >>> i15) & 1))) << 1)) | ((sArr4[i14] >>> i15) & 1));
                        i15++;
                    }
                }
            }
            for (int i17 = 0; i17 < this.SYS_N; i17++) {
                sArr4[i17] = this.gf.gf_mul(sArr4[i17], sArr3[i17]);
            }
            i13++;
        }
        int i18 = 0;
        while (true) {
            int i19 = this.PK_NROWS;
            if (i18 >= i19) {
                if (bArr != null) {
                    if (!this.usePadding) {
                        int i20 = ((this.SYS_N - i19) + 7) / 8;
                        int i21 = 0;
                        while (true) {
                            int i22 = this.PK_NROWS;
                            if (i21 >= i22) {
                                break;
                            }
                            System.arraycopy(bArr3[i21], i22 / 8, bArr, i20 * i21, i20);
                            i21++;
                        }
                    } else {
                        int i23 = i19 % 8;
                        if (i23 != 0) {
                            int i24 = 0;
                            int i25 = 0;
                            while (true) {
                                int i26 = this.PK_NROWS;
                                if (i24 >= i26) {
                                    break;
                                }
                                int i27 = (i26 - 1) / 8;
                                while (i27 < (this.SYS_N / 8) - 1) {
                                    byte[] bArr4 = bArr3[i24];
                                    int i28 = (bArr4[i27] & UByte.MAX_VALUE) >>> i23;
                                    i27++;
                                    bArr[i25] = (byte) ((bArr4[i27] << (8 - i23)) | i28);
                                    i25++;
                                }
                                bArr[i25] = (byte) ((bArr3[i24][i27] & UByte.MAX_VALUE) >>> i23);
                                i24++;
                                i25++;
                            }
                        } else {
                            System.arraycopy(bArr3[i13], (i19 - 1) / 8, bArr, 0, this.SYS_N / 8);
                            int i29 = this.SYS_N / 8;
                        }
                    }
                }
                return 0;
            }
            i13 = i18 >>> 3;
            int i30 = i18 & 7;
            if (this.usePivots && i18 == i19 - 32) {
                if (mov_columns(bArr3, sArr, jArr) != 0) {
                    return -1;
                }
            }
            int i31 = i18 + 1;
            for (int i32 = i31; i32 < this.PK_NROWS; i32++) {
                byte b2 = (byte) (-((byte) (((byte) (((byte) (bArr3[i18][i13] ^ bArr3[i32][i13])) >> i30)) & b)));
                int i33 = 0;
                while (i33 < this.SYS_N / 8) {
                    byte[] bArr5 = bArr3[i18];
                    bArr5[i33] = (byte) (bArr5[i33] ^ (bArr3[i32][i33] & b2));
                    i33++;
                    b = b;
                }
            }
            byte b3 = b;
            if (((bArr3[i18][i13] >> i30) & 1) == 0) {
                return -1;
            }
            for (int i34 = 0; i34 < this.PK_NROWS; i34++) {
                if (i34 != i18) {
                    byte b4 = (byte) (-((byte) (((byte) (bArr3[i34][i13] >> i30)) & 1)));
                    for (int i35 = 0; i35 < this.SYS_N / 8; i35++) {
                        byte[] bArr6 = bArr3[i34];
                        bArr6[i35] = (byte) (bArr6[i35] ^ (bArr3[i18][i35] & b4));
                    }
                }
            }
            i18 = i31;
            b = b3;
        }
    }

    private void root(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i = 0; i < this.SYS_N; i++) {
            sArr[i] = eval(sArr2, sArr3[i]);
        }
    }

    private static byte same_mask32(short s, short s2) {
        return (byte) ((-(((s ^ s2) - 1) >>> 31)) & 255);
    }

    private static long same_mask64(short s, short s2) {
        return -(((s ^ s2) - 1) >>> 63);
    }

    private static void sort32(int[] iArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 2) {
            return;
        }
        int i4 = 1;
        while (i4 < i3 - i4) {
            i4 += i4;
        }
        for (int i5 = i4; i5 > 0; i5 >>>= 1) {
            int i6 = 0;
            for (int i7 = 0; i7 < i3 - i5; i7++) {
                if ((i7 & i5) == 0) {
                    int i8 = i + i7;
                    int i9 = i8 + i5;
                    int i10 = iArr[i9];
                    int i11 = iArr[i8];
                    int i12 = i10 ^ i11;
                    int i13 = i10 - i11;
                    int i14 = ((((i10 ^ i13) & i12) ^ i13) >> 31) & i12;
                    iArr[i8] = i11 ^ i14;
                    iArr[i9] = iArr[i9] ^ i14;
                }
            }
            for (int i15 = i4; i15 > i5; i15 >>>= 1) {
                while (i6 < i3 - i15) {
                    if ((i6 & i5) == 0) {
                        int i16 = i + i6;
                        int i17 = i16 + i5;
                        int i18 = iArr[i17];
                        for (int i19 = i15; i19 > i5; i19 >>>= 1) {
                            int i20 = i16 + i19;
                            int i21 = iArr[i20];
                            int i22 = i21 ^ i18;
                            int i23 = i21 - i18;
                            int i24 = i22 & ((i23 ^ ((i23 ^ i21) & i22)) >> 31);
                            i18 ^= i24;
                            iArr[i20] = i21 ^ i24;
                        }
                        iArr[i17] = i18;
                    }
                    i6++;
                }
            }
        }
    }

    private static void sort64(long[] jArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 2) {
            return;
        }
        int i4 = 1;
        while (i4 < i3 - i4) {
            i4 += i4;
        }
        for (int i5 = i4; i5 > 0; i5 >>>= 1) {
            int i6 = 0;
            for (int i7 = 0; i7 < i3 - i5; i7++) {
                if ((i7 & i5) == 0) {
                    int i8 = i + i7;
                    int i9 = i8 + i5;
                    long j = jArr[i9];
                    long j2 = jArr[i8];
                    long j3 = (j ^ j2) & (-((j - j2) >>> 63));
                    jArr[i8] = j2 ^ j3;
                    jArr[i9] = jArr[i9] ^ j3;
                }
            }
            for (int i10 = i4; i10 > i5; i10 >>>= 1) {
                while (i6 < i3 - i10) {
                    if ((i6 & i5) == 0) {
                        int i11 = i + i6;
                        int i12 = i11 + i5;
                        long j4 = jArr[i12];
                        for (int i13 = i10; i13 > i5; i13 >>>= 1) {
                            int i14 = i11 + i13;
                            long j5 = jArr[i14];
                            long j6 = (-((j5 - j4) >>> 63)) & (j4 ^ j5);
                            j4 ^= j6;
                            jArr[i14] = j5 ^ j6;
                        }
                        jArr[i12] = j4;
                    }
                    i6++;
                }
            }
        }
    }

    private void synd(short[] sArr, short[] sArr2, short[] sArr3, byte[] bArr) {
        short s = (short) (bArr[0] & 1);
        short s2 = sArr3[0];
        short eval = eval(sArr2, s2);
        GF gf = this.gf;
        short gf_inv = (short) ((-s) & gf.gf_inv(gf.gf_sq(eval)));
        sArr[0] = gf_inv;
        for (int i = 1; i < this.SYS_T * 2; i++) {
            gf_inv = this.gf.gf_mul(gf_inv, s2);
            sArr[i] = gf_inv;
        }
        for (int i2 = 1; i2 < this.SYS_N; i2++) {
            short s3 = (short) ((bArr[i2 / 8] >> (i2 % 8)) & 1);
            short s4 = sArr3[i2];
            short eval2 = eval(sArr2, s4);
            GF gf2 = this.gf;
            short gf_mul = this.gf.gf_mul(gf2.gf_inv(gf2.gf_sq(eval2)), s3);
            sArr[0] = (short) (sArr[0] ^ gf_mul);
            for (int i3 = 1; i3 < this.SYS_T * 2; i3++) {
                gf_mul = this.gf.gf_mul(gf_mul, s4);
                sArr[i3] = (short) (sArr[i3] ^ gf_mul);
            }
        }
    }

    private void syndrome(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        short[] sArr = new short[this.SYS_N / 8];
        int i = this.PK_NROWS % 8;
        for (int i2 = 0; i2 < this.SYND_BYTES; i2++) {
            bArr[i2] = 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.PK_NROWS; i4++) {
            for (int i5 = 0; i5 < this.SYS_N / 8; i5++) {
                sArr[i5] = 0;
            }
            int i6 = 0;
            while (true) {
                int i7 = this.PK_ROW_BYTES;
                if (i6 >= i7) {
                    break;
                }
                sArr[((this.SYS_N / 8) - i7) + i6] = bArr2[i3 + i6];
                i6++;
            }
            if (this.usePadding) {
                for (int i8 = (this.SYS_N / 8) - 1; i8 >= (this.SYS_N / 8) - this.PK_ROW_BYTES; i8--) {
                    sArr[i8] = (short) ((((sArr[i8] & 255) << i) | ((sArr[i8 - 1] & 255) >>> (8 - i))) & 255);
                }
            }
            int i9 = i4 / 8;
            int i10 = i4 % 8;
            sArr[i9] = (short) (sArr[i9] | (1 << i10));
            byte b = 0;
            for (int i11 = 0; i11 < this.SYS_N / 8; i11++) {
                b = (byte) (b ^ (sArr[i11] & bArr3[i11]));
            }
            byte b2 = (byte) ((b >>> 4) ^ b);
            byte b3 = (byte) (b2 ^ (b2 >>> 2));
            bArr[i9] = (byte) ((((byte) (1 & ((byte) (b3 ^ (b3 >>> 1))))) << i10) | bArr[i9]);
            i3 += this.PK_ROW_BYTES;
        }
    }

    int check_c_padding(byte[] bArr) {
        return ((byte) ((((byte) (((byte) ((bArr[this.SYND_BYTES - 1] & UByte.MAX_VALUE) >>> (this.PK_NROWS % 8))) - 1)) & UByte.MAX_VALUE) >>> 7)) - 1;
    }

    int check_pk_padding(byte[] bArr) {
        byte b = 0;
        for (int i = 0; i < this.PK_NROWS; i++) {
            int i2 = this.PK_ROW_BYTES;
            b = (byte) (b | bArr[((i * i2) + i2) - 1]);
        }
        return ((byte) ((((byte) (((byte) ((b & UByte.MAX_VALUE) >>> (this.PK_NCOLS % 8))) - 1)) & UByte.MAX_VALUE) >>> 7)) - 1;
    }

    public byte[] decompress_private_key(byte[] bArr) {
        int i;
        byte[] bArr2 = new byte[getPrivateKeySize()];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        int i2 = (this.SYS_N / 8) + ((1 << this.GFBITS) * 4) + this.IRR_BYTES;
        int i3 = i2 + 32;
        byte[] bArr3 = new byte[i3];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(JSONB.Constants.BC_INT32_SHORT_MIN);
        sHAKEDigest.update(bArr, 0, 32);
        sHAKEDigest.doFinal(bArr3, 0, i3);
        if (bArr.length <= 40) {
            short[] sArr = new short[this.SYS_T];
            int i4 = this.IRR_BYTES;
            byte[] bArr4 = new byte[i4];
            int i5 = i2 - i4;
            for (int i6 = 0; i6 < this.SYS_T; i6++) {
                sArr[i6] = Utils.load_gf(bArr3, (i6 * 2) + i5, this.GFMASK);
            }
            generate_irr_poly(sArr);
            for (int i7 = 0; i7 < this.SYS_T; i7++) {
                Utils.store_gf(bArr4, i7 * 2, sArr[i7]);
            }
            System.arraycopy(bArr4, 0, bArr2, 40, this.IRR_BYTES);
        }
        int length = bArr.length;
        int i8 = this.IRR_BYTES;
        if (length <= i8 + 40) {
            int i9 = this.GFBITS;
            int[] iArr = new int[1 << i9];
            short[] sArr2 = new short[1 << i9];
            int i10 = (i2 - i8) - ((1 << i9) * 4);
            int i11 = 0;
            while (true) {
                i = this.GFBITS;
                if (i11 >= (1 << i)) {
                    break;
                }
                iArr[i11] = Utils.load4(bArr3, (i11 * 4) + i10);
                i11++;
            }
            if (this.usePivots) {
                pk_gen(null, bArr2, iArr, sArr2, new long[]{0});
            } else {
                int i12 = 1 << i;
                long[] jArr = new long[i12];
                for (int i13 = 0; i13 < (1 << this.GFBITS); i13++) {
                    long j = iArr[i13];
                    jArr[i13] = j;
                    long j2 = j << 31;
                    jArr[i13] = j2;
                    long j3 = j2 | i13;
                    jArr[i13] = j3;
                    jArr[i13] = j3 & Long.MAX_VALUE;
                }
                sort64(jArr, 0, i12);
                for (int i14 = 0; i14 < (1 << this.GFBITS); i14++) {
                    sArr2[i14] = (short) (jArr[i14] & this.GFMASK);
                }
            }
            int i15 = this.COND_BYTES;
            byte[] bArr5 = new byte[i15];
            controlbitsfrompermutation(bArr5, sArr2, this.GFBITS, 1 << r3);
            System.arraycopy(bArr5, 0, bArr2, this.IRR_BYTES + 40, i15);
        }
        int privateKeySize = getPrivateKeySize();
        int i16 = this.SYS_N;
        System.arraycopy(bArr3, 0, bArr2, privateKeySize - (i16 / 8), i16 / 8);
        return bArr2;
    }

    public byte[] generate_public_key_from_private_key(byte[] bArr) {
        byte[] bArr2 = new byte[getPublicKeySize()];
        int i = this.GFBITS;
        short[] sArr = new short[1 << i];
        long[] jArr = {0};
        int[] iArr = new int[1 << i];
        int i2 = (this.SYS_N / 8) + ((1 << i) * 4);
        byte[] bArr3 = new byte[i2];
        int i3 = ((i2 - 32) - this.IRR_BYTES) - ((1 << i) * 4);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(JSONB.Constants.BC_INT32_SHORT_MIN);
        sHAKEDigest.update(bArr, 0, 32);
        sHAKEDigest.doFinal(bArr3, 0, i2);
        for (int i4 = 0; i4 < (1 << this.GFBITS); i4++) {
            iArr[i4] = Utils.load4(bArr3, (i4 * 4) + i3);
        }
        pk_gen(bArr2, bArr, iArr, sArr, jArr);
        return bArr2;
    }

    public int getCipherTextSize() {
        return this.SYND_BYTES;
    }

    public int getCondBytes() {
        return this.COND_BYTES;
    }

    public int getDefaultSessionKeySize() {
        return this.defaultKeySize;
    }

    public int getIrrBytes() {
        return this.IRR_BYTES;
    }

    public int getPrivateKeySize() {
        return this.COND_BYTES + this.IRR_BYTES + (this.SYS_N / 8) + 40;
    }

    public int getPublicKeySize() {
        if (!this.usePadding) {
            return (this.PK_NROWS * this.PK_NCOLS) / 8;
        }
        int i = this.PK_NROWS;
        return i * ((this.SYS_N / 8) - ((i - 1) / 8));
    }

    public int kem_dec(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.SYS_N;
        byte[] bArr4 = new byte[i / 8];
        int i2 = (i / 8) + 1 + this.SYND_BYTES;
        byte[] bArr5 = new byte[i2];
        int check_c_padding = this.usePadding ? check_c_padding(bArr2) : 0;
        short decrypt = (short) (((short) (((short) (((byte) decrypt(bArr4, bArr3, bArr2)) - 1)) >> 8)) & 255);
        bArr5[0] = (byte) (decrypt & 1);
        int i3 = 0;
        while (i3 < this.SYS_N / 8) {
            int i4 = i3 + 1;
            bArr5[i4] = (byte) ((bArr4[i3] & decrypt) | ((~decrypt) & bArr3[i3 + 40 + this.IRR_BYTES + this.COND_BYTES]));
            i3 = i4;
        }
        for (int i5 = 0; i5 < this.SYND_BYTES; i5++) {
            bArr5[(this.SYS_N / 8) + 1 + i5] = bArr2[i5];
        }
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr5, 0, i2);
        sHAKEDigest.doFinal(bArr, 0, bArr.length);
        if (!this.usePadding) {
            return 0;
        }
        byte b = (byte) check_c_padding;
        for (int i6 = 0; i6 < bArr.length; i6++) {
            bArr[i6] = (byte) (bArr[i6] | b);
        }
        return check_c_padding;
    }

    public int kem_enc(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        int i = this.SYS_N / 8;
        byte[] bArr4 = new byte[i];
        int check_pk_padding = this.usePadding ? check_pk_padding(bArr3) : 0;
        encrypt(bArr, bArr3, bArr4, secureRandom);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 1);
        sHAKEDigest.update(bArr4, 0, i);
        sHAKEDigest.update(bArr, 0, bArr.length);
        sHAKEDigest.doFinal(bArr2, 0, bArr2.length);
        if (!this.usePadding) {
            return 0;
        }
        byte b = (byte) (((byte) check_pk_padding) ^ UByte.MAX_VALUE);
        for (int i2 = 0; i2 < this.SYND_BYTES; i2++) {
            bArr[i2] = (byte) (bArr[i2] & b);
        }
        for (int i3 = 0; i3 < 32; i3++) {
            bArr2[i3] = (byte) (bArr2[i3] & b);
        }
        return check_pk_padding;
    }

    public void kem_keypair(byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        int i;
        int i2;
        int i3;
        short[] sArr;
        int i4;
        long j;
        int i5 = 32;
        byte[] bArr3 = new byte[32];
        int i6 = 1;
        int i7 = 0;
        byte[] bArr4 = {JSONB.Constants.BC_INT32_SHORT_MIN};
        secureRandom.nextBytes(bArr3);
        int i8 = (this.SYS_N / 8) + ((1 << this.GFBITS) * 4) + (this.SYS_T * 2);
        int i9 = i8 + 32;
        byte[] bArr5 = new byte[i9];
        long[] jArr = {0};
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        byte[] bArr6 = bArr3;
        while (true) {
            sHAKEDigest.update(bArr4, i7, i6);
            sHAKEDigest.update(bArr3, i7, bArr3.length);
            sHAKEDigest.doFinal(bArr5, i7, i9);
            byte[] copyOfRange = Arrays.copyOfRange(bArr5, i8, i8 + 32);
            System.arraycopy(bArr6, i7, bArr2, i7, i5);
            byte[] copyOfRange2 = Arrays.copyOfRange(copyOfRange, i7, i5);
            int i10 = this.SYS_T;
            short[] sArr2 = new short[i10];
            int i11 = i8 - (i10 * 2);
            i = i6;
            for (int i12 = i7; i12 < this.SYS_T; i12++) {
                sArr2[i12] = Utils.load_gf(bArr5, (i12 * 2) + i11, this.GFMASK);
            }
            if (generate_irr_poly(sArr2) != -1) {
                for (int i13 = i7; i13 < this.SYS_T; i13++) {
                    Utils.store_gf(bArr2, 40 + (i13 * 2), sArr2[i13]);
                }
                int i14 = this.GFBITS;
                int[] iArr = new int[i << i14];
                i2 = i11 - ((i << i14) * 4);
                int i15 = 0;
                while (true) {
                    i3 = this.GFBITS;
                    if (i15 >= (i << i3)) {
                        break;
                    }
                    iArr[i15] = Utils.load4(bArr5, (i15 * 4) + i2);
                    i15++;
                }
                sArr = new short[i << i3];
                if (pk_gen(bArr, bArr2, iArr, sArr, jArr) != -1) {
                    break;
                }
            }
            bArr3 = copyOfRange;
            bArr6 = copyOfRange2;
            i6 = i;
            i5 = 32;
            i7 = 0;
        }
        int i16 = this.COND_BYTES;
        byte[] bArr7 = new byte[i16];
        controlbitsfrompermutation(bArr7, sArr, this.GFBITS, i << r6);
        System.arraycopy(bArr7, 0, bArr2, this.IRR_BYTES + 40, i16);
        int i17 = this.SYS_N;
        System.arraycopy(bArr5, i2 - (i17 / 8), bArr2, bArr2.length - (i17 / 8), i17 / 8);
        if (this.usePivots) {
            i4 = 32;
            j = jArr[0];
        } else {
            j = 4294967295L;
            i4 = 32;
        }
        Utils.store8(bArr2, i4, j);
    }
}
