package androidx.compose.ui.node;

import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusModifier;
import androidx.compose.ui.focus.FocusNodeUtilsKt;
import androidx.compose.ui.focus.FocusOrder;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.focus.FocusStateImpl;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModifiedFocusNode.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0000H\u0016J\b\u0010\u0017\u001a\u00020\u0000H\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00000\u001bJ\b\u0010\u001c\u001a\u00020\u0014H\u0016J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010\t\u001a\u00020!H\u0016J\u000e\u0010\"\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020!R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u0004\u0018\u00010\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u00008F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006#"}, d2 = {"Landroidx/compose/ui/node/ModifiedFocusNode;", "Landroidx/compose/ui/node/DelegatingLayoutNodeWrapper;", "Landroidx/compose/ui/focus/FocusModifier;", "wrapped", "Landroidx/compose/ui/node/LayoutNodeWrapper;", "modifier", "(Landroidx/compose/ui/node/LayoutNodeWrapper;Landroidx/compose/ui/focus/FocusModifier;)V", "value", "Landroidx/compose/ui/focus/FocusStateImpl;", "focusState", "getFocusState", "()Landroidx/compose/ui/focus/FocusStateImpl;", "setFocusState", "(Landroidx/compose/ui/focus/FocusStateImpl;)V", "focusedChild", "getFocusedChild", "()Landroidx/compose/ui/node/ModifiedFocusNode;", "setFocusedChild", "(Landroidx/compose/ui/node/ModifiedFocusNode;)V", "attach", "", "detach", "findNextFocusWrapper", "findPreviousFocusWrapper", "focusRect", "Landroidx/compose/ui/geometry/Rect;", "focusableChildren", "", "onModifierChanged", "populateFocusOrder", "focusOrder", "Landroidx/compose/ui/focus/FocusOrder;", "propagateFocusEvent", "Landroidx/compose/ui/focus/FocusState;", "sendOnFocusEvent", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ModifiedFocusNode extends DelegatingLayoutNodeWrapper<FocusModifier> {

    /* compiled from: ModifiedFocusNode.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            iArr[FocusStateImpl.Active.ordinal()] = 1;
            iArr[FocusStateImpl.Captured.ordinal()] = 2;
            iArr[FocusStateImpl.ActiveParent.ordinal()] = 3;
            iArr[FocusStateImpl.Disabled.ordinal()] = 4;
            iArr[FocusStateImpl.Inactive.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // androidx.compose.ui.node.DelegatingLayoutNodeWrapper, androidx.compose.ui.node.LayoutNodeWrapper
    public ModifiedFocusNode findNextFocusWrapper() {
        return this;
    }

    @Override // androidx.compose.ui.node.DelegatingLayoutNodeWrapper, androidx.compose.ui.node.LayoutNodeWrapper
    public ModifiedFocusNode findPreviousFocusWrapper() {
        return this;
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public void populateFocusOrder(FocusOrder focusOrder) {
        Intrinsics.checkNotNullParameter(focusOrder, "focusOrder");
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public void propagateFocusEvent(FocusState focusState) {
        Intrinsics.checkNotNullParameter(focusState, "focusState");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModifiedFocusNode(LayoutNodeWrapper wrapped, FocusModifier modifier) {
        super(wrapped, modifier);
        Intrinsics.checkNotNullParameter(wrapped, "wrapped");
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        modifier.setFocusNode(this);
    }

    public final FocusStateImpl getFocusState() {
        return getModifier().getFocusState();
    }

    public final void setFocusState(FocusStateImpl value) {
        Intrinsics.checkNotNullParameter(value, "value");
        getModifier().setFocusState(value);
        sendOnFocusEvent(value);
    }

    public final ModifiedFocusNode getFocusedChild() {
        return getModifier().getFocusedChild();
    }

    public final void setFocusedChild(ModifiedFocusNode modifiedFocusNode) {
        getModifier().setFocusedChild(modifiedFocusNode);
    }

    public final Rect focusRect() {
        return LayoutCoordinatesKt.boundsInRoot(this);
    }

    public final List<ModifiedFocusNode> focusableChildren() {
        ModifiedFocusNode findNextFocusWrapper = getWrapped().findNextFocusWrapper();
        if (findNextFocusWrapper != null) {
            return CollectionsKt.listOf(findNextFocusWrapper);
        }
        ArrayList arrayList = new ArrayList();
        List<LayoutNode> children$ui_release = getLayoutNode().getChildren$ui_release();
        int size = children$ui_release.size() - 1;
        if (size >= 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                FocusNodeUtilsKt.findFocusableChildren(children$ui_release.get(i), arrayList);
                if (i2 > size) {
                    break;
                }
                i = i2;
            }
        }
        return arrayList;
    }

    public final void sendOnFocusEvent(FocusState focusState) {
        Intrinsics.checkNotNullParameter(focusState, "focusState");
        LayoutNodeWrapper wrappedBy$ui_release = getWrappedBy();
        if (wrappedBy$ui_release == null) {
            return;
        }
        wrappedBy$ui_release.propagateFocusEvent(focusState);
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public void onModifierChanged() {
        super.onModifierChanged();
        sendOnFocusEvent(getFocusState());
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public void attach() {
        super.attach();
        sendOnFocusEvent(getFocusState());
    }

    @Override // androidx.compose.ui.node.LayoutNodeWrapper
    public void detach() {
        FocusManager focusManager;
        int i = WhenMappings.$EnumSwitchMapping$0[getFocusState().ordinal()];
        if (i == 1 || i == 2) {
            Owner owner = getLayoutNode().getOwner();
            if (owner != null && (focusManager = owner.getFocusManager()) != null) {
                focusManager.clearFocus(true);
            }
        } else if (i == 3) {
            ModifiedFocusNode findNextFocusWrapper = getWrapped().findNextFocusWrapper();
            if (findNextFocusWrapper == null) {
                findNextFocusWrapper = FocusNodeUtilsKt.searchChildrenForFocusNode$default(getLayoutNode(), null, 1, null);
            }
            if (findNextFocusWrapper != null) {
                ModifiedFocusNode findParentFocusNode$ui_release = findParentFocusNode$ui_release();
                if (findParentFocusNode$ui_release != null) {
                    findParentFocusNode$ui_release.getModifier().setFocusedChild(findNextFocusWrapper);
                }
                sendOnFocusEvent(findNextFocusWrapper.getFocusState());
            } else {
                sendOnFocusEvent(FocusStateImpl.Inactive);
            }
        }
        super.detach();
    }
}
