package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: NullPaddedList.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u0004H&¢\u0006\u0002\u0010\u000fR\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0012\u0010\t\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0012\u0010\u000b\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006¨\u0006\u0010"}, d2 = {"Landroidx/paging/NullPaddedList;", ExifInterface.GPS_DIRECTION_TRUE, "", "placeholdersAfter", "", "getPlaceholdersAfter", "()I", "placeholdersBefore", "getPlaceholdersBefore", "size", "getSize", "storageCount", "getStorageCount", "getFromStorage", "localIndex", "(I)Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public interface NullPaddedList<T> {
    T getFromStorage(int localIndex);

    int getPlaceholdersAfter();

    int getPlaceholdersBefore();

    int getSize();

    int getStorageCount();
}
