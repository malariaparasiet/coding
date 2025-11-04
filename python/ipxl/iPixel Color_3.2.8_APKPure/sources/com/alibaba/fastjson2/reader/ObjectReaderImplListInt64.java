package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.function.Function;

/* loaded from: classes2.dex */
public final class ObjectReaderImplListInt64 implements ObjectReader {
    final Class instanceType;
    final long instanceTypeHash;
    final Class listType;

    public ObjectReaderImplListInt64(Class cls, Class cls2) {
        this.listType = cls;
        this.instanceType = cls2;
        this.instanceTypeHash = Fnv.hashCode64(TypeUtils.getTypeName(cls2));
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(long j) {
        Class cls = this.instanceType;
        if (cls == ArrayList.class) {
            return new ArrayList();
        }
        if (cls == LinkedList.class) {
            return new LinkedList();
        }
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException unused) {
            throw new JSONException("create list error, type " + this.instanceType);
        }
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        Collection collection2 = (Collection) createInstance(j);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            collection2.add(TypeUtils.toLong(it.next()));
        }
        return collection2;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Collection collection;
        Function buildFunction;
        if (jSONReader.nextIfNull()) {
            return null;
        }
        Class cls = this.listType;
        ObjectReader checkAutoType = jSONReader.checkAutoType(cls, this.instanceTypeHash, j);
        if (checkAutoType != null) {
            cls = checkAutoType.getObjectClass();
        }
        if (cls == ArrayList.class) {
            collection = new ArrayList();
        } else if (cls == JSONArray.class) {
            collection = new JSONArray();
        } else if (cls != null && cls != this.listType) {
            collection = (Collection) checkAutoType.createInstance(j);
        } else {
            collection = (Collection) createInstance(jSONReader.getContext().getFeatures() | j);
        }
        int startArray = jSONReader.startArray();
        for (int i = 0; i < startArray; i++) {
            collection.add(jSONReader.readInt64());
        }
        return (checkAutoType == null || (buildFunction = checkAutoType.getBuildFunction()) == null) ? collection : (Collection) buildFunction.apply(collection);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Collection collection;
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, 0L);
        }
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.isString()) {
            Collection collection2 = (Collection) createInstance(jSONReader.getContext().getFeatures() | j);
            String readString = jSONReader.readString();
            if (readString.indexOf(44) != -1) {
                for (String str : readString.split(",")) {
                    collection2.add(Long.valueOf(Long.parseLong(str)));
                }
            } else {
                collection2.add(Long.valueOf(Long.parseLong(readString)));
            }
            jSONReader.nextIfComma();
            return collection2;
        }
        boolean nextIfSet = jSONReader.nextIfSet();
        if (jSONReader.current() != '[') {
            throw new JSONException(jSONReader.info("format error"));
        }
        jSONReader.next();
        if (nextIfSet && this.instanceType == Collection.class) {
            collection = new LinkedHashSet();
        } else {
            collection = (Collection) createInstance(jSONReader.getContext().getFeatures() | j);
        }
        while (!jSONReader.isEnd()) {
            if (!jSONReader.nextIfArrayEnd()) {
                collection.add(jSONReader.readInt64());
            } else {
                jSONReader.nextIfComma();
                return collection;
            }
        }
        throw new JSONException(jSONReader.info("illegal input error"));
    }
}
