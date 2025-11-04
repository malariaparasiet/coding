package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.BeanUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;

/* loaded from: classes2.dex */
public final class ObjectReaderImplList implements ObjectReader {
    static List kotlinEmptyList;
    static Set kotlinEmptySet;
    final Function builder;
    volatile Constructor constructor;
    volatile boolean instanceError;
    final Class instanceType;
    final long instanceTypeHash;
    final Class itemClass;
    final String itemClassName;
    final long itemClassNameHash;
    ObjectReader itemObjectReader;
    final Type itemType;
    final Class listClass;
    Object listSingleton;
    final Type listType;
    static final Class CLASS_EMPTY_SET = Collections.emptySet().getClass();
    static final Class CLASS_EMPTY_LIST = Collections.emptyList().getClass();
    static final Class CLASS_SINGLETON = Collections.singleton(0).getClass();
    static final Class CLASS_SINGLETON_LIST = Collections.singletonList(0).getClass();
    static final Class CLASS_ARRAYS_LIST = Arrays.asList(0).getClass();
    static final Class CLASS_UNMODIFIABLE_COLLECTION = Collections.unmodifiableCollection(Collections.emptyList()).getClass();
    static final Class CLASS_UNMODIFIABLE_LIST = Collections.unmodifiableList(Collections.emptyList()).getClass();
    static final Class CLASS_UNMODIFIABLE_SET = Collections.unmodifiableSet(Collections.emptySet()).getClass();
    static final Class CLASS_UNMODIFIABLE_SORTED_SET = Collections.unmodifiableSortedSet(Collections.emptySortedSet()).getClass();
    static final Class CLASS_UNMODIFIABLE_NAVIGABLE_SET = Collections.unmodifiableNavigableSet(Collections.emptyNavigableSet()).getClass();
    public static ObjectReaderImplList INSTANCE = new ObjectReaderImplList(ArrayList.class, ArrayList.class, ArrayList.class, Object.class, null);

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0277, code lost:
    
