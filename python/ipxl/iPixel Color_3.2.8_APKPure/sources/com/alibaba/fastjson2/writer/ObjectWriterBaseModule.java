package com.alibaba.fastjson2.writer;

import androidx.autofill.HintConstants;
import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONPObject;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.annotation.JSONCompiler;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.codec.BeanInfo;
import com.alibaba.fastjson2.codec.FieldInfo;
import com.alibaba.fastjson2.modules.ObjectWriterAnnotationProcessor;
import com.alibaba.fastjson2.modules.ObjectWriterModule;
import com.alibaba.fastjson2.support.LambdaMiscCodec;
import com.alibaba.fastjson2.support.money.MoneySupport;
import com.alibaba.fastjson2.util.ApacheLang3Support;
import com.alibaba.fastjson2.util.BeanUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.JdbcSupport;
import com.alibaba.fastjson2.util.JodaSupport;
import com.alibaba.fastjson2.util.KotlinUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import com.alibaba.fastjson2.writer.ObjectWriterBaseModule;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import kotlin.text.Typography;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

/* loaded from: classes2.dex */
public class ObjectWriterBaseModule implements ObjectWriterModule {
    static ObjectWriterAdapter STACK_TRACE_ELEMENT_WRITER;
    final WriterAnnotationProcessor annotationProcessor = new WriterAnnotationProcessor();
    final ObjectWriterProvider provider;

    public ObjectWriterBaseModule(ObjectWriterProvider objectWriterProvider) {
        this.provider = objectWriterProvider;
    }

    @Override // com.alibaba.fastjson2.modules.ObjectWriterModule
    public ObjectWriterProvider getProvider() {
        return this.provider;
    }

    @Override // com.alibaba.fastjson2.modules.ObjectWriterModule
    public ObjectWriterAnnotationProcessor getAnnotationProcessor() {
        return this.annotationProcessor;
    }

    public class WriterAnnotationProcessor implements ObjectWriterAnnotationProcessor {
        public WriterAnnotationProcessor() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:123:0x00fd, code lost:
        
            if (r12.equals("com.fasterxml.jackson.annotation.JsonFormat") == false) goto L49;
         */
        @Override // com.alibaba.fastjson2.modules.ObjectWriterAnnotationProcessor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void getBeanInfo(final com.alibaba.fastjson2.codec.BeanInfo r18, java.lang.Class r19) {
            /*
                Method dump skipped, instructions count: 732
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.writer.ObjectWriterBaseModule.WriterAnnotationProcessor.getBeanInfo(com.alibaba.fastjson2.codec.BeanInfo, java.lang.Class):void");
        }

        @Override // com.alibaba.fastjson2.modules.ObjectWriterAnnotationProcessor
        public void getFieldInfo(BeanInfo beanInfo, FieldInfo fieldInfo, Class cls, Field field) {
            boolean isUseJacksonAnnotation;
            Class cls2;
            Field field2;
            JSONField jSONField = null;
            if (cls != null && (cls2 = ObjectWriterBaseModule.this.provider.mixInCache.get(cls)) != null && cls2 != cls) {
                try {
                    field2 = cls2.getDeclaredField(field.getName());
                } catch (Exception unused) {
                    field2 = null;
                }
                if (field2 != null) {
                    getFieldInfo(beanInfo, fieldInfo, cls2, field2);
                }
            }
            if (ObjectWriterBaseModule.this.provider.mixInCache.get(field.getType()) != null) {
                fieldInfo.fieldClassMixIn = true;
            }
            if (Modifier.isTransient(field.getModifiers())) {
                fieldInfo.isTransient = true;
                if (fieldInfo.skipTransient && beanInfo.skipTransient) {
                    fieldInfo.ignore = true;
                }
            }
            Annotation[] annotations = BeanUtils.getAnnotations(field);
            if (annotations.length == 0 && KotlinUtils.isKotlin(cls)) {
                annotations = BeanUtils.getAnnotations(field.getType());
                Constructor kotlinConstructor = KotlinUtils.getKotlinConstructor(BeanUtils.getConstructor(cls));
                if (kotlinConstructor != null) {
                    String[] koltinConstructorParameters = KotlinUtils.getKoltinConstructorParameters(cls);
                    int i = 0;
                    while (true) {
                        if (i >= koltinConstructorParameters.length) {
                            break;
                        }
                        if (koltinConstructorParameters[i].equals(field.getName())) {
                            annotations = kotlinConstructor.getParameterAnnotations()[i];
                            break;
                        }
                        i++;
                    }
                    if (fieldInfo.ignore) {
                        for (Annotation annotation : annotations) {
                            if (annotation.annotationType() == JSONField.class) {
                                fieldInfo.ignore = !((JSONField) r9).serialize();
                            }
                        }
                    }
                }
            }
            for (Annotation annotation2 : annotations) {
                Class<? extends Annotation> annotationType = annotation2.annotationType();
                if (jSONField != null || (jSONField = (JSONField) BeanUtils.findAnnotation(annotation2, JSONField.class)) != annotation2) {
                    String name = annotationType.getName();
                    isUseJacksonAnnotation = JSONFactory.isUseJacksonAnnotation();
                    name.hashCode();
                    switch (name) {
                        case "com.google.gson.annotations.SerializedName":
                            if (JSONFactory.isUseGsonAnnotation()) {
                                BeanUtils.processGsonSerializedName(fieldInfo, annotation2);
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonInclude":
                            if (isUseJacksonAnnotation) {
                                BeanUtils.processJacksonJsonInclude(beanInfo, annotation2);
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonManagedReference":
                            if (isUseJacksonAnnotation) {
                                fieldInfo.features |= JSONWriter.Feature.ReferenceDetection.mask;
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.databind.annotation.JsonSerialize":
                            if (isUseJacksonAnnotation) {
                                processJacksonJsonSerialize(fieldInfo, annotation2);
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonFormat":
                            if (isUseJacksonAnnotation) {
                                BeanUtils.processJacksonJsonFormat(fieldInfo, annotation2);
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonIgnore":
                            if (isUseJacksonAnnotation) {
                                BeanUtils.processJacksonJsonIgnore(fieldInfo, annotation2);
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonValue":
                            if (isUseJacksonAnnotation) {
                                fieldInfo.features |= FieldInfo.VALUE_MASK;
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonAnyGetter":
                            if (isUseJacksonAnnotation) {
                                fieldInfo.features |= FieldInfo.UNWRAPPED_MASK;
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonProperty":
                            if (isUseJacksonAnnotation) {
                                processJacksonJsonProperty(fieldInfo, annotation2);
                                break;
                            } else {
                                break;
                            }
                        case "com.alibaba.fastjson.annotation.JSONField":
                            processJSONField1x(fieldInfo, annotation2);
                            break;
                        case "com.fasterxml.jackson.annotation.JsonBackReference":
                            if (isUseJacksonAnnotation) {
                                fieldInfo.features |= 2305843009213693952L;
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonRawValue":
                            if (isUseJacksonAnnotation) {
                                fieldInfo.features = FieldInfo.RAW_VALUE_MASK | fieldInfo.features;
                                break;
                            } else {
                                break;
                            }
                    }
                }
            }
            if (jSONField == null) {
                return;
            }
            loadFieldInfo(fieldInfo, jSONField);
            Class<?> writeUsing = jSONField.writeUsing();
            if (ObjectWriter.class.isAssignableFrom(writeUsing)) {
                fieldInfo.writeUsing = writeUsing;
            }
            Class<?> serializeUsing = jSONField.serializeUsing();
            if (ObjectWriter.class.isAssignableFrom(serializeUsing)) {
                fieldInfo.writeUsing = serializeUsing;
            }
            if (jSONField.jsonDirect()) {
                fieldInfo.features |= FieldInfo.RAW_VALUE_MASK;
            }
            if ((fieldInfo.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) == 0 || String.class.equals(field.getType()) || fieldInfo.writeUsing != null) {
                return;
            }
            fieldInfo.writeUsing = ObjectWriterImplToString.class;
        }

        private void processJacksonJsonSubTypes(final BeanInfo beanInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor$$ExternalSyntheticLambda5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectWriterBaseModule.WriterAnnotationProcessor.lambda$processJacksonJsonSubTypes$1(annotation, beanInfo, (Method) obj);
                }
            });
        }

        static /* synthetic */ void lambda$processJacksonJsonSubTypes$1(Annotation annotation, BeanInfo beanInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if ("value".equals(name)) {
                    Annotation[] annotationArr = (Annotation[]) invoke;
                    if (annotationArr.length != 0) {
                        beanInfo.seeAlso = new Class[annotationArr.length];
                        beanInfo.seeAlsoNames = new String[annotationArr.length];
                        for (int i = 0; i < annotationArr.length; i++) {
                            BeanUtils.processJacksonJsonSubTypesType(beanInfo, i, annotationArr[i]);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }

        private void processJacksonJsonSerialize(final BeanInfo beanInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectWriterBaseModule.WriterAnnotationProcessor.this.m2819x32eb22ba(annotation, beanInfo, (Method) obj);
                }
            });
        }

