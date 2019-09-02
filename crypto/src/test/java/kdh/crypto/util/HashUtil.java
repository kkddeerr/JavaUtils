package kdh.crypto.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class HashUtil {
    private static Set<String> hashAlogrithm;

    static {
        hashAlogrithm = new HashSet<String>();
        hashAlogrithm.add(Constants.MD5);
        hashAlogrithm.add(Constants.SHA);
        hashAlogrithm.add(Constants.SHA256);
        hashAlogrithm.add(Constants.SHA512);
        hashAlogrithm = Collections.unmodifiableSet(hashAlogrithm);
    }

    private HashUtil() {
    }

    static byte[] digest(String str, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException,
            NoSuchProviderException {
        return digest(str, algorithm, Constants.PROVIDER, 0);
    }

    static byte[] digest(String str, String algorithm, String provider, int saltSize) throws NoSuchAlgorithmException,
            UnsupportedEncodingException, NoSuchProviderException {

        return digest(str.getBytes(Constants.CRYPT_CHARSET), algorithm, provider, saltSize);
    }

    private static byte[] digest(byte[] input, String algorithm, String provider, int saltSize)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
        MessageDigest md = MessageDigest.getInstance(algorithm, provider);

        byte[] randomSalt = SaltUtil.genSalt(saltSize);

        md.update(randomSalt);
        md.update(input);

        byte[] digested = md.digest();

        return SaltUtil.addSalt(digested, randomSalt);
    }

    public static boolean isSupportedAlgorithm(String algorithm) {
        return hashAlogrithm.contains(algorithm.toUpperCase());
    }
}