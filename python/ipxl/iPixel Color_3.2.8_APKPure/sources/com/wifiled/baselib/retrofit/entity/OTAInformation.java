package com.wifiled.baselib.retrofit.entity;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OTAInformation.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010JV\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0014\u0010\u0010¨\u0006#"}, d2 = {"Lcom/wifiled/baselib/retrofit/entity/OTAInformation;", "Ljava/io/Serializable;", "desc_cn", "", "desc_en", "need_update", "", "version_no", "file_path", "force", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getDesc_cn", "()Ljava/lang/String;", "getDesc_en", "getNeed_update", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getVersion_no", "getFile_path", "getForce", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/wifiled/baselib/retrofit/entity/OTAInformation;", "equals", "other", "", "hashCode", "", "toString", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class OTAInformation implements Serializable {
    private final String desc_cn;
    private final String desc_en;
    private final String file_path;
    private final Boolean force;
    private final Boolean need_update;
    private final String version_no;

    public static /* synthetic */ OTAInformation copy$default(OTAInformation oTAInformation, String str, String str2, Boolean bool, String str3, String str4, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = oTAInformation.desc_cn;
        }
        if ((i & 2) != 0) {
            str2 = oTAInformation.desc_en;
        }
        if ((i & 4) != 0) {
            bool = oTAInformation.need_update;
        }
        if ((i & 8) != 0) {
            str3 = oTAInformation.version_no;
        }
        if ((i & 16) != 0) {
            str4 = oTAInformation.file_path;
        }
        if ((i & 32) != 0) {
            bool2 = oTAInformation.force;
        }
        String str5 = str4;
        Boolean bool3 = bool2;
        return oTAInformation.copy(str, str2, bool, str3, str5, bool3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDesc_cn() {
        return this.desc_cn;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDesc_en() {
        return this.desc_en;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getNeed_update() {
        return this.need_update;
    }

    /* renamed from: component4, reason: from getter */
    public final String getVersion_no() {
        return this.version_no;
    }

    /* renamed from: component5, reason: from getter */
    public final String getFile_path() {
        return this.file_path;
    }

    /* renamed from: component6, reason: from getter */
    public final Boolean getForce() {
        return this.force;
    }

    public final OTAInformation copy(String desc_cn, String desc_en, Boolean need_update, String version_no, String file_path, Boolean force) {
        return new OTAInformation(desc_cn, desc_en, need_update, version_no, file_path, force);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OTAInformation)) {
            return false;
        }
        OTAInformation oTAInformation = (OTAInformation) other;
        return Intrinsics.areEqual(this.desc_cn, oTAInformation.desc_cn) && Intrinsics.areEqual(this.desc_en, oTAInformation.desc_en) && Intrinsics.areEqual(this.need_update, oTAInformation.need_update) && Intrinsics.areEqual(this.version_no, oTAInformation.version_no) && Intrinsics.areEqual(this.file_path, oTAInformation.file_path) && Intrinsics.areEqual(this.force, oTAInformation.force);
    }

    public int hashCode() {
        String str = this.desc_cn;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.desc_en;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.need_update;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str3 = this.version_no;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.file_path;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Boolean bool2 = this.force;
        return hashCode5 + (bool2 != null ? bool2.hashCode() : 0);
    }

    public String toString() {
        return "OTAInformation(desc_cn=" + this.desc_cn + ", desc_en=" + this.desc_en + ", need_update=" + this.need_update + ", version_no=" + this.version_no + ", file_path=" + this.file_path + ", force=" + this.force + ")";
    }

    public OTAInformation(String str, String str2, Boolean bool, String str3, String str4, Boolean bool2) {
        this.desc_cn = str;
        this.desc_en = str2;
        this.need_update = bool;
        this.version_no = str3;
        this.file_path = str4;
        this.force = bool2;
    }

    public final String getDesc_cn() {
        return this.desc_cn;
    }

    public final String getDesc_en() {
        return this.desc_en;
    }

    public final Boolean getNeed_update() {
        return this.need_update;
    }

    public final String getVersion_no() {
        return this.version_no;
    }

    public final String getFile_path() {
        return this.file_path;
    }

    public final Boolean getForce() {
        return this.force;
    }
}
