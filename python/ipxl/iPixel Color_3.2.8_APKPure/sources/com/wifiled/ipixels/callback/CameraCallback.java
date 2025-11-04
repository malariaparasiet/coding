package com.wifiled.ipixels.callback;

import android.hardware.Camera;

/* loaded from: classes3.dex */
public interface CameraCallback {
    void onCameraClosed();

    void onCameraConfigurationChanged(int cameraID, int displayOrientation);

    void onCameraError(Exception e);

    void onCameraOpened(Camera camera, int cameraId, int displayOrientation, boolean isMirror);

    void onPreview(byte[] data, Camera camera);
}
