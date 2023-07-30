package aes_in_ecb_mode;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        Scanner scanner = new Scanner(new File("set1\\src\\aes_in_ecb_mode\\7.txt"));
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }

        String encryptedText = stringBuilder.toString();
        String key = "YELLOW SUBMARINE";
        System.out.println(AES.decryptAES128(encryptedText, key));
    }
}
