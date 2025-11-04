package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.PropertyNamingStrategy;
import com.alibaba.fastjson2.codec.BeanInfo;
import com.alibaba.fastjson2.codec.FieldInfo;
import com.alibaba.fastjson2.function.FieldBiConsumer;
import com.alibaba.fastjson2.function.FieldConsumer;
import com.alibaba.fastjson2.modules.ObjectCodecProvider;
import com.alibaba.fastjson2.modules.ObjectReaderAnnotationProcessor;
import com.alibaba.fastjson2.modules.ObjectReaderModule;
import com.alibaba.fastjson2.support.LambdaMiscCodec;
import com.alibaba.fastjson2.util.BeanUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public class ObjectReaderProvider implements ObjectCodecProvider {
    static final String[] AUTO_TYPE_ACCEPT_LIST;
    static JSONReader.AutoTypeBeforeHandler DEFAULT_AUTO_TYPE_BEFORE_HANDLER;
    static Consumer<Class> DEFAULT_AUTO_TYPE_HANDLER;
    static boolean DEFAULT_AUTO_TYPE_HANDLER_INIT_ERROR;
    static final String[] DENYS;
    static final ClassLoader FASTJSON2_CLASS_LOADER = JSON.class.getClassLoader();
    public static final boolean SAFE_MODE;
    static ObjectReaderCachePair readerCache;
    private long[] acceptHashCodes;
    final ObjectReaderCreator creator;
    PropertyNamingStrategy namingStrategy;
    final ConcurrentMap<Type, ObjectReader> cache = new ConcurrentHashMap();
    final ConcurrentMap<Type, ObjectReader> cacheFieldBased = new ConcurrentHashMap();
    final ConcurrentMap<Integer, ConcurrentHashMap<Long, ObjectReader>> tclHashCaches = new ConcurrentHashMap();
    final ConcurrentMap<Long, ObjectReader> hashCache = new ConcurrentHashMap();
    final ConcurrentMap<Class, Class> mixInCache = new ConcurrentHashMap();
    final LRUAutoTypeCache autoTypeList = new LRUAutoTypeCache(1024);
    private final ConcurrentMap<Type, Map<Type, Function>> typeConverts = new ConcurrentHashMap();
    final List<ObjectReaderModule> modules = new CopyOnWriteArrayList();
    boolean disableReferenceDetect = JSONFactory.isDisableReferenceDetect();
    boolean disableArrayMapping = JSONFactory.isDisableArrayMapping();
    boolean disableJSONB = JSONFactory.isDisableJSONB();
    boolean disableAutoType = JSONFactory.isDisableAutoType();
    boolean disableSmartMatch = JSONFactory.isDisableSmartMatch();
    private JSONReader.AutoTypeBeforeHandler autoTypeBeforeHandler = DEFAULT_AUTO_TYPE_BEFORE_HANDLER;
    private Consumer<Class> autoTypeHandler = DEFAULT_AUTO_TYPE_HANDLER;

    @Deprecated
    public void addAutoTypeDeny(String str) {
    }

    static {
        Class loadClass;
        Class loadClass2;
        String property = System.getProperty(JSONFactory.PROPERTY_DENY_PROPERTY);
        if (property == null) {
            property = JSONFactory.Conf.getProperty(JSONFactory.PROPERTY_DENY_PROPERTY);
        }
        if (property != null && property.length() > 0) {
            DENYS = property.split(",");
        } else {
            DENYS = new String[0];
        }
        String property2 = System.getProperty(JSONFactory.PROPERTY_AUTO_TYPE_ACCEPT);
        if (property2 == null) {
            property2 = JSONFactory.Conf.getProperty(JSONFactory.PROPERTY_AUTO_TYPE_ACCEPT);
        }
        if (property2 != null && property2.length() > 0) {
            AUTO_TYPE_ACCEPT_LIST = property2.split(",");
        } else {
            AUTO_TYPE_ACCEPT_LIST = new String[0];
        }
        String property3 = System.getProperty(JSONFactory.PROPERTY_AUTO_TYPE_BEFORE_HANDLER);
        if (property3 == null || property3.isEmpty()) {
            property3 = JSONFactory.Conf.getProperty(JSONFactory.PROPERTY_AUTO_TYPE_BEFORE_HANDLER);
        }
        if (property3 != null) {
            property3 = property3.trim();
        }
        if (property3 != null && !property3.isEmpty() && (loadClass2 = TypeUtils.loadClass(property3)) != null) {
            try {
                DEFAULT_AUTO_TYPE_BEFORE_HANDLER = (JSONReader.AutoTypeBeforeHandler) loadClass2.newInstance();
            } catch (Exception unused) {
                DEFAULT_AUTO_TYPE_HANDLER_INIT_ERROR = true;
            }
        }
        String property4 = System.getProperty(JSONFactory.PROPERTY_AUTO_TYPE_HANDLER);
        if (property4 == null || property4.isEmpty()) {
            property4 = JSONFactory.Conf.getProperty(JSONFactory.PROPERTY_AUTO_TYPE_HANDLER);
        }
        if (property4 != null) {
            property4 = property4.trim();
        }
        if (property4 != null && !property4.isEmpty() && (loadClass = TypeUtils.loadClass(property4)) != null) {
            try {
                DEFAULT_AUTO_TYPE_HANDLER = (Consumer) loadClass.newInstance();
            } catch (Exception unused2) {
                DEFAULT_AUTO_TYPE_HANDLER_INIT_ERROR = true;
            }
        }
        String property5 = System.getProperty("fastjson.parser.safeMode");
        if (property5 == null || property5.isEmpty()) {
            property5 = JSONFactory.Conf.getProperty("fastjson.parser.safeMode");
        }
        if (property5 == null || property5.isEmpty()) {
            property5 = System.getProperty("fastjson2.parser.safeMode");
        }
        if (property5 == null || property5.isEmpty()) {
            property5 = JSONFactory.Conf.getProperty("fastjson2.parser.safeMode");
        }
        if (property5 != null) {
            property5 = property5.trim();
        }
        SAFE_MODE = "true".equals(property5);
    }

    private static final class ObjectReaderCachePair {
        final long hashCode;
        volatile int missCount;
        final ObjectReader reader;

        public ObjectReaderCachePair(long j, ObjectReader objectReader) {
            this.hashCode = j;
            this.reader = objectReader;
        }
    }

    public void registerIfAbsent(long j, ObjectReader objectReader) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null && contextClassLoader != JSON.class.getClassLoader()) {
            int identityHashCode = System.identityHashCode(contextClassLoader);
            ConcurrentHashMap<Long, ObjectReader> concurrentHashMap = this.tclHashCaches.get(Integer.valueOf(identityHashCode));
            if (concurrentHashMap == null) {
                this.tclHashCaches.putIfAbsent(Integer.valueOf(identityHashCode), new ConcurrentHashMap<>());
                concurrentHashMap = this.tclHashCaches.get(Integer.valueOf(identityHashCode));
            }
            concurrentHashMap.putIfAbsent(Long.valueOf(j), objectReader);
        }
        this.hashCache.putIfAbsent(Long.valueOf(j), objectReader);
    }

    public void addAutoTypeAccept(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        long hashCode64 = Fnv.hashCode64(str);
        if (Arrays.binarySearch(this.acceptHashCodes, hashCode64) < 0) {
            long[] jArr = this.acceptHashCodes;
            int length = jArr.length;
            long[] jArr2 = new long[length + 1];
            jArr2[length] = hashCode64;
            System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
            Arrays.sort(jArr2);
            this.acceptHashCodes = jArr2;
        }
    }

    public Consumer<Class> getAutoTypeHandler() {
        return this.autoTypeHandler;
    }

    public void setAutoTypeHandler(Consumer<Class> consumer) {
        this.autoTypeHandler = consumer;
    }

    @Override // com.alibaba.fastjson2.modules.ObjectCodecProvider
    public Class getMixIn(Class cls) {
        return this.mixInCache.get(cls);
    }

    public void cleanupMixIn() {
        this.mixInCache.clear();
    }

    public void mixIn(Class cls, Class cls2) {
        if (cls2 == null) {
            this.mixInCache.remove(cls);
        } else {
            this.mixInCache.put(cls, cls2);
        }
        this.cache.remove(cls);
        this.cacheFieldBased.remove(cls);
    }

    public void registerSeeAlsoSubType(Class cls) {
        registerSeeAlsoSubType(cls, null);
    }

    public void registerSeeAlsoSubType(Class cls, String str) {
        ObjectReaderSeeAlso objectReaderSeeAlso;
        ObjectReaderSeeAlso addSubType;
        Class superclass = cls.getSuperclass();
        if (superclass == null) {
            throw new JSONException("superclass is null");
        }
        ObjectReader objectReader = getObjectReader(superclass);
        if (!(objectReader instanceof ObjectReaderSeeAlso) || (addSubType = (objectReaderSeeAlso = (ObjectReaderSeeAlso) objectReader).addSubType(cls, str)) == objectReaderSeeAlso) {
            return;
        }
        if (this.cache.containsKey(superclass)) {
            this.cache.put(superclass, addSubType);
        } else {
            this.cacheFieldBased.put(cls, addSubType);
        }
    }

    public ObjectReader register(Type type, ObjectReader objectReader, boolean z) {
        ConcurrentMap<Type, ObjectReader> concurrentMap = z ? this.cacheFieldBased : this.cache;
        if (objectReader == null) {
            return concurrentMap.remove(type);
        }
        return concurrentMap.put(type, objectReader);
    }

    public ObjectReader register(Type type, ObjectReader objectReader) {
        return register(type, objectReader, false);
    }

    public ObjectReader registerIfAbsent(Type type, ObjectReader objectReader) {
        return registerIfAbsent(type, objectReader, false);
    }

    public ObjectReader registerIfAbsent(Type type, ObjectReader objectReader, boolean z) {
        return (z ? this.cacheFieldBased : this.cache).putIfAbsent(type, objectReader);
    }

    public ObjectReader unregisterObjectReader(Type type) {
        return unregisterObjectReader(type, false);
    }

    public ObjectReader unregisterObjectReader(Type type, boolean z) {
        return (z ? this.cacheFieldBased : this.cache).remove(type);
    }

    public boolean unregisterObjectReader(Type type, ObjectReader objectReader) {
        return unregisterObjectReader(type, objectReader, false);
    }

    public boolean unregisterObjectReader(Type type, ObjectReader objectReader, boolean z) {
        return (z ? this.cacheFieldBased : this.cache).remove(type, objectReader);
    }

    public boolean register(ObjectReaderModule objectReaderModule) {
        for (int size = this.modules.size() - 1; size >= 0; size--) {
            if (this.modules.get(size) == objectReaderModule) {
                return false;
            }
        }
        objectReaderModule.init(this);
        this.modules.add(0, objectReaderModule);
        return true;
    }

    public boolean unregister(ObjectReaderModule objectReaderModule) {
        return this.modules.remove(objectReaderModule);
    }

    public void cleanup(Class cls) {
        this.mixInCache.remove(cls);
        this.cache.remove(cls);
        this.cacheFieldBased.remove(cls);
        Iterator<ConcurrentHashMap<Long, ObjectReader>> it = this.tclHashCaches.values().iterator();
        while (it.hasNext()) {
            Iterator<Map.Entry<Long, ObjectReader>> it2 = it.next().entrySet().iterator();
            while (it2.hasNext()) {
                if (it2.next().getValue().getObjectClass() == cls) {
                    it2.remove();
                }
            }
        }
        BeanUtils.cleanupCache(cls);
    }

    public void clear() {
        this.mixInCache.clear();
        this.cache.clear();
        this.cacheFieldBased.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean match(Type type, ObjectReader objectReader, ClassLoader classLoader) {
        Class<?> cls = TypeUtils.getClass(type);
        if (cls != null && cls.getClassLoader() == classLoader) {
            return true;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (match(parameterizedType.getRawType(), objectReader, classLoader)) {
                return true;
            }
            for (Type type2 : parameterizedType.getActualTypeArguments()) {
                if (match(type2, objectReader, classLoader)) {
                    return true;
                }
            }
        }
        if (objectReader instanceof ObjectReaderImplMapTyped) {
            ObjectReaderImplMapTyped objectReaderImplMapTyped = (ObjectReaderImplMapTyped) objectReader;
            Class cls2 = objectReaderImplMapTyped.valueClass;
            if (cls2 != null && cls2.getClassLoader() == classLoader) {
                return true;
            }
            Class<?> cls3 = TypeUtils.getClass(objectReaderImplMapTyped.keyType);
            return cls3 != null && cls3.getClassLoader() == classLoader;
        }
        if (objectReader instanceof ObjectReaderImplList) {
            ObjectReaderImplList objectReaderImplList = (ObjectReaderImplList) objectReader;
            return objectReaderImplList.itemClass != null && objectReaderImplList.itemClass.getClassLoader() == classLoader;
        }
        if (objectReader instanceof ObjectReaderImplOptional) {
            Class cls4 = ((ObjectReaderImplOptional) objectReader).itemClass;
            return cls4 != null && cls4.getClassLoader() == classLoader;
        }
        if (objectReader instanceof ObjectReaderAdapter) {
            for (FieldReader fieldReader : ((ObjectReaderAdapter) objectReader).fieldReaders) {
                if (fieldReader.fieldClass != null && fieldReader.fieldClass.getClassLoader() == classLoader) {
                    return true;
                }
                Type type3 = fieldReader.fieldType;
                if ((type3 instanceof ParameterizedType) && match(type3, null, classLoader)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void cleanup(final ClassLoader classLoader) {
        this.mixInCache.entrySet().removeIf(new Predicate() { // from class: com.alibaba.fastjson2.reader.ObjectReaderProvider$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ObjectReaderProvider.lambda$cleanup$0(classLoader, (Map.Entry) obj);
            }
        });
        this.cache.entrySet().removeIf(new Predicate() { // from class: com.alibaba.fastjson2.reader.ObjectReaderProvider$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean match;
                match = ObjectReaderProvider.match((Type) r2.getKey(), (ObjectReader) ((Map.Entry) obj).getValue(), classLoader);
                return match;
            }
        });
        this.cacheFieldBased.entrySet().removeIf(new Predicate() { // from class: com.alibaba.fastjson2.reader.ObjectReaderProvider$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean match;
                match = ObjectReaderProvider.match((Type) r2.getKey(), (ObjectReader) ((Map.Entry) obj).getValue(), classLoader);
                return match;
            }
        });
        this.tclHashCaches.remove(Integer.valueOf(System.identityHashCode(classLoader)));
        BeanUtils.cleanupCache(classLoader);
    }

    static /* synthetic */ boolean lambda$cleanup$0(ClassLoader classLoader, Map.Entry entry) {
        return ((Class) entry.getKey()).getClassLoader() == classLoader;
    }

    public ObjectReaderCreator getCreator() {
        ObjectReaderCreator contextReaderCreator = JSONFactory.getContextReaderCreator();
        return contextReaderCreator != null ? contextReaderCreator : this.creator;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x00dc, code lost:
    
        if (r0.equals("reflect") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x00ee, code lost:
    
        r0 = com.alibaba.fastjson2.reader.ObjectReaderCreator.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ec, code lost:
    
        if (r0.equals("lambda") != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ObjectReaderProvider() {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.ObjectReaderProvider.<init>():void");
    }

    public ObjectReaderProvider(ObjectReaderCreator objectReaderCreator) {
        long[] jArr;
        String[] strArr = AUTO_TYPE_ACCEPT_LIST;
        if (strArr == null) {
            jArr = new long[1];
        } else {
            jArr = new long[strArr.length + 1];
            int i = 0;
            while (true) {
                String[] strArr2 = AUTO_TYPE_ACCEPT_LIST;
                if (i >= strArr2.length) {
                    break;
                }
                jArr[i] = Fnv.hashCode64(strArr2[i]);
                i++;
            }
        }
        jArr[jArr.length - 1] = -6293031534589903644L;
        Arrays.sort(jArr);
        this.acceptHashCodes = jArr;
        this.hashCache.put(Long.valueOf(ObjectArrayReader.TYPE_HASH_CODE), ObjectArrayReader.INSTANCE);
        this.hashCache.put(-4834614249632438472L, ObjectReaderImplString.INSTANCE);
        this.hashCache.put(Long.valueOf(Fnv.hashCode64(TypeUtils.getTypeName(HashMap.class))), ObjectReaderImplMap.INSTANCE);
        this.creator = objectReaderCreator;
        this.modules.add(new ObjectReaderBaseModule(this));
        init();
    }

    void init() {
        Iterator<ObjectReaderModule> it = this.modules.iterator();
        while (it.hasNext()) {
            it.next().init(this);
        }
    }

    public Function getTypeConvert(Type type, Type type2) {
        Map<Type, Function> map = this.typeConverts.get(type);
        if (map == null) {
            return null;
        }
        return map.get(type2);
    }

    public Function registerTypeConvert(Type type, Type type2, Function function) {
        Map<Type, Function> map = this.typeConverts.get(type);
        if (map == null) {
            this.typeConverts.putIfAbsent(type, new ConcurrentHashMap());
            map = this.typeConverts.get(type);
        }
        return map.put(type2, function);
    }

    public ObjectReader getObjectReader(long j) {
        ObjectReaderCachePair objectReaderCachePair = readerCache;
        ObjectReader objectReader = null;
        if (objectReaderCachePair != null) {
            if (objectReaderCachePair.hashCode == j) {
                return objectReaderCachePair.reader;
            }
            int i = objectReaderCachePair.missCount;
            objectReaderCachePair.missCount = i + 1;
            if (i > 16) {
                readerCache = null;
            }
        }
        Long valueOf = Long.valueOf(j);
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null && contextClassLoader != FASTJSON2_CLASS_LOADER) {
            ConcurrentHashMap<Long, ObjectReader> concurrentHashMap = this.tclHashCaches.get(Integer.valueOf(System.identityHashCode(contextClassLoader)));
            if (concurrentHashMap != null) {
                objectReader = concurrentHashMap.get(valueOf);
            }
        }
        if (objectReader == null) {
            objectReader = this.hashCache.get(valueOf);
        }
        if (objectReader != null && readerCache == null) {
            readerCache = new ObjectReaderCachePair(j, objectReader);
        }
        return objectReader;
    }

    public ObjectReader getObjectReader(String str, Class<?> cls, long j) {
        Class<?> checkAutoType = checkAutoType(str, cls, j);
        if (checkAutoType == null) {
            return null;
        }
        ObjectReader objectReader = getObjectReader(checkAutoType, (j & JSONReader.Feature.FieldBased.mask) != 0);
        if (checkAutoType != cls) {
            registerIfAbsent(Fnv.hashCode64(str), objectReader);
        }
        return objectReader;
    }

    final void afterAutoType(String str, Class cls) {
        Consumer<Class> consumer = this.autoTypeHandler;
        if (consumer != null) {
            consumer.accept(cls);
        }
        synchronized (this.autoTypeList) {
            this.autoTypeList.putIfAbsent(str, new Date());
        }
    }

    public Class<?> checkAutoType(String str, Class<?> cls, long j) {
        Class<?> loadClass;
        Class<?> apply;
        if (str == null || str.isEmpty()) {
            return null;
        }
        JSONReader.AutoTypeBeforeHandler autoTypeBeforeHandler = this.autoTypeBeforeHandler;
        if (autoTypeBeforeHandler != null && (apply = autoTypeBeforeHandler.apply(str, cls, j)) != null) {
            afterAutoType(str, apply);
            return apply;
        }
        if (SAFE_MODE) {
            return null;
        }
        int length = str.length();
        if (length >= 192) {
            throw new JSONException("autoType is not support. " + str);
        }
        if (str.charAt(0) == '[') {
            checkAutoType(str.substring(1), null, j);
        }
        if (cls != null && cls.getName().equals(str)) {
            afterAutoType(str, cls);
            return cls;
        }
        long j2 = 0;
        boolean z = (JSONReader.Feature.SupportAutoType.mask & j) != 0;
        long j3 = Fnv.MAGIC_PRIME;
        long j4 = Fnv.MAGIC_HASH_CODE;
        if (z) {
            long j5 = -3750763034362895579L;
            int i = 0;
            while (i < length) {
                long j6 = j2;
                char charAt = str.charAt(i);
                if (charAt == '$') {
                    charAt = '.';
                }
                long j7 = (j5 ^ charAt) * j3;
                long j8 = j3;
                if (Arrays.binarySearch(this.acceptHashCodes, j7) >= 0 && (loadClass = TypeUtils.loadClass(str)) != null) {
                    if (cls != null && !cls.isAssignableFrom(loadClass)) {
                        throw new JSONException("type not match. " + str + " -> " + cls.getName());
                    }
                    afterAutoType(str, loadClass);
                    return loadClass;
                }
                i++;
                j3 = j8;
                j5 = j7;
                j2 = j6;
            }
        }
        long j9 = j2;
        long j10 = j3;
        if (!z) {
            int i2 = 0;
            while (i2 < length) {
                char charAt2 = str.charAt(i2);
                if (charAt2 == '$') {
                    charAt2 = '.';
                }
                long j11 = (j4 ^ charAt2) * j10;
                if (Arrays.binarySearch(this.acceptHashCodes, j11) >= 0) {
                    Class<?> loadClass2 = TypeUtils.loadClass(str);
                    if (loadClass2 != null && cls != null && !cls.isAssignableFrom(loadClass2)) {
                        throw new JSONException("type not match. " + str + " -> " + cls.getName());
                    }
                    afterAutoType(str, loadClass2);
                    return loadClass2;
                }
                i2++;
                j4 = j11;
            }
        }
        if (!z) {
            return null;
        }
        Class<?> mapping = TypeUtils.getMapping(str);
        if (mapping != null) {
            if (cls != null && cls != Object.class && mapping != HashMap.class && !cls.isAssignableFrom(mapping)) {
                throw new JSONException("type not match. " + str + " -> " + cls.getName());
            }
            afterAutoType(str, mapping);
            return mapping;
        }
        Class<?> loadClass3 = TypeUtils.loadClass(str);
        if (loadClass3 != null) {
            if (ClassLoader.class.isAssignableFrom(loadClass3) || JDKUtils.isSQLDataSourceOrRowSet(loadClass3)) {
                throw new JSONException("autoType is not support. " + str);
            }
            if (cls != null) {
                if (cls.isAssignableFrom(loadClass3)) {
                    afterAutoType(str, loadClass3);
                    return loadClass3;
                }
                if ((j & JSONReader.Feature.IgnoreAutoTypeNotMatch.mask) != j9) {
                    return cls;
                }
                throw new JSONException("type not match. " + str + " -> " + cls.getName());
            }
        }
        afterAutoType(str, loadClass3);
        return loadClass3;
    }

    public List<ObjectReaderModule> getModules() {
        return this.modules;
    }

    public void getBeanInfo(BeanInfo beanInfo, Class cls) {
        for (int i = 0; i < this.modules.size(); i++) {
            this.modules.get(i).getBeanInfo(beanInfo, cls);
        }
    }

    public void getFieldInfo(FieldInfo fieldInfo, Class cls, Field field) {
        for (int i = 0; i < this.modules.size(); i++) {
            this.modules.get(i).getFieldInfo(fieldInfo, cls, field);
        }
    }

    public void getFieldInfo(FieldInfo fieldInfo, Class cls, Constructor constructor, int i, Parameter parameter) {
        int i2 = 0;
        while (i2 < this.modules.size()) {
            ObjectReaderAnnotationProcessor annotationProcessor = this.modules.get(i2).getAnnotationProcessor();
            FieldInfo fieldInfo2 = fieldInfo;
            Class cls2 = cls;
            Constructor constructor2 = constructor;
            int i3 = i;
            Parameter parameter2 = parameter;
            if (annotationProcessor != null) {
                annotationProcessor.getFieldInfo(fieldInfo2, cls2, constructor2, i3, parameter2);
            }
            i2++;
            fieldInfo = fieldInfo2;
            cls = cls2;
            constructor = constructor2;
            i = i3;
            parameter = parameter2;
        }
    }

    public void getFieldInfo(FieldInfo fieldInfo, Class cls, Method method, int i, Parameter parameter) {
        int i2 = 0;
        while (i2 < this.modules.size()) {
            ObjectReaderAnnotationProcessor annotationProcessor = this.modules.get(i2).getAnnotationProcessor();
            FieldInfo fieldInfo2 = fieldInfo;
            Class cls2 = cls;
            Method method2 = method;
            int i3 = i;
            Parameter parameter2 = parameter;
            if (annotationProcessor != null) {
                annotationProcessor.getFieldInfo(fieldInfo2, cls2, method2, i3, parameter2);
            }
            i2++;
            fieldInfo = fieldInfo2;
            cls = cls2;
            method = method2;
            i = i3;
            parameter = parameter2;
        }
    }

    public ObjectReader getObjectReader(Type type) {
        return getObjectReader(type, false);
    }

    public Function<Consumer, ByteArrayValueConsumer> createValueConsumerCreator(Class cls, FieldReader[] fieldReaderArr) {
        return this.creator.createByteArrayValueConsumerCreator(cls, fieldReaderArr);
    }

    public Function<Consumer, CharArrayValueConsumer> createCharArrayValueConsumerCreator(Class cls, FieldReader[] fieldReaderArr) {
        return this.creator.createCharArrayValueConsumerCreator(cls, fieldReaderArr);
    }

    public ObjectReader getObjectReader(Type type, boolean z) {
        ObjectReader objectReader;
        if (type == null) {
            type = Object.class;
        }
        if (z) {
            objectReader = this.cacheFieldBased.get(type);
        } else {
            objectReader = this.cache.get(type);
        }
        if (objectReader == null && (type instanceof WildcardType)) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                objectReader = (z ? this.cacheFieldBased : this.cache).get(upperBounds[0]);
            }
        }
        return objectReader != null ? objectReader : getObjectReaderInternal(type, z);
    }

    private ObjectReader getObjectReaderInternal(Type type, boolean z) {
        ObjectReader objectReader;
        ObjectReader objectReader2;
        ObjectReader putIfAbsent;
        Iterator<ObjectReaderModule> it = this.modules.iterator();
        ObjectReader objectReader3 = null;
        while (it.hasNext()) {
            objectReader3 = it.next().getObjectReader(this, type);
            if (objectReader3 != null) {
                if (z) {
                    putIfAbsent = this.cacheFieldBased.putIfAbsent(type, objectReader3);
                } else {
                    putIfAbsent = this.cache.putIfAbsent(type, objectReader3);
                }
                return putIfAbsent != null ? putIfAbsent : objectReader3;
            }
        }
        boolean z2 = false;
        if (type instanceof TypeVariable) {
            Type[] bounds = ((TypeVariable) type).getBounds();
            if (bounds.length > 0) {
                Type type2 = bounds[0];
                if ((type2 instanceof Class) && (objectReader2 = getObjectReader(type2, z)) != null) {
                    ObjectReader previousObjectReader = getPreviousObjectReader(z, type, objectReader2);
                    return previousObjectReader != null ? previousObjectReader : objectReader2;
                }
            }
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (rawType instanceof Class) {
                Class cls = (Class) rawType;
                Class cls2 = cls;
                while (true) {
                    if (cls2 == Object.class) {
                        break;
                    }
                    if (cls2.getTypeParameters().length > 0) {
                        z2 = true;
                        break;
                    }
                    cls2 = cls2.getSuperclass();
                }
                if ((actualTypeArguments.length == 0 || !z2) && (objectReader = getObjectReader(cls, z)) != null) {
                    ObjectReader previousObjectReader2 = getPreviousObjectReader(z, type, objectReader);
                    return previousObjectReader2 != null ? previousObjectReader2 : objectReader;
                }
                if (actualTypeArguments.length == 1 && ArrayList.class.isAssignableFrom(cls)) {
                    return ObjectReaderImplList.of(type, cls, 0L);
                }
                if (actualTypeArguments.length == 2 && Map.class.isAssignableFrom(cls)) {
                    return ObjectReaderImplMap.of(type, cls, 0L);
                }
            }
        }
        Class<?> mapping = TypeUtils.getMapping(type);
        String name = mapping.getName();
        if (!z && "com.google.common.collect.ArrayListMultimap".equals(name)) {
            objectReader3 = ObjectReaderImplMap.of(null, mapping, 0L);
        }
        if (objectReader3 == null) {
            objectReader3 = getCreator().createObjectReader(mapping, type, z, this);
        }
        ObjectReader previousObjectReader3 = getPreviousObjectReader(z, type, objectReader3);
        return previousObjectReader3 != null ? previousObjectReader3 : objectReader3;
    }

    private ObjectReader getPreviousObjectReader(boolean z, Type type, ObjectReader objectReader) {
        if (z) {
            return this.cacheFieldBased.putIfAbsent(type, objectReader);
        }
        return this.cache.putIfAbsent(type, objectReader);
    }

    public JSONReader.AutoTypeBeforeHandler getAutoTypeBeforeHandler() {
        return this.autoTypeBeforeHandler;
    }

    public Map<String, Date> getAutoTypeList() {
        return this.autoTypeList;
    }

    public void setAutoTypeBeforeHandler(JSONReader.AutoTypeBeforeHandler autoTypeBeforeHandler) {
        this.autoTypeBeforeHandler = autoTypeBeforeHandler;
    }

    private static final class LRUAutoTypeCache extends LinkedHashMap<String, Date> {
        private final int maxSize;

        public LRUAutoTypeCache(int i) {
            super(16, 0.75f, false);
            this.maxSize = i;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, Date> entry) {
            return size() > this.maxSize;
        }
    }

    public void getFieldInfo(FieldInfo fieldInfo, Class cls, Method method) {
        for (int i = 0; i < this.modules.size(); i++) {
            ObjectReaderAnnotationProcessor annotationProcessor = this.modules.get(i).getAnnotationProcessor();
            if (annotationProcessor != null) {
                annotationProcessor.getFieldInfo(fieldInfo, cls, method);
            }
        }
        if (fieldInfo.fieldName == null && fieldInfo.alternateNames == null) {
            String name = method.getName();
            if (name.startsWith("set")) {
                String substring = name.substring(3);
                if (BeanUtils.getDeclaredField(cls, substring) != null) {
                    fieldInfo.alternateNames = new String[]{substring};
                }
            }
        }
    }

    public <T> Supplier<T> createObjectCreator(Class<T> cls, long j) {
        final ObjectReader objectReader;
        if ((j & JSONReader.Feature.FieldBased.mask) != 0) {
            objectReader = this.cacheFieldBased.get(cls);
        } else {
            objectReader = this.cache.get(cls);
        }
        if (objectReader != null) {
            return new Supplier() { // from class: com.alibaba.fastjson2.reader.ObjectReaderProvider$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    Object createInstance;
                    createInstance = ObjectReader.this.createInstance(0L);
                    return createInstance;
                }
            };
        }
        Constructor defaultConstructor = BeanUtils.getDefaultConstructor(cls, false);
        if (defaultConstructor == null) {
            throw new JSONException("default constructor not found : " + cls.getName());
        }
        return LambdaMiscCodec.createSupplier(defaultConstructor);
    }

    public FieldReader createFieldReader(Class cls, String str, long j) {
        ObjectReader objectReader;
        if ((j & JSONReader.Feature.FieldBased.mask) != 0) {
            objectReader = this.cacheFieldBased.get(cls);
        } else {
            objectReader = this.cache.get(cls);
        }
        if (objectReader != null) {
            return objectReader.getFieldReader(str);
        }
        final AtomicReference atomicReference = new AtomicReference();
        final long hashCode64LCase = Fnv.hashCode64LCase(str);
        BeanUtils.fields(cls, new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderProvider$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ObjectReaderProvider.lambda$createFieldReader$4(hashCode64LCase, atomicReference, (Field) obj);
            }
        });
        Field field = (Field) atomicReference.get();
        if (field != null) {
            return this.creator.createFieldReader(str, null, field.getType(), field);
        }
        final AtomicReference atomicReference2 = new AtomicReference();
        BeanUtils.setters(cls, new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderProvider$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ObjectReaderProvider.lambda$createFieldReader$5(hashCode64LCase, atomicReference2, (Method) obj);
            }
        });
        Method method = (Method) atomicReference2.get();
        if (method == null) {
            return null;
        }
        Class<?> cls2 = method.getParameterTypes()[0];
        return this.creator.createFieldReaderMethod(cls, str, null, cls2, cls2, method);
    }

    static /* synthetic */ void lambda$createFieldReader$4(long j, AtomicReference atomicReference, Field field) {
        if (j == Fnv.hashCode64LCase(field.getName())) {
            atomicReference.set(field);
        }
    }

    static /* synthetic */ void lambda$createFieldReader$5(long j, AtomicReference atomicReference, Method method) {
        if (j == Fnv.hashCode64LCase(BeanUtils.setterName(method.getName(), PropertyNamingStrategy.CamelCase.name()))) {
            atomicReference.set(method);
        }
    }

    public <T> ObjectReader<T> createObjectReader(String[] strArr, Type[] typeArr, Supplier<T> supplier, FieldConsumer<T> fieldConsumer) {
        return createObjectReader(strArr, typeArr, null, supplier, fieldConsumer);
    }

    public <T> ObjectReader<T> createObjectReader(String[] strArr, Type[] typeArr, long[] jArr, Supplier<T> supplier, FieldConsumer<T> fieldConsumer) {
        FieldReader[] fieldReaderArr = new FieldReader[strArr.length];
        int i = 0;
        while (i < strArr.length) {
            Type type = typeArr[i];
            fieldReaderArr[i] = this.creator.createFieldReader(strArr[i], type, TypeUtils.getClass(type), (jArr == null || i >= jArr.length) ? 0L : jArr[i], new FieldBiConsumer(i, fieldConsumer));
            i++;
        }
        return this.creator.createObjectReader(null, supplier, fieldReaderArr);
    }

    public boolean isDisableReferenceDetect() {
        return this.disableReferenceDetect;
    }

    public boolean isDisableAutoType() {
        return this.disableAutoType;
    }

    public boolean isDisableJSONB() {
        return this.disableJSONB;
    }

    public boolean isDisableArrayMapping() {
        return this.disableArrayMapping;
    }

    public void setDisableReferenceDetect(boolean z) {
        this.disableReferenceDetect = z;
    }

    public void setDisableArrayMapping(boolean z) {
        this.disableArrayMapping = z;
    }

    public void setDisableJSONB(boolean z) {
        this.disableJSONB = z;
    }

    public void setDisableAutoType(boolean z) {
        this.disableAutoType = z;
    }

    public boolean isDisableSmartMatch() {
        return this.disableSmartMatch;
    }

    public void setDisableSmartMatch(boolean z) {
        this.disableSmartMatch = z;
    }

    public PropertyNamingStrategy getNamingStrategy() {
        return this.namingStrategy;
    }

    public void setNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this.namingStrategy = propertyNamingStrategy;
    }
}
