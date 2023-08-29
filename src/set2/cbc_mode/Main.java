package set2.cbc_mode;

import set2.pkcs7_padding.PKSCPadding;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("src/set2/cbc_mode/10.txt"));
        String key = "YELLOW SUBMARINE";
        char c = 0;
        String initVector = PKSCPadding.padding("", 16, c);

        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }

        String res = new String(CBC.decrypt(
                stringBuilder.toString().getBytes(),
                key.getBytes(StandardCharsets.UTF_8),
                initVector.getBytes()));
        System.out.println(res);
        System.out.println("Encrypted again: " +
                new String(CBC.encrypt(res.getBytes(),
                        key.getBytes(StandardCharsets.UTF_8),
                        initVector.getBytes())));
    }
}
