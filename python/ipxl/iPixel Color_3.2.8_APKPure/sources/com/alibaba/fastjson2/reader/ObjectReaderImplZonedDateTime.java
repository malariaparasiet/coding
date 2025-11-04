package com.alibaba.fastjson2.reader;

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
import java.util.function.Function;

/* loaded from: classes2.dex */
class ObjectReaderImplZonedDateTime extends DateTimeCodec implements ObjectReader {
    static final ObjectReaderImplZonedDateTime INSTANCE = new ObjectReaderImplZonedDateTime(null, null);
    private Function builder;

    public static ObjectReaderImplZonedDateTime of(String str, Locale locale) {
        if (str == null) {
            return INSTANCE;
        }
        return new ObjectReaderImplZonedDateTime(str, locale);
    }

    public ObjectReaderImplZonedDateTime(Function function) {
        super(null, null);
        this.builder = function;
    }

    public ObjectReaderImplZonedDateTime(String str, Locale locale) {
        super(str, locale);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return ZonedDateTime.class;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        ZonedDateTime readZonedDateTime = jSONReader.readZonedDateTime();
        Function function = this.builder;
        return (function == null || readZonedDateTime == null) ? readZonedDateTime : function.apply(readZonedDateTime);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        ZonedDateTime readZonedDateTime;
        JSONReader.Context context = jSONReader.getContext();
        if (jSONReader.isInt()) {
            long readInt64Value = jSONReader.readInt64Value();
            if (this.formatUnixTime) {
                readInt64Value *= 1000;
            }
            readZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(readInt64Value), context.getZoneId());
        } else if (jSONReader.readIfNull()) {
            readZonedDateTime = null;
        } else if (this.format == null || this.yyyyMMddhhmmss19 || this.formatISO8601) {
            readZonedDateTime = jSONReader.readZonedDateTime();
        } else {
            String readString = jSONReader.readString();
            if (this.formatMillis || this.formatUnixTime) {
                long parseLong = Long.parseLong(readString);
                if (this.formatUnixTime) {
                    parseLong *= 1000;
                }
                readZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(parseLong), context.getZoneId());
            } else {
                DateTimeFormatter dateFormatter = getDateFormatter(jSONReader.getLocale());
                if (!this.formatHasHour) {
                    readZonedDateTime = ZonedDateTime.of(LocalDate.parse(readString, dateFormatter), LocalTime.MIN, context.getZoneId());
                } else if (!this.formatHasDay) {
                    readZonedDateTime = ZonedDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.parse(readString, dateFormatter), context.getZoneId());
                } else {
                    readZonedDateTime = ZonedDateTime.of(LocalDateTime.parse(readString, dateFormatter), context.getZoneId());
                }
            }
        }
        Function function = this.builder;
        return (function == null || readZonedDateTime == null) ? readZonedDateTime : function.apply(readZonedDateTime);
    }
}
