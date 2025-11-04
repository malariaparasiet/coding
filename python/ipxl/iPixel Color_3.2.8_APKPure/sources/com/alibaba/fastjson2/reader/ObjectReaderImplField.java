package com.alibaba.fastjson2.reader;

import androidx.autofill.HintConstants;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public class ObjectReaderImplField implements ObjectReader {
    static final long HASH_DECLARING_CLASS = Fnv.hashCode64("declaringClass");
    static final long HASH_NAME = Fnv.hashCode64(HintConstants.AUTOFILL_HINT_NAME);

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        return readObject(jSONReader, type, obj, j);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readArrayMappingJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.startArray() != 2) {
            throw new JSONException("not support input " + jSONReader.info());
        }
        return getField(j | jSONReader.getContext().getFeatures(), jSONReader.readString(), jSONReader.readString());
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readArrayMappingObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (!jSONReader.nextIfArrayStart()) {
            throw new JSONException("not support input " + jSONReader.info());
        }
        String readString = jSONReader.readString();
        String readString2 = jSONReader.readString();
        if (!jSONReader.nextIfArrayEnd()) {
            throw new JSONException("not support input " + jSONReader.info());
        }
        jSONReader.nextIfComma();
        return getField(jSONReader.getContext().getFeatures() | j, readString2, readString);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (!jSONReader.nextIfObjectStart()) {
            if (jSONReader.isSupportBeanArray(j)) {
                if (jSONReader.jsonb) {
                    return readArrayMappingJSONBObject(jSONReader, type, obj, j);
                }
                return readArrayMappingObject(jSONReader, type, obj, j);
            }
            throw new JSONException("not support input " + jSONReader.info());
        }
        String str = null;
        String str2 = null;
        while (!jSONReader.nextIfObjectEnd()) {
            long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
            if (readFieldNameHashCode == HASH_DECLARING_CLASS) {
                str2 = jSONReader.readString();
            } else if (readFieldNameHashCode == HASH_NAME) {
                str = jSONReader.readString();
            } else {
                jSONReader.skipValue();
            }
        }
        if (!jSONReader.jsonb) {
            jSONReader.nextIfComma();
        }
        return getField(j | jSONReader.getContext().getFeatures(), str, str2);
    }

    private Field getField(long j, String str, String str2) {
        if ((j & JSONReader.Feature.SupportClassForName.mask) == 0) {
            throw new JSONException("ClassForName not support");
        }
        try {
            return TypeUtils.loadClass(str2).getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            throw new JSONException("method not found", e);
        }
    }
}
