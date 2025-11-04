package com.alibaba.fastjson2.writer;

import androidx.camera.video.AudioStats;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.util.JDKUtils;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
class FieldWriterDoubleField<T> extends FieldWriter<T> {
    protected FieldWriterDoubleField(String str, int i, long j, String str2, String str3, Field field) {
        super(str, i, j, str2, null, str3, Double.class, Double.class, field, null);
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public Object getFieldValue(Object obj) {
        if (obj == null) {
            throw new JSONException("field.get error, " + this.fieldName);
        }
        try {
            if (this.fieldOffset != -1 && !this.fieldClass.isPrimitive()) {
                return JDKUtils.UNSAFE.getObject(obj, this.fieldOffset);
            }
            return this.field.get(obj);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new JSONException("field.get error, " + this.fieldName, e);
        }
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public boolean write(JSONWriter jSONWriter, T t) {
        Double d = (Double) getFieldValue(t);
        if (d == null) {
            long features = jSONWriter.getFeatures(this.features);
            if (((JSONWriter.Feature.WriteNulls.mask | JSONWriter.Feature.NullAsDefaultValue.mask) & features) == 0 || (JSONWriter.Feature.NotWriteDefaultValue.mask & features) != 0) {
                return false;
            }
            writeFieldName(jSONWriter);
            if ((features & JSONWriter.Feature.NullAsDefaultValue.mask) != 0) {
                jSONWriter.writeDouble(AudioStats.AUDIO_AMPLITUDE_NONE);
            } else {
                jSONWriter.writeNumberNull();
            }
            return true;
        }
        writeFieldName(jSONWriter);
        double doubleValue = d.doubleValue();
        if (this.decimalFormat != null) {
            jSONWriter.writeDouble(doubleValue, this.decimalFormat);
        } else {
            jSONWriter.writeDouble(doubleValue);
        }
        return true;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public void writeValue(JSONWriter jSONWriter, T t) {
        Double d = (Double) getFieldValue(t);
        if (d == null) {
            jSONWriter.writeNumberNull();
            return;
        }
        double doubleValue = d.doubleValue();
        if (this.decimalFormat != null) {
            jSONWriter.writeDouble(doubleValue, this.decimalFormat);
        } else {
            jSONWriter.writeDouble(doubleValue);
        }
    }
}
