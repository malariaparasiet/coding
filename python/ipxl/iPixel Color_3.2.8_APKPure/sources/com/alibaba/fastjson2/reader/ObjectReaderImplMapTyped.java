package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.ReferenceKey;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/* loaded from: classes2.dex */
class ObjectReaderImplMapTyped implements ObjectReader {
    final Function builder;
    final Constructor defaultConstructor;
    final long features;
    final Class instanceType;
    ObjectReader keyObjectReader;
    final Type keyType;
    final Class mapType;
    final boolean multiValue;
    final Class valueClass;
    ObjectReader valueObjectReader;
    final Type valueType;

    public ObjectReaderImplMapTyped(Class cls, Class cls2, Type type, Type type2, long j, Function function) {
        Constructor<?> constructor = null;
        type = type == Object.class ? null : type;
        this.mapType = cls;
        this.instanceType = cls2;
        this.keyType = type;
        this.valueType = type2;
        this.valueClass = TypeUtils.getClass(type2);
        this.features = j;
        this.builder = function;
        int i = 0;
        this.multiValue = cls2 != null && "org.springframework.util.LinkedMultiValueMap".equals(cls2.getName());
        Constructor<?>[] declaredConstructors = cls2.getDeclaredConstructors();
        int length = declaredConstructors.length;
        while (true) {
            if (i >= length) {
                break;
            }
            Constructor<?> constructor2 = declaredConstructors[i];
            if (constructor2.getParameterCount() == 0 && !Modifier.isPublic(constructor2.getModifiers())) {
                constructor2.setAccessible(true);
                constructor = constructor2;
                break;
            }
            i++;
        }
        this.defaultConstructor = constructor;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return this.mapType;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Map map, long j) {
        Map hashMap;
        Object obj;
        ObjectReaderProvider defaultObjectReaderProvider = JSONFactory.getDefaultObjectReaderProvider();
        Class cls = this.instanceType;
        if (cls == Map.class || cls == HashMap.class) {
            hashMap = new HashMap();
        } else {
            hashMap = (Map) createInstance(j);
        }
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            Type type = this.keyType;
            if (type == null || type == String.class) {
                obj = key.toString();
            } else {
                obj = TypeUtils.cast(key, type);
            }
            if (value != null) {
                Class<?> cls2 = value.getClass();
                if (this.valueType == Object.class) {
                    continue;
                } else if (cls2 == JSONObject.class || cls2 == TypeUtils.CLASS_JSON_OBJECT_1x) {
                    if (this.valueObjectReader == null) {
                        this.valueObjectReader = defaultObjectReaderProvider.getObjectReader(this.valueType);
                    }
                    value = this.valueObjectReader.createInstance((Map) value, j);
                } else if ((cls2 == JSONArray.class || cls2 == TypeUtils.CLASS_JSON_ARRAY_1x) && this.valueClass == List.class) {
                    if (this.valueObjectReader == null) {
                        this.valueObjectReader = defaultObjectReaderProvider.getObjectReader(this.valueType);
                    }
                    value = this.valueObjectReader.createInstance((List) value, j);
                } else {
                    Function typeConvert = defaultObjectReaderProvider.getTypeConvert(cls2, this.valueType);
                    if (typeConvert != null) {
                        value = typeConvert.apply(value);
                    } else if (value instanceof Map) {
                        if (this.valueObjectReader == null) {
                            this.valueObjectReader = defaultObjectReaderProvider.getObjectReader(this.valueType);
                        }
                        value = this.valueObjectReader.createInstance((Map) value, j);
                    } else if ((value instanceof Collection) && !this.multiValue) {
                        if (this.valueObjectReader == null) {
                            this.valueObjectReader = defaultObjectReaderProvider.getObjectReader(this.valueType);
                        }
                        value = this.valueObjectReader.createInstance((Collection) value, j);
                    } else if (!cls2.isInstance(value)) {
                        throw new JSONException("can not convert from " + cls2 + " to " + this.valueType);
                    }
                }
            }
            hashMap.put(obj, value);
        }
        Function function = this.builder;
        return function != null ? function.apply(hashMap) : hashMap;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(long j) {
        Class cls = this.instanceType;
        if (cls != null && !cls.isInterface()) {
            try {
                Constructor constructor = this.defaultConstructor;
                if (constructor != null) {
                    return constructor.newInstance(new Object[0]);
                }
                return this.instanceType.newInstance();
            } catch (Exception e) {
                throw new JSONException("create map error", e);
            }
        }
        return new HashMap();
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        long j2;
        Function function;
        ObjectReader objectReader;
        Map map;
        Object readFieldName;
        Object obj2;
        Object readJSONBObject;
        Object readAny;
        Type type2;
        Function function2 = this.builder;
        if (jSONReader.getType() == -110) {
            objectReader = jSONReader.checkAutoType(this.mapType, 0L, this.features | j);
            if (objectReader == null || objectReader == this) {
                j2 = j;
                function = function2;
            } else {
                function = objectReader.getBuildFunction();
                if (!(objectReader instanceof ObjectReaderImplMap) && !(objectReader instanceof ObjectReaderImplMapTyped)) {
                    return objectReader.readJSONBObject(jSONReader, type, obj, j);
                }
                j2 = j;
            }
        } else {
            j2 = j;
            function = function2;
            objectReader = null;
        }
        byte type3 = jSONReader.getType();
        if (type3 == -81) {
            jSONReader.next();
            return null;
        }
        if (type3 == -90) {
            jSONReader.next();
        }
        long features = j2 | jSONReader.getContext().getFeatures();
        if (objectReader != null) {
            map = (Map) objectReader.createInstance(features);
        } else if (this.instanceType == HashMap.class) {
            map = new HashMap();
        } else {
            map = (Map) createInstance(j2);
        }
        Map map2 = map;
        int i = 0;
        while (jSONReader.getType() != -91) {
            if (this.keyType == String.class || jSONReader.isString()) {
                readFieldName = jSONReader.readFieldName();
            } else {
                if (jSONReader.isReference()) {
                    String readReference = jSONReader.readReference();
                    readAny = new ReferenceKey(i);
                    jSONReader.addResolveTask(map2, readAny, JSONPath.of(readReference));
                } else {
                    if (this.keyObjectReader == null && (type2 = this.keyType) != null) {
                        this.keyObjectReader = jSONReader.getObjectReader(type2);
                    }
                    ObjectReader objectReader2 = this.keyObjectReader;
                    if (objectReader2 == null) {
                        readAny = jSONReader.readAny();
                    } else {
                        readFieldName = objectReader2.readJSONBObject(jSONReader, null, null, j2);
                    }
                }
                readFieldName = readAny;
            }
            if (jSONReader.isReference()) {
                String readReference2 = jSONReader.readReference();
                if ("..".equals(readReference2)) {
                    map2.put(readFieldName, map2);
                } else {
                    jSONReader.addResolveTask(map2, readFieldName, JSONPath.of(readReference2));
                    if (!(map2 instanceof ConcurrentMap)) {
                        map2.put(readFieldName, null);
                    }
                }
            } else if (jSONReader.nextIfNull()) {
                map2.put(readFieldName, null);
            } else {
                if (this.valueType == Object.class) {
                    readJSONBObject = jSONReader.readAny();
                    obj2 = readFieldName;
                } else {
                    ObjectReader checkAutoType = jSONReader.checkAutoType(this.valueClass, 0L, j);
                    if (checkAutoType != null && checkAutoType != this) {
                        obj2 = readFieldName;
                        readJSONBObject = checkAutoType.readJSONBObject(jSONReader, this.valueType, obj2, j);
                    } else {
                        obj2 = readFieldName;
                        if (this.valueObjectReader == null) {
                            this.valueObjectReader = jSONReader.getObjectReader(this.valueType);
                        }
                        readJSONBObject = this.valueObjectReader.readJSONBObject(jSONReader, this.valueType, obj2, j);
                    }
                }
                if (readJSONBObject != null || (JSONReader.Feature.IgnoreNullPropertyValue.mask & features) == 0) {
                    map2.put(obj2, readJSONBObject);
                }
            }
            i++;
            j2 = j;
        }
        jSONReader.next();
        if (function == null) {
            return map2;
        }
        if (function == ObjectReaderImplMap.ENUM_MAP_BUILDER && map2.isEmpty()) {
            return new EnumMap((Class) this.keyType);
        }
        return function.apply(map2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x02a9  */
    @Override // com.alibaba.fastjson2.reader.ObjectReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object readObject(com.alibaba.fastjson2.JSONReader r15, java.lang.reflect.Type r16, java.lang.Object r17, long r18) {
        /*
            Method dump skipped, instructions count: 706
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.ObjectReaderImplMapTyped.readObject(com.alibaba.fastjson2.JSONReader, java.lang.reflect.Type, java.lang.Object, long):java.lang.Object");
    }
}
