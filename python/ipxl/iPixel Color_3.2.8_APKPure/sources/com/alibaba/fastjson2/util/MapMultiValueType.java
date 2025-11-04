package com.alibaba.fastjson2.util;

import com.alibaba.fastjson2.JSONObject;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class MapMultiValueType<T extends Map> implements Type {
    private final Class<T> mapType;
    private final Map<String, Type> valueTypes;

    MapMultiValueType(Class<T> cls, String str, Type type) {
        HashMap hashMap = new HashMap();
        this.valueTypes = hashMap;
        this.mapType = cls;
        hashMap.put(str, type);
    }

    MapMultiValueType(Class<T> cls, Map<String, Type> map) {
        HashMap hashMap = new HashMap();
        this.valueTypes = hashMap;
        this.mapType = cls;
        hashMap.putAll(map);
    }

    public Class<T> getMapType() {
        return this.mapType;
    }

    public Type getType(String str) {
        return this.valueTypes.get(str);
    }

    public static MapMultiValueType<JSONObject> of(String str, Type type) {
        return new MapMultiValueType<>(JSONObject.class, str, type);
    }

    public static MapMultiValueType<JSONObject> of(Map<String, Type> map) {
        return new MapMultiValueType<>(JSONObject.class, map);
    }

    public static <T extends Map> MapMultiValueType<T> of(Class<T> cls, String str, Type type) {
        return new MapMultiValueType<>(cls, str, type);
    }

    public static <T extends Map> MapMultiValueType<T> of(Class<T> cls, Map<String, Type> map) {
        return new MapMultiValueType<>(cls, map);
    }
}
