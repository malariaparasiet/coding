package com.alibaba.fastjson2.reader;

import androidx.autofill.HintConstants;
import androidx.camera.video.AudioStats;
import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONPObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.annotation.JSONBuilder;
import com.alibaba.fastjson2.annotation.JSONCompiler;
import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.annotation.JSONType;
import com.alibaba.fastjson2.codec.BeanInfo;
import com.alibaba.fastjson2.codec.FieldInfo;
import com.alibaba.fastjson2.function.impl.StringToAny;
import com.alibaba.fastjson2.function.impl.ToBigDecimal;
import com.alibaba.fastjson2.function.impl.ToBigInteger;
import com.alibaba.fastjson2.function.impl.ToBoolean;
import com.alibaba.fastjson2.function.impl.ToByte;
import com.alibaba.fastjson2.function.impl.ToDouble;
import com.alibaba.fastjson2.function.impl.ToFloat;
import com.alibaba.fastjson2.function.impl.ToInteger;
import com.alibaba.fastjson2.function.impl.ToLong;
import com.alibaba.fastjson2.function.impl.ToNumber;
import com.alibaba.fastjson2.function.impl.ToShort;
import com.alibaba.fastjson2.function.impl.ToString;
import com.alibaba.fastjson2.modules.ObjectReaderAnnotationProcessor;
import com.alibaba.fastjson2.modules.ObjectReaderModule;
import com.alibaba.fastjson2.reader.ObjectReaderBaseModule;
import com.alibaba.fastjson2.support.LambdaMiscCodec;
import com.alibaba.fastjson2.support.money.MoneySupport;
import com.alibaba.fastjson2.util.ApacheLang3Support;
import com.alibaba.fastjson2.util.BeanUtils;
import com.alibaba.fastjson2.util.GuavaSupport;
import com.alibaba.fastjson2.util.JdbcSupport;
import com.alibaba.fastjson2.util.JodaSupport;
import com.alibaba.fastjson2.util.MapMultiValueType;
import com.alibaba.fastjson2.util.MultiType;
import com.alibaba.fastjson2.util.ParameterizedTypeImpl;
import com.alibaba.fastjson2.util.TypeUtils;
import java.io.Closeable;
import java.io.File;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSequentialList;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

/* loaded from: classes2.dex */
public class ObjectReaderBaseModule implements ObjectReaderModule {
    static Method METHOD_getPermittedSubclasses;
    final ReaderAnnotationProcessor annotationProcessor = new ReaderAnnotationProcessor();
    final ObjectReaderProvider provider;

    public static /* synthetic */ File $r8$lambda$3UDCRuryPMpPdeZ_mA8WA5301tk(String str) {
        return new File(str);
    }

    public static /* synthetic */ AtomicIntegerArray $r8$lambda$3qZHSL4C1HvTalVoO96JJ_FVv6I(int[] iArr) {
        return new AtomicIntegerArray(iArr);
    }

    public static /* synthetic */ AtomicLong $r8$lambda$8ZqB1iVOXrPrsVyf72if6iEU5hw(long j) {
        return new AtomicLong(j);
    }

    public static /* synthetic */ AtomicLongArray $r8$lambda$U0lGPadskxz3pdM5U9NMWRlHrRE(long[] jArr) {
        return new AtomicLongArray(jArr);
    }

    public static /* synthetic */ SimpleDateFormat $r8$lambda$b3Lmh_nz6Ow94jVYodnZkoZlIXY(String str) {
        return new SimpleDateFormat(str);
    }

    public static /* synthetic */ AtomicInteger $r8$lambda$fgjolKahIJ8DIhpC69R6doXRWuY(int i) {
        return new AtomicInteger(i);
    }

    /* renamed from: $r8$lambda$p90uMnuBXujNSd9h8icGqEE8-mU, reason: not valid java name */
    public static /* synthetic */ AtomicBoolean m2787$r8$lambda$p90uMnuBXujNSd9h8icGqEE8mU(boolean z) {
        return new AtomicBoolean(z);
    }

    static /* synthetic */ Object lambda$init$0(Object obj) {
        return obj;
    }

    static /* synthetic */ Object lambda$init$1(Object obj) {
        return obj;
    }

    public ObjectReaderBaseModule(ObjectReaderProvider objectReaderProvider) {
        this.provider = objectReaderProvider;
    }

