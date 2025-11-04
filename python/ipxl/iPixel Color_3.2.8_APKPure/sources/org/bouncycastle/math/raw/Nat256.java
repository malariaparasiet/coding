package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
public abstract class Nat256 {
    private static final long M = 4294967295L;

    public static int add(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = (iArr[i] & M) + (iArr2[i2] & M);
        iArr3[i3] = (int) j;
        long j2 = (j >>> 32) + (iArr[i + 1] & M) + (iArr2[i2 + 1] & M);
        iArr3[i3 + 1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[i + 2] & M) + (iArr2[i2 + 2] & M);
        iArr3[i3 + 2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[i + 3] & M) + (iArr2[i2 + 3] & M);
        iArr3[i3 + 3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[i + 4] & M) + (iArr2[i2 + 4] & M);
        iArr3[i3 + 4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[i + 5] & M) + (iArr2[i2 + 5] & M);
        iArr3[i3 + 5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[i + 6] & M) + (iArr2[i2 + 6] & M);
        iArr3[i3 + 6] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[i + 7] & M) + (iArr2[i2 + 7] & M);
        iArr3[i3 + 7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int add(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & M) + (iArr2[0] & M);
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & M) + (iArr2[1] & M);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & M) + (iArr2[2] & M);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & M) + (iArr2[3] & M);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & M) + (iArr2[4] & M);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[5] & M) + (iArr2[5] & M);
        iArr3[5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[6] & M) + (iArr2[6] & M);
        iArr3[6] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[7] & M) + (iArr2[7] & M);
        iArr3[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addBothTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = (iArr[i] & M) + (iArr2[i2] & M) + (iArr3[i3] & M);
        iArr3[i3] = (int) j;
        long j2 = (j >>> 32) + (iArr[i + 1] & M) + (iArr2[i2 + 1] & M) + (iArr3[r7] & M);
        iArr3[i3 + 1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[i + 2] & M) + (iArr2[i2 + 2] & M) + (iArr3[r7] & M);
        iArr3[i3 + 2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[i + 3] & M) + (iArr2[i2 + 3] & M) + (iArr3[r7] & M);
        iArr3[i3 + 3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[i + 4] & M) + (iArr2[i2 + 4] & M) + (iArr3[r7] & M);
        iArr3[i3 + 4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[i + 5] & M) + (iArr2[i2 + 5] & M) + (iArr3[r7] & M);
        iArr3[i3 + 5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[i + 6] & M) + (iArr2[i2 + 6] & M) + (iArr3[r7] & M);
        iArr3[i3 + 6] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[i + 7] & M) + (iArr2[i2 + 7] & M) + (iArr3[r15] & M);
        iArr3[i3 + 7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addBothTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & M) + (iArr2[0] & M) + (iArr3[0] & M);
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & M) + (iArr2[1] & M) + (iArr3[1] & M);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & M) + (iArr2[2] & M) + (iArr3[2] & M);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & M) + (iArr2[3] & M) + (iArr3[3] & M);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & M) + (iArr2[4] & M) + (iArr3[4] & M);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[5] & M) + (iArr2[5] & M) + (iArr3[5] & M);
        iArr3[5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[6] & M) + (iArr2[6] & M) + (iArr3[6] & M);
        iArr3[6] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[7] & M) + (iArr2[7] & M) + (iArr3[7] & M);
        iArr3[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addTo(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        long j = (i3 & M) + (iArr[i] & M) + (iArr2[i2] & M);
        iArr2[i2] = (int) j;
        long j2 = (j >>> 32) + (iArr[i + 1] & M) + (iArr2[r6] & M);
        iArr2[i2 + 1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[i + 2] & M) + (iArr2[r6] & M);
        iArr2[i2 + 2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[i + 3] & M) + (iArr2[r6] & M);
        iArr2[i2 + 3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[i + 4] & M) + (iArr2[r6] & M);
        iArr2[i2 + 4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[i + 5] & M) + (iArr2[r6] & M);
        iArr2[i2 + 5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[i + 6] & M) + (iArr2[r6] & M);
        iArr2[i2 + 6] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[i + 7] & M) + (M & iArr2[r12]);
        iArr2[i2 + 7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2) {
        long j = (iArr[0] & M) + (iArr2[0] & M);
        iArr2[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & M) + (iArr2[1] & M);
        iArr2[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & M) + (iArr2[2] & M);
        iArr2[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & M) + (iArr2[3] & M);
        iArr2[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & M) + (iArr2[4] & M);
        iArr2[4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[5] & M) + (iArr2[5] & M);
        iArr2[5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[6] & M) + (iArr2[6] & M);
        iArr2[6] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[7] & M) + (M & iArr2[7]);
        iArr2[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2, int i) {
        long j = (i & M) + (iArr[0] & M) + (iArr2[0] & M);
        iArr2[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & M) + (iArr2[1] & M);
        iArr2[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & M) + (iArr2[2] & M);
        iArr2[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & M) + (iArr2[3] & M);
        iArr2[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & M) + (iArr2[4] & M);
        iArr2[4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[5] & M) + (iArr2[5] & M);
        iArr2[5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[6] & M) + (iArr2[6] & M);
        iArr2[6] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[7] & M) + (M & iArr2[7]);
        iArr2[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addToEachOther(int[] iArr, int i, int[] iArr2, int i2) {
        long j = (iArr[i] & M) + (iArr2[i2] & M);
        int i3 = (int) j;
        iArr[i] = i3;
        iArr2[i2] = i3;
        long j2 = (j >>> 32) + (iArr[r5] & M) + (iArr2[r8] & M);
        int i4 = (int) j2;
        iArr[i + 1] = i4;
        iArr2[i2 + 1] = i4;
        long j3 = (j2 >>> 32) + (iArr[r5] & M) + (iArr2[r8] & M);
        int i5 = (int) j3;
        iArr[i + 2] = i5;
        iArr2[i2 + 2] = i5;
        long j4 = (j3 >>> 32) + (iArr[r5] & M) + (iArr2[r8] & M);
        int i6 = (int) j4;
        iArr[i + 3] = i6;
        iArr2[i2 + 3] = i6;
        long j5 = (j4 >>> 32) + (iArr[r5] & M) + (iArr2[r8] & M);
        int i7 = (int) j5;
        iArr[i + 4] = i7;
        iArr2[i2 + 4] = i7;
        long j6 = (j5 >>> 32) + (iArr[r5] & M) + (iArr2[r8] & M);
        int i8 = (int) j6;
        iArr[i + 5] = i8;
        iArr2[i2 + 5] = i8;
        long j7 = (j6 >>> 32) + (iArr[r5] & M) + (iArr2[r8] & M);
        int i9 = (int) j7;
        iArr[i + 6] = i9;
        iArr2[i2 + 6] = i9;
        long j8 = (j7 >>> 32) + (iArr[r12] & M) + (M & iArr2[r14]);
        int i10 = (int) j8;
        iArr[i + 7] = i10;
        iArr2[i2 + 7] = i10;
        return (int) (j8 >>> 32);
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        iArr2[i2] = iArr[i];
        iArr2[i2 + 1] = iArr[i + 1];
        iArr2[i2 + 2] = iArr[i + 2];
        iArr2[i2 + 3] = iArr[i + 3];
        iArr2[i2 + 4] = iArr[i + 4];
        iArr2[i2 + 5] = iArr[i + 5];
        iArr2[i2 + 6] = iArr[i + 6];
        iArr2[i2 + 7] = iArr[i + 7];
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
        iArr2[6] = iArr[6];
        iArr2[7] = iArr[7];
    }

    public static void copy64(long[] jArr, int i, long[] jArr2, int i2) {
        jArr2[i2] = jArr[i];
        jArr2[i2 + 1] = jArr[i + 1];
        jArr2[i2 + 2] = jArr[i + 2];
        jArr2[i2 + 3] = jArr[i + 3];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    public static int[] create() {
        return new int[8];
    }

    public static long[] create64() {
        return new long[4];
    }

    public static int[] createExt() {
        return new int[16];
    }

    public static long[] createExt64() {
        return new long[8];
    }

    public static boolean diff(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        boolean gte = gte(iArr, i, iArr2, i2);
        if (gte) {
            sub(iArr, i, iArr2, i2, iArr3, i3);
            return gte;
        }
        sub(iArr2, i2, iArr, i, iArr3, i3);
        return gte;
    }

    public static boolean eq(int[] iArr, int[] iArr2) {
        for (int i = 7; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int i = 3; i >= 0; i--) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int i = 0; i < 8; i++) {
            create[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int i = 0; i < 4; i++) {
            create64[i] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return create64;
    }

    public static int getBit(int[] iArr, int i) {
        int i2;
        if (i == 0) {
            i2 = iArr[0];
        } else {
            if ((i & 255) != i) {
                return 0;
            }
            i2 = iArr[i >>> 5] >>> (i & 31);
        }
        return i2 & 1;
    }

    public static boolean gte(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 7; i3 >= 0; i3--) {
            int i4 = iArr[i + i3] ^ Integer.MIN_VALUE;
            int i5 = Integer.MIN_VALUE ^ iArr2[i2 + i3];
            if (i4 < i5) {
                return false;
            }
            if (i4 > i5) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i = 7; i >= 0; i--) {
            int i2 = iArr[i] ^ Integer.MIN_VALUE;
            int i3 = Integer.MIN_VALUE ^ iArr2[i];
            if (i2 < i3) {
                return false;
            }
            if (i2 > i3) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 8; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i = 0; i < 8; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int i = 0; i < 4; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = iArr2[i2] & M;
        long j2 = iArr2[i2 + 1] & M;
        long j3 = iArr2[i2 + 2] & M;
        long j4 = iArr2[i2 + 3] & M;
        long j5 = iArr2[i2 + 4] & M;
        long j6 = iArr2[i2 + 5] & M;
        long j7 = iArr2[i2 + 6] & M;
        long j8 = iArr2[i2 + 7] & M;
        long j9 = iArr[i] & M;
        long j10 = j9 * j;
        iArr3[i3] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        iArr3[i3 + 1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        iArr3[i3 + 2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        iArr3[i3 + 3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        iArr3[i3 + 4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        iArr3[i3 + 5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        iArr3[i3 + 6] = (int) j16;
        long j17 = (j16 >>> 32) + (j9 * j8);
        iArr3[i3 + 7] = (int) j17;
        iArr3[i3 + 8] = (int) (j17 >>> 32);
        int i4 = 1;
        int i5 = i3;
        while (i4 < 8) {
            int i6 = i5 + 1;
            int i7 = i5;
            long j18 = iArr[i + i4] & M;
            long j19 = (j18 * j) + (iArr3[i6] & M);
            iArr3[i6] = (int) j19;
            long j20 = (j19 >>> 32) + (j18 * j2) + (iArr3[r3] & M);
            iArr3[i7 + 2] = (int) j20;
            long j21 = (j20 >>> 32) + (j18 * j3) + (iArr3[r24] & M);
            iArr3[i7 + 3] = (int) j21;
            long j22 = (j21 >>> 32) + (j18 * j4) + (iArr3[r24] & M);
            iArr3[i7 + 4] = (int) j22;
            long j23 = (j22 >>> 32) + (j18 * j5) + (iArr3[r24] & M);
            iArr3[i7 + 5] = (int) j23;
            long j24 = (j23 >>> 32) + (j18 * j6) + (iArr3[r24] & M);
            iArr3[i7 + 6] = (int) j24;
            long j25 = (j24 >>> 32) + (j18 * j7) + (iArr3[r24] & M);
            iArr3[i7 + 7] = (int) j25;
            long j26 = (j25 >>> 32) + (j18 * j8) + (iArr3[r24] & M);
            iArr3[i7 + 8] = (int) j26;
            iArr3[i7 + 9] = (int) (j26 >>> 32);
            i4++;
            i5 = i6;
        }
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = iArr2[0] & M;
        long j2 = iArr2[1] & M;
        long j3 = iArr2[2] & M;
        long j4 = iArr2[3] & M;
        long j5 = iArr2[4] & M;
        long j6 = iArr2[5] & M;
        long j7 = iArr2[6] & M;
        long j8 = iArr2[7] & M;
        long j9 = iArr[0] & M;
        long j10 = j9 * j;
        iArr3[0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        iArr3[1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        iArr3[2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        iArr3[3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        iArr3[4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        iArr3[5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        iArr3[6] = (int) j16;
        long j17 = (j16 >>> 32) + (j9 * j8);
        iArr3[7] = (int) j17;
        iArr3[8] = (int) (j17 >>> 32);
        int i = 1;
        for (int i2 = 8; i < i2; i2 = 8) {
            long j18 = iArr[i] & M;
            long j19 = j5;
            long j20 = (j18 * j) + (iArr3[i] & M);
            iArr3[i] = (int) j20;
            int i3 = i + 1;
            long j21 = (j20 >>> 32) + (j18 * j2) + (iArr3[i3] & M);
            iArr3[i3] = (int) j21;
            long j22 = (j21 >>> 32) + (j18 * j3) + (iArr3[r25] & M);
            iArr3[i + 2] = (int) j22;
            long j23 = (j22 >>> 32) + (j18 * j4) + (iArr3[r25] & M);
            iArr3[i + 3] = (int) j23;
            long j24 = (j23 >>> 32) + (j18 * j19) + (iArr3[r25] & M);
            iArr3[i + 4] = (int) j24;
            long j25 = (j24 >>> 32) + (j18 * j6) + (iArr3[r25] & M);
            iArr3[i + 5] = (int) j25;
            long j26 = (j25 >>> 32) + (j18 * j7) + (iArr3[r25] & M);
            iArr3[i + 6] = (int) j26;
            long j27 = (j26 >>> 32) + (j18 * j8) + (iArr3[r14] & M);
            iArr3[i + 7] = (int) j27;
            iArr3[i + 8] = (int) (j27 >>> 32);
            i = i3;
            j5 = j19;
        }
    }

    public static void mul128(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = iArr[0] & M;
        long j2 = iArr[1] & M;
        long j3 = iArr[2] & M;
        long j4 = iArr[3] & M;
        long j5 = iArr[4] & M;
        long j6 = iArr[5] & M;
        long j7 = iArr[6] & M;
        long j8 = iArr[7] & M;
        long j9 = iArr2[0] & M;
        long j10 = j9 * j;
        iArr3[0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        iArr3[1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        iArr3[2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        iArr3[3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        iArr3[4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        iArr3[5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        iArr3[6] = (int) j16;
        long j17 = (j16 >>> 32) + (j9 * j8);
        iArr3[7] = (int) j17;
        iArr3[8] = (int) (j17 >>> 32);
        int i = 1;
        for (int i2 = 4; i < i2; i2 = 4) {
            long j18 = iArr2[i] & M;
            long j19 = j5;
            long j20 = (j18 * j) + (iArr3[i] & M);
            iArr3[i] = (int) j20;
            int i3 = i + 1;
            long j21 = (j20 >>> 32) + (j18 * j2) + (iArr3[i3] & M);
            iArr3[i3] = (int) j21;
            long j22 = (j21 >>> 32) + (j18 * j3) + (iArr3[r25] & M);
            iArr3[i + 2] = (int) j22;
            long j23 = (j22 >>> 32) + (j18 * j4) + (iArr3[r25] & M);
            iArr3[i + 3] = (int) j23;
            long j24 = (j23 >>> 32) + (j18 * j19) + (iArr3[r25] & M);
            iArr3[i + 4] = (int) j24;
            long j25 = (j24 >>> 32) + (j18 * j6) + (iArr3[r25] & M);
            iArr3[i + 5] = (int) j25;
            long j26 = (j25 >>> 32) + (j18 * j7) + (iArr3[r25] & M);
            iArr3[i + 6] = (int) j26;
            long j27 = (j26 >>> 32) + (j18 * j8) + (iArr3[r14] & M);
            iArr3[i + 7] = (int) j27;
            iArr3[i + 8] = (int) (j27 >>> 32);
            i = i3;
            j5 = j19;
        }
    }

    public static long mul33Add(int i, int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = i & M;
        long j2 = iArr[i2] & M;
        long j3 = (j * j2) + (iArr2[i3] & M);
        iArr3[i4] = (int) j3;
        long j4 = iArr[i2 + 1] & M;
        long j5 = (j3 >>> 32) + (j * j4) + j2 + (iArr2[i3 + 1] & M);
        iArr3[i4 + 1] = (int) j5;
        long j6 = j5 >>> 32;
        long j7 = iArr[i2 + 2] & M;
        long j8 = j6 + (j * j7) + j4 + (iArr2[i3 + 2] & M);
        iArr3[i4 + 2] = (int) j8;
        long j9 = iArr[i2 + 3] & M;
        long j10 = (j8 >>> 32) + (j * j9) + j7 + (iArr2[i3 + 3] & M);
        iArr3[i4 + 3] = (int) j10;
        long j11 = iArr[i2 + 4] & M;
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (iArr2[i3 + 4] & M);
        iArr3[i4 + 4] = (int) j12;
        long j13 = iArr[i2 + 5] & M;
        long j14 = (j12 >>> 32) + (j * j13) + j11 + (iArr2[i3 + 5] & M);
        iArr3[i4 + 5] = (int) j14;
        long j15 = iArr[i2 + 6] & M;
        long j16 = (j14 >>> 32) + (j * j15) + j13 + (iArr2[i3 + 6] & M);
        iArr3[i4 + 6] = (int) j16;
        long j17 = iArr[i2 + 7] & M;
        long j18 = (j16 >>> 32) + (j * j17) + j15 + (M & iArr2[i3 + 7]);
        iArr3[i4 + 7] = (int) j18;
        return (j18 >>> 32) + j17;
    }

    public static int mul33DWordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = i & M;
        long j3 = j & M;
        long j4 = (j2 * j3) + (iArr[i2] & M);
        iArr[i2] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        long j7 = (j4 >>> 32) + j6 + (iArr[r4] & M);
        iArr[i2 + 1] = (int) j7;
        long j8 = (j7 >>> 32) + j5 + (iArr[r4] & M);
        iArr[i2 + 2] = (int) j8;
        long j9 = j8 >>> 32;
        long j10 = j9 + (iArr[r0] & M);
        iArr[i2 + 3] = (int) j10;
        if ((j10 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(8, iArr, i2, 4);
    }

    public static int mul33WordAdd(int i, int i2, int[] iArr, int i3) {
        long j = i & M;
        long j2 = i2 & M;
        long j3 = (j * j2) + (iArr[i3] & M);
        iArr[i3] = (int) j3;
        long j4 = (j3 >>> 32) + j2 + (iArr[r5] & M);
        iArr[i3 + 1] = (int) j4;
        long j5 = j4 >>> 32;
        long j6 = j5 + (iArr[r0] & M);
        iArr[i3 + 2] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(8, iArr, i3, 3);
    }

    public static int mulAddTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = iArr2[i2] & M;
        long j2 = iArr2[i2 + 1] & M;
        long j3 = iArr2[i2 + 2] & M;
        long j4 = iArr2[i2 + 3] & M;
        long j5 = iArr2[i2 + 4] & M;
        long j6 = iArr2[i2 + 5] & M;
        long j7 = iArr2[i2 + 6] & M;
        long j8 = iArr2[i2 + 7] & M;
        int i4 = i3;
        int i5 = 0;
        long j9 = 0;
        while (i5 < 8) {
            long j10 = j2;
            long j11 = iArr[i + i5] & M;
            long j12 = (j11 * j) + (iArr3[i4] & M);
            iArr3[i4] = (int) j12;
            int i6 = i4 + 1;
            long j13 = (j12 >>> 32) + (j11 * j10) + (iArr3[i6] & M);
            iArr3[i6] = (int) j13;
            int i7 = i5;
            long j14 = (j13 >>> 32) + (j11 * j3) + (iArr3[r5] & M);
            iArr3[i4 + 2] = (int) j14;
            long j15 = (j14 >>> 32) + (j11 * j4) + (iArr3[r0] & M);
            iArr3[i4 + 3] = (int) j15;
            long j16 = (j15 >>> 32) + (j11 * j5) + (iArr3[r0] & M);
            iArr3[i4 + 4] = (int) j16;
            long j17 = (j16 >>> 32) + (j11 * j6) + (iArr3[r0] & M);
            iArr3[i4 + 5] = (int) j17;
            long j18 = (j17 >>> 32) + (j11 * j7) + (iArr3[r0] & M);
            iArr3[i4 + 6] = (int) j18;
            long j19 = (j18 >>> 32) + (j11 * j8) + (iArr3[r0] & M);
            iArr3[i4 + 7] = (int) j19;
            long j20 = j9 + (j19 >>> 32) + (iArr3[r16] & M);
            iArr3[i4 + 8] = (int) j20;
            j9 = j20 >>> 32;
            i5 = i7 + 1;
            j2 = j10;
            i4 = i6;
        }
        return (int) j9;
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = iArr2[0] & M;
        long j2 = iArr2[1] & M;
        long j3 = iArr2[2] & M;
        long j4 = iArr2[3] & M;
        long j5 = iArr2[4] & M;
        long j6 = iArr2[5] & M;
        long j7 = iArr2[6] & M;
        long j8 = iArr2[7] & M;
        long j9 = 0;
        int i = 0;
        while (i < 8) {
            long j10 = iArr[i] & M;
            long j11 = (j10 * j) + (iArr3[i] & M);
            int i2 = i;
            iArr3[i2] = (int) j11;
            int i3 = i2 + 1;
            long j12 = (j11 >>> 32) + (j10 * j2) + (iArr3[i3] & M);
            iArr3[i3] = (int) j12;
            long j13 = (j12 >>> 32) + (j10 * j3) + (iArr3[r4] & M);
            iArr3[i2 + 2] = (int) j13;
            long j14 = (j13 >>> 32) + (j10 * j4) + (iArr3[r4] & M);
            iArr3[i2 + 3] = (int) j14;
            long j15 = (j14 >>> 32) + (j10 * j5) + (iArr3[r4] & M);
            iArr3[i2 + 4] = (int) j15;
            long j16 = (j15 >>> 32) + (j10 * j6) + (iArr3[r4] & M);
            iArr3[i2 + 5] = (int) j16;
            long j17 = (j16 >>> 32) + (j10 * j7) + (iArr3[r4] & M);
            iArr3[i2 + 6] = (int) j17;
            long j18 = (j17 >>> 32) + (j10 * j8) + (iArr3[r4] & M);
            iArr3[i2 + 7] = (int) j18;
            long j19 = j9 + (j18 >>> 32) + (iArr3[r4] & M);
            iArr3[i2 + 8] = (int) j19;
            j9 = j19 >>> 32;
            i = i3;
        }
        return (int) j9;
    }

    public static int mulByWord(int i, int[] iArr) {
        long j = i & M;
        long j2 = (iArr[0] & M) * j;
        iArr[0] = (int) j2;
        long j3 = (j2 >>> 32) + ((iArr[1] & M) * j);
        iArr[1] = (int) j3;
        long j4 = (j3 >>> 32) + ((iArr[2] & M) * j);
        iArr[2] = (int) j4;
        long j5 = (j4 >>> 32) + ((iArr[3] & M) * j);
        iArr[3] = (int) j5;
        long j6 = (j5 >>> 32) + ((iArr[4] & M) * j);
        iArr[4] = (int) j6;
        long j7 = (j6 >>> 32) + ((iArr[5] & M) * j);
        iArr[5] = (int) j7;
        long j8 = (j7 >>> 32) + ((iArr[6] & M) * j);
        iArr[6] = (int) j8;
        long j9 = (j8 >>> 32) + (j * (M & iArr[7]));
        iArr[7] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mulByWordAddTo(int i, int[] iArr, int[] iArr2) {
        long j = i & M;
        long j2 = ((iArr2[0] & M) * j) + (iArr[0] & M);
        iArr2[0] = (int) j2;
        long j3 = (j2 >>> 32) + ((iArr2[1] & M) * j) + (iArr[1] & M);
        iArr2[1] = (int) j3;
        long j4 = (j3 >>> 32) + ((iArr2[2] & M) * j) + (iArr[2] & M);
        iArr2[2] = (int) j4;
        long j5 = (j4 >>> 32) + ((iArr2[3] & M) * j) + (iArr[3] & M);
        iArr2[3] = (int) j5;
        long j6 = (j5 >>> 32) + ((iArr2[4] & M) * j) + (iArr[4] & M);
        iArr2[4] = (int) j6;
        long j7 = (j6 >>> 32) + ((iArr2[5] & M) * j) + (iArr[5] & M);
        iArr2[5] = (int) j7;
        long j8 = (j7 >>> 32) + ((iArr2[6] & M) * j) + (iArr[6] & M);
        iArr2[6] = (int) j8;
        long j9 = (j8 >>> 32) + (j * (iArr2[7] & M)) + (M & iArr[7]);
        iArr2[7] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mulWord(int i, int[] iArr, int[] iArr2, int i2) {
        long j = i & M;
        long j2 = 0;
        int i3 = 0;
        do {
            long j3 = j2 + ((iArr[i3] & M) * j);
            iArr2[i2 + i3] = (int) j3;
            j2 = j3 >>> 32;
            i3++;
        } while (i3 < 8);
        return (int) j2;
    }

    public static int mulWordAddTo(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        long j = i & M;
        long j2 = ((iArr[i2] & M) * j) + (iArr2[i3] & M);
        iArr2[i3] = (int) j2;
        long j3 = (j2 >>> 32) + ((iArr[i2 + 1] & M) * j) + (iArr2[r8] & M);
        iArr2[i3 + 1] = (int) j3;
        long j4 = (j3 >>> 32) + ((iArr[i2 + 2] & M) * j) + (iArr2[r8] & M);
        iArr2[i3 + 2] = (int) j4;
        long j5 = (j4 >>> 32) + ((iArr[i2 + 3] & M) * j) + (iArr2[r8] & M);
        iArr2[i3 + 3] = (int) j5;
        long j6 = (j5 >>> 32) + ((iArr[i2 + 4] & M) * j) + (iArr2[r8] & M);
        iArr2[i3 + 4] = (int) j6;
        long j7 = (j6 >>> 32) + ((iArr[i2 + 5] & M) * j) + (iArr2[r8] & M);
        iArr2[i3 + 5] = (int) j7;
        long j8 = (j7 >>> 32) + ((iArr[i2 + 6] & M) * j) + (iArr2[r8] & M);
        iArr2[i3 + 6] = (int) j8;
        long j9 = (j8 >>> 32) + (j * (iArr[i2 + 7] & M)) + (iArr2[r15] & M);
        iArr2[i3 + 7] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mulWordDwordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = i & M;
        long j3 = ((j & M) * j2) + (iArr[i2] & M);
        iArr[i2] = (int) j3;
        long j4 = j2 * (j >>> 32);
        long j5 = (j3 >>> 32) + j4 + (iArr[r9] & M);
        iArr[i2 + 1] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[r0] & M);
        iArr[i2 + 2] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(8, iArr, i2, 3);
    }

    public static void square(int[] iArr, int i, int[] iArr2, int i2) {
        long j = iArr[i] & M;
        int i3 = 0;
        int i4 = 16;
        int i5 = 7;
        while (true) {
            int i6 = i5 - 1;
            long j2 = iArr[i + i5] & M;
            long j3 = j2 * j2;
            iArr2[i2 + (i4 - 1)] = (i3 << 31) | ((int) (j3 >>> 33));
            i4 -= 2;
            iArr2[i2 + i4] = (int) (j3 >>> 1);
            i3 = (int) j3;
            if (i6 <= 0) {
                long j4 = j * j;
                long j5 = (j4 >>> 33) | ((i3 << 31) & M);
                iArr2[i2] = (int) j4;
                int i7 = ((int) (j4 >>> 32)) & 1;
                long j6 = iArr[i + 1] & M;
                long j7 = iArr2[r12] & M;
                long j8 = j5 + (j6 * j);
                int i8 = (int) j8;
                iArr2[i2 + 1] = (i8 << 1) | i7;
                int i9 = i8 >>> 31;
                long j9 = j7 + (j8 >>> 32);
                long j10 = iArr[i + 2] & M;
                long j11 = iArr2[r15] & M;
                long j12 = iArr2[r18] & M;
                long j13 = j9 + (j10 * j);
                int i10 = (int) j13;
                iArr2[i2 + 2] = (i10 << 1) | i9;
                long j14 = j11 + (j13 >>> 32) + (j10 * j6);
                long j15 = j12 + (j14 >>> 32);
                long j16 = j14 & M;
                long j17 = iArr[i + 3] & M;
                long j18 = (iArr2[r14] & M) + (j15 >>> 32);
                long j19 = j15 & M;
                long j20 = (iArr2[r7] & M) + (j18 >>> 32);
                long j21 = j18 & M;
                long j22 = j16 + (j17 * j);
                int i11 = (int) j22;
                iArr2[i2 + 3] = (i11 << 1) | (i10 >>> 31);
                int i12 = i11 >>> 31;
                long j23 = j19 + (j22 >>> 32) + (j17 * j6);
                long j24 = j21 + (j23 >>> 32) + (j17 * j10);
                long j25 = j23 & M;
                long j26 = j20 + (j24 >>> 32);
                long j27 = j24 & M;
                long j28 = iArr[i + 4] & M;
                long j29 = (iArr2[r2] & M) + (j26 >>> 32);
                long j30 = j26 & M;
                long j31 = (iArr2[r15] & M) + (j29 >>> 32);
                long j32 = j29 & M;
                long j33 = j25 + (j28 * j);
                int i13 = (int) j33;
                iArr2[i2 + 4] = (i13 << 1) | i12;
                int i14 = i13 >>> 31;
                long j34 = j27 + (j33 >>> 32) + (j28 * j6);
                long j35 = j30 + (j34 >>> 32) + (j28 * j10);
                long j36 = j34 & M;
                long j37 = j32 + (j35 >>> 32) + (j28 * j17);
                long j38 = j35 & M;
                long j39 = j31 + (j37 >>> 32);
                long j40 = j37 & M;
                long j41 = iArr[i + 5] & M;
                long j42 = (iArr2[r18] & M) + (j39 >>> 32);
                long j43 = j39 & M;
                long j44 = (iArr2[r24] & M) + (j42 >>> 32);
                long j45 = j42 & M;
                long j46 = j36 + (j41 * j);
                int i15 = (int) j46;
                iArr2[i2 + 5] = (i15 << 1) | i14;
                int i16 = i15 >>> 31;
                long j47 = j38 + (j46 >>> 32) + (j41 * j6);
                long j48 = j40 + (j47 >>> 32) + (j41 * j10);
                long j49 = j47 & M;
                long j50 = j43 + (j48 >>> 32) + (j41 * j17);
                long j51 = j48 & M;
                long j52 = j45 + (j50 >>> 32) + (j41 * j28);
                long j53 = j50 & M;
                long j54 = j44 + (j52 >>> 32);
                long j55 = j52 & M;
                long j56 = iArr[i + 6] & M;
                long j57 = (iArr2[r14] & M) + (j54 >>> 32);
                long j58 = j54 & M;
                long j59 = (iArr2[r26] & M) + (j57 >>> 32);
                long j60 = j57 & M;
                long j61 = j49 + (j56 * j);
                int i17 = (int) j61;
                iArr2[i2 + 6] = (i17 << 1) | i16;
                int i18 = i17 >>> 31;
                long j62 = j51 + (j61 >>> 32) + (j56 * j6);
                long j63 = j53 + (j62 >>> 32) + (j56 * j10);
                long j64 = j62 & M;
                long j65 = j55 + (j63 >>> 32) + (j56 * j17);
                long j66 = j63 & M;
                long j67 = j58 + (j65 >>> 32) + (j56 * j28);
                long j68 = j65 & M;
                long j69 = j60 + (j67 >>> 32) + (j56 * j41);
                long j70 = j67 & M;
                long j71 = j59 + (j69 >>> 32);
                long j72 = j69 & M;
                long j73 = iArr[i + 7] & M;
                long j74 = (iArr2[r7] & M) + (j71 >>> 32);
                long j75 = j71 & M;
                long j76 = (iArr2[r20] & M) + (j74 >>> 32);
                long j77 = j74 & M;
                long j78 = j64 + (j * j73);
                int i19 = (int) j78;
                iArr2[i2 + 7] = (i19 << 1) | i18;
                long j79 = j66 + (j78 >>> 32) + (j6 * j73);
                long j80 = j68 + (j79 >>> 32) + (j73 * j10);
                long j81 = j70 + (j80 >>> 32) + (j73 * j17);
                long j82 = j72 + (j81 >>> 32) + (j73 * j28);
                long j83 = j75 + (j82 >>> 32) + (j73 * j41);
                long j84 = j77 + (j83 >>> 32) + (j73 * j56);
                long j85 = j76 + (j84 >>> 32);
                int i20 = (int) j79;
                iArr2[i2 + 8] = (i19 >>> 31) | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) j80;
                iArr2[i2 + 9] = i21 | (i22 << 1);
                int i23 = (int) j81;
                iArr2[i2 + 10] = (i23 << 1) | (i22 >>> 31);
                int i24 = (int) j82;
                iArr2[i2 + 11] = (i23 >>> 31) | (i24 << 1);
                int i25 = i24 >>> 31;
                int i26 = (int) j83;
                iArr2[i2 + 12] = i25 | (i26 << 1);
                int i27 = i26 >>> 31;
                int i28 = (int) j84;
                iArr2[i2 + 13] = i27 | (i28 << 1);
                int i29 = i28 >>> 31;
                int i30 = (int) j85;
                iArr2[i2 + 14] = i29 | (i30 << 1);
                int i31 = i30 >>> 31;
                int i32 = i2 + 15;
                iArr2[i32] = i31 | ((iArr2[i32] + ((int) (j85 >>> 32))) << 1);
                return;
            }
            i5 = i6;
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j = iArr[0] & M;
        int i = 16;
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i3 - 1;
            long j2 = iArr[i3] & M;
            long j3 = j2 * j2;
            iArr2[i - 1] = (i2 << 31) | ((int) (j3 >>> 33));
            i -= 2;
            iArr2[i] = (int) (j3 >>> 1);
            i2 = (int) j3;
            if (i4 <= 0) {
                long j4 = j * j;
                long j5 = (j4 >>> 33) | ((i2 << 31) & M);
                iArr2[0] = (int) j4;
                long j6 = iArr[1] & M;
                long j7 = iArr2[2] & M;
                long j8 = j5 + (j6 * j);
                int i5 = (int) j8;
                iArr2[1] = (i5 << 1) | (((int) (j4 >>> 32)) & 1);
                long j9 = j7 + (j8 >>> 32);
                long j10 = iArr[2] & M;
                long j11 = iArr2[3] & M;
                long j12 = iArr2[4] & M;
                long j13 = j9 + (j10 * j);
                int i6 = (int) j13;
                iArr2[2] = (i6 << 1) | (i5 >>> 31);
                long j14 = j11 + (j13 >>> 32) + (j10 * j6);
                long j15 = j12 + (j14 >>> 32);
                long j16 = j14 & M;
                long j17 = iArr[3] & M;
                long j18 = (iArr2[5] & M) + (j15 >>> 32);
                long j19 = j15 & M;
                long j20 = (iArr2[6] & M) + (j18 >>> 32);
                long j21 = j18 & M;
                long j22 = j16 + (j17 * j);
                int i7 = (int) j22;
                iArr2[3] = (i7 << 1) | (i6 >>> 31);
                int i8 = i7 >>> 31;
                long j23 = j19 + (j22 >>> 32) + (j17 * j6);
                long j24 = j21 + (j23 >>> 32) + (j17 * j10);
                long j25 = j23 & M;
                long j26 = j20 + (j24 >>> 32);
                long j27 = j24 & M;
                long j28 = iArr[4] & M;
                long j29 = (iArr2[7] & M) + (j26 >>> 32);
                long j30 = j26 & M;
                long j31 = (iArr2[8] & M) + (j29 >>> 32);
                long j32 = j29 & M;
                long j33 = j25 + (j28 * j);
                int i9 = (int) j33;
                iArr2[4] = (i9 << 1) | i8;
                int i10 = i9 >>> 31;
                long j34 = j27 + (j33 >>> 32) + (j28 * j6);
                long j35 = j30 + (j34 >>> 32) + (j28 * j10);
                long j36 = j34 & M;
                long j37 = j32 + (j35 >>> 32) + (j28 * j17);
                long j38 = j35 & M;
                long j39 = j31 + (j37 >>> 32);
                long j40 = j37 & M;
                long j41 = iArr[5] & M;
                long j42 = (iArr2[9] & M) + (j39 >>> 32);
                long j43 = j39 & M;
                long j44 = (iArr2[10] & M) + (j42 >>> 32);
                long j45 = j42 & M;
                long j46 = j36 + (j41 * j);
                int i11 = (int) j46;
                iArr2[5] = (i11 << 1) | i10;
                int i12 = i11 >>> 31;
                long j47 = j38 + (j46 >>> 32) + (j41 * j6);
                long j48 = j40 + (j47 >>> 32) + (j41 * j10);
                long j49 = j47 & M;
                long j50 = j43 + (j48 >>> 32) + (j41 * j17);
                long j51 = j48 & M;
                long j52 = j45 + (j50 >>> 32) + (j41 * j28);
                long j53 = j50 & M;
                long j54 = j44 + (j52 >>> 32);
                long j55 = j52 & M;
                long j56 = iArr[6] & M;
                long j57 = (iArr2[11] & M) + (j54 >>> 32);
                long j58 = j54 & M;
                long j59 = (iArr2[12] & M) + (j57 >>> 32);
                long j60 = j57 & M;
                long j61 = j49 + (j56 * j);
                int i13 = (int) j61;
                iArr2[6] = (i13 << 1) | i12;
                int i14 = i13 >>> 31;
                long j62 = j51 + (j61 >>> 32) + (j56 * j6);
                long j63 = j53 + (j62 >>> 32) + (j56 * j10);
                long j64 = j62 & M;
                long j65 = j55 + (j63 >>> 32) + (j56 * j17);
                long j66 = j63 & M;
                long j67 = j58 + (j65 >>> 32) + (j56 * j28);
                long j68 = j65 & M;
                long j69 = j60 + (j67 >>> 32) + (j56 * j41);
                long j70 = j67 & M;
                long j71 = j59 + (j69 >>> 32);
                long j72 = j69 & M;
                long j73 = iArr[7] & M;
                long j74 = (iArr2[13] & M) + (j71 >>> 32);
                long j75 = j71 & M;
                long j76 = (iArr2[14] & M) + (j74 >>> 32);
                long j77 = j74 & M;
                long j78 = j64 + (j * j73);
                int i15 = (int) j78;
                iArr2[7] = (i15 << 1) | i14;
                int i16 = i15 >>> 31;
                long j79 = j66 + (j78 >>> 32) + (j73 * j6);
                long j80 = j68 + (j79 >>> 32) + (j73 * j10);
                long j81 = j70 + (j80 >>> 32) + (j73 * j17);
                long j82 = j72 + (j81 >>> 32) + (j73 * j28);
                long j83 = j75 + (j82 >>> 32) + (j41 * j73);
                long j84 = j77 + (j83 >>> 32) + (j73 * j56);
                long j85 = j76 + (j84 >>> 32);
                int i17 = (int) j79;
                iArr2[8] = i16 | (i17 << 1);
                int i18 = i17 >>> 31;
                int i19 = (int) j80;
                iArr2[9] = i18 | (i19 << 1);
                int i20 = (int) j81;
                iArr2[10] = (i19 >>> 31) | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) j82;
                iArr2[11] = i21 | (i22 << 1);
                int i23 = i22 >>> 31;
                int i24 = (int) j83;
                iArr2[12] = i23 | (i24 << 1);
                int i25 = i24 >>> 31;
                int i26 = (int) j84;
                iArr2[13] = i25 | (i26 << 1);
                int i27 = i26 >>> 31;
                int i28 = (int) j85;
                iArr2[14] = i27 | (i28 << 1);
                iArr2[15] = ((iArr2[15] + ((int) (j85 >>> 32))) << 1) | (i28 >>> 31);
                return;
            }
            i3 = i4;
        }
    }

    public static int sub(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = (iArr[i] & M) - (iArr2[i2] & M);
        iArr3[i3] = (int) j;
        long j2 = (j >> 32) + ((iArr[i + 1] & M) - (iArr2[i2 + 1] & M));
        iArr3[i3 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr[i + 2] & M) - (iArr2[i2 + 2] & M));
        iArr3[i3 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr[i + 3] & M) - (iArr2[i2 + 3] & M));
        iArr3[i3 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr[i + 4] & M) - (iArr2[i2 + 4] & M));
        iArr3[i3 + 4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr[i + 5] & M) - (iArr2[i2 + 5] & M));
        iArr3[i3 + 5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr[i + 6] & M) - (iArr2[i2 + 6] & M));
        iArr3[i3 + 6] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr[i + 7] & M) - (iArr2[i2 + 7] & M));
        iArr3[i3 + 7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int sub(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & M) - (iArr2[0] & M);
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + ((iArr[1] & M) - (iArr2[1] & M));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr[2] & M) - (iArr2[2] & M));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr[3] & M) - (iArr2[3] & M));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr[4] & M) - (iArr2[4] & M));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr[5] & M) - (iArr2[5] & M));
        iArr3[5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr[6] & M) - (iArr2[6] & M));
        iArr3[6] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr[7] & M) - (iArr2[7] & M));
        iArr3[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subBothFrom(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((iArr3[0] & M) - (iArr[0] & M)) - (iArr2[0] & M);
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + (((iArr3[1] & M) - (iArr[1] & M)) - (iArr2[1] & M));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + (((iArr3[2] & M) - (iArr[2] & M)) - (iArr2[2] & M));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + (((iArr3[3] & M) - (iArr[3] & M)) - (iArr2[3] & M));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + (((iArr3[4] & M) - (iArr[4] & M)) - (iArr2[4] & M));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + (((iArr3[5] & M) - (iArr[5] & M)) - (iArr2[5] & M));
        iArr3[5] = (int) j6;
        long j7 = (j6 >> 32) + (((iArr3[6] & M) - (iArr[6] & M)) - (iArr2[6] & M));
        iArr3[6] = (int) j7;
        long j8 = (j7 >> 32) + (((iArr3[7] & M) - (iArr[7] & M)) - (iArr2[7] & M));
        iArr3[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subFrom(int[] iArr, int i, int[] iArr2, int i2) {
        long j = (iArr2[i2] & M) - (iArr[i] & M);
        iArr2[i2] = (int) j;
        long j2 = (j >> 32) + ((iArr2[r5] & M) - (iArr[i + 1] & M));
        iArr2[i2 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[r5] & M) - (iArr[i + 2] & M));
        iArr2[i2 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[r5] & M) - (iArr[i + 3] & M));
        iArr2[i2 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[r5] & M) - (iArr[i + 4] & M));
        iArr2[i2 + 4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr2[r5] & M) - (iArr[i + 5] & M));
        iArr2[i2 + 5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr2[r5] & M) - (iArr[i + 6] & M));
        iArr2[i2 + 6] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr2[r13] & M) - (iArr[i + 7] & M));
        iArr2[i2 + 7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subFrom(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        long j = (i3 & M) + ((iArr2[i2] & M) - (iArr[i] & M));
        iArr2[i2] = (int) j;
        long j2 = (j >> 32) + ((iArr2[r4] & M) - (iArr[i + 1] & M));
        iArr2[i2 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[r4] & M) - (iArr[i + 2] & M));
        iArr2[i2 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[r4] & M) - (iArr[i + 3] & M));
        iArr2[i2 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[r4] & M) - (iArr[i + 4] & M));
        iArr2[i2 + 4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr2[r4] & M) - (iArr[i + 5] & M));
        iArr2[i2 + 5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr2[r4] & M) - (iArr[i + 6] & M));
        iArr2[i2 + 6] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr2[r12] & M) - (iArr[i + 7] & M));
        iArr2[i2 + 7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2) {
        long j = (iArr2[0] & M) - (iArr[0] & M);
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((iArr2[1] & M) - (iArr[1] & M));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[2] & M) - (iArr[2] & M));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[3] & M) - (iArr[3] & M));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[4] & M) - (iArr[4] & M));
        iArr2[4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr2[5] & M) - (iArr[5] & M));
        iArr2[5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr2[6] & M) - (iArr[6] & M));
        iArr2[6] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr2[7] & M) - (M & iArr[7]));
        iArr2[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2, int i) {
        long j = (i & M) + ((iArr2[0] & M) - (iArr[0] & M));
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((iArr2[1] & M) - (iArr[1] & M));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[2] & M) - (iArr[2] & M));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[3] & M) - (iArr[3] & M));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[4] & M) - (iArr[4] & M));
        iArr2[4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr2[5] & M) - (iArr[5] & M));
        iArr2[5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr2[6] & M) - (iArr[6] & M));
        iArr2[6] = (int) j7;
        long j8 = (j7 >> 32) + ((iArr2[7] & M) - (M & iArr[7]));
        iArr2[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 8; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                Pack.intToBigEndian(i2, bArr, (7 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 4; i++) {
            long j = jArr[i];
            if (j != 0) {
                Pack.longToBigEndian(j, bArr, (3 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
    }
}
