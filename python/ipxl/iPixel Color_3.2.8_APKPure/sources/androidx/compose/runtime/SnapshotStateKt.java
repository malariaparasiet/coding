package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: SnapshotState.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\u001a \u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\f\u001a\u0012\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\n0\u000e\"\u0004\b\u0000\u0010\n\u001a+\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\n0\u000e\"\u0004\b\u0000\u0010\n2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\n0\u0010\"\u0002H\n¢\u0006\u0002\u0010\u0011\u001a\u001e\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u0013\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\u0015\u001aO\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u0013\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\u00152*\u0010\u0016\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u00030\u0010\"\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u0003¢\u0006\u0002\u0010\u0017\u001a/\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\n0\u0019\"\u0004\b\u0000\u0010\n2\u0006\u0010\u001a\u001a\u0002H\n2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\n0\u001c¢\u0006\u0002\u0010\u001d\u001a\u0012\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\n0\u001c\"\u0004\b\u0000\u0010\n\u001a.\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010 2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\u00052\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H 0\fH\u0082\b¢\u0006\u0002\u0010#\u001aj\u0010$\u001a\u00020\u0006\"\u0004\b\u0000\u0010 2%\u0010%\u001a!\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\t¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00060\u00042%\u0010(\u001a!\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\t¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00060\u00042\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H 0\fH\u0000\u001as\u0010)\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\u0006\u0010*\u001a\u0002H\n2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010,2\b\u0010.\u001a\u0004\u0018\u00010,2/\b\u0001\u0010/\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n01\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000602\u0012\u0006\u0012\u0004\u0018\u00010,00¢\u0006\u0002\b3H\u0007ø\u0001\u0000¢\u0006\u0002\u00104\u001ai\u0010)\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\u0006\u0010*\u001a\u0002H\n2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010,2/\b\u0001\u0010/\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n01\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000602\u0012\u0006\u0012\u0004\u0018\u00010,00¢\u0006\u0002\b3H\u0007ø\u0001\u0000¢\u0006\u0002\u00105\u001a_\u0010)\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\u0006\u0010*\u001a\u0002H\n2\b\u0010+\u001a\u0004\u0018\u00010,2/\b\u0001\u0010/\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n01\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000602\u0012\u0006\u0012\u0004\u0018\u00010,00¢\u0006\u0002\b3H\u0007ø\u0001\u0000¢\u0006\u0002\u00106\u001am\u0010)\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\u0006\u0010*\u001a\u0002H\n2\u0016\u00107\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010,0\u0010\"\u0004\u0018\u00010,2/\b\u0001\u0010/\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n01\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000602\u0012\u0006\u0012\u0004\u0018\u00010,00¢\u0006\u0002\b3H\u0007ø\u0001\u0000¢\u0006\u0002\u00108\u001aU\u0010)\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\u0006\u0010*\u001a\u0002H\n2/\b\u0001\u0010/\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n01\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000602\u0012\u0006\u0012\u0004\u0018\u00010,00¢\u0006\u0002\b3H\u0007ø\u0001\u0000¢\u0006\u0002\u00109\u001a\u0012\u0010:\u001a\b\u0012\u0004\u0012\u0002H\n0\u001c\"\u0004\b\u0000\u0010\n\u001a!\u0010;\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\u0006\u0010<\u001a\u0002H\nH\u0007¢\u0006\u0002\u0010=\u001a \u0010>\u001a\b\u0012\u0004\u0012\u0002H\n0?\"\u0004\b\u0000\u0010\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\n0\f\u001a\u0012\u0010@\u001a\b\u0012\u0004\u0012\u0002H\n0\u001c\"\u0004\b\u0000\u0010\n\u001a?\u0010A\u001a\b\u0012\u0004\u0012\u0002H 0\t\"\b\b\u0000\u0010\n*\u0002H \"\u0004\b\u0001\u0010 *\b\u0012\u0004\u0012\u0002H\n0?2\u0006\u0010B\u001a\u0002H 2\b\b\u0002\u0010C\u001a\u00020DH\u0007¢\u0006\u0002\u0010E\u001a-\u0010A\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0F2\b\b\u0002\u0010C\u001a\u00020DH\u0007¢\u0006\u0002\u0010G\u001a4\u0010H\u001a\u0002H\n\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\t2\b\u0010I\u001a\u0004\u0018\u00010,2\n\u0010J\u001a\u0006\u0012\u0002\b\u00030KH\u0086\n¢\u0006\u0002\u0010L\u001a&\u0010M\u001a\u00020N\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0O2\f\u0010P\u001a\b\u0012\u0004\u0012\u0002H\n0OH\u0002\u001a<\u0010Q\u001a\u00020\u0006\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00192\b\u0010I\u001a\u0004\u0018\u00010,2\n\u0010J\u001a\u0006\u0012\u0002\b\u00030K2\u0006\u0010\u001a\u001a\u0002H\nH\u0086\n¢\u0006\u0002\u0010R\u001a\u001c\u0010S\u001a\b\u0012\u0004\u0012\u0002H\n0\u000e\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0T\u001a4\u0010U\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u0013\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\u0015*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u00030V\"J\u0010\u0000\u001a>\u0012:\u00128\u00124\u00122\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003j\u0002`\u00070\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*d\b\u0002\u0010W\".\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u00032.\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006X"}, d2 = {"derivedStateObservers", "Landroidx/compose/runtime/SnapshotThreadLocal;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "Lkotlin/Pair;", "Lkotlin/Function1;", "Landroidx/compose/runtime/DerivedState;", "", "Landroidx/compose/runtime/DerivedStateObservers;", "derivedStateOf", "Landroidx/compose/runtime/State;", ExifInterface.GPS_DIRECTION_TRUE, "calculation", "Lkotlin/Function0;", "mutableStateListOf", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "elements", "", "([Ljava/lang/Object;)Landroidx/compose/runtime/snapshots/SnapshotStateList;", "mutableStateMapOf", "Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "pairs", "([Lkotlin/Pair;)Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "mutableStateOf", "Landroidx/compose/runtime/MutableState;", "value", "policy", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "(Ljava/lang/Object;Landroidx/compose/runtime/SnapshotMutationPolicy;)Landroidx/compose/runtime/MutableState;", "neverEqualPolicy", "notifyObservers", "R", "derivedState", "block", "(Landroidx/compose/runtime/DerivedState;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "observeDerivedStateRecalculations", "start", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "done", "produceState", "initialValue", "key1", "", "key2", "key3", "producer", "Lkotlin/Function2;", "Landroidx/compose/runtime/ProduceStateScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "keys", "(Ljava/lang/Object;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "referentialEqualityPolicy", "rememberUpdatedState", "newValue", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "snapshotFlow", "Lkotlinx/coroutines/flow/Flow;", "structuralEqualityPolicy", "collectAsState", "initial", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Lkotlinx/coroutines/flow/StateFlow;", "(Lkotlinx/coroutines/flow/StateFlow;Lkotlin/coroutines/CoroutineContext;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "getValue", "thisObj", "property", "Lkotlin/reflect/KProperty;", "(Landroidx/compose/runtime/State;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "intersects", "", "", "other", "setValue", "(Landroidx/compose/runtime/MutableState;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "toMutableStateList", "", "toMutableStateMap", "", "DerivedStateObservers", "runtime_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SnapshotStateKt {
    private static final SnapshotThreadLocal<PersistentList<Pair<Function1<DerivedState<?>, Unit>, Function1<DerivedState<?>, Unit>>>> derivedStateObservers = new SnapshotThreadLocal<>();

    public static /* synthetic */ MutableState mutableStateOf$default(Object obj, SnapshotMutationPolicy snapshotMutationPolicy, int i, Object obj2) {
        if ((i & 2) != 0) {
            snapshotMutationPolicy = structuralEqualityPolicy();
        }
        return mutableStateOf(obj, snapshotMutationPolicy);
    }

    public static final <T> MutableState<T> mutableStateOf(T t, SnapshotMutationPolicy<T> policy) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        return ActualAndroid_androidKt.createSnapshotMutableState(t, policy);
    }

    public static final <T> T getValue(State<? extends T> state, Object obj, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(state, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        return state.getValue();
    }

    public static final <T> void setValue(MutableState<T> mutableState, Object obj, KProperty<?> property, T t) {
        Intrinsics.checkNotNullParameter(mutableState, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        mutableState.setValue(t);
    }

    public static final <T> SnapshotMutationPolicy<T> referentialEqualityPolicy() {
        return ReferentialEqualityPolicy.INSTANCE;
    }

    public static final <T> SnapshotMutationPolicy<T> structuralEqualityPolicy() {
        return StructuralEqualityPolicy.INSTANCE;
    }

    public static final <T> SnapshotMutationPolicy<T> neverEqualPolicy() {
        return NeverEqualPolicy.INSTANCE;
    }

    public static final <T> SnapshotStateList<T> mutableStateListOf() {
        return new SnapshotStateList<>();
    }

    public static final <T> SnapshotStateList<T> mutableStateListOf(T... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        SnapshotStateList<T> snapshotStateList = new SnapshotStateList<>();
        snapshotStateList.addAll(ArraysKt.toList(elements));
        return snapshotStateList;
    }

    public static final <T> SnapshotStateList<T> toMutableStateList(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        SnapshotStateList<T> snapshotStateList = new SnapshotStateList<>();
        snapshotStateList.addAll(collection);
        return snapshotStateList;
    }

    public static final <K, V> SnapshotStateMap<K, V> mutableStateMapOf() {
        return new SnapshotStateMap<>();
    }

    public static final <K, V> SnapshotStateMap<K, V> mutableStateMapOf(Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        SnapshotStateMap<K, V> snapshotStateMap = new SnapshotStateMap<>();
        snapshotStateMap.putAll(MapsKt.toMap(pairs));
        return snapshotStateMap;
    }

    public static final <K, V> SnapshotStateMap<K, V> toMutableStateMap(Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        SnapshotStateMap<K, V> snapshotStateMap = new SnapshotStateMap<>();
        snapshotStateMap.putAll(MapsKt.toMap(iterable));
        return snapshotStateMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e A[LOOP:1: B:14:0x003e->B:16:0x0052, LOOP_START, PHI: r2
      0x003e: PHI (r2v7 int) = (r2v0 int), (r2v12 int) binds: [B:13:0x003c, B:16:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final <R> R notifyObservers(androidx.compose.runtime.DerivedState<?> r5, kotlin.jvm.functions.Function0<? extends R> r6) {
        /*
            androidx.compose.runtime.SnapshotThreadLocal r0 = access$getDerivedStateObservers$p()
            java.lang.Object r0 = r0.get()
            androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList r0 = (androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList) r0
            if (r0 != 0) goto L10
            androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList r0 = androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt.persistentListOf()
        L10:
            java.util.List r0 = (java.util.List) r0
            int r1 = r0.size()
            int r1 = r1 + (-1)
            r2 = 0
            if (r1 < 0) goto L32
            r3 = r2
        L1c:
            int r4 = r3 + 1
            java.lang.Object r3 = r0.get(r3)
            kotlin.Pair r3 = (kotlin.Pair) r3
            java.lang.Object r3 = r3.component1()
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            r3.invoke(r5)
            if (r4 <= r1) goto L30
            goto L32
        L30:
            r3 = r4
            goto L1c
        L32:
            java.lang.Object r6 = r6.invoke()     // Catch: java.lang.Throwable -> L55
            int r1 = r0.size()
            int r1 = r1 + (-1)
            if (r1 < 0) goto L54
        L3e:
            int r3 = r2 + 1
            java.lang.Object r2 = r0.get(r2)
            kotlin.Pair r2 = (kotlin.Pair) r2
            java.lang.Object r2 = r2.component2()
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            r2.invoke(r5)
            if (r3 <= r1) goto L52
            goto L54
        L52:
            r2 = r3
            goto L3e
        L54:
            return r6
        L55:
            r6 = move-exception
            int r1 = r0.size()
            int r1 = r1 + (-1)
            if (r1 < 0) goto L73
        L5e:
            int r3 = r2 + 1
            java.lang.Object r2 = r0.get(r2)
            kotlin.Pair r2 = (kotlin.Pair) r2
            java.lang.Object r2 = r2.component2()
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            r2.invoke(r5)
            if (r3 > r1) goto L73
            r2 = r3
            goto L5e
        L73:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt.notifyObservers(androidx.compose.runtime.DerivedState, kotlin.jvm.functions.Function0):java.lang.Object");
    }

    public static final <T> State<T> derivedStateOf(Function0<? extends T> calculation) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        return new DerivedSnapshotState(calculation);
    }

    public static final <R> void observeDerivedStateRecalculations(Function1<? super State<?>, Unit> start, Function1<? super State<?>, Unit> done, Function0<? extends R> block) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(done, "done");
        Intrinsics.checkNotNullParameter(block, "block");
        SnapshotThreadLocal<PersistentList<Pair<Function1<DerivedState<?>, Unit>, Function1<DerivedState<?>, Unit>>>> snapshotThreadLocal = derivedStateObservers;
        PersistentList<Pair<Function1<DerivedState<?>, Unit>, Function1<DerivedState<?>, Unit>>> persistentList = snapshotThreadLocal.get();
        try {
            PersistentList<Pair<Function1<DerivedState<?>, Unit>, Function1<DerivedState<?>, Unit>>> persistentList2 = snapshotThreadLocal.get();
            if (persistentList2 == null) {
                persistentList2 = ExtensionsKt.persistentListOf();
            }
            snapshotThreadLocal.set(persistentList2.add((PersistentList<Pair<Function1<DerivedState<?>, Unit>, Function1<DerivedState<?>, Unit>>>) TuplesKt.to(start, done)));
            block.invoke();
            snapshotThreadLocal.set(persistentList);
        } catch (Throwable th) {
            derivedStateObservers.set(persistentList);
            throw th;
        }
    }

    public static final <T> State<T> produceState(T t, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870515065);
        ComposerKt.sourceInformation(composer, "C(produceState)599@21697L41,600@21743L95:SnapshotState.kt#9igjgp");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) rememberedValue;
        EffectsKt.LaunchedEffect(Unit.INSTANCE, new SnapshotStateKt$produceState$1(producer, mutableState, null), composer, 0);
        composer.endReplaceableGroup();
        return mutableState;
    }

    public static final <T> State<T> produceState(T t, Object obj, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870513751);
        ComposerKt.sourceInformation(composer, "C(produceState)632@23027L41,633@23073L95:SnapshotState.kt#9igjgp");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) rememberedValue;
        EffectsKt.LaunchedEffect(obj, new SnapshotStateKt$produceState$2(producer, mutableState, null), composer, 8);
        composer.endReplaceableGroup();
        return mutableState;
    }

    public static final <T> State<T> produceState(T t, Object obj, Object obj2, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870512401);
        ComposerKt.sourceInformation(composer, "C(produceState)666@24393L41,667@24439L101:SnapshotState.kt#9igjgp");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) rememberedValue;
        EffectsKt.LaunchedEffect(obj, obj2, new SnapshotStateKt$produceState$3(producer, mutableState, null), composer, 72);
        composer.endReplaceableGroup();
        return mutableState;
    }

    public static final <T> State<T> produceState(T t, Object obj, Object obj2, Object obj3, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870511014);
        ComposerKt.sourceInformation(composer, "C(produceState)701@25796L41,702@25842L107:SnapshotState.kt#9igjgp");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) rememberedValue;
        EffectsKt.LaunchedEffect(obj, obj2, obj3, new SnapshotStateKt$produceState$4(producer, mutableState, null), composer, 584);
        composer.endReplaceableGroup();
        return mutableState;
    }

    public static final <T> State<T> produceState(T t, Object[] keys, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870509641);
        ComposerKt.sourceInformation(composer, "C(produceState)734@27144L41,736@27260L102:SnapshotState.kt#9igjgp");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) rememberedValue;
        EffectsKt.LaunchedEffect(Arrays.copyOf(keys, keys.length), (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) new SnapshotStateKt$produceState$5(producer, mutableState, null), composer, 8);
        composer.endReplaceableGroup();
        return mutableState;
    }

    public static final <T> State<T> rememberUpdatedState(T t, Composer composer, int i) {
        composer.startReplaceableGroup(-1519447800);
        ComposerKt.sourceInformation(composer, "C(rememberUpdatedState)*764@28632L41:SnapshotState.kt#9igjgp");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) rememberedValue;
        mutableState.setValue(t);
        composer.endReplaceableGroup();
        return mutableState;
    }

    public static final <T> State<T> collectAsState(StateFlow<? extends T> stateFlow, CoroutineContext coroutineContext, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(stateFlow, "<this>");
        composer.startReplaceableGroup(2062153999);
        ComposerKt.sourceInformation(composer, "C(collectAsState)781@29252L30:SnapshotState.kt#9igjgp");
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        State<T> collectAsState = collectAsState(stateFlow, stateFlow.getValue(), coroutineContext, composer, 520, 0);
        composer.endReplaceableGroup();
        return collectAsState;
    }

    public static final <T extends R, R> State<R> collectAsState(Flow<? extends T> flow, R r, CoroutineContext coroutineContext, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        composer.startReplaceableGroup(2062154523);
        ComposerKt.sourceInformation(composer, "C(collectAsState)P(1)796@29794L186:SnapshotState.kt#9igjgp");
        if ((i2 & 2) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        CoroutineContext coroutineContext2 = coroutineContext;
        int i3 = i >> 3;
        State<R> produceState = produceState(r, flow, coroutineContext2, new SnapshotStateKt$collectAsState$1(coroutineContext2, flow, null), composer, (i3 & 8) | 576 | (i3 & 14));
        composer.endReplaceableGroup();
        return produceState;
    }

    public static final <T> Flow<T> snapshotFlow(Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return FlowKt.flow(new SnapshotStateKt$snapshotFlow$1(block, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> boolean intersects(Set<? extends T> set, Set<? extends T> set2) {
        if (set.size() < set2.size()) {
            Set<? extends T> set3 = set;
            if ((set3 instanceof Collection) && set3.isEmpty()) {
                return false;
            }
            Iterator<T> it = set3.iterator();
            while (it.hasNext()) {
                if (set2.contains(it.next())) {
                    return true;
                }
            }
            return false;
        }
        Set<? extends T> set4 = set2;
        if ((set4 instanceof Collection) && set4.isEmpty()) {
            return false;
        }
        Iterator<T> it2 = set4.iterator();
        while (it2.hasNext()) {
            if (set.contains(it2.next())) {
                return true;
            }
        }
        return false;
    }
}
