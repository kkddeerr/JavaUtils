package kdh.crypto.util;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final public class SymmetricCrypt {

    private static Set<String> symmetricAlogrithm;

    static {
        symmetricAlogrithm = new HashSet<String>();
        symmetricAlogrithm.add(Constants.BLOWFISH);
        symmetricAlogrithm.add(Constants.TWOFISH);
        symmetricAlogrithm.add(Constants.DES);
        symmetricAlogrithm.add(Constants.DESEDE);
        symmetricAlogrithm.add(Constants.AES);
        symmetricAlogrithm.add(Constants.ARIA);
        symmetricAlogrithm = Collections.unmodifiableSet(symmetricAlogrithm);
    }

    private SymmetricCrypt() {
    }

    static Cipher getCipher(String algorithm, String mode, String padding) throws NoSuchAlgorithmException,
            NoSuchPaddingException, NoSuchProviderException {
        return Cipher.getInstance(algorithm + "/" + mode + "/" + padding, Constants.PROVIDER);
    }

    static byte[] encrypt(Cipher cipher, Key key, byte[] salt, String text) throws InvalidKeyException,
            UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return encrypt(cipher, key, salt, text.getBytes(Charset.forName("UTF-8")));
    }

    static byte[] encrypt(Cipher cipher, Key key, byte[] salt, byte[] text) throws InvalidKeyException,
            UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec iv = null;

        if (cipher.getAlgorithm().toUpperCase().indexOf(Constants.CRYPT_MODE_CBC) != -1) {
            int blockSize = cipher.getBlockSize();
            byte[] keyByte = SaltUtil.getSaltBytes(key.getEncoded(), blockSize);
            iv = new IvParameterSpec(keyByte);
        }

        try {
            if (iv != null) {
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }

        } catch (InvalidAlgorithmParameterException e) {
            throw new NoAlgorithmException(e);
        }

        byte[] vtext = text;
        if (salt != null) {
            vtext = SaltUtil.addSalt(vtext, salt);
        }

        return cipher.doFinal(vtext);
    }

    static byte[] encrypt(String algorithm, String mode, String paddingMode, byte[] key, int saltSize, String text) {
        if (!SymmetricCrypt.isSupportedAlgorithm(algorithm)) {
            throw new NoAlgorithmException(algorithm);
        }

        String vmode = mode;
        String vpaddingMode = paddingMode;

        try {
            if (algorithm.equals(Constants.ARIA)) {
                return AriaUtil.encrypt(key, saltSize, text);
            }

            if (vmode == null) {
                vmode = Constants.CRYPT_MODE;
            }
            if (vpaddingMode == null) {
                vpaddingMode = Constants.CRYPT_PADDING;
            }

            Key cryptKey = KeyUtil.getKey(key, algorithm);
            Cipher cipher = getCipher(algorithm, vmode, vpaddingMode);
            byte[] salt = SaltUtil.genSalt(saltSize);

            if (Constants.TWOFISH.equalsIgnoreCase(algorithm)) {
                byte[] padded = pad(text);
                return encrypt(cipher, cryptKey, salt, padded);
            } else {
                return encrypt(cipher, cryptKey, salt, text);
            }

        } catch (Exception e) {
            System.out.println("Occure Exception");
            throw new CryptException(e);
        }
    }

    static byte[] pad(String text) throws UnsupportedEncodingException {
        int max = 16;

        byte[] pt = null;
        byte[] pt2 = new byte[max];
        pt = text.getBytes("UTF-8");

        System.arraycopy(pt, 0, pt2, 0, pt.length > max ? max : pt.length);
        for (int i = pt.length; i < 16; i++) {
            pt2[i] = (byte) 0;
        }

        return pt2;
    }

    public static boolean isSupportedAlgorithm(String algorithm) {
        return symmetricAlogrithm.contains(algorithm.toUpperCase());
    }
}