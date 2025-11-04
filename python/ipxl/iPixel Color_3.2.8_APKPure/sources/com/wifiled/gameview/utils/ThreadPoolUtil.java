package com.wifiled.gameview.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class ThreadPoolUtil {
    Callable<Runnable> call = new Callable() { // from class: com.wifiled.gameview.utils.ThreadPoolUtil$$ExternalSyntheticLambda0
        @Override // java.util.concurrent.Callable
        public final Object call() {
            return ThreadPoolUtil.lambda$new$1();
        }
    };
    FutureTask<Runnable> task = new FutureTask<>(this.call);
    private static BlockingQueue blockingQueue = new ArrayBlockingQueue(1000);
    private static ScheduledExecutorService scheduleExcutor = Executors.newScheduledThreadPool(2);
    private static ThreadFactory threadFactory = new ThreadFactory() { // from class: com.wifiled.gameview.utils.ThreadPoolUtil.1
        private final AtomicInteger integer = new AtomicInteger();

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "myThreadPool thread:" + this.integer.getAndIncrement());
        }
    };
    private static int CORE_POOL_SIZE = 10;
    private static int MAX_POOL_SIZE = 20;
    private static int KEEP_ALIVE_TIME = 10000;
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, (BlockingQueue<Runnable>) blockingQueue, threadFactory);

    static /* synthetic */ void lambda$new$0() {
    }

    public static ScheduledExecutorService getScheExeSer() {
        if (scheduleExcutor == null) {
            scheduleExcutor = Executors.newScheduledThreadPool(1);
        }
        return scheduleExcutor;
    }

    public static void endScheExeSer() {
        scheduleExcutor = null;
    }

    public static void execute(Runnable runnable) {
        threadPool.execute(runnable);
    }

    public static void execute(FutureTask futureTask) {
        threadPool.execute(futureTask);
    }

    public static void cancel(FutureTask futureTask) {
        futureTask.cancel(true);
    }

    public static void scheduleAtFixedRate(Runnable runnable, int i) {
        getScheExeSer();
        scheduleExcutor.scheduleAtFixedRate(runnable, 1L, i, TimeUnit.SECONDS);
    }

    static /* synthetic */ Runnable lambda$new$1() throws Exception {
        return new Runnable() { // from class: com.wifiled.gameview.utils.ThreadPoolUtil$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ThreadPoolUtil.lambda$new$0();
            }
        };
    }

    public FutureTask<Runnable> getTask() {
        return this.task;
    }
}
