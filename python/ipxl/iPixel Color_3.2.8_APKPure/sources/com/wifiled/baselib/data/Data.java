package com.wifiled.baselib.data;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Data.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003JA\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\tHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013¨\u0006 "}, d2 = {"Lcom/wifiled/baselib/data/Data;", "", "pageNo", "", "pageSize", "records", "", "Lcom/wifiled/baselib/data/Record;", "totalCount", "", "totalPage", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;II)V", "getPageNo", "()Ljava/lang/String;", "getPageSize", "getRecords", "()Ljava/util/List;", "getTotalCount", "()I", "getTotalPage", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Data {

    @SerializedName("pageNo")
    private final String pageNo;

    @SerializedName("pageSize")
    private final String pageSize;

    @SerializedName("records")
    private final List<Record> records;

    @SerializedName("totalCount")
    private final int totalCount;

    @SerializedName("totalPage")
    private final int totalPage;

    public static /* synthetic */ Data copy$default(Data data, String str, String str2, List list, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = data.pageNo;
        }
        if ((i3 & 2) != 0) {
            str2 = data.pageSize;
        }
        if ((i3 & 4) != 0) {
            list = data.records;
        }
        if ((i3 & 8) != 0) {
            i = data.totalCount;
        }
        if ((i3 & 16) != 0) {
            i2 = data.totalPage;
        }
        int i4 = i2;
        List list2 = list;
        return data.copy(str, str2, list2, i, i4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPageNo() {
        return this.pageNo;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPageSize() {
        return this.pageSize;
    }

    public final List<Record> component3() {
        return this.records;
    }

    /* renamed from: component4, reason: from getter */
    public final int getTotalCount() {
        return this.totalCount;
    }

    /* renamed from: component5, reason: from getter */
    public final int getTotalPage() {
        return this.totalPage;
    }

    public final Data copy(String pageNo, String pageSize, List<Record> records, int totalCount, int totalPage) {
        Intrinsics.checkNotNullParameter(pageNo, "pageNo");
        Intrinsics.checkNotNullParameter(pageSize, "pageSize");
        Intrinsics.checkNotNullParameter(records, "records");
        return new Data(pageNo, pageSize, records, totalCount, totalPage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Data)) {
            return false;
        }
        Data data = (Data) other;
        return Intrinsics.areEqual(this.pageNo, data.pageNo) && Intrinsics.areEqual(this.pageSize, data.pageSize) && Intrinsics.areEqual(this.records, data.records) && this.totalCount == data.totalCount && this.totalPage == data.totalPage;
    }

    public int hashCode() {
        return (((((((this.pageNo.hashCode() * 31) + this.pageSize.hashCode()) * 31) + this.records.hashCode()) * 31) + Integer.hashCode(this.totalCount)) * 31) + Integer.hashCode(this.totalPage);
    }

    public String toString() {
        return "Data(pageNo=" + this.pageNo + ", pageSize=" + this.pageSize + ", records=" + this.records + ", totalCount=" + this.totalCount + ", totalPage=" + this.totalPage + ")";
    }

    public Data(String pageNo, String pageSize, List<Record> records, int i, int i2) {
        Intrinsics.checkNotNullParameter(pageNo, "pageNo");
        Intrinsics.checkNotNullParameter(pageSize, "pageSize");
        Intrinsics.checkNotNullParameter(records, "records");
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.records = records;
        this.totalCount = i;
        this.totalPage = i2;
    }

    public final String getPageNo() {
        return this.pageNo;
    }

    public final String getPageSize() {
        return this.pageSize;
    }

    public final List<Record> getRecords() {
        return this.records;
    }

    public final int getTotalCount() {
        return this.totalCount;
    }

    public final int getTotalPage() {
        return this.totalPage;
    }
}
