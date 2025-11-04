package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class ObjectArrayTypedReader extends ObjectReaderPrimitive {
    final Class componentClass;
    final long componentClassHash;
    final Class componentType;
    final String typeName;
    final long typeNameHashCode;

    ObjectArrayTypedReader(Class cls) {
        super(cls);
        Class<?> componentType = cls.getComponentType();
        this.componentType = componentType;
        String typeName = TypeUtils.getTypeName(componentType);
        this.componentClassHash = Fnv.hashCode64(typeName);
        String str = "[" + typeName;
        this.typeName = str;
        this.typeNameHashCode = Fnv.hashCode64(str);
        this.componentClass = TypeUtils.getClass(componentType);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, 0L);
        }
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            Object[] objArr = (Object[]) Array.newInstance((Class<?>) this.componentType, 16);
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                int i2 = i + 1;
                if (i2 - objArr.length > 0) {
                    int length = objArr.length;
                    int i3 = length + (length >> 1);
                    if (i3 - i2 < 0) {
                        i3 = i2;
                    }
                    objArr = Arrays.copyOf(objArr, i3);
                }
                objArr[i] = jSONReader.read(this.componentType);
                jSONReader.nextIfComma();
                i = i2;
            }
            jSONReader.nextIfMatch(',');
            return Arrays.copyOf(objArr, i);
        }
        if (jSONReader.current() == '{') {
            jSONReader.next();
            if (jSONReader.readFieldNameHashCode() == HASH_TYPE) {
                jSONReader.readString();
            }
        }
        if (jSONReader.isString()) {
            String readString = jSONReader.readString();
            if (readString == null || readString.isEmpty()) {
                return null;
            }
            if (ObjectReader.VALUE_NAME.equals(readString)) {
                jSONReader.next();
                Object readObject = readObject(jSONReader, type, obj, j);
                jSONReader.nextIfObjectEnd();
                return readObject;
            }
        }
        throw new JSONException(jSONReader.info("TODO"));
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Object read;
        Object obj2;
        if (jSONReader.getType() == -110) {
            jSONReader.next();
            long readTypeHashCode = jSONReader.readTypeHashCode();
            if (readTypeHashCode != ObjectArrayReader.TYPE_HASH_CODE && readTypeHashCode != this.typeNameHashCode) {
                if (jSONReader.isSupportAutoType(j)) {
                    ObjectReader objectReaderAutoType = jSONReader.getObjectReaderAutoType(readTypeHashCode, this.objectClass, j);
                    if (objectReaderAutoType == null) {
                        throw new JSONException(jSONReader.info("autoType not support : " + jSONReader.getString()));
                    }
                    return objectReaderAutoType.readObject(jSONReader, type, obj, j);
                }
                throw new JSONException(jSONReader.info("not support autotype : " + jSONReader.getString()));
            }
        }
        int startArray = jSONReader.startArray();
        if (startArray == -1) {
            return null;
        }
        Object[] objArr = (Object[]) Array.newInstance((Class<?>) this.componentClass, startArray);
        for (int i = 0; i < startArray; i++) {
            if (jSONReader.isReference()) {
                String readReference = jSONReader.readReference();
                if ("..".equals(readReference)) {
                    obj2 = objArr;
                } else {
                    jSONReader.addResolveTask(objArr, i, JSONPath.of(readReference));
                    obj2 = null;
                }
                read = obj2;
            } else {
                ObjectReader checkAutoType = jSONReader.checkAutoType(this.componentClass, this.componentClassHash, j);
                if (checkAutoType != null) {
                    read = checkAutoType.readJSONBObject(jSONReader, null, null, j);
                } else {
                    read = jSONReader.read((Class<Object>) this.componentType);
                }
            }
            objArr[i] = read;
        }
        return objArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        Class<?> cls;
        Function typeConvert;
        Object[] objArr = (Object[]) Array.newInstance((Class<?>) this.componentClass, collection.size());
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null && (cls = next.getClass()) != this.componentType && (typeConvert = JSONFactory.getDefaultObjectReaderProvider().getTypeConvert(cls, this.componentType)) != null) {
                next = typeConvert.apply(next);
            }
            if (!this.componentType.isInstance(next)) {
                ObjectReader objectReader = JSONFactory.getDefaultObjectReaderProvider().getObjectReader(this.componentType);
                if (next instanceof Map) {
                    next = objectReader.createInstance((Map) next, new JSONReader.Feature[0]);
                } else if (next instanceof Collection) {
                    next = objectReader.createInstance((Collection) next, j);
                } else if (next instanceof Object[]) {
                    next = objectReader.createInstance(JSONArray.of((Object[]) next), j);
                } else if (next != null) {
                    Class<?> cls2 = next.getClass();
                    if (cls2.isArray()) {
                        int length = Array.getLength(next);
                        JSONArray jSONArray = new JSONArray(length);
                        for (int i2 = 0; i2 < length; i2++) {
                            jSONArray.add(Array.get(next, i2));
                        }
                        next = objectReader.createInstance(jSONArray, j);
                    } else {
                        throw new JSONException("component type not match, expect " + this.componentType.getName() + ", but " + cls2);
                    }
                } else {
                    continue;
                }
            }
            objArr[i] = next;
            i++;
        }
        return objArr;
    }
}
