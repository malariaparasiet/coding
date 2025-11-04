package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFive12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewSix12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewThree12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewTwo12;

/* loaded from: classes3.dex */
public final class SubzoneListItem21Binding implements ViewBinding {
    public final ImageView image;
    public final ConstraintLayout itemLayout;
    public final ImageView ivSchedule;
    public final ImageView ivScheduleSelect;
    private final ConstraintLayout rootView;
    public final TemplateViewOne12 template1;
    public final TemplateViewTwo12 template2;
    public final TemplateViewThree12 template3;
    public final ConstraintLayout template3264;
    public final TemplateViewFour12 template4;
    public final TemplateViewFive12 template5;
    public final TemplateViewSix12 template6;

    private SubzoneListItem21Binding(ConstraintLayout rootView, ImageView image, ConstraintLayout itemLayout, ImageView ivSchedule, ImageView ivScheduleSelect, TemplateViewOne12 template1, TemplateViewTwo12 template2, TemplateViewThree12 template3, ConstraintLayout template3264, TemplateViewFour12 template4, TemplateViewFive12 template5, TemplateViewSix12 template6) {
        this.rootView = rootView;
        this.image = image;
        this.itemLayout = itemLayout;
        this.ivSchedule = ivSchedule;
        this.ivScheduleSelect = ivScheduleSelect;
        this.template1 = template1;
        this.template2 = template2;
        this.template3 = template3;
        this.template3264 = template3264;
        this.template4 = template4;
        this.template5 = template5;
        this.template6 = template6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static SubzoneListItem21Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SubzoneListItem21Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.subzone_list_item_2_1, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static SubzoneListItem21Binding bind(View rootView) {
        int i = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            i = R.id.iv_schedule;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_schedule);
            if (imageView2 != null) {
                i = R.id.iv_schedule_select;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_schedule_select);
                if (imageView3 != null) {
                    i = R.id.template1;
                    TemplateViewOne12 templateViewOne12 = (TemplateViewOne12) ViewBindings.findChildViewById(rootView, R.id.template1);
                    if (templateViewOne12 != null) {
                        i = R.id.template2;
                        TemplateViewTwo12 templateViewTwo12 = (TemplateViewTwo12) ViewBindings.findChildViewById(rootView, R.id.template2);
                        if (templateViewTwo12 != null) {
                            i = R.id.template3;
                            TemplateViewThree12 templateViewThree12 = (TemplateViewThree12) ViewBindings.findChildViewById(rootView, R.id.template3);
                            if (templateViewThree12 != null) {
                                i = R.id.template_3264;
                                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.template_3264);
                                if (constraintLayout2 != null) {
                                    i = R.id.template4;
                                    TemplateViewFour12 templateViewFour12 = (TemplateViewFour12) ViewBindings.findChildViewById(rootView, R.id.template4);
                                    if (templateViewFour12 != null) {
                                        i = R.id.template5;
                                        TemplateViewFive12 templateViewFive12 = (TemplateViewFive12) ViewBindings.findChildViewById(rootView, R.id.template5);
                                        if (templateViewFive12 != null) {
                                            i = R.id.template6;
                                            TemplateViewSix12 templateViewSix12 = (TemplateViewSix12) ViewBindings.findChildViewById(rootView, R.id.template6);
                                            if (templateViewSix12 != null) {
                                                return new SubzoneListItem21Binding(constraintLayout, imageView, constraintLayout, imageView2, imageView3, templateViewOne12, templateViewTwo12, templateViewThree12, constraintLayout2, templateViewFour12, templateViewFive12, templateViewSix12);
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
