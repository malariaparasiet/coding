package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class ObjectArrayReader extends ObjectReaderPrimitive {
    public static final ObjectArrayReader INSTANCE = new ObjectArrayReader();
    public static final long TYPE_HASH_CODE = Fnv.hashCode64("[O");

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public /* bridge */ /* synthetic */ Object createInstance(long j) {
        return super.createInstance(j);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public /* bridge */ /* synthetic */ Class getObjectClass() {
        return super.getObjectClass();
    }

    public ObjectArrayReader() {
        super(Object[].class);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object[] createInstance(Collection collection, long j) {
        Object[] objArr = new Object[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
        return objArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Object readString;
        if (jSONReader.nextIfNullOrEmptyString()) {
            return null;
        }
        if (jSONReader.nextIfArrayStart()) {
            Object[] objArr = new Object[16];
            int i = 0;
            while (!jSONReader.nextIfArrayEnd()) {
                int i2 = i + 1;
                if (i2 - objArr.length > 0) {
                    int length = objArr.length;
                    int i3 = length + (length >> 1);
                    if (i3 - i2 < 0) {
                        i3 = i2;
                    }
                    objArr = Arrays.copyOf(objArr, i3);
                }
                char current = jSONReader.current();
                if (current == '\"') {
                    readString = jSONReader.readString();
                } else {
                    if (current != '+') {
                        if (current != '[') {
                            if (current != 'f') {
                                if (current == 'n') {
                                    jSONReader.readNull();
                                    readString = null;
                                } else if (current != 't') {
                                    if (current == '{') {
                                        readString = jSONReader.read((Class<Object>) Object.class);
                                    } else if (current != '-' && current != '.') {
                                        switch (current) {
                                            case '0':
                                            case '1':
                                            case '2':
                                            case '3':
                                            case '4':
                                            case '5':
                                            case '6':
                                            case '7':
                                            case '8':
                                            case '9':
                                                break;
                                            default:
                                                throw new JSONException(jSONReader.info());
                                        }
                                    }
                                }
                            }
                            readString = Boolean.valueOf(jSONReader.readBoolValue());
                        } else {
                            readString = jSONReader.readArray();
                        }
                    }
                    readString = jSONReader.readNumber();
                }
                objArr[i] = readString;
                i = i2;
            }
            jSONReader.nextIfComma();
            return Arrays.copyOf(objArr, i);
        }
        if (jSONReader.current() == '{') {
            jSONReader.next();
            if (jSONReader.readFieldNameHashCode() == HASH_TYPE) {
                jSONReader.readString();
            }
        }
        if (jSONReader.isString()) {
            String readString2 = jSONReader.readString();
            if (readString2 == null || readString2.isEmpty()) {
                return null;
            }
            if (ObjectReader.VALUE_NAME.equals(readString2)) {
                jSONReader.next();
                Object readObject = readObject(jSONReader, type, obj, j);
                jSONReader.nextIfObjectEnd();
                return readObject;
            }
        }
        throw new JSONException(jSONReader.info("TODO"));
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Object readAny;
        ObjectReader checkAutoType;
        if (jSONReader.getType() == -110 && (checkAutoType = jSONReader.checkAutoType(Object[].class, TYPE_HASH_CODE, j)) != this) {
            return checkAutoType.readJSONBObject(jSONReader, type, obj, j);
        }
        int startArray = jSONReader.startArray();
        if (startArray == -1) {
            return null;
        }
        Object[] objArr = new Object[startArray];
        for (int i = 0; i < startArray; i++) {
            byte type2 = jSONReader.getType();
            if (type2 >= 73 && type2 <= 125) {
                readAny = jSONReader.readString();
            } else if (type2 == -110) {
                ObjectReader checkAutoType2 = jSONReader.checkAutoType(Object.class, 0L, j);
                if (checkAutoType2 != null) {
                    readAny = checkAutoType2.readJSONBObject(jSONReader, null, null, j);
                } else {
                    readAny = jSONReader.readAny();
                }
            } else if (type2 == -81) {
                jSONReader.next();
                readAny = null;
            } else if (type2 == -79) {
                jSONReader.next();
                readAny = Boolean.TRUE;
            } else if (type2 == -80) {
                jSONReader.next();
                readAny = Boolean.FALSE;
            } else if (type2 == -66) {
                readAny = Long.valueOf(jSONReader.readInt64Value());
            } else {
                readAny = jSONReader.readAny();
            }
            objArr[i] = readAny;
        }
        return objArr;
    }
}
