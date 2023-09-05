package set1.fixed_xor;

import set1.hex_to_base64.*;

public class FixedXOR {
    public static String produceXOR(String hex1, String hex2) {
        if (hex1.length() != hex2.length()) throw new IndexOutOfBoundsException("Hex1 and Hex2 arent the same length");

        byte[] hex1Bytes = HexToBase64.hexToByteArray(hex1);
        byte[] hex2Bytes = HexToBase64.hexToByteArray(hex2);
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < hex1Bytes.length; i++) {
            s.append(Integer.toHexString(hex1Bytes[i] ^ hex2Bytes[i]));
        }

        return s.toString();
    }

    public static byte[] produceXOR(byte[] hex1, byte[] hex2) {
        if (hex1.length != hex2.length) throw new IndexOutOfBoundsException("Hex1 and Hex2 arent the same length");

        byte[] result = new byte[hex1.length];
        for (int i = 0; i < hex1.length; i++) {
            result[i] = (byte) (hex1[i] ^ hex2[i]);
        }

        return result;
    }
}
