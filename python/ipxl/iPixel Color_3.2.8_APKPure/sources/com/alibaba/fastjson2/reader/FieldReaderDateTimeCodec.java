package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.DateUtils;
import com.alibaba.fastjson2.util.IOUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
abstract class FieldReaderDateTimeCodec<T> extends FieldReader<T> {
    final ObjectReader dateReader;
    final boolean formatMillis;
    final boolean formatUnixTime;

    protected abstract void accept(T t, Instant instant);

    protected abstract void accept(T t, LocalDateTime localDateTime);

    protected abstract void accept(T t, ZonedDateTime zonedDateTime);

    protected abstract void accept(T t, Date date);

    protected abstract void acceptNull(T t);

    protected abstract Object apply(long j);

    protected abstract Object apply(Instant instant);

    protected abstract Object apply(LocalDateTime localDateTime);

    protected abstract Object apply(ZonedDateTime zonedDateTime);

    protected abstract Object apply(Date date);

    public FieldReaderDateTimeCodec(String str, Type type, Class cls, int i, long j, String str2, Locale locale, Object obj, JSONSchema jSONSchema, Method method, Field field, ObjectReader objectReader) {
        super(str, type, cls, i, j, str2, locale, obj, jSONSchema, method, field);
        boolean z;
        this.dateReader = objectReader;
        boolean z2 = false;
        if (str2 != null) {
            str2.hashCode();
            z = true;
            if (!str2.equals("millis")) {
                if (str2.equals("unixtime")) {
                    z = false;
                    z2 = true;
                }
            }
            this.formatUnixTime = z2;
            this.formatMillis = z;
        }
        z = false;
        this.formatUnixTime = z2;
        this.formatMillis = z;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public final Object readFieldValue(JSONReader jSONReader) {
        return this.dateReader.readObject(jSONReader, this.fieldType, this.fieldName, this.features);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public final ObjectReader getObjectReader(JSONReader jSONReader) {
        return this.dateReader;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public final ObjectReader getObjectReader(JSONReader.Context context) {
        return this.dateReader;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        if (obj == null) {
            acceptNull(t);
            return;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.isEmpty() || "null".equals(str)) {
                acceptNull(t);
                return;
            }
            if ((this.format == null || this.formatUnixTime || this.formatMillis) && IOUtils.isNumber(str)) {
                long parseLong = Long.parseLong(str);
                if (this.formatUnixTime) {
                    parseLong *= 1000;
                }
                accept((FieldReaderDateTimeCodec<T>) t, parseLong);
                return;
            }
            obj = DateUtils.parseDate(str, this.format, DateUtils.DEFAULT_ZONE_ID);
        }
        if (obj instanceof Date) {
            accept((FieldReaderDateTimeCodec<T>) t, (Date) obj);
            return;
        }
        if (obj instanceof Instant) {
            accept((FieldReaderDateTimeCodec<T>) t, (Instant) obj);
            return;
        }
        if (obj instanceof Long) {
            accept((FieldReaderDateTimeCodec<T>) t, ((Long) obj).longValue());
        } else if (obj instanceof LocalDateTime) {
            accept((FieldReaderDateTimeCodec<T>) t, (LocalDateTime) obj);
        } else {
            if (obj instanceof ZonedDateTime) {
                accept((FieldReaderDateTimeCodec<T>) t, (ZonedDateTime) obj);
                return;
            }
            throw new JSONException("not support value " + obj.getClass());
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public boolean supportAcceptType(Class cls) {
        return cls == Date.class || cls == String.class;
    }
}
