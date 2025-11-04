package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class FieldWriterDoubleFunc<T> extends FieldWriter<T> {
    final Function<T, Double> function;

    FieldWriterDoubleFunc(String str, int i, long j, String str2, String str3, Field field, Method method, Function<T, Double> function) {
        super(str, i, j, str2, null, str3, Double.class, Double.class, field, method);
        this.function = function;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public Object getFieldValue(T t) {
        return this.function.apply(t);
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public void writeValue(JSONWriter jSONWriter, T t) {
        Double apply = this.function.apply(t);
        if (apply == null) {
            jSONWriter.writeNumberNull();
            return;
        }
        double doubleValue = apply.doubleValue();
        if (this.decimalFormat != null) {
            jSONWriter.writeDouble(doubleValue, this.decimalFormat);
        } else {
            jSONWriter.writeDouble(doubleValue);
        }
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public boolean write(JSONWriter jSONWriter, T t) {
        try {
            Double apply = this.function.apply(t);
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
            double doubleValue = apply.doubleValue();
            if (this.decimalFormat != null) {
                jSONWriter.writeDouble(doubleValue, this.decimalFormat);
            } else {
                jSONWriter.writeDouble(doubleValue);
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
