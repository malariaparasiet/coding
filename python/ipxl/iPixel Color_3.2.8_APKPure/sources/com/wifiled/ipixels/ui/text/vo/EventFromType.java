package com.wifiled.ipixels.ui.text.vo;

import kotlin.Metadata;

/* compiled from: EventFromType.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0011"}, d2 = {"Lcom/wifiled/ipixels/ui/text/vo/EventFromType;", "", "fromType", "", "<init>", "(I)V", "getFromType", "()I", "setFromType", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class EventFromType {
    private int fromType;

    public static /* synthetic */ EventFromType copy$default(EventFromType eventFromType, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = eventFromType.fromType;
        }
        return eventFromType.copy(i);
    }

    /* renamed from: component1, reason: from getter */
    public final int getFromType() {
        return this.fromType;
    }

    public final EventFromType copy(int fromType) {
        return new EventFromType(fromType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof EventFromType) && this.fromType == ((EventFromType) other).fromType;
    }

    public int hashCode() {
        return Integer.hashCode(this.fromType);
    }

    public String toString() {
        return "EventFromType(fromType=" + this.fromType + ")";
    }

    public EventFromType(int i) {
        this.fromType = i;
    }

    public final int getFromType() {
        return this.fromType;
    }

    public final void setFromType(int i) {
        this.fromType = i;
    }
}
