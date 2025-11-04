package com.wifiled.ipixels.utils;

import java.util.regex.Pattern;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;

/* loaded from: classes3.dex */
public class ArabicUtils {
    private static ArabicUtils single;
    static final int[][] Arbic_Position = {new int[]{1569, 65152, 65152, 65152, 65152}, new int[]{1570, 65154, 65153, 65154, 65153}, new int[]{1571, 65156, 65155, 65156, 65155}, new int[]{1572, 65158, 65157, 65158, 65157}, new int[]{1573, 65160, 65159, 65160, 65159}, new int[]{1574, 65162, 65163, 65164, 65161}, new int[]{1575, 65166, 65165, 65166, 65165}, new int[]{1576, 65168, 65169, 65170, 65167}, new int[]{1577, 65172, 65171, 65172, 65171}, new int[]{1578, 65174, 65175, 65176, 65173}, new int[]{1579, 65178, 65179, 65180, 65177}, new int[]{1580, 65182, 65183, 65184, 65181}, new int[]{1581, 65186, 65187, 65188, 65185}, new int[]{1582, 65190, 65191, 65192, 65189}, new int[]{1583, 65194, 65193, 65194, 65193}, new int[]{1584, 65196, 65195, 65196, 65195}, new int[]{1585, 65198, 65197, 65198, 65197}, new int[]{1586, 65200, 65199, 65200, 65199}, new int[]{1587, 65202, 65203, 65204, 65201}, new int[]{1588, 65206, 65207, 65208, 65205}, new int[]{1589, 65210, 65211, 65212, 65209}, new int[]{1590, 65214, 65215, 65216, 65213}, new int[]{1591, 65218, 65219, 65220, 65217}, new int[]{1592, 65222, 65223, 65224, 65221}, new int[]{1593, 65226, 65227, 65228, 65225}, new int[]{1594, 65230, 65231, 65232, 65229}, new int[]{1595, 1595, 1595, 1595, 1595}, new int[]{1596, 1596, 1596, 1596, 1596}, new int[]{1597, 1597, 1597, 1597, 1597}, new int[]{1598, 1598, 1598, 1598, 1598}, new int[]{1599, 1599, 1599, 1599, 1599}, new int[]{1600, 1600, 1600, 1600, 1600}, new int[]{1601, 65234, 65235, 65236, 65233}, new int[]{1602, 65238, 65239, 65240, 65237}, new int[]{1603, 65242, 65243, 65244, 65241}, new int[]{1604, 65246, 65247, 65248, 65245}, new int[]{1605, 65250, 65251, 65252, 65249}, new int[]{1606, 65254, 65255, 65256, 65253}, new int[]{1607, 65258, 65259, 65260, 65257}, new int[]{1608, 65262, 65261, 65262, 65261}, new int[]{1609, 65264, 65267, 65268, 65263}, new int[]{1610, 65266, 65267, 65268, 65265}};
    static final int[] theSet1 = {1580, 1581, 1582, 1607, 1593, 1594, 1601, 1602, 1579, 1589, 1590, 1591, 1603, 1605, 1606, 1578, 1604, 1576, 1610, 1587, 1588, 1592, 1574, 1600};
    static final int[] theSet2 = {1580, 1581, 1582, 1607, 1593, 1594, 1601, 1602, 1579, 1589, 1590, 1591, 1603, 1605, 1606, 1578, 1604, 1576, 1610, 1587, 1588, 1592, 1574, 1575, 1571, 1573, 1570, 1583, 1584, 1585, 1586, 1608, 1572, 1577, 1609, 1600};
    static final int[][] arabic_specs = {new int[]{65269, 65270}, new int[]{65271, 65272}, new int[]{65273, 65274}, new int[]{65275, 65276}};
    static final int[] ArabicSup_Subs = {1611, 1612, 1613, 1614, 1615, 1616, 1617, 1618, 1619, 1620, 1621, 1622, 1623, 1624, 1625, 1626, 1627, 1628, 1629, 1630, 1750, 1751, 1752, DilithiumEngine.DilithiumRootOfUnity, 1754, 1755, 1756, 1759, 1760, 1761, 1762, 1763, 1764, 1767, 1768, 1770, 1771, 1772};
    static final int[] HindiSup_Subs = {2305, 2306, 2307, 2364, 2369, 2370, 2371, 2372, 2373, 2374, 2375, 2376, 2381, 2385, 2386, 2387, 2388, 2402, 2403};
    static final int[] HebrewSup_Subs = {1425, 1426, 1427, 1428, 1429, 1430, 1431, 1432, 1433, 1434, 1435, 1436, 1437, 1438, 1439, 1440, 1441, 1442, 1443, 1444, 1445, 1446, 1447, 1448, 1449, 1450, 1451, 1452, 1453, 1454, 1455, 1456, 1457, 1458, 1459, 1460, 1461, 1462, 1463, 1464, 1467, 1469, 1471, 1473, 1474, 1476, 1477, 1479};
    static final int[] ThaiSup_Subs = {3633, 3636, 3637, 3638, 3639, 3640, 3641, 3642, 3655, 3656, 3657, 3658, 3659, 3660, 3661, 3662};
    static final int[] CRLH = {2307, 2366};

