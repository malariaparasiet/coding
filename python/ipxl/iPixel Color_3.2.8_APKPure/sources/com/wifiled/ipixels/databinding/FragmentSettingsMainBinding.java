package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public final class FragmentSettingsMainBinding implements ViewBinding {
    public final AppCompatImageView arraw1;
    public final AppCompatImageView arraw2;
    public final AppCompatImageView arraw3;
    public final AppCompatImageView brightIcon;
    public final ConstraintLayout checkUp;
    public final ImageView checkUpImage;
    public final TextView checkUpText;
    public final AppCompatImageView cleanIcon;
    public final AppCompatImageView devinfoIcon;
    public final AppCompatImageView devpwdIcon;
    public final AppCompatImageView languageIcon;
    public final RelativeLayout rlLightSeek;
    private final ConstraintLayout rootView;
    public final AppCompatButton rotate0;
    public final AppCompatButton rotate180;
    public final AppCompatButton rotate270;
    public final AppCompatButton rotate90;
    public final AppCompatImageView rotateIcon;
    public final LinearLayoutCompat rotateLl;
    public final SeekBar sbSettingsAlpha;
    public final ConstraintLayout tvBright;
    public final ConstraintLayout tvClearData;
    public final ConstraintLayout tvDevinfo;
    public final ConstraintLayout tvDevpwd;
    public final ConstraintLayout tvLanguage;
    public final ConstraintLayout tvRotate;
    public final ConstraintLayout tvUpsideDown;
    public final TextView tvVer;
    public final AppCompatImageView upsideDownIcon;

    private FragmentSettingsMainBinding(ConstraintLayout rootView, AppCompatImageView arraw1, AppCompatImageView arraw2, AppCompatImageView arraw3, AppCompatImageView brightIcon, ConstraintLayout checkUp, ImageView checkUpImage, TextView checkUpText, AppCompatImageView cleanIcon, AppCompatImageView devinfoIcon, AppCompatImageView devpwdIcon, AppCompatImageView languageIcon, RelativeLayout rlLightSeek, AppCompatButton rotate0, AppCompatButton rotate180, AppCompatButton rotate270, AppCompatButton rotate90, AppCompatImageView rotateIcon, LinearLayoutCompat rotateLl, SeekBar sbSettingsAlpha, ConstraintLayout tvBright, ConstraintLayout tvClearData, ConstraintLayout tvDevinfo, ConstraintLayout tvDevpwd, ConstraintLayout tvLanguage, ConstraintLayout tvRotate, ConstraintLayout tvUpsideDown, TextView tvVer, AppCompatImageView upsideDownIcon) {
        this.rootView = rootView;
        this.arraw1 = arraw1;
        this.arraw2 = arraw2;
        this.arraw3 = arraw3;
        this.brightIcon = brightIcon;
        this.checkUp = checkUp;
        this.checkUpImage = checkUpImage;
        this.checkUpText = checkUpText;
        this.cleanIcon = cleanIcon;
        this.devinfoIcon = devinfoIcon;
        this.devpwdIcon = devpwdIcon;
        this.languageIcon = languageIcon;
        this.rlLightSeek = rlLightSeek;
        this.rotate0 = rotate0;
        this.rotate180 = rotate180;
        this.rotate270 = rotate270;
        this.rotate90 = rotate90;
        this.rotateIcon = rotateIcon;
        this.rotateLl = rotateLl;
        this.sbSettingsAlpha = sbSettingsAlpha;
        this.tvBright = tvBright;
        this.tvClearData = tvClearData;
        this.tvDevinfo = tvDevinfo;
        this.tvDevpwd = tvDevpwd;
        this.tvLanguage = tvLanguage;
        this.tvRotate = tvRotate;
        this.tvUpsideDown = tvUpsideDown;
        this.tvVer = tvVer;
        this.upsideDownIcon = upsideDownIcon;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSettingsMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSettingsMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_settings_main, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentSettingsMainBinding bind(View rootView) {
        int i = R.id.arraw_1;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.arraw_1);
        if (appCompatImageView != null) {
            i = R.id.arraw_2;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.arraw_2);
            if (appCompatImageView2 != null) {
                i = R.id.arraw_3;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.arraw_3);
                if (appCompatImageView3 != null) {
                    i = R.id.bright_icon;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.bright_icon);
                    if (appCompatImageView4 != null) {
                        i = R.id.check_up;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.check_up);
                        if (constraintLayout != null) {
                            i = R.id.check_up_image;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.check_up_image);
                            if (imageView != null) {
                                i = R.id.check_up_text;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.check_up_text);
                                if (textView != null) {
                                    i = R.id.clean_icon;
                                    AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.clean_icon);
                                    if (appCompatImageView5 != null) {
                                        i = R.id.devinfo_icon;
                                        AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.devinfo_icon);
                                        if (appCompatImageView6 != null) {
                                            i = R.id.devpwd_icon;
                                            AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.devpwd_icon);
                                            if (appCompatImageView7 != null) {
                                                i = R.id.language_icon;
                                                AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.language_icon);
                                                if (appCompatImageView8 != null) {
                                                    i = R.id.rl_light_seek;
                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_light_seek);
                                                    if (relativeLayout != null) {
                                                        i = R.id.rotate_0;
                                                        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(rootView, R.id.rotate_0);
                                                        if (appCompatButton != null) {
                                                            i = R.id.rotate_180;
                                                            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(rootView, R.id.rotate_180);
                                                            if (appCompatButton2 != null) {
                                                                i = R.id.rotate_270;
                                                                AppCompatButton appCompatButton3 = (AppCompatButton) ViewBindings.findChildViewById(rootView, R.id.rotate_270);
                                                                if (appCompatButton3 != null) {
                                                                    i = R.id.rotate_90;
                                                                    AppCompatButton appCompatButton4 = (AppCompatButton) ViewBindings.findChildViewById(rootView, R.id.rotate_90);
                                                                    if (appCompatButton4 != null) {
                                                                        i = R.id.rotate_icon;
                                                                        AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.rotate_icon);
                                                                        if (appCompatImageView9 != null) {
                                                                            i = R.id.rotate_ll;
                                                                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, R.id.rotate_ll);
                                                                            if (linearLayoutCompat != null) {
                                                                                i = R.id.sb_settings_alpha;
                                                                                SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.sb_settings_alpha);
                                                                                if (seekBar != null) {
                                                                                    i = R.id.tv_bright;
                                                                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tv_bright);
                                                                                    if (constraintLayout2 != null) {
                                                                                        i = R.id.tv_clear_data;
                                                                                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tv_clear_data);
                                                                                        if (constraintLayout3 != null) {
                                                                                            i = R.id.tv_devinfo;
                                                                                            ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tv_devinfo);
                                                                                            if (constraintLayout4 != null) {
                                                                                                i = R.id.tv_devpwd;
                                                                                                ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tv_devpwd);
                                                                                                if (constraintLayout5 != null) {
                                                                                                    i = R.id.tv_language;
                                                                                                    ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tv_language);
                                                                                                    if (constraintLayout6 != null) {
                                                                                                        i = R.id.tv_rotate;
                                                                                                        ConstraintLayout constraintLayout7 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tv_rotate);
                                                                                                        if (constraintLayout7 != null) {
                                                                                                            i = R.id.tv_upside_down;
                                                                                                            ConstraintLayout constraintLayout8 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.tv_upside_down);
                                                                                                            if (constraintLayout8 != null) {
                                                                                                                i = R.id.tv_ver;
                                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_ver);
                                                                                                                if (textView2 != null) {
                                                                                                                    i = R.id.upside_down_icon;
                                                                                                                    AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.upside_down_icon);
                                                                                                                    if (appCompatImageView10 != null) {
                                                                                                                        return new FragmentSettingsMainBinding((ConstraintLayout) rootView, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, constraintLayout, imageView, textView, appCompatImageView5, appCompatImageView6, appCompatImageView7, appCompatImageView8, relativeLayout, appCompatButton, appCompatButton2, appCompatButton3, appCompatButton4, appCompatImageView9, linearLayoutCompat, seekBar, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, constraintLayout6, constraintLayout7, constraintLayout8, textView2, appCompatImageView10);
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
