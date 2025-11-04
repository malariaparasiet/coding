package com.wifiled.ipixels.ui.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CreativeTextUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/wifiled/ipixels/ui/text/AnimationType;", "", "<init>", "(Ljava/lang/String;I)V", "NEON", "PRINTER", "THREE_D_ROTATE", "WAVE_IN", "ROTATE_ENTER", "ROTATE_SCALE", "CLOCK", "PARTICLE", "MIDDLE_SPLIT", "EXPAND_SHRINK", "BOUNCE", "THREE_D_Y_ROTATE", "ROTATE_ENTER_LEFT", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AnimationType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AnimationType[] $VALUES;
    public static final AnimationType NEON = new AnimationType("NEON", 0);
    public static final AnimationType PRINTER = new AnimationType("PRINTER", 1);
    public static final AnimationType THREE_D_ROTATE = new AnimationType("THREE_D_ROTATE", 2);
    public static final AnimationType WAVE_IN = new AnimationType("WAVE_IN", 3);
    public static final AnimationType ROTATE_ENTER = new AnimationType("ROTATE_ENTER", 4);
    public static final AnimationType ROTATE_SCALE = new AnimationType("ROTATE_SCALE", 5);
    public static final AnimationType CLOCK = new AnimationType("CLOCK", 6);
    public static final AnimationType PARTICLE = new AnimationType("PARTICLE", 7);
    public static final AnimationType MIDDLE_SPLIT = new AnimationType("MIDDLE_SPLIT", 8);
    public static final AnimationType EXPAND_SHRINK = new AnimationType("EXPAND_SHRINK", 9);
    public static final AnimationType BOUNCE = new AnimationType("BOUNCE", 10);
    public static final AnimationType THREE_D_Y_ROTATE = new AnimationType("THREE_D_Y_ROTATE", 11);
    public static final AnimationType ROTATE_ENTER_LEFT = new AnimationType("ROTATE_ENTER_LEFT", 12);

    private static final /* synthetic */ AnimationType[] $values() {
        return new AnimationType[]{NEON, PRINTER, THREE_D_ROTATE, WAVE_IN, ROTATE_ENTER, ROTATE_SCALE, CLOCK, PARTICLE, MIDDLE_SPLIT, EXPAND_SHRINK, BOUNCE, THREE_D_Y_ROTATE, ROTATE_ENTER_LEFT};
    }

    public static EnumEntries<AnimationType> getEntries() {
        return $ENTRIES;
    }

    public static AnimationType valueOf(String str) {
        return (AnimationType) Enum.valueOf(AnimationType.class, str);
    }

    public static AnimationType[] values() {
        return (AnimationType[]) $VALUES.clone();
    }

    private AnimationType(String str, int i) {
    }

    static {
        AnimationType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
