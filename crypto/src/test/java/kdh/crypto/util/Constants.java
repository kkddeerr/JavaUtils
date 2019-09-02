package kdh.crypto.util;
public class Constants {

    public static final String CLEARTEXT = "CLEARTEXT";
    public static final String UNIX_CRYPT = "CRYPT";
    public static final String DES = "DES";
    public static final String DESEDE = "DESEDE";
    public static final String TWOFISH = "TWOFISH";
    public static final String BLOWFISH = "BLOWFISH";
    public static final String AES = "AES";
    public static final String ARIA = "ARIA";
    public static final String SSHA = "SSHA";
    public static final String SHA = "SHA";
    public static final String SHA256 = "SHA256";
    public static final String SHA512 = "SHA512";
    public static final String MD5 = "MD5";

    static final String CRYPT_CHARSET = "ISO-8859-1";

    static final String PROVIDER = "BC";

    static final String CRYPT_MODE_ECB = "ECB";
    static final String CRYPT_MODE_CBC = "CBC";
    static final String CRYPT_MODE = CRYPT_MODE_ECB;
    static final String CRYPT_PADDING_PKCS5 = "PKCS5Padding";
    static final String CRYPT_PADDING_NO = "NoPadding";
    static final String CRYPT_PADDING = CRYPT_PADDING_PKCS5;

    static final String DEFAULT_ALGORITHM = AES;

    static final byte[] KEY_TWOFISH = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    static final byte[] KEY_128 = {22, -96, -81, 48, 51, 1, -35, 19, 119, -55, -8, -72, 102, 65, 91, 113};


}
