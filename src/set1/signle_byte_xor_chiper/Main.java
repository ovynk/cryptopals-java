package set1.signle_byte_xor_chiper;

import set1.hex_to_base64.*;

public class Main {
    public static void main(String[] args) {
        String hex = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        int[] hexBytes = HexToBase64.hexToIntArray(hex);

        ScoreCharText result = SingleXORCipher.decryptStringSingleXORCipher(hexBytes);
        System.out.println(result.getText());
    }
}
