package Mentality.components;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.Arrays;
import java.util.Random;

public class Password {
    private String passHash;

    private Password(String p) {
        passHash = new String(Base64.encodeBase64(DigestUtils.sha256(p)));
        if (passHash.length() < 32) {
            Random r = new Random(passHash.charAt(passHash.length()/2));
            while (passHash.length() < 32) {
                int i = r.nextBoolean() ? r.nextInt(26) + 'A' : r.nextInt(26) + 'a';
                passHash = r.nextBoolean() ? passHash + (char)i : (char)i + passHash;
            }
        }
        passHash = passHash.substring(0, 32);
    }

    public static String hashPassword(String p) {
        return new Password(p).passHash;
    }

    public static String toKey(String e, char[] p) {
        return Integer.toUnsignedString(Arrays.hashCode(p)) + e;
    }
}
