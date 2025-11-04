package androidx.compose.ui.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.DpRect;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubcomposeLayout.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH&¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/layout/MeasureScope;", "subcompose", "", "Landroidx/compose/ui/layout/Measurable;", "slotId", "", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface SubcomposeMeasureScope extends MeasureScope {
    List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content);

    /* compiled from: SubcomposeLayout.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static MeasureResult layout(SubcomposeMeasureScope subcomposeMeasureScope, int i, int i2, Map<AlignmentLine, Integer> alignmentLines, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            Intrinsics.checkNotNullParameter(alignmentLines, "alignmentLines");
            Intrinsics.checkNotNullParameter(placementBlock, "placementBlock");
            return MeasureScope.DefaultImpls.layout(subcomposeMeasureScope, i, i2, alignmentLines, placementBlock);
        }

        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m2007roundToPxR2X_6o(SubcomposeMeasureScope subcomposeMeasureScope, long j) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1952roundToPxR2X_6o(subcomposeMeasureScope, j);
        }

        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m2008roundToPx0680j_4(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1953roundToPx0680j_4(subcomposeMeasureScope, f);
        }

        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m2009toDpGaN1DYA(SubcomposeMeasureScope subcomposeMeasureScope, long j) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1954toDpGaN1DYA(subcomposeMeasureScope, j);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2010toDpu2uoSUM(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1955toDpu2uoSUM(subcomposeMeasureScope, f);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2011toDpu2uoSUM(SubcomposeMeasureScope subcomposeMeasureScope, int i) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1956toDpu2uoSUM((MeasureScope) subcomposeMeasureScope, i);
        }

        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m2012toPxR2X_6o(SubcomposeMeasureScope subcomposeMeasureScope, long j) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1957toPxR2X_6o(subcomposeMeasureScope, j);
        }

        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m2013toPx0680j_4(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1958toPx0680j_4(subcomposeMeasureScope, f);
        }

        public static Rect toRect(SubcomposeMeasureScope subcomposeMeasureScope, DpRect receiver) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return MeasureScope.DefaultImpls.toRect(subcomposeMeasureScope, receiver);
        }

        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m2014toSp0xMU5do(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1959toSp0xMU5do(subcomposeMeasureScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2015toSpkPz2Gy4(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1960toSpkPz2Gy4(subcomposeMeasureScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2016toSpkPz2Gy4(SubcomposeMeasureScope subcomposeMeasureScope, int i) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m1961toSpkPz2Gy4((MeasureScope) subcomposeMeasureScope, i);
        }
    }
}
