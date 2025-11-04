package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
class FieldReaderInt32ValueField<T> extends FieldReaderObjectField<T> {
    FieldReaderInt32ValueField(String str, Class cls, int i, String str2, Integer num, JSONSchema jSONSchema, Field field) {
        super(str, cls, cls, i, 0L, str2, null, num, jSONSchema, field);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        int readInt32Value = jSONReader.readInt32Value();
        if (this.schema != null) {
            this.schema.assertValidate(readInt32Value);
        }
        JDKUtils.UNSAFE.putInt(t, this.fieldOffset, readInt32Value);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValueJSONB(JSONReader jSONReader, T t) {
        accept((FieldReaderInt32ValueField<T>) t, jSONReader.readInt32Value());
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObjectField, com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, float f) {
        accept((FieldReaderInt32ValueField<T>) t, Integer.valueOf((int) f));
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObjectField, com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, double d) {
        accept((FieldReaderInt32ValueField<T>) t, Integer.valueOf((int) d));
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObjectField, com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        int intValue = TypeUtils.toIntValue(obj);
        if (this.schema != null) {
            this.schema.assertValidate(intValue);
        }
        JDKUtils.UNSAFE.putInt(t, this.fieldOffset, intValue);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObjectField, com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, long j) {
        if (this.schema != null) {
            this.schema.assertValidate(j);
        }
        JDKUtils.UNSAFE.putInt(t, this.fieldOffset, (int) j);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        return Integer.valueOf(jSONReader.readInt32Value());
    }
}
