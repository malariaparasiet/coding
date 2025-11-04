package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
final class FieldReaderInt32ValueArrayFinalField<T> extends FieldReaderObjectField<T> {
    @Override // com.alibaba.fastjson2.reader.FieldReader
    public boolean isReadOnly() {
        return true;
    }

    FieldReaderInt32ValueArrayFinalField(String str, Class cls, int i, long j, String str2, int[] iArr, JSONSchema jSONSchema, Field field) {
        super(str, cls, cls, i, j, str2, null, iArr, jSONSchema, field);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, T t) {
        if (jSONReader.readIfNull()) {
            return;
        }
        try {
            int[] iArr = (int[]) this.field.get(t);
            if (jSONReader.nextIfArrayStart()) {
                int i = 0;
                while (!jSONReader.nextIfArrayEnd()) {
                    int readInt32Value = jSONReader.readInt32Value();
                    if (iArr != null && i < iArr.length) {
                        iArr[i] = readInt32Value;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            throw new JSONException(jSONReader.info("set " + this.fieldName + " error"), e);
        }
    }
}
