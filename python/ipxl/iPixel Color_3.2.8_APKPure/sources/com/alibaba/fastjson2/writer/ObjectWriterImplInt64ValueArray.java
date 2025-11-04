package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class ObjectWriterImplInt64ValueArray extends ObjectWriterPrimitiveImpl {
    private final Function<Object, long[]> function;
    static final ObjectWriterImplInt64ValueArray INSTANCE = new ObjectWriterImplInt64ValueArray(null);
    static final byte[] JSONB_TYPE_NAME_BYTES = JSONB.toBytes("[J");
    static final long JSONB_TYPE_HASH = Fnv.hashCode64("[J");

    public ObjectWriterImplInt64ValueArray(Function<Object, long[]> function) {
        this.function = function;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeJSONB(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        long[] jArr;
        if (obj == null) {
            jSONWriter.writeNull();
            return;
        }
        if (jSONWriter.isWriteTypeInfo(obj, type)) {
            jSONWriter.writeTypeName(JSONB_TYPE_NAME_BYTES, JSONB_TYPE_HASH);
        }
        Function<Object, long[]> function = this.function;
        if (function != null) {
            jArr = function.apply(obj);
        } else {
            jArr = (long[]) obj;
        }
        if ((JSONWriter.Feature.WriteNonStringValueAsString.mask & j) != 0) {
            jSONWriter.writeString(jArr);
        } else {
            jSONWriter.writeInt64(jArr);
        }
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        long[] jArr;
        if (obj == null) {
            jSONWriter.writeNull();
            return;
        }
        ObjectWriter objectWriter = (jSONWriter.context.provider.userDefineMask & 4) != 0 ? jSONWriter.context.getObjectWriter(Long.class) : null;
        Function<Object, long[]> function = this.function;
        if (function != null) {
            jArr = function.apply(obj);
        } else {
            jArr = (long[]) obj;
        }
        if (objectWriter == null || objectWriter == ObjectWriterImplInt32.INSTANCE) {
            jSONWriter.writeInt64(jArr);
            return;
        }
        jSONWriter.startArray();
        for (int i = 0; i < jArr.length; i++) {
            if (i != 0) {
                jSONWriter.writeComma();
            }
            objectWriter.write(jSONWriter, Long.valueOf(jArr[i]), Integer.valueOf(i), Long.TYPE, j);
        }
        jSONWriter.endArray();
    }
}
