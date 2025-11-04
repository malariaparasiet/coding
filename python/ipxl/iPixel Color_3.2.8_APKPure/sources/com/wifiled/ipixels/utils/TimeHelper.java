package com.wifiled.ipixels.utils;

import android.os.SystemClock;
import kotlin.Metadata;

/* compiled from: TimeHelper.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/wifiled/ipixels/utils/TimeHelper;", "", "<init>", "()V", "lastSendTime", "", "allowSend", "", "delay", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TimeHelper {
    public static final TimeHelper INSTANCE = new TimeHelper();
    private static long lastSendTime;

    private TimeHelper() {
    }

    public static /* synthetic */ boolean allowSend$default(TimeHelper timeHelper, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 400;
        }
        return timeHelper.allowSend(i);
    }

    public final boolean allowSend(int delay) {
        if (SystemClock.elapsedRealtime() - lastSendTime < delay) {
            return false;
        }
        lastSendTime = SystemClock.elapsedRealtime();
        return true;
    }
}
