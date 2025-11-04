package androidx.compose.ui.graphics;

import android.graphics.Shader;
import kotlin.Metadata;

/* compiled from: AndroidTileMode.android.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0005"}, d2 = {"toAndroidTileMode", "Landroid/graphics/Shader$TileMode;", "Landroidx/compose/ui/graphics/TileMode;", "toAndroidTileMode-0vamqd0", "(I)Landroid/graphics/Shader$TileMode;", "ui-graphics_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidTileMode_androidKt {
    /* renamed from: toAndroidTileMode-0vamqd0, reason: not valid java name */
    public static final Shader.TileMode m585toAndroidTileMode0vamqd0(int i) {
        return TileMode.m957equalsimpl0(i, TileMode.INSTANCE.m961getClamp3opZhB0()) ? Shader.TileMode.CLAMP : TileMode.m957equalsimpl0(i, TileMode.INSTANCE.m963getRepeated3opZhB0()) ? Shader.TileMode.REPEAT : TileMode.m957equalsimpl0(i, TileMode.INSTANCE.m962getMirror3opZhB0()) ? Shader.TileMode.MIRROR : Shader.TileMode.CLAMP;
    }
}
