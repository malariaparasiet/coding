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
public final class ObjectReaderImplInt32ValueArray extends ObjectReaderPrimitive {
    final Function<int[], Object> builder;
    static final ObjectReaderImplInt32ValueArray INSTANCE = new ObjectReaderImplInt32ValueArray(int[].class, null);
    public static final long HASH_TYPE = Fnv.hashCode64("[I");

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public /* bridge */ /* synthetic */ Object createInstance(long j) {
        return super.createInstance(j);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public /* bridge */ /* synthetic */ Class getObjectClass() {
        return super.getObjectClass();
    }

    ObjectReaderImplInt32ValueArray(Class cls, Function<int[], Object> function) {
        super(cls);
        this.builder = function;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, j);
        }
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            int[] iArr = new int[16];
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                if (jSONReader.isEnd()) {
                    throw new JSONException(jSONReader.info("input end"));
                }
                int i2 = i + 1;
                if (i2 - iArr.length > 0) {
                    int length = iArr.length;
                    int i3 = length + (length >> 1);
                    if (i3 - i2 < 0) {
                        i3 = i2;
                    }
                    iArr = Arrays.copyOf(iArr, i3);
                }
                iArr[i] = jSONReader.readInt32Value();
                i = i2;
            }
            jSONReader.nextIfComma();
            int[] copyOf = Arrays.copyOf(iArr, i);
            Function<int[], Object> function = this.builder;
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
            if (readTypeHashCode != HASH_TYPE && readTypeHashCode != ObjectReaderImplInt32Array.HASH_TYPE) {
                throw new JSONException("not support autoType : " + jSONReader.getString());
            }
        }
        int startArray = jSONReader.startArray();
        if (startArray == -1) {
            return null;
        }
        int[] iArr = new int[startArray];
        for (int i = 0; i < startArray; i++) {
            iArr[i] = jSONReader.readInt32Value();
        }
        Function<int[], Object> function = this.builder;
        return function != null ? function.apply(iArr) : iArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        int intValue;
        int[] iArr = new int[collection.size()];
        int i = 0;
        for (Object obj : collection) {
            if (obj == null) {
                intValue = 0;
            } else if (obj instanceof Number) {
                intValue = ((Number) obj).intValue();
            } else {
                Function typeConvert = JSONFactory.getDefaultObjectReaderProvider().getTypeConvert(obj.getClass(), Integer.TYPE);
                if (typeConvert == null) {
                    throw new JSONException("can not cast to int " + obj.getClass());
                }
                intValue = ((Integer) typeConvert.apply(obj)).intValue();
            }
            iArr[i] = intValue;
            i++;
        }
        Function<int[], Object> function = this.builder;
        return function != null ? function.apply(iArr) : iArr;
    }
}
