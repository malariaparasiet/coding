package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.RhythmLedView;
import com.wifiled.ipixels.view.ViewPagerAllResponse;

/* loaded from: classes3.dex */
public final class ActivityMusicBinding implements ViewBinding {
    public final ConstraintLayout constraintLayout3;
    public final ConstraintLayout ilMusicToolbar;
    public final ImageView ivMode;
    public final ImageView ivNext;
    public final ImageView ivPlay;
    public final ImageView ivPre;
    public final ImageView ivRhyOutsideImageBg;
    public final ImageView ivRhythm;
    public final ConstraintLayout llSeekbar;
    public final RhythmLedView rhyledview1;
    public final RhythmLedView rhyledview2;
    public final ConstraintLayout rlPlay;
    public final LinearLayout rlRhyBg;
    public final RelativeLayout rlRoot;
    public final ConstraintLayout rlTitle;
    private final RelativeLayout rootView;
    public final SeekBar sb;
    public final TextView tvSbDuration;
    public final TextView tvSbTime;
    public final TextView tvSongArtist;
    public final TextView tvSongName;
    public final ViewPagerAllResponse vpRhyhm;

    private ActivityMusicBinding(RelativeLayout rootView, ConstraintLayout constraintLayout3, ConstraintLayout ilMusicToolbar, ImageView ivMode, ImageView ivNext, ImageView ivPlay, ImageView ivPre, ImageView ivRhyOutsideImageBg, ImageView ivRhythm, ConstraintLayout llSeekbar, RhythmLedView rhyledview1, RhythmLedView rhyledview2, ConstraintLayout rlPlay, LinearLayout rlRhyBg, RelativeLayout rlRoot, ConstraintLayout rlTitle, SeekBar sb, TextView tvSbDuration, TextView tvSbTime, TextView tvSongArtist, TextView tvSongName, ViewPagerAllResponse vpRhyhm) {
        this.rootView = rootView;
        this.constraintLayout3 = constraintLayout3;
        this.ilMusicToolbar = ilMusicToolbar;
        this.ivMode = ivMode;
        this.ivNext = ivNext;
        this.ivPlay = ivPlay;
        this.ivPre = ivPre;
        this.ivRhyOutsideImageBg = ivRhyOutsideImageBg;
        this.ivRhythm = ivRhythm;
        this.llSeekbar = llSeekbar;
        this.rhyledview1 = rhyledview1;
        this.rhyledview2 = rhyledview2;
        this.rlPlay = rlPlay;
        this.rlRhyBg = rlRhyBg;
        this.rlRoot = rlRoot;
        this.rlTitle = rlTitle;
        this.sb = sb;
        this.tvSbDuration = tvSbDuration;
        this.tvSbTime = tvSbTime;
        this.tvSongArtist = tvSongArtist;
        this.tvSongName = tvSongName;
        this.vpRhyhm = vpRhyhm;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMusicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMusicBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_music, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityMusicBinding bind(View rootView) {
        int i = R.id.constraintLayout3;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.constraintLayout3);
        if (constraintLayout != null) {
            i = R.id.il_music_toolbar;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.il_music_toolbar);
            if (constraintLayout2 != null) {
                i = R.id.iv_mode;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_mode);
                if (imageView != null) {
                    i = R.id.iv_next;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_next);
                    if (imageView2 != null) {
                        i = R.id.iv_play;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_play);
                        if (imageView3 != null) {
                            i = R.id.iv_pre;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pre);
                            if (imageView4 != null) {
                                i = R.id.iv_rhy_outside_image_bg;
                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_rhy_outside_image_bg);
                                if (imageView5 != null) {
                                    i = R.id.iv_rhythm;
                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_rhythm);
                                    if (imageView6 != null) {
                                        i = R.id.ll_seekbar;
                                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.ll_seekbar);
                                        if (constraintLayout3 != null) {
                                            i = R.id.rhyledview_1;
                                            RhythmLedView rhythmLedView = (RhythmLedView) ViewBindings.findChildViewById(rootView, R.id.rhyledview_1);
                                            if (rhythmLedView != null) {
                                                i = R.id.rhyledview_2;
                                                RhythmLedView rhythmLedView2 = (RhythmLedView) ViewBindings.findChildViewById(rootView, R.id.rhyledview_2);
                                                if (rhythmLedView2 != null) {
                                                    i = R.id.rl_play;
                                                    ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.rl_play);
                                                    if (constraintLayout4 != null) {
                                                        i = R.id.rl_rhy_bg;
                                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.rl_rhy_bg);
                                                        if (linearLayout != null) {
                                                            i = R.id.rl_root;
                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_root);
                                                            if (relativeLayout != null) {
                                                                i = R.id.rl_title;
                                                                ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.rl_title);
                                                                if (constraintLayout5 != null) {
                                                                    i = R.id.sb;
                                                                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.sb);
                                                                    if (seekBar != null) {
                                                                        i = R.id.tv_sb_duration;
                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sb_duration);
                                                                        if (textView != null) {
                                                                            i = R.id.tv_sb_time;
                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sb_time);
                                                                            if (textView2 != null) {
                                                                                i = R.id.tv_song_artist;
                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_song_artist);
                                                                                if (textView3 != null) {
                                                                                    i = R.id.tv_song_name;
                                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_song_name);
                                                                                    if (textView4 != null) {
                                                                                        i = R.id.vp_rhyhm;
                                                                                        ViewPagerAllResponse viewPagerAllResponse = (ViewPagerAllResponse) ViewBindings.findChildViewById(rootView, R.id.vp_rhyhm);
                                                                                        if (viewPagerAllResponse != null) {
                                                                                            return new ActivityMusicBinding((RelativeLayout) rootView, constraintLayout, constraintLayout2, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, constraintLayout3, rhythmLedView, rhythmLedView2, constraintLayout4, linearLayout, relativeLayout, constraintLayout5, seekBar, textView, textView2, textView3, textView4, viewPagerAllResponse);
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