        /* renamed from: lambda$processJacksonJsonSerialize$2$com-alibaba-fastjson2-writer-ObjectWriterBaseModule$WriterAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2819x32eb22ba(Annotation annotation, BeanInfo beanInfo, Method method) {
            Class processUsing;
            Class processUsing2;
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                int hashCode = name.hashCode();
                if (hashCode == 111582340) {
                    if (!name.equals("using") || (processUsing = processUsing((Class) invoke)) == null) {
                        return;
                    }
                    beanInfo.serializer = processUsing;
                    return;
                }
                if (hashCode == 491860325 && name.equals("keyUsing") && (processUsing2 = processUsing((Class) invoke)) != null) {
                    beanInfo.serializer = processUsing2;
                }
            } catch (Throwable unused) {
            }
        }

        private Class processUsing(Class cls) {
            String name = cls.getName();
            if (!"com.fasterxml.jackson.databind.JsonSerializer$None".equals(name) && ObjectWriter.class.isAssignableFrom(cls)) {
                return cls;
            }
            if ("com.fasterxml.jackson.databind.ser.std.ToStringSerializer".equals(name)) {
                return ObjectWriterImplToString.class;
            }
            return null;
        }

        private void processJacksonJsonTypeInfo(final BeanInfo beanInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor$$ExternalSyntheticLambda6
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectWriterBaseModule.WriterAnnotationProcessor.lambda$processJacksonJsonTypeInfo$3(annotation, beanInfo, (Method) obj);
                }
            });
        }

        static /* synthetic */ void lambda$processJacksonJsonTypeInfo$3(Annotation annotation, BeanInfo beanInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if ("property".equals(name)) {
                    String str = (String) invoke;
                    if (str.isEmpty()) {
                        return;
                    }
                    beanInfo.typeKey = str;
                    beanInfo.writerFeatures |= JSONWriter.Feature.WriteClassName.mask;
                }
            } catch (Throwable unused) {
            }
        }

