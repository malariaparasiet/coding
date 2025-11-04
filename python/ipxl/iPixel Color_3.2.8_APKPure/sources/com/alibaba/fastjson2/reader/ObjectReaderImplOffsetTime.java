package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.codec.DateTimeCodec;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* loaded from: classes2.dex */
final class ObjectReaderImplOffsetTime extends DateTimeCodec implements ObjectReader {
    static final ObjectReaderImplOffsetTime INSTANCE = new ObjectReaderImplOffsetTime(null, null);

    public static ObjectReaderImplOffsetTime of(String str, Locale locale) {
        if (str == null) {
            return INSTANCE;
        }
        return new ObjectReaderImplOffsetTime(str, locale);
    }

    public ObjectReaderImplOffsetTime(String str, Locale locale) {
        super(str, locale);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return OffsetTime.class;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        return readObject(jSONReader, type, obj, j);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        JSONReader.Context context = jSONReader.getContext();
        if (jSONReader.isInt()) {
            long readInt64Value = jSONReader.readInt64Value();
            if (this.formatUnixTime || context.isFormatUnixTime()) {
                readInt64Value *= 1000;
            }
            Instant ofEpochMilli = Instant.ofEpochMilli(readInt64Value);
            ZoneId zoneId = context.getZoneId();
            return OffsetTime.of(LocalDateTime.ofInstant(ofEpochMilli, zoneId).toLocalTime(), zoneId.getRules().getOffset(ofEpochMilli));
        }
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (this.format == null) {
            return jSONReader.readOffsetTime();
        }
        String readString = jSONReader.readString();
        ZoneId zoneId2 = context.getZoneId();
        if (this.formatMillis || this.formatUnixTime) {
            long parseLong = Long.parseLong(readString);
            if (this.formatUnixTime) {
                parseLong *= 1000;
            }
            Instant ofEpochMilli2 = Instant.ofEpochMilli(parseLong);
            return OffsetDateTime.of(LocalDateTime.ofInstant(ofEpochMilli2, zoneId2), zoneId2.getRules().getOffset(ofEpochMilli2)).toOffsetTime();
        }
        DateTimeFormatter dateFormatter = getDateFormatter(jSONReader.getLocale());
        if (!this.formatHasHour) {
            LocalDateTime of = LocalDateTime.of(LocalDate.parse(readString, dateFormatter), LocalTime.MIN);
            return OffsetDateTime.of(of, zoneId2.getRules().getOffset(of)).toOffsetTime();
        }
        if (!this.formatHasDay) {
            return ZonedDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.parse(readString, dateFormatter), zoneId2).toOffsetDateTime().toOffsetTime();
        }
        LocalDateTime parse = LocalDateTime.parse(readString, dateFormatter);
        return OffsetDateTime.of(parse, zoneId2.getRules().getOffset(parse)).toOffsetTime();
    }
}
