package org.bouncycastle.pqc.crypto.mldsa;

import kotlin.UByte;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
class Packing {
    Packing() {
    }

    static byte[] packPublicKey(PolyVecK polyVecK, MLDSAEngine mLDSAEngine) {
        byte[] bArr = new byte[mLDSAEngine.getCryptoPublicKeyBytes() - 32];
        for (int i = 0; i < mLDSAEngine.getDilithiumK(); i++) {
            System.arraycopy(polyVecK.getVectorIndex(i).polyt1Pack(), 0, bArr, i * 320, 320);
        }
        return bArr;
    }

    static byte[][] packSecretKey(byte[] bArr, byte[] bArr2, byte[] bArr3, PolyVecK polyVecK, PolyVecL polyVecL, PolyVecK polyVecK2, MLDSAEngine mLDSAEngine) {
        byte[][] bArr4 = new byte[6][];
        bArr4[0] = bArr;
        bArr4[1] = bArr3;
        bArr4[2] = bArr2;
        bArr4[3] = new byte[mLDSAEngine.getDilithiumL() * mLDSAEngine.getDilithiumPolyEtaPackedBytes()];
        for (int i = 0; i < mLDSAEngine.getDilithiumL(); i++) {
            polyVecL.getVectorIndex(i).polyEtaPack(bArr4[3], mLDSAEngine.getDilithiumPolyEtaPackedBytes() * i);
        }
        bArr4[4] = new byte[mLDSAEngine.getDilithiumK() * mLDSAEngine.getDilithiumPolyEtaPackedBytes()];
        for (int i2 = 0; i2 < mLDSAEngine.getDilithiumK(); i2++) {
            polyVecK2.getVectorIndex(i2).polyEtaPack(bArr4[4], mLDSAEngine.getDilithiumPolyEtaPackedBytes() * i2);
        }
        bArr4[5] = new byte[mLDSAEngine.getDilithiumK() * 416];
        for (int i3 = 0; i3 < mLDSAEngine.getDilithiumK(); i3++) {
            polyVecK.getVectorIndex(i3).polyt0Pack(bArr4[5], i3 * 416);
        }
        return bArr4;
    }

    static void packSignature(byte[] bArr, PolyVecL polyVecL, PolyVecK polyVecK, MLDSAEngine mLDSAEngine) {
        int dilithiumCTilde = mLDSAEngine.getDilithiumCTilde();
        for (int i = 0; i < mLDSAEngine.getDilithiumL(); i++) {
            polyVecL.getVectorIndex(i).zPack(bArr, dilithiumCTilde);
            dilithiumCTilde += mLDSAEngine.getDilithiumPolyZPackedBytes();
        }
        for (int i2 = 0; i2 < mLDSAEngine.getDilithiumOmega() + mLDSAEngine.getDilithiumK(); i2++) {
            bArr[dilithiumCTilde + i2] = 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < mLDSAEngine.getDilithiumK(); i4++) {
            for (int i5 = 0; i5 < 256; i5++) {
                if (polyVecK.getVectorIndex(i4).getCoeffIndex(i5) != 0) {
                    bArr[i3 + dilithiumCTilde] = (byte) i5;
                    i3++;
                }
            }
            bArr[mLDSAEngine.getDilithiumOmega() + dilithiumCTilde + i4] = (byte) i3;
        }
    }

    static PolyVecK unpackPublicKey(PolyVecK polyVecK, byte[] bArr, MLDSAEngine mLDSAEngine) {
        int i = 0;
        while (i < mLDSAEngine.getDilithiumK()) {
            Poly vectorIndex = polyVecK.getVectorIndex(i);
            int i2 = i * 320;
            i++;
            vectorIndex.polyt1Unpack(Arrays.copyOfRange(bArr, i2, i * 320));
        }
        return polyVecK;
    }

    static void unpackSecretKey(PolyVecK polyVecK, PolyVecL polyVecL, PolyVecK polyVecK2, byte[] bArr, byte[] bArr2, byte[] bArr3, MLDSAEngine mLDSAEngine) {
        for (int i = 0; i < mLDSAEngine.getDilithiumL(); i++) {
            polyVecL.getVectorIndex(i).polyEtaUnpack(bArr2, mLDSAEngine.getDilithiumPolyEtaPackedBytes() * i);
        }
        for (int i2 = 0; i2 < mLDSAEngine.getDilithiumK(); i2++) {
            polyVecK2.getVectorIndex(i2).polyEtaUnpack(bArr3, mLDSAEngine.getDilithiumPolyEtaPackedBytes() * i2);
        }
        for (int i3 = 0; i3 < mLDSAEngine.getDilithiumK(); i3++) {
            polyVecK.getVectorIndex(i3).polyt0Unpack(bArr, i3 * 416);
        }
    }

    static boolean unpackSignature(PolyVecL polyVecL, PolyVecK polyVecK, byte[] bArr, MLDSAEngine mLDSAEngine) {
        int dilithiumCTilde = mLDSAEngine.getDilithiumCTilde();
        int i = 0;
        while (i < mLDSAEngine.getDilithiumL()) {
            Poly vectorIndex = polyVecL.getVectorIndex(i);
            int dilithiumPolyZPackedBytes = (mLDSAEngine.getDilithiumPolyZPackedBytes() * i) + dilithiumCTilde;
            i++;
            vectorIndex.zUnpack(Arrays.copyOfRange(bArr, dilithiumPolyZPackedBytes, (mLDSAEngine.getDilithiumPolyZPackedBytes() * i) + dilithiumCTilde));
        }
        int dilithiumL = dilithiumCTilde + (mLDSAEngine.getDilithiumL() * mLDSAEngine.getDilithiumPolyZPackedBytes());
        int i2 = 0;
        for (int i3 = 0; i3 < mLDSAEngine.getDilithiumK(); i3++) {
            for (int i4 = 0; i4 < 256; i4++) {
                polyVecK.getVectorIndex(i3).setCoeffIndex(i4, 0);
            }
            if ((bArr[mLDSAEngine.getDilithiumOmega() + dilithiumL + i3] & 255) < i2 || (bArr[mLDSAEngine.getDilithiumOmega() + dilithiumL + i3] & 255) > mLDSAEngine.getDilithiumOmega()) {
                return false;
            }
            for (int i5 = i2; i5 < (bArr[mLDSAEngine.getDilithiumOmega() + dilithiumL + i3] & 255); i5++) {
                if (i5 > i2) {
                    int i6 = dilithiumL + i5;
                    if ((bArr[i6] & UByte.MAX_VALUE) <= (bArr[i6 - 1] & UByte.MAX_VALUE)) {
                        return false;
                    }
                }
                polyVecK.getVectorIndex(i3).setCoeffIndex(bArr[dilithiumL + i5] & UByte.MAX_VALUE, 1);
            }
            i2 = bArr[mLDSAEngine.getDilithiumOmega() + dilithiumL + i3];
        }
        while (i2 < mLDSAEngine.getDilithiumOmega()) {
            if ((bArr[dilithiumL + i2] & UByte.MAX_VALUE) != 0) {
                return false;
            }
            i2++;
        }
        return true;
    }
}
