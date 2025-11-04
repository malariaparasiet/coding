package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.LayoutCoordinates;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HitPathTracker.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J/\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00120\u00162\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019H\u0002ø\u0001\u0000J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0017\u0010\u001e\u001a\u00020\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140 H\u0082\bJ/\u0010!\u001a\u00020\u001d2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00120\u00162\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019H\u0016ø\u0001\u0000J\b\u0010\"\u001a\u00020#H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Landroidx/compose/ui/input/pointer/Node;", "Landroidx/compose/ui/input/pointer/NodeParent;", "pointerInputFilter", "Landroidx/compose/ui/input/pointer/PointerInputFilter;", "(Landroidx/compose/ui/input/pointer/PointerInputFilter;)V", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerIds", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/input/pointer/PointerId;", "getPointerIds", "()Landroidx/compose/runtime/collection/MutableVector;", "getPointerInputFilter", "()Landroidx/compose/ui/input/pointer/PointerInputFilter;", "relevantChanges", "", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "buildCache", "", "changes", "", "parentCoordinates", "internalPointerEvent", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "clearCache", "dispatchCancel", "dispatchFinalEventPass", "", "dispatchIfNeeded", "block", "Lkotlin/Function0;", "dispatchMainEventPass", "toString", "", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class Node extends NodeParent {
    private LayoutCoordinates coordinates;
    private PointerEvent pointerEvent;
    private final MutableVector<PointerId> pointerIds;
    private final PointerInputFilter pointerInputFilter;
    private final Map<PointerId, PointerInputChange> relevantChanges;

    public Node(PointerInputFilter pointerInputFilter) {
        Intrinsics.checkNotNullParameter(pointerInputFilter, "pointerInputFilter");
        this.pointerInputFilter = pointerInputFilter;
        this.pointerIds = new MutableVector<>(new PointerId[16], 0);
        this.relevantChanges = new LinkedHashMap();
    }

    public final PointerInputFilter getPointerInputFilter() {
        return this.pointerInputFilter;
    }

    public final MutableVector<PointerId> getPointerIds() {
        return this.pointerIds;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchMainEventPass(Map<PointerId, PointerInputChange> changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent) {
        MutableVector<Node> children;
        int size;
        Intrinsics.checkNotNullParameter(changes, "changes");
        Intrinsics.checkNotNullParameter(parentCoordinates, "parentCoordinates");
        Intrinsics.checkNotNullParameter(internalPointerEvent, "internalPointerEvent");
        buildCache(changes, parentCoordinates, internalPointerEvent);
        int i = 0;
        if (this.relevantChanges.isEmpty() || !getPointerInputFilter().isAttached$ui_release()) {
            return false;
        }
        PointerEvent pointerEvent = this.pointerEvent;
        Intrinsics.checkNotNull(pointerEvent);
        LayoutCoordinates layoutCoordinates = this.coordinates;
        Intrinsics.checkNotNull(layoutCoordinates);
        long mo1944getSizeYbymL2g = layoutCoordinates.mo1944getSizeYbymL2g();
        getPointerInputFilter().mo1864onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Initial, mo1944getSizeYbymL2g);
        if (getPointerInputFilter().isAttached$ui_release() && (size = (children = getChildren()).getSize()) > 0) {
            Node[] content = children.getContent();
            do {
                Node node = content[i];
                Map<PointerId, PointerInputChange> map = this.relevantChanges;
                LayoutCoordinates layoutCoordinates2 = this.coordinates;
                Intrinsics.checkNotNull(layoutCoordinates2);
                node.dispatchMainEventPass(map, layoutCoordinates2, internalPointerEvent);
                i++;
            } while (i < size);
        }
        if (getPointerInputFilter().isAttached$ui_release()) {
            getPointerInputFilter().mo1864onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Main, mo1944getSizeYbymL2g);
        }
        return true;
    }

    private final void buildCache(Map<PointerId, PointerInputChange> changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent) {
        PointerInputChange m1846copyEzrO64;
        if (this.pointerInputFilter.isAttached$ui_release()) {
            this.coordinates = this.pointerInputFilter.getLayoutCoordinates();
            for (Map.Entry<PointerId, PointerInputChange> entry : changes.entrySet()) {
                long value = entry.getKey().getValue();
                PointerInputChange value2 = entry.getValue();
                if (this.pointerIds.contains(PointerId.m1838boximpl(value))) {
                    Map<PointerId, PointerInputChange> map = this.relevantChanges;
                    PointerId m1838boximpl = PointerId.m1838boximpl(value);
                    LayoutCoordinates layoutCoordinates = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates);
                    long mo1945localPositionOfR5De75A = layoutCoordinates.mo1945localPositionOfR5De75A(parentCoordinates, value2.getPreviousPosition());
                    LayoutCoordinates layoutCoordinates2 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates2);
                    m1846copyEzrO64 = value2.m1846copyEzrO64((r29 & 1) != 0 ? value2.getId() : 0L, (r29 & 2) != 0 ? value2.uptimeMillis : 0L, (r29 & 4) != 0 ? value2.getPosition() : layoutCoordinates2.mo1945localPositionOfR5De75A(parentCoordinates, value2.getPosition()), (r29 & 8) != 0 ? value2.pressed : false, (r29 & 16) != 0 ? value2.previousUptimeMillis : 0L, (r29 & 32) != 0 ? value2.getPreviousPosition() : mo1945localPositionOfR5De75A, (r29 & 64) != 0 ? value2.previousPressed : false, (r29 & 128) != 0 ? value2.consumed : null, (r29 & 256) != 0 ? value2.getType() : 0);
                    map.put(m1838boximpl, m1846copyEzrO64);
                }
            }
            if (this.relevantChanges.isEmpty()) {
                return;
            }
            this.pointerEvent = new PointerEvent((List<PointerInputChange>) CollectionsKt.toList(this.relevantChanges.values()), internalPointerEvent);
        }
    }

    private final void clearCache() {
        this.relevantChanges.clear();
        this.coordinates = null;
        this.pointerEvent = null;
    }

    private final boolean dispatchIfNeeded(Function0<Unit> block) {
        if (this.relevantChanges.isEmpty() || !getPointerInputFilter().isAttached$ui_release()) {
            return false;
        }
        block.invoke();
        return true;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void dispatchCancel() {
        MutableVector<Node> children = getChildren();
        int size = children.getSize();
        if (size > 0) {
            Node[] content = children.getContent();
            int i = 0;
            do {
                content[i].dispatchCancel();
                i++;
            } while (i < size);
        }
        this.pointerInputFilter.onCancel();
    }

    public String toString() {
        return "Node(pointerInputFilter=" + this.pointerInputFilter + ", children=" + getChildren() + ", pointerIds=" + this.pointerIds + ')';
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchFinalEventPass() {
        MutableVector<Node> children;
        int size;
        boolean z = false;
        int i = 0;
        z = false;
        if (!this.relevantChanges.isEmpty() && getPointerInputFilter().isAttached$ui_release()) {
            PointerEvent pointerEvent = this.pointerEvent;
            Intrinsics.checkNotNull(pointerEvent);
            LayoutCoordinates layoutCoordinates = this.coordinates;
            Intrinsics.checkNotNull(layoutCoordinates);
            getPointerInputFilter().mo1864onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Final, layoutCoordinates.mo1944getSizeYbymL2g());
            if (getPointerInputFilter().isAttached$ui_release() && (size = (children = getChildren()).getSize()) > 0) {
                Node[] content = children.getContent();
                do {
                    content[i].dispatchFinalEventPass();
                    i++;
                } while (i < size);
            }
            z = true;
        }
        clearCache();
        return z;
    }
}
