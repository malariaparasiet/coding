package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.PropertyNamingStrategy;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.codec.FieldInfo;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.BeanUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.JdbcSupport;
import com.alibaba.fastjson2.util.TypeUtils;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.BiConsumer;

/* loaded from: classes2.dex */
public abstract class FieldReader<T> implements Comparable<FieldReader> {
    public final Object defaultValue;
    public final long features;
    public final Field field;
    public final Class fieldClass;
    final boolean fieldClassSerializable;
    public final String fieldName;
    final long fieldNameHash;
    final long fieldNameHashLCase;
    protected final long fieldOffset;
    public final Type fieldType;
    public final String format;
    Class itemClass;
    volatile ObjectReader itemReader;
    Type itemType;
    public final Locale locale;
    public final Method method;
    final boolean noneStaticMemberClass;
    public final int ordinal;
    final boolean readOnly;
    volatile ObjectReader reader;
    volatile JSONPath referenceCache;
    public final JSONSchema schema;

    public abstract void accept(T t, Object obj);

    public void acceptExtra(Object obj, String str, Object obj2) {
    }

    public ObjectReader checkObjectAutoType(JSONReader jSONReader) {
        return null;
    }

    public BiConsumer getFunction() {
        return null;
    }

    public ObjectReader getInitReader() {
        return null;
    }

    public abstract Object readFieldValue(JSONReader jSONReader);

    public abstract void readFieldValue(JSONReader jSONReader, T t);

    public FieldReader(String str, Type type, Class cls, int i, long j, String str2, Locale locale, Object obj, JSONSchema jSONSchema, Method method, Field field) {
        Class<?> declaringClass;
        this.fieldName = str;
        this.fieldType = type;
        this.fieldClass = cls;
        boolean z = false;
        this.fieldClassSerializable = cls != null && (Serializable.class.isAssignableFrom(cls) || Modifier.isInterface(cls.getModifiers()) || BeanUtils.isRecord(cls));
        this.features = j;
        this.fieldNameHash = Fnv.hashCode64(str);
        this.fieldNameHashLCase = Fnv.hashCode64LCase(str);
        this.ordinal = i;
        this.format = str2;
        this.locale = locale;
        this.defaultValue = obj;
        this.schema = jSONSchema;
        this.method = method;
        this.field = field;
        if ((method != null && method.getParameterCount() == 0) || (field != null && Modifier.isFinal(field.getModifiers()))) {
            z = true;
        }
        this.readOnly = z;
        long objectFieldOffset = (field == null || (j & FieldInfo.DISABLE_UNSAFE) != 0) ? -1L : JDKUtils.UNSAFE.objectFieldOffset(field);
        this.fieldOffset = objectFieldOffset;
        if (objectFieldOffset == -1 && field != null && method == null) {
            try {
                field.setAccessible(true);
            } catch (Throwable th) {
                JDKUtils.setReflectErrorLast(th);
            }
        }
        if (method != null) {
            declaringClass = method.getDeclaringClass();
        } else {
            declaringClass = field != null ? field.getDeclaringClass() : null;
        }
        this.noneStaticMemberClass = BeanUtils.isNoneStaticMemberClass(declaringClass, cls);
    }

    public void acceptDefaultValue(T t) {
        Object obj = this.defaultValue;
        if (obj != null) {
            accept((FieldReader<T>) t, obj);
        }
    }

    public ObjectReader getObjectReader(JSONReader jSONReader) {
        if (this.reader != null) {
            return this.reader;
        }
        ObjectReader objectReader = jSONReader.getObjectReader(this.fieldType);
        this.reader = objectReader;
        return objectReader;
    }

    public ObjectReader getObjectReader(JSONReader.Context context) {
        if (this.reader != null) {
            return this.reader;
        }
        ObjectReader objectReader = context.getObjectReader(this.fieldType);
        this.reader = objectReader;
        return objectReader;
    }

    public ObjectReader getObjectReader(ObjectReaderProvider objectReaderProvider) {
        if (this.reader != null) {
            return this.reader;
        }
        ObjectReader objectReader = objectReaderProvider.getObjectReader(this.fieldType, (this.features & JSONReader.Feature.FieldBased.mask) != 0);
        this.reader = objectReader;
        return objectReader;
    }

