package com.wifiled.baselib.uicode.statuslayout;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: StatusLayoutType.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/wifiled/baselib/uicode/statuslayout/StatusLayoutType;", "", "<init>", "(Ljava/lang/String;I)V", "STATUS_DEFAULT", "STATUS_EMPTY", "STATUS_LOAD_ERROR", "STATUS_NET_DISCONNECT_ERROR", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class StatusLayoutType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ StatusLayoutType[] $VALUES;
    public static final StatusLayoutType STATUS_DEFAULT = new StatusLayoutType("STATUS_DEFAULT", 0);
    public static final StatusLayoutType STATUS_EMPTY = new StatusLayoutType("STATUS_EMPTY", 1);
    public static final StatusLayoutType STATUS_LOAD_ERROR = new StatusLayoutType("STATUS_LOAD_ERROR", 2);
    public static final StatusLayoutType STATUS_NET_DISCONNECT_ERROR = new StatusLayoutType("STATUS_NET_DISCONNECT_ERROR", 3);

    private static final /* synthetic */ StatusLayoutType[] $values() {
        return new StatusLayoutType[]{STATUS_DEFAULT, STATUS_EMPTY, STATUS_LOAD_ERROR, STATUS_NET_DISCONNECT_ERROR};
    }

    public static EnumEntries<StatusLayoutType> getEntries() {
        return $ENTRIES;
    }

    public static StatusLayoutType valueOf(String str) {
        return (StatusLayoutType) Enum.valueOf(StatusLayoutType.class, str);
    }

    public static StatusLayoutType[] values() {
        return (StatusLayoutType[]) $VALUES.clone();
    }

    private StatusLayoutType(String str, int i) {
    }

    static {
        StatusLayoutType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
