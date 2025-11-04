package com.alibaba.fastjson2;

import com.alibaba.fastjson2.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;

/* loaded from: classes2.dex */
final class JSONPathTypedMultiIndexes extends JSONPathTypedMulti {
    final boolean duplicate;
    final JSONPath[] indexPaths;
    final int[] indexes;
    final int maxIndex;
    final JSONPath prefix;

    JSONPathTypedMultiIndexes(JSONPath[] jSONPathArr, JSONPath jSONPath, JSONPath[] jSONPathArr2, Type[] typeArr, String[] strArr, long[] jArr, ZoneId zoneId, long j) {
        super(jSONPathArr, typeArr, strArr, jArr, zoneId, j);
        this.prefix = jSONPath;
        this.indexPaths = jSONPathArr2;
        int length = jSONPathArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < jSONPathArr2.length; i++) {
            iArr[i] = ((JSONPathSingleIndex) jSONPathArr2[i]).index;
        }
        this.indexes = iArr;
        int i2 = -1;
        int i3 = 0;
        boolean z = false;
        while (i3 < length) {
            int i4 = iArr[i3];
            i2 = i3 == 0 ? i4 : Math.max(i2, i4);
            int i5 = 0;
            while (true) {
                if (i5 < length && !z) {
                    if (i5 != i3 && i4 == iArr[i5]) {
                        z = true;
                        break;
                    }
                    i5++;
                }
            }
            i3++;
        }
        this.duplicate = z;
        this.maxIndex = i2;
    }

    @Override // com.alibaba.fastjson2.JSONPathTypedMulti, com.alibaba.fastjson2.JSONPath
    public Object eval(Object obj) {
        Object[] objArr = new Object[this.paths.length];
        JSONPath jSONPath = this.prefix;
        if (jSONPath != null) {
            obj = jSONPath.eval(obj);
        }
        if (obj != null) {
            int i = 0;
            if (obj instanceof List) {
                List list = (List) obj;
                while (true) {
                    int[] iArr = this.indexes;
                    if (i >= iArr.length) {
                        break;
                    }
                    int i2 = iArr[i];
                    Object obj2 = i2 < list.size() ? list.get(i2) : null;
                    Type type = this.types[i];
                    if (obj2 != null) {
                        try {
                            if (obj2.getClass() != type) {
                                obj2 = type == Long.class ? TypeUtils.toLong(obj2) : type == BigDecimal.class ? TypeUtils.toBigDecimal(obj2) : type == String[].class ? TypeUtils.toStringArray(obj2) : TypeUtils.cast(obj2, type);
                            }
                        } catch (Exception e) {
                            if (!ignoreError(i)) {
                                throw new JSONException("jsonpath eval path, path : " + this.paths[i] + ", msg : " + e.getMessage(), e);
                            }
                        }
                    }
                    objArr[i] = obj2;
                    i++;
                }
            } else {
                while (i < this.paths.length) {
                    JSONPath jSONPath2 = this.indexPaths[i];
                    Type type2 = this.types[i];
                    try {
                        Object eval = jSONPath2.eval(obj);
                        if (eval != null && eval.getClass() != type2) {
                            eval = type2 == Long.class ? TypeUtils.toLong(eval) : type2 == BigDecimal.class ? TypeUtils.toBigDecimal(eval) : type2 == String[].class ? TypeUtils.toStringArray(eval) : TypeUtils.cast(eval, type2);
                        }
                        objArr[i] = eval;
                    } catch (Exception e2) {
                        if (!ignoreError(i)) {
                            throw new JSONException("jsonpath eval path, path : " + this.paths[i] + ", msg : " + e2.getMessage(), e2);
                        }
                    }
                    i++;
                }
            }
        }
        return objArr;
    }

    @Override // com.alibaba.fastjson2.JSONPathTypedMulti, com.alibaba.fastjson2.JSONPath
    public Object extract(JSONReader jSONReader) {
        Integer num;
        Object obj;
        if (jSONReader.nextIfNull()) {
            return new Object[this.indexes.length];
        }
        JSONPath jSONPath = this.prefix;
        if (jSONPath instanceof JSONPathSingleName) {
            long j = ((JSONPathSingleName) jSONPath).nameHashCode;
            if (!jSONReader.nextIfObjectStart()) {
                throw new JSONException(jSONReader.info("illegal input, expect '[', but " + jSONReader.current()));
            }
            while (!jSONReader.nextIfObjectEnd() && jSONReader.readFieldNameHashCode() != j) {
                jSONReader.skipValue();
            }
            if (jSONReader.nextIfNull()) {
                return new Object[this.indexes.length];
            }
        } else if (jSONPath instanceof JSONPathSingleIndex) {
            int i = ((JSONPathSingleIndex) jSONPath).index;
            int startArray = jSONReader.startArray();
            for (int i2 = 0; i2 < i && i2 < startArray; i2++) {
                jSONReader.skipValue();
            }
            if (jSONReader.nextIfNull()) {
                return null;
            }
        } else if (jSONPath != null) {
            return eval(jSONReader.readAny());
        }
        int startArray2 = jSONReader.startArray();
        Object[] objArr = new Object[this.indexes.length];
        for (int i3 = 0; i3 <= this.maxIndex && i3 < startArray2 && (jSONReader.jsonb || !jSONReader.nextIfArrayEnd()); i3++) {
            int i4 = 0;
            while (true) {
                int[] iArr = this.indexes;
                if (i4 >= iArr.length) {
                    num = null;
                    break;
                }
                if (iArr[i4] == i3) {
                    num = Integer.valueOf(i4);
                    break;
                }
                i4++;
            }
            if (num == null) {
                jSONReader.skipValue();
            } else {
                Type type = this.types[num.intValue()];
                try {
                    obj = jSONReader.read(type);
                } catch (Exception e) {
                    if (!ignoreError(num.intValue())) {
                        throw e;
                    }
                    obj = null;
                }
                objArr[num.intValue()] = obj;
                if (this.duplicate) {
                    int intValue = num.intValue();
                    while (true) {
                        intValue++;
                        int[] iArr2 = this.indexes;
                        if (intValue < iArr2.length) {
                            if (iArr2[intValue] == i3) {
                                Type type2 = this.types[intValue];
                                objArr[intValue] = type2 == type ? obj : TypeUtils.cast(obj, type2);
                            }
                        }
                    }
                }
            }
        }
        return objArr;
    }
}
