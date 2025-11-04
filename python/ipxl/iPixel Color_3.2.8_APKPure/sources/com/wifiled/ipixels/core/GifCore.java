package com.wifiled.ipixels.core;

import android.graphics.Bitmap;
import com.squareup.gifencoder.Image;
import com.squareup.gifencoder.ImageOptions;
import com.wifiled.baselib.utils.FileUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.ui.imgtxt.SaveGifData;
import com.wifiled.ipixels.utils.BitmapUtils;
import com.wifiled.musiclib.player.constant.DbFinal;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import pl.droidsonroids.gif.GifDecoder;
import pl.droidsonroids.gif.GifIOException;
import pl.droidsonroids.gif.GifOptions;
import pl.droidsonroids.gif.InputSource;

/* compiled from: GifCore.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0002J\u0014\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ \u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0007J \u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J$\u0010\u0011\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014J4\u0010\u0016\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0014¨\u0006\u0019"}, d2 = {"Lcom/wifiled/ipixels/core/GifCore;", "", "<init>", "()V", "getLedType", "", "bitmaps2gifTA2", "bitmaps", "", "Lcom/wifiled/ipixels/ui/imgtxt/SaveGifData;", "decodeGif", "Ljava/util/ArrayList;", "Landroid/graphics/Bitmap;", "Lkotlin/collections/ArrayList;", DbFinal.LOCAL_PATH, "ba", "", "newBitmaps2gif2", "Lcom/squareup/gifencoder/Image;", "dataType", "", "fFrameRate", "newBitmaps2TemplateGif", "width", "height", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GifCore {
    public static final GifCore INSTANCE = new GifCore();

    private GifCore() {
    }

    private final String getLedType() {
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
                return "96";
            case 2:
                return "32";
            case 3:
                return "16";
            case 4:
                return "12";
            case 5:
                return "20";
            case 6:
                return "128";
            case 7:
                return "144";
            case 8:
                return "192";
            case 9:
                return "2448";
            case 10:
                return "3264";
            case 11:
                return "3296";
            case 12:
                return "128_2";
            case 13:
                return "3296_2";
            case 14:
                return "32160";
            case 15:
                return "32192";
            case 16:
                return "32256";
            default:
                return "64";
        }
    }

    public final String bitmaps2gifTA2(List<SaveGifData> bitmaps) {
        Intrinsics.checkNotNullParameter(bitmaps, "bitmaps");
        String absolutePath = new File(FileUtils.getExternalFilePath(App.INSTANCE.getContext(), "Gif" + File.separator + getLedType()), System.currentTimeMillis() + ".gif").getAbsolutePath();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
            ImageOptions imageOptions = new ImageOptions();
            imageOptions.setTop(0);
            imageOptions.setLeft(0);
            com.squareup.gifencoder.GifEncoder gifEncoder = new com.squareup.gifencoder.GifEncoder(fileOutputStream, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), 0);
            for (SaveGifData saveGifData : bitmaps) {
                imageOptions.setDelay(saveGifData.getDelayMs(), TimeUnit.MILLISECONDS);
                gifEncoder.addImage(Image.fromRgb(BitmapUtils.INSTANCE.getRGBArray(saveGifData.getBitmap()), saveGifData.getBitmap().getWidth()), imageOptions);
            }
            gifEncoder.finishEncoding();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intrinsics.checkNotNull(absolutePath);
        return absolutePath;
    }

    @JvmStatic
    public static final ArrayList<Bitmap> decodeGif(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        try {
            GifDecoder gifDecoder = new GifDecoder(new InputSource.FileSource(path), new GifOptions());
            Bitmap createBitmap = Bitmap.createBitmap(gifDecoder.getWidth(), gifDecoder.getHeight(), Bitmap.Config.ARGB_8888);
            int numberOfFrames = gifDecoder.getNumberOfFrames();
            for (int i = 0; i < numberOfFrames; i++) {
                gifDecoder.seekToFrame(i, createBitmap);
                arrayList.add(BitmapUtils.INSTANCE.zoomImage(createBitmap, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue()));
            }
            gifDecoder.recycle();
            return arrayList;
        } catch (GifIOException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    @JvmStatic
    public static final ArrayList<Bitmap> decodeGif(byte[] ba) {
        Intrinsics.checkNotNullParameter(ba, "ba");
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        GifDecoder gifDecoder = new GifDecoder(new InputSource.ByteArraySource(ba), new GifOptions());
        Bitmap createBitmap = Bitmap.createBitmap(gifDecoder.getWidth(), gifDecoder.getHeight(), Bitmap.Config.ARGB_8888);
        int numberOfFrames = gifDecoder.getNumberOfFrames();
        for (int i = 0; i < numberOfFrames; i++) {
            gifDecoder.seekToFrame(i, createBitmap);
            arrayList.add(BitmapUtils.INSTANCE.zoomImage(createBitmap, gifDecoder.getWidth(), gifDecoder.getHeight()));
        }
        gifDecoder.recycle();
        return arrayList;
    }

    public final String newBitmaps2gif2(List<Image> bitmaps, int dataType, int fFrameRate) {
        Intrinsics.checkNotNullParameter(bitmaps, "bitmaps");
        String absolutePath = new File(FileUtils.getExternalFilePath(App.INSTANCE.getContext(), (dataType == 3 ? "Gif" : "Video") + File.separator + getLedType()), System.currentTimeMillis() + ".gif").getAbsolutePath();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
            ImageOptions imageOptions = new ImageOptions();
            imageOptions.setTop(0);
            imageOptions.setLeft(0);
            imageOptions.setDelay(fFrameRate, TimeUnit.MILLISECONDS);
            com.squareup.gifencoder.GifEncoder gifEncoder = new com.squareup.gifencoder.GifEncoder(fileOutputStream, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), 0);
            Iterator<T> it = bitmaps.iterator();
            while (it.hasNext()) {
                gifEncoder.addImage((Image) it.next(), imageOptions);
            }
            gifEncoder.finishEncoding();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intrinsics.checkNotNull(absolutePath);
        return absolutePath;
    }

    public final String newBitmaps2TemplateGif(List<Image> bitmaps, int dataType, int fFrameRate, int width, int height) {
        Intrinsics.checkNotNullParameter(bitmaps, "bitmaps");
        String absolutePath = new File(FileUtils.getExternalFilePath(App.INSTANCE.getContext(), (dataType == 3 ? "Gif" : "Video") + File.separator + getLedType()), System.currentTimeMillis() + ".gif").getAbsolutePath();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
            ImageOptions imageOptions = new ImageOptions();
            imageOptions.setTop(0);
            imageOptions.setLeft(0);
            imageOptions.setDelay(fFrameRate, TimeUnit.MILLISECONDS);
            com.squareup.gifencoder.GifEncoder gifEncoder = new com.squareup.gifencoder.GifEncoder(fileOutputStream, width, height, 0);
            Iterator<Image> it = bitmaps.iterator();
            while (it.hasNext()) {
                gifEncoder.addImage(it.next(), imageOptions);
            }
            gifEncoder.finishEncoding();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intrinsics.checkNotNull(absolutePath);
        return absolutePath;
    }
}
