package com.wifiled.musiclib.player.recoder;

import android.media.AudioRecord;
import android.media.audiofx.Visualizer;
import android.util.Log;
import com.wifiled.musiclib.player.AudioFx.BaseVisualizerView;

/* loaded from: classes3.dex */
public class SoundRecordHelper {
    private Visualizer mVisualizer;
    private BaseVisualizerView mVisualizerView;
    private int recSampleRate = 16000;
    private int recChannel = 2;
    private int recAudioFormat = 2;
    private int audioSource = 1;
    private boolean recordFlag = false;
    AudioRecord audioRecord = null;
    RecordThread recThread = null;
    SoundRecoderHelperCallbackData callbackData = null;
    public int minRecBufSize = AudioRecord.getMinBufferSize(16000, 2, 2);

    public interface SoundRecoderHelperCallbackData {
        void reportdata(short[] sArr);
    }

    public void start() {
        prepare();
        this.audioRecord.startRecording();
        RecordThread recordThread = new RecordThread(this.audioRecord, this.minRecBufSize);
        this.recThread = recordThread;
        this.recordFlag = true;
        recordThread.start();
    }

    private void prepare() {
        if (this.audioRecord == null) {
            this.audioRecord = new AudioRecord(this.audioSource, this.recSampleRate, this.recChannel, this.recAudioFormat, this.minRecBufSize);
        }
    }

    public void stop() {
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord != null) {
            audioRecord.stop();
        }
        this.recordFlag = false;
        this.recThread = null;
    }

    public void setDatareportCallBack(SoundRecoderHelperCallbackData soundRecoderHelperCallbackData) {
        this.callbackData = soundRecoderHelperCallbackData;
    }

    public class RecordThread extends Thread {
        private AudioRecord ar;
        private int bufSize;

        public RecordThread(AudioRecord audioRecord, int i) {
            this.ar = audioRecord;
            this.bufSize = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                short[] sArr = new short[this.bufSize];
                while (SoundRecordHelper.this.recordFlag) {
                    if (this.ar.read(sArr, 0, this.bufSize) == -2) {
                        SoundRecordHelper.this.recordFlag = false;
                    } else {
                        SoundRecordHelper.this.callbackData.reportdata(sArr);
                    }
                }
                this.ar.stop();
            } catch (Exception e) {
                Log.e("Receive message E", e.toString());
            }
        }
    }
}
