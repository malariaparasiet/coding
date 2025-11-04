package com.google.android.material.motion;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.PathParser;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialAttributes;

/* loaded from: classes2.dex */
public class MotionUtils {
    private static final String EASING_TYPE_CUBIC_BEZIER = "cubic-bezier";
    private static final String EASING_TYPE_FORMAT_END = ")";
    private static final String EASING_TYPE_FORMAT_START = "(";
    private static final String EASING_TYPE_PATH = "path";

    private MotionUtils() {
    }

    public static SpringForce resolveThemeSpringForce(Context context, int i, int i2) {
        TypedArray obtainStyledAttributes;
        TypedValue resolve = MaterialAttributes.resolve(context, i);
        if (resolve == null) {
            obtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.MaterialSpring, 0, i2);
        } else {
            obtainStyledAttributes = context.obtainStyledAttributes(resolve.resourceId, R.styleable.MaterialSpring);
        }
        SpringForce springForce = new SpringForce();
        try {
            float f = obtainStyledAttributes.getFloat(R.styleable.MaterialSpring_stiffness, Float.MIN_VALUE);
            if (f == Float.MIN_VALUE) {
                throw new IllegalArgumentException("A MaterialSpring style must have stiffness value.");
            }
            float f2 = obtainStyledAttributes.getFloat(R.styleable.MaterialSpring_damping, Float.MIN_VALUE);
            if (f2 == Float.MIN_VALUE) {
                throw new IllegalArgumentException("A MaterialSpring style must have a damping value.");
            }
            springForce.setStiffness(f);
            springForce.setDampingRatio(f2);
            return springForce;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int resolveThemeDuration(Context context, int i, int i2) {
        return MaterialAttributes.resolveInteger(context, i, i2);
    }

    public static TimeInterpolator resolveThemeInterpolator(Context context, int i, TimeInterpolator timeInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i, typedValue, true)) {
            return timeInterpolator;
        }
        if (typedValue.type != 3) {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
        String valueOf = String.valueOf(typedValue.string);
        if (isLegacyEasingAttribute(valueOf)) {
            return getLegacyThemeInterpolator(valueOf);
        }
        return AnimationUtils.loadInterpolator(context, typedValue.resourceId);
    }

    private static TimeInterpolator getLegacyThemeInterpolator(String str) {
        if (isLegacyEasingType(str, EASING_TYPE_CUBIC_BEZIER)) {
            String[] split = getLegacyEasingContent(str, EASING_TYPE_CUBIC_BEZIER).split(",");
            if (split.length != 4) {
                throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + split.length);
            }
            return new PathInterpolator(getLegacyControlPoint(split, 0), getLegacyControlPoint(split, 1), getLegacyControlPoint(split, 2), getLegacyControlPoint(split, 3));
        }
        if (isLegacyEasingType(str, "path")) {
            return new PathInterpolator(PathParser.createPathFromPathData(getLegacyEasingContent(str, "path")));
        }
        throw new IllegalArgumentException("Invalid motion easing type: " + str);
    }

    private static boolean isLegacyEasingAttribute(String str) {
        return isLegacyEasingType(str, EASING_TYPE_CUBIC_BEZIER) || isLegacyEasingType(str, "path");
    }

    private static boolean isLegacyEasingType(String str, String str2) {
        return str.startsWith(new StringBuilder().append(str2).append(EASING_TYPE_FORMAT_START).toString()) && str.endsWith(EASING_TYPE_FORMAT_END);
    }

    private static String getLegacyEasingContent(String str, String str2) {
        return str.substring(str2.length() + EASING_TYPE_FORMAT_START.length(), str.length() - EASING_TYPE_FORMAT_END.length());
    }

    private static float getLegacyControlPoint(String[] strArr, int i) {
        float parseFloat = Float.parseFloat(strArr[i]);
        if (parseFloat < 0.0f || parseFloat > 1.0f) {
            throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
        }
        return parseFloat;
    }
}
