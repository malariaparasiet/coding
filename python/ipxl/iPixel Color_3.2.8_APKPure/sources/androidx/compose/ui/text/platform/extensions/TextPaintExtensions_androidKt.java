package androidx.compose.ui.text.platform.extensions;

import android.graphics.Typeface;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.platform.TypefaceAdapter;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextPaintExtensions.android.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a$\u0010\u0006\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\u0003H\u0000¨\u0006\f"}, d2 = {"createTypeface", "Landroid/graphics/Typeface;", "style", "Landroidx/compose/ui/text/SpanStyle;", "typefaceAdapter", "Landroidx/compose/ui/text/platform/TypefaceAdapter;", "applySpanStyle", "Landroidx/compose/ui/text/platform/AndroidTextPaint;", "density", "Landroidx/compose/ui/unit/Density;", "hasFontAttributes", "", "ui-text_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TextPaintExtensions_androidKt {
    public static final SpanStyle applySpanStyle(AndroidTextPaint androidTextPaint, SpanStyle style, TypefaceAdapter typefaceAdapter, Density density) {
        long m2582getUnspecifiedXSAIIZE;
        long background;
        BaselineShift baselineShift;
        Intrinsics.checkNotNullParameter(androidTextPaint, "<this>");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(typefaceAdapter, "typefaceAdapter");
        Intrinsics.checkNotNullParameter(density, "density");
        long m2570getTypeUIouoOA = TextUnit.m2570getTypeUIouoOA(style.getFontSize());
        if (TextUnitType.m2599equalsimpl0(m2570getTypeUIouoOA, TextUnitType.INSTANCE.m2604getSpUIouoOA())) {
            androidTextPaint.setTextSize(density.mo372toPxR2X_6o(style.getFontSize()));
        } else if (TextUnitType.m2599equalsimpl0(m2570getTypeUIouoOA, TextUnitType.INSTANCE.m2603getEmUIouoOA())) {
            androidTextPaint.setTextSize(androidTextPaint.getTextSize() * TextUnit.m2571getValueimpl(style.getFontSize()));
        }
        if (hasFontAttributes(style)) {
            androidTextPaint.setTypeface(createTypeface(style, typefaceAdapter));
        }
        if (style.getLocaleList() != null && !Intrinsics.areEqual(style.getLocaleList(), LocaleList.INSTANCE.getCurrent())) {
            LocaleListHelperMethods.INSTANCE.setTextLocales(androidTextPaint, style.getLocaleList());
        }
        long m2570getTypeUIouoOA2 = TextUnit.m2570getTypeUIouoOA(style.getLetterSpacing());
        if (TextUnitType.m2599equalsimpl0(m2570getTypeUIouoOA2, TextUnitType.INSTANCE.m2603getEmUIouoOA())) {
            androidTextPaint.setLetterSpacing(TextUnit.m2571getValueimpl(style.getLetterSpacing()));
        } else {
            TextUnitType.m2599equalsimpl0(m2570getTypeUIouoOA2, TextUnitType.INSTANCE.m2604getSpUIouoOA());
        }
        if (style.getFontFeatureSettings() != null && !Intrinsics.areEqual(style.getFontFeatureSettings(), "")) {
            androidTextPaint.setFontFeatureSettings(style.getFontFeatureSettings());
        }
        if (style.getTextGeometricTransform() != null && !Intrinsics.areEqual(style.getTextGeometricTransform(), TextGeometricTransform.INSTANCE.getNone$ui_text_release())) {
            androidTextPaint.setTextScaleX(androidTextPaint.getTextScaleX() * style.getTextGeometricTransform().getScaleX());
            androidTextPaint.setTextSkewX(androidTextPaint.getTextSkewX() + style.getTextGeometricTransform().getSkewX());
        }
        androidTextPaint.m2310setColor8_81llA(style.getColor());
        androidTextPaint.setShadow(style.getShadow());
        androidTextPaint.setTextDecoration(style.getTextDecoration());
        if (TextUnitType.m2599equalsimpl0(TextUnit.m2570getTypeUIouoOA(style.getLetterSpacing()), TextUnitType.INSTANCE.m2604getSpUIouoOA()) && TextUnit.m2571getValueimpl(style.getLetterSpacing()) != 0.0f) {
            m2582getUnspecifiedXSAIIZE = style.getLetterSpacing();
        } else {
            m2582getUnspecifiedXSAIIZE = TextUnit.INSTANCE.m2582getUnspecifiedXSAIIZE();
        }
        long j = m2582getUnspecifiedXSAIIZE;
        if (Color.m672equalsimpl0(style.getBackground(), Color.INSTANCE.m706getTransparent0d7_KjU())) {
            background = Color.INSTANCE.m707getUnspecified0d7_KjU();
        } else {
            background = style.getBackground();
        }
        long j2 = background;
        BaselineShift baselineShift2 = style.getBaselineShift();
        if (baselineShift2 == null ? false : BaselineShift.m2338equalsimpl0(baselineShift2.getMultiplier(), BaselineShift.INSTANCE.m2345getNoney9eOQZs())) {
            baselineShift = null;
        } else {
            baselineShift = style.getBaselineShift();
        }
        return new SpanStyle(0L, 0L, null, null, null, null, null, j, baselineShift, null, null, j2, null, null, 13951, null);
    }

    public static final boolean hasFontAttributes(SpanStyle spanStyle) {
        Intrinsics.checkNotNullParameter(spanStyle, "<this>");
        return (spanStyle.getFontFamily() == null && spanStyle.getFontStyle() == null && spanStyle.getFontWeight() == null) ? false : true;
    }

    private static final Typeface createTypeface(SpanStyle spanStyle, TypefaceAdapter typefaceAdapter) {
        FontFamily fontFamily = spanStyle.getFontFamily();
        FontWeight fontWeight = spanStyle.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        FontStyle fontStyle = spanStyle.getFontStyle();
        int m2230getNormal_LCdwA = fontStyle == null ? FontStyle.INSTANCE.m2230getNormal_LCdwA() : fontStyle.getValue();
        FontSynthesis fontSynthesis = spanStyle.getFontSynthesis();
        return typefaceAdapter.m2316createDPcqOEQ(fontFamily, fontWeight, m2230getNormal_LCdwA, fontSynthesis == null ? FontSynthesis.INSTANCE.m2240getAllGVVA2EU() : fontSynthesis.getValue());
    }
}
