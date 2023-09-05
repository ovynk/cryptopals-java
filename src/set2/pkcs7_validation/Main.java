package set2.pkcs7_validation;

import set2.pkcs7_padding.PKSCPadding;

import static set2.pkcs7_padding.PKSCPadding.validate;

public class Main {
    public static void main(String[] args) {
        String s = "ICE ICE BABY";
        String paddedS = PKSCPadding.padding(s, 16);

        System.out.println(paddedS + " " + validate(paddedS, 16));

        String wrongPad = s + (char) 3 + (char) 4 +(char) 4 + (char) 4;
        System.out.println(wrongPad + " " + validate(wrongPad, 16));
    }

}
