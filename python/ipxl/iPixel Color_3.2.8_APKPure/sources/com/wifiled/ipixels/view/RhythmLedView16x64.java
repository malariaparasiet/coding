package com.wifiled.ipixels.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.ComposerKt;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.Command;
import java.util.List;

/* loaded from: classes3.dex */
public class RhythmLedView16x64 extends ViewGroup {
    public static final int MODE_ERASER = 2;
    public static final int MODE_NO = 0;
    public static final int MODE_PAINT = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 0;
    private static final String TAG = "RhythmLedView";
    private int alignment;
    int[][] animData;
    private boolean animEnable;
    private boolean flag;
    private int heightCount;
    int heightSize;
    int index;
    private int mode;
    private int orientation;
    private int pointLength;
    private int pointMargin;
    private int pointXAllLength;
    private int pointYAllLength;
    int[] rhy1ColorArray;
    int[] rhy2ColorArray;
    int[] rhy3ColorArray;
    int[] rhy4ColorArray;
    int[] rhy5ColorArray;
    private int selectedColor1;
    private int selectedColor2;
    private int selectedColor3;
    private int selectedColor4;
    private int selectedColorGray1;
    private int selectedColorGray2;
    private int selectedColorGray3;
    private int unSelectedColor;
    private int widthCount;
    int widthSize;
    private int xMore;
    private int yMore;

