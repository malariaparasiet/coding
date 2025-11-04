package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateRecord;
import androidx.exifinterface.media.ExifInterface;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SnapshotState.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u001fB\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J2\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\b\u0010\u001e\u001a\u00020\u001bH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\t¨\u0006 "}, d2 = {"Landroidx/compose/runtime/DerivedSnapshotState;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/snapshots/StateObject;", "Landroidx/compose/runtime/DerivedState;", "calculation", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "currentValue", "getCurrentValue", "()Ljava/lang/Object;", "dependencies", "", "getDependencies", "()Ljava/util/Set;", "first", "Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord;", "firstStateRecord", "Landroidx/compose/runtime/snapshots/StateRecord;", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "value", "getValue", "currentRecord", "readable", "snapshot", "Landroidx/compose/runtime/snapshots/Snapshot;", "displayValue", "", "prependStateRecord", "", "toString", "ResultRecord", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class DerivedSnapshotState<T> implements StateObject, DerivedState<T> {
    private final Function0<T> calculation;
    private ResultRecord<T> first;

    /* JADX WARN: Multi-variable type inference failed */
    public DerivedSnapshotState(Function0<? extends T> calculation) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        this.calculation = calculation;
        this.first = new ResultRecord<>();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        return StateObject.DefaultImpls.mergeRecords(this, stateRecord, stateRecord2, stateRecord3);
    }

    /* compiled from: SnapshotState.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\b\u0010\u001b\u001a\u00020\u0002H\u0016J\u001a\u0010\u001c\u001a\u00020\u001d2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!J\u001a\u0010\"\u001a\u00020\u00132\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!R.\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u0004\u0018\u00018\u0001X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/snapshots/StateRecord;", "()V", "dependencies", "Ljava/util/HashSet;", "Landroidx/compose/runtime/snapshots/StateObject;", "Lkotlin/collections/HashSet;", "getDependencies", "()Ljava/util/HashSet;", "setDependencies", "(Ljava/util/HashSet;)V", "result", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "resultHash", "", "getResultHash", "()I", "setResultHash", "(I)V", "assign", "", "value", "create", "isValid", "", "derivedState", "Landroidx/compose/runtime/DerivedState;", "snapshot", "Landroidx/compose/runtime/snapshots/Snapshot;", "readableHash", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class ResultRecord<T> extends StateRecord {
        private HashSet<StateObject> dependencies;
        private T result;
        private int resultHash;

        public final HashSet<StateObject> getDependencies() {
            return this.dependencies;
        }

        public final void setDependencies(HashSet<StateObject> hashSet) {
            this.dependencies = hashSet;
        }

        public final T getResult() {
            return this.result;
        }

        public final void setResult(T t) {
            this.result = t;
        }

        public final int getResultHash() {
            return this.resultHash;
        }

        public final void setResultHash(int i) {
            this.resultHash = i;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord value) {
            Intrinsics.checkNotNullParameter(value, "value");
            ResultRecord resultRecord = (ResultRecord) value;
            this.dependencies = resultRecord.dependencies;
            this.result = resultRecord.result;
            this.resultHash = resultRecord.resultHash;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new ResultRecord();
        }

        public final boolean isValid(DerivedState<?> derivedState, Snapshot snapshot) {
            Intrinsics.checkNotNullParameter(derivedState, "derivedState");
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            return this.result != null && this.resultHash == readableHash(derivedState, snapshot);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0054 A[Catch: all -> 0x0098, LOOP:1: B:19:0x004e->B:21:0x0054, LOOP_END, TryCatch #0 {all -> 0x0098, blocks: (B:18:0x004a, B:19:0x004e, B:21:0x0054, B:23:0x0077), top: B:17:0x004a }] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0081 A[LOOP:2: B:26:0x0081->B:28:0x0095, LOOP_START, PHI: r4
          0x0081: PHI (r4v3 int) = (r4v0 int), (r4v4 int) binds: [B:25:0x007f, B:28:0x0095] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final int readableHash(androidx.compose.runtime.DerivedState<?> r8, androidx.compose.runtime.snapshots.Snapshot r9) {
            /*
                r7 = this;
                java.lang.String r0 = "derivedState"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                java.lang.String r0 = "snapshot"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                java.lang.Object r0 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
                monitor-enter(r0)
                java.util.HashSet r1 = r7.getDependencies()     // Catch: java.lang.Throwable -> Lb8
                monitor-exit(r0)
                r0 = 7
                if (r1 == 0) goto Lb7
                androidx.compose.runtime.SnapshotThreadLocal r2 = androidx.compose.runtime.SnapshotStateKt.access$getDerivedStateObservers$p()
                java.lang.Object r2 = r2.get()
                androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList r2 = (androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList) r2
                if (r2 != 0) goto L28
                androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList r2 = androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt.persistentListOf()
            L28:
                java.util.List r2 = (java.util.List) r2
                int r3 = r2.size()
                int r3 = r3 + (-1)
                r4 = 0
                if (r3 < 0) goto L4a
                r5 = r4
            L34:
                int r6 = r5 + 1
                java.lang.Object r5 = r2.get(r5)
                kotlin.Pair r5 = (kotlin.Pair) r5
                java.lang.Object r5 = r5.component1()
                kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
                r5.invoke(r8)
                if (r6 <= r3) goto L48
                goto L4a
            L48:
                r5 = r6
                goto L34
            L4a:
                java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L98
            L4e:
                boolean r3 = r1.hasNext()     // Catch: java.lang.Throwable -> L98
                if (r3 == 0) goto L77
                java.lang.Object r3 = r1.next()     // Catch: java.lang.Throwable -> L98
                androidx.compose.runtime.snapshots.StateObject r3 = (androidx.compose.runtime.snapshots.StateObject) r3     // Catch: java.lang.Throwable -> L98
                androidx.compose.runtime.snapshots.StateRecord r5 = r3.getFirstStateRecord()     // Catch: java.lang.Throwable -> L98
                java.lang.String r6 = "stateObject"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)     // Catch: java.lang.Throwable -> L98
                androidx.compose.runtime.snapshots.StateRecord r3 = androidx.compose.runtime.snapshots.SnapshotKt.readable(r5, r3, r9)     // Catch: java.lang.Throwable -> L98
                int r0 = r0 * 31
                int r5 = androidx.compose.runtime.ActualJvm_jvmKt.identityHashCode(r3)     // Catch: java.lang.Throwable -> L98
                int r0 = r0 + r5
                int r0 = r0 * 31
                int r3 = r3.getSnapshotId()     // Catch: java.lang.Throwable -> L98
                int r0 = r0 + r3
                goto L4e
            L77:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L98
                int r9 = r2.size()
                int r9 = r9 + (-1)
                if (r9 < 0) goto L97
            L81:
                int r1 = r4 + 1
                java.lang.Object r3 = r2.get(r4)
                kotlin.Pair r3 = (kotlin.Pair) r3
                java.lang.Object r3 = r3.component2()
                kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
                r3.invoke(r8)
                if (r1 <= r9) goto L95
                goto L97
            L95:
                r4 = r1
                goto L81
            L97:
                return r0
            L98:
                r9 = move-exception
                int r0 = r2.size()
                int r0 = r0 + (-1)
                if (r0 < 0) goto Lb6
            La1:
                int r1 = r4 + 1
                java.lang.Object r3 = r2.get(r4)
                kotlin.Pair r3 = (kotlin.Pair) r3
                java.lang.Object r3 = r3.component2()
                kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
                r3.invoke(r8)
                if (r1 > r0) goto Lb6
                r4 = r1
                goto La1
            Lb6:
                throw r9
            Lb7:
                return r0
            Lb8:
                r8 = move-exception
                monitor-exit(r0)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.DerivedSnapshotState.ResultRecord.readableHash(androidx.compose.runtime.DerivedState, androidx.compose.runtime.snapshots.Snapshot):int");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0057 A[LOOP:1: B:18:0x0057->B:20:0x006b, LOOP_START, PHI: r2
      0x0057: PHI (r2v9 int) = (r2v0 int), (r2v14 int) binds: [B:17:0x0055, B:20:0x006b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final androidx.compose.runtime.DerivedSnapshotState.ResultRecord<T> currentRecord(androidx.compose.runtime.DerivedSnapshotState.ResultRecord<T> r6, androidx.compose.runtime.snapshots.Snapshot r7, kotlin.jvm.functions.Function0<? extends T> r8) {
        /*
            r5 = this;
            r0 = r5
            androidx.compose.runtime.DerivedState r0 = (androidx.compose.runtime.DerivedState) r0
            boolean r7 = r6.isValid(r0, r7)
            if (r7 == 0) goto La
            return r6
        La:
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            androidx.compose.runtime.SnapshotThreadLocal r7 = androidx.compose.runtime.SnapshotStateKt.access$getDerivedStateObservers$p()
            java.lang.Object r7 = r7.get()
            androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList r7 = (androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList) r7
            if (r7 != 0) goto L1f
            androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList r7 = androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt.persistentListOf()
        L1f:
            java.util.List r7 = (java.util.List) r7
            int r1 = r7.size()
            int r1 = r1 + (-1)
            r2 = 0
            if (r1 < 0) goto L41
            r3 = r2
        L2b:
            int r4 = r3 + 1
            java.lang.Object r3 = r7.get(r3)
            kotlin.Pair r3 = (kotlin.Pair) r3
            java.lang.Object r3 = r3.component1()
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            r3.invoke(r0)
            if (r4 <= r1) goto L3f
            goto L41
        L3f:
            r3 = r4
            goto L2b
        L41:
            androidx.compose.runtime.snapshots.Snapshot$Companion r1 = androidx.compose.runtime.snapshots.Snapshot.INSTANCE     // Catch: java.lang.Throwable -> L9f
            androidx.compose.runtime.DerivedSnapshotState$currentRecord$result$1$1 r3 = new androidx.compose.runtime.DerivedSnapshotState$currentRecord$result$1$1     // Catch: java.lang.Throwable -> L9f
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L9f
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3     // Catch: java.lang.Throwable -> L9f
            r4 = 0
            java.lang.Object r8 = r1.observe(r3, r4, r8)     // Catch: java.lang.Throwable -> L9f
            int r1 = r7.size()
            int r1 = r1 + (-1)
            if (r1 < 0) goto L6d
        L57:
            int r3 = r2 + 1
            java.lang.Object r2 = r7.get(r2)
            kotlin.Pair r2 = (kotlin.Pair) r2
            java.lang.Object r2 = r2.component2()
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            r2.invoke(r0)
            if (r3 <= r1) goto L6b
            goto L6d
        L6b:
            r2 = r3
            goto L57
        L6d:
            java.lang.Object r7 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
            monitor-enter(r7)
            androidx.compose.runtime.snapshots.Snapshot$Companion r0 = androidx.compose.runtime.snapshots.Snapshot.INSTANCE     // Catch: java.lang.Throwable -> L9c
            androidx.compose.runtime.snapshots.Snapshot r0 = r0.getCurrent()     // Catch: java.lang.Throwable -> L9c
            androidx.compose.runtime.DerivedSnapshotState$ResultRecord<T> r1 = r5.first     // Catch: java.lang.Throwable -> L9c
            androidx.compose.runtime.snapshots.StateRecord r1 = (androidx.compose.runtime.snapshots.StateRecord) r1     // Catch: java.lang.Throwable -> L9c
            r2 = r5
            androidx.compose.runtime.snapshots.StateObject r2 = (androidx.compose.runtime.snapshots.StateObject) r2     // Catch: java.lang.Throwable -> L9c
            androidx.compose.runtime.snapshots.StateRecord r1 = androidx.compose.runtime.snapshots.SnapshotKt.newWritableRecord(r1, r2, r0)     // Catch: java.lang.Throwable -> L9c
            androidx.compose.runtime.DerivedSnapshotState$ResultRecord r1 = (androidx.compose.runtime.DerivedSnapshotState.ResultRecord) r1     // Catch: java.lang.Throwable -> L9c
            r1.setDependencies(r6)     // Catch: java.lang.Throwable -> L9c
            r6 = r5
            androidx.compose.runtime.DerivedState r6 = (androidx.compose.runtime.DerivedState) r6     // Catch: java.lang.Throwable -> L9c
            int r6 = r1.readableHash(r6, r0)     // Catch: java.lang.Throwable -> L9c
            r1.setResultHash(r6)     // Catch: java.lang.Throwable -> L9c
            r1.setResult(r8)     // Catch: java.lang.Throwable -> L9c
            monitor-exit(r7)
            androidx.compose.runtime.snapshots.Snapshot$Companion r6 = androidx.compose.runtime.snapshots.Snapshot.INSTANCE
            r6.notifyObjectsInitialized()
            return r1
        L9c:
            r6 = move-exception
            monitor-exit(r7)
            throw r6
        L9f:
            r6 = move-exception
            int r8 = r7.size()
            int r8 = r8 + (-1)
            if (r8 < 0) goto Lbd
        La8:
            int r1 = r2 + 1
            java.lang.Object r2 = r7.get(r2)
            kotlin.Pair r2 = (kotlin.Pair) r2
            java.lang.Object r2 = r2.component2()
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            r2.invoke(r0)
            if (r1 > r8) goto Lbd
            r2 = r1
            goto La8
        Lbd:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.DerivedSnapshotState.currentRecord(androidx.compose.runtime.DerivedSnapshotState$ResultRecord, androidx.compose.runtime.snapshots.Snapshot, kotlin.jvm.functions.Function0):androidx.compose.runtime.DerivedSnapshotState$ResultRecord");
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.first;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.first = (ResultRecord) value;
    }

    @Override // androidx.compose.runtime.State
    public T getValue() {
        Function1<Object, Unit> readObserver$runtime_release = Snapshot.INSTANCE.getCurrent().getReadObserver$runtime_release();
        if (readObserver$runtime_release != null) {
            readObserver$runtime_release.invoke(this);
        }
        return getCurrentValue();
    }

    @Override // androidx.compose.runtime.DerivedState
    public T getCurrentValue() {
        return currentRecord((ResultRecord) SnapshotKt.current(this.first, Snapshot.INSTANCE.getCurrent()), Snapshot.INSTANCE.getCurrent(), this.calculation).getResult();
    }

    @Override // androidx.compose.runtime.DerivedState
    public Set<StateObject> getDependencies() {
        HashSet<StateObject> dependencies = currentRecord((ResultRecord) SnapshotKt.current(this.first, Snapshot.INSTANCE.getCurrent()), Snapshot.INSTANCE.getCurrent(), this.calculation).getDependencies();
        return dependencies == null ? SetsKt.emptySet() : dependencies;
    }

    public String toString() {
        return "DerivedState(value=" + displayValue() + ")@" + hashCode();
    }

    private final String displayValue() {
        ResultRecord resultRecord = (ResultRecord) SnapshotKt.current(this.first, Snapshot.INSTANCE.getCurrent());
        if (resultRecord.isValid(this, Snapshot.INSTANCE.getCurrent())) {
            return String.valueOf(resultRecord.getResult());
        }
        return "<Not calculated>";
    }
}
