package com.wifiled.blelibrary.ble.utils;

import androidx.core.view.ViewCompat;
import com.alibaba.fastjson2.JSONB;
import com.bumptech.glide.load.Key;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes2.dex */
public class ByteUtils {
    public static byte[] stream2Bytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static short[] toShorts(byte[] bArr) {
        int length = bArr.length >> 1;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            sArr[i] = (short) (((bArr[i2 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i2] & UByte.MAX_VALUE));
        }
        return sArr;
    }

    public static short bytes2Short2(byte[] bArr) {
        return (short) ((bArr[0] & UByte.MAX_VALUE) | ((bArr[1] & UByte.MAX_VALUE) << 8));
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        int length = bArr.length - 1;
        if (length == -1) {
            return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        while (true) {
            sb.append(String.format("%02x", Integer.valueOf(bArr[i] & UByte.MAX_VALUE)));
            if (i == length) {
                return sb.append(']').toString();
            }
            sb.append(", ");
            i++;
        }
    }

    public static String bytes2HexStr(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format("%02x", Integer.valueOf(b & UByte.MAX_VALUE)));
        }
        return sb.toString();
    }

    public static byte[] hexStr2Bytes(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static byte[] short2Bytes(short s) {
        return new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)};
    }

    public static byte[] int2byte(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
    }

    public static int byte2int(byte[] bArr) {
        return (bArr[3] << 24) | (bArr[0] & UByte.MAX_VALUE) | ((bArr[1] << 8) & 65280) | ((bArr[2] << 24) >>> 8);
    }

    public static byte[] getBytes(boolean z) {
        return new byte[]{z ? (byte) 1 : (byte) 0};
    }

    public static byte[] getBytes(short s) {
        byte[] bArr = new byte[2];
        if (isLittleEndian()) {
            bArr[0] = (byte) (s & 255);
            bArr[1] = (byte) ((s & 65280) >> 8);
            return bArr;
        }
        bArr[1] = (byte) (s & 255);
        bArr[0] = (byte) ((s & 65280) >> 8);
        return bArr;
    }

    public static byte[] getBytes(char c) {
        byte[] bArr = new byte[2];
        if (isLittleEndian()) {
            bArr[0] = (byte) c;
            bArr[1] = (byte) (c >> '\b');
            return bArr;
        }
        bArr[1] = (byte) c;
        bArr[0] = (byte) (c >> '\b');
        return bArr;
    }

    public static byte[] getBytes(int i) {
        byte[] bArr = new byte[4];
        if (isLittleEndian()) {
            bArr[0] = (byte) (i & 255);
            bArr[1] = (byte) ((i & 65280) >> 8);
            bArr[2] = (byte) ((i & 16711680) >> 16);
            bArr[3] = (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24);
            return bArr;
        }
        bArr[3] = (byte) (i & 255);
        bArr[2] = (byte) ((i & 65280) >> 8);
        bArr[1] = (byte) ((i & 16711680) >> 16);
        bArr[0] = (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24);
        return bArr;
    }

    public static byte[] getBytes(long j) {
        byte[] bArr = new byte[8];
        if (isLittleEndian()) {
            bArr[0] = (byte) (j & 255);
            bArr[1] = (byte) ((j >> 8) & 255);
            bArr[2] = (byte) ((j >> 16) & 255);
            bArr[3] = (byte) ((j >> 24) & 255);
            bArr[4] = (byte) ((j >> 32) & 255);
            bArr[5] = (byte) ((j >> 40) & 255);
            bArr[6] = (byte) ((j >> 48) & 255);
            bArr[7] = (byte) ((j >> 56) & 255);
            return bArr;
        }
        bArr[7] = (byte) (j & 255);
        bArr[6] = (byte) ((j >> 8) & 255);
        bArr[5] = (byte) ((j >> 16) & 255);
        bArr[4] = (byte) ((j >> 24) & 255);
        bArr[3] = (byte) ((j >> 32) & 255);
        bArr[2] = (byte) ((j >> 40) & 255);
        bArr[1] = (byte) ((j >> 48) & 255);
        bArr[0] = (byte) ((j >> 56) & 255);
        return bArr;
    }

    public static byte[] getBytes(float f) {
        return getBytes(Float.floatToIntBits(f));
    }

    public static byte[] getBytes(double d) {
        return getBytes(Double.doubleToLongBits(d));
    }

    public static byte[] getBytes(String str) {
        return str.getBytes(Charset.forName(Key.STRING_CHARSET_NAME));
    }

    public static byte[] getBytes(String str, String str2) {
        return str.getBytes(Charset.forName(str2));
    }

    public static boolean toBoolean(byte[] bArr) {
        return bArr[0] != 0;
    }

    public static boolean toBoolean(byte[] bArr, int i) {
        return toBoolean(copyFrom(bArr, i, 1));
    }

    public static short toShort(byte[] bArr) {
        int i;
        byte b;
        if (isLittleEndian()) {
            i = bArr[0] & UByte.MAX_VALUE;
            b = bArr[1];
        } else {
            i = bArr[1] & UByte.MAX_VALUE;
            b = bArr[0];
        }
        return (short) (((b << 8) & 65280) | i);
    }

    public static short toShort(byte[] bArr, int i) {
        return toShort(copyFrom(bArr, i, 2));
    }

    public static char toChar(byte[] bArr) {
        int i;
        byte b;
        if (isLittleEndian()) {
            i = bArr[0] & UByte.MAX_VALUE;
            b = bArr[1];
        } else {
            i = bArr[1] & UByte.MAX_VALUE;
            b = bArr[0];
        }
        return (char) (((b << 8) & 65280) | i);
    }

    public static char toChar(byte[] bArr, int i) {
        return toChar(copyFrom(bArr, i, 2));
    }

    public static int toInt(byte[] bArr) {
        int i;
        byte b;
        if (isLittleEndian()) {
            i = (bArr[0] & UByte.MAX_VALUE) | ((bArr[1] << 8) & 65280) | ((bArr[2] << JSONB.Constants.BC_INT32_NUM_16) & 16711680);
            b = bArr[3];
        } else {
            i = (bArr[3] & UByte.MAX_VALUE) | ((bArr[2] << 8) & 65280) | ((bArr[1] << JSONB.Constants.BC_INT32_NUM_16) & 16711680);
            b = bArr[0];
        }
        return ((b << 24) & ViewCompat.MEASURED_STATE_MASK) | i;
    }

    public static int toInt(byte[] bArr, int i) {
        return toInt(copyFrom(bArr, i, 4));
    }

    public static long toLong(byte[] bArr) {
        long j;
        long j2;
        byte b;
        if (isLittleEndian()) {
            j = -72057594037927936L;
            j2 = (bArr[0] & 255) | ((bArr[1] << 8) & 65280) | ((bArr[2] << 16) & 16711680) | ((bArr[3] << 24) & 4278190080L) | ((bArr[4] << 32) & 1095216660480L) | ((bArr[5] << 40) & 280375465082880L) | ((bArr[6] << 48) & 71776119061217280L);
            b = bArr[7];
        } else {
            j = -72057594037927936L;
            j2 = (bArr[7] & 255) | ((bArr[6] << 8) & 65280) | ((bArr[5] << 16) & 16711680) | ((bArr[4] << 24) & 4278190080L) | ((bArr[3] << 32) & 1095216660480L) | ((bArr[2] << 40) & 280375465082880L) | ((bArr[1] << 48) & 71776119061217280L);
            b = bArr[0];
        }
        return j2 | ((b << 56) & j);
    }

    public static long toLong(byte[] bArr, int i) {
        return toLong(copyFrom(bArr, i, 8));
    }

    public static float toFloat(byte[] bArr) {
        return Float.intBitsToFloat(toInt(bArr));
    }

    public static float toFloat(byte[] bArr, int i) {
        return Float.intBitsToFloat(toInt(copyFrom(bArr, i, 4)));
    }

    public static double toDouble(byte[] bArr) {
        return Double.longBitsToDouble(toLong(bArr));
    }

    public static double toDouble(byte[] bArr, int i) {
        return Double.longBitsToDouble(toLong(copyFrom(bArr, i, 8)));
    }

    public static String toString(byte[] bArr) {
        return new String(bArr, Charset.forName(Key.STRING_CHARSET_NAME));
    }

    public static String toString(byte[] bArr, String str) {
        return new String(bArr, Charset.forName(str));
    }

    private static byte[] copyFrom(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i < bArr.length && i3 < i2; i3++) {
            bArr2[i3] = bArr[i];
            i++;
        }
        return bArr2;
    }

    private static byte[] checkRetByteArray(byte[] bArr) {
        if (bArr == null || bArr.length < 5) {
            return null;
        }
        return copyFrom(bArr, 0, (bArr[1] * 256) + bArr[0]);
    }

    private static boolean isLittleEndian() {
        return ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
    }

    public static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + bArr2.length);
        System.arraycopy(bArr2, 0, copyOf, bArr.length, bArr2.length);
        return copyOf;
    }
}
