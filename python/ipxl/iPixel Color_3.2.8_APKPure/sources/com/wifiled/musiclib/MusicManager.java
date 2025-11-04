package com.wifiled.musiclib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import com.wifiled.musiclib.IPlayerCallback;
import com.wifiled.musiclib.MusicPlayer;
import com.wifiled.musiclib.player.MediaPlayerCompat;
import com.wifiled.musiclib.player.PlayerHelper;
import com.wifiled.musiclib.player.ScanThread;
import com.wifiled.musiclib.player.callback.MusicScanCallback;
import com.wifiled.musiclib.player.callback.OnDataCaptureListener;
import com.wifiled.musiclib.player.callback.PlayStateCallback;
import com.wifiled.musiclib.player.callback.RecordDataCallBack;
import com.wifiled.musiclib.player.callback.ServiceConnectedCallback;
import com.wifiled.musiclib.player.db.DatabaseHelper;
import com.wifiled.musiclib.player.recoder.SoundRecordHelper;
import com.wifiled.musiclib.player.service.PlayerService;
import com.wifiled.musiclib.vo.MusicVO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class MusicManager {
    public static final int MSG_SCANNED_MUSIC = 771;
    private static final String TAG = "MusicManager";
    private static MusicManager sMusicManager;
    private boolean isRecord;
    private Context mContext;
    private DatabaseHelper mDatabaseHelper;
    private MusicPlayer mMusicPlayer;
    private MusicScanCallback mMusicScanCallback;
    private PlayStateCallback mPlayStateCallback;
    private RecordDataCallBack mRecordDataCallBack;
    private ScanThread mScanThread;
    private ServiceConnectedCallback mServiceConnectedCallback;
    private Intent mServiceIntent;
    private SoundRecordHelper mSoundRecordHelper;
    private MessageHandler mHandler = new MessageHandler();
    private ArrayList<MusicVO> mMusicList = new ArrayList<>();
    private boolean isPlaying = false;
    private boolean isMusicRhythming = false;
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.wifiled.musiclib.MusicManager.1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicManager.this.mMusicPlayer = MusicPlayer.Stub.asInterface(iBinder);
            if (MusicManager.this.mServiceConnectedCallback != null) {
                MusicManager.this.mServiceConnectedCallback.onServiceConnected();
            }
            try {
                MusicManager.this.mMusicPlayer.registerCallback(MusicManager.this.mIPlayerCallback);
            } catch (RemoteException unused) {
            }
            try {
                MusicManager.this.mMusicPlayer.dataChange(MusicManager.this.mMusicList, 0, 3);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    private IPlayerCallback mIPlayerCallback = new IPlayerCallback.Stub() { // from class: com.wifiled.musiclib.MusicManager.2
        @Override // com.wifiled.musiclib.IPlayerCallback
        public void onStateChange(int i, int i2, int i3) {
            if (MusicManager.this.mPlayStateCallback != null) {
                MusicManager.this.mPlayStateCallback.onStateChange(i, i2, i3);
            }
            if (i != 1) {
                if (i == 2 || i == 3) {
                    MusicManager.this.isPlaying = false;
                    return;
                } else if (i != 4) {
                    return;
                }
            }
            MusicManager.this.isPlaying = true;
        }

        @Override // com.wifiled.musiclib.IPlayerCallback
        public void onSeekChange(int i, int i2, String str, String str2) throws RemoteException {
            if (MusicManager.this.mPlayStateCallback != null) {
                MusicManager.this.mPlayStateCallback.onSeekChange(i, i2, str, str2);
            }
        }

        @Override // com.wifiled.musiclib.IPlayerCallback
        public void onModeChange(int i) throws RemoteException {
            if (MusicManager.this.mPlayStateCallback != null) {
                MusicManager.this.mPlayStateCallback.onModeChange(i);
            }
        }
    };

    private class MessageHandler extends Handler {
        private MessageHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 771) {
                return;
            }
            if (MusicManager.this.mMusicScanCallback != null) {
                Log.i(MusicManager.TAG, "歌曲数量 :" + MusicManager.this.mMusicList.size() + "");
                MusicManager musicManager = MusicManager.this;
                musicManager.addDefaultMusic(musicManager.mMusicList);
                MusicManager.this.mMusicScanCallback.onMusicScanResult(MusicManager.this.mMusicList);
            }
            if (MusicManager.this.mScanThread == null || !MusicManager.this.mScanThread.isAlive()) {
                return;
            }
            MusicManager.this.mScanThread.interrupt();
            MusicManager.this.mScanThread = null;
        }
    }

    public static MusicManager getInstance() {
        if (sMusicManager == null) {
            sMusicManager = new MusicManager();
        }
        return sMusicManager;
    }

    public static void cleanMusicManager() {
        sMusicManager = null;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mSoundRecordHelper = new SoundRecordHelper();
        this.mServiceIntent = new Intent(context, (Class<?>) PlayerService.class);
        if (Build.VERSION.SDK_INT > 33) {
            context.bindService(this.mServiceIntent, this.mServiceConnection, Context.BindServiceFlags.of(513L));
        } else {
            context.bindService(this.mServiceIntent, this.mServiceConnection, 1);
        }
    }

    public void setMediaPlayerType(MediaPlayerCompat.PlayerType playerType) {
        getMediaPlayer().setMediaPlayerType(playerType);
    }

    public void destroy() {
        try {
            ServiceConnection serviceConnection = this.mServiceConnection;
            if (serviceConnection != null) {
                this.mContext.unbindService(serviceConnection);
                this.mContext.stopService(this.mServiceIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopRecord();
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public boolean isRecord() {
        return this.isRecord;
    }

    public void setRecord(boolean z) {
        this.isRecord = z;
    }

    public Context getContext() {
        Context context = this.mContext;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("please init MusicManager");
    }

    public MediaPlayerCompat getMediaPlayer() {
        return PlayerHelper.getPlayer();
    }

    public DatabaseHelper getDatabaseHelper() {
        if (this.mDatabaseHelper == null) {
            this.mDatabaseHelper = new DatabaseHelper(this.mContext);
        }
        return this.mDatabaseHelper;
    }

    public void startScanMusic(MusicScanCallback musicScanCallback) {
        startScanMusic(null, null, musicScanCallback);
    }

    public void startScanMusic(String str, String str2, MusicScanCallback musicScanCallback) {
        this.mMusicScanCallback = musicScanCallback;
        ScanThread scanThread = new ScanThread(this.mContext, this.mHandler, this.mMusicList, str, str2);
        this.mScanThread = scanThread;
        scanThread.start();
        findByDB();
    }

    private void findByDB() {
        try {
            ArrayList arrayList = (ArrayList) getDatabaseHelper().getDao().queryBuilder().orderBy("title", true).query();
            int i = 0;
            while (i < arrayList.size()) {
                if (!new File(((MusicVO) arrayList.get(i)).getUrl()).exists()) {
                    arrayList.remove(i);
                    i--;
                }
                i++;
            }
            synchronized (this.mMusicList) {
                this.mMusicList.clear();
                Log.d(TAG, "添加音乐：");
                this.mMusicList.addAll(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDefaultMusic(List<MusicVO> list) {
        Log.d(TAG, "添加默认音乐");
        try {
            AssetManager assets = this.mContext.getAssets();
            int size = list != null ? list.size() : 0;
            AssetFileDescriptor openFd = assets.openFd("Alls Fair In Love.mp3");
            MusicVO musicVO = new MusicVO();
            musicVO.setId(1000001 + size);
            musicVO.setSrcType(1);
            musicVO.setDuration(49000L);
            musicVO.setArtist("Bryan Teoh");
            musicVO.setTitle("Alls Fair In Love");
            musicVO.setAssetFileDescriptor(openFd);
            AssetFileDescriptor openFd2 = assets.openFd("Attic Secrets.mp3");
            MusicVO musicVO2 = new MusicVO();
            musicVO2.setId(1000002 + size);
            musicVO2.setSrcType(1);
            musicVO2.setDuration(46000L);
            musicVO2.setArtist("Rafael Krux");
            musicVO2.setTitle("Attic Secrets");
            musicVO2.setAssetFileDescriptor(openFd2);
            AssetFileDescriptor openFd3 = assets.openFd("Baltic Levity.mp3");
            MusicVO musicVO3 = new MusicVO();
            musicVO3.setId(1000003 + size);
            musicVO3.setSrcType(1);
            musicVO3.setDuration(45000L);
            musicVO3.setArtist("Kevin MacLeod");
            musicVO3.setTitle("Baltic Levity");
            musicVO3.setAssetFileDescriptor(openFd3);
            AssetFileDescriptor openFd4 = assets.openFd("Barroom Ballet.mp3");
            MusicVO musicVO4 = new MusicVO();
            musicVO4.setId(1000004 + size);
            musicVO4.setSrcType(1);
            musicVO4.setDuration(56000L);
            musicVO4.setArtist("Kevin MacLeod");
            musicVO4.setTitle("Barroom Ballet");
            musicVO4.setAssetFileDescriptor(openFd4);
            AssetFileDescriptor openFd5 = assets.openFd("Battle Ready.mp3");
            MusicVO musicVO5 = new MusicVO();
            musicVO5.setId(1000005 + size);
            musicVO5.setSrcType(1);
            musicVO5.setDuration(59000L);
            musicVO5.setArtist(EnvironmentCompat.MEDIA_UNKNOWN);
            musicVO5.setTitle("Battle Ready");
            musicVO5.setAssetFileDescriptor(openFd5);
            AssetFileDescriptor openFd6 = assets.openFd("Circus of Freaks.mp3");
            MusicVO musicVO6 = new MusicVO();
            musicVO6.setId(size + 1000006);
            musicVO6.setSrcType(1);
            musicVO6.setDuration(57000L);
            musicVO6.setArtist(EnvironmentCompat.MEDIA_UNKNOWN);
            musicVO6.setTitle("Circus of Freaks");
            musicVO6.setAssetFileDescriptor(openFd6);
            AssetFileDescriptor openFd7 = assets.openFd("City Sunshine.mp3");
            MusicVO musicVO7 = new MusicVO();
            musicVO7.setId(1000007 + size);
            musicVO7.setSrcType(1);
            musicVO7.setDuration(47000L);
            musicVO7.setArtist("Kevin MacLeod");
            musicVO7.setTitle("City Sunshine");
            musicVO7.setAssetFileDescriptor(openFd7);
            AssetFileDescriptor openFd8 = assets.openFd("Fancy Family.mp3");
            MusicVO musicVO8 = new MusicVO();
            musicVO8.setId(1000008 + size);
            musicVO8.setSrcType(1);
            musicVO8.setDuration(59000L);
            musicVO8.setArtist("Rafael Krux");
            musicVO8.setTitle("Fancy Family");
            musicVO8.setAssetFileDescriptor(openFd8);
            AssetFileDescriptor openFd9 = assets.openFd("Happy Whistling Ukulele.mp3");
            MusicVO musicVO9 = new MusicVO();
            musicVO9.setId(1000009 + size);
            musicVO9.setSrcType(1);
            musicVO9.setDuration(52000L);
            musicVO9.setArtist("Rafael Krux");
            musicVO9.setTitle("Happy Whistling Ukulele");
            musicVO9.setAssetFileDescriptor(openFd9);
            AssetFileDescriptor openFd10 = assets.openFd("Horns.mp3");
            MusicVO musicVO10 = new MusicVO();
            musicVO10.setId(size + 10000010);
            musicVO10.setSrcType(1);
            musicVO10.setDuration(52000L);
            musicVO10.setArtist("Kevin MacLeod");
            musicVO10.setTitle("Horns");
            musicVO10.setAssetFileDescriptor(openFd10);
            this.mMusicList.add(musicVO);
            this.mMusicList.add(musicVO2);
            this.mMusicList.add(musicVO3);
            this.mMusicList.add(musicVO4);
            this.mMusicList.add(musicVO5);
            this.mMusicList.add(musicVO6);
            this.mMusicList.add(musicVO7);
            this.mMusicList.add(musicVO8);
            this.mMusicList.add(musicVO9);
            this.mMusicList.add(musicVO10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRecordDataCallBack(RecordDataCallBack recordDataCallBack) {
        this.mRecordDataCallBack = recordDataCallBack;
        this.mSoundRecordHelper.setDatareportCallBack(new SoundRecordHelper.SoundRecoderHelperCallbackData() { // from class: com.wifiled.musiclib.MusicManager$$ExternalSyntheticLambda0
            @Override // com.wifiled.musiclib.player.recoder.SoundRecordHelper.SoundRecoderHelperCallbackData
            public final void reportdata(short[] sArr) {
                MusicManager.this.lambda$setRecordDataCallBack$0(sArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setRecordDataCallBack$0(short[] sArr) {
        RecordDataCallBack recordDataCallBack = this.mRecordDataCallBack;
        if (recordDataCallBack != null) {
            recordDataCallBack.onRecordData(sArr);
        }
    }

    public void unsetRecordDataCallBack() {
        this.mRecordDataCallBack = null;
    }

    public void startRecord() {
        this.isRecord = true;
        this.mSoundRecordHelper.start();
    }

    public void stopRecord() {
        this.isRecord = false;
        SoundRecordHelper soundRecordHelper = this.mSoundRecordHelper;
        if (soundRecordHelper != null) {
            soundRecordHelper.stop();
        }
    }

    public void setDataCaptureCallback(OnDataCaptureListener onDataCaptureListener) {
        getMediaPlayer().setDataCaptureListener(onDataCaptureListener);
    }

    public void stopRhythm() {
        if (this.isMusicRhythming) {
            this.isMusicRhythming = false;
            getMediaPlayer().stopRhythm();
        }
    }

    public void stopMusicAndRhythm() {
        Log.d(TAG, "停止音乐和律动===");
        if (isPlaying()) {
            playOrPause();
            stopRhythm();
        }
    }

    public void startRhythm() {
        Log.d(TAG, "开始律动" + this.isMusicRhythming);
        if (this.isMusicRhythming) {
            return;
        }
        this.isMusicRhythming = true;
        getMediaPlayer().startRhythm();
    }

    public boolean isMusicRhythming() {
        return this.isMusicRhythming;
    }

    public void setPlayStateCallback(PlayStateCallback playStateCallback) {
        this.mPlayStateCallback = playStateCallback;
    }

    public void setServiceConnectedCallback(ServiceConnectedCallback serviceConnectedCallback) {
        this.mServiceConnectedCallback = serviceConnectedCallback;
    }

    public MusicPlayer getMusicPlayer() {
        return this.mMusicPlayer;
    }

    public void playOrPause() {
        MusicPlayer musicPlayer = this.mMusicPlayer;
        if (musicPlayer != null) {
            try {
                musicPlayer.playOrPause();
            } catch (RemoteException unused) {
            }
        }
    }

    public void playItem(MusicVO musicVO) {
        if (this.mMusicPlayer != null) {
            try {
                Log.d(TAG, "播放列表音乐：" + musicVO.getTitle() + "    " + musicVO.getSrcType());
                this.mMusicPlayer.playItem(musicVO);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void pre() {
        MusicPlayer musicPlayer = this.mMusicPlayer;
        if (musicPlayer != null) {
            try {
                musicPlayer.prev();
            } catch (RemoteException unused) {
            }
        }
    }

    public void next() {
        MusicPlayer musicPlayer = this.mMusicPlayer;
        if (musicPlayer != null) {
            try {
                musicPlayer.next();
            } catch (RemoteException unused) {
            }
        }
    }

    public void changeSeek(int i) {
        MusicPlayer musicPlayer = this.mMusicPlayer;
        if (musicPlayer != null) {
            try {
                musicPlayer.changeSeek(i);
            } catch (RemoteException unused) {
            }
        }
    }

    public void changeMode(int i) {
        MusicPlayer musicPlayer = this.mMusicPlayer;
        if (musicPlayer != null) {
            try {
                musicPlayer.changeMode(i);
            } catch (RemoteException unused) {
            }
        }
    }
}
