package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class ObjectWriterImplBigDecimal extends ObjectWriterPrimitiveImpl {
    static final ObjectWriterImplBigDecimal INSTANCE = new ObjectWriterImplBigDecimal(null, null);
    private final DecimalFormat format;
    final Function<Object, BigDecimal> function;

    public ObjectWriterImplBigDecimal(DecimalFormat decimalFormat, Function<Object, BigDecimal> function) {
        this.format = decimalFormat;
        this.function = function;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeJSONB(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        BigDecimal bigDecimal;
        Function<Object, BigDecimal> function = this.function;
        if (function != null && obj != null) {
            bigDecimal = function.apply(obj);
        } else {
            bigDecimal = (BigDecimal) obj;
        }
        jSONWriter.writeDecimal(bigDecimal, j, this.format);
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        BigDecimal bigDecimal;
        Function<Object, BigDecimal> function = this.function;
        if (function != null && obj != null) {
            bigDecimal = function.apply(obj);
        } else {
            bigDecimal = (BigDecimal) obj;
        }
        jSONWriter.writeDecimal(bigDecimal, j, this.format);
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriterPrimitiveImpl
    public Function getFunction() {
        return this.function;
    }
}
