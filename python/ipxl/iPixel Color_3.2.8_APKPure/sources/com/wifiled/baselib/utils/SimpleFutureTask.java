package com.wifiled.baselib.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* loaded from: classes2.dex */
public abstract class SimpleFutureTask<T> extends FutureTask<T> {
    public abstract void onFinish();

    public SimpleFutureTask(Callable<T> callable) {
        super(callable);
    }

    @Override // java.util.concurrent.FutureTask
    protected void done() {
        onFinish();
    }
}
