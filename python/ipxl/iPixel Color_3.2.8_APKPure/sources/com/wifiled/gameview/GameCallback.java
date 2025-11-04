package com.wifiled.gameview;

import android.graphics.Bitmap;
import android.view.View;

/* loaded from: classes3.dex */
public interface GameCallback {
    void onGameExit();

    void onGameFrame(Bitmap bitmap);

    void onGameFrame(View view);

    void onGameOver();

    void onGameStart();

    void onGameStop();
}
