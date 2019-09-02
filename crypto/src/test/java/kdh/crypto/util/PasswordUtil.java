package kdh.crypto.util;
import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.lang.RandomStringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

public class PasswordUtil {

    private static final String DEFAULT_PW_ALGORITHM = Constants.TWOFISH;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encryptPassword(String algorithmName, String plaintextPassword) {
        String a = algorithmName;
        if (algorithmName == null) {
            a = DEFAULT_PW_ALGORITHM;
        }

        if (a.startsWith(PasswordEty.START_DELIM) && a.endsWith(PasswordEty.END_DELIM)) {
            a = algorithmName.substring(1, algorithmName.length() - 1);
        }

        PasswordEty ety = makePasswordEty(plaintextPassword, a);

        return ety.toDbStr();
    }

    private static String encrypt(String algorithm, String pwd) {
        String randomSalt = RandomStringUtils.randomAlphanumeric(2);
        return encrypt(algorithm, pwd, randomSalt);
    }

    private static String encrypt(String algorithm, String pwd, String salt) {
        String valgorithm = algorithm;
        if (valgorithm == null) {
            valgorithm = Constants.DEFAULT_ALGORITHM;
        }

        String result = null;
        if (Constants.UNIX_CRYPT.equalsIgnoreCase(valgorithm)) {
            result = Crypt.crypt(pwd, salt);
        } else if (SymmetricCrypt.isSupportedAlgorithm(valgorithm)) {
            if (Constants.TWOFISH.equalsIgnoreCase(valgorithm)) {
                result = Base64Helper.encode(SymmetricCrypt.encrypt(valgorithm, Constants.CRYPT_MODE,
                        Constants.CRYPT_PADDING_NO, Constants.KEY_TWOFISH, 0, pwd));
            } else {
                result = Base64Helper.encode(SymmetricCrypt.encrypt(valgorithm, Constants.CRYPT_MODE,
                        Constants.CRYPT_PADDING, Constants.KEY_128, 0, pwd));
            }
        } else if (HashUtil.isSupportedAlgorithm(valgorithm)) {
            try {
                result = Base64Helper.encode(HashUtil.digest(pwd, valgorithm));
            } catch (NoSuchAlgorithmException e) {
                throw new NoAlgorithmException(e);
            } catch (UnsupportedEncodingException e) {
                throw new EncodingException(e);
            } catch (NoSuchProviderException e) {
                throw new CryptException(e);
            }
        } else if (Constants.SSHA.equalsIgnoreCase(valgorithm)) {
            SSHAUtil ssha = new SSHAUtil();
            result = ssha.createDigestNoLabel(pwd);
        } else if (Constants.CLEARTEXT.equalsIgnoreCase(valgorithm)) {
            result = pwd;
        }

        if (result == null) {
            throw new NoAlgorithmException(valgorithm);
        }

        return result;
    }

    private static PasswordEty makePasswordEty(String input, String algorithm) {
        PasswordEty pwd = PasswordEty.instance(encrypt(algorithm, input));
        pwd.setAlgorithm(algorithm);
        return pwd;
    }

}