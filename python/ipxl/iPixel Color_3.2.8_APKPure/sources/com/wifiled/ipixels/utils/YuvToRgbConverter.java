package com.wifiled.ipixels.utils;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.media.Image;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.renderscript.Type;
import com.wifiled.ipixels.App;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: YuvToRgbConverter.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/wifiled/ipixels/utils/YuvToRgbConverter;", "", "<init>", "()V", "rs", "Landroid/renderscript/RenderScript;", "kotlin.jvm.PlatformType", "scriptYuvToRgb", "Landroid/renderscript/ScriptIntrinsicYuvToRGB;", "pixelCount", "", "yuvBuffer", "Ljava/nio/ByteBuffer;", "inputAllocation", "Landroid/renderscript/Allocation;", "outputAllocation", "yuvToRgb", "", "image", "Landroid/media/Image;", "output", "Landroid/graphics/Bitmap;", "imageToByteBuffer", "outputBuffer", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class YuvToRgbConverter {
    public static final YuvToRgbConverter INSTANCE = new YuvToRgbConverter();
    private static Allocation inputAllocation;
    private static Allocation outputAllocation;
    private static int pixelCount;
    private static final RenderScript rs;
    private static final ScriptIntrinsicYuvToRGB scriptYuvToRgb;
    private static ByteBuffer yuvBuffer;

    private YuvToRgbConverter() {
    }

    static {
        RenderScript create = RenderScript.create(App.INSTANCE.getContext());
        rs = create;
        scriptYuvToRgb = ScriptIntrinsicYuvToRGB.create(create, Element.U8_4(create));
        pixelCount = -1;
    }

    public final synchronized void yuvToRgb(Image image, Bitmap output) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(output, "output");
        if (yuvBuffer == null) {
            pixelCount = image.getCropRect().width() * image.getCropRect().height();
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect((pixelCount * ImageFormat.getBitsPerPixel(35)) / 8);
            Intrinsics.checkNotNullExpressionValue(allocateDirect, "allocateDirect(...)");
            yuvBuffer = allocateDirect;
        }
        ByteBuffer byteBuffer = yuvBuffer;
        Allocation allocation = null;
        if (byteBuffer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yuvBuffer");
            byteBuffer = null;
        }
        byteBuffer.rewind();
        ByteBuffer byteBuffer2 = yuvBuffer;
        if (byteBuffer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yuvBuffer");
            byteBuffer2 = null;
        }
        byte[] array = byteBuffer2.array();
        Intrinsics.checkNotNullExpressionValue(array, "array(...)");
        imageToByteBuffer(image, array);
        if (inputAllocation == null) {
            RenderScript renderScript = rs;
            Element element = new Type.Builder(renderScript, Element.YUV(renderScript)).setYuvFormat(17).create().getElement();
            ByteBuffer byteBuffer3 = yuvBuffer;
            if (byteBuffer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yuvBuffer");
                byteBuffer3 = null;
            }
            Allocation createSized = Allocation.createSized(renderScript, element, byteBuffer3.array().length);
            Intrinsics.checkNotNullExpressionValue(createSized, "createSized(...)");
            inputAllocation = createSized;
        }
        if (outputAllocation == null) {
            Allocation createFromBitmap = Allocation.createFromBitmap(rs, output);
            Intrinsics.checkNotNullExpressionValue(createFromBitmap, "createFromBitmap(...)");
            outputAllocation = createFromBitmap;
        }
        Allocation allocation2 = inputAllocation;
        if (allocation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inputAllocation");
            allocation2 = null;
        }
        ByteBuffer byteBuffer4 = yuvBuffer;
        if (byteBuffer4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yuvBuffer");
            byteBuffer4 = null;
        }
        allocation2.copyFrom(byteBuffer4.array());
        ScriptIntrinsicYuvToRGB scriptIntrinsicYuvToRGB = scriptYuvToRgb;
        Allocation allocation3 = inputAllocation;
        if (allocation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inputAllocation");
            allocation3 = null;
        }
        scriptIntrinsicYuvToRGB.setInput(allocation3);
        Allocation allocation4 = outputAllocation;
        if (allocation4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outputAllocation");
            allocation4 = null;
        }
        scriptIntrinsicYuvToRGB.forEach(allocation4);
        Allocation allocation5 = outputAllocation;
        if (allocation5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outputAllocation");
        } else {
            allocation = allocation5;
        }
        allocation.copyTo(output);
    }

    private final void imageToByteBuffer(Image image, byte[] outputBuffer) {
        int i;
        int i2;
        Rect rect;
        Image.Plane[] planeArr;
        Rect cropRect = image.getCropRect();
        Image.Plane[] planes = image.getPlanes();
        Intrinsics.checkNotNull(planes);
        int length = planes.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            Image.Plane plane = planes[i3];
            int i5 = i4 + 1;
            if (i4 != 0) {
                if (i4 == 1) {
                    i2 = pixelCount + 1;
                } else if (i4 != 2) {
                    planeArr = planes;
                    i3++;
                    i4 = i5;
                    planes = planeArr;
                    cropRect = cropRect;
                } else {
                    i2 = pixelCount;
                }
                i = 2;
            } else {
                i = 1;
                i2 = 0;
            }
            ByteBuffer buffer = plane.getBuffer();
            int rowStride = plane.getRowStride();
            int pixelStride = plane.getPixelStride();
            if (i4 == 0) {
                rect = cropRect;
                planeArr = planes;
            } else {
                planeArr = planes;
                rect = new Rect(cropRect.left / 2, cropRect.top / 2, cropRect.right / 2, cropRect.bottom / 2);
            }
            int width = rect.width();
            int height = rect.height();
            byte[] bArr = new byte[plane.getRowStride()];
            int i6 = (pixelStride == 1 && i == 1) ? width : ((width - 1) * pixelStride) + 1;
            int i7 = 0;
            while (i7 < height) {
                Rect rect2 = cropRect;
                buffer.position(((rect.top + i7) * rowStride) + (rect.left * pixelStride));
                if (pixelStride == 1 && i == 1) {
                    buffer.get(outputBuffer, i2, i6);
                    i2 += i6;
                } else {
                    buffer.get(bArr, 0, i6);
                    for (int i8 = 0; i8 < width; i8++) {
                        outputBuffer[i2] = bArr[i8 * pixelStride];
                        i2 += i;
                    }
                }
                i7++;
                cropRect = rect2;
            }
            i3++;
            i4 = i5;
            planes = planeArr;
            cropRect = cropRect;
        }
    }
}
