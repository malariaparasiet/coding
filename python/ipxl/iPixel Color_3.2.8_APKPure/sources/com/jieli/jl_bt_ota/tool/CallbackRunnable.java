package com.jieli.jl_bt_ota.tool;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public class CallbackRunnable<T> implements Runnable {
    private final ArrayList<T> a;
    private final ICallbackHandler<T> b;

    public CallbackRunnable(ArrayList<T> arrayList, ICallbackHandler<T> iCallbackHandler) {
        this.a = arrayList;
        this.b = iCallbackHandler;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        ArrayList<T> arrayList = this.a;
        if (arrayList == null || arrayList.isEmpty() || this.b == null) {
            return;
        }
        ArrayList arrayList2 = new ArrayList(this.a);
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            this.b.onHandle(obj);
        }
    }
}
