package com.wifiled.baselib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.YuvImage;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View;
import androidx.camera.video.AudioStats;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;

/* loaded from: classes2.dex */
public class BitmapUtil {
    public static Bitmap view2Bitmap(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        view.layout(0, 0, width, height);
        view.draw(canvas);
        return createBitmap;
    }

    public static Bitmap convertSurfaceViewToBitmap(SurfaceView surfaceView, int i, int i2) {
        surfaceView.buildDrawingCache();
        Bitmap createBitmap = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(), Bitmap.Config.ARGB_8888);
        surfaceView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Bitmap convertViewToBitmap(View view, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Bitmap createBitmap(Bitmap bitmap, Bitmap bitmap2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmap2, width - width2, height - height2, (Paint) null);
        canvas.save();
        canvas.restore();
        return createBitmap;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap rotate(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap createCircleBitmap(Bitmap bitmap, int i, boolean z, int i2, int i3) {
        int width = bitmap.getWidth() < bitmap.getHeight() ? bitmap.getWidth() : bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float f = width / 2;
        canvas.drawCircle(f, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        float f2 = i;
        canvas.drawBitmap(bitmap, f2, f2, paint);
        if (z) {
            if (i3 == 0) {
                i3 = -89528;
            }
            paint.setColor(i3);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(i2);
            canvas.drawCircle(f, f, f, paint);
        }
        return createBitmap;
    }

    public static Bitmap createCornerBitmap(Bitmap bitmap, int i, int i2, boolean z, int i3, int i4) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float f = i;
        float f2 = i2;
        canvas.drawRoundRect(rectF, f, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        if (z) {
            if (i4 == 0) {
                i4 = -89528;
            }
            paint.setColor(i4);
            paint.setColor(i4);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(i3);
            canvas.drawRoundRect(rectF, f, f2, paint);
        }
        return createBitmap;
    }

    public static Bitmap cropBitmap(Bitmap bitmap, float f, float f2) {
        float width = bitmap.getWidth();
        int i = (int) (width * f);
        float height = bitmap.getHeight();
        return Bitmap.createBitmap(bitmap, (int) ((width * (1.0f - f)) / 2.0f), (int) ((height * (1.0f - f2)) / 2.0f), i, (int) (height * f2), (Matrix) null, false);
    }

    public static Bitmap createReflectionBitmap(Bitmap bitmap, float f) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        float f2 = height;
        int i = (int) (f2 * f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, (int) ((1.0f - f) * f2), width, i, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height + i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(createBitmap, 0.0f, f2, (Paint) null);
        LinearGradient linearGradient = new LinearGradient(0.0f, bitmap.getHeight(), 0.0f, createBitmap2.getHeight(), 1895825407, 16777215, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, f2, width, createBitmap2.getHeight(), paint);
        return createBitmap2;
    }

    public static Bitmap compressBitmap(Bitmap bitmap, float f) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, ((int) f) * 100, byteArrayOutputStream);
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    public static Bitmap imageZoom(Bitmap bitmap, double d) {
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, new ByteArrayOutputStream());
        double length = (r0.toByteArray().length / 1024) / d;
        doRecycledIfNot(bitmap);
        if (length > 1.0d) {
            return scaleWithWH(bitmap, bitmap.getWidth() / Math.sqrt(length), bitmap.getHeight() / Math.sqrt(length));
        }
        return null;
    }

    public static Bitmap scaleWithWH(Bitmap bitmap, double d, double d2) {
        if (d == AudioStats.AUDIO_AMPLITUDE_NONE || d2 == AudioStats.AUDIO_AMPLITUDE_NONE || bitmap == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale((float) (d / width), (float) (d2 / height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap getBitmap(byte[] bArr, int i, int i2) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, yuvImage.getWidth(), yuvImage.getHeight()), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return (Bitmap) new SoftReference(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, new BitmapFactory.Options())).get();
    }

    public static Bitmap getBitmapResources(Context context, int i) {
        return BitmapFactory.decodeResource(context.getResources(), i);
    }

    public static Bitmap getBitmapPath(String str) {
        return BitmapFactory.decodeFile(str);
    }

    public static boolean saveFile(String str, Bitmap bitmap) {
        if (!TextUtils.isEmpty(str) && bitmap != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            } else {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void doRecycledIfNot(Bitmap bitmap) {
        if (bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002d, code lost:
    
        if (r0 == null) goto L20;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String imageToBase64(java.lang.String r3) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            int r3 = r0.available()     // Catch: java.lang.Exception -> L24 java.lang.Throwable -> L31
            byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> L24 java.lang.Throwable -> L31
            r0.read(r3)     // Catch: java.lang.Exception -> L24 java.lang.Throwable -> L31
            r2 = 0
            java.lang.String r1 = android.util.Base64.encodeToString(r3, r2)     // Catch: java.lang.Exception -> L24 java.lang.Throwable -> L31
        L1b:
            r0.close()     // Catch: java.io.IOException -> L1f
            goto L30
        L1f:
            r3 = move-exception
            r3.printStackTrace()
            goto L30
        L24:
            r3 = move-exception
            goto L2a
        L26:
            r3 = move-exception
            goto L33
        L28:
            r3 = move-exception
            r0 = r1
        L2a:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L31
            if (r0 == 0) goto L30
            goto L1b
        L30:
            return r1
        L31:
            r3 = move-exception
            r1 = r0
        L33:
            if (r1 == 0) goto L3d
            r1.close()     // Catch: java.io.IOException -> L39
            goto L3d
        L39:
            r0 = move-exception
            r0.printStackTrace()
        L3d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.baselib.utils.BitmapUtil.imageToBase64(java.lang.String):java.lang.String");
    }

    public static Bitmap createRGBImage(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        int i2 = 0;
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                int pixel = bitmap.getPixel(i4, i3);
                if (pixel == -131073) {
                    iArr[i2] = 0;
                } else {
                    iArr[i2] = pixel;
                }
                i2++;
            }
        }
        return Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap getBitmapFromFile(String str) {
        return BitmapFactory.decodeFile(str);
    }

    public static Bitmap getBitmapFromFile(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i3 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        float f = options.outWidth;
        float f2 = options.outHeight;
        float f3 = i2;
        if (f2 > f3 || f > i) {
            if (f > f2) {
                i3 = Math.round(f2 / f3);
            } else {
                i3 = Math.round(f / i);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = i3;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap getBitmapFromFD(String str, int i, int i2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i3 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
            float f = options.outWidth;
            float f2 = options.outHeight;
            float f3 = i2;
            if (f2 > f3 || f > i) {
                if (f > f2) {
                    i3 = Math.round(f2 / f3);
                } else {
                    i3 = Math.round(f / i);
                }
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = i3;
            return BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Bitmap getBitmapFromFD(String str) {
        try {
            return BitmapFactory.decodeFileDescriptor(new FileInputStream(str).getFD());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
