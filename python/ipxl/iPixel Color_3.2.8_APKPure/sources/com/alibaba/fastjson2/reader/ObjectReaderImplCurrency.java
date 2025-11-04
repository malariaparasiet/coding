package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.util.Currency;

/* loaded from: classes2.dex */
final class ObjectReaderImplCurrency extends ObjectReaderPrimitive {
    static final ObjectReaderImplCurrency INSTANCE = new ObjectReaderImplCurrency();
    static final long TYPE_HASH = Fnv.hashCode64("Currency");

    ObjectReaderImplCurrency() {
        super(Currency.class);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.getType() == -110) {
            jSONReader.next();
            long readTypeHashCode = jSONReader.readTypeHashCode();
            if (readTypeHashCode != TYPE_HASH && readTypeHashCode != -7860540621745740270L) {
                throw new JSONException(jSONReader.info("currency not support input autoTypeClass " + jSONReader.getString()));
            }
        }
        String readString = jSONReader.readString();
        if (readString == null || readString.isEmpty()) {
            return null;
        }
        return Currency.getInstance(readString);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        String readString;
        if (jSONReader.isObject()) {
            JSONObject jSONObject = new JSONObject();
            jSONReader.readObject(jSONObject, new JSONReader.Feature[0]);
            readString = jSONObject.getString("currency");
            if (readString == null) {
                readString = jSONObject.getString("currencyCode");
            }
        } else {
            readString = jSONReader.readString();
        }
        if (readString == null || readString.isEmpty()) {
            return null;
        }
        return Currency.getInstance(readString);
    }
}
