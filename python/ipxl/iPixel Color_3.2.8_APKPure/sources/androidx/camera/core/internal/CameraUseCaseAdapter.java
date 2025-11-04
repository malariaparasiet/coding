package androidx.camera.core.internal;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.LayoutSettings;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.UseCase;
import androidx.camera.core.ViewPort;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.Identifier;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.core.impl.RestrictedCameraControl;
import androidx.camera.core.impl.RestrictedCameraInfo;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.processing.TargetUtils;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes.dex */
public final class CameraUseCaseAdapter implements Camera {
    private static final String TAG = "CameraUseCaseAdapter";
    private final RestrictedCameraControl mAdapterCameraControl;
    private final RestrictedCameraInfo mAdapterCameraInfo;
    private final RestrictedCameraInfo mAdapterSecondaryCameraInfo;
    private final List<UseCase> mAppUseCases;
    private boolean mAttached;
    private final CameraConfig mCameraConfig;
    private final CameraCoordinator mCameraCoordinator;
    private final CameraDeviceSurfaceManager mCameraDeviceSurfaceManager;
    private final CameraInternal mCameraInternal;
    private final List<UseCase> mCameraUseCases;
    private List<CameraEffect> mEffects;
    private final CameraId mId;
    private Config mInteropConfig;
    private final LayoutSettings mLayoutSettings;
    private final Object mLock;
    private UseCase mPlaceholderForExtensions;
    private final CameraInternal mSecondaryCameraInternal;
    private final LayoutSettings mSecondaryLayoutSettings;
    private StreamSharing mStreamSharing;
    private final UseCaseConfigFactory mUseCaseConfigFactory;
    private ViewPort mViewPort;

    public CameraUseCaseAdapter(CameraInternal cameraInternal, CameraCoordinator cameraCoordinator, CameraDeviceSurfaceManager cameraDeviceSurfaceManager, UseCaseConfigFactory useCaseConfigFactory) {
        this(cameraInternal, null, new RestrictedCameraInfo(cameraInternal.getCameraInfoInternal(), CameraConfigs.defaultConfig()), null, LayoutSettings.DEFAULT, LayoutSettings.DEFAULT, cameraCoordinator, cameraDeviceSurfaceManager, useCaseConfigFactory);
    }

    public CameraUseCaseAdapter(CameraInternal cameraInternal, CameraInternal cameraInternal2, RestrictedCameraInfo restrictedCameraInfo, RestrictedCameraInfo restrictedCameraInfo2, LayoutSettings layoutSettings, LayoutSettings layoutSettings2, CameraCoordinator cameraCoordinator, CameraDeviceSurfaceManager cameraDeviceSurfaceManager, UseCaseConfigFactory useCaseConfigFactory) {
        this.mAppUseCases = new ArrayList();
        this.mCameraUseCases = new ArrayList();
        this.mEffects = Collections.emptyList();
        this.mLock = new Object();
        this.mAttached = true;
        this.mInteropConfig = null;
        this.mCameraInternal = cameraInternal;
        this.mSecondaryCameraInternal = cameraInternal2;
        this.mLayoutSettings = layoutSettings;
        this.mSecondaryLayoutSettings = layoutSettings2;
        this.mCameraCoordinator = cameraCoordinator;
        this.mCameraDeviceSurfaceManager = cameraDeviceSurfaceManager;
        this.mUseCaseConfigFactory = useCaseConfigFactory;
        CameraConfig cameraConfig = restrictedCameraInfo.getCameraConfig();
        this.mCameraConfig = cameraConfig;
        this.mAdapterCameraControl = new RestrictedCameraControl(cameraInternal.getCameraControlInternal(), cameraConfig.getSessionProcessor(null));
        this.mAdapterCameraInfo = restrictedCameraInfo;
        this.mAdapterSecondaryCameraInfo = restrictedCameraInfo2;
        this.mId = generateCameraId(restrictedCameraInfo, restrictedCameraInfo2);
    }

    public static CameraId generateCameraId(RestrictedCameraInfo restrictedCameraInfo, RestrictedCameraInfo restrictedCameraInfo2) {
        return CameraId.create(restrictedCameraInfo.getCameraId() + (restrictedCameraInfo2 == null ? "" : restrictedCameraInfo2.getCameraId()), restrictedCameraInfo.getCameraConfig().getCompatibilityId());
    }

