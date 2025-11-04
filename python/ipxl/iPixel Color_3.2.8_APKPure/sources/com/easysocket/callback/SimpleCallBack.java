package com.easysocket.callback;

/* loaded from: classes2.dex */
public abstract class SimpleCallBack<T> extends SuperCallBack<T> {
    @Override // com.easysocket.callback.SuperCallBack
    public void onCompleted() {
    }

    @Override // com.easysocket.callback.SuperCallBack
    public void onError(Exception exc) {
    }

    @Override // com.easysocket.callback.SuperCallBack
    public void onStart() {
    }

    public SimpleCallBack(String str) {
        super(str);
    }
}
