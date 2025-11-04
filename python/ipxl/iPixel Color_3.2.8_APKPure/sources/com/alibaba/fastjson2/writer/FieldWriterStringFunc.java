package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.codec.FieldInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Function;

/* loaded from: classes2.dex */
final class FieldWriterStringFunc<T> extends FieldWriter<T> {
    final Function<T, String> function;
    final boolean raw;
    final boolean symbol;
    final boolean trim;

    FieldWriterStringFunc(String str, int i, long j, String str2, String str3, Field field, Method method, Function<T, String> function) {
        super(str, i, j, str2, null, str3, String.class, String.class, field, method);
        this.function = function;
        this.symbol = "symbol".equals(str2);
        this.trim = "trim".equals(str2);
        this.raw = (FieldInfo.RAW_VALUE_MASK & j) != 0;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public Object getFieldValue(T t) {
        return this.function.apply(t);
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public boolean write(JSONWriter jSONWriter, T t) {
        try {
            String apply = this.function.apply(t);
            long features = this.features | jSONWriter.getFeatures();
            if (apply == null) {
                if (((JSONWriter.Feature.WriteNulls.mask | JSONWriter.Feature.NullAsDefaultValue.mask | JSONWriter.Feature.WriteNullStringAsEmpty.mask) & features) == 0) {
                    return false;
                }
            } else if (this.trim) {
                apply = apply.trim();
            }
            if (apply != null && apply.isEmpty() && (JSONWriter.Feature.IgnoreEmpty.mask & features) != 0) {
                return false;
            }
            writeFieldName(jSONWriter);
            if (apply == null) {
                if ((features & (JSONWriter.Feature.NullAsDefaultValue.mask | JSONWriter.Feature.WriteNullStringAsEmpty.mask)) != 0) {
                    jSONWriter.writeString("");
                } else {
                    jSONWriter.writeNull();
                }
                return true;
            }
            if (this.symbol && jSONWriter.jsonb) {
                jSONWriter.writeSymbol(apply);
            } else if (this.raw) {
                jSONWriter.writeRaw(apply);
            } else {
                jSONWriter.writeString(apply);
            }
            return true;
        } catch (RuntimeException e) {
            if ((jSONWriter.getFeatures(this.features) | JSONWriter.Feature.IgnoreNonFieldGetter.mask) != 0) {
                return false;
            }
            throw e;
        }
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public void writeValue(JSONWriter jSONWriter, T t) {
        String apply = this.function.apply(t);
        if (this.trim && apply != null) {
            apply = apply.trim();
        }
        if (this.symbol && jSONWriter.jsonb) {
            jSONWriter.writeSymbol(apply);
        } else if (this.raw) {
            jSONWriter.writeRaw(apply);
        } else {
            jSONWriter.writeString(apply);
        }
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public Function getFunction() {
        return this.function;
    }
}
