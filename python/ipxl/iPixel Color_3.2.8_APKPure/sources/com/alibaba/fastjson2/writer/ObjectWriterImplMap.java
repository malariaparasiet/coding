package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.AfterFilter;
import com.alibaba.fastjson2.filter.BeforeFilter;
import com.alibaba.fastjson2.filter.NameFilter;
import com.alibaba.fastjson2.filter.PropertyFilter;
import com.alibaba.fastjson2.filter.PropertyPreFilter;
import com.alibaba.fastjson2.filter.ValueFilter;
import com.alibaba.fastjson2.util.BeanUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.temporal.Temporal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public final class ObjectWriterImplMap extends ObjectWriterPrimitiveImpl {
    static final ObjectWriterImplMap INSTANCE_1x;
    final boolean contentAs;
    final long features;
    final String format;
    final boolean jsonObject1;
    final Field jsonObject1InnerMap;
    final long jsonObject1InnerMapOffset;
    final byte[] jsonbTypeInfo;
    final Type keyType;
    volatile ObjectWriter keyWriter;
    final Class objectClass;
    final Type objectType;
    final char[] typeInfoUTF16;
    final byte[] typeInfoUTF8;
    final long typeNameHash;
    final Type valueType;
    final boolean valueTypeRefDetect;
    volatile ObjectWriter valueWriter;
    static final byte[] TYPE_NAME_JSONObject1O = JSONB.toBytes("JO10");
    static final long TYPE_HASH_JSONObject1O = Fnv.hashCode64("JO10");
    static final ObjectWriterImplMap INSTANCE = new ObjectWriterImplMap(String.class, Object.class, JSONObject.class, JSONObject.class, 0);

    static {
        if (TypeUtils.CLASS_JSON_OBJECT_1x == null) {
            INSTANCE_1x = null;
        } else {
            INSTANCE_1x = new ObjectWriterImplMap(String.class, Object.class, TypeUtils.CLASS_JSON_OBJECT_1x, TypeUtils.CLASS_JSON_OBJECT_1x, 0L);
        }
    }

    public ObjectWriterImplMap(Class cls, long j) {
        this(null, null, cls, cls, j);
    }

    public ObjectWriterImplMap(Type type, Type type2, Class cls, Type type3, long j) {
        this(type, type2, null, cls, type3, j);
    }

    public ObjectWriterImplMap(Type type, Type type2, String str, Class cls, Type type3, long j) {
        long j2;
        this.keyType = type;
        this.valueType = type2;
        this.format = str;
        this.objectClass = cls;
        this.objectType = type3;
        this.features = j;
        if (type2 == null) {
            this.valueTypeRefDetect = true;
        } else {
            this.valueTypeRefDetect = !ObjectWriterProvider.isNotReferenceDetect(TypeUtils.getClass(type2));
        }
        this.contentAs = (Long.MIN_VALUE & j) != 0;
        String typeName = TypeUtils.getTypeName(cls);
        String str2 = "\"@type\":\"" + cls.getName() + "\"";
        this.typeInfoUTF16 = str2.toCharArray();
        this.typeInfoUTF8 = str2.getBytes(StandardCharsets.UTF_8);
        boolean equals = "JO1".equals(typeName);
        this.jsonObject1 = equals;
        this.jsonbTypeInfo = JSONB.toBytes(typeName);
        this.typeNameHash = Fnv.hashCode64(typeName);
        if (equals) {
            Field declaredField = BeanUtils.getDeclaredField(cls, "map");
            this.jsonObject1InnerMap = declaredField;
            if (declaredField != null) {
                declaredField.setAccessible(true);
                j2 = JDKUtils.UNSAFE.objectFieldOffset(declaredField);
                this.jsonObject1InnerMapOffset = j2;
            }
        } else {
            this.jsonObject1InnerMap = null;
        }
        j2 = -1;
        this.jsonObject1InnerMapOffset = j2;
    }

    public static ObjectWriterImplMap of(Class cls) {
        if (cls == JSONObject.class) {
            return INSTANCE;
        }
        if (cls == TypeUtils.CLASS_JSON_OBJECT_1x) {
            return INSTANCE_1x;
        }
        return new ObjectWriterImplMap(null, null, cls, cls, 0L);
    }

    public static ObjectWriterImplMap of(Type type) {
        return new ObjectWriterImplMap(TypeUtils.getClass(type), 0L);
    }

    public static ObjectWriterImplMap of(Type type, Class cls) {
        return of(type, null, cls);
    }

    public static ObjectWriterImplMap of(Type type, String str, Class cls) {
        Type type2;
        Type type3;
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                Type type4 = actualTypeArguments[0];
                type3 = actualTypeArguments[1];
                type2 = type4;
                return new ObjectWriterImplMap(type2, type3, str, cls, type, 0L);
            }
        }
        type2 = null;
        type3 = null;
        return new ObjectWriterImplMap(type2, type3, str, cls, type, 0L);
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriterPrimitiveImpl, com.alibaba.fastjson2.writer.ObjectWriter
    public void writeArrayMappingJSONB(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        JSONWriter jSONWriter2;
        jSONWriter.startObject();
        boolean isWriteNulls = jSONWriter.isWriteNulls();
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                jSONWriter.writeString(str);
                Class<?> cls = value.getClass();
                if (cls == String.class) {
                    jSONWriter.writeString((String) value);
                    jSONWriter2 = jSONWriter;
                } else {
                    jSONWriter2 = jSONWriter;
                    jSONWriter.getObjectWriter(cls).writeJSONB(jSONWriter2, value, str, this.valueType, this.features);
                }
                jSONWriter = jSONWriter2;
            } else if (isWriteNulls) {
                jSONWriter.writeString(str);
                jSONWriter.writeNull();
            }
        }
        jSONWriter.endObject();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x024e  */
    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeJSONB(com.alibaba.fastjson2.JSONWriter r27, java.lang.Object r28, java.lang.Object r29, java.lang.reflect.Type r30, long r31) {
        /*
            Method dump skipped, instructions count: 706
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.writer.ObjectWriterImplMap.writeJSONB(com.alibaba.fastjson2.JSONWriter, java.lang.Object, java.lang.Object, java.lang.reflect.Type, long):void");
    }

    static boolean isWriteAsString(Object obj, long j) {
        return ((j & (JSONWriter.Feature.WriteNonStringKeyAsString.mask | JSONWriter.Feature.BrowserCompatible.mask)) == 0 || !ObjectWriterProvider.isPrimitiveOrEnum(obj.getClass()) || (obj instanceof Temporal) || (obj instanceof Date)) ? false : true;
    }

    String mapKeyToString(Object obj, JSONWriter jSONWriter, long j) {
        int length;
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if ((obj instanceof Integer) || (obj instanceof Long)) {
            return obj.toString();
        }
        if (isWriteAsString(obj, j)) {
            return obj.toString();
        }
        String jSONString = JSON.toJSONString(obj, jSONWriter.getContext());
        if (jSONString == null || (length = jSONString.length()) <= 1) {
            return jSONString;
        }
        char c = jSONWriter.useSingleQuote ? '\'' : Typography.quote;
        if (jSONString.charAt(0) != c) {
            return jSONString;
        }
        int i = length - 1;
        return jSONString.charAt(i) == c ? jSONString.substring(1, i) : jSONString;
    }

    String writeMapKey(Object obj, JSONWriter jSONWriter, long j) {
        if (obj == null) {
            jSONWriter.writeName("null");
            return null;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            jSONWriter.writeName(str);
            return str;
        }
        if (isWriteAsString(obj, j)) {
            String obj2 = obj.toString();
            jSONWriter.writeName(obj2);
            return obj2;
        }
        if (obj instanceof Integer) {
            jSONWriter.writeName(((Integer) obj).intValue());
            return null;
        }
        if (obj instanceof Long) {
            jSONWriter.writeName(((Long) obj).longValue());
            return null;
        }
        jSONWriter.writeNameAny(obj);
        return null;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public boolean writeTypeInfo(JSONWriter jSONWriter) {
        if (jSONWriter.utf8) {
            jSONWriter.writeNameRaw(this.typeInfoUTF8);
            return true;
        }
        jSONWriter.writeNameRaw(this.typeInfoUTF16);
        return true;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        ObjectWriterProvider objectWriterProvider;
        Object obj3;
        DecimalFormat decimalFormat;
        Object obj4;
        String writeMapKey;
        Class<?> cls;
        boolean isPrimitiveOrEnum;
        ObjectWriter objectWriter;
        if (jSONWriter.jsonb) {
            writeJSONB(jSONWriter, obj, obj2, type, j);
            return;
        }
        if (hasFilter(jSONWriter)) {
            writeWithFilter(jSONWriter, obj, obj2, type, j);
            return;
        }
        Map map = (Map) obj;
        boolean isRefDetect = jSONWriter.isRefDetect();
        boolean z = (type == this.objectType && jSONWriter.isWriteMapTypeInfo(obj, this.objectClass, j)) || jSONWriter.isWriteTypeInfo(obj, type, j);
        if (!z && map.isEmpty()) {
            jSONWriter.writeRaw('{', '}');
            return;
        }
        jSONWriter.startObject();
        if (z) {
            writeTypeInfo(jSONWriter);
        }
        long features = j | jSONWriter.getFeatures();
        if (((JSONWriter.Feature.MapSortField.mask | JSONWriter.Feature.SortMapEntriesByKeys.mask) & features) != 0 && !(map instanceof SortedMap) && (map.getClass() != LinkedHashMap.class || (JSONWriter.Feature.SortMapEntriesByKeys.mask & features) != 0)) {
            map = new TreeMap(map);
        }
        ObjectWriterProvider objectWriterProvider2 = jSONWriter.context.provider;
        for (Map.Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (value != null) {
                if ((JSONWriter.Feature.NotWriteEmptyArray.mask & features) != 0) {
                    if (!(value instanceof Collection) || !((Collection) value).isEmpty()) {
                        if (value.getClass().isArray() && Array.getLength(value) == 0) {
                        }
                    }
                }
                if (this.keyWriter != null) {
                    obj3 = value;
                    objectWriterProvider = objectWriterProvider2;
                    decimalFormat = null;
                    this.keyWriter.write(jSONWriter, key, null, null, 0L);
                    obj4 = key;
                    writeMapKey = null;
                } else {
                    objectWriterProvider = objectWriterProvider2;
                    obj3 = value;
                    decimalFormat = null;
                    obj4 = key;
                    writeMapKey = writeMapKey(obj4, jSONWriter, features);
                }
                jSONWriter.writeColon();
                if (this.contentAs) {
                    cls = (Class) this.valueType;
                } else {
                    cls = obj3.getClass();
                }
                if (cls == String.class) {
                    jSONWriter.writeString((String) obj3);
                } else if (cls == Integer.class) {
                    jSONWriter.writeInt32((Integer) obj3);
                } else if (cls != Long.class) {
                    Object obj5 = obj3;
                    if (cls == Boolean.class) {
                        jSONWriter.writeBool(((Boolean) obj5).booleanValue());
                    } else if (cls != BigDecimal.class) {
                        if (cls == this.valueType) {
                            if (this.valueWriter != null) {
                                objectWriter = this.valueWriter;
                            } else {
                                String str = this.format;
                                if (str != null) {
                                    objectWriter = jSONWriter.getObjectWriter(cls, str);
                                } else {
                                    objectWriter = jSONWriter.getObjectWriter(cls);
                                }
                                this.valueWriter = objectWriter;
                            }
                            isPrimitiveOrEnum = ObjectWriterProvider.isPrimitiveOrEnum(obj5.getClass());
                        } else {
                            if (cls == JSONObject.class) {
                                objectWriter = INSTANCE;
                            } else if (cls == TypeUtils.CLASS_JSON_OBJECT_1x) {
                                objectWriter = INSTANCE_1x;
                            } else if (cls == JSONArray.class) {
                                objectWriter = ObjectWriterImplList.INSTANCE;
                            } else if (cls == TypeUtils.CLASS_JSON_ARRAY_1x) {
                                objectWriter = ObjectWriterImplList.INSTANCE;
                            } else {
                                ObjectWriter objectWriter2 = jSONWriter.getObjectWriter(cls);
                                isPrimitiveOrEnum = ObjectWriterProvider.isPrimitiveOrEnum(cls);
                                objectWriter = objectWriter2;
                            }
                            isPrimitiveOrEnum = false;
                        }
                        boolean z2 = (!isRefDetect || writeMapKey == null || isPrimitiveOrEnum) ? false : true;
                        if (z2) {
                            if (obj5 == obj) {
                                jSONWriter.writeReference("..");
                            } else {
                                String path = jSONWriter.setPath(writeMapKey, obj5);
                                if (path != null) {
                                    jSONWriter.writeReference(path);
                                    jSONWriter.popPath(obj5);
                                }
                            }
                        }
                        objectWriter.write(jSONWriter, obj5, obj4, this.valueType, this.features);
                        if (z2) {
                            jSONWriter.popPath(obj5);
                        }
                    } else if ((objectWriterProvider.userDefineMask & 8) == 0) {
                        jSONWriter.writeDecimal((BigDecimal) obj5, features, decimalFormat);
                    } else {
                        jSONWriter.getObjectWriter(cls).write(jSONWriter, obj5, obj4, this.valueType, this.features);
                    }
                } else if ((objectWriterProvider.userDefineMask & 4) == 0) {
                    jSONWriter.writeInt64((Long) obj3);
                } else {
                    jSONWriter.getObjectWriter(cls).write(jSONWriter, obj3, writeMapKey, Long.class, features);
                }
                objectWriterProvider2 = objectWriterProvider;
            } else if ((JSONWriter.Feature.WriteNulls.mask & features) != 0) {
                writeMapKey(key, jSONWriter, features);
                jSONWriter.writeColon();
                jSONWriter.writeNull();
            }
        }
        jSONWriter.endObject();
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeWithFilter(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        long j2;
        Class<?> cls;
        PropertyFilter propertyFilter;
        AfterFilter afterFilter;
        String path;
        if (obj == null) {
            jSONWriter.writeNull();
            return;
        }
        jSONWriter.startObject();
        Map map = (Map) obj;
        long features = j | jSONWriter.getFeatures();
        long j3 = 0;
        if (((JSONWriter.Feature.MapSortField.mask | JSONWriter.Feature.SortMapEntriesByKeys.mask) & features) != 0 && !(map instanceof SortedMap) && (map.getClass() != LinkedHashMap.class || (JSONWriter.Feature.SortMapEntriesByKeys.mask & features) != 0)) {
            map = new TreeMap(map);
        }
        JSONWriter.Context context = jSONWriter.context;
        BeforeFilter beforeFilter = context.getBeforeFilter();
        if (beforeFilter != null) {
            beforeFilter.writeBefore(jSONWriter, obj);
        }
        PropertyPreFilter propertyPreFilter = context.getPropertyPreFilter();
        NameFilter nameFilter = context.getNameFilter();
        ValueFilter valueFilter = context.getValueFilter();
        PropertyFilter propertyFilter2 = context.getPropertyFilter();
        AfterFilter afterFilter2 = context.getAfterFilter();
        boolean isEnabled = context.isEnabled(JSONWriter.Feature.WriteNulls.mask);
        boolean isEnabled2 = context.isEnabled(JSONWriter.Feature.ReferenceDetection.mask);
        for (Map.Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value != null || isEnabled) {
                String mapKeyToString = mapKeyToString(entry.getKey(), jSONWriter, features);
                if (isEnabled2 && (path = jSONWriter.setPath(mapKeyToString, value)) != null) {
                    jSONWriter.writeName(mapKeyToString);
                    jSONWriter.writeColon();
                    jSONWriter.writeReference(path);
                    jSONWriter.popPath(value);
                } else {
                    if (propertyPreFilter != null) {
                        try {
                            if (!propertyPreFilter.process(jSONWriter, obj, mapKeyToString)) {
                                if (isEnabled2) {
                                    jSONWriter.popPath(value);
                                }
                            }
                        } finally {
                            if (isEnabled2) {
                                jSONWriter.popPath(value);
                            }
                        }
                    }
                    if (nameFilter != null) {
                        mapKeyToString = nameFilter.process(obj, mapKeyToString, value);
                    }
                    if (propertyFilter2 == null || propertyFilter2.apply(obj, mapKeyToString, value)) {
                        if (valueFilter != null) {
                            value = valueFilter.apply(obj, mapKeyToString, value);
                        }
                        if (value == null) {
                            j2 = j3;
                            if ((jSONWriter.getFeatures(features) & JSONWriter.Feature.WriteNulls.mask) == j2) {
                                j3 = j2;
                            }
                        } else {
                            j2 = j3;
                        }
                        jSONWriter.writeName(mapKeyToString);
                        jSONWriter.writeColon();
                        if (value == null) {
                            jSONWriter.writeNull();
                            propertyFilter = propertyFilter2;
                            afterFilter = afterFilter2;
                        } else {
                            if (this.contentAs) {
                                cls = (Class) this.valueType;
                            } else {
                                cls = value.getClass();
                            }
                            propertyFilter = propertyFilter2;
                            afterFilter = afterFilter2;
                            jSONWriter.getObjectWriter(cls).write(jSONWriter, value, obj2, type, this.features);
                        }
                        if (isEnabled2) {
                            jSONWriter.popPath(value);
                        }
                        propertyFilter2 = propertyFilter;
                        afterFilter2 = afterFilter;
                        j3 = j2;
                    } else if (isEnabled2) {
                        jSONWriter.popPath(value);
                    }
                }
            }
        }
        AfterFilter afterFilter3 = afterFilter2;
        if (afterFilter3 != null) {
            afterFilter3.writeAfter(jSONWriter, obj);
        }
        jSONWriter.endObject();
    }
}
