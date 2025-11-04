package com.alibaba.fastjson2.util;

import androidx.camera.video.AudioStats;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.codec.FieldInfo;
import java.math.BigInteger;

/* loaded from: classes2.dex */
public final class NumberUtils {
    static final long INFI;
    static final long INFINITY;
    static final int MOD_DOUBLE_EXP = 2047;
    static final long MOD_DOUBLE_MANTISSA = 4503599627370495L;
    static final int MOD_FLOAT_EXP = 255;
    static final int MOD_FLOAT_MANTISSA = 8388607;
    static final LongBiFunction MULTIPLY_HIGH;
    static final double[] NEGATIVE_DECIMAL_POWER;
    static final char[][] NEGATIVE_DECIMAL_POWER_CHARS;
    static final long NITY;
    static final double[] POSITIVE_DECIMAL_POWER;
    static final char[][] POSITIVE_DECIMAL_POWER_CHARS;
    static final long[] POW10_LONG_VALUES;
    static final BigInteger[] POW5_BI_VALUES;
    static final long[] POW5_LONG_VALUES;
    static final short[] TWO_DIGITS_16_BITS;
    static final int[] TWO_DIGITS_32_BITS;

    @FunctionalInterface
    interface LongBiFunction {
        long multiplyHigh(long j, long j2);
    }