        if (r6.equals("kotlin.collections.EmptyList") == false) goto L168;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0271  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.alibaba.fastjson2.reader.ObjectReader of(java.lang.reflect.Type r8, java.lang.Class r9, long r10) {
        /*
            Method dump skipped, instructions count: 860
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.ObjectReaderImplList.of(java.lang.reflect.Type, java.lang.Class, long):com.alibaba.fastjson2.reader.ObjectReader");
    }

    static /* synthetic */ Object lambda$of$0(Type type, Object obj) {
        Collection collection = (Collection) obj;
        if (collection.isEmpty() && (type instanceof Class)) {
            return EnumSet.noneOf((Class) type);
        }
        return EnumSet.copyOf(collection);
    }

    ObjectReaderImplList(Class cls, Object obj) {
        this(cls, cls, cls, Object.class, null);
        this.listSingleton = obj;
    }

    public ObjectReaderImplList(Type type, Class cls, Class cls2, Type type2, Function function) {
        this.listType = type;
        this.listClass = cls;
        this.instanceType = cls2;
        this.instanceTypeHash = Fnv.hashCode64(TypeUtils.getTypeName(cls2));
        this.itemType = type2;
        Class<?> cls3 = TypeUtils.getClass(type2);
        this.itemClass = cls3;
        this.builder = function;
        String typeName = cls3 != null ? TypeUtils.getTypeName(cls3) : null;
        this.itemClassName = typeName;
        this.itemClassNameHash = typeName != null ? Fnv.hashCode64(typeName) : 0L;
    }

    static Set getKotlinEmptySet(Class cls) {
        Set set = kotlinEmptySet;
        if (set != null) {
            return set;
        }
        try {
            Field field = cls.getField("INSTANCE");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Set set2 = (Set) field.get(null);
            kotlinEmptySet = set2;
            return set2;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalStateException("Failed to get singleton of " + cls, e);
        }
    }

    static List getKotlinEmptyList(Class cls) {
        List list = kotlinEmptyList;
        if (list != null) {
            return list;
        }
        try {
            Field field = cls.getField("INSTANCE");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            List list2 = (List) field.get(null);
            kotlinEmptyList = list2;
            return list2;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalStateException("Failed to get singleton of " + cls, e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return this.listClass;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Function getBuildFunction() {
        return this.builder;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        Collection collection2;
        if (collection.size() == 0 && this.listClass == List.class) {
            ArrayList arrayList = new ArrayList();
            Function function = this.builder;
            return function != null ? function.apply(arrayList) : arrayList;
        }
        ObjectReaderProvider defaultObjectReaderProvider = JSONFactory.getDefaultObjectReaderProvider();
        if (this.instanceType == ArrayList.class) {
            collection2 = new ArrayList(collection.size());
        } else {
            collection2 = (Collection) createInstance(0L);
        }
        for (Object obj : collection) {
            if (obj == null) {
                collection2.add(null);
            } else {
                Class<?> cls = obj.getClass();
                if ((cls == JSONObject.class || cls == TypeUtils.CLASS_JSON_OBJECT_1x) && this.itemClass != cls) {
                    if (this.itemObjectReader == null) {
                        this.itemObjectReader = defaultObjectReaderProvider.getObjectReader(this.itemType);
                    }
                    obj = this.itemObjectReader.createInstance((Map) obj, j);
                } else {
                    Type type = this.itemType;
                    if (cls != type) {
                        Function typeConvert = defaultObjectReaderProvider.getTypeConvert(cls, type);
                        if (typeConvert != null) {
                            obj = typeConvert.apply(obj);
                        } else if (obj instanceof Map) {
                            Map map = (Map) obj;
                            if (this.itemObjectReader == null) {
                                this.itemObjectReader = defaultObjectReaderProvider.getObjectReader(this.itemType);
                            }
                            obj = this.itemObjectReader.createInstance(map, 0L);
                        } else if (obj instanceof Collection) {
                            if (this.itemObjectReader == null) {
                                this.itemObjectReader = defaultObjectReaderProvider.getObjectReader(this.itemType);
                            }
                            obj = this.itemObjectReader.createInstance((Collection) obj, j);
                        } else if (!this.itemClass.isInstance(obj)) {
                            if (Enum.class.isAssignableFrom(this.itemClass)) {
                                if (this.itemObjectReader == null) {
                                    this.itemObjectReader = defaultObjectReaderProvider.getObjectReader(this.itemType);
                                }
                                ObjectReader objectReader = this.itemObjectReader;
                                if (objectReader instanceof ObjectReaderImplEnum) {
                                    obj = ((ObjectReaderImplEnum) objectReader).getEnum((String) obj);
                                } else {
                                    throw new JSONException("can not convert from " + cls + " to " + this.itemType);
                                }
                            } else {
                                throw new JSONException("can not convert from " + cls + " to " + this.itemType);
                            }
                        }
                    }
                }
                collection2.add(obj);
            }
        }
        Function function2 = this.builder;
        return function2 != null ? function2.apply(collection2) : collection2;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(long j) {
        JSONException jSONException;
        Class cls = this.instanceType;
        if (cls == ArrayList.class) {
            return JDKUtils.JVM_VERSION == 8 ? new ArrayList(10) : new ArrayList();
        }
        if (cls == LinkedList.class) {
            return new LinkedList();
        }
        if (cls == HashSet.class) {
            return new HashSet();
        }
        if (cls == LinkedHashSet.class) {
            return new LinkedHashSet();
        }
        if (cls == TreeSet.class) {
            return new TreeSet();
        }
        Object obj = this.listSingleton;
        if (obj != null) {
            return obj;
        }
        if (cls != null) {
            if (this.constructor == null && !BeanUtils.hasPublicDefaultConstructor(this.instanceType)) {
                this.constructor = BeanUtils.getDefaultConstructor(this.instanceType, false);
                this.constructor.setAccessible(true);
            }
            if (this.instanceError) {
                jSONException = null;
            } else {
                try {
                    if (this.constructor != null) {
                        return this.constructor.newInstance(new Object[0]);
                    }
                    return this.instanceType.newInstance();
                } catch (IllegalAccessException | InstantiationException | RuntimeException | InvocationTargetException unused) {
                    this.instanceError = true;
                    jSONException = new JSONException("create list error, type " + this.instanceType);
                }
            }
            if (this.instanceError && List.class.isAssignableFrom(this.instanceType.getSuperclass())) {
                try {
                    return this.instanceType.getSuperclass().newInstance();
                } catch (IllegalAccessException | InstantiationException unused2) {
                    this.instanceError = true;
                    jSONException = new JSONException("create list error, type " + this.instanceType);
                }
            }
            if (jSONException != null) {
                throw jSONException;
            }
        }
        return new ArrayList();
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        JSONArray jSONArray;
        Collection collection;
        Object readJSONBObject;
        Collection collection2;
        ArrayList arrayList;
        Object readJSONBObject2;
        if (jSONReader.nextIfNull()) {
            return null;
        }
        ObjectReader checkAutoType = jSONReader.checkAutoType(this.listClass, 0L, j);
        Function function = this.builder;
        Class cls = this.instanceType;
        if (checkAutoType != null) {
            if (checkAutoType instanceof ObjectReaderImplList) {
                ObjectReaderImplList objectReaderImplList = (ObjectReaderImplList) checkAutoType;
                cls = objectReaderImplList.instanceType;
                function = objectReaderImplList.builder;
            } else {
                cls = checkAutoType.getObjectClass();
            }
            if (cls == CLASS_UNMODIFIABLE_COLLECTION) {
                cls = ArrayList.class;
                function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda5
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        Collection unmodifiableCollection;
                        unmodifiableCollection = Collections.unmodifiableCollection((Collection) obj2);
                        return unmodifiableCollection;
                    }
                };
            } else if (cls == CLASS_UNMODIFIABLE_LIST) {
                cls = ArrayList.class;
                function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda6
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        List unmodifiableList;
                        unmodifiableList = Collections.unmodifiableList((List) obj2);
                        return unmodifiableList;
                    }
                };
            } else if (cls == CLASS_UNMODIFIABLE_SET) {
                cls = LinkedHashSet.class;
                function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda7
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        Set unmodifiableSet;
                        unmodifiableSet = Collections.unmodifiableSet((Set) obj2);
                        return unmodifiableSet;
                    }
                };
            } else if (cls == CLASS_UNMODIFIABLE_SORTED_SET) {
                cls = TreeSet.class;
                function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda8
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        SortedSet unmodifiableSortedSet;
                        unmodifiableSortedSet = Collections.unmodifiableSortedSet((SortedSet) obj2);
                        return unmodifiableSortedSet;
                    }
                };
            } else if (cls == CLASS_UNMODIFIABLE_NAVIGABLE_SET) {
                cls = TreeSet.class;
                function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda9
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        NavigableSet unmodifiableNavigableSet;
                        unmodifiableNavigableSet = Collections.unmodifiableNavigableSet((NavigableSet) obj2);
                        return unmodifiableNavigableSet;
                    }
                };
            } else if (cls == CLASS_SINGLETON) {
                cls = ArrayList.class;
                function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda10
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        Collection singleton;
                        singleton = Collections.singleton(((Collection) obj2).iterator().next());
                        return singleton;
                    }
                };
            } else if (cls == CLASS_SINGLETON_LIST) {
                cls = ArrayList.class;
                function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda12
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        List singletonList;
                        singletonList = Collections.singletonList(((List) obj2).get(0));
                        return singletonList;
                    }
                };
            } else {
                String typeName = cls.getTypeName();
                typeName.hashCode();
                if (typeName.equals("kotlin.collections.EmptyList") || typeName.equals("kotlin.collections.EmptySet")) {
                    return checkAutoType.readObject(jSONReader, type, obj, j);
                }
            }
        }
        Class cls2 = cls;
        JSONReader jSONReader2 = jSONReader;
        int startArray = jSONReader2.startArray();
        if (startArray > 0 && this.itemObjectReader == null) {
            this.itemObjectReader = jSONReader2.getContext().getObjectReader(this.itemType);
        }
        int i = 0;
        if (cls2 == CLASS_ARRAYS_LIST) {
            Object[] objArr = new Object[startArray];
            List asList = Arrays.asList(objArr);
            while (i < startArray) {
                if (jSONReader2.isReference()) {
                    String readReference = jSONReader2.readReference();
                    if ("..".equals(readReference)) {
                        readJSONBObject2 = asList;
                    } else {
                        jSONReader2.addResolveTask(asList, i, JSONPath.of(readReference));
                        readJSONBObject2 = null;
                    }
                } else {
                    readJSONBObject2 = this.itemObjectReader.readJSONBObject(jSONReader2, this.itemType, Integer.valueOf(i), j);
                }
                objArr[i] = readJSONBObject2;
                i++;
            }
            return asList;
        }
        if (cls2 == ArrayList.class) {
            if (startArray > 0) {
                collection = arrayList;
                arrayList = new ArrayList(startArray);
            } else {
                collection = arrayList;
                arrayList = new ArrayList();
            }
        } else if (cls2 == JSONArray.class) {
            if (startArray > 0) {
                collection = jSONArray;
                jSONArray = new JSONArray(startArray);
            } else {
                collection = jSONArray;
                jSONArray = new JSONArray();
            }
        } else if (cls2 == HashSet.class) {
            collection = new HashSet();
        } else if (cls2 == LinkedHashSet.class) {
            collection = new LinkedHashSet();
        } else if (cls2 == TreeSet.class) {
            collection = new TreeSet();
        } else if (cls2 == CLASS_EMPTY_SET) {
            collection = Collections.emptySet();
        } else if (cls2 == CLASS_EMPTY_LIST) {
            collection = Collections.emptyList();
        } else if (cls2 == CLASS_SINGLETON_LIST) {
            ArrayList arrayList2 = new ArrayList();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda13
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    Collection singletonList;
                    singletonList = Collections.singletonList(((Collection) obj2).iterator().next());
                    return singletonList;
                }
            };
            collection = arrayList2;
        } else if (cls2 == CLASS_UNMODIFIABLE_LIST) {
            ArrayList arrayList3 = new ArrayList();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    List unmodifiableList;
                    unmodifiableList = Collections.unmodifiableList((List) obj2);
                    return unmodifiableList;
                }
            };
            collection = arrayList3;
        } else if (cls2 != null && EnumSet.class.isAssignableFrom(cls2)) {
            HashSet hashSet = new HashSet();
            function = new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderImplList$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    return ObjectReaderImplList.this.m2805x4f914009(obj2);
                }
            };
            collection = hashSet;
        } else if (cls2 != null && cls2 != this.listType) {
            String name = cls2.getName();
            name.hashCode();
            if (!name.equals("kotlin.collections.EmptyList")) {
                if (name.equals("kotlin.collections.EmptySet")) {
                    collection = getKotlinEmptySet(cls2);
                } else {
                    try {
                        collection = (Collection) cls2.newInstance();
                    } catch (IllegalAccessException | InstantiationException e) {
                        throw new JSONException(jSONReader2.info("create instance error " + cls2), e);
                    }
                }
            } else {
                collection = getKotlinEmptyList(cls2);
            }
        } else {
            collection = (Collection) createInstance(jSONReader2.getContext().getFeatures() | j);
        }
        Function function2 = function;
        Collection collection3 = collection;
        ObjectReader objectReader = this.itemObjectReader;
        Type type2 = this.itemType;
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length == 1 && (type2 = actualTypeArguments[0]) != this.itemType) {
                objectReader = jSONReader2.getObjectReader(type2);
            }
        }
        while (true) {
            ObjectReader objectReader2 = objectReader;
            Type type3 = type2;
            if (i >= startArray) {
                break;
            }
            if (jSONReader2.isReference()) {
                String readReference2 = jSONReader2.readReference();
                if ("..".equals(readReference2)) {
                    collection2 = collection3;
                } else {
                    jSONReader2.addResolveTask(collection3, i, JSONPath.of(readReference2));
                    if (collection3 instanceof List) {
                        collection2 = null;
                    } else {
                        objectReader = objectReader2;
                        type2 = type3;
                        i++;
                        jSONReader2 = jSONReader;
                    }
                }
                Collection collection4 = collection2;
                objectReader = objectReader2;
                readJSONBObject = collection4;
                type2 = type3;
            } else {
                ObjectReader checkAutoType2 = jSONReader.checkAutoType(this.itemClass, this.itemClassNameHash, j);
                if (checkAutoType2 != null) {
                    type2 = type3;
                    objectReader = objectReader2;
                    readJSONBObject = checkAutoType2.readJSONBObject(jSONReader, type2, Integer.valueOf(i), j);
                } else {
                    type2 = type3;
                    objectReader = objectReader2;
                    readJSONBObject = objectReader.readJSONBObject(jSONReader, type2, Integer.valueOf(i), j);
                }
            }
            collection3.add(readJSONBObject);
            i++;
            jSONReader2 = jSONReader;
        }
        return function2 != null ? function2.apply(collection3) : collection3;
    }

    /* renamed from: lambda$readJSONBObject$12$com-alibaba-fastjson2-reader-ObjectReaderImplList, reason: not valid java name */
    /* synthetic */ Object m2805x4f914009(Object obj) {
        Collection collection = (Collection) obj;
        if (collection.isEmpty()) {
            Type type = this.itemType;
            if (type instanceof Class) {
                return EnumSet.noneOf((Class) type);
            }
        }
        return EnumSet.copyOf(collection);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Collection collection;
        Object readObject;
        JSONReader.Context context = jSONReader.getContext();
        if (this.itemObjectReader == null) {
            this.itemObjectReader = context.getObjectReader(this.itemType);
        }
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, 0L);
        }
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfSet()) {
            collection = new HashSet();
        } else {
            collection = (Collection) createInstance(context.getFeatures() | j);
        }
        char current = jSONReader.current();
        if (current == '\"') {
            String readString = jSONReader.readString();
            if (this.itemClass == String.class) {
                jSONReader.nextIfComma();
                collection.add(readString);
                return collection;
            }
            if (readString.isEmpty()) {
                jSONReader.nextIfComma();
                return null;
            }
            ObjectReaderProvider provider = context.getProvider();
            if (this.itemClass.isEnum()) {
                ObjectReader objectReader = provider.getObjectReader(this.itemClass);
                if (objectReader instanceof ObjectReaderImplEnum) {
                    Enum r0 = ((ObjectReaderImplEnum) objectReader).getEnum(readString);
                    if (r0 == null) {
                        if (JSONReader.Feature.ErrorOnEnumNotMatch.isEnabled(jSONReader.features(j))) {
                            throw new JSONException(jSONReader.info("enum not match : " + readString));
                        }
                        return null;
                    }
                    collection.add(r0);
                    return collection;
                }
            }
            Function typeConvert = provider.getTypeConvert(String.class, this.itemType);
            if (typeConvert != null) {
                Object apply = typeConvert.apply(readString);
                jSONReader.nextIfComma();
                collection.add(apply);
                return collection;
            }
            throw new JSONException(jSONReader.info());
        }
        int i = 0;
        if (current == '[') {
            jSONReader.next();
            ObjectReader objectReader2 = this.itemObjectReader;
            Type type2 = this.itemType;
            if (type != this.listType && (type instanceof ParameterizedType)) {
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                if (actualTypeArguments.length == 1 && (type2 = actualTypeArguments[0]) != this.itemType) {
                    objectReader2 = jSONReader.getObjectReader(type2);
                }
            }
            ObjectReader objectReader3 = objectReader2;
            Type type3 = type2;
            while (!jSONReader.nextIfArrayEnd()) {
                if (type3 == String.class) {
                    readObject = jSONReader.readString();
                } else if (objectReader3 != null) {
                    if (jSONReader.isReference()) {
                        String readReference = jSONReader.readReference();
                        if ("..".equals(readReference)) {
                            readObject = this;
                        } else {
                            jSONReader.addResolveTask(collection, i, JSONPath.of(readReference));
                            i++;
                        }
                    } else {
                        readObject = objectReader3.readObject(jSONReader, type3, Integer.valueOf(i), 0L);
                    }
                } else {
                    throw new JSONException(jSONReader.info("TODO : " + type3));
                }
                collection.add(readObject);
                i++;
            }
            jSONReader.nextIfComma();
            Function function = this.builder;
            if (function != null) {
                return function.apply(collection);
            }
        } else {
            Class cls = this.itemClass;
            if ((cls != Object.class && this.itemObjectReader != null) || (cls == Object.class && jSONReader.isObject())) {
                collection.add(this.itemObjectReader.readObject(jSONReader, this.itemType, 0, 0L));
                Function function2 = this.builder;
                if (function2 != null) {
                    return (Collection) function2.apply(collection);
                }
            } else {
                throw new JSONException(jSONReader.info());
            }
        }
        return collection;
    }
}
