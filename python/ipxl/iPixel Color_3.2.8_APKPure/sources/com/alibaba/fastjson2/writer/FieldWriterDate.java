package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

/* loaded from: classes2.dex */
abstract class FieldWriterDate<T> extends FieldWriter<T> {
    protected ObjectWriter dateWriter;
    final boolean formatISO8601;
    final boolean formatMillis;
    final boolean formatUnixTime;
    protected DateTimeFormatter formatter;
    final boolean formatyyyyMMdd8;
    final boolean formatyyyyMMddhhmmss14;
    final boolean formatyyyyMMddhhmmss19;

    protected FieldWriterDate(String str, int i, long j, String str2, String str3, Type type, Class cls, Field field, Method method) {
        super(str, i, j, str2, null, str3, type, cls, field, method);
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6 = false;
        if (str2 != null) {
            str2.hashCode();
            z2 = true;
            switch (str2) {
                case "millis":
                    z = false;
                    z3 = false;
                    z5 = false;
                    z4 = false;
                    z6 = true;
                    z2 = z4;
                    break;
                case "unixtime":
                    z3 = false;
                    z5 = false;
                    z4 = false;
                    z = true;
                    z2 = z4;
                    break;
                case "yyyyMMdd":
                    z = false;
                    z5 = false;
                    z4 = false;
                    z3 = true;
                    z2 = z4;
                    break;
                case "yyyy-MM-dd HH:mm:ss":
                    z = false;
                    z3 = false;
                    z5 = false;
                    z4 = true;
                    z2 = false;
                    break;
                case "yyyyMMddHHmmss":
                    z = false;
                    z3 = false;
                    z4 = false;
                    z5 = true;
                    z2 = z4;
                    break;
                case "iso8601":
                    z = false;
                    z3 = false;
                    z5 = z3;
                    z4 = z5;
                    break;
            }
            this.formatMillis = z6;
            this.formatISO8601 = z2;
            this.formatUnixTime = z;
            this.formatyyyyMMdd8 = z3;
            this.formatyyyyMMddhhmmss14 = z5;
            this.formatyyyyMMddhhmmss19 = z4;
        }
        z = false;
        z2 = false;
        z3 = false;
        z5 = z3;
        z4 = z5;
        this.formatMillis = z6;
        this.formatISO8601 = z2;
        this.formatUnixTime = z;
        this.formatyyyyMMdd8 = z3;
        this.formatyyyyMMddhhmmss14 = z5;
        this.formatyyyyMMddhhmmss19 = z4;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public boolean isDateFormatMillis() {
        return this.formatMillis;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public boolean isDateFormatISO8601() {
        return this.formatISO8601;
    }

    public DateTimeFormatter getFormatter() {
        if (this.formatter == null && this.format != null && !this.formatMillis && !this.formatISO8601 && !this.formatUnixTime) {
            this.formatter = DateTimeFormatter.ofPattern(this.format);
        }
        return this.formatter;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public ObjectWriter getObjectWriter(JSONWriter jSONWriter, Class cls) {
        if (cls == this.fieldClass) {
            ObjectWriterProvider objectWriterProvider = jSONWriter.context.provider;
            if (this.dateWriter == null) {
                if ((objectWriterProvider.userDefineMask & 16) != 0) {
                    this.dateWriter = objectWriterProvider.getObjectWriter((Type) cls, cls, false);
                } else {
                    if (this.format == null) {
                        ObjectWriterImplDate objectWriterImplDate = ObjectWriterImplDate.INSTANCE;
                        this.dateWriter = objectWriterImplDate;
                        return objectWriterImplDate;
                    }
                    ObjectWriterImplDate objectWriterImplDate2 = new ObjectWriterImplDate(this.format, null);
                    this.dateWriter = objectWriterImplDate2;
                    return objectWriterImplDate2;
                }
            }
            return this.dateWriter;
        }
        return jSONWriter.getObjectWriter(cls);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00f2  */
    @Override // com.alibaba.fastjson2.writer.FieldWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeDate(com.alibaba.fastjson2.JSONWriter r35, long r36) {
        /*
            Method dump skipped, instructions count: 613
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.writer.FieldWriterDate.writeDate(com.alibaba.fastjson2.JSONWriter, long):void");
    }
}
