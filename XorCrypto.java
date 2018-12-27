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
        
        String result = "" ;
        int x=0 , y=0 ;
        while(x<input.length()){
            try {
            char C = (char) (input.charAt(x) ^ key.charAt(y)) ; x++ ; y++ ;
            result += C ;
            }
            catch(java.lang.StringIndexOutOfBoundsException e){ key += key ; }
        }
        return result ;
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