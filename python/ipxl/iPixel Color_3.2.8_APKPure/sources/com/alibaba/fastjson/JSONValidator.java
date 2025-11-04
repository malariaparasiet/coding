package com.alibaba.fastjson;

/* loaded from: classes2.dex */
public class JSONValidator {
    private char firstChar;
    private com.alibaba.fastjson2.JSONReader jsonReader;
    private Type type;
    private Boolean validateResult;

    public enum Type {
        Object,
        Array,
        Value
    }

    private JSONValidator(com.alibaba.fastjson2.JSONReader jSONReader) {
        this.jsonReader = jSONReader;
    }

    public static JSONValidator fromUtf8(byte[] bArr) {
        return new JSONValidator(com.alibaba.fastjson2.JSONReader.of(bArr));
    }

    public static JSONValidator from(String str) {
        return new JSONValidator(com.alibaba.fastjson2.JSONReader.of(str));
    }

    public boolean validate() {
        Boolean bool = this.validateResult;
        try {
            if (bool != null) {
                return bool.booleanValue();
            }
            try {
                this.firstChar = this.jsonReader.current();
                this.jsonReader.skipValue();
                this.jsonReader.close();
                char c = this.firstChar;
                if (c == '{') {
                    this.type = Type.Object;
                } else if (c == '[') {
                    this.type = Type.Array;
                } else {
                    this.type = Type.Value;
                }
                Boolean valueOf = Boolean.valueOf(this.jsonReader.isEnd());
                this.validateResult = valueOf;
                return valueOf.booleanValue();
            } catch (com.alibaba.fastjson2.JSONException | ArrayIndexOutOfBoundsException unused) {
                Boolean bool2 = false;
                this.validateResult = bool2;
                boolean booleanValue = bool2.booleanValue();
                this.jsonReader.close();
                return booleanValue;
            }
        } catch (Throwable th) {
            this.jsonReader.close();
            throw th;
        }
    }

    public Type getType() {
        if (this.type == null) {
            validate();
        }
        return this.type;
    }
}