        private void processJacksonJsonPropertyOrder(final BeanInfo beanInfo, final Annotation annotation) {
            Class<?> cls = annotation.getClass();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            BeanUtils.annotationMethods(cls, new Consumer() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor$$ExternalSyntheticLambda7
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectWriterBaseModule.WriterAnnotationProcessor.lambda$processJacksonJsonPropertyOrder$4(annotation, beanInfo, atomicBoolean, (Method) obj);
                }
            });
            if (beanInfo.orders == null || beanInfo.orders.length == 0) {
                beanInfo.alphabetic = atomicBoolean.get();
            }
        }

        static /* synthetic */ void lambda$processJacksonJsonPropertyOrder$4(Annotation annotation, BeanInfo beanInfo, AtomicBoolean atomicBoolean, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if ("value".equals(name)) {
                    String[] strArr = (String[]) invoke;
                    if (strArr.length != 0) {
                        beanInfo.orders = strArr;
                        return;
                    }
                    return;
                }
                if ("alphabetic".equals(name)) {
                    atomicBoolean.set(((Boolean) invoke).booleanValue());
                }
            } catch (Throwable unused) {
            }
        }

        private void processJacksonJsonSerialize(final FieldInfo fieldInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectWriterBaseModule.WriterAnnotationProcessor.this.m2820x4865c97d(annotation, fieldInfo, (Method) obj);
                }
            });
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* renamed from: lambda$processJacksonJsonSerialize$5$com-alibaba-fastjson2-writer-ObjectWriterBaseModule$WriterAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2820x4865c97d(Annotation annotation, FieldInfo fieldInfo, Method method) {
            Class<?> cls;
            Class<?> processUsing;
            Class<?> processUsing2;
            Class<?> processUsing3;
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                switch (name.hashCode()) {
                    case -407108981:
                        if (name.equals("contentAs") && (cls = (Class) invoke) != Void.class) {
                            fieldInfo.contentAs = cls;
                            break;
                        }
                        break;
                    case 111582340:
                        if (name.equals("using") && (processUsing = processUsing((Class) invoke)) != null) {
                            fieldInfo.writeUsing = processUsing;
                            break;
                        }
                        break;
                    case 491860325:
                        if (name.equals("keyUsing") && (processUsing2 = processUsing((Class) invoke)) != null) {
                            fieldInfo.keyUsing = processUsing2;
                            break;
                        }
                        break;
                    case 2034063763:
                        if (name.equals("valueUsing") && (processUsing3 = processUsing((Class) invoke)) != null) {
                            fieldInfo.valueUsing = processUsing3;
                            break;
                        }
                        break;
                }
            } catch (Throwable unused) {
            }
        }

        private void processJacksonJsonProperty(final FieldInfo fieldInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectWriterBaseModule.WriterAnnotationProcessor.lambda$processJacksonJsonProperty$6(annotation, fieldInfo, (Method) obj);
                }
            });
        }

        static /* synthetic */ void lambda$processJacksonJsonProperty$6(Annotation annotation, FieldInfo fieldInfo, Method method) {
            int intValue;
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                int hashCode = name.hashCode();
                if (hashCode == -1423461020) {
                    if (name.equals("access")) {
                        fieldInfo.ignore = "WRITE_ONLY".equals(((Enum) invoke).name());
                        return;
                    }
                    return;
                }
                if (hashCode == 100346066) {
                    if (!name.equals("index") || (intValue = ((Integer) invoke).intValue()) == -1) {
                        return;
                    }
                    fieldInfo.ordinal = intValue;
                    return;
                }
                if (hashCode == 111972721 && name.equals("value")) {
                    String str = (String) invoke;
                    if (str.isEmpty()) {
                        return;
                    }
                    if (fieldInfo.fieldName == null || fieldInfo.fieldName.isEmpty()) {
                        fieldInfo.fieldName = str;
                    }
                }
            } catch (Throwable unused) {
            }
        }

        private void processJacksonJsonIgnoreProperties(final BeanInfo beanInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectWriterBaseModule.WriterAnnotationProcessor.lambda$processJacksonJsonIgnoreProperties$7(annotation, beanInfo, (Method) obj);
                }
            });
        }

        static /* synthetic */ void lambda$processJacksonJsonIgnoreProperties$7(Annotation annotation, BeanInfo beanInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if ("value".equals(name)) {
                    String[] strArr = (String[]) invoke;
                    if (strArr.length != 0) {
                        beanInfo.ignores = strArr;
                    }
                }
            } catch (Throwable unused) {
            }
        }

        private void processJSONField1x(final FieldInfo fieldInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectWriterBaseModule.WriterAnnotationProcessor.this.m2818xed6a8b50(annotation, fieldInfo, (Method) obj);
                }
            });
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* renamed from: lambda$processJSONField1x$8$com-alibaba-fastjson2-writer-ObjectWriterBaseModule$WriterAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2818xed6a8b50(Annotation annotation, FieldInfo fieldInfo, Method method) {
            int intValue;
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                switch (name.hashCode()) {
                    case -1371565692:
                        if (name.equals("serializeUsing")) {
                            Class<?> cls = (Class) invoke;
                            if (ObjectWriter.class.isAssignableFrom(cls)) {
                                fieldInfo.writeUsing = cls;
                                break;
                            }
                        }
                        break;
                    case -1268779017:
                        if (name.equals("format")) {
                            loadJsonFieldFormat(fieldInfo, (String) invoke);
                            break;
                        }
                        break;
                    case -1206994319:
                        if (name.equals("ordinal") && (intValue = ((Integer) invoke).intValue()) != 0) {
                            fieldInfo.ordinal = intValue;
                            break;
                        }
                        break;
                    case -987658292:
                        if (name.equals("unwrapped") && ((Boolean) invoke).booleanValue()) {
                            fieldInfo.features |= FieldInfo.UNWRAPPED_MASK;
                            break;
                        }
                        break;
                    case -940893828:
                        if (name.equals("serialzeFeatures")) {
                            applyFeatures(fieldInfo, (Enum[]) invoke);
                            break;
                        }
                        break;
                    case -659125328:
                        if (name.equals("defaultValue")) {
                            String str = (String) invoke;
                            if (!str.isEmpty()) {
                                fieldInfo.defaultValue = str;
                                break;
                            }
                        }
                        break;
                    case -573479200:
                        if (name.equals("serialize") && !((Boolean) invoke).booleanValue()) {
                            fieldInfo.ignore = true;
                            break;
                        }
                        break;
                    case 3373707:
                        if (name.equals(HintConstants.AUTOFILL_HINT_NAME)) {
                            String str2 = (String) invoke;
                            if (!str2.isEmpty()) {
                                fieldInfo.fieldName = str2;
                                break;
                            }
                        }
                        break;
                    case 12396273:
                        if (name.equals("jsonDirect") && ((Boolean) invoke).booleanValue()) {
                            fieldInfo.features |= FieldInfo.RAW_VALUE_MASK;
                            break;
                        }
                        break;
                    case 102727412:
                        if (name.equals(AnnotatedPrivateKey.LABEL)) {
                            String str3 = (String) invoke;
                            if (!str3.isEmpty()) {
                                fieldInfo.label = str3;
                                break;
                            }
                        }
                        break;
                }
            } catch (Throwable unused) {
            }
        }

        private void applyFeatures(FieldInfo fieldInfo, Enum[] enumArr) {
            for (Enum r0 : enumArr) {
                String name = r0.name();
                name.hashCode();
                switch (name) {
                    case "DisableCircularReferenceDetect":
                        fieldInfo.features |= FieldInfo.DISABLE_REFERENCE_DETECT;
                        break;
                    case "WriteNullNumberAsZero":
                        fieldInfo.features |= JSONWriter.Feature.WriteNullNumberAsZero.mask;
                        break;
                    case "IgnoreErrorGetter":
                        fieldInfo.features |= JSONWriter.Feature.IgnoreErrorGetter.mask;
                        break;
                    case "UseISO8601DateFormat":
                        fieldInfo.format = "iso8601";
                        break;
                    case "WriteBigDecimalAsPlain":
                        fieldInfo.features |= JSONWriter.Feature.WriteBigDecimalAsPlain.mask;
                        break;
                    case "WriteEnumUsingToString":
                        fieldInfo.features |= JSONWriter.Feature.WriteEnumUsingToString.mask;
                        break;
                    case "BrowserCompatible":
                        fieldInfo.features |= JSONWriter.Feature.BrowserCompatible.mask;
                        break;
                    case "WriteNullStringAsEmpty":
                        fieldInfo.features |= JSONWriter.Feature.WriteNullStringAsEmpty.mask;
                        break;
                    case "NotWriteRootClassName":
                        fieldInfo.features |= JSONWriter.Feature.NotWriteRootClassName.mask;
                        break;
                    case "WriteNullListAsEmpty":
                        fieldInfo.features |= JSONWriter.Feature.WriteNullListAsEmpty.mask;
                        break;
                    case "WriteNonStringValueAsString":
                        fieldInfo.features |= JSONWriter.Feature.WriteNonStringValueAsString.mask;
                        break;
                    case "WriteNullBooleanAsFalse":
                        fieldInfo.features |= JSONWriter.Feature.WriteNullBooleanAsFalse.mask;
                        break;
                    case "WriteClassName":
                        fieldInfo.features |= JSONWriter.Feature.WriteClassName.mask;
                        break;
                    case "WriteMapNullValue":
                        fieldInfo.features |= JSONWriter.Feature.WriteNulls.mask;
                        break;
                }
            }
        }

        @Override // com.alibaba.fastjson2.modules.ObjectWriterAnnotationProcessor
        public void getFieldInfo(BeanInfo beanInfo, FieldInfo fieldInfo, Class cls, Method method) {
            Field field;
            Method method2;
            Class cls2 = ObjectWriterBaseModule.this.provider.mixInCache.get(cls);
            String name = method.getName();
            if ("getTargetSql".equals(name) && cls != null && cls.getName().startsWith("com.baomidou.mybatisplus.")) {
                fieldInfo.features |= JSONWriter.Feature.IgnoreErrorGetter.mask;
            }
            if (cls2 != null && cls2 != cls) {
                try {
                    method2 = cls2.getDeclaredMethod(name, method.getParameterTypes());
                } catch (Exception unused) {
                    method2 = null;
                }
                if (method2 != null) {
                    getFieldInfo(beanInfo, fieldInfo, cls2, method2);
                }
            }
            if (ObjectWriterBaseModule.this.provider.mixInCache.get(method.getReturnType()) != null) {
                fieldInfo.fieldClassMixIn = true;
            }
            if (JDKUtils.CLASS_TRANSIENT != null && method.getAnnotation(JDKUtils.CLASS_TRANSIENT) != null) {
                fieldInfo.ignore = true;
                fieldInfo.isTransient = true;
                if (!beanInfo.skipTransient) {
                    fieldInfo.skipTransient = false;
                    fieldInfo.ignore = false;
                }
            }
            if (cls != null) {
                Class superclass = cls.getSuperclass();
                Method method3 = BeanUtils.getMethod(superclass, method);
                boolean z = fieldInfo.ignore;
                if (method3 != null) {
                    getFieldInfo(beanInfo, fieldInfo, superclass, method3);
                    Field field2 = BeanUtils.getField(cls, method);
                    int modifiers = method3.getModifiers();
                    if (field2 != null && z != fieldInfo.ignore && !Modifier.isAbstract(modifiers) && !method3.equals(method)) {
                        fieldInfo.ignore = z;
                    }
                }
                for (Class<?> cls3 : cls.getInterfaces()) {
                    Method method4 = BeanUtils.getMethod(cls3, method);
                    if (superclass != null && method4 != null) {
                        getFieldInfo(beanInfo, fieldInfo, superclass, method4);
                    }
                }
            }
            fieldInfo.isPrivate = false;
            processAnnotations(fieldInfo, BeanUtils.getAnnotations(method));
            if (!cls.getName().startsWith("java.lang") && !BeanUtils.isRecord(cls) && (field = BeanUtils.getField(cls, method)) != null) {
                fieldInfo.features |= FieldInfo.FIELD_MASK;
                getFieldInfo(beanInfo, fieldInfo, cls, field);
            }
            if (!beanInfo.f14kotlin || beanInfo.creatorConstructor == null || beanInfo.createParameterNames == null) {
                return;
            }
            String str = BeanUtils.getterName(method, beanInfo.f14kotlin, null);
            for (int i = 0; i < beanInfo.createParameterNames.length; i++) {
                if (str.equals(beanInfo.createParameterNames[i])) {
                    Annotation[][] parameterAnnotations = beanInfo.creatorConstructor.getParameterAnnotations();
                    if (i < parameterAnnotations.length) {
                        processAnnotations(fieldInfo, parameterAnnotations[i]);
                        return;
                    }
                }
            }
        }

        private void processAnnotations(FieldInfo fieldInfo, Annotation[] annotationArr) {
            boolean isUseJacksonAnnotation;
            for (Annotation annotation : annotationArr) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                JSONField jSONField = (JSONField) BeanUtils.findAnnotation(annotation, JSONField.class);
                Objects.nonNull(jSONField);
                if (jSONField != null) {
                    loadFieldInfo(fieldInfo, jSONField);
                } else {
                    if (annotationType == JSONCompiler.class && ((JSONCompiler) annotation).value() == JSONCompiler.CompilerOption.LAMBDA) {
                        fieldInfo.features |= FieldInfo.JIT;
                    }
                    isUseJacksonAnnotation = JSONFactory.isUseJacksonAnnotation();
                    String name = annotationType.getName();
                    name.hashCode();
                    switch (name) {
                        case "com.fasterxml.jackson.annotation.JsonInclude":
                            if (isUseJacksonAnnotation) {
                                BeanUtils.processJacksonJsonInclude(fieldInfo, annotation);
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.databind.annotation.JsonSerialize":
                            if (isUseJacksonAnnotation) {
                                processJacksonJsonSerialize(fieldInfo, annotation);
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonFormat":
                            if (isUseJacksonAnnotation) {
                                BeanUtils.processJacksonJsonFormat(fieldInfo, annotation);
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonIgnore":
                            if (isUseJacksonAnnotation) {
                                BeanUtils.processJacksonJsonIgnore(fieldInfo, annotation);
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonValue":
                            if (isUseJacksonAnnotation) {
                                fieldInfo.features |= FieldInfo.VALUE_MASK;
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonAnyGetter":
                            if (isUseJacksonAnnotation) {
                                fieldInfo.features |= FieldInfo.UNWRAPPED_MASK;
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonProperty":
                            if (isUseJacksonAnnotation) {
                                processJacksonJsonProperty(fieldInfo, annotation);
                                break;
                            } else {
                                break;
                            }
                        case "java.beans.Transient":
                            if (fieldInfo.skipTransient) {
                                fieldInfo.ignore = true;
                            }
                            fieldInfo.isTransient = true;
                            break;
                        case "com.alibaba.fastjson.annotation.JSONField":
                            processJSONField1x(fieldInfo, annotation);
                            break;
                        case "com.fasterxml.jackson.annotation.JsonRawValue":
                            if (isUseJacksonAnnotation) {
                                fieldInfo.features |= FieldInfo.RAW_VALUE_MASK;
                                break;
                            } else {
                                break;
                            }
                        case "com.fasterxml.jackson.annotation.JsonUnwrapped":
                            if (isUseJacksonAnnotation) {
                                BeanUtils.processJacksonJsonUnwrapped(fieldInfo, annotation);
                                break;
                            } else {
                                break;
                            }
                    }
                }
            }
        }

        private void loadFieldInfo(FieldInfo fieldInfo, JSONField jSONField) {
            String name = jSONField.name();
            if (!name.isEmpty()) {
                fieldInfo.fieldName = name;
            }
            String defaultValue = jSONField.defaultValue();
            if (!defaultValue.isEmpty()) {
                fieldInfo.defaultValue = defaultValue;
            }
            loadJsonFieldFormat(fieldInfo, jSONField.format());
            String label = jSONField.label();
            if (!label.isEmpty()) {
                fieldInfo.label = label;
            }
            String locale = jSONField.locale();
            if (!locale.isEmpty()) {
                String[] split = locale.split("_");
                if (split.length == 2) {
                    fieldInfo.locale = new Locale(split[0], split[1]);
                }
            }
            boolean serialize = jSONField.serialize();
            boolean z = !serialize;
            if (!fieldInfo.ignore) {
                fieldInfo.ignore = z;
            }
            if (!jSONField.skipTransient()) {
                fieldInfo.skipTransient = false;
                if (fieldInfo.isTransient && !fieldInfo.isPrivate) {
                    fieldInfo.ignore = false;
                }
            }
            if (jSONField.unwrapped()) {
                fieldInfo.features |= FieldInfo.UNWRAPPED_MASK;
            }
            for (JSONWriter.Feature feature : jSONField.serializeFeatures()) {
                fieldInfo.features |= feature.mask;
                if (fieldInfo.ignore && !fieldInfo.isTransient && serialize && feature == JSONWriter.Feature.FieldBased) {
                    fieldInfo.ignore = false;
                }
            }
            int ordinal = jSONField.ordinal();
            if (ordinal != 0) {
                fieldInfo.ordinal = ordinal;
            }
            if (jSONField.value()) {
                fieldInfo.features |= FieldInfo.VALUE_MASK;
            }
            if (jSONField.jsonDirect()) {
                fieldInfo.features |= FieldInfo.RAW_VALUE_MASK;
            }
            Class<?> serializeUsing = jSONField.serializeUsing();
            if (ObjectWriter.class.isAssignableFrom(serializeUsing)) {
                fieldInfo.writeUsing = serializeUsing;
            }
            Class<?> contentAs = jSONField.contentAs();
            if (contentAs != Void.class) {
                fieldInfo.contentAs = contentAs;
            }
        }

        private void loadJsonFieldFormat(FieldInfo fieldInfo, String str) {
            if (str.isEmpty()) {
                return;
            }
            String trim = str.trim();
            if (trim.indexOf(84) != -1 && !trim.contains("'T'")) {
                trim = trim.replace(ExifInterface.GPS_DIRECTION_TRUE, "'T'");
            }
            if (trim.isEmpty()) {
                return;
            }
            fieldInfo.format = trim;
        }
    }

    ObjectWriter getExternalObjectWriter(String str, Class cls) {
        str.hashCode();
        switch (str) {
            case "org.joda.time.LocalDate":
                return JodaSupport.createLocalDateWriter(cls, null);
            case "org.joda.time.chrono.GregorianChronology":
                return JodaSupport.createGregorianChronologyWriter(cls);
            case "java.sql.Time":
                return JdbcSupport.createTimeWriter(null);
            case "org.joda.time.chrono.ISOChronology":
                return JodaSupport.createISOChronologyWriter(cls);
            case "org.joda.time.DateTime":
                return new ObjectWriterImplZonedDateTime(null, null, new JodaSupport.DateTime2ZDT());
            case "java.sql.Timestamp":
                return JdbcSupport.createTimestampWriter(cls, null);
            case "org.joda.time.LocalDateTime":
                return JodaSupport.createLocalDateTimeWriter(cls, null);
            default:
                if (JdbcSupport.isClob(cls)) {
                    return JdbcSupport.createClobWriter(cls);
                }
                return null;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.alibaba.fastjson2.modules.ObjectWriterModule
    public ObjectWriter getObjectWriter(Type type, Class cls) {
        Class cls2;
        ObjectWriter createEnumWriter;
        Class<?> mapping;
        Type type2 = type;
        if (type2 == String.class) {
            return ObjectWriterImplString.INSTANCE;
        }
        if (cls == null) {
            if (type2 instanceof Class) {
                mapping = (Class) type2;
            } else {
                mapping = TypeUtils.getMapping(type2);
            }
            cls2 = mapping;
        } else {
            cls2 = cls;
        }
        String name = cls2.getName();
        ObjectWriter externalObjectWriter = getExternalObjectWriter(name, cls2);
        if (externalObjectWriter != null) {
            return externalObjectWriter;
        }
        name.hashCode();
        char c = 65535;
        switch (name.hashCode()) {
            case -2088293497:
                if (name.equals("java.awt.Color")) {
                    c = 0;
                    break;
                }
                break;
            case -2037224663:
                if (name.equals("java.util.regex.Pattern")) {
                    c = 1;
                    break;
                }
                break;
            case -1786540538:
                if (name.equals("com.google.common.collect.AbstractMapBasedMultimap$WrappedSet")) {
                    c = 2;
                    break;
                }
                break;
            case -1757049669:
                if (name.equals("com.carrotsearch.hppc.LongHashSet")) {
                    c = 3;
                    break;
                }
                break;
            case -1682705914:
                if (name.equals("gnu.trove.set.hash.TShortHashSet")) {
                    c = 4;
                    break;
                }
                break;
            case -1670613343:
                if (name.equals("com.carrotsearch.hppc.CharHashSet")) {
                    c = 5;
                    break;
                }
                break;
            case -1598338761:
                if (name.equals("java.nio.DirectByteBuffer")) {
                    c = 6;
                    break;
                }
                break;
            case -1369392210:
                if (name.equals("com.fasterxml.jackson.databind.node.ObjectNode")) {
                    c = 7;
                    break;
                }
                break;
            case -942806480:
                if (name.equals("org.javamoney.moneta.internal.JDKCurrencyAdapter")) {
                    c = '\b';
                    break;
                }
                break;
            case -864935548:
                if (name.equals("com.carrotsearch.hppc.CharArrayList")) {
                    c = '\t';
                    break;
                }
                break;
            case -848095899:
                if (name.equals("com.carrotsearch.hppc.IntArrayList")) {
                    c = '\n';
                    break;
                }
                break;
            case -808573634:
                if (name.equals("gnu.trove.list.array.TLongArrayList")) {
                    c = 11;
                    break;
                }
                break;
            case -702521390:
                if (name.equals("com.carrotsearch.hppc.BitSet")) {
                    c = '\f';
                    break;
                }
                break;
            case -561799942:
                if (name.equals("java.nio.HeapByteBuffer")) {
                    c = '\r';
                    break;
                }
                break;
            case -448666600:
                if (name.equals("gnu.trove.list.array.TShortArrayList")) {
                    c = 14;
                    break;
                }
                break;
            case -342082893:
                if (name.equals("gnu.trove.set.hash.TIntHashSet")) {
                    c = 15;
                    break;
                }
                break;
            case -314457643:
                if (name.equals("org.apache.commons.lang3.tuple.MutablePair")) {
                    c = 16;
                    break;
                }
                break;
            case -240096200:
                if (name.equals("com.carrotsearch.hppc.ShortArrayList")) {
                    c = 17;
                    break;
                }
                break;
            case -172623342:
                if (name.equals("org.javamoney.moneta.Money")) {
                    c = 18;
                    break;
                }
                break;
            case -137241147:
                if (name.equals("org.apache.commons.lang3.tuple.Pair")) {
                    c = 19;
                    break;
                }
                break;
            case -127813975:
                if (name.equals("com.carrotsearch.hppc.DoubleArrayList")) {
                    c = 20;
                    break;
                }
                break;
            case 100244498:
                if (name.equals("com.carrotsearch.hppc.ByteArrayList")) {
                    c = 21;
                    break;
                }
                break;
            case 217956074:
                if (name.equals("gnu.trove.set.hash.TLongHashSet")) {
                    c = 22;
                    break;
                }
                break;
            case 255703211:
                if (name.equals("net.sf.json.JSONNull")) {
                    c = 23;
                    break;
                }
                break;
            case 348125975:
                if (name.equals("org.javamoney.moneta.spi.DefaultNumberValue")) {
                    c = 24;
                    break;
                }
                break;
            case 444521103:
                if (name.equals("java.net.Inet6Address")) {
                    c = 25;
                    break;
                }
                break;
            case 574530702:
                if (name.equals("com.fasterxml.jackson.databind.node.ArrayNode")) {
                    c = JSONLexer.EOI;
                    break;
                }
                break;
            case 652357028:
                if (name.equals("gnu.trove.list.array.TCharArrayList")) {
                    c = 27;
                    break;
                }
                break;
            case 924843249:
                if (name.equals("org.apache.commons.lang3.tuple.ImmutablePair")) {
                    c = 28;
                    break;
                }
                break;
            case 1138418232:
                if (name.equals("gnu.trove.list.array.TFloatArrayList")) {
                    c = 29;
                    break;
                }
                break;
            case 1195384194:
                if (name.equals("gnu.trove.stack.array.TByteArrayStack")) {
                    c = 30;
                    break;
                }
                break;
            case 1253867729:
                if (name.equals("java.net.Inet4Address")) {
                    c = 31;
                    break;
                }
                break;
            case 1346988632:
                if (name.equals("com.carrotsearch.hppc.FloatArrayList")) {
                    c = ' ';
                    break;
                }
                break;
            case 1395322562:
                if (name.equals("com.carrotsearch.hppc.IntHashSet")) {
                    c = '!';
                    break;
                }
                break;
            case 1527725683:
                if (name.equals("com.google.common.collect.AbstractMapBasedMultimap$RandomAccessWrappedList")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 1539653772:
                if (name.equals("java.text.SimpleDateFormat")) {
                    c = '#';
                    break;
                }
                break;
            case 1556153669:
                if (name.equals("gnu.trove.list.array.TIntArrayList")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case 1585284048:
                if (name.equals("java.net.InetSocketAddress")) {
                    c = '%';
                    break;
                }
                break;
            case 1617537074:
                if (name.equals("gnu.trove.list.array.TByteArrayList")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 1643140783:
                if (name.equals("org.bson.types.Decimal128")) {
                    c = '\'';
                    break;
                }
                break;
            case 1891987166:
                if (name.equals("gnu.trove.set.hash.TByteHashSet")) {
                    c = '(';
                    break;
                }
                break;
            case 1969101086:
                if (name.equals("com.carrotsearch.hppc.LongArrayList")) {
                    c = ')';
                    break;
                }
                break;
            case 1996438217:
                if (name.equals("gnu.trove.list.array.TDoubleArrayList")) {
                    c = '*';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                try {
                    return new ObjectWriter4(cls2, null, null, 0L, Arrays.asList(ObjectWriters.fieldWriter("r", cls2.getMethod("getRed", new Class[0])), ObjectWriters.fieldWriter("g", cls2.getMethod("getGreen", new Class[0])), ObjectWriters.fieldWriter("b", cls2.getMethod("getBlue", new Class[0])), ObjectWriters.fieldWriter("alpha", cls2.getMethod("getAlpha", new Class[0]))));
                } catch (NoSuchMethodException unused) {
                    break;
                }
            case 1:
            case 23:
            case 25:
            case 26:
            case 31:
            case '#':
            case '%':
                return ObjectWriterMisc.INSTANCE;
            case 2:
            case '\"':
                return null;
            case 3:
            case 4:
            case 5:
            case '\t':
            case '\n':
            case 11:
            case '\f':
            case 14:
            case 15:
            case 17:
            case 20:
            case 21:
            case 22:
            case 27:
            case 29:
            case 30:
            case ' ':
            case '!':
            case '$':
            case '&':
            case '\'':
            case '(':
            case ')':
            case '*':
                return LambdaMiscCodec.getObjectWriter(type2, cls2);
            case 6:
            case '\r':
                return new ObjectWriterImplInt8ValueArray(new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        byte[] array;
                        array = ((ByteBuffer) obj).array();
                        return array;
                    }
                });
            case 7:
                return ObjectWriterImplToString.DIRECT;
            case '\b':
                return ObjectWriterImplToString.INSTANCE;
            case 16:
            case 19:
            case 28:
                return new ApacheLang3Support.PairWriter(cls2);
            case 18:
                return MoneySupport.createMonetaryAmountWriter();
            case 24:
                return MoneySupport.createNumberValueWriter();
        }
        if (type2 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type2;
            Type rawType = parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (rawType == List.class || rawType == ArrayList.class) {
                if (actualTypeArguments.length == 1 && actualTypeArguments[0] == String.class) {
                    return ObjectWriterImplListStr.INSTANCE;
                }
                type2 = rawType;
            }
            if (Map.class.isAssignableFrom(cls2)) {
                return ObjectWriterImplMap.of(type2, cls2);
            }
            if (cls2 == Optional.class && actualTypeArguments.length == 1) {
                return new ObjectWriterImplOptional(actualTypeArguments[0], null, null);
            }
        }
        if (type2 == LinkedList.class) {
            return ObjectWriterImplList.INSTANCE;
        }
        if (type2 == ArrayList.class || type2 == List.class || List.class.isAssignableFrom(cls2)) {
            return ObjectWriterImplList.INSTANCE;
        }
        if (Collection.class.isAssignableFrom(cls2)) {
            return ObjectWriterImplCollection.INSTANCE;
        }
        if (BeanUtils.isExtendedMap(cls2)) {
            return null;
        }
        if (Map.class.isAssignableFrom(cls2)) {
            return ObjectWriterImplMap.of(cls2);
        }
        if (Map.Entry.class.isAssignableFrom(cls2)) {
            return ObjectWriterImplMapEntry.INSTANCE;
        }
        if (Path.class.isAssignableFrom(cls2)) {
            return ObjectWriterImplToString.INSTANCE;
        }
        if (type2 == Integer.class) {
            return ObjectWriterImplInt32.INSTANCE;
        }
        if (type2 == AtomicInteger.class) {
            return ObjectWriterImplAtomicInteger.INSTANCE;
        }
        if (type2 == Byte.class) {
            return ObjectWriterImplInt8.INSTANCE;
        }
        if (type2 == Short.class) {
            return ObjectWriterImplInt16.INSTANCE;
        }
        if (type2 == Long.class) {
            return ObjectWriterImplInt64.INSTANCE;
        }
        if (type2 == AtomicLong.class) {
            return ObjectWriterImplAtomicLong.INSTANCE;
        }
        if (type2 == AtomicReference.class) {
            return ObjectWriterImplAtomicReference.INSTANCE;
        }
        if (type2 == Float.class) {
            return ObjectWriterImplFloat.INSTANCE;
        }
        if (type2 == Double.class) {
            return ObjectWriterImplDouble.INSTANCE;
        }
        if (type2 == BigInteger.class) {
            return ObjectWriterBigInteger.INSTANCE;
        }
        if (type2 == BigDecimal.class) {
            return ObjectWriterImplBigDecimal.INSTANCE;
        }
        if (type2 == BitSet.class) {
            return ObjectWriterImplBitSet.INSTANCE;
        }
        if (type2 == OptionalInt.class) {
            return ObjectWriterImplOptionalInt.INSTANCE;
        }
        if (type2 == OptionalLong.class) {
            return ObjectWriterImplOptionalLong.INSTANCE;
        }
        if (type2 == OptionalDouble.class) {
            return ObjectWriterImplOptionalDouble.INSTANCE;
        }
        if (type2 == Optional.class) {
            return ObjectWriterImplOptional.INSTANCE;
        }
        if (type2 == Boolean.class) {
            return ObjectWriterImplBoolean.INSTANCE;
        }
        if (type2 == AtomicBoolean.class) {
            return ObjectWriterImplAtomicBoolean.INSTANCE;
        }
        if (type2 == AtomicIntegerArray.class) {
            return ObjectWriterImplAtomicIntegerArray.INSTANCE;
        }
        if (type2 == AtomicLongArray.class) {
            return ObjectWriterImplAtomicLongArray.INSTANCE;
        }
        if (type2 == Character.class) {
            return ObjectWriterImplCharacter.INSTANCE;
        }
        if (type2 instanceof Class) {
            Class cls3 = (Class) type2;
            if (TimeUnit.class.isAssignableFrom(cls3)) {
                return new ObjectWriterImplEnum(null, TimeUnit.class, null, null, 0L);
            }
            if (Enum.class.isAssignableFrom(cls3) && (createEnumWriter = createEnumWriter(cls3)) != null) {
                return createEnumWriter;
            }
            if (JSONPath.class.isAssignableFrom(cls3)) {
                return ObjectWriterImplToString.INSTANCE;
            }
            if (cls3 == boolean[].class) {
                return ObjectWriterImplBoolValueArray.INSTANCE;
            }
            if (cls3 == char[].class) {
                return ObjectWriterImplCharValueArray.INSTANCE;
            }
            if (cls3 == StringBuffer.class || cls3 == StringBuilder.class) {
                return ObjectWriterImplToString.INSTANCE;
            }
            if (cls3 == byte[].class) {
                return ObjectWriterImplInt8ValueArray.INSTANCE;
            }
            if (cls3 == short[].class) {
                return ObjectWriterImplInt16ValueArray.INSTANCE;
            }
            if (cls3 == int[].class) {
                return ObjectWriterImplInt32ValueArray.INSTANCE;
            }
            if (cls3 == long[].class) {
                return ObjectWriterImplInt64ValueArray.INSTANCE;
            }
            if (cls3 == float[].class) {
                return ObjectWriterImplFloatValueArray.INSTANCE;
            }
            if (cls3 == double[].class) {
                return ObjectWriterImplDoubleValueArray.INSTANCE;
            }
            if (cls3 == Byte[].class) {
                return ObjectWriterImplInt8Array.INSTANCE;
            }
            if (cls3 == Integer[].class) {
                return ObjectWriterImplInt32Array.INSTANCE;
            }
            if (cls3 == Long[].class) {
                return ObjectWriterImplInt64Array.INSTANCE;
            }
            if (String[].class == cls3) {
                return ObjectWriterImplStringArray.INSTANCE;
            }
            if (BigDecimal[].class == cls3) {
                return ObjectWriterImpDecimalArray.INSTANCE;
            }
            if (Object[].class.isAssignableFrom(cls3)) {
                if (cls3 == Object[].class) {
                    return ObjectWriterArray.INSTANCE;
                }
                Class<?> componentType = cls3.getComponentType();
                if (Modifier.isFinal(componentType.getModifiers())) {
                    return new ObjectWriterArrayFinal(componentType, null);
                }
                return new ObjectWriterArray(componentType);
            }
            if (cls3 == UUID.class) {
                return ObjectWriterImplUUID.INSTANCE;
            }
            if (cls3 == Locale.class) {
                return ObjectWriterImplLocale.INSTANCE;
            }
            if (cls3 == Currency.class) {
                return ObjectWriterImplCurrency.INSTANCE;
            }
            if (TimeZone.class.isAssignableFrom(cls3)) {
                return ObjectWriterImplTimeZone.INSTANCE;
            }
            if (JSONPObject.class.isAssignableFrom(cls3)) {
                return new ObjectWriterImplJSONP();
            }
            if (cls3 == URI.class || cls3 == URL.class || cls3 == File.class || ZoneId.class.isAssignableFrom(cls3) || Charset.class.isAssignableFrom(cls3)) {
                return ObjectWriterImplToString.INSTANCE;
            }
            ObjectWriter externalObjectWriter2 = getExternalObjectWriter(cls3.getName(), cls3);
            if (externalObjectWriter2 != null) {
                return externalObjectWriter2;
            }
            BeanInfo createBeanInfo = this.provider.createBeanInfo();
            Class mixIn = this.provider.getMixIn(cls3);
            if (mixIn != null) {
                this.annotationProcessor.getBeanInfo(createBeanInfo, mixIn);
            }
            if (Date.class.isAssignableFrom(cls3)) {
                if (createBeanInfo.format != null || createBeanInfo.locale != null) {
                    return new ObjectWriterImplDate(createBeanInfo.format, createBeanInfo.locale);
                }
                return ObjectWriterImplDate.INSTANCE;
            }
            if (Calendar.class.isAssignableFrom(cls3)) {
                if (createBeanInfo.format != null || createBeanInfo.locale != null) {
                    return new ObjectWriterImplCalendar(createBeanInfo.format, createBeanInfo.locale);
                }
                return ObjectWriterImplCalendar.INSTANCE;
            }
            if (ZonedDateTime.class == cls3) {
                if (createBeanInfo.format != null || createBeanInfo.locale != null) {
                    return new ObjectWriterImplZonedDateTime(createBeanInfo.format, createBeanInfo.locale);
                }
                return ObjectWriterImplZonedDateTime.INSTANCE;
            }
            if (OffsetDateTime.class == cls3) {
                return ObjectWriterImplOffsetDateTime.of(createBeanInfo.format, createBeanInfo.locale);
            }
            if (LocalDateTime.class == cls3) {
                if (createBeanInfo.format != null || createBeanInfo.locale != null) {
                    return new ObjectWriterImplLocalDateTime(createBeanInfo.format, createBeanInfo.locale);
                }
                return ObjectWriterImplLocalDateTime.INSTANCE;
            }
            if (LocalDate.class == cls3) {
                return ObjectWriterImplLocalDate.of(createBeanInfo.format, createBeanInfo.locale);
            }
            if (LocalTime.class == cls3) {
                if (createBeanInfo.format != null || createBeanInfo.locale != null) {
                    return new ObjectWriterImplLocalTime(createBeanInfo.format, createBeanInfo.locale);
                }
                return ObjectWriterImplLocalTime.INSTANCE;
            }
            if (OffsetTime.class == cls3) {
                if (createBeanInfo.format != null || createBeanInfo.locale != null) {
                    return new ObjectWriterImplOffsetTime(createBeanInfo.format, createBeanInfo.locale);
                }
                return ObjectWriterImplOffsetTime.INSTANCE;
            }
            if (Instant.class == cls3) {
                if (createBeanInfo.format != null || createBeanInfo.locale != null) {
                    return new ObjectWriterImplInstant(createBeanInfo.format, createBeanInfo.locale);
                }
                return ObjectWriterImplInstant.INSTANCE;
            }
            if (Duration.class == cls3 || Period.class == cls3) {
                return ObjectWriterImplToString.INSTANCE;
            }
            if (StackTraceElement.class == cls3) {
                if (STACK_TRACE_ELEMENT_WRITER == null) {
                    ObjectWriterCreator creator = this.provider.getCreator();
                    STACK_TRACE_ELEMENT_WRITER = new ObjectWriterAdapter(StackTraceElement.class, null, null, 0L, Arrays.asList(creator.createFieldWriter("fileName", String.class, BeanUtils.getDeclaredField(StackTraceElement.class, "fileName"), BeanUtils.getMethod(StackTraceElement.class, "getFileName"), new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda7
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            String fileName;
                            fileName = ((StackTraceElement) obj).getFileName();
                            return fileName;
                        }
                    }), creator.createFieldWriter("lineNumber", BeanUtils.getDeclaredField(StackTraceElement.class, "lineNumber"), BeanUtils.getMethod(StackTraceElement.class, "getLineNumber"), new ToIntFunction() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda8
                        @Override // java.util.function.ToIntFunction
                        public final int applyAsInt(Object obj) {
                            int lineNumber;
                            lineNumber = ((StackTraceElement) obj).getLineNumber();
                            return lineNumber;
                        }
                    }), creator.createFieldWriter("className", String.class, BeanUtils.getDeclaredField(StackTraceElement.class, "declaringClass"), BeanUtils.getMethod(StackTraceElement.class, "getClassName"), new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda9
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            String className;
                            className = ((StackTraceElement) obj).getClassName();
                            return className;
                        }
                    }), creator.createFieldWriter("methodName", String.class, BeanUtils.getDeclaredField(StackTraceElement.class, "methodName"), BeanUtils.getMethod(StackTraceElement.class, "getMethodName"), new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda10
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            String methodName;
                            methodName = ((StackTraceElement) obj).getMethodName();
                            return methodName;
                        }
                    })));
                }
                return STACK_TRACE_ELEMENT_WRITER;
            }
            if (Class.class == cls3) {
                return ObjectWriterImplClass.INSTANCE;
            }
            if (Method.class == cls3) {
                return new ObjectWriterAdapter(Method.class, null, null, 0L, Arrays.asList(ObjectWriters.fieldWriter("declaringClass", Class.class, new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda11
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Class declaringClass;
                        declaringClass = ((Method) obj).getDeclaringClass();
                        return declaringClass;
                    }
                }), ObjectWriters.fieldWriter(HintConstants.AUTOFILL_HINT_NAME, String.class, new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda12
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        String name2;
                        name2 = ((Method) obj).getName();
                        return name2;
                    }
                }), ObjectWriters.fieldWriter("parameterTypes", Class[].class, new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Class[] parameterTypes;
                        parameterTypes = ((Method) obj).getParameterTypes();
                        return parameterTypes;
                    }
                })));
            }
            if (Field.class == cls3) {
                return new ObjectWriterAdapter(Method.class, null, null, 0L, Arrays.asList(ObjectWriters.fieldWriter("declaringClass", Class.class, new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Class declaringClass;
                        declaringClass = ((Field) obj).getDeclaringClass();
                        return declaringClass;
                    }
                }), ObjectWriters.fieldWriter(HintConstants.AUTOFILL_HINT_NAME, String.class, new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        String name2;
                        name2 = ((Field) obj).getName();
                        return name2;
                    }
                })));
            }
            if (ParameterizedType.class.isAssignableFrom(cls3)) {
                return ObjectWriters.objectWriter(ParameterizedType.class, ObjectWriters.fieldWriter("actualTypeArguments", Type[].class, new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda4
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((ParameterizedType) obj).getActualTypeArguments();
                    }
                }), ObjectWriters.fieldWriter("ownerType", Type.class, new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda5
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((ParameterizedType) obj).getOwnerType();
                    }
                }), ObjectWriters.fieldWriter("rawType", Type.class, new Function() { // from class: com.alibaba.fastjson2.writer.ObjectWriterBaseModule$$ExternalSyntheticLambda6
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((ParameterizedType) obj).getRawType();
                    }
                }));
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0055 A[LOOP:0: B:18:0x0052->B:20:0x0055, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.alibaba.fastjson2.writer.ObjectWriter createEnumWriter(java.lang.Class r10) {
        /*
            r9 = this;
            boolean r0 = r10.isEnum()
            if (r0 != 0) goto L12
            java.lang.Class r0 = r10.getSuperclass()
            boolean r1 = r0.isEnum()
            if (r1 == 0) goto L12
            r4 = r0
            goto L13
        L12:
            r4 = r10
        L13:
            com.alibaba.fastjson2.writer.ObjectWriterProvider r10 = r9.provider
            java.lang.reflect.Member r10 = com.alibaba.fastjson2.util.BeanUtils.getEnumValueField(r4, r10)
            r0 = 0
            if (r10 != 0) goto L47
            com.alibaba.fastjson2.writer.ObjectWriterProvider r1 = r9.provider
            java.util.concurrent.ConcurrentMap<java.lang.Class, java.lang.Class> r1 = r1.mixInCache
            java.lang.Object r1 = r1.get(r4)
            java.lang.Class r1 = (java.lang.Class) r1
            com.alibaba.fastjson2.writer.ObjectWriterProvider r2 = r9.provider
            java.lang.reflect.Member r1 = com.alibaba.fastjson2.util.BeanUtils.getEnumValueField(r1, r2)
            boolean r2 = r1 instanceof java.lang.reflect.Field
            if (r2 == 0) goto L39
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L47
            java.lang.reflect.Field r10 = r4.getField(r1)     // Catch: java.lang.Throwable -> L47
            goto L47
        L39:
            boolean r2 = r1 instanceof java.lang.reflect.Method
            if (r2 == 0) goto L47
            java.lang.String r1 = r1.getName()
            java.lang.Class[] r2 = new java.lang.Class[r0]
            java.lang.reflect.Method r10 = r4.getMethod(r1, r2)
        L47:
            r5 = r10
            com.alibaba.fastjson2.writer.ObjectWriterProvider r10 = r9.provider
            com.alibaba.fastjson2.codec.BeanInfo r10 = r10.createBeanInfo()
            java.lang.Class[] r1 = r4.getInterfaces()
        L52:
            int r2 = r1.length
            if (r0 >= r2) goto L5f
            com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor r2 = r9.annotationProcessor
            r3 = r1[r0]
            r2.getBeanInfo(r10, r3)
            int r0 = r0 + 1
            goto L52
        L5f:
            com.alibaba.fastjson2.writer.ObjectWriterBaseModule$WriterAnnotationProcessor r0 = r9.annotationProcessor
            r0.getBeanInfo(r10, r4)
            boolean r10 = r10.writeEnumAsJavaBean
            if (r10 == 0) goto L6a
            r10 = 0
            return r10
        L6a:
            java.lang.String[] r6 = com.alibaba.fastjson2.util.BeanUtils.getEnumAnnotationNames(r4)
            com.alibaba.fastjson2.writer.ObjectWriterImplEnum r2 = new com.alibaba.fastjson2.writer.ObjectWriterImplEnum
            r3 = 0
            r7 = 0
            r2.<init>(r3, r4, r5, r6, r7)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.writer.ObjectWriterBaseModule.createEnumWriter(java.lang.Class):com.alibaba.fastjson2.writer.ObjectWriter");
    }

    static class VoidObjectWriter implements ObjectWriter {
        public static final VoidObjectWriter INSTANCE = new VoidObjectWriter();

        @Override // com.alibaba.fastjson2.writer.ObjectWriter
        public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        }

        VoidObjectWriter() {
        }
    }
}
