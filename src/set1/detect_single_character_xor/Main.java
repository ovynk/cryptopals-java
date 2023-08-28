package set1.detect_single_character_xor;

import java.io.File;
import java.io.FileNotFoundException;

import set1.signle_byte_xor_chiper.ScoreCharText;
import set1.signle_byte_xor_chiper.SingleXORCipher;
import set1.hex_to_base64.HexToBase64;

import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/set1/detect_single_character_xor/4.txt"));

        ScoreCharText maxScored = null;
        long score = 0;
        while (scanner.hasNextLine()) {
            ScoreCharText temp = SingleXORCipher.decryptStringSingleXORCipher(HexToBase64.hexToIntArray(scanner.nextLine()));
            if (temp.score() > score) {
                maxScored = temp;
                score = maxScored.score();
            }
        }

        assert maxScored != null;
        System.out.println("Char of string: " + maxScored.c());
        System.out.println("Score of string: " + maxScored.score());
        System.out.println("Decrypted string: " + maxScored.text());
    }
}
