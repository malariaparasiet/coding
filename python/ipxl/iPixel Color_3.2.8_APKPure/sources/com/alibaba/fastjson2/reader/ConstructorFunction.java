package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.codec.FieldInfo;
import com.alibaba.fastjson2.internal.asm.ASMUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class ConstructorFunction<T> implements Function<Map<Long, Object>, T> {
    Map<Set<Long>, Type[]> alternateConstructorArgTypes;
    Map<Set<Long>, Constructor> alternateConstructorMap;
    Map<Set<Long>, long[]> alternateConstructorNameHashCodes;
    Map<Set<Long>, String[]> alternateConstructorNames;
    final List<Constructor> alternateConstructors;
    final BiFunction biFunction;
    final Constructor constructor;
    final Function function;
    final long[] hashCodes;
    final boolean marker;
    final String[] paramNames;
    final Parameter[] parameters;

    ConstructorFunction(List<Constructor> list, Constructor constructor, Function function, BiFunction biFunction, Constructor constructor2, String... strArr) {
        String name;
        this.function = function;
        this.biFunction = biFunction;
        boolean z = constructor2 != null;
        this.marker = z;
        this.constructor = z ? constructor2 : constructor;
        Parameter[] parameters = constructor.getParameters();
        this.parameters = parameters;
        this.paramNames = strArr;
        this.hashCodes = new long[parameters.length];
        int i = 0;
        while (true) {
            Parameter[] parameterArr = this.parameters;
            if (i >= parameterArr.length) {
                break;
            }
            if (i < strArr.length) {
                name = strArr[i];
            } else {
                name = parameterArr[i].getName();
            }
            if (name == null) {
                name = "arg" + i;
            }
            this.hashCodes[i] = Fnv.hashCode64(name);
            i++;
        }
        this.alternateConstructors = list;
        if (list != null) {
            int size = list.size();
            this.alternateConstructorMap = new HashMap(size, 1.0f);
            this.alternateConstructorNames = new HashMap(size, 1.0f);
            this.alternateConstructorArgTypes = new HashMap(size, 1.0f);
            this.alternateConstructorNameHashCodes = new HashMap(size, 1.0f);
            for (int i2 = 0; i2 < size; i2++) {
                Constructor constructor3 = list.get(i2);
                constructor3.setAccessible(true);
                String[] lookupParameterNames = ASMUtils.lookupParameterNames(constructor3);
                Parameter[] parameters2 = constructor3.getParameters();
                FieldInfo fieldInfo = new FieldInfo();
                ObjectReaderProvider defaultObjectReaderProvider = JSONFactory.getDefaultObjectReaderProvider();
                for (int i3 = 0; i3 < parameters2.length && i3 < lookupParameterNames.length; i3++) {
                    fieldInfo.init();
                    defaultObjectReaderProvider.getFieldInfo(fieldInfo, constructor3.getDeclaringClass(), constructor3, i3, parameters2[i3]);
                    if (fieldInfo.fieldName != null) {
                        lookupParameterNames[i3] = fieldInfo.fieldName;
                    }
                }
                long[] jArr = new long[lookupParameterNames.length];
                Type[] genericParameterTypes = constructor3.getGenericParameterTypes();
                HashSet hashSet = new HashSet(lookupParameterNames.length);
                for (int i4 = 0; i4 < lookupParameterNames.length; i4++) {
                    long hashCode64 = Fnv.hashCode64(lookupParameterNames[i4]);
                    jArr[i4] = hashCode64;
                    hashSet.add(Long.valueOf(hashCode64));
                }
                this.alternateConstructorMap.put(hashSet, constructor3);
                this.alternateConstructorNames.put(hashSet, lookupParameterNames);
                this.alternateConstructorNameHashCodes.put(hashSet, jArr);
                this.alternateConstructorArgTypes.put(hashSet, genericParameterTypes);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    public T apply(Map<Long, Object> map) {
        Set<Long> keySet;
        Constructor constructor;
        long[] jArr = this.hashCodes;
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (map.containsKey(Long.valueOf(jArr[i2]))) {
                i2++;
            } else if (this.alternateConstructorMap != null && (constructor = this.alternateConstructorMap.get((keySet = map.keySet()))) != null) {
                long[] jArr2 = this.alternateConstructorNameHashCodes.get(keySet);
                Type[] typeArr = this.alternateConstructorArgTypes.get(keySet);
                Object[] objArr = new Object[jArr2.length];
                while (i < jArr2.length) {
                    Object obj = map.get(Long.valueOf(jArr2[i]));
                    Type type = typeArr[i];
                    if (obj == null) {
                        obj = TypeUtils.getDefaultValue(type);
                    }
                    objArr[i] = obj;
                    i++;
                }
                try {
                    return (T) constructor.newInstance(objArr);
                } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException e) {
                    throw new JSONException("invoke constructor error, " + constructor, e);
                }
            }
        }
        if (this.function != null) {
            Parameter[] parameterArr = this.parameters;
            if (parameterArr.length == 1) {
                Parameter parameter = parameterArr[0];
                Object obj2 = map.get(Long.valueOf(this.hashCodes[0]));
                Class<?> type2 = parameter.getType();
                if (obj2 == null) {
                    obj2 = TypeUtils.getDefaultValue(type2);
                } else if (!type2.isInstance(obj2)) {
                    obj2 = TypeUtils.cast(obj2, (Class<Object>) type2);
                }
                return (T) this.function.apply(obj2);
            }
        }
        if (this.biFunction != null && this.parameters.length == 2) {
            Object obj3 = map.get(Long.valueOf(this.hashCodes[0]));
            Class<?> type3 = this.parameters[0].getType();
            if (obj3 == null) {
                obj3 = TypeUtils.getDefaultValue(type3);
            } else if (!type3.isInstance(obj3)) {
                obj3 = TypeUtils.cast(obj3, (Class<Object>) type3);
            }
            Object obj4 = map.get(Long.valueOf(this.hashCodes[1]));
            Class<?> type4 = this.parameters[1].getType();
            if (obj4 == null) {
                obj4 = TypeUtils.getDefaultValue(type4);
            } else if (!type4.isInstance(obj4)) {
                obj4 = TypeUtils.cast(obj4, (Class<Object>) type4);
            }
            return (T) this.biFunction.apply(obj3, obj4);
        }
        int length2 = this.parameters.length;
        Object[] objArr2 = new Object[this.constructor.getParameterCount()];
        if (this.marker) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < length2) {
                Parameter parameter2 = this.parameters[i3];
                Class<?> type5 = parameter2.getType();
                Type parameterizedType = parameter2.getParameterizedType();
                Object obj5 = map.get(Long.valueOf(this.hashCodes[i3]));
                if (obj5 != null) {
                    if (!type5.isInstance(obj5)) {
                        obj5 = TypeUtils.cast(obj5, (Class<Object>) type5);
                    } else if (parameterizedType instanceof ParameterizedType) {
                        obj5 = TypeUtils.cast(obj5, parameterizedType);
                    }
                    objArr2[i3] = obj5;
                } else {
                    i4 |= 1 << i3;
                    if (type5.isPrimitive()) {
                        objArr2[i3] = TypeUtils.getDefaultValue(type5);
                    }
                }
                int i5 = i3 + 1;
                if (i5 % 32 == 0 || i5 == length2) {
                    objArr2[(i3 / 32) + length2] = Integer.valueOf(i4);
                    i4 = 0;
                }
                i3 = i5;
            }
        } else {
            while (i < length2) {
                Parameter parameter3 = this.parameters[i];
                Class<?> type6 = parameter3.getType();
                Type parameterizedType2 = parameter3.getParameterizedType();
                Object obj6 = map.get(Long.valueOf(this.hashCodes[i]));
                if (obj6 == null) {
                    obj6 = TypeUtils.getDefaultValue(type6);
                } else if (!type6.isInstance(obj6)) {
                    obj6 = TypeUtils.cast(obj6, (Class<Object>) type6);
                } else if (parameterizedType2 instanceof ParameterizedType) {
                    obj6 = TypeUtils.cast(obj6, parameterizedType2);
                }
                objArr2[i] = obj6;
                i++;
            }
        }
        try {
            return (T) this.constructor.newInstance(objArr2);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException e2) {
            throw new JSONException("invoke constructor error, " + this.constructor, e2);
        }
    }
}
