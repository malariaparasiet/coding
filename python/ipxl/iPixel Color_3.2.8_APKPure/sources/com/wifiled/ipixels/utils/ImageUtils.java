package com.wifiled.ipixels.utils;

import android.graphics.ImageFormat;
import android.media.Image;
import android.util.Base64;
import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class ImageUtils {
    public static final int NV21 = 2;
    private static final String TAG = "ImageUtil";
    public static final int YUV420P = 0;
    public static final int YUV420SP = 1;

    public static byte[] getBytesFromImageAsType(Image image, int type) {
        Image.Plane[] planeArr;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        try {
            Image.Plane[] planes = image.getPlanes();
            int width = image.getWidth();
            int height = image.getHeight();
            int i6 = width * height;
            byte[] bArr = new byte[(ImageFormat.getBitsPerPixel(35) * i6) / 8];
            int i7 = i6 / 4;
            byte[] bArr2 = new byte[i7];
            int i8 = i6 / 4;
            byte[] bArr3 = new byte[i8];
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            while (i9 < planes.length) {
                int pixelStride = planes[i9].getPixelStride();
                int rowStride = planes[i9].getRowStride();
                ByteBuffer buffer = planes[i9].getBuffer();
                byte[] bArr4 = new byte[buffer.capacity()];
                buffer.get(bArr4);
                if (i9 == 0) {
                    int i13 = 0;
                    for (int i14 = 0; i14 < height; i14++) {
                        System.arraycopy(bArr4, i13, bArr, i10, width);
                        i13 += rowStride;
                        i10 += width;
                    }
                    planeArr = planes;
                } else if (i9 == 1) {
                    int i15 = 0;
                    int i16 = 0;
                    while (true) {
                        planeArr = planes;
                        if (i15 >= height / 2) {
                            break;
                        }
                        int i17 = 0;
                        while (true) {
                            i4 = width;
                            if (i17 >= i4 / 2) {
                                break;
                            }
                            bArr2[i11] = bArr4[i16];
                            i16 += pixelStride;
                            i17++;
                            i11++;
                            width = i4;
                        }
                        if (pixelStride == 2) {
                            i5 = rowStride - i4;
                        } else if (pixelStride == 1) {
                            i5 = rowStride - (i4 / 2);
                        } else {
                            i15++;
                            planes = planeArr;
                            width = i4;
                        }
                        i16 += i5;
                        i15++;
                        planes = planeArr;
                        width = i4;
                    }
                } else {
                    planeArr = planes;
                    i = width;
                    if (i9 == 2) {
                        int i18 = 0;
                        int i19 = 0;
                        while (i18 < height / 2) {
                            int i20 = 0;
                            while (true) {
                                i2 = i18;
                                if (i20 >= i / 2) {
                                    break;
                                }
                                bArr3[i12] = bArr4[i19];
                                i19 += pixelStride;
                                i20++;
                                i12++;
                                i18 = i2;
                            }
                            if (pixelStride == 2) {
                                i3 = rowStride - i;
                            } else if (pixelStride == 1) {
                                i3 = rowStride - (i / 2);
                            } else {
                                i18 = i2 + 1;
                            }
                            i19 += i3;
                            i18 = i2 + 1;
                        }
                    }
                    i9++;
                    planes = planeArr;
                    width = i;
                }
                i = width;
                i9++;
                planes = planeArr;
                width = i;
            }
            if (type == 0) {
                System.arraycopy(bArr2, 0, bArr, i10, i7);
                System.arraycopy(bArr3, 0, bArr, i10 + i7, i8);
                return bArr;
            }
            if (type == 1) {
                for (int i21 = 0; i21 < i8; i21++) {
                    int i22 = i10 + 1;
                    bArr[i10] = bArr2[i21];
                    i10 += 2;
                    bArr[i22] = bArr3[i21];
                }
            } else if (type == 2) {
                for (int i23 = 0; i23 < i8; i23++) {
                    int i24 = i10 + 1;
                    bArr[i10] = bArr3[i23];
                    i10 += 2;
                    bArr[i24] = bArr2[i23];
                }
            }
            return bArr;
        } catch (Exception unused) {
            if (image == null) {
                return null;
            }
            image.close();
            return null;
        }
    }

    public static int[] decodeYUV420SP(byte[] yuv420sp, int width, int height) {
        int i = width * height;
        int[] iArr = new int[i];
        int i2 = 0;
        for (int i3 = 0; i3 < height; i3++) {
            int i4 = ((i3 >> 1) * width) + i;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (i5 < width) {
                int i8 = (yuv420sp[i2] & UByte.MAX_VALUE) - 16;
                if (i8 < 0) {
                    i8 = 0;
                }
                if ((i5 & 1) == 0) {
                    int i9 = i4 + 1;
                    i7 = (yuv420sp[i4] & UByte.MAX_VALUE) - 128;
                    i4 += 2;
                    i6 = (yuv420sp[i9] & UByte.MAX_VALUE) - 128;
                }
                int i10 = i8 * 1192;
                int i11 = (i7 * 1634) + i10;
                int i12 = (i10 - (i7 * 833)) - (i6 * 400);
                int i13 = i10 + (i6 * 2066);
                if (i11 < 0) {
                    i11 = 0;
                } else if (i11 > 262143) {
                    i11 = 262143;
                }
                if (i12 < 0) {
                    i12 = 0;
                } else if (i12 > 262143) {
                    i12 = 262143;
                }
                if (i13 < 0) {
                    i13 = 0;
                } else if (i13 > 262143) {
                    i13 = 262143;
                }
                iArr[i2] = ((i13 >> 10) & 255) | ((i11 << 6) & 16711680) | ViewCompat.MEASURED_STATE_MASK | ((i12 >> 2) & 65280);
                i5++;
                i2++;
            }
        }
        return iArr;
    }

    public static byte[] decodeImage(String base64Data) {
        return Base64.decode(removeHeadAndTail(base64Data), 0);
    }

    private static String removeHeadAndTail(String base64Data) {
        return (base64Data == null || base64Data.length() <= 64) ? base64Data : base64Data.substring(32, base64Data.length() - 32);
    }
}
