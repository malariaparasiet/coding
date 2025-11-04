package com.wifiled.ipixels.ui.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.Size;
import androidx.autofill.HintConstants;
import androidx.camera.video.AudioStats;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.Image;
import com.squareup.gifencoder.ImageOptions;
import com.wifiled.baselib.utils.FileUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.PreferenceSettings;
import com.wifiled.ipixels.core.GifCore;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.FontUtils;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: CreativeTextUtil.kt */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\b\u0011\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b)\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0083\u0001\u0010f\u001a\u00020g2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010h\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001e\u001a\u00020\u00162\u0006\u00100\u001a\u0002012\u0006\u00106\u001a\u0002012\u0006\u0010!\u001a\u00020\"2\u0006\u0010'\u001a\u00020(2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010-\u001a\u00020(2\u0006\u0010E\u001a\u0002012!\u0010i\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\bk\u0012\b\bl\u0012\u0004\b\b(m\u0012\u0004\u0012\u00020g0jJ\u0016\u0010n\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010o\u001a\u00020bH\u0002J\u001c\u0010p\u001a\b\u0012\u0004\u0012\u00020b0a2\f\u0010q\u001a\b\u0012\u0004\u0012\u00020r0aH\u0002J8\u0010s\u001a\u00020g2\u0006\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020r2\u0006\u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020O2\u0006\u0010z\u001a\u0002012\u0006\u0010{\u001a\u000201H\u0002J\u0016\u0010|\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010}\u001a\u00020bH\u0002J\b\u0010~\u001a\u00020gH\u0002J\b\u0010\u007f\u001a\u00020gH\u0002J-\u0010\u0080\u0001\u001a\u00020g2\"\u0010\u0081\u0001\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\bk\u0012\b\bl\u0012\u0004\b\b(m\u0012\u0004\u0012\u00020g0jH\u0002J1\u0010\u0082\u0001\u001a\u00020g2\u0006\u0010t\u001a\u00020u2\u0007\u0010\u0083\u0001\u001a\u0002012\r\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002JJ\u0010\u0085\u0001\u001a\u00020g2\u0006\u0010t\u001a\u00020u2\u0007\u0010\u0083\u0001\u001a\u0002012\u0007\u0010\u0086\u0001\u001a\u00020b2\u0006\u0010\u0015\u001a\u00020\u00162\u0014\u0010\u0087\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0088\u00012\u0007\u0010\u0089\u0001\u001a\u000201H\u0002J\u001c\u0010\u008a\u0001\u001a\u00020(2\u0007\u0010\u008b\u0001\u001a\u00020(2\b\b\u0002\u0010[\u001a\u000201H\u0002J+\u0010\u008c\u0001\u001a\u00020g2\u0006\u0010t\u001a\u00020u2\u0006\u0010\u0015\u001a\u00020\u00162\u0007\u0010\u0086\u0001\u001a\u00020b2\u0007\u0010\u008d\u0001\u001a\u00020(H\u0002J)\u0010\u008e\u0001\u001a\u00020g2\u0006\u0010t\u001a\u00020u2\u0006\u0010\u0015\u001a\u00020\u00162\u0007\u0010\u0086\u0001\u001a\u00020b2\u0007\u0010\u008d\u0001\u001a\u00020(J\u0015\u0010\u008f\u0001\u001a\b\u0012\u0004\u0012\u00020x0a2\u0006\u0010\u0015\u001a\u00020\u0016J$\u0010\u0090\u0001\u001a\u00020(2\u0007\u0010\u0091\u0001\u001a\u0002012\u0007\u0010\u0092\u0001\u001a\u0002012\u0007\u0010\u0093\u0001\u001a\u00020(H\u0002JF\u0010\u0094\u0001\u001a\u00020g2\u0006\u0010t\u001a\u00020u2\u0007\u0010\u0095\u0001\u001a\u00020b2\u0007\u0010\u0096\u0001\u001a\u00020(2\u0007\u0010\u0097\u0001\u001a\u00020(2\u0007\u0010\u0098\u0001\u001a\u00020(2\u0007\u0010\u008d\u0001\u001a\u00020(2\u0006\u00109\u001a\u00020:H\u0002J*\u0010\u0099\u0001\u001a\t\u0012\u0004\u0012\u00020b0\u009a\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0007\u0010\u0086\u0001\u001a\u00020b2\u0007\u0010\u009b\u0001\u001a\u00020(H\u0002J$\u0010\u009c\u0001\u001a\u00020(2\u0007\u0010\u0083\u0001\u001a\u0002012\u0007\u0010\u009d\u0001\u001a\u0002012\u0007\u0010\u009e\u0001\u001a\u000201H\u0002J<\u0010\u009f\u0001\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0a0\u009a\u00012\u0013\u0010 \u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160a0a2\u0007\u0010\u009b\u0001\u001a\u00020(2\u0006\u00109\u001a\u00020:H\u0002J4\u0010¡\u0001\u001a\u00020g2\u0006\u0010t\u001a\u00020u2\u0006\u0010\u0015\u001a\u00020\u00162\u0007\u0010¢\u0001\u001a\u00020(2\u0007\u0010£\u0001\u001a\u00020(2\u0007\u0010¤\u0001\u001a\u000201H\u0002J\u0013\u0010¥\u0001\u001a\u00030¦\u00012\u0007\u0010\u008d\u0001\u001a\u00020(H\u0002J\u001c\u0010§\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0088\u00012\u0006\u0010\u0015\u001a\u00020\u0016J\n\u0010¨\u0001\u001a\u00030¦\u0001H\u0002J'\u0010©\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010\u0015\u001a\u00020\u00162\u000e\u0010ª\u0001\u001a\t\u0012\u0004\u0012\u00020b0\u009a\u0001H\u0002J'\u0010«\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010\u0015\u001a\u00020\u00162\u000e\u0010ª\u0001\u001a\t\u0012\u0004\u0012\u00020b0\u009a\u0001H\u0002JC\u0010¬\u0001\u001a\u00020b2\u0007\u0010\u009d\u0001\u001a\u0002012\u0007\u0010\u008d\u0001\u001a\u00020(2\u0007\u0010\u00ad\u0001\u001a\u00020(2\r\u0010®\u0001\u001a\b\u0012\u0004\u0012\u00020(0a2\u000e\u0010¯\u0001\u001a\t\u0012\u0004\u0012\u00020b0\u009a\u0001H\u0002J'\u0010°\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010\u0015\u001a\u00020\u00162\u000e\u0010ª\u0001\u001a\t\u0012\u0004\u0012\u00020b0\u009a\u0001H\u0002JB\u0010±\u0001\u001a\u00020b2\r\u0010²\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\r\u0010³\u0001\u001a\b\u0012\u0004\u0012\u00020(0a2\u0007\u0010\u008d\u0001\u001a\u00020(2\u0007\u0010´\u0001\u001a\u00020b2\u0007\u0010µ\u0001\u001a\u00020uH\u0002J&\u0010¶\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010\u0015\u001a\u00020\u00162\r\u0010ª\u0001\u001a\b\u0012\u0004\u0012\u00020b0aH\u0002J\u0011\u0010·\u0001\u001a\u00020(2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J \u0010¸\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010}\u001a\u00020b2\u0007\u0010\u009b\u0001\u001a\u00020(H\u0002J*\u0010¹\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\u0007\u0010º\u0001\u001a\u00020b2\u0007\u0010»\u0001\u001a\u00020b2\u0007\u0010\u009b\u0001\u001a\u00020(H\u0002J\u0012\u0010¼\u0001\u001a\u00020(2\u0007\u0010\u008b\u0001\u001a\u00020(H\u0002J\u000f\u0010½\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160aH\u0002J\u0015\u0010¾\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160a0aH\u0002J\u001e\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\u0007\u0010\u0089\u0001\u001a\u0002012\u0006\u0010}\u001a\u00020bJ#\u0010À\u0001\u001a\u00020b2\u0006\u0010}\u001a\u00020b2\u0007\u0010\u008d\u0001\u001a\u00020(2\u0007\u0010Á\u0001\u001a\u000201H\u0002J<\u0010Â\u0001\u001a\u00020g2\r\u0010Ã\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\"\u0010\u0081\u0001\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\bk\u0012\b\bl\u0012\u0004\b\b(m\u0012\u0004\u0012\u00020g0jH\u0002J9\u0010Ä\u0001\u001a\u00020b2\u0006\u0010t\u001a\u00020u2\u0007\u0010Å\u0001\u001a\u00020b2\u0007\u0010Æ\u0001\u001a\u00020b2\t\u0010Ç\u0001\u001a\u0004\u0018\u00010b2\t\u0010È\u0001\u001a\u0004\u0018\u00010bH\u0002J\u001a\u0010É\u0001\u001a\u00020b2\u0006\u0010\u0015\u001a\u00020\u00162\u0007\u0010Ê\u0001\u001a\u00020\"H\u0002J%\u0010Ë\u0001\u001a\u00020g2\u0007\u0010Ì\u0001\u001a\u0002012\b\u0010Í\u0001\u001a\u00030¦\u00012\u0007\u0010\u009b\u0001\u001a\u00020(H\u0002J\u0019\u0010Î\u0001\u001a\u00020(2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010'\u001a\u00020(H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001a\u0010\u001e\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010*\"\u0004\b/\u0010,R\u001a\u00100\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00103\"\u0004\b8\u00105R\u001a\u00109\u001a\u00020:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001a\u0010E\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u00103\"\u0004\bG\u00105R\u001a\u0010H\u001a\u00020IX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u001a\u0010N\u001a\u00020OX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001a\u0010T\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010*\"\u0004\bV\u0010,R\u000e\u0010W\u001a\u000201X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u000201X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u000201X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010`\u001a\b\u0012\u0004\u0012\u00020b0aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010c\u001a\u0004\u0018\u00010bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010d\u001a\u0004\u0018\u00010eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Ï\u0001"}, d2 = {"Lcom/wifiled/ipixels/ui/text/CreativeTextUtil;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "color_new1", "", "color_new2", "canvasSize", "Landroid/util/Size;", "getCanvasSize", "()Landroid/util/Size;", "setCanvasSize", "(Landroid/util/Size;)V", "animationType", "Lcom/wifiled/ipixels/ui/text/AnimationType;", "getAnimationType", "()Lcom/wifiled/ipixels/ui/text/AnimationType;", "setAnimationType", "(Lcom/wifiled/ipixels/ui/text/AnimationType;)V", TextBundle.TEXT_ENTRY, "", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "backgroundGifPath", "getBackgroundGifPath", "setBackgroundGifPath", "boardGifPath", "getBoardGifPath", "setBoardGifPath", "textAlignment", "Lcom/wifiled/ipixels/ui/text/TextAlignment;", "getTextAlignment", "()Lcom/wifiled/ipixels/ui/text/TextAlignment;", "setTextAlignment", "(Lcom/wifiled/ipixels/ui/text/TextAlignment;)V", "fontSize", "", "getFontSize", "()F", "setFontSize", "(F)V", "animationSpeed", "getAnimationSpeed", "setAnimationSpeed", "textColor", "", "getTextColor", "()I", "setTextColor", "(I)V", "textGradients", "getTextGradients", "setTextGradients", "direction", "Lcom/wifiled/ipixels/ui/text/DirectionType;", "getDirection", "()Lcom/wifiled/ipixels/ui/text/DirectionType;", "setDirection", "(Lcom/wifiled/ipixels/ui/text/DirectionType;)V", "gradientType", "Lcom/wifiled/ipixels/ui/text/GradientType;", "getGradientType", "()Lcom/wifiled/ipixels/ui/text/GradientType;", "setGradientType", "(Lcom/wifiled/ipixels/ui/text/GradientType;)V", "stayDuration", "getStayDuration", "setStayDuration", "font", "Landroid/graphics/Typeface;", "getFont", "()Landroid/graphics/Typeface;", "setFont", "(Landroid/graphics/Typeface;)V", "mIsAntiAlias", "", "getMIsAntiAlias", "()Z", "setMIsAntiAlias", "(Z)V", "durationPerChar", "getDurationPerChar", "setDurationPerChar", "FPS", "ASSEMBLE_FRAMES", "DISSOLVE_FRAMES", "dropHeight", "bounceCount", "charDelay", "showRatio", "stayRatio", "hideRatio", "bgFrames", "", "Landroid/graphics/Bitmap;", "mBoardBitmap", "mLinearGradient", "Landroid/graphics/LinearGradient;", "createGIFWithText", "", "aniType", "completion", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, ImagesContract.URL, "makeParticleAni", "frameImage", "createAnimationFrames", "particles", "Lcom/wifiled/ipixels/ui/text/TextParticle;", "drawParticle", "canvas", "Landroid/graphics/Canvas;", "particle", PlayerFinal.PLAYER_POSITION, "Landroid/graphics/PointF;", "isDissolving", "currentFrame", "dissolveStart", "makeStayImage", "image", "setBackgroundPath", "setBoardPath", "updateAniWithCompletion", "onComplete", "drawClockFlipFrame", "frameIndex", "images", "drawWaveInFrame", "fullImage", "charInfo", "", "totalFrames", "bounceValueAt", "t", "draw3DRotateIn2", "progress", "draw3DRotateInY", "calculate3DRotatePositions", "charAnimationProgressForIndex", "index", "total", "globalProgress", "drawCharacter", "character", "finalX", "charWSize", "charHSize", "precutCharacters", "", "totalWidth", "calculateCharProgress", "charIndex", "fps", "getTextPositionInfo", "textArray", "drawGlowLayer", "x", "y", "baseColor", "neonTextPaintForProgress", "Landroid/graphics/Paint;", "calculateCharPositions", "getDrawTextPaint", "createPrinterFrames", "charList", "createBounceFrames", "createAnimatedFrameFromImage", "startX", "charWidths", "charBitmap", "createExpandShrinkFramesWithText", "renderExpandFrameWithImages", "charImages", "positions", "sharedBitmap", "sharedCanvas", "createExpandShrinkFramesWithTextNew", "getStartXWithText", "makeMiddleSplitWithImage", "generateAnimationFramesWithTopImage", "topImage", "bottomImage", "easeOut", "splitTextToArray", "splitTextToCharacters", "makeRotateScale", "createFrameWithImage", TypedValues.CycleType.S_WAVE_PHASE, "generateGIFWithFramesOptimized", "frames", "mergeImageInPlace", "reusedBitmap", "textImage", "backgroundImage", "borderImage", "renderFullGradientText", "alignment", "getLinearGradient2", "gradient", "paint", "getTextWidth", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreativeTextUtil {
    private final int ASSEMBLE_FRAMES;
    private final int DISSOLVE_FRAMES;
    private final int FPS;
    private float animationSpeed;
    private AnimationType animationType;
    private String backgroundGifPath;
    private List<Bitmap> bgFrames;
    private String boardGifPath;
    private int bounceCount;
    private Size canvasSize;
    private float charDelay;
    private final int[] color_new1;
    private final int[] color_new2;
    private final Context context;
    private DirectionType direction;
    private float dropHeight;
    private float durationPerChar;
    private Typeface font;
    private float fontSize;
    private GradientType gradientType;
    private float hideRatio;
    private Bitmap mBoardBitmap;
    private boolean mIsAntiAlias;
    private LinearGradient mLinearGradient;
    private float showRatio;
    private int stayDuration;
    private float stayRatio;
    private String text;
    private TextAlignment textAlignment;
    private int textColor;
    private int textGradients;

    /* compiled from: CreativeTextUtil.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[AnimationType.values().length];
            try {
                iArr[AnimationType.CLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnimationType.WAVE_IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnimationType.ROTATE_ENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AnimationType.ROTATE_ENTER_LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AnimationType.NEON.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[AnimationType.THREE_D_ROTATE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[AnimationType.THREE_D_Y_ROTATE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[AnimationType.ROTATE_SCALE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[AnimationType.PARTICLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[AnimationType.MIDDLE_SPLIT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[AnimationType.EXPAND_SHRINK.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[AnimationType.BOUNCE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[AnimationType.PRINTER.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DirectionType.values().length];
            try {
                iArr2[DirectionType.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[DirectionType.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[TextAlignment.values().length];
            try {
                iArr3[TextAlignment.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr3[TextAlignment.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr3[TextAlignment.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    private final float calculateCharProgress(int frameIndex, int charIndex, int fps) {
        float f = fps;
        int i = (int) (charIndex * 0.2f * f);
        int i2 = (int) (i + (f * 0.5f));
        if (frameIndex < i) {
            return 0.0f;
        }
        if (frameIndex >= i2) {
            return 1.0f;
        }
        return (frameIndex - i) / (i2 - i);
    }

    public CreativeTextUtil(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.color_new1 = new int[]{SupportMenu.CATEGORY_MASK, -16711936, -16776961, Color.parseColor("#eb1ce3"), Color.parseColor("#e2db19")};
        this.color_new2 = new int[]{InputDeviceCompat.SOURCE_ANY, -16711936, Color.parseColor("#00fbff"), Color.parseColor("#737bff"), Color.parseColor("#ff00ff"), Color.parseColor("#737bff"), Color.parseColor("#00fbff"), -16711936, InputDeviceCompat.SOURCE_ANY};
        this.canvasSize = new Size(256, 32);
        this.animationType = AnimationType.ROTATE_SCALE;
        this.text = "";
        this.boardGifPath = "";
        this.textAlignment = TextAlignment.CENTER;
        this.fontSize = 32.0f;
        this.animationSpeed = 25.0f;
        this.textColor = -1;
        this.textGradients = -1;
        this.direction = DirectionType.RIGHT;
        this.gradientType = GradientType.HORIZONTAL;
        this.stayDuration = 5;
        Typeface typeface = FontUtils.getTypeface(27);
        Intrinsics.checkNotNullExpressionValue(typeface, "getTypeface(...)");
        this.font = typeface;
        this.durationPerChar = 0.2f;
        this.FPS = 24;
        this.ASSEMBLE_FRAMES = 40;
        this.DISSOLVE_FRAMES = 40;
        this.dropHeight = -80.0f;
        this.bounceCount = 3;
        this.charDelay = 0.1f;
        this.showRatio = 0.2f;
        this.stayRatio = 0.6f;
        this.hideRatio = 0.2f;
        this.context = context;
        this.bgFrames = CollectionsKt.emptyList();
    }

    public final Size getCanvasSize() {
        return this.canvasSize;
    }

    public final void setCanvasSize(Size size) {
        Intrinsics.checkNotNullParameter(size, "<set-?>");
        this.canvasSize = size;
    }

    public final AnimationType getAnimationType() {
        return this.animationType;
    }

    public final void setAnimationType(AnimationType animationType) {
        Intrinsics.checkNotNullParameter(animationType, "<set-?>");
        this.animationType = animationType;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public final String getBackgroundGifPath() {
        return this.backgroundGifPath;
    }

    public final void setBackgroundGifPath(String str) {
        this.backgroundGifPath = str;
    }

    public final String getBoardGifPath() {
        return this.boardGifPath;
    }

    public final void setBoardGifPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.boardGifPath = str;
    }

    public final TextAlignment getTextAlignment() {
        return this.textAlignment;
    }

    public final void setTextAlignment(TextAlignment textAlignment) {
        Intrinsics.checkNotNullParameter(textAlignment, "<set-?>");
        this.textAlignment = textAlignment;
    }

    public final float getFontSize() {
        return this.fontSize;
    }

    public final void setFontSize(float f) {
        this.fontSize = f;
    }

    public final float getAnimationSpeed() {
        return this.animationSpeed;
    }

    public final void setAnimationSpeed(float f) {
        this.animationSpeed = f;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final void setTextColor(int i) {
        this.textColor = i;
    }

    public final int getTextGradients() {
        return this.textGradients;
    }

    public final void setTextGradients(int i) {
        this.textGradients = i;
    }

    public final DirectionType getDirection() {
        return this.direction;
    }

    public final void setDirection(DirectionType directionType) {
        Intrinsics.checkNotNullParameter(directionType, "<set-?>");
        this.direction = directionType;
    }

    public final GradientType getGradientType() {
        return this.gradientType;
    }

    public final void setGradientType(GradientType gradientType) {
        Intrinsics.checkNotNullParameter(gradientType, "<set-?>");
        this.gradientType = gradientType;
    }

    public final int getStayDuration() {
        return this.stayDuration;
    }

    public final void setStayDuration(int i) {
        this.stayDuration = i;
    }

    public final Typeface getFont() {
        return this.font;
    }

    public final void setFont(Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "<set-?>");
        this.font = typeface;
    }

    public final boolean getMIsAntiAlias() {
        return this.mIsAntiAlias;
    }

    public final void setMIsAntiAlias(boolean z) {
        this.mIsAntiAlias = z;
    }

    public final float getDurationPerChar() {
        return this.durationPerChar;
    }

    public final void setDurationPerChar(float f) {
        this.durationPerChar = f;
    }

    public final void createGIFWithText(String text, AnimationType aniType, String backgroundGifPath, String boardGifPath, int textColor, int textGradients, TextAlignment textAlignment, float fontSize, Size canvasSize, float animationSpeed, int stayDuration, final Function1<? super String, Unit> completion) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(aniType, "aniType");
        Intrinsics.checkNotNullParameter(boardGifPath, "boardGifPath");
        Intrinsics.checkNotNullParameter(textAlignment, "textAlignment");
        Intrinsics.checkNotNullParameter(canvasSize, "canvasSize");
        Intrinsics.checkNotNullParameter(completion, "completion");
        this.durationPerChar = 0.2f;
        this.text = text;
        this.canvasSize = canvasSize;
        this.fontSize = fontSize;
        this.textAlignment = textAlignment;
        this.animationSpeed = animationSpeed;
        this.textColor = textColor;
        this.textGradients = textGradients;
        this.backgroundGifPath = backgroundGifPath;
        this.stayDuration = stayDuration;
        this.animationType = aniType;
        this.boardGifPath = boardGifPath;
        this.dropHeight = -80.0f;
        this.bounceCount = 3;
        this.charDelay = 0.1f;
        this.showRatio = 0.2f;
        this.stayRatio = 0.6f;
        this.hideRatio = 0.2f;
        if (AppConfig.INSTANCE.getLedSize().get(1).intValue() < 32) {
            Typeface typeface = FontUtils.getTypeface(1);
            Intrinsics.checkNotNullExpressionValue(typeface, "getTypeface(...)");
            this.font = typeface;
            this.mIsAntiAlias = false;
        }
        this.gradientType = GradientType.DIAGONAL;
        String str = this.backgroundGifPath;
        if (str != null && str.length() != 0) {
            setBackgroundPath();
        }
        if (this.boardGifPath.length() > 0) {
            setBoardPath();
        }
        updateAniWithCompletion(new Function1() { // from class: com.wifiled.ipixels.ui.text.CreativeTextUtil$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit createGIFWithText$lambda$0;
                createGIFWithText$lambda$0 = CreativeTextUtil.createGIFWithText$lambda$0(Function1.this, (String) obj);
                return createGIFWithText$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createGIFWithText$lambda$0(Function1 function1, String base64) {
        Intrinsics.checkNotNullParameter(base64, "base64");
        function1.invoke(base64);
        return Unit.INSTANCE;
    }

    private final List<Bitmap> makeParticleAni(Bitmap frameImage) {
        int[] iArr;
        int i;
        ArrayList arrayList = new ArrayList();
        int width = frameImage.getWidth();
        int height = frameImage.getHeight();
        int[] iArr2 = new int[width * height];
        frameImage.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i2 = 0;
        while (i2 < height) {
            int i3 = 0;
            while (i3 < width) {
                int i4 = iArr2[(i2 * width) + i3];
                int i5 = (i4 >> 24) & 255;
                if (i5 > 0) {
                    TextParticle textParticle = new TextParticle(null, null, null, 0, 0, 0.0f, 0, 127, null);
                    textParticle.setTargetPosition(new PointF(i3, i2));
                    textParticle.setStartPosition(new PointF(Random.INSTANCE.nextInt(this.canvasSize.getWidth() + 100) - 50, -Random.INSTANCE.nextInt(this.canvasSize.getHeight())));
                    iArr = iArr2;
                    i = i2;
                    double nextDouble = Random.INSTANCE.nextDouble(AudioStats.AUDIO_AMPLITUDE_NONE, 6.283185307179586d);
                    float f = textParticle.getTargetPosition().x;
                    double cos = Math.cos(nextDouble);
                    double d = 100;
                    textParticle.setEndPosition(new PointF(f + ((float) (cos * d)), ((float) (Math.sin(nextDouble) * d)) + textParticle.getTargetPosition().y));
                    textParticle.setAssembleDelay(Random.INSTANCE.nextInt(this.ASSEMBLE_FRAMES / 2));
                    textParticle.setDissolveDelay(Random.INSTANCE.nextInt(this.DISSOLVE_FRAMES / 2));
                    textParticle.setVelocity((Random.INSTANCE.nextInt(100) / 200.0f) + 0.5f);
                    textParticle.setColor(Color.argb(i5, (i4 >> 16) & 255, (i4 >> 8) & 255, i4 & 255));
                    arrayList.add(textParticle);
                } else {
                    iArr = iArr2;
                    i = i2;
                }
                i3++;
                iArr2 = iArr;
                i2 = i;
            }
            i2++;
        }
        return createAnimationFrames(arrayList);
    }

    private final List<Bitmap> createAnimationFrames(List<TextParticle> particles) {
        Bitmap bitmap;
        PointF startPosition;
        PointF targetPosition;
        float f;
        int i;
        int i2;
        boolean z;
        CreativeTextUtil creativeTextUtil = this;
        float f2 = creativeTextUtil.animationSpeed;
        float f3 = 0.0f;
        if (f2 <= 0.0f) {
            f2 = 0.016f;
        }
        int ceil = (int) Math.ceil((creativeTextUtil.stayDuration * 1000) / f2);
        int i3 = creativeTextUtil.ASSEMBLE_FRAMES + ceil + creativeTextUtil.DISSOLVE_FRAMES;
        if (i3 > 1000) {
            LogUtils.w("Animation", "Warning: Total frames " + i3 + " exceeds limit 1000");
        }
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        while (i4 < i3) {
            Bitmap createBitmap = Bitmap.createBitmap(creativeTextUtil.canvasSize.getWidth(), creativeTextUtil.canvasSize.getHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            Canvas canvas = new Canvas(createBitmap);
            for (TextParticle textParticle : particles) {
                new PointF();
                new PointF();
                int i5 = creativeTextUtil.ASSEMBLE_FRAMES;
                float f4 = 1.0f;
                if (i4 >= i5) {
                    bitmap = createBitmap;
                    if (i4 < i5 + ceil) {
                        startPosition = textParticle.getTargetPosition();
                        targetPosition = textParticle.getTargetPosition();
                        f = f4;
                        i = ceil;
                        i2 = 0;
                        z = false;
                    } else {
                        int i6 = i5 + ceil;
                        if (i4 >= textParticle.getDissolveDelay() + i6) {
                            i = ceil;
                            float pow = (float) Math.pow(RangesKt.coerceIn(((i4 - i6) - textParticle.getDissolveDelay()) / (r9 * textParticle.getVelocity()), f3, 1.0f), 1.5f);
                            z = true;
                            i2 = i6;
                            startPosition = textParticle.getTargetPosition();
                            targetPosition = textParticle.getEndPosition();
                            f = pow;
                        } else {
                            createBitmap = bitmap;
                        }
                    }
                } else if (i4 >= textParticle.getAssembleDelay()) {
                    bitmap = createBitmap;
                    f4 = (float) Math.pow(RangesKt.coerceIn((i4 - textParticle.getAssembleDelay()) / ((creativeTextUtil.ASSEMBLE_FRAMES - textParticle.getAssembleDelay()) * textParticle.getVelocity()), f3, 1.0f), 1.5f);
                    startPosition = textParticle.getStartPosition();
                    targetPosition = textParticle.getTargetPosition();
                    f = f4;
                    i = ceil;
                    i2 = 0;
                    z = false;
                }
                creativeTextUtil = this;
                creativeTextUtil.drawParticle(canvas, textParticle, new PointF(startPosition.x + ((targetPosition.x - startPosition.x) * f), startPosition.y + ((targetPosition.y - startPosition.y) * f)), z, i4, i2);
                createBitmap = bitmap;
                ceil = i;
                f3 = 0.0f;
            }
            arrayList.add(createBitmap);
            i4++;
            f3 = 0.0f;
        }
        if (creativeTextUtil.stayDuration > 0) {
            arrayList.addAll(creativeTextUtil.makeStayImage((Bitmap) CollectionsKt.last((List) arrayList)));
        }
        return arrayList;
    }

    private final void drawParticle(Canvas canvas, TextParticle particle, PointF position, boolean isDissolving, int currentFrame, int dissolveStart) {
        float f = isDissolving ? 0.5f : 1.2f;
        int coerceIn = isDissolving ? (int) (255 * (1.0f - RangesKt.coerceIn((currentFrame - dissolveStart) / this.DISSOLVE_FRAMES, 0.0f, 1.0f))) : 255;
        Paint paint = new Paint();
        paint.setAntiAlias(this.mIsAntiAlias);
        paint.setColor(particle.getColor());
        paint.setAlpha(coerceIn);
        canvas.drawOval(position.x, position.y, position.x + f, position.y + f, paint);
    }

    private final List<Bitmap> makeStayImage(Bitmap image) {
        float f = ((this.stayDuration - 1) * 1000) / this.animationSpeed;
        ArrayList arrayList = new ArrayList();
        int i = (int) f;
        for (int i2 = 0; i2 < i; i2++) {
            Bitmap.Config config = image.getConfig();
            if (config != null) {
                Bitmap copy = image.copy(config, true);
                Intrinsics.checkNotNullExpressionValue(copy, "copy(...)");
                arrayList.add(copy);
            }
        }
        return arrayList;
    }

    private final void setBackgroundPath() {
        ArrayList<Bitmap> emptyList;
        try {
            byte[] assetFileDescriptorToByteArray = FileUtil.assetFileDescriptorToByteArray(this.context, this.backgroundGifPath);
            Intrinsics.checkNotNullExpressionValue(assetFileDescriptorToByteArray, "assetFileDescriptorToByteArray(...)");
            emptyList = GifCore.decodeGif(assetFileDescriptorToByteArray);
        } catch (NullPointerException unused) {
            emptyList = CollectionsKt.emptyList();
        }
        this.bgFrames = emptyList;
    }

    private final void setBoardPath() {
        byte[] assetFileDescriptorToByteArray = FileUtil.assetFileDescriptorToByteArray(this.context, this.boardGifPath);
        Intrinsics.checkNotNull(assetFileDescriptorToByteArray);
        this.mBoardBitmap = BitmapFactory.decodeByteArray(assetFileDescriptorToByteArray, 0, assetFileDescriptorToByteArray.length);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x03d7 A[LOOP:4: B:73:0x03d1->B:75:0x03d7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03e6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0179  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void updateAniWithCompletion(final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r26) {
        /*
            Method dump skipped, instructions count: 1052
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.text.CreativeTextUtil.updateAniWithCompletion(kotlin.jvm.functions.Function1):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit updateAniWithCompletion$lambda$6(Function1 function1, String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        function1.invoke(url);
        return Unit.INSTANCE;
    }

    private final void drawClockFlipFrame(Canvas canvas, int frameIndex, List<Bitmap> images, String text) {
        float startXWithText = getStartXWithText(text);
        int length = text.length();
        for (int i = 0; i < length; i++) {
            float textWidth = getTextWidth(String.valueOf(text.charAt(i)), this.fontSize);
            Bitmap bitmap = (Bitmap) CollectionsKt.getOrNull(images, i);
            if (bitmap != null) {
                int i2 = frameIndex - (i * 10);
                float width = (bitmap.getWidth() / 2.0f) + startXWithText;
                float height = (bitmap.getHeight() / 2.0f) + 0.0f;
                if (i2 >= 0 && i2 < 10) {
                    float f = i2 / 10;
                    float sin = ((float) Math.sin(f * 3.141592653589793d)) * 0.2f;
                    Camera camera = new Camera();
                    Matrix matrix = new Matrix();
                    camera.save();
                    if (f <= 0.5f) {
                        camera.rotateX(f * 180.0f * 1.8f);
                    } else {
                        camera.rotateX((1 - ((f - 0.5f) * 1.8f)) * 180.0f);
                    }
                    camera.getMatrix(matrix);
                    camera.restore();
                    float f2 = sin * 10.0f;
                    matrix.preTranslate(-width, (-height) - f2);
                    matrix.postTranslate(width, height + f2);
                    canvas.save();
                    canvas.concat(matrix);
                    canvas.drawBitmap(bitmap, startXWithText, 0.0f, (Paint) null);
                    canvas.restore();
                } else {
                    canvas.drawBitmap(bitmap, startXWithText, 0.0f, (Paint) null);
                }
                startXWithText += textWidth;
            }
        }
    }

    private final void drawWaveInFrame(Canvas canvas, int frameIndex, Bitmap fullImage, String text, Map<String, ? extends Object> charInfo, int totalFrames) {
        float f = 2.0f;
        float f2 = (-this.fontSize) * 2.0f;
        Object obj = charInfo.get("charsX");
        List list = obj instanceof List ? (List) obj : null;
        if (list == null) {
            return;
        }
        Object obj2 = charInfo.get("finalY");
        Float f3 = obj2 instanceof Float ? (Float) obj2 : null;
        if (f3 != null) {
            float floatValue = f3.floatValue();
            int length = text.length();
            float f4 = 0.0f;
            float f5 = 0.0f;
            int i = 0;
            while (i < length) {
                float textWidth = getTextWidth(String.valueOf(text.charAt(i)), this.fontSize);
                int ceil = (int) Math.ceil(f5);
                int height = (int) ((this.canvasSize.getHeight() - this.fontSize) / f);
                List list2 = list;
                int ceil2 = (int) Math.ceil(textWidth);
                int i2 = (int) this.fontSize;
                if (ceil + ceil2 > fullImage.getWidth()) {
                    ceil2 = fullImage.getWidth() - ceil;
                }
                Bitmap createBitmap = Bitmap.createBitmap(fullImage, ceil, height, ceil2, i2);
                Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
                canvas.drawBitmap(createBitmap, ((Number) list2.get(i)).floatValue(), (bounceValueAt$default(this, RangesKt.coerceIn(((frameIndex / 30.0f) - (this.charDelay * i)) / 1.3f, f4, 1.0f), 0, 2, null) * f2) + floatValue, (Paint) null);
                f5 += textWidth;
                createBitmap.recycle();
                i++;
                list = list2;
                f = 2.0f;
                f4 = 0.0f;
            }
        }
    }

    static /* synthetic */ float bounceValueAt$default(CreativeTextUtil creativeTextUtil, float f, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 3;
        }
        return creativeTextUtil.bounceValueAt(f, i);
    }

    private final float bounceValueAt(float t, int bounceCount) {
        if (t < 0.0f) {
            return 1.0f;
        }
        if (t >= 1.0f) {
            return 0.0f;
        }
        if (t < 0.3f) {
            return 1.0f - ((float) Math.pow(t / 0.3f, 2));
        }
        float f = ((t - 0.3f) / (1 - 0.3f)) * bounceCount;
        int floor = (int) Math.floor(f);
        return ((float) Math.pow(0.4f, floor + 1)) * ((float) Math.sin((f - floor) * 3.141592653589793d));
    }

    private final void draw3DRotateIn2(Canvas canvas, String text, Bitmap fullImage, float progress) {
        List<PointF> calculate3DRotatePositions = calculate3DRotatePositions(text);
        float f = this.fontSize;
        int length = text.length();
        float f2 = 0.0f;
        for (int i = 0; i < length; i++) {
            float textWidth = getTextWidth(String.valueOf(text.charAt(i)), this.fontSize);
            PointF pointF = calculate3DRotatePositions.get(i);
            Bitmap createBitmap = Bitmap.createBitmap(fullImage, (int) f2, (int) ((this.canvasSize.getHeight() / 2.0f) - (f / 2.0f)), (int) textWidth, (int) f);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            float charAnimationProgressForIndex = charAnimationProgressForIndex(i, text.length(), progress) * 360.0f;
            Matrix matrix = new Matrix();
            Camera camera = new Camera();
            camera.save();
            camera.rotateY(charAnimationProgressForIndex);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate((-textWidth) / 2.0f, (-f) / 2.0f);
            matrix.postTranslate(pointF.x, pointF.y);
            canvas.save();
            canvas.concat(matrix);
            canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
            canvas.restore();
            f2 += textWidth;
            createBitmap.recycle();
        }
    }

    public final void draw3DRotateInY(Canvas canvas, String text, Bitmap fullImage, float progress) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fullImage, "fullImage");
        List<PointF> calculate3DRotatePositions = calculate3DRotatePositions(text);
        float f = this.fontSize;
        int length = text.length();
        float f2 = 0.0f;
        for (int i = 0; i < length; i++) {
            float textWidth = getTextWidth(String.valueOf(text.charAt(i)), this.fontSize);
            PointF pointF = calculate3DRotatePositions.get(i);
            Bitmap createBitmap = Bitmap.createBitmap(fullImage, (int) f2, (int) ((this.canvasSize.getHeight() / 2.0f) - (f / 2.0f)), (int) textWidth, (int) f);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            float charAnimationProgressForIndex = charAnimationProgressForIndex(i, text.length(), progress) * 360.0f;
            Matrix matrix = new Matrix();
            Camera camera = new Camera();
            camera.save();
            camera.rotateX(charAnimationProgressForIndex);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate((-textWidth) / 2.0f, (-f) / 2.0f);
            matrix.postTranslate(pointF.x, pointF.y);
            canvas.save();
            canvas.concat(matrix);
            canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
            canvas.restore();
            f2 += textWidth;
            createBitmap.recycle();
        }
    }

    public final List<PointF> calculate3DRotatePositions(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        float startXWithText = getStartXWithText(text);
        ArrayList arrayList = new ArrayList();
        int length = text.length();
        float f = 0.0f;
        for (int i = 0; i < length; i++) {
            float textWidth = getTextWidth(String.valueOf(text.charAt(i)), this.fontSize);
            arrayList.add(new PointF(startXWithText + f + (textWidth / 2.0f), this.canvasSize.getHeight() / 2.0f));
            f += textWidth;
        }
        return arrayList;
    }

    private final float charAnimationProgressForIndex(int index, int total, float globalProgress) {
        return RangesKt.coerceIn(((globalProgress * (((total - 1) * 0.1f) + 1.0f)) - (index * 0.1f)) / 1.0f, 0.0f, 1.0f);
    }

    private final void drawCharacter(Canvas canvas, Bitmap character, float finalX, float charWSize, float charHSize, float progress, DirectionType direction) {
        if (progress < 0.0f || progress > 1.0f) {
            return;
        }
        int save = canvas.save();
        float height = this.canvasSize.getHeight() / 2.0f;
        float f = charWSize / 2.0f;
        float f2 = charHSize / 2.0f;
        int i = WhenMappings.$EnumSwitchMapping$1[direction.ordinal()];
        if (i == 1) {
            float f3 = -character.getWidth();
            float f4 = f3 + ((finalX - f3) * progress);
            float f5 = (1 - progress) * (-6.2831855f);
            Matrix matrix = new Matrix();
            matrix.postTranslate(-f, -f2);
            matrix.postRotate((float) Math.toDegrees(f5));
            matrix.postTranslate(f4 + f, height);
            canvas.drawBitmap(character, matrix, null);
        } else if (i == 2) {
            float width = this.canvasSize.getWidth() + charWSize;
            Matrix matrix2 = new Matrix();
            matrix2.postTranslate(-f, -f2);
            matrix2.postRotate((float) Math.toDegrees((1 - progress) * 12.566371f));
            matrix2.postTranslate((width - ((width - finalX) * progress)) - f, height);
            canvas.drawBitmap(character, matrix2, null);
        }
        canvas.restoreToCount(save);
    }

    private final List<Bitmap> precutCharacters(String text, Bitmap fullImage, float totalWidth) {
        ArrayList arrayList = new ArrayList();
        String str = text;
        ArrayList arrayList2 = new ArrayList(str.length());
        for (int i = 0; i < str.length(); i++) {
            arrayList2.add(Float.valueOf(getTextWidth(String.valueOf(str.charAt(i)), this.fontSize)));
        }
        ArrayList arrayList3 = arrayList2;
        float f = this.direction == DirectionType.LEFT ? totalWidth : 0.0f;
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            int length2 = this.direction == DirectionType.LEFT ? (text.length() - 1) - i2 : i2;
            float floatValue = ((Number) arrayList3.get(length2)).floatValue();
            if (this.direction == DirectionType.LEFT) {
                f -= floatValue;
            }
            int coerceAtLeast = RangesKt.coerceAtLeast((int) f, 0);
            float f2 = f + floatValue;
            int coerceAtMost = RangesKt.coerceAtMost((int) f2, fullImage.getWidth());
            int coerceAtMost2 = RangesKt.coerceAtMost(this.canvasSize.getHeight(), fullImage.getHeight());
            try {
                Bitmap createBitmap = Bitmap.createBitmap(fullImage, coerceAtLeast, 0, coerceAtMost - coerceAtLeast, coerceAtMost2);
                Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
                if (this.direction == DirectionType.LEFT) {
                    arrayList.add(0, createBitmap);
                } else {
                    arrayList.add(createBitmap);
                }
            } catch (Exception e) {
                LogUtils.e("precutCharacters", "截取字符失败: [" + length2 + "] 区域=(" + coerceAtLeast + ",0)-(" + coerceAtMost + "," + coerceAtMost2 + ")", e);
            }
            if (this.direction != DirectionType.LEFT) {
                f = f2;
            }
        }
        return arrayList;
    }

    private final List<List<Float>> getTextPositionInfo(List<? extends List<String>> textArray, float totalWidth, DirectionType direction) {
        float width;
        ArrayList arrayList = new ArrayList();
        for (List<String> list : textArray) {
            ArrayList arrayList2 = new ArrayList();
            int i = WhenMappings.$EnumSwitchMapping$2[this.textAlignment.ordinal()];
            if (i != 1) {
                width = 0.0f;
                if (i == 2) {
                    if (WhenMappings.$EnumSwitchMapping$1[direction.ordinal()] == 1) {
                        width = totalWidth;
                    }
                } else {
                    if (i != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    int i2 = WhenMappings.$EnumSwitchMapping$1[direction.ordinal()];
                    if (i2 == 1) {
                        width = this.canvasSize.getWidth();
                    } else if (i2 == 2) {
                        width = this.canvasSize.getWidth() - totalWidth;
                    }
                }
            } else if (direction == DirectionType.RIGHT) {
                width = (this.canvasSize.getWidth() - totalWidth) / 2.0f;
            } else {
                width = ((this.canvasSize.getWidth() - totalWidth) / 2.0f) + totalWidth;
            }
            if (direction == DirectionType.LEFT) {
                list = CollectionsKt.reversed(list);
            }
            for (String str : list) {
                Intrinsics.checkNotNullExpressionValue(str, "next(...)");
                float textWidth = getTextWidth(str, this.fontSize);
                width = direction == DirectionType.LEFT ? width - textWidth : width + textWidth;
                arrayList2.add(Float.valueOf(width));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private final void drawGlowLayer(Canvas canvas, String text, float x, float y, int baseColor) {
        Typeface typeface = FontUtils.getTypeface(1);
        for (int i = 0; i < 3; i++) {
            Paint paint = new Paint();
            paint.setAntiAlias(this.mIsAntiAlias);
            paint.setTextSize(48.0f);
            paint.setTypeface(typeface);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(0);
            paint.setShadowLayer(20.0f * ((i * 0.3f) + 1.0f), 0.0f, 0.0f, baseColor);
            canvas.drawText(text, x, y, paint);
        }
    }

    private final Paint neonTextPaintForProgress(float progress) {
        Paint drawTextPaint = getDrawTextPaint();
        int HSVToColor = Color.HSVToColor(new float[]{((progress * 2.0f) % 1.0f) * 360.0f, 1.0f, 1.0f});
        drawTextPaint.setColor(HSVToColor);
        drawTextPaint.setShadowLayer(20.0f, 0.0f, 0.0f, HSVToColor);
        return drawTextPaint;
    }

    public final Map<String, Object> calculateCharPositions(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ArrayList arrayList = new ArrayList();
        Paint drawTextPaint = getDrawTextPaint();
        ArrayList arrayList2 = new ArrayList();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            arrayList2.add(Float.valueOf(drawTextPaint.measureText(String.valueOf(text.charAt(i)))));
        }
        float startXWithText = getStartXWithText(text);
        float height = (this.canvasSize.getHeight() - this.fontSize) / 2.0f;
        Iterator it = arrayList2.iterator();
        float f = startXWithText;
        while (it.hasNext()) {
            float floatValue = ((Number) it.next()).floatValue();
            arrayList.add(Float.valueOf(f));
            f += floatValue;
        }
        return MapsKt.mapOf(TuplesKt.to("baseX", Float.valueOf(startXWithText)), TuplesKt.to("charsX", arrayList), TuplesKt.to("finalY", Float.valueOf(height)));
    }

    private final Paint getDrawTextPaint() {
        Paint paint = new Paint();
        paint.setTypeface(this.font);
        paint.setTextSize(this.fontSize);
        paint.setColor(this.textColor);
        paint.setAntiAlias(this.mIsAntiAlias);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    private final List<Bitmap> createPrinterFrames(String text, List<Bitmap> charList) {
        ArrayList arrayList = new ArrayList();
        float startXWithText = getStartXWithText(text);
        String str = text;
        ArrayList arrayList2 = new ArrayList(str.length());
        for (int i = 0; i < str.length(); i++) {
            arrayList2.add(Float.valueOf(getTextWidth(String.valueOf(str.charAt(i)), this.fontSize)));
        }
        ArrayList arrayList3 = arrayList2;
        Bitmap createBitmap = Bitmap.createBitmap(this.canvasSize.getWidth(), this.canvasSize.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        int i2 = 1;
        Paint paint = new Paint(1);
        int size = charList.size();
        if (1 <= size) {
            while (true) {
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                float f = startXWithText;
                for (int i3 = 0; i3 < i2; i3++) {
                    canvas.drawBitmap(charList.get(i3), f, 0.0f, paint);
                    f += ((Number) arrayList3.get(i3)).floatValue();
                }
                Bitmap copy = createBitmap.copy(Bitmap.Config.ARGB_8888, false);
                Intrinsics.checkNotNull(copy);
                arrayList.add(copy);
                if (i2 == size) {
                    break;
                }
                i2++;
            }
        }
        if (this.stayDuration > 0) {
            ArrayList arrayList4 = arrayList;
            if (!arrayList4.isEmpty()) {
                CollectionsKt.addAll(arrayList4, makeStayImage((Bitmap) CollectionsKt.last((List) arrayList)));
            }
        }
        createBitmap.recycle();
        Iterator<Bitmap> it = charList.iterator();
        while (it.hasNext()) {
            it.next().recycle();
        }
        return arrayList;
    }

    private final List<Bitmap> createBounceFrames(String text, List<Bitmap> charList) {
        List<Bitmap> list;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            arrayList2.add(Float.valueOf(getTextWidth(String.valueOf(text.charAt(i)), this.fontSize)));
        }
        float startXWithText = getStartXWithText(text);
        int length2 = text.length();
        int i2 = 0;
        while (i2 < length2) {
            int i3 = 0;
            while (true) {
                list = charList;
                arrayList.add(createAnimatedFrameFromImage(i2, i3 / 15, startXWithText, arrayList2, list));
                if (i3 != 15) {
                    i3++;
                    charList = list;
                }
            }
            i2++;
            charList = list;
        }
        Iterator<Bitmap> it = charList.iterator();
        while (it.hasNext()) {
            it.next().recycle();
        }
        if (this.stayDuration > 0) {
            CollectionsKt.addAll(arrayList, makeStayImage((Bitmap) CollectionsKt.last((List) arrayList)));
        }
        return arrayList;
    }

    private final Bitmap createAnimatedFrameFromImage(int charIndex, float progress, float startX, List<Float> charWidths, List<Bitmap> charBitmap) {
        float f;
        int i = charIndex;
        int width = this.canvasSize.getWidth();
        int height = this.canvasSize.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        int size = charBitmap.size();
        int i2 = 0;
        float f2 = startX;
        while (i2 < size && i2 <= i) {
            int width2 = charBitmap.get(i2).getWidth();
            Bitmap bitmap = charBitmap.get(i2);
            float f3 = (width2 / 2.0f) + f2;
            float f4 = height;
            float f5 = f4 / 2.0f;
            canvas.save();
            if (i2 == i) {
                f = 2.0f;
                double d = progress * 3.141592653589793d;
                float f6 = -(f4 * 0.8f * ((float) Math.sin(d)));
                float sin = ((float) Math.sin(d)) * 0.5235988f;
                canvas.translate(f3, f5 + f6);
                canvas.rotate((float) Math.toDegrees(sin));
                canvas.translate((-width2) / 2.0f, (-height) / 2.0f);
            } else {
                f = 2.0f;
                canvas.translate(f2, 0.0f);
            }
            canvas.drawBitmap(bitmap, 0.0f, (height - bitmap.getHeight()) / f, (Paint) null);
            canvas.restore();
            f2 += charWidths.get(i2).floatValue();
            i2++;
            i = charIndex;
        }
        return createBitmap;
    }

    private final List<Bitmap> createExpandShrinkFramesWithText(String text, List<Bitmap> charList) {
        List<Bitmap> list;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        float startXWithText = getStartXWithText(text);
        for (Bitmap bitmap : charList) {
            arrayList2.add(Float.valueOf(startXWithText));
            startXWithText += bitmap.getWidth();
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.canvasSize.getWidth(), this.canvasSize.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        int i = 0;
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        int i2 = (int) ((this.stayDuration * 1000) / this.animationSpeed);
        if (i2 >= 0) {
            while (true) {
                list = charList;
                arrayList.add(renderExpandFrameWithImages(list, arrayList2, i / i2, createBitmap, canvas));
                if (i == i2) {
                    break;
                }
                i++;
                charList = list;
            }
        } else {
            list = charList;
        }
        for (int i3 = i2; -1 < i3; i3--) {
            arrayList.add(renderExpandFrameWithImages(list, arrayList2, i3 / i2, createBitmap, canvas));
        }
        Iterator<Bitmap> it = list.iterator();
        while (it.hasNext()) {
            it.next().recycle();
        }
        return arrayList;
    }

    private final Bitmap renderExpandFrameWithImages(List<Bitmap> charImages, List<Float> positions, float progress, Bitmap sharedBitmap, Canvas sharedCanvas) {
        sharedCanvas.setBitmap(sharedBitmap);
        sharedCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint(1);
        float width = this.canvasSize.getWidth() * 2 * progress;
        int size = charImages.size();
        for (int i = 0; i < size; i++) {
            float floatValue = positions.get(i).floatValue();
            if (floatValue < width) {
                sharedCanvas.drawBitmap(charImages.get(i), floatValue, (this.canvasSize.getHeight() - r5.getHeight()) / 2.0f, paint);
            }
        }
        Bitmap copy = sharedBitmap.copy(Bitmap.Config.ARGB_8888, false);
        Intrinsics.checkNotNullExpressionValue(copy, "copy(...)");
        return copy;
    }

    private final List<Bitmap> createExpandShrinkFramesWithTextNew(String text, List<Bitmap> charList) {
        int i;
        float f;
        List<Bitmap> list;
        ArrayList arrayList = new ArrayList();
        float startXWithText = getStartXWithText(text);
        List<Bitmap> list2 = charList;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(((Bitmap) it.next()).getWidth()));
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            int intValue = ((Number) it2.next()).intValue();
            arrayList3.add(Float.valueOf(startXWithText));
            startXWithText += intValue;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.canvasSize.getWidth(), this.canvasSize.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        float height = (this.canvasSize.getHeight() - this.fontSize) / 2.0f;
        int i2 = 0;
        while (true) {
            f = 20;
            list = charList;
            arrayList.add(createExpandShrinkFramesWithTextNew$renderFrame(canvas, this, list, arrayList3, height, paint, createBitmap, i2 / f));
            if (i2 == 20) {
                break;
            }
            i2++;
            charList = list;
        }
        for (i = 20; -1 < i; i--) {
            arrayList.add(createExpandShrinkFramesWithTextNew$renderFrame(canvas, this, list, arrayList3, height, paint, createBitmap, i / f));
        }
        createBitmap.recycle();
        return arrayList;
    }

    private static final Bitmap createExpandShrinkFramesWithTextNew$renderFrame(Canvas canvas, CreativeTextUtil creativeTextUtil, List<Bitmap> list, List<Float> list2, float f, Paint paint, Bitmap bitmap, float f2) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        float width = creativeTextUtil.canvasSize.getWidth() / 2.0f;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Bitmap bitmap2 = list.get(i);
            float floatValue = list2.get(i).floatValue();
            canvas.drawBitmap(bitmap2, floatValue + (((((float) bitmap2.getWidth()) / 2.0f) + floatValue < width ? -1 : 1) * Math.abs(((bitmap2.getWidth() / 2.0f) + floatValue) - width) * (1.0f - f2)), f, paint);
        }
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        Intrinsics.checkNotNullExpressionValue(copy, "copy(...)");
        return copy;
    }

    private final float getStartXWithText(String text) {
        float textWidth = getTextWidth(text, this.fontSize);
        int i = WhenMappings.$EnumSwitchMapping$2[this.textAlignment.ordinal()];
        if (i == 1) {
            return (this.canvasSize.getWidth() - textWidth) / 2.0f;
        }
        if (i == 2) {
            return 0.0f;
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        return this.canvasSize.getWidth() - textWidth;
    }

    private final List<Bitmap> makeMiddleSplitWithImage(Bitmap image, float totalWidth) {
        ArrayList arrayList = new ArrayList();
        int width = (int) ((this.canvasSize.getWidth() - totalWidth) / 2.0f);
        float height = this.canvasSize.getHeight();
        float f = this.fontSize;
        int i = (int) ((height - f) / 2.0f);
        int i2 = (int) (f / 2.0f);
        int coerceAtMost = RangesKt.coerceAtMost((int) totalWidth, image.getWidth() - width);
        int coerceAtMost2 = RangesKt.coerceAtMost((int) this.fontSize, image.getHeight() - i);
        if (coerceAtMost > 0 && coerceAtMost2 > 0) {
            Bitmap createBitmap = Bitmap.createBitmap(image, width, i, coerceAtMost, RangesKt.coerceAtMost(i2, image.getHeight() - i));
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            int i3 = i + i2;
            int i4 = coerceAtMost2 - i2;
            Bitmap createBitmap2 = Bitmap.createBitmap(image, width, i3, coerceAtMost, RangesKt.coerceAtLeast(i4, 0));
            Intrinsics.checkNotNullExpressionValue(createBitmap2, "createBitmap(...)");
            image.recycle();
            ArrayList arrayList2 = arrayList;
            CollectionsKt.addAll(arrayList2, generateAnimationFramesWithTopImage(createBitmap, createBitmap2, totalWidth));
            if (!arrayList2.isEmpty()) {
                for (int i5 = 0; i5 < 20; i5++) {
                    Bitmap.Config config = ((Bitmap) CollectionsKt.last((List) arrayList)).getConfig();
                    if (config != null) {
                        Bitmap copy = ((Bitmap) CollectionsKt.last((List) arrayList)).copy(config, true);
                        Intrinsics.checkNotNullExpressionValue(copy, "copy(...)");
                        arrayList.add(copy);
                    }
                }
            }
        }
        return arrayList;
    }

    private final List<Bitmap> generateAnimationFramesWithTopImage(Bitmap topImage, Bitmap bottomImage, float totalWidth) {
        int width = this.canvasSize.getWidth();
        int height = this.canvasSize.getHeight();
        float f = width;
        float f2 = (f - totalWidth) / 2.0f;
        float f3 = this.fontSize;
        float f4 = (height - f3) / 2.0f;
        float f5 = f4 + (f3 / 2.0f);
        int i = (int) (24 * 0.8f);
        float f6 = (this.stayDuration * 1000) / this.animationSpeed;
        ArrayList arrayList = new ArrayList();
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        for (int i2 = 0; i2 < i; i2++) {
            float easeOut = easeOut(i2 / i);
            arrayList.add(generateAnimationFramesWithTopImage$makeFrame(canvas, topImage, f4, paint, bottomImage, f5, createBitmap, (-totalWidth) + ((f2 + totalWidth) * easeOut), f - (easeOut * (f - f2))));
        }
        int i3 = (int) f6;
        int i4 = 0;
        while (i4 < i3) {
            ArrayList arrayList2 = arrayList;
            Canvas canvas2 = canvas;
            arrayList2.add(generateAnimationFramesWithTopImage$makeFrame(canvas2, topImage, f4, paint, bottomImage, f5, createBitmap, f2, f2));
            i4++;
            canvas = canvas2;
            arrayList = arrayList2;
        }
        ArrayList arrayList3 = arrayList;
        Canvas canvas3 = canvas;
        for (int i5 = 0; i5 < i; i5++) {
            float f7 = i5 / i;
            float f8 = f7 * f7 * f7;
            arrayList3.add(generateAnimationFramesWithTopImage$makeFrame(canvas3, topImage, f4, paint, bottomImage, f5, createBitmap, f2 - (((2 * totalWidth) + f2) * f8), f2 + (f8 * ((f - f2) + totalWidth))));
        }
        createBitmap.recycle();
        topImage.recycle();
        bottomImage.recycle();
        return arrayList3;
    }

    private static final Bitmap generateAnimationFramesWithTopImage$makeFrame(Canvas canvas, Bitmap bitmap, float f, Paint paint, Bitmap bitmap2, float f2, Bitmap bitmap3, float f3, float f4) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bitmap, f3, f, paint);
        canvas.drawBitmap(bitmap2, f4, f2, paint);
        Bitmap copy = bitmap3.copy(Bitmap.Config.ARGB_8888, false);
        Intrinsics.checkNotNullExpressionValue(copy, "copy(...)");
        return copy;
    }

    private final float easeOut(float t) {
        return 1 - ((float) Math.pow(r0 - t, 3));
    }

    private final List<String> splitTextToArray() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        LogUtils.vTag("ruis", "splitTextToArray text ---" + this.text);
        int length = this.text.length();
        float f = 0.0f;
        for (int i = 0; i < length; i++) {
            if (arrayList.size() > 2) {
                return arrayList;
            }
            String valueOf = String.valueOf(this.text.charAt(i));
            float textWidth = getTextWidth(valueOf, this.fontSize);
            String str = " widthSum: " + f;
            f += textWidth;
            LogUtils.dTag("ruis", "char:" + valueOf, "char: " + textWidth, str, "widthSum + charWidth: " + f);
            if (f == this.canvasSize.getWidth()) {
                sb.append(valueOf);
                String sb2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                arrayList.add(sb2);
                StringsKt.clear(sb);
                f = 0.0f;
            } else if (f > this.canvasSize.getWidth()) {
                String sb3 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(sb3, "toString(...)");
                arrayList.add(sb3);
                StringsKt.clear(sb);
                sb.append(valueOf);
                float f2 = textWidth + 0.0f;
                if (i == this.text.length() - 1) {
                    String sb4 = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(sb4, "toString(...)");
                    arrayList.add(sb4);
                }
                f = f2;
            } else {
                sb.append(valueOf);
                if (i == this.text.length() - 1) {
                    String sb5 = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(sb5, "toString(...)");
                    arrayList.add(sb5);
                }
            }
        }
        LogUtils.d("splitTextToArray", "分屏个数: " + arrayList.size() + ", 内容: " + arrayList);
        return arrayList;
    }

    private final List<List<String>> splitTextToCharacters() {
        List list;
        List list2;
        List list3;
        List list4;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int length = this.text.length();
        float f = 0.0f;
        for (int i = 0; i < length; i++) {
            if (arrayList.size() > 2) {
                return arrayList;
            }
            String valueOf = String.valueOf(this.text.charAt(i));
            float textWidth = getTextWidth(valueOf, this.fontSize);
            f += textWidth;
            if (f == this.canvasSize.getWidth()) {
                arrayList2.add(valueOf);
                if (this.direction == DirectionType.LEFT) {
                    list4 = CollectionsKt.reversed(arrayList2);
                } else {
                    list4 = CollectionsKt.toList(arrayList2);
                }
                arrayList.add(list4);
                arrayList2.clear();
                f = 0.0f;
            } else if (f >= this.canvasSize.getWidth()) {
                if (this.direction == DirectionType.LEFT) {
                    list2 = CollectionsKt.reversed(arrayList2);
                } else {
                    list2 = CollectionsKt.toList(arrayList2);
                }
                arrayList.add(list2);
                arrayList2.clear();
                arrayList2.add(valueOf);
                float f2 = textWidth + 0.0f;
                if (i == this.text.length() - 1) {
                    if (this.direction == DirectionType.LEFT) {
                        list3 = CollectionsKt.reversed(arrayList2);
                    } else {
                        list3 = CollectionsKt.toList(arrayList2);
                    }
                    arrayList.add(list3);
                }
                f = f2;
            } else {
                arrayList2.add(valueOf);
                if (i == this.text.length() - 1) {
                    if (this.direction == DirectionType.LEFT) {
                        list = CollectionsKt.reversed(arrayList2);
                    } else {
                        list = CollectionsKt.toList(arrayList2);
                    }
                    arrayList.add(list);
                }
            }
        }
        LogUtils.d("splitText", "分屏个数: " + arrayList.size() + ", 内容: " + arrayList);
        return arrayList;
    }

    public final List<Bitmap> makeRotateScale(int totalFrames, Bitmap image) {
        Intrinsics.checkNotNullParameter(image, "image");
        ArrayList arrayList = new ArrayList();
        float f = totalFrames;
        int i = (int) (this.showRatio * f);
        int i2 = (int) (f * this.hideRatio);
        for (int i3 = 0; i3 < i; i3++) {
            arrayList.add(createFrameWithImage(image, i3 / i, 0));
        }
        if (this.stayDuration > 0) {
            int i4 = (int) ((r1 * 1000) / this.animationSpeed);
            for (int i5 = 0; i5 < i4; i5++) {
                arrayList.add(createFrameWithImage(image, 1.0f, 1));
            }
        }
        for (int i6 = 0; i6 < i2; i6++) {
            arrayList.add(createFrameWithImage(image, 1.0f - (i6 / i2), 2));
        }
        image.recycle();
        return arrayList;
    }

    private final Bitmap createFrameWithImage(Bitmap image, float progress, int phase) {
        Bitmap createBitmap = Bitmap.createBitmap(this.canvasSize.getWidth(), this.canvasSize.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        float width = this.canvasSize.getWidth() / 2.0f;
        float height = this.canvasSize.getHeight() / 2.0f;
        float width2 = image.getWidth();
        float height2 = image.getHeight();
        Matrix matrix = new Matrix();
        matrix.postTranslate((-width2) / 2.0f, (-height2) / 2.0f);
        if (phase == 0) {
            float f = (0.8f * progress) + 0.2f;
            matrix.postScale(f, f);
            matrix.postRotate(progress * 360.0f);
        } else if (phase == 2) {
            matrix.postScale(progress, progress);
            matrix.postRotate((1 - progress) * 360.0f);
        }
        matrix.postTranslate(width, height);
        canvas.drawBitmap(image, matrix, null);
        return createBitmap;
    }

    private final void generateGIFWithFramesOptimized(List<Bitmap> frames, Function1<? super String, Unit> onComplete) {
        FileOutputStream fileOutputStream;
        Throwable th;
        int i;
        Bitmap bitmap;
        Bitmap bitmap2;
        String absolutePath = new File(FileUtils.getExternalFilePath(App.INSTANCE.getContext(), "Gif/" + PreferenceSettings.INSTANCE.getLedType()), System.currentTimeMillis() + ".gif").getAbsolutePath();
        int intValue = AppConfig.INSTANCE.getLedSize().get(0).intValue();
        int intValue2 = AppConfig.INSTANCE.getLedSize().get(1).intValue();
        int[] iArr = new int[intValue * intValue2];
        com.squareup.gifencoder.Color[][] colorArr = new com.squareup.gifencoder.Color[intValue2][];
        for (int i2 = 0; i2 < intValue2; i2++) {
            colorArr[i2] = new com.squareup.gifencoder.Color[intValue];
        }
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(absolutePath);
            try {
                GifEncoder gifEncoder = new GifEncoder(fileOutputStream2, intValue, intValue2, 0);
                ImageOptions imageOptions = new ImageOptions();
                imageOptions.setDelay((long) this.animationSpeed, TimeUnit.MILLISECONDS);
                Bitmap createBitmap = Bitmap.createBitmap(intValue, intValue2, Bitmap.Config.ARGB_8888);
                Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
                Canvas canvas = new Canvas();
                int i3 = 0;
                for (Bitmap bitmap3 : frames) {
                    Bitmap bitmap4 = this.mBoardBitmap;
                    if (this.bgFrames.isEmpty()) {
                        i = i3;
                        bitmap = bitmap3;
                        bitmap2 = null;
                    } else {
                        try {
                            List<Bitmap> list = this.bgFrames;
                            Bitmap bitmap5 = list.get(i3 % list.size());
                            i = i3 + 1;
                            bitmap = bitmap3;
                            bitmap2 = bitmap5;
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = fileOutputStream2;
                            try {
                                throw th;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(fileOutputStream, th);
                                throw th3;
                            }
                        }
                    }
                    Bitmap mergeImageInPlace = mergeImageInPlace(canvas, createBitmap, bitmap, bitmap2, bitmap4);
                    GifEncoder gifEncoder2 = gifEncoder;
                    ImageOptions imageOptions2 = imageOptions;
                    com.squareup.gifencoder.Color[][] colorArr2 = colorArr;
                    fileOutputStream = fileOutputStream2;
                    int i4 = intValue2;
                    try {
                        mergeImageInPlace.getPixels(iArr, 0, intValue, 0, 0, intValue, i4);
                        for (int i5 = 0; i5 < i4; i5++) {
                            for (int i6 = 0; i6 < intValue; i6++) {
                                colorArr2[i5][i6] = com.squareup.gifencoder.Color.fromRgbInt(iArr[(i5 * intValue) + i6]);
                            }
                        }
                        gifEncoder2.addImage(Image.fromColors(colorArr2), imageOptions2);
                        bitmap.recycle();
                        imageOptions = imageOptions2;
                        fileOutputStream2 = fileOutputStream;
                        gifEncoder = gifEncoder2;
                        i3 = i;
                        intValue2 = i4;
                        colorArr = colorArr2;
                    } catch (Throwable th4) {
                        th = th4;
                        th = th;
                        throw th;
                    }
                }
                fileOutputStream = fileOutputStream2;
                GifEncoder gifEncoder3 = gifEncoder;
                Iterator<T> it = this.bgFrames.iterator();
                while (it.hasNext()) {
                    ((Bitmap) it.next()).recycle();
                }
                Bitmap bitmap6 = this.mBoardBitmap;
                if (bitmap6 != null) {
                    bitmap6.recycle();
                }
                createBitmap.recycle();
                gifEncoder3.finishEncoding();
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = fileOutputStream2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intrinsics.checkNotNull(absolutePath);
        onComplete.invoke(absolutePath);
    }

    private final Bitmap mergeImageInPlace(Canvas canvas, Bitmap reusedBitmap, Bitmap textImage, Bitmap backgroundImage, Bitmap borderImage) {
        canvas.setBitmap(reusedBitmap);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Rect rect = new Rect(0, 0, reusedBitmap.getWidth(), reusedBitmap.getHeight());
        if (backgroundImage != null) {
            canvas.drawBitmap(backgroundImage, (Rect) null, rect, (Paint) null);
        }
        if (borderImage != null) {
            canvas.drawBitmap(borderImage, (Rect) null, rect, (Paint) null);
        }
        canvas.drawBitmap(textImage, (Rect) null, rect, (Paint) null);
        return reusedBitmap;
    }

    private final Bitmap renderFullGradientText(String text, TextAlignment alignment) {
        float width;
        Bitmap createBitmap = Bitmap.createBitmap(this.canvasSize.getWidth(), this.canvasSize.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Paint drawTextPaint = getDrawTextPaint();
        float textWidth = getTextWidth(text, this.fontSize);
        int i = WhenMappings.$EnumSwitchMapping$2[alignment.ordinal()];
        if (i != 1) {
            width = 0.0f;
            if (i != 2) {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                width = RangesKt.coerceAtLeast(this.canvasSize.getWidth() - textWidth, 0.0f);
            }
        } else {
            width = (this.canvasSize.getWidth() - textWidth) / 2.0f;
        }
        float height = (this.canvasSize.getHeight() / 2.0f) - ((drawTextPaint.descent() + drawTextPaint.ascent()) / 2.0f);
        int i2 = this.textGradients;
        if (i2 != -1) {
            getLinearGradient2(i2, drawTextPaint, textWidth);
        }
        canvas.drawText(text, width, height, drawTextPaint);
        return createBitmap;
    }

    private final void getLinearGradient2(int gradient, Paint paint, float totalWidth) {
        LogUtils.vTag("ruis", "getLinearGradient2 ---- " + gradient);
        if (gradient == 0) {
            LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, totalWidth, 0.0f, this.color_new1, (float[]) null, Shader.TileMode.CLAMP);
            this.mLinearGradient = linearGradient;
            paint.setShader(linearGradient);
        } else if (gradient == 1) {
            this.mLinearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, AppConfig.INSTANCE.getLedSize().get(1).intValue(), this.color_new1, (float[]) null, Shader.TileMode.CLAMP);
        } else if (gradient == 2) {
            this.mLinearGradient = new LinearGradient(0.0f, 0.0f, AppConfig.INSTANCE.getLedSize().get(1).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), this.color_new1, (float[]) null, Shader.TileMode.REPEAT);
        } else if (gradient == 3) {
            this.mLinearGradient = new LinearGradient(0.0f, 0.0f, totalWidth, 0.0f, this.color_new2, (float[]) null, Shader.TileMode.CLAMP);
        } else if (gradient == 4) {
            this.mLinearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, AppConfig.INSTANCE.getLedSize().get(1).intValue(), this.color_new2, (float[]) null, Shader.TileMode.CLAMP);
        } else if (gradient == 5) {
            this.mLinearGradient = new LinearGradient(0.0f, 0.0f, AppConfig.INSTANCE.getLedSize().get(1).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), this.color_new2, (float[]) null, Shader.TileMode.MIRROR);
        }
        paint.setShader(this.mLinearGradient);
    }

    private final float getTextWidth(String text, float fontSize) {
        Paint paint = new Paint();
        paint.setTypeface(this.font);
        paint.setColor(this.textColor);
        paint.setAntiAlias(this.mIsAntiAlias);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(fontSize);
        return paint.measureText(text);
    }
}
