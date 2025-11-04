package com.wifiled.ipixels.core;

import androidx.core.app.FrameMetricsAggregator;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.UByte;

/* loaded from: classes3.dex */
class LzwEncoder {
    static final int BITS = 12;
    private static final int EOF = -1;
    static final int HSIZE = 5003;
    int ClearCode;
    int EOFCode;
    int a_count;
    private int curPixel;
    int g_init_bits;
    private int imgH;
    private int imgW;
    private int initCodeSize;
    int maxcode;
    int n_bits;
    private byte[] pixAry;
    private int remaining;
    int maxbits = 12;
    int maxmaxcode = 4096;
    int[] htab = new int[HSIZE];
    int[] codetab = new int[HSIZE];
    int hsize = HSIZE;
    int free_ent = 0;
    boolean clear_flg = false;
    int cur_accum = 0;
    int cur_bits = 0;
    int[] masks = {0, 1, 3, 7, 15, 31, 63, 127, 255, FrameMetricsAggregator.EVERY_DURATION, 1023, 2047, 4095, 8191, 16383, 32767, 65535};
    byte[] accum = new byte[256];

    final int MAXCODE(int n_bits) {
        return (1 << n_bits) - 1;
    }

    LzwEncoder(int width, int height, byte[] pixels, int color_depth) {
        this.imgW = width;
        this.imgH = height;
        this.pixAry = pixels;
        this.initCodeSize = Math.max(2, color_depth);
    }

    void char_out(byte c, OutputStream outs) throws IOException {
        byte[] bArr = this.accum;
        int i = this.a_count;
        int i2 = i + 1;
        this.a_count = i2;
        bArr[i] = c;
        if (i2 >= 254) {
            flush_char(outs);
        }
    }

    void cl_block(OutputStream outs) throws IOException {
        cl_hash(this.hsize);
        int i = this.ClearCode;
        this.free_ent = i + 2;
        this.clear_flg = true;
        output(i, outs);
    }

    void cl_hash(int hsize) {
        for (int i = 0; i < hsize; i++) {
            this.htab[i] = -1;
        }
    }

    void compress(int init_bits, OutputStream outs) throws IOException {
        int i;
        this.g_init_bits = init_bits;
        int i2 = 0;
        this.clear_flg = false;
        this.n_bits = init_bits;
        this.maxcode = MAXCODE(init_bits);
        int i3 = 1 << (init_bits - 1);
        this.ClearCode = i3;
        this.EOFCode = i3 + 1;
        this.free_ent = i3 + 2;
        this.a_count = 0;
        int nextPixel = nextPixel();
        for (int i4 = this.hsize; i4 < 65536; i4 *= 2) {
            i2++;
        }
        int i5 = 8 - i2;
        int i6 = this.hsize;
        cl_hash(i6);
        output(this.ClearCode, outs);
        while (true) {
            int nextPixel2 = nextPixel();
            if (nextPixel2 != -1) {
                int i7 = (nextPixel2 << this.maxbits) + nextPixel;
                int i8 = (nextPixel2 << i5) ^ nextPixel;
                int i9 = this.htab[i8];
                if (i9 == i7) {
                    nextPixel = this.codetab[i8];
                } else {
                    if (i9 >= 0) {
                        int i10 = i6 - i8;
                        if (i8 == 0) {
                            i10 = 1;
                        }
                        do {
                            i8 -= i10;
                            if (i8 < 0) {
                                i8 += i6;
                            }
                            i = this.htab[i8];
                            if (i == i7) {
                                nextPixel = this.codetab[i8];
                                break;
                            }
                        } while (i >= 0);
                    }
                    output(nextPixel, outs);
                    int i11 = this.free_ent;
                    if (i11 < this.maxmaxcode) {
                        int[] iArr = this.codetab;
                        this.free_ent = i11 + 1;
                        iArr[i8] = i11;
                        this.htab[i8] = i7;
                    } else {
                        cl_block(outs);
                    }
                    nextPixel = nextPixel2;
                }
            } else {
                output(nextPixel, outs);
                output(this.EOFCode, outs);
                return;
            }
        }
    }

    void encode(OutputStream os) throws IOException {
        os.write(this.initCodeSize);
        this.remaining = this.imgW * this.imgH;
        this.curPixel = 0;
        compress(this.initCodeSize + 1, os);
        os.write(0);
    }

    void flush_char(OutputStream outs) throws IOException {
        int i = this.a_count;
        if (i > 0) {
            outs.write(i);
            outs.write(this.accum, 0, this.a_count);
            this.a_count = 0;
        }
    }

    private int nextPixel() {
        int i = this.remaining;
        if (i == 0) {
            return -1;
        }
        this.remaining = i - 1;
        byte[] bArr = this.pixAry;
        int i2 = this.curPixel;
        this.curPixel = i2 + 1;
        return bArr[i2] & UByte.MAX_VALUE;
    }

    void output(int code, OutputStream outs) throws IOException {
        int i = this.cur_accum;
        int[] iArr = this.masks;
        int i2 = this.cur_bits;
        int i3 = i & iArr[i2];
        this.cur_accum = i3;
        if (i2 > 0) {
            this.cur_accum = i3 | (code << i2);
        } else {
            this.cur_accum = code;
        }
        this.cur_bits = i2 + this.n_bits;
        while (this.cur_bits >= 8) {
            char_out((byte) (this.cur_accum & 255), outs);
            this.cur_accum >>= 8;
            this.cur_bits -= 8;
        }
        if (this.free_ent > this.maxcode || this.clear_flg) {
            if (this.clear_flg) {
                int i4 = this.g_init_bits;
                this.n_bits = i4;
                this.maxcode = MAXCODE(i4);
                this.clear_flg = false;
            } else {
                int i5 = this.n_bits + 1;
                this.n_bits = i5;
                if (i5 == this.maxbits) {
                    this.maxcode = this.maxmaxcode;
                } else {
                    this.maxcode = MAXCODE(i5);
                }
            }
        }
        if (code == this.EOFCode) {
            while (this.cur_bits > 0) {
                char_out((byte) (this.cur_accum & 255), outs);
                this.cur_accum >>= 8;
                this.cur_bits -= 8;
            }
            flush_char(outs);
        }
    }
}
