package com.wifiled.ipixels.ui.text;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.subzone.TextBgOrBorderAdapter;
import com.wifiled.ipixels.ui.subzone.TextBorderData;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CreativeTextBgBorderDialog.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\nJ\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0003J&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/wifiled/ipixels/ui/text/CreativeTextBgBorderDialog;", "Landroidx/fragment/app/DialogFragment;", "title", "", "data", "", "Lcom/wifiled/ipixels/ui/subzone/TextBorderData;", "<init>", "(ILjava/util/List;)V", "mOnClickLinstener", "Lcom/wifiled/ipixels/ui/text/CreativeTextBgBorderDialog$CreativeTextLinstener;", "mPosition", "mData", "mTitle", "mTitleTv", "Landroid/widget/TextView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setOnClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setPosition", PlayerFinal.PLAYER_POSITION, "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "CreativeTextLinstener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreativeTextBgBorderDialog extends DialogFragment {
    private List<TextBorderData> mData;
    private CreativeTextLinstener mOnClickLinstener;
    private int mPosition;
    private int mTitle;
    private TextView mTitleTv;

    /* compiled from: CreativeTextBgBorderDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/text/CreativeTextBgBorderDialog$CreativeTextLinstener;", "", "onSubmitClick", "", PlayerFinal.PLAYER_POSITION, "", "res", "onCancelClick", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface CreativeTextLinstener {
        void onCancelClick();

        void onSubmitClick(int position, int res);
    }

    public CreativeTextBgBorderDialog(int i, List<TextBorderData> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.mData = data;
        this.mTitle = i;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.ShowTitleDontShowActionBar);
    }

    public final void setOnClickListener(CreativeTextLinstener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mOnClickLinstener = listener;
    }

    public final void setPosition(int position) {
        this.mPosition = position;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        TextView textView = null;
        View inflate = inflater.inflate(R.layout.dialog_creative_text_bg_border, (ViewGroup) null);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Dialog dialog = getDialog();
        Intrinsics.checkNotNull(dialog);
        Window window = dialog.getWindow();
        Intrinsics.checkNotNull(window);
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -2;
        Dialog dialog2 = getDialog();
        Intrinsics.checkNotNull(dialog2);
        Window window2 = dialog2.getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setAttributes(layoutParams);
        Dialog dialog3 = getDialog();
        Intrinsics.checkNotNull(dialog3);
        Window window3 = dialog3.getWindow();
        Intrinsics.checkNotNull(window3);
        window3.setGravity(80);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_cancel);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_confirm);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.border_list);
        View findViewById = inflate.findViewById(R.id.dialog_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        TextView textView2 = (TextView) findViewById;
        this.mTitleTv = textView2;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTitleTv");
        } else {
            textView = textView2;
        }
        textView.setText(this.mTitle);
        TextBgOrBorderAdapter textBgOrBorderAdapter = new TextBgOrBorderAdapter(R.layout.item_creative_bg_border);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(textBgOrBorderAdapter);
        textBgOrBorderAdapter.setList(CollectionsKt.toMutableList((Collection) this.mData));
        textBgOrBorderAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextBgBorderDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CreativeTextBgBorderDialog.onCreateView$lambda$0(CreativeTextBgBorderDialog.this, baseQuickAdapter, view, i);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextBgBorderDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreativeTextBgBorderDialog.this.dismiss();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextBgBorderDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreativeTextBgBorderDialog.onCreateView$lambda$2(CreativeTextBgBorderDialog.this, view);
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$0(CreativeTextBgBorderDialog creativeTextBgBorderDialog, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        creativeTextBgBorderDialog.mData.get(creativeTextBgBorderDialog.mPosition).setSelect(false);
        adapter.notifyItemChanged(creativeTextBgBorderDialog.mPosition);
        creativeTextBgBorderDialog.mData.get(i).setSelect(true);
        creativeTextBgBorderDialog.mPosition = i;
        adapter.notifyItemChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$2(CreativeTextBgBorderDialog creativeTextBgBorderDialog, View view) {
        CreativeTextLinstener creativeTextLinstener = creativeTextBgBorderDialog.mOnClickLinstener;
        if (creativeTextLinstener != null) {
            int i = creativeTextBgBorderDialog.mPosition;
            creativeTextLinstener.onSubmitClick(i, creativeTextBgBorderDialog.mData.get(i).getBorderRes());
        }
        creativeTextBgBorderDialog.dismiss();
    }
}
