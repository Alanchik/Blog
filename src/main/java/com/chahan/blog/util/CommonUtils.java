package com.chahan.blog.util;

import lombok.NoArgsConstructor;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CommonUtils {

    /*PATTERNS*/
    public static final String USERNAME_PATTERN = "^(?=\\S+$).{8,}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$";

    // ERROR MESSAGES
    public static final String ERROR_USERNAME_PATTERN = "Username must be min 8 symbols and no spaces";
    public static final String ERROR_PASSWORD_PATTERN = "Password must have at least one number" +
            " and lowercase letter" +
            ", no spaces and at least 8 characters";

    public static final String USER_ALREADY_EXISTS = "User already exists";

}
