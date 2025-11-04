package com.wifiled.ipixels.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.blankj.utilcode.util.ScreenUtils;
import com.wifiled.baselib.CoreBase;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.gameview.utils.GridLineView;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.common.UndoRedoList;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.core.text.GradientColor;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.diy.CPaintRunTimeItem;
import com.wifiled.ipixels.ui.diy.DiyImageFun;
import com.wifiled.ipixels.ui.diy.DiyImageMoveType;
import com.wifiled.ipixels.ui.text.MsgItemViews;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.utils.TimeHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes3.dex */
public class LedView extends View {
    private static final float MAX_SCALE = 4.0f;
    private static final float MIN_SCALE = 1.0f;
    public static final int MIRROR_HORIZONTAL = 0;
    public static final int MIRROR_NULL = -1;
    public static final int MIRROR_VERTICAL = 1;
    public static final int MODE_ERASER = 2;
    public static final int MODE_MOVE = 3;
    public static final int MODE_PAINT = 1;
    public static final int ORIENTATION_PORTRAIT = 0;
    private static final float SCALING_FACTOR = 0.05f;
    private static final String TAG = "LedView";
    private ArrayList<Integer> arrayMarkSelColor;
    private boolean bIsUnRedoClick;
    private boolean bRoutDiyImage;
    private int heightCount;
    private int heightSize;
    private int iCmpWhichChildView;
    private boolean isColorChange;
    private boolean isDispatchTouch;
    private boolean isHasAdded;
    private int itemColorTotalSize;
    private List<ItemView> itemViews;
    private long lastTime;
    private LedDiyListener ledDiyListener;
    private LedListener ledListener;
    private ByteBuffer mArrMoveVal;
    private boolean mIsUnDoClick;
    private int mLastX;
    private int mLastY;
    private CPaintRunTimeItem mPaintItem;
    private float mScale;
    private int mirrorMode;
    private int mode;
    private byte moveBottom;
    private byte moveL;
    private byte moveR;
    private byte moveUp;
    private int offset;
    private boolean onShow;
    private int orientation;
    private Paint paint;
    private int pointAllLength;
    private int pointLength;
    private int pointMargin;
    private int prevColorIndex;
    private final RectF rectFBuffer;
    private ScaleCallback scaleCallback;
    private int selectedColor;
    private int tMarkColor;
    private int tPushColor;
    private int tWhichColumn;
    private int tWhichRow;
    private int unSelectedColor;
    private final UndoRedoList<DrawPath> undoRedoList;
    private int whichChildView;
    private int widthCount;
    private int widthSize;
    private int xMore;
    private int yMore;

    public interface LedDiyListener {
        void onRunTimeItemSelect(CPaintRunTimeItem item);
    }

    public interface LedListener {
        void onItemSelect(byte[] data);
    }

    public interface ScaleCallback {
        void onScale(float scale);
    }

    public boolean isGradientColor(int color) {
        switch (color) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }

    public LedView(Context context) {
        super(context);
        this.pointMargin = 1;
        this.unSelectedColor = Color.parseColor("#000000");
        this.selectedColor = Color.parseColor("#62ff3c");
        this.mode = 1;
        this.orientation = 0;
        this.mirrorMode = -1;
        this.mPaintItem = new CPaintRunTimeItem();
        this.arrayMarkSelColor = new ArrayList<>();
        this.prevColorIndex = -1;
        this.isDispatchTouch = true;
        this.onShow = false;
        this.mScale = 1.0f;
        this.itemViews = new ArrayList();
        this.rectFBuffer = new RectF();
        this.bRoutDiyImage = false;
        this.bIsUnRedoClick = false;
        this.mIsUnDoClick = false;
        this.lastTime = 0L;
        this.mLastX = 0;
        this.mLastY = 0;
        this.undoRedoList = new UndoRedoList<>();
        this.isHasAdded = false;
        this.whichChildView = -1;
        this.tWhichRow = -1;
        this.tWhichColumn = -1;
        this.iCmpWhichChildView = -1;
        this.tPushColor = Color.parseColor("#000000");
        this.tMarkColor = Color.parseColor("#000000");
        this.isColorChange = false;
        this.itemColorTotalSize = 0;
        this.mArrMoveVal = ByteBuffer.allocate(4);
        this.moveL = (byte) 0;
        this.moveR = (byte) 0;
        this.moveUp = (byte) 0;
        this.moveBottom = (byte) 0;
    }

