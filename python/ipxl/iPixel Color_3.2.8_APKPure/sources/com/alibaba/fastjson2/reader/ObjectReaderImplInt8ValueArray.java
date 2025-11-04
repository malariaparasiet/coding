package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.function.Function;

/* loaded from: classes2.dex */
class ObjectReaderImplInt8ValueArray extends ObjectReaderPrimitive {
    final Function<byte[], Object> builder;
    final long features;
    final String format;
    static final ObjectReaderImplInt8ValueArray INSTANCE = new ObjectReaderImplInt8ValueArray(null);
    static final long HASH_TYPE = Fnv.hashCode64("[B");

    ObjectReaderImplInt8ValueArray(String str) {
        super(byte[].class);
        this.format = str;
        this.builder = null;
        this.features = 0L;
    }

    ObjectReaderImplInt8ValueArray(Function<byte[], Object> function, String str) {
        super(byte[].class);
        this.format = str;
        this.features = "base64".equals(str) ? JSONReader.Feature.Base64StringAsByteArray.mask : 0L;
        this.builder = function;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        byte[] bArr = null;
        if (jSONReader.readIfNull()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            byte[] bArr2 = new byte[16];
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                if (jSONReader.isEnd()) {
                    throw new JSONException(jSONReader.info("input end"));
                }
                int i2 = i + 1;
                if (i2 - bArr2.length > 0) {
                    int length = bArr2.length;
                    int i3 = length + (length >> 1);
                    if (i3 - i2 < 0) {
                        i3 = i2;
                    }
                    bArr2 = Arrays.copyOf(bArr2, i3);
                }
                bArr2[i] = (byte) jSONReader.readInt32Value();
                i = i2;
            }
            jSONReader.nextIfComma();
            byte[] copyOf = Arrays.copyOf(bArr2, i);
            Function<byte[], Object> function = this.builder;
            return function != null ? function.apply(copyOf) : copyOf;
        }
        if (jSONReader.isString()) {
            if ((jSONReader.features(j | this.features) & JSONReader.Feature.Base64StringAsByteArray.mask) != 0) {
                bArr = jSONReader.readBase64();
            } else {
                String readString = jSONReader.readString();
                if (!readString.isEmpty()) {
                    throw new JSONException(jSONReader.info("illegal input : " + readString));
                }
            }
            Function<byte[], Object> function2 = this.builder;
            return function2 != null ? function2.apply(bArr) : bArr;
        }
        throw new JSONException(jSONReader.info("TODO"));
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        byte[] bArr;
        if (jSONReader.nextIfMatch(JSONB.Constants.BC_TYPED_ANY)) {
            long readTypeHashCode = jSONReader.readTypeHashCode();
            if (readTypeHashCode != HASH_TYPE && readTypeHashCode != ObjectReaderImplInt8Array.HASH_TYPE) {
                throw new JSONException("not support autoType : " + jSONReader.getString());
            }
        }
        if (jSONReader.isBinary()) {
            bArr = jSONReader.readBinary();
        } else if (jSONReader.isString()) {
            String readString = jSONReader.readString();
            if (readString != null && readString.startsWith("data:image/jpeg;base64,")) {
                readString = readString.substring("data:image/jpeg;base64,".length());
            }
            bArr = Base64.getDecoder().decode(readString);
        } else {
            int startArray = jSONReader.startArray();
            if (startArray == -1) {
                return null;
            }
            byte[] bArr2 = new byte[startArray];
            for (int i = 0; i < startArray; i++) {
                bArr2[i] = (byte) jSONReader.readInt32Value();
            }
            bArr = bArr2;
        }
        Function<byte[], Object> function = this.builder;
        return function != null ? function.apply(bArr) : bArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object createInstance(Collection collection, long j) {
        byte byteValue;
        byte[] bArr = new byte[collection.size()];
        int i = 0;
        for (Object obj : collection) {
            if (obj == null) {
                byteValue = 0;
            } else if (obj instanceof Number) {
                byteValue = ((Number) obj).byteValue();
            } else {
                Function typeConvert = JSONFactory.getDefaultObjectReaderProvider().getTypeConvert(obj.getClass(), Byte.TYPE);
                if (typeConvert == null) {
                    throw new JSONException("can not cast to byte " + obj.getClass());
                }
                byteValue = ((Byte) typeConvert.apply(obj)).byteValue();
            }
            bArr[i] = byteValue;
            i++;
        }
        Function<byte[], Object> function = this.builder;
        return function != null ? function.apply(bArr) : bArr;
    }
}
