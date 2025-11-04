package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.IdentityScopeMap;
import androidx.compose.runtime.snapshots.StateObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: Composition.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0000\u0018\u00002\u00020\u0001:\u0001bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010E\u001a\u00020\u001b2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002030GH\u0002J\b\u0010H\u001a\u00020\u001bH\u0016J \u0010I\u001a\u00020\u001b2\u0011\u0010J\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001e¢\u0006\u0002\b\u001fH\u0016¢\u0006\u0002\u0010#J\b\u0010K\u001a\u00020\u001bH\u0016J\b\u0010L\u001a\u00020\u001bH\u0002J\b\u0010M\u001a\u00020\u001bH\u0002J\u0018\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u0002012\b\u0010Q\u001a\u0004\u0018\u000103J\b\u0010R\u001a\u00020\u001bH\u0016J\u0010\u0010S\u001a\u00020\u001b2\u0006\u0010T\u001a\u000203H\u0002J\u0016\u0010U\u001a\u00020\u000f2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002030GH\u0016J\u0016\u0010V\u001a\u00020\u001b2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001eH\u0016J\b\u0010X\u001a\u00020\u000fH\u0016J\u0016\u0010Y\u001a\u00020\u001b2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002030GH\u0016J\u0010\u0010Z\u001a\u00020\u001b2\u0006\u0010T\u001a\u000203H\u0016J\u0010\u0010[\u001a\u00020\u001b2\u0006\u0010T\u001a\u000203H\u0016J\u001d\u0010\\\u001a\u00020\u001b2\u0006\u0010Q\u001a\u0002032\u0006\u0010P\u001a\u000201H\u0000¢\u0006\u0002\b]J \u0010^\u001a\u00020\u001b2\u0011\u0010J\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001e¢\u0006\u0002\b\u001fH\u0016¢\u0006\u0002\u0010#J\u001c\u0010_\u001a\u0016\u0012\u0004\u0012\u000201\u0012\f\u0012\n\u0012\u0004\u0012\u000203\u0018\u00010200H\u0002J\u0010\u0010`\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010a\u001a\u00020\u001bH\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011Ra\u0010\u0012\u001aU\u0012Q\u0012O\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0005¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b0\u0014j\u0002`\u001c0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001e¢\u0006\u0002\b\u001fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030)0(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u0011R\u0014\u0010-\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0011R\"\u0010/\u001a\u0016\u0012\u0004\u0012\u000201\u0012\f\u0012\n\u0012\u0004\u0012\u000203\u0018\u00010200X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u0010\u0011R\u0014\u00105\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010\u0011R\u0011\u00106\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0011R\u000e\u00107\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\b\u0012\u0004\u0012\u0002010(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00109\u001a\b\u0012\u0004\u0012\u0002010(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010:\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0011\"\u0004\b<\u0010=R\"\u0010>\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001030?j\n\u0012\u0006\u0012\u0004\u0018\u000103`@X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bA\u0010BR\u000e\u0010C\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "parent", "Landroidx/compose/runtime/CompositionContext;", "applier", "Landroidx/compose/runtime/Applier;", "recomposeContext", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;Lkotlin/coroutines/CoroutineContext;)V", "_recomposeContext", "abandonSet", "Ljava/util/HashSet;", "Landroidx/compose/runtime/RememberObserver;", "Lkotlin/collections/HashSet;", "areChildrenComposing", "", "getAreChildrenComposing", "()Z", "changes", "", "Lkotlin/Function3;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "", "Landroidx/compose/runtime/Change;", "composable", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "getComposable", "()Lkotlin/jvm/functions/Function2;", "setComposable", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "derivedStates", "Landroidx/compose/runtime/collection/IdentityScopeMap;", "Landroidx/compose/runtime/DerivedState;", "disposed", "hasInvalidations", "getHasInvalidations", "hasPendingChanges", "getHasPendingChanges", "invalidations", "Landroidx/compose/runtime/collection/IdentityArrayMap;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/collection/IdentityArraySet;", "", "isComposing", "isDisposed", "isRoot", "lock", "observations", "observationsProcessed", "pendingInvalidScopes", "getPendingInvalidScopes$runtime_release", "setPendingInvalidScopes$runtime_release", "(Z)V", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/AtomicReference;", "getRecomposeContext", "()Lkotlin/coroutines/CoroutineContext;", "slotTable", "Landroidx/compose/runtime/SlotTable;", "addPendingInvalidationsLocked", "values", "", "applyChanges", "composeContent", "content", "dispose", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "invalidate", "Landroidx/compose/runtime/InvalidationResult;", "scope", "instance", "invalidateAll", "invalidateScopeOfLocked", "value", "observesAnyOf", "prepareCompose", "block", "recompose", "recordModificationsOf", "recordReadOf", "recordWriteOf", "removeObservation", "removeObservation$runtime_release", "setContent", "takeInvalidations", "validateRecomposeScopeAnchors", "verifyConsistent", "RememberEventDispatcher", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class CompositionImpl implements ControlledComposition {
    private final CoroutineContext _recomposeContext;
    private final HashSet<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final List<Function3<Applier<?>, SlotWriter, RememberManager, Unit>> changes;
    private Function2<? super Composer, ? super Integer, Unit> composable;
    private final ComposerImpl composer;
    private final IdentityScopeMap<DerivedState<?>> derivedStates;
    private boolean disposed;
    private IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> invalidations;
    private final boolean isRoot;
    private final Object lock;
    private final IdentityScopeMap<RecomposeScopeImpl> observations;
    private final IdentityScopeMap<RecomposeScopeImpl> observationsProcessed;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference<Object> pendingModifications;
    private final SlotTable slotTable;

    public CompositionImpl(CompositionContext parent, Applier<?> applier, CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(applier, "applier");
        this.parent = parent;
        this.applier = applier;
        this.pendingModifications = new AtomicReference<>(null);
        this.lock = new Object();
        HashSet<RememberObserver> hashSet = new HashSet<>();
        this.abandonSet = hashSet;
        SlotTable slotTable = new SlotTable();
        this.slotTable = slotTable;
        this.observations = new IdentityScopeMap<>();
        this.derivedStates = new IdentityScopeMap<>();
        ArrayList arrayList = new ArrayList();
        this.changes = arrayList;
        this.observationsProcessed = new IdentityScopeMap<>();
        this.invalidations = new IdentityArrayMap<>(0, 1, null);
        ComposerImpl composerImpl = new ComposerImpl(applier, parent, slotTable, hashSet, arrayList, this);
        parent.registerComposer$runtime_release(composerImpl);
        Unit unit = Unit.INSTANCE;
        this.composer = composerImpl;
        this._recomposeContext = coroutineContext;
        this.isRoot = parent instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.m335getLambda1$runtime_release();
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compositionContext, applier, (i & 4) != 0 ? null : coroutineContext);
    }

    /* renamed from: getPendingInvalidScopes$runtime_release, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final void setPendingInvalidScopes$runtime_release(boolean z) {
        this.pendingInvalidScopes = z;
    }

    public final CoroutineContext getRecomposeContext() {
        CoroutineContext coroutineContext = this._recomposeContext;
        return coroutineContext == null ? this.parent.getRecomposeCoroutineContext$runtime_release() : coroutineContext;
    }

    /* renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime_release();
    }

    public final Function2<Composer, Integer, Unit> getComposable() {
        return this.composable;
    }

    public final void setComposable(Function2<? super Composer, ? super Integer, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.composable = function2;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    /* renamed from: isDisposed, reason: from getter */
    public boolean getDisposed() {
        return this.disposed;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime_release;
        synchronized (this.lock) {
            hasPendingChanges$runtime_release = this.composer.getHasPendingChanges$runtime_release();
        }
        return hasPendingChanges$runtime_release;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        if (this.disposed) {
            throw new IllegalStateException("The composition is disposed".toString());
        }
        this.composable = content;
        this.parent.composeInitial$runtime_release(this, content);
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object obj;
        Object obj2;
        AtomicReference<Object> atomicReference = this.pendingModifications;
        obj = CompositionKt.PendingApplyNoModifications;
        Object andSet = atomicReference.getAndSet(obj);
        if (andSet == null) {
            return;
        }
        obj2 = CompositionKt.PendingApplyNoModifications;
        if (Intrinsics.areEqual(andSet, obj2)) {
            throw new IllegalStateException("pending composition has not been applied".toString());
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set) andSet);
            return;
        }
        if (!(andSet instanceof Object[])) {
            throw new IllegalStateException(Intrinsics.stringPlus("corrupt pendingModifications drain: ", this.pendingModifications).toString());
        }
        Set<? extends Object>[] setArr = (Set[]) andSet;
        int length = setArr.length;
        int i = 0;
        while (i < length) {
            Set<? extends Object> set = setArr[i];
            i++;
            addPendingInvalidationsLocked(set);
        }
    }

    private final void drainPendingModificationsLocked() {
        Object obj;
        Object andSet = this.pendingModifications.getAndSet(null);
        obj = CompositionKt.PendingApplyNoModifications;
        if (Intrinsics.areEqual(andSet, obj)) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set) andSet);
            return;
        }
        if (!(andSet instanceof Object[])) {
            if (andSet == null) {
                throw new IllegalStateException("calling recordModificationsOf and applyChanges concurrently is not supported".toString());
            }
            throw new IllegalStateException(Intrinsics.stringPlus("corrupt pendingModifications drain: ", this.pendingModifications).toString());
        }
        Set<? extends Object>[] setArr = (Set[]) andSet;
        int length = setArr.length;
        int i = 0;
        while (i < length) {
            Set<? extends Object> set = setArr[i];
            i++;
            addPendingInvalidationsLocked(set);
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        synchronized (this.lock) {
            drainPendingModificationsForCompositionLocked();
            this.composer.composeContent$runtime_release(takeInvalidations(), content);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        synchronized (this.lock) {
            if (!this.disposed) {
                this.disposed = true;
                setComposable(ComposableSingletons$CompositionKt.INSTANCE.m336getLambda2$runtime_release());
                if (this.slotTable.getGroupsSize() > 0) {
                    RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                    SlotWriter openWriter = this.slotTable.openWriter();
                    try {
                        ComposerKt.removeCurrentGroup(openWriter, rememberEventDispatcher);
                        Unit unit = Unit.INSTANCE;
                        openWriter.close();
                        this.applier.clear();
                        rememberEventDispatcher.dispatchRememberObservers();
                    } catch (Throwable th) {
                        openWriter.close();
                        throw th;
                    }
                }
                this.composer.dispose$runtime_release();
                this.parent.unregisterComposition$runtime_release(this);
                this.parent.unregisterComposition$runtime_release(this);
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        synchronized (this.lock) {
            z = this.invalidations.getSize() > 0;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.util.Set[]] */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        Object obj2;
        boolean areEqual;
        Set<? extends Object> set;
        Intrinsics.checkNotNullParameter(values, "values");
        do {
            obj = this.pendingModifications.get();
            if (obj == null) {
                areEqual = true;
            } else {
                obj2 = CompositionKt.PendingApplyNoModifications;
                areEqual = Intrinsics.areEqual(obj, obj2);
            }
            if (areEqual) {
                set = values;
            } else if (obj instanceof Set) {
                set = new Set[]{(Set) obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(Intrinsics.stringPlus("corrupt pendingModifications: ", this.pendingModifications).toString());
                }
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                }
                set = ArraysKt.plus((Set<? extends Object>[]) obj, values);
            }
        } while (!PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.pendingModifications, obj, set));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean observesAnyOf(Set<? extends Object> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        for (Object obj : values) {
            if (this.observations.contains(obj) || this.derivedStates.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.composer.prepareCompose$runtime_release(block);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void addPendingInvalidationsLocked(Set<? extends Object> values) {
        Object obj;
        Object obj2;
        int i;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Iterator<? extends Object> it = values.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (next instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) next).invalidateForResult(null);
            } else {
                addPendingInvalidationsLocked$invalidate(this, objectRef, next);
                IdentityScopeMap<DerivedState<?>> identityScopeMap = this.derivedStates;
                int find = identityScopeMap.find(next);
                if (find >= 0) {
                    Iterator<T> it2 = identityScopeMap.scopeSetAt(find).iterator();
                    while (it2.hasNext()) {
                        addPendingInvalidationsLocked$invalidate(this, objectRef, (DerivedState) it2.next());
                    }
                }
            }
        }
        HashSet hashSet = (HashSet) objectRef.element;
        if (hashSet == null) {
            return;
        }
        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap2 = this.observations;
        int size = identityScopeMap2.getSize();
        int i2 = 0;
        if (size > 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = i3 + 1;
                int i6 = identityScopeMap2.getValueOrder()[i3];
                IdentityArraySet<RecomposeScopeImpl> identityArraySet = identityScopeMap2.getScopeSets()[i6];
                Intrinsics.checkNotNull(identityArraySet);
                int size2 = identityArraySet.size();
                if (size2 > 0) {
                    int i7 = 0;
                    i = 0;
                    while (true) {
                        int i8 = i7 + 1;
                        Object obj3 = identityArraySet.getValues()[i7];
                        if (obj3 != null) {
                            obj2 = obj;
                            if (!hashSet.contains((RecomposeScopeImpl) obj3)) {
                                if (i != i7) {
                                    identityArraySet.getValues()[i] = obj3;
                                }
                                i++;
                            }
                            if (i8 >= size2) {
                                break;
                            }
                            obj = obj2;
                            i7 = i8;
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                        }
                    }
                } else {
                    obj2 = obj;
                    i = 0;
                }
                int size3 = identityArraySet.size();
                if (i < size3) {
                    int i9 = i;
                    while (true) {
                        int i10 = i9 + 1;
                        identityArraySet.getValues()[i9] = obj2;
                        if (i10 >= size3) {
                            break;
                        } else {
                            i9 = i10;
                        }
                    }
                }
                identityArraySet.setSize(i);
                if (identityArraySet.size() > 0) {
                    if (i4 != i3) {
                        int i11 = identityScopeMap2.getValueOrder()[i4];
                        identityScopeMap2.getValueOrder()[i4] = i6;
                        identityScopeMap2.getValueOrder()[i3] = i11;
                    }
                    i4++;
                }
                if (i5 >= size) {
                    i2 = i4;
                    break;
                } else {
                    obj = obj2;
                    i3 = i5;
                }
            }
        } else {
            obj2 = null;
        }
        int size4 = identityScopeMap2.getSize();
        if (i2 < size4) {
            int i12 = i2;
            while (true) {
                int i13 = i12 + 1;
                identityScopeMap2.getValues()[identityScopeMap2.getValueOrder()[i12]] = obj2;
                if (i13 >= size4) {
                    break;
                } else {
                    i12 = i13;
                }
            }
        }
        identityScopeMap2.setSize(i2);
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [T, java.util.HashSet] */
    private static final void addPendingInvalidationsLocked$invalidate(CompositionImpl compositionImpl, Ref.ObjectRef<HashSet<RecomposeScopeImpl>> objectRef, Object obj) {
        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap = compositionImpl.observations;
        int find = identityScopeMap.find(obj);
        if (find >= 0) {
            for (RecomposeScopeImpl recomposeScopeImpl : identityScopeMap.scopeSetAt(find)) {
                if (!compositionImpl.observationsProcessed.remove(obj, recomposeScopeImpl) && recomposeScopeImpl.invalidateForResult(obj) != InvalidationResult.IGNORED) {
                    HashSet<RecomposeScopeImpl> hashSet = objectRef.element;
                    HashSet<RecomposeScopeImpl> hashSet2 = hashSet;
                    if (hashSet == null) {
                        ?? hashSet3 = new HashSet();
                        objectRef.element = hashSet3;
                        hashSet2 = hashSet3;
                    }
                    hashSet2.add(recomposeScopeImpl);
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void recordReadOf(Object value) {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        Intrinsics.checkNotNullParameter(value, "value");
        if (getAreChildrenComposing() || (currentRecomposeScope$runtime_release = this.composer.getCurrentRecomposeScope$runtime_release()) == null) {
            return;
        }
        currentRecomposeScope$runtime_release.setUsed(true);
        this.observations.add(value, currentRecomposeScope$runtime_release);
        if (value instanceof DerivedState) {
            Iterator<T> it = ((DerivedState) value).getDependencies().iterator();
            while (it.hasNext()) {
                this.derivedStates.add((StateObject) it.next(), value);
            }
        }
        currentRecomposeScope$runtime_release.recordRead(value);
    }

    private final void invalidateScopeOfLocked(Object value) {
        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap = this.observations;
        int find = identityScopeMap.find(value);
        if (find >= 0) {
            for (RecomposeScopeImpl recomposeScopeImpl : identityScopeMap.scopeSetAt(find)) {
                if (recomposeScopeImpl.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                    this.observationsProcessed.add(value, recomposeScopeImpl);
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void recordWriteOf(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        synchronized (this.lock) {
            invalidateScopeOfLocked(value);
            IdentityScopeMap<DerivedState<?>> identityScopeMap = this.derivedStates;
            int find = identityScopeMap.find(value);
            if (find >= 0) {
                Iterator<T> it = identityScopeMap.scopeSetAt(find).iterator();
                while (it.hasNext()) {
                    invalidateScopeOfLocked((DerivedState) it.next());
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean recompose() {
        boolean recompose$runtime_release;
        synchronized (this.lock) {
            drainPendingModificationsForCompositionLocked();
            recompose$runtime_release = this.composer.recompose$runtime_release(takeInvalidations());
            if (!recompose$runtime_release) {
                drainPendingModificationsLocked();
            }
        }
        return recompose$runtime_release;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        int i;
        Object obj;
        int i2;
        int i3;
        int i4;
        synchronized (this.lock) {
            RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
            try {
                this.applier.onBeginChanges();
                SlotWriter openWriter = this.slotTable.openWriter();
                try {
                    Applier<?> applier = this.applier;
                    List<Function3<Applier<?>, SlotWriter, RememberManager, Unit>> list = this.changes;
                    int size = list.size() - 1;
                    int i5 = 0;
                    if (size >= 0) {
                        int i6 = 0;
                        while (true) {
                            int i7 = i6 + 1;
                            list.get(i6).invoke(applier, openWriter, rememberEventDispatcher);
                            if (i7 > size) {
                                break;
                            } else {
                                i6 = i7;
                            }
                        }
                    }
                    this.changes.clear();
                    Unit unit = Unit.INSTANCE;
                    openWriter.close();
                    this.applier.onEndChanges();
                    rememberEventDispatcher.dispatchRememberObservers();
                    rememberEventDispatcher.dispatchSideEffects();
                    if (getPendingInvalidScopes()) {
                        setPendingInvalidScopes$runtime_release(false);
                        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap = this.observations;
                        int size2 = identityScopeMap.getSize();
                        Object obj2 = null;
                        if (size2 > 0) {
                            int i8 = 0;
                            i = 0;
                            while (true) {
                                int i9 = i8 + 1;
                                int i10 = identityScopeMap.getValueOrder()[i8];
                                IdentityArraySet<RecomposeScopeImpl> identityArraySet = identityScopeMap.getScopeSets()[i10];
                                Intrinsics.checkNotNull(identityArraySet);
                                int size3 = identityArraySet.size();
                                if (size3 > 0) {
                                    int i11 = 0;
                                    i4 = 0;
                                    while (true) {
                                        int i12 = i11 + 1;
                                        Object obj3 = identityArraySet.getValues()[i11];
                                        if (obj3 != null) {
                                            if (((RecomposeScopeImpl) obj3).getValid()) {
                                                if (i4 != i11) {
                                                    identityArraySet.getValues()[i4] = obj3;
                                                }
                                                i4++;
                                            }
                                            if (i12 >= size3) {
                                                break;
                                            } else {
                                                i11 = i12;
                                            }
                                        } else {
                                            throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                        }
                                    }
                                } else {
                                    i4 = 0;
                                }
                                int size4 = identityArraySet.size();
                                if (i4 < size4) {
                                    int i13 = i4;
                                    while (true) {
                                        int i14 = i13 + 1;
                                        identityArraySet.getValues()[i13] = null;
                                        if (i14 >= size4) {
                                            break;
                                        } else {
                                            i13 = i14;
                                        }
                                    }
                                }
                                identityArraySet.setSize(i4);
                                if (identityArraySet.size() > 0) {
                                    if (i != i8) {
                                        int i15 = identityScopeMap.getValueOrder()[i];
                                        identityScopeMap.getValueOrder()[i] = i10;
                                        identityScopeMap.getValueOrder()[i8] = i15;
                                    }
                                    i++;
                                }
                                if (i9 >= size2) {
                                    break;
                                } else {
                                    i8 = i9;
                                }
                            }
                        } else {
                            i = 0;
                        }
                        int size5 = identityScopeMap.getSize();
                        if (i < size5) {
                            int i16 = i;
                            while (true) {
                                int i17 = i16 + 1;
                                identityScopeMap.getValues()[identityScopeMap.getValueOrder()[i16]] = null;
                                if (i17 >= size5) {
                                    break;
                                } else {
                                    i16 = i17;
                                }
                            }
                        }
                        identityScopeMap.setSize(i);
                        IdentityScopeMap<DerivedState<?>> identityScopeMap2 = this.derivedStates;
                        int size6 = identityScopeMap2.getSize();
                        if (size6 > 0) {
                            int i18 = 0;
                            int i19 = 0;
                            while (true) {
                                int i20 = i18 + 1;
                                int i21 = identityScopeMap2.getValueOrder()[i18];
                                IdentityArraySet<DerivedState<?>> identityArraySet2 = identityScopeMap2.getScopeSets()[i21];
                                Intrinsics.checkNotNull(identityArraySet2);
                                int size7 = identityArraySet2.size();
                                if (size7 > 0) {
                                    int i22 = i5;
                                    i3 = i22;
                                    while (true) {
                                        int i23 = i22 + 1;
                                        Object obj4 = identityArraySet2.getValues()[i22];
                                        if (obj4 != null) {
                                            obj = obj2;
                                            if (this.observations.contains((DerivedState) obj4)) {
                                                if (i3 != i22) {
                                                    identityArraySet2.getValues()[i3] = obj4;
                                                }
                                                i3++;
                                            }
                                            if (i23 >= size7) {
                                                break;
                                            }
                                            i22 = i23;
                                            obj2 = obj;
                                        } else {
                                            throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                        }
                                    }
                                } else {
                                    obj = obj2;
                                    i3 = 0;
                                }
                                int size8 = identityArraySet2.size();
                                if (i3 < size8) {
                                    int i24 = i3;
                                    while (true) {
                                        int i25 = i24 + 1;
                                        identityArraySet2.getValues()[i24] = obj;
                                        if (i25 >= size8) {
                                            break;
                                        } else {
                                            i24 = i25;
                                        }
                                    }
                                }
                                identityArraySet2.setSize(i3);
                                if (identityArraySet2.size() > 0) {
                                    if (i19 != i18) {
                                        int i26 = identityScopeMap2.getValueOrder()[i19];
                                        identityScopeMap2.getValueOrder()[i19] = i21;
                                        identityScopeMap2.getValueOrder()[i18] = i26;
                                    }
                                    i19++;
                                }
                                if (i20 >= size6) {
                                    i2 = i19;
                                    break;
                                } else {
                                    i18 = i20;
                                    obj2 = obj;
                                    i5 = 0;
                                }
                            }
                        } else {
                            obj = null;
                            i2 = 0;
                        }
                        int size9 = identityScopeMap2.getSize();
                        if (i2 < size9) {
                            int i27 = i2;
                            while (true) {
                                int i28 = i27 + 1;
                                identityScopeMap2.getValues()[identityScopeMap2.getValueOrder()[i27]] = obj;
                                if (i28 >= size9) {
                                    break;
                                } else {
                                    i27 = i28;
                                }
                            }
                        }
                        identityScopeMap2.setSize(i2);
                    }
                    rememberEventDispatcher.dispatchAbandons();
                    drainPendingModificationsLocked();
                    Unit unit2 = Unit.INSTANCE;
                } catch (Throwable th) {
                    openWriter.close();
                    throw th;
                }
            } catch (Throwable th2) {
                rememberEventDispatcher.dispatchAbandons();
                throw th2;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        synchronized (this.lock) {
            for (Object obj : this.slotTable.getSlots()) {
                RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.invalidate();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        synchronized (this.lock) {
            if (!isComposing()) {
                this.slotTable.verifyWellFormed();
                validateRecomposeScopeAnchors(this.slotTable);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !this.slotTable.ownsAnchor(anchor) || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (anchor.toIndexFor(this.slotTable) < 0) {
            return InvalidationResult.IGNORED;
        }
        if (isComposing() && this.composer.tryImminentInvalidation$runtime_release(scope, instance)) {
            return InvalidationResult.IMMINENT;
        }
        if (instance != null) {
            CompositionKt.addValue(this.invalidations, scope, instance);
        } else {
            this.invalidations.set(scope, null);
        }
        this.parent.invalidate$runtime_release(this);
        return isComposing() ? InvalidationResult.DEFERRED : InvalidationResult.SCHEDULED;
    }

    public final void removeObservation$runtime_release(Object instance, RecomposeScopeImpl scope) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.observations.remove(instance, scope);
    }

    private final IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> takeInvalidations() {
        IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> identityArrayMap = this.invalidations;
        this.invalidations = new IdentityArrayMap<>(0, 1, null);
        return identityArrayMap;
    }

    private final void validateRecomposeScopeAnchors(SlotTable slotTable) {
        Object[] slots = slotTable.getSlots();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : slots) {
            RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
            if (recomposeScopeImpl != null) {
                arrayList.add(recomposeScopeImpl);
            }
        }
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i2 = i + 1;
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) arrayList2.get(i);
            Anchor anchor = recomposeScopeImpl2.getAnchor();
            if (anchor != null && !slotTable.slotsOf$runtime_release(anchor.toIndexFor(slotTable)).contains(recomposeScopeImpl2)) {
                throw new IllegalStateException(("Misaligned anchor " + anchor + " in scope " + recomposeScopeImpl2 + " encountered, scope found at " + ArraysKt.indexOf((RecomposeScopeImpl[]) slotTable.getSlots(), recomposeScopeImpl2)).toString());
            }
            if (i2 > size) {
                return;
            } else {
                i = i2;
            }
        }
    }

    /* compiled from: Composition.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000bJ\u0006\u0010\u000e\u001a\u00020\u000bJ\u0010\u0010\u0006\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0016\u0010\u0010\u001a\u00020\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/runtime/CompositionImpl$RememberEventDispatcher;", "Landroidx/compose/runtime/RememberManager;", "abandoning", "", "Landroidx/compose/runtime/RememberObserver;", "(Ljava/util/Set;)V", "forgetting", "", "remembering", "sideEffects", "Lkotlin/Function0;", "", "dispatchAbandons", "dispatchRememberObservers", "dispatchSideEffects", "instance", "sideEffect", "effect", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class RememberEventDispatcher implements RememberManager {
        private final Set<RememberObserver> abandoning;
        private final List<RememberObserver> forgetting;
        private final List<RememberObserver> remembering;
        private final List<Function0<Unit>> sideEffects;

        public RememberEventDispatcher(Set<RememberObserver> abandoning) {
            Intrinsics.checkNotNullParameter(abandoning, "abandoning");
            this.abandoning = abandoning;
            this.remembering = new ArrayList();
            this.forgetting = new ArrayList();
            this.sideEffects = new ArrayList();
        }

        @Override // androidx.compose.runtime.RememberManager
        public void remembering(RememberObserver instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            int lastIndexOf = this.forgetting.lastIndexOf(instance);
            if (lastIndexOf >= 0) {
                this.forgetting.remove(lastIndexOf);
                this.abandoning.remove(instance);
            } else {
                this.remembering.add(instance);
            }
        }

        @Override // androidx.compose.runtime.RememberManager
        public void forgetting(RememberObserver instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            int lastIndexOf = this.remembering.lastIndexOf(instance);
            if (lastIndexOf >= 0) {
                this.remembering.remove(lastIndexOf);
                this.abandoning.remove(instance);
            } else {
                this.forgetting.add(instance);
            }
        }

        @Override // androidx.compose.runtime.RememberManager
        public void sideEffect(Function0<Unit> effect) {
            Intrinsics.checkNotNullParameter(effect, "effect");
            this.sideEffects.add(effect);
        }

        public final void dispatchRememberObservers() {
            int size;
            if (!this.forgetting.isEmpty() && this.forgetting.size() - 1 >= 0) {
                while (true) {
                    int i = size - 1;
                    RememberObserver rememberObserver = this.forgetting.get(size);
                    if (!this.abandoning.contains(rememberObserver)) {
                        rememberObserver.onForgotten();
                    }
                    if (i < 0) {
                        break;
                    } else {
                        size = i;
                    }
                }
            }
            if (this.remembering.isEmpty()) {
                return;
            }
            List<RememberObserver> list = this.remembering;
            int size2 = list.size() - 1;
            if (size2 < 0) {
                return;
            }
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                RememberObserver rememberObserver2 = list.get(i2);
                this.abandoning.remove(rememberObserver2);
                rememberObserver2.onRemembered();
                if (i3 > size2) {
                    return;
                } else {
                    i2 = i3;
                }
            }
        }

        public final void dispatchSideEffects() {
            if (this.sideEffects.isEmpty()) {
                return;
            }
            List<Function0<Unit>> list = this.sideEffects;
            int size = list.size() - 1;
            if (size >= 0) {
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    list.get(i).invoke();
                    if (i2 > size) {
                        break;
                    } else {
                        i = i2;
                    }
                }
            }
            this.sideEffects.clear();
        }

        public final void dispatchAbandons() {
            if (this.abandoning.isEmpty()) {
                return;
            }
            Iterator<RememberObserver> it = this.abandoning.iterator();
            while (it.hasNext()) {
                RememberObserver next = it.next();
                it.remove();
                next.onAbandoned();
            }
        }
    }
}
