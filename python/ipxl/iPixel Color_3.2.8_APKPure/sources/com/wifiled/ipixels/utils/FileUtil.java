package com.wifiled.ipixels.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.baselib.utils.FileUtils;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.DataType;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;

/* loaded from: classes3.dex */
public class FileUtil {
    public static String getPath(int dataType, String suffix) {
        String str;
        String str2;
        if (dataType == 0) {
            str = "Text";
        } else if (dataType == 1) {
            str = "Image";
        } else if (dataType == 2) {
            str = "Video";
        } else if (dataType != 3) {
            str = "";
        } else {
            str = "Gif";
        }
        if (AppConfig.INSTANCE.getLedType() == 2) {
            str2 = "32";
        } else if (AppConfig.INSTANCE.getLedType() == 3) {
            str2 = "16";
        } else if (AppConfig.INSTANCE.getLedType() == 5) {
            str2 = "20";
        } else if (AppConfig.INSTANCE.getLedType() == 4) {
            str2 = "12";
        } else if (AppConfig.INSTANCE.getLedType() == 1) {
            str2 = "96";
        } else if (AppConfig.INSTANCE.getLedType() == 6) {
            str2 = "128";
        } else if (AppConfig.INSTANCE.getLedType() == 7) {
            str2 = "144";
        } else if (AppConfig.INSTANCE.getLedType() == 8) {
            str2 = "192";
        } else if (AppConfig.INSTANCE.getLedType() == 9) {
            str2 = "2448";
        } else if (AppConfig.INSTANCE.getLedType() == 10) {
            str2 = "3264";
        } else if (AppConfig.INSTANCE.getLedType() == 11) {
            str2 = "3296";
        } else if (AppConfig.INSTANCE.getLedType() == 13) {
            str2 = "3296_2";
        } else if (AppConfig.INSTANCE.getLedType() == 12) {
            str2 = "128_2";
        } else if (AppConfig.INSTANCE.getLedType() == 15) {
            str2 = "32192";
        } else if (AppConfig.INSTANCE.getLedType() == 14) {
            str2 = "32160";
        } else if (AppConfig.INSTANCE.getLedType() != 16) {
            str2 = "64";
        } else {
            str2 = "32256";
        }
        return new File(FileUtils.getExternalFilePath(App.context, str + File.separator + str2), System.currentTimeMillis() + suffix).getAbsolutePath();
    }

    public static String getPath2(int dataType, String suffix) {
        String str;
        String str2;
        if (dataType == 0) {
            str = "Text";
        } else if (dataType == 1) {
            str = "Image";
        } else if (dataType == 2) {
            str = "Video";
        } else if (dataType != 3) {
            str = "";
        } else {
            str = "Gif";
        }
        if (AppConfig.INSTANCE.getLedType() == 2) {
            str2 = "32";
        } else if (AppConfig.INSTANCE.getLedType() == 3) {
            str2 = "16";
        } else if (AppConfig.INSTANCE.getLedType() == 5) {
            str2 = "20";
        } else if (AppConfig.INSTANCE.getLedType() == 4) {
            str2 = "12";
        } else if (AppConfig.INSTANCE.getLedType() == 1) {
            str2 = "96";
        } else if (AppConfig.INSTANCE.getLedType() == 6) {
            str2 = "128";
        } else if (AppConfig.INSTANCE.getLedType() == 7) {
            str2 = "144";
        } else if (AppConfig.INSTANCE.getLedType() == 8) {
            str2 = "192";
        } else if (AppConfig.INSTANCE.getLedType() == 9) {
            str2 = "2448";
        } else if (AppConfig.INSTANCE.getLedType() == 10) {
            str2 = "3264";
        } else if (AppConfig.INSTANCE.getLedType() == 11) {
            str2 = "3296";
        } else if (AppConfig.INSTANCE.getLedType() == 13) {
            str2 = "3296_2";
        } else if (AppConfig.INSTANCE.getLedType() == 12) {
            str2 = "128_2";
        } else if (AppConfig.INSTANCE.getLedType() == 15) {
            str2 = "32192";
        } else if (AppConfig.INSTANCE.getLedType() == 14) {
            str2 = "32160";
        } else if (AppConfig.INSTANCE.getLedType() != 16) {
            str2 = "64";
        } else {
            str2 = "32256";
        }
        return new File(FileUtils.getExternalFilePath(App.context, str + File.separator + str2), "bitmap" + suffix + ".gif").getAbsolutePath();
    }

