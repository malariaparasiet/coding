package androidx.compose.ui.layout;

import androidx.compose.ui.ExperimentalComposeUiApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutInfo.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u00038WX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\u0005¨\u0006\n"}, d2 = {"Landroidx/compose/ui/layout/GraphicLayerInfo;", "", "layerId", "", "getLayerId", "()J", "ownerViewId", "getOwnerViewId$annotations", "()V", "getOwnerViewId", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface GraphicLayerInfo {

    /* compiled from: LayoutInfo.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        @ExperimentalComposeUiApi
        public static long getOwnerViewId(GraphicLayerInfo graphicLayerInfo) {
            Intrinsics.checkNotNullParameter(graphicLayerInfo, "this");
            return 0L;
        }

        @ExperimentalComposeUiApi
        public static /* synthetic */ void getOwnerViewId$annotations() {
        }
    }

    long getLayerId();

    @ExperimentalComposeUiApi
    long getOwnerViewId();
}
