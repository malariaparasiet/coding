package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Locale;

/* loaded from: classes2.dex */
final class FieldReaderInt16ValueMethod<T> extends FieldReaderObject<T> {
    FieldReaderInt16ValueMethod(String str, Type type, Class cls, int i, long j, String str2, Locale locale, Short sh, JSONSchema jSONSchema, Method method) {
        super(str, type, cls, i, j, str2, locale, sh, jSONSchema, method, null, null);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        int readInt32Value = jSONReader.readInt32Value();
        if (this.schema != null) {
            this.schema.assertValidate(readInt32Value);
        }
        try {
            this.method.invoke(t, Short.valueOf((short) readInt32Value));
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
            this.method.invoke(t, Short.valueOf((short) readInt32Value));
        } catch (Exception e) {
            throw new JSONException(jSONReader.info("set " + this.fieldName + " error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        short shortValue = TypeUtils.toShortValue(obj);
        if (this.schema != null) {
            this.schema.assertValidate(shortValue);
        }
        try {
            this.method.invoke(t, Short.valueOf(shortValue));
        } catch (Exception e) {
            throw new JSONException("set " + this.fieldName + " error", e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        return Integer.valueOf(jSONReader.readInt32Value());
    }
}
