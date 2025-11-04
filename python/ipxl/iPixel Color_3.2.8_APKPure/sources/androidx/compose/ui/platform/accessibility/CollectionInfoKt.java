package androidx.compose.ui.platform.accessibility;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.CollectionItemInfo;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectionInfo.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u0014\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000f*\u00020\u0002H\u0002\u001a\u001c\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u00120\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0007H\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u0015"}, d2 = {"isLazyCollection", "", "Landroidx/compose/ui/semantics/CollectionInfo;", "(Landroidx/compose/ui/semantics/CollectionInfo;)Z", "calculateIfHorizontallyStacked", "items", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "setCollectionInfo", "", "node", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "setCollectionItemInfo", "toAccessibilityCollectionInfo", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat$CollectionInfoCompat;", "kotlin.jvm.PlatformType", "toAccessibilityCollectionItemInfo", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat$CollectionItemInfoCompat;", "Landroidx/compose/ui/semantics/CollectionItemInfo;", "itemNode", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class CollectionInfoKt {
    public static final void setCollectionInfo(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(info, "info");
        CollectionInfo collectionInfo = (CollectionInfo) SemanticsConfigurationKt.getOrNull(node.getConfig(), SemanticsProperties.INSTANCE.getCollectionInfo());
        if (collectionInfo != null) {
            info.setCollectionInfo(toAccessibilityCollectionInfo(collectionInfo));
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (SemanticsConfigurationKt.getOrNull(node.getConfig(), SemanticsProperties.INSTANCE.getSelectableGroup()) != null) {
            List<SemanticsNode> replacedChildren$ui_release = node.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size() - 1;
            if (size >= 0) {
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    SemanticsNode semanticsNode = replacedChildren$ui_release.get(i);
                    if (semanticsNode.getConfig().contains(SemanticsProperties.INSTANCE.getSelected())) {
                        arrayList.add(semanticsNode);
                    }
                    if (i2 > size) {
                        break;
                    } else {
                        i = i2;
                    }
                }
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return;
        }
        boolean calculateIfHorizontallyStacked = calculateIfHorizontallyStacked(arrayList);
        info.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(calculateIfHorizontallyStacked ? 1 : arrayList2.size(), calculateIfHorizontallyStacked ? arrayList2.size() : 1, false, 0));
    }

    public static final void setCollectionItemInfo(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(info, "info");
        CollectionItemInfo collectionItemInfo = (CollectionItemInfo) SemanticsConfigurationKt.getOrNull(node.getConfig(), SemanticsProperties.INSTANCE.getCollectionItemInfo());
        if (collectionItemInfo != null) {
            info.setCollectionItemInfo(toAccessibilityCollectionItemInfo(collectionItemInfo, node));
        }
        SemanticsNode parent = node.getParent();
        if (parent == null || SemanticsConfigurationKt.getOrNull(parent.getConfig(), SemanticsProperties.INSTANCE.getSelectableGroup()) == null) {
            return;
        }
        CollectionInfo collectionInfo = (CollectionInfo) SemanticsConfigurationKt.getOrNull(parent.getConfig(), SemanticsProperties.INSTANCE.getCollectionInfo());
        if ((collectionInfo != null && isLazyCollection(collectionInfo)) || !node.getConfig().contains(SemanticsProperties.INSTANCE.getSelected())) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<SemanticsNode> replacedChildren$ui_release = parent.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size() - 1;
        if (size >= 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                SemanticsNode semanticsNode = replacedChildren$ui_release.get(i);
                if (semanticsNode.getConfig().contains(SemanticsProperties.INSTANCE.getSelected())) {
                    arrayList.add(semanticsNode);
                }
                if (i2 > size) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        boolean calculateIfHorizontallyStacked = calculateIfHorizontallyStacked(arrayList);
        int size2 = arrayList.size() - 1;
        if (size2 < 0) {
            return;
        }
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            SemanticsNode semanticsNode2 = (SemanticsNode) arrayList.get(i3);
            if (semanticsNode2.getId() == node.getId()) {
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat obtain = AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(calculateIfHorizontallyStacked ? 0 : i3, 1, calculateIfHorizontallyStacked ? i3 : 0, 1, false, ((Boolean) semanticsNode2.getConfig().getOrElse(SemanticsProperties.INSTANCE.getSelected(), new Function0<Boolean>() { // from class: androidx.compose.ui.platform.accessibility.CollectionInfoKt$setCollectionItemInfo$2$itemInfo$1
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final boolean invoke2() {
                        return false;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Boolean invoke() {
                        return Boolean.valueOf(invoke2());
                    }
                })).booleanValue());
                if (obtain != null) {
                    info.setCollectionItemInfo(obtain);
                }
            }
            if (i4 > size2) {
                return;
            } else {
                i3 = i4;
            }
        }
    }

    private static final boolean calculateIfHorizontallyStacked(List<SemanticsNode> list) {
        ArrayList emptyList;
        long packedValue;
        if (list.size() < 2) {
            return true;
        }
        if (list.size() == 0 || list.size() == 1) {
            emptyList = CollectionsKt.emptyList();
        } else {
            emptyList = new ArrayList();
            SemanticsNode semanticsNode = list.get(0);
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (lastIndex > 0) {
                int i = 0;
                while (true) {
                    i++;
                    SemanticsNode semanticsNode2 = list.get(i);
                    SemanticsNode semanticsNode3 = semanticsNode2;
                    SemanticsNode semanticsNode4 = semanticsNode;
                    emptyList.add(Offset.m431boximpl(OffsetKt.Offset(Math.abs(Offset.m442getXimpl(semanticsNode4.getBoundsInRoot().m472getCenterF1C5BW0()) - Offset.m442getXimpl(semanticsNode3.getBoundsInRoot().m472getCenterF1C5BW0())), Math.abs(Offset.m443getYimpl(semanticsNode4.getBoundsInRoot().m472getCenterF1C5BW0()) - Offset.m443getYimpl(semanticsNode3.getBoundsInRoot().m472getCenterF1C5BW0())))));
                    if (i >= lastIndex) {
                        break;
                    }
                    semanticsNode = semanticsNode2;
                }
            }
        }
        if (emptyList.size() == 1) {
            packedValue = ((Offset) CollectionsKt.first(emptyList)).getPackedValue();
        } else {
            if (emptyList.isEmpty()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object first = CollectionsKt.first((List<? extends Object>) emptyList);
            int lastIndex2 = CollectionsKt.getLastIndex(emptyList);
            if (1 <= lastIndex2) {
                int i2 = 1;
                while (true) {
                    int i3 = i2 + 1;
                    first = Offset.m431boximpl(Offset.m447plusMKHz9U(((Offset) first).getPackedValue(), ((Offset) emptyList.get(i2)).getPackedValue()));
                    if (i2 == lastIndex2) {
                        break;
                    }
                    i2 = i3;
                }
            }
            packedValue = ((Offset) first).getPackedValue();
        }
        return Offset.m433component2impl(packedValue) < Offset.m432component1impl(packedValue);
    }

    private static final boolean isLazyCollection(CollectionInfo collectionInfo) {
        return collectionInfo.getRowCount() < 0 || collectionInfo.getColumnCount() < 0;
    }

    private static final AccessibilityNodeInfoCompat.CollectionInfoCompat toAccessibilityCollectionInfo(CollectionInfo collectionInfo) {
        return AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(collectionInfo.getRowCount(), collectionInfo.getColumnCount(), false, 0);
    }

    private static final AccessibilityNodeInfoCompat.CollectionItemInfoCompat toAccessibilityCollectionItemInfo(CollectionItemInfo collectionItemInfo, SemanticsNode semanticsNode) {
        return AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(collectionItemInfo.getRowIndex(), collectionItemInfo.getRowSpan(), collectionItemInfo.getColumnIndex(), collectionItemInfo.getColumnSpan(), false, ((Boolean) semanticsNode.getConfig().getOrElse(SemanticsProperties.INSTANCE.getSelected(), new Function0<Boolean>() { // from class: androidx.compose.ui.platform.accessibility.CollectionInfoKt$toAccessibilityCollectionItemInfo$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                return false;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }
        })).booleanValue());
    }
}
