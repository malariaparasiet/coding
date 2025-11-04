package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.ColorBarView;
import com.wifiled.ipixels.view.LedView;
import com.wifiled.ipixels.view.customview.CustomImageView;

/* loaded from: classes3.dex */
public final class FragmentDiyImageBinding implements ViewBinding {
    public final ImageView btnDecrease;
    public final ImageView btnEraser;
    public final ImageView btnHorizontalMirror;
    public final ImageView btnImport;
    public final ImageView btnIncrease;
    public final ImageView btnMoveOutsideBg;
    public final ImageView btnMovePainted;
    public final ImageView btnPaint;
    public final ImageView btnRedo;
    public final ImageView btnSelect;
    public final ImageView btnUndo;
    public final ImageView btnVerticalMirror;
    public final ConstraintLayout clBottoomFunction;
    public final ConstraintLayout clDiyColorSet;
    public final ConstraintLayout clDiyImageToolbar;
    public final ConstraintLayout clFlowFirst;
    public final ConstraintLayout clLedview;
    public final ColorBarView colorBarView;
    public final ImageView imageView3;
    public final ImageView ivDiyAnimClear;
    public final CustomImageView ivShowDiycolor;
    public final LedView ledView;
    public final Flow llBottom;
    public final Flow llBottom1;
    public final RecyclerView recyclerviewColor;
    public final RelativeLayout rl128bg;
    public final RelativeLayout rlOutsideDiycolorFrame;
    private final ConstraintLayout rootView;
    public final TextView tvScaleValue;

