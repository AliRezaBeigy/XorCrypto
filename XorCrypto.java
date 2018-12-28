
import java.util.Base64;

package xorcrypto;

public class XorCrypto {

    static final String KEY = "Password";
    static final String SEPARATOR = "----------------------------------------";
    static String[] tests = new String[]{"Hello", "This is a test", "I want to encode me then decode me :D"};

    public static void main(String[] args) {
        for (String test : tests) {
            String encoded = encrypt(test, KEY);
            String decrypted = decrypt(encoded, KEY);
            System.out.printf("%s%n%s -> %s%n%s -> %s%n", SEPARATOR, test, encoded, encoded, decrypted);
        }
    }

    static String encrypt(String plainText, String key) {
        String result = xor(plainText, key);
        return Base64.encrypt(result);
    }

    static String decrypt(String base64, String key) {
        String result = xor(Base64.decrypt(base64), key);
        return result;
    }

    static String xor(String input, String key) {
String result="";        
        // Compelet it
        // help : r = (char)(i ^ k);
        while (key.length() < input.length()/2) {
        key += key;
    }
  for (int i=0;i<input.length();i+=2) {
        String h = input.substring(i, i+2);
        int v1 = Integer.parseInt(h, 16);
        int v2 = key.charAt(i/2);
        int xor = v1 ^ v2;
  result += (char)(xor);
  }
        return result;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                             Output                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //      Hello -> GAQfHxg=                                                                               //
    //      GAQfHxg= -> Hello                                                                               //
    //      ----------------------------------------                                                        //
    //      This is a test -> BAkaAFcGAUQxQQcWBBs=                                                          //
    //      BAkaAFcGAUQxQQcWBBs= -> This is a test                                                          //
    //      ----------------------------------------                                                        //
    //      I want to encode me then decode me :D -> GUEEEhkbUhA/QRYdFAAWAXAMFlMDBxcKcAUWEBgLF0Q9BFNJMw==   //
    //      GUEEEhkbUhA/QRYdFAAWAXAMFlMDBxcKcAUWEBgLF0Q9BFNJMw== -> I want to encode me then decode me :D   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
}
