package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Matrix.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087@\u0018\u0000 G2\u00020\u0001:\u0001GB\u0014\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0086\n¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020 2\u0006\u0010!\u001a\u00020 ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#J\u0015\u0010\u001b\u001a\u00020$2\u0006\u0010\u001c\u001a\u00020$¢\u0006\u0004\b\u001e\u0010%J\r\u0010&\u001a\u00020\u0018¢\u0006\u0004\b'\u0010\u001aJ\u0015\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000e¢\u0006\u0004\b*\u0010+J\u0015\u0010,\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000e¢\u0006\u0004\b-\u0010+J\u0015\u0010.\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000e¢\u0006\u0004\b/\u0010+J+\u00100\u001a\u00020\u00182\b\b\u0002\u00101\u001a\u00020\u000e2\b\b\u0002\u00102\u001a\u00020\u000e2\b\b\u0002\u00103\u001a\u00020\u000e¢\u0006\u0004\b4\u00105J(\u00106\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u000eH\u0086\n¢\u0006\u0004\b8\u00109J\u001b\u0010:\u001a\u00020\u00182\u0006\u0010;\u001a\u00020\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b<\u0010=J\u001e\u0010>\u001a\u00020\u00182\u0006\u0010?\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b@\u0010=J\u000f\u0010A\u001a\u00020BH\u0016¢\u0006\u0004\bC\u0010DJ+\u0010E\u001a\u00020\u00182\b\b\u0002\u00101\u001a\u00020\u000e2\b\b\u0002\u00102\u001a\u00020\u000e2\b\b\u0002\u00103\u001a\u00020\u000e¢\u0006\u0004\bF\u00105R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002ø\u0001\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006H"}, d2 = {"Landroidx/compose/ui/graphics/Matrix;", "", "values", "", "constructor-impl", "([F)[F", "getValues", "()[F", "equals", "", "other", "equals-impl", "([FLjava/lang/Object;)Z", "get", "", "row", "", "column", "get-impl", "([FII)F", "hashCode", "hashCode-impl", "([F)I", "invert", "", "invert-impl", "([F)V", "map", "rect", "Landroidx/compose/ui/geometry/MutableRect;", "map-impl", "([FLandroidx/compose/ui/geometry/MutableRect;)V", "Landroidx/compose/ui/geometry/Offset;", "point", "map-MK-Hz9U", "([FJ)J", "Landroidx/compose/ui/geometry/Rect;", "([FLandroidx/compose/ui/geometry/Rect;)Landroidx/compose/ui/geometry/Rect;", "reset", "reset-impl", "rotateX", "degrees", "rotateX-impl", "([FF)V", "rotateY", "rotateY-impl", "rotateZ", "rotateZ-impl", "scale", "x", "y", "z", "scale-impl", "([FFFF)V", "set", "v", "set-impl", "([FIIF)V", "setFrom", "matrix", "setFrom-58bKbWc", "([F[F)V", "timesAssign", "m", "timesAssign-58bKbWc", "toString", "", "toString-impl", "([F)Ljava/lang/String;", "translate", "translate-impl", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@JvmInline
/* loaded from: classes.dex */
public final class Matrix {
    public static final int Perspective0 = 3;
    public static final int Perspective1 = 7;
    public static final int Perspective2 = 15;
    public static final int ScaleX = 0;
    public static final int ScaleY = 5;
    public static final int ScaleZ = 10;
    public static final int SkewX = 4;
    public static final int SkewY = 1;
    public static final int TranslateX = 12;
    public static final int TranslateY = 13;
    public static final int TranslateZ = 14;
    private final float[] values;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Matrix m833boximpl(float[] fArr) {
        return new Matrix(fArr);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static float[] m834constructorimpl(float[] values) {
        Intrinsics.checkNotNullParameter(values, "values");
        return values;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m836equalsimpl(float[] fArr, Object obj) {
        return (obj instanceof Matrix) && Intrinsics.areEqual(fArr, ((Matrix) obj).getValues());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m837equalsimpl0(float[] fArr, float[] fArr2) {
        return Intrinsics.areEqual(fArr, fArr2);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m839hashCodeimpl(float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    public boolean equals(Object obj) {
        return m836equalsimpl(getValues(), obj);
    }

    public int hashCode() {
        return m839hashCodeimpl(getValues());
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ float[] getValues() {
        return this.values;
    }

    private /* synthetic */ Matrix(float[] fArr) {
        this.values = fArr;
    }

    public final float[] getValues() {
        return getValues();
    }

    /* renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ float[] m835constructorimpl$default(float[] fArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            fArr = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        }
        return m834constructorimpl(fArr);
    }

    /* renamed from: get-impl, reason: not valid java name */
    public static final float m838getimpl(float[] arg0, int i, int i2) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        return arg0[(i * 4) + i2];
    }

    /* renamed from: set-impl, reason: not valid java name */
    public static final void m850setimpl(float[] arg0, int i, int i2, float f) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        arg0[(i * 4) + i2] = f;
    }

    /* renamed from: map-MK-Hz9U, reason: not valid java name */
    public static final long m841mapMKHz9U(float[] arg0, long j) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        float m442getXimpl = Offset.m442getXimpl(j);
        float m443getYimpl = Offset.m443getYimpl(j);
        float f = (arg0[3] * m442getXimpl) + (arg0[7] * m443getYimpl) + arg0[15];
        float f2 = f != 0.0f ? 1.0f / f : 0.0f;
        return OffsetKt.Offset(((arg0[0] * m442getXimpl) + (arg0[4] * m443getYimpl) + arg0[12]) * f2, f2 * ((arg0[1] * m442getXimpl) + (arg0[5] * m443getYimpl) + arg0[13]));
    }

    /* renamed from: map-impl, reason: not valid java name */
    public static final Rect m842mapimpl(float[] arg0, Rect rect) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        Intrinsics.checkNotNullParameter(rect, "rect");
        long m841mapMKHz9U = m841mapMKHz9U(arg0, OffsetKt.Offset(rect.getLeft(), rect.getTop()));
        long m841mapMKHz9U2 = m841mapMKHz9U(arg0, OffsetKt.Offset(rect.getLeft(), rect.getBottom()));
        long m841mapMKHz9U3 = m841mapMKHz9U(arg0, OffsetKt.Offset(rect.getRight(), rect.getTop()));
        long m841mapMKHz9U4 = m841mapMKHz9U(arg0, OffsetKt.Offset(rect.getRight(), rect.getBottom()));
        return new Rect(Math.min(Math.min(Offset.m442getXimpl(m841mapMKHz9U), Offset.m442getXimpl(m841mapMKHz9U2)), Math.min(Offset.m442getXimpl(m841mapMKHz9U3), Offset.m442getXimpl(m841mapMKHz9U4))), Math.min(Math.min(Offset.m443getYimpl(m841mapMKHz9U), Offset.m443getYimpl(m841mapMKHz9U2)), Math.min(Offset.m443getYimpl(m841mapMKHz9U3), Offset.m443getYimpl(m841mapMKHz9U4))), Math.max(Math.max(Offset.m442getXimpl(m841mapMKHz9U), Offset.m442getXimpl(m841mapMKHz9U2)), Math.max(Offset.m442getXimpl(m841mapMKHz9U3), Offset.m442getXimpl(m841mapMKHz9U4))), Math.max(Math.max(Offset.m443getYimpl(m841mapMKHz9U), Offset.m443getYimpl(m841mapMKHz9U2)), Math.max(Offset.m443getYimpl(m841mapMKHz9U3), Offset.m443getYimpl(m841mapMKHz9U4))));
    }

    /* renamed from: map-impl, reason: not valid java name */
    public static final void m843mapimpl(float[] arg0, MutableRect rect) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        Intrinsics.checkNotNullParameter(rect, "rect");
        long m841mapMKHz9U = m841mapMKHz9U(arg0, OffsetKt.Offset(rect.getLeft(), rect.getTop()));
        long m841mapMKHz9U2 = m841mapMKHz9U(arg0, OffsetKt.Offset(rect.getLeft(), rect.getBottom()));
        long m841mapMKHz9U3 = m841mapMKHz9U(arg0, OffsetKt.Offset(rect.getRight(), rect.getTop()));
        long m841mapMKHz9U4 = m841mapMKHz9U(arg0, OffsetKt.Offset(rect.getRight(), rect.getBottom()));
        rect.setLeft(Math.min(Math.min(Offset.m442getXimpl(m841mapMKHz9U), Offset.m442getXimpl(m841mapMKHz9U2)), Math.min(Offset.m442getXimpl(m841mapMKHz9U3), Offset.m442getXimpl(m841mapMKHz9U4))));
        rect.setTop(Math.min(Math.min(Offset.m443getYimpl(m841mapMKHz9U), Offset.m443getYimpl(m841mapMKHz9U2)), Math.min(Offset.m443getYimpl(m841mapMKHz9U3), Offset.m443getYimpl(m841mapMKHz9U4))));
        rect.setRight(Math.max(Math.max(Offset.m442getXimpl(m841mapMKHz9U), Offset.m442getXimpl(m841mapMKHz9U2)), Math.max(Offset.m442getXimpl(m841mapMKHz9U3), Offset.m442getXimpl(m841mapMKHz9U4))));
        rect.setBottom(Math.max(Math.max(Offset.m443getYimpl(m841mapMKHz9U), Offset.m443getYimpl(m841mapMKHz9U2)), Math.max(Offset.m443getYimpl(m841mapMKHz9U3), Offset.m443getYimpl(m841mapMKHz9U4))));
    }

    /* renamed from: timesAssign-58bKbWc, reason: not valid java name */
    public static final void m852timesAssign58bKbWc(float[] arg0, float[] m) {
        float m858dotp89u6pk;
        float m858dotp89u6pk2;
        float m858dotp89u6pk3;
        float m858dotp89u6pk4;
        float m858dotp89u6pk5;
        float m858dotp89u6pk6;
        float m858dotp89u6pk7;
        float m858dotp89u6pk8;
        float m858dotp89u6pk9;
        float m858dotp89u6pk10;
        float m858dotp89u6pk11;
        float m858dotp89u6pk12;
        float m858dotp89u6pk13;
        float m858dotp89u6pk14;
        float m858dotp89u6pk15;
        float m858dotp89u6pk16;
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        Intrinsics.checkNotNullParameter(m, "m");
        m858dotp89u6pk = MatrixKt.m858dotp89u6pk(arg0, 0, m, 0);
        m858dotp89u6pk2 = MatrixKt.m858dotp89u6pk(arg0, 0, m, 1);
        m858dotp89u6pk3 = MatrixKt.m858dotp89u6pk(arg0, 0, m, 2);
        m858dotp89u6pk4 = MatrixKt.m858dotp89u6pk(arg0, 0, m, 3);
        m858dotp89u6pk5 = MatrixKt.m858dotp89u6pk(arg0, 1, m, 0);
        m858dotp89u6pk6 = MatrixKt.m858dotp89u6pk(arg0, 1, m, 1);
        m858dotp89u6pk7 = MatrixKt.m858dotp89u6pk(arg0, 1, m, 2);
        m858dotp89u6pk8 = MatrixKt.m858dotp89u6pk(arg0, 1, m, 3);
        m858dotp89u6pk9 = MatrixKt.m858dotp89u6pk(arg0, 2, m, 0);
        m858dotp89u6pk10 = MatrixKt.m858dotp89u6pk(arg0, 2, m, 1);
        m858dotp89u6pk11 = MatrixKt.m858dotp89u6pk(arg0, 2, m, 2);
        m858dotp89u6pk12 = MatrixKt.m858dotp89u6pk(arg0, 2, m, 3);
        m858dotp89u6pk13 = MatrixKt.m858dotp89u6pk(arg0, 3, m, 0);
        m858dotp89u6pk14 = MatrixKt.m858dotp89u6pk(arg0, 3, m, 1);
        m858dotp89u6pk15 = MatrixKt.m858dotp89u6pk(arg0, 3, m, 2);
        m858dotp89u6pk16 = MatrixKt.m858dotp89u6pk(arg0, 3, m, 3);
        arg0[0] = m858dotp89u6pk;
        arg0[1] = m858dotp89u6pk2;
        arg0[2] = m858dotp89u6pk3;
        arg0[3] = m858dotp89u6pk4;
        arg0[4] = m858dotp89u6pk5;
        arg0[5] = m858dotp89u6pk6;
        arg0[6] = m858dotp89u6pk7;
        arg0[7] = m858dotp89u6pk8;
        arg0[8] = m858dotp89u6pk9;
        arg0[9] = m858dotp89u6pk10;
        arg0[10] = m858dotp89u6pk11;
        arg0[11] = m858dotp89u6pk12;
        arg0[12] = m858dotp89u6pk13;
        arg0[13] = m858dotp89u6pk14;
        arg0[14] = m858dotp89u6pk15;
        arg0[15] = m858dotp89u6pk16;
    }

    public String toString() {
        return m853toStringimpl(getValues());
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m853toStringimpl(float[] arg0) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        StringBuilder sb = new StringBuilder("\n            |");
        sb.append(arg0[0]).append(' ').append(arg0[1]).append(' ').append(arg0[2]).append(' ').append(arg0[3]).append("|\n            |").append(arg0[4]).append(' ').append(arg0[5]).append(' ').append(arg0[6]).append(' ').append(arg0[7]).append("|\n            |").append(arg0[8]).append(' ').append(arg0[9]).append(' ').append(arg0[10]).append(' ');
        sb.append(arg0[11]).append("|\n            |").append(arg0[12]).append(' ').append(arg0[13]).append(' ').append(arg0[14]).append(' ').append(arg0[15]).append("|\n        ");
        return StringsKt.trimIndent(sb.toString());
    }

    /* renamed from: setFrom-58bKbWc, reason: not valid java name */
    public static final void m851setFrom58bKbWc(float[] arg0, float[] matrix) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        int i = 0;
        while (true) {
            int i2 = i + 1;
            arg0[i] = matrix[i];
            if (i2 > 15) {
                return;
            } else {
                i = i2;
            }
        }
    }

