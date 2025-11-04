package com.wifiled.ipixels.core.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.musiclib.player.service.PlayerService;
import java.lang.Character;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: CharacterUtils.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u000e\u0010\b\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u000e\u0010\t\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u000e\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0007\u001a\u000e\u0010\r\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003\u001a\u000e\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0007\u001a\u000e\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0007\u001a\u000e\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u000e\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0007\u001a\u000e\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0007\u001a\u000e\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u000e\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0014\u0010\u000e\u001a\u00020\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017\u001a\u0010\u0010\u0019\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u001a\u000e\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003\u001a\u000e\u0010\u001c\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0007\u001a\u000e\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0010\u0010\u001f\u001a\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u001a\u0016\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%\u001a\u0016\u0010&\u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%¨\u0006'"}, d2 = {"isKoreanCharacter", "", "ch", "", "isChinese", "c", TextBundle.TEXT_ENTRY, "", "containsTamil", "containsDevanagari", "isTamilCharacter", "isChineseStr", "str", "isVietnamese", "containsVietnamese", "input", "containsCzech", "containsTurkishChars", "containsGerman", "containsFrenchOrSpanishOrDutchChars", "containsGreekChars", "containsCroatianCharsOrPolishChars", "mTexts", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "isAlphabetic", "isJapaneseCharacter", "isEnglish", "containsEnglish", "isCyrillicCharacter", "isSpecialSymbol", "containsCyrillic", "getBitmapFromResource", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "resourceId", "", "getBitmapFromDrawable", "app_googleRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CharacterUtilsKt {
    public static final boolean isEnglish(char c) {
        if ('A' > c || c >= '[') {
            return 'a' <= c && c < '{';
        }
        return true;
    }

    public static final boolean isKoreanCharacter(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.HANGUL_SYLLABLES || of == Character.UnicodeBlock.HANGUL_JAMO || of == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO;
    }

    public static final boolean isChinese(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.GENERAL_PUNCTUATION || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS || of == Character.UnicodeBlock.VERTICAL_FORMS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT;
    }

    public static final boolean isKoreanCharacter(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (text.length() <= 0) {
            return false;
        }
        Character.UnicodeBlock of = Character.UnicodeBlock.of(text.charAt(0));
        return of == Character.UnicodeBlock.HANGUL_SYLLABLES || of == Character.UnicodeBlock.HANGUL_JAMO || of == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO;
    }

    public static final boolean containsTamil(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        int length = text.length();
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual(Character.UnicodeBlock.of(text.charAt(i)), Character.UnicodeBlock.TAMIL)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean containsDevanagari(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        int length = text.length();
        int i = 0;
        while (i < length) {
            int codePointAt = text.codePointAt(i);
            if (2304 <= codePointAt && codePointAt < 2432) {
                return true;
            }
            if (43232 <= codePointAt && codePointAt < 43264) {
                return true;
            }
            if (7376 <= codePointAt && codePointAt < 7424) {
                return true;
            }
            i += Character.charCount(codePointAt);
        }
        return false;
    }

    public static final boolean isTamilCharacter(char c) {
        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.TAMIL;
    }

    public static final boolean isChineseStr(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (isChinese(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isVietnamese(char c) {
        for (int[] iArr : new int[][]{new int[]{192, PlayerService.MODE_CHANGE}, new int[]{7840, 7929}, new int[]{7840, 7929}, new int[]{272, 273}, new int[]{7844, 7879}, new int[]{7880, 7921}}) {
            if (c >= iArr[0] && c <= iArr[1]) {
                return true;
            }
        }
        return false;
    }

    public static final boolean containsVietnamese(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return new Regex(".*[ĂăĄąẠạẢảẤấẦầẨẩẪẫẬậẮắẰằẲẳẴẵẶặẸẹẺẻẼẽẾếỀềỂểỄễỆệỈỉỊịỌọỎỏỐốỒồỔổỖỗỘộỚớỜờỞởỠỡỢợỤụỦủỨứỪừỬửỮữỰựỲỳỴỵỶỷỸỹỺỻỼỽỾỿ].*").matches(input);
    }

    public static final boolean containsCzech(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char charAt = input.charAt(i);
            if (256 <= charAt && charAt < 384) {
                return true;
            }
            if (448 <= charAt && charAt < 512) {
                return true;
            }
        }
        return false;
    }

    public static final boolean containsTurkishChars(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        List listOf = CollectionsKt.listOf((Object[]) new Character[]{(char) 199, (char) 231, (char) 286, (char) 287, (char) 304, (char) 305, (char) 214, (char) 246, (char) 350, (char) 351, (char) 220, (char) 252});
        String str = text;
        for (int i = 0; i < str.length(); i++) {
            if (listOf.contains(Character.valueOf(str.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    public static final boolean containsGerman(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return new Regex(".*[äöüßÄÖÜ].*").matches(input);
    }

    public static final boolean containsFrenchOrSpanishOrDutchChars(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char charAt = input.charAt(i);
            if (192 <= charAt && charAt < 256) {
                return true;
            }
            if (256 <= charAt && charAt < 384) {
                return true;
            }
        }
        return false;
    }

    public static final boolean containsGreekChars(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char charAt = text.charAt(i);
            if (880 <= charAt && charAt < 1024) {
                return true;
            }
            if (7936 <= charAt && charAt < 8192) {
                return true;
            }
        }
        return false;
    }

    public static final boolean containsCroatianCharsOrPolishChars(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char charAt = text.charAt(i);
            if (256 <= charAt && charAt < 384) {
                return true;
            }
            if (384 <= charAt && charAt < 592) {
                return true;
            }
        }
        return false;
    }

    public static final boolean containsVietnamese(List<TextEmojiVO> mTexts) {
        Intrinsics.checkNotNullParameter(mTexts, "mTexts");
        if (mTexts.isEmpty()) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<TextEmojiVO> it = mTexts.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        char[] charArray = sb2.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        for (char c : charArray) {
            if (isVietnamese(c)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isAlphabetic(String str) {
        if (str == null) {
            return false;
        }
        String str2 = str;
        for (int i = 0; i < str2.length(); i++) {
            if (!Character.isLetter(str2.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isJapaneseCharacter(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.HIRAGANA || of == Character.UnicodeBlock.KATAKANA || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B;
    }

    public static final boolean containsEnglish(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        char[] charArray = str.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        for (char c : charArray) {
            if (isEnglish(c)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isCyrillicCharacter(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.CYRILLIC || of == Character.UnicodeBlock.CYRILLIC_EXTENDED_A || of == Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY || of == Character.UnicodeBlock.CYRILLIC_EXTENDED_B;
    }

    public static final boolean isSpecialSymbol(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return Intrinsics.areEqual(of, Character.UnicodeBlock.GEOMETRIC_SHAPES) || Intrinsics.areEqual(of, Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS) || Intrinsics.areEqual(of, Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS) || Intrinsics.areEqual(of, Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) || Intrinsics.areEqual(of, Character.UnicodeBlock.DINGBATS);
    }

    public static final boolean containsCyrillic(String str) {
        String str2 = str;
        if (str2 != null && str2.length() != 0) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (Intrinsics.areEqual(Character.UnicodeBlock.CYRILLIC, Character.UnicodeBlock.of(charAt)) || Intrinsics.areEqual(Character.UnicodeBlock.CYRILLIC_EXTENDED_A, Character.UnicodeBlock.of(charAt)) || Intrinsics.areEqual(Character.UnicodeBlock.CYRILLIC_EXTENDED_B, Character.UnicodeBlock.of(charAt)) || Intrinsics.areEqual(Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY, Character.UnicodeBlock.of(charAt))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final Bitmap getBitmapFromResource(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        Intrinsics.checkNotNullExpressionValue(decodeResource, "decodeResource(...)");
        return decodeResource;
    }

    public static final Bitmap getBitmapFromDrawable(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i, options);
        Intrinsics.checkNotNull(decodeResource);
        return decodeResource;
    }
}
