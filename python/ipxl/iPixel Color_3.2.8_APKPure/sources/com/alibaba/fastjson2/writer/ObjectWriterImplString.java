package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
final class ObjectWriterImplString extends ObjectWriterPrimitiveImpl {
    static final ObjectWriterImplString INSTANCE = new ObjectWriterImplString();

    ObjectWriterImplString() {
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeJSONB(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        jSONWriter.writeString((String) obj);
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        jSONWriter.writeString((String) obj);
    }
}
