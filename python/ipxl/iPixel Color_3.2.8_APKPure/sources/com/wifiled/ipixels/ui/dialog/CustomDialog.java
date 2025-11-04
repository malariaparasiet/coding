package com.wifiled.ipixels.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public class CustomDialog extends Dialog {
    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private View btnView;
        private ImageView cancelBtn;
        private DialogInterface.OnClickListener cancel_btnClickListener;
        private String cancel_btnText;
        private ImageView confirmBtn;
        private DialogInterface.OnClickListener confirm_btnClickListener;
        private String confirm_btnText;
        private Context context;
        private View mContentView;
        private CustomDialog mDialog;
        private TextView mTVMsg;
        private String message;
        private String title;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder refReshMessage(String message) {
            this.mTVMsg.setText(message);
            if (this.mTVMsg.getVisibility() == 8) {
                this.mTVMsg.setVisibility(0);
            }
            return this;
        }

        public Builder setDialogShow(boolean isSHow) {
            if (isSHow) {
                this.mDialog.show();
                return this;
            }
            this.mDialog.dismiss();
            return this;
        }

        public Builder setCancelBtnShow(boolean isSHow) {
            if (isSHow) {
                this.cancelBtn.setVisibility(0);
                this.confirmBtn.setVisibility(8);
                return this;
            }
            this.cancelBtn.setVisibility(8);
            this.confirmBtn.setVisibility(0);
            return this;
        }

        public Builder setCancelBtnShow2(boolean isSHow) {
            if (isSHow) {
                this.cancelBtn.setVisibility(0);
                this.confirmBtn.setVisibility(8);
                return this;
            }
            this.cancelBtn.setVisibility(8);
            this.confirmBtn.setVisibility(8);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setmContentView(View v) {
            this.mContentView = v;
            return this;
        }

        public Builder setPositiveButton(String confirm_btnText, DialogInterface.OnClickListener listener) {
            this.confirm_btnText = confirm_btnText;
            this.confirm_btnClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String cancel_btnText, DialogInterface.OnClickListener listener) {
            this.cancel_btnText = cancel_btnText;
            this.cancel_btnClickListener = listener;
            return this;
        }

        public CustomDialog create() {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
            this.mDialog = new CustomDialog(this.context, R.style.MyDialogStyle);
            View inflate = layoutInflater.inflate(R.layout.dialog_custom, (ViewGroup) null);
            this.mDialog.addContentView(inflate, new WindowManager.LayoutParams(-1, -2));
            TextView textView = (TextView) inflate.findViewById(R.id.title);
            this.confirmBtn = (ImageView) inflate.findViewById(R.id.confirm_btn);
            this.cancelBtn = (ImageView) inflate.findViewById(R.id.cancel_btn);
            this.mTVMsg = (TextView) inflate.findViewById(R.id.message);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.ll_message);
            if (this.message != null) {
                textView.setText(this.title);
                textView.getPaint().setFakeBoldText(true);
            } else {
                textView.setVisibility(8);
            }
            if (this.confirm_btnText != null) {
                if (this.confirm_btnClickListener != null) {
                    this.confirmBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.dialog.CustomDialog.Builder.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            Builder.this.confirm_btnClickListener.onClick(Builder.this.mDialog, -1);
                        }
                    });
                }
            } else {
                this.confirmBtn.setVisibility(8);
                this.mTVMsg.setVisibility(8);
            }
            if (this.cancel_btnText != null) {
                if (this.cancel_btnClickListener != null) {
                    this.cancelBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.dialog.CustomDialog.Builder.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            Builder.this.cancel_btnClickListener.onClick(Builder.this.mDialog, -2);
                        }
                    });
                }
            } else {
                this.cancelBtn.setVisibility(8);
                this.mTVMsg.setVisibility(8);
            }
            String str = this.message;
            if (str != null) {
                this.mTVMsg.setText(str);
            } else if (inflate != null) {
                linearLayout.removeView(inflate);
                linearLayout.addView(inflate, new LinearLayout.LayoutParams(-2, -2));
            }
            Window window = this.mDialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (this.context.getResources().getDisplayMetrics().widthPixels * 0.8d);
            window.setAttributes(attributes);
            this.mDialog.setContentView(inflate);
            return this.mDialog;
        }
    }
}
