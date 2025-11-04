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
class ObjectReaderImplInt16ValueArray extends ObjectReaderPrimitive {
    final Function<short[], Object> builder;
    static final ObjectReaderImplInt16ValueArray INSTANCE = new ObjectReaderImplInt16ValueArray(null);
    static final long HASH_TYPE = Fnv.hashCode64("[S");

    ObjectReaderImplInt16ValueArray(Function<short[], Object> function) {
        super(short[].class);
        this.builder = function;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            short[] sArr = new short[16];
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                if (jSONReader.isEnd()) {
                    throw new JSONException(jSONReader.info("input end"));
                }
                int i2 = i + 1;
                if (i2 - sArr.length > 0) {
                    int length = sArr.length;
                    int i3 = length + (length >> 1);
                    if (i3 - i2 < 0) {
                        i3 = i2;
                    }
                    sArr = Arrays.copyOf(sArr, i3);
                }
                sArr[i] = (short) jSONReader.readInt32Value();
                i = i2;
            }
            jSONReader.nextIfComma();
            short[] copyOf = Arrays.copyOf(sArr, i);
            Function<short[], Object> function = this.builder;
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
        if (jSONReader.nextIfMatch(JSONB.Constants.BC_TYPED_ANY)) {
            long readTypeHashCode = jSONReader.readTypeHashCode();
            if (readTypeHashCode != HASH_TYPE && readTypeHashCode != ObjectReaderImplInt16Array.HASH_TYPE) {
                throw new JSONException("not support autoType : " + jSONReader.getString());
            }
        }
        int startArray = jSONReader.startArray();
        if (startArray == -1) {
            return null;
        }
        short[] sArr = new short[startArray];
        for (int i = 0; i < startArray; i++) {
            sArr[i] = (short) jSONReader.readInt32Value();
        }
        Function<short[], Object> function = this.builder;
        return function != null ? function.apply(sArr) : sArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        short shortValue;
        short[] sArr = new short[collection.size()];
        int i = 0;
        for (Object obj : collection) {
            if (obj == null) {
                shortValue = 0;
            } else if (obj instanceof Number) {
                shortValue = ((Number) obj).shortValue();
            } else {
                Function typeConvert = JSONFactory.getDefaultObjectReaderProvider().getTypeConvert(obj.getClass(), Short.TYPE);
                if (typeConvert == null) {
                    throw new JSONException("can not cast to short " + obj.getClass());
                }
                shortValue = ((Short) typeConvert.apply(obj)).shortValue();
            }
            sArr[i] = shortValue;
            i++;
        }
        Function<short[], Object> function = this.builder;
        return function != null ? function.apply(sArr) : sArr;
    }
}
