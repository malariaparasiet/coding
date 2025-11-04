package com.easysocket.utils;

import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Random;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class Util {
    public static <T> Type findGenericityType(Class<T> cls) {
        Type type = ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        if (type instanceof TypeVariable) {
            throw new IllegalStateException("没有填写泛型参数");
        }
        return type;
    }

    public static boolean isStringEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String getRandomChar(int i) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO};
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(cArr[random.nextInt(36)]);
        }
        return stringBuffer.toString();
    }

    public static Handler getHandler(boolean z) {
        if (z) {
            return new Handler(Looper.getMainLooper());
        }
        Looper.prepare();
        return new Handler();
    }

    public static void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void checkNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        try {
            throw new Exception(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void throwNotNull(Object obj, String str) throws Exception {
        if (obj == null) {
            throw new Exception(str);
        }
    }
}
