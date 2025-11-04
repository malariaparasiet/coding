package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;

/* compiled from: SnapshotStateList.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003:\u0001CB\u0005¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J\u001d\u0010\u0015\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u000b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0016J\u0016\u0010\u001c\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0016J)\u0010 \u001a\u00020\u00162\u001e\u0010!\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#0\"H\u0082\bJ\u0016\u0010$\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0018J\u0016\u0010%\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0016J\u0016\u0010&\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u000bH\u0096\u0002¢\u0006\u0002\u0010'J\u0015\u0010(\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020\u0016H\u0016J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000,H\u0096\u0002J\u0015\u0010-\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010)J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000/H\u0016J\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000/2\u0006\u0010\u001a\u001a\u00020\u000bH\u0016J.\u00100\u001a\u0002H1\"\u0004\b\u0001\u001012\u0018\u0010!\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0004\u0012\u0002H10\"H\u0082\b¢\u0006\u0002\u00102J\u0010\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u0006H\u0016J\u0015\u00105\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J\u0016\u00106\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0016J\u0015\u00107\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010'J\u0016\u00108\u001a\u00020\u00192\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000bJ\u0016\u0010;\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0016J\u001e\u0010<\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010=J\u001e\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000bH\u0016J)\u0010?\u001a\u00020\u00192\u001e\u0010!\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#0\"H\u0082\bJ3\u0010@\u001a\u0002H1\"\u0004\b\u0001\u001012\u001d\u0010!\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0004\u0012\u0002H10\"¢\u0006\u0002\bAH\u0082\b¢\u0006\u0002\u00102J3\u0010B\u001a\u0002H1\"\u0004\b\u0001\u001012\u001d\u0010!\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0004\u0012\u0002H10\"¢\u0006\u0002\bAH\u0082\b¢\u0006\u0002\u00102R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f8@X\u0080\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0004\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\r¨\u0006D"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateList;", ExifInterface.GPS_DIRECTION_TRUE, "", "Landroidx/compose/runtime/snapshots/StateObject;", "()V", "<set-?>", "Landroidx/compose/runtime/snapshots/StateRecord;", "firstStateRecord", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "modification", "", "getModification$runtime_release", "()I", "readable", "Landroidx/compose/runtime/snapshots/SnapshotStateList$StateListStateRecord;", "getReadable$runtime_release$annotations", "getReadable$runtime_release", "()Landroidx/compose/runtime/snapshots/SnapshotStateList$StateListStateRecord;", "size", "getSize", "add", "", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "elements", "", "clear", "conditionalUpdate", "block", "Lkotlin/Function1;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "contains", "containsAll", "get", "(I)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "mutate", "R", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "prependStateRecord", "value", "remove", "removeAll", "removeAt", "removeRange", "fromIndex", "toIndex", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "subList", "update", "withCurrent", "Lkotlin/ExtensionFunctionType;", "writable", "StateListStateRecord", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SnapshotStateList<T> implements List<T>, StateObject, KMutableList {
    private StateRecord firstStateRecord = new StateListStateRecord(ExtensionsKt.persistentListOf());

    public static /* synthetic */ void getReadable$runtime_release$annotations() {
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(this, array);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        return StateObject.DefaultImpls.mergeRecords(this, stateRecord, stateRecord2, stateRecord3);
    }

    @Override // java.util.List
    public final /* bridge */ T remove(int i) {
        return removeAt(i);
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord value) {
        Intrinsics.checkNotNullParameter(value, "value");
        value.setNext$runtime_release(getFirstStateRecord());
        this.firstStateRecord = (StateListStateRecord) value;
    }

    public final StateListStateRecord<T> getReadable$runtime_release() {
        return (StateListStateRecord) SnapshotKt.readable((StateListStateRecord) getFirstStateRecord(), this);
    }

    /* compiled from: SnapshotStateList.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0015\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016J\b\u0010\u0012\u001a\u00020\u0002H\u0016R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u001a\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateList$StateListStateRecord;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/snapshots/StateRecord;", "list", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;)V", "getList$runtime_release", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "setList$runtime_release", "modification", "", "getModification$runtime_release", "()I", "setModification$runtime_release", "(I)V", "assign", "", "value", "create", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class StateListStateRecord<T> extends StateRecord {
        private PersistentList<? extends T> list;
        private int modification;

        public final PersistentList<T> getList$runtime_release() {
            return this.list;
        }

        public final void setList$runtime_release(PersistentList<? extends T> persistentList) {
            Intrinsics.checkNotNullParameter(persistentList, "<set-?>");
            this.list = persistentList;
        }

        public StateListStateRecord(PersistentList<? extends T> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
        }

        /* renamed from: getModification$runtime_release, reason: from getter */
        public final int getModification() {
            return this.modification;
        }

        public final void setModification$runtime_release(int i) {
            this.modification = i;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord value) {
            Intrinsics.checkNotNullParameter(value, "value");
            StateListStateRecord stateListStateRecord = (StateListStateRecord) value;
            this.list = stateListStateRecord.list;
            this.modification = stateListStateRecord.modification;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new StateListStateRecord(this.list);
        }
    }

    public int getSize() {
        return getReadable$runtime_release().getList$runtime_release().size();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object element) {
        return getReadable$runtime_release().getList$runtime_release().contains(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return getReadable$runtime_release().getList$runtime_release().containsAll(elements);
    }

    @Override // java.util.List
    public T get(int index) {
        return (T) getReadable$runtime_release().getList$runtime_release().get(index);
    }

    @Override // java.util.List
    public int indexOf(Object element) {
        return getReadable$runtime_release().getList$runtime_release().indexOf(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return getReadable$runtime_release().getList$runtime_release().isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object element) {
        return getReadable$runtime_release().getList$runtime_release().lastIndexOf(element);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return new StateListIterator(this, 0);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int index) {
        return new StateListIterator(this, index);
    }

    @Override // java.util.List
    public List<T> subList(int fromIndex, int toIndex) {
        if (!(fromIndex >= 0 && fromIndex <= toIndex && toIndex <= size())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return new SubList(this, fromIndex, toIndex);
    }

    public T removeAt(int index) {
        Snapshot current;
        T t = get(index);
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList<T> removeAt = stateListStateRecord.getList$runtime_release().removeAt(index);
        if (removeAt == stateListStateRecord.getList$runtime_release()) {
            return t;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(removeAt);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return t;
    }

    @Override // java.util.List
    public T set(int index, T element) {
        Snapshot current;
        T t = get(index);
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList<T> persistentList = stateListStateRecord.getList$runtime_release().set(index, (int) element);
        if (persistentList == stateListStateRecord.getList$runtime_release()) {
            return t;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(persistentList);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return t;
    }

    private final <R> R writable(Function1<? super StateListStateRecord<T>, ? extends R> block) {
        Snapshot current;
        R invoke;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            invoke = block.invoke(SnapshotKt.writableRecord(stateListStateRecord, this, current));
        }
        SnapshotKt.notifyWrite(current, this);
        return invoke;
    }

    private final <R> R withCurrent(Function1<? super StateListStateRecord<T>, ? extends R> block) {
        return block.invoke(SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent()));
    }

    public final int getModification$runtime_release() {
        return ((StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent())).getModification();
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(T element) {
        Snapshot current;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList<T> add = stateListStateRecord.getList$runtime_release().add((PersistentList<T>) element);
        if (add == stateListStateRecord.getList$runtime_release()) {
            return false;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(add);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return true;
    }

    @Override // java.util.List
    public void add(int index, T element) {
        Snapshot current;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList<T> add = stateListStateRecord.getList$runtime_release().add(index, (int) element);
        if (add != stateListStateRecord.getList$runtime_release()) {
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                stateListStateRecord3.setList$runtime_release(add);
                stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
            }
            SnapshotKt.notifyWrite(current, this);
        }
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection<? extends T> elements) {
        Snapshot current;
        Intrinsics.checkNotNullParameter(elements, "elements");
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList.Builder<T> builder = stateListStateRecord.getList$runtime_release().builder();
        boolean addAll = builder.addAll(index, elements);
        PersistentList<T> build = builder.build();
        if (build == stateListStateRecord.getList$runtime_release()) {
            return addAll;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(build);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return addAll;
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> elements) {
        Snapshot current;
        Intrinsics.checkNotNullParameter(elements, "elements");
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList<T> addAll = stateListStateRecord.getList$runtime_release().addAll(elements);
        if (addAll == stateListStateRecord.getList$runtime_release()) {
            return false;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(addAll);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        Snapshot current;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            ((StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord, this, current)).setList$runtime_release(ExtensionsKt.persistentListOf());
            Unit unit = Unit.INSTANCE;
        }
        SnapshotKt.notifyWrite(current, this);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object element) {
        Snapshot current;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList<T> remove = stateListStateRecord.getList$runtime_release().remove((PersistentList<T>) element);
        if (remove == stateListStateRecord.getList$runtime_release()) {
            return false;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(remove);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        Snapshot current;
        Intrinsics.checkNotNullParameter(elements, "elements");
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList<T> removeAll = stateListStateRecord.getList$runtime_release().removeAll((Collection<? extends T>) elements);
        if (removeAll == stateListStateRecord.getList$runtime_release()) {
            return false;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(removeAll);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        Snapshot current;
        Intrinsics.checkNotNullParameter(elements, "elements");
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList.Builder<T> builder = stateListStateRecord.getList$runtime_release().builder();
        boolean retainAll = builder.retainAll(elements);
        PersistentList<T> build = builder.build();
        if (build == stateListStateRecord.getList$runtime_release()) {
            return retainAll;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(build);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return retainAll;
    }

    public final void removeRange(int fromIndex, int toIndex) {
        Snapshot current;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList.Builder<T> builder = stateListStateRecord.getList$runtime_release().builder();
        builder.subList(fromIndex, toIndex).clear();
        Unit unit = Unit.INSTANCE;
        PersistentList<T> build = builder.build();
        if (build != stateListStateRecord.getList$runtime_release()) {
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                stateListStateRecord3.setList$runtime_release(build);
                stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
            }
            SnapshotKt.notifyWrite(current, this);
        }
    }

    private final <R> R mutate(Function1<? super List<T>, ? extends R> block) {
        Snapshot current;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList.Builder<T> builder = stateListStateRecord.getList$runtime_release().builder();
        R invoke = block.invoke(builder);
        PersistentList<T> build = builder.build();
        if (build == stateListStateRecord.getList$runtime_release()) {
            return invoke;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(build);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return invoke;
    }

    private final void update(Function1<? super PersistentList<? extends T>, ? extends PersistentList<? extends T>> block) {
        Snapshot current;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList<? extends T> invoke = block.invoke(stateListStateRecord.getList$runtime_release());
        if (invoke != stateListStateRecord.getList$runtime_release()) {
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                stateListStateRecord3.setList$runtime_release(invoke);
                stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
            }
            SnapshotKt.notifyWrite(current, this);
        }
    }

    private final boolean conditionalUpdate(Function1<? super PersistentList<? extends T>, ? extends PersistentList<? extends T>> block) {
        Snapshot current;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord(), Snapshot.INSTANCE.getCurrent());
        PersistentList<? extends T> invoke = block.invoke(stateListStateRecord.getList$runtime_release());
        if (invoke == stateListStateRecord.getList$runtime_release()) {
            return false;
        }
        StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
            stateListStateRecord3.setList$runtime_release(invoke);
            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
        }
        SnapshotKt.notifyWrite(current, this);
        return true;
    }
}
