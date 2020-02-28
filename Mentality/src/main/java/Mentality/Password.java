package Mentality;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.*;
import java.util.Random;

class Password {
    private String passHash;

    private Password(JPasswordField p) {
        passHash = new String(Base64.encodeBase64(DigestUtils.sha256(new String(p.getPassword()).getBytes())));
        if (passHash.length() < 32) {
            Random r = new Random(passHash.charAt(passHash.length()/2));
            while (passHash.length() < 32) {
                int i = r.nextBoolean() ? r.nextInt(26) + 'A' : r.nextInt(26) + 'a';
                passHash = r.nextBoolean() ? passHash + (char)i : (char)i + passHash;
            }
        }
        passHash = passHash.substring(0, 32);
    }

    static String getHashedPassword(JPasswordField p) {
        return new Password(p).passHash;
    }
}
