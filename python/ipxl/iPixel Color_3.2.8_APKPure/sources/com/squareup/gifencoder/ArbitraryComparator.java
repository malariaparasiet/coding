package com.squareup.gifencoder;

import java.util.Comparator;
import java.util.WeakHashMap;

/* loaded from: classes2.dex */
final class ArbitraryComparator implements Comparator<Object> {
    public static final ArbitraryComparator INSTANCE = new ArbitraryComparator();
    private static final WeakHashMap<Object, Integer> objectIds = new WeakHashMap<>();

    private ArbitraryComparator() {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        if (obj == obj2) {
            return 0;
        }
        if (obj == null) {
            return -1;
        }
        if (obj2 == null) {
            return 1;
        }
        int identityHashCode = System.identityHashCode(obj) - System.identityHashCode(obj2);
        return identityHashCode != 0 ? identityHashCode : getObjectId(obj) - getObjectId(obj2);
    }

    private static int getObjectId(Object obj) {
        int intValue;
        WeakHashMap<Object, Integer> weakHashMap = objectIds;
        synchronized (weakHashMap) {
            Integer num = weakHashMap.get(obj);
            if (num == null) {
                num = Integer.valueOf(weakHashMap.size());
                weakHashMap.put(obj, num);
            }
            intValue = num.intValue();
        }
        return intValue;
    }
}
