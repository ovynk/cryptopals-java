package set2.cbc_mode;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CBC {
    public static byte[] encrypt(byte[] plaintext, byte[] key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        return Base64.getEncoder().encode(cipher.doFinal(plaintext));
    }

    public static byte[] decrypt(byte[] cipherText, byte[] key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        return cipher.doFinal(Base64.getDecoder().decode(cipherText));
    }
}