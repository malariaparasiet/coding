package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPObject;
import com.alibaba.fastjson2.JSONReader;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public class ObjectReaderImplJSONP implements ObjectReader {
    private final Class objectClass;

    public ObjectReaderImplJSONP(Class cls) {
        this.objectClass = cls;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        JSONPObject jSONPObject;
        String readFieldNameUnquote = jSONReader.readFieldNameUnquote();
        if (jSONReader.nextIfMatch('.')) {
            readFieldNameUnquote = readFieldNameUnquote + '.' + jSONReader.readFieldNameUnquote();
        }
        char current = jSONReader.current();
        if (current == '/' && jSONReader.nextIfMatchIdent('/', '*', '*', '/')) {
            current = jSONReader.current();
        }
        if (current != '(') {
            throw new JSONException(jSONReader.info("illegal jsonp input"));
        }
        jSONReader.next();
        Class cls = this.objectClass;
        if (cls == JSONObject.class) {
            jSONPObject = new JSONPObject(readFieldNameUnquote);
        } else {
            try {
                jSONPObject = (JSONPObject) cls.newInstance();
                jSONPObject.setFunction(readFieldNameUnquote);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new JSONException("create jsonp instance error", e);
            }
        }
        while (!jSONReader.isEnd()) {
            if (!jSONReader.nextIfMatch(')')) {
                jSONPObject.addParameter(jSONReader.readAny());
            } else {
                jSONReader.nextIfMatch(';');
                jSONReader.nextIfMatchIdent('/', '*', '*', '/');
                return jSONPObject;
            }
        }
        throw new JSONException(jSONReader.info("illegal jsonp input"));
    }
}