    public CameraId getCameraId() {
        return this.mId;
    }

    public boolean isEquivalent(CameraUseCaseAdapter cameraUseCaseAdapter) {
        return getCameraId().equals(cameraUseCaseAdapter.getCameraId());
    }

    public void setViewPort(ViewPort viewPort) {
        synchronized (this.mLock) {
            this.mViewPort = viewPort;
        }
    }

    public void setEffects(List<CameraEffect> list) {
        synchronized (this.mLock) {
            this.mEffects = list;
        }
    }

    public void addUseCases(Collection<UseCase> collection) throws CameraException {
        synchronized (this.mLock) {
            this.mCameraInternal.setExtendedConfig(this.mCameraConfig);
            CameraInternal cameraInternal = this.mSecondaryCameraInternal;
            if (cameraInternal != null) {
                cameraInternal.setExtendedConfig(this.mCameraConfig);
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.mAppUseCases);
            linkedHashSet.addAll(collection);
            try {
                CameraInternal cameraInternal2 = this.mSecondaryCameraInternal;
                boolean z = true;
                boolean z2 = cameraInternal2 != null;
                if (cameraInternal2 == null) {
                    z = false;
                }
                updateUseCases(linkedHashSet, z2, z);
            } catch (IllegalArgumentException e) {
                throw new CameraException(e);
            }
        }
    }

