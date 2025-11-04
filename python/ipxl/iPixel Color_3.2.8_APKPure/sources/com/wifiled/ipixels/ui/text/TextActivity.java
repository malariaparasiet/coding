package com.wifiled.ipixels.ui.text;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelLazy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wifiled.baselib.app.cache.ACache;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.utils.KeyBordUtil;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.baselib.widget.NoNewLineEditText;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.UtilsExtensionKt$appViewModels$3;
import com.wifiled.ipixels.UtilsExtensionKt$appViewModels$factoryPromise$1;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.SendDataCallback;
import com.wifiled.ipixels.core.text.CharacterUtilsKt;
import com.wifiled.ipixels.core.text.process.ArabicTextProcess;
import com.wifiled.ipixels.core.text.process.CommonTextProcess;
import com.wifiled.ipixels.core.text.process.IndianTextProcess;
import com.wifiled.ipixels.core.text.process.NewArabicTextProcess;
import com.wifiled.ipixels.core.text.process.NewCommonTextProcess;
import com.wifiled.ipixels.core.text.process.NewIndianTextProcess;
import com.wifiled.ipixels.core.text.process.NewRussianTextProcess;
import com.wifiled.ipixels.core.text.process.NewTamilTextProcess;
import com.wifiled.ipixels.core.text.process.NewThaiTextProcess;
import com.wifiled.ipixels.core.text.process.NewVietnameseTextProcess;
import com.wifiled.ipixels.core.text.process.RussianTextProcess;
import com.wifiled.ipixels.core.text.process.TamilTextProcess;
import com.wifiled.ipixels.core.text.process.TextData;
import com.wifiled.ipixels.core.text.process.ThaiTextProcess;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.UpDataState;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.diy.DiyImageFun;
import com.wifiled.ipixels.ui.subzone.SubzoneData;
import com.wifiled.ipixels.ui.text.TextAttrFragment;
import com.wifiled.ipixels.ui.text.TextEffectFragment;
import com.wifiled.ipixels.ui.text.vo.CheckContent;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.ui.text.vo.TextHistoryVO;
import com.wifiled.ipixels.utils.ArabicUtils;
import com.wifiled.ipixels.utils.ResourceUtils;
import com.wifiled.ipixels.utils.ThreadPoolUtil;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.view.customview.CustomImageViewPlus;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: TextActivity.kt */
@Metadata(d1 = {"\u0000\u0092\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u001c\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010[\u001a\u00020\\H\u0014J\b\u0010]\u001a\u00020\\H\u0014J\b\u0010^\u001a\u00020\\H\u0014J\b\u0010_\u001a\u00020\\H\u0014J\b\u0010`\u001a\u00020\\H\u0014J\b\u0010a\u001a\u00020\\H\u0014J\b\u0010b\u001a\u00020\\H\u0002J\b\u0010c\u001a\u00020\\H\u0002J\u0010\u0010d\u001a\u00020\\2\u0006\u0010e\u001a\u00020fH\u0007J \u0010g\u001a\u00020\\2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00162\b\b\u0002\u0010i\u001a\u00020&H\u0002J\u0010\u0010j\u001a\u00020\\2\u0006\u0010k\u001a\u00020lH\u0007J\u0010\u0010m\u001a\u00020\\2\u0006\u0010n\u001a\u00020oH\u0007J\b\u0010p\u001a\u00020\\H\u0002J\b\u0010q\u001a\u00020&H\u0002J\u001a\u0010r\u001a\u00020&2\u0006\u0010s\u001a\u00020\u00072\b\u0010n\u001a\u0004\u0018\u00010tH\u0016J\b\u0010u\u001a\u00020\\H\u0002J\b\u0010w\u001a\u00020\\H\u0002J\b\u0010x\u001a\u00020\u0007H\u0002J\b\u0010y\u001a\u00020\\H\u0002J\b\u0010|\u001a\u00020\\H\u0002J\u001a\u0010}\u001a\u00020\\2\u0006\u0010~\u001a\u00020&2\b\b\u0002\u0010\u007f\u001a\u00020&H\u0002J\t\u0010\u0080\u0001\u001a\u00020\\H\u0016J\u0019\u0010\u0081\u0001\u001a\u00020\\2\u0006\u0010~\u001a\u00020&2\u0006\u0010\u007f\u001a\u00020&H\u0002J\n\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0002J,\u0010\u008d\u0001\u001a\u00020\\2\u0006\u0010~\u001a\u00020&2\u0006\u0010\u007f\u001a\u00020&2\b\u0010\u008e\u0001\u001a\u00030\u008c\u00012\u0007\u0010\u008f\u0001\u001a\u000206H\u0002J\u0012\u0010\u0090\u0001\u001a\u00020\\2\u0007\u0010\u0091\u0001\u001a\u00020\u0007H\u0002J\u0012\u0010\u0092\u0001\u001a\u00020\\2\u0007\u0010\u0093\u0001\u001a\u00020\u0007H\u0002J\u0012\u0010\u0094\u0001\u001a\u00020\\2\u0007\u0010\u0095\u0001\u001a\u00020\u0007H\u0002J\t\u0010\u009c\u0001\u001a\u00020\\H\u0015J\u001b\u0010¡\u0001\u001a\u00020\\2\u0007\u0010¢\u0001\u001a\u00020\u00072\u0007\u0010£\u0001\u001a\u00020\u0007H\u0016J\u0012\u0010¤\u0001\u001a\u00020\\2\u0007\u0010\u0093\u0001\u001a\u000206H\u0002J\t\u0010¥\u0001\u001a\u00020\\H\u0002J\t\u0010¦\u0001\u001a\u00020\\H\u0002J0\u0010§\u0001\u001a\u00020\\2\n\u0010¨\u0001\u001a\u0005\u0018\u00010©\u00012\u0007\u0010ª\u0001\u001a\u00020\u00072\u0007\u0010«\u0001\u001a\u00020\u00072\u0007\u0010¬\u0001\u001a\u00020\u0007H\u0016J0\u0010\u00ad\u0001\u001a\u00020\\2\n\u0010¨\u0001\u001a\u0005\u0018\u00010©\u00012\u0007\u0010ª\u0001\u001a\u00020\u00072\u0007\u0010®\u0001\u001a\u00020\u00072\u0007\u0010«\u0001\u001a\u00020\u0007H\u0016J\u0015\u0010°\u0001\u001a\u00020\\2\n\u0010¨\u0001\u001a\u0005\u0018\u00010±\u0001H\u0016R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082.¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020>X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020>X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020>X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020DX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020JX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020LX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020NX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020PX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020PX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020>X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020TX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020TX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020TX\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010W\u001a\u00020X¢\u0006\b\n\u0000\u001a\u0004\bY\u0010ZR\u000e\u0010v\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010z\u001a\u0004\u0018\u00010{X\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010\u0084\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0086\u0001\u0012\u0004\u0012\u00020\\0\u0085\u0001¢\u0006\u0003\b\u0087\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0088\u0001\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0089\u0001\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u008a\u0001\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\u0096\u0001\u001a\u00020&X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001\"\u0006\b\u0098\u0001\u0010\u0099\u0001R\u001f\u0010\u009a\u0001\u001a\u00020&X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009a\u0001\u0010\u0097\u0001\"\u0006\b\u009b\u0001\u0010\u0099\u0001R\u000f\u0010\u009d\u0001\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u009e\u0001\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0001\u0010 \"\u0005\b \u0001\u0010\"R\u000f\u0010¯\u0001\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006²\u0001"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "Lcom/wifiled/ipixels/ui/text/KeyboardHeightObserver;", "Landroid/text/TextWatcher;", "<init>", "()V", "layoutId", "", "textViewModel", "Lcom/wifiled/ipixels/ui/text/TextViewModel;", "getTextViewModel", "()Lcom/wifiled/ipixels/ui/text/TextViewModel;", "textViewModel$delegate", "Lkotlin/Lazy;", "textBgColor", "textColor", "textHorizontalAlign", "textVerticalAlign", "textEffect", "textTypeface", "textDirction", "emojiPreviews", "", "emojiAdapter", "Lcom/wifiled/ipixels/ui/text/TextAttrEmojiAdapter;", "mKeyboardHeightProvider", "Lcom/wifiled/ipixels/ui/text/KeyboardHeightProvider;", "mMaxLenth", "mTexts", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "mValHasCounted", "getMValHasCounted", "()I", "setMValHasCounted", "(I)V", "mPageIndex", "mPrevPageIndex", "mFirstLoad", "", "mIsTextSizeChange", "mActionFromRecord", "mCmpAboveView", "mCurFragment", "Lcom/wifiled/baselib/base/BaseFragment;", "mTextAttrFragment", "Lcom/wifiled/ipixels/ui/text/TextAttrFragment;", "mTextEffectFragment", "Lcom/wifiled/ipixels/ui/text/TextEffectFragment;", "mTextRecordFragment", "Lcom/wifiled/ipixels/ui/text/TextRecordFragment;", "isPriview", "progressDialog", "Lcom/wifiled/ipixels/ui/text/TextChangeTipDialog;", "mEventTextSendData", "Lcom/wifiled/ipixels/event/EventText;", "mFromType", "mTemplateType", "mChooseTvIndex", "mSendDataCallback", "Lcom/wifiled/ipixels/core/SendDataCallback;", "mIsBold", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "iv_middle", "tv_title", "Landroid/widget/TextView;", "viewpager", "Landroidx/viewpager2/widget/ViewPager2;", "tv_menu_rec", "tv_menu_effect", "tv_menu_attr", "tv_fontlimit_in_attr", "test_edit", "Lcom/wifiled/baselib/widget/NoNewLineEditText;", "rl_input_in_attr", "Landroidx/recyclerview/widget/RecyclerView;", "rl_hide_keyboard", "Landroid/widget/RelativeLayout;", "iv_text_send", "Landroid/widget/ImageView;", "iv_text_mode", "iv_emoji_in_attr", "cl_edit_outframe_in_attr", "Landroidx/constraintlayout/widget/ConstraintLayout;", "cl_edit_kb_in_attr", "cl_edit_in_attr", "imageGetter", "Landroid/text/Html$ImageGetter;", "getImageGetter", "()Landroid/text/Html$ImageGetter;", "initView", "", "bindData", "onStart", "onResume", "onStop", "onDestroy", "reloadHistoryData", "initLedView", "showReordSelectedItem", "textHistoryVO", "Lcom/wifiled/ipixels/ui/text/vo/TextHistoryVO;", "showTextByLedType", "textEmojiVOs", "isAppend", "onUpDataState", "instance", "Lcom/wifiled/ipixels/ui/UpDataState;", "onEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "initToolbar", "comparedTextData", "onKeyDown", "keyCode", "Landroid/view/KeyEvent;", "showTextChangeTipDialog", "bIsFirstInit", "initEvent", "getTextTotalSize", "showLimitTextInfo", "disposable", "Lio/reactivex/disposables/Disposable;", "saveTextAction", "sendTextAction", "isDown", "isFinish", "onBackPressed", "sendText", "getTextProcessData", "Lcom/wifiled/ipixels/core/text/process/TextData;", "callback", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "mSdIsFinish", "mSdIsDown", "mSdEventText", "mSdTextBa", "", "sendByteArray", "textBa", "eventText", "changeTypeFace", "typeFace", "changeTextAlign", "it", "changeTextSize", "size", "isShow", "()Z", "setShow", "(Z)V", "isShowEmoj", "setShowEmoj", "bindListener", "windowHeight", "kbHeight", "getKbHeight", "setKbHeight", "onKeyboardHeightChanged", "height", "orientation", "showTextColor", "initEmojs", "initViewpager", "beforeTextChanged", "s", "", "start", "count", "after", "onTextChanged", "before", "isInternalEdit", "afterTextChanged", "Landroid/text/Editable;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextActivity extends BaseActivity implements KeyboardHeightObserver, TextWatcher {
    private ConstraintLayout cl_edit_in_attr;
    private ConstraintLayout cl_edit_kb_in_attr;
    private ConstraintLayout cl_edit_outframe_in_attr;
    private Disposable disposable;
    private TextAttrEmojiAdapter emojiAdapter;
    private boolean isInternalEdit;
    private boolean isPriview;
    private boolean isShowEmoj;
    private CustomImageView iv_back;
    private CustomImageView iv_emoji_in_attr;
    private CustomImageView iv_middle;
    private CustomImageView iv_right;
    private ImageView iv_text_mode;
    private ImageView iv_text_send;
    private int kbHeight;
    private boolean mActionFromRecord;
    private int mChooseTvIndex;
    private boolean mCmpAboveView;
    private BaseFragment mCurFragment;
    private EventText mEventTextSendData;
    private boolean mFirstLoad;
    private int mFromType;
    private boolean mIsBold;
    private boolean mIsTextSizeChange;
    private KeyboardHeightProvider mKeyboardHeightProvider;
    private EventText mSdEventText;
    private boolean mSdIsDown;
    private boolean mSdIsFinish;
    private byte[] mSdTextBa;
    private SendDataCallback mSendDataCallback;
    private int mTemplateType;
    private TextAttrFragment mTextAttrFragment;
    private TextEffectFragment mTextEffectFragment;
    private TextRecordFragment mTextRecordFragment;
    private int mValHasCounted;
    private TextChangeTipDialog progressDialog;
    private RelativeLayout rl_hide_keyboard;
    private RecyclerView rl_input_in_attr;
    private NoNewLineEditText test_edit;
    private int textDirction;
    private int textEffect;
    private int textTypeface;
    private TextView tv_fontlimit_in_attr;
    private TextView tv_menu_attr;
    private TextView tv_menu_effect;
    private TextView tv_menu_rec;
    private TextView tv_title;
    private ViewPager2 viewpager;
    private int windowHeight;

    /* renamed from: textViewModel$delegate, reason: from kotlin metadata */
    private final Lazy textViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(TextViewModel.class), UtilsExtensionKt$appViewModels$3.INSTANCE, new UtilsExtensionKt$appViewModels$factoryPromise$1(this), null, 8, null);
    private int textBgColor = ViewCompat.MEASURED_STATE_MASK;
    private int textColor = -1;
    private int textHorizontalAlign = 1;
    private int textVerticalAlign = 1;
    private List<Integer> emojiPreviews = new ArrayList();
    private int mMaxLenth = 500;
    private List<TextEmojiVO> mTexts = new ArrayList();
    private int mPageIndex = -1;
    private int mPrevPageIndex = -1;
    private final Html.ImageGetter imageGetter = new Html.ImageGetter() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda39
        @Override // android.text.Html.ImageGetter
        public final Drawable getDrawable(String str) {
            Drawable imageGetter$lambda$0;
            imageGetter$lambda$0 = TextActivity.imageGetter$lambda$0(TextActivity.this, str);
            return imageGetter$lambda$0;
        }
    };
    private boolean bIsFirstInit = true;
    private Function1<? super SendCore.CallbackBuilder, Unit> callback = new Function1() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda40
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit callback$lambda$45;
            callback$lambda$45 = TextActivity.callback$lambda$45(TextActivity.this, (SendCore.CallbackBuilder) obj);
            return callback$lambda$45;
        }
    };
    private boolean isShow = true;

    /* compiled from: TextActivity.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextAttrEnum.values().length];
            try {
                iArr[TextAttrEnum.TextSize.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextAttrEnum.TextChange.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TextAttrEnum.TextFontType.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TextAttrEnum.TextHorizontalAlign.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TextAttrEnum.TextVerticalAlign.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TextAttrEnum.TextColor.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[TextAttrEnum.TextBgColor.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[TextAttrEnum.TextEffect.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[TextAttrEnum.TextDirction.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[TextAttrEnum.TextBold.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[TextAttrEnum.TextAlpha.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[TextAttrEnum.TextSpeed.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_text;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    private final TextViewModel getTextViewModel() {
        return (TextViewModel) this.textViewModel.getValue();
    }

    public final int getMValHasCounted() {
        return this.mValHasCounted;
    }

    public final void setMValHasCounted(int i) {
        this.mValHasCounted = i;
    }

    public final Html.ImageGetter getImageGetter() {
        return this.imageGetter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Drawable imageGetter$lambda$0(TextActivity textActivity, String str) {
        Intrinsics.checkNotNull(str);
        Drawable drawable = textActivity.getResources().getDrawable(Integer.parseInt(str));
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0254, code lost:
    
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) "hi", false, 2, (java.lang.Object) null) != false) goto L45;
     */
    @Override // com.wifiled.baselib.base.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void initView() {
        /*
            Method dump skipped, instructions count: 714
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.text.TextActivity.initView():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initView$lambda$2(final TextActivity textActivity, TextView textView, int i, KeyEvent keyEvent) {
        Log.v("ruis", "actionId------" + i);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit initView$lambda$2$lambda$1;
                initView$lambda$2$lambda$1 = TextActivity.initView$lambda$2$lambda$1(TextActivity.this);
                return initView$lambda$2$lambda$1;
            }
        });
        RelativeLayout relativeLayout = textActivity.rl_hide_keyboard;
        ConstraintLayout constraintLayout = null;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_hide_keyboard");
            relativeLayout = null;
        }
        UtilsExtensionKt.hide(relativeLayout);
        if (!textActivity.isShowEmoj) {
            return true;
        }
        textActivity.isShowEmoj = false;
        CustomImageView customImageView = textActivity.iv_emoji_in_attr;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_emoji_in_attr");
            customImageView = null;
        }
        customImageView.setSelected(textActivity.isShowEmoj);
        ConstraintLayout constraintLayout2 = textActivity.cl_edit_outframe_in_attr;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_outframe_in_attr");
        } else {
            constraintLayout = constraintLayout2;
        }
        UtilsExtensionKt.hide(constraintLayout);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initView$lambda$2$lambda$1(TextActivity textActivity) {
        NoNewLineEditText noNewLineEditText = textActivity.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        KeyBordUtil.hideSoftKeyboard(noNewLineEditText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3(TextActivity textActivity) {
        KeyboardHeightProvider keyboardHeightProvider = textActivity.mKeyboardHeightProvider;
        if (keyboardHeightProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mKeyboardHeightProvider");
            keyboardHeightProvider = null;
        }
        keyboardHeightProvider.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4(TextActivity textActivity, View view, View view2) {
        ImageView imageView = textActivity.iv_text_mode;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_text_mode");
            imageView = null;
        }
        textActivity.startActivity(new Intent(textActivity, (Class<?>) CreativeTextActivity.class), ActivityOptions.makeScaleUpAnimation(imageView, 0, 0, view.getWidth(), view.getHeight()).toBundle());
        textActivity.finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mPageIndex = this.mPrevPageIndex;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        KeyboardHeightProvider keyboardHeightProvider = this.mKeyboardHeightProvider;
        if (keyboardHeightProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mKeyboardHeightProvider");
            keyboardHeightProvider = null;
        }
        keyboardHeightProvider.setKeyboardHeightObserver(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mCmpAboveView = false;
        this.mPageIndex = -1;
        KeyboardHeightProvider keyboardHeightProvider = this.mKeyboardHeightProvider;
        if (keyboardHeightProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mKeyboardHeightProvider");
            keyboardHeightProvider = null;
        }
        keyboardHeightProvider.setKeyboardHeightObserver(null);
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.file("TextActivity onDestroy");
        reloadHistoryData();
        TextEmojiBuilder.INSTANCE.restore();
        KeyboardHeightProvider keyboardHeightProvider = this.mKeyboardHeightProvider;
        NoNewLineEditText noNewLineEditText = null;
        if (keyboardHeightProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mKeyboardHeightProvider");
            keyboardHeightProvider = null;
        }
        keyboardHeightProvider.close();
        EventBus.getDefault().unregister(this);
        this.mTextAttrFragment = null;
        this.mTextEffectFragment = null;
        this.mTextRecordFragment = null;
        this.mSendDataCallback = null;
        this.callback = new Function1() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onDestroy$lambda$5;
                onDestroy$lambda$5 = TextActivity.onDestroy$lambda$5((SendCore.CallbackBuilder) obj);
                return onDestroy$lambda$5;
            }
        };
        NoNewLineEditText noNewLineEditText2 = this.test_edit;
        if (noNewLineEditText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
        } else {
            noNewLineEditText = noNewLineEditText2;
        }
        noNewLineEditText.removeTextChangedListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onDestroy$lambda$5(SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        return Unit.INSTANCE;
    }

    private final void reloadHistoryData() {
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda49
            @Override // java.lang.Runnable
            public final void run() {
                TextActivity.reloadHistoryData$lambda$6(TextActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reloadHistoryData$lambda$6(TextActivity textActivity) {
        try {
            if (ACache.get(textActivity).getAsList("text_history", TextHistoryVO.class) != null) {
                CheckContent checkContent = CheckContent.INSTANCE;
                ArrayList<TextHistoryVO> asList = ACache.get(textActivity).getAsList("text_history", TextHistoryVO.class);
                Intrinsics.checkNotNullExpressionValue(asList, "getAsList(...)");
                checkContent.setCacheTextHistoryVOs(asList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void initLedView() {
        final EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        UtilsExtensionKt.uiDelay(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit initLedView$lambda$7;
                initLedView$lambda$7 = TextActivity.initLedView$lambda$7(EventText.this, this);
                return initLedView$lambda$7;
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initLedView$lambda$7(EventText eventText, TextActivity textActivity) {
        eventText.setChangeMode(TextAttrEnum.TextEffect);
        textActivity.getTextViewModel().getTextChangedLiveData().setValue(eventText);
        return Unit.INSTANCE;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void showReordSelectedItem(TextHistoryVO textHistoryVO) {
        Intrinsics.checkNotNullParameter(textHistoryVO, "textHistoryVO");
        if (this.mPageIndex != 2) {
            this.mFirstLoad = false;
            return;
        }
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        TextHistoryVO textHistoryVO2 = new TextHistoryVO(new ArrayList(), new EventText(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null));
        textHistoryVO2.getTextEmojiVOs().addAll(textHistoryVO.getTextEmojiVOs());
        if (!TextEmojiBuilder.INSTANCE.checkListEqual(textHistoryVO.getTextEmojiVOs(), eventText.getTextEmojiVO())) {
            eventText.getTextEmojiVO().clear();
            eventText.getTextEmojiVO().addAll(textHistoryVO2.getTextEmojiVOs());
        }
        this.mActionFromRecord = true;
        LogUtils.i(this.TAG + ">>>[showReordSelectedItem]: " + textHistoryVO2.getTextEmojiVOs() + " \n eventext：" + textHistoryVO2.getEventText());
        int textHorizontalAlign = textHistoryVO2.getEventText().getTextHorizontalAlign();
        this.textHorizontalAlign = textHorizontalAlign;
        changeTextAlign(textHorizontalAlign);
        int textVerticalAlign = textHistoryVO2.getEventText().getTextVerticalAlign();
        this.textVerticalAlign = textVerticalAlign;
        changeTextAlign(textVerticalAlign);
        this.mMaxLenth = TextEmojiBuilder.getCurFontLimit$default(TextEmojiBuilder.INSTANCE, 0, 1, null);
        showLimitTextInfo();
        showTextByLedType$default(this, eventText.getTextEmojiVO(), false, 2, null);
    }

    static /* synthetic */ void showTextByLedType$default(TextActivity textActivity, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        textActivity.showTextByLedType(list, z);
    }

    private final void showTextByLedType(List<TextEmojiVO> textEmojiVOs, boolean isAppend) {
        ArrayList<TextEmojiVO> arrayList = new ArrayList();
        arrayList.addAll(textEmojiVOs);
        this.mTexts.clear();
        this.mTexts.addAll(arrayList);
        NoNewLineEditText noNewLineEditText = this.test_edit;
        TextView textView = null;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        noNewLineEditText.setText("");
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        if (eventText.getTextEmojiVO().isEmpty()) {
            eventText.setTextEmojiVO(textEmojiVOs);
        }
        int i = 0;
        for (TextEmojiVO textEmojiVO : arrayList) {
            int type = textEmojiVO.getType();
            if (type == 0) {
                String text = textEmojiVO.getText();
                for (int i2 = 0; i2 < text.length(); i2++) {
                    char charAt = text.charAt(i2);
                    i = (CharacterUtilsKt.isChinese(charAt) || CharacterUtilsKt.isKoreanCharacter(charAt) || CharacterUtilsKt.isJapaneseCharacter(charAt) || CharacterUtilsKt.isCyrillicCharacter(charAt)) ? i + 2 : i + 1;
                    if (i <= this.mMaxLenth) {
                        NoNewLineEditText noNewLineEditText2 = this.test_edit;
                        if (noNewLineEditText2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
                            noNewLineEditText2 = null;
                        }
                        noNewLineEditText2.append(String.valueOf(charAt));
                    }
                }
            } else if (type == 1 && (i = i + 2) <= this.mMaxLenth) {
                NoNewLineEditText noNewLineEditText3 = this.test_edit;
                if (noNewLineEditText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("test_edit");
                    noNewLineEditText3 = null;
                }
                noNewLineEditText3.append(Html.fromHtml("<img src='" + textEmojiVO.getText() + "'/>", this.imageGetter, null));
            }
        }
        if (this.mIsTextSizeChange && getTextTotalSize() > this.mMaxLenth) {
            this.mIsTextSizeChange = false;
            NoNewLineEditText noNewLineEditText4 = this.test_edit;
            if (noNewLineEditText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("test_edit");
                noNewLineEditText4 = null;
            }
            noNewLineEditText4.setText("");
        }
        int textTotalSize = getTextTotalSize();
        TextView textView2 = this.tv_fontlimit_in_attr;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_fontlimit_in_attr");
        } else {
            textView = textView2;
        }
        textView.setText(textTotalSize + "/" + this.mMaxLenth);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onUpDataState(UpDataState instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() != 1) {
            return;
        }
        ConstraintLayout constraintLayout = this.cl_edit_outframe_in_attr;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_outframe_in_attr");
            constraintLayout = null;
        }
        CustomImageView.INSTANCE.isTouchPointAboveView(constraintLayout, (int) event.getRawX(), (int) event.getRawY());
    }

    private final void initToolbar() {
        TextView textView = this.tv_title;
        CustomImageView customImageView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getString(R.string.title_text));
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            CustomImageView customImageView2 = this.iv_back;
            if (customImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView2 = null;
            }
            customImageView2.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            CustomImageView customImageView3 = this.iv_back;
            if (customImageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView3 = null;
            }
            customImageView3.setBackgroundResource(R.mipmap.icon_back);
        }
        CustomImageView customImageView4 = this.iv_back;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView4 = null;
        }
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.initToolbar$lambda$12(TextActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView5 = this.iv_back;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView5 = null;
        }
        companion.attachViewOnTouchListener(customImageView5);
        CustomImageView customImageView6 = this.iv_middle;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_middle");
            customImageView6 = null;
        }
        customImageView6.setBackgroundResource(R.drawable.delete_default);
        CustomImageView customImageView7 = this.iv_middle;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_middle");
            customImageView7 = null;
        }
        customImageView7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.initToolbar$lambda$14(TextActivity.this, view);
            }
        });
        CustomImageView customImageView8 = this.iv_middle;
        if (customImageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_middle");
            customImageView8 = null;
        }
        UtilsExtensionKt.show(customImageView8);
        CustomImageViewPlus.Companion companion2 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView9 = this.iv_middle;
        if (customImageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_middle");
            customImageView9 = null;
        }
        companion2.attachViewOnTouchListener(customImageView9);
        CustomImageView customImageView10 = this.iv_right;
        if (customImageView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView10 = null;
        }
        customImageView10.setBackgroundResource(R.mipmap.icon_text_save);
        CustomImageView customImageView11 = this.iv_right;
        if (customImageView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView11 = null;
        }
        customImageView11.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.initToolbar$lambda$18(TextActivity.this, view);
            }
        });
        CustomImageViewPlus.Companion companion3 = CustomImageViewPlus.INSTANCE;
        CustomImageView customImageView12 = this.iv_right;
        if (customImageView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView = customImageView12;
        }
        companion3.attachViewOnTouchListener(customImageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolbar$lambda$12(final TextActivity textActivity, View view) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit initToolbar$lambda$12$lambda$11;
                initToolbar$lambda$12$lambda$11 = TextActivity.initToolbar$lambda$12$lambda$11(TextActivity.this);
                return initToolbar$lambda$12$lambda$11;
            }
        });
        if (!textActivity.isPriview) {
            if (textActivity.comparedTextData()) {
                SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
                textActivity.finish();
                return;
            } else if (textActivity.mTexts.size() != 0 && (AppConfig.INSTANCE.getConnectType() != -1 || AppConfig.INSTANCE.getConnectType2() != -1)) {
                textActivity.showTextChangeTipDialog();
                return;
            } else {
                textActivity.finish();
                return;
            }
        }
        textActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initToolbar$lambda$12$lambda$11(TextActivity textActivity) {
        NoNewLineEditText noNewLineEditText = textActivity.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        KeyBordUtil.hideSoftKeyboard(noNewLineEditText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolbar$lambda$14(final TextActivity textActivity, View view) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda43
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit initToolbar$lambda$14$lambda$13;
                initToolbar$lambda$14$lambda$13 = TextActivity.initToolbar$lambda$14$lambda$13(TextActivity.this);
                return initToolbar$lambda$14$lambda$13;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initToolbar$lambda$14$lambda$13(TextActivity textActivity) {
        textActivity.mTexts.clear();
        NoNewLineEditText noNewLineEditText = textActivity.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        noNewLineEditText.setText("");
        textActivity.showLimitTextInfo();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolbar$lambda$18(final TextActivity textActivity, View view) {
        ArrayList arrayList;
        Iterator<T> it = textActivity.mTexts.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (((TextEmojiVO) it.next()).getType() == -1) {
                z = true;
            }
        }
        if (z || textActivity.mTexts.isEmpty()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit initToolbar$lambda$18$lambda$16;
                    initToolbar$lambda$18$lambda$16 = TextActivity.initToolbar$lambda$18$lambda$16(TextActivity.this);
                    return initToolbar$lambda$18$lambda$16;
                }
            });
            return;
        }
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        TextHistoryVO textHistoryVO = new TextHistoryVO(new ArrayList(), new EventText(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null));
        try {
            arrayList = ACache.get(textActivity).getAsList("text_history", TextHistoryVO.class);
        } catch (Exception e) {
            e.printStackTrace();
            arrayList = null;
        }
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        Iterator it2 = arrayList.iterator();
        Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
        while (it2.hasNext()) {
            Object next = it2.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            if (TextEmojiBuilder.INSTANCE.checkListEqual(((TextHistoryVO) next).getEventText().getTextEmojiVO(), eventText.getTextEmojiVO())) {
                it2.remove();
            }
        }
        Gson gson = new Gson();
        Object fromJson = gson.fromJson(gson.toJson(eventText), new TypeToken<EventText>() { // from class: com.wifiled.ipixels.ui.text.TextActivity$initToolbar$3$tEventText$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
        EventText eventText2 = (EventText) fromJson;
        StringBuilder sb = new StringBuilder();
        boolean z2 = false;
        for (TextEmojiVO textEmojiVO : eventText2.getTextEmojiVO()) {
            if (ArabicUtils.isArabic(textEmojiVO.getText())) {
                sb.append(textEmojiVO.getText());
                z2 = true;
            }
        }
        textHistoryVO.setEventText(eventText2);
        if (z2) {
            TextEmojiVO textEmojiVO2 = eventText2.getTextEmojiVO().get(0);
            int type = textEmojiVO2.getType();
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            textHistoryVO.getTextEmojiVOs().add(new TextEmojiVO(type, sb2, textEmojiVO2.getTypeFace(), textEmojiVO2.getTextColor(), textEmojiVO2.getResPosition(), textEmojiVO2.getTextWidth(), textEmojiVO2.getTextHeight(), textEmojiVO2.getIndex()));
        } else {
            textHistoryVO.getTextEmojiVOs().addAll(eventText2.getTextEmojiVO());
        }
        textActivity.getTextViewModel().getTextRecordAddLiveData().setValue(new TextHistoryVO(textHistoryVO.getTextEmojiVOs(), eventText2));
        List<TextEmojiVO> textEmojiVOs = textHistoryVO.getTextEmojiVOs();
        EventText value = textActivity.getTextViewModel().getTextChangedLiveData().getValue();
        Intrinsics.checkNotNull(value);
        arrayList.add(new TextHistoryVO(textEmojiVOs, value));
        ACache.get(textActivity).put("text_history", arrayList);
        ToastUtil.show(textActivity.getString(R.string.text_save_suc));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initToolbar$lambda$18$lambda$16(TextActivity textActivity) {
        textActivity.toast(textActivity.getString(R.string.text_input_null));
        return Unit.INSTANCE;
    }

    private final boolean comparedTextData() {
        if (this.mEventTextSendData != null) {
            EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
            EventText eventText2 = this.mEventTextSendData;
            Intrinsics.checkNotNull(eventText2);
            if (!Intrinsics.areEqual(eventText2.getTextEmojiVO(), eventText.getTextEmojiVO())) {
                return false;
            }
            EventText eventText3 = this.mEventTextSendData;
            Intrinsics.checkNotNull(eventText3);
            if (eventText3.getTextSpeed() == eventText.getTextSpeed()) {
                EventText eventText4 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText4);
                if (eventText4.getChangeMode() != eventText.getChangeMode()) {
                    return false;
                }
                EventText eventText5 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText5);
                if (eventText5.getTextSize() != eventText.getTextSize()) {
                    return false;
                }
                EventText eventText6 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText6);
                if (eventText6.getTextColor() != eventText.getTextColor()) {
                    return false;
                }
                EventText eventText7 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText7);
                if (eventText7.getTextBgColor() != eventText.getTextBgColor()) {
                    return false;
                }
                EventText eventText8 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText8);
                if (eventText8.getTextHorizontalAlign() != eventText.getTextHorizontalAlign()) {
                    return false;
                }
                EventText eventText9 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText9);
                if (eventText9.getTextVerticalAlign() != eventText.getTextVerticalAlign()) {
                    return false;
                }
                EventText eventText10 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText10);
                if (eventText10.getTextTypeFace() != eventText.getTextTypeFace()) {
                    return false;
                }
                EventText eventText11 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText11);
                return eventText11.getTextEffect() == eventText.getTextEffect();
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return false;
        }
        if (!this.isPriview) {
            if (comparedTextData()) {
                SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
                finish();
                return false;
            }
            if (this.mTexts.size() != 0) {
                showTextChangeTipDialog();
                return false;
            }
            finish();
            return false;
        }
        finish();
        return false;
    }

    private final void showTextChangeTipDialog() {
        TextChangeTipDialog textChangeTipDialog = new TextChangeTipDialog();
        this.progressDialog = textChangeTipDialog;
        textChangeTipDialog.setClickLinstenr(new TextActivity$showTextChangeTipDialog$1(this));
        TextChangeTipDialog textChangeTipDialog2 = this.progressDialog;
        if (textChangeTipDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
            textChangeTipDialog2 = null;
        }
        textChangeTipDialog2.show(getSupportFragmentManager(), "textchange");
    }

    private final void initEvent() {
        if (AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 13) {
            TextEmojiBuilder.INSTANCE.getEventText().setTextSize(32);
        }
        if (AppConfig.INSTANCE.getLedType() == 9) {
            TextEmojiBuilder.INSTANCE.getEventText().setTextSize(24);
            int i = this.mTemplateType;
            if (i == 4 || i == 5 || i == 1) {
                TextEmojiBuilder.INSTANCE.getEventText().setTextSize(12);
                this.mIsBold = false;
            }
        }
        if (AppConfig.INSTANCE.getLedType() == 10 || AppConfig.INSTANCE.getLedType() == 11 || AppConfig.INSTANCE.getLedType() == 12 || AppConfig.INSTANCE.getLedType() == 15 || AppConfig.INSTANCE.getLedType() == 14 || AppConfig.INSTANCE.getLedType() == 18 || AppConfig.INSTANCE.getLedType() == 19 || AppConfig.INSTANCE.getLedType() == 17 || AppConfig.INSTANCE.getLedType() == 16) {
            int i2 = this.mTemplateType;
            if (i2 == 4 || i2 == 5 || i2 == 1) {
                TextEmojiBuilder.INSTANCE.getEventText().setTextSize(16);
                this.mIsBold = false;
            } else {
                TextEmojiBuilder.INSTANCE.getEventText().setTextSize(32);
            }
        }
        getTextViewModel().getTextChangedLiveData().observe(this, new TextActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda50
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initEvent$lambda$19;
                initEvent$lambda$19 = TextActivity.initEvent$lambda$19(TextActivity.this, (EventText) obj);
                return initEvent$lambda$19;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initEvent$lambda$19(TextActivity textActivity, EventText eventText) {
        EventText eventText2 = TextEmojiBuilder.INSTANCE.getEventText();
        TextEmojiBuilder textEmojiBuilder = TextEmojiBuilder.INSTANCE;
        Intrinsics.checkNotNull(eventText);
        textEmojiBuilder.set(eventText);
        Iterator<TextEmojiVO> it = eventText.getTextEmojiVO().iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            if (it.next().getType() != -1) {
                z = false;
            }
            textActivity.bIsFirstInit = z;
        }
        if (textActivity.mPageIndex == 2) {
            textActivity.bIsFirstInit = false;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[eventText.getChangeMode().ordinal()]) {
            case 1:
                textActivity.mMaxLenth = TextEmojiBuilder.getCurFontLimit$default(TextEmojiBuilder.INSTANCE, 0, 1, null);
                textActivity.showLimitTextInfo();
                textActivity.bIsFirstInit = false;
                textActivity.changeTextSize(eventText.getTextSize());
                TextAttrFragment textAttrFragment = textActivity.mTextAttrFragment;
                if (textAttrFragment == null || textAttrFragment.getPrevSize() != eventText.getTextSize()) {
                    TextAttrFragment textAttrFragment2 = textActivity.mTextAttrFragment;
                    if (textAttrFragment2 != null) {
                        textAttrFragment2.setPrevSize(eventText.getTextSize());
                    }
                    textActivity.mIsTextSizeChange = true;
                }
                if (textActivity.bIsFirstInit) {
                    return Unit.INSTANCE;
                }
                sendTextAction$default(textActivity, false, false, 2, null);
                break;
                break;
            case 2:
                break;
            case 3:
                textActivity.bIsFirstInit = false;
                textActivity.mIsBold = eventText.isBold();
                textActivity.changeTypeFace(eventText.getTextTypeFace());
                if (textActivity.bIsFirstInit) {
                    return Unit.INSTANCE;
                }
                sendTextAction$default(textActivity, false, false, 2, null);
                break;
            case 4:
                textActivity.bIsFirstInit = false;
                eventText2.setTextHorizontalAlign(eventText.getTextHorizontalAlign());
                textActivity.changeTextAlign(eventText.getTextHorizontalAlign());
                if (textActivity.bIsFirstInit) {
                    return Unit.INSTANCE;
                }
                sendTextAction$default(textActivity, false, false, 2, null);
                break;
            case 5:
                textActivity.bIsFirstInit = false;
                eventText2.setTextVerticalAlign(eventText.getTextVerticalAlign());
                textActivity.changeTextAlign(eventText.getTextVerticalAlign());
                if (textActivity.bIsFirstInit) {
                    return Unit.INSTANCE;
                }
                sendTextAction$default(textActivity, false, false, 2, null);
                break;
            case 6:
                if (textActivity.mActionFromRecord) {
                    return Unit.INSTANCE;
                }
                textActivity.showTextColor(eventText);
                if (textActivity.bIsFirstInit) {
                    return Unit.INSTANCE;
                }
                sendTextAction$default(textActivity, false, false, 2, null);
                return Unit.INSTANCE;
            case 7:
                if (textActivity.mActionFromRecord) {
                    return Unit.INSTANCE;
                }
                textActivity.textBgColor = eventText.getTextBgColor();
                if (textActivity.bIsFirstInit) {
                    return Unit.INSTANCE;
                }
                sendTextAction$default(textActivity, false, false, 2, null);
                return Unit.INSTANCE;
            case 8:
                textActivity.textEffect = eventText.getTextEffect();
                if (textActivity.bIsFirstInit) {
                    return Unit.INSTANCE;
                }
                sendTextAction$default(textActivity, false, false, 2, null);
                return Unit.INSTANCE;
            case 9:
                textActivity.textDirction = eventText.getTextdirction();
                if (textActivity.bIsFirstInit) {
                    return Unit.INSTANCE;
                }
                sendTextAction$default(textActivity, false, false, 2, null);
                break;
            case 10:
                textActivity.mIsBold = eventText.isBold();
                if (textActivity.bIsFirstInit) {
                    return Unit.INSTANCE;
                }
                sendTextAction$default(textActivity, false, false, 2, null);
                break;
            case 11:
                LogUtils.i(textActivity.TAG + ">>>[initEvent--textAlpha]: " + eventText.getTextAlpha());
                return Unit.INSTANCE;
            case 12:
                LogUtils.i(textActivity.TAG + ">>>[initEvent--textSpeed]: " + eventText.getTextSpeed());
                return Unit.INSTANCE;
            default:
                return Unit.INSTANCE;
        }
        if (textActivity.bIsFirstInit || (eventText.getChangeMode() != TextAttrEnum.TextChange && textActivity.mActionFromRecord)) {
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    private final int getTextTotalSize() {
        int i = 0;
        for (TextEmojiVO textEmojiVO : this.mTexts) {
            i = (textEmojiVO.getType() == 1 || CharacterUtilsKt.isChinese(textEmojiVO.getText().charAt(0)) || CharacterUtilsKt.isKoreanCharacter(textEmojiVO.getText().charAt(0)) || CharacterUtilsKt.isJapaneseCharacter(textEmojiVO.getText().charAt(0)) || CharacterUtilsKt.isCyrillicCharacter(textEmojiVO.getText().charAt(0))) ? i + 2 : i + 1;
        }
        return i;
    }

    private final void showLimitTextInfo() {
        int textTotalSize = getTextTotalSize();
        TextView textView = this.tv_fontlimit_in_attr;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_fontlimit_in_attr");
            textView = null;
        }
        textView.setText(textTotalSize + "/" + this.mMaxLenth);
    }

    private final void saveTextAction() {
        if (this.mTexts.isEmpty()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda45
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit saveTextAction$lambda$21;
                    saveTextAction$lambda$21 = TextActivity.saveTextAction$lambda$21(TextActivity.this);
                    return saveTextAction$lambda$21;
                }
            });
            return;
        }
        Gson gson = new Gson();
        Object fromJson = gson.fromJson(gson.toJson(this.mTexts), new TypeToken<List<TextEmojiVO>>() { // from class: com.wifiled.ipixels.ui.text.TextActivity$saveTextAction$tData$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
        List list = (List) fromJson;
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        byte[] textDataArr = getTextProcessData().getTextDataArr();
        int i = this.textTypeface;
        int i2 = this.textEffect;
        int i3 = this.textVerticalAlign + 3;
        int i4 = this.textHorizontalAlign;
        EventText eventText2 = new EventText(list, eventText.getTextAlpha(), 0.0f, null, null, 0, this.textColor, this.textBgColor, i4, i3, i, i2, 0, 0, 0, 0, false, 127036, null);
        TextAttrFragment textAttrFragment = this.mTextAttrFragment;
        Intrinsics.checkNotNull(textAttrFragment);
        int mBorderPos = textAttrFragment.getMBorderPos();
        TextAttrFragment textAttrFragment2 = this.mTextAttrFragment;
        Intrinsics.checkNotNull(textAttrFragment2);
        int mBorderEffectPos = textAttrFragment2.getMBorderEffectPos();
        TextAttrFragment textAttrFragment3 = this.mTextAttrFragment;
        Intrinsics.checkNotNull(textAttrFragment3);
        ChannelListItem.TextEmojView textEmojView = new ChannelListItem.TextEmojView(false, eventText2, "1", false, textDataArr, 0, mBorderPos, mBorderEffectPos, textAttrFragment3.getMBorderSpeed(), 32, null);
        Intent intent = new Intent();
        intent.putExtra("textData", textEmojView);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit saveTextAction$lambda$21(TextActivity textActivity) {
        textActivity.toast(textActivity.getString(R.string.text_input_null));
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendTextAction$default(TextActivity textActivity, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        textActivity.sendTextAction(z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendTextAction(boolean isDown, boolean isFinish) {
        if (this.mTexts.isEmpty()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendTextAction$lambda$22;
                    sendTextAction$lambda$22 = TextActivity.sendTextAction$lambda$22(TextActivity.this);
                    return sendTextAction$lambda$22;
                }
            });
            return;
        }
        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
            if (isFinishing()) {
                return;
            }
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendTextAction$lambda$23;
                    sendTextAction$lambda$23 = TextActivity.sendTextAction$lambda$23(TextActivity.this);
                    return sendTextAction$lambda$23;
                }
            });
            return;
        }
        if (this.mFromType == 1002) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new ChannelListItem.TextEmojView(false, new EventText(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null), String.valueOf(ChannelIndex.INSTANCE.index()), false, new byte[0], 0, 0, 0, 0, 480, null));
            arrayList.add(new ChannelListItem.TextEmojView(false, new EventText(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null), String.valueOf(ChannelIndex.INSTANCE.index()), false, new byte[0], 0, 0, 0, 0, 480, null));
            Gson gson = new Gson();
            Object fromJson = gson.fromJson(gson.toJson(this.mTexts), new TypeToken<List<TextEmojiVO>>() { // from class: com.wifiled.ipixels.ui.text.TextActivity$sendTextAction$tData$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
            List list = (List) fromJson;
            EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
            TextEmojiBuilder.INSTANCE.getEventText().setTextVerticalAlign(this.textVerticalAlign);
            int i = this.textEffect;
            if (eventText.getTextdirction() == 1) {
                i += 128;
            }
            TextEmojiBuilder.INSTANCE.getEventText().setTextEffect(i);
            byte[] textDataArr = getTextProcessData().getTextDataArr();
            Log.v("ruis", "eventText.textSpeed---" + eventText.getTextSpeed());
            int i2 = this.textTypeface;
            int i3 = this.textEffect;
            float textSpeed = eventText.getTextSpeed();
            int i4 = this.textVerticalAlign + 3;
            EventText eventText2 = new EventText(list, eventText.getTextAlpha(), textSpeed, null, null, 0, this.textColor, this.textBgColor, this.textHorizontalAlign, i4, i2, i3, 0, 0, 0, 0, false, 127032, null);
            TextAttrFragment textAttrFragment = this.mTextAttrFragment;
            Intrinsics.checkNotNull(textAttrFragment);
            int mBorderPos = textAttrFragment.getMBorderPos();
            TextAttrFragment textAttrFragment2 = this.mTextAttrFragment;
            Intrinsics.checkNotNull(textAttrFragment2);
            int mBorderEffectPos = textAttrFragment2.getMBorderEffectPos();
            TextAttrFragment textAttrFragment3 = this.mTextAttrFragment;
            Intrinsics.checkNotNull(textAttrFragment3);
            ChannelListItem.TextEmojView textEmojView = new ChannelListItem.TextEmojView(false, eventText2, "1", false, textDataArr, 0, mBorderPos, mBorderEffectPos, textAttrFragment3.getMBorderSpeed(), 32, null);
            if (this.mChooseTvIndex == 1) {
                arrayList.set(0, textEmojView);
            } else {
                arrayList.set(1, textEmojView);
            }
            SubzoneData subzoneData = new SubzoneData(this.mTemplateType, arrayList, AppConfig.INSTANCE.getLedType(), 0, 0, 0, new byte[0], new byte[0], 0, 0, false);
            SendDataCallback sendDataCallback = this.mSendDataCallback;
            if (sendDataCallback != null) {
                SendCore.sendTemplateData$default(false, subzoneData, sendDataCallback, (byte) 0, 8, null);
                return;
            }
            return;
        }
        this.isPriview = isDown;
        if (AppConfig.INSTANCE.getMcu() > 4 && isDown) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add((byte) 7);
            arrayList2.add((byte) 0);
            arrayList2.add((byte) 8);
            arrayList2.add(Byte.valueOf(ByteCompanionObject.MIN_VALUE));
            arrayList2.add((byte) 1);
            arrayList2.add((byte) 0);
            ChannelIndex.INSTANCE.inc();
            arrayList2.add(Byte.valueOf((byte) ChannelIndex.INSTANCE.index()));
            Observable<Long> observeOn = Observable.interval(3L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendTextAction$lambda$25;
                    sendTextAction$lambda$25 = TextActivity.sendTextAction$lambda$25(TextActivity.this, (Long) obj);
                    return sendTextAction$lambda$25;
                }
            };
            this.disposable = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            ArrayList arrayList3 = arrayList2;
            SendCore.INSTANCE.sendCompat(CollectionsKt.toByteArray(arrayList3), new TextActivity$sendTextAction$5(this, isDown, isFinish));
            if (AppConfig.INSTANCE.getConnectType2() != -1) {
                SendCore.INSTANCE.sendCompat2(CollectionsKt.toByteArray(arrayList3), new TextActivity$sendTextAction$6(this, isDown, isFinish));
                return;
            }
            return;
        }
        sendText(isDown, isFinish);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextAction$lambda$22(TextActivity textActivity) {
        textActivity.toast(textActivity.getString(R.string.text_input_null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextAction$lambda$23(TextActivity textActivity) {
        textActivity.toast(textActivity.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextAction$lambda$25(TextActivity textActivity, Long l) {
        Disposable disposable = textActivity.disposable;
        Intrinsics.checkNotNull(disposable);
        disposable.dispose();
        UtilsExtensionKt.showLoadingDialog$default((Activity) textActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!this.isPriview) {
            SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendText(final boolean isDown, final boolean isFinish) {
        try {
            if (ChannelIndex.INSTANCE.mapSaveChannel().size() >= 100) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda34
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit sendText$lambda$27;
                        sendText$lambda$27 = TextActivity.sendText$lambda$27(TextActivity.this);
                        return sendText$lambda$27;
                    }
                });
            }
            Log.v("ruis", "mTexts size-----" + this.mTexts.size());
            Iterator<T> it = this.mTexts.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (((TextEmojiVO) it.next()).getType() == -1) {
                    z = true;
                }
            }
            if (z) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit sendText$lambda$29;
                        sendText$lambda$29 = TextActivity.sendText$lambda$29(TextActivity.this);
                        return sendText$lambda$29;
                    }
                });
                return;
            }
            final EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
            if (isDown) {
                EventText eventText2 = new EventText(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null);
                this.mEventTextSendData = eventText2;
                Intrinsics.checkNotNull(eventText2);
                eventText2.setTextEmojiVO(eventText.getTextEmojiVO());
                EventText eventText3 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText3);
                eventText3.setTextAlpha(eventText.getTextAlpha());
                EventText eventText4 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText4);
                eventText4.setTextSpeed(eventText.getTextSpeed());
                EventText eventText5 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText5);
                eventText5.setChangeMode(eventText.getChangeMode());
                EventText eventText6 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText6);
                eventText6.setText(eventText.getText());
                EventText eventText7 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText7);
                eventText7.setTextSize(eventText.getTextSize());
                EventText eventText8 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText8);
                eventText8.setTextColor(eventText.getTextColor());
                EventText eventText9 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText9);
                eventText9.setTextBgColor(eventText.getTextBgColor());
                EventText eventText10 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText10);
                eventText10.setTextHorizontalAlign(eventText.getTextHorizontalAlign());
                EventText eventText11 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText11);
                eventText11.setTextVerticalAlign(eventText.getTextVerticalAlign());
                EventText eventText12 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText12);
                eventText12.setTextTypeFace(eventText.getTextTypeFace());
                EventText eventText13 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText13);
                eventText13.setTextEffect(eventText.getTextEffect());
                EventText eventText14 = this.mEventTextSendData;
                Intrinsics.checkNotNull(eventText14);
                eventText14.setTextdirction(eventText.getTextdirction());
            }
            TextEmojiBuilder.INSTANCE.getEventText().setTextVerticalAlign(this.textVerticalAlign);
            int i = this.textEffect;
            if (eventText.getTextdirction() == 1) {
                i += 128;
            }
            TextEmojiBuilder.INSTANCE.getEventText().setTextEffect(i);
            TextData textProcessData = getTextProcessData();
            final byte[] textDataArr = textProcessData.getTextDataArr();
            if (textProcessData.isMoreThan()) {
                String string = getString(R.string.content_send_limit_notice);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                UtilsExtensionKt.showDialog$default(this, null, string, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit sendText$lambda$30;
                        sendText$lambda$30 = TextActivity.sendText$lambda$30(TextActivity.this, isDown, isFinish, textDataArr, eventText);
                        return sendText$lambda$30;
                    }
                }, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                }, 1, null);
                return;
            }
            sendByteArray(isDown, isFinish, textDataArr, eventText);
        } catch (Exception e) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendText$lambda$32;
                    sendText$lambda$32 = TextActivity.sendText$lambda$32(e, this);
                    return sendText$lambda$32;
                }
            });
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendText$lambda$27(TextActivity textActivity) {
        textActivity.toast(textActivity.getString(R.string.send_failed_deivce_space));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendText$lambda$29(TextActivity textActivity) {
        textActivity.toast(textActivity.getString(R.string.text_input_null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendText$lambda$30(TextActivity textActivity, boolean z, boolean z2, byte[] bArr, EventText eventText) {
        textActivity.sendByteArray(z, z2, bArr, eventText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendText$lambda$32(Exception exc, TextActivity textActivity) {
        exc.printStackTrace();
        ToastUtil.show(textActivity.getString(R.string.send_text_error) + "(10013)");
        textActivity.showLimitTextInfo();
        NoNewLineEditText noNewLineEditText = textActivity.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        noNewLineEditText.setText("");
        textActivity.mTexts.clear();
        return Unit.INSTANCE;
    }

    private final TextData getTextProcessData() {
        int mcuVersion;
        int mcuVersion2;
        int mcuVersion3;
        CommonTextProcess commonTextProcess;
        int mcuVersion4;
        int mcuVersion5;
        int mcuVersion6;
        int mcuVersion7;
        int mcuVersion8;
        int mcuVersion9;
        int mcuVersion10;
        int mcuVersion11;
        int mcuVersion12;
        int mcuVersion13;
        int mcuVersion14;
        int mcuVersion15;
        int mcuVersion16;
        int mcuVersion17;
        int mcuVersion18;
        int mcuVersion19;
        int mcuVersion20;
        int mcuVersion21;
        NoNewLineEditText noNewLineEditText = this.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        String replace$default = StringsKt.replace$default(StringsKt.trim((CharSequence) String.valueOf(noNewLineEditText.getText())).toString(), "\n", " ", false, 4, (Object) null);
        if (ArabicUtils.isArabic(replace$default)) {
            int mcuVersion22 = AppConfig.INSTANCE.getMcuVersion();
            if ((1809 <= mcuVersion22 && mcuVersion22 < 1900) || ((1412 <= (mcuVersion19 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion19 < 1500) || ((2100 <= (mcuVersion20 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion20 < 2700) || (2800 <= (mcuVersion21 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion21 < 3900)))) {
                commonTextProcess = new NewArabicTextProcess();
            } else {
                commonTextProcess = new ArabicTextProcess();
            }
        } else if (CharacterUtilsKt.containsTamil(replace$default)) {
            int mcuVersion23 = AppConfig.INSTANCE.getMcuVersion();
            if ((1809 <= mcuVersion23 && mcuVersion23 < 1900) || ((1412 <= (mcuVersion16 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion16 < 1500) || ((2100 <= (mcuVersion17 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion17 < 2700) || (2800 <= (mcuVersion18 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion18 < 3900)))) {
                commonTextProcess = new NewTamilTextProcess();
            } else {
                commonTextProcess = new TamilTextProcess();
            }
        } else if (ArabicUtils.isThai(replace$default)) {
            int mcuVersion24 = AppConfig.INSTANCE.getMcuVersion();
            if ((1809 <= mcuVersion24 && mcuVersion24 < 1900) || ((1412 <= (mcuVersion13 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion13 < 1500) || ((2100 <= (mcuVersion14 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion14 < 2700) || (2800 <= (mcuVersion15 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion15 < 3900)))) {
                commonTextProcess = new NewThaiTextProcess();
            } else {
                commonTextProcess = new ThaiTextProcess();
            }
        } else if (CharacterUtilsKt.containsCyrillic(replace$default)) {
            int mcuVersion25 = AppConfig.INSTANCE.getMcuVersion();
            if ((1809 <= mcuVersion25 && mcuVersion25 < 1900) || ((1412 <= (mcuVersion10 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion10 < 1500) || ((2100 <= (mcuVersion11 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion11 < 2700) || (2800 <= (mcuVersion12 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion12 < 3900)))) {
                commonTextProcess = new NewRussianTextProcess();
            } else {
                commonTextProcess = new RussianTextProcess();
            }
        } else if (CharacterUtilsKt.containsVietnamese(replace$default) || CharacterUtilsKt.containsCzech(replace$default) || CharacterUtilsKt.containsGerman(replace$default) || CharacterUtilsKt.containsFrenchOrSpanishOrDutchChars(replace$default) || CharacterUtilsKt.containsGreekChars(replace$default) || CharacterUtilsKt.containsCroatianCharsOrPolishChars(replace$default) || CharacterUtilsKt.containsTurkishChars(replace$default)) {
            int mcuVersion26 = AppConfig.INSTANCE.getMcuVersion();
            if ((1809 <= mcuVersion26 && mcuVersion26 < 1900) || ((1412 <= (mcuVersion = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion < 1500) || ((2100 <= (mcuVersion2 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion2 < 2700) || (2800 <= (mcuVersion3 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion3 < 3900)))) {
                commonTextProcess = new NewVietnameseTextProcess();
            } else {
                commonTextProcess = new CommonTextProcess(false);
            }
        } else if (CharacterUtilsKt.containsDevanagari(replace$default)) {
            int mcuVersion27 = AppConfig.INSTANCE.getMcuVersion();
            if ((1809 <= mcuVersion27 && mcuVersion27 < 1900) || ((1412 <= (mcuVersion7 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion7 < 1500) || ((2100 <= (mcuVersion8 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion8 < 2700) || (2800 <= (mcuVersion9 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion9 < 3900)))) {
                commonTextProcess = new NewIndianTextProcess();
            } else {
                commonTextProcess = new IndianTextProcess();
            }
        } else {
            int mcuVersion28 = AppConfig.INSTANCE.getMcuVersion();
            if ((1809 <= mcuVersion28 && mcuVersion28 < 1900) || ((1412 <= (mcuVersion4 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion4 < 1500) || ((2100 <= (mcuVersion5 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion5 < 2700) || (2800 <= (mcuVersion6 = AppConfig.INSTANCE.getMcuVersion()) && mcuVersion6 < 3900)))) {
                commonTextProcess = new NewCommonTextProcess(this.mIsBold);
            } else {
                commonTextProcess = new CommonTextProcess(false);
            }
        }
        return commonTextProcess.textToByteData(replace$default, this.mTexts);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45(final TextActivity textActivity, SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        callbackBuilder.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$45$lambda$34;
                callback$lambda$45$lambda$34 = TextActivity.callback$lambda$45$lambda$34(TextActivity.this);
                return callback$lambda$45$lambda$34;
            }
        });
        callbackBuilder.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$45$lambda$36;
                callback$lambda$45$lambda$36 = TextActivity.callback$lambda$45$lambda$36(TextActivity.this, ((Integer) obj).intValue());
                return callback$lambda$45$lambda$36;
            }
        });
        callbackBuilder.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$45$lambda$40;
                callback$lambda$45$lambda$40 = TextActivity.callback$lambda$45$lambda$40(TextActivity.this);
                return callback$lambda$45$lambda$40;
            }
        });
        callbackBuilder.onError(new Function1() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$45$lambda$42;
                callback$lambda$45$lambda$42 = TextActivity.callback$lambda$45$lambda$42(TextActivity.this, ((Integer) obj).intValue());
                return callback$lambda$45$lambda$42;
            }
        });
        callbackBuilder.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$45$lambda$44;
                callback$lambda$45$lambda$44 = TextActivity.callback$lambda$45$lambda$44(TextActivity.this, (byte[]) obj);
                return callback$lambda$45$lambda$44;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$34(final TextActivity textActivity) {
        if (!textActivity.isFinishing() && Intrinsics.areEqual((Object) UtilsExtensionKt.isDialogShow(), (Object) false)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$45$lambda$34$lambda$33;
                    callback$lambda$45$lambda$34$lambda$33 = TextActivity.callback$lambda$45$lambda$34$lambda$33(TextActivity.this);
                    return callback$lambda$45$lambda$34$lambda$33;
                }
            });
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$34$lambda$33(TextActivity textActivity) {
        String string = textActivity.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) textActivity, false, string, false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$36(final TextActivity textActivity, final int i) {
        if (!textActivity.isFinishing()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$45$lambda$36$lambda$35;
                    callback$lambda$45$lambda$36$lambda$35 = TextActivity.callback$lambda$45$lambda$36$lambda$35(TextActivity.this, i);
                    return callback$lambda$45$lambda$36$lambda$35;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$36$lambda$35(TextActivity textActivity, int i) {
        TextActivity textActivity2 = textActivity;
        UtilsExtensionKt.showLoadingDialog$default((Activity) textActivity2, true, (String) null, false, 6, (Object) null);
        UtilsExtensionKt.showLoadingDialog$default((Activity) textActivity2, false, textActivity.getString(R.string.msg_sending_progress) + " " + i + " %", true, 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$40(final TextActivity textActivity) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$45$lambda$40$lambda$37;
                callback$lambda$45$lambda$40$lambda$37 = TextActivity.callback$lambda$45$lambda$40$lambda$37(TextActivity.this);
                return callback$lambda$45$lambda$40$lambda$37;
            }
        });
        if (textActivity.mSdIsDown) {
            Gson gson = new Gson();
            Object fromJson = gson.fromJson(gson.toJson(textActivity.mTexts), new TypeToken<List<TextEmojiVO>>() { // from class: com.wifiled.ipixels.ui.text.TextActivity$callback$1$3$tData$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
            List<TextEmojiVO> list = (List) fromJson;
            for (TextEmojiVO textEmojiVO : list) {
                if (textActivity.textColor != textEmojiVO.getTextColor()) {
                    textEmojiVO.setTextColor(textActivity.textColor);
                }
            }
            int i = textActivity.textTypeface;
            int i2 = textActivity.textEffect;
            int i3 = textActivity.textVerticalAlign + 3;
            int i4 = textActivity.textHorizontalAlign;
            int i5 = textActivity.textColor;
            int i6 = textActivity.textBgColor;
            EventText eventText = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText);
            float textAlpha = eventText.getTextAlpha();
            EventText eventText2 = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText2);
            int textdirction = eventText2.getTextdirction();
            EventText eventText3 = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText3);
            int borderType = eventText3.getBorderType();
            EventText eventText4 = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText4);
            int borderSpeed = eventText4.getBorderSpeed();
            EventText eventText5 = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText5);
            EventText eventText6 = new EventText(list, textAlpha, 0.0f, null, null, 0, i5, i6, i4, i3, i, i2, textdirction, borderType, borderSpeed, eventText5.getBorderEffects(), false, 65596, null);
            String valueOf = String.valueOf(ChannelIndex.INSTANCE.index());
            byte[] bArr = textActivity.mSdTextBa;
            Intrinsics.checkNotNull(bArr);
            ChannelIndex.INSTANCE.mapSaveChannel().put(Integer.valueOf(ChannelIndex.INSTANCE.index()), new ChannelListItem.TextEmojView(false, eventText6, valueOf, false, bArr, 0, 0, 0, 0, 480, null));
            TextActivity textActivity2 = textActivity;
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
            SPUtils.put(textActivity2, str, ChannelIndex.INSTANCE.mapSaveChannel());
            if (textActivity.mSdIsFinish) {
                textActivity.finish();
            }
        } else if (textActivity.mFromType == 1001 && textActivity.mSdIsFinish) {
            Gson gson2 = new Gson();
            Object fromJson2 = gson2.fromJson(gson2.toJson(textActivity.mTexts), new TypeToken<List<TextEmojiVO>>() { // from class: com.wifiled.ipixels.ui.text.TextActivity$callback$1$3$tData$2
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson2, "fromJson(...)");
            List<TextEmojiVO> list2 = (List) fromJson2;
            for (TextEmojiVO textEmojiVO2 : list2) {
                if (textActivity.textColor != textEmojiVO2.getTextColor()) {
                    textEmojiVO2.setTextColor(textActivity.textColor);
                }
            }
            int i7 = textActivity.textTypeface;
            int i8 = textActivity.textEffect;
            int i9 = textActivity.textVerticalAlign + 3;
            int i10 = textActivity.textHorizontalAlign;
            int i11 = textActivity.textColor;
            int i12 = textActivity.textBgColor;
            EventText eventText7 = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText7);
            float textAlpha2 = eventText7.getTextAlpha();
            EventText eventText8 = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText8);
            int textdirction2 = eventText8.getTextdirction();
            EventText eventText9 = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText9);
            int borderType2 = eventText9.getBorderType();
            EventText eventText10 = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText10);
            int borderSpeed2 = eventText10.getBorderSpeed();
            EventText eventText11 = textActivity.mSdEventText;
            Intrinsics.checkNotNull(eventText11);
            EventText eventText12 = new EventText(list2, textAlpha2, 0.0f, null, null, 0, i11, i12, i10, i9, i7, i8, textdirction2, borderType2, borderSpeed2, eventText11.getBorderEffects(), false, 65596, null);
            byte[] bArr2 = textActivity.mSdTextBa;
            Intrinsics.checkNotNull(bArr2);
            ChannelListItem.TextEmojView textEmojView = new ChannelListItem.TextEmojView(false, eventText12, "1", false, bArr2, 0, 0, 0, 0, 480, null);
            Intent intent = new Intent();
            intent.putExtra("textData", textEmojView);
            textActivity.setResult(-1, intent);
            textActivity.finish();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$40$lambda$37(TextActivity textActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) textActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$42(final TextActivity textActivity, int i) {
        if (!textActivity.isFinishing() && Intrinsics.areEqual((Object) UtilsExtensionKt.isDialogShow(), (Object) true)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda47
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$45$lambda$42$lambda$41;
                    callback$lambda$45$lambda$42$lambda$41 = TextActivity.callback$lambda$45$lambda$42$lambda$41(TextActivity.this);
                    return callback$lambda$45$lambda$42$lambda$41;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$42$lambda$41(TextActivity textActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) textActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$44(final TextActivity textActivity, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$45$lambda$44$lambda$43;
                callback$lambda$45$lambda$44$lambda$43 = TextActivity.callback$lambda$45$lambda$44$lambda$43(TextActivity.this);
                return callback$lambda$45$lambda$44$lambda$43;
            }
        });
        byte b = it[4];
        if (b == 2) {
            LogUtils.i(textActivity.TAG + ">>>[onResult]:空间不足 ");
        } else if (b == 3) {
            LogUtils.i(textActivity.TAG + ">>>[onResult]:保存文件成功 ");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$45$lambda$44$lambda$43(TextActivity textActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) textActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    private final void sendByteArray(boolean isDown, boolean isFinish, byte[] textBa, EventText eventText) {
        if (AppConfig.INSTANCE.getConnectType() != -1 || AppConfig.INSTANCE.getConnectType2() != -1) {
            this.mSdIsFinish = isFinish;
            this.mSdIsDown = isDown;
            this.mSdEventText = eventText;
            this.mSdTextBa = textBa;
            SendCore.sendTextDataInvokFun$default(SendCore.INSTANCE, isDown, new byte[0], textBa, this.callback, (byte) 0, 16, null);
            SendCore.sendTextDataInvokFun2$default(SendCore.INSTANCE, isDown, new byte[0], textBa, this.callback, (byte) 0, 16, null);
            return;
        }
        if (isFinishing()) {
            return;
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda46
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendByteArray$lambda$46;
                sendByteArray$lambda$46 = TextActivity.sendByteArray$lambda$46(TextActivity.this);
                return sendByteArray$lambda$46;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendByteArray$lambda$46(TextActivity textActivity) {
        textActivity.toast(textActivity.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    private final void changeTypeFace(int typeFace) {
        this.textTypeface = typeFace;
        Iterator<T> it = this.mTexts.iterator();
        while (it.hasNext()) {
            ((TextEmojiVO) it.next()).setTypeFace(typeFace);
        }
    }

    private final void changeTextAlign(int it) {
        if (it >= 0 && it < 3) {
            this.textHorizontalAlign = it;
            TextEmojiBuilder.INSTANCE.getEventText().setTextHorizontalAlign(this.textHorizontalAlign);
        } else {
            if (3 > it || it >= 6) {
                return;
            }
            this.textVerticalAlign = it - 3;
            TextEmojiBuilder.INSTANCE.getEventText().setTextVerticalAlign(this.textVerticalAlign);
        }
    }

    private final void changeTextSize(int size) {
        LogUtils.i(this.TAG + ">>>[changeTextSize]: " + size);
        for (TextEmojiVO textEmojiVO : this.mTexts) {
            if (textEmojiVO.getType() == 0 || textEmojiVO.getType() == -1) {
                boolean isChinese = CharacterUtilsKt.isChinese(textEmojiVO.getText().charAt(0));
                boolean isKoreanCharacter = CharacterUtilsKt.isKoreanCharacter(textEmojiVO.getText().charAt(0));
                boolean isJapaneseCharacter = CharacterUtilsKt.isJapaneseCharacter(textEmojiVO.getText().charAt(0));
                boolean isCyrillicCharacter = CharacterUtilsKt.isCyrillicCharacter(textEmojiVO.getText().charAt(0));
                boolean isTamilCharacter = CharacterUtilsKt.isTamilCharacter(textEmojiVO.getText().charAt(0));
                boolean isSpecialSymbol = CharacterUtilsKt.isSpecialSymbol(textEmojiVO.getText().charAt(0));
                boolean isArabic = ArabicUtils.isArabic(textEmojiVO.getText());
                boolean containsDevanagari = CharacterUtilsKt.containsDevanagari(textEmojiVO.getText());
                boolean isThai = ArabicUtils.isThai(textEmojiVO.getText());
                LogUtils.vTag("ruis", "isArabic --" + isArabic);
                textEmojiVO.setTextWidth((isChinese || isKoreanCharacter || isJapaneseCharacter || isCyrillicCharacter || isTamilCharacter || isArabic || isSpecialSymbol || containsDevanagari || isThai) ? size : size / 2);
                textEmojiVO.setTextHeight(size);
            } else {
                textEmojiVO.setTextWidth(size);
                textEmojiVO.setTextHeight(size);
            }
        }
    }

    /* renamed from: isShow, reason: from getter */
    public final boolean getIsShow() {
        return this.isShow;
    }

    public final void setShow(boolean z) {
        this.isShow = z;
    }

    /* renamed from: isShowEmoj, reason: from getter */
    public final boolean getIsShowEmoj() {
        return this.isShowEmoj;
    }

    public final void setShowEmoj(boolean z) {
        this.isShowEmoj = z;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        ImageView imageView = this.iv_text_send;
        TextAttrEmojiAdapter textAttrEmojiAdapter = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_text_send");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda51
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.bindListener$lambda$49(TextActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        ImageView imageView2 = this.iv_text_send;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_text_send");
            imageView2 = null;
        }
        companion.attachViewOnTouchListener(imageView2);
        ConstraintLayout constraintLayout = this.cl_edit_kb_in_attr;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_kb_in_attr");
            constraintLayout = null;
        }
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.bindListener$lambda$51(TextActivity.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        ConstraintLayout constraintLayout2 = this.cl_edit_kb_in_attr;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_kb_in_attr");
            constraintLayout2 = null;
        }
        companion2.attachViewOnTouchListener(constraintLayout2);
        ConstraintLayout constraintLayout3 = this.cl_edit_in_attr;
        if (constraintLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_in_attr");
            constraintLayout3 = null;
        }
        constraintLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.bindListener$lambda$55(TextActivity.this, view);
            }
        });
        CustomImageView.Companion companion3 = CustomImageView.INSTANCE;
        ConstraintLayout constraintLayout4 = this.cl_edit_in_attr;
        if (constraintLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_in_attr");
            constraintLayout4 = null;
        }
        companion3.attachViewOnTouchListener(constraintLayout4);
        RelativeLayout relativeLayout = this.rl_hide_keyboard;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_hide_keyboard");
            relativeLayout = null;
        }
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.bindListener$lambda$57(TextActivity.this, view);
            }
        });
        CustomImageView.Companion companion4 = CustomImageView.INSTANCE;
        RelativeLayout relativeLayout2 = this.rl_hide_keyboard;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_hide_keyboard");
            relativeLayout2 = null;
        }
        companion4.attachViewOnTouchListener(relativeLayout2);
        TextAttrEmojiAdapter textAttrEmojiAdapter2 = this.emojiAdapter;
        if (textAttrEmojiAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emojiAdapter");
        } else {
            textAttrEmojiAdapter = textAttrEmojiAdapter2;
        }
        textAttrEmojiAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda4
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextActivity.bindListener$lambda$58(TextActivity.this, viewGroup, view, (Integer) obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$49(TextActivity textActivity, View view) {
        int i = textActivity.mFromType;
        if (i == 1001) {
            textActivity.sendTextAction(false, true);
        } else if (i == 1002) {
            textActivity.saveTextAction();
        } else {
            sendTextAction$default(textActivity, true, false, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$51(final TextActivity textActivity, View view) {
        textActivity.isShowEmoj = false;
        view.setSelected(false);
        ConstraintLayout constraintLayout = textActivity.cl_edit_in_attr;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_in_attr");
            constraintLayout = null;
        }
        constraintLayout.setSelected(textActivity.isShowEmoj);
        if (textActivity.isShowEmoj) {
            return;
        }
        UtilsExtensionKt.uiDelay(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindListener$lambda$51$lambda$50;
                bindListener$lambda$51$lambda$50 = TextActivity.bindListener$lambda$51$lambda$50(TextActivity.this);
                return bindListener$lambda$51$lambda$50;
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$51$lambda$50(TextActivity textActivity) {
        NoNewLineEditText noNewLineEditText = textActivity.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        KeyBordUtil.showSoftKeyboard(noNewLineEditText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$55(final TextActivity textActivity, View view) {
        textActivity.isShowEmoj = true;
        view.setSelected(true);
        ConstraintLayout constraintLayout = textActivity.cl_edit_kb_in_attr;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_kb_in_attr");
            constraintLayout = null;
        }
        constraintLayout.setSelected(textActivity.isShowEmoj);
        boolean z = textActivity.isShowEmoj;
        if (!z) {
            UtilsExtensionKt.uiDelay(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$55$lambda$52;
                    bindListener$lambda$55$lambda$52 = TextActivity.bindListener$lambda$55$lambda$52(TextActivity.this);
                    return bindListener$lambda$55$lambda$52;
                }
            }, 100L);
        } else {
            if (!z) {
                throw new NoWhenBranchMatchedException();
            }
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$55$lambda$53;
                    bindListener$lambda$55$lambda$53 = TextActivity.bindListener$lambda$55$lambda$53(TextActivity.this);
                    return bindListener$lambda$55$lambda$53;
                }
            });
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$55$lambda$54;
                    bindListener$lambda$55$lambda$54 = TextActivity.bindListener$lambda$55$lambda$54(TextActivity.this);
                    return bindListener$lambda$55$lambda$54;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$55$lambda$52(TextActivity textActivity) {
        NoNewLineEditText noNewLineEditText = textActivity.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        KeyBordUtil.showSoftKeyboard(noNewLineEditText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$55$lambda$53(TextActivity textActivity) {
        RecyclerView recyclerView = textActivity.rl_input_in_attr;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_input_in_attr");
            recyclerView = null;
        }
        UtilsExtensionKt.show(recyclerView);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$55$lambda$54(TextActivity textActivity) {
        NoNewLineEditText noNewLineEditText = textActivity.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        KeyBordUtil.hideSoftKeyboard(noNewLineEditText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$57(final TextActivity textActivity, View view) {
        Intrinsics.checkNotNull(view);
        UtilsExtensionKt.hide(view);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda48
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindListener$lambda$57$lambda$56;
                bindListener$lambda$57$lambda$56 = TextActivity.bindListener$lambda$57$lambda$56(TextActivity.this);
                return bindListener$lambda$57$lambda$56;
            }
        });
        if (textActivity.isShowEmoj) {
            textActivity.isShowEmoj = false;
            CustomImageView customImageView = textActivity.iv_emoji_in_attr;
            ConstraintLayout constraintLayout = null;
            if (customImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_emoji_in_attr");
                customImageView = null;
            }
            customImageView.setSelected(textActivity.isShowEmoj);
            ConstraintLayout constraintLayout2 = textActivity.cl_edit_outframe_in_attr;
            if (constraintLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_edit_outframe_in_attr");
            } else {
                constraintLayout = constraintLayout2;
            }
            UtilsExtensionKt.hide(constraintLayout);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$57$lambda$56(TextActivity textActivity) {
        NoNewLineEditText noNewLineEditText = textActivity.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        KeyBordUtil.hideSoftKeyboard(noNewLineEditText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$58(TextActivity textActivity, ViewGroup viewGroup, View view, Integer num, int i) {
        if (textActivity.getTextTotalSize() >= textActivity.mMaxLenth) {
            ToastUtil.show(textActivity.getString(R.string.font_limit_notice));
            return;
        }
        NoNewLineEditText noNewLineEditText = textActivity.test_edit;
        if (noNewLineEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText = null;
        }
        int selectionStart = noNewLineEditText.getSelectionStart();
        NoNewLineEditText noNewLineEditText2 = textActivity.test_edit;
        if (noNewLineEditText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("test_edit");
            noNewLineEditText2 = null;
        }
        Editable editableText = noNewLineEditText2.getEditableText();
        Intrinsics.checkNotNullExpressionValue(editableText, "getEditableText(...)");
        if (selectionStart < 0 || selectionStart >= editableText.length()) {
            TextAttrEmojiAdapter textAttrEmojiAdapter = textActivity.emojiAdapter;
            if (textAttrEmojiAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("emojiAdapter");
                textAttrEmojiAdapter = null;
            }
            editableText.append((CharSequence) Html.fromHtml("<img src='" + textAttrEmojiAdapter.getMData().get(i) + "'/>", textActivity.imageGetter, null));
            return;
        }
        TextAttrEmojiAdapter textAttrEmojiAdapter2 = textActivity.emojiAdapter;
        if (textAttrEmojiAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emojiAdapter");
            textAttrEmojiAdapter2 = null;
        }
        editableText.insert(selectionStart, Html.fromHtml("<img src='" + textAttrEmojiAdapter2.getMData().get(i) + "'/>", textActivity.imageGetter, null));
    }

    public final int getKbHeight() {
        return this.kbHeight;
    }

    public final void setKbHeight(int i) {
        this.kbHeight = i;
    }

    @Override // com.wifiled.ipixels.ui.text.KeyboardHeightObserver
    public void onKeyboardHeightChanged(int height, int orientation) {
        String str = orientation == 1 ? "portrait" : "landscape";
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if (height > 500) {
            if (this.kbHeight != this.windowHeight - rect.bottom && this.windowHeight - rect.bottom > 120) {
                this.kbHeight = this.windowHeight - rect.bottom;
            }
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onKeyboardHeightChanged$lambda$59;
                    onKeyboardHeightChanged$lambda$59 = TextActivity.onKeyboardHeightChanged$lambda$59(TextActivity.this);
                    return onKeyboardHeightChanged$lambda$59;
                }
            });
            return;
        }
        if (this.windowHeight == 0) {
            this.windowHeight = rect.bottom;
        }
        Log.e(this.TAG, "onKeyboardHeightChanged hide in pixels: " + height + " " + str);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onKeyboardHeightChanged$lambda$60;
                onKeyboardHeightChanged$lambda$60 = TextActivity.onKeyboardHeightChanged$lambda$60(TextActivity.this);
                return onKeyboardHeightChanged$lambda$60;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onKeyboardHeightChanged$lambda$59(TextActivity textActivity) {
        textActivity.isShow = true;
        ConstraintLayout constraintLayout = textActivity.cl_edit_kb_in_attr;
        RecyclerView recyclerView = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_kb_in_attr");
            constraintLayout = null;
        }
        constraintLayout.setSelected(!textActivity.isShow);
        ConstraintLayout constraintLayout2 = textActivity.cl_edit_in_attr;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_in_attr");
            constraintLayout2 = null;
        }
        constraintLayout2.setSelected(true ^ textActivity.isShow);
        if (textActivity.mFromType != 1002) {
            ConstraintLayout constraintLayout3 = textActivity.cl_edit_outframe_in_attr;
            if (constraintLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_edit_outframe_in_attr");
                constraintLayout3 = null;
            }
            UtilsExtensionKt.show(constraintLayout3);
        }
        RelativeLayout relativeLayout = textActivity.rl_hide_keyboard;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_hide_keyboard");
            relativeLayout = null;
        }
        UtilsExtensionKt.show(relativeLayout);
        RecyclerView recyclerView2 = textActivity.rl_input_in_attr;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_input_in_attr");
            recyclerView2 = null;
        }
        ViewGroup.LayoutParams layoutParams = recyclerView2.getLayoutParams();
        Intrinsics.checkNotNullExpressionValue(layoutParams, "getLayoutParams(...)");
        layoutParams.height = textActivity.kbHeight;
        RecyclerView recyclerView3 = textActivity.rl_input_in_attr;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_input_in_attr");
        } else {
            recyclerView = recyclerView3;
        }
        recyclerView.setLayoutParams(layoutParams);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onKeyboardHeightChanged$lambda$60(TextActivity textActivity) {
        textActivity.isShow = false;
        if (!textActivity.isShowEmoj) {
            ConstraintLayout constraintLayout = textActivity.cl_edit_outframe_in_attr;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_edit_outframe_in_attr");
                constraintLayout = null;
            }
            UtilsExtensionKt.hide(constraintLayout);
        }
        return Unit.INSTANCE;
    }

    private final void showTextColor(EventText it) {
        this.textColor = it.getTextColor();
    }

    private final void initEmojs() {
        this.emojiPreviews.clear();
        TextActivity textActivity = this;
        int[] resIds = ResourceUtils.getResIds(textActivity, R.array.text_emoji_100_preview);
        Intrinsics.checkNotNull(resIds);
        for (int i : resIds) {
            this.emojiPreviews.add(Integer.valueOf(i));
        }
        RecyclerView recyclerView = this.rl_input_in_attr;
        TextView textView = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_input_in_attr");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(textActivity, 7));
        this.emojiAdapter = new TextAttrEmojiAdapter(textActivity, this.emojiPreviews);
        RecyclerView recyclerView2 = this.rl_input_in_attr;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_input_in_attr");
            recyclerView2 = null;
        }
        TextAttrEmojiAdapter textAttrEmojiAdapter = this.emojiAdapter;
        if (textAttrEmojiAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emojiAdapter");
            textAttrEmojiAdapter = null;
        }
        recyclerView2.setAdapter(textAttrEmojiAdapter);
        TextView textView2 = this.tv_fontlimit_in_attr;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_fontlimit_in_attr");
        } else {
            textView = textView2;
        }
        textView.setText(this.mValHasCounted + "/" + this.mMaxLenth);
    }

    private final void initViewpager() {
        ViewPager2 viewPager2 = this.viewpager;
        TextView textView = null;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewpager");
            viewPager2 = null;
        }
        viewPager2.setAdapter(new FragmentStateAdapter() { // from class: com.wifiled.ipixels.ui.text.TextActivity$initViewpager$1
            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return 3;
            }

            {
                super(TextActivity.this);
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int position) {
                TextAttrFragment textAttrFragment;
                TextAttrFragment textAttrFragment2;
                TextAttrFragment textAttrFragment3;
                int i;
                BaseFragment baseFragment;
                TextEffectFragment textEffectFragment;
                TextEffectFragment textEffectFragment2;
                int i2;
                TextRecordFragment textRecordFragment;
                TextRecordFragment textRecordFragment2;
                LogUtils.i("createFragment: " + position);
                TextActivity textActivity = TextActivity.this;
                if (position == 0) {
                    textAttrFragment = textActivity.mTextAttrFragment;
                    if (textAttrFragment == null) {
                        TextActivity textActivity2 = TextActivity.this;
                        TextAttrFragment.Companion companion = TextAttrFragment.INSTANCE;
                        i = TextActivity.this.mTemplateType;
                        textActivity2.mTextAttrFragment = companion.newInstance(i);
                    }
                    textAttrFragment2 = TextActivity.this.mTextAttrFragment;
                    Intrinsics.checkNotNull(textAttrFragment2);
                    textAttrFragment3 = textAttrFragment2;
                } else if (position != 1) {
                    textRecordFragment = textActivity.mTextRecordFragment;
                    if (textRecordFragment == null) {
                        TextActivity.this.mTextRecordFragment = new TextRecordFragment();
                    }
                    textRecordFragment2 = TextActivity.this.mTextRecordFragment;
                    Intrinsics.checkNotNull(textRecordFragment2);
                    textAttrFragment3 = textRecordFragment2;
                } else {
                    textEffectFragment = textActivity.mTextEffectFragment;
                    if (textEffectFragment == null) {
                        TextActivity textActivity3 = TextActivity.this;
                        TextEffectFragment.Companion companion2 = TextEffectFragment.INSTANCE;
                        i2 = TextActivity.this.mFromType;
                        textActivity3.mTextEffectFragment = companion2.newInstance(i2);
                    }
                    textEffectFragment2 = TextActivity.this.mTextEffectFragment;
                    Intrinsics.checkNotNull(textEffectFragment2);
                    textAttrFragment3 = textEffectFragment2;
                }
                textActivity.mCurFragment = textAttrFragment3;
                baseFragment = TextActivity.this.mCurFragment;
                if (baseFragment == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
                    baseFragment = null;
                }
                return baseFragment;
            }
        });
        ViewPager2 viewPager22 = this.viewpager;
        if (viewPager22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewpager");
            viewPager22 = null;
        }
        viewPager22.setUserInputEnabled(false);
        ViewPager2 viewPager23 = this.viewpager;
        if (viewPager23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewpager");
            viewPager23 = null;
        }
        viewPager23.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wifiled.ipixels.ui.text.TextActivity$initViewpager$2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int position) {
                int i;
                int i2;
                int i3;
                super.onPageSelected(position);
                LogUtils.i("onPageSelected: " + position);
                i = TextActivity.this.mPageIndex;
                if (i != position) {
                    TextActivity.this.mPageIndex = position;
                    TextActivity textActivity = TextActivity.this;
                    i2 = textActivity.mPageIndex;
                    textActivity.mPrevPageIndex = i2;
                    i3 = TextActivity.this.mPageIndex;
                    if (i3 != 2) {
                        TextActivity.this.mActionFromRecord = false;
                    }
                }
            }
        });
        ViewPager2 viewPager24 = this.viewpager;
        if (viewPager24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewpager");
            viewPager24 = null;
        }
        viewPager24.setOffscreenPageLimit(1);
        TextView textView2 = this.tv_menu_attr;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_attr");
            textView2 = null;
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.initViewpager$lambda$62(TextActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        TextView textView3 = this.tv_menu_attr;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_attr");
            textView3 = null;
        }
        companion.attachViewOnTouchListener(textView3);
        TextView textView4 = this.tv_menu_effect;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_effect");
            textView4 = null;
        }
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.initViewpager$lambda$63(TextActivity.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        TextView textView5 = this.tv_menu_effect;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_effect");
            textView5 = null;
        }
        companion2.attachViewOnTouchListener(textView5);
        TextView textView6 = this.tv_menu_rec;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_rec");
            textView6 = null;
        }
        textView6.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.TextActivity$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextActivity.initViewpager$lambda$64(TextActivity.this, view);
            }
        });
        CustomImageView.Companion companion3 = CustomImageView.INSTANCE;
        TextView textView7 = this.tv_menu_rec;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_rec");
        } else {
            textView = textView7;
        }
        companion3.attachViewOnTouchListener(textView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViewpager$lambda$62(TextActivity textActivity, View view) {
        TextView textView = textActivity.tv_menu_attr;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_attr");
            textView = null;
        }
        textView.setTextColor(textActivity.getResources().getColor(R.color.vp_sel_bgcolor, textActivity.getTheme()));
        ViewPager2 viewPager2 = textActivity.viewpager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewpager");
            viewPager2 = null;
        }
        viewPager2.setCurrentItem(0, true);
        TextView textView3 = textActivity.tv_menu_effect;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_effect");
            textView3 = null;
        }
        textView3.setTextColor(textActivity.getResources().getColor(R.color.vp_nosel_bgcolor, textActivity.getTheme()));
        TextView textView4 = textActivity.tv_menu_rec;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_rec");
        } else {
            textView2 = textView4;
        }
        textView2.setTextColor(textActivity.getResources().getColor(R.color.vp_nosel_bgcolor, textActivity.getTheme()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViewpager$lambda$63(TextActivity textActivity, View view) {
        TextView textView = textActivity.tv_menu_effect;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_effect");
            textView = null;
        }
        textView.setTextColor(textActivity.getResources().getColor(R.color.vp_sel_bgcolor, textActivity.getTheme()));
        ViewPager2 viewPager2 = textActivity.viewpager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewpager");
            viewPager2 = null;
        }
        viewPager2.setCurrentItem(1, true);
        TextView textView3 = textActivity.tv_menu_attr;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_attr");
            textView3 = null;
        }
        textView3.setTextColor(textActivity.getResources().getColor(R.color.vp_nosel_bgcolor, textActivity.getTheme()));
        TextView textView4 = textActivity.tv_menu_rec;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_rec");
        } else {
            textView2 = textView4;
        }
        textView2.setTextColor(textActivity.getResources().getColor(R.color.vp_nosel_bgcolor, textActivity.getTheme()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViewpager$lambda$64(TextActivity textActivity, View view) {
        TextView textView = textActivity.tv_menu_rec;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_rec");
            textView = null;
        }
        textView.setTextColor(textActivity.getResources().getColor(R.color.vp_sel_bgcolor, textActivity.getTheme()));
        ViewPager2 viewPager2 = textActivity.viewpager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewpager");
            viewPager2 = null;
        }
        viewPager2.setCurrentItem(2, true);
        TextView textView3 = textActivity.tv_menu_attr;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_attr");
            textView3 = null;
        }
        textView3.setTextColor(textActivity.getResources().getColor(R.color.vp_nosel_bgcolor, textActivity.getTheme()));
        TextView textView4 = textActivity.tv_menu_effect;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_menu_effect");
        } else {
            textView2 = textView4;
        }
        textView2.setTextColor(textActivity.getResources().getColor(R.color.vp_nosel_bgcolor, textActivity.getTheme()));
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0111 A[Catch: all -> 0x01e6, TryCatch #1 {all -> 0x01e6, blocks: (B:11:0x001a, B:12:0x0027, B:14:0x002f, B:16:0x0038, B:20:0x004d, B:26:0x007f, B:29:0x008a, B:32:0x0075, B:33:0x0132, B:39:0x013e, B:40:0x0157, B:42:0x0161, B:43:0x0198, B:45:0x01b4, B:46:0x01bb, B:50:0x0173, B:52:0x017d, B:54:0x0187, B:35:0x0137, B:56:0x00b5, B:58:0x00bb, B:60:0x00c1, B:62:0x00c7, B:64:0x00cd, B:66:0x00d3, B:68:0x00d9, B:70:0x00e3, B:72:0x00ed, B:78:0x0100, B:80:0x0111, B:81:0x011b, B:82:0x0116, B:25:0x0058), top: B:10:0x001a, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0116 A[Catch: all -> 0x01e6, TryCatch #1 {all -> 0x01e6, blocks: (B:11:0x001a, B:12:0x0027, B:14:0x002f, B:16:0x0038, B:20:0x004d, B:26:0x007f, B:29:0x008a, B:32:0x0075, B:33:0x0132, B:39:0x013e, B:40:0x0157, B:42:0x0161, B:43:0x0198, B:45:0x01b4, B:46:0x01bb, B:50:0x0173, B:52:0x017d, B:54:0x0187, B:35:0x0137, B:56:0x00b5, B:58:0x00bb, B:60:0x00c1, B:62:0x00c7, B:64:0x00cd, B:66:0x00d3, B:68:0x00d9, B:70:0x00e3, B:72:0x00ed, B:78:0x0100, B:80:0x0111, B:81:0x011b, B:82:0x0116, B:25:0x0058), top: B:10:0x001a, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ff  */
    @Override // android.text.TextWatcher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void afterTextChanged(android.text.Editable r24) {
        /*
            Method dump skipped, instructions count: 491
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.text.TextActivity.afterTextChanged(android.text.Editable):void");
    }
}
