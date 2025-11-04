package com.wifiled.ipixels.ui.text.vo;

import kotlin.Metadata;

/* compiled from: EventLanguage.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/wifiled/ipixels/ui/text/vo/EventLanguage;", "", "type", "", "<init>", "(I)V", "getType", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class EventLanguage {
    private final int type;

    public static /* synthetic */ EventLanguage copy$default(EventLanguage eventLanguage, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = eventLanguage.type;
        }
        return eventLanguage.copy(i);
    }

    /* renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    public final EventLanguage copy(int type) {
        return new EventLanguage(type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof EventLanguage) && this.type == ((EventLanguage) other).type;
    }

    public int hashCode() {
        return Integer.hashCode(this.type);
    }

    public String toString() {
        return "EventLanguage(type=" + this.type + ")";
    }

    public EventLanguage(int i) {
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }
}
