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
class ObjectReaderImplInt16Array extends ObjectReaderPrimitive {
    static final ObjectReaderImplInt16Array INSTANCE = new ObjectReaderImplInt16Array();
    static final long HASH_TYPE = Fnv.hashCode64("[Short");

    ObjectReaderImplInt16Array() {
        super(Short[].class);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        Short sh;
        Short[] shArr = new Short[collection.size()];
        int i = 0;
        for (Object obj : collection) {
            if (obj == null) {
                sh = null;
            } else if (obj instanceof Number) {
                sh = Short.valueOf(((Number) obj).shortValue());
            } else {
                Function typeConvert = JSONFactory.getDefaultObjectReaderProvider().getTypeConvert(obj.getClass(), Short.class);
                if (typeConvert == null) {
                    throw new JSONException("can not cast to Short " + obj.getClass());
                }
                sh = (Short) typeConvert.apply(obj);
            }
            shArr[i] = sh;
            i++;
        }
        return shArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            Short[] shArr = new Short[16];
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                if (jSONReader.isEnd()) {
                    throw new JSONException(jSONReader.info("input end"));
                }
                int i2 = i + 1;
                if (i2 - shArr.length > 0) {
                    int length = shArr.length;
                    int i3 = length + (length >> 1);
                    if (i3 - i2 < 0) {
                        i3 = i2;
                    }
                    shArr = (Short[]) Arrays.copyOf(shArr, i3);
                }
                Integer readInt32 = jSONReader.readInt32();
                shArr[i] = Short.valueOf(readInt32 == null ? (short) 0 : readInt32.shortValue());
                i = i2;
            }
            jSONReader.nextIfComma();
            return Arrays.copyOf(shArr, i);
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
            if (readTypeHashCode != HASH_TYPE && readTypeHashCode != ObjectReaderImplInt16ValueArray.HASH_TYPE) {
                throw new JSONException(jSONReader.info("not support type " + jSONReader.getString()));
            }
        }
        int startArray = jSONReader.startArray();
        if (startArray == -1) {
            return null;
        }
        Short[] shArr = new Short[startArray];
        for (int i = 0; i < startArray; i++) {
            Integer readInt32 = jSONReader.readInt32();
            shArr[i] = readInt32 == null ? null : Short.valueOf(readInt32.shortValue());
        }
        return shArr;
    }
}
