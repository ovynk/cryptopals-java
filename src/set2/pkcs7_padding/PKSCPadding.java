package set2.pkcs7_padding;

public class PKSCPadding {
    public static String padding(String original, int lengthTo) {
        int padLength = lengthTo - original.length();
        char padChar = (char) padLength;
        return "" + original + String.valueOf(padChar).repeat(Math.max(0, padLength));
    }
}
