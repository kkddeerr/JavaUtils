package kdh.crypto.util;
import org.bouncycastle.util.encoders.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Helper {

    public static String encode(byte[] text) {
        try {
            return new String(Base64.encode(text), Constants.CRYPT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new EncodingException(e);
        }
    }

}
