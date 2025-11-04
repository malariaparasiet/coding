package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes2.dex */
class GradientStrokeParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "g", "o", "t", "s", "e", "w", "lc", "lj", "ml", "hd", "d");
    private static final JsonReader.Options GRADIENT_NAMES = JsonReader.Options.of("p", "k");
    private static final JsonReader.Options DASH_PATTERN_NAMES = JsonReader.Options.of("n", "v");

    private GradientStrokeParser() {
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0027. Please report as an issue. */
    static GradientStroke parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        AnimatableIntegerValue animatableIntegerValue;
        ArrayList arrayList = new ArrayList();
        GradientType gradientType = null;
        String str = null;
        AnimatableGradientColorValue animatableGradientColorValue = null;
        AnimatablePointValue animatablePointValue = null;
        AnimatablePointValue animatablePointValue2 = null;
        AnimatableFloatValue animatableFloatValue = null;
        ShapeStroke.LineCapType lineCapType = null;
        ShapeStroke.LineJoinType lineJoinType = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        float f = 0.0f;
        boolean z = false;
        AnimatableIntegerValue animatableIntegerValue2 = null;
        while (jsonReader.hasNext()) {
            GradientType gradientType2 = gradientType;
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    str = jsonReader.nextString();
                    gradientType = gradientType2;
                    break;
                case 1:
                    animatableIntegerValue = animatableIntegerValue2;
                    jsonReader.beginObject();
                    int i = -1;
                    while (jsonReader.hasNext()) {
                        int selectName = jsonReader.selectName(GRADIENT_NAMES);
                        if (selectName == 0) {
                            i = jsonReader.nextInt();
                        } else if (selectName == 1) {
                            animatableGradientColorValue = AnimatableValueParser.parseGradientColor(jsonReader, lottieComposition, i);
                        } else {
                            jsonReader.skipName();
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    gradientType = gradientType2;
                    animatableIntegerValue2 = animatableIntegerValue;
                    break;
                case 2:
                    animatableIntegerValue2 = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
                    gradientType = gradientType2;
                    break;
                case 3:
                    animatableIntegerValue = animatableIntegerValue2;
                    gradientType = jsonReader.nextInt() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    animatableIntegerValue2 = animatableIntegerValue;
                    break;
                case 4:
                    animatablePointValue = AnimatableValueParser.parsePoint(jsonReader, lottieComposition);
                    gradientType = gradientType2;
                    break;
                case 5:
                    animatablePointValue2 = AnimatableValueParser.parsePoint(jsonReader, lottieComposition);
                    gradientType = gradientType2;
                    break;
                case 6:
                    animatableFloatValue = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                    gradientType = gradientType2;
                    break;
                case 7:
                    animatableIntegerValue = animatableIntegerValue2;
                    lineCapType = ShapeStroke.LineCapType.values()[jsonReader.nextInt() - 1];
                    gradientType = gradientType2;
                    animatableIntegerValue2 = animatableIntegerValue;
                    break;
                case 8:
                    animatableIntegerValue = animatableIntegerValue2;
                    lineJoinType = ShapeStroke.LineJoinType.values()[jsonReader.nextInt() - 1];
                    gradientType = gradientType2;
                    animatableIntegerValue2 = animatableIntegerValue;
                    break;
                case 9:
                    animatableIntegerValue = animatableIntegerValue2;
                    f = (float) jsonReader.nextDouble();
                    gradientType = gradientType2;
                    animatableIntegerValue2 = animatableIntegerValue;
                    break;
                case 10:
                    z = jsonReader.nextBoolean();
                    gradientType = gradientType2;
                    break;
                case 11:
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        jsonReader.beginObject();
                        String str2 = null;
                        AnimatableFloatValue animatableFloatValue3 = null;
                        while (jsonReader.hasNext()) {
                            int selectName2 = jsonReader.selectName(DASH_PATTERN_NAMES);
                            if (selectName2 != 0) {
                                AnimatableIntegerValue animatableIntegerValue3 = animatableIntegerValue2;
                                if (selectName2 == 1) {
                                    animatableFloatValue3 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                                } else {
                                    jsonReader.skipName();
                                    jsonReader.skipValue();
                                }
                                animatableIntegerValue2 = animatableIntegerValue3;
                            } else {
                                str2 = jsonReader.nextString();
                            }
                        }
                        AnimatableIntegerValue animatableIntegerValue4 = animatableIntegerValue2;
                        jsonReader.endObject();
                        if (str2.equals("o")) {
                            animatableFloatValue2 = animatableFloatValue3;
                        } else if (str2.equals("d") || str2.equals("g")) {
                            lottieComposition.setHasDashPattern(true);
                            arrayList.add(animatableFloatValue3);
                            animatableIntegerValue2 = animatableIntegerValue4;
                        }
                        animatableIntegerValue2 = animatableIntegerValue4;
                    }
                    animatableIntegerValue = animatableIntegerValue2;
                    jsonReader.endArray();
                    if (arrayList.size() == 1) {
                        arrayList.add((AnimatableFloatValue) arrayList.get(0));
                    }
                    gradientType = gradientType2;
                    animatableIntegerValue2 = animatableIntegerValue;
                    break;
                default:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    gradientType = gradientType2;
                    break;
            }
        }
        AnimatableIntegerValue animatableIntegerValue5 = animatableIntegerValue2;
        return new GradientStroke(str, gradientType, animatableGradientColorValue, animatableIntegerValue5 == null ? new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100))) : animatableIntegerValue5, animatablePointValue, animatablePointValue2, animatableFloatValue, lineCapType, lineJoinType, f, arrayList, animatableFloatValue2, z);
    }
}
