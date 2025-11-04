package com.wifiled.ipixels.utils;

import com.alibaba.fastjson2.JSONB;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class ByteUtils {
    private static final String TAG = "ByteUtils";

    public static int getHeight4(byte data) {
        return (data & JSONB.Constants.BC_INT32_NUM_MIN) >> 4;
    }

    public static int getLow4(byte data) {
        return data & 15;
    }

    public static byte[] toBytes(short[] src) {
        int length = src.length;
        byte[] bArr = new byte[length << 1];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            short s = src[i];
            bArr[i2] = (byte) s;
            bArr[i2 + 1] = (byte) (s >> 8);
        }
        return bArr;
    }

    public static String binaryToHexString(byte[] bytes) {
        return "";
    }

    public static byte[] toBytes(float f) {
        int floatToIntBits = Float.floatToIntBits(f);
        byte[] bArr = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr[i] = (byte) (floatToIntBits >> (24 - (i * 8)));
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        for (int i2 = 0; i2 < 2; i2++) {
            byte b = bArr2[i2];
            int i3 = 3 - i2;
            bArr2[i2] = bArr2[i3];
            bArr2[i3] = b;
        }
        return bArr2;
    }

    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bArr = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bArr, 0, bt1.length);
        System.arraycopy(bt2, 0, bArr, bt1.length, bt2.length);
        return bArr;
    }

    public static byte[] toBytes(short src) {
        return new byte[]{(byte) src, (byte) (src >> 8)};
    }

    public static byte[] toBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static byte[] toBytes(String str) {
        return str.getBytes();
    }

    public static byte[] toBytes(long number) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putLong(0, number);
        return allocate.array();
    }

    public static int toInt(byte[] src, int offset) {
        return ((src[offset + 3] & UByte.MAX_VALUE) << 24) | (src[offset] & UByte.MAX_VALUE) | ((src[offset + 1] & UByte.MAX_VALUE) << 8) | ((src[offset + 2] & UByte.MAX_VALUE) << 16);
    }

    public static int toInt(byte[] src) {
        return toInt(src, 0);
    }

    public static long toLong(byte[] b) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.put(b, 0, b.length);
        return allocate.getLong();
    }

    public static short[] toShorts(byte[] src) {
        int length = src.length >> 1;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            sArr[i] = (short) (((src[i2 + 1] & UByte.MAX_VALUE) << 8) | (src[i2] & UByte.MAX_VALUE));
        }
        return sArr;
    }

    public static byte[] merger(byte[] bt1, byte[] bt2) {
        byte[] bArr = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bArr, 0, bt1.length);
        System.arraycopy(bt2, 0, bArr, bt1.length, bt2.length);
        return bArr;
    }

    public static String toString(byte[] b) {
        return Arrays.toString(b);
    }

    public static void byte2File(byte[] buf, File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                randomAccessFile.seek(file.length());
                randomAccessFile.write(buf);
                randomAccessFile.close();
            } finally {
            }
        } catch (Exception unused) {
        }
    }
}
