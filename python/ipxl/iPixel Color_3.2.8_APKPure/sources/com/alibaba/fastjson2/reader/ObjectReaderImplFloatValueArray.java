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
class ObjectReaderImplFloatValueArray extends ObjectReaderPrimitive {
    static final ObjectReaderImplFloatValueArray INSTANCE = new ObjectReaderImplFloatValueArray(null);
    static final long TYPE_HASH = Fnv.hashCode64("[F");
    final Function<float[], Object> builder;

    ObjectReaderImplFloatValueArray(Function<float[], Object> function) {
        super(float[].class);
        this.builder = function;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            float[] fArr = new float[16];
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
                    fArr = Arrays.copyOf(fArr, i3);
                }
                fArr[i] = jSONReader.readFloatValue();
                i = i2;
            }
            jSONReader.nextIfComma();
            float[] copyOf = Arrays.copyOf(fArr, i);
            Function<float[], Object> function = this.builder;
            return function != null ? function.apply(copyOf) : copyOf;
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
        if (jSONReader.nextIfMatch(JSONB.Constants.BC_TYPED_ANY) && jSONReader.readTypeHashCode() != TYPE_HASH) {
            throw new JSONException("not support autoType : " + jSONReader.getString());
        }
        int startArray = jSONReader.startArray();
        if (startArray == -1) {
            return null;
        }
        float[] fArr = new float[startArray];
        for (int i = 0; i < startArray; i++) {
            fArr[i] = jSONReader.readFloatValue();
        }
        Function<float[], Object> function = this.builder;
        return function != null ? function.apply(fArr) : fArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        float floatValue;
        float[] fArr = new float[collection.size()];
        int i = 0;
        for (Object obj : collection) {
            if (obj == null) {
                floatValue = 0.0f;
            } else if (obj instanceof Number) {
                floatValue = ((Number) obj).floatValue();
            } else {
                Function typeConvert = JSONFactory.getDefaultObjectReaderProvider().getTypeConvert(obj.getClass(), Float.TYPE);
                if (typeConvert == null) {
                    throw new JSONException("can not cast to float " + obj.getClass());
                }
                floatValue = ((Float) typeConvert.apply(obj)).floatValue();
            }
            fArr[i] = floatValue;
            i++;
        }
        Function<float[], Object> function = this.builder;
        return function != null ? function.apply(fArr) : fArr;
    }
}
