package androidx.compose.ui.semantics;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeWrapper;
import androidx.compose.ui.semantics.NodeLocationHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SemanticsSort.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003H\u0000\u001a\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006*\u00020\u00012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0000\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\u0001H\u0000¨\u0006\f"}, d2 = {"findNodeByPredicateTraversal", "Landroidx/compose/ui/node/LayoutNode;", "predicate", "Lkotlin/Function1;", "", "findOneLayerOfSemanticsWrappersSortedByBounds", "", "Landroidx/compose/ui/semantics/SemanticsWrapper;", "list", "", "findWrapperToGetBounds", "Landroidx/compose/ui/node/LayoutNodeWrapper;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SemanticsSortKt {
    public static /* synthetic */ List findOneLayerOfSemanticsWrappersSortedByBounds$default(LayoutNode layoutNode, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        return findOneLayerOfSemanticsWrappersSortedByBounds(layoutNode, list);
    }

    private static final List<NodeLocationHolder> findOneLayerOfSemanticsWrappersSortedByBounds$sortWithStrategy(List<NodeLocationHolder> list) {
        try {
            NodeLocationHolder.INSTANCE.setComparisonStrategy$ui_release(NodeLocationHolder.ComparisonStrategy.Stripe);
            List<NodeLocationHolder> mutableList = CollectionsKt.toMutableList((Collection) list);
            CollectionsKt.sort(mutableList);
            return mutableList;
        } catch (IllegalArgumentException unused) {
            NodeLocationHolder.INSTANCE.setComparisonStrategy$ui_release(NodeLocationHolder.ComparisonStrategy.Location);
            List<NodeLocationHolder> mutableList2 = CollectionsKt.toMutableList((Collection) list);
            CollectionsKt.sort(mutableList2);
            return mutableList2;
        }
    }

    public static final List<SemanticsWrapper> findOneLayerOfSemanticsWrappersSortedByBounds(LayoutNode layoutNode, List<SemanticsWrapper> list) {
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        Intrinsics.checkNotNullParameter(list, "list");
        if (layoutNode.isAttached()) {
            ArrayList arrayList = new ArrayList();
            List<LayoutNode> children$ui_release = layoutNode.getChildren$ui_release();
            int size = children$ui_release.size() - 1;
            int i = 0;
            if (size >= 0) {
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    LayoutNode layoutNode2 = children$ui_release.get(i2);
                    if (layoutNode2.isAttached()) {
                        arrayList.add(new NodeLocationHolder(layoutNode, layoutNode2));
                    }
                    if (i3 > size) {
                        break;
                    }
                    i2 = i3;
                }
            }
            List<NodeLocationHolder> findOneLayerOfSemanticsWrappersSortedByBounds$sortWithStrategy = findOneLayerOfSemanticsWrappersSortedByBounds$sortWithStrategy(arrayList);
            ArrayList arrayList2 = new ArrayList(findOneLayerOfSemanticsWrappersSortedByBounds$sortWithStrategy.size());
            int size2 = findOneLayerOfSemanticsWrappersSortedByBounds$sortWithStrategy.size() - 1;
            if (size2 >= 0) {
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    arrayList2.add(findOneLayerOfSemanticsWrappersSortedByBounds$sortWithStrategy.get(i4).getNode());
                    if (i5 > size2) {
                        break;
                    }
                    i4 = i5;
                }
            }
            ArrayList arrayList3 = arrayList2;
            int size3 = arrayList3.size() - 1;
            if (size3 >= 0) {
                while (true) {
                    int i6 = i + 1;
                    LayoutNode layoutNode3 = (LayoutNode) arrayList3.get(i);
                    SemanticsWrapper outerSemantics = SemanticsNodeKt.getOuterSemantics(layoutNode3);
                    if (outerSemantics != null) {
                        list.add(outerSemantics);
                    } else {
                        findOneLayerOfSemanticsWrappersSortedByBounds(layoutNode3, list);
                    }
                    if (i6 > size3) {
                        break;
                    }
                    i = i6;
                }
            }
        }
        return list;
    }

    public static final LayoutNode findNodeByPredicateTraversal(LayoutNode layoutNode, Function1<? super LayoutNode, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if (predicate.invoke(layoutNode).booleanValue()) {
            return layoutNode;
        }
        List<LayoutNode> children$ui_release = layoutNode.getChildren$ui_release();
        int size = children$ui_release.size() - 1;
        if (size < 0) {
            return null;
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            LayoutNode findNodeByPredicateTraversal = findNodeByPredicateTraversal(children$ui_release.get(i), predicate);
            if (findNodeByPredicateTraversal != null) {
                return findNodeByPredicateTraversal;
            }
            if (i2 > size) {
                return null;
            }
            i = i2;
        }
    }

    public static final LayoutNodeWrapper findWrapperToGetBounds(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        SemanticsWrapper outerMergingSemantics = SemanticsNodeKt.getOuterMergingSemantics(layoutNode);
        if (outerMergingSemantics != null) {
            return outerMergingSemantics;
        }
        SemanticsWrapper outerSemantics = SemanticsNodeKt.getOuterSemantics(layoutNode);
        return outerSemantics == null ? layoutNode.getInnerLayoutNodeWrapper() : outerSemantics;
    }
}
