package org.bouncycastle.pqc.legacy.crypto.gmss.util;

import kotlin.UByte;
import org.bouncycastle.crypto.Digest;

/* loaded from: classes4.dex */
public class WinternitzOTSVerify {
    private int mdsize;
    private Digest messDigestOTS;
    private int w;

    public WinternitzOTSVerify(Digest digest, int i) {
        this.w = i;
        this.messDigestOTS = digest;
        this.mdsize = digest.getDigestSize();
    }

    private void hashSignatureBlock(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 < 1) {
            System.arraycopy(bArr, i, bArr2, i3, this.mdsize);
            return;
        }
        this.messDigestOTS.update(bArr, i, this.mdsize);
        while (true) {
            this.messDigestOTS.doFinal(bArr2, i3);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                this.messDigestOTS.update(bArr2, i3, this.mdsize);
            }
        }
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = this.mdsize;
        byte[] bArr3 = new byte[i5];
        int i6 = 0;
        this.messDigestOTS.update(bArr, 0, bArr.length);
        this.messDigestOTS.doFinal(bArr3, 0);
        int i7 = this.mdsize << 3;
        int i8 = this.w;
        int i9 = (i7 + (i8 - 1)) / i8;
        boolean z = true;
        int log = getLog((i9 << i8) + 1);
        int i10 = this.w;
        int i11 = this.mdsize;
        int i12 = i11 * ((((log + i10) - 1) / i10) + i9);
        if (i12 != bArr2.length) {
            return null;
        }
        byte[] bArr4 = new byte[i12];
        char c = '\b';
        if (8 % i10 == 0) {
            int i13 = 8 / i10;
            int i14 = (1 << i10) - 1;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            while (i17 < i5) {
                int i18 = i16;
                int i19 = 0;
                while (i19 < i13) {
                    int i20 = bArr3[i17] & i14;
                    int i21 = i15 + i20;
                    int i22 = this.mdsize;
                    hashSignatureBlock(bArr2, i18 * i22, i14 - i20, bArr4, i22 * i18);
                    bArr3[i17] = (byte) (bArr3[i17] >>> this.w);
                    i18++;
                    i19++;
                    i15 = i21;
                }
                i17++;
                i16 = i18;
            }
            int i23 = (i9 << this.w) - i15;
            int i24 = i16;
            int i25 = 0;
            while (i25 < log) {
                int i26 = this.mdsize;
                hashSignatureBlock(bArr2, i24 * i26, i14 - (i23 & i14), bArr4, i24 * i26);
                int i27 = this.w;
                i23 >>>= i27;
                i24++;
                i25 += i27;
            }
        } else if (i10 < 8) {
            int i28 = i11 / i10;
            int i29 = (1 << i10) - 1;
            int i30 = 0;
            int i31 = 0;
            int i32 = 0;
            int i33 = 0;
            while (i30 < i28) {
                int i34 = i31;
                boolean z2 = z;
                long j = 0;
                for (int i35 = 0; i35 < this.w; i35++) {
                    j ^= (bArr3[i34] & UByte.MAX_VALUE) << (i35 << 3);
                    i34++;
                }
                int i36 = 0;
                while (i36 < 8) {
                    int i37 = (int) (j & i29);
                    int i38 = i32 + i37;
                    int i39 = this.mdsize;
                    hashSignatureBlock(bArr2, i33 * i39, i29 - i37, bArr4, i39 * i33);
                    j >>>= this.w;
                    i33++;
                    i36++;
                    i32 = i38;
                    i29 = i29;
                    i30 = i30;
                }
                i30++;
                z = z2;
                i31 = i34;
            }
            int i40 = i29;
            int i41 = this.mdsize % this.w;
            long j2 = 0;
            for (int i42 = 0; i42 < i41; i42++) {
                j2 ^= (bArr3[i31] & UByte.MAX_VALUE) << (i42 << 3);
                i31++;
            }
            int i43 = i41 << 3;
            int i44 = 0;
            while (i44 < i43) {
                int i45 = (int) (j2 & i40);
                int i46 = i32 + i45;
                int i47 = this.mdsize;
                hashSignatureBlock(bArr2, i33 * i47, i40 - i45, bArr4, i33 * i47);
                int i48 = this.w;
                j2 >>>= i48;
                i33++;
                i44 += i48;
                i32 = i46;
            }
            int i49 = (i9 << this.w) - i32;
            int i50 = 0;
            while (i50 < log) {
                int i51 = this.mdsize;
                hashSignatureBlock(bArr2, i33 * i51, i40 - (i49 & i40), bArr4, i33 * i51);
                int i52 = this.w;
                i49 >>>= i52;
                i33++;
                i50 += i52;
            }
        } else if (i10 < 57) {
            int i53 = (i11 << 3) - i10;
            int i54 = (1 << i10) - 1;
            byte[] bArr5 = new byte[i11];
            int i55 = 0;
            int i56 = 0;
            int i57 = 0;
            while (i55 <= i53) {
                int i58 = i55 >>> 3;
                int i59 = i55 % 8;
                char c2 = c;
                i55 += this.w;
                int i60 = i6;
                int i61 = i58;
                long j3 = 0;
                while (true) {
                    i4 = i53;
                    if (i61 >= ((i55 + 7) >>> 3)) {
                        break;
                    }
                    j3 ^= (bArr3[i61] & UByte.MAX_VALUE) << (i60 << 3);
                    i60++;
                    i61++;
                    i53 = i4;
                    bArr3 = bArr3;
                }
                byte[] bArr6 = bArr3;
                long j4 = i54;
                long j5 = (j3 >>> i59) & j4;
                i56 = (int) (i56 + j5);
                int i62 = this.mdsize;
                System.arraycopy(bArr2, i57 * i62, bArr5, 0, i62);
                while (j5 < j4) {
                    this.messDigestOTS.update(bArr5, 0, i11);
                    this.messDigestOTS.doFinal(bArr5, 0);
                    j5++;
                }
                int i63 = this.mdsize;
                System.arraycopy(bArr5, 0, bArr4, i57 * i63, i63);
                i57++;
                c = c2;
                i53 = i4;
                bArr3 = bArr6;
                i6 = 0;
            }
            byte[] bArr7 = bArr3;
            int i64 = i55 >>> 3;
            if (i64 < this.mdsize) {
                int i65 = i55 % 8;
                int i66 = 0;
                long j6 = 0;
                while (true) {
                    i3 = this.mdsize;
                    if (i64 >= i3) {
                        break;
                    }
                    j6 ^= (bArr7[i64] & UByte.MAX_VALUE) << (i66 << 3);
                    i66++;
                    i64++;
                }
                i = i9;
                long j7 = i54;
                long j8 = (j6 >>> i65) & j7;
                i56 = (int) (i56 + j8);
                System.arraycopy(bArr2, i57 * i3, bArr5, 0, i3);
                while (j8 < j7) {
                    this.messDigestOTS.update(bArr5, 0, i11);
                    this.messDigestOTS.doFinal(bArr5, 0);
                    j8++;
                }
                int i67 = this.mdsize;
                System.arraycopy(bArr5, 0, bArr4, i57 * i67, i67);
                i57++;
            } else {
                i = i9;
            }
            int i68 = (i << this.w) - i56;
            int i69 = 0;
            while (i69 < log) {
                int i70 = this.mdsize;
                System.arraycopy(bArr2, i57 * i70, bArr5, 0, i70);
                int i71 = i57;
                for (long j9 = i68 & i54; j9 < i54; j9++) {
                    this.messDigestOTS.update(bArr5, 0, i11);
                    this.messDigestOTS.doFinal(bArr5, 0);
                }
                int i72 = this.mdsize;
                System.arraycopy(bArr5, 0, bArr4, i71 * i72, i72);
                int i73 = this.w;
                i68 >>>= i73;
                i57 = i71 + 1;
                i69 += i73;
            }
            i2 = 0;
            this.messDigestOTS.update(bArr4, i2, i12);
            byte[] bArr8 = new byte[this.mdsize];
            this.messDigestOTS.doFinal(bArr8, i2);
            return bArr8;
        }
        i2 = 0;
        this.messDigestOTS.update(bArr4, i2, i12);
        byte[] bArr82 = new byte[this.mdsize];
        this.messDigestOTS.doFinal(bArr82, i2);
        return bArr82;
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int i = this.w;
        int i2 = ((digestSize << 3) + (i - 1)) / i;
        int log = getLog((i2 << i) + 1);
        return digestSize * (i2 + (((log + r3) - 1) / this.w));
    }
}
