package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PointerEvent.android.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\b\u0010\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B\u0015\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\bB\u001f\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\nHÀ\u0003¢\u0006\u0002\b\u0012J%\u0010\u0013\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerEvent;", "", "changes", "", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "internalPointerEvent", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "(Ljava/util/List;Landroidx/compose/ui/input/pointer/InternalPointerEvent;)V", "(Ljava/util/List;)V", "motionEvent", "Landroid/view/MotionEvent;", "(Ljava/util/List;Landroid/view/MotionEvent;)V", "getChanges", "()Ljava/util/List;", "getMotionEvent$ui_release", "()Landroid/view/MotionEvent;", "component1", "component2", "component2$ui_release", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class PointerEvent {
    public static final int $stable = 8;
    private final List<PointerInputChange> changes;
    private final MotionEvent motionEvent;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PointerEvent copy$default(PointerEvent pointerEvent, List list, MotionEvent motionEvent, int i, Object obj) {
        if ((i & 1) != 0) {
            list = pointerEvent.changes;
        }
        if ((i & 2) != 0) {
            motionEvent = pointerEvent.motionEvent;
        }
        return pointerEvent.copy(list, motionEvent);
    }

    public final List<PointerInputChange> component1() {
        return this.changes;
    }

    /* renamed from: component2$ui_release, reason: from getter */
    public final MotionEvent getMotionEvent() {
        return this.motionEvent;
    }

    public final PointerEvent copy(List<PointerInputChange> changes, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(changes, "changes");
        return new PointerEvent(changes, motionEvent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PointerEvent)) {
            return false;
        }
        PointerEvent pointerEvent = (PointerEvent) other;
        return Intrinsics.areEqual(this.changes, pointerEvent.changes) && Intrinsics.areEqual(this.motionEvent, pointerEvent.motionEvent);
    }

    public int hashCode() {
        int hashCode = this.changes.hashCode() * 31;
        MotionEvent motionEvent = this.motionEvent;
        return hashCode + (motionEvent == null ? 0 : motionEvent.hashCode());
    }

    public String toString() {
        return "PointerEvent(changes=" + this.changes + ", motionEvent=" + this.motionEvent + ')';
    }

    public PointerEvent(List<PointerInputChange> changes, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(changes, "changes");
        this.changes = changes;
        this.motionEvent = motionEvent;
    }

    public final List<PointerInputChange> getChanges() {
        return this.changes;
    }

    public final MotionEvent getMotionEvent$ui_release() {
        return this.motionEvent;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PointerEvent(List<PointerInputChange> changes, InternalPointerEvent internalPointerEvent) {
        this(changes, internalPointerEvent == null ? null : internalPointerEvent.getMotionEvent());
        Intrinsics.checkNotNullParameter(changes, "changes");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PointerEvent(List<PointerInputChange> changes) {
        this(changes, (MotionEvent) null);
        Intrinsics.checkNotNullParameter(changes, "changes");
    }
}
