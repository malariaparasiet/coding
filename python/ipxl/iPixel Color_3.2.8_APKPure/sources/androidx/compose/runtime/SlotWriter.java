package androidx.compose.runtime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0017\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b0\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\nJ\u0010\u00102\u001a\u00020\u00072\b\b\u0002\u00103\u001a\u00020\nJ\u000e\u00104\u001a\u00020\n2\u0006\u00102\u001a\u00020\u0007J\u0006\u00105\u001a\u000200J\u0006\u00106\u001a\u000200J \u00107\u001a\u00020\n2\u0006\u00102\u001a\u00020\n2\u0006\u00108\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u00109\u001a\u00020\n2\u0006\u00103\u001a\u00020\nH\u0002J\u0010\u0010:\u001a\u00020\n2\u0006\u00109\u001a\u00020\nH\u0002J(\u0010;\u001a\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u0010<\u001a\u00020\n2\u0006\u00108\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010=\u001a\u00020\nJ\u0006\u0010>\u001a\u000200J\u000e\u0010?\u001a\u0002002\u0006\u00102\u001a\u00020\u0007J\u000e\u0010?\u001a\u0002002\u0006\u00103\u001a\u00020\nJ \u0010@\u001a\u0002002\u0006\u0010\"\u001a\u00020\n2\u0006\u0010=\u001a\u00020\n2\u0006\u0010A\u001a\u00020\nH\u0002J\u0010\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020\nJ\u0010\u0010C\u001a\u00020\n2\u0006\u00103\u001a\u00020\nH\u0002J\u000e\u0010D\u001a\u00020\n2\u0006\u00103\u001a\u00020\nJ\u0010\u0010E\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020\nJ\u000e\u0010F\u001a\u00020\n2\u0006\u00103\u001a\u00020\nJ\u000e\u0010G\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010HJ\u0006\u0010I\u001a\u00020JJ\u0010\u0010K\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u0010\u0010M\u001a\u0002002\u0006\u0010$\u001a\u00020\nH\u0002J\u0018\u0010N\u001a\u0002002\u0006\u0010$\u001a\u00020\n2\u0006\u0010O\u001a\u00020\nH\u0002J\u000e\u0010P\u001a\b\u0012\u0004\u0012\u00020\n0QH\u0002J \u0010R\u001a\u0002002\u0006\u0010S\u001a\u00020\n2\u0006\u0010T\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH\u0002J\u001c\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00070Q2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00103\u001a\u00020\nJ\u000e\u0010V\u001a\u0002002\u0006\u0010W\u001a\u00020\nJ\u0010\u0010X\u001a\u0002002\u0006\u00103\u001a\u00020\nH\u0002J\u0018\u0010Y\u001a\u0002002\u0006\u00103\u001a\u00020\n2\u0006\u0010O\u001a\u00020\nH\u0002J\u0010\u0010Z\u001a\u0004\u0018\u00010\u00012\u0006\u00102\u001a\u00020\u0007J\u0010\u0010Z\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020\nJ\u000e\u0010\"\u001a\u00020\n2\u0006\u00102\u001a\u00020\u0007J\u000e\u0010\"\u001a\u00020\n2\u0006\u00103\u001a\u00020\nJ\u0010\u0010[\u001a\u00020\n2\u0006\u00103\u001a\u00020\nH\u0002J\u0018\u0010\\\u001a\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u0010<\u001a\u00020\nH\u0002J\u0018\u0010]\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH\u0002J\u0006\u0010^\u001a\u00020\u000eJ\u0018\u0010_\u001a\u00020\u000e2\u0006\u0010`\u001a\u00020\n2\u0006\u0010a\u001a\u00020\nH\u0002J \u0010b\u001a\u0002002\u0006\u0010`\u001a\u00020\n2\u0006\u0010a\u001a\u00020\n2\u0006\u0010O\u001a\u00020\nH\u0002J\b\u0010c\u001a\u00020\nH\u0002J\b\u0010d\u001a\u000200H\u0002J\u000e\u0010e\u001a\u0002002\u0006\u00102\u001a\u00020\u0007J\u0010\u0010f\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u001a\u0010f\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020\n2\b\u0010L\u001a\u0004\u0018\u00010\u0001J\b\u0010g\u001a\u0004\u0018\u00010\u0001J\u0006\u0010h\u001a\u00020\nJ\u0006\u0010i\u001a\u000200J\u0018\u0010j\u001a\u0002002\u0006\u0010k\u001a\u00020\n2\b\u0010l\u001a\u0004\u0018\u00010\u0001J\"\u0010j\u001a\u0002002\u0006\u0010k\u001a\u00020\n2\b\u0010m\u001a\u0004\u0018\u00010\u00012\b\u0010l\u001a\u0004\u0018\u00010\u0001J\u0006\u0010n\u001a\u000200J\u000e\u0010n\u001a\u0002002\u0006\u0010k\u001a\u00020\nJ\u0018\u0010n\u001a\u0002002\u0006\u0010k\u001a\u00020\n2\b\u0010o\u001a\u0004\u0018\u00010\u0001J,\u0010n\u001a\u0002002\u0006\u0010k\u001a\u00020\n2\b\u0010m\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001f\u001a\u00020\u000e2\b\u0010l\u001a\u0004\u0018\u00010\u0001H\u0002J\u0010\u0010p\u001a\u0002002\b\u0010k\u001a\u0004\u0018\u00010\u0001J\u001a\u0010p\u001a\u0002002\b\u0010k\u001a\u0004\u0018\u00010\u00012\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\b\u0010q\u001a\u00020JH\u0016J\u0012\u0010r\u001a\u0004\u0018\u00010\u00012\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u0018\u0010s\u001a\u0002002\u0006\u0010t\u001a\u00020\n2\u0006\u0010u\u001a\u00020\nH\u0002J\u0010\u0010v\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u0018\u0010w\u001a\u0002002\u0006\u00102\u001a\u00020\u00072\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u0010\u0010w\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u001a\u0010x\u001a\u0002002\u0006\u00103\u001a\u00020\n2\b\u0010L\u001a\u0004\u0018\u00010\u0001H\u0002J\u0010\u0010y\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\r\u0010z\u001a\u000200H\u0000¢\u0006\u0002\b{J\r\u0010|\u001a\u000200H\u0000¢\u0006\u0002\b}J\u0014\u0010~\u001a\u00020\n*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\nH\u0002J\u0014\u00109\u001a\u00020\n*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\nH\u0002J\u0013\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\n0Q*\u00020\u001cH\u0002J\u001b\u0010\u0081\u0001\u001a\u000200*\b0\u0082\u0001j\u0003`\u0083\u00012\u0006\u00103\u001a\u00020\nH\u0002J\u0015\u0010\u0084\u0001\u001a\u00020\n*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\nH\u0002J\u0014\u0010\"\u001a\u00020\n*\u00020\u001c2\u0006\u00103\u001a\u00020\nH\u0002J\u0015\u0010\u0085\u0001\u001a\u00020\n*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\nH\u0002J\u001d\u0010\u0086\u0001\u001a\u000200*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\n2\u0006\u00109\u001a\u00020\nH\u0002R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0011R\u0011\u0010\u001f\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0011R\u000e\u0010 \u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\fR\u0014\u0010$\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\fR\u0018\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010'X\u0082\u000e¢\u0006\u0004\n\u0002\u0010(R\u000e\u0010)\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u0006\u0087\u0001"}, d2 = {"Landroidx/compose/runtime/SlotWriter;", "", "table", "Landroidx/compose/runtime/SlotTable;", "(Landroidx/compose/runtime/SlotTable;)V", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/Anchor;", "Lkotlin/collections/ArrayList;", "capacity", "", "getCapacity", "()I", "<set-?>", "", "closed", "getClosed", "()Z", "currentGroup", "getCurrentGroup", "currentGroupEnd", "currentSlot", "currentSlotEnd", "endStack", "Landroidx/compose/runtime/IntStack;", "groupGapLen", "groupGapStart", "groups", "", "insertCount", "isGroupEnd", "isNode", "nodeCount", "nodeCountStack", "parent", "getParent", "size", "getSize$runtime_release", "slots", "", "[Ljava/lang/Object;", "slotsGapLen", "slotsGapOwner", "slotsGapStart", "startStack", "getTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "advanceBy", "", "amount", "anchor", "index", "anchorIndex", "beginInsert", "close", "dataAnchorToDataIndex", "gapLen", "dataIndex", "dataIndexToDataAddress", "dataIndexToDataAnchor", "gapStart", "endGroup", "endInsert", "ensureStarted", "fixParentAnchorsFor", "firstChild", "groupAux", "groupIndexToAddress", "groupKey", "groupObjectKey", "groupSize", "groupSlots", "", "groupsAsString", "", "insertAux", "value", "insertGroups", "insertSlots", "group", "keys", "", "moveAnchors", "originalLocation", "newLocation", "moveFrom", "moveGroup", TypedValues.CycleType.S_WAVE_OFFSET, "moveGroupGapTo", "moveSlotGapTo", "node", "parentAnchorToIndex", "parentIndexToAnchor", "removeAnchors", "removeGroup", "removeGroups", "start", "len", "removeSlots", "restoreCurrentGroupEnd", "saveCurrentGroupEnd", "seek", "set", "skip", "skipGroup", "skipToGroupEnd", "startData", "key", "aux", "objectKey", "startGroup", "dataKey", "startNode", "toString", "update", "updateAnchors", "previousGapStart", "newGapStart", "updateAux", "updateNode", "updateNodeOfGroup", "updateParentNode", "verifyDataAnchors", "verifyDataAnchors$runtime_release", "verifyParentAnchors", "verifyParentAnchors$runtime_release", "auxIndex", "address", "dataIndexes", "groupAsString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "nodeIndex", "slotIndex", "updateDataIndex", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SlotWriter {
    private ArrayList<Anchor> anchors;
    private boolean closed;
    private int currentGroup;
    private int currentGroupEnd;
    private int currentSlot;
    private int currentSlotEnd;
    private final IntStack endStack;
    private int groupGapLen;
    private int groupGapStart;
    private int[] groups;
    private int insertCount;
    private int nodeCount;
    private final IntStack nodeCountStack;
    private int parent;
    private Object[] slots;
    private int slotsGapLen;
    private int slotsGapOwner;
    private int slotsGapStart;
    private final IntStack startStack;
    private final SlotTable table;

    private final int dataAnchorToDataIndex(int anchor, int gapLen, int capacity) {
        return anchor < 0 ? (capacity - gapLen) + anchor + 1 : anchor;
    }

    private final int dataIndexToDataAnchor(int index, int gapStart, int gapLen, int capacity) {
        return index > gapStart ? -(((capacity - gapLen) - index) + 1) : index;
    }

    public SlotWriter(SlotTable table) {
        Intrinsics.checkNotNullParameter(table, "table");
        this.table = table;
        this.groups = table.getGroups();
        this.slots = table.getSlots();
        this.anchors = table.getAnchors$runtime_release();
        this.groupGapStart = table.getGroupsSize();
        this.groupGapLen = (this.groups.length / 5) - table.getGroupsSize();
        this.currentGroupEnd = table.getGroupsSize();
        this.slotsGapStart = table.getSlotsSize();
        this.slotsGapLen = this.slots.length - table.getSlotsSize();
        this.slotsGapOwner = table.getGroupsSize();
        this.startStack = new IntStack();
        this.endStack = new IntStack();
        this.nodeCountStack = new IntStack();
        this.parent = -1;
    }

    /* renamed from: getTable$runtime_release, reason: from getter */
    public final SlotTable getTable() {
        return this.table;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final boolean isGroupEnd() {
        return this.currentGroup == this.currentGroupEnd;
    }

    public final boolean isNode() {
        boolean isNode;
        int i = this.currentGroup;
        if (i >= this.currentGroupEnd) {
            return false;
        }
        isNode = SlotTableKt.isNode(this.groups, groupIndexToAddress(i));
        return isNode;
    }

    public final int groupKey(int index) {
        int key;
        key = SlotTableKt.key(this.groups, groupIndexToAddress(index));
        return key;
    }

    public final Object groupObjectKey(int index) {
        boolean hasObjectKey;
        int objectKeyIndex;
        int groupIndexToAddress = groupIndexToAddress(index);
        hasObjectKey = SlotTableKt.hasObjectKey(this.groups, groupIndexToAddress);
        if (!hasObjectKey) {
            return null;
        }
        Object[] objArr = this.slots;
        objectKeyIndex = SlotTableKt.objectKeyIndex(this.groups, groupIndexToAddress);
        return objArr[objectKeyIndex];
    }

    public final int groupSize(int index) {
        int groupSize;
        groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress(index));
        return groupSize;
    }

    public final Object groupAux(int index) {
        boolean hasAux;
        int groupIndexToAddress = groupIndexToAddress(index);
        hasAux = SlotTableKt.hasAux(this.groups, groupIndexToAddress);
        return hasAux ? this.slots[auxIndex(this.groups, groupIndexToAddress)] : Composer.INSTANCE.getEmpty();
    }

    public final Object node(int index) {
        boolean isNode;
        int groupIndexToAddress = groupIndexToAddress(index);
        isNode = SlotTableKt.isNode(this.groups, groupIndexToAddress);
        if (isNode) {
            return this.slots[dataIndexToDataAddress(nodeIndex(this.groups, groupIndexToAddress))];
        }
        return null;
    }

    public final Object node(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        return node(anchor.toIndexFor(this));
    }

    public final int getParent() {
        return this.parent;
    }

    public final int parent(int index) {
        return parent(this.groups, index);
    }

    public final int parent(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (anchor.getValid()) {
            return parent(this.groups, anchorIndex(anchor));
        }
        return -1;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final void close() {
        this.closed = true;
        moveGroupGapTo(getSize$runtime_release());
        moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
        this.table.close$runtime_release(this, this.groups, this.groupGapStart, this.slots, this.slotsGapStart, this.anchors);
    }

    public final Object update(Object value) {
        Object skip = skip();
        set(value);
        return skip;
    }

    public final void updateAux(Object value) {
        boolean hasAux;
        int groupIndexToAddress = groupIndexToAddress(this.currentGroup);
        hasAux = SlotTableKt.hasAux(this.groups, groupIndexToAddress);
        if (hasAux) {
            this.slots[dataIndexToDataAddress(auxIndex(this.groups, groupIndexToAddress))] = value;
        } else {
            ComposerKt.composeRuntimeError("Updating the data of a group that was not created with a data slot".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final void insertAux(Object value) {
        boolean hasAux;
        if (this.insertCount >= 0) {
            int i = this.parent;
            int groupIndexToAddress = groupIndexToAddress(i);
            hasAux = SlotTableKt.hasAux(this.groups, groupIndexToAddress);
            if (!hasAux) {
                insertSlots(1, i);
                int auxIndex = auxIndex(this.groups, groupIndexToAddress);
                int dataIndexToDataAddress = dataIndexToDataAddress(auxIndex);
                int i2 = this.currentSlot;
                if (i2 > auxIndex) {
                    int i3 = i2 - auxIndex;
                    if (!(i3 < 3)) {
                        throw new IllegalStateException("Moving more than two slot not supported".toString());
                    }
                    if (i3 > 1) {
                        Object[] objArr = this.slots;
                        objArr[dataIndexToDataAddress + 2] = objArr[dataIndexToDataAddress + 1];
                    }
                    Object[] objArr2 = this.slots;
                    objArr2[dataIndexToDataAddress + 1] = objArr2[dataIndexToDataAddress];
                }
                SlotTableKt.addAux(this.groups, groupIndexToAddress);
                this.slots[dataIndexToDataAddress] = value;
                this.currentSlot++;
                return;
            }
            ComposerKt.composeRuntimeError("Group already has auxiliary data".toString());
            throw new KotlinNothingValueException();
        }
        ComposerKt.composeRuntimeError("Cannot insert auxiliary data when not inserting".toString());
        throw new KotlinNothingValueException();
    }

    public final void updateNode(Object value) {
        updateNodeOfGroup(this.currentGroup, value);
    }

    public final void updateNode(Anchor anchor, Object value) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        updateNodeOfGroup(anchor.toIndexFor(this), value);
    }

    public final void updateParentNode(Object value) {
        updateNodeOfGroup(this.parent, value);
    }

    public final void set(Object value) {
        int i = this.currentSlot;
        if (i <= this.currentSlotEnd) {
            this.slots[dataIndexToDataAddress(i - 1)] = value;
        } else {
            ComposerKt.composeRuntimeError("Writing to an invalid slot".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final Object set(int index, Object value) {
        int slotIndex = slotIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int i = slotIndex + index;
        if (i >= slotIndex && i < dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1))) {
            int dataIndexToDataAddress = dataIndexToDataAddress(i);
            Object[] objArr = this.slots;
            Object obj = objArr[dataIndexToDataAddress];
            objArr[dataIndexToDataAddress] = value;
            return obj;
        }
        ComposerKt.composeRuntimeError(("Write to an invalid slot index " + index + " for group " + getCurrentGroup()).toString());
        throw new KotlinNothingValueException();
    }

    public final Object skip() {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        return objArr[dataIndexToDataAddress(i)];
    }

    public final void advanceBy(int amount) {
        if (!(amount >= 0)) {
            throw new IllegalArgumentException("Cannot seek backwards".toString());
        }
        if (!(this.insertCount <= 0)) {
            throw new IllegalStateException("Cannot call seek() while inserting".toString());
        }
        int i = this.currentGroup + amount;
        if (i >= this.parent && i <= this.currentGroupEnd) {
            this.currentGroup = i;
            int dataIndex = dataIndex(this.groups, groupIndexToAddress(i));
            this.currentSlot = dataIndex;
            this.currentSlotEnd = dataIndex;
            return;
        }
        ComposerKt.composeRuntimeError(("Cannot seek outside the current group (" + getParent() + '-' + this.currentGroupEnd + ')').toString());
        throw new KotlinNothingValueException();
    }

    public final void seek(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        advanceBy(anchor.toIndexFor(this) - this.currentGroup);
    }

    public final void skipToGroupEnd() {
        int i = this.currentGroupEnd;
        this.currentGroup = i;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(i));
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            saveCurrentGroupEnd();
        }
    }

    public final void endInsert() {
        int i = this.insertCount;
        if (!(i > 0)) {
            throw new IllegalStateException("Unbalanced begin/end insert".toString());
        }
        int i2 = i - 1;
        this.insertCount = i2;
        if (i2 == 0) {
            if (this.nodeCountStack.getTos() == this.startStack.getTos()) {
                restoreCurrentGroupEnd();
            } else {
                ComposerKt.composeRuntimeError("startGroup/endGroup mismatch while inserting".toString());
                throw new KotlinNothingValueException();
            }
        }
    }

    public final void startGroup() {
        if (!(this.insertCount == 0)) {
            throw new IllegalArgumentException("Key must be supplied when inserting".toString());
        }
        startGroup(0, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
    }

    public final void startGroup(int key) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
    }

    public final void startGroup(int key, Object dataKey) {
        startGroup(key, dataKey, false, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(Object key) {
        startGroup(125, key, true, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(Object key, Object node) {
        startGroup(125, key, true, node);
    }

    public final void startData(int key, Object objectKey, Object aux) {
        startGroup(key, objectKey, false, aux);
    }

    public final void startData(int key, Object aux) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, aux);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v2 */
    private final void startGroup(int key, Object objectKey, boolean isNode, Object aux) {
        int nodeCount;
        int groupSize;
        int i;
        Object[] objArr = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        if (objArr != false) {
            insertGroups(1);
            int i2 = this.currentGroup;
            int groupIndexToAddress = groupIndexToAddress(i2);
            ?? r7 = objectKey != Composer.INSTANCE.getEmpty() ? 1 : 0;
            ?? r8 = (isNode || aux == Composer.INSTANCE.getEmpty()) ? 0 : 1;
            SlotTableKt.initGroup(this.groups, groupIndexToAddress, key, isNode, r7, r8, this.parent, this.currentSlot);
            this.currentSlotEnd = this.currentSlot;
            int i3 = (isNode ? 1 : 0) + r7 + r8;
            if (i3 > 0) {
                insertSlots(i3, i2);
                Object[] objArr2 = this.slots;
                int i4 = this.currentSlot;
                if (isNode) {
                    objArr2[i4] = aux;
                    i4++;
                }
                if (r7 != 0) {
                    objArr2[i4] = objectKey;
                    i4++;
                }
                if (r8 != 0) {
                    objArr2[i4] = aux;
                    i4++;
                }
                this.currentSlot = i4;
            }
            this.nodeCount = 0;
            i = i2 + 1;
            this.parent = i2;
            this.currentGroup = i;
        } else {
            this.startStack.push(this.parent);
            saveCurrentGroupEnd();
            int i5 = this.currentGroup;
            int groupIndexToAddress2 = groupIndexToAddress(i5);
            if (!Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty())) {
                if (isNode) {
                    updateNode(aux);
                } else {
                    updateAux(aux);
                }
            }
            this.currentSlot = slotIndex(this.groups, groupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            nodeCount = SlotTableKt.nodeCount(this.groups, groupIndexToAddress2);
            this.nodeCount = nodeCount;
            this.parent = i5;
            this.currentGroup = i5 + 1;
            groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress2);
            i = i5 + groupSize;
        }
        this.currentGroupEnd = i;
    }

    public final int endGroup() {
        boolean isNode;
        int groupSize;
        int nodeCount;
        boolean isNode2;
        int nodeCount2;
        int groupSize2;
        boolean z = this.insertCount > 0;
        int i = this.currentGroup;
        int i2 = this.currentGroupEnd;
        int i3 = this.parent;
        int groupIndexToAddress = groupIndexToAddress(i3);
        int i4 = this.nodeCount;
        int i5 = i - i3;
        isNode = SlotTableKt.isNode(this.groups, groupIndexToAddress);
        if (z) {
            SlotTableKt.updateGroupSize(this.groups, groupIndexToAddress, i5);
            SlotTableKt.updateNodeCount(this.groups, groupIndexToAddress, i4);
            this.nodeCount = this.nodeCountStack.pop() + (isNode ? 1 : i4);
            this.parent = parent(this.groups, i3);
            return i4;
        }
        if ((i != i2 ? 0 : 1) != 0) {
            groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress);
            nodeCount = SlotTableKt.nodeCount(this.groups, groupIndexToAddress);
            SlotTableKt.updateGroupSize(this.groups, groupIndexToAddress, i5);
            SlotTableKt.updateNodeCount(this.groups, groupIndexToAddress, i4);
            int pop = this.startStack.pop();
            restoreCurrentGroupEnd();
            this.parent = pop;
            int parent = parent(this.groups, i3);
            int pop2 = this.nodeCountStack.pop();
            this.nodeCount = pop2;
            if (parent == pop) {
                this.nodeCount = pop2 + (isNode ? 0 : i4 - nodeCount);
                return i4;
            }
            int i6 = i5 - groupSize;
            int i7 = isNode ? 0 : i4 - nodeCount;
            if (i6 != 0 || i7 != 0) {
                while (parent != 0 && parent != pop && (i7 != 0 || i6 != 0)) {
                    int groupIndexToAddress2 = groupIndexToAddress(parent);
                    if (i6 != 0) {
                        groupSize2 = SlotTableKt.groupSize(this.groups, groupIndexToAddress2);
                        SlotTableKt.updateGroupSize(this.groups, groupIndexToAddress2, groupSize2 + i6);
                    }
                    if (i7 != 0) {
                        int[] iArr = this.groups;
                        nodeCount2 = SlotTableKt.nodeCount(iArr, groupIndexToAddress2);
                        SlotTableKt.updateNodeCount(iArr, groupIndexToAddress2, nodeCount2 + i7);
                    }
                    isNode2 = SlotTableKt.isNode(this.groups, groupIndexToAddress2);
                    if (isNode2) {
                        i7 = 0;
                    }
                    parent = parent(this.groups, parent);
                }
            }
            this.nodeCount += i7;
            return i4;
        }
        throw new IllegalArgumentException("Expected to be at the end of a group".toString());
    }

    public final void ensureStarted(int index) {
        if (!(this.insertCount <= 0)) {
            throw new IllegalArgumentException("Cannot call ensureStarted() while inserting".toString());
        }
        int i = this.parent;
        if (i != index) {
            if (!(index >= i && index < this.currentGroupEnd)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Started group must be a subgroup of the group at ", Integer.valueOf(i)).toString());
            }
            int i2 = this.currentGroup;
            int i3 = this.currentSlot;
            int i4 = this.currentSlotEnd;
            this.currentGroup = index;
            startGroup();
            this.currentGroup = i2;
            this.currentSlot = i3;
            this.currentSlotEnd = i4;
        }
    }

    public final void ensureStarted(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        ensureStarted(anchor.toIndexFor(this));
    }

    public final int skipGroup() {
        int groupSize;
        boolean isNode;
        int nodeCount;
        int groupIndexToAddress = groupIndexToAddress(this.currentGroup);
        int i = this.currentGroup;
        groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress);
        int i2 = i + groupSize;
        this.currentGroup = i2;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(i2));
        isNode = SlotTableKt.isNode(this.groups, groupIndexToAddress);
        if (isNode) {
            return 1;
        }
        nodeCount = SlotTableKt.nodeCount(this.groups, groupIndexToAddress);
        return nodeCount;
    }

    public final boolean removeGroup() {
        if (!(this.insertCount == 0)) {
            throw new IllegalArgumentException("Cannot remove group while inserting".toString());
        }
        int i = this.currentGroup;
        int i2 = this.currentSlot;
        int skipGroup = skipGroup();
        boolean removeGroups = removeGroups(i, this.currentGroup - i);
        removeSlots(i2, this.currentSlot - i2, i - 1);
        this.currentGroup = i;
        this.currentSlot = i2;
        this.nodeCount -= skipGroup;
        return removeGroups;
    }

    public final Iterator<Object> groupSlots() {
        int dataIndex = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int[] iArr = this.groups;
        int i = this.currentGroup;
        return new SlotWriter$groupSlots$1(dataIndex, dataIndex(iArr, groupIndexToAddress(i + groupSize(i))), this);
    }

    public final void moveGroup(int offset) {
        int groupSize;
        int groupSize2;
        boolean z = true;
        if (!(this.insertCount == 0)) {
            throw new IllegalArgumentException("Cannot move a group while inserting".toString());
        }
        if (!(offset >= 0)) {
            throw new IllegalArgumentException("Parameter offset is out of bounds".toString());
        }
        if (offset == 0) {
            return;
        }
        int i = this.currentGroup;
        int i2 = this.parent;
        int i3 = this.currentGroupEnd;
        int i4 = i;
        for (int i5 = offset; i5 > 0; i5--) {
            groupSize2 = SlotTableKt.groupSize(this.groups, groupIndexToAddress(i4));
            i4 += groupSize2;
            if (!(i4 <= i3)) {
                throw new IllegalArgumentException("Parameter offset is out of bounds".toString());
            }
        }
        groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress(i4));
        int i6 = this.currentSlot;
        int dataIndex = dataIndex(this.groups, groupIndexToAddress(i4));
        int i7 = i4 + groupSize;
        int dataIndex2 = dataIndex(this.groups, groupIndexToAddress(i7));
        int i8 = dataIndex2 - dataIndex;
        insertSlots(i8, Math.max(this.currentGroup - 1, 0));
        insertGroups(groupSize);
        int[] iArr = this.groups;
        int groupIndexToAddress = groupIndexToAddress(i7) * 5;
        ArraysKt.copyInto(iArr, iArr, groupIndexToAddress(i) * 5, groupIndexToAddress, (groupSize * 5) + groupIndexToAddress);
        if (i8 > 0) {
            Object[] objArr = this.slots;
            ArraysKt.copyInto(objArr, objArr, i6, dataIndexToDataAddress(dataIndex + i8), dataIndexToDataAddress(dataIndex2 + i8));
        }
        int i9 = dataIndex + i8;
        int i10 = i9 - i6;
        int i11 = this.slotsGapStart;
        int i12 = this.slotsGapLen;
        int length = this.slots.length;
        int i13 = this.slotsGapOwner;
        int i14 = i + groupSize;
        if (i < i14) {
            int i15 = i;
            while (true) {
                boolean z2 = z;
                int i16 = i15 + 1;
                int groupIndexToAddress2 = groupIndexToAddress(i15);
                int i17 = i10;
                int dataIndex3 = dataIndex(iArr, groupIndexToAddress2) - i17;
                int i18 = i11;
                if (i13 < groupIndexToAddress2) {
                    i11 = 0;
                }
                updateDataIndex(iArr, groupIndexToAddress2, dataIndexToDataAnchor(dataIndex3, i11, i12, length));
                if (i16 >= i14) {
                    break;
                }
                i10 = i17;
                i15 = i16;
                z = z2;
                i11 = i18;
            }
        }
        moveAnchors(i7, i, groupSize);
        if (!removeGroups(i7, groupSize)) {
            fixParentAnchorsFor(i2, this.currentGroupEnd, i);
            if (i8 > 0) {
                removeSlots(i9, i8, i7 - 1);
                return;
            }
            return;
        }
        ComposerKt.composeRuntimeError("Unexpectedly removed anchors".toString());
        throw new KotlinNothingValueException();
    }

    public final List<Anchor> moveFrom(SlotTable table, int index) {
        int i;
        int locationOf;
        int locationOf2;
        ArrayList emptyList;
        boolean isNode;
        int locationOf3;
        int i2;
        int i3;
        int parentAnchor;
        Intrinsics.checkNotNullParameter(table, "table");
        if (!(this.insertCount > 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (index == 0 && this.currentGroup == 0 && this.table.getGroupsSize() == 0) {
            int[] iArr = this.groups;
            Object[] objArr = this.slots;
            ArrayList<Anchor> arrayList = this.anchors;
            int[] groups = table.getGroups();
            int groupsSize = table.getGroupsSize();
            Object[] slots = table.getSlots();
            int slotsSize = table.getSlotsSize();
            this.groups = groups;
            this.slots = slots;
            this.anchors = table.getAnchors$runtime_release();
            this.groupGapStart = groupsSize;
            this.groupGapLen = (groups.length / 5) - groupsSize;
            this.slotsGapStart = slotsSize;
            this.slotsGapLen = slots.length - slotsSize;
            this.slotsGapOwner = groupsSize;
            table.setTo$runtime_release(iArr, 0, objArr, 0, arrayList);
            return this.anchors;
        }
        SlotWriter openWriter = table.openWriter();
        try {
            int groupSize = openWriter.groupSize(index);
            int i4 = index + groupSize;
            int dataIndex = openWriter.dataIndex(index);
            int dataIndex2 = openWriter.dataIndex(i4);
            int i5 = dataIndex2 - dataIndex;
            insertGroups(groupSize);
            insertSlots(i5, getCurrentGroup());
            int[] iArr2 = this.groups;
            int currentGroup = getCurrentGroup();
            ArraysKt.copyInto(openWriter.groups, iArr2, currentGroup * 5, index * 5, i4 * 5);
            Object[] objArr2 = this.slots;
            int i6 = this.currentSlot;
            ArraysKt.copyInto(openWriter.slots, objArr2, i6, dataIndex, dataIndex2);
            SlotTableKt.updateParentAnchor(iArr2, currentGroup, getParent());
            int i7 = currentGroup - index;
            int i8 = groupSize + currentGroup;
            int dataIndex3 = i6 - dataIndex(iArr2, currentGroup);
            int i9 = this.slotsGapOwner;
            int i10 = this.slotsGapLen;
            int length = objArr2.length;
            if (currentGroup < i8) {
                int i11 = currentGroup;
                while (true) {
                    int i12 = i11 + 1;
                    if (i11 != currentGroup) {
                        parentAnchor = SlotTableKt.parentAnchor(iArr2, i11);
                        i = i7;
                        SlotTableKt.updateParentAnchor(iArr2, i11, parentAnchor + i);
                    } else {
                        i = i7;
                    }
                    int dataIndex4 = dataIndex(iArr2, i11) + dataIndex3;
                    if (i9 < i11) {
                        i2 = dataIndex3;
                        i3 = 0;
                    } else {
                        i2 = dataIndex3;
                        i3 = this.slotsGapStart;
                    }
                    SlotTableKt.updateDataAnchor(iArr2, i11, dataIndexToDataAnchor(dataIndex4, i3, i10, length));
                    if (i11 == i9) {
                        i9++;
                    }
                    if (i12 >= i8) {
                        break;
                    }
                    i11 = i12;
                    dataIndex3 = i2;
                    i7 = i;
                }
            } else {
                i = i7;
            }
            this.slotsGapOwner = i9;
            locationOf = SlotTableKt.locationOf(table.getAnchors$runtime_release(), index, table.getGroupsSize());
            locationOf2 = SlotTableKt.locationOf(table.getAnchors$runtime_release(), i4, table.getGroupsSize());
            if (locationOf < locationOf2) {
                ArrayList<Anchor> anchors$runtime_release = table.getAnchors$runtime_release();
                ArrayList arrayList2 = new ArrayList(locationOf2 - locationOf);
                if (locationOf < locationOf2) {
                    int i13 = locationOf;
                    while (true) {
                        int i14 = i13 + 1;
                        Anchor anchor = anchors$runtime_release.get(i13);
                        Intrinsics.checkNotNullExpressionValue(anchor, "sourceAnchors[anchorIndex]");
                        Anchor anchor2 = anchor;
                        anchor2.setLocation$runtime_release(anchor2.getLocation() + i);
                        arrayList2.add(anchor2);
                        if (i14 >= locationOf2) {
                            break;
                        }
                        i13 = i14;
                    }
                }
                locationOf3 = SlotTableKt.locationOf(this.anchors, getCurrentGroup(), getSize$runtime_release());
                getTable().getAnchors$runtime_release().addAll(locationOf3, arrayList2);
                anchors$runtime_release.subList(locationOf, locationOf2).clear();
                emptyList = arrayList2;
            } else {
                emptyList = CollectionsKt.emptyList();
            }
            int parent = openWriter.parent(index);
            if (parent >= 0) {
                openWriter.startGroup();
                openWriter.advanceBy(parent - openWriter.getCurrentGroup());
                openWriter.startGroup();
            }
            openWriter.advanceBy(index - openWriter.getCurrentGroup());
            boolean removeGroup = openWriter.removeGroup();
            if (parent >= 0) {
                openWriter.skipToGroupEnd();
                openWriter.endGroup();
                openWriter.skipToGroupEnd();
                openWriter.endGroup();
            }
            if (!removeGroup) {
                int i15 = this.nodeCount;
                isNode = SlotTableKt.isNode(iArr2, currentGroup);
                this.nodeCount = i15 + (isNode ? 1 : SlotTableKt.nodeCount(iArr2, currentGroup));
                this.currentGroup = i8;
                this.currentSlot = i6 + i5;
                return emptyList;
            }
            ComposerKt.composeRuntimeError("Unexpectedly removed anchors".toString());
            throw new KotlinNothingValueException();
        } finally {
            openWriter.close();
        }
    }

    public static /* synthetic */ Anchor anchor$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.currentGroup;
        }
        return slotWriter.anchor(i);
    }

    public final Anchor anchor(int index) {
        ArrayList<Anchor> arrayList = this.anchors;
        int search = SlotTableKt.search(arrayList, index, getSize$runtime_release());
        if (search < 0) {
            if (index > this.groupGapStart) {
                index = -(getSize$runtime_release() - index);
            }
            Anchor anchor = new Anchor(index);
            arrayList.add(-(search + 1), anchor);
            return anchor;
        }
        Anchor anchor2 = arrayList.get(search);
        Intrinsics.checkNotNullExpressionValue(anchor2, "get(location)");
        return anchor2;
    }

    public final int anchorIndex(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        int location = anchor.getLocation();
        return location < 0 ? getSize$runtime_release() + location : location;
    }

    public String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime_release() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    private final void saveCurrentGroupEnd() {
        this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
    }

    private final int restoreCurrentGroupEnd() {
        int capacity = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.currentGroupEnd = capacity;
        return capacity;
    }

    private final void fixParentAnchorsFor(int parent, int endGroup, int firstChild) {
        int groupSize;
        int parentIndexToAnchor = parentIndexToAnchor(parent, this.groupGapStart);
        while (firstChild < endGroup) {
            SlotTableKt.updateParentAnchor(this.groups, groupIndexToAddress(firstChild), parentIndexToAnchor);
            groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress(firstChild));
            int i = groupSize + firstChild;
            fixParentAnchorsFor(firstChild, i, firstChild + 1);
            firstChild = i;
        }
    }

    private final void moveGroupGapTo(int index) {
        int parentAnchor;
        int i = this.groupGapLen;
        int i2 = this.groupGapStart;
        if (i2 != index) {
            if (!this.anchors.isEmpty()) {
                updateAnchors(i2, index);
            }
            if (i > 0) {
                int[] iArr = this.groups;
                int i3 = index * 5;
                int i4 = i * 5;
                int i5 = i2 * 5;
                if (index < i2) {
                    ArraysKt.copyInto(iArr, iArr, i4 + i3, i3, i5);
                } else {
                    ArraysKt.copyInto(iArr, iArr, i5, i5 + i4, i3 + i4);
                }
            }
            if (index < i2) {
                i2 = index + i;
            }
            int capacity = getCapacity();
            ComposerKt.runtimeCheck(i2 < capacity);
            while (i2 < capacity) {
                parentAnchor = SlotTableKt.parentAnchor(this.groups, i2);
                int parentIndexToAnchor = parentIndexToAnchor(parentAnchorToIndex(parentAnchor), index);
                if (parentIndexToAnchor != parentAnchor) {
                    SlotTableKt.updateParentAnchor(this.groups, i2, parentIndexToAnchor);
                }
                i2++;
                if (i2 == index) {
                    i2 += i;
                }
            }
        }
        this.groupGapStart = index;
    }

    private final void moveSlotGapTo(int index, int group) {
        int dataAnchor;
        int dataAnchor2;
        int i = this.slotsGapLen;
        int i2 = this.slotsGapStart;
        int i3 = this.slotsGapOwner;
        if (i2 != index) {
            Object[] objArr = this.slots;
            if (index < i2) {
                ArraysKt.copyInto(objArr, objArr, index + i, index, i2);
            } else {
                ArraysKt.copyInto(objArr, objArr, i2, i2 + i, index + i);
            }
            ArraysKt.fill(objArr, (Object) null, index, index + i);
        }
        int min = Math.min(group + 1, getSize$runtime_release());
        if (i3 != min) {
            int length = this.slots.length - i;
            if (min < i3) {
                int groupIndexToAddress = groupIndexToAddress(min);
                int groupIndexToAddress2 = groupIndexToAddress(i3);
                int i4 = this.groupGapStart;
                while (groupIndexToAddress < groupIndexToAddress2) {
                    dataAnchor2 = SlotTableKt.dataAnchor(this.groups, groupIndexToAddress);
                    if (dataAnchor2 >= 0) {
                        SlotTableKt.updateDataAnchor(this.groups, groupIndexToAddress, -((length - dataAnchor2) + 1));
                        groupIndexToAddress++;
                        if (groupIndexToAddress == i4) {
                            groupIndexToAddress += this.groupGapLen;
                        }
                    } else {
                        ComposerKt.composeRuntimeError("Unexpected anchor value, expected a positive anchor".toString());
                        throw new KotlinNothingValueException();
                    }
                }
            } else {
                int groupIndexToAddress3 = groupIndexToAddress(i3);
                int groupIndexToAddress4 = groupIndexToAddress(min);
                while (groupIndexToAddress3 < groupIndexToAddress4) {
                    dataAnchor = SlotTableKt.dataAnchor(this.groups, groupIndexToAddress3);
                    if (dataAnchor < 0) {
                        SlotTableKt.updateDataAnchor(this.groups, groupIndexToAddress3, dataAnchor + length + 1);
                        groupIndexToAddress3++;
                        if (groupIndexToAddress3 == this.groupGapStart) {
                            groupIndexToAddress3 += this.groupGapLen;
                        }
                    } else {
                        ComposerKt.composeRuntimeError("Unexpected anchor value, expected a negative anchor".toString());
                        throw new KotlinNothingValueException();
                    }
                }
            }
            this.slotsGapOwner = min;
        }
        this.slotsGapStart = index;
    }

    private final void insertGroups(int size) {
        if (size > 0) {
            int i = this.currentGroup;
            moveGroupGapTo(i);
            int i2 = this.groupGapStart;
            int i3 = this.groupGapLen;
            int[] iArr = this.groups;
            int length = iArr.length / 5;
            int i4 = length - i3;
            if (i3 < size) {
                int max = Math.max(Math.max(length * 2, i4 + size), 32);
                int[] iArr2 = new int[max * 5];
                int i5 = max - i4;
                ArraysKt.copyInto(iArr, iArr2, 0, 0, i2 * 5);
                ArraysKt.copyInto(iArr, iArr2, (i2 + i5) * 5, (i3 + i2) * 5, length * 5);
                this.groups = iArr2;
                i3 = i5;
            }
            int i6 = this.currentGroupEnd;
            if (i6 >= i2) {
                this.currentGroupEnd = i6 + size;
            }
            int i7 = i2 + size;
            this.groupGapStart = i7;
            this.groupGapLen = i3 - size;
            int dataIndexToDataAnchor = dataIndexToDataAnchor(i4 > 0 ? dataIndex(i + size) : 0, this.slotsGapOwner >= i2 ? this.slotsGapStart : 0, this.slotsGapLen, this.slots.length);
            if (i2 < i7) {
                int i8 = i2;
                while (true) {
                    int i9 = i8 + 1;
                    SlotTableKt.updateDataAnchor(this.groups, i8, dataIndexToDataAnchor);
                    if (i9 >= i7) {
                        break;
                    } else {
                        i8 = i9;
                    }
                }
            }
            int i10 = this.slotsGapOwner;
            if (i10 >= i2) {
                this.slotsGapOwner = i10 + size;
            }
        }
    }

    private final void insertSlots(int size, int group) {
        if (size > 0) {
            moveSlotGapTo(this.currentSlot, group);
            int i = this.slotsGapStart;
            int i2 = this.slotsGapLen;
            if (i2 < size) {
                Object[] objArr = this.slots;
                int length = objArr.length;
                int i3 = length - i2;
                int max = Math.max(Math.max(length * 2, i3 + size), 32);
                Object[] objArr2 = new Object[max];
                for (int i4 = 0; i4 < max; i4++) {
                    objArr2[i4] = null;
                }
                int i5 = max - i3;
                ArraysKt.copyInto(objArr, objArr2, 0, 0, i);
                ArraysKt.copyInto(objArr, objArr2, i + i5, i2 + i, length);
                this.slots = objArr2;
                i2 = i5;
            }
            int i6 = this.currentSlotEnd;
            if (i6 >= i) {
                this.currentSlotEnd = i6 + size;
            }
            this.slotsGapStart = i + size;
            this.slotsGapLen = i2 - size;
        }
    }

    private final boolean removeGroups(int start, int len) {
        if (len > 0) {
            ArrayList<Anchor> arrayList = this.anchors;
            moveGroupGapTo(start);
            r0 = arrayList.isEmpty() ? false : removeAnchors(start, len);
            this.groupGapStart = start;
            this.groupGapLen += len;
            int i = this.slotsGapOwner;
            if (i > start) {
                this.slotsGapOwner = i - len;
            }
            int i2 = this.currentGroupEnd;
            if (i2 >= start) {
                this.currentGroupEnd = i2 - len;
            }
        }
        return r0;
    }

    private final void removeSlots(int start, int len, int group) {
        if (len > 0) {
            int i = this.slotsGapLen;
            int i2 = start + len;
            moveSlotGapTo(i2, group);
            this.slotsGapStart = start;
            this.slotsGapLen = i + len;
            ArraysKt.fill(this.slots, (Object) null, start, i2);
            int i3 = this.currentSlotEnd;
            if (i3 >= start) {
                this.currentSlotEnd = i3 - len;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void updateNodeOfGroup(int r4, java.lang.Object r5) {
        /*
            r3 = this;
            int r0 = r3.groupIndexToAddress(r4)
            int[] r1 = r3.groups
            int r2 = r1.length
            if (r0 >= r2) goto L11
            boolean r1 = androidx.compose.runtime.SlotTableKt.access$isNode(r1, r0)
            if (r1 == 0) goto L11
            r1 = 1
            goto L12
        L11:
            r1 = 0
        L12:
            if (r1 == 0) goto L23
            java.lang.Object[] r4 = r3.slots
            int[] r1 = r3.groups
            int r0 = r3.nodeIndex(r1, r0)
            int r0 = r3.dataIndexToDataAddress(r0)
            r4[r0] = r5
            return
        L23:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "Updating the node of a group at "
            r5.<init>(r0)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r5 = " that was not created with as a node group"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = r4.toString()
            androidx.compose.runtime.ComposerKt.composeRuntimeError(r4)
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SlotWriter.updateNodeOfGroup(int, java.lang.Object):void");
    }

    private final void updateAnchors(int previousGapStart, int newGapStart) {
        int locationOf;
        int locationOf2;
        int i;
        int capacity = getCapacity() - this.groupGapLen;
        if (previousGapStart < newGapStart) {
            for (locationOf2 = SlotTableKt.locationOf(this.anchors, previousGapStart, capacity); locationOf2 < this.anchors.size(); locationOf2++) {
                Anchor anchor = this.anchors.get(locationOf2);
                Intrinsics.checkNotNullExpressionValue(anchor, "anchors[index]");
                Anchor anchor2 = anchor;
                int location = anchor2.getLocation();
                if (location >= 0 || (i = location + capacity) >= newGapStart) {
                    return;
                }
                anchor2.setLocation$runtime_release(i);
            }
            return;
        }
        for (locationOf = SlotTableKt.locationOf(this.anchors, newGapStart, capacity); locationOf < this.anchors.size(); locationOf++) {
            Anchor anchor3 = this.anchors.get(locationOf);
            Intrinsics.checkNotNullExpressionValue(anchor3, "anchors[index]");
            Anchor anchor4 = anchor3;
            int location2 = anchor4.getLocation();
            if (location2 < 0) {
                return;
            }
            anchor4.setLocation$runtime_release(-(capacity - location2));
        }
    }

    private final boolean removeAnchors(int gapStart, int size) {
        int locationOf;
        int i = size + gapStart;
        locationOf = SlotTableKt.locationOf(this.anchors, i, getCapacity() - this.groupGapLen);
        if (locationOf >= this.anchors.size()) {
            locationOf--;
        }
        int i2 = locationOf + 1;
        int i3 = 0;
        while (locationOf >= 0) {
            Anchor anchor = this.anchors.get(locationOf);
            Intrinsics.checkNotNullExpressionValue(anchor, "anchors[index]");
            Anchor anchor2 = anchor;
            int anchorIndex = anchorIndex(anchor2);
            if (anchorIndex < gapStart) {
                break;
            }
            if (anchorIndex < i) {
                anchor2.setLocation$runtime_release(Integer.MIN_VALUE);
                if (i3 == 0) {
                    i3 = locationOf + 1;
                }
                i2 = locationOf;
            }
            locationOf--;
        }
        boolean z = i2 < i3;
        if (z) {
            this.anchors.subList(i2, i3).clear();
        }
        return z;
    }

    private final void moveAnchors(int originalLocation, int newLocation, int size) {
        int locationOf;
        int locationOf2;
        int i = size + originalLocation;
        int size$runtime_release = getSize$runtime_release();
        locationOf = SlotTableKt.locationOf(this.anchors, originalLocation, size$runtime_release);
        ArrayList arrayList = new ArrayList();
        if (locationOf >= 0) {
            while (locationOf < this.anchors.size()) {
                Anchor anchor = this.anchors.get(locationOf);
                Intrinsics.checkNotNullExpressionValue(anchor, "anchors[index]");
                Anchor anchor2 = anchor;
                int anchorIndex = anchorIndex(anchor2);
                if (anchorIndex < originalLocation || anchorIndex >= i) {
                    break;
                }
                arrayList.add(anchor2);
                this.anchors.remove(locationOf);
            }
        }
        int i2 = newLocation - originalLocation;
        int size2 = arrayList.size() - 1;
        if (size2 < 0) {
            return;
        }
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            Anchor anchor3 = (Anchor) arrayList.get(i3);
            int anchorIndex2 = anchorIndex(anchor3) + i2;
            if (anchorIndex2 >= this.groupGapStart) {
                anchor3.setLocation$runtime_release(-(size$runtime_release - anchorIndex2));
            } else {
                anchor3.setLocation$runtime_release(anchorIndex2);
            }
            locationOf2 = SlotTableKt.locationOf(this.anchors, anchorIndex2, size$runtime_release);
            this.anchors.add(locationOf2, anchor3);
            if (i4 > size2) {
                return;
            } else {
                i3 = i4;
            }
        }
    }

    public final String groupsAsString() {
        StringBuilder sb = new StringBuilder();
        int size$runtime_release = getSize$runtime_release();
        if (size$runtime_release > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                groupAsString(sb, i);
                sb.append('\n');
                if (i2 >= size$runtime_release) {
                    break;
                }
                i = i2;
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private final void groupAsString(StringBuilder sb, int i) {
        int groupSize;
        int parentAnchor;
        int key;
        int nodeCount;
        int dataAnchor;
        int parentAnchor2;
        int groupIndexToAddress = groupIndexToAddress(i);
        sb.append("Group(");
        if (i < 10) {
            sb.append(' ');
        }
        if (i < 100) {
            sb.append(' ');
        }
        if (i < 1000) {
            sb.append(' ');
        }
        sb.append(i);
        sb.append('#');
        groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress);
        sb.append(groupSize);
        sb.append('^');
        parentAnchor = SlotTableKt.parentAnchor(this.groups, groupIndexToAddress);
        sb.append(parentAnchorToIndex(parentAnchor));
        sb.append(": key=");
        key = SlotTableKt.key(this.groups, groupIndexToAddress);
        sb.append(key);
        sb.append(", nodes=");
        nodeCount = SlotTableKt.nodeCount(this.groups, groupIndexToAddress);
        sb.append(nodeCount);
        sb.append(", dataAnchor=");
        dataAnchor = SlotTableKt.dataAnchor(this.groups, groupIndexToAddress);
        sb.append(dataAnchor);
        sb.append(", parentAnchor=");
        parentAnchor2 = SlotTableKt.parentAnchor(this.groups, groupIndexToAddress);
        sb.append(parentAnchor2);
        sb.append(")");
    }

    public final void verifyDataAnchors$runtime_release() {
        int dataAnchor;
        int i = this.slotsGapOwner;
        int length = this.slots.length - this.slotsGapLen;
        int size$runtime_release = getSize$runtime_release();
        if (size$runtime_release <= 0) {
            return;
        }
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (true) {
            int i4 = i2 + 1;
            int groupIndexToAddress = groupIndexToAddress(i2);
            dataAnchor = SlotTableKt.dataAnchor(this.groups, groupIndexToAddress);
            int dataIndex = dataIndex(this.groups, groupIndexToAddress);
            if (!(dataIndex >= i3)) {
                throw new IllegalStateException(("Data index out of order at " + i2 + ", previous = " + i3 + ", current = " + dataIndex).toString());
            }
            if (!(dataIndex <= length)) {
                throw new IllegalStateException(("Data index, " + dataIndex + ", out of bound at " + i2).toString());
            }
            if (dataAnchor < 0 && !z) {
                if (!(i == i2)) {
                    throw new IllegalStateException(("Expected the slot gap owner to be " + i + " found gap at " + i2).toString());
                }
                z = true;
            }
            if (i4 >= size$runtime_release) {
                return;
            }
            i2 = i4;
            i3 = dataIndex;
        }
    }

    public final void verifyParentAnchors$runtime_release() {
        int parentAnchor;
        int parentAnchor2;
        int i = this.groupGapStart;
        int i2 = this.groupGapLen;
        int capacity = getCapacity();
        if (i > 0) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                parentAnchor2 = SlotTableKt.parentAnchor(this.groups, i3);
                if (!(parentAnchor2 > -2)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Expected a start relative anchor at ", Integer.valueOf(i3)).toString());
                }
                if (i4 >= i) {
                    break;
                } else {
                    i3 = i4;
                }
            }
        }
        int i5 = i2 + i;
        if (i5 >= capacity) {
            return;
        }
        while (true) {
            int i6 = i5 + 1;
            parentAnchor = SlotTableKt.parentAnchor(this.groups, i5);
            if (parentAnchorToIndex(parentAnchor) < i) {
                if (!(parentAnchor > -2)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Expected a start relative anchor at ", Integer.valueOf(i5)).toString());
                }
            } else {
                if (!(parentAnchor <= -2)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Expected an end relative anchor at ", Integer.valueOf(i5)).toString());
                }
            }
            if (i6 >= capacity) {
                return;
            } else {
                i5 = i6;
            }
        }
    }

    public final int getSize$runtime_release() {
        return getCapacity() - this.groupGapLen;
    }

    private final int getCapacity() {
        return this.groups.length / 5;
    }

    private final int groupIndexToAddress(int index) {
        return index < this.groupGapStart ? index : index + this.groupGapLen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAddress(int dataIndex) {
        return dataIndex < this.slotsGapStart ? dataIndex : dataIndex + this.slotsGapLen;
    }

    private final int parent(int[] iArr, int i) {
        int parentAnchor;
        parentAnchor = SlotTableKt.parentAnchor(iArr, groupIndexToAddress(i));
        return parentAnchorToIndex(parentAnchor);
    }

    private final int dataIndex(int index) {
        return dataIndex(this.groups, groupIndexToAddress(index));
    }

    private final int dataIndex(int[] iArr, int i) {
        int dataAnchor;
        if (i >= getCapacity()) {
            return this.slots.length - this.slotsGapLen;
        }
        dataAnchor = SlotTableKt.dataAnchor(iArr, i);
        return dataAnchorToDataIndex(dataAnchor, this.slotsGapLen, this.slots.length);
    }

    private final int slotIndex(int[] iArr, int i) {
        int slotAnchor;
        if (i >= getCapacity()) {
            return this.slots.length - this.slotsGapLen;
        }
        slotAnchor = SlotTableKt.slotAnchor(iArr, i);
        return dataAnchorToDataIndex(slotAnchor, this.slotsGapLen, this.slots.length);
    }

    private final void updateDataIndex(int[] iArr, int i, int i2) {
        SlotTableKt.updateDataAnchor(iArr, i, dataIndexToDataAnchor(i2, this.slotsGapStart, this.slotsGapLen, this.slots.length));
    }

    private final int nodeIndex(int[] iArr, int i) {
        return dataIndex(iArr, i);
    }

    private final int auxIndex(int[] iArr, int i) {
        int groupInfo;
        int countOneBits;
        int dataIndex = dataIndex(iArr, i);
        groupInfo = SlotTableKt.groupInfo(iArr, i);
        countOneBits = SlotTableKt.countOneBits(groupInfo >> 29);
        return dataIndex + countOneBits;
    }

    private final List<Integer> dataIndexes(int[] iArr) {
        int i = 0;
        List dataAnchors$default = SlotTableKt.dataAnchors$default(this.groups, 0, 1, null);
        List plus = CollectionsKt.plus((Collection) CollectionsKt.slice(dataAnchors$default, RangesKt.until(0, this.groupGapStart)), (Iterable) CollectionsKt.slice(dataAnchors$default, RangesKt.until(this.groupGapStart + this.groupGapLen, iArr.length / 5)));
        ArrayList arrayList = new ArrayList(plus.size());
        int size = plus.size() - 1;
        if (size >= 0) {
            while (true) {
                int i2 = i + 1;
                arrayList.add(Integer.valueOf(dataAnchorToDataIndex(((Number) plus.get(i)).intValue(), this.slotsGapLen, this.slots.length)));
                if (i2 > size) {
                    break;
                }
                i = i2;
            }
        }
        return arrayList;
    }

    private final List<Integer> keys() {
        int i = 0;
        List keys$default = SlotTableKt.keys$default(this.groups, 0, 1, null);
        ArrayList arrayList = new ArrayList(keys$default.size());
        int size = keys$default.size() - 1;
        if (size >= 0) {
            while (true) {
                int i2 = i + 1;
                Object obj = keys$default.get(i);
                ((Number) obj).intValue();
                int i3 = this.groupGapStart;
                if (i < i3 || i >= i3 + this.groupGapLen) {
                    arrayList.add(obj);
                }
                if (i2 > size) {
                    break;
                }
                i = i2;
            }
        }
        return arrayList;
    }

    private final int parentIndexToAnchor(int index, int gapStart) {
        return index < gapStart ? index : -((getSize$runtime_release() - index) + 2);
    }

    private final int parentAnchorToIndex(int index) {
        return index > -2 ? index : (getSize$runtime_release() + index) - (-2);
    }
}
