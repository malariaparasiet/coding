package com.wifiled.ipixels.ui.text;

import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.send.SendResultCallback;
import io.reactivex.disposables.Disposable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextActivity.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/wifiled/ipixels/ui/text/TextActivity$sendTextAction$6", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextActivity$sendTextAction$6 implements SendResultCallback {
    final /* synthetic */ boolean $isDown;
    final /* synthetic */ boolean $isFinish;
    final /* synthetic */ TextActivity this$0;

    TextActivity$sendTextAction$6(TextActivity textActivity, boolean z, boolean z2) {
        this.this$0 = textActivity;
        this.$isDown = z;
        this.$isFinish = z2;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        Disposable disposable;
        Disposable disposable2;
        Disposable disposable3;
        Intrinsics.checkNotNullParameter(result, "result");
        if (Arrays.equals(result, new byte[]{5, 0, 8, ByteCompanionObject.MIN_VALUE, 1})) {
            disposable = this.this$0.disposable;
            if (disposable != null) {
                disposable2 = this.this$0.disposable;
                Intrinsics.checkNotNull(disposable2);
                if (!disposable2.isDisposed()) {
                    disposable3 = this.this$0.disposable;
                    Intrinsics.checkNotNull(disposable3);
                    disposable3.dispose();
                }
            }
            this.this$0.sendText(this.$isDown, this.$isFinish);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$0(TextActivity textActivity, int i) {
        ToastUtil.show(textActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(final int result) {
        Disposable disposable;
        Disposable disposable2;
        Disposable disposable3;
        final TextActivity textActivity = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$sendTextAction$6$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$0;
                onError$lambda$0 = TextActivity$sendTextAction$6.onError$lambda$0(TextActivity.this, result);
                return onError$lambda$0;
            }
        });
        disposable = this.this$0.disposable;
        if (disposable != null) {
            disposable2 = this.this$0.disposable;
            Intrinsics.checkNotNull(disposable2);
            if (disposable2.isDisposed()) {
                return;
            }
            disposable3 = this.this$0.disposable;
            Intrinsics.checkNotNull(disposable3);
            disposable3.dispose();
        }
    }
}
