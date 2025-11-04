package com.wifiled.musiclib.vo;

import android.content.res.AssetFileDescriptor;
import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.File;
import java.io.Serializable;

@DatabaseTable(tableName = "music")
/* loaded from: classes3.dex */
public class MusicVO implements Serializable, Parcelable {
    public static final Parcelable.Creator<MusicVO> CREATOR = new Parcelable.Creator<MusicVO>() { // from class: com.wifiled.musiclib.vo.MusicVO.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicVO createFromParcel(Parcel parcel) {
            MusicVO musicVO = new MusicVO();
            musicVO.id = parcel.readInt();
            musicVO.url = parcel.readString();
            musicVO.title = parcel.readString();
            musicVO.playCount = parcel.readInt();
            musicVO.addDate = parcel.readLong();
            musicVO.duration = parcel.readLong();
            musicVO.artist = parcel.readString();
            musicVO.sort = parcel.readInt();
            musicVO.album = parcel.readString();
            musicVO.fileSize = parcel.readLong();
            musicVO.internet = parcel.readInt();
            return musicVO;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicVO[] newArray(int i) {
            return new MusicVO[i];
        }
    };

    @DatabaseField(canBeNull = false, defaultValue = "0")
    public long addDate;

    @DatabaseField(canBeNull = true, defaultValue = "")
    public String album;

    @DatabaseField(canBeNull = true)
    public String artist;
    private AssetFileDescriptor assetFileDescriptor;

    @DatabaseField(canBeNull = true, defaultValue = "0.00")
    public long duration;
    public boolean fileExists;

    @DatabaseField(canBeNull = true, defaultValue = "0")
    public long fileSize;

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(canBeNull = true, defaultValue = "0")
    public int internet;

    @DatabaseField(canBeNull = false, defaultValue = "0")
    public int playCount;

    @DatabaseField(canBeNull = true, defaultValue = "0")
    private int sort;
    public boolean sortChanged;
    public int srcType;

    @DatabaseField(canBeNull = true)
    public String title;

    @DatabaseField(canBeNull = false, unique = true)
    public String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void checkFile() {
        File file = new File(this.url);
        this.fileExists = !file.isDirectory() && file.exists() && file.canRead();
    }

    public int getSrcType() {
        return this.srcType;
    }

    public void setSrcType(int i) {
        this.srcType = i;
    }

    public AssetFileDescriptor getAssetFileDescriptor() {
        return this.assetFileDescriptor;
    }

    public void setAssetFileDescriptor(AssetFileDescriptor assetFileDescriptor) {
        this.assetFileDescriptor = assetFileDescriptor;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.url);
        parcel.writeString(this.title);
        parcel.writeInt(this.playCount);
        parcel.writeLong(this.addDate);
        parcel.writeLong(this.duration);
        parcel.writeString(this.artist);
        parcel.writeInt(this.sort);
        parcel.writeString(this.album);
        parcel.writeLong(this.fileSize);
        parcel.writeInt(this.internet);
    }

    public int getSort() {
        return this.sort;
    }

    public void setSort(int i) {
        if (this.sort == i) {
            return;
        }
        this.sort = i;
        this.sortChanged = true;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getPlayCount() {
        return this.playCount;
    }

    public void setPlayCount(int i) {
        this.playCount = i;
    }

    public long getAddDate() {
        return this.addDate;
    }

    public void setAddDate(long j) {
        this.addDate = j;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String str) {
        this.album = str;
    }

    public Long getFileSize() {
        return Long.valueOf(this.fileSize);
    }

    public void setFileSize(Long l) {
        this.fileSize = l.longValue();
    }

    public boolean isFileExists() {
        return this.fileExists;
    }

    public void setFileExists(boolean z) {
        this.fileExists = z;
    }

    public boolean isSortChanged() {
        return this.sortChanged;
    }

    public void setSortChanged(boolean z) {
        this.sortChanged = z;
    }

    public int getInternet() {
        return this.internet;
    }

    public void setInternet(int i) {
        this.internet = i;
    }

    public String toString() {
        return "MusicVO{id=" + this.id + ", url='" + this.url + "', title='" + this.title + "', playCount=" + this.playCount + ", addDate=" + this.addDate + ", duration=" + this.duration + ", artist='" + this.artist + "', sort=" + this.sort + ", album='" + this.album + "', fileSize=" + this.fileSize + ", internet=" + this.internet + ", fileExists=" + this.fileExists + ", sortChanged=" + this.sortChanged + '}';
    }
}
