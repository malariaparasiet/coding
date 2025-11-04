package com.wifiled.ipixels.ui.text;

import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.ui.diy.DiyImageFun;
import com.wifiled.ipixels.ui.text.TextChangeTipDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: TextActivity.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"com/wifiled/ipixels/ui/text/TextActivity$showTextChangeTipDialog$1", "Lcom/wifiled/ipixels/ui/text/TextChangeTipDialog$TextChangeTipDialogClickLinstener;", "onSubmitClick", "", "onCancelClick", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextActivity$showTextChangeTipDialog$1 implements TextChangeTipDialog.TextChangeTipDialogClickLinstener {
    final /* synthetic */ TextActivity this$0;

    TextActivity$showTextChangeTipDialog$1(TextActivity textActivity) {
        this.this$0 = textActivity;
    }

    @Override // com.wifiled.ipixels.ui.text.TextChangeTipDialog.TextChangeTipDialogClickLinstener
    public void onSubmitClick() {
        final TextActivity textActivity = this.this$0;
        UtilsExtensionKt.async(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$showTextChangeTipDialog$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onSubmitClick$lambda$0;
                onSubmitClick$lambda$0 = TextActivity$showTextChangeTipDialog$1.onSubmitClick$lambda$0(TextActivity.this);
                return onSubmitClick$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onSubmitClick$lambda$0(TextActivity textActivity) {
        textActivity.sendTextAction(true, true);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.ui.text.TextChangeTipDialog.TextChangeTipDialogClickLinstener
    public void onCancelClick() {
        SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
        this.this$0.finish();
    }
}
