package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.ToDoubleFunction;

/* loaded from: classes2.dex */
final class FieldWriterDoubleValueFunc extends FieldWriter {
    final ToDoubleFunction function;

    FieldWriterDoubleValueFunc(String str, int i, long j, String str2, String str3, Field field, Method method, ToDoubleFunction toDoubleFunction) {
        super(str, i, j, str2, null, str3, Double.TYPE, Double.TYPE, field, method);
        this.function = toDoubleFunction;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public Object getFieldValue(Object obj) {
        return Double.valueOf(this.function.applyAsDouble(obj));
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public void writeValue(JSONWriter jSONWriter, Object obj) {
        double applyAsDouble = this.function.applyAsDouble(obj);
        if (this.decimalFormat != null) {
            jSONWriter.writeDouble(applyAsDouble, this.decimalFormat);
        } else {
            jSONWriter.writeDouble(applyAsDouble);
        }
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public boolean write(JSONWriter jSONWriter, Object obj) {
        try {
            double applyAsDouble = this.function.applyAsDouble(obj);
            writeFieldName(jSONWriter);
            if (this.decimalFormat != null) {
                jSONWriter.writeDouble(applyAsDouble, this.decimalFormat);
                return true;
            }
            jSONWriter.writeDouble(applyAsDouble);
            return true;
        } catch (RuntimeException e) {
            if (jSONWriter.isIgnoreErrorGetter()) {
                return false;
            }
            throw e;
        }
    }
}
