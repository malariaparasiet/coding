package com.wifiled.baselib.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.wifiled.baselib.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class PermissionFragment extends DialogFragment {
    private static final String PERMISSION_KEY = "permission_key";
    private static final String REQUEST_CODE = "request_code";
    private static final String REQUEST_RATIONALE = "request_rationale";
    private static IPermission permissionListener;
    private FragmentActivity activity;
    private String[] permissions;
    private String rationale;
    private int requestCode = 1;

    public static PermissionFragment newInstance() {
        return new PermissionFragment();
    }

    public void requestPermission(FragmentActivity fragmentActivity, String[] strArr, String str, IPermission iPermission) {
        permissionListener = iPermission;
        show(fragmentActivity.getSupportFragmentManager(), "");
        this.activity = fragmentActivity;
        this.permissions = strArr;
        this.rationale = str;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setDimAmount(0.0f);
        }
        requestPermissionInner(this.permissions);
    }

    private void requestPermissionInner(String[] strArr) {
        if (PermissionUtils.hasSelfPermissions(this.activity, strArr)) {
            IPermission iPermission = permissionListener;
            if (iPermission != null) {
                iPermission.permissionGranted();
                permissionListener = null;
            }
            dismissAllowingStateLoss();
            return;
        }
        boolean shouldShowRequestPermissionRationale = PermissionUtils.shouldShowRequestPermissionRationale(this.activity, strArr);
        if (TextUtils.isEmpty(this.rationale)) {
            requestPermissions(strArr, this.requestCode);
        } else if (shouldShowRequestPermissionRationale) {
            showRequestPermissionRationale(this.activity, strArr, this.rationale);
        } else {
            requestPermissions(strArr, this.requestCode);
        }
    }

    public void showRequestPermissionRationale(Activity activity, final String[] strArr, String str) {
        new AlertDialog.Builder(activity).setMessage(str).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.permission.PermissionFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PermissionFragment.this.lambda$showRequestPermissionRationale$0(strArr, dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.permission.PermissionFragment$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRequestPermissionRationale$0(String[] strArr, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        requestPermissions(strArr, this.requestCode);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (PermissionUtils.verifyPermissions(iArr)) {
            IPermission iPermission = permissionListener;
            if (iPermission != null) {
                iPermission.permissionGranted();
            }
        } else {
            int i2 = 0;
            if (!PermissionUtils.shouldShowRequestPermissionRationale(this.activity, strArr)) {
                if (strArr.length != iArr.length) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                while (i2 < iArr.length) {
                    if (iArr[i2] == -1) {
                        arrayList.add(strArr[i2]);
                    }
                    i2++;
                }
                IPermission iPermission2 = permissionListener;
                if (iPermission2 != null) {
                    iPermission2.permissionNoAskDenied(i, arrayList);
                }
            } else if (permissionListener != null) {
                ArrayList arrayList2 = new ArrayList();
                while (i2 < iArr.length) {
                    if (iArr[i2] == -1) {
                        arrayList2.add(strArr[i2]);
                    }
                    i2++;
                }
                permissionListener.permissionDenied(i, arrayList2);
            }
        }
        permissionListener = null;
        dismissAllowingStateLoss();
    }
}
