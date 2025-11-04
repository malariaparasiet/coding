package androidx.compose.ui.node;

import androidx.compose.ui.draw.BuildDrawCacheParams;
import androidx.compose.ui.draw.DrawCacheModifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModifiedDrawNode.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001!B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0014J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\n\u0010 \u001a\u0004\u0018\u00010\u000bH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/compose/ui/node/ModifiedDrawNode;", "Landroidx/compose/ui/node/DelegatingLayoutNodeWrapper;", "Landroidx/compose/ui/draw/DrawModifier;", "Landroidx/compose/ui/node/OwnerScope;", "wrapped", "Landroidx/compose/ui/node/LayoutNodeWrapper;", "drawModifier", "(Landroidx/compose/ui/node/LayoutNodeWrapper;Landroidx/compose/ui/draw/DrawModifier;)V", "buildCacheParams", "Landroidx/compose/ui/draw/BuildDrawCacheParams;", "cacheDrawModifier", "Landroidx/compose/ui/draw/DrawCacheModifier;", "invalidateCache", "", "isValid", "()Z", "value", "modifier", "getModifier", "()Landroidx/compose/ui/draw/DrawModifier;", "setModifier", "(Landroidx/compose/ui/draw/DrawModifier;)V", "updateCache", "Lkotlin/Function0;", "", "onMeasureResultChanged", "width", "", "height", "performDraw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "updateCacheDrawModifier", "Companion", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ModifiedDrawNode extends DelegatingLayoutNodeWrapper<DrawModifier> implements OwnerScope {
    private static final Function1<ModifiedDrawNode, Unit> onCommitAffectingModifiedDrawNode = new Function1<ModifiedDrawNode, Unit>() { // from class: androidx.compose.ui.node.ModifiedDrawNode$Companion$onCommitAffectingModifiedDrawNode$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ModifiedDrawNode modifiedDrawNode) {
            invoke2(modifiedDrawNode);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(ModifiedDrawNode modifiedDrawNode) {
            Intrinsics.checkNotNullParameter(modifiedDrawNode, "modifiedDrawNode");
            if (modifiedDrawNode.isValid()) {
                modifiedDrawNode.invalidateCache = true;
                modifiedDrawNode.invalidateLayer();
            }
        }
    };
    private final BuildDrawCacheParams buildCacheParams;
    private DrawCacheModifier cacheDrawModifier;
    private boolean invalidateCache;
    private final Function0<Unit> updateCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModifiedDrawNode(LayoutNodeWrapper wrapped, DrawModifier drawModifier) {
        super(wrapped, drawModifier);
        Intrinsics.checkNotNullParameter(wrapped, "wrapped");
        Intrinsics.checkNotNullParameter(drawModifier, "drawModifier");
        this.cacheDrawModifier = updateCacheDrawModifier();
        this.buildCacheParams = new BuildDrawCacheParams() { // from class: androidx.compose.ui.node.ModifiedDrawNode$buildCacheParams$1
            private final Density density;

            {
                this.density = ModifiedDrawNode.this.getLayoutNode().getDensity();
            }

            @Override // androidx.compose.ui.draw.BuildDrawCacheParams
            public Density getDensity() {
                return this.density;
            }

            @Override // androidx.compose.ui.draw.BuildDrawCacheParams
            public LayoutDirection getLayoutDirection() {
                return ModifiedDrawNode.this.getLayoutNode().getLayoutDirection();
            }

            @Override // androidx.compose.ui.draw.BuildDrawCacheParams
            /* renamed from: getSize-NH-jbRc */
            public long mo365getSizeNHjbRc() {
                long j;
                j = ModifiedDrawNode.this.getMeasuredSize();
                return IntSizeKt.m2560toSizeozmzZPI(j);
            }
        };
        this.invalidateCache = true;
        this.updateCache = new Function0<Unit>() { // from class: androidx.compose.ui.node.ModifiedDrawNode$updateCache$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                DrawCacheModifier drawCacheModifier;
                BuildDrawCacheParams buildDrawCacheParams;
                drawCacheModifier = ModifiedDrawNode.this.cacheDrawModifier;
                if (drawCacheModifier != null) {
                    buildDrawCacheParams = ModifiedDrawNode.this.buildCacheParams;
                    drawCacheModifier.onBuildCache(buildDrawCacheParams);
                }
                ModifiedDrawNode.this.invalidateCache = false;
            }
        };
    }

    private final DrawCacheModifier updateCacheDrawModifier() {
        DrawModifier modifier = getModifier();
        if (modifier instanceof DrawCacheModifier) {
            return (DrawCacheModifier) modifier;
        }
        return null;
    }

    @Override // androidx.compose.ui.node.DelegatingLayoutNodeWrapper
    public DrawModifier getModifier() {
        return (DrawModifier) super.getModifier();
    }

    @Override // androidx.compose.ui.node.DelegatingLayoutNodeWrapper
    public void setModifier(DrawModifier value) {
        Intrinsics.checkNotNullParameter(value, "value");
        super.setModifier((ModifiedDrawNode) value);
        this.cacheDrawModifier = updateCacheDrawModifier();
        this.invalidateCache = true;
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    protected void onMeasureResultChanged(int width, int height) {
        super.onMeasureResultChanged(width, height);
        this.invalidateCache = true;
    }

    @Override // androidx.compose.ui.node.DelegatingLayoutNodeWrapper, androidx.compose.ui.node.LayoutNodeWrapper
    protected void performDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        long m2560toSizeozmzZPI = IntSizeKt.m2560toSizeozmzZPI(getMeasuredSize());
        if (this.cacheDrawModifier != null && this.invalidateCache) {
            LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver().observeReads$ui_release(this, onCommitAffectingModifiedDrawNode, this.updateCache);
        }
        LayoutNodeDrawScope mDrawScope = getLayoutNode().getMDrawScope();
        LayoutNodeWrapper wrapped$ui_release = getWrapped();
        LayoutNodeWrapper layoutNodeWrapper = mDrawScope.wrapped;
        mDrawScope.wrapped = wrapped$ui_release;
        CanvasDrawScope canvasDrawScope = mDrawScope.canvasDrawScope;
        MeasureScope measureScope = wrapped$ui_release.getMeasureScope();
        LayoutDirection layoutDirection = wrapped$ui_release.getMeasureScope().getLayoutDirection();
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope.getDrawParams();
        Density density = drawParams.getDensity();
        LayoutDirection layoutDirection2 = drawParams.getLayoutDirection();
        Canvas canvas2 = drawParams.getCanvas();
        long size = drawParams.getSize();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope.getDrawParams();
        drawParams2.setDensity(measureScope);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(canvas);
        drawParams2.m1050setSizeuvyYCjk(m2560toSizeozmzZPI);
        canvas.save();
        getModifier().draw(mDrawScope);
        canvas.restore();
        CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope.getDrawParams();
        drawParams3.setDensity(density);
        drawParams3.setLayoutDirection(layoutDirection2);
        drawParams3.setCanvas(canvas2);
        drawParams3.m1050setSizeuvyYCjk(size);
        mDrawScope.wrapped = layoutNodeWrapper;
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper, androidx.compose.ui.node.OwnerScope
    public boolean isValid() {
        return isAttached();
    }
}
