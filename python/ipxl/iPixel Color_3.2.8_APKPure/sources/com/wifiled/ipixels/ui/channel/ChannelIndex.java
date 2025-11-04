package com.wifiled.ipixels.ui.channel;

import android.os.Handler;
import android.util.Log;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.ui.text.vo.SendResultMsg;
import com.wifiled.ipixels.utils.HandlerUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.greenrobot.eventbus.EventBus;

/* compiled from: ChannelIndex.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010/\u001a\u000200J\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007J\u001a\u00101\u001a\u0002002\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007J\u0006\u00102\u001a\u00020\u0005J\u0006\u00103\u001a\u00020\u0005J\u0006\u00104\u001a\u00020\u0005J\u000e\u00105\u001a\u0002002\u0006\u00106\u001a\u000207R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/ChannelIndex;", "", "<init>", "()V", "index64", "", "mapSaveChannel64", "", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "index32", "mapSaveChannel32", "index16", "mapSaveChannel16", "index20", "mapSaveChannel20", "index12", "mapSaveChannel12", "index96", "mapSaveChannel96", "index128", "mapSaveChannel128", "index144", "mapSaveChannel144", "index192", "mapSaveChannel192", "index2448", "mapSaveChannel2448", "index3264", "mapSaveChannel3264", "index3296", "mapSaveChannel3296", "index3296_2", "mapSaveChannel3296_2", "index128_2", "mapSaveChannel128_2", "index32192", "mapSaveChannel32192", "index32160", "mapSaveChannel32160", "index32384", "mapSaveChannel32384", "index32320", "mapSaveChannel32320", "index32256", "mapSaveChannel32256", "index32448", "mapSaveChannel32448", "initChannelData", "", "mapSaveChannel", "index", "dec", "inc", "removeRecord", "arrIndex", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChannelIndex {
    private static int index12;
    private static int index128;
    private static int index128_2;
    private static int index144;
    private static int index16;
    private static int index192;
    private static int index20;
    private static int index2448;
    private static int index32;
    private static int index32160;
    private static int index32192;
    private static int index32256;
    private static int index32320;
    private static int index32384;
    private static int index32448;
    private static int index3264;
    private static int index3296;
    private static int index3296_2;
    private static int index64;
    private static int index96;
    public static final ChannelIndex INSTANCE = new ChannelIndex();
    private static Map<Integer, ChannelListItem> mapSaveChannel64 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel32 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel16 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel20 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel12 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel96 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel128 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel144 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel192 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel2448 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel3264 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel3296 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel3296_2 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel128_2 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel32192 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel32160 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel32384 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel32320 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel32256 = new LinkedHashMap();
    private static Map<Integer, ChannelListItem> mapSaveChannel32448 = new LinkedHashMap();

    private ChannelIndex() {
    }

    public final void initChannelData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            String str = "channel_data";
            switch (AppConfig.INSTANCE.getLedType()) {
                case 1:
                    str = "channel_data_96";
                    break;
                case 2:
                    str = "channel_data_32";
                    break;
                case 3:
                    str = "channel_data_16";
                    break;
                case 4:
                    str = "channel_data_12";
                    break;
                case 5:
                    str = "channel_data_20";
                    break;
                case 6:
                    str = "channel_data_128";
                    break;
                case 7:
                    str = "channel_data_144";
                    break;
                case 8:
                    str = "channel_data_192";
                    break;
                case 9:
                    str = "channel_data_24_48";
                    break;
                case 10:
                    str = "channel_data_32_64";
                    break;
                case 11:
                    str = "channel_data_32_96";
                    break;
                case 12:
                    str = "channel_data_128_2";
                    break;
                case 13:
                    str = "channel_data_32_96_2";
                    break;
                case 14:
                    str = "channel_data_32_160";
                    break;
                case 15:
                    str = "channel_data_32_192";
                    break;
                case 16:
                    str = "channel_data_32_256";
                    break;
                case 17:
                    str = "channel_data_32_320";
                    break;
                case 18:
                    str = "channel_data_32_384";
                    break;
                case 19:
                    str = "channel_data_32_448";
                    break;
            }
            if (SPUtils.contains(ContextHolder.getContext(), str) && SPUtils.get(ContextHolder.getContext(), str, new LinkedHashMap()) != null) {
                Object obj = SPUtils.get(ContextHolder.getContext(), str, new LinkedHashMap());
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                linkedHashMap = (Map) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("ruis", "initChannelData Exception" + e.getMessage());
        }
        mapSaveChannel(linkedHashMap);
    }

    public final Map<Integer, ChannelListItem> mapSaveChannel() {
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                return mapSaveChannel64;
            case 1:
                return mapSaveChannel96;
            case 2:
                return mapSaveChannel32;
            case 3:
                return mapSaveChannel16;
            case 4:
                return mapSaveChannel12;
            case 5:
                return mapSaveChannel20;
            case 6:
                return mapSaveChannel128;
            case 7:
                return mapSaveChannel144;
            case 8:
                return mapSaveChannel192;
            case 9:
                return mapSaveChannel2448;
            case 10:
                return mapSaveChannel3264;
            case 11:
                return mapSaveChannel3296;
            case 12:
                return mapSaveChannel128_2;
            case 13:
                return mapSaveChannel3296_2;
            case 14:
                return mapSaveChannel32160;
            case 15:
                return mapSaveChannel32192;
            case 16:
                return mapSaveChannel32256;
            case 17:
                return mapSaveChannel32320;
            case 18:
                return mapSaveChannel32384;
            case 19:
                return mapSaveChannel32448;
            default:
                throw new IllegalStateException("尺寸未适配".toString());
        }
    }

    public final void mapSaveChannel(Map<Integer, ChannelListItem> mapSaveChannel) {
        Intrinsics.checkNotNullParameter(mapSaveChannel, "mapSaveChannel");
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                mapSaveChannel64 = mapSaveChannel;
                return;
            case 1:
                mapSaveChannel96 = mapSaveChannel;
                return;
            case 2:
                mapSaveChannel32 = mapSaveChannel;
                return;
            case 3:
                mapSaveChannel16 = mapSaveChannel;
                return;
            case 4:
                mapSaveChannel12 = mapSaveChannel;
                return;
            case 5:
                mapSaveChannel20 = mapSaveChannel;
                return;
            case 6:
                mapSaveChannel128 = mapSaveChannel;
                return;
            case 7:
                mapSaveChannel144 = mapSaveChannel;
                return;
            case 8:
                mapSaveChannel192 = mapSaveChannel;
                return;
            case 9:
                mapSaveChannel2448 = mapSaveChannel;
                return;
            case 10:
                mapSaveChannel3264 = mapSaveChannel;
                return;
            case 11:
                mapSaveChannel3296 = mapSaveChannel;
                return;
            case 12:
                mapSaveChannel128_2 = mapSaveChannel;
                return;
            case 13:
                mapSaveChannel3296_2 = mapSaveChannel;
                return;
            case 14:
                mapSaveChannel32160 = mapSaveChannel;
                return;
            case 15:
                mapSaveChannel32192 = mapSaveChannel;
                return;
            case 16:
                mapSaveChannel32256 = mapSaveChannel;
                return;
            case 17:
                mapSaveChannel32320 = mapSaveChannel;
                return;
            case 18:
                mapSaveChannel32384 = mapSaveChannel;
                return;
            case 19:
                mapSaveChannel32448 = mapSaveChannel;
                return;
            default:
                throw new IllegalStateException("尺寸未适配".toString());
        }
    }

    public final int index() {
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                return index64;
            case 1:
                return index96;
            case 2:
                return index32;
            case 3:
                return index16;
            case 4:
                return index12;
            case 5:
                return index20;
            case 6:
                return index128;
            case 7:
                return index144;
            case 8:
                return index192;
            case 9:
                return index2448;
            case 10:
                return index3264;
            case 11:
                return index3296;
            case 12:
                return index128_2;
            case 13:
                return index3296_2;
            case 14:
                return index32160;
            case 15:
                return index32192;
            case 16:
                return index32256;
            case 17:
                return index32320;
            case 18:
                return index32384;
            case 19:
                return index32448;
            default:
                throw new IllegalStateException("尺寸未适配".toString());
        }
    }

    public final int dec() {
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                int i = index64 - 1;
                index64 = i;
                if (i < 0) {
                    index64 = 0;
                }
                return index64;
            case 1:
                int i2 = index96 - 1;
                index96 = i2;
                if (i2 < 0) {
                    index96 = 0;
                }
                return index96;
            case 2:
                int i3 = index32 - 1;
                index32 = i3;
                if (i3 < 0) {
                    index32 = 0;
                }
                return index32;
            case 3:
                int i4 = index16 - 1;
                index16 = i4;
                if (i4 < 0) {
                    index16 = 0;
                }
                return index16;
            case 4:
                int i5 = index12 - 1;
                index12 = i5;
                if (i5 < 0) {
                    index12 = 0;
                }
                return index12;
            case 5:
                int i6 = index20 - 1;
                index20 = i6;
                if (i6 < 0) {
                    index20 = 0;
                }
                return index20;
            case 6:
                int i7 = index128 - 1;
                index128 = i7;
                if (i7 < 0) {
                    index128 = 0;
                }
                return index128;
            case 7:
                int i8 = index144 - 1;
                index144 = i8;
                if (i8 < 0) {
                    index144 = 0;
                }
                return index144;
            case 8:
                int i9 = index192 - 1;
                index192 = i9;
                if (i9 < 0) {
                    index192 = 0;
                }
                return index192;
            case 9:
                int i10 = index2448 - 1;
                index2448 = i10;
                if (i10 < 0) {
                    index2448 = 0;
                }
                return index2448;
            case 10:
                int i11 = index3264 - 1;
                index3264 = i11;
                if (i11 < 0) {
                    index3264 = 0;
                }
                return index3264;
            case 11:
                int i12 = index3296 - 1;
                index3296 = i12;
                if (i12 < 0) {
                    index3296 = 0;
                }
                return index3296;
            case 12:
                int i13 = index128_2 - 1;
                index128_2 = i13;
                if (i13 < 0) {
                    index128_2 = 0;
                }
                return index128_2;
            case 13:
                int i14 = index3296_2 - 1;
                index3296_2 = i14;
                if (i14 < 0) {
                    index3296_2 = 0;
                }
                return index3296_2;
            case 14:
                int i15 = index32160 - 1;
                index32160 = i15;
                if (i15 < 0) {
                    index32160 = 0;
                }
                return index32160;
            case 15:
                int i16 = index32192 - 1;
                index32192 = i16;
                if (i16 < 0) {
                    index32192 = 0;
                }
                return index32192;
            case 16:
                int i17 = index32256 - 1;
                index32256 = i17;
                if (i17 < 0) {
                    index32256 = 0;
                }
                return index32256;
            case 17:
                int i18 = index32320 - 1;
                index32320 = i18;
                if (i18 < 0) {
                    index32320 = 0;
                }
                return index32320;
            case 18:
                int i19 = index32384 - 1;
                index32384 = i19;
                if (i19 < 0) {
                    index32384 = 0;
                }
                return index32384;
            case 19:
                int i20 = index32448 - 1;
                index32448 = i20;
                if (i20 < 0) {
                    index32448 = 0;
                }
                return index32448;
            default:
                throw new IllegalStateException("尺寸未适配".toString());
        }
    }

    public final int inc() {
        if (mapSaveChannel().size() >= 100) {
            Handler handler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler().get();
            if (handler == null) {
                return -1;
            }
            handler.post(new Runnable() { // from class: com.wifiled.ipixels.ui.channel.ChannelIndex$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ChannelIndex.inc$lambda$0();
                }
            });
            return -1;
        }
        int i = 0;
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                SortedMap sortedMap = MapsKt.toSortedMap(mapSaveChannel64);
                mapSaveChannel64 = sortedMap;
                Iterator it = sortedMap.entrySet().iterator();
                while (it.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it.next()).getKey()).intValue() != i) {
                        index64 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel64.size()) {
                    index64 = i;
                }
                int i2 = index64 + 1;
                index64 = i2;
                return i2;
            case 1:
                SortedMap sortedMap2 = MapsKt.toSortedMap(mapSaveChannel96);
                mapSaveChannel96 = sortedMap2;
                Iterator it2 = sortedMap2.entrySet().iterator();
                while (it2.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it2.next()).getKey()).intValue() != i) {
                        index96 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel96.size()) {
                    index96 = i;
                }
                int i3 = index96 + 1;
                index96 = i3;
                return i3;
            case 2:
                SortedMap sortedMap3 = MapsKt.toSortedMap(mapSaveChannel32);
                mapSaveChannel32 = sortedMap3;
                Iterator it3 = sortedMap3.entrySet().iterator();
                while (it3.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it3.next()).getKey()).intValue() != i) {
                        index32 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel32.size()) {
                    index32 = i;
                }
                int i4 = index32 + 1;
                index32 = i4;
                return i4;
            case 3:
                SortedMap sortedMap4 = MapsKt.toSortedMap(mapSaveChannel16);
                mapSaveChannel16 = sortedMap4;
                Iterator it4 = sortedMap4.entrySet().iterator();
                while (it4.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it4.next()).getKey()).intValue() != i) {
                        index16 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel16.size()) {
                    index16 = i;
                }
                int i5 = index16 + 1;
                index16 = i5;
                return i5;
            case 4:
                SortedMap sortedMap5 = MapsKt.toSortedMap(mapSaveChannel12);
                mapSaveChannel12 = sortedMap5;
                Iterator it5 = sortedMap5.entrySet().iterator();
                while (it5.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it5.next()).getKey()).intValue() != i) {
                        index12 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel12.size()) {
                    index12 = i;
                }
                int i6 = index12 + 1;
                index12 = i6;
                return i6;
            case 5:
                SortedMap sortedMap6 = MapsKt.toSortedMap(mapSaveChannel20);
                mapSaveChannel20 = sortedMap6;
                Iterator it6 = sortedMap6.entrySet().iterator();
                while (it6.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it6.next()).getKey()).intValue() != i) {
                        index20 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel20.size()) {
                    index20 = i;
                }
                int i7 = index20 + 1;
                index20 = i7;
                return i7;
            case 6:
                SortedMap sortedMap7 = MapsKt.toSortedMap(mapSaveChannel128);
                mapSaveChannel128 = sortedMap7;
                Iterator it7 = sortedMap7.entrySet().iterator();
                while (it7.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it7.next()).getKey()).intValue() != i) {
                        index128 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel128.size()) {
                    index128 = i;
                }
                int i8 = index128 + 1;
                index128 = i8;
                return i8;
            case 7:
                SortedMap sortedMap8 = MapsKt.toSortedMap(mapSaveChannel144);
                mapSaveChannel144 = sortedMap8;
                Iterator it8 = sortedMap8.entrySet().iterator();
                while (it8.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it8.next()).getKey()).intValue() != i) {
                        index144 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel144.size()) {
                    index144 = i;
                }
                int i9 = index144 + 1;
                index144 = i9;
                return i9;
            case 8:
                SortedMap sortedMap9 = MapsKt.toSortedMap(mapSaveChannel192);
                mapSaveChannel192 = sortedMap9;
                Iterator it9 = sortedMap9.entrySet().iterator();
                while (it9.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it9.next()).getKey()).intValue() != i) {
                        index192 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel192.size()) {
                    index192 = i;
                }
                int i10 = index192 + 1;
                index192 = i10;
                return i10;
            case 9:
                SortedMap sortedMap10 = MapsKt.toSortedMap(mapSaveChannel2448);
                mapSaveChannel2448 = sortedMap10;
                Iterator it10 = sortedMap10.entrySet().iterator();
                while (it10.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it10.next()).getKey()).intValue() != i) {
                        index2448 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel2448.size()) {
                    index2448 = i;
                }
                int i11 = index2448 + 1;
                index2448 = i11;
                return i11;
            case 10:
                SortedMap sortedMap11 = MapsKt.toSortedMap(mapSaveChannel3264);
                mapSaveChannel3264 = sortedMap11;
                Iterator it11 = sortedMap11.entrySet().iterator();
                while (it11.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it11.next()).getKey()).intValue() != i) {
                        index3264 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel3264.size()) {
                    index3264 = i;
                }
                int i12 = index3264 + 1;
                index3264 = i12;
                return i12;
            case 11:
                SortedMap sortedMap12 = MapsKt.toSortedMap(mapSaveChannel3296);
                mapSaveChannel3296 = sortedMap12;
                Iterator it12 = sortedMap12.entrySet().iterator();
                while (it12.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it12.next()).getKey()).intValue() != i) {
                        index3296 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel3296.size()) {
                    index3296 = i;
                }
                int i13 = index3296 + 1;
                index3296 = i13;
                return i13;
            case 12:
                SortedMap sortedMap13 = MapsKt.toSortedMap(mapSaveChannel128_2);
                mapSaveChannel128_2 = sortedMap13;
                Iterator it13 = sortedMap13.entrySet().iterator();
                while (it13.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it13.next()).getKey()).intValue() != i) {
                        index128_2 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel128_2.size()) {
                    index128_2 = i;
                }
                int i14 = index128_2 + 1;
                index128_2 = i14;
                return i14;
            case 13:
                SortedMap sortedMap14 = MapsKt.toSortedMap(mapSaveChannel3296_2);
                mapSaveChannel3296_2 = sortedMap14;
                Iterator it14 = sortedMap14.entrySet().iterator();
                while (it14.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it14.next()).getKey()).intValue() != i) {
                        index3296_2 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel3296_2.size()) {
                    index3296_2 = i;
                }
                int i15 = index3296_2 + 1;
                index3296_2 = i15;
                return i15;
            case 14:
                SortedMap sortedMap15 = MapsKt.toSortedMap(mapSaveChannel32160);
                mapSaveChannel32160 = sortedMap15;
                Iterator it15 = sortedMap15.entrySet().iterator();
                while (it15.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it15.next()).getKey()).intValue() != i) {
                        index32160 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel32160.size()) {
                    index32160 = i;
                }
                int i16 = index32160 + 1;
                index32160 = i16;
                return i16;
            case 15:
                SortedMap sortedMap16 = MapsKt.toSortedMap(mapSaveChannel32192);
                mapSaveChannel32192 = sortedMap16;
                Iterator it16 = sortedMap16.entrySet().iterator();
                while (it16.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it16.next()).getKey()).intValue() != i) {
                        index32192 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel32192.size()) {
                    index32192 = i;
                }
                int i17 = index32192 + 1;
                index32192 = i17;
                return i17;
            case 16:
                SortedMap sortedMap17 = MapsKt.toSortedMap(mapSaveChannel32256);
                mapSaveChannel32256 = sortedMap17;
                Iterator it17 = sortedMap17.entrySet().iterator();
                while (it17.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it17.next()).getKey()).intValue() != i) {
                        index32256 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel32256.size()) {
                    index32256 = i;
                }
                int i18 = index32256 + 1;
                index32256 = i18;
                return i18;
            case 17:
                SortedMap sortedMap18 = MapsKt.toSortedMap(mapSaveChannel32320);
                mapSaveChannel32320 = sortedMap18;
                Iterator it18 = sortedMap18.entrySet().iterator();
                while (it18.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it18.next()).getKey()).intValue() != i) {
                        index32320 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel32320.size()) {
                    index32320 = i;
                }
                int i19 = index32320 + 1;
                index32320 = i19;
                return i19;
            case 18:
                SortedMap sortedMap19 = MapsKt.toSortedMap(mapSaveChannel32384);
                mapSaveChannel32384 = sortedMap19;
                Iterator it19 = sortedMap19.entrySet().iterator();
                while (it19.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it19.next()).getKey()).intValue() != i) {
                        index32384 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel32384.size()) {
                    index32384 = i;
                }
                int i20 = index32384 + 1;
                index32384 = i20;
                return i20;
            case 19:
                SortedMap sortedMap20 = MapsKt.toSortedMap(mapSaveChannel32448);
                mapSaveChannel32448 = sortedMap20;
                Iterator it20 = sortedMap20.entrySet().iterator();
                while (it20.hasNext()) {
                    i++;
                    if (((Number) ((Map.Entry) it20.next()).getKey()).intValue() != i) {
                        index32448 = i;
                        return i;
                    }
                }
                if (i == mapSaveChannel32448.size()) {
                    index32448 = i;
                }
                int i21 = index32448 + 1;
                index32448 = i21;
                return i21;
            default:
                throw new IllegalStateException("尺寸未适配".toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void inc$lambda$0() {
        EventBus eventBus = EventBus.getDefault();
        byte[] bytes = "channel low space".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        eventBus.post(new SendResultMsg(bytes));
    }

    public final void removeRecord(byte[] arrIndex) {
        Intrinsics.checkNotNullParameter(arrIndex, "arrIndex");
        int i = 0;
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                int length = arrIndex.length;
                while (i < length) {
                    mapSaveChannel64.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 1:
                int length2 = arrIndex.length;
                while (i < length2) {
                    mapSaveChannel96.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 2:
                int length3 = arrIndex.length;
                while (i < length3) {
                    mapSaveChannel32.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 3:
                int length4 = arrIndex.length;
                while (i < length4) {
                    mapSaveChannel16.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 4:
                int length5 = arrIndex.length;
                while (i < length5) {
                    mapSaveChannel12.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 5:
                int length6 = arrIndex.length;
                while (i < length6) {
                    mapSaveChannel20.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 6:
                int length7 = arrIndex.length;
                while (i < length7) {
                    mapSaveChannel128.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 7:
                int length8 = arrIndex.length;
                while (i < length8) {
                    mapSaveChannel144.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 8:
                int length9 = arrIndex.length;
                while (i < length9) {
                    mapSaveChannel192.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 9:
                int length10 = arrIndex.length;
                while (i < length10) {
                    mapSaveChannel2448.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 10:
                int length11 = arrIndex.length;
                while (i < length11) {
                    mapSaveChannel3264.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 11:
                int length12 = arrIndex.length;
                while (i < length12) {
                    mapSaveChannel3296.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 12:
                int length13 = arrIndex.length;
                while (i < length13) {
                    mapSaveChannel128_2.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 13:
                int length14 = arrIndex.length;
                while (i < length14) {
                    mapSaveChannel3296_2.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 14:
                int length15 = arrIndex.length;
                while (i < length15) {
                    mapSaveChannel32160.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 15:
                int length16 = arrIndex.length;
                while (i < length16) {
                    mapSaveChannel32192.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 16:
                int length17 = arrIndex.length;
                while (i < length17) {
                    mapSaveChannel32256.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 17:
                int length18 = arrIndex.length;
                while (i < length18) {
                    mapSaveChannel32320.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 18:
                int length19 = arrIndex.length;
                while (i < length19) {
                    mapSaveChannel32384.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            case 19:
                int length20 = arrIndex.length;
                while (i < length20) {
                    mapSaveChannel32448.remove(Integer.valueOf(arrIndex[i]));
                    INSTANCE.dec();
                    i++;
                }
                return;
            default:
                throw new IllegalStateException("尺寸未适配".toString());
        }
    }
}
