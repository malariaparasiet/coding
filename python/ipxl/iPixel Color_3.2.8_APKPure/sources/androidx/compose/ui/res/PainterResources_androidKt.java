package androidx.compose.ui.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.TypedValue;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorPainter;
import androidx.compose.ui.graphics.vector.VectorPainterKt;
import androidx.compose.ui.graphics.vector.compat.XmlVectorParser_androidKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PainterResources.android.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a$\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bR\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u0017\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"errorMessage", "", "loadImageBitmapResource", "Landroidx/compose/ui/graphics/ImageBitmap;", "res", "Landroid/content/res/Resources;", "id", "", "loadVectorResource", "Landroidx/compose/ui/graphics/vector/ImageVector;", "theme", "Landroid/content/res/Resources$Theme;", "painterResource", "Landroidx/compose/ui/graphics/painter/Painter;", "(ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/painter/Painter;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PainterResources_androidKt {
    private static final String errorMessage = "Only VectorDrawables and rasterized asset types are supported ex. PNG, JPG";

    public static final Painter painterResource(int i, Composer composer, int i2) {
        BitmapPainter bitmapPainter;
        composer.startReplaceableGroup(-738265722);
        ComposerKt.sourceInformation(composer, "C(painterResource)56@2348L7,58@2404L25:PainterResources.android.kt#ccshc7");
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume = composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Context context = (Context) consume;
        Resources res = context.getResources();
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new TypedValue();
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        TypedValue typedValue = (TypedValue) rememberedValue;
        res.getValue(i, typedValue, true);
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && StringsKt.endsWith$default(charSequence, (CharSequence) ".xml", false, 2, (Object) null)) {
            composer.startReplaceableGroup(-738265379);
            ComposerKt.sourceInformation(composer, "63@2635L85,66@2729L34");
            Object valueOf = Integer.valueOf(i);
            composer.startReplaceableGroup(-3686552);
            ComposerKt.sourceInformation(composer, "C(remember)P(1,2):Composables.kt#9igjgp");
            boolean changed = composer.changed(charSequence) | composer.changed(valueOf);
            Object rememberedValue2 = composer.rememberedValue();
            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Resources.Theme theme = context.getTheme();
                Intrinsics.checkNotNullExpressionValue(theme, "context.theme");
                Intrinsics.checkNotNullExpressionValue(res, "res");
                rememberedValue2 = loadVectorResource(theme, res, i);
                composer.updateRememberedValue(rememberedValue2);
            }
            composer.endReplaceableGroup();
            VectorPainter rememberVectorPainter = VectorPainterKt.rememberVectorPainter((ImageVector) rememberedValue2, composer, 0);
            composer.endReplaceableGroup();
            bitmapPainter = rememberVectorPainter;
        } else {
            composer.startReplaceableGroup(-738265211);
            ComposerKt.sourceInformation(composer, "69@2849L75");
            Object valueOf2 = Integer.valueOf(i);
            composer.startReplaceableGroup(-3686552);
            ComposerKt.sourceInformation(composer, "C(remember)P(1,2):Composables.kt#9igjgp");
            boolean changed2 = composer.changed(valueOf2) | composer.changed(charSequence);
            Object rememberedValue3 = composer.rememberedValue();
            if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Intrinsics.checkNotNullExpressionValue(res, "res");
                rememberedValue3 = loadImageBitmapResource(res, i);
                composer.updateRememberedValue(rememberedValue3);
            }
            composer.endReplaceableGroup();
            BitmapPainter bitmapPainter2 = new BitmapPainter((ImageBitmap) rememberedValue3, 0L, 0L, 6, null);
            composer.endReplaceableGroup();
            bitmapPainter = bitmapPainter2;
        }
        composer.endReplaceableGroup();
        return bitmapPainter;
    }

    private static final ImageVector loadVectorResource(Resources.Theme theme, Resources resources, int i) {
        XmlResourceParser xml = resources.getXml(i);
        Intrinsics.checkNotNullExpressionValue(xml, "res.getXml(id)");
        if (!Intrinsics.areEqual(XmlVectorParser_androidKt.seekToStartTag(xml).getName(), "vector")) {
            throw new IllegalArgumentException(errorMessage);
        }
        return VectorResources_androidKt.loadVectorResourceInner(theme, resources, xml);
    }

    private static final ImageBitmap loadImageBitmapResource(Resources resources, int i) {
        return ImageResources_androidKt.imageResource(ImageBitmap.INSTANCE, resources, i);
    }
}
