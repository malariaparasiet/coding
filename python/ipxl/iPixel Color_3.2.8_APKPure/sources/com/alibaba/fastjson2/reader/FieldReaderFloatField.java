package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
final class FieldReaderFloatField<T> extends FieldReaderObjectField<T> {
    FieldReaderFloatField(String str, Class cls, int i, long j, String str2, Float f, JSONSchema jSONSchema, Field field) {
        super(str, cls, cls, i, j, str2, null, f, jSONSchema, field);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        Float readFloat = jSONReader.readFloat();
        if (this.schema != null) {
            this.schema.assertValidate(readFloat);
        }
        try {
            this.field.set(t, readFloat);
        } catch (Exception e) {
            throw new JSONException(jSONReader.info("set " + this.fieldName + " error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        return jSONReader.readFloat();
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObjectField, com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        Float f = TypeUtils.toFloat(obj);
        if (this.schema != null) {
            this.schema.assertValidate(f);
        }
        try {
            this.field.set(t, f);
        } catch (Exception e) {
            throw new JSONException("set " + this.fieldName + " error", e);
        }
    }
}
