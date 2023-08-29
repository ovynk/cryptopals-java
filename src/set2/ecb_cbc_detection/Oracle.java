package set2.ecb_cbc_detection;

import set1.aes_in_ecb_mode.ECB;
import set2.cbc_mode.CBC;

import java.security.SecureRandom;
import java.util.Random;

public class Oracle {
    public static byte[] randBytes(int length) {
        SecureRandom random = new SecureRandom();
        byte[] result = new byte[length];
        random.nextBytes(result);
        return result;
    }

    public static byte[] encryptRand(byte[] text) throws Exception {
        Random random = new Random();

        byte[] prefix = randBytes(random.nextInt(5, 11));
        byte[] postfix = randBytes(random.nextInt(5, 11));

        byte[] textBytes = new byte[prefix.length + text.length + postfix.length];
        System.arraycopy(prefix, 0, textBytes, 0, prefix.length);
        System.arraycopy(text, 0, textBytes, prefix.length, text.length);
        System.arraycopy(postfix, 0, textBytes, prefix.length + text.length, postfix.length);

        byte[] key = randBytes(16);
        byte[] initVector = randBytes(16);

        int rand = random.nextInt(1, 3);
        System.out.print(rand == 1 ? "CBC" : "EBC");
        return switch (rand) {
            case 1 -> CBC.encrypt(textBytes, key, initVector);
            case 2 -> ECB.encryptAES128(textBytes, key);
            default -> null;
        };
    }
}
