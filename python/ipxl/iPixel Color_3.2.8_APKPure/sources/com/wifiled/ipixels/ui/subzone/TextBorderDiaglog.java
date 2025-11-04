package com.wifiled.ipixels.ui.subzone;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextBorderDiaglog.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\u000e\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/TextBorderDiaglog;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "mOnClickLinstener", "Lcom/wifiled/ipixels/ui/subzone/TextBorderDiaglog$TextBorderClickLinstener;", "mPosition", "", "mEffectPosition", "mSpeed", "mBorderData", "", "Lcom/wifiled/ipixels/ui/subzone/TextBorderData;", "mBorderEffectData", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "initData", "setOnClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "TextBorderClickLinstener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextBorderDiaglog extends DialogFragment {
    private List<TextBorderData> mBorderData = new ArrayList();
    private List<TextBorderData> mBorderEffectData = new ArrayList();
    private int mEffectPosition;
    private TextBorderClickLinstener mOnClickLinstener;
    private int mPosition;
    private int mSpeed;

    /* compiled from: TextBorderDiaglog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/TextBorderDiaglog$TextBorderClickLinstener;", "", "onSubmitClick", "", PlayerFinal.PLAYER_POSITION, "", "effectPosition", "speed", "onCancelClick", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface TextBorderClickLinstener {
        void onCancelClick();

        void onSubmitClick(int position, int effectPosition, int speed);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.ShowTitleDontShowActionBar);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dialog_text_border, (ViewGroup) null);
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
        initData();
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_cancel);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_submit);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.border_list);
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(R.id.border_effect_list);
        SeekBar seekBar = (SeekBar) inflate.findViewById(R.id.sb_speed);
        final TextBorderAdapter2 textBorderAdapter2 = new TextBorderAdapter2(R.layout.item_text_border);
        final TextBorderAdapter textBorderAdapter = new TextBorderAdapter(R.layout.item_text_border_effect);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(textBorderAdapter2);
        recyclerView2.setAdapter(textBorderAdapter);
        textBorderAdapter2.setList(this.mBorderData);
        textBorderAdapter.setList(this.mBorderEffectData);
        textBorderAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.subzone.TextBorderDiaglog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TextBorderDiaglog.onCreateView$lambda$0(TextBorderDiaglog.this, textBorderAdapter2, baseQuickAdapter, view, i);
            }
        });
        textBorderAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.subzone.TextBorderDiaglog$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TextBorderDiaglog.onCreateView$lambda$1(TextBorderDiaglog.this, textBorderAdapter, baseQuickAdapter, view, i);
            }
        });
        Intrinsics.checkNotNull(seekBar);
        UtilsExtensionKt.setOnSeekBarStopChangeListener(seekBar, new Function1() { // from class: com.wifiled.ipixels.ui.subzone.TextBorderDiaglog$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCreateView$lambda$2;
                onCreateView$lambda$2 = TextBorderDiaglog.onCreateView$lambda$2(TextBorderDiaglog.this, (SeekBar) obj);
                return onCreateView$lambda$2;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.TextBorderDiaglog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextBorderDiaglog.this.dismiss();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.TextBorderDiaglog$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextBorderDiaglog.onCreateView$lambda$4(TextBorderDiaglog.this, view);
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$0(TextBorderDiaglog textBorderDiaglog, TextBorderAdapter2 textBorderAdapter2, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        textBorderDiaglog.mBorderData.get(textBorderDiaglog.mPosition).setSelect(false);
        textBorderAdapter2.notifyItemChanged(textBorderDiaglog.mPosition);
        textBorderDiaglog.mBorderData.get(i).setSelect(true);
        textBorderDiaglog.mPosition = i;
        textBorderAdapter2.notifyItemChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$1(TextBorderDiaglog textBorderDiaglog, TextBorderAdapter textBorderAdapter, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        textBorderDiaglog.mBorderEffectData.get(textBorderDiaglog.mEffectPosition).setSelect(false);
        textBorderAdapter.notifyItemChanged(textBorderDiaglog.mEffectPosition);
        textBorderDiaglog.mBorderEffectData.get(i).setSelect(true);
        textBorderDiaglog.mEffectPosition = i;
        textBorderAdapter.notifyItemChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreateView$lambda$2(final TextBorderDiaglog textBorderDiaglog, SeekBar it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.subzone.TextBorderDiaglog$onCreateView$3$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                TextBorderDiaglog.this.mSpeed = seekBar.getProgress();
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$4(TextBorderDiaglog textBorderDiaglog, View view) {
        TextBorderClickLinstener textBorderClickLinstener = textBorderDiaglog.mOnClickLinstener;
        if (textBorderClickLinstener != null) {
            textBorderClickLinstener.onSubmitClick(textBorderDiaglog.mPosition, textBorderDiaglog.mEffectPosition, textBorderDiaglog.mSpeed);
        }
    }

    private final void initData() {
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_0, true));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_1, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_2, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_3, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_4, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_5, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_6, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_7, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_8, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_9, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_10, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_11, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_12, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_13, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_14, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_15, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_16, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_17, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_18, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_19, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_20, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_21, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_22, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_23, false));
        this.mBorderData.add(new TextBorderData(R.drawable.icon_text_border_24, false));
        this.mBorderEffectData.add(new TextBorderData(R.drawable.text_border_effect_4, true));
        this.mBorderEffectData.add(new TextBorderData(R.drawable.text_border_effect_1, false));
        this.mBorderEffectData.add(new TextBorderData(R.drawable.text_border_effect_3, false));
        this.mBorderEffectData.add(new TextBorderData(R.drawable.text_border_effect_2, false));
    }

    public final void setOnClickListener(TextBorderClickLinstener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mOnClickLinstener = listener;
    }
}
