package com.wifiled.ipixels;

import androidx.lifecycle.ViewModelStore;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: UtilsExtension.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = Opcodes.ARETURN)
/* loaded from: classes3.dex */
public final class UtilsExtensionKt$appViewModels$1 implements Function0<ViewModelStore> {
    public static final UtilsExtensionKt$appViewModels$1 INSTANCE = new UtilsExtensionKt$appViewModels$1();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ViewModelStore invoke() {
        return App.INSTANCE.getInstace().getViewModelStore();
    }
}
