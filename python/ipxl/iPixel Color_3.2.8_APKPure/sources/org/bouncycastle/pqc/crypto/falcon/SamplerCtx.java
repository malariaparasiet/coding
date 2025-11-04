package org.bouncycastle.pqc.crypto.falcon;

import androidx.camera.video.AudioStats;

/* loaded from: classes4.dex */
class SamplerCtx {
    double sigma_min = AudioStats.AUDIO_AMPLITUDE_NONE;
    FalconRNG p = new FalconRNG();

    SamplerCtx() {
    }
}
