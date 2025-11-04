package com.wifiled.ipixels;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UtilsExtension.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = Opcodes.ARETURN)
/* loaded from: classes3.dex */
public final class UtilsExtensionKt$appViewModels$2 implements Function0<CreationExtras> {
    final /* synthetic */ Fragment $this_appViewModels;

    public UtilsExtensionKt$appViewModels$2(Fragment fragment) {
        this.$this_appViewModels = fragment;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final CreationExtras invoke() {
        CreationExtras defaultViewModelCreationExtras = this.$this_appViewModels.getDefaultViewModelCreationExtras();
        Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
        return defaultViewModelCreationExtras;
    }
}
