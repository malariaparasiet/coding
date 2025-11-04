package com.alibaba.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.alibaba.fastjson.serializer.LabelFilter;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.PropertyFilter;
import com.alibaba.fastjson2.filter.PropertyPreFilter;
import com.alibaba.fastjson2.filter.ValueFilter;
import com.alibaba.fastjson2.reader.ObjectReaderProvider;
import com.alibaba.fastjson2.support.AwtRederModule;
import com.alibaba.fastjson2.support.AwtWriterModule;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.ParameterizedTypeImpl;
import com.alibaba.fastjson2.writer.ObjectWriterProvider;
import com.wifiled.baselib.utils.DateUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public abstract class JSON implements JSONAware {
    static final Cache CACHE;
    static final AtomicReferenceFieldUpdater<Cache, char[]> CHARS_UPDATER;
    public static String DEFAULT_DATE_FORMAT = null;
    public static int DEFAULT_GENERATE_FEATURE = 0;
    public static int DEFAULT_PARSER_FEATURE = 0;
    private static final TimeZone DEFAULT_TIME_ZONE;
    public static String DEFAULT_TYPE_KEY = null;

    @Deprecated
    public static String DEFFAULT_DATE_FORMAT = null;
    private static final int MAX_LEVEL = 2048;
    public static final String VERSION = "2.0.58";
    static final Supplier<List> arraySupplier;
    public static Locale defaultLocale;
    static final Supplier<Map> defaultSupplier;
    public static TimeZone defaultTimeZone;
    static final Supplier<Map> orderedSupplier;

    public abstract <T> T toJavaObject(Class<T> cls);

    public abstract <T> T toJavaObject(Type type);

    static {
        TimeZone timeZone = TimeZone.getDefault();
        DEFAULT_TIME_ZONE = timeZone;
        CACHE = new Cache();
        CHARS_UPDATER = AtomicReferenceFieldUpdater.newUpdater(Cache.class, char[].class, "chars");
        defaultTimeZone = timeZone;
        defaultLocale = Locale.getDefault();
        DEFAULT_TYPE_KEY = "@type";
        DEFFAULT_DATE_FORMAT = DateUtils.TIME_FORMAT;
        DEFAULT_DATE_FORMAT = DateUtils.TIME_FORMAT;
        DEFAULT_PARSER_FEATURE = Feature.AutoCloseSource.getMask() | Feature.InternFieldNames.getMask() | Feature.UseBigDecimal.getMask() | Feature.AllowUnQuotedFieldNames.getMask() | Feature.AllowSingleQuotes.getMask() | Feature.AllowArbitraryCommas.getMask() | Feature.SortFeidFastMatch.getMask() | Feature.IgnoreNotMatch.getMask();
        DEFAULT_GENERATE_FEATURE = SerializerFeature.QuoteFieldNames.getMask() | SerializerFeature.SkipTransientField.getMask() | SerializerFeature.WriteEnumUsingName.getMask() | SerializerFeature.SortField.getMask();
        arraySupplier = new Supplier() { // from class: com.alibaba.fastjson.JSON$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return new JSONArray();
            }
        };
        defaultSupplier = JSONObject.Creator.INSTANCE;
        orderedSupplier = new Supplier() { // from class: com.alibaba.fastjson.JSON$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return JSON.lambda$static$0();
            }
        };
        boolean z = JDKUtils.ANDROID;
        ObjectReaderProvider defaultObjectReaderProvider = JSONFactory.getDefaultObjectReaderProvider();
        if (!z) {
            defaultObjectReaderProvider.register(AwtRederModule.INSTANCE);
        }
        defaultObjectReaderProvider.register(new Fastjson1xReaderModule(defaultObjectReaderProvider));
        ObjectWriterProvider objectWriterProvider = SerializeConfig.DEFAULT_PROVIDER;
        if (!z) {
            objectWriterProvider.register(AwtWriterModule.INSTANCE);
        }
        objectWriterProvider.register(new Fastjson1xWriterModule(objectWriterProvider));
    }

    static /* synthetic */ Map lambda$static$0() {
        return new JSONObject(true);
    }

    public static JSONReader.Context createReadContext(int i, Feature... featureArr) {
        return createReadContext(JSONFactory.getDefaultObjectReaderProvider(), i, featureArr);
    }

    public static JSONReader.Context createReadContext(ObjectReaderProvider objectReaderProvider, int i, Feature... featureArr) {
        Supplier<Map> supplier;
        for (Feature feature : featureArr) {
            i |= feature.mask;
        }
        JSONReader.Context context = new JSONReader.Context(objectReaderProvider);
        if ((Feature.UseBigDecimal.mask & i) == 0) {
            context.config(JSONReader.Feature.UseBigDecimalForDoubles);
        }
        if ((Feature.SupportArrayToBean.mask & i) != 0) {
            context.config(JSONReader.Feature.SupportArrayToBean);
        }
        if ((Feature.ErrorOnEnumNotMatch.mask & i) != 0) {
            context.config(JSONReader.Feature.ErrorOnEnumNotMatch);
        }
        if ((Feature.SupportNonPublicField.mask & i) != 0) {
            context.config(JSONReader.Feature.FieldBased);
        }
        if ((Feature.SupportClassForName.mask & i) != 0) {
            context.config(JSONReader.Feature.SupportClassForName);
        }
        if ((Feature.TrimStringFieldValue.mask & i) != 0) {
            context.config(JSONReader.Feature.TrimString);
        }
        if ((Feature.ErrorOnNotSupportAutoType.mask & i) != 0) {
            context.config(JSONReader.Feature.ErrorOnNotSupportAutoType);
        }
        if ((Feature.AllowUnQuotedFieldNames.mask & i) != 0) {
            context.config(JSONReader.Feature.AllowUnQuotedFieldNames);
        }
        if ((Feature.UseNativeJavaObject.mask & i) != 0) {
            context.config(JSONReader.Feature.UseNativeObject);
        } else {
            context.setArraySupplier(arraySupplier);
            if ((Feature.OrderedField.mask & i) != 0) {
                supplier = orderedSupplier;
            } else {
                supplier = defaultSupplier;
            }
            context.setObjectSupplier(supplier);
        }
        if ((Feature.NonStringKeyAsString.mask & i) != 0) {
            context.config(JSONReader.Feature.NonStringKeyAsString);
        }
        if ((Feature.DisableFieldSmartMatch.mask & i) == 0) {
            context.config(JSONReader.Feature.SupportSmartMatch);
        }
        if ((Feature.SupportAutoType.mask & i) != 0) {
            context.config(JSONReader.Feature.SupportAutoType);
        }
        String str = DEFAULT_DATE_FORMAT;
        if (!DateUtils.TIME_FORMAT.equals(str)) {
            context.setDateFormat(str);
        }
        context.config(JSONReader.Feature.Base64StringAsByteArray);
        return context;
    }

    public static JSONObject parseObject(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, new Feature[0]));
        try {
            if (of.nextIfNullOrEmptyString()) {
                return null;
            }
            HashMap hashMap = new HashMap();
            of.read(hashMap, 0L);
            JSONObject jSONObject = new JSONObject(hashMap);
            of.handleResolveTasks(jSONObject);
            if (of.isEnd()) {
                return jSONObject;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static JSONObject parseObject(String str, Feature... featureArr) {
        Map hashMap;
        if (str == null || str.isEmpty()) {
            return null;
        }
        JSONReader.Context createReadContext = createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr);
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext);
        String str2 = DEFAULT_DATE_FORMAT;
        if (!DateUtils.TIME_FORMAT.equals(str2)) {
            createReadContext.setDateFormat(str2);
        }
        int length = featureArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                hashMap = new HashMap();
                break;
            }
            if (featureArr[i] == Feature.OrderedField) {
                hashMap = new LinkedHashMap();
                break;
            }
            try {
                i++;
            } catch (com.alibaba.fastjson2.JSONException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    cause = e;
                }
                throw new JSONException(e.getMessage(), cause);
            }
        }
        of.read(hashMap, 0L);
        JSONObject jSONObject = new JSONObject((Map<String, Object>) hashMap);
        of.handleResolveTasks(jSONObject);
        if (of.isEnd()) {
            return jSONObject;
        }
        throw new JSONException(of.info("input not end"));
    }

    public static <T> T parseObject(byte[] bArr, Charset charset, Type type, ParserConfig parserConfig, ParseProcess parseProcess, int i, Feature... featureArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        if (parserConfig == null) {
            parserConfig = ParserConfig.global;
        }
        JSONReader.Context createReadContext = createReadContext(parserConfig.getProvider(), i, featureArr);
        if (parseProcess != null) {
            createReadContext.config(parseProcess);
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(bArr, 0, bArr.length, charset, createReadContext);
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(byte[] bArr, int i, int i2, Charset charset, Type type, ParserConfig parserConfig, ParseProcess parseProcess, int i3, Feature... featureArr) {
        if (bArr == null || bArr.length == 0 || i2 == 0) {
            return null;
        }
        if (parserConfig == null) {
            parserConfig = ParserConfig.global;
        }
        JSONReader.Context createReadContext = createReadContext(parserConfig.getProvider(), i3, featureArr);
        if (parseProcess != null) {
            createReadContext.config(parseProcess);
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(bArr, i, i2, charset, createReadContext);
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(char[] cArr, int i, Type type, Feature... featureArr) {
        if (cArr == null || cArr.length == 0) {
            return null;
        }
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(cArr, 0, i, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
        try {
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(char[] cArr, Class<T> cls, Feature... featureArr) {
        if (cArr == null || cArr.length == 0) {
            return null;
        }
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(cArr, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
        try {
            T t = (T) of.getObjectReader(cls).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(String str, Type type, ParserConfig parserConfig, ParseProcess parseProcess, int i, Feature... featureArr) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (parserConfig == null) {
            parserConfig = ParserConfig.global;
        }
        JSONReader.Context createReadContext = createReadContext(parserConfig.getProvider(), i, featureArr);
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext);
        createReadContext.config(parseProcess);
        try {
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(String str, Type type, ParserConfig parserConfig, int i, Feature... featureArr) {
        return (T) parseObject(str, type, parserConfig, (ParseProcess) null, i, featureArr);
    }

    public static <T> T parseObject(String str, Type type, ParseProcess parseProcess, Feature... featureArr) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        JSONReader.Context createReadContext = createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr);
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext);
        createReadContext.config(parseProcess);
        try {
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(String str, Class<T> cls, ParseProcess parseProcess, Feature... featureArr) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        JSONReader.Context createReadContext = createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr);
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext);
        createReadContext.config(parseProcess);
        try {
            T t = (T) of.getObjectReader(cls).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(String str, TypeReference<T> typeReference, Feature... featureArr) {
        return (T) parseObject(str, typeReference != null ? typeReference.getType() : Object.class, featureArr);
    }

    public static <T> T parseObject(String str, Type type, int i, Feature... featureArr) {
        return (T) parseObject(str, type, ParserConfig.global, i, featureArr);
    }

    public static <T> T parseObject(String str, Class<T> cls) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, new Feature[0]));
        try {
            T t = (T) of.getObjectReader(cls).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(String str, Class<T> cls, Feature... featureArr) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
        try {
            T t = (T) of.getObjectReader(cls).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(String str, Type type, Feature... featureArr) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
        try {
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(String str, Type type, ParserConfig parserConfig, Feature... featureArr) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        JSONReader.Context createReadContext = createReadContext(parserConfig.getProvider(), DEFAULT_PARSER_FEATURE, featureArr);
        if (parserConfig.fieldBase) {
            createReadContext.config(JSONReader.Feature.FieldBased);
        }
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext);
        try {
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(InputStream inputStream, Type type, Feature... featureArr) throws IOException {
        return (T) parseObject(inputStream, StandardCharsets.UTF_8, type, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Class<T> cls, Feature... featureArr) throws IOException {
        return (T) parseObject(inputStream, StandardCharsets.UTF_8, cls, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, ParserConfig parserConfig, ParseProcess parseProcess, int i, Feature... featureArr) throws IOException {
        if (inputStream == null) {
            return null;
        }
        if (parserConfig == null) {
            parserConfig = ParserConfig.global;
        }
        JSONReader.Context createReadContext = createReadContext(parserConfig.getProvider(), i, featureArr);
        if (parseProcess != null) {
            createReadContext.config(parseProcess);
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(inputStream, charset, createReadContext);
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, ParserConfig parserConfig, Feature... featureArr) throws IOException {
        return (T) parseObject(inputStream, charset, type, parserConfig, (ParseProcess) null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, Feature... featureArr) throws IOException {
        if (inputStream == null) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(inputStream, charset, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static JSONObject parseObject(byte[] bArr, Feature... featureArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        JSONReader.Context createReadContext = createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr);
        boolean z = false;
        for (Feature feature : featureArr) {
            if (feature == Feature.OrderedField) {
                z = true;
                break;
            }
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(bArr, createReadContext);
            Map linkedHashMap = z ? new LinkedHashMap() : new HashMap();
            of.read(linkedHashMap, 0L);
            JSONObject jSONObject = new JSONObject((Map<String, Object>) linkedHashMap);
            of.handleResolveTasks(jSONObject);
            if (of.isEnd()) {
                return jSONObject;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(byte[] bArr, Class<T> cls, Feature... featureArr) {
        if (bArr == null) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(bArr, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
            T t = (T) of.getObjectReader(cls).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(byte[] bArr, Type type, Feature... featureArr) {
        if (bArr == null) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(bArr, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(byte[] bArr, Type type, JSONReader.Context context) {
        if (bArr == null) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(bArr, context);
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> T parseObject(byte[] bArr, Type type, SerializeFilter serializeFilter, Feature... featureArr) {
        if (bArr == null) {
            return null;
        }
        JSONReader.Context createReadContext = createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr);
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(bArr, createReadContext);
            if (serializeFilter != null) {
                createReadContext.config(serializeFilter);
            }
            T t = (T) of.getObjectReader(type).readObject(of, null, null, 0L);
            if (t != null) {
                of.handleResolveTasks(t);
            }
            if (of.isEnd()) {
                return t;
            }
            throw new JSONException(of.info("input not end"));
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static Object parse(String str) {
        Object readAny;
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, new Feature[0]));
            try {
                if (of.isObject() && !of.isSupportAutoType(0L)) {
                    readAny = of.read((Class<Object>) JSONObject.class);
                } else {
                    readAny = of.readAny();
                }
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return readAny;
            } finally {
            }
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static Object parse(String str, int i) {
        return parse(str, ParserConfig.global, i);
    }

    public static Object parse(String str, Feature... featureArr) {
        Object readAny;
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
            try {
                if (of.isObject() && !of.isSupportAutoType(0L)) {
                    readAny = of.read((Class<Object>) JSONObject.class);
                } else {
                    readAny = of.readAny();
                }
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return readAny;
            } finally {
            }
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static Object parse(String str, ParserConfig parserConfig, Feature... featureArr) {
        Object read;
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(parserConfig.getProvider(), DEFAULT_PARSER_FEATURE, featureArr));
            try {
                if (of.isObject() && !of.isSupportAutoType(0L)) {
                    read = of.read((Class<Object>) JSONObject.class);
                } else {
                    read = of.read((Class<Object>) Object.class);
                }
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return read;
            } finally {
            }
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static Object parse(String str, ParserConfig parserConfig) {
        Object readAny;
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(parserConfig.getProvider(), DEFAULT_PARSER_FEATURE, new Feature[0]));
            try {
                if (of.isObject() && !of.isSupportAutoType(0L)) {
                    readAny = of.read((Class<Object>) JSONObject.class);
                } else {
                    readAny = of.readAny();
                }
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return readAny;
            } finally {
            }
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static Object parse(String str, ParserConfig parserConfig, int i) {
        Object readAny;
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(parserConfig.getProvider(), i, new Feature[0]));
            try {
                if (of.isObject() && !of.isSupportAutoType(0L)) {
                    readAny = of.read((Class<Object>) JSONObject.class);
                } else {
                    readAny = of.readAny();
                }
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return readAny;
            } finally {
            }
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static Object parse(byte[] bArr, Feature... featureArr) {
        Object readAny;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(bArr, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
            try {
                if (of.isObject() && !of.isSupportAutoType(0L)) {
                    readAny = of.read((Class<Object>) JSONObject.class);
                } else {
                    readAny = of.readAny();
                }
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return readAny;
            } finally {
            }
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static Object parse(byte[] bArr, int i, int i2, CharsetDecoder charsetDecoder, Feature... featureArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int i3 = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            i3 = Feature.config(i3, feature, true);
        }
        return parse(bArr, i, i2, charsetDecoder, i3);
    }

    public static Object parse(byte[] bArr, int i, int i2, CharsetDecoder charsetDecoder, int i3) {
        charsetDecoder.reset();
        int maxCharsPerByte = (int) (i2 * charsetDecoder.maxCharsPerByte());
        char[] andSet = CHARS_UPDATER.getAndSet(CACHE, null);
        if (andSet == null || andSet.length < maxCharsPerByte) {
            andSet = new char[maxCharsPerByte];
        }
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr, i, i2);
            CharBuffer wrap2 = CharBuffer.wrap(andSet);
            IOUtils.decode(charsetDecoder, wrap, wrap2);
            int position = wrap2.position();
            JSONReader.Context createReadContext = createReadContext(JSONFactory.getDefaultObjectReaderProvider(), i3, new Feature[0]);
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(andSet, 0, position, createReadContext);
            for (Feature feature : Feature.values()) {
                if ((feature.mask & i3) != 0) {
                    int i4 = AnonymousClass1.$SwitchMap$com$alibaba$fastjson$parser$Feature[feature.ordinal()];
                    if (i4 == 1) {
                        createReadContext.config(JSONReader.Feature.SupportArrayToBean);
                    } else if (i4 == 2) {
                        createReadContext.config(JSONReader.Feature.SupportAutoType);
                    } else {
                        if (i4 == 3) {
                            createReadContext.config(JSONReader.Feature.ErrorOnEnumNotMatch);
                        } else if (i4 != 4) {
                        }
                        createReadContext.config(JSONReader.Feature.FieldBased);
                    }
                }
            }
            Object read = of.read((Class<Object>) Object.class);
            if (read != null) {
                of.handleResolveTasks(read);
            }
            if (of.isEnd()) {
                return read;
            }
            throw new JSONException(of.info("input not end"));
        } finally {
            if (andSet.length <= 65536) {
                CHARS_UPDATER.set(CACHE, andSet);
            }
        }
    }

    /* renamed from: com.alibaba.fastjson.JSON$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$fastjson$parser$Feature;

        static {
            int[] iArr = new int[Feature.values().length];
            $SwitchMap$com$alibaba$fastjson$parser$Feature = iArr;
            try {
                iArr[Feature.SupportArrayToBean.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$parser$Feature[Feature.SupportAutoType.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$parser$Feature[Feature.ErrorOnEnumNotMatch.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$parser$Feature[Feature.SupportNonPublicField.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static <T> T parseObject(byte[] bArr, int i, int i2, CharsetDecoder charsetDecoder, Type type, Feature... featureArr) {
        charsetDecoder.reset();
        int maxCharsPerByte = (int) (i2 * charsetDecoder.maxCharsPerByte());
        AtomicReferenceFieldUpdater<Cache, char[]> atomicReferenceFieldUpdater = CHARS_UPDATER;
        Cache cache = CACHE;
        char[] andSet = atomicReferenceFieldUpdater.getAndSet(cache, null);
        if (andSet == null || andSet.length < maxCharsPerByte) {
            andSet = new char[maxCharsPerByte];
        }
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr, i, i2);
            CharBuffer wrap2 = CharBuffer.wrap(andSet);
            IOUtils.decode(charsetDecoder, wrap, wrap2);
            try {
                com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(andSet, 0, wrap2.position(), createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
                try {
                    T t = (T) of.read(type);
                    if (t != null) {
                        of.handleResolveTasks(t);
                    }
                    if (!of.isEnd()) {
                        throw new JSONException(of.info("input not end"));
                    }
                    if (of != null) {
                        of.close();
                    }
                    if (andSet.length <= 65536) {
                        atomicReferenceFieldUpdater.set(cache, andSet);
                    }
                    return t;
                } catch (Throwable th) {
                    if (of != null) {
                        try {
                            of.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (com.alibaba.fastjson2.JSONException e) {
                throw new JSONException(e.getMessage(), e.getCause() != null ? e.getCause() : e);
            }
        } catch (Throwable th3) {
            if (andSet.length <= 65536) {
                CHARS_UPDATER.set(CACHE, andSet);
            }
            throw th3;
        }
    }

    public static <T> T parseObject(byte[] bArr, int i, int i2, Charset charset, Type type, Feature... featureArr) {
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(bArr, i, i2, charset, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
            try {
                T t = (T) of.read(type);
                if (t != null) {
                    of.handleResolveTasks(t);
                }
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return t;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            throw new JSONException(e.getMessage(), e.getCause() != null ? e.getCause() : e);
        }
    }

    public static JSONWriter.Context createWriteContext(SerializeConfig serializeConfig, int i, SerializerFeature... serializerFeatureArr) {
        for (SerializerFeature serializerFeature : serializerFeatureArr) {
            i |= serializerFeature.mask;
        }
        ObjectWriterProvider provider = serializeConfig.getProvider();
        provider.setCompatibleWithFieldName(TypeUtils.compatibleWithFieldName);
        JSONWriter.Context context = new JSONWriter.Context(provider);
        if (serializeConfig.fieldBased) {
            context.config(JSONWriter.Feature.FieldBased);
        }
        if (serializeConfig.propertyNamingStrategy != null && serializeConfig.propertyNamingStrategy != PropertyNamingStrategy.NeverUseThisValueExceptDefaultValue && serializeConfig.propertyNamingStrategy != PropertyNamingStrategy.CamelCase1x) {
            configFilter(context, NameFilter.of(serializeConfig.propertyNamingStrategy));
        }
        if ((SerializerFeature.DisableCircularReferenceDetect.mask & i) == 0) {
            context.config(JSONWriter.Feature.ReferenceDetection);
        }
        if ((SerializerFeature.UseISO8601DateFormat.mask & i) != 0) {
            context.setDateFormat("iso8601");
        } else {
            context.setDateFormat("millis");
        }
        if ((SerializerFeature.WriteMapNullValue.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteMapNullValue);
        }
        if ((SerializerFeature.WriteNullListAsEmpty.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteNullListAsEmpty);
        }
        if ((SerializerFeature.WriteNullStringAsEmpty.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteNullStringAsEmpty);
        }
        if ((SerializerFeature.WriteNullNumberAsZero.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteNullNumberAsZero);
        }
        if ((SerializerFeature.WriteNullBooleanAsFalse.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteNullBooleanAsFalse);
        }
        if ((SerializerFeature.BrowserCompatible.mask & i) != 0) {
            context.config(JSONWriter.Feature.BrowserCompatible);
            context.config(JSONWriter.Feature.EscapeNoneAscii);
        }
        if ((SerializerFeature.BrowserSecure.mask & i) != 0) {
            context.config(JSONWriter.Feature.BrowserSecure);
        }
        if ((SerializerFeature.WriteClassName.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteClassName);
        }
        if ((SerializerFeature.WriteNonStringValueAsString.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteNonStringValueAsString);
        }
        if ((SerializerFeature.WriteEnumUsingToString.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteEnumUsingToString);
        }
        if ((SerializerFeature.WriteEnumUsingName.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteEnumsUsingName);
        }
        if ((SerializerFeature.NotWriteRootClassName.mask & i) != 0) {
            context.config(JSONWriter.Feature.NotWriteRootClassName);
        }
        if ((SerializerFeature.IgnoreErrorGetter.mask & i) != 0) {
            context.config(JSONWriter.Feature.IgnoreErrorGetter);
        }
        if ((SerializerFeature.WriteDateUseDateFormat.mask & i) != 0) {
            context.setDateFormat(DEFAULT_DATE_FORMAT);
        }
        if ((SerializerFeature.BeanToArray.mask & i) != 0) {
            context.config(JSONWriter.Feature.BeanToArray);
        }
        if ((SerializerFeature.UseSingleQuotes.mask & i) != 0) {
            context.config(JSONWriter.Feature.UseSingleQuotes);
        }
        if ((SerializerFeature.MapSortField.mask & i) != 0) {
            context.config(JSONWriter.Feature.MapSortField);
        }
        if ((SerializerFeature.PrettyFormat.mask & i) != 0) {
            context.config(JSONWriter.Feature.PrettyFormat);
        }
        if ((SerializerFeature.WriteNonStringKeyAsString.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteNonStringKeyAsString);
        }
        if ((SerializerFeature.IgnoreNonFieldGetter.mask & i) != 0) {
            context.config(JSONWriter.Feature.IgnoreNonFieldGetter);
        }
        if ((SerializerFeature.NotWriteDefaultValue.mask & i) != 0) {
            context.config(JSONWriter.Feature.NotWriteDefaultValue);
        }
        if ((SerializerFeature.WriteBigDecimalAsPlain.mask & i) != 0) {
            context.config(JSONWriter.Feature.WriteBigDecimalAsPlain);
        }
        if ((SerializerFeature.QuoteFieldNames.mask & i) == 0 && (SerializerFeature.UseSingleQuotes.mask & i) == 0) {
            context.config(JSONWriter.Feature.UnquoteFieldName);
        }
        TimeZone timeZone = defaultTimeZone;
        if (timeZone != null && timeZone != DEFAULT_TIME_ZONE) {
            context.setZoneId(timeZone.toZoneId());
        }
        context.config(JSONWriter.Feature.WriteByteArrayAsBase64);
        context.config(JSONWriter.Feature.WriteThrowableClassName);
        return context;
    }

    public static String toJSONString(Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, String str, int i, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(serializeConfig, i, serializerFeatureArr);
        if (str != null && !str.isEmpty()) {
            createWriteContext.setDateFormat(str);
        }
        try {
            com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
            try {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    configFilter(createWriteContext, serializeFilter);
                }
                if (obj == null) {
                    of.writeNull();
                } else {
                    of.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
                }
                String obj2 = of.toString();
                if (of != null) {
                    of.close();
                }
                return obj2;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONString error", e2);
        }
    }

    public static String toJSONString(Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, serializeConfig, serializeFilterArr, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
            try {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    configFilter(createWriteContext, serializeFilter);
                }
                if (obj == null) {
                    of.writeNull();
                } else {
                    of.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
                }
                String obj2 = of.toString();
                if (of != null) {
                    of.close();
                }
                return obj2;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONString error", e2);
        }
    }

    public static void configFilter(JSONWriter.Context context, SerializeFilter serializeFilter) {
        if (serializeFilter instanceof NameFilter) {
            context.setNameFilter((NameFilter) serializeFilter);
        }
        if (serializeFilter instanceof ValueFilter) {
            context.setValueFilter((ValueFilter) serializeFilter);
        }
        if (serializeFilter instanceof PropertyPreFilter) {
            context.setPropertyPreFilter((PropertyPreFilter) serializeFilter);
        }
        if (serializeFilter instanceof PropertyFilter) {
            context.setPropertyFilter((PropertyFilter) serializeFilter);
        }
        if (serializeFilter instanceof BeforeFilter) {
            context.setBeforeFilter((BeforeFilter) serializeFilter);
        }
        if (serializeFilter instanceof AfterFilter) {
            context.setAfterFilter((AfterFilter) serializeFilter);
        }
        if (serializeFilter instanceof LabelFilter) {
            context.setLabelFilter((LabelFilter) serializeFilter);
        }
        if (serializeFilter instanceof ContextValueFilter) {
            context.setContextValueFilter((ContextValueFilter) serializeFilter);
        }
    }

    public static byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, int i, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(serializeConfig, i, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    configFilter(createWriteContext, serializeFilter);
                }
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return bytes;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONBytes error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONBytes error", e2);
        }
    }

    public static byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(serializeConfig, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                configFilter(createWriteContext, serializeFilter);
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return bytes;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONBytes error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONBytes error", e2);
        }
    }

    public static byte[] toJSONBytes(Charset charset, Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, String str, int i, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(serializeConfig, i, serializerFeatureArr);
        if (str != null && !str.isEmpty()) {
            createWriteContext.setDateFormat(str);
        }
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    configFilter(createWriteContext, serializeFilter);
                }
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes(charset);
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return bytes;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONBytes error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONBytes error", e2);
        }
    }

    public static byte[] toJSONBytes(Object obj, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    configFilter(createWriteContext, serializeFilter);
                }
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return bytes;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONBytes error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONBytes error", e2);
        }
    }

    public static byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(serializeConfig, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return bytes;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONBytes error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONBytes error", e2);
        }
    }

    public static String toJSONString(Object obj, boolean z) {
        SerializerFeature[] serializerFeatureArr;
        if (z) {
            serializerFeatureArr = new SerializerFeature[]{SerializerFeature.PrettyFormat};
        } else {
            serializerFeatureArr = SerializerFeature.EMPTY;
        }
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
            try {
                if (obj == null) {
                    of.writeNull();
                } else {
                    of.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
                }
                String obj2 = of.toString();
                if (of != null) {
                    of.close();
                }
                return obj2;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONString error", e2);
        }
    }

    public static String toJSONString(Object obj) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, new SerializerFeature[0]);
        try {
            com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
            try {
                if (obj == null) {
                    of.writeNull();
                } else {
                    of.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
                }
                String obj2 = of.toString();
                if (of != null) {
                    of.close();
                }
                return obj2;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            throw new JSONException(e.getMessage(), e.getCause() != null ? e.getCause() : e);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONString error", e2);
        }
    }

    public static String toJSONString(Object obj, SerializeFilter serializeFilter, SerializeFilter serializeFilter2, SerializeFilter... serializeFilterArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, new SerializerFeature[0]);
        configFilter(createWriteContext, serializeFilter);
        configFilter(createWriteContext, serializeFilter2);
        for (SerializeFilter serializeFilter3 : serializeFilterArr) {
            configFilter(createWriteContext, serializeFilter3);
        }
        try {
            com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
            try {
                if (obj == null) {
                    of.writeNull();
                } else {
                    of.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
                }
                String obj2 = of.toString();
                if (of != null) {
                    of.close();
                }
                return obj2;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONString error", e2);
        }
    }

    public static String toJSONString(Object obj, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
            try {
                if (obj == null) {
                    of.writeNull();
                } else {
                    of.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
                }
                String obj2 = of.toString();
                if (of != null) {
                    of.close();
                }
                return obj2;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONString error", e2);
        }
    }

    public static byte[] toJSONBytes(Object obj) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, new SerializerFeature[0]);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return bytes;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONBytes error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONBytes error", e2);
        }
    }

    public static byte[] toJSONBytes(Object obj, SerializeFilter... serializeFilterArr) {
        return toJSONBytes(obj, serializeFilterArr, SerializerFeature.EMPTY);
    }

    public static byte[] toJSONBytes(Object obj, int i, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, i, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return bytes;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONBytes error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONBytes error", e2);
        }
    }

    public static byte[] toJSONBytes(Object obj, SerializeFilter serializeFilter) {
        return toJSONBytes(obj, SerializeConfig.global, new SerializeFilter[]{serializeFilter}, DEFAULT_GENERATE_FEATURE, new SerializerFeature[0]);
    }

    public static byte[] toJSONBytes(Object obj, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, SerializeConfig.global, new SerializeFilter[]{serializeFilter}, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, String str, int i, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(serializeConfig, i, serializerFeatureArr);
        if (str != null && !str.isEmpty()) {
            createWriteContext.setDateFormat(str);
        }
        for (SerializeFilter serializeFilter : serializeFilterArr) {
            configFilter(createWriteContext, serializeFilter);
        }
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return bytes;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONBytes error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONBytes error", e2);
        }
    }

    public static byte[] toJSONBytes(Object obj, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    Class<?> cls = obj.getClass();
                    createWriteContext.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return bytes;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("toJSONBytes error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("toJSONBytes error", e2);
        }
    }

    public static byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, int i, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, serializeConfig, new SerializeFilter[0], i, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext(serializeConfig, DEFAULT_GENERATE_FEATURE, serializerFeatureArr));
        try {
            of.setRootObject(obj);
            of.writeAny(obj);
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } catch (Throwable th) {
            if (of != null) {
                try {
                    of.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static String toJSONStringZ(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, serializeConfig, new SerializeFilter[0], null, 0, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, SerializeConfig serializeConfig, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(serializeConfig, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
        try {
            if (serializeConfig.propertyNamingStrategy != null && serializeConfig.propertyNamingStrategy != PropertyNamingStrategy.NeverUseThisValueExceptDefaultValue) {
                NameFilter of2 = NameFilter.of(serializeConfig.propertyNamingStrategy);
                if (serializeFilter instanceof NameFilter) {
                    serializeFilter = NameFilter.compose(of2, (NameFilter) serializeFilter);
                } else {
                    configFilter(createWriteContext, of2);
                }
            }
            configFilter(createWriteContext, serializeFilter);
            if (obj == null) {
                of.writeNull();
            } else {
                of.setRootObject(obj);
                Class<?> cls = obj.getClass();
                createWriteContext.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
            }
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } catch (Throwable th) {
            if (of == null) {
                throw th;
            }
            try {
                of.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public static String toJSONString(Object obj, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        configFilter(createWriteContext, serializeFilter);
        com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
        try {
            if (obj == null) {
                of.writeNull();
            } else {
                of.setRootObject(obj);
                createWriteContext.getObjectWriter(obj.getClass()).write(of, obj, null, null, 0L);
            }
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } catch (Throwable th) {
            if (of == null) {
                throw th;
            }
            try {
                of.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public static String toJSONString(Object obj, int i, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, i, serializerFeatureArr);
        com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
        try {
            if (obj == null) {
                of.writeNull();
            } else {
                of.setRootObject(obj);
                createWriteContext.getObjectWriter(obj.getClass()).write(of, obj, null, null, 0L);
            }
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } catch (Throwable th) {
            if (of == null) {
                throw th;
            }
            try {
                of.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public static String toJSONStringWithDateFormat(Object obj, String str, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        com.alibaba.fastjson2.JSONWriter of = com.alibaba.fastjson2.JSONWriter.of(createWriteContext);
        try {
            createWriteContext.setDateFormat(str);
            if (obj == null) {
                of.writeNull();
            } else {
                of.setRootObject(obj);
                createWriteContext.getObjectWriter(obj.getClass()).write(of, obj, null, null, 0L);
            }
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } catch (Throwable th) {
            if (of == null) {
                throw th;
            }
            try {
                of.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, SerializerFeature... serializerFeatureArr) throws IOException {
        return writeJSONString(outputStream, obj, new SerializeFilter[0], serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, SerializeFilter[] serializeFilterArr) throws IOException {
        return writeJSONString(outputStream, obj, serializeFilterArr, SerializerFeature.EMPTY);
    }

    public static final int writeJSONString(OutputStream outputStream, Charset charset, Object obj, SerializerFeature... serializerFeatureArr) throws IOException {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    createWriteContext.getObjectWriter(obj.getClass()).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes(charset);
                outputStream.write(bytes);
                int length = bytes.length;
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return length;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("writeJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("writeJSONString error", e2);
        }
    }

    public static void writeJSONString(Writer writer, Object obj, SerializerFeature... serializerFeatureArr) {
        writeJSONString(writer, obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static void writeJSONString(Writer writer, Object obj, int i, SerializerFeature... serializerFeatureArr) {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, i, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                ofUTF8.setRootObject(obj);
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    createWriteContext.getObjectWriter(obj.getClass()).write(ofUTF8, obj, null, null, 0L);
                }
                ofUTF8.flushTo(writer);
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("writeJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("writeJSONString error", e2);
        }
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, int i, SerializerFeature... serializerFeatureArr) throws IOException {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, i, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                ofUTF8.setRootObject(obj);
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    createWriteContext.getObjectWriter(obj.getClass()).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                outputStream.write(bytes);
                int length = bytes.length;
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return length;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("writeJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("writeJSONString error", e2);
        }
    }

    public static final int writeJSONString(OutputStream outputStream, Charset charset, Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, String str, int i, SerializerFeature... serializerFeatureArr) throws IOException {
        JSONWriter.Context createWriteContext = createWriteContext(serializeConfig, i, serializerFeatureArr);
        if (str != null && !str.isEmpty()) {
            createWriteContext.setDateFormat(str);
        }
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    configFilter(createWriteContext, serializeFilter);
                }
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    createWriteContext.getObjectWriter(obj.getClass()).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes(charset);
                outputStream.write(bytes);
                int length = bytes.length;
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return length;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            Throwable th = e;
            if (cause != null) {
                th = e.getCause();
            }
            throw new JSONException("writeJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("writeJSONString error", e2);
        }
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) throws IOException {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    configFilter(createWriteContext, serializeFilter);
                }
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    createWriteContext.getObjectWriter(obj.getClass()).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                outputStream.write(bytes);
                int length = bytes.length;
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return length;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("writeJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("writeJSONString error", e2);
        }
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, String str, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) throws IOException {
        JSONWriter.Context createWriteContext = createWriteContext(SerializeConfig.global, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        if (str != null) {
            createWriteContext.setDateFormat(str);
        }
        try {
            com.alibaba.fastjson2.JSONWriter ofUTF8 = com.alibaba.fastjson2.JSONWriter.ofUTF8(createWriteContext);
            try {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    configFilter(createWriteContext, serializeFilter);
                }
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.setRootObject(obj);
                    createWriteContext.getObjectWriter(obj.getClass()).write(ofUTF8, obj, null, null, 0L);
                }
                byte[] bytes = ofUTF8.getBytes();
                outputStream.write(bytes);
                int length = bytes.length;
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return length;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            com.alibaba.fastjson2.JSONException jSONException = e;
            Throwable cause = jSONException.getCause();
            Throwable th = jSONException;
            if (cause != null) {
                th = jSONException.getCause();
            }
            throw new JSONException("writeJSONString error", th);
        } catch (RuntimeException e2) {
            throw new JSONException("writeJSONString error", e2);
        }
    }

    public static JSONArray parseArray(String str, Feature... featureArr) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
        try {
            if (of.nextIfNull()) {
                if (of != null) {
                    of.close();
                }
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            of.read((List) jSONArray);
            if (!of.isEnd()) {
                throw new JSONException(of.info("input not end"));
            }
            if (of != null) {
                of.close();
            }
            return jSONArray;
        } catch (Throwable th) {
            if (of != null) {
                try {
                    of.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static JSONArray parseArray(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, new Feature[0]));
            try {
                if (of.nextIfNullOrEmptyString()) {
                    if (of != null) {
                        of.close();
                    }
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                of.read((List) arrayList);
                JSONArray jSONArray = new JSONArray(arrayList);
                of.handleResolveTasks(jSONArray);
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return jSONArray;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> List<T> parseArray(String str, Class<T> cls) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        ParameterizedTypeImpl parameterizedTypeImpl = new ParameterizedTypeImpl(new Type[]{cls}, null, List.class);
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, new Feature[0]));
            try {
                List<T> list = (List) of.read(parameterizedTypeImpl);
                of.handleResolveTasks(list);
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return list;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> List<T> parseArray(String str, Class<T> cls, ParserConfig parserConfig) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (parserConfig == null) {
            parserConfig = ParserConfig.global;
        }
        ParameterizedTypeImpl parameterizedTypeImpl = new ParameterizedTypeImpl(new Type[]{cls}, null, List.class);
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(parserConfig.getProvider(), DEFAULT_PARSER_FEATURE, new Feature[0]));
            try {
                List<T> list = (List) of.read(parameterizedTypeImpl);
                of.handleResolveTasks(list);
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return list;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static <T> List<T> parseArray(String str, Class<T> cls, Feature... featureArr) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        ParameterizedTypeImpl parameterizedTypeImpl = new ParameterizedTypeImpl(new Type[]{cls}, null, List.class);
        try {
            com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_PARSER_FEATURE, featureArr));
            try {
                List<T> list = (List) of.read(parameterizedTypeImpl);
                of.handleResolveTasks(list);
                if (!of.isEnd()) {
                    throw new JSONException(of.info("input not end"));
                }
                if (of != null) {
                    of.close();
                }
                return list;
            } finally {
            }
        } catch (com.alibaba.fastjson2.JSONException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(e.getMessage(), cause);
        }
    }

    public static boolean isValid(String str) {
        return com.alibaba.fastjson2.JSON.isValid(str);
    }

    public static boolean isValidArray(String str) {
        return com.alibaba.fastjson2.JSON.isValidArray(str);
    }

    public static boolean isValidObject(String str) {
        return com.alibaba.fastjson2.JSON.isValidObject(str);
    }

    public <T> T toJavaObject(TypeReference<T> typeReference) {
        return (T) toJavaObject(typeReference != null ? typeReference.getType() : Object.class);
    }

    public static <T> T toJavaObject(JSON json, Class<T> cls) {
        if (json instanceof JSONObject) {
            return (T) json.toJavaObject((Class) cls);
        }
        return (T) parseObject(toJSONString(json), cls);
    }

    public static Object toJSON(Object obj) {
        if (obj instanceof JSON) {
            return obj;
        }
        try {
            return adaptResult(com.alibaba.fastjson2.JSON.toJSON(obj));
        } catch (com.alibaba.fastjson2.JSONException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static Object adaptResult(Object obj) {
        return adaptResult(obj, 0);
    }

    private static Object adaptResult(Object obj, int i) {
        if (i > 2048) {
            throw new JSONException("level too large : " + i);
        }
        if (obj instanceof com.alibaba.fastjson2.JSONObject) {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, Object> entry : ((com.alibaba.fastjson2.JSONObject) obj).entrySet()) {
                jSONObject.put(entry.getKey(), adaptResult(entry.getValue(), i + 1));
            }
            return jSONObject;
        }
        if (!(obj instanceof com.alibaba.fastjson2.JSONArray)) {
            return obj;
        }
        JSONArray jSONArray = new JSONArray();
        com.alibaba.fastjson2.JSONArray jSONArray2 = (com.alibaba.fastjson2.JSONArray) obj;
        for (int i2 = 0; i2 < jSONArray2.size(); i2++) {
            jSONArray.set(i2, adaptResult(jSONArray2.get(i2), i + 1));
        }
        return jSONArray;
    }

    public static Object toJSON(Object obj, SerializeConfig serializeConfig) {
        if (obj instanceof JSON) {
            return obj;
        }
        Object parse = parse(toJSONString(obj, serializeConfig, new SerializerFeature[0]));
        return parse instanceof List ? new JSONArray((List) parse) : parse;
    }

    public static Object toJSON(Object obj, ParserConfig parserConfig) {
        if (obj instanceof JSON) {
            return obj;
        }
        Object parse = parse(toJSONString(obj), parserConfig);
        return parse instanceof List ? new JSONArray((List) parse) : parse;
    }

    public static List<Object> parseArray(String str, Type[] typeArr) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray(typeArr.length);
        com.alibaba.fastjson2.JSONReader of = com.alibaba.fastjson2.JSONReader.of(str, createReadContext(JSONFactory.getDefaultObjectReaderProvider(), DEFAULT_GENERATE_FEATURE, new Feature[0]));
        try {
            of.startArray();
            for (Type type : typeArr) {
                jSONArray.add(of.read(type));
            }
            of.endArray();
            of.handleResolveTasks(jSONArray);
            if (!of.isEnd()) {
                throw new JSONException(of.info("input not end"));
            }
            if (of != null) {
                of.close();
            }
            return jSONArray;
        } catch (Throwable th) {
            if (of != null) {
                try {
                    of.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void addMixInAnnotations(Type type, Type type2) {
        if (type == null || type2 == null) {
            return;
        }
        Class cls = (Class) type;
        Class cls2 = (Class) type2;
        JSONFactory.getDefaultObjectReaderProvider().mixIn(cls, cls2);
        SerializeConfig.DEFAULT_PROVIDER.mixIn(cls, cls2);
    }

    public static void removeMixInAnnotations(Type type) {
        Class cls = (Class) type;
        JSONFactory.getDefaultObjectReaderProvider().mixIn(cls, null);
        JSONFactory.getDefaultObjectWriterProvider().mixIn(cls, null);
    }

    public static void clearMixInAnnotations() {
        JSONFactory.getDefaultObjectReaderProvider().cleanupMixIn();
        JSONFactory.getDefaultObjectWriterProvider().cleanupMixIn();
    }

    public static Type getMixInAnnotations(Type type) {
        Class cls = (Class) type;
        Class mixIn = JSONFactory.getDefaultObjectReaderProvider().getMixIn(cls);
        return mixIn == null ? JSONFactory.getDefaultObjectWriterProvider().getMixIn(cls) : mixIn;
    }

    static class Cache {
        volatile char[] chars;

        Cache() {
        }
    }

    @Override // com.alibaba.fastjson.JSONAware
    public String toJSONString() {
        return com.alibaba.fastjson2.JSON.toJSONString(this, JSONWriter.Feature.ReferenceDetection);
    }

    public String toString(SerializerFeature... serializerFeatureArr) {
        return toJSONString(this, serializerFeatureArr);
    }

    public void writeJSONString(Appendable appendable) {
        if (appendable instanceof Writer) {
            writeJSONString((Writer) appendable, this, new SerializerFeature[0]);
            return;
        }
        try {
            appendable.append(toJSONString(this));
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }
}
