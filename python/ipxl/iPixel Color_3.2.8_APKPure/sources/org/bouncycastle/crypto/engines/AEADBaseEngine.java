package org.bouncycastle.crypto.engines;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
abstract class AEADBaseEngine implements AEADCipher {
    protected int AADBufferSize;
    protected int BlockSize;
    protected int IV_SIZE;
    protected int KEY_SIZE;
    protected int MAC_SIZE;
    protected AADOperator aadOperator;
    protected String algorithmName;
    protected DataOperator dataOperator;
    protected boolean forEncryption;
    protected byte[] initialAssociatedText;
    protected byte[] m_aad;
    protected int m_aadPos;
    protected byte[] m_buf;
    protected int m_bufPos;
    protected int m_bufferSizeDecrypt;
    protected State m_state = State.Uninitialized;
    protected byte[] mac;
    protected AADProcessingBuffer processor;

    protected interface AADOperator {
        int getLen();

        void processAADByte(byte b);

        void processAADBytes(byte[] bArr, int i, int i2);

        void reset();
    }

    protected static class AADOperatorType {
        public static final int COUNTER = 1;
        public static final int DEFAULT = 0;
        public static final int STREAM = 2;
        private final int ord;
        public static final AADOperatorType Default = new AADOperatorType(0);
        public static final AADOperatorType Counter = new AADOperatorType(1);
        public static final AADOperatorType Stream = new AADOperatorType(2);

        AADOperatorType(int i) {
            this.ord = i;
        }
    }

    private interface AADProcessingBuffer {
        int getUpdateOutputSize(int i);

        boolean isLengthExceedingBlockSize(int i, int i2);

        boolean isLengthWithinAvailableSpace(int i, int i2);

        void processAADByte(byte b);

        int processByte(byte b, byte[] bArr, int i);
    }

