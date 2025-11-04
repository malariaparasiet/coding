package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.codec.DateTimeCodec;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public final class ObjectReaderImplInstant extends DateTimeCodec implements ObjectReader {
    public static final ObjectReaderImplInstant INSTANCE = new ObjectReaderImplInstant(null, null);

    public static ObjectReaderImplInstant of(String str, Locale locale) {
        if (str == null) {
            return INSTANCE;
        }
        return new ObjectReaderImplInstant(str, locale);
    }

    ObjectReaderImplInstant(String str, Locale locale) {
        super(str, locale);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return Instant.class;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Map map, long j) {
        Number number = (Number) map.get("nano");
        Number number2 = (Number) map.get("epochSecond");
        if (number != null && number2 != null) {
            return Instant.ofEpochSecond(number2.longValue(), number.longValue());
        }
        if (number2 != null) {
            return Instant.ofEpochSecond(number2.longValue());
        }
        Number number3 = (Number) map.get("epochMilli");
        if (number3 != null) {
            return Instant.ofEpochMilli(number3.longValue());
        }
        throw new JSONException("can not create instant.");
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        return jSONReader.readInstant();
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        JSONReader.Context context = jSONReader.getContext();
        if (jSONReader.isInt() && context.getDateFormat() == null) {
            long readInt64Value = jSONReader.readInt64Value();
            if (this.formatUnixTime) {
                readInt64Value *= 1000;
            }
            return Instant.ofEpochMilli(readInt64Value);
        }
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (this.format == null || this.yyyyMMddhhmmss19 || this.formatISO8601 || jSONReader.isObject()) {
            return jSONReader.readInstant();
        }
        String readString = jSONReader.readString();
        if (readString.isEmpty()) {
            return null;
        }
        if (this.formatMillis || this.formatUnixTime) {
            long parseLong = Long.parseLong(readString);
            if (this.formatUnixTime) {
                parseLong *= 1000;
            }
            return Instant.ofEpochMilli(parseLong);
        }
        DateTimeFormatter dateFormatter = getDateFormatter(jSONReader.getLocale());
        if (!this.formatHasHour) {
            return ZonedDateTime.of(LocalDate.parse(readString, dateFormatter), LocalTime.MIN, context.getZoneId()).toInstant();
        }
        if (!this.formatHasDay) {
            return ZonedDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.parse(readString, dateFormatter), context.getZoneId()).toInstant();
        }
        return ZonedDateTime.of(LocalDateTime.parse(readString, dateFormatter), context.getZoneId()).toInstant();
    }
}
