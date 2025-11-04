package com.wifiled.ipixels.ui.diy;

import android.content.DialogInterface;
import android.content.Intent;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import kotlin.Metadata;

/* compiled from: DiyImageActivity.kt */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/wifiled/ipixels/ui/diy/DiyImageActivity$chooseImage$2$2", "Lcom/wifiled/baselib/base/BaseActivity$GrantedResult;", "onResult", "", "granted", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DiyImageActivity$chooseImage$2$2 extends BaseActivity.GrantedResult {
    final /* synthetic */ DiyImageActivity this$0;

    DiyImageActivity$chooseImage$2$2(DiyImageActivity diyImageActivity) {
        this.this$0 = diyImageActivity;
    }

    @Override // com.wifiled.baselib.base.BaseActivity.GrantedResult
    public void onResult(boolean granted) {
        int i;
        if (!granted) {
            CustomDialog.Builder builder = new CustomDialog.Builder(this.this$0);
            builder.setTitle(this.this$0.getString(R.string.gps_tip));
            builder.setMessage(this.this$0.getString(R.string.open_camera));
            String string = this.this$0.getString(R.string.sure);
            final DiyImageActivity diyImageActivity = this.this$0;
            builder.setPositiveButton(string, new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$chooseImage$2$2$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    DiyImageActivity$chooseImage$2$2.onResult$lambda$0(DiyImageActivity.this, dialogInterface, i2);
                }
            });
            builder.setNegativeButton(this.this$0.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$chooseImage$2$2$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
            return;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        DiyImageActivity diyImageActivity2 = this.this$0;
        i = diyImageActivity2.REQUEST_TAKE_PHOTO_CODE;
        diyImageActivity2.startActivityForResult(intent, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResult$lambda$0(DiyImageActivity diyImageActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        App.INSTANCE.gotoAppDetailIntent(diyImageActivity);
    }
}
