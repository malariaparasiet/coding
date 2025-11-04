package androidx.compose.ui.text.platform;

import android.graphics.Typeface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypefaceAdapter.android.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/ui/text/platform/TypefaceAdapterHelperMethods;", "", "()V", "create", "Landroid/graphics/Typeface;", "typeface", "finalFontWeight", "", "finalFontStyle", "", "ui-text_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TypefaceAdapterHelperMethods {
    public static final TypefaceAdapterHelperMethods INSTANCE = new TypefaceAdapterHelperMethods();

    private TypefaceAdapterHelperMethods() {
    }

    public final Typeface create(Typeface typeface, int finalFontWeight, boolean finalFontStyle) {
        Intrinsics.checkNotNullParameter(typeface, "typeface");
        Typeface create = Typeface.create(typeface, finalFontWeight, finalFontStyle);
        Intrinsics.checkNotNullExpressionValue(create, "create(typeface, finalFontWeight, finalFontStyle)");
        return create;
    }
}
