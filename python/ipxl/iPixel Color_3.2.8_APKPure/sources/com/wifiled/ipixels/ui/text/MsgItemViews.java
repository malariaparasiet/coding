package com.wifiled.ipixels.ui.text;

import com.wifiled.ipixels.view.LedView;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgItemViews.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004R\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\n\u001a\f\u0012\b\u0012\u00060\u0004R\u00020\u00050\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004R\u00020\u00050\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u001b\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004R\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/wifiled/ipixels/ui/text/MsgItemViews;", "Ljava/io/Serializable;", "itemViews", "", "Lcom/wifiled/ipixels/view/LedView$ItemView;", "Lcom/wifiled/ipixels/view/LedView;", "<init>", "(Ljava/util/List;)V", "getItemViews", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class MsgItemViews implements Serializable {
    private final List<LedView.ItemView> itemViews;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MsgItemViews copy$default(MsgItemViews msgItemViews, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = msgItemViews.itemViews;
        }
        return msgItemViews.copy(list);
    }

    public final List<LedView.ItemView> component1() {
        return this.itemViews;
    }

    public final MsgItemViews copy(List<? extends LedView.ItemView> itemViews) {
        Intrinsics.checkNotNullParameter(itemViews, "itemViews");
        return new MsgItemViews(itemViews);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MsgItemViews) && Intrinsics.areEqual(this.itemViews, ((MsgItemViews) other).itemViews);
    }

    public int hashCode() {
        return this.itemViews.hashCode();
    }

    public String toString() {
        return "MsgItemViews(itemViews=" + this.itemViews + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MsgItemViews(List<? extends LedView.ItemView> itemViews) {
        Intrinsics.checkNotNullParameter(itemViews, "itemViews");
        this.itemViews = itemViews;
    }

    public final List<LedView.ItemView> getItemViews() {
        return this.itemViews;
    }
}
