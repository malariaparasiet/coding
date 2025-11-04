package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public final class ObjectReaderInterface<T> extends ObjectReaderAdapter<T> {
    public ObjectReaderInterface(Class cls, String str, String str2, long j, Supplier supplier, Function function, FieldReader[] fieldReaderArr) {
        super(cls, str, str2, j, null, supplier, function, fieldReaderArr);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReader
    public T readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.nextIfNull()) {
            return null;
        }
        ObjectReader checkAutoType = jSONReader.checkAutoType(this.objectClass, this.typeNameHash, this.features | j);
        if (checkAutoType != null && checkAutoType.getObjectClass() != this.objectClass) {
            return (T) checkAutoType.readJSONBObject(jSONReader, type, obj, j);
        }
        if (jSONReader.isArray()) {
            if (jSONReader.isSupportBeanArray()) {
                return readArrayMappingJSONBObject(jSONReader, type, obj, j);
            }
            throw new JSONException(jSONReader.info("expect object, but " + JSONB.typeName(jSONReader.getType())));
        }
        jSONReader.nextIfObjectStart();
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (!jSONReader.nextIfObjectEnd()) {
            long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
            if (readFieldNameHashCode == this.typeKeyHashCode && i == 0) {
                long readValueHashCode = jSONReader.readValueHashCode();
                JSONReader.Context context = jSONReader.getContext();
                ObjectReader autoType = autoType(context, readValueHashCode);
                if (autoType == null) {
                    String string = jSONReader.getString();
                    ObjectReader objectReaderAutoType = context.getObjectReaderAutoType(string, null);
                    if (objectReaderAutoType == null) {
                        throw new JSONException(jSONReader.info("autoType not support : " + string));
                    }
                    autoType = objectReaderAutoType;
                }
                if (autoType != this) {
                    jSONReader.setTypeRedirect(true);
                    return (T) autoType.readJSONBObject(jSONReader, type, obj, j);
                }
            } else if (readFieldNameHashCode != 0) {
                FieldReader fieldReader = getFieldReader(readFieldNameHashCode);
                if (fieldReader == null && jSONReader.isSupportSmartMatch(this.features | j)) {
                    fieldReader = getFieldReaderLCase(jSONReader.getNameHashCodeLCase());
                }
                if (fieldReader == null) {
                    jSONObject.put(jSONReader.getFieldName(), jSONReader.readAny());
                } else {
                    jSONObject.put(fieldReader.fieldName, fieldReader.readFieldValue(jSONReader));
                }
            }
            i++;
        }
        T t = (T) TypeUtils.newProxyInstance(this.objectClass, jSONObject);
        if (this.schema != null) {
            this.schema.assertValidate(t);
        }
        return t;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderBean, com.alibaba.fastjson2.reader.ObjectReader
    public T readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        String str;
        Class<?> apply;
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, j);
        }
        if (jSONReader.nextIfNull()) {
            jSONReader.nextIfComma();
            return null;
        }
        if (jSONReader.isArray() && jSONReader.isSupportBeanArray(getFeatures() | j)) {
            return readArrayMappingObject(jSONReader, type, obj, j);
        }
        JSONObject jSONObject = new JSONObject();
        if (!jSONReader.nextIfObjectStart()) {
            char current = jSONReader.current();
            if (current == 't' || current == 'f') {
                jSONReader.readBoolValue();
                return null;
            }
            if (current != '\"' && current != '\'' && current != '}') {
                throw new JSONException(jSONReader.info());
            }
        }
        int i = 0;
        while (!jSONReader.nextIfObjectEnd()) {
            JSONReader.Context context = jSONReader.getContext();
            long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
            JSONReader.AutoTypeBeforeHandler contextAutoTypeBeforeHandler = context.getContextAutoTypeBeforeHandler();
            if (i == 0 && readFieldNameHashCode == getTypeKeyHash()) {
                long features = j | getFeatures() | context.getFeatures();
                if ((JSONReader.Feature.SupportAutoType.mask & features) != 0 || contextAutoTypeBeforeHandler != null) {
                    long readTypeHashCode = jSONReader.readTypeHashCode();
                    ObjectReader objectReader = (contextAutoTypeBeforeHandler == null || contextAutoTypeBeforeHandler.apply(readTypeHashCode, this.objectClass, features) != null || (apply = contextAutoTypeBeforeHandler.apply(jSONReader.getString(), this.objectClass, features)) == null) ? null : context.getObjectReader(apply);
                    if (objectReader == null) {
                        objectReader = autoType(context, readTypeHashCode);
                    }
                    if (objectReader == null) {
                        String string = jSONReader.getString();
                        ObjectReader objectReaderAutoType = context.getObjectReaderAutoType(string, this.objectClass, features);
                        if (objectReaderAutoType == null) {
                            throw new JSONException(jSONReader.info("No suitable ObjectReader found for" + string));
                        }
                        objectReader = objectReaderAutoType;
                        str = string;
                    } else {
                        str = null;
                    }
                    if (objectReader != this) {
                        FieldReader fieldReader = objectReader.getFieldReader(readFieldNameHashCode);
                        if (fieldReader != null && str == null) {
                            str = jSONReader.getString();
                        }
                        String str2 = str;
                        T t = (T) objectReader.readObject(jSONReader, null, null, j | getFeatures());
                        if (fieldReader != null) {
                            fieldReader.accept((FieldReader) t, (Object) str2);
                        }
                        return t;
                    }
                    i++;
                }
            }
            FieldReader fieldReader2 = getFieldReader(readFieldNameHashCode);
            if (fieldReader2 == null && jSONReader.isSupportSmartMatch(j | getFeatures())) {
                fieldReader2 = getFieldReaderLCase(jSONReader.getNameHashCodeLCase());
            }
            if (fieldReader2 == null) {
                jSONObject.put(jSONReader.getFieldName(), jSONReader.readAny());
            } else {
                jSONObject.put(fieldReader2.fieldName, fieldReader2.readFieldValue(jSONReader));
            }
            i++;
        }
        jSONReader.nextIfComma();
        T t2 = (T) TypeUtils.newProxyInstance(this.objectClass, jSONObject);
        Function buildFunction = getBuildFunction();
        if (buildFunction != null) {
            t2 = (T) buildFunction.apply(t2);
        }
        if (this.schema != null) {
            this.schema.assertValidate(t2);
        }
        return t2;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReader
    public T createInstance(long j) {
        return (T) TypeUtils.newProxyInstance(this.objectClass, new JSONObject());
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReader
    public T createInstance(Map map, long j) {
        JSONObject jSONObject;
        if (map instanceof JSONObject) {
            jSONObject = (JSONObject) map;
        } else {
            jSONObject = new JSONObject(map);
        }
        for (FieldReader fieldReader : this.fieldReaders) {
            Object obj = jSONObject.get(fieldReader.fieldName);
            if (obj instanceof Map) {
                ObjectReader objectReader = fieldReader.getObjectReader(JSONFactory.getDefaultObjectReaderProvider());
                if (objectReader instanceof ObjectReaderAdapter) {
                    if (jSONObject == map) {
                        jSONObject = new JSONObject(map);
                    }
                    jSONObject.put(fieldReader.fieldName, objectReader.createInstance((Map) obj, j));
                }
            }
        }
        return (T) TypeUtils.newProxyInstance(this.objectClass, jSONObject);
    }
}
