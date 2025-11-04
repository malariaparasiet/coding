package com.wifiled.baselib.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.wifiled.baselib.R;
import com.wifiled.baselib.utils.DialogUtils;

/* loaded from: classes2.dex */
public class DialogUtils {

    public interface DialogCall {
        void onNegative();

        void onPositive(DialogInterface dialogInterface);
    }

    public static AlertDialog showProgressDialog(Activity activity, String str) {
        AlertDialog create = new AlertDialog.Builder(activity).create();
        create.setCanceledOnTouchOutside(false);
        create.show();
        create.setContentView(R.layout.progress_dialog);
        ((TextView) create.findViewById(R.id.message)).setText(str);
        create.show();
        return create;
    }

    public static Dialog showBottomDialog(Activity activity, int i) {
        return showBottomDialog(activity, i, null);
    }

    public static Dialog showBottomDialog(Activity activity, int i, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        Dialog dialog = new Dialog(activity, R.style.BottomDialogStyle);
        dialog.setContentView(LayoutInflater.from(activity).inflate(i, (ViewGroup) null));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        attributes.width = ScreenUtil.getScreenWidth(activity);
        window.setGravity(80);
        window.setWindowAnimations(R.style.BottomDialogAnimation);
        dialog.show();
        return dialog;
    }

    public static AlertDialog showCommonDialog(Activity activity, String str, String str2, final DialogCall dialogCall) {
        AlertDialog create = new AlertDialog.Builder(activity).setTitle(str).setMessage(str2).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.utils.DialogUtils$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogUtils.DialogCall.this.onNegative();
            }
        }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.utils.DialogUtils$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogUtils.DialogCall.this.onPositive(dialogInterface);
            }
        }).create();
        create.show();
        return create;
    }

    public static AlertDialog showCustomDialog(Activity activity, String str, String str2, int i, final DialogCall dialogCall) {
        AlertDialog create = new AlertDialog.Builder(activity).setTitle(str).setMessage(str2).setView(i).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.utils.DialogUtils$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                DialogUtils.DialogCall.this.onNegative();
            }
        }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.utils.DialogUtils.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                DialogCall.this.onPositive(dialogInterface);
            }
        }).create();
        create.show();
        return create;
    }

    public static AlertDialog showCustomDialog(Activity activity, String str, int i, final DialogCall dialogCall) {
        AlertDialog create = new AlertDialog.Builder(activity).setTitle(str).setView(i).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.utils.DialogUtils$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                DialogUtils.DialogCall.this.onNegative();
            }
        }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.utils.DialogUtils$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                DialogUtils.DialogCall.this.onPositive(dialogInterface);
            }
        }).create();
        create.show();
        return create;
    }
}
