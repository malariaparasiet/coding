package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.codec.DateTimeCodec;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes2.dex */
final class ObjectReaderImplCalendar extends DateTimeCodec implements ObjectReader {
    static final ObjectReaderImplCalendar INSTANCE = new ObjectReaderImplCalendar(null, null);

    public static ObjectReaderImplCalendar of(String str, Locale locale) {
        if (str == null) {
            return INSTANCE;
        }
        return new ObjectReaderImplCalendar(str, locale);
    }

    public ObjectReaderImplCalendar(String str, Locale locale) {
        super(str, locale);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return Calendar.class;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.isInt()) {
            long readInt64Value = jSONReader.readInt64Value();
            if (this.formatUnixTime) {
                readInt64Value *= 1000;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(readInt64Value);
            return calendar;
        }
        if (jSONReader.readIfNull()) {
            return null;
        }
        long readMillisFromString = jSONReader.readMillisFromString();
        if (this.formatUnixTime) {
            readMillisFromString *= 1000;
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(readMillisFromString);
        return calendar2;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        DateTimeFormatter dateFormatter;
        if (jSONReader.isString()) {
            if (this.format != null && (dateFormatter = getDateFormatter()) != null) {
                String readString = jSONReader.readString();
                if (readString.isEmpty()) {
                    return null;
                }
                long epochMilli = ZonedDateTime.of(LocalDateTime.parse(readString, dateFormatter), jSONReader.getContext().getZoneId()).toInstant().toEpochMilli();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(epochMilli);
                return calendar;
            }
            long readMillisFromString = jSONReader.readMillisFromString();
            if (readMillisFromString == 0 && jSONReader.wasNull()) {
                return null;
            }
            if (this.formatUnixTime) {
                readMillisFromString *= 1000;
            }
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(readMillisFromString);
            return calendar2;
        }
        if (jSONReader.readIfNull()) {
            return null;
        }
        long readInt64Value = jSONReader.readInt64Value();
        if (this.formatUnixTime || jSONReader.getContext().isFormatUnixTime()) {
            readInt64Value *= 1000;
        }
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTimeInMillis(readInt64Value);
        return calendar3;
    }
}
