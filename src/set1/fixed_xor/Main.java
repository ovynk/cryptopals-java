package set1.fixed_xor;

public class Main {
    public static void main(String[] args) {
//        System.out.println(5 ^ 5);
//        System.out.println(398 ^ 1909);
//        System.out.println(0001 ^ 0000010);

        String result = FixedXOR.produceXOR(
                "1c0111001f010100061a024b53535009181c",
                "686974207468652062756c6c277320657965");
        String expected = "746865206b696420646f6e277420706c6179";

        System.out.println(result.equals(expected));
    }
}
