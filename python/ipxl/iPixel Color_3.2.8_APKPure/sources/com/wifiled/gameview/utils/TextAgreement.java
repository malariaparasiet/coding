package com.wifiled.gameview.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class TextAgreement {
    private static ArrayList<String> arrListGradientColor = new ArrayList<>();

    public static boolean isChinese(char c) {
        return c >= 19968 && c <= 40869;
    }

    public static byte[] bitmap2GradientBGR(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        bitmap.getHeight();
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        byte[] array = allocate.array();
        byte[] bArr = new byte[(array.length / 4) * 3];
        int i2 = -1;
        for (int i3 = 0; i3 < array.length / 4; i3++) {
            int i4 = i3 % width;
            int i5 = i3 / width;
            if (i4 == 0) {
                i2 = Color.parseColor(arrListGradientColor.get(i5));
            }
            if (bitmap.getPixel(i4, i5) == -1) {
                int i6 = i3 * 3;
                bArr[i6] = (byte) Color.blue(i2);
                bArr[i6 + 1] = (byte) Color.green(i2);
                bArr[i6 + 2] = (byte) Color.red(i2);
            }
        }
        return bArr;
    }

    public static Bitmap getCharBitmap(char c, int i, int i2, int i3) {
        boolean isChinese = isChinese(c);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        Paint paint = new Paint();
        paint.setTextSize(isChinese ? i2 : (float) (i * 1.3d));
        paint.setColor(i3);
        paint.setTypeface(FontUtils.getTypeface());
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(c), ((i / 2.0f) - 1.0f) + 1.0f, (float) ((i2 * 5.2d) / 6.0d), paint);
        return createBitmap;
    }

    public static byte[] getTextData(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight() * width;
        byte[] bArr = new byte[height];
        for (int i = 0; i < height; i++) {
            if (bitmap.getPixel(i % width, i / width) != 0) {
                bArr[i] = 1;
            }
        }
        int i2 = height / 8;
        byte[] bArr2 = new byte[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = 0;
            byte b = 0;
            while (i5 < 8) {
                b = (byte) ((bArr[i3] << i5) | b);
                i5++;
                i3++;
            }
            bArr2[i4] = b;
        }
        return bArr2;
    }
}
