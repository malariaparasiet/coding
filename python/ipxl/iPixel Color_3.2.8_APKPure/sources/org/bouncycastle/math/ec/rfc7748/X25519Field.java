package org.bouncycastle.math.ec.rfc7748;

import kotlin.UByte;
import org.bouncycastle.math.raw.Mod;

/* loaded from: classes4.dex */
public abstract class X25519Field {
    private static final int M24 = 16777215;
    private static final int M25 = 33554431;
    private static final int M26 = 67108863;
    private static final int[] P32 = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    private static final int[] ROOT_NEG_ONE = {-32595792, -7943725, 4688975, 3500415, 6194736, 33281959, -12573105, -1002827, 163343, 5703241};
    public static final int SIZE = 10;

    protected X25519Field() {
    }

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i = 0; i < 10; i++) {
            iArr3[i] = iArr[i] + iArr2[i];
        }
    }

    public static void addOne(int[] iArr) {
        iArr[0] = iArr[0] + 1;
    }

    public static void addOne(int[] iArr, int i) {
        iArr[i] = iArr[i] + 1;
    }

    public static void apm(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        for (int i = 0; i < 10; i++) {
            int i2 = iArr[i];
            int i3 = iArr2[i];
            iArr3[i] = i2 + i3;
            iArr4[i] = i2 - i3;
        }
    }

    public static int areEqual(int[] iArr, int[] iArr2) {
        int i = 0;
        for (int i2 = 0; i2 < 10; i2++) {
            i |= iArr[i2] ^ iArr2[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static boolean areEqualVar(int[] iArr, int[] iArr2) {
        return areEqual(iArr, iArr2) != 0;
    }

    public static void carry(int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = i3 + (i2 >> 26);
        int i12 = i2 & M26;
        int i13 = i5 + (i4 >> 26);
        int i14 = i4 & M26;
        int i15 = i8 + (i7 >> 26);
        int i16 = i7 & M26;
        int i17 = i10 + (i9 >> 26);
        int i18 = i9 & M26;
        int i19 = i14 + (i11 >> 25);
        int i20 = i11 & M25;
        int i21 = i6 + (i13 >> 25);
        int i22 = i13 & M25;
        int i23 = i18 + (i15 >> 25);
        int i24 = i15 & M25;
        int i25 = i + ((i17 >> 25) * 38);
        int i26 = i17 & M25;
        int i27 = i12 + (i25 >> 26);
        int i28 = i25 & M26;
        int i29 = i16 + (i21 >> 26);
        int i30 = i21 & M26;
        int i31 = i20 + (i27 >> 26);
        int i32 = i27 & M26;
        int i33 = i22 + (i19 >> 26);
        int i34 = i19 & M26;
        int i35 = i24 + (i29 >> 26);
        int i36 = i29 & M26;
        int i37 = i26 + (i23 >> 26);
        int i38 = i23 & M26;
        iArr[0] = i28;
        iArr[1] = i32;
        iArr[2] = i31;
        iArr[3] = i34;
        iArr[4] = i33;
        iArr[5] = i30;
        iArr[6] = i36;
        iArr[7] = i35;
        iArr[8] = i38;
        iArr[9] = i37;
    }

    public static void cmov(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 0; i4 < 10; i4++) {
            int i5 = i3 + i4;
            int i6 = iArr2[i5];
            iArr2[i5] = i6 ^ ((iArr[i2 + i4] ^ i6) & i);
        }
    }

    public static void cnegate(int i, int[] iArr) {
        int i2 = 0 - i;
        for (int i3 = 0; i3 < 10; i3++) {
            iArr[i3] = (iArr[i3] ^ i2) - i2;
        }
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 0; i3 < 10; i3++) {
            iArr2[i2 + i3] = iArr[i + i3];
        }
    }

    public static int[] create() {
        return new int[10];
    }

    public static int[] createTable(int i) {
        return new int[i * 10];
    }

    public static void cswap(int i, int[] iArr, int[] iArr2) {
        int i2 = 0 - i;
        for (int i3 = 0; i3 < 10; i3++) {
            int i4 = iArr[i3];
            int i5 = iArr2[i3];
            int i6 = (i4 ^ i5) & i2;
            iArr[i3] = i4 ^ i6;
            iArr2[i3] = i5 ^ i6;
        }
    }

    public static void decode(byte[] bArr, int i, int[] iArr) {
        decode128(bArr, i, iArr, 0);
        decode128(bArr, i + 16, iArr, 5);
        iArr[9] = iArr[9] & 16777215;
    }

    public static void decode(byte[] bArr, int i, int[] iArr, int i2) {
        decode128(bArr, i, iArr, i2);
        decode128(bArr, i + 16, iArr, i2 + 5);
        int i3 = i2 + 9;
        iArr[i3] = iArr[i3] & 16777215;
    }

    public static void decode(byte[] bArr, int[] iArr) {
        decode128(bArr, 0, iArr, 0);
        decode128(bArr, 16, iArr, 5);
        iArr[9] = iArr[9] & 16777215;
    }

    public static void decode(int[] iArr, int i, int[] iArr2) {
        decode128(iArr, i, iArr2, 0);
        decode128(iArr, i + 4, iArr2, 5);
        iArr2[9] = iArr2[9] & 16777215;
    }

    private static void decode128(byte[] bArr, int i, int[] iArr, int i2) {
        int decode32 = decode32(bArr, i);
        int decode322 = decode32(bArr, i + 4);
        int decode323 = decode32(bArr, i + 8);
        int decode324 = decode32(bArr, i + 12);
        iArr[i2] = decode32 & M26;
        iArr[i2 + 1] = ((decode32 >>> 26) | (decode322 << 6)) & M26;
        iArr[i2 + 2] = ((decode322 >>> 20) | (decode323 << 12)) & M25;
        iArr[i2 + 3] = M26 & ((decode324 << 19) | (decode323 >>> 13));
        iArr[i2 + 4] = decode324 >>> 7;
    }

    private static void decode128(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = iArr[i];
        int i4 = iArr[i + 1];
        int i5 = iArr[i + 2];
        int i6 = iArr[i + 3];
        iArr2[i2] = i3 & M26;
        iArr2[i2 + 1] = ((i3 >>> 26) | (i4 << 6)) & M26;
        iArr2[i2 + 2] = ((i4 >>> 20) | (i5 << 12)) & M25;
        iArr2[i2 + 3] = M26 & ((i6 << 19) | (i5 >>> 13));
        iArr2[i2 + 4] = i6 >>> 7;
    }

    private static int decode32(byte[] bArr, int i) {
        return (bArr[i + 3] << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    public static void encode(int[] iArr, int i, byte[] bArr, int i2) {
        encode128(iArr, i, bArr, i2);
        encode128(iArr, i + 5, bArr, i2 + 16);
    }

    public static void encode(int[] iArr, byte[] bArr) {
        encode128(iArr, 0, bArr, 0);
        encode128(iArr, 5, bArr, 16);
    }

    public static void encode(int[] iArr, byte[] bArr, int i) {
        encode128(iArr, 0, bArr, i);
        encode128(iArr, 5, bArr, i + 16);
    }

    public static void encode(int[] iArr, int[] iArr2, int i) {
        encode128(iArr, 0, iArr2, i);
        encode128(iArr, 5, iArr2, i + 4);
    }

    private static void encode128(int[] iArr, int i, byte[] bArr, int i2) {
        int i3 = iArr[i];
        int i4 = iArr[i + 1];
        int i5 = iArr[i + 2];
        int i6 = iArr[i + 3];
        int i7 = iArr[i + 4];
        encode32((i4 << 26) | i3, bArr, i2);
        encode32((i4 >>> 6) | (i5 << 20), bArr, i2 + 4);
        encode32((i5 >>> 12) | (i6 << 13), bArr, i2 + 8);
        encode32((i7 << 7) | (i6 >>> 19), bArr, i2 + 12);
    }

    private static void encode128(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = iArr[i];
        int i4 = iArr[i + 1];
        int i5 = iArr[i + 2];
        int i6 = iArr[i + 3];
        int i7 = iArr[i + 4];
        iArr2[i2] = (i4 << 26) | i3;
        iArr2[i2 + 1] = (i4 >>> 6) | (i5 << 20);
        iArr2[i2 + 2] = (i5 >>> 12) | (i6 << 13);
        iArr2[i2 + 3] = (i7 << 7) | (i6 >>> 19);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    public static void inv(int[] iArr, int[] iArr2) {
        int[] create = create();
        int[] iArr3 = new int[8];
        copy(iArr, 0, create, 0);
        normalize(create);
        encode(create, iArr3, 0);
        Mod.modOddInverse(P32, iArr3, iArr3);
        decode(iArr3, 0, iArr2);
    }

    public static void invVar(int[] iArr, int[] iArr2) {
        int[] create = create();
        int[] iArr3 = new int[8];
        copy(iArr, 0, create, 0);
        normalize(create);
        encode(create, iArr3, 0);
        Mod.modOddInverseVar(P32, iArr3, iArr3);
        decode(iArr3, 0, iArr2);
    }

    public static int isOne(int[] iArr) {
        int i = iArr[0] ^ 1;
        for (int i2 = 1; i2 < 10; i2++) {
            i |= iArr[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static boolean isOneVar(int[] iArr) {
        return isOne(iArr) != 0;
    }

    public static int isZero(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 10; i2++) {
            i |= iArr[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static boolean isZeroVar(int[] iArr) {
        return isZero(iArr) != 0;
    }

    public static void mul(int[] iArr, int i, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        long j = i;
        long j2 = i4 * j;
        int i12 = ((int) j2) & M25;
        long j3 = j2 >> 25;
        long j4 = i6 * j;
        int i13 = ((int) j4) & M25;
        long j5 = i9 * j;
        int i14 = ((int) j5) & M25;
        long j6 = i11 * j;
        int i15 = ((int) j6) & M25;
        long j7 = ((j6 >> 25) * 38) + (i2 * j);
        iArr2[0] = ((int) j7) & M26;
        long j8 = (j4 >> 25) + (i7 * j);
        iArr2[5] = ((int) j8) & M26;
        long j9 = (j7 >> 26) + (i3 * j);
        iArr2[1] = ((int) j9) & M26;
        long j10 = j3 + (i5 * j);
        iArr2[3] = ((int) j10) & M26;
        long j11 = (j8 >> 26) + (i8 * j);
        iArr2[6] = ((int) j11) & M26;
        long j12 = (j5 >> 25) + (i10 * j);
        iArr2[8] = ((int) j12) & M26;
        iArr2[2] = i12 + ((int) (j9 >> 26));
        iArr2[4] = i13 + ((int) (j10 >> 26));
        iArr2[7] = i14 + ((int) (j11 >> 26));
        iArr2[9] = i15 + ((int) (j12 >> 26));
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = iArr[0];
        int i2 = iArr2[0];
        int i3 = iArr[1];
        int i4 = iArr2[1];
        int i5 = iArr[2];
        int i6 = iArr2[2];
        int i7 = iArr[3];
        int i8 = iArr2[3];
        int i9 = iArr[4];
        int i10 = iArr2[4];
        int i11 = iArr[5];
        int i12 = iArr2[5];
        int i13 = iArr[6];
        int i14 = iArr2[6];
        int i15 = iArr[7];
        int i16 = iArr2[7];
        int i17 = iArr[8];
        int i18 = iArr2[8];
        int i19 = iArr[9];
        int i20 = iArr2[9];
        long j = i;
        long j2 = i2;
        long j3 = j * j2;
        long j4 = i4;
        long j5 = j * j4;
        long j6 = i3;
        long j7 = j5 + (j6 * j2);
        long j8 = i6;
        long j9 = (j * j8) + (j6 * j4);
        long j10 = i5;
        long j11 = j9 + (j10 * j2);
        long j12 = ((j6 * j8) + (j10 * j4)) << 1;
        long j13 = i8;
        long j14 = j * j13;
        long j15 = i7;
        long j16 = j12 + j14 + (j15 * j2);
        long j17 = (j10 * j8) << 1;
        long j18 = i10;
        long j19 = i9;
        long j20 = j17 + (j * j18) + (j6 * j13) + (j15 * j4) + (j2 * j19);
        long j21 = ((((j6 * j18) + (j10 * j13)) + (j15 * j8)) + (j19 * j4)) << 1;
        long j22 = (((j10 * j18) + (j19 * j8)) << 1) + (j15 * j13);
        long j23 = (j15 * j18) + (j19 * j13);
        long j24 = (j19 * j18) << 1;
        long j25 = i11;
        long j26 = i12;
        long j27 = j25 * j26;
        long j28 = i14;
        long j29 = j25 * j28;
        long j30 = i13;
        long j31 = j29 + (j30 * j26);
        long j32 = i16;
        long j33 = (j25 * j32) + (j30 * j28);
        long j34 = i15;
        long j35 = i18;
        long j36 = j25 * j35;
        long j37 = i17;
        long j38 = (((j30 * j32) + (j34 * j28)) << 1) + j36 + (j37 * j26);
        long j39 = i20;
        long j40 = (j25 * j39) + (j30 * j35) + (j37 * j28);
        long j41 = i19;
        long j42 = ((j34 * j32) << 1) + j40 + (j26 * j41);
        long j43 = j3 - (((((j30 * j39) + (j34 * j35)) + (j37 * j32)) + (j41 * j28)) * 76);
        long j44 = j7 - (((((j34 * j39) + (j41 * j32)) << 1) + (j37 * j35)) * 38);
        long j45 = j11 - (((j37 * j39) + (j41 * j35)) * 38);
        long j46 = j16 - ((j41 * j39) * 76);
        long j47 = j21 - j27;
        long j48 = j22 - j31;
        long j49 = j23 - (j33 + (j34 * j26));
        long j50 = j24 - j38;
        int i21 = i2 + i12;
        int i22 = i3 + i13;
        long j51 = i + i11;
        long j52 = i21;
        long j53 = j51 * j52;
        long j54 = i4 + i14;
        long j55 = j51 * j54;
        long j56 = i22;
        long j57 = j55 + (j56 * j52);
        long j58 = i6 + i16;
        long j59 = i5 + i15;
        long j60 = (j51 * j58) + (j56 * j54) + (j59 * j52);
        long j61 = ((j56 * j58) + (j59 * j54)) << 1;
        long j62 = i8 + i18;
        long j63 = i7 + i17;
        long j64 = j61 + (j51 * j62) + (j63 * j52);
        long j65 = i10 + i20;
        long j66 = i9 + i19;
        long j67 = ((j59 * j58) << 1) + (j51 * j65) + (j56 * j62) + (j63 * j54) + (j66 * j52);
        long j68 = ((((j56 * j65) + (j59 * j62)) + (j63 * j58)) + (j66 * j54)) << 1;
        long j69 = (((j59 * j65) + (j58 * j66)) << 1) + (j63 * j62);
        long j70 = j50 + (j64 - j46);
        int i23 = ((int) j70) & M26;
        long j71 = (j70 >> 26) + ((j67 - j20) - j42);
        int i24 = ((int) j71) & M25;
        long j72 = j43 + ((((j71 >> 25) + j68) - j47) * 38);
        iArr3[0] = ((int) j72) & M26;
        long j73 = (j72 >> 26) + j44 + ((j69 - j48) * 38);
        iArr3[1] = ((int) j73) & M26;
        long j74 = (j73 >> 26) + j45 + ((((j63 * j65) + (j66 * j62)) - j49) * 38);
        iArr3[2] = ((int) j74) & M25;
        long j75 = (j74 >> 25) + j46 + ((((j66 * j65) << 1) - j50) * 38);
        iArr3[3] = ((int) j75) & M26;
        long j76 = (j75 >> 26) + j20 + (j42 * 38);
        iArr3[4] = ((int) j76) & M25;
        long j77 = (j76 >> 25) + j47 + (j53 - j43);
        iArr3[5] = ((int) j77) & M26;
        long j78 = (j77 >> 26) + j48 + (j57 - j44);
        iArr3[6] = ((int) j78) & M26;
        long j79 = (j78 >> 26) + j49 + (j60 - j45);
        iArr3[7] = ((int) j79) & M25;
        long j80 = (j79 >> 25) + i23;
        iArr3[8] = ((int) j80) & M26;
        iArr3[9] = i24 + ((int) (j80 >> 26));
    }

    public static void negate(int[] iArr, int[] iArr2) {
        for (int i = 0; i < 10; i++) {
            iArr2[i] = -iArr[i];
        }
    }

    public static void normalize(int[] iArr) {
        int i = (iArr[9] >>> 23) & 1;
        reduce(iArr, i);
        reduce(iArr, -i);
    }

    public static void one(int[] iArr) {
        iArr[0] = 1;
        for (int i = 1; i < 10; i++) {
            iArr[i] = 0;
        }
    }

    private static void powPm5d8(int[] iArr, int[] iArr2, int[] iArr3) {
        sqr(iArr, iArr2);
        mul(iArr, iArr2, iArr2);
        int[] create = create();
        sqr(iArr2, create);
        mul(iArr, create, create);
        sqr(create, 2, create);
        mul(iArr2, create, create);
        int[] create2 = create();
        sqr(create, 5, create2);
        mul(create, create2, create2);
        int[] create3 = create();
        sqr(create2, 5, create3);
        mul(create, create3, create3);
        sqr(create3, 10, create);
        mul(create2, create, create);
        sqr(create, 25, create2);
        mul(create, create2, create2);
        sqr(create2, 25, create3);
        mul(create, create3, create3);
        sqr(create3, 50, create);
        mul(create2, create, create);
        sqr(create, 125, create2);
        mul(create, create2, create2);
        sqr(create2, 2, create);
        mul(create, iArr, iArr3);
    }

    private static void reduce(int[] iArr, int i) {
        int i2 = iArr[9];
        long j = (((i2 >> 24) + i) * 19) + iArr[0];
        iArr[0] = ((int) j) & M26;
        long j2 = (j >> 26) + iArr[1];
        iArr[1] = ((int) j2) & M26;
        long j3 = (j2 >> 26) + iArr[2];
        iArr[2] = ((int) j3) & M25;
        long j4 = (j3 >> 25) + iArr[3];
        iArr[3] = ((int) j4) & M26;
        long j5 = (j4 >> 26) + iArr[4];
        iArr[4] = ((int) j5) & M25;
        long j6 = (j5 >> 25) + iArr[5];
        iArr[5] = ((int) j6) & M26;
        long j7 = (j6 >> 26) + iArr[6];
        iArr[6] = ((int) j7) & M26;
        long j8 = (j7 >> 26) + iArr[7];
        iArr[7] = M25 & ((int) j8);
        long j9 = (j8 >> 25) + iArr[8];
        iArr[8] = M26 & ((int) j9);
        iArr[9] = (16777215 & i2) + ((int) (j9 >> 26));
    }

    public static void sqr(int[] iArr, int i, int[] iArr2) {
        sqr(iArr, iArr2);
        while (true) {
            i--;
            if (i <= 0) {
                return;
            } else {
                sqr(iArr2, iArr2);
            }
        }
    }

    public static void sqr(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        long j = i;
        long j2 = j * j;
        long j3 = i2 * 2;
        long j4 = j * j3;
        long j5 = i3 * 2;
        long j6 = j * j5;
        long j7 = i2;
        long j8 = j6 + (j7 * j7);
        long j9 = i4 * 2;
        long j10 = (j3 * j5) + (j * j9);
        long j11 = i5 * 2;
        long j12 = (i3 * j5) + (j * j11) + (j7 * j9);
        long j13 = (j3 * j11) + (j5 * j9);
        long j14 = j5 * j11;
        long j15 = i4;
        long j16 = j14 + (j15 * j15);
        long j17 = j15 * j11;
        long j18 = i6;
        long j19 = j18 * j18;
        long j20 = i7 * 2;
        long j21 = j18 * j20;
        long j22 = i8 * 2;
        long j23 = j18 * j22;
        long j24 = i7;
        long j25 = j23 + (j24 * j24);
        long j26 = j20 * j22;
        long j27 = i9 * 2;
        long j28 = i10 * 2;
        long j29 = (i8 * j22) + (j18 * j28) + (j24 * j27);
        long j30 = (j20 * j28) + (j22 * j27);
        long j31 = j22 * j28;
        long j32 = i9;
        long j33 = j2 - (j30 * 38);
        long j34 = j4 - ((j31 + (j32 * j32)) * 38);
        long j35 = j8 - ((j32 * j28) * 38);
        long j36 = j10 - ((i10 * j28) * 38);
        long j37 = j13 - j19;
        long j38 = j16 - j21;
        long j39 = j17 - j25;
        long j40 = (i5 * j11) - (j26 + (j18 * j27));
        int i11 = i2 + i7;
        int i12 = i3 + i8;
        int i13 = i4 + i9;
        int i14 = i5 + i10;
        long j41 = i + i6;
        long j42 = j41 * j41;
        long j43 = i11 * 2;
        long j44 = j41 * j43;
        long j45 = i12 * 2;
        long j46 = i11;
        long j47 = (j41 * j45) + (j46 * j46);
        long j48 = i13 * 2;
        long j49 = (j43 * j45) + (j41 * j48);
        long j50 = i14 * 2;
        long j51 = (i12 * j45) + (j41 * j50) + (j46 * j48);
        long j52 = (j43 * j50) + (j48 * j45);
        long j53 = i13;
        long j54 = (j45 * j50) + (j53 * j53);
        long j55 = j53 * j50;
        long j56 = i14 * j50;
        long j57 = j40 + (j49 - j36);
        int i15 = ((int) j57) & M26;
        long j58 = (j57 >> 26) + ((j51 - j12) - j29);
        int i16 = ((int) j58) & M25;
        long j59 = j33 + ((((j58 >> 25) + j52) - j37) * 38);
        iArr2[0] = ((int) j59) & M26;
        long j60 = (j59 >> 26) + j34 + ((j54 - j38) * 38);
        iArr2[1] = ((int) j60) & M26;
        long j61 = (j60 >> 26) + j35 + ((j55 - j39) * 38);
        iArr2[2] = ((int) j61) & M25;
        long j62 = (j61 >> 25) + j36 + ((j56 - j40) * 38);
        iArr2[3] = ((int) j62) & M26;
        long j63 = (j62 >> 26) + j12 + (j29 * 38);
        iArr2[4] = ((int) j63) & M25;
        long j64 = (j63 >> 25) + j37 + (j42 - j33);
        iArr2[5] = ((int) j64) & M26;
        long j65 = (j64 >> 26) + j38 + (j44 - j34);
        iArr2[6] = ((int) j65) & M26;
        long j66 = (j65 >> 26) + j39 + (j47 - j35);
        iArr2[7] = ((int) j66) & M25;
        long j67 = (j66 >> 25) + i15;
        iArr2[8] = ((int) j67) & M26;
        iArr2[9] = i16 + ((int) (j67 >> 26));
    }

    public static boolean sqrtRatioVar(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = create();
        int[] create2 = create();
        mul(iArr, iArr2, create);
        sqr(iArr2, create2);
        mul(create, create2, create);
        sqr(create2, create2);
        mul(create2, create, create2);
        int[] create3 = create();
        int[] create4 = create();
        powPm5d8(create2, create3, create4);
        mul(create4, create, create4);
        int[] create5 = create();
        sqr(create4, create5);
        mul(create5, iArr2, create5);
        sub(create5, iArr, create3);
        normalize(create3);
        if (isZeroVar(create3)) {
            copy(create4, 0, iArr3, 0);
            return true;
        }
        add(create5, iArr, create3);
        normalize(create3);
        if (!isZeroVar(create3)) {
            return false;
        }
        mul(create4, ROOT_NEG_ONE, iArr3);
        return true;
    }

    public static void sub(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i = 0; i < 10; i++) {
            iArr3[i] = iArr[i] - iArr2[i];
        }
    }

    public static void subOne(int[] iArr) {
        iArr[0] = iArr[0] - 1;
    }

    public static void zero(int[] iArr) {
        for (int i = 0; i < 10; i++) {
            iArr[i] = 0;
        }
    }
}
