package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Locale;
import java.util.function.BiConsumer;

/* loaded from: classes2.dex */
final class FieldReaderLocalDate extends FieldReaderObject {
    public FieldReaderLocalDate(String str, Type type, Class cls, int i, long j, String str2, Locale locale, Object obj, JSONSchema jSONSchema, Method method, Field field, BiConsumer biConsumer) {
        super(str, type, cls, i, j, str2, locale, obj, jSONSchema, method, field, biConsumer);
        this.initReader = ObjectReaderImplLocalDate.of(str2, locale);
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public ObjectReader getObjectReader(JSONReader jSONReader) {
        return this.initReader;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public ObjectReader getObjectReader(JSONReader.Context context) {
        return this.initReader;
    }

    @Override // com.alibaba.fastjson2.reader.FieldReaderObject, com.alibaba.fastjson2.reader.FieldReader
    public void readFieldValue(JSONReader jSONReader, Object obj) {
        LocalDate readLocalDate;
        if (jSONReader.jsonb) {
            readLocalDate = (LocalDate) this.initReader.readJSONBObject(jSONReader, this.fieldType, this.fieldName, this.features);
        } else if (this.format != null) {
            readLocalDate = (LocalDate) this.initReader.readObject(jSONReader, this.fieldType, this.fieldName, this.features);
        } else {
            readLocalDate = jSONReader.readLocalDate();
        }
        accept((FieldReaderLocalDate) obj, (Object) readLocalDate);
    }
}
