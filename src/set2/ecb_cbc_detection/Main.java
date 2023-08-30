package set2.ecb_cbc_detection;

import static set2.ecb_cbc_detection.Oracle.encryptRand;

public class Main {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            byte[] _48bytesForEncrypt = AESDetection.constructAESDetectingPlaintext(16);
            byte[] s = encryptRand(_48bytesForEncrypt);
            System.out.print(" " + AESDetection.detectECBCBC(s, 16) + "\n");
        }
    }
}