    public void removeUseCases(Collection<UseCase> collection) {
        synchronized (this.mLock) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.mAppUseCases);
            linkedHashSet.removeAll(collection);
            CameraInternal cameraInternal = this.mSecondaryCameraInternal;
            boolean z = true;
            boolean z2 = cameraInternal != null;
            if (cameraInternal == null) {
                z = false;
            }
            updateUseCases(linkedHashSet, z2, z);
        }
    }

    void updateUseCases(Collection<UseCase> collection) {
        updateUseCases(collection, false, false);
    }

    void updateUseCases(Collection<UseCase> collection, boolean z, boolean z2) {
        StreamSpec streamSpec;
        Config implementationOptions;
        synchronized (this.mLock) {
            checkUnsupportedFeatureCombinationAndThrow(collection);
            if (!z && hasExtension() && hasVideoCapture(collection)) {
                updateUseCases(collection, true, z2);
                return;
            }
            StreamSharing createOrReuseStreamSharing = createOrReuseStreamSharing(collection, z);
            UseCase calculatePlaceholderForExtensions = calculatePlaceholderForExtensions(collection, createOrReuseStreamSharing);
            Collection<UseCase> calculateCameraUseCases = calculateCameraUseCases(collection, calculatePlaceholderForExtensions, createOrReuseStreamSharing);
            ArrayList<UseCase> arrayList = new ArrayList(calculateCameraUseCases);
            arrayList.removeAll(this.mCameraUseCases);
            ArrayList<UseCase> arrayList2 = new ArrayList(calculateCameraUseCases);
            arrayList2.retainAll(this.mCameraUseCases);
            ArrayList arrayList3 = new ArrayList(this.mCameraUseCases);
            arrayList3.removeAll(calculateCameraUseCases);
            Map<UseCase, ConfigPair> configs = getConfigs(arrayList, this.mCameraConfig.getUseCaseConfigFactory(), this.mUseCaseConfigFactory);
            Map<UseCase, StreamSpec> emptyMap = Collections.emptyMap();
            try {
                Map<UseCase, StreamSpec> calculateSuggestedStreamSpecs = calculateSuggestedStreamSpecs(getCameraMode(), this.mCameraInternal.getCameraInfoInternal(), arrayList, arrayList2, configs);
                if (this.mSecondaryCameraInternal != null) {
                    emptyMap = calculateSuggestedStreamSpecs(getCameraMode(), ((CameraInternal) Objects.requireNonNull(this.mSecondaryCameraInternal)).getCameraInfoInternal(), arrayList, arrayList2, configs);
                }
                updateViewPortAndSensorToBufferMatrix(calculateSuggestedStreamSpecs, calculateCameraUseCases);
                updateEffects(this.mEffects, calculateCameraUseCases, collection);
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    ((UseCase) it.next()).unbindFromCamera(this.mCameraInternal);
                }
                this.mCameraInternal.detachUseCases(arrayList3);
                if (this.mSecondaryCameraInternal != null) {
                    Iterator it2 = arrayList3.iterator();
                    while (it2.hasNext()) {
                        ((UseCase) it2.next()).unbindFromCamera((CameraInternal) Objects.requireNonNull(this.mSecondaryCameraInternal));
                    }
                    ((CameraInternal) Objects.requireNonNull(this.mSecondaryCameraInternal)).detachUseCases(arrayList3);
                }
                if (arrayList3.isEmpty()) {
                    for (UseCase useCase : arrayList2) {
                        if (calculateSuggestedStreamSpecs.containsKey(useCase) && (implementationOptions = (streamSpec = calculateSuggestedStreamSpecs.get(useCase)).getImplementationOptions()) != null && hasImplementationOptionChanged(streamSpec, useCase.getSessionConfig())) {
                            useCase.updateSuggestedStreamSpecImplementationOptions(implementationOptions);
                            if (this.mAttached) {
                                this.mCameraInternal.onUseCaseUpdated(useCase);
                                CameraInternal cameraInternal = this.mSecondaryCameraInternal;
                                if (cameraInternal != null) {
                                    ((CameraInternal) Objects.requireNonNull(cameraInternal)).onUseCaseUpdated(useCase);
                                }
                            }
                        }
                    }
                }
                for (UseCase useCase2 : arrayList) {
                    ConfigPair configPair = (ConfigPair) Objects.requireNonNull(configs.get(useCase2));
                    CameraInternal cameraInternal2 = this.mSecondaryCameraInternal;
                    if (cameraInternal2 != null) {
                        useCase2.bindToCamera(this.mCameraInternal, (CameraInternal) Objects.requireNonNull(cameraInternal2), configPair.mExtendedConfig, configPair.mCameraConfig);
                        useCase2.updateSuggestedStreamSpec((StreamSpec) Preconditions.checkNotNull(calculateSuggestedStreamSpecs.get(useCase2)), emptyMap.get(useCase2));
                    } else {
                        useCase2.bindToCamera(this.mCameraInternal, null, configPair.mExtendedConfig, configPair.mCameraConfig);
                        useCase2.updateSuggestedStreamSpec((StreamSpec) Preconditions.checkNotNull(calculateSuggestedStreamSpecs.get(useCase2)), null);
                    }
                }
                if (this.mAttached) {
                    this.mCameraInternal.attachUseCases(arrayList);
                    CameraInternal cameraInternal3 = this.mSecondaryCameraInternal;
                    if (cameraInternal3 != null) {
                        ((CameraInternal) Objects.requireNonNull(cameraInternal3)).attachUseCases(arrayList);
                    }
                }
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    ((UseCase) it3.next()).notifyState();
                }
                this.mAppUseCases.clear();
                this.mAppUseCases.addAll(collection);
                this.mCameraUseCases.clear();
                this.mCameraUseCases.addAll(calculateCameraUseCases);
                this.mPlaceholderForExtensions = calculatePlaceholderForExtensions;
                this.mStreamSharing = createOrReuseStreamSharing;
            } catch (IllegalArgumentException e) {
                if (!z && !hasExtension() && this.mCameraCoordinator.getCameraOperatingMode() != 2) {
                    updateUseCases(collection, true, z2);
                    return;
                }
                throw e;
            }
        }
    }

    private static boolean hasImplementationOptionChanged(StreamSpec streamSpec, SessionConfig sessionConfig) {
        Config implementationOptions = streamSpec.getImplementationOptions();
        Config implementationOptions2 = sessionConfig.getImplementationOptions();
        if (implementationOptions.listOptions().size() != sessionConfig.getImplementationOptions().listOptions().size()) {
            return true;
        }
        for (Config.Option<?> option : implementationOptions.listOptions()) {
            if (!implementationOptions2.containsOption(option) || !Objects.equals(implementationOptions2.retrieveOption(option), implementationOptions.retrieveOption(option))) {
                return true;
            }
        }
        return false;
    }

    private int getCameraMode() {
        synchronized (this.mLock) {
            return this.mCameraCoordinator.getCameraOperatingMode() == 2 ? 1 : 0;
        }
    }

    private boolean hasExtension() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mCameraConfig.getSessionProcessor(null) != null;
        }
        return z;
    }

    private Set<UseCase> getStreamSharingChildren(Collection<UseCase> collection, boolean z) {
        HashSet hashSet = new HashSet();
        int sharingTargets = getSharingTargets(z);
        for (UseCase useCase : collection) {
            Preconditions.checkArgument(!StreamSharing.isStreamSharing(useCase), "Only support one level of sharing for now.");
            if (useCase.isEffectTargetsSupported(sharingTargets)) {
                hashSet.add(useCase);
            }
        }
        return hashSet;
    }

    private int getSharingTargets(boolean z) {
        int i;
        synchronized (this.mLock) {
            Iterator<CameraEffect> it = this.mEffects.iterator();
            CameraEffect cameraEffect = null;
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                CameraEffect next = it.next();
                if (TargetUtils.getNumberOfTargets(next.getTargets()) > 1) {
                    Preconditions.checkState(cameraEffect == null, "Can only have one sharing effect.");
                    cameraEffect = next;
                }
            }
            if (cameraEffect != null) {
                i = cameraEffect.getTargets();
            }
            if (z) {
                i |= 3;
            }
        }
        return i;
    }

    private StreamSharing createOrReuseStreamSharing(Collection<UseCase> collection, boolean z) {
        synchronized (this.mLock) {
            Set<UseCase> streamSharingChildren = getStreamSharingChildren(collection, z);
            if (streamSharingChildren.size() < 2 && (!hasExtension() || !hasVideoCapture(streamSharingChildren))) {
                return null;
            }
            StreamSharing streamSharing = this.mStreamSharing;
            if (streamSharing != null && streamSharing.getChildren().equals(streamSharingChildren)) {
                return (StreamSharing) Objects.requireNonNull(this.mStreamSharing);
            }
            if (!isStreamSharingChildrenCombinationValid(streamSharingChildren)) {
                return null;
            }
            return new StreamSharing(this.mCameraInternal, this.mSecondaryCameraInternal, this.mLayoutSettings, this.mSecondaryLayoutSettings, streamSharingChildren, this.mUseCaseConfigFactory);
        }
    }

    static boolean isStreamSharingChildrenCombinationValid(Collection<UseCase> collection) {
        int[] iArr = {1, 2, 4};
        HashSet hashSet = new HashSet();
        for (UseCase useCase : collection) {
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                if (useCase.isEffectTargetsSupported(i2)) {
                    if (hashSet.contains(Integer.valueOf(i2))) {
                        return false;
                    }
                    hashSet.add(Integer.valueOf(i2));
                }
            }
        }
        return true;
    }

    static Collection<UseCase> calculateCameraUseCases(Collection<UseCase> collection, UseCase useCase, StreamSharing streamSharing) {
        ArrayList arrayList = new ArrayList(collection);
        if (useCase != null) {
            arrayList.add(useCase);
        }
        if (streamSharing != null) {
            arrayList.add(streamSharing);
            arrayList.removeAll(streamSharing.getChildren());
        }
        return arrayList;
    }

    public List<UseCase> getUseCases() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mAppUseCases);
        }
        return arrayList;
    }

    public Collection<UseCase> getCameraUseCases() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mCameraUseCases);
        }
        return arrayList;
    }

    public void attachUseCases() {
        synchronized (this.mLock) {
            if (!this.mAttached) {
                if (!this.mCameraUseCases.isEmpty()) {
                    this.mCameraInternal.setExtendedConfig(this.mCameraConfig);
                    CameraInternal cameraInternal = this.mSecondaryCameraInternal;
                    if (cameraInternal != null) {
                        cameraInternal.setExtendedConfig(this.mCameraConfig);
                    }
                }
                this.mCameraInternal.attachUseCases(this.mCameraUseCases);
                CameraInternal cameraInternal2 = this.mSecondaryCameraInternal;
                if (cameraInternal2 != null) {
                    cameraInternal2.attachUseCases(this.mCameraUseCases);
                }
                restoreInteropConfig();
                Iterator<UseCase> it = this.mCameraUseCases.iterator();
                while (it.hasNext()) {
                    it.next().notifyState();
                }
                this.mAttached = true;
            }
        }
    }

    public void setActiveResumingMode(boolean z) {
        this.mCameraInternal.setActiveResumingMode(z);
    }

    public void detachUseCases() {
        synchronized (this.mLock) {
            if (this.mAttached) {
                this.mCameraInternal.detachUseCases(new ArrayList(this.mCameraUseCases));
                CameraInternal cameraInternal = this.mSecondaryCameraInternal;
                if (cameraInternal != null) {
                    cameraInternal.detachUseCases(new ArrayList(this.mCameraUseCases));
                }
                cacheInteropConfig();
                this.mAttached = false;
            }
        }
    }

    private void restoreInteropConfig() {
        synchronized (this.mLock) {
            if (this.mInteropConfig != null) {
                this.mCameraInternal.getCameraControlInternal().addInteropConfig(this.mInteropConfig);
            }
        }
    }

    private void cacheInteropConfig() {
        synchronized (this.mLock) {
            CameraControlInternal cameraControlInternal = this.mCameraInternal.getCameraControlInternal();
            this.mInteropConfig = cameraControlInternal.getInteropConfig();
            cameraControlInternal.clearInteropConfig();
        }
    }

    private Map<UseCase, StreamSpec> calculateSuggestedStreamSpecs(int i, CameraInfoInternal cameraInfoInternal, Collection<UseCase> collection, Collection<UseCase> collection2, Map<UseCase, ConfigPair> map) {
        Rect rect;
        ArrayList arrayList = new ArrayList();
        String cameraId = cameraInfoInternal.getCameraId();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Iterator<UseCase> it = collection2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            UseCase next = it.next();
            AttachedSurfaceInfo create = AttachedSurfaceInfo.create(this.mCameraDeviceSurfaceManager.transformSurfaceConfig(i, cameraId, next.getImageFormat(), next.getAttachedSurfaceResolution()), next.getImageFormat(), next.getAttachedSurfaceResolution(), ((StreamSpec) Preconditions.checkNotNull(next.getAttachedStreamSpec())).getDynamicRange(), StreamSharing.getCaptureTypes(next), next.getAttachedStreamSpec().getImplementationOptions(), next.getCurrentConfig().getTargetFrameRate(null));
            arrayList.add(create);
            hashMap2.put(create, next);
            hashMap.put(next, next.getAttachedStreamSpec());
        }
        if (!collection.isEmpty()) {
            HashMap hashMap3 = new HashMap();
            HashMap hashMap4 = new HashMap();
            try {
                rect = this.mCameraInternal.getCameraControlInternal().getSensorRect();
            } catch (NullPointerException unused) {
                rect = null;
            }
            SupportedOutputSizesSorter supportedOutputSizesSorter = new SupportedOutputSizesSorter(cameraInfoInternal, rect != null ? TransformUtils.rectToSize(rect) : null);
            boolean z = false;
            for (UseCase useCase : collection) {
                ConfigPair configPair = map.get(useCase);
                UseCaseConfig<?> mergeConfigs = useCase.mergeConfigs(cameraInfoInternal, configPair.mExtendedConfig, configPair.mCameraConfig);
                hashMap3.put(mergeConfigs, useCase);
                hashMap4.put(mergeConfigs, supportedOutputSizesSorter.getSortedSupportedOutputSizes(mergeConfigs));
                if (useCase.getCurrentConfig() instanceof PreviewConfig) {
                    z = ((PreviewConfig) useCase.getCurrentConfig()).getPreviewStabilizationMode() == 2;
                }
            }
            Pair<Map<UseCaseConfig<?>, StreamSpec>, Map<AttachedSurfaceInfo, StreamSpec>> suggestedStreamSpecs = this.mCameraDeviceSurfaceManager.getSuggestedStreamSpecs(i, cameraId, arrayList, hashMap4, z, hasVideoCapture(collection));
            for (Map.Entry entry : hashMap3.entrySet()) {
                hashMap.put((UseCase) entry.getValue(), (StreamSpec) ((Map) suggestedStreamSpecs.first).get(entry.getKey()));
            }
            for (Map.Entry entry2 : ((Map) suggestedStreamSpecs.second).entrySet()) {
                if (hashMap2.containsKey(entry2.getKey())) {
                    hashMap.put((UseCase) hashMap2.get(entry2.getKey()), (StreamSpec) entry2.getValue());
                }
            }
        }
        return hashMap;
    }

    static void updateEffects(List<CameraEffect> list, Collection<UseCase> collection, Collection<UseCase> collection2) {
        List<CameraEffect> effectsOnUseCases = setEffectsOnUseCases(list, collection);
        ArrayList arrayList = new ArrayList(collection2);
        arrayList.removeAll(collection);
        List<CameraEffect> effectsOnUseCases2 = setEffectsOnUseCases(effectsOnUseCases, arrayList);
        if (effectsOnUseCases2.size() > 0) {
            Logger.w(TAG, "Unused effects: " + effectsOnUseCases2);
        }
    }

    private static List<CameraEffect> setEffectsOnUseCases(List<CameraEffect> list, Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList(list);
        for (UseCase useCase : collection) {
            useCase.setEffect(null);
            for (CameraEffect cameraEffect : list) {
                if (useCase.isEffectTargetsSupported(cameraEffect.getTargets())) {
                    Preconditions.checkState(useCase.getEffect() == null, useCase + " already has effect" + useCase.getEffect());
                    useCase.setEffect(cameraEffect);
                    arrayList.remove(cameraEffect);
                }
            }
        }
        return arrayList;
    }

    private void updateViewPortAndSensorToBufferMatrix(Map<UseCase, StreamSpec> map, Collection<UseCase> collection) {
        Map<UseCase, StreamSpec> map2;
        synchronized (this.mLock) {
            if (this.mViewPort == null || collection.isEmpty()) {
                map2 = map;
            } else {
                Integer valueOf = Integer.valueOf(this.mCameraInternal.getCameraInfoInternal().getLensFacing());
                boolean z = true;
                if (valueOf == null) {
                    Logger.w(TAG, "The lens facing is null, probably an external.");
                } else if (valueOf.intValue() != 0) {
                    z = false;
                }
                map2 = map;
                Map<UseCase, Rect> calculateViewPortRects = ViewPorts.calculateViewPortRects(this.mCameraInternal.getCameraControlInternal().getSensorRect(), z, this.mViewPort.getAspectRatio(), this.mCameraInternal.getCameraInfoInternal().getSensorRotationDegrees(this.mViewPort.getRotation()), this.mViewPort.getScaleType(), this.mViewPort.getLayoutDirection(), map2);
                for (UseCase useCase : collection) {
                    useCase.setViewPortCropRect((Rect) Preconditions.checkNotNull(calculateViewPortRects.get(useCase)));
                }
            }
            for (UseCase useCase2 : collection) {
                useCase2.setSensorToBufferTransformMatrix(calculateSensorToBufferTransformMatrix(this.mCameraInternal.getCameraControlInternal().getSensorRect(), ((StreamSpec) Preconditions.checkNotNull(map2.get(useCase2))).getResolution()));
            }
        }
    }

    private static Matrix calculateSensorToBufferTransformMatrix(Rect rect, Size size) {
        Preconditions.checkArgument(rect.width() > 0 && rect.height() > 0, "Cannot compute viewport crop rects zero sized sensor rect.");
        RectF rectF = new RectF(rect);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight()), rectF, Matrix.ScaleToFit.CENTER);
        matrix.invert(matrix);
        return matrix;
    }

    private static class ConfigPair {
        UseCaseConfig<?> mCameraConfig;
        UseCaseConfig<?> mExtendedConfig;

        ConfigPair(UseCaseConfig<?> useCaseConfig, UseCaseConfig<?> useCaseConfig2) {
            this.mExtendedConfig = useCaseConfig;
            this.mCameraConfig = useCaseConfig2;
        }
    }

    private static Map<UseCase, ConfigPair> getConfigs(Collection<UseCase> collection, UseCaseConfigFactory useCaseConfigFactory, UseCaseConfigFactory useCaseConfigFactory2) {
        UseCaseConfig<?> defaultConfig;
        HashMap hashMap = new HashMap();
        for (UseCase useCase : collection) {
            if (StreamSharing.isStreamSharing(useCase)) {
                defaultConfig = generateExtendedStreamSharingConfigFromPreview(useCaseConfigFactory, (StreamSharing) useCase);
            } else {
                defaultConfig = useCase.getDefaultConfig(false, useCaseConfigFactory);
            }
            hashMap.put(useCase, new ConfigPair(defaultConfig, useCase.getDefaultConfig(true, useCaseConfigFactory2)));
        }
        return hashMap;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.camera.core.impl.UseCaseConfig, androidx.camera.core.impl.UseCaseConfig<?>] */
    private static UseCaseConfig<?> generateExtendedStreamSharingConfigFromPreview(UseCaseConfigFactory useCaseConfigFactory, StreamSharing streamSharing) {
        UseCaseConfig<?> defaultConfig = new Preview.Builder().build().getDefaultConfig(false, useCaseConfigFactory);
        if (defaultConfig == null) {
            return null;
        }
        MutableOptionsBundle from = MutableOptionsBundle.from((Config) defaultConfig);
        from.removeOption(TargetConfig.OPTION_TARGET_CLASS);
        return streamSharing.getUseCaseConfigBuilder(from).getUseCaseConfig();
    }

    private void checkUnsupportedFeatureCombinationAndThrow(Collection<UseCase> collection) throws IllegalArgumentException {
        if (hasExtension()) {
            if (hasNonSdrConfig(collection)) {
                throw new IllegalArgumentException("Extensions are only supported for use with standard dynamic range.");
            }
            if (hasUltraHdrImageCapture(collection)) {
                throw new IllegalArgumentException("Extensions are not supported for use with Ultra HDR image capture.");
            }
        }
        synchronized (this.mLock) {
            if (!this.mEffects.isEmpty() && hasUltraHdrImageCapture(collection)) {
                throw new IllegalArgumentException("Ultra HDR image capture does not support for use with CameraEffect.");
            }
        }
    }

    private static boolean hasNonSdrConfig(Collection<UseCase> collection) {
        Iterator<UseCase> it = collection.iterator();
        while (it.hasNext()) {
            if (isNotSdr(it.next().getCurrentConfig().getDynamicRange())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNotSdr(DynamicRange dynamicRange) {
        return (dynamicRange.getBitDepth() == 10) || (dynamicRange.getEncoding() != 1 && dynamicRange.getEncoding() != 0);
    }

    private static boolean hasUltraHdrImageCapture(Collection<UseCase> collection) {
        for (UseCase useCase : collection) {
            if (isImageCapture(useCase)) {
                UseCaseConfig<?> currentConfig = useCase.getCurrentConfig();
                if (currentConfig.containsOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT) && ((Integer) Preconditions.checkNotNull((Integer) currentConfig.retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT))).intValue() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static abstract class CameraId {
        public abstract Identifier getCameraConfigId();

        public abstract String getCameraIdString();

        public static CameraId create(String str, Identifier identifier) {
            return new AutoValue_CameraUseCaseAdapter_CameraId(str, identifier);
        }
    }

    public static final class CameraException extends Exception {
        public CameraException() {
        }

        public CameraException(String str) {
            super(str);
        }

        public CameraException(Throwable th) {
            super(th);
        }
    }

    @Override // androidx.camera.core.Camera
    public CameraControl getCameraControl() {
        return this.mAdapterCameraControl;
    }

    @Override // androidx.camera.core.Camera
    public CameraInfo getCameraInfo() {
        return this.mAdapterCameraInfo;
    }

    public CameraInfo getSecondaryCameraInfo() {
        return this.mAdapterSecondaryCameraInfo;
    }

    @Override // androidx.camera.core.Camera
    public CameraConfig getExtendedConfig() {
        CameraConfig cameraConfig;
        synchronized (this.mLock) {
            cameraConfig = this.mCameraConfig;
        }
        return cameraConfig;
    }

    @Override // androidx.camera.core.Camera
    public boolean isUseCasesCombinationSupported(boolean z, UseCase... useCaseArr) {
        Collection<UseCase> asList = Arrays.asList(useCaseArr);
        if (z) {
            asList = calculateCameraUseCases(asList, null, createOrReuseStreamSharing(asList, true));
        }
        Collection<UseCase> collection = asList;
        synchronized (this.mLock) {
            try {
                try {
                    calculateSuggestedStreamSpecs(getCameraMode(), this.mCameraInternal.getCameraInfoInternal(), collection, Collections.emptyList(), getConfigs(collection, this.mCameraConfig.getUseCaseConfigFactory(), this.mUseCaseConfigFactory));
                } catch (IllegalArgumentException unused) {
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    private UseCase calculatePlaceholderForExtensions(Collection<UseCase> collection, StreamSharing streamSharing) {
        UseCase useCase;
        synchronized (this.mLock) {
            ArrayList arrayList = new ArrayList(collection);
            if (streamSharing != null) {
                arrayList.add(streamSharing);
                arrayList.removeAll(streamSharing.getChildren());
            }
            if (isCoexistingPreviewImageCaptureRequired()) {
                if (isExtraPreviewRequired(arrayList)) {
                    if (isPreview(this.mPlaceholderForExtensions)) {
                        useCase = this.mPlaceholderForExtensions;
                    } else {
                        useCase = createExtraPreview();
                    }
                } else if (isExtraImageCaptureRequired(arrayList)) {
                    if (isImageCapture(this.mPlaceholderForExtensions)) {
                        useCase = this.mPlaceholderForExtensions;
                    } else {
                        useCase = createExtraImageCapture();
                    }
                }
            }
            useCase = null;
        }
        return useCase;
    }

    private boolean isCoexistingPreviewImageCaptureRequired() {
        boolean z;
        synchronized (this.mLock) {
            z = true;
            if (this.mCameraConfig.getUseCaseCombinationRequiredRule() != 1) {
                z = false;
            }
        }
        return z;
    }

    private static boolean isExtraPreviewRequired(Collection<UseCase> collection) {
        boolean z = false;
        boolean z2 = false;
        for (UseCase useCase : collection) {
            if (isPreview(useCase) || StreamSharing.isStreamSharing(useCase)) {
                z2 = true;
            } else if (isImageCapture(useCase)) {
                z = true;
            }
        }
        return z && !z2;
    }

    private static boolean isExtraImageCaptureRequired(Collection<UseCase> collection) {
        boolean z = false;
        boolean z2 = false;
        for (UseCase useCase : collection) {
            if (isPreview(useCase) || StreamSharing.isStreamSharing(useCase)) {
                z = true;
            } else if (isImageCapture(useCase)) {
                z2 = true;
            }
        }
        return z && !z2;
    }

    private static boolean hasVideoCapture(Collection<UseCase> collection) {
        Iterator<UseCase> it = collection.iterator();
        while (it.hasNext()) {
            if (isVideoCapture(it.next())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isVideoCapture(UseCase useCase) {
        if (useCase != null) {
            if (useCase.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE)) {
                return useCase.getCurrentConfig().getCaptureType() == UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE;
            }
            Log.e(TAG, useCase + " UseCase does not have capture type.");
        }
        return false;
    }

    private static boolean isPreview(UseCase useCase) {
        return useCase instanceof Preview;
    }

    private static boolean isImageCapture(UseCase useCase) {
        return useCase instanceof ImageCapture;
    }

    private Preview createExtraPreview() {
        Preview build = new Preview.Builder().setTargetName("Preview-Extra").build();
        build.setSurfaceProvider(new Preview.SurfaceProvider() { // from class: androidx.camera.core.internal.CameraUseCaseAdapter$$ExternalSyntheticLambda1
            @Override // androidx.camera.core.Preview.SurfaceProvider
            public final void onSurfaceRequested(SurfaceRequest surfaceRequest) {
                CameraUseCaseAdapter.lambda$createExtraPreview$1(surfaceRequest);
            }
        });
        return build;
    }

    static /* synthetic */ void lambda$createExtraPreview$1(SurfaceRequest surfaceRequest) {
        final SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(surfaceRequest.getResolution().getWidth(), surfaceRequest.getResolution().getHeight());
        surfaceTexture.detachFromGLContext();
        final Surface surface = new Surface(surfaceTexture);
        surfaceRequest.provideSurface(surface, CameraXExecutors.directExecutor(), new Consumer() { // from class: androidx.camera.core.internal.CameraUseCaseAdapter$$ExternalSyntheticLambda0
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                CameraUseCaseAdapter.lambda$createExtraPreview$0(surface, surfaceTexture, (SurfaceRequest.Result) obj);
            }
        });
    }

    static /* synthetic */ void lambda$createExtraPreview$0(Surface surface, SurfaceTexture surfaceTexture, SurfaceRequest.Result result) {
        surface.release();
        surfaceTexture.release();
    }

    private ImageCapture createExtraImageCapture() {
        return new ImageCapture.Builder().setTargetName("ImageCapture-Extra").build();
    }
}