    public static void bitmap2File(File file, Bitmap bitmap) {
        if (bitmap == null) {
            LogUtils.logi("FileUtil >>>[bitmap2File]: bitmap is null", new Object[0]);
            return;
        }
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String turn24BitPng(String path) {
        File file = new File(path);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        decodeFile.getWidth();
        decodeFile.getHeight();
        int[] convertByteToColor323 = BitmapAndRgbByteUtil.convertByteToColor323(BGRUtils.bitmap2BGR(decodeFile));
        Bitmap createBitmap = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(IntBuffer.wrap(convertByteToColor323));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String str = ContextHolder.getContext().getFilesDir().getAbsolutePath() + File.separator + "image_test.jpg";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return str;
        } catch (IOException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static byte[] bmp2RGB(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        return rgb2YCbCr420(iArr, width, height);
    }

    public static byte[] rgb2YCbCr420(int[] pixels, int width, int height) {
        byte[] bArr = new byte[width * height * 3];
        int i = -1;
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                LogUtils.loge("RGB>>>***********", new Object[0]);
                int i4 = pixels[(i2 * width) + i3];
                int i5 = 16777215 & i4;
                int i6 = i4 & 255;
                int i7 = (i5 >> 8) & 255;
                int i8 = (i5 >> 16) & 255;
                LogUtils.loge("RGB>>>" + i6 + ">>>" + i7 + ">>>" + i8, new Object[0]);
                bArr[i + 1] = (byte) i6;
                bArr[i + 2] = (byte) i7;
                i += 3;
                bArr[i] = (byte) i8;
            }
        }
        return bArr;
    }

