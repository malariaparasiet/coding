package com.wifiled.ipixels.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: InputFilterUtils.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/wifiled/ipixels/utils/InputFilterUtils;", "", "<init>", "()V", "Charset_UTF", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "Charset_GB", "addFilter", "", "editText", "Landroid/widget/EditText;", "filter", "Landroid/text/InputFilter;", "lengthFilter", "maxLen", "", "customLengthFilter", "isAlphabetic", "", "str", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class InputFilterUtils {
    public static final InputFilterUtils INSTANCE = new InputFilterUtils();
    private static final Charset Charset_UTF = StandardCharsets.UTF_8;
    private static final Charset Charset_GB = Charset.forName("GB2312");

    private InputFilterUtils() {
    }

    private final void addFilter(EditText editText, InputFilter filter) {
        InputFilter[] inputFilterArr = new InputFilter[editText.getFilters().length + 1];
        InputFilter[] filters = editText.getFilters();
        Intrinsics.checkNotNullExpressionValue(filters, "getFilters(...)");
        InputFilter[] inputFilterArr2 = filters;
        int length = inputFilterArr2.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            InputFilter inputFilter = inputFilterArr2[i];
            if (inputFilter != null) {
                inputFilterArr[i2] = inputFilter;
            }
            i++;
            i2 = i3;
        }
        inputFilterArr[editText.getFilters().length] = filter;
        editText.setFilters(inputFilterArr);
    }

    public final void lengthFilter(final EditText editText, final int maxLen) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        addFilter(editText, new InputFilter() { // from class: com.wifiled.ipixels.utils.InputFilterUtils$$ExternalSyntheticLambda1
            @Override // android.text.InputFilter
            public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                CharSequence lengthFilter$lambda$1;
                lengthFilter$lambda$1 = InputFilterUtils.lengthFilter$lambda$1(editText, maxLen, charSequence, i, i2, spanned, i3, i4);
                return lengthFilter$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence lengthFilter$lambda$1(EditText editText, int i, CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        String obj = editText.getText().toString();
        Charset Charset_GB2 = Charset_GB;
        Intrinsics.checkNotNullExpressionValue(Charset_GB2, "Charset_GB");
        byte[] bytes = obj.getBytes(Charset_GB2);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String obj2 = charSequence.toString();
        Intrinsics.checkNotNullExpressionValue(Charset_GB2, "Charset_GB");
        byte[] bytes2 = obj2.getBytes(Charset_GB2);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        if (bytes.length + bytes2.length > i) {
            return "";
        }
        return null;
    }

    public final void customLengthFilter(EditText editText, final int maxLen) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        addFilter(editText, new InputFilter() { // from class: com.wifiled.ipixels.utils.InputFilterUtils$$ExternalSyntheticLambda0
            @Override // android.text.InputFilter
            public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                CharSequence customLengthFilter$lambda$2;
                customLengthFilter$lambda$2 = InputFilterUtils.customLengthFilter$lambda$2(maxLen, charSequence, i, i2, spanned, i3, i4);
                return customLengthFilter$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence customLengthFilter$lambda$2(int i, CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        String obj = spanned.toString();
        String obj2 = charSequence.toString();
        Charset Charset_GB2 = Charset_GB;
        Intrinsics.checkNotNullExpressionValue(Charset_GB2, "Charset_GB");
        byte[] bytes = obj.getBytes(Charset_GB2);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        int length = bytes.length;
        Intrinsics.checkNotNullExpressionValue(Charset_GB2, "Charset_GB");
        byte[] bytes2 = obj2.getBytes(Charset_GB2);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        int length2 = bytes2.length;
        Log.v("ruis", "destLen---" + length + " " + obj);
        Log.v("ruis", "sourceLen---" + length2 + " " + obj2);
        InputFilterUtils inputFilterUtils = INSTANCE;
        String str = null;
        if (inputFilterUtils.isAlphabetic(obj2) && inputFilterUtils.isAlphabetic(obj)) {
            if (length2 > i) {
                String obj3 = charSequence.subSequence(0, i).toString();
                Intrinsics.checkNotNullExpressionValue(Charset_GB2, "Charset_GB");
                byte[] bytes3 = obj3.getBytes(Charset_GB2);
                Intrinsics.checkNotNullExpressionValue(bytes3, "getBytes(...)");
                byte[] byteArray = CollectionsKt.toByteArray(ArraysKt.take(bytes3, i));
                Intrinsics.checkNotNullExpressionValue(Charset_GB2, "Charset_GB");
                str = new String(byteArray, Charset_GB2);
            }
            return str;
        }
        if (length2 + length > i) {
            int i6 = i - length;
            if (i6 <= 0) {
                str = "";
            } else {
                String obj4 = charSequence.subSequence(i2, i3).toString();
                Intrinsics.checkNotNullExpressionValue(Charset_GB2, "Charset_GB");
                byte[] bytes4 = obj4.getBytes(Charset_GB2);
                Intrinsics.checkNotNullExpressionValue(bytes4, "getBytes(...)");
                byte[] byteArray2 = CollectionsKt.toByteArray(ArraysKt.take(bytes4, i6));
                Intrinsics.checkNotNullExpressionValue(Charset_GB2, "Charset_GB");
                str = new String(byteArray2, Charset_GB2);
            }
        }
        return str;
    }

    public final boolean isAlphabetic(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return new Regex("[a-zA-Z]+").matches(str);
    }
}
