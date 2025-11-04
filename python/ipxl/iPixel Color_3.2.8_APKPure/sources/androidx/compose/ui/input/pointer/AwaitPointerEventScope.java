package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SuspendingPointerInputFilter.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001b\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0010H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001b\u0010\u0006\u001a\u00020\u0007X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Landroidx/compose/ui/unit/Density;", "currentEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "getCurrentEvent", "()Landroidx/compose/ui/input/pointer/PointerEvent;", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "awaitPointerEvent", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "(Landroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface AwaitPointerEventScope extends Density {
    Object awaitPointerEvent(PointerEventPass pointerEventPass, Continuation<? super PointerEvent> continuation);

    PointerEvent getCurrentEvent();

    /* renamed from: getSize-YbymL2g, reason: not valid java name */
    long mo1820getSizeYbymL2g();

    ViewConfiguration getViewConfiguration();

    /* compiled from: SuspendingPointerInputFilter.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m1821roundToPxR2X_6o(AwaitPointerEventScope awaitPointerEventScope, long j) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2418roundToPxR2X_6o(awaitPointerEventScope, j);
        }

        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m1822roundToPx0680j_4(AwaitPointerEventScope awaitPointerEventScope, float f) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2419roundToPx0680j_4(awaitPointerEventScope, f);
        }

        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m1823toDpGaN1DYA(AwaitPointerEventScope awaitPointerEventScope, long j) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2420toDpGaN1DYA(awaitPointerEventScope, j);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m1824toDpu2uoSUM(AwaitPointerEventScope awaitPointerEventScope, float f) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2421toDpu2uoSUM(awaitPointerEventScope, f);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m1825toDpu2uoSUM(AwaitPointerEventScope awaitPointerEventScope, int i) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2422toDpu2uoSUM((Density) awaitPointerEventScope, i);
        }

        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m1826toPxR2X_6o(AwaitPointerEventScope awaitPointerEventScope, long j) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2423toPxR2X_6o(awaitPointerEventScope, j);
        }

        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m1827toPx0680j_4(AwaitPointerEventScope awaitPointerEventScope, float f) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2424toPx0680j_4(awaitPointerEventScope, f);
        }

        public static Rect toRect(AwaitPointerEventScope awaitPointerEventScope, DpRect receiver) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return Density.DefaultImpls.toRect(awaitPointerEventScope, receiver);
        }

        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m1828toSp0xMU5do(AwaitPointerEventScope awaitPointerEventScope, float f) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2425toSp0xMU5do(awaitPointerEventScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m1829toSpkPz2Gy4(AwaitPointerEventScope awaitPointerEventScope, float f) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2426toSpkPz2Gy4(awaitPointerEventScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m1830toSpkPz2Gy4(AwaitPointerEventScope awaitPointerEventScope, int i) {
            Intrinsics.checkNotNullParameter(awaitPointerEventScope, "this");
            return Density.DefaultImpls.m2427toSpkPz2Gy4((Density) awaitPointerEventScope, i);
        }

        public static /* synthetic */ Object awaitPointerEvent$default(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: awaitPointerEvent");
            }
            if ((i & 1) != 0) {
                pointerEventPass = PointerEventPass.Main;
            }
            return awaitPointerEventScope.awaitPointerEvent(pointerEventPass, continuation);
        }
    }
}
