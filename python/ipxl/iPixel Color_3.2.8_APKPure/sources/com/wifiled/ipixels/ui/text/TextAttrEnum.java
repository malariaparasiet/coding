package com.wifiled.ipixels.ui.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: TextAttrEnum.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextAttrEnum;", "", "<init>", "(Ljava/lang/String;I)V", "TextChange", "TextColor", "TextBgColor", "TextHorizontalAlign", "TextVerticalAlign", "TextEffect", "TextSize", "TextAlpha", "TextSpeed", "TextFontType", "Other", "TextDirction", "TextBold", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAttrEnum {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TextAttrEnum[] $VALUES;
    public static final TextAttrEnum TextChange = new TextAttrEnum("TextChange", 0);
    public static final TextAttrEnum TextColor = new TextAttrEnum("TextColor", 1);
    public static final TextAttrEnum TextBgColor = new TextAttrEnum("TextBgColor", 2);
    public static final TextAttrEnum TextHorizontalAlign = new TextAttrEnum("TextHorizontalAlign", 3);
    public static final TextAttrEnum TextVerticalAlign = new TextAttrEnum("TextVerticalAlign", 4);
    public static final TextAttrEnum TextEffect = new TextAttrEnum("TextEffect", 5);
    public static final TextAttrEnum TextSize = new TextAttrEnum("TextSize", 6);
    public static final TextAttrEnum TextAlpha = new TextAttrEnum("TextAlpha", 7);
    public static final TextAttrEnum TextSpeed = new TextAttrEnum("TextSpeed", 8);
    public static final TextAttrEnum TextFontType = new TextAttrEnum("TextFontType", 9);
    public static final TextAttrEnum Other = new TextAttrEnum("Other", 10);
    public static final TextAttrEnum TextDirction = new TextAttrEnum("TextDirction", 11);
    public static final TextAttrEnum TextBold = new TextAttrEnum("TextBold", 12);

    private static final /* synthetic */ TextAttrEnum[] $values() {
        return new TextAttrEnum[]{TextChange, TextColor, TextBgColor, TextHorizontalAlign, TextVerticalAlign, TextEffect, TextSize, TextAlpha, TextSpeed, TextFontType, Other, TextDirction, TextBold};
    }

    public static EnumEntries<TextAttrEnum> getEntries() {
        return $ENTRIES;
    }

    public static TextAttrEnum valueOf(String str) {
        return (TextAttrEnum) Enum.valueOf(TextAttrEnum.class, str);
    }

    public static TextAttrEnum[] values() {
        return (TextAttrEnum[]) $VALUES.clone();
    }

    private TextAttrEnum(String str, int i) {
    }

    static {
        TextAttrEnum[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
