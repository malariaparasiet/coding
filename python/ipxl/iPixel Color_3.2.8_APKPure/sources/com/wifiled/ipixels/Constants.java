package com.wifiled.ipixels;

import android.content.Context;
import com.wifiled.ipixels.utils.ResourceUtils;
import com.wifiled.ipixels.vo.CategoryVO;
import com.wifiled.ipixels.vo.RidingVO;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Constants.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u000e\u0010\u0011\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/wifiled/ipixels/Constants;", "", "<init>", "()V", "VAL_GLOBAL_LIGHT", "", "VAL_DIY_ANIM_LIGHT", Constants.FLAG, "iAnimAlha", "", "getIAnimAlha", "()I", "setIAnimAlha", "(I)V", "iAnimSpeed", "getIAnimSpeed", "setIAnimSpeed", "Bit0", "", "Bit1", "Bit2", "Bit3", "getRidingData", "", "Lcom/wifiled/ipixels/vo/RidingVO;", "getCategoryData", "Lcom/wifiled/ipixels/vo/CategoryVO;", "context", "Landroid/content/Context;", "getGameCategoryData", "getMultiDeviceList", "DIRECTION", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Constants {
    public static final byte Bit0 = 0;
    public static final byte Bit1 = 1;
    public static final byte Bit2 = 2;
    public static final byte Bit3 = 3;
    public static final String FLAG = "FLAG";
    public static final String VAL_DIY_ANIM_LIGHT = "diy_anim_light_value";
    public static final String VAL_GLOBAL_LIGHT = "global_light_value";
    public static final Constants INSTANCE = new Constants();
    private static int iAnimAlha = 100;
    private static int iAnimSpeed = 50;

    private Constants() {
    }

    public final int getIAnimAlha() {
        return iAnimAlha;
    }

    public final void setIAnimAlha(int i) {
        iAnimAlha = i;
    }

    public final int getIAnimSpeed() {
        return iAnimSpeed;
    }

    public final void setIAnimSpeed(int i) {
        iAnimSpeed = i;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Constants.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/wifiled/ipixels/Constants$DIRECTION;", "", "<init>", "(Ljava/lang/String;I)V", "UP", "DOWN", "DOWN_LONG_CLICK", "LEFT", "RIGHT", "BACK", "SCREEN_SWITCH", "START_PAUSE", "DEFORMATION", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DIRECTION {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ DIRECTION[] $VALUES;
        public static final DIRECTION UP = new DIRECTION("UP", 0);
        public static final DIRECTION DOWN = new DIRECTION("DOWN", 1);
        public static final DIRECTION DOWN_LONG_CLICK = new DIRECTION("DOWN_LONG_CLICK", 2);
        public static final DIRECTION LEFT = new DIRECTION("LEFT", 3);
        public static final DIRECTION RIGHT = new DIRECTION("RIGHT", 4);
        public static final DIRECTION BACK = new DIRECTION("BACK", 5);
        public static final DIRECTION SCREEN_SWITCH = new DIRECTION("SCREEN_SWITCH", 6);
        public static final DIRECTION START_PAUSE = new DIRECTION("START_PAUSE", 7);
        public static final DIRECTION DEFORMATION = new DIRECTION("DEFORMATION", 8);

        private static final /* synthetic */ DIRECTION[] $values() {
            return new DIRECTION[]{UP, DOWN, DOWN_LONG_CLICK, LEFT, RIGHT, BACK, SCREEN_SWITCH, START_PAUSE, DEFORMATION};
        }

        public static EnumEntries<DIRECTION> getEntries() {
            return $ENTRIES;
        }

        public static DIRECTION valueOf(String str) {
            return (DIRECTION) Enum.valueOf(DIRECTION.class, str);
        }

        public static DIRECTION[] values() {
            return (DIRECTION[]) $VALUES.clone();
        }

        private DIRECTION(String str, int i) {
        }

        static {
            DIRECTION[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public final List<RidingVO> getRidingData() {
        ArrayList arrayList = new ArrayList();
        Integer valueOf = Integer.valueOf(R.mipmap.ic_launcher);
        int i = 0;
        for (Object obj : CollectionsKt.listOf((Object[]) new Integer[]{valueOf, valueOf, valueOf, valueOf, valueOf, valueOf})) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(new RidingVO(((Number) obj).intValue(), i, new byte[4]));
            i = i2;
        }
        return arrayList;
    }

    public final List<CategoryVO> getCategoryData(Context context) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                if (AppConfig.INSTANCE.getLedHasWifi()) {
                    String string = context.getString(R.string.title_text);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    String string2 = context.getString(R.string.title_gallery);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    String string3 = context.getString(R.string.title_diy);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String string4 = context.getString(R.string.title_rhythm);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    String string5 = context.getString(R.string.title_game);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    String string6 = context.getString(R.string.title_projection);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                    String string7 = context.getString(R.string.title_riding);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                    String string8 = context.getString(R.string.title_channel_list);
                    Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                    String string9 = context.getString(R.string.title_settings);
                    Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                    arrayList2 = CollectionsKt.mutableListOf(string, string2, string3, string4, string5, string6, string7, string8, string9);
                } else if (AppConfig.INSTANCE.getMcu() > 4 && AppConfig.INSTANCE.getMcu() != 7 && AppConfig.INSTANCE.getMcu() != 12) {
                    if (AppConfig.INSTANCE.getCid().length() == 0 && AppConfig.INSTANCE.getPid().length() == 0) {
                        String string10 = context.getString(R.string.title_text);
                        Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                        String string11 = context.getString(R.string.title_gallery);
                        Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                        String string12 = context.getString(R.string.title_diy);
                        Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                        String string13 = context.getString(R.string.title_rhythm);
                        Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
                        String string14 = context.getString(R.string.title_riding);
                        Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
                        String string15 = context.getString(R.string.title_channel_list);
                        Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
                        String string16 = context.getString(R.string.title_colock);
                        Intrinsics.checkNotNullExpressionValue(string16, "getString(...)");
                        String string17 = context.getString(R.string.img_txt_combination);
                        Intrinsics.checkNotNullExpressionValue(string17, "getString(...)");
                        String string18 = context.getString(R.string.title_settings);
                        Intrinsics.checkNotNullExpressionValue(string18, "getString(...)");
                        arrayList2 = CollectionsKt.mutableListOf(string10, string11, string12, string13, string14, string15, string16, string17, string18);
                    } else if (Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0004") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "02")) {
                        String string19 = context.getString(R.string.title_text);
                        Intrinsics.checkNotNullExpressionValue(string19, "getString(...)");
                        String string20 = context.getString(R.string.title_gallery);
                        Intrinsics.checkNotNullExpressionValue(string20, "getString(...)");
                        String string21 = context.getString(R.string.title_diy);
                        Intrinsics.checkNotNullExpressionValue(string21, "getString(...)");
                        String string22 = context.getString(R.string.title_rhythm);
                        Intrinsics.checkNotNullExpressionValue(string22, "getString(...)");
                        String string23 = context.getString(R.string.title_riding);
                        Intrinsics.checkNotNullExpressionValue(string23, "getString(...)");
                        String string24 = context.getString(R.string.title_channel_list);
                        Intrinsics.checkNotNullExpressionValue(string24, "getString(...)");
                        String string25 = context.getString(R.string.title_colock);
                        Intrinsics.checkNotNullExpressionValue(string25, "getString(...)");
                        String string26 = context.getString(R.string.img_txt_combination);
                        Intrinsics.checkNotNullExpressionValue(string26, "getString(...)");
                        String string27 = context.getString(R.string.title_settings);
                        Intrinsics.checkNotNullExpressionValue(string27, "getString(...)");
                        arrayList2 = CollectionsKt.mutableListOf(string19, string20, string21, string22, string23, string24, string25, string26, string27);
                    } else {
                        String string28 = context.getString(R.string.title_text);
                        Intrinsics.checkNotNullExpressionValue(string28, "getString(...)");
                        String string29 = context.getString(R.string.title_gallery);
                        Intrinsics.checkNotNullExpressionValue(string29, "getString(...)");
                        String string30 = context.getString(R.string.title_diy);
                        Intrinsics.checkNotNullExpressionValue(string30, "getString(...)");
                        String string31 = context.getString(R.string.title_rhythm);
                        Intrinsics.checkNotNullExpressionValue(string31, "getString(...)");
                        String string32 = context.getString(R.string.title_riding);
                        Intrinsics.checkNotNullExpressionValue(string32, "getString(...)");
                        String string33 = context.getString(R.string.title_channel_list);
                        Intrinsics.checkNotNullExpressionValue(string33, "getString(...)");
                        String string34 = context.getString(R.string.title_colock);
                        Intrinsics.checkNotNullExpressionValue(string34, "getString(...)");
                        String string35 = context.getString(R.string.title_control);
                        Intrinsics.checkNotNullExpressionValue(string35, "getString(...)");
                        String string36 = context.getString(R.string.img_txt_combination);
                        Intrinsics.checkNotNullExpressionValue(string36, "getString(...)");
                        String string37 = context.getString(R.string.title_settings);
                        Intrinsics.checkNotNullExpressionValue(string37, "getString(...)");
                        arrayList2 = CollectionsKt.mutableListOf(string28, string29, string30, string31, string32, string33, string34, string35, string36, string37);
                    }
                } else {
                    String string38 = context.getString(R.string.title_text);
                    Intrinsics.checkNotNullExpressionValue(string38, "getString(...)");
                    String string39 = context.getString(R.string.title_gallery);
                    Intrinsics.checkNotNullExpressionValue(string39, "getString(...)");
                    String string40 = context.getString(R.string.title_diy);
                    Intrinsics.checkNotNullExpressionValue(string40, "getString(...)");
                    String string41 = context.getString(R.string.title_rhythm);
                    Intrinsics.checkNotNullExpressionValue(string41, "getString(...)");
                    String string42 = context.getString(R.string.title_riding);
                    Intrinsics.checkNotNullExpressionValue(string42, "getString(...)");
                    String string43 = context.getString(R.string.title_channel_list);
                    Intrinsics.checkNotNullExpressionValue(string43, "getString(...)");
                    String string44 = context.getString(R.string.title_colock);
                    Intrinsics.checkNotNullExpressionValue(string44, "getString(...)");
                    String string45 = context.getString(R.string.img_txt_combination);
                    Intrinsics.checkNotNullExpressionValue(string45, "getString(...)");
                    String string46 = context.getString(R.string.title_settings);
                    Intrinsics.checkNotNullExpressionValue(string46, "getString(...)");
                    arrayList2 = CollectionsKt.mutableListOf(string38, string39, string40, string41, string42, string43, string44, string45, string46);
                }
                if (AppConfig.INSTANCE.getLedHasWifi()) {
                    i = R.array.category;
                    break;
                } else if (AppConfig.INSTANCE.getMcu() > 4 && AppConfig.INSTANCE.getMcu() != 7 && AppConfig.INSTANCE.getMcu() != 12 && ((AppConfig.INSTANCE.getCid().length() != 0 || AppConfig.INSTANCE.getPid().length() != 0) && (!Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0004") || !Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "02")))) {
                    i = R.array.category_bt_ble;
                    break;
                } else {
                    i = R.array.category_bt_ble_4;
                    break;
                }
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
                String string47 = context.getString(R.string.title_text);
                Intrinsics.checkNotNullExpressionValue(string47, "getString(...)");
                String string48 = context.getString(R.string.title_gallery);
                Intrinsics.checkNotNullExpressionValue(string48, "getString(...)");
                String string49 = context.getString(R.string.title_channel_list);
                Intrinsics.checkNotNullExpressionValue(string49, "getString(...)");
                String string50 = context.getString(R.string.title_diy_image);
                Intrinsics.checkNotNullExpressionValue(string50, "getString(...)");
                String string51 = context.getString(R.string.title_diy_video);
                Intrinsics.checkNotNullExpressionValue(string51, "getString(...)");
                String string52 = context.getString(R.string.title_control);
                Intrinsics.checkNotNullExpressionValue(string52, "getString(...)");
                String string53 = context.getString(R.string.title_rhythm);
                Intrinsics.checkNotNullExpressionValue(string53, "getString(...)");
                String string54 = context.getString(R.string.title_colock);
                Intrinsics.checkNotNullExpressionValue(string54, "getString(...)");
                String string55 = context.getString(R.string.img_txt_combination);
                Intrinsics.checkNotNullExpressionValue(string55, "getString(...)");
                String string56 = context.getString(R.string.title_settings);
                Intrinsics.checkNotNullExpressionValue(string56, "getString(...)");
                arrayList2 = CollectionsKt.mutableListOf(string47, string48, string49, string50, string51, string52, string53, string54, string55, string56);
                i = R.array.category_bt_ble_16;
                break;
            case 2:
                if (AppConfig.INSTANCE.getMcu() > 4 && AppConfig.INSTANCE.getMcu() != 7 && !Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0000") && !Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0001") && !Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0007") && !Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "02")) {
                    String string57 = context.getString(R.string.title_text);
                    Intrinsics.checkNotNullExpressionValue(string57, "getString(...)");
                    String string58 = context.getString(R.string.title_gallery);
                    Intrinsics.checkNotNullExpressionValue(string58, "getString(...)");
                    String string59 = context.getString(R.string.title_diy);
                    Intrinsics.checkNotNullExpressionValue(string59, "getString(...)");
                    String string60 = context.getString(R.string.title_rhythm);
                    Intrinsics.checkNotNullExpressionValue(string60, "getString(...)");
                    String string61 = context.getString(R.string.title_channel_list);
                    Intrinsics.checkNotNullExpressionValue(string61, "getString(...)");
                    String string62 = context.getString(R.string.title_colock);
                    Intrinsics.checkNotNullExpressionValue(string62, "getString(...)");
                    String string63 = context.getString(R.string.title_control);
                    Intrinsics.checkNotNullExpressionValue(string63, "getString(...)");
                    String string64 = context.getString(R.string.img_txt_combination);
                    Intrinsics.checkNotNullExpressionValue(string64, "getString(...)");
                    String string65 = context.getString(R.string.title_settings);
                    Intrinsics.checkNotNullExpressionValue(string65, "getString(...)");
                    arrayList2 = CollectionsKt.mutableListOf(string57, string58, string59, string60, string61, string62, string63, string64, string65);
                    i = R.array.category_bt;
                    break;
                } else {
                    String string66 = context.getString(R.string.title_text);
                    Intrinsics.checkNotNullExpressionValue(string66, "getString(...)");
                    String string67 = context.getString(R.string.title_gallery);
                    Intrinsics.checkNotNullExpressionValue(string67, "getString(...)");
                    String string68 = context.getString(R.string.title_diy);
                    Intrinsics.checkNotNullExpressionValue(string68, "getString(...)");
                    String string69 = context.getString(R.string.title_rhythm);
                    Intrinsics.checkNotNullExpressionValue(string69, "getString(...)");
                    String string70 = context.getString(R.string.title_channel_list);
                    Intrinsics.checkNotNullExpressionValue(string70, "getString(...)");
                    String string71 = context.getString(R.string.title_colock);
                    Intrinsics.checkNotNullExpressionValue(string71, "getString(...)");
                    String string72 = context.getString(R.string.img_txt_combination);
                    Intrinsics.checkNotNullExpressionValue(string72, "getString(...)");
                    String string73 = context.getString(R.string.title_settings);
                    Intrinsics.checkNotNullExpressionValue(string73, "getString(...)");
                    arrayList2 = CollectionsKt.mutableListOf(string66, string67, string68, string69, string70, string71, string72, string73);
                    i = R.array.category_bt_4;
                    break;
                }
            case 4:
                if ((Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0001") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "10")) || ((Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0008") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "01")) || (Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0012") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "01")))) {
                    String string74 = context.getString(R.string.title_text);
                    Intrinsics.checkNotNullExpressionValue(string74, "getString(...)");
                    String string75 = context.getString(R.string.title_gallery);
                    Intrinsics.checkNotNullExpressionValue(string75, "getString(...)");
                    String string76 = context.getString(R.string.title_channel_list);
                    Intrinsics.checkNotNullExpressionValue(string76, "getString(...)");
                    String string77 = context.getString(R.string.title_diy_image);
                    Intrinsics.checkNotNullExpressionValue(string77, "getString(...)");
                    String string78 = context.getString(R.string.title_diy_video);
                    Intrinsics.checkNotNullExpressionValue(string78, "getString(...)");
                    String string79 = context.getString(R.string.title_rhythm);
                    Intrinsics.checkNotNullExpressionValue(string79, "getString(...)");
                    String string80 = context.getString(R.string.title_colock);
                    Intrinsics.checkNotNullExpressionValue(string80, "getString(...)");
                    String string81 = context.getString(R.string.img_txt_combination);
                    Intrinsics.checkNotNullExpressionValue(string81, "getString(...)");
                    String string82 = context.getString(R.string.title_settings);
                    Intrinsics.checkNotNullExpressionValue(string82, "getString(...)");
                    arrayList2 = CollectionsKt.mutableListOf(string74, string75, string76, string77, string78, string79, string80, string81, string82);
                    i = R.array.category_bt_ble_12;
                    break;
                } else {
                    String string83 = context.getString(R.string.title_text);
                    Intrinsics.checkNotNullExpressionValue(string83, "getString(...)");
                    String string84 = context.getString(R.string.title_gallery);
                    Intrinsics.checkNotNullExpressionValue(string84, "getString(...)");
                    String string85 = context.getString(R.string.title_channel_list);
                    Intrinsics.checkNotNullExpressionValue(string85, "getString(...)");
                    String string86 = context.getString(R.string.title_diy_image);
                    Intrinsics.checkNotNullExpressionValue(string86, "getString(...)");
                    String string87 = context.getString(R.string.title_diy_video);
                    Intrinsics.checkNotNullExpressionValue(string87, "getString(...)");
                    String string88 = context.getString(R.string.title_control);
                    Intrinsics.checkNotNullExpressionValue(string88, "getString(...)");
                    String string89 = context.getString(R.string.title_rhythm);
                    Intrinsics.checkNotNullExpressionValue(string89, "getString(...)");
                    String string90 = context.getString(R.string.title_colock);
                    Intrinsics.checkNotNullExpressionValue(string90, "getString(...)");
                    String string91 = context.getString(R.string.img_txt_combination);
                    Intrinsics.checkNotNullExpressionValue(string91, "getString(...)");
                    String string92 = context.getString(R.string.title_settings);
                    Intrinsics.checkNotNullExpressionValue(string92, "getString(...)");
                    arrayList2 = CollectionsKt.mutableListOf(string83, string84, string85, string86, string87, string88, string89, string90, string91, string92);
                    i = R.array.category_bt_ble_16;
                    break;
                }
                break;
            case 6:
            case 13:
                String string93 = context.getString(R.string.title_text);
                Intrinsics.checkNotNullExpressionValue(string93, "getString(...)");
                String string94 = context.getString(R.string.title_gallery);
                Intrinsics.checkNotNullExpressionValue(string94, "getString(...)");
                String string95 = context.getString(R.string.title_channel_list);
                Intrinsics.checkNotNullExpressionValue(string95, "getString(...)");
                String string96 = context.getString(R.string.title_diy_image);
                Intrinsics.checkNotNullExpressionValue(string96, "getString(...)");
                String string97 = context.getString(R.string.title_diy_video);
                Intrinsics.checkNotNullExpressionValue(string97, "getString(...)");
                String string98 = context.getString(R.string.title_rhythm);
                Intrinsics.checkNotNullExpressionValue(string98, "getString(...)");
                String string99 = context.getString(R.string.title_colock);
                Intrinsics.checkNotNullExpressionValue(string99, "getString(...)");
                String string100 = context.getString(R.string.img_txt_combination);
                Intrinsics.checkNotNullExpressionValue(string100, "getString(...)");
                String string101 = context.getString(R.string.title_settings);
                Intrinsics.checkNotNullExpressionValue(string101, "getString(...)");
                arrayList2 = CollectionsKt.mutableListOf(string93, string94, string95, string96, string97, string98, string99, string100, string101);
                i = R.array.category_bt_ble_128;
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
                String string102 = context.getString(R.string.title_text);
                Intrinsics.checkNotNullExpressionValue(string102, "getString(...)");
                String string103 = context.getString(R.string.partition_display);
                Intrinsics.checkNotNullExpressionValue(string103, "getString(...)");
                String string104 = context.getString(R.string.title_gallery);
                Intrinsics.checkNotNullExpressionValue(string104, "getString(...)");
                String string105 = context.getString(R.string.title_channel_list);
                Intrinsics.checkNotNullExpressionValue(string105, "getString(...)");
                String string106 = context.getString(R.string.title_diy_image);
                Intrinsics.checkNotNullExpressionValue(string106, "getString(...)");
                String string107 = context.getString(R.string.title_diy_video);
                Intrinsics.checkNotNullExpressionValue(string107, "getString(...)");
                String string108 = context.getString(R.string.title_control);
                Intrinsics.checkNotNullExpressionValue(string108, "getString(...)");
                String string109 = context.getString(R.string.title_rhythm);
                Intrinsics.checkNotNullExpressionValue(string109, "getString(...)");
                String string110 = context.getString(R.string.title_colock);
                Intrinsics.checkNotNullExpressionValue(string110, "getString(...)");
                String string111 = context.getString(R.string.img_txt_combination);
                Intrinsics.checkNotNullExpressionValue(string111, "getString(...)");
                String string112 = context.getString(R.string.title_settings);
                Intrinsics.checkNotNullExpressionValue(string112, "getString(...)");
                arrayList2 = CollectionsKt.mutableListOf(string102, string103, string104, string105, string106, string107, string108, string109, string110, string111, string112);
                i = R.array.category_bt_ble_zone;
                break;
            case 16:
                String string113 = context.getString(R.string.title_text);
                Intrinsics.checkNotNullExpressionValue(string113, "getString(...)");
                String string114 = context.getString(R.string.partition_display);
                Intrinsics.checkNotNullExpressionValue(string114, "getString(...)");
                String string115 = context.getString(R.string.title_gallery);
                Intrinsics.checkNotNullExpressionValue(string115, "getString(...)");
                String string116 = context.getString(R.string.title_diy_video);
                Intrinsics.checkNotNullExpressionValue(string116, "getString(...)");
                String string117 = context.getString(R.string.title_channel_list);
                Intrinsics.checkNotNullExpressionValue(string117, "getString(...)");
                String string118 = context.getString(R.string.title_control);
                Intrinsics.checkNotNullExpressionValue(string118, "getString(...)");
                String string119 = context.getString(R.string.title_colock);
                Intrinsics.checkNotNullExpressionValue(string119, "getString(...)");
                String string120 = context.getString(R.string.img_txt_combination);
                Intrinsics.checkNotNullExpressionValue(string120, "getString(...)");
                String string121 = context.getString(R.string.title_settings);
                Intrinsics.checkNotNullExpressionValue(string121, "getString(...)");
                arrayList2 = CollectionsKt.mutableListOf(string113, string114, string115, string116, string117, string118, string119, string120, string121);
                i = R.array.category_bt_ble_256;
                break;
            case 17:
            case 18:
            case 19:
                String string122 = context.getString(R.string.title_text);
                Intrinsics.checkNotNullExpressionValue(string122, "getString(...)");
                String string123 = context.getString(R.string.partition_display);
                Intrinsics.checkNotNullExpressionValue(string123, "getString(...)");
                String string124 = context.getString(R.string.title_gallery);
                Intrinsics.checkNotNullExpressionValue(string124, "getString(...)");
                String string125 = context.getString(R.string.title_channel_list);
                Intrinsics.checkNotNullExpressionValue(string125, "getString(...)");
                String string126 = context.getString(R.string.title_control);
                Intrinsics.checkNotNullExpressionValue(string126, "getString(...)");
                String string127 = context.getString(R.string.title_colock);
                Intrinsics.checkNotNullExpressionValue(string127, "getString(...)");
                String string128 = context.getString(R.string.img_txt_combination);
                Intrinsics.checkNotNullExpressionValue(string128, "getString(...)");
                String string129 = context.getString(R.string.title_settings);
                Intrinsics.checkNotNullExpressionValue(string129, "getString(...)");
                arrayList2 = CollectionsKt.mutableListOf(string122, string123, string124, string125, string126, string127, string128, string129);
                i = R.array.category_bt_ble_384;
                break;
            default:
                i = 0;
                break;
        }
        int[] resIds = ResourceUtils.getResIds(context, i);
        for (Object obj : arrayList2) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(new CategoryVO((String) obj, resIds[i2]));
            i2 = i3;
        }
        return arrayList;
    }

    public final List<CategoryVO> getGameCategoryData(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        List listOf = CollectionsKt.listOf((Object[]) new String[]{context.getString(R.string.title_game_pong), context.getString(R.string.title_game_tetris), context.getString(R.string.title_game_snake)});
        int[] resIds = ResourceUtils.getResIds(context, R.array.game_category);
        for (Object obj : listOf) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            Intrinsics.checkNotNull(str);
            arrayList.add(new CategoryVO(str, resIds[i]));
            i = i2;
        }
        return arrayList;
    }

    public final List<String> getMultiDeviceList() {
        return CollectionsKt.listOf((Object[]) new String[]{"04", "05", "06", "07", "08", "10", "15", "16", "17", "28", "44"});
    }
}
