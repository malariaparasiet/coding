package com.wifiled.ipixels.ui.text.vo;

import com.wifiled.ipixels.event.EventText;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextHistoryVO.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J#\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/wifiled/ipixels/ui/text/vo/TextHistoryVO;", "Ljava/io/Serializable;", "textEmojiVOs", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "eventText", "Lcom/wifiled/ipixels/event/EventText;", "<init>", "(Ljava/util/List;Lcom/wifiled/ipixels/event/EventText;)V", "getTextEmojiVOs", "()Ljava/util/List;", "getEventText", "()Lcom/wifiled/ipixels/event/EventText;", "setEventText", "(Lcom/wifiled/ipixels/event/EventText;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TextHistoryVO implements Serializable {
    private EventText eventText;
    private final List<TextEmojiVO> textEmojiVOs;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TextHistoryVO copy$default(TextHistoryVO textHistoryVO, List list, EventText eventText, int i, Object obj) {
        if ((i & 1) != 0) {
            list = textHistoryVO.textEmojiVOs;
        }
        if ((i & 2) != 0) {
            eventText = textHistoryVO.eventText;
        }
        return textHistoryVO.copy(list, eventText);
    }

    public final List<TextEmojiVO> component1() {
        return this.textEmojiVOs;
    }

    /* renamed from: component2, reason: from getter */
    public final EventText getEventText() {
        return this.eventText;
    }

    public final TextHistoryVO copy(List<TextEmojiVO> textEmojiVOs, EventText eventText) {
        Intrinsics.checkNotNullParameter(textEmojiVOs, "textEmojiVOs");
        Intrinsics.checkNotNullParameter(eventText, "eventText");
        return new TextHistoryVO(textEmojiVOs, eventText);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextHistoryVO)) {
            return false;
        }
        TextHistoryVO textHistoryVO = (TextHistoryVO) other;
        return Intrinsics.areEqual(this.textEmojiVOs, textHistoryVO.textEmojiVOs) && Intrinsics.areEqual(this.eventText, textHistoryVO.eventText);
    }

    public int hashCode() {
        return (this.textEmojiVOs.hashCode() * 31) + this.eventText.hashCode();
    }

    public String toString() {
        return "TextHistoryVO(textEmojiVOs=" + this.textEmojiVOs + ", eventText=" + this.eventText + ")";
    }

    public TextHistoryVO(List<TextEmojiVO> textEmojiVOs, EventText eventText) {
        Intrinsics.checkNotNullParameter(textEmojiVOs, "textEmojiVOs");
        Intrinsics.checkNotNullParameter(eventText, "eventText");
        this.textEmojiVOs = textEmojiVOs;
        this.eventText = eventText;
    }

    public final EventText getEventText() {
        return this.eventText;
    }

    public final List<TextEmojiVO> getTextEmojiVOs() {
        return this.textEmojiVOs;
    }

    public final void setEventText(EventText eventText) {
        Intrinsics.checkNotNullParameter(eventText, "<set-?>");
        this.eventText = eventText;
    }
}
