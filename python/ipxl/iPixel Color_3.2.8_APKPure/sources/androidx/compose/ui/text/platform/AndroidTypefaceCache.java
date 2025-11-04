package androidx.compose.ui.text.platform;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import androidx.collection.LruCache;
import androidx.compose.ui.text.font.AndroidAssetFont;
import androidx.compose.ui.text.font.AndroidFileDescriptorFont;
import androidx.compose.ui.text.font.AndroidFileFont;
import androidx.compose.ui.text.font.AndroidFont;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.ResourceFont;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidFontListTypeface.android.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidTypefaceCache;", "", "()V", "cache", "Landroidx/collection/LruCache;", "", "Landroid/graphics/Typeface;", "getKey", "context", "Landroid/content/Context;", "font", "Landroidx/compose/ui/text/font/Font;", "getOrCreate", "ui-text_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidTypefaceCache {
    public static final AndroidTypefaceCache INSTANCE = new AndroidTypefaceCache();
    private static final LruCache<String, Typeface> cache = new LruCache<>(16);

    private AndroidTypefaceCache() {
    }

    public final Typeface getOrCreate(Context context, Font font) {
        Typeface typefaceInternal;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(font, "font");
        String key = getKey(context, font);
        if (key != null) {
            Typeface typeface = cache.get(key);
            if (typeface != null) {
                return typeface;
            }
        }
        if (font instanceof ResourceFont) {
            typefaceInternal = AndroidResourceFontLoaderHelper.INSTANCE.create(context, ((ResourceFont) font).getResId());
        } else {
            if (!(font instanceof AndroidFont)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown font type: ", font));
            }
            typefaceInternal = ((AndroidFont) font).getTypefaceInternal();
        }
        if (key == null) {
            return typefaceInternal;
        }
        cache.put(key, typefaceInternal);
        return typefaceInternal;
    }

    public final String getKey(Context context, Font font) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(font, "font");
        if (font instanceof ResourceFont) {
            TypedValue typedValue = new TypedValue();
            context.getResources().getValue(((ResourceFont) font).getResId(), typedValue, true);
            CharSequence charSequence = typedValue.string;
            String obj = charSequence != null ? charSequence.toString() : null;
            Intrinsics.checkNotNull(obj);
            return Intrinsics.stringPlus("res:", obj);
        }
        if (font instanceof AndroidAssetFont) {
            return Intrinsics.stringPlus("asset:", ((AndroidAssetFont) font).getPath());
        }
        if ((font instanceof AndroidFileFont) || (font instanceof AndroidFileDescriptorFont)) {
            return null;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown font type: ", font));
    }
}
