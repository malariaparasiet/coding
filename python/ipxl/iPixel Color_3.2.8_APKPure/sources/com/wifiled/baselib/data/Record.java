package com.wifiled.baselib.data;

import androidx.core.provider.FontsContractCompat;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

/* compiled from: Record.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u0085\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u00065"}, d2 = {"Lcom/wifiled/baselib/data/Record;", "", "appId", "", "categoryId", "categoryName", "filePath", "fileID", "format", "height", AnnotatedPrivateKey.LABEL, "sort", "type", "width", "file", "Ljava/io/File;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V", "getAppId", "()Ljava/lang/String;", "getCategoryId", "getCategoryName", "getFilePath", "getFileID", "getFormat", "getHeight", "getLabel", "getSort", "getType", "getWidth", "getFile", "()Ljava/io/File;", "setFile", "(Ljava/io/File;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "", "toString", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Record {

    @SerializedName("app_id")
    private final String appId;

    @SerializedName("category_id")
    private final String categoryId;

    @SerializedName("category_name")
    private final String categoryName;
    private File file;

    @SerializedName(FontsContractCompat.Columns.FILE_ID)
    private final String fileID;

    @SerializedName("file_path")
    private final String filePath;

    @SerializedName("format")
    private final String format;

    @SerializedName("height")
    private final String height;

    @SerializedName(AnnotatedPrivateKey.LABEL)
    private final String label;

    @SerializedName("sort")
    private final String sort;

    @SerializedName("type")
    private final String type;

    @SerializedName("width")
    private final String width;

    public static /* synthetic */ Record copy$default(Record record, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = record.appId;
        }
        if ((i & 2) != 0) {
            str2 = record.categoryId;
        }
        if ((i & 4) != 0) {
            str3 = record.categoryName;
        }
        if ((i & 8) != 0) {
            str4 = record.filePath;
        }
        if ((i & 16) != 0) {
            str5 = record.fileID;
        }
        if ((i & 32) != 0) {
            str6 = record.format;
        }
        if ((i & 64) != 0) {
            str7 = record.height;
        }
        if ((i & 128) != 0) {
            str8 = record.label;
        }
        if ((i & 256) != 0) {
            str9 = record.sort;
        }
        if ((i & 512) != 0) {
            str10 = record.type;
        }
        if ((i & 1024) != 0) {
            str11 = record.width;
        }
        if ((i & 2048) != 0) {
            file = record.file;
        }
        String str12 = str11;
        File file2 = file;
        String str13 = str9;
        String str14 = str10;
        String str15 = str7;
        String str16 = str8;
        String str17 = str5;
        String str18 = str6;
        return record.copy(str, str2, str3, str4, str17, str18, str15, str16, str13, str14, str12, file2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAppId() {
        return this.appId;
    }

    /* renamed from: component10, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component11, reason: from getter */
    public final String getWidth() {
        return this.width;
    }

    /* renamed from: component12, reason: from getter */
    public final File getFile() {
        return this.file;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCategoryId() {
        return this.categoryId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCategoryName() {
        return this.categoryName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    /* renamed from: component5, reason: from getter */
    public final String getFileID() {
        return this.fileID;
    }

    /* renamed from: component6, reason: from getter */
    public final String getFormat() {
        return this.format;
    }

    /* renamed from: component7, reason: from getter */
    public final String getHeight() {
        return this.height;
    }

    /* renamed from: component8, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component9, reason: from getter */
    public final String getSort() {
        return this.sort;
    }

    public final Record copy(String appId, String categoryId, String categoryName, String filePath, String fileID, String format, String height, String label, String sort, String type, String width, File file) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(fileID, "fileID");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(height, "height");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(width, "width");
        return new Record(appId, categoryId, categoryName, filePath, fileID, format, height, label, sort, type, width, file);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Record)) {
            return false;
        }
        Record record = (Record) other;
        return Intrinsics.areEqual(this.appId, record.appId) && Intrinsics.areEqual(this.categoryId, record.categoryId) && Intrinsics.areEqual(this.categoryName, record.categoryName) && Intrinsics.areEqual(this.filePath, record.filePath) && Intrinsics.areEqual(this.fileID, record.fileID) && Intrinsics.areEqual(this.format, record.format) && Intrinsics.areEqual(this.height, record.height) && Intrinsics.areEqual(this.label, record.label) && Intrinsics.areEqual(this.sort, record.sort) && Intrinsics.areEqual(this.type, record.type) && Intrinsics.areEqual(this.width, record.width) && Intrinsics.areEqual(this.file, record.file);
    }

    public int hashCode() {
        int hashCode = ((((((((((((this.appId.hashCode() * 31) + this.categoryId.hashCode()) * 31) + this.categoryName.hashCode()) * 31) + this.filePath.hashCode()) * 31) + this.fileID.hashCode()) * 31) + this.format.hashCode()) * 31) + this.height.hashCode()) * 31;
        String str = this.label;
        int hashCode2 = (((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.sort.hashCode()) * 31) + this.type.hashCode()) * 31) + this.width.hashCode()) * 31;
        File file = this.file;
        return hashCode2 + (file != null ? file.hashCode() : 0);
    }

    public String toString() {
        return "Record(appId=" + this.appId + ", categoryId=" + this.categoryId + ", categoryName=" + this.categoryName + ", filePath=" + this.filePath + ", fileID=" + this.fileID + ", format=" + this.format + ", height=" + this.height + ", label=" + this.label + ", sort=" + this.sort + ", type=" + this.type + ", width=" + this.width + ", file=" + this.file + ")";
    }

    public Record(String appId, String categoryId, String categoryName, String filePath, String fileID, String format, String height, String str, String sort, String type, String width, File file) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(fileID, "fileID");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(height, "height");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(width, "width");
        this.appId = appId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.filePath = filePath;
        this.fileID = fileID;
        this.format = format;
        this.height = height;
        this.label = str;
        this.sort = sort;
        this.type = type;
        this.width = width;
        this.file = file;
    }

    public /* synthetic */ Record(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, File file, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, (i & 2048) != 0 ? null : file);
    }

    public final String getAppId() {
        return this.appId;
    }

    public final String getCategoryId() {
        return this.categoryId;
    }

    public final String getCategoryName() {
        return this.categoryName;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final String getFileID() {
        return this.fileID;
    }

    public final String getFormat() {
        return this.format;
    }

    public final String getHeight() {
        return this.height;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getSort() {
        return this.sort;
    }

    public final String getType() {
        return this.type;
    }

    public final String getWidth() {
        return this.width;
    }

    public final File getFile() {
        return this.file;
    }

    public final void setFile(File file) {
        this.file = file;
    }
}
