package com.wifiled.ipixels.ota;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.tiro.jlotalibrary.view.TextButton;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: OTADialogActivity.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/wifiled/ipixels/ota/OTADialogActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "mBleMac", "", "tip", "Landroid/widget/TextView;", "tip1", "warn", "cancel_button", "Lcom/tiro/jlotalibrary/view/TextButton;", "sure_button", "old_version", "new_version", "release_notes", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "initView", "onDestroy", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OTADialogActivity extends AppCompatActivity {
    private TextButton cancel_button;
    private String mBleMac = "";
    private TextView new_version;
    private TextView old_version;
    private TextView release_notes;
    private TextButton sure_button;
    private TextView tip;
    private TextView tip1;
    private TextView warn;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otadialog);
        getWindow().getDecorView().setVisibility(8);
        initView();
    }

    private final void initView() {
        String stringExtra;
        View findViewById = findViewById(R.id.tip);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.tip = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.tip1);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tip1 = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.warn);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.warn = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.cancel_button);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.cancel_button = (TextButton) findViewById4;
        View findViewById5 = findViewById(R.id.sure_button);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.sure_button = (TextButton) findViewById5;
        View findViewById6 = findViewById(R.id.old_version);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.old_version = (TextView) findViewById6;
        View findViewById7 = findViewById(R.id.new_version);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.new_version = (TextView) findViewById7;
        View findViewById8 = findViewById(R.id.release_notes);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.release_notes = (TextView) findViewById8;
        TextView textView = this.tip;
        TextButton textButton = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip");
            textView = null;
        }
        textView.setVisibility(4);
        TextView textView2 = this.tip1;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tip1");
            textView2 = null;
        }
        textView2.setVisibility(4);
        TextView textView3 = this.warn;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("warn");
            textView3 = null;
        }
        textView3.setVisibility(0);
        TextButton textButton2 = this.cancel_button;
        if (textButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancel_button");
            textButton2 = null;
        }
        textButton2.setVisibility(0);
        TextButton textButton3 = this.sure_button;
        if (textButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sure_button");
            textButton3 = null;
        }
        textButton3.setVisibility(0);
        TextView textView4 = this.old_version;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("old_version");
            textView4 = null;
        }
        String string = getString(R.string.ota_current_firmware_version);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String format = String.format(string, Arrays.copyOf(new Object[]{getIntent().getStringExtra("version_old")}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        textView4.setText(format);
        TextView textView5 = this.new_version;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("new_version");
            textView5 = null;
        }
        String string2 = getString(R.string.ota_new_firmware_version);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String format2 = String.format(string2, Arrays.copyOf(new Object[]{getIntent().getStringExtra("version_no")}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        textView5.setText(format2);
        this.mBleMac = String.valueOf(getIntent().getStringExtra("macAddress"));
        TextView textView6 = this.release_notes;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("release_notes");
            textView6 = null;
        }
        String string3 = getString(R.string.ota_release_notes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = getString(R.string.ota_release_notes);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        if (StringsKt.contains$default((CharSequence) string4, (CharSequence) "更新说明", false, 2, (Object) null)) {
            stringExtra = getIntent().getStringExtra("desc_cn");
        } else {
            stringExtra = getIntent().getStringExtra("desc_en");
        }
        String format3 = String.format(string3, Arrays.copyOf(new Object[]{stringExtra}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
        textView6.setText(format3);
        TextButton textButton4 = this.cancel_button;
        if (textButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancel_button");
            textButton4 = null;
        }
        textButton4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ota.OTADialogActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OTADialogActivity.initView$lambda$0(OTADialogActivity.this, view);
            }
        });
        TextButton textButton5 = this.sure_button;
        if (textButton5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sure_button");
        } else {
            textButton = textButton5;
        }
        textButton.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ota.OTADialogActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OTADialogActivity.initView$lambda$2(OTADialogActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$0(OTADialogActivity oTADialogActivity, View view) {
        AppConfig.INSTANCE.getConnectList().add(oTADialogActivity.mBleMac);
        oTADialogActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2(OTADialogActivity oTADialogActivity, View view) {
        BleSOTAData bleSOTAData;
        byte[] data = OTARequest.INSTANCE.getData();
        if (data != null && (bleSOTAData = OTARequest.INSTANCE.getBleSOTAData()) != null) {
            bleSOTAData.sendData(data);
        }
        oTADialogActivity.finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
