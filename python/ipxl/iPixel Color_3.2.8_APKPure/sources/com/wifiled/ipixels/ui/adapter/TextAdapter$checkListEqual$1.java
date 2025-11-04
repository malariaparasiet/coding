package com.wifiled.ipixels.ui.adapter;

import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextAdapter.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
final /* synthetic */ class TextAdapter$checkListEqual$1 extends FunctionReferenceImpl implements Function1<TextEmojiVO, Integer> {
    public static final TextAdapter$checkListEqual$1 INSTANCE = new TextAdapter$checkListEqual$1();

    TextAdapter$checkListEqual$1() {
        super(1, TextEmojiVO.class, "hashCode", "hashCode()I", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Integer invoke(TextEmojiVO p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Integer.valueOf(p0.hashCode());
    }
}
