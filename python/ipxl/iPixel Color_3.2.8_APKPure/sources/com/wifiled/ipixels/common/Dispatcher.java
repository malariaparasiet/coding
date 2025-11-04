package com.wifiled.ipixels.common;

import androidx.exifinterface.media.ExifInterface;
import com.wifiled.blelibrary.ble.queue.reconnect.DefaultReConnectHandler;
import java.util.ArrayDeque;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DispatcherWithTimeout.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 \u00112\u00020\u0001:\u0002\u0010\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/wifiled/ipixels/common/Dispatcher;", "Lcom/wifiled/ipixels/common/Task;", "<init>", "()V", "tasks", "Ljava/util/ArrayDeque;", "Ljava/lang/Runnable;", "latch", "Ljava/util/concurrent/CountDownLatch;", "execute", "", "enqueue", "add", "runnable", "remove", "clear", "Callback", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Dispatcher implements Task {
    private static final String TAG = "DispatcherWithTimeout";
    private static final String THREAD_NAME = "dispatcher-worker";
    private static final String TIMEOUT_TOKEN = "timeout_token";
    private static long ACK_TIME_OUT = DefaultReConnectHandler.DEFAULT_CONNECT_DELAY;
    private final ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    private final CountDownLatch latch = new CountDownLatch(2);

    /* compiled from: DispatcherWithTimeout.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/common/Dispatcher$Callback;", ExifInterface.GPS_DIRECTION_TRUE, "", "ack", "result", "(Ljava/lang/Object;)Ljava/lang/Object;", "timeout", "", "error", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Callback<T> {
        T ack(T result);

        void error();

        void timeout();
    }

    @Override // com.wifiled.ipixels.common.Task
    public void enqueue() {
    }

    @Override // com.wifiled.ipixels.common.Task
    public void execute() {
        this.tasks.isEmpty();
    }

    @Override // com.wifiled.ipixels.common.Task
    public Task add(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.tasks.add(runnable);
        return this;
    }

    @Override // com.wifiled.ipixels.common.Task
    public void remove(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.tasks.remove(runnable);
    }

    @Override // com.wifiled.ipixels.common.Task
    public void clear() {
        this.tasks.clear();
    }
}
