package com.alibaba.fastjson2.schema;

import com.alibaba.fastjson2.schema.JSONSchema;

/* loaded from: classes2.dex */
final class Any extends JSONSchema {
    public static final Any INSTANCE;
    public static final JSONSchema NOT_ANY;

    static {
        Any any = new Any();
        INSTANCE = any;
        NOT_ANY = new Not(any, null, null);
    }

    public Any() {
        super(null, null);
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public JSONSchema.Type getType() {
        return JSONSchema.Type.Any;
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(Object obj) {
        return SUCCESS;
    }
}
