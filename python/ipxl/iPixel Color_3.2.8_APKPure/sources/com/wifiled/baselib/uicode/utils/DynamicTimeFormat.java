package com.wifiled.baselib.uicode.utils;

import com.wifiled.baselib.utils.DateUtils;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class DynamicTimeFormat extends SimpleDateFormat {
    private String mFormat;
    private static Locale locale = Locale.CHINA;
    private static String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static String[] moments = {"中午", "凌晨", "早上", "下午", "晚上"};

    public DynamicTimeFormat() {
        this("%s", "yyyy年", "M月d日", DateUtils.FORMAT_HH_MM);
    }

    public DynamicTimeFormat(String str) {
        this();
        this.mFormat = str;
    }

    public DynamicTimeFormat(String str, String str2, String str3) {
        super(String.format(locale, "%s %s %s", str, str2, str3), locale);
        this.mFormat = "%s";
    }

    public DynamicTimeFormat(String str, String str2, String str3, String str4) {
        this(str2, str3, str4);
        this.mFormat = str;
    }

    @Override // java.text.SimpleDateFormat, java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        StringBuffer format = super.format(date, stringBuffer, fieldPosition);
        Calendar calendar = this.calendar;
        Calendar calendar2 = Calendar.getInstance();
        int i = calendar.get(11);
        String[] split = format.toString().split(" ");
        String str = (i == 12 ? moments[0] : moments[(i / 6) + 1]) + " " + split[2];
        String str2 = split[1] + " " + str;
        String str3 = split[0] + str2;
        format.delete(0, format.length());
        if (calendar2.get(1) == calendar.get(1)) {
            if (calendar2.get(2) == calendar.get(2)) {
                switch (calendar2.get(5) - calendar.get(5)) {
                    case 0:
                        format.append(str);
                        break;
                    case 1:
                        format.append("昨天 ");
                        format.append(str);
                        break;
                    case 2:
                        format.append("前天 ");
                        format.append(str);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        if (calendar.get(4) == calendar2.get(4)) {
                            if (calendar.get(7) != 1) {
                                format.append(weeks[calendar.get(7) - 1]);
                                format.append(' ');
                                format.append(str);
                                break;
                            } else {
                                format.append(str2);
                                break;
                            }
                        } else {
                            format.append(str2);
                            break;
                        }
                    default:
                        format.append(str2);
                        break;
                }
            } else {
                format.append(str2);
            }
        } else {
            format.append(str3);
        }
        int length = format.length();
        format.append(String.format(locale, this.mFormat, format.toString()));
        format.delete(0, length);
        return format;
    }
}
