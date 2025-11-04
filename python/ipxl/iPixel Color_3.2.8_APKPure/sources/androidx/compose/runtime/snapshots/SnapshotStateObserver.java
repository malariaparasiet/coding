package androidx.compose.runtime.snapshots;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.IdentityScopeMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SnapshotStateObserver.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0007\u0018\u00002\u00020\u0001:\u0001*B.\u0012'\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\tJ\b\u0010\u0018\u001a\u00020\u0005H\u0002J\u0006\u0010\u0019\u001a\u00020\u0005J\u000e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0001J)\u0010\u001b\u001a\u00020\u00052!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00150\u0003J,\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001e0\f\"\b\b\u0000\u0010\u001e*\u00020\u00012\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u00020\u00050\u0003H\u0002J\u001c\u0010 \u001a\u00020\u00052\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\u0006\u0010\"\u001a\u00020\u0010J?\u0010#\u001a\u00020\u0005\"\b\b\u0000\u0010\u001e*\u00020\u00012\u0006\u0010\u001a\u001a\u0002H\u001e2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u00020\u00050\u00032\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010&J\u0006\u0010'\u001a\u00020\u0005J\u0006\u0010(\u001a\u00020\u0005J\u0014\u0010)\u001a\u00020\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0018\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "", "onChangedExecutor", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "callback", "(Lkotlin/jvm/functions/Function1;)V", "applyMaps", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ApplyMap;", "applyObserver", "Lkotlin/Function2;", "", "Landroidx/compose/runtime/snapshots/Snapshot;", "applyUnsubscribe", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "currentMap", "isObserving", "", "isPaused", "readObserver", "callOnChanged", "clear", "scope", "clearIf", "predicate", "ensureMap", ExifInterface.GPS_DIRECTION_TRUE, "onChanged", "notifyChanges", "changes", "snapshot", "observeReads", "onValueChangedForScope", "block", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "start", "stop", "withNoObservations", "ApplyMap", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SnapshotStateObserver {
    public static final int $stable = 8;
    private final MutableVector<ApplyMap<?>> applyMaps;
    private final Function2<Set<? extends Object>, Snapshot, Unit> applyObserver;
    private ObserverHandle applyUnsubscribe;
    private ApplyMap<?> currentMap;
    private boolean isObserving;
    private boolean isPaused;
    private final Function1<Function0<Unit>, Unit> onChangedExecutor;
    private final Function1<Object, Unit> readObserver;

    /* JADX WARN: Multi-variable type inference failed */
    public SnapshotStateObserver(Function1<? super Function0<Unit>, Unit> onChangedExecutor) {
        Intrinsics.checkNotNullParameter(onChangedExecutor, "onChangedExecutor");
        this.onChangedExecutor = onChangedExecutor;
        this.applyObserver = new Function2<Set<? extends Object>, Snapshot, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$applyObserver$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Set<? extends Object> set, Snapshot snapshot) {
                invoke2(set, snapshot);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Set<? extends Object> set, Snapshot noName_1) {
                MutableVector mutableVector;
                MutableVector mutableVector2;
                boolean z;
                Function1 function1;
                Object[] objArr;
                int i;
                boolean z2;
                Object obj;
                int i2;
                int i3;
                Set<? extends Object> applied = set;
                Intrinsics.checkNotNullParameter(applied, "applied");
                Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
                mutableVector = SnapshotStateObserver.this.applyMaps;
                SnapshotStateObserver snapshotStateObserver = SnapshotStateObserver.this;
                synchronized (mutableVector) {
                    mutableVector2 = snapshotStateObserver.applyMaps;
                    int size = mutableVector2.getSize();
                    if (size > 0) {
                        Object[] content = mutableVector2.getContent();
                        int i4 = 0;
                        boolean z3 = false;
                        while (true) {
                            SnapshotStateObserver.ApplyMap applyMap = (SnapshotStateObserver.ApplyMap) content[i4];
                            HashSet<Object> invalidated = applyMap.getInvalidated();
                            IdentityScopeMap map = applyMap.getMap();
                            Iterator<? extends Object> it = applied.iterator();
                            while (it.hasNext()) {
                                int find = map.find(it.next());
                                if (find >= 0) {
                                    Iterator<T> it2 = map.scopeSetAt(find).iterator();
                                    while (it2.hasNext()) {
                                        invalidated.add(it2.next());
                                        z3 = true;
                                    }
                                }
                            }
                            if (invalidated.isEmpty()) {
                                objArr = content;
                                i = i4;
                                z2 = z3;
                            } else {
                                int size2 = map.getSize();
                                if (size2 > 0) {
                                    int i5 = 0;
                                    i2 = 0;
                                    while (true) {
                                        int i6 = i5 + 1;
                                        int i7 = map.getValueOrder()[i5];
                                        IdentityArraySet identityArraySet = map.getScopeSets()[i7];
                                        Intrinsics.checkNotNull(identityArraySet);
                                        obj = null;
                                        int size3 = identityArraySet.size();
                                        objArr = content;
                                        if (size3 > 0) {
                                            int i8 = 0;
                                            i3 = 0;
                                            while (true) {
                                                i = i4;
                                                int i9 = i8 + 1;
                                                z2 = z3;
                                                Object obj2 = identityArraySet.getValues()[i8];
                                                if (obj2 != null) {
                                                    if (!invalidated.contains(obj2)) {
                                                        if (i3 != i8) {
                                                            identityArraySet.getValues()[i3] = obj2;
                                                        }
                                                        i3++;
                                                    }
                                                    if (i9 >= size3) {
                                                        break;
                                                    }
                                                    i8 = i9;
                                                    i4 = i;
                                                    z3 = z2;
                                                } else {
                                                    throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                                }
                                            }
                                        } else {
                                            i = i4;
                                            z2 = z3;
                                            i3 = 0;
                                        }
                                        int size4 = identityArraySet.size();
                                        if (i3 < size4) {
                                            int i10 = i3;
                                            while (true) {
                                                int i11 = i10 + 1;
                                                identityArraySet.getValues()[i10] = null;
                                                if (i11 >= size4) {
                                                    break;
                                                } else {
                                                    i10 = i11;
                                                }
                                            }
                                        }
                                        identityArraySet.setSize(i3);
                                        if (identityArraySet.size() > 0) {
                                            if (i2 != i5) {
                                                int i12 = map.getValueOrder()[i2];
                                                map.getValueOrder()[i2] = i7;
                                                map.getValueOrder()[i5] = i12;
                                            }
                                            i2++;
                                        }
                                        if (i6 >= size2) {
                                            break;
                                        }
                                        i5 = i6;
                                        content = objArr;
                                        i4 = i;
                                        z3 = z2;
                                    }
                                } else {
                                    objArr = content;
                                    i = i4;
                                    z2 = z3;
                                    obj = null;
                                    i2 = 0;
                                }
                                int size5 = map.getSize();
                                if (i2 < size5) {
                                    int i13 = i2;
                                    while (true) {
                                        int i14 = i13 + 1;
                                        map.getValues()[map.getValueOrder()[i13]] = obj;
                                        if (i14 >= size5) {
                                            break;
                                        } else {
                                            i13 = i14;
                                        }
                                    }
                                }
                                map.setSize(i2);
                            }
                            i4 = i + 1;
                            if (i4 >= size) {
                                z = z2;
                                break;
                            } else {
                                applied = set;
                                content = objArr;
                                z3 = z2;
                            }
                        }
                    } else {
                        z = false;
                    }
                    Unit unit = Unit.INSTANCE;
                }
                if (z) {
                    function1 = SnapshotStateObserver.this.onChangedExecutor;
                    final SnapshotStateObserver snapshotStateObserver2 = SnapshotStateObserver.this;
                    function1.invoke(new Function0<Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$applyObserver$1.2
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            SnapshotStateObserver.this.callOnChanged();
                        }
                    });
                }
            }
        };
        this.readObserver = new Function1<Object, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$readObserver$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object state) {
                boolean z;
                MutableVector mutableVector;
                SnapshotStateObserver.ApplyMap applyMap;
                Intrinsics.checkNotNullParameter(state, "state");
                z = SnapshotStateObserver.this.isPaused;
                if (z) {
                    return;
                }
                mutableVector = SnapshotStateObserver.this.applyMaps;
                SnapshotStateObserver snapshotStateObserver = SnapshotStateObserver.this;
                synchronized (mutableVector) {
                    applyMap = snapshotStateObserver.currentMap;
                    Intrinsics.checkNotNull(applyMap);
                    applyMap.addValue(state);
                    Unit unit = Unit.INSTANCE;
                }
            }
        };
        this.applyMaps = new MutableVector<>(new ApplyMap[16], 0);
    }

    public final <T> void observeReads(T scope, Function1<? super T, Unit> onValueChangedForScope, Function0<Unit> block) {
        ApplyMap<?> ensureMap;
        ApplyMap<?> applyMap;
        boolean z;
        Object obj;
        Function1<Object, Unit> function1;
        int i;
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onValueChangedForScope, "onValueChangedForScope");
        Intrinsics.checkNotNullParameter(block, "block");
        ApplyMap<?> applyMap2 = this.currentMap;
        boolean z2 = this.isPaused;
        synchronized (this.applyMaps) {
            ensureMap = ensureMap(onValueChangedForScope);
        }
        Object currentScope = ensureMap.getCurrentScope();
        ensureMap.setCurrentScope(scope);
        this.currentMap = ensureMap;
        this.isPaused = false;
        if (!this.isObserving) {
            this.isObserving = true;
            try {
                synchronized (this.applyMaps) {
                    IdentityScopeMap<?> map = ensureMap.getMap();
                    int size = map.getSize();
                    if (size > 0) {
                        int i4 = 0;
                        i = 0;
                        while (true) {
                            int i5 = i4 + 1;
                            int i6 = map.getValueOrder()[i4];
                            IdentityArraySet<?> identityArraySet = map.getScopeSets()[i6];
                            Intrinsics.checkNotNull(identityArraySet);
                            function1 = null;
                            int size2 = identityArraySet.size();
                            if (size2 > 0) {
                                z = z2;
                                i2 = i6;
                                i3 = 0;
                                int i7 = 0;
                                while (true) {
                                    applyMap = ensureMap;
                                    int i8 = i7 + 1;
                                    obj = currentScope;
                                    Object obj2 = identityArraySet.getValues()[i7];
                                    if (obj2 == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                    }
                                    if (obj2 != scope) {
                                        if (i3 != i7) {
                                            identityArraySet.getValues()[i3] = obj2;
                                        }
                                        i3++;
                                    }
                                    if (i8 >= size2) {
                                        break;
                                    }
                                    i7 = i8;
                                    ensureMap = applyMap;
                                    currentScope = obj;
                                }
                            } else {
                                applyMap = ensureMap;
                                z = z2;
                                obj = currentScope;
                                i2 = i6;
                                i3 = 0;
                            }
                            int size3 = identityArraySet.size();
                            if (i3 < size3) {
                                int i9 = i3;
                                while (true) {
                                    int i10 = i9 + 1;
                                    identityArraySet.getValues()[i9] = null;
                                    if (i10 >= size3) {
                                        break;
                                    } else {
                                        i9 = i10;
                                    }
                                }
                            }
                            identityArraySet.setSize(i3);
                            if (identityArraySet.size() > 0) {
                                if (i != i4) {
                                    int i11 = map.getValueOrder()[i];
                                    map.getValueOrder()[i] = i2;
                                    map.getValueOrder()[i4] = i11;
                                }
                                i++;
                            }
                            if (i5 >= size) {
                                break;
                            }
                            i4 = i5;
                            z2 = z;
                            ensureMap = applyMap;
                            currentScope = obj;
                        }
                    } else {
                        applyMap = ensureMap;
                        z = z2;
                        obj = currentScope;
                        function1 = null;
                        i = 0;
                    }
                    int size4 = map.getSize();
                    if (i < size4) {
                        int i12 = i;
                        while (true) {
                            int i13 = i12 + 1;
                            map.getValues()[map.getValueOrder()[i12]] = function1;
                            if (i13 >= size4) {
                                break;
                            } else {
                                i12 = i13;
                            }
                        }
                    }
                    map.setSize(i);
                    Unit unit = Unit.INSTANCE;
                }
                Snapshot.INSTANCE.observe(this.readObserver, function1, block);
            } finally {
                this.isObserving = false;
            }
        } else {
            applyMap = ensureMap;
            z = z2;
            obj = currentScope;
            block.invoke();
        }
        this.currentMap = applyMap2;
        applyMap.setCurrentScope(obj);
        this.isPaused = z;
    }

    public final void withNoObservations(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        boolean z = this.isPaused;
        this.isPaused = true;
        try {
            block.invoke();
        } finally {
            this.isPaused = z;
        }
    }

    public final void clear(Object scope) {
        ApplyMap<?>[] applyMapArr;
        Object obj;
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(scope, "scope");
        synchronized (this.applyMaps) {
            MutableVector<ApplyMap<?>> mutableVector = this.applyMaps;
            int size = mutableVector.getSize();
            if (size > 0) {
                ApplyMap<?>[] content = mutableVector.getContent();
                int i3 = 0;
                while (true) {
                    IdentityScopeMap<?> map = content[i3].getMap();
                    int size2 = map.getSize();
                    if (size2 > 0) {
                        int i4 = 0;
                        i = 0;
                        while (true) {
                            int i5 = i4 + 1;
                            int i6 = map.getValueOrder()[i4];
                            IdentityArraySet<?> identityArraySet = map.getScopeSets()[i6];
                            Intrinsics.checkNotNull(identityArraySet);
                            int size3 = identityArraySet.size();
                            if (size3 > 0) {
                                int i7 = 0;
                                i2 = 0;
                                obj = null;
                                while (true) {
                                    int i8 = i7 + 1;
                                    applyMapArr = content;
                                    Object obj2 = identityArraySet.getValues()[i7];
                                    if (obj2 == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                    }
                                    if (obj2 != scope) {
                                        if (i2 != i7) {
                                            identityArraySet.getValues()[i2] = obj2;
                                        }
                                        i2++;
                                    }
                                    if (i8 >= size3) {
                                        break;
                                    }
                                    i7 = i8;
                                    content = applyMapArr;
                                }
                            } else {
                                applyMapArr = content;
                                obj = null;
                                i2 = 0;
                            }
                            int size4 = identityArraySet.size();
                            if (i2 < size4) {
                                int i9 = i2;
                                while (true) {
                                    int i10 = i9 + 1;
                                    identityArraySet.getValues()[i9] = obj;
                                    if (i10 >= size4) {
                                        break;
                                    } else {
                                        i9 = i10;
                                    }
                                }
                            }
                            identityArraySet.setSize(i2);
                            if (identityArraySet.size() > 0) {
                                if (i != i4) {
                                    int i11 = map.getValueOrder()[i];
                                    map.getValueOrder()[i] = i6;
                                    map.getValueOrder()[i4] = i11;
                                }
                                i++;
                            }
                            if (i5 >= size2) {
                                break;
                            }
                            i4 = i5;
                            content = applyMapArr;
                        }
                    } else {
                        applyMapArr = content;
                        obj = null;
                        i = 0;
                    }
                    int size5 = map.getSize();
                    if (i < size5) {
                        int i12 = i;
                        while (true) {
                            int i13 = i12 + 1;
                            map.getValues()[map.getValueOrder()[i12]] = obj;
                            if (i13 >= size5) {
                                break;
                            } else {
                                i12 = i13;
                            }
                        }
                    }
                    map.setSize(i);
                    i3++;
                    if (i3 >= size) {
                        break;
                    } else {
                        content = applyMapArr;
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clearIf(Function1<Object, Boolean> predicate) {
        ApplyMap<?>[] applyMapArr;
        Object obj;
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        synchronized (this.applyMaps) {
            MutableVector<ApplyMap<?>> mutableVector = this.applyMaps;
            int size = mutableVector.getSize();
            if (size > 0) {
                ApplyMap<?>[] content = mutableVector.getContent();
                int i3 = 0;
                while (true) {
                    IdentityScopeMap<?> map = content[i3].getMap();
                    int size2 = map.getSize();
                    if (size2 > 0) {
                        int i4 = 0;
                        i = 0;
                        while (true) {
                            int i5 = i4 + 1;
                            int i6 = map.getValueOrder()[i4];
                            IdentityArraySet<?> identityArraySet = map.getScopeSets()[i6];
                            Intrinsics.checkNotNull(identityArraySet);
                            int size3 = identityArraySet.size();
                            if (size3 > 0) {
                                int i7 = 0;
                                i2 = 0;
                                obj = null;
                                while (true) {
                                    int i8 = i7 + 1;
                                    applyMapArr = content;
                                    Object obj2 = identityArraySet.getValues()[i7];
                                    if (obj2 == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                    }
                                    if (!predicate.invoke(obj2).booleanValue()) {
                                        if (i2 != i7) {
                                            identityArraySet.getValues()[i2] = obj2;
                                        }
                                        i2++;
                                    }
                                    if (i8 >= size3) {
                                        break;
                                    }
                                    i7 = i8;
                                    content = applyMapArr;
                                }
                            } else {
                                applyMapArr = content;
                                obj = null;
                                i2 = 0;
                            }
                            int size4 = identityArraySet.size();
                            if (i2 < size4) {
                                int i9 = i2;
                                while (true) {
                                    int i10 = i9 + 1;
                                    identityArraySet.getValues()[i9] = obj;
                                    if (i10 >= size4) {
                                        break;
                                    } else {
                                        i9 = i10;
                                    }
                                }
                            }
                            identityArraySet.setSize(i2);
                            if (identityArraySet.size() > 0) {
                                if (i != i4) {
                                    int i11 = map.getValueOrder()[i];
                                    map.getValueOrder()[i] = i6;
                                    map.getValueOrder()[i4] = i11;
                                }
                                i++;
                            }
                            if (i5 >= size2) {
                                break;
                            }
                            i4 = i5;
                            content = applyMapArr;
                        }
                    } else {
                        applyMapArr = content;
                        obj = null;
                        i = 0;
                    }
                    int size5 = map.getSize();
                    if (i < size5) {
                        int i12 = i;
                        while (true) {
                            int i13 = i12 + 1;
                            map.getValues()[map.getValueOrder()[i12]] = obj;
                            if (i13 >= size5) {
                                break;
                            } else {
                                i12 = i13;
                            }
                        }
                    }
                    map.setSize(i);
                    i3++;
                    if (i3 >= size) {
                        break;
                    } else {
                        content = applyMapArr;
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void start() {
        this.applyUnsubscribe = Snapshot.INSTANCE.registerApplyObserver(this.applyObserver);
    }

    public final void stop() {
        ObserverHandle observerHandle = this.applyUnsubscribe;
        if (observerHandle == null) {
            return;
        }
        observerHandle.dispose();
    }

    public final void notifyChanges(Set<? extends Object> changes, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(changes, "changes");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.applyObserver.invoke(changes, snapshot);
    }

    public final void clear() {
        synchronized (this.applyMaps) {
            MutableVector<ApplyMap<?>> mutableVector = this.applyMaps;
            int size = mutableVector.getSize();
            if (size > 0) {
                ApplyMap<?>[] content = mutableVector.getContent();
                int i = 0;
                do {
                    content[i].getMap().clear();
                    i++;
                } while (i < size);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callOnChanged() {
        MutableVector<ApplyMap<?>> mutableVector = this.applyMaps;
        int size = mutableVector.getSize();
        if (size > 0) {
            ApplyMap<?>[] content = mutableVector.getContent();
            int i = 0;
            do {
                ApplyMap<?> applyMap = content[i];
                HashSet<Object> invalidated = applyMap.getInvalidated();
                HashSet<Object> hashSet = invalidated;
                if (!hashSet.isEmpty()) {
                    applyMap.callOnChanged(hashSet);
                    invalidated.clear();
                }
                i++;
            } while (i < size);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final <T> androidx.compose.runtime.snapshots.SnapshotStateObserver.ApplyMap<T> ensureMap(kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r6) {
        /*
            r5 = this;
            androidx.compose.runtime.collection.MutableVector<androidx.compose.runtime.snapshots.SnapshotStateObserver$ApplyMap<?>> r0 = r5.applyMaps
            int r1 = r0.getSize()
            r2 = -1
            if (r1 <= 0) goto L1d
            java.lang.Object[] r0 = r0.getContent()
            r3 = 0
        Le:
            r4 = r0[r3]
            androidx.compose.runtime.snapshots.SnapshotStateObserver$ApplyMap r4 = (androidx.compose.runtime.snapshots.SnapshotStateObserver.ApplyMap) r4
            kotlin.jvm.functions.Function1 r4 = r4.getOnChanged()
            if (r4 != r6) goto L19
            goto L1e
        L19:
            int r3 = r3 + 1
            if (r3 < r1) goto Le
        L1d:
            r3 = r2
        L1e:
            if (r3 != r2) goto L2b
            androidx.compose.runtime.snapshots.SnapshotStateObserver$ApplyMap r0 = new androidx.compose.runtime.snapshots.SnapshotStateObserver$ApplyMap
            r0.<init>(r6)
            androidx.compose.runtime.collection.MutableVector<androidx.compose.runtime.snapshots.SnapshotStateObserver$ApplyMap<?>> r6 = r5.applyMaps
            r6.add(r0)
            return r0
        L2b:
            androidx.compose.runtime.collection.MutableVector<androidx.compose.runtime.snapshots.SnapshotStateObserver$ApplyMap<?>> r6 = r5.applyMaps
            java.lang.Object[] r6 = r6.getContent()
            r6 = r6[r3]
            androidx.compose.runtime.snapshots.SnapshotStateObserver$ApplyMap r6 = (androidx.compose.runtime.snapshots.SnapshotStateObserver.ApplyMap) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ensureMap(kotlin.jvm.functions.Function1):androidx.compose.runtime.snapshots.SnapshotStateObserver$ApplyMap");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SnapshotStateObserver.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0002J\u0014\u0010\u001a\u001a\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001cR\u001e\u0010\u0007\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR!\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u000ej\b\u0012\u0004\u0012\u00020\u0002`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ApplyMap;", ExifInterface.GPS_DIRECTION_TRUE, "", "onChanged", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "currentScope", "getCurrentScope", "()Ljava/lang/Object;", "setCurrentScope", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "invalidated", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getInvalidated", "()Ljava/util/HashSet;", "map", "Landroidx/compose/runtime/collection/IdentityScopeMap;", "getMap", "()Landroidx/compose/runtime/collection/IdentityScopeMap;", "getOnChanged", "()Lkotlin/jvm/functions/Function1;", "addValue", "value", "callOnChanged", "scopes", "", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    static final class ApplyMap<T> {
        private T currentScope;
        private final HashSet<Object> invalidated;
        private final IdentityScopeMap<T> map;
        private final Function1<T, Unit> onChanged;

        /* JADX WARN: Multi-variable type inference failed */
        public ApplyMap(Function1<? super T, Unit> onChanged) {
            Intrinsics.checkNotNullParameter(onChanged, "onChanged");
            this.onChanged = onChanged;
            this.map = new IdentityScopeMap<>();
            this.invalidated = new HashSet<>();
        }

        public final Function1<T, Unit> getOnChanged() {
            return this.onChanged;
        }

        public final IdentityScopeMap<T> getMap() {
            return this.map;
        }

        public final HashSet<Object> getInvalidated() {
            return this.invalidated;
        }

        public final T getCurrentScope() {
            return this.currentScope;
        }

        public final void setCurrentScope(T t) {
            this.currentScope = t;
        }

        public final void addValue(Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            IdentityScopeMap<T> identityScopeMap = this.map;
            T t = this.currentScope;
            Intrinsics.checkNotNull(t);
            identityScopeMap.add(value, t);
        }

        public final void callOnChanged(Collection<? extends Object> scopes) {
            Intrinsics.checkNotNullParameter(scopes, "scopes");
            Iterator<T> it = scopes.iterator();
            while (it.hasNext()) {
                getOnChanged().invoke(it.next());
            }
        }
    }
}
