package com.alibaba.fastjson.parser;

import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public interface ObjectDeserializer {
    @Deprecated
    <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj);

    @Deprecated
    default int getFastMatchToken() {
        return 0;
    }
}
