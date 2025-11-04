package com.wifiled.ipixels.ui.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CreativeTextUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextAlignment;", "", "<init>", "(Ljava/lang/String;I)V", "LEFT", "CENTER", "RIGHT", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAlignment {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TextAlignment[] $VALUES;
    public static final TextAlignment LEFT = new TextAlignment("LEFT", 0);
    public static final TextAlignment CENTER = new TextAlignment("CENTER", 1);
    public static final TextAlignment RIGHT = new TextAlignment("RIGHT", 2);

    private static final /* synthetic */ TextAlignment[] $values() {
        return new TextAlignment[]{LEFT, CENTER, RIGHT};
    }

    public static EnumEntries<TextAlignment> getEntries() {
        return $ENTRIES;
    }

    public static TextAlignment valueOf(String str) {
        return (TextAlignment) Enum.valueOf(TextAlignment.class, str);
    }

    public static TextAlignment[] values() {
        return (TextAlignment[]) $VALUES.clone();
    }

    private TextAlignment(String str, int i) {
    }

    static {
        TextAlignment[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
