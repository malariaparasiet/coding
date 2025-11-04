package androidx.paging;

import androidx.autofill.HintConstants;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LegacyPageFetcher;
import androidx.paging.PagedList;
import androidx.paging.PagingSource;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: PagedStorage.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010!\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005:\u0001YB\u0007\b\u0016¢\u0006\u0002\u0010\u0006B)\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fB\u0015\b\u0012\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0002\u0010\u000eJ+\u00100\u001a\u0002012\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n2\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0000¢\u0006\u0002\b4J\u0018\u00105\u001a\u0004\u0018\u00018\u00002\u0006\u00106\u001a\u00020\bH\u0096\u0002¢\u0006\u0002\u00107J\u0015\u00108\u001a\u00028\u00002\u0006\u00109\u001a\u00020\bH\u0016¢\u0006\u0002\u00107J\u001a\u0010:\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u0000\u0018\u00010;2\u0006\u0010<\u001a\u00020=JD\u0010>\u001a\u0002012\u0006\u0010\u0007\u001a\u00020\b2\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u00102\u001a\u0002032\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007J:\u0010>\u001a\u0002012\u0006\u0010\u0007\u001a\u00020\b2\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010?\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u0010B\u001a\u00020\bH\u0002J\u0016\u0010C\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bJ\u0016\u0010D\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bJ+\u0010E\u001a\u0002012\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n2\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0000¢\u0006\u0002\bFJ\u001e\u0010G\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u0010H\u001a\u00020\bJ\f\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000J\b\u0010J\u001a\u00020KH\u0016J`\u0010L\u001a\u0002HM\"\u0004\b\u0001\u0010M2\u0006\u00109\u001a\u00020\b2B\b\u0004\u0010N\u001a<\u0012\u001d\u0012\u001b\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n¢\u0006\f\bP\u0012\b\bQ\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\b¢\u0006\f\bP\u0012\b\bQ\u0012\u0004\b\b(R\u0012\u0004\u0012\u0002HM0OH\u0082\b¢\u0006\u0002\u0010SJ-\u0010T\u001a\u00020\u00102\u0006\u0010U\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0002\bVJ-\u0010W\u001a\u00020\u00102\u0006\u0010U\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0002\bXR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00028\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00028\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\u001d\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0017R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0013R\u001e\u0010!\u001a\u0012\u0012\u000e\u0012\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u001e\u0010&\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0017R\u001e\u0010(\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0017R\u0016\u0010*\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u0013R\u0014\u0010,\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010\u0017R\u001e\u0010.\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0017¨\u0006Z"}, d2 = {"Landroidx/paging/PagedStorage;", ExifInterface.GPS_DIRECTION_TRUE, "", "Ljava/util/AbstractList;", "Landroidx/paging/LegacyPageFetcher$KeyProvider;", "Landroidx/paging/NullPaddedList;", "()V", "leadingNulls", "", "page", "Landroidx/paging/PagingSource$LoadResult$Page;", "trailingNulls", "(ILandroidx/paging/PagingSource$LoadResult$Page;I)V", "other", "(Landroidx/paging/PagedStorage;)V", "counted", "", "firstLoadedItem", "getFirstLoadedItem$paging_common", "()Ljava/lang/Object;", "value", "lastLoadAroundIndex", "getLastLoadAroundIndex", "()I", "setLastLoadAroundIndex", "(I)V", "lastLoadAroundLocalIndex", "lastLoadedItem", "getLastLoadedItem$paging_common", "middleOfLoadedRange", "getMiddleOfLoadedRange", "nextKey", "getNextKey", "pages", "", "<set-?>", "placeholdersAfter", "getPlaceholdersAfter", "placeholdersBefore", "getPlaceholdersBefore", "positionOffset", "getPositionOffset", "prevKey", "getPrevKey", "size", "getSize", "storageCount", "getStorageCount", "appendPage", "", "callback", "Landroidx/paging/PagedStorage$Callback;", "appendPage$paging_common", "get", "index", "(I)Ljava/lang/Object;", "getFromStorage", "localIndex", "getRefreshKeyInfo", "Landroidx/paging/PagingState;", "config", "Landroidx/paging/PagedList$Config;", "init", "needsTrim", "maxSize", "requiredRemaining", "localPageIndex", "needsTrimFromEnd", "needsTrimFromFront", "prependPage", "prependPage$paging_common", "shouldPreTrimNewPage", "countToBeAdded", "snapshot", "toString", "", "traversePages", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "onLastPage", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "pageInternalIndex", "(ILkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "trimFromEnd", "insertNulls", "trimFromEnd$paging_common", "trimFromFront", "trimFromFront$paging_common", "Callback", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class PagedStorage<T> extends AbstractList<T> implements LegacyPageFetcher.KeyProvider<Object>, NullPaddedList<T> {
    private boolean counted;
    private int lastLoadAroundLocalIndex;
    private final List<PagingSource.LoadResult.Page<?, T>> pages;
    private int placeholdersAfter;
    private int placeholdersBefore;
    private int positionOffset;
    private int storageCount;

    /* compiled from: PagedStorage.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&J \u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000f"}, d2 = {"Landroidx/paging/PagedStorage$Callback;", "", "onInitialized", "", "count", "", "onPageAppended", "endPosition", "changed", "added", "onPagePrepended", "leadingNulls", "onPagesRemoved", "startOfDrops", "onPagesSwappedToPlaceholder", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Callback {
        void onInitialized(int count);

        void onPageAppended(int endPosition, int changed, int added);

        void onPagePrepended(int leadingNulls, int changed, int added);

        void onPagesRemoved(int startOfDrops, int count);

        void onPagesSwappedToPlaceholder(int startOfDrops, int count);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ T remove(int i) {
        return (T) removeAt(i);
    }

    public /* bridge */ Object removeAt(int i) {
        return super.remove(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }

    public final T getFirstLoadedItem$paging_common() {
        return (T) CollectionsKt.first(((PagingSource.LoadResult.Page) CollectionsKt.first((List) this.pages)).getData());
    }

    public final T getLastLoadedItem$paging_common() {
        return (T) CollectionsKt.last(((PagingSource.LoadResult.Page) CollectionsKt.last((List) this.pages)).getData());
    }

    @Override // androidx.paging.NullPaddedList
    public int getPlaceholdersBefore() {
        return this.placeholdersBefore;
    }

    @Override // androidx.paging.NullPaddedList
    public int getPlaceholdersAfter() {
        return this.placeholdersAfter;
    }

    public final int getPositionOffset() {
        return this.positionOffset;
    }

    @Override // androidx.paging.NullPaddedList
    public int getStorageCount() {
        return this.storageCount;
    }

    public final int getLastLoadAroundIndex() {
        return getPlaceholdersBefore() + this.lastLoadAroundLocalIndex;
    }

    public final void setLastLoadAroundIndex(int i) {
        this.lastLoadAroundLocalIndex = RangesKt.coerceIn(i - getPlaceholdersBefore(), 0, getStorageCount() - 1);
    }

    public final int getMiddleOfLoadedRange() {
        return getPlaceholdersBefore() + (getStorageCount() / 2);
    }

    public PagedStorage() {
        this.pages = new ArrayList();
        this.counted = true;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PagedStorage(int i, PagingSource.LoadResult.Page<?, T> page, int i2) {
        this();
        Intrinsics.checkNotNullParameter(page, "page");
        init(i, page, i2, 0, true);
    }

    private PagedStorage(PagedStorage<T> pagedStorage) {
        ArrayList arrayList = new ArrayList();
        this.pages = arrayList;
        this.counted = true;
        arrayList.addAll(pagedStorage.pages);
        this.placeholdersBefore = pagedStorage.getPlaceholdersBefore();
        this.placeholdersAfter = pagedStorage.getPlaceholdersAfter();
        this.positionOffset = pagedStorage.positionOffset;
        this.counted = pagedStorage.counted;
        this.storageCount = pagedStorage.getStorageCount();
        this.lastLoadAroundLocalIndex = pagedStorage.lastLoadAroundLocalIndex;
    }

    public final PagedStorage<T> snapshot() {
        return new PagedStorage<>(this);
    }

    private final void init(int leadingNulls, PagingSource.LoadResult.Page<?, T> page, int trailingNulls, int positionOffset, boolean counted) {
        this.placeholdersBefore = leadingNulls;
        this.pages.clear();
        this.pages.add(page);
        this.placeholdersAfter = trailingNulls;
        this.positionOffset = positionOffset;
        this.storageCount = page.getData().size();
        this.counted = counted;
        this.lastLoadAroundLocalIndex = page.getData().size() / 2;
    }

    public static /* synthetic */ void init$default(PagedStorage pagedStorage, int i, PagingSource.LoadResult.Page page, int i2, int i3, Callback callback, boolean z, int i4, Object obj) {
        if ((i4 & 32) != 0) {
            z = true;
        }
        pagedStorage.init(i, page, i2, i3, callback, z);
    }

    public final void init(int leadingNulls, PagingSource.LoadResult.Page<?, T> page, int trailingNulls, int positionOffset, Callback callback, boolean counted) {
        Intrinsics.checkNotNullParameter(page, "page");
        Intrinsics.checkNotNullParameter(callback, "callback");
        init(leadingNulls, page, trailingNulls, positionOffset, counted);
        callback.onInitialized(size());
    }

    @Override // androidx.paging.LegacyPageFetcher.KeyProvider
    public Object getPrevKey() {
        if (!this.counted || getPlaceholdersBefore() + this.positionOffset > 0) {
            return ((PagingSource.LoadResult.Page) CollectionsKt.first((List) this.pages)).getPrevKey();
        }
        return null;
    }

    @Override // androidx.paging.LegacyPageFetcher.KeyProvider
    public Object getNextKey() {
        if (!this.counted || getPlaceholdersAfter() > 0) {
            return ((PagingSource.LoadResult.Page) CollectionsKt.last((List) this.pages)).getNextKey();
        }
        return null;
    }

    private final <V> V traversePages(int localIndex, Function2<? super PagingSource.LoadResult.Page<?, T>, ? super Integer, ? extends V> onLastPage) {
        int size = this.pages.size();
        int i = 0;
        while (i < size) {
            int size2 = ((PagingSource.LoadResult.Page) this.pages.get(i)).getData().size();
            if (size2 > localIndex) {
                break;
            }
            localIndex -= size2;
            i++;
        }
        return onLastPage.invoke((Object) this.pages.get(i), Integer.valueOf(localIndex));
    }

    public final PagingState<?, T> getRefreshKeyInfo(PagedList.Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        if (this.pages.isEmpty()) {
            return null;
        }
        return new PagingState<>(CollectionsKt.toList(this.pages), Integer.valueOf(getLastLoadAroundIndex()), new PagingConfig(config.pageSize, config.prefetchDistance, config.enablePlaceholders, config.initialLoadSizeHint, config.maxSize, 0, 32, null), getPlaceholdersBefore());
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int index) {
        int placeholdersBefore = index - getPlaceholdersBefore();
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        if (placeholdersBefore < 0 || placeholdersBefore >= getStorageCount()) {
            return null;
        }
        return getFromStorage(placeholdersBefore);
    }

    @Override // androidx.paging.NullPaddedList
    public int getSize() {
        return getPlaceholdersBefore() + getStorageCount() + getPlaceholdersAfter();
    }

    private final boolean needsTrim(int maxSize, int requiredRemaining, int localPageIndex) {
        return getStorageCount() > maxSize && this.pages.size() > 2 && getStorageCount() - this.pages.get(localPageIndex).getData().size() >= requiredRemaining;
    }

    public final boolean needsTrimFromFront(int maxSize, int requiredRemaining) {
        return needsTrim(maxSize, requiredRemaining, 0);
    }

    public final boolean needsTrimFromEnd(int maxSize, int requiredRemaining) {
        return needsTrim(maxSize, requiredRemaining, this.pages.size() - 1);
    }

    public final boolean shouldPreTrimNewPage(int maxSize, int requiredRemaining, int countToBeAdded) {
        return getStorageCount() + countToBeAdded > maxSize && this.pages.size() > 1 && getStorageCount() >= requiredRemaining;
    }

    public final boolean trimFromFront$paging_common(boolean insertNulls, int maxSize, int requiredRemaining, Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        int i = 0;
        while (needsTrimFromFront(maxSize, requiredRemaining)) {
            int size = this.pages.remove(0).getData().size();
            i += size;
            this.storageCount = getStorageCount() - size;
        }
        this.lastLoadAroundLocalIndex = RangesKt.coerceAtLeast(this.lastLoadAroundLocalIndex - i, 0);
        if (i > 0) {
            if (insertNulls) {
                int placeholdersBefore = getPlaceholdersBefore();
                this.placeholdersBefore = getPlaceholdersBefore() + i;
                callback.onPagesSwappedToPlaceholder(placeholdersBefore, i);
            } else {
                this.positionOffset += i;
                callback.onPagesRemoved(getPlaceholdersBefore(), i);
            }
        }
        return i > 0;
    }

    public final boolean trimFromEnd$paging_common(boolean insertNulls, int maxSize, int requiredRemaining, Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        int i = 0;
        while (needsTrimFromEnd(maxSize, requiredRemaining)) {
            List<PagingSource.LoadResult.Page<?, T>> list = this.pages;
            int size = list.remove(list.size() - 1).getData().size();
            i += size;
            this.storageCount = getStorageCount() - size;
        }
        this.lastLoadAroundLocalIndex = RangesKt.coerceAtMost(this.lastLoadAroundLocalIndex, getStorageCount() - 1);
        if (i > 0) {
            int placeholdersBefore = getPlaceholdersBefore() + getStorageCount();
            if (insertNulls) {
                this.placeholdersAfter = getPlaceholdersAfter() + i;
                callback.onPagesSwappedToPlaceholder(placeholdersBefore, i);
            } else {
                callback.onPagesRemoved(placeholdersBefore, i);
            }
        }
        return i > 0;
    }

    public static /* synthetic */ void prependPage$paging_common$default(PagedStorage pagedStorage, PagingSource.LoadResult.Page page, Callback callback, int i, Object obj) {
        if ((i & 2) != 0) {
            callback = null;
        }
        pagedStorage.prependPage$paging_common(page, callback);
    }

    public final void prependPage$paging_common(PagingSource.LoadResult.Page<?, T> page, Callback callback) {
        Intrinsics.checkNotNullParameter(page, "page");
        int size = page.getData().size();
        if (size == 0) {
            return;
        }
        this.pages.add(0, page);
        this.storageCount = getStorageCount() + size;
        int min = Math.min(getPlaceholdersBefore(), size);
        int i = size - min;
        if (min != 0) {
            this.placeholdersBefore = getPlaceholdersBefore() - min;
        }
        this.positionOffset -= i;
        if (callback == null) {
            return;
        }
        callback.onPagePrepended(getPlaceholdersBefore(), min, i);
    }

    public static /* synthetic */ void appendPage$paging_common$default(PagedStorage pagedStorage, PagingSource.LoadResult.Page page, Callback callback, int i, Object obj) {
        if ((i & 2) != 0) {
            callback = null;
        }
        pagedStorage.appendPage$paging_common(page, callback);
    }

    public final void appendPage$paging_common(PagingSource.LoadResult.Page<?, T> page, Callback callback) {
        Intrinsics.checkNotNullParameter(page, "page");
        int size = page.getData().size();
        if (size == 0) {
            return;
        }
        this.pages.add(page);
        this.storageCount = getStorageCount() + size;
        int min = Math.min(getPlaceholdersAfter(), size);
        int i = size - min;
        if (min != 0) {
            this.placeholdersAfter = getPlaceholdersAfter() - min;
        }
        if (callback == null) {
            return;
        }
        callback.onPageAppended((getPlaceholdersBefore() + getStorageCount()) - size, min, i);
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return "leading " + getPlaceholdersBefore() + ", storage " + getStorageCount() + ", trailing " + getPlaceholdersAfter() + ' ' + CollectionsKt.joinToString$default(this.pages, " ", null, null, 0, null, null, 62, null);
    }

    @Override // androidx.paging.NullPaddedList
    public T getFromStorage(int localIndex) {
        int size = this.pages.size();
        int i = 0;
        while (i < size) {
            int size2 = ((PagingSource.LoadResult.Page) this.pages.get(i)).getData().size();
            if (size2 > localIndex) {
                break;
            }
            localIndex -= size2;
            i++;
        }
        return (T) ((PagingSource.LoadResult.Page) this.pages.get(i)).getData().get(localIndex);
    }
}
