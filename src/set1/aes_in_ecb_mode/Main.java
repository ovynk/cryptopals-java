package set1.aes_in_ecb_mode;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException,
            NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException,
            BadPaddingException, NoSuchProviderException, InvalidKeyException {
        Scanner scanner = new Scanner(new File("src/set1/aes_in_ecb_mode/7.txt"));
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }


        byte[] encryptedText = stringBuilder.toString().getBytes();
        String key = "YELLOW SUBMARINE";

        String decryptedText = new String(
                ECB.decryptAES128(Base64.getDecoder().decode(encryptedText), key.getBytes(StandardCharsets.UTF_8))
        );
        System.out.println(decryptedText);

        String encryptedAgain = new String(
                Base64.getEncoder().encode(ECB.encryptAES128((decryptedText.getBytes()), key.getBytes(StandardCharsets.UTF_8)))
        );
        System.out.println("\nEncrypted again text:\n" + encryptedAgain);
    }
}
