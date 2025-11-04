package com.alibaba.fastjson.support.hsf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class HSFJSONUtils {
    static final long HASH_ARGS_TYPES = Fnv.hashCode64("argsTypes");
    static final long HASH_ARGS_OBJS = Fnv.hashCode64("argsObjs");

    public static Object[] parseInvocationArguments(String str, MethodLocator methodLocator) {
        String[] strArr;
        Method method;
        String[] strArr2;
        JSONReader of = JSONReader.of(str);
        String str2 = null;
        if (!of.nextIfObjectStart()) {
            if (!of.nextIfArrayStart()) {
                return null;
            }
            if (of.nextIfArrayStart()) {
                int i = 0;
                ArrayList arrayList = null;
                String str3 = null;
                while (!of.nextIfArrayEnd()) {
                    if (of.isEnd()) {
                        throw new JSONException("illegal format");
                    }
                    String readString = of.readString();
                    if (i == 0) {
                        str2 = readString;
                    } else if (i == 1) {
                        str3 = readString;
                    } else if (i == 2) {
                        arrayList = new ArrayList();
                        arrayList.add(str2);
                        arrayList.add(str3);
                        arrayList.add(readString);
                    } else {
                        arrayList.add(readString);
                    }
                    i++;
                }
                of.nextIfComma();
                if (i == 0) {
                    strArr = new String[0];
                } else if (i == 1) {
                    strArr = new String[]{str2};
                } else if (i == 2) {
                    strArr = new String[]{str2, str3};
                } else {
                    String[] strArr3 = new String[arrayList.size()];
                    arrayList.toArray(strArr3);
                    strArr = strArr3;
                }
                return of.readArray(methodLocator.findMethod(strArr).getGenericParameterTypes());
            }
            throw new JSONException("illegal format");
        }
        if (of.readFieldNameHashCode() != HASH_ARGS_TYPES) {
            method = null;
        } else if (of.nextIfArrayStart()) {
            int i2 = 0;
            String str4 = null;
            ArrayList arrayList2 = null;
            String str5 = null;
            while (!of.nextIfArrayEnd()) {
                if (of.isEnd()) {
                    throw new JSONException("illegal format");
                }
                String readString2 = of.readString();
                if (i2 == 0) {
                    str4 = readString2;
                } else if (i2 == 1) {
                    str5 = readString2;
                } else if (i2 == 2) {
                    arrayList2 = new ArrayList();
                    arrayList2.add(str4);
                    arrayList2.add(str5);
                    arrayList2.add(readString2);
                } else {
                    arrayList2.add(readString2);
                }
                i2++;
            }
            of.nextIfComma();
            if (i2 == 0) {
                strArr2 = new String[0];
            } else if (i2 == 1) {
                strArr2 = new String[]{str4};
            } else if (i2 == 2) {
                strArr2 = new String[]{str4, str5};
            } else {
                strArr2 = new String[arrayList2.size()];
                arrayList2.toArray(strArr2);
            }
            method = methodLocator.findMethod(strArr2);
        } else {
            throw new JSONException("illegal format");
        }
        if (method != null) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            if (of.readFieldNameHashCode() == HASH_ARGS_OBJS) {
                return of.readArray(genericParameterTypes);
            }
            throw new JSONException("illegal format");
        }
        JSONObject parseObject = JSON.parseObject(str);
        Method findMethod = methodLocator.findMethod((String[]) parseObject.getObject("argsTypes", String[].class));
        JSONArray jSONArray = parseObject.getJSONArray("argsObjs");
        if (jSONArray == null) {
            return null;
        }
        Type[] genericParameterTypes2 = findMethod.getGenericParameterTypes();
        Object[] objArr = new Object[genericParameterTypes2.length];
        for (int i3 = 0; i3 < genericParameterTypes2.length; i3++) {
            objArr[i3] = jSONArray.getObject(i3, genericParameterTypes2[i3]);
        }
        return objArr;
    }
}
