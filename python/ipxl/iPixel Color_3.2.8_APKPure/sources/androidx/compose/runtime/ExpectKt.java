package androidx.compose.runtime;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: Expect.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u0000¨\u0006\u0003"}, d2 = {"ThreadLocal", "Landroidx/compose/runtime/ThreadLocal;", ExifInterface.GPS_DIRECTION_TRUE, "runtime_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ExpectKt {
    public static final <T> ThreadLocal<T> ThreadLocal() {
        return new ThreadLocal<>(new Function0<T>() { // from class: androidx.compose.runtime.ExpectKt$ThreadLocal$1
            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                return null;
            }
        });
    }
}
