package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;

/* loaded from: classes2.dex */
class ObjectReaderImplGenericArray implements ObjectReader {
    final Class arrayClass;
    final String arrayClassName;
    final long arrayClassNameHash;
    final Type arrayType;
    final Class<?> componentClass;
    ObjectReader itemObjectReader;
    final Type itemType;

    public ObjectReaderImplGenericArray(GenericArrayType genericArrayType) {
        this.arrayType = genericArrayType;
        this.arrayClass = TypeUtils.getClass(genericArrayType);
        Type genericComponentType = genericArrayType.getGenericComponentType();
        this.itemType = genericComponentType;
        Class<?> mapping = TypeUtils.getMapping(genericComponentType);
        this.componentClass = mapping;
        String str = "[" + TypeUtils.getTypeName(mapping);
        this.arrayClassName = str;
        this.arrayClassNameHash = Fnv.hashCode64(str);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.nextIfMatch(JSONB.Constants.BC_TYPED_ANY) && jSONReader.readTypeHashCode() != this.arrayClassNameHash) {
            throw new JSONException("not support input typeName " + jSONReader.getString());
        }
        int startArray = jSONReader.startArray();
        if (startArray > 0 && this.itemObjectReader == null) {
            this.itemObjectReader = jSONReader.getContext().getObjectReader(this.itemType);
        }
        Object newInstance = Array.newInstance(this.componentClass, startArray);
        int i = 0;
        while (i < startArray) {
            JSONReader jSONReader2 = jSONReader;
            Array.set(newInstance, i, this.itemObjectReader.readJSONBObject(jSONReader2, this.itemType, null, 0L));
            i++;
            jSONReader = jSONReader2;
        }
        return newInstance;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Object readString;
        if (this.itemObjectReader == null) {
            this.itemObjectReader = jSONReader.getContext().getObjectReader(this.itemType);
        }
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, 0L);
        }
        JSONReader jSONReader2 = jSONReader;
        if (jSONReader2.readIfNull()) {
            return null;
        }
        char current = jSONReader2.current();
        if (current == '\"') {
            if (!(type instanceof GenericArrayType) || ((GenericArrayType) type).getGenericComponentType() != Byte.TYPE) {
                if (jSONReader2.readString().isEmpty()) {
                    return null;
                }
                throw new JSONException(jSONReader2.info());
            }
            if ((jSONReader2.features(j) & JSONReader.Feature.Base64StringAsByteArray.mask) != 0) {
                return Base64.getDecoder().decode(jSONReader2.readString());
            }
            return jSONReader2.readBinary();
        }
        ArrayList arrayList = new ArrayList();
        if (current != '[') {
            throw new JSONException(jSONReader2.info());
        }
        jSONReader2.next();
        while (!jSONReader2.nextIfArrayEnd()) {
            JSONReader jSONReader3 = jSONReader2;
            ObjectReader objectReader = this.itemObjectReader;
            if (objectReader != null) {
                readString = objectReader.readObject(jSONReader3, this.itemType, null, 0L);
                jSONReader2 = jSONReader3;
            } else {
                jSONReader2 = jSONReader3;
                if (this.itemType == String.class) {
                    readString = jSONReader2.readString();
                } else {
                    throw new JSONException(jSONReader2.info("TODO : " + this.itemType));
                }
            }
            arrayList.add(readString);
            jSONReader2.nextIfComma();
        }
        jSONReader2.nextIfComma();
        Object newInstance = Array.newInstance(this.componentClass, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }
}