    private ArabicUtils() {
    }

    public static ArabicUtils getInstance() {
        ArabicUtils arabicUtils = single;
        if (arabicUtils != null) {
            return arabicUtils;
        }
        ArabicUtils arabicUtils2 = new ArabicUtils();
        single = arabicUtils2;
        return arabicUtils2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v7 */
    public static String getArbicResult(String str) {
        String substring;
        String substring2;
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            String substring3 = str.substring(i, i2);
            if (i == 0) {
                substring = "";
            } else {
                substring = str.substring(i - 1, i);
            }
            if (i == str.length() - 1) {
                substring2 = "";
            } else {
                substring2 = str.substring(i2, i + 2);
            }
            if (isArabic(substring3)) {
                ?? isPreConnect = (!isArabic(substring) || substring.equals("")) ? 0 : getIsPreConnect(substring);
                boolean isForConnect = (!isArabic(substring2) || substring2.equals("")) ? false : getIsForConnect(substring2);
                if (Integer.parseInt(gbEncoding(substring3), 16) == 1604 && !substring2.equals("")) {
                    int parseInt = Integer.parseInt(gbEncoding(substring2), 16);
                    if (parseInt == 1570 || parseInt == 1571 || parseInt == 1573 || parseInt == 1575) {
                        if (parseInt == 1570) {
                            substring3 = arabic_specs[0][isPreConnect] + "";
                        } else if (parseInt == 1571) {
                            substring3 = arabic_specs[1][isPreConnect] + "";
                        } else if (parseInt == 1573) {
                            substring3 = arabic_specs[2][isPreConnect] + "";
                        } else if (parseInt == 1575) {
                            substring3 = arabic_specs[3][isPreConnect] + "";
                        }
                        substring3 = getStrFromUniCode(substring3);
                        i = i2;
                    }
                } else if (isNeedChange(substring3)) {
                    int i3 = (!isForConnect || isPreConnect == 0) ? (isForConnect && isPreConnect == 0) ? 2 : (isForConnect || isPreConnect == 0) ? 0 : 1 : 3;
                    if (!isForConnect && isPreConnect == 0) {
                        i3 = 4;
                    }
                    substring3 = getStrFromUniCode(getChangeReturn(substring3, i3));
                }
            }
            stringBuffer.append(substring3);
            i++;
        }
        return stringBuffer.toString();
    }

    private static String getChangeReturn(String substr, int index) {
        int parseInt = Integer.parseInt(gbEncoding(substr), 16);
        int i = 0;
        while (true) {
            int[][] iArr = Arbic_Position;
            if (i >= iArr.length) {
                return substr;
            }
            if (iArr[i][0] == parseInt) {
                substr = "\\u" + Integer.toHexString(iArr[i][index]);
            }
            i++;
        }
    }

    private static boolean isNeedChange(String substr) {
        int parseInt = Integer.parseInt(gbEncoding(substr), 16);
        int i = 0;
        while (true) {
            int[][] iArr = Arbic_Position;
            if (i >= iArr.length) {
                return false;
            }
            if (iArr[i][0] == parseInt) {
                return true;
            }
            i++;
        }
    }

    private static boolean getIsForConnect(String for_sub) {
        int parseInt = Integer.parseInt(gbEncoding(for_sub), 16);
        int i = 0;
        while (true) {
            int[] iArr = theSet2;
            if (i >= iArr.length) {
                return false;
            }
            if (iArr[i] == parseInt) {
                return true;
            }
            i++;
        }
    }

    public static boolean getIsPreConnect(String pre_sub) {
        int parseInt = Integer.parseInt(gbEncoding(pre_sub), 16);
        int i = 0;
        while (true) {
            int[] iArr = theSet1;
            if (i >= iArr.length) {
                return false;
            }
            if (iArr[i] == parseInt) {
                return true;
            }
            i++;
        }
    }

    public static byte[] adminSup_SubArabic(byte[] str_byte, byte[] follow_byte, int dots) {
        if (dots == 12) {
            for (int i = 8; i < 20; i++) {
                str_byte[i] = 0;
            }
        } else if (dots == 16) {
            for (int i2 = 12; i2 < 24; i2++) {
                str_byte[i2] = 0;
            }
        }
        for (int i3 = 0; i3 < str_byte.length; i3++) {
            follow_byte[i3] = (byte) (str_byte[i3] | follow_byte[i3]);
        }
        return follow_byte;
    }

    public static boolean isSup_SubArabic(String str) {
        int parseInt = Integer.parseInt(gbEncoding(str), 16);
        for (int i : ArabicSup_Subs) {
            if (i == parseInt) {
                return true;
            }
        }
        return false;
    }

