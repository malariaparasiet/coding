package com.wifiled.blelibrary.ble.utils;

/* loaded from: classes2.dex */
public class UuidUtils {
    private static final String base_uuid_regex = "0000([0-9a-f][0-9a-f][0-9a-f][0-9a-f])-0000-1000-8000-00805f9b34fb";

    public static boolean isBaseUUID(String str) {
        return str.toLowerCase().matches(base_uuid_regex);
    }

    public static String uuid128To16(String str) {
        return uuid128To16(str, true);
    }

    public static String uuid128To16(String str, boolean z) {
        if (str.length() != 36) {
            return null;
        }
        if (z) {
            return str.substring(4, 8).toLowerCase();
        }
        return str.substring(4, 8).toUpperCase();
    }

    public static String uuid16To128(String str) {
        return uuid16To128(str, true);
    }

    public static String uuid16To128(String str, boolean z) {
        return z ? (base_uuid_regex.substring(0, 4) + str + base_uuid_regex.substring(38)).toLowerCase() : (base_uuid_regex.substring(0, 4) + str + base_uuid_regex.substring(38)).toUpperCase();
    }
}
