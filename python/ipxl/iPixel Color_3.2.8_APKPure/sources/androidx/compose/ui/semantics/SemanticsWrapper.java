package androidx.compose.ui.semantics;

import androidx.compose.ui.node.DelegatingLayoutNodeWrapper;
import androidx.compose.ui.node.LayoutNodeWrapper;
import androidx.compose.ui.node.Owner;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SemanticsWrapper.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\nH\u0016J+\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00000\u000fH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0012\u001a\u00020\nH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsWrapper;", "Landroidx/compose/ui/node/DelegatingLayoutNodeWrapper;", "Landroidx/compose/ui/semantics/SemanticsModifier;", "wrapped", "Landroidx/compose/ui/node/LayoutNodeWrapper;", "semanticsModifier", "(Landroidx/compose/ui/node/LayoutNodeWrapper;Landroidx/compose/ui/semantics/SemanticsModifier;)V", "collapsedSemanticsConfiguration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "detach", "", "hitTestSemantics", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitSemanticsWrappers", "", "hitTestSemantics-3MmeM6k", "(JLjava/util/List;)V", "onModifierChanged", "toString", "", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SemanticsWrapper extends DelegatingLayoutNodeWrapper<SemanticsModifier> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SemanticsWrapper(LayoutNodeWrapper wrapped, SemanticsModifier semanticsModifier) {
        super(wrapped, semanticsModifier);
        Intrinsics.checkNotNullParameter(wrapped, "wrapped");
        Intrinsics.checkNotNullParameter(semanticsModifier, "semanticsModifier");
    }

    public final SemanticsConfiguration collapsedSemanticsConfiguration() {
        SemanticsWrapper semanticsWrapper;
        LayoutNodeWrapper wrapped$ui_release = getWrapped();
        while (true) {
            if (wrapped$ui_release == null) {
                semanticsWrapper = null;
                break;
            }
            if (wrapped$ui_release instanceof SemanticsWrapper) {
                semanticsWrapper = (SemanticsWrapper) wrapped$ui_release;
                break;
            }
            wrapped$ui_release = wrapped$ui_release.getWrapped();
        }
        if (semanticsWrapper == null || getModifier().getSemanticsConfiguration().getIsClearingSemantics()) {
            return getModifier().getSemanticsConfiguration();
        }
        SemanticsConfiguration copy = getModifier().getSemanticsConfiguration().copy();
        copy.collapsePeer$ui_release(semanticsWrapper.collapsedSemanticsConfiguration());
        return copy;
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public void detach() {
        super.detach();
        Owner owner = getLayoutNode().getOwner();
        if (owner == null) {
            return;
        }
        owner.onSemanticsChange();
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public void onModifierChanged() {
        super.onModifierChanged();
        Owner owner = getLayoutNode().getOwner();
        if (owner == null) {
            return;
        }
        owner.onSemanticsChange();
    }

    public String toString() {
        return super.toString() + " id: " + getModifier().getId() + " config: " + getModifier().getSemanticsConfiguration();
    }

    @Override // androidx.compose.ui.node.DelegatingLayoutNodeWrapper, androidx.compose.ui.node.LayoutNodeWrapper
    /* renamed from: hitTestSemantics-3MmeM6k */
    public void mo2019hitTestSemantics3MmeM6k(long pointerPosition, List<SemanticsWrapper> hitSemanticsWrappers) {
        Intrinsics.checkNotNullParameter(hitSemanticsWrappers, "hitSemanticsWrappers");
        if (m2037isPointerInBoundsk4lQ0M(pointerPosition) && m2040withinLayerBoundsk4lQ0M(pointerPosition)) {
            hitSemanticsWrappers.add(this);
            getWrapped().mo2019hitTestSemantics3MmeM6k(getWrapped().m2035fromParentPositionMKHz9U(pointerPosition), hitSemanticsWrappers);
        }
    }
}
