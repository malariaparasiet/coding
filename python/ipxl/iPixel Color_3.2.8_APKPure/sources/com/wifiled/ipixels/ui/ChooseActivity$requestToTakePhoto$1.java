package com.wifiled.ipixels.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.wifiled.baselib.base.XBaseActivity;
import com.wifiled.baselib.callback.ActivityResultCallback;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import com.wifiled.ipixels.ui.projection.UriUtils;
import com.wifiled.ipixels.ui.projection.VideoActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChooseActivity.kt */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/wifiled/ipixels/ui/ChooseActivity$requestToTakePhoto$1", "Lcom/wifiled/baselib/base/XBaseActivity$GrantedResult;", "onResult", "", "granted", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseActivity$requestToTakePhoto$1 extends XBaseActivity.GrantedResult {
    final /* synthetic */ ChooseActivity this$0;

    ChooseActivity$requestToTakePhoto$1(ChooseActivity chooseActivity) {
        this.this$0 = chooseActivity;
    }

    @Override // com.wifiled.baselib.base.XBaseActivity.GrantedResult
    public void onResult(boolean granted) {
        if (!granted) {
            CustomDialog.Builder builder = new CustomDialog.Builder(this.this$0);
            builder.setTitle(this.this$0.getString(R.string.gps_tip));
            builder.setMessage(this.this$0.getString(R.string.open_camera));
            String string = this.this$0.getString(R.string.sure);
            final ChooseActivity chooseActivity = this.this$0;
            builder.setPositiveButton(string, new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$requestToTakePhoto$1$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ChooseActivity$requestToTakePhoto$1.onResult$lambda$0(ChooseActivity.this, dialogInterface, i);
                }
            });
            builder.setNegativeButton(this.this$0.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$requestToTakePhoto$1$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
            return;
        }
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        if (intent.resolveActivity(this.this$0.getPackageManager()) != null) {
            final ChooseActivity chooseActivity2 = this.this$0;
            chooseActivity2.toActivityForResult(intent, new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.ChooseActivity$requestToTakePhoto$1$$ExternalSyntheticLambda2
                @Override // com.wifiled.baselib.callback.ActivityResultCallback
                public final void onActivityResult(int i, Intent intent2) {
                    ChooseActivity$requestToTakePhoto$1.onResult$lambda$2(ChooseActivity.this, i, intent2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResult$lambda$0(ChooseActivity chooseActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        App.INSTANCE.gotoAppDetailIntent(chooseActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResult$lambda$2(ChooseActivity chooseActivity, int i, Intent intent) {
        if (i == -1) {
            ChooseActivity chooseActivity2 = chooseActivity;
            Intent intent2 = new Intent(chooseActivity2, (Class<?>) VideoActivity.class);
            UriUtils uriUtils = UriUtils.INSTANCE;
            Uri data = intent.getData();
            Intrinsics.checkNotNull(data);
            intent2.putExtra("select_cur_record_video", uriUtils.getPath(chooseActivity2, data));
            chooseActivity.startActivity(intent2);
        }
    }
}