    public LedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.pointMargin = 1;
        this.unSelectedColor = Color.parseColor("#000000");
        this.selectedColor = Color.parseColor("#62ff3c");
        this.mode = 1;
        this.orientation = 0;
        this.mirrorMode = -1;
        this.mPaintItem = new CPaintRunTimeItem();
        this.arrayMarkSelColor = new ArrayList<>();
        this.prevColorIndex = -1;
        this.isDispatchTouch = true;
        this.onShow = false;
        this.mScale = 1.0f;
        this.itemViews = new ArrayList();
        this.rectFBuffer = new RectF();
        this.bRoutDiyImage = false;
        this.bIsUnRedoClick = false;
        this.mIsUnDoClick = false;
        this.lastTime = 0L;
        this.mLastX = 0;
        this.mLastY = 0;
        this.undoRedoList = new UndoRedoList<>();
        this.isHasAdded = false;
        this.whichChildView = -1;
        this.tWhichRow = -1;
        this.tWhichColumn = -1;
        this.iCmpWhichChildView = -1;
        this.tPushColor = Color.parseColor("#000000");
        this.tMarkColor = Color.parseColor("#000000");
        this.isColorChange = false;
        this.itemColorTotalSize = 0;
        this.mArrMoveVal = ByteBuffer.allocate(4);
        this.moveL = (byte) 0;
        this.moveR = (byte) 0;
        this.moveUp = (byte) 0;
        this.moveBottom = (byte) 0;
    }

    public LedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.pointMargin = 1;
        this.unSelectedColor = Color.parseColor("#000000");
        this.selectedColor = Color.parseColor("#62ff3c");
        this.mode = 1;
        this.orientation = 0;
        this.mirrorMode = -1;
        this.mPaintItem = new CPaintRunTimeItem();
        this.arrayMarkSelColor = new ArrayList<>();
        this.prevColorIndex = -1;
        this.isDispatchTouch = true;
        this.onShow = false;
        this.mScale = 1.0f;
        this.itemViews = new ArrayList();
        this.rectFBuffer = new RectF();
        this.bRoutDiyImage = false;
        this.bIsUnRedoClick = false;
        this.mIsUnDoClick = false;
        this.lastTime = 0L;
        this.mLastX = 0;
        this.mLastY = 0;
        this.undoRedoList = new UndoRedoList<>();
        this.isHasAdded = false;
        this.whichChildView = -1;
        this.tWhichRow = -1;
        this.tWhichColumn = -1;
        this.iCmpWhichChildView = -1;
        this.tPushColor = Color.parseColor("#000000");
        this.tMarkColor = Color.parseColor("#000000");
        this.isColorChange = false;
        this.itemColorTotalSize = 0;
        this.mArrMoveVal = ByteBuffer.allocate(4);
        this.moveL = (byte) 0;
        this.moveR = (byte) 0;
        this.moveUp = (byte) 0;
        this.moveBottom = (byte) 0;
    }

    public class ItemView {
        int index;
        public boolean isSelected;
        private Stack<Integer> colors = getColors();
        private Stack<Integer> recycleColors = getRecycleColors();

        public ItemView() {
        }

        Stack<Integer> getColors() {
            Stack<Integer> stack;
            synchronized (ItemView.class) {
                if (this.colors == null) {
                    this.colors = new Stack<>();
                }
                stack = this.colors;
            }
            return stack;
        }

        Stack<Integer> getRecycleColors() {
            Stack<Integer> stack;
            synchronized (ItemView.class) {
                if (this.recycleColors == null) {
                    this.recycleColors = new Stack<>();
                }
                stack = this.recycleColors;
            }
            return stack;
        }

        public void deletePara() {
            this.colors = null;
            this.recycleColors = null;
        }
    }

    public class DrawPath {
        private List<ItemView> views;

        public DrawPath() {
        }

        public List<ItemView> getViews() {
            List<ItemView> list;
            synchronized (DrawPath.class) {
                if (this.views == null) {
                    this.views = new ArrayList();
                }
                list = this.views;
            }
            return list;
        }

        public void deletePara() {
            this.views = null;
        }
    }

    public void init(int widthCount, int heightCount) {
        init(widthCount, heightCount, 0.2f);
    }

    public void init(int widthCount, int heightCount, float strokeWidth) {
        this.widthCount = widthCount;
        this.heightCount = heightCount;
        int totalCount = getTotalCount();
        Paint paint = new Paint(1);
        this.paint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(strokeWidth);
        String topActivity = AppConfig.INSTANCE.getTopActivity();
        for (int i = 0; i < totalCount; i++) {
            ItemView itemView = new ItemView();
            itemView.index = i;
            this.itemViews.add(itemView);
            itemView.deletePara();
            topActivity.contains("ChannelListActivity");
        }
        if (topActivity.contains("DiyImageActivity")) {
            initDrawPath();
        }
    }

    public void initUndoRedoList() {
        initDrawPath();
    }

    private void initDrawPath() {
        DrawPath drawPath = new DrawPath();
        for (ItemView itemView : this.itemViews) {
            ItemView itemView2 = new ItemView();
            if (!itemView2.getColors().empty()) {
                itemView2.getColors().add(itemView.getColors().peek());
            }
            itemView2.index = itemView.index;
            drawPath.getViews().add(itemView2);
        }
        this.undoRedoList.put(drawPath);
    }

    private int getTotalCount() {
        return this.widthCount * this.heightCount;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getTotalCount() <= 0) {
            return;
        }
        String topActivity = AppConfig.INSTANCE.getTopActivity();
        for (int i = 0; i < this.itemViews.size(); i++) {
            ItemView itemView = this.itemViews.get(i);
            if (!itemView.getColors().empty()) {
                this.paint.setColor(itemView.getColors().peek().intValue());
                setIfOnlyShowAlpha(Math.round(TextEmojiBuilder.INSTANCE.getEventText().getTextAlpha() * 255.0f));
            } else if (this.bRoutDiyImage && !itemView.getRecycleColors().isEmpty()) {
                this.paint.setColor(itemView.getRecycleColors().get(0).intValue());
                setIfOnlyShowAlpha(Math.round(TextEmojiBuilder.INSTANCE.getEventText().getTextAlpha() * 255.0f));
            } else {
                this.paint.setColor(this.unSelectedColor);
                setIfOnlyShowAlpha(0);
            }
            int rowNumber = (getRowNumber(i) * this.pointAllLength) + this.pointMargin;
            int columnNumber = (getColumnNumber(i) * this.pointAllLength) + this.pointMargin;
            int i2 = this.pointLength;
            this.rectFBuffer.set(columnNumber, rowNumber, columnNumber + i2, i2 + rowNumber);
            canvas.drawRect(this.rectFBuffer, this.paint);
        }
        if (topActivity.contains("DiyImageActivity")) {
            if (this.widthCount == 32 && this.heightCount == 32) {
                GridLineView.INSTANCE.drawTextGridLine(this.paint, canvas, this.widthSize - this.xMore, this.heightSize - this.yMore);
            } else if (AppConfig.INSTANCE.getLedType() == 6) {
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.diy_32_128_bg), (Rect) null, new RectF(0.0f, 0.0f, this.widthSize - (this.offset * 2), (r0 / 128) * 32), (Paint) null);
            } else if (AppConfig.INSTANCE.getLedType() == 13) {
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.diy_32_96_bg), (Rect) null, new RectF(0.0f, 0.0f, this.widthSize - (this.offset * 2), (r0 / 96) * 32), (Paint) null);
            } else if (AppConfig.INSTANCE.getLedType() == 7) {
                GridLineView.INSTANCE.drawTextGridLine1696(this.paint, canvas, this.widthSize - this.xMore, 144.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 8) {
                GridLineView.INSTANCE.drawTextGridLine1696(this.paint, canvas, this.widthSize - this.xMore, 192.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 1) {
                GridLineView.INSTANCE.drawTextGridLine1696(this.paint, canvas, this.widthSize - this.xMore, 96.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 3) {
                GridLineView.INSTANCE.drawTextGridLine1696(this.paint, canvas, this.widthSize - this.xMore, 64.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 5) {
                GridLineView.INSTANCE.drawTextGridLine2064(this.paint, canvas, this.widthSize - this.xMore);
            } else if (AppConfig.INSTANCE.getLedType() == 4) {
                GridLineView.INSTANCE.drawTextGridLine1696(this.paint, canvas, this.widthSize - this.xMore, 32.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 9) {
                GridLineView.INSTANCE.drawTextGridLine2448(this.paint, canvas, this.widthSize - this.xMore, 48.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 10) {
                GridLineView.INSTANCE.drawTextGridLine3296(this.paint, canvas, this.widthSize - this.xMore, 64.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 11) {
                GridLineView.INSTANCE.drawTextGridLine3296(this.paint, canvas, this.widthSize - this.xMore, 96.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 12) {
                GridLineView.INSTANCE.drawTextGridLine3296(this.paint, canvas, this.widthSize - this.xMore, 128.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 15) {
                GridLineView.INSTANCE.drawTextGridLine3296(this.paint, canvas, this.widthSize - this.xMore, 192.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 14) {
                GridLineView.INSTANCE.drawTextGridLine3296(this.paint, canvas, this.widthSize - this.xMore, 160.0f);
            } else if (AppConfig.INSTANCE.getLedType() == 16) {
                GridLineView.INSTANCE.drawTextGridLine3296(this.paint, canvas, this.widthSize - this.xMore, 256.0f);
            } else {
                GridLineView.INSTANCE.drawTextGridLine(this.paint, canvas, this.widthSize - this.xMore, this.heightSize - this.yMore);
            }
            if (this.ledListener == null || UtilsExtensionKt.isRepeatClick(100L) || !this.bIsUnRedoClick) {
                return;
            }
            this.bIsUnRedoClick = false;
            this.ledListener.onItemSelect(getRGBData());
        }
    }

    private void setIfOnlyShowAlpha(int alpha) {
        if (this.onShow) {
            this.paint.setAlpha(alpha);
        }
    }

    public void onlyRefreshAlpha(TextEmojiVO textEmojiVO, List<ItemView> tItemViews) {
        this.widthCount = textEmojiVO.getTextWidth();
        this.heightCount = textEmojiVO.getTextHeight();
        this.itemViews = tItemViews;
        if (this.paint == null) {
            Paint paint = new Paint(1);
            this.paint = paint;
            paint.setStyle(Paint.Style.FILL);
        }
        invalidate();
    }

    public void renderTextcolor(TextEmojiVO textEmojiVO, List<ItemView> tItemViews) {
        int i;
        int textColor = textEmojiVO.getTextColor();
        this.itemViews = tItemViews;
        this.widthCount = textEmojiVO.getTextWidth();
        this.heightCount = textEmojiVO.getTextHeight();
        if (this.paint == null) {
            Paint paint = new Paint(1);
            this.paint = paint;
            paint.setStyle(Paint.Style.FILL);
        }
        ArrayList<String> arrayList = new ArrayList<>();
        switch (textColor) {
            case 2:
                arrayList = GradientColor.INSTANCE.getGradientColor1();
                break;
            case 3:
                arrayList = GradientColor.INSTANCE.getGradientColor2();
                break;
            case 4:
                arrayList = GradientColor.INSTANCE.getGradientColor3();
                break;
            case 5:
                arrayList = GradientColor.INSTANCE.getGradientColor4();
                break;
            case 6:
                arrayList = GradientColor.INSTANCE.getGradientColor5();
                break;
            case 7:
                arrayList = GradientColor.INSTANCE.getGradientColor6();
                break;
            case 8:
                arrayList = GradientColor.INSTANCE.getGradientColor7();
                break;
            case 9:
                arrayList = GradientColor.INSTANCE.getGradientColor8();
                break;
        }
        int i2 = 0;
        if (arrayList.size() >= this.heightCount && isGradientColor(textColor)) {
            int i3 = this.unSelectedColor;
            while (i2 < getTotalCount()) {
                int i4 = i2 % this.widthCount;
                int i5 = i2 / this.heightCount;
                if (i4 == 0) {
                    i3 = Color.parseColor(arrayList.get(i5));
                }
                if (!this.itemViews.get(i2).isSelected) {
                    this.itemViews.get(i2).getColors().clear();
                } else {
                    this.itemViews.get(i2).getColors().clear();
                    this.itemViews.get(i2).getColors().push(Integer.valueOf(i3));
                }
                i2++;
            }
        } else {
            while (i2 < getTotalCount()) {
                List<ItemView> list = this.itemViews;
                if (list == null || list.size() < (i = i2 + 1)) {
                    return;
                }
                if (!this.itemViews.get(i2).isSelected) {
                    this.itemViews.get(i2).getColors().clear();
                } else {
                    this.itemViews.get(i2).getColors().clear();
                    this.itemViews.get(i2).getColors().push(Integer.valueOf(textColor));
                }
                i2 = i;
            }
        }
        invalidate();
    }

    private int getRowNumber(int i) {
        return i / this.widthCount;
    }

    private int getColumnNumber(int i) {
        return i % this.widthCount;
    }

    private void handleZoom(boolean isZoomIn) {
        if (System.currentTimeMillis() - this.lastTime < 50) {
            return;
        }
        this.lastTime = System.currentTimeMillis();
        if (isZoomIn) {
            float f = this.mScale + SCALING_FACTOR;
            this.mScale = f;
            setScale(f);
        } else {
            float f2 = this.mScale - SCALING_FACTOR;
            this.mScale = f2;
            if (f2 <= 0.4f) {
                this.mScale = 0.4f;
            }
            setScale(this.mScale);
        }
    }

    public void setScale(float scale) {
        if (scale < 1.0f || scale > 4.0f) {
            scale = scale > 4.0f ? 4.0f : 1.0f;
        }
        this.mScale = scale;
        ScaleCallback scaleCallback = this.scaleCallback;
        if (scaleCallback != null) {
            scaleCallback.onScale(scale);
        }
        setScaleX(this.mScale);
        setScaleY(this.mScale);
    }

    public float getScale() {
        return this.mScale;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        this.widthSize = size;
        int i = this.heightCount;
        int i2 = this.widthCount;
        int i3 = (size * i) / i2;
        this.heightSize = i3;
        int i4 = size % i2;
        this.xMore = i4;
        int i5 = i3 % i;
        this.yMore = i5;
        int i6 = size / i2;
        this.pointAllLength = i6;
        this.pointLength = i6 - (this.pointMargin * 2);
        this.offset = i4 / 2;
        setMeasuredDimension(size - i4, i3 - i5);
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isEnabled()) {
            return true;
        }
        if (!this.mPaintItem.getArrPointXY().isEmpty()) {
            this.mPaintItem.getArrPointXY().clear();
        }
        if (!this.mPaintItem.getArrDifColorPointXY().isEmpty()) {
            this.mPaintItem.getArrDifColorPointXY().clear();
        }
        if (this.isDispatchTouch) {
            if (this.mIsUnDoClick) {
                this.mIsUnDoClick = false;
                DrawPath remove = this.undoRedoList.getUndoList().remove(this.undoRedoList.getUndoList().size() - 1);
                DrawPath drawPath = new DrawPath();
                for (int i = 0; i < remove.getViews().size(); i++) {
                    ItemView itemView = new ItemView();
                    if (remove.getViews().get(i).isSelected && this.whichChildView != i && !remove.getViews().get(i).getColors().isEmpty()) {
                        itemView = remove.getViews().get(i);
                    } else {
                        itemView.getColors().add(Integer.valueOf(Color.parseColor("#000000")));
                    }
                    itemView.index = remove.getViews().get(i).index;
                    drawPath.getViews().add(itemView);
                }
                this.undoRedoList.getUndoList().add(drawPath);
            }
            if (doDispatchTouch(ev)) {
                return true;
            }
            return this.isDispatchTouch;
        }
        return undoDispatchTouch(ev);
    }

    private boolean isTouchLeft() {
        int totalCount = getTotalCount();
        for (int i = 0; i < totalCount; i++) {
            if (this.itemViews.get(i).isSelected && i <= totalCount - 1 && i % this.widthCount == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isTouchRight() {
        int totalCount = getTotalCount();
        for (int i = 0; i < totalCount; i++) {
            if (this.itemViews.get(i).isSelected && i <= totalCount - 1 && (i + 1) % this.widthCount == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isTouchBottom() {
        int totalCount = getTotalCount();
        for (int i = 0; i < totalCount; i++) {
            if (this.itemViews.get(i).isSelected && i <= totalCount - 1 && i / this.widthCount >= this.heightCount - 1) {
                return true;
            }
        }
        return false;
    }

    public void undo() {
        List list;
        int i = 1;
        this.bIsUnRedoClick = true;
        this.mIsUnDoClick = true;
        DrawPath undo = this.undoRedoList.undo();
        if (undo == null) {
            return;
        }
        List list2 = undo.views;
        this.itemViews.clear();
        this.itemViews.addAll(list2);
        this.mPaintItem.getArrPointXY().clear();
        this.mPaintItem.getArrDifColorPointXY().clear();
        this.mPaintItem.getArrMapDifColorPointXY().clear();
        int size = this.undoRedoList.getUndoList().size();
        List list3 = this.undoRedoList.getRedoList().get(this.undoRedoList.getRedoList().size() - 1).views;
        int i2 = size - 1;
        List list4 = this.undoRedoList.getUndoList().get(i2).views;
        int intValue = AppConfig.INSTANCE.getLedSize().get(0).intValue();
        int intValue2 = AppConfig.INSTANCE.getLedSize().get(1).intValue();
        if (size == 1) {
            SendCore.INSTANCE.setDiyFunMode(DiyImageFun.ENTER_CLEAR_CUR_SHOW.getMode(), new SendResultCallback() { // from class: com.wifiled.ipixels.view.LedView.1
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    ToastUtil.show(CoreBase.getContext().getString(R.string.msg_send_fail) + "(" + result + ")");
                }
            });
        } else {
            int parseColor = Color.parseColor("#000000");
            if (this.arrayMarkSelColor.size() == 1) {
                i2 = 0;
            }
            if (i2 >= this.arrayMarkSelColor.size() && !this.arrayMarkSelColor.isEmpty()) {
                i2 = this.arrayMarkSelColor.size() - 1;
            }
            if (this.arrayMarkSelColor.size() > i2 && size > 1 && parseColor != this.arrayMarkSelColor.get(i2).intValue()) {
                int i3 = this.prevColorIndex;
                if (i3 != i2) {
                    this.prevColorIndex = i2;
                } else {
                    int i4 = i3 - 1;
                    this.prevColorIndex = i4;
                    if (i4 < 0) {
                        this.prevColorIndex = 0;
                    }
                }
                parseColor = this.arrayMarkSelColor.get(this.prevColorIndex).intValue();
            }
            int i5 = 0;
            while (i5 < list2.size()) {
                ItemView itemView = (ItemView) list2.get(i5);
                if (itemView.isSelected || ((ItemView) list3.get(i5)).isSelected || (((ItemView) list3.get(i5)).getColors().isEmpty() && ((ItemView) list3.get(i5)).getRecycleColors().isEmpty())) {
                    int i6 = i5 % intValue2;
                    int i7 = i5 / intValue;
                    if (isRoutDiyImage() && ((ItemView) list3.get(i5)).getColors().size() >= 2 && ((ItemView) list3.get(i5)).getColors().peek().intValue() == parseColor) {
                        if (itemView.getColors().size() >= 2) {
                            parseColor = ((ItemView) list2.get(i5)).getColors().peek().intValue();
                            this.mPaintItem.setDifColor(parseColor);
                            this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i6));
                            this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i7));
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(Integer.valueOf(i6));
                            arrayList.add(Integer.valueOf(i7));
                            if (this.mPaintItem.getArrMapDifColorPointXY().containsKey(Integer.valueOf(parseColor))) {
                                arrayList.addAll(this.mPaintItem.getArrMapDifColorPointXY().get(Integer.valueOf(parseColor)));
                            }
                            this.mPaintItem.getArrMapDifColorPointXY().put(Integer.valueOf(parseColor), arrayList);
                        } else {
                            int parseColor2 = Color.parseColor("#000000");
                            if (((ItemView) list3.get(i5)).getColors().isEmpty() && ((ItemView) list3.get(i5)).getRecycleColors().isEmpty() && !((ItemView) list2.get(i5)).getColors().isEmpty()) {
                                parseColor2 = ((ItemView) list2.get(i5)).getColors().peek().intValue();
                            }
                            this.mPaintItem.setSelColor(parseColor2);
                            this.mPaintItem.getArrPointXY().add(Integer.valueOf(i6));
                            this.mPaintItem.getArrPointXY().add(Integer.valueOf(i7));
                        }
                    } else if (!((ItemView) list4.get(i5)).isSelected) {
                        int parseColor3 = Color.parseColor("#000000");
                        if (((ItemView) list4.get(i5)).getColors().isEmpty() && ((ItemView) list4.get(i5)).getRecycleColors().isEmpty() && !((ItemView) list2.get(i5)).getColors().isEmpty()) {
                            parseColor3 = ((ItemView) list2.get(i5)).getColors().peek().intValue();
                        }
                        this.mPaintItem.setDifColor(parseColor3);
                        this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i6));
                        this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i7));
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(Integer.valueOf(i6));
                        arrayList2.add(Integer.valueOf(i7));
                        if (this.mPaintItem.getArrMapDifColorPointXY().containsKey(Integer.valueOf(parseColor3))) {
                            arrayList2.addAll(this.mPaintItem.getArrMapDifColorPointXY().get(Integer.valueOf(parseColor3)));
                        }
                        this.mPaintItem.getArrMapDifColorPointXY().put(Integer.valueOf(parseColor3), arrayList2);
                    } else {
                        if (((ItemView) list3.get(i5)).getColors().size() >= 2) {
                            int i8 = this.arrayMarkSelColor.size() == i ? 0 : this.prevColorIndex;
                            int intValue3 = ((ItemView) list3.get(i5)).getColors().get(isRoutDiyImage() ? ((ItemView) list3.get(i5)).getColors().size() - i : 0).intValue();
                            int i9 = 0;
                            while (true) {
                                if (i9 >= ((ItemView) list3.get(i5)).getColors().size()) {
                                    list = list2;
                                    i = 1;
                                    break;
                                }
                                if (this.arrayMarkSelColor.size() > i8) {
                                    list = list2;
                                    if (((ItemView) list3.get(i5)).getColors().get(i9).intValue() == this.arrayMarkSelColor.get(i8).intValue()) {
                                        i = 1;
                                        if (size > 1 && intValue3 != this.arrayMarkSelColor.get(i8).intValue()) {
                                            intValue3 = this.arrayMarkSelColor.get(i8).intValue();
                                        }
                                    }
                                } else {
                                    list = list2;
                                }
                                i9++;
                                list2 = list;
                            }
                            this.mPaintItem.setDifColor(intValue3);
                            this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i6));
                            this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i7));
                            ArrayList arrayList3 = new ArrayList();
                            arrayList3.add(Integer.valueOf(i6));
                            arrayList3.add(Integer.valueOf(i7));
                            if (this.mPaintItem.getArrMapDifColorPointXY().containsKey(Integer.valueOf(intValue3))) {
                                arrayList3.addAll(this.mPaintItem.getArrMapDifColorPointXY().get(Integer.valueOf(intValue3)));
                            }
                            this.mPaintItem.getArrMapDifColorPointXY().put(Integer.valueOf(intValue3), arrayList3);
                        } else {
                            list = list2;
                            if (!itemView.getColors().isEmpty()) {
                                int intValue4 = itemView.getColors().peek().intValue();
                                if (intValue4 == parseColor) {
                                    this.mPaintItem.setDifColor(intValue4);
                                    this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i6));
                                    this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i7));
                                    ArrayList arrayList4 = new ArrayList();
                                    arrayList4.add(Integer.valueOf(i6));
                                    arrayList4.add(Integer.valueOf(i7));
                                    if (this.mPaintItem.getArrMapDifColorPointXY().containsKey(Integer.valueOf(intValue4))) {
                                        arrayList4.addAll(this.mPaintItem.getArrMapDifColorPointXY().get(Integer.valueOf(intValue4)));
                                    }
                                    this.mPaintItem.getArrMapDifColorPointXY().put(Integer.valueOf(intValue4), arrayList4);
                                } else if (itemView.getColors().size() == ((ItemView) list4.get(i5)).getColors().size() && itemView.getRecycleColors().size() == ((ItemView) list4.get(i5)).getRecycleColors().size()) {
                                    int intValue5 = itemView.getColors().peek().intValue();
                                    this.mPaintItem.setDifColor(intValue5);
                                    this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i6));
                                    this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i7));
                                    ArrayList arrayList5 = new ArrayList();
                                    arrayList5.add(Integer.valueOf(i6));
                                    arrayList5.add(Integer.valueOf(i7));
                                    if (this.mPaintItem.getArrMapDifColorPointXY().containsKey(Integer.valueOf(intValue5))) {
                                        arrayList5.addAll((Collection) Objects.requireNonNull((List) Objects.requireNonNull(this.mPaintItem.getArrMapDifColorPointXY().get(Integer.valueOf(intValue5)))));
                                    }
                                    this.mPaintItem.getArrMapDifColorPointXY().put(Integer.valueOf(intValue5), arrayList5);
                                }
                            }
                        }
                        i5++;
                        list2 = list;
                    }
                } else if (itemView.getColors().isEmpty() && !itemView.getRecycleColors().isEmpty()) {
                    this.mPaintItem.setSelColor(Color.parseColor("#000000"));
                    this.mPaintItem.getArrPointXY().add(Integer.valueOf(i5 % intValue2));
                    this.mPaintItem.getArrPointXY().add(Integer.valueOf(i5 / intValue));
                }
                list = list2;
                i5++;
                list2 = list;
            }
        }
        invalidate();
    }

    public void redo() {
        this.bIsUnRedoClick = true;
        DrawPath redo = this.undoRedoList.redo();
        if (redo == null) {
            return;
        }
        List list = redo.views;
        this.itemViews.clear();
        this.itemViews.addAll(list);
        int intValue = AppConfig.INSTANCE.getLedSize().get(0).intValue();
        int intValue2 = AppConfig.INSTANCE.getLedSize().get(1).intValue();
        if (!this.mPaintItem.getArrPointXY().isEmpty()) {
            this.mPaintItem.getArrPointXY().clear();
        }
        if (!this.mPaintItem.getArrDifColorPointXY().isEmpty()) {
            this.mPaintItem.getArrDifColorPointXY().clear();
        }
        this.mPaintItem.getArrMapDifColorPointXY().clear();
        if (!this.undoRedoList.getRedoList().isEmpty()) {
            redo = this.undoRedoList.getRedoList().get(this.undoRedoList.getRedoList().size() - 1);
        }
        List list2 = redo.views;
        List list3 = this.undoRedoList.getUndoList().get(this.undoRedoList.getUndoList().size() - 1).views;
        int parseColor = Color.parseColor("#000000");
        int size = this.arrayMarkSelColor.size() == 1 ? 0 : this.undoRedoList.getUndoList().size() - 1;
        if (size >= this.arrayMarkSelColor.size() && !this.arrayMarkSelColor.isEmpty()) {
            size = this.arrayMarkSelColor.size() - 1;
        }
        if (this.arrayMarkSelColor.size() > size && this.undoRedoList.getUndoList().size() >= 2 && parseColor != this.arrayMarkSelColor.get(size).intValue()) {
            if (this.prevColorIndex != size) {
                this.prevColorIndex = size;
            } else if (isRoutDiyImage()) {
                int i = this.prevColorIndex + 1;
                this.prevColorIndex = i;
                if (i >= this.arrayMarkSelColor.size()) {
                    this.prevColorIndex = this.arrayMarkSelColor.size() - 1;
                }
            }
            parseColor = this.arrayMarkSelColor.get(this.prevColorIndex).intValue();
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ItemView itemView = (ItemView) list.get(i2);
            if (itemView.isSelected || ((ItemView) list3.get(i2)).isSelected || (itemView.getColors().isEmpty() && itemView.getRecycleColors().isEmpty())) {
                int i3 = i2 % intValue2;
                int i4 = i2 / intValue;
                if (itemView.getColors().size() >= 2) {
                    int intValue3 = itemView.getColors().peek().intValue();
                    this.mPaintItem.setDifColor(intValue3);
                    this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i3));
                    this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i4));
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(i3));
                    arrayList.add(Integer.valueOf(i4));
                    if (this.mPaintItem.getArrMapDifColorPointXY().containsKey(Integer.valueOf(intValue3))) {
                        arrayList.addAll(this.mPaintItem.getArrMapDifColorPointXY().get(Integer.valueOf(intValue3)));
                    }
                    this.mPaintItem.getArrMapDifColorPointXY().put(Integer.valueOf(intValue3), arrayList);
                } else if (!((ItemView) list3.get(i2)).isSelected) {
                    int intValue4 = ((ItemView) list3.get(i2)).getColors().isEmpty() ? parseColor : ((ItemView) list3.get(i2)).getColors().peek().intValue();
                    if (itemView.getColors().isEmpty() && itemView.getRecycleColors().isEmpty()) {
                        intValue4 = Color.parseColor("#000000");
                    }
                    this.mPaintItem.setSelColor(intValue4);
                    this.mPaintItem.getArrPointXY().add(Integer.valueOf(i3));
                    this.mPaintItem.getArrPointXY().add(Integer.valueOf(i4));
                } else if (!((ItemView) list2.get(i2)).isSelected && ((ItemView) list2.get(i2)).getColors().isEmpty()) {
                    int parseColor2 = Color.parseColor("#000000");
                    this.mPaintItem.setDifColor(parseColor2);
                    this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i3));
                    this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i4));
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(Integer.valueOf(i3));
                    arrayList2.add(Integer.valueOf(i4));
                    if (this.mPaintItem.getArrMapDifColorPointXY().containsKey(Integer.valueOf(parseColor2))) {
                        arrayList2.addAll(this.mPaintItem.getArrMapDifColorPointXY().get(Integer.valueOf(parseColor2)));
                    }
                    this.mPaintItem.getArrMapDifColorPointXY().put(Integer.valueOf(parseColor2), arrayList2);
                } else {
                    int intValue5 = itemView.getColors().isEmpty() ? parseColor : itemView.getColors().peek().intValue();
                    if (intValue5 == parseColor) {
                        this.mPaintItem.setDifColor(intValue5);
                        this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i3));
                        this.mPaintItem.getArrDifColorPointXY().add(Integer.valueOf(i4));
                        ArrayList arrayList3 = new ArrayList();
                        arrayList3.add(Integer.valueOf(i3));
                        arrayList3.add(Integer.valueOf(i4));
                        if (this.mPaintItem.getArrMapDifColorPointXY().containsKey(Integer.valueOf(intValue5))) {
                            arrayList3.addAll(this.mPaintItem.getArrMapDifColorPointXY().get(Integer.valueOf(intValue5)));
                        }
                        this.mPaintItem.getArrMapDifColorPointXY().put(Integer.valueOf(intValue5), arrayList3);
                    }
                }
            } else if (itemView.getColors().isEmpty() && itemView.getRecycleColors().size() >= 1) {
                this.mPaintItem.setSelColor(Color.parseColor("#000000"));
                this.mPaintItem.getArrPointXY().add(Integer.valueOf(i2 % intValue2));
                this.mPaintItem.getArrPointXY().add(Integer.valueOf(i2 / intValue));
            }
        }
        invalidate();
    }

    public void clear() {
        for (ItemView itemView : this.itemViews) {
            itemView.isSelected = false;
            itemView.getColors().clear();
            itemView.getRecycleColors().clear();
        }
        this.arrayMarkSelColor.clear();
        if (this.mPaintItem.getArrPointXY().size() > 0) {
            this.mPaintItem.getArrPointXY().clear();
        }
        if (this.mPaintItem.getArrDifColorPointXY().size() > 0) {
            this.mPaintItem.getArrDifColorPointXY().clear();
        }
        this.mPaintItem.getArrMapDifColorPointXY().clear();
        invalidate();
    }

    public UndoRedoList<DrawPath> getUndoRedoList() {
        return this.undoRedoList;
    }

    private void touchDown() {
        this.isHasAdded = false;
    }

    private void touchUp() {
        int i = this.mode;
        if (i == 1 || i == 2) {
            this.tWhichRow = -1;
            this.tWhichColumn = -1;
            this.tMarkColor = Color.parseColor("#000000");
            DrawPath drawPath = new DrawPath();
            for (ItemView itemView : this.itemViews) {
                ItemView itemView2 = new ItemView();
                if (!itemView.getColors().empty()) {
                    itemView2.getColors().addAll(itemView.getColors());
                }
                if (isRoutDiyImage() && !itemView.getRecycleColors().isEmpty()) {
                    itemView2.getRecycleColors().addAll(itemView.getRecycleColors());
                }
                itemView2.isSelected = itemView.isSelected;
                itemView2.index = itemView.index;
                drawPath.getViews().add(itemView2);
            }
            this.undoRedoList.getUndoList().add(drawPath);
        }
        if (this.isDispatchTouch) {
            return;
        }
        this.ledListener.onItemSelect(getRGBData());
    }

    public boolean doDispatchTouch(MotionEvent ev) {
        int action = ev.getAction();
        float scrollX = getScrollX();
        float y = ev.getY();
        float x = ev.getX() + scrollX;
        if (action == 0 || action == 2) {
            if (!TimeHelper.INSTANCE.allowSend(50) || x < 0.0f || this.widthSize + scrollX < x || y < 0.0f || this.heightSize < y) {
                return true;
            }
            int i = this.offset;
            int i2 = this.pointAllLength;
            int i3 = (int) ((y - i) / i2);
            int i4 = (int) ((x - i) / i2);
            if (i3 >= this.heightCount) {
                return true;
            }
            if ((AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 12) && this.widthCount == 128 && ScreenUtils.getScreenWidth() == 1080) {
                i3 += 5;
                i4 += 5;
            }
            if (AppConfig.INSTANCE.getLedType() == 7 && ScreenUtils.getScreenWidth() == 1080) {
                i3 += 8;
                i4 += 8;
            }
            if (AppConfig.INSTANCE.getLedType() == 8 || AppConfig.INSTANCE.getLedType() == 15) {
                if (ScreenUtils.getScreenWidth() == 1080) {
                    i3++;
                    i4++;
                } else {
                    i3 += 12;
                    i4 += 12;
                }
            }
            if (AppConfig.INSTANCE.getLedType() == 14) {
                i4++;
            }
            int i5 = this.widthCount;
            if (i4 >= i5) {
                return true;
            }
            int i6 = (i5 * i3) + i4;
            this.whichChildView = i6;
            if (i6 >= 0 && i6 < getTotalCount()) {
                if (action == 0) {
                    touchDown();
                }
                int i7 = this.mode;
                if (i7 == 1) {
                    int i8 = this.iCmpWhichChildView;
                    int i9 = this.whichChildView;
                    if (i8 != i9) {
                        this.iCmpWhichChildView = i9;
                        this.tPushColor = Color.parseColor("#000000");
                    }
                    ItemView itemView = this.itemViews.get(this.whichChildView);
                    itemView.isSelected = true;
                    int i10 = this.tMarkColor;
                    int i11 = this.selectedColor;
                    if (i10 != i11) {
                        this.tMarkColor = i11;
                        this.arrayMarkSelColor.add(Integer.valueOf(i11));
                    }
                    int i12 = this.tPushColor;
                    int i13 = this.selectedColor;
                    if (i12 != i13) {
                        this.tPushColor = i13;
                        this.isColorChange = true;
                        if (isRoutDiyImage()) {
                            itemView.getRecycleColors().push(Integer.valueOf(this.selectedColor));
                        }
                        itemView.getColors().push(Integer.valueOf(this.selectedColor));
                    }
                    int i14 = this.mirrorMode;
                    if (i14 == 1) {
                        ItemView itemView2 = this.itemViews.get((((this.heightCount - 1) - i3) * this.widthCount) + i4);
                        itemView2.isSelected = true;
                        int i15 = this.tPushColor;
                        int i16 = this.selectedColor;
                        if (i15 != i16) {
                            this.tPushColor = i16;
                            this.isColorChange = true;
                        }
                        itemView2.getColors().push(Integer.valueOf(this.selectedColor));
                    } else if (i14 == 0) {
                        int i17 = this.widthCount;
                        ItemView itemView3 = this.itemViews.get((i3 * i17) + ((i17 - 1) - i4));
                        itemView3.isSelected = true;
                        int i18 = this.tPushColor;
                        int i19 = this.selectedColor;
                        if (i18 != i19) {
                            this.tPushColor = i19;
                            this.isColorChange = true;
                        }
                        itemView3.getColors().push(Integer.valueOf(this.selectedColor));
                    }
                    if (this.tWhichRow != i3 || this.tWhichColumn != i4 || this.isColorChange) {
                        this.isColorChange = false;
                        this.tWhichRow = i3;
                        this.tWhichColumn = i4;
                        this.mPaintItem.setSelColor(this.selectedColor);
                        this.mPaintItem.setColumn(i4);
                        this.mPaintItem.setRow(i3);
                        this.mPaintItem.setMoveType(this.mirrorMode + 1);
                        this.ledDiyListener.onRunTimeItemSelect(this.mPaintItem);
                    }
                    invalidate();
                } else {
                    if (i7 != 2) {
                        if (i7 == 3) {
                            int rawX = (int) ev.getRawX();
                            int rawY = (int) ev.getRawY();
                            if (ev.getAction() == 2) {
                                int i20 = rawX - this.mLastX;
                                int i21 = rawY - this.mLastY;
                                int translationX = ((int) getTranslationX()) + i20;
                                int translationY = ((int) getTranslationY()) + i21;
                                if (Math.abs(translationX) < ScreenUtil.getScreenWidth(App.context) * 0.9f * 0.9f && Math.abs(translationY) < ScreenUtil.getScreenWidth(App.context) * 0.9f * 0.9f) {
                                    setTranslationX(translationX);
                                    setTranslationY(translationY);
                                }
                            }
                            this.mLastX = rawX;
                            this.mLastY = rawY;
                        }
                        return true;
                    }
                    ItemView itemView4 = this.itemViews.get(this.whichChildView);
                    if (this.tWhichRow != i3 || this.tWhichColumn != i4 || this.isColorChange) {
                        this.tWhichRow = i3;
                        this.tWhichColumn = i4;
                        if (itemView4.isSelected || isRoutDiyImage()) {
                            int parseColor = Color.parseColor("#000000");
                            this.itemColorTotalSize = itemView4.getRecycleColors().size();
                            if (isRoutDiyImage() && this.itemColorTotalSize > 0) {
                                itemView4.getRecycleColors().pop();
                                if (itemView4.getColors().size() > 0) {
                                    itemView4.getColors().pop();
                                }
                                int size = itemView4.getRecycleColors().size();
                                this.itemColorTotalSize = size;
                                if (size > 0) {
                                    parseColor = itemView4.getRecycleColors().get(this.itemColorTotalSize - 1).intValue();
                                }
                            }
                            this.mPaintItem.setSelColor(parseColor);
                            this.mPaintItem.setColumn(i4);
                            this.mPaintItem.setRow(i3);
                            this.mPaintItem.setMoveType(this.mirrorMode + 1);
                            this.ledDiyListener.onRunTimeItemSelect(this.mPaintItem);
                        }
                        itemView4.isSelected = false;
                        if (!isRoutDiyImage()) {
                            itemView4.getColors().clear();
                            itemView4.getRecycleColors().clear();
                        }
                    }
                    int i22 = this.mirrorMode;
                    if (i22 == 1) {
                        ItemView itemView5 = this.itemViews.get((((this.heightCount - 1) - i3) * this.widthCount) + i4);
                        int i23 = this.tWhichRow;
                        int i24 = this.heightCount;
                        if (i23 != (i24 - 1) - i3 || this.tWhichColumn != i4 || this.isColorChange) {
                            this.tWhichRow = (i24 - 1) - i3;
                            this.tWhichColumn = i4;
                            if (itemView5.isSelected || isRoutDiyImage()) {
                                int parseColor2 = Color.parseColor("#000000");
                                this.itemColorTotalSize = itemView5.getRecycleColors().size();
                                if (isRoutDiyImage() && this.itemColorTotalSize > 0) {
                                    itemView5.getRecycleColors().pop();
                                    if (itemView5.getColors().size() > 0) {
                                        itemView5.getColors().pop();
                                    }
                                    int size2 = itemView5.getRecycleColors().size();
                                    this.itemColorTotalSize = size2;
                                    if (size2 > 0) {
                                        parseColor2 = itemView5.getRecycleColors().get(this.itemColorTotalSize - 1).intValue();
                                    }
                                }
                                this.mPaintItem.setSelColor(parseColor2);
                                this.mPaintItem.setColumn(i4);
                                this.mPaintItem.setRow(i3);
                                this.mPaintItem.setMoveType(this.mirrorMode + 1);
                                this.ledDiyListener.onRunTimeItemSelect(this.mPaintItem);
                            }
                            itemView5.isSelected = false;
                            if (!isRoutDiyImage()) {
                                itemView5.getColors().clear();
                                itemView5.getRecycleColors().clear();
                            }
                        }
                    } else if (i22 == 0) {
                        int i25 = this.widthCount;
                        ItemView itemView6 = this.itemViews.get((i3 * i25) + ((i25 - 1) - i4));
                        if (this.tWhichRow != i3 || this.tWhichColumn != (this.widthCount - 1) - i4 || this.isColorChange) {
                            this.tWhichRow = i3;
                            this.tWhichColumn = (this.widthCount - 1) - i4;
                            if (itemView6.isSelected || isRoutDiyImage()) {
                                int parseColor3 = Color.parseColor("#000000");
                                this.itemColorTotalSize = itemView6.getRecycleColors().size();
                                if (isRoutDiyImage() && this.itemColorTotalSize > 0) {
                                    itemView6.getRecycleColors().pop();
                                    if (!itemView6.getColors().isEmpty()) {
                                        itemView6.getColors().pop();
                                    }
                                    int size3 = itemView6.getRecycleColors().size();
                                    this.itemColorTotalSize = size3;
                                    if (size3 > 0) {
                                        parseColor3 = itemView6.getRecycleColors().get(this.itemColorTotalSize - 1).intValue();
                                    }
                                }
                                this.mPaintItem.setSelColor(parseColor3);
                                this.mPaintItem.setColumn(i4);
                                this.mPaintItem.setRow(i3);
                                this.mPaintItem.setMoveType(this.mirrorMode + 1);
                                this.ledDiyListener.onRunTimeItemSelect(this.mPaintItem);
                            }
                            itemView6.isSelected = false;
                            if (!isRoutDiyImage()) {
                                itemView6.getColors().clear();
                                itemView6.getRecycleColors().clear();
                            }
                        }
                    }
                    invalidate();
                }
            }
        }
        if (action == 1 && !this.isHasAdded) {
            touchUp();
        }
        return false;
    }

    public boolean undoDispatchTouch(MotionEvent ev) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        char c;
        ItemView itemView;
        int i7;
        int rawX = (int) ev.getRawX();
        int rawY = (int) ev.getRawY();
        int action = ev.getAction();
        if (action != 0) {
            if (action != 1) {
                int i8 = 2;
                if (action == 2 && TimeHelper.INSTANCE.allowSend(50)) {
                    int i9 = rawX - this.mLastX;
                    int i10 = rawY - this.mLastY;
                    LogUtils.logi("LedView>>>undoDispatchTouch[prev MODE_MOVE----] deltaX:" + i9 + "----deltaY:[" + i10 + "]mLastX:[" + this.mLastX + "]mLastY:[" + this.mLastY + "]--pointLength:" + this.pointLength, new Object[0]);
                    if (Math.abs(i9) >= this.pointLength / 3 || Math.abs(i10) >= this.pointLength / 3) {
                        int i11 = i9 > 0 ? 1 : -1;
                        int i12 = i10 > 0 ? 1 : -1;
                        int totalCount = getTotalCount();
                        HashMap hashMap = new HashMap();
                        AppConfig.INSTANCE.getTopActivity();
                        int i13 = 0;
                        int i14 = 0;
                        boolean z = false;
                        while (true) {
                            if (i13 >= totalCount) {
                                i3 = rawX;
                                i4 = rawY;
                                break;
                            }
                            ItemView itemView2 = this.itemViews.get(i13);
                            int i15 = i8;
                            if (itemView2.isSelected && i13 <= totalCount - 1) {
                                ItemView itemView3 = new ItemView();
                                itemView3.isSelected = itemView2.isSelected;
                                itemView3.getColors().addAll(itemView2.getColors());
                                i5 = i9;
                                i6 = i10;
                                boolean z2 = z;
                                i4 = rawY;
                                i3 = rawX;
                                if (Math.abs(i9) >= this.pointLength / 2 && Math.abs(i6) < this.pointLength / 2) {
                                    if ((isTouchRight() && i5 > 0) || (isTouchLeft() && i5 < 0)) {
                                        break;
                                    }
                                    if (z2) {
                                        z = z2;
                                    } else {
                                        if (i5 > 0) {
                                            byte b = this.moveR;
                                            if (b == 0) {
                                                this.moveR = (byte) (b + 1);
                                            }
                                        } else {
                                            byte b2 = this.moveL;
                                            if (b2 == 0) {
                                                this.moveL = (byte) (b2 + 1);
                                            }
                                        }
                                        LogUtils.logi("LedView>>> 水平移动moveUp:[" + ((int) this.moveUp) + "]moveBottom:[" + ((int) this.moveBottom) + "] moveL:[" + ((int) this.moveL) + "]moveR:[" + ((int) this.moveR) + "]", new Object[0]);
                                        this.mArrMoveVal.put(0, this.moveUp);
                                        this.mArrMoveVal.put(1, this.moveBottom);
                                        this.mArrMoveVal.put(i15, this.moveL);
                                        this.mArrMoveVal.put(3, this.moveR);
                                        this.mPaintItem.setArrMoveDirectionNum(this.mArrMoveVal.array());
                                        this.mPaintItem.setMoveType(DiyImageMoveType.OVERALL_MOVEMENT.getMode());
                                        this.ledDiyListener.onRunTimeItemSelect(this.mPaintItem);
                                        this.moveL = (byte) 0;
                                        this.moveR = (byte) 0;
                                        this.moveUp = (byte) 0;
                                        this.moveBottom = (byte) 0;
                                        z = true;
                                    }
                                    i7 = i13 + i11;
                                    LogUtils.logi("LedView>>>#1.0.1#undoDispatchTouch[prev ACTION_DOWN----] xIndex:[" + i11 + "]yIndex:[" + i12 + "]--i:[" + i13 + "]index:[" + i7 + "]", new Object[0]);
                                    ItemView itemView4 = this.itemViews.get(i7);
                                    if (itemView3.getRecycleColors().isEmpty() && this.bRoutDiyImage && !itemView4.getRecycleColors().isEmpty()) {
                                        itemView3.getRecycleColors().push(itemView4.getRecycleColors().firstElement());
                                    }
                                    hashMap.put(Integer.valueOf(i7), itemView3);
                                    i14 = i7;
                                    itemView = itemView2;
                                    i8 = 2;
                                    c = 3;
                                    itemView.getColors().clear();
                                    itemView.isSelected = false;
                                } else if (Math.abs(i6) >= this.pointLength / 2 && Math.abs(i5) < this.pointLength / 2) {
                                    if ((i13 / this.widthCount <= 0 && i6 < 0) || (isTouchBottom() && i6 > 0)) {
                                        break;
                                    }
                                    if (z2) {
                                        z = z2;
                                    } else {
                                        if (i6 > 0) {
                                            byte b3 = this.moveBottom;
                                            if (b3 == 0) {
                                                this.moveBottom = (byte) (b3 + 1);
                                            }
                                        } else {
                                            byte b4 = this.moveUp;
                                            if (b4 == 0) {
                                                this.moveUp = (byte) (b4 + 1);
                                            }
                                        }
                                        LogUtils.logi("LedView>>> 竖直移动 moveUp:[" + ((int) this.moveUp) + "]moveBottom:[" + ((int) this.moveBottom) + "] moveL:[" + ((int) this.moveL) + "]moveR:[" + ((int) this.moveR) + "]", new Object[0]);
                                        this.mArrMoveVal.put(0, this.moveUp);
                                        this.mArrMoveVal.put(1, this.moveBottom);
                                        this.mArrMoveVal.put(2, this.moveL);
                                        this.mArrMoveVal.put(3, this.moveR);
                                        this.mPaintItem.setArrMoveDirectionNum(this.mArrMoveVal.array());
                                        this.mPaintItem.setMoveType(DiyImageMoveType.OVERALL_MOVEMENT.getMode());
                                        this.ledDiyListener.onRunTimeItemSelect(this.mPaintItem);
                                        this.moveL = (byte) 0;
                                        this.moveR = (byte) 0;
                                        this.moveUp = (byte) 0;
                                        this.moveBottom = (byte) 0;
                                        z = true;
                                    }
                                    i7 = (this.widthCount * i12) + i13;
                                    LogUtils.logi("LedView>>>#1.1.1#undoDispatchTouch[prev ACTION_DOWN----] yIndex:[" + i12 + "]xIndex:[" + i11 + "] ----i:[" + i13 + "]index:[" + i7 + "]", new Object[0]);
                                    ItemView itemView5 = this.itemViews.get(i7);
                                    if (itemView3.getRecycleColors().isEmpty() && this.bRoutDiyImage && !itemView5.getRecycleColors().isEmpty()) {
                                        itemView3.getRecycleColors().push(itemView5.getRecycleColors().firstElement());
                                    }
                                    hashMap.put(Integer.valueOf(i7), itemView3);
                                    i14 = i7;
                                    itemView = itemView2;
                                    i8 = 2;
                                    c = 3;
                                    itemView.getColors().clear();
                                    itemView.isSelected = false;
                                } else {
                                    if (Math.abs(i6) < this.pointLength / 2 || Math.abs(i5) < this.pointLength / 2) {
                                        break;
                                    }
                                    itemView = itemView2;
                                    LogUtils.logi("LedView>>>#1.2.0#undoDispatchTouch[prev ACTION_DOWN----] yIndex:" + i12 + "xIndex:" + i11 + "----i:[" + i13 + "]index:[" + i14 + "]", new Object[0]);
                                    if ((isTouchBottom() && i6 > 0) || ((i13 / this.widthCount <= 0 && i6 < 0) || ((isTouchRight() && i5 > 0) || (isTouchLeft() && i5 < 0)))) {
                                        break;
                                    }
                                    if (z2) {
                                        i8 = 2;
                                        c = 3;
                                        z = z2;
                                    } else {
                                        if (i5 > 0) {
                                            byte b5 = this.moveR;
                                            if (b5 == 0) {
                                                this.moveR = (byte) (b5 + 1);
                                            }
                                        } else {
                                            byte b6 = this.moveL;
                                            if (b6 == 0) {
                                                this.moveL = (byte) (b6 + 1);
                                            }
                                        }
                                        if (i6 > 0) {
                                            byte b7 = this.moveBottom;
                                            if (b7 == 0) {
                                                this.moveBottom = (byte) (b7 + 1);
                                            }
                                        } else {
                                            byte b8 = this.moveUp;
                                            if (b8 == 0) {
                                                this.moveUp = (byte) (b8 + 1);
                                            }
                                        }
                                        LogUtils.logi("LedView>>> 倾斜移动 moveUp:[" + ((int) this.moveUp) + "]moveBottom:[" + ((int) this.moveBottom) + "] moveL:[" + ((int) this.moveL) + "]moveR:[" + ((int) this.moveR) + "]", new Object[0]);
                                        this.mArrMoveVal.put(0, this.moveUp);
                                        this.mArrMoveVal.put(1, this.moveBottom);
                                        i8 = 2;
                                        this.mArrMoveVal.put(2, this.moveL);
                                        c = 3;
                                        this.mArrMoveVal.put(3, this.moveR);
                                        this.mPaintItem.setArrMoveDirectionNum(this.mArrMoveVal.array());
                                        this.mPaintItem.setMoveType(DiyImageMoveType.OVERALL_MOVEMENT.getMode());
                                        this.ledDiyListener.onRunTimeItemSelect(this.mPaintItem);
                                        this.moveL = (byte) 0;
                                        this.moveR = (byte) 0;
                                        this.moveUp = (byte) 0;
                                        this.moveBottom = (byte) 0;
                                        z = true;
                                    }
                                    int i16 = (this.widthCount * i12) + i13 + i11;
                                    LogUtils.logi("LedView>>>#1.2.1#undoDispatchTouch[prev ACTION_DOWN----] yIndex:" + i12 + "xIndex:" + i11 + "----i:[" + i13 + "]index:[" + i16 + "]", new Object[0]);
                                    if (i16 < 0 || i16 >= totalCount) {
                                        break;
                                    }
                                    ItemView itemView6 = this.itemViews.get(i16);
                                    if (itemView3.getRecycleColors().isEmpty() && this.bRoutDiyImage && !itemView6.getRecycleColors().isEmpty()) {
                                        itemView3.getRecycleColors().push(itemView6.getRecycleColors().firstElement());
                                    }
                                    hashMap.put(Integer.valueOf(i16), itemView3);
                                    i14 = i16;
                                    itemView.getColors().clear();
                                    itemView.isSelected = false;
                                }
                            } else {
                                i3 = rawX;
                                i4 = rawY;
                                i5 = i9;
                                i6 = i10;
                                i8 = i15;
                                c = 3;
                                z = z;
                            }
                            i13++;
                            i9 = i5;
                            i10 = i6;
                            rawY = i4;
                            rawX = i3;
                        }
                        ArrayList<Map.Entry> arrayList = new ArrayList(hashMap.entrySet());
                        Collections.sort(arrayList, new MapKeyComparator());
                        for (Map.Entry entry : arrayList) {
                            this.itemViews.set(((Integer) entry.getKey()).intValue(), (ItemView) entry.getValue());
                        }
                        invalidate();
                    }
                }
                i2 = rawY;
                i = rawX;
            } else {
                i3 = rawX;
                i4 = rawY;
                touchUp();
            }
            i2 = i4;
            i = i3;
        } else {
            touchDown();
            this.moveL = (byte) 0;
            this.moveR = (byte) 0;
            this.moveUp = (byte) 0;
            this.moveBottom = (byte) 0;
            i = rawX;
            i2 = rawY;
            LogUtils.logi("LedView>>>undoDispatchTouch[prev ACTION_DOWN----] X:" + i + "----Y:" + i2, new Object[0]);
        }
        this.mLastX = i;
        this.mLastY = i2;
        return true;
    }

    static class MapKeyComparator implements Comparator<Map.Entry<Integer, ItemView>> {
        MapKeyComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Map.Entry<Integer, ItemView> map1, Map.Entry<Integer, ItemView> map2) {
            return map1.getKey().intValue() - map2.getKey().intValue();
        }
    }

    public void setPointMargin(int pointMargin) {
        this.pointMargin = pointMargin;
    }

    public void setUnSelectedColor(int unSelectedColor) {
        this.unSelectedColor = unSelectedColor;
        invalidate();
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return this.mode;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setMirrorMode(int mirrorMode) {
        this.mirrorMode = mirrorMode;
    }

    public LedListener getLedListener() {
        return this.ledListener;
    }

    public void setLedListener(LedListener ledListener) {
        this.ledListener = ledListener;
    }

    public LedDiyListener getLedDiyListener() {
        return this.ledDiyListener;
    }

    public void setLedDiyListener(LedDiyListener listener) {
        this.ledDiyListener = listener;
    }

    public void setDispatchTouch(boolean dispatchTouch) {
        this.isDispatchTouch = dispatchTouch;
    }

    public void setOnShow(boolean onShow) {
        this.onShow = onShow;
    }

    private float getFingerSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public void setScaleCallback(ScaleCallback callback) {
        this.scaleCallback = callback;
    }

    public void setData(int[] colors) {
        int totalCount;
        if (colors != null && colors.length == (totalCount = getTotalCount())) {
            for (int i = 0; i < totalCount; i++) {
                ItemView itemView = this.itemViews.get(i);
                if (colors[i] != Color.parseColor("#000000")) {
                    itemView.isSelected = true;
                    itemView.getColors().push(Integer.valueOf(colors[i]));
                }
            }
            if (AppConfig.INSTANCE.getTopActivity().contains("TextActivity")) {
                EventBus.getDefault().post(new MsgItemViews(this.itemViews));
            }
            invalidate();
        }
    }

    public void setData(int[] colors, boolean routDiyImage) {
        int totalCount;
        if (colors != null && colors.length == (totalCount = getTotalCount())) {
            for (int i = 0; i < totalCount; i++) {
                ItemView itemView = this.itemViews.get(i);
                itemView.getRecycleColors().clear();
                itemView.isSelected = !routDiyImage;
                itemView.getColors().push(Integer.valueOf(colors[i]));
                itemView.getRecycleColors().push(Integer.valueOf(colors[i]));
            }
            DrawPath drawPath = new DrawPath();
            for (ItemView itemView2 : this.itemViews) {
                ItemView itemView3 = new ItemView();
                if (!itemView2.getColors().empty()) {
                    int intValue = itemView2.getColors().peek().intValue();
                    itemView3.getColors().add(Integer.valueOf(intValue));
                    itemView3.getRecycleColors().push(Integer.valueOf(intValue));
                }
                itemView3.index = itemView2.index;
                drawPath.getViews().add(itemView3);
            }
            this.undoRedoList.put(drawPath);
            invalidate();
        }
    }

    public void setData(byte[] bgr_data, boolean routDiyImage) {
        if (bgr_data == null || bgr_data.length == 0) {
            return;
        }
        int[] convertRGBToColor = BGRUtils.convertRGBToColor(bgr_data);
        if (routDiyImage) {
            this.bRoutDiyImage = true;
            setData(convertRGBToColor, true);
        } else {
            setData(convertRGBToColor);
        }
    }

    public void setData(byte[] bgr_data) {
        if (bgr_data == null || bgr_data.length == 0) {
            return;
        }
        setData(BGRUtils.convertRGBToColor(bgr_data));
        this.bRoutDiyImage = false;
    }

    public void setTextGradientBgData(List<String> gradientColors) {
        if (gradientColors.size() < this.heightCount) {
            return;
        }
        clear();
        int totalCount = getTotalCount();
        int i = this.unSelectedColor;
        for (int i2 = 0; i2 < totalCount; i2++) {
            int i3 = i2 % this.widthCount;
            int i4 = i2 / this.heightCount;
            if (i3 == 0) {
                i = Color.parseColor(gradientColors.get(i4));
            }
            ItemView itemView = this.itemViews.get(i2);
            itemView.isSelected = true;
            itemView.getColors().push(Integer.valueOf(i));
        }
        invalidate();
    }

    public int[] getColorData() {
        int[] iArr = new int[this.itemViews.size()];
        for (int i = 0; i < this.itemViews.size(); i++) {
            ItemView itemView = this.itemViews.get(i);
            if (this.bRoutDiyImage) {
                if (!itemView.getColors().isEmpty()) {
                    iArr[i] = itemView.getColors().peek().intValue();
                } else if (!itemView.getRecycleColors().isEmpty()) {
                    iArr[i] = itemView.getRecycleColors().get(0).intValue();
                } else {
                    iArr[i] = Color.rgb(0, 0, 0);
                }
            } else if (itemView.isSelected && !itemView.getColors().isEmpty()) {
                iArr[i] = itemView.getColors().peek().intValue();
            } else {
                iArr[i] = Color.rgb(0, 0, 0);
            }
        }
        return iArr;
    }

    public byte[] getRGBData() {
        return BGRUtils.convertColorToRGB(getColorData());
    }

    public byte[] getBgrData() {
        return BGRUtils.convertColorToBGR(getColorData());
    }

    public void setRoutDiyImage(boolean bInput) {
        this.bRoutDiyImage = bInput;
    }

    public boolean isRoutDiyImage() {
        return this.bRoutDiyImage;
    }

    public boolean isPainted() {
        if (this.bRoutDiyImage) {
            return true;
        }
        int totalCount = getTotalCount();
        for (int i = 0; i < totalCount; i++) {
            if (this.itemViews.get(i).isSelected) {
                return true;
            }
        }
        return false;
    }
}
