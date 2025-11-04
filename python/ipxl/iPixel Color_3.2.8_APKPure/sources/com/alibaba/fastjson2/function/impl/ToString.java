package com.alibaba.fastjson2.function.impl;

import java.util.function.Function;

/* loaded from: classes2.dex */
public class ToString implements Function {
    @Override // java.util.function.Function
    public Object apply(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }
}
