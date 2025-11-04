package com.wifiled.blelibrary.ble.request;

import android.content.Context;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.annotation.Implement;
import dalvik.system.DexFile;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class Rproxy {
    private static Map<Class, Object> requestObjs;

    public static void init(Class... clsArr) {
        requestObjs = new HashMap();
        for (Class cls : clsArr) {
            if (cls.isAnnotationPresent(Implement.class)) {
                for (Annotation annotation : cls.getDeclaredAnnotations()) {
                    if (annotation instanceof Implement) {
                        try {
                            requestObjs.put(cls, ((Implement) annotation).value().newInstance());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static <T> T getRequest(Class cls) {
        T t = (T) requestObjs.get(cls);
        return t != null ? t : (T) getRequestByReflect(cls);
    }

    private static <T> T getRequestByReflect(Class cls) {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            T t = null;
            try {
                t = declaredConstructor.newInstance(new Object[0]);
                requestObjs.put(cls, t);
                return t;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return t;
            } catch (InstantiationException e2) {
                e2.printStackTrace();
                return t;
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
                return t;
            }
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            throw new NoClassDefFoundError("Class not Request Type");
        }
    }

    public static void release() {
        requestObjs.clear();
        BleLog.d("Rproxy", "Request proxy cache is released");
    }

    private List<Class> getRequestsClass(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<String> entries = new DexFile(context.getPackageCodePath()).entries();
            while (entries.hasMoreElements()) {
                String nextElement = entries.nextElement();
                if (nextElement.contains(str) && !nextElement.contains("$")) {
                    try {
                        arrayList.add(Class.forName(nextElement));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }
}
