package set2.ecb_cbc_detection;

import java.util.Arrays;

public class AESDetection {
    public static String detectECBCBC(byte[] cipher, int blockLength) {
        return countBlockMatches(cipher, blockLength) > 0 ? "ECB" : "CBC";
    }

    public static int countBlockMatches(byte[] cipher, int blockLength) {
        int blocksCount = cipher.length/blockLength;
        int matches = 0;

        for (int index = 0; index < blocksCount; index++) {
            byte[] block = Arrays.copyOfRange(cipher, index * 16, index * 16 + 16);
            for (int j = index + 1; j < blocksCount; j++) {
                byte[] other = Arrays.copyOfRange(cipher, j * 16, j * 16 + 16);
                if (Arrays.equals(block, other))
                    matches++;
            }
        }

        return matches;
    }

    public static byte[] constructAESDetectingPlaintext(int blockSize) {
        byte[] plaintext = new byte[blockSize*3];
        Arrays.fill(plaintext, (byte) 1);
        return plaintext;
    }
}
