package com.wifiled.ipixels.ui.projection;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.video.MediaStoreOutputOptions;
import androidx.camera.video.Quality;
import androidx.camera.video.QualitySelector;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoRecordEvent;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import com.google.common.util.concurrent.ListenableFuture;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.permission.IPermission;
import com.wifiled.baselib.permission.PermissionUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.CoreDecoder;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.view.customview.CustomImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraXActivity.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010 \u001a\u00020\u000fH\u0014J\b\u0010!\u001a\u00020\"H\u0014J\b\u0010#\u001a\u00020\"H\u0014J\b\u0010$\u001a\u00020\"H\u0002J\b\u0010%\u001a\u00020\"H\u0002J\b\u0010&\u001a\u00020\"H\u0003J-\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u000f2\u000e\u0010)\u001a\n\u0012\u0006\b\u0001\u0012\u00020+0*2\u0006\u0010,\u001a\u00020-H\u0016¢\u0006\u0002\u0010.J\b\u0010/\u001a\u00020\"H\u0014J\b\u00100\u001a\u00020\"H\u0002J\b\u00101\u001a\u00020\"H\u0003J\b\u00102\u001a\u00020\"H\u0002J\u0010\u00104\u001a\u00020\"2\u0006\u00105\u001a\u000206H\u0003J\b\u00107\u001a\u00020\"H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/wifiled/ipixels/ui/projection/CameraXActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "<init>", "()V", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "videoCapture", "Landroidx/camera/video/VideoCapture;", "Landroidx/camera/video/Recorder;", "recording", "Landroidx/camera/video/Recording;", "lensFacing", "", "lastTime", "", "isRecording", "", "lastCameraTime", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "title_bar", "Landroidx/constraintlayout/widget/ConstraintLayout;", "viewFinder", "Landroidx/camera/view/PreviewView;", "iv_preview", "Landroid/widget/ImageView;", "layoutId", "initView", "", "bindData", "initToolbar", "initCamera", "initVideoCapture", "onRequestPermissionsResult", "requestCode", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "bindListener", "switchCamera", "startOrStopRecord", "startCamera", TypedValues.AttributesType.S_FRAME, "sendAndRender", "it", "Landroidx/camera/core/ImageProxy;", "onDestroy", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraXActivity extends BaseActivity {
    private ProcessCameraProvider cameraProvider;
    private int frame;
    private boolean isRecording;
    private CustomImageView iv_back;
    private ImageView iv_preview;
    private CustomImageView iv_right;
    private long lastCameraTime;
    private long lastTime;
    private Recording recording;
    private ConstraintLayout title_bar;
    private TextView tv_title;
    private VideoCapture<Recorder> videoCapture;
    private PreviewView viewFinder;
    private ExecutorService cameraExecutor = Executors.newSingleThreadExecutor();
    private int lensFacing = 1;

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        getWindow().setFlags(128, 128);
        return R.layout.activity_camerax;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById;
        View findViewById2 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById2;
        View findViewById3 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tv_title = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.title_bar);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.title_bar = (ConstraintLayout) findViewById4;
        View findViewById5 = findViewById(R.id.viewFinder);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.viewFinder = (PreviewView) findViewById5;
        View findViewById6 = findViewById(R.id.iv_preview);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.iv_preview = (ImageView) findViewById6;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        initToolbar();
        initVideoCapture();
        initCamera();
    }

    private final void initToolbar() {
        ConstraintLayout constraintLayout = this.title_bar;
        CustomImageView customImageView = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title_bar");
            constraintLayout = null;
        }
        constraintLayout.setBackgroundResource(R.mipmap.projection_nav_bg);
        CustomImageView customImageView2 = this.iv_back;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView2 = null;
        }
        customImageView2.setBackgroundResource(R.mipmap.icon_camera_switch);
        TextView textView = this.tv_title;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getBaseContext().getString(R.string.title_projection));
        CustomImageView customImageView3 = this.iv_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView = customImageView3;
        }
        customImageView.setBackgroundResource(R.mipmap.icon_cancel);
    }

    private final void initCamera() {
        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissionSelfPermis(new String[]{"android.permission.CAMERA"}, "", new IPermission() { // from class: com.wifiled.ipixels.ui.projection.CameraXActivity$initCamera$1
                @Override // com.wifiled.baselib.permission.IPermission
                public void permissionGranted() {
                    CameraXActivity.this.startCamera();
                }
            });
        } else {
            requestPermissionSelfPermis(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, "", new IPermission() { // from class: com.wifiled.ipixels.ui.projection.CameraXActivity$initCamera$2
                @Override // com.wifiled.baselib.permission.IPermission
                public void permissionGranted() {
                    CameraXActivity.this.startCamera();
                }
            });
        }
    }

    private final void initVideoCapture() {
        Recorder build = new Recorder.Builder().setQualitySelector(QualitySelector.from(Quality.FHD)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        VideoCapture<Recorder> withOutput = VideoCapture.withOutput(build);
        Intrinsics.checkNotNullExpressionValue(withOutput, "withOutput(...)");
        this.videoCapture = withOutput;
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("requestCode = " + requestCode, " per = " + permissions[0] + " ；grantResults = " + grantResults);
        if (Intrinsics.areEqual(permissions[0], "android.permission.CAMERA") && PermissionUtils.verifyPermissions(Arrays.copyOf(grantResults, grantResults.length))) {
            startCamera();
        }
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        CustomImageView customImageView = this.iv_back;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView = null;
        }
        customImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.projection.CameraXActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraXActivity.this.switchCamera();
            }
        });
        CustomImageView customImageView3 = this.iv_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView2 = customImageView3;
        }
        customImageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.projection.CameraXActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraXActivity.bindListener$lambda$1(CameraXActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$1(CameraXActivity cameraXActivity, View view) {
        AppConfig.INSTANCE.setPrevActivityName(AppConfig.INSTANCE.getTopActivity());
        cameraXActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchCamera() {
        this.lensFacing = this.lensFacing == 0 ? 1 : 0;
        startCamera();
    }

    private final void startOrStopRecord() {
        boolean z = this.isRecording;
        this.isRecording = !z;
        VideoCapture<Recorder> videoCapture = null;
        if (!z) {
            String str = System.currentTimeMillis() + ".mp4";
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", str);
            contentValues.put("mime_type", "video/mp4");
            if (Build.VERSION.SDK_INT >= 29) {
                contentValues.put("relative_path", "Movies/CameraX");
            }
            MediaStoreOutputOptions build = new MediaStoreOutputOptions.Builder(getContentResolver(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI).setContentValues(contentValues).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            VideoCapture<Recorder> videoCapture2 = this.videoCapture;
            if (videoCapture2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoCapture");
            } else {
                videoCapture = videoCapture2;
            }
            CameraXActivity cameraXActivity = this;
            this.recording = videoCapture.getOutput().prepareRecording(cameraXActivity, build).withAudioEnabled().start(ContextCompat.getMainExecutor(cameraXActivity), new Consumer() { // from class: com.wifiled.ipixels.ui.projection.CameraXActivity$$ExternalSyntheticLambda2
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    CameraXActivity.startOrStopRecord$lambda$3(CameraXActivity.this, (VideoRecordEvent) obj);
                }
            });
            return;
        }
        Recording recording = this.recording;
        if (recording != null) {
            recording.stop();
        }
        this.recording = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startOrStopRecord$lambda$3(CameraXActivity cameraXActivity, VideoRecordEvent videoRecordEvent) {
        if (videoRecordEvent instanceof VideoRecordEvent.Start) {
            Log.e(cameraXActivity.TAG, "🎥 开始录制");
            return;
        }
        if (videoRecordEvent instanceof VideoRecordEvent.Finalize) {
            VideoRecordEvent.Finalize finalize = (VideoRecordEvent.Finalize) videoRecordEvent;
            if (!finalize.hasError()) {
                Log.e(cameraXActivity.TAG, "✅ 录制完成: " + finalize.getOutputResults().getOutputUri());
            } else {
                Log.e(cameraXActivity.TAG, "❌ 录制出错: " + finalize.getError());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCamera() {
        CameraXActivity cameraXActivity = this;
        final ListenableFuture<ProcessCameraProvider> companion = ProcessCameraProvider.INSTANCE.getInstance(cameraXActivity);
        companion.addListener(new Runnable() { // from class: com.wifiled.ipixels.ui.projection.CameraXActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CameraXActivity.startCamera$lambda$6(CameraXActivity.this, companion);
            }
        }, ContextCompat.getMainExecutor(cameraXActivity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void startCamera$lambda$6(final CameraXActivity cameraXActivity, ListenableFuture listenableFuture) {
        V v = listenableFuture.get();
        Intrinsics.checkNotNullExpressionValue(v, "get(...)");
        cameraXActivity.cameraProvider = (ProcessCameraProvider) v;
        Preview build = new Preview.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        CameraSelector build2 = new CameraSelector.Builder().requireLensFacing(cameraXActivity.lensFacing).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        ImageAnalysis build3 = new ImageAnalysis.Builder().setBackpressureStrategy(0).build();
        build3.setAnalyzer(cameraXActivity.cameraExecutor, new ImageAnalysis.Analyzer() { // from class: com.wifiled.ipixels.ui.projection.CameraXActivity$$ExternalSyntheticLambda3
            @Override // androidx.camera.core.ImageAnalysis.Analyzer
            public final void analyze(ImageProxy imageProxy) {
                CameraXActivity.startCamera$lambda$6$lambda$5$lambda$4(CameraXActivity.this, imageProxy);
            }
        });
        Intrinsics.checkNotNullExpressionValue(build3, "also(...)");
        try {
            ProcessCameraProvider processCameraProvider = cameraXActivity.cameraProvider;
            PreviewView previewView = null;
            if (processCameraProvider == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                processCameraProvider = null;
            }
            processCameraProvider.unbindAll();
            ProcessCameraProvider processCameraProvider2 = cameraXActivity.cameraProvider;
            if (processCameraProvider2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                processCameraProvider2 = null;
            }
            CameraXActivity cameraXActivity2 = cameraXActivity;
            UseCase[] useCaseArr = new UseCase[3];
            useCaseArr[0] = build;
            useCaseArr[1] = build3;
            VideoCapture<Recorder> videoCapture = cameraXActivity.videoCapture;
            if (videoCapture == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoCapture");
                videoCapture = null;
            }
            useCaseArr[2] = videoCapture;
            processCameraProvider2.bindToLifecycle(cameraXActivity2, build2, useCaseArr);
            PreviewView previewView2 = cameraXActivity.viewFinder;
            if (previewView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            } else {
                previewView = previewView2;
            }
            build.setSurfaceProvider(previewView.getSurfaceProvider());
        } catch (Exception e) {
            Log.e(cameraXActivity.TAG, "Use case binding failed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startCamera$lambda$6$lambda$5$lambda$4(CameraXActivity cameraXActivity, ImageProxy it) {
        Intrinsics.checkNotNullParameter(it, "it");
        cameraXActivity.sendAndRender(it);
        it.close();
    }

    private final void sendAndRender(ImageProxy it) {
        Image image;
        int i = this.frame + 1;
        this.frame = i;
        if ((i & 1) != 0 || (image = it.getImage()) == null) {
            return;
        }
        this.lastTime = System.currentTimeMillis();
        final Bitmap cameraDecode = CoreDecoder.INSTANCE.cameraDecode(image, this.lensFacing == 0);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.projection.CameraXActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendAndRender$lambda$8$lambda$7;
                sendAndRender$lambda$8$lambda$7 = CameraXActivity.sendAndRender$lambda$8$lambda$7(CameraXActivity.this, cameraDecode);
                return sendAndRender$lambda$8$lambda$7;
            }
        });
        Log.e(this.TAG, "startCamera: 耗时: " + (System.currentTimeMillis() - this.lastTime) + " ms");
        String str = getFilesDir().getAbsolutePath() + File.separator + "image_test.jpg";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            cameraDecode.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception unused) {
        }
        SendCore sendCore = SendCore.INSTANCE;
        byte[] readFileBytes = FileUtil.readFileBytes(str);
        Intrinsics.checkNotNullExpressionValue(readFileBytes, "readFileBytes(...)");
        sendCore.sendCameraData(readFileBytes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendAndRender$lambda$8$lambda$7(CameraXActivity cameraXActivity, Bitmap bitmap) {
        ImageView imageView = cameraXActivity.iv_preview;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_preview");
            imageView = null;
        }
        imageView.setImageBitmap(bitmap);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.cameraExecutor.shutdown();
        Recording recording = this.recording;
        if (recording != null) {
            recording.stop();
        }
        SendCore.INSTANCE.sendExitCmd(null);
    }
}
