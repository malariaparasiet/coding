package com.wifiled.ipixels.utils;

/* loaded from: classes3.dex */
public class ClickFilter {
    private static final long INTERVAL = 500;
    private static long lastClickTime;

    public static boolean filter() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = lastClickTime;
        long j2 = currentTimeMillis - j;
        if (j != 0 && j2 < INTERVAL) {
            lastClickTime = currentTimeMillis;
            return true;
        }
        lastClickTime = currentTimeMillis;
        return false;
    }

    public static boolean filter(long INTERVAL2) {
        long currentTimeMillis = System.currentTimeMillis();
        long j = lastClickTime;
        long j2 = currentTimeMillis - j;
        if (j != 0 && j2 < INTERVAL2) {
            lastClickTime = currentTimeMillis;
            return true;
        }
        lastClickTime = currentTimeMillis;
        return false;
    }
}
