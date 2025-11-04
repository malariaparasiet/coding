package com.wifiled.ipixels;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UtilsExtension.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = Opcodes.ARETURN)
/* loaded from: classes3.dex */
public final class UtilsExtensionKt$appViewModels$factoryPromise$1 implements Function0<ViewModelProvider.Factory> {
    final /* synthetic */ ComponentActivity $this_appViewModels;

    public UtilsExtensionKt$appViewModels$factoryPromise$1(ComponentActivity componentActivity) {
        this.$this_appViewModels = componentActivity;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ViewModelProvider.Factory invoke() {
        ViewModelProvider.Factory defaultViewModelProviderFactory = this.$this_appViewModels.getDefaultViewModelProviderFactory();
        Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "<get-defaultViewModelProviderFactory>(...)");
        return defaultViewModelProviderFactory;
    }
}
