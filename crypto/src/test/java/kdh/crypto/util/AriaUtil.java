package kdh.crypto.util;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;

class AriaUtil {
    public static final byte PADDING_BYTE = 0; // ASCII 0 (NULL ����)

    public static final int BLOCK_SIZE = 16; // 128 bit ( 8 * 16);


    static byte[] encrypt(byte[] key, int saltSize, String text) throws InvalidKeyException {
        return encrypt(key, saltSize, text.getBytes(Charset.forName("UTF-8")));
    }

    static byte[] encrypt(byte[] key, int saltSize, byte[] text) throws InvalidKeyException {
        byte[] vtext = text;
        vtext = SaltUtil.addRandomSalt(vtext, saltSize);
        return encrypt(key, vtext);
    }

    static byte[] encrypt(byte[] key, byte[] text) throws InvalidKeyException {
        byte[] vtext = text;
        ARIAEngine ariaEngine = getAriaEngine(key);
        vtext = fillPadding(vtext);
        byte[] encrypted = new byte[vtext.length];
        for (int i = 0, blockCnt = getBlockCnt(vtext); i < blockCnt; i++) {
            ariaEngine.encrypt(vtext, i * BLOCK_SIZE, encrypted, i * BLOCK_SIZE);
        }
        return encrypted;
    }

    private static ARIAEngine getAriaEngine(byte[] key) throws InvalidKeyException {
        int keySize = key.length * 8;
        ARIAEngine instance = new ARIAEngine(keySize);

        instance.reset();
        instance.setKeySize(keySize);
        instance.setKey(key);
        return instance;
    }

    private static byte[] fillPadding(byte[] org) {
        int orgSize = org.length;
        int blockCnt = getBlockCnt(org);
        byte[] newBytes = new byte[blockCnt * BLOCK_SIZE];

        System.arraycopy(org, 0, newBytes, 0, orgSize);
        for (int i = orgSize, newSize = newBytes.length; i < newSize; i++) {
            newBytes[i] = PADDING_BYTE;
        }
        return newBytes;
    }

    private static int getBlockCnt(byte[] org) {
        int orgSize = org.length;
        return (int) Math.ceil((double) orgSize / (double) BLOCK_SIZE);
    }
}