package androidx.compose.runtime;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Composables.kt */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ab\u0010\f\u001a\u00020\r\"\b\b\u0000\u0010\u000e*\u00020\u000f\"\u000e\b\u0001\u0010\u0010\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00112\u000e\b\b\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\"\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0016\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u0019\u001aq\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e\"\u000e\b\u0001\u0010\u0010\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00112\u000e\b\b\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\"\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0016\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\u0002\b\u001bH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001c\u001a\u0097\u0001\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e\"\u000e\b\u0001\u0010\u0010\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00112\u000e\b\b\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\"\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0016\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182$\b\b\u0010\u001d\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u001e\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u001b¢\u0006\u0002\b\u00182\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\u0002\b\u001bH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001f\u001ab\u0010 \u001a\u00020\r\"\b\b\u0000\u0010\u000e*\u00020\u000f\"\u000e\b\u0001\u0010\u0010\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00112\u000e\b\b\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\"\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0016\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u0019\u001aq\u0010 \u001a\u00020\r\"\u0004\b\u0000\u0010\u000e\"\u000e\b\u0001\u0010\u0010\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00112\u000e\b\b\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\"\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0016\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\u0002\b\u001bH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001c\u001a\u0097\u0001\u0010 \u001a\u00020\r\"\u0004\b\u0000\u0010\u000e\"\u000e\b\u0001\u0010\u0010\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00112\u000e\b\b\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\"\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0016\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182$\b\b\u0010\u001d\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u001e\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u001b¢\u0006\u0002\b\u00182\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\u0002\b\u001bH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001f\u001a.\u0010!\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010\u000f2\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\u0002\b\u001bH\u0087\bø\u0001\u0001¢\u0006\u0002\u0010#\u001a\b\u0010$\u001a\u00020\rH\u0001\u001aB\u0010\"\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\u0016\u0010%\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000f0&\"\u0004\u0018\u00010\u000f2\u0011\u0010'\u001a\r\u0012\u0004\u0012\u0002H\u000e0\u0013¢\u0006\u0002\b\u001bH\u0087\bø\u0001\u0001¢\u0006\u0002\u0010(\u001a*\u0010)\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\u0011\u0010*\u001a\r\u0012\u0004\u0012\u0002H\u000e0\u0013¢\u0006\u0002\b\u0017H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010+\u001a4\u0010)\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\b\u0010,\u001a\u0004\u0018\u00010\u000f2\u0011\u0010*\u001a\r\u0012\u0004\u0012\u0002H\u000e0\u0013¢\u0006\u0002\b\u0017H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010-\u001a>\u0010)\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\b\u0010,\u001a\u0004\u0018\u00010\u000f2\b\u0010.\u001a\u0004\u0018\u00010\u000f2\u0011\u0010*\u001a\r\u0012\u0004\u0012\u0002H\u000e0\u0013¢\u0006\u0002\b\u0017H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010/\u001aH\u0010)\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\b\u0010,\u001a\u0004\u0018\u00010\u000f2\b\u0010.\u001a\u0004\u0018\u00010\u000f2\b\u00100\u001a\u0004\u0018\u00010\u000f2\u0011\u0010*\u001a\r\u0012\u0004\u0012\u0002H\u000e0\u0013¢\u0006\u0002\b\u0017H\u0087\bø\u0001\u0001¢\u0006\u0002\u00101\u001aB\u0010)\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\u0016\u0010%\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000f0&\"\u0004\u0018\u00010\u000f2\u0011\u0010*\u001a\r\u0012\u0004\u0012\u0002H\u000e0\u0013¢\u0006\u0002\b\u0017H\u0087\bø\u0001\u0001¢\u0006\u0002\u00102\u001a\r\u00103\u001a\u000204H\u0007¢\u0006\u0002\u00105\"\u0011\u0010\u0000\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\"\u0011\u0010\b\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u00066"}, d2 = {"currentComposer", "Landroidx/compose/runtime/Composer;", "getCurrentComposer", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/Composer;", "currentCompositeKeyHash", "", "getCurrentCompositeKeyHash", "(Landroidx/compose/runtime/Composer;I)I", "currentRecomposeScope", "Landroidx/compose/runtime/RecomposeScope;", "getCurrentRecomposeScope", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/RecomposeScope;", "ComposeNode", "", ExifInterface.GPS_DIRECTION_TRUE, "", ExifInterface.LONGITUDE_EAST, "Landroidx/compose/runtime/Applier;", "factory", "Lkotlin/Function0;", "update", "Lkotlin/Function1;", "Landroidx/compose/runtime/Updater;", "Landroidx/compose/runtime/DisallowComposableCalls;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "skippableUpdate", "Landroidx/compose/runtime/SkippableUpdater;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ReusableComposeNode", "ReusableContent", "key", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "invalidApplier", "keys", "", "block", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "remember", "calculation", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "key1", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "key2", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "key3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "rememberCompositionContext", "Landroidx/compose/runtime/CompositionContext;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/CompositionContext;", "runtime_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ComposablesKt {
    public static final <T> T remember(Object obj, Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean changed = composer.changed(obj);
        T t = (T) composer.rememberedValue();
        if (changed || t == Composer.INSTANCE.getEmpty()) {
            t = calculation.invoke();
            composer.updateRememberedValue(t);
        }
        composer.endReplaceableGroup();
        return t;
    }

    public static final <T> T remember(Object obj, Object obj2, Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3686552);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2):Composables.kt#9igjgp");
        boolean changed = composer.changed(obj) | composer.changed(obj2);
        T t = (T) composer.rememberedValue();
        if (changed || t == Composer.INSTANCE.getEmpty()) {
            t = calculation.invoke();
            composer.updateRememberedValue(t);
        }
        composer.endReplaceableGroup();
        return t;
    }

    public static final <T> T remember(Object obj, Object obj2, Object obj3, Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3686095);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean changed = composer.changed(obj) | composer.changed(obj2) | composer.changed(obj3);
        T t = (T) composer.rememberedValue();
        if (changed || t == Composer.INSTANCE.getEmpty()) {
            t = calculation.invoke();
            composer.updateRememberedValue(t);
        }
        composer.endReplaceableGroup();
        return t;
    }

    public static final <T> T remember(Object[] keys, Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3685570);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        int length = keys.length;
        int i2 = 0;
        boolean z = false;
        while (i2 < length) {
            Object obj = keys[i2];
            i2++;
            z |= composer.changed(obj);
        }
        T t = (T) composer.rememberedValue();
        if (z || t == Composer.INSTANCE.getEmpty()) {
            t = calculation.invoke();
            composer.updateRememberedValue(t);
        }
        composer.endReplaceableGroup();
        return t;
    }

    public static final <T> T key(Object[] keys, Function2<? super Composer, ? super Integer, ? extends T> block, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Intrinsics.checkNotNullParameter(block, "block");
        composer.startReplaceableGroup(-1542330587);
        ComposerKt.sourceInformation(composer, "C(key)P(1)128@4598L7:Composables.kt#9igjgp");
        T invoke = block.invoke(composer, Integer.valueOf((i >> 3) & 14));
        composer.endReplaceableGroup();
        return invoke;
    }

    public static final void ReusableContent(Object obj, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(-1530021272);
        ComposerKt.sourceInformation(composer, "C(ReusableContent)P(1)145@5253L9:Composables.kt#9igjgp");
        composer.startReusableGroup(ComposerKt.reuseKey, obj);
        content.invoke(composer, Integer.valueOf((i >> 3) & 14));
        composer.endReusableGroup();
        composer.endReplaceableGroup();
    }

    public static final Composer getCurrentComposer(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 756186890, "C:Composables.kt#9igjgp");
        throw new NotImplementedError("Implemented as an intrinsic");
    }

    public static final RecomposeScope getCurrentRecomposeScope(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 142554597, "C:Composables.kt#9igjgp");
        RecomposeScope recomposeScope = composer.getRecomposeScope();
        if (recomposeScope == null) {
            throw new IllegalStateException("no recompose scope found".toString());
        }
        composer.recordUsed(recomposeScope);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return recomposeScope;
    }

    public static final int getCurrentCompositeKeyHash(Composer composer, int i) {
        return composer.getCompoundKeyHash();
    }

    public static final /* synthetic */ <T, E extends Applier<?>> void ComposeNode(final Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        composer.startReplaceableGroup(-2103251527);
        ComposerKt.sourceInformation(composer, "C(ComposeNode):Composables.kt#9igjgp");
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, ExifInterface.LONGITUDE_EAST);
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startNode();
        if (composer.getInserting()) {
            composer.createNode(new Function0<T>() { // from class: androidx.compose.runtime.ComposablesKt$ComposeNode$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final T invoke() {
                    return factory.invoke();
                }
            });
        } else {
            composer.useNode();
        }
        update.invoke(Updater.m346boximpl(Updater.m347constructorimpl(composer)));
        composer.endNode();
        composer.endReplaceableGroup();
    }

    public static final /* synthetic */ <T, E extends Applier<?>> void ReusableComposeNode(final Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        composer.startReplaceableGroup(1546164280);
        ComposerKt.sourceInformation(composer, "C(ReusableComposeNode):Composables.kt#9igjgp");
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, ExifInterface.LONGITUDE_EAST);
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(new Function0<T>() { // from class: androidx.compose.runtime.ComposablesKt$ReusableComposeNode$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final T invoke() {
                    return factory.invoke();
                }
            });
        } else {
            composer.useNode();
        }
        composer.disableReusing();
        update.invoke(Updater.m346boximpl(Updater.m347constructorimpl(composer)));
        composer.enableReusing();
        composer.endNode();
        composer.endReplaceableGroup();
    }

    public static final /* synthetic */ <T, E extends Applier<?>> void ComposeNode(Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(-2103248778);
        ComposerKt.sourceInformation(composer, "C(ComposeNode)P(1,2)292@10549L9:Composables.kt#9igjgp");
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, ExifInterface.LONGITUDE_EAST);
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startNode();
        if (composer.getInserting()) {
            composer.createNode(factory);
        } else {
            composer.useNode();
        }
        update.invoke(Updater.m346boximpl(Updater.m347constructorimpl(composer)));
        content.invoke(composer, Integer.valueOf((i >> 6) & 14));
        composer.endNode();
        composer.endReplaceableGroup();
    }

    public static final /* synthetic */ <T, E extends Applier<?>> void ReusableComposeNode(Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(1546167211);
        ComposerKt.sourceInformation(composer, "C(ReusableComposeNode)P(1,2)334@12088L9:Composables.kt#9igjgp");
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, ExifInterface.LONGITUDE_EAST);
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(factory);
        } else {
            composer.useNode();
        }
        composer.disableReusing();
        update.invoke(Updater.m346boximpl(Updater.m347constructorimpl(composer)));
        composer.enableReusing();
        content.invoke(composer, Integer.valueOf((i >> 6) & 14));
        composer.endNode();
        composer.endReplaceableGroup();
    }

    public static final /* synthetic */ <T, E extends Applier<?>> void ComposeNode(Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Function3<? super SkippableUpdater<T>, ? super Composer, ? super Integer, Unit> skippableUpdate, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(skippableUpdate, "skippableUpdate");
        Intrinsics.checkNotNullParameter(content, "content");
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, ExifInterface.LONGITUDE_EAST);
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startNode();
        if (composer.getInserting()) {
            composer.createNode(factory);
        } else {
            composer.useNode();
        }
        update.invoke(Updater.m346boximpl(Updater.m347constructorimpl(composer)));
        skippableUpdate.invoke(SkippableUpdater.m338boximpl(SkippableUpdater.m339constructorimpl(composer)), composer, Integer.valueOf((i >> 3) & 112));
        composer.startReplaceableGroup(2058660585);
        content.invoke(composer, Integer.valueOf((i >> 9) & 14));
        composer.endReplaceableGroup();
        composer.endNode();
    }

    public static final /* synthetic */ <T, E extends Applier<?>> void ReusableComposeNode(Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Function3<? super SkippableUpdater<T>, ? super Composer, ? super Integer, Unit> skippableUpdate, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(skippableUpdate, "skippableUpdate");
        Intrinsics.checkNotNullParameter(content, "content");
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, ExifInterface.LONGITUDE_EAST);
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(factory);
        } else {
            composer.useNode();
        }
        composer.disableReusing();
        update.invoke(Updater.m346boximpl(Updater.m347constructorimpl(composer)));
        composer.enableReusing();
        skippableUpdate.invoke(SkippableUpdater.m338boximpl(SkippableUpdater.m339constructorimpl(composer)), composer, Integer.valueOf((i >> 3) & 112));
        composer.startReplaceableGroup(2058660585);
        content.invoke(composer, Integer.valueOf((i >> 9) & 14));
        composer.endReplaceableGroup();
        composer.endNode();
    }

    public static final void invalidApplier() {
        throw new IllegalStateException("Invalid applier".toString());
    }

    public static final CompositionContext rememberCompositionContext(Composer composer, int i) {
        composer.startReplaceableGroup(-1359198498);
        ComposerKt.sourceInformation(composer, "C(rememberCompositionContext):Composables.kt#9igjgp");
        CompositionContext buildContext = composer.buildContext();
        composer.endReplaceableGroup();
        return buildContext;
    }

    public static final <T> T remember(Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        T t = (T) composer.rememberedValue();
        if (t == Composer.INSTANCE.getEmpty()) {
            t = calculation.invoke();
            composer.updateRememberedValue(t);
        }
        composer.endReplaceableGroup();
        return t;
    }
}
