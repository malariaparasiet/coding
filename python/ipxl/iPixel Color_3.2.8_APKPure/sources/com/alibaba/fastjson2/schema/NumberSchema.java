package com.alibaba.fastjson2.schema;

import androidx.camera.video.AudioStats;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.schema.JSONSchema;
import java.math.BigDecimal;

/* loaded from: classes2.dex */
public final class NumberSchema extends JSONSchema {
    final boolean exclusiveMaximum;
    final boolean exclusiveMinimum;
    final BigDecimal maximum;
    final long maximumLongValue;
    final BigDecimal minimum;
    final long minimumLongValue;
    final BigDecimal multipleOf;
    final long multipleOfLongValue;
    final boolean typed;

    NumberSchema(JSONObject jSONObject) {
        super(jSONObject);
        this.typed = "number".equals(jSONObject.get("type"));
        Object obj = jSONObject.get("exclusiveMinimum");
        BigDecimal bigDecimal = jSONObject.getBigDecimal("minimum");
        if (obj == Boolean.TRUE) {
            this.minimum = bigDecimal;
            this.exclusiveMinimum = true;
        } else if (obj instanceof Number) {
            this.minimum = jSONObject.getBigDecimal("exclusiveMinimum");
            this.exclusiveMinimum = true;
        } else {
            this.minimum = bigDecimal;
            this.exclusiveMinimum = false;
        }
        BigDecimal bigDecimal2 = this.minimum;
        if (bigDecimal2 == null || bigDecimal2.compareTo(BigDecimal.valueOf(bigDecimal2.longValue())) != 0) {
            this.minimumLongValue = Long.MIN_VALUE;
        } else {
            this.minimumLongValue = this.minimum.longValue();
        }
        BigDecimal bigDecimal3 = jSONObject.getBigDecimal("maximum");
        Object obj2 = jSONObject.get("exclusiveMaximum");
        if (obj2 == Boolean.TRUE) {
            this.maximum = bigDecimal3;
            this.exclusiveMaximum = true;
        } else if (obj2 instanceof Number) {
            this.maximum = jSONObject.getBigDecimal("exclusiveMaximum");
            this.exclusiveMaximum = true;
        } else {
            this.maximum = bigDecimal3;
            this.exclusiveMaximum = false;
        }
        BigDecimal bigDecimal4 = this.maximum;
        if (bigDecimal4 == null || bigDecimal4.compareTo(BigDecimal.valueOf(bigDecimal4.longValue())) != 0) {
            this.maximumLongValue = Long.MIN_VALUE;
        } else {
            this.maximumLongValue = this.maximum.longValue();
        }
        BigDecimal bigDecimal5 = jSONObject.getBigDecimal("multipleOf");
        this.multipleOf = bigDecimal5;
        if (bigDecimal5 == null) {
            this.multipleOfLongValue = Long.MIN_VALUE;
            return;
        }
        long longValue = bigDecimal5.longValue();
        if (bigDecimal5.compareTo(BigDecimal.valueOf(longValue)) == 0) {
            this.multipleOfLongValue = longValue;
        } else {
            this.multipleOfLongValue = Long.MIN_VALUE;
        }
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public JSONSchema.Type getType() {
        return JSONSchema.Type.Number;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0086, code lost:
    
        if (r4.exclusiveMaximum == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0088, code lost:
    
        r1 = "exclusiveMaximum not match, expect < %s, but %s";
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0096, code lost:
    
        return new com.alibaba.fastjson2.schema.ValidateResult(false, r1, r4.maximum, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008b, code lost:
    
        r1 = "maximum not match, expect <= %s, but %s";
     */
    @Override // com.alibaba.fastjson2.schema.JSONSchema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.alibaba.fastjson2.schema.ValidateResult validate(java.lang.Object r5) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.schema.NumberSchema.validate(java.lang.Object):com.alibaba.fastjson2.schema.ValidateResult");
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(Integer num) {
        if (num == null) {
            return SUCCESS;
        }
        return validate(num.longValue());
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(Float f) {
        if (f == null) {
            return SUCCESS;
        }
        return validate(f.doubleValue());
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(Double d) {
        if (d == null) {
            return SUCCESS;
        }
        return validate(d.doubleValue());
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(Long l) {
        if (l == null) {
            return SUCCESS;
        }
        return validate(l.longValue());
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0081, code lost:
    
        if (r9.exclusiveMaximum == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0084, code lost:
    
        r7 = "maximum not match, expect <= %s, but %s";
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0092, code lost:
    
        return new com.alibaba.fastjson2.schema.ValidateResult(false, r7, r9.maximum, java.lang.Long.valueOf(r10));
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c8  */
    @Override // com.alibaba.fastjson2.schema.JSONSchema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.alibaba.fastjson2.schema.ValidateResult validate(long r10) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.schema.NumberSchema.validate(long):com.alibaba.fastjson2.schema.ValidateResult");
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(double d) {
        BigDecimal bigDecimal = this.minimum;
        if (bigDecimal != null) {
            long j = this.minimumLongValue;
            if (j != Long.MIN_VALUE) {
                double d2 = j;
                if (!this.exclusiveMinimum ? d < d2 : d <= d2) {
                    return new ValidateResult(false, this.exclusiveMinimum ? "exclusiveMinimum not match, expect > %s, but %s" : "minimum not match, expect >= %s, but %s", this.minimum, Double.valueOf(d));
                }
            } else {
                double doubleValue = bigDecimal.doubleValue();
                if (!this.exclusiveMinimum ? d < doubleValue : d <= doubleValue) {
                    return new ValidateResult(false, this.exclusiveMinimum ? "exclusiveMinimum not match, expect > %s, but %s" : "minimum not match, expect >= %s, but %s", this.minimum, Double.valueOf(d));
                }
            }
        }
        BigDecimal bigDecimal2 = this.maximum;
        if (bigDecimal2 != null) {
            long j2 = this.maximumLongValue;
            if (j2 != Long.MIN_VALUE) {
                double d3 = j2;
                if (!this.exclusiveMaximum ? d > d3 : d >= d3) {
                    return new ValidateResult(false, this.exclusiveMaximum ? "exclusiveMaximum not match, expect < %s, but %s" : "maximum not match, expect <= %s, but %s", this.maximum, Double.valueOf(d));
                }
            } else {
                double doubleValue2 = bigDecimal2.doubleValue();
                if (!this.exclusiveMaximum ? d > doubleValue2 : d >= doubleValue2) {
                    return new ValidateResult(false, this.exclusiveMaximum ? "exclusiveMaximum not match, expect < %s, but %s" : "maximum not match, expect <= %s, but %s", this.maximum, Double.valueOf(d));
                }
            }
        }
        if (this.multipleOf != null) {
            long j3 = this.multipleOfLongValue;
            if (j3 != Long.MIN_VALUE && d % j3 != AudioStats.AUDIO_AMPLITUDE_NONE) {
                return new ValidateResult(false, "multipleOf not match, expect multipleOf %s, but %s", this.multipleOf, Double.valueOf(d));
            }
            BigDecimal valueOf = BigDecimal.valueOf(d);
            if (valueOf.divideAndRemainder(this.multipleOf)[1].abs().compareTo(BigDecimal.ZERO) > 0) {
                return new ValidateResult(false, "multipleOf not match, expect multipleOf %s, but %s", this.multipleOf, valueOf);
            }
        }
        return SUCCESS;
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public JSONObject toJSONObject() {
        JSONObject of = JSONObject.of("type", (Object) "number");
        long j = this.minimumLongValue;
        if (j != Long.MIN_VALUE) {
            of.put(this.exclusiveMinimum ? "exclusiveMinimum" : "minimum", Long.valueOf(j));
        } else {
            BigDecimal bigDecimal = this.minimum;
            if (bigDecimal != null) {
                of.put(this.exclusiveMinimum ? "exclusiveMinimum" : "minimum", bigDecimal);
            }
        }
        long j2 = this.maximumLongValue;
        if (j2 != Long.MIN_VALUE) {
            of.put(this.exclusiveMaximum ? "exclusiveMaximum" : "maximum", Long.valueOf(j2));
        } else {
            BigDecimal bigDecimal2 = this.maximum;
            if (bigDecimal2 != null) {
                of.put(this.exclusiveMaximum ? "exclusiveMaximum" : "maximum", bigDecimal2);
            }
        }
        long j3 = this.multipleOfLongValue;
        if (j3 != Long.MIN_VALUE) {
            of.put("multipleOf", Long.valueOf(j3));
            return of;
        }
        BigDecimal bigDecimal3 = this.multipleOf;
        if (bigDecimal3 != null) {
            of.put("multipleOf", bigDecimal3);
        }
        return of;
    }
}
