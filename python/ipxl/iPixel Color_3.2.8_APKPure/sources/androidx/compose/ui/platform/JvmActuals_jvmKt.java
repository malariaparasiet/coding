package androidx.compose.ui.platform;

import androidx.autofill.HintConstants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: JvmActuals.jvm.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0003H\u0000*\f\b\u0000\u0010\u0006\"\u00020\u00072\u00020\u0007¨\u0006\b"}, d2 = {"simpleIdentityToString", "", "obj", "", HintConstants.AUTOFILL_HINT_NAME, "nativeClass", "AtomicInt", "Ljava/util/concurrent/atomic/AtomicInteger;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class JvmActuals_jvmKt {
    public static final String simpleIdentityToString(Object obj, String str) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        if (str == null) {
            if (obj.getClass().isAnonymousClass()) {
                str = obj.getClass().getName();
            } else {
                str = obj.getClass().getSimpleName();
            }
        }
        StringBuilder append = new StringBuilder().append(str).append('@');
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%07x", Arrays.copyOf(new Object[]{Integer.valueOf(System.identityHashCode(obj))}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
        return append.append(format).toString();
    }

    public static final Object nativeClass(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        return obj.getClass();
    }
}
