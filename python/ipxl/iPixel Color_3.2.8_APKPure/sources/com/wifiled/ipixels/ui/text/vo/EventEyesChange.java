package com.wifiled.ipixels.ui.text.vo;

import kotlin.Metadata;

/* compiled from: EventEyesChange.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0006\"\u0004\b\u0007\u0010\u0005¨\u0006\u0010"}, d2 = {"Lcom/wifiled/ipixels/ui/text/vo/EventEyesChange;", "", "isShow", "", "<init>", "(Z)V", "()Z", "setShow", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class EventEyesChange {
    private boolean isShow;

    public static /* synthetic */ EventEyesChange copy$default(EventEyesChange eventEyesChange, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = eventEyesChange.isShow;
        }
        return eventEyesChange.copy(z);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsShow() {
        return this.isShow;
    }

    public final EventEyesChange copy(boolean isShow) {
        return new EventEyesChange(isShow);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof EventEyesChange) && this.isShow == ((EventEyesChange) other).isShow;
    }

    public int hashCode() {
        return Boolean.hashCode(this.isShow);
    }

    public String toString() {
        return "EventEyesChange(isShow=" + this.isShow + ")";
    }

    public EventEyesChange(boolean z) {
        this.isShow = z;
    }

    public final boolean isShow() {
        return this.isShow;
    }

    public final void setShow(boolean z) {
        this.isShow = z;
    }
}
