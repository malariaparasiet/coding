package com.alibaba.fastjson2.schema;

import com.alibaba.fastjson2.schema.JSONSchema;

/* loaded from: classes2.dex */
final class AllOf extends JSONSchema {
    final JSONSchema[] items;

    public AllOf(JSONSchema[] jSONSchemaArr) {
        super(null, null);
        this.items = jSONSchemaArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0082 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AllOf(com.alibaba.fastjson2.JSONObject r6, com.alibaba.fastjson2.schema.JSONSchema r7) {
        /*
            r5 = this;
            r5.<init>(r6)
            java.lang.String r0 = "allOf"
            com.alibaba.fastjson2.JSONArray r6 = r6.getJSONArray(r0)
            if (r6 == 0) goto L8f
            boolean r0 = r6.isEmpty()
            if (r0 != 0) goto L8f
            int r0 = r6.size()
            com.alibaba.fastjson2.schema.JSONSchema[] r0 = new com.alibaba.fastjson2.schema.JSONSchema[r0]
            r5.items = r0
            r0 = 0
            r1 = 0
            r2 = r0
        L1c:
            com.alibaba.fastjson2.schema.JSONSchema[] r3 = r5.items
            int r3 = r3.length
            if (r1 >= r3) goto L8e
            java.lang.Object r3 = r6.get(r1)
            boolean r4 = r3 instanceof java.lang.Boolean
            if (r4 == 0) goto L37
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r2 = r3.booleanValue()
            if (r2 == 0) goto L34
            com.alibaba.fastjson2.schema.Any r2 = com.alibaba.fastjson2.schema.Any.INSTANCE
            goto L82
        L34:
            com.alibaba.fastjson2.schema.JSONSchema r2 = com.alibaba.fastjson2.schema.Any.NOT_ANY
            goto L82
        L37:
            com.alibaba.fastjson2.JSONObject r3 = (com.alibaba.fastjson2.JSONObject) r3
            java.lang.String r4 = "$ref"
            boolean r4 = r3.containsKey(r4)
            if (r4 != 0) goto L7b
            java.lang.String r4 = "type"
            boolean r4 = r3.containsKey(r4)
            if (r4 != 0) goto L7b
            if (r2 == 0) goto L7b
            int[] r4 = com.alibaba.fastjson2.schema.AllOf.AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$schema$JSONSchema$Type
            int r2 = r2.ordinal()
            r2 = r4[r2]
            switch(r2) {
                case 1: goto L75;
                case 2: goto L6f;
                case 3: goto L69;
                case 4: goto L63;
                case 5: goto L5d;
                case 6: goto L57;
                default: goto L56;
            }
        L56:
            goto L7b
        L57:
            com.alibaba.fastjson2.schema.ObjectSchema r2 = new com.alibaba.fastjson2.schema.ObjectSchema
            r2.<init>(r3)
            goto L7c
        L5d:
            com.alibaba.fastjson2.schema.ArraySchema r2 = new com.alibaba.fastjson2.schema.ArraySchema
            r2.<init>(r3, r0)
            goto L7c
        L63:
            com.alibaba.fastjson2.schema.BooleanSchema r2 = new com.alibaba.fastjson2.schema.BooleanSchema
            r2.<init>(r3)
            goto L7c
        L69:
            com.alibaba.fastjson2.schema.NumberSchema r2 = new com.alibaba.fastjson2.schema.NumberSchema
            r2.<init>(r3)
            goto L7c
        L6f:
            com.alibaba.fastjson2.schema.IntegerSchema r2 = new com.alibaba.fastjson2.schema.IntegerSchema
            r2.<init>(r3)
            goto L7c
        L75:
            com.alibaba.fastjson2.schema.StringSchema r2 = new com.alibaba.fastjson2.schema.StringSchema
            r2.<init>(r3)
            goto L7c
        L7b:
            r2 = r0
        L7c:
            if (r2 != 0) goto L82
            com.alibaba.fastjson2.schema.JSONSchema r2 = com.alibaba.fastjson2.schema.JSONSchema.of(r3, r7)
        L82:
            com.alibaba.fastjson2.schema.JSONSchema$Type r3 = r2.getType()
            com.alibaba.fastjson2.schema.JSONSchema[] r4 = r5.items
            r4[r1] = r2
            int r1 = r1 + 1
            r2 = r3
            goto L1c
        L8e:
            return
        L8f:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException
            java.lang.String r7 = "allOf not found"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.schema.AllOf.<init>(com.alibaba.fastjson2.JSONObject, com.alibaba.fastjson2.schema.JSONSchema):void");
    }

    /* renamed from: com.alibaba.fastjson2.schema.AllOf$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$fastjson2$schema$JSONSchema$Type;

        static {
            int[] iArr = new int[JSONSchema.Type.values().length];
            $SwitchMap$com$alibaba$fastjson2$schema$JSONSchema$Type = iArr;
            try {
                iArr[JSONSchema.Type.String.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$schema$JSONSchema$Type[JSONSchema.Type.Integer.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$schema$JSONSchema$Type[JSONSchema.Type.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$schema$JSONSchema$Type[JSONSchema.Type.Boolean.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$schema$JSONSchema$Type[JSONSchema.Type.Array.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$schema$JSONSchema$Type[JSONSchema.Type.Object.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public JSONSchema.Type getType() {
        return JSONSchema.Type.AllOf;
    }

    @Override // com.alibaba.fastjson2.schema.JSONSchema
    public ValidateResult validate(Object obj) {
        for (JSONSchema jSONSchema : this.items) {
            ValidateResult validate = jSONSchema.validate(obj);
            if (!validate.isSuccess()) {
                return validate;
            }
        }
        return SUCCESS;
    }
}
