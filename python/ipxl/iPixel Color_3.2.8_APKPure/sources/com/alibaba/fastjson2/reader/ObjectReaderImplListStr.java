package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.GuavaSupport;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;

/* loaded from: classes2.dex */
public final class ObjectReaderImplListStr implements ObjectReader {
    final Class instanceType;
    final Class listType;

    public ObjectReaderImplListStr(Class cls, Class cls2) {
        this.listType = cls;
        this.instanceType = cls2;
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
        if (this.listType.isInstance(collection)) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!(it.next() instanceof String)) {
                }
            }
            return collection;
        }
        Collection collection2 = (Collection) createInstance(0L);
        for (Object obj : collection) {
            if (obj == null || (obj instanceof String)) {
                collection2.add(obj);
            } else {
                collection2.add(JSON.toJSONString(obj));
            }
        }
        return collection2;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return this.listType;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Collection collection;
        Function immutableListConverter;
        ArrayList arrayList;
        JSONArray jSONArray;
        ArrayList arrayList2;
        Class cls = this.instanceType;
        Function function = null;
        if (jSONReader.nextIfNull()) {
            return null;
        }
        ObjectReader checkAutoType = jSONReader.checkAutoType(this.listType, 0L, j);
        if (checkAutoType != null) {
            cls = checkAutoType.getObjectClass();
        }
        int i = 0;
        if (cls == ObjectReaderImplList.CLASS_ARRAYS_LIST) {
            int startArray = jSONReader.startArray();
            String[] strArr = new String[startArray];
            while (i < startArray) {
                strArr[i] = jSONReader.readString();
                i++;
            }
            return Arrays.asList(strArr);
        }
        int startArray2 = jSONReader.startArray();
        if (cls == ArrayList.class) {
            if (startArray2 > 0) {
                collection = arrayList2;
                arrayList2 = new ArrayList(startArray2);
            } else {
                collection = arrayList2;
                arrayList2 = new ArrayList();
            }
        } else if (cls == JSONArray.class) {
            if (startArray2 > 0) {
                collection = jSONArray;
                jSONArray = new JSONArray(startArray2);
            } else {
                collection = jSONArray;
                jSONArray = new JSONArray();
            }
        } else if (cls == ObjectReaderImplList.CLASS_UNMODIFIABLE_COLLECTION) {
            ArrayList arrayList3 = new ArrayList();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplListStr$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    Collection unmodifiableCollection;
                    unmodifiableCollection = Collections.unmodifiableCollection((Collection) obj2);
                    return unmodifiableCollection;
                }
            };
            collection = arrayList3;
        } else if (cls == ObjectReaderImplList.CLASS_UNMODIFIABLE_LIST) {
            ArrayList arrayList4 = new ArrayList();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplListStr$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    List unmodifiableList;
                    unmodifiableList = Collections.unmodifiableList((List) obj2);
                    return unmodifiableList;
                }
            };
            collection = arrayList4;
        } else if (cls == ObjectReaderImplList.CLASS_UNMODIFIABLE_SET) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplListStr$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    Set unmodifiableSet;
                    unmodifiableSet = Collections.unmodifiableSet((Set) obj2);
                    return unmodifiableSet;
                }
            };
            collection = linkedHashSet;
        } else if (cls == ObjectReaderImplList.CLASS_UNMODIFIABLE_SORTED_SET) {
            TreeSet treeSet = new TreeSet();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplListStr$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    SortedSet unmodifiableSortedSet;
                    unmodifiableSortedSet = Collections.unmodifiableSortedSet((SortedSet) obj2);
                    return unmodifiableSortedSet;
                }
            };
            collection = treeSet;
        } else if (cls == ObjectReaderImplList.CLASS_UNMODIFIABLE_NAVIGABLE_SET) {
            TreeSet treeSet2 = new TreeSet();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplListStr$$ExternalSyntheticLambda4
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    NavigableSet unmodifiableNavigableSet;
                    unmodifiableNavigableSet = Collections.unmodifiableNavigableSet((NavigableSet) obj2);
                    return unmodifiableNavigableSet;
                }
            };
            collection = treeSet2;
        } else if (cls == ObjectReaderImplList.CLASS_SINGLETON) {
            ArrayList arrayList5 = new ArrayList();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplListStr$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    Collection singleton;
                    singleton = Collections.singleton(((Collection) obj2).iterator().next());
                    return singleton;
                }
            };
            collection = arrayList5;
        } else if (cls == ObjectReaderImplList.CLASS_SINGLETON_LIST) {
            ArrayList arrayList6 = new ArrayList();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplListStr$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    Collection singletonList;
                    singletonList = Collections.singletonList(((Collection) obj2).iterator().next());
                    return singletonList;
                }
            };
            collection = arrayList6;
        } else if (cls != null && cls != this.listType) {
            String typeName = cls.getTypeName();
            typeName.hashCode();
            switch (typeName) {
                case "com.google.common.collect.ImmutableList":
                    ArrayList arrayList7 = new ArrayList();
                    immutableListConverter = GuavaSupport.immutableListConverter();
                    arrayList = arrayList7;
                    function = immutableListConverter;
                    collection = arrayList;
                    break;
                case "kotlin.collections.EmptyList":
                    collection = ObjectReaderImplList.getKotlinEmptyList(cls);
                    break;
                case "kotlin.collections.EmptySet":
                    collection = ObjectReaderImplList.getKotlinEmptySet(cls);
                    break;
                case "com.google.common.collect.ImmutableSet":
                    ArrayList arrayList8 = new ArrayList();
                    immutableListConverter = GuavaSupport.immutableSetConverter();
                    arrayList = arrayList8;
                    function = immutableListConverter;
                    collection = arrayList;
                    break;
                case "com.google.common.collect.Lists$TransformingRandomAccessList":
                    collection = new ArrayList();
                    break;
                case "com.google.common.collect.Lists.TransformingSequentialList":
                    collection = new LinkedList();
                    break;
                default:
                    try {
                        collection = (Collection) cls.newInstance();
                        break;
                    } catch (IllegalAccessException | InstantiationException e) {
                        throw new JSONException(jSONReader.info("create instance error " + cls), e);
                    }
            }
        } else {
            collection = (Collection) createInstance(jSONReader.getContext().getFeatures() | j);
        }
        while (i < startArray2) {
            collection.add(jSONReader.readString());
            i++;
        }
        return function != null ? (Collection) function.apply(collection) : collection;
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
        if (jSONReader.nextIfSet()) {
            collection = new HashSet();
        } else {
            collection = (Collection) createInstance(jSONReader.getContext().getFeatures() | j);
        }
        char current = jSONReader.current();
        if (current == '[') {
            jSONReader.next();
            while (!jSONReader.nextIfArrayEnd()) {
                String readString = jSONReader.readString();
                if (readString != null || !(collection instanceof SortedSet)) {
                    collection.add(readString);
                }
            }
        } else if (current == '\"' || current == '\'' || current == '{') {
            String readString2 = jSONReader.readString();
            if (readString2 != null && !readString2.isEmpty()) {
                collection.add(readString2);
            }
        } else {
            throw new JSONException(jSONReader.info());
        }
        jSONReader.nextIfComma();
        return collection;
    }
}
