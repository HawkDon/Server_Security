package logic;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptValidation {

    public static boolean validate(String userPw, String password) {
        boolean isIdentical;
        try {
            isIdentical = BCrypt.checkpw(userPw, password);
        } catch(Exception e) {
            isIdentical = false;
        }
        return isIdentical;
    }
}
