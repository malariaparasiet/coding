package androidx.compose.ui.layout;

import java.util.List;
import kotlin.Metadata;

/* compiled from: LayoutInfo.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0012\u0010\r\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\t¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/layout/LayoutInfo;", "", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "height", "", "getHeight", "()I", "isAttached", "", "()Z", "isPlaced", "parentInfo", "getParentInfo", "()Landroidx/compose/ui/layout/LayoutInfo;", "width", "getWidth", "getModifierInfo", "", "Landroidx/compose/ui/layout/ModifierInfo;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface LayoutInfo {
    LayoutCoordinates getCoordinates();

    int getHeight();

    List<ModifierInfo> getModifierInfo();

    LayoutInfo getParentInfo();

    int getWidth();

    boolean isAttached();

    boolean isPlaced();
}
