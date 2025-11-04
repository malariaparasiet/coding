package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import java.lang.reflect.Type;
import java.util.OptionalLong;

/* loaded from: classes2.dex */
class ObjectReaderImplOptionalLong extends ObjectReaderPrimitive {
    static final ObjectReaderImplOptionalLong INSTANCE = new ObjectReaderImplOptionalLong();

    public ObjectReaderImplOptionalLong() {
        super(OptionalLong.class);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Long readInt64 = jSONReader.readInt64();
        if (readInt64 == null) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(readInt64.longValue());
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Long readInt64 = jSONReader.readInt64();
        if (readInt64 == null) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(readInt64.longValue());
    }
}
