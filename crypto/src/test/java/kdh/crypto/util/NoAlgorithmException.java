package kdh.crypto.util;
public class NoAlgorithmException extends CryptException {

    private static final long serialVersionUID = 6573612645550033406L;

    public NoAlgorithmException(String message) {
        super("Not support Algorithm [" + message + "]");
    }

    public NoAlgorithmException(Throwable cause) {
        super(cause);
    }
}
