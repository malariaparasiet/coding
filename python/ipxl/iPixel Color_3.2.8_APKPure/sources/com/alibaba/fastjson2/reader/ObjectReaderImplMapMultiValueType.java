package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.util.GuavaSupport;
import com.alibaba.fastjson2.util.MapMultiValueType;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;

/* loaded from: classes2.dex */
public class ObjectReaderImplMapMultiValueType implements ObjectReader {
    final Function builder;
    final Class instanceType;
    final Class mapType;
    final MapMultiValueType multiValueType;

    public ObjectReaderImplMapMultiValueType(MapMultiValueType mapMultiValueType) {
        this.multiValueType = mapMultiValueType;
        Class mapType = mapMultiValueType.getMapType();
        this.mapType = mapType;
        Function function = null;
        if (mapType == Map.class || mapType == AbstractMap.class || mapType == ObjectReaderImplMap.CLASS_SINGLETON_MAP) {
            mapType = HashMap.class;
        } else if (mapType == ObjectReaderImplMap.CLASS_UNMODIFIABLE_MAP) {
            mapType = LinkedHashMap.class;
        } else if (mapType == SortedMap.class || mapType == ObjectReaderImplMap.CLASS_UNMODIFIABLE_SORTED_MAP || mapType == ObjectReaderImplMap.CLASS_UNMODIFIABLE_NAVIGABLE_MAP) {
            mapType = TreeMap.class;
        } else if (mapType == ConcurrentMap.class) {
            mapType = ConcurrentHashMap.class;
        } else if (mapType == ConcurrentNavigableMap.class) {
            mapType = ConcurrentSkipListMap.class;
        } else {
            String typeName = mapType.getTypeName();
            typeName.hashCode();
            switch (typeName) {
                case "java.util.Collections$SynchronizedSortedMap":
                    mapType = TreeMap.class;
                    function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplMapMultiValueType$$ExternalSyntheticLambda2
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            SortedMap synchronizedSortedMap;
                            synchronizedSortedMap = Collections.synchronizedSortedMap((SortedMap) obj);
                            return synchronizedSortedMap;
                        }
                    };
                    break;
                case "com.google.common.collect.SingletonImmutableBiMap":
                    mapType = HashMap.class;
                    function = GuavaSupport.singletonBiMapConverter();
                    break;
                case "java.util.Collections$SynchronizedMap":
                    mapType = HashMap.class;
                    function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplMapMultiValueType$$ExternalSyntheticLambda0
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            Map synchronizedMap;
                            synchronizedMap = Collections.synchronizedMap((Map) obj);
                            return synchronizedMap;
                        }
                    };
                    break;
                case "java.util.Collections$SynchronizedNavigableMap":
                    mapType = TreeMap.class;
                    function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplMapMultiValueType$$ExternalSyntheticLambda1
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            NavigableMap synchronizedNavigableMap;
                            synchronizedNavigableMap = Collections.synchronizedNavigableMap((NavigableMap) obj);
                            return synchronizedNavigableMap;
                        }
                    };
                    break;
                case "com.google.common.collect.ImmutableMap":
                case "com.google.common.collect.RegularImmutableMap":
                    mapType = HashMap.class;
                    function = GuavaSupport.immutableMapConverter();
                    break;
            }
        }
        this.instanceType = mapType;
        this.builder = function;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(long j) {
        Class cls = this.instanceType;
        if (cls != null && !cls.isInterface()) {
            try {
                return this.instanceType.newInstance();
            } catch (Exception e) {
                throw new JSONException("create map error", e);
            }
        }
        return new HashMap();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0104 A[RETURN] */
    @Override // com.alibaba.fastjson2.reader.ObjectReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object readObject(com.alibaba.fastjson2.JSONReader r15, java.lang.reflect.Type r16, java.lang.Object r17, long r18) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.ObjectReaderImplMapMultiValueType.readObject(com.alibaba.fastjson2.JSONReader, java.lang.reflect.Type, java.lang.Object, long):java.lang.Object");
    }
}
