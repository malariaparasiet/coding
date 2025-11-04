package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.MultiType;
import java.lang.reflect.Type;
import java.util.Collection;

/* loaded from: classes2.dex */
final class ObjectArrayReaderMultiType implements ObjectReader {
    final ObjectReader[] readers;
    final Type[] types;

    ObjectArrayReaderMultiType(MultiType multiType) {
        int size = multiType.size();
        Type[] typeArr = new Type[size];
        for (int i = 0; i < multiType.size(); i++) {
            typeArr[i] = multiType.getType(i);
        }
        this.types = typeArr;
        this.readers = new ObjectReader[size];
    }

    ObjectReader getObjectReader(JSONReader jSONReader, int i) {
        ObjectReader objectReader = this.readers[i];
        if (objectReader != null) {
            return objectReader;
        }
        ObjectReader objectReader2 = jSONReader.getObjectReader(this.types[i]);
        this.readers[i] = objectReader2;
        return objectReader2;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        long j2;
        Object readObject;
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, 0L);
        }
        JSONReader jSONReader2 = jSONReader;
        if (jSONReader2.nextIfNullOrEmptyString()) {
            return null;
        }
        Object[] objArr = new Object[this.types.length];
        if (jSONReader2.nextIfArrayStart()) {
            int i = 0;
            while (!jSONReader2.nextIfArrayEnd()) {
                if (jSONReader2.isReference()) {
                    String readReference = jSONReader2.readReference();
                    if ("..".equals(readReference)) {
                        readObject = objArr;
                    } else {
                        jSONReader2.addResolveTask(objArr, i, JSONPath.of(readReference));
                        readObject = null;
                    }
                    j2 = j;
                } else {
                    JSONReader jSONReader3 = jSONReader2;
                    j2 = j;
                    readObject = getObjectReader(jSONReader3, i).readObject(jSONReader3, this.types[i], Integer.valueOf(i), j2);
                    jSONReader2 = jSONReader3;
                }
                objArr[i] = readObject;
                jSONReader2.nextIfComma();
                i++;
                j = j2;
            }
            jSONReader2.nextIfComma();
            return objArr;
        }
        throw new JSONException(jSONReader2.info("TODO"));
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        JSONReader jSONReader2;
        long j2;
        Object readObject;
        int startArray = jSONReader.startArray();
        if (startArray == -1) {
            return null;
        }
        Object[] objArr = new Object[this.types.length];
        int i = 0;
        while (i < startArray) {
            if (jSONReader.isReference()) {
                String readReference = jSONReader.readReference();
                if ("..".equals(readReference)) {
                    readObject = objArr;
                } else {
                    jSONReader.addResolveTask(objArr, i, JSONPath.of(readReference));
                    readObject = null;
                }
                jSONReader2 = jSONReader;
                j2 = j;
            } else {
                jSONReader2 = jSONReader;
                j2 = j;
                readObject = getObjectReader(jSONReader, i).readObject(jSONReader2, this.types[i], Integer.valueOf(i), j2);
            }
            objArr[i] = readObject;
            i++;
            jSONReader = jSONReader2;
            j = j2;
        }
        return objArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        return new Object[this.types.length];
    }
}
