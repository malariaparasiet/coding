package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public final class ObjectReaderImplEnum implements ObjectReader {
    final Method createMethod;
    final Type createMethodParamType;
    final Class enumClass;
    private final long[] enumNameHashCodes;
    private final Enum[] enums;
    private long[] intValues;
    private final Enum[] ordinalEnums;
    private String[] stringValues;
    final long typeNameHash;
    final Member valueField;
    final Type valueFieldType;

    /* JADX WARN: Multi-variable type inference failed */
    public ObjectReaderImplEnum(Class cls, Method method, Member member, Enum[] enumArr, Enum[] enumArr2, long[] jArr) {
        Class<?> returnType;
        Object invoke;
        this.enumClass = cls;
        this.createMethod = method;
        if (member instanceof AccessibleObject) {
            ((AccessibleObject) member).setAccessible(true);
        }
        this.valueField = member;
        Class<?> cls2 = null;
        if (member instanceof Field) {
            returnType = ((Field) member).getType();
        } else {
            returnType = member instanceof Method ? ((Method) member).getReturnType() : null;
        }
        this.valueFieldType = returnType;
        if (returnType != null) {
            this.stringValues = new String[enumArr.length];
            if (returnType != String.class) {
                this.intValues = new long[enumArr.length];
            }
            for (int i = 0; i < enumArr.length; i++) {
                Enum r5 = enumArr[i];
                try {
                    if (member instanceof Field) {
                        invoke = ((Field) member).get(r5);
                    } else {
                        invoke = ((Method) member).invoke(r5, new Object[0]);
                    }
                    if (returnType == String.class) {
                        this.stringValues[i] = (String) invoke;
                    } else {
                        this.stringValues[i] = invoke == null ? null : invoke.toString();
                        if (invoke instanceof Number) {
                            this.intValues[i] = ((Number) invoke).longValue();
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
        if (method != null && method.getParameterCount() == 1) {
            cls2 = method.getParameterTypes()[0];
        }
        this.createMethodParamType = cls2;
        this.typeNameHash = Fnv.hashCode64(TypeUtils.getTypeName(cls));
        this.enums = enumArr;
        this.ordinalEnums = enumArr2;
        this.enumNameHashCodes = jArr;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Class getObjectClass() {
        return this.enumClass;
    }

    public Enum getEnumByHashCode(long j) {
        int binarySearch;
        if (this.enums != null && (binarySearch = Arrays.binarySearch(this.enumNameHashCodes, j)) >= 0) {
            return this.enums[binarySearch];
        }
        return null;
    }

    public Enum getEnum(String str) {
        if (str == null) {
            return null;
        }
        return getEnumByHashCode(Fnv.hashCode64(str));
    }

    public Enum getEnumByOrdinal(int i) {
        if (i >= 0) {
            Enum[] enumArr = this.ordinalEnums;
            if (i < enumArr.length) {
                return enumArr[i];
            }
        }
        throw new JSONException("No enum ordinal " + this.enumClass.getCanonicalName() + "." + i);
    }

    public Enum of(int i) {
        Member member = this.valueField;
        Enum r1 = null;
        if (member == null) {
            if (i >= 0) {
                Enum[] enumArr = this.ordinalEnums;
                if (i < enumArr.length) {
                    return enumArr[i];
                }
            }
            return null;
        }
        try {
            int i2 = 0;
            if (!(member instanceof Field)) {
                Method method = (Method) member;
                int i3 = 0;
                while (true) {
                    Enum[] enumArr2 = this.enums;
                    if (i3 >= enumArr2.length) {
                        break;
                    }
                    Enum r4 = enumArr2[i3];
                    if (((Number) method.invoke(r4, new Object[0])).intValue() == i) {
                        r1 = r4;
                        break;
                    }
                    i3++;
                }
            } else {
                while (true) {
                    Enum[] enumArr3 = this.enums;
                    if (i2 >= enumArr3.length) {
                        break;
                    }
                    Enum r0 = enumArr3[i2];
                    if (((Field) this.valueField).getInt(r0) == i) {
                        r1 = r0;
                        break;
                    }
                    i2++;
                }
            }
            if (r1 != null) {
                return r1;
            }
            throw new JSONException("None enum ordinal or value " + i);
        } catch (Exception e) {
            throw new JSONException("parse enum error, class " + this.enumClass.getName() + ", value " + i, e);
        }
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Enum enumByHashCode;
        int offset = jSONReader.getOffset();
        int type2 = jSONReader.getType();
        if (type2 == -110) {
            ObjectReader checkAutoType = jSONReader.checkAutoType(this.enumClass, 0L, j);
            if (checkAutoType != null) {
                if (checkAutoType != this) {
                    return checkAutoType.readJSONBObject(jSONReader, type, obj, j);
                }
            } else if (jSONReader.isEnabled(JSONReader.Feature.ErrorOnNotSupportAutoType)) {
                throw new JSONException(jSONReader.info("not support enumType : " + jSONReader.getString()));
            }
        }
        if (type2 >= -16 && type2 <= 72) {
            if (type2 <= 47) {
                jSONReader.next();
            } else {
                type2 = jSONReader.readInt32Value();
            }
            if (type2 >= 0) {
                Enum[] enumArr = this.ordinalEnums;
                if (type2 < enumArr.length) {
                    enumByHashCode = enumArr[type2];
                }
            }
            throw new JSONException("No enum ordinal " + this.enumClass.getCanonicalName() + "." + type2);
        }
        if (jSONReader.nextIfNullOrEmptyString()) {
            return null;
        }
        enumByHashCode = getEnumByHashCode(jSONReader.readValueHashCode());
        if (enumByHashCode == null) {
            enumByHashCode = getEnumByHashCode(jSONReader.getNameHashCodeLCase());
        }
        if (enumByHashCode == null && jSONReader.getOffset() == offset) {
            oomCheck(type);
        }
        return enumByHashCode;
    }

    private void oomCheck(Type type) {
        if ((type instanceof ParameterizedType) && List.class.isAssignableFrom((Class) ((ParameterizedType) type).getRawType())) {
            throw new JSONException(getClass().getSimpleName() + " parses error, JSONReader not forward when field type belongs to collection to avoid OOM");
        }
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        int parseInt;
        int offset = jSONReader.getOffset();
        Type type2 = this.createMethodParamType;
        Enum r12 = null;
        if (type2 != null) {
            Object read = jSONReader.read(type2);
            try {
                return this.createMethod.invoke(null, read);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new JSONException(jSONReader.info("create enum error, enumClass " + this.enumClass.getName() + ", paramValue " + read), e);
            }
        }
        int i = 0;
        if (jSONReader.isInt()) {
            int readInt32Value = jSONReader.readInt32Value();
            if (this.valueField == null) {
                if (readInt32Value >= 0) {
                    Enum[] enumArr = this.ordinalEnums;
                    if (readInt32Value < enumArr.length) {
                        r12 = enumArr[readInt32Value];
                    }
                }
                throw new JSONException("No enum ordinal " + this.enumClass.getCanonicalName() + "." + readInt32Value);
            }
            if (this.intValues != null) {
                while (true) {
                    long[] jArr = this.intValues;
                    if (i >= jArr.length) {
                        break;
                    }
                    if (jArr[i] == readInt32Value) {
                        r12 = this.enums[i];
                        break;
                    }
                    i++;
                }
            }
            if (r12 == null && jSONReader.isEnabled(JSONReader.Feature.ErrorOnEnumNotMatch)) {
                throw new JSONException(jSONReader.info("parse enum error, class " + this.enumClass.getName() + ", " + this.valueField.getName() + " " + readInt32Value));
            }
        } else if (!jSONReader.nextIfNullOrEmptyString()) {
            if (this.stringValues != null && jSONReader.isString()) {
                String readString = jSONReader.readString();
                while (true) {
                    String[] strArr = this.stringValues;
                    if (i >= strArr.length) {
                        break;
                    }
                    if (readString.equals(strArr[i])) {
                        r12 = this.enums[i];
                        break;
                    }
                    i++;
                }
                if (r12 == null && this.valueField != null) {
                    try {
                        r12 = Enum.valueOf(this.enumClass, readString);
                    } catch (IllegalArgumentException unused) {
                    }
                }
            } else if (this.intValues != null && jSONReader.isInt()) {
                int readInt32Value2 = jSONReader.readInt32Value();
                while (true) {
                    long[] jArr2 = this.intValues;
                    if (i >= jArr2.length) {
                        break;
                    }
                    if (jArr2[i] == readInt32Value2) {
                        r12 = this.enums[i];
                        break;
                    }
                    i++;
                }
            } else {
                long readValueHashCode = jSONReader.readValueHashCode();
                if (readValueHashCode == Fnv.MAGIC_HASH_CODE) {
                    return null;
                }
                Enum enumByHashCode = getEnumByHashCode(readValueHashCode);
                if (enumByHashCode == null) {
                    enumByHashCode = getEnumByHashCode(jSONReader.getNameHashCodeLCase());
                }
                if (enumByHashCode == null) {
                    String string = jSONReader.getString();
                    if (TypeUtils.isInteger(string) && (parseInt = Integer.parseInt(string)) >= 0) {
                        Enum[] enumArr2 = this.ordinalEnums;
                        if (parseInt < enumArr2.length) {
                            enumByHashCode = enumArr2[parseInt];
                        }
                    }
                }
                r12 = enumByHashCode;
            }
            if (r12 == null && jSONReader.isEnabled(JSONReader.Feature.ErrorOnEnumNotMatch)) {
                throw new JSONException(jSONReader.info("parse enum error, class " + this.enumClass.getName() + ", value " + jSONReader.getString()));
            }
        }
        if (r12 == null && jSONReader.getOffset() == offset) {
            oomCheck(type);
        }
        return r12;
    }
}
