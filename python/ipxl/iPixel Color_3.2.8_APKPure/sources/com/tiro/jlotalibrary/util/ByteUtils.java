package com.tiro.jlotalibrary.util;

import androidx.core.view.InputDeviceCompat;
import com.alibaba.fastjson2.JSONB;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

/* loaded from: classes2.dex */
public class ByteUtils {
    public static byte[] toByteArray(InputStream inputStream) {
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

    public static byte[] int2Bytes(int i) {
        return new byte[]{(byte) (i / 256), (byte) (i % 256)};
    }

    public static short bytes2Short2(byte[] bArr) {
        return (short) ((bArr[0] & UByte.MAX_VALUE) | ((bArr[1] & UByte.MAX_VALUE) << 8));
    }

    public static String BinaryToHexString(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return "bytes is null or lenth < 0";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.valueOf("0123456789ABCDEF".charAt((b & JSONB.Constants.BC_INT32_NUM_MIN) >> 4)) + String.valueOf("0123456789ABCDEF".charAt(b & 15))).append(" ");
        }
        return sb.toString();
    }

    public static String BinaryToHexString1(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append("0x").append(String.valueOf("0123456789ABCDEF".charAt((b & JSONB.Constants.BC_INT32_NUM_MIN) >> 4)) + String.valueOf("0123456789ABCDEF".charAt(b & 15))).append(",");
        }
        return sb.toString();
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

    public static String byteArray2ASCIIStr(byte[] bArr) {
        if (bArr == null) {
            return "byteBuf is null";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append((char) b);
        }
        return stringBuffer.toString();
    }

    public static String byteArrayToHexStr(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            cArr[i2] = charArray[(b & UByte.MAX_VALUE) >>> 4];
            cArr[i2 + 1] = charArray[b & 15];
        }
        return new String(cArr);
    }

    public static byte[] hexStrToByteArray(String str) {
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

    public static byte getByteByArray(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bArr == null) {
            return (byte) 0;
        }
        for (int length = bArr.length - 1; length >= 0; length--) {
            stringBuffer.append(((int) bArr[length]) + "");
        }
        return bitToByte(stringBuffer.toString());
    }

    public static byte bitToByte(String str) {
        int parseInt;
        if (str == null) {
            return (byte) 0;
        }
        int length = str.length();
        if (length != 4 && length != 8) {
            return (byte) 0;
        }
        if (length == 8) {
            if (str.charAt(0) == '0') {
                parseInt = Integer.parseInt(str, 2);
            } else {
                parseInt = Integer.parseInt(str, 2) + InputDeviceCompat.SOURCE_ANY;
            }
        } else {
            parseInt = Integer.parseInt(str, 2);
        }
        return (byte) parseInt;
    }
}
