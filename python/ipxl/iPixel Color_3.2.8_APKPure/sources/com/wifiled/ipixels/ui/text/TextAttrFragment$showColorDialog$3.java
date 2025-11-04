package com.wifiled.ipixels.ui.text;

import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ImageView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BusMutableLiveData;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.view.RoundColorPaletteHSV360;
import com.wifiled.ipixels.view.customview.CustomImageView;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: TextAttrFragment.kt */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/wifiled/ipixels/ui/text/TextAttrFragment$showColorDialog$3", "Lcom/wifiled/ipixels/view/RoundColorPaletteHSV360$ColorChangeCallBack;", "onChange", "", TypedValues.Custom.S_COLOR, "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAttrFragment$showColorDialog$3 implements RoundColorPaletteHSV360.ColorChangeCallBack {
    final /* synthetic */ Ref.BooleanRef $isClearBgColorSel;
    final /* synthetic */ TextAttrFragment this$0;

    TextAttrFragment$showColorDialog$3(TextAttrFragment textAttrFragment, Ref.BooleanRef booleanRef) {
        this.this$0 = textAttrFragment;
        this.$isClearBgColorSel = booleanRef;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [com.wifiled.ipixels.event.EventText] */
    @Override // com.wifiled.ipixels.view.RoundColorPaletteHSV360.ColorChangeCallBack
    public void onChange(int color) {
        boolean z;
        EventText eventText;
        EventText eventText2;
        TextViewModel textViewModel;
        ?? r0;
        EventText eventText3;
        EventText eventText4;
        EventText eventText5;
        TextViewModel textViewModel2;
        EventText eventText6;
        CustomImageView customImageView;
        TextViewModel textViewModel3;
        EventText eventText7;
        LogUtils.file("TextActivity TextAttrFragment   colorPickerView.setColorChangeCallBack  onChange");
        this.this$0.eventText = TextEmojiBuilder.INSTANCE.getEventText();
        Log.v("ruis", "setColorChangeCallBack");
        z = this.this$0.isTextColorMode;
        if (z) {
            eventText3 = this.this$0.eventText;
            if (eventText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText3 = null;
            }
            eventText3.setTextColor(color);
            eventText4 = this.this$0.eventText;
            if (eventText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText4 = null;
            }
            List<TextEmojiVO> textEmojiVO = eventText4.getTextEmojiVO();
            TextAttrFragment textAttrFragment = this.this$0;
            for (TextEmojiVO textEmojiVO2 : textEmojiVO) {
                eventText7 = textAttrFragment.eventText;
                if (eventText7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText7 = null;
                }
                textEmojiVO2.setTextColor(eventText7.getTextColor());
            }
            eventText5 = this.this$0.eventText;
            if (eventText5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText5 = null;
            }
            eventText5.setChangeMode(TextAttrEnum.TextColor);
            textViewModel2 = this.this$0.getTextViewModel();
            BusMutableLiveData<EventText> textChangedLiveData = textViewModel2.getTextChangedLiveData();
            eventText6 = this.this$0.eventText;
            if (eventText6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText6 = null;
            }
            textChangedLiveData.setValue(eventText6);
            customImageView = this.this$0.iv_show_textcolor;
            if (customImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_show_textcolor");
                customImageView = null;
            }
            textViewModel3 = this.this$0.getTextViewModel();
            EventText value = textViewModel3.getTextChangedLiveData().getValue();
            customImageView.setImageDrawable(value != null ? new ColorDrawable(value.getTextColor()) : null);
            return;
        }
        eventText = this.this$0.eventText;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextBgColor(color);
        TextAttrEnum textAttrEnum = TextAttrEnum.TextBgColor;
        eventText2 = this.this$0.eventText;
        if (eventText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText2 = null;
        }
        eventText2.setChangeMode(textAttrEnum);
        textViewModel = this.this$0.getTextViewModel();
        BusMutableLiveData<EventText> textChangedLiveData2 = textViewModel.getTextChangedLiveData();
        r0 = this.this$0.eventText;
        if (r0 == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            r2 = r0;
        }
        textChangedLiveData2.setValue(r2);
        this.$isClearBgColorSel.element = false;
        final TextAttrFragment textAttrFragment2 = this.this$0;
        final Ref.BooleanRef booleanRef = this.$isClearBgColorSel;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$showColorDialog$3$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onChange$lambda$2;
                onChange$lambda$2 = TextAttrFragment$showColorDialog$3.onChange$lambda$2(TextAttrFragment.this, booleanRef);
                return onChange$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onChange$lambda$2(TextAttrFragment textAttrFragment, Ref.BooleanRef booleanRef) {
        ImageView imageView;
        imageView = textAttrFragment.ivClearBgColor;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivClearBgColor");
            imageView = null;
        }
        imageView.setSelected(booleanRef.element);
        return Unit.INSTANCE;
    }
}
