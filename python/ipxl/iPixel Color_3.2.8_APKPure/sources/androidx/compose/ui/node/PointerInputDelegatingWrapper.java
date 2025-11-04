package androidx.compose.ui.node;

import androidx.compose.ui.input.pointer.PointerInputFilter;
import androidx.compose.ui.input.pointer.PointerInputModifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PointerInputDelegatingWrapper.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J+\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015R$\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/node/PointerInputDelegatingWrapper;", "Landroidx/compose/ui/node/DelegatingLayoutNodeWrapper;", "Landroidx/compose/ui/input/pointer/PointerInputModifier;", "wrapped", "Landroidx/compose/ui/node/LayoutNodeWrapper;", "pointerInputModifier", "(Landroidx/compose/ui/node/LayoutNodeWrapper;Landroidx/compose/ui/input/pointer/PointerInputModifier;)V", "value", "modifier", "getModifier", "()Landroidx/compose/ui/input/pointer/PointerInputModifier;", "setModifier", "(Landroidx/compose/ui/input/pointer/PointerInputModifier;)V", "hitTest", "", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitPointerInputFilters", "", "Landroidx/compose/ui/input/pointer/PointerInputFilter;", "hitTest-3MmeM6k", "(JLjava/util/List;)V", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PointerInputDelegatingWrapper extends DelegatingLayoutNodeWrapper<PointerInputModifier> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PointerInputDelegatingWrapper(LayoutNodeWrapper wrapped, PointerInputModifier pointerInputModifier) {
        super(wrapped, pointerInputModifier);
        Intrinsics.checkNotNullParameter(wrapped, "wrapped");
        Intrinsics.checkNotNullParameter(pointerInputModifier, "pointerInputModifier");
        pointerInputModifier.getPointerInputFilter().setLayoutCoordinates$ui_release(this);
    }

    @Override // androidx.compose.ui.node.DelegatingLayoutNodeWrapper
    public PointerInputModifier getModifier() {
        return (PointerInputModifier) super.getModifier();
    }

    @Override // androidx.compose.ui.node.DelegatingLayoutNodeWrapper
    public void setModifier(PointerInputModifier value) {
        Intrinsics.checkNotNullParameter(value, "value");
        super.setModifier((PointerInputDelegatingWrapper) value);
        value.getPointerInputFilter().setLayoutCoordinates$ui_release(this);
    }

    @Override // androidx.compose.ui.node.DelegatingLayoutNodeWrapper, androidx.compose.ui.node.LayoutNodeWrapper
    /* renamed from: hitTest-3MmeM6k */
    public void mo2018hitTest3MmeM6k(long pointerPosition, List<PointerInputFilter> hitPointerInputFilters) {
        Intrinsics.checkNotNullParameter(hitPointerInputFilters, "hitPointerInputFilters");
        if (m2037isPointerInBoundsk4lQ0M(pointerPosition) && m2040withinLayerBoundsk4lQ0M(pointerPosition)) {
            hitPointerInputFilters.add(getModifier().getPointerInputFilter());
            getWrapped().mo2018hitTest3MmeM6k(getWrapped().m2035fromParentPositionMKHz9U(pointerPosition), hitPointerInputFilters);
        }
    }
}
