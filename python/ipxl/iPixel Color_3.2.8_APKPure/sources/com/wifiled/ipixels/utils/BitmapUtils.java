package com.wifiled.ipixels.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.util.Log;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.musiclib.player.constant.DbFinal;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImagePixelationFilter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapUtils.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0005J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u000b\u001a\u00020\u0005J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0005J\"\u0010\u0016\u001a\u0004\u0018\u00010\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J \u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0011J\"\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J \u0010!\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0011J\"\u0010$\u001a\u0004\u0018\u00010\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0011J\u001a\u0010'\u001a\u0004\u0018\u00010\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010(\u001a\u00020\u0011J\u0018\u0010)\u001a\u0004\u0018\u00010\u00052\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0011J\"\u0010,\u001a\u0004\u0018\u00010\u00052\b\u0010-\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J \u0010.\u001a\u00020\u00052\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J \u00101\u001a\u00020\u00052\b\u00102\u001a\u0004\u0018\u0001002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J\u0010\u00103\u001a\u00020\u00112\b\u00102\u001a\u0004\u0018\u000100J\u0016\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0005J\u0016\u00106\u001a\u00020\u00052\u0006\u00107\u001a\u0002082\u0006\u0010\u0015\u001a\u00020\u0005J\u0010\u00109\u001a\u0004\u0018\u00010\u00052\u0006\u0010:\u001a\u00020;J\u000e\u0010<\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u0005J\u000e\u0010>\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0005R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/wifiled/ipixels/utils/BitmapUtils;", "", "<init>", "()V", "getMskBitmap", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "originBitmap", "splitBitmap", "", "originalBitmap", "argbToNv21", "", "argb", "", "width", "", "height", "getImageUri", "Landroid/net/Uri;", "bitmap", "bitmapToNv21", "src", "zoomImage", "original", "newWidth", "newHeight", "compressBitmap", "format", "Landroid/graphics/Bitmap$CompressFormat;", "many", "", "sizeCompres", "targetWidth", "targetHeight", "scaleBitmapCanvas", "last", "", "centerSquareScaleBitmap", "edgeLength", "getResizedBitmap", "image", "maxSize", "getOriginalBitmap", "data", "getImageThumbnail", "imagePath", "", "getRotateBitmap", DbFinal.LOCAL_PATH, "readPictureDegree", "rotateBitmap", "angle", "rotateMirrorCameraBitmap", "mirror", "", "DrawableToBitmap", "drawable", "Landroid/graphics/drawable/Drawable;", "bgrToRgbBitmap", "bgrBitmap", "getRGBArray", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BitmapUtils {
    public static final BitmapUtils INSTANCE = new BitmapUtils();
    private static long last;

    private BitmapUtils() {
    }

    public final Bitmap getMskBitmap(Context context, Bitmap originBitmap) {
        GPUImage gPUImage = new GPUImage(context);
        gPUImage.setImage(originBitmap);
        GPUImagePixelationFilter gPUImagePixelationFilter = new GPUImagePixelationFilter();
        gPUImagePixelationFilter.setPixel(3.0f);
        gPUImage.setFilter(gPUImagePixelationFilter);
        Bitmap bitmapWithFilterApplied = gPUImage.getBitmapWithFilterApplied();
        Intrinsics.checkNotNullExpressionValue(bitmapWithFilterApplied, "getBitmapWithFilterApplied(...)");
        return bitmapWithFilterApplied;
    }

    public final List<Bitmap> splitBitmap(Bitmap originalBitmap) {
        Intrinsics.checkNotNullParameter(originalBitmap, "originalBitmap");
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        int i = 0;
        int intValue = AppConfig.INSTANCE.getLedSize().get(0).intValue() >= 256 ? 223 : AppConfig.INSTANCE.getLedSize().get(0).intValue() - 32;
        int ceil = (int) Math.ceil(width / intValue);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i < ceil) {
            int i3 = i == ceil + (-1) ? width - i2 : intValue;
            Bitmap createBitmap = Bitmap.createBitmap(i3, height, Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            new Canvas(createBitmap).drawBitmap(originalBitmap, -i2, 0.0f, new Paint());
            arrayList.add(createBitmap);
            i2 += i3;
            i++;
        }
        return arrayList;
    }

    private final byte[] argbToNv21(int[] argb, int width, int height) {
        int i = width * height;
        int i2 = (i * 3) / 2;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < height; i5++) {
            int i6 = 0;
            while (i6 < width) {
                int i7 = argb[i4];
                int i8 = (16711680 & i7) >> 16;
                int i9 = (65280 & i7) >> 8;
                int i10 = 255;
                int i11 = i7 & 255;
                int i12 = (((((i8 * 66) + (i9 * Opcodes.LOR)) + (i11 * 25)) + 128) >> 8) + 16;
                int i13 = (((((i8 * (-38)) - (i9 * 74)) + (i11 * 112)) + 128) >> 8) + 128;
                int i14 = (((((i8 * 112) - (i9 * 94)) - (i11 * 18)) + 128) >> 8) + 128;
                int i15 = i3 + 1;
                if (i12 < 0) {
                    i12 = 0;
                } else if (i12 > 255) {
                    i12 = 255;
                }
                bArr[i3] = (byte) i12;
                if (i5 % 2 == 0 && i4 % 2 == 0 && i < i2 - 2) {
                    int i16 = i + 1;
                    if (i14 < 0) {
                        i14 = 0;
                    } else if (i14 > 255) {
                        i14 = 255;
                    }
                    bArr[i] = (byte) i14;
                    i += 2;
                    if (i13 < 0) {
                        i10 = 0;
                    } else if (i13 <= 255) {
                        i10 = i13;
                    }
                    bArr[i16] = (byte) i10;
                }
                i4++;
                i6++;
                i3 = i15;
            }
        }
        return bArr;
    }

    public final Uri getImageUri(Context context, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        File file = new File(context.getCacheDir(), "image_" + System.currentTimeMillis() + ".png");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                FileOutputStream fileOutputStream2 = fileOutputStream;
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
                fileOutputStream2.flush();
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
                return FileProvider.getUriForFile(context, context.getPackageName() + ".android7.fileprovider", file);
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final byte[] bitmapToNv21(Bitmap src, int width, int height) {
        if (src == null || src.getWidth() < width || src.getHeight() < height) {
            return null;
        }
        int[] iArr = new int[width * height];
        src.getPixels(iArr, 0, width, 0, 0, width, height);
        return argbToNv21(iArr, width, height);
    }

    public final Bitmap zoomImage(Bitmap original, int newWidth, int newHeight) {
        float f;
        float f2;
        if (original != null) {
            f = original.getWidth();
            f2 = original.getHeight();
        } else {
            f = 1.0f;
            f2 = 1.0f;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(newWidth / f, newHeight / f2);
        Intrinsics.checkNotNull(original);
        Bitmap createBitmap = Bitmap.createBitmap(original, 0, 0, (int) f, (int) f2, matrix, true);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        return createBitmap;
    }

    public static /* synthetic */ Bitmap compressBitmap$default(BitmapUtils bitmapUtils, Bitmap bitmap, Bitmap.CompressFormat compressFormat, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            compressFormat = Bitmap.CompressFormat.JPEG;
        }
        return bitmapUtils.compressBitmap(bitmap, compressFormat, f);
    }

    public final Bitmap compressBitmap(Bitmap bitmap, Bitmap.CompressFormat format, float many) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(format, "format");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(format, ((int) many) * 100, byteArrayOutputStream);
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    public final Bitmap sizeCompres(Bitmap bitmap, int targetWidth, int targetHeight) {
        int i;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Bitmap bitmap2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            if (byteArrayOutputStream.toByteArray().length / 1024 > 1024) {
                byteArrayOutputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i2 = 1;
            options.inJustDecodeBounds = true;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            BitmapFactory.decodeStream(byteArrayInputStream, null, options);
            options.inJustDecodeBounds = false;
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            if (i3 >= i4 && i3 > targetWidth) {
                i = i3 / targetWidth;
            } else {
                i = (i4 < i3 || i4 <= targetHeight) ? 1 : i4 / targetHeight;
            }
            if (i > 1) {
                i2 = i;
            }
            options.inSampleSize = i2;
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            bitmap2 = BitmapFactory.decodeStream(byteArrayInputStream2, null, options);
            byteArrayInputStream2.close();
            byteArrayOutputStream.close();
            return bitmap2;
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap2;
        }
    }

    public final Bitmap scaleBitmapCanvas(Bitmap bitmap, int targetWidth, int targetHeight) {
        PaintFlagsDrawFilter paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, 3);
        Canvas canvas = bitmap != null ? new Canvas(bitmap) : null;
        Integer valueOf = bitmap != null ? Integer.valueOf(bitmap.getWidth()) : null;
        Integer valueOf2 = bitmap != null ? Integer.valueOf(bitmap.getHeight()) : null;
        Intrinsics.checkNotNull(valueOf);
        float intValue = targetWidth / valueOf.intValue();
        Intrinsics.checkNotNull(valueOf2);
        float intValue2 = targetHeight / valueOf2.intValue();
        Bitmap createBitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Paint paint = new Paint();
        if (canvas != null) {
            canvas.setBitmap(createBitmap);
        }
        paint.setXfermode(null);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setStyle(Paint.Style.FILL);
        if (canvas != null) {
            canvas.save();
        }
        if (canvas != null) {
            canvas.scale(intValue, intValue2);
        }
        if (canvas != null) {
            canvas.setDrawFilter(paintFlagsDrawFilter);
        }
        if (canvas != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
        if (canvas != null) {
            canvas.restore();
        }
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public final Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if (bitmap == null || edgeLength <= 0) {
            return null;
        }
        last = System.currentTimeMillis();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= edgeLength || height <= edgeLength) {
            return bitmap;
        }
        int max = (Math.max(width, height) * edgeLength) / Math.min(width, height);
        int i = width > height ? max : edgeLength;
        if (width > height) {
            max = edgeLength;
        }
        try {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, max, true);
            Intrinsics.checkNotNull(createScaledBitmap);
            Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap, (i - edgeLength) / 2, (max - edgeLength) / 2, edgeLength, edgeLength);
            createScaledBitmap.recycle();
            return createBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    public final Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int i;
        Intrinsics.checkNotNullParameter(image, "image");
        float width = image.getWidth() / image.getHeight();
        if (width > 1.0f) {
            i = (int) (maxSize / width);
        } else {
            int i2 = (int) (maxSize * width);
            i = maxSize;
            maxSize = i2;
        }
        return Bitmap.createScaledBitmap(image, maxSize, i, true);
    }

    public final Bitmap getOriginalBitmap(byte[] data, int width, int height) {
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        ByteBuffer wrap = ByteBuffer.wrap(data);
        wrap.rewind();
        createBitmap.copyPixelsFromBuffer(wrap);
        return createBitmap;
    }

    public final Bitmap getImageThumbnail(String imagePath, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false;
        int i = options.outHeight;
        int i2 = options.outWidth / width;
        int i3 = i / height;
        if (i2 >= i3) {
            i2 = i3;
        }
        options.inSampleSize = i2 > 0 ? i2 : 1;
        Bitmap extractThumbnail = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(imagePath, options), width, height, 2);
        Intrinsics.checkNotNull(extractThumbnail);
        return extractThumbnail;
    }

    public final Bitmap getRotateBitmap(String path, int width, int height) {
        int readPictureDegree = readPictureDegree(path);
        Bitmap imageThumbnail = getImageThumbnail(path, width, height);
        return readPictureDegree != 0 ? rotateBitmap(readPictureDegree, imageThumbnail) : imageThumbnail;
    }

    public final int readPictureDegree(String path) {
        int i = 0;
        try {
            Intrinsics.checkNotNull(path);
            int attributeInt = new ExifInterface(path).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Log.e("ContentValues", "readPictureDegree: orientation-------->" + attributeInt);
            if (attributeInt == 3) {
                i = Opcodes.GETFIELD;
            } else if (attributeInt == 6) {
                i = 90;
            } else if (attributeInt == 8) {
                i = 270;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("ContentValues", "readPictureDegree: degree-origin------->" + i);
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Bitmap rotateBitmap(int r9, android.graphics.Bitmap r10) {
        /*
            r8 = this;
            java.lang.String r0 = "bitmap"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            android.graphics.Matrix r6 = new android.graphics.Matrix
            r6.<init>()
            float r9 = (float) r9
            r6.postRotate(r9)
            int r4 = r10.getWidth()     // Catch: java.lang.OutOfMemoryError -> L1f
            int r5 = r10.getHeight()     // Catch: java.lang.OutOfMemoryError -> L1f
            r7 = 1
            r2 = 0
            r3 = 0
            r1 = r10
            android.graphics.Bitmap r9 = android.graphics.Bitmap.createBitmap(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.OutOfMemoryError -> L20
            goto L21
        L1f:
            r1 = r10
        L20:
            r9 = 0
        L21:
            if (r9 != 0) goto L24
            r9 = r1
        L24:
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r9)
            if (r10 != 0) goto L2d
            r1.recycle()
        L2d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.utils.BitmapUtils.rotateBitmap(int, android.graphics.Bitmap):android.graphics.Bitmap");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Bitmap rotateMirrorCameraBitmap(boolean r9, android.graphics.Bitmap r10) {
        /*
            r8 = this;
            java.lang.String r0 = "bitmap"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            android.graphics.Matrix r6 = new android.graphics.Matrix
            r6.<init>()
            if (r9 == 0) goto Lf
            r0 = 270(0x10e, float:3.78E-43)
            goto L11
        Lf:
            r0 = 90
        L11:
            float r0 = (float) r0
            r6.postRotate(r0)
            if (r9 == 0) goto L1e
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            r6.postScale(r9, r0)
        L1e:
            int r4 = r10.getWidth()     // Catch: java.lang.OutOfMemoryError -> L2f
            int r5 = r10.getHeight()     // Catch: java.lang.OutOfMemoryError -> L2f
            r7 = 1
            r2 = 0
            r3 = 0
            r1 = r10
            android.graphics.Bitmap r9 = android.graphics.Bitmap.createBitmap(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.OutOfMemoryError -> L30
            goto L31
        L2f:
            r1 = r10
        L30:
            r9 = 0
        L31:
            if (r9 != 0) goto L34
            r9 = r1
        L34:
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r9)
            if (r10 != 0) goto L3d
            r1.recycle()
        L3d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.utils.BitmapUtils.rotateMirrorCameraBitmap(boolean, android.graphics.Bitmap):android.graphics.Bitmap");
    }

    public final Bitmap DrawableToBitmap(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        drawable.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public final Bitmap bgrToRgbBitmap(Bitmap bgrBitmap) {
        Intrinsics.checkNotNullParameter(bgrBitmap, "bgrBitmap");
        int width = bgrBitmap.getWidth();
        int height = bgrBitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                int pixel = bgrBitmap.getPixel(i2, i);
                int blue = Color.blue(pixel);
                int green = Color.green(pixel);
                int red = Color.red(pixel);
                int rgb = Color.rgb(red, green, blue);
                Log.v("ruis", "rgbPixel---" + rgb + " blue-" + blue + " green-" + green + " red-" + red);
                createBitmap.setPixel(i2, i, rgb);
            }
        }
        return createBitmap;
    }

    public final int[] getRGBArray(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int[] iArr2 = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            iArr2[i2] = Color.blue(i3) | (Color.red(i3) << 16) | (Color.green(i3) << 8);
        }
        return iArr2;
    }
}
