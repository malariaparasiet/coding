package com.easysocket.interfaces.io;

/* loaded from: classes2.dex */
public interface IReader<T> {
    void closeReader();

    void openReader();

    void read();

    void setOption(T t);
}
