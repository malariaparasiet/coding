package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionData;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Composer.kt */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u0000 l2\u00020\u0001:\u0001lJ@\u0010+\u001a\u00020,\"\u0004\b\u0000\u0010-\"\u0004\b\u0001\u0010.2\u0006\u0010/\u001a\u0002H-2\u001d\u00100\u001a\u0019\u0012\u0004\u0012\u0002H.\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020,01¢\u0006\u0002\b2H'¢\u0006\u0002\u00103J\b\u00104\u001a\u000205H'J\u0012\u00106\u001a\u00020\u001c2\b\u0010/\u001a\u0004\u0018\u00010\u0001H'J\u0010\u00106\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\u001cH\u0017J\u0010\u00106\u001a\u00020\u001c2\u0006\u0010/\u001a\u000207H\u0017J\u0010\u00106\u001a\u00020\u001c2\u0006\u0010/\u001a\u000208H\u0017J\u0010\u00106\u001a\u00020\u001c2\u0006\u0010/\u001a\u000209H\u0017J\u0010\u00106\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020:H\u0017J\u0010\u00106\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\u0017H\u0017J\u0010\u00106\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020;H\u0017J\u0010\u00106\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020<H\u0017J\b\u0010=\u001a\u00020,H'J!\u0010>\u001a\u0002H.\"\u0004\b\u0000\u0010.2\f\u0010?\u001a\b\u0012\u0004\u0012\u0002H.0@H'¢\u0006\u0002\u0010AJ\u001c\u0010B\u001a\u00020,\"\u0004\b\u0000\u0010.2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002H.0DH'J\b\u0010E\u001a\u00020,H'J\b\u0010F\u001a\u00020,H'J\b\u0010G\u001a\u00020,H'J\b\u0010H\u001a\u00020,H'J\b\u0010I\u001a\u00020,H'J\b\u0010J\u001a\u00020,H'J\b\u0010K\u001a\u00020,H'J\n\u0010L\u001a\u0004\u0018\u00010MH'J\b\u0010N\u001a\u00020,H'J\u001c\u0010O\u001a\u00020\u00012\b\u0010P\u001a\u0004\u0018\u00010\u00012\b\u0010Q\u001a\u0004\u0018\u00010\u0001H'J\u0016\u0010R\u001a\u00020,2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020,0DH'J\u0010\u0010T\u001a\u00020,2\u0006\u0010U\u001a\u00020$H'J\n\u0010V\u001a\u0004\u0018\u00010\u0001H'J\b\u0010W\u001a\u00020,H'J\b\u0010X\u001a\u00020,H'J\u0010\u0010Y\u001a\u00020,2\u0006\u0010Y\u001a\u00020ZH&J\b\u0010[\u001a\u00020,H&J\u0018\u0010\\\u001a\u00020,2\u0006\u0010?\u001a\u00020\u00172\u0006\u0010Y\u001a\u00020ZH&J\b\u0010]\u001a\u00020,H'J\u001a\u0010^\u001a\u00020,2\u0006\u0010?\u001a\u00020\u00172\b\u0010_\u001a\u0004\u0018\u00010\u0001H'J\b\u0010`\u001a\u00020,H'J!\u0010a\u001a\u00020,2\u0012\u0010b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030d0cH'¢\u0006\u0002\u0010eJ\u0010\u0010f\u001a\u00020,2\u0006\u0010?\u001a\u00020\u0017H'J\u0010\u0010g\u001a\u00020\u00002\u0006\u0010?\u001a\u00020\u0017H'J\u001a\u0010h\u001a\u00020,2\u0006\u0010?\u001a\u00020\u00172\b\u0010_\u001a\u0004\u0018\u00010\u0001H'J\b\u0010i\u001a\u00020,H'J\u0012\u0010j\u001a\u00020,2\b\u0010/\u001a\u0004\u0018\u00010\u0001H'J\b\u0010k\u001a\u00020,H'R\u001e\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\t8fX§\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0005\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128&X§\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0005\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00178&X§\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\u0005\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001c8&X§\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u0005\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\u001c8&X§\u0004¢\u0006\f\u0012\u0004\b!\u0010\u0005\u001a\u0004\b\"\u0010\u001fR\u001c\u0010#\u001a\u0004\u0018\u00010$8&X§\u0004¢\u0006\f\u0012\u0004\b%\u0010\u0005\u001a\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\u001c8&X§\u0004¢\u0006\f\u0012\u0004\b)\u0010\u0005\u001a\u0004\b*\u0010\u001f¨\u0006m"}, d2 = {"Landroidx/compose/runtime/Composer;", "", "applier", "Landroidx/compose/runtime/Applier;", "getApplier$annotations", "()V", "getApplier", "()Landroidx/compose/runtime/Applier;", "applyCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getApplyCoroutineContext$annotations", "getApplyCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "composition", "Landroidx/compose/runtime/ControlledComposition;", "getComposition", "()Landroidx/compose/runtime/ControlledComposition;", "compositionData", "Landroidx/compose/runtime/tooling/CompositionData;", "getCompositionData$annotations", "getCompositionData", "()Landroidx/compose/runtime/tooling/CompositionData;", "compoundKeyHash", "", "getCompoundKeyHash$annotations", "getCompoundKeyHash", "()I", "defaultsInvalid", "", "getDefaultsInvalid$annotations", "getDefaultsInvalid", "()Z", "inserting", "getInserting$annotations", "getInserting", "recomposeScope", "Landroidx/compose/runtime/RecomposeScope;", "getRecomposeScope$annotations", "getRecomposeScope", "()Landroidx/compose/runtime/RecomposeScope;", "skipping", "getSkipping$annotations", "getSkipping", "apply", "", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.GPS_DIRECTION_TRUE, "value", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "buildContext", "Landroidx/compose/runtime/CompositionContext;", "changed", "", "", "", "", "", "", "collectParameterInformation", "consume", "key", "Landroidx/compose/runtime/CompositionLocal;", "(Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "createNode", "factory", "Lkotlin/Function0;", "disableReusing", "enableReusing", "endDefaults", "endMovableGroup", "endNode", "endProviders", "endReplaceableGroup", "endRestartGroup", "Landroidx/compose/runtime/ScopeUpdateScope;", "endReusableGroup", "joinKey", "left", "right", "recordSideEffect", "effect", "recordUsed", "scope", "rememberedValue", "skipCurrentGroup", "skipToGroupEnd", "sourceInformation", "", "sourceInformationMarkerEnd", "sourceInformationMarkerStart", "startDefaults", "startMovableGroup", "dataKey", "startNode", "startProviders", "values", "", "Landroidx/compose/runtime/ProvidedValue;", "([Landroidx/compose/runtime/ProvidedValue;)V", "startReplaceableGroup", "startRestartGroup", "startReusableGroup", "startReusableNode", "updateRememberedValue", "useNode", "Companion", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface Composer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @ComposeCompilerApi
    <V, T> void apply(V value, Function2<? super T, ? super V, Unit> block);

    @InternalComposeApi
    CompositionContext buildContext();

    @ComposeCompilerApi
    boolean changed(byte value);

    @ComposeCompilerApi
    boolean changed(char value);

    @ComposeCompilerApi
    boolean changed(double value);

    @ComposeCompilerApi
    boolean changed(float value);

    @ComposeCompilerApi
    boolean changed(int value);

    @ComposeCompilerApi
    boolean changed(long value);

    @ComposeCompilerApi
    boolean changed(Object value);

    @ComposeCompilerApi
    boolean changed(short value);

    @ComposeCompilerApi
    boolean changed(boolean value);

    @InternalComposeApi
    void collectParameterInformation();

    @InternalComposeApi
    <T> T consume(CompositionLocal<T> key);

    @ComposeCompilerApi
    <T> void createNode(Function0<? extends T> factory);

    @ComposeCompilerApi
    void disableReusing();

    @ComposeCompilerApi
    void enableReusing();

    @ComposeCompilerApi
    void endDefaults();

    @ComposeCompilerApi
    void endMovableGroup();

    @ComposeCompilerApi
    void endNode();

    @InternalComposeApi
    void endProviders();

    @ComposeCompilerApi
    void endReplaceableGroup();

    @ComposeCompilerApi
    ScopeUpdateScope endRestartGroup();

    @ComposeCompilerApi
    void endReusableGroup();

    Applier<?> getApplier();

    CoroutineContext getApplyCoroutineContext();

    ControlledComposition getComposition();

    CompositionData getCompositionData();

    int getCompoundKeyHash();

    boolean getDefaultsInvalid();

    boolean getInserting();

    RecomposeScope getRecomposeScope();

    boolean getSkipping();

    @ComposeCompilerApi
    Object joinKey(Object left, Object right);

    @InternalComposeApi
    void recordSideEffect(Function0<Unit> effect);

    @InternalComposeApi
    void recordUsed(RecomposeScope scope);

    @ComposeCompilerApi
    Object rememberedValue();

    @ComposeCompilerApi
    void skipCurrentGroup();

    @ComposeCompilerApi
    void skipToGroupEnd();

    void sourceInformation(String sourceInformation);

    void sourceInformationMarkerEnd();

    void sourceInformationMarkerStart(int key, String sourceInformation);

    @ComposeCompilerApi
    void startDefaults();

    @ComposeCompilerApi
    void startMovableGroup(int key, Object dataKey);

    @ComposeCompilerApi
    void startNode();

    @InternalComposeApi
    void startProviders(ProvidedValue<?>[] values);

    @ComposeCompilerApi
    void startReplaceableGroup(int key);

    @ComposeCompilerApi
    Composer startRestartGroup(int key);

    @ComposeCompilerApi
    void startReusableGroup(int key, Object dataKey);

    @ComposeCompilerApi
    void startReusableNode();

    @ComposeCompilerApi
    void updateRememberedValue(Object value);

    @ComposeCompilerApi
    void useNode();

    /* compiled from: Composer.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        @ComposeCompilerApi
        public static /* synthetic */ void getApplier$annotations() {
        }

        @InternalComposeApi
        public static /* synthetic */ void getApplyCoroutineContext$annotations() {
        }

        @InternalComposeApi
        public static /* synthetic */ void getCompositionData$annotations() {
        }

        @InternalComposeApi
        public static /* synthetic */ void getCompoundKeyHash$annotations() {
        }

        @ComposeCompilerApi
        public static /* synthetic */ void getDefaultsInvalid$annotations() {
        }

        @ComposeCompilerApi
        public static /* synthetic */ void getInserting$annotations() {
        }

        @InternalComposeApi
        public static /* synthetic */ void getRecomposeScope$annotations() {
        }

        @ComposeCompilerApi
        public static /* synthetic */ void getSkipping$annotations() {
        }

        @ComposeCompilerApi
        public static boolean changed(Composer composer, boolean z) {
            Intrinsics.checkNotNullParameter(composer, "this");
            return composer.changed(z);
        }

        @ComposeCompilerApi
        public static boolean changed(Composer composer, char c) {
            Intrinsics.checkNotNullParameter(composer, "this");
            return composer.changed(c);
        }

        @ComposeCompilerApi
        public static boolean changed(Composer composer, byte b) {
            Intrinsics.checkNotNullParameter(composer, "this");
            return composer.changed(b);
        }

        @ComposeCompilerApi
        public static boolean changed(Composer composer, short s) {
            Intrinsics.checkNotNullParameter(composer, "this");
            return composer.changed(s);
        }

        @ComposeCompilerApi
        public static boolean changed(Composer composer, int i) {
            Intrinsics.checkNotNullParameter(composer, "this");
            return composer.changed(i);
        }

        @ComposeCompilerApi
        public static boolean changed(Composer composer, float f) {
            Intrinsics.checkNotNullParameter(composer, "this");
            return composer.changed(f);
        }

        @ComposeCompilerApi
        public static boolean changed(Composer composer, long j) {
            Intrinsics.checkNotNullParameter(composer, "this");
            return composer.changed(j);
        }

        @ComposeCompilerApi
        public static boolean changed(Composer composer, double d) {
            Intrinsics.checkNotNullParameter(composer, "this");
            return composer.changed(d);
        }
    }

    /* compiled from: Composer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/runtime/Composer$Companion;", "", "()V", "Empty", "getEmpty", "()Ljava/lang/Object;", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Object Empty = new Object() { // from class: androidx.compose.runtime.Composer$Companion$Empty$1
            public String toString() {
                return "Empty";
            }
        };

        private Companion() {
        }

        public final Object getEmpty() {
            return Empty;
        }
    }
}
