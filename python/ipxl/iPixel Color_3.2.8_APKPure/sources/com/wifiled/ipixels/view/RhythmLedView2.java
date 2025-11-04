package com.wifiled.ipixels.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.ComposerKt;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.Command;
import com.wifiled.ipixels.common.MyTimeTask;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class RhythmLedView2 extends ViewGroup {
    public static final int MODE_ERASER = 2;
    public static final int MODE_NO = 0;
    public static final int MODE_PAINT = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 0;
    private static final String TAG = "RhythmLedView";
    int[][] animData;
    private boolean animEnable;
    private boolean flag;
    final Handler handler;
    private int heightCount;
    int heightSize;
    int index;
    private int mode;
    int moveMax;
    int moveYMax;
    private int offset;
    private int orientation;
    private int pointAllLength;
    private int pointLength;
    private int pointMargin;
    private int pointYAllLength;
    private int rhy1Color_1;
    private int rhy1Color_10;
    private int rhy1Color_11;
    private int rhy1Color_2;
    private int rhy1Color_3;
    private int rhy1Color_4;
    private int rhy1Color_5;
    private int rhy1Color_6;
    private int rhy1Color_7;
    private int rhy1Color_8;
    private int rhy1Color_9;
    private int rhy2Color_1;
    private int rhy2Color_10;
    private int rhy2Color_11;
    private int rhy2Color_12;
    private int rhy2Color_13;
    private int rhy2Color_14;
    private int rhy2Color_15;
    private int rhy2Color_16;
    private int rhy2Color_2;
    private int rhy2Color_3;
    private int rhy2Color_4;
    private int rhy2Color_5;
    private int rhy2Color_6;
    private int rhy2Color_7;
    private int rhy2Color_8;
    private int rhy2Color_9;
    private int rhy3Color_1;
    private int rhy3Color_10;
    private int rhy3Color_11;
    private int rhy3Color_12;
    private int rhy3Color_13;
    private int rhy3Color_14;
    private int rhy3Color_15;
    private int rhy3Color_16;
    private int rhy3Color_17;
    private int rhy3Color_18;
    private int rhy3Color_19;
    private int rhy3Color_2;
    private int rhy3Color_20;
    private int rhy3Color_21;
    private int rhy3Color_22;
    private int rhy3Color_23;
    private int rhy3Color_24;
    private int rhy3Color_25;
    private int rhy3Color_26;
    private int rhy3Color_3;
    private int rhy3Color_4;
    private int rhy3Color_5;
    private int rhy3Color_6;
    private int rhy3Color_7;
    private int rhy3Color_8;
    private int rhy3Color_9;
    private int rhy4Color_1;
    private int rhy4Color_10;
    private int rhy4Color_11;
    private int rhy4Color_2;
    private int rhy4Color_3;
    private int rhy4Color_4;
    private int rhy4Color_5;
    private int rhy4Color_6;
    private int rhy4Color_7;
    private int rhy4Color_8;
    private int rhy4Color_9;
    private int rhy5Color_1;
    private int rhy5Color_10;
    private int rhy5Color_11;
    private int rhy5Color_12;
    private int rhy5Color_13;
    private int rhy5Color_14;
    private int rhy5Color_15;
    private int rhy5Color_16;
    private int rhy5Color_17;
    private int rhy5Color_18;
    private int rhy5Color_19;
    private int rhy5Color_2;
    private int rhy5Color_20;
    private int rhy5Color_21;
    private int rhy5Color_22;
    private int rhy5Color_23;
    private int rhy5Color_24;
    private int rhy5Color_25;
    private int rhy5Color_26;
    private int rhy5Color_27;
    private int rhy5Color_28;
    private int rhy5Color_29;
    private int rhy5Color_3;
    private int rhy5Color_30;
    private int rhy5Color_31;
    private int rhy5Color_32;
    private int rhy5Color_4;
    private int rhy5Color_5;
    private int rhy5Color_6;
    private int rhy5Color_7;
    private int rhy5Color_8;
    private int rhy5Color_9;
    private int selectedColor1;
    private int selectedColor2;
    private int selectedColor3;
    private int selectedColor4;
    private int selectedColorGray1;
    private int selectedColorGray2;
    private int selectedColorGray3;
    private MyTimeTask task;
    private Timer timer;
    private int unSelectedColor;
    private int widthCount;
    int widthSize;
    private int xMore;
    private int yMore;

    public RhythmLedView2(Context context) {
        super(context);
        this.widthCount = 5;
        this.heightCount = 10;
        this.pointMargin = 1;
        this.selectedColor1 = Color.argb(89, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor2 = Color.argb(Opcodes.IF_ACMPNE, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor3 = Color.rgb(17, ComposerKt.providerValuesKey, Command.CMD_REBOOT_DEVICE);
        this.selectedColor4 = Color.rgb(255, 255, 255);
        this.selectedColorGray1 = Color.argb(89, 42, 122, Opcodes.D2I);
        this.selectedColorGray2 = Color.argb(Opcodes.IF_ACMPNE, 42, 122, Opcodes.D2I);
        this.selectedColorGray3 = Color.rgb(42, 122, Opcodes.D2I);
        this.unSelectedColor = 0;
        this.rhy1Color_1 = Color.parseColor("#aa00ff");
        this.rhy1Color_2 = Color.parseColor("#0037ff");
        this.rhy1Color_3 = Color.parseColor("#00c8ff");
        this.rhy1Color_4 = Color.parseColor("#00ffc8");
        this.rhy1Color_5 = Color.parseColor("#00ff66");
        this.rhy1Color_6 = Color.parseColor("#1eff00");
        this.rhy1Color_7 = Color.parseColor("#80ff00");
        this.rhy1Color_8 = Color.parseColor("#eaff00");
        this.rhy1Color_9 = Color.parseColor("#ffc800");
        this.rhy1Color_10 = Color.parseColor("#ff6600");
        this.rhy1Color_11 = Color.parseColor("#ff0000");
        this.rhy2Color_1 = Color.parseColor("#FF1500");
        this.rhy2Color_2 = Color.parseColor("#FF7B00");
        this.rhy2Color_3 = Color.parseColor("#E2FF00");
        this.rhy2Color_4 = Color.parseColor("#4CFF03");
        this.rhy2Color_5 = Color.parseColor("#0FDA28");
        this.rhy2Color_6 = Color.parseColor("#133EC0");
        this.rhy2Color_7 = Color.parseColor("#5B00FF");
        this.rhy2Color_8 = Color.parseColor("#CE00FF");
        this.rhy2Color_9 = Color.parseColor("#ff5600");
        this.rhy2Color_10 = Color.parseColor("#ffe000");
        this.rhy2Color_11 = Color.parseColor("#9bff00");
        this.rhy2Color_12 = Color.parseColor("#13ff00");
        this.rhy2Color_13 = Color.parseColor("#0c8f71");
        this.rhy2Color_14 = Color.parseColor("#3204fa");
        this.rhy2Color_15 = Color.parseColor("#9500ff");
        this.rhy2Color_16 = Color.parseColor("#f600ff");
        this.rhy3Color_1 = Color.rgb(255, 246, 0);
        this.rhy3Color_2 = Color.rgb(255, Opcodes.FRETURN, 0);
        this.rhy3Color_3 = Color.rgb(255, 78, 0);
        this.rhy3Color_4 = Color.rgb(255, 24, 0);
        this.rhy3Color_5 = Color.rgb(255, 0, 102);
        this.rhy3Color_6 = Color.rgb(255, 0, Opcodes.IF_ICMPGE);
        this.rhy3Color_7 = Color.rgb(234, 0, 255);
        this.rhy3Color_8 = Color.rgb(Opcodes.F2I, 0, 237);
        this.rhy3Color_9 = Color.rgb(78, 0, 255);
        this.rhy3Color_10 = Color.rgb(0, 126, 255);
        this.rhy3Color_11 = Color.rgb(0, 192, 255);
        this.rhy3Color_12 = Color.rgb(0, 240, 255);
        this.rhy3Color_13 = Color.rgb(0, 255, 255);
        this.rhy3Color_14 = Color.rgb(0, 255, 255);
        this.rhy3Color_15 = Color.rgb(0, 240, 255);
        this.rhy3Color_16 = Color.rgb(0, 192, 255);
        this.rhy3Color_17 = Color.rgb(0, 126, 255);
        this.rhy3Color_18 = Color.rgb(78, 0, 255);
        this.rhy3Color_19 = Color.rgb(Opcodes.F2I, 0, 237);
        this.rhy3Color_20 = Color.rgb(234, 0, 255);
        this.rhy3Color_21 = Color.rgb(255, 0, Opcodes.IF_ICMPGE);
        this.rhy3Color_22 = Color.rgb(255, 0, 102);
        this.rhy3Color_23 = Color.rgb(255, 24, 0);
        this.rhy3Color_24 = Color.rgb(255, 78, 0);
        this.rhy3Color_25 = Color.rgb(255, Opcodes.FRETURN, 0);
        this.rhy3Color_26 = Color.rgb(255, 246, 0);
        this.rhy4Color_1 = Color.parseColor("#75FFFF");
        this.rhy4Color_2 = Color.parseColor("#00CCFE");
        this.rhy4Color_3 = Color.parseColor("#0078FD");
        this.rhy4Color_4 = Color.parseColor("#2F00FF");
        this.rhy4Color_5 = Color.parseColor("#6601FF");
        this.rhy4Color_6 = Color.parseColor("#9C01FF");
        this.rhy4Color_7 = Color.parseColor("#FF00D8");
        this.rhy4Color_8 = Color.parseColor("#FF006A");
        this.rhy4Color_9 = Color.parseColor("#FD002C");
        this.rhy4Color_10 = Color.parseColor("#FF7F00");
        this.rhy4Color_11 = Color.parseColor("#FFE100");
        this.rhy5Color_1 = Color.parseColor("#87EB4A");
        this.rhy5Color_2 = Color.parseColor("#85EC47");
        this.rhy5Color_3 = Color.parseColor("#84EA48");
        this.rhy5Color_4 = Color.parseColor("#52EB5D");
        this.rhy5Color_5 = Color.parseColor("#53EC5C");
        this.rhy5Color_6 = Color.parseColor("#53EC5E");
        this.rhy5Color_7 = Color.parseColor("#36E580");
        this.rhy5Color_8 = Color.parseColor("#37E681");
        this.rhy5Color_9 = Color.parseColor("#36E57E");
        this.rhy5Color_10 = Color.parseColor("#0BEAB1");
        this.rhy5Color_11 = Color.parseColor("#0BECB4");
        this.rhy5Color_12 = Color.parseColor("#0BECB4");
        this.rhy5Color_13 = Color.parseColor("#09EBE9");
        this.rhy5Color_14 = Color.parseColor("#0AECEA");
        this.rhy5Color_15 = Color.parseColor("#0BECE8");
        this.rhy5Color_16 = Color.parseColor("#0BCCEB");
        this.rhy5Color_17 = Color.parseColor("#0BCCEB");
        this.rhy5Color_18 = Color.parseColor("#0ACDEB");
        this.rhy5Color_19 = Color.parseColor("#0C70EC");
        this.rhy5Color_20 = Color.parseColor("#0B72EB");
        this.rhy5Color_21 = Color.parseColor("#0B72EB");
        this.rhy5Color_22 = Color.parseColor("#4E31EE");
        this.rhy5Color_23 = Color.parseColor("#4E31EE");
        this.rhy5Color_24 = Color.parseColor("#4E31ED");
        this.rhy5Color_25 = Color.parseColor("#950AE9");
        this.rhy5Color_26 = Color.parseColor("#950AE9");
        this.rhy5Color_27 = Color.parseColor("#950AE9");
        this.rhy5Color_28 = Color.parseColor("#D209EB");
        this.rhy5Color_29 = Color.parseColor("#D10AED");
        this.rhy5Color_30 = Color.parseColor("#D20BEC");
        this.rhy5Color_31 = Color.parseColor("#EE15EA");
        this.rhy5Color_32 = Color.parseColor("#E917EC");
        this.index = 0;
        this.handler = new Handler() { // from class: com.wifiled.ipixels.view.RhythmLedView2.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == 1001) {
                    RhythmLedView2 rhythmLedView2 = RhythmLedView2.this;
                    rhythmLedView2.showImage(rhythmLedView2.animData);
                }
                super.handleMessage(msg);
            }
        };
    }

    public RhythmLedView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.widthCount = 5;
        this.heightCount = 10;
        this.pointMargin = 1;
        this.selectedColor1 = Color.argb(89, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor2 = Color.argb(Opcodes.IF_ACMPNE, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor3 = Color.rgb(17, ComposerKt.providerValuesKey, Command.CMD_REBOOT_DEVICE);
        this.selectedColor4 = Color.rgb(255, 255, 255);
        this.selectedColorGray1 = Color.argb(89, 42, 122, Opcodes.D2I);
        this.selectedColorGray2 = Color.argb(Opcodes.IF_ACMPNE, 42, 122, Opcodes.D2I);
        this.selectedColorGray3 = Color.rgb(42, 122, Opcodes.D2I);
        this.unSelectedColor = 0;
        this.rhy1Color_1 = Color.parseColor("#aa00ff");
        this.rhy1Color_2 = Color.parseColor("#0037ff");
        this.rhy1Color_3 = Color.parseColor("#00c8ff");
        this.rhy1Color_4 = Color.parseColor("#00ffc8");
        this.rhy1Color_5 = Color.parseColor("#00ff66");
        this.rhy1Color_6 = Color.parseColor("#1eff00");
        this.rhy1Color_7 = Color.parseColor("#80ff00");
        this.rhy1Color_8 = Color.parseColor("#eaff00");
        this.rhy1Color_9 = Color.parseColor("#ffc800");
        this.rhy1Color_10 = Color.parseColor("#ff6600");
        this.rhy1Color_11 = Color.parseColor("#ff0000");
        this.rhy2Color_1 = Color.parseColor("#FF1500");
        this.rhy2Color_2 = Color.parseColor("#FF7B00");
        this.rhy2Color_3 = Color.parseColor("#E2FF00");
        this.rhy2Color_4 = Color.parseColor("#4CFF03");
        this.rhy2Color_5 = Color.parseColor("#0FDA28");
        this.rhy2Color_6 = Color.parseColor("#133EC0");
        this.rhy2Color_7 = Color.parseColor("#5B00FF");
        this.rhy2Color_8 = Color.parseColor("#CE00FF");
        this.rhy2Color_9 = Color.parseColor("#ff5600");
        this.rhy2Color_10 = Color.parseColor("#ffe000");
        this.rhy2Color_11 = Color.parseColor("#9bff00");
        this.rhy2Color_12 = Color.parseColor("#13ff00");
        this.rhy2Color_13 = Color.parseColor("#0c8f71");
        this.rhy2Color_14 = Color.parseColor("#3204fa");
        this.rhy2Color_15 = Color.parseColor("#9500ff");
        this.rhy2Color_16 = Color.parseColor("#f600ff");
        this.rhy3Color_1 = Color.rgb(255, 246, 0);
        this.rhy3Color_2 = Color.rgb(255, Opcodes.FRETURN, 0);
        this.rhy3Color_3 = Color.rgb(255, 78, 0);
        this.rhy3Color_4 = Color.rgb(255, 24, 0);
        this.rhy3Color_5 = Color.rgb(255, 0, 102);
        this.rhy3Color_6 = Color.rgb(255, 0, Opcodes.IF_ICMPGE);
        this.rhy3Color_7 = Color.rgb(234, 0, 255);
        this.rhy3Color_8 = Color.rgb(Opcodes.F2I, 0, 237);
        this.rhy3Color_9 = Color.rgb(78, 0, 255);
        this.rhy3Color_10 = Color.rgb(0, 126, 255);
        this.rhy3Color_11 = Color.rgb(0, 192, 255);
        this.rhy3Color_12 = Color.rgb(0, 240, 255);
        this.rhy3Color_13 = Color.rgb(0, 255, 255);
        this.rhy3Color_14 = Color.rgb(0, 255, 255);
        this.rhy3Color_15 = Color.rgb(0, 240, 255);
        this.rhy3Color_16 = Color.rgb(0, 192, 255);
        this.rhy3Color_17 = Color.rgb(0, 126, 255);
        this.rhy3Color_18 = Color.rgb(78, 0, 255);
        this.rhy3Color_19 = Color.rgb(Opcodes.F2I, 0, 237);
        this.rhy3Color_20 = Color.rgb(234, 0, 255);
        this.rhy3Color_21 = Color.rgb(255, 0, Opcodes.IF_ICMPGE);
        this.rhy3Color_22 = Color.rgb(255, 0, 102);
        this.rhy3Color_23 = Color.rgb(255, 24, 0);
        this.rhy3Color_24 = Color.rgb(255, 78, 0);
        this.rhy3Color_25 = Color.rgb(255, Opcodes.FRETURN, 0);
        this.rhy3Color_26 = Color.rgb(255, 246, 0);
        this.rhy4Color_1 = Color.parseColor("#75FFFF");
        this.rhy4Color_2 = Color.parseColor("#00CCFE");
        this.rhy4Color_3 = Color.parseColor("#0078FD");
        this.rhy4Color_4 = Color.parseColor("#2F00FF");
        this.rhy4Color_5 = Color.parseColor("#6601FF");
        this.rhy4Color_6 = Color.parseColor("#9C01FF");
        this.rhy4Color_7 = Color.parseColor("#FF00D8");
        this.rhy4Color_8 = Color.parseColor("#FF006A");
        this.rhy4Color_9 = Color.parseColor("#FD002C");
        this.rhy4Color_10 = Color.parseColor("#FF7F00");
        this.rhy4Color_11 = Color.parseColor("#FFE100");
        this.rhy5Color_1 = Color.parseColor("#87EB4A");
        this.rhy5Color_2 = Color.parseColor("#85EC47");
        this.rhy5Color_3 = Color.parseColor("#84EA48");
        this.rhy5Color_4 = Color.parseColor("#52EB5D");
        this.rhy5Color_5 = Color.parseColor("#53EC5C");
        this.rhy5Color_6 = Color.parseColor("#53EC5E");
        this.rhy5Color_7 = Color.parseColor("#36E580");
        this.rhy5Color_8 = Color.parseColor("#37E681");
        this.rhy5Color_9 = Color.parseColor("#36E57E");
        this.rhy5Color_10 = Color.parseColor("#0BEAB1");
        this.rhy5Color_11 = Color.parseColor("#0BECB4");
        this.rhy5Color_12 = Color.parseColor("#0BECB4");
        this.rhy5Color_13 = Color.parseColor("#09EBE9");
        this.rhy5Color_14 = Color.parseColor("#0AECEA");
        this.rhy5Color_15 = Color.parseColor("#0BECE8");
        this.rhy5Color_16 = Color.parseColor("#0BCCEB");
        this.rhy5Color_17 = Color.parseColor("#0BCCEB");
        this.rhy5Color_18 = Color.parseColor("#0ACDEB");
        this.rhy5Color_19 = Color.parseColor("#0C70EC");
        this.rhy5Color_20 = Color.parseColor("#0B72EB");
        this.rhy5Color_21 = Color.parseColor("#0B72EB");
        this.rhy5Color_22 = Color.parseColor("#4E31EE");
        this.rhy5Color_23 = Color.parseColor("#4E31EE");
        this.rhy5Color_24 = Color.parseColor("#4E31ED");
        this.rhy5Color_25 = Color.parseColor("#950AE9");
        this.rhy5Color_26 = Color.parseColor("#950AE9");
        this.rhy5Color_27 = Color.parseColor("#950AE9");
        this.rhy5Color_28 = Color.parseColor("#D209EB");
        this.rhy5Color_29 = Color.parseColor("#D10AED");
        this.rhy5Color_30 = Color.parseColor("#D20BEC");
        this.rhy5Color_31 = Color.parseColor("#EE15EA");
        this.rhy5Color_32 = Color.parseColor("#E917EC");
        this.index = 0;
        this.handler = new Handler() { // from class: com.wifiled.ipixels.view.RhythmLedView2.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == 1001) {
                    RhythmLedView2 rhythmLedView2 = RhythmLedView2.this;
                    rhythmLedView2.showImage(rhythmLedView2.animData);
                }
                super.handleMessage(msg);
            }
        };
    }

    public RhythmLedView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.widthCount = 5;
        this.heightCount = 10;
        this.pointMargin = 1;
        this.selectedColor1 = Color.argb(89, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor2 = Color.argb(Opcodes.IF_ACMPNE, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor3 = Color.rgb(17, ComposerKt.providerValuesKey, Command.CMD_REBOOT_DEVICE);
        this.selectedColor4 = Color.rgb(255, 255, 255);
        this.selectedColorGray1 = Color.argb(89, 42, 122, Opcodes.D2I);
        this.selectedColorGray2 = Color.argb(Opcodes.IF_ACMPNE, 42, 122, Opcodes.D2I);
        this.selectedColorGray3 = Color.rgb(42, 122, Opcodes.D2I);
        this.unSelectedColor = 0;
        this.rhy1Color_1 = Color.parseColor("#aa00ff");
        this.rhy1Color_2 = Color.parseColor("#0037ff");
        this.rhy1Color_3 = Color.parseColor("#00c8ff");
        this.rhy1Color_4 = Color.parseColor("#00ffc8");
        this.rhy1Color_5 = Color.parseColor("#00ff66");
        this.rhy1Color_6 = Color.parseColor("#1eff00");
        this.rhy1Color_7 = Color.parseColor("#80ff00");
        this.rhy1Color_8 = Color.parseColor("#eaff00");
        this.rhy1Color_9 = Color.parseColor("#ffc800");
        this.rhy1Color_10 = Color.parseColor("#ff6600");
        this.rhy1Color_11 = Color.parseColor("#ff0000");
        this.rhy2Color_1 = Color.parseColor("#FF1500");
        this.rhy2Color_2 = Color.parseColor("#FF7B00");
        this.rhy2Color_3 = Color.parseColor("#E2FF00");
        this.rhy2Color_4 = Color.parseColor("#4CFF03");
        this.rhy2Color_5 = Color.parseColor("#0FDA28");
        this.rhy2Color_6 = Color.parseColor("#133EC0");
        this.rhy2Color_7 = Color.parseColor("#5B00FF");
        this.rhy2Color_8 = Color.parseColor("#CE00FF");
        this.rhy2Color_9 = Color.parseColor("#ff5600");
        this.rhy2Color_10 = Color.parseColor("#ffe000");
        this.rhy2Color_11 = Color.parseColor("#9bff00");
        this.rhy2Color_12 = Color.parseColor("#13ff00");
        this.rhy2Color_13 = Color.parseColor("#0c8f71");
        this.rhy2Color_14 = Color.parseColor("#3204fa");
        this.rhy2Color_15 = Color.parseColor("#9500ff");
        this.rhy2Color_16 = Color.parseColor("#f600ff");
        this.rhy3Color_1 = Color.rgb(255, 246, 0);
        this.rhy3Color_2 = Color.rgb(255, Opcodes.FRETURN, 0);
        this.rhy3Color_3 = Color.rgb(255, 78, 0);
        this.rhy3Color_4 = Color.rgb(255, 24, 0);
        this.rhy3Color_5 = Color.rgb(255, 0, 102);
        this.rhy3Color_6 = Color.rgb(255, 0, Opcodes.IF_ICMPGE);
        this.rhy3Color_7 = Color.rgb(234, 0, 255);
        this.rhy3Color_8 = Color.rgb(Opcodes.F2I, 0, 237);
        this.rhy3Color_9 = Color.rgb(78, 0, 255);
        this.rhy3Color_10 = Color.rgb(0, 126, 255);
        this.rhy3Color_11 = Color.rgb(0, 192, 255);
        this.rhy3Color_12 = Color.rgb(0, 240, 255);
        this.rhy3Color_13 = Color.rgb(0, 255, 255);
        this.rhy3Color_14 = Color.rgb(0, 255, 255);
        this.rhy3Color_15 = Color.rgb(0, 240, 255);
        this.rhy3Color_16 = Color.rgb(0, 192, 255);
        this.rhy3Color_17 = Color.rgb(0, 126, 255);
        this.rhy3Color_18 = Color.rgb(78, 0, 255);
        this.rhy3Color_19 = Color.rgb(Opcodes.F2I, 0, 237);
        this.rhy3Color_20 = Color.rgb(234, 0, 255);
        this.rhy3Color_21 = Color.rgb(255, 0, Opcodes.IF_ICMPGE);
        this.rhy3Color_22 = Color.rgb(255, 0, 102);
        this.rhy3Color_23 = Color.rgb(255, 24, 0);
        this.rhy3Color_24 = Color.rgb(255, 78, 0);
        this.rhy3Color_25 = Color.rgb(255, Opcodes.FRETURN, 0);
        this.rhy3Color_26 = Color.rgb(255, 246, 0);
        this.rhy4Color_1 = Color.parseColor("#75FFFF");
        this.rhy4Color_2 = Color.parseColor("#00CCFE");
        this.rhy4Color_3 = Color.parseColor("#0078FD");
        this.rhy4Color_4 = Color.parseColor("#2F00FF");
        this.rhy4Color_5 = Color.parseColor("#6601FF");
        this.rhy4Color_6 = Color.parseColor("#9C01FF");
        this.rhy4Color_7 = Color.parseColor("#FF00D8");
        this.rhy4Color_8 = Color.parseColor("#FF006A");
        this.rhy4Color_9 = Color.parseColor("#FD002C");
        this.rhy4Color_10 = Color.parseColor("#FF7F00");
        this.rhy4Color_11 = Color.parseColor("#FFE100");
        this.rhy5Color_1 = Color.parseColor("#87EB4A");
        this.rhy5Color_2 = Color.parseColor("#85EC47");
        this.rhy5Color_3 = Color.parseColor("#84EA48");
        this.rhy5Color_4 = Color.parseColor("#52EB5D");
        this.rhy5Color_5 = Color.parseColor("#53EC5C");
        this.rhy5Color_6 = Color.parseColor("#53EC5E");
        this.rhy5Color_7 = Color.parseColor("#36E580");
        this.rhy5Color_8 = Color.parseColor("#37E681");
        this.rhy5Color_9 = Color.parseColor("#36E57E");
        this.rhy5Color_10 = Color.parseColor("#0BEAB1");
        this.rhy5Color_11 = Color.parseColor("#0BECB4");
        this.rhy5Color_12 = Color.parseColor("#0BECB4");
        this.rhy5Color_13 = Color.parseColor("#09EBE9");
        this.rhy5Color_14 = Color.parseColor("#0AECEA");
        this.rhy5Color_15 = Color.parseColor("#0BECE8");
        this.rhy5Color_16 = Color.parseColor("#0BCCEB");
        this.rhy5Color_17 = Color.parseColor("#0BCCEB");
        this.rhy5Color_18 = Color.parseColor("#0ACDEB");
        this.rhy5Color_19 = Color.parseColor("#0C70EC");
        this.rhy5Color_20 = Color.parseColor("#0B72EB");
        this.rhy5Color_21 = Color.parseColor("#0B72EB");
        this.rhy5Color_22 = Color.parseColor("#4E31EE");
        this.rhy5Color_23 = Color.parseColor("#4E31EE");
        this.rhy5Color_24 = Color.parseColor("#4E31ED");
        this.rhy5Color_25 = Color.parseColor("#950AE9");
        this.rhy5Color_26 = Color.parseColor("#950AE9");
        this.rhy5Color_27 = Color.parseColor("#950AE9");
        this.rhy5Color_28 = Color.parseColor("#D209EB");
        this.rhy5Color_29 = Color.parseColor("#D10AED");
        this.rhy5Color_30 = Color.parseColor("#D20BEC");
        this.rhy5Color_31 = Color.parseColor("#EE15EA");
        this.rhy5Color_32 = Color.parseColor("#E917EC");
        this.index = 0;
        this.handler = new Handler() { // from class: com.wifiled.ipixels.view.RhythmLedView2.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == 1001) {
                    RhythmLedView2 rhythmLedView2 = RhythmLedView2.this;
                    rhythmLedView2.showImage(rhythmLedView2.animData);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void init(int widthCount, int heightCount) {
        this.widthCount = widthCount;
        this.heightCount = heightCount;
        for (int i = 0; i < widthCount * heightCount; i++) {
            LedItemRhyView ledItemRhyView = new LedItemRhyView(getContext());
            ledItemRhyView.setViewNumber(i);
            ledItemRhyView.setColumnNumber(i / heightCount);
            ledItemRhyView.setRowNumber(i % heightCount);
            ledItemRhyView.setPaint(this.unSelectedColor);
            addView(ledItemRhyView);
        }
        postInvalidate();
    }

    public void init(int widthCount, int heightCount, float strokeWidth) {
        this.widthCount = widthCount;
        this.heightCount = heightCount;
        for (int i = 0; i < widthCount * heightCount; i++) {
            LedItemRhyView ledItemRhyView = new LedItemRhyView(getContext());
            ledItemRhyView.setViewNumber(i);
            ledItemRhyView.setColumnNumber(i / heightCount);
            ledItemRhyView.setRowNumber(i % heightCount);
            ledItemRhyView.setPaint(this.unSelectedColor, strokeWidth);
            addView(ledItemRhyView);
        }
        postInvalidate();
    }

    public void removeAllChildView() {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                removeViewAt(0);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        this.widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        this.heightSize = size;
        int i = this.widthSize;
        int i2 = this.widthCount;
        int i3 = i % i2;
        this.xMore = i3;
        int i4 = this.heightCount;
        this.yMore = size % i4;
        int i5 = i / i2;
        this.pointAllLength = i5;
        int i6 = size / i4;
        this.pointYAllLength = i6;
        this.pointLength = i5 - (this.pointMargin * 2);
        this.offset = i3 / 2;
        this.moveMax = (i5 * i4) - size;
        this.moveYMax = (i6 * i2) - i;
        int i7 = i5 * i4;
        this.heightSize = i7;
        setMeasuredDimension(i, i7);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int i2 = this.heightCount;
            int i3 = this.pointAllLength;
            int i4 = this.pointMargin;
            int i5 = ((i / i2) * i3) + i4 + this.offset;
            int i6 = ((i % i2) * i3) + i4;
            int i7 = this.pointLength;
            childAt.layout(i5, i6, i5 + i7, i7 + i6);
        }
    }

    public void clearSelected() {
        for (int i = 0; i < getChildCount(); i++) {
            LedItemRhyView ledItemRhyView = (LedItemRhyView) getChildAt(i);
            ledItemRhyView.setChecked(false);
            ledItemRhyView.setPaint(this.unSelectedColor);
        }
        invalidate();
    }

    public void setData(byte[] data) {
        if (data == null) {
            return;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LedItemRhyView ledItemRhyView = (LedItemRhyView) getChildAt(i);
            if (data[i] == 1) {
                ledItemRhyView.setChecked(true);
                ledItemRhyView.setPaint(this.selectedColor3);
            } else {
                ledItemRhyView.setChecked(false);
                ledItemRhyView.setPaint(this.unSelectedColor);
            }
            ledItemRhyView.postInvalidate();
        }
    }

    public void setData(List<Integer> data) {
        if (data == null) {
            return;
        }
        getChildCount();
        for (int i = 0; i < data.size(); i++) {
            LedItemRhyView ledItemRhyView = (LedItemRhyView) getChildAt(i);
            if (data.get(i).intValue() == 1) {
                ledItemRhyView.setChecked(true);
                ledItemRhyView.setPaint(this.selectedColor3);
            } else {
                ledItemRhyView.setChecked(false);
                ledItemRhyView.setPaint(this.unSelectedColor);
            }
            ledItemRhyView.postInvalidate();
        }
    }

    private LedItemRhyView getLedItemRhyView(int cloum, int row) {
        return (LedItemRhyView) getChildAt((cloum * 8) + (8 - row));
    }

    private LedItemRhyView getLedItemRhyView16(int cloum, int row) {
        int i = this.heightCount;
        return (LedItemRhyView) getChildAt((cloum * i) + (i - row));
    }

    public void updateRhythmUI(final List<Integer> rhyDataList) {
        if (rhyDataList != null) {
            clearSelected();
            for (int i = 1; i <= 21 && rhyDataList.size() - 1 >= i; i++) {
                int intValue = rhyDataList.get(i).intValue() / 3;
                if (intValue <= 0) {
                    intValue = 2;
                }
                if (intValue > 8) {
                    intValue = 8;
                }
                for (int i2 = 1; i2 <= intValue; i2++) {
                    int i3 = i * 3;
                    int i4 = i3 - 2;
                    LedItemRhyView ledItemRhyView16 = getLedItemRhyView16(i3 - 1, i2);
                    LedItemRhyView ledItemRhyView162 = getLedItemRhyView16(i4, i2);
                    if (ledItemRhyView16 != null && ledItemRhyView162 != null) {
                        ledItemRhyView16.setChecked(true);
                        ledItemRhyView162.setChecked(true);
                        switch (i) {
                            case 1:
                            case 21:
                                ledItemRhyView16.setPaint(this.rhy1Color_1);
                                ledItemRhyView162.setPaint(this.rhy1Color_1);
                                break;
                            case 2:
                            case 20:
                                ledItemRhyView16.setPaint(this.rhy1Color_2);
                                ledItemRhyView162.setPaint(this.rhy1Color_2);
                                break;
                            case 3:
                            case 19:
                                ledItemRhyView16.setPaint(this.rhy1Color_3);
                                ledItemRhyView162.setPaint(this.rhy1Color_3);
                                break;
                            case 4:
                            case 18:
                                ledItemRhyView16.setPaint(this.rhy1Color_4);
                                ledItemRhyView162.setPaint(this.rhy1Color_4);
                                break;
                            case 5:
                            case 17:
                                ledItemRhyView16.setPaint(this.rhy1Color_5);
                                ledItemRhyView162.setPaint(this.rhy1Color_5);
                                break;
                            case 6:
                            case 16:
                                ledItemRhyView16.setPaint(this.rhy1Color_6);
                                ledItemRhyView162.setPaint(this.rhy1Color_6);
                                break;
                            case 7:
                            case 15:
                                ledItemRhyView16.setPaint(this.rhy1Color_7);
                                ledItemRhyView162.setPaint(this.rhy1Color_7);
                                break;
                            case 8:
                            case 14:
                                ledItemRhyView16.setPaint(this.rhy1Color_8);
                                ledItemRhyView162.setPaint(this.rhy1Color_8);
                                break;
                            case 9:
                            case 13:
                                ledItemRhyView16.setPaint(this.rhy1Color_9);
                                ledItemRhyView162.setPaint(this.rhy1Color_9);
                                break;
                            case 10:
                            case 12:
                                ledItemRhyView16.setPaint(this.rhy1Color_10);
                                ledItemRhyView162.setPaint(this.rhy1Color_10);
                                break;
                            case 11:
                                ledItemRhyView16.setPaint(this.rhy1Color_11);
                                ledItemRhyView162.setPaint(this.rhy1Color_11);
                                break;
                        }
                    }
                }
            }
        }
    }

    public void setRhyData2(List<Integer> list) {
        if (list == null) {
            return;
        }
        clearSelected();
        for (int i = 1; i <= 32 && list.size() >= i; i++) {
            int intValue = list.get(i - 1).intValue() / 3;
            if (intValue <= 0) {
                intValue = 1;
            }
            if (intValue > 8) {
                intValue = 8;
            }
            for (int i2 = 1; i2 <= intValue; i2++) {
                int i3 = i * 2;
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i3 - 1, i2);
                LedItemRhyView ledItemRhyView2 = getLedItemRhyView(i3, i2);
                if (ledItemRhyView == null || ledItemRhyView2 == null) {
                    return;
                }
                ledItemRhyView.setChecked(true);
                ledItemRhyView2.setChecked(true);
                ledItemRhyView.setPaint(this.rhy2Color_16);
                ledItemRhyView2.setPaint(this.rhy2Color_16);
                switch (i2) {
                    case 1:
                        ledItemRhyView.setPaint(this.rhy2Color_1);
                        ledItemRhyView2.setPaint(this.rhy2Color_1);
                        break;
                    case 2:
                        ledItemRhyView.setPaint(this.rhy2Color_2);
                        ledItemRhyView2.setPaint(this.rhy2Color_2);
                        break;
                    case 3:
                        ledItemRhyView.setPaint(this.rhy2Color_3);
                        ledItemRhyView2.setPaint(this.rhy2Color_3);
                        break;
                    case 4:
                        ledItemRhyView.setPaint(this.rhy2Color_4);
                        ledItemRhyView2.setPaint(this.rhy2Color_4);
                        break;
                    case 5:
                        ledItemRhyView.setPaint(this.rhy2Color_5);
                        ledItemRhyView2.setPaint(this.rhy2Color_5);
                        break;
                    case 6:
                        ledItemRhyView.setPaint(this.rhy2Color_6);
                        ledItemRhyView2.setPaint(this.rhy2Color_6);
                        break;
                    case 7:
                        ledItemRhyView.setPaint(this.rhy2Color_7);
                        ledItemRhyView2.setPaint(this.rhy2Color_7);
                        break;
                    case 8:
                        ledItemRhyView.setPaint(this.rhy2Color_8);
                        ledItemRhyView2.setPaint(this.rhy2Color_8);
                        break;
                    case 9:
                        ledItemRhyView.setPaint(this.rhy2Color_9);
                        ledItemRhyView2.setPaint(this.rhy2Color_9);
                        break;
                    case 10:
                        ledItemRhyView.setPaint(this.rhy2Color_10);
                        ledItemRhyView2.setPaint(this.rhy2Color_10);
                        break;
                    case 11:
                        ledItemRhyView.setPaint(this.rhy2Color_11);
                        ledItemRhyView2.setPaint(this.rhy2Color_11);
                        break;
                    case 12:
                        ledItemRhyView.setPaint(this.rhy2Color_12);
                        ledItemRhyView2.setPaint(this.rhy2Color_12);
                        break;
                    case 13:
                        ledItemRhyView.setPaint(this.rhy2Color_13);
                        ledItemRhyView2.setPaint(this.rhy2Color_13);
                        break;
                    case 14:
                        ledItemRhyView.setPaint(this.rhy2Color_14);
                        ledItemRhyView2.setPaint(this.rhy2Color_14);
                        break;
                    case 15:
                        ledItemRhyView.setPaint(this.rhy2Color_15);
                        ledItemRhyView2.setPaint(this.rhy2Color_15);
                        break;
                    case 16:
                        ledItemRhyView.setPaint(this.rhy2Color_16);
                        ledItemRhyView2.setPaint(this.rhy2Color_16);
                        break;
                    case 17:
                        ledItemRhyView.setPaint(this.rhy2Color_16);
                        ledItemRhyView2.setPaint(this.rhy2Color_16);
                        break;
                    case 18:
                        ledItemRhyView.setPaint(this.rhy2Color_15);
                        ledItemRhyView2.setPaint(this.rhy2Color_15);
                        break;
                    case 19:
                        ledItemRhyView.setPaint(this.rhy2Color_14);
                        ledItemRhyView2.setPaint(this.rhy2Color_14);
                        break;
                    case 20:
                        ledItemRhyView.setPaint(this.rhy2Color_13);
                        ledItemRhyView2.setPaint(this.rhy2Color_13);
                        break;
                    case 21:
                        ledItemRhyView.setPaint(this.rhy2Color_12);
                        ledItemRhyView2.setPaint(this.rhy2Color_12);
                        break;
                    case 22:
                        ledItemRhyView.setPaint(this.rhy2Color_11);
                        ledItemRhyView2.setPaint(this.rhy2Color_11);
                        break;
                    case 23:
                        ledItemRhyView.setPaint(this.rhy2Color_10);
                        ledItemRhyView2.setPaint(this.rhy2Color_10);
                        break;
                    case 24:
                        ledItemRhyView.setPaint(this.rhy2Color_9);
                        ledItemRhyView2.setPaint(this.rhy2Color_9);
                        break;
                    case 25:
                        ledItemRhyView.setPaint(this.rhy2Color_8);
                        ledItemRhyView2.setPaint(this.rhy2Color_8);
                        break;
                    case 26:
                        ledItemRhyView.setPaint(this.rhy2Color_7);
                        ledItemRhyView2.setPaint(this.rhy2Color_7);
                        break;
                    case 27:
                        ledItemRhyView.setPaint(this.rhy2Color_6);
                        ledItemRhyView2.setPaint(this.rhy2Color_6);
                        break;
                    case 28:
                        ledItemRhyView.setPaint(this.rhy2Color_5);
                        ledItemRhyView2.setPaint(this.rhy2Color_5);
                        break;
                    case 29:
                        ledItemRhyView.setPaint(this.rhy2Color_4);
                        ledItemRhyView2.setPaint(this.rhy2Color_4);
                        break;
                    case 30:
                        ledItemRhyView.setPaint(this.rhy2Color_3);
                        ledItemRhyView2.setPaint(this.rhy2Color_3);
                        break;
                    case 31:
                        ledItemRhyView.setPaint(this.rhy2Color_2);
                        ledItemRhyView2.setPaint(this.rhy2Color_2);
                        break;
                    case 32:
                        ledItemRhyView.setPaint(this.rhy2Color_1);
                        ledItemRhyView2.setPaint(this.rhy2Color_1);
                        break;
                    default:
                        ledItemRhyView.setPaint(this.rhy2Color_16);
                        ledItemRhyView2.setPaint(this.rhy2Color_16);
                        break;
                }
            }
        }
    }

    public void setRhyData3(List<Integer> list) {
        if (list == null) {
            return;
        }
        clearSelected();
        for (int i = 1; i <= 21 && list.size() >= i; i++) {
            int intValue = list.get(i - 1).intValue() / 3;
            if (intValue <= 0) {
                intValue = 1;
            }
            if (intValue > 8) {
                intValue = 8;
            }
            for (int i2 = 1; i2 <= intValue; i2++) {
                int i3 = i * 3;
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i3 - 2, i2);
                LedItemRhyView ledItemRhyView2 = getLedItemRhyView(i3 - 1, i2);
                ledItemRhyView.setChecked(true);
                ledItemRhyView2.setChecked(true);
                switch (i) {
                    case 1:
                        ledItemRhyView.setPaint(this.rhy4Color_11);
                        ledItemRhyView2.setPaint(this.rhy4Color_11);
                        break;
                    case 2:
                        ledItemRhyView.setPaint(this.rhy4Color_10);
                        ledItemRhyView2.setPaint(this.rhy4Color_10);
                        break;
                    case 3:
                        ledItemRhyView.setPaint(this.rhy4Color_9);
                        ledItemRhyView2.setPaint(this.rhy4Color_9);
                        break;
                    case 4:
                        ledItemRhyView.setPaint(this.rhy4Color_8);
                        ledItemRhyView2.setPaint(this.rhy4Color_8);
                        break;
                    case 5:
                        ledItemRhyView.setPaint(this.rhy4Color_7);
                        ledItemRhyView2.setPaint(this.rhy4Color_7);
                        break;
                    case 6:
                        ledItemRhyView.setPaint(this.rhy4Color_6);
                        ledItemRhyView2.setPaint(this.rhy4Color_6);
                        break;
                    case 7:
                        ledItemRhyView.setPaint(this.rhy4Color_5);
                        ledItemRhyView2.setPaint(this.rhy4Color_5);
                        break;
                    case 8:
                        ledItemRhyView.setPaint(this.rhy4Color_4);
                        ledItemRhyView2.setPaint(this.rhy4Color_4);
                        break;
                    case 9:
                        ledItemRhyView.setPaint(this.rhy4Color_3);
                        ledItemRhyView2.setPaint(this.rhy4Color_3);
                        break;
                    case 10:
                        ledItemRhyView.setPaint(this.rhy4Color_2);
                        ledItemRhyView2.setPaint(this.rhy4Color_2);
                        break;
                    case 11:
                        ledItemRhyView.setPaint(this.rhy4Color_1);
                        ledItemRhyView2.setPaint(this.rhy4Color_1);
                        break;
                    case 12:
                        ledItemRhyView.setPaint(this.rhy4Color_2);
                        ledItemRhyView2.setPaint(this.rhy4Color_2);
                        break;
                    case 13:
                        ledItemRhyView.setPaint(this.rhy4Color_3);
                        ledItemRhyView2.setPaint(this.rhy4Color_3);
                        break;
                    case 14:
                        ledItemRhyView.setPaint(this.rhy4Color_4);
                        ledItemRhyView2.setPaint(this.rhy4Color_4);
                        break;
                    case 15:
                        ledItemRhyView.setPaint(this.rhy4Color_5);
                        ledItemRhyView2.setPaint(this.rhy4Color_5);
                        break;
                    case 16:
                        ledItemRhyView.setPaint(this.rhy4Color_6);
                        ledItemRhyView2.setPaint(this.rhy4Color_6);
                        break;
                    case 17:
                        ledItemRhyView.setPaint(this.rhy4Color_7);
                        ledItemRhyView2.setPaint(this.rhy4Color_7);
                        break;
                    case 18:
                        ledItemRhyView.setPaint(this.rhy4Color_8);
                        ledItemRhyView2.setPaint(this.rhy4Color_8);
                        break;
                    case 19:
                        ledItemRhyView.setPaint(this.rhy4Color_9);
                        ledItemRhyView2.setPaint(this.rhy4Color_9);
                        break;
                    case 20:
                        ledItemRhyView.setPaint(this.rhy4Color_10);
                        ledItemRhyView2.setPaint(this.rhy4Color_10);
                        break;
                    case 21:
                        ledItemRhyView.setPaint(this.rhy4Color_11);
                        ledItemRhyView2.setPaint(this.rhy4Color_11);
                        break;
                }
            }
        }
    }

    public void setRhyData4(List<Integer> list) {
        if (list == null) {
            return;
        }
        clearSelected();
        for (int i = 1; i <= 21 && list.size() >= i; i++) {
            int intValue = list.get(i - 1).intValue();
            if (intValue == 0) {
                intValue = 2;
            }
            if (intValue > 16) {
                intValue = 16;
            }
            for (int i2 = 1; i2 <= intValue; i2++) {
                int i3 = i * 3;
                int i4 = i3 - 2;
                LedItemRhyView ledItemRhyView16 = getLedItemRhyView16(i3 - 1, i2);
                LedItemRhyView ledItemRhyView162 = getLedItemRhyView16(i4, i2);
                ledItemRhyView16.setChecked(true);
                ledItemRhyView162.setChecked(true);
                switch (i) {
                    case 1:
                        ledItemRhyView16.setPaint(this.rhy4Color_1);
                        ledItemRhyView162.setPaint(this.rhy4Color_1);
                        break;
                    case 2:
                        ledItemRhyView16.setPaint(this.rhy4Color_2);
                        ledItemRhyView162.setPaint(this.rhy4Color_2);
                        break;
                    case 3:
                        ledItemRhyView16.setPaint(this.rhy4Color_3);
                        ledItemRhyView162.setPaint(this.rhy4Color_3);
                        break;
                    case 4:
                        ledItemRhyView16.setPaint(this.rhy4Color_4);
                        ledItemRhyView162.setPaint(this.rhy4Color_4);
                        break;
                    case 5:
                        ledItemRhyView16.setPaint(this.rhy4Color_5);
                        ledItemRhyView162.setPaint(this.rhy4Color_5);
                        break;
                    case 6:
                        ledItemRhyView16.setPaint(this.rhy4Color_6);
                        ledItemRhyView162.setPaint(this.rhy4Color_6);
                        break;
                    case 7:
                        ledItemRhyView16.setPaint(this.rhy4Color_7);
                        ledItemRhyView162.setPaint(this.rhy4Color_7);
                        break;
                    case 8:
                        ledItemRhyView16.setPaint(this.rhy4Color_8);
                        ledItemRhyView162.setPaint(this.rhy4Color_8);
                        break;
                    case 9:
                        ledItemRhyView16.setPaint(this.rhy4Color_9);
                        ledItemRhyView162.setPaint(this.rhy4Color_9);
                        break;
                    case 10:
                        ledItemRhyView16.setPaint(this.rhy4Color_10);
                        ledItemRhyView162.setPaint(this.rhy4Color_10);
                        break;
                    case 11:
                        ledItemRhyView16.setPaint(this.rhy4Color_11);
                        ledItemRhyView162.setPaint(this.rhy4Color_11);
                        break;
                    case 12:
                        ledItemRhyView16.setPaint(this.rhy4Color_10);
                        ledItemRhyView162.setPaint(this.rhy4Color_10);
                        break;
                    case 13:
                        ledItemRhyView16.setPaint(this.rhy4Color_9);
                        ledItemRhyView162.setPaint(this.rhy4Color_9);
                        break;
                    case 14:
                        ledItemRhyView16.setPaint(this.rhy4Color_8);
                        ledItemRhyView162.setPaint(this.rhy4Color_8);
                        break;
                    case 15:
                        ledItemRhyView16.setPaint(this.rhy4Color_7);
                        ledItemRhyView162.setPaint(this.rhy4Color_7);
                        break;
                    case 16:
                        ledItemRhyView16.setPaint(this.rhy4Color_6);
                        ledItemRhyView162.setPaint(this.rhy4Color_6);
                        break;
                    case 17:
                        ledItemRhyView16.setPaint(this.rhy4Color_5);
                        ledItemRhyView162.setPaint(this.rhy4Color_5);
                        break;
                    case 18:
                        ledItemRhyView16.setPaint(this.rhy4Color_4);
                        ledItemRhyView162.setPaint(this.rhy4Color_4);
                        break;
                    case 19:
                        ledItemRhyView16.setPaint(this.rhy4Color_3);
                        ledItemRhyView162.setPaint(this.rhy4Color_3);
                        break;
                    case 20:
                        ledItemRhyView16.setPaint(this.rhy4Color_2);
                        ledItemRhyView162.setPaint(this.rhy4Color_2);
                        break;
                    case 21:
                        ledItemRhyView16.setPaint(this.rhy4Color_1);
                        ledItemRhyView162.setPaint(this.rhy4Color_1);
                        break;
                }
            }
        }
    }

    public void setRhyData5(List<Integer> list) {
        if (list == null) {
            return;
        }
        clearSelected();
        int i = 1;
        while (i <= 64 && list.size() - 1 >= i) {
            int intValue = list.get(i).intValue() / 3;
            if (intValue <= 0) {
                intValue = 1;
            }
            if (intValue > 8) {
                intValue = 8;
            }
            for (int i2 = 1; i2 <= intValue; i2++) {
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i >= 32 ? i - 1 : i, i2);
                if (ledItemRhyView == null) {
                    return;
                }
                ledItemRhyView.setChecked(true);
                ledItemRhyView.setPaint(this.rhy1Color_1);
                switch (i) {
                    case 1:
                        ledItemRhyView.setPaint(this.rhy5Color_1);
                        break;
                    case 2:
                        ledItemRhyView.setPaint(this.rhy5Color_2);
                        break;
                    case 3:
                        ledItemRhyView.setPaint(this.rhy5Color_3);
                        break;
                    case 4:
                        ledItemRhyView.setPaint(this.rhy5Color_4);
                        break;
                    case 5:
                        ledItemRhyView.setPaint(this.rhy5Color_5);
                        break;
                    case 6:
                        ledItemRhyView.setPaint(this.rhy5Color_6);
                        break;
                    case 7:
                        ledItemRhyView.setPaint(this.rhy5Color_7);
                        break;
                    case 8:
                        ledItemRhyView.setPaint(this.rhy5Color_8);
                        break;
                    case 9:
                        ledItemRhyView.setPaint(this.rhy5Color_9);
                        break;
                    case 10:
                        ledItemRhyView.setPaint(this.rhy5Color_10);
                        break;
                    case 11:
                        ledItemRhyView.setPaint(this.rhy5Color_11);
                        break;
                    case 12:
                        ledItemRhyView.setPaint(this.rhy5Color_12);
                        break;
                    case 13:
                        ledItemRhyView.setPaint(this.rhy5Color_13);
                        break;
                    case 14:
                        ledItemRhyView.setPaint(this.rhy5Color_14);
                        break;
                    case 15:
                        ledItemRhyView.setPaint(this.rhy5Color_15);
                        break;
                    case 16:
                        ledItemRhyView.setPaint(this.rhy5Color_16);
                        break;
                    case 17:
                        ledItemRhyView.setPaint(this.rhy5Color_17);
                        break;
                    case 18:
                        ledItemRhyView.setPaint(this.rhy5Color_18);
                        break;
                    case 19:
                        ledItemRhyView.setPaint(this.rhy5Color_19);
                        break;
                    case 20:
                        ledItemRhyView.setPaint(this.rhy5Color_20);
                        break;
                    case 21:
                        ledItemRhyView.setPaint(this.rhy5Color_21);
                        break;
                    case 22:
                        ledItemRhyView.setPaint(this.rhy5Color_22);
                        break;
                    case 23:
                        ledItemRhyView.setPaint(this.rhy5Color_23);
                        break;
                    case 24:
                        ledItemRhyView.setPaint(this.rhy5Color_24);
                        break;
                    case 25:
                        ledItemRhyView.setPaint(this.rhy5Color_25);
                        break;
                    case 26:
                        ledItemRhyView.setPaint(this.rhy5Color_26);
                        break;
                    case 27:
                        ledItemRhyView.setPaint(this.rhy5Color_27);
                        break;
                    case 28:
                        ledItemRhyView.setPaint(this.rhy5Color_28);
                        break;
                    case 29:
                        ledItemRhyView.setPaint(this.rhy5Color_29);
                        break;
                    case 30:
                        ledItemRhyView.setPaint(this.rhy5Color_30);
                        break;
                    case 31:
                        ledItemRhyView.setPaint(this.rhy5Color_31);
                        break;
                    case 32:
                        ledItemRhyView.setPaint(this.rhy5Color_32);
                        break;
                    case 33:
                        ledItemRhyView.setPaint(this.rhy5Color_32);
                        break;
                    case 34:
                        ledItemRhyView.setPaint(this.rhy5Color_31);
                        break;
                    case 35:
                        ledItemRhyView.setPaint(this.rhy5Color_30);
                        break;
                    case 36:
                        ledItemRhyView.setPaint(this.rhy5Color_29);
                        break;
                    case 37:
                        ledItemRhyView.setPaint(this.rhy5Color_28);
                        break;
                    case 38:
                        ledItemRhyView.setPaint(this.rhy5Color_27);
                        break;
                    case 39:
                        ledItemRhyView.setPaint(this.rhy5Color_26);
                        break;
                    case 40:
                        ledItemRhyView.setPaint(this.rhy5Color_25);
                        break;
                    case 41:
                        ledItemRhyView.setPaint(this.rhy5Color_24);
                        break;
                    case 42:
                        ledItemRhyView.setPaint(this.rhy5Color_23);
                        break;
                    case 43:
                        ledItemRhyView.setPaint(this.rhy5Color_22);
                        break;
                    case 44:
                        ledItemRhyView.setPaint(this.rhy5Color_21);
                        break;
                    case 45:
                        ledItemRhyView.setPaint(this.rhy5Color_20);
                        break;
                    case 46:
                        ledItemRhyView.setPaint(this.rhy5Color_19);
                        break;
                    case 47:
                        ledItemRhyView.setPaint(this.rhy5Color_18);
                        break;
                    case 48:
                        ledItemRhyView.setPaint(this.rhy5Color_17);
                        break;
                    case 49:
                        ledItemRhyView.setPaint(this.rhy5Color_16);
                        break;
                    case 50:
                        ledItemRhyView.setPaint(this.rhy5Color_15);
                        break;
                    case 51:
                        ledItemRhyView.setPaint(this.rhy5Color_14);
                        break;
                    case 52:
                        ledItemRhyView.setPaint(this.rhy5Color_13);
                        break;
                    case 53:
                        ledItemRhyView.setPaint(this.rhy5Color_12);
                        break;
                    case 54:
                        ledItemRhyView.setPaint(this.rhy5Color_11);
                        break;
                    case 55:
                        ledItemRhyView.setPaint(this.rhy5Color_10);
                        break;
                    case 56:
                        ledItemRhyView.setPaint(this.rhy5Color_9);
                        break;
                    case 57:
                        ledItemRhyView.setPaint(this.rhy5Color_8);
                        break;
                    case 58:
                        ledItemRhyView.setPaint(this.rhy5Color_7);
                        break;
                    case 59:
                        ledItemRhyView.setPaint(this.rhy5Color_6);
                        break;
                    case 60:
                        ledItemRhyView.setPaint(this.rhy5Color_5);
                        break;
                    case 61:
                        ledItemRhyView.setPaint(this.rhy5Color_4);
                        break;
                    case 62:
                        ledItemRhyView.setPaint(this.rhy5Color_3);
                        break;
                    case 63:
                        ledItemRhyView.setPaint(this.rhy5Color_2);
                        break;
                    case 64:
                        ledItemRhyView.setPaint(this.rhy5Color_1);
                        break;
                }
            }
            i++;
        }
    }

    private void showImage(int[][] data, int index) {
        if (index >= data.length) {
            return;
        }
        int[] iArr = data[index];
        for (int i = 0; i < iArr.length; i++) {
            for (int i2 = 0; i2 < 9; i2++) {
                int i3 = (iArr[i] >> (i2 * 2)) & 3;
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i, i2);
                if (ledItemRhyView != null) {
                    if (i3 == 1) {
                        ledItemRhyView.setChecked(true);
                        ledItemRhyView.setPaint(this.selectedColorGray1);
                    } else if (i3 == 2) {
                        ledItemRhyView.setChecked(true);
                        ledItemRhyView.setPaint(this.selectedColorGray2);
                    } else if (i3 == 3) {
                        ledItemRhyView.setChecked(true);
                        ledItemRhyView.setPaint(this.selectedColorGray3);
                    } else {
                        ledItemRhyView.setChecked(false);
                        ledItemRhyView.setPaint(this.unSelectedColor);
                    }
                    ledItemRhyView.postInvalidate();
                }
            }
        }
        this.index++;
    }

    public void setPointMargin(int pointMargin) {
        this.pointMargin = pointMargin;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor3 = selectedColor;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showImage(int[][] data) {
        if (this.index >= data.length) {
            this.index = 0;
        }
        int[] iArr = data[this.index];
        for (int i = 0; i < iArr.length; i++) {
            for (int i2 = 0; i2 < 9; i2++) {
                int i3 = (iArr[i] >> (i2 * 2)) & 3;
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i, i2);
                if (ledItemRhyView != null) {
                    if (i3 == 1) {
                        ledItemRhyView.setChecked(true);
                        ledItemRhyView.setPaint(this.selectedColorGray1);
                    } else if (i3 == 2) {
                        ledItemRhyView.setChecked(true);
                        ledItemRhyView.setPaint(this.selectedColorGray2);
                    } else if (i3 == 3) {
                        ledItemRhyView.setChecked(true);
                        ledItemRhyView.setPaint(this.selectedColorGray3);
                    } else {
                        ledItemRhyView.setChecked(false);
                        ledItemRhyView.setPaint(this.unSelectedColor);
                    }
                    ledItemRhyView.postInvalidate();
                }
            }
        }
        this.index++;
    }

    public void setTimer(boolean enable) {
        if (enable) {
            MyTimeTask myTimeTask = new MyTimeTask(100L, new TimerTask() { // from class: com.wifiled.ipixels.view.RhythmLedView2.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (RhythmLedView2.this.animEnable) {
                        RhythmLedView2.this.handler.sendEmptyMessage(1001);
                    }
                }
            });
            this.task = myTimeTask;
            myTimeTask.start();
            return;
        }
        this.task.stop();
    }

    public void setShowAnim(boolean enable) {
        this.animEnable = enable;
    }

    public boolean getShowAnim() {
        return this.animEnable;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