    private FragmentDiyImageBinding(ConstraintLayout rootView, ImageView btnDecrease, ImageView btnEraser, ImageView btnHorizontalMirror, ImageView btnImport, ImageView btnIncrease, ImageView btnMoveOutsideBg, ImageView btnMovePainted, ImageView btnPaint, ImageView btnRedo, ImageView btnSelect, ImageView btnUndo, ImageView btnVerticalMirror, ConstraintLayout clBottoomFunction, ConstraintLayout clDiyColorSet, ConstraintLayout clDiyImageToolbar, ConstraintLayout clFlowFirst, ConstraintLayout clLedview, ColorBarView colorBarView, ImageView imageView3, ImageView ivDiyAnimClear, CustomImageView ivShowDiycolor, LedView ledView, Flow llBottom, Flow llBottom1, RecyclerView recyclerviewColor, RelativeLayout rl128bg, RelativeLayout rlOutsideDiycolorFrame, TextView tvScaleValue) {
        this.rootView = rootView;
        this.btnDecrease = btnDecrease;
        this.btnEraser = btnEraser;
        this.btnHorizontalMirror = btnHorizontalMirror;
        this.btnImport = btnImport;
        this.btnIncrease = btnIncrease;
        this.btnMoveOutsideBg = btnMoveOutsideBg;
        this.btnMovePainted = btnMovePainted;
        this.btnPaint = btnPaint;
        this.btnRedo = btnRedo;
        this.btnSelect = btnSelect;
        this.btnUndo = btnUndo;
        this.btnVerticalMirror = btnVerticalMirror;
        this.clBottoomFunction = clBottoomFunction;
        this.clDiyColorSet = clDiyColorSet;
        this.clDiyImageToolbar = clDiyImageToolbar;
        this.clFlowFirst = clFlowFirst;
        this.clLedview = clLedview;
        this.colorBarView = colorBarView;
        this.imageView3 = imageView3;
        this.ivDiyAnimClear = ivDiyAnimClear;
        this.ivShowDiycolor = ivShowDiycolor;
        this.ledView = ledView;
        this.llBottom = llBottom;
        this.llBottom1 = llBottom1;
        this.recyclerviewColor = recyclerviewColor;
        this.rl128bg = rl128bg;
        this.rlOutsideDiycolorFrame = rlOutsideDiycolorFrame;
        this.tvScaleValue = tvScaleValue;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDiyImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDiyImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_diy_image, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentDiyImageBinding bind(View rootView) {
        int i = R.id.btn_decrease;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_decrease);
        if (imageView != null) {
            i = R.id.btn_eraser;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_eraser);
            if (imageView2 != null) {
                i = R.id.btn_horizontal_mirror;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_horizontal_mirror);
                if (imageView3 != null) {
                    i = R.id.btn_import;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_import);
                    if (imageView4 != null) {
                        i = R.id.btn_increase;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_increase);
                        if (imageView5 != null) {
                            i = R.id.btn_move_outside_bg;
                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_move_outside_bg);
                            if (imageView6 != null) {
                                i = R.id.btn_move_painted;
                                ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_move_painted);
                                if (imageView7 != null) {
                                    i = R.id.btn_paint;
                                    ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_paint);
                                    if (imageView8 != null) {
                                        i = R.id.btn_redo;
                                        ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_redo);
                                        if (imageView9 != null) {
                                            i = R.id.btn_select;
                                            ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_select);
                                            if (imageView10 != null) {
                                                i = R.id.btn_undo;
                                                ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_undo);
                                                if (imageView11 != null) {
                                                    i = R.id.btn_vertical_mirror;
                                                    ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_vertical_mirror);
                                                    if (imageView12 != null) {
                                                        i = R.id.cl_bottoom_function;
                                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_bottoom_function);
                                                        if (constraintLayout != null) {
                                                            i = R.id.cl_diy_color_set;
                                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_diy_color_set);
                                                            if (constraintLayout2 != null) {
                                                                i = R.id.cl_diy_image_toolbar;
                                                                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_diy_image_toolbar);
                                                                if (constraintLayout3 != null) {
                                                                    i = R.id.cl_flow_first;
                                                                    ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_flow_first);
                                                                    if (constraintLayout4 != null) {
                                                                        i = R.id.cl_ledview;
                                                                        ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_ledview);
                                                                        if (constraintLayout5 != null) {
                                                                            i = R.id.colorBarView;
                                                                            ColorBarView colorBarView = (ColorBarView) ViewBindings.findChildViewById(rootView, R.id.colorBarView);
                                                                            if (colorBarView != null) {
                                                                                i = R.id.imageView3;
                                                                                ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView3);
                                                                                if (imageView13 != null) {
                                                                                    i = R.id.iv_diy_anim_clear;
                                                                                    ImageView imageView14 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_diy_anim_clear);
                                                                                    if (imageView14 != null) {
                                                                                        i = R.id.iv_show_diycolor;
                                                                                        CustomImageView customImageView = (CustomImageView) ViewBindings.findChildViewById(rootView, R.id.iv_show_diycolor);
                                                                                        if (customImageView != null) {
                                                                                            i = R.id.ledView;
                                                                                            LedView ledView = (LedView) ViewBindings.findChildViewById(rootView, R.id.ledView);
                                                                                            if (ledView != null) {
                                                                                                i = R.id.ll_bottom;
                                                                                                Flow flow = (Flow) ViewBindings.findChildViewById(rootView, R.id.ll_bottom);
                                                                                                if (flow != null) {
                                                                                                    i = R.id.ll_bottom1;
                                                                                                    Flow flow2 = (Flow) ViewBindings.findChildViewById(rootView, R.id.ll_bottom1);
                                                                                                    if (flow2 != null) {
                                                                                                        i = R.id.recyclerview_color;
                                                                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_color);
                                                                                                        if (recyclerView != null) {
                                                                                                            i = R.id.rl_128bg;
                                                                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_128bg);
                                                                                                            if (relativeLayout != null) {
                                                                                                                i = R.id.rl_outside_diycolor_frame;
                                                                                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rl_outside_diycolor_frame);
                                                                                                                if (relativeLayout2 != null) {
                                                                                                                    i = R.id.tv_scale_value;
                                                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_scale_value);
                                                                                                                    if (textView != null) {
                                                                                                                        return new FragmentDiyImageBinding((ConstraintLayout) rootView, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, colorBarView, imageView13, imageView14, customImageView, ledView, flow, flow2, recyclerView, relativeLayout, relativeLayout2, textView);
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
