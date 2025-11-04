package com.wifiled.ipixels.ui.diy;

import android.app.Activity;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.wifiled.baselib.app.Navigation;
import com.wifiled.baselib.base.BaseNavActivity;
import com.wifiled.ipixels.R;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DiyActivity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014¨\u0006\r"}, d2 = {"Lcom/wifiled/ipixels/ui/diy/DiyActivity;", "Lcom/wifiled/baselib/base/BaseNavActivity;", "<init>", "()V", "layoutId", "", "containerViewId", PlayerFinal.PLAYER_MODE, "Lcom/wifiled/baselib/app/Navigation$MODE;", "bindData", "", "onDestroy", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DiyActivity extends BaseNavActivity {
    public static final int MY_PERMISSIONS_REQUEST = 1001;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] PERMISSIONS = {"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE"};
    private static final String[] PERMISSIONS2 = {"android.permission.CAMERA"};
    private static final ArrayList<String> mPermissionList = new ArrayList<>();

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
    }

    @Override // com.wifiled.baselib.base.BaseNavActivity
    protected int containerViewId() {
        return R.id.navigation_container;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_diy;
    }

    @Override // com.wifiled.baselib.base.BaseNavActivity
    protected Navigation.MODE mode() {
        return Navigation.MODE.ADD;
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    /* compiled from: DiyActivity.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\b0\fj\b\u0012\u0004\u0012\u00020\b`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/wifiled/ipixels/ui/diy/DiyActivity$Companion;", "", "<init>", "()V", "MY_PERMISSIONS_REQUEST", "", "PERMISSIONS", "", "", "[Ljava/lang/String;", "PERMISSIONS2", "mPermissionList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "checkPermissions", "", "ins", "Landroid/app/Activity;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void checkPermissions(Activity ins) {
            Intrinsics.checkNotNullParameter(ins, "ins");
            if (Build.VERSION.SDK_INT >= 33) {
                int length = DiyActivity.PERMISSIONS2.length;
                for (int i = 0; i < length; i++) {
                    if (ContextCompat.checkSelfPermission(ins, DiyActivity.PERMISSIONS2[i]) != 0) {
                        DiyActivity.mPermissionList.add(DiyActivity.PERMISSIONS2[i]);
                    }
                }
                if (DiyActivity.mPermissionList.isEmpty()) {
                    return;
                }
                ActivityCompat.requestPermissions(ins, (String[]) DiyActivity.mPermissionList.toArray(new String[0]), 1001);
                return;
            }
            int length2 = DiyActivity.PERMISSIONS.length;
            for (int i2 = 0; i2 < length2; i2++) {
                if (ContextCompat.checkSelfPermission(ins, DiyActivity.PERMISSIONS[i2]) != 0) {
                    DiyActivity.mPermissionList.add(DiyActivity.PERMISSIONS[i2]);
                }
            }
            if (DiyActivity.mPermissionList.isEmpty()) {
                return;
            }
            ActivityCompat.requestPermissions(ins, (String[]) DiyActivity.mPermissionList.toArray(new String[0]), 1001);
        }
    }
}
