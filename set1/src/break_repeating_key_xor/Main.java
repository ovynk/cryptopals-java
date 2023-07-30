package break_repeating_key_xor;

import implement_repeating_key_xor.RepeatingXOR;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static break_repeating_key_xor.BreakRepeatingXOR.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Test of hamming distance method, result: " +
                hammingDistance("this is a test", "wokka wokka!!!"));

        Scanner scanner = new Scanner(new File("set1\\src\\break_repeating_key_xor\\6.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        Base64.Decoder encoder = Base64.getDecoder();

        while (scanner.hasNextLine()) {
            stringBuilder.append(new String(encoder.decode(scanner.nextLine())));
        }

        HashMap<Float, Integer> normalizedKeySizes = normalizedKeySizes(stringBuilder.toString());

        // trying to find best key
        List<Float> sortedKeySizes = normalizedKeySizes.keySet().stream().sorted().toList();
        for (int i = 0; i < 5; i++) {
            int keySize = normalizedKeySizes.get(sortedKeySizes.get(i));
            System.out.println(
                    "Keysize: " + keySize +
                    " Key: " + breakByKeySizeRepeatingXOR(stringBuilder.toString(), keySize)
            );
        }

        String key = breakByKeySizeRepeatingXOR(stringBuilder.toString(), 29);
        System.out.println("\nBest Key: " + key);
        System.out.println("Decrypted text:\n" + RepeatingXOR.decryptRepeatingXOR(stringBuilder.toString(), key));
    }
}