    public static boolean isArabic(String text) {
        if (text != null && !text.isEmpty()) {
            int i = 0;
            while (i < text.length()) {
                int codePointAt = text.codePointAt(i);
                if ((codePointAt >= 1536 && codePointAt <= 1791) || ((codePointAt >= 1872 && codePointAt <= 1919) || ((codePointAt >= 2208 && codePointAt <= 2303) || ((codePointAt >= 64336 && codePointAt <= 65023) || ((codePointAt >= 65136 && codePointAt <= 65279) || (codePointAt >= 126464 && codePointAt <= 126719)))))) {
                    return true;
                }
                if (Character.isSupplementaryCodePoint(codePointAt)) {
                    i++;
                }
                i++;
            }
        }
        return false;
    }

    public static byte[] adminSup_SubThai(byte[] str_byte, byte[] follow_byte, int dots) {
        if (dots == 12) {
            for (int i = 10; i < 20; i++) {
                str_byte[i] = 0;
            }
        } else if (dots == 16) {
            for (int i2 = 12; i2 < 24; i2++) {
                str_byte[i2] = 0;
            }
        }
        for (int i3 = 0; i3 < str_byte.length; i3++) {
            follow_byte[i3] = (byte) (str_byte[i3] | follow_byte[i3]);
        }
        return follow_byte;
    }

    public static boolean isSup_SubThai(String str) {
        int parseInt = Integer.parseInt(gbEncoding(str), 16);
        for (int i : ThaiSup_Subs) {
            if (i == parseInt) {
                return true;
            }
        }
        return false;
    }

    public static boolean isThai(String sub) {
        int parseInt;
        return sub.length() > 0 && (((parseInt = Integer.parseInt(gbEncoding(sub.substring(0, 1)), 16)) > 3584 && parseInt < 3642) || (parseInt > 3647 && parseInt < 3675));
    }

    public static byte[] adminSup_SubHebrew(byte[] str_byte, byte[] follow_byte, int dots) {
        if (dots == 12) {
            for (int i = 8; i < 20; i++) {
                str_byte[i] = 0;
            }
        } else if (dots == 16) {
            for (int i2 = 12; i2 < 26; i2++) {
                str_byte[i2] = 0;
            }
        }
        for (int i3 = 0; i3 < str_byte.length; i3++) {
            follow_byte[i3] = (byte) (str_byte[i3] | follow_byte[i3]);
        }
        return follow_byte;
    }

    public static boolean isSup_SubHebrew(String str) {
        int parseInt = Integer.parseInt(gbEncoding(str), 16);
        int i = 0;
        while (true) {
            int[] iArr = HebrewSup_Subs;
            if (i >= iArr.length) {
                return false;
            }
            if (iArr[i] == parseInt) {
                return true;
            }
            i++;
        }
    }

    public static boolean isHebrew(String sub) {
        int parseInt;
        if (sub.length() > 0) {
            String substring = sub.substring(0, 1);
            if (!substring.equals("") && (parseInt = Integer.parseInt(gbEncoding(substring), 16)) > 1424 && parseInt < 1535) {
                return true;
            }
        }
        return false;
    }

    public static byte[] adminSup_SubHindi(byte[] str_byte, byte[] follow_byte, int dots) {
        if (dots == 12) {
            for (int i = 10; i < 22; i++) {
                str_byte[i] = 0;
            }
        } else if (dots == 16) {
            for (int i2 = 14; i2 < 26; i2++) {
                str_byte[i2] = 0;
            }
        }
        for (int i3 = 0; i3 < str_byte.length; i3++) {
            follow_byte[i3] = (byte) (str_byte[i3] | follow_byte[i3]);
        }
        return follow_byte;
    }

    public static boolean isSup_SubHindi(String str) {
        int parseInt = Integer.parseInt(gbEncoding(str), 16);
        int i = 0;
        while (true) {
            int[] iArr = HindiSup_Subs;
            if (i >= iArr.length) {
                return false;
            }
            if (iArr[i] == parseInt) {
                return true;
            }
            i++;
        }
    }

    public static boolean isHindi(String sub) {
        int parseInt;
        if (sub.length() > 0) {
            String substring = sub.substring(0, 1);
            if (!substring.equals("") && (parseInt = Integer.parseInt(gbEncoding(substring), 16)) > 2304 && parseInt < 2431) {
                return true;
            }
        }
        return false;
    }

    private static String gbEncoding(final String gbString) {
        String str = "";
        for (char c : gbString.toCharArray()) {
            String hexString = Integer.toHexString(c);
            if (hexString.length() <= 2) {
                hexString = "00" + hexString;
            }
            str = str + hexString;
        }
        return str;
    }

    private static String getStrFromUniCode(String unicode) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] split = unicode.split("\\\\u");
        for (int i = 1; i < split.length; i++) {
            stringBuffer.append((char) Integer.parseInt(split[i], 16));
        }
        return stringBuffer.toString();
    }

    public static String replaceUnicode(String sourceStr) {
        return Pattern.compile("[\u0000-\u001f\u007f- ً-ً]").matcher(sourceStr).replaceAll("");
    }
}
