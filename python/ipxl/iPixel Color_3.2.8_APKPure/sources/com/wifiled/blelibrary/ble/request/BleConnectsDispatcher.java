package com.wifiled.blelibrary.ble.request;

import com.wifiled.blelibrary.ble.BleHandler;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class BleConnectsDispatcher<T> implements Runnable {
    private static final long DEFALUT_DELAY = 2000;
    private NextCallback<T> callback;
    private List<T> connectDevices = new ArrayList();

    interface NextCallback<T> {
        void onNext(T t);
    }

    void excute(List<T> list, NextCallback<T> nextCallback) {
        this.callback = nextCallback;
        this.connectDevices.addAll(list);
        BleHandler.of().post(this);
    }

    int getLastSize() {
        return this.connectDevices.size();
    }

    boolean isContains(T t) {
        return this.connectDevices.contains(t);
    }

    void cancelAll() {
        this.connectDevices.clear();
    }

    void cancelOne(T t) {
        this.connectDevices.remove(t);
    }

    @Override // java.lang.Runnable
    public void run() {
        List<T> list = this.connectDevices;
        if (list != null && !list.isEmpty()) {
            NextCallback<T> nextCallback = this.callback;
            if (nextCallback != null) {
                nextCallback.onNext(this.connectDevices.get(0));
                this.connectDevices.remove(0);
                if (this.connectDevices.isEmpty()) {
                    return;
                }
                BleHandler.of().postDelayed(this, 2000L);
                return;
            }
            return;
        }
        this.callback = null;
    }
}
