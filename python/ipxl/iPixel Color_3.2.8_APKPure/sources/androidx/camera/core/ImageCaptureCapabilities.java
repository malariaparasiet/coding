package androidx.camera.core;

import java.util.Set;

/* loaded from: classes.dex */
public interface ImageCaptureCapabilities {
    Set<Integer> getSupportedOutputFormats();

    boolean isCaptureProcessProgressSupported();

    boolean isPostviewSupported();
}
