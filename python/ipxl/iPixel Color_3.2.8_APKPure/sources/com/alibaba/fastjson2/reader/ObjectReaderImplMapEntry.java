package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Map;

/* loaded from: classes2.dex */
class ObjectReaderImplMapEntry extends ObjectReaderPrimitive {
    volatile ObjectReader keyReader;
    final Type keyType;
    volatile ObjectReader valueReader;
    final Type valueType;

    public ObjectReaderImplMapEntry(Type type, Type type2) {
        super(Map.Entry.class);
        this.keyType = type;
        this.valueType = type2;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        JSONReader jSONReader2;
        Type type2;
        Object obj2;
        long j2;
        Object readObject;
        Object readObject2;
        int startArray = jSONReader.startArray();
        if (startArray != 2) {
            throw new JSONException(jSONReader.info("entryCnt must be 2, but " + startArray));
        }
        if (this.keyType == null) {
            readObject = jSONReader.readAny();
            jSONReader2 = jSONReader;
            type2 = type;
            obj2 = obj;
            j2 = j;
        } else {
            if (this.keyReader == null) {
                this.keyReader = jSONReader.getObjectReader(this.keyType);
            }
            jSONReader2 = jSONReader;
            type2 = type;
            obj2 = obj;
            j2 = j;
            readObject = this.keyReader.readObject(jSONReader2, type2, obj2, j2);
        }
        if (this.valueType == null) {
            readObject2 = jSONReader2.readAny();
        } else {
            if (this.valueReader == null) {
                this.valueReader = jSONReader2.getObjectReader(this.valueType);
            }
            readObject2 = this.valueReader.readObject(jSONReader2, type2, obj2, j2);
        }
        return new AbstractMap.SimpleEntry(readObject, readObject2);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        JSONReader jSONReader2;
        Object readObject;
        jSONReader.nextIfObjectStart();
        Object readAny = jSONReader.readAny();
        jSONReader.nextIfMatch(':');
        if (this.valueType == null) {
            readObject = jSONReader.readAny();
            jSONReader2 = jSONReader;
        } else {
            if (this.valueReader == null) {
                this.valueReader = jSONReader.getObjectReader(this.valueType);
            }
            jSONReader2 = jSONReader;
            readObject = this.valueReader.readObject(jSONReader2, type, obj, j);
        }
        jSONReader2.nextIfObjectEnd();
        jSONReader2.nextIfComma();
        return new AbstractMap.SimpleEntry(readAny, readObject);
    }
}
