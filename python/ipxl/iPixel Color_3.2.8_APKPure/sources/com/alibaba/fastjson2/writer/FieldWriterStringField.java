package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
final class FieldWriterStringField<T> extends FieldWriter<T> {
    FieldWriterStringField(String str, int i, long j, String str2, String str3, Field field) {
        super(str, i, j, str2, null, str3, String.class, String.class, field, null);
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public boolean write(JSONWriter jSONWriter, T t) {
        String str = (String) getFieldValue(t);
        long features = this.features | jSONWriter.getFeatures();
        if (str == null) {
            if (((JSONWriter.Feature.WriteNulls.mask | JSONWriter.Feature.NullAsDefaultValue.mask | JSONWriter.Feature.WriteNullStringAsEmpty.mask) & features) == 0 || (JSONWriter.Feature.NotWriteDefaultValue.mask & features) != 0) {
                return false;
            }
            writeFieldName(jSONWriter);
            if ((features & (JSONWriter.Feature.NullAsDefaultValue.mask | JSONWriter.Feature.WriteNullStringAsEmpty.mask)) != 0) {
                jSONWriter.writeString("");
            } else {
                jSONWriter.writeNull();
            }
            return true;
        }
        if (this.trim) {
            str = str.trim();
        }
        if (str.isEmpty() && (features & JSONWriter.Feature.IgnoreEmpty.mask) != 0) {
            return false;
        }
        writeFieldName(jSONWriter);
        if (this.symbol && jSONWriter.jsonb) {
            jSONWriter.writeSymbol(str);
        } else if (this.raw) {
            jSONWriter.writeRaw(str);
        } else {
            jSONWriter.writeString(str);
        }
        return true;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public void writeValue(JSONWriter jSONWriter, T t) {
        String str = (String) getFieldValue(t);
        if (str == null) {
            jSONWriter.writeNull();
            return;
        }
        if (this.trim) {
            str = str.trim();
        }
        if (this.raw) {
            jSONWriter.writeRaw(str);
        } else {
            jSONWriter.writeString(str);
        }
    }
}
