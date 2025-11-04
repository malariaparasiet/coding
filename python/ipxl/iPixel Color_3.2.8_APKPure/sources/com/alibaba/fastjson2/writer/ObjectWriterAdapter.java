package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.SymbolTable;
import com.alibaba.fastjson2.codec.FieldInfo;
import com.alibaba.fastjson2.filter.AfterFilter;
import com.alibaba.fastjson2.filter.BeanContext;
import com.alibaba.fastjson2.filter.BeforeFilter;
import com.alibaba.fastjson2.filter.ContextNameFilter;
import com.alibaba.fastjson2.filter.ContextValueFilter;
import com.alibaba.fastjson2.filter.LabelFilter;
import com.alibaba.fastjson2.filter.NameFilter;
import com.alibaba.fastjson2.filter.PropertyFilter;
import com.alibaba.fastjson2.filter.PropertyPreFilter;
import com.alibaba.fastjson2.filter.ValueFilter;
import com.alibaba.fastjson2.util.BeanUtils;
import com.alibaba.fastjson2.util.DateUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.TypeUtils;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class ObjectWriterAdapter<T> implements ObjectWriter<T> {
    static final String TYPE = "@type";
    final boolean containsNoneFieldGetter;
    final long features;
    protected final FieldWriter[] fieldWriterArray;
    final List<FieldWriter> fieldWriters;
    final boolean googleCollection;
    boolean hasFilter;
    final boolean hasValueField;
    final long[] hashCodes;
    byte[] jsonbClassInfo;
    final short[] mapping;
    NameFilter nameFilter;
    char[] nameWithColonUTF16;
    byte[] nameWithColonUTF8;
    final Class objectClass;
    PropertyFilter propertyFilter;
    PropertyPreFilter propertyPreFilter;
    final boolean serializable;
    final String typeKey;
    byte[] typeKeyJSONB;
    protected final String typeName;
    protected final long typeNameHash;
    protected final byte[] typeNameJSONB;
    protected long typeNameSymbolCache;
    ValueFilter valueFilter;

    public ObjectWriterAdapter(Class<T> cls, List<FieldWriter> list) {
        this(cls, null, null, 0L, list);
    }

    public ObjectWriterAdapter(Class<T> cls, String str, String str2, long j, List<FieldWriter> list) {
        if (str2 == null && cls != null) {
            if (Enum.class.isAssignableFrom(cls) && !cls.isEnum()) {
                str2 = cls.getSuperclass().getName();
            } else {
                str2 = TypeUtils.getTypeName(cls);
            }
        }
        this.objectClass = cls;
        this.typeKey = (str == null || str.isEmpty()) ? TYPE : str;
        this.typeName = str2;
        this.typeNameHash = str2 != null ? Fnv.hashCode64(str2) : 0L;
        this.typeNameJSONB = JSONB.toBytes(str2);
        this.features = j;
        this.fieldWriters = list;
        this.serializable = cls == null || Serializable.class.isAssignableFrom(cls);
        this.googleCollection = "com.google.common.collect.AbstractMapBasedMultimap$RandomAccessWrappedList".equals(str2) || "com.google.common.collect.AbstractMapBasedMultimap$WrappedSet".equals(str2);
        FieldWriter[] fieldWriterArr = new FieldWriter[list.size()];
        this.fieldWriterArray = fieldWriterArr;
        list.toArray(fieldWriterArr);
        this.hasValueField = fieldWriterArr.length == 1 && (fieldWriterArr[0].features & FieldInfo.VALUE_MASK) != 0;
        int length = fieldWriterArr.length;
        long[] jArr = new long[length];
        int i = 0;
        boolean z = false;
        while (true) {
            FieldWriter[] fieldWriterArr2 = this.fieldWriterArray;
            if (i >= fieldWriterArr2.length) {
                break;
            }
            FieldWriter fieldWriter = fieldWriterArr2[i];
            jArr[i] = Fnv.hashCode64(fieldWriter.fieldName);
            if (fieldWriter.method != null && (fieldWriter.features & FieldInfo.FIELD_MASK) == 0) {
                z = true;
            }
            i++;
        }
        this.containsNoneFieldGetter = z;
        long[] copyOf = Arrays.copyOf(jArr, length);
        this.hashCodes = copyOf;
        Arrays.sort(copyOf);
        this.mapping = new short[copyOf.length];
        for (int i2 = 0; i2 < length; i2++) {
            this.mapping[Arrays.binarySearch(this.hashCodes, jArr[i2])] = (short) i2;
        }
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter, com.alibaba.fastjson2.reader.ObjectReader
    public long getFeatures() {
        return this.features;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public FieldWriter getFieldWriter(long j) {
        int binarySearch = Arrays.binarySearch(this.hashCodes, j);
        if (binarySearch < 0) {
            return null;
        }
        return this.fieldWriterArray[this.mapping[binarySearch]];
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public final boolean hasFilter(JSONWriter jSONWriter) {
        return jSONWriter.hasFilter(this.containsNoneFieldGetter) | this.hasFilter;
    }

    protected final boolean hasFilter0(JSONWriter jSONWriter) {
        return jSONWriter.hasFilter() | this.hasFilter;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void setPropertyFilter(PropertyFilter propertyFilter) {
        this.propertyFilter = propertyFilter;
        if (propertyFilter != null) {
            this.hasFilter = true;
        }
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void setValueFilter(ValueFilter valueFilter) {
        this.valueFilter = valueFilter;
        if (valueFilter != null) {
            this.hasFilter = true;
        }
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void setNameFilter(NameFilter nameFilter) {
        this.nameFilter = nameFilter;
        if (nameFilter != null) {
            this.hasFilter = true;
        }
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void setPropertyPreFilter(PropertyPreFilter propertyPreFilter) {
        this.propertyPreFilter = propertyPreFilter;
        if (propertyPreFilter != null) {
            this.hasFilter = true;
        }
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeArrayMappingJSONB(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        if (jSONWriter.isWriteTypeInfo(obj, type, j)) {
            writeClassInfo(jSONWriter);
        }
        int size = this.fieldWriters.size();
        jSONWriter.startArray(size);
        for (int i = 0; i < size; i++) {
            this.fieldWriters.get(i).writeValue(jSONWriter, obj);
        }
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeJSONB(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        long features = this.features | j | jSONWriter.getFeatures();
        if (!this.serializable) {
            if ((JSONWriter.Feature.ErrorOnNoneSerializable.mask & features) != 0) {
                errorOnNoneSerializable();
                return;
            } else if ((JSONWriter.Feature.IgnoreNoneSerializable.mask & features) != 0) {
                jSONWriter.writeNull();
                return;
            }
        }
        if ((features & JSONWriter.Feature.IgnoreNoneSerializable.mask) != 0) {
            writeWithFilter(jSONWriter, obj, obj2, type, j);
            return;
        }
        int length = this.fieldWriterArray.length;
        if (jSONWriter.isWriteTypeInfo(obj, type, j)) {
            writeClassInfo(jSONWriter);
        }
        jSONWriter.startObject();
        for (int i = 0; i < length; i++) {
            this.fieldWriters.get(i).write(jSONWriter, obj);
        }
        jSONWriter.endObject();
    }

    protected final void writeClassInfo(JSONWriter jSONWriter) {
        SymbolTable symbolTable = jSONWriter.symbolTable;
        if (symbolTable == null || !writeClassInfoSymbol(jSONWriter, symbolTable)) {
            jSONWriter.writeTypeName(this.typeNameJSONB, this.typeNameHash);
        }
    }

    private boolean writeClassInfoSymbol(JSONWriter jSONWriter, SymbolTable symbolTable) {
        int ordinalByHashCode;
        int identityHashCode = System.identityHashCode(symbolTable);
        long j = this.typeNameSymbolCache;
        if (j == 0) {
            ordinalByHashCode = symbolTable.getOrdinalByHashCode(this.typeNameHash);
            if (ordinalByHashCode != -1) {
                this.typeNameSymbolCache = (ordinalByHashCode << 32) | identityHashCode;
            }
        } else if (((int) j) == identityHashCode) {
            ordinalByHashCode = (int) (j >> 32);
        } else {
            ordinalByHashCode = symbolTable.getOrdinalByHashCode(this.typeNameHash);
            if (ordinalByHashCode != -1) {
                this.typeNameSymbolCache = (ordinalByHashCode << 32) | identityHashCode;
            }
        }
        if (ordinalByHashCode == -1) {
            return false;
        }
        jSONWriter.writeRaw(JSONB.Constants.BC_TYPED_ANY);
        jSONWriter.writeInt32(-ordinalByHashCode);
        return true;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        if (this.hasValueField) {
            this.fieldWriterArray[0].writeValue(jSONWriter, obj);
            return;
        }
        long features = j | this.features | jSONWriter.getFeatures();
        boolean z = (JSONWriter.Feature.BeanToArray.mask & features) != 0;
        if (jSONWriter.jsonb) {
            if (z) {
                writeArrayMappingJSONB(jSONWriter, obj, obj2, type, j);
                return;
            } else {
                writeJSONB(jSONWriter, obj, obj2, type, j);
                return;
            }
        }
        if (this.googleCollection) {
            ObjectWriterImplCollection.INSTANCE.write(jSONWriter, (Collection) obj, obj2, type, j);
            return;
        }
        if (z) {
            writeArrayMapping(jSONWriter, obj, obj2, type, j);
            return;
        }
        if (!this.serializable) {
            if ((JSONWriter.Feature.ErrorOnNoneSerializable.mask & features) != 0) {
                errorOnNoneSerializable();
                return;
            } else if ((features & JSONWriter.Feature.IgnoreNoneSerializable.mask) != 0) {
                jSONWriter.writeNull();
                return;
            }
        }
        if (hasFilter(jSONWriter)) {
            writeWithFilter(jSONWriter, obj, obj2, type, j);
            return;
        }
        jSONWriter.startObject();
        if (((this.features | j) & JSONWriter.Feature.WriteClassName.mask) != 0 || jSONWriter.isWriteTypeInfo(obj, j)) {
            writeTypeInfo(jSONWriter);
        }
        int size = this.fieldWriters.size();
        for (int i = 0; i < size; i++) {
            this.fieldWriters.get(i).write(jSONWriter, obj);
        }
        jSONWriter.endObject();
    }

    public Map<String, Object> toMap(Object obj) {
        int size = this.fieldWriters.size();
        JSONObject jSONObject = new JSONObject(size, 1.0f);
        for (int i = 0; i < size; i++) {
            FieldWriter fieldWriter = this.fieldWriters.get(i);
            jSONObject.put(fieldWriter.fieldName, fieldWriter.getFieldValue(obj));
        }
        return jSONObject;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public List<FieldWriter> getFieldWriters() {
        return this.fieldWriters;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public boolean writeTypeInfo(JSONWriter jSONWriter) {
        if (jSONWriter.utf8) {
            if (this.nameWithColonUTF8 == null) {
                int length = this.typeKey.length();
                int length2 = this.typeName.length();
                int i = length + length2;
                byte[] bArr = new byte[i + 5];
                bArr[0] = 34;
                this.typeKey.getBytes(0, length, bArr, 1);
                bArr[length + 1] = 34;
                bArr[length + 2] = 58;
                bArr[length + 3] = 34;
                this.typeName.getBytes(0, length2, bArr, length + 4);
                bArr[i + 4] = 34;
                this.nameWithColonUTF8 = bArr;
            }
            jSONWriter.writeNameRaw(this.nameWithColonUTF8);
            return true;
        }
        if (jSONWriter.utf16) {
            if (this.nameWithColonUTF16 == null) {
                int length3 = this.typeKey.length();
                int length4 = this.typeName.length();
                int i2 = length3 + length4;
                char[] cArr = new char[i2 + 5];
                cArr[0] = Typography.quote;
                this.typeKey.getChars(0, length3, cArr, 1);
                cArr[length3 + 1] = Typography.quote;
                cArr[length3 + 2] = ':';
                cArr[length3 + 3] = Typography.quote;
                this.typeName.getChars(0, length4, cArr, length3 + 4);
                cArr[i2 + 4] = Typography.quote;
                this.nameWithColonUTF16 = cArr;
            }
            jSONWriter.writeNameRaw(this.nameWithColonUTF16);
            return true;
        }
        if (jSONWriter.jsonb) {
            if (this.typeKeyJSONB == null) {
                this.typeKeyJSONB = JSONB.toBytes(this.typeKey);
            }
            jSONWriter.writeRaw(this.typeKeyJSONB);
            jSONWriter.writeRaw(this.typeNameJSONB);
            return true;
        }
        jSONWriter.writeString(this.typeKey);
        jSONWriter.writeColon();
        jSONWriter.writeString(this.typeName);
        return true;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeWithFilter(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        NameFilter nameFilter;
        ContextNameFilter contextNameFilter;
        LabelFilter labelFilter;
        int i;
        ValueFilter valueFilter;
        long j2;
        Object fieldValue;
        BeanContext beanContext;
        Field field;
        boolean z;
        BeanContext beanContext2;
        String str;
        ObjectWriterAdapter<T> objectWriterAdapter = this;
        if (obj == null) {
            jSONWriter.writeNull();
            return;
        }
        if (jSONWriter.isWriteTypeInfo(obj, type, j)) {
            if (jSONWriter.jsonb) {
                writeClassInfo(jSONWriter);
                jSONWriter.startObject();
            } else {
                jSONWriter.startObject();
                writeTypeInfo(jSONWriter);
            }
        } else {
            jSONWriter.startObject();
        }
        JSONWriter.Context context = jSONWriter.context;
        long features = context.getFeatures() | j;
        boolean z2 = (JSONWriter.Feature.ReferenceDetection.mask & features) != 0;
        boolean z3 = (features & JSONWriter.Feature.IgnoreNonFieldGetter.mask) != 0;
        BeforeFilter beforeFilter = context.getBeforeFilter();
        if (beforeFilter != null) {
            beforeFilter.writeBefore(jSONWriter, obj);
        }
        PropertyPreFilter propertyPreFilter = context.getPropertyPreFilter();
        if (propertyPreFilter == null) {
            propertyPreFilter = objectWriterAdapter.propertyPreFilter;
        }
        PropertyPreFilter propertyPreFilter2 = propertyPreFilter;
        NameFilter nameFilter2 = context.getNameFilter();
        if (nameFilter2 == null) {
            nameFilter2 = objectWriterAdapter.nameFilter;
        } else {
            NameFilter nameFilter3 = objectWriterAdapter.nameFilter;
            if (nameFilter3 != null) {
                nameFilter2 = NameFilter.compose(nameFilter3, nameFilter2);
            }
        }
        NameFilter nameFilter4 = nameFilter2;
        ContextNameFilter contextNameFilter2 = context.getContextNameFilter();
        ValueFilter valueFilter2 = context.getValueFilter();
        if (valueFilter2 == null) {
            valueFilter2 = objectWriterAdapter.valueFilter;
        } else {
            ValueFilter valueFilter3 = objectWriterAdapter.valueFilter;
            if (valueFilter3 != null) {
                valueFilter2 = ValueFilter.compose(valueFilter3, valueFilter2);
            }
        }
        ValueFilter valueFilter4 = valueFilter2;
        ContextValueFilter contextValueFilter = context.getContextValueFilter();
        PropertyFilter propertyFilter = context.getPropertyFilter();
        if (propertyFilter == null) {
            propertyFilter = objectWriterAdapter.propertyFilter;
        }
        PropertyFilter propertyFilter2 = propertyFilter;
        LabelFilter labelFilter2 = context.getLabelFilter();
        boolean z4 = z3;
        int i2 = 0;
        while (i2 < objectWriterAdapter.fieldWriters.size()) {
            FieldWriter fieldWriter = objectWriterAdapter.fieldWriters.get(i2);
            Field field2 = fieldWriter.field;
            if (!z4 || fieldWriter.method == null || (fieldWriter.features & FieldInfo.FIELD_MASK) != 0) {
                String str2 = fieldWriter.fieldName;
                if ((propertyPreFilter2 == null || propertyPreFilter2.process(jSONWriter, obj, str2)) && (labelFilter2 == null || (str = fieldWriter.label) == null || str.isEmpty() || labelFilter2.apply(str))) {
                    if (nameFilter4 == null && propertyFilter2 == null && contextValueFilter == null && contextNameFilter2 == null && valueFilter4 == null) {
                        fieldWriter.write(jSONWriter, obj);
                    } else {
                        try {
                            fieldValue = fieldWriter.getFieldValue(obj);
                        } finally {
                            if ((j2 > r13 ? 1 : (j2 == r13 ? 0 : -1)) != 0) {
                            }
                        }
                        if ((fieldValue != null || jSONWriter.isWriteNulls()) && (z2 || (!"this$0".equals(str2) && !"this$1".equals(str2) && !"this$2".equals(str2)))) {
                            String process = nameFilter4 != null ? nameFilter4.process(obj, str2, fieldValue) : str2;
                            if (contextNameFilter2 != null) {
                                nameFilter = nameFilter4;
                                if (field2 != null || fieldWriter.method == null) {
                                    labelFilter = labelFilter2;
                                    field = field2;
                                } else {
                                    labelFilter = labelFilter2;
                                    field = BeanUtils.getDeclaredField(objectWriterAdapter.objectClass, fieldWriter.fieldName);
                                }
                                i = i2;
                                beanContext = new BeanContext(objectWriterAdapter.objectClass, fieldWriter.method, field, fieldWriter.fieldName, fieldWriter.label, fieldWriter.fieldClass, fieldWriter.fieldType, fieldWriter.features, fieldWriter.format);
                                process = contextNameFilter2.process(beanContext, obj, process, fieldValue);
                            } else {
                                nameFilter = nameFilter4;
                                labelFilter = labelFilter2;
                                i = i2;
                                beanContext = null;
                                field = field2;
                            }
                            if (propertyFilter2 == null || propertyFilter2.apply(obj, str2, fieldValue)) {
                                boolean z5 = (process == null || process == str2) ? false : true;
                                Object apply = valueFilter4 != null ? valueFilter4.apply(obj, str2, fieldValue) : fieldValue;
                                if (contextValueFilter != null) {
                                    if (beanContext == null) {
                                        if (field == null && fieldWriter.method != null) {
                                            field = BeanUtils.getDeclaredField(objectWriterAdapter.objectClass, fieldWriter.fieldName);
                                        }
                                        z = z5;
                                        beanContext2 = new BeanContext(objectWriterAdapter.objectClass, fieldWriter.method, field, fieldWriter.fieldName, fieldWriter.label, fieldWriter.fieldClass, fieldWriter.fieldType, fieldWriter.features, fieldWriter.format);
                                    } else {
                                        z = z5;
                                        beanContext2 = beanContext;
                                    }
                                    apply = contextValueFilter.process(beanContext2, obj, process, apply);
                                } else {
                                    z = z5;
                                }
                                if (apply != fieldValue) {
                                    if (z) {
                                        jSONWriter.writeName(process);
                                        jSONWriter.writeColon();
                                    } else {
                                        fieldWriter.writeFieldName(jSONWriter);
                                    }
                                    if (apply == null) {
                                        jSONWriter.writeNull();
                                    } else {
                                        contextNameFilter = contextNameFilter2;
                                        valueFilter = valueFilter4;
                                        fieldWriter.getObjectWriter(jSONWriter, apply.getClass()).write(jSONWriter, apply, obj2, type, j);
                                    }
                                } else {
                                    contextNameFilter = contextNameFilter2;
                                    valueFilter = valueFilter4;
                                    if (!z) {
                                        fieldWriter.write(jSONWriter, obj);
                                    } else {
                                        jSONWriter.writeName(process);
                                        jSONWriter.writeColon();
                                        if (fieldValue == null) {
                                            fieldWriter.getObjectWriter(jSONWriter, fieldWriter.fieldClass).write(jSONWriter, null, obj2, type, j);
                                        } else {
                                            fieldWriter.getObjectWriter(jSONWriter, fieldValue.getClass()).write(jSONWriter, fieldValue, obj2, type, j);
                                        }
                                    }
                                }
                                i2 = i + 1;
                                contextNameFilter2 = contextNameFilter;
                                valueFilter4 = valueFilter;
                                nameFilter4 = nameFilter;
                                labelFilter2 = labelFilter;
                                objectWriterAdapter = this;
                            }
                            contextNameFilter = contextNameFilter2;
                            valueFilter = valueFilter4;
                            i2 = i + 1;
                            contextNameFilter2 = contextNameFilter;
                            valueFilter4 = valueFilter;
                            nameFilter4 = nameFilter;
                            labelFilter2 = labelFilter;
                            objectWriterAdapter = this;
                        }
                    }
                }
            }
            nameFilter = nameFilter4;
            contextNameFilter = contextNameFilter2;
            labelFilter = labelFilter2;
            i = i2;
            valueFilter = valueFilter4;
            i2 = i + 1;
            contextNameFilter2 = contextNameFilter;
            valueFilter4 = valueFilter;
            nameFilter4 = nameFilter;
            labelFilter2 = labelFilter;
            objectWriterAdapter = this;
        }
        AfterFilter afterFilter = context.getAfterFilter();
        if (afterFilter != null) {
            afterFilter.writeAfter(jSONWriter, obj);
        }
        jSONWriter.endObject();
    }

    public JSONObject toJSONObject(T t) {
        return toJSONObject(t, 0L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JSONObject toJSONObject(T t, long j) {
        JSONObject jSONObject = new JSONObject();
        int size = this.fieldWriters.size();
        for (int i = 0; i < size; i++) {
            FieldWriter fieldWriter = this.fieldWriters.get(i);
            Object fieldValue = fieldWriter.getFieldValue(t);
            String str = fieldWriter.format;
            Class cls = fieldWriter.fieldClass;
            if (str != null) {
                if (cls == Date.class) {
                    fieldValue = "millis".equals(str) ? Long.valueOf(((Date) fieldValue).getTime()) : DateUtils.format((Date) fieldValue, str);
                } else if (cls == LocalDate.class) {
                    fieldValue = DateUtils.format((LocalDate) fieldValue, str);
                } else if (cls == LocalDateTime.class) {
                    fieldValue = DateUtils.format((LocalDateTime) fieldValue, str);
                }
            }
            if ((fieldWriter.features & FieldInfo.UNWRAPPED_MASK) == 0) {
                if (fieldValue != null) {
                    String name = fieldValue.getClass().getName();
                    if (Collection.class.isAssignableFrom(cls) && fieldValue.getClass() != JSONObject.class && !name.equals("com.alibaba.fastjson.JSONObject")) {
                        Collection collection = (Collection) fieldValue;
                        JSONArray jSONArray = new JSONArray(collection.size());
                        Iterator it = collection.iterator();
                        while (it.hasNext()) {
                            Object next = it.next();
                            jSONArray.add(next == t ? jSONObject : JSON.toJSON(next));
                        }
                        fieldValue = jSONArray;
                    }
                }
                if (fieldValue != null || ((this.features | j) & JSONWriter.Feature.WriteNulls.mask) != 0) {
                    if (fieldValue == t) {
                        fieldValue = jSONObject;
                    }
                    if ((fieldValue instanceof Enum) && (JSONWriter.Feature.WriteEnumsUsingName.mask & j) != 0) {
                        fieldValue = ((Enum) fieldValue).name();
                    }
                    if ((fieldWriter instanceof FieldWriterObject) && !(fieldValue instanceof Map)) {
                        ObjectWriter initWriter = fieldWriter.getInitWriter();
                        if (initWriter == null) {
                            initWriter = JSONFactory.getObjectWriter(fieldWriter.fieldType, this.features | j);
                        }
                        if (initWriter instanceof ObjectWriterAdapter) {
                            ObjectWriterAdapter objectWriterAdapter = (ObjectWriterAdapter) initWriter;
                            if (!objectWriterAdapter.getFieldWriters().isEmpty()) {
                                fieldValue = objectWriterAdapter.toJSONObject(fieldValue);
                            } else {
                                fieldValue = JSON.toJSON(fieldValue);
                            }
                        }
                    }
                    jSONObject.put(fieldWriter.fieldName, fieldValue);
                }
            } else if (fieldValue instanceof Map) {
                jSONObject.putAll((Map) fieldValue);
            } else {
                ObjectWriter initWriter2 = fieldWriter.getInitWriter();
                if (initWriter2 == null) {
                    initWriter2 = JSONFactory.getDefaultObjectWriterProvider().getObjectWriter(cls);
                }
                List<FieldWriter> fieldWriters = initWriter2.getFieldWriters();
                int size2 = fieldWriters.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    FieldWriter fieldWriter2 = fieldWriters.get(i2);
                    jSONObject.put(fieldWriter2.fieldName, fieldWriter2.getFieldValue(fieldValue));
                }
            }
        }
        return jSONObject;
    }

    public String toString() {
        return this.objectClass.getName();
    }

    protected void errorOnNoneSerializable() {
        throw new JSONException("not support none serializable class " + this.objectClass.getName());
    }
}
