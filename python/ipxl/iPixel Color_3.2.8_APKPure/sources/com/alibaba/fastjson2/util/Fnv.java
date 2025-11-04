package com.alibaba.fastjson2.util;

/* loaded from: classes2.dex */
public final class Fnv {
    public static final long MAGIC_HASH_CODE = -3750763034362895579L;
    public static final long MAGIC_PRIME = 1099511628211L;

    public static long hashCode64LCase(String str) {
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i < str.length()) {
                char charAt = str.charAt(i);
                if (charAt > 255 || (i == 0 && charAt == 0)) {
                    break;
                }
                if (charAt == '-' || charAt == '_' || charAt == ' ') {
                    i2++;
                }
                i++;
            } else if (str.length() - i2 <= 8) {
                int i3 = 0;
                long j = 0;
                for (int length = str.length() - 1; length >= 0; length--) {
                    char charAt2 = str.charAt(length);
                    if (charAt2 != '-' && charAt2 != '_' && charAt2 != ' ') {
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt2 = (char) (charAt2 + ' ');
                        }
                        j = i3 == 0 ? (byte) charAt2 : (j << 8) + charAt2;
                        i3++;
                    }
                }
                if (j != 0) {
                    return j;
                }
            }
        }
        long j2 = MAGIC_HASH_CODE;
        for (int i4 = 0; i4 < str.length(); i4++) {
            char charAt3 = str.charAt(i4);
            if (charAt3 != '-' && charAt3 != '_' && charAt3 != ' ') {
                if (charAt3 >= 'A' && charAt3 <= 'Z') {
                    charAt3 = (char) (charAt3 + ' ');
                }
                j2 = (j2 ^ charAt3) * MAGIC_PRIME;
            }
        }
        return j2;
    }

    public static long hashCode64(String... strArr) {
        if (strArr.length == 1) {
            return hashCode64(strArr[0]);
        }
        long j = MAGIC_HASH_CODE;
        for (String str : strArr) {
            j = (j ^ hashCode64(str)) * MAGIC_PRIME;
        }
        return j;
    }

    public static long hashCode64(String str) {
        if (str.length() <= 8) {
            int i = 0;
            while (true) {
                if (i < str.length()) {
                    char charAt = str.charAt(i);
                    if (charAt > 255 || (i == 0 && charAt == 0)) {
                        break;
                    }
                    i++;
                } else {
                    int length = str.length() - 1;
                    long j = 0;
                    while (length >= 0) {
                        j = length == str.length() + (-1) ? (byte) r7 : (j << 8) + str.charAt(length);
                        length--;
                    }
                    if (j != 0) {
                        return j;
                    }
                }
            }
        }
        long j2 = MAGIC_HASH_CODE;
        for (int i2 = 0; i2 < str.length(); i2++) {
            j2 = (j2 ^ str.charAt(i2)) * MAGIC_PRIME;
        }
        return j2;
    }
}
