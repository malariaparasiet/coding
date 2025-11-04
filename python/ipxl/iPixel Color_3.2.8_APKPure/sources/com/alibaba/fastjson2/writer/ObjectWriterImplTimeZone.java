package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Type;
import java.util.TimeZone;

/* loaded from: classes2.dex */
final class ObjectWriterImplTimeZone extends ObjectWriterPrimitiveImpl {
    static final ObjectWriterImplTimeZone INSTANCE = new ObjectWriterImplTimeZone();

    ObjectWriterImplTimeZone() {
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        if (obj == null) {
            jSONWriter.writeNull();
        } else {
            jSONWriter.writeString(((TimeZone) obj).getID());
        }
    }
}
