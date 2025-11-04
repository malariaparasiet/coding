package com.alibaba.fastjson2.filter;

import com.alibaba.fastjson2.JSONWriter;

/* loaded from: classes2.dex */
public abstract class AfterFilter implements Filter {
    private static final ThreadLocal<JSONWriter> writerLocal = new ThreadLocal<>();

    public abstract void writeAfter(Object obj);

    public void writeAfter(JSONWriter jSONWriter, Object obj) {
        ThreadLocal<JSONWriter> threadLocal = writerLocal;
        JSONWriter jSONWriter2 = threadLocal.get();
        threadLocal.set(jSONWriter);
        writeAfter(obj);
        threadLocal.set(jSONWriter2);
    }

    protected final void writeKeyValue(String str, Object obj) {
        JSONWriter jSONWriter = writerLocal.get();
        boolean containsReference = jSONWriter.containsReference(obj);
        jSONWriter.writeName(str);
        jSONWriter.writeColon();
        jSONWriter.writeAny(obj);
        if (containsReference) {
            return;
        }
        jSONWriter.removeReference(obj);
    }
}
