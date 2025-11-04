package com.wifiled.ipixels.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import androidx.core.os.HandlerCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HandlerUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/wifiled/ipixels/utils/HandlerUtil;", "", "<init>", "()V", "HandlerHolder", "HandlerMain", "OnReceiveMessageListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HandlerUtil {

    /* compiled from: HandlerUtil.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/utils/HandlerUtil$OnReceiveMessageListener;", "", "handlerMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnReceiveMessageListener {
        void handlerMessage(Message msg);
    }

    /* compiled from: HandlerUtil.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0018\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/wifiled/ipixels/utils/HandlerUtil$HandlerHolder;", "Landroid/os/Handler;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/wifiled/ipixels/utils/HandlerUtil$OnReceiveMessageListener;", "<init>", "(Lcom/wifiled/ipixels/utils/HandlerUtil$OnReceiveMessageListener;)V", "mListenerWeakReference", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class HandlerHolder extends Handler {
        private WeakReference<OnReceiveMessageListener> mListenerWeakReference;

        public HandlerHolder(OnReceiveMessageListener onReceiveMessageListener) {
            this.mListenerWeakReference = new WeakReference<>(onReceiveMessageListener);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            WeakReference<OnReceiveMessageListener> weakReference = this.mListenerWeakReference;
            if (weakReference != null) {
                Intrinsics.checkNotNull(weakReference);
                if (weakReference.get() != null) {
                    WeakReference<OnReceiveMessageListener> weakReference2 = this.mListenerWeakReference;
                    Intrinsics.checkNotNull(weakReference2);
                    OnReceiveMessageListener onReceiveMessageListener = weakReference2.get();
                    Intrinsics.checkNotNull(onReceiveMessageListener);
                    onReceiveMessageListener.handlerMessage(msg);
                }
            }
        }
    }

    private HandlerUtil() {
        throw new UnsupportedOperationException("Guy, r u crazy? u can NOT instantiate me...");
    }

    /* compiled from: HandlerUtil.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/wifiled/ipixels/utils/HandlerUtil$HandlerMain;", "Landroid/os/Handler;", "<init>", "()V", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class HandlerMain extends Handler {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        private HandlerMain() {
        }

        /* compiled from: HandlerUtil.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/wifiled/ipixels/utils/HandlerUtil$HandlerMain$Companion;", "", "<init>", "()V", "getMainHandler", "Ljava/lang/ref/WeakReference;", "Landroid/os/Handler;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final WeakReference<Handler> getMainHandler() {
                return new WeakReference<>(HandlerCompat.createAsync(Looper.getMainLooper()));
            }
        }
    }
}
