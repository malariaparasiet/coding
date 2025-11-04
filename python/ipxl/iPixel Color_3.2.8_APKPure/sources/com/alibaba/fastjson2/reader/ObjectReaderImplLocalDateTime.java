package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.codec.DateTimeCodec;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* loaded from: classes2.dex */
class ObjectReaderImplLocalDateTime extends DateTimeCodec implements ObjectReader {
    static final ObjectReaderImplLocalDateTime INSTANCE = new ObjectReaderImplLocalDateTime(null, null);

    public ObjectReaderImplLocalDateTime(String str, Locale locale) {
        super(str, locale);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return LocalDateTime.class;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        return jSONReader.readLocalDateTime();
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        DateTimeFormatter dateFormatter;
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, j);
        }
        JSONReader.Context context = jSONReader.getContext();
        if (jSONReader.isInt()) {
            if (!this.yyyyMMddhhmmss19 && !this.formatMillis && !this.formatISO8601 && !this.formatUnixTime && (dateFormatter = getDateFormatter()) != null) {
                return LocalDateTime.parse(jSONReader.readString(), dateFormatter);
            }
            long readInt64Value = jSONReader.readInt64Value();
            if (this.formatUnixTime) {
                readInt64Value *= 1000;
            }
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(readInt64Value), context.getZoneId());
        }
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (this.format == null || this.yyyyMMdd8 || this.yyyyMMdd10 || this.yyyyMMddhhmmss19 || this.formatISO8601) {
            return jSONReader.readLocalDateTime();
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
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(parseLong), context.getZoneId());
        }
        DateTimeFormatter dateFormatter2 = getDateFormatter(context.getLocale());
        if (!this.formatHasHour) {
            return LocalDateTime.of(LocalDate.parse(readString, dateFormatter2), LocalTime.MIN);
        }
        if (!this.formatHasDay) {
            return LocalDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.parse(readString, dateFormatter2));
        }
        return LocalDateTime.parse(readString, dateFormatter2);
    }
}
