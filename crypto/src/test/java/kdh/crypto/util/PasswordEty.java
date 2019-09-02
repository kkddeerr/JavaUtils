package kdh.crypto.util;
public final class PasswordEty {

    public static final String START_DELIM = "{";
    public static final String END_DELIM = "}";

    private String algorithm, pwdStr;
    private boolean authSuccessed;

    private PasswordEty(String org) {

        if (org.startsWith(START_DELIM)) {
            int endIdx = org.indexOf(END_DELIM);
            algorithm = org.substring(1, endIdx);
            pwdStr = org.substring(endIdx + 1);
        } else {
            algorithm = Constants.CLEARTEXT;
            pwdStr = org;
        }
    }

    public static PasswordEty instance(String pwdStr) {
        return new PasswordEty(pwdStr);
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String toDbStr() {
        if (Constants.CLEARTEXT.equalsIgnoreCase(algorithm)) {
            return pwdStr;
        }

        return START_DELIM + algorithm + END_DELIM + pwdStr;
    }

    @Override
    public String toString() {
        StringBuffer tmpStr = new StringBuffer(64);

        tmpStr.append("\nalgorithm = [").append(algorithm).append("]\npwdStr = [");
        tmpStr.append(pwdStr).append("]\nauthSuccessed = [");
        tmpStr.append(authSuccessed).append("]\n");

        return tmpStr.toString();
    }
}