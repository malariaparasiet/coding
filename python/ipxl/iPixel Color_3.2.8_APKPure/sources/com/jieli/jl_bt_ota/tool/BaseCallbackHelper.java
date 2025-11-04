package com.jieli.jl_bt_ota.tool;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class BaseCallbackHelper<T> {
    protected final ArrayList<T> callbacks = new ArrayList<>();
    private final Handler a = new Handler(Looper.getMainLooper());

    public boolean addCallback(T t) {
        if (t == null) {
            return false;
        }
        boolean contains = this.callbacks.contains(t);
        return !contains ? this.callbacks.add(t) : contains;
    }

    protected void callbackEvent(ICallbackHandler<T> iCallbackHandler) {
        if (iCallbackHandler == null) {
            return;
        }
        CallbackRunnable callbackRunnable = new CallbackRunnable(this.callbacks, iCallbackHandler);
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            callbackRunnable.run();
        } else {
            this.a.post(callbackRunnable);
        }
    }

    public void release() {
        this.callbacks.clear();
        this.a.removeCallbacksAndMessages(null);
    }

    public boolean removeCallback(T t) {
        if (t == null || this.callbacks.isEmpty()) {
            return false;
        }
        return this.callbacks.remove(t);
    }
}
