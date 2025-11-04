package androidx.camera.core.impl;

import android.util.Pair;
import android.util.Size;
import androidx.camera.core.CameraInfo;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public interface SessionProcessor {

    public interface CaptureCallback {
        default void onCaptureCompleted(long j, int i, CameraCaptureResult cameraCaptureResult) {
        }

        default void onCaptureFailed(int i) {
        }

        default void onCaptureProcessProgressed(int i) {
        }

        default void onCaptureProcessStarted(int i) {
        }

        default void onCaptureSequenceAborted(int i) {
        }

        default void onCaptureSequenceCompleted(int i) {
        }

        default void onCaptureStarted(int i, long j) {
        }
    }

    void abortCapture(int i);

    void deInitSession();

    default Pair<Long, Long> getRealtimeCaptureLatency() {
        return null;
    }

    SessionConfig initSession(CameraInfo cameraInfo, OutputSurfaceConfiguration outputSurfaceConfiguration);

    void onCaptureSessionEnd();

    void onCaptureSessionStart(RequestProcessor requestProcessor);

    void setParameters(Config config);

    int startCapture(boolean z, TagBundle tagBundle, CaptureCallback captureCallback);

    int startRepeating(TagBundle tagBundle, CaptureCallback captureCallback);

    default int startTrigger(Config config, TagBundle tagBundle, CaptureCallback captureCallback) {
        return -1;
    }

    void stopRepeating();

    default Map<Integer, List<Size>> getSupportedPostviewSize(Size size) {
        return Collections.emptyMap();
    }

    default Set<Integer> getSupportedCameraOperations() {
        return Collections.emptySet();
    }
}
