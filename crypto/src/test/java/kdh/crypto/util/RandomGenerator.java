package kdh.crypto.util;
import java.security.SecureRandom;

class RandomGenerator {

    private static final SecureRandom random = new SecureRandom();

    static byte[] nextBytes(int byteSize) {
        byte[] randomBytes = new byte[byteSize];
        random.nextBytes(randomBytes);
        return randomBytes;
    }
}
