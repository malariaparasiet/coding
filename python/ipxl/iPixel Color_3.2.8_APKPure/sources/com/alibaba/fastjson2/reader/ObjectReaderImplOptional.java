package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Optional;

/* loaded from: classes2.dex */
class ObjectReaderImplOptional extends ObjectReaderPrimitive {
    static final ObjectReaderImplOptional INSTANCE = new ObjectReaderImplOptional(null, null, null);
    final String format;
    final Class itemClass;
    ObjectReader itemObjectReader;
    final Type itemType;
    final Locale locale;

    static ObjectReaderImplOptional of(Type type, String str, Locale locale) {
        if (type == null) {
            return INSTANCE;
        }
        return new ObjectReaderImplOptional(type, str, locale);
    }

    public ObjectReaderImplOptional(Type type, String str, Locale locale) {
        super(Optional.class);
        Type type2;
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length == 1) {
                type2 = actualTypeArguments[0];
                this.itemType = type2;
                this.itemClass = TypeUtils.getClass(type2);
                this.format = str;
                this.locale = locale;
            }
        }
        type2 = null;
        this.itemType = type2;
        this.itemClass = TypeUtils.getClass(type2);
        this.format = str;
        this.locale = locale;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Object readJSONBObject;
        Type type2 = this.itemType;
        if (type2 == null) {
            readJSONBObject = jSONReader.readAny();
        } else {
            if (this.itemObjectReader == null) {
                String str = this.format;
                ObjectReader createFormattedObjectReader = str != null ? FieldReader.createFormattedObjectReader(type2, this.itemClass, str, this.locale) : null;
                if (createFormattedObjectReader == null) {
                    this.itemObjectReader = jSONReader.getObjectReader(this.itemType);
                } else {
                    this.itemObjectReader = createFormattedObjectReader;
                }
            }
            readJSONBObject = this.itemObjectReader.readJSONBObject(jSONReader, this.itemType, obj, 0L);
        }
        if (readJSONBObject == null) {
            return Optional.empty();
        }
        return Optional.of(readJSONBObject);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Object readObject;
        Type type2 = this.itemType;
        if (type2 == null) {
            readObject = jSONReader.readAny();
        } else {
            if (this.itemObjectReader == null) {
                String str = this.format;
                ObjectReader createFormattedObjectReader = str != null ? FieldReader.createFormattedObjectReader(type2, this.itemClass, str, this.locale) : null;
                if (createFormattedObjectReader == null) {
                    this.itemObjectReader = jSONReader.getObjectReader(this.itemType);
                } else {
                    this.itemObjectReader = createFormattedObjectReader;
                }
            }
            readObject = this.itemObjectReader.readObject(jSONReader, this.itemType, obj, 0L);
        }
        if (readObject == null) {
            return Optional.empty();
        }
        return Optional.of(readObject);
    }
}
