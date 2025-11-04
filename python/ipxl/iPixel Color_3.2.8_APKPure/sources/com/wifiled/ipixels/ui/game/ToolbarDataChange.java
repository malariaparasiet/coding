package com.wifiled.ipixels.ui.game;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ToolbarDataChange.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/wifiled/ipixels/ui/game/ToolbarDataChange;", "", "<init>", "()V", "strTitle", "", "getStrTitle", "()Ljava/lang/String;", "setStrTitle", "(Ljava/lang/String;)V", "isShow", "", "()Z", "setShow", "(Z)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ToolbarDataChange {
    private boolean isShow;
    private String strTitle = "";
    private int index = -1;

    public final String getStrTitle() {
        return this.strTitle;
    }

    public final void setStrTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.strTitle = str;
    }

    /* renamed from: isShow, reason: from getter */
    public final boolean getIsShow() {
        return this.isShow;
    }

    public final void setShow(boolean z) {
        this.isShow = z;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }
}
