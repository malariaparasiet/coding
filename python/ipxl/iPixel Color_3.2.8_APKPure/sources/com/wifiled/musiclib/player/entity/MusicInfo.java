package com.wifiled.musiclib.player.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class MusicInfo implements Serializable, Parcelable {
    public static final Parcelable.Creator<MusicInfo> CREATOR = new Parcelable.Creator<MusicInfo>() { // from class: com.wifiled.musiclib.player.entity.MusicInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicInfo createFromParcel(Parcel parcel) {
            MusicInfo musicInfo = new MusicInfo();
            musicInfo.setTitle(parcel.readString());
            musicInfo.setArtist(parcel.readString());
            musicInfo.setAlbum(parcel.readString());
            musicInfo.setPath(parcel.readString());
            musicInfo.setDuration(Long.valueOf(parcel.readLong()));
            musicInfo.setSize(Long.valueOf(parcel.readLong()));
            musicInfo.setAlbum_img_path(parcel.readString());
            musicInfo.setLyric_file_name(parcel.readString());
            return musicInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicInfo[] newArray(int i) {
            return new MusicInfo[i];
        }
    };
    private static final long serialVersionUID = 1;
    private String album;
    private String album_img_path;
    private String artist;
    private Long duration;
    private int id;
    private String lyric_file_name;
    private String path;
    private Long size;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
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

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long l) {
        this.size = l;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getPath() {
        return this.path;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
        parcel.writeString(this.album);
        parcel.writeString(this.path);
        parcel.writeLong(this.duration.longValue());
        parcel.writeLong(this.size.longValue());
        parcel.writeString(this.album_img_path);
        parcel.writeString(this.lyric_file_name);
    }

    public void setAlbum_img_path(String str) {
        this.album_img_path = str;
    }

    public String getAlbum_img_path() {
        return this.album_img_path;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public void setLyric_file_name(String str) {
        this.lyric_file_name = str;
    }

    public String getLyric_file_name() {
        return this.lyric_file_name;
    }
}
