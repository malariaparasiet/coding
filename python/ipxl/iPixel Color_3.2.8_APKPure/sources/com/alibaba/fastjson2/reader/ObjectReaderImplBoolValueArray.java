package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.util.Arrays;

/* loaded from: classes2.dex */
class ObjectReaderImplBoolValueArray extends ObjectReaderPrimitive {
    static final ObjectReaderImplBoolValueArray INSTANCE = new ObjectReaderImplBoolValueArray();
    static final long TYPE_HASH = Fnv.hashCode64("[Z");

    ObjectReaderImplBoolValueArray() {
        super(boolean[].class);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            boolean[] zArr = new boolean[16];
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                int i2 = i + 1;
                if (i2 - zArr.length > 0) {
                    int length = zArr.length;
                    int i3 = length + (length >> 1);
                    if (i3 - i2 < 0) {
                        i3 = i2;
                    }
                    zArr = Arrays.copyOf(zArr, i3);
                }
                zArr[i] = jSONReader.readBoolValue();
                i = i2;
            }
            jSONReader.nextIfComma();
            return Arrays.copyOf(zArr, i);
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
        boolean[] zArr = new boolean[startArray];
        for (int i = 0; i < startArray; i++) {
            zArr[i] = jSONReader.readBoolValue();
        }
        return zArr;
    }
}
