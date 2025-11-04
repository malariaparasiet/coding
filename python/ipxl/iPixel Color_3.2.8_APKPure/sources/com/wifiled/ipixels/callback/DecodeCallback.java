package com.wifiled.ipixels.callback;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: DecodeCallback.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH&J\u0015\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0007¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/callback/DecodeCallback;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LATITUDE_SOUTH, "", "onStart", "", "t", "(Ljava/lang/Object;)V", "onProgress", "progress", "", "onError", NotificationCompat.CATEGORY_MESSAGE, "", "onFinish", "s", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface DecodeCallback<T, S> {
    void onError(String msg);

    void onFinish(S s);

    void onProgress(int progress);

    void onStart(T t);
}
