package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class ObjectReaderImplFloatArray extends ObjectReaderPrimitive {
    static final ObjectReaderImplFloatArray INSTANCE = new ObjectReaderImplFloatArray();
    static final long HASH_TYPE = Fnv.hashCode64("[Float");

    ObjectReaderImplFloatArray() {
        super(Float[].class);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            Float[] fArr = new Float[16];
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                if (jSONReader.isEnd()) {
                    throw new JSONException(jSONReader.info("input end"));
                }
                int i2 = i + 1;
                if (i2 - fArr.length > 0) {
                    int length = fArr.length;
                    int i3 = length + (length >> 1);
                    if (i3 - i2 < 0) {
                        i3 = i2;
                    }
                    fArr = (Float[]) Arrays.copyOf(fArr, i3);
                }
                fArr[i] = jSONReader.readFloat();
                i = i2;
            }
            jSONReader.nextIfComma();
            return Arrays.copyOf(fArr, i);
        }
        if (jSONReader.isString()) {
            String readString = jSONReader.readString();
            if (readString.isEmpty()) {
                return null;
            }
            throw new JSONException(jSONReader.info("not support input " + readString));
        }
        throw new JSONException(jSONReader.info("TODO"));
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.nextIfMatch(JSONB.Constants.BC_TYPED_ANY) && jSONReader.readTypeHashCode() != HASH_TYPE) {
            throw new JSONException("not support autoType : " + jSONReader.getString());
        }
        int startArray = jSONReader.startArray();
        if (startArray == -1) {
            return null;
        }
        Float[] fArr = new Float[startArray];
        for (int i = 0; i < startArray; i++) {
            fArr[i] = jSONReader.readFloat();
        }
        return fArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        Float f;
        Float[] fArr = new Float[collection.size()];
        int i = 0;
        for (Object obj : collection) {
            if (obj == null) {
                f = null;
            } else if (obj instanceof Number) {
                f = Float.valueOf(((Number) obj).floatValue());
            } else {
                Function typeConvert = JSONFactory.getDefaultObjectReaderProvider().getTypeConvert(obj.getClass(), Float.class);
                if (typeConvert == null) {
                    throw new JSONException("can not cast to Float " + obj.getClass());
                }
                f = (Float) typeConvert.apply(obj);
            }
            fArr[i] = f;
            i++;
        }
        return fArr;
    }
}
