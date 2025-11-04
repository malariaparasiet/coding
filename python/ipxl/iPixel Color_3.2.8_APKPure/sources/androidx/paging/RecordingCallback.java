package androidx.paging;

import androidx.paging.PagedList;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* compiled from: RecordingCallback.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001J\u0018\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0018\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/paging/RecordingCallback;", "Landroidx/paging/PagedList$Callback;", "()V", "list", "", "", "dispatchRecordingTo", "", "other", "onChanged", PlayerFinal.PLAYER_POSITION, "count", "onInserted", "onRemoved", "Companion", "paging-runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class RecordingCallback extends PagedList.Callback {
    private static final int Changed = 0;
    private static final int Inserted = 1;
    private static final int Removed = 2;
    private final List<Integer> list = new ArrayList();

    @Override // androidx.paging.PagedList.Callback
    public void onChanged(int position, int count) {
        this.list.add(0);
        this.list.add(Integer.valueOf(position));
        this.list.add(Integer.valueOf(count));
    }

    @Override // androidx.paging.PagedList.Callback
    public void onInserted(int position, int count) {
        this.list.add(1);
        this.list.add(Integer.valueOf(position));
        this.list.add(Integer.valueOf(count));
    }

    @Override // androidx.paging.PagedList.Callback
    public void onRemoved(int position, int count) {
        this.list.add(2);
        this.list.add(Integer.valueOf(position));
        this.list.add(Integer.valueOf(count));
    }

    public final void dispatchRecordingTo(PagedList.Callback other) {
        Intrinsics.checkNotNullParameter(other, "other");
        IntProgression step = RangesKt.step(RangesKt.until(0, this.list.size()), 3);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
            while (true) {
                int i = first + step2;
                int intValue = this.list.get(first).intValue();
                if (intValue == 0) {
                    other.onChanged(this.list.get(first + 1).intValue(), this.list.get(first + 2).intValue());
                } else if (intValue == 1) {
                    other.onInserted(this.list.get(first + 1).intValue(), this.list.get(first + 2).intValue());
                } else if (intValue == 2) {
                    other.onRemoved(this.list.get(first + 1).intValue(), this.list.get(first + 2).intValue());
                } else {
                    throw new IllegalStateException("Unexpected recording value");
                }
                if (first == last) {
                    break;
                } else {
                    first = i;
                }
            }
        }
        this.list.clear();
    }
}
