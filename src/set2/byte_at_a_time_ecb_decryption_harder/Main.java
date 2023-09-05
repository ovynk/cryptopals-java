package set2.byte_at_a_time_ecb_decryption_harder;

import set2.byte_at_a_time_ecb_decryption.ECBSuffix;
import set2.ecb_cbc_detection.Oracle;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static final byte[] randPrefix = Oracle.randBytes(new Random().nextInt(0, 20));

    public static void main(String[] args) throws Exception {
        int encryptionSizeBlock = getEncryptionSizeBlock();
        int prefixLen = getPrefixLen();
        int lenOfUnknownString = getEncryptionUnknownStringLength();
        System.out.println(encryptionSizeBlock + " " + lenOfUnknownString);

        System.out.println(prefixLen + " " + (randPrefix.length == prefixLen));

        String unknown = new String(recoverUnknownString(lenOfUnknownString));
        System.out.println(unknown);

    }

    public static byte[] encryptECBPrefixSuffix(byte[] text) throws Exception {
        byte[] plainText = new byte[randPrefix.length + text.length];

        System.arraycopy(randPrefix, 0, plainText, 0, randPrefix.length);
        System.arraycopy(text, 0, plainText, randPrefix.length, text.length);

        return ECBSuffix.encryptRandECB(plainText, ECBSuffix.key);
    }

    public static int getPrefixLen() throws Exception {
        byte[] encryptedText0 = encryptECBPrefixSuffix(new byte[0]);
        byte[] encryptedText1 = encryptECBPrefixSuffix(new byte[1]);

        int start = 0;
        for (int i = 0; i < encryptedText0.length; i++) {
            if (encryptedText0[i] != encryptedText1[i]) {
                break;
            }
            start++;
        }

        byte[] prevEncryption = new byte[16];
        System.arraycopy(encryptECBPrefixSuffix(new byte[0]), start, prevEncryption, 0, 16);

        int c = 1;
        byte[] compareCut = new byte[16];
        System.arraycopy(encryptECBPrefixSuffix(new byte[c]), start, compareCut, 0, 16);

        while (!Arrays.equals(prevEncryption, compareCut)) {
            c++;
            prevEncryption = compareCut;
            compareCut = new byte[16];
            System.arraycopy(encryptECBPrefixSuffix(new byte[c]), start, compareCut, 0, 16);
        }

        return (start + 16) - (c - 1);
    }

    public static int getEncryptionSizeBlock() throws Exception {
        int pt = 0;
        byte[] cipher = new byte[pt];
        int baseLen = encryptECBPrefixSuffix(cipher).length;
        int newLen = baseLen;

        while (baseLen == newLen) {
            pt++;
            cipher = encryptECBPrefixSuffix(new byte[pt]);
            newLen = cipher.length;
        }

        return newLen - baseLen;
    }

    public static int getEncryptionUnknownStringLength() throws Exception {
        int pt = 0;
        byte[] cipher = new byte[pt];
        int baseLen = encryptECBPrefixSuffix(cipher).length;
        int newLen = baseLen;

        while (baseLen == newLen) {
            pt++;
            cipher = encryptECBPrefixSuffix(new byte[pt]);
            newLen = cipher.length;
        }

        return baseLen - pt - getPrefixLen();
    }

    public static byte[] recoverUnknownString(int lengthOfUnknown) throws Exception {
        StringBuilder recovered = new StringBuilder();

        int prefixLen = getPrefixLen();
        int lenOfEncrypt = encryptECBPrefixSuffix(new byte[0]).length;
        int secretSize = lenOfEncrypt - prefixLen;
        int compareLen = prefixLen + secretSize;
        lengthOfUnknown++;

        for (int i = 1; i < lengthOfUnknown; i++) {
            byte[] s = new byte[secretSize - i];

            byte[] encrypted = encryptECBPrefixSuffix(s);

            for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
                byte[] test = new byte[s.length + recovered.toString().getBytes().length + 1];
                System.arraycopy(s, 0, test, 0, s.length);
                System.arraycopy(recovered.toString().getBytes(), 0, test, s.length, recovered.toString().getBytes().length);
                test[test.length - 1] = b;

                byte[] encryptedTest = encryptECBPrefixSuffix(test);

                if (Arrays.equals(encrypted, 0, compareLen, encryptedTest, 0, compareLen)) {
                    recovered.append((char) b);
                    break;
                }
            }
        }

        return recovered.toString().getBytes();
    }
}
