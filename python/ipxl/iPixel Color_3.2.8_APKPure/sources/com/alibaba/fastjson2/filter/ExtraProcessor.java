package com.alibaba.fastjson2.filter;

import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public interface ExtraProcessor extends Filter {
    void processExtra(Object obj, String str, Object obj2);

    default Type getType(String str) {
        return Object.class;
    }
}
