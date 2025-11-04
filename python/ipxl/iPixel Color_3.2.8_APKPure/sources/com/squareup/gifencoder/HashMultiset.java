package com.squareup.gifencoder;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: classes2.dex */
public final class HashMultiset<E> extends AbstractCollection<E> implements Multiset<E> {
    private final Map<E, Count> elementCounts = new HashMap();
    private int size;

    public HashMultiset() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HashMultiset(Collection<E> collection) {
        addAll(collection);
    }

    @Override // com.squareup.gifencoder.Multiset
    public void add(E e, int i) {
        Count count = this.elementCounts.get(e);
        if (count != null) {
            count.value += i;
        } else {
            this.elementCounts.put(e, new Count(i));
        }
        this.size += i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(E e) {
        add(e, 1);
        return true;
    }

    @Override // com.squareup.gifencoder.Multiset
    public int remove(Object obj, int i) {
        Count count = this.elementCounts.get(obj);
        if (count == null) {
            return 0;
        }
        if (i < count.value) {
            count.value -= i;
            this.size -= i;
            return i;
        }
        this.elementCounts.remove(obj);
        this.size -= count.value;
        return count.value;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        return remove(obj, 1) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new HashMultisetIterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // com.squareup.gifencoder.Multiset
    public int count(Object obj) {
        Count count = this.elementCounts.get(obj);
        if (count != null) {
            return count.value;
        }
        return 0;
    }

    @Override // com.squareup.gifencoder.Multiset
    public Set<E> getDistinctElements() {
        return this.elementCounts.keySet();
    }

    private final class HashMultisetIterator implements Iterator<E> {
        int currentCount = 0;
        E currentElement;
        boolean currentElementRemoved;
        final Iterator<Map.Entry<E, Count>> distinctElementIterator;

        HashMultisetIterator() {
            this.distinctElementIterator = HashMultiset.this.elementCounts.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.currentCount > 0 || this.distinctElementIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("iterator has been exhausted");
            }
            if (this.currentCount == 0) {
                Map.Entry<E, Count> next = this.distinctElementIterator.next();
                this.currentElement = next.getKey();
                this.currentCount = next.getValue().value;
            }
            this.currentCount--;
            this.currentElementRemoved = false;
            return this.currentElement;
        }

        @Override // java.util.Iterator
        public void remove() {
            E e = this.currentElement;
            if (e == null) {
                throw new IllegalStateException("next() has not been called");
            }
            if (this.currentElementRemoved) {
                throw new IllegalStateException("remove() already called for current element");
            }
            HashMultiset.this.remove(e);
        }
    }

    private static final class Count {
        int value;

        Count(int i) {
            this.value = i;
        }
    }
}
