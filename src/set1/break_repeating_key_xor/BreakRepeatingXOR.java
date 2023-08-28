package set1.break_repeating_key_xor;

import set1.signle_byte_xor_chiper.SingleXORCipher;

import java.util.*;

public class BreakRepeatingXOR {

    public static int hammingDistance(String s1, String s2) {
        if (s1.length() != s2.length()) throw new IndexOutOfBoundsException("S1 and S2 must be the same length");

        int counter = 0;
        for (int i = 0; i < s1.length(); i++) {
            counter += Long.bitCount(s1.charAt(i) ^ s2.charAt(i));
        }
        return counter;
    }

    public static HashMap<Float, Integer> normalizedKeySizes(String text) {
        HashMap<Float, Integer> results = new HashMap<>();
        for (int i = 2; i < 40; i++) {
            String s1 = text.substring(0, i);
            String s2 = text.substring(i, i * 2);
            String s3 = text.substring(i * 2, i * 3);
            String s4 = text.substring(i * 3, i * 4);

            float normalizedDistance =
                    (float)(hammingDistance(s1, s2) + hammingDistance(s2, s3) + hammingDistance(s3, s4)) / (3 * i);

            results.put(normalizedDistance, i);
        }
        return results;
    }

    public static String breakByKeySizeRepeatingXOR(String text, int keySize) {
        ArrayList<StringBuilder> transposedBlocks = new ArrayList<>();
        for (int i = 0; i < keySize; i++) {
            transposedBlocks.add(new StringBuilder());
        }

        for (int i = 0; i < text.length(); i++) {
            transposedBlocks.get(i % keySize).append(text.charAt(i));
        }

        StringBuilder finalKey = new StringBuilder();
        for (StringBuilder transposedBlock : transposedBlocks) {
            finalKey.append(SingleXORCipher
                    .decryptStringSingleXORCipher(transposedBlock.toString().getBytes()).getC());
        }
        return finalKey.toString();
    }
}
