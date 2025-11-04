package com.easysocket.interfaces.io;

import java.net.SocketException;

/* loaded from: classes2.dex */
public interface IWriter<T> {
    void closeWriter();

    void offer(byte[] bArr);

    void openWriter();

    void setOption(T t);

    void write(byte[] bArr) throws SocketException;
}
