package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.lang.reflect.Type;
import java.util.function.Function;

/* loaded from: classes2.dex */
public class ObjectReaderImplValueString<T> implements ObjectReader<T> {
    final long features;
    final Function<String, T> function;
    final JSONSchema schema;

    public ObjectReaderImplValueString(Class<T> cls, long j, JSONSchema jSONSchema, Function<String, T> function) {
        this.features = j;
        this.schema = jSONSchema;
        this.function = function;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public T readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        return readObject(jSONReader, type, obj, j);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public T readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.nextIfNullOrEmptyString()) {
            return null;
        }
        String readString = jSONReader.readString();
        JSONSchema jSONSchema = this.schema;
        if (jSONSchema != null) {
            jSONSchema.validate(readString);
        }
        try {
            return this.function.apply(readString);
        } catch (Exception e) {
            throw new JSONException(jSONReader.info("create object error"), e);
        }
    }

    public static <T> ObjectReaderImplValueString<T> of(Class<T> cls, Function<String, T> function) {
        return new ObjectReaderImplValueString<>(cls, 0L, null, function);
    }

    public static <T> ObjectReaderImplValueString<T> of(Class<T> cls, long j, JSONSchema jSONSchema, Function<String, T> function) {
        return new ObjectReaderImplValueString<>(cls, j, jSONSchema, function);
    }
}
