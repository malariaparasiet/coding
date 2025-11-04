package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* loaded from: classes2.dex */
public class FieldReaderList<T, V> extends FieldReaderObject<T> {
    final long fieldClassHash;
    final long itemClassHash;

    public FieldReaderList(String str, Type type, Class cls, Type type2, Class cls2, int i, long j, String str2, Locale locale, Object obj, JSONSchema jSONSchema, Method method, Field field, BiConsumer biConsumer) {
        super(str, type, cls, i, j, str2, locale, obj, jSONSchema, method, field, biConsumer);
        this.itemType = type2;
        this.itemClass = cls2;
        this.itemClassHash = this.itemClass == null ? 0L : Fnv.hashCode64(cls2.getName());
        this.fieldClassHash = cls != null ? Fnv.hashCode64(TypeUtils.getTypeName(cls)) : 0L;
        if (str2 == null || type2 != Date.class) {
            return;
        }
        this.itemReader = new ObjectReaderImplDate(str2, locale);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public long getItemClassHash() {
        return this.itemClassHash;
    }

    public Collection<V> createList(JSONReader.Context context) {
        if (this.fieldClass == List.class || this.fieldClass == Collection.class || this.fieldClass == ArrayList.class) {
            return new ArrayList();
        }
        return (Collection) getObjectReader(context).createInstance(this.features);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        Object readObject;
        Object readObject2;
        JSONReader jSONReader2;
        if (jSONReader.jsonb) {
            readFieldValueJSONB(jSONReader, t);
            return;
        }
        Function function = null;
        if (jSONReader.nextIfNull()) {
            accept((FieldReaderList<T, V>) t, (Object) null);
            return;
        }
        if (jSONReader.isReference()) {
            String readReference = jSONReader.readReference();
            if ("..".equals(readReference)) {
                accept((FieldReaderList<T, V>) t, t);
                return;
            } else {
                addResolveTask(jSONReader, t, readReference);
                return;
            }
        }
        JSONReader.Context context = jSONReader.getContext();
        ObjectReader objectReader = getObjectReader(context);
        if (this.initReader != null) {
            function = this.initReader.getBuildFunction();
        } else if (objectReader instanceof ObjectReaderImplList) {
            function = objectReader.getBuildFunction();
        }
        char current = jSONReader.current();
        if (current == '[') {
            ObjectReader itemObjectReader = getItemObjectReader(context);
            Collection createList = createList(context);
            jSONReader.next();
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                if (jSONReader.readReference(createList, i)) {
                    jSONReader2 = jSONReader;
                } else {
                    JSONReader jSONReader3 = jSONReader;
                    jSONReader2 = jSONReader3;
                    createList.add(itemObjectReader.readObject(jSONReader3, null, null, 0L));
                    jSONReader2.nextIfComma();
                }
                i++;
                jSONReader = jSONReader2;
            }
            if (function != null) {
                createList = (Collection) function.apply(createList);
            }
            accept((FieldReaderList<T, V>) t, createList);
            jSONReader.nextIfComma();
            return;
        }
        if (current == '{' && (getItemObjectReader(context) instanceof ObjectReaderBean)) {
            if (jSONReader.jsonb) {
                readObject2 = this.itemReader.readJSONBObject(jSONReader, null, null, this.features);
            } else {
                readObject2 = this.itemReader.readObject(jSONReader, null, null, this.features);
            }
            Collection collection = (Collection) objectReader.createInstance(this.features);
            collection.add(readObject2);
            if (function != null) {
                collection = (Collection) function.apply(collection);
            }
            accept((FieldReaderList<T, V>) t, collection);
            jSONReader.nextIfComma();
            return;
        }
        if (jSONReader.jsonb) {
            readObject = objectReader.readJSONBObject(jSONReader, null, null, this.features);
        } else {
            readObject = objectReader.readObject(jSONReader, null, null, this.features);
        }
        accept((FieldReaderList<T, V>) t, readObject);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        Function typeConvert;
        int i = 0;
        if (jSONReader.jsonb) {
            int startArray = jSONReader.startArray();
            if (startArray == -1) {
                return null;
            }
            Object[] objArr = new Object[startArray];
            ObjectReader itemObjectReader = getItemObjectReader(jSONReader.getContext());
            while (i < startArray) {
                JSONReader jSONReader2 = jSONReader;
                objArr[i] = itemObjectReader.readObject(jSONReader2, null, null, 0L);
                i++;
                jSONReader = jSONReader2;
            }
            return Arrays.asList(objArr);
        }
        if (jSONReader.current() == '[') {
            JSONReader.Context context = jSONReader.getContext();
            ObjectReader itemObjectReader2 = getItemObjectReader(context);
            Collection<V> createList = createList(context);
            jSONReader.next();
            while (!jSONReader.nextIfArrayEnd()) {
                createList.add(itemObjectReader2.readObject(jSONReader, null, null, 0L));
                jSONReader.nextIfComma();
                itemObjectReader2 = itemObjectReader2;
            }
            jSONReader.nextIfComma();
            return createList;
        }
        if (jSONReader.isString()) {
            String readString = jSONReader.readString();
            if ((this.itemType instanceof Class) && Number.class.isAssignableFrom((Class) this.itemType) && (typeConvert = jSONReader.getContext().getProvider().getTypeConvert(String.class, this.itemType)) != null) {
                Collection<V> createList2 = createList(jSONReader.getContext());
                if (readString.indexOf(44) != -1) {
                    String[] split = readString.split(",");
                    int length = split.length;
                    while (i < length) {
                        createList2.add(typeConvert.apply(split[i]));
                        i++;
                    }
                }
                return createList2;
            }
        }
        throw new JSONException(jSONReader.info("TODO : " + getClass()));
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public ObjectReader checkObjectAutoType(JSONReader jSONReader) {
        if (!jSONReader.nextIfMatch(JSONB.Constants.BC_TYPED_ANY)) {
            return null;
        }
        long readTypeHashCode = jSONReader.readTypeHashCode();
        long features = jSONReader.features(this.features);
        JSONReader.Context context = jSONReader.getContext();
        JSONReader.AutoTypeBeforeHandler contextAutoTypeBeforeHandler = context.getContextAutoTypeBeforeHandler();
        if (contextAutoTypeBeforeHandler != null) {
            Class<?> apply = contextAutoTypeBeforeHandler.apply(readTypeHashCode, this.fieldClass, features);
            if (apply == null) {
                apply = contextAutoTypeBeforeHandler.apply(jSONReader.getString(), this.fieldClass, features);
            }
            if (apply != null) {
                return context.getObjectReader(this.fieldClass);
            }
        }
        if (!jSONReader.isSupportAutoType(features)) {
            if (jSONReader.isArray() && !jSONReader.isEnabled(JSONReader.Feature.ErrorOnNotSupportAutoType)) {
                return getObjectReader(jSONReader);
            }
            throw new JSONException(jSONReader.info("autoType not support input " + jSONReader.getString()));
        }
        ObjectReader objectReaderAutoType = jSONReader.getObjectReaderAutoType(readTypeHashCode, this.fieldClass, features);
        if (objectReaderAutoType instanceof ObjectReaderImplList) {
            ObjectReaderImplList objectReaderImplList = (ObjectReaderImplList) objectReaderAutoType;
            objectReaderAutoType = new ObjectReaderImplList(this.fieldType, this.fieldClass, objectReaderImplList.instanceType, this.itemType, objectReaderImplList.builder);
        }
        if (objectReaderAutoType != null) {
            return objectReaderAutoType;
        }
        throw new JSONException(jSONReader.info("autoType not support : " + jSONReader.getString()));
    }
}
