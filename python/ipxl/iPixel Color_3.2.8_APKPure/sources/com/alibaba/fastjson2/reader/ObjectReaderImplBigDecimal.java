package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.function.impl.ToBigDecimal;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class ObjectReaderImplBigDecimal extends ObjectReaderPrimitive {
    static final ObjectReaderImplBigDecimal INSTANCE = new ObjectReaderImplBigDecimal(null);
    private final Function converter;
    final Function<BigDecimal, Object> function;

    public ObjectReaderImplBigDecimal(Function<BigDecimal, Object> function) {
        super(BigDecimal.class);
        this.converter = new ToBigDecimal();
        this.function = function;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        BigDecimal readBigDecimal = jSONReader.readBigDecimal();
        Function<BigDecimal, Object> function = this.function;
        return function != null ? function.apply(readBigDecimal) : readBigDecimal;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        BigDecimal readBigDecimal = jSONReader.readBigDecimal();
        Function<BigDecimal, Object> function = this.function;
        return function != null ? function.apply(readBigDecimal) : readBigDecimal;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Map map, long j) {
        Object obj = map.get("value");
        if (obj == null) {
            obj = map.get("$numberDecimal");
        }
        if (!(obj instanceof BigDecimal)) {
            obj = this.converter.apply(obj);
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        Function<BigDecimal, Object> function = this.function;
        return function != null ? function.apply(bigDecimal) : bigDecimal;
    }
}
