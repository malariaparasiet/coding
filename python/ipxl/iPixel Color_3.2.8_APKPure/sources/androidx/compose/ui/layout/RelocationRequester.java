package androidx.compose.ui.layout;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ExperimentalComposeUiApi;
import kotlin.Metadata;

/* compiled from: RelocationRequester.kt */
@ExperimentalComposeUiApi
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/ui/layout/RelocationRequester;", "", "()V", "modifiers", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/layout/RelocationRequesterModifier;", "getModifiers$ui_release", "()Landroidx/compose/runtime/collection/MutableVector;", "bringIntoView", "", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class RelocationRequester {
    public static final int $stable = MutableVector.$stable;
    private final MutableVector<RelocationRequesterModifier> modifiers = new MutableVector<>(new RelocationRequesterModifier[16], 0);

    public final MutableVector<RelocationRequesterModifier> getModifiers$ui_release() {
        return this.modifiers;
    }

    public final void bringIntoView() {
        MutableVector<RelocationRequesterModifier> mutableVector = this.modifiers;
        int size = mutableVector.getSize();
        if (size > 0) {
            RelocationRequesterModifier[] content = mutableVector.getContent();
            int i = 0;
            do {
                content[i].bringIntoView();
                i++;
            } while (i < size);
        }
    }
}
