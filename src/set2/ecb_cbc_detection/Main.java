package set2.ecb_cbc_detection;

import java.util.Arrays;
import java.util.Base64;

import static set2.ecb_cbc_detection.Oracle.encryptRand;

public class Main {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            byte[] _48bytesForEncrypt = AESDetection.constructAESDetectingPlaintext(16);
            byte[] s = Base64.getDecoder().decode(encryptRand(_48bytesForEncrypt));
            System.out.print(" {" + new String(s) + "} " + AESDetection.detectECBCBC(s, 16) + "\n");
        }
    }
}
