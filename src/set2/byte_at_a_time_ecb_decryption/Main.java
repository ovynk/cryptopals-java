package set2.byte_at_a_time_ecb_decryption;

import set1.aes_in_ecb_mode.ECB;

import java.util.Arrays;
import java.util.Base64;

import static set2.ecb_cbc_detection.Oracle.randBytes;

public class Main {
    public static final byte[] key = randBytes(16);

    public static final String append =
            "Um9sbGluJyBpbiBteSA1LjAKV2l0aCBteSByYWctdG9wIGRvd24gc28gbXkg" +
            "aGFpciBjYW4gYmxvdwpUaGUgZ2lybGllcyBvbiBzdGFuZGJ5IHdhdmluZyBq" +
            "dXN0IHRvIHNheSBoaQpEaWQgeW91IHN0b3A/IE5vLCBJIGp1c3QgZHJvdmUg" +
            "YnkK";

    public static void main(String[] args) throws Exception {
        byte[] empty = {};
        int lenOfEncryption = encryptRandECB(empty, key).length;
        int lenOfUnknownString = getEncryptionUnknownStringLength();
        System.out.println("Size of blocks to ensure(must be 16): " + getEncryptionSizeBlock());
        System.out.println("Length of unknown string: " + lenOfUnknownString);

        byte[] recovered = recoverUnknownString(lenOfEncryption, lenOfUnknownString);
        String recoveredString = new String(recovered);
        System.out.println("Unknown string:\n" + recoveredString);

        byte[] expected = Base64.getDecoder().decode(append.getBytes());
        String expectedString = new String(expected);

        System.out.println("Does origin unknown string equal to recovered: " + expectedString.equals(recoveredString));
    }

    public static byte[] encryptRandECB(byte[] text, byte[] randomKey) throws Exception {
        byte[] appendBytes = Base64.getDecoder().decode(append.getBytes());

        byte[] textBytes = new byte[text.length + appendBytes.length];

        System.arraycopy(text, 0, textBytes, 0, text.length);
        System.arraycopy(appendBytes, 0, textBytes, text.length, appendBytes.length);

        return ECB.encryptAES128(textBytes, randomKey);
    }

    public static int getEncryptionSizeBlock() throws Exception {
        int pt = 0;
        byte[] cipher = new byte[pt];
        int baseLen = encryptRandECB(cipher, key).length;
        int newLen = baseLen;

        while (baseLen == newLen) {
            pt++;
            cipher = encryptRandECB(new byte[pt], key);
            newLen = cipher.length;
        }

        return newLen - baseLen;
    }

    public static int getEncryptionUnknownStringLength() throws Exception {
        int pt = 0;
        byte[] cipher = new byte[pt];
        int baseLen = encryptRandECB(cipher, key).length;
        int newLen = baseLen;

        while (baseLen == newLen) {
            pt++;
            cipher = encryptRandECB(new byte[pt], key);
            newLen = cipher.length;
        }

        return baseLen - pt;
    }

    public static byte[] recoverUnknownString(int lenOfEncrypt, int lengthOfUnknown) throws Exception {
        StringBuilder recovered = new StringBuilder();

        lengthOfUnknown++;
        for (int i = 1; i < lengthOfUnknown; i++) {
            String s = "A".repeat(lenOfEncrypt - i);

            byte[] encrypted = encryptRandECB(s.getBytes(), key);

            for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
                byte[] test = new byte[s.getBytes().length + recovered.toString().getBytes().length + 1];
                System.arraycopy(s.getBytes(), 0, test, 0, s.getBytes().length);
                System.arraycopy(recovered.toString().getBytes(), 0, test, s.getBytes().length, recovered.toString().getBytes().length);
                test[test.length - 1] = b;

                byte[] encryptedTest = encryptRandECB(test, key);

                if (Arrays.equals(encrypted, 0, lenOfEncrypt, encryptedTest, 0, lenOfEncrypt)) {
                    recovered.append((char) b);
                    break;
                }
            }
        }

        return recovered.toString().getBytes();
    }
}