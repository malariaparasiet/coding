package com.alibaba.fastjson2.internal.asm;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class TypeCollector {
    static final Map<String, String> PRIMITIVES;
    protected MethodCollector collector = null;
    final String methodName;
    final Class<?>[] parameterTypes;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("int", "I");
        hashMap.put(TypedValues.Custom.S_BOOLEAN, "Z");
        hashMap.put("byte", "B");
        hashMap.put("char", "C");
        hashMap.put("short", ExifInterface.LATITUDE_SOUTH);
        hashMap.put(TypedValues.Custom.S_FLOAT, "F");
        hashMap.put("long", "J");
        hashMap.put("double", "D");
        PRIMITIVES = hashMap;
    }

    public TypeCollector(String str, Class<?>[] clsArr) {
        this.methodName = str;
        this.parameterTypes = clsArr;
    }

    protected MethodCollector visitMethod(int i, String str, String str2) {
        if (this.collector != null || !str.equals(this.methodName)) {
            return null;
        }
        Type[] argumentTypes = Type.getArgumentTypes(str2);
        int i2 = 0;
        for (Type type : argumentTypes) {
            String className = type.getClassName();
            if ("long".equals(className) || "double".equals(className)) {
                i2++;
            }
        }
        if (argumentTypes.length != this.parameterTypes.length) {
            return null;
        }
        for (int i3 = 0; i3 < argumentTypes.length; i3++) {
            if (!correctTypeName(argumentTypes[i3], this.parameterTypes[i3].getName())) {
                return null;
            }
        }
        MethodCollector methodCollector = new MethodCollector(!Modifier.isStatic(i) ? 1 : 0, argumentTypes.length + i2);
        this.collector = methodCollector;
        return methodCollector;
    }

    private boolean correctTypeName(Type type, String str) {
        String className = type.getClassName();
        StringBuilder sb = new StringBuilder();
        while (className.endsWith(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI)) {
            sb.append('[');
            className = className.substring(0, className.length() - 2);
        }
        if (sb.length() != 0) {
            String str2 = PRIMITIVES.get(className);
            if (str2 != null) {
                className = sb.append(str2).toString();
            } else {
                className = sb.append(Matrix.MATRIX_TYPE_RANDOM_LT).append(className).append(';').toString();
            }
        }
        return className.equals(str);
    }

    public String[] getParameterNamesForMethod() {
        MethodCollector methodCollector = this.collector;
        if (methodCollector == null || !methodCollector.debugInfoPresent) {
            return new String[0];
        }
        return this.collector.getResult().split(",");
    }
}
