package androidx.paging;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InvalidatingPagingSourceFactory.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00050\u0004B\u001f\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0004¢\u0006\u0002\u0010\u0007J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005H\u0096\u0002R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\t8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Landroidx/paging/InvalidatingPagingSourceFactory;", "Key", "", "Value", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "pagingSourceFactory", "(Lkotlin/jvm/functions/Function0;)V", "pagingSources", "Ljava/util/concurrent/CopyOnWriteArrayList;", "getPagingSources$paging_common$annotations", "()V", "getPagingSources$paging_common", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "invalidate", "", "invoke", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class InvalidatingPagingSourceFactory<Key, Value> implements Function0<PagingSource<Key, Value>> {
    private final Function0<PagingSource<Key, Value>> pagingSourceFactory;
    private final CopyOnWriteArrayList<PagingSource<Key, Value>> pagingSources;

    public static /* synthetic */ void getPagingSources$paging_common$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public InvalidatingPagingSourceFactory(Function0<? extends PagingSource<Key, Value>> pagingSourceFactory) {
        Intrinsics.checkNotNullParameter(pagingSourceFactory, "pagingSourceFactory");
        this.pagingSourceFactory = pagingSourceFactory;
        this.pagingSources = new CopyOnWriteArrayList<>();
    }

    public final CopyOnWriteArrayList<PagingSource<Key, Value>> getPagingSources$paging_common() {
        return this.pagingSources;
    }

    @Override // kotlin.jvm.functions.Function0
    public PagingSource<Key, Value> invoke() {
        PagingSource<Key, Value> invoke = this.pagingSourceFactory.invoke();
        getPagingSources$paging_common().add(invoke);
        return invoke;
    }

    public final void invalidate() {
        Iterator<PagingSource<Key, Value>> it = this.pagingSources.iterator();
        while (it.hasNext()) {
            PagingSource<Key, Value> next = it.next();
            if (!next.getInvalid()) {
                next.invalidate();
            }
        }
        CollectionsKt.removeAll((List) this.pagingSources, (Function1) new Function1<PagingSource<Key, Value>, Boolean>() { // from class: androidx.paging.InvalidatingPagingSourceFactory$invalidate$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PagingSource<Key, Value> pagingSource) {
                return Boolean.valueOf(pagingSource.getInvalid());
            }
        });
    }
}