    static long multiplyHigh(long j, long j2) {
        long j3 = j >> 32;
        long j4 = j & 4294967295L;
        long j5 = j2 >> 32;
        long j6 = j2 & 4294967295L;
        long j7 = (j6 * j3) + ((j4 * j6) >>> 32);
        return (j3 * j5) + (j7 >> 32) + (((4294967295L & j7) + (j4 * j5)) >> 32);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008f A[LOOP:0: B:10:0x0089->B:12:0x008f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00d7 A[LOOP:1: B:15:0x00d0->B:17:0x00d7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00dd A[EDGE_INSN: B:18:0x00dd->B:19:0x00dd BREAK  A[LOOP:1: B:15:0x00d0->B:17:0x00d7], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ed A[LOOP:2: B:20:0x00e8->B:22:0x00ed, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00f6 A[EDGE_INSN: B:23:0x00f6->B:24:0x00f6 BREAK  A[LOOP:2: B:20:0x00e8->B:22:0x00ed], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0101 A[LOOP:3: B:25:0x00ff->B:26:0x0101, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0042  */
    static {
        /*
            Method dump skipped, instructions count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.NumberUtils.<clinit>():void");
    }

    private NumberUtils() {
    }

    static long multiplyHighAndShift(long j, long j2, int i) {
        long multiplyHigh = MULTIPLY_HIGH.multiplyHigh(j, j2);
        if (i >= 64) {
            return multiplyHigh >>> (i - 64);
        }
        return ((j * j2) >>> i) | (multiplyHigh << (64 - i));
    }

    static long multiplyHighAndShift(long j, long j2, long j3, int i) {
        int i2 = i - 64;
        LongBiFunction longBiFunction = MULTIPLY_HIGH;
        long multiplyHigh = longBiFunction.multiplyHigh(j, j2);
        long j4 = j2 * j;
        long multiplyHigh2 = (longBiFunction.multiplyHigh(j, j3) << 32) + ((j * j3) >>> 32);
        long j5 = j4 + multiplyHigh2;
        if ((j4 | multiplyHigh2) < 0 && ((j4 & multiplyHigh2) < 0 || j5 >= 0)) {
            multiplyHigh++;
        }
        if (i2 >= 0) {
            return multiplyHigh >>> i2;
        }
        return (j5 >>> i) | (multiplyHigh << (-i2));
    }

    public static Scientific doubleToScientific(double d) {
        int i;
        long j;
        long j2;
        long j3;
        long j4;
        boolean z;
        int i2;
        int i3;
        long j5;
        char c;
        if (d == Double.MIN_VALUE) {
            return Scientific.DOUBLE_MIN;
        }
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        int i4 = ((int) (doubleToRawLongBits >> 52)) & 2047;
        long j6 = MOD_DOUBLE_MANTISSA & doubleToRawLongBits;
        boolean z2 = j6 > 0;
        if (i4 > 0) {
            if (i4 == 2047) {
                return Scientific.SCIENTIFIC_NULL;
            }
            j = FieldInfo.FIELD_MASK | j6;
            i = i4 - 1075;
        } else {
            if (j6 == 0) {
                return doubleToRawLongBits == 0 ? Scientific.ZERO : Scientific.NEGATIVE_ZERO;
            }
            int numberOfLeadingZeros = Long.numberOfLeadingZeros(j6) - 11;
            i = (-1074) - numberOfLeadingZeros;
            j = j6 << numberOfLeadingZeros;
        }
        if (i >= 0) {
            ED ed = ED.E2_D_A[i];
            i2 = ed.e10;
            i3 = ed.adl;
            j4 = ed.d3;
            j2 = ed.d4;
            if (ed.b) {
                c = '\n';
                if (j >= ed.bv) {
                    if (j > ed.bv) {
                        i2++;
                        i3++;
                    } else {
                        int i5 = i2 + 1;
                        if (d == POSITIVE_DECIMAL_POWER[i5]) {
                            return new Scientific(i5, true);
                        }
                    }
                }
            } else {
                c = '\n';
            }
            int i6 = ed.o5;
            int i7 = i + i6;
            if (i6 < 0) {
                ED5 ed5 = ED5.ED5_A[-i6];
                j3 = multiplyHighAndShift(j << c, ed5.oy, ed5.of, 32 - ((i7 - 10) - ed5.ob));
                if (i6 != -1 || i7 >= 11) {
                    z = false;
                }
            } else {
                j3 = (j * POW5_LONG_VALUES[i6]) << i7;
            }
            z = true;
        } else {
            ED ed2 = ED.E5_D_A[-i];
            int i8 = ed2.e10;
            int i9 = ed2.adl;
            long j7 = ed2.d3;
            j2 = ed2.d4;
            if (ed2.b && j >= ed2.bv) {
                if (j > ed2.bv) {
                    i8++;
                    i9++;
                } else {
                    if (i8 >= -1) {
                        int i10 = i8 + 1;
                        if (d == POSITIVE_DECIMAL_POWER[i10]) {
                            return new Scientific(i10, true);
                        }
                    }
                    if (i8 < -1 && d == NEGATIVE_DECIMAL_POWER[(-i8) - 1]) {
                        return new Scientific(i8 + 1, true);
                    }
                }
            }
            int i11 = ed2.o5;
            int i12 = i + i11;
            if (i12 < 0) {
                long[] jArr = POW5_LONG_VALUES;
                if (i11 < jArr.length) {
                    j3 = multiplyHighAndShift(j, jArr[i11], -i12);
                } else if (i11 < jArr.length + 4) {
                    j3 = multiplyHighAndShift(j * jArr[(i11 - jArr.length) + 1], jArr[jArr.length - 1], -i12);
                } else {
                    ED5 ed52 = ED5.ED5_A[i11];
                    j3 = multiplyHighAndShift(j << 10, ed52.y, ed52.f, (-(ed52.dfb + i12)) + 10);
                }
            } else {
                j3 = (POW5_LONG_VALUES[i11] * j) << i12;
            }
            j4 = j7;
            z = false;
            i2 = i8;
            i3 = i9;
        }
        if (z) {
            long j8 = j3 / 10;
            if (i3 == 16) {
                i3--;
                j8 = (j8 + 5) / 10;
            }
            return new Scientific(j8, i3 + 2, i2);
        }
        long j9 = j3 / 1000;
        long j10 = j3 - (1000 * j9);
        int i13 = ((10001 - (j10 * 10)) << 1) <= j2 ? 1 : 0;
        if (i13 == 0) {
            if (((j10 + 1) << (z2 ? (char) 1 : (char) 2)) > j4) {
                if (z2) {
                    j5 = (j3 + 50) / 100;
                } else {
                    j5 = (j3 + 5) / 10;
                    i3++;
                }
                return new Scientific(j5, i3 + 1, i2);
            }
        }
        j5 = j9 + i13;
        i3--;
        return new Scientific(j5, i3 + 1, i2);
    }

    public static int writeDouble(byte[] bArr, int i, double d, boolean z) {
        if (d == AudioStats.AUDIO_AMPLITUDE_NONE) {
            if (Double.doubleToLongBits(d) == Long.MIN_VALUE) {
                bArr[i] = 45;
                i++;
            }
            bArr[i] = JSONB.Constants.BC_INT32_BYTE_MIN;
            IOUtils.putShortUnaligned(bArr, i + 1, IOUtils.DOT_ZERO_16);
            return i + 3;
        }
        if (d < AudioStats.AUDIO_AMPLITUDE_NONE) {
            if (!z || d != Double.NEGATIVE_INFINITY) {
                bArr[i] = 45;
                i++;
            }
            d = -d;
        }
        int i2 = i;
        long j = (long) d;
        if (d == j) {
            int stringSize = IOUtils.stringSize(j);
            return writeDecimal(j, stringSize, stringSize - 1, bArr, i2);
        }
        Scientific doubleToScientific = doubleToScientific(d);
        int i3 = doubleToScientific.e10;
        if (!doubleToScientific.b) {
            return writeDecimal(doubleToScientific.output, doubleToScientific.count, doubleToScientific.e10, bArr, i2);
        }
        if (doubleToScientific == Scientific.SCIENTIFIC_NULL) {
            if (z) {
                IOUtils.putIntUnaligned(bArr, i2, IOUtils.NULL_32);
                return i2 + 4;
            }
            if (d == Double.POSITIVE_INFINITY) {
                IOUtils.putLongUnaligned(bArr, i2, INFINITY);
                return i2 + 8;
            }
            bArr[i2] = JSONB.Constants.BC_STR_ASCII_FIX_5;
            bArr[i2 + 1] = 97;
            bArr[i2 + 2] = JSONB.Constants.BC_STR_ASCII_FIX_5;
            return i2 + 3;
        }
        int i4 = 0;
        if (i3 >= 0) {
            char[] cArr = POSITIVE_DECIMAL_POWER_CHARS[i3];
            int length = cArr.length;
            while (i4 < length) {
                bArr[i2] = (byte) cArr[i4];
                i4++;
                i2++;
            }
            return i2;
        }
        char[] cArr2 = NEGATIVE_DECIMAL_POWER_CHARS[-i3];
        int length2 = cArr2.length;
        while (i4 < length2) {
            bArr[i2] = (byte) cArr2[i4];
            i4++;
            i2++;
        }
        return i2;
    }

    public static int writeDouble(char[] cArr, int i, double d, boolean z) {
        if (d == AudioStats.AUDIO_AMPLITUDE_NONE) {
            if (Double.doubleToLongBits(d) == Long.MIN_VALUE) {
                cArr[i] = '-';
                i++;
            }
            cArr[i] = '0';
            IOUtils.putIntUnaligned(cArr, i + 1, IOUtils.DOT_ZERO_32);
            return i + 3;
        }
        if (d < AudioStats.AUDIO_AMPLITUDE_NONE) {
            if (!z || d != Double.NEGATIVE_INFINITY) {
                cArr[i] = '-';
                i++;
            }
            d = -d;
        }
        int i2 = i;
        long j = (long) d;
        if (d == j) {
            int stringSize = IOUtils.stringSize(j);
            return writeDecimal(j, stringSize, stringSize - 1, cArr, i2);
        }
        Scientific doubleToScientific = doubleToScientific(d);
        int i3 = doubleToScientific.e10;
        if (!doubleToScientific.b) {
            return writeDecimal(doubleToScientific.output, doubleToScientific.count, i3, cArr, i2);
        }
        if (doubleToScientific != Scientific.SCIENTIFIC_NULL) {
            if (i3 >= 0) {
                char[] cArr2 = POSITIVE_DECIMAL_POWER_CHARS[i3];
                System.arraycopy(cArr2, 0, cArr, i2, cArr2.length);
                return i2 + cArr2.length;
            }
            char[] cArr3 = NEGATIVE_DECIMAL_POWER_CHARS[-i3];
            System.arraycopy(cArr3, 0, cArr, i2, cArr3.length);
            return i2 + cArr3.length;
        }
        if (z) {
            IOUtils.putLongUnaligned(cArr, i2, IOUtils.NULL_64);
            return i2 + 4;
        }
        if (d == Double.POSITIVE_INFINITY) {
            IOUtils.putLongUnaligned(cArr, i2, INFI);
            IOUtils.putLongUnaligned(cArr, i2 + 4, NITY);
            return i2 + 8;
        }
        cArr[i2] = 'N';
        cArr[i2 + 1] = 'a';
        cArr[i2 + 2] = 'N';
        return i2 + 3;
    }

    public static int writeFloat(byte[] bArr, int i, float f, boolean z) {
        int i2;
        if (Float.isNaN(f) || f == Float.POSITIVE_INFINITY || f == Float.NEGATIVE_INFINITY) {
            return writeSpecial(bArr, i, f, z);
        }
        if (f == 0.0f) {
            if (Float.floatToIntBits(f) == Integer.MIN_VALUE) {
                bArr[i] = 45;
                i++;
            }
            bArr[i] = JSONB.Constants.BC_INT32_BYTE_MIN;
            IOUtils.putShortUnaligned(bArr, i + 1, IOUtils.DOT_ZERO_16);
            return i + 3;
        }
        if (f < 0.0f) {
            bArr[i] = 45;
            f = -f;
            i2 = i + 1;
        } else {
            i2 = i;
        }
        Scientific floatToScientific = floatToScientific(f);
        return writeDecimal(floatToScientific.output, floatToScientific.count, floatToScientific.e10, bArr, i2);
    }

    public static int writeFloat(char[] cArr, int i, float f, boolean z) {
        int i2;
        if (Float.isNaN(f) || f == Float.POSITIVE_INFINITY || f == Float.NEGATIVE_INFINITY) {
            return writeSpecial(cArr, i, f, z);
        }
        if (f == 0.0f) {
            if (Float.floatToIntBits(f) == Integer.MIN_VALUE) {
                cArr[i] = '-';
                i++;
            }
            cArr[i] = '0';
            IOUtils.putIntUnaligned(cArr, i + 1, IOUtils.DOT_ZERO_32);
            return i + 3;
        }
        if (f < 0.0f) {
            cArr[i] = '-';
            f = -f;
            i2 = i + 1;
        } else {
            i2 = i;
        }
        Scientific floatToScientific = floatToScientific(f);
        return writeDecimal(floatToScientific.output, floatToScientific.count, floatToScientific.e10, cArr, i2);
    }

    private static int writeSpecial(byte[] bArr, int i, float f, boolean z) {
        if (z) {
            IOUtils.putIntUnaligned(bArr, i, IOUtils.NULL_32);
            return i + 4;
        }
        if (Float.isNaN(f)) {
            bArr[i] = JSONB.Constants.BC_STR_ASCII_FIX_5;
            bArr[i + 1] = 97;
            bArr[i + 2] = JSONB.Constants.BC_STR_ASCII_FIX_5;
            return i + 3;
        }
        if (f == Float.NEGATIVE_INFINITY) {
            bArr[i] = 45;
            i++;
        }
        IOUtils.putLongUnaligned(bArr, i, INFINITY);
        return i + 8;
    }

    private static int writeSpecial(char[] cArr, int i, float f, boolean z) {
        if (z) {
            IOUtils.putLongUnaligned(cArr, i, IOUtils.NULL_64);
            return i + 4;
        }
        if (Float.isNaN(f)) {
            cArr[i] = 'N';
            cArr[i + 1] = 'a';
            cArr[i + 2] = 'N';
            return i + 3;
        }
        if (f == Float.NEGATIVE_INFINITY) {
            cArr[i] = '-';
            i++;
        }
        IOUtils.putLongUnaligned(cArr, i, INFI);
        IOUtils.putLongUnaligned(cArr, i + 4, NITY);
        return i + 8;
    }

    public static Scientific floatToScientific(float f) {
        int i;
        int i2;
        int i3;
        long j;
        int i4;
        int i5;
        long j2;
        long j3;
        int i6;
        long multiplyHigh;
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        int i7 = (floatToRawIntBits >> 23) & 255;
        int i8 = MOD_FLOAT_MANTISSA & floatToRawIntBits;
        boolean z = i8 > 0;
        if (i7 > 0) {
            if (i7 == 255) {
                return Scientific.SCIENTIFIC_NULL;
            }
            i2 = 8388608 | i8;
            i = i7 - 150;
        } else {
            if (i8 == 0) {
                return floatToRawIntBits == 0 ? Scientific.ZERO : Scientific.NEGATIVE_ZERO;
            }
            int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i8) - 8;
            i = (-149) - numberOfLeadingZeros;
            i2 = i8 << numberOfLeadingZeros;
        }
        if (i >= 0) {
            ED ed = EF.E2_F_A[i];
            i4 = ed.e10;
            i5 = ed.adl;
            j = ed.d4;
            if (ed.b && i2 > ed.bv) {
                i4++;
                i5++;
            }
            int i9 = ed.o5 + 6;
            int i10 = i + i9;
            if (i9 >= 0) {
                i3 = 1;
                j2 = (i2 * POW5_LONG_VALUES[i9]) << i10;
            } else if (i10 < 40) {
                j2 = (i2 << i10) / POW5_LONG_VALUES[-i9];
                i3 = 1;
            } else {
                ED5 ed5 = ED5.ED5_A[-i9];
                i3 = 1;
                j2 = multiplyHighAndShift(i2 << 39, ed5.oy, ed5.of, (ed5.ob + 71) - i10);
            }
        } else {
            i3 = 1;
            ED ed2 = EF.E5_F_A[-i];
            int i11 = ed2.e10;
            int i12 = ed2.adl;
            j = ed2.d4;
            if (ed2.b && i2 > ed2.bv) {
                i11++;
                i12++;
            }
            i4 = i11;
            i5 = i12;
            int i13 = ed2.o5 + 6;
            int i14 = i + i13;
            if (i14 >= 0) {
                j2 = (POW5_LONG_VALUES[i13] * i2) << i14;
            } else if (i13 < 17) {
                j2 = (i2 * POW5_LONG_VALUES[i13]) >> (-i14);
            } else {
                long[] jArr = POW5_LONG_VALUES;
                if (i13 < jArr.length) {
                    j2 = multiplyHighAndShift(i2, jArr[i13], -i14);
                } else if (i13 < jArr.length + 4) {
                    j2 = multiplyHighAndShift(i2 * jArr[(i13 - jArr.length) + 1], jArr[jArr.length - 1], -i14);
                } else {
                    ED5 ed52 = ED5.ED5_A[i13];
                    j2 = multiplyHighAndShift(i2 << 39, ed52.y, ed52.f, (-(ed52.dfb + i14)) + 39);
                }
            }
        }
        if (j2 < 1000000000) {
            return new Scientific(MULTIPLY_HIGH.multiplyHigh(j2, 7737125245533626719L) >> 22, 2, i4);
        }
        LongBiFunction longBiFunction = MULTIPLY_HIGH;
        long multiplyHigh2 = longBiFunction.multiplyHigh(j2, 4951760157141521100L) >> 28;
        long j4 = j2 - (1000000000 * multiplyHigh2);
        int i15 = ((1000000001 - j4) << i3) <= j ? i3 : 0;
        if (i15 == 0) {
            if (((j4 + 1) << (z ? i3 : 2)) > j) {
                if (z) {
                    multiplyHigh = (longBiFunction.multiplyHigh(j2, 6189700196426901375L) >> 25) + (j4 % 100000000 >= 50000000 ? i3 : 0);
                    i6 = i5;
                } else {
                    multiplyHigh = (longBiFunction.multiplyHigh(j2, 7737125245533626719L) >> 22) + (j4 % 10000000 >= 5000000 ? i3 : 0);
                    i6 = i5 + 1;
                }
                j3 = multiplyHigh;
                return new Scientific(j3, i6 + 1, i4);
            }
        }
        j3 = multiplyHigh2 + i15;
        int i16 = i5 - 1;
        if (i15 == 0 || POW10_LONG_VALUES[i16] != j3) {
            i6 = i16;
        } else {
            i4++;
            j3 = 1;
            i6 = 0;
        }
        return new Scientific(j3, i6 + 1, i4);
    }

    private static int writeDecimal(long j, int i, int i2, byte[] bArr, int i3) {
        long j2;
        int i4;
        int writeInt64;
        int i5 = i2;
        if ((j & 1) == 0 && j % 5 == 0) {
            j2 = j;
            int i6 = i;
            while (j2 % 100 == 0) {
                i6 -= 2;
                j2 /= 100;
                if (i6 == 1) {
                    break;
                }
            }
            if ((1 & j2) == 0 && j2 % 5 == 0 && j2 > 0) {
                i4 = i6 - 1;
                j2 /= 10;
            } else {
                i4 = i6;
            }
        } else {
            j2 = j;
            i4 = i;
        }
        if (i5 < -3 || i5 >= 7) {
            if (i4 == 1) {
                bArr[i3] = (byte) (j2 + 48);
                IOUtils.putShortUnaligned(bArr, i3 + 1, IOUtils.DOT_ZERO_16);
                writeInt64 = i3 + 3;
            } else {
                int i7 = i4 - 2;
                long j3 = POW10_LONG_VALUES[i7];
                int i8 = (int) (j2 / j3);
                bArr[i3] = (byte) (i8 + 48);
                bArr[i3 + 1] = 46;
                int i9 = i3 + 2;
                long j4 = j2 - (i8 * j3);
                while (true) {
                    i7--;
                    if (i7 <= -1 || j4 >= POW10_LONG_VALUES[i7]) {
                        break;
                    }
                    bArr[i9] = JSONB.Constants.BC_INT32_BYTE_MIN;
                    i9++;
                }
                writeInt64 = IOUtils.writeInt64(bArr, i9, j4);
            }
            int i10 = writeInt64 + 1;
            bArr[writeInt64] = 69;
            if (i5 < 0) {
                bArr[i10] = 45;
                i5 = -i5;
                i10 = writeInt64 + 2;
            }
            if (i5 > 99) {
                int i11 = (int) ((i5 * 1374389535) >> 37);
                bArr[i10] = (byte) (i11 + 48);
                IOUtils.putShortUnaligned(bArr, i10 + 1, TWO_DIGITS_16_BITS[i5 - (i11 * 100)]);
                return i10 + 3;
            }
            if (i5 > 9) {
                IOUtils.putShortUnaligned(bArr, i10, TWO_DIGITS_16_BITS[i5]);
                return i10 + 2;
            }
            int i12 = i10 + 1;
            bArr[i10] = (byte) (i5 + 48);
            return i12;
        }
        if (i5 < 0) {
            IOUtils.putShortUnaligned(bArr, i3, IOUtils.ZERO_DOT_16);
            int i13 = i3 + 2;
            if (i5 == -2) {
                bArr[i13] = JSONB.Constants.BC_INT32_BYTE_MIN;
                i13 = i3 + 3;
            } else if (i5 == -3) {
                IOUtils.putShortUnaligned(bArr, i13, (short) 12336);
                i13 = i3 + 4;
            }
            return IOUtils.writeInt64(bArr, i13, j2);
        }
        int i14 = (i4 - 1) - i5;
        if (i14 > 0) {
            int i15 = i14 - 1;
            long j5 = POW10_LONG_VALUES[i15];
            long j6 = (int) (j2 / j5);
            int writeInt32 = IOUtils.writeInt32(bArr, i3, j6);
            int i16 = writeInt32 + 1;
            bArr[writeInt32] = 46;
            long j7 = j2 - (j6 * j5);
            while (true) {
                i15--;
                if (i15 <= -1 || j7 >= POW10_LONG_VALUES[i15]) {
                    break;
                }
                bArr[i16] = JSONB.Constants.BC_INT32_BYTE_MIN;
                i16++;
            }
            return IOUtils.writeInt64(bArr, i16, j7);
        }
        int writeInt642 = IOUtils.writeInt64(bArr, i3, j2);
        int i17 = -i14;
        if (i17 > 0) {
            int i18 = 0;
            while (i18 < i17) {
                bArr[writeInt642] = JSONB.Constants.BC_INT32_BYTE_MIN;
                i18++;
                writeInt642++;
            }
        }
        IOUtils.putShortUnaligned(bArr, writeInt642, IOUtils.DOT_ZERO_16);
        return writeInt642 + 2;
    }

    private static int writeDecimal(long j, int i, int i2, char[] cArr, int i3) {
        long j2;
        int i4;
        int writeInt64;
        int i5 = i2;
        if ((j & 1) == 0 && j % 5 == 0) {
            j2 = j;
            int i6 = i;
            while (j2 % 100 == 0) {
                i6 -= 2;
                j2 /= 100;
                if (i6 == 1) {
                    break;
                }
            }
            if ((1 & j2) == 0 && j2 % 5 == 0 && j2 > 0) {
                i4 = i6 - 1;
                j2 /= 10;
            } else {
                i4 = i6;
            }
        } else {
            j2 = j;
            i4 = i;
        }
        if (i5 < -3 || i5 >= 7) {
            if (i4 == 1) {
                cArr[i3] = (char) (j2 + 48);
                IOUtils.putIntUnaligned(cArr, i3 + 1, IOUtils.DOT_ZERO_32);
                writeInt64 = i3 + 3;
            } else {
                int i7 = i4 - 2;
                long j3 = POW10_LONG_VALUES[i7];
                int i8 = (int) (j2 / j3);
                cArr[i3] = (char) (i8 + 48);
                cArr[i3 + 1] = '.';
                int i9 = i3 + 2;
                long j4 = j2 - (i8 * j3);
                while (true) {
                    i7--;
                    if (i7 <= -1 || j4 >= POW10_LONG_VALUES[i7]) {
                        break;
                    }
                    cArr[i9] = '0';
                    i9++;
                }
                writeInt64 = IOUtils.writeInt64(cArr, i9, j4);
            }
            int i10 = writeInt64 + 1;
            cArr[writeInt64] = 'E';
            if (i5 < 0) {
                cArr[i10] = '-';
                i5 = -i5;
                i10 = writeInt64 + 2;
            }
            if (i5 > 99) {
                int i11 = (int) ((i5 * 1374389535) >> 37);
                cArr[i10] = (char) (i11 + 48);
                IOUtils.putIntUnaligned(cArr, i10 + 1, TWO_DIGITS_32_BITS[i5 - (i11 * 100)]);
                return i10 + 3;
            }
            if (i5 > 9) {
                IOUtils.putIntUnaligned(cArr, i10, TWO_DIGITS_32_BITS[i5]);
                return i10 + 2;
            }
            int i12 = i10 + 1;
            cArr[i10] = (char) (i5 + 48);
            return i12;
        }
        if (i5 < 0) {
            IOUtils.putIntUnaligned(cArr, i3, IOUtils.ZERO_DOT_32);
            int i13 = i3 + 2;
            if (i5 == -2) {
                cArr[i13] = '0';
                i13 = i3 + 3;
            } else if (i5 == -3) {
                IOUtils.putIntUnaligned(cArr, i13, 3145776);
                i13 = i3 + 4;
            }
            return IOUtils.writeInt64(cArr, i13, j2);
        }
        int i14 = (i4 - 1) - i5;
        if (i14 > 0) {
            int i15 = i14 - 1;
            long j5 = POW10_LONG_VALUES[i15];
            long j6 = (int) (j2 / j5);
            int writeInt642 = IOUtils.writeInt64(cArr, i3, j6);
            int i16 = writeInt642 + 1;
            cArr[writeInt642] = '.';
            long j7 = j2 - (j6 * j5);
            while (true) {
                i15--;
                if (i15 <= -1 || j7 >= POW10_LONG_VALUES[i15]) {
                    break;
                }
                cArr[i16] = '0';
                i16++;
            }
            return IOUtils.writeInt64(cArr, i16, j7);
        }
        int writeInt643 = IOUtils.writeInt64(cArr, i3, j2);
        int i17 = -i14;
        if (i17 > 0) {
            int i18 = 0;
            while (i18 < i17) {
                cArr[writeInt643] = '0';
                i18++;
                writeInt643++;
            }
        }
        IOUtils.putIntUnaligned(cArr, writeInt643, IOUtils.DOT_ZERO_32);
        return writeInt643 + 2;
    }
}
