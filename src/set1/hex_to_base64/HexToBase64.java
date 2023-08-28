package set1.hex_to_base64;

import java.util.Base64;

public class HexToBase64 {
    public static String hexToBase64(String hex) {
        byte[] bytes = hexToByteArray(hex);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    public static byte[] hexToByteArray(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = Byte.parseByte(hex.substring(i, i + 2), 16);
        }
        return bytes;
    }

    public static int[] hexToIntArray(String hex) {
        int[] ints = new int[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            ints[i / 2] = Integer.parseInt(hex.substring(i, i + 2), 16);
        }
        return ints;
    }

    public static String hexDecode(String hex) {
        StringBuilder result = new StringBuilder();
        try {
            for (int i = 0; i < hex.length(); i += 2) {
                String str = hex.substring(i, i + 2);
                result.append((char) Integer.parseInt(str, 16));
            }
        } catch (IndexOutOfBoundsException e) {
            // if hex length is odd, last char will be parsed too
            result.append((char) Integer.parseInt(String.valueOf(hex.charAt(hex.length() - 1)), 16));
        }
        return result.toString();
    }
}