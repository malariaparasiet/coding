package androidx.compose.ui.graphics.vector;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VectorPainter.kt */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\u0007H\u0001¢\u0006\u0002\u0010\t\u001a\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u000e\u001a\u0094\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192;\u0010\u001a\u001a7\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u001c\u0012\b\b\u0015\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u001c\u0012\b\b\u0015\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00030\u001b¢\u0006\u0002\b\u001dH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"RootGroupName", "", "RenderVectorGroup", "", "group", "Landroidx/compose/ui/graphics/vector/VectorGroup;", "overrides", "", "Landroidx/compose/ui/graphics/vector/VectorOverride;", "(Landroidx/compose/ui/graphics/vector/VectorGroup;Ljava/util/Map;Landroidx/compose/runtime/Composer;II)V", "rememberVectorPainter", "Landroidx/compose/ui/graphics/vector/VectorPainter;", "image", "Landroidx/compose/ui/graphics/vector/ImageVector;", "(Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/vector/VectorPainter;", "defaultWidth", "Landroidx/compose/ui/unit/Dp;", "defaultHeight", "viewportWidth", "", "viewportHeight", HintConstants.AUTOFILL_HINT_NAME, "tintColor", "Landroidx/compose/ui/graphics/Color;", "tintBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "content", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "Landroidx/compose/runtime/Composable;", "rememberVectorPainter-mlNsNFs", "(FFFFLjava/lang/String;JILkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/vector/VectorPainter;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class VectorPainterKt {
    public static final String RootGroupName = "VectorRootGroup";

    /* renamed from: rememberVectorPainter-mlNsNFs, reason: not valid java name */
    public static final VectorPainter m1170rememberVectorPaintermlNsNFs(float f, float f2, float f3, float f4, String str, long j, int i, Function4<? super Float, ? super Float, ? super Composer, ? super Integer, Unit> content, Composer composer, int i2, int i3) {
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(-1998940692);
        ComposerKt.sourceInformation(composer, "C(rememberVectorPainter)P(2:c#ui.unit.Dp,1:c#ui.unit.Dp,7,6,3,5:c#ui.graphics.Color,4:c#ui.graphics.BlendMode)71@3064L7,84@3614L411:VectorPainter.kt#huu6hf");
        if ((i3 & 4) != 0) {
            f3 = Float.NaN;
        }
        if ((i3 & 8) != 0) {
            f4 = Float.NaN;
        }
        if ((i3 & 16) != 0) {
            str = RootGroupName;
        }
        if ((i3 & 32) != 0) {
            j = Color.INSTANCE.m707getUnspecified0d7_KjU();
        }
        final long j2 = j;
        if ((i3 & 64) != 0) {
            i = BlendMode.INSTANCE.m620getSrcIn0nO6VwU();
        }
        final int i4 = i;
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Density density = (Density) consume;
        float mo373toPx0680j_4 = density.mo373toPx0680j_4(f);
        float mo373toPx0680j_42 = density.mo373toPx0680j_4(f2);
        float f5 = Float.isNaN(f3) ? mo373toPx0680j_4 : f3;
        if (Float.isNaN(f4)) {
            f4 = mo373toPx0680j_42;
        }
        composer.startReplaceableGroup(-1998939971);
        ComposerKt.sourceInformation(composer, "*78@3354L28,82@3557L46");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new VectorPainter();
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        final VectorPainter vectorPainter = (VectorPainter) rememberedValue;
        vectorPainter.m1169setSizeuvyYCjk$ui_release(SizeKt.Size(mo373toPx0680j_4, mo373toPx0680j_42));
        int i5 = i2 >> 12;
        vectorPainter.RenderVector$ui_release(str, f5, f4, content, composer, (i5 & 14) | 32768 | (i5 & 7168));
        composer.endReplaceableGroup();
        EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$rememberVectorPainter$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                ColorFilter colorFilter;
                VectorPainter vectorPainter2 = VectorPainter.this;
                if (!Color.m672equalsimpl0(j2, Color.INSTANCE.m707getUnspecified0d7_KjU())) {
                    colorFilter = ColorFilter.INSTANCE.m715tintxETnrds(j2, i4);
                } else {
                    colorFilter = null;
                }
                vectorPainter2.setIntrinsicColorFilter$ui_release(colorFilter);
            }
        }, composer, 0);
        composer.endReplaceableGroup();
        return vectorPainter;
    }

    public static final VectorPainter rememberVectorPainter(final ImageVector image, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(image, "image");
        composer.startReplaceableGroup(-1998939043);
        ComposerKt.sourceInformation(composer, "C(rememberVectorPainter)105@4363L385:VectorPainter.kt#huu6hf");
        VectorPainter m1170rememberVectorPaintermlNsNFs = m1170rememberVectorPaintermlNsNFs(image.getDefaultWidth(), image.getDefaultHeight(), image.getViewportWidth(), image.getViewportHeight(), image.getName(), image.getTintColor(), image.getTintBlendMode(), ComposableLambdaKt.composableLambda(composer, -819890981, true, new Function4<Float, Float, Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$rememberVectorPainter$2
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(Float f, Float f2, Composer composer2, Integer num) {
                invoke(f.floatValue(), f2.floatValue(), composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(float f, float f2, Composer composer2, int i2) {
                ComposerKt.sourceInformation(composer2, "C113@4703L37:VectorPainter.kt#huu6hf");
                if (((i2 & 11) ^ 2) == 0 && composer2.getSkipping()) {
                    composer2.skipToGroupEnd();
                } else {
                    VectorPainterKt.RenderVectorGroup(ImageVector.this.getRoot(), null, composer2, 0, 2);
                }
            }
        }), composer, 12582912, 0);
        composer.endReplaceableGroup();
        return m1170rememberVectorPaintermlNsNFs;
    }

    public static final void RenderVectorGroup(final VectorGroup group, Map<String, ? extends VectorOverride> map, Composer composer, final int i, final int i2) {
        int i3;
        final Map<String, ? extends VectorOverride> emptyMap;
        Map<String, ? extends VectorOverride> map2;
        Intrinsics.checkNotNullParameter(group, "group");
        Composer startRestartGroup = composer.startRestartGroup(-326287540);
        ComposerKt.sourceInformation(startRestartGroup, "C(RenderVectorGroup):VectorPainter.kt#huu6hf");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 14) == 0) {
            i3 = (startRestartGroup.changed(group) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 16;
        }
        if ((2 & (~i2)) != 0 || ((i3 & 91) ^ 18) != 0 || !startRestartGroup.getSkipping()) {
            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                startRestartGroup.skipCurrentGroup();
                emptyMap = map;
            } else {
                startRestartGroup.startDefaults();
                emptyMap = i4 != 0 ? MapsKt.emptyMap() : map;
                startRestartGroup.endDefaults();
            }
            Iterator<VectorNode> it = group.iterator();
            while (it.hasNext()) {
                final VectorNode next = it.next();
                if (next instanceof VectorPath) {
                    startRestartGroup.startReplaceableGroup(-326287363);
                    ComposerKt.sourceInformation(startRestartGroup, "318@10527L1004");
                    VectorPath vectorPath = (VectorPath) next;
                    DefaultVectorOverride defaultVectorOverride = emptyMap.get(vectorPath.getName());
                    if (defaultVectorOverride == null) {
                        defaultVectorOverride = DefaultVectorOverride.INSTANCE;
                    }
                    Composer composer2 = startRestartGroup;
                    VectorComposeKt.m1164Path9cdaXJ4(defaultVectorOverride.obtainPathData(vectorPath.getPathData()), vectorPath.getPathFillType(), vectorPath.getName(), defaultVectorOverride.obtainFill(vectorPath.getFill()), defaultVectorOverride.obtainFillAlpha(vectorPath.getFillAlpha()), defaultVectorOverride.obtainStroke(vectorPath.getStroke()), defaultVectorOverride.obtainStrokeAlpha(vectorPath.getStrokeAlpha()), defaultVectorOverride.obtainStrokeWidth(vectorPath.getStrokeLineWidth()), vectorPath.getStrokeLineCap(), vectorPath.getStrokeLineJoin(), vectorPath.getStrokeLineMiter(), defaultVectorOverride.obtainTrimPathStart(vectorPath.getTrimPathStart()), defaultVectorOverride.obtainTrimPathEnd(vectorPath.getTrimPathEnd()), defaultVectorOverride.obtainTrimPathOffset(vectorPath.getTrimPathOffset()), composer2, 8, 0, 0);
                    startRestartGroup = composer2;
                    startRestartGroup.endReplaceableGroup();
                } else {
                    if (next instanceof VectorGroup) {
                        startRestartGroup.startReplaceableGroup(-326286219);
                        ComposerKt.sourceInformation(startRestartGroup, "336@11671L740");
                        VectorGroup vectorGroup = (VectorGroup) next;
                        DefaultVectorOverride defaultVectorOverride2 = emptyMap.get(vectorGroup.getName());
                        if (defaultVectorOverride2 == null) {
                            defaultVectorOverride2 = DefaultVectorOverride.INSTANCE;
                        }
                        map2 = emptyMap;
                        VectorComposeKt.Group(vectorGroup.getName(), defaultVectorOverride2.obtainRotation(vectorGroup.getRotation()), defaultVectorOverride2.obtainPivotX(vectorGroup.getPivotX()), defaultVectorOverride2.obtainPivotY(vectorGroup.getPivotY()), defaultVectorOverride2.obtainScaleX(vectorGroup.getScaleX()), defaultVectorOverride2.obtainScaleY(vectorGroup.getScaleY()), defaultVectorOverride2.obtainTranslateX(vectorGroup.getTranslationX()), defaultVectorOverride2.obtainTranslateY(vectorGroup.getTranslationY()), defaultVectorOverride2.obtainPathData(vectorGroup.getClipPathData()), ComposableLambdaKt.composableLambda(startRestartGroup, -819898735, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i5) {
                                ComposerKt.sourceInformation(composer3, "C347@12337L60:VectorPainter.kt#huu6hf");
                                if (((i5 & 11) ^ 2) == 0 && composer3.getSkipping()) {
                                    composer3.skipToGroupEnd();
                                } else {
                                    VectorPainterKt.RenderVectorGroup((VectorGroup) VectorNode.this, emptyMap, composer3, 64, 0);
                                }
                            }
                        }), startRestartGroup, 939524096, 0);
                        startRestartGroup.endReplaceableGroup();
                    } else {
                        map2 = emptyMap;
                        startRestartGroup.startReplaceableGroup(-326285376);
                        startRestartGroup.endReplaceableGroup();
                    }
                    emptyMap = map2;
                }
            }
        } else {
            startRestartGroup.skipToGroupEnd();
            emptyMap = map;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i5) {
                VectorPainterKt.RenderVectorGroup(VectorGroup.this, emptyMap, composer3, i | 1, i2);
            }
        });
    }
}
