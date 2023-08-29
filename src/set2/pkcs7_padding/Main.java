package set2.pkcs7_padding;

public class Main {
    public static void main(String[] args) {
        char c = 4;
        System.out.println(PKSCPadding.padding("YELLOW SUBMARINE", 20, c));
    }
}
