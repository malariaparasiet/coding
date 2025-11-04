package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/* loaded from: classes2.dex */
public interface ObjectReader<T> {
    public static final long HASH_TYPE = Fnv.hashCode64("@type");
    public static final String VALUE_NAME = "@value";

    default void acceptExtra(Object obj, String str, Object obj2, long j) {
    }

    default Function getBuildFunction() {
        return null;
    }

    default long getFeatures() {
        return 0L;
    }

    default FieldReader getFieldReader(long j) {
        return null;
    }

    default FieldReader getFieldReaderLCase(long j) {
        return null;
    }

    default Class<T> getObjectClass() {
        return null;
    }

    T readObject(JSONReader jSONReader, Type type, Object obj, long j);

    default T createInstance() {
        return createInstance(0L);
    }

    default T createInstance(long j) {
        throw new UnsupportedOperationException();
    }

    default T createInstance(Collection collection) {
        return createInstance(collection, 0L);
    }

    default T createInstance(Collection collection, JSONReader.Feature... featureArr) {
        return createInstance(collection, JSONReader.Feature.of(featureArr));
    }

    default T createInstance(Collection collection, long j) {
        throw new UnsupportedOperationException(getClass().getName());
    }

    default void acceptExtra(Object obj, String str, Object obj2) {
        acceptExtra(obj, str, obj2, getFeatures());
    }

    default T createInstance(Map map, JSONReader.Feature... featureArr) {
        long j = 0;
        for (JSONReader.Feature feature : featureArr) {
            j |= feature.mask;
        }
        return createInstance(map, j);
    }

    default T createInstance(Map map, long j) {
        ObjectReaderProvider defaultObjectReaderProvider = JSONFactory.getDefaultObjectReaderProvider();
        Object obj = map.get(getTypeKey());
        if (obj instanceof String) {
            String str = (String) obj;
            ObjectReader autoType = ((JSONReader.Feature.SupportAutoType.mask & j) != 0 || (this instanceof ObjectReaderSeeAlso)) ? autoType(defaultObjectReaderProvider, Fnv.hashCode64(str)) : null;
            if (autoType == null) {
                autoType = defaultObjectReaderProvider.getObjectReader(str, getObjectClass(), getFeatures() | j);
            }
            if (autoType != this && autoType != null) {
                return autoType.createInstance(map, j);
            }
        }
        return accept(createInstance(0L), map, j);
    }

    default T accept(T t, Map map, long j) {
        for (Map.Entry entry : map.entrySet()) {
            String obj = entry.getKey().toString();
            Object value = entry.getValue();
            FieldReader fieldReader = getFieldReader(obj);
            if (fieldReader == null) {
                acceptExtra(t, obj, entry.getValue(), j);
            } else {
                fieldReader.acceptAny(t, value, j);
            }
        }
        Function buildFunction = getBuildFunction();
        return buildFunction != null ? (T) buildFunction.apply(t) : t;
    }

    default T createInstanceNoneDefaultConstructor(Map<Long, Object> map) {
        throw new UnsupportedOperationException();
    }

    default String getTypeKey() {
        return "@type";
    }

    default long getTypeKeyHash() {
        return HASH_TYPE;
    }

    default boolean setFieldValue(Object obj, String str, long j, int i) {
        FieldReader fieldReader = getFieldReader(j);
        if (fieldReader == null) {
            return false;
        }
        fieldReader.accept((FieldReader) obj, i);
        return true;
    }

    default boolean setFieldValue(Object obj, String str, long j, long j2) {
        FieldReader fieldReader = getFieldReader(j);
        if (fieldReader == null) {
            return false;
        }
        fieldReader.accept((FieldReader) obj, j2);
        return true;
    }

    default FieldReader getFieldReader(String str) {
        long hashCode64 = Fnv.hashCode64(str);
        FieldReader fieldReader = getFieldReader(hashCode64);
        if (fieldReader == null && (fieldReader = getFieldReaderLCase(hashCode64)) == null) {
            long hashCode64LCase = Fnv.hashCode64LCase(str);
            if (hashCode64LCase != hashCode64) {
                return getFieldReaderLCase(hashCode64LCase);
            }
        }
        return fieldReader;
    }

    default boolean setFieldValue(Object obj, String str, Object obj2) {
        FieldReader fieldReader = getFieldReader(str);
        if (fieldReader == null) {
            return false;
        }
        fieldReader.accept((FieldReader) obj, obj2);
        return true;
    }

    default ObjectReader autoType(JSONReader.Context context, long j) {
        return context.getObjectReaderAutoType(j);
    }

    default ObjectReader autoType(ObjectReaderProvider objectReaderProvider, long j) {
        return objectReaderProvider.getObjectReader(j);
    }

    default T readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.isArray() && jSONReader.isSupportBeanArray()) {
            return readArrayMappingJSONBObject(jSONReader, type, obj, j);
        }
        jSONReader.nextIfObjectStart();
        JSONReader.Context context = jSONReader.getContext();
        long features = context.getFeatures() | j;
        int i = 0;
        T t = null;
        while (!jSONReader.nextIfObjectEnd()) {
            long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
            if (readFieldNameHashCode == getTypeKeyHash() && i == 0) {
                ObjectReader autoType = autoType(context, jSONReader.readTypeHashCode());
                if (autoType == null) {
                    String string = jSONReader.getString();
                    ObjectReader objectReaderAutoType = context.getObjectReaderAutoType(string, null);
                    if (objectReaderAutoType == null) {
                        throw new JSONException(jSONReader.info("No suitable ObjectReader found for " + string));
                    }
                    autoType = objectReaderAutoType;
                }
                if (autoType != this) {
                    return autoType.readJSONBObject(jSONReader, type, obj, j);
                }
            } else if (readFieldNameHashCode != 0) {
                FieldReader fieldReader = getFieldReader(readFieldNameHashCode);
                if (fieldReader == null && jSONReader.isSupportSmartMatch(getFeatures() | features)) {
                    long nameHashCodeLCase = jSONReader.getNameHashCodeLCase();
                    if (nameHashCodeLCase != readFieldNameHashCode) {
                        fieldReader = getFieldReaderLCase(nameHashCodeLCase);
                    }
                }
                if (fieldReader == null) {
                    jSONReader.skipValue();
                } else {
                    if (t == null) {
                        t = createInstance(features);
                    }
                    fieldReader.readFieldValue(jSONReader, t);
                }
            }
            i++;
        }
        return t != null ? t : createInstance(features);
    }

    default T readArrayMappingJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        throw new UnsupportedOperationException();
    }

    default T readArrayMappingObject(JSONReader jSONReader, Type type, Object obj, long j) {
        throw new UnsupportedOperationException();
    }

    default T readObject(String str, JSONReader.Feature... featureArr) {
        JSONReader of = JSONReader.of(str, JSONFactory.createReadContext(featureArr));
        try {
            T readObject = readObject(of, null, null, getFeatures());
            if (of != null) {
                of.close();
            }
            return readObject;
        } catch (Throwable th) {
            if (of == null) {
                throw th;
            }
            try {
                of.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    default T readObject(JSONReader jSONReader) {
        return readObject(jSONReader, null, null, getFeatures());
    }

    default T readObject(JSONReader jSONReader, long j) {
        return readObject(jSONReader, null, null, j);
    }
}
