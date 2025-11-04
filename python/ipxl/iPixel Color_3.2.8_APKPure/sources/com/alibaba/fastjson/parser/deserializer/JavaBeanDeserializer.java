package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson2.reader.ObjectReader;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    final ObjectReader objectReader;

    /* JADX WARN: Multi-variable type inference failed */
    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this.objectReader = (parserConfig == null ? ParserConfig.global : parserConfig).getProvider().getObjectReader(type != 0 ? type : cls);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) this.objectReader.readObject(defaultJSONParser.getRawReader(), type, obj, 0L);
    }
}
