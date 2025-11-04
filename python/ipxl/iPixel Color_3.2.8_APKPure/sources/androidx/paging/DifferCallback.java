package androidx.paging;

import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;

/* compiled from: PagingDataDiffer.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Landroidx/paging/DifferCallback;", "", "onChanged", "", PlayerFinal.PLAYER_POSITION, "", "count", "onInserted", "onRemoved", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public interface DifferCallback {
    void onChanged(int position, int count);

    void onInserted(int position, int count);

    void onRemoved(int position, int count);
}
