package com.wifiled.ipixels.ui.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CreativeTextUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/wifiled/ipixels/ui/text/GradientType;", "", "<init>", "(Ljava/lang/String;I)V", "HORIZONTAL", "VERTICAL", "DIAGONAL", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GradientType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ GradientType[] $VALUES;
    public static final GradientType HORIZONTAL = new GradientType("HORIZONTAL", 0);
    public static final GradientType VERTICAL = new GradientType("VERTICAL", 1);
    public static final GradientType DIAGONAL = new GradientType("DIAGONAL", 2);

    private static final /* synthetic */ GradientType[] $values() {
        return new GradientType[]{HORIZONTAL, VERTICAL, DIAGONAL};
    }

    public static EnumEntries<GradientType> getEntries() {
        return $ENTRIES;
    }

    public static GradientType valueOf(String str) {
        return (GradientType) Enum.valueOf(GradientType.class, str);
    }

    public static GradientType[] values() {
        return (GradientType[]) $VALUES.clone();
    }

    private GradientType(String str, int i) {
    }

    static {
        GradientType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
