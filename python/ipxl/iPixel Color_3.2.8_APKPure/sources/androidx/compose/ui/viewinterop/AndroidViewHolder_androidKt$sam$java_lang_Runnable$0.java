package androidx.compose.ui.viewinterop;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: AndroidViewHolder.android.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class AndroidViewHolder_androidKt$sam$java_lang_Runnable$0 implements Runnable {
    private final /* synthetic */ Function0 function;

    AndroidViewHolder_androidKt$sam$java_lang_Runnable$0(Function0 function0) {
        this.function = function0;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.function.invoke();
    }
}
