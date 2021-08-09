package com.chahan.blog.util;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CommonUtils {

    /*PATTERNS*/
    public static final String USERNAME_PATTERN = "^(?=\\S+$).{8,}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$";

    /*ERROR MESSAGES*/
    public static final String ERROR_USERNAME_PATTERN = "Username must be min 8 symbols and no spaces";
    public static final String ERROR_PASSWORD_PATTERN = "Password must have at least one number"
            + " and lowercase letter"
            + ", no spaces and at least 8 characters";

    public static final String ERROR_USER_ALREADY_EXISTS = "User already exists";
    public static final String ERROR_USER_FOLLOW_YOURSELF = "User can't subscribe to himself";
    public static final String ERROR_USER_NOT_FOUND = "User not found";
    public static final String ERROR_ACTION_FORBIDDEN = "Action with comment forbidden";
    public static final String ERROR_POST_NOT_BELONG = "Post doesn't belong to current user";
    public static final String ERROR_INCORRECT_ID = "Id specified incorrectly";
    public static final String ERROR_REPLY_TO_REPLY_FORBIDDEN = "Action with post reply to reply forbidden";
}
