package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTextRangeSelector;
import com.airbnb.lottie.model.animatable.AnimatableTextStyle;
import com.airbnb.lottie.model.content.TextRangeUnits;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.Collections;

/* loaded from: classes2.dex */
public class AnimatableTextPropertiesParser {
    private static final JsonReader.Options PROPERTIES_NAMES = JsonReader.Options.of("s", "a");
    private static final JsonReader.Options ANIMATABLE_RANGE_PROPERTIES_NAMES = JsonReader.Options.of("s", "e", "o", "r");
    private static final JsonReader.Options ANIMATABLE_PROPERTIES_NAMES = JsonReader.Options.of("fc", "sc", "sw", "t", "o");

    private AnimatableTextPropertiesParser() {
    }

    public static AnimatableTextProperties parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        jsonReader.beginObject();
        AnimatableTextStyle animatableTextStyle = null;
        AnimatableTextRangeSelector animatableTextRangeSelector = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(PROPERTIES_NAMES);
            if (selectName == 0) {
                animatableTextRangeSelector = parseAnimatableTextRangeSelector(jsonReader, lottieComposition);
            } else if (selectName == 1) {
                animatableTextStyle = parseAnimatableTextStyle(jsonReader, lottieComposition);
            } else {
                jsonReader.skipName();
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return new AnimatableTextProperties(animatableTextStyle, animatableTextRangeSelector);
    }

    private static AnimatableTextRangeSelector parseAnimatableTextRangeSelector(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        jsonReader.beginObject();
        AnimatableIntegerValue animatableIntegerValue = null;
        AnimatableIntegerValue animatableIntegerValue2 = null;
        AnimatableIntegerValue animatableIntegerValue3 = null;
        TextRangeUnits textRangeUnits = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(ANIMATABLE_RANGE_PROPERTIES_NAMES);
            if (selectName == 0) {
                animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
            } else if (selectName == 1) {
                animatableIntegerValue2 = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
            } else if (selectName == 2) {
                animatableIntegerValue3 = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
            } else if (selectName == 3) {
                int nextInt = jsonReader.nextInt();
                if (nextInt != 1 && nextInt != 2) {
                    lottieComposition.addWarning("Unsupported text range units: " + nextInt);
                    textRangeUnits = TextRangeUnits.INDEX;
                } else {
                    textRangeUnits = nextInt == 1 ? TextRangeUnits.PERCENT : TextRangeUnits.INDEX;
                }
            } else {
                jsonReader.skipName();
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (animatableIntegerValue == null && animatableIntegerValue2 != null) {
            animatableIntegerValue = new AnimatableIntegerValue(Collections.singletonList(new Keyframe(0)));
        }
        return new AnimatableTextRangeSelector(animatableIntegerValue, animatableIntegerValue2, animatableIntegerValue3, textRangeUnits);
    }

    private static AnimatableTextStyle parseAnimatableTextStyle(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        jsonReader.beginObject();
        AnimatableColorValue animatableColorValue = null;
        AnimatableColorValue animatableColorValue2 = null;
        AnimatableFloatValue animatableFloatValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        AnimatableIntegerValue animatableIntegerValue = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(ANIMATABLE_PROPERTIES_NAMES);
            if (selectName == 0) {
                animatableColorValue = AnimatableValueParser.parseColor(jsonReader, lottieComposition);
            } else if (selectName == 1) {
                animatableColorValue2 = AnimatableValueParser.parseColor(jsonReader, lottieComposition);
            } else if (selectName == 2) {
                animatableFloatValue = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
            } else if (selectName == 3) {
                animatableFloatValue2 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
            } else if (selectName == 4) {
                animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
            } else {
                jsonReader.skipName();
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return new AnimatableTextStyle(animatableColorValue, animatableColorValue2, animatableFloatValue, animatableFloatValue2, animatableIntegerValue);
    }
}
