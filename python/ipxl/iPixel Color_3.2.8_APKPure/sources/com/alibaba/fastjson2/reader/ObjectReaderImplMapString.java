package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
final class ObjectReaderImplMapString extends ObjectReaderImplMapTyped {
    public ObjectReaderImplMapString(Class cls, Class cls2, long j) {
        super(cls, cls2, null, String.class, j, null);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderImplMapTyped, com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        ObjectReaderImplMapString objectReaderImplMapString;
        JSONReader jSONReader2;
        Map map;
        Object put;
        JSONReader jSONReader3;
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, j);
        }
        if (jSONReader.nextIfObjectStart()) {
            objectReaderImplMapString = this;
            jSONReader2 = jSONReader;
        } else {
            if (jSONReader.current() == '[') {
                jSONReader.next();
                if (jSONReader.current() == '{') {
                    Object readObject = readObject(jSONReader, String.class, obj, j);
                    jSONReader3 = jSONReader;
                    if (jSONReader3.nextIfArrayEnd()) {
                        jSONReader3.nextIfComma();
                        return readObject;
                    }
                } else {
                    jSONReader3 = jSONReader;
                }
                throw new JSONException(jSONReader3.info("expect '{', but '['"));
            }
            objectReaderImplMapString = this;
            jSONReader2 = jSONReader;
            if (jSONReader2.nextIfNullOrEmptyString() || jSONReader2.nextIfMatchIdent(Typography.quote, 'n', 'u', 'l', 'l', Typography.quote)) {
                return null;
            }
        }
        JSONReader.Context context = jSONReader2.getContext();
        if (objectReaderImplMapString.instanceType == HashMap.class) {
            map = new HashMap();
        } else {
            map = (Map) createInstance(context.getFeatures() | j);
        }
        long features = j | context.getFeatures();
        int i = 0;
        while (!jSONReader2.nextIfObjectEnd()) {
            String readFieldName = jSONReader2.readFieldName();
            if (objectReaderImplMapString.multiValue && jSONReader2.nextIfArrayStart()) {
                JSONArray jSONArray = new JSONArray();
                while (!jSONReader2.nextIfArrayEnd()) {
                    jSONArray.add(jSONReader2.readString());
                }
                map.put(readFieldName, jSONArray);
            } else {
                String readString = jSONReader2.readString();
                if ((i != 0 || (JSONReader.Feature.SupportAutoType.mask & features) == 0 || !readFieldName.equals(getTypeKey())) && ((readString != null || (JSONReader.Feature.IgnoreNullPropertyValue.mask & features) == 0) && (put = map.put(readFieldName, readString)) != null && (JSONReader.Feature.DuplicateKeyValueAsArray.mask & features) != 0)) {
                    if (put instanceof Collection) {
                        ((Collection) put).add(readString);
                        map.put(readFieldName, put);
                    } else {
                        map.put(readFieldName, JSONArray.of(put, (Object) readString));
                    }
                }
            }
            i++;
        }
        jSONReader2.nextIfMatch(',');
        return map;
    }
}
