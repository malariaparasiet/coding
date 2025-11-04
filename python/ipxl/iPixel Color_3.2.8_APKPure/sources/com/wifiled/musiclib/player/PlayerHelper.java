package com.wifiled.musiclib.player;

import android.content.res.AssetFileDescriptor;
import com.wifiled.musiclib.player.callback.OnCompletionListener;
import com.wifiled.musiclib.player.service.PlayerService;
import java.io.IOException;
import java.util.Random;

/* loaded from: classes3.dex */
public class PlayerHelper {
    private static final String TAG = "PlayerHelper";
    private static MediaPlayerCompat player;
    private PlayerService mPlayerService;
    private boolean mute;
    private float volume;

    public PlayerHelper(PlayerService playerService) {
        this.mPlayerService = playerService;
        if (player == null) {
            player = new MediaPlayerCompat();
        }
    }

    public static MediaPlayerCompat getPlayer() {
        if (player == null) {
            player = new MediaPlayerCompat();
        }
        return player;
    }

    public void mute(float f) {
        boolean z = this.mute;
        this.mute = !z;
        if (!z) {
            this.volume = f;
            player.setVolume(0.0f, 0.0f);
        } else {
            MediaPlayerCompat mediaPlayerCompat = player;
            float f2 = this.volume;
            mediaPlayerCompat.setVolume(f2, f2);
        }
    }

    public boolean play(String str, AssetFileDescriptor assetFileDescriptor, int i) {
        try {
            player.play(str, assetFileDescriptor, i);
            player.start();
            player.setOnCompletionListener(new OnCompletionListener() { // from class: com.wifiled.musiclib.player.PlayerHelper.1
                @Override // com.wifiled.musiclib.player.callback.OnCompletionListener
                public void onCompletion(Object obj) {
                    int i2 = PlayerHelper.this.mPlayerService.playMode;
                    if (i2 == 0) {
                        Random random = new Random();
                        int i3 = PlayerHelper.this.mPlayerService.servicePosition;
                        do {
                            PlayerHelper.this.mPlayerService.servicePosition = random.nextInt(PlayerHelper.this.mPlayerService.serviceMusicList.size());
                        } while (i3 == PlayerHelper.this.mPlayerService.servicePosition);
                        PlayerHelper.this.mPlayerService.state = 1;
                    } else if (i2 == 1) {
                        PlayerHelper.player.setLooping(true);
                        PlayerHelper.this.mPlayerService.state = 1;
                    } else if (i2 == 3) {
                        if (PlayerHelper.this.mPlayerService.servicePosition == PlayerHelper.this.mPlayerService.serviceMusicList.size() - 1) {
                            PlayerHelper.this.mPlayerService.servicePosition = 0;
                        } else {
                            PlayerHelper.this.mPlayerService.servicePosition++;
                        }
                        PlayerHelper.this.mPlayerService.state = 1;
                    }
                    PlayerHelper.this.mPlayerService.stateChange = true;
                }
            });
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
            return false;
        } catch (SecurityException e4) {
            e4.printStackTrace();
            return false;
        }
    }

    public void pause() {
        player.pause();
    }

    public void continuePlay() {
        player.start();
    }

    public void stop() {
        if (isPlaying().booleanValue()) {
            player.stop();
        }
        player = null;
    }

    public int getPlayCurrentTime() {
        return player.getPlayCurrentTime();
    }

    public int getPlayDuration() {
        return player.getPlayDuration();
    }

    public void seekToMusic(int i) {
        if ((player.getMediaPlayer() instanceof MediaPlayer) && ((MediaPlayer) player.getMediaPlayer()).getPlayState() == 4) {
            this.mPlayerService.state = 1;
            return;
        }
        MediaPlayerCompat mediaPlayerCompat = player;
        if (mediaPlayerCompat != null) {
            mediaPlayerCompat.seekTo(i);
        }
    }

    public Boolean isPlaying() {
        MediaPlayerCompat mediaPlayerCompat = player;
        if (mediaPlayerCompat != null) {
            return Boolean.valueOf(mediaPlayerCompat.isPlaying());
        }
        return false;
    }
}