    public Type getItemType() {
        return this.itemType;
    }

    public Class getItemClass() {
        Type type = this.itemType;
        if (type == null) {
            return null;
        }
        if (this.itemClass == null) {
            this.itemClass = TypeUtils.getClass(type);
        }
        return this.itemClass;
    }

    public long getItemClassHash() {
        Class itemClass = getItemClass();
        if (itemClass == null) {
            return 0L;
        }
        return Fnv.hashCode64(itemClass.getName());
    }

    public String toString() {
        Member member = this.method;
        if (member == null) {
            member = this.field;
        }
        if (member != null) {
            return member.getName();
        }
        return this.fieldName;
    }

    public void addResolveTask(JSONReader jSONReader, Object obj, String str) {
        JSONPath of;
        if (this.referenceCache != null && this.referenceCache.toString().equals(str)) {
            of = this.referenceCache;
        } else {
            of = JSONPath.of(str);
            this.referenceCache = of;
        }
        jSONReader.addResolveTask(this, obj, of);
    }

    @Override // java.lang.Comparable
    public int compareTo(FieldReader fieldReader) {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3;
        Class<?> declaringClass;
        Class<?> declaringClass2;
        int compareTo = this.fieldName.compareTo(fieldReader.fieldName);
        if (compareTo != 0) {
            int i = this.ordinal;
            int i2 = fieldReader.ordinal;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            return compareTo;
        }
        int i3 = isReadOnly() == fieldReader.isReadOnly() ? 0 : isReadOnly() ? 1 : -1;
        if (i3 == 0) {
            Member member = this.field;
            if (member == null) {
                member = this.method;
            }
            Member member2 = fieldReader.field;
            if (member2 == null) {
                member2 = fieldReader.method;
            }
            if (member != null && member2 != null && member.getClass() != member2.getClass() && (declaringClass2 = member.getDeclaringClass()) != (declaringClass = member2.getDeclaringClass())) {
                if (declaringClass2.isAssignableFrom(declaringClass)) {
                    return 1;
                }
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    return -1;
                }
            }
            Field field = this.field;
            if (field != null && fieldReader.field != null) {
                Class<?> declaringClass3 = field.getDeclaringClass();
                Class<?> declaringClass4 = fieldReader.field.getDeclaringClass();
                for (Class<? super Object> superclass = declaringClass3.getSuperclass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                    if (superclass == declaringClass4) {
                        return 1;
                    }
                }
                do {
                    declaringClass4 = declaringClass4.getSuperclass();
                    if (declaringClass4 != null && declaringClass4 != Object.class) {
                    }
                } while (declaringClass4 != declaringClass3);
                return -1;
            }
            Method method = this.method;
            if (method != null && fieldReader.method != null) {
                Class<?> declaringClass5 = method.getDeclaringClass();
                Class<?> declaringClass6 = fieldReader.method.getDeclaringClass();
                if (declaringClass5 != declaringClass6) {
                    for (Class<? super Object> superclass2 = declaringClass5.getSuperclass(); superclass2 != null && superclass2 != Object.class; superclass2 = superclass2.getSuperclass()) {
                        if (superclass2 == declaringClass6) {
                            return -1;
                        }
                    }
                    for (Class<? super Object> superclass3 = declaringClass6.getSuperclass(); superclass3 != null && superclass3 != Object.class; superclass3 = superclass3.getSuperclass()) {
                        if (superclass3 == declaringClass5) {
                            return 1;
                        }
                    }
                }
                if (this.method.getParameterCount() == 1 && fieldReader.method.getParameterCount() == 1 && (cls = this.method.getParameterTypes()[0]) != (cls2 = fieldReader.method.getParameterTypes()[0])) {
                    if (cls.isAssignableFrom(cls2)) {
                        return 1;
                    }
                    if (cls2.isAssignableFrom(cls)) {
                        return -1;
                    }
                    if (Collection.class.isAssignableFrom(cls2) && !Collection.class.isAssignableFrom(cls)) {
                        return 1;
                    }
                    if (Collection.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls2)) {
                        return -1;
                    }
                    if (needCompareToActualFieldClass(cls) || needCompareToActualFieldClass(cls2)) {
                        try {
                            cls3 = declaringClass5.getDeclaredField(this.fieldName).getType();
                            if (cls3 == null) {
                                try {
                                    cls3 = declaringClass6.getDeclaredField(this.fieldName).getType();
                                } catch (NoSuchFieldException unused) {
                                }
                            }
                        } catch (NoSuchFieldException unused2) {
                            cls3 = null;
                        }
                        if (cls3 != null) {
                            while (cls != null && cls != Object.class) {
                                if (cls == cls3) {
                                    return -1;
                                }
                                cls = cls.getSuperclass();
                            }
                            while (cls2 != null && cls2 != Object.class) {
                                if (cls2 == cls3) {
                                    return 1;
                                }
                                cls2 = cls2.getSuperclass();
                            }
                        }
                    }
                    JSONField jSONField = (JSONField) BeanUtils.findAnnotation(this.method, JSONField.class);
                    JSONField jSONField2 = (JSONField) BeanUtils.findAnnotation(fieldReader.method, JSONField.class);
                    boolean z = jSONField != null;
                    if (z == (jSONField2 == null)) {
                        return z ? -1 : 1;
                    }
                }
                String name = this.method.getName();
                String name2 = fieldReader.method.getName();
                if (!name.equals(name2)) {
                    boolean startsWith = name.startsWith("set");
                    if (startsWith != name2.startsWith("set")) {
                        return startsWith ? -1 : 1;
                    }
                    String str = BeanUtils.setterName(name, (String) null);
                    String str2 = BeanUtils.setterName(name2, (String) null);
                    boolean equals = this.fieldName.equals(str);
                    if (equals != fieldReader.fieldName.equals(str2)) {
                        return equals ? 1 : -1;
                    }
                }
            }
            ObjectReader initReader = getInitReader();
            ObjectReader initReader2 = fieldReader.getInitReader();
            if (initReader != null && initReader2 == null) {
                return -1;
            }
            if (initReader == null && initReader2 != null) {
                return 1;
            }
            Class cls4 = this.fieldClass;
            Class cls5 = fieldReader.fieldClass;
            boolean isPrimitive = cls4.isPrimitive();
            boolean isPrimitive2 = cls5.isPrimitive();
            if (isPrimitive && !isPrimitive2) {
                return -1;
            }
            if (!isPrimitive && isPrimitive2) {
                return 1;
            }
            boolean startsWith2 = cls4.getName().startsWith("java.");
            boolean startsWith3 = cls5.getName().startsWith("java.");
            if (startsWith2 && !startsWith3) {
                return -1;
            }
            if (!startsWith2 && startsWith3) {
                return 1;
            }
        }
        return i3;
    }

    public boolean isUnwrapped() {
        return (this.features & FieldInfo.UNWRAPPED_MASK) != 0;
    }

    public void addResolveTask(JSONReader jSONReader, List list, int i, String str) {
        jSONReader.addResolveTask(list, i, JSONPath.of(str));
    }

    public void readFieldValueJSONB(JSONReader jSONReader, T t) {
        readFieldValue(jSONReader, t);
    }

    public void accept(T t, boolean z) {
        accept((FieldReader<T>) t, Boolean.valueOf(z));
    }

    public boolean supportAcceptType(Class cls) {
        return this.fieldClass == cls;
    }

    public void accept(T t, byte b) {
        accept((FieldReader<T>) t, Byte.valueOf(b));
    }

    public void accept(T t, short s) {
        accept((FieldReader<T>) t, Short.valueOf(s));
    }

    public void accept(T t, int i) {
        accept((FieldReader<T>) t, Integer.valueOf(i));
    }

    public void accept(T t, long j) {
        accept((FieldReader<T>) t, Long.valueOf(j));
    }

    public void accept(T t, char c) {
        accept((FieldReader<T>) t, Character.valueOf(c));
    }

    public void accept(T t, float f) {
        accept((FieldReader<T>) t, Float.valueOf(f));
    }

    public void accept(T t, double d) {
        accept((FieldReader<T>) t, Double.valueOf(d));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0052 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void acceptAny(T r12, java.lang.Object r13, long r14) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.FieldReader.acceptAny(java.lang.Object, java.lang.Object, long):void");
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void processExtra(JSONReader jSONReader, Object obj) {
        jSONReader.skipValue();
    }

    public ObjectReader getItemObjectReader(JSONReader.Context context) {
        if (this.itemReader != null) {
            return this.itemReader;
        }
        ObjectReader objectReader = context.getObjectReader(this.itemType);
        this.itemReader = objectReader;
        return objectReader;
    }

    public ObjectReader getItemObjectReader(JSONReader jSONReader) {
        return getItemObjectReader(jSONReader.getContext());
    }

    static ObjectReader createFormattedObjectReader(Type type, Class cls, String str, Locale locale) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String typeName = type.getTypeName();
        typeName.hashCode();
        switch (typeName) {
            case "byte[]":
            case "[B":
                return new ObjectReaderImplInt8Array(str);
            case "java.sql.Date":
                return JdbcSupport.createDateReader((Class) type, str, locale);
            case "java.sql.Time":
                return JdbcSupport.createTimeReader((Class) type, str, locale);
            case "java.sql.Timestamp":
                return JdbcSupport.createTimestampReader((Class) type, str, locale);
            default:
                if (Calendar.class.isAssignableFrom(cls)) {
                    return ObjectReaderImplCalendar.of(str, locale);
                }
                if (cls == ZonedDateTime.class) {
                    return ObjectReaderImplZonedDateTime.of(str, locale);
                }
                if (cls == LocalDateTime.class) {
                    return new ObjectReaderImplLocalDateTime(str, locale);
                }
                if (cls == LocalDate.class) {
                    return ObjectReaderImplLocalDate.of(str, locale);
                }
                if (cls == LocalTime.class) {
                    return new ObjectReaderImplLocalTime(str, locale);
                }
                if (cls == Instant.class) {
                    return ObjectReaderImplInstant.of(str, locale);
                }
                if (cls == OffsetTime.class) {
                    return ObjectReaderImplOffsetTime.of(str, locale);
                }
                if (cls == OffsetDateTime.class) {
                    return ObjectReaderImplOffsetDateTime.of(str, locale);
                }
                if (cls == Optional.class) {
                    return ObjectReaderImplOptional.of(type, str, locale);
                }
                if (cls == Date.class) {
                    return ObjectReaderImplDate.of(str, locale);
                }
                return null;
        }
    }

    public boolean sameTo(FieldReader fieldReader) {
        Field field = this.field;
        if (field != null) {
            String name = field.getName();
            Field field2 = fieldReader.field;
            if (field2 != null && name.equals(field2.getName())) {
                return true;
            }
            if (fieldReader.method != null && name.equals(getActualFieldName(fieldReader))) {
                return true;
            }
        }
        if (this.method != null) {
            String actualFieldName = getActualFieldName(this);
            if (fieldReader.method != null) {
                String actualFieldName2 = getActualFieldName(fieldReader);
                if (actualFieldName != null && actualFieldName.equals(actualFieldName2)) {
                    return true;
                }
            }
            Field field3 = fieldReader.field;
            if (field3 != null && actualFieldName != null && actualFieldName.equals(field3.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean belongTo(Class cls) {
        Field field = this.field;
        if (field != null && field.getDeclaringClass() == cls) {
            return true;
        }
        Method method = this.method;
        return method != null && method.getDeclaringClass().isAssignableFrom(cls);
    }

    private String getActualFieldName(FieldReader fieldReader) {
        String name = fieldReader.method.getName();
        return fieldReader.isReadOnly() ? BeanUtils.getterName(name, PropertyNamingStrategy.CamelCase.name()) : BeanUtils.setterName(name, PropertyNamingStrategy.CamelCase.name());
    }

    private boolean needCompareToActualFieldClass(Class cls) {
        return cls.isEnum() || cls.isInterface();
    }
}
