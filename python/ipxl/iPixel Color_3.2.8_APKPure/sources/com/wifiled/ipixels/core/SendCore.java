package com.wifiled.ipixels.core;

import android.graphics.Color;
import android.util.Log;
import androidx.autofill.HintConstants;
import com.alibaba.fastjson2.JSONB;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.baselib.utils.DateUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.BleRequestImpl;
import com.wifiled.blelibrary.ble.utils.ByteUtils;
import com.wifiled.blelibrary.ble.utils.CrcUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.core.send.WifiSend;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.channel.ChannelListActivity;
import com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity;
import com.wifiled.ipixels.ui.diy.CPaintRunTimeItem;
import com.wifiled.ipixels.ui.diy.DiyImageMoveType;
import com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity;
import com.wifiled.ipixels.ui.subzone.SubzoneData;
import com.wifiled.ipixels.utils.DateUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: SendCore.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010!\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001lB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u0005H\u0002J>\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020!J>\u0010\"\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020!J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0018\u0010&\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J\u0010\u0010(\u001a\u00020'2\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u001a\u0010)\u001a\u00020'2\u0006\u0010\u0019\u001a\u00020\u00172\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+JG\u0010,\u001a\u00020'2\b\b\u0002\u0010-\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!H\u0007JG\u00103\u001a\u00020'2\b\b\u0002\u0010-\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!H\u0007J\u0018\u00104\u001a\u00020'2\u0006\u00105\u001a\u0002062\b\u0010*\u001a\u0004\u0018\u00010+JC\u00107\u001a\u00020'2\u0006\u0010-\u001a\u00020%2\u0006\u00108\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!JC\u00109\u001a\u00020'2\u0006\u0010-\u001a\u00020%2\u0006\u00108\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!J\u0018\u0010:\u001a\u00020'2\u0006\u0010;\u001a\u00020\u00172\b\u0010*\u001a\u0004\u0018\u00010+J\u001a\u0010<\u001a\u00020'2\u0006\u0010=\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u0010>\u001a\u00020'2\b\u0010*\u001a\u0004\u0018\u00010+J1\u0010?\u001a\u00020'2\u0006\u0010-\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b2H\u0016J/\u0010@\u001a\u00020'2\u0006\u0010-\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b2J;\u0010A\u001a\u00020'2\b\b\u0002\u0010.\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!J;\u0010B\u001a\u00020'2\b\b\u0002\u0010.\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!J;\u0010C\u001a\u00020'2\b\b\u0002\u0010.\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!JQ\u0010D\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020%2\b\b\u0002\u0010F\u001a\u00020\u00172\b\u0010G\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!H\u0007J!\u0010H\u001a\u00020'2\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b2H\u0007J'\u0010I\u001a\u00020'2\u0006\u0010J\u001a\u00020!2\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b2J'\u0010K\u001a\u00020'2\u0006\u0010J\u001a\u00020!2\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b2J;\u0010L\u001a\u00020'2\u0006\u0010M\u001a\u00020\u00052\b\u0010N\u001a\u0004\u0018\u00010\u00172\u0006\u0010O\u001a\u00020\u00052\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b2H\u0007JQ\u0010T\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020%2\b\b\u0002\u0010F\u001a\u00020\u00172\b\u0010G\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!H\u0007JQ\u0010]\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020%2\b\b\u0002\u0010F\u001a\u00020\u00172\b\b\u0002\u0010^\u001a\u00020\u00172\b\b\u0002\u0010_\u001a\u00020\u00172\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020'00¢\u0006\u0002\b22\b\b\u0002\u0010 \u001a\u00020!J\u0006\u0010`\u001a\u00020'J,\u0010a\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020%2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020e2\b\b\u0002\u0010 \u001a\u00020!H\u0007J,\u0010f\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020%2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020e2\b\b\u0002\u0010 \u001a\u00020!H\u0007J,\u0010g\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020%2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020e2\b\b\u0002\u0010 \u001a\u00020!H\u0007J@\u0010h\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020%2\b\u0010G\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010d\u001a\u00020e2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010i\u001a\u00020\u0005H\u0007J@\u0010j\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020%2\b\u0010G\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010d\u001a\u00020e2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010i\u001a\u00020\u0005H\u0007J@\u0010k\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020%2\b\u0010G\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010d\u001a\u00020e2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010i\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00170Q¢\u0006\b\n\u0000\u001a\u0004\bR\u0010SR\u001a\u0010U\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001a\u0010Z\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010W\"\u0004\b\\\u0010Y¨\u0006m"}, d2 = {"Lcom/wifiled/ipixels/core/SendCore;", "Lcom/wifiled/ipixels/core/send/WifiSend;", "<init>", "()V", "HEADER_LENGTH", "", "HEADER_TEXT_LENGTH", "TAG", "", "TYPE_CAMERA", "TYPE_VIDEO", "TYPE_IMAGE", "TYPE_GIF", "TYPE_TEXT", "TYPE_DIY_IMAGE", "TYPE_DIY_IMAGE_UNREDO", "TYPE_TEM", "FIRST_SEND", "CONTINUE_SEND", "CHECK_SPACE", "NO_CHECK_SPACE", "tProcess", "payload", "", "type", "data", "totalData", "option", "totalLength", "bright", "payloadChannel", "byteArrTemp", "isBulkSend", "", "payloadTemChannel", "getDataType", "shouldCrc", "", "changeLight", "", "sendCameraData", "sendUnRedoImageData", "callback", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "sendChannelImageData", "is_down", "serial", "callbackBuilder", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "sendChannelImageData2", "sendDiyImageData", "item", "Lcom/wifiled/ipixels/ui/diy/CPaintRunTimeItem;", "sendTextDataInvokFun", "arrSerial", "sendTextDataInvokFun2", "sendChannelDelIndex", "arryIndex", "setTextSpeed", "speed", "sendExitCmd", "sendGifData", "sendGifData2", "sendChannelGifData", "sendChannelGifData1", "sendChannelGifData2", "sendDataInner2", "isDown", "head", "bgr_data", "getHwInfo", "getLedType", Constance.SP.LANGUAGE, "getLedType2", "sendOtaDataInner", "otaType", "otaData", "frameTotal", "sendDataInnerArrAnswer", "", "getSendDataInnerArrAnswer", "()Ljava/util/List;", "sendDataInner", "leftSynData", "getLeftSynData", "()[B", "setLeftSynData", "([B)V", "rightSynData", "getRightSynData", "setRightSynData", "sendEyeData", "eye_left_data", "eye_right_data", "sendSynchronization", "sendTemplateData", "subzoneData", "Lcom/wifiled/ipixels/ui/subzone/SubzoneData;", "sendDataCallback", "Lcom/wifiled/ipixels/core/SendDataCallback;", "sendTemplateData1", "sendTemplateData2", "sendDataTemInner", "fileNum", "sendDataTemInner1", "sendDataTemInner2", "CallbackBuilder", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SendCore implements WifiSend {
    private static final int CHECK_SPACE = 1;
    private static final int CONTINUE_SEND = 2;
    private static final int FIRST_SEND = 0;
    private static final int HEADER_LENGTH = 9;
    private static final int HEADER_TEXT_LENGTH = 10;
    private static final int NO_CHECK_SPACE = 0;
    public static final String TAG = "SendCore";
    private static final int TYPE_CAMERA = 0;
    private static final int TYPE_DIY_IMAGE = 5;
    private static final int TYPE_DIY_IMAGE_UNREDO = 6;
    private static final int TYPE_GIF = 3;
    private static final int TYPE_IMAGE = 2;
    private static final int TYPE_TEM = 7;
    private static final int TYPE_TEXT = 4;
    private static final int TYPE_VIDEO = 1;
    private static int tProcess;
    public static final SendCore INSTANCE = new SendCore();
    private static final List<byte[]> sendDataInnerArrAnswer = CollectionsKt.mutableListOf(new byte[]{5, 0, 2, 0, 3}, new byte[]{5, 0, 2, 0, 1}, new byte[]{5, 0, 3, 0, 1}, new byte[]{5, 0, 4, 0, 1}, new byte[]{5, 0, 3, 0, 3}, new byte[]{5, 0, 4, 0, 3}, new byte[]{5, 0, 0, 1, 1}, new byte[]{5, 0, 0, 1, 3}, new byte[]{5, 0, 0, 0, 2});
    private static byte[] leftSynData = new byte[0];
    private static byte[] rightSynData = new byte[0];

    private final boolean shouldCrc(int type) {
        return type == 2 || type == 1 || type == 3 || type == 4 || type == 7;
    }

    private SendCore() {
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void deleteAllData(SendResultCallback sendResultCallback) {
        super.deleteAllData(sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void deleteAllData2(SendResultCallback sendResultCallback) {
        super.deleteAllData2(sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void sendColockMode(int i, boolean z, boolean z2, SendResultCallback sendResultCallback) {
        super.sendColockMode(i, z, z2, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void sendCompat(byte[] bArr, SendResultCallback sendResultCallback) {
        super.sendCompat(bArr, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void sendCompat2(byte[] bArr, SendResultCallback sendResultCallback) {
        super.sendCompat2(bArr, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void sendLedOnOff(int i, SendResultCallback sendResultCallback) {
        super.sendLedOnOff(i, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void sendRhythm(int i, int i2) {
        super.sendRhythm(i, i2);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void sendRhythmChart(int i, byte[] bArr) {
        super.sendRhythmChart(i, bArr);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void sendSportData(int i, int i2, int i3, SendResultCallback sendResultCallback) {
        super.sendSportData(i, i2, i3, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void setDiyFunMode(int i, SendResultCallback sendResultCallback) {
        super.setDiyFunMode(i, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void setLedLight(int i, SendResultCallback sendResultCallback) {
        super.setLedLight(i, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void setPwd(int i, String str, SendResultCallback sendResultCallback) {
        super.setPwd(i, str, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void setUpsideDown(int i, SendResultCallback sendResultCallback) {
        super.setUpsideDown(i, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void upDataOTA2900Start(SendResultCallback sendResultCallback) {
        super.upDataOTA2900Start(sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void updateOtaMcuOrWifiStep1(int i, byte[] bArr, int i2, int i3, SendResultCallback sendResultCallback) {
        super.updateOtaMcuOrWifiStep1(i, bArr, i2, i3, sendResultCallback);
    }

    @Override // com.wifiled.ipixels.core.send.BaseSend
    public void verifyPwd(String str, SendResultCallback sendResultCallback) {
        super.verifyPwd(str, sendResultCallback);
    }

    static /* synthetic */ byte[] payload$default(SendCore sendCore, int i, byte[] bArr, byte[] bArr2, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            bArr2 = new byte[0];
        }
        byte[] bArr3 = bArr2;
        int i6 = (i5 & 8) != 0 ? 0 : i2;
        if ((i5 & 16) != 0) {
            i3 = AppConfig.INSTANCE.getLedFrameSize();
        }
        int i7 = i3;
        if ((i5 & 32) != 0) {
            i4 = 100;
        }
        return sendCore.payload(i, bArr, bArr3, i6, i7, i4);
    }

    private final byte[] payload(int type, byte[] data, byte[] totalData, int option, int totalLength, int bright) {
        long CRC32;
        int i = type != 4 ? type != 5 ? 9 : 5 : 10;
        byte[] dataType = getDataType(type);
        byte[] bArr = new byte[i];
        boolean shouldCrc = shouldCrc(type);
        int length = data.length + i;
        if (shouldCrc) {
            length = data.length + i + 5;
        }
        bArr[0] = (byte) (length & 255);
        bArr[1] = (byte) ((length >> 8) & 255);
        bArr[2] = dataType[0];
        bArr[3] = dataType[1];
        bArr[4] = (byte) option;
        if (type != 5) {
            byte[] int2byte = ByteUtils.int2byte(totalLength);
            LogUtils.vTag("ruis", "frameLength ---" + ByteUtils.toHexString(int2byte));
            System.arraycopy(int2byte, 0, bArr, 5, int2byte.length);
        }
        if (bright != 100) {
            changeLight(bright, data);
        }
        if (shouldCrc) {
            byte[] bArr2 = new byte[5];
            if (type == 0 || type == 1 || type == 3) {
                CRC32 = CrcUtils.CRC32.CRC32(totalData, 0, totalData.length);
            } else {
                CRC32 = CrcUtils.CRC32.CRC32(data, 0, data.length);
            }
            byte[] int2byte2 = ByteUtils.int2byte((int) CRC32);
            System.arraycopy(int2byte2, 0, bArr2, 0, int2byte2.length);
            if (type == 3) {
                bArr2[4] = 2;
            }
            return ArraysKt.plus(ArraysKt.plus(bArr, bArr2), data);
        }
        return ArraysKt.plus(bArr, data);
    }

    public static /* synthetic */ byte[] payloadChannel$default(SendCore sendCore, int i, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, byte b, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            bArr2 = new byte[0];
        }
        byte[] bArr4 = bArr2;
        int i4 = (i3 & 8) != 0 ? 0 : i2;
        if ((i3 & 16) != 0) {
            bArr3 = new byte[0];
        }
        return sendCore.payloadChannel(i, bArr, bArr4, i4, bArr3, (i3 & 32) != 0 ? (byte) 0 : b);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00fd, code lost:
    
        if (r20 != 7) goto L80;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0173  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] payloadChannel(int r20, byte[] r21, byte[] r22, int r23, byte[] r24, byte r25) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.SendCore.payloadChannel(int, byte[], byte[], int, byte[], byte):byte[]");
    }

    public static /* synthetic */ byte[] payloadTemChannel$default(SendCore sendCore, int i, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, byte b, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            bArr2 = new byte[0];
        }
        byte[] bArr4 = bArr2;
        int i4 = (i3 & 8) != 0 ? 0 : i2;
        if ((i3 & 16) != 0) {
            bArr3 = new byte[0];
        }
        return sendCore.payloadTemChannel(i, bArr, bArr4, i4, bArr3, (i3 & 32) != 0 ? (byte) 0 : b);
    }

    public final byte[] payloadTemChannel(int type, byte[] data, byte[] totalData, int option, byte[] byteArrTemp, byte isBulkSend) {
        int inc;
        byte b;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(totalData, "totalData");
        Intrinsics.checkNotNullParameter(byteArrTemp, "byteArrTemp");
        LogUtils.vTag("ruis", "isBulkSend--" + ((int) isBulkSend));
        byte[] dataType = getDataType(type);
        byte[] bArr = new byte[9];
        int length = data.length + 15;
        String topActivity = AppConfig.INSTANCE.getTopActivity();
        boolean z = StringsKt.contains$default((CharSequence) topActivity, (CharSequence) String.valueOf(Reflection.getOrCreateKotlinClass(ChannelListActivity.class).getSimpleName()), false, 2, (Object) null) || StringsKt.contains$default((CharSequence) topActivity, (CharSequence) String.valueOf(Reflection.getOrCreateKotlinClass(ImageTextListActivity.class).getSimpleName()), false, 2, (Object) null) || StringsKt.contains$default((CharSequence) topActivity, (CharSequence) String.valueOf(Reflection.getOrCreateKotlinClass(BusinessRemoteControlActivity.class).getSimpleName()), false, 2, (Object) null);
        LogUtils.vTag("ruis", "isInChannel--" + z);
        byte b2 = z ? isBulkSend : (byte) 0;
        bArr[0] = (byte) (length & 255);
        bArr[1] = (byte) ((length >> 8) & 255);
        bArr[2] = dataType[0];
        bArr[3] = dataType[1];
        bArr[4] = (byte) option;
        byte[] int2byte = ByteUtils.int2byte(totalData.length);
        System.arraycopy(int2byte, 0, bArr, 5, int2byte.length);
        byte[] bArr2 = new byte[4];
        byte[] int2byte2 = ByteUtils.int2byte((int) CrcUtils.CRC32.CRC32(totalData, 0, totalData.length));
        System.arraycopy(int2byte2, 0, bArr2, 0, int2byte2.length);
        if (z) {
            LogUtils.vTag("ruis", "bSerial--" + ((int) b2));
            b = b2;
        } else {
            LogUtils.vTag("ruis", "option--" + option);
            if (option == 2) {
                inc = ChannelIndex.INSTANCE.index();
            } else {
                LogUtils.vTag("ruis", "chanelTypeInfo isBulkSend--" + ((int) isBulkSend));
                if (isBulkSend > 0) {
                    b = isBulkSend;
                } else {
                    inc = ChannelIndex.INSTANCE.inc();
                }
            }
            b = (byte) inc;
        }
        return ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(bArr, bArr2), new byte[]{2, b}), data);
    }

    private final byte[] getDataType(int type) {
        switch (type) {
            case 0:
            case 6:
                return new byte[]{0, 0};
            case 1:
                return new byte[]{1, 0};
            case 2:
                return new byte[]{2, 0};
            case 3:
                return new byte[]{3, 0};
            case 4:
                return new byte[]{0, 1};
            case 5:
                return new byte[]{5, 1};
            case 7:
                return new byte[]{4, 0};
            default:
                return new byte[]{0, 0};
        }
    }

    private final void changeLight(int bright, byte[] data) {
        int length = data.length;
        for (int i = 0; i < length; i++) {
            data[i] = (byte) RangesKt.coerceIn(((data[i] & UByte.MAX_VALUE) * bright) / 100, 0, 255);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, byte[]] */
    @Override // com.wifiled.ipixels.core.send.WifiSend
    public void sendCameraData(final byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = data.length / 12288;
        if (data.length % 12288 != 0) {
            intRef.element++;
        }
        final Ref.IntRef intRef2 = new Ref.IntRef();
        final Ref.IntRef intRef3 = new Ref.IntRef();
        final ArrayList arrayList = new ArrayList();
        int i = intRef.element;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 12288;
            int length = data.length - i3 < 12288 ? data.length - i3 : 12288;
            byte[] bArr = new byte[length];
            System.arraycopy(data, i3, bArr, 0, length);
            arrayList.add(bArr);
        }
        LogUtils.vTag("ruis", "perBlockData size " + arrayList.size());
        final Ref.IntRef intRef4 = new Ref.IntRef();
        final Ref.IntRef intRef5 = new Ref.IntRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = payload$default(this, 0, (byte[]) arrayList.get(intRef2.element), data, 0, 0, 0, 56, null);
        sendCompat((byte[]) objectRef.element, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendCameraData$1
            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onError(int errorCode) {
            }

            /* JADX WARN: Type inference failed for: r1v4, types: [T, byte[]] */
            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onResult(byte[] result) {
                boolean z;
                int i4;
                Intrinsics.checkNotNullParameter(result, "result");
                LogUtils.vTag("ruis", "onResult ----" + ByteUtils.toHexString(result));
                Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (Arrays.equals(result, it.next())) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    Ref.IntRef.this.element += result.length;
                    if (result.length == 5) {
                        byte b = result[4];
                        if (b == 0) {
                            LogUtils.i("SendCore>>>[onResult]:命令无效 ");
                        } else if (b == 2) {
                            LogUtils.i("SendCore>>>[onResult]:空间不足 ");
                        }
                    }
                } else if (result[result.length - 1] == 3) {
                    return;
                }
                int length2 = (Ref.IntRef.this.element * 100) / (data.length + (intRef.element * 15));
                int i5 = length2 <= 100 ? length2 : 100;
                if (z && arrayList.size() > 0) {
                    intRef2.element++;
                    objectRef.element = SendCore.payload$default(SendCore.INSTANCE, 0, arrayList.get(intRef2.element), data, intRef2.element > 0 ? 2 : 0, 0, 0, 48, null);
                    SendCore.INSTANCE.sendCompat(objectRef.element, this);
                }
                i4 = SendCore.tProcess;
                if (i4 == i5 || z) {
                    return;
                }
                SendCore sendCore = SendCore.INSTANCE;
                SendCore.tProcess = i5;
            }
        });
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2((byte[]) objectRef.element, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendCameraData$2
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int errorCode) {
                }

                /* JADX WARN: Type inference failed for: r1v2, types: [T, byte[]] */
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    boolean z;
                    int i4;
                    Intrinsics.checkNotNullParameter(result, "result");
                    Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (Arrays.equals(result, it.next())) {
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        Ref.IntRef.this.element += result.length;
                        if (result.length == 5) {
                            byte b = result[4];
                            if (b == 0) {
                                LogUtils.i("SendCore>>>[onResult]:命令无效 ");
                            } else if (b == 2) {
                                LogUtils.i("SendCore>>>[onResult]:空间不足 ");
                            }
                        }
                    } else if (result[result.length - 1] == 3) {
                        return;
                    }
                    int length2 = (Ref.IntRef.this.element * 100) / (data.length + (intRef.element * 15));
                    int i5 = length2 <= 100 ? length2 : 100;
                    if (z && arrayList.size() > 0) {
                        intRef3.element++;
                        objectRef.element = SendCore.payload$default(SendCore.INSTANCE, 0, arrayList.get(intRef3.element), data, intRef3.element > 0 ? 2 : 0, 0, 0, 48, null);
                        SendCore.INSTANCE.sendCompat2(objectRef.element, this);
                    }
                    i4 = SendCore.tProcess;
                    if (i4 == i5 || z) {
                        return;
                    }
                    SendCore sendCore = SendCore.INSTANCE;
                    SendCore.tProcess = i5;
                }
            });
        }
    }

    public static /* synthetic */ void sendUnRedoImageData$default(SendCore sendCore, byte[] bArr, SendResultCallback sendResultCallback, int i, Object obj) {
        if ((i & 2) != 0) {
            sendResultCallback = null;
        }
        sendCore.sendUnRedoImageData(bArr, sendResultCallback);
    }

    public final void sendUnRedoImageData(byte[] data, SendResultCallback callback) {
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] payload$default = payload$default(this, 6, data, null, 0, 0, 0, 60, null);
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendCompat(payload$default, callback);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(payload$default, callback);
        }
    }

    public static /* synthetic */ void sendChannelImageData$default(SendCore sendCore, boolean z, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        byte b2;
        Function1 function12;
        byte[] bArr3;
        byte[] bArr4;
        SendCore sendCore2;
        boolean z2;
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            bArr = new byte[0];
        }
        if ((i & 16) != 0) {
            b2 = 0;
            bArr4 = bArr2;
            function12 = function1;
            z2 = z;
            bArr3 = bArr;
            sendCore2 = sendCore;
        } else {
            b2 = b;
            function12 = function1;
            bArr3 = bArr;
            bArr4 = bArr2;
            sendCore2 = sendCore;
            z2 = z;
        }
        sendCore2.sendChannelImageData(z2, bArr3, bArr4, function12, b2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r1v18, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, com.wifiled.ipixels.core.SendCore$CallbackBuilder, java.lang.Object] */
    public final void sendChannelImageData(final boolean is_down, final byte[] serial, final byte[] data, Function1<? super CallbackBuilder, Unit> callbackBuilder, final byte isBulkSend) {
        Intrinsics.checkNotNullParameter(serial, "serial");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        ?? callbackBuilder2 = new CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        objectRef2.element = callbackBuilder2;
        AppConfig.INSTANCE.setLedFrameSize(12288);
        int length = AppConfig.INSTANCE.getLedFrameSize() > data.length ? data.length : AppConfig.INSTANCE.getLedFrameSize();
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = data.length / length;
        if (data.length % length != 0) {
            intRef.element++;
        }
        final ArrayList arrayList = new ArrayList();
        int i = intRef.element;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * length;
            int length2 = data.length - i3 < length ? data.length - i3 : length;
            byte[] bArr = new byte[length2];
            System.arraycopy(data, i3, bArr, 0, length2);
            arrayList.add(bArr);
        }
        Observable<Long> observeOn = Observable.interval(8L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendChannelImageData$lambda$0;
                sendChannelImageData$lambda$0 = SendCore.sendChannelImageData$lambda$0(Ref.ObjectRef.this, (Long) obj);
                return sendChannelImageData$lambda$0;
            }
        };
        objectRef.element = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda17
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Function1.this.invoke(obj);
            }
        });
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            LogUtils.vTag("ruis", "sendCompat");
            final Ref.IntRef intRef2 = new Ref.IntRef();
            final Ref.IntRef intRef3 = new Ref.IntRef();
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = payloadChannel(2, (byte[]) arrayList.get(intRef2.element), data, 0, serial, isBulkSend);
            if (!is_down) {
                ((byte[]) objectRef3.element)[14] = 101;
            }
            sendCompat((byte[]) objectRef3.element, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendChannelImageData$2
                /* JADX WARN: Type inference failed for: r1v3, types: [T, byte[]] */
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    boolean z;
                    int i4;
                    int i5;
                    Function0<Unit> completeAction$app_googleRelease;
                    Intrinsics.checkNotNullParameter(result, "result");
                    Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (Arrays.equals(result, it.next())) {
                            if (objectRef.element != null) {
                                Disposable disposable = objectRef.element;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                                objectRef.element = null;
                            }
                            z = true;
                        }
                    }
                    if (!z) {
                        intRef3.element += result.length;
                        if (result.length == 5) {
                            byte b = result[4];
                            if (b == 0) {
                                LogUtils.i("SendCore>>>[onResult]:命令无效 ");
                                Function1<Integer, Unit> errorAction$app_googleRelease = objectRef2.element.getErrorAction$app_googleRelease();
                                if (errorAction$app_googleRelease != null) {
                                    errorAction$app_googleRelease.invoke(10017);
                                }
                            } else if (b == 2) {
                                LogUtils.i("SendCore>>>[onResult]:空间不足 ");
                                Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef2.element.getErrorAction$app_googleRelease();
                                if (errorAction$app_googleRelease2 != null) {
                                    errorAction$app_googleRelease2.invoke(10016);
                                }
                            }
                        }
                    } else {
                        Function1<byte[], Unit> resultAction$app_googleRelease = objectRef2.element.getResultAction$app_googleRelease();
                        if (resultAction$app_googleRelease != null) {
                            resultAction$app_googleRelease.invoke(result);
                        }
                        if (result[result.length - 1] == 3) {
                            Function0<Unit> completeAction$app_googleRelease2 = objectRef2.element.getCompleteAction$app_googleRelease();
                            if (completeAction$app_googleRelease2 != null) {
                                completeAction$app_googleRelease2.invoke();
                                return;
                            }
                            return;
                        }
                    }
                    int length3 = (intRef3.element * 100) / (data.length + (intRef.element * 15));
                    if (length3 > 100) {
                        length3 = 100;
                    }
                    if (z && arrayList.size() > 0) {
                        intRef2.element++;
                        objectRef3.element = SendCore.INSTANCE.payloadChannel(2, arrayList.get(intRef2.element), data, intRef2.element > 0 ? 2 : 0, serial, isBulkSend);
                        if (!is_down) {
                            objectRef3.element[14] = 101;
                        }
                        SendCore.INSTANCE.sendCompat(objectRef3.element, this);
                    }
                    i4 = SendCore.tProcess;
                    if (i4 != length3 && !z) {
                        SendCore sendCore = SendCore.INSTANCE;
                        SendCore.tProcess = length3;
                        Function1<Integer, Unit> progressAction$app_googleRelease = objectRef2.element.getProgressAction$app_googleRelease();
                        if (progressAction$app_googleRelease != null) {
                            progressAction$app_googleRelease.invoke(Integer.valueOf(length3));
                            return;
                        }
                        return;
                    }
                    if (z) {
                        i5 = SendCore.tProcess;
                        if (i5 != 100 || (completeAction$app_googleRelease = objectRef2.element.getCompleteAction$app_googleRelease()) == null) {
                            return;
                        }
                        completeAction$app_googleRelease.invoke();
                    }
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    intRef3.element = 0;
                    intRef2.element = 0;
                    Function1<Integer, Unit> errorAction$app_googleRelease = objectRef2.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(Integer.valueOf(result));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendChannelImageData$lambda$0(Ref.ObjectRef objectRef, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(10013);
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void sendChannelImageData2$default(SendCore sendCore, boolean z, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        byte b2;
        Function1 function12;
        byte[] bArr3;
        byte[] bArr4;
        SendCore sendCore2;
        boolean z2;
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            bArr = new byte[0];
        }
        if ((i & 16) != 0) {
            b2 = 0;
            bArr4 = bArr2;
            function12 = function1;
            z2 = z;
            bArr3 = bArr;
            sendCore2 = sendCore;
        } else {
            b2 = b;
            function12 = function1;
            bArr3 = bArr;
            bArr4 = bArr2;
            sendCore2 = sendCore;
            z2 = z;
        }
        sendCore2.sendChannelImageData2(z2, bArr3, bArr4, function12, b2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r1v18, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, com.wifiled.ipixels.core.SendCore$CallbackBuilder, java.lang.Object] */
    public final void sendChannelImageData2(final boolean is_down, final byte[] serial, final byte[] data, Function1<? super CallbackBuilder, Unit> callbackBuilder, final byte isBulkSend) {
        Intrinsics.checkNotNullParameter(serial, "serial");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        ?? callbackBuilder2 = new CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        objectRef2.element = callbackBuilder2;
        AppConfig.INSTANCE.setLedFrameSize(12288);
        int length = AppConfig.INSTANCE.getLedFrameSize() > data.length ? data.length : AppConfig.INSTANCE.getLedFrameSize();
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = data.length / length;
        if (data.length % length != 0) {
            intRef.element++;
        }
        final ArrayList arrayList = new ArrayList();
        int i = intRef.element;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * length;
            int length2 = data.length - i3 < length ? data.length - i3 : length;
            byte[] bArr = new byte[length2];
            System.arraycopy(data, i3, bArr, 0, length2);
            arrayList.add(bArr);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            Observable<Long> observeOn = Observable.interval(8L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendChannelImageData2$lambda$2;
                    sendChannelImageData2$lambda$2 = SendCore.sendChannelImageData2$lambda$2(Ref.ObjectRef.this, (Long) obj);
                    return sendChannelImageData2$lambda$2;
                }
            };
            objectRef.element = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda14
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            LogUtils.vTag("ruis", "sendCompat2");
            final Ref.IntRef intRef2 = new Ref.IntRef();
            final Ref.IntRef intRef3 = new Ref.IntRef();
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = payloadChannel(2, (byte[]) arrayList.get(intRef2.element), data, 0, serial, isBulkSend);
            if (!is_down) {
                ((byte[]) objectRef3.element)[14] = 101;
            }
            sendCompat2((byte[]) objectRef3.element, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendChannelImageData2$2
                /* JADX WARN: Type inference failed for: r1v3, types: [T, byte[]] */
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    boolean z;
                    int i4;
                    int i5;
                    Function0<Unit> completeAction$app_googleRelease;
                    Intrinsics.checkNotNullParameter(result, "result");
                    Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (Arrays.equals(result, it.next())) {
                            if (objectRef.element != null) {
                                Disposable disposable = objectRef.element;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                                objectRef.element = null;
                            }
                            z = true;
                        }
                    }
                    if (!z) {
                        intRef3.element += result.length;
                        if (result.length == 5) {
                            byte b = result[4];
                            if (b == 0) {
                                LogUtils.i("SendCore>>>[onResult]:命令无效 ");
                                Function1<Integer, Unit> errorAction$app_googleRelease = objectRef2.element.getErrorAction$app_googleRelease();
                                if (errorAction$app_googleRelease != null) {
                                    errorAction$app_googleRelease.invoke(10017);
                                }
                            } else if (b == 2) {
                                LogUtils.i("SendCore>>>[onResult]:空间不足 ");
                                Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef2.element.getErrorAction$app_googleRelease();
                                if (errorAction$app_googleRelease2 != null) {
                                    errorAction$app_googleRelease2.invoke(10016);
                                }
                            }
                        }
                    } else {
                        Function1<byte[], Unit> resultAction$app_googleRelease = objectRef2.element.getResultAction$app_googleRelease();
                        if (resultAction$app_googleRelease != null) {
                            resultAction$app_googleRelease.invoke(result);
                        }
                        if (result[result.length - 1] == 3) {
                            Function0<Unit> completeAction$app_googleRelease2 = objectRef2.element.getCompleteAction$app_googleRelease();
                            if (completeAction$app_googleRelease2 != null) {
                                completeAction$app_googleRelease2.invoke();
                                return;
                            }
                            return;
                        }
                    }
                    int length3 = (intRef3.element * 100) / (data.length + (intRef.element * 15));
                    if (length3 > 100) {
                        length3 = 100;
                    }
                    if (z && arrayList.size() > 0) {
                        intRef2.element++;
                        objectRef3.element = SendCore.INSTANCE.payloadChannel(2, arrayList.get(intRef2.element), data, intRef2.element > 0 ? 2 : 0, serial, isBulkSend);
                        if (!is_down) {
                            objectRef3.element[14] = 101;
                        }
                        SendCore.INSTANCE.sendCompat2(objectRef3.element, this);
                    }
                    i4 = SendCore.tProcess;
                    if (i4 != length3 && !z) {
                        SendCore sendCore = SendCore.INSTANCE;
                        SendCore.tProcess = length3;
                        Function1<Integer, Unit> progressAction$app_googleRelease = objectRef2.element.getProgressAction$app_googleRelease();
                        if (progressAction$app_googleRelease != null) {
                            progressAction$app_googleRelease.invoke(Integer.valueOf(length3));
                            return;
                        }
                        return;
                    }
                    if (z) {
                        i5 = SendCore.tProcess;
                        if (i5 != 100 || (completeAction$app_googleRelease = objectRef2.element.getCompleteAction$app_googleRelease()) == null) {
                            return;
                        }
                        completeAction$app_googleRelease.invoke();
                    }
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    LogUtils.vTag("ruis", "onError ------" + BleRequestImpl.getBleRequest().getConnectedDevices().get(1).getName());
                    intRef3.element = 0;
                    intRef2.element = 0;
                    Function1<Integer, Unit> errorAction$app_googleRelease = objectRef2.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(Integer.valueOf(result));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendChannelImageData2$lambda$2(Ref.ObjectRef objectRef, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(10013);
        }
        return Unit.INSTANCE;
    }

    public final void sendDiyImageData(CPaintRunTimeItem item, SendResultCallback callback) {
        Intrinsics.checkNotNullParameter(item, "item");
        byte[] bArr = {(byte) Color.red(item.getSelColor()), (byte) Color.green(item.getSelColor()), (byte) Color.blue(item.getSelColor()), (byte) item.getColumn(), (byte) item.getRow()};
        if (!item.getArrPointXY().isEmpty()) {
            bArr = new byte[]{(byte) Color.red(item.getSelColor()), (byte) Color.green(item.getSelColor()), (byte) Color.blue(item.getSelColor())};
            Iterator<T> it = item.getArrPointXY().iterator();
            while (it.hasNext()) {
                bArr = ArraysKt.plus(bArr, (byte) ((Number) it.next()).intValue());
            }
        } else if (!item.getArrDifColorPointXY().isEmpty()) {
            bArr = new byte[]{(byte) Color.red(item.getDifColor()), (byte) Color.green(item.getDifColor()), (byte) Color.blue(item.getDifColor())};
            Iterator<T> it2 = item.getArrDifColorPointXY().iterator();
            while (it2.hasNext()) {
                bArr = ArraysKt.plus(bArr, (byte) ((Number) it2.next()).intValue());
            }
        }
        if (item.getMoveType() == DiyImageMoveType.OVERALL_MOVEMENT.getMode()) {
            byte[] arrMoveDirectionNum = item.getArrMoveDirectionNum();
            bArr = Arrays.copyOf(arrMoveDirectionNum, arrMoveDirectionNum.length);
            Intrinsics.checkNotNullExpressionValue(bArr, "copyOf(...)");
        }
        byte[] payload$default = payload$default(this, 5, bArr, null, item.getMoveType(), 0, 0, 52, null);
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendCompat(payload$default, callback);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(payload$default, callback);
        }
    }

    public static /* synthetic */ void sendTextDataInvokFun$default(SendCore sendCore, boolean z, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 4) != 0) {
            bArr2 = new byte[0];
        }
        sendCore.sendTextDataInvokFun(z, bArr, bArr2, function1, (i & 16) != 0 ? (byte) 0 : b);
    }

    public final void sendTextDataInvokFun(boolean is_down, byte[] arrSerial, byte[] totalData, Function1<? super CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        Intrinsics.checkNotNullParameter(arrSerial, "arrSerial");
        Intrinsics.checkNotNullParameter(totalData, "totalData");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        sendDataInner(is_down, arrSerial, totalData, 4, callbackBuilder, isBulkSend);
    }

    public static /* synthetic */ void sendTextDataInvokFun2$default(SendCore sendCore, boolean z, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 4) != 0) {
            bArr2 = new byte[0];
        }
        sendCore.sendTextDataInvokFun2(z, bArr, bArr2, function1, (i & 16) != 0 ? (byte) 0 : b);
    }

    public final void sendTextDataInvokFun2(boolean is_down, byte[] arrSerial, byte[] totalData, Function1<? super CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        Intrinsics.checkNotNullParameter(arrSerial, "arrSerial");
        Intrinsics.checkNotNullParameter(totalData, "totalData");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        sendDataInner2(is_down, arrSerial, totalData, 4, callbackBuilder, isBulkSend);
    }

    public final void sendChannelDelIndex(byte[] arryIndex, SendResultCallback callback) {
        Intrinsics.checkNotNullParameter(arryIndex, "arryIndex");
        int length = 6 + arryIndex.length;
        byte[] plus = ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(new byte[]{(byte) (length & 255), (byte) ((length >>> 8) & 255)}, new byte[]{2, 1}), new byte[]{(byte) (arryIndex.length & 255), (byte) ((arryIndex.length >>> 8) & 255)}), arryIndex);
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendCompat(plus, callback);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat(plus, callback);
        }
    }

    @Override // com.wifiled.ipixels.core.send.SimpleSend
    public void setTextSpeed(int speed, SendResultCallback callback) {
        byte[] bArr = {5, 0, 3, 1, 0};
        bArr[4] = (byte) speed;
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendCompat(bArr, callback);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(bArr, callback);
        }
    }

    public final void sendExitCmd(SendResultCallback callback) {
        byte[] bArr = {4, 0, 1, 1};
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendCompat(bArr, callback);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendCompat2(bArr, callback);
        }
    }

    @Override // com.wifiled.ipixels.core.send.WifiSend
    public void sendGifData(boolean is_down, byte[] data, Function1<? super CallbackBuilder, Unit> callbackBuilder) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        sendDataInner$default(is_down, null, data, 3, callbackBuilder, (byte) 0, 34, null);
    }

    public final void sendGifData2(boolean is_down, byte[] data, Function1<? super CallbackBuilder, Unit> callbackBuilder) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        sendDataInner2$default(is_down, null, data, 3, callbackBuilder, (byte) 0, 34, null);
    }

    public static /* synthetic */ void sendChannelGifData$default(SendCore sendCore, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = new byte[0];
        }
        if ((i & 8) != 0) {
            b = 0;
        }
        sendCore.sendChannelGifData(bArr, bArr2, function1, b);
    }

    public final void sendChannelGifData(byte[] serial, byte[] data, Function1<? super CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        Intrinsics.checkNotNullParameter(serial, "serial");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        sendDataInner$default(false, serial, data, 3, callbackBuilder, isBulkSend, 1, null);
    }

    public static /* synthetic */ void sendChannelGifData1$default(SendCore sendCore, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = new byte[0];
        }
        if ((i & 8) != 0) {
            b = 0;
        }
        sendCore.sendChannelGifData1(bArr, bArr2, function1, b);
    }

    public final void sendChannelGifData1(byte[] serial, byte[] data, Function1<? super CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        Intrinsics.checkNotNullParameter(serial, "serial");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        sendDataInner$default(false, serial, data, 3, callbackBuilder, isBulkSend, 1, null);
    }

    public static /* synthetic */ void sendChannelGifData2$default(SendCore sendCore, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = new byte[0];
        }
        if ((i & 8) != 0) {
            b = 0;
        }
        sendCore.sendChannelGifData2(bArr, bArr2, function1, b);
    }

    public final void sendChannelGifData2(byte[] serial, byte[] data, Function1<? super CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        Intrinsics.checkNotNullParameter(serial, "serial");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        sendDataInner2$default(false, serial, data, 3, callbackBuilder, isBulkSend, 1, null);
    }

    public static /* synthetic */ void sendDataInner2$default(boolean z, byte[] bArr, byte[] bArr2, int i, Function1 function1, byte b, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = true;
        }
        if ((i2 & 2) != 0) {
            bArr = new byte[0];
        }
        sendDataInner2(z, bArr, bArr2, i, function1, (i2 & 32) != 0 ? (byte) 0 : b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.wifiled.ipixels.core.SendCore$CallbackBuilder, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v5, types: [T, byte[]] */
    @JvmStatic
    public static final void sendDataInner2(boolean isDown, byte[] head, byte[] bgr_data, int type, Function1<? super CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        Intrinsics.checkNotNullParameter(head, "head");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        ?? callbackBuilder2 = new CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        objectRef2.element = callbackBuilder2;
        if (bgr_data == null || bgr_data.length == 0) {
            Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef2.element).getErrorAction$app_googleRelease();
            if (errorAction$app_googleRelease != null) {
                errorAction$app_googleRelease.invoke(10012);
                return;
            }
            return;
        }
        AppConfig.INSTANCE.setLedFrameSize(12288);
        Function0<Unit> startAction$app_googleRelease = ((CallbackBuilder) objectRef2.element).getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        int length = AppConfig.INSTANCE.getLedFrameSize() > bgr_data.length ? bgr_data.length : AppConfig.INSTANCE.getLedFrameSize();
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = bgr_data.length / length;
        if (bgr_data.length % length != 0) {
            intRef.element++;
        }
        ArrayList arrayList = new ArrayList();
        int i = intRef.element;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * length;
            int length2 = bgr_data.length - i3 < length ? bgr_data.length - i3 : length;
            byte[] bArr = new byte[length2];
            System.arraycopy(bgr_data, i3, bArr, 0, length2);
            arrayList.add(bArr);
        }
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            Observable<Long> observeOn = Observable.interval(8L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendDataInner2$lambda$6;
                    sendDataInner2$lambda$6 = SendCore.sendDataInner2$lambda$6(Ref.ObjectRef.this, (Long) obj);
                    return sendDataInner2$lambda$6;
                }
            };
            objectRef.element = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda19
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            Ref.IntRef intRef2 = new Ref.IntRef();
            Ref.IntRef intRef3 = new Ref.IntRef();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            SendCore sendCore = INSTANCE;
            objectRef3.element = payloadChannel$default(sendCore, type, (byte[]) arrayList.get(intRef2.element), bgr_data, 0, head, isBulkSend, 8, null);
            if (!isDown) {
                ((byte[]) objectRef3.element)[14] = 101;
            }
            sendCore.sendCompat2((byte[]) objectRef3.element, new SendCore$sendDataInner2$2(objectRef, intRef3, objectRef2, bgr_data, intRef, arrayList, intRef2, objectRef3, type, head, isBulkSend, isDown));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendDataInner2$lambda$6(Ref.ObjectRef objectRef, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(10013);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, com.wifiled.ipixels.core.SendCore$CallbackBuilder, java.lang.Object] */
    @JvmStatic
    public static final void getHwInfo(Function1<? super CallbackBuilder, Unit> callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        byte[] bArr = {4, 0, 5, ByteCompanionObject.MIN_VALUE};
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? callbackBuilder2 = new CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        objectRef.element = callbackBuilder2;
        if (AppConfig.INSTANCE.getConnectType() == 0) {
            Function0<Unit> startAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getStartAction$app_googleRelease();
            if (startAction$app_googleRelease != null) {
                startAction$app_googleRelease.invoke();
            }
            SocketManager.INSTANCE.sendData(bArr, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$getHwInfo$1
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    Function0<Unit> completeAction$app_googleRelease;
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result.length == 0) {
                        Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease != null) {
                            errorAction$app_googleRelease.invoke(100113);
                            return;
                        }
                        return;
                    }
                    Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                    if (resultAction$app_googleRelease != null) {
                        resultAction$app_googleRelease.invoke(result);
                    }
                    if (result.length > 4) {
                        byte b = result[4];
                        if (b == 1 || b != 3 || (completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                            return;
                        }
                        completeAction$app_googleRelease.invoke();
                        return;
                    }
                    Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease2 != null) {
                        errorAction$app_googleRelease2.invoke(100113);
                    }
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(100113);
                    }
                }
            });
            return;
        }
        if (AppConfig.INSTANCE.getConnectType() == 1) {
            Function0<Unit> startAction$app_googleRelease2 = ((CallbackBuilder) objectRef.element).getStartAction$app_googleRelease();
            if (startAction$app_googleRelease2 != null) {
                startAction$app_googleRelease2.invoke();
            }
            if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
                INSTANCE.sendCompat(bArr, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$getHwInfo$2
                    @Override // com.wifiled.ipixels.core.send.SendResultCallback
                    public void onResult(byte[] result) {
                        Function1<Integer, Unit> errorAction$app_googleRelease;
                        Function0<Unit> completeAction$app_googleRelease;
                        Intrinsics.checkNotNullParameter(result, "result");
                        Log.d(getClass().getSimpleName(), "onResult: " + ByteUtils.toHexString(result));
                        if (result.length == 0) {
                            Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef.element.getErrorAction$app_googleRelease();
                            if (errorAction$app_googleRelease2 != null) {
                                errorAction$app_googleRelease2.invoke(100113);
                                return;
                            }
                            return;
                        }
                        Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                        if (resultAction$app_googleRelease != null) {
                            resultAction$app_googleRelease.invoke(result);
                        }
                        if (result.length > 4) {
                            byte b = result[4];
                            if (b == 1 || b != 3 || (completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                                return;
                            }
                            completeAction$app_googleRelease.invoke();
                            return;
                        }
                        if (result.length != 0 || (errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease()) == null) {
                            return;
                        }
                        errorAction$app_googleRelease.invoke(100113);
                    }

                    @Override // com.wifiled.ipixels.core.send.SendResultCallback
                    public void onError(int result) {
                        Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease != null) {
                            errorAction$app_googleRelease.invoke(Integer.valueOf(result));
                        }
                    }
                });
                return;
            }
            return;
        }
        if (AppConfig.INSTANCE.getConnectType2() == 1) {
            Function0<Unit> startAction$app_googleRelease3 = ((CallbackBuilder) objectRef.element).getStartAction$app_googleRelease();
            if (startAction$app_googleRelease3 != null) {
                startAction$app_googleRelease3.invoke();
            }
            if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
                INSTANCE.sendCompat2(bArr, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$getHwInfo$3
                    @Override // com.wifiled.ipixels.core.send.SendResultCallback
                    public void onResult(byte[] result) {
                        Function1<Integer, Unit> errorAction$app_googleRelease;
                        Function0<Unit> completeAction$app_googleRelease;
                        Intrinsics.checkNotNullParameter(result, "result");
                        Log.d(getClass().getSimpleName(), "onResult: " + ByteUtils.toHexString(result));
                        if (result.length == 0) {
                            Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef.element.getErrorAction$app_googleRelease();
                            if (errorAction$app_googleRelease2 != null) {
                                errorAction$app_googleRelease2.invoke(100113);
                                return;
                            }
                            return;
                        }
                        Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                        if (resultAction$app_googleRelease != null) {
                            resultAction$app_googleRelease.invoke(result);
                        }
                        if (result.length > 4) {
                            byte b = result[4];
                            if (b == 1 || b != 3 || (completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                                return;
                            }
                            completeAction$app_googleRelease.invoke();
                            return;
                        }
                        if (result.length != 0 || (errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease()) == null) {
                            return;
                        }
                        errorAction$app_googleRelease.invoke(100113);
                    }

                    @Override // com.wifiled.ipixels.core.send.SendResultCallback
                    public void onError(int result) {
                        Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease != null) {
                            errorAction$app_googleRelease.invoke(Integer.valueOf(result));
                        }
                    }
                });
                return;
            }
            return;
        }
        Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(100114);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, com.wifiled.ipixels.core.SendCore$CallbackBuilder, java.lang.Object] */
    public final void getLedType(byte language, Function1<? super CallbackBuilder, Unit> callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        byte[] bArr = {8, 0, 1, ByteCompanionObject.MIN_VALUE, 0, 0, 0, language};
        String convertSecondsToFormat = DateUtil.convertSecondsToFormat(System.currentTimeMillis(), DateUtils.FORMAT_HH_MM_SS);
        Intrinsics.checkNotNull(convertSecondsToFormat);
        List mutableList = CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) convertSecondsToFormat, new String[]{":"}, false, 0, 6, (Object) null));
        bArr[4] = Byte.parseByte((String) mutableList.get(0));
        bArr[5] = Byte.parseByte((String) mutableList.get(1));
        bArr[6] = Byte.parseByte((String) mutableList.get(2));
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? callbackBuilder2 = new CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        objectRef.element = callbackBuilder2;
        int connectType = AppConfig.INSTANCE.getConnectType();
        if (connectType == 0) {
            Function0<Unit> startAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getStartAction$app_googleRelease();
            if (startAction$app_googleRelease != null) {
                startAction$app_googleRelease.invoke();
            }
            SocketManager.INSTANCE.sendData(bArr, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$getLedType$1
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    byte b;
                    Function0<Unit> completeAction$app_googleRelease;
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result.length == 0) {
                        Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease != null) {
                            errorAction$app_googleRelease.invoke(100013);
                            return;
                        }
                        return;
                    }
                    Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                    if (resultAction$app_googleRelease != null) {
                        resultAction$app_googleRelease.invoke(result);
                    }
                    if (result.length <= 4 || (b = result[4]) == 1 || b != 3 || (completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                        return;
                    }
                    completeAction$app_googleRelease.invoke();
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(Integer.valueOf(result));
                    }
                }
            });
            return;
        }
        if (connectType == 1) {
            Log.v("ruis", "getLedType");
            sendCompat(bArr, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$getLedType$2
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    byte b;
                    Function0<Unit> completeAction$app_googleRelease;
                    Intrinsics.checkNotNullParameter(result, "result");
                    Log.d(SendCore.TAG, "onResult: " + ByteUtils.toHexString(result));
                    if (result.length == 0) {
                        Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease != null) {
                            errorAction$app_googleRelease.invoke(100113);
                            return;
                        }
                        return;
                    }
                    Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                    if (resultAction$app_googleRelease != null) {
                        resultAction$app_googleRelease.invoke(result);
                    }
                    if (result.length <= 4 || (b = result[4]) == 1 || b != 3 || (completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                        return;
                    }
                    completeAction$app_googleRelease.invoke();
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(Integer.valueOf(result));
                    }
                }
            });
        } else {
            if (AppConfig.INSTANCE.getConnectType2() == 1) {
                sendCompat2(bArr, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$getLedType$3
                    @Override // com.wifiled.ipixels.core.send.SendResultCallback
                    public void onResult(byte[] result) {
                        byte b;
                        Function0<Unit> completeAction$app_googleRelease;
                        Intrinsics.checkNotNullParameter(result, "result");
                        Log.d(SendCore.TAG, "onResult: " + ByteUtils.toHexString(result));
                        if (result.length == 0) {
                            Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                            if (errorAction$app_googleRelease != null) {
                                errorAction$app_googleRelease.invoke(100113);
                                return;
                            }
                            return;
                        }
                        Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                        if (resultAction$app_googleRelease != null) {
                            resultAction$app_googleRelease.invoke(result);
                        }
                        if (result.length <= 4 || (b = result[4]) == 1 || b != 3 || (completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                            return;
                        }
                        completeAction$app_googleRelease.invoke();
                    }

                    @Override // com.wifiled.ipixels.core.send.SendResultCallback
                    public void onError(int result) {
                        Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease != null) {
                            errorAction$app_googleRelease.invoke(Integer.valueOf(result));
                        }
                    }
                });
                return;
            }
            Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
            if (errorAction$app_googleRelease != null) {
                errorAction$app_googleRelease.invoke(100114);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, com.wifiled.ipixels.core.SendCore$CallbackBuilder, java.lang.Object] */
    public final void getLedType2(byte language, Function1<? super CallbackBuilder, Unit> callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        byte[] bArr = {8, 0, 1, ByteCompanionObject.MIN_VALUE, 0, 0, 0, language};
        String convertSecondsToFormat = DateUtil.convertSecondsToFormat(System.currentTimeMillis(), DateUtils.FORMAT_HH_MM_SS);
        Intrinsics.checkNotNull(convertSecondsToFormat);
        List mutableList = CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) convertSecondsToFormat, new String[]{":"}, false, 0, 6, (Object) null));
        bArr[4] = Byte.parseByte((String) mutableList.get(0));
        bArr[5] = Byte.parseByte((String) mutableList.get(1));
        bArr[6] = Byte.parseByte((String) mutableList.get(2));
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? callbackBuilder2 = new CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        objectRef.element = callbackBuilder2;
        int connectType2 = AppConfig.INSTANCE.getConnectType2();
        if (connectType2 == 0) {
            Function0<Unit> startAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getStartAction$app_googleRelease();
            if (startAction$app_googleRelease != null) {
                startAction$app_googleRelease.invoke();
            }
            SocketManager.INSTANCE.sendData(bArr, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$getLedType2$1
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    byte b;
                    Function0<Unit> completeAction$app_googleRelease;
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result.length == 0) {
                        Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease != null) {
                            errorAction$app_googleRelease.invoke(100013);
                            return;
                        }
                        return;
                    }
                    Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                    if (resultAction$app_googleRelease != null) {
                        resultAction$app_googleRelease.invoke(result);
                    }
                    if (result.length <= 4 || (b = result[4]) == 1 || b != 3 || (completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                        return;
                    }
                    completeAction$app_googleRelease.invoke();
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(Integer.valueOf(result));
                    }
                }
            });
            return;
        }
        if (connectType2 == 1) {
            Log.v("ruis", "getLedType");
            sendCompat2(bArr, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$getLedType2$2
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    byte b;
                    Function0<Unit> completeAction$app_googleRelease;
                    Intrinsics.checkNotNullParameter(result, "result");
                    Log.d(SendCore.TAG, "onResult: " + ByteUtils.toHexString(result));
                    if (result.length == 0) {
                        Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease != null) {
                            errorAction$app_googleRelease.invoke(100113);
                            return;
                        }
                        return;
                    }
                    Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                    if (resultAction$app_googleRelease != null) {
                        resultAction$app_googleRelease.invoke(result);
                    }
                    if (result.length <= 4 || (b = result[4]) == 1 || b != 3 || (completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                        return;
                    }
                    completeAction$app_googleRelease.invoke();
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    Function1<Integer, Unit> errorAction$app_googleRelease = objectRef.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(Integer.valueOf(result));
                    }
                }
            });
        } else {
            Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
            if (errorAction$app_googleRelease != null) {
                errorAction$app_googleRelease.invoke(100114);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r0v21, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r11v6, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r14v0, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r15v1, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, com.wifiled.ipixels.core.SendCore$CallbackBuilder, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r3v10, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r9v4, types: [T, byte[]] */
    @JvmStatic
    public static final void sendOtaDataInner(final int otaType, final byte[] otaData, final int frameTotal, Function1<? super CallbackBuilder, Unit> callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? callbackBuilder2 = new CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        objectRef.element = callbackBuilder2;
        if (otaData == null || otaData.length == 0) {
            Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
            if (errorAction$app_googleRelease != null) {
                errorAction$app_googleRelease.invoke(100113);
                return;
            }
            return;
        }
        AppConfig.INSTANCE.setLedFrameSize(12288);
        int connectType = AppConfig.INSTANCE.getConnectType();
        if (connectType == 0) {
            Function0<Unit> startAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getStartAction$app_googleRelease();
            if (startAction$app_googleRelease != null) {
                startAction$app_googleRelease.invoke();
            }
            final Ref.IntRef intRef = new Ref.IntRef();
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            final int i = 12288;
            objectRef2.element = ByteUtils.int2byte(12288);
            final Ref.IntRef intRef2 = new Ref.IntRef();
            intRef2.element = 12301;
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = ByteUtils.int2byte(intRef2.element);
            final Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
            objectRef4.element = new byte[]{((byte[]) objectRef3.element)[0], ((byte[]) objectRef3.element)[1], (byte) otaType, JSONB.Constants.BC_INT64_SHORT_MIN};
            objectRef4.element = ArraysKt.plus((byte[]) objectRef4.element, (byte) intRef.element);
            final Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
            objectRef5.element = new byte[12288];
            System.arraycopy(otaData, 0, objectRef5.element, 0, 12288);
            byte[] bArr = (byte[]) objectRef4.element;
            byte[] int2byte = ByteUtils.int2byte((int) CrcUtils.CRC32.CRC32((byte[]) objectRef5.element, 0, ((byte[]) objectRef5.element).length));
            Intrinsics.checkNotNullExpressionValue(int2byte, "int2byte(...)");
            objectRef4.element = ArraysKt.plus(bArr, int2byte);
            byte[] bArr2 = (byte[]) objectRef4.element;
            T element = objectRef2.element;
            Intrinsics.checkNotNullExpressionValue(element, "element");
            objectRef4.element = ArraysKt.plus(bArr2, (byte[]) element);
            objectRef4.element = ArraysKt.plus((byte[]) objectRef4.element, (byte[]) objectRef5.element);
            SocketManager.INSTANCE.sendData((byte[]) objectRef4.element, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendOtaDataInner$1
                /* JADX WARN: Type inference failed for: r0v14, types: [T, byte[]] */
                /* JADX WARN: Type inference failed for: r0v17, types: [T, byte[]] */
                /* JADX WARN: Type inference failed for: r0v20, types: [T, byte[]] */
                /* JADX WARN: Type inference failed for: r1v7, types: [T, byte[]] */
                /* JADX WARN: Type inference failed for: r5v10, types: [T, byte[]] */
                /* JADX WARN: Type inference failed for: r5v11, types: [T, byte[]] */
                /* JADX WARN: Type inference failed for: r5v9, types: [T, byte[]] */
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    Function0<Unit> completeAction$app_googleRelease;
                    Function0<Unit> completeAction$app_googleRelease2;
                    Intrinsics.checkNotNullParameter(result, "result");
                    Log.d(getClass().getSimpleName(), "onResult: " + ByteUtils.toHexString(result));
                    if (result.length == 0) {
                        Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease2 != null) {
                            errorAction$app_googleRelease2.invoke(100113);
                            return;
                        }
                        return;
                    }
                    Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                    if (resultAction$app_googleRelease != null) {
                        resultAction$app_googleRelease.invoke(result);
                    }
                    if (result.length < 4) {
                        return;
                    }
                    byte b = result[4];
                    if (b != 1) {
                        if (b != 3 || (completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                            return;
                        }
                        completeAction$app_googleRelease.invoke();
                        return;
                    }
                    intRef.element++;
                    Function1<Integer, Unit> progressAction$app_googleRelease = objectRef.element.getProgressAction$app_googleRelease();
                    if (progressAction$app_googleRelease != null) {
                        progressAction$app_googleRelease.invoke(Integer.valueOf((intRef.element * 100) / frameTotal));
                    }
                    if (intRef.element == frameTotal) {
                        Function0<Unit> completeAction$app_googleRelease3 = objectRef.element.getCompleteAction$app_googleRelease();
                        if (completeAction$app_googleRelease3 != null) {
                            completeAction$app_googleRelease3.invoke();
                            return;
                        }
                        return;
                    }
                    int i2 = intRef.element;
                    int i3 = i;
                    int i4 = i2 * i3;
                    int length = otaData.length - i4;
                    if (length < i3) {
                        intRef2.element = length + 13;
                        objectRef3.element = ByteUtils.int2byte(intRef2.element);
                        objectRef2.element = ByteUtils.int2byte(length);
                        objectRef5.element = new byte[length];
                        i3 = length;
                    }
                    objectRef4.element = new byte[]{objectRef3.element[0], objectRef3.element[1], (byte) otaType, JSONB.Constants.BC_INT64_SHORT_MIN};
                    Ref.ObjectRef<byte[]> objectRef6 = objectRef4;
                    objectRef6.element = ArraysKt.plus(objectRef6.element, (byte) intRef.element);
                    System.arraycopy(otaData, i4, objectRef5.element, 0, i3);
                    Ref.ObjectRef<byte[]> objectRef7 = objectRef4;
                    byte[] bArr3 = objectRef7.element;
                    byte[] int2byte2 = ByteUtils.int2byte((int) CrcUtils.CRC32.CRC32(objectRef5.element, 0, objectRef5.element.length));
                    Intrinsics.checkNotNullExpressionValue(int2byte2, "int2byte(...)");
                    objectRef7.element = ArraysKt.plus(bArr3, int2byte2);
                    Ref.ObjectRef<byte[]> objectRef8 = objectRef4;
                    byte[] bArr4 = objectRef8.element;
                    byte[] element2 = objectRef2.element;
                    Intrinsics.checkNotNullExpressionValue(element2, "element");
                    objectRef8.element = ArraysKt.plus(bArr4, element2);
                    Ref.ObjectRef<byte[]> objectRef9 = objectRef4;
                    objectRef9.element = ArraysKt.plus(objectRef9.element, objectRef5.element);
                    SocketManager.INSTANCE.sendData(objectRef4.element, this);
                    if (intRef.element < frameTotal - 1 || (completeAction$app_googleRelease2 = objectRef.element.getCompleteAction$app_googleRelease()) == null) {
                        return;
                    }
                    completeAction$app_googleRelease2.invoke();
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease2 != null) {
                        errorAction$app_googleRelease2.invoke(Integer.valueOf(result));
                    }
                }
            });
            return;
        }
        if (connectType == 1) {
            Function0<Unit> startAction$app_googleRelease2 = ((CallbackBuilder) objectRef.element).getStartAction$app_googleRelease();
            if (startAction$app_googleRelease2 != null) {
                startAction$app_googleRelease2.invoke();
            }
            byte[] int2byte2 = ByteUtils.int2byte(12288);
            byte[] int2byte3 = ByteUtils.int2byte(12301);
            final Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
            objectRef6.element = new byte[]{int2byte3[0], int2byte3[1], (byte) otaType, JSONB.Constants.BC_INT64_SHORT_MIN};
            objectRef6.element = ArraysKt.plus((byte[]) objectRef6.element, (byte) 0);
            byte[] bArr3 = new byte[12288];
            System.arraycopy(otaData, 0, bArr3, 0, 12288);
            byte[] bArr4 = (byte[]) objectRef6.element;
            byte[] int2byte4 = ByteUtils.int2byte((int) CrcUtils.CRC32.CRC32(bArr3, 0, 12288));
            Intrinsics.checkNotNullExpressionValue(int2byte4, "int2byte(...)");
            objectRef6.element = ArraysKt.plus(bArr4, int2byte4);
            byte[] bArr5 = (byte[]) objectRef6.element;
            Intrinsics.checkNotNull(int2byte2);
            objectRef6.element = ArraysKt.plus(bArr5, int2byte2);
            objectRef6.element = ArraysKt.plus((byte[]) objectRef6.element, bArr3);
            final Ref.IntRef intRef3 = new Ref.IntRef();
            intRef3.element = otaData.length / 12288;
            if (otaData.length % 12288 != 0) {
                intRef3.element++;
            }
            final Ref.IntRef intRef4 = new Ref.IntRef();
            final ArrayList arrayList = new ArrayList();
            int i2 = intRef3.element;
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = i3 * 12288;
                int length = otaData.length - i4 < 12288 ? otaData.length - i4 : 12288;
                byte[] bArr6 = new byte[length];
                System.arraycopy(otaData, i4, bArr6, 0, length);
                arrayList.add(bArr6);
            }
            final Ref.IntRef intRef5 = new Ref.IntRef();
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            INSTANCE.sendCompat((byte[]) objectRef6.element, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendOtaDataInner$callback$1
                /* JADX WARN: Type inference failed for: r2v25, types: [T, byte[]] */
                /* JADX WARN: Type inference failed for: r4v4, types: [T, byte[]] */
                /* JADX WARN: Type inference failed for: r4v8, types: [T, byte[]] */
                /* JADX WARN: Type inference failed for: r7v7, types: [T, byte[]] */
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    boolean z;
                    int i5;
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result.length == 0) {
                        Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease2 != null) {
                            errorAction$app_googleRelease2.invoke(100113);
                            return;
                        }
                        return;
                    }
                    Function1<byte[], Unit> resultAction$app_googleRelease = objectRef.element.getResultAction$app_googleRelease();
                    if (resultAction$app_googleRelease != null) {
                        resultAction$app_googleRelease.invoke(result);
                    }
                    Iterator it = CollectionsKt.mutableListOf(new byte[]{5, 0, 0, JSONB.Constants.BC_INT64_SHORT_MIN, 1}, new byte[]{5, 0, 1, JSONB.Constants.BC_INT64_SHORT_MIN, 1}).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (Arrays.equals(result, (byte[]) it.next())) {
                            z = true;
                            break;
                        }
                    }
                    String byteUtils = ByteUtils.toString(result);
                    Intrinsics.checkNotNullExpressionValue(byteUtils, "toString(...)");
                    if (!StringsKt.contains$default((CharSequence) byteUtils, (CharSequence) "ble_data_cont", false, 2, (Object) null) && !z) {
                        intRef5.element += result.length;
                    }
                    if (intRef5.element == (AppConfig.INSTANCE.getLedFrameSize() + 13) * (intRef4.element + 1) && z && !booleanRef.element) {
                        intRef4.element++;
                        if (intRef4.element == intRef3.element) {
                            return;
                        }
                        byte[] int2byte5 = ByteUtils.int2byte(arrayList.get(intRef4.element).length);
                        byte[] int2byte6 = ByteUtils.int2byte(arrayList.get(intRef4.element).length + 13);
                        objectRef6.element = new byte[]{int2byte6[0], int2byte6[1], (byte) otaType, JSONB.Constants.BC_INT64_SHORT_MIN};
                        Ref.ObjectRef<byte[]> objectRef7 = objectRef6;
                        objectRef7.element = ArraysKt.plus(objectRef7.element, (byte) intRef4.element);
                        Ref.ObjectRef<byte[]> objectRef8 = objectRef6;
                        byte[] bArr7 = objectRef8.element;
                        byte[] int2byte7 = ByteUtils.int2byte((int) CrcUtils.CRC32.CRC32(arrayList.get(intRef4.element), 0, arrayList.get(intRef4.element).length));
                        Intrinsics.checkNotNullExpressionValue(int2byte7, "int2byte(...)");
                        objectRef8.element = ArraysKt.plus(bArr7, int2byte7);
                        Ref.ObjectRef<byte[]> objectRef9 = objectRef6;
                        byte[] bArr8 = objectRef9.element;
                        Intrinsics.checkNotNull(int2byte5);
                        objectRef9.element = ArraysKt.plus(bArr8, int2byte5);
                        Ref.ObjectRef<byte[]> objectRef10 = objectRef6;
                        objectRef10.element = ArraysKt.plus(objectRef10.element, arrayList.get(intRef4.element));
                        SendCore.INSTANCE.sendCompat(objectRef6.element, this);
                    }
                    int length2 = (intRef5.element * 100) / (otaData.length + (intRef3.element * 13));
                    if (length2 > 100) {
                        length2 = 100;
                    }
                    LogUtils.d("process:" + length2);
                    i5 = SendCore.tProcess;
                    if (i5 != length2 && !z) {
                        SendCore sendCore = SendCore.INSTANCE;
                        SendCore.tProcess = length2;
                        Function1<Integer, Unit> progressAction$app_googleRelease = objectRef.element.getProgressAction$app_googleRelease();
                        if (progressAction$app_googleRelease != null) {
                            progressAction$app_googleRelease.invoke(Integer.valueOf(length2));
                        }
                    }
                    if (!(Arrays.equals(result, new byte[]{5, 0, 3, 0, 3}) && result[4] == 3) && (length2 < 100 || booleanRef.element)) {
                        return;
                    }
                    Function0<Unit> completeAction$app_googleRelease = objectRef.element.getCompleteAction$app_googleRelease();
                    if (completeAction$app_googleRelease != null) {
                        completeAction$app_googleRelease.invoke();
                    }
                    arrayList.clear();
                    booleanRef.element = true;
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    SendCore.INSTANCE.sendCompat(objectRef6.element, this);
                }
            });
            return;
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendOtaDataInner$lambda$8;
                sendOtaDataInner$lambda$8 = SendCore.sendOtaDataInner$lambda$8();
                return sendOtaDataInner$lambda$8;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendOtaDataInner$lambda$8() {
        ToastUtil.show(ContextHolder.getContext().getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    public final List<byte[]> getSendDataInnerArrAnswer() {
        return sendDataInnerArrAnswer;
    }

    public static /* synthetic */ void sendDataInner$default(boolean z, byte[] bArr, byte[] bArr2, int i, Function1 function1, byte b, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = true;
        }
        if ((i2 & 2) != 0) {
            bArr = new byte[0];
        }
        sendDataInner(z, bArr, bArr2, i, function1, (i2 & 32) != 0 ? (byte) 0 : b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v24, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.wifiled.ipixels.core.SendCore$CallbackBuilder, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v11, types: [T, byte[]] */
    @JvmStatic
    public static final void sendDataInner(boolean isDown, final byte[] head, final byte[] bgr_data, final int type, Function1<? super CallbackBuilder, Unit> callbackBuilder, final byte isBulkSend) {
        Intrinsics.checkNotNullParameter(head, "head");
        Intrinsics.checkNotNullParameter(callbackBuilder, "callbackBuilder");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        ?? callbackBuilder2 = new CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        objectRef2.element = callbackBuilder2;
        if (bgr_data == null || bgr_data.length == 0) {
            Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef2.element).getErrorAction$app_googleRelease();
            if (errorAction$app_googleRelease != null) {
                errorAction$app_googleRelease.invoke(10012);
                return;
            }
            return;
        }
        AppConfig.INSTANCE.setLedFrameSize(12288);
        if (AppConfig.INSTANCE.getConnectType() == 0) {
            Function0<Unit> startAction$app_googleRelease = ((CallbackBuilder) objectRef2.element).getStartAction$app_googleRelease();
            if (startAction$app_googleRelease != null) {
                startAction$app_googleRelease.invoke();
            }
            final Ref.IntRef intRef = new Ref.IntRef();
            final int length = AppConfig.INSTANCE.getLedFrameSize() > bgr_data.length ? bgr_data.length : AppConfig.INSTANCE.getLedFrameSize();
            final Ref.IntRef intRef2 = new Ref.IntRef();
            intRef2.element = bgr_data.length / length;
            if (bgr_data.length % length != 0) {
                intRef2.element++;
            }
            byte[] bArr = new byte[length];
            System.arraycopy(bgr_data, 0, bArr, 0, length);
            SocketManager.INSTANCE.sendData(payloadChannel$default(INSTANCE, type, bArr, bgr_data, 0, head, isBulkSend, 8, null), new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendDataInner$1
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    boolean z;
                    Function0<Unit> completeAction$app_googleRelease;
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result.length == 0) {
                        Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef2.element.getErrorAction$app_googleRelease();
                        if (errorAction$app_googleRelease2 != null) {
                            errorAction$app_googleRelease2.invoke(100113);
                            return;
                        }
                        return;
                    }
                    Function1<byte[], Unit> resultAction$app_googleRelease = objectRef2.element.getResultAction$app_googleRelease();
                    if (resultAction$app_googleRelease != null) {
                        resultAction$app_googleRelease.invoke(result);
                    }
                    Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (Arrays.equals(result, it.next())) {
                            z = true;
                            break;
                        }
                    }
                    String byteUtils = ByteUtils.toString(result);
                    Intrinsics.checkNotNull(byteUtils);
                    String str = byteUtils;
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || (StringsKt.contains$default((CharSequence) str, (CharSequence) "Socket connect fail", false, 2, (Object) null) && !z)) {
                        Function1<byte[], Unit> resultAction$app_googleRelease2 = objectRef2.element.getResultAction$app_googleRelease();
                        if (resultAction$app_googleRelease2 != null) {
                            byte[] bytes = byteUtils.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                            resultAction$app_googleRelease2.invoke(bytes);
                        }
                        Log.e("akon", "## " + byteUtils + " ##");
                    }
                    String byteUtils2 = ByteUtils.toString(result);
                    Intrinsics.checkNotNullExpressionValue(byteUtils2, "toString(...)");
                    if (StringsKt.contains$default((CharSequence) byteUtils2, (CharSequence) "cur12k_no_answer", false, 2, (Object) null) && !z) {
                        if (intRef.element != 0) {
                            intRef.element--;
                        }
                        Log.e("akon", "## cur12k_no_answer ##");
                    } else {
                        String byteUtils3 = ByteUtils.toString(result);
                        Intrinsics.checkNotNullExpressionValue(byteUtils3, "toString(...)");
                        if (StringsKt.contains$default((CharSequence) byteUtils3, (CharSequence) "allsend_no_answer", false, 2, (Object) null) && !z) {
                            intRef.element = 0;
                            Log.e("akon", "## allsend_no_answer ##");
                        }
                    }
                    if (result.length == 5) {
                        byte b = result[4];
                        if (b != 1) {
                            if (b == 3 && (completeAction$app_googleRelease = objectRef2.element.getCompleteAction$app_googleRelease()) != null) {
                                completeAction$app_googleRelease.invoke();
                                return;
                            }
                            return;
                        }
                        intRef.element++;
                        Function1<Integer, Unit> progressAction$app_googleRelease = objectRef2.element.getProgressAction$app_googleRelease();
                        if (progressAction$app_googleRelease != null) {
                            progressAction$app_googleRelease.invoke(Integer.valueOf((intRef.element * 100) / intRef2.element));
                        }
                        if (intRef.element == intRef2.element) {
                            return;
                        }
                        int i = intRef.element;
                        int i2 = length;
                        int i3 = i * i2;
                        byte[] bArr2 = bgr_data;
                        if (i3 > bArr2.length) {
                            return;
                        }
                        int length2 = bArr2.length - i3;
                        if (1 <= length2 && length2 < i2) {
                            i2 = bArr2.length - i3;
                        }
                        byte[] bArr3 = new byte[i2];
                        System.arraycopy(bArr2, i3, bArr3, 0, i2);
                        SocketManager.INSTANCE.sendData(SendCore.INSTANCE.payloadChannel(type, bArr3, bgr_data, 2, head, isBulkSend), this);
                        return;
                    }
                    SocketManager.INSTANCE.sendData(result, this);
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    Function1<Integer, Unit> errorAction$app_googleRelease2 = objectRef2.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease2 != null) {
                        errorAction$app_googleRelease2.invoke(Integer.valueOf(result));
                    }
                }
            });
            return;
        }
        Function0<Unit> startAction$app_googleRelease2 = ((CallbackBuilder) objectRef2.element).getStartAction$app_googleRelease();
        if (startAction$app_googleRelease2 != null) {
            startAction$app_googleRelease2.invoke();
        }
        int length2 = AppConfig.INSTANCE.getLedFrameSize() > bgr_data.length ? bgr_data.length : AppConfig.INSTANCE.getLedFrameSize();
        Ref.IntRef intRef3 = new Ref.IntRef();
        intRef3.element = bgr_data.length / length2;
        if (bgr_data.length % length2 != 0) {
            intRef3.element++;
        }
        ArrayList arrayList = new ArrayList();
        int i = intRef3.element;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * length2;
            int length3 = bgr_data.length - i3 < length2 ? bgr_data.length - i3 : length2;
            byte[] bArr2 = new byte[length3];
            System.arraycopy(bgr_data, i3, bArr2, 0, length3);
            arrayList.add(bArr2);
        }
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            Observable<Long> observeOn = Observable.interval(8L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendDataInner$lambda$9;
                    sendDataInner$lambda$9 = SendCore.sendDataInner$lambda$9(Ref.ObjectRef.this, (Long) obj);
                    return sendDataInner$lambda$9;
                }
            };
            objectRef.element = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda11
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            Ref.IntRef intRef4 = new Ref.IntRef();
            Ref.IntRef intRef5 = new Ref.IntRef();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            SendCore sendCore = INSTANCE;
            objectRef3.element = payloadChannel$default(sendCore, type, (byte[]) arrayList.get(intRef4.element), bgr_data, 0, head, isBulkSend, 8, null);
            if (!isDown) {
                ((byte[]) objectRef3.element)[14] = 101;
            }
            sendCore.sendCompat((byte[]) objectRef3.element, new SendCore$sendDataInner$3(objectRef, intRef5, objectRef2, bgr_data, intRef3, arrayList, intRef4, objectRef3, type, head, isBulkSend, isDown));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendDataInner$lambda$9(Ref.ObjectRef objectRef, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(10013);
        }
        return Unit.INSTANCE;
    }

    public final byte[] getLeftSynData() {
        return leftSynData;
    }

    public final void setLeftSynData(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        leftSynData = bArr;
    }

    public final byte[] getRightSynData() {
        return rightSynData;
    }

    public final void setRightSynData(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        rightSynData = bArr;
    }

    public static /* synthetic */ void sendEyeData$default(SendCore sendCore, boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, Function1 function1, byte b, int i, Object obj) {
        byte b2;
        Function1 function12;
        byte[] bArr4;
        byte[] bArr5;
        boolean z2;
        byte[] bArr6;
        SendCore sendCore2;
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            bArr = new byte[0];
        }
        if ((i & 4) != 0) {
            bArr2 = new byte[0];
        }
        if ((i & 8) != 0) {
            bArr3 = new byte[0];
        }
        if ((i & 32) != 0) {
            b2 = 0;
            bArr5 = bArr3;
            function12 = function1;
            bArr6 = bArr;
            bArr4 = bArr2;
            sendCore2 = sendCore;
            z2 = z;
        } else {
            b2 = b;
            function12 = function1;
            bArr4 = bArr2;
            bArr5 = bArr3;
            z2 = z;
            bArr6 = bArr;
            sendCore2 = sendCore;
        }
        sendCore2.sendEyeData(z2, bArr6, bArr4, bArr5, function12, b2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x019e  */
    /* JADX WARN: Type inference failed for: r0v16, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r0v51, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r1v22, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r1v33, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, com.wifiled.ipixels.core.SendCore$CallbackBuilder, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void sendEyeData(final boolean r29, final byte[] r30, final byte[] r31, final byte[] r32, kotlin.jvm.functions.Function1<? super com.wifiled.ipixels.core.SendCore.CallbackBuilder, kotlin.Unit> r33, final byte r34) {
        /*
            Method dump skipped, instructions count: 688
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.SendCore.sendEyeData(boolean, byte[], byte[], byte[], kotlin.jvm.functions.Function1, byte):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendEyeData$lambda$11(Ref.ObjectRef objectRef, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(10013);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendEyeData$lambda$13() {
        ToastUtil.cancelToast();
        ToastUtil.show(ContextHolder.getContext().getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendEyeData$lambda$14(Ref.ObjectRef objectRef, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        Function1<Integer, Unit> errorAction$app_googleRelease = ((CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(10013);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendEyeData$lambda$16() {
        ToastUtil.cancelToast();
        ToastUtil.show(ContextHolder.getContext().getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    public final void sendSynchronization() {
        LogUtils.vTag("ruis", "  sendSynchronization .left--- " + ByteUtils.toHexString(leftSynData));
        LogUtils.vTag("ruis", "  sendSynchronization .right--- " + ByteUtils.toHexString(rightSynData));
        SendCore sendCore = INSTANCE;
        sendCore.sendCompat(leftSynData, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendSynchronization$1
            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onResult(byte[] result) {
                Intrinsics.checkNotNullParameter(result, "result");
                LogUtils.vTag("ruis", "  SendCore.sendCompat--- " + ByteUtils.toHexString(result));
            }

            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onError(int errorCode) {
                LogUtils.vTag("ruis", "  SendCore.sendCompat--onError- " + errorCode);
            }
        });
        sendCore.sendCompat2(rightSynData, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendSynchronization$2
            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onResult(byte[] result) {
                Intrinsics.checkNotNullParameter(result, "result");
                LogUtils.vTag("ruis", "  SendCore.sendCompat2--- " + ByteUtils.toHexString(result));
            }

            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onError(int errorCode) {
                LogUtils.vTag("ruis", "  SendCore.sendCompat2-onError-- " + errorCode);
            }
        });
    }

    public static /* synthetic */ void sendTemplateData$default(boolean z, SubzoneData subzoneData, SendDataCallback sendDataCallback, byte b, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            b = 0;
        }
        sendTemplateData(z, subzoneData, sendDataCallback, b);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x14b0 A[LOOP:0: B:9:0x14aa->B:11:0x14b0, LOOP_END] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void sendTemplateData(boolean r29, com.wifiled.ipixels.ui.subzone.SubzoneData r30, com.wifiled.ipixels.core.SendDataCallback r31, byte r32) {
        /*
            Method dump skipped, instructions count: 5492
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.SendCore.sendTemplateData(boolean, com.wifiled.ipixels.ui.subzone.SubzoneData, com.wifiled.ipixels.core.SendDataCallback, byte):void");
    }

    public static /* synthetic */ void sendTemplateData1$default(boolean z, SubzoneData subzoneData, SendDataCallback sendDataCallback, byte b, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            b = 0;
        }
        sendTemplateData1(z, subzoneData, sendDataCallback, b);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x14b0 A[LOOP:0: B:9:0x14aa->B:11:0x14b0, LOOP_END] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void sendTemplateData1(boolean r29, com.wifiled.ipixels.ui.subzone.SubzoneData r30, com.wifiled.ipixels.core.SendDataCallback r31, byte r32) {
        /*
            Method dump skipped, instructions count: 5492
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.SendCore.sendTemplateData1(boolean, com.wifiled.ipixels.ui.subzone.SubzoneData, com.wifiled.ipixels.core.SendDataCallback, byte):void");
    }

    public static /* synthetic */ void sendTemplateData2$default(boolean z, SubzoneData subzoneData, SendDataCallback sendDataCallback, byte b, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            b = 0;
        }
        sendTemplateData2(z, subzoneData, sendDataCallback, b);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x14b0 A[LOOP:0: B:9:0x14aa->B:11:0x14b0, LOOP_END] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void sendTemplateData2(boolean r29, com.wifiled.ipixels.ui.subzone.SubzoneData r30, com.wifiled.ipixels.core.SendDataCallback r31, byte r32) {
        /*
            Method dump skipped, instructions count: 5492
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.SendCore.sendTemplateData2(boolean, com.wifiled.ipixels.ui.subzone.SubzoneData, com.wifiled.ipixels.core.SendDataCallback, byte):void");
    }

    public static /* synthetic */ void sendDataTemInner$default(boolean z, byte[] bArr, int i, SendDataCallback sendDataCallback, byte b, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = true;
        }
        if ((i3 & 16) != 0) {
            b = 0;
        }
        sendDataTemInner(z, bArr, i, sendDataCallback, b, (i3 & 32) != 0 ? 0 : i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v19, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r1v46, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r2v15, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, byte[]] */
    @JvmStatic
    public static final void sendDataTemInner(final boolean isDown, final byte[] bgr_data, final int type, SendDataCallback sendDataCallback, final byte isBulkSend, int fileNum) {
        String str;
        byte b;
        char c;
        byte[] bArr = bgr_data;
        final SendDataCallback sendDataCallback2 = sendDataCallback;
        Intrinsics.checkNotNullParameter(sendDataCallback2, "sendDataCallback");
        if (bArr == null || bArr.length == 0) {
            sendDataCallback2.onError(10012);
            return;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        AppConfig.INSTANCE.setLedFrameSize(12288);
        if (AppConfig.INSTANCE.getConnectType() == 1) {
            sendDataCallback2.onStart();
            int length = AppConfig.INSTANCE.getLedFrameSize() > bArr.length ? bArr.length : AppConfig.INSTANCE.getLedFrameSize();
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = bArr.length / length;
            if (bArr.length % length != 0) {
                intRef.element++;
            }
            final Ref.IntRef intRef2 = new Ref.IntRef();
            final ArrayList arrayList = new ArrayList();
            int i = intRef.element;
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * length;
                int length2 = bArr.length - i3 < length ? bArr.length - i3 : length;
                byte[] bArr2 = new byte[length2];
                System.arraycopy(bArr, i3, bArr2, 0, length2);
                arrayList.add(bArr2);
            }
            b = 101;
            c = 14;
            final Ref.IntRef intRef3 = new Ref.IntRef();
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            SendCore sendCore = INSTANCE;
            objectRef2.element = payloadTemChannel$default(sendCore, type, (byte[]) arrayList.get(intRef2.element), bArr, 0, null, isBulkSend, 24, null);
            ((byte[]) objectRef2.element)[13] = (byte) fileNum;
            if (!isDown) {
                ((byte[]) objectRef2.element)[14] = 101;
            }
            Observable<Long> observeOn = Observable.interval(8L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendDataTemInner$lambda$17;
                    sendDataTemInner$lambda$17 = SendCore.sendDataTemInner$lambda$17(SendDataCallback.this, (Long) obj);
                    return sendDataTemInner$lambda$17;
                }
            };
            objectRef.element = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda21
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            LogUtils.vTag("ruis", "send template " + ByteUtils.toHexString((byte[]) objectRef2.element));
            byte[] bArr3 = (byte[]) objectRef2.element;
            str = "ruis";
            SendResultCallback sendResultCallback = new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendDataTemInner$2
                /* JADX WARN: Type inference failed for: r4v9, types: [T, byte[]] */
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    boolean z;
                    int i4;
                    int i5;
                    int i6;
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result.length > 5) {
                        return;
                    }
                    Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (Arrays.equals(result, it.next())) {
                            if (objectRef.element != null) {
                                Disposable disposable = objectRef.element;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                                objectRef.element = null;
                            }
                            z = true;
                        }
                    }
                    LogUtils.vTag("ruis", "onResult template isAnswer---" + z);
                    if (!z) {
                        intRef3.element += result.length;
                        if (result.length == 5) {
                            byte b2 = result[4];
                            if (b2 == 0) {
                                LogUtils.i("SendCore>>>[onResult]:命令无效 ");
                                sendDataCallback2.onError(10017);
                            } else if (b2 == 2) {
                                sendDataCallback2.onError(10016);
                            }
                        }
                    } else {
                        sendDataCallback2.onResult(result);
                        if (result[result.length - 1] == 3) {
                            sendDataCallback2.onProgress(100);
                            sendDataCallback2.onCompleted();
                            return;
                        }
                    }
                    int length3 = (intRef3.element * 100) / (bgr_data.length + (intRef.element * 15));
                    LogUtils.vTag("ruis", "process template---" + length3);
                    if (length3 > 100) {
                        length3 = 100;
                    }
                    if (z && arrayList.size() > 0) {
                        intRef2.element++;
                        objectRef2.element = SendCore.payloadTemChannel$default(SendCore.INSTANCE, type, arrayList.get(intRef2.element), bgr_data, intRef2.element > 0 ? 2 : 0, null, isBulkSend, 16, null);
                        if (!isDown) {
                            objectRef2.element[14] = 101;
                        }
                        SendCore.INSTANCE.sendCompat(objectRef2.element, this);
                    }
                    i4 = SendCore.tProcess;
                    LogUtils.vTag("ruis", "tProcess---" + i4 + "   process----" + length3 + "   isAnswer---" + z);
                    i5 = SendCore.tProcess;
                    if (i5 != length3 && !z) {
                        SendCore sendCore2 = SendCore.INSTANCE;
                        SendCore.tProcess = length3;
                        sendDataCallback2.onProgress(length3);
                    } else if (z) {
                        i6 = SendCore.tProcess;
                        if (i6 == 100) {
                            sendDataCallback2.onCompleted();
                        }
                    }
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    intRef3.element = 0;
                    intRef2.element = 0;
                    sendDataCallback2.onError(result);
                }
            };
            objectRef = objectRef;
            sendDataCallback2 = sendDataCallback2;
            bArr = bgr_data;
            sendCore.sendCompat(bArr3, sendResultCallback);
        } else {
            str = "ruis";
            b = 101;
            c = 14;
        }
        if (AppConfig.INSTANCE.getConnectType2() == 1) {
            sendDataCallback2.onStart();
            int length3 = AppConfig.INSTANCE.getLedFrameSize() > bArr.length ? bArr.length : AppConfig.INSTANCE.getLedFrameSize();
            final Ref.IntRef intRef4 = new Ref.IntRef();
            intRef4.element = bArr.length / length3;
            if (bArr.length % length3 != 0) {
                intRef4.element++;
            }
            final Ref.IntRef intRef5 = new Ref.IntRef();
            final ArrayList arrayList2 = new ArrayList();
            int i4 = intRef4.element;
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = i5 * length3;
                int length4 = bArr.length - i6 < length3 ? bArr.length - i6 : length3;
                byte[] bArr4 = new byte[length4];
                System.arraycopy(bArr, i6, bArr4, 0, length4);
                arrayList2.add(bArr4);
            }
            final Ref.IntRef intRef6 = new Ref.IntRef();
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            SendCore sendCore2 = INSTANCE;
            objectRef3.element = payloadTemChannel$default(sendCore2, type, (byte[]) arrayList2.get(intRef5.element), bArr, 0, null, isBulkSend, 24, null);
            ((byte[]) objectRef3.element)[13] = (byte) fileNum;
            if (!isDown) {
                ((byte[]) objectRef3.element)[c] = b;
            }
            Observable<Long> observeOn2 = Observable.interval(8L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final Function1 function12 = new Function1() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendDataTemInner$lambda$19;
                    sendDataTemInner$lambda$19 = SendCore.sendDataTemInner$lambda$19(SendDataCallback.this, (Long) obj);
                    return sendDataTemInner$lambda$19;
                }
            };
            objectRef.element = observeOn2.subscribe(new Consumer() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            LogUtils.vTag(str, "send template " + ByteUtils.toHexString((byte[]) objectRef3.element));
            final SendDataCallback sendDataCallback3 = sendDataCallback2;
            final Ref.ObjectRef objectRef4 = objectRef;
            sendCore2.sendCompat2((byte[]) objectRef3.element, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendDataTemInner$4
                /* JADX WARN: Type inference failed for: r4v9, types: [T, byte[]] */
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    boolean z;
                    int i7;
                    int i8;
                    int i9;
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result.length > 5) {
                        return;
                    }
                    Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (Arrays.equals(result, it.next())) {
                            if (objectRef4.element != null) {
                                Disposable disposable = objectRef4.element;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                                objectRef4.element = null;
                            }
                            z = true;
                        }
                    }
                    LogUtils.vTag("ruis", "onResult template isAnswer---" + z);
                    if (!z) {
                        intRef6.element += result.length;
                        if (result.length == 5) {
                            byte b2 = result[4];
                            if (b2 == 0) {
                                LogUtils.i("SendCore>>>[onResult]:命令无效 ");
                                sendDataCallback3.onError(10017);
                            } else if (b2 == 2) {
                                sendDataCallback3.onError(10016);
                            }
                        }
                    } else {
                        sendDataCallback3.onResult(result);
                        if (result[result.length - 1] == 3) {
                            sendDataCallback3.onProgress(100);
                            sendDataCallback3.onCompleted();
                            return;
                        }
                    }
                    int length5 = (intRef6.element * 100) / (bgr_data.length + (intRef4.element * 15));
                    LogUtils.vTag("ruis", "process template---" + length5);
                    if (length5 > 100) {
                        length5 = 100;
                    }
                    if (z && arrayList2.size() > 0) {
                        intRef5.element++;
                        objectRef3.element = SendCore.payloadTemChannel$default(SendCore.INSTANCE, type, arrayList2.get(intRef5.element), bgr_data, intRef5.element > 0 ? 2 : 0, null, isBulkSend, 16, null);
                        if (!isDown) {
                            objectRef3.element[14] = 101;
                        }
                        SendCore.INSTANCE.sendCompat2(objectRef3.element, this);
                    }
                    i7 = SendCore.tProcess;
                    LogUtils.vTag("ruis", "tProcess---" + i7 + "   process----" + length5 + "   isAnswer---" + z);
                    i8 = SendCore.tProcess;
                    if (i8 != length5 && !z) {
                        SendCore sendCore3 = SendCore.INSTANCE;
                        SendCore.tProcess = length5;
                        sendDataCallback3.onProgress(length5);
                    } else if (z) {
                        i9 = SendCore.tProcess;
                        if (i9 == 100) {
                            sendDataCallback3.onCompleted();
                        }
                    }
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    intRef6.element = 0;
                    intRef5.element = 0;
                    sendDataCallback3.onError(result);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendDataTemInner$lambda$17(SendDataCallback sendDataCallback, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        sendDataCallback.onError(10013);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendDataTemInner$lambda$19(SendDataCallback sendDataCallback, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        sendDataCallback.onError(10013);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void sendDataTemInner1$default(boolean z, byte[] bArr, int i, SendDataCallback sendDataCallback, byte b, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = true;
        }
        if ((i3 & 16) != 0) {
            b = 0;
        }
        sendDataTemInner1(z, bArr, i, sendDataCallback, b, (i3 & 32) != 0 ? 0 : i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v14, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, byte[]] */
    @JvmStatic
    public static final void sendDataTemInner1(final boolean isDown, final byte[] bgr_data, final int type, final SendDataCallback sendDataCallback, final byte isBulkSend, int fileNum) {
        Intrinsics.checkNotNullParameter(sendDataCallback, "sendDataCallback");
        if (bgr_data == null || bgr_data.length == 0) {
            sendDataCallback.onError(10012);
            return;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        AppConfig.INSTANCE.setLedFrameSize(12288);
        if (AppConfig.INSTANCE.getConnectType() == 1) {
            sendDataCallback.onStart();
            int length = AppConfig.INSTANCE.getLedFrameSize() > bgr_data.length ? bgr_data.length : AppConfig.INSTANCE.getLedFrameSize();
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = bgr_data.length / length;
            if (bgr_data.length % length != 0) {
                intRef.element++;
            }
            final Ref.IntRef intRef2 = new Ref.IntRef();
            final ArrayList arrayList = new ArrayList();
            int i = intRef.element;
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * length;
                int length2 = bgr_data.length - i3 < length ? bgr_data.length - i3 : length;
                byte[] bArr = new byte[length2];
                System.arraycopy(bgr_data, i3, bArr, 0, length2);
                arrayList.add(bArr);
            }
            final Ref.IntRef intRef3 = new Ref.IntRef();
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            SendCore sendCore = INSTANCE;
            objectRef2.element = payloadTemChannel$default(sendCore, type, (byte[]) arrayList.get(intRef2.element), bgr_data, 0, null, isBulkSend, 24, null);
            ((byte[]) objectRef2.element)[13] = (byte) fileNum;
            if (!isDown) {
                ((byte[]) objectRef2.element)[14] = 101;
            }
            Observable<Long> observeOn = Observable.interval(8L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendDataTemInner1$lambda$21;
                    sendDataTemInner1$lambda$21 = SendCore.sendDataTemInner1$lambda$21(SendDataCallback.this, (Long) obj);
                    return sendDataTemInner1$lambda$21;
                }
            };
            objectRef.element = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda12
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            LogUtils.vTag("ruis", "send template " + ByteUtils.toHexString((byte[]) objectRef2.element));
            sendCore.sendCompat((byte[]) objectRef2.element, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendDataTemInner1$2
                /* JADX WARN: Type inference failed for: r4v9, types: [T, byte[]] */
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    boolean z;
                    int i4;
                    int i5;
                    int i6;
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result.length > 5) {
                        return;
                    }
                    Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (Arrays.equals(result, it.next())) {
                            if (objectRef.element != null) {
                                Disposable disposable = objectRef.element;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                                objectRef.element = null;
                            }
                            z = true;
                        }
                    }
                    LogUtils.vTag("ruis", "onResult template isAnswer---" + z);
                    if (!z) {
                        intRef3.element += result.length;
                        if (result.length == 5) {
                            byte b = result[4];
                            if (b == 0) {
                                LogUtils.i("SendCore>>>[onResult]:命令无效 ");
                                sendDataCallback.onError(10017);
                            } else if (b == 2) {
                                sendDataCallback.onError(10016);
                            }
                        }
                    } else {
                        sendDataCallback.onResult(result);
                        if (result[result.length - 1] == 3) {
                            sendDataCallback.onProgress(100);
                            sendDataCallback.onCompleted();
                            return;
                        }
                    }
                    int length3 = (intRef3.element * 100) / (bgr_data.length + (intRef.element * 15));
                    LogUtils.vTag("ruis", "process template---" + length3);
                    if (length3 > 100) {
                        length3 = 100;
                    }
                    if (z && arrayList.size() > 0) {
                        intRef2.element++;
                        objectRef2.element = SendCore.payloadTemChannel$default(SendCore.INSTANCE, type, arrayList.get(intRef2.element), bgr_data, intRef2.element > 0 ? 2 : 0, null, isBulkSend, 16, null);
                        if (!isDown) {
                            objectRef2.element[14] = 101;
                        }
                        SendCore.INSTANCE.sendCompat(objectRef2.element, this);
                    }
                    i4 = SendCore.tProcess;
                    LogUtils.vTag("ruis", "tProcess---" + i4 + "   process----" + length3 + "   isAnswer---" + z);
                    i5 = SendCore.tProcess;
                    if (i5 != length3 && !z) {
                        SendCore sendCore2 = SendCore.INSTANCE;
                        SendCore.tProcess = length3;
                        sendDataCallback.onProgress(length3);
                    } else if (z) {
                        i6 = SendCore.tProcess;
                        if (i6 == 100) {
                            sendDataCallback.onCompleted();
                        }
                    }
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    intRef3.element = 0;
                    intRef2.element = 0;
                    sendDataCallback.onError(result);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendDataTemInner1$lambda$21(SendDataCallback sendDataCallback, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        sendDataCallback.onError(10013);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void sendDataTemInner2$default(boolean z, byte[] bArr, int i, SendDataCallback sendDataCallback, byte b, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = true;
        }
        if ((i3 & 16) != 0) {
            b = 0;
        }
        sendDataTemInner2(z, bArr, i, sendDataCallback, b, (i3 & 32) != 0 ? 0 : i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v14, types: [T, io.reactivex.disposables.Disposable] */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, byte[]] */
    @JvmStatic
    public static final void sendDataTemInner2(final boolean isDown, final byte[] bgr_data, final int type, final SendDataCallback sendDataCallback, final byte isBulkSend, int fileNum) {
        Intrinsics.checkNotNullParameter(sendDataCallback, "sendDataCallback");
        if (bgr_data == null || bgr_data.length == 0) {
            sendDataCallback.onError(10012);
            return;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        AppConfig.INSTANCE.setLedFrameSize(12288);
        if (AppConfig.INSTANCE.getConnectType2() == 1) {
            sendDataCallback.onStart();
            int length = AppConfig.INSTANCE.getLedFrameSize() > bgr_data.length ? bgr_data.length : AppConfig.INSTANCE.getLedFrameSize();
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = bgr_data.length / length;
            if (bgr_data.length % length != 0) {
                intRef.element++;
            }
            final Ref.IntRef intRef2 = new Ref.IntRef();
            final ArrayList arrayList = new ArrayList();
            int i = intRef.element;
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * length;
                int length2 = bgr_data.length - i3 < length ? bgr_data.length - i3 : length;
                byte[] bArr = new byte[length2];
                System.arraycopy(bgr_data, i3, bArr, 0, length2);
                arrayList.add(bArr);
            }
            final Ref.IntRef intRef3 = new Ref.IntRef();
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            SendCore sendCore = INSTANCE;
            objectRef2.element = payloadTemChannel$default(sendCore, type, (byte[]) arrayList.get(intRef2.element), bgr_data, 0, null, isBulkSend, 24, null);
            ((byte[]) objectRef2.element)[13] = (byte) fileNum;
            if (!isDown) {
                ((byte[]) objectRef2.element)[14] = 101;
            }
            Observable<Long> observeOn = Observable.interval(8L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendDataTemInner2$lambda$23;
                    sendDataTemInner2$lambda$23 = SendCore.sendDataTemInner2$lambda$23(SendDataCallback.this, (Long) obj);
                    return sendDataTemInner2$lambda$23;
                }
            };
            objectRef.element = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.core.SendCore$$ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            LogUtils.vTag("ruis", "send template " + ByteUtils.toHexString((byte[]) objectRef2.element));
            sendCore.sendCompat2((byte[]) objectRef2.element, new SendResultCallback() { // from class: com.wifiled.ipixels.core.SendCore$sendDataTemInner2$2
                /* JADX WARN: Type inference failed for: r4v9, types: [T, byte[]] */
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    boolean z;
                    int i4;
                    int i5;
                    int i6;
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result.length > 5) {
                        return;
                    }
                    Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (Arrays.equals(result, it.next())) {
                            if (objectRef.element != null) {
                                Disposable disposable = objectRef.element;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                                objectRef.element = null;
                            }
                            z = true;
                        }
                    }
                    LogUtils.vTag("ruis", "onResult template isAnswer---" + z);
                    if (!z) {
                        intRef3.element += result.length;
                        if (result.length == 5) {
                            byte b = result[4];
                            if (b == 0) {
                                LogUtils.i("SendCore>>>[onResult]:命令无效 ");
                                sendDataCallback.onError(10017);
                            } else if (b == 2) {
                                sendDataCallback.onError(10016);
                            }
                        }
                    } else {
                        sendDataCallback.onResult(result);
                        if (result[result.length - 1] == 3) {
                            sendDataCallback.onProgress(100);
                            sendDataCallback.onCompleted();
                            return;
                        }
                    }
                    int length3 = (intRef3.element * 100) / (bgr_data.length + (intRef.element * 15));
                    LogUtils.vTag("ruis", "process template---" + length3);
                    if (length3 > 100) {
                        length3 = 100;
                    }
                    if (z && arrayList.size() > 0) {
                        intRef2.element++;
                        objectRef2.element = SendCore.payloadTemChannel$default(SendCore.INSTANCE, type, arrayList.get(intRef2.element), bgr_data, intRef2.element > 0 ? 2 : 0, null, isBulkSend, 16, null);
                        if (!isDown) {
                            objectRef2.element[14] = 101;
                        }
                        SendCore.INSTANCE.sendCompat2(objectRef2.element, this);
                    }
                    i4 = SendCore.tProcess;
                    LogUtils.vTag("ruis", "tProcess---" + i4 + "   process----" + length3 + "   isAnswer---" + z);
                    i5 = SendCore.tProcess;
                    if (i5 != length3 && !z) {
                        SendCore sendCore2 = SendCore.INSTANCE;
                        SendCore.tProcess = length3;
                        sendDataCallback.onProgress(length3);
                    } else if (z) {
                        i6 = SendCore.tProcess;
                        if (i6 == 100) {
                            sendDataCallback.onCompleted();
                        }
                    }
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                    intRef3.element = 0;
                    intRef2.element = 0;
                    sendDataCallback.onError(result);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendDataTemInner2$lambda$23(SendDataCallback sendDataCallback, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        sendDataCallback.onError(10013);
        return Unit.INSTANCE;
    }

    /* compiled from: SendCore.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0012\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010$\u001a\u00020\u00062\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J)\u0010&\u001a\u00020\u00062!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00060\fJ\u0014\u0010'\u001a\u00020\u00062\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0014\u0010(\u001a\u00020\u00062\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J)\u0010)\u001a\u00020\u00062!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00060\fJ)\u0010*\u001a\u00020\u00062!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110 ¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00060\fR\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR7\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\b\"\u0004\b\u0017\u0010\nR7\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0012\"\u0004\b\u001b\u0010\u0014R\"\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\b\"\u0004\b\u001e\u0010\nR7\u0010\u001f\u001a\u001f\u0012\u0013\u0012\u00110 ¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014¨\u0006+"}, d2 = {"Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "", "<init>", "()V", "startAction", "Lkotlin/Function0;", "", "getStartAction$app_googleRelease", "()Lkotlin/jvm/functions/Function0;", "setStartAction$app_googleRelease", "(Lkotlin/jvm/functions/Function0;)V", "progressAction", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "progress", "getProgressAction$app_googleRelease", "()Lkotlin/jvm/functions/Function1;", "setProgressAction$app_googleRelease", "(Lkotlin/jvm/functions/Function1;)V", "completeAction", "getCompleteAction$app_googleRelease", "setCompleteAction$app_googleRelease", "errorAction", "error", "getErrorAction$app_googleRelease", "setErrorAction$app_googleRelease", "successAction", "getSuccessAction$app_googleRelease", "setSuccessAction$app_googleRelease", "resultAction", "", "result", "getResultAction$app_googleRelease", "setResultAction$app_googleRelease", "onStart", "action", "onProgress", "onCompleted", "onSuccess", "onError", "onResult", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CallbackBuilder {
        private Function0<Unit> completeAction;
        private Function1<? super Integer, Unit> errorAction;
        private Function1<? super Integer, Unit> progressAction;
        private Function1<? super byte[], Unit> resultAction;
        private Function0<Unit> startAction;
        private Function0<Unit> successAction;

        public final Function0<Unit> getStartAction$app_googleRelease() {
            return this.startAction;
        }

        public final void setStartAction$app_googleRelease(Function0<Unit> function0) {
            this.startAction = function0;
        }

        public final Function1<Integer, Unit> getProgressAction$app_googleRelease() {
            return this.progressAction;
        }

        public final void setProgressAction$app_googleRelease(Function1<? super Integer, Unit> function1) {
            this.progressAction = function1;
        }

        public final Function0<Unit> getCompleteAction$app_googleRelease() {
            return this.completeAction;
        }

        public final void setCompleteAction$app_googleRelease(Function0<Unit> function0) {
            this.completeAction = function0;
        }

        public final Function1<Integer, Unit> getErrorAction$app_googleRelease() {
            return this.errorAction;
        }

        public final void setErrorAction$app_googleRelease(Function1<? super Integer, Unit> function1) {
            this.errorAction = function1;
        }

        public final Function0<Unit> getSuccessAction$app_googleRelease() {
            return this.successAction;
        }

        public final void setSuccessAction$app_googleRelease(Function0<Unit> function0) {
            this.successAction = function0;
        }

        public final Function1<byte[], Unit> getResultAction$app_googleRelease() {
            return this.resultAction;
        }

        public final void setResultAction$app_googleRelease(Function1<? super byte[], Unit> function1) {
            this.resultAction = function1;
        }

        public final void onStart(Function0<Unit> action) {
            Intrinsics.checkNotNullParameter(action, "action");
            this.startAction = action;
        }

        public final void onProgress(Function1<? super Integer, Unit> action) {
            Intrinsics.checkNotNullParameter(action, "action");
            this.progressAction = action;
        }

        public final void onCompleted(Function0<Unit> action) {
            Intrinsics.checkNotNullParameter(action, "action");
            this.completeAction = action;
        }

        public final void onSuccess(Function0<Unit> action) {
            Intrinsics.checkNotNullParameter(action, "action");
            this.successAction = action;
        }

        public final void onError(Function1<? super Integer, Unit> action) {
            Intrinsics.checkNotNullParameter(action, "action");
            this.errorAction = action;
        }

        public final void onResult(Function1<? super byte[], Unit> action) {
            Intrinsics.checkNotNullParameter(action, "action");
            this.resultAction = action;
        }
    }
}
