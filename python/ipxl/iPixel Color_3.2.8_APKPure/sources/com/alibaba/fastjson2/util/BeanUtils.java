package com.alibaba.fastjson2.util;

import androidx.autofill.HintConstants;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.TypeReference;
import com.alibaba.fastjson2.annotation.JSONType;
import com.alibaba.fastjson2.codec.BeanInfo;
import com.alibaba.fastjson2.codec.FieldInfo;
import com.alibaba.fastjson2.modules.ObjectCodecProvider;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes2.dex */
public abstract class BeanUtils {
    private static volatile Class RECORD_CLASS = null;
    private static volatile Method RECORD_COMPONENT_GET_NAME = null;
    private static volatile Method RECORD_GET_RECORD_COMPONENTS = null;
    public static final String SUPER = "$super$";
    static final Type[] EMPTY_TYPE_ARRAY = new Type[0];
    static final ConcurrentMap<Class, Field[]> fieldCache = new ConcurrentHashMap();
    static final ConcurrentMap<Class, Map<String, Field>> fieldMapCache = new ConcurrentHashMap();
    static final ConcurrentMap<Class, Field[]> declaredFieldCache = new ConcurrentHashMap();
    static final ConcurrentMap<Class, Method[]> methodCache = new ConcurrentHashMap();
    static final ConcurrentMap<Class, Constructor[]> constructorCache = new ConcurrentHashMap();
    static final long[] IGNORE_CLASS_HASH_CODES = {-9214723784238596577L, -9030616758866828325L, -8335274122997354104L, -6963030519018899258L, -4863137578837233966L, -3653547262287832698L, -2819277587813726773L, -2669552864532011468L, -2458634727370886912L, -2291619803571459675L, -1811306045128064037L, -864440709753525476L, -779604756358333743L, 8731803887940231L, 1616814008855344660L, 2164749833121980361L, 2688642392827789427L, 3724195282986200606L, 3742915795806478647L, 3977020351318456359L, 4775491097662790952L, 4882459834864833642L, 6033839080488254886L, 7981148566008458638L, 8344106065386396833L, 9215465129261900012L};

    public static String[] getRecordFieldNames(Class<?> cls) {
        if (JDKUtils.JVM_VERSION < 14 && JDKUtils.ANDROID_SDK_INT < 33) {
            return new String[0];
        }
        try {
            if (RECORD_GET_RECORD_COMPONENTS == null) {
                RECORD_GET_RECORD_COMPONENTS = Class.class.getMethod("getRecordComponents", new Class[0]);
            }
            if (RECORD_COMPONENT_GET_NAME == null) {
                RECORD_COMPONENT_GET_NAME = Class.forName("java.lang.reflect.RecordComponent").getMethod("getName", new Class[0]);
            }
            Object[] objArr = (Object[]) RECORD_GET_RECORD_COMPONENTS.invoke(cls, new Object[0]);
            String[] strArr = new String[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                strArr[i] = (String) RECORD_COMPONENT_GET_NAME.invoke(objArr[i], new Object[0]);
            }
            return strArr;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to access Methods needed to support `java.lang.Record`: (%s) %s", e.getClass().getName(), e.getMessage()), e);
        }
    }

    public static void fields(Class cls, Consumer<Field> consumer) {
        if (TypeUtils.isProxy(cls)) {
            fields(cls.getSuperclass(), consumer);
            return;
        }
        ConcurrentMap<Class, Field[]> concurrentMap = fieldCache;
        Field[] fieldArr = concurrentMap.get(cls);
        if (fieldArr == null) {
            fieldArr = cls.getFields();
            concurrentMap.putIfAbsent(cls, fieldArr);
        }
        boolean isAssignableFrom = Enum.class.isAssignableFrom(cls);
        for (Field field : fieldArr) {
            if ((!Modifier.isStatic(field.getModifiers()) || isAssignableFrom) && !ignore(field.getType())) {
                consumer.accept(field);
            }
        }
    }

    public static Method getMethod(Class cls, String str) {
        ConcurrentMap<Class, Method[]> concurrentMap = methodCache;
        Method[] methodArr = concurrentMap.get(cls);
        if (methodArr == null) {
            methodArr = getMethods(cls);
            concurrentMap.putIfAbsent(cls, methodArr);
        }
        for (Method method : methodArr) {
            if (method.getName().equals(str)) {
                return method;
            }
        }
        return null;
    }

    public static Method fluentSetter(Class cls, String str, Class cls2) {
        ConcurrentMap<Class, Method[]> concurrentMap = methodCache;
        Method[] methodArr = concurrentMap.get(cls);
        if (methodArr == null) {
            methodArr = getMethods(cls);
            concurrentMap.putIfAbsent(cls, methodArr);
        }
        for (Method method : methodArr) {
            if (method.getName().equals(str) && method.getReturnType() == cls && method.getParameterCount() == 1 && method.getParameterTypes()[0] == cls2) {
                return method;
            }
        }
        return null;
    }

