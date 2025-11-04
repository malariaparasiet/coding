package com.wifiled.ipixels.ui.text;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EmojiExcludeFilter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J>\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\fH\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\fH\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/wifiled/ipixels/ui/text/EmojiExcludeFilter;", "Landroid/text/InputFilter;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "imageGetter", "Landroid/text/Html$ImageGetter;", "getImageGetter", "()Landroid/text/Html$ImageGetter;", "specificUnicodePoints", "", "", "filter", "", "source", "start", "end", "dest", "Landroid/text/Spanned;", "dstart", "dend", "isMyEmoji", "", "codePoint", "isEmoji", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EmojiExcludeFilter implements InputFilter {
    private final Html.ImageGetter imageGetter;
    private final Set<Integer> specificUnicodePoints;

    private final boolean isMyEmoji(int codePoint) {
        if (codePoint == 0 || codePoint == 9 || codePoint == 10 || codePoint == 13) {
            return true;
        }
        if (32 <= codePoint && codePoint < 55296) {
            return true;
        }
        if (57344 > codePoint || codePoint >= 65534) {
            return 65536 <= codePoint && codePoint < 1114112;
        }
        return true;
    }

    public EmojiExcludeFilter(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.imageGetter = new Html.ImageGetter() { // from class: com.wifiled.ipixels.ui.text.EmojiExcludeFilter$$ExternalSyntheticLambda0
            @Override // android.text.Html.ImageGetter
            public final Drawable getDrawable(String str) {
                Drawable imageGetter$lambda$0;
                imageGetter$lambda$0 = EmojiExcludeFilter.imageGetter$lambda$0(context, str);
                return imageGetter$lambda$0;
            }
        };
        this.specificUnicodePoints = SetsKt.setOf((Object[]) new Integer[]{10084, 65039, 10024, 9731, 9924, 9785, 9786, 9749, 9996, 9994, 9995, 9997, 9757});
    }

    public final Html.ImageGetter getImageGetter() {
        return this.imageGetter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Drawable imageGetter$lambda$0(Context context, String str) {
        Intrinsics.checkNotNull(str);
        Drawable drawable = context.getDrawable(Integer.parseInt(str));
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        return drawable;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (source == null) {
            return null;
        }
        Integer valueOf = dest != null ? Integer.valueOf(dest.length()) : null;
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNull(valueOf);
        int intValue = valueOf.intValue();
        for (int i = 0; i < intValue; i++) {
            char charAt = dest.charAt(i);
            if (isMyEmoji(charAt)) {
                Log.v("ruis", "c-------" + charAt);
                if (charAt == 65532) {
                    Log.v("ruis", "dest[index]-------" + ((Object) dest));
                    ImageSpan[] imageSpanArr = (ImageSpan[]) dest.getSpans(i, i + 1, ImageSpan.class);
                    if (imageSpanArr != null) {
                        if (!(imageSpanArr.length == 0)) {
                            sb.append((CharSequence) Html.fromHtml("<img src='" + imageSpanArr[0].getSource() + "'/>", this.imageGetter, null));
                        }
                    }
                } else {
                    sb.append(charAt);
                }
            } else if (!isEmoji(charAt) && !this.specificUnicodePoints.contains(Integer.valueOf(charAt))) {
                sb.append(charAt);
            }
        }
        return sb.length() == 0 ? "" : sb.toString();
    }

    private final boolean isEmoji(int codePoint) {
        if (Character.getType(codePoint) == 19 || Character.getType(codePoint) == 28) {
            return true;
        }
        if (128512 <= codePoint && codePoint < 128592) {
            return true;
        }
        if (127744 <= codePoint && codePoint < 128512) {
            return true;
        }
        if (128640 <= codePoint && codePoint < 128768) {
            return true;
        }
        if (128768 <= codePoint && codePoint < 128896) {
            return true;
        }
        if (128896 <= codePoint && codePoint < 129024) {
            return true;
        }
        if (129024 <= codePoint && codePoint < 129280) {
            return true;
        }
        if (129280 <= codePoint && codePoint < 129536) {
            return true;
        }
        if (129536 <= codePoint && codePoint < 129648) {
            return true;
        }
        if (129648 <= codePoint && codePoint < 129792) {
            return true;
        }
        if (9728 <= codePoint && codePoint < 9984) {
            return true;
        }
        if (9984 > codePoint || codePoint >= 10176) {
            return 11088 <= codePoint && codePoint < 11094;
        }
        return true;
    }
}
