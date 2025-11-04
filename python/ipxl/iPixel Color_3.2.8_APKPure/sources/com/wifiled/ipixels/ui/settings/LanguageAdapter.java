package com.wifiled.ipixels.ui.settings;

import android.content.Context;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.bean.LanguageData;
import java.util.List;

/* loaded from: classes3.dex */
public class LanguageAdapter extends BaseQuickAdapter<LanguageData, BaseViewHolder> {
    private Context mContext;
    private int selected;

    public LanguageAdapter(int layoutResId, List<LanguageData> data) {
        super(layoutResId, data);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, LanguageData languageData) {
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_state);
        baseViewHolder.setText(R.id.tv_language_title, languageData.getLanguage());
        if (languageData.isSelect()) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }
}
