package com.heaton.baselib.app.event;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes2.dex */
public class MainThreadManager {
    private final Object mLock;
    private volatile Handler mMainHandler;

    private static class SingletonHolder {
        private static final MainThreadManager INSTANCE = new MainThreadManager();

        private SingletonHolder() {
        }
    }

    public static MainThreadManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private MainThreadManager() {
        this.mLock = new Object();
    }

    public void postToMainThread(Runnable runnable) {
        if (this.mMainHandler == null) {
            synchronized (this.mLock) {
                if (this.mMainHandler == null) {
                    this.mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.mMainHandler.post(runnable);
    }

    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
