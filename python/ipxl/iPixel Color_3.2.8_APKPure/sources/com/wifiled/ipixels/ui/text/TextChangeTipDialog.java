package com.wifiled.ipixels.ui.text;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.ipixels.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextChangeTipDialog.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J&\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextChangeTipDialog;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "mOnClickListener", "Lcom/wifiled/ipixels/ui/text/TextChangeTipDialog$TextChangeTipDialogClickLinstener;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "setClickLinstenr", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "TextChangeTipDialogClickLinstener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextChangeTipDialog extends DialogFragment {
    private TextChangeTipDialogClickLinstener mOnClickListener;

    /* compiled from: TextChangeTipDialog.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextChangeTipDialog$TextChangeTipDialogClickLinstener;", "", "onSubmitClick", "", "onCancelClick", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface TextChangeTipDialogClickLinstener {
        void onCancelClick();

        void onSubmitClick();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.text_dialog_style);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dialog_text_change_tip, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.cancel);
        TextView textView2 = (TextView) inflate.findViewById(R.id.submit);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextChangeTipDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextChangeTipDialog.onCreateView$lambda$0(TextChangeTipDialog.this, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextChangeTipDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextChangeTipDialog.onCreateView$lambda$1(TextChangeTipDialog.this, view);
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$0(TextChangeTipDialog textChangeTipDialog, View view) {
        textChangeTipDialog.dismiss();
        TextChangeTipDialogClickLinstener textChangeTipDialogClickLinstener = textChangeTipDialog.mOnClickListener;
        if (textChangeTipDialogClickLinstener != null) {
            textChangeTipDialogClickLinstener.onCancelClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$1(TextChangeTipDialog textChangeTipDialog, View view) {
        TextChangeTipDialogClickLinstener textChangeTipDialogClickLinstener = textChangeTipDialog.mOnClickListener;
        if (textChangeTipDialogClickLinstener != null) {
            textChangeTipDialogClickLinstener.onSubmitClick();
        }
    }

    public final void setClickLinstenr(TextChangeTipDialogClickLinstener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mOnClickListener = listener;
    }
}
