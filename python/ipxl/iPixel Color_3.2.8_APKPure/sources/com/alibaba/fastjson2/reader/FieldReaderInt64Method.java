package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Method;
import java.util.Locale;

/* loaded from: classes2.dex */
final class FieldReaderInt64Method<T> extends FieldReaderObject<T> {
    FieldReaderInt64Method(String str, int i, long j, String str2, Locale locale, Long l, JSONSchema jSONSchema, Method method) {
        super(str, Long.class, Long.class, i, j, str2, locale, l, jSONSchema, method, null, null);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        Long readInt64 = jSONReader.readInt64();
        if (this.schema != null) {
            this.schema.assertValidate(readInt64);
        }
        try {
            this.method.invoke(t, readInt64);
        } catch (Exception e) {
            throw new JSONException(jSONReader.info("set " + this.fieldName + " error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValueJSONB(JSONReader jSONReader, T t) {
        Long readInt64 = jSONReader.readInt64();
        if (this.schema != null) {
            this.schema.assertValidate(readInt64);
        }
        try {
            this.method.invoke(t, readInt64);
        } catch (Exception e) {
            throw new JSONException(jSONReader.info("set " + this.fieldName + " error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        Long l = TypeUtils.toLong(obj);
        if (this.schema != null) {
            this.schema.assertValidate(l);
        }
        try {
            this.method.invoke(t, l);
        } catch (Exception e) {
            throw new JSONException("set " + this.fieldName + " error", e);
        }
    }
}
