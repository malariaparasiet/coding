package com.wifiled.ipixels.ui.settings;

import androidx.fragment.app.Fragment;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.utils.ByteUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsMainFragment.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/wifiled/ipixels/ui/settings/SettingsMainFragment$bindListener$1$3$1", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsMainFragment$bindListener$1$3$1 implements SendResultCallback {
    final /* synthetic */ byte $language;
    final /* synthetic */ SettingsMainFragment this$0;

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(int result) {
    }

    SettingsMainFragment$bindListener$1$3$1(SettingsMainFragment settingsMainFragment, byte b) {
        this.this$0 = settingsMainFragment;
        this.$language = b;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        Intrinsics.checkNotNullParameter(result, "result");
        LogUtils.vTag("ruis", "deleteAllData----" + ByteUtils.binaryToHexString(result));
        if (result.length == 5 && result[4] == 1) {
            if (this.this$0.isAdded()) {
                SendCore.INSTANCE.getLedType(this.$language, new Function1() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$bindListener$1$3$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit onResult$lambda$1;
                        onResult$lambda$1 = SettingsMainFragment$bindListener$1$3$1.onResult$lambda$1((SendCore.CallbackBuilder) obj);
                        return onResult$lambda$1;
                    }
                });
                final SettingsMainFragment settingsMainFragment = this.this$0;
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$bindListener$1$3$1$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit onResult$lambda$2;
                        onResult$lambda$2 = SettingsMainFragment$bindListener$1$3$1.onResult$lambda$2(SettingsMainFragment.this);
                        return onResult$lambda$2;
                    }
                });
                final SettingsMainFragment settingsMainFragment2 = this.this$0;
                UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$bindListener$1$3$1$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit onResult$lambda$3;
                        onResult$lambda$3 = SettingsMainFragment$bindListener$1$3$1.onResult$lambda$3(SettingsMainFragment.this);
                        return onResult$lambda$3;
                    }
                }, 0L, 2, null);
                return;
            }
            return;
        }
        if (result.length == 5 && result[4] == 0 && this.this$0.isAdded()) {
            final SettingsMainFragment settingsMainFragment3 = this.this$0;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$bindListener$1$3$1$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onResult$lambda$4;
                    onResult$lambda$4 = SettingsMainFragment$bindListener$1$3$1.onResult$lambda$4(SettingsMainFragment.this);
                    return onResult$lambda$4;
                }
            });
            final SettingsMainFragment settingsMainFragment4 = this.this$0;
            UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$bindListener$1$3$1$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onResult$lambda$5;
                    onResult$lambda$5 = SettingsMainFragment$bindListener$1$3$1.onResult$lambda$5(SettingsMainFragment.this);
                    return onResult$lambda$5;
                }
            }, 0L, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$1(SendCore.CallbackBuilder getLedType) {
        Intrinsics.checkNotNullParameter(getLedType, "$this$getLedType");
        getLedType.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.settings.SettingsMainFragment$bindListener$1$3$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onResult$lambda$1$lambda$0;
                onResult$lambda$1$lambda$0 = SettingsMainFragment$bindListener$1$3$1.onResult$lambda$1$lambda$0((byte[]) obj);
                return onResult$lambda$1$lambda$0;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$1$lambda$0(byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$2(SettingsMainFragment settingsMainFragment) {
        String string = settingsMainFragment.getString(R.string.success);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, string, false, 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$3(SettingsMainFragment settingsMainFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$4(SettingsMainFragment settingsMainFragment) {
        String string = settingsMainFragment.getString(R.string.fail);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, string, false, 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResult$lambda$5(SettingsMainFragment settingsMainFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) settingsMainFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }
}
