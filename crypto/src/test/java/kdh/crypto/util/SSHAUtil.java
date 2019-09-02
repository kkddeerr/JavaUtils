package kdh.crypto.util;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;

class SSHAUtil {

    private MessageDigest sha;

    public SSHAUtil() {
        try {
            sha = MessageDigest.getInstance("SHA-1");
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println("Construction failed: " + e);
        }
    }

    public String createDigestNoLabel(String entity) {
        SecureRandom rng = new SecureRandom();
        byte[] salt = new byte[8];
        rng.nextBytes(salt);
        sha.reset();
        sha.update(entity.getBytes(Charset.forName("UTF-8")));
        sha.update(salt);
        byte[] pwhash = sha.digest();
        return Base64.encodeBase64String(concatenate(pwhash, salt)).trim();
    }

    private static byte[] concatenate(byte[] l, byte[] r) {
        byte[] b = new byte[l.length + r.length];
        System.arraycopy(l, 0, b, 0, l.length);
        System.arraycopy(r, 0, b, l.length, r.length);
        return b;
    }
}
