package com.alibaba.fastjson2.reader;

import androidx.autofill.HintConstants;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/* loaded from: classes2.dex */
public class ObjectReaderImplMethod implements ObjectReader<Method> {
    static final long HASH_DECLARING_CLASS = Fnv.hashCode64("declaringClass");
    static final long HASH_NAME = Fnv.hashCode64(HintConstants.AUTOFILL_HINT_NAME);
    static final long HASH_PARAMETER_TYPES = Fnv.hashCode64("parameterTypes");

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Method readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        return readObject(jSONReader, type, obj, j);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Method readArrayMappingJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.startArray() != 3) {
            throw new JSONException("not support input " + jSONReader.info());
        }
        return getMethod(jSONReader.getContext().getFeatures() | j, jSONReader.readString(), jSONReader.readString(), jSONReader.readArray(String.class));
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Method readArrayMappingObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (!jSONReader.nextIfArrayStart()) {
            throw new JSONException("not support input " + jSONReader.info());
        }
        String readString = jSONReader.readString();
        String readString2 = jSONReader.readString();
        List readArray = jSONReader.readArray(String.class);
        if (!jSONReader.nextIfArrayEnd()) {
            throw new JSONException("not support input " + jSONReader.info());
        }
        jSONReader.nextIfComma();
        return getMethod(jSONReader.getContext().getFeatures() | j, readString2, readString, readArray);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Method readObject(JSONReader jSONReader, Type type, Object obj, long j) {
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
        List list = null;
        while (!jSONReader.nextIfObjectEnd()) {
            long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
            if (readFieldNameHashCode == HASH_DECLARING_CLASS) {
                str2 = jSONReader.readString();
            } else if (readFieldNameHashCode == HASH_NAME) {
                str = jSONReader.readString();
            } else if (readFieldNameHashCode == HASH_PARAMETER_TYPES) {
                list = jSONReader.readArray(String.class);
            } else {
                jSONReader.skipValue();
            }
        }
        if (!jSONReader.jsonb) {
            jSONReader.nextIfComma();
        }
        return getMethod(jSONReader.getContext().getFeatures() | j, str, str2, list);
    }

    private Method getMethod(long j, String str, String str2, List<String> list) {
        Class<?>[] clsArr;
        if ((j & JSONReader.Feature.SupportClassForName.mask) != 0) {
            Class loadClass = TypeUtils.loadClass(str2);
            if (list == null) {
                clsArr = new Class[0];
            } else {
                Class<?>[] clsArr2 = new Class[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    clsArr2[i] = TypeUtils.loadClass(list.get(i));
                }
                clsArr = clsArr2;
            }
            try {
                return loadClass.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                throw new JSONException("method not found", e);
            }
        }
        throw new JSONException("ClassForName not support");
    }
}