    public static Method getMethod(Class cls, Method method) {
        if (cls != null && cls != Object.class && cls != Serializable.class) {
            ConcurrentMap<Class, Method[]> concurrentMap = methodCache;
            Method[] methodArr = concurrentMap.get(cls);
            if (methodArr == null) {
                methodArr = getMethods(cls);
                concurrentMap.putIfAbsent(cls, methodArr);
            }
            for (Method method2 : methodArr) {
                if (method2.getName().equals(method.getName()) && method2.getParameterCount() == method.getParameterCount()) {
                    Class<?>[] parameterTypes = method2.getParameterTypes();
                    Class<?>[] parameterTypes2 = method.getParameterTypes();
                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (!parameterTypes[i].equals(parameterTypes2[i])) {
                            break;
                        }
                    }
                    return method2;
                }
            }
        }
        return null;
    }

    public static Field getDeclaredField(Class cls, String str) {
        ConcurrentMap<Class, Map<String, Field>> concurrentMap = fieldMapCache;
        Map<String, Field> map = concurrentMap.get(cls);
        if (map == null) {
            final HashMap hashMap = new HashMap();
            declaredFields(cls, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    hashMap.put(r2.getName(), (Field) obj);
                }
            });
            concurrentMap.putIfAbsent(cls, hashMap);
            map = concurrentMap.get(cls);
        }
        return map.get(str);
    }

    public static Method getSetter(Class cls, final String str) {
        final Method[] methodArr = new Method[1];
        setters(cls, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda13
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$getSetter$1(str, methodArr, (Method) obj);
            }
        });
        return methodArr[0];
    }

    static /* synthetic */ void lambda$getSetter$1(String str, Method[] methodArr, Method method) {
        if (str.equals(method.getName())) {
            methodArr[0] = method;
        }
    }

    public static void declaredFields(Class cls, Consumer<Field> consumer) {
        boolean z;
        if (cls == null || consumer == null || ignore(cls) || cls.getName().contains("$$Lambda") || JdbcSupport.isStruct(cls)) {
            return;
        }
        if (TypeUtils.isProxy(cls)) {
            declaredFields(cls.getSuperclass(), consumer);
            return;
        }
        Class superclass = cls.getSuperclass();
        if (superclass == null || superclass == Object.class) {
            z = false;
        } else {
            z = "com.google.protobuf.GeneratedMessageV3".equals(superclass.getName());
            if (!z) {
                declaredFields(superclass, consumer);
            }
        }
        ConcurrentMap<Class, Field[]> concurrentMap = declaredFieldCache;
        Field[] fieldArr = concurrentMap.get(cls);
        if (fieldArr == null) {
            try {
                fieldArr = cls.getDeclaredFields();
                concurrentMap.put(cls, fieldArr);
            } catch (Throwable unused) {
                fieldArr = new Field[0];
            }
            int length = fieldArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (Modifier.isStatic(fieldArr[i].getModifiers())) {
                    boolean isAssignableFrom = Enum.class.isAssignableFrom(cls);
                    ArrayList arrayList = new ArrayList(fieldArr.length);
                    for (Field field : fieldArr) {
                        if (isAssignableFrom || !Modifier.isStatic(field.getModifiers())) {
                            arrayList.add(field);
                        }
                    }
                    fieldArr = (Field[]) arrayList.toArray(new Field[arrayList.size()]);
                } else {
                    i++;
                }
            }
            fieldCache.putIfAbsent(cls, fieldArr);
        }
        for (Field field2 : fieldArr) {
            int modifiers = field2.getModifiers();
            Class<?> type = field2.getType();
            if ((modifiers & 8) == 0 && !ignore(type)) {
                if (z && "cardsmap_".equals(field2.getName()) && "com.google.protobuf.MapField".equals(type.getName())) {
                    return;
                }
                Class<?> declaringClass = field2.getDeclaringClass();
                if (declaringClass != AbstractMap.class && declaringClass != HashMap.class && declaringClass != LinkedHashMap.class && declaringClass != TreeMap.class && declaringClass != ConcurrentHashMap.class) {
                    consumer.accept(field2);
                }
            }
        }
    }

    public static void staticMethod(Class cls, Consumer<Method> consumer) {
        ConcurrentMap<Class, Method[]> concurrentMap = methodCache;
        Method[] methodArr = concurrentMap.get(cls);
        if (methodArr == null) {
            methodArr = getMethods(cls);
            concurrentMap.putIfAbsent(cls, methodArr);
        }
        for (Method method : methodArr) {
            if (Modifier.isStatic(method.getModifiers())) {
                consumer.accept(method);
            }
        }
    }

    public static Method buildMethod(Class cls, String str) {
        ConcurrentMap<Class, Method[]> concurrentMap = methodCache;
        Method[] methodArr = concurrentMap.get(cls);
        if (methodArr == null) {
            methodArr = getMethods(cls);
            concurrentMap.putIfAbsent(cls, methodArr);
        }
        for (Method method : methodArr) {
            if (!Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0 && method.getName().equals(str)) {
                return method;
            }
        }
        return null;
    }

    public static void constructor(Class cls, Consumer<Constructor> consumer) {
        ConcurrentMap<Class, Constructor[]> concurrentMap = constructorCache;
        Constructor<?>[] constructorArr = concurrentMap.get(cls);
        if (constructorArr == null) {
            constructorArr = cls.getDeclaredConstructors();
            concurrentMap.putIfAbsent(cls, constructorArr);
        }
        boolean isRecord = isRecord(cls);
        for (Constructor<?> constructor : constructorArr) {
            if (!isRecord || constructor.getParameterCount() != 0) {
                consumer.accept(constructor);
            }
        }
    }

    public static Constructor[] getConstructor(Class cls) {
        ConcurrentMap<Class, Constructor[]> concurrentMap = constructorCache;
        Constructor[] constructorArr = concurrentMap.get(cls);
        if (constructorArr != null) {
            return constructorArr;
        }
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        concurrentMap.putIfAbsent(cls, declaredConstructors);
        return declaredConstructors;
    }

    public static boolean hasPublicDefaultConstructor(Class cls) {
        Constructor defaultConstructor = getDefaultConstructor(cls, false);
        return defaultConstructor != null && Modifier.isPublic(defaultConstructor.getModifiers());
    }

    public static Constructor getDefaultConstructor(Class cls, boolean z) {
        Class<?> declaringClass;
        if ((cls == StackTraceElement.class && JDKUtils.JVM_VERSION >= 9) || isRecord(cls)) {
            return null;
        }
        ConcurrentMap<Class, Constructor[]> concurrentMap = constructorCache;
        Constructor[] constructorArr = concurrentMap.get(cls);
        if (constructorArr == null) {
            constructorArr = cls.getDeclaredConstructors();
            concurrentMap.putIfAbsent(cls, constructorArr);
        }
        for (Constructor<?> constructor : constructorArr) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        if (z && (declaringClass = cls.getDeclaringClass()) != null) {
            for (Constructor<?> constructor2 : constructorArr) {
                if (constructor2.getParameterCount() == 1 && declaringClass.equals(constructor2.getParameterTypes()[0])) {
                    return constructor2;
                }
            }
        }
        return null;
    }

    public static void setters(Class cls, Consumer<Method> consumer) {
        setters(cls, null, null, consumer);
    }

    public static void setters(Class cls, Class cls2, Consumer<Method> consumer) {
        setters(cls, null, cls2, consumer);
    }

    /* JADX WARN: Code restructure failed: missing block: B:120:0x01a1, code lost:
    
        if (((com.alibaba.fastjson2.annotation.JSONField) r12).unwrapped() == false) goto L121;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void setters(java.lang.Class r16, com.alibaba.fastjson2.codec.BeanInfo r17, java.lang.Class r18, java.util.function.Consumer<java.lang.reflect.Method> r19) {
        /*
            Method dump skipped, instructions count: 466
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.BeanUtils.setters(java.lang.Class, com.alibaba.fastjson2.codec.BeanInfo, java.lang.Class, java.util.function.Consumer):void");
    }

    static /* synthetic */ void lambda$setters$2(Annotation annotation, AtomicBoolean atomicBoolean, Method method) {
        try {
            if ("unwrapped".equals(method.getName()) && ((Boolean) method.invoke(annotation, new Object[0])).booleanValue()) {
                atomicBoolean.set(true);
            }
        } catch (Throwable unused) {
        }
    }

    public static void setters(Class cls, boolean z, Consumer<Method> consumer) {
        if (ignore(cls)) {
            return;
        }
        ConcurrentMap<Class, Method[]> concurrentMap = methodCache;
        Method[] methodArr = concurrentMap.get(cls);
        if (methodArr == null) {
            methodArr = getMethods(cls);
            concurrentMap.putIfAbsent(cls, methodArr);
        }
        for (Method method : methodArr) {
            int parameterCount = method.getParameterCount();
            if (parameterCount == 0) {
                String name = method.getName();
                if (!z || (name.length() > 3 && name.startsWith("get"))) {
                    Class<?> returnType = method.getReturnType();
                    if (returnType == AtomicInteger.class || returnType == AtomicLong.class || returnType == AtomicBoolean.class || returnType == AtomicIntegerArray.class || returnType == AtomicLongArray.class || Collection.class.isAssignableFrom(returnType)) {
                        consumer.accept(method);
                    }
                }
            }
            if (parameterCount == 1 && !Modifier.isStatic(method.getModifiers())) {
                String name2 = method.getName();
                int length = name2.length();
                if (!z || (length > 3 && name2.startsWith("set"))) {
                    consumer.accept(method);
                }
            }
        }
    }

    public static void annotationMethods(Class cls, Consumer<Method> consumer) {
        ConcurrentMap<Class, Method[]> concurrentMap = methodCache;
        Method[] methodArr = concurrentMap.get(cls);
        if (methodArr == null) {
            methodArr = getMethods(cls);
            concurrentMap.putIfAbsent(cls, methodArr);
        }
        for (Method method : methodArr) {
            if (method.getParameterCount() == 0 && method.getDeclaringClass() != Object.class) {
                String name = method.getName();
                name.hashCode();
                switch (name) {
                    case "toString":
                    case "hashCode":
                    case "annotationType":
                        break;
                    default:
                        consumer.accept(method);
                        break;
                }
            }
        }
    }

    public static boolean isWriteEnumAsJavaBean(Class cls) {
        for (final Annotation annotation : getAnnotations(cls)) {
            JSONType jSONType = (JSONType) findAnnotation(annotation, JSONType.class);
            if (jSONType != null) {
                return jSONType.writeEnumAsJavaBean();
            }
            Class<? extends Annotation> annotationType = annotation.annotationType();
            String name = annotationType.getName();
            final BeanInfo beanInfo = new BeanInfo(JSONFactory.getDefaultObjectWriterProvider());
            name.hashCode();
            if (name.equals("com.fasterxml.jackson.annotation.JsonFormat")) {
                if (JSONFactory.isUseJacksonAnnotation()) {
                    processJacksonJsonFormat(beanInfo, annotation);
                }
            } else if (name.equals("com.alibaba.fastjson.annotation.JSONType")) {
                annotationMethods(annotationType, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        BeanUtils.processJSONType1x(BeanInfo.this, annotation, (Method) obj);
                    }
                });
            }
            if (beanInfo.writeEnumAsJavaBean) {
                return true;
            }
        }
        return false;
    }

    public static String[] getEnumAnnotationNames(Class cls) {
        final Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        int length = enumArr.length;
        final String[] strArr = new String[length];
        fields(cls, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$getEnumAnnotationNames$6(enumArr, strArr, (Field) obj);
            }
        });
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (strArr[i2] == null) {
                i++;
            }
        }
        if (i == length) {
            return null;
        }
        return strArr;
    }

    static /* synthetic */ void lambda$getEnumAnnotationNames$6(Enum[] enumArr, final String[] strArr, Field field) {
        String name = field.getName();
        for (final int i = 0; i < enumArr.length; i++) {
            final String name2 = enumArr[i].name();
            if (name.equals(name2)) {
                for (final Annotation annotation : field.getAnnotations()) {
                    Class<? extends Annotation> annotationType = annotation.annotationType();
                    String name3 = annotationType.getName();
                    if ("com.alibaba.fastjson2.annotation.JSONField".equals(name3) || "com.alibaba.fastjson.annotation.JSONField".equals(name3)) {
                        annotationMethods(annotationType, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda16
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                BeanUtils.lambda$getEnumAnnotationNames$4(annotation, name2, strArr, i, (Method) obj);
                            }
                        });
                    } else if ("com.fasterxml.jackson.annotation.JsonProperty".equals(name3)) {
                        annotationMethods(annotationType, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda17
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                BeanUtils.lambda$getEnumAnnotationNames$5(annotation, name2, strArr, i, (Method) obj);
                            }
                        });
                    }
                }
                return;
            }
        }
    }

    static /* synthetic */ void lambda$getEnumAnnotationNames$4(Annotation annotation, String str, String[] strArr, int i, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            if (HintConstants.AUTOFILL_HINT_NAME.equals(name)) {
                String str2 = (String) invoke;
                if (str2.length() == 0 || str2.equals(str)) {
                    return;
                }
                strArr[i] = str2;
            }
        } catch (Exception unused) {
        }
    }

    static /* synthetic */ void lambda$getEnumAnnotationNames$5(Annotation annotation, String str, String[] strArr, int i, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            if ("value".equals(name)) {
                String str2 = (String) invoke;
                if (str2.length() == 0 || str2.equals(str)) {
                    return;
                }
                strArr[i] = str2;
            }
        } catch (Exception unused) {
        }
    }

    public static Member getEnumValueField(Class cls, ObjectCodecProvider objectCodecProvider) {
        boolean z;
        Class<?> declaringClass;
        Class mixIn;
        Member member;
        Field declaredField;
        if (cls == null) {
            return null;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        ConcurrentMap<Class, Method[]> concurrentMap = methodCache;
        Method[] methodArr = concurrentMap.get(cls);
        Method[] methodArr2 = methodArr;
        if (methodArr == null) {
            Method[] methods = cls.getMethods();
            concurrentMap.putIfAbsent(cls, methods);
            methodArr2 = methods;
        }
        Member member2 = null;
        for (final Method method : methodArr2) {
            if (method.getReturnType() != Void.class && method.getParameterCount() == 0 && (declaringClass = method.getDeclaringClass()) != Enum.class && declaringClass != Object.class) {
                final String name = method.getName();
                if ("values".equals(name)) {
                    continue;
                } else {
                    if (isJSONField(method)) {
                        return method;
                    }
                    if (name.startsWith("get") && (declaredField = getDeclaredField(cls, getterName(name, (String) null))) != null) {
                        member = method;
                        if (isJSONField(declaredField)) {
                            if (member2 != null) {
                                if (!member2.getName().equals(method.getName())) {
                                    return null;
                                }
                                if (member2 instanceof Method) {
                                    boolean isAssignableFrom = ((Method) member2).getReturnType().isAssignableFrom(method.getReturnType());
                                    member = method;
                                    if (!isAssignableFrom) {
                                    }
                                }
                            }
                            member2 = member;
                        }
                    }
                    final AtomicReference atomicReference = new AtomicReference();
                    for (Class<?> cls2 : interfaces) {
                        getters(cls2, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda10
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                BeanUtils.lambda$getEnumValueField$7(name, atomicReference, method, (Method) obj);
                            }
                        });
                        if (objectCodecProvider != null) {
                            mixIn = objectCodecProvider.getMixIn(cls2);
                        } else {
                            mixIn = JSONFactory.getDefaultObjectWriterProvider().getMixIn(cls2);
                        }
                        if (mixIn != null) {
                            getters(mixIn, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda12
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj) {
                                    BeanUtils.lambda$getEnumValueField$8(name, atomicReference, method, (Method) obj);
                                }
                            });
                        }
                    }
                    Member member3 = (Member) atomicReference.get();
                    member = member3;
                    if (member3 == null) {
                        continue;
                    } else {
                        if (member2 != null) {
                            if (!member2.getName().equals(member3.getName())) {
                                return null;
                            }
                        }
                        member2 = member;
                    }
                }
            }
        }
        if (member2 != null) {
            return member2;
        }
        ConcurrentMap<Class, Field[]> concurrentMap2 = fieldCache;
        Field[] fieldArr = concurrentMap2.get(cls);
        if (fieldArr == null) {
            fieldArr = cls.getFields();
            concurrentMap2.putIfAbsent(cls, fieldArr);
        }
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        for (Field field : fieldArr) {
            if (enumArr != null) {
                String name2 = field.getName();
                for (Enum r0 : enumArr) {
                    if (name2.equals(r0.name())) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (isJSONField(field) && !z) {
                return field;
            }
        }
        return null;
    }

    static /* synthetic */ void lambda$getEnumValueField$7(String str, AtomicReference atomicReference, Method method, Method method2) {
        if (method2.getName().equals(str) && isJSONField(method2)) {
            atomicReference.set(method);
        }
    }

    static /* synthetic */ void lambda$getEnumValueField$8(String str, AtomicReference atomicReference, Method method, Method method2) {
        if (method2.getName().equals(str) && isJSONField(method2)) {
            atomicReference.set(method);
        }
    }

    public static void getters(Class cls, Consumer<Method> consumer) {
        getters(cls, null, consumer);
    }

    public static void getters(Class cls, Class cls2, Consumer<Method> consumer) {
        getters(cls, cls2, false, consumer);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0115, code lost:
    
        if (r10 != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0137, code lost:
    
        if ("com.google.protobuf.ByteString".equals(r15.getName()) != false) goto L35;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:148:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0306 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void getters(java.lang.Class r29, java.lang.Class r30, boolean r31, java.util.function.Consumer<java.lang.reflect.Method> r32) {
        /*
            Method dump skipped, instructions count: 868
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.BeanUtils.getters(java.lang.Class, java.lang.Class, boolean, java.util.function.Consumer):void");
    }

    private static Method[] getMethods(Class cls) {
        try {
            return cls.getMethods();
        } catch (NoClassDefFoundError unused) {
            return new Method[0];
        }
    }

    private static boolean isJSONField(AnnotatedElement annotatedElement) {
        for (Annotation annotation : annotatedElement.getAnnotations()) {
            String name = annotation.annotationType().getName();
            name.hashCode();
            switch (name) {
                case "com.fasterxml.jackson.annotation.JsonValue":
                case "com.fasterxml.jackson.annotation.JsonProperty":
                case "com.fasterxml.jackson.annotation.JsonRawValue":
                case "com.fasterxml.jackson.annotation.JsonUnwrapped":
                    if (JSONFactory.isUseJacksonAnnotation()) {
                        return true;
                    }
                    break;
                case "com.alibaba.fastjson.annotation.JSONField":
                case "com.alibaba.fastjson2.annotation.JSONField":
                    return true;
            }
        }
        return false;
    }

    static boolean ignore(Class cls) {
        return cls == null || Arrays.binarySearch(IGNORE_CLASS_HASH_CODES, Fnv.hashCode64(cls.getName())) >= 0;
    }

    public static boolean isRecord(Class cls) {
        Class superclass = cls.getSuperclass();
        if (superclass == null) {
            return false;
        }
        if (RECORD_CLASS != null) {
            return superclass == RECORD_CLASS;
        }
        if (!"java.lang.Record".equals(superclass.getName())) {
            return false;
        }
        RECORD_CLASS = superclass;
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x00de, code lost:
    
        if (r10.equals("LowerCaseWithUnderScores") == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String setterName(java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 612
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.BeanUtils.setterName(java.lang.String, java.lang.String):java.lang.String");
    }

    public static String setterName(String str, int i) {
        char c;
        int length = str.length();
        int i2 = length - i;
        char[] cArr = new char[i2];
        str.getChars(i, length, cArr, 0);
        char c2 = cArr[0];
        boolean z = i2 > 1 && (c = cArr[1]) >= 'A' && c <= 'Z';
        if (c2 >= 'A' && c2 <= 'Z' && !z) {
            cArr[0] = (char) (c2 + ' ');
        }
        return new String(cArr);
    }

    public static String getterName(Method method, String str) {
        return getterName(method, false, str);
    }

    public static String getterName(Method method, boolean z, String str) {
        int indexOf;
        Class<?> returnType;
        String name = method.getName();
        if (name.startsWith("is") && (((returnType = method.getReturnType()) != Boolean.class && returnType != Boolean.TYPE) || z)) {
            return name;
        }
        String str2 = getterName(name, str);
        if (z && (indexOf = str2.indexOf(45)) != -1) {
            str2 = str2.substring(0, indexOf);
        }
        if (str2.length() > 2 && str2.charAt(0) >= 'A' && str2.charAt(0) <= 'Z' && str2.charAt(1) >= 'A' && str2.charAt(1) <= 'Z') {
            char[] charArray = str2.toCharArray();
            charArray[0] = (char) (charArray[0] + ' ');
            Field declaredField = getDeclaredField(method.getDeclaringClass(), new String(charArray));
            if (declaredField != null && Modifier.isPublic(declaredField.getModifiers())) {
                return declaredField.getName();
            }
        }
        return str2;
    }

    public static Field getField(Class cls, final String str) {
        final Field[] fieldArr = new Field[1];
        declaredFields(cls, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda20
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$getField$9(str, fieldArr, (Field) obj);
            }
        });
        return fieldArr[0];
    }

    static /* synthetic */ void lambda$getField$9(String str, Field[] fieldArr, Field field) {
        if (field.getName().equals(str)) {
            fieldArr[0] = field;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0065 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.reflect.Field getField(java.lang.Class r14, final java.lang.reflect.Method r15) {
        /*
            Method dump skipped, instructions count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.BeanUtils.getField(java.lang.Class, java.lang.reflect.Method):java.lang.reflect.Field");
    }

    static /* synthetic */ void lambda$getField$10(Method method, int i, int i2, Class cls, char c, String str, Field[] fieldArr, Field field) {
        if (field.getDeclaringClass() != method.getDeclaringClass()) {
            return;
        }
        String name = field.getName();
        int length = name.length();
        if (length == i - i2 && (field.getType() == cls || cls.isAssignableFrom(field.getType()))) {
            if (c >= 'A' && c <= 'Z' && c + ' ' == name.charAt(0) && name.regionMatches(1, str, i2 + 1, length - 1)) {
                fieldArr[0] = field;
                return;
            } else {
                if (name.regionMatches(0, str, i2, length)) {
                    fieldArr[1] = field;
                    return;
                }
                return;
            }
        }
        if (Boolean.TYPE == field.getType() && str.equals(name)) {
            fieldArr[0] = field;
        }
    }

    public static String getterName(String str, String str2) {
        char c;
        if (str2 == null) {
            str2 = "CamelCase";
        }
        int length = str.length();
        boolean startsWith = str.startsWith("is");
        boolean startsWith2 = str.startsWith("get");
        int i = 3;
        int i2 = startsWith ? 2 : startsWith2 ? 3 : 0;
        if (length == i2) {
            return str;
        }
        str2.hashCode();
        switch (str2) {
            case "UpperCase":
                return str.substring(i2).toUpperCase();
            case "UpperCaseWithDots":
                return dots(str, i2, true);
            case "NeverUseThisValueExceptDefaultValue":
            case "CamelCase":
                int i3 = length - i2;
                char[] cArr = new char[i3];
                str.getChars(i2, length, cArr, 0);
                char c2 = cArr[0];
                boolean z = i3 > 1 && (c = cArr[1]) >= 'A' && c <= 'Z';
                if (c2 >= 'A' && c2 <= 'Z' && !z) {
                    cArr[0] = (char) (c2 + ' ');
                }
                return new String(cArr);
            case "LowerCaseWithUnderScores":
                return underScores(str, i2, false);
            case "UpperCamelCaseWithUnderScores":
                return upperCamelWith(str, i2, '_');
            case "UpperCaseWithDashes":
                return dashes(str, i2, true);
            case "UpperCamelCaseWithDashes":
                return upperCamelWith(str, i2, '-');
            case "UpperCamelCaseWithDots":
                return upperCamelWith(str, i2, '.');
            case "LowerCaseWithDots":
                return dots(str, i2, false);
            case "PascalCase":
                return pascal(str, length, i2);
            case "UpperCamelCaseWithSpaces":
                return upperCamelWith(str, i2, ' ');
            case "KebabCase":
                StringBuilder sb = new StringBuilder();
                if (startsWith) {
                    i = 2;
                } else if (!startsWith2) {
                    i = 0;
                }
                for (int i4 = i; i4 < str.length(); i4++) {
                    char charAt = str.charAt(i4);
                    if (charAt >= 'A' && charAt <= 'Z') {
                        charAt = (char) (charAt + ' ');
                        if (i4 > i) {
                            sb.append('-');
                        }
                    }
                    sb.append(charAt);
                }
                return sb.toString();
            case "LowerCase":
                return str.substring(i2).toLowerCase();
            case "LowerCaseWithDashes":
                return dashes(str, i2, false);
            case "CamelCase1x":
                char[] cArr2 = new char[length - i2];
                str.getChars(i2, length, cArr2, 0);
                char c3 = cArr2[0];
                if (c3 >= 'A' && c3 <= 'Z') {
                    cArr2[0] = (char) (c3 + ' ');
                }
                return new String(cArr2);
            case "UpperCaseWithUnderScores":
                return underScores(str, i2, true);
            case "SnakeCase":
                return snakeCase(str, i2);
            default:
                throw new JSONException("TODO : " + str2);
        }
    }

    private static String pascal(String str, int i, int i2) {
        char c;
        char c2;
        int i3 = i - i2;
        char[] cArr = new char[i3];
        str.getChars(i2, i, cArr, 0);
        char c3 = cArr[0];
        if (c3 >= 'a' && c3 <= 'z' && i3 > 1) {
            cArr[0] = (char) (c3 - ' ');
        } else if (c3 == '_' && i3 > 2 && (c = cArr[1]) >= 'a' && c <= 'z' && (c2 = cArr[2]) >= 'a' && c2 <= 'z') {
            cArr[1] = (char) (c - ' ');
        }
        return new String(cArr);
    }

    public static String fieldName(String str, String str2) {
        int i;
        char charAt;
        char charAt2;
        if (str2 == null) {
            str2 = "CamelCase";
        }
        if (str == null || str.isEmpty()) {
            return str;
        }
        str2.hashCode();
        switch (str2) {
            case "UpperCase":
                return str.toUpperCase();
            case "UpperCaseWithDots":
                return dots(str, 0, true);
            case "NeverUseThisValueExceptDefaultValue":
            case "NoChange":
            case "CamelCase":
                char charAt3 = str.charAt(0);
                char charAt4 = str.length() > 1 ? str.charAt(1) : (char) 0;
                if (charAt3 < 'A' || charAt3 > 'Z' || str.length() <= 1) {
                    return str;
                }
                if (charAt4 >= 'A' && charAt4 <= 'Z') {
                    return str;
                }
                char[] charArray = str.toCharArray();
                charArray[0] = (char) (charAt3 + ' ');
                return new String(charArray);
            case "LowerCaseWithUnderScores":
                return underScores(str, 0, false);
            case "UpperCamelCaseWithUnderScores":
                return upperCamelWith(str, 0, '_');
            case "UpperCaseWithDashes":
                return dashes(str, 0, true);
            case "UpperCamelCaseWithDashes":
                return upperCamelWith(str, 0, '-');
            case "UpperCamelCaseWithDots":
                return upperCamelWith(str, 0, '.');
            case "LowerCaseWithDots":
                return dots(str, 0, false);
            case "PascalCase":
                char charAt5 = str.charAt(0);
                if (charAt5 >= 'a' && charAt5 <= 'z' && str.length() > 1 && (charAt2 = str.charAt(1)) >= 'a' && charAt2 <= 'z') {
                    char[] charArray2 = str.toCharArray();
                    charArray2[0] = (char) (charAt5 - ' ');
                    return new String(charArray2);
                }
                if (charAt5 != '_' || str.length() <= 1 || (charAt = str.charAt(1)) < 'a' || charAt > 'z') {
                    return str;
                }
                char[] charArray3 = str.toCharArray();
                charArray3[1] = (char) (charAt - ' ');
                return new String(charArray3);
            case "UpperCamelCaseWithSpaces":
                return upperCamelWith(str, 0, ' ');
            case "KebabCase":
                StringBuilder sb = new StringBuilder();
                for (i = 0; i < str.length(); i++) {
                    char charAt6 = str.charAt(i);
                    if (charAt6 >= 'A' && charAt6 <= 'Z') {
                        charAt6 = (char) (charAt6 + ' ');
                        if (i > 0) {
                            sb.append('-');
                        }
                    }
                    sb.append(charAt6);
                }
                return sb.toString();
            case "LowerCase":
                return str.toLowerCase();
            case "LowerCaseWithDashes":
                return dashes(str, 0, false);
            case "CamelCase1x":
                char charAt7 = str.charAt(0);
                if (charAt7 < 'A' || charAt7 > 'Z' || str.length() <= 1) {
                    return str;
                }
                char[] charArray4 = str.toCharArray();
                charArray4[0] = (char) (charAt7 + ' ');
                return new String(charArray4);
            case "UpperCaseWithUnderScores":
                return underScores(str, 0, true);
            case "SnakeCase":
                return snakeCase(str, 0);
            default:
                throw new JSONException("TODO : " + str2);
        }
    }

    static String snakeCase(String str, int i) {
        int length = str.length();
        char[] andSet = TypeUtils.CHARS_UPDATER.getAndSet(TypeUtils.CACHE, null);
        if (andSet == null) {
            andSet = new char[128];
        }
        int i2 = i;
        int i3 = 0;
        while (i2 < length) {
            try {
                char charAt = str.charAt(i2);
                if (charAt >= 'A' && charAt <= 'Z') {
                    charAt = (char) (charAt + ' ');
                    if (i2 > i) {
                        andSet[i3] = '_';
                        i3++;
                    }
                }
                andSet[i3] = charAt;
                i2++;
                i3++;
            } finally {
                TypeUtils.CHARS_UPDATER.set(TypeUtils.CACHE, andSet);
            }
        }
        return new String(andSet, 0, i3);
    }

    static String upperCamelWith(String str, int i, char c) {
        int i2;
        char charAt;
        char charAt2;
        int i3;
        int i4;
        char charAt3;
        int i5;
        char charAt4;
        int i6;
        char charAt5;
        int length = str.length();
        char[] andSet = TypeUtils.CHARS_UPDATER.getAndSet(TypeUtils.CACHE, null);
        if (andSet == null) {
            andSet = new char[128];
        }
        int i7 = i;
        int i8 = 0;
        while (i7 < length) {
            try {
                char charAt6 = str.charAt(i7);
                if (i7 == i) {
                    if (charAt6 >= 'a' && charAt6 <= 'z' && (i6 = i7 + 1) < length && (charAt5 = str.charAt(i6)) >= 'a' && charAt5 <= 'z') {
                        charAt6 = (char) (charAt6 - ' ');
                    } else if (charAt6 == '_' && (i5 = i7 + 1) < length && (charAt4 = str.charAt(i5)) >= 'a' && charAt4 <= 'z') {
                        andSet[i8] = charAt6;
                        charAt6 = (char) (charAt4 - ' ');
                        i8++;
                        i7 = i5;
                    }
                } else if (charAt6 < 'A' || charAt6 > 'Z' || (i4 = i7 + 1) >= length || ((charAt3 = str.charAt(i4)) >= 'A' && charAt3 <= 'Z')) {
                    if (charAt6 >= 'A' && charAt6 <= 'Z' && i7 > i && (i2 = i7 + 1) < length && (charAt = str.charAt(i2)) >= 'A' && charAt <= 'Z' && (charAt2 = str.charAt(i7 - 1)) >= 'a' && charAt2 <= 'z') {
                        i3 = i8 + 1;
                        andSet[i8] = c;
                        i8 = i3;
                    }
                } else if (i7 > i) {
                    i3 = i8 + 1;
                    andSet[i8] = c;
                    i8 = i3;
                }
                andSet[i8] = charAt6;
                i7++;
                i8++;
            } finally {
                TypeUtils.CHARS_UPDATER.set(TypeUtils.CACHE, andSet);
            }
        }
        return new String(andSet, 0, i8);
    }

    static String underScores(String str, int i, boolean z) {
        int i2;
        int length = str.length();
        char[] andSet = TypeUtils.CHARS_UPDATER.getAndSet(TypeUtils.CACHE, null);
        if (andSet == null) {
            andSet = new char[128];
        }
        int i3 = i;
        int i4 = 0;
        while (i3 < length) {
            try {
                char charAt = str.charAt(i3);
                if (z) {
                    if (charAt >= 'A' && charAt <= 'Z') {
                        if (i3 > i) {
                            andSet[i4] = '_';
                            i4++;
                        }
                    }
                    if (charAt >= 'a' && charAt <= 'z') {
                        i2 = charAt - ' ';
                        charAt = (char) i2;
                    }
                } else if (charAt >= 'A' && charAt <= 'Z') {
                    if (i3 > i) {
                        andSet[i4] = '_';
                        i4++;
                    }
                    i2 = charAt + ' ';
                    charAt = (char) i2;
                }
                andSet[i4] = charAt;
                i3++;
                i4++;
            } finally {
                TypeUtils.CHARS_UPDATER.set(TypeUtils.CACHE, andSet);
            }
        }
        return new String(andSet, 0, i4);
    }

    static String dashes(String str, int i, boolean z) {
        int i2;
        int length = str.length();
        char[] andSet = TypeUtils.CHARS_UPDATER.getAndSet(TypeUtils.CACHE, null);
        if (andSet == null) {
            andSet = new char[128];
        }
        int i3 = i;
        int i4 = 0;
        while (i3 < length) {
            try {
                char charAt = str.charAt(i3);
                if (z) {
                    if (charAt < 'A' || charAt > 'Z') {
                        if (charAt >= 'a' && charAt <= 'z') {
                            i2 = charAt - ' ';
                            charAt = (char) i2;
                        }
                    } else if (i3 > i) {
                        andSet[i4] = '-';
                        i4++;
                    }
                } else if (charAt >= 'A' && charAt <= 'Z') {
                    if (i3 > i) {
                        andSet[i4] = '-';
                        i4++;
                    }
                    i2 = charAt + ' ';
                    charAt = (char) i2;
                }
                andSet[i4] = charAt;
                i3++;
                i4++;
            } finally {
                TypeUtils.CHARS_UPDATER.set(TypeUtils.CACHE, andSet);
            }
        }
        return new String(andSet, 0, i4);
    }

    static String dots(String str, int i, boolean z) {
        int i2;
        int length = str.length();
        char[] andSet = TypeUtils.CHARS_UPDATER.getAndSet(TypeUtils.CACHE, null);
        if (andSet == null) {
            andSet = new char[128];
        }
        int i3 = i;
        int i4 = 0;
        while (i3 < length) {
            try {
                char charAt = str.charAt(i3);
                if (z) {
                    if (charAt < 'A' || charAt > 'Z') {
                        if (charAt >= 'a' && charAt <= 'z') {
                            i2 = charAt - ' ';
                            charAt = (char) i2;
                        }
                    } else if (i3 > i) {
                        andSet[i4] = '.';
                        i4++;
                    }
                } else if (charAt >= 'A' && charAt <= 'Z') {
                    if (i3 > i) {
                        andSet[i4] = '.';
                        i4++;
                    }
                    i2 = charAt + ' ';
                    charAt = (char) i2;
                }
                andSet[i4] = charAt;
                i3++;
                i4++;
            } finally {
                TypeUtils.CHARS_UPDATER.set(TypeUtils.CACHE, andSet);
            }
        }
        return new String(andSet, 0, i4);
    }

    public static Type getFieldType(TypeReference typeReference, Class<?> cls, Member member, Type type) {
        Class<?> declaringClass = member == null ? null : member.getDeclaringClass();
        while (cls != Object.class) {
            Type type2 = typeReference == null ? null : typeReference.getType();
            if (declaringClass == cls) {
                return resolve(type2, declaringClass, type);
            }
            Type genericSuperclass = cls.getGenericSuperclass();
            if (genericSuperclass == null) {
                break;
            }
            typeReference = TypeReference.get(resolve(type2, cls, genericSuperclass));
            cls = typeReference.getRawType();
        }
        return null;
    }

    public static Type getParamType(TypeReference typeReference, Class<?> cls, Class cls2, Parameter parameter, Type type) {
        while (cls != Object.class) {
            if (cls2 == cls) {
                return resolve(typeReference.getType(), cls2, type);
            }
            typeReference = TypeReference.get(resolve(typeReference.getType(), cls, cls.getGenericSuperclass()));
            cls = typeReference.getRawType();
        }
        return null;
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type, Type type2, Type... typeArr) {
        return new ParameterizedTypeImpl(type, type2, typeArr);
    }

    public static GenericArrayType arrayOf(Type type) {
        return new GenericArrayTypeImpl(type);
    }

    public static WildcardType subtypeOf(Type type) {
        Type[] typeArr;
        if (type instanceof WildcardType) {
            typeArr = ((WildcardType) type).getUpperBounds();
        } else {
            typeArr = new Type[]{type};
        }
        return new WildcardTypeImpl(typeArr, EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type type) {
        Type[] typeArr;
        Type[] typeArr2 = {Object.class};
        if (type instanceof WildcardType) {
            typeArr = ((WildcardType) type).getLowerBounds();
        } else {
            typeArr = new Type[]{type};
        }
        return new WildcardTypeImpl(typeArr2, typeArr);
    }

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new GenericArrayTypeImpl(canonicalize(cls.getComponentType())) : cls;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
        }
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            checkArgument(rawType instanceof Class);
            return (Class) rawType;
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    static boolean equal(Object obj, Object obj2) {
        return Objects.equals(obj, obj2);
    }

    public static boolean equals(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            return equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return equals(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        }
        if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
        }
        if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) type;
        TypeVariable typeVariable2 = (TypeVariable) type2;
        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
    }

    static int hashCodeOrZero(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                Class<?> cls3 = interfaces[i];
                if (cls3 == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(cls3)) {
                    return getGenericSupertype(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (cls != null && !cls.isInterface()) {
            while (cls != Object.class) {
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return getGenericSupertype(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static Type resolve(Type type, Class<?> cls, Type type2) {
        return resolve(type, cls, type2, new HashMap());
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x00df, code lost:
    
        if (r0 == null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00e1, code lost:
    
        r12.put(r0, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00e4, code lost:
    
        return r11;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v11, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.lang.reflect.ParameterizedType] */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9, types: [java.lang.Object, java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.util.Map, java.util.Map<java.lang.reflect.TypeVariable<?>, java.lang.reflect.Type>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.reflect.Type resolve(java.lang.reflect.Type r9, java.lang.Class<?> r10, java.lang.reflect.Type r11, java.util.Map<java.lang.reflect.TypeVariable<?>, java.lang.reflect.Type> r12) {
        /*
            r0 = 0
        L1:
            boolean r1 = r11 instanceof java.lang.reflect.TypeVariable
            if (r1 == 0) goto L26
            r1 = r11
            java.lang.reflect.TypeVariable r1 = (java.lang.reflect.TypeVariable) r1
            java.lang.Object r2 = r12.get(r1)
            java.lang.reflect.Type r2 = (java.lang.reflect.Type) r2
            if (r2 == 0) goto L16
            java.lang.Class r9 = java.lang.Void.TYPE
            if (r2 != r9) goto L15
            return r11
        L15:
            return r2
        L16:
            java.lang.Class r11 = java.lang.Void.TYPE
            r12.put(r1, r11)
            if (r0 != 0) goto L1e
            r0 = r1
        L1e:
            java.lang.reflect.Type r11 = resolveTypeVariable(r9, r10, r1)
            if (r11 != r1) goto L1
            goto Ldf
        L26:
            boolean r1 = r11 instanceof java.lang.Class
            if (r1 == 0) goto L4b
            r1 = r11
            java.lang.Class r1 = (java.lang.Class) r1
            boolean r2 = r1.isArray()
            if (r2 == 0) goto L4b
            java.lang.Class r11 = r1.getComponentType()
            java.lang.reflect.Type r9 = resolve(r9, r10, r11, r12)
            boolean r10 = equal(r11, r9)
            if (r10 == 0) goto L44
            r11 = r1
            goto Ldf
        L44:
            java.lang.reflect.GenericArrayType r9 = arrayOf(r9)
        L48:
            r11 = r9
            goto Ldf
        L4b:
            boolean r1 = r11 instanceof java.lang.reflect.GenericArrayType
            if (r1 == 0) goto L66
            java.lang.reflect.GenericArrayType r11 = (java.lang.reflect.GenericArrayType) r11
            java.lang.reflect.Type r1 = r11.getGenericComponentType()
            java.lang.reflect.Type r9 = resolve(r9, r10, r1, r12)
            boolean r10 = equal(r1, r9)
            if (r10 == 0) goto L61
            goto Ldf
        L61:
            java.lang.reflect.GenericArrayType r9 = arrayOf(r9)
            goto L48
        L66:
            boolean r1 = r11 instanceof java.lang.reflect.ParameterizedType
            r2 = 0
            r3 = 1
            if (r1 == 0) goto Lad
            java.lang.reflect.ParameterizedType r11 = (java.lang.reflect.ParameterizedType) r11
            java.lang.reflect.Type r1 = r11.getOwnerType()
            java.lang.reflect.Type r4 = resolve(r9, r10, r1, r12)
            boolean r1 = equal(r4, r1)
            r1 = r1 ^ r3
            java.lang.reflect.Type[] r5 = r11.getActualTypeArguments()
            int r6 = r5.length
        L80:
            if (r2 >= r6) goto La2
            r7 = r5[r2]
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            if (r7 != r8) goto L89
            goto L9f
        L89:
            java.lang.reflect.Type r8 = resolve(r9, r10, r7, r12)
            boolean r7 = equal(r8, r7)
            if (r7 != 0) goto L9f
            if (r1 != 0) goto L9d
            java.lang.Object r1 = r5.clone()
            r5 = r1
            java.lang.reflect.Type[] r5 = (java.lang.reflect.Type[]) r5
            r1 = r3
        L9d:
            r5[r2] = r8
        L9f:
            int r2 = r2 + 1
            goto L80
        La2:
            if (r1 == 0) goto Ldf
            java.lang.reflect.Type r9 = r11.getRawType()
            java.lang.reflect.ParameterizedType r9 = newParameterizedTypeWithOwner(r4, r9, r5)
            goto L48
        Lad:
            boolean r1 = r11 instanceof java.lang.reflect.WildcardType
            if (r1 == 0) goto Ldf
            r1 = r11
            java.lang.reflect.WildcardType r1 = (java.lang.reflect.WildcardType) r1
            java.lang.reflect.Type[] r4 = r1.getLowerBounds()
            java.lang.reflect.Type[] r1 = r1.getUpperBounds()
            int r5 = r4.length
            if (r5 != r3) goto Lce
            r1 = r4[r2]
            java.lang.reflect.Type r9 = resolve(r9, r10, r1, r12)
            r10 = r4[r2]
            if (r9 == r10) goto Ldf
            java.lang.reflect.WildcardType r11 = supertypeOf(r9)
            goto Ldf
        Lce:
            int r4 = r1.length
            if (r4 != r3) goto Ldf
            r3 = r1[r2]
            java.lang.reflect.Type r9 = resolve(r9, r10, r3, r12)
            r10 = r1[r2]
            if (r9 == r10) goto Ldf
            java.lang.reflect.WildcardType r11 = subtypeOf(r9)
        Ldf:
            if (r0 == 0) goto Le4
            r12.put(r0, r11)
        Le4:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.BeanUtils.resolve(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type, java.util.Map):java.lang.reflect.Type");
    }

    static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class<?> declaringClassOf = declaringClassOf(typeVariable);
        if (declaringClassOf != null) {
            Type genericSupertype = getGenericSupertype(type, cls, declaringClassOf);
            if (genericSupertype instanceof ParameterizedType) {
                return ((ParameterizedType) genericSupertype).getActualTypeArguments()[indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
            }
        }
        return typeVariable;
    }

    private static int indexOf(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    static void checkNotPrimitive(Type type) {
        checkArgument(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
    }

    public static <A extends Annotation> A findAnnotation(AnnotatedElement annotatedElement, Class<A> cls) {
        if (cls == null) {
            throw new NullPointerException("annotationType must not be null");
        }
        return (A) findAnnotation(annotatedElement, cls, cls.isAnnotationPresent(Inherited.class), new HashSet());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <A extends Annotation> A findAnnotation(Annotation annotation, Class<A> cls) {
        if (annotation == 0) {
            throw new NullPointerException("annotation must not be null");
        }
        if (cls == null) {
            throw new NullPointerException("annotationType must not be null");
        }
        Class<? extends Annotation> annotationType = annotation.annotationType();
        return annotationType == cls ? annotation : (A) findAnnotation(annotationType, cls, cls.isAnnotationPresent(Inherited.class), new HashSet());
    }

    private static <A extends Annotation> A findAnnotation(AnnotatedElement annotatedElement, Class<A> cls, boolean z, Set<Annotation> set) {
        Class superclass;
        A a;
        A a2;
        if (annotatedElement == null || cls == null) {
            return null;
        }
        A a3 = (A) annotatedElement.getDeclaredAnnotation(cls);
        if (a3 != null) {
            return a3;
        }
        A a4 = (A) findMetaAnnotation(cls, annotatedElement.getDeclaredAnnotations(), z, set);
        if (a4 != null) {
            return a4;
        }
        if (annotatedElement instanceof Class) {
            Class cls2 = (Class) annotatedElement;
            for (Class<?> cls3 : cls2.getInterfaces()) {
                if (cls3 != Annotation.class && (a2 = (A) findAnnotation(cls3, cls, z, set)) != null) {
                    return a2;
                }
            }
            if (z && (superclass = cls2.getSuperclass()) != null && superclass != Object.class && (a = (A) findAnnotation(superclass, cls, true, set)) != null) {
                return a;
            }
        }
        return (A) findMetaAnnotation(cls, getAnnotations(annotatedElement), z, set);
    }

    private static <A extends Annotation> A findMetaAnnotation(Class<A> cls, Annotation[] annotationArr, boolean z, Set<Annotation> set) {
        A a;
        for (Annotation annotation : annotationArr) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            String name = annotationType.getName();
            if (!name.startsWith("java.lang.annotation") && !name.startsWith("kotlin.") && set.add(annotation) && (a = (A) findAnnotation(annotationType, cls, z, set)) != null) {
                return a;
            }
        }
        return null;
    }

    public static Annotation[] getAnnotations(AnnotatedElement annotatedElement) {
        try {
            return annotatedElement.getDeclaredAnnotations();
        } catch (Throwable unused) {
            return new Annotation[0];
        }
    }

    static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z = true;
                boolean z2 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
                if (type == null && !z2) {
                    z = false;
                }
                BeanUtils.checkArgument(z);
            }
            this.ownerType = type == null ? null : BeanUtils.canonicalize(type);
            this.rawType = BeanUtils.canonicalize(type2);
            Type[] typeArr2 = (Type[]) typeArr.clone();
            this.typeArguments = typeArr2;
            int length = typeArr2.length;
            for (int i = 0; i < length; i++) {
                BeanUtils.checkNotPrimitive(this.typeArguments[i]);
                Type[] typeArr3 = this.typeArguments;
                typeArr3[i] = BeanUtils.canonicalize(typeArr3[i]);
            }
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.rawType;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.ownerType;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && BeanUtils.equals(this, (ParameterizedType) obj);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ BeanUtils.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            int length = this.typeArguments.length;
            if (length == 0) {
                return BeanUtils.typeToString(this.rawType);
            }
            StringBuilder sb = new StringBuilder((length + 1) * 30);
            sb.append(BeanUtils.typeToString(this.rawType)).append(SimpleComparison.LESS_THAN_OPERATION).append(BeanUtils.typeToString(this.typeArguments[0]));
            for (int i = 1; i < length; i++) {
                sb.append(", ").append(BeanUtils.typeToString(this.typeArguments[i]));
            }
            return sb.append(SimpleComparison.GREATER_THAN_OPERATION).toString();
        }
    }

    public static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type type) {
            this.componentType = BeanUtils.canonicalize(type);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && BeanUtils.equals(this, (GenericArrayType) obj);
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return BeanUtils.typeToString(this.componentType) + _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
    }

    static final class WildcardTypeImpl implements WildcardType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            BeanUtils.checkArgument(typeArr2.length <= 1);
            BeanUtils.checkArgument(typeArr.length == 1);
            if (typeArr2.length == 1) {
                BeanUtils.checkNotPrimitive(typeArr2[0]);
                BeanUtils.checkArgument(typeArr[0] == Object.class);
                this.lowerBound = BeanUtils.canonicalize(typeArr2[0]);
                this.upperBound = Object.class;
                return;
            }
            BeanUtils.checkNotPrimitive(typeArr[0]);
            this.lowerBound = null;
            this.upperBound = BeanUtils.canonicalize(typeArr[0]);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.lowerBound;
            return type != null ? new Type[]{type} : BeanUtils.EMPTY_TYPE_ARRAY;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && BeanUtils.equals(this, (WildcardType) obj);
        }

        public int hashCode() {
            Type type = this.lowerBound;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            if (this.lowerBound != null) {
                return "? super " + BeanUtils.typeToString(this.lowerBound);
            }
            if (this.upperBound == Object.class) {
                return "?";
            }
            return "? extends " + BeanUtils.typeToString(this.upperBound);
        }
    }

    static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void processJacksonJsonIgnore(final FieldInfo fieldInfo, final Annotation annotation) {
        fieldInfo.ignore = true;
        annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$processJacksonJsonIgnore$11(annotation, fieldInfo, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processJacksonJsonIgnore$11(Annotation annotation, FieldInfo fieldInfo, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            if ("value".equals(name)) {
                fieldInfo.ignore = ((Boolean) invoke).booleanValue();
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean isNoneStaticMemberClass(Class cls, Class cls2) {
        Class<?> enclosingClass;
        if (cls2 == null || cls2.isPrimitive() || cls2 == String.class || cls2 == List.class || (enclosingClass = cls2.getEnclosingClass()) == null) {
            return false;
        }
        if (cls != null && !cls.equals(enclosingClass)) {
            return false;
        }
        ConcurrentMap<Class, Constructor[]> concurrentMap = constructorCache;
        Constructor[] constructorArr = concurrentMap.get(cls2);
        if (constructorArr == null) {
            constructorArr = cls2.getDeclaredConstructors();
            concurrentMap.putIfAbsent(cls2, constructorArr);
        }
        if (constructorArr.length == 0) {
            return false;
        }
        Constructor<?> constructor = constructorArr[0];
        if (constructor.getParameterCount() == 0) {
            return false;
        }
        return enclosingClass.equals(constructor.getParameterTypes()[0]);
    }

    public static void setNoneStaticMemberClassParent(Object obj, Object obj2) {
        Class<?> cls = obj.getClass();
        Field[] fieldArr = declaredFieldCache.get(cls);
        if (fieldArr == null) {
            fieldArr = cls.getDeclaredFields();
            int length = fieldArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (Modifier.isStatic(fieldArr[i].getModifiers())) {
                    ArrayList arrayList = new ArrayList(fieldArr.length);
                    for (Field field : fieldArr) {
                        if (!Modifier.isStatic(field.getModifiers())) {
                            arrayList.add(field);
                        }
                    }
                    fieldArr = (Field[]) arrayList.toArray(new Field[arrayList.size()]);
                } else {
                    i++;
                }
            }
            fieldCache.putIfAbsent(cls, fieldArr);
        }
        Field field2 = null;
        for (Field field3 : fieldArr) {
            if ("this$0".equals(field3.getName())) {
                field2 = field3;
            }
        }
        if (field2 != null) {
            field2.setAccessible(true);
            try {
                field2.set(obj, obj2);
            } catch (IllegalAccessException unused) {
                throw new JSONException("setNoneStaticMemberClassParent error, class " + cls);
            }
        }
    }

    public static void cleanupCache(Class cls) {
        if (cls == null) {
            return;
        }
        fieldCache.remove(cls);
        fieldMapCache.remove(cls);
        declaredFieldCache.remove(cls);
        methodCache.remove(cls);
        constructorCache.remove(cls);
    }

    public static void cleanupCache(ClassLoader classLoader) {
        Iterator<Map.Entry<Class, Field[]>> it = fieldCache.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getKey().getClassLoader() == classLoader) {
                it.remove();
            }
        }
        Iterator<Map.Entry<Class, Map<String, Field>>> it2 = fieldMapCache.entrySet().iterator();
        while (it2.hasNext()) {
            if (it2.next().getKey().getClassLoader() == classLoader) {
                it2.remove();
            }
        }
        Iterator<Map.Entry<Class, Field[]>> it3 = declaredFieldCache.entrySet().iterator();
        while (it3.hasNext()) {
            if (it3.next().getKey().getClassLoader() == classLoader) {
                it3.remove();
            }
        }
        Iterator<Map.Entry<Class, Method[]>> it4 = methodCache.entrySet().iterator();
        while (it4.hasNext()) {
            if (it4.next().getKey().getClassLoader() == classLoader) {
                it4.remove();
            }
        }
        Iterator<Map.Entry<Class, Constructor[]>> it5 = constructorCache.entrySet().iterator();
        while (it5.hasNext()) {
            if (it5.next().getKey().getClassLoader() == classLoader) {
                it5.remove();
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public static void processJSONType1x(BeanInfo beanInfo, Annotation annotation, Method method) {
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            String name = method.getName();
            switch (name.hashCode()) {
                case -1678076717:
                    if (name.equals("deserializer")) {
                        Class cls = (Class) invoke;
                        if (ObjectReader.class.isAssignableFrom(cls)) {
                            beanInfo.deserializer = cls;
                            return;
                        }
                        return;
                    }
                    return;
                case -1315832283:
                    if (name.equals("serializeEnumAsJavaBean") && ((Boolean) invoke).booleanValue()) {
                        beanInfo.writeEnumAsJavaBean = true;
                        return;
                    }
                    return;
                case -1210506547:
                    if (!name.equals("alphabetic") || ((Boolean) invoke).booleanValue()) {
                        return;
                    }
                    beanInfo.alphabetic = false;
                    return;
                case -1052827512:
                    if (name.equals("naming")) {
                        beanInfo.namingStrategy = ((Enum) invoke).name();
                        return;
                    }
                    return;
                case -1008770331:
                    if (name.equals("orders")) {
                        String[] strArr = (String[]) invoke;
                        if (strArr.length != 0) {
                            beanInfo.orders = strArr;
                            return;
                        }
                        return;
                    }
                    return;
                case -940893828:
                    if (!name.equals("serialzeFeatures")) {
                        return;
                    }
                    break;
                case -853109563:
                    if (name.equals("typeKey")) {
                        String str = (String) invoke;
                        if (str.isEmpty()) {
                            return;
                        }
                        beanInfo.typeKey = str;
                        return;
                    }
                    return;
                case -676507419:
                    if (name.equals("typeName")) {
                        String str2 = (String) invoke;
                        if (str2.isEmpty()) {
                            return;
                        }
                        beanInfo.typeName = str2;
                        return;
                    }
                    return;
                case -597985902:
                    if (name.equals("serializer")) {
                        Class cls2 = (Class) invoke;
                        if (ObjectWriter.class.isAssignableFrom(cls2)) {
                            beanInfo.writeEnumAsJavaBean = true;
                            beanInfo.serializer = cls2;
                            return;
                        }
                        return;
                    }
                    return;
                case -167039347:
                    if (name.equals("rootName")) {
                        String str3 = (String) invoke;
                        if (str3.isEmpty()) {
                            return;
                        }
                        beanInfo.rootName = str3;
                        return;
                    }
                    return;
                case 90259659:
                    if (name.equals("includes")) {
                        String[] strArr2 = (String[]) invoke;
                        if (strArr2.length != 0) {
                            beanInfo.includes = strArr2;
                            return;
                        }
                        return;
                    }
                    return;
                case 1752415457:
                    if (name.equals("ignores")) {
                        String[] strArr3 = (String[]) invoke;
                        if (strArr3.length != 0) {
                            if (beanInfo.ignores == null) {
                                beanInfo.ignores = strArr3;
                                return;
                            }
                            LinkedHashSet linkedHashSet = new LinkedHashSet();
                            linkedHashSet.addAll(Arrays.asList(beanInfo.ignores));
                            linkedHashSet.addAll(Arrays.asList(strArr3));
                            beanInfo.ignores = (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
                            return;
                        }
                        return;
                    }
                    return;
                case 1869860669:
                    if (name.equals("serializeFeatures")) {
                        break;
                    } else {
                        return;
                    }
                case 1970571962:
                    if (name.equals("seeAlso")) {
                        Class[] clsArr = (Class[]) invoke;
                        if (clsArr.length != 0) {
                            beanInfo.seeAlso = clsArr;
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
            for (Enum r0 : (Enum[]) invoke) {
                String name2 = r0.name();
                switch (name2.hashCode()) {
                    case -1937516631:
                        if (name2.equals("WriteNullNumberAsZero")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.WriteNullNumberAsZero.mask;
                            break;
                        } else {
                            break;
                        }
                    case -1779797023:
                        if (name2.equals("IgnoreErrorGetter")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.IgnoreErrorGetter.mask;
                            break;
                        } else {
                            break;
                        }
                    case -335314544:
                        if (name2.equals("WriteEnumUsingToString")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.WriteEnumUsingToString.mask;
                            break;
                        } else {
                            break;
                        }
                    case -211922948:
                        if (name2.equals("BrowserCompatible")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.BrowserCompatible.mask;
                            break;
                        } else {
                            break;
                        }
                    case -102443356:
                        if (name2.equals("WriteNullStringAsEmpty")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.WriteNullStringAsEmpty.mask;
                            break;
                        } else {
                            break;
                        }
                    case -62964779:
                        if (name2.equals("NotWriteRootClassName")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.NotWriteRootClassName.mask;
                            break;
                        } else {
                            break;
                        }
                    case 1009181687:
                        if (name2.equals("WriteNullListAsEmpty")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.WriteNullListAsEmpty.mask;
                            break;
                        } else {
                            break;
                        }
                    case 1519175029:
                        if (name2.equals("WriteNonStringValueAsString")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.WriteNonStringValueAsString.mask;
                            break;
                        } else {
                            break;
                        }
                    case 1808123471:
                        if (name2.equals("WriteNullBooleanAsFalse")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.WriteNullBooleanAsFalse.mask;
                            break;
                        } else {
                            break;
                        }
                    case 1879776036:
                        if (name2.equals("WriteClassName")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.WriteClassName.mask;
                            break;
                        } else {
                            break;
                        }
                    case 2049970061:
                        if (name2.equals("WriteMapNullValue")) {
                            beanInfo.writerFeatures |= JSONWriter.Feature.WriteNulls.mask;
                            break;
                        } else {
                            break;
                        }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void processJacksonJsonFormat(final FieldInfo fieldInfo, final Annotation annotation) {
        annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda19
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$processJacksonJsonFormat$12(annotation, fieldInfo, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processJacksonJsonFormat$12(Annotation annotation, FieldInfo fieldInfo, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            int hashCode = name.hashCode();
            if (hashCode == -1097462182) {
                if (name.equals("locale")) {
                    String str = (String) invoke;
                    if (str.isEmpty() || "##default".equals(str)) {
                        return;
                    }
                    fieldInfo.locale = Locale.forLanguageTag(str);
                    return;
                }
                return;
            }
            if (hashCode == -791090288) {
                if (name.equals("pattern")) {
                    String str2 = (String) invoke;
                    if (str2.length() != 0) {
                        fieldInfo.format = str2;
                        return;
                    }
                    return;
                }
                return;
            }
            if (hashCode == 109399969 && name.equals("shape")) {
                String name2 = ((Enum) invoke).name();
                if ("STRING".equals(name2)) {
                    fieldInfo.features |= JSONWriter.Feature.WriteNonStringValueAsString.mask;
                } else if ("NUMBER".equals(name2)) {
                    fieldInfo.format = "millis";
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void processJacksonJsonFormat(final BeanInfo beanInfo, final Annotation annotation) {
        annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$processJacksonJsonFormat$13(annotation, beanInfo, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processJacksonJsonFormat$13(Annotation annotation, BeanInfo beanInfo, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            int hashCode = name.hashCode();
            if (hashCode == -1097462182) {
                if (name.equals("locale")) {
                    String str = (String) invoke;
                    if (str.isEmpty() || "##default".equals(str)) {
                        return;
                    }
                    beanInfo.locale = Locale.forLanguageTag(str);
                    return;
                }
                return;
            }
            if (hashCode == -791090288) {
                if (name.equals("pattern")) {
                    String str2 = (String) invoke;
                    if (str2.isEmpty()) {
                        return;
                    }
                    beanInfo.format = str2;
                    return;
                }
                return;
            }
            if (hashCode == 109399969 && name.equals("shape")) {
                String name2 = ((Enum) invoke).name();
                if ("NUMBER".equals(name2)) {
                    beanInfo.format = "millis";
                } else if ("OBJECT".equals(name2)) {
                    beanInfo.writeEnumAsJavaBean = true;
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void processJacksonJsonInclude(final BeanInfo beanInfo, final Annotation annotation) {
        annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$processJacksonJsonInclude$14(annotation, beanInfo, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processJacksonJsonInclude$14(Annotation annotation, BeanInfo beanInfo, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            if ("value".equals(name)) {
                String name2 = ((Enum) invoke).name();
                int hashCode = name2.hashCode();
                if (hashCode == -7755493) {
                    if (name2.equals("NON_EMPTY")) {
                        beanInfo.writerFeatures |= JSONWriter.Feature.NotWriteEmptyArray.mask;
                    }
                } else if (hashCode == 10566287) {
                    if (name2.equals("NON_DEFAULT")) {
                        beanInfo.writerFeatures |= JSONWriter.Feature.NotWriteDefaultValue.mask;
                    }
                } else if (hashCode == 1933739535 && name2.equals("ALWAYS")) {
                    beanInfo.writerFeatures |= JSONWriter.Feature.WriteNulls.mask;
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void processJacksonJsonInclude(final FieldInfo fieldInfo, final Annotation annotation) {
        annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda18
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$processJacksonJsonInclude$15(annotation, fieldInfo, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processJacksonJsonInclude$15(Annotation annotation, FieldInfo fieldInfo, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            if ("value".equals(name)) {
                String name2 = ((Enum) invoke).name();
                int hashCode = name2.hashCode();
                if (hashCode == -7755493) {
                    if (name2.equals("NON_EMPTY")) {
                        fieldInfo.features |= JSONWriter.Feature.NotWriteEmptyArray.mask;
                        fieldInfo.features |= JSONWriter.Feature.IgnoreEmpty.mask;
                        return;
                    }
                    return;
                }
                if (hashCode == 10566287) {
                    if (name2.equals("NON_DEFAULT")) {
                        fieldInfo.features |= JSONWriter.Feature.NotWriteDefaultValue.mask;
                    }
                } else if (hashCode == 1933739535 && name2.equals("ALWAYS")) {
                    fieldInfo.features |= JSONWriter.Feature.WriteNulls.mask;
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void processJacksonJsonUnwrapped(final FieldInfo fieldInfo, final Annotation annotation) {
        annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda15
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$processJacksonJsonUnwrapped$16(annotation, fieldInfo, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processJacksonJsonUnwrapped$16(Annotation annotation, FieldInfo fieldInfo, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            if ("enabled".equals(name) && ((Boolean) invoke).booleanValue()) {
                fieldInfo.features = FieldInfo.UNWRAPPED_MASK;
            }
        } catch (Throwable unused) {
        }
    }

    public static void processJacksonJsonTypeName(final BeanInfo beanInfo, final Annotation annotation) {
        annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$processJacksonJsonTypeName$17(annotation, beanInfo, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processJacksonJsonTypeName$17(Annotation annotation, BeanInfo beanInfo, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            if ("value".equals(name)) {
                String str = (String) invoke;
                if (str.isEmpty()) {
                    return;
                }
                beanInfo.typeName = str;
            }
        } catch (Throwable unused) {
        }
    }

    public static void processJacksonJsonSubTypesType(final BeanInfo beanInfo, final int i, final Annotation annotation) {
        annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda14
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$processJacksonJsonSubTypesType$18(annotation, beanInfo, i, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processJacksonJsonSubTypesType$18(Annotation annotation, BeanInfo beanInfo, int i, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            int hashCode = name.hashCode();
            if (hashCode == 3373707) {
                if (name.equals(HintConstants.AUTOFILL_HINT_NAME)) {
                    beanInfo.seeAlsoNames[i] = (String) invoke;
                    return;
                }
                return;
            }
            if (hashCode == 111972721 && name.equals("value")) {
                beanInfo.seeAlso[i] = (Class) invoke;
            }
        } catch (Throwable unused) {
        }
    }

    public static void processGsonSerializedName(final FieldInfo fieldInfo, final Annotation annotation) {
        annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$processGsonSerializedName$19(annotation, fieldInfo, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processGsonSerializedName$19(Annotation annotation, FieldInfo fieldInfo, Method method) {
        String name = method.getName();
        try {
            Object invoke = method.invoke(annotation, new Object[0]);
            int hashCode = name.hashCode();
            if (hashCode == -1408024454) {
                if (name.equals("alternate")) {
                    String[] strArr = (String[]) invoke;
                    if (strArr.length != 0) {
                        fieldInfo.alternateNames = strArr;
                        return;
                    }
                    return;
                }
                return;
            }
            if (hashCode == 111972721 && name.equals("value")) {
                String str = (String) invoke;
                if (str.isEmpty()) {
                    return;
                }
                fieldInfo.fieldName = str;
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean isExtendedMap(Class cls) {
        if (cls == HashMap.class || cls == LinkedHashMap.class || cls == TreeMap.class || cls.getSimpleName().isEmpty()) {
            return false;
        }
        final Class superclass = cls.getSuperclass();
        if ((superclass != HashMap.class && superclass != LinkedHashMap.class && superclass != TreeMap.class) || getDefaultConstructor(cls, false) != null) {
            return false;
        }
        final ArrayList arrayList = new ArrayList();
        declaredFields(cls, new Consumer() { // from class: com.alibaba.fastjson2.util.BeanUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BeanUtils.lambda$isExtendedMap$20(superclass, arrayList, (Field) obj);
            }
        });
        return !arrayList.isEmpty();
    }

    static /* synthetic */ void lambda$isExtendedMap$20(Class cls, List list, Field field) {
        int modifiers = field.getModifiers();
        if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers) || field.getDeclaringClass().isAssignableFrom(cls) || "this$0".equals(field.getName())) {
            return;
        }
        list.add(field);
    }
}
