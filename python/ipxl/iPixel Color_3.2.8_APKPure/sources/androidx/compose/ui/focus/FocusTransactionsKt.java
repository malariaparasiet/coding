package androidx.compose.ui.focus;

import androidx.compose.ui.node.ModifiedFocusNode;
import androidx.compose.ui.node.Owner;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FocusTransactions.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0016\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\t\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0001H\u0000\u001a\u001c\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0002\u001a\f\u0010\f\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\r"}, d2 = {"captureFocus", "", "Landroidx/compose/ui/node/ModifiedFocusNode;", "clearFocus", "forcedClear", "freeFocus", "grantFocus", "", "propagateFocus", "requestFocus", "requestFocusForChild", "childNode", "requestFocusForOwner", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class FocusTransactionsKt {

    /* compiled from: FocusTransactions.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            iArr[FocusStateImpl.Active.ordinal()] = 1;
            iArr[FocusStateImpl.Captured.ordinal()] = 2;
            iArr[FocusStateImpl.Disabled.ordinal()] = 3;
            iArr[FocusStateImpl.ActiveParent.ordinal()] = 4;
            iArr[FocusStateImpl.Inactive.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void requestFocus$default(ModifiedFocusNode modifiedFocusNode, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        requestFocus(modifiedFocusNode, z);
    }

    public static final void requestFocus(ModifiedFocusNode modifiedFocusNode, boolean z) {
        Intrinsics.checkNotNullParameter(modifiedFocusNode, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[modifiedFocusNode.getFocusState().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            modifiedFocusNode.sendOnFocusEvent(modifiedFocusNode.getFocusState());
            return;
        }
        if (i != 4) {
            if (i != 5) {
                return;
            }
            ModifiedFocusNode findParentFocusNode$ui_release = modifiedFocusNode.findParentFocusNode$ui_release();
            if (findParentFocusNode$ui_release == null) {
                if (requestFocusForOwner(modifiedFocusNode)) {
                    grantFocus(modifiedFocusNode, z);
                    return;
                }
                return;
            }
            requestFocusForChild(findParentFocusNode$ui_release, modifiedFocusNode, z);
            return;
        }
        ModifiedFocusNode focusedChild = modifiedFocusNode.getFocusedChild();
        if (focusedChild == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        if (z) {
            modifiedFocusNode.sendOnFocusEvent(modifiedFocusNode.getFocusState());
        } else if (clearFocus$default(focusedChild, false, 1, null)) {
            grantFocus(modifiedFocusNode, z);
            modifiedFocusNode.setFocusedChild(null);
        }
    }

    public static final boolean captureFocus(ModifiedFocusNode modifiedFocusNode) {
        Intrinsics.checkNotNullParameter(modifiedFocusNode, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[modifiedFocusNode.getFocusState().ordinal()];
        if (i != 1) {
            return i == 2;
        }
        modifiedFocusNode.setFocusState(FocusStateImpl.Captured);
        return true;
    }

    public static final boolean freeFocus(ModifiedFocusNode modifiedFocusNode) {
        Intrinsics.checkNotNullParameter(modifiedFocusNode, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[modifiedFocusNode.getFocusState().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return false;
            }
            modifiedFocusNode.setFocusState(FocusStateImpl.Active);
        }
        return true;
    }

    public static /* synthetic */ boolean clearFocus$default(ModifiedFocusNode modifiedFocusNode, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return clearFocus(modifiedFocusNode, z);
    }

    public static final boolean clearFocus(ModifiedFocusNode modifiedFocusNode, boolean z) {
        Intrinsics.checkNotNullParameter(modifiedFocusNode, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[modifiedFocusNode.getFocusState().ordinal()];
        if (i == 1) {
            modifiedFocusNode.setFocusState(FocusStateImpl.Inactive);
            return true;
        }
        if (i == 2) {
            if (z) {
                modifiedFocusNode.setFocusState(FocusStateImpl.Inactive);
            }
            return z;
        }
        if (i != 3) {
            if (i == 4) {
                ModifiedFocusNode focusedChild = modifiedFocusNode.getFocusedChild();
                if (focusedChild == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                boolean clearFocus = clearFocus(focusedChild, z);
                if (clearFocus) {
                    modifiedFocusNode.setFocusState(FocusStateImpl.Inactive);
                    modifiedFocusNode.setFocusedChild(null);
                }
                return clearFocus;
            }
            if (i != 5) {
                throw new NoWhenBranchMatchedException();
            }
        }
        return true;
    }

    private static final void grantFocus(ModifiedFocusNode modifiedFocusNode, boolean z) {
        ModifiedFocusNode modifiedFocusNode2 = (ModifiedFocusNode) CollectionsKt.firstOrNull((List) modifiedFocusNode.focusableChildren());
        if (modifiedFocusNode2 == null || !z) {
            modifiedFocusNode.setFocusState(FocusStateImpl.Active);
            return;
        }
        modifiedFocusNode.setFocusState(FocusStateImpl.ActiveParent);
        modifiedFocusNode.setFocusedChild(modifiedFocusNode2);
        grantFocus(modifiedFocusNode2, z);
    }

    private static final boolean requestFocusForChild(ModifiedFocusNode modifiedFocusNode, ModifiedFocusNode modifiedFocusNode2, boolean z) {
        if (!modifiedFocusNode.focusableChildren().contains(modifiedFocusNode2)) {
            throw new IllegalStateException("Non child node cannot request focus.".toString());
        }
        int i = WhenMappings.$EnumSwitchMapping$0[modifiedFocusNode.getFocusState().ordinal()];
        if (i == 1) {
            modifiedFocusNode.setFocusState(FocusStateImpl.ActiveParent);
            modifiedFocusNode.setFocusedChild(modifiedFocusNode2);
            grantFocus(modifiedFocusNode2, z);
            return true;
        }
        if (i == 2) {
            return false;
        }
        if (i == 3) {
            throw new IllegalStateException("non root FocusNode needs a focusable parent".toString());
        }
        if (i == 4) {
            ModifiedFocusNode focusedChild = modifiedFocusNode.getFocusedChild();
            if (focusedChild == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            if (!clearFocus$default(focusedChild, false, 1, null)) {
                return false;
            }
            modifiedFocusNode.setFocusedChild(modifiedFocusNode2);
            grantFocus(modifiedFocusNode2, z);
            return true;
        }
        if (i == 5) {
            ModifiedFocusNode findParentFocusNode$ui_release = modifiedFocusNode.findParentFocusNode$ui_release();
            if (findParentFocusNode$ui_release == null) {
                if (!requestFocusForOwner(modifiedFocusNode)) {
                    return false;
                }
                modifiedFocusNode.setFocusState(FocusStateImpl.Active);
                return requestFocusForChild(modifiedFocusNode, modifiedFocusNode2, z);
            }
            if (requestFocusForChild(findParentFocusNode$ui_release, modifiedFocusNode, false)) {
                return requestFocusForChild(modifiedFocusNode, modifiedFocusNode2, z);
            }
            return false;
        }
        throw new NoWhenBranchMatchedException();
    }

    private static final boolean requestFocusForOwner(ModifiedFocusNode modifiedFocusNode) {
        Owner owner = modifiedFocusNode.getLayoutNode().getOwner();
        if (owner == null) {
            throw new IllegalArgumentException("Owner not initialized.".toString());
        }
        return owner.requestFocus();
    }
}
