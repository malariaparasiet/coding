package org.bouncycastle.pqc.crypto.falcon;

import kotlin.UByte;
import org.bouncycastle.crypto.digests.SHAKEDigest;

/* loaded from: classes4.dex */
class FalconCommon {
    static final int[] l2bound = {0, 101498, 208714, 428865, 892039, 1852696, 3842630, 7959734, 16468416, 34034726, 70265242};

    FalconCommon() {
    }

    static void hash_to_point_vartime(SHAKEDigest sHAKEDigest, short[] sArr, int i) {
        int i2 = 1 << i;
        byte[] bArr = new byte[2];
        int i3 = 0;
        while (i2 > 0) {
            sHAKEDigest.doOutput(bArr, 0, 2);
            int i4 = ((bArr[0] & UByte.MAX_VALUE) << 8) | (bArr[1] & UByte.MAX_VALUE);
            if (i4 < 61445) {
                sArr[i3] = (short) (i4 % 12289);
                i2--;
                i3++;
            }
        }
    }

    static int is_short(short[] sArr, int i, short[] sArr2, int i2) {
        int i3 = 1 << i2;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            short s = sArr[i + i6];
            int i7 = i4 + (s * s);
            int i8 = i5 | i7;
            short s2 = sArr2[i6];
            i4 = i7 + (s2 * s2);
            i5 = i8 | i4;
        }
        return (((long) ((-(i5 >>> 31)) | i4)) & 4294967295L) <= ((long) l2bound[i2]) ? 1 : 0;
    }

    static int is_short_half(int i, short[] sArr, int i2) {
        int i3 = 1 << i2;
        int i4 = -(i >>> 31);
        for (int i5 = 0; i5 < i3; i5++) {
            short s = sArr[i5];
            i += s * s;
            i4 |= i;
        }
        return (((long) (i | (-(i4 >>> 31)))) & 4294967295L) <= ((long) l2bound[i2]) ? 1 : 0;
    }
}
