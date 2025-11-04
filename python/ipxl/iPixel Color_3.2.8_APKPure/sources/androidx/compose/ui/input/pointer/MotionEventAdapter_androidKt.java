package androidx.compose.ui.input.pointer;

import android.os.Build;
import android.view.MotionEvent;
import androidx.compose.ui.geometry.OffsetKt;
import kotlin.Metadata;

/* compiled from: MotionEventAdapter.android.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001aG\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u000f"}, d2 = {"createPointerInputEventData", "Landroidx/compose/ui/input/pointer/PointerInputEventData;", "positionCalculator", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "timestamp", "", "motionEvent", "Landroid/view/MotionEvent;", "index", "", "upIndex", "createPointerInputEventData-VnAYq1g", "(Landroidx/compose/ui/input/pointer/PositionCalculator;JJLandroid/view/MotionEvent;ILjava/lang/Integer;)Landroidx/compose/ui/input/pointer/PointerInputEventData;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MotionEventAdapter_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createPointerInputEventData-VnAYq1g, reason: not valid java name */
    public static final PointerInputEventData m1834createPointerInputEventDataVnAYq1g(PositionCalculator positionCalculator, long j, long j2, MotionEvent motionEvent, int i, Integer num) {
        long j3;
        long mo1899localToScreenMKHz9U;
        int m1898getUnknownT8wyACA;
        long Offset = OffsetKt.Offset(motionEvent.getX(i), motionEvent.getY(i));
        if (i == 0) {
            long Offset2 = OffsetKt.Offset(motionEvent.getRawX(), motionEvent.getRawY());
            mo1899localToScreenMKHz9U = Offset2;
            j3 = positionCalculator.mo1900screenToLocalMKHz9U(Offset2);
        } else if (Build.VERSION.SDK_INT >= 29) {
            long m1835toRawOffsetdBAh8RU = MotionEventHelper.INSTANCE.m1835toRawOffsetdBAh8RU(motionEvent, i);
            mo1899localToScreenMKHz9U = m1835toRawOffsetdBAh8RU;
            j3 = positionCalculator.mo1900screenToLocalMKHz9U(m1835toRawOffsetdBAh8RU);
        } else {
            j3 = Offset;
            mo1899localToScreenMKHz9U = positionCalculator.mo1899localToScreenMKHz9U(Offset);
        }
        int toolType = motionEvent.getToolType(i);
        boolean z = true;
        if (toolType == 0) {
            m1898getUnknownT8wyACA = PointerType.INSTANCE.m1898getUnknownT8wyACA();
        } else if (toolType == 1) {
            m1898getUnknownT8wyACA = PointerType.INSTANCE.m1897getTouchT8wyACA();
        } else if (toolType == 2) {
            m1898getUnknownT8wyACA = PointerType.INSTANCE.m1896getStylusT8wyACA();
        } else if (toolType == 3) {
            m1898getUnknownT8wyACA = PointerType.INSTANCE.m1895getMouseT8wyACA();
        } else if (toolType == 4) {
            m1898getUnknownT8wyACA = PointerType.INSTANCE.m1894getEraserT8wyACA();
        } else {
            m1898getUnknownT8wyACA = PointerType.INSTANCE.m1898getUnknownT8wyACA();
        }
        int i2 = m1898getUnknownT8wyACA;
        if (num != null && i == num.intValue()) {
            z = false;
        }
        return new PointerInputEventData(j, j2, mo1899localToScreenMKHz9U, j3, z, i2, null);
    }
}
