package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.JDKUtils;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
class FieldReaderStringField<T> extends FieldReaderObjectField<T> {
    final boolean emptyToNull;
    final boolean trim;
    final boolean upper;

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public boolean supportAcceptType(Class cls) {
        return true;
    }

    FieldReaderStringField(String str, Class cls, int i, long j, String str2, String str3, JSONSchema jSONSchema, Field field) {
        super(str, cls, cls, i, j, str2, null, str3, jSONSchema, field);
        this.trim = "trim".equals(str2) || (j & JSONReader.Feature.TrimString.mask) != 0;
        this.upper = "upper".equals(str2);
        this.emptyToNull = (j & JSONReader.Feature.EmptyStringAsNull.mask) != 0;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        String readString = jSONReader.readString();
        if (readString != null) {
            if (this.trim) {
                readString = readString.trim();
            }
            if (this.upper) {
                readString = readString.toUpperCase();
            }
            if (this.emptyToNull && readString.isEmpty()) {
                readString = null;
            }
        }
        if (this.schema != null) {
            this.schema.assertValidate(readString);
        }
        JDKUtils.UNSAFE.putObject(t, this.fieldOffset, readString);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValueJSONB(JSONReader jSONReader, T t) {
        String readString = jSONReader.readString();
        if (readString != null) {
            if (this.trim) {
                readString = readString.trim();
            }
            if (this.upper) {
                readString = readString.toUpperCase();
            }
            if (this.emptyToNull && readString.isEmpty()) {
                readString = null;
            }
        }
        if (this.schema != null) {
            this.schema.assertValidate(readString);
        }
        accept((FieldReaderStringField<T>) t, readString);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public String readFieldValue(JSONReader jSONReader) {
        String readString = jSONReader.readString();
        return (!this.trim || readString == null) ? readString : readString.trim();
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObjectField, com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        String str;
        if (obj != null && !(obj instanceof String)) {
            str = obj.toString();
        } else {
            str = (String) obj;
        }
        if (str != null) {
            if (this.trim) {
                str = str.trim();
            }
            if (this.upper) {
                str = str.toUpperCase();
            }
            if (this.emptyToNull && str.isEmpty()) {
                str = null;
            }
        }
        if (this.schema != null) {
            this.schema.assertValidate(str);
        }
        JDKUtils.UNSAFE.putObject(t, this.fieldOffset, str);
    }
}
