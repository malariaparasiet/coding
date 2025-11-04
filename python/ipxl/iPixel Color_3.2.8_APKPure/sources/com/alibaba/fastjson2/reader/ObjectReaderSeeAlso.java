package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.annotation.JSONType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
final class ObjectReaderSeeAlso<T> extends ObjectReaderAdapter<T> {
    ObjectReaderSeeAlso(Class cls, Supplier<T> supplier, String str, Class[] clsArr, String[] strArr, Class cls2, FieldReader... fieldReaderArr) {
        super(cls, str, null, JSONReader.Feature.SupportAutoType.mask, null, supplier, null, clsArr, strArr, cls2, fieldReaderArr);
    }

    ObjectReaderSeeAlso addSubType(Class cls, String str) {
        JSONType jSONType;
        for (int i = 0; i < this.seeAlso.length; i++) {
            if (this.seeAlso[i] == cls) {
                return this;
            }
        }
        Class[] clsArr = (Class[]) Arrays.copyOf(this.seeAlso, this.seeAlso.length + 1);
        String[] strArr = (String[]) Arrays.copyOf(this.seeAlsoNames, this.seeAlsoNames.length + 1);
        clsArr[clsArr.length - 1] = cls;
        if (str == null && (jSONType = (JSONType) cls.getAnnotation(JSONType.class)) != null) {
            str = jSONType.typeName();
        }
        if (str != null) {
            strArr[strArr.length - 1] = str;
        }
        return new ObjectReaderSeeAlso(this.objectClass, this.creator, this.typeKey, clsArr, strArr, this.seeAlsoDefault, this.fieldReaders);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReader
    public T createInstance(long j) {
        if (this.creator == null) {
            return null;
        }
        return this.creator.get();
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReader
    public T readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (jSONReader.nextIfNull()) {
            return null;
        }
        ObjectReader checkAutoType = jSONReader.checkAutoType(this.objectClass, this.typeNameHash, this.features | j);
        if (checkAutoType != null && checkAutoType.getObjectClass() != this.objectClass) {
            return (T) checkAutoType.readJSONBObject(jSONReader, type, obj, j);
        }
        if (!this.serializable) {
            jSONReader.errorOnNoneSerializable(this.objectClass);
        }
        if (jSONReader.isArray()) {
            if (jSONReader.isSupportBeanArray()) {
                return readArrayMappingJSONBObject(jSONReader, type, obj, j);
            }
            throw new JSONException(jSONReader.info("expect object, but " + JSONB.typeName(jSONReader.getType())));
        }
        JSONReader.SavePoint mark = jSONReader.mark();
        jSONReader.nextIfObjectStart();
        int i = 0;
        T t = null;
        while (!jSONReader.nextIfObjectEnd()) {
            long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
            if (readFieldNameHashCode == this.typeKeyHashCode) {
                long readValueHashCode = jSONReader.readValueHashCode();
                JSONReader.Context context = jSONReader.getContext();
                ObjectReader autoType = autoType(context, readValueHashCode);
                if (autoType == null) {
                    String string = jSONReader.getString();
                    ObjectReader objectReaderAutoType = context.getObjectReaderAutoType(string, null);
                    if (objectReaderAutoType == null) {
                        throw new JSONException(jSONReader.info("autoType not support : " + string));
                    }
                    autoType = objectReaderAutoType;
                }
                if (autoType != this) {
                    if (i != 0) {
                        jSONReader.reset(mark);
                    }
                    jSONReader.setTypeRedirect(true);
                    return (T) autoType.readJSONBObject(jSONReader, type, obj, j);
                }
            } else if (readFieldNameHashCode != 0) {
                FieldReader fieldReader = getFieldReader(readFieldNameHashCode);
                if (fieldReader == null && jSONReader.isSupportSmartMatch(this.features | j)) {
                    fieldReader = getFieldReaderLCase(jSONReader.getNameHashCodeLCase());
                }
                if (fieldReader == null) {
                    processExtra(jSONReader, t);
                } else {
                    if (t == null) {
                        t = createInstance(jSONReader.getContext().getFeatures() | j);
                    }
                    fieldReader.readFieldValue(jSONReader, t);
                }
            }
            i++;
        }
        if (t == null) {
            t = createInstance(jSONReader.getContext().getFeatures() | j);
        }
        if (this.schema != null) {
            this.schema.assertValidate(t);
        }
        return t;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0254 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x024d A[SYNTHETIC] */
    @Override // com.alibaba.fastjson2.reader.ObjectReaderBean, com.alibaba.fastjson2.reader.ObjectReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public T readObject(com.alibaba.fastjson2.JSONReader r23, java.lang.reflect.Type r24, java.lang.Object r25, long r26) {
        /*
            Method dump skipped, instructions count: 664
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.reader.ObjectReaderSeeAlso.readObject(com.alibaba.fastjson2.JSONReader, java.lang.reflect.Type, java.lang.Object, long):java.lang.Object");
    }
}
