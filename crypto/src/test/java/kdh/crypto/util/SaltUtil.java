package kdh.crypto.util;
final class SaltUtil {

    private SaltUtil() {
    }

    static byte[] genSalt(int saltSize) {
        return RandomGenerator.nextBytes(saltSize);
    }

    static byte[] addRandomSalt(byte[] orgBytes, int saltSize) {

        byte[] randomSalt = genSalt(saltSize);
        byte[] result = new byte[randomSalt.length + orgBytes.length];

        System.arraycopy(randomSalt, 0, result, 0, saltSize);
        System.arraycopy(orgBytes, 0, result, saltSize, orgBytes.length);

        return result;
    }

    static byte[] addSalt(byte[] orgBytes, byte[] randomSalt) {
        int saltSize = randomSalt.length;
        int orgByteSize = orgBytes.length;

        byte[] result = new byte[saltSize + orgByteSize];

        System.arraycopy(randomSalt, 0, result, 0, saltSize);
        System.arraycopy(orgBytes, 0, result, saltSize, orgByteSize);

        return result;
    }

    static byte[] getSaltBytes(byte[] saltAddedBytes, int saltSize) {
        byte[] randomSalt = new byte[saltSize];
        System.arraycopy(saltAddedBytes, 0, randomSalt, 0, saltSize);
        return randomSalt;
    }
}