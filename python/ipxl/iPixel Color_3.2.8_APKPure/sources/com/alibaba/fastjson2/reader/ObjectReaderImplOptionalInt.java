package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import java.lang.reflect.Type;
import java.util.OptionalInt;

/* loaded from: classes2.dex */
final class ObjectReaderImplOptionalInt extends ObjectReaderPrimitive {
    static final ObjectReaderImplOptionalInt INSTANCE = new ObjectReaderImplOptionalInt();

    public ObjectReaderImplOptionalInt() {
        super(OptionalInt.class);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Integer readInt32 = jSONReader.readInt32();
        if (readInt32 == null) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(readInt32.intValue());
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Integer readInt32 = jSONReader.readInt32();
        if (readInt32 == null) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(readInt32.intValue());
    }
}
