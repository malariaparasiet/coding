package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.lang.reflect.Parameter;
import java.util.Locale;

/* loaded from: classes2.dex */
final class FieldReaderInt32Param<T> extends FieldReaderObjectParam<T> {
    FieldReaderInt32Param(String str, Class cls, String str2, Parameter parameter, int i, long j, String str3, Locale locale, Object obj, JSONSchema jSONSchema) {
        super(str, cls, cls, str2, parameter, i, j, str3, locale, obj, jSONSchema);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public Object readFieldValue(JSONReader jSONReader) {
        Integer readInt32 = jSONReader.readInt32();
        if (readInt32 == null && this.fieldClass == Integer.TYPE && (jSONReader.features(this.features) & JSONReader.Feature.ErrorOnNullForPrimitives.mask) != 0) {
            throw new JSONException(jSONReader.info("int value not support input null"));
        }
        return readInt32;
    }
}
