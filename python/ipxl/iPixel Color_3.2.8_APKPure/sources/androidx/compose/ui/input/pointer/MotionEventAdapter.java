package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* compiled from: MotionEventAdapter.android.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J9\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010\u001aR+\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0000X\u0081\u0004ø\u0001\u0000¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/input/pointer/MotionEventAdapter;", "", "()V", "motionEventToComposePointerIdMap", "", "", "Landroidx/compose/ui/input/pointer/PointerId;", "getMotionEventToComposePointerIdMap$ui_release$annotations", "getMotionEventToComposePointerIdMap$ui_release", "()Ljava/util/Map;", "nextId", "", "pointers", "", "Landroidx/compose/ui/input/pointer/PointerInputEventData;", "convertToPointerInputEvent", "Landroidx/compose/ui/input/pointer/PointerInputEvent;", "motionEvent", "Landroid/view/MotionEvent;", "positionCalculator", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "convertToPointerInputEvent$ui_release", "createPointerInputEventData", "index", "downIndex", "upIndex", "(Landroidx/compose/ui/input/pointer/PositionCalculator;Landroid/view/MotionEvent;ILjava/lang/Integer;Ljava/lang/Integer;)Landroidx/compose/ui/input/pointer/PointerInputEventData;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MotionEventAdapter {
    private long nextId;
    private final Map<Integer, PointerId> motionEventToComposePointerIdMap = new LinkedHashMap();
    private final List<PointerInputEventData> pointers = new ArrayList();

    public static /* synthetic */ void getMotionEventToComposePointerIdMap$ui_release$annotations() {
    }

    public final Map<Integer, PointerId> getMotionEventToComposePointerIdMap$ui_release() {
        return this.motionEventToComposePointerIdMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0056 A[LOOP:0: B:18:0x0056->B:20:0x0068, LOOP_START, PHI: r1 r10 r11
      0x0056: PHI (r1v2 int) = (r1v1 int), (r1v3 int) binds: [B:17:0x0054, B:20:0x0068] A[DONT_GENERATE, DONT_INLINE]
      0x0056: PHI (r10v2 'motionEvent' android.view.MotionEvent) = (r10v0 'motionEvent' android.view.MotionEvent), (r10v4 'motionEvent' android.view.MotionEvent) binds: [B:17:0x0054, B:20:0x0068] A[DONT_GENERATE, DONT_INLINE]
      0x0056: PHI (r11v2 'positionCalculator' androidx.compose.ui.input.pointer.PositionCalculator) = 
      (r11v0 'positionCalculator' androidx.compose.ui.input.pointer.PositionCalculator)
      (r11v3 'positionCalculator' androidx.compose.ui.input.pointer.PositionCalculator)
     binds: [B:17:0x0054, B:20:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.input.pointer.PointerInputEvent convertToPointerInputEvent$ui_release(android.view.MotionEvent r10, androidx.compose.ui.input.pointer.PositionCalculator r11) {
        /*
            r9 = this;
            java.lang.String r0 = "motionEvent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "positionCalculator"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            int r0 = r10.getActionMasked()
            r1 = 3
            r2 = 0
            if (r0 != r1) goto L18
            java.util.Map<java.lang.Integer, androidx.compose.ui.input.pointer.PointerId> r10 = r9.motionEventToComposePointerIdMap
            r10.clear()
            return r2
        L18:
            int r0 = r10.getActionMasked()
            r1 = 0
            if (r0 == 0) goto L2d
            r3 = 5
            if (r0 == r3) goto L24
            r7 = r2
            goto L32
        L24:
            int r0 = r10.getActionIndex()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L31
        L2d:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
        L31:
            r7 = r0
        L32:
            int r0 = r10.getActionMasked()
            r3 = 1
            if (r0 == r3) goto L46
            r3 = 6
            if (r0 == r3) goto L3d
            goto L4a
        L3d:
            int r0 = r10.getActionIndex()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            goto L4a
        L46:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
        L4a:
            r8 = r2
            java.util.List<androidx.compose.ui.input.pointer.PointerInputEventData> r0 = r9.pointers
            r0.clear()
            int r0 = r10.getPointerCount()
            if (r0 <= 0) goto L6b
        L56:
            r6 = r1
            int r1 = r6 + 1
            java.util.List<androidx.compose.ui.input.pointer.PointerInputEventData> r2 = r9.pointers
            r3 = r9
            r5 = r10
            r4 = r11
            androidx.compose.ui.input.pointer.PointerInputEventData r10 = r3.createPointerInputEventData(r4, r5, r6, r7, r8)
            r2.add(r10)
            if (r1 < r0) goto L68
            goto L6d
        L68:
            r11 = r4
            r10 = r5
            goto L56
        L6b:
            r3 = r9
            r5 = r10
        L6d:
            androidx.compose.ui.input.pointer.PointerInputEvent r10 = new androidx.compose.ui.input.pointer.PointerInputEvent
            long r0 = r5.getEventTime()
            java.util.List<androidx.compose.ui.input.pointer.PointerInputEventData> r11 = r3.pointers
            r10.<init>(r0, r11, r5)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.MotionEventAdapter.convertToPointerInputEvent$ui_release(android.view.MotionEvent, androidx.compose.ui.input.pointer.PositionCalculator):androidx.compose.ui.input.pointer.PointerInputEvent");
    }

    private final PointerInputEventData createPointerInputEventData(PositionCalculator positionCalculator, MotionEvent motionEvent, int index, Integer downIndex, Integer upIndex) {
        PointerId pointerId;
        PointerInputEventData m1834createPointerInputEventDataVnAYq1g;
        int pointerId2 = motionEvent.getPointerId(index);
        if (downIndex != null && index == downIndex.intValue()) {
            long j = this.nextId;
            this.nextId = 1 + j;
            pointerId = PointerId.m1838boximpl(PointerId.m1839constructorimpl(j));
            getMotionEventToComposePointerIdMap$ui_release().put(Integer.valueOf(pointerId2), PointerId.m1838boximpl(pointerId.getValue()));
        } else if (upIndex != null && index == upIndex.intValue()) {
            pointerId = this.motionEventToComposePointerIdMap.remove(Integer.valueOf(pointerId2));
        } else {
            pointerId = this.motionEventToComposePointerIdMap.get(Integer.valueOf(pointerId2));
        }
        if (pointerId != null) {
            m1834createPointerInputEventDataVnAYq1g = MotionEventAdapter_androidKt.m1834createPointerInputEventDataVnAYq1g(positionCalculator, pointerId.getValue(), motionEvent.getEventTime(), motionEvent, index, upIndex);
            return m1834createPointerInputEventDataVnAYq1g;
        }
        throw new IllegalStateException("Compose assumes that all pointer ids in MotionEvents are first provided alongside ACTION_DOWN or ACTION_POINTER_DOWN.  This appears not to have been the case");
    }
}
