package com.wifiled.baselib.callback;

/* loaded from: classes2.dex */
public abstract class CallBackUI<T> {
    public abstract void callBackUI(T t);

    public abstract T execute();

    public void onPreExecute() {
    }
}
