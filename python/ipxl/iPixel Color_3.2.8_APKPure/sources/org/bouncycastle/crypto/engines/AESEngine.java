package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.Command;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import java.lang.reflect.Array;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DefaultMultiBlockCipher;
import org.bouncycastle.crypto.MultiBlockCipher;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class AESEngine extends DefaultMultiBlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;
    private byte[] s;
    private static final byte[] S = {99, JSONB.Constants.BC_STR_UTF16LE, 119, JSONB.Constants.BC_STR_UTF16, -14, 107, 111, -59, JSONB.Constants.BC_INT32_BYTE_MIN, 1, 103, 43, -2, JSONB.Constants.BC_INT64_BYTE_MAX, JSONB.Constants.BC_TIMESTAMP_MILLIS, 118, -54, -126, -55, JSONB.Constants.BC_STR_UTF16BE, -6, 89, JSONB.Constants.BC_INT32_SHORT_MAX, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MINUTES, -44, -94, JSONB.Constants.BC_NULL, -100, JSONB.Constants.BC_ARRAY, 114, JSONB.Constants.BC_INT64_SHORT_MIN, JSONB.Constants.BC_FLOAT, -3, JSONB.Constants.BC_REFERENCE, 38, 54, 63, -9, -52, 52, JSONB.Constants.BC_OBJECT_END, -27, -15, 113, JSONB.Constants.BC_INT64_NUM_MIN, 49, 21, 4, JSONB.Constants.BC_INT64_SHORT_MAX, 35, -61, 24, -106, 5, -102, 7, 18, ByteCompanionObject.MIN_VALUE, -30, -21, 39, JSONB.Constants.BC_DOUBLE_NUM_0, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, JSONB.Constants.BC_DOUBLE_NUM_1, 41, -29, JSONB.Constants.BC_INT32_NUM_MAX, -124, 83, -47, 0, -19, 32, -4, JSONB.Constants.BC_TRUE, 91, 106, -53, JSONB.Constants.BC_INT64, 57, JSONB.Constants.BC_STR_ASCII_FIX_1, 76, 88, -49, JSONB.Constants.BC_INT64_BYTE_ZERO, -17, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, -5, 67, JSONB.Constants.BC_STR_ASCII_FIX_4, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, JSONB.Constants.BC_LOCAL_DATETIME, 81, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_INT32_SHORT_MIN, -113, JSONB.Constants.BC_TYPED_ANY, -99, JSONB.Constants.BC_INT32_BYTE_ZERO, -11, -68, JSONB.Constants.BC_FLOAT_INT, -38, 33, JSONB.Constants.BC_INT32_NUM_16, -1, -13, -46, -51, 12, 19, -20, 95, -105, JSONB.Constants.BC_INT32_SHORT_ZERO, 23, JSONB.Constants.BC_INT64_SHORT_ZERO, JSONB.Constants.BC_LOCAL_TIME, JSONB.Constants.BC_STR_GB18030, Base64.padSymbol, 100, 93, 25, 115, 96, -127, 79, JL_Constant.PREFIX_FLAG_SECOND, 34, 42, JSONB.Constants.BC_CHAR, -120, 70, -18, JSONB.Constants.BC_DECIMAL_LONG, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, JSONB.Constants.BC_TIMESTAMP_SECONDS, 98, JSONB.Constants.BC_BINARY, -107, -28, JSONB.Constants.BC_STR_ASCII, -25, JSONB.Constants.BC_INT64_BYTE_MIN, 55, JSONB.Constants.BC_STR_ASCII_FIX_36, -115, -43, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_LOCAL_DATE, 108, 86, -12, -22, 101, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_TIMESTAMP, 8, -70, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 37, 46, 28, JSONB.Constants.BC_OBJECT, JSONB.Constants.BC_DOUBLE_LONG, -58, -24, -35, 116, 31, 75, JSONB.Constants.BC_INT8, -117, -118, 112, 62, JSONB.Constants.BC_DOUBLE, 102, JSONB.Constants.BC_INT32, 3, -10, 14, 97, 53, 87, JSONB.Constants.BC_DECIMAL, -122, -63, 29, -98, -31, -8, -104, 17, JSONB.Constants.BC_STR_ASCII_FIX_32, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, JSONB.Constants.BC_INT64_INT, -26, 66, 104, 65, -103, 45, 15, JSONB.Constants.BC_FALSE, 84, JSONB.Constants.BC_BIGINT, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER};
    private static final byte[] Si = {82, 9, 106, -43, JSONB.Constants.BC_INT32_BYTE_MIN, 54, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_INT32_BYTE_ZERO, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_INT32_SHORT_MIN, JSONB.Constants.BC_ARRAY_FIX_MAX, -98, -127, -13, JSONB.Constants.BC_INT64_BYTE_MAX, -5, JSONB.Constants.BC_STR_UTF16LE, -29, 57, -126, -101, JSONB.Constants.BC_INT32_NUM_MAX, -1, -121, 52, -114, 67, JSONB.Constants.BC_INT32_SHORT_ZERO, JSONB.Constants.BC_INT64_SHORT_ZERO, -34, -23, -53, 84, JSONB.Constants.BC_STR_UTF16, -108, 50, JSONB.Constants.BC_OBJECT, -62, 35, Base64.padSymbol, -18, 76, -107, 11, 66, -6, -61, JSONB.Constants.BC_STR_ASCII_FIX_5, 8, 46, -95, 102, 40, -39, 36, JSONB.Constants.BC_DOUBLE_NUM_0, 118, 91, -94, 73, JSONB.Constants.BC_STR_ASCII_FIX_36, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, -44, JSONB.Constants.BC_ARRAY, 92, -52, 93, 101, JSONB.Constants.BC_FLOAT_INT, JSONB.Constants.BC_TYPED_ANY, 108, 112, JSONB.Constants.BC_INT32, 80, -3, -19, JSONB.Constants.BC_DECIMAL, -38, 94, 21, 70, 87, JSONB.Constants.BC_LOCAL_TIME, -115, -99, -124, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_INT64_NUM_MIN, JSONB.Constants.BC_TIMESTAMP_MILLIS, 0, -116, -68, -45, 10, -9, -28, 88, 5, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_DOUBLE_NUM_1, 69, 6, JSONB.Constants.BC_INT64_BYTE_ZERO, 44, 30, -113, -54, 63, 15, 2, -63, JSONB.Constants.BC_NULL, JSONB.Constants.BC_INT8, 3, 1, 19, -118, 107, 58, JSONB.Constants.BC_BINARY, 17, 65, 79, 103, JL_Constant.PREFIX_FLAG_SECOND, -22, -105, -14, -49, -50, JSONB.Constants.BC_INT32_NUM_MIN, JSONB.Constants.BC_DOUBLE_LONG, -26, 115, -106, JSONB.Constants.BC_TIMESTAMP_SECONDS, 116, 34, -25, JSONB.Constants.BC_TIMESTAMP_MINUTES, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, JSONB.Constants.BC_INT32_SHORT_MAX, -15, 26, 113, 29, 41, -59, -119, 111, JSONB.Constants.BC_FLOAT, 98, 14, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, 24, JSONB.Constants.BC_INT64, 27, -4, 86, 62, 75, -58, -46, JSONB.Constants.BC_STR_ASCII, 32, -102, -37, JSONB.Constants.BC_INT64_SHORT_MIN, -2, JSONB.Constants.BC_STR_ASCII_FIX_MAX, -51, 90, -12, 31, -35, JSONB.Constants.BC_LOCAL_DATETIME, 51, -120, 7, JSONB.Constants.BC_INT64_SHORT_MAX, 49, JSONB.Constants.BC_TRUE, 18, JSONB.Constants.BC_INT32_NUM_16, 89, 39, ByteCompanionObject.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, JSONB.Constants.BC_LOCAL_DATE, 25, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_STR_ASCII_FIX_1, 13, 45, -27, JSONB.Constants.BC_STR_UTF8, -97, JSONB.Constants.BC_REFERENCE, -55, -100, -17, -96, -32, 59, JSONB.Constants.BC_STR_ASCII_FIX_4, JSONB.Constants.BC_TIMESTAMP, 42, -11, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_INT64_BYTE_MIN, -21, JSONB.Constants.BC_BIGINT, 60, -125, 83, -103, 97, 23, 43, 4, JSONB.Constants.BC_STR_GB18030, -70, 119, -42, 38, -31, JSONB.Constants.BC_STR_ASCII_FIX_32, 20, 99, 85, 33, 12, JSONB.Constants.BC_STR_UTF16BE};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, Opcodes.LOOKUPSWITCH, 77, Opcodes.IFNE, 47, 94, 188, 99, Opcodes.IFNULL, Opcodes.DCMPL, 53, 106, Command.CMD_GET_DEV_MD5, Opcodes.PUTSTATIC, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, Opcodes.I2B};
    private static final int[] T0 = {-1520213050, -2072216328, -1720223762, -1921287178, 234025727, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 200339707, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 1055122397, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 1429418854, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 1713513028, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 403179536, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 856756514, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};
    private static final int[] Tinv0 = {1353184337, 1399144830, -1012656358, -1772214470, -882136261, -247096033, -1420232020, -1828461749, 1442459680, -160598355, -1854485368, 625738485, -52959921, -674551099, -2143013594, -1885117771, 1230680542, 1729870373, -1743852987, -507445667, 41234371, 317738113, -1550367091, -956705941, -413167869, -1784901099, -344298049, -631680363, 763608788, -752782248, 694804553, 1154009486, 1787413109, 2021232372, 1799248025, -579749593, -1236278850, 397248752, 1722556617, -1271214467, 407560035, -2110711067, 1613975959, 1165972322, -529046351, -2068943941, 480281086, -1809118983, 1483229296, 436028815, -2022908268, -1208452270, 601060267, -503166094, 1468997603, 715871590, 120122290, 63092015, -1703164538, -1526188077, -226023376, -1297760477, -1167457534, 1552029421, 723308426, -1833666137, -252573709, -1578997426, -839591323, -708967162, 526529745, -1963022652, -1655493068, -1604979806, 853641733, 1978398372, 971801355, -1427152832, 111112542, 1360031421, -108388034, 1023860118, -1375387939, 1186850381, -1249028975, 90031217, 1876166148, -15380384, 620468249, -1746289194, -868007799, 2006899047, -1119688528, -2004121337, 945494503, -605108103, 1191869601, -384875908, -920746760, 0, -2088337399, 1223502642, -1401941730, 1316117100, -67170563, 1446544655, 517320253, 658058550, 1691946762, 564550760, -783000677, 976107044, -1318647284, 266819475, -761860428, -1634624741, 1338359936, -1574904735, 1766553434, 370807324, 179999714, -450191168, 1138762300, 488053522, 185403662, -1379431438, -1180125651, -928440812, -2061897385, 1275557295, -1143105042, -44007517, -1624899081, -1124765092, -985962940, 880737115, 1982415755, -590994485, 1761406390, 1676797112, -891538985, 277177154, 1076008723, 538035844, 2099530373, -130171950, 288553390, 1839278535, 1261411869, -214912292, -330136051, -790380169, 1813426987, -1715900247, -95906799, 577038663, -997393240, 440397984, -668172970, -275762398, -951170681, -1043253031, -22885748, 906744984, -813566554, 685669029, 646887386, -1530942145, -459458004, 227702864, -1681105046, 1648787028, -1038905866, -390539120, 1593260334, -173030526, -1098883681, 2090061929, -1456614033, -1290656305, 999926984, -1484974064, 1852021992, 2075868123, 158869197, -199730834, 28809964, -1466282109, 1701746150, 2129067946, 147831841, -420997649, -644094022, -835293366, -737566742, -696471511, -1347247055, 824393514, 815048134, -1067015627, 935087732, -1496677636, -1328508704, 366520115, 1251476721, -136647615, 240176511, 804688151, -1915335306, 1303441219, 1414376140, -553347356, -474623586, 461924940, -1205916479, 2136040774, 82468509, 1563790337, 1937016826, 776014843, 1511876531, 1389550482, 861278441, 323475053, -1939744870, 2047648055, -1911228327, -1992551445, -299390514, 902390199, -303751967, 1018251130, 1507840668, 1064563285, 2043548696, -1086863501, -355600557, 1537932639, 342834655, -2032450440, -2114736182, 1053059257, 741614648, 1598071746, 1925389590, 203809468, -1958134744, 1100287487, 1895934009, -558691320, -1662733096, -1866377628, 1636092795, 1890988757, 1952214088, 1113045200};

    public AESEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256));
    }

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = (m5 & i) << 2;
        int i3 = i & m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private int bitsOfSecurity() {
        if (this.WorkingKey == null) {
            return 256;
        }
        return (r0.length - 7) << 5;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int i3 = this.ROUNDS;
        int[] iArr2 = iArr[i3];
        boolean z = false;
        int i4 = littleEndianToInt ^ iArr2[0];
        int i5 = 1;
        int i6 = littleEndianToInt2 ^ iArr2[1];
        char c = 2;
        int i7 = littleEndianToInt3 ^ iArr2[2];
        int i8 = i3 - 1;
        char c2 = 3;
        int i9 = littleEndianToInt4 ^ iArr2[3];
        while (i8 > i5) {
            int[] iArr3 = Tinv0;
            boolean z2 = z;
            int i10 = i5;
            int shift = (((iArr3[i4 & 255] ^ shift(iArr3[(i9 >> 8) & 255], 24)) ^ shift(iArr3[(i7 >> 16) & 255], 16)) ^ shift(iArr3[(i6 >> 24) & 255], 8)) ^ iArr[i8][z2 ? 1 : 0];
            char c3 = c;
            int shift2 = (((iArr3[i6 & 255] ^ shift(iArr3[(i4 >> 8) & 255], 24)) ^ shift(iArr3[(i9 >> 16) & 255], 16)) ^ shift(iArr3[(i7 >> 24) & 255], 8)) ^ iArr[i8][i10];
            char c4 = c2;
            int shift3 = (((iArr3[i7 & 255] ^ shift(iArr3[(i6 >> 8) & 255], 24)) ^ shift(iArr3[(i4 >> 16) & 255], 16)) ^ shift(iArr3[(i9 >> 24) & 255], 8)) ^ iArr[i8][c3];
            int shift4 = ((iArr3[i9 & 255] ^ shift(iArr3[(i7 >> 8) & 255], 24)) ^ shift(iArr3[(i6 >> 16) & 255], 16)) ^ shift(iArr3[(i4 >> 24) & 255], 8);
            int i11 = i8 - 1;
            int i12 = shift4 ^ iArr[i8][c4];
            int shift5 = (((iArr3[shift & 255] ^ shift(iArr3[(i12 >> 8) & 255], 24)) ^ shift(iArr3[(shift3 >> 16) & 255], 16)) ^ shift(iArr3[(shift2 >> 24) & 255], 8)) ^ iArr[i11][z2 ? 1 : 0];
            int shift6 = (((iArr3[shift2 & 255] ^ shift(iArr3[(shift >> 8) & 255], 24)) ^ shift(iArr3[(i12 >> 16) & 255], 16)) ^ shift(iArr3[(shift3 >> 24) & 255], 8)) ^ iArr[i11][i10];
            int shift7 = (((iArr3[shift3 & 255] ^ shift(iArr3[(shift2 >> 8) & 255], 24)) ^ shift(iArr3[(shift >> 16) & 255], 16)) ^ shift(iArr3[(i12 >> 24) & 255], 8)) ^ iArr[i11][c3];
            i8 -= 2;
            i9 = (((iArr3[i12 & 255] ^ shift(iArr3[(shift3 >> 8) & 255], 24)) ^ shift(iArr3[(shift2 >> 16) & 255], 16)) ^ shift(iArr3[(shift >> 24) & 255], 8)) ^ iArr[i11][c4];
            z = z2 ? 1 : 0;
            i5 = i10;
            i4 = shift5;
            i6 = shift6;
            i7 = shift7;
            c = c3;
            c2 = c4;
        }
        boolean z3 = z;
        int i13 = i5;
        char c5 = c;
        char c6 = c2;
        int[] iArr4 = Tinv0;
        int shift8 = (((iArr4[i4 & 255] ^ shift(iArr4[(i9 >> 8) & 255], 24)) ^ shift(iArr4[(i7 >> 16) & 255], 16)) ^ shift(iArr4[(i6 >> 24) & 255], 8)) ^ iArr[i8][z3 ? 1 : 0];
        int shift9 = (((iArr4[i6 & 255] ^ shift(iArr4[(i4 >> 8) & 255], 24)) ^ shift(iArr4[(i9 >> 16) & 255], 16)) ^ shift(iArr4[(i7 >> 24) & 255], 8)) ^ iArr[i8][i13];
        int shift10 = (((iArr4[i7 & 255] ^ shift(iArr4[(i6 >> 8) & 255], 24)) ^ shift(iArr4[(i4 >> 16) & 255], 16)) ^ shift(iArr4[(i9 >> 24) & 255], 8)) ^ iArr[i8][c5];
        int shift11 = (((iArr4[i9 & 255] ^ shift(iArr4[(i7 >> 8) & 255], 24)) ^ shift(iArr4[(i6 >> 16) & 255], 16)) ^ shift(iArr4[(i4 >> 24) & 255], 8)) ^ iArr[i8][c6];
        byte[] bArr3 = Si;
        int i14 = bArr3[shift8 & 255] & UByte.MAX_VALUE;
        byte[] bArr4 = this.s;
        int i15 = ((i14 ^ ((bArr4[(shift11 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(shift10 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(shift9 >> 24) & 255] << 24);
        int[] iArr5 = iArr[z3 ? 1 : 0];
        int i16 = i15 ^ iArr5[z3 ? 1 : 0];
        int i17 = ((((bArr4[shift9 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(shift8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(shift11 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(shift10 >> 24) & 255] << 24)) ^ iArr5[i13];
        int i18 = ((((bArr4[shift10 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(shift9 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(shift8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(shift11 >> 24) & 255] << 24)) ^ iArr5[c5];
        int i19 = ((((bArr3[shift11 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(shift10 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(shift9 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(shift8 >> 24) & 255] << 24)) ^ iArr5[c6];
        Pack.intToLittleEndian(i16, bArr2, i2);
        Pack.intToLittleEndian(i17, bArr2, i2 + 4);
        Pack.intToLittleEndian(i18, bArr2, i2 + 8);
        Pack.intToLittleEndian(i19, bArr2, i2 + 12);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        boolean z = false;
        int[] iArr2 = iArr[0];
        int i3 = littleEndianToInt ^ iArr2[0];
        int i4 = 1;
        int i5 = littleEndianToInt2 ^ iArr2[1];
        char c = 2;
        int i6 = littleEndianToInt3 ^ iArr2[2];
        char c2 = 3;
        int i7 = littleEndianToInt4 ^ iArr2[3];
        int i8 = 1;
        while (i8 < this.ROUNDS - i4) {
            int[] iArr3 = T0;
            boolean z2 = z;
            int i9 = i4;
            int shift = (((iArr3[i3 & 255] ^ shift(iArr3[(i5 >> 8) & 255], 24)) ^ shift(iArr3[(i6 >> 16) & 255], 16)) ^ shift(iArr3[(i7 >> 24) & 255], 8)) ^ iArr[i8][z2 ? 1 : 0];
            char c3 = c;
            int shift2 = (((iArr3[i5 & 255] ^ shift(iArr3[(i6 >> 8) & 255], 24)) ^ shift(iArr3[(i7 >> 16) & 255], 16)) ^ shift(iArr3[(i3 >> 24) & 255], 8)) ^ iArr[i8][i9];
            char c4 = c2;
            int shift3 = (((iArr3[i6 & 255] ^ shift(iArr3[(i7 >> 8) & 255], 24)) ^ shift(iArr3[(i3 >> 16) & 255], 16)) ^ shift(iArr3[(i5 >> 24) & 255], 8)) ^ iArr[i8][c3];
            int shift4 = ((iArr3[i7 & 255] ^ shift(iArr3[(i3 >> 8) & 255], 24)) ^ shift(iArr3[(i5 >> 16) & 255], 16)) ^ shift(iArr3[(i6 >> 24) & 255], 8);
            int i10 = i8 + 1;
            int i11 = shift4 ^ iArr[i8][c4];
            int shift5 = (((iArr3[shift & 255] ^ shift(iArr3[(shift2 >> 8) & 255], 24)) ^ shift(iArr3[(shift3 >> 16) & 255], 16)) ^ shift(iArr3[(i11 >> 24) & 255], 8)) ^ iArr[i10][z2 ? 1 : 0];
            int shift6 = (((iArr3[shift2 & 255] ^ shift(iArr3[(shift3 >> 8) & 255], 24)) ^ shift(iArr3[(i11 >> 16) & 255], 16)) ^ shift(iArr3[(shift >> 24) & 255], 8)) ^ iArr[i10][i9];
            int shift7 = (((iArr3[shift3 & 255] ^ shift(iArr3[(i11 >> 8) & 255], 24)) ^ shift(iArr3[(shift >> 16) & 255], 16)) ^ shift(iArr3[(shift2 >> 24) & 255], 8)) ^ iArr[i10][c3];
            i8 += 2;
            i7 = (((iArr3[i11 & 255] ^ shift(iArr3[(shift >> 8) & 255], 24)) ^ shift(iArr3[(shift2 >> 16) & 255], 16)) ^ shift(iArr3[(shift3 >> 24) & 255], 8)) ^ iArr[i10][c4];
            z = z2 ? 1 : 0;
            i4 = i9;
            i3 = shift5;
            i5 = shift6;
            i6 = shift7;
            c = c3;
            c2 = c4;
        }
        boolean z3 = z;
        int i12 = i4;
        char c5 = c;
        char c6 = c2;
        int[] iArr4 = T0;
        int shift8 = (((iArr4[i3 & 255] ^ shift(iArr4[(i5 >> 8) & 255], 24)) ^ shift(iArr4[(i6 >> 16) & 255], 16)) ^ shift(iArr4[(i7 >> 24) & 255], 8)) ^ iArr[i8][z3 ? 1 : 0];
        int shift9 = (((iArr4[i5 & 255] ^ shift(iArr4[(i6 >> 8) & 255], 24)) ^ shift(iArr4[(i7 >> 16) & 255], 16)) ^ shift(iArr4[(i3 >> 24) & 255], 8)) ^ iArr[i8][i12];
        int shift10 = (((iArr4[i6 & 255] ^ shift(iArr4[(i7 >> 8) & 255], 24)) ^ shift(iArr4[(i3 >> 16) & 255], 16)) ^ shift(iArr4[(i5 >> 24) & 255], 8)) ^ iArr[i8][c5];
        int shift11 = (((iArr4[i7 & 255] ^ shift(iArr4[(i3 >> 8) & 255], 24)) ^ shift(iArr4[(i5 >> 16) & 255], 16)) ^ shift(iArr4[(i6 >> 24) & 255], 8)) ^ iArr[i8][c6];
        byte[] bArr3 = S;
        int i13 = (bArr3[shift8 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(shift9 >> 8) & 255] & UByte.MAX_VALUE) << 8);
        byte[] bArr4 = this.s;
        int i14 = (i13 ^ ((bArr4[(shift10 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(shift11 >> 24) & 255] << 24);
        int[] iArr5 = iArr[i8 + 1];
        int i15 = i14 ^ iArr5[z3 ? 1 : 0];
        int i16 = ((((bArr4[shift9 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(shift10 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(shift11 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr4[(shift8 >> 24) & 255] << 24)) ^ iArr5[i12];
        int i17 = ((((bArr4[shift10 & 255] & UByte.MAX_VALUE) ^ ((bArr3[(shift11 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr3[(shift8 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(shift9 >> 24) & 255] << 24)) ^ iArr5[c5];
        int i18 = ((((bArr4[shift11 & 255] & UByte.MAX_VALUE) ^ ((bArr4[(shift8 >> 8) & 255] & UByte.MAX_VALUE) << 8)) ^ ((bArr4[(shift9 >> 16) & 255] & UByte.MAX_VALUE) << 16)) ^ (bArr3[(shift10 >> 24) & 255] << 24)) ^ iArr5[c6];
        Pack.intToLittleEndian(i15, bArr2, i2);
        Pack.intToLittleEndian(i16, bArr2, i2 + 4);
        Pack.intToLittleEndian(i17, bArr2, i2 + 8);
        Pack.intToLittleEndian(i18, bArr2, i2 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int i;
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i2 = length >>> 2;
        this.ROUNDS = i2 + 6;
        int i3 = 1;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2 + 7, 4);
        char c = 3;
        if (i2 == 4) {
            i = 1;
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            for (int i4 = 1; i4 <= 10; i4++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[i4 - 1];
                int[] iArr2 = iArr[i4];
                iArr2[0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr2[1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr2[2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr2[3] = littleEndianToInt4;
            }
        } else if (i2 == 6) {
            i = 1;
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int i5 = 1;
            int i6 = 1;
            while (true) {
                int[] iArr3 = iArr[i5];
                iArr3[0] = littleEndianToInt9;
                iArr3[1] = littleEndianToInt10;
                int subWord = littleEndianToInt5 ^ (subWord(shift(littleEndianToInt10, 8)) ^ i6);
                int[] iArr4 = iArr[i5];
                iArr4[2] = subWord;
                int i7 = littleEndianToInt6 ^ subWord;
                iArr4[3] = i7;
                int i8 = littleEndianToInt7 ^ i7;
                int[] iArr5 = iArr[i5 + 1];
                iArr5[0] = i8;
                int i9 = littleEndianToInt8 ^ i8;
                iArr5[1] = i9;
                int i10 = littleEndianToInt9 ^ i9;
                iArr5[2] = i10;
                int i11 = littleEndianToInt10 ^ i10;
                iArr5[3] = i11;
                int subWord2 = subWord(shift(i11, 8)) ^ (i6 << 1);
                i6 <<= 2;
                littleEndianToInt5 = subWord ^ subWord2;
                int[] iArr6 = iArr[i5 + 2];
                iArr6[0] = littleEndianToInt5;
                littleEndianToInt6 = i7 ^ littleEndianToInt5;
                iArr6[1] = littleEndianToInt6;
                littleEndianToInt7 = i8 ^ littleEndianToInt6;
                iArr6[2] = littleEndianToInt7;
                littleEndianToInt8 = i9 ^ littleEndianToInt7;
                iArr6[3] = littleEndianToInt8;
                i5 += 3;
                if (i5 >= 13) {
                    break;
                }
                littleEndianToInt9 = i10 ^ littleEndianToInt8;
                littleEndianToInt10 = i11 ^ littleEndianToInt9;
            }
        } else {
            if (i2 != 8) {
                throw new IllegalStateException("Should never get here");
            }
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i12 = 1;
            int i13 = 2;
            while (true) {
                int subWord3 = subWord(shift(littleEndianToInt18, 8)) ^ i12;
                i12 <<= i3;
                littleEndianToInt11 ^= subWord3;
                int[] iArr7 = iArr[i13];
                iArr7[0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr7[i3] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr7[2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr7[c] = littleEndianToInt14;
                i = i3;
                int i14 = i13 + 1;
                char c2 = c;
                if (i14 >= 15) {
                    break;
                }
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                int[] iArr8 = iArr[i14];
                iArr8[0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr8[i] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr8[2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr8[c2] = littleEndianToInt18;
                i13 += 2;
                i3 = i;
                c = c2;
            }
        }
        if (!z) {
            for (int i15 = i; i15 < this.ROUNDS; i15++) {
                for (int i16 = 0; i16 < 4; i16++) {
                    int[] iArr9 = iArr[i15];
                    iArr9[i16] = inv_mcol(iArr9[i16]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    public static MultiBlockCipher newInstance() {
        return new AESEngine();
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & UByte.MAX_VALUE) | ((bArr[(i >> 8) & 255] & UByte.MAX_VALUE) << 8) | ((bArr[(i >> 16) & 255] & UByte.MAX_VALUE) << 16);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
        this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
        this.forEncryption = z;
        if (z) {
            this.s = Arrays.clone(S);
        } else {
            this.s = Arrays.clone(Si);
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(z)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 > bArr2.length - 16) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            encryptBlock(bArr, i, bArr2, i2, iArr);
        } else {
            decryptBlock(bArr, i, bArr2, i2, iArr);
        }
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
