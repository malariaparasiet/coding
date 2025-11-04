package com.alibaba.fastjson2.util;

import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.alibaba.fastjson2.reader.ObjectReaderImplDate;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.zone.ZoneRules;
import java.util.Date;
import java.util.Locale;
import kotlin.time.DurationKt;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class DateUtils {
    static DateTimeFormatter DATE_TIME_FORMATTER_34 = null;
    static DateTimeFormatter DATE_TIME_FORMATTER_COOKIE = null;
    static DateTimeFormatter DATE_TIME_FORMATTER_COOKIE_LOCAL = null;
    static DateTimeFormatter DATE_TIME_FORMATTER_RFC_2822 = null;
    public static final ZoneId DEFAULT_ZONE_ID;
    public static final LocalDate LOCAL_DATE_19700101;
    static final int LOCAL_EPOCH_DAY;
    public static final ZoneId OFFSET_8_ZONE_ID;
    public static final String OFFSET_8_ZONE_ID_NAME = "+08:00";
    private static final int[] POWERS;
    public static final ZoneId SHANGHAI_ZONE_ID;
    public static final String SHANGHAI_ZONE_ID_NAME = "Asia/Shanghai";
    public static final ZoneRules SHANGHAI_ZONE_RULES;

    public static int getShanghaiZoneOffsetTotalSeconds(long j) {
        if (j >= 684900000) {
            return 28800;
        }
        if (j >= 671598000) {
            return 32400;
        }
        if (j >= 653450400) {
            return 28800;
        }
        if (j >= 640148400) {
            return 32400;
        }
        if (j >= 622000800) {
            return 28800;
        }
        if (j >= 608698800) {
            return 32400;
        }
        if (j >= 589946400) {
            return 28800;
        }
        if (j >= 577249200) {
            return 32400;
        }
        if (j >= 558496800) {
            return 28800;
        }
        if (j >= 545194800) {
            return 32400;
        }
        if (j >= 527047200) {
            return 28800;
        }
        if (j >= 515559600) {
            return 32400;
        }
        if (j >= -649987200) {
            return 28800;
        }
        if (j >= -652316400) {
            return 32400;
        }
        if (j >= -670636800) {
            return 28800;
        }
        if (j >= -683852400) {
            return 32400;
        }
        if (j >= -699580800) {
            return 28800;
        }
        if (j >= -716857200) {
            return 32400;
        }
        if (j >= -733795200) {
            return 28800;
        }
        if (j >= -745801200) {
            return 32400;
        }
        if (j >= -767836800) {
            return 28800;
        }
        if (j >= -881017200) {
            return 32400;
        }
        if (j >= -888796800) {
            return 28800;
        }
        if (j >= -908838000) {
            return 32400;
        }
        if (j >= -922060800) {
            return 28800;
        }
        if (j >= -933634800) {
            return 32400;
        }
        if (j >= -1585872000) {
            return 28800;
        }
        if (j >= -1600642800) {
            return 32400;
        }
        return j >= -2177452800L ? 28800 : 29143;
    }

    public static int hourAfterNoon(char c, char c2) {
        if (c != '0') {
            if (c == '1') {
                switch (c2) {
                    case '0':
                        c = '2';
                        c2 = '2';
                        break;
                    case '1':
                        c2 = '3';
                        c = '2';
                        break;
                    case '2':
                        c2 = '4';
                        c = '2';
                        break;
                }
            }
        } else {
            switch (c2) {
                case '0':
                    c2 = '2';
                    c = '1';
                    break;
                case '1':
                    c2 = '3';
                    c = '1';
                    break;
                case '2':
                    c2 = '4';
                    c = '1';
                    break;
                case '3':
                    c2 = '5';
                    c = '1';
                    break;
                case '4':
                    c2 = '6';
                    c = '1';
                    break;
                case '5':
                    c2 = '7';
                    c = '1';
                    break;
                case '6':
                    c2 = '8';
                    c = '1';
                    break;
                case '7':
                    c2 = '9';
                    c = '1';
                    break;
                case '8':
                    c = '2';
                    c2 = '0';
                    break;
                case '9':
                    c = '2';
                    c2 = '1';
                    break;
            }
        }
        return (c << 16) | c2;
    }

    public static int month(char c, char c2, char c3) {
        switch ((c << 16) | (c2 << '\b') | c3) {
            case 4288626:
                return 4;
            case 4289895:
                return 8;
            case 4482403:
                return 12;
            case 4613474:
                return 2;
            case 4874606:
                return 1;
            case 4879724:
                return 7;
            case 4879726:
                return 6;
            case 5071218:
                return 3;
            case 5071225:
                return 5;
            case 5140342:
                return 11;
            case 5202804:
                return 10;
            case 5465456:
                return 9;
            default:
                return -1;
        }
    }

    static {
        int shanghaiZoneOffsetTotalSeconds;
        ZoneId systemDefault = ZoneId.systemDefault();
        DEFAULT_ZONE_ID = systemDefault;
        ZoneRules zoneRules = null;
        try {
            if (!SHANGHAI_ZONE_ID_NAME.equals(systemDefault.getId())) {
                systemDefault = ZoneId.of(SHANGHAI_ZONE_ID_NAME);
            }
            try {
                zoneRules = systemDefault.getRules();
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            systemDefault = null;
        }
        SHANGHAI_ZONE_ID = systemDefault;
        SHANGHAI_ZONE_RULES = zoneRules;
        OFFSET_8_ZONE_ID = ZoneId.of(OFFSET_8_ZONE_ID_NAME);
        LOCAL_DATE_19700101 = LocalDate.of(1970, 1, 1);
        long currentTimeMillis = System.currentTimeMillis();
        ZoneId zoneId = DEFAULT_ZONE_ID;
        long floorDiv = Math.floorDiv(currentTimeMillis, 1000L);
        if (zoneId == systemDefault || zoneId.getRules() == zoneRules) {
            shanghaiZoneOffsetTotalSeconds = getShanghaiZoneOffsetTotalSeconds(floorDiv);
        } else {
            shanghaiZoneOffsetTotalSeconds = zoneId.getRules().getOffset(Instant.ofEpochMilli(currentTimeMillis)).getTotalSeconds();
        }
        LOCAL_EPOCH_DAY = (int) Math.floorDiv(floorDiv + shanghaiZoneOffsetTotalSeconds, 86400L);
        POWERS = new int[]{1, 10, 100, 1000, 10000, AndroidComposeViewAccessibilityDelegateCompat.ParcelSafeTextLength, DurationKt.NANOS_IN_MILLIS, 10000000, 100000000, 1000000000, 0, 0, 0, 0, 0, 0};
    }

    static class CacheDate8 {
        static final String[] CACHE = new String[1024];

        CacheDate8() {
        }
    }

    static class CacheDate10 {
        static final String[] CACHE = new String[1024];

        CacheDate10() {
        }
    }

    public static Date parseDateYMDHMS19(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return new Date(parseMillisYMDHMS19(str, DEFAULT_ZONE_ID));
    }

    public static Date parseDate(String str, String str2) {
        return parseDate(str, str2, DEFAULT_ZONE_ID);
    }

    public static Date parseDate(String str, String str2, ZoneId zoneId) {
        if (str == null || str.isEmpty() || "null".equals(str)) {
            return null;
        }
        if (str2 == null || str2.isEmpty()) {
            long parseMillis = parseMillis(str, zoneId);
            if (parseMillis == 0) {
                return null;
            }
            return new Date(parseMillis);
        }
        str2.hashCode();
        switch (str2) {
            case "yyyyMMddHHmmssSSSZ":
                return new Date(parseMillis(str, DEFAULT_ZONE_ID));
            case "yyyyMMdd":
                LocalDate parse = LocalDate.parse(str, DateTimeFormatter.ofPattern(str2));
                return new Date(millis(zoneId, parse.getYear(), parse.getMonthValue(), parse.getDayOfMonth(), 0, 0, 0, 0));
            case "yyyy-MM-dd":
                return new Date(parseMillis10(str, zoneId, DateTimeFormatPattern.DATE_FORMAT_10_DASH));
            case "yyyy/MM/dd":
                return new Date(parseMillis10(str, zoneId, DateTimeFormatPattern.DATE_FORMAT_10_SLASH));
            case "yyyy/MM/dd HH:mm:ss":
                return new Date(parseMillis19(str, zoneId, DateTimeFormatPattern.DATE_TIME_FORMAT_19_SLASH));
            case "yyyy-MM-dd HH:mm:ss":
                return new Date(parseMillisYMDHMS19(str, zoneId));
            case "dd.MM.yyyy HH:mm:ss":
                return new Date(parseMillis19(str, zoneId, DateTimeFormatPattern.DATE_TIME_FORMAT_19_DOT));
            case "yyyy-MM-dd'T'HH:mm:ss":
                return new Date(parseMillis19(str, zoneId, DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH_T));
            case "iso8601":
                return parseDate(str);
            default:
                if (zoneId == null) {
                    zoneId = DEFAULT_ZONE_ID;
                }
                return new Date(millis(LocalDateTime.parse(str, DateTimeFormatter.ofPattern(str2)), zoneId));
        }
    }

    public static Date parseDate(String str) {
        long parseMillis = parseMillis(str, DEFAULT_ZONE_ID);
        if (parseMillis == 0) {
            return null;
        }
        return new Date(parseMillis);
    }

    public static Date parseDate(String str, ZoneId zoneId) {
        long parseMillis = parseMillis(str, zoneId);
        if (parseMillis == 0) {
            return null;
        }
        return new Date(parseMillis);
    }

    public static long parseMillis(String str) {
        return parseMillis(str, DEFAULT_ZONE_ID);
    }

    public static long parseMillis(String str, ZoneId zoneId) {
        if (str == null) {
            return 0L;
        }
        if (JDKUtils.STRING_CODER != null && JDKUtils.STRING_VALUE != null && JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
            byte[] apply = JDKUtils.STRING_VALUE.apply(str);
            return parseMillis(apply, 0, apply.length, StandardCharsets.ISO_8859_1, zoneId);
        }
        char[] charArray = JDKUtils.getCharArray(str);
        return parseMillis(charArray, 0, charArray.length, zoneId);
    }

    public static LocalDateTime parseLocalDateTime(String str) {
        if (str == null) {
            return null;
        }
        return parseLocalDateTime(str, 0, str.length());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a5, code lost:
    
        if (r4.equals("0000-0-00") == false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.LocalDateTime parseLocalDateTime(java.lang.String r4, int r5, int r6) {
        /*
            r0 = 0
            if (r4 == 0) goto Lb3
            if (r6 != 0) goto L7
            goto Lb3
        L7:
            java.util.function.ToIntFunction<java.lang.String> r1 = com.alibaba.fastjson2.util.JDKUtils.STRING_CODER
            r2 = 0
            if (r1 == 0) goto L25
            java.util.function.Function<java.lang.String, byte[]> r1 = com.alibaba.fastjson2.util.JDKUtils.STRING_VALUE
            if (r1 == 0) goto L25
            java.util.function.ToIntFunction<java.lang.String> r1 = com.alibaba.fastjson2.util.JDKUtils.STRING_CODER
            int r1 = r1.applyAsInt(r4)
            if (r1 != 0) goto L25
            java.util.function.Function<java.lang.String, byte[]> r1 = com.alibaba.fastjson2.util.JDKUtils.STRING_VALUE
            java.lang.Object r1 = r1.apply(r4)
            byte[] r1 = (byte[]) r1
            java.time.LocalDateTime r6 = parseLocalDateTime(r1, r5, r6)
            goto L43
        L25:
            int r1 = com.alibaba.fastjson2.util.JDKUtils.JVM_VERSION
            r3 = 8
            if (r1 != r3) goto L38
            boolean r1 = com.alibaba.fastjson2.util.JDKUtils.FIELD_STRING_VALUE_ERROR
            if (r1 != 0) goto L38
            char[] r1 = com.alibaba.fastjson2.util.JDKUtils.getCharArray(r4)
            java.time.LocalDateTime r6 = parseLocalDateTime(r1, r5, r6)
            goto L43
        L38:
            char[] r1 = new char[r6]
            int r3 = r5 + r6
            r4.getChars(r5, r3, r1, r2)
            java.time.LocalDateTime r6 = parseLocalDateTime(r1, r5, r6)
        L43:
            if (r6 != 0) goto Lb2
            r4.hashCode()
            int r6 = r4.hashCode()
            r1 = -1
            switch(r6) {
                case -2035181974: goto L9f;
                case -2035179184: goto L94;
                case -1328438272: goto L89;
                case -1173940224: goto L7e;
                case 0: goto L73;
                case 3392903: goto L68;
                case 86814033: goto L5d;
                case 1333954784: goto L52;
                default: goto L50;
            }
        L50:
            r2 = r1
            goto La8
        L52:
            java.lang.String r6 = "0000-00-00"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L5b
            goto L50
        L5b:
            r2 = 7
            goto La8
        L5d:
            java.lang.String r6 = "0000年00月00日"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L66
            goto L50
        L66:
            r2 = 6
            goto La8
        L68:
            java.lang.String r6 = "null"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L71
            goto L50
        L71:
            r2 = 5
            goto La8
        L73:
            java.lang.String r6 = ""
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L7c
            goto L50
        L7c:
            r2 = 4
            goto La8
        L7e:
            java.lang.String r6 = "00000000"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L87
            goto L50
        L87:
            r2 = 3
            goto La8
        L89:
            java.lang.String r6 = "000000000000"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L92
            goto L50
        L92:
            r2 = 2
            goto La8
        L94:
            java.lang.String r6 = "0000-00-0"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L9d
            goto L50
        L9d:
            r2 = 1
            goto La8
        L9f:
            java.lang.String r6 = "0000-0-00"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto La8
            goto L50
        La8:
            switch(r2) {
                case 0: goto Lb1;
                case 1: goto Lb1;
                case 2: goto Lb1;
                case 3: goto Lb1;
                case 4: goto Lb1;
                case 5: goto Lb1;
                case 6: goto Lb1;
                case 7: goto Lb1;
                default: goto Lab;
            }
        Lab:
            java.time.format.DateTimeParseException r6 = new java.time.format.DateTimeParseException
            r6.<init>(r4, r4, r5)
            throw r6
        Lb1:
            return r0
        Lb2:
            return r6
        Lb3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseLocalDateTime(java.lang.String, int, int):java.time.LocalDateTime");
    }

    public static LocalDateTime parseLocalDateTime(char[] cArr, int i, int i2) {
        if (cArr == null || i2 == 0) {
            return null;
        }
        switch (i2) {
            case 4:
                if (cArr[i] == 'n' && cArr[i + 1] == 'u' && cArr[i + 2] == 'l' && cArr[i + 3] == 'l') {
                    return null;
                }
                String str = new String(cArr, i, i2);
                throw new DateTimeParseException("illegal input ".concat(str), str, 0);
            case 5:
            case 6:
            case 7:
            case 13:
            case 15:
            default:
                return parseLocalDateTimeX(cArr, i, i2);
            case 8:
                if (cArr[2] == ':' && cArr[5] == ':') {
                    return LocalDateTime.of(LOCAL_DATE_19700101, parseLocalTime8(cArr, i));
                }
                LocalDate parseLocalDate8 = parseLocalDate8(cArr, i);
                if (parseLocalDate8 == null) {
                    return null;
                }
                return LocalDateTime.of(parseLocalDate8, LocalTime.MIN);
            case 9:
                LocalDate parseLocalDate9 = parseLocalDate9(cArr, i);
                if (parseLocalDate9 == null) {
                    return null;
                }
                return LocalDateTime.of(parseLocalDate9, LocalTime.MIN);
            case 10:
                LocalDate parseLocalDate10 = parseLocalDate10(cArr, i);
                if (parseLocalDate10 == null) {
                    return null;
                }
                return LocalDateTime.of(parseLocalDate10, LocalTime.MIN);
            case 11:
                LocalDate parseLocalDate11 = parseLocalDate11(cArr, i);
                if (parseLocalDate11 == null) {
                    return null;
                }
                return LocalDateTime.of(parseLocalDate11, LocalTime.MIN);
            case 12:
                return parseLocalDateTime12(cArr, i);
            case 14:
                return parseLocalDateTime14(cArr, i);
            case 16:
                return parseLocalDateTime16(cArr, i);
            case 17:
                return parseLocalDateTime17(cArr, i);
            case 18:
                return parseLocalDateTime18(cArr, i);
            case 19:
                return parseLocalDateTime19(cArr, i);
            case 20:
                return parseLocalDateTime20(cArr, i);
        }
    }

    public static LocalTime parseLocalTime5(byte[] bArr, int i) {
        int digit1;
        int i2;
        int i3;
        if (i + 5 > bArr.length) {
            return null;
        }
        int i4 = i + 2;
        if (bArr[i4] == 58) {
            i3 = IOUtils.digit2(bArr, i);
            i2 = IOUtils.digit2(bArr, i + 3);
            digit1 = 0;
        } else {
            if (bArr[i + 1] != 58 || bArr[i + 3] != 58) {
                return null;
            }
            int digit12 = IOUtils.digit1(bArr, i);
            int digit13 = IOUtils.digit1(bArr, i4);
            digit1 = IOUtils.digit1(bArr, i + 4);
            i2 = digit13;
            i3 = digit12;
        }
        return localTime(i3, i2, digit1);
    }

    public static LocalTime parseLocalTime5(char[] cArr, int i) {
        int digit1;
        int i2;
        int i3;
        if (i + 5 > cArr.length) {
            return null;
        }
        int i4 = i + 2;
        if (cArr[i4] == ':') {
            i3 = IOUtils.digit2(cArr, i);
            i2 = IOUtils.digit2(cArr, i + 3);
            digit1 = 0;
        } else {
            if (cArr[i + 1] != ':' || cArr[i + 3] != ':') {
                return null;
            }
            int digit12 = IOUtils.digit1(cArr, i);
            int digit13 = IOUtils.digit1(cArr, i4);
            digit1 = IOUtils.digit1(cArr, i + 4);
            i2 = digit13;
            i3 = digit12;
        }
        return localTime(i3, i2, digit1);
    }

    public static LocalTime parseLocalTime6(byte[] bArr, int i) {
        int digit1;
        int digit12;
        int digit2;
        int i2 = i + 5;
        if (i2 > bArr.length) {
            return null;
        }
        byte b = bArr[i + 1];
        int i3 = i + 4;
        byte b2 = bArr[i3];
        int i4 = i + 2;
        if (bArr[i4] == 58 && b2 == 58) {
            digit1 = IOUtils.digit2(bArr, i);
            digit12 = IOUtils.digit1(bArr, i + 3);
            digit2 = IOUtils.digit1(bArr, i2);
        } else if (b == 58 && b2 == 58) {
            digit1 = IOUtils.digit1(bArr, i);
            digit12 = IOUtils.digit2(bArr, i4);
            digit2 = IOUtils.digit1(bArr, i2);
        } else {
            if (b != 58 || bArr[i + 3] != 58) {
                return null;
            }
            digit1 = IOUtils.digit1(bArr, i);
            digit12 = IOUtils.digit1(bArr, i4);
            digit2 = IOUtils.digit2(bArr, i3);
        }
        return localTime(digit1, digit12, digit2);
    }

    public static LocalTime parseLocalTime6(char[] cArr, int i) {
        int digit1;
        int digit12;
        int digit2;
        int i2 = i + 5;
        if (i2 > cArr.length) {
            return null;
        }
        char c = cArr[i + 1];
        int i3 = i + 4;
        char c2 = cArr[i3];
        int i4 = i + 2;
        if (cArr[i4] == ':' && c2 == ':') {
            digit1 = IOUtils.digit2(cArr, i);
            digit12 = IOUtils.digit1(cArr, i + 3);
            digit2 = IOUtils.digit1(cArr, i2);
        } else if (c == ':' && c2 == ':') {
            digit1 = IOUtils.digit1(cArr, i);
            digit12 = IOUtils.digit2(cArr, i4);
            digit2 = IOUtils.digit1(cArr, i2);
        } else {
            if (c != ':' || cArr[i + 3] != ':') {
                return null;
            }
            digit1 = IOUtils.digit1(cArr, i);
            digit12 = IOUtils.digit1(cArr, i4);
            digit2 = IOUtils.digit2(cArr, i3);
        }
        return localTime(digit1, digit12, digit2);
    }

    public static LocalTime parseLocalTime7(byte[] bArr, int i) {
        int digit2;
        int digit1;
        int i2;
        int i3 = i + 5;
        if (i3 > bArr.length) {
            return null;
        }
        int i4 = i + 2;
        byte b = bArr[i4];
        byte b2 = bArr[i + 4];
        if (bArr[i + 1] == 58 && b2 == 58) {
            i2 = IOUtils.digit1(bArr, i);
            digit2 = IOUtils.digit2(bArr, i4);
            digit1 = IOUtils.digit2(bArr, i3);
        } else if (b == 58 && b2 == 58) {
            int digit22 = IOUtils.digit2(bArr, i);
            int digit12 = IOUtils.digit1(bArr, i + 3);
            digit1 = IOUtils.digit2(bArr, i3);
            digit2 = digit12;
            i2 = digit22;
        } else {
            if (b != 58 || bArr[i3] != 58) {
                return null;
            }
            int digit23 = IOUtils.digit2(bArr, i);
            digit2 = IOUtils.digit2(bArr, i + 3);
            digit1 = IOUtils.digit1(bArr, i + 6);
            i2 = digit23;
        }
        return localTime(i2, digit2, digit1);
    }

    public static LocalTime parseLocalTime7(char[] cArr, int i) {
        int digit2;
        int digit1;
        int i2;
        int i3 = i + 5;
        if (i3 > cArr.length) {
            return null;
        }
        int i4 = i + 2;
        char c = cArr[i4];
        char c2 = cArr[i + 4];
        if (cArr[i + 1] == ':' && c2 == ':') {
            i2 = IOUtils.digit1(cArr, i);
            digit2 = IOUtils.digit2(cArr, i4);
            digit1 = IOUtils.digit2(cArr, i3);
        } else if (c == ':' && c2 == ':') {
            int digit22 = IOUtils.digit2(cArr, i);
            int digit12 = IOUtils.digit1(cArr, i + 3);
            digit1 = IOUtils.digit2(cArr, i3);
            digit2 = digit12;
            i2 = digit22;
        } else {
            if (c != ':' || cArr[i3] != ':') {
                return null;
            }
            int digit23 = IOUtils.digit2(cArr, i);
            digit2 = IOUtils.digit2(cArr, i + 3);
            digit1 = IOUtils.digit1(cArr, i + 6);
            i2 = digit23;
        }
        return localTime(i2, digit2, digit1);
    }

    public static LocalTime parseLocalTime8(byte[] bArr, int i) {
        if (i + 8 > bArr.length) {
            return null;
        }
        long hms = hms(bArr, i);
        if (hms == -1) {
            return null;
        }
        return LocalTime.of(((int) hms) & 255, ((int) (hms >> 24)) & 255, ((int) (hms >> 48)) & 255);
    }

    public static LocalTime parseLocalTime8(char[] cArr, int i) {
        if (i + 8 <= cArr.length && cArr[i + 2] == ':' && cArr[i + 5] == ':') {
            return localTime(IOUtils.digit2(cArr, i), IOUtils.digit2(cArr, i + 3), IOUtils.digit2(cArr, i + 6));
        }
        return null;
    }

    public static LocalTime parseLocalTime(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        if (c3 == ':' && c6 == ':' && c >= '0' && c <= '9' && c2 >= '0' && c2 <= '9') {
            int i = ((c - '0') * 10) + (c2 - '0');
            if (c4 >= '0' && c4 <= '9' && c5 >= '0' && c5 <= '9') {
                int i2 = ((c4 - '0') * 10) + (c5 - '0');
                if (c7 >= '0' && c7 <= '9' && c8 >= '0' && c8 <= '9') {
                    return LocalTime.of(i, i2, ((c7 - '0') * 10) + (c8 - '0'));
                }
            }
        }
        return null;
    }

    public static LocalTime parseLocalTime10(byte[] bArr, int i) {
        if (i + 10 > bArr.length || bArr[i + 2] != 58 || bArr[i + 5] != 58 || bArr[i + 8] != 46) {
            return null;
        }
        int digit2 = IOUtils.digit2(bArr, i);
        int digit22 = IOUtils.digit2(bArr, i + 3);
        int digit23 = IOUtils.digit2(bArr, i + 6);
        int digit1 = IOUtils.digit1(bArr, i + 9);
        if (digit1 > 0) {
            digit1 *= 100000000;
        }
        if ((digit2 | digit22 | digit23 | digit22) < 0) {
            return null;
        }
        return LocalTime.of(digit2, digit22, digit23, digit1);
    }

    public static LocalTime parseLocalTime10(char[] cArr, int i) {
        if (i + 10 > cArr.length || cArr[i + 2] != ':' || cArr[i + 5] != ':' || cArr[i + 8] != '.') {
            return null;
        }
        int digit2 = IOUtils.digit2(cArr, i);
        int digit22 = IOUtils.digit2(cArr, i + 3);
        int digit23 = IOUtils.digit2(cArr, i + 6);
        int digit1 = IOUtils.digit1(cArr, i + 9);
        if (digit1 > 0) {
            digit1 *= 100000000;
        }
        if ((digit2 | digit22 | digit23 | digit22) < 0) {
            return null;
        }
        return LocalTime.of(digit2, digit22, digit23, digit1);
    }

    public static LocalTime parseLocalTime11(byte[] bArr, int i) {
        if (i + 11 > bArr.length) {
            return null;
        }
        long hms = hms(bArr, i);
        if (hms == -1 || bArr[i + 8] != 46) {
            return null;
        }
        int i2 = ((int) hms) & 255;
        int i3 = ((int) (hms >> 24)) & 255;
        int i4 = ((int) (hms >> 48)) & 255;
        int digit2 = IOUtils.digit2(bArr, i + 9);
        if (digit2 > 0) {
            digit2 *= 10000000;
        }
        return LocalTime.of(i2, i3, i4, digit2);
    }

    public static LocalTime parseLocalTime11(char[] cArr, int i) {
        if (i + 11 > cArr.length || cArr[i + 2] != ':' || cArr[i + 5] != ':' || cArr[i + 8] != '.') {
            return null;
        }
        int digit2 = IOUtils.digit2(cArr, i);
        int digit22 = IOUtils.digit2(cArr, i + 3);
        int digit23 = IOUtils.digit2(cArr, i + 6);
        int digit24 = IOUtils.digit2(cArr, i + 9);
        if (digit24 > 0) {
            digit24 *= 10000000;
        }
        if ((digit2 | digit22 | digit23 | digit22) < 0) {
            return null;
        }
        return LocalTime.of(digit2, digit22, digit23, digit24);
    }

    public static LocalTime parseLocalTime12(byte[] bArr, int i) {
        if (i + 12 > bArr.length) {
            return null;
        }
        long hms = hms(bArr, i);
        if (hms == -1 || bArr[i + 8] != 46) {
            return null;
        }
        int i2 = ((int) hms) & 255;
        int i3 = ((int) (hms >> 24)) & 255;
        int i4 = ((int) (hms >> 48)) & 255;
        int digit3 = IOUtils.digit3(bArr, i + 9);
        if (digit3 > 0) {
            digit3 *= DurationKt.NANOS_IN_MILLIS;
        }
        return LocalTime.of(i2, i3, i4, digit3);
    }

    public static LocalTime parseLocalTime12(char[] cArr, int i) {
        if (i + 12 > cArr.length || cArr[i + 2] != ':' || cArr[i + 5] != ':' || cArr[i + 8] != '.') {
            return null;
        }
        int digit2 = IOUtils.digit2(cArr, i);
        int digit22 = IOUtils.digit2(cArr, i + 3);
        int digit23 = IOUtils.digit2(cArr, i + 6);
        int digit3 = IOUtils.digit3(cArr, i + 9);
        if (digit3 > 0) {
            digit3 *= DurationKt.NANOS_IN_MILLIS;
        }
        if ((digit2 | digit22 | digit23 | digit22) < 0) {
            return null;
        }
        return LocalTime.of(digit2, digit22, digit23, digit3);
    }

    public static LocalTime parseLocalTime15(byte[] bArr, int i) {
        if (i + 15 <= bArr.length) {
            long hms = hms(bArr, i);
            if (hms != -1 && bArr[i + 8] == 46) {
                int i2 = ((int) hms) & 255;
                int i3 = ((int) (hms >> 24)) & 255;
                int i4 = ((int) (hms >> 48)) & 255;
                int readNanos = readNanos(bArr, 6, i + 9);
                if (readNanos < 0) {
                    return null;
                }
                return LocalTime.of(i2, i3, i4, readNanos);
            }
        }
        return null;
    }

    public static LocalTime parseLocalTime15(char[] cArr, int i) {
        if (i + 15 > cArr.length || cArr[i + 2] != ':' || cArr[i + 5] != ':' || cArr[i + 8] != '.') {
            return null;
        }
        int digit2 = IOUtils.digit2(cArr, i);
        int digit22 = IOUtils.digit2(cArr, i + 3);
        int digit23 = IOUtils.digit2(cArr, i + 6);
        int readNanos = readNanos(cArr, 6, i + 9);
        if ((digit2 | digit22 | digit23 | readNanos) < 0) {
            return null;
        }
        return LocalTime.of(digit2, digit22, digit23, readNanos);
    }

    public static LocalTime parseLocalTime18(byte[] bArr, int i) {
        if (i + 18 <= bArr.length) {
            long hms = hms(bArr, i);
            if (hms != -1 && bArr[i + 8] == 46) {
                int i2 = ((int) hms) & 255;
                int i3 = ((int) (hms >> 24)) & 255;
                int i4 = ((int) (hms >> 48)) & 255;
                int readNanos = readNanos(bArr, 9, i + 9);
                if (readNanos < 0) {
                    return null;
                }
                return LocalTime.of(i2, i3, i4, readNanos);
            }
        }
        return null;
    }

    public static LocalTime parseLocalTime18(char[] cArr, int i) {
        if (i + 18 > cArr.length || cArr[i + 2] != ':' || cArr[i + 5] != ':' || cArr[i + 8] != '.') {
            return null;
        }
        int digit2 = IOUtils.digit2(cArr, i);
        int digit22 = IOUtils.digit2(cArr, i + 3);
        int digit23 = IOUtils.digit2(cArr, i + 6);
        int readNanos = readNanos(cArr, 9, i + 9);
        if ((digit2 | digit22 | digit23 | readNanos) < 0) {
            return null;
        }
        return LocalTime.of(digit2, digit22, digit23, readNanos);
    }

    private static LocalTime localTime(int i, int i2, int i3) {
        if ((i | i2 | i3) < 0) {
            return null;
        }
        return LocalTime.of(i, i2, i3);
    }

    public static LocalDateTime parseLocalDateTime(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 == 0) {
            return null;
        }
        switch (i2) {
            case 4:
                if (bArr[i] == 110 && bArr[i + 1] == 117 && bArr[i + 2] == 108 && bArr[i + 3] == 108) {
                    return null;
                }
                String str = new String(bArr, i, i2);
                throw new DateTimeParseException("illegal input ".concat(str), str, 0);
            case 5:
            case 6:
            case 7:
            case 13:
            case 15:
            default:
                return parseLocalDateTimeX(bArr, i, i2);
            case 8:
                LocalDate parseLocalDate8 = parseLocalDate8(bArr, i);
                if (parseLocalDate8 == null) {
                    return null;
                }
                return LocalDateTime.of(parseLocalDate8, LocalTime.MIN);
            case 9:
                LocalDate parseLocalDate9 = parseLocalDate9(bArr, i);
                if (parseLocalDate9 == null) {
                    return null;
                }
                return LocalDateTime.of(parseLocalDate9, LocalTime.MIN);
            case 10:
                LocalDate parseLocalDate10 = parseLocalDate10(bArr, i);
                if (parseLocalDate10 == null) {
                    return null;
                }
                return LocalDateTime.of(parseLocalDate10, LocalTime.MIN);
            case 11:
                return LocalDateTime.of(parseLocalDate11(bArr, i), LocalTime.MIN);
            case 12:
                return parseLocalDateTime12(bArr, i);
            case 14:
                return parseLocalDateTime14(bArr, i);
            case 16:
                return parseLocalDateTime16(bArr, i);
            case 17:
                return parseLocalDateTime17(bArr, i);
            case 18:
                return parseLocalDateTime18(bArr, i);
            case 19:
                return parseLocalDateTime19(bArr, i);
            case 20:
                return parseLocalDateTime20(bArr, i);
        }
    }

    public static LocalDate parseLocalDate(String str) {
        LocalDate parseLocalDate;
        if (str == null) {
            return null;
        }
        if (JDKUtils.STRING_CODER != null && JDKUtils.STRING_VALUE != null && JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
            byte[] apply = JDKUtils.STRING_VALUE.apply(str);
            parseLocalDate = parseLocalDate(apply, 0, apply.length);
        } else {
            char[] charArray = JDKUtils.getCharArray(str);
            parseLocalDate = parseLocalDate(charArray, 0, charArray.length);
        }
        if (parseLocalDate != null) {
            return parseLocalDate;
        }
        str.hashCode();
        switch (str) {
            case "0000-0-00":
            case "00000000":
            case "":
            case "null":
            case "0000年00月00日":
            case "0000-00-00":
                return null;
            default:
                throw new DateTimeParseException(str, str, 0);
        }
    }

    public static LocalDate parseLocalDate(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 == 0) {
            return null;
        }
        if (i + i2 > bArr.length) {
            String str = new String(bArr, i, i2);
            throw new DateTimeParseException("illegal input ".concat(str), str, 0);
        }
        switch (i2) {
            case 8:
                return parseLocalDate8(bArr, i);
            case 9:
                return parseLocalDate9(bArr, i);
            case 10:
                return parseLocalDate10(bArr, i);
            case 11:
                return parseLocalDate11(bArr, i);
            default:
                if (i2 == 4 && bArr[i] == 110 && bArr[i + 1] == 117 && bArr[i + 2] == 108 && bArr[i + 3] == 108) {
                    return null;
                }
                String str2 = new String(bArr, i, i2);
                throw new DateTimeParseException("illegal input ".concat(str2), str2, 0);
        }
    }

    public static LocalDate parseLocalDate(char[] cArr, int i, int i2) {
        if (cArr == null || i2 == 0) {
            return null;
        }
        if (i + i2 > cArr.length) {
            String str = new String(cArr, i, i2);
            throw new DateTimeParseException("illegal input ".concat(str), str, 0);
        }
        switch (i2) {
            case 8:
                return parseLocalDate8(cArr, i);
            case 9:
                return parseLocalDate9(cArr, i);
            case 10:
                return parseLocalDate10(cArr, i);
            case 11:
                return parseLocalDate11(cArr, i);
            default:
                if (i2 == 4 && cArr[i] == 'n' && cArr[i + 1] == 'u' && cArr[i + 2] == 'l' && cArr[i + 3] == 'l') {
                    return null;
                }
                String str2 = new String(cArr, i, i2);
                throw new DateTimeParseException("illegal input ".concat(str2), str2, 0);
        }
    }

    public static long parseMillis(byte[] bArr, int i, int i2) {
        return parseMillis(bArr, i, i2, StandardCharsets.UTF_8, DEFAULT_ZONE_ID);
    }

    public static long parseMillis(byte[] bArr, int i, int i2, Charset charset) {
        return parseMillis(bArr, i, i2, charset, DEFAULT_ZONE_ID);
    }

    public static long parseMillis(byte[] bArr, int i, int i2, Charset charset, ZoneId zoneId) {
        int i3;
        char c;
        ZoneId zoneId2 = zoneId;
        if (bArr == null || i2 == 0) {
            return 0L;
        }
        if (i2 == 4 && IOUtils.isNULL(bArr, i)) {
            return 0L;
        }
        char c2 = (char) bArr[i];
        if (c2 != '\"' || bArr[i2 - 1] != 34) {
            if (i2 == 19) {
                return parseMillis19(bArr, i, zoneId2);
            }
            if (i2 > 19 || (i2 == 16 && ((c = (char) bArr[i + 10]) == '+' || c == '-'))) {
                ZonedDateTime parseZonedDateTime = parseZonedDateTime(bArr, i, i2, zoneId2);
                if (parseZonedDateTime == null) {
                    String str = new String(bArr, i, i2 - i);
                    throw new DateTimeParseException("illegal input ".concat(str), str, 0);
                }
                return parseZonedDateTime.toInstant().toEpochMilli();
            }
            if ((c2 == '-' || (c2 >= '0' && c2 <= '9')) && IOUtils.isNumber(bArr, i, i2)) {
                long parseLong = TypeUtils.parseLong(bArr, i, i2);
                if (i2 != 8 || parseLong < 19700101 || parseLong > 21000101) {
                    return parseLong;
                }
                int i4 = (int) parseLong;
                int i5 = i4 / 10000;
                int i6 = (i4 % 10000) / 100;
                int i7 = i4 % 100;
                if (i6 < 1 || i6 > 12) {
                    return parseLong;
                }
                if (i6 != 2) {
                    i3 = (i6 == 4 || i6 == 6 || i6 == 9 || i6 == 11) ? 30 : 31;
                } else {
                    i3 = ((i5 & 3) != 0 || (i5 % 100 == 0 && i5 % 400 != 0)) ? 28 : 29;
                }
                return i7 <= i3 ? ZonedDateTime.ofLocal(LocalDateTime.of(i5, i6, i7, 0, 0, 0), zoneId2, null).toEpochSecond() * 1000 : parseLong;
            }
            if (((char) bArr[i2 - 1]) == 'Z') {
                zoneId2 = ZoneOffset.UTC;
            }
            LocalDateTime parseLocalDateTime = parseLocalDateTime(bArr, i, i2);
            if (parseLocalDateTime == null && IOUtils.getLongLE(bArr, i) == 3256155501228994608L && IOUtils.getShortLE(bArr, i + 8) == 12336) {
                parseLocalDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
            }
            long epochSecond = ZonedDateTime.ofLocal(parseLocalDateTime, zoneId2, null).toEpochSecond();
            int nano = parseLocalDateTime.getNano();
            if (epochSecond < 0 && nano > 0) {
                return (((epochSecond + 1) * 1000) + (nano / DurationKt.NANOS_IN_MILLIS)) - 1000;
            }
            return (epochSecond * 1000) + (nano / DurationKt.NANOS_IN_MILLIS);
        }
        JSONReader of = JSONReader.of(bArr, i, i2, charset);
        try {
            long time = ((Date) ObjectReaderImplDate.INSTANCE.readObject(of, null, null, 0L)).getTime();
            if (of != null) {
                of.close();
            }
            return time;
        } finally {
        }
    }

    public static long parseMillis(char[] cArr, int i, int i2) {
        return parseMillis(cArr, i, i2, DEFAULT_ZONE_ID);
    }

    public static long parseMillis(char[] cArr, int i, int i2, ZoneId zoneId) {
        int i3;
        char c;
        int i4 = i2;
        ZoneId zoneId2 = zoneId;
        if (cArr == null || i4 == 0) {
            return 0L;
        }
        if (i4 == 4 && IOUtils.isNULL(cArr, i)) {
            return 0L;
        }
        char c2 = cArr[i];
        if (c2 != '\"' || cArr[i4 - 1] != '\"') {
            if (i4 == 19) {
                return parseMillis19(cArr, i, zoneId2);
            }
            if (i4 > 19 || (i4 == 16 && ((c = cArr[i + 10]) == '+' || c == '-'))) {
                ZonedDateTime parseZonedDateTime = parseZonedDateTime(cArr, i, i2, zoneId);
                if (parseZonedDateTime == null) {
                    String str = new String(cArr, i, i4 - i);
                    throw new DateTimeParseException("illegal input ".concat(str), str, 0);
                }
                return parseZonedDateTime.toInstant().toEpochMilli();
            }
            if ((c2 == '-' || (c2 >= '0' && c2 <= '9')) && IOUtils.isNumber(cArr, i, i2)) {
                long parseLong = TypeUtils.parseLong(cArr, i, i2);
                if (i4 != 8 || parseLong < 19700101 || parseLong > 21000101) {
                    return parseLong;
                }
                int i5 = (int) parseLong;
                int i6 = i5 / 10000;
                int i7 = (i5 % 10000) / 100;
                int i8 = i5 % 100;
                if (i7 < 1 || i7 > 12) {
                    return parseLong;
                }
                if (i7 != 2) {
                    i3 = (i7 == 4 || i7 == 6 || i7 == 9 || i7 == 11) ? 30 : 31;
                } else {
                    i3 = ((i6 & 3) != 0 || (i6 % 100 == 0 && i6 % 400 != 0)) ? 28 : 29;
                }
                return i8 <= i3 ? ZonedDateTime.ofLocal(LocalDateTime.of(i6, i7, i8, 0, 0, 0), zoneId2, null).toEpochSecond() * 1000 : parseLong;
            }
            if (cArr[i4 - 1] == 'Z') {
                i4--;
                zoneId2 = ZoneOffset.UTC;
            }
            LocalDateTime parseLocalDateTime = parseLocalDateTime(cArr, i, i4);
            if (parseLocalDateTime == null && IOUtils.getLongLE(cArr, i) == 13511005043687472L && IOUtils.getLongLE(cArr, i + 4) == 12666580113555501L && IOUtils.getIntLE(cArr, i + 8) == 3145776) {
                parseLocalDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
            }
            if (parseLocalDateTime == null) {
                String str2 = new String(cArr, i, i4 - i);
                throw new DateTimeParseException("illegal input ".concat(str2), str2, 0);
            }
            long epochSecond = ZonedDateTime.ofLocal(parseLocalDateTime, zoneId2, null).toEpochSecond();
            int nano = parseLocalDateTime.getNano();
            if (epochSecond < 0 && nano > 0) {
                return (((epochSecond + 1) * 1000) + (nano / DurationKt.NANOS_IN_MILLIS)) - 1000;
            }
            return (epochSecond * 1000) + (nano / DurationKt.NANOS_IN_MILLIS);
        }
        JSONReader of = JSONReader.of(cArr, i, i2);
        try {
            long time = ((Date) ObjectReaderImplDate.INSTANCE.readObject(of, null, null, 0L)).getTime();
            if (of != null) {
                of.close();
            }
            return time;
        } finally {
        }
    }

    public static LocalDate parseLocalDate8(byte[] bArr, int i) {
        int digit4;
        int digit2;
        int digit22;
        if (i + 8 > bArr.length) {
            return null;
        }
        char c = (char) bArr[i + 1];
        char c2 = (char) bArr[i + 3];
        int i2 = i + 4;
        char c3 = (char) bArr[i2];
        if (c3 == '-' && bArr[i + 6] == 45) {
            digit4 = IOUtils.digit4(bArr, i);
            digit2 = IOUtils.digit1(bArr, i + 5);
            digit22 = IOUtils.digit1(bArr, i + 7);
        } else if (c == '/' && c2 == '/') {
            digit2 = IOUtils.digit1(bArr, i);
            int digit1 = IOUtils.digit1(bArr, i + 2);
            digit4 = IOUtils.digit4(bArr, i2);
            digit22 = digit1;
        } else if (c == '-' && bArr[i + 5] == 45) {
            int digit12 = IOUtils.digit1(bArr, i);
            digit2 = month((char) bArr[i + 2], c2, c3);
            int digit23 = IOUtils.digit2(bArr, i + 6);
            if (digit23 != -1) {
                digit23 += 2000;
            }
            digit4 = digit23;
            digit22 = digit12;
        } else {
            digit4 = IOUtils.digit4(bArr, i);
            digit2 = IOUtils.digit2(bArr, i2);
            digit22 = IOUtils.digit2(bArr, i + 6);
        }
        if ((digit4 | digit2 | digit22) <= 0) {
            return null;
        }
        return LocalDate.of(digit4, digit2, digit22);
    }

    public static LocalDate parseLocalDate8(char[] cArr, int i) {
        int digit4;
        int digit2;
        int digit22;
        if (i + 8 > cArr.length) {
            return null;
        }
        char c = cArr[i + 1];
        char c2 = cArr[i + 3];
        int i2 = i + 4;
        char c3 = cArr[i2];
        if (c3 == '-' && cArr[i + 6] == '-') {
            digit4 = IOUtils.digit4(cArr, i);
            digit2 = IOUtils.digit1(cArr, i + 5);
            digit22 = IOUtils.digit1(cArr, i + 7);
        } else if (c == '/' && c2 == '/') {
            digit2 = IOUtils.digit1(cArr, i);
            int digit1 = IOUtils.digit1(cArr, i + 2);
            digit4 = IOUtils.digit4(cArr, i2);
            digit22 = digit1;
        } else if (c == '-' && cArr[i + 5] == '-') {
            int digit12 = IOUtils.digit1(cArr, i);
            digit2 = month(cArr[i + 2], c2, c3);
            int digit23 = IOUtils.digit2(cArr, i + 6);
            if (digit23 != -1) {
                digit23 += 2000;
            }
            digit4 = digit23;
            digit22 = digit12;
        } else {
            digit4 = IOUtils.digit4(cArr, i);
            digit2 = IOUtils.digit2(cArr, i2);
            digit22 = IOUtils.digit2(cArr, i + 6);
        }
        if ((digit4 | digit2 | digit22) <= 0) {
            return null;
        }
        return LocalDate.of(digit4, digit2, digit22);
    }

    public static LocalDate parseLocalDate9(byte[] bArr, int i) {
        int digit2;
        int digit1;
        int digit4;
        int digit42;
        int i2;
        if (i + 9 > bArr.length) {
            return null;
        }
        char c = (char) bArr[i + 1];
        int i3 = i + 2;
        char c2 = (char) bArr[i3];
        char c3 = (char) bArr[i + 4];
        char c4 = (char) bArr[i + 6];
        int i4 = i + 7;
        char c5 = (char) bArr[i4];
        if ((c3 == '-' && c5 == '-') || (c3 == '/' && c5 == '/')) {
            digit42 = IOUtils.digit4(bArr, i);
            digit2 = IOUtils.digit2(bArr, i + 5);
            i2 = IOUtils.digit1(bArr, i + 8);
        } else if ((c3 == '-' && c4 == '-') || (c3 == '/' && c4 == '/')) {
            digit42 = IOUtils.digit4(bArr, i);
            digit2 = IOUtils.digit1(bArr, i + 5);
            i2 = IOUtils.digit2(bArr, i4);
        } else {
            if (c == '.' && c3 == '.') {
                digit1 = IOUtils.digit1(bArr, i);
                digit2 = IOUtils.digit2(bArr, i3);
                digit4 = IOUtils.digit4(bArr, i + 5);
            } else if ((c2 == '.' && c3 == '.') || (c2 == '-' && c3 == '-')) {
                digit1 = IOUtils.digit2(bArr, i);
                digit2 = IOUtils.digit1(bArr, i + 3);
                digit4 = IOUtils.digit4(bArr, i + 5);
            } else if (c == '-' && c3 == '-') {
                digit1 = IOUtils.digit1(bArr, i);
                digit2 = IOUtils.digit2(bArr, i3);
                digit4 = IOUtils.digit4(bArr, i + 5);
            } else if (c2 == '-' && c4 == '-') {
                digit1 = IOUtils.digit2(bArr, i);
                digit2 = month((char) bArr[i + 3], c3, (char) bArr[i + 5]);
                digit4 = IOUtils.digit2(bArr, i4);
                if (digit4 != -1) {
                    digit4 += 2000;
                }
            } else if (c == '/' && c3 == '/') {
                int digit12 = IOUtils.digit1(bArr, i);
                int digit22 = IOUtils.digit2(bArr, i3);
                digit42 = IOUtils.digit4(bArr, i + 5);
                i2 = digit22;
                digit2 = digit12;
            } else {
                if (c2 != '/' || c3 != '/') {
                    return null;
                }
                digit2 = IOUtils.digit2(bArr, i);
                digit1 = IOUtils.digit1(bArr, i + 3);
                digit4 = IOUtils.digit4(bArr, i + 5);
            }
            int i5 = digit1;
            digit42 = digit4;
            i2 = i5;
        }
        if ((digit42 | digit2 | i2) <= 0) {
            return null;
        }
        return LocalDate.of(digit42, digit2, i2);
    }

    public static LocalDate parseLocalDate9(char[] cArr, int i) {
        int digit2;
        int digit1;
        int digit4;
        int i2;
        int i3;
        int i4;
        int digit12;
        int digit22;
        int digit42;
        if (i + 9 > cArr.length) {
            return null;
        }
        char c = cArr[i + 1];
        int i5 = i + 2;
        char c2 = cArr[i5];
        char c3 = cArr[i + 4];
        char c4 = cArr[i + 6];
        int i6 = i + 7;
        char c5 = cArr[i6];
        int i7 = i + 8;
        char c6 = cArr[i7];
        if ((c3 == '-' && c5 == '-') || (c3 == '/' && c5 == '/')) {
            i3 = IOUtils.digit4(cArr, i);
            i2 = IOUtils.digit2(cArr, i + 5);
            i4 = IOUtils.digit1(cArr, i7);
        } else if ((c3 == '-' && c4 == '-') || (c3 == '/' && c4 == '/')) {
            i3 = IOUtils.digit4(cArr, i);
            i2 = IOUtils.digit1(cArr, i + 5);
            i4 = IOUtils.digit2(cArr, i6);
        } else if ((c3 == 24180 && c4 == 26376 && c6 == 26085) || (c3 == 45380 && c4 == 50900 && c6 == 51068)) {
            i3 = IOUtils.digit4(cArr, i);
            i2 = IOUtils.digit1(cArr, i + 5);
            i4 = IOUtils.digit1(cArr, i6);
        } else {
            if (c == '.' && c3 == '.') {
                digit12 = IOUtils.digit1(cArr, i);
                digit22 = IOUtils.digit2(cArr, i5);
                digit42 = IOUtils.digit4(cArr, i + 5);
            } else if ((c2 == '.' && c3 == '.') || (c2 == '-' && c3 == '-')) {
                digit12 = IOUtils.digit2(cArr, i);
                digit22 = IOUtils.digit1(cArr, i + 3);
                digit42 = IOUtils.digit4(cArr, i + 5);
            } else if (c == '-' && c3 == '-') {
                digit12 = IOUtils.digit1(cArr, i);
                digit22 = IOUtils.digit2(cArr, i5);
                digit42 = IOUtils.digit4(cArr, i + 5);
            } else if (c2 == '-' && c4 == '-') {
                int digit23 = IOUtils.digit2(cArr, i);
                i2 = month(cArr[i + 3], c3, cArr[i + 5]);
                int digit24 = IOUtils.digit2(cArr, i6);
                if (digit24 != -1) {
                    digit24 += 2000;
                }
                i3 = digit24;
                i4 = digit23;
            } else {
                if (c == '/' && c3 == '/') {
                    digit2 = IOUtils.digit1(cArr, i);
                    digit1 = IOUtils.digit2(cArr, i5);
                    digit4 = IOUtils.digit4(cArr, i + 5);
                } else {
                    if (c2 != '/' || c3 != '/') {
                        return null;
                    }
                    digit2 = IOUtils.digit2(cArr, i);
                    digit1 = IOUtils.digit1(cArr, i + 3);
                    digit4 = IOUtils.digit4(cArr, i + 5);
                }
                i2 = digit2;
                i3 = digit4;
                i4 = digit1;
            }
            int i8 = digit12;
            i3 = digit42;
            i4 = i8;
            i2 = digit22;
        }
        if ((i3 | i2 | i4) <= 0) {
            return null;
        }
        return LocalDate.of(i3, i2, i4);
    }

    public static LocalDate parseLocalDate10(byte[] bArr, int i) {
        int month;
        int digit4;
        int i2;
        int digit2;
        int digit42;
        if (i + 10 > bArr.length) {
            return null;
        }
        char c = (char) bArr[i + 2];
        char c2 = (char) bArr[i + 4];
        int i3 = i + 5;
        char c3 = (char) bArr[i3];
        char c4 = (char) bArr[i + 7];
        if ((c2 == '-' && c4 == '-') || (c2 == '/' && c4 == '/')) {
            digit4 = IOUtils.digit4(bArr, i);
            month = IOUtils.digit2(bArr, i3);
            i2 = IOUtils.digit2(bArr, i + 8);
        } else {
            if ((c == '.' && c3 == '.') || (c == '-' && c3 == '-')) {
                digit2 = IOUtils.digit2(bArr, i);
                month = IOUtils.digit2(bArr, i + 3);
                digit42 = IOUtils.digit4(bArr, i + 6);
            } else if (c == '/' && c3 == '/') {
                month = IOUtils.digit2(bArr, i);
                digit2 = IOUtils.digit2(bArr, i + 3);
                digit42 = IOUtils.digit4(bArr, i + 6);
            } else {
                if (bArr[i + 1] != 32 || c3 != ' ') {
                    return null;
                }
                int digit1 = IOUtils.digit1(bArr, i);
                month = month(c, (char) bArr[i + 3], c2);
                digit4 = IOUtils.digit4(bArr, i + 6);
                i2 = digit1;
            }
            int i4 = digit2;
            digit4 = digit42;
            i2 = i4;
        }
        if ((digit4 | month | i2) <= 0) {
            return null;
        }
        return LocalDate.of(digit4, month, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.LocalDate parseLocalDate10(char[] r18, int r19) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseLocalDate10(char[], int):java.time.LocalDate");
    }

    public static LocalDate parseLocalDate11(char[] cArr, int i) {
        int digit2;
        int i2;
        int i3;
        if (i + 11 > cArr.length) {
            return null;
        }
        char c = cArr[i + 4];
        int i4 = i + 7;
        char c2 = cArr[i4];
        char c3 = cArr[i + 10];
        if ((c == 24180 && c2 == 26376 && c3 == 26085) || ((c == '-' && c2 == '-' && c3 == 'Z') || (c == 45380 && c2 == 50900 && c3 == 51068))) {
            i3 = IOUtils.digit4(cArr, i);
            i2 = IOUtils.digit2(cArr, i + 5);
            digit2 = IOUtils.digit2(cArr, i + 8);
        } else {
            if (cArr[i + 2] == ' ' && cArr[i + 6] == ' ') {
                int digit4 = IOUtils.digit4(cArr, i4);
                int month = month(cArr[i + 3], c, cArr[i + 5]);
                digit2 = IOUtils.digit2(cArr, i);
                i2 = month;
                i3 = digit4;
            }
            return null;
        }
        if ((i3 | i2 | digit2) >= 0 && (i3 != 0 || i2 != 0 || digit2 != 0)) {
            return LocalDate.of(i3, i2, digit2);
        }
        return null;
    }

    public static LocalDate parseLocalDate11(byte[] bArr, int i) {
        int digit2;
        int i2;
        int i3;
        if (i + 11 > bArr.length) {
            return null;
        }
        int i4 = i + 4;
        if (bArr[i4] == 45 && bArr[i + 7] == 45 && bArr[i + 10] == 90) {
            i3 = IOUtils.digit4(bArr, i);
            i2 = IOUtils.digit2(bArr, i + 5);
            digit2 = IOUtils.digit2(bArr, i + 8);
        } else {
            if (bArr[i + 2] == 32 && bArr[i + 6] == 32) {
                int digit4 = IOUtils.digit4(bArr, i + 7);
                int month = month((char) bArr[i + 3], (char) bArr[i4], (char) bArr[i + 5]);
                digit2 = IOUtils.digit2(bArr, i);
                i2 = month;
                i3 = digit4;
            }
            return null;
        }
        if ((i3 | i2 | digit2) >= 0 && (i3 != 0 || i2 != 0 || digit2 != 0)) {
            return LocalDate.of(i3, i2, digit2);
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime12(char[] cArr, int i) {
        int i2 = i + 12;
        if (i2 > cArr.length) {
            String str = new String(cArr, i, cArr.length - i);
            throw new DateTimeParseException("illegal input ".concat(str), str, 0);
        }
        int digit4 = IOUtils.digit4(cArr, i);
        int digit2 = IOUtils.digit2(cArr, i + 4);
        int digit22 = IOUtils.digit2(cArr, i + 6);
        int digit23 = IOUtils.digit2(cArr, i + 8);
        int digit24 = IOUtils.digit2(cArr, i + 10);
        if ((digit4 | digit2 | digit22 | digit23 | digit24) < 0) {
            String str2 = new String(cArr, i, i2);
            throw new DateTimeParseException("illegal input ".concat(str2), str2, 0);
        }
        if (digit4 == 0 && digit2 == 0 && digit22 == 0 && digit23 == 0 && digit24 == 0) {
            return null;
        }
        return LocalDateTime.of(digit4, digit2, digit22, digit23, digit24, 0);
    }

    public static LocalDateTime parseLocalDateTime12(byte[] bArr, int i) {
        int i2 = i + 12;
        if (i2 > bArr.length) {
            String str = new String(bArr, i, bArr.length - i);
            throw new DateTimeParseException("illegal input ".concat(str), str, 0);
        }
        int digit4 = IOUtils.digit4(bArr, i);
        int digit2 = IOUtils.digit2(bArr, i + 4);
        int digit22 = IOUtils.digit2(bArr, i + 6);
        int digit23 = IOUtils.digit2(bArr, i + 8);
        int digit24 = IOUtils.digit2(bArr, i + 10);
        if ((digit4 | digit2 | digit22 | digit23 | digit24) < 0) {
            String str2 = new String(bArr, i, i2);
            throw new DateTimeParseException("illegal input ".concat(str2), str2, 0);
        }
        if (digit4 == 0 && digit2 == 0 && digit22 == 0 && digit23 == 0 && digit24 == 0) {
            return null;
        }
        return LocalDateTime.of(digit4, digit2, digit22, digit23, digit24, 0);
    }

    public static LocalDateTime parseLocalDateTime14(char[] cArr, int i) {
        if (i + 14 > cArr.length) {
            return null;
        }
        int digit4 = IOUtils.digit4(cArr, i);
        int digit2 = IOUtils.digit2(cArr, i + 4);
        int digit22 = IOUtils.digit2(cArr, i + 6);
        int digit23 = IOUtils.digit2(cArr, i + 8);
        int digit24 = IOUtils.digit2(cArr, i + 10);
        int digit25 = IOUtils.digit2(cArr, i + 12);
        if ((digit4 | digit2 | digit22 | digit23 | digit24 | digit25) < 0) {
            return null;
        }
        return LocalDateTime.of(digit4, digit2, digit22, digit23, digit24, digit25);
    }

    public static LocalDateTime parseLocalDateTime14(byte[] bArr, int i) {
        if (i + 14 > bArr.length) {
            return null;
        }
        int digit4 = IOUtils.digit4(bArr, i);
        int digit2 = IOUtils.digit2(bArr, i + 4);
        int digit22 = IOUtils.digit2(bArr, i + 6);
        int digit23 = IOUtils.digit2(bArr, i + 8);
        int digit24 = IOUtils.digit2(bArr, i + 10);
        int digit25 = IOUtils.digit2(bArr, i + 12);
        if ((digit4 | digit2 | digit22 | digit23 | digit24 | digit25) < 0) {
            return null;
        }
        return LocalDateTime.of(digit4, digit2, digit22, digit23, digit24, digit25);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ac, code lost:
    
        if (r10 == ' ') goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0133 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0134  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.LocalDateTime parseLocalDateTime16(char[] r20, int r21) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseLocalDateTime16(char[], int):java.time.LocalDateTime");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x01dd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0130  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.LocalDateTime parseLocalDateTime16(byte[] r31, int r32) {
        /*
            Method dump skipped, instructions count: 484
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseLocalDateTime16(byte[], int):java.time.LocalDateTime");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0212 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.LocalDateTime parseLocalDateTime17(char[] r31, int r32) {
        /*
            Method dump skipped, instructions count: 560
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseLocalDateTime17(char[], int):java.time.LocalDateTime");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0252 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0254  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.LocalDateTime parseLocalDateTime17(byte[] r32, int r33) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseLocalDateTime17(byte[], int):java.time.LocalDateTime");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0262 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01cf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01f9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0238 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.LocalDateTime parseLocalDateTime18(char[] r29, int r30) {
        /*
            Method dump skipped, instructions count: 704
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseLocalDateTime18(char[], int):java.time.LocalDateTime");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0262 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01cf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01f9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0238 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.LocalDateTime parseLocalDateTime18(byte[] r29, int r30) {
        /*
            Method dump skipped, instructions count: 704
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseLocalDateTime18(byte[], int):java.time.LocalDateTime");
    }

    public static LocalDateTime parseLocalDateTime19(char[] cArr, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        if (i + 19 > cArr.length) {
            return null;
        }
        char c = cArr[i + 1];
        char c2 = cArr[i + 2];
        int i8 = i + 3;
        char c3 = cArr[i8];
        char c4 = cArr[i + 4];
        int i9 = i + 5;
        char c5 = cArr[i9];
        char c6 = cArr[i + 7];
        char c7 = cArr[i + 10];
        char c8 = cArr[i + 13];
        char c9 = cArr[i + 16];
        if (((c4 == '-' && c6 == '-') || (c4 == '/' && c6 == '/')) && ((c7 == ' ' || c7 == 'T') && c8 == ':' && c9 == ':')) {
            i6 = IOUtils.digit4(cArr, i);
            i5 = IOUtils.digit2(cArr, i9);
            i4 = IOUtils.digit2(cArr, i + 8);
            i3 = IOUtils.digit2(cArr, i + 11);
            i2 = IOUtils.digit2(cArr, i + 14);
            i7 = IOUtils.digit2(cArr, i + 17);
        } else if (c2 == '/' && c5 == '/' && ((c7 == ' ' || c7 == 'T') && c8 == ':' && c9 == ':')) {
            i4 = IOUtils.digit2(cArr, i);
            i5 = IOUtils.digit2(cArr, i8);
            i6 = IOUtils.digit4(cArr, i + 6);
            i3 = IOUtils.digit2(cArr, i + 11);
            i2 = IOUtils.digit2(cArr, i + 14);
            i7 = IOUtils.digit2(cArr, i + 17);
        } else {
            if (c != ' ' || c5 != ' ' || c7 != ' ' || c8 != ':' || c9 != ':') {
                return null;
            }
            i4 = IOUtils.digit1(cArr, i);
            i5 = month(c2, c3, c4);
            i6 = IOUtils.digit4(cArr, i + 6);
            i3 = IOUtils.digit2(cArr, i + 11);
            i2 = IOUtils.digit2(cArr, i + 14);
            i7 = IOUtils.digit2(cArr, i + 17);
        }
        int i10 = i7;
        int i11 = i6;
        int i12 = i5;
        int i13 = i4;
        int i14 = i3;
        int i15 = i2;
        if ((i11 | i12 | i13 | i14 | i15 | i10) <= 0) {
            return null;
        }
        return LocalDateTime.of(i11, i12, i13, i14, i15, i10);
    }

    public static LocalDateTime parseLocalDateTime19(String str, int i) {
        int i2 = i + 19;
        if (i2 > str.length()) {
            return null;
        }
        if (JDKUtils.STRING_CODER != null && JDKUtils.STRING_VALUE != null && JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
            return parseLocalDateTime19(JDKUtils.STRING_VALUE.apply(str), i);
        }
        if (JDKUtils.JVM_VERSION == 8 && !JDKUtils.FIELD_STRING_VALUE_ERROR) {
            return parseLocalDateTime19(JDKUtils.getCharArray(str), i);
        }
        char[] cArr = new char[19];
        str.getChars(i, i2, cArr, 0);
        return parseLocalDateTime19(cArr, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.LocalDateTime parseLocalDateTime19(byte[] r14, int r15) {
        /*
            int r0 = r15 + 19
            int r1 = r14.length
            r2 = 0
            if (r0 <= r1) goto L7
            return r2
        L7:
            int r0 = r15 + 1
            r0 = r14[r0]
            int r1 = r15 + 2
            r1 = r14[r1]
            int r3 = r15 + 3
            r4 = r14[r3]
            int r5 = r15 + 4
            r5 = r14[r5]
            int r6 = r15 + 5
            r7 = r14[r6]
            int r8 = r15 + 7
            r8 = r14[r8]
            int r9 = r15 + 10
            r9 = r14[r9]
            r10 = 84
            r11 = 45
            r12 = 47
            r13 = 32
            if (r5 != r11) goto L2f
            if (r8 == r11) goto L33
        L2f:
            if (r5 != r12) goto L49
            if (r8 != r12) goto L49
        L33:
            if (r9 == r13) goto L37
            if (r9 != r10) goto L49
        L37:
            int r0 = com.alibaba.fastjson2.util.IOUtils.digit4(r14, r15)
            int r1 = com.alibaba.fastjson2.util.IOUtils.digit2(r14, r6)
            int r3 = r15 + 8
            int r3 = com.alibaba.fastjson2.util.IOUtils.digit2(r14, r3)
        L45:
            r4 = r0
            r5 = r1
            r6 = r3
            goto L78
        L49:
            if (r1 != r12) goto L63
            if (r7 != r12) goto L63
            if (r9 == r13) goto L51
            if (r9 != r10) goto L63
        L51:
            int r0 = com.alibaba.fastjson2.util.IOUtils.digit2(r14, r15)
            int r1 = com.alibaba.fastjson2.util.IOUtils.digit2(r14, r3)
            int r3 = r15 + 6
            int r3 = com.alibaba.fastjson2.util.IOUtils.digit4(r14, r3)
            r6 = r0
            r5 = r1
            r4 = r3
            goto L78
        L63:
            if (r0 != r13) goto L9f
            if (r7 != r13) goto L9f
            if (r9 != r13) goto L9f
            int r3 = com.alibaba.fastjson2.util.IOUtils.digit1(r14, r15)
            int r1 = month(r1, r4, r5)
            int r0 = r15 + 6
            int r0 = com.alibaba.fastjson2.util.IOUtils.digit4(r14, r0)
            goto L45
        L78:
            int r15 = r15 + 11
            long r14 = hms(r14, r15)
            r0 = r4 | r5
            r0 = r0 | r6
            long r0 = (long) r0
            long r0 = r0 | r14
            r7 = 0
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 > 0) goto L8a
            return r2
        L8a:
            int r0 = (int) r14
            r7 = r0 & 255(0xff, float:3.57E-43)
            r0 = 24
            long r0 = r14 >> r0
            int r0 = (int) r0
            r8 = r0 & 255(0xff, float:3.57E-43)
            r0 = 48
            long r14 = r14 >> r0
            int r14 = (int) r14
            r9 = r14 & 255(0xff, float:3.57E-43)
            java.time.LocalDateTime r14 = java.time.LocalDateTime.of(r4, r5, r6, r7, r8, r9)
            return r14
        L9f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseLocalDateTime19(byte[], int):java.time.LocalDateTime");
    }

    public static LocalDateTime parseLocalDateTime20(char[] cArr, int i) {
        if (i + 19 <= cArr.length && cArr[i + 2] == ' ' && cArr[i + 6] == ' ' && cArr[i + 11] == ' ' && cArr[i + 14] == ':' && cArr[i + 17] == ':') {
            int digit2 = IOUtils.digit2(cArr, i);
            int month = month(cArr[i + 3], cArr[i + 4], cArr[i + 5]);
            int digit4 = IOUtils.digit4(cArr, i + 7);
            int digit22 = IOUtils.digit2(cArr, i + 12);
            int digit23 = IOUtils.digit2(cArr, i + 15);
            int digit24 = IOUtils.digit2(cArr, i + 18);
            if ((digit4 | month | digit2 | digit22 | digit23 | digit24) > 0 && digit22 <= 24 && digit23 <= 59 && digit24 <= 60) {
                return LocalDateTime.of(digit4, month, digit2, digit22, digit23, digit24);
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime20(byte[] bArr, int i) {
        if (i + 19 <= bArr.length && bArr[i + 2] == 32 && bArr[i + 6] == 32 && bArr[i + 11] == 32) {
            long hms = hms(bArr, i + 12);
            if (hms != -1) {
                int digit2 = IOUtils.digit2(bArr, i);
                int month = month(bArr[i + 3], bArr[i + 4], bArr[i + 5]);
                int digit4 = IOUtils.digit4(bArr, i + 7);
                int i2 = ((int) hms) & 255;
                int i3 = ((int) (hms >> 24)) & 255;
                int i4 = ((int) (hms >> 48)) & 255;
                if ((digit4 | month | digit2 | i2 | i3 | i4) > 0 && i2 <= 24 && i3 <= 59 && i4 <= 60) {
                    return LocalDateTime.of(digit4, month, digit2, i2, i3, i4);
                }
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime26(byte[] bArr, int i) {
        byte b;
        if (i + 26 <= bArr.length && bArr[i + 4] == 45 && bArr[i + 7] == 45 && ((b = bArr[i + 10]) == 32 || b == 84)) {
            long hms = hms(bArr, i + 11);
            if (hms != -1 && bArr[i + 19] == 46) {
                int digit4 = IOUtils.digit4(bArr, i);
                int digit2 = IOUtils.digit2(bArr, i + 5);
                int digit22 = IOUtils.digit2(bArr, i + 8);
                int i2 = ((int) hms) & 255;
                int i3 = ((int) (hms >> 24)) & 255;
                int i4 = ((int) (hms >> 48)) & 255;
                int readNanos = readNanos(bArr, 6, i + 20);
                if ((digit4 | digit2 | digit22 | i2 | i3 | i4 | readNanos) > 0 && i2 <= 24 && i3 <= 59 && i4 <= 60) {
                    return LocalDateTime.of(digit4, digit2, digit22, i2, i3, i4, readNanos);
                }
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime26(char[] cArr, int i) {
        char c;
        if (i + 26 <= cArr.length && cArr[i + 4] == '-' && cArr[i + 7] == '-' && (((c = cArr[i + 10]) == ' ' || c == 'T') && cArr[i + 13] == ':' && cArr[i + 16] == ':' && cArr[i + 19] == '.')) {
            int digit4 = IOUtils.digit4(cArr, i);
            int digit2 = IOUtils.digit2(cArr, i + 5);
            int digit22 = IOUtils.digit2(cArr, i + 8);
            int digit23 = IOUtils.digit2(cArr, i + 11);
            int digit24 = IOUtils.digit2(cArr, i + 14);
            int digit25 = IOUtils.digit2(cArr, i + 17);
            int readNanos = readNanos(cArr, 6, i + 20);
            if ((digit4 | digit2 | digit22 | digit23 | digit24 | digit25 | readNanos) > 0 && digit23 <= 24 && digit24 <= 59 && digit25 <= 60) {
                return LocalDateTime.of(digit4, digit2, digit22, digit23, digit24, digit25, readNanos);
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime27(byte[] bArr, int i) {
        byte b;
        if (i + 27 <= bArr.length && bArr[i + 4] == 45 && bArr[i + 7] == 45 && ((b = bArr[i + 10]) == 32 || b == 84)) {
            long hms = hms(bArr, i + 11);
            if (hms != -1 && bArr[i + 19] == 46) {
                int digit4 = IOUtils.digit4(bArr, i);
                int digit2 = IOUtils.digit2(bArr, i + 5);
                int digit22 = IOUtils.digit2(bArr, i + 8);
                int i2 = ((int) hms) & 255;
                int i3 = ((int) (hms >> 24)) & 255;
                int i4 = ((int) (hms >> 48)) & 255;
                int readNanos = readNanos(bArr, 7, i + 20);
                if ((digit4 | digit2 | digit22 | i2 | i3 | i4 | readNanos) > 0 && i2 <= 24 && i3 <= 59 && i4 <= 60) {
                    return LocalDateTime.of(digit4, digit2, digit22, i2, i3, i4, readNanos);
                }
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime27(char[] cArr, int i) {
        char c;
        if (i + 27 <= cArr.length && cArr[i + 4] == '-' && cArr[i + 7] == '-' && (((c = cArr[i + 10]) == ' ' || c == 'T') && cArr[i + 13] == ':' && cArr[i + 16] == ':' && cArr[i + 19] == '.')) {
            int digit4 = IOUtils.digit4(cArr, i);
            int digit2 = IOUtils.digit2(cArr, i + 5);
            int digit22 = IOUtils.digit2(cArr, i + 8);
            int digit23 = IOUtils.digit2(cArr, i + 11);
            int digit24 = IOUtils.digit2(cArr, i + 14);
            int digit25 = IOUtils.digit2(cArr, i + 17);
            int readNanos = readNanos(cArr, 7, i + 20);
            if ((digit4 | digit2 | digit22 | digit23 | digit24 | digit25 | readNanos) > 0 && digit23 <= 24 && digit24 <= 59 && digit25 <= 60) {
                return LocalDateTime.of(digit4, digit2, digit22, digit23, digit24, digit25, readNanos);
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime28(char[] cArr, int i) {
        char c;
        if (i + 28 <= cArr.length && cArr[i + 4] == '-' && cArr[i + 7] == '-' && (((c = cArr[i + 10]) == ' ' || c == 'T') && cArr[i + 13] == ':' && cArr[i + 16] == ':' && cArr[i + 19] == '.')) {
            int digit4 = IOUtils.digit4(cArr, i);
            int digit2 = IOUtils.digit2(cArr, i + 5);
            int digit22 = IOUtils.digit2(cArr, i + 8);
            int digit23 = IOUtils.digit2(cArr, i + 11);
            int digit24 = IOUtils.digit2(cArr, i + 14);
            int digit25 = IOUtils.digit2(cArr, i + 17);
            int readNanos = readNanos(cArr, 8, i + 20);
            if ((digit4 | digit2 | digit22 | digit23 | digit24 | digit25 | readNanos) > 0 && digit23 <= 24 && digit24 <= 59 && digit25 <= 60) {
                return LocalDateTime.of(digit4, digit2, digit22, digit23, digit24, digit25, readNanos);
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime28(byte[] bArr, int i) {
        byte b;
        if (i + 28 <= bArr.length && bArr[i + 4] == 45 && bArr[i + 7] == 45 && ((b = bArr[i + 10]) == 32 || b == 84)) {
            long hms = hms(bArr, i + 11);
            if (hms != -1 && bArr[i + 19] == 46) {
                int digit4 = IOUtils.digit4(bArr, i);
                int digit2 = IOUtils.digit2(bArr, i + 5);
                int digit22 = IOUtils.digit2(bArr, i + 8);
                int i2 = ((int) hms) & 255;
                int i3 = ((int) (hms >> 24)) & 255;
                int i4 = ((int) (hms >> 48)) & 255;
                int readNanos = readNanos(bArr, 8, i + 20);
                if ((digit4 | digit2 | digit22 | i2 | i3 | i4 | readNanos) > 0 && i2 <= 24 && i3 <= 59 && i4 <= 60) {
                    return LocalDateTime.of(digit4, digit2, digit22, i2, i3, i4, readNanos);
                }
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime29(byte[] bArr, int i) {
        byte b;
        if (i + 29 <= bArr.length && bArr[i + 4] == 45 && bArr[i + 7] == 45 && ((b = bArr[i + 10]) == 32 || b == 84)) {
            long hms = hms(bArr, i + 11);
            if (hms != -1 && bArr[i + 19] == 46) {
                int digit4 = IOUtils.digit4(bArr, i);
                int digit2 = IOUtils.digit2(bArr, i + 5);
                int digit22 = IOUtils.digit2(bArr, i + 8);
                int i2 = ((int) hms) & 255;
                int i3 = ((int) (hms >> 24)) & 255;
                int i4 = ((int) (hms >> 48)) & 255;
                int readNanos = readNanos(bArr, 9, i + 20);
                if ((digit4 | digit2 | digit22 | i2 | i3 | i4 | readNanos) > 0 && i2 <= 24 && i3 <= 59 && i4 <= 60) {
                    return LocalDateTime.of(digit4, digit2, digit22, i2, i3, i4, readNanos);
                }
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime29(char[] cArr, int i) {
        char c;
        if (i + 29 <= cArr.length && cArr[i + 4] == '-' && cArr[i + 7] == '-' && (((c = cArr[i + 10]) == ' ' || c == 'T') && cArr[i + 13] == ':' && cArr[i + 16] == ':' && cArr[i + 19] == '.')) {
            int digit4 = IOUtils.digit4(cArr, i);
            int digit2 = IOUtils.digit2(cArr, i + 5);
            int digit22 = IOUtils.digit2(cArr, i + 8);
            int digit23 = IOUtils.digit2(cArr, i + 11);
            int digit24 = IOUtils.digit2(cArr, i + 14);
            int digit25 = IOUtils.digit2(cArr, i + 17);
            int readNanos = readNanos(cArr, 9, i + 20);
            if ((digit4 | digit2 | digit22 | digit23 | digit24 | digit25 | readNanos) > 0 && digit23 <= 24 && digit24 <= 59 && digit25 <= 60) {
                return LocalDateTime.of(digit4, digit2, digit22, digit23, digit24, digit25, readNanos);
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTimeX(char[] cArr, int i, int i2) {
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        char c6;
        char c7;
        char c8;
        char c9;
        char c10;
        char c11;
        char c12;
        char c13;
        char c14;
        char c15;
        char c16;
        char c17;
        char c18;
        char c19;
        char c20;
        if (cArr == null || i2 == 0 || i2 < 21 || i2 > 29) {
            return null;
        }
        char c21 = cArr[i];
        char c22 = cArr[i + 1];
        char c23 = cArr[i + 2];
        char c24 = cArr[i + 3];
        char c25 = cArr[i + 4];
        char c26 = cArr[i + 5];
        char c27 = cArr[i + 6];
        char c28 = cArr[i + 7];
        char c29 = cArr[i + 8];
        char c30 = cArr[i + 9];
        char c31 = cArr[i + 10];
        char c32 = cArr[i + 11];
        char c33 = cArr[i + 12];
        char c34 = cArr[i + 13];
        char c35 = cArr[i + 14];
        char c36 = cArr[i + 15];
        char c37 = cArr[i + 16];
        char c38 = cArr[i + 17];
        char c39 = cArr[i + 18];
        char c40 = cArr[i + 19];
        char c41 = '0';
        switch (i2) {
            case 21:
                c = c29;
                c2 = c30;
                c3 = c32;
                c4 = c35;
                c5 = c38;
                c6 = '0';
                c7 = '0';
                c8 = '0';
                c9 = '0';
                c10 = '0';
                c11 = c21;
                c12 = cArr[i + 20];
                c13 = '0';
                c14 = '0';
                break;
            case 22:
                c15 = cArr[i + 20];
                c16 = cArr[i + 21];
                c = c29;
                c2 = c30;
                c3 = c32;
                c4 = c35;
                c5 = c38;
                c7 = '0';
                c8 = '0';
                c9 = c8;
                c10 = c9;
                c11 = c21;
                c12 = c15;
                c13 = c10;
                c14 = c13;
                c41 = c16;
                c6 = c14;
                break;
            case 23:
                c15 = cArr[i + 20];
                c16 = cArr[i + 21];
                c7 = cArr[i + 22];
                c = c29;
                c2 = c30;
                c3 = c32;
                c4 = c35;
                c5 = c38;
                c8 = '0';
                c9 = c8;
                c10 = c9;
                c11 = c21;
                c12 = c15;
                c13 = c10;
                c14 = c13;
                c41 = c16;
                c6 = c14;
                break;
            case 24:
                char c42 = cArr[i + 20];
                char c43 = cArr[i + 21];
                c7 = cArr[i + 22];
                c = c29;
                c2 = c30;
                c3 = c32;
                c4 = c35;
                c5 = c38;
                c9 = '0';
                c10 = '0';
                c11 = c21;
                c12 = c42;
                c13 = cArr[i + 23];
                c14 = '0';
                c8 = '0';
                c41 = c43;
                c6 = '0';
                break;
            case 25:
                char c44 = cArr[i + 20];
                char c45 = cArr[i + 21];
                c7 = cArr[i + 22];
                c = c29;
                c2 = c30;
                c3 = c32;
                c4 = c35;
                c5 = c38;
                c10 = '0';
                c11 = c21;
                c12 = c44;
                c13 = cArr[i + 23];
                c14 = cArr[i + 24];
                c8 = '0';
                c9 = '0';
                c41 = c45;
                c6 = '0';
                break;
            case 26:
                char c46 = cArr[i + 20];
                c17 = cArr[i + 21];
                c7 = cArr[i + 22];
                c18 = cArr[i + 23];
                char c47 = cArr[i + 24];
                c19 = cArr[i + 25];
                c = c29;
                c2 = c30;
                c3 = c32;
                c4 = c35;
                c5 = c38;
                c10 = '0';
                c12 = c46;
                c14 = c47;
                c9 = '0';
                c41 = c17;
                c6 = c19;
                c11 = c21;
                c13 = c18;
                c8 = c9;
                break;
            case 27:
                char c48 = cArr[i + 20];
                c17 = cArr[i + 21];
                c7 = cArr[i + 22];
                c18 = cArr[i + 23];
                char c49 = cArr[i + 24];
                c19 = cArr[i + 25];
                c10 = cArr[i + 26];
                c = c29;
                c2 = c30;
                c3 = c32;
                c4 = c35;
                c5 = c38;
                c12 = c48;
                c14 = c49;
                c9 = '0';
                c41 = c17;
                c6 = c19;
                c11 = c21;
                c13 = c18;
                c8 = c9;
                break;
            case 28:
                char c50 = cArr[i + 20];
                char c51 = cArr[i + 21];
                c7 = cArr[i + 22];
                char c52 = cArr[i + 23];
                char c53 = cArr[i + 24];
                char c54 = cArr[i + 25];
                c10 = cArr[i + 26];
                c = c29;
                c2 = c30;
                c3 = c32;
                c4 = c35;
                c5 = c38;
                c12 = c50;
                c14 = c53;
                c9 = '0';
                c41 = c51;
                c6 = c54;
                c11 = c21;
                c13 = c52;
                c8 = cArr[i + 27];
                break;
            default:
                char c55 = cArr[i + 20];
                c41 = cArr[i + 21];
                char c56 = cArr[i + 22];
                char c57 = cArr[i + 23];
                char c58 = cArr[i + 24];
                char c59 = cArr[i + 25];
                char c60 = cArr[i + 26];
                c = c29;
                c2 = c30;
                c3 = c32;
                c4 = c35;
                c5 = c38;
                c12 = c55;
                c14 = c58;
                c8 = cArr[i + 27];
                c10 = c60;
                c11 = c21;
                c13 = c57;
                c7 = c56;
                c6 = c59;
                c9 = cArr[i + 28];
                break;
        }
        if (c25 != '-' || c28 != '-' || ((c31 != ' ' && c31 != 'T') || c34 != ':' || c37 != ':' || c40 != '.')) {
            int i3 = i + i2;
            if (cArr[i3 - 15] == '-' && cArr[i3 - 12] == '-' && (((c20 = cArr[i3 - 9]) == ' ' || c20 == 'T') && cArr[i3 - 6] == ':' && cArr[i3 - 3] == ':')) {
                return LocalDateTime.of(TypeUtils.parseInt(cArr, i, i2 - 15), TypeUtils.parseInt(cArr, i3 - 14, 2), TypeUtils.parseInt(cArr, i3 - 11, 2), TypeUtils.parseInt(cArr, i3 - 8, 2), TypeUtils.parseInt(cArr, i3 - 5, 2), TypeUtils.parseInt(cArr, i3 - 2, 2));
            }
            return null;
        }
        return localDateTime(c11, c22, c23, c24, c26, c27, c, c2, c3, c33, c4, c36, c5, c39, c12, c41, c7, c13, c14, c6, c10, c8, c9);
    }

    public static LocalDateTime parseLocalDateTimeX(byte[] bArr, int i, int i2) {
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        char c6;
        char c7;
        char c8;
        char c9;
        char c10;
        char c11;
        char c12;
        char c13;
        char c14;
        char c15;
        char c16;
        byte b;
        if (bArr == null || i2 == 0 || i2 < 21 || i2 > 29) {
            return null;
        }
        char c17 = (char) bArr[i];
        char c18 = (char) bArr[i + 1];
        char c19 = (char) bArr[i + 2];
        char c20 = (char) bArr[i + 3];
        char c21 = (char) bArr[i + 4];
        char c22 = (char) bArr[i + 5];
        char c23 = (char) bArr[i + 6];
        char c24 = (char) bArr[i + 7];
        char c25 = (char) bArr[i + 8];
        char c26 = (char) bArr[i + 9];
        char c27 = (char) bArr[i + 10];
        char c28 = (char) bArr[i + 11];
        char c29 = (char) bArr[i + 12];
        char c30 = (char) bArr[i + 13];
        char c31 = (char) bArr[i + 14];
        char c32 = (char) bArr[i + 15];
        char c33 = (char) bArr[i + 16];
        char c34 = (char) bArr[i + 17];
        char c35 = (char) bArr[i + 18];
        char c36 = (char) bArr[i + 19];
        char c37 = '0';
        switch (i2) {
            case 21:
                c = (char) bArr[i + 20];
                c2 = c25;
                c3 = c26;
                c4 = c28;
                c5 = '0';
                c6 = '0';
                c7 = '0';
                c8 = '0';
                c9 = c29;
                c10 = c31;
                c11 = c34;
                c12 = c35;
                c13 = c18;
                c14 = '0';
                c15 = '0';
                c16 = '0';
                break;
            case 22:
                char c38 = (char) bArr[i + 20];
                c2 = c25;
                c3 = c26;
                c4 = c28;
                c5 = '0';
                c7 = '0';
                c8 = '0';
                c9 = c29;
                c10 = c31;
                c11 = c34;
                c12 = c35;
                c13 = c18;
                c37 = (char) bArr[i + 21];
                c14 = '0';
                c15 = '0';
                c16 = '0';
                c = c38;
                c6 = '0';
                break;
            case 23:
                char c39 = (char) bArr[i + 20];
                char c40 = (char) bArr[i + 21];
                c2 = c25;
                c3 = c26;
                c4 = c28;
                c5 = '0';
                c8 = '0';
                c9 = c29;
                c10 = c31;
                c11 = c34;
                c12 = c35;
                c13 = c18;
                c14 = (char) bArr[i + 22];
                c15 = '0';
                c16 = '0';
                c = c39;
                c37 = c40;
                c6 = '0';
                c7 = c6;
                break;
            case 24:
                char c41 = (char) bArr[i + 20];
                char c42 = (char) bArr[i + 21];
                c2 = c25;
                c3 = c26;
                c4 = c28;
                c5 = '0';
                c9 = c29;
                c10 = c31;
                c11 = c34;
                c12 = c35;
                c13 = c18;
                c14 = (char) bArr[i + 22];
                c15 = (char) bArr[i + 23];
                c16 = '0';
                c8 = '0';
                c = c41;
                c37 = c42;
                c6 = '0';
                c7 = c6;
                break;
            case 25:
                char c43 = (char) bArr[i + 20];
                char c44 = (char) bArr[i + 21];
                char c45 = (char) bArr[i + 22];
                char c46 = (char) bArr[i + 23];
                c5 = (char) bArr[i + 24];
                c2 = c25;
                c3 = c26;
                c4 = c28;
                c9 = c29;
                c10 = c31;
                c11 = c34;
                c12 = c35;
                c = c43;
                c14 = c45;
                c15 = c46;
                c6 = '0';
                c8 = '0';
                c13 = c18;
                c16 = '0';
                c37 = c44;
                c7 = '0';
                break;
            case 26:
                char c47 = (char) bArr[i + 20];
                char c48 = (char) bArr[i + 21];
                char c49 = (char) bArr[i + 22];
                c2 = c25;
                c3 = c26;
                c4 = c28;
                c10 = c31;
                c12 = c35;
                c15 = (char) bArr[i + 23];
                c5 = (char) bArr[i + 24];
                c9 = c29;
                c11 = c34;
                c13 = c18;
                c14 = c49;
                c16 = (char) bArr[i + 25];
                c8 = '0';
                c = c47;
                c6 = '0';
                c37 = c48;
                c7 = c6;
                break;
            case 27:
                char c50 = (char) bArr[i + 20];
                char c51 = (char) bArr[i + 21];
                char c52 = (char) bArr[i + 22];
                char c53 = (char) bArr[i + 23];
                char c54 = (char) bArr[i + 24];
                char c55 = (char) bArr[i + 25];
                c6 = (char) bArr[i + 26];
                c = c50;
                c2 = c25;
                c3 = c26;
                c4 = c28;
                c10 = c31;
                c12 = c35;
                c15 = c53;
                c13 = c18;
                c16 = c55;
                c5 = c54;
                c9 = c29;
                c11 = c34;
                c14 = c52;
                c8 = '0';
                c37 = c51;
                c7 = '0';
                break;
            case 28:
                char c56 = (char) bArr[i + 20];
                char c57 = (char) bArr[i + 21];
                char c58 = (char) bArr[i + 22];
                char c59 = (char) bArr[i + 23];
                char c60 = (char) bArr[i + 24];
                char c61 = (char) bArr[i + 25];
                char c62 = (char) bArr[i + 26];
                c2 = c25;
                c3 = c26;
                c4 = c28;
                c10 = c31;
                c12 = c35;
                c15 = c59;
                c13 = c18;
                c16 = c61;
                c5 = c60;
                c9 = c29;
                c11 = c34;
                c14 = c58;
                c8 = '0';
                c37 = c57;
                c7 = (char) bArr[i + 27];
                c = c56;
                c6 = c62;
                break;
            default:
                char c63 = (char) bArr[i + 20];
                char c64 = (char) bArr[i + 21];
                char c65 = (char) bArr[i + 22];
                char c66 = (char) bArr[i + 23];
                char c67 = (char) bArr[i + 24];
                char c68 = (char) bArr[i + 25];
                char c69 = (char) bArr[i + 26];
                char c70 = (char) bArr[i + 27];
                c37 = c64;
                c6 = c69;
                c2 = c25;
                c3 = c26;
                c4 = c28;
                c10 = c31;
                c12 = c35;
                c15 = c66;
                c8 = (char) bArr[i + 28];
                c = c63;
                c5 = c67;
                c13 = c18;
                c16 = c68;
                c9 = c29;
                c11 = c34;
                c14 = c65;
                c7 = c70;
                break;
        }
        if (c21 != '-' || c24 != '-' || ((c27 != ' ' && c27 != 'T') || c30 != ':' || c33 != ':' || c36 != '.')) {
            int i3 = i + i2;
            if (bArr[i3 - 15] == 45 && bArr[i3 - 12] == 45 && (((b = bArr[i3 - 9]) == 32 || b == 84) && bArr[i3 - 6] == 58 && bArr[i3 - 3] == 58)) {
                return LocalDateTime.of(TypeUtils.parseInt(bArr, i, i2 - 15), TypeUtils.parseInt(bArr, i3 - 14, 2), TypeUtils.parseInt(bArr, i3 - 11, 2), TypeUtils.parseInt(bArr, i3 - 8, 2), TypeUtils.parseInt(bArr, i3 - 5, 2), TypeUtils.parseInt(bArr, i3 - 2, 2));
            }
            return null;
        }
        return localDateTime(c17, c13, c19, c20, c22, c23, c2, c3, c4, c9, c10, c32, c11, c12, c, c37, c14, c15, c5, c16, c6, c7, c8);
    }

    static ZonedDateTime parseZonedDateTime16(char[] cArr, int i, ZoneId zoneId) {
        if (i + 16 > cArr.length) {
            String str = new String(cArr, i, cArr.length - i);
            throw new DateTimeParseException("illegal input ".concat(str), str, 0);
        }
        char c = cArr[i];
        char c2 = cArr[i + 1];
        char c3 = cArr[i + 2];
        char c4 = cArr[i + 3];
        char c5 = cArr[i + 4];
        char c6 = cArr[i + 5];
        char c7 = cArr[i + 6];
        char c8 = cArr[i + 7];
        char c9 = cArr[i + 8];
        char c10 = cArr[i + 9];
        int i2 = i + 10;
        char c11 = cArr[i2];
        char c12 = cArr[i + 13];
        if (c5 != '-' || c8 != '-' || ((c11 != '+' && c11 != '-') || c12 != ':')) {
            String str2 = new String(cArr, i, 16);
            throw new DateTimeParseException("illegal input ".concat(str2), str2, 0);
        }
        if (c < '0' || c > '9' || c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9') {
            String str3 = new String(cArr, i, 16);
            throw new DateTimeParseException("illegal input ".concat(str3), str3, 0);
        }
        int i3 = ((c - '0') * 1000) + ((c2 - '0') * 100) + ((c3 - '0') * 10) + (c4 - '0');
        if (c6 < '0' || c6 > '9' || c7 < '0' || c7 > '9') {
            String str4 = new String(cArr, i, 16);
            throw new DateTimeParseException("illegal input ".concat(str4), str4, 0);
        }
        int i4 = ((c6 - '0') * 10) + (c7 - '0');
        if (c9 < '0' || c9 > '9' || c10 < '0' || c10 > '9') {
            String str5 = new String(cArr, i, 16);
            throw new DateTimeParseException("illegal input ".concat(str5), str5, 0);
        }
        return ZonedDateTime.of(LocalDateTime.of(LocalDate.of(i3, i4, ((c9 - '0') * 10) + (c10 - '0')), LocalTime.MIN), getZoneId(new String(cArr, i2, 6), zoneId));
    }

    static ZonedDateTime parseZonedDateTime16(byte[] bArr, int i, ZoneId zoneId) {
        if (i + 16 > bArr.length) {
            String str = new String(bArr, i, bArr.length - i);
            throw new DateTimeParseException("illegal input ".concat(str), str, 0);
        }
        char c = (char) bArr[i];
        char c2 = (char) bArr[i + 1];
        char c3 = (char) bArr[i + 2];
        char c4 = (char) bArr[i + 3];
        char c5 = (char) bArr[i + 4];
        char c6 = (char) bArr[i + 5];
        char c7 = (char) bArr[i + 6];
        char c8 = (char) bArr[i + 7];
        char c9 = (char) bArr[i + 8];
        char c10 = (char) bArr[i + 9];
        int i2 = i + 10;
        char c11 = (char) bArr[i2];
        char c12 = (char) bArr[i + 13];
        if (c5 != '-' || c8 != '-' || ((c11 != '+' && c11 != '-') || c12 != ':')) {
            String str2 = new String(bArr, i, 16);
            throw new DateTimeParseException("illegal input ".concat(str2), str2, 0);
        }
        if (c < '0' || c > '9' || c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9') {
            String str3 = new String(bArr, i, 16);
            throw new DateTimeParseException("illegal input ".concat(str3), str3, 0);
        }
        int i3 = ((c - '0') * 1000) + ((c2 - '0') * 100) + ((c3 - '0') * 10) + (c4 - '0');
        if (c6 < '0' || c6 > '9' || c7 < '0' || c7 > '9') {
            String str4 = new String(bArr, i, 16);
            throw new DateTimeParseException("illegal input ".concat(str4), str4, 0);
        }
        int i4 = ((c6 - '0') * 10) + (c7 - '0');
        if (c9 < '0' || c9 > '9' || c10 < '0' || c10 > '9') {
            String str5 = new String(bArr, i, 16);
            throw new DateTimeParseException("illegal input ".concat(str5), str5, 0);
        }
        return ZonedDateTime.of(LocalDateTime.of(LocalDate.of(i3, i4, ((c9 - '0') * 10) + (c10 - '0')), LocalTime.MIN), getZoneId(new String(bArr, i2, 6), zoneId));
    }

    public static ZonedDateTime parseZonedDateTime(String str) {
        return parseZonedDateTime(str, DEFAULT_ZONE_ID);
    }

    public static ZonedDateTime parseZonedDateTime(String str, ZoneId zoneId) {
        ZonedDateTime parseZonedDateTime;
        if (str == null || str.length() == 0) {
            return null;
        }
        if (JDKUtils.STRING_CODER != null && JDKUtils.STRING_VALUE != null && JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
            byte[] apply = JDKUtils.STRING_VALUE.apply(str);
            parseZonedDateTime = parseZonedDateTime(apply, 0, apply.length, zoneId);
        } else {
            char[] charArray = JDKUtils.getCharArray(str);
            parseZonedDateTime = parseZonedDateTime(charArray, 0, charArray.length, zoneId);
        }
        if (parseZonedDateTime != null) {
            return parseZonedDateTime;
        }
        str.hashCode();
        switch (str) {
            case "0":
            case "null":
            case "0000-00-00":
                return null;
            default:
                throw new DateTimeParseException(str, str, 0);
        }
    }

    public static ZonedDateTime parseZonedDateTime(byte[] bArr, int i, int i2) {
        return parseZonedDateTime(bArr, i, i2, DEFAULT_ZONE_ID);
    }

    public static ZonedDateTime parseZonedDateTime(char[] cArr, int i, int i2) {
        return parseZonedDateTime(cArr, i, i2, DEFAULT_ZONE_ID);
    }

    /* JADX WARN: Code restructure failed: missing block: B:128:0x042c, code lost:
    
        if (r5 != 'Z') goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x04a0, code lost:
    
        if (r4 != 'Z') goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x05ed, code lost:
    
        if (r9 == 'P') goto L207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:417:0x0a30, code lost:
    
        if (r1 != 'Z') goto L471;
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x0aaa, code lost:
    
        if (r15 != 'Z') goto L505;
     */
    /* JADX WARN: Code restructure failed: missing block: B:501:0x0c1f, code lost:
    
        if (r6 != 'Z') goto L603;
     */
    /* JADX WARN: Code restructure failed: missing block: B:525:0x0cd0, code lost:
    
        if (r12 != 'Z') goto L637;
     */
    /* JADX WARN: Code restructure failed: missing block: B:609:0x0ecc, code lost:
    
        if (r14 != 'Z') goto L735;
     */
    /* JADX WARN: Code restructure failed: missing block: B:752:0x0ba5, code lost:
    
        if (r15 != 'Z') goto L568;
     */
    /* JADX WARN: Code restructure failed: missing block: B:797:0x09a6, code lost:
    
        if (r14 != 'Z') goto L437;
     */
    /* JADX WARN: Removed duplicated region for block: B:201:0x05eb  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x05f8  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x06bd  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x074d  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x08ea  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0970 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0a02 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0a7c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:444:0x0af0  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0b75 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0bf1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:504:0x0ca2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:528:0x0d20  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0d99  */
    /* JADX WARN: Removed duplicated region for block: B:568:0x0e23  */
    /* JADX WARN: Removed duplicated region for block: B:588:0x0e9e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0fe6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0fe9  */
    /* JADX WARN: Removed duplicated region for block: B:612:0x0f14  */
    /* JADX WARN: Removed duplicated region for block: B:618:0x0ff7  */
    /* JADX WARN: Removed duplicated region for block: B:685:0x1099  */
    /* JADX WARN: Removed duplicated region for block: B:690:0x10b1  */
    /* JADX WARN: Removed duplicated region for block: B:702:0x0ff1  */
    /* JADX WARN: Removed duplicated region for block: B:705:0x0ed7  */
    /* JADX WARN: Removed duplicated region for block: B:707:0x0eda  */
    /* JADX WARN: Removed duplicated region for block: B:716:0x0d8f  */
    /* JADX WARN: Removed duplicated region for block: B:719:0x0cdb  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x0cde  */
    /* JADX WARN: Removed duplicated region for block: B:755:0x0bb0  */
    /* JADX WARN: Removed duplicated region for block: B:759:0x0bb3  */
    /* JADX WARN: Removed duplicated region for block: B:763:0x0b6d  */
    /* JADX WARN: Removed duplicated region for block: B:766:0x0ab5  */
    /* JADX WARN: Removed duplicated region for block: B:768:0x0ab8  */
    /* JADX WARN: Removed duplicated region for block: B:774:0x0a3b  */
    /* JADX WARN: Removed duplicated region for block: B:776:0x0a3e  */
    /* JADX WARN: Removed duplicated region for block: B:800:0x09af  */
    /* JADX WARN: Removed duplicated region for block: B:802:0x09b2  */
    /* JADX WARN: Removed duplicated region for block: B:809:0x08e4  */
    /* JADX WARN: Removed duplicated region for block: B:815:0x0743  */
    /* JADX WARN: Removed duplicated region for block: B:817:0x05f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.ZonedDateTime parseZonedDateTime(byte[] r58, int r59, int r60, java.time.ZoneId r61) {
        /*
            Method dump skipped, instructions count: 4336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseZonedDateTime(byte[], int, int, java.time.ZoneId):java.time.ZonedDateTime");
    }

    /* JADX WARN: Code restructure failed: missing block: B:128:0x036b, code lost:
    
        if (r4 != 'Z') goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x03de, code lost:
    
        if (r6 != 'Z') goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x052b, code lost:
    
        if (r9 == 'P') goto L205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:347:0x0881, code lost:
    
        if (r13 != 'Z') goto L409;
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x0a21, code lost:
    
        if (r1 != 'Z') goto L509;
     */
    /* JADX WARN: Code restructure failed: missing block: B:483:0x0b3f, code lost:
    
        if (r1 != 'Z') goto L573;
     */
    /* JADX WARN: Code restructure failed: missing block: B:507:0x0bba, code lost:
    
        if (r7 != 'Z') goto L608;
     */
    /* JADX WARN: Code restructure failed: missing block: B:531:0x0c5b, code lost:
    
        if (r12 != 'Z') goto L642;
     */
    /* JADX WARN: Code restructure failed: missing block: B:614:0x0e4a, code lost:
    
        if (r14 != 'Z') goto L741;
     */
    /* JADX WARN: Code restructure failed: missing block: B:795:0x07f8, code lost:
    
        if (r14 != 'Z') goto L376;
     */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0536  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0577  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x05fa  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0688  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0703  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x07b1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0853 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:350:0x08cd  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x095b  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x09e5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0a89  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0b11 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0ed9  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x0b8c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0eec A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0c2d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0eee  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x0ca2  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x0d18  */
    /* JADX WARN: Removed duplicated region for block: B:573:0x0da3  */
    /* JADX WARN: Removed duplicated region for block: B:593:0x0e1c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0f65  */
    /* JADX WARN: Removed duplicated region for block: B:617:0x0e90  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0f68  */
    /* JADX WARN: Removed duplicated region for block: B:623:0x0f76  */
    /* JADX WARN: Removed duplicated region for block: B:690:0x1018  */
    /* JADX WARN: Removed duplicated region for block: B:695:0x1030  */
    /* JADX WARN: Removed duplicated region for block: B:707:0x0f70  */
    /* JADX WARN: Removed duplicated region for block: B:710:0x0e55  */
    /* JADX WARN: Removed duplicated region for block: B:712:0x0e58  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x0d0e  */
    /* JADX WARN: Removed duplicated region for block: B:725:0x0c66  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x0c69  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x0b4a  */
    /* JADX WARN: Removed duplicated region for block: B:747:0x0b4d  */
    /* JADX WARN: Removed duplicated region for block: B:752:0x0b07  */
    /* JADX WARN: Removed duplicated region for block: B:755:0x0a2e  */
    /* JADX WARN: Removed duplicated region for block: B:757:0x0a31  */
    /* JADX WARN: Removed duplicated region for block: B:772:0x088c  */
    /* JADX WARN: Removed duplicated region for block: B:774:0x088f  */
    /* JADX WARN: Removed duplicated region for block: B:798:0x0801  */
    /* JADX WARN: Removed duplicated region for block: B:800:0x0804  */
    /* JADX WARN: Removed duplicated region for block: B:821:0x067e  */
    /* JADX WARN: Removed duplicated region for block: B:823:0x052e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.time.ZonedDateTime parseZonedDateTime(char[] r55, int r56, int r57, java.time.ZoneId r58) {
        /*
            Method dump skipped, instructions count: 4228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseZonedDateTime(char[], int, int, java.time.ZoneId):java.time.ZonedDateTime");
    }

    static ZonedDateTime parseZonedDateTimeCookie(String str) {
        if (str.endsWith(" CST")) {
            DateTimeFormatter dateTimeFormatter = DATE_TIME_FORMATTER_COOKIE_LOCAL;
            if (dateTimeFormatter == null) {
                dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
                DATE_TIME_FORMATTER_COOKIE_LOCAL = dateTimeFormatter;
            }
            return ZonedDateTime.of(LocalDateTime.parse(str.substring(0, str.length() - 4), dateTimeFormatter), SHANGHAI_ZONE_ID);
        }
        DateTimeFormatter dateTimeFormatter2 = DATE_TIME_FORMATTER_COOKIE;
        if (dateTimeFormatter2 == null) {
            dateTimeFormatter2 = DateTimeFormatter.ofPattern("EEEE, dd-MMM-yyyy HH:mm:ss zzz", Locale.ENGLISH);
            DATE_TIME_FORMATTER_COOKIE = dateTimeFormatter2;
        }
        return ZonedDateTime.parse(str, dateTimeFormatter2);
    }

    public static ZoneId getZoneId(String str, ZoneId zoneId) {
        int indexOf;
        char charAt;
        if (str == null) {
            return zoneId != null ? zoneId : DEFAULT_ZONE_ID;
        }
        str.hashCode();
        switch (str) {
            case "000":
                return ZoneOffset.UTC;
            case "CST":
                return SHANGHAI_ZONE_ID;
            case "+08:00":
                return OFFSET_8_ZONE_ID;
            default:
                if (str.length() > 0 && (((charAt = str.charAt(0)) == '+' || charAt == '-') && str.charAt(str.length() - 1) != ']')) {
                    return ZoneOffset.of(str);
                }
                int indexOf2 = str.indexOf(91);
                if (indexOf2 > 0 && (indexOf = str.indexOf(93, indexOf2)) > 0) {
                    return ZoneId.of(str.substring(indexOf2 + 1, indexOf));
                }
                return ZoneId.of(str);
        }
    }

    public static long parseMillisYMDHMS19(String str, ZoneId zoneId) {
        char charAt;
        char charAt2;
        char charAt3;
        char charAt4;
        char charAt5;
        char charAt6;
        char charAt7;
        char charAt8;
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        char charAt9;
        char c6;
        char charAt10;
        char charAt11;
        char c7;
        char c8;
        int i;
        char c9;
        char c10;
        int totalSeconds;
        if (str == null) {
            return 0L;
        }
        if (JDKUtils.JVM_VERSION == 8) {
            char[] charArray = JDKUtils.getCharArray(str);
            if (charArray.length != 19) {
                throw new DateTimeParseException("illegal input " + str, str, 0);
            }
            charAt = charArray[0];
            charAt2 = charArray[1];
            char c11 = charArray[2];
            charAt3 = charArray[3];
            char c12 = charArray[4];
            charAt4 = charArray[5];
            char c13 = charArray[6];
            charAt5 = charArray[7];
            charAt6 = charArray[8];
            char c14 = charArray[9];
            char c15 = charArray[10];
            char c16 = charArray[11];
            char c17 = charArray[12];
            char c18 = charArray[13];
            charAt7 = charArray[14];
            charAt8 = charArray[15];
            charAt9 = charArray[16];
            c8 = c18;
            c = c11;
            c3 = c12;
            c5 = c14;
            c7 = c15;
            c2 = c16;
            c6 = c17;
            charAt10 = charArray[17];
            charAt11 = charArray[18];
            c4 = c13;
        } else if (JDKUtils.STRING_CODER != null && JDKUtils.STRING_CODER.applyAsInt(str) == 0 && JDKUtils.STRING_VALUE != null) {
            byte[] apply = JDKUtils.STRING_VALUE.apply(str);
            if (apply.length != 19) {
                throw new DateTimeParseException("illegal input " + str, str, 0);
            }
            charAt = (char) apply[0];
            charAt2 = (char) apply[1];
            char c19 = (char) apply[2];
            charAt3 = (char) apply[3];
            char c20 = (char) apply[4];
            charAt4 = (char) apply[5];
            char c21 = (char) apply[6];
            charAt5 = (char) apply[7];
            charAt6 = (char) apply[8];
            char c22 = (char) apply[9];
            char c23 = (char) apply[10];
            char c24 = (char) apply[11];
            char c25 = (char) apply[12];
            char c26 = (char) apply[13];
            charAt7 = (char) apply[14];
            charAt8 = (char) apply[15];
            char c27 = (char) apply[16];
            char c28 = (char) apply[17];
            charAt11 = (char) apply[18];
            c4 = c21;
            c5 = c22;
            c8 = c26;
            c = c19;
            c6 = c25;
            c2 = c24;
            c7 = c23;
            c3 = c20;
            charAt9 = c27;
            charAt10 = c28;
        } else {
            if (str.length() != 19) {
                throw new DateTimeParseException("illegal input " + str, str, 0);
            }
            charAt = str.charAt(0);
            charAt2 = str.charAt(1);
            char charAt12 = str.charAt(2);
            charAt3 = str.charAt(3);
            char charAt13 = str.charAt(4);
            charAt4 = str.charAt(5);
            char charAt14 = str.charAt(6);
            charAt5 = str.charAt(7);
            charAt6 = str.charAt(8);
            char charAt15 = str.charAt(9);
            char charAt16 = str.charAt(10);
            char charAt17 = str.charAt(11);
            char charAt18 = str.charAt(12);
            char charAt19 = str.charAt(13);
            charAt7 = str.charAt(14);
            charAt8 = str.charAt(15);
            c = charAt12;
            c2 = charAt17;
            c3 = charAt13;
            c4 = charAt14;
            c5 = charAt15;
            charAt9 = str.charAt(16);
            c6 = charAt18;
            charAt10 = str.charAt(17);
            charAt11 = str.charAt(18);
            c7 = charAt16;
            c8 = charAt19;
        }
        char c29 = c6;
        if (c3 != '-' || charAt5 != '-' || c7 != ' ' || c8 != ':' || charAt9 != ':') {
            throw new DateTimeParseException("illegal input", str, 0);
        }
        if (charAt < '0' || charAt > '9' || charAt2 < '0' || charAt2 > '9' || c < '0' || c > '9' || charAt3 < '0' || charAt3 > '9') {
            throw new DateTimeParseException("illegal input", str, 0);
        }
        int i2 = ((charAt - '0') * 1000) + ((charAt2 - '0') * 100) + ((c - '0') * 10) + (charAt3 - '0');
        if (charAt4 < '0' || charAt4 > '9' || c4 < '0' || c4 > '9') {
            throw new DateTimeParseException("illegal input", str, 0);
        }
        int i3 = ((charAt4 - '0') * 10) + (c4 - '0');
        if ((i3 == 0 && i2 != 0) || i3 > 12) {
            throw new DateTimeParseException("illegal input", str, 0);
        }
        if (charAt6 < '0' || charAt6 > '9' || c5 < '0' || c5 > '9') {
            throw new DateTimeParseException("illegal input", str, 0);
        }
        int i4 = ((charAt6 - '0') * 10) + (c5 - '0');
        if (i3 != 2) {
            i = (i3 == 4 || i3 == 6 || i3 == 9 || i3 == 11) ? 30 : 31;
        } else {
            i = ((i2 & 3) != 0 || (i2 % 100 == 0 && i2 % 400 != 0)) ? 28 : 29;
        }
        if ((i4 == 0 && i2 != 0) || i4 > i) {
            throw new DateTimeParseException("illegal input", str, 0);
        }
        if (c2 < '0' || c2 > '9' || c29 < '0' || c29 > '9') {
            throw new DateTimeParseException("illegal input", str, 0);
        }
        int i5 = ((c2 - '0') * 10) + (c29 - '0');
        char c30 = charAt7;
        if (c30 < '0' || c30 > '9' || (c9 = charAt8) < '0' || c9 > '9') {
            throw new DateTimeParseException("illegal input", str, 0);
        }
        int i6 = ((c30 - '0') * 10) + (c9 - '0');
        char c31 = charAt10;
        if (c31 < '0' || c31 > '9' || (c10 = charAt11) < '0' || c10 > '9') {
            throw new DateTimeParseException("illegal input", str, 0);
        }
        int i7 = ((c31 - '0') * 10) + (c10 - '0');
        if (i2 == 0 && i3 == 0 && i4 == 0) {
            i2 = 1970;
            i4 = 1;
            i3 = 1;
        }
        long calcEpochDay = (calcEpochDay(i2, i3, i4) * 86400) + (i5 * 3600) + (i6 * 60) + i7;
        ZoneId zoneId2 = zoneId == null ? DEFAULT_ZONE_ID : zoneId;
        if (!(zoneId2 == SHANGHAI_ZONE_ID || zoneId2.getRules() == SHANGHAI_ZONE_RULES) || calcEpochDay < 684900000) {
            totalSeconds = (zoneId2 == ZoneOffset.UTC || "UTC".equals(zoneId2.getId())) ? 0 : zoneId2.getRules().getOffset(LocalDateTime.of(LocalDate.of(i2, i3, i4), LocalTime.of(i5, i6, i7, 0))).getTotalSeconds();
        } else {
            totalSeconds = 28800;
        }
        return (calcEpochDay - totalSeconds) * 1000;
    }

    private static long calcEpochDay(int i, int i2, int i3) {
        long j = (i * 365) + (((i + 3) / 4) - ((i + 99) / 100)) + ((i + 399) / 400) + (((i2 * 367) - 362) / 12) + (i3 - 1);
        if (i2 > 2) {
            j = ((i & 3) != 0 || (i % 100 == 0 && i % 400 != 0)) ? j - 2 : j - 1;
        }
        return j - 719528;
    }

    /* JADX WARN: Removed duplicated region for block: B:138:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0264  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static long parseMillis19(java.lang.String r34, java.time.ZoneId r35, com.alibaba.fastjson2.util.DateUtils.DateTimeFormatPattern r36) {
        /*
            Method dump skipped, instructions count: 1001
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseMillis19(java.lang.String, java.time.ZoneId, com.alibaba.fastjson2.util.DateUtils$DateTimeFormatPattern):long");
    }

    /* renamed from: com.alibaba.fastjson2.util.DateUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$fastjson2$util$DateUtils$DateTimeFormatPattern;

        static {
            int[] iArr = new int[DateTimeFormatPattern.values().length];
            $SwitchMap$com$alibaba$fastjson2$util$DateUtils$DateTimeFormatPattern = iArr;
            try {
                iArr[DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$util$DateUtils$DateTimeFormatPattern[DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH_T.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$util$DateUtils$DateTimeFormatPattern[DateTimeFormatPattern.DATE_TIME_FORMAT_19_SLASH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$util$DateUtils$DateTimeFormatPattern[DateTimeFormatPattern.DATE_TIME_FORMAT_19_DOT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$util$DateUtils$DateTimeFormatPattern[DateTimeFormatPattern.DATE_FORMAT_10_DASH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$util$DateUtils$DateTimeFormatPattern[DateTimeFormatPattern.DATE_FORMAT_10_SLASH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0120  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static long parseMillis10(java.lang.String r21, java.time.ZoneId r22, com.alibaba.fastjson2.util.DateUtils.DateTimeFormatPattern r23) {
        /*
            Method dump skipped, instructions count: 594
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseMillis10(java.lang.String, java.time.ZoneId, com.alibaba.fastjson2.util.DateUtils$DateTimeFormatPattern):long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:153:0x020f, code lost:
    
        if (r7 == ':') goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:125:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0325  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long parseMillis19(java.lang.String r33, java.time.ZoneId r34) {
        /*
            Method dump skipped, instructions count: 1318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseMillis19(java.lang.String, java.time.ZoneId):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x01f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long parseMillis19(byte[] r24, int r25, java.time.ZoneId r26) {
        /*
            Method dump skipped, instructions count: 980
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseMillis19(byte[], int, java.time.ZoneId):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x01dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long parseMillis19(char[] r24, int r25, java.time.ZoneId r26) {
        /*
            Method dump skipped, instructions count: 959
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.parseMillis19(char[], int, java.time.ZoneId):long");
    }

    public static LocalDateTime localDateTime(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9, char c10, char c11, char c12, char c13, char c14) {
        if (c >= '0' && c <= '9' && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9') {
            int i = ((c - '0') * 1000) + ((c2 - '0') * 100) + ((c3 - '0') * 10) + (c4 - '0');
            if (c5 >= '0' && c5 <= '9' && c6 >= '0' && c6 <= '9') {
                int i2 = ((c5 - '0') * 10) + (c6 - '0');
                if (c7 >= '0' && c7 <= '9' && c8 >= '0' && c8 <= '9') {
                    int i3 = ((c7 - '0') * 10) + (c8 - '0');
                    if (c9 >= '0' && c9 <= '9' && c10 >= '0' && c10 <= '9') {
                        int i4 = ((c9 - '0') * 10) + (c10 - '0');
                        if (c11 >= '0' && c11 <= '9' && c12 >= '0' && c12 <= '9') {
                            int i5 = ((c11 - '0') * 10) + (c12 - '0');
                            if (c13 >= '0' && c13 <= '9' && c14 >= '0' && c14 <= '9') {
                                int i6 = ((c13 - '0') * 10) + (c14 - '0');
                                if ((i != 0 || i2 != 0 || i3 != 0 || i4 != 0 || i5 != 0 || i6 != 0) && i4 <= 24 && i5 <= 60 && i6 <= 60) {
                                    return LocalDateTime.of(i, i2, i3, i4, i5, i6, 0);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static LocalDateTime localDateTime(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9, char c10, char c11, char c12, char c13, char c14, char c15, char c16, char c17, char c18, char c19, char c20, char c21, char c22, char c23) {
        if (c >= '0' && c <= '9' && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9') {
            int i = ((c - '0') * 1000) + ((c2 - '0') * 100) + ((c3 - '0') * 10) + (c4 - '0');
            if (c5 >= '0' && c5 <= '9' && c6 >= '0' && c6 <= '9') {
                int i2 = ((c5 - '0') * 10) + (c6 - '0');
                if (c7 >= '0' && c7 <= '9' && c8 >= '0' && c8 <= '9') {
                    int i3 = ((c7 - '0') * 10) + (c8 - '0');
                    if (c9 >= '0' && c9 <= '9' && c10 >= '0' && c10 <= '9') {
                        int i4 = ((c9 - '0') * 10) + (c10 - '0');
                        if (c11 >= '0' && c11 <= '9' && c12 >= '0' && c12 <= '9') {
                            int i5 = ((c11 - '0') * 10) + (c12 - '0');
                            if (c13 >= '0' && c13 <= '9' && c14 >= '0' && c14 <= '9') {
                                int i6 = ((c13 - '0') * 10) + (c14 - '0');
                                if (c15 >= '0' && c15 <= '9' && c16 >= '0' && c16 <= '9' && c17 >= '0' && c17 <= '9' && c18 >= '0' && c18 <= '9' && c19 >= '0' && c19 <= '9' && c20 >= '0' && c20 <= '9' && c21 >= '0' && c21 <= '9' && c22 >= '0' && c22 <= '9' && c23 >= '0' && c23 <= '9') {
                                    return LocalDateTime.of(i, i2, i3, i4, i5, i6, ((c15 - '0') * 100000000) + ((c16 - '0') * 10000000) + ((c17 - '0') * DurationKt.NANOS_IN_MILLIS) + ((c18 - '0') * AndroidComposeViewAccessibilityDelegateCompat.ParcelSafeTextLength) + ((c19 - '0') * 10000) + ((c20 - '0') * 1000) + ((c21 - '0') * 100) + ((c22 - '0') * 10) + (c23 - '0'));
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static long millis(LocalDateTime localDateTime) {
        return millis(null, localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getNano());
    }

    public static long millis(LocalDateTime localDateTime, ZoneId zoneId) {
        return millis(zoneId, localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getNano());
    }

    public static long millis(ZoneId zoneId, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (zoneId == null) {
            zoneId = DEFAULT_ZONE_ID;
        }
        long calcEpochDay = (calcEpochDay(i, i2, i3) * 86400) + (i4 * 3600) + (i5 * 60) + i6;
        int i8 = 0;
        if ((zoneId == SHANGHAI_ZONE_ID || zoneId.getRules() == SHANGHAI_ZONE_RULES) && calcEpochDay >= 684900000) {
            i8 = 28800;
        } else if (zoneId != ZoneOffset.UTC && !"UTC".equals(zoneId.getId())) {
            i8 = zoneId.getRules().getOffset(LocalDateTime.of(LocalDate.of(i, i2, i3), LocalTime.of(i4, i5, i6, i7))).getTotalSeconds();
        }
        long j = (calcEpochDay - i8) * 1000;
        return i7 != 0 ? j + (i7 / DurationKt.NANOS_IN_MILLIS) : j;
    }

    public static long utcSeconds(int i, int i2, int i3, int i4, int i5, int i6) {
        return (calcEpochDay(i, i2, i3) * 86400) + (i4 * 3600) + (i5 * 60) + i6;
    }

    public static String formatYMDHMS19(Date date) {
        return formatYMDHMS19(date, DEFAULT_ZONE_ID);
    }

    public static String formatYMDHMS19(Date date, ZoneId zoneId) {
        long j;
        if (date == null) {
            return null;
        }
        long time = date.getTime();
        ZoneId zoneId2 = zoneId == null ? DEFAULT_ZONE_ID : zoneId;
        long floorDiv = Math.floorDiv(time, 1000L);
        long totalSeconds = floorDiv + (((zoneId2 == SHANGHAI_ZONE_ID || zoneId2.getRules() == SHANGHAI_ZONE_RULES) && floorDiv > 684900000) ? 28800 : zoneId2.getRules().getOffset(Instant.ofEpochMilli(time)).getTotalSeconds());
        long floorDiv2 = Math.floorDiv(totalSeconds, 86400L);
        int floorMod = (int) Math.floorMod(totalSeconds, 86400L);
        long j2 = 719468 + floorDiv2;
        if (j2 < 0) {
            long j3 = ((floorDiv2 + 719469) / 146097) - 1;
            j = j3 * 400;
            j2 += (-j3) * 146097;
        } else {
            j = 0;
        }
        long j4 = ((j2 * 400) + 591) / 146097;
        long j5 = j2 - ((((j4 * 365) + (j4 / 4)) - (j4 / 100)) + (j4 / 400));
        if (j5 < 0) {
            j4--;
            j5 = j2 - ((((365 * j4) + (j4 / 4)) - (j4 / 100)) + (j4 / 400));
        }
        int i = (int) j5;
        int i2 = ((i * 5) + 2) / Opcodes.IFEQ;
        int i3 = ((i2 + 2) % 12) + 1;
        int i4 = (i - (((i2 * 306) + 5) / 10)) + 1;
        long j6 = j4 + j + (i2 / 10);
        if (j6 < -999999999 || j6 > 999999999) {
            throw new DateTimeException("Invalid year " + j6);
        }
        int i5 = (int) j6;
        long j7 = floorMod;
        if (j7 < 0 || j7 > 86399) {
            throw new DateTimeException("Invalid secondOfDay " + j7);
        }
        int i6 = (int) (j7 / 3600);
        long j8 = j7 - (i6 * 3600);
        int i7 = (int) (j8 / 60);
        int i8 = (int) (j8 - (i7 * 60));
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            byte[] bArr = new byte[19];
            IOUtils.writeLocalDate(bArr, 0, i5, i3, i4);
            bArr[10] = 32;
            IOUtils.writeLocalTime(bArr, 11, i6, i7, i8);
            return JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        }
        char[] cArr = new char[19];
        IOUtils.writeLocalDate(cArr, 0, i5, i3, i4);
        cArr[10] = ' ';
        IOUtils.writeLocalTime(cArr, 11, i6, i7, i8);
        if (JDKUtils.STRING_CREATOR_JDK8 != null) {
            return JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
        }
        return new String(cArr);
    }

    public static String formatYMD8(Date date) {
        if (date == null) {
            return null;
        }
        return formatYMD8(date.getTime(), DEFAULT_ZONE_ID);
    }

    public static String formatYMD8(long j, ZoneId zoneId) {
        int shanghaiZoneOffsetTotalSeconds;
        long j2;
        String str;
        String str2;
        long floorDiv = Math.floorDiv(j, 1000L);
        ZoneId zoneId2 = zoneId == null ? DEFAULT_ZONE_ID : zoneId;
        if (zoneId2 == SHANGHAI_ZONE_ID || zoneId2.getRules() == SHANGHAI_ZONE_RULES) {
            shanghaiZoneOffsetTotalSeconds = getShanghaiZoneOffsetTotalSeconds(floorDiv);
        } else {
            shanghaiZoneOffsetTotalSeconds = zoneId2.getRules().getOffset(Instant.ofEpochMilli(j)).getTotalSeconds();
        }
        long floorDiv2 = Math.floorDiv(floorDiv + shanghaiZoneOffsetTotalSeconds, 86400L);
        int i = (int) ((floorDiv2 - LOCAL_EPOCH_DAY) + 128);
        String[] strArr = CacheDate8.CACHE;
        if (i >= 0 && i < strArr.length && (str2 = strArr[i]) != null) {
            return str2;
        }
        long j3 = 719468 + floorDiv2;
        if (j3 < 0) {
            long j4 = ((floorDiv2 + 719469) / 146097) - 1;
            j2 = j4 * 400;
            j3 += (-j4) * 146097;
        } else {
            j2 = 0;
        }
        long j5 = ((j3 * 400) + 591) / 146097;
        long j6 = j3 - ((((j5 * 365) + (j5 / 4)) - (j5 / 100)) + (j5 / 400));
        if (j6 < 0) {
            j5--;
            j6 = j3 - ((((365 * j5) + (j5 / 4)) - (j5 / 100)) + (j5 / 400));
        }
        int i2 = (int) j6;
        int i3 = ((i2 * 5) + 2) / Opcodes.IFEQ;
        int i4 = ((i3 + 2) % 12) + 1;
        int i5 = (i2 - (((i3 * 306) + 5) / 10)) + 1;
        long j7 = j5 + j2 + (i3 / 10);
        if (j7 < -999999999 || j7 > 999999999) {
            throw new DateTimeException("Invalid year " + j7);
        }
        int i6 = (int) j7;
        int i7 = i6 / 100;
        int i8 = i6 - (i7 * 100);
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            byte[] bArr = new byte[8];
            IOUtils.writeDigitPair(bArr, 0, i7);
            IOUtils.writeDigitPair(bArr, 2, i8);
            IOUtils.writeDigitPair(bArr, 4, i4);
            IOUtils.writeDigitPair(bArr, 6, i5);
            str = JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        } else {
            char[] cArr = new char[8];
            IOUtils.writeDigitPair(cArr, 0, i7);
            IOUtils.writeDigitPair(cArr, 2, i8);
            IOUtils.writeDigitPair(cArr, 4, i4);
            IOUtils.writeDigitPair(cArr, 6, i5);
            if (JDKUtils.STRING_CREATOR_JDK8 != null) {
                str = JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
            } else {
                str = new String(cArr);
            }
        }
        if (i >= 0 && i < strArr.length) {
            strArr[i] = str;
        }
        return str;
    }

    public static String formatYMD10(int i, int i2, int i3) {
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            byte[] bArr = new byte[10];
            IOUtils.writeLocalDate(bArr, 0, i, i2, i3);
            return JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        }
        char[] cArr = new char[10];
        IOUtils.writeLocalDate(cArr, 0, i, i2, i3);
        if (JDKUtils.STRING_CREATOR_JDK8 != null) {
            return JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
        }
        return new String(cArr);
    }

    public static String formatYMD10(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return formatYMD10(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    }

    public static String formatYMD10(Date date) {
        if (date == null) {
            return null;
        }
        return formatYMD10(date.getTime(), DEFAULT_ZONE_ID);
    }

    public static String formatYMD8(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        int dayOfMonth = localDate.getDayOfMonth();
        int i = year / 100;
        int i2 = year - (i * 100);
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            byte[] bArr = new byte[8];
            IOUtils.writeDigitPair(bArr, 0, i);
            IOUtils.writeDigitPair(bArr, 2, i2);
            IOUtils.writeDigitPair(bArr, 4, monthValue);
            IOUtils.writeDigitPair(bArr, 6, dayOfMonth);
            return JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        }
        char[] cArr = new char[8];
        IOUtils.writeDigitPair(cArr, 0, i);
        IOUtils.writeDigitPair(cArr, 2, i2);
        IOUtils.writeDigitPair(cArr, 4, monthValue);
        IOUtils.writeDigitPair(cArr, 6, dayOfMonth);
        if (JDKUtils.STRING_CREATOR_JDK8 != null) {
            return JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
        }
        return new String(cArr);
    }

    public static String formatYMD10(long j, ZoneId zoneId) {
        int shanghaiZoneOffsetTotalSeconds;
        long j2;
        String str;
        String str2;
        ZoneId zoneId2 = zoneId == null ? DEFAULT_ZONE_ID : zoneId;
        long floorDiv = Math.floorDiv(j, 1000L);
        if (zoneId2 == SHANGHAI_ZONE_ID || zoneId2.getRules() == SHANGHAI_ZONE_RULES) {
            shanghaiZoneOffsetTotalSeconds = getShanghaiZoneOffsetTotalSeconds(floorDiv);
        } else {
            shanghaiZoneOffsetTotalSeconds = zoneId2.getRules().getOffset(Instant.ofEpochMilli(j)).getTotalSeconds();
        }
        long floorDiv2 = Math.floorDiv(floorDiv + shanghaiZoneOffsetTotalSeconds, 86400L);
        int i = (int) ((floorDiv2 - LOCAL_EPOCH_DAY) + 128);
        String[] strArr = CacheDate10.CACHE;
        if (i >= 0 && i < strArr.length && (str2 = strArr[i]) != null) {
            return str2;
        }
        long j3 = 719468 + floorDiv2;
        if (j3 < 0) {
            long j4 = ((floorDiv2 + 719469) / 146097) - 1;
            j2 = j4 * 400;
            j3 += (-j4) * 146097;
        } else {
            j2 = 0;
        }
        long j5 = ((j3 * 400) + 591) / 146097;
        long j6 = j3 - ((((j5 * 365) + (j5 / 4)) - (j5 / 100)) + (j5 / 400));
        if (j6 < 0) {
            j5--;
            j6 = j3 - ((((365 * j5) + (j5 / 4)) - (j5 / 100)) + (j5 / 400));
        }
        int i2 = (int) j6;
        int i3 = ((i2 * 5) + 2) / Opcodes.IFEQ;
        int i4 = ((i3 + 2) % 12) + 1;
        int i5 = (i2 - (((i3 * 306) + 5) / 10)) + 1;
        long j7 = j5 + j2 + (i3 / 10);
        if (j7 < -999999999 || j7 > 999999999) {
            throw new DateTimeException("Invalid year " + j7);
        }
        int i6 = (int) j7;
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            byte[] bArr = new byte[10];
            IOUtils.writeLocalDate(bArr, 0, i6, i4, i5);
            str = JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        } else {
            char[] cArr = new char[10];
            IOUtils.writeLocalDate(cArr, 0, i6, i4, i5);
            if (JDKUtils.STRING_CREATOR_JDK8 != null) {
                str = JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
            } else {
                str = new String(cArr);
            }
        }
        if (i >= 0 && i < strArr.length) {
            strArr[i] = str;
        }
        return str;
    }

    public static String format(Date date, String str) {
        if (date == null) {
            return null;
        }
        if (str == null) {
            return format(date);
        }
        str.hashCode();
        switch (str) {
            case "yyyyMMdd":
                return formatYMD8(date.getTime(), DEFAULT_ZONE_ID);
            case "yyyy-MM-dd":
                return formatYMD10(date.getTime(), DEFAULT_ZONE_ID);
            case "yyyy/MM/dd":
                return format(date.getTime(), DateTimeFormatPattern.DATE_FORMAT_10_SLASH);
            case "yyyy-MM-dd HH:mm:ss":
                return format(date.getTime(), DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH);
            case "dd.MM.yyyy HH:mm:ss":
                return format(date.getTime(), DateTimeFormatPattern.DATE_TIME_FORMAT_19_DOT);
            case "yyyy-MM-dd'T'HH:mm:ss":
            case "yyyy-MM-ddTHH:mm:ss":
                return format(date.getTime(), DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH_T);
            case "dd.MM.yyyy":
                return format(date.getTime(), DateTimeFormatPattern.DATE_FORMAT_10_DOT);
            default:
                return DateTimeFormatter.ofPattern(str).format(Instant.ofEpochMilli(date.getTime()).atZone(DEFAULT_ZONE_ID));
        }
    }

    public static String formatYMDHMS19(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            return null;
        }
        return format(zonedDateTime.getYear(), zonedDateTime.getMonthValue(), zonedDateTime.getDayOfMonth(), zonedDateTime.getHour(), zonedDateTime.getMinute(), zonedDateTime.getSecond(), DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH);
    }

    public static String format(ZonedDateTime zonedDateTime, String str) {
        int year;
        int monthValue;
        int dayOfMonth;
        if (zonedDateTime == null) {
            return null;
        }
        year = zonedDateTime.getYear();
        monthValue = zonedDateTime.getMonthValue();
        dayOfMonth = zonedDateTime.getDayOfMonth();
        str.hashCode();
        switch (str) {
            case "yyyy-MM-dd":
                return format(year, monthValue, dayOfMonth, DateTimeFormatPattern.DATE_FORMAT_10_DASH);
            case "yyyy/MM/dd":
                return format(year, monthValue, dayOfMonth, DateTimeFormatPattern.DATE_FORMAT_10_SLASH);
            case "yyyy-MM-dd HH:mm:ss":
                return format(year, monthValue, dayOfMonth, zonedDateTime.getHour(), zonedDateTime.getMinute(), zonedDateTime.getSecond(), DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH);
            case "yyyy-MM-dd'T'HH:mm:ss":
            case "yyyy-MM-ddTHH:mm:ss":
                return format(year, monthValue, dayOfMonth, zonedDateTime.getHour(), zonedDateTime.getMinute(), zonedDateTime.getSecond(), DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH_T);
            case "dd.MM.yyyy":
                return format(year, monthValue, dayOfMonth, DateTimeFormatPattern.DATE_FORMAT_10_DOT);
            default:
                return DateTimeFormatter.ofPattern(str).format(zonedDateTime);
        }
    }

    public static String formatYMDHMS19(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            byte[] bArr = new byte[19];
            IOUtils.writeLocalDate(bArr, 0, year, monthValue, dayOfMonth);
            bArr[10] = 32;
            IOUtils.writeLocalTime(bArr, 11, hour, minute, second);
            return JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        }
        char[] cArr = new char[19];
        IOUtils.writeLocalDate(cArr, 0, year, monthValue, dayOfMonth);
        cArr[10] = ' ';
        IOUtils.writeLocalTime(cArr, 11, hour, minute, second);
        if (JDKUtils.STRING_CREATOR_JDK8 != null) {
            return JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
        }
        return new String(cArr);
    }

    public static String format(LocalDateTime localDateTime, String str) {
        int year;
        int monthValue;
        int dayOfMonth;
        if (localDateTime == null) {
            return null;
        }
        year = localDateTime.getYear();
        monthValue = localDateTime.getMonthValue();
        dayOfMonth = localDateTime.getDayOfMonth();
        str.hashCode();
        switch (str) {
            case "yyyy-MM-dd":
                return formatYMD10(year, monthValue, dayOfMonth);
            case "yyyy/MM/dd":
                return format(year, monthValue, dayOfMonth, DateTimeFormatPattern.DATE_FORMAT_10_SLASH);
            case "yyyy-MM-dd HH:mm:ss":
                return format(year, monthValue, dayOfMonth, localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH);
            case "yyyy-MM-dd'T'HH:mm:ss":
            case "yyyy-MM-ddTHH:mm:ss":
                return format(year, monthValue, dayOfMonth, localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH_T);
            case "dd.MM.yyyy":
                return format(year, monthValue, dayOfMonth, DateTimeFormatPattern.DATE_FORMAT_10_DOT);
            default:
                return DateTimeFormatter.ofPattern(str).format(localDateTime);
        }
    }

    public static String formatYMDHMS19(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        int dayOfMonth = localDate.getDayOfMonth();
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            byte[] bArr = new byte[19];
            IOUtils.writeLocalDate(bArr, 0, year, monthValue, dayOfMonth);
            bArr[10] = 32;
            IOUtils.writeLocalTime(bArr, 11, 0, 0, 0);
            return JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        }
        char[] cArr = new char[19];
        IOUtils.writeLocalDate(cArr, 0, year, monthValue, dayOfMonth);
        cArr[10] = ' ';
        IOUtils.writeLocalTime(cArr, 11, 0, 0, 0);
        if (JDKUtils.STRING_CREATOR_JDK8 != null) {
            return JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
        }
        return new String(cArr);
    }

    public static String format(LocalDate localDate, String str) {
        int year;
        int monthValue;
        int dayOfMonth;
        if (localDate == null) {
            return null;
        }
        year = localDate.getYear();
        monthValue = localDate.getMonthValue();
        dayOfMonth = localDate.getDayOfMonth();
        str.hashCode();
        switch (str) {
            case "yyyy-MM-dd":
                return format(year, monthValue, dayOfMonth, DateTimeFormatPattern.DATE_FORMAT_10_DASH);
            case "yyyy/MM/dd":
                return format(year, monthValue, dayOfMonth, DateTimeFormatPattern.DATE_FORMAT_10_SLASH);
            case "yyyy-MM-dd HH:mm:ss":
                return format(year, monthValue, dayOfMonth, 0, 0, 0, DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH);
            case "yyyy-MM-dd'T'HH:mm:ss":
            case "yyyy-MM-ddTHH:mm:ss":
                return format(year, monthValue, dayOfMonth, 0, 0, 0, DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH_T);
            case "dd.MM.yyyy":
                return format(year, monthValue, dayOfMonth, DateTimeFormatPattern.DATE_FORMAT_10_DOT);
            default:
                return DateTimeFormatter.ofPattern(str).format(localDate);
        }
    }

    public static String format(int i, int i2, int i3) {
        return format(i, i2, i3, DateTimeFormatPattern.DATE_FORMAT_10_DASH);
    }

    public static String format(int i, int i2, int i3, DateTimeFormatPattern dateTimeFormatPattern) {
        int i4 = i / 100;
        int i5 = i - (i4 * 100);
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            byte[] bArr = new byte[10];
            if (dateTimeFormatPattern == DateTimeFormatPattern.DATE_FORMAT_10_DOT) {
                IOUtils.writeDigitPair(bArr, 0, i3);
                bArr[2] = 46;
                IOUtils.writeDigitPair(bArr, 3, i2);
                bArr[5] = 46;
                IOUtils.writeDigitPair(bArr, 6, i4);
                IOUtils.writeDigitPair(bArr, 8, i5);
            } else {
                byte b = (byte) (dateTimeFormatPattern != DateTimeFormatPattern.DATE_FORMAT_10_DASH ? '/' : '-');
                IOUtils.writeDigitPair(bArr, 0, i4);
                IOUtils.writeDigitPair(bArr, 2, i5);
                bArr[4] = b;
                IOUtils.writeDigitPair(bArr, 5, i2);
                bArr[7] = b;
                IOUtils.writeDigitPair(bArr, 8, i3);
            }
            return JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        }
        char[] cArr = new char[10];
        if (dateTimeFormatPattern == DateTimeFormatPattern.DATE_FORMAT_10_DOT) {
            IOUtils.writeDigitPair(cArr, 0, i3);
            cArr[2] = '.';
            IOUtils.writeDigitPair(cArr, 3, i2);
            cArr[5] = '.';
            IOUtils.writeDigitPair(cArr, 6, i4);
            IOUtils.writeDigitPair(cArr, 8, i5);
        } else {
            char c = dateTimeFormatPattern != DateTimeFormatPattern.DATE_FORMAT_10_DASH ? '/' : '-';
            IOUtils.writeDigitPair(cArr, 0, i4);
            IOUtils.writeDigitPair(cArr, 2, i5);
            cArr[4] = c;
            IOUtils.writeDigitPair(cArr, 5, i2);
            cArr[7] = c;
            IOUtils.writeDigitPair(cArr, 8, i3);
        }
        if (JDKUtils.STRING_CREATOR_JDK8 != null) {
            return JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
        }
        return new String(cArr);
    }

    public static String format(long j) {
        return format(j, DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH);
    }

    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return format(date.getTime(), DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH);
    }

    public static String format(long j, DateTimeFormatPattern dateTimeFormatPattern) {
        int shanghaiZoneOffsetTotalSeconds;
        long j2;
        ZoneId zoneId = DEFAULT_ZONE_ID;
        long floorDiv = Math.floorDiv(j, 1000L);
        if (zoneId == SHANGHAI_ZONE_ID || zoneId.getRules() == SHANGHAI_ZONE_RULES) {
            shanghaiZoneOffsetTotalSeconds = getShanghaiZoneOffsetTotalSeconds(floorDiv);
        } else {
            shanghaiZoneOffsetTotalSeconds = zoneId.getRules().getOffset(Instant.ofEpochMilli(j)).getTotalSeconds();
        }
        long j3 = floorDiv + shanghaiZoneOffsetTotalSeconds;
        long floorDiv2 = Math.floorDiv(j3, 86400L);
        int floorMod = (int) Math.floorMod(j3, 86400L);
        long j4 = 719468 + floorDiv2;
        if (j4 < 0) {
            long j5 = ((floorDiv2 + 719469) / 146097) - 1;
            j2 = j5 * 400;
            j4 += (-j5) * 146097;
        } else {
            j2 = 0;
        }
        long j6 = ((j4 * 400) + 591) / 146097;
        long j7 = j4 - ((((j6 * 365) + (j6 / 4)) - (j6 / 100)) + (j6 / 400));
        if (j7 < 0) {
            j6--;
            j7 = j4 - ((((365 * j6) + (j6 / 4)) - (j6 / 100)) + (j6 / 400));
        }
        int i = (int) j7;
        int i2 = ((i * 5) + 2) / Opcodes.IFEQ;
        int i3 = ((i2 + 2) % 12) + 1;
        int i4 = (i - (((i2 * 306) + 5) / 10)) + 1;
        long j8 = j6 + j2 + (i2 / 10);
        if (j8 < -999999999 || j8 > 999999999) {
            throw new DateTimeException("Invalid year " + j8);
        }
        int i5 = (int) j8;
        if (dateTimeFormatPattern == DateTimeFormatPattern.DATE_FORMAT_10_DASH || dateTimeFormatPattern == DateTimeFormatPattern.DATE_FORMAT_10_SLASH || dateTimeFormatPattern == DateTimeFormatPattern.DATE_FORMAT_10_DOT) {
            return format(i5, i3, i4, dateTimeFormatPattern);
        }
        long j9 = floorMod;
        if (j9 < 0 || j9 > 86399) {
            throw new DateTimeException("Invalid secondOfDay " + j9);
        }
        int i6 = (int) (j9 / 3600);
        long j10 = j9 - (i6 * 3600);
        return format(i5, i3, i4, i6, (int) (j10 / 60), (int) (j10 - (r4 * 60)), dateTimeFormatPattern);
    }

    public static String format(int i, int i2, int i3, int i4, int i5, int i6) {
        return format(i, i2, i3, i4, i5, i6, DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH);
    }

    static String format(int i, int i2, int i3, int i4, int i5, int i6, DateTimeFormatPattern dateTimeFormatPattern) {
        int i7 = i / 100;
        int i8 = i - (i7 * 100);
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            byte[] bArr = new byte[19];
            if (dateTimeFormatPattern == DateTimeFormatPattern.DATE_TIME_FORMAT_19_DOT) {
                IOUtils.writeDigitPair(bArr, 0, i3);
                bArr[2] = 46;
                IOUtils.writeDigitPair(bArr, 3, i2);
                bArr[5] = 46;
                IOUtils.writeDigitPair(bArr, 6, i7);
                IOUtils.writeDigitPair(bArr, 8, i8);
                bArr[10] = 32;
            } else {
                int i9 = dateTimeFormatPattern == DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH ? 32 : 84;
                byte b = (byte) (dateTimeFormatPattern == DateTimeFormatPattern.DATE_TIME_FORMAT_19_SLASH ? 47 : 45);
                IOUtils.writeDigitPair(bArr, 0, i7);
                IOUtils.writeDigitPair(bArr, 2, i8);
                bArr[4] = b;
                IOUtils.writeDigitPair(bArr, 5, i2);
                bArr[7] = b;
                IOUtils.writeDigitPair(bArr, 8, i3);
                bArr[10] = (byte) i9;
            }
            IOUtils.writeLocalTime(bArr, 11, i4, i5, i6);
            return JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        }
        char[] cArr = new char[19];
        if (dateTimeFormatPattern == DateTimeFormatPattern.DATE_TIME_FORMAT_19_DOT) {
            IOUtils.writeDigitPair(cArr, 0, i3);
            cArr[2] = '.';
            IOUtils.writeDigitPair(cArr, 3, i2);
            cArr[5] = '.';
            IOUtils.writeDigitPair(cArr, 6, i7);
            IOUtils.writeDigitPair(cArr, 8, i8);
            cArr[10] = ' ';
        } else {
            char c = dateTimeFormatPattern != DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH ? 'T' : ' ';
            char c2 = dateTimeFormatPattern == DateTimeFormatPattern.DATE_TIME_FORMAT_19_SLASH ? '/' : '-';
            IOUtils.writeDigitPair(cArr, 0, i7);
            IOUtils.writeDigitPair(cArr, 2, i8);
            cArr[4] = c2;
            IOUtils.writeDigitPair(cArr, 5, i2);
            cArr[7] = c2;
            IOUtils.writeDigitPair(cArr, 8, i3);
            cArr[10] = c;
        }
        IOUtils.writeLocalTime(cArr, 11, i4, i5, i6);
        if (JDKUtils.STRING_CREATOR_JDK8 != null) {
            return JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
        }
        return new String(cArr);
    }

    public static String toString(Date date) {
        return toString(date.getTime(), false, DEFAULT_ZONE_ID);
    }

    public static String toString(long j, boolean z, ZoneId zoneId) {
        int shanghaiZoneOffsetTotalSeconds;
        long j2;
        int i;
        long floorDiv = Math.floorDiv(j, 1000L);
        if (zoneId == SHANGHAI_ZONE_ID || zoneId.getRules() == SHANGHAI_ZONE_RULES) {
            shanghaiZoneOffsetTotalSeconds = getShanghaiZoneOffsetTotalSeconds(floorDiv);
        } else {
            shanghaiZoneOffsetTotalSeconds = zoneId.getRules().getOffset(Instant.ofEpochMilli(j)).getTotalSeconds();
        }
        long j3 = floorDiv + shanghaiZoneOffsetTotalSeconds;
        long floorDiv2 = Math.floorDiv(j3, 86400L);
        int floorMod = (int) Math.floorMod(j3, 86400L);
        long j4 = 719468 + floorDiv2;
        if (j4 < 0) {
            long j5 = ((floorDiv2 + 719469) / 146097) - 1;
            j2 = j5 * 400;
            j4 += (-j5) * 146097;
        } else {
            j2 = 0;
        }
        long j6 = ((j4 * 400) + 591) / 146097;
        long j7 = j4 - ((((j6 * 365) + (j6 / 4)) - (j6 / 100)) + (j6 / 400));
        if (j7 < 0) {
            j6--;
            j7 = j4 - ((((365 * j6) + (j6 / 4)) - (j6 / 100)) + (j6 / 400));
        }
        int i2 = (int) j7;
        int i3 = ((i2 * 5) + 2) / Opcodes.IFEQ;
        int i4 = ((i3 + 2) % 12) + 1;
        int i5 = (i2 - (((i3 * 306) + 5) / 10)) + 1;
        long j8 = j6 + j2 + (i3 / 10);
        if (j8 < -999999999 || j8 > 999999999) {
            throw new DateTimeException("Invalid year " + j8);
        }
        int i6 = (int) j8;
        long j9 = floorMod;
        if (j9 < 0 || j9 > 86399) {
            throw new DateTimeException("Invalid secondOfDay " + j9);
        }
        int i7 = (int) (j9 / 3600);
        long j10 = j9 - (i7 * 3600);
        int i8 = (int) (j10 / 60);
        int i9 = (int) (j10 - (i8 * 60));
        int floorMod2 = (int) Math.floorMod(j, 1000L);
        if (floorMod2 == 0) {
            i = 0;
        } else {
            i = 4;
            if (floorMod2 >= 10) {
                if (floorMod2 % 100 == 0) {
                    i = 2;
                } else if (floorMod2 % 10 == 0) {
                    i = 3;
                }
            }
        }
        int i10 = i + 19;
        int i11 = (z ? shanghaiZoneOffsetTotalSeconds == 0 ? 1 : 6 : 0) + i10;
        if (JDKUtils.STRING_CREATOR_JDK8 != null) {
            char[] cArr = new char[i11];
            IOUtils.writeLocalDate(cArr, 0, i6, i4, i5);
            cArr[10] = ' ';
            IOUtils.writeLocalTime(cArr, 11, i7, i8, i9);
            if (i > 0) {
                cArr[19] = '.';
                for (int i12 = 20; i12 < i11; i12++) {
                    cArr[i12] = '0';
                }
                if (floorMod2 < 10) {
                    IOUtils.getChars(floorMod2, i10, cArr);
                } else if (floorMod2 % 100 == 0) {
                    IOUtils.getChars(floorMod2 / 100, i10, cArr);
                } else if (floorMod2 % 10 == 0) {
                    IOUtils.getChars(floorMod2 / 10, i10, cArr);
                } else {
                    IOUtils.getChars(floorMod2, i10, cArr);
                }
            }
            if (z) {
                int i13 = shanghaiZoneOffsetTotalSeconds / 3600;
                if (shanghaiZoneOffsetTotalSeconds == 0) {
                    cArr[i10] = Matrix.MATRIX_TYPE_ZERO;
                } else {
                    int abs = Math.abs(i13);
                    if (i13 >= 0) {
                        cArr[i10] = '+';
                    } else {
                        cArr[i10] = '-';
                    }
                    cArr[i + 20] = '0';
                    int i14 = i + 22;
                    IOUtils.getChars(abs, i14, cArr);
                    cArr[i14] = ':';
                    cArr[i + 23] = '0';
                    int i15 = (shanghaiZoneOffsetTotalSeconds - (i13 * 3600)) / 60;
                    if (i15 < 0) {
                        i15 = -i15;
                    }
                    IOUtils.getChars(i15, i11, cArr);
                }
            }
            return JDKUtils.STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
        }
        byte[] bArr = new byte[i11];
        IOUtils.writeLocalDate(bArr, 0, i6, i4, i5);
        bArr[10] = 32;
        IOUtils.writeLocalTime(bArr, 11, i7, i8, i9);
        if (i > 0) {
            bArr[19] = 46;
            for (int i16 = 20; i16 < i11; i16++) {
                bArr[i16] = JSONB.Constants.BC_INT32_BYTE_MIN;
            }
            if (floorMod2 < 10) {
                IOUtils.getChars(floorMod2, i10, bArr);
            } else if (floorMod2 % 100 == 0) {
                IOUtils.getChars(floorMod2 / 100, i10, bArr);
            } else if (floorMod2 % 10 == 0) {
                IOUtils.getChars(floorMod2 / 10, i10, bArr);
            } else {
                IOUtils.getChars(floorMod2, i10, bArr);
            }
        }
        if (z) {
            int i17 = shanghaiZoneOffsetTotalSeconds / 3600;
            if (shanghaiZoneOffsetTotalSeconds == 0) {
                bArr[i10] = 90;
            } else {
                int abs2 = Math.abs(i17);
                if (i17 >= 0) {
                    bArr[i10] = 43;
                } else {
                    bArr[i10] = 45;
                }
                bArr[i + 20] = JSONB.Constants.BC_INT32_BYTE_MIN;
                int i18 = i + 22;
                IOUtils.getChars(abs2, i18, bArr);
                bArr[i18] = 58;
                bArr[i + 23] = JSONB.Constants.BC_INT32_BYTE_MIN;
                int i19 = (shanghaiZoneOffsetTotalSeconds - (i17 * 3600)) / 60;
                if (i19 < 0) {
                    i19 = -i19;
                }
                IOUtils.getChars(i19, i11, bArr);
            }
        }
        if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            return JDKUtils.STRING_CREATOR_JDK11.apply(bArr, JDKUtils.LATIN1);
        }
        return new String(bArr, 0, i11, StandardCharsets.ISO_8859_1);
    }

    private static int month(byte b, byte b2, byte b3) {
        return month((char) b, (char) b2, (char) b3);
    }

    public enum DateTimeFormatPattern {
        DATE_FORMAT_10_DASH(com.wifiled.baselib.utils.DateUtils.DATE_FORMAT, 10),
        DATE_FORMAT_10_SLASH("yyyy/MM/dd", 10),
        DATE_FORMAT_10_DOT("dd.MM.yyyy", 10),
        DATE_TIME_FORMAT_19_DASH(com.wifiled.baselib.utils.DateUtils.TIME_FORMAT, 19),
        DATE_TIME_FORMAT_19_DASH_T("yyyy-MM-dd'T'HH:mm:ss", 19),
        DATE_TIME_FORMAT_19_SLASH("yyyy/MM/dd HH:mm:ss", 19),
        DATE_TIME_FORMAT_19_DOT("dd.MM.yyyy HH:mm:ss", 19);

        public final int length;
        public final String pattern;

        DateTimeFormatPattern(String str, int i) {
            this.pattern = str;
            this.length = i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0094, code lost:
    
        r15 = 29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isLocalDate(java.lang.String r15) {
        /*
            r0 = 0
            if (r15 == 0) goto Lb1
            boolean r1 = r15.isEmpty()
            if (r1 == 0) goto Lb
            goto Lb1
        Lb:
            int r1 = r15.length()
            r2 = 9
            r3 = 1
            r4 = 10
            if (r1 != r4) goto L9b
            r1 = 4
            char r5 = r15.charAt(r1)
            r6 = 45
            if (r5 != r6) goto L9b
            r5 = 7
            char r5 = r15.charAt(r5)
            if (r5 != r6) goto L9b
            char r5 = r15.charAt(r0)
            char r6 = r15.charAt(r3)
            r7 = 2
            char r8 = r15.charAt(r7)
            r9 = 3
            char r10 = r15.charAt(r9)
            r11 = 5
            char r11 = r15.charAt(r11)
            r12 = 6
            char r13 = r15.charAt(r12)
            r14 = 8
            char r14 = r15.charAt(r14)
            char r15 = r15.charAt(r2)
            int r5 = r5 + (-48)
            int r5 = r5 * 1000
            int r6 = r6 + (-48)
            int r6 = r6 * 100
            int r5 = r5 + r6
            int r8 = r8 + (-48)
            int r8 = r8 * r4
            int r5 = r5 + r8
            int r10 = r10 + (-48)
            int r5 = r5 + r10
            int r11 = r11 + (-48)
            int r11 = r11 * r4
            int r13 = r13 + (-48)
            int r11 = r11 + r13
            int r14 = r14 + (-48)
            int r14 = r14 * r4
            int r15 = r15 + (-48)
            int r14 = r14 + r15
            r15 = 12
            if (r11 <= r15) goto L6d
            return r0
        L6d:
            r15 = 28
            if (r14 <= r15) goto L9a
            if (r11 == r7) goto L83
            if (r11 == r1) goto L80
            if (r11 == r12) goto L80
            if (r11 == r2) goto L80
            r15 = 11
            if (r11 == r15) goto L80
            r15 = 31
            goto L96
        L80:
            r15 = 30
            goto L96
        L83:
            r1 = r5 & 15
            if (r1 != 0) goto L8c
            r1 = r5 & 3
            if (r1 != 0) goto L96
            goto L94
        L8c:
            r1 = r5 & 3
            if (r1 != 0) goto L96
            int r5 = r5 % 100
            if (r5 == 0) goto L96
        L94:
            r15 = 29
        L96:
            if (r14 > r15) goto L99
            return r3
        L99:
            return r0
        L9a:
            return r3
        L9b:
            int r1 = r15.length()
            if (r1 < r2) goto Lb1
            int r1 = r15.length()
            r2 = 40
            if (r1 <= r2) goto Laa
            goto Lb1
        Laa:
            java.time.LocalDate r15 = parseLocalDate(r15)     // Catch: java.lang.Throwable -> Lb1
            if (r15 == 0) goto Lb1
            return r3
        Lb1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.isLocalDate(java.lang.String):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x012f, code lost:
    
        r0 = 29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isDate(java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.DateUtils.isDate(java.lang.String):boolean");
    }

    public static boolean isLocalTime(String str) {
        if (str != null && !str.isEmpty()) {
            if (str.length() == 8 && str.charAt(2) == ':' && str.charAt(5) == ':') {
                char charAt = str.charAt(0);
                char charAt2 = str.charAt(1);
                char charAt3 = str.charAt(3);
                char charAt4 = str.charAt(4);
                char charAt5 = str.charAt(6);
                char charAt6 = str.charAt(7);
                return charAt >= '0' && charAt <= '2' && charAt2 >= '0' && charAt2 <= '9' && charAt3 >= '0' && charAt3 <= '6' && charAt4 >= '0' && charAt4 <= '9' && charAt5 >= '0' && charAt5 <= '6' && charAt6 >= '0' && charAt6 <= '9' && ((charAt - '0') * 10) + (charAt2 - '0') <= 24 && ((charAt3 - '0') * 10) + (charAt4 - '0') <= 60 && ((charAt5 - '0') * 10) + (charAt6 - '0') <= 61;
            }
            try {
                LocalTime.parse(str);
                return true;
            } catch (DateTimeParseException unused) {
            }
        }
        return false;
    }

    public static int readNanos(char[] cArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = cArr[i2 + i4] - '0';
            if ((i5 < 0) || (i5 > 9)) {
                return -1;
            }
            i3 = (i3 * 10) + i5;
        }
        return i3 * POWERS[(9 - i) & 15];
    }

    public static int readNanos(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = bArr[i2 + i4] + JSONB.Constants.BC_INT64_BYTE_ZERO;
            if ((i5 < 0) || (i5 > 9)) {
                return -1;
            }
            i3 = (i3 * 10) + i5;
        }
        return i3 * POWERS[(9 - i) & 15];
    }

    public static ZoneOffset zoneOffset(byte[] bArr, int i, int i2) {
        return ZoneOffset.of(new String(bArr, i, i2));
    }

    public static ZoneOffset zoneOffset(char[] cArr, int i, int i2) {
        return ZoneOffset.of(new String(cArr, i, i2));
    }

    public static int nanos(int i, int i2) {
        return i * POWERS[(9 - i2) & 15];
    }

    public static long hms(byte[] bArr, int i) {
        long j = JDKUtils.UNSAFE.getLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i);
        if (JDKUtils.BIG_ENDIAN) {
            j = Long.reverseBytes(j);
        }
        if (((((-1085102592571150096L) & j) - 3472328296227680304L) | (((1085102592571150095L & j) + 434034439958300166L) & (-1085366475377544976L))) != 0 || (16492675399680L & j) != 10995116933120L) {
            return -1L;
        }
        long j2 = 4222124902318095L & j;
        return (j2 << 3) + (j2 << 1) + ((j & 1080863974993432320L) >> 8);
    }

    public static long ymd(byte[] bArr, int i) {
        long j = JDKUtils.UNSAFE.getLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i);
        if (JDKUtils.BIG_ENDIAN) {
            j = Long.reverseBytes(j);
        }
        if ((280375481794560L & j) != 49478026199040L) {
            return -1L;
        }
        if ((((-1085366475377544976L) & ((1085086099895750415L & j) + 434034439958300166L)) | ((j & (-1085366475377544976L)) - 3472275519666401328L)) != 0) {
            return -1L;
        }
        long j2 = 4222124902318095L & j;
        return (j2 << 3) + (j2 << 1) + ((j & 1080863974993432320L) >> 8);
    }

    public static int yy(byte[] bArr, int i) {
        short s = JDKUtils.UNSAFE.getShort(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i);
        if (JDKUtils.BIG_ENDIAN) {
            s = Short.reverseBytes(s);
        }
        int i2 = s & 3855;
        if (((61680 & (i2 + 1542)) | ((s & 61680) - 12336)) != 0) {
            return -1;
        }
        return ((s & 15) * 1000) + ((i2 >> 8) * 100);
    }
}
