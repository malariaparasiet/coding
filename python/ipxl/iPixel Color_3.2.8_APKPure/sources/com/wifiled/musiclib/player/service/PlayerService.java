package com.wifiled.musiclib.player.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.heaton.musiclib.R;
import com.wifiled.musiclib.IPlayerCallback;
import com.wifiled.musiclib.MusicPlayer;
import com.wifiled.musiclib.player.MediaPlayerCompat;
import com.wifiled.musiclib.player.PlayerHelper;
import com.wifiled.musiclib.utils.Utils;
import com.wifiled.musiclib.vo.MusicVO;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;

/* loaded from: classes3.dex */
public class PlayerService extends Service implements Runnable {
    public static final int ERROR_CHANGE = 260;
    public static final int MAX_SEEK_TICKER = 500;
    public static final int MODE_CHANGE = 259;
    public static final int NOTIFICATION_ID = 2;
    public static final int SEEK_CHANGE = 258;
    public static final int STATE_CHANGE = 257;
    public static final String TAG = "PlayerService";
    public boolean hold;
    private AudioManager mAudioManager;
    private int mLastSeekTime;
    public boolean modeChange;
    public PlayerHelper player;
    public boolean seekChange;
    public List<MusicVO> serviceMusicList;
    public boolean stateChange;
    public int state = 0;
    public int playMode = 3;
    public Boolean isRun = true;
    public int servicePosition = 0;
    private int progress = 0;
    private int max = 0;
    private String time = "0:00";
    private String duration = "0:00";
    private RemoteCallbackList<IPlayerCallback> mCallbacks = new RemoteCallbackList<>();
    private Handler handler = new MessageHandler(this);
    MusicPlayer.Stub mBinder = new MusicPlayer.Stub() { // from class: com.wifiled.musiclib.player.service.PlayerService.1
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0020, code lost:
        
            if (r0 != 4) goto L19;
         */
        @Override // com.wifiled.musiclib.MusicPlayer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void playOrPause() throws android.os.RemoteException {
            /*
                r5 = this;
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                java.util.List<com.wifiled.musiclib.vo.MusicVO> r0 = r0.serviceMusicList
                if (r0 == 0) goto L35
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                java.util.List<com.wifiled.musiclib.vo.MusicVO> r0 = r0.serviceMusicList
                int r0 = r0.size()
                if (r0 <= 0) goto L35
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                int r0 = r0.state
                r1 = 1
                if (r0 == 0) goto L2d
                r2 = 2
                if (r0 == r1) goto L28
                r3 = 4
                if (r0 == r2) goto L23
                r4 = 3
                if (r0 == r4) goto L2d
                if (r0 == r3) goto L28
                goto L31
            L23:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r0.state = r3
                goto L31
            L28:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r0.state = r2
                goto L31
            L2d:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r0.state = r1
            L31:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r0.stateChange = r1
            L35:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wifiled.musiclib.player.service.PlayerService.AnonymousClass1.playOrPause():void");
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void playItem(MusicVO musicVO) throws RemoteException {
            Log.d(PlayerService.TAG, "播放歌曲:" + musicVO.toString());
            if (PlayerService.this.serviceMusicList != null && PlayerService.this.serviceMusicList.size() > 0) {
                int size = PlayerService.this.serviceMusicList.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    MusicVO musicVO2 = PlayerService.this.serviceMusicList.get(i);
                    if (musicVO2 != null && musicVO.id > 0 && musicVO.id == musicVO2.id) {
                        PlayerService.this.servicePosition = i;
                        break;
                    }
                    i++;
                }
            }
            PlayerService.this.state = 1;
            PlayerService.this.stateChange = true;
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void playPosition(int i) throws RemoteException {
            Log.d(PlayerService.TAG, "播放位置" + i);
            PlayerService.this.servicePosition = i;
            PlayerService.this.stateChange = true;
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void dataChange(List<MusicVO> list, int i, int i2) throws RemoteException {
            if (list != null) {
                PlayerService.this.serviceMusicList = list;
            }
            if (i > -1) {
                PlayerService.this.servicePosition = i;
                MediaPlayerCompat player = PlayerHelper.getPlayer();
                if (player.isNativeMediaPlayer() && !PlayerService.this.serviceMusicList.isEmpty()) {
                    try {
                        player.play(PlayerService.this.serviceMusicList.get(PlayerService.this.servicePosition).url, PlayerService.this.serviceMusicList.get(PlayerService.this.servicePosition).getAssetFileDescriptor(), PlayerService.this.serviceMusicList.get(PlayerService.this.servicePosition).getSrcType());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (i2 > -1) {
                PlayerService.this.playMode = i2;
            }
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void registerCallback(IPlayerCallback iPlayerCallback) throws RemoteException {
            if (iPlayerCallback == null) {
                return;
            }
            PlayerService.this.mCallbacks.register(iPlayerCallback);
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void unRegisterCallback(IPlayerCallback iPlayerCallback) throws RemoteException {
            PlayerService.this.mCallbacks.unregister(iPlayerCallback);
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void pause() throws RemoteException {
            if (PlayerService.this.serviceMusicList == null || PlayerService.this.serviceMusicList.size() <= 0) {
                return;
            }
            int i = PlayerService.this.state;
            if (i == 1 || i == 4) {
                PlayerService.this.state = 2;
            }
            PlayerService.this.stateChange = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
        
            if (r0 != 3) goto L15;
         */
        @Override // com.wifiled.musiclib.MusicPlayer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void resume() throws android.os.RemoteException {
            /*
                r3 = this;
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                java.util.List<com.wifiled.musiclib.vo.MusicVO> r0 = r0.serviceMusicList
                if (r0 == 0) goto L2c
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                java.util.List<com.wifiled.musiclib.vo.MusicVO> r0 = r0.serviceMusicList
                int r0 = r0.size()
                if (r0 <= 0) goto L2c
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                int r0 = r0.state
                r1 = 1
                if (r0 == 0) goto L24
                r2 = 2
                if (r0 == r2) goto L1e
                r2 = 3
                if (r0 == r2) goto L24
                goto L28
            L1e:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r2 = 4
                r0.state = r2
                goto L28
            L24:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r0.state = r1
            L28:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r0.stateChange = r1
            L2c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wifiled.musiclib.player.service.PlayerService.AnonymousClass1.resume():void");
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void hold() throws RemoteException {
            if (PlayerService.this.serviceMusicList == null || PlayerService.this.serviceMusicList.size() <= 0) {
                return;
            }
            int i = PlayerService.this.state;
            if (i == 1 || i == 4) {
                PlayerService.this.state = 2;
                PlayerService.this.hold = true;
                PlayerService.this.stateChange = true;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
        
            if (r0 != 3) goto L17;
         */
        @Override // com.wifiled.musiclib.MusicPlayer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void unHold() throws android.os.RemoteException {
            /*
                r3 = this;
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                java.util.List<com.wifiled.musiclib.vo.MusicVO> r0 = r0.serviceMusicList
                if (r0 == 0) goto L32
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                java.util.List<com.wifiled.musiclib.vo.MusicVO> r0 = r0.serviceMusicList
                int r0 = r0.size()
                if (r0 <= 0) goto L32
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                boolean r0 = r0.hold
                if (r0 == 0) goto L32
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                int r0 = r0.state
                r1 = 1
                if (r0 == 0) goto L2a
                r2 = 2
                if (r0 == r2) goto L24
                r2 = 3
                if (r0 == r2) goto L2a
                goto L2e
            L24:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r2 = 4
                r0.state = r2
                goto L2e
            L2a:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r0.state = r1
            L2e:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r0.stateChange = r1
            L32:
                com.wifiled.musiclib.player.service.PlayerService r0 = com.wifiled.musiclib.player.service.PlayerService.this
                r1 = 0
                r0.hold = r1
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wifiled.musiclib.player.service.PlayerService.AnonymousClass1.unHold():void");
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void mute() throws RemoteException {
            if (PlayerService.this.mAudioManager == null) {
                PlayerService playerService = PlayerService.this;
                playerService.mAudioManager = (AudioManager) playerService.getSystemService("audio");
            }
            PlayerService.this.player.mute(PlayerService.this.mAudioManager.getStreamVolume(3) / PlayerService.this.mAudioManager.getStreamMaxVolume(3));
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void next() throws RemoteException {
            if (PlayerService.this.serviceMusicList == null || PlayerService.this.serviceMusicList.size() <= 0) {
                return;
            }
            int i = PlayerService.this.playMode;
            if (i != 0) {
                if (i == 1 || i == 3) {
                    if (PlayerService.this.serviceMusicList.size() == 1) {
                        PlayerService.this.servicePosition = 0;
                    } else if (PlayerService.this.servicePosition == PlayerService.this.serviceMusicList.size() - 1) {
                        PlayerService.this.servicePosition = 0;
                    } else {
                        PlayerService.this.servicePosition++;
                    }
                    PlayerService.this.state = 1;
                }
            } else if (PlayerService.this.serviceMusicList.size() == 1) {
                PlayerService.this.servicePosition = 0;
                PlayerService.this.state = 1;
            } else {
                Random random = new Random();
                int i2 = PlayerService.this.servicePosition;
                do {
                    PlayerService playerService = PlayerService.this;
                    playerService.servicePosition = random.nextInt(playerService.serviceMusicList.size());
                } while (i2 == PlayerService.this.servicePosition);
                PlayerService.this.state = 1;
            }
            PlayerService.this.stateChange = true;
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void prev() throws RemoteException {
            if (PlayerService.this.serviceMusicList == null || PlayerService.this.serviceMusicList.size() <= 0) {
                return;
            }
            int i = PlayerService.this.playMode;
            if (i != 0) {
                if (i == 1 || i == 3) {
                    if (PlayerService.this.serviceMusicList.size() == 1) {
                        PlayerService.this.servicePosition = 0;
                    } else if (PlayerService.this.servicePosition == 0) {
                        PlayerService playerService = PlayerService.this;
                        playerService.servicePosition = playerService.serviceMusicList.size() - 1;
                    } else {
                        PlayerService.this.servicePosition--;
                    }
                    PlayerService.this.state = 1;
                }
            } else if (PlayerService.this.serviceMusicList.size() == 1) {
                PlayerService.this.servicePosition = 0;
                PlayerService.this.state = 1;
            } else {
                Random random = new Random();
                int i2 = PlayerService.this.servicePosition;
                do {
                    PlayerService playerService2 = PlayerService.this;
                    playerService2.servicePosition = random.nextInt(playerService2.serviceMusicList.size());
                } while (i2 == PlayerService.this.servicePosition);
                PlayerService.this.state = 1;
            }
            PlayerService.this.stateChange = true;
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void changeMode(int i) throws RemoteException {
            if (i > -1) {
                PlayerService.this.playMode = i;
            } else {
                int i2 = PlayerService.this.playMode;
                if (i2 == 0) {
                    PlayerService.this.playMode = 1;
                } else if (i2 == 1) {
                    PlayerService.this.playMode = 3;
                } else if (i2 == 3) {
                    PlayerService.this.playMode = 0;
                }
            }
            PlayerService.this.modeChange = true;
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void changeSeek(int i) throws RemoteException {
            PlayerService.this.state = 4;
            PlayerService.this.player.seekToMusic(i);
            PlayerService.this.stateChange = true;
            PlayerService.this.seekChange = true;
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public int getAudioSessionId() throws RemoteException {
            PlayerHelper playerHelper = PlayerService.this.player;
            return ((MediaPlayer) PlayerHelper.getPlayer().getMediaPlayer()).getAudioSessionId();
        }
    };

    private static class MessageHandler extends Handler {
        private WeakReference<PlayerService> weakReference;

        MessageHandler(PlayerService playerService) {
            this.weakReference = new WeakReference<>(playerService);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            PlayerService playerService;
            super.handleMessage(message);
            WeakReference<PlayerService> weakReference = this.weakReference;
            if (weakReference == null || (playerService = weakReference.get()) == null) {
                return;
            }
            int i = 0;
            switch (message.what) {
                case 257:
                    int beginBroadcast = playerService.mCallbacks.beginBroadcast();
                    while (i < beginBroadcast) {
                        try {
                            ((IPlayerCallback) playerService.mCallbacks.getBroadcastItem(i)).onStateChange(playerService.state, playerService.playMode, playerService.servicePosition);
                        } catch (RemoteException unused) {
                        }
                        i++;
                    }
                    playerService.mCallbacks.finishBroadcast();
                    break;
                case PlayerService.SEEK_CHANGE /* 258 */:
                    int beginBroadcast2 = playerService.mCallbacks.beginBroadcast();
                    while (i < beginBroadcast2) {
                        try {
                            ((IPlayerCallback) playerService.mCallbacks.getBroadcastItem(i)).onSeekChange(playerService.progress, playerService.max, playerService.time, playerService.duration);
                        } catch (RemoteException unused2) {
                        }
                        i++;
                    }
                    playerService.mCallbacks.finishBroadcast();
                    break;
                case PlayerService.MODE_CHANGE /* 259 */:
                    int beginBroadcast3 = playerService.mCallbacks.beginBroadcast();
                    while (i < beginBroadcast3) {
                        try {
                            ((IPlayerCallback) playerService.mCallbacks.getBroadcastItem(i)).onModeChange(playerService.playMode);
                        } catch (RemoteException unused3) {
                        }
                        i++;
                    }
                    playerService.mCallbacks.finishBroadcast();
                    break;
                case PlayerService.ERROR_CHANGE /* 260 */:
                    int beginBroadcast4 = playerService.mCallbacks.beginBroadcast();
                    while (i < beginBroadcast4) {
                        try {
                            ((IPlayerCallback) playerService.mCallbacks.getBroadcastItem(i)).onStateChange(playerService.state, playerService.playMode, playerService.servicePosition);
                        } catch (RemoteException unused4) {
                        }
                        i++;
                    }
                    playerService.mCallbacks.finishBroadcast();
                    if (playerService.playMode != 1) {
                        try {
                            playerService.mBinder.next();
                            break;
                        } catch (RemoteException unused5) {
                            return;
                        }
                    }
                    break;
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.isRun = true;
        this.player = new PlayerHelper(this);
        this.stateChange = true;
        new Thread(this).start();
    }

    private void startForeService() {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
        String appName = Utils.getAppName(getApplicationContext());
        String str = appName + "_channelId";
        notificationManager.createNotificationChannel(new NotificationChannel(str, appName, 2));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), str);
        builder.setContentTitle("正在运行").setContentText("").setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_launcher).setAutoCancel(true).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        startForeground(2, builder.build());
    }

    public static void cancelNotifiction(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancel(2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:3|(1:55)(3:5|(3:45|46|(2:48|(2:50|(1:52))(1:53))(1:54))(4:7|(3:11|(1:13)(2:41|(1:43))|(2:15|16)(4:38|39|40|36))|44|(0)(0))|17)|18|(4:20|(1:22)|23|(1:27))|28|(1:30)|31|32|33|35|36|1) */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0090 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009c A[SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.musiclib.player.service.PlayerService.run():void");
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean stopService(Intent intent) {
        return super.stopService(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.isRun = false;
        this.player.stop();
        this.player = null;
        stopForeground(true);
        super.onDestroy();
    }
}
