package xorcrypto;

public class Base64 {
    public static String encrypt(String plainText) {
        String result = plainText;
       result=Base64.getEncoder().encodeToString(result.getBytes());
        return result;
    }

    public static String decrypt(String base64) {
        String result = base64;
        byte b[]=Base64.getDecoder().decode(result);
        result=new String(b);
        return result;
    }
}