    @Override // com.alibaba.fastjson2.modules.ObjectReaderModule
    public ObjectReaderProvider getProvider() {
        return this.provider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v4, types: [int] */
    @Override // com.alibaba.fastjson2.modules.ObjectReaderModule
    public void init(ObjectReaderProvider objectReaderProvider) {
        objectReaderProvider.registerTypeConvert(Character.class, Character.TYPE, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda19
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ObjectReaderBaseModule.lambda$init$0(obj);
            }
        });
        boolean z = false;
        Class[] clsArr = {Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Number.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, AtomicInteger.class, AtomicLong.class};
        ToBoolean toBoolean = new ToBoolean(null);
        for (int i = 0; i < 12; i++) {
            objectReaderProvider.registerTypeConvert(clsArr[i], Boolean.class, toBoolean);
        }
        ToBoolean toBoolean2 = new ToBoolean(Boolean.FALSE);
        for (int i2 = 0; i2 < 12; i2++) {
            objectReaderProvider.registerTypeConvert(clsArr[i2], Boolean.TYPE, toBoolean2);
        }
        ToString toString = new ToString();
        for (int i3 = 0; i3 < 12; i3++) {
            objectReaderProvider.registerTypeConvert(clsArr[i3], String.class, toString);
        }
        ToBigDecimal toBigDecimal = new ToBigDecimal();
        for (int i4 = 0; i4 < 12; i4++) {
            objectReaderProvider.registerTypeConvert(clsArr[i4], BigDecimal.class, toBigDecimal);
        }
        ToBigInteger toBigInteger = new ToBigInteger();
        for (int i5 = 0; i5 < 12; i5++) {
            objectReaderProvider.registerTypeConvert(clsArr[i5], BigInteger.class, toBigInteger);
        }
        ToByte toByte = new ToByte(null);
        for (int i6 = 0; i6 < 12; i6++) {
            objectReaderProvider.registerTypeConvert(clsArr[i6], Byte.class, toByte);
        }
        ToByte toByte2 = new ToByte((byte) 0);
        for (int i7 = 0; i7 < 12; i7++) {
            objectReaderProvider.registerTypeConvert(clsArr[i7], Byte.TYPE, toByte2);
        }
        ToShort toShort = new ToShort(null);
        for (int i8 = 0; i8 < 12; i8++) {
            objectReaderProvider.registerTypeConvert(clsArr[i8], Short.class, toShort);
        }
        ToShort toShort2 = new ToShort((short) 0);
        for (int i9 = 0; i9 < 12; i9++) {
            objectReaderProvider.registerTypeConvert(clsArr[i9], Short.TYPE, toShort2);
        }
        ToInteger toInteger = new ToInteger(null);
        for (int i10 = 0; i10 < 12; i10++) {
            objectReaderProvider.registerTypeConvert(clsArr[i10], Integer.class, toInteger);
        }
        ToInteger toInteger2 = new ToInteger(0);
        for (int i11 = 0; i11 < 12; i11++) {
            objectReaderProvider.registerTypeConvert(clsArr[i11], Integer.TYPE, toInteger2);
        }
        ToLong toLong = new ToLong(null);
        for (int i12 = 0; i12 < 12; i12++) {
            objectReaderProvider.registerTypeConvert(clsArr[i12], Long.class, toLong);
        }
        ToLong toLong2 = new ToLong(0L);
        for (int i13 = 0; i13 < 12; i13++) {
            objectReaderProvider.registerTypeConvert(clsArr[i13], Long.TYPE, toLong2);
        }
        ToFloat toFloat = new ToFloat(null);
        for (int i14 = 0; i14 < 12; i14++) {
            objectReaderProvider.registerTypeConvert(clsArr[i14], Float.class, toFloat);
        }
        ToFloat toFloat2 = new ToFloat(Float.valueOf(0.0f));
        for (int i15 = 0; i15 < 12; i15++) {
            objectReaderProvider.registerTypeConvert(clsArr[i15], Float.TYPE, toFloat2);
        }
        ToDouble toDouble = new ToDouble(null);
        for (int i16 = 0; i16 < 12; i16++) {
            objectReaderProvider.registerTypeConvert(clsArr[i16], Double.class, toDouble);
        }
        ToDouble toDouble2 = new ToDouble(Double.valueOf(AudioStats.AUDIO_AMPLITUDE_NONE));
        int i17 = 0;
        while (i17 < 12) {
            objectReaderProvider.registerTypeConvert(clsArr[i17], Double.TYPE, toDouble2);
            i17++;
            z = z;
        }
        boolean z2 = z;
        ToNumber toNumber = new ToNumber(Double.valueOf(AudioStats.AUDIO_AMPLITUDE_NONE));
        for (?? r4 = z2; r4 < 12; r4++) {
            objectReaderProvider.registerTypeConvert(clsArr[r4], Number.class, toNumber);
        }
        objectReaderProvider.registerTypeConvert(String.class, Character.TYPE, new StringToAny(Character.TYPE, '0'));
        objectReaderProvider.registerTypeConvert(String.class, Boolean.TYPE, new StringToAny(Boolean.TYPE, Boolean.valueOf(z2)));
        objectReaderProvider.registerTypeConvert(String.class, Float.TYPE, new StringToAny(Float.TYPE, Float.valueOf(0.0f)));
        objectReaderProvider.registerTypeConvert(String.class, Double.TYPE, new StringToAny(Double.TYPE, Double.valueOf(AudioStats.AUDIO_AMPLITUDE_NONE)));
        objectReaderProvider.registerTypeConvert(String.class, Byte.TYPE, new StringToAny(Byte.TYPE, (byte) 0));
        objectReaderProvider.registerTypeConvert(String.class, Short.TYPE, new StringToAny(Short.TYPE, (short) 0));
        objectReaderProvider.registerTypeConvert(String.class, Integer.TYPE, new StringToAny(Integer.TYPE, 0));
        objectReaderProvider.registerTypeConvert(String.class, Long.TYPE, new StringToAny(Long.TYPE, 0L));
        objectReaderProvider.registerTypeConvert(String.class, Character.class, new StringToAny(Character.class, null));
        objectReaderProvider.registerTypeConvert(String.class, Boolean.class, new StringToAny(Boolean.class, null));
        objectReaderProvider.registerTypeConvert(String.class, Double.class, new StringToAny(Double.class, null));
        objectReaderProvider.registerTypeConvert(String.class, Float.class, new StringToAny(Float.class, null));
        objectReaderProvider.registerTypeConvert(String.class, Byte.class, new StringToAny(Byte.class, null));
        objectReaderProvider.registerTypeConvert(String.class, Short.class, new StringToAny(Short.class, null));
        objectReaderProvider.registerTypeConvert(String.class, Integer.class, new StringToAny(Integer.class, null));
        objectReaderProvider.registerTypeConvert(String.class, Long.class, new StringToAny(Long.class, null));
        objectReaderProvider.registerTypeConvert(String.class, BigDecimal.class, new StringToAny(BigDecimal.class, null));
        objectReaderProvider.registerTypeConvert(String.class, BigInteger.class, new StringToAny(BigInteger.class, null));
        objectReaderProvider.registerTypeConvert(String.class, Number.class, new StringToAny(BigDecimal.class, null));
        objectReaderProvider.registerTypeConvert(String.class, Collection.class, new StringToAny(Collection.class, null));
        objectReaderProvider.registerTypeConvert(String.class, List.class, new StringToAny(List.class, null));
        objectReaderProvider.registerTypeConvert(String.class, JSONArray.class, new StringToAny(JSONArray.class, null));
        objectReaderProvider.registerTypeConvert(Boolean.class, Boolean.TYPE, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda20
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ObjectReaderBaseModule.lambda$init$1(obj);
            }
        });
        objectReaderProvider.registerTypeConvert(Long.class, LocalDateTime.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda21
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ObjectReaderBaseModule.lambda$init$2(obj);
            }
        });
        objectReaderProvider.registerTypeConvert(String.class, UUID.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda22
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ObjectReaderBaseModule.lambda$init$3(obj);
            }
        });
    }

    static /* synthetic */ Object lambda$init$2(Object obj) {
        if (obj == null || "null".equals(obj) || obj.equals(0L)) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(((Long) obj).longValue()), ZoneId.systemDefault());
    }

    static /* synthetic */ Object lambda$init$3(Object obj) {
        if (obj == null || "null".equals(obj) || "".equals(obj)) {
            return null;
        }
        return UUID.fromString((String) obj);
    }

    public class ReaderAnnotationProcessor implements ObjectReaderAnnotationProcessor {
        public ReaderAnnotationProcessor() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0078, code lost:
        
            getBeanInfo(r11, r2);
         */
        @Override // com.alibaba.fastjson2.modules.ObjectReaderAnnotationProcessor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void getBeanInfo(final com.alibaba.fastjson2.codec.BeanInfo r11, final java.lang.Class<?> r12) {
            /*
                Method dump skipped, instructions count: 466
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.ObjectReaderBaseModule.ReaderAnnotationProcessor.getBeanInfo(com.alibaba.fastjson2.codec.BeanInfo, java.lang.Class):void");
        }

        /* renamed from: lambda$getBeanInfo$0$com-alibaba-fastjson2-reader-ObjectReaderBaseModule$ReaderAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2790xb767e488(BeanInfo beanInfo, Class cls, Method method) {
            ObjectReaderBaseModule.this.getCreator(beanInfo, (Class<?>) cls, method);
        }

        /* renamed from: lambda$getBeanInfo$1$com-alibaba-fastjson2-reader-ObjectReaderBaseModule$ReaderAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2791xbe90c6c9(BeanInfo beanInfo, Class cls, Constructor constructor) {
            ObjectReaderBaseModule.this.getCreator(beanInfo, (Class<?>) cls, constructor);
        }

        /* renamed from: lambda$getBeanInfo$2$com-alibaba-fastjson2-reader-ObjectReaderBaseModule$ReaderAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2792xc5b9a90a(BeanInfo beanInfo, Class cls, Method method) {
            ObjectReaderBaseModule.this.getCreator(beanInfo, (Class<?>) cls, method);
        }

        /* renamed from: lambda$getBeanInfo$3$com-alibaba-fastjson2-reader-ObjectReaderBaseModule$ReaderAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2793xcce28b4b(BeanInfo beanInfo, Class cls, Constructor constructor) {
            ObjectReaderBaseModule.this.getCreator(beanInfo, (Class<?>) cls, constructor);
        }

        private void processJacksonJsonSubTypes(final BeanInfo beanInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda12
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectReaderBaseModule.ReaderAnnotationProcessor.lambda$processJacksonJsonSubTypes$4(annotation, beanInfo, (Method) obj);
                }
            });
        }

        static /* synthetic */ void lambda$processJacksonJsonSubTypes$4(Annotation annotation, BeanInfo beanInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if ("value".equals(name)) {
                    Object[] objArr = (Object[]) invoke;
                    if (objArr.length != 0) {
                        beanInfo.seeAlso = new Class[objArr.length];
                        beanInfo.seeAlsoNames = new String[objArr.length];
                        for (int i = 0; i < objArr.length; i++) {
                            BeanUtils.processJacksonJsonSubTypesType(beanInfo, i, (Annotation) objArr[i]);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }

        private void processJacksonJsonDeserializer(final BeanInfo beanInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda6
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectReaderBaseModule.ReaderAnnotationProcessor.this.m2797x47139f92(annotation, beanInfo, (Method) obj);
                }
            });
        }

        /* renamed from: lambda$processJacksonJsonDeserializer$5$com-alibaba-fastjson2-reader-ObjectReaderBaseModule$ReaderAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2797x47139f92(Annotation annotation, BeanInfo beanInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if (!"using".equals(name) && !"contentUsing".equals(name)) {
                    if ("builder".equals(name)) {
                        processBuilder(beanInfo, (Class) invoke);
                        return;
                    }
                    return;
                }
                Class processUsing = processUsing((Class) invoke);
                if (processUsing != null) {
                    beanInfo.deserializer = processUsing;
                }
            } catch (Throwable unused) {
            }
        }

        private void processJacksonJsonTypeInfo(final BeanInfo beanInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectReaderBaseModule.ReaderAnnotationProcessor.lambda$processJacksonJsonTypeInfo$6(annotation, beanInfo, (Method) obj);
                }
            });
        }

        static /* synthetic */ void lambda$processJacksonJsonTypeInfo$6(Annotation annotation, BeanInfo beanInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if ("property".equals(name)) {
                    String str = (String) invoke;
                    if (str.isEmpty()) {
                        return;
                    }
                    beanInfo.typeKey = str;
                    beanInfo.readerFeatures |= JSONReader.Feature.SupportAutoType.mask;
                }
            } catch (Throwable unused) {
            }
        }

        private void getBeanInfo(BeanInfo beanInfo, Annotation[] annotationArr) {
            int i;
            int length = annotationArr.length;
            while (i < length) {
                Annotation annotation = annotationArr[i];
                Class<? extends Annotation> annotationType = annotation.annotationType();
                JSONType jSONType = (JSONType) BeanUtils.findAnnotation(annotation, JSONType.class);
                if (jSONType != null) {
                    getBeanInfo1x(beanInfo, annotation);
                    i = jSONType == annotation ? i + 1 : 0;
                }
                if (annotationType == JSONCompiler.class && ((JSONCompiler) annotation).value() == JSONCompiler.CompilerOption.LAMBDA) {
                    beanInfo.readerFeatures |= FieldInfo.JIT;
                }
            }
        }

        void getBeanInfo1x(final BeanInfo beanInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda7
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectReaderBaseModule.ReaderAnnotationProcessor.this.m2794x9eaa6af6(annotation, beanInfo, (Method) obj);
                }
            });
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        /* JADX WARN: Removed duplicated region for block: B:95:0x013e A[Catch: all -> 0x0217, TryCatch #0 {all -> 0x0217, blocks: (B:3:0x0005, B:4:0x000f, B:8:0x0014, B:10:0x001c, B:12:0x0021, B:13:0x0028, B:15:0x002b, B:17:0x003d, B:20:0x0047, B:21:0x0043, B:24:0x004e, B:28:0x0058, B:30:0x0060, B:32:0x0065, B:36:0x0068, B:39:0x00e0, B:41:0x00e6, B:43:0x00ee, B:48:0x0071, B:50:0x0079, B:52:0x0081, B:56:0x0089, B:58:0x0091, B:60:0x0096, B:64:0x00a2, B:66:0x00aa, B:68:0x00b4, B:72:0x00b7, B:74:0x00bf, B:77:0x00c5, B:79:0x00cd, B:81:0x00d5, B:85:0x00d8, B:88:0x00f1, B:90:0x00f9, B:92:0x0100, B:93:0x0136, B:95:0x013e, B:99:0x0103, B:101:0x010b, B:103:0x0113, B:107:0x0116, B:109:0x011e, B:111:0x0126, B:115:0x012e, B:118:0x0141, B:120:0x0149, B:122:0x0155, B:126:0x0158, B:128:0x0160, B:130:0x0168, B:134:0x0170, B:136:0x0178, B:138:0x017d, B:142:0x0180, B:144:0x0188, B:147:0x0191, B:149:0x0199, B:151:0x019e, B:152:0x01a8, B:155:0x01e8, B:156:0x01ac, B:158:0x01b4, B:161:0x01be, B:163:0x01c6, B:166:0x01d0, B:168:0x01d8, B:171:0x01e2, B:176:0x01eb, B:178:0x01f3, B:180:0x01fb, B:184:0x0203, B:186:0x020b, B:188:0x0215), top: B:2:0x0005 }] */
        /* renamed from: lambda$getBeanInfo1x$7$com-alibaba-fastjson2-reader-ObjectReaderBaseModule$ReaderAnnotationProcessor, reason: not valid java name */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        /* synthetic */ void m2794x9eaa6af6(java.lang.annotation.Annotation r7, com.alibaba.fastjson2.codec.BeanInfo r8, java.lang.reflect.Method r9) {
            /*
                Method dump skipped, instructions count: 636
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.ObjectReaderBaseModule.ReaderAnnotationProcessor.m2794x9eaa6af6(java.lang.annotation.Annotation, com.alibaba.fastjson2.codec.BeanInfo, java.lang.reflect.Method):void");
        }

        private void processBuilder(BeanInfo beanInfo, Class cls) {
            if (cls == Void.TYPE || cls == Void.class) {
                return;
            }
            beanInfo.builder = cls;
            for (Annotation annotation : BeanUtils.getAnnotations(cls)) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                String name = annotationType.getName();
                if ("com.alibaba.fastjson.annotation.JSONPOJOBuilder".equals(name) || "com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder".equals(name)) {
                    ObjectReaderBaseModule.this.getBeanInfo1xJSONPOJOBuilder(beanInfo, cls, annotation, annotationType);
                } else {
                    JSONBuilder jSONBuilder = (JSONBuilder) BeanUtils.findAnnotation(cls, JSONBuilder.class);
                    if (jSONBuilder != null) {
                        beanInfo.buildMethod = BeanUtils.buildMethod(cls, jSONBuilder.buildMethod());
                        String withPrefix = jSONBuilder.withPrefix();
                        if (!withPrefix.isEmpty()) {
                            beanInfo.builderWithPrefix = withPrefix;
                        }
                    }
                }
            }
            if (beanInfo.buildMethod == null) {
                beanInfo.buildMethod = BeanUtils.buildMethod(cls, "build");
            }
            if (beanInfo.buildMethod == null) {
                beanInfo.buildMethod = BeanUtils.buildMethod(cls, "create");
            }
        }

        private void processSeeAlsoAnnotation(BeanInfo beanInfo, Class<?> cls) {
            Class cls2 = ObjectReaderBaseModule.this.provider.mixInCache.get(cls);
            if (cls2 == null && "org.apache.commons.lang3.tuple.Triple".equals(cls.getName())) {
                ObjectReaderBaseModule.this.provider.mixIn(cls, ApacheLang3Support.TripleMixIn.class);
                cls2 = ApacheLang3Support.TripleMixIn.class;
            }
            if (cls2 != null && cls2 != cls) {
                beanInfo.mixIn = true;
                processSeeAlsoAnnotation(beanInfo, BeanUtils.getAnnotations(cls2));
            }
            processSeeAlsoAnnotation(beanInfo, BeanUtils.getAnnotations(cls));
        }

        private void processSeeAlsoAnnotation(final BeanInfo beanInfo, Annotation[] annotationArr) {
            for (final Annotation annotation : annotationArr) {
                BeanUtils.annotationMethods(annotation.annotationType(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ObjectReaderBaseModule.ReaderAnnotationProcessor.lambda$processSeeAlsoAnnotation$8(annotation, beanInfo, (Method) obj);
                    }
                });
            }
        }

        static /* synthetic */ void lambda$processSeeAlsoAnnotation$8(Annotation annotation, BeanInfo beanInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if ("typeName".equals(name)) {
                    String str = (String) invoke;
                    if (str.isEmpty()) {
                        return;
                    }
                    beanInfo.typeName = str;
                }
            } catch (Throwable unused) {
            }
        }

        @Override // com.alibaba.fastjson2.modules.ObjectReaderAnnotationProcessor
        public void getFieldInfo(FieldInfo fieldInfo, Class cls, Constructor constructor, int i, Parameter parameter) {
            Class cls2;
            Constructor constructor2;
            Annotation[] annotationArr = null;
            if (cls != null && (cls2 = ObjectReaderBaseModule.this.provider.mixInCache.get(cls)) != null && cls2 != cls) {
                try {
                    constructor2 = cls2.getDeclaredConstructor(constructor.getParameterTypes());
                } catch (NoSuchMethodException unused) {
                    constructor2 = null;
                }
                if (constructor2 != null) {
                    processAnnotation(fieldInfo, BeanUtils.getAnnotations(constructor2.getParameters()[i]));
                }
            }
            if (Modifier.isStatic(constructor.getDeclaringClass().getModifiers())) {
                try {
                    annotationArr = BeanUtils.getAnnotations(parameter);
                } catch (ArrayIndexOutOfBoundsException unused2) {
                }
            } else {
                Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
                if (parameterAnnotations.length != constructor.getParameterCount()) {
                    i--;
                }
                if (i >= 0 && i < parameterAnnotations.length) {
                    annotationArr = parameterAnnotations[i];
                }
            }
            if (annotationArr == null || annotationArr.length <= 0) {
                return;
            }
            processAnnotation(fieldInfo, annotationArr);
        }

        @Override // com.alibaba.fastjson2.modules.ObjectReaderAnnotationProcessor
        public void getFieldInfo(FieldInfo fieldInfo, Class cls, Method method, int i, Parameter parameter) {
            Class cls2;
            Method method2;
            if (cls != null && (cls2 = ObjectReaderBaseModule.this.provider.mixInCache.get(cls)) != null && cls2 != cls) {
                try {
                    method2 = cls2.getMethod(method.getName(), method.getParameterTypes());
                } catch (NoSuchMethodException unused) {
                    method2 = null;
                }
                if (method2 != null) {
                    processAnnotation(fieldInfo, BeanUtils.getAnnotations(method2.getParameters()[i]));
                }
            }
            processAnnotation(fieldInfo, BeanUtils.getAnnotations(parameter));
        }

        @Override // com.alibaba.fastjson2.modules.ObjectReaderAnnotationProcessor
        public void getFieldInfo(FieldInfo fieldInfo, Class cls, Field field) {
            Class cls2;
            Field field2;
            if (cls != null && (cls2 = ObjectReaderBaseModule.this.provider.mixInCache.get(cls)) != null && cls2 != cls) {
                try {
                    field2 = cls2.getDeclaredField(field.getName());
                } catch (Exception unused) {
                    field2 = null;
                }
                if (field2 != null) {
                    getFieldInfo(fieldInfo, cls2, field2);
                }
            }
            processAnnotation(fieldInfo, BeanUtils.getAnnotations(field));
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x00e9, code lost:
        
            if (r13.equals("com.fasterxml.jackson.annotation.JsonFormat") == false) goto L39;
         */
        @Override // com.alibaba.fastjson2.modules.ObjectReaderAnnotationProcessor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void getFieldInfo(final com.alibaba.fastjson2.codec.FieldInfo r19, final java.lang.Class r20, java.lang.reflect.Method r21) {
            /*
                Method dump skipped, instructions count: 482
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.ObjectReaderBaseModule.ReaderAnnotationProcessor.getFieldInfo(com.alibaba.fastjson2.codec.FieldInfo, java.lang.Class, java.lang.reflect.Method):void");
        }

        /* renamed from: lambda$getFieldInfo$9$com-alibaba-fastjson2-reader-ObjectReaderBaseModule$ReaderAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2795x8ae4c409(String str, FieldInfo fieldInfo, Class cls, String str2, String str3, Field field) {
            if (field.getName().equals(str)) {
                int modifiers = field.getModifiers();
                if (!Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
                    getFieldInfo(fieldInfo, cls, field);
                }
                fieldInfo.features |= FieldInfo.FIELD_MASK;
                return;
            }
            if (field.getName().equals(str2)) {
                int modifiers2 = field.getModifiers();
                if (!Modifier.isPublic(modifiers2) && !Modifier.isStatic(modifiers2)) {
                    getFieldInfo(fieldInfo, cls, field);
                }
                fieldInfo.features |= FieldInfo.FIELD_MASK;
                return;
            }
            if (field.getName().equals(str3)) {
                int modifiers3 = field.getModifiers();
                if (!Modifier.isPublic(modifiers3) && !Modifier.isStatic(modifiers3)) {
                    getFieldInfo(fieldInfo, cls, field);
                }
                fieldInfo.features |= FieldInfo.FIELD_MASK;
            }
        }

        private void processAnnotation(FieldInfo fieldInfo, Annotation[] annotationArr) {
            int i;
            boolean isUseJacksonAnnotation;
            for (Annotation annotation : annotationArr) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                JSONField jSONField = (JSONField) BeanUtils.findAnnotation(annotation, JSONField.class);
                if (jSONField != null) {
                    getFieldInfo(fieldInfo, jSONField);
                    i = jSONField == annotation ? i + 1 : 0;
                }
                if (annotationType == JSONCompiler.class && ((JSONCompiler) annotation).value() == JSONCompiler.CompilerOption.LAMBDA) {
                    fieldInfo.features |= FieldInfo.JIT;
                }
                isUseJacksonAnnotation = JSONFactory.isUseJacksonAnnotation();
                String name = annotationType.getName();
                name.hashCode();
                switch (name) {
                    case "com.google.gson.annotations.SerializedName":
                        if (JSONFactory.isUseGsonAnnotation()) {
                            BeanUtils.processGsonSerializedName(fieldInfo, annotation);
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
                    case "com.fasterxml.jackson.databind.annotation.JsonDeserialize":
                        if (isUseJacksonAnnotation) {
                            processJacksonJsonDeserialize(fieldInfo, annotation);
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
                    case "com.fasterxml.jackson.annotation.JsonSetter":
                        if (isUseJacksonAnnotation) {
                            processJacksonJsonSetter(fieldInfo, annotation);
                            break;
                        } else {
                            break;
                        }
                    case "com.fasterxml.jackson.annotation.JsonAlias":
                        if (isUseJacksonAnnotation) {
                            processJacksonJsonAlias(fieldInfo, annotation);
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
                    case "com.alibaba.fastjson.annotation.JSONField":
                        processJSONField1x(fieldInfo, annotation);
                        break;
                    case "com.fasterxml.jackson.annotation.JsonBackReference":
                        if (isUseJacksonAnnotation) {
                            fieldInfo.features |= 2305843009213693952L;
                            break;
                        } else {
                            break;
                        }
                }
            }
        }

        private void processJacksonJsonDeserialize(final FieldInfo fieldInfo, final Annotation annotation) {
            if (JSONFactory.isUseJacksonAnnotation()) {
                BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda2
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ObjectReaderBaseModule.ReaderAnnotationProcessor.this.m2796x3ab2c52(annotation, fieldInfo, (Method) obj);
                    }
                });
            }
        }

        /* renamed from: lambda$processJacksonJsonDeserialize$10$com-alibaba-fastjson2-reader-ObjectReaderBaseModule$ReaderAnnotationProcessor, reason: not valid java name */
        /* synthetic */ void m2796x3ab2c52(Annotation annotation, FieldInfo fieldInfo, Method method) {
            Class<?> processUsing;
            Class<?> processUsing2;
            Class<?> processUsing3;
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                int hashCode = name.hashCode();
                if (hashCode == 111582340) {
                    if (!name.equals("using") || (processUsing = processUsing((Class) invoke)) == null) {
                        return;
                    }
                    fieldInfo.readUsing = processUsing;
                    return;
                }
                if (hashCode == 491860325) {
                    if (!name.equals("keyUsing") || (processUsing2 = processUsing((Class) invoke)) == null) {
                        return;
                    }
                    fieldInfo.keyUsing = processUsing2;
                    return;
                }
                if (hashCode == 2034063763 && name.equals("valueUsing") && (processUsing3 = processUsing((Class) invoke)) != null) {
                    fieldInfo.keyUsing = processUsing3;
                }
            } catch (Throwable unused) {
            }
        }

        private Class processUsing(Class cls) {
            if ("com.fasterxml.jackson.databind.JsonDeserializer$None".equals(cls.getName()) || !ObjectReader.class.isAssignableFrom(cls)) {
                return null;
            }
            return cls;
        }

        private void processJacksonJsonProperty(final FieldInfo fieldInfo, final Annotation annotation) {
            if (JSONFactory.isUseJacksonAnnotation()) {
                BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda14
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ObjectReaderBaseModule.ReaderAnnotationProcessor.lambda$processJacksonJsonProperty$11(annotation, fieldInfo, (Method) obj);
                    }
                });
            }
        }

        static /* synthetic */ void lambda$processJacksonJsonProperty$11(Annotation annotation, FieldInfo fieldInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                int hashCode = name.hashCode();
                if (hashCode == -1423461020) {
                    if (name.equals("access")) {
                        fieldInfo.ignore = "READ_ONLY".equals(((Enum) invoke).name());
                        return;
                    }
                    return;
                }
                if (hashCode == -393139297) {
                    if (name.equals("required") && ((Boolean) invoke).booleanValue()) {
                        fieldInfo.required = true;
                        return;
                    }
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

        private void processJacksonJsonSetter(final FieldInfo fieldInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectReaderBaseModule.ReaderAnnotationProcessor.lambda$processJacksonJsonSetter$12(annotation, fieldInfo, (Method) obj);
                }
            });
        }

        static /* synthetic */ void lambda$processJacksonJsonSetter$12(Annotation annotation, FieldInfo fieldInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if (name.hashCode() == 111972721 && name.equals("value")) {
                    String str = (String) invoke;
                    if (str.isEmpty()) {
                        return;
                    }
                    fieldInfo.fieldName = str;
                }
            } catch (Throwable unused) {
            }
        }

        private void processJacksonJsonAlias(final FieldInfo fieldInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectReaderBaseModule.ReaderAnnotationProcessor.lambda$processJacksonJsonAlias$13(annotation, fieldInfo, (Method) obj);
                }
            });
        }

        static /* synthetic */ void lambda$processJacksonJsonAlias$13(Annotation annotation, FieldInfo fieldInfo, Method method) {
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if ("value".equals(name)) {
                    String[] strArr = (String[]) invoke;
                    if (strArr.length != 0) {
                        fieldInfo.alternateNames = strArr;
                    }
                }
            } catch (Throwable unused) {
            }
        }

        private void processJSONField1x(final FieldInfo fieldInfo, final Annotation annotation) {
            BeanUtils.annotationMethods(annotation.getClass(), new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$ReaderAnnotationProcessor$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ObjectReaderBaseModule.ReaderAnnotationProcessor.lambda$processJSONField1x$14(annotation, fieldInfo, (Method) obj);
                }
            });
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        static /* synthetic */ void lambda$processJSONField1x$14(Annotation annotation, FieldInfo fieldInfo, Method method) {
            int intValue;
            String name = method.getName();
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                switch (name.hashCode()) {
                    case -1268779017:
                        if (name.equals("format")) {
                            String str = (String) invoke;
                            if (!str.isEmpty()) {
                                String trim = str.trim();
                                if (trim.indexOf(84) != -1 && !trim.contains("'T'")) {
                                    trim = trim.replace(ExifInterface.GPS_DIRECTION_TRUE, "'T'");
                                }
                                fieldInfo.format = trim;
                                break;
                            }
                        }
                        break;
                    case -1206994319:
                        if (name.equals("ordinal") && (intValue = ((Integer) invoke).intValue()) != 0) {
                            fieldInfo.ordinal = intValue;
                            break;
                        }
                        break;
                    case -1073807344:
                        if (name.equals("parseFeatures")) {
                            for (Enum r0 : (Enum[]) invoke) {
                                String name2 = r0.name();
                                int hashCode = name2.hashCode();
                                if (hashCode != -894003883) {
                                    if (hashCode != -200815016) {
                                        if (hashCode == 2005790178 && name2.equals("InitStringFieldAsEmpty")) {
                                            fieldInfo.features |= JSONReader.Feature.InitStringFieldAsEmpty.mask;
                                        }
                                    } else if (name2.equals("SupportAutoType")) {
                                        fieldInfo.features |= JSONReader.Feature.SupportAutoType.mask;
                                    }
                                } else if (name2.equals("SupportArrayToBean")) {
                                    fieldInfo.features |= JSONReader.Feature.SupportArrayToBean.mask;
                                }
                            }
                            break;
                        }
                        break;
                    case -987658292:
                        if (name.equals("unwrapped") && ((Boolean) invoke).booleanValue()) {
                            fieldInfo.features |= FieldInfo.UNWRAPPED_MASK;
                            break;
                        }
                        break;
                    case -659125328:
                        if (name.equals("defaultValue")) {
                            String str2 = (String) invoke;
                            if (!str2.isEmpty()) {
                                fieldInfo.defaultValue = str2;
                                break;
                            }
                        }
                        break;
                    case -224599314:
                        if (name.equals("alternateNames")) {
                            String[] strArr = (String[]) invoke;
                            if (strArr.length != 0) {
                                if (fieldInfo.alternateNames == null) {
                                    fieldInfo.alternateNames = strArr;
                                    break;
                                } else {
                                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                                    linkedHashSet.addAll(Arrays.asList(strArr));
                                    linkedHashSet.addAll(Arrays.asList(fieldInfo.alternateNames));
                                    fieldInfo.alternateNames = (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
                                    break;
                                }
                            }
                        }
                        break;
                    case 3373707:
                        if (name.equals(HintConstants.AUTOFILL_HINT_NAME)) {
                            String str3 = (String) invoke;
                            if (!str3.isEmpty()) {
                                fieldInfo.fieldName = str3;
                                break;
                            }
                        }
                        break;
                    case 102727412:
                        if (name.equals(AnnotatedPrivateKey.LABEL)) {
                            String str4 = (String) invoke;
                            if (!str4.isEmpty()) {
                                fieldInfo.label = str4;
                                break;
                            }
                        }
                        break;
                    case 1053501509:
                        if (name.equals("deserializeUsing")) {
                            Class<?> cls = (Class) invoke;
                            if (ObjectReader.class.isAssignableFrom(cls)) {
                                fieldInfo.readUsing = cls;
                                break;
                            }
                        }
                        break;
                    case 1746983807:
                        if (name.equals("deserialize") && !((Boolean) invoke).booleanValue()) {
                            fieldInfo.ignore = true;
                            break;
                        }
                        break;
                }
            } catch (Throwable unused) {
            }
        }

        private void getFieldInfo(FieldInfo fieldInfo, JSONField jSONField) {
            if (jSONField == null) {
                return;
            }
            String name = jSONField.name();
            if (!name.isEmpty()) {
                fieldInfo.fieldName = name;
            }
            String format = jSONField.format();
            if (!format.isEmpty()) {
                String trim = format.trim();
                if (trim.indexOf(84) != -1 && !trim.contains("'T'")) {
                    trim = trim.replace(ExifInterface.GPS_DIRECTION_TRUE, "'T'");
                }
                fieldInfo.format = trim;
            }
            String label = jSONField.label();
            if (!label.isEmpty()) {
                fieldInfo.label = label.trim();
            }
            String defaultValue = jSONField.defaultValue();
            if (!defaultValue.isEmpty()) {
                fieldInfo.defaultValue = defaultValue;
            }
            String locale = jSONField.locale();
            if (!locale.isEmpty()) {
                String[] split = locale.split("_");
                if (split.length == 2) {
                    fieldInfo.locale = new Locale(split[0], split[1]);
                }
            }
            String[] alternateNames = jSONField.alternateNames();
            if (alternateNames.length != 0) {
                if (fieldInfo.alternateNames == null) {
                    fieldInfo.alternateNames = alternateNames;
                } else {
                    LinkedHashSet linkedHashSet = new LinkedHashSet(alternateNames.length + fieldInfo.alternateNames.length, 1.0f);
                    Collections.addAll(linkedHashSet, alternateNames);
                    Collections.addAll(linkedHashSet, fieldInfo.alternateNames);
                    fieldInfo.alternateNames = (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
                }
            }
            boolean deserialize = jSONField.deserialize();
            boolean z = !deserialize;
            if (!fieldInfo.ignore) {
                fieldInfo.ignore = z;
            }
            for (JSONReader.Feature feature : jSONField.deserializeFeatures()) {
                fieldInfo.features |= feature.mask;
                if (fieldInfo.ignore && deserialize && feature == JSONReader.Feature.FieldBased) {
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
            if (jSONField.unwrapped()) {
                fieldInfo.features |= FieldInfo.UNWRAPPED_MASK;
            }
            if (jSONField.required()) {
                fieldInfo.required = true;
            }
            String trim2 = jSONField.schema().trim();
            if (!trim2.isEmpty()) {
                fieldInfo.schema = trim2;
            }
            Class<?> deserializeUsing = jSONField.deserializeUsing();
            if (ObjectReader.class.isAssignableFrom(deserializeUsing)) {
                fieldInfo.readUsing = deserializeUsing;
            }
            String trim3 = jSONField.arrayToMapKey().trim();
            if (!trim3.isEmpty()) {
                fieldInfo.arrayToMapKey = trim3;
            }
            Class<?> arrayToMapDuplicateHandler = jSONField.arrayToMapDuplicateHandler();
            if (arrayToMapDuplicateHandler != Void.class) {
                fieldInfo.arrayToMapDuplicateHandler = arrayToMapDuplicateHandler;
            }
            Class<?> contentAs = jSONField.contentAs();
            if (contentAs != Void.class) {
                fieldInfo.contentAs = contentAs;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getBeanInfo1xJSONPOJOBuilder(final BeanInfo beanInfo, final Class<?> cls, final Annotation annotation, Class<? extends Annotation> cls2) {
        BeanUtils.annotationMethods(cls2, new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda18
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ObjectReaderBaseModule.lambda$getBeanInfo1xJSONPOJOBuilder$4(annotation, beanInfo, cls, (Method) obj);
            }
        });
    }

    static /* synthetic */ void lambda$getBeanInfo1xJSONPOJOBuilder$4(Annotation annotation, BeanInfo beanInfo, Class cls, Method method) {
        try {
            String name = method.getName();
            int hashCode = name.hashCode();
            if (hashCode != 672684058) {
                if (hashCode != 2068281583) {
                    if (hashCode == 2092901112 && name.equals("withPrefix")) {
                        String str = (String) method.invoke(annotation, new Object[0]);
                        if (str.isEmpty()) {
                            return;
                        }
                        beanInfo.builderWithPrefix = str;
                        return;
                    }
                    return;
                }
                if (!name.equals("buildMethod")) {
                    return;
                }
            } else if (!name.equals("buildMethodName")) {
                return;
            }
            beanInfo.buildMethod = BeanUtils.buildMethod(cls, (String) method.invoke(annotation, new Object[0]));
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCreator(final BeanInfo beanInfo, Class<?> cls, Constructor constructor) {
        Constructor<?> constructor2;
        Class<? extends Annotation> annotationType;
        if (cls.isEnum()) {
            return;
        }
        boolean z = false;
        for (final Annotation annotation : BeanUtils.getAnnotations(constructor)) {
            annotationType = annotation.annotationType();
            JSONCreator jSONCreator = (JSONCreator) BeanUtils.findAnnotation(annotation, JSONCreator.class);
            if (jSONCreator != null) {
                String[] parameterNames = jSONCreator.parameterNames();
                if (parameterNames.length != 0) {
                    beanInfo.createParameterNames = parameterNames;
                }
                if (jSONCreator != annotation) {
                    z = true;
                }
                z = true;
            }
            String name = annotationType.getName();
            name.hashCode();
            switch (name) {
                case "com.fasterxml.jackson.annotation.JsonCreator":
                    if (!JSONFactory.isUseJacksonAnnotation()) {
                        break;
                    }
                    z = true;
                    break;
                case "com.alibaba.fastjson.annotation.JSONCreator":
                case "com.alibaba.fastjson2.annotation.JSONCreator":
                    BeanUtils.annotationMethods(annotationType, new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda17
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ObjectReaderBaseModule.lambda$getCreator$5(annotation, beanInfo, (Method) obj);
                        }
                    });
                    z = true;
                    break;
            }
        }
        if (z) {
            try {
                constructor2 = cls.getDeclaredConstructor(constructor.getParameterTypes());
            } catch (NoSuchMethodException unused) {
                constructor2 = null;
            }
            if (constructor2 != null) {
                beanInfo.creatorConstructor = constructor2;
            }
        }
    }

    static /* synthetic */ void lambda$getCreator$5(Annotation annotation, BeanInfo beanInfo, Method method) {
        try {
            if ("parameterNames".equals(method.getName())) {
                String[] strArr = (String[]) method.invoke(annotation, new Object[0]);
                if (strArr.length != 0) {
                    beanInfo.createParameterNames = strArr;
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCreator(final BeanInfo beanInfo, Class<?> cls, Method method) {
        if (method.getDeclaringClass() == Enum.class) {
            return;
        }
        String name = method.getName();
        if (cls.isEnum() && "values".equals(name)) {
            return;
        }
        Annotation[] annotations = BeanUtils.getAnnotations(method);
        int length = annotations.length;
        Method method2 = null;
        int i = 0;
        JSONCreator jSONCreator = null;
        boolean z = false;
        while (i < length) {
            final Annotation annotation = annotations[i];
            Class<? extends Annotation> annotationType = annotation.annotationType();
            JSONCreator jSONCreator2 = (JSONCreator) BeanUtils.findAnnotation(annotation, JSONCreator.class);
            if (jSONCreator2 != annotation) {
                String name2 = annotationType.getName();
                name2.hashCode();
                if (name2.equals("com.fasterxml.jackson.annotation.JsonCreator")) {
                    if (JSONFactory.isUseJacksonAnnotation()) {
                        BeanUtils.annotationMethods(annotationType, new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda11
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ObjectReaderBaseModule.lambda$getCreator$7(annotation, beanInfo, (Method) obj);
                            }
                        });
                        z = true;
                    }
                } else if (name2.equals("com.alibaba.fastjson.annotation.JSONCreator")) {
                    BeanUtils.annotationMethods(annotationType, new Consumer() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ObjectReaderBaseModule.lambda$getCreator$6(annotation, beanInfo, (Method) obj);
                        }
                    });
                    z = true;
                }
            }
            i++;
            jSONCreator = jSONCreator2;
        }
        if (jSONCreator != null) {
            String[] parameterNames = jSONCreator.parameterNames();
            if (parameterNames.length != 0) {
                beanInfo.createParameterNames = parameterNames;
            }
            z = true;
        }
        if (z) {
            try {
                method2 = cls.getDeclaredMethod(name, method.getParameterTypes());
            } catch (NoSuchMethodException unused) {
            }
            if (method2 != null) {
                beanInfo.createMethod = method2;
            }
        }
    }

    static /* synthetic */ void lambda$getCreator$6(Annotation annotation, BeanInfo beanInfo, Method method) {
        try {
            if ("parameterNames".equals(method.getName())) {
                String[] strArr = (String[]) method.invoke(annotation, new Object[0]);
                if (strArr.length != 0) {
                    beanInfo.createParameterNames = strArr;
                }
            }
        } catch (Throwable unused) {
        }
    }

    static /* synthetic */ void lambda$getCreator$7(Annotation annotation, BeanInfo beanInfo, Method method) {
        try {
            if ("parameterNames".equals(method.getName())) {
                String[] strArr = (String[]) method.invoke(annotation, new Object[0]);
                if (strArr.length != 0) {
                    beanInfo.createParameterNames = strArr;
                }
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.alibaba.fastjson2.modules.ObjectReaderModule
    public ReaderAnnotationProcessor getAnnotationProcessor() {
        return this.annotationProcessor;
    }

    @Override // com.alibaba.fastjson2.modules.ObjectReaderModule
    public void getBeanInfo(BeanInfo beanInfo, Class<?> cls) {
        ReaderAnnotationProcessor readerAnnotationProcessor = this.annotationProcessor;
        if (readerAnnotationProcessor != null) {
            readerAnnotationProcessor.getBeanInfo(beanInfo, cls);
        }
    }

    @Override // com.alibaba.fastjson2.modules.ObjectReaderModule
    public void getFieldInfo(FieldInfo fieldInfo, Class cls, Field field) {
        ReaderAnnotationProcessor readerAnnotationProcessor = this.annotationProcessor;
        if (readerAnnotationProcessor != null) {
            readerAnnotationProcessor.getFieldInfo(fieldInfo, cls, field);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.alibaba.fastjson2.modules.ObjectReaderModule
    public ObjectReader getObjectReader(ObjectReaderProvider objectReaderProvider, Type type) {
        String str;
        char c;
        char c2;
        char c3;
        char c4;
        if (type == String.class || type == CharSequence.class) {
            return ObjectReaderImplString.INSTANCE;
        }
        if (type == Character.TYPE || type == Character.class) {
            return ObjectReaderImplCharacter.INSTANCE;
        }
        if (type == Boolean.TYPE || type == Boolean.class) {
            return ObjectReaderImplBoolean.INSTANCE;
        }
        if (type == Byte.TYPE || type == Byte.class) {
            return ObjectReaderImplByte.INSTANCE;
        }
        if (type == Short.TYPE || type == Short.class) {
            return ObjectReaderImplShort.INSTANCE;
        }
        if (type == Integer.TYPE || type == Integer.class) {
            return ObjectReaderImplInteger.INSTANCE;
        }
        if (type == Long.TYPE || type == Long.class) {
            return ObjectReaderImplInt64.INSTANCE;
        }
        if (type == Float.TYPE || type == Float.class) {
            return ObjectReaderImplFloat.INSTANCE;
        }
        if (type == Double.TYPE || type == Double.class) {
            return ObjectReaderImplDouble.INSTANCE;
        }
        if (type == BigInteger.class) {
            return ObjectReaderImplBigInteger.INSTANCE;
        }
        if (type == BigDecimal.class) {
            return ObjectReaderImplBigDecimal.INSTANCE;
        }
        if (type == Number.class) {
            return ObjectReaderImplNumber.INSTANCE;
        }
        if (type == BitSet.class) {
            return ObjectReaderImplBitSet.INSTANCE;
        }
        if (type == OptionalInt.class) {
            return ObjectReaderImplOptionalInt.INSTANCE;
        }
        if (type == OptionalLong.class) {
            return ObjectReaderImplOptionalLong.INSTANCE;
        }
        if (type == OptionalDouble.class) {
            return ObjectReaderImplOptionalDouble.INSTANCE;
        }
        if (type == Optional.class) {
            return ObjectReaderImplOptional.INSTANCE;
        }
        if (type == UUID.class) {
            return ObjectReaderImplUUID.INSTANCE;
        }
        if (type == Duration.class) {
            return new ObjectReaderImplFromString(Duration.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Duration parse;
                    parse = Duration.parse((String) obj);
                    return parse;
                }
            });
        }
        if (type == Period.class) {
            return new ObjectReaderImplFromString(Period.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Period parse;
                    parse = Period.parse((String) obj);
                    return parse;
                }
            });
        }
        if (type == AtomicBoolean.class) {
            return new ObjectReaderImplFromBoolean(AtomicBoolean.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ObjectReaderBaseModule.m2787$r8$lambda$p90uMnuBXujNSd9h8icGqEE8mU(((Boolean) obj).booleanValue());
                }
            });
        }
        if (type == URI.class) {
            return new ObjectReaderImplFromString(URI.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda9
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    URI create;
                    create = URI.create((String) obj);
                    return create;
                }
            });
        }
        if (type == Charset.class) {
            return new ObjectReaderImplFromString(Charset.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Charset forName;
                    forName = Charset.forName((String) obj);
                    return forName;
                }
            });
        }
        if (type == File.class) {
            return new ObjectReaderImplFromString(File.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ObjectReaderBaseModule.$r8$lambda$3UDCRuryPMpPdeZ_mA8WA5301tk((String) obj);
                }
            });
        }
        if (type == Path.class) {
            return new ObjectReaderImplFromString(Path.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda13
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Path path;
                    path = Paths.get((String) obj, new String[0]);
                    return path;
                }
            });
        }
        if (type == URL.class) {
            return new ObjectReaderImplFromString(URL.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ObjectReaderBaseModule.lambda$getObjectReader$9((String) obj);
                }
            });
        }
        if (type == Pattern.class) {
            return new ObjectReaderImplFromString(Pattern.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda15
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Pattern compile;
                    compile = Pattern.compile((String) obj);
                    return compile;
                }
            });
        }
        if (type == Class.class) {
            return ObjectReaderImplClass.INSTANCE;
        }
        if (type == Method.class) {
            return new ObjectReaderImplMethod();
        }
        if (type == Field.class) {
            return new ObjectReaderImplField();
        }
        if (type == Type.class) {
            return ObjectReaderImplClass.INSTANCE;
        }
        String typeName = type.getTypeName();
        typeName.hashCode();
        switch (typeName) {
            case "org.springframework.security.authentication.UsernamePasswordAuthenticationToken":
                str = "org.springframework.security.jackson2.UsernamePasswordAuthenticationTokenMixin";
                break;
            case "com.google.common.collect.AbstractMapBasedMultimap$WrappedSet":
                return null;
            case "org.springframework.security.web.csrf.DefaultCsrfToken":
                str = "org.springframework.security.web.jackson2.DefaultCsrfTokenMixin";
                break;
            case "org.springframework.security.core.authority.AnonymousAuthenticationToken":
                str = "org.springframework.security.jackson2.RememberMeAuthenticationTokenMixin";
                break;
            case "org.springframework.security.web.savedrequest.SavedCookie":
                str = "org.springframework.security.web.jackson2.SavedCookieMixin";
                break;
            case "org.springframework.security.web.authentication.WebAuthenticationDetails":
                str = "org.springframework.security.web.jackson2.WebAuthenticationDetailsMixin";
                break;
            case "org.springframework.util.LinkedMultiValueMap":
                return ObjectReaderImplMap.of(type, (Class) type, 0L);
            case "org.springframework.security.core.authority.RememberMeAuthenticationToken":
                str = "org.springframework.security.jackson2.AnonymousAuthenticationTokenMixin";
                break;
            case "org.springframework.security.authentication.BadCredentialsException":
                str = "org.springframework.security.jackson2.BadCredentialsExceptionMixin";
                break;
            case "org.springframework.security.core.userdetails.User":
                str = "org.springframework.security.jackson2.UserMixin";
                break;
            case "org.springframework.security.core.authority.SimpleGrantedAuthority":
                str = "org.springframework.security.jackson2.SimpleGrantedAuthorityMixin";
                break;
            default:
                str = null;
                break;
        }
        if (str != null && objectReaderProvider.mixInCache.get(type) == null) {
            Class loadClass = TypeUtils.loadClass(str);
            if (loadClass == null && "org.springframework.security.jackson2.SimpleGrantedAuthorityMixin".equals(str)) {
                loadClass = TypeUtils.loadClass("com.alibaba.fastjson2.internal.mixin.spring.SimpleGrantedAuthorityMixin");
            }
            if (loadClass != null) {
                objectReaderProvider.mixInCache.putIfAbsent((Class) type, loadClass);
            }
        }
        if (type == Map.class || type == AbstractMap.class) {
            return ObjectReaderImplMap.of(null, (Class) type, 0L);
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return typedMap((Class) type, ConcurrentHashMap.class, null, Object.class);
        }
        if (type == ConcurrentNavigableMap.class || type == ConcurrentSkipListMap.class) {
            return typedMap((Class) type, ConcurrentSkipListMap.class, null, Object.class);
        }
        if (type == SortedMap.class || type == NavigableMap.class || type == TreeMap.class) {
            return typedMap((Class) type, TreeMap.class, null, Object.class);
        }
        if (type == Calendar.class || "javax.xml.datatype.XMLGregorianCalendar".equals(typeName)) {
            return ObjectReaderImplCalendar.INSTANCE;
        }
        if (type == Date.class) {
            return ObjectReaderImplDate.INSTANCE;
        }
        if (type == LocalDate.class) {
            return ObjectReaderImplLocalDate.INSTANCE;
        }
        if (type == LocalTime.class) {
            return ObjectReaderImplLocalTime.INSTANCE;
        }
        if (type == LocalDateTime.class) {
            return ObjectReaderImplLocalDateTime.INSTANCE;
        }
        if (type == ZonedDateTime.class) {
            return ObjectReaderImplZonedDateTime.INSTANCE;
        }
        if (type == OffsetDateTime.class) {
            return ObjectReaderImplOffsetDateTime.INSTANCE;
        }
        if (type == OffsetTime.class) {
            return ObjectReaderImplOffsetTime.INSTANCE;
        }
        if (type == ZoneOffset.class) {
            return new ObjectReaderImplFromString(ZoneOffset.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda16
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    ZoneOffset of;
                    of = ZoneOffset.of((String) obj);
                    return of;
                }
            });
        }
        if (type == Instant.class) {
            return ObjectReaderImplInstant.INSTANCE;
        }
        if (type == Locale.class) {
            return ObjectReaderImplLocale.INSTANCE;
        }
        if (type == Currency.class) {
            return ObjectReaderImplCurrency.INSTANCE;
        }
        if (type == ZoneId.class) {
            return new ObjectReaderImplFromString(ZoneId.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda24
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    ZoneId of;
                    of = ZoneId.of((String) obj);
                    return of;
                }
            });
        }
        if (type == TimeZone.class) {
            return new ObjectReaderImplFromString(TimeZone.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    TimeZone timeZone;
                    timeZone = TimeZone.getTimeZone((String) obj);
                    return timeZone;
                }
            });
        }
        if (type == char[].class) {
            return ObjectReaderImplCharValueArray.INSTANCE;
        }
        if (type == float[].class) {
            return ObjectReaderImplFloatValueArray.INSTANCE;
        }
        if (type == double[].class) {
            return ObjectReaderImplDoubleValueArray.INSTANCE;
        }
        if (type == boolean[].class) {
            return ObjectReaderImplBoolValueArray.INSTANCE;
        }
        if (type == byte[].class) {
            return ObjectReaderImplInt8ValueArray.INSTANCE;
        }
        if (type == short[].class) {
            return ObjectReaderImplInt16ValueArray.INSTANCE;
        }
        if (type == int[].class) {
            return ObjectReaderImplInt32ValueArray.INSTANCE;
        }
        if (type == long[].class) {
            return ObjectReaderImplInt64ValueArray.INSTANCE;
        }
        if (type == Byte[].class) {
            return ObjectReaderImplInt8Array.INSTANCE;
        }
        if (type == Short[].class) {
            return ObjectReaderImplInt16Array.INSTANCE;
        }
        if (type == Integer[].class) {
            return ObjectReaderImplInt32Array.INSTANCE;
        }
        if (type == Long[].class) {
            return ObjectReaderImplInt64Array.INSTANCE;
        }
        if (type == Float[].class) {
            return ObjectReaderImplFloatArray.INSTANCE;
        }
        if (type == Double[].class) {
            return ObjectReaderImplDoubleArray.INSTANCE;
        }
        if (type == Number[].class) {
            return ObjectReaderImplNumberArray.INSTANCE;
        }
        if (type == String[].class) {
            return ObjectReaderImplStringArray.INSTANCE;
        }
        if (type == AtomicInteger.class) {
            return new ObjectReaderImplFromInt(AtomicInteger.class, new IntFunction() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda26
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return ObjectReaderBaseModule.$r8$lambda$fgjolKahIJ8DIhpC69R6doXRWuY(i);
                }
            });
        }
        if (type == AtomicLong.class) {
            return new ObjectReaderImplFromLong(AtomicLong.class, new LongFunction() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda1
                @Override // java.util.function.LongFunction
                public final Object apply(long j) {
                    return ObjectReaderBaseModule.$r8$lambda$8ZqB1iVOXrPrsVyf72if6iEU5hw(j);
                }
            });
        }
        if (type == AtomicIntegerArray.class) {
            return new ObjectReaderImplInt32ValueArray(AtomicIntegerArray.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ObjectReaderBaseModule.$r8$lambda$3qZHSL4C1HvTalVoO96JJ_FVv6I((int[]) obj);
                }
            });
        }
        if (type == AtomicLongArray.class) {
            return new ObjectReaderImplInt64ValueArray(AtomicLongArray.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ObjectReaderBaseModule.$r8$lambda$U0lGPadskxz3pdM5U9NMWRlHrRE((long[]) obj);
                }
            });
        }
        if (type == AtomicReference.class) {
            return ObjectReaderImplAtomicReference.INSTANCE;
        }
        if (type instanceof MultiType) {
            return new ObjectArrayReaderMultiType((MultiType) type);
        }
        if (type instanceof MapMultiValueType) {
            return new ObjectReaderImplMapMultiValueType((MapMultiValueType) type);
        }
        if (type == StringBuffer.class || type == StringBuilder.class) {
            try {
                Class cls = (Class) type;
                return new ObjectReaderImplValue(cls, String.class, String.class, 0L, null, null, null, cls.getConstructor(String.class), null, null);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        if (type == Iterable.class || type == Collection.class || type == List.class || type == AbstractCollection.class || type == AbstractList.class || type == ArrayList.class) {
            return ObjectReaderImplList.of(type, null, 0L);
        }
        if (type == Queue.class || type == Deque.class || type == AbstractSequentialList.class || type == LinkedList.class) {
            return ObjectReaderImplList.of(type, null, 0L);
        }
        if (type == Set.class || type == AbstractSet.class || type == EnumSet.class) {
            return ObjectReaderImplList.of(type, null, 0L);
        }
        if (type == NavigableSet.class || type == SortedSet.class) {
            return ObjectReaderImplList.of(type, null, 0L);
        }
        if (type == ConcurrentLinkedDeque.class || type == ConcurrentLinkedQueue.class || type == ConcurrentSkipListSet.class || type == LinkedHashSet.class || type == HashSet.class || type == TreeSet.class || type == CopyOnWriteArrayList.class) {
            return ObjectReaderImplList.of(type, null, 0L);
        }
        if (type == ObjectReaderImplList.CLASS_EMPTY_SET || type == ObjectReaderImplList.CLASS_EMPTY_LIST || type == ObjectReaderImplList.CLASS_SINGLETON || type == ObjectReaderImplList.CLASS_SINGLETON_LIST || type == ObjectReaderImplList.CLASS_ARRAYS_LIST || type == ObjectReaderImplList.CLASS_UNMODIFIABLE_COLLECTION || type == ObjectReaderImplList.CLASS_UNMODIFIABLE_LIST || type == ObjectReaderImplList.CLASS_UNMODIFIABLE_SET || type == ObjectReaderImplList.CLASS_UNMODIFIABLE_SORTED_SET || type == ObjectReaderImplList.CLASS_UNMODIFIABLE_NAVIGABLE_SET) {
            return ObjectReaderImplList.of(type, null, 0L);
        }
        if (type == TypeUtils.CLASS_SINGLE_SET) {
            return ObjectReaderImplList.of(type, null, 0L);
        }
        if (type == Object.class || type == Cloneable.class || type == Closeable.class || type == Serializable.class || type == Comparable.class) {
            return ObjectReaderImplObject.INSTANCE;
        }
        if (type == Map.Entry.class) {
            return new ObjectReaderImplMapEntry(null, null);
        }
        if (type instanceof Class) {
            Class cls2 = (Class) type;
            if (BeanUtils.isExtendedMap(cls2)) {
                return null;
            }
            if (Map.class.isAssignableFrom(cls2)) {
                return ObjectReaderImplMap.of(null, cls2, 0L);
            }
            if (Collection.class.isAssignableFrom(cls2)) {
                return ObjectReaderImplList.of(cls2, cls2, 0L);
            }
            if (cls2.isArray()) {
                if (cls2.getComponentType() == Object.class) {
                    return ObjectArrayReader.INSTANCE;
                }
                return new ObjectArrayTypedReader(cls2);
            }
            if (JSONPObject.class.isAssignableFrom(cls2)) {
                return new ObjectReaderImplJSONP(cls2);
            }
            ObjectReaderCreator creator = JSONFactory.getDefaultObjectReaderProvider().getCreator();
            if (cls2 == StackTraceElement.class) {
                try {
                    return creator.createObjectReaderNoneDefaultConstructor(cls2.getConstructor(String.class, String.class, String.class, Integer.TYPE), "className", "methodName", "fileName", "lineNumber");
                } catch (Throwable unused) {
                }
            }
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                Type type2 = actualTypeArguments[0];
                Type type3 = actualTypeArguments[1];
                if (rawType == Map.class || rawType == AbstractMap.class || rawType == HashMap.class) {
                    return typedMap((Class) rawType, HashMap.class, type2, type3);
                }
                if (rawType == ConcurrentMap.class || rawType == ConcurrentHashMap.class) {
                    return typedMap((Class) rawType, ConcurrentHashMap.class, type2, type3);
                }
                if (rawType == ConcurrentNavigableMap.class || rawType == ConcurrentSkipListMap.class) {
                    return typedMap((Class) rawType, ConcurrentSkipListMap.class, type2, type3);
                }
                if (rawType == LinkedHashMap.class || rawType == TreeMap.class || rawType == Hashtable.class) {
                    Class cls3 = (Class) rawType;
                    return typedMap(cls3, cls3, type2, type3);
                }
                if (rawType == Map.Entry.class) {
                    return new ObjectReaderImplMapEntry(type2, type3);
                }
                String typeName2 = rawType.getTypeName();
                typeName2.hashCode();
                switch (typeName2.hashCode()) {
                    case -1693810977:
                        if (typeName2.equals("com.google.common.collect.SingletonImmutableBiMap")) {
                            c4 = 0;
                            break;
                        }
                        c4 = 65535;
                        break;
                    case -166181530:
                        if (typeName2.equals("org.springframework.util.LinkedMultiValueMap")) {
                            c4 = 1;
                            break;
                        }
                        c4 = 65535;
                        break;
                    case -137241147:
                        if (typeName2.equals("org.apache.commons.lang3.tuple.Pair")) {
                            c4 = 2;
                            break;
                        }
                        c4 = 65535;
                        break;
                    case 905744473:
                        if (typeName2.equals("com.google.common.collect.ImmutableMap")) {
                            c4 = 3;
                            break;
                        }
                        c4 = 65535;
                        break;
                    case 924843249:
                        if (typeName2.equals("org.apache.commons.lang3.tuple.ImmutablePair")) {
                            c4 = 4;
                            break;
                        }
                        c4 = 65535;
                        break;
                    case 2035427703:
                        if (typeName2.equals("com.google.common.collect.RegularImmutableMap")) {
                            c4 = 5;
                            break;
                        }
                        c4 = 65535;
                        break;
                    default:
                        c4 = 65535;
                        break;
                }
                switch (c4) {
                    case 0:
                        return new ObjectReaderImplMapTyped((Class) rawType, HashMap.class, type2, type3, 0L, GuavaSupport.singletonBiMapConverter());
                    case 1:
                        return ObjectReaderImplMap.of(type, (Class) rawType, 0L);
                    case 2:
                    case 4:
                        return new ApacheLang3Support.PairReader((Class) rawType, type2, type3);
                    case 3:
                    case 5:
                        return new ObjectReaderImplMapTyped((Class) rawType, HashMap.class, type2, type3, 0L, GuavaSupport.immutableMapConverter());
                    default:
                        return null;
                }
            }
            if (actualTypeArguments.length != 1) {
                return null;
            }
            Type type4 = actualTypeArguments[0];
            Class<?> mapping = TypeUtils.getMapping(type4);
            if (rawType == Iterable.class || rawType == Collection.class || rawType == List.class || rawType == AbstractCollection.class || rawType == AbstractList.class || rawType == ArrayList.class) {
                if (mapping == String.class) {
                    return new ObjectReaderImplListStr((Class) rawType, ArrayList.class);
                }
                if (mapping == Long.class) {
                    return new ObjectReaderImplListInt64((Class) rawType, ArrayList.class);
                }
                return ObjectReaderImplList.of(type, null, 0L);
            }
            if (rawType == Queue.class || rawType == Deque.class || rawType == AbstractSequentialList.class || rawType == LinkedList.class) {
                if (mapping == String.class) {
                    return new ObjectReaderImplListStr((Class) rawType, LinkedList.class);
                }
                if (mapping == Long.class) {
                    return new ObjectReaderImplListInt64((Class) rawType, LinkedList.class);
                }
                return ObjectReaderImplList.of(type, null, 0L);
            }
            if (rawType == Set.class || rawType == AbstractSet.class || rawType == EnumSet.class) {
                if (mapping == String.class) {
                    return new ObjectReaderImplListStr((Class) rawType, HashSet.class);
                }
                if (mapping == Long.class) {
                    return new ObjectReaderImplListInt64((Class) rawType, HashSet.class);
                }
                return ObjectReaderImplList.of(type, null, 0L);
            }
            if (rawType == NavigableSet.class || rawType == SortedSet.class) {
                if (type4 == String.class) {
                    return new ObjectReaderImplListStr((Class) rawType, TreeSet.class);
                }
                if (mapping == Long.class) {
                    return new ObjectReaderImplListInt64((Class) rawType, TreeSet.class);
                }
                return ObjectReaderImplList.of(type, null, 0L);
            }
            if (rawType == ConcurrentLinkedDeque.class || rawType == ConcurrentLinkedQueue.class || rawType == ConcurrentSkipListSet.class || rawType == LinkedHashSet.class || rawType == HashSet.class || rawType == TreeSet.class || rawType == CopyOnWriteArrayList.class) {
                if (type4 == String.class) {
                    Class cls4 = (Class) rawType;
                    return new ObjectReaderImplListStr(cls4, cls4);
                }
                if (mapping == Long.class) {
                    Class cls5 = (Class) rawType;
                    return new ObjectReaderImplListInt64(cls5, cls5);
                }
                return ObjectReaderImplList.of(type, null, 0L);
            }
            String typeName3 = rawType.getTypeName();
            typeName3.hashCode();
            switch (typeName3.hashCode()) {
                case -1986714303:
                    if (typeName3.equals("com.google.common.collect.ImmutableList")) {
                        c3 = 0;
                        break;
                    }
                    c3 = 65535;
                    break;
                case -681075156:
                    if (typeName3.equals("com.google.common.collect.SingletonImmutableSet")) {
                        c3 = 1;
                        break;
                    }
                    c3 = 65535;
                    break;
                case 572464727:
                    if (typeName3.equals("cn.hutool.core.lang.tree.Tree")) {
                        c3 = 2;
                        break;
                    }
                    c3 = 65535;
                    break;
                case 905750367:
                    if (typeName3.equals("com.google.common.collect.ImmutableSet")) {
                        c3 = 3;
                        break;
                    }
                    c3 = 65535;
                    break;
                default:
                    c3 = 65535;
                    break;
            }
            switch (c3) {
                case 0:
                case 1:
                case 3:
                    return ObjectReaderImplList.of(type, null, 0L);
                case 2:
                    return ObjectReaderImplMap.of(null, (Class) rawType, 0L);
                default:
                    if (rawType == Optional.class) {
                        return ObjectReaderImplOptional.of(type, null, null);
                    }
                    if (rawType == AtomicReference.class) {
                        return new ObjectReaderImplAtomicReference(type4);
                    }
                    if (type4 instanceof WildcardType) {
                        return getObjectReader(objectReaderProvider, rawType);
                    }
                    return null;
            }
        }
        if (type instanceof GenericArrayType) {
            return new ObjectReaderImplGenericArray((GenericArrayType) type);
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getObjectReader(objectReaderProvider, upperBounds[0]);
            }
        }
        if (type == ParameterizedType.class) {
            return ObjectReaders.ofReflect(ParameterizedTypeImpl.class);
        }
        typeName.hashCode();
        switch (typeName.hashCode()) {
            case -2088293497:
                if (typeName.equals("java.awt.Color")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -2050985813:
                if (typeName.equals("java.lang.RuntimeException")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -2010664371:
                if (typeName.equals("java.io.IOException")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1986714303:
                if (typeName.equals("com.google.common.collect.ImmutableList")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1976644094:
                if (typeName.equals("javax.money.NumberValue")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1922416486:
                if (typeName.equals("org.joda.time.LocalDate")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1757049669:
                if (typeName.equals("com.carrotsearch.hppc.LongHashSet")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1682705914:
                if (typeName.equals("gnu.trove.set.hash.TShortHashSet")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1670613343:
                if (typeName.equals("com.carrotsearch.hppc.CharHashSet")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1492703689:
                if (typeName.equals("java.io.UncheckedIOException")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1410806254:
                if (typeName.equals("java.util.JumboEnumSet")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1264798181:
                if (typeName.equals("javax.money.CurrencyUnit")) {
                    c2 = 11;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -864935548:
                if (typeName.equals("com.carrotsearch.hppc.CharArrayList")) {
                    c2 = '\f';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -848095899:
                if (typeName.equals("com.carrotsearch.hppc.IntArrayList")) {
                    c2 = '\r';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -808573634:
                if (typeName.equals("gnu.trove.list.array.TLongArrayList")) {
                    c2 = 14;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -720123389:
                if (typeName.equals("java.net.InetAddress")) {
                    c2 = 15;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -681075156:
                if (typeName.equals("com.google.common.collect.SingletonImmutableSet")) {
                    c2 = 16;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -561799942:
                if (typeName.equals("java.nio.HeapByteBuffer")) {
                    c2 = 17;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -547316498:
                if (typeName.equals("java.nio.ByteBuffer")) {
                    c2 = 18;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -448666600:
                if (typeName.equals("gnu.trove.list.array.TShortArrayList")) {
                    c2 = 19;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -342082893:
                if (typeName.equals("gnu.trove.set.hash.TIntHashSet")) {
                    c2 = 20;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -240096200:
                if (typeName.equals("com.carrotsearch.hppc.ShortArrayList")) {
                    c2 = 21;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -137241147:
                if (typeName.equals("org.apache.commons.lang3.tuple.Pair")) {
                    c2 = 22;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -127813975:
                if (typeName.equals("com.carrotsearch.hppc.DoubleArrayList")) {
                    c2 = 23;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -715518:
                if (typeName.equals("org.joda.time.Instant")) {
                    c2 = 24;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 72706427:
                if (typeName.equals("java.lang.Exception")) {
                    c2 = 25;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 75599616:
                if (typeName.equals("java.lang.IllegalStateException")) {
                    c2 = JSONLexer.EOI;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 100244498:
                if (typeName.equals("com.carrotsearch.hppc.ByteArrayList")) {
                    c2 = 27;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 217956074:
                if (typeName.equals("gnu.trove.set.hash.TLongHashSet")) {
                    c2 = 28;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 652357028:
                if (typeName.equals("gnu.trove.list.array.TCharArrayList")) {
                    c2 = 29;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 905750367:
                if (typeName.equals("com.google.common.collect.ImmutableSet")) {
                    c2 = 30;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 924843249:
                if (typeName.equals("org.apache.commons.lang3.tuple.ImmutablePair")) {
                    c2 = 31;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1087757882:
                if (typeName.equals("java.sql.Date")) {
                    c2 = ' ';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1088242009:
                if (typeName.equals("java.sql.Time")) {
                    c2 = '!';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1138418232:
                if (typeName.equals("gnu.trove.list.array.TFloatArrayList")) {
                    c2 = Typography.quote;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1215440026:
                if (typeName.equals("org.joda.time.DateTime")) {
                    c2 = '#';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1247089131:
                if (typeName.equals("javax.money.MonetaryAmount")) {
                    c2 = Typography.dollar;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1252880906:
                if (typeName.equals("java.sql.Timestamp")) {
                    c2 = '%';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1346988632:
                if (typeName.equals("com.carrotsearch.hppc.FloatArrayList")) {
                    c2 = Typography.amp;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1395322562:
                if (typeName.equals("com.carrotsearch.hppc.IntHashSet")) {
                    c2 = '\'';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1458317959:
                if (typeName.equals("org.joda.time.LocalDateTime")) {
                    c2 = '(';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1527725683:
                if (typeName.equals("com.google.common.collect.AbstractMapBasedMultimap$RandomAccessWrappedList")) {
                    c2 = ')';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1539653772:
                if (typeName.equals("java.text.SimpleDateFormat")) {
                    c2 = '*';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1556153669:
                if (typeName.equals("gnu.trove.list.array.TIntArrayList")) {
                    c2 = '+';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1585284048:
                if (typeName.equals("java.net.InetSocketAddress")) {
                    c2 = ',';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1617537074:
                if (typeName.equals("gnu.trove.list.array.TByteArrayList")) {
                    c2 = '-';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1630335596:
                if (typeName.equals("java.lang.Throwable")) {
                    c2 = '.';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1643140783:
                if (typeName.equals("org.bson.types.Decimal128")) {
                    c2 = '/';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1683410586:
                if (typeName.equals("javax.money.Money")) {
                    c2 = '0';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1829465637:
                if (typeName.equals("java.util.RegularEnumSet")) {
                    c2 = '1';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1891987166:
                if (typeName.equals("gnu.trove.set.hash.TByteHashSet")) {
                    c2 = '2';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1969101086:
                if (typeName.equals("com.carrotsearch.hppc.LongArrayList")) {
                    c2 = '3';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 1996438217:
                if (typeName.equals("gnu.trove.list.array.TDoubleArrayList")) {
                    c2 = '4';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 2035433597:
                if (typeName.equals("com.google.common.collect.RegularImmutableSet")) {
                    c2 = '5';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 2080325655:
                if (typeName.equals("org.joda.time.Chronology")) {
                    c2 = '6';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                try {
                    return ObjectReaderCreator.INSTANCE.createObjectReaderNoneDefaultConstructor(((Class) type).getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE), "r", "g", "b", "alpha");
                } catch (Throwable unused2) {
                    return null;
                }
            case 1:
            case 2:
            case '\t':
            case 25:
            case 26:
            case '.':
                return new ObjectReaderException((Class) type);
            case 3:
            case 16:
            case 30:
            case ')':
            case '5':
                return ObjectReaderImplList.of(type, null, 0L);
            case 4:
                return MoneySupport.createNumberValueReader();
            case 5:
                return JodaSupport.createLocalDateReader((Class) type);
            case 6:
            case 7:
            case '\b':
            case '\f':
            case '\r':
            case 14:
            case 19:
            case 20:
            case 21:
            case 23:
            case 27:
            case 28:
            case 29:
            case '\"':
            case '&':
            case '\'':
            case '+':
            case '-':
            case '/':
            case '2':
            case '3':
            case '4':
                return LambdaMiscCodec.getObjectReader((Class) type);
            case '\n':
            case '1':
                return ObjectReaderImplList.of(type, TypeUtils.getClass(type), 0L);
            case 11:
                return MoneySupport.createCurrencyUnitReader();
            case 15:
                return ObjectReaderImplValue.of((Class) type, String.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda4
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ObjectReaderBaseModule.lambda$getObjectReader$10((String) obj);
                    }
                });
            case 17:
            case 18:
                return new ObjectReaderImplInt8ValueArray(new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda6
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        ByteBuffer wrap;
                        wrap = ByteBuffer.wrap((byte[]) obj);
                        return wrap;
                    }
                }, null);
            case 22:
            case 31:
                return new ApacheLang3Support.PairReader((Class) type, Object.class, Object.class);
            case 24:
                return JodaSupport.createInstantReader((Class) type);
            case ' ':
                return JdbcSupport.createDateReader((Class) type, null, null);
            case '!':
                return JdbcSupport.createTimeReader((Class) type, null, null);
            case '#':
                return new ObjectReaderImplZonedDateTime(new JodaSupport.DateTimeFromZDT());
            case '$':
            case '0':
                return MoneySupport.createMonetaryAmountReader();
            case '%':
                return JdbcSupport.createTimestampReader((Class) type, null, null);
            case '(':
                return JodaSupport.createLocalDateTimeReader((Class) type);
            case '*':
                return ObjectReaderImplValue.of((Class) type, String.class, new Function() { // from class: com.alibaba.fastjson2.reader.ObjectReaderBaseModule$$ExternalSyntheticLambda5
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ObjectReaderBaseModule.$r8$lambda$b3Lmh_nz6Ow94jVYodnZkoZlIXY((String) obj);
                    }
                });
            case ',':
                return new ObjectReaderMisc((Class) type);
            case '6':
                return JodaSupport.createChronologyReader((Class) type);
            default:
                return null;
        }
    }

    static /* synthetic */ URL lambda$getObjectReader$9(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new JSONException("read URL error", e);
        }
    }

    static /* synthetic */ InetAddress lambda$getObjectReader$10(String str) {
        try {
            return InetAddress.getByName(str);
        } catch (UnknownHostException e) {
            throw new JSONException("create address error", e);
        }
    }

    public static ObjectReader typedMap(Class cls, Class cls2, Type type, Type type2) {
        if ((type == null || type == String.class) && type2 == String.class) {
            return new ObjectReaderImplMapString(cls, cls2, 0L);
        }
        return new ObjectReaderImplMapTyped(cls, cls2, type, type2, 0L, null);
    }
}
