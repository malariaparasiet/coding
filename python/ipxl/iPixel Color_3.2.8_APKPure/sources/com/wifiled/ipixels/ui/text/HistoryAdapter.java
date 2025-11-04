package com.wifiled.ipixels.ui.text;

import android.content.Context;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.text.vo.TextHistoryVO;
import com.wifiled.ipixels.view.TextEmojiView;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextRecordFragment.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0002H\u0016R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Lcom/wifiled/ipixels/ui/text/HistoryAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/ui/text/vo/TextHistoryVO;", "context", "Landroid/content/Context;", "data", "", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "layoutId", "", "m_iSelect", "getM_iSelect", "()I", "setM_iSelect", "(I)V", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "textHistoryVO", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HistoryAdapter extends RecyclerAdapter<TextHistoryVO> {
    private int m_iSelect;

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public int layoutId() {
        return R.layout.item_text_history;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HistoryAdapter(Context context, List<TextHistoryVO> data) {
        super(context, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.m_iSelect = -1;
    }

    public final int getM_iSelect() {
        return this.m_iSelect;
    }

    public final void setM_iSelect(int i) {
        this.m_iSelect = i;
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public void convert(RecyclerViewHolder holder, TextHistoryVO textHistoryVO) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(textHistoryVO, "textHistoryVO");
        TextEmojiView textEmojiView = (TextEmojiView) holder.getView(R.id.textrec_EmojiView);
        if (!textEmojiView.getData().isEmpty()) {
            textEmojiView.getData().clear();
        }
        textEmojiView.addTextEmojis(textHistoryVO.getTextEmojiVOs());
        boolean z = this.m_iSelect == getPosition(holder);
        if (z) {
            holder.getConvertView().setSelected(true);
        } else {
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            holder.getConvertView().setSelected(false);
        }
    }
}
