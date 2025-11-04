package com.wifiled.ipixels.core;

import android.graphics.Bitmap;
import android.media.Image;
import androidx.constraintlayout.motion.widget.Key;
import com.libyuv.util.YuvUtil;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.utils.BitmapUtils;
import com.wifiled.ipixels.utils.ImageUtils;
import com.wifiled.ipixels.utils.RSYuvUtils;
import com.wifiled.ipixels.utils.YuvToRgbConverter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CoreDecoder.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/wifiled/ipixels/core/CoreDecoder;", "", "<init>", "()V", "TAG", "", "videoDecode", "Landroid/graphics/Bitmap;", "image", "Landroid/media/Image;", Key.ROTATION, "", "videoDecode2", "cameraDecode", "isMirror", "", "cameraDecode2", "cameraDecode3", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CoreDecoder {
    public static final CoreDecoder INSTANCE = new CoreDecoder();
    public static final String TAG = "Core";

    private CoreDecoder() {
    }

    public final Bitmap videoDecode(Image image, int rotation) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkNotNullParameter(image, "image");
        List<Integer> ledSize = AppConfig.INSTANCE.getLedSize();
        int intValue = ledSize.get(0).intValue();
        int intValue2 = ledSize.get(1).intValue();
        int width = image.getWidth();
        int height = image.getHeight();
        LogUtils.logi("Core>>>[videoDecode]: 视频帧宽高: width:" + width + " ---- height:" + height + " --- rotation:" + rotation, new Object[0]);
        byte[] bytesFromImageAsType = ImageUtils.getBytesFromImageAsType(image, 2);
        if (width >= height) {
            int i7 = (intValue2 * width) / height;
            if (i7 % 2 != 0) {
                i7--;
            }
            i2 = i7;
            i = intValue2;
        } else {
            int i8 = (intValue * height) / width;
            if (i8 % 2 != 0) {
                i8--;
            }
            i = i8;
            i2 = intValue;
        }
        byte[] bArr = new byte[((i2 * i) * 3) / 2];
        YuvUtil.yuvCompress(bytesFromImageAsType, width, height, bArr, i2, i, 3, rotation, false);
        if (rotation == 90 || rotation == 270) {
            i3 = i2;
            i4 = i;
        } else {
            i4 = i2;
            i3 = i;
        }
        byte[] bArr2 = new byte[((i4 * i3) * 3) / 2];
        if (i4 >= i3) {
            int i9 = (i4 - i3) / 2;
            if (i9 % 2 != 0) {
                i9--;
            }
            i6 = i9;
            i5 = 0;
        } else {
            int i10 = (i3 - i4) / 2;
            if (i10 % 2 != 0) {
                i10--;
            }
            i5 = i10;
            i6 = 0;
        }
        YuvUtil.yuvCropI420(bArr, i4, i3, bArr2, intValue, intValue2, i6, i5);
        YuvUtil.yuvI420ToNV21(bArr2, intValue, intValue2, bytesFromImageAsType);
        RSYuvUtils rSYuvUtils = RSYuvUtils.INSTANCE;
        Intrinsics.checkNotNull(bytesFromImageAsType);
        return BitmapUtils.INSTANCE.zoomImage(rSYuvUtils.nv21ToBitmap(bytesFromImageAsType, intValue, intValue2), intValue, intValue2);
    }

    public final Bitmap videoDecode2(Image image, int rotation) {
        Intrinsics.checkNotNullParameter(image, "image");
        LogUtils.logi("Core>>>[videoDecode2]: " + rotation, new Object[0]);
        List<Integer> ledSize = AppConfig.INSTANCE.getLedSize();
        int intValue = ledSize.get(0).intValue();
        int intValue2 = ledSize.get(1).intValue();
        byte[] bytesFromImageAsType = ImageUtils.getBytesFromImageAsType(image, 2);
        RSYuvUtils rSYuvUtils = RSYuvUtils.INSTANCE;
        Intrinsics.checkNotNull(bytesFromImageAsType);
        Bitmap zoomImage = BitmapUtils.INSTANCE.zoomImage(rSYuvUtils.nv21ToBitmap(bytesFromImageAsType, image.getWidth(), image.getHeight()), intValue, intValue2);
        return (rotation == 90 || rotation == 270) ? BitmapUtils.INSTANCE.rotateBitmap(rotation, zoomImage) : zoomImage;
    }

    public final Bitmap cameraDecode(Image image, boolean isMirror) {
        Intrinsics.checkNotNullParameter(image, "image");
        List<Integer> ledSize = AppConfig.INSTANCE.getLedSize();
        int intValue = ledSize.get(0).intValue();
        int intValue2 = ledSize.get(1).intValue();
        byte[] bytesFromImageAsType = ImageUtils.getBytesFromImageAsType(image, 2);
        byte[] bArr = new byte[((intValue * intValue2) * 3) / 2];
        YuvUtil.yuvCompress(bytesFromImageAsType, image.getWidth(), image.getHeight(), bArr, intValue, intValue2, 3, 90, isMirror);
        YuvUtil.yuvI420ToNV21(bArr, intValue, intValue2, bytesFromImageAsType);
        RSYuvUtils rSYuvUtils = RSYuvUtils.INSTANCE;
        Intrinsics.checkNotNull(bytesFromImageAsType);
        return rSYuvUtils.nv21ToBitmap(bytesFromImageAsType, intValue, intValue2);
    }

    public final Bitmap cameraDecode2(Image image, boolean isMirror) {
        Intrinsics.checkNotNullParameter(image, "image");
        List<Integer> ledSize = AppConfig.INSTANCE.getLedSize();
        int intValue = ledSize.get(0).intValue();
        int intValue2 = ledSize.get(1).intValue();
        byte[] bytesFromImageAsType = ImageUtils.getBytesFromImageAsType(image, 2);
        RSYuvUtils rSYuvUtils = RSYuvUtils.INSTANCE;
        Intrinsics.checkNotNull(bytesFromImageAsType);
        return BitmapUtils.INSTANCE.rotateMirrorCameraBitmap(isMirror, BitmapUtils.INSTANCE.zoomImage(rSYuvUtils.nv21ToBitmap(bytesFromImageAsType, image.getWidth(), image.getHeight()), intValue, intValue2));
    }

    public final Bitmap cameraDecode3(Image image, boolean isMirror) {
        Intrinsics.checkNotNullParameter(image, "image");
        List<Integer> ledSize = AppConfig.INSTANCE.getLedSize();
        int intValue = ledSize.get(0).intValue();
        int intValue2 = ledSize.get(1).intValue();
        Bitmap createBitmap = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        YuvToRgbConverter.INSTANCE.yuvToRgb(image, createBitmap);
        return BitmapUtils.INSTANCE.rotateMirrorCameraBitmap(isMirror, BitmapUtils.INSTANCE.zoomImage(createBitmap, intValue, intValue2));
    }
}
