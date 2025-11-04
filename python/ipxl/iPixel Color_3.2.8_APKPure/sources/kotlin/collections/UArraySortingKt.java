package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UArraySorting.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\r\u0010\u000e\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0014\u0010\u0015\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001e\u0010\u000b\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001f\u0010\u0010\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b \u0010\u0015\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b!\u0010\u001a¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort-Aa5vz7o", "([SII)V", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "quickSort-oBK06Vg", "([III)V", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "quickSort--nroSd4", "([JII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-Aa5vz7o", "sortArray-oBK06Vg", "sortArray--nroSd4", "kotlin-stdlib"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m4027partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte m3643getw2LRezQ = UByteArray.m3643getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m3643getw2LRezQ2 = UByteArray.m3643getw2LRezQ(bArr, i) & UByte.MAX_VALUE;
                i3 = m3643getw2LRezQ & UByte.MAX_VALUE;
                if (Intrinsics.compare(m3643getw2LRezQ2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m3643getw2LRezQ(bArr, i2) & UByte.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte m3643getw2LRezQ3 = UByteArray.m3643getw2LRezQ(bArr, i);
                UByteArray.m3648setVurrAj0(bArr, i, UByteArray.m3643getw2LRezQ(bArr, i2));
                UByteArray.m3648setVurrAj0(bArr, i2, m3643getw2LRezQ3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m4031quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int m4027partition4UcCI2c = m4027partition4UcCI2c(bArr, i, i2);
        int i3 = m4027partition4UcCI2c - 1;
        if (i < i3) {
            m4031quickSort4UcCI2c(bArr, i, i3);
        }
        if (m4027partition4UcCI2c < i2) {
            m4031quickSort4UcCI2c(bArr, m4027partition4UcCI2c, i2);
        }
    }

    /* renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m4028partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short m3906getMh2AYeg = UShortArray.m3906getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = m3906getMh2AYeg & 65535;
                if (Intrinsics.compare(UShortArray.m3906getMh2AYeg(sArr, i) & 65535, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m3906getMh2AYeg(sArr, i2) & 65535, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short m3906getMh2AYeg2 = UShortArray.m3906getMh2AYeg(sArr, i);
                UShortArray.m3911set01HTLdE(sArr, i, UShortArray.m3906getMh2AYeg(sArr, i2));
                UShortArray.m3911set01HTLdE(sArr, i2, m3906getMh2AYeg2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m4032quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int m4028partitionAa5vz7o = m4028partitionAa5vz7o(sArr, i, i2);
        int i3 = m4028partitionAa5vz7o - 1;
        if (i < i3) {
            m4032quickSortAa5vz7o(sArr, i, i3);
        }
        if (m4028partitionAa5vz7o < i2) {
            m4032quickSortAa5vz7o(sArr, m4028partitionAa5vz7o, i2);
        }
    }

    /* renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m4029partitionoBK06Vg(int[] iArr, int i, int i2) {
        int m3722getpVg5ArA = UIntArray.m3722getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (Integer.compareUnsigned(UIntArray.m3722getpVg5ArA(iArr, i), m3722getpVg5ArA) < 0) {
                i++;
            }
            while (Integer.compareUnsigned(UIntArray.m3722getpVg5ArA(iArr, i2), m3722getpVg5ArA) > 0) {
                i2--;
            }
            if (i <= i2) {
                int m3722getpVg5ArA2 = UIntArray.m3722getpVg5ArA(iArr, i);
                UIntArray.m3727setVXSXFK8(iArr, i, UIntArray.m3722getpVg5ArA(iArr, i2));
                UIntArray.m3727setVXSXFK8(iArr, i2, m3722getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m4033quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int m4029partitionoBK06Vg = m4029partitionoBK06Vg(iArr, i, i2);
        int i3 = m4029partitionoBK06Vg - 1;
        if (i < i3) {
            m4033quickSortoBK06Vg(iArr, i, i3);
        }
        if (m4029partitionoBK06Vg < i2) {
            m4033quickSortoBK06Vg(iArr, m4029partitionoBK06Vg, i2);
        }
    }

    /* renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m4026partitionnroSd4(long[] jArr, int i, int i2) {
        long m3801getsVKNKU = ULongArray.m3801getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (Long.compareUnsigned(ULongArray.m3801getsVKNKU(jArr, i), m3801getsVKNKU) < 0) {
                i++;
            }
            while (Long.compareUnsigned(ULongArray.m3801getsVKNKU(jArr, i2), m3801getsVKNKU) > 0) {
                i2--;
            }
            if (i <= i2) {
                long m3801getsVKNKU2 = ULongArray.m3801getsVKNKU(jArr, i);
                ULongArray.m3806setk8EXiF4(jArr, i, ULongArray.m3801getsVKNKU(jArr, i2));
                ULongArray.m3806setk8EXiF4(jArr, i2, m3801getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m4030quickSortnroSd4(long[] jArr, int i, int i2) {
        int m4026partitionnroSd4 = m4026partitionnroSd4(jArr, i, i2);
        int i3 = m4026partitionnroSd4 - 1;
        if (i < i3) {
            m4030quickSortnroSd4(jArr, i, i3);
        }
        if (m4026partitionnroSd4 < i2) {
            m4030quickSortnroSd4(jArr, m4026partitionnroSd4, i2);
        }
    }

    /* renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m4035sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m4031quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m4036sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m4032quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m4037sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m4033quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m4034sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m4030quickSortnroSd4(array, i, i2 - 1);
    }
}
