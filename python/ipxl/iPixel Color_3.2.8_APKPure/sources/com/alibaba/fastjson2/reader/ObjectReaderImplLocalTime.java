package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.codec.DateTimeCodec;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* loaded from: classes2.dex */
class ObjectReaderImplLocalTime extends DateTimeCodec implements ObjectReader {
    static final ObjectReaderImplLocalTime INSTANCE = new ObjectReaderImplLocalTime(null, null);

    public ObjectReaderImplLocalTime(String str, Locale locale) {
        super(str, locale);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return LocalTime.class;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        return jSONReader.readLocalTime();
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        JSONReader.Context context = jSONReader.getContext();
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.isInt()) {
            long readInt64Value = jSONReader.readInt64Value();
            if (this.formatUnixTime) {
                readInt64Value *= 1000;
            }
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(readInt64Value), context.getZoneId()).toLocalTime();
        }
        if (this.format == null || jSONReader.isNumber()) {
            return jSONReader.readLocalTime();
        }
        if (this.yyyyMMddhhmmss19 || this.formatISO8601) {
            return jSONReader.readLocalDateTime().toLocalTime();
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
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(parseLong), context.getZoneId()).toLocalTime();
        }
        DateTimeFormatter dateFormatter = getDateFormatter(context.getLocale());
        if (this.formatHasDay) {
            return LocalDateTime.parse(readString, dateFormatter).toLocalTime();
        }
        return LocalTime.parse(readString, dateFormatter);
    }
}
