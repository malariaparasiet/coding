package com.wifiled.ipixels.ui.diy;

import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: DiyImageFun.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0013\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/wifiled/ipixels/ui/diy/DiyImageFun;", "", PlayerFinal.PLAYER_MODE, "", "<init>", "(Ljava/lang/String;II)V", "getMode", "()I", "QUIT_NOSAVE_KEEP_PREV", "ENTER_CLEAR_CUR_SHOW", "QUIT_STILL_CUR_SHOW", "ENTER_NO_CLEAR_CUR_SHOW", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DiyImageFun {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DiyImageFun[] $VALUES;
    private final int mode;
    public static final DiyImageFun QUIT_NOSAVE_KEEP_PREV = new DiyImageFun("QUIT_NOSAVE_KEEP_PREV", 0, 0);
    public static final DiyImageFun ENTER_CLEAR_CUR_SHOW = new DiyImageFun("ENTER_CLEAR_CUR_SHOW", 1, 1);
    public static final DiyImageFun QUIT_STILL_CUR_SHOW = new DiyImageFun("QUIT_STILL_CUR_SHOW", 2, 2);
    public static final DiyImageFun ENTER_NO_CLEAR_CUR_SHOW = new DiyImageFun("ENTER_NO_CLEAR_CUR_SHOW", 3, 3);

    private static final /* synthetic */ DiyImageFun[] $values() {
        return new DiyImageFun[]{QUIT_NOSAVE_KEEP_PREV, ENTER_CLEAR_CUR_SHOW, QUIT_STILL_CUR_SHOW, ENTER_NO_CLEAR_CUR_SHOW};
    }

    public static EnumEntries<DiyImageFun> getEntries() {
        return $ENTRIES;
    }

    public static DiyImageFun valueOf(String str) {
        return (DiyImageFun) Enum.valueOf(DiyImageFun.class, str);
    }

    public static DiyImageFun[] values() {
        return (DiyImageFun[]) $VALUES.clone();
    }

    private DiyImageFun(String str, int i, int i2) {
        this.mode = i2;
    }

    /* synthetic */ DiyImageFun(String str, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i3 & 1) != 0 ? 0 : i2);
    }

    public final int getMode() {
        return this.mode;
    }

    static {
        DiyImageFun[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
