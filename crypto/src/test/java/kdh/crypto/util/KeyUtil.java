package kdh.crypto.util;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;


final class KeyUtil {

    private KeyUtil() {
    }

    static SecretKeySpec getKey(byte[] keyBytes, String algorithm) {
        byte[] vkeyBytes = keyBytes;
        if (Constants.DES.equals(algorithm)) {
            DESKeySpec desKey = null;
            try {
                desKey = new DESKeySpec(vkeyBytes);
                vkeyBytes = desKey.getKey();
            } catch (InvalidKeyException e) {
                System.out.println("Invalid DES Key");
            }
        }

        return new SecretKeySpec(vkeyBytes, algorithm);
    }
}