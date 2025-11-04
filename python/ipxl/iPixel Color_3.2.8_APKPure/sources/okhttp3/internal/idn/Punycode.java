package okhttp3.internal.idn;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okio.Buffer;
import okio.ByteString;

/* compiled from: Punycode.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0005J(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0005J(\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J \u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u0017H\u0002J\u001c\u0010\"\u001a\u00020\u0017*\u00020\u00052\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002J\"\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0$*\u00020\u00052\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010%\u001a\u00020\r*\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006("}, d2 = {"Lokhttp3/internal/idn/Punycode;", "", "<init>", "()V", "PREFIX_STRING", "", "getPREFIX_STRING", "()Ljava/lang/String;", "PREFIX", "Lokio/ByteString;", "getPREFIX", "()Lokio/ByteString;", "BASE", "", "TMIN", "TMAX", "SKEW", "DAMP", "INITIAL_BIAS", "INITIAL_N", "encode", TypedValues.Custom.S_STRING, "encodeLabel", "", "pos", "limit", "result", "Lokio/Buffer;", "decode", "decodeLabel", "adapt", "delta", "numpoints", "first", "requiresEncode", "codePoints", "", "punycodeDigit", "getPunycodeDigit", "(I)I", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Punycode {
    private static final int BASE = 36;
    private static final int DAMP = 700;
    private static final int INITIAL_BIAS = 72;
    private static final int INITIAL_N = 128;
    private static final int SKEW = 38;
    private static final int TMAX = 26;
    private static final int TMIN = 1;
    public static final Punycode INSTANCE = new Punycode();
    private static final String PREFIX_STRING = "xn--";
    private static final ByteString PREFIX = ByteString.INSTANCE.encodeUtf8("xn--");

    private Punycode() {
    }

    public final String getPREFIX_STRING() {
        return PREFIX_STRING;
    }

    public final ByteString getPREFIX() {
        return PREFIX;
    }

    public final String encode(String string) {
        int indexOf$default;
        Intrinsics.checkNotNullParameter(string, "string");
        int length = string.length();
        Buffer buffer = new Buffer();
        for (int i = 0; i < length; i = indexOf$default + 1) {
            indexOf$default = StringsKt.indexOf$default((CharSequence) string, '.', i, false, 4, (Object) null);
            if (indexOf$default == -1) {
                indexOf$default = length;
            }
            if (!encodeLabel(string, i, indexOf$default, buffer)) {
                return null;
            }
            if (indexOf$default >= length) {
                break;
            }
            buffer.writeByte(46);
        }
        return buffer.readUtf8();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean encodeLabel(String string, int pos, int limit, Buffer result) {
        int i;
        int i2;
        int i3;
        int i4 = 1;
        if (!requiresEncode(string, pos, limit)) {
            result.writeUtf8(string, pos, limit);
            return true;
        }
        result.write(PREFIX);
        List<Integer> codePoints = codePoints(string, pos, limit);
        Iterator<Integer> it = codePoints.iterator();
        int i5 = 0;
        while (true) {
            i = 128;
            if (!it.hasNext()) {
                break;
            }
            int intValue = it.next().intValue();
            if (intValue < 128) {
                result.writeByte(intValue);
                i5++;
            }
        }
        if (i5 > 0) {
            result.writeByte(45);
        }
        int i6 = 72;
        int i7 = 0;
        int i8 = i5;
        while (i8 < codePoints.size()) {
            Iterator<T> it2 = codePoints.iterator();
            if (!it2.hasNext()) {
                throw new NoSuchElementException();
            }
            Object next = it2.next();
            if (it2.hasNext()) {
                int intValue2 = ((Number) next).intValue();
                if (intValue2 < i) {
                    intValue2 = Integer.MAX_VALUE;
                }
                do {
                    Object next2 = it2.next();
                    int intValue3 = ((Number) next2).intValue();
                    if (intValue3 < i) {
                        intValue3 = Integer.MAX_VALUE;
                    }
                    if (intValue2 > intValue3) {
                        next = next2;
                        intValue2 = intValue3;
                    }
                } while (it2.hasNext());
            }
            int intValue4 = ((Number) next).intValue();
            int i9 = (intValue4 - i) * (i8 + 1);
            if (i7 > Integer.MAX_VALUE - i9) {
                return false;
            }
            int i10 = i7 + i9;
            Iterator<Integer> it3 = codePoints.iterator();
            while (it3.hasNext()) {
                int intValue5 = it3.next().intValue();
                if (intValue5 < intValue4) {
                    if (i10 == Integer.MAX_VALUE) {
                        return false;
                    }
                    i10++;
                } else if (intValue5 == intValue4) {
                    IntProgression step = RangesKt.step(RangesKt.until(36, Integer.MAX_VALUE), 36);
                    int first = step.getFirst();
                    int last = step.getLast();
                    int step2 = step.getStep();
                    if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                        i3 = i10;
                        while (true) {
                            if (first <= i6) {
                                i2 = i4;
                            } else {
                                i2 = i4;
                                i4 = first >= i6 + 26 ? 26 : first - i6;
                            }
                            if (i3 < i4) {
                                break;
                            }
                            int i11 = i3 - i4;
                            int i12 = 36 - i4;
                            result.writeByte(getPunycodeDigit(i4 + (i11 % i12)));
                            i3 = i11 / i12;
                            if (first == last) {
                                break;
                            }
                            first += step2;
                            i4 = i2;
                        }
                    } else {
                        i2 = i4;
                        i3 = i10;
                    }
                    result.writeByte(getPunycodeDigit(i3));
                    int i13 = i8 + 1;
                    i6 = adapt(i10, i13, i8 == i5 ? i2 : false);
                    i8 = i13;
                    i10 = 0;
                    i4 = i2;
                }
            }
            i7 = i10 + 1;
            i = intValue4 + 1;
        }
        return i4;
    }

    public final String decode(String string) {
        int indexOf$default;
        Intrinsics.checkNotNullParameter(string, "string");
        int length = string.length();
        Buffer buffer = new Buffer();
        for (int i = 0; i < length; i = indexOf$default + 1) {
            indexOf$default = StringsKt.indexOf$default((CharSequence) string, '.', i, false, 4, (Object) null);
            if (indexOf$default == -1) {
                indexOf$default = length;
            }
            if (!decodeLabel(string, i, indexOf$default, buffer)) {
                return null;
            }
            if (indexOf$default >= length) {
                break;
            }
            buffer.writeByte(46);
        }
        return buffer.readUtf8();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean decodeLabel(String string, int pos, int limit, Buffer result) {
        int i;
        int i2;
        int i3 = 1;
        if (!StringsKt.regionMatches(string, pos, PREFIX_STRING, 0, 4, true)) {
            result.writeUtf8(string, pos, limit);
            return true;
        }
        int i4 = pos + 4;
        ArrayList arrayList = new ArrayList();
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) string, '-', limit, false, 4, (Object) null);
        char c = '0';
        char c2 = '[';
        char c3 = '{';
        boolean z = false;
        if (lastIndexOf$default >= i4) {
            while (i4 < lastIndexOf$default) {
                int i5 = i4 + 1;
                char charAt = string.charAt(i4);
                if (('a' > charAt || charAt >= '{') && (('A' > charAt || charAt >= '[') && (('0' > charAt || charAt >= ':') && charAt != '-'))) {
                    return false;
                }
                arrayList.add(Integer.valueOf(charAt));
                i4 = i5;
            }
            i4++;
        }
        int i6 = 128;
        int i7 = 72;
        int i8 = 0;
        while (i4 < limit) {
            int i9 = i3;
            boolean z2 = z;
            IntProgression step = RangesKt.step(RangesKt.until(36, Integer.MAX_VALUE), 36);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                i = i8;
                int i10 = i9;
                while (i4 != limit) {
                    int i11 = i4 + 1;
                    char charAt2 = string.charAt(i4);
                    if ('a' <= charAt2 && charAt2 < c3) {
                        i2 = charAt2 - 'a';
                    } else if ('A' <= charAt2 && charAt2 < c2) {
                        i2 = charAt2 - 'A';
                    } else {
                        if (c > charAt2 || charAt2 >= ':') {
                            return z2;
                        }
                        i2 = charAt2 - 22;
                    }
                    int i12 = i10;
                    int i13 = i2 * i12;
                    int i14 = i;
                    if (i14 > Integer.MAX_VALUE - i13) {
                        return z2;
                    }
                    i = i14 + i13;
                    int i15 = first <= i7 ? i9 : first >= i7 + 26 ? 26 : first - i7;
                    if (i2 >= i15) {
                        int i16 = 36 - i15;
                        if (i12 > Integer.MAX_VALUE / i16) {
                            return z2;
                        }
                        i10 = i12 * i16;
                        if (first != last) {
                            first += step2;
                            i4 = i11;
                            c = '0';
                            c2 = '[';
                            c3 = '{';
                        }
                    }
                    i4 = i11;
                }
                return z2;
            }
            i = i8;
            i7 = adapt(i - i8, arrayList.size() + 1, i8 == 0 ? i9 : z2);
            int size = i / (arrayList.size() + 1);
            if (i6 > Integer.MAX_VALUE - size) {
                return z2;
            }
            i6 += size;
            int size2 = i % (arrayList.size() + 1);
            if (i6 > 1114111) {
                return z2;
            }
            arrayList.add(size2, Integer.valueOf(i6));
            i8 = size2 + 1;
            z = z2;
            i3 = i9;
            c = '0';
            c2 = '[';
            c3 = '{';
        }
        boolean z3 = i3;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            result.writeUtf8CodePoint(((Number) it.next()).intValue());
        }
        return z3;
    }

    private final int adapt(int delta, int numpoints, boolean first) {
        int i;
        if (first) {
            i = delta / 700;
        } else {
            i = delta / 2;
        }
        int i2 = i + (i / numpoints);
        int i3 = 0;
        while (i2 > 455) {
            i2 /= 35;
            i3 += 36;
        }
        return i3 + ((i2 * 36) / (i2 + 38));
    }

    private final boolean requiresEncode(String str, int i, int i2) {
        while (i < i2) {
            if (str.charAt(i) >= 128) {
                return true;
            }
            i++;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [char] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    private final List<Integer> codePoints(String str, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        while (i < i2) {
            int charAt = str.charAt(i);
            ArrayList arrayList2 = arrayList;
            if (CharsKt.isSurrogate(charAt)) {
                int i3 = i + 1;
                char charAt2 = i3 < i2 ? str.charAt(i3) : (char) 0;
                if (Character.isLowSurrogate(charAt) || !Character.isLowSurrogate(charAt2)) {
                    charAt = 63;
                } else {
                    charAt = 65536 + (((charAt & 1023) << 10) | (charAt2 & 1023));
                    i = i3;
                }
            }
            arrayList2.add(Integer.valueOf(charAt));
            i++;
        }
        return arrayList;
    }

    private final int getPunycodeDigit(int i) {
        if (i < 26) {
            return i + 97;
        }
        if (i < 36) {
            return i + 22;
        }
        throw new IllegalStateException(("unexpected digit: " + i).toString());
    }
}
