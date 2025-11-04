package com.wifiled.ipixels.utils;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VibrateHelp.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u001e\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rJ\u0006\u0010\u0013\u001a\u00020\tR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/wifiled/ipixels/utils/VibrateHelp;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "vibrator", "Landroid/os/Vibrator;", "vSimple", "", "milliseconds", "", "amplitude", "", "vComplicated", "timings", "", "", "repeat", "stop", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VibrateHelp {
    private Vibrator vibrator;

    public VibrateHelp(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.vibrator = (Vibrator) context.getSystemService("vibrator");
    }

    public final void vSimple(long milliseconds, int amplitude) {
        Vibrator vibrator = this.vibrator;
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, amplitude));
        }
    }

    public final void vComplicated(long[] timings, int[] amplitude, int repeat) {
        Intrinsics.checkNotNullParameter(timings, "timings");
        Intrinsics.checkNotNullParameter(amplitude, "amplitude");
        Vibrator vibrator = this.vibrator;
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createWaveform(timings, amplitude, repeat));
        }
    }

    public final void stop() {
        Vibrator vibrator = this.vibrator;
        if (vibrator != null) {
            vibrator.cancel();
        }
    }
}
