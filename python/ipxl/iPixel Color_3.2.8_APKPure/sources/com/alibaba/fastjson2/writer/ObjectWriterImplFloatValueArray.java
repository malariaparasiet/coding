package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class ObjectWriterImplFloatValueArray extends ObjectWriterPrimitiveImpl {
    private final DecimalFormat format;
    private final Function<Object, float[]> function;
    static final ObjectWriterImplFloatValueArray INSTANCE = new ObjectWriterImplFloatValueArray(null, null);
    static final byte[] JSONB_TYPE_NAME_BYTES = JSONB.toBytes("[F");
    static final long JSONB_TYPE_HASH = Fnv.hashCode64("[F");

    public ObjectWriterImplFloatValueArray(DecimalFormat decimalFormat) {
        this.format = decimalFormat;
        this.function = null;
    }

    public ObjectWriterImplFloatValueArray(Function<Object, float[]> function, DecimalFormat decimalFormat) {
        this.function = function;
        this.format = decimalFormat;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeJSONB(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        float[] fArr;
        if (jSONWriter.isWriteTypeInfo(obj, type)) {
            jSONWriter.writeTypeName(JSONB_TYPE_NAME_BYTES, JSONB_TYPE_HASH);
        }
        Function<Object, float[]> function = this.function;
        if (function != null && obj != null) {
            fArr = function.apply(obj);
        } else {
            fArr = (float[]) obj;
        }
        jSONWriter.writeFloat(fArr);
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        float[] fArr;
        Function<Object, float[]> function = this.function;
        if (function != null && obj != null) {
            fArr = function.apply(obj);
        } else {
            fArr = (float[]) obj;
        }
        DecimalFormat decimalFormat = this.format;
        if (decimalFormat == null) {
            jSONWriter.writeFloat(fArr);
        } else {
            jSONWriter.writeFloat(fArr, decimalFormat);
        }
    }
}
