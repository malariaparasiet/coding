package org.bouncycastle.pqc.jcajce.provider.util;

import java.security.InvalidKeyException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.ARIAEngine;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.engines.RFC3394WrapEngine;
import org.bouncycastle.crypto.engines.RFC5649WrapEngine;
import org.bouncycastle.crypto.engines.SEEDEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class WrapUtil {
    public static Wrapper getKeyUnwrapper(KTSParameterSpec kTSParameterSpec, byte[] bArr) throws InvalidKeyException {
        Wrapper wrapper = getWrapper(kTSParameterSpec.getKeyAlgorithmName());
        wrapper.init(false, kTSParameterSpec.getKdfAlgorithm() == null ? new KeyParameter(bArr, 0, (kTSParameterSpec.getKeySize() + 7) / 8) : new KeyParameter(makeKeyBytes(kTSParameterSpec, bArr)));
        return wrapper;
    }

    public static Wrapper getKeyWrapper(KTSParameterSpec kTSParameterSpec, byte[] bArr) throws InvalidKeyException {
        Wrapper wrapper = getWrapper(kTSParameterSpec.getKeyAlgorithmName());
        wrapper.init(true, kTSParameterSpec.getKdfAlgorithm() == null ? new KeyParameter(Arrays.copyOfRange(bArr, 0, (kTSParameterSpec.getKeySize() + 7) / 8)) : new KeyParameter(makeKeyBytes(kTSParameterSpec, bArr)));
        return wrapper;
    }

    public static Wrapper getWrapper(String str) {
        if (str.equalsIgnoreCase("AESWRAP") || str.equalsIgnoreCase("AES")) {
            return new RFC3394WrapEngine(AESEngine.newInstance());
        }
        if (str.equalsIgnoreCase("ARIA")) {
            return new RFC3394WrapEngine(new ARIAEngine());
        }
        if (str.equalsIgnoreCase("Camellia")) {
            return new RFC3394WrapEngine(new CamelliaEngine());
        }
        if (str.equalsIgnoreCase("SEED")) {
            return new RFC3394WrapEngine(new SEEDEngine());
        }
        if (str.equalsIgnoreCase("AES-KWP")) {
            return new RFC5649WrapEngine(new AESEngine());
        }
        if (str.equalsIgnoreCase("Camellia-KWP")) {
            return new RFC5649WrapEngine(new CamelliaEngine());
        }
        if (str.equalsIgnoreCase("ARIA-KWP")) {
            return new RFC5649WrapEngine(new ARIAEngine());
        }
        throw new UnsupportedOperationException("unknown key algorithm: " + str);
    }

    private static byte[] makeKeyBytes(KTSParameterSpec kTSParameterSpec, byte[] bArr) throws InvalidKeyException {
        try {
            return KdfUtil.makeKeyBytes(kTSParameterSpec.getKdfAlgorithm(), bArr, kTSParameterSpec.getOtherInfo(), kTSParameterSpec.getKeySize());
        } catch (IllegalArgumentException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    public static byte[] trimSecret(String str, byte[] bArr) {
        return str.equals("SEED") ? Arrays.copyOfRange(bArr, 0, 16) : bArr;
    }
}
