package androidx.compose.ui.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.ExperimentalComposeUiApi;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.nestedscroll.NestedScrollDelegatingWrapper;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.node.LayoutNodeWrapper;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RelocationRequesterModifier.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\n\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"calculateOffset", "", "leadingEdge", "trailingEdge", "parentSize", "bringIntoView", "", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDelegatingWrapper;", "child", "Landroidx/compose/ui/layout/LayoutCoordinates;", "relocationRequester", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/layout/RelocationRequester;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class RelocationRequesterModifierKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void bringIntoView(NestedScrollDelegatingWrapper nestedScrollDelegatingWrapper, LayoutCoordinates layoutCoordinates) {
        NestedScrollDelegatingWrapper findPreviousNestedScrollWrapper;
        Rect localBoundingBoxOf = nestedScrollDelegatingWrapper.localBoundingBoxOf(layoutCoordinates, false);
        nestedScrollDelegatingWrapper.getModifier().getConnection().mo1798onPostScrollDzOQY0M(Offset.INSTANCE.m458getZeroF1C5BW0(), OffsetKt.Offset(calculateOffset(localBoundingBoxOf.getLeft(), localBoundingBoxOf.getRight(), IntSize.m2550getWidthimpl(nestedScrollDelegatingWrapper.mo1944getSizeYbymL2g())), calculateOffset(localBoundingBoxOf.getTop(), localBoundingBoxOf.getBottom(), IntSize.m2549getHeightimpl(nestedScrollDelegatingWrapper.mo1944getSizeYbymL2g()))), NestedScrollSource.INSTANCE.m1819getRelocateWNlRxjI());
        LayoutNodeWrapper wrappedBy$ui_release = nestedScrollDelegatingWrapper.getWrappedBy();
        if (wrappedBy$ui_release == null || (findPreviousNestedScrollWrapper = wrappedBy$ui_release.findPreviousNestedScrollWrapper()) == null) {
            return;
        }
        bringIntoView(findPreviousNestedScrollWrapper, layoutCoordinates);
    }

    private static final float calculateOffset(float f, float f2, float f3) {
        if (f >= 0.0f && f2 <= f3) {
            return 0.0f;
        }
        if (f >= 0.0f || f2 <= f3) {
            return Math.abs(f) < Math.abs(f2 - f3) ? -f : f3 - f2;
        }
        return 0.0f;
    }

    @ExperimentalComposeUiApi
    public static final Modifier relocationRequester(Modifier modifier, final RelocationRequester relocationRequester) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(relocationRequester, "relocationRequester");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.ui.layout.RelocationRequesterModifierKt$relocationRequester$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("relocationRequester");
                inspectorInfo.getProperties().set("relocationRequester", RelocationRequester.this);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.ui.layout.RelocationRequesterModifierKt$relocationRequester$2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(1518712278);
                ComposerKt.sourceInformation(composer, "C70@3027L42,71@3074L159:RelocationRequesterModifier.kt#80mrfh");
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object rememberedValue = composer.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    rememberedValue = new RelocationRequesterModifier();
                    composer.updateRememberedValue(rememberedValue);
                }
                composer.endReplaceableGroup();
                final RelocationRequesterModifier relocationRequesterModifier = (RelocationRequesterModifier) rememberedValue;
                RelocationRequester relocationRequester2 = RelocationRequester.this;
                final RelocationRequester relocationRequester3 = RelocationRequester.this;
                EffectsKt.DisposableEffect(relocationRequester2, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.layout.RelocationRequesterModifierKt$relocationRequester$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                        RelocationRequester.this.getModifiers$ui_release().add(relocationRequesterModifier);
                        final RelocationRequester relocationRequester4 = RelocationRequester.this;
                        final RelocationRequesterModifier relocationRequesterModifier2 = relocationRequesterModifier;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.layout.RelocationRequesterModifierKt$relocationRequester$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                RelocationRequester.this.getModifiers$ui_release().remove(relocationRequesterModifier2);
                            }
                        };
                    }
                }, composer, MutableVector.$stable);
                composer.endReplaceableGroup();
                return relocationRequesterModifier;
            }
        });
    }
}
