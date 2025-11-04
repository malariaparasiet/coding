package androidx.compose.ui.node;

import androidx.compose.ui.node.LayoutNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutTreeConsistencyChecker.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0003H\u0002J\f\u0010\u0011\u001a\u00020\f*\u00020\u0003H\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/node/LayoutTreeConsistencyChecker;", "", "root", "Landroidx/compose/ui/node/LayoutNode;", "relayoutNodes", "Landroidx/compose/ui/node/DepthSortedSet;", "postponedMeasureRequests", "", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/node/DepthSortedSet;Ljava/util/List;)V", "assertConsistent", "", "isTreeConsistent", "", "node", "logTree", "", "nodeToString", "consistentLayoutState", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutTreeConsistencyChecker {
    private final List<LayoutNode> postponedMeasureRequests;
    private final DepthSortedSet relayoutNodes;
    private final LayoutNode root;

    public LayoutTreeConsistencyChecker(LayoutNode root, DepthSortedSet relayoutNodes, List<LayoutNode> postponedMeasureRequests) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(relayoutNodes, "relayoutNodes");
        Intrinsics.checkNotNullParameter(postponedMeasureRequests, "postponedMeasureRequests");
        this.root = root;
        this.relayoutNodes = relayoutNodes;
        this.postponedMeasureRequests = postponedMeasureRequests;
    }

    public final void assertConsistent() {
        if (isTreeConsistent(this.root)) {
            return;
        }
        System.out.println((Object) logTree());
        throw new IllegalStateException("Inconsistency found!");
    }

    private final boolean isTreeConsistent(LayoutNode node) {
        if (!consistentLayoutState(node)) {
            return false;
        }
        List<LayoutNode> children$ui_release = node.getChildren$ui_release();
        int size = children$ui_release.size() - 1;
        if (size < 0) {
            return true;
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!isTreeConsistent(children$ui_release.get(i))) {
                return false;
            }
            if (i2 > size) {
                return true;
            }
            i = i2;
        }
    }

    private final boolean consistentLayoutState(LayoutNode layoutNode) {
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if ((!layoutNode.getIsPlaced() && (layoutNode.getPlaceOrder() == Integer.MAX_VALUE || parent$ui_release == null || !parent$ui_release.getIsPlaced())) || (layoutNode.getLayoutState() == LayoutNode.LayoutState.NeedsRemeasure && this.postponedMeasureRequests.contains(layoutNode))) {
            return true;
        }
        LayoutNode.LayoutState layoutState = parent$ui_release == null ? null : parent$ui_release.getLayoutState();
        return layoutNode.getLayoutState() == LayoutNode.LayoutState.NeedsRemeasure ? this.relayoutNodes.contains(layoutNode) || layoutState == LayoutNode.LayoutState.NeedsRemeasure || layoutState == LayoutNode.LayoutState.Measuring : layoutNode.getLayoutState() != LayoutNode.LayoutState.NeedsRelayout || this.relayoutNodes.contains(layoutNode) || layoutState == LayoutNode.LayoutState.NeedsRemeasure || layoutState == LayoutNode.LayoutState.NeedsRelayout || layoutState == LayoutNode.LayoutState.Measuring || layoutState == LayoutNode.LayoutState.LayingOut;
    }

    private final String nodeToString(LayoutNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append(node);
        sb.append("[" + node.getLayoutState() + ']');
        if (!node.getIsPlaced()) {
            sb.append("[!isPlaced]");
        }
        sb.append("[measuredByParent=" + node.getMeasuredByParent() + ']');
        if (!consistentLayoutState(node)) {
            sb.append("[INCONSISTENT]");
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "with(StringBuilder()) {\n            append(node)\n            append(\"[${node.layoutState}]\")\n            if (!node.isPlaced) append(\"[!isPlaced]\")\n            append(\"[measuredByParent=${node.measuredByParent}]\")\n            if (!node.consistentLayoutState()) {\n                append(\"[INCONSISTENT]\")\n            }\n            toString()\n        }");
        return sb2;
    }

    private final String logTree() {
        StringBuilder sb = new StringBuilder();
        StringBuilder append = sb.append("Tree state:");
        Intrinsics.checkNotNullExpressionValue(append, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
        logTree$printSubTree(this, sb, this.root, 0);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "stringBuilder.toString()");
        return sb2;
    }

    private static final void logTree$printSubTree(LayoutTreeConsistencyChecker layoutTreeConsistencyChecker, StringBuilder sb, LayoutNode layoutNode, int i) {
        String nodeToString = layoutTreeConsistencyChecker.nodeToString(layoutNode);
        int i2 = 0;
        if (nodeToString.length() > 0) {
            if (i > 0) {
                int i3 = 0;
                do {
                    i3++;
                    sb.append("..");
                } while (i3 < i);
            }
            StringBuilder append = sb.append(nodeToString);
            Intrinsics.checkNotNullExpressionValue(append, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
            i++;
        }
        List<LayoutNode> children$ui_release = layoutNode.getChildren$ui_release();
        int size = children$ui_release.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i4 = i2 + 1;
            logTree$printSubTree(layoutTreeConsistencyChecker, sb, children$ui_release.get(i2), i);
            if (i4 > size) {
                return;
            } else {
                i2 = i4;
            }
        }
    }
}
