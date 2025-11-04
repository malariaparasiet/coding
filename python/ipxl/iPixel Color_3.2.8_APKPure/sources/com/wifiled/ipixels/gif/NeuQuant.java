package com.wifiled.ipixels.gif;

import com.google.android.material.internal.ViewUtils;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class NeuQuant {
    protected static final int alphabiasshift = 10;
    protected static final int alpharadbias = 262144;
    protected static final int alpharadbshift = 18;
    protected static final int beta = 64;
    protected static final int betagamma = 65536;
    protected static final int betashift = 10;
    protected static final int gamma = 1024;
    protected static final int gammashift = 10;
    protected static final int initalpha = 1024;
    protected static final int initrad = 32;
    protected static final int initradius = 2048;
    protected static final int intbias = 65536;
    protected static final int intbiasshift = 16;
    protected static final int maxnetpos = 255;
    protected static final int minpicturebytes = 1509;
    protected static final int ncycles = 100;
    protected static final int netbiasshift = 4;
    protected static final int netsize = 256;
    protected static final int prime1 = 499;
    protected static final int prime2 = 491;
    protected static final int prime3 = 487;
    protected static final int prime4 = 503;
    protected static final int radbias = 256;
    protected static final int radbiasshift = 8;
    protected static final int radiusbias = 64;
    protected static final int radiusbiasshift = 6;
    protected static final int radiusdec = 30;
    protected int alphadec;
    protected int lengthcount;
    protected int samplefac;
    protected byte[] thepicture;
    protected int[] netindex = new int[256];
    protected int[] bias = new int[256];
    protected int[] freq = new int[256];
    protected int[] radpower = new int[32];
    protected int[][] network = new int[256][];

    public NeuQuant(byte[] thepic, int len, int sample) {
        this.thepicture = thepic;
        this.lengthcount = len;
        this.samplefac = sample;
        for (int i = 0; i < 256; i++) {
            this.network[i] = new int[]{r6, r6, r6, 0};
            int i2 = (i << 12) / 256;
            this.freq[i] = 256;
            this.bias[i] = 0;
        }
    }

    public byte[] colorMap() {
        byte[] bArr = new byte[ViewUtils.EDGE_TO_EDGE_FLAGS];
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[this.network[i][3]] = i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            int[] iArr2 = this.network[iArr[i3]];
            bArr[i2] = (byte) iArr2[0];
            int i4 = i2 + 2;
            bArr[i2 + 1] = (byte) iArr2[1];
            i2 += 3;
            bArr[i4] = (byte) iArr2[2];
        }
        return bArr;
    }

    public void inxbuild() {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < 256) {
            int[] iArr = this.network[i];
            int i4 = iArr[1];
            int i5 = i + 1;
            int i6 = i;
            for (int i7 = i5; i7 < 256; i7++) {
                int i8 = this.network[i7][1];
                if (i8 < i4) {
                    i6 = i7;
                    i4 = i8;
                }
            }
            int[] iArr2 = this.network[i6];
            if (i != i6) {
                int i9 = iArr2[0];
                iArr2[0] = iArr[0];
                iArr[0] = i9;
                int i10 = iArr2[1];
                iArr2[1] = iArr[1];
                iArr[1] = i10;
                int i11 = iArr2[2];
                iArr2[2] = iArr[2];
                iArr[2] = i11;
                int i12 = iArr2[3];
                iArr2[3] = iArr[3];
                iArr[3] = i12;
            }
            if (i4 != i2) {
                this.netindex[i2] = (i3 + i) >> 1;
                while (true) {
                    i2++;
                    if (i2 >= i4) {
                        break;
                    } else {
                        this.netindex[i2] = i;
                    }
                }
                i3 = i;
                i2 = i4;
            }
            i = i5;
        }
        this.netindex[i2] = (i3 + 255) >> 1;
        for (int i13 = i2 + 1; i13 < 256; i13++) {
            this.netindex[i13] = 255;
        }
    }

    public void learn() {
        int i;
        NeuQuant neuQuant = this;
        int i2 = neuQuant.lengthcount;
        int i3 = minpicturebytes;
        if (i2 < minpicturebytes) {
            neuQuant.samplefac = 1;
        }
        int i4 = neuQuant.samplefac;
        neuQuant.alphadec = ((i4 - 1) / 3) + 30;
        byte[] bArr = neuQuant.thepicture;
        int i5 = i2 / (i4 * 3);
        int i6 = i5 / 100;
        for (int i7 = 0; i7 < 32; i7++) {
            neuQuant.radpower[i7] = 1024 * (((1024 - (i7 * i7)) * 256) / 1024);
        }
        int i8 = neuQuant.lengthcount;
        if (i8 < minpicturebytes) {
            i = 3;
        } else {
            if (i8 % prime1 != 0) {
                i3 = 1497;
            } else if (i8 % prime2 != 0) {
                i3 = 1473;
            } else if (i8 % prime3 != 0) {
                i3 = 1461;
            }
            i = i3;
        }
        int i9 = i6;
        int i10 = 0;
        int i11 = 0;
        int i12 = 32;
        int i13 = 2048;
        int i14 = 1024;
        while (i10 < i5) {
            int i15 = (bArr[i11] & UByte.MAX_VALUE) << 4;
            int i16 = (bArr[i11 + 1] & UByte.MAX_VALUE) << 4;
            int i17 = (bArr[i11 + 2] & UByte.MAX_VALUE) << 4;
            int contest = neuQuant.contest(i15, i16, i17);
            neuQuant.altersingle(i14, contest, i15, i16, i17);
            int i18 = i14;
            neuQuant = this;
            int i19 = i12;
            if (i12 != 0) {
                neuQuant.alterneigh(i19, contest, i15, i16, i17);
            }
            int i20 = i11 + i;
            if (i20 >= i2) {
                i20 -= neuQuant.lengthcount;
            }
            i11 = i20;
            i10++;
            if (i9 == 0) {
                i9 = 1;
            }
            if (i10 % i9 == 0) {
                i14 = i18 - (i18 / neuQuant.alphadec);
                i13 -= i13 / 30;
                int i21 = i13 >> 6;
                i12 = i21 <= 1 ? 0 : i21;
                for (int i22 = 0; i22 < i12; i22++) {
                    int i23 = i12 * i12;
                    neuQuant.radpower[i22] = (((i23 - (i22 * i22)) * 256) / i23) * i14;
                }
            } else {
                i12 = i19;
                i14 = i18;
            }
        }
    }

    public int map(int b, int g, int r) {
        int i = this.netindex[g];
        int i2 = i - 1;
        int i3 = 1000;
        int i4 = -1;
        while (true) {
            if (i >= 256 && i2 < 0) {
                return i4;
            }
            if (i < 256) {
                int[] iArr = this.network[i];
                int i5 = iArr[1] - g;
                if (i5 >= i3) {
                    i = 256;
                } else {
                    i++;
                    if (i5 < 0) {
                        i5 = -i5;
                    }
                    int i6 = iArr[0] - b;
                    if (i6 < 0) {
                        i6 = -i6;
                    }
                    int i7 = i5 + i6;
                    if (i7 < i3) {
                        int i8 = iArr[2] - r;
                        if (i8 < 0) {
                            i8 = -i8;
                        }
                        int i9 = i7 + i8;
                        if (i9 < i3) {
                            i4 = iArr[3];
                            i3 = i9;
                        }
                    }
                }
            }
            if (i2 >= 0) {
                int[] iArr2 = this.network[i2];
                int i10 = g - iArr2[1];
                if (i10 >= i3) {
                    i2 = -1;
                } else {
                    i2--;
                    if (i10 < 0) {
                        i10 = -i10;
                    }
                    int i11 = iArr2[0] - b;
                    if (i11 < 0) {
                        i11 = -i11;
                    }
                    int i12 = i10 + i11;
                    if (i12 < i3) {
                        int i13 = iArr2[2] - r;
                        if (i13 < 0) {
                            i13 = -i13;
                        }
                        int i14 = i13 + i12;
                        if (i14 < i3) {
                            i4 = iArr2[3];
                            i3 = i14;
                        }
                    }
                }
            }
        }
    }

    public byte[] process() {
        learn();
        unbiasnet();
        inxbuild();
        return colorMap();
    }

    public void unbiasnet() {
        for (int i = 0; i < 256; i++) {
            int[] iArr = this.network[i];
            iArr[0] = iArr[0] >> 4;
            iArr[1] = iArr[1] >> 4;
            iArr[2] = iArr[2] >> 4;
            iArr[3] = i;
        }
    }

    protected void alterneigh(int rad, int i, int b, int g, int r) {
        int i2 = i - rad;
        if (i2 < -1) {
            i2 = -1;
        }
        int i3 = rad + i;
        if (i3 > 256) {
            i3 = 256;
        }
        int i4 = i + 1;
        int i5 = i - 1;
        int i6 = 1;
        while (true) {
            if (i4 >= i3 && i5 <= i2) {
                return;
            }
            int i7 = i6 + 1;
            int i8 = this.radpower[i6];
            if (i4 < i3) {
                int i9 = i4 + 1;
                int[] iArr = this.network[i4];
                try {
                    int i10 = iArr[0];
                    iArr[0] = i10 - (((i10 - b) * i8) / 262144);
                    int i11 = iArr[1];
                    iArr[1] = i11 - (((i11 - g) * i8) / 262144);
                    int i12 = iArr[2];
                    iArr[2] = i12 - (((i12 - r) * i8) / 262144);
                } catch (Exception unused) {
                }
                i4 = i9;
            }
            if (i5 > i2) {
                int i13 = i5 - 1;
                int[] iArr2 = this.network[i5];
                try {
                    int i14 = iArr2[0];
                    iArr2[0] = i14 - (((i14 - b) * i8) / 262144);
                    int i15 = iArr2[1];
                    iArr2[1] = i15 - (((i15 - g) * i8) / 262144);
                    int i16 = iArr2[2];
                    iArr2[2] = i16 - ((i8 * (i16 - r)) / 262144);
                } catch (Exception unused2) {
                }
                i6 = i7;
                i5 = i13;
            } else {
                i6 = i7;
            }
        }
    }

    protected void altersingle(int alpha, int i, int b, int g, int r) {
        int[] iArr = this.network[i];
        int i2 = iArr[0];
        iArr[0] = i2 - (((i2 - b) * alpha) / 1024);
        int i3 = iArr[1];
        iArr[1] = i3 - (((i3 - g) * alpha) / 1024);
        int i4 = iArr[2];
        iArr[2] = i4 - ((alpha * (i4 - r)) / 1024);
    }

    protected int contest(int b, int g, int r) {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        int i3 = -1;
        int i4 = Integer.MAX_VALUE;
        for (int i5 = 0; i5 < 256; i5++) {
            int[] iArr = this.network[i5];
            int i6 = iArr[0] - b;
            if (i6 < 0) {
                i6 = -i6;
            }
            int i7 = iArr[1] - g;
            if (i7 < 0) {
                i7 = -i7;
            }
            int i8 = i6 + i7;
            int i9 = iArr[2] - r;
            if (i9 < 0) {
                i9 = -i9;
            }
            int i10 = i8 + i9;
            if (i10 < i) {
                i2 = i5;
                i = i10;
            }
            int[] iArr2 = this.bias;
            int i11 = i10 - (iArr2[i5] >> 12);
            if (i11 < i4) {
                i3 = i5;
                i4 = i11;
            }
            int[] iArr3 = this.freq;
            int i12 = iArr3[i5];
            int i13 = i12 >> 10;
            iArr3[i5] = i12 - i13;
            iArr2[i5] = iArr2[i5] + (i13 << 10);
        }
        int[] iArr4 = this.freq;
        iArr4[i2] = iArr4[i2] + 64;
        int[] iArr5 = this.bias;
        iArr5[i2] = iArr5[i2] - 65536;
        return i3;
    }
}
