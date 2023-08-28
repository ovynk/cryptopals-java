package set1.detect_aes_in_ecb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/set1/detect_aes_in_ecb/8.txt"));

        ArrayList<String> strings = new ArrayList<>();
        while (scanner.hasNextLine()) {
            strings.add(scanner.nextLine());
        }

        for (int i = 0; i < strings.size(); i++) {
            Set<String> uniqueBlocks = new HashSet<>();
            for (int j = 0; j < strings.get(i).length(); j+=16) {
                String chunk = strings.get(i).substring(j, j + 16);
                uniqueBlocks.add(chunk);
            }

            int numBlocks = strings.get(i).length() / 16;
            int numUniqueBlocks = uniqueBlocks.size();

            if (numBlocks - numUniqueBlocks != 0) {
                System.out.println("Line: " + (i + 1) +
                        ", number of all blocks: " + numBlocks +
                        ", unique blocks: " + numUniqueBlocks +
                        ", line: " + strings.get(i));
            }
        }

    }
}
