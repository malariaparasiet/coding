package com.alibaba.fastjson2.schema;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.schema.JSONSchema;

/* loaded from: classes2.dex */
public final class IntegerSchema extends JSONSchema {
    final Long constValue;
    final boolean exclusiveMaximum;
    final boolean exclusiveMinimum;
    final long maximum;
    final long minimum;
    final long multipleOf;
    final boolean typed;

    IntegerSchema(JSONObject jSONObject) {
        super(jSONObject);
        this.typed = TypedValues.Custom.S_INT.equalsIgnoreCase(jSONObject.getString("type")) || jSONObject.getBooleanValue("required");
        Object obj = jSONObject.get("exclusiveMinimum");
        long longValue = jSONObject.getLongValue("minimum", Long.MIN_VALUE);
        if (obj == Boolean.TRUE) {
            this.exclusiveMinimum = true;
            this.minimum = longValue;
        } else if (obj instanceof Number) {
            this.exclusiveMinimum = true;
            this.minimum = jSONObject.getLongValue("exclusiveMinimum");
        } else {
            this.minimum = longValue;
            this.exclusiveMinimum = false;
        }
        long longValue2 = jSONObject.getLongValue("maximum", Long.MIN_VALUE);
        Object obj2 = jSONObject.get("exclusiveMaximum");
        if (obj2 == Boolean.TRUE) {
            this.exclusiveMaximum = true;
            this.maximum = longValue2;
        } else if (obj2 instanceof Number) {
            this.exclusiveMaximum = true;
            this.maximum = jSONObject.getLongValue("exclusiveMaximum");
        } else {
            this.exclusiveMaximum = false;
            this.maximum = longValue2;
        }
        this.multipleOf = jSONObject.getLongValue("multipleOf", 0L);
        this.constValue = jSONObject.getLong("const");
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public JSONSchema.Type getType() {
        return JSONSchema.Type.Integer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00e3, code lost:
    
        if (r10.constValue.longValue() == java.lang.Long.parseLong(r1)) goto L69;
     */
    @Override // com.alibaba.fastjson2.schema.JSONSchema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.alibaba.fastjson2.schema.ValidateResult validate(java.lang.Object r11) {
        /*
            Method dump skipped, instructions count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.schema.IntegerSchema.validate(java.lang.Object):com.alibaba.fastjson2.schema.ValidateResult");
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(long j) {
        long j2 = this.minimum;
        if (j2 != Long.MIN_VALUE && (!this.exclusiveMinimum ? j < j2 : j <= j2)) {
            return new ValidateResult(false, this.exclusiveMinimum ? "exclusiveMinimum not match, expect > %s, but %s" : "minimum not match, expect >= %s, but %s", Long.valueOf(this.minimum), Long.valueOf(j));
        }
        long j3 = this.maximum;
        if (j3 != Long.MIN_VALUE && (!this.exclusiveMaximum ? j > j3 : j >= j3)) {
            return new ValidateResult(false, this.exclusiveMaximum ? "exclusiveMaximum not match, expect < %s, but %s" : "maximum not match, expect <= %s, but %s", Long.valueOf(this.maximum), Long.valueOf(j));
        }
        long j4 = this.multipleOf;
        if (j4 != 0 && j % j4 != 0) {
            return new ValidateResult(false, "multipleOf not match, expect multipleOf %s, but %s", Long.valueOf(this.multipleOf), Long.valueOf(j));
        }
        Long l = this.constValue;
        if (l != null && l.longValue() != j) {
            return new ValidateResult(false, "const not match, expect %s, but %s", this.constValue, Long.valueOf(j));
        }
        return SUCCESS;
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(Long l) {
        if (l == null) {
            return this.typed ? FAIL_INPUT_NULL : SUCCESS;
        }
        long longValue = l.longValue();
        long j = this.minimum;
        if (j != Long.MIN_VALUE && (!this.exclusiveMinimum ? longValue < j : longValue <= j)) {
            return new ValidateResult(false, this.exclusiveMinimum ? "exclusiveMinimum not match, expect > %s, but %s" : "minimum not match, expect >= %s, but %s", Long.valueOf(this.minimum), l);
        }
        long j2 = this.maximum;
        if (j2 != Long.MIN_VALUE && (!this.exclusiveMaximum ? longValue > j2 : longValue >= j2)) {
            return new ValidateResult(false, this.exclusiveMaximum ? "exclusiveMaximum not match, expect < %s, but %s" : "maximum not match, expect <= %s, but %s", Long.valueOf(this.maximum), l);
        }
        long j3 = this.multipleOf;
        if (j3 != 0 && longValue % j3 != 0) {
            return new ValidateResult(false, "multipleOf not match, expect multipleOf %s, but %s", Long.valueOf(this.multipleOf), Long.valueOf(longValue));
        }
        Long l2 = this.constValue;
        if (l2 != null && l2.longValue() != longValue) {
            return new ValidateResult(false, "const not match, expect %s, but %s", this.constValue, l);
        }
        return SUCCESS;
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(Integer num) {
        if (num == null) {
            return this.typed ? FAIL_INPUT_NULL : SUCCESS;
        }
        long longValue = num.longValue();
        long j = this.minimum;
        if (j != Long.MIN_VALUE && (!this.exclusiveMinimum ? longValue < j : longValue <= j)) {
            return new ValidateResult(false, this.exclusiveMinimum ? "exclusiveMinimum not match, expect > %s, but %s" : "minimum not match, expect >= %s, but %s", Long.valueOf(this.minimum), num);
        }
        long j2 = this.maximum;
        if (j2 != Long.MIN_VALUE && (!this.exclusiveMaximum ? longValue > j2 : longValue >= j2)) {
            return new ValidateResult(false, this.exclusiveMaximum ? "exclusiveMaximum not match, expect < %s, but %s" : "maximum not match, expect <= %s, but %s", Long.valueOf(this.maximum), num);
        }
        long j3 = this.multipleOf;
        if (j3 != 0 && longValue % j3 != 0) {
            return new ValidateResult(false, "multipleOf not match, expect multipleOf %s, but %s", Long.valueOf(this.multipleOf), Long.valueOf(longValue));
        }
        Long l = this.constValue;
        if (l != null && l.longValue() != longValue) {
            return new ValidateResult(false, "const not match, expect %s, but %s", this.constValue, num);
        }
        return SUCCESS;
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", TypedValues.Custom.S_INT);
        long j = this.minimum;
        if (j != Long.MIN_VALUE) {
            jSONObject.put(this.exclusiveMinimum ? "exclusiveMinimum" : "minimum", Long.valueOf(j));
        }
        long j2 = this.maximum;
        if (j2 != Long.MIN_VALUE) {
            jSONObject.put(this.exclusiveMaximum ? "exclusiveMaximum" : "maximum", Long.valueOf(j2));
        }
        long j3 = this.multipleOf;
        if (j3 != 0) {
            jSONObject.put("multipleOf", Long.valueOf(j3));
        }
        Long l = this.constValue;
        if (l != null) {
            jSONObject.put("const", l);
        }
        return jSONObject;
    }
}
