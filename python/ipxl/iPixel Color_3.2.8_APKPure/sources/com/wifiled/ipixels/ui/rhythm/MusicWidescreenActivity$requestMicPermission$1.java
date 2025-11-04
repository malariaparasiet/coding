package com.wifiled.ipixels.ui.rhythm;

import android.content.DialogInterface;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import com.wifiled.musiclib.MusicManager;
import kotlin.Metadata;

/* compiled from: MusicWidescreenActivity.kt */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/wifiled/ipixels/ui/rhythm/MusicWidescreenActivity$requestMicPermission$1", "Lcom/wifiled/baselib/base/BaseActivity$GrantedResult;", "onResult", "", "granted", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicWidescreenActivity$requestMicPermission$1 extends BaseActivity.GrantedResult {
    final /* synthetic */ MusicWidescreenActivity this$0;

    MusicWidescreenActivity$requestMicPermission$1(MusicWidescreenActivity musicWidescreenActivity) {
        this.this$0 = musicWidescreenActivity;
    }

    @Override // com.wifiled.baselib.base.BaseActivity.GrantedResult
    public void onResult(boolean granted) {
        MusicManager musicManager;
        MusicManager musicManager2;
        if (granted) {
            this.this$0.toActivity(MicWidescreenActivity.class);
            musicManager = this.this$0.musicManager;
            if (musicManager.isPlaying()) {
                musicManager2 = this.this$0.musicManager;
                musicManager2.getMusicPlayer().pause();
                return;
            }
            return;
        }
        CustomDialog.Builder builder = new CustomDialog.Builder(this.this$0);
        builder.setTitle(this.this$0.getString(R.string.gps_tip));
        builder.setMessage(this.this$0.getString(R.string.open_audio));
        String string = this.this$0.getString(R.string.sure);
        final MusicWidescreenActivity musicWidescreenActivity = this.this$0;
        builder.setPositiveButton(string, new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$requestMicPermission$1$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MusicWidescreenActivity$requestMicPermission$1.onResult$lambda$0(MusicWidescreenActivity.this, dialogInterface, i);
            }
        });
        builder.setNegativeButton(this.this$0.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$requestMicPermission$1$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResult$lambda$0(MusicWidescreenActivity musicWidescreenActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        App.INSTANCE.gotoAppDetailIntent(musicWidescreenActivity);
    }
}
