package org.bouncycastle.pqc.crypto.falcon;

import kotlin.UByte;

/* loaded from: classes4.dex */
class FalconCodec {
    static final byte[] max_fg_bits = {0, 8, 8, 8, 8, 8, 7, 7, 6, 6, 5};
    static final byte[] max_FG_bits = {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};

    FalconCodec() {
    }

    static int comp_decode(short[] sArr, int i, byte[] bArr, int i2) {
        int i3 = 1 << i;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i3; i7++) {
            if (i6 >= i2) {
                return 0;
            }
            i4 = (i4 << 8) | (bArr[i6] & UByte.MAX_VALUE);
            i6++;
            int i8 = i4 >>> i5;
            int i9 = i8 & 128;
            int i10 = i8 & 127;
            do {
                if (i5 == 0) {
                    if (i6 >= i2) {
                        return 0;
                    }
                    i4 = (i4 << 8) | (bArr[i6] & UByte.MAX_VALUE);
                    i6++;
                    i5 = 8;
                }
                i5--;
                if (((i4 >>> i5) & 1) == 0) {
                    i10 += 128;
                } else {
                    if (i9 != 0 && i10 == 0) {
                        return 0;
                    }
                    if (i9 != 0) {
                        i10 = -i10;
                    }
                    sArr[i7] = (short) i10;
                }
            } while (i10 <= 2047);
            return 0;
        }
        if ((((1 << i5) - 1) & i4) != 0) {
            return 0;
        }
        return i6;
    }

    static int comp_encode(byte[] bArr, int i, short[] sArr, int i2) {
        int i3 = 1 << i2;
        for (int i4 = 0; i4 < i3; i4++) {
            short s = sArr[i4];
            if (s < -2047 || s > 2047) {
                return 0;
            }
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < i3; i8++) {
            int i9 = i6 << 1;
            short s2 = sArr[i8];
            int i10 = s2;
            if (s2 < 0) {
                i9 |= 1;
                i10 = -s2;
            }
            int i11 = (i9 << 7) | (i10 & 127);
            int i12 = (i10 >>> 7) + 1;
            i6 = (i11 << i12) | 1;
            i5 = i5 + 8 + i12;
            while (i5 >= 8) {
                i5 -= 8;
                if (bArr != null) {
                    if (i7 >= i) {
                        return 0;
                    }
                    bArr[i7] = (byte) (i6 >>> i5);
                }
                i7++;
            }
        }
        if (i5 <= 0) {
            return i7;
        }
        if (bArr != null) {
            if (i7 >= i) {
                return 0;
            }
            bArr[i7] = (byte) (i6 << (8 - i5));
        }
        return i7 + 1;
    }

    static int modq_decode(short[] sArr, int i, byte[] bArr, int i2) {
        int i3 = 1 << i;
        int i4 = ((i3 * 14) + 7) >> 3;
        if (i4 > i2) {
            return 0;
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i5 < i3) {
            int i9 = i8 + 1;
            i6 = (i6 << 8) | (bArr[i8] & UByte.MAX_VALUE);
            int i10 = i7 + 8;
            if (i10 >= 14) {
                i7 -= 6;
                int i11 = (i6 >>> i7) & 16383;
                if (i11 >= 12289) {
                    return 0;
                }
                sArr[i5] = (short) i11;
                i5++;
            } else {
                i7 = i10;
            }
            i8 = i9;
        }
        if ((((1 << i7) - 1) & i6) != 0) {
            return 0;
        }
        return i4;
    }

    static int modq_encode(byte[] bArr, int i, short[] sArr, int i2) {
        int i3 = 1;
        int i4 = 1 << i2;
        for (int i5 = 0; i5 < i4; i5++) {
            if ((65535 & sArr[i5]) >= 12289) {
                return 0;
            }
        }
        int i6 = ((i4 * 14) + 7) >> 3;
        if (bArr != null) {
            if (i6 > i) {
                return 0;
            }
            int i7 = 0;
            int i8 = 0;
            for (int i9 = 0; i9 < i4; i9++) {
                i8 = (i8 << 14) | (sArr[i9] & 65535);
                i7 += 14;
                while (i7 >= 8) {
                    i7 -= 8;
                    bArr[i3] = (byte) (i8 >> i7);
                    i3++;
                }
            }
            if (i7 > 0) {
                bArr[i3] = (byte) (i8 << (8 - i7));
            }
        }
        return i6;
    }

    static int trim_i8_decode(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = 1 << i;
        int i6 = ((i5 * i2) + 7) >> 3;
        if (i6 > i4) {
            return 0;
        }
        int i7 = (1 << i2) - 1;
        int i8 = 1 << (i2 - 1);
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i9 < i5) {
            int i12 = i3 + 1;
            i10 = (i10 << 8) | (bArr2[i3] & 255);
            i11 += 8;
            while (i11 >= i2 && i9 < i5) {
                i11 -= i2;
                int i13 = (i10 >>> i11) & i7;
                int i14 = i13 | (-(i13 & i8));
                if (i14 == (-i8)) {
                    return 0;
                }
                bArr[i9] = (byte) i14;
                i9++;
            }
            i3 = i12;
        }
        if ((((1 << i11) - 1) & i10) != 0) {
            return 0;
        }
        return i6;
    }

    static int trim_i8_encode(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = 1 << i3;
        int i6 = (1 << (i4 - 1)) - 1;
        int i7 = -i6;
        for (int i8 = 0; i8 < i5; i8++) {
            int i9 = bArr2[i8];
            if (i9 < i7 || i9 > i6) {
                return 0;
            }
        }
        int i10 = ((i5 * i4) + 7) >> 3;
        if (bArr != null) {
            if (i10 > i2) {
                return 0;
            }
            int i11 = (1 << i4) - 1;
            int i12 = 0;
            int i13 = 0;
            for (int i14 = 0; i14 < i5; i14++) {
                i13 = (i13 << i4) | (bArr2[i14] & 65535 & i11);
                i12 += i4;
                while (i12 >= 8) {
                    i12 -= 8;
                    bArr[i] = (byte) (i13 >>> i12);
                    i++;
                }
            }
            if (i12 > 0) {
                bArr[i] = (byte) (i13 << (8 - i12));
            }
        }
        return i10;
    }
}
