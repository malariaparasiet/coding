package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import android.util.Range;
import android.util.Size;
import androidx.arch.core.util.Function;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.camera.video.VideoSpec;
import androidx.camera.video.internal.config.VideoConfigUtil;
import androidx.camera.video.internal.encoder.VideoEncoderConfig;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import androidx.camera.video.internal.utils.EncoderProfilesUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ExtraSupportedQualityQuirk implements Quirk {
    private static final String MOTO_C_FRONT_CAM_ID = "1";

    static boolean load() {
        return isMotoC();
    }

    private static boolean isMotoC() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto c".equalsIgnoreCase(Build.MODEL);
    }

    public Map<Integer, EncoderProfilesProxy> getExtraEncoderProfiles(CameraInfoInternal cameraInfoInternal, EncoderProfilesProvider encoderProfilesProvider, Function<VideoEncoderConfig, VideoEncoderInfo> function) {
        if (isMotoC()) {
            return getExtraEncoderProfilesForMotoC(cameraInfoInternal, encoderProfilesProvider, function);
        }
        return Collections.emptyMap();
    }

    private Map<Integer, EncoderProfilesProxy> getExtraEncoderProfilesForMotoC(CameraInfoInternal cameraInfoInternal, EncoderProfilesProvider encoderProfilesProvider, Function<VideoEncoderConfig, VideoEncoderInfo> function) {
        EncoderProfilesProxy all;
        EncoderProfilesProxy.VideoProfileProxy firstVideoProfile;
        if (!MOTO_C_FRONT_CAM_ID.equals(cameraInfoInternal.getCameraId()) || encoderProfilesProvider.hasProfile(4) || (firstVideoProfile = EncoderProfilesUtil.getFirstVideoProfile((all = encoderProfilesProvider.getAll(1)))) == null) {
            return null;
        }
        EncoderProfilesProxy.ImmutableEncoderProfilesProxy create = EncoderProfilesProxy.ImmutableEncoderProfilesProxy.create(all.getDefaultDurationSeconds(), all.getRecommendedFileFormat(), all.getAudioProfiles(), Collections.singletonList(EncoderProfilesUtil.deriveVideoProfile(firstVideoProfile, SizeUtil.RESOLUTION_480P, getSupportedBitrateRange(firstVideoProfile, function))));
        HashMap hashMap = new HashMap();
        hashMap.put(4, create);
        if (SizeUtil.getArea(SizeUtil.RESOLUTION_480P) > SizeUtil.getArea(new Size(firstVideoProfile.getWidth(), firstVideoProfile.getHeight()))) {
            hashMap.put(1, create);
        }
        return hashMap;
    }

    private static Range<Integer> getSupportedBitrateRange(EncoderProfilesProxy.VideoProfileProxy videoProfileProxy, Function<VideoEncoderConfig, VideoEncoderInfo> function) {
        VideoEncoderInfo apply = function.apply(VideoConfigUtil.toVideoEncoderConfig(videoProfileProxy));
        if (apply != null) {
            return apply.getSupportedBitrateRange();
        }
        return VideoSpec.BITRATE_RANGE_AUTO;
    }
}
