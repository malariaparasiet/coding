package com.wifiled.ipixels.view.video_clip;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.utils.VideoUtils;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MediaClipper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00052\u00020\u0001:\u0002\u0004\u0005B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/wifiled/ipixels/view/video_clip/MediaClipper;", "", "<init>", "()V", "MEDIA_TYPE", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaClipper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final void clip(String str, String str2, long j, long j2, MEDIA_TYPE media_type, VideoTrimCallback videoTrimCallback) {
        INSTANCE.clip(str, str2, j, j2, media_type, videoTrimCallback);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: MediaClipper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/wifiled/ipixels/view/video_clip/MediaClipper$MEDIA_TYPE;", "", "<init>", "(Ljava/lang/String;I)V", "AUDIO", "VIDEO", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MEDIA_TYPE {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ MEDIA_TYPE[] $VALUES;
        public static final MEDIA_TYPE AUDIO = new MEDIA_TYPE("AUDIO", 0);
        public static final MEDIA_TYPE VIDEO = new MEDIA_TYPE("VIDEO", 1);

        private static final /* synthetic */ MEDIA_TYPE[] $values() {
            return new MEDIA_TYPE[]{AUDIO, VIDEO};
        }

        public static EnumEntries<MEDIA_TYPE> getEntries() {
            return $ENTRIES;
        }

        public static MEDIA_TYPE valueOf(String str) {
            return (MEDIA_TYPE) Enum.valueOf(MEDIA_TYPE.class, str);
        }

        public static MEDIA_TYPE[] values() {
            return (MEDIA_TYPE[]) $VALUES.clone();
        }

        private MEDIA_TYPE(String str, int i) {
        }

        static {
            MEDIA_TYPE[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* compiled from: MediaClipper.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u0014"}, d2 = {"Lcom/wifiled/ipixels/view/video_clip/MediaClipper$Companion;", "", "<init>", "()V", "clip", "", "inputPath", "", "outputPath", "startTime", "", "endTime", "mediaType", "Lcom/wifiled/ipixels/view/video_clip/MediaClipper$MEDIA_TYPE;", "callback", "Lcom/wifiled/ipixels/view/video_clip/VideoTrimCallback;", "getTrackIndex", "", "mediaExtractor", "Landroid/media/MediaExtractor;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void clip(String inputPath, String outputPath, long startTime, long endTime, MEDIA_TYPE mediaType, VideoTrimCallback callback) {
            int readSampleData;
            Intrinsics.checkNotNullParameter(inputPath, "inputPath");
            Intrinsics.checkNotNullParameter(outputPath, "outputPath");
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            Intrinsics.checkNotNullParameter(callback, "callback");
            long currentTimeMillis = System.currentTimeMillis();
            long j = 1000;
            long j2 = startTime * j;
            long j3 = endTime * j;
            MediaExtractor mediaExtractor = new MediaExtractor();
            mediaExtractor.setDataSource(inputPath);
            int trackIndex = MediaClipper.INSTANCE.getTrackIndex(mediaExtractor, mediaType);
            mediaExtractor.selectTrack(trackIndex);
            mediaExtractor.seekTo(j2, 2);
            MediaFormat trackFormat = mediaExtractor.getTrackFormat(trackIndex);
            Intrinsics.checkNotNullExpressionValue(trackFormat, "getTrackFormat(...)");
            trackFormat.setInteger("durationUs", (int) (j3 - j2));
            MediaMuxer mediaMuxer = new MediaMuxer(outputPath, 0);
            int videoRation = VideoUtils.getVideoRation(inputPath);
            LogUtils.logi("MediaClipper>>>[视频角度]: " + videoRation, new Object[0]);
            mediaMuxer.setOrientationHint(videoRation);
            int addTrack = mediaMuxer.addTrack(trackFormat);
            mediaMuxer.start();
            callback.onStartTrim();
            ByteBuffer allocate = ByteBuffer.allocate(trackFormat.getInteger("max-input-size"));
            while (true) {
                readSampleData = mediaExtractor.readSampleData(allocate, 0);
                if (readSampleData < 0 || mediaExtractor.getSampleTime() > j3) {
                    break;
                }
                MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                bufferInfo.offset = 0;
                bufferInfo.size = readSampleData;
                bufferInfo.flags = mediaExtractor.getSampleFlags();
                bufferInfo.presentationTimeUs = mediaExtractor.getSampleTime();
                mediaMuxer.writeSampleData(addTrack, allocate, bufferInfo);
                mediaExtractor.advance();
            }
            LogUtils.logi("sampleSize = " + readSampleData + ", sampleTime = " + mediaExtractor.getSampleTime() + ", endTimeUs = " + j3, new Object[0]);
            callback.onFinishTrim(outputPath);
            LogUtils.logi(">>>[clip]:总耗时: " + (System.currentTimeMillis() - currentTimeMillis) + "ms", new Object[0]);
            mediaExtractor.release();
            mediaMuxer.stop();
            mediaMuxer.release();
        }

        private final int getTrackIndex(MediaExtractor mediaExtractor, MEDIA_TYPE mediaType) {
            int trackCount = mediaExtractor.getTrackCount();
            for (int i = 0; i < trackCount; i++) {
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
                String str = mediaType == MEDIA_TYPE.VIDEO ? "video" : "audio";
                String string = trackFormat.getString("mime");
                Intrinsics.checkNotNull(string);
                if (StringsKt.contains$default((CharSequence) string, (CharSequence) str, false, 2, (Object) null)) {
                    return i;
                }
            }
            return -1;
        }
    }
}
