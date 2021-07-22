package utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class AESUtil {
    private static SecretKeySpec secretKey;
    private static byte[] key;

    private static void setEncryptKey() throws Exception {
        final String encryptKey = "3213455467512341abc!!!234@$%&sbg";
        try {
            key = encryptKey.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e.getStackTrace().toString());
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e.getStackTrace().toString());
        }
    }

    public static String encrypt(String strToEncrypt) throws Exception {
        try {
            setEncryptKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new Exception("Houve um erro enquanto encriptava");
        }
    }

    public static String decrypt(String strToDecrypt) throws Exception {
        try {
            setEncryptKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            throw new Exception("Houve um erro enquanto desencriptava");
        }
    }
}
