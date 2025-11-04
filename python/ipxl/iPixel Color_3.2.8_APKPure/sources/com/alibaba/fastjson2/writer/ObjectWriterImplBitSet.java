package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Type;
import java.util.BitSet;

/* loaded from: classes2.dex */
final class ObjectWriterImplBitSet extends ObjectWriterPrimitiveImpl {
    static final ObjectWriterImplBitSet INSTANCE = new ObjectWriterImplBitSet();

    ObjectWriterImplBitSet() {
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        if (obj == null) {
            jSONWriter.writeNull();
        } else {
            jSONWriter.writeBinary(((BitSet) obj).toByteArray());
        }
    }
}
