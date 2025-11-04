package com.wifiled.ipixels.gif;

import android.graphics.Bitmap;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class GIFEncoder {
    protected byte[] colorTab;
    protected int height;
    protected byte[] indexedPixels;
    protected OutputStream out;
    protected byte[] pixels;
    protected int width;
    protected int delay = 0;
    protected boolean[] usedEntry = new boolean[256];

    public void init(Bitmap firstBitmap) {
        this.width = firstBitmap.getWidth();
        this.height = firstBitmap.getHeight();
        getImagePixels(firstBitmap);
        analyzePixels();
    }

    public boolean start(OutputStream os) {
        if (os == null) {
            return false;
        }
        this.out = os;
        try {
            writeHeader();
            writeLSD();
            writePalette();
            writeGraphicCtrlExt();
            writeImageDesc();
            writePalette();
            writePixels();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean start(String file) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            this.out = bufferedOutputStream;
            return start(bufferedOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFrame(Bitmap bitmap) {
        try {
            getImagePixels(bitmap);
            analyzePixels();
            writeGraphicCtrlExt();
            writeImageDesc();
            writePalette();
            writePixels();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean finish() {
        try {
            this.out.write(59);
            this.out.flush();
            this.out.close();
            this.out = null;
            this.pixels = null;
            this.indexedPixels = null;
            this.colorTab = null;
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public void setFrameRate(float fps) {
        if (fps != 0.0f) {
            this.delay = Math.round(100.0f / fps);
        }
    }

    protected void analyzePixels() {
        int length = this.pixels.length;
        int i = length / 3;
        this.indexedPixels = new byte[i];
        NeuQuant neuQuant = new NeuQuant(this.pixels, length, 10);
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
    }

    protected void getImagePixels(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.pixels = new byte[width * height * 3];
        for (int i = 0; i < height; i++) {
            int i2 = width * 3 * i;
            for (int i3 = 0; i3 < width; i3++) {
                int pixel = bitmap.getPixel(i3, i);
                int i4 = (i3 * 3) + i2;
                byte[] bArr = this.pixels;
                bArr[i4] = (byte) (pixel & 255);
                bArr[i4 + 1] = (byte) ((65280 & pixel) >> 8);
                bArr[i4 + 2] = (byte) ((pixel & 16711680) >> 16);
            }
        }
    }

    protected void writeHeader() throws IOException {
        writeString("GIF89a");
    }

    protected void writeGraphicCtrlExt() throws IOException {
        this.out.write(33);
        this.out.write(249);
        this.out.write(4);
        this.out.write(0);
        writeShort(this.delay);
        this.out.write(0);
        this.out.write(0);
    }

    protected void writeImageDesc() throws IOException {
        this.out.write(44);
        writeShort(0);
        writeShort(0);
        writeShort(this.width);
        writeShort(this.height);
        this.out.write(Opcodes.I2D);
    }

    protected void writeLSD() throws IOException {
        writeShort(this.width);
        writeShort(this.height);
        this.out.write(247);
        this.out.write(0);
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
        new LZWEncoder(this.width, this.height, this.indexedPixels, 8).encode(this.out);
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
