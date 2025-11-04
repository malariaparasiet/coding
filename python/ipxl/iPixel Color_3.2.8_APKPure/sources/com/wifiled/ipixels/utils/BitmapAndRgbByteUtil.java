package com.wifiled.ipixels.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import androidx.core.view.ViewCompat;
import com.wifiled.baselib.utils.LogUtils;

/* loaded from: classes3.dex */
public class BitmapAndRgbByteUtil {
    public static final int RGB_DATA_WIDTH_OR_HEIGHT = 64;
    private static final String TAG = "BitmapAndRgbByteUtil";
    private static int mOriginHeight;
    private static int mOriginWidth;

    private static int convertByteToInt(byte data) {
        return (((data >> 4) & 15) * 16) + (data & 15);
    }

    public static Bitmap rgb2BitmapFor123(byte[] data, int width, int height) {
        System.currentTimeMillis();
        try {
            int[] convertByteToColor123 = convertByteToColor123(data);
            if (convertByteToColor123 == null) {
                return null;
            }
            return Bitmap.createBitmap(convertByteToColor123, 0, width, width, height, Bitmap.Config.ARGB_8888);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Bitmap rgb2BitmapFor323(byte[] data, int width, int height) {
        System.currentTimeMillis();
        try {
            int[] convertByteToColor323 = convertByteToColor323(data);
            if (convertByteToColor323 == null) {
                return null;
            }
            return Bitmap.createBitmap(convertByteToColor323, 0, width, width, height, Bitmap.Config.ARGB_8888);
        } catch (Exception unused) {
            return null;
        }
    }

    private static int[] convertByteToColor123(byte[] data) {
        int length = data != null ? data.length : -1;
        if (length == 0) {
            return null;
        }
        int i = 0;
        int i2 = length % 3 != 0 ? 1 : 0;
        int i3 = length + i2;
        int[] iArr = new int[i3];
        if (i2 == 0) {
            while (i < i3) {
                iArr[i] = (convertByteToInt(data[i]) << 16) | (convertByteToInt(data[i]) << 8) | convertByteToInt(data[i]) | ViewCompat.MEASURED_STATE_MASK;
                i++;
            }
            return iArr;
        }
        while (true) {
            int i4 = i3 - 1;
            if (i < i4) {
                iArr[i] = (convertByteToInt(data[i]) << 16) | (convertByteToInt(data[i]) << 8) | convertByteToInt(data[i]) | ViewCompat.MEASURED_STATE_MASK;
                i++;
            } else {
                iArr[i4] = -16777216;
                return iArr;
            }
        }
    }

    public static int[] convertByteToColor323(byte[] data) {
        int length = data != null ? data.length : -1;
        if (length == 0) {
            return null;
        }
        int i = 0;
        int i2 = length % 3 != 0 ? 1 : 0;
        int i3 = (length / 3) + i2;
        int[] iArr = new int[i3];
        if (i2 == 0) {
            for (int i4 = 0; i4 < i3; i4++) {
                LogUtils.logi("BitmapAndRgbByteUtil>>>[convertByteToColor323]: ", new Object[0]);
                int i5 = i4 * 3;
                iArr[i4] = (convertByteToInt(data[i5 + 2]) << 16) | (convertByteToInt(data[i5 + 1]) << 8) | convertByteToInt(data[i5]) | ViewCompat.MEASURED_STATE_MASK;
            }
            return iArr;
        }
        while (true) {
            int i6 = i3 - 1;
            if (i < i6) {
                int i7 = i * 3;
                iArr[i] = convertByteToInt(data[i7 + 2]) | (convertByteToInt(data[i7]) << 16) | (convertByteToInt(data[i7 + 1]) << 8) | ViewCompat.MEASURED_STATE_MASK;
                i++;
            } else {
                iArr[i6] = -16777216;
                return iArr;
            }
        }
    }

    public static byte[] bitmap2RGBData(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[i * 3];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            int i4 = i2 * 3;
            bArr[i4] = (byte) ((i3 >> 16) & 255);
            bArr[i4 + 1] = (byte) ((i3 >> 8) & 255);
            bArr[i4 + 2] = (byte) (i3 & 255);
        }
        return bArr;
    }

    public static Bitmap resizeTo64(Bitmap bm) {
        mOriginWidth = bm.getWidth();
        int height = bm.getHeight();
        mOriginHeight = height;
        float f = 64;
        Matrix matrix = new Matrix();
        matrix.postScale(f / mOriginWidth, f / height);
        return Bitmap.createBitmap(bm, 0, 0, mOriginWidth, mOriginHeight, matrix, true);
    }

    public static Bitmap resize64ToOrigin(Bitmap bm) {
        int i = mOriginWidth;
        Matrix matrix = new Matrix();
        matrix.postScale(i / 64.0f, mOriginHeight / 64.0f);
        return Bitmap.createBitmap(bm, 0, 0, 64, 64, matrix, true);
    }
}
