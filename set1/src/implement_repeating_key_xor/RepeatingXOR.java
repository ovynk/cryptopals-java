package implement_repeating_key_xor;

public class RepeatingXOR {
    public static String encryptToHexRepeatingXOR(String text, String key) {
        StringBuilder encrypted = new StringBuilder();

        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            encrypted.append(String.format("%02x", text.charAt(i) ^ key.charAt(keyIndex++)));
            if (keyIndex == key.length()) keyIndex = 0;
        }

        return encrypted.toString();
    }

    public static String decryptRepeatingXOR(String text, String key) {
        StringBuilder encrypted = new StringBuilder();

        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            encrypted.append((char) (text.charAt(i) ^ key.charAt(keyIndex++)));
            if (keyIndex == key.length()) keyIndex = 0;
        }

        return encrypted.toString();
    }
}