    /* renamed from: rotateX-impl, reason: not valid java name */
    public static final void m845rotateXimpl(float[] arg0, float f) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        double d = (f * 3.141592653589793d) / 180.0d;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        float f2 = arg0[1];
        float f3 = arg0[2];
        float f4 = arg0[5];
        float f5 = arg0[6];
        float f6 = arg0[9];
        float f7 = arg0[10];
        float f8 = arg0[13];
        float f9 = arg0[14];
        arg0[1] = (f2 * cos) - (f3 * sin);
        arg0[2] = (f2 * sin) + (f3 * cos);
        arg0[5] = (f4 * cos) - (f5 * sin);
        arg0[6] = (f4 * sin) + (f5 * cos);
        arg0[9] = (f6 * cos) - (f7 * sin);
        arg0[10] = (f6 * sin) + (f7 * cos);
        arg0[13] = (f8 * cos) - (f9 * sin);
        arg0[14] = (f8 * sin) + (f9 * cos);
    }

    /* renamed from: rotateY-impl, reason: not valid java name */
    public static final void m846rotateYimpl(float[] arg0, float f) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        double d = (f * 3.141592653589793d) / 180.0d;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        float f2 = arg0[0];
        float f3 = arg0[2];
        float f4 = arg0[4];
        float f5 = arg0[6];
        float f6 = arg0[8];
        float f7 = arg0[10];
        float f8 = arg0[12];
        float f9 = arg0[14];
        arg0[0] = (f2 * cos) + (f3 * sin);
        arg0[2] = ((-f2) * sin) + (f3 * cos);
        arg0[4] = (f4 * cos) + (f5 * sin);
        arg0[6] = ((-f4) * sin) + (f5 * cos);
        arg0[8] = (f6 * cos) + (f7 * sin);
        arg0[10] = ((-f6) * sin) + (f7 * cos);
        arg0[12] = (f8 * cos) + (f9 * sin);
        arg0[14] = ((-f8) * sin) + (f9 * cos);
    }

    /* renamed from: rotateZ-impl, reason: not valid java name */
    public static final void m847rotateZimpl(float[] arg0, float f) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        double d = (f * 3.141592653589793d) / 180.0d;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        float f2 = arg0[0];
        float f3 = arg0[4];
        float f4 = -sin;
        float f5 = arg0[1];
        float f6 = arg0[5];
        float f7 = arg0[2];
        float f8 = arg0[6];
        float f9 = arg0[3];
        float f10 = arg0[7];
        arg0[0] = (cos * f2) + (sin * f3);
        arg0[1] = (cos * f5) + (sin * f6);
        arg0[2] = (cos * f7) + (sin * f8);
        arg0[3] = (cos * f9) + (sin * f10);
        arg0[4] = (f2 * f4) + (f3 * cos);
        arg0[5] = (f5 * f4) + (f6 * cos);
        arg0[6] = (f7 * f4) + (f8 * cos);
        arg0[7] = (f4 * f9) + (cos * f10);
    }

    /* renamed from: scale-impl$default, reason: not valid java name */
    public static /* synthetic */ void m849scaleimpl$default(float[] fArr, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        if ((i & 2) != 0) {
            f2 = 1.0f;
        }
        if ((i & 4) != 0) {
            f3 = 1.0f;
        }
        m848scaleimpl(fArr, f, f2, f3);
    }

    /* renamed from: translate-impl$default, reason: not valid java name */
    public static /* synthetic */ void m855translateimpl$default(float[] fArr, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        if ((i & 4) != 0) {
            f3 = 0.0f;
        }
        m854translateimpl(fArr, f, f2, f3);
    }

    /* renamed from: invert-impl, reason: not valid java name */
    public static final void m840invertimpl(float[] arg0) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        float f = arg0[0];
        float f2 = arg0[1];
        float f3 = arg0[2];
        float f4 = arg0[3];
        float f5 = arg0[4];
        float f6 = arg0[5];
        float f7 = arg0[6];
        float f8 = arg0[7];
        float f9 = arg0[8];
        float f10 = arg0[9];
        float f11 = arg0[10];
        float f12 = arg0[11];
        float f13 = arg0[12];
        float f14 = arg0[13];
        float f15 = arg0[14];
        float f16 = arg0[15];
        float f17 = (f * f6) - (f2 * f5);
        float f18 = (f * f7) - (f3 * f5);
        float f19 = (f * f8) - (f4 * f5);
        float f20 = (f2 * f7) - (f3 * f6);
        float f21 = (f2 * f8) - (f4 * f6);
        float f22 = (f3 * f8) - (f4 * f7);
        float f23 = (f9 * f14) - (f10 * f13);
        float f24 = (f9 * f15) - (f11 * f13);
        float f25 = (f9 * f16) - (f12 * f13);
        float f26 = (f10 * f15) - (f11 * f14);
        float f27 = (f10 * f16) - (f12 * f14);
        float f28 = (f11 * f16) - (f12 * f15);
        float f29 = (((((f17 * f28) - (f18 * f27)) + (f19 * f26)) + (f20 * f25)) - (f21 * f24)) + (f22 * f23);
        if (f29 == 0.0f) {
            return;
        }
        float f30 = 1.0f / f29;
        arg0[0] = (((f6 * f28) - (f7 * f27)) + (f8 * f26)) * f30;
        arg0[1] = ((((-f2) * f28) + (f3 * f27)) - (f4 * f26)) * f30;
        arg0[2] = (((f14 * f22) - (f15 * f21)) + (f16 * f20)) * f30;
        arg0[3] = ((((-f10) * f22) + (f11 * f21)) - (f12 * f20)) * f30;
        float f31 = -f5;
        arg0[4] = (((f31 * f28) + (f7 * f25)) - (f8 * f24)) * f30;
        arg0[5] = (((f28 * f) - (f3 * f25)) + (f4 * f24)) * f30;
        float f32 = -f13;
        arg0[6] = (((f32 * f22) + (f15 * f19)) - (f16 * f18)) * f30;
        arg0[7] = (((f22 * f9) - (f11 * f19)) + (f12 * f18)) * f30;
        arg0[8] = (((f5 * f27) - (f6 * f25)) + (f8 * f23)) * f30;
        arg0[9] = ((((-f) * f27) + (f25 * f2)) - (f4 * f23)) * f30;
        arg0[10] = (((f13 * f21) - (f14 * f19)) + (f16 * f17)) * f30;
        arg0[11] = ((((-f9) * f21) + (f19 * f10)) - (f12 * f17)) * f30;
        arg0[12] = (((f31 * f26) + (f6 * f24)) - (f7 * f23)) * f30;
        arg0[13] = (((f * f26) - (f2 * f24)) + (f3 * f23)) * f30;
        arg0[14] = (((f32 * f20) + (f14 * f18)) - (f15 * f17)) * f30;
        arg0[15] = (((f9 * f20) - (f10 * f18)) + (f11 * f17)) * f30;
    }

    /* renamed from: reset-impl, reason: not valid java name */
    public static final void m844resetimpl(float[] arg0) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        int i = 0;
        while (true) {
            int i2 = i + 1;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                arg0[(i3 * 4) + i] = i == i3 ? 1.0f : 0.0f;
                if (i4 > 3) {
                    break;
                } else {
                    i3 = i4;
                }
            }
            if (i2 > 3) {
                return;
            } else {
                i = i2;
            }
        }
    }

    /* renamed from: scale-impl, reason: not valid java name */
    public static final void m848scaleimpl(float[] arg0, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        arg0[0] = arg0[0] * f;
        arg0[1] = arg0[1] * f;
        arg0[2] = arg0[2] * f;
        arg0[3] = arg0[3] * f;
        arg0[4] = arg0[4] * f2;
        arg0[5] = arg0[5] * f2;
        arg0[6] = arg0[6] * f2;
        arg0[7] = arg0[7] * f2;
        arg0[8] = arg0[8] * f3;
        arg0[9] = arg0[9] * f3;
        arg0[10] = arg0[10] * f3;
        arg0[11] = arg0[11] * f3;
    }

    /* renamed from: translate-impl, reason: not valid java name */
    public static final void m854translateimpl(float[] arg0, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        float f4 = (arg0[0] * f) + (arg0[4] * f2) + (arg0[8] * f3) + arg0[12];
        float f5 = (arg0[1] * f) + (arg0[5] * f2) + (arg0[9] * f3) + arg0[13];
        float f6 = (arg0[2] * f) + (arg0[6] * f2) + (arg0[10] * f3) + arg0[14];
        float f7 = (arg0[3] * f) + (arg0[7] * f2) + (arg0[11] * f3) + arg0[15];
        arg0[12] = f4;
        arg0[13] = f5;
        arg0[14] = f6;
        arg0[15] = f7;
    }
}
