package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.function.Function;

/* loaded from: classes2.dex */
public final class ObjectReaderImplInt64ValueArray extends ObjectReaderPrimitive {
    final Function<long[], Object> builder;
    static final ObjectReaderImplInt64ValueArray INSTANCE = new ObjectReaderImplInt64ValueArray(long[].class, null);
    public static final long HASH_TYPE = Fnv.hashCode64("[J");

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public /* bridge */ /* synthetic */ Object createInstance(long j) {
        return super.createInstance(j);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public /* bridge */ /* synthetic */ Class getObjectClass() {
        return super.getObjectClass();
    }

    ObjectReaderImplInt64ValueArray(Class cls, Function<long[], Object> function) {
        super(cls);
        this.builder = function;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Function<long[], Object> function;
        long[] readInt64ValueArray = jSONReader.readInt64ValueArray();
        return (readInt64ValueArray == null || (function = this.builder) == null) ? readInt64ValueArray : function.apply(readInt64ValueArray);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Function<long[], Object> function;
        long[] readInt64ValueArray = jSONReader.readInt64ValueArray();
        return (readInt64ValueArray == null || (function = this.builder) == null) ? readInt64ValueArray : function.apply(readInt64ValueArray);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        long longValue;
        long[] jArr = new long[collection.size()];
        int i = 0;
        for (Object obj : collection) {
            if (obj == null) {
                longValue = 0;
            } else if (obj instanceof Number) {
                longValue = ((Number) obj).longValue();
            } else {
                Function typeConvert = JSONFactory.getDefaultObjectReaderProvider().getTypeConvert(obj.getClass(), Long.TYPE);
                if (typeConvert == null) {
                    throw new JSONException("can not cast to long " + obj.getClass());
                }
                longValue = ((Long) typeConvert.apply(obj)).longValue();
            }
            jArr[i] = longValue;
            i++;
        }
        Function<long[], Object> function = this.builder;
        return function != null ? function.apply(jArr) : jArr;
    }
}
