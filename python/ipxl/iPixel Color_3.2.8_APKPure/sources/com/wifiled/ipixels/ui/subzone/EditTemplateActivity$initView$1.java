package com.wifiled.ipixels.ui.subzone;

import android.app.Activity;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendDataCallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EditTemplateActivity.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/wifiled/ipixels/ui/subzone/EditTemplateActivity$initView$1", "Lcom/wifiled/ipixels/core/SendDataCallback;", "onStart", "", "onProgress", "progress", "", "onCompleted", "onError", "error", "onResult", "result", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EditTemplateActivity$initView$1 implements SendDataCallback {
    final /* synthetic */ EditTemplateActivity this$0;

    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onProgress(int progress) {
    }

    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onResult(byte[] result) {
        Intrinsics.checkNotNullParameter(result, "result");
    }

    EditTemplateActivity$initView$1(EditTemplateActivity editTemplateActivity) {
        this.this$0 = editTemplateActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStart$lambda$0(EditTemplateActivity editTemplateActivity) {
        String string = editTemplateActivity.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) editTemplateActivity, false, string, false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onStart() {
        final EditTemplateActivity editTemplateActivity = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$initView$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onStart$lambda$0;
                onStart$lambda$0 = EditTemplateActivity$initView$1.onStart$lambda$0(EditTemplateActivity.this);
                return onStart$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCompleted$lambda$1(EditTemplateActivity editTemplateActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) editTemplateActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onCompleted() {
        final EditTemplateActivity editTemplateActivity = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$initView$1$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onCompleted$lambda$1;
                onCompleted$lambda$1 = EditTemplateActivity$initView$1.onCompleted$lambda$1(EditTemplateActivity.this);
                return onCompleted$lambda$1;
            }
        });
    }

    @Override // com.wifiled.ipixels.core.SendDataCallback
    public void onError(final int error) {
        final EditTemplateActivity editTemplateActivity = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$initView$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$2;
                onError$lambda$2 = EditTemplateActivity$initView$1.onError$lambda$2(EditTemplateActivity.this, error);
                return onError$lambda$2;
            }
        });
        final EditTemplateActivity editTemplateActivity2 = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$initView$1$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$3;
                onError$lambda$3 = EditTemplateActivity$initView$1.onError$lambda$3(EditTemplateActivity.this);
                return onError$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$2(EditTemplateActivity editTemplateActivity, int i) {
        ToastUtil.show(editTemplateActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$3(EditTemplateActivity editTemplateActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) editTemplateActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }
}
