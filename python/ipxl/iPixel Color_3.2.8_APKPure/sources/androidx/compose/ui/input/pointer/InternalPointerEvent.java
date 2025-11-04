package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InternalPointerEvent.android.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B&\b\u0016\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bB$\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\t\u001a\u00020\nø\u0001\u0000¢\u0006\u0002\u0010\u000bR \u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "", "changes", "", "Landroidx/compose/ui/input/pointer/PointerId;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "pointerInputEvent", "Landroidx/compose/ui/input/pointer/PointerInputEvent;", "(Ljava/util/Map;Landroidx/compose/ui/input/pointer/PointerInputEvent;)V", "motionEvent", "Landroid/view/MotionEvent;", "(Ljava/util/Map;Landroid/view/MotionEvent;)V", "getChanges", "()Ljava/util/Map;", "getMotionEvent", "()Landroid/view/MotionEvent;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class InternalPointerEvent {
    private final Map<PointerId, PointerInputChange> changes;
    private final MotionEvent motionEvent;

    public InternalPointerEvent(Map<PointerId, PointerInputChange> changes, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(changes, "changes");
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        this.changes = changes;
        this.motionEvent = motionEvent;
    }

    public final Map<PointerId, PointerInputChange> getChanges() {
        return this.changes;
    }

    public final MotionEvent getMotionEvent() {
        return this.motionEvent;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InternalPointerEvent(Map<PointerId, PointerInputChange> changes, PointerInputEvent pointerInputEvent) {
        this(changes, pointerInputEvent.getMotionEvent());
        Intrinsics.checkNotNullParameter(changes, "changes");
        Intrinsics.checkNotNullParameter(pointerInputEvent, "pointerInputEvent");
    }
}
