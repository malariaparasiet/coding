package com.wifiled.ipixels.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.core.view.ViewCompat;
import com.alibaba.fastjson2.JSONB;
import com.wifiled.ipixels.AppConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class BGRUtils {
    public static byte[] bitmap2BGR(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        byte[] bArr = new byte[i * 3];
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            int i4 = i2 * 3;
            bArr[i4] = (byte) (i3 & 255);
            bArr[i4 + 1] = (byte) ((i3 >> 8) & 255);
            bArr[i4 + 2] = (byte) ((i3 >> 16) & 255);
        }
        return bArr;
    }

    public static byte[] bitmapToByteStream(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
            return byteArray;
        } catch (IOException e) {
            e.printStackTrace();
            return byteArray;
        }
    }

    public static byte[] bitmap2RGB(Bitmap image) {
        if (image == null) {
            return null;
        }
        int width = image.getWidth();
        int height = image.getHeight();
        int i = width * height;
        final byte[] bArr = new byte[i * 3];
        final int[] iArr = new int[i];
        image.getPixels(iArr, 0, width, 0, 0, width, height);
        IntStream.range(0, i).parallel().forEach(new IntConsumer() { // from class: com.wifiled.ipixels.utils.BGRUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.IntConsumer
            public final void accept(int i2) {
                BGRUtils.lambda$bitmap2RGB$0(iArr, bArr, i2);
            }
        });
        return bArr;
    }

    static /* synthetic */ void lambda$bitmap2RGB$0(int[] iArr, byte[] bArr, int i) {
        int i2 = iArr[i];
        int i3 = i * 3;
        bArr[i3] = (byte) ((i2 >> 16) & 255);
        bArr[i3 + 1] = (byte) ((i2 >> 8) & 255);
        bArr[i3 + 2] = (byte) (i2 & 255);
    }

    public static Bitmap RGB2bitmap(byte[] rgb) {
        int intValue = AppConfig.INSTANCE.getLedSize().get(0).intValue();
        int intValue2 = AppConfig.INSTANCE.getLedSize().get(1).intValue();
        Bitmap createBitmap = Bitmap.createBitmap(intValue, intValue2, Bitmap.Config.ARGB_8888);
        int i = intValue * intValue2;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 3;
            iArr[i2] = (rgb[i3 + 2] & UByte.MAX_VALUE) | ((rgb[i3] & UByte.MAX_VALUE) << 16) | ViewCompat.MEASURED_STATE_MASK | ((rgb[i3 + 1] & UByte.MAX_VALUE) << 8);
        }
        createBitmap.setPixels(iArr, 0, AppConfig.INSTANCE.getLedSize().get(0).intValue(), 0, 0, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue());
        return createBitmap;
    }

    public static Bitmap RGB2bitmap(byte[] rgb, int width, int height) {
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int i = width * height;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 3;
            iArr[i2] = (rgb[i3 + 2] & UByte.MAX_VALUE) | ((rgb[i3] & UByte.MAX_VALUE) << 16) | ViewCompat.MEASURED_STATE_MASK | ((rgb[i3 + 1] & UByte.MAX_VALUE) << 8);
        }
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    public static int[] convertRGBToColor(byte[] data) {
        int length = data.length;
        if (length == 0) {
            return null;
        }
        int i = 0;
        int i2 = length % 3 != 0 ? 1 : 0;
        int i3 = (length / 3) + i2;
        int[] iArr = new int[i3];
        if (i2 == 0) {
            while (i < i3) {
                int i4 = i * 3;
                iArr[i] = (data[i4 + 2] & UByte.MAX_VALUE) | ((data[i4] << JSONB.Constants.BC_INT32_NUM_16) & 16711680) | ((data[i4 + 1] << 8) & 65280) | ViewCompat.MEASURED_STATE_MASK;
                i++;
            }
            return iArr;
        }
        while (true) {
            int i5 = i3 - 1;
            if (i >= i5) {
                iArr[i5] = -16777216;
                return iArr;
            }
            int i6 = i * 3;
            iArr[i] = (data[i6 + 2] & UByte.MAX_VALUE) | ((data[i6] << JSONB.Constants.BC_INT32_NUM_16) & 16711680) | ((data[i6 + 1] << 8) & 65280) | ViewCompat.MEASURED_STATE_MASK;
            i++;
        }
    }

    public static byte[] convertColorToBGR(int[] colors) {
        int length = colors.length;
        byte[] bArr = new byte[length * 3];
        for (int i = 0; i < length; i++) {
            int i2 = colors[i];
            int i3 = i * 3;
            bArr[i3] = (byte) Color.blue(i2);
            bArr[i3 + 1] = (byte) Color.green(i2);
            bArr[i3 + 2] = (byte) Color.red(i2);
        }
        return bArr;
    }

    public static byte[] convertColorToRGB(int[] colors) {
        int length = colors.length;
        byte[] bArr = new byte[length * 3];
        for (int i = 0; i < length; i++) {
            int i2 = colors[i];
            int i3 = i * 3;
            bArr[i3] = (byte) ((i2 >> 16) & 255);
            bArr[i3 + 1] = (byte) ((i2 >> 8) & 255);
            bArr[i3 + 2] = (byte) (i2 & 255);
        }
        return bArr;
    }
}
