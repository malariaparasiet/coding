package com.alibaba.fastjson2.reader;

import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public interface ByteArrayValueConsumer {
    void accept(int i, int i2, byte[] bArr, int i3, int i4, Charset charset);

    default void afterRow(int i) {
    }

    default void beforeRow(int i) {
    }

    default void end() {
    }

    default void start() {
    }
}
