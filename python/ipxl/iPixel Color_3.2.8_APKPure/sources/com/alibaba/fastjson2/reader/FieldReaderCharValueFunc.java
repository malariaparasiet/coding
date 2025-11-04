package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.function.ObjCharConsumer;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
final class FieldReaderCharValueFunc<T> extends FieldReader<T> {
    final ObjCharConsumer<T> function;

    FieldReaderCharValueFunc(String str, int i, String str2, Character ch, JSONSchema jSONSchema, Method method, ObjCharConsumer<T> objCharConsumer) {
        super(str, Character.TYPE, Character.TYPE, i, 0L, str2, null, ch, jSONSchema, method, null);
        this.function = objCharConsumer;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, char c) {
        this.function.accept(t, c);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        char charValue;
        if (obj instanceof String) {
            charValue = ((String) obj).charAt(0);
        } else if (obj instanceof Character) {
            charValue = ((Character) obj).charValue();
        } else {
            throw new JSONException("cast to char error");
        }
        accept((FieldReaderCharValueFunc<T>) t, charValue);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        char readCharValue = jSONReader.readCharValue();
        if (readCharValue == 0 && jSONReader.wasNull()) {
            return;
        }
        this.function.accept(t, readCharValue);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public String readFieldValue(JSONReader jSONReader) {
        return jSONReader.readString();
    }
}
