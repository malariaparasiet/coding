package com.wifiled.ipixels.core;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: AdvertisementParser.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/wifiled/ipixels/core/AdvertisementParser;", "", "<init>", "()V", "parseAdvertisement", "Lcom/wifiled/ipixels/core/AdvertisementParser$ParsedAdvertisement;", "data", "", "ParsedAdvertisement", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AdvertisementParser {

    /* compiled from: AdvertisementParser.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0003H\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003J>\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001cJ\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0010\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/wifiled/ipixels/core/AdvertisementParser$ParsedAdvertisement;", "", "flags", "", "completeLocalName", "", "manufacturerId", "manufacturerData", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;[B)V", "getFlags", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCompleteLocalName", "()Ljava/lang/String;", "getManufacturerId", "getManufacturerData", "()[B", "equals", "", "other", "hashCode", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;[B)Lcom/wifiled/ipixels/core/AdvertisementParser$ParsedAdvertisement;", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ParsedAdvertisement {
        private final String completeLocalName;
        private final Integer flags;
        private final byte[] manufacturerData;
        private final Integer manufacturerId;

        public ParsedAdvertisement() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ ParsedAdvertisement copy$default(ParsedAdvertisement parsedAdvertisement, Integer num, String str, Integer num2, byte[] bArr, int i, Object obj) {
            if ((i & 1) != 0) {
                num = parsedAdvertisement.flags;
            }
            if ((i & 2) != 0) {
                str = parsedAdvertisement.completeLocalName;
            }
            if ((i & 4) != 0) {
                num2 = parsedAdvertisement.manufacturerId;
            }
            if ((i & 8) != 0) {
                bArr = parsedAdvertisement.manufacturerData;
            }
            return parsedAdvertisement.copy(num, str, num2, bArr);
        }

        /* renamed from: component1, reason: from getter */
        public final Integer getFlags() {
            return this.flags;
        }

        /* renamed from: component2, reason: from getter */
        public final String getCompleteLocalName() {
            return this.completeLocalName;
        }

        /* renamed from: component3, reason: from getter */
        public final Integer getManufacturerId() {
            return this.manufacturerId;
        }

        /* renamed from: component4, reason: from getter */
        public final byte[] getManufacturerData() {
            return this.manufacturerData;
        }

        public final ParsedAdvertisement copy(Integer flags, String completeLocalName, Integer manufacturerId, byte[] manufacturerData) {
            return new ParsedAdvertisement(flags, completeLocalName, manufacturerId, manufacturerData);
        }

        public String toString() {
            return "ParsedAdvertisement(flags=" + this.flags + ", completeLocalName=" + this.completeLocalName + ", manufacturerId=" + this.manufacturerId + ", manufacturerData=" + Arrays.toString(this.manufacturerData) + ")";
        }

        public ParsedAdvertisement(Integer num, String str, Integer num2, byte[] bArr) {
            this.flags = num;
            this.completeLocalName = str;
            this.manufacturerId = num2;
            this.manufacturerData = bArr;
        }

        public /* synthetic */ ParsedAdvertisement(Integer num, String str, Integer num2, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : bArr);
        }

        public final Integer getFlags() {
            return this.flags;
        }

        public final String getCompleteLocalName() {
            return this.completeLocalName;
        }

        public final Integer getManufacturerId() {
            return this.manufacturerId;
        }

        public final byte[] getManufacturerData() {
            return this.manufacturerData;
        }

        public boolean equals(Object other) {
            byte[] bArr;
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
                return false;
            }
            Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.wifiled.ipixels.core.AdvertisementParser.ParsedAdvertisement");
            ParsedAdvertisement parsedAdvertisement = (ParsedAdvertisement) other;
            return Intrinsics.areEqual(this.flags, parsedAdvertisement.flags) && Intrinsics.areEqual(this.completeLocalName, parsedAdvertisement.completeLocalName) && Intrinsics.areEqual(this.manufacturerId, parsedAdvertisement.manufacturerId) && (bArr = this.manufacturerData) != null && Arrays.equals(bArr, parsedAdvertisement.manufacturerData);
        }

        public int hashCode() {
            Integer num = this.flags;
            int intValue = (num != null ? num.intValue() : 0) * 31;
            String str = this.completeLocalName;
            int hashCode = (intValue + (str != null ? str.hashCode() : 0)) * 31;
            Integer num2 = this.manufacturerId;
            int intValue2 = (hashCode + (num2 != null ? num2.intValue() : 0)) * 31;
            byte[] bArr = this.manufacturerData;
            return intValue2 + (bArr != null ? Arrays.hashCode(bArr) : 0);
        }
    }

    public final ParsedAdvertisement parseAdvertisement(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int i = 0;
        Integer num = null;
        String str = null;
        Integer num2 = null;
        byte[] bArr = null;
        while (i < data.length) {
            int i2 = data[i] & UByte.MAX_VALUE;
            int i3 = i + 1;
            if (i2 == 0) {
                break;
            }
            if ((i3 + i2) - 1 > data.length) {
                break;
            }
            int i4 = data[i3] & UByte.MAX_VALUE;
            int i5 = i + 2;
            int i6 = (i2 - 1) + i5;
            if (i6 > data.length) {
                break;
            }
            byte[] sliceArray = ArraysKt.sliceArray(data, RangesKt.until(i5, i6));
            if (i4 == 1) {
                if (!(sliceArray.length == 0)) {
                    num = Integer.valueOf(sliceArray[0] & UByte.MAX_VALUE);
                }
            } else if (i4 == 9) {
                try {
                    Charset UTF_8 = StandardCharsets.UTF_8;
                    Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
                    str = new String(sliceArray, UTF_8);
                } catch (Exception unused) {
                    str = null;
                }
            } else if (i4 == 255 && sliceArray.length >= 2) {
                num2 = Integer.valueOf(((sliceArray[0] & UByte.MAX_VALUE) << 8) | (sliceArray[1] & UByte.MAX_VALUE));
                bArr = ArraysKt.copyOfRange(sliceArray, 2, sliceArray.length);
            }
            i = i6;
        }
        return new ParsedAdvertisement(num, str, num2, bArr);
    }
}