    public static String bgr2File(byte[] bgr, int dataType) {
        if (bgr == null || bgr.length == 0) {
            return "";
        }
        File file = new File(FileUtils.getExternalFilePath(App.context, "BGR"), DataType.getPrefixByType(dataType));
        if (!file.exists()) {
            file.mkdir();
        }
        String absolutePath = new File(file, "bgr_" + System.currentTimeMillis() + ".bgr").getAbsolutePath();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bgr);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = byteArrayInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayInputStream.close();
                    fileOutputStream.close();
                    return absolutePath;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return absolutePath;
        }
    }

    public static byte[] readFileBytes(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String compress(String path, int inSampleSize) {
        File file = new File(path);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String str = ContextHolder.getContext().getFilesDir().getAbsolutePath() + File.separator + "image_test.jpg";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return str;
        } catch (IOException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static String compress(byte[] arrData) {
        Bitmap copy = BitmapFactory.decodeByteArray(arrData, 0, arrData.length).copy(Bitmap.Config.ARGB_8888, true);
        if (copy == null) {
            return "";
        }
        for (int i = 0; i < copy.getWidth(); i++) {
            for (int i2 = 0; i2 < copy.getHeight(); i2++) {
                copy.getPixel(i, i2);
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String str = ContextHolder.getContext().getFilesDir().getAbsolutePath() + File.separator + "image_test.jpg";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return str;
        } catch (IOException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static byte[] drawableImag2Bytes(int id) {
        Bitmap bitmap = ((BitmapDrawable) ContextHolder.getContext().getResources().getDrawable(id)).getBitmap();
        String str = ContextHolder.getContext().getFilesDir().getAbsolutePath() + File.separator + "image_test.jpg";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception unused) {
        }
        return readFileBytes(str);
    }

    public static byte[] Png2Bytes(Bitmap bitmap) {
        String str = ContextHolder.getContext().getFilesDir().getAbsolutePath() + File.separator + "image_test.png";
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(copy);
        canvas.drawColor(-1);
        canvas.drawBitmap(copy, 0.0f, 0.0f, (Paint) null);
        new File(str);
        String str2 = ContextHolder.getContext().getFilesDir().getAbsolutePath() + File.separator + "image_test.jpg";
        BitmapFactory.decodeFile(str);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            try {
                if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream)) {
                    bufferedOutputStream.flush();
                }
                bufferedOutputStream.close();
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readFileBytes(str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0031, code lost:
    
        if (r6 != 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0033, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0064, code lost:
    
        if (r6 == 0) goto L43;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005c A[Catch: IOException -> 0x002c, TRY_ENTER, TryCatch #9 {IOException -> 0x002c, blocks: (B:19:0x0028, B:20:0x002e, B:22:0x0033, B:30:0x005c, B:32:0x0061), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0061 A[Catch: IOException -> 0x002c, TRY_LEAVE, TryCatch #9 {IOException -> 0x002c, blocks: (B:19:0x0028, B:20:0x002e, B:22:0x0033, B:30:0x005c, B:32:0x0061), top: B:2:0x0001 }] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r6v11, types: [android.content.res.AssetFileDescriptor] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [android.content.res.AssetFileDescriptor] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [android.content.res.AssetFileDescriptor] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.FileInputStream, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] assetFileDescriptorToByteArray(android.content.Context r6, java.lang.String r7) {
        /*
            r0 = 0
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L53
            android.content.res.AssetFileDescriptor r6 = r6.openFd(r7)     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L53
            java.io.FileInputStream r7 = r6.createInputStream()     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L42
            r1.<init>()     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L42
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L68
        L16:
            int r3 = r7.read(r2)     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L68
            r4 = -1
            if (r3 == r4) goto L22
            r4 = 0
            r1.write(r2, r4, r3)     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L68
            goto L16
        L22:
            byte[] r0 = r1.toByteArray()     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L68
            if (r7 == 0) goto L2e
            r7.close()     // Catch: java.io.IOException -> L2c
            goto L2e
        L2c:
            r6 = move-exception
            goto L37
        L2e:
            r1.close()     // Catch: java.io.IOException -> L2c
            if (r6 == 0) goto L67
        L33:
            r6.close()     // Catch: java.io.IOException -> L2c
            goto L67
        L37:
            r6.printStackTrace()
            goto L67
        L3b:
            r2 = move-exception
            goto L57
        L3d:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L69
        L42:
            r2 = move-exception
            r1 = r0
            goto L57
        L45:
            r7 = move-exception
            r1 = r0
            r0 = r7
            r7 = r1
            goto L69
        L4a:
            r2 = move-exception
            r7 = r0
            goto L56
        L4d:
            r6 = move-exception
            r7 = r0
            r1 = r7
            r0 = r6
            r6 = r1
            goto L69
        L53:
            r2 = move-exception
            r6 = r0
            r7 = r6
        L56:
            r1 = r7
        L57:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L68
            if (r7 == 0) goto L5f
            r7.close()     // Catch: java.io.IOException -> L2c
        L5f:
            if (r1 == 0) goto L64
            r1.close()     // Catch: java.io.IOException -> L2c
        L64:
            if (r6 == 0) goto L67
            goto L33
        L67:
            return r0
        L68:
            r0 = move-exception
        L69:
            if (r7 == 0) goto L71
            r7.close()     // Catch: java.io.IOException -> L6f
            goto L71
        L6f:
            r6 = move-exception
            goto L7c
        L71:
            if (r1 == 0) goto L76
            r1.close()     // Catch: java.io.IOException -> L6f
        L76:
            if (r6 == 0) goto L7f
            r6.close()     // Catch: java.io.IOException -> L6f
            goto L7f
        L7c:
            r6.printStackTrace()
        L7f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.utils.FileUtil.assetFileDescriptorToByteArray(android.content.Context, java.lang.String):byte[]");
    }
}
