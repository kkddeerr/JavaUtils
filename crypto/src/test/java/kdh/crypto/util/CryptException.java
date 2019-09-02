package kdh.crypto.util;
public class CryptException extends RuntimeException {

    private static final long serialVersionUID = -207860357095071618L;

    public CryptException(String message) {
        super(message);
    }

    public CryptException(Throwable cause) {
        super(cause);
    }
}
