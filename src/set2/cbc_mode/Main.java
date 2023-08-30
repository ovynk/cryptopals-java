package set2.cbc_mode;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("src/set2/cbc_mode/10.txt"));
        String key = "YELLOW SUBMARINE";
        char char0 = 0;
        String initVector = ("" + char0).repeat(16);

        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }

        String res = new String(CBC.decrypt(
                Base64.getDecoder().decode(stringBuilder.toString().getBytes()),
                key.getBytes(StandardCharsets.UTF_8),
                initVector.getBytes())
        );
        System.out.println(res);

        System.out.println("Encrypted again: " +
                Base64.getEncoder().encodeToString(
                        CBC.encrypt(res.getBytes(), key.getBytes(StandardCharsets.UTF_8), initVector.getBytes()))
        );
    }
}
