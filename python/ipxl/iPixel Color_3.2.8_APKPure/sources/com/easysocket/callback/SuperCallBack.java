package com.easysocket.callback;

import com.easysocket.interfaces.callback.IType;
import com.easysocket.utils.Util;
import com.google.gson.Gson;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public abstract class SuperCallBack<T> implements IType<T> {
    private String callbackId;

    public abstract void onCompleted();

    public abstract void onError(Exception exc);

    public abstract void onResponse(T t);

    public abstract void onStart();

    public SuperCallBack(String str) {
        this.callbackId = str;
    }

    public String getCallbackId() {
        return this.callbackId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onSuccess(String str) {
        onCompleted();
        Class<?> genericityClazz = getGenericityClazz();
        if (genericityClazz.equals(String.class)) {
            onResponse(str);
        } else {
            onResponse(new Gson().fromJson(str, (Class) genericityClazz));
        }
    }

    @Override // com.easysocket.interfaces.callback.IType
    public Type getType() {
        return Util.findGenericityType(getClass());
    }

    @Override // com.easysocket.interfaces.callback.IType
    public Class<?> getGenericityClazz() {
        return (Class) getType();
    }
}
