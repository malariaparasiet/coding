package androidx.compose.runtime.snapshots;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Snapshot.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0001¢\u0006\u0002\u0010\fJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\tH\u0016R\u0011\u0010\u000b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Landroidx/compose/runtime/snapshots/NestedMutableSnapshot;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "id", "", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "readObserver", "Lkotlin/Function1;", "", "", "writeObserver", "parent", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/snapshots/MutableSnapshot;)V", "getParent", "()Landroidx/compose/runtime/snapshots/MutableSnapshot;", "root", "Landroidx/compose/runtime/snapshots/Snapshot;", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "dispose", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class NestedMutableSnapshot extends MutableSnapshot {
    private final MutableSnapshot parent;

    public final MutableSnapshot getParent() {
        return this.parent;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NestedMutableSnapshot(int i, SnapshotIdSet invalid, Function1<Object, Unit> function1, Function1<Object, Unit> function12, MutableSnapshot parent) {
        super(i, invalid, function1, function12);
        Intrinsics.checkNotNullParameter(invalid, "invalid");
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.parent = parent;
        parent.mo361nestedActivated$runtime_release(this);
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this.parent.getRoot();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        super.dispose();
        this.parent.mo362nestedDeactivated$runtime_release(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008b A[Catch: all -> 0x00cb, TryCatch #0 {, blocks: (B:11:0x0030, B:13:0x0038, B:16:0x003f, B:21:0x005d, B:23:0x0067, B:24:0x0078, B:25:0x0081, B:27:0x008b, B:28:0x0092, B:33:0x007e), top: B:10:0x0030 }] */
    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.compose.runtime.snapshots.SnapshotApplyResult apply() {
        /*
            r6 = this;
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent
            boolean r0 = r0.getApplied()
            if (r0 != 0) goto Lce
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent
            boolean r0 = r0.getDisposed()
            if (r0 == 0) goto L12
            goto Lce
        L12:
            java.util.Set r0 = r6.getModified$runtime_release()
            int r1 = r6.getId()
            if (r0 == 0) goto L2a
            androidx.compose.runtime.snapshots.MutableSnapshot r2 = r6.parent
            r3 = r6
            androidx.compose.runtime.snapshots.MutableSnapshot r3 = (androidx.compose.runtime.snapshots.MutableSnapshot) r3
            androidx.compose.runtime.snapshots.SnapshotIdSet r4 = r2.getInvalid()
            java.util.Map r2 = androidx.compose.runtime.snapshots.SnapshotKt.access$optimisticMerges(r2, r3, r4)
            goto L2b
        L2a:
            r2 = 0
        L2b:
            java.lang.Object r3 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
            monitor-enter(r3)
            r4 = r6
            androidx.compose.runtime.snapshots.Snapshot r4 = (androidx.compose.runtime.snapshots.Snapshot) r4     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.SnapshotKt.access$validateOpen(r4)     // Catch: java.lang.Throwable -> Lcb
            if (r0 == 0) goto L7e
            int r4 = r0.size()     // Catch: java.lang.Throwable -> Lcb
            if (r4 != 0) goto L3f
            goto L7e
        L3f:
            androidx.compose.runtime.snapshots.MutableSnapshot r4 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            int r4 = r4.getId()     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.MutableSnapshot r5 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.SnapshotIdSet r5 = r5.getInvalid()     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.SnapshotApplyResult r2 = r6.innerApply$runtime_release(r4, r2, r5)     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Success r4 = androidx.compose.runtime.snapshots.SnapshotApplyResult.Success.INSTANCE     // Catch: java.lang.Throwable -> Lcb
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)     // Catch: java.lang.Throwable -> Lcb
            if (r4 != 0) goto L5d
            monitor-exit(r3)
            return r2
        L5d:
            androidx.compose.runtime.snapshots.MutableSnapshot r2 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            java.util.Set r2 = r2.getModified$runtime_release()     // Catch: java.lang.Throwable -> Lcb
            if (r2 != 0) goto L78
            java.util.HashSet r2 = new java.util.HashSet     // Catch: java.lang.Throwable -> Lcb
            r2.<init>()     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.MutableSnapshot r4 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            r5 = r2
            java.util.Set r5 = (java.util.Set) r5     // Catch: java.lang.Throwable -> Lcb
            r4.setModified(r5)     // Catch: java.lang.Throwable -> Lcb
            java.util.Set r2 = (java.util.Set) r2     // Catch: java.lang.Throwable -> Lcb
        L78:
            java.util.Collection r0 = (java.util.Collection) r0     // Catch: java.lang.Throwable -> Lcb
            r2.addAll(r0)     // Catch: java.lang.Throwable -> Lcb
            goto L81
        L7e:
            r6.close$runtime_release()     // Catch: java.lang.Throwable -> Lcb
        L81:
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            int r0 = r0.getId()     // Catch: java.lang.Throwable -> Lcb
            if (r0 >= r1) goto L92
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            r0.advance$runtime_release()     // Catch: java.lang.Throwable -> Lcb
        L92:
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.MutableSnapshot r2 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.SnapshotIdSet r2 = r2.getInvalid()     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.SnapshotIdSet r2 = r2.clear(r1)     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.SnapshotIdSet r4 = r6.getPreviousIds()     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.SnapshotIdSet r2 = r2.andNot(r4)     // Catch: java.lang.Throwable -> Lcb
            r0.setInvalid$runtime_release(r2)     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            r0.recordPrevious$runtime_release(r1)     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.getParent()     // Catch: java.lang.Throwable -> Lcb
            androidx.compose.runtime.snapshots.SnapshotIdSet r1 = r6.getPreviousIds()     // Catch: java.lang.Throwable -> Lcb
            r0.recordPreviousList$runtime_release(r1)     // Catch: java.lang.Throwable -> Lcb
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lcb
            monitor-exit(r3)
            r0 = 1
            r6.setApplied$runtime_release(r0)
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Success r0 = androidx.compose.runtime.snapshots.SnapshotApplyResult.Success.INSTANCE
            androidx.compose.runtime.snapshots.SnapshotApplyResult r0 = (androidx.compose.runtime.snapshots.SnapshotApplyResult) r0
            return r0
        Lcb:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        Lce:
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Failure r0 = new androidx.compose.runtime.snapshots.SnapshotApplyResult$Failure
            r1 = r6
            androidx.compose.runtime.snapshots.Snapshot r1 = (androidx.compose.runtime.snapshots.Snapshot) r1
            r0.<init>(r1)
            androidx.compose.runtime.snapshots.SnapshotApplyResult r0 = (androidx.compose.runtime.snapshots.SnapshotApplyResult) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.NestedMutableSnapshot.apply():androidx.compose.runtime.snapshots.SnapshotApplyResult");
    }
}
