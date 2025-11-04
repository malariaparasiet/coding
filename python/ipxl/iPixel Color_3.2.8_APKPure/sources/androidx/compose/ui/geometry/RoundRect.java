package androidx.compose.ui.geometry;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RoundRect.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 >2\u00020\u0001:\u0001>BP\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\bø\u0001\u0000¢\u0006\u0002\u0010\fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u0019\u0010!\u001a\u00020\bHÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\"\u0010\u0011J\u0019\u0010#\u001a\u00020\bHÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b$\u0010\u0011J\u0019\u0010%\u001a\u00020\bHÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0011J\u0019\u0010'\u001a\u00020\bHÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b(\u0010\u0011J\u001b\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.Jf\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bHÆ\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101J\u0013\u00102\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J(\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00032\u0006\u00109\u001a\u00020\u00032\u0006\u0010:\u001a\u00020\u0003H\u0002J\b\u0010;\u001a\u00020\u0000H\u0002J\b\u0010<\u001a\u00020=H\u0016R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u000b\u001a\u00020\bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\n\u001a\u00020\bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u001c\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0019\u0010\u0011R\u001c\u0010\t\u001a\u00020\bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\u001b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000f\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006?"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect;", "", "left", "", "top", "right", "bottom", "topLeftCornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "topRightCornerRadius", "bottomRightCornerRadius", "bottomLeftCornerRadius", "(FFFFJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "_scaledRadiiRect", "getBottom", "()F", "getBottomLeftCornerRadius-kKHJgLs", "()J", "J", "getBottomRightCornerRadius-kKHJgLs", "height", "getHeight", "getLeft", "getRight", "getTop", "getTopLeftCornerRadius-kKHJgLs", "getTopRightCornerRadius-kKHJgLs", "width", "getWidth", "component1", "component2", "component3", "component4", "component5", "component5-kKHJgLs", "component6", "component6-kKHJgLs", "component7", "component7-kKHJgLs", "component8", "component8-kKHJgLs", "contains", "", "point", "Landroidx/compose/ui/geometry/Offset;", "contains-k-4lQ0M", "(J)Z", "copy", "copy-MDFrsts", "(FFFFJJJJ)Landroidx/compose/ui/geometry/RoundRect;", "equals", "other", "hashCode", "", "minRadius", "min", "radius1", "radius2", "limit", "scaledRadiiRect", "toString", "", "Companion", "ui-geometry_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class RoundRect {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RoundRect Zero = RoundRectKt.m496RoundRectgG7oq9Y(0.0f, 0.0f, 0.0f, 0.0f, CornerRadius.INSTANCE.m427getZerokKHJgLs());
    private RoundRect _scaledRadiiRect;
    private final float bottom;
    private final long bottomLeftCornerRadius;
    private final long bottomRightCornerRadius;
    private final float left;
    private final float right;
    private final float top;
    private final long topLeftCornerRadius;
    private final long topRightCornerRadius;

    public /* synthetic */ RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, j, j2, j3, j4);
    }

    /* renamed from: copy-MDFrsts$default, reason: not valid java name */
    public static /* synthetic */ RoundRect m483copyMDFrsts$default(RoundRect roundRect, float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = roundRect.left;
        }
        if ((i & 2) != 0) {
            f2 = roundRect.top;
        }
        if ((i & 4) != 0) {
            f3 = roundRect.right;
        }
        if ((i & 8) != 0) {
            f4 = roundRect.bottom;
        }
        if ((i & 16) != 0) {
            j = roundRect.topLeftCornerRadius;
        }
        if ((i & 32) != 0) {
            j2 = roundRect.topRightCornerRadius;
        }
        if ((i & 64) != 0) {
            j3 = roundRect.bottomRightCornerRadius;
        }
        if ((i & 128) != 0) {
            j4 = roundRect.bottomLeftCornerRadius;
        }
        long j5 = j4;
        long j6 = j3;
        long j7 = j2;
        long j8 = j;
        return roundRect.m489copyMDFrsts(f, f2, f3, f4, j8, j7, j6, j5);
    }

    public static final RoundRect getZero() {
        return INSTANCE.getZero();
    }

    /* renamed from: component1, reason: from getter */
    public final float getLeft() {
        return this.left;
    }

    /* renamed from: component2, reason: from getter */
    public final float getTop() {
        return this.top;
    }

    /* renamed from: component3, reason: from getter */
    public final float getRight() {
        return this.right;
    }

    /* renamed from: component4, reason: from getter */
    public final float getBottom() {
        return this.bottom;
    }

    /* renamed from: component5-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopLeftCornerRadius() {
        return this.topLeftCornerRadius;
    }

    /* renamed from: component6-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopRightCornerRadius() {
        return this.topRightCornerRadius;
    }

    /* renamed from: component7-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomRightCornerRadius() {
        return this.bottomRightCornerRadius;
    }

    /* renamed from: component8-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomLeftCornerRadius() {
        return this.bottomLeftCornerRadius;
    }

    /* renamed from: copy-MDFrsts, reason: not valid java name */
    public final RoundRect m489copyMDFrsts(float left, float top, float right, float bottom, long topLeftCornerRadius, long topRightCornerRadius, long bottomRightCornerRadius, long bottomLeftCornerRadius) {
        return new RoundRect(left, top, right, bottom, topLeftCornerRadius, topRightCornerRadius, bottomRightCornerRadius, bottomLeftCornerRadius, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoundRect)) {
            return false;
        }
        RoundRect roundRect = (RoundRect) other;
        return Intrinsics.areEqual((Object) Float.valueOf(this.left), (Object) Float.valueOf(roundRect.left)) && Intrinsics.areEqual((Object) Float.valueOf(this.top), (Object) Float.valueOf(roundRect.top)) && Intrinsics.areEqual((Object) Float.valueOf(this.right), (Object) Float.valueOf(roundRect.right)) && Intrinsics.areEqual((Object) Float.valueOf(this.bottom), (Object) Float.valueOf(roundRect.bottom)) && CornerRadius.m416equalsimpl0(this.topLeftCornerRadius, roundRect.topLeftCornerRadius) && CornerRadius.m416equalsimpl0(this.topRightCornerRadius, roundRect.topRightCornerRadius) && CornerRadius.m416equalsimpl0(this.bottomRightCornerRadius, roundRect.bottomRightCornerRadius) && CornerRadius.m416equalsimpl0(this.bottomLeftCornerRadius, roundRect.bottomLeftCornerRadius);
    }

    public int hashCode() {
        return (((((((((((((Float.hashCode(this.left) * 31) + Float.hashCode(this.top)) * 31) + Float.hashCode(this.right)) * 31) + Float.hashCode(this.bottom)) * 31) + CornerRadius.m419hashCodeimpl(this.topLeftCornerRadius)) * 31) + CornerRadius.m419hashCodeimpl(this.topRightCornerRadius)) * 31) + CornerRadius.m419hashCodeimpl(this.bottomRightCornerRadius)) * 31) + CornerRadius.m419hashCodeimpl(this.bottomLeftCornerRadius);
    }

    private RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
        this.topLeftCornerRadius = j;
        this.topRightCornerRadius = j2;
        this.bottomRightCornerRadius = j3;
        this.bottomLeftCornerRadius = j4;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getTop() {
        return this.top;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getBottom() {
        return this.bottom;
    }

    public /* synthetic */ RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, (i & 16) != 0 ? CornerRadius.INSTANCE.m427getZerokKHJgLs() : j, (i & 32) != 0 ? CornerRadius.INSTANCE.m427getZerokKHJgLs() : j2, (i & 64) != 0 ? CornerRadius.INSTANCE.m427getZerokKHJgLs() : j3, (i & 128) != 0 ? CornerRadius.INSTANCE.m427getZerokKHJgLs() : j4, null);
    }

    /* renamed from: getTopLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m492getTopLeftCornerRadiuskKHJgLs() {
        return this.topLeftCornerRadius;
    }

    /* renamed from: getTopRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m493getTopRightCornerRadiuskKHJgLs() {
        return this.topRightCornerRadius;
    }

    /* renamed from: getBottomRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m491getBottomRightCornerRadiuskKHJgLs() {
        return this.bottomRightCornerRadius;
    }

    /* renamed from: getBottomLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m490getBottomLeftCornerRadiuskKHJgLs() {
        return this.bottomLeftCornerRadius;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    private final RoundRect scaledRadiiRect() {
        RoundRect roundRect = this._scaledRadiiRect;
        if (roundRect != null) {
            return roundRect;
        }
        float minRadius = minRadius(minRadius(minRadius(minRadius(1.0f, CornerRadius.m418getYimpl(m490getBottomLeftCornerRadiuskKHJgLs()), CornerRadius.m418getYimpl(m492getTopLeftCornerRadiuskKHJgLs()), getHeight()), CornerRadius.m417getXimpl(m492getTopLeftCornerRadiuskKHJgLs()), CornerRadius.m417getXimpl(m493getTopRightCornerRadiuskKHJgLs()), getWidth()), CornerRadius.m418getYimpl(m493getTopRightCornerRadiuskKHJgLs()), CornerRadius.m418getYimpl(m491getBottomRightCornerRadiuskKHJgLs()), getHeight()), CornerRadius.m417getXimpl(m491getBottomRightCornerRadiuskKHJgLs()), CornerRadius.m417getXimpl(m490getBottomLeftCornerRadiuskKHJgLs()), getWidth());
        RoundRect roundRect2 = new RoundRect(getLeft() * minRadius, getTop() * minRadius, getRight() * minRadius, getBottom() * minRadius, CornerRadiusKt.CornerRadius(CornerRadius.m417getXimpl(m492getTopLeftCornerRadiuskKHJgLs()) * minRadius, CornerRadius.m418getYimpl(m492getTopLeftCornerRadiuskKHJgLs()) * minRadius), CornerRadiusKt.CornerRadius(CornerRadius.m417getXimpl(m493getTopRightCornerRadiuskKHJgLs()) * minRadius, CornerRadius.m418getYimpl(m493getTopRightCornerRadiuskKHJgLs()) * minRadius), CornerRadiusKt.CornerRadius(CornerRadius.m417getXimpl(m491getBottomRightCornerRadiuskKHJgLs()) * minRadius, CornerRadius.m418getYimpl(m491getBottomRightCornerRadiuskKHJgLs()) * minRadius), CornerRadiusKt.CornerRadius(CornerRadius.m417getXimpl(m490getBottomLeftCornerRadiuskKHJgLs()) * minRadius, CornerRadius.m418getYimpl(m490getBottomLeftCornerRadiuskKHJgLs()) * minRadius), null);
        this._scaledRadiiRect = roundRect2;
        return roundRect2;
    }

    private final float minRadius(float min, float radius1, float radius2, float limit) {
        float f = radius1 + radius2;
        return (f <= limit || f == 0.0f) ? min : Math.min(min, limit / f);
    }

    /* renamed from: contains-k-4lQ0M, reason: not valid java name */
    public final boolean m488containsk4lQ0M(long point) {
        float m442getXimpl;
        float m443getYimpl;
        float m417getXimpl;
        float m418getYimpl;
        if (Offset.m442getXimpl(point) < this.left || Offset.m442getXimpl(point) >= this.right || Offset.m443getYimpl(point) < this.top || Offset.m443getYimpl(point) >= this.bottom) {
            return false;
        }
        RoundRect scaledRadiiRect = scaledRadiiRect();
        if (Offset.m442getXimpl(point) < this.left + CornerRadius.m417getXimpl(scaledRadiiRect.m492getTopLeftCornerRadiuskKHJgLs()) && Offset.m443getYimpl(point) < this.top + CornerRadius.m418getYimpl(scaledRadiiRect.m492getTopLeftCornerRadiuskKHJgLs())) {
            m442getXimpl = (Offset.m442getXimpl(point) - this.left) - CornerRadius.m417getXimpl(scaledRadiiRect.m492getTopLeftCornerRadiuskKHJgLs());
            m443getYimpl = (Offset.m443getYimpl(point) - this.top) - CornerRadius.m418getYimpl(scaledRadiiRect.m492getTopLeftCornerRadiuskKHJgLs());
            m417getXimpl = CornerRadius.m417getXimpl(scaledRadiiRect.m492getTopLeftCornerRadiuskKHJgLs());
            m418getYimpl = CornerRadius.m418getYimpl(scaledRadiiRect.m492getTopLeftCornerRadiuskKHJgLs());
        } else if (Offset.m442getXimpl(point) > this.right - CornerRadius.m417getXimpl(scaledRadiiRect.m493getTopRightCornerRadiuskKHJgLs()) && Offset.m443getYimpl(point) < this.top + CornerRadius.m418getYimpl(scaledRadiiRect.m493getTopRightCornerRadiuskKHJgLs())) {
            m442getXimpl = (Offset.m442getXimpl(point) - this.right) + CornerRadius.m417getXimpl(scaledRadiiRect.m493getTopRightCornerRadiuskKHJgLs());
            m443getYimpl = (Offset.m443getYimpl(point) - this.top) - CornerRadius.m418getYimpl(scaledRadiiRect.m493getTopRightCornerRadiuskKHJgLs());
            m417getXimpl = CornerRadius.m417getXimpl(scaledRadiiRect.m493getTopRightCornerRadiuskKHJgLs());
            m418getYimpl = CornerRadius.m418getYimpl(scaledRadiiRect.m493getTopRightCornerRadiuskKHJgLs());
        } else if (Offset.m442getXimpl(point) > this.right - CornerRadius.m417getXimpl(scaledRadiiRect.m491getBottomRightCornerRadiuskKHJgLs()) && Offset.m443getYimpl(point) > this.bottom - CornerRadius.m418getYimpl(scaledRadiiRect.m491getBottomRightCornerRadiuskKHJgLs())) {
            m442getXimpl = (Offset.m442getXimpl(point) - this.right) + CornerRadius.m417getXimpl(scaledRadiiRect.m491getBottomRightCornerRadiuskKHJgLs());
            m443getYimpl = (Offset.m443getYimpl(point) - this.bottom) + CornerRadius.m418getYimpl(scaledRadiiRect.m491getBottomRightCornerRadiuskKHJgLs());
            m417getXimpl = CornerRadius.m417getXimpl(scaledRadiiRect.m491getBottomRightCornerRadiuskKHJgLs());
            m418getYimpl = CornerRadius.m418getYimpl(scaledRadiiRect.m491getBottomRightCornerRadiuskKHJgLs());
        } else {
            if (Offset.m442getXimpl(point) >= this.left + CornerRadius.m417getXimpl(scaledRadiiRect.m490getBottomLeftCornerRadiuskKHJgLs()) || Offset.m443getYimpl(point) <= this.bottom - CornerRadius.m418getYimpl(scaledRadiiRect.m490getBottomLeftCornerRadiuskKHJgLs())) {
                return true;
            }
            m442getXimpl = (Offset.m442getXimpl(point) - this.left) - CornerRadius.m417getXimpl(scaledRadiiRect.m490getBottomLeftCornerRadiuskKHJgLs());
            m443getYimpl = (Offset.m443getYimpl(point) - this.bottom) + CornerRadius.m418getYimpl(scaledRadiiRect.m490getBottomLeftCornerRadiuskKHJgLs());
            m417getXimpl = CornerRadius.m417getXimpl(scaledRadiiRect.m490getBottomLeftCornerRadiuskKHJgLs());
            m418getYimpl = CornerRadius.m418getYimpl(scaledRadiiRect.m490getBottomLeftCornerRadiuskKHJgLs());
        }
        float f = m442getXimpl / m417getXimpl;
        float f2 = m443getYimpl / m418getYimpl;
        return (f * f) + (f2 * f2) <= 1.0f;
    }

    public String toString() {
        long m492getTopLeftCornerRadiuskKHJgLs = m492getTopLeftCornerRadiuskKHJgLs();
        long m493getTopRightCornerRadiuskKHJgLs = m493getTopRightCornerRadiuskKHJgLs();
        long m491getBottomRightCornerRadiuskKHJgLs = m491getBottomRightCornerRadiuskKHJgLs();
        long m490getBottomLeftCornerRadiuskKHJgLs = m490getBottomLeftCornerRadiuskKHJgLs();
        String str = GeometryUtilsKt.toStringAsFixed(this.left, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.top, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.right, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom, 1);
        return (CornerRadius.m416equalsimpl0(m492getTopLeftCornerRadiuskKHJgLs, m493getTopRightCornerRadiuskKHJgLs) && CornerRadius.m416equalsimpl0(m493getTopRightCornerRadiuskKHJgLs, m491getBottomRightCornerRadiuskKHJgLs) && CornerRadius.m416equalsimpl0(m491getBottomRightCornerRadiuskKHJgLs, m490getBottomLeftCornerRadiuskKHJgLs)) ? CornerRadius.m417getXimpl(m492getTopLeftCornerRadiuskKHJgLs) == CornerRadius.m418getYimpl(m492getTopLeftCornerRadiuskKHJgLs) ? "RoundRect(rect=" + str + ", radius=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m417getXimpl(m492getTopLeftCornerRadiuskKHJgLs), 1) + ')' : "RoundRect(rect=" + str + ", x=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m417getXimpl(m492getTopLeftCornerRadiuskKHJgLs), 1) + ", y=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m418getYimpl(m492getTopLeftCornerRadiuskKHJgLs), 1) + ')' : "RoundRect(rect=" + str + ", topLeft=" + ((Object) CornerRadius.m423toStringimpl(m492getTopLeftCornerRadiuskKHJgLs)) + ", topRight=" + ((Object) CornerRadius.m423toStringimpl(m493getTopRightCornerRadiuskKHJgLs)) + ", bottomRight=" + ((Object) CornerRadius.m423toStringimpl(m491getBottomRightCornerRadiuskKHJgLs)) + ", bottomLeft=" + ((Object) CornerRadius.m423toStringimpl(m490getBottomLeftCornerRadiuskKHJgLs)) + ')';
    }

    /* compiled from: RoundRect.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect$Companion;", "", "()V", "Zero", "Landroidx/compose/ui/geometry/RoundRect;", "getZero$annotations", "getZero", "()Landroidx/compose/ui/geometry/RoundRect;", "ui-geometry_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getZero$annotations() {
        }

        private Companion() {
        }

        public final RoundRect getZero() {
            return RoundRect.Zero;
        }
    }
}
