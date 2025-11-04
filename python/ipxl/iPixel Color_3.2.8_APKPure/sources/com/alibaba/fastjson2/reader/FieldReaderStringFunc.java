package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.function.BiConsumer;

/* loaded from: classes2.dex */
final class FieldReaderStringFunc<T, V> extends FieldReader<T> {
    final String format;
    final BiConsumer<T, V> function;
    final boolean trim;
    final boolean upper;

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public boolean supportAcceptType(Class cls) {
        return true;
    }

    FieldReaderStringFunc(String str, Class<V> cls, int i, long j, String str2, Locale locale, Object obj, JSONSchema jSONSchema, Method method, BiConsumer<T, V> biConsumer) {
        super(str, cls, cls, i, j, str2, locale, obj, jSONSchema, method, null);
        this.function = biConsumer;
        this.format = str2;
        this.trim = "trim".equals(str2) || (j & JSONReader.Feature.TrimString.mask) != 0;
        this.upper = "upper".equals(str2);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, int i) {
        accept((FieldReaderStringFunc<T, V>) t, Integer.toString(i));
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public void accept(T t, long j) {
        accept((FieldReaderStringFunc<T, V>) t, Long.toString(j));
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
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
            this.function.accept(t, str);
        } catch (Exception e) {
            throw new JSONException("set " + super.toString() + " error", e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
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
        this.function.accept(t, readString);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        return jSONReader.readString();
    }

    @Override // com.alibaba.fastjson2.reader.FieldReader
    public BiConsumer getFunction() {
        return this.function;
    }
}
