package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.function.ObjShortConsumer;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Method;
import java.util.Locale;

/* loaded from: classes2.dex */
final class FieldReaderInt16ValueFunc<T> extends FieldReader<T> {
    final ObjShortConsumer<T> function;

    public FieldReaderInt16ValueFunc(String str, int i, long j, String str2, Locale locale, Short sh, JSONSchema jSONSchema, Method method, ObjShortConsumer<T> objShortConsumer) {
        super(str, Short.TYPE, Short.TYPE, i, j, str2, locale, sh, jSONSchema, method, null);
        this.function = objShortConsumer;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, short s) {
        if (this.schema != null) {
            this.schema.assertValidate(s);
        }
        this.function.accept(t, s);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        short shortValue = TypeUtils.toShortValue(obj);
        if (this.schema != null) {
            this.schema.assertValidate(shortValue);
        }
        this.function.accept(t, shortValue);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        short readInt32Value = (short) jSONReader.readInt32Value();
        if (this.schema != null) {
            this.schema.assertValidate(readInt32Value);
        }
        this.function.accept(t, readInt32Value);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        return Short.valueOf((short) jSONReader.readInt32Value());
    }
}