    private class BufferedAADProcessor implements AADProcessingBuffer {
        private BufferedAADProcessor() {
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public int getUpdateOutputSize(int i) {
            return Math.max(0, i) - 1;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public boolean isLengthExceedingBlockSize(int i, int i2) {
            return i > i2;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public boolean isLengthWithinAvailableSpace(int i, int i2) {
            return i <= i2;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public void processAADByte(byte b) {
            if (AEADBaseEngine.this.m_aadPos == AEADBaseEngine.this.AADBufferSize) {
                AEADBaseEngine aEADBaseEngine = AEADBaseEngine.this;
                aEADBaseEngine.processBufferAAD(aEADBaseEngine.m_aad, 0);
                AEADBaseEngine.this.m_aadPos = 0;
            }
            byte[] bArr = AEADBaseEngine.this.m_aad;
            AEADBaseEngine aEADBaseEngine2 = AEADBaseEngine.this;
            int i = aEADBaseEngine2.m_aadPos;
            aEADBaseEngine2.m_aadPos = i + 1;
            bArr[i] = b;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public int processByte(byte b, byte[] bArr, int i) {
            AEADBaseEngine.this.checkData(false);
            int processEncDecByte = AEADBaseEngine.this.processEncDecByte(bArr, i);
            byte[] bArr2 = AEADBaseEngine.this.m_buf;
            AEADBaseEngine aEADBaseEngine = AEADBaseEngine.this;
            int i2 = aEADBaseEngine.m_bufPos;
            aEADBaseEngine.m_bufPos = i2 + 1;
            bArr2[i2] = b;
            return processEncDecByte;
        }
    }

    private class CounterAADOperator implements AADOperator {
        private int aadLen;

        private CounterAADOperator() {
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public int getLen() {
            return this.aadLen;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public void processAADByte(byte b) {
            this.aadLen++;
            AEADBaseEngine.this.processor.processAADByte(b);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public void processAADBytes(byte[] bArr, int i, int i2) {
            this.aadLen += i2;
            AEADBaseEngine.this.processAadBytes(bArr, i, i2);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public void reset() {
            this.aadLen = 0;
        }
    }

    private class CounterDataOperator implements DataOperator {
        private int messegeLen;

        private CounterDataOperator() {
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int getLen() {
            return this.messegeLen;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int processByte(byte b, byte[] bArr, int i) {
            this.messegeLen++;
            return AEADBaseEngine.this.processor.processByte(b, bArr, i);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            this.messegeLen += i2;
            return AEADBaseEngine.this.processEncDecBytes(bArr, i, i2, bArr2, i3);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public void reset() {
            this.messegeLen = 0;
        }
    }

    protected interface DataOperator {
        int getLen();

        int processByte(byte b, byte[] bArr, int i);

        int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

        void reset();
    }

    protected static class DataOperatorType {
        public static final int COUNTER = 1;
        public static final int DEFAULT = 0;
        public static final int STREAM = 2;
        public static final int STREAM_CIPHER = 3;
        private final int ord;
        public static final DataOperatorType Default = new DataOperatorType(0);
        public static final DataOperatorType Counter = new DataOperatorType(1);
        public static final DataOperatorType Stream = new DataOperatorType(2);
        public static final DataOperatorType StreamCipher = new DataOperatorType(3);

        DataOperatorType(int i) {
            this.ord = i;
        }
    }

    private class DefaultAADOperator implements AADOperator {
        private DefaultAADOperator() {
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public int getLen() {
            return AEADBaseEngine.this.m_aadPos;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public void processAADByte(byte b) {
            AEADBaseEngine.this.processor.processAADByte(b);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public void processAADBytes(byte[] bArr, int i, int i2) {
            AEADBaseEngine.this.processAadBytes(bArr, i, i2);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public void reset() {
        }
    }

    private class DefaultDataOperator implements DataOperator {
        private DefaultDataOperator() {
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int getLen() {
            return AEADBaseEngine.this.m_bufPos;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int processByte(byte b, byte[] bArr, int i) {
            return AEADBaseEngine.this.processor.processByte(b, bArr, i);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            return AEADBaseEngine.this.processEncDecBytes(bArr, i, i2, bArr2, i3);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public void reset() {
        }
    }

    protected static final class ErasableOutputStream extends ByteArrayOutputStream {
        public byte[] getBuf() {
            return this.buf;
        }
    }

    private class ImmediateAADProcessor implements AADProcessingBuffer {
        private ImmediateAADProcessor() {
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public int getUpdateOutputSize(int i) {
            return Math.max(0, i);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public boolean isLengthExceedingBlockSize(int i, int i2) {
            return i >= i2;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public boolean isLengthWithinAvailableSpace(int i, int i2) {
            return i < i2;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public void processAADByte(byte b) {
            byte[] bArr = AEADBaseEngine.this.m_aad;
            AEADBaseEngine aEADBaseEngine = AEADBaseEngine.this;
            int i = aEADBaseEngine.m_aadPos;
            aEADBaseEngine.m_aadPos = i + 1;
            bArr[i] = b;
            if (AEADBaseEngine.this.m_aadPos == AEADBaseEngine.this.AADBufferSize) {
                AEADBaseEngine aEADBaseEngine2 = AEADBaseEngine.this;
                aEADBaseEngine2.processBufferAAD(aEADBaseEngine2.m_aad, 0);
                AEADBaseEngine.this.m_aadPos = 0;
            }
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADProcessingBuffer
        public int processByte(byte b, byte[] bArr, int i) {
            AEADBaseEngine.this.checkData(false);
            byte[] bArr2 = AEADBaseEngine.this.m_buf;
            AEADBaseEngine aEADBaseEngine = AEADBaseEngine.this;
            int i2 = aEADBaseEngine.m_bufPos;
            aEADBaseEngine.m_bufPos = i2 + 1;
            bArr2[i2] = b;
            return AEADBaseEngine.this.processEncDecByte(bArr, i);
        }
    }

    protected static class ProcessingBufferType {
        public static final int BUFFERED = 0;
        public static final int IMMEDIATE = 1;
        private final int ord;
        public static final ProcessingBufferType Buffered = new ProcessingBufferType(0);
        public static final ProcessingBufferType Immediate = new ProcessingBufferType(1);

        ProcessingBufferType(int i) {
            this.ord = i;
        }
    }

    protected static class State {
        public static final int DEC_AAD = 6;
        public static final int DEC_DATA = 7;
        public static final int DEC_FINAL = 8;
        public static final int DEC_INIT = 5;
        public static final int ENC_AAD = 2;
        public static final int ENC_DATA = 3;
        public static final int ENC_FINAL = 4;
        public static final int ENC_INIT = 1;
        public static final int UNINITIALIZED = 0;
        final int ord;
        public static final State Uninitialized = new State(0);
        public static final State EncInit = new State(1);
        public static final State EncAad = new State(2);
        public static final State EncData = new State(3);
        public static final State EncFinal = new State(4);
        public static final State DecInit = new State(5);
        public static final State DecAad = new State(6);
        public static final State DecData = new State(7);
        public static final State DecFinal = new State(8);

        State(int i) {
            this.ord = i;
        }
    }

    protected static class StreamAADOperator implements AADOperator {
        private final ErasableOutputStream stream = new ErasableOutputStream();

        protected StreamAADOperator() {
        }

        public byte[] getBytes() {
            return this.stream.getBuf();
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public int getLen() {
            return this.stream.size();
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public void processAADByte(byte b) {
            this.stream.write(b);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public void processAADBytes(byte[] bArr, int i, int i2) {
            this.stream.write(bArr, i, i2);
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperator
        public void reset() {
            this.stream.reset();
        }
    }

    private class StreamCipherOperator implements DataOperator {
        private int len;

        private StreamCipherOperator() {
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int getLen() {
            return this.len;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int processByte(byte b, byte[] bArr, int i) {
            if (AEADBaseEngine.this.checkData(false)) {
                this.len = 1;
                AEADBaseEngine.this.processBufferEncrypt(new byte[]{b}, 0, bArr, i);
                return 1;
            }
            if (AEADBaseEngine.this.m_bufPos == AEADBaseEngine.this.MAC_SIZE) {
                this.len = 1;
                AEADBaseEngine aEADBaseEngine = AEADBaseEngine.this;
                aEADBaseEngine.processBufferDecrypt(aEADBaseEngine.m_buf, 0, bArr, i);
                System.arraycopy(AEADBaseEngine.this.m_buf, 1, AEADBaseEngine.this.m_buf, 0, AEADBaseEngine.this.m_bufPos - 1);
                AEADBaseEngine.this.m_buf[AEADBaseEngine.this.m_bufPos - 1] = b;
                return 1;
            }
            byte[] bArr2 = AEADBaseEngine.this.m_buf;
            AEADBaseEngine aEADBaseEngine2 = AEADBaseEngine.this;
            int i2 = aEADBaseEngine2.m_bufPos;
            aEADBaseEngine2.m_bufPos = i2 + 1;
            bArr2[i2] = b;
            return 0;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            int i4 = 0;
            if (bArr == bArr2 && Arrays.segmentsOverlap(i, i2, i3, AEADBaseEngine.this.processor.getUpdateOutputSize(i2))) {
                bArr = new byte[i2];
                System.arraycopy(bArr2, i, bArr, 0, i2);
                i = 0;
            }
            if (AEADBaseEngine.this.checkData(false)) {
                this.len = i2;
                AEADBaseEngine.this.processBufferEncrypt(bArr, i, bArr2, i3);
                return i2;
            }
            int max = Math.max((AEADBaseEngine.this.m_bufPos + i2) - AEADBaseEngine.this.MAC_SIZE, 0);
            if (AEADBaseEngine.this.m_bufPos > 0) {
                int min = Math.min(max, AEADBaseEngine.this.m_bufPos);
                this.len = min;
                AEADBaseEngine aEADBaseEngine = AEADBaseEngine.this;
                aEADBaseEngine.processBufferDecrypt(aEADBaseEngine.m_buf, 0, bArr2, i3);
                max -= min;
                AEADBaseEngine.this.m_bufPos -= min;
                System.arraycopy(AEADBaseEngine.this.m_buf, min, AEADBaseEngine.this.m_buf, 0, AEADBaseEngine.this.m_bufPos);
                i4 = min;
            }
            if (max > 0) {
                this.len = max;
                AEADBaseEngine.this.processBufferDecrypt(bArr, i, bArr2, i3);
                i4 += max;
                i2 -= max;
                i += max;
            }
            System.arraycopy(bArr, i, AEADBaseEngine.this.m_buf, AEADBaseEngine.this.m_bufPos, i2);
            AEADBaseEngine.this.m_bufPos += i2;
            return i4;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public void reset() {
        }
    }

    protected class StreamDataOperator implements DataOperator {
        private final ErasableOutputStream stream = new ErasableOutputStream();

        protected StreamDataOperator() {
        }

        public byte[] getBytes() {
            return this.stream.getBuf();
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int getLen() {
            return this.stream.size();
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int processByte(byte b, byte[] bArr, int i) {
            AEADBaseEngine.this.ensureInitialized();
            this.stream.write(b);
            AEADBaseEngine.this.m_bufPos = this.stream.size();
            return 0;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            AEADBaseEngine.this.ensureInitialized();
            this.stream.write(bArr, i, i2);
            AEADBaseEngine.this.m_bufPos = this.stream.size();
            return 0;
        }

        @Override // org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperator
        public void reset() {
            this.stream.reset();
        }
    }

    AEADBaseEngine() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processAadBytes(byte[] bArr, int i, int i2) {
        int i3 = this.m_aadPos;
        if (i3 > 0) {
            int i4 = this.AADBufferSize - i3;
            if (this.processor.isLengthWithinAvailableSpace(i2, i4)) {
                System.arraycopy(bArr, i, this.m_aad, this.m_aadPos, i2);
                this.m_aadPos += i2;
                return;
            } else {
                System.arraycopy(bArr, i, this.m_aad, this.m_aadPos, i4);
                i += i4;
                i2 -= i4;
                processBufferAAD(this.m_aad, 0);
            }
        }
        while (this.processor.isLengthExceedingBlockSize(i2, this.AADBufferSize)) {
            processBufferAAD(bArr, i);
            int i5 = this.AADBufferSize;
            i += i5;
            i2 -= i5;
        }
        System.arraycopy(bArr, i, this.m_aad, 0, i2);
        this.m_aadPos = i2;
    }

    protected void checkAAD() {
        State state;
        int i = this.m_state.ord;
        if (i == 1) {
            state = State.EncAad;
        } else {
            if (i == 2) {
                return;
            }
            if (i == 4) {
                throw new IllegalStateException(getAlgorithmName() + " cannot be reused for encryption");
            }
            if (i != 5) {
                if (i != 6) {
                    throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
                }
                return;
            }
            state = State.DecAad;
        }
        this.m_state = state;
    }

    protected boolean checkData(boolean z) {
        switch (this.m_state.ord) {
            case 1:
            case 2:
                finishAAD(State.EncData, z);
                return true;
            case 3:
                return true;
            case 4:
                throw new IllegalStateException(getAlgorithmName() + " cannot be reused for encryption");
            case 5:
            case 6:
                finishAAD(State.DecData, z);
                return false;
            case 7:
                return false;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        int i2;
        boolean checkData = checkData(true);
        int i3 = this.m_bufPos;
        int i4 = this.MAC_SIZE;
        if (checkData) {
            i2 = i3 + i4;
        } else {
            if (i3 < i4) {
                throw new InvalidCipherTextException("data too short");
            }
            i2 = i3 - i4;
            this.m_bufPos = i2;
        }
        ensureSufficientOutputBuffer(bArr, i, i2);
        this.mac = new byte[this.MAC_SIZE];
        processFinalBlock(bArr, i);
        if (checkData) {
            byte[] bArr2 = this.mac;
            int i5 = this.MAC_SIZE;
            System.arraycopy(bArr2, 0, bArr, (i + i2) - i5, i5);
        } else if (!Arrays.constantTimeAreEqual(this.MAC_SIZE, this.mac, 0, this.m_buf, this.m_bufPos)) {
            throw new InvalidCipherTextException(this.algorithmName + " mac does not match");
        }
        reset(!checkData);
        return i2;
    }

    protected final void ensureInitialized() {
        if (this.m_state == State.Uninitialized) {
            throw new IllegalStateException("Need to call init function before operation");
        }
    }

    protected final void ensureSufficientInputBuffer(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
    }

    protected final void ensureSufficientOutputBuffer(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new OutputLengthException("output buffer too short");
        }
    }

    protected abstract void finishAAD(State state, boolean z);

    protected void finishAAD1(State state) {
        int i = this.m_state.ord;
        if (i == 1 || i == 2 || i == 5 || i == 6) {
            processFinalAAD();
        }
        this.m_state = state;
    }

    protected void finishAAD2(State state) {
        int i = this.m_state.ord;
        if (i == 2 || i == 6) {
            processFinalAAD();
        }
        this.m_aadPos = 0;
        this.m_state = state;
    }

    protected void finishAAD3(State state, boolean z) {
        int i = this.m_state.ord;
        if (i != 1 && i != 2) {
            if (i == 5 || i == 6) {
                if (!z && this.dataOperator.getLen() <= this.MAC_SIZE) {
                    return;
                }
            }
            this.m_aadPos = 0;
            this.m_state = state;
        }
        processFinalAAD();
        this.m_aadPos = 0;
        this.m_state = state;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public final int getBlockSize() {
        return this.BlockSize;
    }

    public int getIVBytesSize() {
        return this.IV_SIZE;
    }

    public int getKeyBytesSize() {
        return this.KEY_SIZE;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        return this.mac;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        int max = Math.max(0, i);
        switch (this.m_state.ord) {
            case 3:
            case 4:
                return max + this.m_bufPos + this.MAC_SIZE;
            case 5:
            case 6:
            case 7:
            case 8:
                return Math.max(0, (max + this.m_bufPos) - this.MAC_SIZE);
            default:
                return max + this.MAC_SIZE;
        }
    }

    protected int getTotalBytesForUpdate(int i) {
        int i2;
        int updateOutputSize = this.processor.getUpdateOutputSize(i);
        switch (this.m_state.ord) {
            case 3:
            case 4:
                i2 = updateOutputSize + this.m_bufPos;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                i2 = (updateOutputSize + this.m_bufPos) - this.MAC_SIZE;
                break;
            default:
                return updateOutputSize;
        }
        return Math.max(0, i2);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        int totalBytesForUpdate = getTotalBytesForUpdate(i);
        return totalBytesForUpdate - (totalBytesForUpdate % this.BlockSize);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        KeyParameter keyParameter;
        byte[] iv;
        this.forEncryption = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            keyParameter = aEADParameters.getKey();
            iv = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize != this.MAC_SIZE * 8) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("invalid parameters passed to " + this.algorithmName);
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
            iv = parametersWithIV.getIV();
            this.initialAssociatedText = null;
        }
        if (keyParameter == null) {
            throw new IllegalArgumentException(this.algorithmName + " Init parameters must include a key");
        }
        if (iv == null || iv.length != this.IV_SIZE) {
            throw new IllegalArgumentException(this.algorithmName + " requires exactly " + this.IV_SIZE + " bytes of IV");
        }
        byte[] key = keyParameter.getKey();
        if (key.length != this.KEY_SIZE) {
            throw new IllegalArgumentException(this.algorithmName + " key must be " + this.KEY_SIZE + " bytes long");
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
        this.m_state = z ? State.EncInit : State.DecInit;
        init(key, iv);
        reset(true);
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    protected abstract void init(byte[] bArr, byte[] bArr2);

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        checkAAD();
        this.aadOperator.processAADByte(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        ensureSufficientInputBuffer(bArr, i, i2);
        if (i2 <= 0) {
            return;
        }
        checkAAD();
        this.aadOperator.processAADBytes(bArr, i, i2);
    }

    protected abstract void processBufferAAD(byte[] bArr, int i);

    protected abstract void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2);

    protected abstract void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2);

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return this.dataOperator.processByte(b, bArr, i);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        ensureSufficientInputBuffer(bArr, i, i2);
        return this.dataOperator.processBytes(bArr, i, i2, bArr2, i3);
    }

    protected int processEncDecByte(byte[] bArr, int i) {
        if ((this.forEncryption ? this.BlockSize : this.m_bufferSizeDecrypt) - this.m_bufPos != 0) {
            return 0;
        }
        ensureSufficientOutputBuffer(bArr, i, this.BlockSize);
        if (this.forEncryption) {
            processBufferEncrypt(this.m_buf, 0, bArr, i);
        } else {
            processBufferDecrypt(this.m_buf, 0, bArr, i);
            byte[] bArr2 = this.m_buf;
            int i2 = this.BlockSize;
            System.arraycopy(bArr2, i2, bArr2, 0, this.m_bufPos - i2);
        }
        int i3 = this.m_bufPos;
        int i4 = this.BlockSize;
        this.m_bufPos = i3 - i4;
        return i4;
    }

    protected int processEncDecBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4;
        boolean checkData = checkData(false);
        int i5 = (checkData ? this.BlockSize : this.m_bufferSizeDecrypt) - this.m_bufPos;
        if (this.processor.isLengthWithinAvailableSpace(i2, i5)) {
            System.arraycopy(bArr, i, this.m_buf, this.m_bufPos, i2);
            this.m_bufPos += i2;
            return 0;
        }
        int updateOutputSize = this.processor.getUpdateOutputSize(i2);
        int i6 = (this.m_bufPos + updateOutputSize) - (checkData ? 0 : this.MAC_SIZE);
        ensureSufficientOutputBuffer(bArr2, i3, i6 - (i6 % this.BlockSize));
        if (bArr == bArr2 && Arrays.segmentsOverlap(i, i2, i3, updateOutputSize)) {
            bArr = new byte[i2];
            System.arraycopy(bArr2, i, bArr, 0, i2);
            i = 0;
        }
        if (checkData) {
            int i7 = this.m_bufPos;
            if (i7 > 0) {
                System.arraycopy(bArr, i, this.m_buf, i7, i5);
                i += i5;
                i2 -= i5;
                processBufferEncrypt(this.m_buf, 0, bArr2, i3);
                i4 = this.BlockSize;
            } else {
                i4 = 0;
            }
            while (this.processor.isLengthExceedingBlockSize(i2, this.BlockSize)) {
                processBufferEncrypt(bArr, i, bArr2, i3 + i4);
                int i8 = this.BlockSize;
                i += i8;
                i2 -= i8;
                i4 += i8;
            }
        } else {
            i4 = 0;
            while (this.processor.isLengthExceedingBlockSize(this.m_bufPos, this.BlockSize) && this.processor.isLengthExceedingBlockSize(this.m_bufPos + i2, this.m_bufferSizeDecrypt)) {
                processBufferDecrypt(this.m_buf, i4, bArr2, i3 + i4);
                int i9 = this.m_bufPos;
                int i10 = this.BlockSize;
                this.m_bufPos = i9 - i10;
                i4 += i10;
            }
            int i11 = this.m_bufPos;
            if (i11 > 0) {
                byte[] bArr3 = this.m_buf;
                System.arraycopy(bArr3, i4, bArr3, 0, i11);
                if (this.processor.isLengthWithinAvailableSpace(this.m_bufPos + i2, this.m_bufferSizeDecrypt)) {
                    System.arraycopy(bArr, i, this.m_buf, this.m_bufPos, i2);
                    this.m_bufPos += i2;
                    return i4;
                }
                int max = Math.max(this.BlockSize - this.m_bufPos, 0);
                System.arraycopy(bArr, i, this.m_buf, this.m_bufPos, max);
                i += max;
                i2 -= max;
                processBufferDecrypt(this.m_buf, 0, bArr2, i3 + i4);
                i4 += this.BlockSize;
            }
            while (this.processor.isLengthExceedingBlockSize(i2, this.m_bufferSizeDecrypt)) {
                processBufferDecrypt(bArr, i, bArr2, i3 + i4);
                int i12 = this.BlockSize;
                i += i12;
                i2 -= i12;
                i4 += i12;
            }
        }
        System.arraycopy(bArr, i, this.m_buf, 0, i2);
        this.m_bufPos = i2;
        return i4;
    }

    protected abstract void processFinalAAD();

    protected abstract void processFinalBlock(byte[] bArr, int i);

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        reset(true);
    }

    protected void reset(boolean z) {
        ensureInitialized();
        if (z) {
            this.mac = null;
        }
        byte[] bArr = this.m_buf;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.m_bufPos = 0;
        }
        byte[] bArr2 = this.m_aad;
        if (bArr2 != null) {
            Arrays.fill(bArr2, (byte) 0);
            this.m_aadPos = 0;
        }
        switch (this.m_state.ord) {
            case 1:
            case 5:
                break;
            case 2:
            case 3:
            case 4:
                this.m_state = State.EncFinal;
                return;
            case 6:
            case 7:
            case 8:
                this.m_state = State.DecFinal;
                break;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
        this.aadOperator.reset();
        this.dataOperator.reset();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void setInnerMembers(org.bouncycastle.crypto.engines.AEADBaseEngine.ProcessingBufferType r4, org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperatorType r5, org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperatorType r6) {
        /*
            r3 = this;
            int r4 = org.bouncycastle.crypto.engines.AEADBaseEngine.ProcessingBufferType.access$000(r4)
            r0 = 1
            r1 = 0
            if (r4 == 0) goto L11
            if (r4 == r0) goto Lb
            goto L18
        Lb:
            org.bouncycastle.crypto.engines.AEADBaseEngine$ImmediateAADProcessor r4 = new org.bouncycastle.crypto.engines.AEADBaseEngine$ImmediateAADProcessor
            r4.<init>()
            goto L16
        L11:
            org.bouncycastle.crypto.engines.AEADBaseEngine$BufferedAADProcessor r4 = new org.bouncycastle.crypto.engines.AEADBaseEngine$BufferedAADProcessor
            r4.<init>()
        L16:
            r3.processor = r4
        L18:
            int r4 = r3.BlockSize
            int r2 = r3.MAC_SIZE
            int r4 = r4 + r2
            r3.m_bufferSizeDecrypt = r4
            int r4 = org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperatorType.access$300(r5)
            r5 = 0
            r2 = 2
            if (r4 == 0) goto L40
            if (r4 == r0) goto L34
            if (r4 == r2) goto L2c
            goto L4d
        L2c:
            r3.AADBufferSize = r5
            org.bouncycastle.crypto.engines.AEADBaseEngine$StreamAADOperator r4 = new org.bouncycastle.crypto.engines.AEADBaseEngine$StreamAADOperator
            r4.<init>()
            goto L4b
        L34:
            int r4 = r3.AADBufferSize
            byte[] r4 = new byte[r4]
            r3.m_aad = r4
            org.bouncycastle.crypto.engines.AEADBaseEngine$CounterAADOperator r4 = new org.bouncycastle.crypto.engines.AEADBaseEngine$CounterAADOperator
            r4.<init>()
            goto L4b
        L40:
            int r4 = r3.AADBufferSize
            byte[] r4 = new byte[r4]
            r3.m_aad = r4
            org.bouncycastle.crypto.engines.AEADBaseEngine$DefaultAADOperator r4 = new org.bouncycastle.crypto.engines.AEADBaseEngine$DefaultAADOperator
            r4.<init>()
        L4b:
            r3.aadOperator = r4
        L4d:
            int r4 = org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperatorType.access$600(r6)
            if (r4 == 0) goto L83
            if (r4 == r0) goto L77
            if (r4 == r2) goto L6b
            r6 = 3
            if (r4 == r6) goto L5b
            return
        L5b:
            r3.BlockSize = r5
            int r4 = r3.m_bufferSizeDecrypt
            byte[] r4 = new byte[r4]
            r3.m_buf = r4
            org.bouncycastle.crypto.engines.AEADBaseEngine$StreamCipherOperator r4 = new org.bouncycastle.crypto.engines.AEADBaseEngine$StreamCipherOperator
            r4.<init>()
        L68:
            r3.dataOperator = r4
            return
        L6b:
            int r4 = r3.MAC_SIZE
            byte[] r4 = new byte[r4]
            r3.m_buf = r4
            org.bouncycastle.crypto.engines.AEADBaseEngine$StreamDataOperator r4 = new org.bouncycastle.crypto.engines.AEADBaseEngine$StreamDataOperator
            r4.<init>()
            goto L68
        L77:
            int r4 = r3.m_bufferSizeDecrypt
            byte[] r4 = new byte[r4]
            r3.m_buf = r4
            org.bouncycastle.crypto.engines.AEADBaseEngine$CounterDataOperator r4 = new org.bouncycastle.crypto.engines.AEADBaseEngine$CounterDataOperator
            r4.<init>()
            goto L68
        L83:
            int r4 = r3.m_bufferSizeDecrypt
            byte[] r4 = new byte[r4]
            r3.m_buf = r4
            org.bouncycastle.crypto.engines.AEADBaseEngine$DefaultDataOperator r4 = new org.bouncycastle.crypto.engines.AEADBaseEngine$DefaultDataOperator
            r4.<init>()
            goto L68
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.AEADBaseEngine.setInnerMembers(org.bouncycastle.crypto.engines.AEADBaseEngine$ProcessingBufferType, org.bouncycastle.crypto.engines.AEADBaseEngine$AADOperatorType, org.bouncycastle.crypto.engines.AEADBaseEngine$DataOperatorType):void");
    }
}
