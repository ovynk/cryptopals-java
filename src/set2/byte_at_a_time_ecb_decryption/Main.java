package set2.byte_at_a_time_ecb_decryption;

import java.util.Base64;

import static set2.byte_at_a_time_ecb_decryption.ECBSuffix.*;

public class Main {
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

}