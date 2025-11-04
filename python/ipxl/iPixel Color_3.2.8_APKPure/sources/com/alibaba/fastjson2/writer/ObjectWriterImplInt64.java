package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Type;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
final class ObjectWriterImplInt64 extends ObjectWriterPrimitiveImpl {
    static final ObjectWriterImplInt64 INSTANCE = new ObjectWriterImplInt64(null);
    final Class defineClass;

    public ObjectWriterImplInt64(Class cls) {
        this.defineClass = cls;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeJSONB(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        if (obj == null) {
            jSONWriter.writeNumberNull();
            return;
        }
        long longValue = ((Long) obj).longValue();
        if ((j & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0) {
            jSONWriter.writeString(longValue);
        } else {
            jSONWriter.writeInt64(longValue);
        }
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        if (obj == null) {
            jSONWriter.writeNumberNull();
            return;
        }
        long longValue = ((Number) obj).longValue();
        if ((JSONWriter.Feature.WriteNonStringValueAsString.mask & j) != 0) {
            jSONWriter.writeString(longValue);
            return;
        }
        jSONWriter.writeInt64(longValue);
        if (longValue < -2147483648L || longValue > 2147483647L || (JSONWriter.Feature.WriteClassName.mask & j) == 0) {
            return;
        }
        long features = jSONWriter.getFeatures();
        if ((JSONWriter.Feature.WriteClassName.mask & features) == 0 && (features & (JSONWriter.Feature.WriteNonStringValueAsString.mask | JSONWriter.Feature.WriteLongAsString.mask)) == 0) {
            jSONWriter.writeRaw(Matrix.MATRIX_TYPE_RANDOM_LT);
        }
    }
}
