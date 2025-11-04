package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.view.ViewGroup;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.core.text.TextAgreement;
import com.wifiled.ipixels.core.text.TextEmojiBGRUtils;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.utils.ResourceUtils;
import com.wifiled.ipixels.view.LedView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;
import org.json.JSONException;

/* compiled from: TextAdapter.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001/B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0002H\u0016J\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00020\u0006J\u001c\u0010!\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0016R\u00020\u00170\u00150\u0013J\u000e\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\fJ\u000e\u0010$\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010%\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\fJ\b\u0010&\u001a\u00020\fH\u0016J\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015J\u0006\u0010(\u001a\u00020\u001cJ\u0006\u0010)\u001a\u00020\u001cJ\u0014\u0010*\u001a\u00020\u001c2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015J\"\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00020\u00152\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0016R\u00020\u00170\u00150\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/TextAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "context", "Landroid/content/Context;", "data", "", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "refreshMode", "Lcom/wifiled/ipixels/ui/adapter/TextAdapter$REFRESH_MODE;", "typeFace", "", "textColor", "emojis", "", "kotlin.jvm.PlatformType", "mutableListSaveBitmap", "mapListItems", "", "", "", "Lcom/wifiled/ipixels/view/LedView$ItemView;", "Lcom/wifiled/ipixels/view/LedView;", "curTextEmojiVO", "gsonIns", "Lcom/google/gson/Gson;", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "textEmojiVO", "getMutableBitmaps", "getMapItemViews", "setTextSize", "size", "setTypeFace", "setTextColor", "getItemCount", "getData", "clear", "clearSaveMap", "addData", "checkListEqual", "", "srcList", "destList", "REFRESH_MODE", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAdapter extends RecyclerAdapter<TextEmojiVO> {
    private TextEmojiVO curTextEmojiVO;
    private int[] emojis;
    private Gson gsonIns;
    private Map<String, List<LedView.ItemView>> mapListItems;
    private List<TextEmojiVO> mutableListSaveBitmap;
    private REFRESH_MODE refreshMode;
    private int textColor;
    private int typeFace;

    /* compiled from: TextAdapter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[REFRESH_MODE.values().length];
            try {
                iArr[REFRESH_MODE.MODE_TEXT_ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextAdapter(Context context, List<TextEmojiVO> data) {
        super(context, data, R.layout.item_text);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.refreshMode = REFRESH_MODE.MODE_NULL;
        this.typeFace = AppConfig.INSTANCE.getTypeFace();
        this.textColor = -1;
        this.emojis = ResourceUtils.getResIds(context, R.array.emoji_big);
        this.mutableListSaveBitmap = new ArrayList();
        this.mapListItems = new LinkedHashMap();
        this.gsonIns = new Gson();
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: TextAdapter.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/TextAdapter$REFRESH_MODE;", "", "<init>", "(Ljava/lang/String;I)V", "MODE_NULL", "MODE_TEXT_COLOR", "MODE_TEXT_TYPEFACE", "MODE_TEXT_SIZE", "MODE_TEXT_ALPHA", "MODE_TEXT_SPEED", "MODE_TEXT_EFFECT", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class REFRESH_MODE {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ REFRESH_MODE[] $VALUES;
        public static final REFRESH_MODE MODE_NULL = new REFRESH_MODE("MODE_NULL", 0);
        public static final REFRESH_MODE MODE_TEXT_COLOR = new REFRESH_MODE("MODE_TEXT_COLOR", 1);
        public static final REFRESH_MODE MODE_TEXT_TYPEFACE = new REFRESH_MODE("MODE_TEXT_TYPEFACE", 2);
        public static final REFRESH_MODE MODE_TEXT_SIZE = new REFRESH_MODE("MODE_TEXT_SIZE", 3);
        public static final REFRESH_MODE MODE_TEXT_ALPHA = new REFRESH_MODE("MODE_TEXT_ALPHA", 4);
        public static final REFRESH_MODE MODE_TEXT_SPEED = new REFRESH_MODE("MODE_TEXT_SPEED", 5);
        public static final REFRESH_MODE MODE_TEXT_EFFECT = new REFRESH_MODE("MODE_TEXT_EFFECT", 6);

        private static final /* synthetic */ REFRESH_MODE[] $values() {
            return new REFRESH_MODE[]{MODE_NULL, MODE_TEXT_COLOR, MODE_TEXT_TYPEFACE, MODE_TEXT_SIZE, MODE_TEXT_ALPHA, MODE_TEXT_SPEED, MODE_TEXT_EFFECT};
        }

        public static EnumEntries<REFRESH_MODE> getEntries() {
            return $ENTRIES;
        }

        public static REFRESH_MODE valueOf(String str) {
            return (REFRESH_MODE) Enum.valueOf(REFRESH_MODE.class, str);
        }

        public static REFRESH_MODE[] values() {
            return (REFRESH_MODE[]) $VALUES.clone();
        }

        private REFRESH_MODE(String str, int i) {
        }

        static {
            REFRESH_MODE[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public void convert(RecyclerViewHolder holder, TextEmojiVO textEmojiVO) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(textEmojiVO, "textEmojiVO");
        SystemClock.elapsedRealtime();
        int textWidth = 64 / textEmojiVO.getTextWidth();
        int textHeight = 512 / (64 / textEmojiVO.getTextHeight());
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.width = 512 / textWidth;
        layoutParams.height = textHeight;
        holder.itemView.setLayoutParams(layoutParams);
        LedView ledView = (LedView) holder.getView(R.id.ledView);
        if (this.mutableListSaveBitmap.indexOf(textEmojiVO) != -1 && (this.refreshMode == REFRESH_MODE.MODE_TEXT_COLOR || this.refreshMode == REFRESH_MODE.MODE_TEXT_SPEED || this.refreshMode == REFRESH_MODE.MODE_TEXT_ALPHA)) {
            ledView.setEnabled(false);
            ledView.setOnShow(true);
            JSONObject parseObject = JSON.parseObject(this.gsonIns.toJson(textEmojiVO));
            parseObject.remove("textColor");
            if (WhenMappings.$EnumSwitchMapping$0[this.refreshMode.ordinal()] == 1) {
                ledView.onlyRefreshAlpha(textEmojiVO, this.mapListItems.get(parseObject.toJSONString()));
                return;
            } else {
                ledView.renderTextcolor(textEmojiVO, this.mapListItems.get(parseObject.toJSONString()));
                return;
            }
        }
        if (this.mutableListSaveBitmap.indexOf(textEmojiVO) == -1 || this.mData.contains(textEmojiVO)) {
            this.mutableListSaveBitmap.add(textEmojiVO);
        }
        ledView.clear();
        ledView.setEnabled(false);
        ledView.setOnShow(true);
        ledView.init(textEmojiVO.getTextWidth(), textEmojiVO.getTextHeight());
        Bitmap bitmapByTextEmoji$default = TextEmojiBGRUtils.getBitmapByTextEmoji$default(TextEmojiBGRUtils.INSTANCE, textEmojiVO, false, 2, null);
        if (textEmojiVO.getType() == 0 || textEmojiVO.getType() == -1) {
            byte[] bitmap2BGR = BGRUtils.bitmap2BGR(bitmapByTextEmoji$default);
            switch (textEmojiVO.getTextColor()) {
                case 2:
                    bitmap2BGR = TextAgreement.bitmap2GradientBGR(bitmapByTextEmoji$default, 2);
                    break;
                case 3:
                    bitmap2BGR = TextAgreement.bitmap2GradientBGR(bitmapByTextEmoji$default, 3);
                    break;
                case 4:
                    bitmap2BGR = TextAgreement.bitmap2GradientBGR(bitmapByTextEmoji$default, 4);
                    break;
                case 5:
                    bitmap2BGR = TextAgreement.bitmap2GradientBGR(bitmapByTextEmoji$default, 5);
                    break;
                case 6:
                    bitmap2BGR = TextAgreement.bitmap2GradientBGR(bitmapByTextEmoji$default, 6);
                    break;
                case 7:
                    bitmap2BGR = TextAgreement.bitmap2GradientBGR(bitmapByTextEmoji$default, 7);
                    break;
                case 8:
                    bitmap2BGR = TextAgreement.bitmap2GradientBGR(bitmapByTextEmoji$default, 8);
                    break;
                case 9:
                    bitmap2BGR = TextAgreement.bitmap2GradientBGR(bitmapByTextEmoji$default, 9);
                    break;
            }
            ledView.setData(bitmap2BGR);
            return;
        }
        if (textEmojiVO.getType() == 1) {
            ledView.setData(bitmapByTextEmoji$default != null ? TextEmojiBGRUtils.INSTANCE.getEmojiBGR(bitmapByTextEmoji$default, textEmojiVO.getTextColor()) : null);
        }
    }

    public final List<TextEmojiVO> getMutableBitmaps() {
        return this.mutableListSaveBitmap;
    }

    public final Map<String, List<LedView.ItemView>> getMapItemViews() {
        return this.mapListItems;
    }

    public final void setTextSize(int size) {
        this.refreshMode = REFRESH_MODE.MODE_TEXT_SIZE;
        clearSaveMap();
        notifyDataSetChanged();
    }

    public final void setTypeFace(int typeFace) {
        this.typeFace = typeFace;
        this.refreshMode = REFRESH_MODE.MODE_TEXT_TYPEFACE;
        clearSaveMap();
        notifyDataSetChanged();
    }

    public final void setTextColor(int textColor) {
        this.textColor = textColor;
        this.refreshMode = REFRESH_MODE.MODE_TEXT_COLOR;
        notifyDataSetChanged();
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    public final List<TextEmojiVO> getData() {
        List mData = this.mData;
        Intrinsics.checkNotNullExpressionValue(mData, "mData");
        return mData;
    }

    public final void clear() {
        if (getItemCount() > 0) {
            this.mData.clear();
        }
    }

    public final void clearSaveMap() {
        this.mutableListSaveBitmap.clear();
        this.mapListItems.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addData(List<TextEmojiVO> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.mData = data;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer checkListEqual$lambda$2(Function1 function1, Object obj) {
        return (Integer) function1.invoke(obj);
    }

    public final boolean checkListEqual(List<TextEmojiVO> srcList, List<TextEmojiVO> destList) {
        Intrinsics.checkNotNullParameter(srcList, "srcList");
        Intrinsics.checkNotNullParameter(destList, "destList");
        final TextAdapter$checkListEqual$1 textAdapter$checkListEqual$1 = TextAdapter$checkListEqual$1.INSTANCE;
        Comparator comparing = Comparator.comparing(new Function() { // from class: com.wifiled.ipixels.ui.adapter.TextAdapter$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Integer checkListEqual$lambda$2;
                checkListEqual$lambda$2 = TextAdapter.checkListEqual$lambda$2(Function1.this, obj);
                return checkListEqual$lambda$2;
            }
        });
        Intrinsics.checkNotNullExpressionValue(comparing, "comparing(...)");
        CollectionsKt.sortedWith(srcList, comparing);
        final TextAdapter$checkListEqual$2 textAdapter$checkListEqual$2 = TextAdapter$checkListEqual$2.INSTANCE;
        Comparator comparing2 = Comparator.comparing(new Function() { // from class: com.wifiled.ipixels.ui.adapter.TextAdapter$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Integer checkListEqual$lambda$3;
                checkListEqual$lambda$3 = TextAdapter.checkListEqual$lambda$3(Function1.this, obj);
                return checkListEqual$lambda$3;
            }
        });
        Intrinsics.checkNotNullExpressionValue(comparing2, "comparing(...)");
        CollectionsKt.sortedWith(destList, comparing2);
        if (srcList.toString().equals(destList.toString())) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<TextEmojiVO> it = srcList.iterator();
        while (it.hasNext()) {
            try {
                org.json.JSONObject jSONObject = new org.json.JSONObject(new Gson().toJson(it.next()));
                sb.append(jSONObject.optString(TextBundle.TEXT_ENTRY) + jSONObject.optString("resPosition"));
            } catch (JSONException e) {
                e.printStackTrace();
                Unit unit = Unit.INSTANCE;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        Iterator<TextEmojiVO> it2 = destList.iterator();
        while (it2.hasNext()) {
            try {
                org.json.JSONObject jSONObject2 = new org.json.JSONObject(new Gson().toJson(it2.next()));
                sb2.append(jSONObject2.optString(TextBundle.TEXT_ENTRY) + jSONObject2.optString("resPosition"));
            } catch (JSONException e2) {
                e2.printStackTrace();
                Unit unit2 = Unit.INSTANCE;
            }
        }
        return sb.toString().equals(sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer checkListEqual$lambda$3(Function1 function1, Object obj) {
        return (Integer) function1.invoke(obj);
    }
}
