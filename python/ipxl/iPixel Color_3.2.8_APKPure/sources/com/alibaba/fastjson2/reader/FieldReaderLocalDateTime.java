package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.DateUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.function.BiConsumer;

/* loaded from: classes2.dex */
public final class FieldReaderLocalDateTime<T> extends FieldReaderDateTimeCodec<T> {
    final BiConsumer<T, ZonedDateTime> function;

    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    protected Object apply(LocalDateTime localDateTime) {
        return localDateTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec, com.alibaba.fastjson2.reader.FieldReader
    public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) {
        super.accept((FieldReaderLocalDateTime<T>) obj, obj2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    FieldReaderLocalDateTime(java.lang.String r15, java.lang.reflect.Type r16, java.lang.Class r17, int r18, long r19, java.lang.String r21, java.util.Locale r22, java.lang.Object r23, com.alibaba.fastjson2.schema.JSONSchema r24, java.lang.reflect.Field r25, java.lang.reflect.Method r26, java.util.function.BiConsumer<T, java.time.ZonedDateTime> r27) {
        /*
            r14 = this;
            r7 = r21
            if (r7 == 0) goto Lc
            com.alibaba.fastjson2.reader.ObjectReaderImplLocalDateTime r0 = new com.alibaba.fastjson2.reader.ObjectReaderImplLocalDateTime
            r8 = r22
            r0.<init>(r7, r8)
            goto L10
        Lc:
            r8 = r22
            com.alibaba.fastjson2.reader.ObjectReaderImplLocalDateTime r0 = com.alibaba.fastjson2.reader.ObjectReaderImplLocalDateTime.INSTANCE
        L10:
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r9 = r23
            r10 = r24
            r12 = r25
            r11 = r26
            r13 = r0
            r0 = r14
            r0.<init>(r1, r2, r3, r4, r5, r7, r8, r9, r10, r11, r12, r13)
            r15 = r27
            r14.function = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.FieldReaderLocalDateTime.<init>(java.lang.String, java.lang.reflect.Type, java.lang.Class, int, long, java.lang.String, java.util.Locale, java.lang.Object, com.alibaba.fastjson2.schema.JSONSchema, java.lang.reflect.Field, java.lang.reflect.Method, java.util.function.BiConsumer):void");
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec, com.alibaba.fastjson2.reader.FieldReader
    public boolean supportAcceptType(Class cls) {
        return this.fieldClass == Instant.class || this.fieldClass == Long.class;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, Object obj) {
        LocalDateTime localDateTime;
        if (jSONReader.jsonb) {
            localDateTime = (LocalDateTime) this.dateReader.readJSONBObject(jSONReader, this.fieldType, this.fieldName, this.features);
        } else {
            localDateTime = (LocalDateTime) this.dateReader.readObject(jSONReader, this.fieldType, this.fieldName, this.features);
        }
        accept(obj, localDateTime);
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [java.time.LocalDateTime] */
    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(Object obj, long j) {
        accept(obj, (LocalDateTime) Instant.ofEpochMilli(j).atZone(DateUtils.DEFAULT_ZONE_ID).toLocalDateTime());
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [java.time.LocalDateTime] */
    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    protected void accept(Object obj, Date date) {
        accept(obj, (LocalDateTime) date.toInstant().atZone(DateUtils.DEFAULT_ZONE_ID).toLocalDateTime());
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    protected void acceptNull(Object obj) {
        accept(obj, (LocalDateTime) null);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [java.time.LocalDateTime] */
    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    protected void accept(Object obj, Instant instant) {
        accept(obj, (LocalDateTime) instant.atZone(DateUtils.DEFAULT_ZONE_ID).toLocalDateTime());
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.time.LocalDateTime] */
    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    protected void accept(Object obj, ZonedDateTime zonedDateTime) {
        accept(obj, (LocalDateTime) zonedDateTime.toLocalDateTime());
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    protected Object apply(Date date) {
        return date.toInstant().atZone(DateUtils.DEFAULT_ZONE_ID).toLocalDateTime();
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    protected Object apply(Instant instant) {
        return instant.atZone(DateUtils.DEFAULT_ZONE_ID).toLocalDateTime();
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    protected Object apply(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalDateTime();
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    protected Object apply(long j) {
        return Instant.ofEpochMilli(j).atZone(DateUtils.DEFAULT_ZONE_ID).toLocalDateTime();
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderDateTimeCodec
    public void accept(Object obj, LocalDateTime localDateTime) {
        if (this.schema != null) {
            this.schema.assertValidate(localDateTime);
        }
        if (obj == null) {
            throw new JSONException("set " + this.fieldName + " error, object is null");
        }
        if (localDateTime != null || (this.features & JSONReader.Feature.IgnoreSetNullValue.mask) == 0) {
            if (this.fieldOffset != -1) {
                JDKUtils.UNSAFE.putObject(obj, this.fieldOffset, localDateTime);
                return;
            }
            try {
                this.field.set(obj, localDateTime);
            } catch (Exception e) {
                throw new JSONException("set " + this.fieldName + " error", e);
            }
        }
    }
}
