package com.wifiled.baselib.utils;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class TaskExecutor {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static Executor mParallelExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
    private static Executor mSerialExecutor = AsyncTask.SERIAL_EXECUTOR;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private TaskExecutor() {
        mParallelExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
        mSerialExecutor = AsyncTask.SERIAL_EXECUTOR;
    }

    public static void mainThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            sHandler.post(runnable);
        }
    }

    public static void mainThreadDelay(Runnable runnable, long j) {
        sHandler.postDelayed(runnable, j);
    }

    public static void executeTask(Runnable runnable) {
        executeTask(runnable, true);
    }

    public static void executeTaskSerially(Runnable runnable) {
        executeTask(runnable, false);
    }

    public static void executeTask(Runnable runnable, boolean z) {
        if (z) {
            mParallelExecutor.execute(runnable);
        } else {
            mSerialExecutor.execute(runnable);
        }
    }

    public static void submit(Callable callable) {
        executorService.submit(callable);
    }
}
