package com.wifiled.ipixels.core;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class GifEncoder {
    protected int colorDepth;
    protected byte[] colorTab;
    protected int height;
    protected Bitmap image;
    protected byte[] indexedPixels;
    protected OutputStream out;
    protected byte[] pixels;
    protected int transIndex;
    protected int width;
    protected Color transparent = null;
    protected int repeat = -1;
    protected int delay = 0;
    protected boolean started = false;
    protected boolean[] usedEntry = new boolean[256];
    protected int palSize = 7;
    protected int dispose = -1;
    protected boolean closeStream = false;
    protected boolean firstFrame = true;
    protected boolean sizeSet = false;
    protected int sample = 10;

    public void setDelay(int ms) {
        this.delay = Math.round(ms / 10.0f);
    }

    public void setDispose(int code) {
        if (code >= 0) {
            this.dispose = code;
        }
    }

    public void setRepeat(int iter) {
        if (iter >= 0) {
            this.repeat = iter;
        }
    }

    public void setTransparent(Color c) {
        this.transparent = c;
    }

    public boolean addFrame(Bitmap im) {
        if (im != null && this.started) {
            try {
                if (!this.sizeSet) {
                    setSize(im.getWidth(), im.getHeight());
                }
                this.image = im;
                getImagePixels();
                analyzePixels();
                if (this.firstFrame) {
                    writeLSD();
                    writePalette();
                    if (this.repeat >= 0) {
                        writeNetscapeExt();
                    }
                }
                writeGraphicCtrlExt();
                writeImageDesc();
                if (!this.firstFrame) {
                    writePalette();
                }
                writePixels();
                this.firstFrame = false;
                return true;
            } catch (IOException unused) {
            }
        }
        return false;
    }

    public boolean outFlush() {
        try {
            this.out.flush();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public byte[] getFrameByteArray() {
        return ((ByteArrayOutputStream) this.out).toByteArray();
    }

    public boolean finish() {
        if (!this.started) {
            return false;
        }
        this.started = false;
        try {
            this.out.write(59);
            this.out.flush();
            if (this.closeStream) {
                this.out.close();
            }
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public void reset() {
        this.transIndex = 0;
        this.out = null;
        this.image = null;
        this.pixels = null;
        this.indexedPixels = null;
        this.colorTab = null;
        this.closeStream = false;
        this.firstFrame = true;
    }

    public void setFrameRate(float fps) {
        if (fps != 0.0f) {
            this.delay = Math.round(100.0f / fps);
        }
    }

    public void setQuality(int quality) {
        if (quality < 1) {
            quality = 1;
        }
        this.sample = quality;
    }

    public void setSize(int w, int h) {
        if (!this.started || this.firstFrame) {
            this.width = w;
            this.height = h;
            if (w < 1) {
                this.width = 320;
            }
            if (h < 1) {
                this.height = 240;
            }
            this.sizeSet = true;
        }
    }

    public boolean start(OutputStream os) {
        boolean z = false;
        if (os == null) {
            return false;
        }
        this.closeStream = false;
        this.out = os;
        try {
            writeString("GIF89a");
            z = true;
        } catch (IOException unused) {
        }
        this.started = z;
        return z;
    }

    public boolean start(String file) {
        boolean z;
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            this.out = bufferedOutputStream;
            z = start(bufferedOutputStream);
            this.closeStream = true;
        } catch (IOException unused) {
            z = false;
        }
        this.started = z;
        return z;
    }

    protected void analyzePixels() {
        int length = this.pixels.length;
        int i = length / 3;
        this.indexedPixels = new byte[i];
        NeuQuant neuQuant = new NeuQuant(this.pixels, length, this.sample);
        this.colorTab = neuQuant.process();
        int i2 = 0;
        while (true) {
            byte[] bArr = this.colorTab;
            if (i2 >= bArr.length) {
                break;
            }
            byte b = bArr[i2];
            int i3 = i2 + 2;
            bArr[i2] = bArr[i3];
            bArr[i3] = b;
            this.usedEntry[i2 / 3] = false;
            i2 += 3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            byte[] bArr2 = this.pixels;
            int i6 = bArr2[i4] & UByte.MAX_VALUE;
            int i7 = i4 + 2;
            int i8 = bArr2[i4 + 1] & UByte.MAX_VALUE;
            i4 += 3;
            int map = neuQuant.map(i6, i8, bArr2[i7] & UByte.MAX_VALUE);
            this.usedEntry[map] = true;
            this.indexedPixels[i5] = (byte) map;
        }
        this.pixels = null;
        this.colorDepth = 8;
        this.palSize = 7;
        Color color = this.transparent;
        if (color != null) {
            this.transIndex = findClosest(color);
        }
    }

    protected int findClosest(Color c) {
        byte[] bArr = this.colorTab;
        if (bArr == null) {
            return -1;
        }
        int length = bArr.length;
        int i = 16777216;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            byte[] bArr2 = this.colorTab;
            int i4 = 0 - (bArr2[i3] & UByte.MAX_VALUE);
            int i5 = i3 + 2;
            int i6 = 0 - (bArr2[i3 + 1] & UByte.MAX_VALUE);
            int i7 = 0 - (bArr2[i5] & UByte.MAX_VALUE);
            int i8 = (i4 * i4) + (i6 * i6) + (i7 * i7);
            int i9 = i5 / 3;
            if (this.usedEntry[i9] && i8 < i) {
                i = i8;
                i2 = i9;
            }
        }
        return i2;
    }

    private byte[] getPixelBytes(int[] img) {
        byte[] bArr = new byte[img.length * 3];
        int i = 0;
        for (int i2 : img) {
            byte[] array = ByteBuffer.allocate(4).putInt(i2).array();
            bArr[i] = array[3];
            bArr[i + 1] = array[2];
            bArr[i + 2] = array[1];
            i += 3;
        }
        return bArr;
    }

    protected void getImagePixels() {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        int[] iArr = new int[width * height * 3];
        this.image.getPixels(iArr, 0, this.width, 0, 0, width, height);
        this.pixels = getPixelBytes(iArr);
    }

    protected void writeGraphicCtrlExt() throws IOException {
        int i;
        int i2;
        this.out.write(33);
        this.out.write(249);
        this.out.write(4);
        if (this.transparent == null) {
            i = 0;
            i2 = 0;
        } else {
            i = 1;
            i2 = 2;
        }
        int i3 = this.dispose;
        if (i3 >= 0) {
            i2 = i3 & 7;
        }
        this.out.write(i | (i2 << 2));
        writeShort(this.delay);
        this.out.write(this.transIndex);
        this.out.write(0);
    }

    protected void writeImageDesc() throws IOException {
        this.out.write(44);
        writeShort(0);
        writeShort(0);
        writeShort(this.width);
        writeShort(this.height);
        if (this.firstFrame) {
            this.out.write(0);
        } else {
            this.out.write(this.palSize | 128);
        }
    }

    protected void writeLSD() throws IOException {
        writeShort(this.width);
        writeShort(this.height);
        this.out.write(this.palSize | 240);
        this.out.write(0);
        this.out.write(0);
    }

    protected void writeNetscapeExt() throws IOException {
        this.out.write(33);
        this.out.write(255);
        this.out.write(11);
        writeString("NETSCAPE2.0");
        this.out.write(3);
        this.out.write(1);
        writeShort(this.repeat);
        this.out.write(0);
    }

    protected void writePalette() throws IOException {
        OutputStream outputStream = this.out;
        byte[] bArr = this.colorTab;
        outputStream.write(bArr, 0, bArr.length);
        int length = 768 - this.colorTab.length;
        for (int i = 0; i < length; i++) {
            this.out.write(0);
        }
    }

    protected void writePixels() throws IOException {
        new LzwEncoder(this.width, this.height, this.indexedPixels, this.colorDepth).encode(this.out);
    }

    protected void writeShort(int value) throws IOException {
        this.out.write(value & 255);
        this.out.write((value >> 8) & 255);
    }

    protected void writeString(String s) throws IOException {
        for (int i = 0; i < s.length(); i++) {
            this.out.write((byte) s.charAt(i));
        }
    }
}
