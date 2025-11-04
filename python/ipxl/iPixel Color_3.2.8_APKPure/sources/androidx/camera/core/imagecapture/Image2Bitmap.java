package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProcessingUtil;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.ImageReaderProxys;
import androidx.camera.core.SafeCloseImageReaderProxy;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class Image2Bitmap implements Operation<Packet<ImageProxy>, Bitmap> {
    @Override // androidx.camera.core.processing.Operation
    public Bitmap apply(Packet<ImageProxy> packet) throws ImageCaptureException {
        Bitmap rotateBitmap;
        SafeCloseImageReaderProxy safeCloseImageReaderProxy = null;
        try {
            try {
                if (packet.getFormat() == 35) {
                    ImageProxy data = packet.getData();
                    boolean z = packet.getRotationDegrees() % Opcodes.GETFIELD != 0;
                    SafeCloseImageReaderProxy safeCloseImageReaderProxy2 = new SafeCloseImageReaderProxy(ImageReaderProxys.createIsolatedReader(z ? data.getHeight() : data.getWidth(), z ? data.getWidth() : data.getHeight(), 1, 2));
                    try {
                        ImageProxy convertYUVToRGB = ImageProcessingUtil.convertYUVToRGB(data, safeCloseImageReaderProxy2, ByteBuffer.allocateDirect(data.getWidth() * data.getHeight() * 4), packet.getRotationDegrees(), false);
                        data.close();
                        if (convertYUVToRGB == null) {
                            throw new ImageCaptureException(0, "Can't covert YUV to RGB", null);
                        }
                        rotateBitmap = ImageUtil.createBitmapFromImageProxy(convertYUVToRGB);
                        convertYUVToRGB.close();
                        safeCloseImageReaderProxy = safeCloseImageReaderProxy2;
                    } catch (UnsupportedOperationException e) {
                        e = e;
                        throw new ImageCaptureException(0, "Can't convert " + (packet.getFormat() == 35 ? "YUV" : "JPEG") + " to bitmap", e);
                    } catch (Throwable th) {
                        th = th;
                        safeCloseImageReaderProxy = safeCloseImageReaderProxy2;
                        if (safeCloseImageReaderProxy != null) {
                            safeCloseImageReaderProxy.close();
                        }
                        throw th;
                    }
                } else if (packet.getFormat() == 256) {
                    ImageProxy data2 = packet.getData();
                    Bitmap createBitmapFromImageProxy = ImageUtil.createBitmapFromImageProxy(data2);
                    data2.close();
                    rotateBitmap = ImageUtil.rotateBitmap(createBitmapFromImageProxy, packet.getRotationDegrees());
                } else {
                    throw new IllegalArgumentException("Invalid postview image format : " + packet.getFormat());
                }
                if (safeCloseImageReaderProxy != null) {
                    safeCloseImageReaderProxy.close();
                }
                return rotateBitmap;
            } catch (UnsupportedOperationException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
