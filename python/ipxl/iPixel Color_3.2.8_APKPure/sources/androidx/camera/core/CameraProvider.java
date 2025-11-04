package androidx.camera.core;

import java.util.List;

/* loaded from: classes.dex */
public interface CameraProvider {
    List<CameraInfo> getAvailableCameraInfos();

    boolean hasCamera(CameraSelector cameraSelector) throws CameraInfoUnavailableException;

    default CameraInfo getCameraInfo(CameraSelector cameraSelector) {
        throw new UnsupportedOperationException("The camera provider is not implemented properly.");
    }
}
