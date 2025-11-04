package com.wifiled.musiclib.player.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.heaton.musiclib.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wifiled.musiclib.vo.MusicVO;
import java.sql.SQLException;

/* loaded from: classes3.dex */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "heaton.db";
    private static final int DATABASE_VERSION = 5;
    private RuntimeExceptionDao<MusicVO, Integer> mMusicRuntimeDao;
    private Dao<MusicVO, Integer> musicDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 5, R.raw.db_config);
        this.musicDao = null;
        this.mMusicRuntimeDao = null;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, MusicVO.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.dropTable(connectionSource, MusicVO.class, true);
            onCreate(sQLiteDatabase, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<MusicVO, Integer> getDao() throws SQLException {
        if (this.musicDao == null) {
            this.musicDao = getDao(MusicVO.class);
        }
        return this.musicDao;
    }

    public RuntimeExceptionDao<MusicVO, Integer> getMusicDao() {
        if (this.mMusicRuntimeDao == null) {
            this.mMusicRuntimeDao = getRuntimeExceptionDao(MusicVO.class);
        }
        return this.mMusicRuntimeDao;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper, android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        super.close();
        this.musicDao = null;
        this.mMusicRuntimeDao = null;
    }
}
