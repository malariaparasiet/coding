package androidx.compose.ui.graphics;

import android.graphics.PorterDuff;
import android.os.Build;
import kotlin.Metadata;

/* compiled from: AndroidBlendMode.android.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a\u0019\u0010\t\u001a\u00020\n*\u00020\u0002H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"isSupported", "", "Landroidx/compose/ui/graphics/BlendMode;", "isSupported-s9anfk8", "(I)Z", "toAndroidBlendMode", "Landroid/graphics/BlendMode;", "toAndroidBlendMode-s9anfk8", "(I)Landroid/graphics/BlendMode;", "toPorterDuffMode", "Landroid/graphics/PorterDuff$Mode;", "toPorterDuffMode-s9anfk8", "(I)Landroid/graphics/PorterDuff$Mode;", "ui-graphics_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidBlendMode_androidKt {
    /* renamed from: isSupported-s9anfk8, reason: not valid java name */
    public static final boolean m533isSupporteds9anfk8(int i) {
        return Build.VERSION.SDK_INT >= 29 || BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m622getSrcOver0nO6VwU()) || m535toPorterDuffModes9anfk8(i) != PorterDuff.Mode.SRC_OVER;
    }

    /* renamed from: toPorterDuffMode-s9anfk8, reason: not valid java name */
    public static final PorterDuff.Mode m535toPorterDuffModes9anfk8(int i) {
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m595getClear0nO6VwU())) {
            return PorterDuff.Mode.CLEAR;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m618getSrc0nO6VwU())) {
            return PorterDuff.Mode.SRC;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m601getDst0nO6VwU())) {
            return PorterDuff.Mode.DST;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m622getSrcOver0nO6VwU())) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m605getDstOver0nO6VwU())) {
            return PorterDuff.Mode.DST_OVER;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m620getSrcIn0nO6VwU())) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m603getDstIn0nO6VwU())) {
            return PorterDuff.Mode.DST_IN;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m621getSrcOut0nO6VwU())) {
            return PorterDuff.Mode.SRC_OUT;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m604getDstOut0nO6VwU())) {
            return PorterDuff.Mode.DST_OUT;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m619getSrcAtop0nO6VwU())) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m602getDstAtop0nO6VwU())) {
            return PorterDuff.Mode.DST_ATOP;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m623getXor0nO6VwU())) {
            return PorterDuff.Mode.XOR;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m614getPlus0nO6VwU())) {
            return PorterDuff.Mode.ADD;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m616getScreen0nO6VwU())) {
            return PorterDuff.Mode.SCREEN;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m613getOverlay0nO6VwU())) {
            return PorterDuff.Mode.OVERLAY;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m599getDarken0nO6VwU())) {
            return PorterDuff.Mode.DARKEN;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m609getLighten0nO6VwU())) {
            return PorterDuff.Mode.LIGHTEN;
        }
        if (BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m611getModulate0nO6VwU())) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return PorterDuff.Mode.SRC_OVER;
    }

    /* renamed from: toAndroidBlendMode-s9anfk8, reason: not valid java name */
    public static final android.graphics.BlendMode m534toAndroidBlendModes9anfk8(int i) {
        return BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m595getClear0nO6VwU()) ? android.graphics.BlendMode.CLEAR : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m618getSrc0nO6VwU()) ? android.graphics.BlendMode.SRC : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m601getDst0nO6VwU()) ? android.graphics.BlendMode.DST : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m622getSrcOver0nO6VwU()) ? android.graphics.BlendMode.SRC_OVER : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m605getDstOver0nO6VwU()) ? android.graphics.BlendMode.DST_OVER : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m620getSrcIn0nO6VwU()) ? android.graphics.BlendMode.SRC_IN : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m603getDstIn0nO6VwU()) ? android.graphics.BlendMode.DST_IN : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m621getSrcOut0nO6VwU()) ? android.graphics.BlendMode.SRC_OUT : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m604getDstOut0nO6VwU()) ? android.graphics.BlendMode.DST_OUT : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m619getSrcAtop0nO6VwU()) ? android.graphics.BlendMode.SRC_ATOP : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m602getDstAtop0nO6VwU()) ? android.graphics.BlendMode.DST_ATOP : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m623getXor0nO6VwU()) ? android.graphics.BlendMode.XOR : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m614getPlus0nO6VwU()) ? android.graphics.BlendMode.PLUS : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m611getModulate0nO6VwU()) ? android.graphics.BlendMode.MODULATE : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m616getScreen0nO6VwU()) ? android.graphics.BlendMode.SCREEN : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m613getOverlay0nO6VwU()) ? android.graphics.BlendMode.OVERLAY : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m599getDarken0nO6VwU()) ? android.graphics.BlendMode.DARKEN : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m609getLighten0nO6VwU()) ? android.graphics.BlendMode.LIGHTEN : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m598getColorDodge0nO6VwU()) ? android.graphics.BlendMode.COLOR_DODGE : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m597getColorBurn0nO6VwU()) ? android.graphics.BlendMode.COLOR_BURN : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m607getHardlight0nO6VwU()) ? android.graphics.BlendMode.HARD_LIGHT : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m617getSoftlight0nO6VwU()) ? android.graphics.BlendMode.SOFT_LIGHT : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m600getDifference0nO6VwU()) ? android.graphics.BlendMode.DIFFERENCE : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m606getExclusion0nO6VwU()) ? android.graphics.BlendMode.EXCLUSION : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m612getMultiply0nO6VwU()) ? android.graphics.BlendMode.MULTIPLY : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m608getHue0nO6VwU()) ? android.graphics.BlendMode.HUE : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m615getSaturation0nO6VwU()) ? android.graphics.BlendMode.SATURATION : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m596getColor0nO6VwU()) ? android.graphics.BlendMode.COLOR : BlendMode.m591equalsimpl0(i, BlendMode.INSTANCE.m610getLuminosity0nO6VwU()) ? android.graphics.BlendMode.LUMINOSITY : android.graphics.BlendMode.SRC_OVER;
    }
}
