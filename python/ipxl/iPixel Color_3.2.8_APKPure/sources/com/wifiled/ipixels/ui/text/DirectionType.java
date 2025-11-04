package com.wifiled.ipixels.ui.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CreativeTextUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/wifiled/ipixels/ui/text/DirectionType;", "", "<init>", "(Ljava/lang/String;I)V", "LEFT", "RIGHT", "TOP", "BOTTOM", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DirectionType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DirectionType[] $VALUES;
    public static final DirectionType LEFT = new DirectionType("LEFT", 0);
    public static final DirectionType RIGHT = new DirectionType("RIGHT", 1);
    public static final DirectionType TOP = new DirectionType("TOP", 2);
    public static final DirectionType BOTTOM = new DirectionType("BOTTOM", 3);

    private static final /* synthetic */ DirectionType[] $values() {
        return new DirectionType[]{LEFT, RIGHT, TOP, BOTTOM};
    }

    public static EnumEntries<DirectionType> getEntries() {
        return $ENTRIES;
    }

    public static DirectionType valueOf(String str) {
        return (DirectionType) Enum.valueOf(DirectionType.class, str);
    }

    public static DirectionType[] values() {
        return (DirectionType[]) $VALUES.clone();
    }

    private DirectionType(String str, int i) {
    }

    static {
        DirectionType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
