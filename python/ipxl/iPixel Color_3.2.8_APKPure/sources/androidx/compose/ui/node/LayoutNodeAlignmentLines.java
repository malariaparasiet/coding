package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: LayoutNodeAlignmentLines.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0$J\u0006\u0010%\u001a\u00020&J\r\u0010'\u001a\u00020&H\u0000¢\u0006\u0002\b(J\r\u0010)\u001a\u00020&H\u0000¢\u0006\u0002\b*R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\fR\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\fR\u001a\u0010\u0017\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000eR\u001a\u0010\u001a\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\f\"\u0004\b\u001c\u0010\u000eR\u001a\u0010\u001d\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\f\"\u0004\b\u001f\u0010\u000eR\u001a\u0010 \u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\f\"\u0004\b\"\u0010\u000e¨\u0006+"}, d2 = {"Landroidx/compose/ui/node/LayoutNodeAlignmentLines;", "", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "(Landroidx/compose/ui/node/LayoutNode;)V", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "", "dirty", "", "getDirty$ui_release", "()Z", "setDirty$ui_release", "(Z)V", "previousUsedDuringParentLayout", "getPreviousUsedDuringParentLayout$ui_release", "setPreviousUsedDuringParentLayout$ui_release", "queried", "getQueried$ui_release", "queryOwner", "required", "getRequired$ui_release", "usedByModifierLayout", "getUsedByModifierLayout$ui_release", "setUsedByModifierLayout$ui_release", "usedByModifierMeasurement", "getUsedByModifierMeasurement$ui_release", "setUsedByModifierMeasurement$ui_release", "usedDuringParentLayout", "getUsedDuringParentLayout$ui_release", "setUsedDuringParentLayout$ui_release", "usedDuringParentMeasurement", "getUsedDuringParentMeasurement$ui_release", "setUsedDuringParentMeasurement$ui_release", "getLastCalculation", "", "recalculate", "", "recalculateQueryOwner", "recalculateQueryOwner$ui_release", "reset", "reset$ui_release", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutNodeAlignmentLines {
    private final Map<AlignmentLine, Integer> alignmentLines;
    private boolean dirty;
    private final LayoutNode layoutNode;
    private boolean previousUsedDuringParentLayout;
    private LayoutNode queryOwner;
    private boolean usedByModifierLayout;
    private boolean usedByModifierMeasurement;
    private boolean usedDuringParentLayout;
    private boolean usedDuringParentMeasurement;

    public LayoutNodeAlignmentLines(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutNode = layoutNode;
        this.dirty = true;
        this.alignmentLines = new HashMap();
    }

    /* renamed from: getDirty$ui_release, reason: from getter */
    public final boolean getDirty() {
        return this.dirty;
    }

    public final void setDirty$ui_release(boolean z) {
        this.dirty = z;
    }

    /* renamed from: getUsedDuringParentMeasurement$ui_release, reason: from getter */
    public final boolean getUsedDuringParentMeasurement() {
        return this.usedDuringParentMeasurement;
    }

    public final void setUsedDuringParentMeasurement$ui_release(boolean z) {
        this.usedDuringParentMeasurement = z;
    }

    /* renamed from: getUsedDuringParentLayout$ui_release, reason: from getter */
    public final boolean getUsedDuringParentLayout() {
        return this.usedDuringParentLayout;
    }

    public final void setUsedDuringParentLayout$ui_release(boolean z) {
        this.usedDuringParentLayout = z;
    }

    /* renamed from: getPreviousUsedDuringParentLayout$ui_release, reason: from getter */
    public final boolean getPreviousUsedDuringParentLayout() {
        return this.previousUsedDuringParentLayout;
    }

    public final void setPreviousUsedDuringParentLayout$ui_release(boolean z) {
        this.previousUsedDuringParentLayout = z;
    }

    /* renamed from: getUsedByModifierMeasurement$ui_release, reason: from getter */
    public final boolean getUsedByModifierMeasurement() {
        return this.usedByModifierMeasurement;
    }

    public final void setUsedByModifierMeasurement$ui_release(boolean z) {
        this.usedByModifierMeasurement = z;
    }

    /* renamed from: getUsedByModifierLayout$ui_release, reason: from getter */
    public final boolean getUsedByModifierLayout() {
        return this.usedByModifierLayout;
    }

    public final void setUsedByModifierLayout$ui_release(boolean z) {
        this.usedByModifierLayout = z;
    }

    public final boolean getQueried$ui_release() {
        return this.usedDuringParentMeasurement || this.previousUsedDuringParentLayout || this.usedByModifierMeasurement || this.usedByModifierLayout;
    }

    public final boolean getRequired$ui_release() {
        recalculateQueryOwner$ui_release();
        return this.queryOwner != null;
    }

    public final void recalculateQueryOwner$ui_release() {
        LayoutNode layoutNode;
        LayoutNodeAlignmentLines alignmentLines;
        LayoutNodeAlignmentLines alignmentLines2;
        if (getQueried$ui_release()) {
            layoutNode = this.layoutNode;
        } else {
            LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null) {
                return;
            }
            layoutNode = parent$ui_release.getAlignmentLines().queryOwner;
            if (layoutNode == null || !layoutNode.getAlignmentLines().getQueried$ui_release()) {
                LayoutNode layoutNode2 = this.queryOwner;
                if (layoutNode2 == null || layoutNode2.getAlignmentLines().getQueried$ui_release()) {
                    return;
                }
                LayoutNode parent$ui_release2 = layoutNode2.getParent$ui_release();
                if (parent$ui_release2 != null && (alignmentLines2 = parent$ui_release2.getAlignmentLines()) != null) {
                    alignmentLines2.recalculateQueryOwner$ui_release();
                }
                LayoutNode parent$ui_release3 = layoutNode2.getParent$ui_release();
                layoutNode = (parent$ui_release3 == null || (alignmentLines = parent$ui_release3.getAlignmentLines()) == null) ? null : alignmentLines.queryOwner;
            }
        }
        this.queryOwner = layoutNode;
    }

    public final Map<AlignmentLine, Integer> getLastCalculation() {
        return this.alignmentLines;
    }

    public final void recalculate() {
        this.alignmentLines.clear();
        MutableVector<LayoutNode> mutableVector = this.layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i = 0;
            do {
                LayoutNode layoutNode = content[i];
                if (layoutNode.getIsPlaced()) {
                    if (layoutNode.getAlignmentLines().getDirty()) {
                        layoutNode.layoutChildren$ui_release();
                    }
                    for (Map.Entry<AlignmentLine, Integer> entry : layoutNode.getAlignmentLines().alignmentLines.entrySet()) {
                        recalculate$addAlignmentLine(this, entry.getKey(), entry.getValue().intValue(), layoutNode.getInnerLayoutNodeWrapper());
                    }
                    LayoutNodeWrapper wrappedBy = layoutNode.getInnerLayoutNodeWrapper().getWrappedBy();
                    Intrinsics.checkNotNull(wrappedBy);
                    while (!Intrinsics.areEqual(wrappedBy, this.layoutNode.getInnerLayoutNodeWrapper())) {
                        for (AlignmentLine alignmentLine : wrappedBy.getProvidedAlignmentLines()) {
                            recalculate$addAlignmentLine(this, alignmentLine, wrappedBy.get(alignmentLine), wrappedBy);
                        }
                        wrappedBy = wrappedBy.getWrappedBy();
                        Intrinsics.checkNotNull(wrappedBy);
                    }
                }
                i++;
            } while (i < size);
        }
        this.alignmentLines.putAll(this.layoutNode.getInnerLayoutNodeWrapper().getMeasureResult().getAlignmentLines());
        this.dirty = false;
    }

    private static final void recalculate$addAlignmentLine(LayoutNodeAlignmentLines layoutNodeAlignmentLines, AlignmentLine alignmentLine, int i, LayoutNodeWrapper layoutNodeWrapper) {
        int roundToInt;
        float f = i;
        long Offset = OffsetKt.Offset(f, f);
        while (true) {
            Offset = layoutNodeWrapper.m2039toParentPositionMKHz9U(Offset);
            layoutNodeWrapper = layoutNodeWrapper.getWrappedBy();
            Intrinsics.checkNotNull(layoutNodeWrapper);
            if (Intrinsics.areEqual(layoutNodeWrapper, layoutNodeAlignmentLines.layoutNode.getInnerLayoutNodeWrapper())) {
                break;
            } else if (layoutNodeWrapper.getProvidedAlignmentLines().contains(alignmentLine)) {
                float f2 = layoutNodeWrapper.get(alignmentLine);
                Offset = OffsetKt.Offset(f2, f2);
            }
        }
        if (alignmentLine instanceof HorizontalAlignmentLine) {
            roundToInt = MathKt.roundToInt(Offset.m443getYimpl(Offset));
        } else {
            roundToInt = MathKt.roundToInt(Offset.m442getXimpl(Offset));
        }
        Map<AlignmentLine, Integer> map = layoutNodeAlignmentLines.alignmentLines;
        if (map.containsKey(alignmentLine)) {
            roundToInt = AlignmentLineKt.merge(alignmentLine, ((Number) MapsKt.getValue(layoutNodeAlignmentLines.alignmentLines, alignmentLine)).intValue(), roundToInt);
        }
        map.put(alignmentLine, Integer.valueOf(roundToInt));
    }

    public final void reset$ui_release() {
        this.dirty = true;
        this.usedDuringParentMeasurement = false;
        this.previousUsedDuringParentLayout = false;
        this.usedDuringParentLayout = false;
        this.usedByModifierMeasurement = false;
        this.usedByModifierLayout = false;
        this.queryOwner = null;
    }
}
