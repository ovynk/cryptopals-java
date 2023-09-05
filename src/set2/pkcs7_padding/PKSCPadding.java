package set2.pkcs7_padding;

public class PKSCPadding {
    public static String padding(String original, int lengthTo) {
        int padLength = lengthTo - original.length();
        char padChar = (char) padLength;
        return "" + original + String.valueOf(padChar).repeat(Math.max(0, padLength));
    }

    public static String validate(String padded, int blockSize) {
        if (padded.getBytes().length % blockSize != 0) throw new RuntimeException("Wrong length of padding");

        char lastChar = padded.charAt(blockSize - 1);
        int countPad = 0;
        for (int i = blockSize - 1; i > 0; i--) {
            if (padded.charAt(i) == lastChar) countPad++;
            else break;
        }

        if (countPad != (int) lastChar) throw new RuntimeException("Wrong padding");
        return padded.substring(0, padded.length() - countPad);
    }
}
