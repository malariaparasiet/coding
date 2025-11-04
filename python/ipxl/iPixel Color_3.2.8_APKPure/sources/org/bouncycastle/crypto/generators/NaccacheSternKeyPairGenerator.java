package org.bouncycastle.crypto.generators;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.Command;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NaccacheSternKeyGenerationParameters param;
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, Opcodes.LXOR, 137, Opcodes.F2I, Opcodes.FCMPL, Opcodes.DCMPL, Opcodes.IFGT, Opcodes.IF_ICMPGT, Opcodes.GOTO, Opcodes.LRETURN, Opcodes.PUTSTATIC, Opcodes.PUTFIELD, Opcodes.ATHROW, Opcodes.INSTANCEOF, 197, Opcodes.IFNONNULL, Primes.SMALL_FACTOR_LIMIT, 223, Command.CMD_OTA_ENTER_UPDATE_MODE, Command.CMD_OTA_SEND_FIRMWARE_UPDATE_BLOCK, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, TypedValues.AttributesType.TYPE_EASING, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, TypedValues.PositionType.TYPE_PERCENT_WIDTH, 509, 521, 523, 541, 547, 557};
    private static final BigInteger ONE = BigInteger.valueOf(1);

    private static Vector findFirstPrimes(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf(smallPrimes[i2]));
        }
        return vector;
    }

    private static BigInteger generatePrime(int i, int i2, SecureRandom secureRandom) {
        BigInteger createRandomPrime;
        do {
            createRandomPrime = BigIntegers.createRandomPrime(i, i2, secureRandom);
        } while (createRandomPrime.bitLength() != i);
        return createRandomPrime;
    }

    private static int getInt(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((i * (secureRandom.nextInt() & Integer.MAX_VALUE)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = nextInt % i;
        } while ((nextInt - i2) + (i - 1) < 0);
        return i2;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        while (true) {
            vector3.removeElementAt(0);
            if (vector3.size() == 0) {
                return vector2;
            }
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        long j;
        BigInteger generatePrime;
        BigInteger add;
        BigInteger generatePrime2;
        boolean z;
        BigInteger bigInteger;
        BigInteger add2;
        BigInteger bigInteger2;
        BigInteger multiply;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        BigInteger bigInteger7;
        BigInteger bigInteger8;
        int i;
        PrintStream printStream;
        StringBuilder sb;
        int i2;
        BigInteger createRandomPrime;
        SecureRandom secureRandom;
        int i3;
        SecureRandom secureRandom2;
        int i4;
        BigInteger bigInteger9;
        BigInteger bigInteger10;
        int i5;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean isDebug = this.param.isDebug();
        if (isDebug) {
            System.out.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector permuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigInteger11 = ONE;
        BigInteger bigInteger12 = bigInteger11;
        for (int i6 = 0; i6 < permuteList.size() / 2; i6++) {
            bigInteger12 = bigInteger12.multiply((BigInteger) permuteList.elementAt(i6));
        }
        for (int size = permuteList.size() / 2; size < permuteList.size(); size++) {
            bigInteger11 = bigInteger11.multiply((BigInteger) permuteList.elementAt(size));
        }
        BigInteger multiply2 = bigInteger12.multiply(bigInteger11);
        int bitLength = (((strength - multiply2.bitLength()) - 48) / 2) + 1;
        BigInteger generatePrime3 = generatePrime(bitLength, certainty, random);
        BigInteger generatePrime4 = generatePrime(bitLength, certainty, random);
        if (isDebug) {
            System.out.println("generating p and q");
        }
        BigInteger shiftLeft = generatePrime3.multiply(bigInteger12).shiftLeft(1);
        BigInteger shiftLeft2 = generatePrime4.multiply(bigInteger11).shiftLeft(1);
        long j2 = 0;
        while (true) {
            j = j2 + 1;
            generatePrime = generatePrime(24, certainty, random);
            add = generatePrime.multiply(shiftLeft).add(ONE);
            if (add.isProbablePrime(certainty)) {
                while (true) {
                    do {
                        generatePrime2 = generatePrime(24, certainty, random);
                    } while (generatePrime.equals(generatePrime2));
                    BigInteger multiply3 = generatePrime2.multiply(shiftLeft2);
                    z = isDebug;
                    bigInteger = ONE;
                    add2 = multiply3.add(bigInteger);
                    if (add2.isProbablePrime(certainty)) {
                        break;
                    }
                    isDebug = z;
                }
                bigInteger2 = shiftLeft2;
                if (BigIntegers.modOddIsCoprime(generatePrime.multiply(generatePrime2), multiply2)) {
                    BigInteger bigInteger13 = shiftLeft;
                    multiply = add.multiply(add2);
                    bigInteger3 = bigInteger13;
                    if (multiply.bitLength() >= strength) {
                        break;
                    }
                    int i7 = strength;
                    secureRandom2 = random;
                    i4 = certainty;
                    bigInteger9 = generatePrime3;
                    bigInteger10 = generatePrime4;
                    if (z) {
                        i5 = i7;
                        System.out.println("key size too small. Should be " + i5 + " but is actually " + add.multiply(add2).bitLength());
                    } else {
                        i5 = i7;
                    }
                    strength = i5;
                    generatePrime3 = bigInteger9;
                    generatePrime4 = bigInteger10;
                    j2 = j;
                    isDebug = z;
                    shiftLeft2 = bigInteger2;
                    shiftLeft = bigInteger3;
                    random = secureRandom2;
                    certainty = i4;
                } else {
                    secureRandom2 = random;
                    i4 = certainty;
                }
            } else {
                secureRandom2 = random;
                i4 = certainty;
                z = isDebug;
                bigInteger2 = shiftLeft2;
            }
            bigInteger3 = shiftLeft;
            bigInteger10 = generatePrime4;
            bigInteger9 = generatePrime3;
            i5 = strength;
            strength = i5;
            generatePrime3 = bigInteger9;
            generatePrime4 = bigInteger10;
            j2 = j;
            isDebug = z;
            shiftLeft2 = bigInteger2;
            shiftLeft = bigInteger3;
            random = secureRandom2;
            certainty = i4;
        }
        BigInteger bigInteger14 = generatePrime4;
        if (z) {
            bigInteger4 = generatePrime3;
            System.out.println("needed " + j + " tries to generate p and q.");
        } else {
            bigInteger4 = generatePrime3;
        }
        BigInteger multiply4 = add.subtract(bigInteger).multiply(add2.subtract(bigInteger));
        if (z) {
            System.out.println("generating g");
        }
        long j3 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger5 = add2;
            int i8 = 0;
            while (i8 != permuteList.size()) {
                BigInteger divide = multiply4.divide((BigInteger) permuteList.elementAt(i8));
                while (true) {
                    j3++;
                    i2 = i8;
                    createRandomPrime = BigIntegers.createRandomPrime(strength, certainty, random);
                    secureRandom = random;
                    i3 = certainty;
                    if (createRandomPrime.modPow(divide, multiply).equals(ONE)) {
                        i8 = i2;
                        random = secureRandom;
                        certainty = i3;
                    }
                }
                vector.addElement(createRandomPrime);
                i8 = i2 + 1;
                random = secureRandom;
                certainty = i3;
            }
            SecureRandom secureRandom3 = random;
            int i9 = certainty;
            bigInteger6 = ONE;
            for (int i10 = 0; i10 < permuteList.size(); i10++) {
                bigInteger6 = bigInteger6.multiply(((BigInteger) vector.elementAt(i10)).modPow(multiply2.divide((BigInteger) permuteList.elementAt(i10)), multiply)).mod(multiply);
            }
            int i11 = 0;
            while (true) {
                if (i11 >= permuteList.size()) {
                    BigInteger modPow = bigInteger6.modPow(multiply4.divide(BigInteger.valueOf(4L)), multiply);
                    BigInteger bigInteger15 = ONE;
                    if (!modPow.equals(bigInteger15)) {
                        if (!bigInteger6.modPow(multiply4.divide(generatePrime), multiply).equals(bigInteger15)) {
                            if (!bigInteger6.modPow(multiply4.divide(generatePrime2), multiply).equals(bigInteger15)) {
                                bigInteger7 = bigInteger4;
                                if (!bigInteger6.modPow(multiply4.divide(bigInteger7), multiply).equals(bigInteger15)) {
                                    bigInteger8 = bigInteger14;
                                    if (!bigInteger6.modPow(multiply4.divide(bigInteger8), multiply).equals(bigInteger15)) {
                                        break;
                                    }
                                    if (z) {
                                        i = strength;
                                        System.out.println("g has order phi(n)/b\n g: " + bigInteger6);
                                    }
                                } else {
                                    if (z) {
                                        System.out.println("g has order phi(n)/a\n g: " + bigInteger6);
                                    }
                                    bigInteger8 = bigInteger14;
                                }
                            } else if (z) {
                                printStream = System.out;
                                sb = new StringBuilder("g has order phi(n)/q'\n g: ");
                                printStream.println(sb.append(bigInteger6).toString());
                            }
                        } else if (z) {
                            printStream = System.out;
                            sb = new StringBuilder("g has order phi(n)/p'\n g: ");
                            printStream.println(sb.append(bigInteger6).toString());
                        }
                    } else if (z) {
                        printStream = System.out;
                        sb = new StringBuilder("g has order phi(n)/4\n g:");
                        printStream.println(sb.append(bigInteger6).toString());
                    }
                } else if (!bigInteger6.modPow(multiply4.divide((BigInteger) permuteList.elementAt(i11)), multiply).equals(ONE)) {
                    i11++;
                } else if (z) {
                    System.out.println("g has order phi(n)/" + permuteList.elementAt(i11) + "\n g: " + bigInteger6);
                }
            }
            bigInteger8 = bigInteger14;
            bigInteger7 = bigInteger4;
            i = strength;
            bigInteger4 = bigInteger7;
            strength = i;
            random = secureRandom3;
            certainty = i9;
            bigInteger14 = bigInteger8;
            add2 = bigInteger5;
        }
        if (z) {
            System.out.println("needed " + j3 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            System.out.println("smallPrimes: " + permuteList);
            System.out.println("sigma:...... " + multiply2 + " (" + multiply2.bitLength() + " bits)");
            System.out.println("a:.......... " + bigInteger7);
            System.out.println("b:.......... " + bigInteger8);
            System.out.println("p':......... " + generatePrime);
            System.out.println("q':......... " + generatePrime2);
            System.out.println("p:.......... " + add);
            System.out.println("q:.......... " + bigInteger5);
            System.out.println("n:.......... " + multiply);
            System.out.println("phi(n):..... " + multiply4);
            System.out.println("g:.......... " + bigInteger6);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigInteger6, multiply, multiply2.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigInteger6, multiply, multiply2.bitLength(), permuteList, multiply4));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("NaccacheStern KeyGen", ConstraintUtils.bitsOfSecurityForFF(keyGenerationParameters.getStrength()), keyGenerationParameters, CryptoServicePurpose.KEYGEN));
    }
}
