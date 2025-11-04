package androidx.compose.ui.input.nestedscroll;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: NestedScrollModifier.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0006"}, d2 = {"nestedScroll", "Landroidx/compose/ui/Modifier;", "connection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "dispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class NestedScrollModifierKt {
    public static /* synthetic */ Modifier nestedScroll$default(Modifier modifier, NestedScrollConnection nestedScrollConnection, NestedScrollDispatcher nestedScrollDispatcher, int i, Object obj) {
        if ((i & 2) != 0) {
            nestedScrollDispatcher = null;
        }
        return nestedScroll(modifier, nestedScrollConnection, nestedScrollDispatcher);
    }

    public static final Modifier nestedScroll(Modifier modifier, final NestedScrollConnection connection, final NestedScrollDispatcher nestedScrollDispatcher) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(connection, "connection");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt$nestedScroll$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("nestedScroll");
                inspectorInfo.getProperties().set("connection", NestedScrollConnection.this);
                inspectorInfo.getProperties().set("dispatcher", nestedScrollDispatcher);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt$nestedScroll$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(100476458);
                ComposerKt.sourceInformation(composer, "C354@16076L24,357@16227L331:NestedScrollModifier.kt#kpqmsf");
                composer.startReplaceableGroup(-723524056);
                ComposerKt.sourceInformation(composer, "C(rememberCoroutineScope)475@19849L144:Effects.kt#9igjgp");
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object rememberedValue = composer.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                    composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                    rememberedValue = compositionScopedCoroutineScopeCanceller;
                }
                composer.endReplaceableGroup();
                final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                composer.endReplaceableGroup();
                final NestedScrollDispatcher nestedScrollDispatcher2 = NestedScrollDispatcher.this;
                if (nestedScrollDispatcher2 == null) {
                    composer.startReplaceableGroup(100476585);
                    ComposerKt.sourceInformation(composer, "356@16185L37");
                    composer.startReplaceableGroup(-3687241);
                    ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                    Object rememberedValue2 = composer.rememberedValue();
                    if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue2 = new NestedScrollDispatcher();
                        composer.updateRememberedValue(rememberedValue2);
                    }
                    composer.endReplaceableGroup();
                    nestedScrollDispatcher2 = (NestedScrollDispatcher) rememberedValue2;
                } else {
                    composer.startReplaceableGroup(100476571);
                }
                composer.endReplaceableGroup();
                final NestedScrollConnection nestedScrollConnection = connection;
                composer.startReplaceableGroup(-3686095);
                ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
                boolean changed = composer.changed(nestedScrollConnection) | composer.changed(nestedScrollDispatcher2) | composer.changed(coroutineScope);
                Object rememberedValue3 = composer.rememberedValue();
                if (changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue3 = new NestedScrollModifier(nestedScrollConnection, coroutineScope) { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt$nestedScroll$2$1$1
                        final /* synthetic */ NestedScrollConnection $connection;
                        final /* synthetic */ CoroutineScope $scope;
                        private final NestedScrollConnection connection;
                        private final NestedScrollDispatcher dispatcher;

                        {
                            this.$connection = nestedScrollConnection;
                            this.$scope = coroutineScope;
                            NestedScrollDispatcher.this.setOriginNestedScrollScope$ui_release(coroutineScope);
                            Unit unit = Unit.INSTANCE;
                            this.dispatcher = NestedScrollDispatcher.this;
                            this.connection = nestedScrollConnection;
                        }

                        @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
                        public boolean all(Function1<? super Modifier.Element, Boolean> function1) {
                            return NestedScrollModifier.DefaultImpls.all(this, function1);
                        }

                        @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
                        public boolean any(Function1<? super Modifier.Element, Boolean> function1) {
                            return NestedScrollModifier.DefaultImpls.any(this, function1);
                        }

                        @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
                        public <R> R foldIn(R r, Function2<? super R, ? super Modifier.Element, ? extends R> function2) {
                            return (R) NestedScrollModifier.DefaultImpls.foldIn(this, r, function2);
                        }

                        @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
                        public <R> R foldOut(R r, Function2<? super Modifier.Element, ? super R, ? extends R> function2) {
                            return (R) NestedScrollModifier.DefaultImpls.foldOut(this, r, function2);
                        }

                        @Override // androidx.compose.ui.Modifier
                        public Modifier then(Modifier modifier2) {
                            return NestedScrollModifier.DefaultImpls.then(this, modifier2);
                        }

                        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollModifier
                        public NestedScrollDispatcher getDispatcher() {
                            return this.dispatcher;
                        }

                        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollModifier
                        public NestedScrollConnection getConnection() {
                            return this.connection;
                        }
                    };
                    composer.updateRememberedValue(rememberedValue3);
                }
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
                return (NestedScrollModifierKt$nestedScroll$2$1$1) rememberedValue3;
            }
        });
    }
}
