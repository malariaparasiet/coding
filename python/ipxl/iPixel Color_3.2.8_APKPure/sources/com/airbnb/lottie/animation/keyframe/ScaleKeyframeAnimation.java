package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.ScaleXY;
import java.util.List;

/* loaded from: classes2.dex */
public class ScaleKeyframeAnimation extends KeyframeAnimation<ScaleXY> {
    private final ScaleXY scaleXY;

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f) {
        return getValue((Keyframe<ScaleXY>) keyframe, f);
    }

    public ScaleKeyframeAnimation(List<Keyframe<ScaleXY>> list) {
        super(list);
        this.scaleXY = new ScaleXY();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public ScaleXY getValue(Keyframe<ScaleXY> keyframe, float f) {
        float f2;
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        ScaleXY scaleXY = keyframe.startValue;
        ScaleXY scaleXY2 = keyframe.endValue;
        if (this.valueCallback != null) {
            f2 = f;
            ScaleXY scaleXY3 = (ScaleXY) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), scaleXY, scaleXY2, f2, getLinearCurrentKeyframeProgress(), getProgress());
            if (scaleXY3 != null) {
                return scaleXY3;
            }
        } else {
            f2 = f;
        }
        this.scaleXY.set(MiscUtils.lerp(scaleXY.getScaleX(), scaleXY2.getScaleX(), f2), MiscUtils.lerp(scaleXY.getScaleY(), scaleXY2.getScaleY(), f2));
        return this.scaleXY;
    }
}
