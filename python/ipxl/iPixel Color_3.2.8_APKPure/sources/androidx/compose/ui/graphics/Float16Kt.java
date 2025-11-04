package androidx.compose.ui.graphics;

import kotlin.Metadata;

/* compiled from: Float16.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a%\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\u0005\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\b"}, d2 = {"max", "Landroidx/compose/ui/graphics/Float16;", "x", "y", "max-AoSsdG0", "(SS)S", "min", "min-AoSsdG0", "ui-graphics_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class Float16Kt {
    /* renamed from: min-AoSsdG0, reason: not valid java name */
    public static final short m804minAoSsdG0(short s, short s2) {
        if (Float16.m776isNaNimpl(s) || Float16.m776isNaNimpl(s2)) {
            return Float16.INSTANCE.m798getNaNslo4al4();
        }
        return Float16.m763compareTo41bOqos(s, s2) <= 0 ? s : s2;
    }

    /* renamed from: max-AoSsdG0, reason: not valid java name */
    public static final short m803maxAoSsdG0(short s, short s2) {
        if (Float16.m776isNaNimpl(s) || Float16.m776isNaNimpl(s2)) {
            return Float16.INSTANCE.m798getNaNslo4al4();
        }
        return Float16.m763compareTo41bOqos(s, s2) >= 0 ? s : s2;
    }
}
