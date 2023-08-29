package set2.pkcs7_padding;

public class PKSCPadding {
    public static String padding(String original, int lengthTo, char padChar) {
        int padLength = lengthTo - original.length();

        return "" + original + String.valueOf(padChar).repeat(Math.max(0, padLength));
    }
}
