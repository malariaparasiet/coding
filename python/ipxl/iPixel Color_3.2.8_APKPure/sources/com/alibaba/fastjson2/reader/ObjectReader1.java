package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.JDKUtils;
import java.lang.reflect.Type;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public class ObjectReader1<T> extends ObjectReaderAdapter<T> {
    protected final FieldReader fieldReader0;
    final long hashCode0;
    final long hashCode0LCase;
    protected ObjectReader objectReader0;

    public ObjectReader1(Class cls, long j, JSONSchema jSONSchema, Supplier<T> supplier, Function function, FieldReader fieldReader) {
        this(cls, null, null, j, jSONSchema, supplier, function, fieldReader);
    }

    public ObjectReader1(Class cls, String str, String str2, long j, Supplier<T> supplier, Function function, FieldReader... fieldReaderArr) {
        this(cls, str, str2, j, null, supplier, function, fieldReaderArr);
    }

    public ObjectReader1(Class cls, String str, String str2, long j, JSONSchema jSONSchema, Supplier<T> supplier, Function function, FieldReader... fieldReaderArr) {
        super(cls, str, str2, j, jSONSchema, supplier, function, fieldReaderArr);
        FieldReader fieldReader = fieldReaderArr[0];
        this.fieldReader0 = fieldReader;
        this.hashCode0 = fieldReader.fieldNameHash;
        this.hashCode0LCase = fieldReader.fieldNameHashLCase;
        this.hasDefaultValue = fieldReader.defaultValue != null;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public T readObject(JSONReader jSONReader) {
        return readObject(jSONReader, null, null, this.features);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReader
    public T readArrayMappingJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        if (!this.serializable) {
            jSONReader.errorOnNoneSerializable(this.objectClass);
        }
        ObjectReader checkAutoType = checkAutoType(jSONReader, j);
        if (checkAutoType != null) {
            return (T) checkAutoType.readArrayMappingJSONBObject(jSONReader, type, obj, j);
        }
        T t = this.creator.get();
        int startArray = jSONReader.startArray();
        if (startArray > 0) {
            this.fieldReader0.readFieldValue(jSONReader, t);
            for (int i = 1; i < startArray; i++) {
                jSONReader.skipValue();
            }
        }
        return this.buildFunction != null ? (T) this.buildFunction.apply(t) : t;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReader
    public T readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        T t;
        ObjectReader objectReader;
        if (!this.serializable) {
            jSONReader.errorOnNoneSerializable(this.objectClass);
        }
        ObjectReader checkAutoType = checkAutoType(jSONReader, j);
        if (checkAutoType != null) {
            return (T) checkAutoType.readJSONBObject(jSONReader, type, obj, j);
        }
        if (jSONReader.isArray()) {
            T t2 = this.creator.get();
            int startArray = jSONReader.startArray();
            if (startArray > 0) {
                this.fieldReader0.readFieldValue(jSONReader, t2);
                for (int i = 1; i < startArray; i++) {
                    jSONReader.skipValue();
                }
            }
            return this.buildFunction != null ? (T) this.buildFunction.apply(t2) : t2;
        }
        int i2 = 0;
        if (!jSONReader.nextIfMatch(JSONB.Constants.BC_OBJECT)) {
            if (jSONReader.isTypeRedirect()) {
                jSONReader.setTypeRedirect(false);
            } else {
                throw new JSONException(jSONReader.info("expect object, but " + JSONB.typeName(jSONReader.getType())));
            }
        }
        if (this.creator != null) {
            t = this.creator.get();
        } else if (((jSONReader.getContext().getFeatures() | j) & JSONReader.Feature.FieldBased.mask) != 0) {
            try {
                t = (T) JDKUtils.UNSAFE.allocateInstance(this.objectClass);
            } catch (InstantiationException e) {
                throw new JSONException(jSONReader.info("create instance error"), e);
            }
        } else {
            t = null;
        }
        if (t != null && this.hasDefaultValue) {
            initDefaultValue(t);
        }
        while (!jSONReader.nextIfMatch(JSONB.Constants.BC_OBJECT_END)) {
            long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
            if (readFieldNameHashCode == getTypeKeyHash() && i2 == 0) {
                long readTypeHashCode = jSONReader.readTypeHashCode();
                JSONReader.Context context = jSONReader.getContext();
                ObjectReader autoType = autoType(context, readTypeHashCode);
                if (autoType == null) {
                    String string = jSONReader.getString();
                    objectReader = context.getObjectReaderAutoType(string, null);
                    if (objectReader == null) {
                        throw new JSONException(jSONReader.info("autoType not support : " + string));
                    }
                } else {
                    objectReader = autoType;
                }
                if (objectReader != this) {
                    return (T) objectReader.readJSONBObject(jSONReader, type, obj, j);
                }
            } else if (readFieldNameHashCode != 0) {
                if (readFieldNameHashCode == this.hashCode0) {
                    this.fieldReader0.readFieldValueJSONB(jSONReader, t);
                } else if (jSONReader.isSupportSmartMatch(this.features | j) && jSONReader.getNameHashCodeLCase() == this.hashCode0LCase) {
                    this.fieldReader0.readFieldValue(jSONReader, t);
                } else {
                    processExtra(jSONReader, t);
                }
            }
            i2++;
        }
        if (this.buildFunction != null) {
            t = (T) this.buildFunction.apply(t);
        }
        if (this.schema != null) {
            this.schema.assertValidate(t);
        }
        return t;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReaderBean
    protected void initDefaultValue(T t) {
        this.fieldReader0.acceptDefaultValue(t);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderBean, com.alibaba.fastjson2.reader.ObjectReader
    public T readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        ObjectReader objectReader;
        if (!this.serializable) {
            jSONReader.errorOnNoneSerializable(this.objectClass);
        }
        if (jSONReader.jsonb) {
            return readJSONBObject(jSONReader, type, obj, 0L);
        }
        T t = null;
        if (jSONReader.nextIfNullOrEmptyString()) {
            return null;
        }
        long features = jSONReader.features(this.features | j);
        if (jSONReader.isArray()) {
            if ((JSONReader.Feature.SupportArrayToBean.mask & features) != 0) {
                jSONReader.next();
                T t2 = this.creator.get();
                this.fieldReader0.readFieldValue(jSONReader, t2);
                if (!jSONReader.nextIfArrayEnd()) {
                    throw new JSONException(jSONReader.info("array to bean end error, " + jSONReader.current()));
                }
                jSONReader.nextIfComma();
                return this.buildFunction != null ? (T) this.buildFunction.apply(t2) : t2;
            }
            return processObjectInputSingleItemArray(jSONReader, type, obj, features);
        }
        jSONReader.nextIfObjectStart();
        if (this.creator != null) {
            t = this.creator.get();
        }
        if (this.hasDefaultValue) {
            initDefaultValue(t);
        }
        if (t != null && (features & JSONReader.Feature.InitStringFieldAsEmpty.mask) != 0) {
            initStringFieldAsEmpty(t);
        }
        int i = 0;
        while (true) {
            if (jSONReader.nextIfObjectEnd()) {
                break;
            }
            long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
            if (i == 0 && readFieldNameHashCode == HASH_TYPE) {
                long readTypeHashCode = jSONReader.readTypeHashCode();
                JSONReader.Context context = jSONReader.getContext();
                ObjectReader objectReaderAutoType = context.getObjectReaderAutoType(readTypeHashCode);
                if ((objectReaderAutoType != null || (objectReaderAutoType = context.getObjectReaderAutoType(jSONReader.getString(), this.objectClass)) != null) && (objectReader = objectReaderAutoType) != this) {
                    t = objectReader.readObject(jSONReader, type, obj, j);
                    break;
                }
            } else if (readFieldNameHashCode == this.hashCode0) {
                this.fieldReader0.readFieldValue(jSONReader, t);
            } else if (jSONReader.isSupportSmartMatch(this.features | j) && jSONReader.getNameHashCodeLCase() == this.hashCode0LCase) {
                this.fieldReader0.readFieldValue(jSONReader, t);
            } else {
                processExtra(jSONReader, t);
            }
            i++;
        }
        jSONReader.nextIfComma();
        if (this.buildFunction != null) {
            t = (T) this.buildFunction.apply(t);
        }
        if (this.schema != null) {
            this.schema.assertValidate(t);
        }
        return t;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReader
    public FieldReader getFieldReader(long j) {
        if (j == this.hashCode0) {
            return this.fieldReader0;
        }
        return null;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderAdapter, com.alibaba.fastjson2.reader.ObjectReader
    public FieldReader getFieldReaderLCase(long j) {
        if (j == this.hashCode0LCase) {
            return this.fieldReader0;
        }
        return null;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public boolean setFieldValue(Object obj, String str, long j, int i) {
        if (this.hashCode0 != j) {
            return false;
        }
        this.fieldReader0.accept((FieldReader) obj, i);
        return true;
    }
}
