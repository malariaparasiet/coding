package com.alibaba.fastjson2;

import androidx.autofill.HintConstants;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONPathSegment;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.FieldReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.reader.ObjectReaderProvider;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.writer.FieldWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.alibaba.fastjson2.writer.ObjectWriterAdapter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/* loaded from: classes2.dex */
class JSONPathSegmentName extends JSONPathSegment {
    static final long HASH_NAME = Fnv.hashCode64(HintConstants.AUTOFILL_HINT_NAME);
    static final long HASH_ORDINAL = Fnv.hashCode64("ordinal");
    final String name;
    final long nameHashCode;

    public JSONPathSegmentName(String str, long j) {
        this.name = str;
        this.nameHashCode = j;
    }

    @Override // com.alibaba.fastjson2.JSONPathSegment
    public boolean remove(JSONPath.Context context) {
        Object obj;
        if (context.parent == null) {
            obj = context.root;
        } else {
            obj = context.parent.value;
        }
        if (obj instanceof Map) {
            ((Map) obj).remove(this.name);
            context.eval = true;
            return true;
        }
        if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                if (obj2 != null) {
                    if (obj2 instanceof Map) {
                        ((Map) obj2).remove(this.name);
                    } else {
                        FieldReader fieldReader = context.path.getReaderContext().getProvider().getObjectReader(obj2.getClass()).getFieldReader(this.nameHashCode);
                        if (fieldReader != null) {
                            fieldReader.accept((FieldReader) obj2, (Object) null);
                        }
                    }
                }
            }
            context.eval = true;
            return true;
        }
        FieldReader fieldReader2 = context.path.getReaderContext().getProvider().getObjectReader(obj.getClass()).getFieldReader(this.nameHashCode);
        if (fieldReader2 != null) {
            fieldReader2.accept((FieldReader) obj, (Object) null);
        }
        context.eval = true;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONPathSegment
    public boolean contains(JSONPath.Context context) {
        Object obj;
        FieldWriter fieldWriter;
        FieldWriter fieldWriter2;
        FieldWriter fieldWriter3;
        FieldWriter fieldWriter4;
        if (context.parent == null) {
            obj = context.root;
        } else {
            obj = context.parent.value;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Map) {
            return ((Map) obj).containsKey(this.name);
        }
        if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                if (obj2 != null) {
                    if ((obj2 instanceof Map) && ((Map) obj2).get(this.name) != null) {
                        return true;
                    }
                    ObjectWriter objectWriter = context.path.getWriterContext().getObjectWriter(obj2.getClass());
                    if ((objectWriter instanceof ObjectWriterAdapter) && (fieldWriter4 = objectWriter.getFieldWriter(this.nameHashCode)) != null && fieldWriter4.getFieldValue(obj2) != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (obj instanceof JSONPath.Sequence) {
            for (Object obj3 : ((JSONPath.Sequence) obj).values) {
                if (obj3 != null) {
                    if ((obj3 instanceof Map) && ((Map) obj3).get(this.name) != null) {
                        return true;
                    }
                    ObjectWriter objectWriter2 = context.path.getWriterContext().getObjectWriter(obj3.getClass());
                    if ((objectWriter2 instanceof ObjectWriterAdapter) && (fieldWriter3 = objectWriter2.getFieldWriter(this.nameHashCode)) != null && fieldWriter3.getFieldValue(obj3) != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (obj instanceof Object[]) {
            for (Object obj4 : (Object[]) obj) {
                if (obj4 != null) {
                    if ((obj4 instanceof Map) && ((Map) obj4).get(this.name) != null) {
                        return true;
                    }
                    ObjectWriter objectWriter3 = context.path.getWriterContext().getObjectWriter(obj4.getClass());
                    if ((objectWriter3 instanceof ObjectWriterAdapter) && (fieldWriter2 = objectWriter3.getFieldWriter(this.nameHashCode)) != null && fieldWriter2.getFieldValue(obj4) != null) {
                        return true;
                    }
                }
            }
        }
        ObjectWriter objectWriter4 = context.path.getWriterContext().getObjectWriter(obj.getClass());
        return (!(objectWriter4 instanceof ObjectWriterAdapter) || (fieldWriter = objectWriter4.getFieldWriter(this.nameHashCode)) == null || fieldWriter.getFieldValue(obj) == null) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.util.Collection] */
    @Override // com.alibaba.fastjson2.JSONPathSegment
    public void eval(JSONPath.Context context) {
        Object obj;
        Object obj2;
        if (context.parent == null) {
            obj = context.root;
        } else {
            obj = context.parent.value;
        }
        if (obj == null) {
            return;
        }
        JSONArray jSONArray = null;
        Long l = null;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj3 = map.get(this.name);
            if (obj3 == null) {
                boolean isNumber = IOUtils.isNumber(this.name);
                Iterator it = map.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    Object key = entry.getKey();
                    if ((key instanceof Enum) && ((Enum) key).name().equals(this.name)) {
                        obj3 = entry.getValue();
                        break;
                    } else if (key instanceof Long) {
                        if (l == null && isNumber) {
                            l = Long.valueOf(Long.parseLong(this.name));
                        }
                        if (key.equals(l)) {
                            obj3 = entry.getValue();
                            break;
                        }
                    }
                }
            }
            context.value = obj3;
            return;
        }
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            int size = collection.size();
            for (Object obj4 : collection) {
                if ((obj4 instanceof Map) && (obj2 = ((Map) obj4).get(this.name)) != null) {
                    if (!(obj2 instanceof Collection)) {
                        if (jSONArray == null) {
                            jSONArray = new JSONArray(size);
                        }
                        jSONArray.add(obj2);
                    } else if (size == 1) {
                        jSONArray = (Collection) obj2;
                    } else {
                        if (jSONArray == null) {
                            jSONArray = new JSONArray(size);
                        }
                        jSONArray.addAll((Collection) obj2);
                    }
                }
            }
            context.value = jSONArray;
            return;
        }
        if (obj instanceof JSONPath.Sequence) {
            List list = ((JSONPath.Sequence) obj).values;
            JSONArray jSONArray2 = new JSONArray(list.size());
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                context.value = it2.next();
                JSONPath.Context context2 = context;
                JSONPath.Context context3 = new JSONPath.Context(context.path, context2, context.current, context.next, context.readerFeatures);
                eval(context3);
                Object obj5 = context3.value;
                if (obj5 != null || (context2.path.features & JSONPath.Feature.KeepNullValue.mask) != 0) {
                    if (obj5 instanceof Collection) {
                        jSONArray2.addAll((Collection) obj5);
                    } else {
                        jSONArray2.add(obj5);
                    }
                }
                context = context2;
            }
            JSONPath.Context context4 = context;
            if (context4.next != null) {
                context4.value = new JSONPath.Sequence(jSONArray2);
            } else {
                context4.value = jSONArray2;
            }
            context4.eval = true;
            return;
        }
        ObjectWriter objectWriter = context.path.getWriterContext().getObjectWriter(obj.getClass());
        if (objectWriter instanceof ObjectWriterAdapter) {
            FieldWriter fieldWriter = objectWriter.getFieldWriter(this.nameHashCode);
            if (fieldWriter != null) {
                context.value = fieldWriter.getFieldValue(obj);
                return;
            }
            return;
        }
        long j = this.nameHashCode;
        if (j == HASH_NAME && (obj instanceof Enum)) {
            context.value = ((Enum) obj).name();
            return;
        }
        if (j == HASH_ORDINAL && (obj instanceof Enum)) {
            context.value = Integer.valueOf(((Enum) obj).ordinal());
            return;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (!str.isEmpty() && str.charAt(0) == '{') {
                context.value = JSONPath.of("$." + this.name).extract(JSONReader.of(str));
                return;
            } else {
                context.value = null;
                return;
            }
        }
        if ((obj instanceof Number) || (obj instanceof Boolean)) {
            context.value = null;
            return;
        }
        throw new JSONException("not support : " + obj.getClass());
    }

    @Override // com.alibaba.fastjson2.JSONPathSegment
    public void set(JSONPath.Context context, Object obj) {
        Object obj2;
        Class<?> cls;
        Class<?> cls2;
        Function typeConvert;
        if (context.parent == null) {
            obj2 = context.root;
        } else {
            obj2 = context.parent.value;
        }
        if (obj2 instanceof JSONPath.Sequence) {
            obj2 = ((JSONPath.Sequence) obj2).values;
        }
        if (obj2 instanceof Map) {
            Map map = (Map) obj2;
            Object put = map.put(this.name, obj);
            if (put == null || (context.readerFeatures & JSONReader.Feature.DuplicateKeyValueAsArray.mask) == 0) {
                return;
            }
            if (put instanceof Collection) {
                ((Collection) put).add(obj);
                map.put(this.name, obj);
                return;
            } else {
                map.put(this.name, JSONArray.of(put, obj));
                return;
            }
        }
        if (obj2 instanceof Collection) {
            for (Object obj3 : (Collection) obj2) {
                if (obj3 != null) {
                    if (obj3 instanceof Map) {
                        Map map2 = (Map) obj3;
                        Object put2 = map2.put(this.name, obj);
                        if (put2 != null && (context.readerFeatures & JSONReader.Feature.DuplicateKeyValueAsArray.mask) != 0) {
                            if (put2 instanceof Collection) {
                                ((Collection) put2).add(obj);
                                map2.put(this.name, obj);
                            } else {
                                map2.put(this.name, JSONArray.of(put2, obj));
                            }
                        }
                    } else {
                        FieldReader fieldReader = context.path.getReaderContext().getProvider().getObjectReader(obj3.getClass()).getFieldReader(this.nameHashCode);
                        if (fieldReader != null) {
                            fieldReader.accept((FieldReader) obj3, obj);
                        }
                    }
                }
            }
            return;
        }
        ObjectReaderProvider provider = context.path.getReaderContext().getProvider();
        FieldReader fieldReader2 = provider.getObjectReader(obj2.getClass()).getFieldReader(this.nameHashCode);
        if (fieldReader2 == null) {
            return;
        }
        if (obj != null && (cls = obj.getClass()) != (cls2 = fieldReader2.fieldClass) && (typeConvert = provider.getTypeConvert(cls, cls2)) != null) {
            obj = typeConvert.apply(obj);
        }
        fieldReader2.accept((FieldReader) obj2, obj);
    }

    @Override // com.alibaba.fastjson2.JSONPathSegment
    public void setCallback(JSONPath.Context context, BiFunction biFunction) {
        Object obj;
        if (context.parent == null) {
            obj = context.root;
        } else {
            obj = context.parent.value;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(this.name);
            if (obj2 != null) {
                map.put(this.name, biFunction.apply(map, obj2));
                return;
            }
            return;
        }
        ObjectReader objectReader = context.path.getReaderContext().getProvider().getObjectReader(obj.getClass());
        ObjectWriter objectWriter = context.path.getWriterContext().provider.getObjectWriter((Class) obj.getClass());
        FieldReader fieldReader = objectReader.getFieldReader(this.nameHashCode);
        FieldWriter fieldWriter = objectWriter.getFieldWriter(this.nameHashCode);
        if (fieldReader == null || fieldWriter == null) {
            return;
        }
        fieldReader.accept((FieldReader) obj, biFunction.apply(obj, fieldWriter.getFieldValue(obj)));
    }

    @Override // com.alibaba.fastjson2.JSONPathSegment
    public void accept(JSONReader jSONReader, JSONPath.Context context) {
        char c;
        char c2;
        Object readString;
        Object readString2;
        if (context.parent != null && (context.parent.eval || (context.parent.current instanceof JSONPathFilter) || (context.parent.current instanceof JSONPathSegment.MultiIndexSegment))) {
            eval(context);
            return;
        }
        if (jSONReader.jsonb) {
            if (jSONReader.nextIfObjectStart()) {
                while (!jSONReader.nextIfObjectEnd()) {
                    long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
                    if (readFieldNameHashCode != 0) {
                        if (readFieldNameHashCode != this.nameHashCode) {
                            jSONReader.skipValue();
                        } else {
                            if ((jSONReader.isArray() || jSONReader.isObject()) && context.next != null) {
                                return;
                            }
                            context.value = jSONReader.readAny();
                            context.eval = true;
                            return;
                        }
                    }
                }
                return;
            }
            if (jSONReader.isArray() && context.parent != null && (context.parent.current instanceof JSONPathSegment.AllSegment)) {
                JSONArray jSONArray = new JSONArray();
                int startArray = jSONReader.startArray();
                for (int i = 0; i < startArray; i++) {
                    if (jSONReader.nextIfMatch(JSONB.Constants.BC_OBJECT)) {
                        while (!jSONReader.nextIfMatch(JSONB.Constants.BC_OBJECT_END)) {
                            if (jSONReader.readFieldNameHashCode() != this.nameHashCode) {
                                jSONReader.skipValue();
                            } else if ((jSONReader.isArray() || jSONReader.isObject()) && context.next != null) {
                                break;
                            } else {
                                jSONArray.add(jSONReader.readAny());
                            }
                        }
                    } else {
                        jSONReader.skipValue();
                    }
                }
                context.value = jSONArray;
                context.eval = true;
                return;
            }
            throw new JSONException("TODO");
        }
        String str = "TODO : ";
        char c3 = '}';
        char c4 = '{';
        if (jSONReader.nextIfObjectStart()) {
            if (jSONReader.ch == '}') {
                jSONReader.next();
                if (jSONReader.isEnd()) {
                    return;
                } else {
                    jSONReader.nextIfComma();
                }
            }
            while (!jSONReader.nextIfObjectEnd()) {
                String str2 = str;
                if (jSONReader.readFieldNameHashCode() != this.nameHashCode) {
                    jSONReader.skipValue();
                    if (jSONReader.ch == ',') {
                        jSONReader.next();
                    }
                    str = str2;
                } else {
                    char c5 = jSONReader.ch;
                    if (c5 != '\"' && c5 != '\'') {
                        if (c5 != '+' && c5 != '-') {
                            if (c5 == '[') {
                                if (context.next != null && !(context.next instanceof JSONPathSegment.EvalSegment) && !(context.next instanceof JSONPathSegmentName) && !(context.next instanceof JSONPathSegment.AllSegment)) {
                                    return;
                                }
                                readString2 = jSONReader.readArray();
                                context.eval = true;
                            } else {
                                if (c5 != 'f') {
                                    if (c5 == 'n') {
                                        jSONReader.readNull();
                                        readString2 = null;
                                    } else if (c5 != 't') {
                                        if (c5 == '{') {
                                            if (context.next != null && !(context.next instanceof JSONPathSegment.EvalSegment) && !(context.next instanceof JSONPathSegment.AllSegment)) {
                                                return;
                                            }
                                            readString2 = jSONReader.readObject();
                                            context.eval = true;
                                        } else {
                                            switch (c5) {
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
                                                    throw new JSONException(str2 + jSONReader.ch);
                                            }
                                        }
                                    }
                                }
                                readString2 = Boolean.valueOf(jSONReader.readBoolValue());
                            }
                        }
                        jSONReader.readNumber0();
                        readString2 = jSONReader.getNumber();
                    } else {
                        readString2 = jSONReader.readString();
                    }
                    context.value = readString2;
                    return;
                }
            }
            jSONReader.next();
            return;
        }
        if (jSONReader.ch == '[' && context.parent != null && (context.parent.current instanceof JSONPathSegment.AllSegment)) {
            jSONReader.next();
            JSONArray jSONArray2 = new JSONArray();
            while (true) {
                if (jSONReader.ch != 26) {
                    if (jSONReader.ch == ']') {
                        jSONReader.next();
                    } else {
                        if (jSONReader.ch == c4) {
                            jSONReader.next();
                            while (jSONReader.ch != c3) {
                                if (jSONReader.readFieldNameHashCode() != this.nameHashCode) {
                                    jSONReader.skipValue();
                                    if (jSONReader.ch == ',') {
                                        jSONReader.next();
                                    }
                                } else {
                                    char c6 = jSONReader.ch;
                                    if (c6 != '\"' && c6 != '\'') {
                                        if (c6 != '+') {
                                            if (c6 == '[') {
                                                c2 = '-';
                                                if (context.next != null) {
                                                    c = ',';
                                                } else {
                                                    readString = jSONReader.readArray();
                                                }
                                            } else {
                                                if (c6 != 'f') {
                                                    if (c6 == 'n') {
                                                        jSONReader.readNull();
                                                        readString = null;
                                                    } else if (c6 != 't') {
                                                        if (c6 == '{') {
                                                            c2 = '-';
                                                            if (context.next != null) {
                                                                c = ',';
                                                            } else {
                                                                readString = jSONReader.readObject();
                                                            }
                                                        } else if (c6 != '-' && c6 != '.') {
                                                            switch (c6) {
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
                                                readString = Boolean.valueOf(jSONReader.readBoolValue());
                                            }
                                        }
                                        jSONReader.readNumber0();
                                        readString = jSONReader.getNumber();
                                    } else {
                                        readString = jSONReader.readString();
                                    }
                                    jSONArray2.add(readString);
                                }
                                c3 = '}';
                            }
                            jSONReader.next();
                            c = ',';
                            c2 = '-';
                        } else {
                            c = ',';
                            c2 = '-';
                            jSONReader.skipValue();
                        }
                        if (jSONReader.ch == c) {
                            jSONReader.next();
                        }
                        c3 = '}';
                        c4 = '{';
                    }
                }
            }
            context.value = jSONArray2;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            JSONPathSegmentName jSONPathSegmentName = (JSONPathSegmentName) obj;
            if (this.nameHashCode == jSONPathSegmentName.nameHashCode && Objects.equals(this.name, jSONPathSegmentName.name)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.name, Long.valueOf(this.nameHashCode));
    }

    public String toString() {
        return this.name;
    }
}
