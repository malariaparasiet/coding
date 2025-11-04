package com.wifiled.musiclib.player.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.wifiled.musiclib.player.constant.DbFinal;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import com.wifiled.musiclib.player.entity.MusicInfo;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class MusicDBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public MusicDBHelper(Context context) {
        super(context, DbFinal.DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.db = getWritableDatabase();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS local_music (_id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT UNIQUE NOT NULL,artist TEXT,album TEXT,path TEXT NOT NULL,duration LONG NOT NULL,file_size LONG NOT NULL,lrc_title TEXTlrc_path TEXTalbum_img_title TEXTalbum_img_path TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS favorites (_id INTEGER PRIMARY KEY AUTOINCREMENT,local_id INTEGER UNIQUE NOT NULL);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS artist (_id INTEGER PRIMARY KEY AUTOINCREMENT,local_artist TEXT UNIQUE NOT NULL);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS album (_id INTEGER PRIMARY KEY AUTOINCREMENT,local_album TEXT UNIQUE NOT NULL);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS local_music");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS favorites");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS album");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS artist");
        onCreate(sQLiteDatabase);
    }

    public void clearLocal() {
        this.db.execSQL("DROP TABLE IF EXISTS local_music");
        onCreate(this.db);
    }

    public Long insertLocal(MusicInfo musicInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", musicInfo.getTitle());
        contentValues.put("artist", musicInfo.getArtist());
        contentValues.put("album", musicInfo.getAlbum());
        contentValues.put(DbFinal.LOCAL_PATH, musicInfo.getPath());
        contentValues.put("duration", musicInfo.getDuration());
        contentValues.put(DbFinal.LOCAL_FILE_SIZE, musicInfo.getSize());
        contentValues.put(DbFinal.LOCAL_LRC_TITLE, musicInfo.getLyric_file_name());
        return Long.valueOf(this.db.insert(DbFinal.TABLE_LOCALMUSIC, null, contentValues));
    }

    public Long insertFav(MusicInfo musicInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbFinal.FAVORITES_LOCAL_ID, Integer.valueOf(musicInfo.getId()));
        return Long.valueOf(this.db.insert(DbFinal.TABLE_FAVORITES, null, contentValues));
    }

    public Long insertArtist(MusicInfo musicInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbFinal.ARTIST_LOCAL_ARTIST, musicInfo.getArtist());
        return Long.valueOf(this.db.insert("artist", null, contentValues));
    }

    public Long insertAlbum(MusicInfo musicInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbFinal.ALBUM_LOCAL_ALBUM, musicInfo.getAlbum());
        return Long.valueOf(this.db.insert("album", null, contentValues));
    }

    public Cursor queryLocalByID() {
        return this.db.query(DbFinal.TABLE_LOCALMUSIC, null, null, null, null, null, "_id asc");
    }

    public Cursor queryFavByID() {
        return this.db.query(DbFinal.TABLE_FAVORITES, null, null, null, null, null, "_id asc");
    }

    public Cursor queryFavFromLocal() {
        Cursor query = this.db.query(DbFinal.TABLE_FAVORITES, null, null, null, null, null, "_id asc");
        int count = query.getCount();
        String[] strArr = new String[count];
        if (query.getCount() != 0) {
            query.moveToFirst();
            Log.e(PlayerFinal.TAG, "查询到的总数" + query.getCount() + "数组长度" + count);
            int i = 0;
            do {
                strArr[i] = String.valueOf(query.getInt(query.getColumnIndex(DbFinal.FAVORITES_LOCAL_ID)));
                Log.e(PlayerFinal.TAG, i + "========" + strArr[i]);
                i++;
                query.moveToNext();
            } while (!query.isAfterLast());
        }
        return this.db.query(DbFinal.TABLE_LOCALMUSIC, null, "_id=?", strArr, null, null, "_id asc");
    }

    public Cursor queryArtistByID() {
        return this.db.query("artist", null, null, null, null, null, "_id asc");
    }

    public Cursor queryAlbumByID() {
        return this.db.query("album", null, null, null, null, null, "_id asc");
    }

    public ArrayList<MusicInfo> getMusicListFromLocal(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        ArrayList<MusicInfo> arrayList = new ArrayList<>();
        do {
            MusicInfo musicInfo = new MusicInfo();
            musicInfo.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            musicInfo.setArtist(cursor.getString(cursor.getColumnIndex("artist")));
            musicInfo.setAlbum(cursor.getString(cursor.getColumnIndex("album")));
            musicInfo.setPath(cursor.getString(cursor.getColumnIndex(DbFinal.LOCAL_PATH)));
            musicInfo.setDuration(Long.valueOf(cursor.getLong(cursor.getColumnIndex("duration"))));
            musicInfo.setSize(Long.valueOf(cursor.getLong(cursor.getColumnIndex(DbFinal.LOCAL_FILE_SIZE))));
            musicInfo.setLyric_file_name(cursor.getString(cursor.getColumnIndex(DbFinal.LOCAL_LRC_TITLE)));
            arrayList.add(musicInfo);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return arrayList;
    }

    public int delLocal(String str) {
        return this.db.delete(DbFinal.TABLE_LOCALMUSIC, "title=?", new String[]{str});
    }

    public int delFav(int i) {
        return this.db.delete(DbFinal.TABLE_FAVORITES, "local_id=?", new String[]{i + ""});
    }

    public Cursor queryLocalByArtist(String str) {
        return this.db.query(DbFinal.TABLE_LOCALMUSIC, null, "artist=?", new String[]{str}, null, null, "_id asc");
    }

    public Cursor queryLocalByAlbum(String str) {
        return this.db.query(DbFinal.TABLE_LOCALMUSIC, null, "album=?", new String[]{str}, null, null, "_id asc");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public synchronized void close() {
        SQLiteDatabase sQLiteDatabase = this.db;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        super.close();
    }
}
