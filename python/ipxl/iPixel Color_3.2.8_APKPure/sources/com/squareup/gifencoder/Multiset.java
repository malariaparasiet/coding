package com.squareup.gifencoder;

import java.util.Collection;
import java.util.Set;

/* loaded from: classes2.dex */
public interface Multiset<E> extends Collection<E> {
    void add(E e, int i);

    int count(Object obj);

    Set<E> getDistinctElements();

    int remove(Object obj, int i);
}
