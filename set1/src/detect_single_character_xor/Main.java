package detect_single_character_xor;

import java.io.File;
import java.io.FileNotFoundException;
import signle_byte_xor_chiper.SingleXORCipher;
import hex_to_base64.HexToBase64;

import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("set1\\src\\detect_single_character_xor\\4.txt"));
        Map<Integer, String> results = new HashMap<>();

        Map.Entry<Character, String> entry;
        while (scanner.hasNextLine()) {
            entry = SingleXORCipher.decryptStringSingleXORCipher(HexToBase64.hexToIntArray(scanner.nextLine()));
            results.put(Integer.valueOf(entry.getKey()), entry.getValue());
        }
        System.out.println("Encrypted string: [" + results.get(Collections.max(results.keySet())) + "]");

    }
}
