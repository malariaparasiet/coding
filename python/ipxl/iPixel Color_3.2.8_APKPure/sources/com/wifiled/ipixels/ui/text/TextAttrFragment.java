package com.wifiled.ipixels.ui.text;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.CoreBase;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.UtilsExtensionKt$appViewModels$1;
import com.wifiled.ipixels.UtilsExtensionKt$appViewModels$2;
import com.wifiled.ipixels.core.BusMutableLiveData;
import com.wifiled.ipixels.core.text.CharacterUtilsKt;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import com.wifiled.ipixels.ui.subzone.TextBorderDiaglog;
import com.wifiled.ipixels.ui.text.vo.EventFromType;
import com.wifiled.ipixels.ui.text.vo.EventNotifity;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.ResourceUtils;
import com.wifiled.ipixels.view.ColorBarView;
import com.wifiled.ipixels.view.RoundColorPaletteHSV360;
import com.wifiled.ipixels.view.customview.CustomImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: TextAttrFragment.kt */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 ®\u00012\u00020\u0001:\u0002®\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\n\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0014J\u0016\u0010\u008d\u0001\u001a\u00030\u008c\u00012\n\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008f\u0001H\u0016J\n\u0010\u0090\u0001\u001a\u00030\u008c\u0001H\u0016J\n\u0010\u0091\u0001\u001a\u00030\u008c\u0001H\u0014J\u0014\u0010\u0092\u0001\u001a\u00030\u008c\u00012\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0007J\n\u0010\u0095\u0001\u001a\u00030\u008c\u0001H\u0014J\n\u0010\u0096\u0001\u001a\u00030\u008c\u0001H\u0002J\u0014\u0010\u0097\u0001\u001a\u00030\u008c\u00012\b\u0010\u0098\u0001\u001a\u00030\u008a\u0001H\u0002J\n\u0010\u0099\u0001\u001a\u00030\u008c\u0001H\u0002J\n\u0010\u009a\u0001\u001a\u00030\u008c\u0001H\u0002J\n\u0010\u009b\u0001\u001a\u00030\u008c\u0001H\u0003J\n\u0010\u009c\u0001\u001a\u00030\u008c\u0001H\u0002J\n\u0010\u009d\u0001\u001a\u00030\u008c\u0001H\u0002J\u0016\u0010¥\u0001\u001a\u00030\u008c\u00012\n\b\u0002\u0010¦\u0001\u001a\u00030\u008a\u0001H\u0002J$\u0010§\u0001\u001a\u00030\u008c\u00012\f\b\u0002\u0010¨\u0001\u001a\u0005\u0018\u00010©\u00012\n\b\u0002\u0010ª\u0001\u001a\u00030\u008a\u0001H\u0002J\n\u0010«\u0001\u001a\u00030\u008c\u0001H\u0002J\n\u0010¬\u0001\u001a\u00030\u008c\u0001H\u0016J\n\u0010\u00ad\u0001\u001a\u00030\u008c\u0001H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u000208X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020;X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020;X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020;X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020@X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0DX\u0082.¢\u0006\u0002\n\u0000R\u0017\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0010\u0010I\u001a\u0004\u0018\u00010JX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010K\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0DX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020NX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010O\u001a\u00020P8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bS\u0010T\u001a\u0004\bQ\u0010RR\u0010\u0010U\u001a\u0004\u0018\u00010NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020WX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010X\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u000e\u0010]\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010^\u001a\b\u0012\u0004\u0012\u00020\u00050_X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR \u0010d\u001a\b\u0012\u0004\u0012\u00020\u00050_X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010a\"\u0004\bf\u0010cR \u0010g\u001a\b\u0012\u0004\u0012\u00020\u00050_X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010a\"\u0004\bi\u0010cR \u0010j\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010H\"\u0004\bl\u0010mR \u0010n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010H\"\u0004\bp\u0010mR\u001a\u0010q\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010Z\"\u0004\bs\u0010\\R\u001a\u0010t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010Z\"\u0004\bv\u0010\\R\u001a\u0010w\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010Z\"\u0004\by\u0010\\R\u0014\u0010z\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010{\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0DX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010|\u001a\u0004\u0018\u00010NX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010}\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010~\u001a\u0004\u0018\u00010\u007fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0080\u0001\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010Z\"\u0005\b\u0082\u0001\u0010\\R\u001d\u0010\u0083\u0001\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010Z\"\u0005\b\u0085\u0001\u0010\\R\u001d\u0010\u0086\u0001\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0001\u0010Z\"\u0005\b\u0088\u0001\u0010\\R\u0010\u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u009e\u0001\u001a\u00030\u008a\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u009f\u0001\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000f\u0010 \u0001\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000f\u0010¡\u0001\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000f\u0010¢\u0001\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010£\u0001\u001a\u00030¤\u0001X\u0082.¢\u0006\u0002\n\u0000¨\u0006¯\u0001"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextAttrFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "layoutId", "", "fonts", "", "", "cl_text_gradient", "Landroidx/constraintlayout/widget/ConstraintLayout;", "ivClearBgColor", "Landroid/widget/ImageView;", "cl_text_bg_color_set", "cl_text_bg_color_set2_bc", "cl_text_border", "cl_text_border_bc", "cl_text_color_set", "cl_text_font_set", "cl_text_font_set_bc", "cl_text_size_set", "cl_text_size_set_bc", "colorBarView_bg", "Lcom/wifiled/ipixels/view/ColorBarView;", "colorBarView_bg_bc", "Landroidx/recyclerview/widget/RecyclerView;", "colorBarView_text", "iv_border", "iv_border_bc", "iv_show_text_bgcolor", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_show_textcolor", "recyclerview_base_color", "recyclerview_base_color_gradation", "recyclerview_color", "rg_align", "Landroid/widget/RadioGroup;", "rg_align_vertical", "rl_outside_bgcolor_frame2", "Landroid/widget/RelativeLayout;", "rl_outside_bgcolor_frame2_bc", "style_base_color", "Landroid/widget/LinearLayout;", "style_rectangle", "style_square", "text_bold_iv", "text_bold_iv_bc", "text_bold_layout", "text_direction_layout", "text_direction_layout_bc", "text_bold_layout_bc", "text_direction_horizontal", "text_direction_horizontal_bc", "text_direction_vertical", "text_direction_vertical_bc", "tv_font", "Landroidx/appcompat/widget/AppCompatTextView;", "tv_textSize", "tv_text_font", "Landroid/widget/TextView;", "tv_text_font_bc", "tv_text_size_32_128", "tv_text_size_32_bc", "rb_center", "Landroid/widget/RadioButton;", "rb_vertical_center", "mTemplateType", "fontAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "textSizes", "getTextSizes", "()Ljava/util/List;", "mBorderData2", "", "textSizeAdapter", "actionRecyclerView", "fontDialog", "Landroid/app/Dialog;", "textViewModel", "Lcom/wifiled/ipixels/ui/text/TextViewModel;", "getTextViewModel", "()Lcom/wifiled/ipixels/ui/text/TextViewModel;", "textViewModel$delegate", "Lkotlin/Lazy;", "colorDialog", "eventText", "Lcom/wifiled/ipixels/event/EventText;", "prevSize", "getPrevSize", "()I", "setPrevSize", "(I)V", "nextSize", "mColorAdapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "getMColorAdapter", "()Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "setMColorAdapter", "(Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;)V", "mColorBgAdapter", "getMColorBgAdapter", "setMColorBgAdapter", "mColorBcAdapter", "getMColorBcAdapter", "setMColorBcAdapter", "mColorsData", "getMColorsData", "setMColorsData", "(Ljava/util/List;)V", "mColorsBgData", "getMColorsBgData", "setMColorsBgData", "mSelectColorPos", "getMSelectColorPos", "setMSelectColorPos", "mSelectColorBcPos", "getMSelectColorBcPos", "setMSelectColorBcPos", "mSelectColorBgPos", "getMSelectColorBgPos", "setMSelectColorBgPos", "mTextSize32_128List", "mTextSizeAdapter", "mTextSizeDialog", "mFontSizeRecyclerView", "mTextBorderDiaglog", "Lcom/wifiled/ipixels/ui/subzone/TextBorderDiaglog;", "mBorderPos", "getMBorderPos", "setMBorderPos", "mBorderEffectPos", "getMBorderEffectPos", "setMBorderEffectPos", "mBorderSpeed", "getMBorderSpeed", "setMBorderSpeed", "mIsBold", "", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "bindData", "onFromTypeEvent", "eventFromType", "Lcom/wifiled/ipixels/ui/text/vo/EventFromType;", "bindListener", "init32128TextSizeDialog", "isDisableBold", TypedValues.Custom.S_BOOLEAN, "init32128TextSizeDialog2", "initDefaultTextPara", "initFontAdapter", "initTextSizeAdapter", "showTexturizeDialog", "isTextColorMode", "rlGradient01", "rlGradient02", "rlGradient03", "rlGradient04", "colorPickerView", "Lcom/wifiled/ipixels/view/RoundColorPaletteHSV360;", "showColorDialog", "isTextColor", "showGradientSelected", "sel", "Landroid/view/View;", "isSel", "initBorderData2", "onStart", "onStop", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAttrFragment extends BaseFragment {
    private static final String ARG_TEMP_TYPE = "tem_type";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private RecyclerView actionRecyclerView;
    private ConstraintLayout cl_text_bg_color_set;
    private ConstraintLayout cl_text_bg_color_set2_bc;
    private ConstraintLayout cl_text_border;
    private ConstraintLayout cl_text_border_bc;
    private ConstraintLayout cl_text_color_set;
    private ConstraintLayout cl_text_font_set;
    private ConstraintLayout cl_text_font_set_bc;
    private ConstraintLayout cl_text_gradient;
    private ConstraintLayout cl_text_size_set;
    private ConstraintLayout cl_text_size_set_bc;
    private ColorBarView colorBarView_bg;
    private RecyclerView colorBarView_bg_bc;
    private ColorBarView colorBarView_text;
    private Dialog colorDialog;
    private RoundColorPaletteHSV360 colorPickerView;
    private EventText eventText;
    private IosDialogStyleAdapter<Object> fontAdapter;
    private Dialog fontDialog;
    private List<String> fonts;
    private boolean isTextColorMode;
    private ImageView ivClearBgColor;
    private ImageView iv_border;
    private ImageView iv_border_bc;
    private CustomImageView iv_show_text_bgcolor;
    private CustomImageView iv_show_textcolor;
    private int[] mBorderData2;
    private int mBorderEffectPos;
    private int mBorderPos;
    private int mBorderSpeed;
    public RecyclerAdapter<Integer> mColorAdapter;
    public RecyclerAdapter<Integer> mColorBcAdapter;
    public RecyclerAdapter<Integer> mColorBgAdapter;
    public List<Integer> mColorsBgData;
    public List<Integer> mColorsData;
    private RecyclerView mFontSizeRecyclerView;
    private boolean mIsBold;
    private int mSelectColorBcPos;
    private int mSelectColorBgPos;
    private int mSelectColorPos;
    private int mTemplateType;
    private TextBorderDiaglog mTextBorderDiaglog;
    private final List<String> mTextSize32_128List;
    private IosDialogStyleAdapter<Object> mTextSizeAdapter;
    private Dialog mTextSizeDialog;
    private int nextSize;
    private int prevSize;
    private RadioButton rb_center;
    private RadioButton rb_vertical_center;
    private RecyclerView recyclerview_base_color;
    private RecyclerView recyclerview_base_color_gradation;
    private RecyclerView recyclerview_color;
    private RadioGroup rg_align;
    private RadioGroup rg_align_vertical;
    private RelativeLayout rlGradient01;
    private RelativeLayout rlGradient02;
    private RelativeLayout rlGradient03;
    private RelativeLayout rlGradient04;
    private RelativeLayout rl_outside_bgcolor_frame2;
    private RelativeLayout rl_outside_bgcolor_frame2_bc;
    private LinearLayout style_base_color;
    private LinearLayout style_rectangle;
    private LinearLayout style_square;
    private IosDialogStyleAdapter<Object> textSizeAdapter;
    private final List<Integer> textSizes;

    /* renamed from: textViewModel$delegate, reason: from kotlin metadata */
    private final Lazy textViewModel;
    private ImageView text_bold_iv;
    private ImageView text_bold_iv_bc;
    private ConstraintLayout text_bold_layout;
    private ConstraintLayout text_bold_layout_bc;
    private ImageView text_direction_horizontal;
    private ImageView text_direction_horizontal_bc;
    private ConstraintLayout text_direction_layout;
    private ConstraintLayout text_direction_layout_bc;
    private ImageView text_direction_vertical;
    private ImageView text_direction_vertical_bc;
    private AppCompatTextView tv_font;
    private AppCompatTextView tv_textSize;
    private TextView tv_text_font;
    private TextView tv_text_font_bc;
    private TextView tv_text_size_32_128;
    private TextView tv_text_size_32_bc;

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_text_attr;
    }

    public TextAttrFragment() {
        List<Integer> listOf;
        int intValue = AppConfig.INSTANCE.getLedSize().get(1).intValue();
        if (intValue == 24) {
            listOf = CollectionsKt.listOf(24);
        } else if (intValue == 32) {
            listOf = CollectionsKt.listOf((Object[]) new Integer[]{16, 32});
        } else if (intValue == 64) {
            listOf = CollectionsKt.listOf((Object[]) new Integer[]{16, 32, 64});
        } else {
            listOf = CollectionsKt.listOf(16);
        }
        this.textSizes = listOf;
        TextAttrFragment textAttrFragment = this;
        this.textViewModel = FragmentViewModelLazyKt.createViewModelLazy(textAttrFragment, Reflection.getOrCreateKotlinClass(TextViewModel.class), UtilsExtensionKt$appViewModels$1.INSTANCE, new UtilsExtensionKt$appViewModels$2(textAttrFragment), null);
        this.mSelectColorPos = -1;
        this.mSelectColorBcPos = -1;
        this.mSelectColorBgPos = -1;
        this.mTextSize32_128List = CollectionsKt.listOf((Object[]) new String[]{"16", "32"});
        this.isTextColorMode = true;
    }

    public final List<Integer> getTextSizes() {
        return this.textSizes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextViewModel getTextViewModel() {
        return (TextViewModel) this.textViewModel.getValue();
    }

    public final int getPrevSize() {
        return this.prevSize;
    }

    public final void setPrevSize(int i) {
        this.prevSize = i;
    }

    public final RecyclerAdapter<Integer> getMColorAdapter() {
        RecyclerAdapter<Integer> recyclerAdapter = this.mColorAdapter;
        if (recyclerAdapter != null) {
            return recyclerAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mColorAdapter");
        return null;
    }

    public final void setMColorAdapter(RecyclerAdapter<Integer> recyclerAdapter) {
        Intrinsics.checkNotNullParameter(recyclerAdapter, "<set-?>");
        this.mColorAdapter = recyclerAdapter;
    }

    public final RecyclerAdapter<Integer> getMColorBgAdapter() {
        RecyclerAdapter<Integer> recyclerAdapter = this.mColorBgAdapter;
        if (recyclerAdapter != null) {
            return recyclerAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mColorBgAdapter");
        return null;
    }

    public final void setMColorBgAdapter(RecyclerAdapter<Integer> recyclerAdapter) {
        Intrinsics.checkNotNullParameter(recyclerAdapter, "<set-?>");
        this.mColorBgAdapter = recyclerAdapter;
    }

    public final RecyclerAdapter<Integer> getMColorBcAdapter() {
        RecyclerAdapter<Integer> recyclerAdapter = this.mColorBcAdapter;
        if (recyclerAdapter != null) {
            return recyclerAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mColorBcAdapter");
        return null;
    }

    public final void setMColorBcAdapter(RecyclerAdapter<Integer> recyclerAdapter) {
        Intrinsics.checkNotNullParameter(recyclerAdapter, "<set-?>");
        this.mColorBcAdapter = recyclerAdapter;
    }

    public final List<Integer> getMColorsData() {
        List<Integer> list = this.mColorsData;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mColorsData");
        return null;
    }

    public final void setMColorsData(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mColorsData = list;
    }

    public final List<Integer> getMColorsBgData() {
        List<Integer> list = this.mColorsBgData;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mColorsBgData");
        return null;
    }

    public final void setMColorsBgData(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mColorsBgData = list;
    }

    public final int getMSelectColorPos() {
        return this.mSelectColorPos;
    }

    public final void setMSelectColorPos(int i) {
        this.mSelectColorPos = i;
    }

    public final int getMSelectColorBcPos() {
        return this.mSelectColorBcPos;
    }

    public final void setMSelectColorBcPos(int i) {
        this.mSelectColorBcPos = i;
    }

    public final int getMSelectColorBgPos() {
        return this.mSelectColorBgPos;
    }

    public final void setMSelectColorBgPos(int i) {
        this.mSelectColorBgPos = i;
    }

    public final int getMBorderPos() {
        return this.mBorderPos;
    }

    public final void setMBorderPos(int i) {
        this.mBorderPos = i;
    }

    public final int getMBorderEffectPos() {
        return this.mBorderEffectPos;
    }

    public final void setMBorderEffectPos(int i) {
        this.mBorderEffectPos = i;
    }

    public final int getMBorderSpeed() {
        return this.mBorderSpeed;
    }

    public final void setMBorderSpeed(int i) {
        this.mBorderSpeed = i;
    }

    /* compiled from: TextAttrFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextAttrFragment$Companion;", "", "<init>", "()V", "ARG_TEMP_TYPE", "", "newInstance", "Lcom/wifiled/ipixels/ui/text/TextAttrFragment;", "temType", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TextAttrFragment newInstance(int temType) {
            TextAttrFragment textAttrFragment = new TextAttrFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(TextAttrFragment.ARG_TEMP_TYPE, temType);
            textAttrFragment.setArguments(bundle);
            return textAttrFragment;
        }
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.cl_text_bg_color_set);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.cl_text_bg_color_set = (ConstraintLayout) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.cl_text_bg_color_set2_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.cl_text_bg_color_set2_bc = (ConstraintLayout) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.cl_text_border);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.cl_text_border = (ConstraintLayout) findViewById3;
        View findViewById4 = this.mRootView.findViewById(R.id.cl_text_border_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.cl_text_border_bc = (ConstraintLayout) findViewById4;
        View findViewById5 = this.mRootView.findViewById(R.id.cl_text_color_set);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.cl_text_color_set = (ConstraintLayout) findViewById5;
        View findViewById6 = this.mRootView.findViewById(R.id.cl_text_font_set);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.cl_text_font_set = (ConstraintLayout) findViewById6;
        View findViewById7 = this.mRootView.findViewById(R.id.cl_text_font_set_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.cl_text_font_set_bc = (ConstraintLayout) findViewById7;
        View findViewById8 = this.mRootView.findViewById(R.id.cl_text_size_set);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.cl_text_size_set = (ConstraintLayout) findViewById8;
        View findViewById9 = this.mRootView.findViewById(R.id.cl_text_size_set_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.cl_text_size_set_bc = (ConstraintLayout) findViewById9;
        View findViewById10 = this.mRootView.findViewById(R.id.colorBarView_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.colorBarView_bg = (ColorBarView) findViewById10;
        View findViewById11 = this.mRootView.findViewById(R.id.colorBarView_bg_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.colorBarView_bg_bc = (RecyclerView) findViewById11;
        View findViewById12 = this.mRootView.findViewById(R.id.colorBarView_text);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.colorBarView_text = (ColorBarView) findViewById12;
        View findViewById13 = this.mRootView.findViewById(R.id.iv_border);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.iv_border = (ImageView) findViewById13;
        View findViewById14 = this.mRootView.findViewById(R.id.iv_border_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.iv_border_bc = (ImageView) findViewById14;
        View findViewById15 = this.mRootView.findViewById(R.id.iv_show_text_bgcolor);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.iv_show_text_bgcolor = (CustomImageView) findViewById15;
        View findViewById16 = this.mRootView.findViewById(R.id.iv_show_textcolor);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.iv_show_textcolor = (CustomImageView) findViewById16;
        View findViewById17 = this.mRootView.findViewById(R.id.recyclerview_base_color);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.recyclerview_base_color = (RecyclerView) findViewById17;
        View findViewById18 = this.mRootView.findViewById(R.id.recyclerview_base_color_gradation);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.recyclerview_base_color_gradation = (RecyclerView) findViewById18;
        View findViewById19 = this.mRootView.findViewById(R.id.recyclerview_color);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(...)");
        this.recyclerview_color = (RecyclerView) findViewById19;
        View findViewById20 = this.mRootView.findViewById(R.id.rg_align);
        Intrinsics.checkNotNullExpressionValue(findViewById20, "findViewById(...)");
        this.rg_align = (RadioGroup) findViewById20;
        View findViewById21 = this.mRootView.findViewById(R.id.rg_align_vertical);
        Intrinsics.checkNotNullExpressionValue(findViewById21, "findViewById(...)");
        this.rg_align_vertical = (RadioGroup) findViewById21;
        View findViewById22 = this.mRootView.findViewById(R.id.rl_outside_bgcolor_frame2);
        Intrinsics.checkNotNullExpressionValue(findViewById22, "findViewById(...)");
        this.rl_outside_bgcolor_frame2 = (RelativeLayout) findViewById22;
        View findViewById23 = this.mRootView.findViewById(R.id.rl_outside_bgcolor_frame2_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById23, "findViewById(...)");
        this.rl_outside_bgcolor_frame2_bc = (RelativeLayout) findViewById23;
        View findViewById24 = this.mRootView.findViewById(R.id.style_base_color);
        Intrinsics.checkNotNullExpressionValue(findViewById24, "findViewById(...)");
        this.style_base_color = (LinearLayout) findViewById24;
        View findViewById25 = this.mRootView.findViewById(R.id.style_rectangle);
        Intrinsics.checkNotNullExpressionValue(findViewById25, "findViewById(...)");
        this.style_rectangle = (LinearLayout) findViewById25;
        View findViewById26 = this.mRootView.findViewById(R.id.style_square);
        Intrinsics.checkNotNullExpressionValue(findViewById26, "findViewById(...)");
        this.style_square = (LinearLayout) findViewById26;
        View findViewById27 = this.mRootView.findViewById(R.id.text_bold_iv);
        Intrinsics.checkNotNullExpressionValue(findViewById27, "findViewById(...)");
        this.text_bold_iv = (ImageView) findViewById27;
        View findViewById28 = this.mRootView.findViewById(R.id.text_bold_iv_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById28, "findViewById(...)");
        this.text_bold_iv_bc = (ImageView) findViewById28;
        View findViewById29 = this.mRootView.findViewById(R.id.text_bold_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById29, "findViewById(...)");
        this.text_bold_layout = (ConstraintLayout) findViewById29;
        View findViewById30 = this.mRootView.findViewById(R.id.text_bold_layout_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById30, "findViewById(...)");
        this.text_bold_layout_bc = (ConstraintLayout) findViewById30;
        View findViewById31 = this.mRootView.findViewById(R.id.text_direction_horizontal);
        Intrinsics.checkNotNullExpressionValue(findViewById31, "findViewById(...)");
        this.text_direction_horizontal = (ImageView) findViewById31;
        View findViewById32 = this.mRootView.findViewById(R.id.text_direction_horizontal_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById32, "findViewById(...)");
        this.text_direction_horizontal_bc = (ImageView) findViewById32;
        View findViewById33 = this.mRootView.findViewById(R.id.text_direction_vertical);
        Intrinsics.checkNotNullExpressionValue(findViewById33, "findViewById(...)");
        this.text_direction_vertical = (ImageView) findViewById33;
        View findViewById34 = this.mRootView.findViewById(R.id.text_direction_vertical_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById34, "findViewById(...)");
        this.text_direction_vertical_bc = (ImageView) findViewById34;
        View findViewById35 = this.mRootView.findViewById(R.id.tv_font);
        Intrinsics.checkNotNullExpressionValue(findViewById35, "findViewById(...)");
        this.tv_font = (AppCompatTextView) findViewById35;
        View findViewById36 = this.mRootView.findViewById(R.id.tv_textSize);
        Intrinsics.checkNotNullExpressionValue(findViewById36, "findViewById(...)");
        this.tv_textSize = (AppCompatTextView) findViewById36;
        View findViewById37 = this.mRootView.findViewById(R.id.tv_text_font);
        Intrinsics.checkNotNullExpressionValue(findViewById37, "findViewById(...)");
        this.tv_text_font = (TextView) findViewById37;
        View findViewById38 = this.mRootView.findViewById(R.id.tv_text_font_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById38, "findViewById(...)");
        this.tv_text_font_bc = (TextView) findViewById38;
        View findViewById39 = this.mRootView.findViewById(R.id.tv_text_size_32_128);
        Intrinsics.checkNotNullExpressionValue(findViewById39, "findViewById(...)");
        this.tv_text_size_32_128 = (TextView) findViewById39;
        View findViewById40 = this.mRootView.findViewById(R.id.tv_text_size_32_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById40, "findViewById(...)");
        this.tv_text_size_32_bc = (TextView) findViewById40;
        View findViewById41 = this.mRootView.findViewById(R.id.rb_center);
        Intrinsics.checkNotNullExpressionValue(findViewById41, "findViewById(...)");
        this.rb_center = (RadioButton) findViewById41;
        View findViewById42 = this.mRootView.findViewById(R.id.rb_vertical_center);
        Intrinsics.checkNotNullExpressionValue(findViewById42, "findViewById(...)");
        this.rb_vertical_center = (RadioButton) findViewById42;
        View findViewById43 = this.mRootView.findViewById(R.id.text_direction_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById43, "findViewById(...)");
        this.text_direction_layout = (ConstraintLayout) findViewById43;
        View findViewById44 = this.mRootView.findViewById(R.id.text_direction_layout_bc);
        Intrinsics.checkNotNullExpressionValue(findViewById44, "findViewById(...)");
        this.text_direction_layout_bc = (ConstraintLayout) findViewById44;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTemplateType = arguments.getInt(ARG_TEMP_TYPE);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.colorDialog = null;
        this.mActivity = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0053, code lost:
    
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r1, (java.lang.CharSequence) "hi", false, 2, (java.lang.Object) null) != false) goto L8;
     */
    @Override // com.wifiled.baselib.base.BaseFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void bindData() {
        /*
            Method dump skipped, instructions count: 1384
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.text.TextAttrFragment.bindData():void");
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onFromTypeEvent(EventFromType eventFromType) {
        Intrinsics.checkNotNullParameter(eventFromType, "eventFromType");
        ConstraintLayout constraintLayout = null;
        if (eventFromType.getFromType() == 1002) {
            ConstraintLayout constraintLayout2 = this.cl_text_border;
            if (constraintLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_text_border");
            } else {
                constraintLayout = constraintLayout2;
            }
            constraintLayout.setVisibility(0);
            initBorderData2();
        } else {
            ConstraintLayout constraintLayout3 = this.cl_text_border;
            if (constraintLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_text_border");
            } else {
                constraintLayout = constraintLayout3;
            }
            constraintLayout.setVisibility(4);
        }
        EventBus.getDefault().removeStickyEvent(eventFromType);
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        AppCompatTextView appCompatTextView = this.tv_font;
        RadioGroup radioGroup = null;
        ConstraintLayout constraintLayout = null;
        ConstraintLayout constraintLayout2 = null;
        if (appCompatTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_font");
            appCompatTextView = null;
        }
        companion.attachViewOnTouchListener(appCompatTextView);
        int ledType = AppConfig.INSTANCE.getLedType();
        if (ledType == 0 || ledType == 2) {
            AppCompatTextView appCompatTextView2 = this.tv_textSize;
            if (appCompatTextView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_textSize");
                appCompatTextView2 = null;
            }
            appCompatTextView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TextAttrFragment.this.showTexturizeDialog();
                }
            });
            AppCompatTextView appCompatTextView3 = this.tv_font;
            if (appCompatTextView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_font");
                appCompatTextView3 = null;
            }
            appCompatTextView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda15
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TextAttrFragment.bindListener$lambda$2(TextAttrFragment.this, view);
                }
            });
            CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
            AppCompatTextView appCompatTextView4 = this.tv_textSize;
            if (appCompatTextView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_textSize");
                appCompatTextView4 = null;
            }
            companion2.attachViewOnTouchListener(appCompatTextView4);
            ConstraintLayout constraintLayout3 = this.cl_text_color_set;
            if (constraintLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_text_color_set");
                constraintLayout3 = null;
            }
            UtilsExtensionKt.setOnClickFilterListener(constraintLayout3, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$3;
                    bindListener$lambda$3 = TextAttrFragment.bindListener$lambda$3(TextAttrFragment.this);
                    return bindListener$lambda$3;
                }
            });
            CustomImageView.Companion companion3 = CustomImageView.INSTANCE;
            ConstraintLayout constraintLayout4 = this.cl_text_color_set;
            if (constraintLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_text_color_set");
                constraintLayout4 = null;
            }
            companion3.attachViewOnTouchListener(constraintLayout4);
            ConstraintLayout constraintLayout5 = this.cl_text_bg_color_set;
            if (constraintLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_text_bg_color_set");
                constraintLayout5 = null;
            }
            UtilsExtensionKt.setOnClickFilterListener(constraintLayout5, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$4;
                    bindListener$lambda$4 = TextAttrFragment.bindListener$lambda$4(TextAttrFragment.this);
                    return bindListener$lambda$4;
                }
            });
            CustomImageView.Companion companion4 = CustomImageView.INSTANCE;
            ConstraintLayout constraintLayout6 = this.cl_text_bg_color_set;
            if (constraintLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_text_bg_color_set");
                constraintLayout6 = null;
            }
            companion4.attachViewOnTouchListener(constraintLayout6);
            RadioGroup radioGroup2 = this.rg_align;
            if (radioGroup2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rg_align");
                radioGroup2 = null;
            }
            radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda25
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup3, int i) {
                    TextAttrFragment.bindListener$lambda$5(TextAttrFragment.this, radioGroup3, i);
                }
            });
            RadioGroup radioGroup3 = this.rg_align_vertical;
            if (radioGroup3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rg_align_vertical");
            } else {
                radioGroup = radioGroup3;
            }
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda26
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup4, int i) {
                    TextAttrFragment.bindListener$lambda$6(TextAttrFragment.this, radioGroup4, i);
                }
            });
            return;
        }
        switch (ledType) {
            case 17:
            case 18:
            case 19:
                final Context context = getContext();
                final List<Integer> mColorsData = getMColorsData();
                setMColorAdapter(new RecyclerAdapter<Integer>(context, mColorsData) { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$bindListener$7
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
                    public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
                        convert(recyclerViewHolder, num.intValue());
                    }

                    public void convert(RecyclerViewHolder holder, int integer) {
                        Intrinsics.checkNotNullParameter(holder, "holder");
                        View view = holder.getView(R.id.rl_paint_outside_frame);
                        View view2 = holder.getView(R.id.colorView);
                        if (TextAttrFragment.this.getMSelectColorPos() == getPosition(holder)) {
                            view.setBackgroundResource(R.drawable.icon_paint_color_sel);
                        } else {
                            view.setBackgroundResource(R.color.transparent);
                        }
                        view2.setBackgroundResource(integer);
                    }
                });
                RecyclerView recyclerView = this.recyclerview_base_color_gradation;
                if (recyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerview_base_color_gradation");
                    recyclerView = null;
                }
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
                RecyclerView recyclerView2 = this.recyclerview_base_color_gradation;
                if (recyclerView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerview_base_color_gradation");
                    recyclerView2 = null;
                }
                recyclerView2.setAdapter(getMColorAdapter());
                getMColorAdapter().setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda27
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
                    public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                        TextAttrFragment.bindListener$lambda$8(TextAttrFragment.this, viewGroup, view, (Integer) obj, i);
                    }
                });
                final Context context2 = getContext();
                final List<Integer> mColorsBgData = getMColorsBgData();
                setMColorBcAdapter(new RecyclerAdapter<Integer>(context2, mColorsBgData) { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$bindListener$9
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
                    public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
                        convert(recyclerViewHolder, num.intValue());
                    }

                    public void convert(RecyclerViewHolder holder, int integer) {
                        Intrinsics.checkNotNullParameter(holder, "holder");
                        View view = holder.getView(R.id.rl_paint_outside_frame);
                        View view2 = holder.getView(R.id.colorView);
                        if (TextAttrFragment.this.getMSelectColorBcPos() == getPosition(holder)) {
                            view.setBackgroundResource(R.drawable.text_colorblock_select);
                        } else {
                            view.setBackgroundResource(R.color.transparent);
                        }
                        view2.setBackgroundResource(integer);
                    }
                });
                RecyclerView recyclerView3 = this.recyclerview_base_color;
                if (recyclerView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerview_base_color");
                    recyclerView3 = null;
                }
                recyclerView3.setLayoutManager(new GridLayoutManager(getContext(), 7));
                RecyclerView recyclerView4 = this.recyclerview_base_color;
                if (recyclerView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerview_base_color");
                    recyclerView4 = null;
                }
                recyclerView4.setAdapter(getMColorBcAdapter());
                getMColorBcAdapter().setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda28
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
                    public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                        TextAttrFragment.bindListener$lambda$10(TextAttrFragment.this, viewGroup, view, (Integer) obj, i);
                    }
                });
                final Context context3 = getContext();
                final List<Integer> mColorsBgData2 = getMColorsBgData();
                setMColorBgAdapter(new RecyclerAdapter<Integer>(context3, mColorsBgData2) { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$bindListener$11
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
                    public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
                        convert(recyclerViewHolder, num.intValue());
                    }

                    public void convert(RecyclerViewHolder holder, int integer) {
                        Intrinsics.checkNotNullParameter(holder, "holder");
                        View view = holder.getView(R.id.rl_paint_outside_frame);
                        View view2 = holder.getView(R.id.colorView);
                        if (TextAttrFragment.this.getMSelectColorBgPos() == getPosition(holder)) {
                            view.setBackgroundResource(R.drawable.text_colorblock_select);
                        } else {
                            view.setBackgroundResource(R.color.transparent);
                        }
                        view2.setBackgroundResource(integer);
                    }
                });
                RecyclerView recyclerView5 = this.colorBarView_bg_bc;
                if (recyclerView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("colorBarView_bg_bc");
                    recyclerView5 = null;
                }
                recyclerView5.setLayoutManager(new GridLayoutManager(getContext(), 7));
                RecyclerView recyclerView6 = this.colorBarView_bg_bc;
                if (recyclerView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("colorBarView_bg_bc");
                    recyclerView6 = null;
                }
                recyclerView6.setAdapter(getMColorBgAdapter());
                getMColorBgAdapter().setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda29
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
                    public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                        TextAttrFragment.bindListener$lambda$11(TextAttrFragment.this, viewGroup, view, (Integer) obj, i);
                    }
                });
                RelativeLayout relativeLayout = this.rl_outside_bgcolor_frame2_bc;
                if (relativeLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_outside_bgcolor_frame2_bc");
                    relativeLayout = null;
                }
                relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda30
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$12(TextAttrFragment.this, view);
                    }
                });
                CustomImageView.Companion companion5 = CustomImageView.INSTANCE;
                RelativeLayout relativeLayout2 = this.rl_outside_bgcolor_frame2_bc;
                if (relativeLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_outside_bgcolor_frame2_bc");
                    relativeLayout2 = null;
                }
                companion5.attachViewOnTouchListener(relativeLayout2);
                ImageView imageView = this.text_direction_vertical_bc;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("text_direction_vertical_bc");
                    imageView = null;
                }
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$13(TextAttrFragment.this, view);
                    }
                });
                ImageView imageView2 = this.text_direction_horizontal_bc;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("text_direction_horizontal_bc");
                    imageView2 = null;
                }
                imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda5
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$14(TextAttrFragment.this, view);
                    }
                });
                ImageView imageView3 = this.text_bold_iv_bc;
                if (imageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("text_bold_iv_bc");
                    imageView3 = null;
                }
                imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$15(TextAttrFragment.this, view);
                    }
                });
                ConstraintLayout constraintLayout7 = this.cl_text_border_bc;
                if (constraintLayout7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cl_text_border_bc");
                    constraintLayout7 = null;
                }
                constraintLayout7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$16(TextAttrFragment.this, view);
                    }
                });
                ConstraintLayout constraintLayout8 = this.cl_text_size_set_bc;
                if (constraintLayout8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cl_text_size_set_bc");
                    constraintLayout8 = null;
                }
                constraintLayout8.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda8
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$17(TextAttrFragment.this, view);
                    }
                });
                ConstraintLayout constraintLayout9 = this.cl_text_font_set_bc;
                if (constraintLayout9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cl_text_font_set_bc");
                } else {
                    constraintLayout2 = constraintLayout9;
                }
                constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$18(TextAttrFragment.this, view);
                    }
                });
                break;
            default:
                final Context context4 = getContext();
                final List<Integer> mColorsData2 = getMColorsData();
                setMColorAdapter(new RecyclerAdapter<Integer>(context4, mColorsData2) { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$bindListener$20
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
                    public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
                        convert(recyclerViewHolder, num.intValue());
                    }

                    public void convert(RecyclerViewHolder holder, int integer) {
                        Intrinsics.checkNotNullParameter(holder, "holder");
                        View view = holder.getView(R.id.rl_paint_outside_frame);
                        View view2 = holder.getView(R.id.colorView);
                        if (TextAttrFragment.this.getMSelectColorPos() == getPosition(holder)) {
                            view.setBackgroundResource(R.drawable.icon_paint_color_sel);
                        } else {
                            view.setBackgroundResource(R.color.transparent);
                        }
                        view2.setBackgroundResource(integer);
                    }
                });
                RecyclerView recyclerView7 = this.recyclerview_color;
                if (recyclerView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerview_color");
                    recyclerView7 = null;
                }
                recyclerView7.setLayoutManager(new GridLayoutManager(getContext(), 8));
                RecyclerView recyclerView8 = this.recyclerview_color;
                if (recyclerView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerview_color");
                    recyclerView8 = null;
                }
                recyclerView8.setAdapter(getMColorAdapter());
                RelativeLayout relativeLayout3 = this.rl_outside_bgcolor_frame2;
                if (relativeLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_outside_bgcolor_frame2");
                    relativeLayout3 = null;
                }
                relativeLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda10
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$19(TextAttrFragment.this, view);
                    }
                });
                CustomImageView.Companion companion6 = CustomImageView.INSTANCE;
                RelativeLayout relativeLayout4 = this.rl_outside_bgcolor_frame2;
                if (relativeLayout4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_outside_bgcolor_frame2");
                    relativeLayout4 = null;
                }
                companion6.attachViewOnTouchListener(relativeLayout4);
                getMColorAdapter().setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda12
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
                    public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                        TextAttrFragment.bindListener$lambda$21(TextAttrFragment.this, viewGroup, view, (Integer) obj, i);
                    }
                });
                ColorBarView colorBarView = this.colorBarView_text;
                if (colorBarView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("colorBarView_text");
                    colorBarView = null;
                }
                colorBarView.setOnColorChangerListener(new ColorBarView.OnColorChangeListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda13
                    @Override // com.wifiled.ipixels.view.ColorBarView.OnColorChangeListener
                    public final void onColorChange(int i) {
                        TextAttrFragment.bindListener$lambda$23(TextAttrFragment.this, i);
                    }
                });
                ColorBarView colorBarView2 = this.colorBarView_bg;
                if (colorBarView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("colorBarView_bg");
                    colorBarView2 = null;
                }
                colorBarView2.setOnColorChangerListener(new ColorBarView.OnColorChangeListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda14
                    @Override // com.wifiled.ipixels.view.ColorBarView.OnColorChangeListener
                    public final void onColorChange(int i) {
                        TextAttrFragment.bindListener$lambda$25(TextAttrFragment.this, i);
                    }
                });
                ImageView imageView4 = this.text_direction_vertical;
                if (imageView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("text_direction_vertical");
                    imageView4 = null;
                }
                imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda16
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$26(TextAttrFragment.this, view);
                    }
                });
                ImageView imageView5 = this.text_direction_horizontal;
                if (imageView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("text_direction_horizontal");
                    imageView5 = null;
                }
                imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda17
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$27(TextAttrFragment.this, view);
                    }
                });
                ImageView imageView6 = this.text_bold_iv;
                if (imageView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("text_bold_iv");
                    imageView6 = null;
                }
                imageView6.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda18
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$28(TextAttrFragment.this, view);
                    }
                });
                ConstraintLayout constraintLayout10 = this.cl_text_border;
                if (constraintLayout10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cl_text_border");
                    constraintLayout10 = null;
                }
                constraintLayout10.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda19
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$29(TextAttrFragment.this, view);
                    }
                });
                ConstraintLayout constraintLayout11 = this.cl_text_size_set;
                if (constraintLayout11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cl_text_size_set");
                    constraintLayout11 = null;
                }
                constraintLayout11.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda20
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$30(TextAttrFragment.this, view);
                    }
                });
                ConstraintLayout constraintLayout12 = this.cl_text_font_set;
                if (constraintLayout12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cl_text_font_set");
                } else {
                    constraintLayout = constraintLayout12;
                }
                constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda21
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TextAttrFragment.bindListener$lambda$31(TextAttrFragment.this, view);
                    }
                });
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$2(TextAttrFragment textAttrFragment, View view) {
        Dialog dialog = textAttrFragment.fontDialog;
        if (dialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontDialog");
            dialog = null;
        }
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$3(TextAttrFragment textAttrFragment) {
        LogUtils.file("TextActivity TextAttrFragment  cl_text_color_set.setOnClickListener ");
        showColorDialog$default(textAttrFragment, false, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$4(TextAttrFragment textAttrFragment) {
        LogUtils.file("TextActivity TextAttrFragment   cl_text_bg_color_set.setOnClickFilterListener");
        textAttrFragment.showColorDialog(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$5(TextAttrFragment textAttrFragment, RadioGroup radioGroup, int i) {
        LogUtils.file("TextActivity TextAttrFragment   rg_align.setOnCheckedChangeListener checkedId=" + i);
        int i2 = 0;
        switch (i) {
            case R.id.rb_center /* 2131297027 */:
                i2 = 1;
                break;
            case R.id.rb_right /* 2131297029 */:
                i2 = 2;
                break;
        }
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        EventText eventText2 = null;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextHorizontalAlign(i2);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        eventText3.setChangeMode(TextAttrEnum.TextHorizontalAlign);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText4 = textAttrFragment.eventText;
        if (eventText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText2 = eventText4;
        }
        textChangedLiveData.setValue(eventText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$6(TextAttrFragment textAttrFragment, RadioGroup radioGroup, int i) {
        LogUtils.file("TextActivity TextAttrFragment   rg_align_vertical.setOnCheckedChangeListener checkedId=" + i);
        int i2 = 3;
        switch (i) {
            case R.id.rb_vertical_bottom /* 2131297030 */:
                i2 = 5;
                break;
            case R.id.rb_vertical_center /* 2131297031 */:
                i2 = 4;
                break;
        }
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        EventText eventText2 = null;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextVerticalAlign(i2);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        eventText3.setChangeMode(TextAttrEnum.TextVerticalAlign);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText4 = textAttrFragment.eventText;
        if (eventText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText2 = eventText4;
        }
        textChangedLiveData.setValue(eventText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$8(TextAttrFragment textAttrFragment, ViewGroup viewGroup, View view, Integer num, int i) {
        LogUtils.file("TextActivity TextAttrFragment mColorAdapter.setOnItemClickListener");
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        EventText eventText2 = null;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextColor(i + 2);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        for (TextEmojiVO textEmojiVO : eventText3.getTextEmojiVO()) {
            EventText eventText4 = textAttrFragment.eventText;
            if (eventText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText4 = null;
            }
            textEmojiVO.setTextColor(eventText4.getTextColor());
        }
        EventText eventText5 = textAttrFragment.eventText;
        if (eventText5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText5 = null;
        }
        eventText5.setChangeMode(TextAttrEnum.TextColor);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText6 = textAttrFragment.eventText;
        if (eventText6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText2 = eventText6;
        }
        textChangedLiveData.setValue(eventText2);
        int i2 = textAttrFragment.mSelectColorPos;
        textAttrFragment.mSelectColorPos = i;
        if (i2 != -1) {
            textAttrFragment.getMColorAdapter().notifyItemChanged(i2);
        }
        textAttrFragment.getMColorAdapter().notifyItemChanged(textAttrFragment.mSelectColorPos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$10(TextAttrFragment textAttrFragment, ViewGroup viewGroup, View view, Integer num, int i) {
        int parseColor;
        switch (i) {
            case 0:
                parseColor = Color.parseColor("#FF0000");
                break;
            case 1:
                parseColor = Color.parseColor("#00FF00");
                break;
            case 2:
                parseColor = Color.parseColor("#0000FF");
                break;
            case 3:
                parseColor = Color.parseColor("#00FFFF");
                break;
            case 4:
                parseColor = Color.parseColor("#FFFF00");
                break;
            case 5:
                parseColor = Color.parseColor("#FF00FF");
                break;
            case 6:
                parseColor = Color.parseColor("#FFFFFF");
                break;
            default:
                parseColor = Color.parseColor("#FFFFFF");
                break;
        }
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        EventText eventText2 = null;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextColor(parseColor);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        for (TextEmojiVO textEmojiVO : eventText3.getTextEmojiVO()) {
            EventText eventText4 = textAttrFragment.eventText;
            if (eventText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText4 = null;
            }
            textEmojiVO.setTextColor(eventText4.getTextColor());
        }
        EventText eventText5 = textAttrFragment.eventText;
        if (eventText5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText5 = null;
        }
        eventText5.setChangeMode(TextAttrEnum.TextColor);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText6 = textAttrFragment.eventText;
        if (eventText6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText2 = eventText6;
        }
        textChangedLiveData.setValue(eventText2);
        int i2 = textAttrFragment.mSelectColorBcPos;
        textAttrFragment.mSelectColorBcPos = i;
        if (i2 != -1) {
            textAttrFragment.getMColorBcAdapter().notifyItemChanged(i2);
        }
        textAttrFragment.getMColorBcAdapter().notifyItemChanged(textAttrFragment.mSelectColorBcPos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$11(TextAttrFragment textAttrFragment, ViewGroup viewGroup, View view, Integer num, int i) {
        int parseColor;
        switch (i) {
            case 0:
                parseColor = Color.parseColor("#FF0000");
                break;
            case 1:
                parseColor = Color.parseColor("#00FF00");
                break;
            case 2:
                parseColor = Color.parseColor("#0000FF");
                break;
            case 3:
                parseColor = Color.parseColor("#00FFFF");
                break;
            case 4:
                parseColor = Color.parseColor("#FFFF00");
                break;
            case 5:
                parseColor = Color.parseColor("#FF00FF");
                break;
            case 6:
                parseColor = Color.parseColor("#FFFFFF");
                break;
            default:
                parseColor = Color.parseColor("#FFFFFF");
                break;
        }
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        EventText eventText2 = null;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextBgColor(parseColor);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        eventText3.setChangeMode(TextAttrEnum.TextBgColor);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText4 = textAttrFragment.eventText;
        if (eventText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText2 = eventText4;
        }
        textChangedLiveData.setValue(eventText2);
        int i2 = textAttrFragment.mSelectColorBgPos;
        textAttrFragment.mSelectColorBgPos = i;
        if (i2 != -1) {
            textAttrFragment.getMColorBgAdapter().notifyItemChanged(i2);
        }
        textAttrFragment.getMColorBgAdapter().notifyItemChanged(textAttrFragment.mSelectColorBgPos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$12(TextAttrFragment textAttrFragment, View view) {
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        EventText eventText2 = null;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextBgColor(ViewCompat.MEASURED_STATE_MASK);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        eventText3.setChangeMode(TextAttrEnum.TextBgColor);
        RelativeLayout relativeLayout = textAttrFragment.rl_outside_bgcolor_frame2_bc;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_outside_bgcolor_frame2_bc");
            relativeLayout = null;
        }
        relativeLayout.setBackgroundResource(R.drawable.item_sel);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText4 = textAttrFragment.eventText;
        if (eventText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText2 = eventText4;
        }
        textChangedLiveData.setValue(eventText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$13(TextAttrFragment textAttrFragment, View view) {
        LogUtils.file("TextActivity TextAttrFragment colorBarView_bg.setOnColorChangerListener ");
        ImageView imageView = textAttrFragment.text_direction_vertical_bc;
        EventText eventText = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_direction_vertical_bc");
            imageView = null;
        }
        imageView.setSelected(true);
        ImageView imageView2 = textAttrFragment.text_direction_horizontal_bc;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_direction_horizontal_bc");
            imageView2 = null;
        }
        imageView2.setSelected(false);
        EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText2;
        if (eventText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText2 = null;
        }
        eventText2.setTextdirction(1);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText = eventText3;
        }
        eventText.setChangeMode(TextAttrEnum.TextDirction);
        EventBus.getDefault().post(new EventNotifity(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$14(TextAttrFragment textAttrFragment, View view) {
        LogUtils.file("TextActivity TextAttrFragment  text_direction_horizontal.setOnClickListener ");
        ImageView imageView = textAttrFragment.text_direction_horizontal_bc;
        EventText eventText = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_direction_horizontal_bc");
            imageView = null;
        }
        imageView.setSelected(true);
        ImageView imageView2 = textAttrFragment.text_direction_vertical_bc;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_direction_vertical_bc");
            imageView2 = null;
        }
        imageView2.setSelected(false);
        EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText2;
        if (eventText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText2 = null;
        }
        eventText2.setTextdirction(0);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText = eventText3;
        }
        eventText.setChangeMode(TextAttrEnum.TextDirction);
        EventBus.getDefault().post(new EventNotifity(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$15(TextAttrFragment textAttrFragment, View view) {
        textAttrFragment.mIsBold = !textAttrFragment.mIsBold;
        ImageView imageView = textAttrFragment.text_bold_iv_bc;
        EventText eventText = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_bold_iv_bc");
            imageView = null;
        }
        imageView.setSelected(textAttrFragment.mIsBold);
        EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText2;
        if (eventText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText2 = null;
        }
        eventText2.setBold(textAttrFragment.mIsBold);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        eventText3.setChangeMode(TextAttrEnum.TextBold);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText4 = textAttrFragment.eventText;
        if (eventText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText = eventText4;
        }
        textChangedLiveData.setValue(eventText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$16(final TextAttrFragment textAttrFragment, View view) {
        TextBorderDiaglog textBorderDiaglog = new TextBorderDiaglog();
        textAttrFragment.mTextBorderDiaglog = textBorderDiaglog;
        Intrinsics.checkNotNull(textBorderDiaglog);
        textBorderDiaglog.show(textAttrFragment.getChildFragmentManager(), "border");
        TextBorderDiaglog textBorderDiaglog2 = textAttrFragment.mTextBorderDiaglog;
        Intrinsics.checkNotNull(textBorderDiaglog2);
        textBorderDiaglog2.setOnClickListener(new TextBorderDiaglog.TextBorderClickLinstener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$bindListener$17$1
            @Override // com.wifiled.ipixels.ui.subzone.TextBorderDiaglog.TextBorderClickLinstener
            public void onCancelClick() {
            }

            @Override // com.wifiled.ipixels.ui.subzone.TextBorderDiaglog.TextBorderClickLinstener
            public void onSubmitClick(int position, int effectPosition, int speed) {
                EventText eventText;
                EventText eventText2;
                EventText eventText3;
                TextBorderDiaglog textBorderDiaglog3;
                ImageView imageView;
                int[] iArr;
                EventText eventText4;
                TextViewModel textViewModel;
                EventText eventText5;
                TextAttrFragment.this.setMBorderPos(position);
                TextAttrFragment.this.setMBorderSpeed(speed);
                TextAttrFragment.this.setMBorderEffectPos(effectPosition);
                TextAttrFragment.this.eventText = TextEmojiBuilder.INSTANCE.getEventText();
                eventText = TextAttrFragment.this.eventText;
                EventText eventText6 = null;
                if (eventText == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText = null;
                }
                eventText.setBorderType(TextAttrFragment.this.getMBorderPos());
                eventText2 = TextAttrFragment.this.eventText;
                if (eventText2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText2 = null;
                }
                eventText2.setBorderSpeed(TextAttrFragment.this.getMBorderSpeed());
                eventText3 = TextAttrFragment.this.eventText;
                if (eventText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText3 = null;
                }
                eventText3.setBorderEffects(TextAttrFragment.this.getMBorderEffectPos());
                textBorderDiaglog3 = TextAttrFragment.this.mTextBorderDiaglog;
                Intrinsics.checkNotNull(textBorderDiaglog3);
                textBorderDiaglog3.dismiss();
                imageView = TextAttrFragment.this.iv_border_bc;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("iv_border_bc");
                    imageView = null;
                }
                iArr = TextAttrFragment.this.mBorderData2;
                imageView.setImageResource(iArr != null ? iArr[position] : 0);
                eventText4 = TextAttrFragment.this.eventText;
                if (eventText4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText4 = null;
                }
                eventText4.setChangeMode(TextAttrEnum.TextColor);
                textViewModel = TextAttrFragment.this.getTextViewModel();
                BusMutableLiveData<EventText> textChangedLiveData = textViewModel.getTextChangedLiveData();
                eventText5 = TextAttrFragment.this.eventText;
                if (eventText5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                } else {
                    eventText6 = eventText5;
                }
                textChangedLiveData.setValue(eventText6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$17(TextAttrFragment textAttrFragment, View view) {
        LogUtils.file("TextActivity TextAttrFragment  cl_text_size_set.setOnClickListener ");
        Dialog dialog = textAttrFragment.mTextSizeDialog;
        if (dialog != null) {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$18(TextAttrFragment textAttrFragment, View view) {
        Dialog dialog = textAttrFragment.fontDialog;
        if (dialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontDialog");
            dialog = null;
        }
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$19(TextAttrFragment textAttrFragment, View view) {
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        EventText eventText2 = null;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextBgColor(ViewCompat.MEASURED_STATE_MASK);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        eventText3.setChangeMode(TextAttrEnum.TextBgColor);
        RelativeLayout relativeLayout = textAttrFragment.rl_outside_bgcolor_frame2;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_outside_bgcolor_frame2");
            relativeLayout = null;
        }
        relativeLayout.setBackgroundResource(R.drawable.item_sel);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText4 = textAttrFragment.eventText;
        if (eventText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText2 = eventText4;
        }
        textChangedLiveData.setValue(eventText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$21(TextAttrFragment textAttrFragment, ViewGroup viewGroup, View view, Integer num, int i) {
        LogUtils.file("TextActivity TextAttrFragment mColorAdapter.setOnItemClickListener");
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        EventText eventText2 = null;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextColor(i + 2);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        for (TextEmojiVO textEmojiVO : eventText3.getTextEmojiVO()) {
            EventText eventText4 = textAttrFragment.eventText;
            if (eventText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText4 = null;
            }
            textEmojiVO.setTextColor(eventText4.getTextColor());
        }
        EventText eventText5 = textAttrFragment.eventText;
        if (eventText5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText5 = null;
        }
        eventText5.setChangeMode(TextAttrEnum.TextColor);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText6 = textAttrFragment.eventText;
        if (eventText6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText2 = eventText6;
        }
        textChangedLiveData.setValue(eventText2);
        int i2 = textAttrFragment.mSelectColorPos;
        textAttrFragment.mSelectColorPos = i;
        if (i2 != -1) {
            textAttrFragment.getMColorAdapter().notifyItemChanged(i2);
        }
        textAttrFragment.getMColorAdapter().notifyItemChanged(textAttrFragment.mSelectColorPos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$23(TextAttrFragment textAttrFragment, int i) {
        LogUtils.file("TextActivity TextAttrFragment colorBarView_text.setOnColorChangerListener ");
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        EventText eventText2 = null;
        if (eventText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText = null;
        }
        eventText.setTextColor(i);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        for (TextEmojiVO textEmojiVO : eventText3.getTextEmojiVO()) {
            EventText eventText4 = textAttrFragment.eventText;
            if (eventText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText4 = null;
            }
            textEmojiVO.setTextColor(eventText4.getTextColor());
        }
        EventText eventText5 = textAttrFragment.eventText;
        if (eventText5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText5 = null;
        }
        eventText5.setChangeMode(TextAttrEnum.TextColor);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText6 = textAttrFragment.eventText;
        if (eventText6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText2 = eventText6;
        }
        textChangedLiveData.setValue(eventText2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$25(TextAttrFragment textAttrFragment, int i) {
        LogUtils.file("TextActivity TextAttrFragment colorBarView_bg.setOnColorChangerListener ");
        RelativeLayout relativeLayout = textAttrFragment.rl_outside_bgcolor_frame2;
        EventText eventText = null;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_outside_bgcolor_frame2");
            relativeLayout = null;
        }
        relativeLayout.setBackground(null);
        EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText2;
        if (eventText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText2 = null;
        }
        eventText2.setTextBgColor(i);
        TextAttrEnum textAttrEnum = TextAttrEnum.TextBgColor;
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        eventText3.setChangeMode(textAttrEnum);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText4 = textAttrFragment.eventText;
        if (eventText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText = eventText4;
        }
        textChangedLiveData.setValue(eventText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$26(TextAttrFragment textAttrFragment, View view) {
        LogUtils.file("TextActivity TextAttrFragment colorBarView_bg.setOnColorChangerListener ");
        ImageView imageView = textAttrFragment.text_direction_vertical;
        EventText eventText = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_direction_vertical");
            imageView = null;
        }
        imageView.setSelected(true);
        ImageView imageView2 = textAttrFragment.text_direction_horizontal;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_direction_horizontal");
            imageView2 = null;
        }
        imageView2.setSelected(false);
        EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText2;
        if (eventText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText2 = null;
        }
        eventText2.setTextdirction(1);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText = eventText3;
        }
        eventText.setChangeMode(TextAttrEnum.TextDirction);
        EventBus.getDefault().post(new EventNotifity(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$27(TextAttrFragment textAttrFragment, View view) {
        LogUtils.file("TextActivity TextAttrFragment  text_direction_horizontal.setOnClickListener ");
        ImageView imageView = textAttrFragment.text_direction_horizontal;
        EventText eventText = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_direction_horizontal");
            imageView = null;
        }
        imageView.setSelected(true);
        ImageView imageView2 = textAttrFragment.text_direction_vertical;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_direction_vertical");
            imageView2 = null;
        }
        imageView2.setSelected(false);
        EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText2;
        if (eventText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText2 = null;
        }
        eventText2.setTextdirction(0);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText = eventText3;
        }
        eventText.setChangeMode(TextAttrEnum.TextDirction);
        EventBus.getDefault().post(new EventNotifity(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$28(TextAttrFragment textAttrFragment, View view) {
        textAttrFragment.mIsBold = !textAttrFragment.mIsBold;
        ImageView imageView = textAttrFragment.text_bold_iv;
        EventText eventText = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_bold_iv");
            imageView = null;
        }
        imageView.setSelected(textAttrFragment.mIsBold);
        EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText2;
        if (eventText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText2 = null;
        }
        eventText2.setBold(textAttrFragment.mIsBold);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        eventText3.setChangeMode(TextAttrEnum.TextBold);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText4 = textAttrFragment.eventText;
        if (eventText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText = eventText4;
        }
        textChangedLiveData.setValue(eventText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$29(final TextAttrFragment textAttrFragment, View view) {
        TextBorderDiaglog textBorderDiaglog = new TextBorderDiaglog();
        textAttrFragment.mTextBorderDiaglog = textBorderDiaglog;
        Intrinsics.checkNotNull(textBorderDiaglog);
        textBorderDiaglog.show(textAttrFragment.getParentFragmentManager(), "border");
        TextBorderDiaglog textBorderDiaglog2 = textAttrFragment.mTextBorderDiaglog;
        Intrinsics.checkNotNull(textBorderDiaglog2);
        textBorderDiaglog2.setOnClickListener(new TextBorderDiaglog.TextBorderClickLinstener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$bindListener$28$1
            @Override // com.wifiled.ipixels.ui.subzone.TextBorderDiaglog.TextBorderClickLinstener
            public void onCancelClick() {
            }

            @Override // com.wifiled.ipixels.ui.subzone.TextBorderDiaglog.TextBorderClickLinstener
            public void onSubmitClick(int position, int effectPosition, int speed) {
                EventText eventText;
                EventText eventText2;
                EventText eventText3;
                TextBorderDiaglog textBorderDiaglog3;
                ImageView imageView;
                int[] iArr;
                EventText eventText4;
                TextViewModel textViewModel;
                EventText eventText5;
                TextAttrFragment.this.setMBorderPos(position);
                TextAttrFragment.this.setMBorderSpeed(speed);
                TextAttrFragment.this.setMBorderEffectPos(effectPosition);
                TextAttrFragment.this.eventText = TextEmojiBuilder.INSTANCE.getEventText();
                eventText = TextAttrFragment.this.eventText;
                EventText eventText6 = null;
                if (eventText == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText = null;
                }
                eventText.setBorderType(TextAttrFragment.this.getMBorderPos());
                eventText2 = TextAttrFragment.this.eventText;
                if (eventText2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText2 = null;
                }
                eventText2.setBorderSpeed(TextAttrFragment.this.getMBorderSpeed());
                eventText3 = TextAttrFragment.this.eventText;
                if (eventText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText3 = null;
                }
                eventText3.setBorderEffects(TextAttrFragment.this.getMBorderEffectPos());
                textBorderDiaglog3 = TextAttrFragment.this.mTextBorderDiaglog;
                Intrinsics.checkNotNull(textBorderDiaglog3);
                textBorderDiaglog3.dismiss();
                imageView = TextAttrFragment.this.iv_border;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("iv_border");
                    imageView = null;
                }
                iArr = TextAttrFragment.this.mBorderData2;
                imageView.setImageResource(iArr != null ? iArr[position] : 0);
                eventText4 = TextAttrFragment.this.eventText;
                if (eventText4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText4 = null;
                }
                eventText4.setChangeMode(TextAttrEnum.TextColor);
                textViewModel = TextAttrFragment.this.getTextViewModel();
                BusMutableLiveData<EventText> textChangedLiveData = textViewModel.getTextChangedLiveData();
                eventText5 = TextAttrFragment.this.eventText;
                if (eventText5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                } else {
                    eventText6 = eventText5;
                }
                textChangedLiveData.setValue(eventText6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$30(TextAttrFragment textAttrFragment, View view) {
        LogUtils.file("TextActivity TextAttrFragment  cl_text_size_set.setOnClickListener ");
        Dialog dialog = textAttrFragment.mTextSizeDialog;
        if (dialog != null) {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$31(TextAttrFragment textAttrFragment, View view) {
        Dialog dialog = textAttrFragment.fontDialog;
        if (dialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontDialog");
            dialog = null;
        }
        dialog.show();
    }

    private final void init32128TextSizeDialog() {
        FragmentActivity mActivity = this.mActivity;
        Intrinsics.checkNotNullExpressionValue(mActivity, "mActivity");
        this.mTextSizeAdapter = new IosDialogStyleAdapter<>(mActivity, this.mTextSize32_128List);
        Dialog dialog = new Dialog(this.mActivity, R.style.BottomDialogStyle);
        this.mTextSizeDialog = dialog;
        Intrinsics.checkNotNull(dialog);
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        dialog.setContentView(LayoutInflater.from(this.mActivity).inflate(R.layout.dialog_select_media_small, (ViewGroup) null));
        Dialog dialog2 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog2);
        dialog2.setCanceledOnTouchOutside(true);
        Dialog dialog3 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog3);
        dialog3.setCancelable(true);
        Dialog dialog4 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog4);
        Window window = dialog4.getWindow();
        Intrinsics.checkNotNull(window);
        window.getAttributes().width = ScreenUtil.getScreenWidth(this.mActivity);
        Dialog dialog5 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog5);
        Window window2 = dialog5.getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setGravity(80);
        Dialog dialog6 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog6);
        Window window3 = dialog6.getWindow();
        Intrinsics.checkNotNull(window3);
        window3.setWindowAnimations(R.style.BottomDialogAnimation);
        Dialog dialog7 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog7);
        TextView textView = (TextView) dialog7.findViewById(R.id.tv_cancel);
        Dialog dialog8 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog8);
        this.mFontSizeRecyclerView = (RecyclerView) dialog8.findViewById(R.id.rl_actions);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda40
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextAttrFragment.init32128TextSizeDialog$lambda$32(TextAttrFragment.this, view);
            }
        });
        RecyclerView recyclerView = this.mFontSizeRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(CoreBase.getContext(), 1, false));
        }
        RecyclerView recyclerView2 = this.mFontSizeRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(CoreBase.getContext(), 1));
        }
        RecyclerView recyclerView3 = this.mFontSizeRecyclerView;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.mTextSizeAdapter;
            if (iosDialogStyleAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
                iosDialogStyleAdapter2 = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter2);
        }
        RecyclerView recyclerView4 = this.mFontSizeRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda41
                @Override // java.lang.Runnable
                public final void run() {
                    TextAttrFragment.init32128TextSizeDialog$lambda$33(TextAttrFragment.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mTextSizeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda42
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextAttrFragment.init32128TextSizeDialog$lambda$38(TextAttrFragment.this, viewGroup, view, obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init32128TextSizeDialog$lambda$32(TextAttrFragment textAttrFragment, View view) {
        Dialog dialog = textAttrFragment.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init32128TextSizeDialog$lambda$33(TextAttrFragment textAttrFragment) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = textAttrFragment.mTextSizeAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = textAttrFragment.mTextSizeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = textAttrFragment.mFontSizeRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init32128TextSizeDialog$lambda$38(final TextAttrFragment textAttrFragment, ViewGroup viewGroup, View view, Object obj, final int i) {
        EventText eventText;
        LogUtils.file("TextActivity TextAttrFragment   mTextSizeAdapter.setOnItemClickListener position=" + i);
        Dialog dialog = textAttrFragment.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
        TextView textView = textAttrFragment.tv_text_size_32_128;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_text_size_32_128");
            textView = null;
        }
        textView.setText(String.valueOf(textAttrFragment.textSizes.get(i).intValue()));
        int intValue = textAttrFragment.textSizes.get(i).intValue();
        textAttrFragment.nextSize = intValue;
        if (textAttrFragment.prevSize != intValue) {
            EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
            textAttrFragment.eventText = eventText2;
            if (eventText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            } else {
                eventText = eventText2;
            }
            final EventText copy$default = EventText.copy$default(eventText, null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null);
            int curFontLimit = TextEmojiBuilder.INSTANCE.getCurFontLimit(textAttrFragment.nextSize);
            EventText eventText3 = textAttrFragment.eventText;
            if (eventText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText3 = null;
            }
            for (TextEmojiVO textEmojiVO : eventText3.getTextEmojiVO()) {
                if (textEmojiVO.getType() != 1) {
                    CharacterUtilsKt.isChinese(textEmojiVO.getText().charAt(0));
                }
            }
            final ArrayList arrayList = new ArrayList();
            int i2 = 0;
            for (TextEmojiVO textEmojiVO2 : copy$default.getTextEmojiVO()) {
                i2 = textEmojiVO2.getType() == 1 ? true : CharacterUtilsKt.isChinese(textEmojiVO2.getText().charAt(0)) ? i2 + 2 : i2 + 1;
                if (i2 <= curFontLimit) {
                    arrayList.add(textEmojiVO2);
                }
            }
            copy$default.setTextEmojiVO(arrayList);
            boolean z = i2 >= curFontLimit;
            if (!z) {
                if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                TextView textView3 = textAttrFragment.tv_text_size_32_128;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_text_size_32_128");
                } else {
                    textView2 = textView3;
                }
                textView2.setText(String.valueOf(textAttrFragment.textSizes.get(i).intValue()));
                copy$default.setTextSize(textAttrFragment.textSizes.get(i).intValue());
                copy$default.setChangeMode(TextAttrEnum.TextSize);
                textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(copy$default);
                if (textAttrFragment.textSizes.get(i).intValue() == 16) {
                    textAttrFragment.isDisableBold(false);
                    return;
                } else {
                    textAttrFragment.isDisableBold(true);
                    return;
                }
            }
            boolean z2 = i2 == curFontLimit;
            if (!z2) {
                if (z2) {
                    throw new NoWhenBranchMatchedException();
                }
                CustomDialog.Builder builder = new CustomDialog.Builder(textAttrFragment.requireActivity());
                builder.setTitle(textAttrFragment.getString(R.string.gps_tip));
                builder.setMessage(textAttrFragment.getString(R.string.textinput_tip));
                builder.setPositiveButton(textAttrFragment.getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        TextAttrFragment.init32128TextSizeDialog$lambda$38$lambda$36(TextAttrFragment.this, i, copy$default, arrayList, dialogInterface, i3);
                    }
                });
                builder.setNegativeButton(textAttrFragment.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda11
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
                return;
            }
            TextView textView4 = textAttrFragment.tv_text_size_32_128;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_text_size_32_128");
            } else {
                textView2 = textView4;
            }
            textView2.setText(String.valueOf(textAttrFragment.textSizes.get(i).intValue()));
            copy$default.setTextSize(textAttrFragment.textSizes.get(i).intValue());
            copy$default.setChangeMode(TextAttrEnum.TextSize);
            textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(copy$default);
            textAttrFragment.prevSize = textAttrFragment.nextSize;
            if (textAttrFragment.textSizes.get(i).intValue() == 16) {
                textAttrFragment.isDisableBold(false);
            } else {
                textAttrFragment.isDisableBold(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init32128TextSizeDialog$lambda$38$lambda$36(TextAttrFragment textAttrFragment, int i, EventText eventText, List list, DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        TextView textView = textAttrFragment.tv_text_size_32_128;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_text_size_32_128");
            textView = null;
        }
        textView.setText(String.valueOf(textAttrFragment.textSizes.get(i).intValue()));
        eventText.setTextSize(textAttrFragment.textSizes.get(i).intValue());
        eventText.setChangeMode(TextAttrEnum.TextSize);
        textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(eventText);
        textAttrFragment.prevSize = textAttrFragment.nextSize;
        eventText.setTextEmojiVO(list);
        eventText.setChangeMode(TextAttrEnum.TextChange);
        textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(eventText);
        if (textAttrFragment.textSizes.get(i).intValue() == 16) {
            textAttrFragment.isDisableBold(false);
        } else {
            textAttrFragment.isDisableBold(true);
        }
    }

    private final void isDisableBold(boolean r6) {
        ImageView imageView = null;
        if (r6) {
            ConstraintLayout constraintLayout = this.text_bold_layout;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("text_bold_layout");
                constraintLayout = null;
            }
            constraintLayout.setAlpha(1.0f);
            ConstraintLayout constraintLayout2 = this.text_bold_layout_bc;
            if (constraintLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("text_bold_layout_bc");
                constraintLayout2 = null;
            }
            constraintLayout2.setAlpha(1.0f);
        } else {
            ConstraintLayout constraintLayout3 = this.text_bold_layout;
            if (constraintLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("text_bold_layout");
                constraintLayout3 = null;
            }
            constraintLayout3.setAlpha(0.6f);
            ConstraintLayout constraintLayout4 = this.text_bold_layout_bc;
            if (constraintLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("text_bold_layout_bc");
                constraintLayout4 = null;
            }
            constraintLayout4.setAlpha(0.6f);
        }
        ImageView imageView2 = this.text_bold_iv;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_bold_iv");
            imageView2 = null;
        }
        imageView2.setSelected(r6);
        ConstraintLayout constraintLayout5 = this.text_bold_layout;
        if (constraintLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_bold_layout");
            constraintLayout5 = null;
        }
        constraintLayout5.setEnabled(r6);
        ImageView imageView3 = this.text_bold_iv;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_bold_iv");
            imageView3 = null;
        }
        imageView3.setEnabled(r6);
        ImageView imageView4 = this.text_bold_iv_bc;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_bold_iv_bc");
            imageView4 = null;
        }
        imageView4.setSelected(r6);
        ConstraintLayout constraintLayout6 = this.text_bold_layout_bc;
        if (constraintLayout6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_bold_layout_bc");
            constraintLayout6 = null;
        }
        constraintLayout6.setEnabled(r6);
        ImageView imageView5 = this.text_bold_iv_bc;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("text_bold_iv_bc");
        } else {
            imageView = imageView5;
        }
        imageView.setEnabled(r6);
        this.mIsBold = r6;
    }

    private final void init32128TextSizeDialog2() {
        FragmentActivity mActivity = this.mActivity;
        Intrinsics.checkNotNullExpressionValue(mActivity, "mActivity");
        this.mTextSizeAdapter = new IosDialogStyleAdapter<>(mActivity, this.mTextSize32_128List);
        Dialog dialog = new Dialog(this.mActivity, R.style.BottomDialogStyle);
        this.mTextSizeDialog = dialog;
        Intrinsics.checkNotNull(dialog);
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        dialog.setContentView(LayoutInflater.from(this.mActivity).inflate(R.layout.dialog_select_media_small, (ViewGroup) null));
        Dialog dialog2 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog2);
        dialog2.setCanceledOnTouchOutside(true);
        Dialog dialog3 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog3);
        dialog3.setCancelable(true);
        Dialog dialog4 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog4);
        Window window = dialog4.getWindow();
        Intrinsics.checkNotNull(window);
        window.getAttributes().width = ScreenUtil.getScreenWidth(this.mActivity);
        Dialog dialog5 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog5);
        Window window2 = dialog5.getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setGravity(80);
        Dialog dialog6 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog6);
        Window window3 = dialog6.getWindow();
        Intrinsics.checkNotNull(window3);
        window3.setWindowAnimations(R.style.BottomDialogAnimation);
        Dialog dialog7 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog7);
        TextView textView = (TextView) dialog7.findViewById(R.id.tv_cancel);
        Dialog dialog8 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog8);
        this.mFontSizeRecyclerView = (RecyclerView) dialog8.findViewById(R.id.rl_actions);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda46
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextAttrFragment.init32128TextSizeDialog2$lambda$39(TextAttrFragment.this, view);
            }
        });
        RecyclerView recyclerView = this.mFontSizeRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(CoreBase.getContext(), 1, false));
        }
        RecyclerView recyclerView2 = this.mFontSizeRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(CoreBase.getContext(), 1));
        }
        RecyclerView recyclerView3 = this.mFontSizeRecyclerView;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.mTextSizeAdapter;
            if (iosDialogStyleAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
                iosDialogStyleAdapter2 = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter2);
        }
        RecyclerView recyclerView4 = this.mFontSizeRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda47
                @Override // java.lang.Runnable
                public final void run() {
                    TextAttrFragment.init32128TextSizeDialog2$lambda$40(TextAttrFragment.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mTextSizeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda48
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextAttrFragment.init32128TextSizeDialog2$lambda$45(TextAttrFragment.this, viewGroup, view, obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init32128TextSizeDialog2$lambda$39(TextAttrFragment textAttrFragment, View view) {
        Dialog dialog = textAttrFragment.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init32128TextSizeDialog2$lambda$40(TextAttrFragment textAttrFragment) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = textAttrFragment.mTextSizeAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = textAttrFragment.mTextSizeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = textAttrFragment.mFontSizeRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init32128TextSizeDialog2$lambda$45(final TextAttrFragment textAttrFragment, ViewGroup viewGroup, View view, Object obj, final int i) {
        EventText eventText;
        LogUtils.file("TextActivity TextAttrFragment   mTextSizeAdapter.setOnItemClickListener position=" + i);
        Dialog dialog = textAttrFragment.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
        TextView textView = textAttrFragment.tv_text_size_32_bc;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_text_size_32_bc");
            textView = null;
        }
        textView.setText(String.valueOf(textAttrFragment.textSizes.get(i).intValue()));
        int intValue = textAttrFragment.textSizes.get(i).intValue();
        textAttrFragment.nextSize = intValue;
        if (textAttrFragment.prevSize != intValue) {
            EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
            textAttrFragment.eventText = eventText2;
            if (eventText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            } else {
                eventText = eventText2;
            }
            final EventText copy$default = EventText.copy$default(eventText, null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null);
            int curFontLimit = TextEmojiBuilder.INSTANCE.getCurFontLimit(textAttrFragment.nextSize);
            EventText eventText3 = textAttrFragment.eventText;
            if (eventText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText3 = null;
            }
            for (TextEmojiVO textEmojiVO : eventText3.getTextEmojiVO()) {
                if (textEmojiVO.getType() != 1) {
                    CharacterUtilsKt.isChinese(textEmojiVO.getText().charAt(0));
                }
            }
            final ArrayList arrayList = new ArrayList();
            int i2 = 0;
            for (TextEmojiVO textEmojiVO2 : copy$default.getTextEmojiVO()) {
                i2 = textEmojiVO2.getType() == 1 ? true : CharacterUtilsKt.isChinese(textEmojiVO2.getText().charAt(0)) ? i2 + 2 : i2 + 1;
                if (i2 <= curFontLimit) {
                    arrayList.add(textEmojiVO2);
                }
            }
            copy$default.setTextEmojiVO(arrayList);
            boolean z = i2 >= curFontLimit;
            if (!z) {
                if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                TextView textView3 = textAttrFragment.tv_text_size_32_bc;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_text_size_32_bc");
                } else {
                    textView2 = textView3;
                }
                textView2.setText(String.valueOf(textAttrFragment.textSizes.get(i).intValue()));
                copy$default.setTextSize(textAttrFragment.textSizes.get(i).intValue());
                copy$default.setChangeMode(TextAttrEnum.TextSize);
                textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(copy$default);
                if (textAttrFragment.textSizes.get(i).intValue() == 16) {
                    textAttrFragment.isDisableBold(false);
                    return;
                } else {
                    textAttrFragment.isDisableBold(true);
                    return;
                }
            }
            boolean z2 = i2 == curFontLimit;
            if (!z2) {
                if (z2) {
                    throw new NoWhenBranchMatchedException();
                }
                CustomDialog.Builder builder = new CustomDialog.Builder(textAttrFragment.requireActivity());
                builder.setTitle(textAttrFragment.getString(R.string.gps_tip));
                builder.setMessage(textAttrFragment.getString(R.string.textinput_tip));
                builder.setPositiveButton(textAttrFragment.getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda22
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        TextAttrFragment.init32128TextSizeDialog2$lambda$45$lambda$43(TextAttrFragment.this, i, copy$default, arrayList, dialogInterface, i3);
                    }
                });
                builder.setNegativeButton(textAttrFragment.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda33
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
                return;
            }
            TextView textView4 = textAttrFragment.tv_text_size_32_bc;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_text_size_32_bc");
            } else {
                textView2 = textView4;
            }
            textView2.setText(String.valueOf(textAttrFragment.textSizes.get(i).intValue()));
            copy$default.setTextSize(textAttrFragment.textSizes.get(i).intValue());
            copy$default.setChangeMode(TextAttrEnum.TextSize);
            textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(copy$default);
            textAttrFragment.prevSize = textAttrFragment.nextSize;
            if (textAttrFragment.textSizes.get(i).intValue() == 16) {
                textAttrFragment.isDisableBold(false);
            } else {
                textAttrFragment.isDisableBold(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init32128TextSizeDialog2$lambda$45$lambda$43(TextAttrFragment textAttrFragment, int i, EventText eventText, List list, DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        TextView textView = textAttrFragment.tv_text_size_32_bc;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_text_size_32_bc");
            textView = null;
        }
        textView.setText(String.valueOf(textAttrFragment.textSizes.get(i).intValue()));
        eventText.setTextSize(textAttrFragment.textSizes.get(i).intValue());
        eventText.setChangeMode(TextAttrEnum.TextSize);
        textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(eventText);
        textAttrFragment.prevSize = textAttrFragment.nextSize;
        eventText.setTextEmojiVO(list);
        eventText.setChangeMode(TextAttrEnum.TextChange);
        textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(eventText);
        if (textAttrFragment.textSizes.get(i).intValue() == 16) {
            textAttrFragment.isDisableBold(false);
        } else {
            textAttrFragment.isDisableBold(true);
        }
    }

    private final void initDefaultTextPara() {
        CustomImageView customImageView = this.iv_show_textcolor;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_show_textcolor");
            customImageView = null;
        }
        customImageView.setImageDrawable(new ColorDrawable(-1));
        RadioButton radioButton = this.rb_center;
        if (radioButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rb_center");
            radioButton = null;
        }
        radioButton.setChecked(true);
        RadioButton radioButton2 = this.rb_vertical_center;
        if (radioButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rb_vertical_center");
            radioButton2 = null;
        }
        radioButton2.setChecked(true);
        CustomImageView customImageView3 = this.iv_show_text_bgcolor;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_show_text_bgcolor");
        } else {
            customImageView2 = customImageView3;
        }
        customImageView2.setImageDrawable(this.mActivity.getDrawable(R.mipmap.bgcolor_forbidden));
    }

    private final void initFontAdapter() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        FragmentActivity fragmentActivity = requireActivity;
        List<String> list = this.fonts;
        Dialog dialog = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
            list = null;
        }
        this.fontAdapter = new IosDialogStyleAdapter<>(fragmentActivity, list);
        Dialog showBottomDialog = DialogUtils.showBottomDialog(requireActivity(), R.layout.dialog_select_media);
        Intrinsics.checkNotNullExpressionValue(showBottomDialog, "showBottomDialog(...)");
        this.fontDialog = showBottomDialog;
        if (showBottomDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontDialog");
            showBottomDialog = null;
        }
        showBottomDialog.cancel();
        Dialog dialog2 = this.fontDialog;
        if (dialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontDialog");
            dialog2 = null;
        }
        this.actionRecyclerView = (RecyclerView) dialog2.findViewById(R.id.rl_actions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), 1, false);
        RecyclerView recyclerView = this.actionRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.actionRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(this.mActivity, 1));
        }
        RecyclerView recyclerView3 = this.actionRecyclerView;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter = this.fontAdapter;
            if (iosDialogStyleAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fontAdapter");
                iosDialogStyleAdapter = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter);
        }
        LinearLayout linearLayout = this.style_rectangle;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("style_rectangle");
            linearLayout = null;
        }
        if (linearLayout.getVisibility() == 0) {
            TextView textView = this.tv_text_font;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_text_font");
                textView = null;
            }
            List<String> list2 = this.fonts;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fonts");
                list2 = null;
            }
            textView.setText(list2.get(0));
        } else {
            LinearLayout linearLayout2 = this.style_base_color;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("style_base_color");
                linearLayout2 = null;
            }
            if (linearLayout2.getVisibility() == 0) {
                TextView textView2 = this.tv_text_font_bc;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_text_font_bc");
                    textView2 = null;
                }
                List<String> list3 = this.fonts;
                if (list3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fonts");
                    list3 = null;
                }
                textView2.setText(list3.get(0));
            } else {
                LinearLayout linearLayout3 = this.style_square;
                if (linearLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("style_square");
                    linearLayout3 = null;
                }
                if (linearLayout3.getVisibility() == 0) {
                    AppCompatTextView appCompatTextView = this.tv_font;
                    if (appCompatTextView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tv_font");
                        appCompatTextView = null;
                    }
                    String string = getString(R.string.font_type);
                    List<String> list4 = this.fonts;
                    if (list4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fonts");
                        list4 = null;
                    }
                    appCompatTextView.setText(string + ((Object) list4.get(0)));
                }
            }
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.fontAdapter;
        if (iosDialogStyleAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontAdapter");
            iosDialogStyleAdapter2 = null;
        }
        iosDialogStyleAdapter2.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda31
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextAttrFragment.initFontAdapter$lambda$46(TextAttrFragment.this, viewGroup, view, obj, i);
            }
        });
        Dialog dialog3 = this.fontDialog;
        if (dialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontDialog");
        } else {
            dialog = dialog3;
        }
        TextView textView3 = (TextView) dialog.findViewById(R.id.tv_cancel);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda32
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextAttrFragment.initFontAdapter$lambda$47(TextAttrFragment.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        Intrinsics.checkNotNull(textView3);
        companion.attachViewOnTouchListener(textView3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0202  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void initFontAdapter$lambda$46(com.wifiled.ipixels.ui.text.TextAttrFragment r6, android.view.ViewGroup r7, android.view.View r8, java.lang.Object r9, int r10) {
        /*
            Method dump skipped, instructions count: 519
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.text.TextAttrFragment.initFontAdapter$lambda$46(com.wifiled.ipixels.ui.text.TextAttrFragment, android.view.ViewGroup, android.view.View, java.lang.Object, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initFontAdapter$lambda$47(TextAttrFragment textAttrFragment, View view) {
        Dialog dialog = textAttrFragment.fontDialog;
        if (dialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontDialog");
            dialog = null;
        }
        dialog.cancel();
    }

    private final void initTextSizeAdapter() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.textSizeAdapter = new IosDialogStyleAdapter<>(requireActivity, this.textSizes);
        AppCompatTextView appCompatTextView = this.tv_textSize;
        if (appCompatTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_textSize");
            appCompatTextView = null;
        }
        appCompatTextView.setText(getString(R.string.font_size) + this.textSizes.get(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTexturizeDialog() {
        final Dialog showBottomDialog = DialogUtils.showBottomDialog(requireActivity(), R.layout.dialog_select_media_small);
        this.actionRecyclerView = (RecyclerView) showBottomDialog.findViewById(R.id.rl_actions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CoreBase.getContext(), 1, false);
        RecyclerView recyclerView = this.actionRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.actionRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(CoreBase.getContext(), 1));
        }
        RecyclerView recyclerView3 = this.actionRecyclerView;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.textSizeAdapter;
            if (iosDialogStyleAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("textSizeAdapter");
                iosDialogStyleAdapter2 = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter2);
        }
        RecyclerView recyclerView4 = this.actionRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda43
                @Override // java.lang.Runnable
                public final void run() {
                    TextAttrFragment.showTexturizeDialog$lambda$48(TextAttrFragment.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.textSizeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textSizeAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda44
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextAttrFragment.showTexturizeDialog$lambda$53(showBottomDialog, this, viewGroup, view, obj, i);
            }
        });
        TextView textView = (TextView) showBottomDialog.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda45
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                showBottomDialog.cancel();
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        Intrinsics.checkNotNull(textView);
        companion.attachViewOnTouchListener(textView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTexturizeDialog$lambda$48(TextAttrFragment textAttrFragment) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = textAttrFragment.textSizeAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textSizeAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = textAttrFragment.textSizeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textSizeAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = textAttrFragment.actionRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTexturizeDialog$lambda$53(Dialog dialog, final TextAttrFragment textAttrFragment, ViewGroup viewGroup, View view, Object obj, final int i) {
        EventText eventText;
        LogUtils.file("TextActivity TextAttrFragment textSizeAdapter.setOnItemClickListener");
        dialog.cancel();
        int intValue = textAttrFragment.textSizes.get(i).intValue();
        textAttrFragment.nextSize = intValue;
        if (textAttrFragment.prevSize != intValue) {
            EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
            textAttrFragment.eventText = eventText2;
            AppCompatTextView appCompatTextView = null;
            if (eventText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            } else {
                eventText = eventText2;
            }
            final EventText copy$default = EventText.copy$default(eventText, null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null);
            int curFontLimit = TextEmojiBuilder.INSTANCE.getCurFontLimit(textAttrFragment.nextSize);
            EventText eventText3 = textAttrFragment.eventText;
            if (eventText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText3 = null;
            }
            Iterator<T> it = eventText3.getTextEmojiVO().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TextEmojiVO textEmojiVO = (TextEmojiVO) it.next();
                if (textEmojiVO.getType() != 1) {
                    CharacterUtilsKt.isChinese(textEmojiVO.getText().charAt(0));
                }
            }
            final ArrayList arrayList = new ArrayList();
            int i2 = 0;
            for (TextEmojiVO textEmojiVO2 : copy$default.getTextEmojiVO()) {
                i2 = textEmojiVO2.getType() == 1 ? true : CharacterUtilsKt.isChinese(textEmojiVO2.getText().charAt(0)) ? i2 + 2 : i2 + 1;
                if (i2 <= curFontLimit) {
                    arrayList.add(textEmojiVO2);
                }
            }
            copy$default.setTextEmojiVO(arrayList);
            boolean z = i2 >= curFontLimit;
            if (!z) {
                if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                AppCompatTextView appCompatTextView2 = textAttrFragment.tv_textSize;
                if (appCompatTextView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tv_textSize");
                } else {
                    appCompatTextView = appCompatTextView2;
                }
                appCompatTextView.setText(textAttrFragment.getString(R.string.font_size) + textAttrFragment.textSizes.get(i));
                copy$default.setTextSize(textAttrFragment.textSizes.get(i).intValue());
                copy$default.setChangeMode(TextAttrEnum.TextSize);
                textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(copy$default);
                return;
            }
            boolean z2 = i2 == curFontLimit;
            if (!z2) {
                if (z2) {
                    throw new NoWhenBranchMatchedException();
                }
                CustomDialog.Builder builder = new CustomDialog.Builder(textAttrFragment.requireActivity());
                builder.setTitle(textAttrFragment.getString(R.string.gps_tip));
                builder.setMessage(textAttrFragment.getString(R.string.textinput_tip));
                builder.setPositiveButton(textAttrFragment.getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        TextAttrFragment.showTexturizeDialog$lambda$53$lambda$51(TextAttrFragment.this, i, copy$default, arrayList, dialogInterface, i3);
                    }
                });
                builder.setNegativeButton(textAttrFragment.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
                return;
            }
            AppCompatTextView appCompatTextView3 = textAttrFragment.tv_textSize;
            if (appCompatTextView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_textSize");
            } else {
                appCompatTextView = appCompatTextView3;
            }
            appCompatTextView.setText(textAttrFragment.getString(R.string.font_size) + textAttrFragment.textSizes.get(i));
            copy$default.setTextSize(textAttrFragment.textSizes.get(i).intValue());
            copy$default.setChangeMode(TextAttrEnum.TextSize);
            textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(copy$default);
            textAttrFragment.prevSize = textAttrFragment.nextSize;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTexturizeDialog$lambda$53$lambda$51(TextAttrFragment textAttrFragment, int i, EventText eventText, List list, DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        AppCompatTextView appCompatTextView = textAttrFragment.tv_textSize;
        if (appCompatTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_textSize");
            appCompatTextView = null;
        }
        appCompatTextView.setText(textAttrFragment.getString(R.string.font_size) + textAttrFragment.textSizes.get(i));
        eventText.setTextSize(textAttrFragment.textSizes.get(i).intValue());
        eventText.setChangeMode(TextAttrEnum.TextSize);
        textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(eventText);
        textAttrFragment.prevSize = textAttrFragment.nextSize;
        eventText.setTextEmojiVO(list);
        eventText.setChangeMode(TextAttrEnum.TextChange);
        textAttrFragment.getTextViewModel().getTextChangedLiveData().setValue(eventText);
    }

    static /* synthetic */ void showColorDialog$default(TextAttrFragment textAttrFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textAttrFragment.showColorDialog(z);
    }

    private final void showColorDialog(boolean isTextColor) {
        Window window;
        this.eventText = TextEmojiBuilder.INSTANCE.getEventText();
        this.isTextColorMode = isTextColor;
        RelativeLayout relativeLayout = null;
        if (this.colorDialog == null) {
            Dialog showBottomDialog = DialogUtils.showBottomDialog(this.mWeakActivity.get(), R.layout.dialog_color_select);
            this.colorDialog = showBottomDialog;
            if (showBottomDialog != null && (window = showBottomDialog.getWindow()) != null) {
                window.clearFlags(2);
            }
            Dialog dialog = this.colorDialog;
            RoundColorPaletteHSV360 roundColorPaletteHSV360 = dialog != null ? (RoundColorPaletteHSV360) dialog.findViewById(R.id.color_picker) : null;
            Intrinsics.checkNotNull(roundColorPaletteHSV360);
            this.colorPickerView = roundColorPaletteHSV360;
            Dialog dialog2 = this.colorDialog;
            RelativeLayout relativeLayout2 = dialog2 != null ? (RelativeLayout) dialog2.findViewById(R.id.rl_gradient_01) : null;
            Intrinsics.checkNotNull(relativeLayout2);
            this.rlGradient01 = relativeLayout2;
            Dialog dialog3 = this.colorDialog;
            RelativeLayout relativeLayout3 = dialog3 != null ? (RelativeLayout) dialog3.findViewById(R.id.rl_gradient_02) : null;
            Intrinsics.checkNotNull(relativeLayout3);
            this.rlGradient02 = relativeLayout3;
            Dialog dialog4 = this.colorDialog;
            RelativeLayout relativeLayout4 = dialog4 != null ? (RelativeLayout) dialog4.findViewById(R.id.rl_gradient_03) : null;
            Intrinsics.checkNotNull(relativeLayout4);
            this.rlGradient03 = relativeLayout4;
            Dialog dialog5 = this.colorDialog;
            RelativeLayout relativeLayout5 = dialog5 != null ? (RelativeLayout) dialog5.findViewById(R.id.rl_gradient_04) : null;
            Intrinsics.checkNotNull(relativeLayout5);
            this.rlGradient04 = relativeLayout5;
            Dialog dialog6 = this.colorDialog;
            ImageView imageView = dialog6 != null ? (ImageView) dialog6.findViewById(R.id.iv_clear_bgcolor) : null;
            Intrinsics.checkNotNull(imageView);
            this.ivClearBgColor = imageView;
            Dialog dialog7 = this.colorDialog;
            ConstraintLayout constraintLayout = dialog7 != null ? (ConstraintLayout) dialog7.findViewById(R.id.cl_text_gradient) : null;
            Intrinsics.checkNotNull(constraintLayout);
            this.cl_text_gradient = constraintLayout;
            Dialog dialog8 = this.colorDialog;
            ImageView imageView2 = dialog8 != null ? (ImageView) dialog8.findViewById(R.id.iv_confirm_bgcolor) : null;
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            if (imageView2 != null) {
                UtilsExtensionKt.setOnClickFilterListener(imageView2, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda34
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit showColorDialog$lambda$55;
                        showColorDialog$lambda$55 = TextAttrFragment.showColorDialog$lambda$55(TextAttrFragment.this);
                        return showColorDialog$lambda$55;
                    }
                });
            }
            CustomImageView.Companion companion = CustomImageView.INSTANCE;
            Intrinsics.checkNotNull(imageView2);
            companion.attachViewOnTouchListener(imageView2);
            ImageView imageView3 = this.ivClearBgColor;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ivClearBgColor");
                imageView3 = null;
            }
            UtilsExtensionKt.setOnClickFilterListener(imageView3, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit showColorDialog$lambda$56;
                    showColorDialog$lambda$56 = TextAttrFragment.showColorDialog$lambda$56(Ref.BooleanRef.this, this);
                    return showColorDialog$lambda$56;
                }
            });
            CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
            ImageView imageView4 = this.ivClearBgColor;
            if (imageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ivClearBgColor");
                imageView4 = null;
            }
            companion2.attachViewOnTouchListener(imageView4);
            RoundColorPaletteHSV360 roundColorPaletteHSV3602 = this.colorPickerView;
            if (roundColorPaletteHSV3602 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("colorPickerView");
                roundColorPaletteHSV3602 = null;
            }
            roundColorPaletteHSV3602.setColorChangeCallBack(new TextAttrFragment$showColorDialog$3(this, booleanRef));
            RelativeLayout relativeLayout6 = this.rlGradient01;
            if (relativeLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient01");
                relativeLayout6 = null;
            }
            UtilsExtensionKt.setOnClickFilterListener(relativeLayout6, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit showColorDialog$lambda$58;
                    showColorDialog$lambda$58 = TextAttrFragment.showColorDialog$lambda$58(TextAttrFragment.this);
                    return showColorDialog$lambda$58;
                }
            });
            CustomImageView.Companion companion3 = CustomImageView.INSTANCE;
            RelativeLayout relativeLayout7 = this.rlGradient01;
            if (relativeLayout7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient01");
                relativeLayout7 = null;
            }
            companion3.attachViewOnTouchListener(relativeLayout7);
            RelativeLayout relativeLayout8 = this.rlGradient02;
            if (relativeLayout8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient02");
                relativeLayout8 = null;
            }
            UtilsExtensionKt.setOnClickFilterListener(relativeLayout8, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit showColorDialog$lambda$60;
                    showColorDialog$lambda$60 = TextAttrFragment.showColorDialog$lambda$60(TextAttrFragment.this);
                    return showColorDialog$lambda$60;
                }
            });
            CustomImageView.Companion companion4 = CustomImageView.INSTANCE;
            RelativeLayout relativeLayout9 = this.rlGradient02;
            if (relativeLayout9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient02");
                relativeLayout9 = null;
            }
            companion4.attachViewOnTouchListener(relativeLayout9);
            RelativeLayout relativeLayout10 = this.rlGradient03;
            if (relativeLayout10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient03");
                relativeLayout10 = null;
            }
            UtilsExtensionKt.setOnClickFilterListener(relativeLayout10, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit showColorDialog$lambda$62;
                    showColorDialog$lambda$62 = TextAttrFragment.showColorDialog$lambda$62(TextAttrFragment.this);
                    return showColorDialog$lambda$62;
                }
            });
            CustomImageView.Companion companion5 = CustomImageView.INSTANCE;
            RelativeLayout relativeLayout11 = this.rlGradient03;
            if (relativeLayout11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient03");
                relativeLayout11 = null;
            }
            companion5.attachViewOnTouchListener(relativeLayout11);
            RelativeLayout relativeLayout12 = this.rlGradient04;
            if (relativeLayout12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient04");
                relativeLayout12 = null;
            }
            UtilsExtensionKt.setOnClickFilterListener(relativeLayout12, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextAttrFragment$$ExternalSyntheticLambda39
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit showColorDialog$lambda$64;
                    showColorDialog$lambda$64 = TextAttrFragment.showColorDialog$lambda$64(TextAttrFragment.this);
                    return showColorDialog$lambda$64;
                }
            });
            CustomImageView.Companion companion6 = CustomImageView.INSTANCE;
            RelativeLayout relativeLayout13 = this.rlGradient04;
            if (relativeLayout13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient04");
                relativeLayout13 = null;
            }
            companion6.attachViewOnTouchListener(relativeLayout13);
        }
        Dialog dialog9 = this.colorDialog;
        if (dialog9 != null) {
            dialog9.show();
        }
        if (isTextColor) {
            ImageView imageView5 = this.ivClearBgColor;
            if (imageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ivClearBgColor");
                imageView5 = null;
            }
            UtilsExtensionKt.hide(imageView5);
            ConstraintLayout constraintLayout2 = this.cl_text_gradient;
            if (constraintLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_text_gradient");
                constraintLayout2 = null;
            }
            UtilsExtensionKt.show(constraintLayout2);
            EventText value = getTextViewModel().getTextChangedLiveData().getValue();
            Integer valueOf = value != null ? Integer.valueOf(value.getTextColor()) : null;
            if (valueOf != null && valueOf.intValue() == 2) {
                RelativeLayout relativeLayout14 = this.rlGradient01;
                if (relativeLayout14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rlGradient01");
                } else {
                    relativeLayout = relativeLayout14;
                }
                showGradientSelected(relativeLayout, true);
                return;
            }
            if (valueOf != null && valueOf.intValue() == 3) {
                RelativeLayout relativeLayout15 = this.rlGradient02;
                if (relativeLayout15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rlGradient02");
                } else {
                    relativeLayout = relativeLayout15;
                }
                showGradientSelected(relativeLayout, true);
                return;
            }
            if (valueOf != null && valueOf.intValue() == 4) {
                RelativeLayout relativeLayout16 = this.rlGradient03;
                if (relativeLayout16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rlGradient03");
                } else {
                    relativeLayout = relativeLayout16;
                }
                showGradientSelected(relativeLayout, true);
                return;
            }
            if (valueOf != null && valueOf.intValue() == 5) {
                RelativeLayout relativeLayout17 = this.rlGradient04;
                if (relativeLayout17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rlGradient04");
                } else {
                    relativeLayout = relativeLayout17;
                }
                showGradientSelected(relativeLayout, true);
                return;
            }
            showGradientSelected$default(this, null, false, 3, null);
            return;
        }
        if (isTextColor) {
            throw new NoWhenBranchMatchedException();
        }
        ImageView imageView6 = this.ivClearBgColor;
        if (imageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivClearBgColor");
            imageView6 = null;
        }
        UtilsExtensionKt.show(imageView6);
        ConstraintLayout constraintLayout3 = this.cl_text_gradient;
        if (constraintLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_text_gradient");
            constraintLayout3 = null;
        }
        UtilsExtensionKt.invisible(constraintLayout3);
        CustomImageView customImageView = this.iv_show_text_bgcolor;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_show_text_bgcolor");
            customImageView = null;
        }
        customImageView.setImageDrawable(null);
        CustomImageView customImageView2 = this.iv_show_text_bgcolor;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_show_text_bgcolor");
            customImageView2 = null;
        }
        customImageView2.setBackground(null);
        EventText value2 = getTextViewModel().getTextChangedLiveData().getValue();
        Integer valueOf2 = value2 != null ? Integer.valueOf(value2.getTextBgColor()) : null;
        if (valueOf2 != null && valueOf2.intValue() == -16777216) {
            CustomImageView customImageView3 = this.iv_show_text_bgcolor;
            if (customImageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_show_text_bgcolor");
                customImageView3 = null;
            }
            customImageView3.setImageDrawable(this.mActivity.getDrawable(R.mipmap.bgcolor_forbidden));
            showGradientSelected$default(this, null, false, 3, null);
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == 2) {
            RelativeLayout relativeLayout18 = this.rlGradient01;
            if (relativeLayout18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient01");
            } else {
                relativeLayout = relativeLayout18;
            }
            showGradientSelected(relativeLayout, true);
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == 3) {
            RelativeLayout relativeLayout19 = this.rlGradient02;
            if (relativeLayout19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient02");
            } else {
                relativeLayout = relativeLayout19;
            }
            showGradientSelected(relativeLayout, true);
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == 4) {
            RelativeLayout relativeLayout20 = this.rlGradient03;
            if (relativeLayout20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient03");
            } else {
                relativeLayout = relativeLayout20;
            }
            showGradientSelected(relativeLayout, true);
            return;
        }
        if (valueOf2 != null && valueOf2.intValue() == 5) {
            RelativeLayout relativeLayout21 = this.rlGradient04;
            if (relativeLayout21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient04");
            } else {
                relativeLayout = relativeLayout21;
            }
            showGradientSelected(relativeLayout, true);
            return;
        }
        CustomImageView customImageView4 = this.iv_show_text_bgcolor;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_show_text_bgcolor");
            customImageView4 = null;
        }
        EventText value3 = getTextViewModel().getTextChangedLiveData().getValue();
        customImageView4.setImageDrawable(value3 != null ? new ColorDrawable(value3.getTextBgColor()) : null);
        RelativeLayout relativeLayout22 = this.rlGradient01;
        if (relativeLayout22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlGradient01");
        } else {
            relativeLayout = relativeLayout22;
        }
        showGradientSelected(relativeLayout, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showColorDialog$lambda$55(TextAttrFragment textAttrFragment) {
        Dialog dialog = textAttrFragment.colorDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showColorDialog$lambda$56(Ref.BooleanRef booleanRef, TextAttrFragment textAttrFragment) {
        LogUtils.file("TextActivity TextAttrFragment   ivClearBgColor?.setOnClickFilterListener");
        booleanRef.element = true;
        ImageView imageView = textAttrFragment.ivClearBgColor;
        EventText eventText = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivClearBgColor");
            imageView = null;
        }
        imageView.setSelected(booleanRef.element);
        EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText2;
        if (eventText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText2 = null;
        }
        eventText2.setTextBgColor(ViewCompat.MEASURED_STATE_MASK);
        EventText eventText3 = textAttrFragment.eventText;
        if (eventText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
            eventText3 = null;
        }
        eventText3.setChangeMode(TextAttrEnum.TextBgColor);
        BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
        EventText eventText4 = textAttrFragment.eventText;
        if (eventText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventText");
        } else {
            eventText = eventText4;
        }
        textChangedLiveData.setValue(eventText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showColorDialog$lambda$58(TextAttrFragment textAttrFragment) {
        LogUtils.file("TextActivity TextAttrFragment    rlGradient01.setOnClickFilterListener");
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        RelativeLayout relativeLayout = null;
        if (textAttrFragment.isTextColorMode) {
            if (eventText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            }
            eventText.setTextColor(2);
            EventText eventText2 = textAttrFragment.eventText;
            if (eventText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText2 = null;
            }
            for (TextEmojiVO textEmojiVO : eventText2.getTextEmojiVO()) {
                EventText eventText3 = textAttrFragment.eventText;
                if (eventText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText3 = null;
                }
                textEmojiVO.setTextColor(eventText3.getTextColor());
            }
            EventText eventText4 = textAttrFragment.eventText;
            if (eventText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText4 = null;
            }
            eventText4.setChangeMode(TextAttrEnum.TextColor);
            BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
            EventText eventText5 = textAttrFragment.eventText;
            if (eventText5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText5 = null;
            }
            textChangedLiveData.setValue(eventText5);
            CustomImageView customImageView = textAttrFragment.iv_show_textcolor;
            if (customImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_show_textcolor");
                customImageView = null;
            }
            customImageView.setImageDrawable(textAttrFragment.mActivity.getDrawable(R.mipmap.color_view_template_1));
        } else {
            if (eventText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            }
            eventText.setTextBgColor(2);
            EventText eventText6 = textAttrFragment.eventText;
            if (eventText6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText6 = null;
            }
            eventText6.setChangeMode(TextAttrEnum.TextBgColor);
            BusMutableLiveData<EventText> textChangedLiveData2 = textAttrFragment.getTextViewModel().getTextChangedLiveData();
            EventText eventText7 = textAttrFragment.eventText;
            if (eventText7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText7 = null;
            }
            textChangedLiveData2.setValue(eventText7);
        }
        RelativeLayout relativeLayout2 = textAttrFragment.rlGradient01;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlGradient01");
        } else {
            relativeLayout = relativeLayout2;
        }
        textAttrFragment.showGradientSelected(relativeLayout, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showColorDialog$lambda$60(TextAttrFragment textAttrFragment) {
        LogUtils.file("TextActivity TextAttrFragment    rlGradient02.setOnClickFilterListener");
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        RelativeLayout relativeLayout = null;
        if (textAttrFragment.isTextColorMode) {
            if (eventText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            }
            eventText.setTextColor(3);
            EventText eventText2 = textAttrFragment.eventText;
            if (eventText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText2 = null;
            }
            for (TextEmojiVO textEmojiVO : eventText2.getTextEmojiVO()) {
                EventText eventText3 = textAttrFragment.eventText;
                if (eventText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText3 = null;
                }
                textEmojiVO.setTextColor(eventText3.getTextColor());
            }
            EventText eventText4 = textAttrFragment.eventText;
            if (eventText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText4 = null;
            }
            eventText4.setChangeMode(TextAttrEnum.TextColor);
            BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
            EventText eventText5 = textAttrFragment.eventText;
            if (eventText5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText5 = null;
            }
            textChangedLiveData.setValue(eventText5);
            CustomImageView customImageView = textAttrFragment.iv_show_textcolor;
            if (customImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_show_textcolor");
                customImageView = null;
            }
            customImageView.setImageDrawable(textAttrFragment.mActivity.getDrawable(R.mipmap.color_view_template_2));
        } else {
            if (eventText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            }
            eventText.setTextBgColor(3);
            EventText eventText6 = textAttrFragment.eventText;
            if (eventText6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText6 = null;
            }
            eventText6.setChangeMode(TextAttrEnum.TextBgColor);
            BusMutableLiveData<EventText> textChangedLiveData2 = textAttrFragment.getTextViewModel().getTextChangedLiveData();
            EventText eventText7 = textAttrFragment.eventText;
            if (eventText7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText7 = null;
            }
            textChangedLiveData2.setValue(eventText7);
        }
        RelativeLayout relativeLayout2 = textAttrFragment.rlGradient02;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlGradient02");
        } else {
            relativeLayout = relativeLayout2;
        }
        textAttrFragment.showGradientSelected(relativeLayout, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showColorDialog$lambda$62(TextAttrFragment textAttrFragment) {
        LogUtils.file("TextActivity TextAttrFragment    rlGradient03.setOnClickFilterListener");
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        RelativeLayout relativeLayout = null;
        if (textAttrFragment.isTextColorMode) {
            if (eventText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            }
            eventText.setTextColor(4);
            EventText eventText2 = textAttrFragment.eventText;
            if (eventText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText2 = null;
            }
            for (TextEmojiVO textEmojiVO : eventText2.getTextEmojiVO()) {
                EventText eventText3 = textAttrFragment.eventText;
                if (eventText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText3 = null;
                }
                textEmojiVO.setTextColor(eventText3.getTextColor());
            }
            EventText eventText4 = textAttrFragment.eventText;
            if (eventText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText4 = null;
            }
            eventText4.setChangeMode(TextAttrEnum.TextColor);
            BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
            EventText eventText5 = textAttrFragment.eventText;
            if (eventText5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText5 = null;
            }
            textChangedLiveData.setValue(eventText5);
            CustomImageView customImageView = textAttrFragment.iv_show_textcolor;
            if (customImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_show_textcolor");
                customImageView = null;
            }
            customImageView.setImageDrawable(textAttrFragment.mActivity.getDrawable(R.mipmap.color_view_template_3));
        } else {
            if (eventText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            }
            eventText.setTextBgColor(4);
            EventText eventText6 = textAttrFragment.eventText;
            if (eventText6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText6 = null;
            }
            eventText6.setChangeMode(TextAttrEnum.TextBgColor);
            BusMutableLiveData<EventText> textChangedLiveData2 = textAttrFragment.getTextViewModel().getTextChangedLiveData();
            EventText eventText7 = textAttrFragment.eventText;
            if (eventText7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText7 = null;
            }
            textChangedLiveData2.setValue(eventText7);
        }
        RelativeLayout relativeLayout2 = textAttrFragment.rlGradient03;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlGradient03");
        } else {
            relativeLayout = relativeLayout2;
        }
        textAttrFragment.showGradientSelected(relativeLayout, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showColorDialog$lambda$64(TextAttrFragment textAttrFragment) {
        LogUtils.file("TextActivity TextAttrFragment    rlGradient04.setOnClickFilterListener");
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        textAttrFragment.eventText = eventText;
        RelativeLayout relativeLayout = null;
        if (textAttrFragment.isTextColorMode) {
            if (eventText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            }
            eventText.setTextColor(5);
            EventText eventText2 = textAttrFragment.eventText;
            if (eventText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText2 = null;
            }
            for (TextEmojiVO textEmojiVO : eventText2.getTextEmojiVO()) {
                EventText eventText3 = textAttrFragment.eventText;
                if (eventText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventText");
                    eventText3 = null;
                }
                textEmojiVO.setTextColor(eventText3.getTextColor());
            }
            EventText eventText4 = textAttrFragment.eventText;
            if (eventText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText4 = null;
            }
            eventText4.setChangeMode(TextAttrEnum.TextColor);
            BusMutableLiveData<EventText> textChangedLiveData = textAttrFragment.getTextViewModel().getTextChangedLiveData();
            EventText eventText5 = textAttrFragment.eventText;
            if (eventText5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText5 = null;
            }
            textChangedLiveData.setValue(eventText5);
            CustomImageView customImageView = textAttrFragment.iv_show_textcolor;
            if (customImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_show_textcolor");
                customImageView = null;
            }
            customImageView.setImageDrawable(textAttrFragment.mActivity.getDrawable(R.mipmap.color_view_template_4));
        } else {
            if (eventText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText = null;
            }
            eventText.setTextBgColor(5);
            EventText eventText6 = textAttrFragment.eventText;
            if (eventText6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText6 = null;
            }
            eventText6.setChangeMode(TextAttrEnum.TextBgColor);
            BusMutableLiveData<EventText> textChangedLiveData2 = textAttrFragment.getTextViewModel().getTextChangedLiveData();
            EventText eventText7 = textAttrFragment.eventText;
            if (eventText7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventText");
                eventText7 = null;
            }
            textChangedLiveData2.setValue(eventText7);
        }
        RelativeLayout relativeLayout2 = textAttrFragment.rlGradient04;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlGradient04");
        } else {
            relativeLayout = relativeLayout2;
        }
        textAttrFragment.showGradientSelected(relativeLayout, true);
        return Unit.INSTANCE;
    }

    static /* synthetic */ void showGradientSelected$default(TextAttrFragment textAttrFragment, View view, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            RelativeLayout relativeLayout = textAttrFragment.rlGradient01;
            if (relativeLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rlGradient01");
                relativeLayout = null;
            }
            view = relativeLayout;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        textAttrFragment.showGradientSelected(view, z);
    }

    private final void showGradientSelected(View sel, boolean isSel) {
        View[] viewArr = new View[4];
        RelativeLayout relativeLayout = this.rlGradient01;
        RelativeLayout relativeLayout2 = null;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlGradient01");
            relativeLayout = null;
        }
        viewArr[0] = relativeLayout;
        RelativeLayout relativeLayout3 = this.rlGradient02;
        if (relativeLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlGradient02");
            relativeLayout3 = null;
        }
        viewArr[1] = relativeLayout3;
        RelativeLayout relativeLayout4 = this.rlGradient03;
        if (relativeLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlGradient03");
            relativeLayout4 = null;
        }
        viewArr[2] = relativeLayout4;
        RelativeLayout relativeLayout5 = this.rlGradient04;
        if (relativeLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlGradient04");
        } else {
            relativeLayout2 = relativeLayout5;
        }
        viewArr[3] = relativeLayout2;
        for (int i = 0; i < 4; i++) {
            viewArr[i].setSelected(false);
        }
        if (!isSel || sel == null) {
            return;
        }
        sel.setSelected(true);
    }

    private final void initBorderData2() {
        this.mBorderData2 = ResourceUtils.getResIds(this.mActivity, R.array.subzone_border_2_1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
