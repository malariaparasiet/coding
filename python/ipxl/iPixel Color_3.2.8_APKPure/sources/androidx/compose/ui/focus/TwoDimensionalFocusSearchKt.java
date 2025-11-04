package androidx.compose.ui.focus;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.ModifiedFocusNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TwoDimensionalFocusSearch.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\b\u001a5\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a5\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\f\u001a\f\u0010\u0012\u001a\u00020\u0006*\u00020\u0006H\u0002\u001a1\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\b\u0012\u0004\u0012\u00020\u00140\u00152\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018\u001a\f\u0010\u0019\u001a\u00020\u0006*\u00020\u0006H\u0002\u001a#\u0010\u001a\u001a\u0004\u0018\u00010\u0014*\u00020\u00142\u0006\u0010\t\u001a\u00020\nH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"invalidFocusDirection", "", "noActiveChild", "beamBeats", "", "source", "Landroidx/compose/ui/geometry/Rect;", "rect1", "rect2", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "beamBeats-I7lrPNg", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;I)Z", "isBetterCandidate", "proposedCandidate", "currentCandidate", "focusedRect", "isBetterCandidate-I7lrPNg", "bottomRight", "findBestCandidate", "Landroidx/compose/ui/node/ModifiedFocusNode;", "", "focusRect", "findBestCandidate-4WY_MpI", "(Ljava/util/List;Landroidx/compose/ui/geometry/Rect;I)Landroidx/compose/ui/node/ModifiedFocusNode;", "topLeft", "twoDimensionalFocusSearch", "twoDimensionalFocusSearch-Mxy_nc0", "(Landroidx/compose/ui/node/ModifiedFocusNode;I)Landroidx/compose/ui/node/ModifiedFocusNode;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TwoDimensionalFocusSearchKt {
    private static final String invalidFocusDirection = "This function should only be used for 2-D focus search";
    private static final String noActiveChild = "ActiveParent must have a focusedChild";

    /* compiled from: TwoDimensionalFocusSearch.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            iArr[FocusStateImpl.Inactive.ordinal()] = 1;
            iArr[FocusStateImpl.Disabled.ordinal()] = 2;
            iArr[FocusStateImpl.ActiveParent.ordinal()] = 3;
            iArr[FocusStateImpl.Active.ordinal()] = 4;
            iArr[FocusStateImpl.Captured.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* renamed from: twoDimensionalFocusSearch-Mxy_nc0, reason: not valid java name */
    public static final ModifiedFocusNode m407twoDimensionalFocusSearchMxy_nc0(ModifiedFocusNode twoDimensionalFocusSearch, int i) {
        ModifiedFocusNode m407twoDimensionalFocusSearchMxy_nc0;
        Rect bottomRight;
        Intrinsics.checkNotNullParameter(twoDimensionalFocusSearch, "$this$twoDimensionalFocusSearch");
        int i2 = WhenMappings.$EnumSwitchMapping$0[twoDimensionalFocusSearch.getFocusState().ordinal()];
        if (i2 == 1) {
            return twoDimensionalFocusSearch;
        }
        if (i2 == 2) {
            return null;
        }
        if (i2 == 3) {
            ModifiedFocusNode focusedChild = twoDimensionalFocusSearch.getFocusedChild();
            if (focusedChild == null) {
                throw new IllegalStateException(noActiveChild.toString());
            }
            if (focusedChild.getFocusState() == FocusStateImpl.ActiveParent && (m407twoDimensionalFocusSearchMxy_nc0 = m407twoDimensionalFocusSearchMxy_nc0(focusedChild, i)) != null) {
                return m407twoDimensionalFocusSearchMxy_nc0;
            }
            ModifiedFocusNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(twoDimensionalFocusSearch);
            Rect focusRect = findActiveFocusNode != null ? findActiveFocusNode.focusRect() : null;
            if (focusRect == null) {
                throw new IllegalStateException(noActiveChild.toString());
            }
            return m405findBestCandidate4WY_MpI(twoDimensionalFocusSearch.focusableChildren(), focusRect, i);
        }
        if (i2 == 4 || i2 == 5) {
            List<ModifiedFocusNode> focusableChildren = twoDimensionalFocusSearch.focusableChildren();
            if (focusableChildren.size() <= 1) {
                return (ModifiedFocusNode) CollectionsKt.firstOrNull((List) focusableChildren);
            }
            if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s()) ? true : FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m392getDowndhqQ8s())) {
                bottomRight = topLeft(twoDimensionalFocusSearch.focusRect());
            } else {
                if (!(FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s()) ? true : FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m399getUpdhqQ8s()))) {
                    throw new IllegalStateException(invalidFocusDirection.toString());
                }
                bottomRight = bottomRight(twoDimensionalFocusSearch.focusRect());
            }
            return m405findBestCandidate4WY_MpI(focusableChildren, bottomRight, i);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* renamed from: findBestCandidate-4WY_MpI, reason: not valid java name */
    private static final ModifiedFocusNode m405findBestCandidate4WY_MpI(List<ModifiedFocusNode> list, Rect rect, int i) {
        Rect translate;
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s())) {
            translate = rect.translate(rect.getWidth() + 1, 0.0f);
        } else if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s())) {
            translate = rect.translate(-(rect.getWidth() + 1), 0.0f);
        } else if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m399getUpdhqQ8s())) {
            translate = rect.translate(0.0f, rect.getHeight() + 1);
        } else {
            if (!FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m392getDowndhqQ8s())) {
                throw new IllegalStateException(invalidFocusDirection.toString());
            }
            translate = rect.translate(0.0f, -(rect.getHeight() + 1));
        }
        int size = list.size() - 1;
        ModifiedFocusNode modifiedFocusNode = null;
        if (size < 0) {
            return null;
        }
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            ModifiedFocusNode modifiedFocusNode2 = list.get(i2);
            Rect focusRect = modifiedFocusNode2.focusRect();
            if (m406isBetterCandidateI7lrPNg(focusRect, translate, rect, i)) {
                modifiedFocusNode = modifiedFocusNode2;
                translate = focusRect;
            }
            if (i3 > size) {
                return modifiedFocusNode;
            }
            i2 = i3;
        }
    }

    private static final boolean isBetterCandidate_I7lrPNg$isCandidate(Rect rect, int i, Rect rect2) {
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s())) {
            return (rect2.getRight() > rect.getRight() || rect2.getLeft() >= rect.getRight()) && rect2.getLeft() > rect.getLeft();
        }
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s())) {
            return (rect2.getLeft() < rect.getLeft() || rect2.getRight() <= rect.getLeft()) && rect2.getRight() < rect.getRight();
        }
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m399getUpdhqQ8s())) {
            return (rect2.getBottom() > rect.getBottom() || rect2.getTop() >= rect.getBottom()) && rect2.getTop() > rect.getTop();
        }
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m392getDowndhqQ8s())) {
            return (rect2.getTop() < rect.getTop() || rect2.getBottom() <= rect.getTop()) && rect2.getBottom() < rect.getBottom();
        }
        throw new IllegalStateException(invalidFocusDirection.toString());
    }

    private static final float isBetterCandidate_I7lrPNg$majorAxisDistance(Rect rect, int i, Rect rect2) {
        float top;
        float bottom;
        float top2;
        float bottom2;
        float f;
        if (!FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s())) {
            if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s())) {
                top = rect.getLeft();
                bottom = rect2.getRight();
            } else if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m399getUpdhqQ8s())) {
                top2 = rect2.getTop();
                bottom2 = rect.getBottom();
            } else {
                if (!FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m392getDowndhqQ8s())) {
                    throw new IllegalStateException(invalidFocusDirection.toString());
                }
                top = rect.getTop();
                bottom = rect2.getBottom();
            }
            f = top - bottom;
            return Math.max(0.0f, f);
        }
        top2 = rect2.getLeft();
        bottom2 = rect.getRight();
        f = top2 - bottom2;
        return Math.max(0.0f, f);
    }

    private static final float isBetterCandidate_I7lrPNg$minorAxisDistance(Rect rect, int i, Rect rect2) {
        float f;
        float left;
        float left2;
        float width;
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s()) ? true : FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s())) {
            f = 2;
            left = rect2.getTop() + (rect2.getHeight() / f);
            left2 = rect.getTop();
            width = rect.getHeight();
        } else {
            if (!(FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m399getUpdhqQ8s()) ? true : FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m392getDowndhqQ8s()))) {
                throw new IllegalStateException(invalidFocusDirection.toString());
            }
            f = 2;
            left = rect2.getLeft() + (rect2.getWidth() / f);
            left2 = rect.getLeft();
            width = rect.getWidth();
        }
        return left - (left2 + (width / f));
    }

    private static final long isBetterCandidate_I7lrPNg$weightedDistance(int i, Rect rect, Rect rect2) {
        long abs = (long) Math.abs(isBetterCandidate_I7lrPNg$majorAxisDistance(rect2, i, rect));
        long abs2 = (long) Math.abs(isBetterCandidate_I7lrPNg$minorAxisDistance(rect2, i, rect));
        return (13 * abs * abs) + (abs2 * abs2);
    }

    /* renamed from: isBetterCandidate-I7lrPNg, reason: not valid java name */
    private static final boolean m406isBetterCandidateI7lrPNg(Rect rect, Rect rect2, Rect rect3, int i) {
        if (!isBetterCandidate_I7lrPNg$isCandidate(rect, i, rect3)) {
            return false;
        }
        if (isBetterCandidate_I7lrPNg$isCandidate(rect2, i, rect3) && !m403beamBeatsI7lrPNg(rect3, rect, rect2, i)) {
            return !m403beamBeatsI7lrPNg(rect3, rect2, rect, i) && isBetterCandidate_I7lrPNg$weightedDistance(i, rect3, rect) < isBetterCandidate_I7lrPNg$weightedDistance(i, rect3, rect2);
        }
        return true;
    }

    private static final boolean beamBeats_I7lrPNg$inSourceBeam(Rect rect, int i, Rect rect2) {
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s()) ? true : FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s())) {
            return rect.getBottom() > rect2.getTop() && rect.getTop() < rect2.getBottom();
        }
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m399getUpdhqQ8s()) ? true : FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m392getDowndhqQ8s())) {
            return rect.getRight() > rect2.getLeft() && rect.getLeft() < rect2.getRight();
        }
        throw new IllegalStateException(invalidFocusDirection.toString());
    }

    private static final boolean beamBeats_I7lrPNg$isInDirectionOfSearch(Rect rect, int i, Rect rect2) {
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s())) {
            return rect2.getLeft() >= rect.getRight();
        }
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s())) {
            return rect2.getRight() <= rect.getLeft();
        }
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m399getUpdhqQ8s())) {
            return rect2.getTop() >= rect.getBottom();
        }
        if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m392getDowndhqQ8s())) {
            return rect2.getBottom() <= rect.getTop();
        }
        throw new IllegalStateException(invalidFocusDirection.toString());
    }

    /* renamed from: beamBeats_I7lrPNg$majorAxisDistance-2, reason: not valid java name */
    private static final float m404beamBeats_I7lrPNg$majorAxisDistance2(Rect rect, int i, Rect rect2) {
        float top;
        float bottom;
        float top2;
        float bottom2;
        float f;
        if (!FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s())) {
            if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s())) {
                top = rect.getLeft();
                bottom = rect2.getRight();
            } else if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m399getUpdhqQ8s())) {
                top2 = rect2.getTop();
                bottom2 = rect.getBottom();
            } else {
                if (!FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m392getDowndhqQ8s())) {
                    throw new IllegalStateException(invalidFocusDirection.toString());
                }
                top = rect.getTop();
                bottom = rect2.getBottom();
            }
            f = top - bottom;
            return Math.max(0.0f, f);
        }
        top2 = rect2.getLeft();
        bottom2 = rect.getRight();
        f = top2 - bottom2;
        return Math.max(0.0f, f);
    }

    private static final float beamBeats_I7lrPNg$majorAxisDistanceToFarEdge(Rect rect, int i, Rect rect2) {
        float bottom;
        float bottom2;
        float top;
        float top2;
        float f;
        if (!FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s())) {
            if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s())) {
                bottom = rect.getRight();
                bottom2 = rect2.getRight();
            } else if (FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m399getUpdhqQ8s())) {
                top = rect2.getTop();
                top2 = rect.getTop();
            } else {
                if (!FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m392getDowndhqQ8s())) {
                    throw new IllegalStateException(invalidFocusDirection.toString());
                }
                bottom = rect.getBottom();
                bottom2 = rect2.getBottom();
            }
            f = bottom - bottom2;
            return Math.max(1.0f, f);
        }
        top = rect2.getLeft();
        top2 = rect.getLeft();
        f = top - top2;
        return Math.max(1.0f, f);
    }

    /* renamed from: beamBeats-I7lrPNg, reason: not valid java name */
    private static final boolean m403beamBeatsI7lrPNg(Rect rect, Rect rect2, Rect rect3, int i) {
        if (beamBeats_I7lrPNg$inSourceBeam(rect3, i, rect) || !beamBeats_I7lrPNg$inSourceBeam(rect2, i, rect)) {
            return false;
        }
        return !beamBeats_I7lrPNg$isInDirectionOfSearch(rect3, i, rect) || FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m394getLeftdhqQ8s()) || FocusDirection.m386equalsimpl0(i, FocusDirection.INSTANCE.m398getRightdhqQ8s()) || m404beamBeats_I7lrPNg$majorAxisDistance2(rect2, i, rect) < beamBeats_I7lrPNg$majorAxisDistanceToFarEdge(rect3, i, rect);
    }

    private static final Rect topLeft(Rect rect) {
        return new Rect(rect.getLeft(), rect.getTop(), rect.getLeft(), rect.getTop());
    }

    private static final Rect bottomRight(Rect rect) {
        return new Rect(rect.getRight(), rect.getBottom(), rect.getRight(), rect.getBottom());
    }
}
