package com.wifiled.ipixels.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.wifiled.ipixels.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelecrDeviceDialog.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001dB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0018H\u0002J\u0010\u0010\u001c\u001a\u00020\u00182\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/wifiled/ipixels/ui/dialog/SelecrDeviceDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "deviceName1", "", "deviceName2", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "mName1", "mName2", "clickListener", "Lcom/wifiled/ipixels/ui/dialog/SelecrDeviceDialog$ClickListener;", "mSelectDevice", "", "device_1", "Landroid/widget/RadioButton;", "device_2", "device_group", "Landroid/widget/RadioGroup;", "cancel_btn", "Landroid/widget/ImageView;", "confirm_btn", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "bindData", "setClickListener", "ClickListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SelecrDeviceDialog extends Dialog {
    private ImageView cancel_btn;
    private ClickListener clickListener;
    private ImageView confirm_btn;
    private RadioButton device_1;
    private RadioButton device_2;
    private RadioGroup device_group;
    private String mName1;
    private String mName2;
    private int mSelectDevice;

    /* compiled from: SelecrDeviceDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/dialog/SelecrDeviceDialog$ClickListener;", "", "onCancel", "", "onOk", "num", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ClickListener {
        void onCancel();

        void onOk(int num);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelecrDeviceDialog(Context context, String deviceName1, String deviceName2) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deviceName1, "deviceName1");
        Intrinsics.checkNotNullParameter(deviceName2, "deviceName2");
        this.mName1 = deviceName1;
        this.mName2 = deviceName2;
        this.mSelectDevice = 1;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(View.inflate(getContext(), R.layout.dialog_select_deivce, null));
        Window window = getWindow();
        if (window == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        window.setLayout(-1, -2);
        WindowManager.LayoutParams attributes = window.getAttributes();
        Intrinsics.checkNotNullExpressionValue(attributes, "getAttributes(...)");
        attributes.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 0.8d);
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(0));
        View findViewById = findViewById(R.id.device_1);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.device_1 = (RadioButton) findViewById;
        View findViewById2 = findViewById(R.id.device_2);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.device_2 = (RadioButton) findViewById2;
        View findViewById3 = findViewById(R.id.device_group);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.device_group = (RadioGroup) findViewById3;
        View findViewById4 = findViewById(R.id.cancel_btn);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.cancel_btn = (ImageView) findViewById4;
        View findViewById5 = findViewById(R.id.confirm_btn);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.confirm_btn = (ImageView) findViewById5;
        bindData();
    }

    private final void bindData() {
        RadioButton radioButton = this.device_1;
        ImageView imageView = null;
        if (radioButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("device_1");
            radioButton = null;
        }
        radioButton.setText(this.mName1);
        RadioButton radioButton2 = this.device_2;
        if (radioButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("device_2");
            radioButton2 = null;
        }
        radioButton2.setText(this.mName2);
        RadioGroup radioGroup = this.device_group;
        if (radioGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("device_group");
            radioGroup = null;
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog$$ExternalSyntheticLambda0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                SelecrDeviceDialog.bindData$lambda$0(SelecrDeviceDialog.this, radioGroup2, i);
            }
        });
        ImageView imageView2 = this.cancel_btn;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancel_btn");
            imageView2 = null;
        }
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelecrDeviceDialog.bindData$lambda$1(SelecrDeviceDialog.this, view);
            }
        });
        ImageView imageView3 = this.confirm_btn;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("confirm_btn");
        } else {
            imageView = imageView3;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelecrDeviceDialog.bindData$lambda$2(SelecrDeviceDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$0(SelecrDeviceDialog selecrDeviceDialog, RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.device_1 /* 2131296531 */:
                selecrDeviceDialog.mSelectDevice = 1;
                break;
            case R.id.device_2 /* 2131296532 */:
                selecrDeviceDialog.mSelectDevice = 2;
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$1(SelecrDeviceDialog selecrDeviceDialog, View view) {
        ClickListener clickListener = selecrDeviceDialog.clickListener;
        Intrinsics.checkNotNull(clickListener);
        clickListener.onCancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$2(SelecrDeviceDialog selecrDeviceDialog, View view) {
        ClickListener clickListener = selecrDeviceDialog.clickListener;
        Intrinsics.checkNotNull(clickListener);
        clickListener.onOk(selecrDeviceDialog.mSelectDevice);
    }

    public final void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
