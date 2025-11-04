package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import com.wifiled.ipixels.BuildConfig;

/* loaded from: classes.dex */
public class CaptureSessionShouldUseMrirQuirk implements Quirk {
    static boolean load() {
        return BuildConfig.FLAVOR.equalsIgnoreCase(Build.BRAND) && Build.VERSION.SDK_INT >= 35;
    }
}
