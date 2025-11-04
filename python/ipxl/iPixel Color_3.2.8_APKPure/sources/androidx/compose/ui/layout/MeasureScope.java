package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MeasureScope.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001JG\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0016¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "layout", "Landroidx/compose/ui/layout/MeasureResult;", "width", "", "height", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "placementBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface MeasureScope extends IntrinsicMeasureScope {
    MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super Placeable.PlacementScope, Unit> placementBlock);

    /* compiled from: MeasureScope.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m1952roundToPxR2X_6o(MeasureScope measureScope, long j) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1934roundToPxR2X_6o(measureScope, j);
        }

        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m1953roundToPx0680j_4(MeasureScope measureScope, float f) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1935roundToPx0680j_4(measureScope, f);
        }

        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m1954toDpGaN1DYA(MeasureScope measureScope, long j) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1936toDpGaN1DYA(measureScope, j);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m1955toDpu2uoSUM(MeasureScope measureScope, float f) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1937toDpu2uoSUM(measureScope, f);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m1956toDpu2uoSUM(MeasureScope measureScope, int i) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1938toDpu2uoSUM((IntrinsicMeasureScope) measureScope, i);
        }

        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m1957toPxR2X_6o(MeasureScope measureScope, long j) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1939toPxR2X_6o(measureScope, j);
        }

        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m1958toPx0680j_4(MeasureScope measureScope, float f) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1940toPx0680j_4(measureScope, f);
        }

        public static Rect toRect(MeasureScope measureScope, DpRect receiver) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return IntrinsicMeasureScope.DefaultImpls.toRect(measureScope, receiver);
        }

        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m1959toSp0xMU5do(MeasureScope measureScope, float f) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1941toSp0xMU5do(measureScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m1960toSpkPz2Gy4(MeasureScope measureScope, float f) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1942toSpkPz2Gy4(measureScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m1961toSpkPz2Gy4(MeasureScope measureScope, int i) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            return IntrinsicMeasureScope.DefaultImpls.m1943toSpkPz2Gy4((IntrinsicMeasureScope) measureScope, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MeasureResult layout$default(MeasureScope measureScope, int i, int i2, Map map, Function1 function1, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: layout");
            }
            if ((i3 & 4) != 0) {
                map = MapsKt.emptyMap();
            }
            return measureScope.layout(i, i2, map, function1);
        }

        public static MeasureResult layout(final MeasureScope measureScope, final int i, final int i2, final Map<AlignmentLine, Integer> alignmentLines, final Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
            Intrinsics.checkNotNullParameter(measureScope, "this");
            Intrinsics.checkNotNullParameter(alignmentLines, "alignmentLines");
            Intrinsics.checkNotNullParameter(placementBlock, "placementBlock");
            return new MeasureResult(i, i2, alignmentLines, measureScope, placementBlock) { // from class: androidx.compose.ui.layout.MeasureScope$layout$1
                final /* synthetic */ Map<AlignmentLine, Integer> $alignmentLines;
                final /* synthetic */ int $height;
                final /* synthetic */ Function1<Placeable.PlacementScope, Unit> $placementBlock;
                final /* synthetic */ int $width;
                private final Map<AlignmentLine, Integer> alignmentLines;
                private final int height;
                final /* synthetic */ MeasureScope this$0;
                private final int width;

                /* JADX WARN: Multi-variable type inference failed */
                {
                    this.$width = i;
                    this.$height = i2;
                    this.$alignmentLines = alignmentLines;
                    this.this$0 = measureScope;
                    this.$placementBlock = placementBlock;
                    this.width = i;
                    this.height = i2;
                    this.alignmentLines = alignmentLines;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public int getWidth() {
                    return this.width;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public int getHeight() {
                    return this.height;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public Map<AlignmentLine, Integer> getAlignmentLines() {
                    return this.alignmentLines;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public void placeChildren() {
                    Placeable.PlacementScope.Companion companion = Placeable.PlacementScope.INSTANCE;
                    int i3 = this.$width;
                    LayoutDirection layoutDirection = this.this$0.getLayoutDirection();
                    Function1<Placeable.PlacementScope, Unit> function1 = this.$placementBlock;
                    int parentWidth = Placeable.PlacementScope.INSTANCE.getParentWidth();
                    LayoutDirection parentLayoutDirection = Placeable.PlacementScope.INSTANCE.getParentLayoutDirection();
                    Placeable.PlacementScope.Companion companion2 = Placeable.PlacementScope.INSTANCE;
                    Placeable.PlacementScope.parentWidth = i3;
                    Placeable.PlacementScope.Companion companion3 = Placeable.PlacementScope.INSTANCE;
                    Placeable.PlacementScope.parentLayoutDirection = layoutDirection;
                    function1.invoke(companion);
                    Placeable.PlacementScope.Companion companion4 = Placeable.PlacementScope.INSTANCE;
                    Placeable.PlacementScope.parentWidth = parentWidth;
                    Placeable.PlacementScope.Companion companion5 = Placeable.PlacementScope.INSTANCE;
                    Placeable.PlacementScope.parentLayoutDirection = parentLayoutDirection;
                }
            };
        }
    }
}
