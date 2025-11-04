package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: Density.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u000b\u001a\u00020\f*\u00020\rH\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u000b\u001a\u00020\f*\u00020\u0010H\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\r*\u00020\u0010H\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001c\u0010\u0013\u001a\u00020\r*\u00020\u0003H\u0017ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001c\u0010\u0013\u001a\u00020\r*\u00020\fH\u0017ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u0003*\u00020\rH\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0017J\u0019\u0010\u0019\u001a\u00020\u0003*\u00020\u0010H\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0015J\f\u0010\u001c\u001a\u00020\u001d*\u00020\u001eH\u0017J\u0019\u0010\u001f\u001a\u00020\u0010*\u00020\rH\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010!J\u001c\u0010\u001f\u001a\u00020\u0010*\u00020\u0003H\u0017ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\"\u0010!J\u001c\u0010\u001f\u001a\u00020\u0010*\u00020\fH\u0017ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\"\u0010#R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0005\u001a\u0004\b\n\u0010\u0007\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Landroidx/compose/ui/unit/Density;", "", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "fontScale", "getFontScale$annotations", "getFontScale", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-GaN1DYA", "(J)F", "toDp-u2uoSUM", "(F)F", "(I)F", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSp", "toSp-0xMU5do", "(F)J", "toSp-kPz2Gy4", "(I)J", "ui-unit_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface Density {
    float getDensity();

    float getFontScale();

    /* renamed from: roundToPx--R2X_6o */
    int mo367roundToPxR2X_6o(long j);

    /* renamed from: roundToPx-0680j_4 */
    int mo368roundToPx0680j_4(float f);

    /* renamed from: toDp-GaN1DYA */
    float mo369toDpGaN1DYA(long j);

    /* renamed from: toDp-u2uoSUM */
    float mo370toDpu2uoSUM(float f);

    /* renamed from: toDp-u2uoSUM */
    float mo371toDpu2uoSUM(int i);

    /* renamed from: toPx--R2X_6o */
    float mo372toPxR2X_6o(long j);

    /* renamed from: toPx-0680j_4 */
    float mo373toPx0680j_4(float f);

    Rect toRect(DpRect dpRect);

    /* renamed from: toSp-0xMU5do */
    long mo374toSp0xMU5do(float f);

    /* renamed from: toSp-kPz2Gy4 */
    long mo375toSpkPz2Gy4(float f);

    /* renamed from: toSp-kPz2Gy4 */
    long mo376toSpkPz2Gy4(int i);

    /* compiled from: Density.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        public static /* synthetic */ void getFontScale$annotations() {
        }

        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m2424toPx0680j_4(Density density, float f) {
            Intrinsics.checkNotNullParameter(density, "this");
            return f * density.getDensity();
        }

        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m2419roundToPx0680j_4(Density density, float f) {
            Intrinsics.checkNotNullParameter(density, "this");
            float mo373toPx0680j_4 = density.mo373toPx0680j_4(f);
            if (Float.isInfinite(mo373toPx0680j_4)) {
                return Integer.MAX_VALUE;
            }
            return MathKt.roundToInt(mo373toPx0680j_4);
        }

        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m2425toSp0xMU5do(Density density, float f) {
            Intrinsics.checkNotNullParameter(density, "this");
            return TextUnitKt.getSp(f / density.getFontScale());
        }

        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m2423toPxR2X_6o(Density density, long j) {
            Intrinsics.checkNotNullParameter(density, "this");
            if (!TextUnitType.m2599equalsimpl0(TextUnit.m2570getTypeUIouoOA(j), TextUnitType.INSTANCE.m2604getSpUIouoOA())) {
                throw new IllegalStateException("Only Sp can convert to Px".toString());
            }
            return TextUnit.m2571getValueimpl(j) * density.getFontScale() * density.getDensity();
        }

        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m2418roundToPxR2X_6o(Density density, long j) {
            Intrinsics.checkNotNullParameter(density, "this");
            return MathKt.roundToInt(density.mo372toPxR2X_6o(j));
        }

        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m2420toDpGaN1DYA(Density density, long j) {
            Intrinsics.checkNotNullParameter(density, "this");
            if (!TextUnitType.m2599equalsimpl0(TextUnit.m2570getTypeUIouoOA(j), TextUnitType.INSTANCE.m2604getSpUIouoOA())) {
                throw new IllegalStateException("Only Sp can convert to Px".toString());
            }
            return Dp.m2430constructorimpl(TextUnit.m2571getValueimpl(j) * density.getFontScale());
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2422toDpu2uoSUM(Density density, int i) {
            Intrinsics.checkNotNullParameter(density, "this");
            return Dp.m2430constructorimpl(i / density.getDensity());
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2427toSpkPz2Gy4(Density density, int i) {
            Intrinsics.checkNotNullParameter(density, "this");
            return TextUnitKt.getSp(i / (density.getFontScale() * density.getDensity()));
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2421toDpu2uoSUM(Density density, float f) {
            Intrinsics.checkNotNullParameter(density, "this");
            return Dp.m2430constructorimpl(f / density.getDensity());
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2426toSpkPz2Gy4(Density density, float f) {
            Intrinsics.checkNotNullParameter(density, "this");
            return TextUnitKt.getSp(f / (density.getFontScale() * density.getDensity()));
        }

        public static Rect toRect(Density density, DpRect receiver) {
            Intrinsics.checkNotNullParameter(density, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return new Rect(density.mo373toPx0680j_4(receiver.m2496getLeftD9Ej5fM()), density.mo373toPx0680j_4(receiver.m2498getTopD9Ej5fM()), density.mo373toPx0680j_4(receiver.m2497getRightD9Ej5fM()), density.mo373toPx0680j_4(receiver.m2495getBottomD9Ej5fM()));
        }
    }
}
