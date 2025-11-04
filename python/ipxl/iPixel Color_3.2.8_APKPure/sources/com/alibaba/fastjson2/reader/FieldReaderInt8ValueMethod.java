package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Locale;

/* loaded from: classes2.dex */
final class FieldReaderInt8ValueMethod<T> extends FieldReaderObject<T> {
    FieldReaderInt8ValueMethod(String str, Type type, Class cls, int i, long j, String str2, Locale locale, Byte b, JSONSchema jSONSchema, Method method) {
        super(str, type, cls, i, j, str2, locale, b, jSONSchema, method, null, null);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        int readInt32Value = jSONReader.readInt32Value();
        if (this.schema != null) {
            this.schema.assertValidate(readInt32Value);
        }
        try {
            this.method.invoke(t, Byte.valueOf((byte) readInt32Value));
        } catch (Exception e) {
            throw new JSONException(jSONReader.info("set " + this.fieldName + " error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValueJSONB(JSONReader jSONReader, T t) {
        int readInt32Value = jSONReader.readInt32Value();
        if (this.schema != null) {
            this.schema.assertValidate(readInt32Value);
        }
        try {
            this.method.invoke(t, Byte.valueOf((byte) readInt32Value));
        } catch (Exception e) {
            throw new JSONException(jSONReader.info("set " + this.fieldName + " error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        byte byteValue = TypeUtils.toByteValue(obj);
        if (this.schema != null) {
            this.schema.assertValidate(byteValue);
        }
        try {
            this.method.invoke(t, Byte.valueOf(byteValue));
        } catch (Exception e) {
            throw new JSONException("set " + this.fieldName + " error", e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, long j) {
        if (this.schema != null) {
            this.schema.assertValidate(j);
        }
        try {
            this.method.invoke(t, Byte.valueOf((byte) j));
        } catch (Exception e) {
            throw new JSONException("set " + this.fieldName + " error", e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        return Byte.valueOf((byte) jSONReader.readInt32Value());
    }
}
