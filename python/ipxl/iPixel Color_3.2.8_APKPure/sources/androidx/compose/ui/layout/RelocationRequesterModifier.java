package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollDelegatingWrapper;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.node.LayoutNodeWrapper;
import androidx.compose.ui.node.Owner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RelocationRequesterModifier.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/layout/RelocationRequesterModifier;", "Landroidx/compose/ui/layout/OnGloballyPositionedModifier;", "()V", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "bringIntoView", "", "onGloballyPositioned", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class RelocationRequesterModifier implements OnGloballyPositionedModifier {
    public LayoutCoordinates coordinates;

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public boolean all(Function1<? super Modifier.Element, Boolean> function1) {
        return OnGloballyPositionedModifier.DefaultImpls.all(this, function1);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public boolean any(Function1<? super Modifier.Element, Boolean> function1) {
        return OnGloballyPositionedModifier.DefaultImpls.any(this, function1);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public <R> R foldIn(R r, Function2<? super R, ? super Modifier.Element, ? extends R> function2) {
        return (R) OnGloballyPositionedModifier.DefaultImpls.foldIn(this, r, function2);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public <R> R foldOut(R r, Function2<? super Modifier.Element, ? super R, ? extends R> function2) {
        return (R) OnGloballyPositionedModifier.DefaultImpls.foldOut(this, r, function2);
    }

    @Override // androidx.compose.ui.Modifier
    public Modifier then(Modifier modifier) {
        return OnGloballyPositionedModifier.DefaultImpls.then(this, modifier);
    }

    public final LayoutCoordinates getCoordinates() {
        LayoutCoordinates layoutCoordinates = this.coordinates;
        if (layoutCoordinates != null) {
            return layoutCoordinates;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coordinates");
        throw null;
    }

    public final void setCoordinates(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<set-?>");
        this.coordinates = layoutCoordinates;
    }

    public final void bringIntoView() {
        LayoutCoordinates coordinates = getCoordinates();
        if (!(coordinates instanceof LayoutNodeWrapper)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        LayoutNodeWrapper layoutNodeWrapper = (LayoutNodeWrapper) coordinates;
        NestedScrollDelegatingWrapper findPreviousNestedScrollWrapper = layoutNodeWrapper.findPreviousNestedScrollWrapper();
        if (findPreviousNestedScrollWrapper != null) {
            RelocationRequesterModifierKt.bringIntoView(findPreviousNestedScrollWrapper, getCoordinates());
        }
        Owner owner = layoutNodeWrapper.getLayoutNode().getOwner();
        if (owner == null) {
            return;
        }
        owner.requestRectangleOnScreen(LayoutCoordinatesKt.boundsInRoot(getCoordinates()));
    }

    @Override // androidx.compose.ui.layout.OnGloballyPositionedModifier
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        Intrinsics.checkNotNullParameter(coordinates, "coordinates");
        setCoordinates(coordinates);
    }
}
