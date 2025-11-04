package androidx.compose.runtime;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010\u001c\n\u0002\u0010\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\u0011\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003H\u0096\u0002J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"androidx/compose/runtime/GroupIterator$next$1$data$1", "", "", "", "index", "", "getIndex", "()I", "setIndex", "(I)V", "hasNext", "", "iterator", "next", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class GroupIterator$next$1$data$1 implements Iterable<Object>, Iterator<Object>, KMappedMarker {
    final /* synthetic */ int $end;
    final /* synthetic */ int $start;
    private int index;
    final /* synthetic */ GroupIterator this$0;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    GroupIterator$next$1$data$1(int i, int i2, GroupIterator groupIterator) {
        this.$start = i;
        this.$end = i2;
        this.this$0 = groupIterator;
        this.index = i;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return this;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.$end;
    }

    @Override // java.util.Iterator
    public Object next() {
        int i = this.index;
        Object obj = (i < 0 || i >= this.this$0.getTable().getSlots().length) ? null : this.this$0.getTable().getSlots()[this.index];
        setIndex(getIndex() + 1);
        return obj;
    }
}
