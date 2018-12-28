//package xorcrypto;
import java.util.Base64;
public class Base64Crypto {
    static String encrypt(String plainText) {
        String result = Base64.getEncoder().encodeToString(plainText.getBytes());
        return result;
    }

    static String decrypt(String base64) {
        byte[] decoded = Base64.getDecoder().decode(base64);
        String result = new String(decoded);
        return result;
    }
}
