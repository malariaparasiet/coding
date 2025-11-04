package okhttp3.internal.connection;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RoutePlanner;

/* compiled from: FastFallbackExchangeFinder.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0012H\u0002J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00120\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lokhttp3/internal/connection/FastFallbackExchangeFinder;", "Lokhttp3/internal/connection/ExchangeFinder;", "routePlanner", "Lokhttp3/internal/connection/RoutePlanner;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "<init>", "(Lokhttp3/internal/connection/RoutePlanner;Lokhttp3/internal/concurrent/TaskRunner;)V", "getRoutePlanner", "()Lokhttp3/internal/connection/RoutePlanner;", "connectDelayNanos", "", "nextTcpConnectAtNanos", "tcpConnectsInFlight", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "connectResults", "Ljava/util/concurrent/BlockingQueue;", "Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "kotlin.jvm.PlatformType", "find", "Lokhttp3/internal/connection/RealConnection;", "launchTcpConnect", "awaitTcpConnect", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "cancelInFlightConnects", "", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FastFallbackExchangeFinder implements ExchangeFinder {
    private final long connectDelayNanos;
    private final BlockingQueue<RoutePlanner.ConnectResult> connectResults;
    private long nextTcpConnectAtNanos;
    private final RoutePlanner routePlanner;
    private final TaskRunner taskRunner;
    private final CopyOnWriteArrayList<RoutePlanner.Plan> tcpConnectsInFlight;

    public FastFallbackExchangeFinder(RoutePlanner routePlanner, TaskRunner taskRunner) {
        Intrinsics.checkNotNullParameter(routePlanner, "routePlanner");
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        this.routePlanner = routePlanner;
        this.taskRunner = taskRunner;
        this.connectDelayNanos = TimeUnit.MILLISECONDS.toNanos(250L);
        this.nextTcpConnectAtNanos = Long.MIN_VALUE;
        this.tcpConnectsInFlight = new CopyOnWriteArrayList<>();
        this.connectResults = taskRunner.getBackend().decorate(new LinkedBlockingDeque());
    }

    @Override // okhttp3.internal.connection.ExchangeFinder
    public RoutePlanner getRoutePlanner() {
        return this.routePlanner;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0062 A[Catch: all -> 0x00bd, TryCatch #0 {all -> 0x00bd, blocks: (B:3:0x0002, B:5:0x000c, B:11:0x001f, B:13:0x0029, B:20:0x0053, B:23:0x005c, B:25:0x0062, B:27:0x006f, B:28:0x0078, B:31:0x007e, B:34:0x008a, B:36:0x0090, B:39:0x0096, B:40:0x009a, B:42:0x00a1, B:43:0x00a2, B:46:0x00a8, B:54:0x0048, B:56:0x00b5, B:57:0x00bc), top: B:2:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0090 A[Catch: all -> 0x00bd, TryCatch #0 {all -> 0x00bd, blocks: (B:3:0x0002, B:5:0x000c, B:11:0x001f, B:13:0x0029, B:20:0x0053, B:23:0x005c, B:25:0x0062, B:27:0x006f, B:28:0x0078, B:31:0x007e, B:34:0x008a, B:36:0x0090, B:39:0x0096, B:40:0x009a, B:42:0x00a1, B:43:0x00a2, B:46:0x00a8, B:54:0x0048, B:56:0x00b5, B:57:0x00bc), top: B:2:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0002 A[SYNTHETIC] */
    @Override // okhttp3.internal.connection.ExchangeFinder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.internal.connection.RealConnection find() {
        /*
            r8 = this;
            r0 = 0
            r1 = r0
        L2:
            java.util.concurrent.CopyOnWriteArrayList<okhttp3.internal.connection.RoutePlanner$Plan> r2 = r8.tcpConnectsInFlight     // Catch: java.lang.Throwable -> Lbd
            java.util.Collection r2 = (java.util.Collection) r2     // Catch: java.lang.Throwable -> Lbd
            boolean r2 = r2.isEmpty()     // Catch: java.lang.Throwable -> Lbd
            if (r2 == 0) goto L1f
            okhttp3.internal.connection.RoutePlanner r2 = r8.getRoutePlanner()     // Catch: java.lang.Throwable -> Lbd
            r3 = 1
            boolean r2 = okhttp3.internal.connection.RoutePlanner.hasNext$default(r2, r0, r3, r0)     // Catch: java.lang.Throwable -> Lbd
            if (r2 == 0) goto L18
            goto L1f
        L18:
            r8.cancelInFlightConnects()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            throw r1
        L1f:
            okhttp3.internal.connection.RoutePlanner r2 = r8.getRoutePlanner()     // Catch: java.lang.Throwable -> Lbd
            boolean r2 = r2.isCanceled()     // Catch: java.lang.Throwable -> Lbd
            if (r2 != 0) goto Lb5
            okhttp3.internal.concurrent.TaskRunner r2 = r8.taskRunner     // Catch: java.lang.Throwable -> Lbd
            okhttp3.internal.concurrent.TaskRunner$Backend r2 = r2.getBackend()     // Catch: java.lang.Throwable -> Lbd
            long r2 = r2.nanoTime()     // Catch: java.lang.Throwable -> Lbd
            long r4 = r8.nextTcpConnectAtNanos     // Catch: java.lang.Throwable -> Lbd
            long r4 = r4 - r2
            java.util.concurrent.CopyOnWriteArrayList<okhttp3.internal.connection.RoutePlanner$Plan> r6 = r8.tcpConnectsInFlight     // Catch: java.lang.Throwable -> Lbd
            boolean r6 = r6.isEmpty()     // Catch: java.lang.Throwable -> Lbd
            if (r6 != 0) goto L48
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 > 0) goto L45
            goto L48
        L45:
            r5 = r4
            r4 = r0
            goto L51
        L48:
            okhttp3.internal.connection.RoutePlanner$ConnectResult r4 = r8.launchTcpConnect()     // Catch: java.lang.Throwable -> Lbd
            long r5 = r8.connectDelayNanos     // Catch: java.lang.Throwable -> Lbd
            long r2 = r2 + r5
            r8.nextTcpConnectAtNanos = r2     // Catch: java.lang.Throwable -> Lbd
        L51:
            if (r4 != 0) goto L5c
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch: java.lang.Throwable -> Lbd
            okhttp3.internal.connection.RoutePlanner$ConnectResult r4 = r8.awaitTcpConnect(r5, r2)     // Catch: java.lang.Throwable -> Lbd
            if (r4 != 0) goto L5c
            goto L2
        L5c:
            boolean r2 = r4.isSuccess()     // Catch: java.lang.Throwable -> Lbd
            if (r2 == 0) goto L8a
            r8.cancelInFlightConnects()     // Catch: java.lang.Throwable -> Lbd
            okhttp3.internal.connection.RoutePlanner$Plan r2 = r4.getPlan()     // Catch: java.lang.Throwable -> Lbd
            boolean r2 = r2.getIsReady()     // Catch: java.lang.Throwable -> Lbd
            if (r2 != 0) goto L78
            okhttp3.internal.connection.RoutePlanner$Plan r2 = r4.getPlan()     // Catch: java.lang.Throwable -> Lbd
            okhttp3.internal.connection.RoutePlanner$ConnectResult r2 = r2.mo5304connectTlsEtc()     // Catch: java.lang.Throwable -> Lbd
            r4 = r2
        L78:
            boolean r2 = r4.isSuccess()     // Catch: java.lang.Throwable -> Lbd
            if (r2 == 0) goto L8a
            okhttp3.internal.connection.RoutePlanner$Plan r0 = r4.getPlan()     // Catch: java.lang.Throwable -> Lbd
            okhttp3.internal.connection.RealConnection r0 = r0.mo5300handleSuccess()     // Catch: java.lang.Throwable -> Lbd
            r8.cancelInFlightConnects()
            return r0
        L8a:
            java.lang.Throwable r2 = r4.getThrowable()     // Catch: java.lang.Throwable -> Lbd
            if (r2 == 0) goto La2
            boolean r3 = r2 instanceof java.io.IOException     // Catch: java.lang.Throwable -> Lbd
            if (r3 == 0) goto La1
            if (r1 != 0) goto L9a
            java.io.IOException r2 = (java.io.IOException) r2     // Catch: java.lang.Throwable -> Lbd
            r1 = r2
            goto La2
        L9a:
            r3 = r1
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch: java.lang.Throwable -> Lbd
            kotlin.ExceptionsKt.addSuppressed(r3, r2)     // Catch: java.lang.Throwable -> Lbd
            goto La2
        La1:
            throw r2     // Catch: java.lang.Throwable -> Lbd
        La2:
            okhttp3.internal.connection.RoutePlanner$Plan r2 = r4.getNextPlan()     // Catch: java.lang.Throwable -> Lbd
            if (r2 == 0) goto L2
            okhttp3.internal.connection.RoutePlanner r3 = r8.getRoutePlanner()     // Catch: java.lang.Throwable -> Lbd
            kotlin.collections.ArrayDeque r3 = r3.getDeferredPlans()     // Catch: java.lang.Throwable -> Lbd
            r3.addFirst(r2)     // Catch: java.lang.Throwable -> Lbd
            goto L2
        Lb5:
            java.io.IOException r0 = new java.io.IOException     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r1 = "Canceled"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> Lbd
            throw r0     // Catch: java.lang.Throwable -> Lbd
        Lbd:
            r0 = move-exception
            r8.cancelInFlightConnects()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.FastFallbackExchangeFinder.find():okhttp3.internal.connection.RealConnection");
    }

    private final RoutePlanner.ConnectResult launchTcpConnect() {
        FailedPlan failedPlan;
        if (RoutePlanner.hasNext$default(getRoutePlanner(), null, 1, null)) {
            try {
                failedPlan = getRoutePlanner().plan();
            } catch (Throwable th) {
                failedPlan = new FailedPlan(th);
            }
            final RoutePlanner.Plan plan = failedPlan;
            if (plan.getIsReady()) {
                return new RoutePlanner.ConnectResult(plan, null, null, 6, null);
            }
            if (plan instanceof FailedPlan) {
                return ((FailedPlan) plan).getResult();
            }
            this.tcpConnectsInFlight.add(plan);
            final String str = _UtilJvmKt.okHttpName + " connect " + getRoutePlanner().getAddress().url().redact();
            TaskQueue.schedule$default(this.taskRunner.newQueue(), new Task(str) { // from class: okhttp3.internal.connection.FastFallbackExchangeFinder$launchTcpConnect$1
                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    RoutePlanner.ConnectResult connectResult;
                    CopyOnWriteArrayList copyOnWriteArrayList;
                    BlockingQueue blockingQueue;
                    try {
                        connectResult = plan.getResult();
                    } catch (Throwable th2) {
                        connectResult = new RoutePlanner.ConnectResult(plan, null, th2, 2, null);
                    }
                    copyOnWriteArrayList = this.tcpConnectsInFlight;
                    if (!copyOnWriteArrayList.contains(plan)) {
                        return -1L;
                    }
                    blockingQueue = this.connectResults;
                    blockingQueue.put(connectResult);
                    return -1L;
                }
            }, 0L, 2, null);
        }
        return null;
    }

    private final RoutePlanner.ConnectResult awaitTcpConnect(long timeout, TimeUnit unit) {
        RoutePlanner.ConnectResult poll;
        if (this.tcpConnectsInFlight.isEmpty() || (poll = this.connectResults.poll(timeout, unit)) == null) {
            return null;
        }
        this.tcpConnectsInFlight.remove(poll.getPlan());
        return poll;
    }

    private final void cancelInFlightConnects() {
        Iterator<RoutePlanner.Plan> it = this.tcpConnectsInFlight.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            RoutePlanner.Plan next = it.next();
            next.mo5299cancel();
            RoutePlanner.Plan mo5301retry = next.mo5301retry();
            if (mo5301retry != null) {
                getRoutePlanner().getDeferredPlans().addLast(mo5301retry);
            }
        }
        this.tcpConnectsInFlight.clear();
    }
}
