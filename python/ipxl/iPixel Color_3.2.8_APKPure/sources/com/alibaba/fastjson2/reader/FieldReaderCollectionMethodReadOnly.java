package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes2.dex */
class FieldReaderCollectionMethodReadOnly<T> extends FieldReaderObject<T> {
    @Override // com.alibaba.fastjson2.reader.FieldReader
    public boolean isReadOnly() {
        return true;
    }

    FieldReaderCollectionMethodReadOnly(String str, Type type, Class cls, int i, long j, String str2, JSONSchema jSONSchema, Method method, Field field) {
        super(str, type, cls, i, j, str2, null, null, jSONSchema, method, field, null);
        Type type2;
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                type2 = actualTypeArguments[0];
                this.itemType = type2;
            }
        }
        type2 = null;
        this.itemType = type2;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        if (obj == null) {
            return;
        }
        try {
            Collection collection = (Collection) this.method.invoke(t, new Object[0]);
            if (collection == Collections.EMPTY_LIST || collection == Collections.EMPTY_SET || collection == null || collection.equals(obj)) {
                if (this.schema != null) {
                    this.schema.assertValidate(collection);
                    return;
                }
                return;
            }
            String name = collection.getClass().getName();
            if ("java.util.Collections$UnmodifiableRandomAccessList".equals(name) || "java.util.Arrays$ArrayList".equals(name) || "java.util.Collections$SingletonList".equals(name) || name.startsWith("java.util.ImmutableCollections$") || name.startsWith("java.util.Collections$Unmodifiable")) {
                return;
            }
            for (Object obj2 : (Collection) obj) {
                if (obj2 == null) {
                    collection.add(null);
                } else {
                    if ((obj2 instanceof Map) && (this.itemType instanceof Class) && !((Class) this.itemType).isAssignableFrom(obj2.getClass())) {
                        if (this.itemReader == null) {
                            this.itemReader = JSONFactory.getDefaultObjectReaderProvider().getObjectReader(this.itemType);
                        }
                        obj2 = this.itemReader.createInstance((Map) obj2, 0L);
                    }
                    collection.add(obj2);
                }
            }
            if (this.schema != null) {
                this.schema.assertValidate(collection);
            }
        } catch (Exception e) {
            throw new JSONException("set " + this.fieldName + " error", e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        Object readObject;
        if (this.initReader == null) {
            this.initReader = jSONReader.getContext().getObjectReader(this.fieldType);
        }
        if (jSONReader.jsonb) {
            readObject = this.initReader.readJSONBObject(jSONReader, this.fieldType, this.fieldName, 0L);
        } else {
            readObject = this.initReader.readObject(jSONReader, this.fieldType, this.fieldName, 0L);
        }
        accept((FieldReaderCollectionMethodReadOnly<T>) t, readObject);
    }
}
