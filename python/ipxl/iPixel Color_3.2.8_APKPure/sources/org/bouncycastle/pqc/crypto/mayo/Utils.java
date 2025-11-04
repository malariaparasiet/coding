package org.bouncycastle.pqc.crypto.mayo;

import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CTRModeCipher;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
class Utils {
    Utils() {
    }

    public static void expandP1P2(MayoParameters mayoParameters, long[] jArr, byte[] bArr) {
        int p1Bytes = mayoParameters.getP1Bytes() + mayoParameters.getP2Bytes();
        byte[] bArr2 = new byte[p1Bytes];
        CTRModeCipher newInstance = SICBlockCipher.newInstance(AESEngine.newInstance());
        newInstance.init(true, new ParametersWithIV(new KeyParameter(Arrays.copyOf(bArr, mayoParameters.getPkSeedBytes())), new byte[16]));
        int blockSize = newInstance.getBlockSize();
        byte[] bArr3 = new byte[blockSize];
        byte[] bArr4 = new byte[blockSize];
        int i = 0;
        while (true) {
            int i2 = i + blockSize;
            if (i2 > p1Bytes) {
                break;
            }
            newInstance.processBlock(bArr3, 0, bArr4, 0);
            System.arraycopy(bArr4, 0, bArr2, i, blockSize);
            i = i2;
        }
        if (i < p1Bytes) {
            newInstance.processBlock(bArr3, 0, bArr4, 0);
            System.arraycopy(bArr4, 0, bArr2, i, p1Bytes - i);
        }
        unpackMVecs(bArr2, 0, jArr, 0, (mayoParameters.getP1Limbs() + mayoParameters.getP2Limbs()) / mayoParameters.getMVecLimbs(), mayoParameters.getM());
    }

    public static void packMVecs(long[] jArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i3 + 15) >> 4;
        int i5 = i3 >> 1;
        int i6 = (8 - (i4 << 3)) + i5;
        int i7 = 0;
        int i8 = 0;
        while (i7 < i2) {
            int i9 = 0;
            while (i9 < i4 - 1) {
                Pack.longToLittleEndian(jArr[i8 + i9], bArr, (i9 << 3) + i);
                i9++;
            }
            Pack.longToLittleEndian(jArr[i8 + i9], bArr, (i9 << 3) + i, i6);
            i7++;
            i += i5;
            i8 += i4;
        }
    }

    public static void unpackMVecs(byte[] bArr, int i, long[] jArr, int i2, int i3, int i4) {
        int i5 = (i4 + 15) >> 4;
        int i6 = i4 >> 1;
        int i7 = (8 - (i5 << 3)) + i6;
        int i8 = i3 - 1;
        int i9 = i2 + (i8 * i5);
        int i10 = i + (i8 * i6);
        while (i8 >= 0) {
            int i11 = 0;
            while (i11 < i5 - 1) {
                jArr[i9 + i11] = Pack.littleEndianToLong(bArr, (i11 << 3) + i10);
                i11++;
            }
            jArr[i9 + i11] = Pack.littleEndianToLong(bArr, (i11 << 3) + i10, i7);
            i8--;
            i9 -= i5;
            i10 -= i6;
        }
    }
}
