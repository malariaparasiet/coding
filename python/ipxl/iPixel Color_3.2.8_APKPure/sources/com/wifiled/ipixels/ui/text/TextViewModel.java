package com.wifiled.ipixels.ui.text;

import androidx.lifecycle.ViewModel;
import com.wifiled.ipixels.core.BusMutableLiveData;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.ui.text.vo.TextHistoryVO;
import kotlin.Metadata;

/* compiled from: TextViewModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "textChangedLiveData", "Lcom/wifiled/ipixels/core/BusMutableLiveData;", "Lcom/wifiled/ipixels/event/EventText;", "getTextChangedLiveData", "()Lcom/wifiled/ipixels/core/BusMutableLiveData;", "textRecordAddLiveData", "Lcom/wifiled/ipixels/ui/text/vo/TextHistoryVO;", "getTextRecordAddLiveData", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextViewModel extends ViewModel {
    private final BusMutableLiveData<EventText> textChangedLiveData = new BusMutableLiveData<>();
    private final BusMutableLiveData<TextHistoryVO> textRecordAddLiveData = new BusMutableLiveData<>();

    public final BusMutableLiveData<EventText> getTextChangedLiveData() {
        return this.textChangedLiveData;
    }

    public final BusMutableLiveData<TextHistoryVO> getTextRecordAddLiveData() {
        return this.textRecordAddLiveData;
    }
}
