package com.alibaba.fastjson2;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.FieldReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.reader.ObjectReaderBean;
import com.alibaba.fastjson2.reader.ObjectReaderProvider;
import com.alibaba.fastjson2.reader.ValueConsumer;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.writer.FieldWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class JSONPathSingleName extends JSONPathSingle {
    final String name;
    final long nameHashCode;

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public boolean isRef() {
        return true;
    }

    public JSONPathSingleName(String str, JSONPathSegmentName jSONPathSegmentName, JSONPath.Feature... featureArr) {
        super(jSONPathSegmentName, str, featureArr);
        this.name = jSONPathSegmentName.name;
        this.nameHashCode = jSONPathSegmentName.nameHashCode;
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public Object eval(Object obj) {
        FieldWriter fieldWriter;
        Object fieldValue;
        Long l = null;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            fieldValue = map.get(this.name);
            if (fieldValue == null) {
                boolean isNumber = IOUtils.isNumber(this.name);
                Iterator it = map.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    Object key = entry.getKey();
                    if ((key instanceof Enum) && ((Enum) key).name().equals(this.name)) {
                        fieldValue = entry.getValue();
                        break;
                    }
                    if (key instanceof Long) {
                        if (l == null && isNumber) {
                            l = Long.valueOf(Long.parseLong(this.name));
                        }
                        if (key.equals(l)) {
                            fieldValue = entry.getValue();
                            break;
                        }
                    }
                }
            }
        } else {
            ObjectWriter objectWriter = getWriterContext().getObjectWriter(obj.getClass());
            if (objectWriter == null || (fieldWriter = objectWriter.getFieldWriter(this.nameHashCode)) == null) {
                return null;
            }
            fieldValue = fieldWriter.getFieldValue(obj);
        }
        if ((this.features & JSONPath.Feature.AlwaysReturnList.mask) == 0) {
            return fieldValue;
        }
        if (fieldValue == null) {
            return new JSONArray();
        }
        return JSONArray.of(fieldValue);
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public boolean remove(Object obj) {
        FieldReader fieldReader;
        if (obj == null) {
            return false;
        }
        if (obj instanceof Map) {
            return ((Map) obj).remove(this.name) != null;
        }
        ObjectReader objectReader = getReaderContext().getProvider().getObjectReader(obj.getClass());
        if (objectReader == null || (fieldReader = objectReader.getFieldReader(this.nameHashCode)) == null) {
            return false;
        }
        try {
            fieldReader.accept((FieldReader) obj, (Object) null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public boolean contains(Object obj) {
        FieldWriter fieldWriter;
        if (obj instanceof Map) {
            return ((Map) obj).containsKey(this.name);
        }
        if (obj instanceof List) {
            List list = (List) obj;
            return !list.isEmpty() && contains(list.get(0));
        }
        ObjectWriter objectWriter = getWriterContext().provider.getObjectWriter((Class) obj.getClass());
        return (objectWriter == null || (fieldWriter = objectWriter.getFieldWriter(this.nameHashCode)) == null || fieldWriter.getFieldValue(obj) == null) ? false : true;
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public void set(Object obj, Object obj2) {
        Function typeConvert;
        if (obj instanceof Map) {
            ((Map) obj).put(this.name, obj2);
            return;
        }
        ObjectReaderProvider provider = getReaderContext().getProvider();
        ObjectReader objectReader = provider.getObjectReader(obj.getClass());
        FieldReader fieldReader = objectReader.getFieldReader(this.nameHashCode);
        if (fieldReader != null) {
            if (obj2 != null) {
                Class<?> cls = obj2.getClass();
                Class cls2 = fieldReader.fieldClass;
                if (!fieldReader.supportAcceptType(cls) && (typeConvert = provider.getTypeConvert(cls, cls2)) != null) {
                    obj2 = typeConvert.apply(obj2);
                }
            }
            fieldReader.accept((FieldReader) obj, obj2);
            return;
        }
        if (objectReader instanceof ObjectReaderBean) {
            objectReader.acceptExtra(obj, this.name, obj2, 0L);
        }
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public void set(Object obj, Object obj2, JSONReader.Feature... featureArr) {
        Class<?> cls;
        Class<?> cls2;
        Function typeConvert;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object put = map.put(this.name, obj2);
            if (put != null) {
                for (JSONReader.Feature feature : featureArr) {
                    if (feature == JSONReader.Feature.DuplicateKeyValueAsArray) {
                        if (put instanceof Collection) {
                            ((Collection) put).add(obj2);
                            map.put(this.name, obj2);
                            return;
                        } else {
                            map.put(this.name, JSONArray.of(put, obj2));
                            return;
                        }
                    }
                }
                return;
            }
            return;
        }
        ObjectReaderProvider provider = getReaderContext().getProvider();
        FieldReader fieldReader = provider.getObjectReader(obj.getClass()).getFieldReader(this.nameHashCode);
        if (obj2 != null && (cls = obj2.getClass()) != (cls2 = fieldReader.fieldClass) && (typeConvert = provider.getTypeConvert(cls, cls2)) != null) {
            obj2 = typeConvert.apply(obj2);
        }
        fieldReader.accept((FieldReader) obj, obj2);
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public void setCallback(Object obj, BiFunction biFunction) {
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(this.name);
            if (obj2 != null || map.containsKey(this.name)) {
                map.put(this.name, biFunction.apply(map, obj2));
                return;
            }
            return;
        }
        Class<?> cls = obj.getClass();
        if (this.readerContext == null) {
            this.readerContext = JSONFactory.createReadContext();
        }
        FieldReader fieldReader = this.readerContext.provider.getObjectReader(cls).getFieldReader(this.nameHashCode);
        if (this.writerContext == null) {
            this.writerContext = JSONFactory.createWriteContext();
        }
        FieldWriter fieldWriter = this.writerContext.provider.getObjectWriter((Class) cls).getFieldWriter(this.nameHashCode);
        if (fieldReader == null || fieldWriter == null) {
            return;
        }
        fieldReader.accept((FieldReader) obj, biFunction.apply(obj, fieldWriter.getFieldValue(obj)));
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public void setInt(Object obj, int i) {
        if (obj instanceof Map) {
            ((Map) obj).put(this.name, Integer.valueOf(i));
        } else {
            getReaderContext().getProvider().getObjectReader(obj.getClass()).setFieldValue(obj, this.name, this.nameHashCode, i);
        }
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public void setLong(Object obj, long j) {
        if (obj instanceof Map) {
            ((Map) obj).put(this.name, Long.valueOf(j));
        } else {
            getReaderContext().getProvider().getObjectReader(obj.getClass()).setFieldValue(obj, this.name, this.nameHashCode, j);
        }
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public Object extract(JSONReader jSONReader) {
        Object obj = null;
        if (jSONReader.jsonb) {
            if (jSONReader.nextIfObjectStart()) {
                while (!jSONReader.nextIfObjectEnd()) {
                    long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
                    if (readFieldNameHashCode != 0) {
                        if (readFieldNameHashCode != this.nameHashCode && !jSONReader.isObject() && !jSONReader.isArray()) {
                            jSONReader.skipValue();
                        } else {
                            return jSONReader.readAny();
                        }
                    }
                }
            }
            if ((this.features & JSONPath.Feature.AlwaysReturnList.mask) != 0) {
                return new JSONArray();
            }
            return null;
        }
        if (jSONReader.nextIfObjectStart()) {
            while (!jSONReader.nextIfObjectEnd()) {
                if (jSONReader.readFieldNameHashCode() != this.nameHashCode) {
                    jSONReader.skipValue();
                } else {
                    char c = jSONReader.ch;
                    if (c != '\"' && c != '\'') {
                        if (c != '+' && c != '-') {
                            if (c == '[') {
                                obj = jSONReader.readArray();
                            } else {
                                if (c != 'f') {
                                    if (c == 'n') {
                                        jSONReader.readNull();
                                    } else if (c != 't') {
                                        if (c == '{') {
                                            obj = jSONReader.readObject();
                                        } else {
                                            switch (c) {
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
                                                    throw new JSONException("TODO : " + jSONReader.ch);
                                            }
                                        }
                                    }
                                }
                                obj = Boolean.valueOf(jSONReader.readBoolValue());
                            }
                        }
                        obj = jSONReader.readNumber();
                    } else {
                        obj = jSONReader.readString();
                    }
                    if ((this.features & JSONPath.Feature.AlwaysReturnList.mask) == 0) {
                        return obj;
                    }
                    if (obj == null) {
                        return new JSONArray();
                    }
                    return JSONArray.of(obj);
                }
            }
        }
        if ((this.features & JSONPath.Feature.AlwaysReturnList.mask) != 0) {
            return new JSONArray();
        }
        return null;
    }

    @Override // com.alibaba.fastjson2.JSONPathSingle, com.alibaba.fastjson2.JSONPath
    public String extractScalar(JSONReader jSONReader) {
        Object obj = null;
        if (jSONReader.nextIfObjectStart()) {
            while (jSONReader.ch != '}') {
                boolean z = jSONReader.readFieldNameHashCode() == this.nameHashCode;
                char c = jSONReader.ch;
                if (!z && c != '{' && c != '[') {
                    jSONReader.skipValue();
                } else {
                    char c2 = jSONReader.ch;
                    if (c2 != '\"' && c2 != '\'') {
                        if (c2 != '+' && c2 != '-') {
                            if (c2 == '[') {
                                obj = jSONReader.readArray();
                            } else {
                                if (c2 != 'f') {
                                    if (c2 == 'n') {
                                        jSONReader.readNull();
                                    } else if (c2 != 't') {
                                        if (c2 == '{') {
                                            obj = jSONReader.readObject();
                                        } else {
                                            switch (c2) {
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
                                                    throw new JSONException("TODO : " + jSONReader.ch);
                                            }
                                        }
                                    }
                                }
                                obj = Boolean.valueOf(jSONReader.readBoolValue());
                            }
                        }
                        obj = jSONReader.readNumber();
                    } else {
                        obj = jSONReader.readString();
                    }
                    return JSON.toJSONString(obj);
                }
            }
            jSONReader.next();
        }
        return null;
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public long extractInt64Value(JSONReader jSONReader) {
        if (jSONReader.nextIfObjectStart()) {
            while (jSONReader.ch != '}') {
                if (jSONReader.readFieldNameHashCode() != this.nameHashCode) {
                    jSONReader.skipValue();
                } else {
                    char c = jSONReader.ch;
                    if (c != '\"' && c != '\'') {
                        if (c != '+' && c != '-') {
                            if (c != '[') {
                                if (c != ']') {
                                    if (c != 'f') {
                                        if (c == 'n') {
                                            jSONReader.readNull();
                                            jSONReader.wasNull = true;
                                            return 0L;
                                        }
                                        if (c != 't') {
                                            if (c != '{') {
                                                switch (c) {
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
                                                        throw new JSONException("TODO : " + jSONReader.ch);
                                                }
                                            }
                                        }
                                    }
                                    return jSONReader.readBoolValue() ? 1L : 0L;
                                }
                                jSONReader.next();
                            }
                            return jSONReader.toLong(jSONReader.readObject());
                        }
                        return jSONReader.readInt64Value();
                    }
                    return Long.parseLong(jSONReader.readString());
                }
            }
            jSONReader.wasNull = true;
            return 0L;
        }
        jSONReader.wasNull = true;
        return 0L;
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public int extractInt32Value(JSONReader jSONReader) {
        if (jSONReader.nextIfObjectStart()) {
            while (jSONReader.ch != '}') {
                if (jSONReader.readFieldNameHashCode() != this.nameHashCode) {
                    jSONReader.skipValue();
                } else {
                    char c = jSONReader.ch;
                    if (c != '\"' && c != '\'') {
                        if (c != '+' && c != '-') {
                            if (c != ']') {
                                if (c != 'f') {
                                    if (c == 'n') {
                                        jSONReader.readNull();
                                        jSONReader.wasNull = true;
                                        return 0;
                                    }
                                    if (c != 't') {
                                        switch (c) {
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
                                                throw new JSONException("TODO : " + jSONReader.ch);
                                        }
                                    }
                                }
                                return jSONReader.readBoolValue() ? 1 : 0;
                            }
                            jSONReader.next();
                        }
                        return jSONReader.readInt32Value();
                    }
                    return Integer.parseInt(jSONReader.readString());
                }
            }
            jSONReader.wasNull = true;
            return 0;
        }
        jSONReader.wasNull = true;
        return 0;
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public void extractScalar(JSONReader jSONReader, ValueConsumer valueConsumer) {
        if (jSONReader.nextIfObjectStart()) {
            while (jSONReader.ch != '}') {
                if (jSONReader.readFieldNameHashCode() != this.nameHashCode) {
                    jSONReader.skipValue();
                } else {
                    char c = jSONReader.ch;
                    if (c != '\"' && c != '\'') {
                        if (c != '+' && c != '-') {
                            if (c == '[') {
                                valueConsumer.accept(jSONReader.readArray());
                                return;
                            }
                            if (c != ']') {
                                if (c != 'f') {
                                    if (c == 'n') {
                                        jSONReader.readNull();
                                        valueConsumer.acceptNull();
                                        return;
                                    } else if (c != 't') {
                                        if (c == '{') {
                                            valueConsumer.accept(jSONReader.readObject());
                                            return;
                                        } else {
                                            switch (c) {
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
                                                    throw new JSONException("TODO : " + jSONReader.ch);
                                            }
                                        }
                                    }
                                }
                                valueConsumer.accept(jSONReader.readBoolValue());
                                return;
                            }
                            jSONReader.next();
                        }
                        jSONReader.readNumber(valueConsumer, false);
                        return;
                    }
                    jSONReader.readString(valueConsumer, false);
                    return;
                }
            }
            valueConsumer.acceptNull();
            return;
        }
        valueConsumer.acceptNull();
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public void extract(JSONReader jSONReader, ValueConsumer valueConsumer) {
        if (jSONReader.nextIfObjectStart()) {
            while (jSONReader.ch != '}') {
                if (jSONReader.readFieldNameHashCode() != this.nameHashCode) {
                    jSONReader.skipValue();
                } else {
                    char c = jSONReader.ch;
                    if (c != '\"' && c != '\'') {
                        if (c != '+' && c != '-') {
                            if (c == '[') {
                                valueConsumer.accept(jSONReader.readArray());
                                return;
                            }
                            if (c != 'f') {
                                if (c == 'n') {
                                    jSONReader.readNull();
                                    valueConsumer.acceptNull();
                                    return;
                                } else if (c != 't') {
                                    if (c == '{') {
                                        valueConsumer.accept(jSONReader.readObject());
                                        return;
                                    } else {
                                        switch (c) {
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
                                                throw new JSONException("TODO : " + jSONReader.ch);
                                        }
                                    }
                                }
                            }
                            valueConsumer.accept(jSONReader.readBoolValue());
                            return;
                        }
                        jSONReader.readNumber(valueConsumer, true);
                        return;
                    }
                    jSONReader.readString(valueConsumer, true);
                    return;
                }
            }
            valueConsumer.acceptNull();
            return;
        }
        valueConsumer.acceptNull();
    }
}
