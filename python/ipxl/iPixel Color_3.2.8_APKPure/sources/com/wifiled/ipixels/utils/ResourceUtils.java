package com.wifiled.ipixels.utils;

import android.content.Context;
import android.content.res.TypedArray;

/* loaded from: classes3.dex */
public class ResourceUtils {
    public static int[] getResIds(Context context, int resId) {
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(resId);
        int length = obtainTypedArray.length();
        int[] iArr = new int[obtainTypedArray.length()];
        for (int i = 0; i < length; i++) {
            iArr[i] = obtainTypedArray.getResourceId(i, 0);
        }
        obtainTypedArray.recycle();
        return iArr;
    }
}
