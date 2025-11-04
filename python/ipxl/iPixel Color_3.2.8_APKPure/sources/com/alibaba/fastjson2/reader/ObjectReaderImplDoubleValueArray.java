package com.alibaba.fastjson2.reader;

import androidx.camera.video.AudioStats;
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
class ObjectReaderImplDoubleValueArray extends ObjectReaderPrimitive {
    static final ObjectReaderImplDoubleValueArray INSTANCE = new ObjectReaderImplDoubleValueArray(null);
    static final long TYPE_HASH = Fnv.hashCode64("[D");
    final Function<double[], Object> builder;

    ObjectReaderImplDoubleValueArray(Function<double[], Object> function) {
        super(double[].class);
        this.builder = function;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            double[] dArr = new double[16];
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                if (jSONReader.isEnd()) {
                    throw new JSONException(jSONReader.info("input end"));
                }
                int i2 = i + 1;
                if (i2 - dArr.length > 0) {
                    int length = dArr.length;
                    int i3 = length + (length >> 1);
                    if (i3 - i2 < 0) {
                        i3 = i2;
                    }
                    dArr = Arrays.copyOf(dArr, i3);
                }
                dArr[i] = jSONReader.readDoubleValue();
                i = i2;
            }
            jSONReader.nextIfComma();
            double[] copyOf = Arrays.copyOf(dArr, i);
            Function<double[], Object> function = this.builder;
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
        double[] dArr = new double[startArray];
        for (int i = 0; i < startArray; i++) {
            dArr[i] = jSONReader.readDoubleValue();
        }
        Function<double[], Object> function = this.builder;
        return function != null ? function.apply(dArr) : dArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        double doubleValue;
        double[] dArr = new double[collection.size()];
        int i = 0;
        for (Object obj : collection) {
            if (obj == null) {
                doubleValue = AudioStats.AUDIO_AMPLITUDE_NONE;
            } else if (obj instanceof Number) {
                doubleValue = ((Number) obj).doubleValue();
            } else {
                Function typeConvert = JSONFactory.getDefaultObjectReaderProvider().getTypeConvert(obj.getClass(), Double.TYPE);
                if (typeConvert == null) {
                    throw new JSONException("can not cast to double " + obj.getClass());
                }
                doubleValue = ((Double) typeConvert.apply(obj)).doubleValue();
            }
            dArr[i] = doubleValue;
            i++;
        }
        Function<double[], Object> function = this.builder;
        return function != null ? function.apply(dArr) : dArr;
    }
}
