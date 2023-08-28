package set1.hex_to_base64;

public class Main {
    public static void main(String[] args) {
        String hex = "49276d206b696c6c696e6720796f757220627261696e20" +
                "6c696b65206120706f69736f6e6f7573206d757368726f6f6d";

        System.out.println(HexToBase64.hexToBase64(hex));
    }
}