    public int getAlignment() {
        return this.alignment;
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public RhythmLedView16x64(Context context) {
        super(context);
        this.widthCount = 64;
        this.heightCount = 8;
        this.pointMargin = 0;
        this.alignment = 0;
        this.unSelectedColor = 0;
        this.selectedColor1 = Color.argb(89, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor2 = Color.argb(Opcodes.IF_ACMPNE, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor3 = Color.rgb(17, ComposerKt.providerValuesKey, Command.CMD_REBOOT_DEVICE);
        this.selectedColor4 = Color.rgb(255, 255, 255);
        this.selectedColorGray1 = Color.argb(89, 42, 122, Opcodes.D2I);
        this.selectedColorGray2 = Color.argb(Opcodes.IF_ACMPNE, 42, 122, Opcodes.D2I);
        this.selectedColorGray3 = Color.rgb(42, 122, Opcodes.D2I);
        this.rhy1ColorArray = new int[]{Color.parseColor("#11ADFF"), Color.parseColor("#11ADFF"), Color.parseColor("#FFFA00"), Color.parseColor("#FFFA00"), Color.parseColor("#FF1D10"), Color.parseColor("#FF1D10"), Color.parseColor("#CB46DF"), Color.parseColor("#CB46DF"), Color.parseColor("#11ADFF"), Color.parseColor("#11ADFF"), Color.parseColor("#7AFF0E"), Color.parseColor("#7AFF0E"), Color.parseColor("#FFFC00"), Color.parseColor("#FFFC00"), Color.parseColor("#FF1D10"), Color.parseColor("#FF1D10")};
        this.rhy2ColorArray = new int[]{Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#9C00FF"), Color.parseColor("#9C00FF"), Color.parseColor("#0001FF"), Color.parseColor("#9C00FF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#9C00FF"), Color.parseColor("#0001FF"), Color.parseColor("#9C00FF"), Color.parseColor("#9C00FF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF")};
        this.rhy3ColorArray = new int[]{Color.parseColor("#74F101"), Color.parseColor("#74F101"), Color.parseColor("#7BF300"), Color.parseColor("#7BF300"), Color.parseColor("#8EF900"), Color.parseColor("#8EF900"), Color.parseColor("#A5FF00"), Color.parseColor("#A5FF00"), Color.parseColor("#BCFF00"), Color.parseColor("#BCFF00"), Color.parseColor("#D5FF00"), Color.parseColor("#D5FF00"), Color.parseColor("#EAFF00"), Color.parseColor("#EAFF00"), Color.parseColor("#FAFF00"), Color.parseColor("#FFF00E"), Color.parseColor("#FFF00E"), Color.parseColor("#FFD525"), Color.parseColor("#FFD525"), Color.parseColor("#FFB241"), Color.parseColor("#FFB241"), Color.parseColor("#FF8C61"), Color.parseColor("#FF8C61"), Color.parseColor("#FF6284"), Color.parseColor("#FF6284"), Color.parseColor("#FF3EA5"), Color.parseColor("#FF3EA5"), Color.parseColor("#FF1FC3"), Color.parseColor("#FF1FC3"), Color.parseColor("#FF09DC"), Color.parseColor("#FF09DC"), Color.parseColor("#FF00EF"), Color.parseColor("#FF00EF"), Color.parseColor("#D716FF"), Color.parseColor("#D716FF"), Color.parseColor("#9554FF"), Color.parseColor("#9554FF"), Color.parseColor("#4AA2FF"), Color.parseColor("#4AA2FF"), Color.parseColor("#12DCFF"), Color.parseColor("#12DCFF"), Color.parseColor("#00EAFF"), Color.parseColor("#00EAFF"), Color.parseColor("#00D8FF"), Color.parseColor("#00D8FF"), Color.parseColor("#0BB5FF"), Color.parseColor("#0BB5FF"), Color.parseColor("#2388FF"), Color.parseColor("#2388FF"), Color.parseColor("#3A63FF"), Color.parseColor("#3A63FF"), Color.parseColor("#4F4AFF"), Color.parseColor("#4F4AFF"), Color.parseColor("#5E49FF"), Color.parseColor("#5E49FF"), Color.parseColor("#6F51FF"), Color.parseColor("#6F51FF"), Color.parseColor("#7D5FFF"), Color.parseColor("#7D5FFF"), Color.parseColor("#8D74FF"), Color.parseColor("#8D74FF"), Color.parseColor("#9B86FF"), Color.parseColor("#9B86FF")};
        this.rhy4ColorArray = new int[]{Color.parseColor("#02CDFF"), Color.parseColor("#02CDFF"), Color.parseColor("#0FB2FF"), Color.parseColor("#0FB2FF"), Color.parseColor("#228DFF"), Color.parseColor("#228DFF"), Color.parseColor("#3566FF"), Color.parseColor("#3566FF"), Color.parseColor("#4A3FFF"), Color.parseColor("#4A3FFF"), Color.parseColor("#611AFF"), Color.parseColor("#7701FF"), Color.parseColor("#8D00FF"), Color.parseColor("#A400FF"), Color.parseColor("#BD00FF"), Color.parseColor("#D400FF"), Color.parseColor("#E600FF"), Color.parseColor("#F700FF"), Color.parseColor("#FF00EF"), Color.parseColor("#FF00C8"), Color.parseColor("#FF0099"), Color.parseColor("#FF0066"), Color.parseColor("#FF0037"), Color.parseColor("#FF0012"), Color.parseColor("#FF0400"), Color.parseColor("#FF1300"), Color.parseColor("#FF2700"), Color.parseColor("#FF3F00"), Color.parseColor("#FF5800"), Color.parseColor("#FF7000"), Color.parseColor("#FF8500"), Color.parseColor("#FF9800"), Color.parseColor("#FFAE00"), Color.parseColor("#FFC300"), Color.parseColor("#FFD600"), Color.parseColor("#FFE600"), Color.parseColor("#FFF400")};
        this.rhy5ColorArray = new int[]{Color.parseColor("#FF4300"), Color.parseColor("#FF4300"), Color.parseColor("#FF4B00"), Color.parseColor("#FF4B00"), Color.parseColor("#FF5E00"), Color.parseColor("#FF5E00"), Color.parseColor("#FF7900"), Color.parseColor("#FF7900"), Color.parseColor("#FF9400"), Color.parseColor("#FF9400"), Color.parseColor("#FFAE00"), Color.parseColor("#FFAE00"), Color.parseColor("#FFC400"), Color.parseColor("#FFC400"), Color.parseColor("#FFCF00"), Color.parseColor("#FFCF00"), Color.parseColor("#FFCD03"), Color.parseColor("#FFCD03"), Color.parseColor("#FFB813"), Color.parseColor("#FFB813"), Color.parseColor("#FF932B"), Color.parseColor("#FF932B"), Color.parseColor("#FF6745"), Color.parseColor("#FF6745"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF1872"), Color.parseColor("#FF1872"), Color.parseColor("#FF037D"), Color.parseColor("#FF037D"), Color.parseColor("#FF017E"), Color.parseColor("#FF017E"), Color.parseColor("#FF017E"), Color.parseColor("#FF017E"), Color.parseColor("#FF037D"), Color.parseColor("#FF037D"), Color.parseColor("#FF1872"), Color.parseColor("#FF1872"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF6745"), Color.parseColor("#FF6745"), Color.parseColor("#FF932B"), Color.parseColor("#FF932B"), Color.parseColor("#FFB813"), Color.parseColor("#FFB813"), Color.parseColor("#FFCD03"), Color.parseColor("#FFCD03"), Color.parseColor("#FFCF00"), Color.parseColor("#FFCF00"), Color.parseColor("#FFC400"), Color.parseColor("#FFC400"), Color.parseColor("#FFAE00"), Color.parseColor("#FFAE00"), Color.parseColor("#FF9400"), Color.parseColor("#FF9400"), Color.parseColor("#FF7900"), Color.parseColor("#FF7900"), Color.parseColor("#FF5E00"), Color.parseColor("#FF5E00"), Color.parseColor("#FF4B00"), Color.parseColor("#FF4B00"), Color.parseColor("#FF4300"), Color.parseColor("#FF4300")};
        this.index = 0;
    }

    public RhythmLedView16x64(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.widthCount = 64;
        this.heightCount = 8;
        this.pointMargin = 0;
        this.alignment = 0;
        this.unSelectedColor = 0;
        this.selectedColor1 = Color.argb(89, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor2 = Color.argb(Opcodes.IF_ACMPNE, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor3 = Color.rgb(17, ComposerKt.providerValuesKey, Command.CMD_REBOOT_DEVICE);
        this.selectedColor4 = Color.rgb(255, 255, 255);
        this.selectedColorGray1 = Color.argb(89, 42, 122, Opcodes.D2I);
        this.selectedColorGray2 = Color.argb(Opcodes.IF_ACMPNE, 42, 122, Opcodes.D2I);
        this.selectedColorGray3 = Color.rgb(42, 122, Opcodes.D2I);
        this.rhy1ColorArray = new int[]{Color.parseColor("#11ADFF"), Color.parseColor("#11ADFF"), Color.parseColor("#FFFA00"), Color.parseColor("#FFFA00"), Color.parseColor("#FF1D10"), Color.parseColor("#FF1D10"), Color.parseColor("#CB46DF"), Color.parseColor("#CB46DF"), Color.parseColor("#11ADFF"), Color.parseColor("#11ADFF"), Color.parseColor("#7AFF0E"), Color.parseColor("#7AFF0E"), Color.parseColor("#FFFC00"), Color.parseColor("#FFFC00"), Color.parseColor("#FF1D10"), Color.parseColor("#FF1D10")};
        this.rhy2ColorArray = new int[]{Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#9C00FF"), Color.parseColor("#9C00FF"), Color.parseColor("#0001FF"), Color.parseColor("#9C00FF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#9C00FF"), Color.parseColor("#0001FF"), Color.parseColor("#9C00FF"), Color.parseColor("#9C00FF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF")};
        this.rhy3ColorArray = new int[]{Color.parseColor("#74F101"), Color.parseColor("#74F101"), Color.parseColor("#7BF300"), Color.parseColor("#7BF300"), Color.parseColor("#8EF900"), Color.parseColor("#8EF900"), Color.parseColor("#A5FF00"), Color.parseColor("#A5FF00"), Color.parseColor("#BCFF00"), Color.parseColor("#BCFF00"), Color.parseColor("#D5FF00"), Color.parseColor("#D5FF00"), Color.parseColor("#EAFF00"), Color.parseColor("#EAFF00"), Color.parseColor("#FAFF00"), Color.parseColor("#FFF00E"), Color.parseColor("#FFF00E"), Color.parseColor("#FFD525"), Color.parseColor("#FFD525"), Color.parseColor("#FFB241"), Color.parseColor("#FFB241"), Color.parseColor("#FF8C61"), Color.parseColor("#FF8C61"), Color.parseColor("#FF6284"), Color.parseColor("#FF6284"), Color.parseColor("#FF3EA5"), Color.parseColor("#FF3EA5"), Color.parseColor("#FF1FC3"), Color.parseColor("#FF1FC3"), Color.parseColor("#FF09DC"), Color.parseColor("#FF09DC"), Color.parseColor("#FF00EF"), Color.parseColor("#FF00EF"), Color.parseColor("#D716FF"), Color.parseColor("#D716FF"), Color.parseColor("#9554FF"), Color.parseColor("#9554FF"), Color.parseColor("#4AA2FF"), Color.parseColor("#4AA2FF"), Color.parseColor("#12DCFF"), Color.parseColor("#12DCFF"), Color.parseColor("#00EAFF"), Color.parseColor("#00EAFF"), Color.parseColor("#00D8FF"), Color.parseColor("#00D8FF"), Color.parseColor("#0BB5FF"), Color.parseColor("#0BB5FF"), Color.parseColor("#2388FF"), Color.parseColor("#2388FF"), Color.parseColor("#3A63FF"), Color.parseColor("#3A63FF"), Color.parseColor("#4F4AFF"), Color.parseColor("#4F4AFF"), Color.parseColor("#5E49FF"), Color.parseColor("#5E49FF"), Color.parseColor("#6F51FF"), Color.parseColor("#6F51FF"), Color.parseColor("#7D5FFF"), Color.parseColor("#7D5FFF"), Color.parseColor("#8D74FF"), Color.parseColor("#8D74FF"), Color.parseColor("#9B86FF"), Color.parseColor("#9B86FF")};
        this.rhy4ColorArray = new int[]{Color.parseColor("#02CDFF"), Color.parseColor("#02CDFF"), Color.parseColor("#0FB2FF"), Color.parseColor("#0FB2FF"), Color.parseColor("#228DFF"), Color.parseColor("#228DFF"), Color.parseColor("#3566FF"), Color.parseColor("#3566FF"), Color.parseColor("#4A3FFF"), Color.parseColor("#4A3FFF"), Color.parseColor("#611AFF"), Color.parseColor("#7701FF"), Color.parseColor("#8D00FF"), Color.parseColor("#A400FF"), Color.parseColor("#BD00FF"), Color.parseColor("#D400FF"), Color.parseColor("#E600FF"), Color.parseColor("#F700FF"), Color.parseColor("#FF00EF"), Color.parseColor("#FF00C8"), Color.parseColor("#FF0099"), Color.parseColor("#FF0066"), Color.parseColor("#FF0037"), Color.parseColor("#FF0012"), Color.parseColor("#FF0400"), Color.parseColor("#FF1300"), Color.parseColor("#FF2700"), Color.parseColor("#FF3F00"), Color.parseColor("#FF5800"), Color.parseColor("#FF7000"), Color.parseColor("#FF8500"), Color.parseColor("#FF9800"), Color.parseColor("#FFAE00"), Color.parseColor("#FFC300"), Color.parseColor("#FFD600"), Color.parseColor("#FFE600"), Color.parseColor("#FFF400")};
        this.rhy5ColorArray = new int[]{Color.parseColor("#FF4300"), Color.parseColor("#FF4300"), Color.parseColor("#FF4B00"), Color.parseColor("#FF4B00"), Color.parseColor("#FF5E00"), Color.parseColor("#FF5E00"), Color.parseColor("#FF7900"), Color.parseColor("#FF7900"), Color.parseColor("#FF9400"), Color.parseColor("#FF9400"), Color.parseColor("#FFAE00"), Color.parseColor("#FFAE00"), Color.parseColor("#FFC400"), Color.parseColor("#FFC400"), Color.parseColor("#FFCF00"), Color.parseColor("#FFCF00"), Color.parseColor("#FFCD03"), Color.parseColor("#FFCD03"), Color.parseColor("#FFB813"), Color.parseColor("#FFB813"), Color.parseColor("#FF932B"), Color.parseColor("#FF932B"), Color.parseColor("#FF6745"), Color.parseColor("#FF6745"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF1872"), Color.parseColor("#FF1872"), Color.parseColor("#FF037D"), Color.parseColor("#FF037D"), Color.parseColor("#FF017E"), Color.parseColor("#FF017E"), Color.parseColor("#FF017E"), Color.parseColor("#FF017E"), Color.parseColor("#FF037D"), Color.parseColor("#FF037D"), Color.parseColor("#FF1872"), Color.parseColor("#FF1872"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF6745"), Color.parseColor("#FF6745"), Color.parseColor("#FF932B"), Color.parseColor("#FF932B"), Color.parseColor("#FFB813"), Color.parseColor("#FFB813"), Color.parseColor("#FFCD03"), Color.parseColor("#FFCD03"), Color.parseColor("#FFCF00"), Color.parseColor("#FFCF00"), Color.parseColor("#FFC400"), Color.parseColor("#FFC400"), Color.parseColor("#FFAE00"), Color.parseColor("#FFAE00"), Color.parseColor("#FF9400"), Color.parseColor("#FF9400"), Color.parseColor("#FF7900"), Color.parseColor("#FF7900"), Color.parseColor("#FF5E00"), Color.parseColor("#FF5E00"), Color.parseColor("#FF4B00"), Color.parseColor("#FF4B00"), Color.parseColor("#FF4300"), Color.parseColor("#FF4300")};
        this.index = 0;
    }

    public RhythmLedView16x64(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.widthCount = 64;
        this.heightCount = 8;
        this.pointMargin = 0;
        this.alignment = 0;
        this.unSelectedColor = 0;
        this.selectedColor1 = Color.argb(89, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor2 = Color.argb(Opcodes.IF_ACMPNE, 82, Command.CMD_OTA_ENTER_UPDATE_MODE, 255);
        this.selectedColor3 = Color.rgb(17, ComposerKt.providerValuesKey, Command.CMD_REBOOT_DEVICE);
        this.selectedColor4 = Color.rgb(255, 255, 255);
        this.selectedColorGray1 = Color.argb(89, 42, 122, Opcodes.D2I);
        this.selectedColorGray2 = Color.argb(Opcodes.IF_ACMPNE, 42, 122, Opcodes.D2I);
        this.selectedColorGray3 = Color.rgb(42, 122, Opcodes.D2I);
        this.rhy1ColorArray = new int[]{Color.parseColor("#11ADFF"), Color.parseColor("#11ADFF"), Color.parseColor("#FFFA00"), Color.parseColor("#FFFA00"), Color.parseColor("#FF1D10"), Color.parseColor("#FF1D10"), Color.parseColor("#CB46DF"), Color.parseColor("#CB46DF"), Color.parseColor("#11ADFF"), Color.parseColor("#11ADFF"), Color.parseColor("#7AFF0E"), Color.parseColor("#7AFF0E"), Color.parseColor("#FFFC00"), Color.parseColor("#FFFC00"), Color.parseColor("#FF1D10"), Color.parseColor("#FF1D10")};
        this.rhy2ColorArray = new int[]{Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#9C00FF"), Color.parseColor("#9C00FF"), Color.parseColor("#0001FF"), Color.parseColor("#9C00FF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#9C00FF"), Color.parseColor("#0001FF"), Color.parseColor("#9C00FF"), Color.parseColor("#9C00FF"), Color.parseColor("#FF00AD"), Color.parseColor("#FF00AD"), Color.parseColor("#00FAFF"), Color.parseColor("#00FAFF"), Color.parseColor("#0001FF"), Color.parseColor("#0001FF")};
        this.rhy3ColorArray = new int[]{Color.parseColor("#74F101"), Color.parseColor("#74F101"), Color.parseColor("#7BF300"), Color.parseColor("#7BF300"), Color.parseColor("#8EF900"), Color.parseColor("#8EF900"), Color.parseColor("#A5FF00"), Color.parseColor("#A5FF00"), Color.parseColor("#BCFF00"), Color.parseColor("#BCFF00"), Color.parseColor("#D5FF00"), Color.parseColor("#D5FF00"), Color.parseColor("#EAFF00"), Color.parseColor("#EAFF00"), Color.parseColor("#FAFF00"), Color.parseColor("#FFF00E"), Color.parseColor("#FFF00E"), Color.parseColor("#FFD525"), Color.parseColor("#FFD525"), Color.parseColor("#FFB241"), Color.parseColor("#FFB241"), Color.parseColor("#FF8C61"), Color.parseColor("#FF8C61"), Color.parseColor("#FF6284"), Color.parseColor("#FF6284"), Color.parseColor("#FF3EA5"), Color.parseColor("#FF3EA5"), Color.parseColor("#FF1FC3"), Color.parseColor("#FF1FC3"), Color.parseColor("#FF09DC"), Color.parseColor("#FF09DC"), Color.parseColor("#FF00EF"), Color.parseColor("#FF00EF"), Color.parseColor("#D716FF"), Color.parseColor("#D716FF"), Color.parseColor("#9554FF"), Color.parseColor("#9554FF"), Color.parseColor("#4AA2FF"), Color.parseColor("#4AA2FF"), Color.parseColor("#12DCFF"), Color.parseColor("#12DCFF"), Color.parseColor("#00EAFF"), Color.parseColor("#00EAFF"), Color.parseColor("#00D8FF"), Color.parseColor("#00D8FF"), Color.parseColor("#0BB5FF"), Color.parseColor("#0BB5FF"), Color.parseColor("#2388FF"), Color.parseColor("#2388FF"), Color.parseColor("#3A63FF"), Color.parseColor("#3A63FF"), Color.parseColor("#4F4AFF"), Color.parseColor("#4F4AFF"), Color.parseColor("#5E49FF"), Color.parseColor("#5E49FF"), Color.parseColor("#6F51FF"), Color.parseColor("#6F51FF"), Color.parseColor("#7D5FFF"), Color.parseColor("#7D5FFF"), Color.parseColor("#8D74FF"), Color.parseColor("#8D74FF"), Color.parseColor("#9B86FF"), Color.parseColor("#9B86FF")};
        this.rhy4ColorArray = new int[]{Color.parseColor("#02CDFF"), Color.parseColor("#02CDFF"), Color.parseColor("#0FB2FF"), Color.parseColor("#0FB2FF"), Color.parseColor("#228DFF"), Color.parseColor("#228DFF"), Color.parseColor("#3566FF"), Color.parseColor("#3566FF"), Color.parseColor("#4A3FFF"), Color.parseColor("#4A3FFF"), Color.parseColor("#611AFF"), Color.parseColor("#7701FF"), Color.parseColor("#8D00FF"), Color.parseColor("#A400FF"), Color.parseColor("#BD00FF"), Color.parseColor("#D400FF"), Color.parseColor("#E600FF"), Color.parseColor("#F700FF"), Color.parseColor("#FF00EF"), Color.parseColor("#FF00C8"), Color.parseColor("#FF0099"), Color.parseColor("#FF0066"), Color.parseColor("#FF0037"), Color.parseColor("#FF0012"), Color.parseColor("#FF0400"), Color.parseColor("#FF1300"), Color.parseColor("#FF2700"), Color.parseColor("#FF3F00"), Color.parseColor("#FF5800"), Color.parseColor("#FF7000"), Color.parseColor("#FF8500"), Color.parseColor("#FF9800"), Color.parseColor("#FFAE00"), Color.parseColor("#FFC300"), Color.parseColor("#FFD600"), Color.parseColor("#FFE600"), Color.parseColor("#FFF400")};
        this.rhy5ColorArray = new int[]{Color.parseColor("#FF4300"), Color.parseColor("#FF4300"), Color.parseColor("#FF4B00"), Color.parseColor("#FF4B00"), Color.parseColor("#FF5E00"), Color.parseColor("#FF5E00"), Color.parseColor("#FF7900"), Color.parseColor("#FF7900"), Color.parseColor("#FF9400"), Color.parseColor("#FF9400"), Color.parseColor("#FFAE00"), Color.parseColor("#FFAE00"), Color.parseColor("#FFC400"), Color.parseColor("#FFC400"), Color.parseColor("#FFCF00"), Color.parseColor("#FFCF00"), Color.parseColor("#FFCD03"), Color.parseColor("#FFCD03"), Color.parseColor("#FFB813"), Color.parseColor("#FFB813"), Color.parseColor("#FF932B"), Color.parseColor("#FF932B"), Color.parseColor("#FF6745"), Color.parseColor("#FF6745"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF1872"), Color.parseColor("#FF1872"), Color.parseColor("#FF037D"), Color.parseColor("#FF037D"), Color.parseColor("#FF017E"), Color.parseColor("#FF017E"), Color.parseColor("#FF017E"), Color.parseColor("#FF017E"), Color.parseColor("#FF037D"), Color.parseColor("#FF037D"), Color.parseColor("#FF1872"), Color.parseColor("#FF1872"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF3C5E"), Color.parseColor("#FF6745"), Color.parseColor("#FF6745"), Color.parseColor("#FF932B"), Color.parseColor("#FF932B"), Color.parseColor("#FFB813"), Color.parseColor("#FFB813"), Color.parseColor("#FFCD03"), Color.parseColor("#FFCD03"), Color.parseColor("#FFCF00"), Color.parseColor("#FFCF00"), Color.parseColor("#FFC400"), Color.parseColor("#FFC400"), Color.parseColor("#FFAE00"), Color.parseColor("#FFAE00"), Color.parseColor("#FF9400"), Color.parseColor("#FF9400"), Color.parseColor("#FF7900"), Color.parseColor("#FF7900"), Color.parseColor("#FF5E00"), Color.parseColor("#FF5E00"), Color.parseColor("#FF4B00"), Color.parseColor("#FF4B00"), Color.parseColor("#FF4300"), Color.parseColor("#FF4300")};
        this.index = 0;
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
        invalidate();
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
        invalidate();
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
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        this.widthSize = size;
        if (this.heightCount == 4) {
            this.heightSize = 96;
        } else {
            this.heightSize = 192;
        }
        this.pointXAllLength = 24;
        this.pointYAllLength = 24;
        this.pointLength = 12;
        setMeasuredDimension(size, this.heightSize);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int i;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int i3 = this.heightCount;
            int i4 = ((i2 / i3) * this.pointXAllLength) + 7;
            int i5 = i2 % i3;
            if (this.alignment == 1) {
                i = i5 * this.pointYAllLength;
            } else {
                i = (i5 * this.pointYAllLength) + 6;
            }
            int i6 = this.pointLength;
            childAt.layout(i4, i, i4 + i6, i6 + i);
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
        }
        invalidate();
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
        }
        invalidate();
    }

    private LedItemRhyView getLedItemRhyView(int cloum, int row) {
        int i = this.heightCount;
        return (LedItemRhyView) getChildAt((cloum * i) + (i - row));
    }

    public void updateRhythmUI(int mode, final List<Integer> rhyDataList) {
        if (rhyDataList == null) {
            return;
        }
        if (rhyDataList != null) {
            clearSelected();
        }
        if (mode == 0) {
            setRhyData1(rhyDataList);
            return;
        }
        if (mode == 1) {
            setRhyData2(rhyDataList);
            return;
        }
        if (mode == 2) {
            setRhyData3(rhyDataList);
        } else if (mode == 3) {
            setRhyData4(rhyDataList);
        } else {
            setRhyData5(rhyDataList);
        }
    }

    public void setRhyData1(List<Integer> rhyDataList) {
        int intValue;
        if (rhyDataList == null) {
            return;
        }
        clearSelected();
        if (rhyDataList != null) {
            clearSelected();
        }
        for (int i = 0; i <= this.widthCount - 1 && (intValue = rhyDataList.get(i).intValue()) < this.widthCount; i++) {
            for (int i2 = 1; i2 <= intValue; i2++) {
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i, i2);
                if (ledItemRhyView == null) {
                    return;
                }
                ledItemRhyView.setChecked(true);
                switch (i2) {
                    case 0:
                        ledItemRhyView.setPaint(this.rhy1ColorArray[7]);
                        break;
                    case 1:
                        ledItemRhyView.setPaint(this.rhy1ColorArray[6]);
                        break;
                    case 2:
                        ledItemRhyView.setPaint(this.rhy1ColorArray[5]);
                        break;
                    case 3:
                        ledItemRhyView.setPaint(this.rhy1ColorArray[4]);
                        break;
                    case 4:
                        ledItemRhyView.setPaint(this.rhy1ColorArray[3]);
                        break;
                    case 5:
                        ledItemRhyView.setPaint(this.rhy1ColorArray[2]);
                        break;
                    case 6:
                        ledItemRhyView.setPaint(this.rhy1ColorArray[1]);
                        break;
                    case 7:
                        ledItemRhyView.setPaint(this.rhy1ColorArray[0]);
                        break;
                }
            }
        }
    }

    public void setRhyData2(List<Integer> rhyDataList) {
        int intValue;
        if (rhyDataList == null) {
            return;
        }
        clearSelected();
        if (rhyDataList != null) {
            clearSelected();
        }
        for (int i = 0; i <= this.widthCount - 1 && (intValue = rhyDataList.get(i).intValue()) < this.widthCount; i++) {
            for (int i2 = 1; i2 <= intValue; i2++) {
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i, i2);
                if (ledItemRhyView == null) {
                    return;
                }
                ledItemRhyView.setChecked(true);
                ledItemRhyView.setPaint(this.rhy2ColorArray[i]);
            }
        }
    }

    public void setRhyData3(List<Integer> rhyDataList) {
        int intValue;
        int i;
        for (int i2 = 1; i2 <= this.widthCount - 1 && (intValue = rhyDataList.get(i2).intValue()) < (i = this.widthCount); i2++) {
            if (i2 == 1) {
                intValue = rhyDataList.get(i - 1).intValue();
            }
            if (i2 == 2) {
                intValue = rhyDataList.get(this.widthCount - 2).intValue();
            }
            for (int i3 = 1; i3 <= intValue; i3++) {
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i2, i3);
                if (ledItemRhyView == null) {
                    return;
                }
                ledItemRhyView.setChecked(true);
                ledItemRhyView.setPaint(this.rhy3ColorArray[i2]);
            }
        }
    }

    public void setRhyData4(List<Integer> rhyDataList) {
        int intValue;
        int i;
        for (int i2 = 1; i2 <= this.widthCount - 1 && (intValue = rhyDataList.get(i2).intValue()) < (i = this.widthCount); i2++) {
            if (i2 == 1) {
                intValue = rhyDataList.get(i - 1).intValue();
            }
            if (i2 == 2) {
                intValue = rhyDataList.get(this.widthCount - 2).intValue();
            }
            for (int i3 = 1; i3 <= intValue; i3++) {
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i2, i3);
                if (ledItemRhyView == null) {
                    return;
                }
                ledItemRhyView.setChecked(true);
                ledItemRhyView.setPaint(this.rhy3ColorArray[i2]);
            }
        }
    }

    public void setRhyData5(List<Integer> rhyDataList) {
        int intValue;
        int i;
        for (int i2 = 1; i2 <= this.widthCount - 1 && (intValue = rhyDataList.get(i2).intValue()) < (i = this.widthCount); i2++) {
            if (i2 == 1) {
                intValue = rhyDataList.get(i - 1).intValue();
            }
            if (i2 == 2) {
                intValue = rhyDataList.get(this.widthCount - 2).intValue();
            }
            for (int i3 = 1; i3 <= intValue; i3++) {
                LedItemRhyView ledItemRhyView = getLedItemRhyView(i2, i3);
                if (ledItemRhyView == null) {
                    return;
                }
                ledItemRhyView.setChecked(true);
                ledItemRhyView.setPaint(this.rhy5ColorArray[i2]);
            }
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
                }
            }
        }
        invalidate();
        this.index++;
    }

    public void setPointMargin(int pointMargin) {
        this.pointMargin = 0;
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

    private void showImage(int[][] data) {
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
                }
            }
        }
        invalidate();
        this.index++;
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
