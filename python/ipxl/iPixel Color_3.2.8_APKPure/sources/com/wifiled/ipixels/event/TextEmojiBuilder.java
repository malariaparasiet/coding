package com.wifiled.ipixels.event;

import android.util.Log;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.google.android.material.card.MaterialCardViewHelper;
import com.google.gson.Gson;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TextEmojiBuilder.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tJ\u0006\u0010\u0010\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\tJ\u0014\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0010\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u0006J\"\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001aR*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/wifiled/ipixels/event/TextEmojiBuilder;", "", "<init>", "()V", "mMapfontMaxlen", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "eventText", "Lcom/wifiled/ipixels/event/EventText;", "textEmojiVO", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "set", "", "value", "restore", "getEventText", "setTextEmojivos", "emojiVOs", "getTextEmojivos", "getCurFontLimit", "size", "checkListEqual", "", "srcList", "", "destList", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextEmojiBuilder {
    public static final TextEmojiBuilder INSTANCE = new TextEmojiBuilder();
    private static HashMap<Integer, Integer> mMapfontMaxlen = MapsKt.hashMapOf(TuplesKt.to(12, 600), TuplesKt.to(16, 500), TuplesKt.to(20, 400), TuplesKt.to(24, Integer.valueOf(MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION)), TuplesKt.to(32, Integer.valueOf(Opcodes.GETFIELD)), TuplesKt.to(64, 100));
    private static EventText eventText = new EventText(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null);
    private static List<TextEmojiVO> textEmojiVO = new ArrayList();

    private TextEmojiBuilder() {
    }

    public final void set(EventText value) {
        Intrinsics.checkNotNullParameter(value, "value");
        eventText = value;
    }

    public final EventText restore() {
        EventText eventText2 = new EventText(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null);
        eventText = eventText2;
        return eventText2;
    }

    public final EventText getEventText() {
        return eventText;
    }

    public final void setTextEmojivos(List<TextEmojiVO> emojiVOs) {
        Intrinsics.checkNotNullParameter(emojiVOs, "emojiVOs");
        textEmojiVO.addAll(CollectionsKt.toMutableList((Collection) emojiVOs));
    }

    public final List<TextEmojiVO> getTextEmojivos() {
        Log.d("akon", "prev TextEmojiBuilder getTextEmojivos textEmojiVO: " + textEmojiVO + " }");
        return textEmojiVO;
    }

    public static /* synthetic */ int getCurFontLimit$default(TextEmojiBuilder textEmojiBuilder, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = eventText.getTextSize();
        }
        return textEmojiBuilder.getCurFontLimit(i);
    }

    public final int getCurFontLimit(int size) {
        Integer num = mMapfontMaxlen.get(Integer.valueOf(size));
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer checkListEqual$lambda$0(Function1 function1, Object obj) {
        return (Integer) function1.invoke(obj);
    }

    public final boolean checkListEqual(List<TextEmojiVO> srcList, List<TextEmojiVO> destList) {
        Intrinsics.checkNotNullParameter(srcList, "srcList");
        Intrinsics.checkNotNullParameter(destList, "destList");
        final TextEmojiBuilder$checkListEqual$1 textEmojiBuilder$checkListEqual$1 = TextEmojiBuilder$checkListEqual$1.INSTANCE;
        Comparator comparing = Comparator.comparing(new Function() { // from class: com.wifiled.ipixels.event.TextEmojiBuilder$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Integer checkListEqual$lambda$0;
                checkListEqual$lambda$0 = TextEmojiBuilder.checkListEqual$lambda$0(Function1.this, obj);
                return checkListEqual$lambda$0;
            }
        });
        Intrinsics.checkNotNullExpressionValue(comparing, "comparing(...)");
        CollectionsKt.sortedWith(srcList, comparing);
        final TextEmojiBuilder$checkListEqual$2 textEmojiBuilder$checkListEqual$2 = TextEmojiBuilder$checkListEqual$2.INSTANCE;
        Comparator comparing2 = Comparator.comparing(new Function() { // from class: com.wifiled.ipixels.event.TextEmojiBuilder$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Integer checkListEqual$lambda$1;
                checkListEqual$lambda$1 = TextEmojiBuilder.checkListEqual$lambda$1(Function1.this, obj);
                return checkListEqual$lambda$1;
            }
        });
        Intrinsics.checkNotNullExpressionValue(comparing2, "comparing(...)");
        CollectionsKt.sortedWith(destList, comparing2);
        if (srcList.size() != destList.size()) {
            return false;
        }
        if (Intrinsics.areEqual(srcList.toString(), destList.toString())) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<TextEmojiVO> it = srcList.iterator();
        while (it.hasNext()) {
            try {
                sb.append(new JSONObject(new Gson().toJson(it.next())).optString(TextBundle.TEXT_ENTRY));
            } catch (JSONException e) {
                e.printStackTrace();
                Unit unit = Unit.INSTANCE;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        Iterator<TextEmojiVO> it2 = destList.iterator();
        while (it2.hasNext()) {
            try {
                sb2.append(new JSONObject(new Gson().toJson(it2.next())).optString(TextBundle.TEXT_ENTRY));
            } catch (JSONException e2) {
                e2.printStackTrace();
                Unit unit2 = Unit.INSTANCE;
            }
        }
        return Intrinsics.areEqual(sb.toString(), sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer checkListEqual$lambda$1(Function1 function1, Object obj) {
        return (Integer) function1.invoke(obj);
    }
}
