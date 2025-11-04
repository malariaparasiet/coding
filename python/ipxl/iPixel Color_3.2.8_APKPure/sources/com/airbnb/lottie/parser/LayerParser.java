package com.airbnb.lottie.parser;

import android.graphics.Color;
import android.graphics.Rect;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes2.dex */
public class LayerParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd", "ao", "bm");
    private static final JsonReader.Options TEXT_NAMES = JsonReader.Options.of("d", "a");
    private static final JsonReader.Options EFFECTS_NAMES = JsonReader.Options.of("ty", "nm");

    private LayerParser() {
    }

    public static Layer parse(LottieComposition lottieComposition) {
        Rect bounds = lottieComposition.getBounds();
        return new Layer(Collections.emptyList(), lottieComposition, "__container", -1L, Layer.LayerType.PRE_COMP, -1L, null, Collections.emptyList(), new AnimatableTransform(), 0, 0, 0, 0.0f, 0.0f, bounds.width(), bounds.height(), null, null, Collections.emptyList(), Layer.MatteType.NONE, null, false, null, null, LBlendMode.NORMAL);
    }

    public static Layer parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        Float f;
        ArrayList arrayList;
        boolean z;
        float f2;
        ArrayList arrayList2;
        float f3;
        float f4;
        Layer.MatteType matteType = Layer.MatteType.NONE;
        LBlendMode lBlendMode = LBlendMode.NORMAL;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        jsonReader.beginObject();
        boolean z2 = false;
        float f5 = 0.0f;
        Float valueOf = Float.valueOf(0.0f);
        float f6 = 1.0f;
        Float valueOf2 = Float.valueOf(1.0f);
        LBlendMode lBlendMode2 = lBlendMode;
        AnimatableTextFrame animatableTextFrame = null;
        AnimatableTextProperties animatableTextProperties = null;
        AnimatableFloatValue animatableFloatValue = null;
        BlurEffect blurEffect = null;
        DropShadowEffect dropShadowEffect = null;
        boolean z3 = false;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z4 = false;
        long j = 0;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        Layer.MatteType matteType2 = matteType;
        long j2 = -1;
        String str = "UNSET";
        String str2 = null;
        AnimatableTransform animatableTransform = null;
        Layer.LayerType layerType = null;
        String str3 = null;
        float f11 = 0.0f;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    str = jsonReader.nextString();
                    z2 = false;
                    break;
                case 1:
                    j = jsonReader.nextInt();
                    z2 = false;
                    break;
                case 2:
                    str3 = jsonReader.nextString();
                    z2 = false;
                    break;
                case 3:
                    f2 = f5;
                    int nextInt = jsonReader.nextInt();
                    if (nextInt < Layer.LayerType.UNKNOWN.ordinal()) {
                        layerType = Layer.LayerType.values()[nextInt];
                    } else {
                        layerType = Layer.LayerType.UNKNOWN;
                    }
                    f5 = f2;
                    z2 = false;
                    break;
                case 4:
                    j2 = jsonReader.nextInt();
                    z2 = false;
                    break;
                case 5:
                    i = (int) (jsonReader.nextInt() * Utils.dpScale());
                    z2 = false;
                    break;
                case 6:
                    i2 = (int) (jsonReader.nextInt() * Utils.dpScale());
                    z2 = false;
                    break;
                case 7:
                    i3 = Color.parseColor(jsonReader.nextString());
                    z2 = false;
                    break;
                case 8:
                    animatableTransform = AnimatableTransformParser.parse(jsonReader, lottieComposition);
                    z2 = false;
                    break;
                case 9:
                    f2 = f5;
                    int nextInt2 = jsonReader.nextInt();
                    if (nextInt2 >= Layer.MatteType.values().length) {
                        lottieComposition.addWarning("Unsupported matte type: " + nextInt2);
                    } else {
                        matteType2 = Layer.MatteType.values()[nextInt2];
                        int i4 = AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[matteType2.ordinal()];
                        if (i4 == 1) {
                            lottieComposition.addWarning("Unsupported matte type: Luma");
                        } else if (i4 == 2) {
                            lottieComposition.addWarning("Unsupported matte type: Luma Inverted");
                        }
                        lottieComposition.incrementMatteOrMaskCount(1);
                    }
                    f5 = f2;
                    z2 = false;
                    break;
                case 10:
                    f2 = f5;
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        arrayList3.add(MaskParser.parse(jsonReader, lottieComposition));
                    }
                    lottieComposition.incrementMatteOrMaskCount(arrayList3.size());
                    jsonReader.endArray();
                    f5 = f2;
                    z2 = false;
                    break;
                case 11:
                    ArrayList arrayList5 = arrayList4;
                    f2 = f5;
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        ContentModel parse = ContentModelParser.parse(jsonReader, lottieComposition);
                        ArrayList arrayList6 = arrayList5;
                        if (parse != null) {
                            arrayList6.add(parse);
                        }
                        arrayList5 = arrayList6;
                    }
                    arrayList4 = arrayList5;
                    jsonReader.endArray();
                    f5 = f2;
                    z2 = false;
                    break;
                case 12:
                    arrayList2 = arrayList4;
                    f3 = f5;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        int selectName = jsonReader.selectName(TEXT_NAMES);
                        if (selectName == 0) {
                            animatableTextFrame = AnimatableValueParser.parseDocumentData(jsonReader, lottieComposition);
                        } else if (selectName == 1) {
                            jsonReader.beginArray();
                            if (jsonReader.hasNext()) {
                                animatableTextProperties = AnimatableTextPropertiesParser.parse(jsonReader, lottieComposition);
                            }
                            while (jsonReader.hasNext()) {
                                jsonReader.skipValue();
                            }
                            jsonReader.endArray();
                        } else {
                            jsonReader.skipName();
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    f5 = f3;
                    arrayList4 = arrayList2;
                    z2 = false;
                    break;
                case 13:
                    arrayList2 = arrayList4;
                    f3 = f5;
                    jsonReader.beginArray();
                    ArrayList arrayList7 = new ArrayList();
                    while (jsonReader.hasNext()) {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            int selectName2 = jsonReader.selectName(EFFECTS_NAMES);
                            if (selectName2 == 0) {
                                int nextInt3 = jsonReader.nextInt();
                                if (nextInt3 == 29) {
                                    blurEffect = BlurEffectParser.parse(jsonReader, lottieComposition);
                                } else if (nextInt3 == 25) {
                                    dropShadowEffect = new DropShadowEffectParser().parse(jsonReader, lottieComposition);
                                }
                            } else if (selectName2 == 1) {
                                arrayList7.add(jsonReader.nextString());
                            } else {
                                jsonReader.skipName();
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                    }
                    jsonReader.endArray();
                    lottieComposition.addWarning("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList7);
                    f5 = f3;
                    arrayList4 = arrayList2;
                    z2 = false;
                    break;
                case 14:
                    f6 = (float) jsonReader.nextDouble();
                    z2 = false;
                    break;
                case 15:
                    f11 = (float) jsonReader.nextDouble();
                    z2 = false;
                    break;
                case 16:
                    arrayList2 = arrayList4;
                    f9 = (float) (jsonReader.nextDouble() * Utils.dpScale());
                    arrayList4 = arrayList2;
                    z2 = false;
                    break;
                case 17:
                    f3 = f5;
                    arrayList2 = arrayList4;
                    f10 = (float) (jsonReader.nextDouble() * Utils.dpScale());
                    f5 = f3;
                    arrayList4 = arrayList2;
                    z2 = false;
                    break;
                case 18:
                    f7 = (float) jsonReader.nextDouble();
                    break;
                case 19:
                    f8 = (float) jsonReader.nextDouble();
                    break;
                case 20:
                    animatableFloatValue = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, z2);
                    break;
                case 21:
                    str2 = jsonReader.nextString();
                    break;
                case 22:
                    z4 = jsonReader.nextBoolean();
                    break;
                case 23:
                    f4 = f5;
                    z3 = jsonReader.nextInt() == 1 ? true : z2;
                    f5 = f4;
                    break;
                case 24:
                    int nextInt4 = jsonReader.nextInt();
                    if (nextInt4 >= LBlendMode.values().length) {
                        f4 = f5;
                        lottieComposition.addWarning("Unsupported Blend Mode: " + nextInt4);
                        lBlendMode2 = LBlendMode.NORMAL;
                        f5 = f4;
                        break;
                    } else {
                        lBlendMode2 = LBlendMode.values()[nextInt4];
                        break;
                    }
                default:
                    f2 = f5;
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    f5 = f2;
                    z2 = false;
                    break;
            }
        }
        float f12 = f5;
        jsonReader.endObject();
        ArrayList arrayList8 = new ArrayList();
        if (f7 > f12) {
            arrayList = arrayList4;
            z = z3;
            f = valueOf;
            arrayList8.add(new Keyframe(lottieComposition, valueOf, valueOf, null, 0.0f, Float.valueOf(f7)));
        } else {
            f = valueOf;
            arrayList = arrayList4;
            z = z3;
        }
        if (f8 <= f12) {
            f8 = lottieComposition.getEndFrame();
        }
        arrayList8.add(new Keyframe(lottieComposition, valueOf2, valueOf2, null, f7, Float.valueOf(f8)));
        arrayList8.add(new Keyframe(lottieComposition, f, f, null, f8, Float.valueOf(Float.MAX_VALUE)));
        if (str.endsWith(".ai") || "ai".equals(str2)) {
            lottieComposition.addWarning("Convert your Illustrator layers to shape layers.");
        }
        if (z) {
            if (animatableTransform == null) {
                animatableTransform = new AnimatableTransform();
            }
            animatableTransform.setAutoOrient(z);
        }
        return new Layer(arrayList, lottieComposition, str, j, layerType, j2, str3, arrayList3, animatableTransform, i, i2, i3, f6, f11, f9, f10, animatableTextFrame, animatableTextProperties, arrayList8, matteType2, animatableFloatValue, z4, blurEffect, dropShadowEffect, lBlendMode2);
    }

    /* renamed from: com.airbnb.lottie.parser.LayerParser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType = iArr;
            try {
                iArr[Layer.MatteType.LUMA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[Layer.MatteType.LUMA_INVERTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
