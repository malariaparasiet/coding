package androidx.compose.ui.input.pointer.util;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VelocityTracker.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u001a,\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\f\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"AssumePointerMoveStoppedMilliseconds", "", "DefaultWeight", "", "HistorySize", "HorizonMilliseconds", "MinSampleSize", "polyFitLeastSquares", "Landroidx/compose/ui/input/pointer/util/PolynomialFit;", "x", "", "y", "degree", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class VelocityTrackerKt {
    private static final int AssumePointerMoveStoppedMilliseconds = 40;
    private static final float DefaultWeight = 1.0f;
    private static final int HistorySize = 20;
    private static final int HorizonMilliseconds = 100;
    private static final int MinSampleSize = 3;

    public static final PolynomialFit polyFitLeastSquares(List<Float> x, List<Float> y, int i) {
        int i2;
        ArrayList arrayList;
        float f;
        float f2;
        Intrinsics.checkNotNullParameter(x, "x");
        Intrinsics.checkNotNullParameter(y, "y");
        if (i < 1) {
            throw new IllegalArgumentException("The degree must be at positive integer");
        }
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("x and y must be the same length");
        }
        if (x.isEmpty()) {
            throw new IllegalArgumentException("At least one point must be provided");
        }
        int size = i >= x.size() ? x.size() - 1 : i;
        int i3 = i + 1;
        ArrayList arrayList2 = new ArrayList(i3);
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            arrayList2.add(Float.valueOf(0.0f));
        }
        ArrayList arrayList3 = arrayList2;
        int size2 = x.size();
        int i6 = size + 1;
        Matrix matrix = new Matrix(i6, size2);
        float f3 = 1.0f;
        if (size2 > 0) {
            int i7 = 0;
            while (true) {
                int i8 = i7 + 1;
                matrix.set(0, i7, 1.0f);
                if (1 < i6) {
                    int i9 = 1;
                    while (true) {
                        int i10 = i9 + 1;
                        matrix.set(i9, i7, matrix.get(i9 - 1, i7) * x.get(i7).floatValue());
                        if (i10 >= i6) {
                            break;
                        }
                        i9 = i10;
                    }
                }
                if (i8 >= size2) {
                    break;
                }
                i7 = i8;
            }
        }
        Matrix matrix2 = new Matrix(i6, size2);
        Matrix matrix3 = new Matrix(i6, i6);
        if (i6 > 0) {
            int i11 = 0;
            while (true) {
                int i12 = i11 + 1;
                if (size2 > 0) {
                    int i13 = i4;
                    while (true) {
                        int i14 = i13 + 1;
                        f = f3;
                        matrix2.set(i11, i13, matrix.get(i11, i13));
                        if (i14 >= size2) {
                            break;
                        }
                        i13 = i14;
                        f3 = f;
                    }
                } else {
                    f = f3;
                }
                if (i11 > 0) {
                    int i15 = i4;
                    while (true) {
                        int i16 = i15 + 1;
                        float times = matrix2.getRow(i11).times(matrix2.getRow(i15));
                        if (size2 > 0) {
                            int i17 = i4;
                            while (true) {
                                int i18 = i17 + 1;
                                float f4 = times;
                                matrix2.set(i11, i17, matrix2.get(i11, i17) - (matrix2.get(i15, i17) * times));
                                if (i18 >= size2) {
                                    break;
                                }
                                i17 = i18;
                                times = f4;
                            }
                        }
                        if (i16 >= i11) {
                            break;
                        }
                        i15 = i16;
                        i4 = 0;
                    }
                }
                float norm = matrix2.getRow(i11).norm();
                i2 = size;
                arrayList = arrayList3;
                if (norm < 1.0E-6d) {
                    throw new IllegalArgumentException("Vectors are linearly dependent or zero so no solution. TODO(shepshapard), actually determine what this means");
                }
                float f5 = f / norm;
                if (size2 > 0) {
                    int i19 = 0;
                    while (true) {
                        int i20 = i19 + 1;
                        matrix2.set(i11, i19, matrix2.get(i11, i19) * f5);
                        if (i20 >= size2) {
                            break;
                        }
                        i19 = i20;
                    }
                }
                if (i6 > 0) {
                    int i21 = 0;
                    while (true) {
                        int i22 = i21 + 1;
                        matrix3.set(i11, i21, i21 < i11 ? 0.0f : matrix2.getRow(i11).times(matrix.getRow(i21)));
                        if (i22 >= i6) {
                            break;
                        }
                        i21 = i22;
                    }
                }
                if (i12 >= i6) {
                    break;
                }
                size = i2;
                arrayList3 = arrayList;
                i11 = i12;
                f3 = f;
                i4 = 0;
            }
        } else {
            i2 = size;
            arrayList = arrayList3;
            f = 1.0f;
        }
        Vector vector = new Vector(size2);
        if (size2 > 0) {
            int i23 = 0;
            while (true) {
                int i24 = i23 + 1;
                vector.set(i23, y.get(i23).floatValue() * f);
                if (i24 >= size2) {
                    break;
                }
                i23 = i24;
            }
        }
        if (i2 >= 0) {
            int i25 = i2;
            while (true) {
                int i26 = i25 - 1;
                arrayList.set(i25, Float.valueOf(matrix2.getRow(i25).times(vector)));
                int i27 = i25 + 1;
                if (i27 <= i2) {
                    int i28 = i2;
                    while (true) {
                        int i29 = i28 - 1;
                        arrayList.set(i25, Float.valueOf(((Number) arrayList.get(i25)).floatValue() - (matrix3.get(i25, i28) * ((Number) arrayList.get(i28)).floatValue())));
                        if (i28 == i27) {
                            break;
                        }
                        i28 = i29;
                    }
                }
                arrayList.set(i25, Float.valueOf(((Number) arrayList.get(i25)).floatValue() / matrix3.get(i25, i25)));
                if (i26 < 0) {
                    break;
                }
                i25 = i26;
            }
        }
        if (size2 > 0) {
            int i30 = 0;
            f2 = 0.0f;
            while (true) {
                int i31 = i30 + 1;
                f2 += y.get(i30).floatValue();
                if (i31 >= size2) {
                    break;
                }
                i30 = i31;
            }
        } else {
            f2 = 0.0f;
        }
        float f6 = f2 / size2;
        float f7 = 0.0f;
        float f8 = 0.0f;
        if (size2 > 0) {
            int i32 = 0;
            while (true) {
                int i33 = i32 + 1;
                float floatValue = y.get(i32).floatValue() - ((Number) arrayList.get(0)).floatValue();
                if (1 < i6) {
                    int i34 = 1;
                    float f9 = f;
                    while (true) {
                        int i35 = i34 + 1;
                        f9 *= x.get(i32).floatValue();
                        floatValue -= ((Number) arrayList.get(i34)).floatValue() * f9;
                        if (i35 >= i6) {
                            break;
                        }
                        i34 = i35;
                    }
                }
                f7 += floatValue * f * floatValue;
                float floatValue2 = y.get(i32).floatValue() - f6;
                f8 += floatValue2 * f * floatValue2;
                if (i33 >= size2) {
                    break;
                }
                i32 = i33;
            }
        }
        return new PolynomialFit(arrayList, f8 <= 1.0E-6f ? f : f - (f7 / f8));
    }
}
