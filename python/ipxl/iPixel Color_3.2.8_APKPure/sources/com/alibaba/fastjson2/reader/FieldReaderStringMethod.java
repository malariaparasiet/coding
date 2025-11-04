package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Locale;

/* loaded from: classes2.dex */
final class FieldReaderStringMethod<T> extends FieldReaderObject<T> {
    final boolean trim;
    final boolean upper;

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public boolean supportAcceptType(Class cls) {
        return true;
    }

    FieldReaderStringMethod(String str, Type type, Class cls, int i, long j, String str2, Locale locale, String str3, JSONSchema jSONSchema, Method method) {
        super(str, type, cls, i, j, str2, locale, str3, jSONSchema, method, null, null);
        this.trim = "trim".equals(str2) || (j & JSONReader.Feature.TrimString.mask) != 0;
        this.upper = "upper".equals(str2);
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
        }
        if (this.schema != null) {
            this.schema.assertValidate(readString);
        }
        try {
            this.method.invoke(t, readString);
        } catch (Exception e) {
            throw new JSONException(jSONReader.info("set " + this.fieldName + " error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public String readFieldValue(JSONReader jSONReader) {
        String readString = jSONReader.readString();
        return (!this.trim || readString == null) ? readString : readString.trim();
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, Object obj) {
        String str;
        if ((obj instanceof String) || obj == null) {
            str = (String) obj;
        } else {
            str = obj.toString();
        }
        if (str != null) {
            if (this.trim) {
                str = str.trim();
            }
            if (this.upper) {
                str = str.toUpperCase();
            }
        }
        if (this.schema != null) {
            this.schema.assertValidate(str);
        }
        try {
            this.method.invoke(t, str);
        } catch (Exception e) {
            throw new JSONException("set " + this.fieldName + " error", e);
        }
    }
}
