package com.alibaba.fastjson2.schema;

import com.alibaba.fastjson2.JSONPathFunction$IndexDecimal$$ExternalSyntheticBackportWithForwarding0;
import com.alibaba.fastjson2.schema.JSONSchema;
import com.alibaba.fastjson2.util.TypeUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public final class EnumSchema extends JSONSchema {
    final Set<Object> items;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.math.BigDecimal] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.math.BigInteger] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Long] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.Integer] */
    EnumSchema(Object... objArr) {
        super(null, null);
        this.items = new LinkedHashSet(objArr.length);
        for (?? r2 : objArr) {
            if (r2 instanceof BigDecimal) {
                r2 = JSONPathFunction$IndexDecimal$$ExternalSyntheticBackportWithForwarding0.m((BigDecimal) r2);
                if (r2.scale() == 0) {
                    r2 = r2.toBigInteger();
                    if (r2.compareTo(TypeUtils.BIGINT_INT32_MIN) >= 0 && r2.compareTo(TypeUtils.BIGINT_INT32_MAX) <= 0) {
                        r2 = Integer.valueOf(r2.intValue());
                    } else if (r2.compareTo(TypeUtils.BIGINT_INT64_MIN) >= 0 && r2.compareTo(TypeUtils.BIGINT_INT64_MAX) <= 0) {
                        r2 = Long.valueOf(r2.longValue());
                    }
                }
            }
            this.items.add(r2);
        }
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public JSONSchema.Type getType() {
        return JSONSchema.Type.Enum;
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(Object obj) {
        if (obj instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) obj;
            BigDecimal m = JSONPathFunction$IndexDecimal$$ExternalSyntheticBackportWithForwarding0.m(bigDecimal);
            long longValue = bigDecimal.longValue();
            if (bigDecimal.compareTo(BigDecimal.valueOf(longValue)) == 0) {
                obj = Long.valueOf(longValue);
            } else {
                obj = bigDecimal.scale() == 0 ? bigDecimal.unscaledValue() : m;
            }
        } else if (obj instanceof BigInteger) {
            BigInteger bigInteger = (BigInteger) obj;
            if (bigInteger.compareTo(TypeUtils.BIGINT_INT64_MIN) >= 0 && bigInteger.compareTo(TypeUtils.BIGINT_INT64_MAX) <= 0) {
                obj = Long.valueOf(bigInteger.longValue());
            }
        }
        if (obj instanceof Long) {
            long longValue2 = ((Long) obj).longValue();
            if (longValue2 >= -2147483648L && longValue2 <= 2147483647L) {
                obj = Integer.valueOf((int) longValue2);
            }
        }
        if (this.items.contains(obj)) {
            return SUCCESS;
        }
        if (obj == null) {
            return FAIL_INPUT_NULL;
        }
        return new ValidateResult(false, "expect type %s, but %s", JSONSchema.Type.Enum, obj.getClass());
    }
}
