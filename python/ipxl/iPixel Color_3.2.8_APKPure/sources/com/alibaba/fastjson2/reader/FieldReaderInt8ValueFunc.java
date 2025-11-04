package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.function.ObjByteConsumer;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
final class FieldReaderInt8ValueFunc<T> extends FieldReader<T> {
    final ObjByteConsumer<T> function;

    public FieldReaderInt8ValueFunc(String str, int i, JSONSchema jSONSchema, Method method, ObjByteConsumer<T> objByteConsumer) {
        super(str, Byte.TYPE, Byte.TYPE, i, 0L, null, null, null, jSONSchema, method, null);
        this.function = objByteConsumer;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, byte b) {
        if (this.schema != null) {
            this.schema.assertValidate(b);
        }
        this.function.accept(t, b);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        byte byteValue = TypeUtils.toByteValue(obj);
        if (this.schema != null) {
            this.schema.assertValidate(byteValue);
        }
        this.function.accept(t, byteValue);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        byte readInt32Value = (byte) jSONReader.readInt32Value();
        if (this.schema != null) {
            this.schema.assertValidate(readInt32Value);
        }
        this.function.accept(t, readInt32Value);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        return Byte.valueOf((byte) jSONReader.readInt32Value());
    }
}
