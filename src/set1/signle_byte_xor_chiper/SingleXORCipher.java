package set1.signle_byte_xor_chiper;

import java.util.Map;

import static java.util.Map.entry;

public class SingleXORCipher {
    public static final Map<Character, Integer> etaoinFrequency = Map.ofEntries(
            entry(' ', 30),
            entry('e', 26),
            entry('t', 25),
            entry('a', 24),
            entry('o', 23),
            entry('i', 22),
            entry('n', 21),
            entry('s', 20),
            entry('h', 19),
            entry('r', 18),
            entry('d', 17),
            entry('l', 16),
            entry('c', 15),
            entry('u', 14),
            entry('m', 13),
            entry('w', 12),
            entry('f', 11),
            entry('g', 10),
            entry('y', 9),
            entry('p', 8),
            entry('b', 7),
            entry('v', 6),
            entry('k', 5),
            entry('j', 4),
            entry('x', 3),
            entry('q', 2),
            entry('z', 1)
    );

    public static ScoreCharText decryptStringSingleXORCipher(byte[] bytes) {
        long maxScore = 0;
        String maxScoredString = "";
        char keyChar = 0;

        char[] chars = new char[127];
        for (int i = 0; i < 127; i++) {
            chars[i] = (char) i;
        }

        long tempScore;
        StringBuilder XORbytes = new StringBuilder();
        for (char c : chars) {
            for (byte b : bytes) {
                XORbytes.append((char) (b ^ c));
            }

            tempScore = scoreText(XORbytes.toString());
            if (tempScore > maxScore) {
                keyChar = c;
                maxScore = tempScore;
                maxScoredString = XORbytes.toString();
            }
            XORbytes.delete(0, XORbytes.length());
        }

        return new ScoreCharText(maxScore, keyChar, maxScoredString);
    }


    public static ScoreCharText decryptStringSingleXORCipher(int[] bytes) {
        long maxScore = 0;
        String maxScoredString = "";
        char keyChar = 0;

        int[] chars = new int[127];
        for (int i = 0; i < 127; i++) {
            chars[i] = i;
        }

        long tempScore;
        StringBuilder XORbytes = new StringBuilder();
        for (int c : chars) {
            for (int b : bytes) {
                XORbytes.append((char) (b ^ c));
            }

            tempScore = scoreText(XORbytes.toString());
            if (tempScore > maxScore) {
                keyChar = (char) c;
                maxScore = tempScore;
                maxScoredString = XORbytes.toString();
            }
            XORbytes.delete(0, XORbytes.length());
        }
        return new ScoreCharText(maxScore, keyChar, maxScoredString);
    }

    public static long scoreText(String text) {
        long score = 0;
        if (text != null) {
            for (int i = 0; i < text.length(); i++) {
                if (etaoinFrequency.get(text.charAt(i)) != null) {
                    score += etaoinFrequency.get(text.charAt(i));
                }
            }
        }
        return score;
    }

}
