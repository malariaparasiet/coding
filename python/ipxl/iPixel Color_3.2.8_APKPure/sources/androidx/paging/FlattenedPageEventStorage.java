package androidx.paging;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: CachedPageEventFlow.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00130\u0015J\u0016\u0010\u0016\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0002J\u0016\u0010\u0018\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0002J\u0016\u0010\u001a\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/paging/FlattenedPageEventStorage;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "mediatorStates", "Landroidx/paging/LoadStates;", "pages", "Lkotlin/collections/ArrayDeque;", "Landroidx/paging/TransformablePage;", "placeholdersAfter", "", "placeholdersBefore", "receivedFirstEvent", "", "sourceStates", "Landroidx/paging/MutableLoadStateCollection;", "add", "", NotificationCompat.CATEGORY_EVENT, "Landroidx/paging/PageEvent;", "getAsEvents", "", "handleInsert", "Landroidx/paging/PageEvent$Insert;", "handleLoadStateUpdate", "Landroidx/paging/PageEvent$LoadStateUpdate;", "handlePageDrop", "Landroidx/paging/PageEvent$Drop;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class FlattenedPageEventStorage<T> {
    private LoadStates mediatorStates;
    private int placeholdersAfter;
    private int placeholdersBefore;
    private boolean receivedFirstEvent;
    private final ArrayDeque<TransformablePage<T>> pages = new ArrayDeque<>();
    private final MutableLoadStateCollection sourceStates = new MutableLoadStateCollection();

    /* compiled from: CachedPageEventFlow.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.PREPEND.ordinal()] = 1;
            iArr[LoadType.APPEND.ordinal()] = 2;
            iArr[LoadType.REFRESH.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final void add(PageEvent<T> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.receivedFirstEvent = true;
        if (event instanceof PageEvent.Insert) {
            handleInsert((PageEvent.Insert) event);
        } else if (event instanceof PageEvent.Drop) {
            handlePageDrop((PageEvent.Drop) event);
        } else if (event instanceof PageEvent.LoadStateUpdate) {
            handleLoadStateUpdate((PageEvent.LoadStateUpdate) event);
        }
    }

    private final void handlePageDrop(PageEvent.Drop<T> event) {
        this.sourceStates.set(event.getLoadType(), LoadState.NotLoading.INSTANCE.getIncomplete$paging_common());
        int i = WhenMappings.$EnumSwitchMapping$0[event.getLoadType().ordinal()];
        int i2 = 0;
        if (i == 1) {
            this.placeholdersBefore = event.getPlaceholdersRemaining();
            int pageCount = event.getPageCount();
            while (i2 < pageCount) {
                this.pages.removeFirst();
                i2++;
            }
            return;
        }
        if (i == 2) {
            this.placeholdersAfter = event.getPlaceholdersRemaining();
            int pageCount2 = event.getPageCount();
            while (i2 < pageCount2) {
                this.pages.removeLast();
                i2++;
            }
            return;
        }
        throw new IllegalArgumentException("Page drop type must be prepend or append");
    }

    private final void handleInsert(PageEvent.Insert<T> event) {
        this.sourceStates.set(event.getSourceLoadStates());
        this.mediatorStates = event.getMediatorLoadStates();
        int i = WhenMappings.$EnumSwitchMapping$0[event.getLoadType().ordinal()];
        if (i == 1) {
            this.placeholdersBefore = event.getPlaceholdersBefore();
            Iterator<Integer> it = RangesKt.downTo(event.getPages().size() - 1, 0).iterator();
            while (it.hasNext()) {
                this.pages.addFirst(event.getPages().get(((IntIterator) it).nextInt()));
            }
            return;
        }
        if (i == 2) {
            this.placeholdersAfter = event.getPlaceholdersAfter();
            this.pages.addAll(event.getPages());
        } else {
            if (i != 3) {
                return;
            }
            this.pages.clear();
            this.placeholdersAfter = event.getPlaceholdersAfter();
            this.placeholdersBefore = event.getPlaceholdersBefore();
            this.pages.addAll(event.getPages());
        }
    }

    private final void handleLoadStateUpdate(PageEvent.LoadStateUpdate<T> event) {
        this.sourceStates.set(event.getSource());
        this.mediatorStates = event.getMediator();
    }

    public final List<PageEvent<T>> getAsEvents() {
        if (!this.receivedFirstEvent) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        LoadStates snapshot = this.sourceStates.snapshot();
        if (!this.pages.isEmpty()) {
            arrayList.add(PageEvent.Insert.INSTANCE.Refresh(CollectionsKt.toList(this.pages), this.placeholdersBefore, this.placeholdersAfter, snapshot, this.mediatorStates));
            return arrayList;
        }
        arrayList.add(new PageEvent.LoadStateUpdate(snapshot, this.mediatorStates));
        return arrayList;
    }
}
