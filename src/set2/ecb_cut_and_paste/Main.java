package set2.ecb_cut_and_paste;

import set1.aes_in_ecb_mode.ECB;
import set2.ecb_cbc_detection.Oracle;
import set2.pkcs7_padding.PKSCPadding;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException,
            BadPaddingException, InvalidKeyException {
        String email1 = "ftest@bar.com";
        String email2 = "ftest@bar." + PKSCPadding.padding("admin", 16) + "com";

        String profileLine1 = profileConvertToLine(buildProfile(email1));
        String profileLine2 = profileConvertToLine(buildProfile(email2));

        byte[] key = Oracle.randBytes(16);
        byte[] encrypted1 = ECB.encryptAES128(profileLine1.getBytes(), key);
        byte[] encrypted2 = ECB.encryptAES128(profileLine2.getBytes(), key);

        byte[] merged = new byte[encrypted1.length];
        System.arraycopy(encrypted1, 0, merged, 0, 32);
        System.arraycopy(encrypted2, 16, merged, 32, 16);

        System.out.println(Arrays.toString(encrypted1));
        System.out.println(Arrays.toString(encrypted2));
        System.out.println(Arrays.toString(merged));

        System.out.println(new String(ECB.decryptAES128(encrypted1, key)));
        String admin = new String(ECB.decryptAES128(merged, key));
        System.out.println(admin);

        System.out.println(parse(admin));
    }

    public static LinkedHashMap<String, String> buildProfile(String email) {
        if (email.indexOf('&') != -1 && email.indexOf('=') != -1) {
            throw new RuntimeException("Invalid email syntax");
        }

        LinkedHashMap<String, String> converted = new LinkedHashMap<>();
        converted.put("email", email);
        converted.put("uid", "10");
        converted.put("role", "user");

        return converted;
    }

    public static String profileConvertToLine(LinkedHashMap<String, String> profile) {
        StringBuilder profileString = new StringBuilder();

        for (Map.Entry<String, String> line : profile.entrySet()) {
            profileString.append(line.getKey()).append('=').append(line.getValue()).append('&');
        }

        profileString.deleteCharAt(profileString.length() - 1);
        return profileString.toString();
    }

    public static LinkedHashMap<String, String> parse(String input) {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        char equal = '=';
        char and = '&';

        for (int i = 0; i < input.length();){
            int tempEqual = input.indexOf(equal, i);
            String toEqual = input.substring(i, tempEqual);
            i = tempEqual + 1;

            String toAnd;
            try {
                int tempAnd = input.indexOf(and, tempEqual);
                toAnd = input.substring(i, tempAnd);
                i = tempAnd + 1;
            } catch (IndexOutOfBoundsException e) {
                int lastIndexOfEqual = input.lastIndexOf(equal) + 1;
                toAnd = input.substring(lastIndexOfEqual);
                result.put(toEqual, toAnd);
                break;
            }

            result.put(toEqual, toAnd);
        }

        return result;
    }
}
