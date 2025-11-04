package com.wifiled.musiclib.player;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import androidx.camera.core.CameraInfo;
import com.google.android.gms.common.internal.ImagesContract;
import com.j256.ormlite.dao.Dao;
import com.wifiled.musiclib.MusicManager;
import com.wifiled.musiclib.vo.MusicVO;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class ScanThread extends Thread {
    private static final String TAG = "ScanThread";
    private Context mContext;
    private Handler mHandler;
    private ArrayList<MusicVO> mMusicList;
    private String sortOrder;
    private String where;

    public ScanThread(Context context, Handler handler, ArrayList<MusicVO> arrayList, String str, String str2) {
        this.sortOrder = "title asc";
        this.mContext = context;
        this.mHandler = handler;
        this.mMusicList = arrayList;
        this.where = str;
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.sortOrder = str2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Dao<MusicVO, Integer> dao;
        Cursor query = this.mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, this.where, null, this.sortOrder);
        try {
            dao = MusicManager.getInstance().getDatabaseHelper().getDao();
        } catch (SQLException e) {
            e.printStackTrace();
            dao = null;
        }
        if (dao == null) {
            return;
        }
        int columnIndex = query.getColumnIndex("title");
        int columnIndex2 = query.getColumnIndex("artist");
        int columnIndex3 = query.getColumnIndex("album");
        int columnIndex4 = query.getColumnIndex("_data");
        int columnIndex5 = query.getColumnIndex("duration");
        int columnIndex6 = query.getColumnIndex("_size");
        if (query.getCount() != 0) {
            while (query.moveToNext()) {
                String string = query.getString(columnIndex4);
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put(ImagesContract.URL, string);
                    List<MusicVO> queryForFieldValuesArgs = dao.queryForFieldValuesArgs(hashMap);
                    if (queryForFieldValuesArgs == null || queryForFieldValuesArgs.isEmpty()) {
                        long j = query.getLong(columnIndex5);
                        Log.v("ruis", "cur.getString(pathIndex)--- " + string + "   duration:" + j);
                        if (!TextUtils.isEmpty(string) && (string.endsWith(".mp3") || string.endsWith(".ogg"))) {
                            if (new File(string).exists()) {
                                MusicVO musicVO = new MusicVO();
                                musicVO.title = query.getString(columnIndex);
                                String string2 = query.getString(columnIndex2);
                                if (TextUtils.isEmpty(string2)) {
                                    string2 = CameraInfo.IMPLEMENTATION_TYPE_UNKNOWN;
                                }
                                musicVO.artist = string2;
                                musicVO.album = query.getString(columnIndex3);
                                musicVO.url = query.getString(columnIndex4);
                                musicVO.duration = j;
                                musicVO.fileSize = query.getLong(columnIndex6);
                                musicVO.addDate = System.currentTimeMillis();
                                dao.create(musicVO);
                                musicVO.setSort(musicVO.id);
                                this.mMusicList.add(musicVO);
                            }
                        }
                    }
                } catch (SQLException unused) {
                }
            }
        }
        query.close();
        this.mHandler.obtainMessage(MusicManager.MSG_SCANNED_MUSIC).sendToTarget();
    }
}
