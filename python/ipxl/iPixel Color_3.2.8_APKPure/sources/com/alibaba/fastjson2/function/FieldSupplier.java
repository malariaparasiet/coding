package com.alibaba.fastjson2.function;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface FieldSupplier<T> {
    Object get(T t, int i);
}
