package com.easysocket.utils;

import androidx.lifecycle.CoroutineLiveDataKt;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class ThreadPoolUtil {
    private Map futures = new HashMap();
    private int period = 5;
    private String taskPrefix = "DoSomething-Task";
    private static BlockingQueue blockingQueue = new ArrayBlockingQueue(1000);
    private static ScheduledExecutorService scheduleExcutor = Executors.newScheduledThreadPool(20);
    private static ThreadFactory threadFactory = new ThreadFactory() { // from class: com.easysocket.utils.ThreadPoolUtil.1
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

    public static ScheduledExecutorService getScheExeSer() {
        if (scheduleExcutor == null) {
            scheduleExcutor = Executors.newScheduledThreadPool(20);
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

    public static void scheduleWithFixedDelay(Runnable runnable, int i) {
        getScheExeSer();
        scheduleExcutor.scheduleWithFixedDelay(runnable, 0L, CoroutineLiveDataKt.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public static void shutDown() {
        getScheExeSer().shutdownNow();
    }
}
