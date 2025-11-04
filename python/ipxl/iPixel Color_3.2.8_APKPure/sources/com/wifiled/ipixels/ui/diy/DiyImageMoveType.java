package com.wifiled.ipixels.ui.diy;

import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: DiyImageFun.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0013\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/wifiled/ipixels/ui/diy/DiyImageMoveType;", "", PlayerFinal.PLAYER_MODE, "", "<init>", "(Ljava/lang/String;II)V", "getMode", "()I", "NO_EFFECT", "HORIZONTAL_MIRROR", "VERTICAL_MIRROR", "OVERALL_MOVEMENT", "ERASE", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DiyImageMoveType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DiyImageMoveType[] $VALUES;
    private final int mode;
    public static final DiyImageMoveType NO_EFFECT = new DiyImageMoveType("NO_EFFECT", 0, 0);
    public static final DiyImageMoveType HORIZONTAL_MIRROR = new DiyImageMoveType("HORIZONTAL_MIRROR", 1, 1);
    public static final DiyImageMoveType VERTICAL_MIRROR = new DiyImageMoveType("VERTICAL_MIRROR", 2, 2);
    public static final DiyImageMoveType OVERALL_MOVEMENT = new DiyImageMoveType("OVERALL_MOVEMENT", 3, 3);
    public static final DiyImageMoveType ERASE = new DiyImageMoveType("ERASE", 4, 4);

    private static final /* synthetic */ DiyImageMoveType[] $values() {
        return new DiyImageMoveType[]{NO_EFFECT, HORIZONTAL_MIRROR, VERTICAL_MIRROR, OVERALL_MOVEMENT, ERASE};
    }

    public static EnumEntries<DiyImageMoveType> getEntries() {
        return $ENTRIES;
    }

    public static DiyImageMoveType valueOf(String str) {
        return (DiyImageMoveType) Enum.valueOf(DiyImageMoveType.class, str);
    }

    public static DiyImageMoveType[] values() {
        return (DiyImageMoveType[]) $VALUES.clone();
    }

    private DiyImageMoveType(String str, int i, int i2) {
        this.mode = i2;
    }

    /* synthetic */ DiyImageMoveType(String str, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i3 & 1) != 0 ? 0 : i2);
    }

    public final int getMode() {
        return this.mode;
    }

    static {
        DiyImageMoveType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
