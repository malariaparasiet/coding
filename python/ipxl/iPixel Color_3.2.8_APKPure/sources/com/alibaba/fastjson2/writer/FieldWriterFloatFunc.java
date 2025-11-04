package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class FieldWriterFloatFunc<T> extends FieldWriter<T> {
    final Function<T, Float> function;

    FieldWriterFloatFunc(String str, int i, long j, String str2, String str3, Field field, Method method, Function<T, Float> function) {
        super(str, i, j, str2, null, str3, Float.class, Float.class, field, method);
        this.function = function;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public Object getFieldValue(T t) {
        return this.function.apply(t);
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public void writeValue(JSONWriter jSONWriter, T t) {
        Float apply = this.function.apply(t);
        if (apply == null) {
            jSONWriter.writeNumberNull();
            return;
        }
        float floatValue = apply.floatValue();
        if (this.decimalFormat != null) {
            jSONWriter.writeFloat(floatValue, this.decimalFormat);
        } else {
            jSONWriter.writeFloat(floatValue);
        }
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public boolean write(JSONWriter jSONWriter, T t) {
        try {
            Float apply = this.function.apply(t);
            if (apply == null) {
                long features = jSONWriter.getFeatures(this.features);
                if (((JSONWriter.Feature.WriteNulls.mask | JSONWriter.Feature.NullAsDefaultValue.mask) & features) == 0 || (features & JSONWriter.Feature.NotWriteDefaultValue.mask) != 0) {
                    return false;
                }
                writeFieldName(jSONWriter);
                jSONWriter.writeDecimalNull();
                return true;
            }
            writeFieldName(jSONWriter);
            float floatValue = apply.floatValue();
            if (this.decimalFormat != null) {
                jSONWriter.writeFloat(floatValue, this.decimalFormat);
            } else {
                jSONWriter.writeFloat(floatValue);
            }
            return true;
        } catch (RuntimeException e) {
            if (jSONWriter.isIgnoreErrorGetter()) {
                return false;
            }
            throw e;
        }
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public Function getFunction() {
        return this.function;
    }
}
