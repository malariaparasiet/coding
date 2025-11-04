package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
final class FieldReaderCharValueField<T> extends FieldReaderObjectField<T> {
    FieldReaderCharValueField(String str, int i, long j, String str2, Character ch, JSONSchema jSONSchema, Field field) {
        super(str, Character.TYPE, Character.TYPE, i, j, str2, null, ch, jSONSchema, field);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        char readCharValue = jSONReader.readCharValue();
        if (readCharValue == 0 && jSONReader.wasNull()) {
            return;
        }
        accept((FieldReaderCharValueField<T>) t, readCharValue);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        String readString = jSONReader.readString();
        char c = 0;
        if (readString != null && !readString.isEmpty()) {
            c = readString.charAt(0);
        }
        return Character.valueOf(c);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObjectField, com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        char charValue;
        if (obj instanceof String) {
            charValue = ((String) obj).charAt(0);
        } else if (obj instanceof Character) {
            charValue = ((Character) obj).charValue();
        } else {
            throw new JSONException("cast to char error");
        }
        accept((FieldReaderCharValueField<T>) t, charValue);
    }
}
