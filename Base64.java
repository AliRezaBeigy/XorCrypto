package xorcrypto;

public class Base64 {
    public static String encrypt(String plainText) {
        byte[] encode = plainText.getBytes();
        return java.util.Base64.getEncoder().encodeToString(encode);
    }

    public static String decrypt(String base64) {
        byte[] decode = java.util.Base64.getDecoder().decode(base64);
        return new String(decode) ;
    }
}