package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.input.nestedscroll.NestedScrollDelegatingWrapper;
import androidx.compose.ui.input.pointer.PointerInputFilter;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsWrapper;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.exifinterface.media.ExifInterface;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: InnerPlaceable.kt */
@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 j2\u00020\u00012\u00020\u0002:\u0001jB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\n\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\u001bH\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\n\u0010 \u001a\u0004\u0018\u00010\u0019H\u0016J\n\u0010!\u001a\u0004\u0018\u00010\u001bH\u0016J\n\u0010\"\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010#\u001a\u00020$H\u0014J+\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-J+\u0010.\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002000*H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u0010-J]\u00102\u001a\u00020&\"\u0004\b\u0000\u001032\u0006\u0010'\u001a\u00020(2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H30*2)\u00105\u001a%\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u0002H30*\u0012\u0004\u0012\u00020&06¢\u0006\u0002\b7H\u0082\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b8\u00109J\u0010\u0010:\u001a\u00020\u00152\u0006\u0010;\u001a\u00020\u0015H\u0016J\u0010\u0010<\u001a\u00020\u00152\u0006\u0010=\u001a\u00020\u0015H\u0016J\u001d\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bB\u0010CJ\u0010\u0010D\u001a\u00020\u00152\u0006\u0010;\u001a\u00020\u0015H\u0016J\u0010\u0010E\u001a\u00020\u00152\u0006\u0010=\u001a\u00020\u0015H\u0016J\u0010\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020HH\u0014J@\u0010I\u001a\u00020&2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u00072\u0019\u0010M\u001a\u0015\u0012\u0004\u0012\u00020O\u0012\u0004\u0012\u00020&\u0018\u00010N¢\u0006\u0002\b7H\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bP\u0010QJ\u001a\u0010R\u001a\u00020\u0015*\u00020SH\u0097\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bT\u0010UJ\u001a\u0010R\u001a\u00020\u0015*\u00020VH\u0097\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bW\u0010XJ\u001a\u0010Y\u001a\u00020S*\u00020VH\u0097\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bZ\u0010[J\u001d\u0010Y\u001a\u00020S*\u00020\u0007H\u0097\u0001ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\\\u0010]J\u001d\u0010Y\u001a\u00020S*\u00020\u0015H\u0097\u0001ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\\\u0010^J\u001a\u0010_\u001a\u00020\u0007*\u00020SH\u0097\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b`\u0010]J\u001a\u0010_\u001a\u00020\u0007*\u00020VH\u0097\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\ba\u0010[J\r\u0010b\u001a\u00020c*\u00020dH\u0097\u0001J\u001a\u0010e\u001a\u00020V*\u00020SH\u0097\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bf\u0010gJ\u001d\u0010e\u001a\u00020V*\u00020\u0007H\u0097\u0001ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bh\u0010gJ\u001d\u0010e\u001a\u00020V*\u00020\u0015H\u0097\u0001ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bh\u0010iR\u0014\u0010\u0006\u001a\u00020\u00078\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006k"}, d2 = {"Landroidx/compose/ui/node/InnerPlaceable;", "Landroidx/compose/ui/node/LayoutNodeWrapper;", "Landroidx/compose/ui/unit/Density;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "(Landroidx/compose/ui/node/LayoutNode;)V", "density", "", "getDensity", "()F", "fontScale", "getFontScale", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "getMeasureScope", "()Landroidx/compose/ui/layout/MeasureScope;", "parentData", "", "getParentData", "()Ljava/lang/Object;", "calculateAlignmentLine", "", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "findLastFocusWrapper", "Landroidx/compose/ui/node/ModifiedFocusNode;", "findLastKeyInputWrapper", "Landroidx/compose/ui/node/ModifiedKeyInputNode;", "findNextFocusWrapper", "findNextKeyInputWrapper", "findNextNestedScrollWrapper", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDelegatingWrapper;", "findPreviousFocusWrapper", "findPreviousKeyInputWrapper", "findPreviousNestedScrollWrapper", "getWrappedByCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "hitTest", "", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitPointerInputFilters", "", "Landroidx/compose/ui/input/pointer/PointerInputFilter;", "hitTest-3MmeM6k", "(JLjava/util/List;)V", "hitTestSemantics", "hitSemanticsWrappers", "Landroidx/compose/ui/semantics/SemanticsWrapper;", "hitTestSemantics-3MmeM6k", "hitTestSubtree", ExifInterface.GPS_DIRECTION_TRUE, "hitResult", "nodeHitTest", "Lkotlin/Function3;", "Lkotlin/ExtensionFunctionType;", "hitTestSubtree-9KIMszo", "(JLjava/util/List;Lkotlin/jvm/functions/Function3;)V", "maxIntrinsicHeight", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/Placeable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "minIntrinsicHeight", "minIntrinsicWidth", "performDraw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "placeAt", PlayerFinal.PLAYER_POSITION, "Landroidx/compose/ui/unit/IntOffset;", "zIndex", "layerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "roundToPx", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-GaN1DYA", "(J)F", "toDp-u2uoSUM", "(F)F", "(I)F", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSp", "toSp-0xMU5do", "(F)J", "toSp-kPz2Gy4", "(I)J", "Companion", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class InnerPlaceable extends LayoutNodeWrapper implements Density {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Paint innerBoundsPaint;
    private final /* synthetic */ MeasureScope $$delegate_0;

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public ModifiedFocusNode findNextFocusWrapper() {
        return null;
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public ModifiedKeyInputNode findNextKeyInputWrapper() {
        return null;
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public NestedScrollDelegatingWrapper findNextNestedScrollWrapper() {
        return null;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.$$delegate_0.getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getFontScale() {
        return this.$$delegate_0.getFontScale();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        return null;
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx--R2X_6o */
    public int mo367roundToPxR2X_6o(long j) {
        return this.$$delegate_0.mo367roundToPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx-0680j_4 */
    public int mo368roundToPx0680j_4(float f) {
        return this.$$delegate_0.mo368roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-GaN1DYA */
    public float mo369toDpGaN1DYA(long j) {
        return this.$$delegate_0.mo369toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo370toDpu2uoSUM(float f) {
        return this.$$delegate_0.mo370toDpu2uoSUM(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo371toDpu2uoSUM(int i) {
        return this.$$delegate_0.mo371toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx--R2X_6o */
    public float mo372toPxR2X_6o(long j) {
        return this.$$delegate_0.mo372toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx-0680j_4 */
    public float mo373toPx0680j_4(float f) {
        return this.$$delegate_0.mo373toPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    public Rect toRect(DpRect dpRect) {
        Intrinsics.checkNotNullParameter(dpRect, "<this>");
        return this.$$delegate_0.toRect(dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-0xMU5do */
    public long mo374toSp0xMU5do(float f) {
        return this.$$delegate_0.mo374toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo375toSpkPz2Gy4(float f) {
        return this.$$delegate_0.mo375toSpkPz2Gy4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo376toSpkPz2Gy4(int i) {
        return this.$$delegate_0.mo376toSpkPz2Gy4(i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InnerPlaceable(LayoutNode layoutNode) {
        super(layoutNode);
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.$$delegate_0 = layoutNode.getMeasureScope();
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public MeasureScope getMeasureScope() {
        return getLayoutNode().getMeasureScope();
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    public Placeable mo1932measureBRTryo0(long constraints) {
        m1970setMeasurementConstraintsBRTryo0(constraints);
        getLayoutNode().handleMeasureResult$ui_release(getLayoutNode().getMeasurePolicy().mo1949measure3p2s80s(getLayoutNode().getMeasureScope(), getLayoutNode().getChildren$ui_release(), constraints));
        return this;
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public ModifiedFocusNode findPreviousFocusWrapper() {
        LayoutNodeWrapper wrappedBy$ui_release = getWrappedBy();
        if (wrappedBy$ui_release == null) {
            return null;
        }
        return wrappedBy$ui_release.findPreviousFocusWrapper();
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public ModifiedFocusNode findLastFocusWrapper() {
        return findPreviousFocusWrapper();
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public ModifiedKeyInputNode findPreviousKeyInputWrapper() {
        LayoutNodeWrapper wrappedBy$ui_release = getWrappedBy();
        if (wrappedBy$ui_release == null) {
            return null;
        }
        return wrappedBy$ui_release.findPreviousKeyInputWrapper();
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public NestedScrollDelegatingWrapper findPreviousNestedScrollWrapper() {
        LayoutNodeWrapper wrappedBy$ui_release = getWrappedBy();
        if (wrappedBy$ui_release == null) {
            return null;
        }
        return wrappedBy$ui_release.findPreviousNestedScrollWrapper();
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public ModifiedKeyInputNode findLastKeyInputWrapper() {
        return findPreviousKeyInputWrapper();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicWidth(int height) {
        return getLayoutNode().getIntrinsicsPolicy().minIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicHeight(int width) {
        return getLayoutNode().getIntrinsicsPolicy().minIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicWidth(int height) {
        return getLayoutNode().getIntrinsicsPolicy().maxIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicHeight(int width) {
        return getLayoutNode().getIntrinsicsPolicy().maxIntrinsicHeight(width);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.node.LayoutNodeWrapper, androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public void mo1933placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        super.mo1933placeAtf8xVGno(position, zIndex, layerBlock);
        LayoutNodeWrapper wrappedBy$ui_release = getWrappedBy();
        if (wrappedBy$ui_release != null && wrappedBy$ui_release.getIsShallowPlacing()) {
            return;
        }
        getLayoutNode().onNodePlaced$ui_release();
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public int calculateAlignmentLine(AlignmentLine alignmentLine) {
        Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
        Integer num = getLayoutNode().calculateAlignmentLines$ui_release().get(alignmentLine);
        if (num == null) {
            return Integer.MIN_VALUE;
        }
        return num.intValue();
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    protected void performDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Owner requireOwner = LayoutNodeKt.requireOwner(getLayoutNode());
        MutableVector<LayoutNode> zSortedChildren = getLayoutNode().getZSortedChildren();
        int size = zSortedChildren.getSize();
        if (size > 0) {
            LayoutNode[] content = zSortedChildren.getContent();
            int i = 0;
            do {
                LayoutNode layoutNode = content[i];
                if (layoutNode.getIsPlaced()) {
                    layoutNode.draw$ui_release(canvas);
                }
                i++;
            } while (i < size);
        }
        if (requireOwner.getShowLayoutBounds()) {
            drawBorder(canvas, innerBoundsPaint);
        }
    }

    /* renamed from: hitTestSubtree-9KIMszo, reason: not valid java name */
    private final <T> void m2021hitTestSubtree9KIMszo(long pointerPosition, List<T> hitResult, Function3<? super LayoutNode, ? super Offset, ? super List<T>, Unit> nodeHitTest) {
        if (m2040withinLayerBoundsk4lQ0M(pointerPosition)) {
            int size = hitResult.size();
            MutableVector<LayoutNode> zSortedChildren = getLayoutNode().getZSortedChildren();
            int size2 = zSortedChildren.getSize();
            if (size2 > 0) {
                int i = size2 - 1;
                LayoutNode[] content = zSortedChildren.getContent();
                do {
                    LayoutNode layoutNode = content[i];
                    if (layoutNode.getIsPlaced()) {
                        nodeHitTest.invoke(layoutNode, Offset.m431boximpl(pointerPosition), hitResult);
                        if (hitResult.size() > size) {
                            return;
                        }
                    }
                    i--;
                } while (i >= 0);
            }
        }
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    protected LayoutCoordinates getWrappedByCoordinates() {
        return this;
    }

    /* compiled from: InnerPlaceable.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/node/InnerPlaceable$Companion;", "", "()V", "innerBoundsPaint", "Landroidx/compose/ui/graphics/Paint;", "getInnerBoundsPaint", "()Landroidx/compose/ui/graphics/Paint;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Paint getInnerBoundsPaint() {
            return InnerPlaceable.innerBoundsPaint;
        }
    }

    static {
        Paint Paint = AndroidPaint_androidKt.Paint();
        Paint.mo563setColor8_81llA(Color.INSTANCE.m705getRed0d7_KjU());
        Paint.setStrokeWidth(1.0f);
        Paint.mo567setStylek9PVt8s(PaintingStyle.INSTANCE.m872getStrokeTiuSbCo());
        innerBoundsPaint = Paint;
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    /* renamed from: hitTest-3MmeM6k */
    public void mo2018hitTest3MmeM6k(long pointerPosition, List<PointerInputFilter> hitPointerInputFilters) {
        Intrinsics.checkNotNullParameter(hitPointerInputFilters, "hitPointerInputFilters");
        if (m2040withinLayerBoundsk4lQ0M(pointerPosition)) {
            int size = hitPointerInputFilters.size();
            MutableVector<LayoutNode> zSortedChildren = getLayoutNode().getZSortedChildren();
            int size2 = zSortedChildren.getSize();
            if (size2 > 0) {
                int i = size2 - 1;
                LayoutNode[] content = zSortedChildren.getContent();
                do {
                    LayoutNode layoutNode = content[i];
                    if (layoutNode.getIsPlaced()) {
                        layoutNode.m2023hitTest3MmeM6k$ui_release(pointerPosition, hitPointerInputFilters);
                        if (hitPointerInputFilters.size() > size) {
                            return;
                        }
                    }
                    i--;
                } while (i >= 0);
            }
        }
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    /* renamed from: hitTestSemantics-3MmeM6k */
    public void mo2019hitTestSemantics3MmeM6k(long pointerPosition, List<SemanticsWrapper> hitSemanticsWrappers) {
        Intrinsics.checkNotNullParameter(hitSemanticsWrappers, "hitSemanticsWrappers");
        if (m2040withinLayerBoundsk4lQ0M(pointerPosition)) {
            int size = hitSemanticsWrappers.size();
            MutableVector<LayoutNode> zSortedChildren = getLayoutNode().getZSortedChildren();
            int size2 = zSortedChildren.getSize();
            if (size2 > 0) {
                int i = size2 - 1;
                LayoutNode[] content = zSortedChildren.getContent();
                do {
                    LayoutNode layoutNode = content[i];
                    if (layoutNode.getIsPlaced()) {
                        layoutNode.m2024hitTestSemantics3MmeM6k$ui_release(pointerPosition, hitSemanticsWrappers);
                        if (hitSemanticsWrappers.size() > size) {
                            return;
                        }
                    }
                    i--;
                } while (i >= 0);
            }
        }
    }
}
